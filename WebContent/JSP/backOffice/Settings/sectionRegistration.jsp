<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <script type="text/javascript" src="JS/backOffice/Settings/sectionRegistration.js"></script>

<script type="text/javascript">
	
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>

</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"></bean:write></logic:present></span>
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

		<logic:present name="error" scope="request">
			<div class="errormessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="error" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>


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
					<a href="#" class="msg-success bg-msg-succes"><span class="validateTips"></span></a>
				</div>
			</div>


		<div class="errormessagediv1"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 50%; font-size: 13px; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#" style="color: #000000"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Division Details
							</h3></a>
							
							<div class="navbar-right" >
								 <span id="classSave" class="buttons"
									 style="margin-right:6px;">Save
									</span> 
									<span class="buttons" id="back1"
									 style="margin-right:2px;">Back</a></span>
							</div>
					</div>
	<input type="hidden" name="hiddenlocId" id="hiddenlocId"
	 value='<logic:present name="locId" scope="request"><bean:write name="locId"/></logic:present>'></input>
	 
	 <input type="hidden" name="hiddenstreamId" id="hiddenstreamId"
	 value='<logic:present name="streamId" scope="request"><bean:write name="streamId"/></logic:present>'></input>				
	
	<input type="hidden" name="hiddenclassname" id="hiddenclassname"
	 value='<logic:present name="classname" scope="request"><bean:write name="classname"/></logic:present>'></input>
	 
	 <input type="hidden" name="hiddenstatus" id="hiddenstatus"
	 value='<logic:present name="status" scope="request"><bean:write name="status"/></logic:present>'></input>	
					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
					   <div class="panel-body clearfix">
						 <div class="clearfix">  
							<div class="col-md-6"  id="txtstyle"
								style="font-size: 13px; color: #000;">
								
								<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
						<select id="locationname" name="location" class="form-control" onchange="HideError(this)">
							
								<logic:present name="locationList">
									<logic:iterate id="Location" name="locationList">
										<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
						
						</select>
						</div>
								<input type="hidden" name="schoolId" class="form-control" onclick="HideError(this)" id="schoolId" value='<logic:present name="editClasslist"><bean:write name="editClasslist" property="locationId" /></logic:present>'></input>
							</div>
					
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Division Name</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="sectionName" onclick="HideError(this)" onkeyup = 'toCaseChange(this)' onkeypress='return alphaOnly(event);' maxlength="1" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="secDetailsName"/></logic:present>"/>
									</div>
								</div>
					
							</div>
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								
										<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Select Class</label>
									<div class="col-xs-7">
									<!-- 	<input type="text" class="form-control" id="inputEmail"
											placeholder="First Name" required> -->
									<select name="class" id="classId"
										class="form-control" onchange="HideError(this)">
											<option value="">-------------------Select-------------------</option>
									</select>
													</div>
								</div>
							
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Division
										Strength</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="sectionStrength" onclick="HideError(this)"
											placeholder="" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="sectionStrength"/></logic:present>"/>
											
									</div>
								</div>
								
									<input type="hidden" name="status" id="statusId" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="status"/></logic:present>"/>
											<input type="hidden" name="updateClassCode" id="updateClassCode" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="sectionId"/></logic:present>"/>
											<input type="hidden" name="hiddenStreamId" id="hiddenStreamId" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="secDetailsId"/></logic:present>"/>
											<input type="hidden" name="hiddenStreamName" id="hiddenStreamName" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="secDetailsName"/></logic:present>"/>
						
						         </div>
							</div>
						</div>
					</div>
				</div>
		</form>
	</div>
	</div>
</body>

</html>
