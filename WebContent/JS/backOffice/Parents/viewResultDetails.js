$(document).ready(function(){
	
	//viewExamdetails();
	
	$(".collapseOne").click(function(){
		$("#collapseOne").slidetoggle();
	});
	
	$("#back").click(function(){
		window.location.href = "parentMenu.html?method=examdetails";
	});
	
});