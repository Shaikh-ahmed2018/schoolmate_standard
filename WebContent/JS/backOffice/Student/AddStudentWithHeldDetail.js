$(document).ready(function() {
	reportHistory();
	var StudentImage=$("#photohiddenid").val().trim();
	
	if(StudentImage!=""){
		$("#imagePreview").show();
		$('#imagePreview').attr('src', StudentImage);
	}
	$('#reportHistory').click(function(){
		reportHistory();
	});
	$('#contacts').click(function(){
		showCancelDetails();
	});
	$("#back1").click(function(){
		window.location.href="menuslist.html?method=studentWithheldList&historyaccYear="+$("#historyaccYear").val()
		+"&historylocId="+$("#historylocId").val()+"&historyclassname="+$("#historyclassname").val()+
		"&historysectionid="+$("#historysectionid").val()+"&historysearchvalue="+$("#historysearchvalue").val()+"&historystatus="+$("#historystatus").val();
	});
		$("input,select,textarea").on({
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
	
	
	$('#addconfidential').click(function() {
		$("#admissionDialog").dialog("open");
		$("#comment").val("");
		document.getElementById("comment").style.border ="1px solid #ccc";
		document.getElementById("comment").style.backgroundColor = "#fff";
	});
	
	fSDate = new Date($("#hiddenstartdate").val());
	fEDate = new Date($("#hiddenenddate").val());
	
		$("#entryDate").datepicker({
			dateFormat : "dd-mm-yy",
			maxDate:fEDate,
			minDate:fSDate,
			changeMonth : "true",
			changeYear : "true",
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
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		onClose : function(selectedDate) {
			$('#editentryDate').datepicker('getDate');
		}
	});
	$("#sectionid").change(function(){
		if($(this).val()=="cancel"){
			$(".fromdate").show();
			$("#cancelreason").show();
		}
		else if($(this).val()=="yes"){
			$(".fromdate").hide();
			$("#cancelreason").hide();
		}
	});
	$("#editfromdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		onClose : function(selectedDate) {
			$('#editfromdate').datepicker('getDate');
		}
	});
	$("#admissionDialog").dialog({
	    autoOpen  : false,
	    resizable : false,
	    maxWidth  :	650,
        maxHeight : 450,
        width     : 550,
        height    : 450,
	    modal     : true,
	    title     : "New WithHeld Detail",
	    buttons   : {
	    	'Save'  : function() {
	    		var entryDate = $("#entryDate").val();
	    		var comment = $("#comment").val();
	    		var cancelcomment = $("#cancelcomment").val();
	    		var Fromdate = $("#Fromdate").val();
	    		var sectionid= $("#sectionid").val();
	    		
    			 if(entryDate == "" || entryDate == null){
    				 $(".errormessagediv").show();
    				 showError("#entryDate","Select Date*");
    					return false;
    			
    			 }
    			 else if($("#sectionid").val()==undefined || $("#sectionid").val()=="" || $("#sectionid").val()==null){
    				 $(".errormessagediv").show();
    				 showError("#sectionid","Select the status*");
    					return false;
    				
    			 }
    			 else if(comment.trim() == "" || comment == null){
    				 $(".errormessagediv").show();
    				 showError("#comment","Field Required - Comment*");
    					return false;
    			 }
    			 else{  
    				 setTimeout(function() {
 						location.reload();
 					}, 3000);
    				 $(this).dialog('close');
    				 addConfidetial();
    			 }
	    	},
	    	'close'  : function() {
	    		$(this).dialog('close');
	    	}
	
	    }
	});
	$("#editsectionid").change(function(){
		var status=$(this).val();
		var editfromdate=$("#editfromdate").val();
		var editcancelcomment=$("#editcancelcomment").val();
		if($(this).val()=="cancel"){
			$("#fromdate1").show();
			$("#cancelreason").show();
			$("#editcomment").prop("readonly",true);
			$("#editentryDate").datepicker('disable');
		}
		else if($(this).val()=="yes"){
			$("#fromdate1").hide();
			$("#cancelreason").hide();
			$("#editcomment").prop("enable",true);
			$("#editentryDate").datepicker('enable');
		}
	});
	
	$("#editDialog").dialog({
	    autoOpen  : false,
	    resizable : false,
	    maxWidth  :	600,
        maxHeight : 500,
        width     : 550,
        height    : 450,
	    modal     : true,
	    title     : "Modify WithHeld",
	    buttons   : {
	    	'Update'  : function() {
	    		 var currentId = editid[0];
	    		 var currentDate = $("#editentryDate").val();
	    		 var CurrentComments = $("#editcomment").val();
	    		 var editsectionid=  $("#editsectionid").val();
	    		 var editfromdate=$("#editfromdate").val();
	    		 var editcancelcomment=$("#editcancelcomment").val();
	    		 
	    		 if(editsectionid=="cancel"){
	    			    $("#fromdate1").show();
	    				$("#cancelreason").show();
	    			 if(currentDate == "" || currentDate == null){
	    				 $(".errormessagediv").show();
	    				 showError("#editentryDate","Select Date*");
	    					return false;
	    			 }
	    			 else if(CurrentComments.trim() == "" || CurrentComments == null){
	    				 $(".errormessagediv").show();
	    				 showError("#editcomment","Field Required - Comment*");
	    					return false;
	    					
	    			 }
	    			 else if(editfromdate == "" || editfromdate == undefined || editfromdate == null){
	    				  
	    				 $(".errormessagediv").show();
	    				 showError("#editfromdate","select Cancel Date*");
	    					return false;
	    				 
	    			 }
	    		 else if(editcancelcomment.trim() == "" || editcancelcomment == undefined || editcancelcomment == null){
	    			 $(".errormessagediv").show();
    				 showError("#editcancelcomment","Field Required -Cancel Reason*");
    					return false;
    					
	    			 }
	    			 else{  
	    				 setTimeout(function() {
	  						location.reload();
	  					}, 3000);
	    				 $(this).dialog('close');
	    				 editConfidetial();
	    			 }
	    		 }
	    		 else{
    			 if(currentDate == "" || currentDate == null){
    				 $(".errormessagediv").show();
    				 showError("#editentryDate","Select Date*");
    					return false;
    			 }
    			 else if(CurrentComments == "" || CurrentComments == null){
    				 
    				 $(".errormessagediv").show();
    				 showError("#comment","Field Required - Comment*");
    					return false;
    			 }
    			 else{  
    				 setTimeout(function() {
  						location.reload();
  					}, 3000);
    				 $(this).dialog('close');
    				 editConfidetial();
    			 }
	    		}
	    		 
	    	},
	    	'close'  : function() {
	    		$(this).dialog('close');
	    	}
	    }
	});
	$("#deleteDialog").dialog({
		autoOpen  : false,
		resizable : false,
		maxWidth  :	300,
		maxHeight : 200,
		width     : 300,
		height    : 200,
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
});
	function showCancelDetails(){
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hschoolNameId').val();
		var editsectionid=$('#editsectionid').val();
		$("#addconfidential").hide();
		$('#individualstudenttable').hide();
		$('#individualstudentcanceltable').show();
		$('#studenttable').hide();
		$('#individualstudentcanceltable').empty();
		$("#individualstudentcanceltable").append("<table class='table'  id='allstudent'  width='100%'" +">"
	    
				+"<center><tr><th>SI No</th>"+
				"<th>WithHeld Date</th>"+
				"<th>Reason</th>"+
				"<th>WithHeld Cancel Date</th>"+
				"<th>WithHeld Cancel Comments</th>"+
				"</center></tr>"+
				"</table>"
		);
		$.ajax({
			type : "POST",
			url : "studentRegistration.html?method=singleStudentWithHeldDetailsList",
			data : {"studentId":studentId,
					"accyear":accyear,
					"locationId":locationId,
					"flag":"cancel",
					"editsectionid":editsectionid
			},
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				for (var j = 0; j < result.studentSearchList.length; j++) {
			
					$("#individualstudentcanceltable #allstudent").append(
							"<tr>"
							+"<td class='"+result.studentSearchList[j].confidentialId+"_"+result.studentSearchList[j].confidentialEntryDate+"_"+result.studentSearchList[j].status+"_"+result.studentSearchList[j].confidentialComments+"_"+result.studentSearchList[j].cancelDate+"_"+result.studentSearchList[j].cancelcomment+"_"+" splitid"+"'>"+result.studentSearchList[j].count+"</td>"
							+"<td> "+result.studentSearchList[j].confidentialEntryDate+" </td>"
							+"<td ><span class='comments'>"+result.studentSearchList[j].confidentialComments+" </td>"
							+"<td> "+result.studentSearchList[j].cancelDate+" </td>"
							+"<td> "+result.studentSearchList[j].cancelcomment+"<span class='deletecancel buttons' id='"+result.studentSearchList[j].confidentialId+"'>Remove</span></td>"
							+"</tr>"
						);
					deletecancel();
					}		
			}
		});}
	function reportHistory(){
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hschoolNameId').val();
		var editsectionid=$('#editsectionid').val();
		
		$('#addconfidential').show();
		$('#individualstudenttable').show();
		$("#individualstudentcanceltable").hide();
		$('#studenttable').hide();
		
		$('#individualstudenttable').empty();
		$("#individualstudenttable").append("<table class='table addtable' id='allstudent' left='100px' margin-top='59px' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>WithHeld Date</th>" +
				"<th>Reason</th>" +
				"</center></tr>" +
				"</table>"
		);
		$.ajax({
			type : "POST",
			url : "studentRegistration.html?method=singleStudentWithHeldDetailsList",
			data : {"studentId":studentId,
					"accyear":accyear,
					"locationId":locationId,
					"flag":"withheld",
					"editsectionid":editsectionid
			},
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				for (var j = 0; j < result.studentSearchList.length; j++) {
					$("#individualstudenttable #allstudent").append(
							"<tr>"
							+"<td class='"+result.studentSearchList[j].confidentialId+"_"+result.studentSearchList[j].confidentialEntryDate+"_"+result.studentSearchList[j].status+"_"+result.studentSearchList[j].confidentialComments+"_"+result.studentSearchList[j].cancelDate+"_"+result.studentSearchList[j].cancelcomment+"_"+" splitid"+"'>"+result.studentSearchList[j].count+"</td>"
							+"<td> "+result.studentSearchList[j].confidentialEntryDate+" </td>"
							+"<td ><span class='comments'>"+result.studentSearchList[j].confidentialComments+"</span><span class='edit buttons' id='"+result.studentSearchList[j].confidentialId+"'>Modify</span>&nbsp;&nbsp;&nbsp;</td>"
							+"</tr>"
						);
					/*<span class='delete buttons' id='"+result.studentSearchList[j].confidentialId+"'>Delete</span>*/
					edit();
					deletewithheld();
					}		
			}
		});
	}
	function addConfidetial(){	
		 var entryDate = $("#entryDate").val();
		 var comments = $("#comment").val();
		 var Fromdate = $("#Fromdate").val();
		 var cancelcomment = $("#cancelcomment").val();
		 var student_id = $("#hstudentid").val();
		 var academicyear_id = $("#hacademicYearId").val();
		 var location_id = $("#hschoolNameId").val();
		 var sectionid= $("#sectionid").val();
		 var withheldId= $("#withheldId").val();
		 $.ajax({
				type : "POST",
				url : "studentRegistration.html?method=AddWithHeldDetails",
				data : {"entryDate":entryDate,
						"comments":comments,
						"student_id":student_id,
						"academicyear_id":academicyear_id,
						"location_id":location_id,
						"cancelcomment":cancelcomment,
						"sectionid":sectionid,
						"Fromdate":Fromdate,
						"withheldId":withheldId,
				},
				beforeSend: function(){
					$("#loder").show();
				},
				success : function(response) {
					var result = $.parseJSON(response);
					if(result.studentSearchList =="confidentialReportSuccess"){
						$("#loder").hide();
						$(".successmessagediv").show();
    					$(".successmessage").text("Adding Record Progressing...");
    					 setTimeout(function() {
    	 						location.reload();
    	 					}, 3000);
					}else{
						$("#loder").hide();
						$(".errormessagediv").show();
    					$(".validateTips").text("WithHeld Detail Adding Failed ");
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
					}
					
				}
			});
	}
	function editConfidetial(){
		 var currentDate = $("#editentryDate").val();
		 var currentId = editid[0];
		 var editsectionid = $("#editsectionid").val();
		 var CurrentComments = $("#editcomment").val();
		 var Fromdate = $("#editfromdate").val();
		 var CancelComment = $("#editcancelcomment").val();
		 var student_id = $("#hstudentid").val();
		 var withheldId= $("#withheld").val();
		 $.ajax({
				type : "POST",
				url : "studentRegistration.html?method=EditWithHeldDetails",/*EditConfidentialDetails*/
				data : {"entrydate":currentDate,
					     "sectionid":editsectionid,          
						"comment":CurrentComments,
						"Fromdate":Fromdate,
						"student_id":student_id,
						"cancelcomment":CancelComment,
						"editid":currentId,
						"withheldId":withheldId,
				},
				beforeSend: function(){
					$("#loder").show();
				},
				success : function(response) {
					var result = $.parseJSON(response);
					if(result.studentSearchList =="EditSuccess"){
						$("#loder").hide();
						$(".successmessagediv").show();
						$(".successmessage").text("Updating Record Progressing...");
						 setTimeout(function() {
							 $('.successmessagediv').fadeOut();
		 					}, 3000);
					}else{
						$("#loder").hide();
						$(".errormessagediv").show();
						$(".validateTips").text("Confidential Report Updating Failed ");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
					}
				}
			});
		}
	function deleteReport(deleteid){
		 datalist = {
			"deleteid" :deleteid
		 };
		 $.ajax({
				type : "POST",
				url : "studentRegistration.html?method=deleteWithHeldDetails",
				data : datalist,
				beforeSend: function(){
					$("#loder").show();
				},
				success : function(data){
					var result = $.parseJSON(data);
					
					if(result.studentSearchList == "DeleteSuccess"){
						$("#loder").hide();
				    	 $(".successmessagediv").show();
							$(".successmessage").text("Deleting Record Progressing...");
							setTimeout(function() {
								$('.successmessagediv').fadeOut();
								location.reload();
							}, 3000);
				     }else{
				    	 $("#loder").hide();
				     }
				}
			});
	}
function deletecancel(){
	$('.deletecancel').click(function(){
		deletevalues=$( this ).parent("td").parent("tr").find(".splitid").attr("class").split("_");
		deleteid=deletevalues[0];
		$("#deleteDialog").dialog("open");
	});
}
	function edit(){
		$("#allstudent tbody td .edit").click(function(){
			editid=$( this ).parent("td").parent("tr").find(".splitid").attr("class").split("_");
			$('#editentryDate').val(editid[1]);
			$('#editcomment').val($(this).closest('tr').find('.comments').text());
			$("#editsectionid").val(editid[2]);
			if($("#editsectionid").val() == "cancel"){
				$("#editfromdate").val(editid[4]);
				$("#editcancelcomment").val(editid[5]);
				$("#fromdate1").show();
				$("#cancelreason").show();
				$("#editDialog").dialog("open");
			}
			$("#editDialog").dialog("open");
		});
	}
	function deletewithheld(){
		$('.delete').click(function(){
			deletevalues=$( this ).parent("td").parent("tr").find(".splitid").attr("class").split("_");
			deleteid=deletevalues[0];
			$("#deleteDialog").dialog("open");
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
		$(".errormessagediv").show();
		$(".validateTips").text(errorMessage);
		$(".errormessagediv").delay(3000).fadeOut();
	}
	function hideError(id){
		$(id).css({
			"border":"1px solid #ccc",
			"background-color":"#fff"
			});
	}
