<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <script type="text/javascript" src="JS/backOffice/Settings/updateHoliday.js"></script>

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
<style>
.glyphicon:hover{

cursor: pointer;
}
#classSave:hover {
	cursor: pointer;
}

</style>

<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
<div>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Modify Holiday</span>
		</p>


                  <logic:present name="successmessagediv" scope="request">
						<div class="successmessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span class="validateTips"><bean:write
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
							 <span id="back1" class="buttons">Back</a></span>
									
							</div>
						
					</div>
					<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>
		
		 <input type="hidden" id="startdate" 
	  value='<logic:present name="startdate" scope="request"><bean:write name="startdate" /></logic:present>'></input>
		 <input type="hidden" id="enddate" 
	  value='<logic:present name="enddate" scope="request"><bean:write name="enddate" /></logic:present>'></input>
					
					 
  <input type="hidden" id="historylocId" 
	  value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId" /></logic:present>'></input>
	
  <input type="hidden" id="historyacyearId" 
	  value='<logic:present name="historyacyearId" scope="request"><bean:write name="historyacyearId" /></logic:present>'></input>

<input type="hidden" id="historystatus" 
	  value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus" /></logic:present>'></input>
					 <input type="hidden" name="hiddenlocation" id="hiddenlocation"   class="form-control" 
										 value='<logic:present name="holidayDetails" scope="request"  ><bean:write name="holidayDetails" property="locId" ></bean:write></logic:present>' />
									
					
					 <input type="hidden" name="hiddenDate" id="hiddenDate"   class="form-control" 
										 value='<logic:present name="holidayDetails" scope="request"><bean:write name="holidayDetails" property="date" ></bean:write></logic:present>' />
					<input type="hidden" name="hiddenholidayType" id="hiddenholidayType" class="form-control"  value="<logic:present name="holidayDetails" scope="request"><bean:write name="holidayDetails" property="holidayType" ></bean:write></logic:present>" />			
					<input type="hidden" name="hiddenyear" id="hiddenyear" class="form-control"  value="<logic:present name="holidayDetails" scope="request"><bean:write name="holidayDetails" property="year" ></bean:write></logic:present>" />		
					<input type="hidden" name="hiddenweekday" id="hiddenweekday" class="form-control"  value="<logic:present name="holidayDetails" scope="request"><bean:write name="holidayDetails" property="weekDay" ></bean:write></logic:present>" />
					<input type="hidden" name="hiddeholidayName" id="hiddeholidayName" class="form-control"  value="<logic:present name="holidayDetails" scope="request"><bean:write name="holidayDetails" property="holidaysName" ></bean:write></logic:present>" />
					<input type="hidden" name="hiddenholidayid" id="hiddenholidayid" class="form-control"  value="<logic:present name="holidayDetails" scope="request"><bean:write name="holidayDetails" property="holId" ></bean:write></logic:present>" />                      
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
								<div class="col-md-6" id="txtstyle"
									style="font-size: 13px; color: #000;">
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Branch</label>
										<div class="col-xs-7">
											<select id="locationname" name="location"
												class="form-control">
												
												
												<logic:present name="locationList">

													<logic:iterate id="Location" name="locationList">

														<option
															value="<bean:write name="Location" property="locationId"/>">
															<bean:write name="Location" property="locationName" />
														</option>

													</logic:iterate>


												</logic:present>

											</select>


										</div>
									</div>
									

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Date</label>
										<div class="col-xs-7">
											<input type="text" name="date" id="date" readonly
												class="form-control date"
												value='<logic:present name="holidayDetails" scope="request"><bean:write name="holidayDetails" property="date" ></bean:write></logic:present>' />
										</div>
									</div>

							
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Holiday
											Type</label>
										<div class="col-xs-7">
											<select name="holidaytype" id ="holidaytype" class="form-control"
												style="min-width: 150px;">
												<option value="FirstHalf">First Half</option>
												<option value="Second Half">Second Half</option>
												<option value="Full Day">Full Day</option></select>
										</div>
									</div>

								</div>
								<div class="col-md-6" id="txtstyle"
									style="font-size: 13px; color: #000;">
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Academic
											Year</label>
										<div class="col-xs-7">
											<select name="accyYear" id="accyYear" class="form-control"
												onkeypress="HideError()">
												
												<logic:present name="AccYearList">
													<logic:iterate id="AccYear" name="AccYearList">
														<option
															value="<bean:write name="AccYear" property="accyearId"/>">
															<bean:write name="AccYear" property="accyearname" />
														</option>
													</logic:iterate>
												</logic:present>
											</select>

										</div>
									</div>
							
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">WeekDays</label>
										<div class="col-xs-7">
											<input type="text" name="weekDays" id="WeekDays"
												value="${requestScope.WeekDays}" readonly
												class="form-control" />

										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Holiday Name</label>
										<div class="col-xs-7">
											<input type="text" name="holiday" id="holidayname"
												class="form-control"
												value="<logic:present name="holidayDetails" scope="request"><bean:write name="holidayDetails" property="holidaysName" ></bean:write></logic:present>" />
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
