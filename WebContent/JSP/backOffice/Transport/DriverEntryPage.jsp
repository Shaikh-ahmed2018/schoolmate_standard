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



<script type="text/javascript" src="JS/backOffice/Transport/DriverEntryPage.js"></script> 


<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p class="transportheader">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="driverdetails" scope="request"><bean:write name="driverdetails"></bean:write></logic:present> Master</span>
		</p>
		
		
				<div class="successmessagediv" align="center" style="display: none;">
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
		
						<div class="errormessagediv" align="center" style="display: none;">
					<div class="message-item">
					<!-- Warning -->
				    <a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span>
					<bean:write	name="errormessagediv" scope="request" /></a>
					</div>
					</div>	
							</logic:present>
					
   <input	type="hidden" id="historylocId" 
   value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>"/>
   
   <input	type="hidden" id="historystatus" 
   value="<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>"/>
   
   <input	type="hidden" id="historysearch" 
   value="<logic:present name="historysearch" scope="request"><bean:write name="historysearch"/></logic:present>"/>		

		<form id="driverformid" enctype="multipart/form-data"  method="post">
			<div class="panel-group"  role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#" style="color: #000000;vertical-align: text-top;"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp; <span><logic:present name="driverdetails" scope="request"><bean:write name="driverdetails"></bean:write></logic:present> Details</span>
							</h3></a>
						

							<div class="navbar-right" style="margin-right: -2px;">
							 <span id="savedriver" class="buttons">Save</span>&nbsp;
								<span id="back1" class="buttons">Back</a></span>
							</div>
						
					</div>
					


			<input	type="hidden" name="drivercode" id="drivercode" value="<logic:present name="driverlist" ><bean:write name="driverlist" property="driverCode"/></logic:present>"/>
		    <input  type="hidden" name="vehiclecode" id="vehiclecode" value="<logic:present name="fuellist" ><bean:write name="fuellist" property="vehicleCode"/></logic:present>"/>
            <input type="hidden" name="fuelcode" id="fuelcode"  value="<logic:present name="fuellist" ><bean:write name="fuellist" property="fuelCode"/></logic:present>"/>
		 <%--  <input type="hidden" name="license" id="hlicensetodrive" value='<logic:present name="driverlist"><bean:write name="driverlist" property="license" /></logic:present>'></input> --%>
		    <input type="hidden" id="hlicense" value="<logic:present name="driverlist" ><bean:write name="driverlist" property="drivingliecenseNo"/></logic:present>" />
		    <input type="hidden" name="license" id="hlicensetodrive" value='<logic:present name="driverlist"><bean:write name="driverlist" property="license" /></logic:present>'></input>
		    <input type="hidden" name="licenseupload" id="hlicenseupload" value='<logic:present name="driverlist"><bean:write name="driverlist" property="licensedrive" /></logic:present>'></input>




			<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6" style="margin-top: 12px;" id="txtstyle">
							
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch<font color="red"> *</font></label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" onchange="HideError(this)" tabindex="1"
											class="form-control" required>
											
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
					 <input	type="hidden" name="locId" id="locId" 
					 value="<logic:present name="driverlist" ><bean:write name="driverlist" property="locId"/></logic:present>"/>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Father's Name<font color="red"> *</font></label>
									<div class="col-xs-7">
											<input type="text"  name="father_name" maxlength="49" id="father_name" class="form-control" onclick="HideError(this)"
											tabindex="3"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="father_name"/></logic:present>"/>
									</div>
								</div>
                                
							   <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Age</label>
									<div class="col-xs-7">
											<input type="text" readonly="readonly" name="age" id="ageId" class="form-control"
											tabindex="5"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="age"/></logic:present>"/>
									</div>
								</div>
								
                                <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Gender<font color="red"> *</font></label>
									<div class="col-xs-7"  id="radiostyle" style="margin-top: 3px">
									
									<input type="hidden" id="radio" value='<logic:present name="driverlist"><bean:write name="driverlist" property="gender" /></logic:present>'></input>
									
									<input type="radio" class="istrans" name="gender" tabindex="7" id="genderM" value="Male" allign="center" /><label for="Gender">&nbsp;Male&nbsp;&nbsp;&nbsp;</label>
										
										<input type="radio" class="istrans" name="gender" tabindex="7" id="genderF" value="Female" /><label for="Gender">&nbsp;Female </label>
									
<%-- 									<input type="hidden" name="updatevehicleCode" id="hdrivercode" 	value="<logic:present name="drivercode" ><bean:write name="drivercode" /></logic:present>" />
 --%>										<input type="hidden" name="driver_code" id="drivercode" value="<logic:present name="drivercode" ><bean:write name="drivercode" /></logic:present>" />
									
										<!-- <label class="radio-inline"><input type="radio"
											name="gender" id="genderM" value="Male" />Male</label> <label
											class="radio-inline"><input type="radio"
											name="gender" id="genderF" value="Female" />Female</label> -->
									</div>
								</div>
								
								 
                                <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 20px;">Emergency Contact No.<font color="red">*</font></label>
									<div class="col-xs-7">
									<input type="text" name="emerg_contact" id="emerg_contact"  class="form-control" onclick="HideError(this)"  maxlength="10"
									tabindex="9" value="<logic:present name="driverlist" ><bean:write name="driverlist" property="emerg_contact"/></logic:present>" />
									</div>
								</div>
								
								 
                               <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Experience<font color="red"> *</font></label>
										<div class="col-xs-7">
									<input type="text" name="experience" tabindex="11" id="experience"  class="form-control" onclick="HideError(this)" maxlength="4"
									 value="" />
									
									<input type="hidden" name="hiddenexperience" id="hiddenexperience"
									 value="<logic:present name="driverlist" ><bean:write name="driverlist" property="experience"/></logic:present>" />
									</div>
								</div>
							
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Driving License No<font color="red"> *</font></label>
										<div class="col-xs-7">
									<input type="text" name="drivingliecenseNo" tabindex="13" maxlength="24" id="drivingliecenseNo"  class="form-control" onclick="HideError(this)"
									 value="<logic:present name="driverlist" ><bean:write name="driverlist" property="drivingliecenseNo"/></logic:present>" />
									</div>
								</div>
								
								<%-- <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										DL Issued Date<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control" onkeypress="HideError(this)"  name="dl_issued_date" id="dl_issued_date"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dl_issued_date"/></logic:present>"/>
									</div>
								</div> --%>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">DL Validity Upto<font color="red"> *</font></label>
										<div class="col-xs-7">
									<input type="text" name="dl_validity" tabindex="15" readonly="readonly" id="dl_validity" onclick="HideError(this)"  class="form-control" 
									 value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dl_validity"/></logic:present>" />
									</div>
								</div>
								
							</div>
							
							<div class="col-md-6" style="margin-top: 12px;" id="txtstyle">
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Driver Name<font color="red"> *</font></label>
									<div class="col-xs-7">
											<input type="text" name="driverName" maxlength="49" class="form-control" id="name" onclick="HideError(this)" tabindex="2"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="driverName"/></logic:present>"/>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Date Of Birth<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control" onclick="HideError(this)" 
										tabindex="4"  name="dateofBirth" id="dateofBirthId" onchange="ageCalculateAdd();"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dateofBirth"/></logic:present>"/>
									</div>
								</div>
								
                                <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Mobile No.<font color="red"> *</font></label>
									<div class="col-xs-7">
										  <input type="text"  name="mobile" id="mobile" maxlength="10" class="form-control"  onclick="HideError(this)"
												tabindex="6" value="<logic:present name="driverlist" ><bean:write name="driverlist" property="mobile"/></logic:present>"/>
									</div>
								</div>
								 
                                 <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Date Of Joining<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text"  class="form-control" readonly="readonly" onclick="HideError(this)" 
											tabindex="8" name="dateofJoin" id="dateofJoinId"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dateofJoin"/></logic:present>"/>
									</div>
								</div>
								
								 
                                <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Address<font color="red"> *</font></label>
									<div class="col-xs-7">
										<textarea name="address" maxlength="249" tabindex="10" style="height:71px;resize: none;" id="address" onclick="HideError(this)" class="form-control"><logic:present name="driverlist"><bean:write name="driverlist" property="address"/></logic:present></textarea>
									</div>
								</div>
								
								<%-- <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										DL Issued Date</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"  name="dl_issued_date" id="dl_issued_date" placeholder="Click here" value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dl_issued_date"/></logic:present>"/>
									</div>
								</div> --%>
								
							
							<!-- 	<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">License
										Upload</label>
									<div class="col-xs-7">
									
										<input type="file" id="uploadBirth" name="licensedrive" class="custom-file-uploadfile" style="height: 20%;" />
										<input type="button" id="document1btn" name="profile" class="downloadDoc" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteProfile">  x</span> 
										

									</div>
								</div> -->
								
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">License
										Upload</label>
									<div class="col-xs-7">
									
										<input type="file" id="uploadlicence" tabindex="12" onclick="HideError(this)" name="licensedrive" class="custom-file-uploadfile form-control" style="height: 20%;" />
										<input type="button" id="downloadlicenceid" name="profile" class="downloadDoc1" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteProfile">  x</span>
								
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">License To Drive<font color="red"> *</font></label>
									<div class="col-xs-7">
										<select class="form-control" name="driving_license_types" tabindex="14" id="vehicletodrive" onchange="HideError(this)"
											class="form-control">
											<option value="">--------Select--------</option>
											<option value="CYCL">CYCL</option>
											<option value="LMV">LMV</option>
											<option value="HMV">HMV</option>
										</select>	
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
