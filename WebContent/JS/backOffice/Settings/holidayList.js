function myFunction() {
	    
        document.getElementById("myForm").submit();   
	  }
$(document).ready(function(){
	
	if($("#hiddenstatus").val()!="" && $("#hiddenstatus").val()!=undefined){
		$("#status").val($("#hiddenstatus").val()); 
	}
	   
	
	$("#Acyearid").val($("#hiddenAcademicYear").val());
	
	if($("#allstudent tbody tr").length==0){
		$("#allstudent tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
		$(".numberOfItem").empty();
		$(".numberOfItem").append("No. of Records "+$("#allstudent tbody tr").length);
		pagination(100);
	}
	$("select[name='location']").val($("#searchexamid").val());

	checkboxsselect();
	
	/*if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined)
	 {
		 if($("#currentstatus").val()=="Y")
		 { 
			 $("#editspec").hide();
			 $("#status").val("N");
			$("#inactive").text("Active");
			HolidayListByStatus();
		}
		else{
			$("#editspec").show();
			$("#status").val("Y");
			$("#inactive").text("InActive");
			HolidayListByStatus();
		 }
	 }*/
	
	if($("#historylocId").val()!=""){
		
		$("#locId").val($("#historylocId").val());
		if($("#historyacyearId").val()!=""){
			$("#Acyearid").val($("#historyacyearId").val());
		}
		$("#status").val($("#historystatus").val());
		if($("#status").val()=="Y"){ 
			$("#editspec").show();
			
			$("#inactive").text("InActive");
		}
		else{
			
			$("#editspec").hide();
			$("#inactive").text("Active");
		    }
		HolidayListByStatus();
	}else{
		HolidayListByStatus();
	}
	
	$("#status").change(function(){
		  
		 if($(this).val()=="Y"){ 
			$("#inactive").text("InActive");
			HolidayListByStatus();
			$("#editspec").show();
		}
		else{
			$("#inactive").text("Active");
			HolidayListByStatus();
			$("#editspec").hide();
		    }
		 $("#selectall").prop("checked",false);
	});
	
	$("#locId").change(function(){
		HolidayListByStatus();
		$("#selectall").prop("checked",false);
	});
	
	$("#Acyearid").change(function(){
		HolidayListByStatus();
		$("#selectall").prop("checked",false);
	});
	
	$("#editspec").click(function(){
		var cnt = 0;
		var holiday_Code = null;

		$('.select:checked').map(function() {
			holiday_Code = $(this).attr("id");
			cnt++;
		});
		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
				$(".validateTips").text("Select any one Holiday");
				return false;
		} else {
			$(".errormessagediv").hide();
			window.location.href="holidayMaster.html?method=editHolidayMaster&deptId="+holiday_Code+"&locId="+$("#locId").val()+"&acyearId="+$("#Acyearid").val()+"&status="+$("#status").val();
		}
	});
	
	$("#inactive").click(function(){
		holidayList=[];
		var count =0;
		$(".select:checked").each(function(){
			var Ids=$(this).attr("id");
			holidayList.push(Ids);
			count ++;
		});	
		 
		if(count == 0)	{
			$('.errormessagediv').show();
			$('.validateTips').text("Select Records to "+$("#inactive").text());
			$('.errormessagediv').delay(3000).slideUp();
		}
		else{
				if($(this).text() == "InActive"){
					$("#butstatus").val("N")
				}else{
					$("#butstatus").val("Y")
				}
			
				$("#dialog").dialog("open");
				$("#dialog").empty();
				$("#dialog").append("<p class='warningfont' >Are you sure to "+$("#inactive").text()+"?</p>");
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
	  				$("#activereason").hide();
	  				$("#inactivereason").hide();
	  				$("#activereason").show();
	  			}
		  		});
		  	  reason = $("#inactivereason").val();
		}
	});

	$("#dialog").dialog({
		autoOpen: false,
		modal: true,					    
		title:'Holiday Details',
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
					url : "holidayMaster.html?method=deleteHolidayData",
					data : {
						"holidayList":holidayList.toString(),
						"inactivereason":$("#inactivereason").val(), 
						"activereason":$("#activereason").val(),
						"otherreason":$("#otherreason").val(),
						"status":$("#inactive").text(),
						
					},
					success : function(response) {
						var result = $.parseJSON(response);

						$('.errormessagediv').hide();

						if (result.status =="true") {
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
							$(".validateTips").text($("#inactive").text()+" the Holiday Details Progressing...");
							$('.successmessagediv').delay(3000).slideUp();
						} else if(result.status =="false"){
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Holiday Detail is Not "+$("#inactive").text());
							$('.errormessagediv').delay(3000).slideUp();
						}
						else{
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Holiday Detail is Mapped Cannot InActive");
							$('.errormessagediv').delay(3000).slideUp();
						}
						setTimeout(function(){
							window.location.href="menuslist.html?method=holidaymaster&historystatus="+$("#butstatus").val()+"&historylocId="+$("#locId").val();
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
	
});

function HolidayListByStatus(){
	var status=$("#status").val();
	var locationname=$("#locId").val();
	var Acyearid=$("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "holidayMaster.html?method=HolidayListByStatus",
		data : {
			"status" :status,
			"locationname":locationname,
			"Acyearid":Acyearid
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.list.length;
			
			if(len > 0){
				for(i=0;i<len;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td><input type='checkbox' class='select' name='select' id='"+result.list[i].holId+"'/></td>" 
						+"<td> "+result.list[i].locName+" </td>" 
						+"<td> "+result.list[i].date+" </td>"
						+"<td> "+result.list[i].holidaysName+" </td>"
						+"<td> "+result.list[i].holidayType+" </td>"
						+"<td> "+result.list[i].remarks+" </td>"
						+"</tr>");
				  }	
			}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
				}
			checkboxsselect();
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.list.length);
			pagination(100);
		}
	});
} 

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
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