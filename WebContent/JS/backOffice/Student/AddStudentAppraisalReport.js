$(document).ready(function() {
	$("#starttime").blur(function(){
			hideError("#"+$(this).attr("id"));
			$(".errormessagediv").hide();
			
	});
	
	$("#back1").click(function(){
		window.location.href='menuslist.html?method=StudentAppraisalReport&historyaccYear='+$("#historyaccYear").val()
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
	
	reportHistory();
	
	$("#datetimepicker3").datetimepicker({
		pickDate : false,
		
		
	});
	$("#datetimepicker4").datetimepicker({
		pickDate : false,
		
		
	});
	
	$("#recommented").autocomplete({
		source : function(request, response) {

			$.ajax({
				url : "studentRegistration.html?method=getStaffName",
				data : {
					searchTerm : request.term
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(	result.jsonResponse,function(item) {
						return {
							label : item.teacherName,
							value : item.teacherId,
						}
					}));
				}
			});
		},
		select : function(event, ui) {

			var searchTerm = ui.item.value;

			studentDetails = {
					'searchTerm' : searchTerm
			};
		
			
			
			$("#recommented").val(ui.item.label);
			$("#hiddenstaffId").val(searchTerm);

			return false;
		}
	});
	
	
	
	$("#recommented1").autocomplete({
		source : function(request, response) {

			$.ajax({
				url : "studentRegistration.html?method=getStaffName",
				data : {
					searchTerm : request.term
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(	result.jsonResponse,function(item) {
						return {
							label : item.teacherName,
							value : item.teacherId,
						}
					}));
				}
			});
		},
		select : function(event, ui) {

			var searchTerm = ui.item.value;

			studentDetails = {
					'searchTerm' : searchTerm
			};
		
			
			
			$("#recommented1").val(ui.item.label);
			$("#hiddenstaffId1").val(searchTerm);
            $("#hiddenstaffId2").val($("#hiddenstaffId1").val());
			return false;
		}
	});
	
	
	
	
	
	
	var StudentImage=$("#photohiddenid").val();

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
	
	$("#back").click(function(){
		
		window.location.href="menuslist.html?method=StudentAppraisalReport";

	});
	
	$('#addconfidential').click(function() {
		$("#admissionDialog").dialog("open");
		$("#actiontaken").val(0);
		document.getElementById("actiontaken").style.border ="1px solid #ccc";
		document.getElementById("actiontaken").style.backgroundColor = "#fff";
		$("#recommented").val("");
		document.getElementById("recommented").style.border ="1px solid #ccc";
		document.getElementById("recommented").style.backgroundColor = "#fff";
		/*$("#meetingDate").val("");
		document.getElementById("meetingDate").style.border ="1px solid #ccc";
		document.getElementById("meetingDate").style.backgroundColor = "#fff";*/
		$("#starttime").val("");
		document.getElementById("starttime").style.border ="1px solid #ccc";
		document.getElementById("starttime").style.backgroundColor = "#fff";
		/*$("#meetingwith").val(0);
		document.getElementById("meetingwith").style.border ="1px solid #ccc";
		document.getElementById("meetingwith").style.backgroundColor = "#fff";*/
		$("#remark").val("");
		
	});
	
	$(".allstudent tbody td .edit").click(function(){
		editid=$(this).parent("td").parent("tr").find(".splitid").attr("class").split(" ");
		remarks=$(this).parent("td").parent("tr").find(".remarksid").attr("class").split("-");
       
		$('#hiddenid').val(editid[0]);
		$('#schedualdate1').val(editid[1]);
		$('#meetingDate1').val(editid[2]);
		$("#starttime1").val(editid[3]);
		$("#meetingwith1").val(editid[4]);
		$("#actiontaken1").val($(this).parent("td").parent("tr").find(".action").text());
		$("#recommented1").val($(this).parent("td").parent("tr").find('.recomend').text());
		$("#remark1").val(remarks[0]);
		$("#hiddenstaffId2").val(editid[5]);
		$("#editDialog").dialog("open");
	});
	
	$('.delete').click(function(){
		deletevalues=$( this ).parent("td").parent("tr").find(".splitid").attr("class").split(" ");
		deleteid=deletevalues[0];
		$("#deleteDialog").dialog("open");
	});

	fSDate = new Date($("#hiddenstartdate").val());
	fEDate = new Date($("#hiddenenddate").val());
	
	$("#schedualdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		
	
	});
	
	$("#schedualdate1").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		
	
	});
	
	
	$("#meetingDate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		
	
	});
	
	$("#meetingDate1").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		
	
	});
	
	/*	$("#schedualdate").datepicker({
			maxDate:0,
			dateFormat : "dd-mm-yy",
			yearRange : 'c-65:c+65',
			changeMonth : "true",
			changeYear : "true",
			yearRange : '1997:' + (new Date).getFullYear(),
			onClose : function(selectedDate) {
				$('#schedualdate').datepicker('getDate');
			}
		});
		
		$("#schedualdate1").datepicker({
			maxDate:0,
			dateFormat : "dd-mm-yy",
			yearRange : 'c-65:c+65',
			changeMonth : "true",
			changeYear : "true",
			yearRange : '1997:' + (new Date).getFullYear(),
			onClose : function(selectedDate) {
				$('#schedualdate').datepicker('getDate');
			}
		});*/
		
		/*$("#meetingDate").datepicker({
			minDate:0,
			dateFormat : "dd-mm-yy",
			yearRange : 'c-65:c+65',
			changeMonth : "true",
			changeYear : "true",
			yearRange : '1997:' + (new Date).getFullYear(),
			onClose : function(selectedDate) {
				$('#meetingDate').datepicker('getDate');
			}
		});
		
		$("#meetingDate1").datepicker({
			minDate:0,
			dateFormat : "dd-mm-yy",
			yearRange : 'c-65:c+65',
			changeMonth : "true",
			changeYear : "true",
			yearRange : '1997:' + (new Date).getFullYear(),
			onClose : function(selectedDate) {
				$('#meetingDate').datepicker('getDate');
			}
		});
*/
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
		$("#schedualdate").val(localdate);
	
/*	$("#schedualdate").datepicker({
		minDate:0,
		dateFormat : "dd-mm-yy",
		yearRange : 'c-65:c+65',
		changeMonth : "true",
		changeYear : "true",
		yearRange : '1997:' + (new Date).getFullYear(),
		onClose : function(selectedDate) {
			var date2 = $('#schedualdate').datepicker('getDate');
			date2.setDate(date2.getDate() + 1);

		}
	});*/
	
	$("#admissionDialog").dialog({
	    autoOpen  : false,
	    resizable : false,
	    maxWidth  :	900,
        maxHeight : 500,
        width     : 900,
        height    : 370,
	    modal     : true,
	    title     : "New Appraisal Action",
	    buttons   : {
	    	'Save'  : function() {
	    	
	    		var actiontaken=$("#actiontaken").val();
	    		var schedualdate = $("#schedualdate").val();
	    		var meetingdate=$("#meetingDate").val();
	    		var remark = $("#remark").val();
	    		var recomendeby=$("#hiddenstaffId").val();
	    		var meetingwith=$("#meetingwith").val();
	    		var meetingon=$("#starttime").val();
	    		
    			 if(actiontaken == "" || actiontaken ==null){
    				 $(".errormessagediv").show();
    				 showError("#actiontaken","Field Required -Action Taken*");
 					return false;
    			 }
    			 else if(recomendeby.trim() == "" || recomendeby == null){
    				 $(".errormessagediv").show();
    				 showError("#recommented","Field Required -Recomende By*");
 					return false;
    					
    			 }
    			 else if(schedualdate == "" || schedualdate == null){
    				 $(".errormessagediv").show();
    				 showError("#schedualdate","Field Required -Date*");
    					return false;
    					
    			 }
    			 /*else if(meetingwith == "" || meetingwith == null){
    				 $(".errormessagediv").show();
    					showError("#meetingwith","Field Required - Meeting with");
    					return false;
    			 }
    			 else if(meetingdate == "" || meetingdate == null){
    				 $(".errormessagediv").show();
    				 showError("#meetingDate","Field Required -Meeting Date");
   					return false;
    			 }
    			 else if(meetingon == "" || meetingon == null){
    				 $(".errormessagediv").show();
    					showError("#starttime","Field Required - Meeting On");
    					return false;
    			 }*/
    			 else if(remark.trim() == "" || remark == null){
    				 $(".errormessagediv").show();
    				 showError("#remark","Field Required -Remark*");
  					return false;
    					
    			 }
    			 
    			 else {  
    				 setTimeout(function() {
 						location.reload();
 					}, 3000);
    				 $(this).dialog('close');
    				 addAppraisal();
    			 }
    			 
    			 
	    	},
	    	'close' : function() {
	    		$(this).dialog('close');
	    }
	
	}
	
	});
	
	$("#editDialog").dialog({
	    autoOpen  : false,
	    resizable : false,
	    maxWidth  :	900,
        maxHeight : 500,
        width     : 900,
        height    : 500,
	    modal     : true,
	    title     : "Modify Appraisal",
	    buttons   : {
	    	'Save'  : function() {
	    	
	    		var currentId = editid[0];
	    		var actiontaken=$("#actiontaken1").val();
	    		var schedualdate = $("#schedualdate1").val();
	    		var meetingdate=$("#meetingDate1").val();
	    		var remark = $("#remark1").val();
	    		var recomendeby=$("#recommented1").val();
	    		var meetingwith=$("#meetingwith1").val();
	    		var meetingon=$("#starttime1").val();
	    		
    			 if(actiontaken == "" || actiontaken ==null){
    				 $(".errormessagediv").show();
    				 showError("#actiontaken","Field Required -Action Taken*");
 					return false;
 					
    					
    			 }
    			 else if(schedualdate == "" || schedualdate == null){
    				 $(".errormessagediv").show();
    				 showError("#schedualdate","Field Required -Schedual Date*");
    					return false;
    					
    			 }
    			 
    			 else if(remark == "" || remark == null){
    				 $(".errormessagediv").show();
    				 showError("#remark","Field Required -Remark*");
  					return false;
    					
    			 }
    			 
    			 else if(recomendeby == "" || recomendeby == null){
    				 $(".errormessagediv").show();
    				 showError("#recommented","Field Required -Recomende By*");
 					return false;
    					
    			 }
    			 
    			 else{
	    		editappraisal();
	    		 setTimeout(function() {
						location.reload();
						}, 3000);
 				 $(this).dialog('close');
 			/*	 editappraisal();*/
    			 }
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

	function showContactDetails(){
		
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hschoolNameId').val();
		
		
		$('#studenttable').show();
		$('#individualstudenttable').hide();
		
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
			
		});
	}
	
	function reportHistory(){
		 
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hschoolNameId').val();
		
		$('#individualstudenttable').show();
		$('#studenttable').hide();
		
		$('#individualstudenttable').empty();
		$("#individualstudenttable").append("<table class='table allstudent' width='100%'>"
				+"<center><tr><th style='width:53px;'>SI No</th>"+
				"<th>Action Taken</th>" +
				"<th style='width:108px;'>Schedule Date</th>" +
				"<th style='width:112px;'>Recomende By</th>" +
				"<th style='width:257px;'>Remark</th>" +
				"</center></tr>" +
				"</table>"
		);
		
		$.ajax({
			
			type : "POST",
			url : "studentRegistration.html?method=singleStudentDetailReport",
			data : {"studentId":studentId,
					"accyear":accyear,
					"locationId":locationId,
					/*"flag":"history"*/
			},
			async : false,
			success : function(response) {
			
				var result = $.parseJSON(response);
	
				for (var j = 0; j < result.studentReport.length; j++) {	
					
				$(".allstudent").append(
							"<tr>"
							+"<td class='"+result.studentReport[j].studentappraisalid+" "+result.studentReport[j].schedualdate+" "+result.studentReport[j].meetingdate+" "+result.studentReport[j].meetingon+" "+result.studentReport[j].meetingwith+" "+result.studentReport[j].recomendedby+" "+"splitid"+"'>"+result.studentReport[j].count+"</td>"
							+"<td class='action'>"+result.studentReport[j].actiontaken+"</td>"
							+"<td class='"+result.studentReport[j].remark+"-"+" remarksid"+"'>"+result.studentReport[j].schedualdate+"</td>"
							+"<td class='recomend'>"+result.studentReport[j].staffname+"</td>"
						    +"<td><span>"+result.studentReport[j].remark+"</span><span class='edit buttons' id='"+result.studentReport[j].studentappraisalid+"'>Modify</span>&nbsp;&nbsp;&nbsp;<span class='delete buttons' id='"+result.studentReport[j].studentappraisalid+"'>Remove</span></td>"
							+"</tr>"
						);
				$(".allstudent tbody td .edit").click(function(){
					editid=$(this).parent("td").parent("tr").find(".splitid").attr("class").split(" ");
					remarks=$(this).parent("td").parent("tr").find(".remarksid").attr("class").split("-");
					
					$('#hiddenid').val(editid[0]);
					$('#schedualdate1').val(editid[1]);
					$('#meetingDate1').val(editid[2]);
					$("#starttime1").val(editid[3]);
					$("#meetingwith1").val(editid[4]);
					$("#actiontaken1").val($(this).parent("td").parent("tr").find(".action").text());
					$("#recommented1").val($(this).parent("td").parent("tr").find('.recomend').text());
					$("#remark1").val(remarks[0]);
					$("#editDialog").dialog("open");
				});
				
				 $('.delete').click(function(){
					deletevalues=$( this ).parent("td").parent("tr").find(".splitid").attr("class").split(" ");
					deleteid=deletevalues[0];
					$("#deleteDialog").dialog("open");
				 });
				
		        }		
			}
		});
	}
	
	function addAppraisal(){
		
		var actiontaken=$("#actiontaken").val();
		var schedualdate = $("#schedualdate").val();
		var meetingdate=$("#meetingDate").val();
		var remark = $("#remark").val();
		var recomendeby=$("#hiddenstaffId").val();
		var meetingwith=$("#meetingwith").val();
		var meetingon=$("#starttime").val();
		 var student_id = $("#hstudentid").val();
		 var academicyear_id = $("#hacademicYearId").val();
		 var location_id = $("#hschoolNameId").val();
		 var hiddenid = $("#hiddenid").val();
		 
		 $.ajax({
				
				type : "POST",
				url : "studentRegistration.html?method=AddApparisalDetails",
				data : {"actiontaken":actiontaken,
						"schedualdate":schedualdate,
						"meetingdate":meetingdate,
						"remark":remark,
						"recomendeby":recomendeby,
						"meetingwith":meetingwith,
						"meetingon":meetingon,
						"student_id":student_id,
						"academicyear_id":academicyear_id,
						"location_id":location_id,		
						"hiddenid":hiddenid
				},
				beforeSend: function(){
					$("#loder").show();
				},
				success : function(response) {

					var result = $.parseJSON(response);
				
					if(result.appraisalstatus =="appraisalreportsuccess"){
						$("#loder").hide();
						$(".successmessagediv").show();
    					$(".successmessage").text("Adding Record Progressing...");
    					
    					 setTimeout(function() {
    	 						location.reload();
    	 					}, 3000);
    					
					}else{
						$("#loder").hide();
						$(".errormessagediv").show();
    					showError("#remark","Appraisal Report Adding Failed ");
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
					}
					
				}
				
			});
	}
	
	function  editappraisal(){
		
		var actiontaken=$("#actiontaken1").val();
		var schedualdate = $("#schedualdate1").val();
		var meetingdate=$("#meetingDate1").val();
		var remark = $("#remark1").val();
		
		var recomendeby=$("#hiddenstaffId2").val();
		
		var meetingwith=$("#meetingwith1").val();
		var meetingon=$("#starttime1").val();
		 var student_id = $("#hstudentid").val();
		 var academicyear_id = $("#hacademicYearId").val();
		 var location_id = $("#hschoolNameId").val();
		 
		 var hiddenid = $("#hiddenid").val();
		 
		 $.ajax({
				
				type : "POST",
				url : "studentRegistration.html?method=AddApparisalDetails",
				data : {"actiontaken":actiontaken,
						"schedualdate":schedualdate,
						"meetingdate":meetingdate,
						"remark":remark,
						"recomendeby":recomendeby,
						"meetingwith":meetingwith,
						"meetingon":meetingon,
						"student_id":student_id,
						"academicyear_id":academicyear_id,
						"location_id":location_id,		
						"hiddenid":hiddenid
				},
				beforeSend: function(){
					$("#loder").show();
				},
				success : function(response) {

					var result = $.parseJSON(response);
				
					if(result.appraisalstatus =="upadatesuccuss"){
						$("#loder").hide();
						$(".successmessagediv").show();
    					$(".successmessage").text("Updating Record Progressing...");
    					
    					 setTimeout(function() {
    	 						location.reload();
    	 					}, 3000);
    					
					}else{
						$("#loder").hide();
						$(".errormessagediv").show();
    					showError("#remark","Updating Report Adding Failed");
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
				url : "studentRegistration.html?method=deleteApparaisalDetails",
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
