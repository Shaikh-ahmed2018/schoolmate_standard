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

<script type="text/javascript" src="JS/backOffice/Student/GenerateRollNo.js"></script>
<style type="text/css">
#codition-collapse h4{
cursor: pointer;
color: #767676; 
}
#conditionTable tbody tr td{
vertical-align: middle;
}
.condtion-block{
margin-bottom: 10px;
}
.glyphicon-plus{
height: 24px;
}
</style>
</head>

<body>

<input type="hidden" id="hiddenlocId" value="<logic:present name="hloc"><bean:write name="hloc" /></logic:present>">

    <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="col-md-10" id="div-main" >
		
		<p style="margin-bottom: 0px;"><span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;<span id="pageHeading">Generate Roll No</span></p>
		
		<div class="successmessagediv" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes" style="text-align: center;"><span class="sucessmessage"> </span></a>
							</div>
						</div>
				<div class="errormessagediv" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning" style="text-align: center;"><span class="validateTips"></span></a>
							</div>
						</div>
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne" style="color: #000000;"> 
							<h3 class="panel-title" ><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Generate Roll No</h3></a>
						
						
						<div class="navbar-right">
						<span class="buttons" id="rollNoGenerator">Generate</span>
						<span class="buttons" id="save">Save</span>
						</div>
						
					</div>

					<div id="collapseOne" class="panel-collapse collapse in claerfix" role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
									<select id="locationname" name="locationId" class="form-control">
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

									<select name="classname" id="classname" class="form-control" onchange="HideErrors(this)"
										onchange="HideError(this)">
										<option value=""></option>

									</select>

								</div>
							</div>



						</div>
							
							<div class="col-md-6" >
							
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control">
										<logic:present name="AccYearList">
										<logic:iterate id="Academicyear" name="AccYearList">
										<option value="<bean:write name="Academicyear" property="accyearId"/>"><bean:write name="Academicyear" property="accyearname" /></option>
										</logic:iterate>
										</logic:present>
						
									</select>
									</div>
								</div>
								
								
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select class="form-control" onchange="HideErrors(this)" tabindex="8"
											name="studSectionId" id="studSectionId">
											<option value="">---------Select---------</option>
										</select>
									</div>
								</div>
								
							</div>
		<div class="row">		
			<div class="condtion-block col-md-12 panel-primary">
				<div class="panel-heading" id="codition-collapse">
					<a  style="color: #000000;"> 
							<h3 class="panel-title" ><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Condition</h3></a>
				</div>
				<div id="coditiontable-block" class="claerfix" style="display:none;">
				   <div class="row">
					 
						<div class="col-md-12">
						<table id="OrderingCondition" class="table allstudent">
						<thead>
						<tr>
						<th>Sl.No.</th>
						<th>Field</th>
						<th>Order By</th>
						</tr>
						</thead>
						<tbody>
						<tr id="order1">
						<td>1</td>
						<td><select name="field" class="form-control field field1" onchange="HideError(this)">
						<option value="">-----Select------</option>
						<option value="student_admissionno_var">Admission No.</option><option value="student_fname_var">Name</option>
						<option value="student_gender_var">Gender</option>
						</select></td>
						<td><select  name="ordering" class="form-control ordering">
							<option value="ASC">Ascending</option>
							<option value="DESC">Descending</option></select></td>
						
						</tr>
						<tr id="order2">
						<td>2</td>
						<td><select name="field" class="form-control field">
						<option value="">-----Select------</option>
						<option value="student_admissionno_var">Admission No.</option><option value="student_fname_var">Name</option>
						<option value="student_gender_var">Gender</option>
						</select></td>
						<td><select  name="ordering" class="form-control ordering">
							<option value="ASC">Ascending</option>
							<option value="DESC">Descending</option></select></td>
					
						</tr>
						<tr id="order3">
						<td>3</td>
						<td><select name="field" class="form-control field">
						<option value="">-----Select------</option>
						<option value="student_admissionno_var">Admission No.</option><option value="student_fname_var">Name</option>
						<option value="student_gender_var">Gender</option>
						</select></td>
						<td><select  name="ordering" class="form-control ordering">
							<option value="ASC">Ascending</option>
							<option value="DESC">Descending</option></select></td>
						
						</tr>
						</tbody>
						</table>
					</div>
						</div>
					</div>	
					</div>	
					</div>
					<table class='table' id='allstudent' width='100%' style="display: none;">
						<thead>
						<tr>
							<th>Sl.No.</th>
							<th style="text-align:center">Admission No</th>
							<th style="text-align:center">Student Name</th>
							<th style="text-align:center">Gender</th>
							<th style="text-align:center">Second Language</th>
							<th style="text-align:center">Third Language</th>
							<th style="text-align:center">Old Roll No</th>
							<th style="text-align:center">New Roll No</th>
						</tr>
						</thead>
						<tbody>
						
						</tbody>
						</table>
						
						</div>
					</div>
				</div>
			</div>
	</div>
</html>
