function noBack() { window.history.forward(); }
$(document).ready(function() {
	

	if($("body").attr("class") !="login-out"){
		getBranch();
		getSchool();
	}
	$('input#user_name,input#user_password').bind('cut copy paste', function (e) {
	    e.preventDefault(); //disable cut,copy,paste
	});
	var pag = window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	
	/*if(pag=="login.html?method=login"){
	window.history.forward();
	}*/
	
	$("#username").text($("#username").text().substring(0,20)+"..");
	var windowheight=($(window).height()-120)+'px';
	
	$("#overlay").css({
		'height':'100%'
		
	});
	
	
	var hPriveliges = $("#hPriveliges").val();
	
	if(hPriveliges=="Y") 
	{
		$("#changeBranching").show();
		$("#globalAcademicYear option[value="+$("#hacademicyaer").val()+"]").attr("selected",true);
		$("#globalAcademicYear").attr("disabled",true);
	
		
	}
	else
	{
		$("#changeBranching").hide();
		$("#globalAcademicYear option[value="+$("#hacademicyaer").val()+"]").attr("selected",true);
		$("#globalAcademicYear").attr("disabled",true);
	
	}
	
	$("#acadamicyeartext").text($("#globalAcademicYear option:selected").text());
	var reg=window.location.href.substr(window.location.href.lastIndexOf('&')+1);
	var splitr=reg.split('=');
	var checker=splitr[0];
	if(checker=='re_enter_password'){
		$(".successmessagediv").show();
		$(".successmessagediv").children("span").text("You Are register. Now You can Login");
		
		$(".successmessagediv").delay(3000).fadeOut("slow");
		setTimeout(function() {
			window.location.href="http://localhost:9090/schoolmate/";
		}, 3000);
		
	}
	$(".successmessagediv").hide();
	$(".loginTips").hide();
	
	
	var UserName = $('#usernamehidden').val();
	if (UserName != 'null') {
		$('#adminname').text(UserName);
	} else {
		window.location.href = "index.jsp";
	}

	$('.ui-x .ui-collapse .ui-close').click(function(e) {
		e.preventDefault();
		$(this).parents(".ui-collapse").css("display", "none");
	});

	$('button.close').click(function(e) {
		$("#myModal").css('display', 'none');
		$("#myModal").attr('class', 'modal fade');
		$("#myModal").attr('aria-hidden', 'true');
		$(".parentDisable").css('display', 'none');
	}); 
	if(hPriveliges=="N"){
		$("select#locationname").find("option").not("option[value="+$("#hschoolLocation").val()+"],option[value='']").remove();
		$("select.locationname").find("option").not("option[value="+$("#hschoolLocation").val()+"],option[value='']").remove();
		$("select#locId").find("option").not("option[value="+$("#hschoolLocation").val()+"],option[value='']").remove();
		$("select#schoolLocationId").find("option").not("option[value="+$("#hschoolLocation").val()+"],option[value='']").remove();
	}

	$("select#locationname,select.locationname,select#locId,select#locationName,select#schoolName,select#locationid,select#location").val($("#hschoolLocation").val());

	if(hPriveliges=="N") 
	{
		$("#Acyearid").find("option").not("option[value="+$("#hacademicyaer").val()+"],option[value='']").remove();
	}

	
	$("#show_per_page").change(function(){
		pagination($(this).val());
	});
	
	
	$("#back").click(function(){
		window.history.back();
	});
	
	numberOfItem=$('#allstudent > tbody tr,.allstudent > tbody tr').length;
	
	$(".numberOfItem").append("   No. of Records "+numberOfItem+".");
	pagination(100);
	
	if($("select#status").val()=="inactive"){
		$(".navbar-right span:nth-child(2)").hide();
		$("span[id^='edit']").hide();
	}
	$("select#status").change(function(){
		if($(this).val()=="inactive"){
			$(".navbar-right span:nth-child(2)").hide();
			$("span[id^='edit']").hide();
		}
		else if($(this).val()=="N"){
			$(".navbar-right span:nth-child(2)").hide();
			$("span[id^='edit']").hide();
		}
		else{
			$(".navbar-right span:nth-child(2)").show();
			$("span[id^='edit']").show();
		}
	});
	
	
	$(".navigation_menu > li > a").click(function(e){
		e.preventDefault();
		$(this).next("i").toggleClass("glyphicon-triangle-bottom");
		$(this).next("i").toggleClass("glyphicon-triangle-top");
		$(this).closest("li").find("ul").slideToggle();
		
		
	});
});

function validateFields() {
	
	
	var UserName = $("#user_name").val();
	var password = $("#user_password").val();
	var domainname = window.location.href;

	if (UserName == '') {

		$(".loginTips").show();
		$(".loginTips").html("Enter Username ");
		
		return false;
	}else
	if (password == '') {
		
		$(".loginTips").show();
		$(".loginTips").html("Enter Password");
		
		return false;
		
	} else if(checkuser(UserName,password,domainname)){
		
		return false;
		
	}
	
	else {
		
		$(".loginTips").hide();
		return true;
	
	}
	
	
}

function  checkuser(username,password,domainname){
	
	
	var flag=false;
	
	var UserDetails={
			
			"UserName" : username,
			"password" : password,
			"domainname" : domainname
		};
		
		$.ajax({
			type : "POST",
			url : "login.html?method=checkValidateuser",
			data : UserDetails,
			async : false,
			success : function(data) {
				
				var response = $.parseJSON(data);
				if(response.Status !="licvalid"){
					$(".loginTips").show();
					$(".loginTips").html(response.Status);
					flag=true;
				}
			}
			
		});
		return flag;
}

function gotoLogout() {
	$(window.location).attr('href', 'login.html?method=logout');
}

function changePassword() {
 
	
	
	var oldPassword = $("#oldpassword").val();
	var newPassword = $("#newPassword").val();
	var confirmPassword = $("#confirmPassword").val();

	if (oldPassword.trim() == "") {

		$("#errormsgdiv").show();
		$(".errormsg").text("Enter Old Password");
		$("#oldpassword").css({'border':'1px solid #e60d0d'});
		setTimeout(function(){
			$("#oldpassword").css({'border':'1px solid #ccc'});
			$('#errormsgdiv').hide();
		},3000);
		return false;
	} else if (newPassword.trim() == "") {

		$("#errormsgdiv").show();
		$(".errormsg").text("Enter New Password");
		
		$("#newPassword").css({'border':'1px solid #e60d0d'});
		setTimeout(function(){
			$("#newPassword").css({'border':'1px solid #ccc'});
			$('#errormsgdiv').hide();
		},3000);
		return false;
	} else if (confirmPassword.trim() == "") {

		$("#errormsgdiv").show();
		$(".errormsg").text("Enter Confirm Password");
		
		$("#confirmPassword").css({'border':'1px solid #e60d0d'});
		setTimeout(function(){
			$("#confirmPassword").css({'border':'1px solid #ccc'});
			$('#errormsgdiv').hide();
		},3000);
		
		return false;
	} else if (newPassword.trim() != confirmPassword.trim()) {
		$("#errormsgdiv").show();
		$(".errormsg").text("New and Confirmed password should be same");
	
		$("#newPassword").css({'border':'1px solid #e60d0d'});
		$("#confirmPassword").css({'border':'1px solid #e60d0d'});
		setTimeout(function(){
			$("#newPassword").css({'border':'1px solid #ccc'});
			$("#confirmPassword").css({'border':'1px solid #ccc'});
			$('#errormsgdiv').hide();
		},3000);
		return false;
	} else if (checkCurrentPassword() == "false") {

		$("#errormsgdiv").show();
		$(".errormsg").text("Enter Correct Old Password");
		
		$("#oldpassword").css({'border':'1px solid #e60d0d'});
		setTimeout(function(){
			$("#oldpassword").css({'border':'1px solid #ccc'});
			$('#errormsgdiv').hide();
		},3000);
		return false;
	} else {

		$("#errormsgdiv").hide();
		
		var passwordDetails = {
			"oldPassword" : oldPassword,
			"newPassword" : newPassword,
			"confirmPassword" : confirmPassword
		};
		$.ajax({
			type : 'POST',
			url : 'login.html?method=changePassword',
			data : passwordDetails,
			async : false,
			success : function(response) {

				var data = $.parseJSON(response);
                
				if (data.status == "true") {
					$(".successmessagediv1").show();
					$("#sucessmessage").text("Password changed successfully");

					setTimeout(function() {
						$(window.location).attr('href', 'login.html?method=logout');
							},2000);
				}
			}
		});
	}
}

function checkCurrentPassword() {

	var status1 = null;

	var currentPassword = {
		"oldPassword" : $("#oldpassword").val()
	};
	$.ajax({
		type : 'POST',
		url : 'login.html?method=checkCurrentPassword',
		data : currentPassword,
		async : false,
		success : function(response) {

			var data = $.parseJSON(response);

			status1 = data.status;

		}
	});

	return status1;
}

function removeMessage() {

	location.reload();
}

function opendialog(type) {
	if (type == 'admin') {
		$("#myModal").css('display', 'block');
		$("#myModal").attr('class', 'modal fade in');
		$("#myModal").attr('aria-hidden', 'false');
		$(".parentDisable").css('display', 'block');
	}
}

function closeDialog() {
	location.reload();
}

function checkLoginAuthenticationCase() {
	var uname = $("#user_name").val();
	var pword = $("#user_password").val();
	var logindetails = {
		"uname" : uname,
		"pword" : pword
	};

	$.ajax({
		type : "POST",
		url : "login.html?method=userValidCase",
		data : logindetails,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			if (result.status) {

				loginusercase = false;

			} else {
				loginusercase = true;
				$("#error").text("Incorrect User Name or Password");
			}
		}
	});

	return loginusercase;

}

//fuctions to get values
/*function getAcademicYear() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAcademicYear",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#globalAcademicYear").html("");
			$("#globalAcademicYear").append(
					'<option value="' + "" + '">' + "" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$("#globalAcademicYear").append(
						'<option value="'
								+ result.jsonResponse[j].academicYearId + '">'
								+ result.jsonResponse[j].academicYear
								+ '</option>');
			}

		}
	});

}*/
function getSchool() {
	$.ajax({
		type : 'POST',
		url : "menuslist.html?method=getSchool",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
				$("#school").val(result.jsonResponse+" ("+$("#branch option:selected").text()+")");
				$("#schoolText").text(result.jsonResponse+" ("+$("#branch option:selected").text()+")");
		}
	});

}
function getBranch(){
	$.ajax({
		type : 'POST',
		url : "menuslist.html?method=getBranch",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			for(var i=0;i<result.jsonResponse.length;i++){
				$("#branch").append("<option value='"+result.jsonResponse[i].locationId+"'>"+result.jsonResponse[i].locationName+"</option>");
			}
			$("#branch").val($("#hschoolLocation").val());
		}
	});
}
function changeBranch(){
	$.ajax({
		type : 'POST',
		url : "menuslist.html?method=changeBranch",
		data:{"branch":$("#branch").val()},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			if(result.status){
				$("#hschoolLocation").val($("#branch").val());
				location.reload(true);
			}
			
				
		}
	});
}

function pagination(list) {
	
	var show_per_page = list;
    var number_of_items = $('#allstudent > tbody > tr,.allstudent > tbody > tr').length;
   
    var number_of_pages = Math.ceil(number_of_items / show_per_page);
    
    $('.pagination').empty();
    $('.pagination').append('<div class=controls></div><input id=current_page type=hidden><input id=show_per_page type=hidden>');
    $('#current_page').val(0);
    $('#show_per_page').val(show_per_page);

    var navigation_html = '<a class="prev" onclick="previous()">Prev</a>';
    var current_link = 0;
    while (number_of_pages > current_link) {
        navigation_html += '<a class="page" onclick="go_to_page(' + current_link + ')" longdesc="' + current_link + '">' + (current_link + 1) + '</a>';
    	 current_link++;
    }
    navigation_html += '&nbsp;&nbsp;&nbsp;<a class="next" onclick="next()">Next</a>';

    $('.controls').html(navigation_html);
    $('.controls .page:first').addClass('active');
    $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();
   
    $('#allstudent > tbody > tr,.allstudent > tbody > tr').css('display', 'none');
    $('#allstudent > tbody > tr,.allstudent > tbody > tr').slice(0, show_per_page).css('display', 'table-row');

}

function go_to_page(page_num) {
    var show_per_page = parseInt($('#show_per_page').val(), 0);

    start_from = page_num * show_per_page;

    end_on = start_from + show_per_page;
    $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();	
    $('#allstudent tbody tr,.allstudent tbody tr').css('display', 'none').slice(start_from, end_on).css('display', 'table-row');

    $('.page[longdesc=' + page_num + ']').addClass('active').siblings('.active').removeClass('active');

    $('#current_page').val(page_num);
}



function previous() {

    new_page = parseInt($('#current_page').val(), 0) - 1;
    //if there is an item before the current active link run the function
    if ($('.active').prev('.page').length == true) {
        go_to_page(new_page);
    }

}

function next() {
    new_page = parseInt($('#current_page').val(), 0) + 1;
    //if there is an item after the current active link run the function
    if ($('.active').next('.page').length == true) {
        go_to_page(new_page);
    }
}
