<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>
  <script type="text/javascript" src="JS/backOffice/Admin/RoleMaster.js"></script>

<style>
.save:hover {
	cursor: pointer;
}
</style>

<style>
.buttons {
	vertical-align: 0px;
}
</style>
</head>

<body>


	<div class="content" id="div-main">

		<p>
			<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"></bean:write></logic:present></span>

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



		<form method="post" id="myform">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #000000"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;<h3 class="panel-title">Role
								Details
							</h3></a>
						


						<div class="navbar-right">

							<span class="buttons" id="save">Save</span> 
							
							<span id="back" class="buttons"> Back</a></span>

						</div>
					</div>

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="clearfix">
							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								<br />
								<div class="form-group clearfix">

									<input type="hidden" id="rolecode"
										value="<logic:present name="RoleDetails"><bean:write name="RoleDetails" property="roleCode"/></logic:present>" />
									<input type="hidden" id="hadministrator"
										value="<logic:present name="RoleDetails"><bean:write name="RoleDetails" property="isadministrator"/></logic:present>" />


									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Role
										Name </label>
									<div class="col-xs-7">
										<input type="text" name="rolename" id="rolename"
											class="form-control" onkeypress="HideError()" maxlength="25"
											value="<logic:present name="RoleDetails"><bean:write name="RoleDetails" property="roleName"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Role
										Description </label>
									<div class="col-xs-7">
										<textarea style="resize: none" rows="3" cols="10" class="form-control" name="roleDescription" id="description"><logic:present name="RoleDetails"><bean:write name="RoleDetails" property="roleDescription" /></logic:present></textarea>
									</div>
								</div>
							</div>

							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								<div class="form-group clearfix" id="admissiondatelable">
									<div class="col-xs-7">
										<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="isadministrator" id="isadministrator" 
										value='administrator' />&nbsp;&nbsp;&nbsp;<label for="inputPassword">Is Super Admin</label>
									</div>
								</div>
							</div>
							
				 <input type="hidden" name="hiddenadmin" id="hiddenadmin"
							 value="<logic:present name="RoleDetails"><bean:write name="RoleDetails" property="isadministrator"/></logic:present>" />

						</div>
					</div>

					<!-- <button type="reset" class="btn btn-info" id="clear">Clear</button> -->
					<br />
				</div>
		</form>
	</div>
</body>

</html>


