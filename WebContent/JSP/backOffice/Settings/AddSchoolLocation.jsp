
<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>
<script type="text/javascript"  src="JS/backOffice/Settings/AddSchoolAddress.js"></script>
<script type="text/javascript"  src="JS/backOffice/Settings/CountryStateCity.js"></script>

<style>
.buttons {
	vertical-align: 0px;
}

textarea {
    resize: none;
}

.navbar-right span:last-child {
	margin-right: 10px;
}
</style>
</head>

<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present
					name="Location" scope="request">
					<bean:write name="Location"></bean:write>
				</logic:present></span>
		</p>

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
					class="successmessage"></span></a>
			</div>
		</div>
      
      <form id="upload" styleId="LocationMasterForm" name="LocationMasterForm" method="post" enctype="multipart/form-data">
      
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">

					<a data-toggle="collapse" data-parent="#accordion" href="#"
						style="color: #000000"><h3 class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Branch Details
						</h3></a>

					<div class="navbar-right">
						<span id="saveid" class="buttons" style="margin-right:0px;">Save</span>
						<span id="back11" class="buttons" style="margin-right:2px;">Back</span>
					</div>
				</div>

				<input type="hidden" id="updatelocationname"
					value='<logic:present name="editlist"><bean:write name="editlist" property="locationname" /></logic:present>'></input>
				<input type="hidden" id="updatelocationid"
					value='<logic:present name="editlist"><bean:write name="editlist" property="location_id" /></logic:present>'></input>

				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body clearfix">
						
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-bottom: 9px;">

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">School
									Name<font color="red">*</font></label>
								<div class="col-xs-7">
										<select id="schollid" name="locationnid" tabindex="1" class="form-control" required>
											<!-- <option value="all">ALL</option> -->
											<logic:present name="locationList">
												<logic:iterate id="locationList" name="locationList">
													<option value="<bean:write name="locationList" property="schoolId"/>"><bean:write name="locationList" property="schoolName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
							</div>
						
						<input type="hidden" id="operation" name="operation"
						 value="<logic:present name="editlist"><bean:write name="operation" /></logic:present>" />

                         <div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">School Code<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="schoolcode" onclick="HideError(this)"
										class="form-control" id="schoolcode" tabindex="3"
										value='<logic:present name="editlist"><bean:write name="editlist" property="schoolcode" /></logic:present>'></input>
								   </div>
						 </div>

                    <div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Mobile No.<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="contactno" onclick="HideError(this)" tabindex="5"
										class="form-control" id="contactno" maxlength="10" tabindex="7"
										value='<logic:present name="editlist"><bean:write name="editlist" property="contactno" /></logic:present>'></input>
								   </div> <!-- maxlength="12" -->
					 </div>
					 
					  <div class="form-group clearfix" style="margin-bottom:10px;">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Address1<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<textarea style="" tabindex="7" class="form-control" name="address" id="address" onclick="HideError(this)"><logic:present name="editlist"><bean:write name="editlist" property="address" /></logic:present></textarea>
									</div>
					     </div>
					 
					      <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Country<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select class="form-control countries" onchange="HideError(this)" tabindex="9" name="country" id="countryId">
											<option value="">---------Select---------</option>
										</select>
									</div>
					     </div>
					     
					     <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">City/District<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select class="form-control cities" onchange="HideError(this)" tabindex="11" name="city" id="cityId">
											<option value="">---------Select---------</option>
										</select>
									</div>
						 </div>
					 
					     <div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Affiliated to<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="board" onclick="HideError(this)"
										class="form-control" id="board" tabindex="13"
										value='<logic:present name="editlist"><bean:write name="editlist" property="board" /></logic:present>'></input>
								   </div>
							</div>
						
						<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Board Logo</label>
									<div class="col-xs-7" style="margin-bottom:5px;">
										<input type="file" id="boardlogo" name="boardlogo" class="form-control" onclick="HideError(this)"
										 style="width:76%;display: inline;" tabindex="15" value=""/> 
										 
									    <input type="button" id="downloadlicenceid" name="profile" class="downloadDoc1" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteProfile">  x</span>
										 
										 <span><img id="boardimagePreview" src="#" alt="image" width="35px" 
											height="35px" style="margin-left:5px;display: none;margin-top: -6px;"/></span> 
									</div>
						 </div>
						    	
				 <input type="hidden" id="hiddenschoollogoId" name="hiddenschoollogoId"
				  value="<logic:present name="editlist"><bean:write name="editlist" property="schoollogo" /></logic:present>" />
								
							<input type="hidden" id="hiddenboardlogoId" name="hiddenboardlogoId" value="<logic:present name="editlist"><bean:write name="editlist" property="boardlogo" /></logic:present>" />
						
						 </div>

                        <div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-bottom: 9px;">
								
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Location<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="locAddId" onclick="HideError(this)"
										class="form-control" id="locAddId" tabindex="2"
										value='<logic:present name="editlist"><bean:write name="editlist" property="locationname" /></logic:present>'></input>
								   </div>
							</div>
							
				 <input type="hidden" id="hiddenlocaddressId" 
				 value='<logic:present name="hiddenlocId" scope="request"><bean:write name="hiddenlocId"/></logic:present>'></input>
					
					<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Email Id<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="emailId" onclick="HideError(this)"
										class="form-control" id="emailId" tabindex="4"
										value='<logic:present name="editlist"><bean:write name="editlist" property="emailId" /></logic:present>'></input>
								</div>
				    </div>
						
					 <div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Land line No.</label>
								<div class="col-xs-7">
									<input type="text" name="landlineno" onclick="HideError(this)"
										class="form-control" id="landlineno"  tabindex="6"
										value='<logic:present name="editlist"><bean:write name="editlist" property="landLineNo" /></logic:present>'></input>
								   </div> <!-- maxlength="12" -->
					 </div>	
					
					     <div class="form-group clearfix" style="margin-bottom:10px;">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Address2
									</label>
									<div class="col-xs-7">
										<textarea style="" tabindex="8" class="form-control" name="address2" id="address2" onclick="HideError(this)"><logic:present name="editlist"><bean:write name="editlist" property="address2" /></logic:present></textarea>
									</div>
					      </div>
						
						   <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">State<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select class="form-control states" onchange="HideError(this)" tabindex="10" name="state" id="stateId">
											<option value="">---------Select---------</option>
										</select>
									</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Pin Code<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="pincode" tabindex="12" onclick="HideError(this)" class="form-control" id="pincode"
										value='<logic:present name="editlist"><bean:write name="editlist" property="pinCode" /></logic:present>'></input>
								</div>
							</div>
								
							 <div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Affilation No.</label>
								<div class="col-xs-7">
									<input type="text" name="affilno" onclick="HideError(this)"
										class="form-control" id="affilno" tabindex="14"
										value='<logic:present name="editlist"><bean:write name="editlist" property="affilno" /></logic:present>'></input>
								   </div>
					       </div>
							
		<input type="hidden" name="hiddenaddId" name="hiddenaddId" id="hiddenaddId" value='<logic:present name="editlist"><bean:write name="editlist" property="locAddId" /></logic:present>'></input>
		<input type="hidden" name="hiddencountry" id="hiddencountry" value='<logic:present name="editlist"><bean:write name="editlist" property="countryId" /></logic:present>'></input>
		<input type="hidden" name="hiddenstate" id="hiddenstate" value='<logic:present name="editlist"><bean:write name="editlist" property="stateId" /></logic:present>'></input>
		<input type="hidden" name="hiddencity" id="hiddencity" value='<logic:present name="editlist"><bean:write name="editlist" property="cityId" /></logic:present>'></input>
						
						 </div>
					  </div>
				 </div>
			</div>
		</div>
    </form>
 </div>

</body>

</html>


