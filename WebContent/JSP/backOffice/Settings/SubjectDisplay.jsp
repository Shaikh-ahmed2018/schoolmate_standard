<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <script type="text/javascript" src="JS/backOffice/Settings/SubjectDisplay.js"></script>



</head>

<body>
  
  <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="content" id="div1">
		<div id="dialog"></div>
		<div class="searchWrap">
			<div class="" id="div2">
				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Subject Details</span>
				</p>
			</div>



			<input type="hidden" name="searchterm" class="searchtermclass"
				id="searchexamid"
				value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />
		</logic:present>'></input>

		</div>

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
			<div class="panel-heading clearfix">
	    			<a data-toggle="collapse" data-parent="#accordion2"
					href="#subjectOne" style="color: #fff;"><h3 class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Subject
						Details
					</h3></a>

				<div class="navbar-right">
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SUBADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<a href="subject.html?method=getsubject"><span
										class="buttons">New</span> </a>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SUBUPD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span id="editsubject" class="buttons">Modify</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>


					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SUBDEL" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span id="delete" class="buttons">InActive</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>

				</div>

			</div>
			<!-- pop up -->
	
	<input type="hidden" id="buttsts" value=''/>		
	
	<input type="hidden" id="curr_loc" name="curr_loc" 
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
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">

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
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">
					
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

				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

					<div id="collapseOne" class="accordion-body collapse in">
						<div class="panel-body"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

							<logic:present name="allsubjects" scope="request">

								<table class="table" id="allstudent">
									<thead>
										<tr>
											<th><input type='checkbox' name='selectall'
												id='selectall' style='text-align: center'/></th>
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
												<td><bean:write name='allsubjects' property="subjectname" /></td>
												<td><bean:write name='allsubjects' property="subjectCode" /></td>
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
		</div>
			<!-- <script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script> -->
</body>
</html>