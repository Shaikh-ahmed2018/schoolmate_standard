function removeMessage() {
	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
function myFunction1() {
    document.getElementById("myForm").submit();   
 }
$(document).ready(function() {
	StagedetailsListByStatus();
	checkboxsselect();
	
	$("#status").change(function(){
		 if($(this).val()=="Y"){ 
			$("#inactive").text("InActive");
			$("#editStageId").show();
			StagedetailsListByStatus();
		}
		else{
			$("#inactive").text("Active");
			$("#editStageId").hide();
			StagedetailsListByStatus();
		 }
		 $("#selectall").prop("checked",false);
		});
	
	$("#stagename").click(function(){
		$(".errormessagediv").hide();
	});
	
	$("#addStageDetails").dialog({
	    autoOpen  : false,
	    resizable: false,
	    maxWidth  :	500,
        maxHeight : 400,
        width     : 500,
        height    : 250,
	    modal     : true,
	    //title     : "Add Stage",
	    buttons   : {
	    	'Save'  : function() {
	    	var 	pointer=$(this);
	    		saveStageDetails();
	    	},
	    	'Close' : function() {
	    		$("#stagename").val("");
                $("#description").val("");
                 $(this).dialog('close');
             }
	    }
	});
	
	$("#add").click(function(){
		document.getElementById("stagename").style.border = "1px solid #ccc";
		document.getElementById("stagename").style.backgroundColor = "#fff";
		$("#stagename").val("");
		$("#description").val("");
		$('input:checkbox').prop("checked",false);
		$('#addStageDetails').dialog({ title: "New Stage" });
		$("#addStageDetails").dialog("open");
	});
	$("#editStageId").click(function(){
		document.getElementById("stagename").style.border = "1px solid #ccc";
		document.getElementById("stagename").style.backgroundColor = "#fff";
		var cnt = 0;
		$(".select:checked").each(function() {
					getData = $(this).attr("id").split(",")[0];
					stageName = $(this).attr("id").split(",")[1];
					Description = $(this).attr("id").split(",")[2];
					cnt++;
				});
		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Atleast One Record");
			return false;
		}  else {
			var stageCode = getData.trim();
			$("#stageid").val(stageCode)
			$("#stagename").val(stageName)
			$("#hiddenstagename").val(stageName)
			$("#description").val(Description)
			$('#addStageDetails').dialog({ title: "Modify Stage" });
			$("#addStageDetails").dialog("open");
		}
	});
	$("#back1").click(function(){
		window.location.href="menuslist.html?method=SchoolWiseStageList&historylocId="+$("#historylocId").val();
	});
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='5'>NO Records Found</td></tr>");
	}
	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});
	$("#stage").click(function(){
		$("#stageclose").slideToggle();
	});


	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);

	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);

	/*$("#allstudent tbody tr td:nth-child(3)").each(function(){
						var value=parseInt($(this).text());
						var digit=value.toFixed(2);
						$(this).text(digit);
					});*/

	$('#view').click(function(){
			window.location.href = "menuslist.html?method=StageList";
			});

	// for edit 

	/*$('#editStageId').click(function() {
				var cnt = 0;
				$('input[type="checkbox"]:checked').map(function() {
							getData = $(this).attr("id");
							cnt++;
						});
				if (cnt == 0 || cnt > 1) {
					$(".errormessagediv").show();
					$(".validateTips").text("Select any one Stage to Edit");
					return false;
				}  else {
					var stageCode = getData;
					window.location.href = "addstage.html?method=Edit&stageCode="+stageCode.trim();
				}
			});*/


	$("#search").click(function(){
		StagedetailsListBySearch();
	 });
	$("#resetbtn").click(function(){
		$("#searchvalue").val("");
		$("#status").val("active");
		StagedetailsListBySearch();
	});
	/*$("#searchvalue").keyup(function(event) {
		 StagedetailsListByStatus();
	});*/
	
	$("#searchvalue").keyup(function(event) {
		if (event.keyCode == 13) {
			StagedetailsListBySearch();
			}
	});

	// to delete

	$('#inactive').click(function() {
		var count =0;
		stageCode=[];
		$(".select:checked").each(function(){
			var list=$(this).attr("id").split(",")[0];
			stageCode.push(list);
			count ++;
		});	
		if(count == 0)	{
			$('.errormessagediv').show();
			$('.validateTips').text("Select Atleast One Record");
			$('.errormessagediv').delay(3000).slideUp();
		}
		else 	{
		    $("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p class='warningfont'>Are you sure to "+$("#inactive").text()+"?</p>");
			$("#dialog").append('<label class="warningothers" for="">Reason:</label>');
			$("#dialog").append('<select name="inactivereason" class="warningfont" style="width: 100%;" id="inactivereason" onchange="HideError1(this)">'
					+ '<option value="">' + "----------select----------"
					+ '</option>'
					+ '<option value="Incorrect Entry">' + "Incorrect Entry"
					+ '</option>'
					+ '<option value="Not in use">' + "Not in use" 
					+ '</option>'
					+ '<option value="others">' + "Others" 
					+ '</option>'+
			'</select>');
			
			$("#dialog").append('<select name="activereason" class="warningfont" style="width: 100%;" id="activereason" onchange="HideError1(this)">'
					+ '<option value="">' + "----------select----------"
					+ '</option>'
					+ '<option value="Correct Entry">' + "Correct Entry"
					+ '</option>'
					+ '<option value="In use">' + "In use" 
					+ '</option>'
					+ '<option value="others">' + "Others" 
					+ '</option>'+
			'</select>');
			
			  $("#dialog").append('<div id="othreason"><label class="warningothers">OtherReason:</label><input type="text" style="width: 100%;" class="warningfont" name="other" id="otherreason" maxlength="249" onclick="HideError1(this)"/></div>');
	     
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
		title:'Stage Details',
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
					type : "POST",
					url : "addstage.html?method=deleteStageByStatus",
					data : {
						"stageCode"      :stageCode.toString(),
						"inactivereason" :$("#inactivereason").val(), 
						"activereason"   :$("#activereason").val(),
						"otherreason"    :$("#otherreason").val(),
						"status"         :$("#inactive").text(),
						"locId"          :$("#location").val()
					},
					success : function(response) {
						var result = $.parseJSON(response);

						$('.errormessagediv').hide();
						 
						
						if (result.status =="inactivetrue") {
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
							$(".validateTips").text("Inactivating unmapped Stage details progressing...");
							setTimeout(function(){
								$('.successmessagediv').hide();
								window.location.href="menuslist.html?method=StageList&location="+$("#location").val()
								+"&historylocId="+$("#historylocId").val()+"&currentstatus="+$("#status").val();
							},3000);
						} 
						else if (result.status =="activetrue") {
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
							$(".validateTips").text("Activating Stage details progressing...");
							setTimeout(function(){
								$('.successmessagediv').hide();
								window.location.href="menuslist.html?method=StageList&location="+$("#location").val()
								+"&historylocId="+$("#historylocId").val()+"&currentstatus="+$("#status").val();
							},3000);
						}
						else if(result.status == "inactivefalse"){
							$(".errormessagediv").show();
							$(".validateTips").text("Stage details mapped cannot be inactive");
							setTimeout(function(){
								$('.errormessagediv').hide();
							},3000);
						}
						else{
							$(".errormessagediv").show();
							$(".validateTips").text("Failed to perform the operation");
							setTimeout(function(){
								$(".errormessagediv").hide();
							},3000);
						}
						 
					}

				});  

				$(this).dialog("close");
				$("#selectall").prop("checked",false);
				stageCode=[];
			  }
			},
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});


	$("#xls").click(function(){
				var searchTerm = $("#searchexamid").val().trim();
				window.location.href = "addstage.html?method=downloadStageDetailsXLS&searchTerm="+ searchTerm;
			});
	$("#pdfDownload").click(function(){
				var searchTerm = $("#searchexamid").val().trim();
				window.location.href = "addstage.html?method=downloadStageDetailsPDF&searchTerm="+ searchTerm;
			});
	
	if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){
		 
		if($("#currentstatus").val()=="Y"){ 
			$("#status").val("N");
			$("#editStageId").hide();
			$("#inactive").text("Active");
			StagedetailsListByStatus();
			}
			else{
			$("#status").val("Y");
			$("#editStageId").show();
			$("#inactive").text("InActive");
			StagedetailsListByStatus();
		   }
	}
	
});
function HideError(){
	document.getElementById("stagename").style.border = "1px solid #ccc";
document.getElementById("stagename").style.backgroundColor = "#fff";
}

function HideError1(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
    document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

function saveStageDetails(){
	var id = $('#stageid').val();
	var name = $('#stagename').val();
	var description = $('#description').val();
	var location = $('#location').val();
	
	
	if (name.trim() == "" || name == null)
	{
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required - Stage");
		document.getElementById("stagename").style.border = "1px solid #AF2C2C";
		document.getElementById("stagename").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	}
	else if(validateStagename(id,name)=="duplicate")
	{
		$(".errormessagediv").show();
		$(".validateTips").text("Stage Name Already Exists");
		document.getElementById("stagename").style.border = "1px solid #AF2C2C";
		document.getElementById("stagename").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	}
	else if(validateStagename(id,name)=="duplicateinactive")
	{
		$(".errormessagediv").show();
		$(".validateTips").text("Stage Name Already Exists..Make it Active!!");
		document.getElementById("stagename").style.border = "1px solid #AF2C2C";
		document.getElementById("stagename").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	}
	else if (name.trim().length <= 0)
	{
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Stage Name");
		document.getElementById("stagename").style.border = "1px solid #AF2C2C";
		document.getElementById("stagename").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		return false;
	}
	datalist = {
			"name" : name,
			"description" : description,
			"id" : id,
			"location":location,
	},
	$.ajax({
		type : "POST",
		url : "menuslist.html?method=savestage",
		data : datalist,
		success : function(data)
		{
			var result = $.parseJSON(data);
			if(result.jsonResponse=="Stage Added Successfully"){
				$(".select").prop("checked",false);
			    $("#selectall").prop("checked",false);
				$("#addStageDetails").dialog('close');
				$(".errormessagediv").hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Adding Record Progressing...");
				setTimeout(function(){
					//window.location.href ="menuslist.html?method=StageList&location="+location;
					$("#addStageDetails").dialog('close');
					StagedetailsListByStatus();
				},3000);
			}
			if(result.jsonResponse=="Stage Update Successfully"){
				$(".select").prop("checked",false);
			    $("#selectall").prop("checked",false);
				$("#addStageDetails").dialog('close');
				$(".errormessagediv").hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Updating Record Progressing...");
				setTimeout(function(){
					//window.location.href ="menuslist.html?method=StageList&location="+location;
					StagedetailsListByStatus();
				},3000);
			}
		}
	});
}
function validateStagename(id,name){
	 
	var status;
	var hiddenstagename = $('#hiddenstagename').val();
	if(hiddenstagename == name){
		status="noduplicate";
	}else{
		datalist = {
				"id" : id,
				"name":name,
				"locId":$("#location").val(),
		},
		$.ajax({
			type : "POST",
			url : "menuslist.html?method=validateStage",
			data : datalist,
			async:false,
			success : function(data)
			{
				var result = $.parseJSON(data);
				status=result.jsonResponse;
			}
		});
	}
	return status;
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

function StagedetailsListBySearch(){
	var datalist = {
			"status" :$("#status").val(),
			"searchvalue" :$("#searchvalue").val(),
			"location":$('#location').val(),
		}; 
		$.ajax({
			type : 'POST',
			url : "addstage.html?method=stagedetailsListByStatus",
			data : datalist,
			async:false,
			success : function(data) {
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					var i=0;
					var len=result.list.length;
					if(len>0){
					for(i=0;i<len;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' value='"+result.list[i].stageCode+"' id='"+result.list[i].stageCode+","+result.list[i].stageName+","+result.list[i].description+"' /></td>"
							+"<td> "+result.list[i].stageName+" </td>"
							+"<td> "+result.list[i].amount+"</td>"
							+"<td> "+result.list[i].description+" </td>"
							+"<td> "+result.list[i].remarks+" </td>"
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
			}
		});

	}

function StagedetailsListByStatus(){
	var datalist = {
			"status" :$("#status").val(),
			"searchvalue" :$("#searchvalue").val(),
			"location":$('#location').val(),
		}; 
		$.ajax({
			type : 'POST',
			url : "addstage.html?method=stagedetailsListByStatus",
			data : datalist,
			success : function(data) {
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					var i=0;
					var len=result.list.length;
					if(len>0){
					for(i=0;i<len;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' value='"+result.list[i].stageCode+"' id='"+result.list[i].stageCode+","+result.list[i].stageName+","+result.list[i].description+"' /></td>"
							+"<td> "+result.list[i].stageName+" </td>"
							+"<td> "+result.list[i].amount+"</td>"
							+"<td> "+result.list[i].description+" </td>"
							+"<td> "+result.list[i].remarks+" </td>"
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
			}
		});

	}





