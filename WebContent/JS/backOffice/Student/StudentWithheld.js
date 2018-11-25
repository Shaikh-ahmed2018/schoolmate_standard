$(document).ready(function() {
	
		$("#allstudent tbody tr").click(function(){
			
			window.location.href="menuslist.html?method=studentWithheld";

		});
		
		
$("#back").click(function(){
			
			window.location.href="menuslist.html?method=studentWithheldList";

		});


$("#save").click(function(){
	
	window.location.href="menuslist.html?method=studentWithheldList";

});
});