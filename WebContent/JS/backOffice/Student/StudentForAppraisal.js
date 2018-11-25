$(document).ready(function() {
	
	
	$("#admissionform").click(function(){
		
		window.location.href="menuslist.html?method=getStudentConfidentialReport";

	});
	
	
	$("#allstudent tbody tr").click(function(){
		
		window.location.href="menuslist.html?method=studentAppraisalPage";

	});
});