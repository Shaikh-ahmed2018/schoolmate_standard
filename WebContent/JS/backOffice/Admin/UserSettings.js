$(document).ready(function(){
	
	$("#modify").click(function(){
		userid = $(".select:checked").val();
		window.location.href = "userManagement.html?method=editUsersettings&userid="+userid;
	});
	
	
});