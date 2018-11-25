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
<title>eCampus Pro</title>
<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/backOffice/Student/StudentRegistrationlist.js"></script>



</head>

<body>
<input type="hidden" id="hSchoolId"
    value="<logic:present name="hloc" scope="request"><bean:write name="hloc"/></logic:present>" />



<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="content" id="div1">
	<div class="searchWrap">
		<div class="" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Student Registration</span>
			</p>
		</div>
		
	</div>
		<div id='dialog'></div>
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
			
		<input type="hidden" id="hiddenstatus" value="<logic:present name="status" ><bean:write name="status" /></logic:present>" />
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion12"	href="#collapseOne" >
					<h3 class="panel-title" style="color: #000000; vertical-align: text-top;"> <span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;New Admission List
					</h3></a>
				<div class="navbar-right">
				<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUADD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<a href="menuslist.html?method=addStudent"><span class="buttons" id="add">New</span></a>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUUPD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<span id="editStudent" class="buttons">Modify</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUDEL" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<span id="trash" class="buttons">InActive</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
								<%-- <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUDWD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%>
					
					
					
					
				</div>
			</div>
			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
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
   
   <input type="hidden" id="currentstatus"
    value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />

   <input type="hidden" id="historystatus"
				value="<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>" />		

   <input type="hidden" id="historyacademicId"
				value="<logic:present name="historyacademicId" scope="request"><bean:write name="historyacademicId"/></logic:present>" />

   <input type="hidden" id="historylocId"
				value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>" />

   <input type="hidden" id="historyclassname"
				value="<logic:present name="historyclassname" scope="request"><bean:write name="historyclassname"/></logic:present>" />

   <input type="hidden" id="historysectionid"
				value="<logic:present name="historysectionid" scope="request"><bean:write name="historysectionid"/></logic:present>" />

   <input type="hidden" id="historysearchvalue"
	 value="<logic:present name="historysearchvalue" scope="request"><bean:write name="historysearchvalue"/></logic:present>" />


			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

							<div class="col-md-6" id="txtstyle">
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right" id="inputnames">Branch</label>
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
									 
									
									<div class="form-group clearfix ">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="">ALL</option>
										</select>
									</div>
								</div>
						
																			
							<div class="form-group clearfix ">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Search</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" autocomplete="off"
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
					            <div class="form-group clearfix">
								<div class="col-xs-4">
								
								</div>
								<div class="col-xs-7">
								<span class="buttons" id="search">Search</span>
							    <span class="buttons" id="resetbtn">Reset</button>
								</div>
							</div>
					             
							
							</div>
							<div class="col-md-6" id="txtstyle">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								<input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">
									
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="">ALL</option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Status</label>
									<div class="col-xs-7">
										<select id="status" name="status" class="form-control" required>
											<option value="active">Active</option>
											<option value="inactive">InActive</option>
										</select>
									</div>
								</div>
									
							</div>
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
						 <table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' id = 'selectall' name='selectBox' ></th>
							<th>Academic Year</th>
							<th>Admission No</th>
							<th>Student Name</th>
							<th>Class and Division</th>
							<th>DOB</th>
							<th>Date Of Joining</th>
							
							</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
<div class='pagebanner'><select id='show_per_page'>
	                         <option value='50'>50</option>
	                         <option value='100'>100</option>
	                         <option value='200'>200</option>
	                         <option value='300'>300</option>
	                         <option value='400'>400</option>
	                         <option value='500'>500</option>
                        </select>
	<span  class='numberOfItem'></span>	
	</div><div class='pagination pagelinks' style="margin-bottom: 10px;"></div>
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
</body>
</html>