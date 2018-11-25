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

	bValid = bValid && checkLengthText($('#religionNameId'), "Religion Name", 0, 30);

	bValid = bValid
			&& checkRegexpText($('#religionNameId'), /^[a-zA-Z\s]+$/g,
					"Name should be Alphabet");

	if (!bValid) {
		$(".errormessagediv").show();
	} else {

		$(".errormessagediv").hide();
	}

	return bValid;
		
}

$(document).ready(function() {

	/*					$('.errormessagediv').hide();
	 */					
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);

	$("#back1").click(function(){
		window.location.href = "menuslist.html?method=religionDetails&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
	});

	$("#saveReligionId").click(function(){

		$(".successmessagediv").hide();
		$(".errormessagediv").hide();


		var religion = $("#religionNameId").val().trim();
		var religionId = $("#relgnId").val();
		var hiddenreligion = $("#hiddenreligion").val();

		if (religion.trim() == "" || religion.trim()==null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Religion Name");
			$("#religionNameId").focus();
			document.getElementById("religionNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);

			return false;
		}else if(!religion.match(/^[a-zA-Z0-9-&.(\][)\s]*$/)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Invalid Religion Name");
			$("#religionNameId").focus();
			document.getElementById("religionNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false; 
		}
		else{
			datalist = {
					"religion" : religion, 
					"religionId":religionId, 
					"hiddenreligion":hiddenreligion
			};
			$.ajax({
				type : 'POST',
				url : "religionCastCasteCategory.html?method=insertReligion",
				data : datalist,
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);

					if(result.jsonResponse=="Religion Added Successfully"){
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Adding Religion Progressing...");
						setTimeout(function(){
							window.location.href = "menuslist.html?method=religionDetails&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
						},3000);
					}

					if(result.jsonResponse=="Religion Updated Successfully"){
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Update Religion Progressing...");
						setTimeout(function(){
							window.location.href = "menuslist.html?method=religionDetails&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
						},3000);
					}	

					if(result.jsonResponse=="Religion Already Exist"){

						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Religion Already Exist");

						$("#religionNameId").focus();
						document.getElementById("religionNameId").style.border = "1px solid #AF2C2C";
						document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
					}
					if(result.jsonResponse=="inactive"){

						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("This Religion Already Exist !! Make it Active");

						$("#religionNameId").focus();
						document.getElementById("religionNameId").style.border = "1px solid #AF2C2C";
						document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
					}
				}
			});
		}
	});
});




function HideError() 
{
	
document.getElementById("religionNameId").style.border = "1px solid #ccc";
document.getElementById("religionNameId").style.backgroundColor = "#fff";

}