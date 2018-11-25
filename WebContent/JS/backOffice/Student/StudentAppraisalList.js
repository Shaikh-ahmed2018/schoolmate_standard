$(document).ready(function() {
	
	
	$("#addappraisal").click(function(){
		
		window.location.href="menuslist.html?method=findStudentForAppraisal";

	});
	
	
	$("#allstudent tbody tr").click(function(){
		
		window.location.href="menuslist.html?method=studentAppraisalPage";

	});
});
	