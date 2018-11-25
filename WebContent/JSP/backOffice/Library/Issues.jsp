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

<script type="text/javascript" src="JS/backOffice/Library/Issues.js"></script>


<style>
.form-group {
	margin-bottom: 5px;
}

.save:hover {
	cursor: pointer;
}

fieldset {
	width: 512px;
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

.text2 {
	margin-left: 211px !important;
	width: 819px !important;
}
</style>

<style>

.panel-primary>.panel-heading {
	margin-bottom: 0px;
}

form .panel.panel-primary.panel-list {
	padding-bottom: 0px;
}

.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all {
	z-index: 1;
	display: block;
	left: 478px;
	width: 346px;
	height: 300px;
	overflow: scroll;
	position: absolute;
	top: 200px !important;
}

.back {
	display: none;
}

.navbar-right span {
	margin-right: -2px;
	position: relative;
	top: 4px;
	vertical-align: text-top;
}

#othersname {
	vertical-align: -4px;
	margin-left: 15px;
}
#hiddenclassId{
    width: 94px;
    margin-left: 11px;
}
#hiddendivissionId{
    width: 95px;
    margin-left: -4px;
}
#hiddendepartment{
  width: 94px;
    margin-left: 11px;
}
#hiddendesignation{
    width: 95px;
    margin-left: -4px;
}
</style>
</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;<span id="pageHeading">Issue</span>
		</p>

		<div id="admissionDialog" style="display: none">
			<div id="admissionclose" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

				</div>
				<br />
			</div>
		</div>

		<div class="successmessagediv"
			style="width: auto !important; display: none" align="center">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">

					<a data-toggle="collapse" data-parent="#accordion" href="#"
						style="color: #000000; vertical-align: text-top;"><h3
							class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Issue
						</h3></a>


					<div class="navbar-right" style="margin-right: 2px; height: 32px;">
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LIBTADD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<span class="buttons" id="issue">Issued</span>
										<span class="buttons back" id="back1">Back</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					</div>
				</div>

     <input type="hidden" id="issueId"
	 value="<logic:present name='issueId' scope='request'><bean:write name='issueId'/></logic:present>" />
	 
	 <input type="hidden" id="subId"
	 value="<logic:present name='subId' scope='request'><bean:write name='subId'/></logic:present>" />
	 
	 <input type="hidden" id="subscriberType"
	 value="<logic:present name='subscriberType' scope='request'><bean:write name='subscriberType'/></logic:present>" />
	 
    <input type="hidden" id="historylocId"
	 value="<logic:present name='historylocId' scope='request'><bean:write name='historylocId'/></logic:present>" />
			
	 <input type="hidden" id="historysubscriberType"
	 value="<logic:present name='historysubscriberType' scope='request'><bean:write name='historysubscriberType'/></logic:present>" />	
	
	<input type="hidden" id="historyacademicId"
	 value="<logic:present name='historyacademicId' scope='request'><bean:write name='historyacademicId'/></logic:present>" />
	
	<input type="hidden" id="historygoto"
    value="<logic:present name='historygoto' scope='request'><bean:write name='historygoto'/></logic:present>" />
	
	<input type="hidden" id="historystartedby"
    value="<logic:present name='historystartedby' scope='request'><bean:write name='historystartedby'/></logic:present>" />
	
	<input type="hidden" id="historysearch"
    value="<logic:present name='historysearch' scope='request'><bean:write name='historysearch'/></logic:present>" />	 	 	 			

    <input type="hidden" id="historyorderby"
	 value="<logic:present name='historyorderby' scope='request'><bean:write name='historyorderby'/></logic:present>" />	
	
	<input type="hidden" id="historyclassname"
	 value="<logic:present name='historyclassname' scope='request'><bean:write name='historyclassname'/></logic:present>" />
	
	<input type="hidden" id="historysectionid"
    value="<logic:present name='historysectionid' scope='request'><bean:write name='historysectionid'/></logic:present>" />
	
	<input type="hidden" id="historydepartment"
    value="<logic:present name='historydepartment' scope='request'><bean:write name='historydepartment'/></logic:present>" />
	
	<input type="hidden" id="historydesignation"
    value="<logic:present name='historydesignation' scope='request'><bean:write name='historydesignation'/></logic:present>" />


				<div id="collapseOne" class="panel-collapse collapse in "
					role="tabpanel" aria-labelledby="headingOne">
					<input type="hidden" id="subscriberId"
						value="<logic:present name='subid' scope='request'><bean:write name='subid' property="subscriberId"/></logic:present>">
					<input type="hidden" id="hiddenusetype"
						value="<logic:present name='subscriberType' scope='request'><bean:write name='subscriberType'/></logic:present>" />

					<%-- <input type="hidden" id="academic_Year"  value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="academicYear"/></logic:present>" /> --%>

					<div class="panel-body own-panel">
						<div class="row">
							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Accession
										No <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control ui-autocomplete-input"
											autocomplete="off" id="accession_no" name="Accession No "
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="accessionNo"/></logic:present>" />
										<input type="hidden" name="accessionName" id="hidden_accessionNo"
											value="">
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">ItemType</label>
									<div class="col-xs-7">
										<input type="hidden" readonly="readonly"
											onkeypress="HideError()" class="form-control" id="itemtype"
											maxlength="20"
											value="<logic:present name="studentList"><bean:write name="studentList" property="itemType"></bean:write></logic:present>" />
										<input type="text" readonly="readonly"
											onkeypress="HideError()" class="form-control" id="item"
											maxlength="20"
											value="<logic:present name="studentList"><bean:write name="studentList" property="itemType"></bean:write></logic:present>" />

										<%-- <input type="text" class="form-control" id="itemtype" maxlength="20"
										value='<logic:present name="studentList"><bean:write name="studentList" property="itemType"></bean:write></logic:present>' /> --%>
									</div>
								</div>

								<input type="hidden" id="hiddenusetype"
									value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberType"/></logic:present>" />

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Book
										Title</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" name="Book Title"
											id="bookTitle" class="form-control"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="bookTitle"/></logic:present>" />

									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Author</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											id="author" name="Author" tabindex="4" maxlength="25"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="author"/></logic:present>" />

										
									</div>
								</div>


								<div class="form-group clearfix" style="display: none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Category
									</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											name="Category" id="category" tabindex="5" maxlength="25"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="category"/></logic:present>" />

									</div>
								</div>


								<div class="form-group clearfix Book">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">DDC</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											id="ddc" name="DDC"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="ddc"/></logic:present>" />

									</div>
								</div>


								<div class="form-group clearfix">

									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Current
										Status </label>
									<div class="col-xs-7">
										<input class="form-control" readonly="readonly"
											name="currentstatus" tabindex="5" id="currentstatus"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="status"/></logic:present>" />

									</div>
								</div>

								<div class="form-group clearfix">

									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right;"> Issued to<font color="red">*</font></label>
									<div class="col-xs-7" id="radiostyle">
										<div>
											<span id="teacher" style="vertical-align: -4px;"><input
												type="radio" class="radio-inline" style="top: -2px;"
												class="requested_by" name="requested_by" class="cencession"
												id="teachername" value="Teacher" />&nbsp;Staff&nbsp;&nbsp;&nbsp;
												<input type="hidden" name="TeacherName"
												value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>"></span>
											<span id="studentname" style="vertical-align: -4px;"><input
												type="radio" class="radio-inline" style="top: -2px;"
												name="requested_by" class="requested_by" id="student"
												class="cencession" value="Student" />&nbsp;Student <input
												type="hidden" name="StudentName"
												value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>" />
											</span> 
											<span id="othersname"><input type="radio"
												class="radio-inline" style="top:-2px;" name="requested_by"
												class="requested_by cencession" id="other"
												value="other" />&nbsp;Other<input type="hidden"
												name="OthertName"
												value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>" />
											</span>

										</div>

									</div>
								</div>

								<div class="form-group clearfix" id="usertype"
									style="display: none;">
									<label for="inputPassword" class="control-label col-xs-5"
										id="radiotype" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<input type="text" class="form-control ui-autocomplete-input"
											autocomplete="off" name="usertype" id="userType"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>" />
										<input type="hidden" id="hiddenstuid" />
									</div>
								</div>
						
						<div class="form-group clearfix admissionno" style="display:none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission No</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											id="admissionno" name="admissionno" 
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="adminssionNo"/></logic:present>"/>
									</div>
								</div>
						
								
					<div class="col-md-6" id="class"
						style="font-size: 13px;display: none;left:22%;">
						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Standard</label>
							<div class="col-xs-7">
								<input type="text" name="hiddenclassId" id="hiddenclassId" readonly="readonly"  class="form-control"
								value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="className"/></logic:present>" />
															</div>
						</div>
					</div>
					<div class="col-md-6" id="divission"
						style="font-size: 13px;display: none;left:54px;">
						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Divission</label>
							<div class="col-xs-7">
								<input type="text" name="hiddendivissionId" id="hiddendivissionId" readonly="readonly"  class="form-control"
								value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="sectionName"/></logic:present>" />
							</div>
						</div>
					</div>
						
						
						
						<div class="col-md-6" id="department"
						style="font-size: 13px;display: none;left:22%;">
						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Department</label>
							<div class="col-xs-7">
								<input type="text" name="hiddendepartment" id="hiddendepartment" readonly="readonly"  class="form-control"
								value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="department"/></logic:present>" />
							</div>
						</div>
					</div>
					<div class="col-md-6" id="designation"
						style="font-size: 13px;display: none;left:54px;">
						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="left:-10px;">Designation</label>
							<div class="col-xs-7">
								<input type="text" name="hiddendesignation" id="hiddendesignation" readonly="readonly" class="form-control"
								value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="designation"/></logic:present>" />
							</div>
						</div>
					</div>		
					
				    	<div class="form-group clearfix admissionno" id="contactno" style="display:none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Contact No</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											id="hiddencontactno" name="hiddencontactno" 
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="contactNumber"/></logic:present>"/>
									</div>
								</div>
								
								<div class="form-group clearfix " id="addr" style="display:none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Address</label>
									<div class="col-xs-7">
										<textarea  readonly="readonly" class="form-control" id="hiddenotheraddr" name="hiddenotheraddr"/>
											 </textarea>
											 <input type="hidden" id="otheradderss" value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="otherUserAddr"/></logic:present>"/>
									</div>
								</div>

							</div>

							<div class="col-md-6" 
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div
											style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 173px; height: 172px;">
											<img id="imagePreview" class="setImage" alt="image"
												src="images/profile_photo.png"
												style="height: 45mm; width: 45mm;"> <input
												type="hidden" id="hidenimage"
												value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='imageUrl'/></logic:present>" />
											<span id="removeSpanId" class="close"
												style="position: absolute; top: 0px; right: 118px; color: red;"></span>
										</div>
									</div>

								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Shelf No</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											name="" tabindex="5" id="shelfNo" maxlength="25"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="shelfNno"/></logic:present>" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Library Branch</label>
									<div class="col-xs-7">
										<input type="text" id="locationname" class="form-control"
											name="locationnid" readonly="readonly"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="location"/></logic:present>" />
									</div>
								</div>
								
								<div class="form-group clearfix" style="display: none">
									<div class="col-xs-7">
										<input type="text" id="hiddenlocation" class="form-control"
											name="hiddenlocation" readonly="readonly" value="" />
									</div>
								</div>
								
					<input type="hidden" id="hidenaccyear"
						value="<logic:present name='accademic_year' scope='request'><bean:write name='accademic_year'/></logic:present>" />
                   <div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; display:none">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-7">
									<select id="Acyearid" name="accyear" class="form-control"
										required>
										<!-- <option value="all"></option> -->
										<logic:present name="AccYearList">
											<logic:iterate id="AccYear" name="AccYearList">
												<option
													value="<bean:write name="AccYear" property="accyearId"/>"><bean:write
														name="AccYear" property="accyearname" /></option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>

						</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										From Date<font color="red">*</font> </label>
									<div class="col-xs-7">
										<input type="text" class="form-control" 
											name="fromdate" id="Fromdate"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="issuedDate"/></logic:present>" />
									</div>
									<!-- onkeypress="HideError()" -->
								</div>
                                <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">To Date<font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" class="form-control" 
											name="todate1" id="todate1"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="issuedDate"/></logic:present>" />
									</div>
									<!-- onkeypress="HideError()" -->
								</div>



							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
</html>
