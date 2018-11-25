<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>
<link href="/CSS/newUI/custome.css" rel="stylesheet">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Student/Studentexcelfileupload.js"></script>
</head>
<style>
span#saveid {
    left: -48px;
    position: relative;
    top: 7px;
}
#allstudent th:nth-child(1) {
    text-align: center;
    width: 17%;
}
#allstudent th:nth-child(2),td:nth-child(2),th:nth-child(3),td:nth-child(3),th:nth-child(4),td:nth-child(4),th:nth-child(5),td:nth-child(5),th:nth-child(6),td:nth-child(6),th:nth-child(7),td:nth-child(7) {
    text-align: left;
}
</style>

<body>
	<div id="loder" style="display: none; position: absolute; height: 800px; width: 800px; left: 0; right: 0; top: -40px;; bottom: 0; margin: auto; z-index: 99999;">
		<img style="width: 165px; position: absolute; left: 0; right: 0; height: 165px; bottom: 0; top: -190px; margin: auto;" src="./images/ajax-loading.gif" />
	</div>

	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Student Excel upload</span>
		</p>


		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes">
				<span class="validateTips"></span></a>
			</div>
		</div>


		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning">
				<span class="validateTips"></span></a>
			</div>
		</div>


		<logic:present name="errorMessage" scope="request">
			<div class="errormessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning">
					<span><bean:write name="errorMessage" scope="request" /></span></a>
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


		<form action="uploadStudentXSL.html?method=insertStudentXSL" id="excelfileupload" name="UploadStudentXSLForm" method="post"
			enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">

						<a data-toggle="collapse" data-parent="#accordion1"
							href="#collapseOne" style="color: #000000">
							<h3 class="panel-title">
								<i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Upload Students Data From File
							</h3></a>



					<%-- 	<div class="navbar-right">
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="USDFADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<span id="saveid" class="buttons">Upload</Span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
						</div> --%>

					</div>
					<script>
						$(document).ready(function() {
							$('[data-toggle="tooltip"]').tooltip();
						});
					</script>


					<div class="feebody panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" align="center">
							<div class="panel-body">
							 <div class="col-md-6" id="txtstyle" style="padding: 0;">
									<!-- <div class="form-group">
										<div class="col-xs-7">
											<input type="file" name="formFile" id="studentfile" onchange="checkFileSize(this)" class="form-control"
												style="height: auto;margin-left: 2px;"></input>
									   </div> 
								  </div> -->
								  
								  <div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4"
											id="inputnames"></label>
										<div class="col-xs-7">
											<input type="file" name="formFile" id="studentfile" onchange="checkFileSize(this)"
												class="form-control" style="height: auto;"></input>
										</div>
									</div>
						    </div>

                          <div class="col-md-1">
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="USDFADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											  <button id="saveid" class="buttons">Upload</button>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
						 </div>


								<input type="hidden" name="fileSize" id="fileSize" value=""></input>

								<div class="col-md-3" id="txtstyle">
									<a href="uploadStudentXSL.html?method=downloadstudentInstructionfileformat">Download Instructions</a> <br /> 
										<a href="uploadStudentXSL.html?method=downloadfileformat">Download Template</a>
								</div>



								<div class="panel-body val1"
									style="font-family: Roboto, sans-serif; font-size: 13px; color: #000; overflow: scroll; height: 300px; width: 100%;">
									<logic:present name="FailEmployeeList" scope="request">
										<p style="color: #ff1010; font-family: Roboto Medium;">
											<font size="4">Following Records are not Uploaded!</font>
										</p>
										<table class="table" id="allstudent">
											<thead>
												<tr>
													<th>AdmissionNo</th>
													<th>Name</th>
													<th>Admission Date</th>
													<th>Academic Year</th>
													<th>Stream</th>
													<th>Class</th>
													<th>Section</th>
													<th>Reason</th>
												</tr>
											</thead>
											<tbody>
												<logic:iterate name='FailEmployeeList' id="FailEmployeeList">
													<tr>
														<td><bean:write name='FailEmployeeList' property="studentAdmissionNo" /></td>
														<td><bean:write name='FailEmployeeList' property="studentFirstName" /></td>
														<td><bean:write name='FailEmployeeList' property="dateofJoin" /></td>
														<td><bean:write name='FailEmployeeList' property="academicYear" /></td>
														<td><bean:write name='FailEmployeeList' property="category" /></td>
														<td><bean:write name='FailEmployeeList' property="classname" /></td>
														<td><bean:write name='FailEmployeeList' property="sectionname" /></td>
														<td><bean:write name='FailEmployeeList' property="reason" /></td>
													</tr>
												</logic:iterate>
											</tbody>
										</table>

									</logic:present>
								</div>
								<!-- <div class='pagebanner val1'
									style="margin-top: 5px; margin-left: -47%;">
									<select id='show_per_page'><option value='50'>50</option>
										<option value='100'>100</option>
										<option value='200'>200</option>
										<option value='300'>300</option>
										<option value='400'>400</option>
										<option value='500'>500</option></select> <span class='numberOfItem'></span>
								</div>
								<div class='pagination pagelinks val1' style="margin-top: 4px;"></div> -->

                                  <div class="col-md-12 val1" style="top:12px;">
										<div class='pagebanner' style="left:-2px;"><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
											<span  class='numberOfItem'></span>	
										</div>
										<div class='pagination pagelinks' style="right:-11px;"></div>
									</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
</body>

</html>
