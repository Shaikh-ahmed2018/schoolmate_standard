function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}


function callAjax(urlWithMethod, dataToBeSend) {
	
	$.ajax({
         
		url : urlWithMethod,
		data : dataToBeSend,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			jsonResult = result.status;
			
			 setTimeout(function(){
				   
				 location.reload();
			 
			 },5000);
	
		}
	});
	return jsonResult;
}

function insertValidate() {
	
	$(".errormessagediv").hide();
	
	tips = $(".validateTips");
	var bValid = true;

	bValid = bValid && checkLengthText($('#streamName'), "Stream Name", 0, 30);

	bValid = bValid
			&& checkRegexpText($('#streamName'), /^[a-zA-Z\s]+$/g,
					"Name should be Alphabet");

	if (!bValid) {
		$(".errormessagediv").show();
	} else {

		$(".errormessagediv").hide();
	}

	return bValid;
		
}

$(document).ready(function() {

	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);

	 $("#main_religion").val($("#hmain_religion").val()); 
 
	$("#main_religion").change(function(){
		if($("#hmain_religion").val()!=$("#main_religion").val()){
			validateCaste();
		}
	});
	
	$("#back1").click(function(){
		window.location.href = "menuslist.html?method=casteDetails&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
	});
	 
	$("#saveReligionId").click(function(){

		$(".successmessagediv").hide();
		$(".errormessagediv").hide();

		var religion = $("#religionNameId").val();
		var religionId = $("#relgnId").val();
		var main_religion =$("#main_religion").val();
		var hiddencaste =$("#hiddencaste").val();

		if (main_religion == "" || main_religion==null) {

			$(".errormessagediv").show();

			$(".validateTips").text("Field Required - Religion");
			$("#religionNameId").focus();
			document.getElementById("main_religion").style.border = "1px solid #FF0000";
			document.getElementById("main_religion").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}

		else if (religion.trim() == "" || religion.trim()==null) {

			$(".errormessagediv").show();

			$(".validateTips").text("Field Required - Caste");
			$("#religionNameId").focus();
			document.getElementById("religionNameId").style.border = "1px solid #FF0000";
			document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}else if(!religion.match(/^[a-zA-Z0-9-&.(\][)\s]*$/)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Caste name should be characters!");
			$("#religionNameId").focus();
			document.getElementById("religionNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false; 
		}else{

			datalist = {
					"religion" : religion,
					"religionId":religionId,
					"main_religion" : main_religion,
					"hiddencaste":hiddencaste
			};
			$.ajax({
				type : 'POST',
				url : "religionCastCasteCategory.html?method=insertCaste",
				data : datalist,
				async : false,
				success : function(data) {

					var result = $.parseJSON(data);

					if(result.jsonResponse=="Caste Added Successfully"){
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Add Caste Details progressing...");
						setTimeout(function(){
							window.location.href = "menuslist.html?method=casteDetails&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
						},3000);
					}

					if(result.jsonResponse=="Caste Updated Successfully"){
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Update Caste Details progressing...");
						setTimeout(function(){
							window.location.href = "menuslist.html?method=casteDetails&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
						},3000);
					}	
					if(result.jsonResponse=="Caste Already Exist"){
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Caste Already Exist");
						$("#religionNameId").focus();
						document.getElementById("religionNameId").style.border = "1px solid #AF2C2C";
						document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
					}	
					if(result.jsonResponse=="inactive"){
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Caste Already Exist !! Make it Active");
						$("#religionNameId").focus();
						document.getElementById("religionNameId").style.border = "1px solid #AF2C2C";
						document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
					}
				}
			});
		}
	});
});

function validateCaste(){
	var streamname_validate=0;
	  
	var religionId=$("#main_religion").val();
 	var casteId=$("#religionNameId").val();
 	
	var casteObject = {
		"religionId":religionId,
		"casteId" : casteId
	};

$.ajax({
	type : "POST",
	url : 'religionCastCasteCategory.html?method=validateCaste',
	data : casteObject,
	async : false,
	success : function(data) {
		var result = $.parseJSON(data);
		 
		if (result.status==1) 
	    {
			$("#main_religion").val($("#hmain_religion").val());
			
			$(".errormessagediv").show();
			$(".successmessagediv").hide();
			 $(".validateTips").text("Caste Name already exist in this Religion!!");
			 setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 4000);
		}
		else if (result.status==10) 
	    {
			$("#main_religion").val($("#hmain_religion").val());
			
			$(".errormessagediv").show();
			$(".successmessagediv").hide();
			 $(".validateTips").text("Caste Name already exist in this Religion!! Make it Active");
			 setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 4000);
		} 
	}
});
return streamname_validate;
}


function HideError(pointer) 
{
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}