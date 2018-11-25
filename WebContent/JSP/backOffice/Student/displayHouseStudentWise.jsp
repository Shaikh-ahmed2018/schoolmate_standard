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
 
<script type="text/javascript" src="JS/backOffice/Student/displayHouseSettingClassWise.js"></script>
 
<style type="text/css">

.table{
width:100%;
}

</style>
</head>

<body>
 
 <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>

<div id="dialog" style="display:none;"><p>Re-Generation of House will Over-write Existing House Name.</p><p>Are you sure want to Re-Generate?</p></div>
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
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
		<p><img src="images/addstu.png"/><span id="pageHeading">Generate House</span></p>
				<div class="panel-body clearfix" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
				</div>
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a href="#" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title class"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Generate House</h3></a>
						
						
					 	<div class="navbar-right">
					 	
					 		<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="GENHOUUPD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
											<span class="buttons" id="house" style="top:4px;">Re-Generate</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
							<span class="buttons" id="back" style="top:4px;">Back</span>
							<!-- <span class="buttons" id="save">Save</span> -->
						</div>
						
					</div>
				<input type="hidden" id ="hiddengen" value='<logic:present name="genhouid"><bean:write name="genhouid"/></logic:present>'/>
					<div id="classOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel" id="inputcolor">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<input type="text" name="location" id="locName" class="form-control" readonly="readonly"
											value='<logic:present name="locName"><bean:write name="locName"/></logic:present>'/>
										<input type="hidden" id = "hiddenlocid" value='<logic:present name="locid"><bean:write name="locid"/></logic:present>'/>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="accyName"  id="accyName" class="form-control" readonly="readonly"
											value='<logic:present name="accyName"><bean:write name="accyName"/></logic:present>' />
										<input type="hidden" id="hiddenaccyear" value='<logic:present name="accid"><bean:write name="accid"/></logic:present>'/>	
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="classname" id="classname" class="form-control" readonly="readonly"
											value='<logic:present name="classname"><bean:write name="classname" /></logic:present>'/>
										<input type="hidden" id="hiddenclassname" value='<logic:present name="classid"><bean:write name="classid"/></logic:present>'/>
									</div>
								</div>
							</div>
							
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">No. Of Students</label>
									<div class="col-xs-7">
										<input type="text" name="noofstudents" id="noofstudents" class="form-control" readonly="readonly"
											value='<logic:present name="totalstu"><bean:write name="totalstu"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Allocated Students</label>
									<div class="col-xs-7">
										<input type="text" name="allostu" id="allostu" class="form-control" readonly="readonly" value='<logic:present name="allostu"><bean:write name="allostu"/></logic:present>' />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Filter</label>
									<div class="col-xs-7">
										<input type="text" name="filter" id="filter" class="form-control" readonly="readonly" value='<logic:present name="filter"><bean:write name="filter"/></logic:present>' />
									</div>
								</div>
							</div>
							
					<table class='table' id='allstudent'>
					<thead>
						<tr>
							<th>Sl.No.</th>
							<th style="text-align:center">Admission No</th>
							<th>Student Name</th>
							<th style="text-align:center">Class</th>
							<th style="text-align:center">Section</th>
							<th style="text-align:center">House Name</th>
						</tr>
						<thead>
						<tbody>
						<logic:present name="housestudentwise" scope="request">
					   	<logic:iterate id="housestudentwise" name="housestudentwise" scope="request">
					   		<tr>
					   		<td style="text-align:center"><bean:write name="housestudentwise" property="slno" /></td>
					   		<td style="text-align:center"><bean:write name="housestudentwise" property="admissionno" /></td>
					   		<td class="stuId" id="<bean:write name="housestudentwise" property="stuid"/>"><bean:write name="housestudentwise" property="stuname" /></td>
					   		<td style="text-align:center" class="classId" id="<bean:write name="housestudentwise" property="classid"/>"><bean:write name="housestudentwise" property="classname"/></td>
					   		<td style="text-align:center" class="sectionid" id="<bean:write name="housestudentwise" property="sectionid"/>"><bean:write name="housestudentwise" property="sectionname"/></td>
					   		<td style="text-align:center" class="housename"><bean:write name="housestudentwise" property="housename"/></td>
					   		
					   		</tr>
					   	</logic:iterate>
					   </logic:present>	
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
</html>
