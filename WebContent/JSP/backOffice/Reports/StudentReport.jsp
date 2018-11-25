<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">
<head>
<title>eCampus Pro</title>
<script src="JS/backOffice/Reports/StudentReport.js"></script>
<style>
.house tr th{
	text-align: center;
}

.modal-body {
	text-align: center;
}
</style>

</head>
<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="col-md-10">
		<p>
			<img src="images/addstu.png" style="vertical-align: top;"/>&nbsp;<span id="pageHeading">All Student </span>
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
					class="successmessage"></span></a>
			</div>
		</div>

		<p id="parent1" style="display: none;">
			<a href="">Expand all</a>
		</p>
		<div class=" -group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">


					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #000000"><h3 class="panel-title" id="beforeparent">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Student
						</h3></a>


					<div class="navbar-right">
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
							<logic:equal value="STUREPDWD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
                                	<span class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
					</div>

				</div>


				<!-- pop up -->

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

				<!-- Filters -->

				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body" id="tabletxt" style="padding: 15px;">
						<div class="tab-row" style="margin-bottom: 125px;">
							<div class="col-md-6" id="txtstyle">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="location" name="location" class="form-control"
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
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()"
											name="classname" id="class">
											<option value="all">----------Select----------</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Report
										Type</label>
									<div class="col-xs-7">
										<select id="selection" name="selection" class="form-control"
											required>
											<option value="all">----------Select----------</option>
											<option value="newadmlist">New Admission List</option>
											<option value="newtempadmlist">New Temp. Admission List</option>
											<option value="prmlist">Promotion List</option>
											<option value="studept">Specialization List</option>
											<option value="stustanwise">Student Class Wise</option>
											<option value="stuconde">Student Contact Details</option>
											<option value="stulisthousewise">Student House List</option>
											<option value="stulist">Student List</option>
											<option value="stulistadmission">Student List - Admission No. Wise</option>
											<option value="stulistalpha">Student List - Alphabetic Wise</option>
									        <option value="stulcatwise">Student List - Category Wise</option>
									        <option value="stulistgenwise">Student List - Gender Wise</option>
									        <option value="sturelig">Student List - Religion Wise</option>	
											<option value="stulistrollwise">Student List - Roll No. Wise</option>
											<option value="stuparlist">Student Parent List</option>
											<option value="studob">Students With DOB</option>
											<option value="stufatheroccu">Students With Father's Occupation</option>
											<option value="stumotheroccu">Students With Mother's Occupation</option>
											<option value="stuphonnum">Students With Phone Numbers</option>
											
								                  <logic:present name="UserDetails" scope="session">
								                  <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
								                    	<logic:equal value="RFBRDIS" name="daymap" property="key">
										                <logic:equal value="true" name="daymap" property="value">
                                                      <option value="busroutewise">Report For Bus in Route wise</option>
										              </logic:equal>
									               </logic:equal>
								                </logic:iterate>
							                  </logic:present>
											
											
											<!-- <option value="stuoptsub">Student Optional Subject
												Details</option> -->
												
											<!-- <option value="oldstulist">Old Students List</option> -->
											<!-- <option value="stustrngth">Students Strength</option> -->

										</select>
									</div>
								</div>
								
								
								<div class="col-xs-5">
								</div>
									<div class="col-xs-7">
									<span class="buttons" id="search" style="margin-bottom: 10px;">Search</span>
									<span class="buttons reset"  style="margin-bottom: 10px;">Reset</span>
									</div>
									
								
								
								<!-- <div class="col-xs-10">
									<button type="button" class="btn btn-info " id="search" style="margin-bottom: 10px;">Search</button>
								</div> -->
								
							</div>

							<div class="col-md-6" id="txtstyle">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right;">Academic
										Year</label>
									<div class="col-xs-7">
										<select id="accyear" name="accyear" class="form-control"
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

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right;">Division</label>
									<div class="col-xs-7">
										<select id="section" name="section" class="form-control"
											required>
											<option value="all">----------Select----------</option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix stustatus">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right;">Status</label>
									<div class="col-xs-7">
										<select id="status" name="status" class="form-control" required>
											<option value="active">Active</option>
											<option value="inactive">InActive</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" style="left:59px;"
										id="genderName">Gender Type</label>
									<div class="col-xs-7">
										<select id="gender" name="gender" class="form-control"
											required>
											<option value="all">----------Select---------</option>
											<option value="Male">Boys</option>
											<option value="Female">Girls</option>
										</select>
									</div>
								</div>
								<div class="col-xs-4"></div>
							</div>
						</div>
                 <input type="hidden" id="hiddenlocId" value="<logic:present name="current_loc"><bean:write name="current_loc"/></logic:present>" />
                                    
						<div class="col-md-12 selecteditems">
							<br /> <input type="hidden" id="haccyear"
								value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="accyearId"/></logic:present>" />
							<input type="hidden" id="hstream"
								value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="streamId"/></logic:present>" />
							<input type="hidden" id="hclass"
								value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="classId"/></logic:present>" />
							<input type="hidden" id="hsection"
								value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="sectionId"/></logic:present>" />
							<input type="hidden" id="hlocation"
								value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="locationId"/></logic:present>" />

							<span><b>Location :</b></span>&nbsp;&nbsp;&nbsp;<span> <logic:present
									name="CurrentForm">
									<bean:write name="CurrentForm" property="locationName" />
								</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Academic
									Year :</b></span>&nbsp;&nbsp;&nbsp;<span> <logic:present
									name="CurrentForm">
									<bean:write name="CurrentForm" property="accyearname" />
								</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Class
									:</b></span>&nbsp;&nbsp;&nbsp;<span> <logic:present
									name="CurrentForm">
									<bean:write name="CurrentForm" property="classname" />
								</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Section
									:</b></span>&nbsp;&nbsp;&nbsp;<span> <logic:present
									name="CurrentForm">
									<bean:write name="CurrentForm" property="sectionname" />
								</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />

						</div>

						<div id="studentlisttable"></div>
						<div class='pagebar' style="display: none;">
				         <div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	
				          	</div>
					<div class='pagination pagelinks'></div> 
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Button trigger modal -->
	<span>&nbsp;</span>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>

</html>
