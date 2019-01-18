<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <script type="text/javascript" src="JS/backOffice/Bank/Bank.js"></script>



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
				<h3 class="panel-title" style="color: #000000;">Bank Master</h3>
				
				<div class="navbar-right">
				<!-- add btn -->
				<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="SLCADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
											<span id="savebutton" class="btn btn-xs btn-primary margin-t-5" data-toggle="modal" data-target="#myModal">
											<span class="glyphicon glyphicon-plus" ></span>Add New Class</span>				
					 					 </logic:equal>
									  </logic:equal>
								  </logic:iterate>
							  </logic:present>
					<!-- : ends-->
					
					<!-- edit & delete-->
					<input type="hidden" id="editPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="SLCUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
						<input type="hidden" id="delPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="SLCDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
					<!--  :ends-->	
				</div>

			</div>
			
			
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h3 class="modal-title" id="myModalLabel">Bank Details</h3>
							</div>
							<!-- body:popup  -->
							<div class="modal-body">
							<div class="panel-body clearfix">
									<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right;" id="inputnames">Bank Name</label>
									<div class="col-xs-7">
										<input type="text" name="bankname" id="bankname"
											class="form-control" maxlength="50" onkeypress="return CheckIsNumeric1(event);" 
											value="<logic:present name="bankrecord" scope="request"><bean:write name="bankrecord" property="name" ></bean:write></logic:present>" />
									</div>
								</div>
                                  
                                  <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right;" 	id="inputnames">Short Name</label>
									<div class="col-xs-7">
										<input type="text" name="bankshortname" id="bankshortname"
											maxlength="6" class="form-control" onkeypress="return CheckIsNumeric(event);" 
											value="<logic:present name="bankrecord" scope="request"><bean:write name="bankrecord" property="shortname" ></bean:write></logic:present>" />
									</div>
								</div>
					
								
								
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

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="row">
					<div class="col-md-6"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top: 1%;">
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
								style="text-align: right; line-height: 35px;">Status</label>
						<div class="col-xs-7">
					<input type="text" name="searchtext" id="searchtext" class="form-control"
						Placeholder="Search......" style="height: 35px;">
						</div>
					<!-- <span class="input-group-btn">
						<button class="btn btn-default" type="button" id="search" >
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
				</div>

    <input type="hidden" id="currentstatus" name="currentstatus" 
    value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />

    <input type="hidden" id="historysearchname" 
	value='<logic:present name="searchname1" scope="request"><bean:write name="searchname1" /></logic:present>'></input>
	
	<input type="hidden" id="historystatus" 
	value='<logic:present name="status1" scope="request"><bean:write name="status1" /></logic:present>'></input>

				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

					<logic:present name="banklist" scope="request">

						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th><input type='checkbox' name='selectall' id='selectall' style='text-align: center' onClick='selectAll()' /></th>
									<th>Bank Name</th>
									<th>Bank Short Name</th>
									<th>Remarks</th>
									

								</tr>
							</thead>
							<tbody>
								<logic:iterate name='banklist' id="banklist">
								<tr>
									<td><input type='checkbox' name='select' class='select' style='text-align: center' value='<bean:write name='banklist' property="id"/>' /></td>
												<td><bean:write name='banklist' property="name" /></td>
												<td><bean:write name='banklist' property="shortname" /></td>
												<td><bean:write name='banklist' property="reason" /></td>
												
																									
											</tr>
								</logic:iterate>
							</tbody>

						</table>
					
						<input type='hidden' id = 'bankids'>
					
						<div class='pagebanner'>
							<select id='show_per_page'><option value='50'>50</option>
								<option value='100'>100</option>
								<option value='200'>200</option>
								<option value='300'>300</option>
								<option value='400'>400</option>
								<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
						<div class='pagination pagelinks' style="margin-bottom: 10px;"></div>
						</logic:present>

				</div>

			</div>
	</div>
</body>
</html>