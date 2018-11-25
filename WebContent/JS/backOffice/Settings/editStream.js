$(document).ready(function() {
 
	if($("#hiddenlocId").val()!="" && $("#statuscurrent").val()!="")
	{
	
		$("#locationname").val($("#hiddenlocId").val());
		
	
		changeAccYear();
	}else{
		$("#locationname").val($("#curr_loc").val());
	}
	
	
	changeAccYear();
	checkboxsselect();
			
			$("#status").change(function(){
					changeAccYear();
				
			});
			 
			 
			
	
			

			$("#locationname").change(function() {
				changeAccYear();
				
			});
			
			$(".switch-cirle").click(function(){
				if($("#status").val()=="Y"){
					$("#inactive").text("InActive");
					$("#status").val("N");
					$(this).css({
						"left":"60px",
						
						
					});
					
					$("#text-status").css({"left":"7px"});
					$("#text-status").text("Inactive");
					changeAccYear();
				}
				else{
					$("#inactive").text("Active");
					$("#status").val("Y");
					$(this).css({
						"left":"1px",
						
					});
					
					$("#text-status").css({"left":"30px"});
					$("#text-status").text("Active");
					changeAccYear();
				}
			});
			
			$(".editstream").click(function() {
				
				
				var getData ={"streamid":$(this).closest("tr").attr("id"),
						"locId":$("#locationname").val(),
						"status":$("#status").val()
				};
				
				$.ajax({
					type:'POST',
					url:'streamDetails.html?method=editStreamDetailsAction',
					data:getData,
					async:false,
					success:function(data){
						var result=$.parseJSON(data);
						
						$("#location").val(result.list[0].locationId);
						$("#streamName").val(result.list[0].streamName);
						$("#description").text(result.list[0].description);
						$("#streamId").val(result.list[0].streamId);
						$("#actionstatus").value="editaction";
					}
				});
				


					});
			
			$("#savebutton").click(function(){
				$("#actionstatus").value="addaction";
			});

			$(document).on("click",".inactive",function(){
				  maxval=350;
				 if($("#status").val()=="N"){
					maxval=280;
					$("#dialog").dialog({ width: maxval});
					}else{
						$("#dialog").dialog({ width: maxval});
					}
				
				streamIdlist=[];
				
				var Ids=$(this).closest("tr").attr("id");
					streamIdlist.push(Ids);
					
					 $("#dialog").dialog("open");
						$("#dialog").empty();
						$("#dialog").append("<p class='warningfont'>Are you sure to "+$("#inactive").text()+"?</p>");
						$("#dialog").append('<p class="warningfont" id="warningreason" style="color:red;">*Warning&nbsp;:&nbsp;If you Inactivate this Stream, you won\'t be able to view details related to this Stream.</p>');
						$("#dialog").append('<label class="warningothers" for="">Reason:</label>');
						$("#dialog").append('<div><select class="warningfont" style="width: 100%;" name="inactivereason" id="inactivereason" onchange="HideError(this)">'
								+ '<option value="">' + "----------select----------"
								+ '</option>'
								+ '<option value="Incorrect Entry">' + "Incorrect Entry"
								+ '</option>'
								+ '<option value="Not in use">' + "Not in use" 
								+ '</option>'
								+ '<option value="others">' + "Others" 
								+ '</option>'+
						'</select></div>');
						
						$("#dialog").append('<div><select class="warningfont" style="width: 100%;" name="activereason" id="activereason" onchange="HideError(this)">'
								+ '<option value="">' + "----------select----------"
								+ '</option>'
								+ '<option value="Correct Entry">' + "Correct Entry"
								+ '</option>'
								+ '<option value="In use">' + "In use" 
								+ '</option>'
								+ '<option value="others">' + "Others" 
								+ '</option>'+
						'</select></div>');
						
						  $("#dialog").append('<div id="othreason"><label class="warningothers">OtherReason:</label><input type="text" class="warningfont" style="width: 100%;" name=other id="otherreason" onclick="HideError(this)"/></div>');
				     
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
			  				$("#warningreason").hide();
			  				$("#activereason").show();
			  				$("#inactivereason").hide();
			  			}
				  		$('#activereason').change(function(){
				  		if($(this).val() == 'others'){
			  				$("#othreason").show(); 
			  				$("#activereason").show();
			  				$("#inactivereason").hide();
			  			}else{
			  				$("#othreason").hide(); 
			  				$("#activereason").show();
			  				$("#inactivereason").hide();
			  			}
				  		});
				  	  reason = $("#inactivereason").val();
			});

			$("#dialog").dialog({
				autoOpen: false,
				modal: true,
				resizable: false,
				title:'Stream Details',
				maxheight:280,
				minheight:260,
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
		                	var status="N";
		                	alert($("#status").val());
		                	if($("#status").val()=="Y"){
		                		status="N";
		                	}
		                	else{
		                		status="Y";
		                	}
		                	
						$.ajax({
							type : 'POST',
							url : "streamDetails.html?method=InActiveStreamDetails",
							data : {
								"streamid":streamIdlist.toString(),
								"inactivereason":$("#inactivereason").val(), 
								"activereason":$("#activereason").val(),
								"otherreason":$("#otherreason").val(),
								"status":status,
								
							},
							success : function(response) {
								var result = $.parseJSON(response);

								$('.errormessagediv').hide();

								if (result.status == "true") {
									$(".successmessagediv").show();
									$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
									$(".validateTips").text($("#inactive").text()+" the Stream Details Progressing...");
									$('.successmessagediv').delay(3000).slideUp();
								} else if(result.status == "false"){
									$(".errormessagediv").show();
									$(".validateTips").text("Selected Stream Details is Not "+$("#inactive").text());
									$('.errormessagediv').delay(3000).slideUp();
								}
								else{
									$(".errormessagediv").show();
									$(".validateTips").text("Selected Stream Details is Mapped Cannot InActive");
									$('.errormessagediv').delay(3000).slideUp();
								}
								setTimeout(function(){
									location.reload();
								},3000);

							}

						});  

						$(this).dialog("close");
					  }
					},
					"No" : function() {
						$(this).dialog("close");
					}
				}
			});

			$('#excelDownload')
			.click(function() {
						var searchTerm = $("#searchexamid").val().trim();
						window.location.href = 'streamDetails.html?method=downloadStreamDetailsXLS&searchTerm='+ searchTerm;
					});

			$("#pdfDownload").click(function() {
						var searchTerm = $("#searchexamid").val().trim();
						window.location.href = 'streamDetails.html?method=downloadStreamDetailsPDF&searchTerm='+ searchTerm;
					});
			
			$("#save").click(function(){

				
			     $(".successmessagediv").hide();
			     $(".errormessagediv").hide();
					
			     
					var streamname = $("#streamName").val().trim();
					var description = $("#description").val().trim();
					var streamId = $("#streamId").val().trim();
					
					if($("#location").val()=="" || $("#location").val()==undefined){
						
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
					else if($("#actionstatus").val()=="addaction" && validateStreamName() == 10){
                       $(".errormessagediv").show();
						$(".validateTips").text("Stream already exist in selected branch!!! Make it active");
						return false;
				}
					else if($("#actionstatus").val()=="addaction" && validateStreamName() == 1){
                           $(".errormessagediv").show();
							$(".validateTips").text("Stream already exists in selected branch");
							return false;
					}
					else if($("#actionstatus").val()=="editaction" && ($("#schoolId").val() == $("#locationname").val()) && (validateStreamName() == 10)){	
                       $(".errormessagediv").show();
						$(".validateTips").text("Stream already exist in selected branch!!! Make it active");
						return false;
					}
					else if($("#actionstatus").val()=="editaction" && ($("#schoolId").val() == $("#locationname").val()) && (validateStreamName() == 1)){	
						 
                       $(".errormessagediv").show();
						$(".validateTips").text("Stream already exists");
						return false;
					}
					else{ 
							datalist = {
									"locationname":$("#location").val(),
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
											 location.reload();
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
											 location.reload();
										 },3000);
									}		
								}

							});
					 }
					
				
				
			});

		});

function changeAccYear() {
	
	
	var locationId = $("#locationname").val();
    var status=$("#status").val();
    var sts="Inactive"
		if(status=="N"){
			sts="Active"
		}
		else{
			sts="Inactive"
		}
	$.ajax({
		type : 'POST',
		url : "menuslist.html?method=searchByLocationOnly",
		data : {
			"locationId" : locationId,
			"status":status,
		},
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var len=result.SearchList.length;
			var i=0;
			if(len>0){
			for ( i = 0; i < len; i++) {
				$("#allstudent tbody")
				.append(
						"<tr id='"+ result.SearchList[i].streamId+"'>"
						+ "<td>"+(i+1)+"</td>"
						+"<td>"+ result.SearchList[i].locationName+"</td>"
						+ "<td>"+ result.SearchList[i].streamName+"</td>"
						+ "<td> "+ result.SearchList[i].description+"</td>" 
						+"<td class='actiontd'>  </td>"
						+"</tr>");
			  }
			
			if($("#editPermission").val()=="true"){
				$("td.actiontd").each(function(){
					$(this).append('<span  data-toggle="modal" data-target="#myModal" class="btn btn-xs btn-primary margin-t-5 editstream" title="Edit"><span class="glyphicon glyphicon-edit"></span> Edit</span>');
				});
			}
			if($("#delPermission").val()=="true"){
				$("td.actiontd").each(function(){
					$(this).append('<span  class="btn btn-xs btn-primary margin-t-5 inactive" title="Active/Inactive"><span class="glyphicon glyphicon-link"></span> '+sts+'</span>');
				});
			}
		   }
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='5'>No Records Found</td></tr>");
			}
			checkboxsselect();
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
   		 }
	});
} /*+ "<td> "+ result.SearchList[i].status+"</td>" */

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

function removeMessage() {
	$(".successmessagediv").hide();
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

function myFunction() {
	window.location.href = "menuslist.html?method=streamList&searchname="
		+ $("#searchname").val().trim() + "&school="
		+ $("#school").val().trim();
}