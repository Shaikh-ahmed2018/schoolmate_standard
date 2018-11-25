function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document).ready(function() {	

	transportCategoryListByStatus();
	
	$("#status").change(function(){
		 if($(this).val()=="Y"){ 
			$("#inactive").text("InActive");
			transportCategoryListByStatus();
		}
		else{
			$("#inactive").text("Active");
			transportCategoryListByStatus();
		 }
		 $("#selectall").prop("checked",false);
		});
	
	$("#vehicleType").click(function(){
		$(".errormessagediv").hide();
	});
	
	$("#edit").click(function(){
		document.getElementById("vehicleType").style.border = "1px solid #ccc";
		document.getElementById("vehicleType").style.backgroundColor = "#fff";
		var cnt = 0;
		$(".select:checked").each(function() {
					getData = $(this).attr("id").split(",")[0];
					vehicleType = $(this).attr("id").split(",")[1];
					description = $(this).attr("id").split(",")[2];
					cnt++;
				});
		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Atleast One Record");
			return false;
		}  else {
			var stageCode = getData.trim();
			$("#transporttypeid").val(stageCode);
			$("#vehicleType").val(vehicleType);
			$("#hiddenvechiletype").val(vehicleType);
			$("#descriptionid").val(description);
			$('#addTransportcategory').dialog({ title: "Modify Transport Type" });
			$("#addTransportcategory").dialog("open");
		}
	
    });
	
	$("#addTransportcategory").dialog({
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
	    		saveTransportCategory();
	    	},
	    	'Close' : function() {
	    		$("#stagename").val("");
                $("#description").val("");
                 $(this).dialog('close');
             }
	    }
	});
	
   $("#add").click(function(){
	    document.getElementById("vehicleType").style.border = "1px solid #ccc";
		document.getElementById("vehicleType").style.backgroundColor = "#fff";
		$('input:checkbox').prop("checked",false);
		$("#vehicleType").val("");
		$("#descriptionid").val("");
		$('#addTransportcategory').dialog({ title: "New Transport Type" });
		$("#addTransportcategory").dialog("open");
	});
	
	$('#inactive').click(function() {
		var count =0;
		vehicleid=[];
		$(".select:checked").each(function(){
			var list=$(this).attr("id").split(",")[0];
			vehicleid.push(list);
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
			
			  $("#dialog").append('<div id="othreason"><label class="warningothers">OtherReason:</label><input type="text" style="width: 100%;" class="warningfont" name="other" maxlength="249" id="otherreason" onclick="HideError1(this)"/></div>');
	     
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
		title:'Route Details',
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
					url : "transport.html?method=deleteVehicleType",
					data : {
						"vehicleid" :vehicleid.toString(),
						"inactivereason":$("#inactivereason").val(), 
						"activereason":$("#activereason").val(),
						"otherreason":$("#otherreason").val(),
						"status":$("#inactive").text(),
					},
					
					success : function(response) {
						var result = $.parseJSON(response);

						$('.errormessagediv').hide();

						if (result.status == "true") {
							
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
							$(".validateTips").text($("#inactive").text()+" the Transport Category Progressing...");
							$('.successmessagediv').delay(3000).slideUp();
						} else if(result.status == "false"){
							
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Transport Category is Not "+$("#inactive").text());
							$('.errormessagediv').delay(3000).slideUp();
						}
						setTimeout(function(){
							window.location.href ="menuslist.html?method=transportCategory&currentstatus="+$("#status").val();
						},3000);
					}
				});  

				$(this).dialog("close");
				$("#selectall").prop("checked",false);
				vehicleid=[];
			  }
			},
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});

	$("#searchname").keypress(function(event) {
		if (event.keyCode == 13) { 
			transportCategoryListByStatus();
		}
	});
	
	/*$("#searchname").keyup(function(event) {
	   transportCategoryListByStatus();
	});
*/
	$("#search").click(function(){ 
		transportCategoryListByStatus();
	});
	$("#resetbtn").click(function(){ 
		$("#status").val("active");
		$("#searchname").val("");
		transportCategoryListByStatus();
	});
	
	if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){
		 
		if($("#currentstatus").val()=="Y"){ 
			$("#status").val("N");
			$("#edit").hide();
			$("#inactive").text("Active");
			transportCategoryListByStatus();
			}
			else{
			$("#status").val("Y");
			$("#edit").show();
			$("#inactive").text("InActive");
			transportCategoryListByStatus();
		   }
	}

});			

function saveTransportCategory(){
	 
	var vehicletype = $("#vehicleType").val().trim();
	var descname = $("#descriptionid").val().trim();
	var hiddenvechileid=$('#transporttypeid').val();
	var updatevehicle = $("#hiddenvechiletype").val().trim();
	var checkval=validateVechicleType();

	if (vehicletype == "" || vehicletype == null) {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required - Transport Type");
		document.getElementById("vehicleType").style.border = "1px solid #AF2C2C";
		document.getElementById("vehicleType").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);          

	} 
	else if(vehicletype.length >50 ){
		$('.errormessagediv').show();
		$('.validateTips').text("Transport Type Too Long!!!");
		document.getElementById("vehicleType").style.border = "1px solid #AF2C2C";
		document.getElementById("vehicleType").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	}
			
	else if (checkval==1) {
		$('.errormessagediv').show();
		$('.validateTips').text("Transport Type already Exist");
		document.getElementById("vehicleType").style.border = "1px solid #AF2C2C";
		document.getElementById("vehicleType").style.backgroundColor = "#FFF7F7";
		return false;
	}
	else if (checkval==10) {
		$('.errormessagediv').show();
		$('.validateTips').text("Transport Type already Exist.Make it Active..!!");
		document.getElementById("vehicleType").style.border = "1px solid #AF2C2C";
		document.getElementById("vehicleType").style.backgroundColor = "#FFF7F7";
		return false;
	}
	else {
		datalist = {
			"vehicleType" : vehicletype,
			"desc" : descname,
			"upd_vehicle_id":hiddenvechileid,
			"update_vehicle" : updatevehicle,
		},
		$.ajax({
			type : "POST",
			url : "transport.html?method=insertVehicleType",
			data : datalist,
			
			success : function(data) {
				var result = $.parseJSON(data);//Here result is variable of Object.
				if(result.status == "Success")//status is one variable inside Object returning from insertVehicleType().
					{
					    $("#addTransportcategory").dialog('close');
						$(".successmessagediv").show();
						$(".validateTips").text("Adding Record Progressing...");
					}
				else if(result.status == "UpdateSuccess")
					{
					    $("#addTransportcategory").dialog('close');
						$(".successmessagediv").show();
						$(".validateTips").text("Updating Record Progressing...");
					}
				else if(result.status == "UpdateFailure")
				{
					$("#addTransportcategory").dialog('close');
					$('.errormessagediv').show();
					$('.validateTips').text("Updating Record Failed.");
				}
				else
				{
					$("#addTransportcategory").dialog('close');
					$('.errormessagediv').show();
					$('.validateTips').text("Adding Record Failed.");
				}

				setTimeout(function() {
			         $(".select").prop("checked",false);
			         $("#selectall").prop("checked",false);
					transportCategoryListByStatus();
				}, 3000);
			}

		});
		return false;
	}

}

function validateVechicleType(){
	
	if($("#hiddenvechiletype").val()==$("#vehicleType").val())
	{
			status=0;
	}
	else
	{	
	var completeurl =$("#vehicleType").val();

	$.ajax({
		type : "POST",
		url : "transport.html?method=validateVehicleType",
		data : {   
			   'completeurl':	completeurl,
			   "id":$('#transporttypeid').val()
		  },
	 
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			
			if(result.vehi_available == "true")//vehi_available is one variable inside Object returning from validateVehicleType().
			{
				status=1;
			}
			else if(result.vehi_available == "inactive")
			{
				status=10;
			}
			else{
				status=0;
			}
		}
	});
	return status;
  }
}

function transportCategoryListByStatus(){
	var datalist = {
			"status" :$("#status").val(),
			"searchvalue" :$("#searchname").val(),
		}; 
		$.ajax({
			type : 'POST',
			url : "transport.html?method=transportCategoryListByStatus",
			data : datalist,
			success : function(data) {
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					var i=0;
					var len=result.list.length;
					if(len>0){
					for(i=0;i<len;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' value='"+result.list[i].vehicleId+"' id='"+result.list[i].vehicleId+","+result.list[i].vehicleType+","+result.list[i].description+"' /></td>"
							+"<td> "+result.list[i].vehicleType+" </td>"
							+"<td> "+result.list[i].description+"</td>"
							+"<td> "+result.list[i].remarks+" </td>"
							+"</tr>");
					    }
					}
					else{
						$("#allstudent tbody").append("<tr>" +"<td colspan='4'>No Records Founds</td>" +"</tr>");
					}
					checkboxsselect();
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. Of Records "+len);
					pagination(100);
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


function HideError1(pointer) 
{
document.getElementById(pointer.id).style.border = "1px solid #ccc";
document.getElementById(pointer.id).style.backgroundColor = "#fff";
}