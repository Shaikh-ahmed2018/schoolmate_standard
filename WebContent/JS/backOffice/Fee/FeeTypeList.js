$(document).ready(function(){
	$("input,select,textarea").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv1").hide();
		}
	},
	/*change: function(){
		if(this.value.length>0 ){
		hideError("#"+$(this).attr("id"));
		
		$(".errormessagediv1").hide();
		}
	},*/
	});
	
	 
	getStatusList();
	 
	if(parseInt($("#hiddenlength").val())==0){
		
		$("#allstudent tbody").append("<tr><td ColSpan='5'>No Records Found</td></tr>");
	}
	
	
	
	checkboxsselect();
		reason=null;
		var status = "N";
	  $("#status").change(function(){
		
		  if(this.value=="Y"){
			  $("#delete").text("InActive"); 
			  status = 'N';
		  }
		  else{
			  $("#delete").text("Active"); 
			  status = 'Y';
		  }
		  
		  getStatusList();
		  checkboxsselect()
	  });
	  
	  	
	  
	$("#feeedit").click(function(){
		
		feeid = $(".select:checked").val();
 
		 var len =$(".select:checked").length;
		if(len==0){
			 $(".errormessagediv1").show();
			 $(".validateTips1").text("Select any one record");
			 setTimeout(function() {
	  				$('#errormessagediv').fadeOut();
	  			    },3000);
			return false;
		}else if(len >1){
			 $(".errormessagediv1").show();
			 $(".validateTips1").text("Select any one record");
			 setTimeout(function() {
	  				$('#errormessagediv').fadeOut();
	  			    },3000);
			 
			return false;
		}
		
		if(feeid == "" || feeid == undefined){
			 $(".errormessagediv1").show();
			 $(".validateTips1").text("Select any one record");
			 setTimeout(function(){
					$(".errormessagediv1").hide();
				},2000);
		}else if($(".select:checked").length >1){
			 $(".errormessagediv1").show();
			 $(".validateTips1").text("Select any one record");
			 setTimeout(function(){
					$(".errormessagediv1").hide();
				},2000);
  
		}else{
			window.location.href = 'addfee.html?method=getFeeTypeById&feetypeid='+feeid;
		}
		
	});
	
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='5'>NO Records Found</td></tr>");
	}
	feeid = [];

	$("#delete").click(function(){
		
				count =0;
		$(".select:checked").each(function(){
			feeid.push($(this).val());
			count++;
		});
		
		if(count == 0){
			 $(".errormessagediv1").show();
			 $(".validateTips1").text("Select any one record");
			 setTimeout(function(){
					$(".errormessagediv1").hide();
				},2000);
		
		}
		else {
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure To "+$("#delete").text()+"?</p>");
			$("#dialog").append('<label>Reason:</label>');
			
			$("#dialog").append('<select name="feecanreason" id="feecanreason" onchange="HideError(this)">'
					+ '<option value="">' + "----------select----------"
					+ '</option>'
					+ '<option value="Incorrect Entry">Incorrect Entry</option>'
					+ '<option value="Not In Use">' + "Not In Use" 
					+ '</option>'
					+ '<option value="others">'+"Others" 
					+ '</option>'+
			'</select>');
			
			
			$("#dialog").append('<select name="activereason" id="activereason" onchange="HideError(this)">'
					+ '<option value="">' + "----------select----------"
					+ '</option>'
					+ '<option value="Correct Entry">' + "Correct Entry"
					+ '</option>'
					+ '<option value="In use">' + "In use" 
					+ '</option>'
					+ '<option value="others">' + "Others" 
					+ '</option>'+
			'</select>');
			
			  $("#dialog").append('<div id="othreason"><label for="">OtherReason:</label><input type="text" name=other id="otherreason" onclick="HideError(this)"/></div>');
	     
			  $("#othreason").hide();
	  		  $("#activereason").hide();
	  		  $('#feecanreason').change(function(){
	  			$(".errormessagediv1").hide();
	  			var othersres=$('#feecanreason').val();
	  			if(othersres == 'others'){
	  				
	  				$("#othreason").show(); 
	  				$("#activereason").hide();
	  			}else{
	  				$("#otherreason").val("");
	  				$("#othreason").hide();
	  				$("#feecanreason").show();
	  				$("#activereason").hide();
	  			}
	  			 reason = $("#feecanreason").val();
	  		});
	  		  
	  		if($("#status").val()=="N"){
 				$("#othreason").hide();
 				$("#activereason").show();
 				$("#feecanreason").hide();
 			}
	  		$('#activereason').change(function(){
	  		if($(this).val() == 'others'){
 				$("#othreason").show(); 
 				$("#activereason").show();
 				$("#feecanreason").hide();
 			}
	  		else{
  				$("#otherreason").val("");
  				$("#othreason").hide();
  				$("#feecanreason").hide();
  				$("#activereason").show();
  			}
	  		reason = $("#activereason").val();
	  		});
	  	  
	    	 
		}
		
	});
		$("#dialog").dialog({
			autoOpen: false,
			modal: true,					    
			title:'Fee Type',
			buttons : {
				"Yes" : function() {
					
					var inactivereason=$("#feecanreason").val();
					 var activereason=$("#activereason").val();
					 
	                 if($("#delete").text()=="Active" && activereason.length == 0)
	               	  {
	    				 showError("#activereason","Select Any One Reason");
	    				 return false;
	               	  }
	                 else if($("#delete").text()=="InActive" && inactivereason.length == 0){
	                	 showError("#feecanreason","Select Any One Reason");
	    					return false;
	                 }
	                 else if($("#delete").text()=="InActive" && $("#feecanreason").val() == "others"){
	                	 if($("#otherreason").val().length==0 ) {
	                		 showError("#otherreason","Field Required OtherReason");
		    					return false;   	 }
	                	 else{
						reason = $("#otherreason").val();
						saveDetails(status,reason,feeid);
						$(this).dialog("close");
						
	                	 }
					}
	                 
					else if ($("#delete").text()=="Active" &&$("#activereason").val()=="others") {
						
						 if($("#otherreason").val().length==0 ){
							 showError("#otherreason","Field Required OtherReason");
				     		    return false;
	                	 }
						 else{
							
						reason = $("#otherreason").val();
						
						saveDetails(status,reason,feeid);
						$(this).dialog("close");
						 }
					}
					else{
						saveDetails(status,reason,feeid);
						$(this).dialog("close");
					}
				},
                
				"No" : function() {
					$(this).dialog("close");
				}
				}
			
		});
});

function getStatusList(){
	datalist={
			"status" :$("#status").val()
		},
		$.ajax({
			type : 'POST',
			url : "addfee.html?method=getStatusList",
			data : datalist,
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				$("#feetable").empty();
				$("#feetable").append("<table class='table' id='allstudent'><thead><tr><th><input type='checkbox' name='selectall' id='selectall'/></th><th>Fee Type ID</th><th>Fee Type</th><th>Status</th><th>Remark</th></tr></thead><tbody></tbody></table>");
			    if(result.FeeTypeList.length>0){
				for ( var i = 0; i <result.FeeTypeList.length; i++) {
					$("#allstudent tbody").append("<tr>"+
							"<td><input type='checkbox' name='select' class='select' style='text-align:center;' value="+result.FeeTypeList[i].sno+" /></td>"+
							"<td>"+result.FeeTypeList[i].feeId+"</td>"+
							"<td>"+result.FeeTypeList[i].feename+"</td>"+
							"<td>"+result.FeeTypeList[i].status+"</td>"+
							"<td>"+result.FeeTypeList[i].remark+"</td>"+
							"</tr>"
					);
				}
			    }
			    else{
			    	
			    	$("#allstudent tbody").append("<tr><td colspan='5'><center> No Record Found</center></tr>");
			    }
			    pagination(100);
			    $(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.FeeTypeList.length);
				
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


function saveDetails(status,reason,feeid){

	$.ajax({
		type : "POST",
		url : "addfee.html?method=deletefeetype",
		data : {'feetypeid':feeid,
			'reason':reason,'status':status,'button':$("#delete").text()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			if(result.status == "success"){
			
				$(".successmessagediv").show();
				$(".sucessmessage").text($("#delete").text()+" the record progressing...");
				setTimeout(function(){
					$(".successmessagediv").hide();
					window.location.href= "menuslist.html?method=feeTypeList";
				},2000);
			}

        	else{
				$(".errormessagediv1").show();
				$(".validateTips1").text("Failed to"+" " + $("#delete").text()+" "+ " the record, try again...");
				setTimeout(function(){
					$(".errormessagediv1").hide();
				},2000);
			}
		}
	}); 
	
}

function showError(id,errorMessage){
	$(id).focus();
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv1").show();
	$(".validateTips1").text(errorMessage);
	$(".errormessagediv1").delay(3000).fadeOut();
}

/*function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}*/

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}