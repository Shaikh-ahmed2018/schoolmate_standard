<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>
 <script type="text/javascript" src="JS/backOffice/Settings/holidayList.js"></script>

</head>

<body>

	<div class="content" id="div1">
		<div id="dialog"></div>
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

			<div class="panel-heading clearfix">
				<h3 class="panel-title" style="color: #000000;">Holiday Details</h3> 
				
				<div class="navbar-right">
				<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="HOLCRE" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
											<span id="savebutton" class="btn btn-xs btn-primary margin-t-5" data-toggle="modal" data-target="#myModal">
											<span class="glyphicon glyphicon-plus" ></span>Add New Class</span>				
					
					 					 </logic:equal>
									  </logic:equal>
								  </logic:iterate>
							  </logic:present>
					<!-- : ends-->
					
					<!-- edit & delete-->
					<input type="hidden" id="editPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="HOLUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
						<input type="hidden" id="delPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="HOLDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
					<!--  :ends-->	
				</div>
			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="myModalLabel">Holiday</h3>
						</div>
						<div class="modal-body">
						<div class="panel-body clearfix">
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

	 <input type="hidden" id="butstatus" name="butstatus" />
	
   <input type="hidden" id="currentstatus" name="currentstatus" 
    value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />

  <input type="hidden" id="historylocId" 
	  value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId" /></logic:present>'></input>
	
  <input type="hidden" id="historyacyearId" 
	  value='<logic:present name="historyacyearId" scope="request"><bean:write name="historyacyearId" /></logic:present>'></input>

<input type="hidden" id="historystatus" 
	  value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus" /></logic:present>'></input>

   <div id="collapseOne" class="accordion-body collapse in">
   		<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-bottom: 10px">
          
           <div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
				<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locId" name="locationnid" class="form-control" required>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
				</div>
								
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Status</label>
					<div class="col-xs-7">
						<select id="status" name="status" class="form-control">
							<option value="Y">Active</option>
							<option value="N">InActive</option>
						</select>
					</div>
			 	</div>
			</div>

			<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
				
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Academic Year</label>
							<div class="col-xs-7">
								<select id="Acyearid" name="accyear" class="form-control" required>
									<logic:present name="AccYearList">
											<logic:iterate id="AccYear" name="AccYearList">
												<option
													value="<bean:write name="AccYear" property="accyearId"/>"><bean:write
														name="AccYear" property="accyearname" /></option>
											</logic:iterate>
									</logic:present>
								</select>
							</div>
					</div>
					<input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">			
					 <input type="hidden" id="hiddenaccyear" name="hiddenaccyear" value="<bean:write name='academic_year' scope="request"/>" />
					 <input type="hidden" id="hiddenstatus" name="hiddenstatus" value="<logic:present name='status' scope='request' ><bean:write name='status'/></logic:present>" />
				</div>

					<logic:present name="HolidayList" scope="request">
						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th><input type='checkbox' name='selectall' id='selectall'
										onClick='selectAll()' /></th>
									<th>Branch</th>
									<th>Holiday Date</th>
									<th>Holiday Name</th>
									<th>Holiday Type</th>
									<th>Remarks</th>
								</tr>
							</thead>
							<tbody>
								
							</tbody>
						</table>

						<div class='pagebanner'>
							<select id='show_per_page'><option value='50'>50</option>
								<option value='100'>100</option>
								<option value='200'>200</option>
								<option value='300'>300</option>
								<option value='400'>400</option>
								<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
						<div class='pagination pagelinks'></div>
					</logic:present>
				</div>
				</div>
				</div>
			</div>
</body>
</html>