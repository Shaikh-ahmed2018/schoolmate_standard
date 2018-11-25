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
 
 
  <script type="text/javascript" src="JS/backOffice/Student/issueCertificate.js"></script>
   
 



</head>

<body>
<input type="hidden" id="hiddenlocId" value="<logic:present name="locationId"><bean:write name="locationId" /></logic:present>">

  <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
  
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<div class="col-md-12 input-group">
		<p><span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span id="pageHeading">Issue Certificate</span></p>
		</div>
			<div class="errormessagediv" align="center" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>
						
               <div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

<input type="hidden" id="historyaccyear"
 value='<logic:present name="historyaccyear" scope="request"><bean:write name="historyaccyear"/></logic:present>'/>
 
<input type="hidden" id="historylocation"
 value='<logic:present name="historylocation" scope="request"><bean:write name="historylocation"/></logic:present>'/>
 
<input type="hidden" id="historyclassid"
 value='<logic:present name="historyclassid" scope="request"><bean:write name="historyclassid"/></logic:present>'/>
 
<input type="hidden" id="historysection"
 value='<logic:present name="historysection" scope="request"><bean:write name="historysection"/></logic:present>'/>
 
<input type="hidden" id="historysearchtext"
 value='<logic:present name="historysearchtext" scope="request"><bean:write name="historysearchtext"/></logic:present>'/>
 
<input type="hidden" id="historystatus"
 value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>'/>
 
<input type="hidden" id="historyfindcerti"
 value='<logic:present name="historyfindcerti" scope="request"><bean:write name="historyfindcerti"/></logic:present>'/>

		
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a  href="#" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title class"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Issue Certificate</h3></a>
						
						
						<div class="navbar-right" style="top:-9px;">
							<span class="search">
							
							<label style="text-align: right;color:#000000;font-weight: 500 !important;font-family: Roboto,sans-serif;font-size: 11pt;padding: 4px;">Select Certificate Type</label>	 
							 				<select name="findcerti" id="findcerti" style="color:#000000;font-family:inherit;font-size: 14px;padding: 6px;padding-bottom: 2px;">
							 					<option value="">-----Select-----</option>
							 					<option value="age">Age Certificate</option>
							 					<option value="bonafide">Bonafide Certificate</option>
							 					<option value="course">Character & Conduct Certificate</option>
							 					<option value="visa">Student VISA</option>
											 </select>
							</span>

							<span class="buttons" style="top:4px;" id="findcertificate">Go</span>
							<!-- <span class="buttons" id="" class="save" style="line-height: 1.89;">Go</span> -->
						</div>
						
					</div>
			
					<div id="classOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 10pt; color: #000;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1"name="location" id="location" >
											
												<logic:present name="locationDetailsList" scope="request">
												<logic:iterate id="locationDetailsList" name="locationDetailsList">
												<option value='<bean:write name="locationDetailsList" property="location_id"/>'><bean:write name="locationDetailsList" property="locationname"/></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
										<input type="hidden" name="hiddenlocationname" id="hiddenlocationname" value='<bean:write name="currentlocation"/>'/>
										<input type="hidden" name="hiddenlocationid" id="hiddenlocationid" value='<bean:write name="locationId"/>'/>
										<input type = "hidden" name="hiddenaccyear" id="hiddenaccyear" value='<bean:write name="academic_year"/>'/>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1"name="class" id="classid" >
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search</label>
									<div class="col-xs-7">
										<input type="text" name="searchtext" placeholder="Search......" tabindex="1" id="searchtext"maxlength="25" class="form-control"  />
									</div>
								</div>

									<div class="form-group clearfix">
								   <div class="col-xs-5">
								</div>
								<div class="col-xs-7">
								<span type="button" class="buttons"
								id="search" >Search</span>
								<span type="button" class="buttons"
								id="Reset" style="height:28px;">Reset</span>
								</div>
							</div>
							</div>
							
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1"name="accyear" id="accyear" >
												<logic:present name="accYearList" scope="request">
												<logic:iterate id="accYearList" name="accYearList">
												<option value='<bean:write name="accYearList" property="accyearId"/>'><bean:write name="accYearList" property="accyearname"/></option>
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
										<select class="form-control" onkeypress="HideError()" tabindex="8"
											name="section" id="section">
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Status</label>
									<div class="col-xs-7">
										<select id="status" name="status" class="form-control" required>
											<option value="active">Active</option>
											<option value="inactive">InActive</option>
										</select>
									</div>
								</div>
								
								
								
							</div>
							<div id="studenttable" class="clearfix">
							</div>
					<!-- <table class='table' id='allstudent' width='100%'>
						<tr>
							<th>Select</th>
							<th>Sl.No.</th>
							<th style="text-align:center">Admission No</th>
							<th style="text-align:center">Student Name</th>
							<th style="text-align:center">Roll No</th>
							<th style="text-align:center">Class</th>
							<th style="text-align:center">Division</th>
							<th style="text-align:center">Issue Date</th>
							<th style="text-align:center">Certificate Type</th>
						</tr>
				   </table> -->
				   
				   	<div class="pagebanner">
				     <select id="show_per_page">
				       <option value="50">50</option>
				       <option value="100">100</option>
				       <option value="200">200</option>
				       <option value="300">300</option>
				       <option value="400">400</option>
				       <option value="500">500</option>
				      </select>
					<span class="numberOfItem"></span>	
				</div>
				<div class="pagination pagelinks"></div>
						</div>
					</div>
				</div>
			</div>
	</div>
</html>
