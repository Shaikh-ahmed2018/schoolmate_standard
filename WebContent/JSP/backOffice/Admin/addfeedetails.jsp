<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Campus School Stream</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<script type="text/javascript"
	src="JS/backOffice/Admin/addfeedetails.js"></script>


</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present
					name="FeeDetail" scope="request">
					<bean:write name="FeeDetail"></bean:write>
				</logic:present> </span>
		</p>

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

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">

					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" style="color: #000000">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;New Fee Details
						</h3>
					</a>

					<div class="navbar-right">
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="FEESADD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<span id="save" class="buttons" data-toggle="modal"
											data-target="#myModal">Save</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						<span id="back1" style="right: -3px;" class="buttons">Back</span>
					</div>

				</div>
				
	 <input type="hidden" id="historylocId"
	 value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>'></input>
					
     <input type="hidden" id="historyacyearId"
	 value='<logic:present name="historyacyearId" scope="request"><bean:write name="historyacyearId"/></logic:present>'></input>	 		
	
	<input type="hidden" id="historystatus"
    value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>'></input>
	 			
				<input type="hidden" id="feemasterid"
					value='<logic:present name="editlist"><bean:write name="editlist" property="id" /></logic:present>'></input>

				<input type="hidden" id="hiddenfeeTypeId"
					value='<logic:present name="editlist"><bean:write name="editlist" property="feeTypeId" /></logic:present>'></input>
				<input type="hidden" id="hfeeType"
					value='<logic:present name="editlist"><bean:write name="editlist" property="feeType" /></logic:present>'></input>
				<input type="hidden" id="hlocationId"
					value='<logic:present name="editlist"><bean:write name="editlist" property="locationId" /></logic:present>'></input>
				<input type="hidden" id="haccyear"
					value='<logic:present name="editlist"><bean:write name="editlist" property="academicYear" /></logic:present>'></input>
				<%-- <logic:present name="feeTypeList"><logic:iterate id="feeType" name="feeTypeList"><option value='<bean:write name="feeType" property="feeTypeId"/>'>	<bean:write name="feeType" property="feeType" /></option></logic:iterate></logic:present> --%>
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<div class="col-md-6"
							style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">
                          
                          
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Branch<span style="color: red;">*</span>
								</label>
								<div class="col-xs-7">
									<select id="locationname" name="locationId"
										class="form-control">
										<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option
													value="<bean:write name="Location" property="locationId"/>"><bean:write
														name="Location" property="locationName" /></option>
											</logic:iterate>
										</logic:present>

									</select>
								</div>
								<input type="hidden" name="schoolId" class="form-control"
									id="schoolId"
									value='<logic:present name="list"><bean:write name="list" property="locationId" /></logic:present>'></input>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Fee Type<span
									style="color: #F00;">*</span></label>
								<div class="col-xs-7">
									<select name="feeType" id="feeType" class="form-control"
										onkeypress="HideError(this)">
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Fee Name<span
									style="color: #F00;">*</span></label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="feename"
										id="feename" onkeypress="HideError(this)"
										value='<logic:present name="editlist"><bean:write name="editlist" property="name"/></logic:present>'></input>
								</div>
							</div>


						</div>





						<div class="col-md-6"
							style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-7">
									<select id="Acyearid" name="accyear" class="form-control"
										required>

										<logic:present name="AccYearList">
											<logic:iterate id="AccYear" name="AccYearList">
												<option
													value="<bean:write name="AccYear" property="accyearId"/>"><bean:write
														name="AccYear" property="accyearname" /></option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>

							<input type="hidden" name="Acyearid" id="hacademicyaer"
								value='<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>'></input>
							
							
							
							

							<div class="form-group clearfix">
								<label for="inputDescription" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Description
								</label>
								<div class="col-xs-7">
									<textarea style="resize: none" rows="4" class="form-control"
										name="feedescription" id="feedescription"><logic:present
											name="editlist">
											<bean:write name="editlist" property="description"></bean:write>
										</logic:present></textarea>
								</div>
							</div>
						</div>

					</div>
				</div>


			</div>
		</div>
	</div>

	<!-- <button type="submit" class="btn btn-info col-md-offset-5">Savechanges</button>
				<button type="reset" class="btn btn-info">Clear</button> -->


</body>

</html>
