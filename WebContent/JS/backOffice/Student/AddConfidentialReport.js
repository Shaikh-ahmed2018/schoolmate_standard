$(document).ready(function() {
	reportHistory();
	
	var StudentImage=$("#photohiddenid").val().trim();
	
	
	

	$('#back1').click(function(){
		window.location.href="menuslist.html?method=studentConfidentialReport&historyaccYear="+$("#historyaccYear").val()+
		"&historylocId="+$("#historylocId").val()+"&historyclassname="+$("#historyclassname").val()+"&historysectionid="+$("#historysectionid").val()+
		"&historysearchvalue="+$("#historysearchvalue").val()+"&historystatus="+$("#historystatus").val();
	});
	
	if(StudentImage!=""){
		$("#imagePreview").show();
		$('#imagePreview').attr('src', StudentImage);
	}
	
	$('#reportHistory').click(function(){
		reportHistory();
	});
	
	$('#contacts').click(function(){
		showContactDetails();
	});
	
	$("#deleteHistory").click(function(){
		showDeleteHistory();
	});
	$("#back").click(function(){
		if($("#hiddenbackId").val()=="searchb"){
			 window.location.href = "menuslist.html?method=individualStudentSearch&studentId="+$("#hstudentid").val()+"&accyear="+$("#hacademicYearId").val()+"&locationId="+$("#hschoolNameId").val();
		}else if($("#hiddenbackId").val()=="mis"){
			 window.location.href = "menuslist.html?method=individualMisreport&studentId="+$("#hstudentid").val()+"&accyear="+$("#hacademicYearId").val()+"&locationId="+$("#hschoolNameId").val();
		}else{
			window.location.href="menuslist.html?method=studentConfidentialReport";
		}
	});
	
	$('#addconfidential').click(function() {
		$("#admissionDialog").dialog("open");
	});
	
	$("#allstudent tbody td .edit").click(function(){
		editid=$( this ).parent("td").parent("tr").find(".splitid").attr("class").split(" ");
		$('#editentryDate').val(editid[1]);
		$('#editcomment').val($(this).closest('tr').find('.comments').text());
		$("#editDialog").dialog("open");
	});
	$('#allstudent tbody td .delete').click(function(){
		deletevalues=$( this ).parent("td").parent("tr").find(".splitid").attr("class").split(" ");
		deleteid=deletevalues[0];
		$("#deleteDialog").dialog("open");
		 dispalyDialog($("#deleteDialog"));
	});
	

		$("#entryDate").datepicker({
			maxDate:0,
			dateFormat : "dd-mm-yy",
			yearRange : 'c-65:c+65',
			changeMonth : "true",
			changeYear : "true",
			yearRange : '1997:' + (new Date).getFullYear(),
			onClose : function(selectedDate) {
				$('#entryDate').datepicker('getDate');
			}
		});

		var dNow = new Date();
		var Day = dNow.getDate();
		if (Day < 10) {
			Day = '0' + Day;
		} // end if
		var Month = dNow.getMonth() + 1;
		if (Month < 10) {
			Month = '0' + Month;
		}

		var localdate = Day + '-' + Month + '-'
				+ dNow.getFullYear();
		$("#entryDate").val(localdate);
	
	$("#editentryDate").datepicker({
		maxDate:0,
		dateFormat : "dd-mm-yy",
		yearRange : 'c-65:c+65',
		changeMonth : "true",
		changeYear : "true",
		yearRange : '1997:' + (new Date).getFullYear(),
		onClose : function(selectedDate) {
			var date2 = $('#editentryDate').datepicker('getDate');
			date2.setDate(date2.getDate() + 1);

		}
	});
	
	$("#admissionDialog").dialog({
	    autoOpen  : false,
	    resizable: false,
	    maxWidth  :	750,
        maxHeight : 500,
        width     : 750,
        height    : 290,
	    modal     : true,
	    title     : "New Disciplinary Action",
	    buttons   : {
	    	'Save'  : function() {
	    		
	    		var entryDate = $("#entryDate").val();
	    		var comment = $("#comment").val();
	    		
    			 if(entryDate == "" || entryDate == null){
    				 $(".errormessagediv").show();
    					$(".validateTips").text("Select Date");
    					document.getElementById("entryDate").style.border = "1px solid #AF2C2C";
    					document.getElementById("entryDate").style.backgroundColor = "#FFF7F7";
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
    			 }
    			 else if(comment.trim() == "" || comment == null){
    				 $(".errormessagediv").show();
    					$(".validateTips").text("Field Required - Comments*");
    					document.getElementById("comment").style.border = "1px solid #AF2C2C";
    					document.getElementById("comment").style.backgroundColor = "#FFF7F7";
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
    			 }
    			 else{  
    				 setTimeout(function() {
 						location.reload();
 					}, 3000);
    				 $(this).dialog('close');
    				 addConfidetial();
    			 }
    			 
	    	}
			
	    }
	});
	
	$("#editDialog").dialog({
	    autoOpen  : false,
	    resizable: false,
	    maxWidth  :	750,
        maxHeight : 500,
        width     : 750,
        height    : 290,
	    modal     : true,
	    title     : "Modify Confidential",
	    buttons   : {
	    	'Save'  : function() {
	    	
	    		 var currentId = editid[0];
	    		 var currentDate = $("#editentryDate").val();
	    		 var CurrentComments = $("#editcomment").val();
	    		
    			 if(currentDate == "" || currentDate == null){
    				 $(".errormessagediv").show();
    					$(".validateTips").text("Select Date");
    					document.getElementById("editentryDate").style.border = "1px solid #AF2C2C";
    					document.getElementById("editentryDate").style.backgroundColor = "#FFF7F7";
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
    			 }
    			 else if(CurrentComments == "" || CurrentComments == null){
    				 $(".errormessagediv").show();
    					$(".validateTips").text("Field Required - Comments*");
    					document.getElementById("editcomment").style.border = "1px solid #AF2C2C";
    					document.getElementById("editcomment").style.backgroundColor = "#FFF7F7";
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
    			 }
    			 else{  
    				 setTimeout(function() {
  						location.reload();
  					}, 3000);
    				 $(this).dialog('close');
    				 editConfidetial(currentId,currentDate,CurrentComments);
    				
    			 }
    			
	    	}
	    	
	    }
	});
	
	$("#deleteDialog").dialog({
		autoOpen  : false,
		resizable : false,
		modal     : true,
		title     : "Remove Report",
		buttons   : {
			'Yes'  : function() {
				 deleteReport(deleteid);
				$(this).dialog('close');
			},
			'No' : function() {
				$(this).dialog('close');
				$(".errormessagediv").hide();
			}
		}
	});  
	
	$("#activeDialog").dialog({
		autoOpen  : false,
		resizable : false,
		modal     : true,
		title     : "Active the Confidentioal",
		buttons   : {
			'Yes'  : function() {
				if($("#otherreason").val()=="" || $("#otherreason").val()==null || $("#otherreason").val()==undefined){
					$(".errormessagediv").show();
					$(".validateTips").text("Select Date");
					document.getElementById("entryDate").style.border = "1px solid #AF2C2C";
					document.getElementById("entryDate").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				}else{
					ActivedeleteReport(deletevalues);
					$(this).dialog('close');
				}
			},
			'No' : function() {
				$(this).dialog('close');
				$(".errormessagediv").hide();
			}
		}
	});
});

	function showContactDetails(){
	 
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hschoolNameId').val();
		
		
		$('#studenttable').show();
		$('#individualstudenttable').hide();
		$("#deleteHistorytable").hide();
		$('#studenttable').empty();
		$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Relationship</th>" +
				"<th>Name</th>" +
				"<th>Mobile No</th>"+
				"</center></tr>" +
				"</table>"
		);
		
		$.ajax({
			
			type : "POST",
			url : "studentRegistration.html?method=singleStudentDetail",
			data : {"studentId":studentId,
					"accyear":accyear,
					"locationId":locationId,
					"flag":"contacts"
			},
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				
				if(result.studentSearchList.length>0){
				for (var j = 0; j < result.studentSearchList.length; j++) {
			
					$("#studenttable #allstudent").append(
							"<tr>"
							+"<td>1</td>" 
							+"<td>Father</td>"
							+"<td> "+result.studentSearchList[j].fatherName+"</td>"
							+"<td> "+result.studentSearchList[j].fatherMobileNo+" </td>"
							+"</tr>"
							+"<tr>"
							+"<td>2</td>" 
							+"<td>Mother</td>"
							+"<td> "+result.studentSearchList[j].motherName+"</td>"
							+"<td> "+result.studentSearchList[j].motherMobileNo+" </td>"
							+"</tr>"
							+"<tr>"
							+"<td>3</td>" 
							+"<td>Guardian</td>"
							+"<td> "+result.studentSearchList[j].gaurdianName+"</td>"
							+"<td> "+result.studentSearchList[j].guardianMobileNo+" </td>"
							+"</tr>"
						);
					}	
				}
				else{
					$("#studenttable #allstudent").append("<tr><td colspan='4'>NO Records Found</td></tr>");
				}
				
			}
			
		});
	}
	
	function reportHistory(){
		
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hschoolNameId').val();
		
		$('#individualstudenttable').show();
		$('#studenttable').hide();
		$("#deleteHistorytable").hide();
		
		$('#individualstudenttable').empty();
		$("#individualstudenttable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Entry Date</th>" +
				"<th>Remarks</th>" +
				"</center></tr>" +
				"</table>"
		);
		
		$.ajax({
			
			type : "POST",
			url : "studentRegistration.html?method=singleStudentDetail",
			data : {"studentId":studentId,
					"accyear":accyear,
					"locationId":locationId,
					"flag":"history"
			},
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				if( result.studentSearchList.length>0){
					
				
				for (var j = 0; j < result.studentSearchList.length; j++) {
			
					$("#individualstudenttable #allstudent").append(
							"<tr>"
							+"<td class='"+result.studentSearchList[j].confidentialId+" "+result.studentSearchList[j].confidentialEntryDate+" "+result.studentSearchList[j].confidentialComments+" "+"splitid"+"'>"+result.studentSearchList[j].count+"</td>"
							+"<td> "+result.studentSearchList[j].confidentialEntryDate+" </td>"
							+"<td ><span class='comments'>"+result.studentSearchList[j].confidentialComments+"</span><span class='edit buttons' id='"+result.studentSearchList[j].confidentialId+"'>Modify</span>&nbsp;&nbsp;&nbsp;<span class='delete buttons' id='"+result.studentSearchList[j].confidentialId+"'>Remove</span></td>"
							+"</tr>"
						);
					
					if($("#hiddendelete").val().trim()!="true"){
		        		$(".delete").hide();
		        	}else{
		        		$(".delete").show();
		        	}
					if($("#hiddenedit").val().trim()!="true"){
		        		$(".edit").hide();
		        	}else{
		        		$(".edit").show();
		        	}
					
					$("#allstudent tbody td .edit").click(function(){
						editid=$( this ).parent("td").parent("tr").find(".splitid").attr("class").split(" ");
						$('#editentryDate').val(editid[1]);
						$('#editcomment').val($(this).closest('tr').find('.comments').text());
						$("#editDialog").dialog("open");
					});
					$('#allstudent tbody td .delete').click(function(){
						deletevalues=$( this ).parent("td").parent("tr").find(".splitid").attr("class").split(" ");
						deleteid=deletevalues[0];
						$("#deleteDialog").dialog("open");
						 dispalyDialog($("#deleteDialog"));
					});
					}	
				}
				else{
					$("#studenttable #allstudent").append("<tr><td colspan='3'>NO Records Found</td></tr>");
				}
			}
			
		});
	}
	
	function addConfidetial(){
		
		 var entryDate = $("#entryDate").val();
		 var comments = $("#comment").val();
		 var student_id = $("#hstudentid").val();
		 var academicyear_id = $("#hacademicYearId").val();
		 var location_id = $("#hschoolNameId").val();
			 
		 $.ajax({
				
				type : "POST",
				url : "studentRegistration.html?method=AddConfidentialDetails",
				data : {"entryDate":entryDate,
						"comments":comments,
						"student_id":student_id,
						"academicyear_id":academicyear_id,
						"location_id":location_id,			
				},
				beforeSend: function(){
					$("#loder").show();
				},
				success : function(response) {

					var result = $.parseJSON(response);
				
					if(result.confidentialstatus == "confidentialReportSuccess"){
						$("#loder").hide();
						$(".successmessagediv").show();
    					$(".successmessage").text("Adding Record Progressing...");
					    document.getElementById("comments").style.border = "1px solid #AF2C2C";
    					document.getElementById("comments").style.backgroundColor = "#FFF7F7";
    					 setTimeout(function() {
    	 						location.reload();
    	 					}, 3000);
    					
					}else{
						$("#loder").hide();
						$(".errormessagediv").show();
    					$(".validateTips").text("Confidential Report Adding Failed ");
    					document.getElementById("comments").style.border = "1px solid #AF2C2C";
    					document.getElementById("comments").style.backgroundColor = "#FFF7F7";
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
					}
					
				}
				
			});
	}
	
	function editConfidetial(currentId,currentDate,CurrentComments){
		
		 var currentDate = $("#editentryDate").val();
		 var currentId = editid[0];
		 var CurrentComments = $("#editcomment").val();
		 $.ajax({
				
				type : "POST",
				url : "studentRegistration.html?method=EditConfidentialDetails",
				data : {"entrydate":currentDate,
						"comment":CurrentComments,
						"editid":currentId,
				},
				beforeSend: function(){
					$("#loder").show();
				},
				success : function(response) {

					var result = $.parseJSON(response);
				
					if(result.confidentialstatus == "EditSuccess"){
						$("#loder").hide();
						$(".successmessagediv").show();
						$(".successmessage").text("Updating Record Progressing...");
						document.getElementById("comments").style.border = "1px solid #AF2C2C";
						document.getElementById("comments").style.backgroundColor = "#FFF7F7";
						 setTimeout(function() {
							 $('.successmessagediv').fadeOut();
		 					}, 3000);
					}else{
						$("#loder").hide();
						$(".errormessagediv").show();
						$(".validateTips").text("Confidential Report Updating Failed ");
						document.getElementById("comments").style.border = "1px solid #AF2C2C";
						document.getElementById("comments").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
					}
				}
				
			});
		}
	
	
	function deleteReport(deleteid){
		remarks = null;
		if($("#inactivereason").val() == "Others"){
			remarks = $("#inactivereason").val() +"-"+$("#otherreason").val();
		}else{
			remarks = $("#inactivereason").val()
		}
		
		 datalist = {
			"deleteid" :deleteid,
			"remarks"  : remarks
		 };
		
		 $.ajax({
				type : "POST",
				url : "studentRegistration.html?method=deleteConfidentialDetails",
				data : datalist,
				beforeSend: function(){
					$("#loder").show();
				},
				success : function(data){
					var result = $.parseJSON(data);
					
					if(result.confidentialstatus == "DeleteSuccess"){
						$("#loder").hide();
				    	 $(".successmessagediv").show();
							$(".successmessage").text("Deleting Record Progressing...");
							setTimeout(function() {
								$('.successmessagediv').fadeOut();
								location.reload();
							}, 3000);
				     }else{
				    	 $("#loder").hide();
							$(".errormessagediv").show();
							$(".validateTips").text("Failed to delete the records");
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
				     }
				}
			});
	}
	 
	
function ActivedeleteReport(deletevalues){
		remark = null;
		remark = $("#otherreason").val();
		 datalist = {
			"deleteid" :deletevalues,
			"remarks"  : remark
		 };
		
		 $.ajax({
				type : "POST",
				url : "studentRegistration.html?method=activedeleteConfidentialDetails",
				data : datalist,
				async : false,
				success : function(data){
					var result = $.parseJSON(data);
					
					if(result.confidentialstatus == "true"){
				    	 $(".successmessagediv").show();
							$(".successmessage").text("Active Deleting Record Progressing...");
							setTimeout(function() {
								$('.successmessagediv').fadeOut();
								location.reload();
							}, 3000);
				     }
				}
			});
	}
	
	function showDeleteHistory(){		
		$('#individualstudenttable').hide();
		$('#studenttable').hide();
		$("#deleteHistorytable").show();
		
		$('#deleteHistorytable').empty();
		$("#deleteHistorytable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th style = 'width:200px;'>Entry Date</th>" +
				"<th>Remarks</th>" +
				"</center></tr>" +
				"</table>"
		);
		
		$.ajax({
			
			type : "POST",
			url : "studentRegistration.html?method=deactivateReportDetail",
			data : {"studentId":$("#hstudentid").val(),
					"accyear":$("#hacademicyaer").val(),
					"locationId":$("#hschoolNameId").val(),
					"flag":"history",
					"reportType" : "Disciplinary Action"
			},
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				if( result.studentSearchList.length>0){
					
				
				for (var j = 0; j < result.studentSearchList.length; j++) {
			
					$("#deleteHistorytable #allstudent").append(
							"<tr>"
							+"<td class='"+result.studentSearchList[j].confidentialId+" "+result.studentSearchList[j].confidentialEntryDate+" "+result.studentSearchList[j].confidentialComments+" "+"splitid"+"'>"+result.studentSearchList[j].count+"</td>"
							+"<td> "+result.studentSearchList[j].confidentialEntryDate+" </td>"
							+"<td>"+result.studentSearchList[j].confidentialComments+"&nbsp;&nbsp;&nbsp;<span class='activereason buttons' id='"+result.studentSearchList[j].confidentialId+"'>Active</span></td>"
							+"</tr>"
						);
					$('#allstudent tbody td .activereason').click(function(){
						deletevalues=$(this).attr("id");
						$("#activeDialog").dialog("open");
						dispalyActiveDialog($("#activeDialog"));
					});
					}	
				}
				else{
					$("#deleteHistorytable #allstudent").append("<tr><td colspan='3'>NO Records Found</td></tr>");
				}
			}
			
		});
		
	}
	
	function dispalyDialog(pointer){
		
		$(pointer).empty();
		
			$(pointer).append("<p>Are you sure you want to Remove?</p>");
			$(pointer).append('<label>Reason:</label>');
			$(pointer).append('<select name="inactivereason" id="inactivereason">'
					+ '<option value="">' + "----------select----------"
					+ '</option>'
					+ '<option value="Completed Studies">' + "Completed Studies"
					+ '<option value="Demoted">' + "Demoted"
					+ '</option>'
					+ '<option value="Failed">' + "Failed" 
					+ '</option>'
					+ '<option value="TC Given">' + "TC Given" 
					+ '<option value="Transffred">' + "Transffred" 
					+ '<option value="Others">' + "Others" 
					+ '</option>'+
			'</select>');
			 $(pointer).append('<div id="othreason" style="display: none;"><label>OtherReason:</label><input type="text" name=other id="otherreason"/></div>');
			 $("#inactivereason").change(function(){
				 if($(this).val().toLowerCase() == "others"){
					 $("#othreason").show();
				 }else{
					 $("#othreason").hide();
				 }
			 });
	}

	function dispalyActiveDialog(pointer){
		$(pointer).empty();
		$(pointer).append("<p>Are you sure to Active?</p>");
		$(pointer).append('<label>Reason:</label>');
		$(pointer).append('<div id="othreason"><input type="text" name=other id="otherreason"/></div>');
	}
	
	function HideError(pointer) 
	{
		document.getElementById(pointer.id).style.border = "1px solid #ccc";
		document.getElementById(pointer.id).style.backgroundColor = "#fff";
	}
	