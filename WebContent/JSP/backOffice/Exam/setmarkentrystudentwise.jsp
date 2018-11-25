<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="JS/backOffice/Exam/setmarkentrystudentwise.js"></script>
<style>
#allstudent tr td {
    color: black;
    text-align: center;
    padding: 5px;

}
.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
    background-color: #eee;
    opacity: 1;
    width: 191px;
}

.scrollbar{

	overflow-y:scroll; 
	height: 400px;
}
input{
width: 100%;
}

</style>
</head>
<body>
	<form method="POST" action="examTimetablePath.html?method=AddMarkEntryStudentWise" id="myform">
		<input type="hidden" name="myValue" value="" id="myValue"/>
	</form>	

	<form method="POST" action="examTimetablePath.html?method=setMarkEntryStudentandClasswise" id="backform">
		<input type="hidden" name="formValue" value="" id="backValue"/>
	</form>	
	
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Mark
				Entry Student Wise</span>
		</p>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"><bean:write name="successmessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

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

  <input type="hidden" id="historyacayearId"
  value='<logic:present name="historyacayearId"  scope="request"><bean:write name="historyacayearId" /></logic:present>' />
  
  <input type="hidden" id="historylocId"
  value='<logic:present name="historylocId"  scope="request"><bean:write name="historylocId" /></logic:present>' />


		<input type="hidden" name="hiddenexamid" id="hiddenexamid"
			value='<logic:present name="examid" scope="request"><bean:write name="examid"></bean:write></logic:present>' />

		<input type="hidden" id="hiddenstartaccyear"
			value='<logic:present name="startDate"  scope="request"><bean:write name="startDate" /></logic:present>' />
		<input type="hidden" id="hiddenendaccyear"
			value='<logic:present name="endDate" scope="request"><bean:write name="endDate"  /></logic:present>' />

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a href="#" style="color: #767676"><h4 class="panel-title examdetails"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Mark
							Entry-Student Wise
						</h4></a>
					
					<div class="navbar-right">
	 					<span class="buttons" id="back1">Back</span>
					</div>
				</div>
				
				<div class='clearfix' id="inputcolor" style="margin-top: 10px;">
					<div class="col-md-6" id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
					   <div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: left; line-height: 35px;">Branch</label>
							<div class="col-xs-6">
								<input type="text" readonly="readonly" name="location" style="text-align:left;width: 100%;"
									onkeypress="HideError()" id="location" class="form-control"
									value='<logic:present name="currentlocation" scope="request"><bean:write name="currentlocation" ></bean:write></logic:present>' />
							</div>
							<input type="hidden" name="hiddenlocid" id="hiddenlocid"
								value='<logic:present name="locationid" scope="request"><bean:write name="locationid"></bean:write></logic:present>' />
						</div>
		
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: left; line-height: 35px;">Class</label>
							<div class="col-xs-6">
								<input type="text" readonly="readonly" name="classname"
									onkeypress="HideError()" id="classname" class="form-control" style="text-align:left;width: 100%;"
									value='<logic:present name="classname" scope="request"><bean:write name="classname" ></bean:write></logic:present>' />
							</div>
							<input type="hidden" name="classname" id="hiddenclassid"
								value='<logic:present name="classId" scope="request"><bean:write name="classId"></bean:write></logic:present>' />
						</div>
						
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: left; line-height: 35px;">Exam Code</label>
							<div class="col-xs-6">
								<input type="text" readonly="readonly" name="examCode" style="text-align:left;width: 100%;"
									onkeypress="HideError()" id="examCode" class="form-control"
									value='<logic:present name="examcode" scope="request"><bean:write name="examcode" ></bean:write></logic:present>' />
							</div>
							<input type="hidden" name="hiddenclassid" id="hiddenclassid" value='<logic:present name="classId" scope="request"><bean:write name="classId"></bean:write></logic:present>'/>
						</div>
						
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								style="text-align: left; line-height: 35px;">Specialization</label>
							<div class="col-xs-6">
								<select class="form-control" tabindex="9" name="specilization" id="specilization">
									<option value="">-----------Select------------</option>
								</select>
							</div>
						</div>
						
					</div>
					<div class="col-md-6" id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: left; line-height: 35px;">Academic Year</label>
							<div class="col-xs-6">
								<input type="text" readonly="readonly" name="accyear"
									onkeypress="HideError()" id="accyear" class="form-control" style="text-align:left;width: 100%;"
									value='<logic:present name="accyName" scope="request"><bean:write name="accyName" ></bean:write></logic:present>' />
							</div>
							<input type="hidden" name="hiddenaccyid" id="hiddenaccyid"
								value='<logic:present name="accyId" scope="request"><bean:write name="accyId"></bean:write></logic:present>' />
						</div>
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;" id="hiddensectionlabel">Division</label>
							<div class="col-xs-6">
								<input type="text" readonly="readonly" name="sectionName"
									onkeypress="HideError()" id="sectionName" class="form-control" style="text-align:left;width: 100%;"
									value='<logic:present name="sectionname" scope="request"><bean:write name="sectionname" ></bean:write></logic:present>' />
							</div>
							<input type="hidden" name="hiddensectionid" id="hiddensectionid" value='<logic:present name="sectionId" scope="request"><bean:write name="sectionId"></bean:write></logic:present>'/>
							<input type="hidden" name="hiddenspecid" id="hiddenspecid" value='<logic:present name="specilizationId" scope="request"><bean:write name="specilizationId"></bean:write></logic:present>'/>
						</div>
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: left; line-height: 35px;">Exam Name</label>
							<div class="col-xs-6">
								<input type="text" readonly="readonly" name="examName" style="text-align:left;width: 100%;"
									onkeypress="HideError()" id="examName" class="form-control"
									value='<logic:present name="examname" scope="request"><bean:write name="examname" ></bean:write></logic:present>' />
							</div>
						</div>
						<div class="form-group clearfix ">
							<label for="inputPassword" class="control-label col-xs-4"
								style="text-align: left; line-height: 35px;" id="inputnames">Search</label>
							<div class="col-xs-6">
								<input type="text" class="form-control" id="searchvalue" Placeholder="Search......">
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="hiddenaccyear" value='<logic:present name="accyear"  scope="request"><bean:write name="accyear" /></logic:present>' />
				<div class="form-group scrollbar">
					<table style="background: #fff;" class="allstudent" id="allstudent">
						<thead>
							<tr style='background: #f5f5f5;'>
								<th style="font-size: 13px; text-align: center; width: 95px; padding: 9px;">Roll No</th>
								<th style="font-size: 13px; text-align: left; padding: 9px;">Admission No</th>
								<th style="font-size: 13px; text-align: left; padding: 9px;">Student Name</th>
								<th style="font-size: 13px; text-align: left; padding: 9px;">Specialization Name</th>
				                <th style="font-size: 13px; text-align: center; padding: 9px;">Status</th>
							</tr>
						</thead>
						<tbody>
							<logic:present name="studentlist" scope="request">
								<logic:iterate id="studentlist" name="studentlist" scope="request">
									<tr>
										<td class="rollno"><bean:write name="studentlist" property="studentrollno"/></td>
									    <td style="text-align: left;" class="admissionno"><bean:write name="studentlist" property="admissionno"/>
										<td style="text-align: left;" class="studentname"><bean:write name="studentlist" property="studentname"/><input type="hidden" class="studentid" value='<bean:write name="studentlist" property="studentid"/>' /> </td>
										<td style="text-align: left;" class="specialization" id="<bean:write name="studentlist" property="specializationId"/>"><bean:write name="studentlist" property="specialization"/></td>
										<td><span class="<bean:write name="studentlist" property="status"/>"><bean:write name="studentlist" property="status"/></span></td>
									</tr>
								</logic:iterate>
							</logic:present>
						</tbody>
					</table>
				</div>
				</div>
			</div>
		</div>
</body>

</html>
