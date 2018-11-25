<!DOCTYPE html>
<html lang="en">
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<head>
 <script type="text/javascript" src="JS/backOffice/Admin/AddUser.js"></script>

</head>

<body>
	<div class="col-md-10" id="div-main" style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="Page" scope="request"><bean:write name='Page'></bean:write></logic:present></span>
		
		</p>

						<div class="errormessagediv" style="display: none;" >
							<div class="message-item">
							       <!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning" style="text-align: center;"><span
									class="validateTips" style="text-align: center;"></span></a>
							</div>
						</div> 
							
						<div class="successmessagediv" style="display: none;">
								<div class="message-item">
									<a href="#" class="msg-success bg-msg-succes" style="text-align: center;"><span class="successmessage" style="text-align: center;"></span></a>
								</div>
						</div>
				
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
							
						<h3 class="panel-title" style="color: #000000;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;New User Details</h3>
							
		 				<div class="navbar-right" >
							<span tabindex="8" class="buttons" id="save">Save</span>&nbsp;
							<span  tabindex="9" class="buttons" id="back1">Back</span>&nbsp;
						</div>
						
					</div>


<input type="hidden" id="historystatus"
					value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus" /></logic:present>' />
										
					<div class='clearfix'>
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								
							<div class="form-group clearfix">
								<label class="control-label col-xs-4" id="inputnames" style="text-align: right; line-height: 35px;">Role<font color="red"></font></label>
								<div class="col-xs-7">
									<select name="roleName" id="roleName" class="form-control" onchange="HideError(this)" tabindex="1">
									<option value="">----------Select----------</option>
									<logic:present name="roleMasterList" scope="request">
									<logic:iterate id="role" name="roleMasterList">
									<option value='<bean:write name="role" property="roleCode"/>'><bean:write name="role" property="roleName"/></option>
									</logic:iterate>
									</logic:present>
									</select>	
								</div> 
							</div>
							
	<input type="hidden" id="hiddenrole"
					value='<logic:present name="userdetail"><bean:write name="userdetail" property="roleName" /></logic:present>' />

							<div class="form-group clearfix">
								<label  class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">UserName
								</label>
								<div class="col-xs-7">
									<input tabindex="3" type="text" class="form-control" id="usernames" onclick="HideError(this)" maxlength="10"
									value="<logic:present name="userdetail"><bean:write name="userdetail" property="userName" /></logic:present>" />
								</div>
							</div>
							
							<input type="hidden" id="hiddenusername"
									value="<logic:present name="userdetail"><bean:write name="userdetail" property="userName" /></logic:present>" />
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Confirm Password
										</label>
									<div class="col-xs-7">
										<input tabindex="5" type="password" onclick="HideError(this)" class="form-control" id="cnfpwd" maxlength="8"
										value="<logic:present name="userdetail"><bean:write name="userdetail" property="password" /></logic:present>" />
									</div>
							</div>
							<div class="form-group clearfix">
								<label class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Mobile No.
								</label>
								<div class="col-xs-7">
									<input tabindex="7" type="text" class="form-control" onclick="HideError(this)" id="mobileno" maxlength="10"
									value="<logic:present name="userdetail"><bean:write name="userdetail" property="mobile" /></logic:present>" />
								</div>
							</div>
								
							</div>
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							
								<div class="form-group clearfix">
									<label class="control-label col-xs-4" id="inputnames" style="text-align: right; line-height: 35px;">School<font color="red"></font></label>
										<div class="col-xs-7">
											<select tabindex="2" name="location" id="location" onchange="HideError(this)" class="form-control">
												<option value="">----------Select----------</option>
												<logic:present name="locationList" scope="request">
												<logic:iterate id="loc" name="locationList">
													<option value='<bean:write name="loc" property="locationId"/>'><bean:write name="loc" property="locationName"/></option>
												</logic:iterate>
												</logic:present>
											</select>	
										</div> 
								</div>
								
			       <input type="hidden" id="hiddenloc"
					 value='<logic:present name="userdetail"><bean:write name="userdetail" property="locId" /></logic:present>' />

							<div class="form-group clearfix">
								<label class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Password</label>
									<div class="col-xs-7">
										<input tabindex="4" type="password" onclick="HideError(this)" class="form-control" id="pwd" maxlength="8"
										value="<logic:present name="userdetail"><bean:write name="userdetail" property="password" /></logic:present>" />
									</div>
							</div>
								
									<div class="form-group clearfix">
									<label class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Email</label>
									<div class="col-xs-7">
									<input tabindex="6" type="text" onclick="HideError(this)" class="form-control" id="email" 
									value="<logic:present name="userdetail"><bean:write name="userdetail" property="email" /></logic:present>" />
									</div>
								</div>
								
							</div>
				  <input type="hidden" id='hiddencustId' 
							value='<logic:present name="userdetail"><bean:write name="userdetail" property="userCode" /></logic:present>' />
					 <input type="hidden" id='auserid' 
							value='<logic:present name="userdetail"><bean:write name="userdetail" property="auserid" /></logic:present>' />
					
						</div>
					</div>
					</div>
				</div>
			</div>		
		</div>
</body>

</html>
