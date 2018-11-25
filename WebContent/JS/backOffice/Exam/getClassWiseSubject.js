$(document).ready(function(){
	
	$("#allstudent tbody tr").click(function(){
		var classId = $("#hiddenclassid").val();
		var sectionId = $("#hiddensectionid").val();
		var examid = $("#hiddenexamid").val();
		var accyear = $("#hiddenaccyid").val();
		var subid = $(this).find(".subId").attr("id"); 
		var hschoolLocation=$("#hiddenlocid").val();
		var spec=$("#hiddenspec").val();
		window.location.href = "examCreationPath.html?method=savesubWiseMarksDetails&classId="+classId+
		"&sectionId="+sectionId+"&examid="+examid+"&accyear="+accyear+"&subid="+subid+
		"&hschoolLocation="+hschoolLocation+"&spec="+spec+"&historyacayear="+$("#historyacayear").val()
		+"&historylocation="+$("#historylocation").val();
		
	});
	
	$("#back1").click(function(){
		var examid = $("#hiddenexamid").val();
		var classId = $("#hiddenclassid").val();
		var accyear = $("#hiddenaccyid").val();
		var hschoolLocation=$("#hiddenlocid").val();
		var spec=$("#hiddenspec").val();
		window.location.href="examCreationPath.html?method=getSubjectClass&accyear="+accyear+"&examid="+examid+
		"&hschoolLocation="+hschoolLocation+"&classid="+classId+"&specId="+spec+"&historyacayear="+$("#historyacayear").val()
		+"&historylocation="+$("#historylocation").val();
	});
	
	
	
});