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
					"Name should be alphabet");

	if (!bValid) {
		$(".errormessagediv").show();
	} else {

		$(".errormessagediv").hide();
	}

	return bValid;
		
}

$(document).ready(function() 
		{
			$("#locationname").change(function(){
				
				if($("#schoolId").val() != $("#locationname").val()){
					validateDuplicateLocation();
				}
				
			});
			 
			$("#back1").click(function(){
				window.location.href = "menuslist.html?method=streamList&LocId="+$("#hiddenlocId").val()+"&status="+$("#hiddenstatus").val();
			});
	
			if($("#schoolId").val()!=""){
				$("#locationname").prop('disabled',true);
				$("#locationname").val($("#schoolId").val());
				$("#locationname").find("option").not("option[value*=LOC]").remove();
			}
			var pageurl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
		
			setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
				
					$("#savestreamid").click(function(){
						
				     $(".successmessagediv").hide();
				     $(".errormessagediv").hide();
						
				     
						var streamname = $("#streamName").val().trim();
						var description = $("#description").val().trim();
						var streamId = $("#streamId").val().trim();
						
						if($("#locationname").val()=="" || $("#locationname").val()==null){
							
							$(".errormessagediv").show();
							$(".validateTips").text("Select branch");
							$("#locationname").focus();
							document.getElementById("locationname").style.border = "1px solid #AF2C2C";
							document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							
							return false;
						}
						else if (streamname.trim() == "" || streamname==null) {
							
							$(".errormessagediv").show();
							$(".validateTips").text("Field Required - Stream Name");
							$("#streamName").focus();
							document.getElementById("streamName").style.border = "1px solid #AF2C2C";
							document.getElementById("streamName").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							
							return false;
						}
						/*else if(!streamname.match(/^[a-zA-Z0-9\s]*$/)){
							
							$(".errormessagediv").show();
							$(".validateTips").text("Stream Name should contain only Characters");
							$("#streamName").focus();
							document.getElementById("streamName").style.border = "1px solid #AF2C2C";
							document.getElementById("streamName").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							return false;
							
						}*/
						else if(streamname.trim().length < 4 ){
							
							$(".errormessagediv").show();
							$(".validateTips").text("Stream should be atleast 4 characters");
							$("#streamName").focus();
							document.getElementById("streamName").style.border = "1px solid #AF2C2C";
							document.getElementById("streamName").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							
							return false;
							
						}
						else if(streamname.trim().length > 50){
							
							$(".errormessagediv").show();
							$(".validateTips").text("Stream name too long!!!");
							$("#streamName").focus();
							document.getElementById("streamName").style.border = "1px solid #AF2C2C";
							document.getElementById("streamName").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							
							return false;
							
						}
						else if(pageurl=="menuslist.html?method=addStream" && validateStreamName() == 10){
                            $(".errormessagediv").show();
							$(".validateTips").text("Stream already exist in selected branch!!! Make it active");
							return false;
					}
						else if(pageurl=="menuslist.html?method=addStream" && validateStreamName() == 1){
	                            $(".errormessagediv").show();
								$(".validateTips").text("Stream already exists in selected branch");
								return false;
						}
						else if(pageurl != "menuslist.html?method=addStream" && ($("#schoolId").val() == $("#locationname").val()) && (validateStreamName() == 10)){	
                            $(".errormessagediv").show();
							$(".validateTips").text("Stream already exist in selected branch!!! Make it active");
							return false;
						}
						else if(pageurl != "menuslist.html?method=addStream" && ($("#schoolId").val() == $("#locationname").val()) && (validateStreamName() == 1)){	
							 
                            $(".errormessagediv").show();
							$(".validateTips").text("Stream already exists");
							return false;
						}
						else{ 
								datalist = {
										"locationname":$("#locationname").val(),
										"streamId" : streamId,
										"streamname" : streamname, 
										"description" :description
									};
								
								$.ajax({
									type : 'POST',
									url : "streamDetails.html?method=insertStreamDetailsAction",
									data : datalist,
									async : false,
									success : function(data) {
										
										var result = $.parseJSON(data);
										
										if(result.jsonResponse == "Added Successfully"){
											$(".errormessagediv").hide();
											$(".successmessagediv").show();
											 $(".validateTips").text("Adding Stream Progressing...");
											 setTimeout(function() {
													$('.successmessagediv').fadeOut();
												}, 3000);
											 setTimeout(function(){
												 window.location.href = "menuslist.html?method=streamList&LocId="+$("#hiddenlocId").val()+"&status="+$("#hiddenstatus").val();
											 },3000);
										}
											
										if(result.jsonResponse=="Stream Update progressing"){
											$(".errormessagediv").hide();
											$(".successmessagediv").show();
											 $(".validateTips").text("Updating Stream Progressing...");
											 setTimeout(function() {
													$('.successmessagediv').fadeOut();
												}, 3000);
											
											 setTimeout(function(){
												 window.location.href = "menuslist.html?method=streamList&LocId="+$("#hiddenlocId").val()+"&status="+$("#hiddenstatus").val();
											 },3000);
										}		
									}

								});
						 }
						
					});
				});


function validateStreamName(){
	
	var streamname_validate=0;
	
		var streamId = $("#streamId").val().trim();
		var locationId=$("#locationname").val();
	 	var streamname=$("#streamName").val().trim();
		var streamObject = {
			"locationId":locationId,"streamname" : streamname, "streamId" : streamId
		};
	
	
	$.ajax({

		type : "GET",
		url : 'streamDetails.html?method=validateStreamName',
		data : streamObject,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			
			if (result.status=="inactive") 
		    {
				streamname_validate = 10;
			}
			else if (result.status=="true") 
		    {
				streamname_validate = 1;
			}
			else 
			{
				streamname_validate = 0;
			}
		
		}
	});
	
	return streamname_validate;
	
}

function validateDuplicateLocation(){
	
	   var streamname_validate=0;
		 
		var locationId=$("#locationname").val();
	 	var streamname=$("#streamName").val().trim();
		var streamObject = {
			"locationId":locationId,
			"streamname" : streamname, 
		};
	
	$.ajax({

		type : "GET",
		url : 'streamDetails.html?method=validateDuplicateLocation',
		data : streamObject,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			
			 
			if (result.status=="true") 
		    {
				$("#locationname").val($("#schoolId").val());
				$(".errormessagediv").show();
				$(".successmessagediv").hide();
				 $(".validateTips").text("Stream already exist in this branch!!");
				 setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 4000);
			}
			else if (result.status=="inactive") 
		    {
				$("#locationname").val($("#schoolId").val());
				$(".errormessagediv").show();
				$(".successmessagediv").hide();
				 $(".validateTips").text("Stream already exist in this branch!! Make it active");
				 setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 4000);
			} 
		}
	});
	return streamname_validate;
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}