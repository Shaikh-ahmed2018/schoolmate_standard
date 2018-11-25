$(document).ready(function(){
	
	$("#locationName").val($("#hiddenlocationName").val());
	
	
	$("#allstudent tbody tr").click(function(){
		var accyear  = $("#accyearh").val();
		var location  = $("#locationh").val();
		var teacherId = $(this).attr('id');
		var status = $(this).attr('class');
		
		window.location.href="teachmap.html?method=SubjectwiseTeacherAdd&accyear="+accyear+"&location="+location+"&teacherId="+teacherId+"&status="+status;;
	});
	
	
	if($("#allstudent tbody tr").length=="0"){
		$("#allstudent tbody").append("<tr>" +
				"<td colspan='4'>No Records Founds</td>" +
				"</tr>");
		}
	
	$("#back1").click(function(){
		window.location.href="teachmap.html?method=AddsubjectTeacherMapping&historyacayear="+$("#historyacayear").val()+"&historylocId="+$("#historylocId").val();
	});
	
});