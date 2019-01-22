<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <script type="text/javascript" src="JS/backOffice/Settings/classList.js"></script>
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
				<h3 class="panel-title class" style="color: #000000;">Class Details 
					</h3>

				<div class="navbar-right">
				<!-- add btn -->
				<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CLSADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
											<span id="savebutton" class="btn btn-xs btn-primary margin-t-5" data-toggle="modal" data-target="#myModal">
											<span class="glyphicon glyphicon-plus" ></span>Add New Class</span>				
					
					 					 </logic:equal>
									  </logic:equal>
								  </logic:iterate>
							  </logic:present>
					<!-- : ends-->
					
					<!-- edit & delete-->
					<input type="hidden" id="editPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="CLSUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
						<input type="hidden" id="delPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="CLSDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
					<!--  :ends-->	
				</div>
			</div>
			<!-- pop up -->
			
			
			<div  class="content-div">
			<div class="row">
			<div class="col-md-4" style="font-family: font-size: 13px; color: #000;">
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
					</div>
					<div class="col-md-4" style="font-size: 13px; color: #000; ">
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
			
			<div class="col-md-4" style="font-size: 13px; color: #000; ">
			
			<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"> Class Status</label>
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

				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h3 class="modal-title" id="myModalLabel">Class Details</h3>
							</div>
							<!-- body:popup  -->
							<div class="modal-body">
								<div class="panel-body clearfix">
								<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
								<select id="location" name="location" class="form-control" onchange="HideError(this)">
								<logic:present name="locationList">
									<logic:iterate id="Location" name="locationList">
										<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
						
						</select>
						</div>
							</div>
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Select Stream</label>
									<div class="col-xs-7">
									<select class="form-control" name="stream" onchange="HideError(this)" id="streamName">
										<option value="">-------------Select-----------</option>
									</select>
									</div>
								</div>
					
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class
										Name</label>
									<div class="col-xs-7">
									<select class="form-control" name="class" onchange="HideError(this)" id="classId">
									<option value="">-------------Select-----------</option>
									<option value="1">Pre-KG</option>
									<option value="2">LKG</option>
									<option value="3">UKG</option>
									<option value="4">I</option>
									<option value="5">II</option>
									<option value="6">III</option>
									<option value="7">IV</option>
									<option value="8">V</option>
									<option value="9">VI</option>
									<option value="10">VII</option>
									<option value="11">VIII</option>
									<option value="12">IX</option>
									<option value="13">X</option>
									<option value="14">XI</option>
									<option value="15">XII</option>
									</select>
									</div>	
									<div class="col-xs-7">	
											<input type="hidden" name="status" id="statusId" value=""/>
											<input type="hidden" name="updateClassCode" id="updateClassCode" value=""/>
											<input type="hidden" name="hiddenStreamId" id="hiddenStreamId" value=""/>
											<input type="hidden" name="hiddenclassname" id="hiddenclassname" value=""/>
									</div>
								</div>
								
						<input type="hidden" id="actionstatus" value="add" />		
						</div>
							</div>
							<!-- model body:ends -->
						
						<div class="modal-footer">
						<span id="save" class="buttons button-blue">Save</span>
          				<span class="buttons button-simple" data-dismiss="modal">Close</span>
       				 </div>

						</div>
					</div>
				</div>
		
	<input type="hidden" id="cuur_loc" name="cuur_loc" 
			value="<logic:present name="cuur_loc" scope="request"><bean:write name="cuur_loc"/></logic:present>" />			
			
	<input type="hidden" id="currentstatus" name="currentstatus" 
			value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />			
				
	<input type="hidden" name="hiddenlocId" id="hiddenlocId" 
		 value='<logic:present name="locId" scope="request"><bean:write name="locId" /></logic:present>'></input>
	
	<input type="hidden" name="hiddenstreamId" id="hiddenstreamId" 
		 value='<logic:present name="streamId" scope="request"><bean:write name="streamId" /></logic:present>'></input>
	
	<input type="hidden" name="hiddenstatus" id="hiddenstatus" 
		 value='<logic:present name="status" scope="request"><bean:write name="status" /></logic:present>'></input>			

				<div id="collapseOne_NEW" class="content-div">
					<div class="panel-body"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">


							<table class="table" id="allstudent">
								<thead>
									<tr>
										<th>S.No.</th>
										<th>Branch</th>
										<th>Stream</th>
										<th>Class</th>
										<th>Action</th>
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
							<div class='pagination pagelinks' style="margin-bottom: 10px;"></div>
						<%-- </logic:present> --%>

					</div>
				</div>

	</div>

</body>
</html>