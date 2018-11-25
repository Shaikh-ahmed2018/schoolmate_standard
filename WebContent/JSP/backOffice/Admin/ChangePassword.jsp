<!DOCTYPE html>
<html lang="en">
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<head>
<script type="text/javascript" src="JS/backOffice/Admin/ChangePassword.js"></script>

 

</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
		<span class="glyphicon glyphicon-user"></span>&nbsp;<span id="pageHeading">Change Password</span>
		
		</p>

						<div class="errormessagediv"style="display: none;" >
							<div class="message-item">
							       <!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning" style="text-align: center;"><span
									class="validateTips" style="text-align: center;"></span></a>
							</div>
						</div> 
							
						<div class="successmessagediv" style="display: none;">
					    <div class="message-item"> <a href="#" class="msg-success bg-msg-succes" style="text-align: center;"><span class="successmessage" style="text-align: center;"></span></a></div>
						</div>
					
					
				
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="panel panel-primary">
			<div class="panel-heading" role="tab" id="headingOne">
			  <a data-toggle="collapse" data-parent="#accordion"
				href="#collapseOne" style="color: #000000"><h3 class="panel-title" style="color: #000000;">
				<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Change Password </h3></a>
			
					<div class="navbar-right" >
					<span class="buttons" id="save">Save</span>&nbsp;
					<span class="buttons" id="back1">Back</span>&nbsp;
				</div>
		 </div>
					
	<input type="hidden" id="typename1" 
	value='<logic:present name="typename1" scope="request"><bean:write name="typename1"/></logic:present>'></input>
	
	<input type="hidden" id="status1" 
	value='<logic:present name="status1" scope="request"><bean:write name="status1"/></logic:present>'></input>
	
	<input type="hidden" id="searchTextId1" 
	value='<logic:present name="searchTextId1" scope="request"><bean:write name="searchTextId1"/></logic:present>'></input>				
					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">First
										Name</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="firstname" value="<logic:present name="userrecordVo" scope="request"><bean:write name="userrecordVo" property="firstName"></bean:write></logic:present>"
											placeholder="" readonly="readonly" >
									</div>
								</div>
							

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">New Password
										</label>
									<div class="col-xs-7">
										<input type="password" class="form-control" id="newpassword" onkeypress="HideError()"
											placeholder="">
									</div>
								</div>
							</div>
							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">UserName
										</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="username" value="<logic:present name="userrecordVo" scope="request"><bean:write name="userrecordVo" property="email"></bean:write></logic:present>"
											placeholder="" readonly="readonly">
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Confirm Password
										</label>
									<div class="col-xs-7">
										<input type="password" class="form-control" id="confirmpassword" onkeypress="HideError()"
											placeholder="">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="htechId" value='<logic:present name="userrecordVo"><bean:write name="userrecordVo" property="techId" /></logic:present>'></input>
			<input type="hidden" id="hUser" value='<logic:present name="UserId" scope="request"><bean:write name="UserId" scope="request"></bean:write></logic:present>'></input>	
	</div>	
		
		
	</div>

</body>

</html>
