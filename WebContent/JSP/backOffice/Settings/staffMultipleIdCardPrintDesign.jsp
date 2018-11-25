<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>   
 <script type="text/javascript" src="JS/backOffice/Student/staffMultiIDcardPrint.js"></script>

</head>

<body>

	<div class="content" id="div1">
	<div class="searchWrap">
		<div  id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Print Staff ID Card - Multiple</span>
			</p>
		</div>
		
	</div>
		
		<input type="hidden" id="succmsg" value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />
		
			<div id="successmessages" class="successmessagediv" style="display: none;">
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"><logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present></span></a>
				</div>
			</div>
		
			
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><logic:present name='errorMessage'  scope='request' ><bean:write name='errorMessage'  /></logic:present></span></a>
				</div>
			</div>
		
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" >
					<h3 class="panel-title" style="color: #000000; vertical-align: middle;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Staff Details
					</h3></a>
				<div class="navbar-right">
				<!-- 	<a href="menuslist.html?method=PrintPreviewStaffMultipleIDCard">IDCardMenu -->
					
					<span id="editStudent" class="buttons">Print</span>
					</a>
				</div>
			</div>
			<!-- pop up -->

   <input type="hidden" id="historyacademicId"
	 value='<logic:present name="historyacademicId" scope="request" ><bean:write name="historyacademicId" /></logic:present>' />
   
    <input type="hidden" id="historylocId"
	 value='<logic:present name="historylocId" scope="request" ><bean:write name="historylocId" /></logic:present>' />
	 
	<input type="hidden" id="historysearchvalue"
	 value='<logic:present name="historysearchvalue" scope="request" ><bean:write name="historysearchvalue" /></logic:present>' />
	 
	<input type="hidden" id="historydepartId"
	 value='<logic:present name="historydepartId" scope="request" ><bean:write name="historydepartId" /></logic:present>' />

<input type="hidden" id="historydesigId"
	 value='<logic:present name="historydesigId" scope="request" ><bean:write name="historydesigId" /></logic:present>' />
	 
<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt" style="padding: 15px;">

							<div class="col-md-6" id="txtstyle">
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Branch</label>
									<div class="col-xs-7">
										<select id="locationname" name="location" class="form-control"
											required>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>">
																   <bean:write name="Location" property="locationName" />
													</option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
									
									
									
									
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Department</label>
										<div class="col-xs-7">
										<select name="department" id="department"
											onkeypress="HideError()" class="form-control">
											<option value="">ALL</option>
												<logic:present name="department">
												<logic:iterate id="department" name="department">
													<option value="<bean:write name="Location" property="locationId"/>">
																   <bean:write name="Location" property="locationName" />
													</option>
												</logic:iterate>
												</logic:present>
										</select>
									</div>
									</div>
							
																			
							<div class="form-group ">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Search</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" >
									</div>
								</div>
					<div  style ="margin-left:188px; margin-top:44px;margin-bottom: 10px;">	
					<span class="buttons" id="search">Search</span>
					<span class="buttons" id="resetbtn">Reset</span>
						</div>

							
							</div>
							<div class="col-md-6" id="txtstyle">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
								<div class="col-xs-7">
										<select id="accyear" name="accyear" class="form-control" required="required">
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option value="<bean:write name="AccYear" property="accyearId"/>">
													<bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
					
								
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Designation</label>
										<div class="col-xs-7">
										<select id="designation" name="designation" class="form-control" required="required">
											<option value="">ALL</option>
										</select>
									</div>
								</div>
							
					
								
							</div>

	
<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
 <table class='table' id='allstudent' width='100%' style="margin-top:50px">
 <thead>
 <tr>
 					
 						<th><input type='checkbox' class='selectall'  /></th>
						<th>Staff Id</th>
						<th>Staff Name</th>
						<th>Academic Year</th>
						<th>Branch</th>
						<th>Designation</th>
						<th>Department </th>
						<th>Status</th>
						
						
						</tr>
						</thead>
					<tbody>
					</tbody>
					
			
						</table>
		<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
			<span  class='numberOfItem'></span>	
			</div><div class='pagination pagelinks'></div>
						</div>

 
					</div>
				</div>
		</div>
	</div>

	
</body>
</html>