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

	bValid = bValid && checkLengthText($('#occupation'), "Ocuupation Name", 0, 30);

	bValid = bValid
			&& checkRegexpText($('#occupation'), /^[a-zA-Z\s]+$/g,
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
					
			$("#back1").click(function(){
				window.location.href = "menuslist.html?method=occupationDetails&historysearchname="+$("#hiddensearchname").val()+"&historystatus="+$("#hiddenstatus").val();
			});
				
			 $("#saveReligionId").click(function(){
						$("#saveReligionId").hide();
				              	$(".successmessagediv").hide();
				              	$(".errormessagediv").hide();
						
						
						var occupation = $("#occupation").val().trim();
						var occupationId = $("#occupationId").val();
						var hiddenoccupation = $("#hiddenoccupation").val();
						
						
						if (occupation.trim() == "" || occupation.trim()==null) {
							
							$(".errormessagediv").show();
							
							$(".validateTips").text("Field Required - Occupation");
							$("#occupation").focus();
							document.getElementById("occupation").style.border = "1px solid #AF2C2C";
							document.getElementById("occupation").style.backgroundColor = "#FFF7F7";
							$("#saveReligionId").css({
								"display":"inline"
							});
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							
							return false;
						}
						/*else if(!occupation.match(/^[a-zA-Z-,]*$/)) {
							
							$(".errormessagediv").show();
							
							$(".validateTips").text("Occupation name should be characters!");
							$("#occupation").focus();
							$("#saveReligionId").css({
								"display":"inline"
							});
							document.getElementById("occupation").style.border = "1px solid #AF2C2C";
							document.getElementById("occupation").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							
							return false;
							
						}*/
						else{
							datalist = {
									"occupation" : occupation, 
									"occupationId":occupationId, 
									"hiddenoccupation":hiddenoccupation
							};
							$.ajax({
								type : 'POST',
								url : "religionCastCasteCategory.html?method=insertOccupation",
								data : datalist,
								async : false,
								success : function(data) {
									var result = $.parseJSON(data);
									////alert("response"+result.jsonResponse);

									if(result.jsonResponse=="Occupation Added Successfully"){
										$(".errormessagediv").hide();
										$(".successmessagediv").show();
										$(".validateTips").text("Adding Occupation progressing...");
										setTimeout(function(){
											window.location.href = "menuslist.html?method=occupationDetails&historysearchname="+$("#hiddensearchname").val()+"&historystatus="+$("#hiddenstatus").val();
										},3000);
									}

									if(result.jsonResponse=="Occupation Updated Successfully"){
										$(".errormessagediv").hide();
										$(".successmessagediv").show();
										$(".validateTips").text("Update Occupation progressing...");
										setTimeout(function(){
											window.location.href = "menuslist.html?method=occupationDetails&historysearchname="+$("#hiddensearchname").val()+"&historystatus="+$("#hiddenstatus").val();
										},3000);
									}	
									if(result.jsonResponse=="Occupation Not Updated Successfully"){
										$("#saveReligionId").css({
											"display":"inline"
										});
										$(".errormessagediv").show();
										$(".successmessagediv").hide();
										$(".errormessagediv .validateTips").text("Failed to Update Occupation!!");
										setTimeout(function(){
											window.location.href = "menuslist.html?method=occupationDetails&historysearchname="+$("#hiddensearchname").val()+"&historystatus="+$("#hiddenstatus").val();
										},3000);
									}	
									if(result.jsonResponse=="Occupation Already Exist"){
										$("#saveReligionId").css({
											"display":"inline"
										});
										$(".errormessagediv").show();
										$(".successmessagediv").hide();
										$(".errormessagediv .validateTips").text("Occupation Already Exist.");

										$("#occupation").focus();
										document.getElementById("occupation").style.border = "1px solid #AF2C2C";
										document.getElementById("occupation").style.backgroundColor = "#FFF7F7";
									}
									if(result.jsonResponse=="inactive"){
										$("#saveReligionId").css({
											"display":"inline"
										});
										$(".errormessagediv").show();
										$(".successmessagediv").hide();
										$(".errormessagediv .validateTips").text("Occupation Already Exist !! Make it Active");
										$("#occupation").focus();
										document.getElementById("occupation").style.border = "1px solid #AF2C2C";
										document.getElementById("occupation").style.backgroundColor = "#FFF7F7";
									}
								}
							});
						}
					});
					
					
				});

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}