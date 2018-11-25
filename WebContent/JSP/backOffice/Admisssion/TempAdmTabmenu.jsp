<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html>
<head>   
<script type="text/javascript"  src="JS/frontOffice/New_Registration_Of_Users/New_Registration_Of_Users.js"></script>

<link href="CSS/Admin/Admission/AdmissionNew.css" rel="stylesheet"type="text/css">

</head>
<body>
<div class="content" id="div-main">

	<div class="tab">
		<ul class="nav nav-tabs">
		<logic:present name="UserDetails" scope="session">
			<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
				<logic:equal value="ISSDIS" name="daymap" property="key">
					<logic:equal value="true" name="daymap" property="value">
						<li class="active" id="issuedtab"><a data-toggle="tab" href="#issued">Application</a></li>
					 </logic:equal>
				</logic:equal>
			</logic:iterate>
		</logic:present>
		
		<logic:present name="UserDetails" scope="session">
			<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
				<logic:equal value="APPRDIS" name="daymap" property="key">
					<logic:equal value="true" name="daymap" property="value">
					<li id="approvedtab"><a data-toggle="tab" href="#approvedlink">Approved</a></li>
					 </logic:equal>
				</logic:equal>
			</logic:iterate>
		</logic:present>
		
		<logic:present name="UserDetails" scope="session">
			<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
				<logic:equal value="REJEDIS" name="daymap" property="key">
					<logic:equal value="true" name="daymap" property="value">
						<li id="rejectedtab"><a data-toggle="tab" href="#rejected">Rejected</a></li>
					 </logic:equal>
				</logic:equal>
			</logic:iterate>
		</logic:present>
		
		<logic:present name="UserDetails" scope="session">
			<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
				<logic:equal value="CANDIS" name="daymap" property="key">
					<logic:equal value="true" name="daymap" property="value">
						<li id="cancelledtab"><a data-toggle="tab" href="#cancelled">Cancelled</a></li>
					 </logic:equal>
				</logic:equal>
			</logic:iterate>
		</logic:present>
		
		<%-- <logic:present name="UserDetails" scope="session">
			<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
				<logic:equal value="SUBMDIS" name="daymap" property="key">
					<logic:equal value="true" name="daymap" property="value">
					<li id="submittedtab"><a data-toggle="tab" href="#submitted">Submitted</a></li>
					 </logic:equal>
				</logic:equal>
			</logic:iterate>
		</logic:present>--%>
		
			 
		<logic:present name="UserDetails" scope="session">
			<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
				<logic:equal value="PROSSDIS" name="daymap" property="key">
					<logic:equal value="true" name="daymap" property="value">
					<li id="processedtab"><a data-toggle="tab" href="#processed">Processed</a></li>
					 </logic:equal>
				</logic:equal>
			</logic:iterate>
		</logic:present> 
			
			
			
		<logic:present name="UserDetails" scope="session">
			<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
				<logic:equal value="ISSADD" name="daymap" property="key">
					<logic:equal value="true" name="daymap" property="value">
					<span class="buttons" id="addissue" style="position: absolute; right: 38px; margin-top: 3px;"> 
				           New </span>
					 </logic:equal>
				</logic:equal>
			</logic:iterate>
		</logic:present>
			
			
		</ul>
		<div class="tab-content">
			<div id="add" class="tab-pane">
				<div class="col-md-10" 
					style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
					<p>
						<img src="images/addstu.png" />&nbsp;<span id="pageHeading">
							Registration</span>
					</p>
					<logic:present name="successmessagediv" scope="request">
						<div class="successmessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span><bean:write
											name="successmessagediv" scope="request" /></span></a>
							</div>
						</div>
					</logic:present>


					<logic:present name="errormessagediv" scope="request">

						<div class="errormessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									style="color: red;"> <bean:write name="errormessagediv"
											scope="request" />
								</span></a>
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

					<form action="parentrequiresappointment.html?method=InsertTemporaryStudentRegistration"
						enctype="multipart/form-data" id="formstudent" method="post">
						<div class="panel-group" id="accordion">
							<div class="panel panel-primary">
								<div class="panel-heading" role="tab" id="headingOne">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseOne"
										style="color: #000000; vertical-align: middle;"><h3 class="panel-title"><i
										class="glyphicon glyphicon-menu-hamburger"></i>
										&nbsp;&nbsp;New Registration
									</h3></a>
									
									<div class="navbar-right">
										<span class="buttons" id="save2">Save</span>
									</div>
								</div>
								<div id="collapseOne" class="panel-collapse collapse in"
									role="tabpanel" aria-labelledby="headingOne">
									<div class="panel-body">
										<div class="col-md-6" id="div-main"
											style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">


											<div class="form-group">
												<label for="inputEmail" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;">First
													Name <span style="color: red;">*</span>
												</label>
												<div class="col-xs-7">
													<input type="text" name="parentfirstName"
														onkeypress="HideError()" id="parentfirstName"
														maxlength="25" class="form-control" value='' />
												</div>
											</div>
											<br />
											<div class="form-group">
												<label for="inputPassword" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;">Parents
													Name <span style="color: red;">*</span>
												</label>
												<div class="col-xs-7">
													<input type="text" class="form-control" name="parents_name"
														id="parents_name" onkeypress="HideError()" maxlength="10"
														value='' />
												</div>
											</div>
											<br />
											<div class="form-group">
												<label for="inputPassword" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;">Email
													Id <span style="color: red;">*</span>
												</label>
												<div class="col-xs-7">
													<input type="text" class="form-control"
														name="parentEmailId" onkeypress="HideError()"
														id="parentEmailId" maxlength="100" value='' />
												</div>
											</div>

											<br />
											<div class="form-group">
												<label for="inputEmail" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;">Address
													<span style="color: red;">*</span>
												</label>
												<div class="col-xs-7">
													<textarea name="address" id="address"
														onkeypress="HideError()" class="form-control"></textarea>
												</div>
											</div>

											<br /> <br />
										</div>
										<div class="col-md-6" id="div-main"
											style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
											<div class="form-group">
												<label for="inputEmail" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;">Last
													Name <span style="color: red;">*</span>
												</label>
												<div class="col-xs-7">
													<input type="text" class="form-control" maxlength="50"
														name="parent_LastName" id="parent_LastName"
														onkeypress="HideError()" value='' />
												</div>
											</div>

											<br />
											<div class="form-group">
												<label for="inputEmail" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;">Relationship
													<span style="color: red;">*</span>
												</label>
												<div class="col-xs-7">
													<select name="stu_parrelation" id="stu_parrelation"
														class="form-control" onkeypress="HideError()">
														<option value=" "></option>
														<option value="father">Father</option>
														<option value="mother">Mother</option>
														<option value="guardian">Guardian</option>
													</select>
												</div>
											</div>
											<br />

											<div class="form-group">
												<label for="inputEmail" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;">Mobile
													No <span style="color: red;">*</span>
												</label>
												<div class="col-xs-7">
													<input type="text" class="form-control"
														name="mobile_number" id="mobile_number"
														onkeypress="HideError()" maxlength="10" value='' />
												</div>
											</div>
											<br />
											<div class="form-group">
												<label for="inputEmail" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;"
													id="inputnames">Stream <span style="color: red;">*</span>
												</label>
												<div class="col-xs-7">
													<select id="stream" name="stream" class="form-control"
														required>
														<option value=""></option>
														<logic:present name="StreamList">

															<logic:iterate id="StreamRec" name="StreamList">

																<option
																	value="<bean:write name="StreamRec" property="streamId"/>">
																	<bean:write name="StreamRec" property="streamname" />
																</option>

															</logic:iterate>

														</logic:present>
													</select>

												</div>
											</div>

											<br />
											<div class="form-group">
												<label for="inputPassword" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;"
													id="inputnames">Class <span style="color: red;">*</span>
												</label>
												<div class="col-xs-7">
													<select id="class" name="classname" class="form-control"
														required>
														<option value=""></option>

													</select>
												</div>
											</div>

											<br />
										</div>
									</div>

								</div>

							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="issued" class="tab-pane">
		<div class="col-md-12" id="divIn">
			<div class="searchWrap">
				<div class="col-md-8" id=div2>

					<p>
						<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
							id="pageHeading">Issued Forms</span>
					</p>
				</div>


				

					<div class="input-group col-md-4">
						<input type="text" name="searchname" 
							class="form-control" id='searcissue' onkeypress="handleissue(event)"
							Placeholder="Search......"
							value=''>
						<span class="input-group-btn">
							<button class="btn btn-default" style = "padding-top: 4px;"type="button" id="issuesearch">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
			</div>
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
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


			<div class="panel panel-primary">
				<div class="panel-heading">
					<a data-toggle="" data-parent="#accordion2" href="#"
						style="color: #fff;"><h3 class="panel-title issued"
							style="color: #000000;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Issued
							Forms
						</h3></a>



					<div class="navbar-right">
					          <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="ISSUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<span class="buttons" id="edit" style="top: 12px; margin-right: 7px;">Modify</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
					
					</div>

				</div>
				<!-- pop up --> 
	
	<input type="hidden" id="historysearch1"  
	value='<logic:present name="historysearch" scope="request"><bean:write name="historysearch" /></logic:present>' />			
	
	<input type="hidden" id="historytabstatus1"  
	value='<logic:present name="historytabstatus" scope="request"><bean:write name="historytabstatus" /></logic:present>' />
				
				<div id="issuedOne" class="accordion-body collapse in">
					<div class="panel-body"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
						<div id = "issuedList">
							<table class="table" id="allstudent" class="issuedList">
								<thead>
									<tr>
										<th>Select</th>
										<th>Student Name</th>
										<th>Father Name</th>
										<th>Email</th>
										<th>Mobile No.</th>
										<th>Address</th>
									</tr>
								</thead>
								<tbody>
								<logic:present name="issuedList" scope="request">
								<logic:iterate id="issuedList" name="issuedList">
								<tr>
									<td style="text-align: center;"><input type='checkbox' name='select' class='select'
										style='text-align: center' id='<bean:write name="issuedList" property="enquiryid"/>' /></td>
									<td><a class="anchor">
										<input type="button" class="buttonstyle"
										id="<bean:write name="issuedList" property="enquiryid"/>" value="<bean:write name="issuedList" property="fullname"/>" />
									</a></td>
									<td><bean:write name="issuedList" property="parents_name"/></td>
									<td><bean:write name="issuedList" property="parentEmailId"/></td>
									<td><bean:write name="issuedList" property="mobile_number"/></td>
									<td><bean:write name="issuedList" property="address"/></td>
								</tr>
								</logic:iterate>
								</logic:present>
								</tbody>
							
							</table>
						</div>
						<div class='pagebanner'>
								<select id='show_per_page'>
									<option value='50'>50</option>
									<option value='100'>100</option>
									<option value='200'>200</option>
									<option value='300'>300</option>
									<option value='400'>400</option>
									<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
							<div class='pagination pagelinks' style='top: -9px'></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="approvedlink" class="tab-pane">

		<div class="col-md-12" id="divIn">
			<div class="searchWrap">
				<div class="col-md-8" id="div2">
					<p>
						<span class="glyphicon glyphicon-user"></span><span
							id="pageHeading" style="margin-left: 12px;">Approved Forms</span>
					</p>
				</div>
				<div class="input-group col-md-4">
					<input type="text" name="searchname" id="searchapprove"
						onkeypress="handleapprove(event)" class="form-control"
						Placeholder="Search......"
						value=''>
					<span class="input-group-btn">
						<button class="btn btn-default" style='padding-top: 4px;' type="button" id="apprsearch">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
			</div>
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
									name="successmessagediv" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>

			<logic:present name="errormessagediv" scope="request">

				<div class="errormessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span
							style="color: red;"> <bean:write name="errorMessage"
									scope="request" />
						</span></a>
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

			<div class="panel panel-primary">
				<div class="panel-heading">
					<a data-toggle="collapse" data-parent="#accordion2" href="#"
						style="color: #fff;"><h3 class="panel-title class"
							style="color: #000000;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Approved
							Forms
						</h3></a>
					<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>
				</div>
				<!-- pop up -->
				<div id="classOne" class="accordion-body collapse in">
					<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<div id = "approvedList"></div>
						<div class='pagebanner'>
								<select id='show_per_page'>
									<option value='50'>50</option>
									<option value='100'>100</option>
									<option value='200'>200</option>
									<option value='300'>300</option>
									<option value='400'>400</option>
									<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
							<div class='pagination pagelinks' style='top: -9px'></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="rejected" class="tab-pane">
		<div class="col-md-12" id="divIn">
			<div class="searchWrap">
				<div class="col-md-8" id="div2">

					<p>
						<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
							id="pageHeading">Rejected Forms</span>
					</p>
				</div>
				
				<div class="input-group col-md-4">
					<input type="text" name="searchname" id="searchreject"
						onkeypress="handlereject(event)" class="form-control"
						Placeholder="Search......"
						value=''>
					<span class="input-group-btn">
						<button class="btn btn-default" style='padding-top: 4px;' type="button" id="searchrjct" value="Submitform">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
			</div>
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
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
			<div class="panel panel-primary">
				<div class="panel-heading">
					<a data-toggle="collapse" data-parent="#accordion2" href="#"
						style="color: #fff;"><h3 class="panel-title reject"
							style="color: #000000;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Rejected
							Forms
						</h3></a>
				</div>
				<!-- pop up -->
				<div id="rejectedOne" class="accordion-body collapse in">
					<div class="panel-body"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
						<div id ="rejectlist">
						</div>
						<div class='pagebanner'>
								<select id='show_per_page'>
									<option value='50'>50</option>
									<option value='100'>100</option>
									<option value='200'>200</option>
									<option value='300'>300</option>
									<option value='400'>400</option>
									<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
							<div class='pagination pagelinks' style='top: -9px'></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="cancelled" class="tab-pane">
		<div class="col-md-12" id="divIn">
			<div class="searchWrap">
				<div class="col-md-8" id="div2">

					<p>
						<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
							id="pageHeading">Cancelled Forms</span>
					</p>
				</div>
				<div class="input-group col-md-4">
					<input type="text" name="searchname" id="searchcancelled"
						onkeypress="handlecancel(event)" class="form-control"
						Placeholder="Search......"
						value=''>
					<span class="input-group-btn">
						<button class="btn btn-default" style='padding-top: 4px;' type="button" id="searchcacl">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
			</div>
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
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


			<div class="panel panel-primary">
				<div class="panel-heading">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title cancelled" style="color: #000000;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Cancelled
							Forms
						</h3></a>
				</div>
				<!-- pop up -->
				<div id="cancelledOne" class="accordion-body collapse in">
					<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
						<div id ="cancellist">
						</div>
						<div class='pagebanner'>
								<select id='show_per_page'>
									<option value='50'>50</option>
									<option value='100'>100</option>
									<option value='200'>200</option>
									<option value='300'>300</option>
									<option value='400'>400</option>
									<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
							<div class='pagination pagelinks' style='top: -9px'></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="submitted" class="tab-pane">

		<div class="col-md-12" id="divIn">
			<div class="searchWrap">
				<div class="col-md-8" id="div2">

					<p>
						<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
							id="pageHeading">Submitted Forms</span>
					</p>
				</div>
				<div class="input-group col-md-4">
					<input type="text" name="searchname" id="searchdsbmit" 
						onkeypress="handlesubmit(event)" class="form-control"
						Placeholder="Search......"
						value=''>
					<span class="input-group-btn">
						<button class="btn btn-default" style='padding-top: 4px;' type="button" id="searchsubmit"
							onclick="myFunction()" value="Submitform">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
			</div>
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
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
			<div class="panel panel-primary">
				<div class="panel-heading">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title submitted" style="color: #000000;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Submitted
							Forms
						</h3></a>
				</div>
				<!-- pop up -->
				<div id="submittedOne" class="accordion-body collapse in">
					<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
						<div id='submittedlist'></div>
							<div class='pagebanner'>
								<select id='show_per_page'>
									<option value='50'>50</option>
									<option value='100'>100</option>
									<option value='200'>200</option>
									<option value='300'>300</option>
									<option value='400'>400</option>
									<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
							<div class='pagination pagelinks' style='top: -9px'></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="processed" class="tab-pane">

		<div class="col-md-12" id="divIn">
			<div class="searchWrap">
				<div class="col-md-8" id="div2">

					<p>
						<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
							id="pageHeading">Processed Forms</span>
					</p>
				</div>
				<div class="input-group col-md-4">
					<input type="text" name="searchname" id="searchprocess"
						onkeypress="handleprocess(event)" class="form-control"
						Placeholder="Search......"
						value=''>
					<span class="input-group-btn">
						<button class="btn btn-default" style='padding-top: 4px;' type="button" id="searchpess">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
			</div>
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
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


			<div class="panel panel-primary">
				<div class="panel-heading">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title processed" style="color: #000000;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Processed
							Forms
						</h3></a>
				</div>
				<!-- pop up -->
				
				<div id="processedOne" class="accordion-body collapse in">
					<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
						<div id='processedlist'></div>
						<div class='pagebanner'>
								<select id='show_per_page'>
									<option value='50'>50</option>
									<option value='100'>100</option>
									<option value='200'>200</option>
									<option value='300'>300</option>
									<option value='400'>400</option>
									<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
							<div class='pagination pagelinks' style='top: -9px'></div>
					</div>
				</div>
		
		</div>
	</div>
	</div>
	</div>
</body>
</html>