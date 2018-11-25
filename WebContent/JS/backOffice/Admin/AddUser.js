$(document).ready(function(){
	
	$("#location").val($("#hiddenloc").val());
	$("#roleName").val($("#hiddenrole").val());
	
	$("#mobileno").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number.");
			document.getElementById("mobileno").style.border = "1px solid #AF2C2C";
			document.getElementById("mobileno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
	});
	
	$("#usernames").keypress(function(e){
		if ( e.which == 32) {
			$(".errormessagediv").show();
			$(".validateTips").text("Space is not allowed");
			document.getElementById("usernames").style.border = "1px solid #AF2C2C";
			document.getElementById("usernames").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
	});
	
	$("#pwd").keypress(function(e){
		if ( e.which == 32) {
			$(".errormessagediv").show();
			$(".validateTips").text("Space is not allowed");
			document.getElementById("pwd").style.border = "1px solid #AF2C2C";
			document.getElementById("pwd").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
	});
	$("#cnfpwd").keypress(function(e){
		if ( e.which == 32) {
			$(".errormessagediv").show();
			$(".validateTips").text("Space is not allowed");
			document.getElementById("cnfpwd").style.border = "1px solid #AF2C2C";
			document.getElementById("cnfpwd").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
	});
	
	$("#back1").click(function(){
		window.location.href="menuslist.html?method=getUsersettings&historystatus="+$("#historystatus").val();
	});
	
	pageurl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	
	$("#save").click(function(){
		 
		 var roleName = $("#roleName").val();
		 var location = $("#location").val();
		 var username = $("#usernames").val();
		 var password = $("#pwd").val();
		 var confirmpassword = $("#cnfpwd").val();
		 var mobileno = $("#mobileno").val();
		 var email = $("#email").val();
		 
		if(roleName == ""){
			document.getElementById("roleName").style.border = "1px solid #AF2C2C";
			document.getElementById("roleName").style.backgroundColor = "#FFF7F7";
      	    $(".errormessagediv").show();
     		    $(".validateTips").text("Field Required - Role");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    },3000);
     		    return false;
			 
		}else if(location.trim() == ""){
			document.getElementById("location").style.border = "1px solid #AF2C2C";
			document.getElementById("location").style.backgroundColor = "#FFF7F7";
      	    $(".errormessagediv").show();
     		    $(".validateTips").text("Field Required - School");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    },3000);
     		   return false;
		}else if(username.trim() == ""){
			document.getElementById("usernames").style.border = "1px solid #AF2C2C";
			document.getElementById("usernames").style.backgroundColor = "#FFF7F7";
      	    $(".errormessagediv").show();
     		    $(".validateTips").text("Field Required - UserName");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    },3000);
     		   return false;
		}
		else if(username.length <4){
			document.getElementById("usernames").style.border = "1px solid #AF2C2C";
			document.getElementById("usernames").style.backgroundColor = "#FFF7F7";
      	    $(".errormessagediv").show();
     		    $(".validateTips").text("Field Required - UserName");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    },3000);
     		   return false;
		}
		else if(pageurl=="userManagement.html?method=addUsersettings" && validateusername()==1){
			document.getElementById("usernames").style.border = "1px solid #AF2C2C";
			document.getElementById("usernames").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").show();
			$(".validateTips").text("UserName Already Exist !!");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(pageurl=="userManagement.html?method=addUsersettings" && validateusername()==10){
			document.getElementById("usernames").style.border = "1px solid #AF2C2C";
			document.getElementById("usernames").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").show();
			$(".validateTips").text("UserName Already Exist !! Make it Active");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(pageurl!="userManagement.html?method=addUsersettings"&& ($("#usernames").val()!=$("#hiddenusername").val()) && validateusername()==1){
			document.getElementById("usernames").style.border = "1px solid #AF2C2C";
			document.getElementById("usernames").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").show();
			$(".validateTips").text("User Name record Already Exist !!");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(pageurl!="userManagement.html?method=addUsersettings" && ($("#usernames").val()!=$("#hiddenusername").val()) && validateusername()==10){
			document.getElementById("usernames").style.border = "1px solid #AF2C2C";
			document.getElementById("usernames").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").show();
			$(".validateTips").text("UserName Already Exist !! Make it Active");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(password.trim() == ""){
			document.getElementById("pwd").style.border = "1px solid #AF2C2C";
			document.getElementById("pwd").style.backgroundColor = "#FFF7F7";
      	    $(".errormessagediv").show();
     		    $(".validateTips").text("Field Required - Password");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    },3000);
		}
		else if(password.trim().length <6){
			document.getElementById("pwd").style.border = "1px solid #AF2C2C";
			document.getElementById("pwd").style.backgroundColor = "#FFF7F7";
      	    $(".errormessagediv").show();
     		    $(".validateTips").text("Password should Contain Atleast 6 Characters");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    },3000);
		}
		else if(confirmpassword.trim() == ""){
			document.getElementById("cnfpwd").style.border = "1px solid #AF2C2C";
			document.getElementById("cnfpwd").style.backgroundColor = "#FFF7F7";
      	    $(".errormessagediv").show();
     		    $(".validateTips").text("Field Required - Confirm Password");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    },3000);
		}
		else if(confirmpassword != password){
			document.getElementById("cnfpwd").style.border = "1px solid #AF2C2C";
			document.getElementById("cnfpwd").style.backgroundColor = "#FFF7F7";
      	    $(".errormessagediv").show();
     		    $(".validateTips").text("Password and Confirm Password should be same");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    },3000);
		}
		else if(mobileno.trim() == ""){
			document.getElementById("mobileno").style.border = "1px solid #AF2C2C";
			document.getElementById("mobileno").style.backgroundColor = "#FFF7F7";
      	    $(".errormessagediv").show();
     		    $(".validateTips").text("Field Required - Mobile No.");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    },3000);
		}else if(email.trim() == ""){
			document.getElementById("email").style.border = "1px solid #AF2C2C";
			document.getElementById("email").style.backgroundColor = "#FFF7F7";
      	    $(".errormessagediv").show();
     		    $(".validateTips").text("Field Required - Email");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    },3000);
		}
		else if (!email.match(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/))
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Enter Valid Email");
			document.getElementById("email").style.border = "1px solid #AF2C2C";
			document.getElementById("email").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
		}
		else{
			adduser();
		}
	});
	
});

function adduser(){
	 
	$.ajax({
		type : 'POST',
		url : "userManagement.html?method=insertUserDetails",
		data : {
				"roleName" : $("#roleName").val(),
				"location" : $("#location").val(),
				"username" : $("#usernames").val(),
				"password" : $("#pwd").val(),
				"confirmpassword" : $("#cnfpwd").val(),
				"mobileno" : $("#mobileno").val(),
				"email" : $("#email").val(),
				"hiddenuserid" : $("#hiddencustId").val(),
				"auserid" : $("#auserid").val()
		},
		async:false,
		success:function(data){
			var result = $.parseJSON(data);
			if(result.status == "success"){
				$(".successmessagediv").show();
				$(".successmessage").text("Adding Record progressing...");
				setTimeout(function(){
					window.location.href="menuslist.html?method=getUsersettings";
				},3000);
			}else if(result.status == "fail"){
				$(".errormessagediv").show();
				$(".validateTips").text("Fail to add record...");
				setTimeout(function(){
					$(".errormessagediv").hide();
				},3000);
			}
			else if(result.status == "update"){
				$(".successmessagediv").show();
				$(".successmessage").text("Update Record progressing...");
				setTimeout(function(){
					window.location.href="menuslist.html?method=getUsersettings";
				},3000);
			}
			else if(result.status == "updatefail"){
				$(".errormessagediv").show();
				$(".validateTips").text("Fail to update record...");
				setTimeout(function(){
					$(".errormessagediv").hide();
				},3000);
			}
		}
	});
}
function showError(id,msg){
	$(".errormessagediv").show();
	$(".validateTips").text(msg);
	id.css({
		    "border" : "1px solid rgb(175, 44, 44)",
		    "background-color" : "rgb(255, 247, 247)"
	});
	setTimeout(function(){
		$(".errormessagediv").hide();
	},5000);
}

function validateusername() {
	var username = $('#usernames').val();
	var checkusername = {
			"username" : username,
	};
	var value = 0;

	$.ajax({
		type : "POST",
		url : "userManagement.html?method=validateusername",
		data : checkusername,
		async : false,
		success : function(data) {

			var result = $.parseJSON(data);
			
			if (result.status=="inactive") {
				value = 10;
			}
			else if (result.status=="true") {
				value = 1;
			} 
			else {
				value = 0;
			}
		}
	});
	return value;
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}