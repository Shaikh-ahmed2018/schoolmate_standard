$(document).ready(function(){
	getConcessionTypes()
	
	$("#resetbtn").click(function(){
		$("#status").val("Y");
		$("#searchvalue").val("");
		getConcessionDetails();
	});
	
	if($('#concessionId').val()!=undefined && $('#concessionId').val()!=""){
			$('input[name="concession"]').each(function(){
				if($(this).val()==$("#hconcession").val()){
					$(this).attr('checked', true)
				}
			});
		for(var i=0; i<$("#hisApplicable").val().split(",").length; i++){
			$('input[type="checkbox"]').each(function(){
				if($(this).val()==$("#hisApplicable").val().split(",")[i]){
					$(this).attr('checked', true)
				}
			});
		}
	}
			if($("#hiddenlocId").val()!="" && $("#hiddenlocId").val()!=undefined){
				 $(".locationname").prop('disabled',true);
				 $("#locationname").val($("#hiddenlocId").val());
			}
	
			 if($("#currentstatus").val()!=""){
	      		 if($("#currentstatus").val()=="N"){ 
	      			$("#status").val("N");
	      			$("#editfee").hide();
	      			$("#inactive").text("Active");
	      			getConcessionDetails();
	      		}
	      		else{
	      			$("#editfee").show();
	      			$("#status").val("Y");
	      			$("#inactive").text("InActive");
	      			getConcessionDetails();
	      		 }
	      	}else{
	      		
	      		 getConcessionDetails();
	      	}
					
			 $("#concessiontype").change(function(){
				 HideError(this); 
			 });
			 
	           $('#search').click(function(){
	  	    	   getConcessionDetails();
	  	        });
	           
	           $("#searchvalue").keypress(function(event) {
	        	  
	       		if (event.keyCode == 13) { 
	       			getConcessionDetails();
	       		  }
	       	});
	          
	           $('#back1').click(function()
				 {
				    window.location.href ="menuslist.html?method=FeeConcessionList&historylocId="+$("#historylocId").val()+"&historysearchvalue="+$("#historysearchvalue").val()+"&currentstatus="+$("#historystatus").val();
				 });
	           
	           $('#locationname').change(function(){
	        	   getConcessionDetails();
	    	     });
	           
	      $("#status").change(function(){
	       		
	       	 if($(this).val()=="Y"){ 
	       		$("#editfee").show();
	       		$("#inactive").text("InActive");
	       	}
	       	else{
	       		$("#editfee").hide();
	       		$("#inactive").text("Active");
	       	 }
	       	 getConcessionDetails();
	       	 $("#selectall").prop("checked",false);
	       	 
	       	});
	           
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
						$(".errormessagediv1").hide();

					}, 2000);
					 
					$('#view').click(function()
					   {
						 window.location.href = "menuslist.html?method=FeeConcessionList";
					   });
				
					 $('#concessionname').keypress(function (e) {
					        var regex = new RegExp(/^[a-zA-Z0-9-&.(\][)\s]*$/);
					        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
					        if (regex.test(str)) {
					        	 HideError(this);
					            return true;
					        }
					        e.preventDefault();
					        $(".errormessagediv1").show();
							$(".validateTips1").text("Allows only alphanumerics and -, (, ), [, ], &");
							setTimeout(function() {
				  				$('#errormessagediv1').fadeOut();
				  			    },2000);
					        return false;
					    });		
					
             $('#submit').click(function()
			     {
					var concessionId = $('#concessionId').val();
					var concessionname = $('#concessionname').val().trim();
					var locId = $('#locationname').val().trim();
					var description = $('#description').val().trim();
					var isApplicable=[];
					$('input[type="checkbox"]:checked').each(function(){
						isApplicable.push($(this).val());
					});
					var concessionType=$("#concessiontype").val();
					var concession=$('input[name="concession"]:checked').val();
					if (myFunction(isApplicable))
						{
					datalist = {
							    "concessionId" :concessionId,
								"concessionname" : concessionname,
								"locId" : locId,
								"description" : description,
								"isApplicable" : isApplicable.toString(),
								"concessionType" : concessionType,
								"concession" : concession,
							},
							
							$.ajax({
										type : "POST",
										url : "menuslist.html?method=insertConcesssionDetails",
										data : datalist,
										beforeSend: function(){
	                						$("#loder").show();
	                					},
										success : function(data)
										{
											var result = $.parseJSON(data);
											  
											if(result.jsonResponse == "success") {
												$(".errormessagediv1").hide();
												$("#loder").hide();
												$(".successmessagediv").show();
												$(".validateTips").text("Concession Details Added Successfully");
												setTimeout(function() {
													window.location.href ="menuslist.html?method=FeeConcessionList&historylocId="+$("#historylocId").val()+"&historysearchvalue="+$("#historysearchvalue").val()+"&historystatus="+$("#historystatus").val();
												 },2000);
											}
											else if(result.jsonResponse == "fail") {
												$(".errormessagediv1").hide();
												$("#loder").hide();
												$(".successmessagediv").show();
												$(".validateTips").text("Concession Details not Added Successfully");
												setTimeout(function() {
													window.location.href ="menuslist.html?method=FeeConcessionList&historylocId="+$("#historylocId").val()+"&historysearchvalue="+$("#historysearchvalue").val()+"&historystatus="+$("#historystatus").val();
											     },2000);
											}
											else if(result.jsonResponse == "updatesuccess") {
												$(".errormessagediv1").hide();
												$("#loder").hide();
												$(".successmessagediv").show();
												$(".validateTips").text("Concession Details  Update Successfully");
												setTimeout(function() {
													window.location.href ="menuslist.html?method=FeeConcessionList&historylocId="+$("#historylocId").val()+"&historysearchvalue="+$("#historysearchvalue").val()+"&historystatus="+$("#historystatus").val();
												 },2000);
											}
											else if(result.jsonResponse == "updatefail") {
												$(".errormessagediv1").hide();
												$("#loder").hide();
												$(".successmessagediv").show();
												$(".validateTips").text("Concession Details  not Updated Successfully");
												setTimeout(function() {
													window.location.href ="menuslist.html?method=FeeConcessionList&historylocId="+$("#historylocId").val()+"&historysearchvalue="+$("#historysearchvalue").val()+"&historystatus="+$("#historystatus").val();
												 },2000);
											  }
										}
								 });
				          }
				   });
                          
                      	$('#editfee').click(function()
						  {
							getData = $(".select:checked").attr("id");
					         var cnt =$(".select:checked").length;
	
							if (cnt == 0 || cnt > 1) {
								$(".errormessagediv").show();
								$(".validateTips").text("Select any one Record");
							}
							else {
								window.location.href ="feeconcession.html?method=EditConcesssionFeeDetails&id="+getData+"&locId="+$("#locationname").val()+"&status="+$("#status").val()+"&searchvalue="+$("#searchvalue").val();
							}
						  });
                    	
                      	$("#inactive").click(function(){
                     		var count =0;
                     		concessionIds=[];
                     		$(".select:checked").each(function(){
                     			var list=$(this).attr("id");
                     			concessionIds.push(list);
                     			count ++;
                     		});	

                     		if(count == 0)	{
                     			$('.errormessagediv').show();
                     			$('.validateTips').text("Select Records to "+$("#inactive").text());
                     			$('.errormessagediv').delay(3000).slideUp();
                     		}
                     		else{
                     			if($("#inactive").text() == "InActive"){
                    				$("#hidstatus").val("N")
                    			}else{
                    				$("#hidstatus").val("Y")
                    			}
                     			
                     			    $("#dialog").dialog("open");
                     				$("#dialog").empty();
                     				$("#dialog").append("<p class='warningfont'>Are you sure to "+$("#inactive").text()+"?</p>");
                     				$("#dialog").append('<label class="warningothers" for="">Reason:</label>');
                     				$("#dialog").append('<select name="inactivereason" class="warningfont" style="width: 100%;" id="inactivereason" onchange="HideError(this)">'
                     						+ '<option value="">' + "----------select----------"
                     						+ '</option>'
                     						+ '<option value="Incorrect Entry">' + "Incorrect Entry"
                     						+ '</option>'
                     						+ '<option value="Not in use">' + "Not in use" 
                     						+ '</option>'
                     						+ '<option value="others">' + "Others" 
                     						+ '</option>'+
                     				'</select>');
                     				
                     				$("#dialog").append('<select name="activereason" class="warningfont" style="width: 100%;" id="activereason" onchange="HideError(this)">'
                     						+ '<option value="">' + "----------select----------"
                     						+ '</option>'
                     						+ '<option value="Correct Entry">' + "Correct Entry"
                     						+ '</option>'
                     						+ '<option value="In use">' + "In use" 
                     						+ '</option>'
                     						+ '<option value="others">' + "Others" 
                     						+ '</option>'+
                     				'</select>');
                     				
                     				  $("#dialog").append('<div id="othreason"><label class="warningothers">OtherReason:</label><input type="text" style="width: 100%;" class="warningfont" name=other id="otherreason" onclick="HideError(this)"/></div>');
                     		     
                     				 $("#othreason").hide();
                     		  		  $("#activereason").hide();
                     		  		  $('#inactivereason').change(function(){
                     		  			$(".errormessagediv").hide();
                     		  			var othersres=$('#inactivereason').val();
                     		  			if(othersres == 'others'){
                     		  				$("#othreason").show(); 
                     		  				$("#activereason").hide();
                     		  			}else{
                     		  				$("#otherreason").val("");
                     		  				$("#othreason").hide();
                     		  				$("#inactivereason").show();
                     		  				$("#activereason").hide();
                     		  			}
                     		  		});
                     		  		if($("#status").val()=="N"){
                     	  				$("#othreason").hide();
                     	  				$("#activereason").show();
                     	  				$("#inactivereason").hide();
                     	  			}
                     		  		$('#activereason').change(function(){
                     		  		if($(this).val() == 'others'){
                     	  				$("#othreason").show(); 
                     	  				$("#activereason").show();
                     	  				$("#inactivereason").hide();
                     	  			}else{
                     	  				$("#otherreason").val("");
                     	  				$("#othreason").hide(); 
                     	  				$("#activereason").show();
                     	  				$("#inactivereason").hide();
                     	  			}
                     		  		});
                     		  	  reason = $("#inactivereason").val();
                     		}
                     	});
                     
                     $("#dialog").dialog({
                 	    autoOpen: false,
                 	    resizable: false,
                 		modal: true,					    
                 		title:'Concession Details',
                 		buttons : {
                 			"Yes" : function() {
                 				 
                 				 var inactivereason=$("#inactivereason").val();
                 				 var activereason=$("#activereason").val();
                 				 var otherreason=$("#otherreason").val();
                 				  
                                 if((inactivereason== "" || inactivereason==null)&& $("#status").val()=="Y")
                               	  {
                                 	document.getElementById("inactivereason").style.border = "1px solid #AF2C2C";
                         			document.getElementById("inactivereason").style.backgroundColor = "#FFF7F7";
                               	    $(".errormessagediv").show();
                 		     		    $(".validateTips").text("Select Any One Reason");
                 		     		    setTimeout(function() {
                 		  				$('#errormessagediv').fadeOut();
                 		  			    },3000);
                               	  }
                                 else if((inactivereason=="others" && otherreason.trim()=="") && $("#status").val()=="Y"){
                                 	document.getElementById("otherreason").style.border = "1px solid #AF2C2C";
                         			document.getElementById("otherreason").style.backgroundColor = "#FFF7F7";
                                	      
                         				$(".errormessagediv").show();
                 		     		    $(".validateTips").text("OtherReason Required field");
                 		     		    setTimeout(function() {
                 		  				$('#errormessagediv').fadeOut();
                 		  			    },3000);
                                 }
                                 else if((activereason=="" || activereason==undefined) && $("#status").val()=="N"){
                                 	document.getElementById("activereason").style.border = "1px solid #AF2C2C";
                         			document.getElementById("activereason").style.backgroundColor = "#FFF7F7";
                         				
                                 	$(".errormessagediv").show();
                 		     		    $(".validateTips").text("Select Any One Reason");
                 		     		    setTimeout(function() {
                 		  				$('#errormessagediv').fadeOut();
                 		  			    },3000);
                                 }
                                 else if((activereason=="others" && otherreason.trim()=="") && $("#status").val()=="N"){
                                 	document.getElementById("otherreason").style.border = "1px solid #AF2C2C";
                         			document.getElementById("otherreason").style.backgroundColor = "#FFF7F7";
                                	 $(".errormessagediv").show();
                 		     		    $(".validateTips").text("OtherReason Required field");
                 		     		    setTimeout(function() {
                 		  				$('#errormessagediv').fadeOut();
                 		  			    },3000);
                                 }
                                 else
                                   {
	                 				$.ajax({
	                 					type : 'POST',
	                 					url : "feeconcession.html?method=Delete",
	                 					data : {
	                 						"concessionid":concessionIds.toString(),
	                 						"inactivereason":$("#inactivereason").val(), 
	                 						"activereason":$("#activereason").val(),
	                 						"otherreason":$("#otherreason").val(),
	                 						"status":$("#inactive").text(),
	                 					},
	                 					beforeSend: function(){
	                						$("#loder").show();
	                					},
	                 					success : function(response) {
	                 						var result = $.parseJSON(response);
	                 						
	                 						var flag = false;
	                 						$('.errormessagediv').hide();
	                 						$("#loder").hide();
	                 						if (result.status == "inactivetrue") {
	                 							flag = true;
	                 							$("#loder").hide();
	                 							$(".successmessagediv").show();
	                 							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
	                 							$(".validateTips").text("Inactivating unmapped concession details progressing...");
	                 						}else if(result.status == "inactivefalse"){
	                 							$("#loder").hide();
	                 							$(".errormessagediv").show();
	                 							$(".validateTips").text("Concession details mapped cannot be Inactive");
	                 						}
	                 						else if(result.status == "activetrue"){
	                 							flag = true;
	                 							$(".successmessagediv").show();
	                 							$(".validateTips").text("Activating concession details progressing...");
	                 						}else{
	                 							$("#loder").hide();
	                 							$(".errormessagediv").show();
	                 							$(".validateTips").text("Failed to perform the operation. Try again");
	                 						}
	                 						if(flag){
	                 							setTimeout(function(){
	                 								$(".successmessagediv").hide();
		                 							window.location.href ="menuslist.html?method=FeeConcessionList&currentstatus="+$("#hidstatus").val()+"&historylocId="+$("#locationname").val();
		                 						},2000);
	                 						}else{
	                 							setTimeout(function(){
	                 								$(".errormessagediv").hide();
	                 							},3000);
	                 						}
	                 					}
	                 				});  
                 				 $(this).dialog("close");
                 				$("#selectall").prop("checked",false);
                 				$(".select").prop("checked",false);
                 				concessionIds=[];
                 			   }
                 			},
                 			"No" : function() {
                 				$(this).dialog("close");
                 			}
                 		}
                 	});
                    	
                    	$("#excelDownload").click(function(){
							var searchvalue = $('#feeconcessionsearchid').val();
                			window.location.href = "feeconcession.html?method=ConcessionDetailsExcelReport&searchvalue="
										+ searchvalue;
                		});
                		
                		$("#pdfDownload").click(function(){
							var searchvalue = $('#feeconcessionsearchid').val();
                			window.location.href = "feeconcession.html?method=ConcessionDetailsPDFReport&searchvalue="
										+ searchvalue;
                		});
                		
 });

function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
function myFunction(isApplicable)
{
	var id = $('#concessionId').val();
	var name = $('#concessionname').val().trim();
	var locationname = $('#locationname').val();

	if (locationname == "" || locationname == null)
	{
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field required - Branch Name");
		document.getElementById("locationname").style.border = "1px solid #AF2C2C";
		document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		return false;

	}
	else if (name == "" || name == null)
	{
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field required - Concession Name");
		document.getElementById("concessionname").style.border = "1px solid #AF2C2C";
		document.getElementById("concessionname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);

		return false;
	}
	
	else if(isApplicable.length==0){
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field required - Applicable For");
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 4000);
		return false;
	}else if ($("#concessiontype").val() == "" || $("#concessiontype").val() == null)
	{
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field required - Concession Type");
		document.getElementById("concessiontype").style.border = "1px solid #AF2C2C";
		document.getElementById("concessiontype").style.backgroundColor = "#FFF7F7";
		/*setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);*/
		
		return false;
	}
	var status = false;

	datalist = {
		"name" : name,
		"id" : id,
		"locationname" : locationname
	},

	$.ajax({
		type : "POST",
		url : "feeconcession.html?method=getnamecount",
		data : datalist,
		async : false,
		success : function(data)
		{
			var result = $.parseJSON(data);
			if (result.message=="duplicate")
			{
				$(".successmessagediv").hide();
				$(".errormessagediv1").show();
				$(".validateTips1").text("Concession Name Already Exists");
				status = false;
			}
			else if (result.message=="duplicateInactive")
			{
				$(".successmessagediv").hide();
				$(".errormessagediv1").show();
				$(".validateTips1").text("Concession Name Already Exists.Make it Active...!!");
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
function getConcessionDetails()
{
	var datalist = {
			"location" :$("#locationname").val(),
			"searchvalue":$("#searchvalue").val(),
			"status":$("#status").val(),
		}; 
	
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=ConcessionDetailList",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			success : function(data) {
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					var i = 0;
					var len = result.concessiondetailsList.length;
					if(len>0){
					for(i=0;i<len;i++){

					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' value='"+result.concessiondetailsList[i].concessionId+"' id='"+result.concessiondetailsList[i].concessionId+"' /></td>"
							+"<td> "+result.concessiondetailsList[i].locId+" </td>"
							+"<td> "+result.concessiondetailsList[i].concessionName+" </td>"
							+"<td> "+result.concessiondetailsList[i].description+" </td>"
							+"<td> "+result.concessiondetailsList[i].remarks+" </td>"
							+"</tr>");
					   }
					}
					else{
						$("#allstudent tbody").append("<tr>" +"<td colspan='5'>No Records Founds</td>" +"</tr>");
					}
					checkboxsselect();
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. Of Records "+len);
					pagination(100);
					$("#loder").hide();
			}
		});

	}

function getConcessionTypes()
{ 
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=getConcessionTypes",
			success : function(data) {
				var result = $.parseJSON(data);
				$("#concessiontype").empty();
				$("#concessiontype").append('<option value="">----------Select----------</option>');
					if(result.concessionTypeList.length>0){
					for(var i=0;i<result.concessionTypeList.length;i++){
						$("#concessiontype").append('<option value="'+result.concessionTypeList[i].concessionId+'">'+result.concessionTypeList[i].concessionName+'</option>');
					}
					$("#concessiontype").val($("#hconcessionType").val());
					}
			}
		});

	}
function checkboxsselect(){
	$("#selectall").change(function(){
	    $(".select").prop('checked', $(this).prop("checked"));
     });

$(".select").change(function(){
	if($(".select").length==$(".select:checked").length){
		$("#selectall").prop("checked",true);
	}
	else{
		$("#selectall").prop("checked",false);
	}
});
}

function HideError(pointer) 
{
 $(".errormessagediv").hide();
 document.getElementById(pointer.id).style.border = "1px solid #ccc";
 document.getElementById(pointer.id).style.backgroundColor = "#fff";
}
