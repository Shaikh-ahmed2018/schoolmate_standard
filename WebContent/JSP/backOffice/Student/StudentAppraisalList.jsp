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
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<!-- <script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
 --><script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
 --><link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Student/StudentAppraisalList.js"></script>
<style>
.form-group{
margin-bottom: 5px;}
.save:hover {
	cursor: pointer;
}  

fieldset { 
	width:512px;
    display: block;
   /*  margin-left: auto;
    margin-right: 2px; */
    margin-bottom: 5px;
    padding-bottom: 0.625em;
    padding-left: 7px;
    padding-right: 0px;
    border: 0.5px solid #ccc;
   
}
legend {
    display: inline-block;
    width: auto;
    padding: 0;
    margin-bottom: 0px;
    font-size: 16px;
    line-height: inherit;
    color: #333;
    border: 0;
   }
</style>
<style>
.buttons{

vertical-align:-5px;

}
.navbar-right {
    top: -3px;
}
.panel-primary>.panel-heading{
margin-bottom: 0px;
}
form .panel.panel-primary.panel-list{
padding-bottom: 0px;

}    

@media (min-width:320px) and (max-width:767px){
br{
display: none;
}
}


</style>
</head>

<body>
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Student Appraisal</span>
			</p>
		<div id="admissionDialog" style="display: none">
			<div id="admissionclose" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<display:table class="table" id="admissionstudent"
						name="requestScope.getTempAdmissionDetailsList" 
						defaultsort="3"	>
						
						<display:column  style="text-align:center ">
							<input type='radio' name='select' class='select' style='text-align:center' id='${admissionstudent.temp_regid }'  /></display:column>
						<display:column property="studentname" sortable="true"	title="Student Name" />
						<display:column property="fatherName" sortable="true" title="Parents Name" />
						<display:column property="fatherMobileNo" sortable="true" title="Mobile Number " />
						<display:column property="dateofBirth" sortable="true" title="Date Of Birth" />
						<display:column property="schemeofstudy" sortable="true" title="Scheme Of Study" />
						<%-- <display:column property="classname" sortable="true" title="Class" /> --%>
						<display:column property="created_date" sortable="true" title="Created Date" />
						
						
					</display:table>

					
				</div>
				<br />
			</div>
		</div>
	
			<logic:present name="successMessage" scope="request">

				<div class="successmessagediv1" style="width:auto !important;display:none" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write	name="successMessage" scope="request" /></span></a>
					</div>
				</div>

			</logic:present>
	
			<logic:present name="errorMessage" scope="request">

				<div class="successmessagediv" align="center" style="display: none;" >
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write name="errorMessage" scope="request" /></span></a>
					</div>
				</div>

			</logic:present>
		
			<logic:present name="duplicateMessage" >

				<div class="successmessagediv" align="center" style="display: none;">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write name="duplicateMessage" scope="request" />
						</span></a>
					</div>

				</div>

			</logic:present>
	</center>
	<center>
			<div class="errormessagediv1" style="display: none;" align="center">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>
	</center>
	<center>
		
			<div class="errormessagediv" style="display: none;" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>
	</center>	
		<form action="studentRegistration.html?method=saveStudentRegistration"
			enctype="multipart/form-data" name="StudentRegistrationForm" id="formstudent" method="post">

			
													
			<!-- chiru -->

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Student Appraisal</h3></a>
						

						 <div class="navbar-right">
							<span class="buttons" id="addappraisal">New</span>
							
						</div> 
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="3"
											name="schoolLocation" id="schoolLocationId" >
											<option value=""></option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									
										<select id="class" name="classname" class="form-control"
											required>
											<option value="%%">All</option>

											<logic:present name="classList">

												<logic:iterate id="ClassName" name="classList">

													<option
														value="<bean:write name="ClassName" property="classId"/>">
														<bean:write name="ClassName" property="classname" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search By</label>
									<div class="col-xs-7">
										<input type="text" name="searchBy" tabindex="1"
											onkeypress="HideError()" id="searchBy"
											maxlength="25" class="form-control" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentFirstName"/></logic:present>' />
									</div>
								</div>
								
							</div>
							
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select name="academicYear" onkeypress="HideError()" id="academicYear" class="form-control"></select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="8"
											name="" id="">
											<option value="selected">All</option>
											<option value="">A</option>
											<option value="">B</option>
											<option value="">C</option>
											<option value="">D</option>
											<option value="">F</option>
										</select>
									</div>
								</div>
								
								<p align="center"style="margin-left:17%">
								<button type="button" class="btn btn-info "
								id="search" >Search</button>
								<button type="button" class="btn btn-info "
								id="Reset" >Reset</button></p>
								
							</div>
					<table class='table' id='allstudent' width='100%'><tr><th>Sl.No.</th>
						<th style="text-align:center; width:15%;">Admission No</th>
						<th style="text-align:center">Student Name</th>
						<th style="text-align:center">Roll No.</th>
						<th style="text-align:center">Class</th>
						<th style="text-align:center">Division</th>
						</tr>
						<tr>
						<td >1</td>
						<td style="text-align:center">10225</td>
						<td>Md Isteyak</td>
						<td style="text-align:center">001</td>
						<td style="text-align:center">X</td>
						<td style="text-align:center">A</td>
						</tr>
						<tr>
						<td>2</td>
						<td style="text-align:center">10226</td>
						<td>Md Kamran</td>
						<td style="text-align:center">002</td>
						<td style="text-align:center">X</td>
						<td style="text-align:center">A</td>
						</tr>
						<tr>
						<td>3</td>
						<td style="text-align:center">10227</td>
						<td>Ashok</td>
						<td style="text-align:center">003</td>
						<td style="text-align:center">X</td>
						<td style="text-align:center">A</td>
						</tr>
						<tr>
						<td>4</td>
						<td style="text-align:center">10227</td>
						<td>Ashok</td>
						<td style="text-align:center">003</td>
						<td style="text-align:center">X</td>
						<td style="text-align:center">A</td>
						</tr>
						<tr>
						<td>5</td>
						<td style="text-align:center">10227</td>
						<td>Ashok</td>
						<td style="text-align:center">003</td>
						<td style="text-align:center">X</td>
						<td style="text-align:center">A</td>
						</tr>
						<tr>
						<td>6</td>
						<td style="text-align:center">10227</td>
						<td>Ashok</td>
						<td style="text-align:center">003</td>
						<td style="text-align:center">X</td>
						<td style="text-align:center">A</td>
						</tr>
						</table>
							
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</html>
