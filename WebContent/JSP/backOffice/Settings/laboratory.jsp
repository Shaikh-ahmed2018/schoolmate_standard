<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>
<script type="text/javascript"
	src="JS/backOffice/Settings/laboratory.js"></script>
</head>

<body>
	<div id="loder"
		style="display: none; position: absolute; height: 800px; width: 800px; left: 0; right: 0; top: -40px;; bottom: 0; margin: auto; z-index: 99999;">
		<img
			style="width: 165px; position: absolute; left: 0; right: 0; height: 165px; bottom: 0; top: -190px; margin: auto;"
			src="./images/ajax-loading.gif">
	</div>
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
			<h3 class="panel-title" style="color: #000000;">Practical
				Details</h3>
			<div class="navbar-right">
				
				<logic:present name="UserDetails" scope="session">
					<logic:iterate id="daymap" name="UserDetails"
						property="permissionmap" scope="session">
						<logic:equal value="LABADD" name="daymap" property="key">
							<logic:equal value="true" name="daymap" property="value">
								<span id="savebutton" class="btn btn-xs btn-primary margin-t-5"
									data-toggle="modal" data-target="#myModal"> <span
									class="glyphicon glyphicon-plus"></span>Add Lab Practical
								</span>
							</logic:equal>
						</logic:equal>
					</logic:iterate>
				</logic:present>

				<!-- edit & delete-->
				<input type="hidden" id="editPermission"
					value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="LABUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
				<input type="hidden" id="delPermission"
					value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="LABDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
				<!--  :ends-->
			</div>
		</div>
		<!-- pop up -->
		<input type="hidden" id="buttsts" value='' /> <input type="hidden"
			id="curr_loc" name="curr_loc"
			value="<logic:present name="curr_loc" scope="request"><bean:write name="curr_loc"/></logic:present>" />

		<input type="hidden" id="currentstatus" name="currentstatus"
			value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />

		<input type="hidden" id="hiddenlocId"
			value='<logic:present name="locId1" scope="request"><bean:write name="locId1" /></logic:present>'></input>

		<input type="hidden" id="hiddenclassname"
			value='<logic:present name="classname1" scope="request"><bean:write name="classname1" /></logic:present>'></input>

		<input type="hidden" id="hiddenspecId"
			value='<logic:present name="specId1" scope="request"><bean:write name="specId1" /></logic:present>'></input>

		<input type="hidden" id="hiddenstatus"
			value='<logic:present name="status1" scope="request"><bean:write name="status1" /></logic:present>'></input>

		<div id="collapseOne" class="accordion-body collapse in">
			<div class="col-md-6"
				style="font-family: Roboto, sans-serif; font-size: 13px; color: #000; margin-top: 20px;">

				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
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
					<label for="inputEmail" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Specialization</label>
					<div class="col-xs-7">
						<select id="specialization" name="specialization"
							class="form-control">
							<option value="all">ALL</option>

						</select>
					</div>
				</div>
			</div>
			<div class="col-md-6"
				style="font-family: Roboto, sans-serif; font-size: 13px; color: #000; margin-top: 20px;">

				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Class</label>
					<div class="col-xs-7">
						<select name="classname" id="classname" class="form-control">
							<option value="all">ALL</option>
						</select>
					</div>
				</div>

				<div class="form-group clearfix ">
					<label for="inputEmail" class="control-label col-xs-5"
						style="text-align: right; line-height: 35px;">Status</label>
					<div class="col-xs-7">
						<select name="status" tabindex="1" id="status"
							class="form-control">
							<option value="Y" selected>Active</option>
							<option value="N">InActive</option>
						</select>
					</div>
				</div>

			</div>



		</div>
		<div class="modal fade" id="myModal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" id="myModalLabel">Laboratory Details</h3>
					</div>
					<div class="modal-body">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Branch<span
									style="color: red;">*</span></label>
								<div class="col-xs-7">
									<select id="locationname" name="locationName"
										class="form-control">
										<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option
													value="<bean:write name="Location" property="locationId"/>"><bean:write
														name="Location" property="locationName" /></option>
											</logic:iterate>
										</logic:present>

									</select>
								</div>
								<input type="hidden" name="schoolId" class="form-control"
									id="schoolId"
									value='<logic:present name="list"><bean:write name="list" property="locationId" /></logic:present>'></input>
							</div>


							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Specialization</label>
								<div class="col-xs-7">
									<select id="specialization" name="specialization"
										class="form-control">

										<option value="-" selected="selected">----------Select----------</option>
										<logic:present name="specialization">
											<logic:iterate id="specialization" name="specialization">
												<option
													value="<bean:write name="specialization" property="specializationId"/>"><bean:write
														name="Location" property="specializationName" /></option>
											</logic:iterate>
										</logic:present>

									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Practical
									Name<span style="color: red;">*</span>
								</label>
								<div class="col-xs-7">
									<input type="text" style="text-transform: capitalize;"
										maxlength="25" name="lab_name"
										onkeypress="return isNumberKey(event);"
										onclick="HideError(this)" id="lab_name" class="form-control"
										value='' />
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Description</label>
								<div class="col-xs-7">
									<textarea rows="3px" cols="3px" name="description"
										id="description" class="form-control" placeholder=""><logic:present
											name="singlelabdetails">
											<bean:write name="singlelabdetails" property="description" />
										</logic:present></textarea>
								</div>
						</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Class<span
									style="color: red;">*</span></label>
								<div class="col-xs-7">
									<select name="classname" id="classname" class="form-control"
										onkeypress="HideError()" onchange="HideError(this)">
										<option value="">----------Select----------</option>
									</select>
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Subject<span
									style="color: red;">*</span></label>
								<div class="col-xs-7">
									<select id="subject" name="subjtname" class="form-control"
										onchange="HideError(this)">
										<option value="">----------Select----------</option>
									</select>
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Practical
									Code<span style="color: red;">*</span>
								</label>
								<div class="col-xs-7">
									<input type="text" maxlength="10" name="subjectCode"
										id="subjectCode" onkeypress="return isNumberKey(event);"
										class="form-control" onclick="HideError(this)" placeholder=""
										value='<logic:present name="singlelabdetails"><bean:write name="singlelabdetails" property="subjectCode" /></logic:present>' />
								</div>
							</div>

					</div>

					<div class="modal-footer">
						<span id="save" class="buttons button-blue">Save</span> <span
							class="buttons button-simple" data-dismiss="modal">Close</span>
					</div>
				</div>
			</div>
		</div>
		<div id="subjectOne" class="accordion-body collapse in">

			<div class="panel-body"
				style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">

				<div id="collapseOne" class="accordion-body collapse in">
					<div class="panel-body"
						style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">

						<logic:present name="laboratoryDetails" scope="request">
							<%-- <table id="regConfirmationtable">
							<tbody>
								<display:table class="table" id="allstudent"
									name="requestScope.laboratoryDetails"
									decorator="com.centris.campus.decorator.ViewAllSubjectDecorator"
									requestURI="menuslist.html?method=laboratory">
									
									<tr>
										<display:column media="html"
									title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />">
									<input type='checkbox' name='select' class='select' id='${allstudent.lab_id},${allstudent.locationId}' onclick='selectAll()'  />
								</display:column>
										<display:column property="locationName" sortable="true"  title="School Name<img src='images/sort1.png' style='float: right'/>" />
										<display:column property="classname" sortable="true"  title="Class Name<img src='images/sort1.png' style='float: right'/>" />
										<display:column property="specialization" sortable="true"  title="Specialization<img src='images/sort1.png' style='float: right'/>" />
										<display:column property="subjtname" sortable="true"  title="Subject Name<img src='images/sort1.png' style='float: right'/>" autolink="true" />
										<display:column property="lab_name" sortable="true"  title="Lab  Name<img src='images/sort1.png' style='float: right'/>" autolink="true" />
										<display:column property="totalMarks" sortable="true"  title="Total Marks<img src='images/sort1.png' style='float: right'/>" />
										<display:column property="passMarks" sortable="true"  title="Pass Marks<img src='images/sort1.png' style='float: right'/>" />
										<display:column property="subjectCode" sortable="true"  title="Lab Code<img src='images/sort1.png' style='float: right'/>" autolink="true" />
										<display:column property="description" sortable="true" style="width:93px" title="Description<img src='images/sort1.png' style='float: right'/>" />
										<display:column property="labdownload"   title="Syllabus<img  style='float: right'/>" />
									
									
									</tr>
								</display:table>
							</tbody>
						</table> --%>

							<table class="table" id="allstudent">
								<thead>
									<tr>
										<th><input type='checkbox' name='selectall'
											id='selectall' style='text-align: center' /></th>
										<th>Branch</th>
										<th>Class Name</th>
										<th>Specialization</th>
										<th>Subject</th>
										<th>Practical</th>
										<th>Practical Code</th>
										<th>Description</th>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name='laboratoryDetails' id="laboratoryDetails">
										<tr>
											<td><input type='checkbox' name='select' class='select'
												style='text-align: center'
												id='<bean:write name='laboratoryDetails' property="lab_id"/>,<bean:write name='laboratoryDetails' property="locationId"/>' /></td>
											<td><bean:write name='laboratoryDetails'
													property="locationName" /></td>
											<td><bean:write name='laboratoryDetails'
													property="classname" /></td>
											<td><bean:write name='laboratoryDetails'
													property="specialization" /></td>
											<td><bean:write name='laboratoryDetails'
													property="subjtname" /></td>
											<td><bean:write name='laboratoryDetails'
													property="lab_name" /></td>
											<td><bean:write name='laboratoryDetails'
													property="subjectCode" /></td>
											<td><bean:write name='laboratoryDetails'
													property="description" /></td>
										</tr>
									</logic:iterate>
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

	</div>

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</body>
</html>