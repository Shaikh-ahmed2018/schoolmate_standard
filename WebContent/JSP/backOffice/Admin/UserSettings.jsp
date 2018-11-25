<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <script type="text/javascript" src="JS/backOffice/Admin/userRecords.js"></script>



</head>

<body>
<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
	<form id="myForm" action="menuslist.html?method=religionDetails" method="post">
		<div class="content" id="div1">
		<div id="dialog"></div>
			<div class="searchWrap clearfix">
				<div class="col-md-7" id="div2">
					<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">User Details</span>
					</p>
				</div>
			</div>
			
			<div class="successmessagediv" style="display: none; margin-left:-50px;width: 100%;">
				<div class="message-item">
					<a href="#" class="msg-success bg-msg-succes" style="text-align: center;"><span class="successmessage" style="text-align: center;"></span></a>
				</div>
			</div>
			
			<div class="panel panel-primary">
				<div class="panel-heading clearfix">
					<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" >
					<h3	class="panel-title" style="color: #000000;"><span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;User Details</h3></a>

					<div class="navbar-right" >
					
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="USRADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
							<a href="userManagement.html?method=addUsersettings">
							<span class="buttons" style="left: -1px;">New</span></a>
						      </logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
						
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="USRUPD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
						      <span id = "edit" class="buttons" style="left: -1px;">Modify</span>
						        </logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
					
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							 <logic:equal value="USRDEL" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
						    <span class="buttons" id="inactive" style="left: -1px;">Block</span>
						      </logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
						
					</div>
					
				</div>
			<!-- pop up -->
			
			<input type="hidden" id="currentstatus" name="currentstatus" 
            value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />

            <input type="hidden" id="historystatus"
		    value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus" /></logic:present>' />			

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
									<option value="N">Blocked</option>
								</select>
							</div>
						</div>
					</div>
				  </div>
				  
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
                    <logic:present name="userRecords">
						<table class="table" id="allstudent">
					  	 <thead>
					         <tr>
					         <th><input type='checkbox' id='selectall'></input></th>
					          <th>Role</th>
					           <th>UserName</th>
					            <th>Email</th>
					             <th>Mobile No.</th>
					             <th>Remarks</th>
					             </tr>
					      </thead>
					      <tbody>
					        <logic:iterate id="userRecords" name="userRecords">
							<tr>
						    <td><input type='checkbox' class='select' name="userid" id="<bean:write name="userRecords" property="userCode" ></bean:write>,<bean:write name="userRecords" property="auserid" ></bean:write>" /></td>
							<td><bean:write name="userRecords" property="roleName"></bean:write></td>
							<td><bean:write name="userRecords" property="userName"></bean:write></td>
							<td><bean:write name="userRecords" property="email"></bean:write></td>
							<td><bean:write name="userRecords" property="mobile"></bean:write></td>
							<td><bean:write name="userRecords" property="remarks"></bean:write></td>
							</tr>
							</logic:iterate>
						 </tbody>
						</table>
					</logic:present>
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
                	</div>
                	<div class='pagination pagelinks'></div>
				</div>
			</div>
		</div>
	</div>

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</form>
</body>
</html>
