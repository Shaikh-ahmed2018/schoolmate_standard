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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Admission/secondApplication.js"></script>
<style>
<
style>.save:hover {
	cursor: pointer;
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
.errormessagediv,.errormessagediv1{
top:60px;
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
	/* height: 35px; */
}
</style>
</head>

<body>
	
	<div class="col-md-12" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
	<div id="dialog"></div>
	
		<div class="" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Registration Form Format For Class[I-X]</span>
			</p>
		</div>
		<%-- <div class="input-group col-md-4" >
			<label for="inputPassword" class="control-label col-xs-8" style="text-align: left; margin-left: 120px; line-height: 60px;color: #2A2B2B;font-size: 20px;font-family: Helvetica Neue, Helvetica, Arial, sans-serif;"> 
				TempAdmission No:<span id="admid">${requestScope.SecAdmNo}</span>
   			</label>
		</div> --%>
	
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
									name="successmessagediv" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>

			<logic:present name="errormessagediv" scope="request">

				<div class="errormessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span
							style="color: red;"> <bean:write name="errorMessage"
									scope="request" />
						</span></a>
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

		<form action="parentrequiresappointment.html?method=saveSecondAdmissionApplication" enctype="multipart/form-data" id="formstudent" method="post">
			<div class="panel-group" id="accordion" role="tablist"	aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"
								style="color: #000000; vertical-align: middle;"><h3 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Admission Form 
							</h3></a>
						
						<input type="hidden" name="enquiryid" id="enquiryid" value="" />
						<input type="hidden" name="stream" id="streamid" value="" />
						<div class="navbar-right">

							<span class="buttons" id="save" class="save" title="Save" style="top: 26px;">Submit</span>
						</div>
					</div>
					
					<div class="row">
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
										<select name="gender" id=genderId class="form-control">
											<option value="">----------Select----------</option>
											<option value="Male">Male</option>
											<option value="Female">Female</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Nationality</label>
									<div class="col-xs-7">
										<select name="nationality" id="nationalityId" class="form-control">
											<option value="">----------Select----------</option>
											<!-- <option value="Afghan">Afghan</option>
											<option value="American">American</option>
											<option value="Argentine">Argentine</option>
											<option value="Argentinian">Argentinian</option>
											<option value="Australian">Australian</option>
											<option value="Belgian">Belgian</option>
											<option value="Bolivian">Bolivian</option>
											<option value="Brazilian">Brazilian</option>
											<option value="British">British</option>
											<option value="Cambodian">Cambodian</option>
											<option value="Cameroonian">Cameroonian</option>
											<option value="Canadian">Canadian</option>
											<option value="Chilean">Chilean</option>
											<option value="Chinese">Chinese</option>
											<option value="Colombian">Colombian</option>
											<option value="Costa Rican">Costa Rican</option>
											<option value="Cuban">Cuban</option>
											<option value="Danish">Danish</option>
											<option value="Dominican">Dominican</option>
											<option value="Ecuadorian">Ecuadorian</option>
											<option value="Egyptian">Egyptian</option>
											<option value="Estonian">Estonian</option>
											<option value="Ethiopian">Ethiopian</option>
											<option value="Finnish">Finnish</option>
											<option value="French">French</option>
											<option value="German">German</option>
											<option value="Ghanaian">Ghanaian</option>
											<option value="Greek">Greek</option>
											<option value="Guatemalan">Guatemalan</option>
											<option value="Haitian">Haitian</option>
											<option value="Honduran">Honduran</option>
											<option value="Indonesian">Indonesian</option>
											<option value="Iranian">Iranian</option>
											<option value="Irish">Irish</option>
											<option value="Israeli">Israeli</option>
											<option value="Italian">Italian</option> -->
											<option value="Indian" selected="selected">Indian</option>
											<!-- <option value="Japanese">Japanese</option>
											<option value="Jordanian">Jordanian</option>
											<option value="Kenyan">Kenyan</option>
											<option value="Laotian">Laotian</option>
											<option value="Latvian">Latvian</option>
											<option value="Lithuanian">Lithuanian</option>
											<option value="Malaysian">Malaysian</option>
											<option value="Mexican">Mexican</option>
											<option value="Moroccan">Moroccan</option>
											<option value="Dutch">Dutch</option>
											<option value="New Zealander">New Zealander</option>
											<option value="Nicaraguan">Nicaraguan</option>
											<option value="Norwegian">Norwegian</option>
											<option value="Panamanian">Panamanian</option>
											<option value="Paraguayan">Paraguayan</option>
											<option value="Peruvian">Peruvian</option>
											<option value="Filipino">Filipino</option>
											<option value="Polish">Polish</option>
											<option value="Portuguese">Portuguese</option>
											<option value="Puerto Rican">Puerto Rican</option>
											<option value="Romanian">Romanian</option>
											<option value="Russian">Russian</option>
											<option value="Saudi">Saudi</option>
											<option value="Scottish">Scottish</option>
											<option value="Spanish">Spanish</option>
											<option value="Swedish">Swedish</option>
											<option value="Swiss">Swiss</option>
											<option value="Taiwanese">Taiwanese</option>
											<option value="Tajik">Tajik</option>
											<option value="Thai">Thai</option>
											<option value="Turkish">Turkish</option>
											<option value="Ukrainian">Ukrainian</option>
											<option value="Uruguayan">Uruguayan</option>
											<option value="Venezuelan">Venezuelan</option>
											<option value="Vietnamese">Vietnamese</option>
											<option value="Welsh">Welsh</option> -->
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">State<font color="red">*</font></label>
									<div class="col-xs-7">
										<select name="state" id="stateId" class="form-control">
											<option value="">----------Select----------</option>
											<option value="TamilNadu">TamilNadu</option>
											<option value="Karnataka">Karnataka</option>
											<option value="Kerala">Kerala</option>  
											<option value="Andhra Pradesh">Andhra Pradesh</option>
											<option value="Arunachal Pradesh">Arunachal Pradesh</option>
											<option value="Assam">Assam</option>
											<option value="Bihar">Bihar</option>
											<option value="Chhattisgarh">Chhattisgarh</option>
											<option value="Goa">Goa</option>
											<option value="Gujarat">Gujarat</option> 
											<option value="Haryana">Haryana</option>
											<option value="Himachal Pradesh">Himachal Pradesh</option>
											<option value="Jammu and Kashmir">Jammu and Kashmir</option>
											<option value="Jharkhand">Jharkhand</option>
											<option value="Madhya Pradesh">Madhya Pradesh</option>
											<option value="Maharashtra">Maharashtra</option>
											<option value="Manipur">Manipur</option>
											<option value="Meghalaya">Meghalaya</option>
											<option value="Mizoram">Mizoram</option>
											<option value="Nagaland">Nagaland</option>
											<option value="Odisha">Odisha</option>
											<option value="Punjab">Punjab</option>
											<option value="Rajasthan">Rajasthan</option>
											<option value="Sikkim">Sikkim</option>
											<option value="Telangana">Telangana</option>
											<option value="Tripura">Tripura</option>
											<option value="Uttar Pradesh">Uttar Pradesh</option>
											<option value="Uttarakhand">Uttarakhand</option>
											<option value="West Bengal">West Bengal</option>
											
										</select>
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
										<select name="mothertongue" id="mothertongueId" class="form-control" >
											<option value="">----------Select----------</option>
											<option value="Assamese">Assamese</option> 
											<option value="Bengali (Bangla)">Bengali (Bangla)</option> 
											<option value="Dogri">Dogri</option>
											<option value="English">English</option>
											<option value="Gujarati">Gujarati</option>
											<option value="Hindi">Hindi</option>
											<option value="Kashmiri">Kashmiri</option>
											<option value="Konkani">Konkani</option>
											<option value="Malayalam">Malayalam</option>
											<option value="Marathi">Marathi</option>
											<option value="Nepali">Nepali</option>
											<option value="Odia">Odia</option>
											<option value="Punjabi">Punjabi</option>
											<option value="Sanskrit">Sanskrit</option>
											<option value="Santali">Santali</option>
											<option value="Sindhi">Sindhi</option>
											<option value="Tamil">Tamil</option>
											<option value="Telugu">Telugu</option>
											<option value="Tulu">Tulu</option>
											<option value="Urdu">Urdu</option>
										</select>
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
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Distance From School</label>
								<div class="col-xs-7">
									<input type="text" maxlength="45" name="distance" id="distanceId" class="form-control" />
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 18px;">Preferred Phone No. for School SMS<font color="red">*</font></label>
								<div class="col-xs-7">
									<select class="form-control" name="preferedphno" id="preferedphnoId" >
										<option value="">----------Select----------</option>
										<option value="father">Father</option>
										<option value="mother">Mother</option>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">School Last Attended</label>
								<div class="col-xs-7">
									<input type="text" maxlength="45" name="schoolLocation" id="schoolLocationId" class="form-control" />
								</div>
							</div> 
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Scheme Of Study<font color="red">*</font></label>
								<div class="col-xs-7">
									<select class="form-control" name="schemeofstudy" id="schemeofstudyId" >
										<option value="">----------Select-----------</option>
										<option value="ICSE">ICSE</option>
										<option value="CBSE">CBSE</option>
										<option value="SSLC">SSLC</option>
										<option value="Other">Any Other State Board</option>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix" id="hiddenanyotherboard">
								<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Any Other State Board<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="anyotherboard" id="anyotherboardId" maxlength="20" class="form-control" />
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 18px;">Sencond Language Studied in the present class</label>
								<div class="col-xs-7">
									<select name="secondlanguage" id="secondlanguageId" class="form-control" >
										<option value="">----------Select----------</option>
										<option value="English">English</option>
										<option value="Tamil">Tamil</option>
										<option value="Telugu">Telugu</option>
										<option value="Malayalam">Malayalam</option>
										<option value="Hindi">Hindi</option>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 18px;"> Third Language Studied in the present class</label>
								<div class="col-xs-7">
									<select name="thirdlanguage" id="thirdlanguageId" class="form-control" >
										<option value="">----------Select----------</option>
										<option value="English">English</option>
										<option value="Tamil">Tamil</option>
										<option value="Telugu">Telugu</option>
										<option value="Malayalam">Malayalam</option>
										<option value="Hindi">Hindi</option>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class Looking for Admission<font color="red">*</font></label>
								<div class="col-xs-7 class_name">
									<select class="form-control" name="classname" id="classId" readonly="true">
										<option value="" ></option>
									</select>
									<div class="hide_class"></div>
								</div>
							</div>	
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 70px; margin-top: 7px;">Extra Curricular Activities
								</label>
								<div class="col-xs-7">
									<textarea name="activities" id="activities" class="form-control" style="height: 75px;"></textarea>
								</div>
							</div>	
							
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
						</div>
						<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							<div class="form-group clearfix" style="height: 87px;">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
											<img id="imagePreview" class="setImage" alt="image" src="#" style="height: 45mm; width: 45mm;">
											<div style="position: absolute;top: 0;height: 160px;">
												<input type="file" id="studentImageId1" name="inputfile" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;">
											</div>
											<span id="removeSpanId" class="close" style="position: absolute; top: -2px;right: 170px;color: red;" >X</span>
										</div>
									</div>
							</div>
						<fieldset>
							<legend style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif;font-size: 16px;color: #000000;">Father's Details:</legend>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Father
										Name <font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="fatherName" id="fatherNameId" maxlength="30" class="form-control"  />
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Mobile
										Number <font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="fatherMobileNo" id="fatherMobileNoId" onchange="fatherValidatePhoneNo()"
											maxlength="10" class="form-control" />
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
										<option value=""></option>
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
									style="text-align: right; line-height: 35px;">Monthly Income </label>
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
									</label>
									<div class="col-xs-7">
										<textarea name="fathersOfficialAddress" id="fathersOfficialAddress" onkeypress="HideError()" class="form-control" style="height: 75px;"></textarea>
									</div>
							</div>
						</fieldset>
						
						<fieldset>
							<legend style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif;font-size: 16px;color: #000000;">Mother's Details:</legend>
							<div class="form-group clearfix">
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
									<input type="text" name="motherMobileNo" id="motherMobileNoId" onchange="motherValidatePhoneNo()"
											maxlength="10" class="form-control" />
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
										<option value=""></option>
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
									style="text-align: right; line-height: 35px;">Monthly Income </label>
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
								</label>
								<div class="col-xs-7">
									<textarea name="mothersOfficialAddress" id="mothersOfficialAddress" onkeypress="HideError()" class="form-control" style="height: 75px;"></textarea>
								</div>
							</div>
						</fieldset>	
								
							<div class="form-group clearfix" style="margin-top: 10px;">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 18px;">Are The Parents Alumni In School? </label>
									<div class="col-xs-7" style="margin-left: -12px;">
										<select name="parentsAlumni" id="parentsAlumni" class="form-control" style="width: 315px;  ">
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
								<div class="col-xs-7" style="margin-left: -12px;">
									<input type="text" class="form-control" name="fatherAlumniname"	id="fatherAlumniname" maxlength="25" style="width: 315px;" />
								</div>
							</div>
								
							<div class="form-group clearfix" id="hiddenfatheralumniyear">
								<label id="fatherAlumniyearLabel" for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Year</label>
								<div class="col-xs-7" style="margin-left: -12px;">
									<input type="text" class="form-control" name="fatherAlumniyear" id="fatherAlumniyear" maxlength="25" style="width: 315px;"/>
								</div>
							</div>
							
							<div class="form-group clearfix" id="hiddenmotheralumniname">
								<label id="motherAlumniLabel" for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Mother Name<font color="red">*</font></label>
								<div class="col-xs-7" style="margin-left: -12px;">
									<input type="text" class="form-control" name="motherAlumniname"	id="motherAlumniname" maxlength="25" style="width: 315px;" />
								</div>
							</div>
								
							<div class="form-group clearfix" id="hiddenmotheralumniyear">
								<label id="motherAlumniIdLabel" for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Year</label>
								<div class="col-xs-7" style="margin-left: -12px;">
									<input type="text" class="form-control" name="motherAlumniyear" id="motherAlumniyear" maxlength="25" style="width: 315px;" />
								</div>
							</div>
						</div>
					</div>
				</div>
		</form>
	</div>

</body>
</html>