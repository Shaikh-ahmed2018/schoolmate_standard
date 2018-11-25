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

<script type="text/javascript" src="JS/backOffice/Fee/feeCancellation.js"></script>

</head>


<body>
<div id="dialogind"></div>
<div id="printdiv" style="display: none;"></div>
<div id="dialog">
<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">
<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Student
									Name</label>
								<div class="col-xs-7">
									<input type="text" name="studentName" id="studentName"
										class="form-control" /> <input type="hidden"
										id="studentIdIntId" value="" />
								</div>
							</div>
							</div>
							
							<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-7">
									<select id="Acyearid" name="accyear" class="form-control"
										required="required">
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
						<table class="table dialogtable" id="allstudent" style="display: none;">
									<thead>
										<tr>
											<th><input type='checkbox' class='selectall'></th>
											<th>Admission No</th>
											<th>Student Name</th>
											<th>Class</th>
											<th>Term</th>
											<th>Paid Amt.</th>
											<!-- <th>Action</th> -->
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
</div>
<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<input type="hidden" id="feecode" value="" />
	<!-- <div id="dialog" style="display: none;">
		
		<p>Are you sure to Cancel?</p>
	</div> -->

	<div class="errormessagediv" align="center" style="display: none;">
		<div class="message-item">
			<!-- Warning -->
			<a href="#" class="msg-warning bg-msg-warning"><span
				class="validateTips"></span></a>
		</div>
	</div>
	<div class="successmessagediv" style="display: none;">
		<div class="message-item">
			<!-- Warning -->
			<a href="#" class="msg-success bg-msg-succes" text-align:center;"><span
				class="validateTips"> </span></a>
		</div>
	</div>
	<div class="col-md-10" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">

		<p>
			<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span id="pageHeading">Fee Cancellation</span>
		</p>
		<div class="panel-body clearfix"
			style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top: 0px;">

		</div>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">

					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne"
						style="color: #000000; vertical-align: text-top;">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Fee Cancellation
						</h3>
					</a>
				<div class="navbar-right">
						<span id="add" class="buttons">New</span>
							<!-- <span id="back" class="buttons">Back</span>		 -->							
					</div>
				</div>

				<div id="collapseOne" class="panel-collapse collapse in "
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body own-panel">
						<input type="hidden" id="hiddendelete" 
								value="<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
								<logic:equal name="daymap" property="key" value="FEECANDEL">
									<logic:equal value="true" name="daymap" property="value">
								<bean:write name="daymap" property="value" />
								</logic:equal>
								</logic:equal>
								</logic:iterate>
								</logic:present>" />

						
						<input type="hidden" name="Acyearid" id="Acyearid"
							value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>

						<div id="collapseOne" class="accordion-body collapse in">
							<div class="panel-body"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 10pt; color: #000;">

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
									style="text-align: right; line-height: 35px;">Search</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" id="searchvalue" autocomplete="off"
										Placeholder="Search......" onkeypress="handle(event)"
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
								</div>
							</div>
							
								<div class="form-group clearfix">
									<div class="col-xs-5"></div>
									<div class="col-xs-7">
									<span type="button" class="buttons" id="search">Search</span>
								    <span type="reset" class="buttons" id="resetbtn"
									style="height: 28px;">Reset</span>
									</div>
								</div>
							
							
							
							
							
						</div>
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 10pt; color: #000;">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-7">
									<select id="acyearid" name="accyear" class="form-control"
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
                        <input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Division</label>
								<div class="col-xs-7">
									<select id="sectionid" name="sectionid" class="form-control"
										required>
										<option value="all">ALL</option>
									</select>
								</div>
							</div>
							




						</div>
								<table class="table maintable" id="allstudent"">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Admission No</th>
											<th>Student Name</th>
											<th>Class</th>
											<th>Division</th>
											<!-- <th>Cancelled-Term</th>
											<th>Paid Amt.</th> -->
											<!-- <th>Action</th> -->
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>


							</div>
							<div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select> <span class='numberOfItem'></span>
					</div>
					<div class='pagination pagelinks' style="margin-bottom: 10px"></div>

						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<textarea id="printing-css" style="display:none;">
		#printdiv{
		width:600px !important;
		}
		.printdiv{
		border: 1px solid #000;
		font-size: 14px;
    	font-weight: normal;
		}
				.mainDialog{padding:0px;margin:0px;}
				body {-webkit-print-color-adjust: exact;margin:0 auto;padding:0; width:300px !important;}
	</textarea>
	</body>
</html>
