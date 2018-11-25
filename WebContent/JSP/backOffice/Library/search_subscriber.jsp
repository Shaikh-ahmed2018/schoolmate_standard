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

<script type="text/javascript" src="JS/backOffice/Library/SearchsubscriberDetails.js"></script>


<style>
#editStudent:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}

.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

.class1 {
	width: 41.66666667%;
	margin-left: -65px;
	margin-top: 12px;
}

.fsetRight {
	width: 610px;
	margin-bottom: 15px;
	border: 0.5px solid #ccc;
	padding: 5px;
}

.fieldset {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;

}

.fieldset1 {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;

}

.fieldset2 {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	min-height: 97px;
}

legend {
	display: inline-block;
	width: auto;
	padding: 0;
	margin-bottom: 0px;
	font-size: 16px;
	line-height: inherit;
	color: #333;
	border: 0;
}

.stand {
	margin-left: -16px;
}

.with {
	margin-left: 60px;
}

.page {
	margin-top: 25px;
}

.search1 {
	width: 186px;
	left: -3px;
	color: #000;
	font-size: 14px;
	font-family: Roboto,sans-serif;
}

.navbar-right {
	right: 7px;
	top: 0;
	float: none !important;
	display: block;
	bottom: 0;
	vertical-align: middle;
}

.staffstand {
	display: none;
}

.stafftable {
	display: none;
}

.otherstable {
	display: none;
}

.setsearch {
	padding-left: 45px;
}

#others th:nth-child(6) {
	text-align: left;
	width: 28%;
}

#others th:nth-child(5) {
	text-align: center;
	width: 18%;
}
#others th:nth-child(3) {
	text-align: left;
	width: 15%;
}

#others th:nth-child(2) {
	text-align: center;
	width: 14%;
}

#others td:nth-child(2) {
	text-align: center;
}

#others td:nth-child(6){
	text-align: left;
}

#others td:nth-child(7), th:nth-child(7) {
	text-align: center;
}

#allstudent  th:nth-child(2), th:nth-child(4), th:nth-child(5), th:nth-child(6),
	th:nth-child(7), th:nth-child(8) {
	text-align: center;
}

#allstudent  td:nth-child(2), td:nth-child(4), td:nth-child(5), td:nth-child(6),
	td:nth-child(7), td:nth-child(8) {
	text-align: center;
}

#allstaff td:nth-child(2), th:nth-child(2) {
	text-align: center;
}
.lengend-text{
    font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #1149aeeb;
    margin-left: -38px;
    font-weight: 700;
}
}
</style>

</head>

<body>
	<div class="content" id="div1">
		
			<div id="div2">
			<div class="col-md-12 input-group" >
				<p>
					<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span
						id="pageHeading">Search Subscriber</span>
				</p>
			</div>
</div>
	

		<input type="hidden" id="succmsg"
			value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />

		<div id="successmessages" class="successmessagediv"
			style="display: none;">
			<div class="message-item">
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>

		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne">
					<h3 class="panel-title"
						style="color: #000000; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>
						&nbsp;&nbsp;Subscriber Details
					</h3>
				</a>

				<div class="navbar-right go">

					<div>
						<div class="form-group clearfix" style="padding-top: 3px;">

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

										 <span type="button" class="buttons"style="top:3px;"id="gobutton">Go</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<select class="col-xs-6 search1" id="goto">
												<option>----------GoTo----------</option>
												<option value="subdetail">Subscriber Details</option>
												<option value="issuestate">Issued Statement</option>
												<option value="issuereturn">Issue Return</option>
											</select>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
						</div>
					</div>
				</div>
			</div>
     
     <input type="hidden" id="historylocId"
	 value="<logic:present name='historylocId' scope='request'><bean:write name='historylocId'/></logic:present>" />

     <input type="hidden" id="historysubscriberType"
	 value="<logic:present name='historysubscriberType' scope='request'><bean:write name='historysubscriberType'/></logic:present>" />	
	
	<input type="hidden" id="historyacademicId"
	 value="<logic:present name='historyacademicId' scope='request'><bean:write name='historyacademicId'/></logic:present>" />
	
	<input type="hidden" id="historygoto"
    value="<logic:present name='historygoto' scope='request'><bean:write name='historygoto'/></logic:present>" />
	
	<input type="hidden" id="historystartedby"
    value="<logic:present name='historystartedby' scope='request'><bean:write name='historystartedby'/></logic:present>" />
	
	<input type="hidden" id="historysearch"
    value="<logic:present name='historysearch' scope='request'><bean:write name='historysearch'/></logic:present>" />	 	 	 			

    <input type="hidden" id="historyorderby"
	 value="<logic:present name='historyorderby' scope='request'><bean:write name='historyorderby'/></logic:present>" />	
	
	<input type="hidden" id="historyclassname"
	 value="<logic:present name='historyclassname' scope='request'><bean:write name='historyclassname'/></logic:present>" />
	
	<input type="hidden" id="historysectionid"
    value="<logic:present name='historysectionid' scope='request'><bean:write name='historysectionid'/></logic:present>" />
	
	<input type="hidden" id="historydepartment"
    value="<logic:present name='historydepartment' scope='request'><bean:write name='historydepartment'/></logic:present>" />
	
	<input type="hidden" id="historydesignation"
    value="<logic:present name='historydesignation' scope='request'><bean:write name='historydesignation'/></logic:present>" />


			<!-- pop up -->

			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">

					<div class="row">
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
									<select id="locationname" name="locationnid"
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
							

						</div>

						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-7">
									<select id="Acyearid" name="accyear" class="form-control"
										required>
										<!-- <option value="all"></option> -->
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

						</div>

						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							<fieldset class="fieldset">
								<legend class="lengend-text">
									Subscriber Type:</legend>
								<div class="form-group clearfix"
									style="padding-top: 17px; padding-left: 53px;">

									<label style="width: 100px; margin-left: 2px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="student" class="cencession"
										value="Student" checked="checked" />&nbsp;Student
									</label> <label style="width: 81px;"><input type="radio"
										style="top: -2px;" class="radio-inline" name="requested_by"
										class="cencession" id="staff" value="staff" />&nbsp;Staff&nbsp;&nbsp;&nbsp;
									</label> <label style="width: 86px; margin-left: 8px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="others" class="cencession"
										value="others" />&nbsp;Others
									</label>

								</div>
							</fieldset>
						</div>

						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top: 0px;">

							<fieldset class="fieldset1">
								<legend class="lengend-text">Order By:</legend>

								<div class="form-group clearfix"
									style="margin-top: 17px; margin-left: 40px;">

									<label style="width: 149px;"> <input type="radio"
										style="top: -2px;" class="radio-inline" name="subscriber_by"
										id="student" class="cencession" value="subscriberno" checked />&nbsp;Subscriber
										No
									</label> <label style="width: 195px;"><input type="radio"
										style="top: -2px;" class="radio-inline" name="subscriber_by"
										class="cencession" id="staff" value="subscribername" />&nbsp;Subscriber
										Name&nbsp;&nbsp;&nbsp;</label>
								</div>
							</fieldset>
						</div>

						<div class="col-md-12"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top: 6px;">
							<fieldset class="fieldset2">
								<div class="row" style="margin-top: 10px;">
									<div class="col-md-6 clearfix ">
										<div class="form-group clearfix with"
											style="margin-left: 53px;">

											<label style="width: 100px;"> <input type="radio"
												style="top: -2px;" class="radio-inline" name="started_by"
												id="student" class="cencession" value="startwith" />&nbsp;Start
												with
											</label> <label style="width: 93px;"><input type="radio"
												style="top: -2px;" class="radio-inline" name="started_by"
												class="cencession" id="staff" value="endswith" />&nbsp;Ends
												with&nbsp;&nbsp;&nbsp; </label><label style="width: 86px;">
												<input type="radio" class="radio-inline" name="started_by"
												style="top: -2px;" id="student" class="cencession"
												value="anywhere" checked="checked" />&nbsp;Any where
											</label>

										</div>

										<div class="setsearch" style="padding-left: 41px;">
											<label for="inputPassword" class="control-label col-xs-5"
												style="text-align: left; line-height: 35px; width: 67px;">Search</label>
											<div class="col-md-7">
												<input type="text" class="form-control" id="searchValue"
													Placeholder="Search......"
													value="<logic:present name='searchnamelistValue' scope='request'><bean:write name='searchnamelistValue'/></logic:present>">
											</div>

										</div>

									</div>

									<div class="col-md-6 stand">
										<div class="form-group clearfix">
											<label for="inputPassword"
												class="control-label col-xs-4 standard"
												style="text-align: right; line-height: 35px;">Class</label>
											<div class="col-xs-8">
												<select name="state" id="classname"
													style="width: 217px; font-size: 14px;">
													<option value="all">ALL</option>
												</select>
											</div>
										</div>
										<div class="form-group clearfix">
											<label for="inputPassword" class="control-label col-xs-4"
												style="text-align: right; line-height: 35px;">Division</label>

											<div class="col-xs-8">
												<select name="state" id="sectionid"
													style="width: 217px; font-size: 14px;">
													<option value="all">ALL</option>
												</select>
											</div>
										</div>

									</div>

									<div class="staffstand">
										<div class="col-md-6 ">
											<div class="form-group clearfix">
												<label for="inputPassword"
													class="control-label col-xs-4 standard"
													style="text-align: right; line-height: 35px;">Department</label>
												<div class="col-xs-8">
													<select name="state" style="width: 217px; font-size: 14px;"
														id="department">
														<option>ALL</option>
													</select>
												</div>
											</div>
											<div class="form-group clearfix">
												<label for="inputPassword" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;">Designation</label>

												<div class="col-xs-8">
													<select name="state" style="width: 217px; font-size: 14px;"
														id="designation">
														<option>ALL</option>
													</select>
												</div>
											</div>
											
										<%-- <div class="setsearch form-group clearfix" id="hidensearch" style="padding-left: 41px;display:none">
											<label for="inputPassword" class="control-label col-xs-5"
												style="text-align: left; line-height: 35px; width: 67px;">Search</label>
											<div class="col-md-7">
												<input type="text" class="form-control" id="searchValue"
													Placeholder="Search......"
													value="<logic:present name='searchnamelistValue' scope='request'><bean:write name='searchnamelistValue'/></logic:present>">
											</div>
										 </div> --%>
											
											
										</div>
									</div>
								</div>
							</fieldset>
						</div>
					</div>
					<input type="hidden" id="hidenaccyear"
						value="<logic:present name='accademic_year' scope='request'><bean:write name='accademic_year'/></logic:present>" />
					<!-------------- Student table -------------->
					<div class="allstudenttable" style="margin-top: 6px;">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<table class='table allstudent' id='allstudent'>
								<thead>
									<tr>
										<th>Select</th>
										<th>Subscriber Number</th>
										<th>Subscriber Name</th>
										<th>Admission Number</th>
										<th>Roll Nnumber</th>
										<th>Class</th>
										<th>Section</th>
										<th>Status</th>
									</tr>
								</thead>

								<tbody>

								</tbody>

							</table>


						</div>
					</div>

					<!------------- Staff table ----------->
					<div class="stafftable" style="margin-top: 6px;">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<table class='table allstudent' id="allstaff">

								<thead>
									<tr>
										<th>Select</th>
										<th>Subscriber Number</th>
										<th>Subscriber Name</th>
										<th>Department</th>
										<th>Designation</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>

								</tbody>

							</table>
						</div>
					</div>

					<!---------------------------- others table------------------------------ -->
					<div class="otherstable" style="margin-top: 6px;">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<table class='table allstudent' id="others">

								<thead>
									<tr>
										<th>Select</th>
										<th>Subscriber Number</th>
										<th>Name</th>
										<th>Contact </th>
										<th>Mail Id</th>
										<th>Address</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>

								</tbody>

							</table>
						</div>
					</div>
					<!-- <div class='pagebanner page'> -->
					<div class='pagebanner' style="margin-top: 2px; margin-left: 15px;">
						<select id='show_per_page'>
							<option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option>
						</select>
					</div>
					<div class='pagination pagelinks' style="font-size: 10px;"></div>
					<div
						style="padding-top: 8px; margin-left: 5%;">
						<span class='numberOfItem'></span>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>