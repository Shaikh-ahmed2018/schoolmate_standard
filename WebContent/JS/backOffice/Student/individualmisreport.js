$(document).ready(function(){
	 
	$(".feedetailtable").click(function(){
		$("#collapseOne1").toggle("active");
		$("#collapseTwo2").toggle("active");
	});
	
	var status=$("#confidentialStatusId").val();
	if(status == "AVAILABLE"){
		$('#confidentialStatusId').css("background-color", "yellow");
	}else{
		$('#confidentialStatusId').css("background-color", "#fff");
	}
	
	$("#back1").click(function(){
		// window.location.href ="menuslist.html?method=miscellaneousReport";
/*		 window.location.href ="menuslist.html?method=miscellaneousReport&historyaccYear="+$("#historyaccYear").val()
		 +"&historylocId="+$("#historylocId").val()+"&historyclassname="+$("#historyclassname").val()
		 +"&historysectionid="+$("#historysectionid").val()+"&historysearchvalue="+$("#historysearchvalue").val()+
		 "&historystatus="+$("#historystatus").val();*/
		
		window.location.href ="menuslist.html?method=miscellaneousReport"
	});
	
	
	if($("#hiddenbackId").val()!=""){
		$("#back").click(function(){
			window.location.href = "menuslist.html?method=individualStudentSearch&studentId="+$("#hstudentid").val()+"&accyear="+$("#hacademicYearId").val()+"&locationId="+$("#hschoolNameId").val();
		});
	}
	$('#goPage').click(function(){
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hiddenlocId').val();
		
		var forwardPage = $('.nil').val();
		
		if(forwardPage=='reg_Form'){
			window.location.href="studentRegistration.html?method=editStudent&searchTerm="+$("#hstudentid").val()+","+$("#hacademicYearId").val()+"&status="+$("#hiddenstatus").val()+"&srarchSt=mis";
		}else if(forwardPage=='conf_Report'){
			window.location.href="menuslist.html?method=AddStudentConfidentialReport&studentId="+studentId+"&accyear="+accyear+"&locationId="+locationId+"&srarchSt=mis";
			
		}else if(forwardPage=='id_Card'){
			window.location.href="menuslist.html?method=PrintStudentSingleIDCard&studentId="+studentId+"&accyear="+accyear+"&locationId="+locationId;
		}else if(forwardPage=='mis_Report'){
			window.location.href="menuslist.html?method=miscellaneousReport";
		}else{
			document.getElementById("goto").style.border = "1px solid #AF2C2C";
			document.getElementById("goto").style.backgroundColor = "#FFF7F7";
      	    $(".errormessagediv").show();
     		    $(".validateTips").text("Select Any One Reason");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    },3000);
		}
	});
	
	showContactDetails();
	
	var StudentImage=$("#photohiddenid").val();
	if(StudentImage!=""){

		$("#imagePreview").show();
		$('#imagePreview').attr('src', StudentImage);
	}
	
	$('#contacts').click(function(){
		showContactDetails();
	});
	
	$('#classHistory').click(function(){
			
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hiddenlocId').val();
		$('#individualstudenttable').show();
		$('#examdetailtable').hide();
		$('#studenttable').hide();
		$('#feedetailtable').hide();
		$('#attendancetable').hide();
		$('#appraisaltable').hide();
        $('#individualstudenttable').empty();
		$("#individualstudenttable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Academic Year</th>" +
				"<th>Class</th>" +
				"<th>Section</th>"+
				"<th>Roll No</th>"+
				"<th>Status</th>"+
				"</center></tr>" +
				"</table>"
		);
		
		$.ajax({
			
			type : "POST",
			url : "studentRegistration.html?method=individualStudentSearch",
			data : {"studentId":studentId,
					"accyear":accyear,
					"locationId":locationId			
			},
			async : false,
			
			success : function(response) {

				var result = $.parseJSON(response);

			
				for (var j = 0; j < result.studentSearchList.length; j++) {
			
					$("#individualstudenttable #allstudent").append("<tr>"
							+"<td>"+result.studentSearchList[j].count+"</td>" 
							+"<td> "+result.studentSearchList[j].academicYear+" </td>"
							+"<td> "+result.studentSearchList[j].classname+"</td>"
							+"<td> "+result.studentSearchList[j].sectionnaem+" </td>"
							+"<td> "+result.studentSearchList[j].studentrollno+" </td>"
							+"<td> "+result.studentSearchList[j].status+" </td>"
							+"</tr>");
					}	
			}
			
		});
	});
	
	$('#examDetails').click(function(){
		  
		$('#examdetailtable').show();
		$('#studenttable').hide();
		$('#individualstudenttable').hide();
		$('#feedetailtable').hide();
		$('#attendancetable').hide();
		$('#appraisaltable').hide();
		
		getStudentSubjectList();	
		$('#examdetailtable').empty();
		$("#examdetailtable").append("<label name='examcodelabel' style='margin-left:300px'>Exam Code</label>&nbsp;&nbsp;"
					+"<select name='examcode' id='examcodeId'>"
						+"<option value=''></option>"
					+"</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					);
		
		
		 getExamCodeList(); 
			
		$("#examdetailtable").append("<label name='examnamelabel'>Exam Name</label>&nbsp;&nbsp;"
				
				/*+"<label name='examname' id='examname' class='examnames'>"
					+"<label value=''></label>"
			    +"</label>");*/
		+"<input type='text' name='examname' id='examname' readonly='readonly' class='examnames' style='font-family:Roboto,sans-serif;font-size: 13px;color: #000;'>");
		
          $("#examcodeId").change(function(){
        	  var examcode=$("#examcodeId option:selected").text().trim();
        	  $.ajax({
      			type : 'POST',
      			url : "studentRegistration.html?method=getExamname",
      			data: {'examcode':examcode},
      			async : false,
      			success : function(response) {
      				var result = $.parseJSON(response);
      				                   $('#examdetailtable #examname').html("");
    					        $('#examdetailtable #examname').val(result.examnamelist);
                                  }
      			
      		}); 
        	  getSubjectsmarkbasedExams();
        });
    });
	
	$('#feedetails').click(function(e){
		e.preventDefault();
		$('#feedetailtable').show();
		$('#studenttable').hide();
		$('#individualstudenttable').hide();
		$('#examdetailtable').hide();
		$('#attendancetable').hide();
		$('#appraisaltable').hide();

		var studentid=$("#hstudentid").val();
		  var accyear=$("#hacademicyaer").val();
		  var locationId=$("#hiddenlocId").val();
		  var classId=$("#hiddenclassid").val();
		
		$('#schoolfees').empty();
		$("#transportfees").empty();
		$("#schoolfees").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Terms</th>" +
				"<th>Fee Amt</th>" +
				"<th>Paid Amt</th>"+
				"<th>Fine</th>"+
				"<th>Dues</th>"+
				"<th>Advance</th>"+
				"<th>Status</th>"+
				"</center></tr>" +
				"</table>"
		);
		$("#transportfees").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Terms</th>" +
				"<th>Month</th>" +
				"<th>Fee Amt</th>" +
				"<th>Paid Amt</th>"+
				"<th>Paid Date</th>"+
				"<th>Dues</th>"+
				"<th>Reciepr No</th>"+
				"<th>Status</th>"+
				"</center></tr>" +
				"</table>"
		);
			$.ajax({
			type : "POST",
			url : "studentRegistration.html?method=getSchoolFeeDetails",
			data : {"studentid":studentid,
					"accyear":accyear,
				"locationId":locationId,			
				"classId":classId,			
			},
			async : false,
			
			success : function(response) {
				var result = $.parseJSON(response);
				for (var j = 0; j < result.studentFeeList.length; j++) {
					$("#schoolfees #allstudent").append("<tr>"
							+"<td>"+(j+1)+"</td>" 
							+"<td> "+result.studentFeeList[j].term+" </td>"
							+"<td> "+parseFloat(result.studentFeeList[j].tot_actual_amt).toFixed(2)+"</td>"
							+"<td> "+parseFloat(result.studentFeeList[j].amount_paid_so_far).toFixed(2)+" </td>"
							+"<td> "+parseFloat(result.studentFeeList[j].fineAmount).toFixed(2)+" </td>"
							+"<td> "+parseFloat(result.studentFeeList[j].duesCarry).toFixed(2)+" </td>"
							+"<td> "+parseFloat(result.studentFeeList[j].advanceCarry).toFixed(2)+" </td>"
							+"<td style='text-align: center;'><span class='"+result.studentFeeList[j].status+"'>"+result.studentFeeList[j].status+"</span></td>"
							+"</tr>");
					}	
				if(result.studentTransportFeeList.length>0){
					if(result.studentTransportFeeList[0].feeNamelist.length>0){
					for (var i = 0; i < result.studentTransportFeeList[0].feeNamelist.length; i++) {
						$("#transportfees #allstudent").append("<tr>"
								+"<td>"+(i+1)+"</td>" 
								+"<td>"+result.studentTransportFeeList[0].feeNamelist[i].term+" </td>"
								+"<td>"+result.studentTransportFeeList[0].feeNamelist[i].monthName+" </td>"
								+"<td>"+result.studentTransportFeeList[0].feeNamelist[i].actualAmt+" </td>"
								+"<td>"+result.studentTransportFeeList[0].feeNamelist[i].paidAmt+" </td>"
								+"<td>"+result.studentTransportFeeList[0].feeNamelist[i].paidDate+" </td>"
								+"<td>"+result.studentTransportFeeList[0].feeNamelist[i].balanceAmt+" </td>"
								+"<td>"+result.studentTransportFeeList[0].feeNamelist[i].recieptNo+" </td>"
								+"<td style='text-align: center;'><span class='"+result.studentTransportFeeList[0].feeNamelist[i].status+"'>"+result.studentTransportFeeList[0].feeNamelist[i].status+"</span></td>"
								+"</tr>");
					}	
					}else{
						$(".transportfeediv").css({"display":"none"});
					}
					}else{
					$(".transportfeediv").css({"display":"none"});
				}
				
			}
			
		});
	});

	
	$('#attendance').click(function(){
		 
		$('#attendancetable').show();
		$('#feedetailtable').hide();
		$('#studenttable').hide();
		$('#individualstudenttable').hide();
		$('#examdetailtable').hide();
		$('#libraryinfotable').hide();
		$('#appraisaltable').hide();
		
		
		$('#attendancetable').empty();
		$("#attendancetable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Attendance</th>" +
				"<th>Date</th>" +
				"</center></tr>" +
				"</table>"
		);
		var studentid=$("#hstudentid").val(); 
		var accId=$("#hacademicYearId").val();
		
$.ajax({
			type : "POST",
			url : "studentRegistration.html?method=getstudentattendance",
			data : {
				"studentid":studentid,
				"accId":accId,
				"locationId":$("#hiddenlocId").val()			
			},
			async : false,
			
			success : function(response) {

				var result = $.parseJSON(response);

			
				for (var j = 0; j < result.studentattendance.length; j++) {
			
					$("#attendancetable #allstudent").append("<tr>"
							+"<td>" +result.studentattendance[j].sno+"</td>" 
							+"<td> "+result.studentattendance[j].attendancestatus+"</td>"
							+"<td> "+result.studentattendance[j].date+" </td>"
							+"</tr>");
					}	
			}
			
		});
	});
	
	$('#libraryInfo').click(function(){
		$('#libraryinfotable').show();
		$('#contacttable').hide();
		$('#individualtable').hide();
		$('#examdetailtable').hide();
		$('#feedetailtable').hide();
		$('#attendancetable').hide();
		$('#appraisaltable').hide();
		
		
		$('#libraryinfotable').empty();
		$("#libraryinfotable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Accession No</th>" +
				"<th>Title</th>" +
				"<th>Author</th>"+
				"<th>Issue Date</th>"+
				"<th>Return Date</th>"+
				"<th>Status</th>"+
				"</center></tr>" +
				"</table>"
		);
		
		$("#libraryinfotable #allstudent").append(
				"<tr>"+
				"<td>1</td>"+
				"<td>12345</td>"+
				"<td>Wings Of Fire</td>"+
				"<td>Dr.A.P.J.Abdul Kalam</td>"+
				"<td>11-May-2017</td>"+
				"<td>30-May-2017</td>"+
				"<td>Pending</td>"+
				+"</tr>"
		);
	});
	
	$('#appraisal').click(function(){
		 
		$('#appraisaltable').show();
		$('#studenttable').hide();
		$('#individualstudenttable').hide();
		$('#examdetailtable').hide();
		$('#feedetailtable').hide();
		$('#attendancetable').hide();
		$('#libraryinfotable').hide();
		
		$('#appraisaltable').empty();
		$("#appraisaltable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Schedule Date</th>" +
				"<th>Recommended By</th>" +
				"<th>Action Taken</th>"+
				"<th>Remarks</th>"+
				"<th>Status</th>"+
				"</center></tr>" +
				"</table>"
		);
		
		var acyearid=$("#hacademicYearId").val();
		var loc_id=$("#hiddenlocId").val();
		var stu_id=$("#hstudentid").val();
     $.ajax({
			
			type : "POST",
			url : "studentRegistration.html?method=getStudentAppraisal",
			data : {"acyearid":acyearid,
					"loc_id":loc_id,
					"stu_id":stu_id
//					"locationId":locationId			
			},
			async : false,
			
			success : function(response) {

				var result = $.parseJSON(response);

			
				for (var j = 0; j < result.studentappraisal.length; j++) {
			
					$("#appraisaltable #allstudent").append("<tr>"
							+"<td>"+result.studentappraisal[j].sno+"</td>" 
							+"<td> "+result.studentappraisal[j].date+" </td>"
							+"<td> "+result.studentappraisal[j].recommendedby+"</td>"
							+"<td> "+result.studentappraisal[j].actiontaken+" </td>"
							+"<td> "+result.studentappraisal[j].remarksstatus+" </td>"
							+"<td> "+result.studentappraisal[j].status+" </td>"
							+"</tr>");
					}	
			   }
			
		});
	});

function showContactDetails(){
	
	var studentId = $('#hstudentid').val();
	var accyear = $('#hacademicYearId').val();
	var locationId = $('#hiddenlocId').val();

	
	$('#studenttable').show();
	$('#individualstudenttable').hide();
	$('#examdetailtable').hide();
	$('#attendancetable').hide();
	$('#appraisaltable').hide();
	$('#feedetailtable').hide();
	
	$('#studenttable').empty();
	$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
			+"<center><tr><th>SI No</th>"+
			"<th>Relationship</th>" +
			"<th>Name</th>" +
			"<th>Mobile No</th>"+
			"</center></tr>" +
			"</table>" );
	
	$.ajax({
		
		type : "POST",
		url : "studentRegistration.html?method=individualStudentcontact",
		data : {"studentId":studentId,
				"accyear":accyear,
				"locationId":locationId			
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


});

function getExamCodeList(){
	var loc_id=$("#hiddenlocId").val();
	var acyear=$("#hacademicYearId").val();
	 $.ajax({
			type : "POST",
			url : "studentRegistration.html?method=getexaminationcodeslist",
			data : {
				    "loc_id":loc_id,
					"acyear":acyear
					},
			
			success : function(response) {
                var result = $.parseJSON(response);
				$('#examdetailtable #examcodeId').empty();
				$('#examdetailtable #examcodeId').append('<option value="">----------Select----------</option>');
				for ( var j = 0; j < result.examCodeslist.length; j++) {
					
					$('#examdetailtable #examcodeId').append(
							'<option value="'
						+ result.examCodeslist[j].examid + '">'+ result.examCodeslist[j].examcode	+ '</option>');
				}
				
				var examcode=$("#examcodeId option:selected").text().trim();
	        	  $.ajax({
	      			type : 'POST',
	      			url : "studentRegistration.html?method=getExamname",
	      			data: {'examcode':examcode},
	      			async : false,
	      			success : function(response) {
	      				var result = $.parseJSON(response);
	      				                   $('#examdetailtable #examname').html("");
	    					        $('#examdetailtable #examname').append(result.examnamelist);
	                                  }
	      			
	      		}); 
	        	  getSubjectsmarkbasedExams();
			}
			
		});
	// $("#examcodes option[value="+$("#examcodes").val().trim()+"]").attr("selected",true);
}

function getStudentSubjectList(){
	var classid=$("#hiddenclassid").val();
	var sectionid=$("#hiddensectionid").val();
	var studentid=$("#hstudentid").val();
	var acyyear=$("#hacademicYearId").val();
	var locid=$("#hiddenlocId").val();
	
	  $.ajax({
			type : "POST",
			url : "studentRegistration.html?method=getexaminationdetailsofthestudent",
			data : {"classid":classid,
					"sectionid":sectionid,
					"studentid":studentid,
					"acyyear":acyyear,
					"locid":locid
			},
			
			success : function(response) {

				var result = $.parseJSON(response);

				$("#examdetailtable #allstudent").append("<br>"+"<br>");
				$("#examdetailtable").append("<table class='table' id='allstudent' width='100%'" +">"
						+"<center><tr><th>SI No</th>"+
						"<th>Subject Code</th>" +
						"<th>Subject Name</th>" +
						"<th>Pass Marks</th>"+
						"<th>Total Marks</th>"+
						"<th>Marks Scored</th>"+
						"<th>Grade</th>"+
						"</center></tr>" +
						"</table>"
				);
				for (var j = 0; j < result.examDetailsList.length; j++) {
					
					$("#examdetailtable #allstudent").append("<tr>"
							+"<td>"+result.examDetailsList[j].sno1+"</td>" 
							+"<td> "+result.examDetailsList[j].subCode+" </td>"
							+"<td> "+result.examDetailsList[j].subjectName+"</td>"
							+"<td> "+result.examDetailsList[j].passmarks+" </td>"
							+"<td> "+result.examDetailsList[j].tot_marks+" </td>"
							+"<td> "+result.examDetailsList[j].scoredmarks+" </td>"
							+"<td> "+result.examDetailsList[j].gradename+" </td>"
							+"</tr>");
					}	
			}
			
		});
	  
}


function getSubjectsmarkbasedExams() {

var classid=$("#hiddenclassid").val();
	var sectionid=$("#hiddensectionid").val();
	var studentid=$("#hstudentid").val();
	var acyyear=$("#hacademicYearId").val();
	var locid=$("#hiddenlocId").val();
	var examcode=$("#examcodeId").val();
	var examname=$("#examname").val();
	  $.ajax({  
			type : "POST",
			url : "studentRegistration.html?method=getexaminationdetailsofthestudentbasedexams",
			data : {
				    "classid":classid,
					"sectionid":sectionid,
					"studentid":studentid,
					"acyyear":acyyear,
					"locid":locid,
					"examcode":examcode,
					"examname":examname
					
					
			},
			
			success : function(response) {

				var result = $.parseJSON(response);

				$("#examdetailtable #allstudent").empty();
				$("#examdetailtable").append("<table class='table' id='allstudent' width='100%'" +">"
						+"<center><tr><th>SI No</th>"+
						"<th>Subject Code</th>" +
						"<th>Subject Name</th>" +
						/*"<th>Pass Marks</th>"+*/
						"<th>Total Marks</th>"+
						"<th>Scored Marks</th>"+
						/*"<th>Grade</th>"+*/
						"</center></tr>" +
						"</table>"
				);
				if(result.examDetailsList.length>0){
					for (var j = 0; j < result.examDetailsList.length; j++) {
						
						$("#examdetailtable #allstudent tbody").append("<tr>"
								+"<td>"+result.examDetailsList[j].sno1+"</td>" 
								+"<td> "+result.examDetailsList[j].subCode+" </td>"
								+"<td> "+result.examDetailsList[j].subjectName+"</td>"
								//+"<td> "+result.examDetailsList[j].passmarks+" </td>"
								+"<td> "+result.examDetailsList[j].tot_marks+" </td>"
								+"<td> "+result.examDetailsList[j].scoredmarks+" </td>"
								//+"<td> "+result.examDetailsList[j].gradename+" </td>"
								+"</tr>");
						}
				}else{
					$("#examdetailtable #allstudent tbody").append("<tr>"
							+"<td colspan='5'>No Records Found</td>" 
							+"</tr>");
				}
					
			}
			
		});
}

function HideError(pointer) 
{
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}
 
