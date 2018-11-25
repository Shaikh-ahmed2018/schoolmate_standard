<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>
<script type="text/javascript" src="JS/backOffice/Exam/Addmarkentrystudentwise.js"></script>
<link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css">
<script>
function CheckIsNumeric(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode > 31  &&(charCode < 48 || charCode > 57)) {
        /* document.getElementById("maxmarks").style.backgroundColor = "#FFB2B2"; */
        return false;
    }
    else {
        /* document.getElementById("maxmarks").style.backgroundColor = "#B2D8B2"; */
        return true;
    }
}
</script>
</head>
<style>
.glyphicon:hover {
	cursor: pointer;
}

.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
.Pending{
background-color:#FF0000;
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
.Completed{
background-color:rgba(0, 158, 0, 0.66);
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
#allstudent th:nth-child(1){
width:100px;
}
#allstudent td:nth-child(1){
text-align: center;
}
.form-group{
margin-bottom: 10px;}

.scrollbar{

	overflow-y:scroll; 
	height: 250px;
}
#allstudent th{
font-size: 15px; 
text-align: left;
}

/* .sticky{
	position: -webkit-sticky;
  	position: sticky;
  	top: -1px;
  	padding: 5px;
  	border: 1px;
  	background: #f5f5f5;
}  */

.dnotebook{
display: none;
}
.dsubjectenrichment{
display: none;
}
.dperiodictest{
display: none;
}
.dhalfyearly{
display: none;
}
</style>


<body>
	<form method="POST" action="examTimetablePath.html?method=setMarkEntryStudentwise" id="myform">
		<input type="hidden" name="myValue" value="" id="myValue"/>
	</form>	
<div id="loader" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
<div id="dialog"></div>
	<div class="content" id="div1">
		<div class="" id="div2">
			<p style="">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Marks Entry - Student Wise</span>
			</p>
		</div>

		
	<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<%-- <div class="accademicyear clearfix" style="margin-bottom: 10px;margin-top: 10px;">
		<div class="col-md-4" style="font-size: 15px;color: #5d5d5d;" id="txtstyle">
			<div class="form-group">
				<label for="inputEmail" class="control-label col-xs-5"
					style="text-align: left; line-height: 35px;">Academic Year</label>
				<div class="col-xs-7">
					<select id="accyear" name="accyear" class="form-control" required>
						<logic:present name="AccYearList">
						<logic:iterate id="AccYear" name="AccYearList">
							<option value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
						</logic:iterate>
					</logic:present>
				   </select>
				</div>
			</div>
		</div>
		
		<input type ="hidden" id ="hiddenaccid" name="hiddenaccid" value="<logic:present name="accyear"><bean:write name="accyear"/></logic:present>"/>
		
		</div> --%>
		
		
		
		
		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#" style="color: #fff;"><h3
						class="panel-title grade" style="color: #767676; line-height: 18px;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Student Wise Marks List
					</h3></a>
					
					<div class="navbar-right">
           				<span class="buttons" id="save">Save</span>
           			 	<span class="buttons" id="back1">Back</span>
    			 </div>
			</div>
			
			
			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Download</h4>
						</div>
						<div class="modal-body">
							<span id="excelDownload"><img src="images/xl.png"
								class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>

  <input type="hidden" id="historyacayearId"
  value='<logic:present name="historyacayearId"  scope="request"><bean:write name="historyacayearId" /></logic:present>' />
  
  <input type="hidden" id="historylocId"
  value='<logic:present name="historylocId"  scope="request"><bean:write name="historylocId" /></logic:present>' />
			

			<div id="gradeOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<div class='clearfix' id='inputcolor'>
								<div class="col-md-12" >
										<div class="col-md-6"  id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
			                         
								
			</div>
			</div>
			
			<div class="col-md-12">
			
				<div class="col-md-6"  id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
				
				 <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: left; line-height: 35px;">Branch</label>
									<div class="col-xs-6">
										<input type="text" readonly="readonly" name="branch" onkeypress="HideError()" id="branch" style="text-align: left;"
											class="form-control" value='<logic:present name="currentlocation" scope="request" ><bean:write name="currentlocation" ></bean:write></logic:present>'/>
									</div>
									<input type="hidden" name="hiddenlocation" id="hiddenlocation" value='<logic:present name="locationid" scope="request"><bean:write name="locationid"></bean:write></logic:present>'/>
								</div>
				
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Admission No</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="admissionno" onkeypress="HideError()" id="subcode" class="form-control" value='<logic:present name="admno"  scope="request"><bean:write name="admno"/></logic:present>'/>
						</div>
						<input type="hidden" name="hiddenclassid" id="hiddenclassid" value='<logic:present name="subid" scope="request"><bean:write name="subid"></bean:write></logic:present>'/>
				</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Class</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="classid" onkeypress="HideError()" id="classid" class="form-control" value='<logic:present name="classname" scope="request" ><bean:write name="classname"></bean:write></logic:present>'/>
						</div>
						<input type="hidden" name="classidhidden" id="classidhidden" value='<logic:present name="classId" scope="request"><bean:write name="classId"></bean:write></logic:present>'/>
						<input type="hidden" name="hiddenspecid" id="hiddenspecid" value='<logic:present name="specilizationId" scope="request"><bean:write name="specilizationId"></bean:write></logic:present>'/>
				</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Exam Code</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="examCode" onkeypress="HideError()" id="examCode" class="form-control" value='<logic:present name="examcode"><bean:write name="examcode"></bean:write></logic:present>'/>
						</div>
						<input type="hidden" name="hiddenexamid" id="hiddenexamid" value='<logic:present name="examid" scope="request"><bean:write name="examid"></bean:write></logic:present>'/>
				</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Start Date</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="startdate" onkeypress="HideError()" id="startdate" class="form-control" value='<logic:present name="startdate" scope="request"><bean:write name="startdate"></bean:write></logic:present>'/>
						</div>
				</div>
				
				<div class="form-group clearfix work">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Work Edu.Grade</label>
						<div class="col-xs-6">
						 	<select class="form-control selectgrade" id="workedu">
						 		<option value="">----------Select----------</option>
						 		<option value="A">A</option>
						 		<option value="B">B</option>
						 		<option value="C">C</option>
						 		<option value="D">D</option>
						 		<option value="E">E</option>
						 	</select>
						</div>
						<input type="hidden" id="hiddenwork" value="<logic:present name="workedu_marks" scope="request"><bean:write name="workedu_marks"></bean:write></logic:present>"/>
						<input type="hidden" id="hiddenart" value="<logic:present name="artedu_marks" scope="request"><bean:write name="artedu_marks"></bean:write></logic:present>"/>
						<input type="hidden" id="hiddenhealth" value="<logic:present name="healthedu_marks" scope="request"><bean:write name="healthedu_marks"></bean:write></logic:present>"/>
						<input type="hidden" id="hiddendiscipline" value="<logic:present name="discipline_marks" scope="request"><bean:write name="discipline_marks"></bean:write></logic:present>"/>
						<input type="hidden" id="hiddenexamtypeprefix" value="<logic:present name="examtypeprefix" scope="request"><bean:write name="examtypeprefix"></bean:write></logic:present>"/>
				</div>
				
				<div class="form-group clearfix art">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Art Edu.Grade</label>
						<div class="col-xs-6">
						 	<select class="form-control selectgrade" id="artedu">
						 		<option value="">----------Select----------</option>
						 		<option value="A">A</option>
						 		<option value="B">B</option>
						 		<option value="C">C</option>
						 		<option value="D">D</option>
						 		<option value="E">E</option>
						 	</select>
						</div>
				</div>
			</div>		
			
			
			
			<div class="col-md-6"  id="txtstyle"style="font-size: 11pt; color: #5d5d5d;">
			
				<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: left; line-height: 35px;">Academic Year</label>
									<div class="col-xs-6">
										<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear" style="text-align: left;"
											class="form-control" value='<logic:present name="accyName" scope="request" ><bean:write name="accyName" ></bean:write></logic:present>'/>
									</div>
									<input type="hidden" name="hiddenaccyid" id="hiddenaccyid" value='<logic:present name="accyear" scope="request"><bean:write name="accyear"></bean:write></logic:present>'/>
								</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: left; line-height: 35px;">Student Name</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="studentname" onkeypress="HideError()" id="studentname" class="form-control" value='<logic:present name="stdname" scope="request" ><bean:write name="stdname"/></logic:present>'/>
						</div>
						<input type="hidden" name="hiddenstudentid" id="hiddenstudentid" value='<logic:present name="hiddenstudentid" scope="request"><bean:write name="hiddenstudentid"></bean:write></logic:present>'/>
						
				</div>				
				
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: left; line-height: 35px;" id="hiddensectionlabel">Division</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="sectionid" onkeypress="HideError()" id="sectionid" class="form-control" value='<logic:present name="sectionname" scope="request" ><bean:write name="sectionname" ></bean:write></logic:present>'/>
						</div>
						<input type="hidden" name="hiddensectionid" id="hiddensectionid" value='<logic:present name="sectionId" scope="request"><bean:write name="sectionId"></bean:write></logic:present>'/>
				</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: left; line-height: 35px;">Exam Name</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="examName" onkeypress="HideError()" id="examName" class="form-control" value='<logic:present name="examname" scope="request"><bean:write name="examname"></bean:write></logic:present>'/>
						</div>
				</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: left; line-height: 35px;">End Date</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="enddate" onkeypress="HideError()" id="enddate" class="form-control" value='<logic:present name="enddate" scope="request"><bean:write name="enddate"></bean:write></logic:present>'/>
						</div>
				</div>
				
				<div class="form-group clearfix health">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: left; line-height: 35px;">Health Edu.Grade</label>
						<div class="col-xs-6">
						 	<select class="form-control selectgrade" id="healthedu">
						 		<option value="">----------Select----------</option>
						 		<option value="A">A</option>
						 		<option value="B">B</option>
						 		<option value="C">C</option>
						 		<option value="D">D</option>
						 		<option value="E">E</option>
						 	</select>
						</div>
				</div>
				<div class="form-group clearfix discipline">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: left; line-height: 35px;">Discipline Grade</label>
						<div class="col-xs-6">
						 	<select class="form-control selectgrade" id="disciplineedu">
						 		<option value="">----------Select----------</option>
						 		<option value="A">A</option>
						 		<option value="B">B</option>
						 		<option value="C">C</option>
						 		<option value="D">D</option>
						 		<option value="E">E</option>
						 	</select>
						</div>
				</div>
			</div>	
			</div>	
		</div>
				<!-- <div id = "markstable"></div> -->
					<div class="form-group scrollbar">
					 
						<table style="background: #fff;" class="table" id="allstudent">
							<thead>
							<tr>
								<th colspan="10">Non Practical Subject Details</th>
							</tr>
							
							<tr style="background: #f5f5f5;">
								<th class="sticky" rowspan="2" style="vertical-align: middle;text-align: center;width: 10px;">Sl.No</th>
								<th class="sticky"  rowspan="2" style="vertical-align: middle;text-align: left;">Subject Code</th>
								<th class="sticky" rowspan="2" style="vertical-align: middle;text-align: left;">Subject Name</th>
								<th class="sticky" rowspan="2" style="vertical-align: middle;text-align: center;">Attendance</th>
								<!-- <th class="sticky">Per Test</th> -->
								<th class="sticky dnotebook" colspan="2" style="text-align: center;">Note Book</th>
								<th class="sticky dsubjectenrichment" colspan="2" style="text-align: center;">Subject Enrichment</th>
								<th class="sticky dperiodictest" colspan="2" style="text-align: center;">Periodic Test Marks</th>
								<th class="sticky dhalfyearly chgname" colspan="2" style="text-align: center;">Half Yearly Marks</th>
								
								<!-- <th class="sticky">Max.Marks</th>
								<th class="sticky">Scored Marks</th> -->
						   </tr>
						   <tr style="background: #f5f5f5;">
						   
								<th class="sticky dnotebook" style="text-align: center;">Max.Marks</th>
								<th class="sticky dnotebook" style="text-align: center;">Scored Marks</th>
								<th class="sticky dsubjectenrichment" style="text-align: center;">Max.Marks</th>
								<th class="sticky dsubjectenrichment" style="text-align: center;">Scored Marks</th>
								<th class="sticky dperiodictest" style="text-align: center;width: 200px;">Max.Marks</th>
								<th class="sticky dperiodictest" style="text-align: center;width: 200px;">Scored Marks</th>
								<th class="sticky dhalfyearly" style="text-align: center;">Max.Marks</th>
								<th class="sticky dhalfyearly" style="text-align: center;">Scored Marks</th>
						   </tr>
						  </thead>
						  <tbody>
		                    <logic:present name="markdetailslist" scope="request">
						   	<logic:iterate id="markdetailslist" name="markdetailslist" scope="request">
	                         <tr id='rowId<bean:write name="markdetailslist" property="sno1"/>'>
						   		<td class="sno"><bean:write name="markdetailslist" property="sno1"/></td>
	
						   		<td style="text-align: left;" class="subjectcode" style="text-align: center;"><bean:write name="markdetailslist" property="subCode"/><input type="hidden" class="subprimid" value='<bean:write name="markdetailslist"  property="studenprimid"/>' /></td>
						   		<td style="text-align: left;" class="subjectname"><bean:write name="markdetailslist" property="subjectName"/><input type="hidden" class="subid" value='<bean:write name="markdetailslist" property="subId"/>' /> </td>
								<td class="attandancestatus" style="text-align: center;">
									<select class="statusvalue <bean:write name="markdetailslist" property="attendace"/>"  id="<bean:write name="markdetailslist" property="sno1"/>" style="background-color: turquoise;">
										<option value="Present">Present</option>
										<option value="Absent">Absent</option>
									</select>
								</td>
								<td class="dnotebook"><input type="text" class="maxnotebook" style="width: 100px;background-color:turquoise;" onkeypress ="return CheckIsNumeric(event);" value="<bean:write name="markdetailslist" property="max_notebook_marks"/>"/></td>
								<td class="dnotebook"><input type="text" class="notebook" style="width: 100px;background-color:turquoise;" onkeypress ="return CheckIsNumeric(event);" value="<bean:write name="markdetailslist" property="notebooks"/>"/></td>
								<td class="dsubjectenrichment"><input type="text" class="maxsubjectenrichment" style="width: 100px;background-color:turquoise;" onkeypress ="return CheckIsNumeric(event);" value="<bean:write name="markdetailslist" property="max_subenrich_marks"/>"/></td>
								<td class="dsubjectenrichment"><input type="text" class="subjectenrichment" style="width: 100px;background-color:turquoise;" onkeypress ="return CheckIsNumeric(event);" value="<bean:write name="markdetailslist" property="subjectenrichmarks"/>"/></td>
								<td class="dperiodictest"><input type="text" class="maxperiodic" style="text-align: center;width: 200px;background-color:turquoise;" onkeypress ="return CheckIsNumeric(event);" value="<bean:write name="markdetailslist" property="maxperiodicmark"/>"/></td>
								<td class="dperiodictest"><input type="text" class="scoredperiodic" onkeypress ="return CheckIsNumeric(event);" style="text-align: center;background-color:turquoise;width: 200px;" value="<bean:write name="markdetailslist" property="pertest"/>"/></td>
								<td class="dhalfyearly"><input type="text" class="maxhalfyearly" style="width: 100px;background-color:turquoise;" onkeypress ="return CheckIsNumeric(event);" value="<bean:write name="markdetailslist" property="tot_marks"/>"/></td>
								<td class="dhalfyearly"><input type="text" class="scoredhalfyearly" onkeypress ="return CheckIsNumeric(event);" style="background-color:turquoise;width: 115px;" value="<bean:write name="markdetailslist" property="scoredmarks"/>"/></td>
						   </tr>
						   	</logic:iterate>
						   </logic:present>

							<logic:present name="labdetailslist" scope="request">
								<tr>
									<th colspan="10">Practical Details</th>
								</tr>
								
							<logic:iterate id="labdetailslist" name="labdetailslist" scope="request">
		                        <tr id='rowId<bean:write name="labdetailslist" property="sno1"/>'>
							   		<td class="sno"><bean:write name="labdetailslist" property="sno1"/></td>
		
							   		<td style="text-align: left;" class="subjectcode" style="text-align: center;"><bean:write name="labdetailslist" property="subCode"/><input type="hidden" class="subprimid" value='<bean:write name="labdetailslist"  property="studenprimid"/>' /></td>
							   		<td style="text-align: left;" class="subjectname"><bean:write name="labdetailslist" property="subjectName"/><input type="hidden" class="subid" value='<bean:write name="labdetailslist" property="subId"/>' /> </td>
									<td class="attandancestatus" style="text-align: center;">
										<select class="statusvalue <bean:write name="labdetailslist" property="attendace"/>"  id="<bean:write name="labdetailslist" property="sno1"/>" style="background-color: turquoise;">
											<option value="Present">Present</option>
											<option value="Absent">Absent</option>
										</select>
									</td>
									<td class="dnotebook"><input type="text" class="maxnotebook" style="width: 100px;background-color:turquoise;" onkeypress ="return CheckIsNumeric(event);" value="<bean:write name="labdetailslist" property="max_notebook_marks"/>"/></td>
									<td class="dnotebook"><input type="text" class="notebook" style="width: 100px;background-color:turquoise;" onkeypress ="return CheckIsNumeric(event);" value="<bean:write name="labdetailslist" property="notebooks"/>"/></td>
									<td class="dsubjectenrichment"><input type="text" class="maxsubjectenrichment" style="width: 100px;background-color:turquoise;" onkeypress ="return CheckIsNumeric(event);" value="<bean:write name="labdetailslist" property="max_subenrich_marks"/>"/></td>
									<td class="dsubjectenrichment"><input type="text" class="subjectenrichment" style="width: 100px;background-color:turquoise;" onkeypress ="return CheckIsNumeric(event);" value="<bean:write name="labdetailslist" property="subjectenrichmarks"/>"/></td>
									<td class="dperiodictest"><input type="text" class="maxperiodic" style="text-align: center;width: 200px;background-color:turquoise;" onkeypress ="return CheckIsNumeric(event);" value="<bean:write name="labdetailslist" property="maxperiodicmark"/>"/></td>
									<td class="dperiodictest"><input type="text" class="scoredperiodic" onkeypress ="return CheckIsNumeric(event);" style="text-align: center;background-color:turquoise;width: 200px;" value="<bean:write name="labdetailslist" property="pertest"/>"/></td>
									<td class="dhalfyearly"><input type="text" class="maxhalfyearly" style="width: 100px;background-color:turquoise;" onkeypress ="return CheckIsNumeric(event);" value="<bean:write name="labdetailslist" property="tot_marks"/>"/></td>
									<td class="dhalfyearly"><input type="text" class="scoredhalfyearly" onkeypress ="return CheckIsNumeric(event);" style="background-color:turquoise;width: 115px;" value="<bean:write name="labdetailslist" property="scoredmarks"/>"/></td>
							   	</tr>
						   	</logic:iterate>
							</logic:present>
						</tbody>
					  </table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
