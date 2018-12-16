<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>eCampus Pro</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="JS/backOffice/Settings/editLocation.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Settings/CountryStateCity.js"></script>

<style>
#allstudent td:nth-child(1) {
	width: 300px;
}
</style>
</head>

<body class="addLocation">

	<div class="content" id="div1">
		<div id="dialog"></div>



		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />
		</logic:present>'></input>
		<!-- </form> -->


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



		<div class="panel-heading clearfix">
			<h3 class="panel-title" style="color: #000000;">School Details</h3>

			<div class="navbar-right">

				<logic:present name="UserDetails" scope="session">
					<logic:iterate id="daymap" name="UserDetails"
						property="permissionmap" scope="session">
						<logic:equal value="SLCADD" name="daymap" property="key">
							<logic:equal value="true" name="daymap" property="value">
								<span class="btn btn-xs btn-primary margin-t-5 addSchool"
									data-toggle="modal" data-target="#myModal"> <span
									class="glyphicon glyphicon-plus"></span>Add New School
								</span>

							</logic:equal>
						</logic:equal>
					</logic:iterate>
				</logic:present>

			</div>
			<input type="hidden" id="editPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="SLCUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
		</div>

		<input type="hidden" id="custstatus"
			value="<logic:present name='schlstatus' scope='request' ><bean:write name='schlstatus'/></logic:present>">

		<div id="collapseOne_NEW" class="content-div">
			<input type="hidden" id="hiddenstatus" name="hiddenstatus"
				value="<logic:present name='status' scope='request' ><bean:write name='status'/></logic:present>" />

			<div class="panel-body"
				style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">

				<table class="table" id="allstudent">
					<thead>
						<tr>
							<th>School</th>
							<th>Contact Details</th>
							<th>School Logo</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>

					</tbody>

				</table>
				<!-- <div class='pagebanner'>
							<select id='show_per_page'><option value='50'>50</option>
								<option value='100'>100</option>
								<option value='200'>200</option>
								<option value='300'>300</option>
								<option value='400'>400</option>
								<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
						<div class='pagination pagelinks' style="margin-bottom: 10px;"></div> -->
				<%-- </logic:present> --%>

			</div>

		</div>

		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="modal-title" id="myModalLabel">Download</h3>
					</div>


					<div class="modal-body">
						<form id="upload" name="LocationMasterForm" method="post"
							enctype="multipart/form-data">




							<input type="hidden" id="updatelocationname"
								value='<logic:present name="editlist"><bean:write name="editlist" property="locationname" />	</logic:present>'></input>
							<input type="hidden" id="updatelocationid"
								value='<logic:present name="editlist"><bean:write name="editlist" property="location_id" /></logic:present>'></input>








							<div class="row">
								<div class="col-md-6"
									style="font-family: Roboto, sans-serif; font-size: 13px; color: #000; padding-bottom: 9px;">

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">School
											Name<font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<input type="text" name="locationname"
												onkeyup="HideError(this)" maxlength="249"
												class="form-control" id="schoolId" tabindex="1"
												style="width: 139%;"
												value='<logic:present name="editlist"><bean:write name="editlist" property="locationname" /></logic:present>'></input>
										</div>
									</div>

									<input type="hidden" id="operation" name="operation"
										value="<bean:write name="operation" />" />


									<div class="form-group clearfix" style="margin-bottom: 10px;">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Address1<font
											color="red">*</font>
										</label>
										<div class="col-xs-7">
											<textarea tabindex="4" maxlength="249" class="form-control"
												name="address" id="address" onkeyup="HideError(this)"><logic:present
													name="editlist">
												<bean:write name="editlist" property="address" />
											</logic:present></textarea>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Country<font
											color="red">*</font>
										</label>
										<div class="col-xs-7">
											<select class="form-control countries"
												onchange="HideError(this)" tabindex="6" name="country"
												id="countryId">
												<option value="">---------Select---------</option>
											</select>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">City/District<font
											color="red">*</font>
										</label>
										<div class="col-xs-7">
											<select class="form-control cities"
												onchange="HideError(this)" tabindex="8" name="city"
												id="cityId">
												<option value="">---------Select---------</option>
											</select>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Contact
											Person<font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<input type="text" name="cperson" onkeyup="HideError(this)"
												class="form-control" id="cpeson" tabindex="10"
												maxlength="49"
												value='<logic:present name="editlist"><bean:write name="editlist" property="cperson" /></logic:present>'></input>
										</div>
									</div>
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Mobile
											No.<font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<input type="text" name="cmobileno" onkeyup="HideError(this)"
												onkeypress="return isNumber(event)" class="form-control"
												id="cmobileno" tabindex="12" maxlength="10"
												value='<logic:present name="editlist"><bean:write name="editlist" property="cmobileno" /></logic:present>'></input>
										</div>
									</div>
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">School
											Affiliation No.<font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<input type="text" name="saffiliationno" tabindex="14"
												onkeyup="HideError(this)" class="form-control"
												id="affiliation" maxlength="29"
												value='<logic:present name="editlist"><bean:write name="editlist" property="affilno" /></logic:present>'></input>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Website</label>
										<div class="col-xs-7">
											<input type="text" name="website" onkeyup="HideError(this)"
												class="form-control" id="website" tabindex="2"
												maxlength="49"
												value='<logic:present name="editlist"><bean:write name="editlist" property="website" /></logic:present>'></input>
										</div>
									</div>
								</div>

								<div class="col-md-6"
									style="font-family: Roboto, sans-serif; font-size: 13px; color: #000; padding-bottom: 9px;">

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Email
											Id<font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<input type="text" name="emailId" onkeyup="HideError(this)"
												class="form-control" id="emailId" tabindex="3"
												maxlength="49"
												value='<logic:present name="editlist"><bean:write name="editlist" property="emailId" /></logic:present>'></input>
										</div>
									</div>

									<div class="form-group clearfix" style="margin-bottom: 10px;">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Address2
										</label>
										<div class="col-xs-7">
											<textarea tabindex="5" maxlength="249" class="form-control"
												name="address2" id="address2" onkeyup="HideError(this)"><logic:present
													name="editlist">
												<bean:write name="editlist" property="address2" />
											</logic:present></textarea>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">State<font
											color="red">*</font>
										</label>
										<div class="col-xs-7">
											<select class="form-control states"
												onchange="HideError(this)" tabindex="7" name="state"
												id="stateId">
												<option value="">---------Select---------</option>
											</select>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Pin
											Code<font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<input type="text" name="pincode" tabindex="9"
												onkeyup="HideError(this)" class="form-control" id="pincode"
												maxlength="29"
												value='<logic:present name="editlist"><bean:write name="editlist" property="pinCode" /></logic:present>'></input>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Email
											Id </label>
										<div class="col-xs-7">
											<input type="text" name="cemailId" onkeyup="HideError(this)"
												class="form-control" id="cemailId" tabindex="11"
												maxlength="49"
												value='<logic:present name="editlist"><bean:write name="editlist" property="cemailId" /></logic:present>'></input>
										</div>
									</div>

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Landline
											No. </label>
										<div class="col-xs-7">
											<input type="text" name="clandline" onkeyup="HideError(this)"
												class="form-control" id="clandline" tabindex="13"
												maxlength="49"
												value='<logic:present name="editlist"><bean:write name="editlist" property="clandline" /></logic:present>'></input>
										</div>
									</div>

									<input type="hidden" id="hiddenschoollogoId"
										name="hiddenschoollogoId"
										value="<logic:present name="editlist"><bean:write name="editlist" property="schoollogo" /></logic:present>" />

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">School
											Logo<font color="red">*</font>
										</label>
										<div class="col-xs-7" style="margin-bottom: 5px;">
											<input type="file" id="schoollogo" name="schoollogo"
												class="form-control" onkeyup="HideError(this)"
												style="width: 76%; display: inline;" tabindex="15" value="" />

											<input type="button" id="downloadlicenceid" name="profile"
												class="downloadDoc1" value="Download" /> <span
												style="font-size: 20px; color: red; cursor: pointer;"
												id="deleteProfile"> x</span> <span><img
												id="schoolimagePreview" src="#" alt="image" width="35px"
												height="35px"
												style="margin-left: 5px; display: none; margin-top: -6px;" /></span>


										</div>
									</div>


									<input type="hidden" name="hiddencountry" id="hiddencountry"
										value='<logic:present name="editlist"><bean:write name="editlist" property="countryId" /></logic:present>'></input>
									<input type="hidden" name="hiddenstate" id="hiddenstate"
										value='<logic:present name="editlist"><bean:write name="editlist" property="stateId" /></logic:present>'></input>
									<input type="hidden" name="hiddencity" id="hiddencity"
										value='<logic:present name="editlist"><bean:write name="editlist" property="cityId" /></logic:present>'></input>

								</div>
							</div>



						</form>

					</div>
					<div class="modal-footer">
						<span id="save" class="buttons button-blue">Save</span> <span
							class="buttons button-simple" data-dismiss="modal">Close</span>
					</div>


				</div>
			</div>
		</div>

	</div>
</body>
</html>