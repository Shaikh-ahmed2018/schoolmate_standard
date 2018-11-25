<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <script type="text/javascript" src="JS/backOffice/Settings/sectionList.js"></script>

<script type="text/javascript">
	function handle(e) {
		var searchText = $("#searchValue").val().trim();
		if (e.keyCode == 13) {
			e.preventDefault(); // Ensure it is only this code that rusn
			window.location.href = "menuslist.html?method=sectionList&searchText="
					+ searchText + "&school=" + $('#school').val();
		}
	}
</script>


</head>

<body>
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
				<h3 class="panel-title section" style="color: #000000;">Division Details</h3>

				<div class="navbar-right">
				
				<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="SECADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
											<span id="savebutton" class="btn btn-xs btn-primary margin-t-5" data-toggle="modal" data-target="#myModal">
											<span class="glyphicon glyphicon-plus" ></span>Add New Division</span>				
					
					 					 </logic:equal>
									  </logic:equal>
								  </logic:iterate>
							  </logic:present>
							  
							  
						<input type="hidden" id="editPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="SECUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
						<input type="hidden" id="delPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="SECDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
							
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
							
							<h3 class="modal-title" id="myModalLabel">Division Details</h3>
						</div>
						<div class="modal-body">
						<div class="panel-body clearfix">
						 <div class="clearfix">  
								
								<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
						<select id="locationname" name="location" class="form-control" onchange="HideError(this)">
							
								<logic:present name="locationList">
									<logic:iterate id="Location" name="locationList">
										<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
						
						</select>
						</div>
								<input type="hidden" name="schoolId" class="form-control" onclick="HideError(this)" id="schoolId" value='<logic:present name="editClasslist"><bean:write name="editClasslist" property="locationId" /></logic:present>'></input>
							</div>
					
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Division Name</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="sectionName" onclick="HideError(this)" onkeyup = 'toCaseChange(this)' onkeypress='return alphaOnly(event);' maxlength="1" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="secDetailsName"/></logic:present>"/>
									</div>
								</div>
					
								
										<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Select Class</label>
									<div class="col-xs-7">
									<!-- 	<input type="text" class="form-control" id="inputEmail"
											placeholder="First Name" required> -->
									<select name="class" id="classId"
										class="form-control" onchange="HideError(this)">
											<option value="">-------------------Select-------------------</option>
									</select>
													</div>
								</div>
							
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Division
										Strength</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="sectionStrength" onclick="HideError(this)"
											placeholder="" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="sectionStrength"/></logic:present>"/>
											
									</div>
								</div>
								
									<input type="hidden" name="status" id="statusId" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="status"/></logic:present>"/>
											<input type="hidden" name="updateClassCode" id="updateClassCode" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="sectionId"/></logic:present>"/>
											<input type="hidden" name="hiddenStreamId" id="hiddenStreamId" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="secDetailsId"/></logic:present>"/>
											<input type="hidden" name="hiddenStreamName" id="hiddenStreamName" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="secDetailsName"/></logic:present>"/>
						
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
	<input type='hidden' id='butstatus'>
	 <input type="hidden" name="curr_loc" id="curr_loc"
	 value='<logic:present name="curr_loc" scope="request"><bean:write name="curr_loc"/></logic:present>'></input>
	
	<input type="hidden" id="currentstatus" name="currentstatus" 
    value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />		
			
	 <input type="hidden" name="hiddenlocId" id="hiddenlocId"
	 value='<logic:present name="locId" scope="request"><bean:write name="locId"/></logic:present>'></input>
	 
	 <input type="hidden" name="hiddenstreamId" id="hiddenstreamId"
	 value='<logic:present name="streamId" scope="request"><bean:write name="streamId"/></logic:present>'></input>				
	
	<input type="hidden" name="hiddenclassname" id="hiddenclassname"
	 value='<logic:present name="classname" scope="request"><bean:write name="classname"/></logic:present>'></input>
	 
	 <input type="hidden" name="hiddenstatus" id="hiddenstatus"
	 value='<logic:present name="status" scope="request"><bean:write name="status"/></logic:present>'></input>



				<div class="content-div">
				<div class="row">
				<div class="col-md-6"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">

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
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;"> Stream</label>
						<div class="col-xs-7">

							<select class="form-control" onkeypress="HideError()"
								name="streamId" id="streamId">
								<option value="">ALL</option>
							</select>

						</div>
					</div>

					
				</div>
				




				<div class="col-md-6"
					style="font-size: 13px; color: #000; margin-top: 10px;">
					
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;"> Class</label>
						<div class="col-xs-7">

							<select class="form-control" onkeypress="HideError()"
								name="classname" id="classname">
								<option value="">ALL</option>
							</select>

						</div>
					</div>
					
					
						<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"> Division Status</label>
								<div class="col-xs-7">
								
								<div class="switch">
								<span id="text-status">Active</span>
								<div class="switch-cirle"></div>
								</div>
									<select style="display: none;" id="status" name="statuss" class="form-control">
										<option value="Y">Active</option>
										<option value="N">InActive</option>
									</select> 
								</div>
							 </div>
				</div>
				</div>
				</div>
				

				<div class="content-div"
					style="font-size: 13px; color: #000; margin-bottom: 10px;">
						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th>S.No</th> <!-- onClick='selectAll()'  -->
									<th>Branch</th>
									<th>Stream</th>
									<th>Class</th>
									<th>Division</th>
									<th>Strength</th>
									<th>Actions</th>
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
						<div class='pagination pagelinks'></div>

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