<!DOCTYPE html>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="JS/backOffice/Admin/AddTermDetails.js"></script>

</head>

<body>
		<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
	<!-- <p>
			<img src="images/addstu.png" />&nbsp;<span>Add Term Details</span>
		</p> -->

<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="msge" scope="request"><bean:write name="msge"></bean:write></logic:present></span>
		</p>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<logic:present name="errormessagediv" scope="request">
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv1"
			align="center" style="display: none;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 35%; font-size: 13px; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #000000"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Term Details</h3></a>

						<div class="navbar-right">
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="TRMADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											  <span id="save" class="buttons">Save</span> 
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							<span id="back1" class="buttons" style="right:-3px;">Back</span>
						</div>
					</div>

     <input type="hidden" id="historylocId"
	 value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId" /></logic:present>" />
						
   <input type="hidden" id="historyacademicId"
	 value="<logic:present name="historyacademicId" scope="request"><bean:write name="historyacademicId" /></logic:present>" />						

<input type="hidden" id="curr_loc"
	 value="<logic:present name="curr_loc" scope="request"><bean:write name="curr_loc" /></logic:present>" />						

				
					 <input type="hidden" id="enddates"
						value="<logic:present name="enddate"><bean:write name="enddate" /></logic:present>" />
					<input type="hidden" id="lastDate"
						value="<logic:present name="statuss"><bean:write name="statuss" /></logic:present>" />
					<input type="hidden" id="termmasterid" value='<logic:present name="editlist"><bean:write name="editlist" property="termid" /></logic:present>'></input>
						
						<input type="hidden" id="termmasternm"
						value='<logic:present name="editlist"><bean:write name="editlist" property="termname" /></logic:present>'></input>
						
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Branch<span style="color: red;">*</span></label>
									<div class="col-xs-7">
									<select id="locationname" name="locationId" class="form-control" onchange="HideError(this)">
										<logic:present name="locationList">
										<logic:iterate id="Location" name="locationList">
											<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
										</logic:iterate>
										</logic:present>
						
									</select>
									</div>
								<input type="hidden" name="schoolId" class="form-control" id="schoolId" value='<logic:present name="editlist"><bean:write name="editlist" property="locationId" /></logic:present>'></input>
							</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Term
										Name<span style="color: #F00;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="termname" maxlength="15"
											onclick="HideError(this)" placeholder=""
											value='<logic:present name="editlist"><bean:write name="editlist" property="termname" /></logic:present>'></input>
									</div>
								</div>
								

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="termdescription" onclick="HideError(this)"
											placeholder=""
											value='<logic:present name="editlist"><bean:write name="editlist" property="description" /></logic:present>'></input>
									</div>
								</div>
								

								<div class="col-xs-7">
									<input type="hidden" class="form-control" id="AccStartDate" 
										value='<logic:present name="acclist"><bean:write name="acclist" property="acastartdate"/></logic:present>'></input>
								</div>
								<div class="col-xs-7">
									<input type="hidden" class="form-control" id="AccEndDate" 
										value='<logic:present name="acclist"><bean:write name="acclist" property="acaenddate"/></logic:present>'></input>
								</div>

								
							</div>

							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
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
								<input type="hidden" name="Acyearid" id="hacademicyaer"
								value='<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>'></input>
								
								
							<input type="hidden" name="hiddenaccyearId" class="form-control" id="hiddenaccyearId" 
							   value='<logic:present name="editlist"><bean:write name="editlist" property="accyear" /></logic:present>'></input>	
								
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Start
										Date<span style="color: #F00;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control startdate"
											id="startdate" placeholder="" onclick="HideError(this)"
											value='<logic:present name="editlist"><bean:write name="editlist" property="startdate" /></logic:present>'></input>
									</div>
								</div>

								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">End Date<span
										style="color: #F00;">*</span></label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control enddate"
											id="enddate" placeholder="" onclick="HideError(this)"
											value='<logic:present name="editlist"><bean:write name="editlist" property="enddate" /></logic:present>'></input>
									</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</form>
	</div>
</body>

</html>
