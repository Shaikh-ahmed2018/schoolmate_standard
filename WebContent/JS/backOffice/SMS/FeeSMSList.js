$(document).ready(function(){
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='6'>No Records Found</td></tr>");
	}

	$('#add').click(function(){
		window.location.href="smsPath.html?method=addFeeSMS";
	});

	$("#Acyearid").val($("#hiddenAcademicYear").val());
	$("#locationname").val($("#hiddenlocId").val());
	


	getClassList();
	$("#locationname").change(function(){
		getClassList();
		getFeeSmsList();
	
	});

	$("#classname").change(function(){
		var classname=$("#classname").val();
		getSectionList(classname);
		getFeeSmsList();

	});

	$("#sectionid").change(function(){
		
		getFeeSmsList();
	});

	$("#Acyearid").change(function(){
		getAcademicYearStartDate();
		getFeeSmsList();
		academicyearchange();

	});

	academicyearchange();
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

function getFeeSmsList(){


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
		url:'smsPath.html?method=getFeeSmsList',
		data:dataList,	
		async : false,
		success: function(data){
			var result=$.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.feesmsList.length>0){

				for(var i=0;i<result.feesmsList.length;i++){

					$("#allstudent tbody").append("<tr>" +
							"<td>"+result.feesmsList[i].slno+"</td>" +
							"<td>"+result.feesmsList[i].createdtime+"</td>" +
							"<td>"+result.feesmsList[i].date+"</td>" +
							"<td>"+result.feesmsList[i].clsId+" - "+result.feesmsList[i].divId+"</td>" +
							"<td>"+result.feesmsList[i].studentname+"</td>" +
							"<td>"+result.feesmsList[i].status+"</td>" +
					"</tr>");

				}
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='8'>No Records Found</td></tr>");
			}

			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.feesmsList.length);
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
			getFeeSmsList();
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
			getFeeSmsList();
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
			getFeeSmsList();
		}

	});

}
