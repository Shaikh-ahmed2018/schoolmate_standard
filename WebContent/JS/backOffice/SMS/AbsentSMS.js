$(document).ready(function(){

	if($("#allstudent tbody tr").length == 0){
		$("#allstudent tbody").append("<tr><td colspan='6'>No Records Found</td></tr>");
	}

	$('#add').click(function(){
		window.location.href="menuslist.html?method=absentlistFilter";
	});

	$("#Acyearid").val($("#hiddenAcademicYear").val());
	$("#locationname").val($("#hiddenlocId").val());

	getClassList();
	$("#locationname").change(function(){
		getClassList();
		getAbsentSmsList();
	});

	$("#classname").change(function(){
		var classname=$("#classname").val();
		getSectionList(classname);
		getAbsentSmsList();
	});
	$("#sectionid").change(function(){
		getAbsentSmsList();
	});

	$("#Acyearid").change(function(){
		getAbsentSmsList();
		getAcademicYearStartDate();
	});

	academicyearchange();

	if($("#historylocId").val()!="" || $("#historyacademicId").val()!="" || $("#historystartdate").val()!=""
		|| $("#historyenddate").val()!=""){

		if($("#historyacademicId").val()!="")
		{
			$("#historyacademicId").val($("#historyacademicId").val());
		}
		$("#locationname").val($("#historylocId").val());
		getClassList();
		$("#classname").val($("#historyclassname").val());
		getSectionList($("#classname").val());
		$("#sectionid").val($("#historysectionid").val());

		$("#startdate").val($("#historystartdate").val());
		$("#enddate").val($("#historyenddate").val());
		getAbsentSmsList();

	}

});


function getClassList(){
	var locationid=$("#locationname").val();
	datalist={
			"locationid" : locationid
	},

	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);


			$('#classname').html("");

			$('#classname').append('<option value="All">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function getSectionList(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname").val()
	},

	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#sectionid').html("");

			$('#sectionid').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}

function getAbsentSmsList(){


	dataList={
			"locid":$("#locationname").val(),
			"clasid":$("#classname").val(),
			"secid":$("#sectionid").val(),
			"accid":$("#Acyearid").val(),
			"startdate":$("#startdate").val(),
			"enddate":$("#enddate").val(),
	},
	$.ajax({
		type:'GET',
		url:'smsPath.html?method=getAbsentSmsList',
		data:dataList,	
		async:false,

		success: function(data){
			var result=$.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.absentsmslist.length>0){

				for(var i=0;i<result.absentsmslist.length;i++){

					$("#allstudent tbody").append("<tr>" +
							"<td>"+result.absentsmslist[i].slNo+"</td>" +
							"<td>"+result.absentsmslist[i].senddate+"</td>" +
							"<td>"+result.absentsmslist[i].date+"</td>" +
							"<td>"+result.absentsmslist[i].studentName+"</td>" +
							"<td>"+result.absentsmslist[i].classname+"-"+result.absentsmslist[i].section+"</td>"+
							"<td>"+result.absentsmslist[i].status+"</td>" +


					"</tr>");
				}
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='8'>No Records Found</td></tr>");
			}

			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.suddenholidaysmslist.length);
			pagination(100);
		}
	});
}

function getAcademicYearStartDate(){
	var accyear=$('#Acyearid').val();
	$.ajax({
		type:"POST", 
		url:"menuslist.html?method=getAcademicYearStartDate",
		data:{
			"accyear":accyear,
		},
		async:false,
		success:function(response){
			var  result=$.parseJSON(response);
			$("#hiddenstartdate").val(result.startdate);
			$("#hiddenenddate").val(result.enddate);

		}

	});



}

function academicyearchange(){
	$('#startdate').datepicker('destroy');
	$('#enddate').datepicker('destroy');
	fSDate = new Date($("#hiddenstartdate").val());
	fEDate = new Date($("#hiddenenddate").val());

	$("#startdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var min = $(this).datepicker('getDate'); // Get selected date
			$("#enddate").datepicker('option', 'minDate', min);
			getAbsentSmsList();
		}

	});

	$("#enddate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var max = $(this).datepicker('getDate'); // Get selected date
			$('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
			getAbsentSmsList();
		}

	});

}