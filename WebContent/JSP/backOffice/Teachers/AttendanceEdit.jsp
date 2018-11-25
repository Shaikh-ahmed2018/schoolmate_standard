<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript"  src="JS/backOffice/Teacher/AttendanceEdit.js"></script>


</head>

<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="col-md-10"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Student Attendance
				</span>
		</p>
		
			<logic:present name="successMessage" scope="request">

				<div class="successmessagediv" align = "center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
									name="successMessage" scope="request" />
						</span></a>
					</div>
				</div>

			</logic:present>

			<logic:present name="errorMessage" scope="request">

				<div class="successmessagediv" align = "center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
									name="errorMessage" scope="request" />
						</span></a>
					</div>

				</div>

			</logic:present>

			
				<div class="successmessagediv" style="display: none;" align = "center">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>

	
	
		
			<div class="errormessagediv" style="display: none;" align = "center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>

	
		<form action=""
			enctype="multipart/form-data" id="formstudent" method="post" >
			
			
			<!-- chiru -->

			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"
								style="color: #000000; vertical-align: text-top;"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Student Details
							</h3></a>

				<div class="navbar-right">
						
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
							<logic:equal value="STUATTUPD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span id="saveAttendance" class="buttons style" style ="top: 7px;">Save</span>&nbsp;
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
						<span id="back1" class="buttons style" style ="top: 7px;">Back</span>
							
				</div>
					</div>

	<input type="hidden" id="historyacademicId" 
	value='<logic:present name='historyacademicId' scope="request"><bean:write name="historyacademicId"/></logic:present>'/>

   <input type="hidden" id="historylocId" 
	value='<logic:present name='historylocId' scope="request"><bean:write name="historylocId"/></logic:present>'/>
	
	<input type="hidden" id="historyclassname" 
	value='<logic:present name='historyclassname' scope="request"><bean:write name="historyclassname"/></logic:present>'/>

   <input type="hidden" id="historysectionid" 
	value='<logic:present name='historysectionid' scope="request"><bean:write name="historysectionid"/></logic:present>'/>
	
	<input type="hidden" id="historyspecId" 
	value='<logic:present name='historyspecId' scope="request"><bean:write name="historyspecId"/></logic:present>'/>

   <input type="hidden" id="historystartdate" 
	value='<logic:present name='historystartdate' scope="request"><bean:write name="historystartdate"/></logic:present>'/>
	
	<input type="hidden" id="historyenddate" 
	value='<logic:present name='historyenddate' scope="request"><bean:write name="historyenddate"/></logic:present>'/>

				<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						
						<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top: 1%;" id="txtstyle">
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Branch</label>
											<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" disabled="disabled" required>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
							  </div>
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;"
										id="inputnames">Class</label>
									<div class="col-xs-7">
										<select id="classId" name="classname" class="form-control" onChange="HideError()" disabled="disabled"
											required>
											<logic:present name="stuattEdit" scope="request">
											<option value="<bean:write name="stuattEdit"  scope="request" property="classId"/>"><bean:write name="stuattEdit" scope="request" property="classname"/></option>
											</logic:present>
										</select>
									</div>
							    </div>
								
							
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Specialization</label>
									<div class="col-xs-7">
										<select id="specialization" name="specialization" class="form-control" disabled="disabled" onChange="HideError()">
											<logic:present name="stuattEdit" scope="request">
											<option value="<bean:write name="stuattEdit"  scope="request" property="specId"/>"><bean:write name="stuattEdit" scope="request" property="specName"/></option>
											</logic:present>
										</select>
									</div>
								</div>
							
							
							</div>
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top: 1%;" id="txtstyle">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" disabled="disabled" required>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="section" name="section" class="form-control" disabled="disabled" onChange="HideError()">
											<logic:present name="stuattEdit" scope="request">
											<option value="<bean:write name="stuattEdit" scope="request" property="sectionId"/>"><bean:write name="stuattEdit" scope="request" property="sectionname"/></option>
											</logic:present>
										</select>
									</div>
								</div>
								
									<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Attendance Date</label>
									<div class="col-xs-7">
										<input type="text" name="date" readonly  placeholder="Select Date"
											id="date" 
											maxlength="25" class="form-control"
											value='<logic:present name="stuattEdit" scope="request"><bean:write name="stuattEdit" scope="request" property="date"/></logic:present>'/>
										<!-- <img src="./images/calendar-icon.png" width="30" height="30"  style="margin-left: 88%; margin-top: -12%;"> -->
									</div>
								</div>
								
								
								 	
							<!-- 	<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<select id="studentname" name="studentname" class="form-control" onChange="HideError()">
											<option value="">-------------Select------------</option>
										</select>
									</div>
								</div> -->
								<!-- <button type="button" class="col-md-offset-5 buttons style" id="search">Filter</button>
								<button type="reset" class="col-md-offset-1 buttons style" id="reset" style="margin-left :0">Reset</button> -->
							</div>
							
							<input type="hidden" id="attenid" value='<logic:present name="attenid" scope="request"><bean:write  name ="attenid"/></logic:present>'/>
							 <input type="hidden" id="noofperiod"/>
					<input type="hidden" id="hclass" value='<logic:present name='attendetails'><bean:write  name ="attendetails"  property="classId" /></logic:present>'/>
					<input type="hidden" id="hsection" value='<logic:present name='attendetails'><bean:write name ="attendetails" property="sectinId"/></logic:present>'/>
					<input type="hidden" id="hdate" value='<logic:present name='attendetails'><bean:write    name="attendetails"  property="date"/></logic:present>'/>	
					<input type="hidden" id="hlocationId" value='<logic:present name='attendetails'><bean:write    name="attendetails"  property="locationId"/></logic:present>'/>
					<input type="hidden" id="hiddenAcyearid" value='<logic:present name='attendetails'><bean:write name="attendetails" property="accYearId"/></logic:present>'/>
					<input type="hidden" id="hiddenspec" value='<logic:present name='attendetails'><bean:write name="attendetails" property="specID"/></logic:present>'/>
					
					<DIV class="col-md-12" style="padding: 0; overflow-x: scroll;height: 400px;" id="divIdList">	
						<%-- <input type="hidden" id="hclass" value='<logic:present name='classId'><bean:write name="classId"/></logic:present>'/>
						<input type="hidden" id="hsection" value='<logic:present name='section'><bean:write name="section"/></logic:present>'/>
						<input type="hidden" id="hdate" value='<logic:present name='date'><bean:write name="date"/></logic:present>'/>	 --%>
					
					<table id="allstudent" style="width:100%;">
						<thead></thead>
						<tbody ></tbody>
						</table>
					</DIV>
							
						</div>
					</div>
					
				</div>
			</div>
			
		</form>
	</div>
</html>









