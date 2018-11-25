<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
	
 <script type="text/javascript" src="JS/backOffice/Reports/StaffAttendanceReport.js"></script>
<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">
</head>

<body>
<!-- <form id="staffattendanceid" action="staffattendancereport.html?method=getStaffAttendanceAction" method="post" enctype="multipart/form-data"> -->
	<div class="col-md-10">
		<p>
			<img src="images/addstu.png" style="vertical-align: top;"/>&nbsp;<span id="pageHeading">Staff Attendance
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
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #000000"><h3 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Staff Attendance
						</h3>	 
						</a>
						
						

						<div class="navbar-right">
						   <logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="STFADWD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal"  data-placement="bottom">Download </span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
					
		</div>
						
						
				
				</div>
				
				
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
				
				
		<input type="hidden" name="userid"	id="userid" value="<logic:present name="parentid" ><bean:write name="parentid"  /></logic:present>"  />			
				
				<input type="hidden" id="haccStartdate" value="<logic:present name="startDate"><bean:write name="startDate" /></logic:present>" />
				<input type="hidden" id="haccEnddate" value="<logic:present name="enddate"><bean:write name="enddate" /></logic:present>" />
				
				
				
				
				
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					
					<div class="panel-body" id="tabletxt" style="margin-top: 19px;">

						<div class="col-md-6" id="txtstyle">
							
							<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-4"
							style="text-align: right; line-height: 35px;">Branch</label>
						<div class="col-xs-7">
							<select id="locationname" name="locationnid" class="form-control"
								required>
								
								<logic:present name="locationList">
									<logic:iterate id="Location" name="locationList">
										<option
											value="<bean:write name="Location" property="locationId"/>"><bean:write
												name="Location" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
							</select>
						</div>
					</div>
							<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Teacher Type
								</label>
								<div class="col-xs-7">
									<select name="teachertype" id="teachertype" class="form-control">
										<option value="">----------Select----------</option>
										<option value="teaching">Teaching</option>
										<option value="nonteaching">Non Teaching</option>
									</select>
								</div>
							</div>	
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">From Date
								</label>
								<div class="col-xs-7">
									<input type="text" id="Fromdate" placeholder="Click Here."
										readonly="readonly"
										class="form-control" value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="fromdate"/></logic:present>"></input>
								</div>
							</div>
							<div class="form-group clearfix">
							<div class="col-xs-4">
							</div>
							<div class="col-xs-7">
									<span class="buttons" id="search" >Search</span>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="txtstyle">
						
						<div class="form-group clearfix">
									<!-- <label for="inputPassword" class="control-label col-xs-4"
										id="inputnames">Academic Year</label> -->
										
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Academic Year
								</label>		
									<div class="col-xs-7">
										<select id="accyear" name="accyear" class="form-control"
											required>
											<option value="">----------Select----------</option>

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
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Teacher Name
								</label>
									<div class="col-xs-7">
										<select id="teachername" name="teachername" class="form-control"
											required>
											<option value="">----------Select----------</option>

										
										</select>
									</div>
								</div>	
                    <div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">To Date</label>
								<div class="col-xs-7">
									<input type="text"  id="todate" placeholder="Click Here."
										readonly="readonly" 
										class="form-control"
										 value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="todate"/></logic:present>">
								</div>
							</div>
							<div class="col-xs-4"></div>
							<br />
	               <input type="hidden" name="defaultprofile"	id="hiddenprofile" value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="fileupload" /></logic:present>"  />							
							<input type="hidden" id="hiddenrequestto"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="requesttoid" /></logic:present>'>
								<input type="hidden" id="hiddenleavetype"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="leavetype"/></logic:present>'>
							<input type="hidden" id="hiddensno" name="sno"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="sno" /></logic:present>'>	
							<input type="hidden" id="hiddenstartsession"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="starttime" /></logic:present>'>		
							<input type="hidden" id="hiddenendsession" 
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="endtime" /></logic:present>'>		
							<input type="hidden" id="hiddenstudent" name="studentFname"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="studentFname" /></logic:present>'>		
					<input type="hidden" name="userhidden" class="userhiddenclass" id="userhiddenid" 
							value='<logic:present name="parentid"><bean:write name="parentid" />
													</logic:present>'></input>
					<input type="hidden" id="successid" 
							value='<logic:present name="success"><bean:write name="success" />
													</logic:present>'></input>		
						</div>
							<!-- data-toggle="modal" data-target="#myModal" -->
							<br />			
							<%-- <div class="col-md-12 selecteditems">
								<br /> 
								
								<input type="hidden" id="haccyear"
									value="<logic:present name="accyearname"><bean:write name="accyearname" property="accyearId"/></logic:present>" />
									
								<input type="hidden" id="hteachertype"
									value="<logic:present name="selecteddetails"><bean:write name="selecteddetails" property="teachertype"/></logic:present>" />
									
								<input type="hidden" id="hfromdate"
									value="<logic:present name="selecteddetails"><bean:write name="selecteddetails" property="fromdate"/></logic:present>" />
									
								<input type="hidden" id="htodate"
									value="<logic:present name="selecteddetails"><bean:write name="selecteddetails" property="todate"/></logic:present>" />


		
								<input type="hidden" id="hteachername"
									value="<logic:present name="selecteddetails"><bean:write name="selecteddetails" property="teachername"/></logic:present>" />



								<span><b>Academic Year  :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="accyearname">
										<bean:write name="accyearname" property="accyearname" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Teacher Type :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selecteddetails">
										<bean:write name="selecteddetails" property="teachertype" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>From Date:</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selecteddetails">
										<bean:write name="selecteddetails" property="fromdate" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>To Date:</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selecteddetails">
										<bean:write name="selecteddetails" property="todate" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									
									
									
									 <span><b>Teacher Name:</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selectedteacher">
										<bean:write name="selectedteacher" property="teacherName" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									
									
									
									
									<br />
							</div> --%>
							<br />	
						<br />	
						
				<%-- 		<div id="allstudent">
								<logic:present name="staffattendanceList">
                      				<display:table class="table" id="allstudent"
									name="requestScope.staffattendanceList"
									requestURI="/staffattendancereport.html?method=getStaffAttendanceAction?">

									<display:column property="count" sortable="true"
										title="S.No	<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="teacherName" sortable="true"
										title="Teacher Name	<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="date" sortable="true"
										title="Attendance Date <img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="status" sortable="true"
										title="Attendance Status <img src='images/sort1.png' style='float: right'/>"
										
										></display:column>

									
								</display:table>
                                <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
                                	<span  class='numberOfItem'></span>	
                                	</div><div class='pagination pagelinks'></div>
							</logic:present>
						</div> --%>
						
						
						
						<table class="table" id="allstudent" style='display:none;'>
							<thead>
							<tr>
							<th>S.No</th>
							<th>Teacher Name</th>
							<th>Attendance Date</th>
							<th>Attendance Status</th>
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
								<div class='pagination pagelinks' style="margin-bottom: 10px"></div>
					</div>
					 
			
				</div>

			</div>
		</div>
	</div>
	<!-- </form> -->
</body>

</html>
