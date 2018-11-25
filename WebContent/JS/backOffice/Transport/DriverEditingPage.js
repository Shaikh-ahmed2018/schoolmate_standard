function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
 

$(document).ready(function() {
	
	DriverListByStatus();
	
   checkboxsselect();
   
   $("#locationname").change(function(){
	   DriverListByStatus();
	   
   });
	
	$("#status").change(function(){
		 if($(this).val()=="Y"){ 
			$("#inactive").text("InActive");
			DriverListByStatus();
		}
		else{
			$("#inactive").text("Active");
			DriverListByStatus();
		 }
		 $("#selectall").prop("checked",false);
		}); 
	
	$("#search").click(function(){
		DriverListByStatus();
	});
	$("#resetbtn").click(function(){ 
		$("#status").val("active");
		$("#searchname").val("");
		DriverListByStatus();
	});
	/*$("#searchname").keyup(function(event) {
		 DriverListByStatus();
	});*/
	
	$("#searchname").keypress(function(event) {
		if (event.keyCode == 13) {
			  DriverListByStatus();
			}
	});
	
	$("#add").click(function(){
		window.location.href = "driverDetailsPath.html?method=addDriver&historylocId="+$("#locationname").val()+
	    "&historystatus="+$("#status").val()+"&historysearch="+$("#searchname").val();
    });
	
    $("#editdriver").click(function(){
		 var cnt = 0;
		 $('.select:checked').each(function() {
			 getData = $(this).attr("id");
			 cnt++;
		  });
		 if (cnt == 0 || cnt > 1) {
				$(".errormessagediv").show();
				$(".validateTips").text("Select Atleasr One Record");
				return false;
			} 
		 else
			 {
			 var driverCode = getData;
				window.location.href = "driverDetailsPath.html?method=editDriver&driverCode="+driverCode
				+"&historylocId="+$("#locationname").val()+"&historystatus="+$("#status").val()+
				"&historysearch="+$("#searchname").val();
			 }
   });

$("#delete").click(function(){
	var count =0;
	driverCode=[];
		$(".select:checked").each(function(){
		var list=$(this).attr("id");
		driverCode.push(list);
		count ++;
	 });	

	if(count == 0)	{
		$('.errormessagediv').show();
	$('.validateTips').text("Select Driver to Delete");
	}
	else 	{
	  $("#dialog").dialog("open");
	  $("#dialog").empty();
	  $("#dialog").append("<p>Are you sure to delete?</p>");
}

});

$("#dialog").dialog({
   autoOpen  : false,
   resizable : false,
   modal     : true,					    
   title     :'Driver Details',
   buttons   : {
  	 "Yes" : function() {
      		$.ajax({
					type : 'POST',
					url : "driverDetailsPath.html?method=deleteDriver",
					data : {"driverCode" :driverCode.toString()},
					success : function(response) {
						var result = $.parseJSON(response);
						$('.errormessagediv').hide();
						
						if(result.status=="Driver Deleted Successfully"){
							
							$(".successmessagediv").show();
							$(".validateTips").text("Deleting Record Progressing...");
						}
						
						else if(result.status=="Driver not Deleted Successfully"){
							$('.errormessagediv').show();
							$('.validateTips').text("Selected Driver is Mapped Cannot Delete");
						}else
							{
							$('.errormessagediv').show();
							$('.validateTips').text("Selected Driver is Mapped Cannot Delete");
							}
						setTimeout(function(){
							 window.location.href="menuslist.html?method=driverList";
						 },3000);
					}
				});
      		 $(this).dialog("close");
        },
        "No" : function() {
	            $(this).dialog("close");
	          }
   }
});

$('#inactive').click(function() {
	var count =0;
	driverCode=[];
	$(".select:checked").each(function(){
		var list=$(this).attr("id");
		driverCode.push(list);
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
		
		  $("#dialog").append('<div id="othreason"><label class="warningothers">OtherReason:</label><input type="text" style="width: 100%;" class="warningfont" name="other" maxlength="249" id="otherreason" onclick="HideError(this)"/></div>');
     
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
	modal: true,					    
	title:'Drivers Details',
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
				url : "driverDetailsPath.html?method=deleteDriver",
				data : {
					"driverCode" :driverCode.toString(),
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
						$(".validateTips").text($("#inactive").text()+" the Drivers Details Progressing...");
						$('.successmessagediv').delay(3000).slideUp();
					} else if(result.status == "false"){
						$(".errormessagediv").show();
						$(".validateTips").text("Selected Drivers Details is Not "+$("#inactive").text());
						$('.errormessagediv').delay(3000).slideUp();
					}
					setTimeout(function(){
						window.location.href="menuslist.html?method=driverList&currentstatus="+$("#status").val();
					},3000);
				}
			});  

			$(this).dialog("close");
			$("#selectall").prop("checked",false);
			driverCode=[];
		  }
		},
		"No" : function() {
			$(this).dialog("close");
		}
	}
});

$('#excelDownload').click(function() {
	window.location.href = 'driverDetailsPath.html?method=DriverDetailsXLS&status='+$("#status").val()+"&locId="+$("#locationname").val()+"&searchvalue="+$("#searchname").val()+"&statustext="+$("#status option:Selected").text()+"&loctext="+$("#locationname option:Selected").text();
});

$("#pdfDownload").click(function(){
	window.location.href = 'driverDetailsPath.html?method=DriverDetailsPDFReport&status='+$("#status").val()+"&locId="+$("#locationname").val()+"&searchvalue="+$("#searchname").val()+"&statustext="+$("#status option:Selected").text()+"&loctext="+$("#locationname option:Selected").text();
});	

		if($("#historylocId").val()!="" || $("#historystatus").val()=="N" || $("#historysearch").val()!="")
		{
			$("#locationname").val($("#historylocId").val());
			$("#status").val($("#historystatus").val());
			$("#searchname").val($("#historysearch").val());
			
			if($("#status").val()=="Y"){ 
				$("#inactive").text("InActive");
			}
			else{
				$("#inactive").text("Active");
			 }
			
			DriverListByStatus();
		}
		
		if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){
			 
			if($("#currentstatus").val()=="Y"){ 
				$("#status").val("N");	
				$("#inactive").text("Active");
				DriverListByStatus();
				}
				else{
				$("#status").val("Y");
				$("#inactive").text("InActive");
				DriverListByStatus();
			   }
		}
	
});

function DriverListByStatus(){
	 
	var datalist = {
			"status" :$("#status").val(),
			"searchvalue" :$("#searchname").val(),
			"locId" :$("#locationname").val(),
		}; 
		$.ajax({
			type : 'POST',
			url : "driverDetailsPath.html?method=driverListByStatus",
			data : datalist,
			
			success : function(data) {
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					var i=0;
					var len=result.list.length;
					if(len>0){
					for(i=0;i<len;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' value='"+result.list[i].driverCode+"' id='"+result.list[i].driverCode+"' /></td>"
							+"<td> "+result.list[i].driverName+" </td>"
							+"<td> "+result.list[i].dateofJoin+"</td>"
							+"<td> "+result.list[i].mobile+" </td>"
							+"<td> "+result.list[i].drivingliecenseNo+" </td>"
							+"<td> "+result.list[i].dl_validity+" </td>" 
							+"<td> "+result.list[i].remarks+" </td>"
							+"</tr>");
					    }
					$("#iconsimg").show();
					}
					else{
						$("#iconsimg").hide();
						$("#allstudent tbody").append("<tr>" +"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					checkboxsselect();
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. Of Records "+len+".");
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

function HideError(pointer) 
{
 $(".errormessagediv").hide();
document.getElementById(pointer.id).style.border = "1px solid #ccc";
document.getElementById(pointer.id).style.backgroundColor = "#fff";
}
	
	
	
	
	