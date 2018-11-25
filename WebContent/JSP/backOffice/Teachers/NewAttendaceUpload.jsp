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

<script type="text/javascript" src="JS/backOffice/Teacher/NewAttendaceUpload.js"></script>

</head>

<body>

<input type="hidden" id="hiddenlocId" value="<logic:present name="hloc"><bean:write name="hloc" /></logic:present>">


<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="col-md-10" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		
		<p>
			<img src="images/addstu.png"  style="vertical-align: top;"/>&nbsp;<span id="pageHeading">New Student Attendance
				</span>
		</p>
	
			<logic:present name="successMessage" scope="request">

				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
									name="successMessage" scope="request" />
						</span></a>
					</div>
				</div>

			</logic:present>

			<logic:present name="errorMessage" scope="request">

				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
									name="errorMessage" scope="request" />
						</span></a>
					</div>

				</div>

			</logic:present>

			
				<div class="successmessagediv" align="center" style="display: none;">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>

	

		
			<div class="errormessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>

		
		<form action=""
			enctype="multipart/form-data" id="formstudent" method="post" >
			
			

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary clearfix">
			        <div class="panel-heading clearfix">
						
					<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #000000; vertical-align: middle;">
						<h3 class="panel-title" style="color: #000000;">
							<i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Student Details</h3></a>

						<div class="navbar-right">
							
							<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
							<logic:equal value="STUATTADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span id="saveAttendance" class="buttons style" style ="top: 7px;">Save</span>&nbsp;
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
							<span id="back" class="buttons style" style ="top: 7px;" >Back</span>
						</div>

					</div>

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Branch</label>
											<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<!-- <option value="all">-------------Select------------</option> -->
											<!-- <option value="all" selected>ALL</option> -->
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
							</div>
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;"	id="inputnames">Class</label>
									<div class="col-xs-7">
										<select id="classId" name="classname" class="form-control" onChange="HideError()" required>
											<option value="all">-------------Select------------</option>
										</select>
									</div>
							</div>
				
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Specialization</label>
									<div class="col-xs-7">
										<select id="specialization" name="specialization" class="form-control" onChange="HideError()">
											<option value="">-------------Select------------</option>
										</select>
									</div>
								</div>
								
								
							
							</div>
							<div class="col-md-6" id="txtstyle">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
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
										<select id="section" name="section" class="form-control" onChange="HideError()">
											<option value="all">-------------Select------------</option>
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
											value=''/>
										<!-- <img src="./images/calendar-icon.png" width="30" height="30"  style="margin-left: 88%; margin-top: -12%;"> -->
									</div>
								</div>
								
							
								
								<!-- <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Teacher</label>
									<div class="col-xs-7">
										<input type="text" name="teacher"  
											 id="teacher" readonly onChange="HideError()"
											maxlength="25" class="form-control"
											value=''/>
									</div>
									<input type="hidden" id="teacherid"></input>
								</div> -->
								
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
							 <input type="hidden" id="noofperiod"/>
					<div id="collapseOne" class="accordion-body collapse in" >
					<div class="col-md-12" style="padding: 0; overflow-x: scroll;display: none;height: 400px;" id="divIdList" >			
						<table id="allstudent" class='table'>
						<thead></thead>
						<tbody ></tbody>
						</table>
				
					</div>
						</div>
					</div>
					
				</div>
			</div>
			</div>
		</form>
	</div>
	</body>
</html>
