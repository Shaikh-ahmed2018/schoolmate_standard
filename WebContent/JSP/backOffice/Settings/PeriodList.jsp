<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>
 <script type="text/javascript" src="JS/backOffice/Settings/PeriodList.js"></script>
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
				<h3 class="panel-title class" style="color: #000000;">Period Details</h3>
				<div class="navbar-right">
				<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CLSADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
											<span id="savebutton" class="btn btn-xs btn-primary margin-t-5" data-toggle="modal" data-target="#myModal">
											<span class="glyphicon glyphicon-plus" ></span>Add New Period</span>				
					
					 					 </logic:equal>
									  </logic:equal>
								  </logic:iterate>
							  </logic:present>
							  
							  <!-- edit & delete-->
					<input type="hidden" id="editPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="CLSUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
						<input type="hidden" id="delPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="CLSDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
					<!--  :ends-->	
				</div>

			</div>
			<!-- pop up -->

			<div id="classOne" class="accordion-body collapse in">
				<div class="col-md-6"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 1%;">
					
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Branch</label>
						<div class="col-xs-7">
							<select id="locationname" name="locId" class="form-control" required>
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
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
								
					           <select name="clsId" id="classId"
						         class="form-control" onchange="HideError(this)">
							   <option value="">ALL</option>
					          </select>
									</div>
								</div>
					
					

					
				</div>

				<div class="col-md-6"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 1%;">
                  
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

				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h3 class="modal-title" id="myModalLabel">Period Details</h3>
							</div>
							<div class="modal-body">
									<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locationname" name="locId" class="form-control"
											onchange="HideError(this)">
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
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Stream </label>
									<div class="col-xs-7">

										<select name="streamId" id="StreamName" class="form-control"
											onchange="HideError(this)">
											<option value="">-------Select------</option>
										</select>

										<%-- <input type="text" class="form-control" id="sectionName" onclick="HideError(this)"
											placeholder="" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="secDetailsName"/></logic:present>"/> --%>

									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">

										<select name="clsId" id="classId" class="form-control"
											onchange="HideError(this)">
											<option value="">-------Select------</option>
										</select>
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Number
										of Period</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="noofperiod"
											id="noofPeriod" onclick="HideError(this)"
											onkeypress="return CheckIsNumeric(event);"
											value='<logic:present name="editelist"><bean:write name="editelist" property="noofperiod"/></logic:present>' />

									</div>
								</div>
								<input type="hidden" name="locId" id="hiddenlocId"
									value="<logic:present name="editelist" ><bean:write name="editelist" property="locId"/></logic:present>" />
								<input type="hidden" name="streamId" id="hiddenstreamId"
									value="<logic:present name="editelist" ><bean:write name="editelist" property="streamId"/></logic:present>" />
								<input type="hidden" name="clsId" id="hiddenclsId"
									value="<logic:present name="editelist" ><bean:write name="editelist" property="clsId"/></logic:present>" />
								<input type="hidden" name="hiddenperiodId" id="hiddenperiodId"
									value="<logic:present name="editelist" ><bean:write name="editelist" property="slno"/></logic:present>" />
								

						</div>
						<div class="modal-footer">
						<span id="save" class="buttons button-blue">Save</span>
          				<span class="buttons button-simple" data-dismiss="modal">Close</span>
       				 </div>
					</div>
				</div>
				
				<input type='hidden' id='curr_loc' value='<logic:present name='curr_loc'><bean:write name='curr_loc'/></logic:present>'>

				<div id="classOne" class="accordion-body collapse in">
					<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					
                     
							<table class="table" id="allstudent">
								<thead>
									<tr>
										<th><input type='checkbox' name='selectall' id='selectall' style='text-align: center'onClick='selectAll()' /></th>
										<th>Branch</th>
										<th>Stream</th>
										<th>Class</th>
										<th>No.of Periods</th>
									</tr>
								</thead>
								<tbody>
									<logic:present name="periodList" scope="request">
										<logic:iterate id='periodList' name='periodList'>
											<tr>
												<td><input type='checkbox' class='select' id='<bean:write name='periodList' property='slno'/>'></td>
												<td><bean:write name='periodList' property='locname'/></td>
												<td><bean:write name='periodList' property='streamname'/></td>
												<td><bean:write name='periodList' property='clsname'/></td>
												<td><bean:write name='periodList' property='noofperiod'/></td>
											</tr>
										</logic:iterate>
									</logic:present>
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
					

					</div>
				</div>
			</div>
		

	</div>
	</div>

</body>
</html>