<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>
<script type="text/javascript" src="JS/backOffice/Staff/StaffEmployement.js"></script>
<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
.download:hover{

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
.buttons{

vertical-align:5px;

}
</style>

</head>

<body>

	<div class="content" id="div1">
		<div class="input-group  col-md-12" id="div2">

			<p class="transportheader">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Staff Remuneration</span>
			</p>
		</div>
		<logic:present name="successmessagediv" scope="request">
						<div class="successmessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span><bean:write
											name="successmessagediv" scope="request" /></span></a>
							</div>
						</div>
		</logic:present>
		
		
						<logic:present name="errorMessage" scope="request">

							<div class="successmessagediv" align="center">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-warning bg-msg-warning"><span>
											<bean:write name="errorMessage" scope="request" />
									</span></a>
								</div>

							</div>

						</logic:present>
		
			<div class="errormessagediv"  align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000;vertical-align: middle;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Staff
						Remuneration Details
					</h3></a>
					
				<div class="navbar-right" >
<!-- 					<img src="images/rightline.png" class="rightimg">
 -->					
					 	
					 	
					 	
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="STFFEMPADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span id="editTeacher" class="buttons">Staff Salary Update</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
					
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="STFFEMPDIS" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span id="itdeclare" class="buttons">IT Declaration</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
					 	
					<%-- <logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="STFFEMPDWN" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present> --%>
					  
                       
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
	
	<input type="hidden" id="historylocId"
    value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId" /></logic:present>" />
	
	<input type="hidden" id="historydepartment"
    value="<logic:present name="historydepartment" scope="request"><bean:write name="historydepartment" /></logic:present>" />
    
    <input type="hidden" id="historysearchname"
    value="<logic:present name="historysearchname" scope="request"><bean:write name="historysearchname" /></logic:present>" />
    
    <input type="hidden" id="salaryupdate"
    value="<logic:present name="salaryupdate" scope="request"><bean:write name="salaryupdate" /></logic:present>" />
	
	<input type="hidden" id="Itdeclaration"
    value="<logic:present name="Itdeclaration" scope="request"><bean:write name="Itdeclaration" /></logic:present>" />
    		 
			 <div class="panel-body" id="tabletxt" style="padding: 15px;">
			   <div class="row">
			      <div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
									<select id="locId" name="locId"
										class="form-control" required>
									   
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
									style="text-align: right; line-height: 35px;">Search</label>
									<div class="col-xs-7">
							<input type="text" class="form-control" id="searchname" Placeholder="Search......" onkeypress="handle(event)">
							</div>
			<!-- <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="searchbtn" style="padding-top: 3px;">
					<i class="fa fa-search"></i>
				</button>
			</span> -->
			
		</div>
		<div class="form-group clearfix">
						<div class='col-xs-5'>
						</div>
					<div class='col-xs-7' style="text-align: left;">
					<span class="buttons" id="searchbtn" style="font-weight: normal;">Search</span>  
					<span class="buttons" id="resetbtn" style="font-weight: normal;">Reset</span>
					</div></div>
						</div>
						
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Department</label>
								<div class="col-xs-7">
									<select id="department" name="department" class="form-control"required>
									   <option value="all">ALL</option>
									</select>
								</div>
							</div>
						</div>
				  </div>
			 </div>
			 
				<div id="collapseOne" class="accordion-body collapse in">
				 <div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<!-- <table class="table" id="allstudent"> -->

						<%-- <logic:present name="allTeacherDetailsList" scope="request"> --%>
						<table class="table allstaff" id="allstudent">
							<thead>
							<tr>
							<!-- <th><input type='checkbox' name='selectall' id='selectall' onClick='selectAll()'/></th> -->
							<th>Select</th>
							<th>Staff Id</th>
							<th>Staff Name </th>
							<th>Department</th>
							<th>Account Number</th>
							<th>PF/UAN Number</th>
							<th>CTC</th>
							
							</tr>
							</thead>
							<tbody>
							<%-- <logic:iterate id="allTeacherDetailsList" name="allTeacherDetailsList">
								<tr id="">
									<td><input type='radio' name='select' class='select' style='text-align: center' id='<bean:write name="allTeacherDetailsList" property='teacherId'/>' /></td>
									<td><bean:write name="allTeacherDetailsList" property='registartionNo'/></td>
									<td><bean:write name="allTeacherDetailsList" property='teacherName'/></td>
									<td><bean:write name="allTeacherDetailsList" property='department'/></td>
									<td><bean:write name="allTeacherDetailsList" property='bankaccountNo'/></td>
									<td><bean:write name="allTeacherDetailsList" property='pfnumber'/></td>
									<td class="ctc"><bean:write name="allTeacherDetailsList" property='ctc'/></td>
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
	
	 
</body>
</html>