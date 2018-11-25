<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%><!DOCTYPE html>
<html lang="en">

<head>
 <script type="text/javascript" src="JS/backOffice/Settings/AddDesignation.js"></script>

</head>


<body>

	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present
					name="title">
					<bean:write name="title"></bean:write>
				</logic:present></span>
		</p>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center" style="display: none;">
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
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv1" align="center"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 37%; font-size: 10pt; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>

		<div class="successmessagediv" style="display: none;" align="center">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"
					style="text-align: center;"><span class="successmessage"
					style="text-align: center;"></span></a>
			</div>
		</div>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion" href="#"
							style="color: #000000"><h3 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Designation Details
						</h3></a>
					<div class="navbar-right">
						<span id="submit" class="buttons">Save</span> 
						<span id="back1" class="buttons">Back</span>
					</div>

				</div>

<input type="hidden" id="hiddenstatus"
					value='<logic:present name="status" scope="request"><bean:write name="status" /></logic:present>'></input>
<input type="hidden" id="hiddensearchname"
					value='<logic:present name="searchname" scope="request"><bean:write name="searchname" /></logic:present>'></input>									
				<input type="hidden" id="designationid"
					value='<logic:present name="DesignationLIst"><bean:write name="DesignationLIst" property="designationid"/></logic:present>'></input>

				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<div class="col-md-6" id="txtstyle" style = "padding-bottom:10px;">

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Designation</label>
								<div class="col-xs-7">
									<input type="text" name="designationname" class="form-control"
										id="designation" onkeypress="HideError(this)"
										value='<logic:present name="DesignationLIst"><bean:write name="DesignationLIst" property="designation_name"/></logic:present>'></input>
								</div>
							</div>
					<input type="hidden" id="hiddendesignation" 
										value='<logic:present name="DesignationLIst"><bean:write name="DesignationLIst" property="designation_name"/></logic:present>'></input>
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Description</label>
								<div class="col-xs-7">
									<textarea style="resize: none" rows="4" cols="25" class="form-control" name="description" class="form-control"id="description"> <logic:present name="DesignationLIst"><bean:write name="DesignationLIst" property="designation_description" /></logic:present> </textarea>
								</div>
							</div>
					
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>


</body>

</html>
