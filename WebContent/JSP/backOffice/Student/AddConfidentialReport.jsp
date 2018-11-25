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
<link href="CSS/Admin/Admission/StudentNew.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Student/AddConfidentialReport.js"></script>
<style>

.edit{
position: absolute;
    right: 110px;
    top: 43px;
}
.delete{
position: absolute;
    right: 40px;
    top: 43px;
}

</style>

</head>

<body>

   <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>

	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Disciplinary Action</span></p>
		<div id="deleteDialog" style="display: none">
						<p>Are you sure you want to Remove?</p>
		</div>
		<div id="activeDialog" style="display: none">
						<p>Are You Sure to Active?</p>
		</div>
					<div id="admissionDialog" style="display: none">
							<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;">Entry Date</label>
								<div class="col-xs-7">
									<input class="form-control" onkeypress="HideError(this)" style="Width:150%;"  
									name="entryDate" id="entryDate" class="entryDates">
										
								</div>
							</div>
							<br />
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;">Comment<font color="red">*</font></label>
								<div class="col-xs-7">
									<textarea style="border: 1px solid #ccc;" onkeypress="HideError(this)"  name="comment" id="comment" rows="2" cols="30"></textarea>
										
									</div>
								</div>
								</div>
							</div>
					</div>
					 <div id="editDialog" style="display: none">
							<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;">Entry Date</label>
								<div class="col-xs-7">
									<input class="form-control" onkeypress="HideError(this)" style="Width:150%;" readonly="readonly"
										 name="entryDate" id="editentryDate" class="entryDates">
										
								</div>
							</div>
							<br />
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;">Comment<font color="red">*</font></label>
								<div class="col-xs-7">
									<textarea style="border: 1px solid #ccc;" onkeypress="HideError(this)" name="comment" id="editcomment" rows="2" cols="30"></textarea>
										
									</div>
								</div>
								</div>
							</div>
					</div> 
			<div class="errormessagediv1" style="display: none;" align="center">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>
	
		
			<div class="errormessagediv" style="display: none;" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>
			<div id="successmessages" class="successmessagediv" style="display: none;">
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>
													
			<!-- chiru -->

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Disciplinary Action</h3></a>
						

						<div class="navbar-right">  
							<span class="buttons" id="back1">Back</span>
							
						</div>
					</div>
	<input type="hidden" id="historyaccYear"  
	 value="<logic:present name="historyaccYear" scope="request"><bean:write name="historyaccYear"/></logic:present>"/>
	
	<input type="hidden" id="historylocId" 
	 value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>"/>
	 
	 <input type="hidden" id="historyclassname" 
	 value="<logic:present name="historyclassname" scope="request"><bean:write name="historyclassname"/></logic:present>"/>
	 
	 <input type="hidden" id="historysectionid"
	 value="<logic:present name="historysectionid" scope="request"><bean:write name="historysectionid"/></logic:present>"/>
	 
	 <input type="hidden" id="historysearchvalue"
	 value="<logic:present name="historysearchvalue" scope="request"><bean:write name="historysearchvalue"/></logic:present>"/>											 
								
	<input type="hidden" id="historystatus"
	 value="<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>"/>
	 
	 	
<input type="hidden" id="branchName"
	 value="<logic:present name="studentSearchList" scope="request"><bean:write name="studentSearchList" scope="request" property="branchName"/></logic:present>"/>
	 	 	
	 	
	 	
	 	
	 			 
					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
						
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="academicYear" tabindex="1"	 id="academicYear"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList" scope="request"><bean:write name="studentSearchList" scope="request" property="academicYear"/></logic:present>' />
									</div>
								</div>
								
								
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<input type="text" name="schoolName"  id="schoolNames"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList" scope="request"><bean:write name="studentSearchList" scope="request" property="branchName"/></logic:present>' />
									</div>
								</div>
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFullName" id="studentFullName"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<bean:write name="studentSearchList" property="studentFullName"/>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="admissionNo"  id="admissionNo"
											onchange="" maxlength="25" readonly="readonly"
											value='<bean:write name="studentSearchList" property="studentAdmissionNo"></bean:write>' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Roll No</label>

									<div class="col-xs-7">
										<input type="text" name="studentRollNo" id="studentRollNo"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<bean:write name="studentSearchList" property="studentrollno"/>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="classId" id="classId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<bean:write name="studentSearchList" property="classname"/>' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<input type="text" name="sectionId" tabindex="1" id="sectionId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<bean:write name="studentSearchList" property="sectionnaem"/>' />
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
												<!-- <input type="file" id="studentImageId1" name="studentImage" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;"> -->
												</div>
												
										</div>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student Status</label>
									<div class="col-xs-7">
										<input type="text" name="studentStatusId"  id="studentStatusId"
											maxlength="25" class="form-control" readonly="readonly"
											value='<bean:write name="studentSearchList" property="studentStatus"/>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House</label>
									<div class="col-xs-7">
										<input type="text" name="houseId" tabindex="1"	onkeypress="HideError()" id="houseId"
											maxlength="25" class="form-control" readonly="readonly"
											value='<bean:write name="studentSearchList" property="houseName"/>' />
									</div>
								</div>
								
								
								 <input type="hidden" id="hstudentid" name="studentId"
									value="<bean:write name="studentSearchList" property="studentId"/>"/>
									<input type="hidden" id="hiddenbackId" name="srarchSt" value="<logic:present name='srarchSt' scope='request' ><bean:write name='srarchSt' /></logic:present>" />
								<input type="hidden" id="hacademicYearId" name="academicYearId"
									value="<bean:write name="studentSearchList" property="academicYearId"/>"/>
									
								<input type="hidden" id="hschoolNameId" name="schoolNameId"
									value="<bean:write name="studentSearchList" property="locationId"/>"/>
								
								<input type="hidden" id="photohiddenid" name="previousImage"
									value="<bean:write name="studentSearchList" property="studentPhoto" />"> 
							</div>
							 
						</div>
				</div>
				
				<input type="hidden" id="hiddenedit" 
								value="<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
								<logic:equal name="daymap" property="key" value="DISACTUPD">
									<logic:equal value="true" name="daymap" property="value">
								<bean:write name="daymap" property="value" />
								</logic:equal>
								</logic:equal>
								</logic:iterate>
								</logic:present>" />
								
			 <input type="hidden" id="hiddendelete" 
								value="<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
								<logic:equal name="daymap" property="key" value="DISACTDEL">
									<logic:equal value="true" name="daymap" property="value">
								<bean:write name="daymap" property="value" />
								</logic:equal>
								</logic:equal>
								</logic:iterate>
								</logic:present>" />
				  
			</div>
					<hr style="height:1px;border:none;color:#333;background-color:#ddd;"/>
					
					<div>
						<div class="slideTab clearfix">
						<div class="tab">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#reportHistory" id="reportHistory">Report History</a></li>
								<li><a data-toggle="tab" href="#contacts" id="contacts">Contacts</a></li>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="DISACTADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<span class="buttons" id="addconfidential" style="position: absolute; right: 38px; margin-top: 6px;">New</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							</ul>
												
						<div id="contacts" class="tab-pane">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap">
									<div class="col-md-8" id=div2></div>
									<div id="studenttable"></div>
									<div id="individualstudenttable"></div>	
									<div id="deleteHistorytable"></div>
								</div>
								</div>
						</div>
						</div>
					</div>
				</div>
			</div>
</html>
