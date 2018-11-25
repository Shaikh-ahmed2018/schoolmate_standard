$(document).ready(function() {
	
	$("#accyear").val($("#haccyear").val());
	$("#location").val($("#hlocid").val());
	
	getClasses();
	$("#location").change(function(){
		getClasses();
		getTimeTableList();
		 
	});
	
	$("#accyear").change(function(){
		getTimeTableList(); 
	});
	
	$('#class').change(function() {
		getClassSection();
		getTimeTableList();
		
});
	
	$("#section").change(function(){
		getTimeTableList();
	});
	
	
	
});

function getClasses() {
	var location =$("#location").val();
	 
	$.ajax({
		type : 'GET',
		url : "studentRegistration.html?method=getClassDetailWithOutStream",
		data:{"location":location},
		
		success : function(response) {
			$("#class").html("");
			$("#class").append('<option value="ALL">' + "ALL" + '</option>');
			var result = $.parseJSON(response);

			for ( var j = 0; j < result.ClassList.length; j++) {

				$("#class").append(
						'<option value="' + result.ClassList[j].classcode
						+ '">' + result.ClassList[j].classname
						+ '</option>');
			}
			 
			$("#class").val($("#historyclass1").val());
			if($("#class").val()!="ALL"){
				getClassSection();
			}
		}
	});
}

function getClassSection() {
	  var classId=$("#class").val();
	  var accyear=$("#accyear").val();
	  var locationId=$("#location").val();

	$.ajax({
		type : "GET",
		url : "reportaction.html?method=getSectionByClass",
		data : {"classId":classId,
				"location":locationId},
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);

			$("#section").html("");
			$("#section").append('<option value="all">' + "ALL"	+ '</option>');

			for (var j = 0; j < result.SectionList.length; j++) {

				$("#section").append(
						'<option value="'
						+ result.SectionList[j].sectionId
						+ '">'
						+ result.SectionList[j].sectionname
						+ '</option>');
			}
			$("#section").val($("#historysection1").val());
			
			if($("#section").val()!="all"){
				getTimeTableList();
			}
		}
});
}

function getTimeTableList() { 
	
	datalist = {
		"classId" :$("#class").val(),
		"locationId":$("#location").val(),
		"accyer":$("#accyear").val(),
		"section":$("#section").val(),
	}, 
	$.ajax({
		type :'POST',
		url :'teachermenuaction.html?method=getTimeTableList',
		data : datalist,
		async : false,
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				
				var len=result.timeTableDetails.length;
				if(len>0){
				for(var i=0;i<result.timeTableDetails.length;i++){
				$("#allstudent tbody").append("<tr id='"+result.timeTableDetails[i].teacherId+"'>"
						+"<td>"+result.timeTableDetails[i].subscriberId+"</td>" 
						+"<td> "+result.timeTableDetails[i].abbrvation+" </td>"
						+"<td> "+result.timeTableDetails[i].teacherName+" </td>"
						+"</tr>");
				  }	
				$("#allstudent tbody tr").click(function(){
					window.location.href="teachermenuaction.html?method=viewTeacherTimeTable&userId="+$(this).closest('tr').attr('id');
					});
				}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='3'>No Records Found</td></tr>");
				}
				
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+len);
		}
	});

}
