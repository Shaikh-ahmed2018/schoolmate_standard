$(document).ready(function(){
	accyear=$("#hiddenaccyear").val();
	var examid = $("#hiddenexamid").val();
	classId=$("#hiddenclassid").val();
	sectionId=$("#hiddensectionid").val();
	hschoolLocation=$("#hiddenlocid").val();

	$("#back1").click(function(){
		$("#backValue").val(accyear+","+hschoolLocation+","+examid+","+classId);
  		$("#backform").submit();
	});

	$("#allstudent tbody tr").click(function(){
		var examid = $("#hiddenexamid").val();
		var accyear = $("#hiddenaccyear").val();
		var admissionno =$(this).find(".admissionno").text();
		var studentname =$(this).find(".studentname").text();
		var studentid =$(this).find(".studentid").val();
		var specid =$(this).find(".specialization").attr("id");
		
		$("#myValue").val(accyear+","+hschoolLocation+","+examid+","+classId+","+sectionId+","+admissionno+","+studentname+","+studentid+","+specid);
  		$("#myform").submit();
	});
	
	getClassSpecilization(classId,hschoolLocation);
	
	$("#specilization").change(function(){
		getStudentMarkListSearch();
	});
	
	$("#searchvalue").keyup(function(){
		getStudentMarkListSearch();
	});
});

function getStudentMarkListSearch(){
	var specId=$("#specilization").val();
	var classId=$("#hiddenclassid").val();
	var sectionId=$("#hiddensectionid").val();
	var locationId=$("#hiddenlocid").val();
	var accyear=$("#hiddenaccyear").val();
	var searchtext=$("#searchvalue").val();
	
	var data = {
			"classId" : classId,
			"sectionId" : sectionId,
			"locationId":locationId,
			"accyearId":accyear,
			"specId":specId,
			"searchvalue":searchtext,
	};
	$.ajax({
		type : 'POST',
		url : "examTimetablePath.html?method=getStudentMarkListSearch",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#allstudent tbody').empty();
			if(result.studentList.length>0){
				for(var i=0;i<result.studentList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='rollno'>"+result.studentList[i].studentrollno+"</td>"
							+"<td style='text-align: left;' class='admissionno'>"+result.studentList[i].admissionno+"</td>"
							+"<td style='text-align: left;' class='studentname'>"+result.studentList[i].studentname+"<input type='hidden' class='studentid' value='"+result.studentList[i].studentid+"'/></td>"
							+"<td style='text-align: left;' class='specialization'>"+result.studentList[i].specialization+"</td>"
							+"<td><span class='"+result.studentList[i].status+"'>"+result.studentList[i].status+"</span></td>"
							+"</tr>");
				};
			}else{
				$("#allstudent tbody").append("<tr>"+"<td colspan='5'>No Records Founds</td>" +"</tr>");
			}
			
			$("#allstudent tbody tr").click(function(){
				var examid = $("#hiddenexamid").val();
				var accyear = $("#hiddenaccyear").val();
				var admissionno =$( this ).find(".admissionno").text();
				var studentname =$( this ).find(".studentname").text();
				var studentid =$( this ).find(".studentid").val();
				var specid =$(this).find(".specialization").attr("id");
				
				$("#myValue").val(accyear+","+hschoolLocation+","+examid+","+classId+","+sectionId+","+admissionno+","+studentname+","+studentid+","+specid);
		  		$("#myform").submit();
			});
		}
	});
}

function getClassSpecilization(classId,locationId){
	var data = {
			"classId" : classId,
			"locationId":locationId,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specilization').empty();
			$('#specilization').append('<option value="' + "" + '">'+ "----------Select----------" + '</option>');

			if(result.jsonResponse.length>0){

				for ( var j = 0; j < result.jsonResponse.length; j++) {
					$('#specilization').append('<option value="'
							+ result.jsonResponse[j].spec_Id
							+ '">'
							+ result.jsonResponse[j].spec_Name
							+ '</option>');
				}
			}
		}
	});
}