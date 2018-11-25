<!DOCTYPE html>
<html lang="en">
<%@  taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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

<script type="text/javascript"
	src="JS/backOffice/Transport/AddVehicleDetails.js"></script>
<script type="text/javascript">

function  CheckIsNumeric1(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode!=32 && charCode!=40 && charCode!=41 &&  charCode != 45 &&(charCode < 48 || charCode > 57) && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode >122))  {
        
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

.panel-primary>.panel-heading {
	margin-bottom: -11px;
	padding-left: 15px;
}
</style>

</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p class="transportheader">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="vehicledetails" scope="request">
					<bean:write name="vehicledetails"></bean:write>
				</logic:present> Master</span>
		</p>


		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>


		<div class="errormessagediv"
			style="display: none; text-align: center;">
			<div class="message-item" align="center">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

    <input type="hidden" id="historylocId"
    value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>"/>

    <input type="hidden" id="historystatus"
    value="<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>"/>
    
    <input type="hidden" id="historysearch"
    value="<logic:present name="historysearch" scope="request"><bean:write name="historysearch"/></logic:present>"/>

		<form action="transport.html?method=saveVehicleDetails" id="myForm"
			method="post" enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">

						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #000000; vertical-align: text;"><h3
								class="panel-title">
								<i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;<span><logic:present
					name="vehicledetails" scope="request">
					<bean:write name="vehicledetails"></bean:write> Details
				</logic:present></span>
							</h3></a>

						<div class="navbar-right">

							<span id="saveid" class="buttons">Save</span>&nbsp;
							<span id="back1" class="buttons" >Back</a></span>
						</div>

					</div>


					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" style="padding-top: 25px;">
							<div class="col-md-6" id="txtstyle"
								style="font-size: 13px; color: #000;">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch<font color="red"> *</font>
									</label>
									<div class="col-xs-7">
										<select id="locationname" name="locid" class="form-control" tabindex="1"
											onchange="HideError(this)" required>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option
														value="<bean:write name="Location" property="locationId"/>"><bean:write
															name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Vehicle
										Name<font color="red"> *</font>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="vehiclename" tabindex="3"
											onclick="HideError(this)" id="vehiclename" placeholder=""
											onkeypress="return CheckIsNumeric1(event);" maxlength="49"
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="vehiclename"/></logic:present>" />
									</div>
								</div>




								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Engine
										Number<font color="red"> *</font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="enginenumber" id="enginenumber" tabindex="5"
											onclick="HideError(this)"
											onkeypress="return CheckIsNumeric1(event);"
											class="form-control" placeholder="" maxlength="49"
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="enginenumber"/></logic:present>" />
									</div>
								</div>




								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Tax
										Issued Date<font color="red"> *</font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="taxpaid" id="taxpaid" tabindex="7"
											onclick="HideError(this)" readonly="readonly"
											class="form-control"
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="taxpaid"/></logic:present>" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Last
										Emission Test Date<font color="red"> *</font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="pollution" id="pollution" tabindex="9"
											onclick="HideError(this)" readonly="readonly"
											class="form-control"
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="pollution"/></logic:present>" />
									</div>
								</div>


							</div>

							<div class="col-md-6" id="txtstyle"
								style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 18px;">Registration
										Number<font color="red"> *</font>
									</label>
									<div class="col-xs-7">

										<input type="text" name="vehicleregno" class="form-control" tabindex="2"
											id="vehicleregno" onblur="registernumberValidation()"
											onclick="HideError(this)" maxlength="15"
											onkeypress="return CheckIsNumeric1(event);" placeholder=""
											value="<logic:present name="vehicleDetails" scope="request" ><bean:write name="vehicleDetails" property="vehicleregno"/></logic:present>" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Vehicle
										Type<font color="red"> *</font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="vehicletype" id="vehicletype" tabindex="4"
											onkeypress="HideError(this)"
											onclick="return CheckIsNumeric1(event);" class="form-control"
											placeholder=""  maxlength="49"
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="vehicletype"/></logic:present>" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Chassis
										Number<font color="red"> *</font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="chassisno" id="chassisno"
											onclick="HideError(this)" tabindex="6"
											onkeypress="return CheckIsNumeric1(event);"
											onblur="chassisnovalidation()" class="form-control" maxlength="17"
											placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="chassisno"/></logic:present>" />
									</div>
								</div>




								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px; padding-right: 26px;">Tax
										Expiry Date</label>
									<div class="col-xs-7">
										<input type="text" name="taxexpirydate" id="taxexpirydate" tabindex="8"
											class="form-control" onkeypress="HideError(this)"
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="taxexpirydate"/></logic:present>" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px; padding-right: 26px;">RC
										Upload</label>
									<div class="col-xs-7">

										<input type="file" id="uploadrcfile" name="rcfile" tabindex="10"
											class="custom-file-uploadfile form-control" onclick="HideError(this)"
											style="height: 20%;" /> <input type="button"
											id="document2btn" name="idproof" class="downloadDoc2"
											value="Download" /> <span
											style="font-size: 20px; color: red; cursor: pointer;"
											id="deleteIDProof">x</span>

									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingTwo">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseTwo" style="color: #000000; vertical-align: text;"><h3
								class="panel-title">
								<i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;<span>Insurance
									& Others Details</span>
							</h3></a>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingTwo">
						<div class="panel-body" style="padding-top: 15px;">

							<div class="col-md-6" id="txtstyle"
								style="font-size: 13px; color: #000;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Company
										Name<font color="red"> *</font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="companyname" id="companyname" tabindex="11"
											onclick="HideError(this)" class="form-control" placeholder=""
											onkeypress="return CheckIsNumeric1(event);" maxlength="100"
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="companyname"/></logic:present>" />
									</div>
								</div>

                                <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Issued
										Date<font color="red"> *</font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="issueddate" id="issueddate" tabindex="13"
											placeholder="" readonly="readonly" class="form-control"
											onclick="HideError(this)" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="issueddate"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Fitness Certificate Date<font color="red"> *</font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="fc" id="fc" placeholder="" tabindex="15"
											readonly="readonly" class="form-control"
											onclick="HideError(this)" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="fc"/></logic:present>" />
									</div>
								</div>



							</div>

							<div class="col-md-6" id="txtstyle"
								style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">

                              <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Policy
										Number<font color="red"> *</font>
									</label>
									 <div class="col-xs-7">
										<input type="text" name="doneby" id="doneby" tabindex="12"
											onclick="HideError(this)" maxlength="49"
											onkeypress="return CheckIsNumeric1(event);"
											class="form-control" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="doneby"/></logic:present>" />
									 </div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Expiry
										Date</label>
									<div class="col-xs-7">
										<input type="text" name="expirydate" id="expirydate" tabindex="14"
											class="form-control" onkeypress="HideError(this)"
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="expirydate"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Permit
										Validity Date<font color="red"> *</font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="permitvalidity" id="permitvalidity" tabindex="16"
											placeholder="" readonly="readonly" class="form-control"
											onclick="HideError(this)" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="permitvalidity"/></logic:present>" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px; padding-left: 10px; padding-right: 26px; margin-left:">Insurance
										Upload</label>
									<div class="col-xs-7">

										<input type="file" id="uploadinsurance" name="insurancefile" tabindex="17"
											class="custom-file-uploadfile form-control" onclick="HideError(this)"
											style="height: 20%; margin-bottom: 8px;" /> <input
											type="button" id="document1btn" name="profile"
											class="downloadDoc1" value="Download" /> <span
											style="font-size: 20px; color: red; cursor: pointer;"
											id="deleteProfile"> x</span>

									</div>
								</div>

							</div>
						</div>
					</div>

				</div>

				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingTwo">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseThree"
							style="color: #000000; vertical-align: text;"><h3
								class="panel-title">
								<i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;<span>Driver
									Mapping</span>
							</h3></a>
					</div>
					<div id="collapseThree" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingTwo">
						<div class="panel-body" style="padding-top: 15px;">
							<div class="col-md-6" id="txtstyle"
								style="font-size: 13px; color: #000;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Driver
										Name</label>
									<div class="col-xs-7">
										<select name="driverName" id="drivername" class="form-control"
											onchange="getDriverEntireDetails()" tabindex="18"
											onkeypress="HideError(this)">
											<option value="">-----Select-----</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Experience </label>
									<div class="col-xs-7">
										<input type="text" name="experience" id="experience" tabindex="20"
											readonly="readonly" class="form-control"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="experience"/></logic:present>" />
									</div>
								</div>
								<%-- <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">DL
										Issued Date</label>
									<div class="col-xs-7">
										<input type="text" name="dl_issued_date" id="dlissued"
											readonly="readonly" class="form-control"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="dl_issued_date"/></logic:present>" />
									</div>
								</div> --%>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">DL
										Expiry Date</label>
									<div class="col-xs-7">
										<input type="text" name="dl_validity" id="dlexpiray" tabindex="22"
											readonly="readonly" class="form-control"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="dl_validity"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Driving
										Licence No.</label>
									<div class="col-xs-7">
										<input type="text" name="drivingliecenseNo" id="dlno"
											readonly="readonly" class="form-control" tabindex="24"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="drivingliecenseNo"/></logic:present>" />
									</div>
								</div>


							</div>

							<div class="col-md-6" id="txtstyle"
								style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Mobile
										Number</label>
									<div class="col-xs-7">
										<input type="text" name="mobile" id="phoneno" tabindex="19"
											readonly="readonly" class="form-control"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="mobile"/></logic:present>" />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Date Of
										Join</label>
									<div class="col-xs-7">
										<input type="text" name="dateofJoin" id="doj"  tabindex="21"
											readonly="readonly" class="form-control"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="dateofJoin"/></logic:present>" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Licence
										To Drive </label>
									<div class="col-xs-7">
										<input type="text" name="licensetodrive" id="licencedrive"
											readonly="readonly" class="form-control" tabindex="23"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="license"/></logic:present>" />
									</div>
								</div>



							</div>
						</div>
					</div>

				</div>

				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingTwo">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseFourth"
							style="color: #000000; vertical-align: text;"><h3
								class="panel-title">
								<i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;<span>Route
									Mapping</span>
							</h3></a>

					</div>
					<div id="collapseFourth" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingTwo">
						<div class="panel-body" style="padding-top: 15px;">
							<div class="col-md-6" id="txtstyle"
								style="font-size: 13px; color: #000;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Route
										Name</label>
									<div class="col-xs-7">
										<select name="routename" id="routename" class="form-control"
											onchange="getRouteEntireDetails()" tabindex="25"
											onkeypress="HideError(this)">
											<option value=""></option>
										</select>
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Halt
										time(min)</label>
									<div class="col-xs-7">
										<input type="text" name="halttime" id="halttime"
											readonly="readonly" class="form-control" tabindex="27"
											value="<logic:present name="RouteDetails" ><bean:write name="RouteDetails" property="halttime"/></logic:present>" />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Total
										Distance(km)</label>
									<div class="col-xs-7">
										<input type="text" name="totaldistance" id="totaldistance"
											readonly="readonly" class="form-control" tabindex="29"
											value="<logic:present name="RouteDetails" ><bean:write name="RouteDetails" property="totalDistance"/></logic:present>" />
									</div>
								</div>

							</div>

							<div class="col-md-6" id="txtstyle"
								style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Route
										No </label>
									<div class="col-xs-7">
										<input type="text" name="routeno" id="routeno"
											readonly="readonly" class="form-control" tabindex="26"
											value="<logic:present name="RouteDetails" ><bean:write name="RouteDetails" property="routeNo"/></logic:present>" />
									</div>
								</div>
								<%--  <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Destination</label>
									<div class="col-xs-7">
										<input type="text" name="destination" id="destination"
											readonly="readonly" class="form-control"
											value="<logic:present name="RouteDetails" ><bean:write name="RouteDetails" property="destination"/></logic:present>" />
									</div>
								</div>

								<br />  --%>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Total
										Stops</label>
									<div class="col-xs-7">
										<input type="text" name="totalstops" id="totalstops"
											readonly="readonly" class="form-control"  tabindex="28"
											value="<logic:present name="RouteDetails" ><bean:write name="RouteDetails" property="totalStops"/></logic:present>" />
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>

                   <input type="hidden" name="hiddenLocId" id="hiddenLocId"
					value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="locId"/></logic:present>" />

				<input type="hidden" name="updatevehicleCode" id="hvehicleid"
					value="<logic:present name="vehiclecode" ><bean:write name="vehiclecode" /></logic:present>" />

				<input type="hidden" name="driverCode" id="driverCode"
					value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="driverCode"/></logic:present>" />

				<input type="hidden" name="driverName" id="hiddendriverName"
					value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="driverName"/></logic:present>" />
				<input type="hidden" name="status" id="statusId"
					value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="status"/></logic:present>" />

				<input type="hidden" name="hiddenfuel" id="hiddenfuelId"
					value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="fuelusedintheengine"/></logic:present>" />

				<input type="hidden" name="routecodeid" id="routecodeid"
					value="<logic:present name="RouteDetails" ><bean:write name="RouteDetails" property="routeCode"/></logic:present>" />

				<input type="hidden" name="locid" id="hiddenlocid"
					value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="locid"/></logic:present>" />

				<input type="hidden" name="hrcfileid" id="hrcfileid"
					value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="rcfile"/></logic:present>" />


				<input type="hidden" name="hinsurancefileid" id="hinsurancefileid"
					value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="insurancefile"/></logic:present>" />





			</div>



		</form>
	</div>

</body>

</html>
