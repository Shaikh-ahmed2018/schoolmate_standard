<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/backOffice/Staff/AddStaff.js"></script>

<script type="text/javascript">
 $('.carousel').carousel({
		interval : 5000
	//changes the speed
	});
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<script>
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
</script>
<script type="text/javascript">
function  CheckIsNumeric(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if ((charCode < 65 || charCode > 90) && (charCode < 97 || charCode >122)) {
        
        return false;
    }
    else {
       
        return true;
    }
}
function  CheckIsNumeric1(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode!=43 &&  charCode != 45 &&(charCode < 48 || charCode > 57) && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode >122))  {
        
        return false;
    }
    else {
       
        return true;
    }
}

</script>

<style>
.buttons {
	vertical-align: 0px;
}
</style>
<style>
#allstudent tr {
	font-size: 14px;
	border-bottom: 1px solid #ddd;
	border-left: 1px solid #ddd;
}

#allstudent th {
	background: #f9f9f9 !important;
	border-bottom: 1px solid #ddd;
	border-top: 1px solid #ddd;
	border-right: 1px solid #ddd;
	font-size: 13px;
	color: #000000 !important;
	text-align: center;
	font-family: Roboto Medium;
}

#addstaffsubject tr {
	font-size: 14px;
	border-bottom: 1px solid #ddd;
	border-left: 1px solid #ddd;
}

#addstaffsubject tr:nth-child(n) {
	background-color: #F2FAFC;
}

#addstaffsubject tr:nth-child(2n) {
	background-color: #FFFFFF;
}

#addstaffsubject tr:hover {
	background: #9CDDE3;
	color: #fff;
	cursor: pointer;
}

#addstaffsubject th {
	background: #f9f9f9 !important;
	border-bottom: 1px solid #ddd;
	border-top: 1px solid #ddd;
	border-right: 1px solid #ddd;
	font-size: 13px;
	color: #000000 !important;
	text-align: center;
	font-family: Roboto Medium;
}

#addstaffsubject th a {
	color: #000000 !important;
}

#addstaffsubject tr :HOVER {
	color: #000 !important;
}

#addstaffsubject  tr td {
	border-right: 1px solid #ddd;
	border-left: 0;
	border-top: 0;
	border-bottom: 0;
	color: #000000;
}

.inputbox {
	display: block;
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 13px;
	line-height: 1.42857;
	color: #000000;
	background-color: #fff;
	background-image: none;
	border: 1px solid #cbd5dd;
	border-radius: 0;
	border: 0;
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

.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all {
	overflow: scroll;
	max-height: 300px;
}

.select-control {
	border: 0;
	display: block;
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 13px;
	line-height: 1.42857;
	color: #000000;
	background-color: #fff;
	background-image: none;
	border-radius: 0;
}

.own-panel {
	padding: 10px 0px;
}

form .panel.panel-primary {
	padding-bottom: 0px !important;
}
</style>
</head>

<body>
  <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 0px solid #ddd; margin-top: -6px;">
		<p class="transportheader">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Add Staff</span>
		</p>


		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span class="validateTips"><bean:write
								name="successmessagediv" scope="request" /></span></a>
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



		<form id="teacherform"	enctype="multipart/form-data" method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">

						<a data-toggle="collapse" data-parent="#accordion2"
							class="collapseOneA" href="#collapseOne" style="color: #000000">
							<h3 class="panel-title">
								<i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Staff
								Details
							</h3>
						</a>


						<div class="navbar-right">

							<span id="save" class="buttons" style="cursor: pointer">Save</span>

							<span id="back" class="buttons">Back</a></span>

						</div>

					</div>

					<div id="collapseOne" class="panel-collapse collapse in clearfix"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel clearfix">
							<div class="col-md-6 clearfix" id="txtstyle">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Staff
										Id <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="regno" onkeypress="return CheckIsNumeric1(event);"
											id="regno" onclick="HideError(this)" placeholder="" maxlength="19"
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="regsitrationNo"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">First
										Name <font color="red">*</font>
									</label>
									<div class="col-xs-7">

										<input type="text" name="tfastname" id="fname"
											class="form-control" placeholder="" maxlength="40"
											onclick="HideError(this)"
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="tfastname"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Department
										<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select name="department" id="department"
											onchange="HideError(this)" class="form-control">
											<option value="">----------Select----------</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Date of
										Joining <label style="color: red;"></label>
									</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" name="joiningdate"
											id="joindateid" class="form-control"
											onclick="HideError(this)" placeholder=""
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="joiningdate"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">User Type
										<!-- <font color="red">*</font> -->
									</label>
									<div class="col-xs-7">
										<select name="usertype" id="usertype" class="form-control"
											onchange="HideError(this)">
											<option value=" ">----------Select----------</option>
											<option value="superadmin">SuperAdmin</option>
											<option value="admin">Admin</option>
											<option value="staff">staff</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Reporting
										To <!-- <font color="red">*</font> -->
									</label>
									<div class="col-xs-7">
										<select name="reportingTo" id="reportingTo"
											class="form-control" onchange="HideError(this)">
											<option value=" ">----------Select----------</option>
											<logic:present name="reportingToList">
												<logic:iterate id="reportingTo" name="reportingToList">
													<option
														value='<bean:write name="reportingTo" property="teacherId"/>'>
														<bean:write name="reportingTo" property="teacherName" />
													</option>
												</logic:iterate>
											</logic:present>
										</select> <input type="hidden" name="StudentAdmissionNumber"
											id="teacherId" value="" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Pupil Studying ?</label>
									<div class="col-xs-7" id="radiostyle">
										<label> <input type="radio" class="radio-inline"
											name="is_student_studying" value="Y"
											style="margin-bottom: 4px;" />&nbsp;Yes&nbsp;&nbsp;&nbsp;
										</label> <label><input type="radio" class="radio-inline"
											name="is_student_studying" value="N" checked="checked"
											style="margin-bottom: 4px;" />&nbsp;No</label>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"
										id="student_admission_label">Admission Number <label
										style="color: red;">*</label></label>

									 <div class="col-xs-7">
										<input type="text" name="student_admission_id" onchange="readyOnly()" id="student_admission_id"
											class="form-control" />
										<!-- <input type="hidden" name="StudentAdmissionNumber"
											id="teacherId" value="" /> -->
									</div>
								</div>
							</div>
							<div class="col-md-6 clearfix" id="txtstyle">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Abbreviate Name <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="abbreviate" onkeypress="return CheckIsNumeric1(event);"
											id="abbreviate" onclick="HideError(this)" placeholder=""  maxlength="19"
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="regsitrationNo"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Last
										Name </label>
									<div class="col-xs-7">
										<input type="text" name="tlastname" id="lname" maxlength="40"
											onclick="HideError(this)" class="form-control" placeholder=""
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="tlastname"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Designation
										<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select name="designation" id="designation"
											onchange="HideError(this)" class="form-control">
											<option value=" ">----------Select----------</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Staff
										Type <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select name="teachingtype" id="teachingtype"
											class="form-control" onchange="HideError(this)">
											<option value=" ">----------Select----------</option>
											<option value="TEACHING">Teaching</option>
											<option value="NON-TEACHING">Non-Teaching</option>
											<option value="GENERAL">General</option>
											<option value="OFFICE STAFF">Office Staff</option>

										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Role <!-- <label
										style="color: red;">*</label> --></label>
									<div class="col-xs-7">
										<select name="roleId" id="roleId" class="form-control"
											onchange="HideError(this)">
											<option value=" ">----------Select----------</option>
											<logic:present name="RolePermission">
												<logic:iterate id="role" name="RolePermission"
													property="roleList">
													<option
														value='<bean:write name="role" property="roleCode"/>'>
														<bean:write name="role" property="roleName" />
													</option>
												</logic:iterate>
											</logic:present>
										</select> <input type="hidden" name="roleId" id="selectedRole" value="" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">User
										Name <!-- <font color="red">*</font> -->
									</label>
									<div class="col-xs-7">
										<input type="text" name="username" id="uname"
											onkeypress="HideError(this)" maxlength="25" placeholder=""
											class="form-control"
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="username"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Location<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select name="schoolName" id="schoolName" onchange="HideError(this)" class="form-control">
											
										</select>
									</div>
								</div>


								<div class="form-group clearfix" style="display: none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> </label>

								</div>



								<div class="form-group clearfix" style="display: none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student
										Name <font color="red">*</font>
									</label>
									<div class="col-xs-7" id="radiostyle">
										<input type="text" name="studentName" id="studentName"
											class="form-control" readonly="readonly" />
									</div>
								</div>
							</div>


						</div>
					</div>

					<div class="panel panel-primary clearfix" style="margin-top: 5px;">
						<div class="panel-heading clearfix" role="tab" id="headingTwo">

							<a class="collapsed" role="button" data-toggle="collapse" class="collapseTwoA"
								data-parent="#accordion" href="#collapseTwo"
								style="color: #000000" aria-expanded="false"
								aria-controls="collapseTwo">
								<h3 class="panel-title">
									<i class="glyphicon glyphicon-menu-hamburger"></i>
									&nbsp;&nbsp;Personal Details
								</h3>
							</a>

						</div>
						<div id="collapseTwo" class="panel-collapse collapse clearfix"
							role="tabpanel" aria-labelledby="headingTwo">
							<div class="panel-body own-panel clearfix">
								<div class="col-md-6 clearfix" id="txtstyle">
									<div class="form-group clearfix">

										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Gender
											<font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<select name="gender" id="gender" onchange="HideError(this)"
												class="form-control">
												<option value=" ">----------Select----------</option>
												<option value="MALE">Male</option>
												<option value="FEMALE">Female</option>

											</select>
										</div>
									</div>


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Mobile
											Number<font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<input type="text" name="teachermobno" id="mnumber"
												onclick="HideError(this)" maxlength="11" placeholder=""
												class="form-control"
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="teachermobno"/></logic:present>" />
										</div>
									</div>



									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Qualification</label>
										<div class="col-xs-7">
											<input type="text" name="tqualification" id="qualification"
												maxlength="12" onkeypress="HideError(this)"
												class="form-control" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="tqualification"/></logic:present>" />
										</div>
									</div>



									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Bank
											Name </label>
										<div class="col-xs-7">
											<input type="text" name="bankName" id="bankName"  maxlength="40"
												onkeypress="HideError(this)" class="form-control"
												placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="bankName"/></logic:present>" />
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Pan
											Number <font color="red">*</font>
										</label>


										<div class="col-xs-7">
											<input type="text" name="panNumber" id="panNumber"
												onclick="HideError(this)" maxlength="10"
												class="form-control" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="panNumber"/></logic:present>" />
										</div>
									</div>








									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Id
											Proof</label>
										<div class="col-xs-7">
											<input type="file" class="form-control" name="idproof" onclick="HideError(this)"
												id="idproof"><span
												style="font-size: 20px; color: red; cursor: pointer; position: absolute; right: 0; top: 1px;"
												id="deleteFileIdProof"> x</span>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Profile
										</label>
										<div class="col-xs-7">
											<input type="file" class="form-control" name="teacherprofile" onclick="HideError(this)"
												id="teaprofile"><span
												style="font-size: 20px; color: red; cursor: pointer; position: absolute; right: 0; top: 1px;"
												id="deleteFileProfile"> x</span>
										</div>
									</div>

									<span id="fileUploaddynmic"
										style="text-decoration: underline; color: #2f91c1; cursor: pointer; font-family: Roboto Light; font-weight: bold; margin-left: 45%;">Add
										More Attachments</span>
									<div class="form-group clearfix" id="fileattachment1Div"
										style="display: none;">
										<label for="inputPassword" class="control-label col-xs-5"
											id="fileattachment1label"
											style="text-align: right; line-height: 35px;">File
											Attachment</label>
										<div class="col-xs-7">
											<input type="file" class="form-control" id="filaattachment1" onclick="HideError(this)"
												name="filaattachment1"><span
												style="font-size: 20px; color: red; cursor: pointer; position: absolute; right: 0; top: 1px; display: none;"
												id="deleteFile1"> x</span> <input type="button"
												id="document4btn" name="profile" class="downloadDoc"
												value="Download" style="display: none;" /> <span
												id="downloadFileAttachment1Title" style="display: none;">
												downloadFileAttachment1 </span>
										</div>

									</div>

									<div class="form-group clearfix" id="fileattachment2Div"
										style="display: none;">
										<label for="inputPassword" class="control-label col-xs-5"
											id="fileattachment2label"
											style="text-align: right; line-height: 35px;">File
											Attachment</label>
										<div class="col-xs-7">
											<input type="file" class="form-control" id="filaattachment2" onclick="HideError(this)"
												name="filaattachment2"><span
												style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
												id="deleteFile2"> x</span> <input type="button"
												id="document5btn" name="filaattachment2" class="downloadDoc"
												value="Download" style="display: none;" /> <span
												id="downloadFileAttachment2Title" style="display: none;">
												downloadFileAttachment2</span>
										</div>

									</div>

									<div class="form-group clearfix" id="fileattachment3Div"
										style="display: none;">
										<label for="inputPassword" class="control-label col-xs-5"
											id="fileattachment3label"
											style="text-align: right; line-height: 35px;">File
											Attachment </label>
										<div class="col-xs-7">
											<input type="file" class="form-control" id="filaattachment3" onclick="HideError(this)"
												name="filaattachment3"> <span
												style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
												id="deleteFile3"> x</span> <input type="button"
												id="document6btn" name="filaattachment2" class="downloadDoc"
												value="Download" style="display: none;" /> <span
												id="downloadFileAttachment3Title" style="display: none;">
												downloadFileAttachment3 </span>
										</div>

									</div>

									<div class="form-group clearfix" id="fileattachment4Div"
										style="display: none;">
										<label for="inputPassword" class="control-label col-xs-5"
											id="fileattachment4label"
											style="text-align: right; line-height: 35px;">File
											Attachment</label>
										<div class="col-xs-7">
											<input type="file" class="form-control" id="filaattachment4" onclick="HideError(this)"
												name="filaattachment4"> <span
												style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
												id="deleteFile4"> x</span> <input type="button"
												id="document7btn" name="filaattachment4" class="downloadDoc"
												value="Download" style="display: none;" /> <span
												id="downloadFileAttachment4Title" style="display: none;">
												downloadFileAttachment4 </span>
										</div>

									</div>

									<div class="form-group clearfix" id="fileattachment5Div"
										style="display: none;">
										<label for="inputPassword" class="control-label col-xs-5"
											id="fileattachment5label"
											style="text-align: right; line-height: 35px;">File
											Attachment</label>
										<div class="col-xs-7">
											<input type="file" class="form-control" id="filaattachment5" onclick="HideError(this)"
												name="filaattachment5"><span
												style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
												id="deleteFile5"> x</span> <input type="button"
												id="document8btn" name="filaattachment5" class="downloadDoc"
												value="Download" style="display: none;" /> <span
												id="downloadFileAttachment5Title" style="display: none;">
												downloadFileAttachment5 </span>
										</div>

									</div>
								</div>
								<div class="col-md-6 clearfix"
									style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">

									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;"> Date
											of Birth <font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<input type="text" readonly="readonly"
												onclick="HideError(this)" name="dateofbirth"
												id="dateofbirthid" placeholder="" class="form-control"
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="dateofbirth"/></logic:present>" />
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Blood
											Group </label>
										<div class="col-xs-7">
											<select name="blood" id="bloodId" class="form-control"
												onchange="HideError(this)">
												<option value="">----------Select----------</option>
												<option value="A+ve">A+</option>
												<option value="A-ve">A-</option>
												<option value="B+ve">B+</option>
												<option value="B-ve">B-</option>
												<option value="O+ve">O+</option>
												<option value="O-ve">O-</option>
												<option value="AB+ve">AB+</option>
												<option value="AB-ve">AB-</option>
											</select>
										</div>
									</div>



									<div class="fStudying orm-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Email
											ID <!-- <font color="red">*</font> -->
										</label>
										<div class="col-xs-7">
											<input type="text" name="teacheremail" id="teacherEmail"
												maxlength="45" onclick="HideError(this)"
												class="form-control" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="teacheremail"/></logic:present>" />
										</div>
									</div>


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Account
											Number </label>
										<div class="col-xs-7">
											<input type="text" name="accountNumber" id="accountNumber"
												onkeypress="return CheckIsNumeric1(event);" maxlength="20"
												onclick="HideError(this)" class="form-control"
												placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="accountNumber"/></logic:present>" />
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Aadhaar
											Number <font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<input type="text" name="aadhaarnumber" id="aadhaarnumber"
												onclick="HideError(this)" maxlength="12"
												class="form-control" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="panNumber"/></logic:present>" />
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Marital
											Status <font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<select name="maritalstatus" id="maritalstatus"
												onchange="HideError(this)" class="form-control">
												<option value="">----------Select----------</option>
												<option value="Married">Married</option>
												<option value="Single">Single</option>

											</select>
										</div>
									</div>


									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;"> Image</label>
										<div class="col-xs-7">

											<input type="file" class="image" id="teaImageId" onclick="HideError(this)"
												name="teacherImage">
										</div>
									</div>






									<div class="form-group clearfix">

										<div class="col-xs-7">
											<img id="imagePreview" src="#" alt="image" width="80px"
												height="70px" style="margin-left: 62%;" /> <span
												style="font-size: 20px; color: red; cursor: pointer; position: absolute; right: 0; top: 1px;"
												id="deleteteacher_image"> x</span>
										</div>
									</div>

								</div>
							</div>
						</div>

					</div>



					<div class="panel panel-primary clearfix">
						<div class="panel-heading clearfix" role="tab" id="headingThree">

							<a class="collapsed" role="button" data-toggle="collapse" class="collapseThreeA"
								data-parent="#accordion" href="#collapseThree"
								style="color: #000000" aria-expanded="false"
								aria-controls="collapseThree"><h3 class="panel-title">
									<i class="glyphicon glyphicon-menu-hamburger"></i>
									&nbsp;&nbsp;Family Details
								</h3></a>

						</div>
						<div id="collapseThree" class="panel-collapse collapse clearfix"
							role="tabpanel" aria-labelledby="headingThree">
							<div class="panel-body own-panel clearfix">
								<div class="col-md-6 clearfix"
									style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Father
											Name</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="fathername"
												onkeypress="return CheckIsNumeric(event);" name="fathername"
												onclick="HideError(this)" placeholder="" maxlength="24"
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="fathername"/></logic:present>">
										</div>
									</div>


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Mother
											Name</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="mothername" maxlength="24"
												onkeypress="return CheckIsNumeric(event);" name="mothername"
												onclick="HideError(this)" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="mothername"/></logic:present>">
										</div>
									</div>


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Spouse
											Name</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="spousename" readonly="readonly"
												onkeypress="return CheckIsNumeric(event);" name="spousename"
												onclick="HideError(this)" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="mothername"/></logic:present>">
										</div>
									</div>


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Present
											Address <font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<textarea rows="3px" cols="3px" name="presentadd" id="presentadd" maxlength="240" onkeypress="HideError(this)"
												class="form-control" placeholder=""><logic:present name="teacherdetails">
													<bean:write name="teacherdetails" property="presentadd" />
												</logic:present></textarea>
										</div>
									</div>

								</div>
								<div class="col-md-6 clearfix"
									style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;"> Father
											Mobile Number</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="fatherMobile"
												onkeypress="return CheckIsNumeric1(event);"
												name="fatherMobile" onclick="HideError(this)" placeholder=""
												maxlength="10"
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="fathername"/></logic:present>">
										</div>
									</div>


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;"> Mother
											Mobile Number</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="motherMobile"
												onkeypress="return CheckIsNumeric1(event);"
												name="motherMobile" onclick="HideError(this)" placeholder=""
												maxlength="10"
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="fathername"/></logic:present>">
										</div>
									</div>


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;"> Spouse
											Mobile Number</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="spouseMobile" readonly="readonly"
												onkeypress="return CheckIsNumeric1(event);"
												name="spouseMobile" onclick="HideError(this)" placeholder=""
												maxlength="10"
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="fathername"/></logic:present>">
										</div>
									</div>


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px; padding-left: 0px;">Permanent
											Address <font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<textarea rows="3px" cols="3px" name="permanentadd" maxlength="240"
												id="permanentadd" class="form-control"
												onkeypress="HideError(this)" placeholder=""><logic:present
													name="teacherdetails">
													<bean:write name="teacherdetails" property="permanentadd" />
												</logic:present></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

</body>

</html>
