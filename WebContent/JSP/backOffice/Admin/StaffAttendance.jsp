<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/backOffice/Admin/StaffAttendance.js"></script>



<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
.download:hover{

cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
.sa{
    font-family: Roboto,sans-serif;
    font-size: 15px !important;
}
</style>
</head>

<body>
<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
	<div class="content" id="div1">
		<div class="input-group col-md-12" id="div2">

			<p class="transportheader">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Staff Attendance</span>
			</p>
		</div>

		<center>
			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>

			<div class="errormessagediv1" style="display: none;">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>
		</center>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title sa" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Staff Attendance Details
					</h3></a>
				<div class="navbar-right">
				
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="STFFATTADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
								 <span id="add" class="buttons">New</span> 
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
					
				<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="STFADWD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
								<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
				</div>
				
			</div>
	<input type="hidden" id="historylocId" 
		value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>" />
				
		<input type="hidden" id="historyacademicId" 
		value="<logic:present name="historyacademicId" scope="request"><bean:write name="historyacademicId"/></logic:present>" />
		
		<input type="hidden" id="historystartdate" 
		value="<logic:present name="historystartdate" scope="request"><bean:write name="historystartdate"/></logic:present>" />				
		
		<input type="hidden" id="historyenddate" 
		value="<logic:present name="historyenddate" scope="request"><bean:write name="historyenddate"/></logic:present>" />		
	
	<input type="hidden" id="historyback" 
		value="<logic:present name="historyback" scope="request"><bean:write name="historyback"/></logic:present>" />
				
			<!-- pop up -->
<input type="hidden" name="tatt" class="tatt" id="tatt" value="<logic:present name = 'tatt' scope = 'request'><bean:write name = 'tatt'></bean:write></logic:present>"></input>
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h3 class="modal-title" id="myModalLabel">Download</h3>
						</div>
						<div class="modal-body">
							<span id="excelDownload"><img src="images/xl.png"
								class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

					<%-- <display:table name="requestScope.attendancelist"
						requestURI="menuslist.html?method=getStaffAttendance"
						export="false" class="table" id="allstudent">


						<display:column property="count" sortable="true"
							title="Sno<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="date" sortable="true"
							title="Attendance Date<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="tot_count" sortable="true"
							title="Total Strength<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="present_count" sortable="true"
							title="Total Present<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="absent_count" sortable="true"
							title="Total Absent<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="holiday_count" sortable="true"
							title="Total Holiday<img src='images/sort1.png' style='float: right'/>" />
						<display:column property="leave_count" sortable="true"
							title="Total Leave<img src='images/sort1.png' style='float: right'/>" />
					
					</display:table> --%>
					
					<div class="col-md-6" id="txtstyle"
								style="font-size: 13px; color: #000;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
									
										<select id="dlocation" name="dlocation" class="form-control" required style="cursor: default;">
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								<div class="form-group clearfix">
									
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Start Date
								</label>		
								
									<div class="col-xs-7">
									<input type="text" name="year" id="startdate" maxlength="25" class="form-control"  readonly="readonly" placeholder="Click here...">
								</div>
								
								</div>
								</div>
					<div class="col-md-6" id="txtstyle"
								style="font-size: 13px; color: #000;">
					<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Academic Year</label>
							<div class="col-xs-7">
								<select id="accyearp" name="accyearp" class="form-control" style="cursor: default;"
									required>
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
						<input type="hidden" id="haccYear" value='<logic:present name = 'currentAccYear' scope = 'request'><bean:write name = 'currentAccYear'></bean:write></logic:present>'>
								<div class="form-group clearfix">
									
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">End Date
								</label>		
								
									<div class="col-xs-7">
									<input type="text" name="year" placeholder="Click here..."
							id="enddate" maxlength="25" class="form-control"  readonly="readonly">
								</div>
								
								</div>
								</div>
					
					<logic:present name="attendancelist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>S.No</th>
							<th>Attendance Date</th>
							<th>Total Strength</th>
							<th>Total Present</th>
							<th>Total Absent</th>
							<th>Total Holiday</th>
							<th>Total Leave</th>
							</tr>
							</thead>
							<tbody>
							<%-- <logic:iterate id="attendancelist" name="attendancelist">
								<tr>
								<td><bean:write name='attendancelist' property='count'/></td>
								<td><bean:write name="attendancelist" property='date'/></td>
								<td><bean:write name="attendancelist" property='tot_count'/></td>
								<td><bean:write name="attendancelist" property='present_count'/></td>
								<td><bean:write name="attendancelist" property='absent_count'/></td>
								<td><bean:write name="attendancelist" property='holiday_count'/></td>
								<td><bean:write name="attendancelist" property='leave_count'/></td>
								</tr>
							</logic:iterate> --%>
							</tbody>
						</table>
					</logic:present>
					
					
					
 <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
   	<span  class='numberOfItem'></span>	
   	</div><div class='pagination pagelinks'></div>
				</div>
				<br />
			</div>
		</div>
	</div>


</body>
</html>