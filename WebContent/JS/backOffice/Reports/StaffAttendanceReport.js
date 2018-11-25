$(document).ready(function() {

	$("#accyear").val($("#hacademicyaer").val());
	/*	if(!$("#successid").val()=="")
	{
		$("#txtstyle, #txtstyle").hide();

		$("#allstudent").show();
	}
	else
	{
		$("#allstudent").hide();

	}*/

	if($("#allstudent tbody tr").length == 0){
		$("#allstudent").show();
		$("#allstudent tbody ").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
		pagination(100);
		$(".numberOfItem").empty();
		$(".numberOfItem").append("No. of Records 0");

	}




	// for settings color js
	$("#dropdown").click(function(){
		$("#hbox").slideToggle("slow");
	});

	$('.col-md-10, .vertical').click(function(){
		$("#hbox").hide();
	});

	$("#accyear").change(function(){
		getAcademicYearStartDate();
		academicyearchange();
		getTeacherList();
	})

	// for showing search destails
 $("#locationname").change(function(){
	 getTeacherList(); 
 });

	academicyearchange();

	$("#iconsimg").click(function(){

		if($("#allstudent tbody tr").length == 0){
			$(".errormessagediv").show();
			$(".validateTips").text("No records found");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
			return false;
		}else if($("#allstudent tbody tr:first td:first").text() == "No Records Found"){
			$(".errormessagediv").show();
			$(".validateTips").text("No records found");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
			return false;
		}

	});



	$("#search").click(function(){
		$("#allstudent").show();
		/*$(".selecteditems").show();*/
		//$("#txtstyle, #txtstyle").slideToggle();

		var locId=$("#locationname").val();
		var accyear=$("#accyear").val();
		var fromdate=$("#Fromdate").val();
		var todate=$("#todate").val();
		var teachertype=$("#teachertype").val();
		var teachername=$("#teachername").val();




		if(accyear=="" && fromdate=="" && todate=="" && teachertype=="" && teachername==""){



			$("#txtstyle, #txtstyle").slideToggle();


		}

		if(locId==""){
			$('.errormessagediv').show();
			$('.validateTips').text("Select Branch Name");

			return false;
		}

		if(accyear==""){

			$('.errormessagediv').show();
			$('.validateTips').text("Select Academic Year");

			return false;

		}

		if(fromdate==""){

			$('.errormessagediv').show();
			$('.validateTips').text("Select From Date");

			return false;

		}if(todate==""){

			$('.errormessagediv').show();
			$('.validateTips').text("Select To Date");

			return false;

		}


		if(teachertype==""){

			$('.errormessagediv').show();
			$('.validateTips').text("Select Teacher Type");

			return false;

		}



		if(teachername==""){

			$('.errormessagediv').show();
			$('.validateTips').text("Select Teacher Name");

			return false;

		}	



		else{

			getStaffAttendanceAction();
			/*document.getElementById("staffattendanceid").submit();*/
		}



	});



	$("#teachertype").change(function(){
		getTeacherList();
		
	});
	$("#excelDownload").click(function(){
		var accyear=$("#accyear").val();
		var fromdate=$("#Fromdate").val();
		var todate=$("#todate").val();
		var teachertype=$("#teachertype").val();
		var teachername=$("#teachername").val();
		var locid=$("#locationname").val();
		window.location.href = 'staffattendancereport.html?method=staffAttendanceExcelReport&accyear='+accyear
		+ ' &fromdate='+fromdate+' &todate='+ todate+ ' &teachertype='+teachertype+ ' &teachername='+teachername
		+ ' &locid='+locid +'&locationname='+$("#locationname option:selected").text()+'&accyearname='+$("#accyear option:selected").text()+'&teachertypeselect='+$("#teachertype option:selected").text();

	});
	$("#pdfDownload").click(function(){
		var accyear=$("#accyear").val();
		var fromdate=$("#Fromdate").val();
		var todate=$("#todate").val();
		var teachertype=$("#teachertype").val();
		var teachername=$("#teachername").val();
		var locid=$("#locationname").val();
		window.location.href = 'staffattendancereport.html?method=staffAttendancePDFReport&accyear='+accyear
		+ ' &fromdate='+fromdate+' &todate='+ todate+ ' &teachertype='+teachertype+ ' &teachername='+teachername
		+ ' &locid='+locid +'&locationname='+$("#locationname option:selected").text()+'&accyearname='+$("#accyear option:selected").text()+'&teachertypeselect='+$("#teachertype option:selected").text();
	});
});


function getStaffAttendanceAction(){

	var datalist = {
			"accyear":$("#accyear").val(),
			"teachertype":$("#teachertype").val(),
			"Fromdate":$("#Fromdate").val(),
			"todate":$("#todate").val(),
			"teachername":$("#teachername").val(),
			"locid":$("#locationname").val(),


	}; 
	$.ajax({
		type : 'POST',
		url : "staffattendancereport.html?method=getStaffAttendanceList",
		data : datalist,
		async:false,

		success : function(data) {

			var result = $.parseJSON(data);
			$("#allstudent tbody").empty();

			if(result.staffattendanceList.length>0){
				for(var i=0;i<result.staffattendanceList.length;i++){


					$("#allstudent tbody").append("<tr>"
							+"<td>"+result.staffattendanceList[i].count+"</td>"
							+"<td> "+result.staffattendanceList[i].teacherName+" </td>"
							+"<td> "+result.staffattendanceList[i].date+"</td>"
							+"<td> "+result.staffattendanceList[i].status+" </td>"
							+"</tr>");
				}	
			}
			else{
				$("#allstudent tbody").append("<tr>" +"<td colspan='4'>No Records Found</td>" +"</tr>");
			}

			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.staffattendanceList.length);
		}
	});

}

function getAcademicYearStartDate(){

	var accyear=$("#accyear").val();
	$.ajax({
		type:"POST", 
		url:"menuslist.html?method=getAcademicYearStartDate",
		data:{
			"accyear":accyear,
		},
		async:false,
		success:function(response){
			var  result=$.parseJSON(response);
			$("#haccStartdate").val(result.startdate);
			$("#haccEnddate").val(result.enddate);
		}

	});

}

function academicyearchange(){
	$('#Fromdate').datepicker('destroy');
	$('#todate').datepicker('destroy');

	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:new Date($("#haccEnddate").val()),
		minDate:new Date($("#haccStartdate").val()),
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){

			var min = $(this).datepicker('getDate'); // Get selected date
			$("#todate").datepicker('option', 'minDate', min);

		}

	});

	$("#todate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:new Date($("#haccEnddate").val()),
		minDate:new Date($("#haccStartdate").val()),
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var max = $(this).datepicker('getDate'); // Get selected date
			$('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');

		}

	});

}

function getTeacherList(){
	var teachertype=$("#teachertype").val();
	var locationname=$("#locationname").val();
 
	if(teachertype=="teaching"){




		datalist={
				"teachertype" : teachertype,
				"locationname":locationname
		},

		$.ajax({

			type : 'POST',
			url : "staffattendancereport.html?method=getTeachernameAction",
			data : datalist,
			async : false,
			success : function(response) {



				var result = $.parseJSON(response);




				$('#teachername').html("");

				$('#teachername').append(
						'<option value="'
						+ "all" + '">'
						+ "----------Select----------"
						+ '</option>');
				for ( var j = 0; j < result.teachinglist.length; j++) {
					$('#teachername')
					.append(
							'<option value="'
							+ result.teachinglist[j].teacherId
							+ '">'
							+ result.teachinglist[j].teacherName
							+ '</option>');
				}

			}


		});


	}
	else
	{
		datalist={
				"teachertype" : teachertype
		},
		$.ajax({
			type : 'POST',
			url : "staffattendancereport.html?method=getTeachernameAction",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				$('#teachername').html("");
				$('#teachername').append(
						'<option value="'
						+ "all" + '">'
						+ "----------Select----------"
						+ '</option>');
				for ( var j = 0; j < result.nonteachinglist.length; j++) {
					$('#teachername')
					.append(
							'<option value="'
							+ result.nonteachinglist[j].teacherId
							+ '">'
							+ result.nonteachinglist[j].teacherName
							+ '</option>');
				}

			}


		});
	}
	
}

