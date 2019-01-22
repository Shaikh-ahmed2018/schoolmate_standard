<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<script type="text/javascript"
	src="JS/backOffice/Admin/AcademicYearDetails.js"></script>

<script type="text/javascript">
function handle(e){
	var searchText = $("#searchname").val();
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn

        window.location.href ="menuslist.html?method=academicyear&searchText="
			+ searchText;
    }
}
</script>

<title>eCampus Pro</title>


</head>

<body>

	<div class="content" id="div1">



		<div id="dialog"></div>

		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />
        </logic:present>'></input>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item" style="margin-left: 10px;">
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item" style="margin-left: 10px;">
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>



		<div class="panel-heading">
			<h3 class="panel-title" style="color: #000000; font-family: Poppins;">Academic
				Year Details</h3>
				
			<div class="navbar-right">
				<logic:present name="UserDetails" scope="session">
					<logic:iterate id="daymap" name="UserDetails"
						property="permissionmap" scope="session">
						<logic:equal value="ACCYRADD" name="daymap" property="key">
							<logic:equal value="true" name="daymap" property="value">
								<span class="btn btn-xs btn-primary margin-t-5 addClass"
									data-toggle="modal" data-target="#myModal"> <span
									class="glyphicon glyphicon-plus"></span>Add Academic year
								</span>

							</logic:equal>
						</logic:equal>
					</logic:iterate>
				</logic:present>
			</div>

		</div>
		<!-- pop up -->
		
	 	<%@include  file="/LAYOUT/html/inActiveModel.html" %>
		
		
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="modal-title" id="myModalLabel">Academic Year</h3>
					</div>
					
					
					<div class="modal-body">
						<div class="panel-body">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Start
									Date </label>
								<div class="col-xs-7">
									<input type="text" name="startdate" id="startdate"
										placeholder="Start Date" onclick="HideError(this)"
										class="form-control" value='' onchange="setEndDate()"></input>
								</div>
							</div>
							<input type="hidden" id="editPermission"
								value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="ACCYRUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
							<input type="hidden" id="delPermission"
								value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="ACCYRDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">End Date</label>
								<div class="col-xs-7">
									<input type="text" name="enddate" id="enddate"
										readonly="readonly" placeholder="End Date"
										onclick="HideError(this)"
										class="form-control date hasDatepicker" value=''></input>

								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Academic Year
								</label>
								<div class="col-xs-7">
									<input type="text" name="accyearname" id="accyearname" readonly="readonly"
										maxlength="25" class="form-control" onclick="HideError(this)"
										placeholder="" value=''></input>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Description
								</label>
								<div class="col-xs-7">
									<textarea name="description" class="form-control"
										id="description">
										</textarea>
								</div>
							</div>

							<input type="hidden" name="accid" id="accid" value='' />


						</div>
					</div>
					<div class="modal-footer">
						<span id="save" class="buttons button-blue">Save</span> <span
							class="buttons button-simple" data-dismiss="modal">Close</span>
					</div>
					
					<input type="hidden" id="actionstatus" value="add" />	

				</div>
			</div>
		</div>

		<input type="hidden" id="currentstatus" name="currentstatus"
			value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />

		<div id="collapseOne_NEW" class="content-div">
			<div class="panel-body"
				style="font-family: Poppins, sans-serif; font-size: 13px; color: #000; margin-bottom: 10px">
				<div class="col-md-6"
					style="font-family: Poppins, sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;"> Academic
							year</label>
						<div class="col-xs-7">

							<div class="switch">
								<span id="text-status">Active</span>
								<div class="switch-cirle"></div>
							</div>
							<select style="display: none;" id="status" name="status"
								class="form-control">
								<option value="Y">Active</option>
								<option value="N">InActive</option>
							</select>
						</div>
					</div>
				</div>

				<input type="hidden" id="hiddenstatus1" name="hiddenstatus1"
					value="<logic:present name="status" scope="request"><bean:write name="status"/></logic:present>">


				<logic:present name="academicyearlist" scope="request">
					<table class="table" id="allstudent">
						<thead>
							<tr>
								<th>S.No.</th>
								<th>Academic Name</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>Description</th>

								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name='academicyearlist' id="academicyearlist">
								<tr
									id="<bean:write name='academicyearlist' property="acadamic_id"/>">
									<td><bean:write name='academicyearlist' property="sno" /></td>
									<td><bean:write name='academicyearlist'
											property="acadamic_name" /></td>
									<td><bean:write name='academicyearlist'
											property="startDate" /></td>
									<td><bean:write name='academicyearlist' property="endDate" /></td>
									<td><bean:write name='academicyearlist'
											property="description" /></td>

									<td><logic:present name="UserDetails" scope="session">
											<logic:iterate id="daymap" name="UserDetails"
												property="permissionmap" scope="session">
												<logic:equal value="ACCYRUPD" name="daymap" property="key">
													<logic:equal value="true" name="daymap" property="value">
														<!-- <span class="glyphicon glyphicon-pencil editDesignationId" data-toggle="modal" data-target="#myModal" title="Edit"></span> -->

														<span data-toggle="modal" data-target="#myModal"
															class="btn btn-xs btn-primary margin-t-5 editDesignationId"
															title="Edit"><span
															class="glyphicon glyphicon-edit"></span> Edit</span>
													</logic:equal>
												</logic:equal>
											</logic:iterate>
										</logic:present> <logic:present name="UserDetails" scope="session">
											<logic:iterate id="daymap" name="UserDetails"
												property="permissionmap" scope="session">
												<logic:equal value="ACCYRDEL" name="daymap" property="key">
													<logic:equal value="true" name="daymap" property="value">

														<span data-toggle="modal" data-target="#inActiveModel" class="btn btn-xs btn-primary margin-t-5 inactive" onclick="inactive(this);"
															title="Active/Inactive"><span
															class="glyphicon glyphicon-link"></span> Inactive</span>
													</logic:equal>
												</logic:equal>
											</logic:iterate>
										</logic:present></td>
								</tr>
							</logic:iterate>

						</tbody>
					</table>
					<div class='pagebanner'>
						<select id='show_per_page'>
							<option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option>
						</select> <span class='numberOfItem'></span>
					</div>
					<div class='pagination pagelinks'></div>
				</logic:present>

			</div>
		</div>

	</div>


</body>
</html>