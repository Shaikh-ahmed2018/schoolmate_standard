<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
<!-- <script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script> -->
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script> 
<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet"> -->
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Admission/AddNewApplication.js"></script>

<style>
<
style>.save:hover {
	cursor: pointer;
}
.errormessagediv,.errormessagediv1{
top:60px;
}
.panel-collapse {
	display: block !important;
}

.navbar-right {
    margin: -20px -2px;
}

.collapse in {
	display: block !important;
}
</style>
<style>
.buttons {
	vertical-align: -6px;
}

.panel-heading {
	margin-bottom: 10px;
}
.issibling{
display:none;

}
.form-group {
    margin-bottom: 5px;
}

fieldset { 
	width:600px;
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
.class_name{
	position: relative;
}
.hide_class{
	position: absolute;
	top: 0;
	width: 100%;
	height: 35px;
}
</style>
</head>

<body>
	
	<div class="col-md-12" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
	
	
		<div class="" id="div2">
          <div id="dialog"></div>
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Registration Form Format</span>
			</p>
		</div>
		
	
			<logic:present name="successMessage" scope="request">

				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
									name="successMessage" scope="request" />
						</span></a>
					</div>
				</div>

			</logic:present>

			<logic:present name="errorMessage" scope="request">

				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
									name="errorMessage" scope="request" />
						</span></a>
					</div>

				</div>

			</logic:present>

			<logic:present name="duplicateMessage" scope="request">

				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
									name="duplicateMessage" scope="request" />
						</span></a>
					</div>

				</div>

			</logic:present>

			<div class="errormessagediv1" style="display: none;">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips"></span></a>
				</div>
			</div>

			<!-- <div class="successmessagediv" style="display: none;">
				<div class="message-item">
					Warning <a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div> -->
			<div class="errormessagediv" style="display: none;" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>

		<form action="parentrequiresappointment.html?method=InsertTemporaryStudentRegistration" enctype="multipart/form-data" id="formstudent" method="post">
			
			<input type="hidden" name="henquiryid" id="henquiryid" value='<logic:present name="enquiryid" scope="session" ><bean:write name="enquiryid" scope="session" /></logic:present>' />
			<div class="panel-group" id="accordion" role="tablist"	aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"
								style="color: #000000;"><h3 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Admission Form 
							</h3></a>
						
						<input type="hidden" name="enquiryid" id="enquiryid" value="" />
						<input type="hidden" name="stream" id="streamid" value="" />
						<div class="navbar-right">

							<span class="buttons" id="save" class="save" title="Save" style="top: 26px;">Submit</span>
							<span class="buttons" data-toggle="modal" data-target="#logout" class="hoverColor" style="top: 26px;">Logout</span>
						</div>
					</div>
					
					<div class="modal fade" id="logout">
						<div class="modal-dialog">
							<!-- Modal Content -->
							<div class="modal-content"
								style="margin-left: 25%; width: 50%; margin-top: 20%;height: 150px;">
								<!-- Close Button -->
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true" style="margin-left:-30px;">&times;</span>
								</button>
								<br />
								<center> <p style="font-size: 15px;color: #4a4a4a;">Are You sure, You want to Logout?</p></center>
								<br />
								<button type="button" class="btn btn-info" onclick="gotoLogout()"
									data-dismiss="modal" style="margin-left: 40%;">OK</button>
								<br /> <br />
							</div>
						</div>
					</div>
					
					<input type="hidden" name='accyear' id='hiddenaccyear' 
					value="<logic:present name="locationName"><bean:write name="locationName" property="accyear"/></logic:present>"/>
					
					<input type="hidden" name='locId' id='hiddenlocId' 
					value="<logic:present name="locationName"><bean:write name="locationName" property="locationName"/></logic:present>"/>
					
					<input type="hidden" name='hiddenfatherId' id='hiddenfatherId' 
					value="<logic:present name="locationName"><bean:write name="locationName" property="fatherName"/></logic:present>"/>
					
					<input type="hidden" name='hiddenmotherId' id='hiddenmotherId' 
					value="<logic:present name="locationName"><bean:write name="locationName" property="motherName"/></logic:present>"/>
					
					<input type="hidden" name='relname' id='reliname'/>
					<input type="hidden" name='castename' id='castename'/>
					<input type="hidden" name='castecatname' id='castecatname'/>
					<div class="row" style="margin-top: 15px;">
						<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">First	Name <font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" name="studentfirstName" id="studentFirstNameId" maxlength="25" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Last Name</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" maxlength="20" name="studentLastName" id="studentLastNameId" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Date Of Birth <font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" name="dateofBirth" id="dateofBirthId" class="form-control" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Gender<font color="red">*</font></label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 0px">
										<select name="gender" id="genderId" class="form-control">
											<option value="">----------Select----------</option>
											<option value="Male">Male</option>
											<option value="Female">Female</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Nationality</label>
									<div class="col-xs-7">
									  <input type="text" name="nationality" id="nationalityId" value="" class="form-control" />
										<!-- <select name="nationality" id="nationalityId" class="form-control">
											<option value="">----------Select----------</option>
											 
											<option value="Indian" selected="selected">Indian</option>
											 
										</select> -->
									</div>
								</div>
								
								
                                <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Place Of Birth</label>
									<div class="col-xs-7">
										<input type="text" name="dateofBirth" id="dateofBirthId" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">State<font color="red">*</font></label>
									<div class="col-xs-7">
									<input type="text" name="state" id="stateId" value="" class="form-control" />
										<!-- <select name="state" id="stateId" class="form-control">
											<option value="">----------Select----------</option>
											<option value="Andhra Pradesh">Andhra Pradesh</option>
											<option value="Arunachal Pradesh">Arunachal Pradesh</option>
											<option value="Assam">Assam</option>
											<option value="Bihar">Bihar</option>
											<option value="Chandigarh">Chandigarh</option>
											<option value="Chhattisgarh">Chhattisgarh</option>
											<option value="Goa">Goa</option>
											<option value="Gujarat">Gujarat</option>
											<option value="Haryana">Haryana</option>
											<option value="Haryana">Haryana</option>
											<option value="Himachal Pradesh">Himachal Pradesh</option>
											<option value="Jammu and Kashmir">Jammu and Kashmir</option>
											<option value="Jharkhand">Jharkhand</option>	
											<option value="Karnataka">Karnataka</option>
											<option value="Kerala">Kerala</option>
											<option value="Maharashtra">Maharashtra</option>
											<option value="Madhya Pradesh">Madhya Pradesh</option>
											<option value="Manipur">Manipur</option>
											<option value="Meghalaya">Meghalaya</option>
											<option value="Mizoram">Mizoram</option>
											<option value="Odisha">Odisha</option>
											<option value="Puducherry ">Puducherry </option>
											<option value="Punjab">Punjab</option>
											<option value="Rajasthan">Rajasthan</option>
											<option value="Sikkim">Sikkim</option>
											<option value="TamilNadu">TamilNadu</option>
											<option value="Telangana">Telangana</option>
											<option value="Tripura">Tripura</option>
											<option value="Uttar Pradesh">Uttar Pradesh</option>
											<option value="Uttarakhand">Uttarakhand</option>
											<option value="West Bengal">West Bengal</option>

										</select> -->
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Religion<font color="red">*</font></label>
									<div class="col-xs-7">
										<select name="religion" id="religion" class="form-control">
											<option value=""></option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix" id="hiddingotheregion">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Other Religion Name</label>
									<div class="col-xs-7">
										<input type="text" name="otherreligion" id="otherreligionId" maxlength="20" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Caste<font color="red">*</font></label>
									<div class="col-xs-7">
										<select name="caste" id="casteId" class="form-control">
											<option value="">----------Select----------</option>
											<option value="Other">Others</option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix" id="hiddingothercaste"> 
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Other Caste Name</label>
									<div class="col-xs-7">
										<input type="text" name="othercaste" id="othercasteId" maxlength="20" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Caste Category<font color="red">*</font></label>
									<div class="col-xs-7">
										<select name="casteCategory" id="casteCategoryId" tabindex="16"	class="form-control">
											<option value="">----------Select----------</option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix" id="hiddingothercastecategory"> 
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Other Caste Category</label>
									<div class="col-xs-7">
										<input type="text" name="othercastecategory" id="othercastecategoryId" maxlength="20" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Mother Tongue<font color="red">*</font></label>
									<div class="col-xs-7">
									<input type="text" name="mothertongue" id="mothertongueId" value="" class="form-control" />
										<!-- <select name="mothertongue" id="mothertongueId" class="form-control" >
											<option value="">----------Select----------</option>
											<option value="Bengali">Bengali</option>
											<option value="English">English</option>
											<option value="Hindi">Hindi</option>
											<option value="Kannada">Kannada</option>
											<option value="Konkani">Konkani</option>
											<option value="Malayalam">Malayalam</option>
											<option value="Marathi">Marathi</option>
											<option value="Punjabi">Punjabi</option>
											<option value="Tamil">Tamil</option>
											<option value="Telugu">Telugu</option>
											<option value="Urdu">Urdu</option>
										</select> -->
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Aadhar No</label>
									<div class="col-xs-7">
										<input type="text" name="aadharno" id="aadharnoId" maxlength="20" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 70px; margin-top: 0px;">Permanent Address<font color="red">*</font>	</label>
									<div class="col-xs-7">
										<textarea name="address" id="paddrs" onkeypress="HideError()" class="form-control" style="height: 75px;"></textarea>
									</div>
								</div>
								 
								<div>
									<div style="margin-left: 136px; margin-bottom: 5px;" align="center">
										<input type="checkbox" id="checkAddressId">Same as a permanent address</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 70px; margin-top: 7px;">Address of Communication<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<textarea name="addressofcommunication" id="addressofcommunicationid" onkeypress="HideError()" class="form-control" style="height: 75px;"></textarea>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 18px;">Preferred Sms No.<font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" minlength="10" maxlength="10" class="form-control" name="preferedphno" id="preferedphnoId" />
											
									</div>
								</div>
							
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 20px;">Class Looking for Admission<font color="red">*</font></label>
									<div class="col-xs-7 class_name">
										<select class="form-control" name="classname" id="classId" readonly="true">
											<option value=""></option>
										</select>
										<div class="hide_class"></div>
									</div>
								</div>
								<input type="hidden" name='hiddenclassname' id='hiddenclassname' value="<logic:present name="classId"><bean:write name="classId" property="classname"/></logic:present>"/>
								
							<input type="hidden" name='hiddenloc' id='hiddenloc' value="<logic:present name="classId"><bean:write name="classId" property="locId"/></logic:present>"/>	
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 18px;">Sibling Studying In School? </label>
									<div class="col-xs-7">
										<select name="isSibling" id="isSibling" onkeypress="HideError()" class="form-control">
											<option value="Yes">Yes</option>
											<option value="No" >No</option>
										</select>
									</div>
								</div>
							
								<div class="form-group clearfix" id="hiddensiblingid">
									<label id="isSiblingIdLabel" for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Sibling ID<font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="isSiblingId"	id="isSiblingId" maxlength="25" />
									</div>
								</div>
									
								<div class="form-group clearfix" id="hiddensiblingname">
									<label id="isSiblingNameLabel" for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Sibling Name<font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="isSiblingName" id="isSiblingName" maxlength="25" />
									</div>
								</div>
								
								<div class="form-group clearfix" style="margin-top: 10px;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 18px;">Are The Parents Alumni In School? </label>
									<div class="col-xs-7">
										<select name="parentsAlumni" id="parentsAlumni" class="form-control" >
											<option value="">----------Select----------</option>
											<option value="Father" >Father</option>
											<option value="Mother" >Mother</option>
											<option value="Father/Mother" >Father/Mother</option>
										</select>
									</div>
								</div>
							
								<div class="form-group clearfix" id="hiddenfatheralumniname">
									<label id="fatherAlumniLabel" for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Father Name<font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="fatherAlumniname"	id="fatherAlumniname" maxlength="25" />
									</div>
								</div>
									
								<div class="form-group clearfix" id="hiddenfatheralumniyear">
									<label id="fatherAlumniyearLabel" for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Year</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="fatherAlumniyear" id="fatherAlumniyear" maxlength="25" />
									</div>
								</div>
								
								<div class="form-group clearfix" id="hiddenmotheralumniname">
									<label id="motherAlumniLabel" for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Mother Name<font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="motherAlumniname"	id="motherAlumniname" maxlength="25"  />
									</div>
								</div>
									
								<div class="form-group clearfix" id="hiddenmotheralumniyear">
									<label id="motherAlumniIdLabel" for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Year</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="motherAlumniyear" id="motherAlumniyear" maxlength="25"  />
									</div>
								</div>
								<div class="form-group clearfix" id="birthCertificate">
									<label id="birthCertificateLabe" for="inputforbirthCertificate" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Birth Certificate</label>
									<div class="col-xs-7">
										<input type="file" class="form-control" name="birthCertificateFile" id="birthCertificateFile"   />
									</div>
								</div>
							</div>
						<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							<div class="form-group clearfix" style="height: 87px;">
								<label for="forImage" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Click on Image to select photo</label>
									<div class="col-xs-7">
										<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 173px; height: 172px;">
											<img id="imagePreview" class="setImage" alt="image" src="#" style="height: 45mm; width: 45mm;">
											<div style="position: absolute;top: 0;height: 160px;">
												<input type="file" id="studentImageId1" name="inputfile" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;">
											</div>
											<span id="removeSpanId" class="close" style="position: absolute; top: -2px; right: 170px;color: red;" >X</span>
										</div>
									</div>
							</div>
						<fieldset>
							<legend style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif;font-size: 16px;color: #000000;">Father's Details:</legend>
							<div class="form-group clearfix" style="margin-top: 7px;">
								<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Father
										Name <font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="fatherName" id="fatherNameId" maxlength="30" class="form-control"/>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Mobile
										Number <font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="fatherMobileNo" id="fatherMobileNoId" 
											maxlength="10" class="form-control" /> <!-- onchange="fatherValidatePhoneNo()" -->
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Qualification</label>
								<div class="col-xs-7">
									<input type="text" name="fatherQualification" id="fatherQualification" maxlength="30" class="form-control" />
								</div>
							</div>
								
                            <div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Occupation</label>
								<div class="col-xs-7">
									<select class="form-control" name="fatherOccupation" id="fatherOccupation">
										<option value="NA">NA</option>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Designation</label>
								<div class="col-xs-7">
									<input type="text" name="fatherDesignation" id="fatherDesignationId" maxlength="30" class="form-control" />
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Department</label>
								<div class="col-xs-7">
									<input type="text" name="fatherDepartment" id="fatherDepartmentId" maxlength="30" class="form-control" />
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Monthly Income <font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="fatherMonthlyIncome" id="fatherMonthlyIncome" maxlength="10" class="form-control" />
								</div>
							</div>
								
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Email Id</label>
								<div class="col-xs-7">
									<input type="text" maxlength="45" name="fatherEmail" id="fatherEmail" onchange="fatherValidateEmail()" class="form-control" />
								</div>
							</div>
							
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 70px; margin-top: 7px;">Official Address
									<font color="red">*</font></label>
									<div class="col-xs-7">
										<textarea name="fathersOfficialAddress" id="fathersOfficialAddress" onkeypress="HideError()" class="form-control" style="height: 75px;"></textarea>
									</div>
							</div>
						</fieldset>
						
						<fieldset>
							<legend style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif;font-size: 16px;color: #000000;">Mother's Details:</legend>
							<div class="form-group clearfix" style="margin-top: 7px;">
								<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Mother
										Name <font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="mothername" id="motherNameId" maxlength="30" class="form-control" />
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Mobile
										Number <font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="motherMobileNo" id="motherMobileNoId" 
											maxlength="10" class="form-control" /> <!-- onchange="motherValidatePhoneNo()" -->
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Qualification</label>
								<div class="col-xs-7">
									<input type="text" name="motherQualification" id="motherQualification" maxlength="30" class="form-control" />
								</div>
							</div>
							
						    <div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Occupation</label>
								<div class="col-xs-7">
									<select class="form-control" name="motherOccupation" id="motherOccupation">
										<option value="NA">NA</option>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Designation</label>
								<div class="col-xs-7">
									<input type="text" name="motherDesignation" id="motherDesignationId" maxlength="30" class="form-control" />
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Department</label>
								<div class="col-xs-7">
									<input type="text" name="motherDepartment" id="motherDepartmentId" maxlength="30" class="form-control" />
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Monthly Income <font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="motherMonthlyIncome" id="motherMonthlyIncome" maxlength="10" class="form-control" />
								</div>
							</div>
								
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Email Id</label>
								<div class="col-xs-7">
									<input type="text" maxlength="45" name="motherEmail" id="motherEmail" onchange="motherValidateEmail()" class="form-control" />
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 70px;  margin-top: 7px;">Official Address
								<font color="red">*</font></label>
								<div class="col-xs-7">
									<textarea name="mothersOfficialAddress" id="mothersOfficialAddress" onkeypress="HideError()" class="form-control" style="height: 75px;"></textarea>
								</div>
							</div>
						</fieldset>	
				</div>
			</div>
		</div>
		</div>
		</form>
	</div>

</body>
</html>
