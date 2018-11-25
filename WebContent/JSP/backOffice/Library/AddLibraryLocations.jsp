<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>

<script type="text/javascript" src="JS/backOffice/Library/AddLibraryLocations.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><style>
.buttons{

vertical-align: 0px;

}
.own-panel{
padding : 15px;
}

</style>

</head>

<body>
 
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present
								name="library" scope="request">
								<bean:write name="library"></bean:write>
							</logic:present>
			</span>
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
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a  
							href="#" style="color: #000000;vertical-align: text-top;"><h3 class="panel-title" style="vertical-align: text-top;"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Library Branch Details
						</h3></a>
					

					<div class="navbar-right">
						<span id="save" class="buttons" style="margin-left: -45px;margin-right: -3px;">Save</span>
						<span id="back1" class="buttons" style="margin-left: 4px;">Back</span>
					</div>
				</div>
	
	<input type="hidden" id="historylocId" 
	value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>'>			
			 
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body own-panel" style="padding:0px;">
						<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;padding-bottom:9px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;margin-top: 15px;">Branch<font color="red"> *</font></label>
								<div class="col-xs-7">
							<select id="locationname" name="location" class="form-control"  tabindex="1" style="margin-top: 18px;  ">
							
							
								<logic:present name="locationList">
									<logic:iterate id="Location" name="locationList">
										<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
						</select>
						<input type="hidden" id="hiddenloc" value='<logic:present name="editlist"><bean:write name="editlist" property="locationid"/></logic:present>'>
						</div>
								<input type="hidden" name="schoolId" class="form-control" id="schoolId" value=''></input>
							</div>
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Description
								</label>
								<div class="col-xs-7">
								<textarea style="resize:none" rows="4" cols="25"  class="form-control" tabindex="3" name="description" id="description" ><logic:present name="editlist"><bean:write name="editlist" property="description"></bean:write></logic:present></textarea>
								</div>
							</div>
						</div>
						<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;padding-bottom:9px;">
						<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4" tabindex="2"  maxlength="50"
									style="text-align: right; line-height: 35px;margin-top: 15px;">Library Branch<font color="red"> *</font></label>
								<div class="col-xs-7">
									<input type="text" onclick="HideError(this)" style="text-transform: capitalize;margin-top: 18px;" name="libloc" id="libloc" class="form-control"  value='<logic:present name="editlist" ><bean:write name="editlist" property="libraryLocations"></bean:write></logic:present>'></input>
									<input type="hidden" id="hiddenlibid" value="<logic:present name="libid"><bean:write name="libid"></bean:write></logic:present>"/>
									<input type="hidden" id="hiddenlocname" value="<logic:present name="editlist" ><bean:write name="editlist" property="libraryLocations"></bean:write></logic:present>"/>
								<input type="hidden" id="hiddenschoolname" value="<logic:present name="editlist" ><bean:write name="editlist" property="schoolName"></bean:write></logic:present>"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html:html>