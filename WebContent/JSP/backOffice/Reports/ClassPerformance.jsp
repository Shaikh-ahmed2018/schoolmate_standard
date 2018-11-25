<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="JS/backOffice/Reports/ClassPerformance.js"></script>
</head>
<body>
<div id="dialog" style="display: none;">		
		<div id="dialog1">
			<div class="col-md-12" id="txtstyle"
				style="font-size: 13px; color: #000;">
				
				<table style="width: 100%;border-collapse: unset;" id="dependency" >
							<tr>
								<td ><label for="inputEmail"style="text-align: center; line-height: 35px; width: 88px">Select Exam</label></td>
								<td ><select id="Exam" style="width: 150px;"></select></td>
							</tr>

						</table>
				
			</div>
		</div>
	</div>
<input type="hidden" id="hstudentId"/>
<input type="hidden" id="haccyear"/>
<input type="hidden" id="hlocationId"/>
<input type="hidden" id="hclassId"/>
<input type="hidden" id="hSectionId"/>
<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><img src="images/addstu.png" /><span id="pageHeading">Class Performance</span></p>
				<div class="panel-body clearfix"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top: 0">
					
				</div>

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Class Performance</h3></a>
							
					<div class="navbar-right">
				      <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CLSPERDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">      
					                      <span  class="buttons" id="download" data-toggle="modal" data-target="#myModal" style="vertical-align: 13px;">Download </span>
         	                            </logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
         	            
				</div>
						
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								 
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
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="all">----------Select----------</option>
										</select>
									

									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" onkeypress="handle(event)" autocomplete="off"
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
									<div class="form-group clearfix">
									<div class="col-xs-5">
									</div>
									<div class="col-xs-7">
									<span class="buttons" id="search" >Search</span>
									<span class="buttons" id="resetbtn" >Reset</span>
									</div>
									</div>
								
							</div>
							
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
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
						
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
							
								
								
							</div>
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
							
				<div id="collapseOne" class="accordion-body collapse in">
				<div class="newtable" style="display:none;"></div>
				
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">	
					<%-- <display:table class="table" id="allstudent"
						name="requestScope.studentList"
						requestURI="/menuslist.html?method=studentSearch?">
						<display:column property="sno" sortable="true"	title="S.No"/>
						<display:column property="studentAdmissionNo" class="${allstudent.studentId} ${allstudent.academicYearId} ${allstudent.locationId} ${allstudent.classDetailId} ${allstudent.sectioncode} studentid" sortable="true" title="Admission No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="studentFullName" sortable="true" title="Student Name <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="studentrollno" sortable="true" title="Roll No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="classname" sortable="true" title="Class <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="sectionnaem" sortable="true" title="Division <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						
					</display:table>
					 --%>
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>S.No</th>
							<th>Admission No</th>
							<th>Student Name</th>
							<th>Roll No</th>
							<th>Class</th>
							<th>Division</th>
							
							</tr>
							</thead>
							<tbody>
						<%-- 	<logic:iterate id="studentList" name="studentList">
								<tr>
								<td><bean:write name='studentList' property="sno"/></td>
								<td class="<bean:write name="studentList" property='studentId'/> <bean:write name="studentList" property='academicYearId'/> <bean:write name="studentList" property='locationId'/> <bean:write name="studentList" property='classDetailId'/> <bean:write name="studentList" property='sectioncode'/> studentid" > <bean:write name="studentList" property='studentAdmissionNo'/></td>
								<td><bean:write name="studentList" property='studentFullName'/></td>
								<td><bean:write name="studentList" property='studentrollno'/></td>
								<td><bean:write name="studentList" property='classname'/></td>
								<td><bean:write name="studentList" property='sectionnaem'/></td>
								</tr>
							</logic:iterate> --%>
							</tbody>
						</table>
					
					
					
					</div>
<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
	<span  class='numberOfItem'></span>	
	</div><div class='pagination pagelinks' style='top:-9px'></div>
					</div>
					
						</div>
					</div>
				</div>
			</div>
	</div>
</html>
