<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en"> 

<head>
  <script type="text/javascript" src="JS/backOffice/Settings/editStream.js"></script>
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
		<div class="successmessagediv"  style="display: none;" >
			<div class="message-item" style="text-align: center;">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

			<div class="panel-heading clearfix">
				<h3 class="panel-title" style="color: #000000;"> Stream Details</h3>

			<div class="navbar-right">
					<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="STRADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
											<span id="savebutton" class="btn btn-xs btn-primary margin-t-5" data-toggle="modal" data-target="#myModal">
											<span class="glyphicon glyphicon-plus" ></span>Add New Stream</span>				
					
					 					 </logic:equal>
									  </logic:equal>
								  </logic:iterate>
							  </logic:present>
					
				</div>
			</div>
						<input type="hidden" id="editPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="STRUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
						<input type="hidden" id="delPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="STRDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
							
			
			<div class="modal fade clearfix" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h3 class="modal-title" id="myModalLabel">Stream Details</h3>
						</div>
						<div class="modal-body">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
							<select id="location" name="location" class="form-control"  onchange="HideError(this)">
							
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
									style="text-align: right; line-height: 35px;">Stream Name </label>
								<div class="col-xs-7">
									<input type="text" name="streamName" id="streamName" onclick="HideError(this)" class="form-control" placeholder=""  value=''></input>
								</div>
								<input type="hidden" name="streamId"  id="streamId" value=''></input>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Description
								</label>
								<div class="col-xs-7">
								<textarea style="resize:none" rows="4" cols="25"  class="form-control" name="description" id="description" ></textarea>
								</div>
							</div>
				
						<input type="hidden" id="actionstatus" value="add" />		
										</div>
					<div class="modal-footer">
						<span id="save" class="buttons button-blue">Save</span>
          				<span class="buttons button-simple" data-dismiss="modal">Close</span>
       				 </div>
					</div>
				</div>
			</div>
				
			<div  class="content-div">
			<div class="row">
			<div class="col-md-6" style="font-family: font-size: 13px; color: #000;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<!-- <option value="all">ALL</option> -->
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
							</div>
			
			<div class="col-md-6" style="font-size: 13px; color: #000; ">
			
			<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"> Branch Status</label>
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
			<div id="collapseOne_NEW" class="content-div">
							
			
			<input type="hidden" id="curr_loc" name="curr_loc" 
			value="<logic:present name="curr_loc" scope="request"><bean:write name="curr_loc"/></logic:present>" />
			
			<input type="hidden" id="hiddenlocId" name="hiddenlocId" 
			value="<logic:present name="locId" scope="request"><bean:write name="locId"/></logic:present>" />
			
			<input type="hidden" id="statuscurrent" name="statuscurrent" 
			value="<logic:present name="statuscurrent" scope="request"><bean:write name="statuscurrent"/></logic:present>" />
				
	 		<input type="hidden" id="hiddenstatus" name="hiddenstatus" 
				value="<logic:present name="status" scope="request"><bean:write name="status"/></logic:present>" />				
			
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-bottom:10px;">

					
					<table class="table" id="allstudent">
						<thead>
						<tr>
						<th>S.No</th>
						<th>Branch</th>
						<th>Stream</th>
						<th>Description</th>
						<th>Action</th>
						</tr>
						</thead>
						<tbody>
						
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
             </select>
	          <span  class='numberOfItem'></span>	
	         </div><div class='pagination pagelinks'></div>
				</div>
			</div>
	</div>


	
</body>
</html>