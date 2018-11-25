  <!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<script type="text/javascript" src="JS/backOffice/Settings/DepartmentDetails.js"></script>

</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p >
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name= "title"></bean:write></logic:present></span>
		</p>
		<div class="errormessagediv" align="center" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>
			
					
						<div class="successmessagediv" align="center"  style="display: none;">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
						
						

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne" style="margin-bottom: 5px;">
						
							<a href="#" style="color: #000000"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Department Details
							</h3></a>
							

							<div class="navbar-right">
								<span id="saveid" class="buttons">Save</span> 
							  	<span id="back1" class="buttons">Back</span>
							</div>
					</div>
	<input type="hidden" id="hiddenstatus" 
	value='<logic:present name="status" scope="request"><bean:write name="status"/></logic:present>'></input>
   
   <input type="hidden" id="hiddensearchname" 
	value='<logic:present name="searchname" scope="request"><bean:write name="searchname"/></logic:present>'></input>
						
			<input type="hidden" id="updatedepartmentid" value='<logic:present name="editlist"><bean:write name="editlist" property="depId" /></logic:present>'></input>
			<input type="hidden" id="updatedepartmentname" value='<logic:present name="editlist"><bean:write name="editlist" property="depName" /></logic:present>'></input>			
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-7" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Department Name</label>
									<div class="col-xs-7">
									<input type="text" name="departmentname" onkeypress="HideError()" class="form-control" id="departmentid" 
									value='<logic:present name="editlist"><bean:write name="editlist" property="depName" /></logic:present>'></input>
									</div>
								</div>
						
								
					                              
							  <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description
										</label>
									<div class="col-xs-7">
										<textarea style="resize:none;margin-bottom: 5px;" rows="4" cols="25"  class="form-control"
											name="description" id="descriptionid" ><logic:present name="editlist"><bean:write name="editlist" property="desc"/></logic:present></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
			<%-- 			<div class="panel panel-default">
					
			
					<input type="hidden" name="updatevehicleCode" id="updatevehicleCode" value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="vehiclecode"/></logic:present>"/>
						</div> --%>
					</div>
				</div>
			</div>
		</div>	
	</form>
</body>
</html>

	
