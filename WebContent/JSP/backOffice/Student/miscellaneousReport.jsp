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

<script type="text/javascript" src="JS/backOffice/Student/MiscellaneousReport.js"></script>

</head>
  

<body>


<input type="hidden" id="hiddenlocId" value="<logic:present name="hloc"><bean:write name="hloc" /></logic:present>">



   <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
 
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><span class="glyphicon glyphicon-user"></span>&nbsp;<span id="pageHeading">MIS Report</span></p>
				<div class="panel-body clearfix"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;padding-top:0;">
					
				</div>

			<div class="panel-group" >
		<div class="panel panel-primary">
			<div class="panel-heading">
				
					<h3 id="heading1" class="panel-title" style="color: #000000; vertical-align: super;"> 
					<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;MIS Report
					</h3>
					</div>

   <input type="hidden" id="historyaccYear" 
	value="<logic:present name='historyaccYear' scope='request' ><bean:write name='historyaccYear'/></logic:present>"/>
	
	<input type="hidden" id="historylocId" 
	value="<logic:present name='historylocId' scope='request' ><bean:write name='historylocId'/></logic:present>"/>
	
	<input type="hidden" id="historyclassname" 
	value="<logic:present name='historyclassname' scope='request' ><bean:write name='historyclassname'/></logic:present>"/>
	
	<input type="hidden" id="historysectionid" 
	value="<logic:present name='historysectionid' scope='request' ><bean:write name='historysectionid'/></logic:present>"/>
	
	<input type="hidden" id="historysearchvalue" 
	value="<logic:present name='historysearchvalue' scope='request' ><bean:write name='historysearchvalue'/></logic:present>"/>
	
	<input type="hidden" id="historystatus" 
	value="<logic:present name='historystatus' scope='request' ><bean:write name='historystatus'/></logic:present>"/>


					<div id="MIScollapseOne" class="panel-collapse collapse in">
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
									<select class="form-control"  name="classname" id="classname">
											<option value="all">ALL</option>
									</select>
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" autocomplete="off"
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
								
										<div class="form-group clearfix">
										<div class="col-xs-5">
										</div>
										
									<div class="col-xs-7">
										<span type="button" class="buttons" id="search" >Search</span>
								      <span type="reset" class="buttons" id="resetbtn" >Reset</span>
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
								<input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control">
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
								
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Status</label>
								<div class="col-xs-7">
									<select id="status" name="status" class="form-control">
										<option value="active">Active</option>
										<option value="inactive">InActive</option>
									</select>
								</div>
							</div>
								
								
							</div>
						<%-- <input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input> --%>
							
				
				<div class="" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">	
					<logic:present name="studentList" scope="request">
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
							<%-- <logic:iterate id="studentList" name="studentList">
								<tr>
								<td><bean:write name='studentList' property="count"/></td>
								<td class='<bean:write name="studentList" property='studentId'/> <bean:write name="studentList" property='academicYearId'/> <bean:write name="studentList" property='locationId'/> studentid'><bean:write name='studentList' property="studentAdmissionNo"/></td>
								<td><bean:write name="studentList" property='studentFullName'/></td>
								<td><bean:write name="studentList" property='studentrollno'/></td>
								<td><bean:write name="studentList" property='classname'/></td>
								<td><bean:write name="studentList" property='sectionnaem'/></td>
								</tr>
							</logic:iterate> --%>
							</tbody>
						</table>
					</logic:present>
				
					<div class="pagebanner"><select id="show_per_page"><option value="50">50</option><option value="100">100</option><option value="200">200</option><option value="300">300</option><option value="400">400</option><option value="500">500</option></select>
						<span  class='numberOfItem'></span>	
						</div>
					<div class="pagination pagelinks"><div class="controls"><a class="prev" onclick="previous()">Prev</a><a class="page active" onclick="go_to_page(0)" longdesc="0" style="display: inline;">1</a><a class="page" onclick="go_to_page(1)" longdesc="1" style="display: inline;">2</a>&nbsp;&nbsp;&nbsp;<a class="next" onclick="next()">Next</a></div><input id="current_page" type="hidden" value="0"><input id="show_per_page" type="hidden"></div>
					
					</div>
					</div>
						</div>
					</div>
				</div>
			</div>
</html>
