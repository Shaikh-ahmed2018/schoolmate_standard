<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <script type="text/javascript" src="JS/backOffice/Admin/PasswordMaintenance.js"></script>

</head>

<body>
 
 <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>

<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

<div class="content" id="div1">
<div id="dialog"></div>
	<div class="col-md-12 input-group" id="div2">
		<p>
			<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Password Maintenance</span>
		</p>
	</div>
	<input type="hidden" id="hsearchTextId" value="<logic:present name="SearchText"><bean:write name="SearchText" /></logic:present>"/>
	<input type="hidden" id="htype" value="<logic:present name="Type"><bean:write name="Type" /></logic:present>"/>

		 <input type="hidden" name="headenuserId" id="headenuserId" />
		
								<div class="successmessagediv" style="display: none; margin-left:-50px;width: 100%;">
								<div class="message-item">
									<a href="#" class="msg-success bg-msg-succes" style="text-align: center;"><span class="successmessage" style="text-align: center;"></span></a>
								</div>
						</div>
	      <div class="panel panel-primary">
			<div class="panel-heading clearfix">
				
					<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" >
					<h3	class="panel-title" style="color: #000000;"><span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Password Maintenance</h3></a>

				<div class="navbar-right" >

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="PWDUPD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<span class="buttons" id="Edit">Edit</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>


						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="PWMDEL" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<span class="buttons" id="delete">Block</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

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
							<h3 class="modal-title" id="myModalLabel">Download</h3>
						</div>
						<div class="modal-body">
							<span id="excelDownload"><img src="images/xl.png"
								class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>

    <input type="hidden" id="currentstatus" name="currentstatus" 
    value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />

    <input type="hidden" id="historytypename" 
	value='<logic:present name="historytypename" scope="request"><bean:write name="historytypename"/></logic:present>'></input>
	
	<input type="hidden" id="historystatus" 
	value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>'></input>
	
	<input type="hidden" id="historysearchTextId" 
	value='<logic:present name="historysearchTextId" scope="request"><bean:write name="historysearchTextId"/></logic:present>'></input>

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					
					<div class="col-md-6"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top:8px;">

					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Type</label>
						<div class="col-xs-7">
							<select id="typename" name="typename" class="form-control">
								<option value="all">----------Select----------</option>
                                <option value="TEA">Teacher</option>
						        <option value="PAR">Parent</option>
							</select>
						</div>
					</div>
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
							style="text-align:right; line-height: 35px;">Search</label>
							<div class="col-xs-7">
							<input type="text" class="form-control" id="searchTextId" Placeholder="Search......" 
							value="<logic:present name="searchname"><bean:write name="searchname" /></logic:present>">
									</div>
							</div>
					
					<div class="form-group clearfix">
					
					<div class="col-xs-5"></div>
					<div class="col-xs-7">
								<span  class="buttons" id="search">Search</span>
								<span  class="buttons" id="resetbtn">Reset</span>
								</div>
								</div>
					
				</div>
					<div class="col-md-6"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top:8px;">
					<div class="form-group clearfix">
						<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Status</label>
								<div class="col-xs-7">
									<select name="status" tabindex="1" id="status"
										class="form-control">
										<option value="Y" selected>Active</option>
										<option value="N">Block</option>
									</select>
								</div>
							</div>
					</div>
				</div>
				
                    <logic:present name="userRecords">
					<table class="table" id="allstudent">
					         <thead>
					         <tr>
					         <th><input type='checkbox' name='selectall' id='selectall' style='text-align: center'/></th>
					          <th>Name</th>
					           <th>Designation</th>
					            <th>User Name</th>
					             <th>Mobile No</th>
					             </tr>
					         </thead>
					         <tbody>
					         <logic:iterate id="userRecords" name="userRecords">
							<tr>
						    <td><input type='checkbox' name='select' class='select' style='text-align: center' id="<bean:write property="userId" name="userRecords" ></bean:write>"></input></td>
							<td><bean:write property="firstName" name="userRecords"></bean:write></td>
							<td><bean:write property="designation" name="userRecords" ></bean:write></td>
							<td><bean:write property="userName"  name="userRecords"></bean:write></td>
							<td><bean:write property="mobile" name="userRecords"></bean:write></td>
							
						</tr>
						</logic:iterate>
						</tbody>
					</table>
					</logic:present>
                <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
                	<span  class='numberOfItem'></span>	
                	</div><div class='pagination pagelinks'></div>

				</div>
				<br />
			</div>
			</div>
</div>

</body>
</html>
