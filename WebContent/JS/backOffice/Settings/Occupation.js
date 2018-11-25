function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
/*function myFunction() {
			         window.location.href="menuslist.html?method=occupationDetails&searchname="+$("#searchname").val();   
				  }*/


$(document).ready(function() {	
	$("#resetbtn").click(function(){
		$("#status").val("Y");
		$("#searchname").val("");
		getOccupationListByStatus();
	});
				if($("#hiddenstatus").val()!=""){
					 $("#status").val($("#hiddenstatus").val());
				}
				 
				if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){
					 
					 if($("#currentstatus").val()=="Y"){ 
						 $("#editOccupation").hide();
						 $("#status").val("N");
						$("#inactive").text("Active");
						getOccupationListByStatus();
					}
					else{
						$("#editOccupation").show();
						$("#status").val("Y");
						$("#inactive").text("InActive");
						getOccupationListByStatus();
					 }
				}
	  
	                checkboxsselect();
	     
					if($("#allstudent tbody tr").length==0){
						$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
						pagination(100);
						$(".numberOfItem").empty();
						$(".numberOfItem").append("No. of Records "+$("#allstudent tbody tr").length);
					}
					
					reason=null;
					var status = "N";
					$("#status").change(function(){
						var status=$("#status").val();
						
						if($(this).val()=="Y"){ 
							$("#editOccupation").show();
							$("#inactive").text("InActive");
						}
						else{
							$("#editOccupation").hide();
							$("#inactive").text("Active");
						 }
						getOccupationListByStatus();
						 $("#selectall").prop("checked",false);
				     });	
					
				$("#searchname").keypress(function(e){
					var key = e.which;
					if(key==13){
						searchOccupationDetails();
					}
				});	
				
				$("#search").click(function(e){
					searchOccupationDetails();
				});	
				
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
				
				
					$("#editOccupation").click(function(){
						$(".successmessagediv").hide();
						var cnt = 0;
						$('.select:checked').map(function() {
							getData = $(this).attr("id");
							cnt++;
						});
											
						if (cnt == 0 || cnt > 1) {
		     				$(".errormessagediv").show();
		     				$(".validateTips").text("Select any one Occupation");
		     				return false;
		     			} 
		     			else
		     				{
							var occupationId = getData;
							window.location.href = "religionCastCasteCategory.html?method=editOccupation&occupationId="+occupationId+"&status="+$("#status").val()+"&searchname="+$("#searchname").val();
							var result = $.parseJSON(response);
		     				}

					});
					
					occupationId=[];
					$("#inactive").click(function(){
						var count =0;
						$(".select:checked").each(function(){
							var Ids=$(this).attr("id");
							occupationId.push(Ids);
							count ++;
						});	
						
						if(count == 0)	{
							$('.errormessagediv').show();
							$('.validateTips').text("Select Records to "+$("#inactive").text());
							$('.errormessagediv').delay(3000).slideUp();
						}
						else{
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
									+ '<option value="">' + "----------select----------"+ '</option>'
									+ '<option value="Correct Entry">' + "Correct Entry"+ '</option>'
									+ '<option value="In use">' + "In use" + '</option>'
									+ '<option value="others">' + "Others" + '</option>'+
							'</select>');
							
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
						}
					});

					$("#dialog").dialog({
						autoOpen: false,
						resizable: false,
						modal: true,					    
						title:'Occupation Details',
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
									url : "religionCastCasteCategory.html?method=deleteOccupation",
									data : {
										"occupationId":occupationId.toString(),
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
										$("#loder").hide();
										if (result.status =="inactivetrue") {
											flag = true;
											$(".successmessagediv").show();
											$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
											$(".validateTips").text("Inactivating unmapped occupation details progressing...");
											
										} 
										else if (result.status =="activetrue") {
											flag = true;
											$(".successmessagediv").show();
											$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
											$(".validateTips").text("Activating occupation details progressing...");
										}
										else if(result.status == "inactivefalse"){
											$(".errormessagediv").show();
											$(".validateTips").text("Occupation details mapped cannot be inactive");
										}
										else{
											$(".errormessagediv").show();
											$(".validateTips").text("Failed to perform the operation");
										}
										if(flag){
											setTimeout(function(){
												$('.successmessagediv').hide();
												window.location.href="menuslist.html?method=occupationDetails&currentstatus="+$("#status").val();
											},5000);
										}
										else{
											setTimeout(function(){
												$('.errormessagediv').hide();
											},5000);
											$("#selectall").prop("checked",false);
											$(".select").prop('checked',false);
											occupationId=[];
										}
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
					
				if($("#hiddenhistorysearchname").val()!="" || $("#hiddenhistorystatus").val()=="N"){
					
					$("#searchname").val($("#hiddenhistorysearchname").val());
					$("#status").val($("#hiddenhistorystatus").val());
					
					if($("#status").val()=="Y"){ 
						$("#inactive").text("InActive");
					}
					else{
						$("#inactive").text("Active");
					 }
					searchOccupationDetails();
				}
          });		


function getOccupationListByStatus(){
	var datalist = {
            "status":$("#status").val(),
	}; 
	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getOccupationListByStatus",
		data : datalist,
		async:false,
		success : function(data) {

			var result = $.parseJSON(data);
			$("#allstudent tbody").empty();
			 
			if(result.list.length>0){
				for(var i=0;i<result.list.length;i++){

					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' id='"+result.list[i].occupationId+"' /></td>"
							+"<td> "+result.list[i].occupation+" </td>"
							+"<td> "+result.list[i].remarks+" </td>"
							+"</tr>");
				     }
			   }
			else{
				$("#allstudent tbody").append("<tr>" +"<td colspan='3'>No Records Founds</td>"+"</tr>");
			}
			checkboxsselect();
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.list.length);
		}
	});
}


function searchOccupationDetails(){
	 dataList={
			 searchname:$("#searchname").val().trim(),
			 status:$("#status").val(),
				};
				
				$.ajax({
					type:'POST',
					url:'menuslist.html?method=searchOccupationDetails',
					data:dataList,
					async:false,
					success: function(data){
						var result = $.parseJSON(data);
						$("#allstudent tbody").empty();
						 
						if(result.list.length>0){
							for(var i=0;i<result.list.length;i++){

								$("#allstudent tbody").append("<tr>"
										+"<td><input type='checkbox' class='select' id='"+result.list[i].occupationId+"' /></td>"
										+"<td> "+result.list[i].occupation+" </td>"
										+"<td> "+result.list[i].remarks+" </td>"
										+"</tr>");
							     }
						   }
						else{
							$("#allstudent tbody").append("<tr>" +"<td colspan='3'>No Records Founds</td>"+"</tr>");
						}
						checkboxsselect();
						pagination(100);
						$(".numberOfItem").empty();
						$(".numberOfItem").append("No. of Records  "+result.list.length);
						
						}
				});
				
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

function checkboxsselect(){
	$('#selectall').change(function(){
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

