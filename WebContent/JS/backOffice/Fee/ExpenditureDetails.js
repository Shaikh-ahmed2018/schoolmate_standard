function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
$(document).ready(function(){
	
	if($("#allstudent tbody tr").length==0){
		$("#allstudent tbody").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
	}
	
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0 ){
		hideError("#"+$(this).attr("id"));
		
		$(".errormessagediv").hide();
		}
	},
	});
	
	
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv1").hide();
	}, 3000);
	
	$(".class").click(function()
			 {
				$("#collapseOne").slideToggle();
			 });
	
	
	checkboxsselect()
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
	  getExpendeStatusList();
	  checkboxsselect()
  });
	
  getDataArray=[];
	$("#delete").click(function() 
  {	
		 //add array1
		var cnt = 0;

		$('.select:checked').each(function() {
			getDataArray.push($(this).val());
			cnt++;
		});
		if(cnt == 0){
			$(".errormessagediv1").show();
			$(".validateTips1") .text("Select any one reason");
		
		    } 
					else{
						$("#dialog").dialog("open");
						$("#dialog").empty();
						$("#dialog").append("<p>Are You Sure To "+$("#delete").text()+"?</p>");
						$("#dialog").append('<label>Reason:</label>');
						$("#dialog").append('<select name="feecanreason" id="feecanreason">'
								+ '<option value="">' + "----------select----------"
								+ '</option>'
								+ '<option value="Incorrect Entry">Incorrect Entry</option>'
								+ '<option value="Not In Use">' + "Not In Use" 
								+ '</option>'
								+ '<option value="others">' + "Others" 
								+ '</option>'+
						'</select>');
						
						
						
						$("#dialog").append('<select name="activereason" id="activereason">'
								+ '<option value="">' + "----------select----------"
								+ '</option>'
								+ '<option value="Correct Entry">' + "Correct Entry"
								+ '</option>'
								+ '<option value="In use">' + "In use" 
								+ '</option>'
								+ '<option value="others">' + "Others" 
								+ '</option>'+
						'</select>');
						
						  $("#dialog").append('<div id="othreason"><label for="">OtherReason:</label><input type="text" name=other id="otherreason"/></div>');
				     
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
		autoOpen  : false,
		modal     : true,
		title     : "Expenditure Details",
		buttons   : {
			'Yes' : function() {
				
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
					inactiveexpen(status,reason,getDataArray);
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
					   inactiveexpen(status,reason,getDataArray);
					   $(this).dialog("close");
					 }
				}
				else{
					inactiveexpen(status,reason,getDataArray);
					$(this).dialog("close");
				}
			},
		
			'No' : function() {

				$(this).dialog('close');
			}
		}
	});				
	
	
	$("#expenditureedit").click(function() {
				$(".successmessagediv").hide();
				 
				getData = $(".select:checked").attr("id");
		         var len =$(".select:checked").length;
		         
				if (len==0|| len > 1) {
					$(".errormessagediv1").show();
					$(".validateTips1").text("Select any one record");
					return false;
				} 
				
				else {
					
					window.location.href = "addExpenditure.html?method=editExpenditure&name="+getData;
				}
			});
	
	
	$("#search").click(function() 
			
			{
				var searchvalue=$('#searchvalue').val();
				window.location.href = "menuslist.html?method=expenditureDetailsList&searchvalue="+ searchvalue;
				
			});
					
	$('#excelDownload').click(function() {
				var searchvalue=$('#feesearchid').val();
				window.location.href = 'menuslist.html?method=expenditureDetailsXLS&searchvalue='+searchvalue;
				
			});
	
	$("#pdfDownload").click(function(){
		
		var searchvalue=$('#feesearchid').val();
		
		
		window.location.href = "menuslist.html?method=expenditureDetailsPDFReport&searchvalue="+searchvalue;
			
	});	

});

 function getExpendeStatusList(){

	 dataList={
				"status":$("#status").val()
				},
              
				$.ajax({
					type:'POST',
					url:'addfee.html?method=getExpendeStatusList',
					data:dataList,
					async:false,
					success: function(data){
						
						var result=$.parseJSON(data);
						$("#allstudent tbody").empty();
						 if(result.expndList.length>0){
							for(var i=0;i<result.expndList.length;i++){
								
								$("#allstudent tbody").append("<tr>" +
										"<td><input type='checkbox' id='"+result.expndList[i].expId+"' class='select' name='select' value='"+result.expndList[i].expId+"' /></td>" +
										"<td>"+result.expndList[i].locationname+"</td>" +
										"<td>"+result.expndList[i].expenditureTitle+"</td>" +
										"<td>"+result.expndList[i].amount+"</td>" +
										"<td>"+result.expndList[i].description+"</td>" +
										"<td>"+result.expndList[i].date+"</td>" +
										"<td>"+result.expndList[i].isActive+"</td>" +
										"<td>"+result.expndList[i].remark+"</td>" +
									"</tr>");
							}
						}
						else{
							$("#allstudent tbody").append("<tr><td colspan='8'>No Records Found</td></tr>");
						}
						 checkboxsselect();
							$(".numberOfItem").empty();
							$(".numberOfItem").append("No. of Records  "+result.expndList.length);
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
	
 function inactiveexpen(status,reason,getDataArray){
	 
	 datalist = {
			 'getDataArray':getDataArray.toString(),
			 'reason':reason,
			 'status':status,
			 'button':$("#delete").text()
			 }; 
		$.ajax({
			type : "GET",
			url : "addExpenditure.html?method=deleteExpenditure",
			data : datalist,
			async : false,

			success : function(response) {
				var result = $.parseJSON(response);

				if (result.jsonResponse == "true") {
					$(".successmessagediv").show();
					$(".sucessmessage") .text($("#delete").text()+" the record progressing...");

					$(".successmessagediv").delay(3000).slideUp("slow");
					/*setTimeout(function(){
									window.location.href = "menuslist.html?method=feeDetailsList&result="+result.jsonResponse;
									},3000);*/
				}
			
				setTimeout(function(){
					window.location.href = "menuslist.html?method=expenditureDetailsList";
				},2000);

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
	function hideError(id){
		$(id).css({
			"border":"1px solid #ccc",
			"background-color":"#fff"
			});
	}
	



	
	
	