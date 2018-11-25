<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/backOffice/Fee/FeeScholorship.js"></script>



</head>

<body class="feeconcession">


	<div class="successmessagediv" style="display: none;">
		<div class="message-item">
			<!-- Warning -->
			<a href="#" class="msg-success bg-msg-succes"
				style="text-align: center;"><span class="sucessmessage">
			</span></a>
		</div>
	</div>
	<div class="errormessagediv" style="display: none;">
		<div class="message-item">
			<!-- Warning -->
			<a href="#" class="msg-warning bg-msg-warning"
				style="text-align: center;"><span class="validateTips"></span></a>
		</div>
	</div>
	<div id="myDialog" style="display: none;">
		<div class='row'>
			<div class='col-md-6'>
				<div class='form-group clearfix'>
					<label for='labelforAdmissionNo' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Admission
						No.</label>
					<div class="col-xs-7">
						<input type="text" name='admissionNo' id='admissionNo' /> <input
							type="hidden" name='hstudentId' id='hstudentId' />
					</div>
				</div>

				<div class='form-group clearfix'>
					<label for='labelforName' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Name</label>
					<div class="col-xs-7">
						<input type="text" name='student' id='student' readOnly="readOnly"  />
					</div>
				</div>


				<div class='form-group clearfix'>
					<label for='labelforName' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Concession Type</label>
					<div class="col-xs-7">
						<div class="concessionType">
							<label for='labelforFullConcession' class='control-label'>Full
								Concession </label> <input type="radio" name='contype' value="full" />
						</div>
						<div class="concessionType">
							<label for='labelforEqualConcession' class='control-label'>Equally
								Concession </label> <input type="radio" name='contype' value="equal" />
						</div>
						<div class="concessionType">
							<label for='labelforPartialConcession' class='control-label'>Concession
								Term wise</label> <input type="radio" name='contype' value="partial" />
						</div>
					</div>
				</div>

			</div>
			<div class='col-md-6'>
				<div class='form-group clearfix'>
					<label for='labelforclass' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Standard-Division</label>
					<div class="col-xs-7">
						<input type="text" name='class_section' id='class_section'  readOnly="readOnly" /> <input
							type="hidden" name='hclassId' id='hclassId'  readOnly="readOnly" /> <input
							type="hidden" name='hspecialization' id='hspecialization'  readOnly="readOnly" />
					</div>

				</div>


				<div class='form-group clearfix'>
					<label for='AcademicYear' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Academic
						Year</label>
					<div class="col-xs-7">
						<select id="AcademicYearFor" name="AcademicYearFor" 
							class="form-control" required>

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

				<div class='form-group clearfix equalconcession'>
					<label for='labelforscholorshipAmount'
						class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Concession
						Amount</label>
					<div class="col-xs-7">
						<input type="text" name='scholorshipAmount' id='scholorshipAmount' />
					</div>

				</div>


				<div class='form-group clearfix partialconcession'>
					<label for='labelforscholorshipAmount'
						class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Concession
						Amount</label>
					<div class="col-xs-7">
						<fieldset style="width: auto; padding-top: 10px;">
							<legend>Term wise</legend>
							<table id="termwiseconcession">
								<thead>
									<tr>
										<th>Term Name</th>
										<th>Fee Amount</th>
										<th style="width: 100px;">Concession Amount</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>


						</fieldset>
					</div>

				</div>


			</div>
			<div class='col-md-12'></div>
		</div>
	</div>
	<div id="dialog" style="display: none;"></div>


	<div class="content" id="div1">
		<div class="searchWrap">
			<div class="" id="div2">
				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Fee Concession Details</span>
				</p>
			</div>
		</div>

	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		
			<div class="panel panel-primary ">
			
				<div class="panel-heading clearfix" role="tab" id="headingOne">

					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne"
						style="color: #000000; vertical-align: text-top;">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Fee Concession Student List
						</h3>
					</a>
					<%-- <div class="navbar-right">
					 <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CONADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											 <span class="buttons" id="add">Add</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							 <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CONDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											 <span id="delete" class="buttons">InActive</span>&nbsp;
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
						
						
							
					</div> --%>
				</div>
          
			
			<div id="collapseOne" class="panel-collapse collapse in "
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body own-panel">
					<div class="col-md-6"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Branch</label>
							<div class="col-xs-7">
								<select id="locationname" name="locationnid"
									class="form-control" required>
									
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
									name="classname" id="classname">
									<option value="all">ALL</option>
								</select>


							</div>
						</div>


						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Search</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="searchvalue" autocomplete="off"
									Placeholder="Search......"
									value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
							</div>
						</div>
						
						
							<div class="form-group clearfix">
							<div class="col-xs-5"></div>
							<div class="col-xs-7">
							<span type="button" class="buttons" id="search">Search</span>
							<span type="reset" class="buttons" id="resetbtn">Reset</span>
							</div>
						</div>
							

					</div>

					<div class="col-md-6"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Academic
								Year</label>
							<div class="col-xs-7">
								<select id="Acyearid" name="accyear" class="form-control"
									required>

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

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Division</label>
							<div class="col-xs-7">
								<select id="sectionid" name="sectionid" class="form-control"
									required>
									<option value="all">ALL</option>
								</select>
							</div>
						</div>

						<div class="form-group clearfix ">
							<label for="inputPassword" class="control-label col-xs-5"
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

					
					<input type="hidden" name="Acyearid" id="Acyearid"
						value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>



					<div id="collapseOne" class="accordion-body collapse in">
						<div class="panel-body"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">


							<table class="table" id="allstudent" width="100%">
								<thead>
									<tr>
										<th>S.No</th>
										<th class="sortable">Academic Year</th>
										<th class="sortable">Admission No</th>
										<th class="sortable">Student Name</th>
										<th class="sortable">Class and Section</th>
										<th class="sortable">Concession Type</th>
										<th class="sortable">Status</th>
										<th class="sortable">Remark</th>
										<th class="sortable">Photo</th>

									</tr>
								</thead>
								<tbody></tbody>
							</table>
							<div class="pagebanner">
								<select id="ShowPerPage"><option value="50">50</option>
									<option value="100" selected>100</option>
									<option value="200">200</option>
									<option value="300">300</option>
									<option value="400">400</option>
									<option value="500">500</option>
								</select> <span class='numberOfItem'></span>
							</div>
							<div class="pagination pagelinks" style="margin-bottom: 20px;"></div>

						</div>
					</div>
				</div>
			
		</div>
	</div>
</div>
</div>
</body>
</html>