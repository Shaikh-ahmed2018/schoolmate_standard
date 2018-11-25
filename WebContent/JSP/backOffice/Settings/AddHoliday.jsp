<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <script type="text/javascript" src="JS/backOffice/Settings/addHoliday.js"></script>
  
  <style type="text/css">
  	.glyphicon-trash{
  		line-height: 15px !important;
  	}
  </style>
  
</head>

<body>
<div>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">New Holiday</span>
		</p>


                  <logic:present name="successmessagediv" scope="request">
						<div class="successmessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes" ><span class="validateTips"><bean:write
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
	
				
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a href="#" style="color: #000000"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Holiday Details
							</h3></a>
							

							<div class="navbar-right" >
								
							 <span id="saveid" class="buttons">Save</span>
									
									<span id="back"  class="buttons">Back</span>
									
							</div>
						
					</div>
				
					<input type='hidden' id='currentyear' value='<logic:present name='currentyear'><bean:write name='currentyear'/></logic:present>'/>
					<input type='hidden' id='accstdate' value='<logic:present name='startdate'><bean:write name='startdate'/></logic:present>'/>
					<input type='hidden' id='accenddate' value='<logic:present name='enddate'><bean:write name='enddate'/></logic:present>'/>
					
						<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class='col-md-12' id="txtstyle">
							<div class="col-md-6">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locationname" name="location" class="form-control" onchange="HideError(this)">
											<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option value="<bean:write name="Location" property="locationId"/>">
																<bean:write name="Location" property="locationName" />
												</option>
											</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								<br />
							</div>
							
							<div class="col-md-6" >
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select name="accyYear" id="accyYear" class="form-control" onchange="HideError(this)">
										<option value="">-------------Select-----------</option>
										<logic:present name ="AccYearList">
										<logic:iterate id="AccYear" name="AccYearList">
										<option value="<bean:write name="AccYear" property="accyearId"/>">
														<bean:write name="AccYear" property="accyearname" />
										</option>
										</logic:iterate>
										</logic:present>
										</select>
									</div>
								</div>
								<br />
							</div>
						</div>
						<div class='row'>
						<div class='col-md-12'>
						<div class="form-group">
						<table style="background: #fff;" class="table" id="addholiday">
							<tr class='firstrow'>
							<th style="font-size: 13px; text-align: center;" >Date</th>
							<th style="font-size: 13px; text-align: center;">Weekday</th>
							<th style="font-size: 13px; text-align: center; ">Holiday Name</th>
							<th style="font-size:13px; text-align: center;">Holiday Type
							
							</th>
							<th align="center" style="text-align:center;"><span style="cursor: pointer;"
								class="glyphicon glyphicon-plus" title="Add more holidays" onclick="addMoreHoliday(this.form);"></span></th>
							</tr>
						</table>
					</div>
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
