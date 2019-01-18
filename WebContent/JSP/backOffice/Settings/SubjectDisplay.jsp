<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script type="text/javascript"
	src="JS/backOffice/Settings/SubjectDisplay.js"></script>



</head>

<body>

	<div id="loder"
		style="display: none; position: absolute; height: 800px; width: 800px; left: 0; right: 0; top: -40px;; bottom: 0; margin: auto; z-index: 99999;">
		<img
			style="width: 165px; position: absolute; left: 0; right: 0; height: 165px; bottom: 0; top: -190px; margin: auto;"
			src="./images/ajax-loading.gif" />
	</div>
	<div class="content" id="div1">
		<div id="dialog"></div>

		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />
		</logic:present>'></input>



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
			<h3 class="panel-title" style="color: #000000;">
				</span>&nbsp;&nbsp;Subject Details
			</h3>

			<div class="navbar-right">

				<!-- add btn -->
				<logic:present name="UserDetails" scope="session">
					<logic:iterate id="daymap" name="UserDetails"
						property="permissionmap" scope="session">
						<logic:equal value="SUBADD" name="daymap" property="key">
							<logic:equal value="true" name="daymap" property="value">
								<span id="savebutton" class="btn btn-xs btn-primary margin-t-5"
									data-toggle="modal" data-target="#myModal"> <span
									class="glyphicon glyphicon-plus"></span>Add Subject
								</span>
							</logic:equal>
						</logic:equal>
					</logic:iterate>
				</logic:present>
				<!-- : ends-->

				<!-- edit & delete-->
				<input type="hidden" id="editPermission"
					value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="SUBUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
				<input type="hidden" id="delPermission"
					value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="SUBDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
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
			value='<logic:present name="locId" scope="request"><bean:write name="locId"/></logic:present>'></input>

		<input type="hidden" id="hiddenclassname"
			value='<logic:present name="classname" scope="request"><bean:write name="classname"/></logic:present>'></input>

		<input type="hidden" id="hiddenspecId"
			value='<logic:present name="specialization" scope="request"><bean:write name="specialization"/></logic:present>'></input>

		<input type="hidden" id="hiddenstatus"
			value='<logic:present name="status" scope="request"><bean:write name="status"/></logic:present>'></input>

		<div id="subjectOne" class="accordion-body collapse in">
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
							<option value="">ALL</option>

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
						<select name="classname" id="classname" class="form-control"
							onkeypress="HideError()">
							<option value="">ALL</option>

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

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="myModalLabel">Subject Details</h3>
						</div>
						<div class="modal-body">
						<div class="panel-body clearfix">

							<div class="col-md-6" id="txtstyle" style="font-size: 13px;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Branch <span
										style="color: red;">*</span></label>
									<div class="col-xs-7">
										<select id="locationname" name="locationId"
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
										style="text-align: right; line-height: 35px;">Subject
										Name<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" name="subjtname" id="subjtname"
											onkeypress="HideError()" class="form-control" placeholder=""
											value="" />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Subject
										Code<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" name="subjectCode" id="subjectCode"
											onkeypress="HideError()" class="form-control" placeholder=""
											value="" />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Sub Type<span
										style="color: red;">*</span></label>
									<div class="col-xs-7">
										<!-- <input type="text" name="passMarks" id="passMarks" onkeypress="HideError()" class="form-control"   placeholder="" value=""/> -->
										<select id="subtype" name="subType" class="form-control">
											<option value="">-------------Select-----------</option>
											<option value="Major">Major</option>
											<option value="Minor">Minor</option>

										</select>

									</div>
								</div>
								<input type="hidden" name="clsnam" id="clsname"></input>
							</div>

							<div class="col-md-6" id="txtstyle">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class<span
										style="color: red;">*</span></label>
									<div class="col-xs-7">
										<select name="classname" id="classname" class="form-control"
											onkeypress="HideError()">
											<option value="">-------------Select-----------</option>

										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Language<span
										style="color: red;">*</span> ?
									</label>
									<div class="col-xs-7">
										<select id="isLangauge" name="isLang" class="form-control">
											<option value="N">No</option>
											<option value="Y">Yes</option>
										</select>
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Specialization<span
										style="color: red; display: none;" class="notmandatory">*</span></label>
									<div class="col-xs-7">
										<select id="specialization" name="specialization"
											class="form-control">
											<option value="-" selected="selected">NIL</option>
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
										Available<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<!-- <input type="text" name="totalMarks" id="totalMarks" onkeypress="HideError()" class="form-control"   placeholder="" value=""/> -->
										<select id="isLab" name="islab" class="form-control">
											<option value="N">No</option>
											<option value="Y">Yes</option>


										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
										<textarea rows="3px" cols="3px" name="description"
											id="description" class="form-control" placeholder="">
											</textarea>
									</div>
								</div>

								<input type="hidden" name="hiddensubjectId" id="hiddensubjectid"
									value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="subjectid"/></logic:present>" />

								<input type="hidden" name="hiddenclass" id="hiddenclassid"
									value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="classname"/></logic:present>" />
								<input type="hidden" name="hiddensubject" id="hiddensubject"
									value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="subjectname"/></logic:present>" />
								<input type="hidden" name="hiddendescription"
									id="hiddendescription"
									value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="description"/></logic:present>" />
								<input type="hidden" name="hiddenfile" id="hiddenfile"
									value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="path"/></logic:present>" />

								<input type="hidden" name="status" id="statusid"
									value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="status"/></logic:present>" />

							</div>
							</div>
						</div>
						<div class="modal-footer">
						<span id="save" class="buttons button-blue">Save</span>
          				<span class="buttons button-simple" data-dismiss="modal">Close</span>
       				 </div>

					</div>
				</div>
			</div>

			<div class="panel-body"
				style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">

				<div id="collapseOne" class="accordion-body collapse in">
					<div class="panel-body"
						style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">

						<logic:present name="allsubjects" scope="request">

							<table class="table" id="allstudent">
								<thead>
									<tr>
										<th><input type='checkbox' name='selectall'
											id='selectall' style='text-align: center' /></th>
										<th>Branch</th>
										<th>Class Name</th>
										<th>Specialization</th>
										<th>Subject Name</th>
										<th>Subject Code</th>
										<th>Remarks</th>
										<!-- <th>Syllabus</th> -->
									</tr>
								</thead>
								<tbody>
									<logic:iterate name='allsubjects' id="allsubjects">
										<tr>
											<td><input type='checkbox' name='select' class='select'
												style='text-align: center'
												id='<bean:write name='allsubjects' property="subjectid"/>,<bean:write name='allsubjects' property="locationId"/>' /></td>
											<td><bean:write name='allsubjects'
													property="locationName" /></td>
											<td><bean:write name='allsubjects' property="classname" /></td>
											<td><bean:write name='allsubjects'
													property="specializationName" /></td>
											<td><bean:write name='allsubjects'
													property="subjectname" /></td>
											<td><bean:write name='allsubjects'
													property="subjectCode" /></td>
											<td><bean:write name='allsubjects' property="remark" /></td>
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
							<div class='pagination pagelinks' style="margin-bottom: 10px"></div>
						</logic:present>

					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- <script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script> -->
</body>
</html>