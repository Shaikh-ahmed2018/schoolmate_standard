<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <script type="text/javascript" src="JS/backOffice/Settings/Caste.js"></script>
</head>

<body>

	<div class="content" id="div1">
		<div id="dialog"></div>
		<div class="col-md-12 input-group" id="div2">
		</div>
			<input type="hidden" name="searchterm" class="searchtermclass"
				id="searchexamid"
				value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" /></logic:present>'></input>

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
					<h3 class="panel-title" style="color: #000000;">Caste Details</h3>

					<div class="navbar-right">
					
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CASCRE" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
											<span id="savebutton" class="btn btn-xs btn-primary margin-t-5" data-toggle="modal" data-target="#myModal">
											<span class="glyphicon glyphicon-plus" ></span>Add New Caste</span>				
					
					 					 </logic:equal>
									  </logic:equal>
								  </logic:iterate>
							  </logic:present>

					<!-- edit & delete-->
					<input type="hidden" id="editPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="CASUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
						<input type="hidden" id="delPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="CASDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
					<!--  :ends-->	

					</div>
				</div>
				<!-- pop up -->
    
    <input type="hidden" id="currentstatus" name="currentstatus" 
    value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />

    <input type="hidden" id="historysearchname" 
	  value='<logic:present name="historysearchname" scope="request"><bean:write name="historysearchname" /></logic:present>'></input>
	
	 <input type="hidden" id="historystatus" 
	  value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus" /></logic:present>'></input>


				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h3 class="modal-title" id="myModalLabel">Caste Details</h3>
							</div>
							<div class="modal-body">
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Religion</label>
									<div class="col-xs-7">
										<select name="main_religion" id="main_religion"
											class="form-control" onchange="HideError(this)">
											<option value="">-------Select------</option>
											<logic:present name="religiondetails">
												<logic:iterate id="religiondetails" name="religiondetails">
													<option
														value='<bean:write name="religiondetails" property="religionId"/>'>
														<bean:write name="religiondetails" property="religion" />
													</option>
												</logic:iterate>
											</logic:present>
										</select> 
									</div>
								</div>
								
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Caste</label>
								<div class="col-xs-7">
									<input type="text" name="religion" id="religionNameId"
										onClick="HideError(this)" class="form-control" placeholder=""
										maxlength="30"
										value=''></input>
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

				<input type="hidden" id="hiddenstatus" name="hiddenstatus"
					value="<logic:present name='status' scope='request' ><bean:write name='status'/></logic:present>" />
	

		<div id="collapseOne" class="accordion-body collapse in">
			<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-bottom: 10px">
					
				<div class="col-md-6"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Status</label>
						<div class="col-xs-7">
							<select id="status" name="status" class="form-control">
								<option value="Y">Active</option>
								<option value="N">InActive</option>
							</select>
						</div>
					</div>
					<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Search</label>
								<div class="col-xs-7">
								<input type="text" name="searchname" id="searchname"
										class="form-control" Placeholder="Search......">
								</div>
				<!-- <span class="input-group-btn">
					<button class="btn btn-default" type="button" id="search">
						<i class="fa fa-search"></i>
					</button>
				</span> -->
					</div>
					<div class="form-group clearfix"> 
						<div class="col-xs-5">
							</div>
						<div class="col-xs-7" style="text-align: left;">
								<span class="buttons" id="search" style="font-weight: normal;">Search</span>  
								<span class="buttons" id="resetbtn" style="font-weight: normal;">Reset</span>
						</div></div>
				</div>
				<logic:present name="religionList" scope="request">
					<table class="table" id="allstudent">
						<thead>
							<tr>
								<th><input type='checkbox' name='selectall' id='selectall'
									style='text-align: center' /></th>
								<th>Religion</th>
								<th>Caste</th>
								<th>Remarks</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name='religionList' id="religionList">
								<tr>
									<td><input type='checkbox' name='select' class='select'
										style='text-align: center'
										id='<bean:write name='religionList' property="casteId"/>' /></td>
									<td><bean:write name='religionList' property="religion" /></td>
									<td><bean:write name='religionList' property="caste" /></td>
									<td><bean:write name='religionList' property="remarks" /></td>
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

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>