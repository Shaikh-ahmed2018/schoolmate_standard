$(document).ready(function(){
	
	$("#back1").click(function(){
		window.location.href = "menuslist.html?method=feeDetailsList&historylocId="+$("#historylocId").val()+
		"&historyacyearId="+$("#historyacyearId").val()+"&historystatus="+$("#historystatus").val();
	});
	
	$("#Acyearid").val($("#hacademicyaer").val().trim());
					loadFeeTypeList();
					$("#feeType").val($("#hiddenfeeTypeId").val().trim());
					if($("#hlocationId").val()!=""){
						
						$("#locationname").val($("#hlocationId").val());
					}
					if($("#haccyear").val()!=""){
						
						$("#Acyearid").val($("#haccyear").val());
					}
					
					$("#feeType").change(function(){
						HideError(this);
					});
					$("#locationname").change(function(){
						HideError(this);
					});
					$("#feename").change(function(){
						HideError(this);
					});
					
					 $('#feename').keypress(function (e) {
					        var regex = new RegExp(/^[a-zA-Z0-9-&.(\][)\s]*$/);
					        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
					        if (regex.test(str)) {
					        	 HideError(this);
					            return true;
					           
					        }
					        e.preventDefault();
					        $(".errormessagediv").show();
							$(".validateTips").text("Allows only alphanumerics and -, (, ), [, ], &");
							setTimeout(function() {
				  				$('#errormessagediv').fadeOut();
				  			    },3000);
					        return false;
					     
					    });
					
				
					
				/*	setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv1").hide();
					}, 3000);*/

					var type = $('#concession').val();

					if (type == "Y") {
						$("#yes").attr("checked", true);
					} else if (type == "N") {
						$("#no").attr("checked", true);
					}

					
					
					$("#save").click(function(event)
									{
										var id = $('#feemasterid').val();
										var name = $('#feename').val();
										var description = $('#feedescription').val();
										var feeTypeId=$('#feeType').val();
										var academicYear=$('#Acyearid').val();
										var locationId=$('#locationname').val();

										if (myFunction())
										{
											datalist = {
													"name" : name,
													"feeTypeId" : feeTypeId,
													"description" : description,
													"id" : id,
													"academicYear":academicYear,
													"locationId":locationId,
													"concessiontype" : 'N'
											},
											$.ajax({
												type : "POST",
												url : "addfee.html?method=AddFeeDetailsMaster",
												data : datalist,
												success : function(data)
												{
													var result = $.parseJSON(data);
													if (result.jsonResponse == "Adding Record Progressing...") {
														$(".errormessagediv").hide();
														$(".successmessagediv").show();
														$(".validateTips").text("Adding Record Progressing...");
														setTimeout(function() {
															window.location.href = "menuslist.html?method=feeDetailsList&historylocId="+$("#historylocId").val()+
															"&historyacyearId="+$("#historyacyearId").val()+"&historystatus="+$("#historystatus").val();
														},
														2000);

													}

													if (result.jsonResponse == "Fee Details not Created Successfully") {

														$(".errormessagediv").hide();
														$(".successmessagediv").show();
														$(".validateTips").text("Fee Details not Created.");

														setTimeout(function() {
															window.location.href = "menuslist.html?method=feeDetailsList&historylocId="+$("#historylocId").val()+
															"&historyacyearId="+$("#historyacyearId").val()+"&historystatus="+$("#historystatus").val();
															},
															2000);

													}
													if (result.jsonResponse == "Fee Details Updated Successfully") {
														$(
														".errormessagediv")
														.hide();
														$(
														".successmessagediv")
														.show();
														$(
														".validateTips")
														.text(
														"Updating Record Progressing...");

														setTimeout(
																function() {

																	window.location.href = "menuslist.html?method=feeDetailsList&historylocId="+$("#historylocId").val()+
																	"&historyacyearId="+$("#historyacyearId").val()+"&historystatus="+$("#historystatus").val();

																},
																2000);

													}

													if (result.jsonResponse == "Fee Details not Updated Successfully") {

														$(
														".errormessagediv")
														.hide();
														$(
														".successmessagediv")
														.show();
														$(
														".validateTips")
														.text(
														"Fee Details not Updated Successfully");

														setTimeout(
																function() {

																	window.location.href = "addfee.html?method=addfeedetails";

																},
																3000);

													}

													/*	var result = $.parseJSON(data);



																		window.location.href = "addfee.html?method=addfeedetails&result="+ result.jsonResponse;


																		if(result.jsonResponse=='')
																		{


																		 setTimeout(function(){

																			 window.location.href="menuslist.html?method=examList";

																		 },2000);
																		}*/

												}

											});

										}

									});

					$("#view")
							.click(
									function(event)

									{

										window.location.href = "menuslist.html?method=feeDetailsList";

									});

				});


function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}


function loadFeeTypeList(){
	
	$.ajax({
		type : 'POST',
		url : 'addfee.html?method=getFeeTypeList',
		data:{'locationId':$("#locationId").val()},
		async : false,
		success : function(response) {
			var data = $.parseJSON(response);
			$('#feeType').empty();
			$('#feeType').append('<option value="">'+ "----------Select----------" + '</option>');
			for ( var j = 0; j < data.FeeTypeList.length; j++) {

				$('#feeType').append(
						'<option value="'
						+ data.FeeTypeList[j].feeTypeId
						+ '">'
						+ data.FeeTypeList[j].feeType
						+ '</option>');
			}

		}

	});
}


function myFunction()
{  
	
	var id = $('#feemasterid').val();
	var feeType=$('#feeType').val();
	var feeTypeId=$('#feeTypeId').val();
	var hiddenfeeTypeId=$("#hiddenfeeTypeId").val();
	var name = $('#feename').val();
	var concessiontype = $('.concession[name="concession"]:checked').val();
	
	if($("#Acyearid").val()==""){
		$(".successmessagediv").hide();

		$(".errormessagediv").show();

		$(".validateTips").text("Field Required - Academic Year");
		
		document.getElementById("Acyearid").style.border = "1px solid #AF2C2C";
		document.getElementById("Acyearid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		

		return false;
	}
	else if($("#locationname").val()==""){
		$(".successmessagediv").hide();

		$(".errormessagediv").show();

		$(".validateTips").text("Field Required - School Branch*");
		
		document.getElementById("locationname").style.border = "1px solid #AF2C2C";
		document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
	
		return false;
	}
	else if (feeType == "" || feeType == null || feeType.trim()=="")
	{
		$(".successmessagediv").hide();
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required - Fee Type");
		document.getElementById("feeType").style.border = "1px solid #AF2C2C";
		document.getElementById("feeType").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		return false;
	}
	else if (name == "" || name == null || name.trim()=="")
	{
		$(".successmessagediv").hide();
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required - Fee Name");
		
		document.getElementById("feename").style.border = "1px solid #AF2C2C";
		document.getElementById("feename").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		

		return false;

	}
	var status1=0;
	
	if(feeType == hiddenfeeTypeId){
		status1=0;
	}else{

		datalist1={
				"feeTypeId":feeType,
				"feeType":feeType,
				"locationId":$("#locationname").val(),
				"accyear":$("#Acyearid").val()
		},
		
		
		
		$.ajax({

			type : "POST",
			url : "addfee.html?method=getFeeTypeCount",
			data : datalist1,
			async : false,

			success : function(data)

			{
				var result = $.parseJSON(data);
				
				if (result.message=="inactive")
				{
					$(".successmessagediv").hide();
					$(".errormessagediv").show();
					$(".validateTips").text("This Fee Type  Already Exist !! Make it Active");
					document.getElementById("feeType").style.border = "1px solid #AF2C2C";
					document.getElementById("feeType").style.backgroundColor = "#FFF7F7";
					status1=1;
				}
				/*else if(result.message=="true"){
					$(".successmessagediv").hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Fee Type  Already Exist");
					document.getElementById("feeType").style.border = "1px solid #AF2C2C";
					document.getElementById("feeType").style.backgroundColor = "#FFF7F7";
					status1=1;
				}*/
				
				else
				{ 
					status1=0;
				}
			}
		});
	}
	
	if(status1 == 1){
		return false;
	}
	if (name == "" || name == null || name.trim()=="")
	{
		$(".successmessagediv").hide();
		$(".errormessagediv").show();
		$(".validateTips").text("Enter Fee Name");
		
		document.getElementById("feename").style.border = "1px solid #AF2C2C";
		document.getElementById("feename").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		

		return false;

	}
 
 
 

	/*if (!name.match(/^[a-zA-Z ]*$/))

 
	{

		$(".").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Enter Valid Fee Name");
		document.getElementById("feename").style.border = "1px solid #AF2C2C";
		document.getElementById("feename").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);

		return false;

 
	}*/
else{
 
	var status = false;

	datalist = {
		"name" : name,
		"id" : id,
		"locationId":$("#locationname").val(),
		"accyear":$("#Acyearid").val()
	},

	$.ajax({
		type : "POST",
		url : "addfee.html?method=getnamecount",
		data : datalist,
		async : false,
		success : function(data)
		{
			var result = $.parseJSON(data);
			if (result.message)
			{
				$('#feename').val("");
				$(".successmessagediv").hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Name Already Exists");
				status = false;
			}
			else
			{
				status = true;
			}
		}
	});
	return status;
  }
}
function HideError(pointer) 
{
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}
