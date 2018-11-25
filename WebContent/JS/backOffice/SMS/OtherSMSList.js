$(document).ready(function(){
	
	$('#add').click(function(){ 
		window.location.href = "menuslist.html?method=addOtherSMS";
	});
	
	$("#Acyearid").val($("#hiddenAcademicYear").val());
	
	
	
	$("#locationname").change(function(){
		getClassList();
		getOtherSmsList();
	
		
		
	});
	
	$("#classname").change(function(){
		var classname=$("#classname").val();
		getSectionList(classname);
		getOtherSmsList();
		
	
	});
	$("#sectionid").change(function(){
		getOtherSmsList();
		
	});
	
	$("#Acyearid").change(function(){
		getAcademicYearStartDate();
		getOtherSmsList();
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

			$('#classname').append('<option value="All">' + "----------Select----------"	+ '</option>');

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

function getOtherSmsList(){
	
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
		url:'smsPath.html?method=getOtherSmsList',
		data:dataList,	
		async:false,
		
		success: function(data){
			var result=$.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.othersmslist.length>0){
					
				for(var i=0;i<result.othersmslist.length;i++){
					
					$("#allstudent tbody").append("<tr>" +
							"<td>"+result.othersmslist[i].sno+"</td>" +
							"<td>"+result.othersmslist[i].todate+"</td>" +
							"<td>"+result.othersmslist[i].studentName+"</td>" +
							"<td>"+result.othersmslist[i].classname+"</td>" +
							"<td>"+result.othersmslist[i].description+"</td>" +
							
							
					"</tr>");
				}
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			
			 $(".numberOfItem").empty();
			 $(".numberOfItem").append("No. of Records  "+result.othersmslist.length);
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
		    getOtherSmsList();
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
	        getOtherSmsList();
		}

	});

}
