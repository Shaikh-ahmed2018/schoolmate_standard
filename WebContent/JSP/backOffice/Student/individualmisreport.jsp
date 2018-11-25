<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="JS/backOffice/Student/individualmisreport.js"></script>

<style>
.feedetailtable:HOVER{
 cursor: pointer;
}
.Paid{
    background-color: green;
    min-width: 80px;
    display: inline-block;
    text-align: center;
    color: #fff !important;
}
.Not{
    background-color: red;
    min-width: 80px;
    display: inline-block;
    text-align: center;
    color: #fff !important;
}
.Pending{
    background-color: #00c4ff;
        /* border: 1px solid #cfcece; */
    min-width: 80px;
    display: inline-block;
    text-align: center;
    color: white !important;
}
#allstudent th:nth-child(1) {
    text-align: center;
    width: 70px;
} 
.table {
    max-width: 100%;
    margin-bottom: 20px;
    margin: 0 auto;
}

#examname {
border: 1px solid #ddd;
padding: 0 10px;
}
.feeAmount,.fineAmount,.totalAmount{
text-align: right;
}
#examdetailtable label,#examdetailtable select
{
    font-family: Roboto,sans-serif;
    font-size: 13px;
    color: #000;
}
.navbar-right span{
  top: -3px;
}
</style>

</head>

<body>
	<div class="content" id="div-main">
		<p><img src="images/addstu.png" style="vertical-align: top;"/><span id="pageHeading">MIS Report</span></p>
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
	
		<div class="panel panel-primary">
			<div class="panel-heading">
			
					<h3 class="panel-title" style="color: #000000; vertical-align: text-top;"> 
					<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;MIS Report
					</h3>
						
						<div class="navbar-right">
							<span class="search"  style="top:0px">
							<label style="text-align: right;color:#000000;font-family:inherit;font-weight: 500;font-size: 15px;padding: 4px;">Select Action</label>	 
							 				<select id="goto" class="nil" onchange="HideError(this)" style="color:#000000;font-family:inherit;font-size: 14px;padding: 2px">
							 					<option value="">----------Select----------</option>
							 					
							 					
				            <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="STUDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<option value="reg_Form">Registration Form</option>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
				            <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="DISACTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<option value="conf_Report">Disciplinary Action</option>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
				            <%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="STUIDSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<option value="id_Card">Single ID Card</option>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							 					
							 					
							 					
							 					
							 					<!-- <option value="mis_Report">MIS Report</option>
							 					<option value="tc">Transfer Certificate</option> -->
											 </select>
							</span>
							<span class="buttons" id="goPage" class="save">Go</span>
							<span id="back1" class="buttons">Back</span>
						</div>
					</div>
					<logic:iterate id="studentSearchList" name="studentSearchList">

	<input type="hidden" id="historyaccYear"  
	value="<logic:present name='historyaccYear' scope='request' ><bean:write name='historyaccYear'/></logic:present>"/>
	
	<input type="hidden" id="historylocId" 
	value="<logic:present name='historylocId' scope='request' ><bean:write name='historylocId'/></logic:present>"/>
	
	<input type="hidden" id="historyclassname" 
	value="<logic:present name='historyclassname' scope='request' ><bean:write name='historyclassname'/></logic:present>"/>
	
	<input type="hidden" id="historysectionid" 
	value="<logic:present name='historysectionid' scope='request' ><bean:write name='historysectionid'/></logic:present>"/>
	
	<input type="hidden" id="historysearchvalue" 
	value="<logic:present name='historysearchvalue' scope='request' ><bean:write name='historysearchvalue'/></logic:present>"/>
	
	<input type="hidden" id="historystatus" 
	value="<logic:present name='historystatus' scope='request' ><bean:write name='historystatus'/></logic:present>"/>					
					
					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="academicYear" tabindex="1"	onkeypress="HideError()" id="academicYear"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="academicYear"/></logic:present>' />
									</div>
								</div>
								
								
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<input type="text" name="schoolName" tabindex="1"	onkeypress="HideError()" id="schoolNames"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="location"/></logic:present>' />
									</div>
								</div>
								
								<input type="hidden" name="hiddenlocId"  id="hiddenlocId"
											value='<logic:present name="locId" ><bean:write name="locId" /></logic:present>' />
								<input type="hidden" id="hiddenbackId" name="srarchSt" value="<logic:present name='srarchSt' scope='request' ><bean:write name='srarchSt' /></logic:present>" />
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFullName" tabindex="1"	onkeypress="HideError()" id="studentFullName"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentFullName"/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="admissionNo" tabindex="4" onkeypress="HideError()" id="admissionNo"
											onchange="" maxlength="25" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentAdmissionNo"></bean:write></logic:present>' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Roll No</label>

									<div class="col-xs-7">
										<input type="text" name="studentRollNo" tabindex="1"	onkeypress="HideError()" id="studentRollNo"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentrollno"/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="classId" tabindex="1"	onkeypress="HideError()" id="classId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="classname"/></logic:present>' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<input type="text" name="sectionId" tabindex="1"	onkeypress="HideError()" id="sectionId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="sectionnaem"/></logic:present>' />
									</div>
								</div>
								
								 <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Bus Route </label>
									<div class="col-xs-7">
										<input type="text" name="routeId" tabindex="1"	onkeypress="HideError()" id="routeId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="route"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Bus Boarding Point</label>
									<div class="col-xs-7">
										<input type="text" name="stageId" tabindex="1"	onkeypress="HideError()" id="stageId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="stage_name"/></logic:present>' />
									</div>
								</div>
							</div>
							
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
												<img id="imagePreview" class="setImage" alt="image" src="#" style="height: 45mm; width: 45mm;">
												<div style="position: absolute;top: 0;height: 160px;">
												
												</div>
												
										</div>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student Status</label>
									<div class="col-xs-7">
										<input type="text" name="studentStatusId" tabindex="1"	onkeypress="HideError()" id="studentStatusId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentStatus"/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Confidential Status</label>
									<div class="col-xs-7">
										<input type="text" name="confidentialStatusId" tabindex="1"	onkeypress="HideError()" id="confidentialStatusId" 
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="confidentialStatus"/></logic:present>' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House</label>
									<div class="col-xs-7">
										<input type="text" name="houseId" tabindex="1"	onkeypress="HideError()" id="houseId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="houseName"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Second Language</label>
									<div class="col-xs-7">
										<input type="text" name="secondLanguageId" tabindex="1"	onkeypress="HideError()" id="secondLanguageId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="secondLanguage"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Third Language</label>
									<div class="col-xs-7">
										<input type="text" name="thirdLanguageId" tabindex="1"	onkeypress="HideError()" id="thirdLanguageId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="thirdLanguage"/></logic:present>' />
									</div>
								</div>
								
								<input type="hidden" id="hstudentid" name="studentId"
									value="<logic:present name="studentSearchList" property="studentId"><bean:write name="studentSearchList" property="studentId"/></logic:present>"/>
									
								<input type="hidden" id="hacademicYearId" name="academicYearId"
									value="<logic:present name="studentSearchList" property="studentId"><bean:write name="studentSearchList" property="academicYearId"/></logic:present>"/>
									
								<input type="hidden" id="hschoolNameId" name="schoolNameId"
									value="<logic:present name="studentSearchList" property="studentId"><bean:write name="studentSearchList" property="locationId"/></logic:present>"/>
								
								<input type="hidden" id="photohiddenid" name="previousImage"
									value="<logic:present name="studentSearchList" property="studentPhoto"><bean:write name="studentSearchList" property="studentPhoto" /></logic:present>">
									
									<input type="hidden" id="hiddenclassid" name="hiddenclassid"
									value="<logic:present name="studentSearchList" property="classDetailId"><bean:write name="studentSearchList" property="classDetailId" /></logic:present>">
									
									<input type="hidden" id="hiddensectionid" name="hiddensectionid"
									value="<logic:present name="studentSearchList" property="classSectionId"><bean:write name="studentSearchList" property="classSectionId" /></logic:present>">
									
									<input type="hidden" id="hiddenstatus" name="hiddenstatus"
									value="<logic:present name="studentSearchList" property="status"><bean:write name="studentSearchList" property="status" /></logic:present>">
									
							</div>
						</div>
						</logic:iterate>
						<hr style="height:1px;border:none;color:#333;background-color:#ddd;"/>
					
						<div>
						<div class="slideTab clearfix">
						<div class="tab">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#contacts"  id="contacts">Contacts</a></li>
								<li><a data-toggle="tab" href="#classHistory" id="classHistory">Class History</a></li>
								<li><a data-toggle="tab" href="#feedetails"  id="feedetails">Fee Details</a></li>
								<li><a data-toggle="tab" href="#attendance"  id="attendance">Attendance</a></li>
								<logic:present name="token_information" scope="session">
						<logic:equal value="e-campS" name="token_information" property="subtype">
								<li><a data-toggle="tab" href="#appraisal"  id="appraisal">Appraisal</a></li>
								<li><a data-toggle="tab" href="#examDetails"  id="examDetails">Exam Details</a></li>
					   </logic:equal>
								</logic:present>
								<!-- <li><a data-toggle="tab" href="#appraisal"  id="appraisal">Appraisal</a></li> -->
							</ul>
						<div id="contacts" class="tab-pane">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap">
									<div class="col-md-2"></div>
									<div id="studenttable" style="padding: 5px"></div>
									<div id="individualstudenttable" style="padding: 5px"></div>	
									<div id="examdetailtable" style="padding: 5px">
									<div class="newclass" style="padding: 5px">
								</div>
								    </div>
									<div id="feedetailtable" style="padding: 5px">
		<div class="panel panel-primary" style="margin-left: 12%; width: 70%; margin-bottom: -2px;">
			<div class="panel-heading school" style="height: 28px;">
					<h3 class="panel-title feedetailtable" style="color: #000000; vertical-align: text-top; position: absolute;top: 6px;"> 
					<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;School Fees
					</h3></div>	<div id="collapseOne1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel" style="margin-bottom: 10px; padding-top: 0;">
							<div class="col-md-12" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;"><div id="schoolfees">
							</div>
						</div>
					</div>
				</div>
			</div>
		
		<div class="panel panel-primary transportfeediv" style="margin-left: 12%; width: 70%;">
			<div class="panel-heading" style="height: 28px;">
					<h3 class="panel-title feedetailtable" style="color: #000000; vertical-align: text-top; position: absolute;top: 6px;"> 
					<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Transport Fees
					</h3></div>	<div id="collapseTwo2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel" style="margin-bottom: 10px; padding-top: 0;">
							<div class="col-md-12" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
							<div id="transportfees">
							</div>
						</div>
					</div>
				</div>
			</div>
									</div>
									<div id="attendancetable" style="padding: 5px"></div>
									<div id="appraisaltable" style="padding: 5px"></div>
									
								</div>
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>