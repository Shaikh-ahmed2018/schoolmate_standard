<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Exam/ExcelUploadStudentMarks.js"></script>
<link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css">

<style>

.panel-group {
   margin-bottom: 0px; 
}
</style>


</head>
<body>

<div id="dialog" style="display: none;">

</div>
	 <div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Upload Student Marks</span>
		</p>

		
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<logic:present name="errorMessage" scope="request">
			<div class="errormessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="errorMessage" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<form action="uploadStudentXSL.html?method=uploadStudentExamDetails_byExcel"
			id="excelfileupload" name="SettingExcelUpload" method="post" enctype="multipart/form-data">
			<input type="hidden" name="inserted" id="inserted1" value='<logic:present name="insertion" scope="request"><bean:write name="insertion" /></logic:present>' />
			
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading togglediv" role="tab" id="headingOne">
					<a  href="#collapseOne" style="color: #767676">
							<h4 class="panel-title examdetails"><i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Download Class-Division Wise Marks Excel Upload Format
						</h4></a>
						
						<div class="navbar-right">
						 	<span id="downloadid" class="buttons collapseOne">Download</Span> 
						</div>
					</div>
				
				<div class="feebody panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo" align="center">
						<div class="panel-body">
							<div class="tab-row" >
								<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Branch<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="location" name="location" class="form-control" required>
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
											style="text-align: right; line-height: 35px;"> Class<font color="red">*</font></label>
										<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" name="classname" id="class">
												<option value="all">----------Select----------</option>
										</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Exam Name<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="examname" name="examname" class="form-control">
												<option value="">----------Select----------</option>
											</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Exam Start Date</label>
										<div class="col-xs-7">
											<input type="text" id="examstartdate" class="form-control" readonly="readonly"/>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Exam Code</label>
										<div class="col-xs-7">
											<input type="text" id="examcode" class="form-control" readonly="readonly"/>
										</div>
									</div>
									
								</div>

								<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4"
											id="inputnames" style="text-align: right;padding: 0px;">Academic Year<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="accyear" name="accyear" class="form-control" required>
												<logic:present name="AccYearList">
													<logic:iterate id="AccYear" name="AccYearList">
														<option value="<bean:write name="AccYear" property="accyearId"/>">
															<bean:write name="AccYear" property="accyearname" />
														</option>
													</logic:iterate>
												</logic:present>
											</select>
										</div>
									</div>
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4" id="inputnames" style="text-align: right;padding: 0px;">Division<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="section" name="section" class="form-control" required>
												<option value="all">----------Select----------</option>
											</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4"
											style="text-align: right; padding: 0px;">Subject Name<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="subjectid" name="subjectid" class="form-control">
												<option value="">----------Select----------</option>
											</select>
										</div>
									</div>
									
				 					<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4" id="inputnames" style="text-align: right;padding: 0px;">Exam End Date</label>
										<div class="col-xs-7">
											<input type="text" id="examenddate" class="form-control" readonly="readonly"/>
										</div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
			
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading togglediv" role="tab" id="headingTwo">
							<a  href="#collapseTwo" style="color: #767676">
								<h4 class="panel-title examdetails"><i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Upload Student Marks
							</h4></a>
						<div class="navbar-right">
							<logic:present name="OverRideVisibility" scope="request">
							<!-- <span id="override" class="buttons collapseTwo">Override</Span> --> 
							</logic:present>
							
				     <!-- <span id="saveid" class="buttons collapseTwo">Upload</Span> -->
						 	
						</div>
					</div>
					<div class="feebody panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						<div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" align="center">
							<div class="panel-body">
								<div class="col-md-6" id="txtstyle" style="padding: 0;">
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4"
											id="inputnames"></label>
										<div class="col-xs-7">
											<input type="file" name="formFile" id="exam"
												class="form-control" style="height: auto;"></input>
										</div>
									</div>
								</div>
								
								<div class="col-md-1" id="txtstyle">
										 <logic:present name="UserDetails" scope="session">
										   <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
											 <logic:equal value="UPLSTRADD" name="daymap" property="key">
												<logic:equal value="true" name="daymap" property="value">
									                <button id="saveid" class="buttons">Upload</button>
									            </logic:equal>
											</logic:equal>
										  </logic:iterate>
									   </logic:present>
								</div>
								
							   <div class="col-md-3" id="txtstyle" style="padding-top: 10px;">
									<a href="settingXLUpload.html?method=stuMarkInstruction">Download Instructions</a>
								</div>

								
							 	<logic:present name="FailedExamUploadList" scope="request">
									<div class="col-md-12" style="padding: 0px; height: 250px; width:100%; overflow-x: auto;overflow-y: visisble;    overflow: scroll;">
										<p class="theading">
											<span class="displayno"></span>
										</p>
										<p style="color: #ff1010;font-family: Roboto Medium;">
											<font size="4">Following Records are not Uploaded!</font>
										</p>



						<table class="table stumarks" id="allstudent">
							<thead>
								<tr>
									<!-- <th><input type='checkbox'  id='selectall'
										style='text-align: center' /></th> --> <!-- onClick='selectAll()'  -->
										<th>S.no</th>
									<th>Reason</th>
									<th>Student Name</th>
									<th>Student Id Number</th>
									<th>Branch</th>
									<th>Academic Year</th>
									<th>Stream </th>
									<th>Class </th>
									<th>Section</th>
									<th>Exam Type</th>
									<th>Exam Name</th>
									<th>Exam Code</th>
									
								
									<th>Start Date</th>
									<th>End Date</th>
									<th>Subject Name</th>
									<th>Attendance</th>
									
									<th>Test Max. Marks</th>
									<th>Test Score Marks</th>
									<th>Notebook Max. Marks</th>
									<th>Notebook Score Marks</th>
									<th>Subject Enrichment Max. Marks</th>
									<th>Subject Enrichment scored. Marks</th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name='FailedExamUploadList'
									id="FailedExamUploadList">
									<tr>
									<%-- <td><input type='checkbox'  class='selectOverride'
											style='text-align: center'
											value='<bean:write name='FailedExamUploadList' property="dataForOverRide"/>' />
										</td> --%>
										
										<td class="sno"></td>
										<td><bean:write name='FailedExamUploadList' property="reason" /></td>
										<td><bean:write name='FailedExamUploadList' property="studentName" /></td>
										<td><bean:write name='FailedExamUploadList' property="studentIdNo" /></td>
										<td><bean:write name='FailedExamUploadList' property="schoolName" /></td>
										<td><bean:write name='FailedExamUploadList' property="academicYear" /></td>
										<td><bean:write name='FailedExamUploadList' property="streamName" /></td>
										<td><bean:write name='FailedExamUploadList' property="className" /></td>
										<td><bean:write name='FailedExamUploadList' property="sectionName" /></td>
										<td><bean:write name='FailedExamUploadList' property="examType" /></td>
										<%-- <td><bean:write name='FailedExamUploadList' property="examType" /></td> --%>
										<td><bean:write name='FailedExamUploadList' property="examName" /></td>
										<td><bean:write name='FailedExamUploadList' property="examCode" /></td>
										<td><bean:write name='FailedExamUploadList' property="startdate" /></td>
										<td><bean:write name='FailedExamUploadList' property="enddate" /></td>
										<td><bean:write name='FailedExamUploadList' property="subjectName" /></td>
										<td><bean:write name='FailedExamUploadList' property="attandance" /></td>
										<td><bean:write name='FailedExamUploadList' property="testMaximumMarks" /></td>
										<td><bean:write name='FailedExamUploadList' property="testScoredMarks" /></td>
										<td><bean:write name='FailedExamUploadList' property="notebookMaximumMarks" /></td>
										<td><bean:write name='FailedExamUploadList' property="notebookScoredMarks" /></td>
										<td><bean:write name='FailedExamUploadList' property="subjectMaximumMarks" /></td>
										<td><bean:write name='FailedExamUploadList' property="subjectScoredMarks" /></td>
									</tr>
								</logic:iterate>
							</tbody>

						</table>
				</div>
					</logic:present>
</div>
</div>
</div>
</div>
								<%-- 		<display:table id="allstudent" name="requestScope.FailedExamUploadList" class="table" requestURI="/uploadStudentXSL.html?method=uploadExcelStudentMarks">
											
											
											
											<display:column property="reason" title="Reason" headerClass="heading1"/>
											<display:column property="academicYear" title="Academic Year" headerClass="heading1" />
											<display:column property="schoolName" title="Branch" headerClass="heading1" />
											<display:column property="studentIdNo" title="Student Id No" headerClass="heading1" />
											<display:column property="className" title="Class" headerClass="heading1" />
											<display:column property="sectionName" title="Section" headerClass="heading1" />
											<display:column property="examName" title="Exam Name" headerClass="heading1" />
											<display:column property="examCode" title="Exam Code" headerClass="heading1" />
											<display:column property="subjectName" title="Subject Name" headerClass="heading1" />
										</display:table> --%>
   
									<!-- </div> -->
					<%-- 				<div class="paginationbox">
										<p class="paginationp"></p>
									</div>
							//	</logic:present>  --%>
						
			
			</div>
			
			
			
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading togglediv" role="tab" id="headingThree">
						<a  href="#collapseThree" style="color: #767676">
							<h4 class="panel-title examdetails"><i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Download Class-Division Wise Co-Scholastic Areas Excel Upload Format
						</h4></a>
						
						<div class="navbar-right">
						 	<span id="downloadid1" class="buttons collapseThree">Download</Span> 
						</div>
					</div>
				
				<div class="feebody panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<div id="collapseThree" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingTwo" align="center">
						<div class="panel-body">
							<div class="tab-row" >
								<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Branch<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="location1" name="location1" class="form-control" required>
												
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
											style="text-align: right; line-height: 35px;">Class<font color="red">*</font></label>
										<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" name="classname1" id="class1">
												<option value="all">----------Select----------</option>
										</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Exam Name<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="examname1" name="examname1" class="form-control">
												<option value="">----------Select----------</option>
											</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Exam Start Date</label>
										<div class="col-xs-7">
											<input type="text" id="examstartdate1" class="form-control" readonly="readonly"/>
										</div>
									</div>
									
								</div>

								<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4"
											id="inputnames" style="text-align: right;padding: 0px;">Academic Year<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="accyear1" name="accyear1" class="form-control" required>
												<logic:present name="AccYearList">
													<logic:iterate id="AccYear" name="AccYearList">
														<option value="<bean:write name="AccYear" property="accyearId"/>">
															<bean:write name="AccYear" property="accyearname" />
														</option>
													</logic:iterate>
												</logic:present>
											</select>
										</div>
									</div>
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4" id="inputnames" style="text-align: right;padding: 0px;">Division<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="section1" name="section" class="form-control" required>
												<option value="all">----------Select----------</option>
											</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4" id="inputnames" style="text-align: right;padding: 0px;">Exam Code</label>
										<div class="col-xs-7">
											<input type="text" id="examcode1" class="form-control" readonly="readonly"/>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4" id="inputnames" style="text-align: right;padding: 0px;">Exam End Date</label>
										<div class="col-xs-7">
											<input type="text" id="examenddate1" class="form-control" readonly="readonly"/>
										</div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
			</form>
			
			
			
		<form action="settingXLUpload.html?method=ExamGradeSetting"
			id="excelfileuploadGrades" name="SettingExcelUpload" method="post" enctype="multipart/form-data">
		<input type="hidden" name="inserted" id="inserted" value='<logic:present name="insertion" scope="request"><bean:write name="insertion" /></logic:present>' />


			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading togglediv" role="tab" id="headingFour">
					<a  href="#collapseFour" style="color: #767676">
								<h4 class="panel-title examdetails"><i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Upload Class-Division Wise Co-Scholastic Areas Grades
							</h4></a>
						<!-- <div class="navbar-right">
						 	<span id="saveidGrades" class="buttons collapseFour">Upload</Span> 
						</div> -->
					</div>
					<div class="feebody panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						<div id="collapseFour" class="panel-collapse collapse gradepanel" role="tabpanel" aria-labelledby="headingOne" align="center">
							<div class="panel-body gradepanel">
								<div class="col-md-6" id="txtstyle" style="padding: 0;">
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4"
											id="inputnames"></label>
										<div class="col-xs-7">
											<input type="file" name="formFile" id="examGrades"
												class="form-control" style="height: auto;"></input>
										</div>
									</div>
								</div>
								
									<div class="col-md-1" id="txtstyle">
										 <logic:present name="UserDetails" scope="session">
										   <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
											 <logic:equal value="UPLSTRADD" name="daymap" property="key">
												<logic:equal value="true" name="daymap" property="value">
									                <button id="saveidGrades" class="buttons">Upload</button>
									            </logic:equal>
											</logic:equal>
										  </logic:iterate>
									   </logic:present>
								  </div>
								
								
								<div class="col-md-3" id="txtstyle" style="padding-top: 10px;">
									<a href="settingXLUpload.html?method=stuGradeInstruction">Download Instructions</a>
								</div>
<!-- 
								<div cl
								ass="col-md-3" id="txtstyle" style="padding: 10px 0px;">
									<a href="">Download Excel upload Instructions</a>
								</div> -->
								
							 	<logic:present name="FailedGradeList" scope="request">
									<div class="col-md-12" style="padding: 0px; height: 250px; overflow: scroll;">
										<p class="theading">
											<span class="displayno"></span>
										</p>
										<p style="color: #ff1010;font-family: Roboto Medium;">
											<font size="4">Following Records are not Uploaded!</font>
										</p>

										<display:table id="allstudent" name="requestScope.FailedGradeList" class="table grade" requestURI="/uploadStudentXSL.html?method=uploadExcelStudentMarks">
											
											<display:column property="studentIdNo" title="Student Admission No" headerClass="heading1" />
											<display:column property="reason" title="Reason" headerClass="heading1"/>
											<display:column property="examType" title="Exam Type" headerClass="heading1" />
											<display:column property="workEducation" title="Work Education" headerClass="heading1" />
											<display:column property="artEducation" title="Art Education" headerClass="heading1" />
											<display:column property="healthEducation" title="Health & Physical Education" headerClass="heading1" />
											<display:column property="descipline" title="Discipline" headerClass="heading1" />
										</display:table>
									</div>
											<div class="col-md-12" style="top:12px;">
												<div class='pagebanner' style="left:-2px;"><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
													<span  class='numberOfItem'></span>	
												</div>
												<div class='pagination pagelinks' style="right:-11px;"></div>
											</div>
								</logic:present> 
							</div>
						</div>
					</div>
				</div>
			</div> 
			</form>
	</div>
</body>
</html>
