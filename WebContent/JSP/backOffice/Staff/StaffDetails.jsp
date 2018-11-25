<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/backOffice/Staff/StaffDetails.js"></script>
<style>
.download:hover {
	cursor: pointer; 
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
</style>
<style>
.buttons {
	vertical-align: 5px;
}
</style>
</head>

<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="content" id="div1">
		<div id="dialog"></div>
		
		
		<div class="col-md-12 input-group" id="div2">
			<p class="transportheader">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Staff Registration</span>
			</p>
		</div>
				<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>

			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
									name="successmessagediv" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>
			
			<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
			

		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Staff
						Registration
					</h3></a>
				<div class="navbar-right">

             <logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="STFFADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
								<a href="teacherregistration.html?method=getTeachers"> <span class="buttons">New</span></a>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
					
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="STAFFUPD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
								 <span class="buttons" id="editTeacher" style="cursor: pointer">Modify</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
					
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="STAFFDEL" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
								 <span class="buttons" id="deleteTeacher" style="cursor: pointer">InActive</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>


					<span class="buttons" id="iconsimg" data-toggle="modal"
						data-target="#myModal">Download </span>

				</div>
				
			</div>
			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
						
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="STAFFDWN" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
								<button type="button" class="close" data-dismiss="modal"
								aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h3 class="modal-title" id="myModalLabel">Download</h3>
								
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
							
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

    <input type="hidden" id="currentstatus"
	 value="<logic:present name="currentstatus" scope="request" ><bean:write name="currentstatus"/></logic:present>"/>

    <input type="hidden" id="historylocId"
	 value="<logic:present name="historylocId" scope="request" ><bean:write name="historylocId"/></logic:present>"/>
    
     <input type="hidden" id="historydepName"
	 value="<logic:present name="historydepName" scope="request" ><bean:write name="historydepName"/></logic:present>"/>
	 
	 <input type="hidden" id="historydesigId"
	 value="<logic:present name="historydesigId" scope="request" ><bean:write name="historydesigId"/></logic:present>"/>
	 
	 <input type="hidden" id="historysearchvalue"
	 value="<logic:present name="historysearchvalue" scope="request" ><bean:write name="historysearchvalue"/></logic:present>"/>
	 
	 <input type="hidden" id="historystatus"
	 value="<logic:present name="historystatus" scope="request" ><bean:write name="historystatus"/></logic:present>"/>			
			
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: -15px;">
					<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
															
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Designation</label>
									<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()" 
											name="desgname" id="designameid">
											<option value="all">ALL</option>
												<logic:present name="designattionlist">
												<logic:iterate id="designame" name="designattionlist">
													<option value="<bean:write name="designame" property="desgid"/>"><bean:write name="designame" property="desgname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									
									</div>
									
								</div>
													
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" 
										value="<logic:present name="searchTerm"><bean:write name="searchTerm" /></logic:present>">
									</div>
								</div>
								
								
								<div class="form-group clearfix">
								<div class="col-xs-5"></div>
									<div class="col-xs-7">
										<span type="button" class="buttons" id="search" >Search</span>
										<span type="button" class="buttons" id="resetbtn" >Reset</span>
									</div>
								</div>
								
								
								
								
							</div>
					
					    <div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Department</label>
									<div class="col-xs-7">
										<select id="depName" name="depName" class="form-control" required>
											<option value="All">ALL</option>
											<logic:present name="deptlist">
												<logic:iterate id="deptname" name="deptlist">
													<option value="<bean:write name="deptname" property="depId"/>"><bean:write name="deptname" property="depName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
				                
				                <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Status</label>
									<div class="col-xs-7">
										<select id="status" name="status" class="form-control" required>
											<option value="Y">Active</option>
											<option value="N">InActive</option>
										</select>
									</div>
								</div>
								
							
								<!-- <button type="reset" class="btn btn-info" id="resetbtn" >Reset</button></p> -->
							</div>
					
					
					
				
						
						<%-- <logic:present name="allTeacherDetailsList" scope="request"> --%>
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' style='text-align:center' id='selectall' /></th>
							<th>Staff Id</th>
							<th>Staff Name</th>
							<th>Mobile Number</th>
							<th>Qualification</th>
							<th>Designation</th>
							<th>Email-ID</th>
							<th>Remarks</th>
							</tr>
							</thead>
							<tbody>
							<%-- <logic:iterate id="allTeacherDetailsList" name="allTeacherDetailsList">
								<tr>
								<td><input type='checkbox' name='select' class='select'style='text-align: center' id='<bean:write name="allTeacherDetailsList" property='teacherId'/>' /></td>
								<td><bean:write name="allTeacherDetailsList" property='registartionNo'/></td>
								<td><bean:write name="allTeacherDetailsList" property='teacherName'/></td>
								<td><bean:write name="allTeacherDetailsList" property='mobileNo'/></td>
								<td><bean:write name="allTeacherDetailsList" property='qualification'/></td>
								<td><bean:write name="allTeacherDetailsList" property='designation'/></td>
								<td><bean:write name="allTeacherDetailsList" property='email'/></td>
								<td><bean:write name="allTeacherDetailsList" property='remark'/></td>
								</tr>
							</logic:iterate> --%>
							</tbody>
						</table>
					<%-- </logic:present> --%>
							
                      <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
                      	<span  class='numberOfItem'></span>	
                      	</div><div class='pagination pagelinks'></div>
					

				</div>
				<br />
			</div>
		</div>
	</div>

<!-- 	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script> -->
</body>
