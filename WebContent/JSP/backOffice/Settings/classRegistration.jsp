<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
 
<script type="text/javascript" src="JS/backOffice/Settings/classRegistration.js"></script>

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
<div>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"/></logic:present></span>
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

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a href="#" style="color: #000000"> <h3 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Class Details
							</h3></a>

							<div class="navbar-right" >
								
							<span id="classSave" class="buttons" style="left:-3px;">Save</span>
							<span id="back1" class="buttons">Back</span>
									
							</div>
					</div>

					<!-- <center> -->
					
					<!-- </center> -->
					
	 <input type="hidden" name="hiddenlocId" id="hiddenlocId" 
		 value='<logic:present name="locId" scope="request"><bean:write name="locId" /></logic:present>'></input>
	
	<input type="hidden" name="hiddenstreamId" id="hiddenstreamId" 
		 value='<logic:present name="streamId" scope="request"><bean:write name="streamId" /></logic:present>'></input>
	
	<input type="hidden" name="hiddenstatus" id="hiddenstatus" 
		 value='<logic:present name="status" scope="request"><bean:write name="status" /></logic:present>'></input>	 	 
					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body clearfix">
							<div class="col-md-6" id="txtstyle" style="font-size: 13px; color: #000;">
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
								<input type="hidden" name="schoolId" class="form-control" id="schoolId" value='<logic:present name="editClasslist"><bean:write name="editClasslist" property="locationId" /></logic:present>'></input>
							</div>
					
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class
										Name</label>
									<div class="col-xs-7">
									<select class="form-control" name="class" onchange="HideError(this)" id="classId">
									<option value="">-------------Select-----------</option>
									<option value="1">Pre-KG</option>
									<option value="2">LKG</option>
									<option value="3">UKG</option>
									<option value="4">I</option>
									<option value="5">II</option>
									<option value="6">III</option>
									<option value="7">IV</option>
									<option value="8">V</option>
									<option value="9">VI</option>
									<option value="10">VII</option>
									<option value="11">VIII</option>
									<option value="12">IX</option>
									<option value="13">X</option>
									<option value="14">XI</option>
									<option value="15">XII</option>
									</select>
									</div>	
									<div class="col-xs-7">
										
											<input type="hidden" name="status" id="statusId" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="status"/></logic:present>"/>
											<input type="hidden" name="updateClassCode" id="updateClassCode" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="classId"/></logic:present>"/>
											<input type="hidden" name="hiddenStreamId" id="hiddenStreamId" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="streamId"/></logic:present>"/>
											<input type="hidden" name="hiddenclassname" id="hiddenclassname" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="className"/></logic:present>"/>
									</div>
								</div>
							</div>
							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Select Stream</label>
									<div class="col-xs-7">
									<select class="form-control" name="stream" onchange="HideError(this)" id="streamId">
										<option value="">-------------Select-----------</option>
									</select>
									</div>
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
