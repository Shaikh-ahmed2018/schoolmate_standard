<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>
 <script type="text/javascript" src="JS/backOffice/Settings/EditDepartmentDetails.js"></script>
</head>
<body>
	<div class="content" id="div1">
		<div id="dialog"></div>
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


			<div class="panel-heading clearfix">
				<h3 class="panel-title" style="color: #000000;">Department</h3>
				
				<div class="navbar-right">
				<!-- add btn -->
				<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="DEPADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
											<span id="savebutton" class="btn btn-xs btn-primary margin-t-5" data-toggle="modal" data-target="#myModal">
											<span class="glyphicon glyphicon-plus" ></span>Add Department</span>				
					
					 					 </logic:equal>
									  </logic:equal>
								  </logic:iterate>
							  </logic:present>
					<!-- : ends-->
					
					<!-- edit & delete-->
					<input type="hidden" id="editPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="DEPUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
						<input type="hidden" id="delPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="DEPDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
					<!--  :ends-->	
				</div>

			</div>
			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="myModalLabel">Download</h3>
						</div>
						<div class="modal-body">
						<div class="panel-body clearfix">
						<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Department Name</label>
									<div class="col-xs-7">
									<input type="text" name="departmentname" onkeypress="HideError()" class="form-control" id="departmentid" 
									value='<logic:present name="editlist"><bean:write name="editlist" property="depName" /></logic:present>'></input>
									</div>
								</div>
						
								
					                              
							  <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description
										</label>
									<div class="col-xs-7">
										<textarea style="resize:none;margin-bottom: 5px;" rows="4" cols="25"  class="form-control"
											name="description" id="descriptionid" ><logic:present name="editlist"><bean:write name="editlist" property="desc"/></logic:present></textarea>
									</div>
								</div>
						</div>
					</div>
						<div class="modal-footer">
						<span id="save" class="buttons button-blue">Save</span>
          				<span class="buttons button-simple" data-dismiss="modal">Close</span>
       				 </div>
					
				</div>
			</div>

   <input type="hidden" id="currentstatus" name="currentstatus" 
    value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />

   <input type="hidden" id="hiddenstatus" 
	value='<logic:present name="status" scope="request"><bean:write name="status"/></logic:present>'></input>
   
   <input type="hidden" id="hiddensearchname" 
	value='<logic:present name="searchname" scope="request"><bean:write name="searchname"/></logic:present>'></input>
               
               

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
                     <div class=row>
                     <div class="col-md-6">
                     <div class="form-group clearfix">
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
							
							<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Search</label>
						<div class="col-xs-7">			
					<input type="text" class="form-control" name="searchname"
						id="searchname" Placeholder="Search......" >
					</div>
					<!-- <span class="input-group-btn">
						<button class="btn btn-default" type="button"  id="search" style="height: 27px;">
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
                     
                     
					<logic:present name="DepartmentDetails" scope="request">

						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th><input type='checkbox' name='selectall' id='selectall'
										style='text-align: center'/></th>
									<th>Department Name</th>
									<th>Description</th>
									<th>Remark</th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name='DepartmentDetails' id="DepartmentDetails">
									<tr>
										<td><input type='checkbox' name='select' class='select'
											style='text-align: center'
											id='<bean:write name='DepartmentDetails' property="depId"/>' /></td>
										<td><bean:write name='DepartmentDetails'
												property="depName" /></td>
										<td><bean:write name='DepartmentDetails' property="desc" /></td>
										<td><bean:write name='DepartmentDetails' property="remark" /></td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>

					</logic:present>
					<div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select> <span class='numberOfItem'></span>
					</div>
					<div class='pagination pagelinks'></div>

              <input type="hidden" name=hiddendeptId id="hiddendeptId">

				</div>
				<br />
			</div>
	</div>



</body>
</html>