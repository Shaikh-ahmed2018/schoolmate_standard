$(document).ready(function() {
	$("#startdate").prop("readonly",true);
	$("#startdate").datepicker({
		dateFormat : "dd-mm-yy",
		Date : 0,
		changeMonth : "true",
		changeYear : "true",
		onClose : function(selectedDate) {
			$("#todate").datepicker("option","Date",selectedDate);
			
			if($("#startdate").val()!="" && $("#enddate").val()!=""){
		    	 $("#accyearname").val("");
		    	 $("#accyearname").val($("#startdate").val().split("-")[2]+"-"+$("#enddate").val().split("-")[2]);
		     }
		}
	});

	$("#enddate").datepicker({
		dateFormat : "dd-mm-yy",
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true
	});
	
	     checkboxselect();
 
	      if($("#allstudent tbody tr").length==0){
		    $("#allstudent tbody").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
	      }
	      
	        $("#status").change(function(){
				 status=$("#status").val();
				 if($(this).val()=="Y"){ 
					$("#inactive").text("InActive");
					AcademicYeardetailsListBystatus(status);
				}
				else{
					$("#inactive").text("Active");
					AcademicYeardetailsListBystatus(status);
				 }
				 $("#selectall").prop("checked",false);
				});
	        
			$('.errormessagediv').hide();
			if ($('#accid').val() == null || $('#accid').val() == "") {

			} 
			else {

				$("#description").val($("#descriptionId").val().trim());
			}

			$("#back1").click(function(){
				window.location.href = "menuslist.html?method=academicyear&status="+$("#hiddenstatus").val();
			});

			$('#excelDownload').click(function() {

				var searchTerm = $("#searchexamid").val().trim();

				window.location.href = 'AcademicYearPath.html?method=AcademicYearPathDetailsXLS&searchTerm='+ searchTerm;

			});

			$("#pdfDownload").click(
					function() {
						var searchTerm = $("#searchexamid").val().trim();

						window.location.href = "AcademicYearPath.html?method=AcademicYearPathDetailsPDFReport&searchTerm="+ searchTerm;

					});	
			
		 if($("#hiddenstatus1").val()!="" && $("#hiddenstatus1").val()!=undefined){
			 $("#status").val($("#hiddenstatus1").val());
			 var status=$("#status").val();
			 if($("#status").val()=="Y"){ 
					$("#inactive").text("InActive");
				}
				else{
					$("#inactive").text("Active");
				 }
			 AcademicYeardetailsListBystatus(status);
		 }
		 
       if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){
	 		 if($("#currentstatus").val()=="Y"){ 
	 			 $("#status").val("N");
	 			$("#inactive").text("Active");
	 			AcademicYeardetailsListBystatus($("#status").val());
	 		}
	 		else{
	 			$("#status").val("Y");
	 			$("#inactive").text("InActive");
	 			AcademicYeardetailsListBystatus($("#status").val());
	 		 }
	 	}
       $(".addClass").click(function(){
    		$("#actionstatus").val("add");
       });
			
			$(".editDesignationId").click(function() {
				$("#actionstatus").val("editAction");
				
				var getData ={"Acy_Code":$(this).closest("tr").attr("id"),
						"status":$("#status").val()
				};
				
				$.ajax({
					type:'POST',
					url:'AcademicYearPath.html?method=editAcademicYear',
					data:getData,
					async:false,
					success:function(data){
						var result=$.parseJSON(data);
						
						$("#accyearname").val(result.editacademicyear[0].acadamic_name);
						$("#startdate").val(result.editacademicyear[0].startDate);
						$("#enddate").val(result.editacademicyear[0].endDate);
						$("#description").text(result.editacademicyear[0].description);
						$("#accid").val(result.editacademicyear[0].acadamic_id)
					}
				});
				

			});

			$("#list").click(function() {
				window.location.href = "menuslist.html?method=academicyear";
			});

			$("#searchname").keydown(function(event) {
				var searchText = $("#searchname").val();
				if (event.keyCode == 13) {
					window.location.href = "menuslist.html?method=academicyear&searchText="
						+ searchText;
				}

			});

			$("#search").click(function(){
				var searchText = $("#searchname").val().trim();
				window.location.href ="menuslist.html?method=academicyear&searchText="
					+ searchText;
			});
			
			

			$("#dialog").dialog({
				autoOpen: false,
				modal: true,
				resizable: false,
				title:'Academic Year Details',
				minheight:280,
				maxheight:260,
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
							url : "AcademicYearPath.html?method=deleteAcademicYear",
							data : {
								"accyearList":accyearList.toString(),
								"inactivereason":$("#inactivereason").val(), 
								"activereason":$("#activereason").val(),
								"otherreason":$("#otherreason").val(),
								"status":$("#inactive").text(),
								"sqlname":""
								
							},
							success : function(response) {
								var result = $.parseJSON(response);

								$('.errormessagediv').hide();
                            
								if (result.status ==true) {
									$(".successmessagediv").show();
									$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
									$(".validateTips").text($("#inactive").text()+" the Academic Year Details Progressing...");
									$('.successmessagediv').delay(3000).slideUp();
								} else if(result.status ==false){
									$(".errormessagediv").show();
									$(".validateTips").text("Selected Academic Year Details is Not "+$("#inactive").text());
									$('.errormessagediv').delay(3000).slideUp();
								}
								else{
									$(".errormessagediv").show();
									$(".validateTips").text("Selected Academic Year Details is Mapped Cannot InActive");
									$('.errormessagediv').delay(3000).slideUp();
								}
								setTimeout(function(){
									window.location.href="menuslist.html?method=academicyear&status="+$("#hiddenstatus").val()
									+"&currentstatus="+$("#status").val();
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
			

			$(".errormessagediv").hide();


			$("#save").click(function() {
				var accyear = $('#accyearname').val();
				var startdate = $('#startdate').val();
				var enddate = $('#enddate').val();
				var description = $('#description').val();
				var accid = $('#accid').val().trim();

				if (accyear == "" || accyear == null) {
					$('.errormessagediv').show();
					$('.validateTips').text("Enter Academic Year");
					document.getElementById("accyearname").style.border = "1px solid #AF2C2C";
					document.getElementById("accyearname").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);

					return false;
				} 

				else if (!accyear.match("^(?=.*?[1-9])[0-9()-]+$")) {
					$('.errormessagediv').show();
					$('.validateTips').text("Enter Alpha Numeric");
					document.getElementById("accyearname").style.border = "1px solid #AF2C2C";
					document.getElementById("accyearname").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}

				else if (startdate == ""|| startdate == null) {
					$('.errormessagediv').show();
					$('.validateTips').text("Enter Start Date");
					document.getElementById("startdate").style.border = "1px solid #AF2C2C";
					document.getElementById("startdate").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					},3000);
					return false;
				} else if (enddate == "" || enddate == null) {
					$('.errormessagediv').show();
					$('.validateTips').text("Enter End Date");
					document.getElementById("enddate").style.border = "1px solid #AF2C2C";
					document.getElementById("enddate").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else if (checkName() == 10) {
					 
					$('.errormessagediv').show();
					$('.validateTips').text("Academic Year already Exist!! Make it Active");
					document.getElementById("accyearname").style.border = "1px solid #AF2C2C";
					document.getElementById("accyearname").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else if (checkName() == 11) {
					 
					$('.errormessagediv').show();
					$('.validateTips').text("Academic Year already Exist with this date!! Make it Active");
					document.getElementById("accyearname").style.border = "1px solid #AF2C2C";
					document.getElementById("accyearname").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else if (checkName() == 1) {
					 
					$('.errormessagediv').show();
					$('.validateTips').text("Academic Name or Dates Already Exists");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				} 
				else {
					if (accid == "" || accid == null) {
						accid = "NULL";
					}
					if($("#actionstatus").val()=="editAction")
						$('.validateTips').text("Update Academic Year Details Progressing...");
						else
						$('.validateTips').text("Add Academic Year Details Progressing...");
					var Check = {
							"accyear" : accyear,
							"startdate" : startdate,
							"enddate" : enddate,
							"description" : description,
							"accid" : accid
					};
					$.ajax({
						type : "POST",
						url : "AcademicYearPath.html?method=createAcademicYear",
						data : Check,
						async : false,
						success : function(data) {
							var result = $
							.parseJSON(data);
							if (result.status == "success") {
								$('.successmessagediv').show();
									location.reload();
							} else {
								$('.errormessagediv').show();
								$('.validateTips').text("Failed to Add Academic Details! Try Again...");
							}
						}
					});
				}
			});

			function checkName() {
				accid = $("#accid").val().trim();
				if (accid == "" || accid == null) {
					accid = "NULL";
				}
				var checkspecializationName = {
						"accyearname" : $("#accyearname").val(),
						"startdate" : $("#startdate").val(),
						"enddate" : $("#enddate").val(),
						"accid" : accid,
						"action":$("#actionstatus").val()
				};
				var status = 0;

				$.ajax({
					type : "POST",
					url : "AcademicYearPath.html?method=accyearNameCheck",
					data : checkspecializationName,
					async : false,
					success : function(data) {

						var result = $.parseJSON(data);
						text = result.status;
						if (text == "inactive1") {
							status = 10;
						}
						else if (text == "inactive2") {
							status = 11;
						}
						else if (text == "Academic Year already Exist" || text == "Academic year already created using this dates") {
							status = 1;
						}
						else
						{
							status= 0;
						}
					}
				});

				return status;

			}
$(".switch-cirle").click(function(){
	if($("#status").val()=="Y"){
		$("#inactive").text("InActive");
		$("#status").val("N");
		$(this).css({
			"left":"60px",
			
			
		});
		
		$("#text-status").css({"left":"7px"});
		$("#text-status").text("Inactive");
		AcademicYeardetailsListBystatus($("#status").val());
	}
	else{
		$("#inactive").text("Active");
		$("#status").val("Y");
		$(this).css({
			"left":"1px",
			
		});
		
		$("#text-status").css({"left":"30px"});
		$("#text-status").text("Active");
		AcademicYeardetailsListBystatus($("#status").val());
	}
});
		});

function setEndDate() {

	var startDate = $('#startdate').val();

	startDate = startDate.split("-");

	var d = new Date(startDate[2], startDate[1] - 1, startDate[0]);

	d.setFullYear(d.getFullYear() + 1);
	d.setDate(d.getDate() - 1);

	var str = (d.getMonth() + 1).toString();
	var pad = "00";

	pad.substring(0, pad.length - str.length) + str;

	var ee = d.getDate() + "-" + pad.substring(0, pad.length - str.length)
	+ str + "-" + d.getFullYear();
	$('#enddate').val(ee);

	accid = $("#accid").val().trim();

	if (accid == "" || accid == null) {
		accid = "NULL";
	}
	var checkName = {
			"accyearname" : $("#accyearname").val(),
			"startdate" : $("#startdate").val(),
			"enddate" : $("#enddate").val(),
			"accid" : accid
	};
	var status = false;
	$
	.ajax({
		type : "POST",
		url : "AcademicYearPath.html?method=accyearNameCheck",
		data : checkName,
		async : false,
		success : function(data) {

			var result = $.parseJSON(data);
			text = result.status;
			if (text == "Academic Year already Exist") {
				$('.errormessagediv').show();
				$('.validateTips').text("Academic Year already Exist using this Dates");
				$('.errormessagediv').delay(3000).fadeOut("slow");
				status = true;
			} else if (text == "Academic year already created using this dates") {
				$('.errormessagediv').show();
				$('.validateTips').text("Academic year already created using this dates");
				$('.errormessagediv').delay(3000).fadeOut("slow");
				status = true;
			}
		}
	});

	return status;

}
function AcademicYeardetailsListBystatus(status){
	var sts="Inactive"
		if(status=="N"){
			sts="Active"
		}
		else{
			sts="Inactive"
		}
	$.ajax({
		type : 'POST',
		url : "AcademicYearPath.html?method=AcademicYeardetailsListBystatus",
		data : {
			"status" :status,
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
						+"<td>"+result.list[i].sno+"</td>" 
						+"<td> "+result.list[i].acadamic_name+" </td>"
						+"<td> "+result.list[i].startDate+" </td>"
						+"<td> "+result.list[i].endDate+" </td>"
						+"<td> "+result.list[i].description+" </td>"
					
						+"<td class='actiontd'>  </td>"
						+"</tr>");
				  }	
			
				
				if($("#editPermission").val()=="true"){
					$("td.actiontd").each(function(){
						$(this).append('<span  data-toggle="modal" data-target="#myModal" class="btn btn-xs btn-primary margin-t-5 editDesignationId" title="Edit"><span class="glyphicon glyphicon-edit"></span> Edit</span>');
					});
				}
				if($("#delPermission").val()=="true"){
					$("td.actiontd").each(function(){
						$(this).append('<span data-toggle="modal" data-target="#inActiveModel" class="btn btn-xs btn-primary margin-t-5 inactive" title="Active/Inactive"  onclick="inactive(this);"><span class="glyphicon glyphicon-link"></span> '+sts+'</span>');
					});
				}
				
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.list.length);
				pagination(100);
			}
				else{
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  0");
					pagination(100);
					$("#allstudent tbody").append("<tr><td colspan='7'>No Records Found</td></tr>");
				}
			checkboxselect();
			
		}
	});
} 

function checkboxselect(){
	$("#selectall").change(function(){
		$(".select").prop("checked",$(this).prop("checked"));
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

function inactive(element){
	$("p.warningfont").text("Are you sure to "+$("#inactive").text()+"?");
	$("#warningreason").text('*Warning If you Inactivate this Academic Year, you won\'t be able to view details related to Academic Year.');
	
 
  		 
  
}

function message(){
	
	
	
	
}
function HideError(pointer) 
{
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";

}