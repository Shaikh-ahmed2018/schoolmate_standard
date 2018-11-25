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
<script type="text/javascript" src="JS/backOffice/Library/IssueReturnStatement.js"></script>
<style>
#allstudent th:nth-child(1), th:nth-child(2), th:nth-child(3),
	th:nth-child(4), th:nth-child(5), th:nth-child(6), th:nth-child(7) {
	text-align: center;
}

allstudent td:nth-child(1), td:nth-child(2), td:nth-child(3),
	td:nth-child(4), td:nth-child(5), td:nth-child(6), td:nth-child(7) {
	text-align: center;
}

.select {
	padding: 5px;
	font-size: 14px;
	background-color: #f5f5f5;
	vertical-align: 5px;
	color: #fff;
	border-radius: 3px;
}

.fsetRight {
	width: 610px;
	margin-bottom: 15px;
	border: 0.5px solid #ccc;
	padding: 5px;
}

.fieldset2 {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	min-height: 55px;
	margin-top: 6px;
}
.fieldset4 {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	min-height: 55px;
	margin-top: 12px;
}

.fieldset {
	width: 445px;
	display: block;
	/*  margin-left: auto;
    margin-right: 2px; */
	margin-bottom: 13px;
	padding-bottom: 0.625em;
	padding-left: 7px;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	height: 99px;
	margin-top: -11px;
}

.fsetRight1 {
	width: 434px;
	display: block;
	/*  margin-left: auto;
    margin-right: 2px; */
	margin-bottom: 13px;
	padding-bottom: 0.625em;
	padding-left: 7px;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	height: 91px;
	margin-top: -11px;
}

.fieldset3 {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	min-height: 60px;
	margin-top: 4px;
}

.staffdetail {
	display: none;
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

.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all {
	z-index: 1;
	display: block;
	left: 478px;
	width: 346px;
	height: 300px;
	overflow: scroll;
	position: absolute;
}

.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all {
	overflow: scroll;
	max-height: 300px;
}

#editStudent:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}

.lengend-text{
    font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #1149aeeb;
    margin-left: -38px;
    font-weight: 700;
}

</style>
<script type="text/javascript">
	function handle(e) {

		var selection = $("input[name='requested_by']:checked").val();

		if (e.keyCode === 13) {
			e.preventDefault(); // Ensure it is only this code that rusn
			if (selection == "Student") {
				getIssueDetailsByStartwith();
			} else if (selection == "staff") {

				getIssueByStartwith();
			} else if (selection == "Others") {

				getIssueotherByStartwith();
			}

		}
	}
</script>
<script type="text/javascript">
	function myFunction() {

		var selection = $("input[name='requested_by']:checked").val();
		// Ensure it is only this code that rusn
		if (selection == "Student") {
			getIssueDetailsByStartwith();
		} else if (selection == "staff") {

			getIssueByStartwith();
		} else if (selection == "Others") {

			getIssueotherByStartwith();
		}

	}
</script>
</head>

<body>

	<div class="content" id="div1">
			<div id="div2">
				<div class="col-md-12 input-group">
				<p>
					<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span
						id="pageHeading"> Issued Details</span>
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
						&nbsp;&nbsp;Issued Statement
					</h3>
				</a>

				<div class="navbar-right">
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="LIBRPDWD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span class="buttons" id="iconsimg" data-toggle="modal"
										data-target="#myModal">Download </span>&nbsp;
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
                         <!-- <span class="buttons" id="print">Print</span> -->
				</div>
			</div>

			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
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


			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">
					<div class="row">
						<div class="col-md-7">
							<div class="col-md-7" id="txtstyle">

								<fieldset class="fsetRight"
									style="height: 65px; margin-left: -15px;width: 195%;">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group clearfix">
												<label for="inputPassword" class="control-label col-xs-5"
													style="text-align: right; line-height: 35px; margin-top: 8px;">Branch</label>
												<div class="col-xs-7">
													<select id="locationid" name="locationid" class="form-control"
														style="margin-top: 10px;">
													
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
										<div class="col-md-6">
											<div class="form-group clearfix">
												<label for="inputPassword" class="control-label col-xs-5"
													style="text-align: left; line-height: 35px; margin-top: 8px;">Library Branch</label>
												<div class="col-xs-7">
													<select id="liblocId" name="liblocId" style="margin-top: 10px;" class="form-control" required>
								                        <option value="all">ALL</option>
							                        </select>
												</div>
											</div>
										</div>
									</div>
							</div>
						</div>

						<div class="col-md-5" id="txtstyle">
							<fieldset class="fsetRight1"
								style=" margin-top: 0px;width: 100%;height: 65px;">
								<div class="form-group clearfix"
									style="margin-left: 25px; max-height: 106px; margin-top: 20px; font-size: 13px;">

									<label> <input type="radio" class="radio-inline"
										style="margin-bottom: 5px;" name="statement_by" id="issue"
										class="cencession" value="issue" checked />&nbsp;Issue
										Statement
									</label> <label style="margin-left: 40px;"> <input type="radio"
										class="radio-inline" name="statement_by"
										style="margin-bottom: 5px;" id="return" class="cencession"
										value="return" />&nbsp;Return Statement
									</label>

								</div>
							</fieldset>
						</div>

						<div class="col-md-12" style="top: -10px;">
							<fieldset class="fieldset3">

								<div class="row" style="margin-top: 11px;">
									<div class="col-md-6 clearfix">
										<div class="form-group clearfix with" style="width:200%;margin-left:6%;">
											<label style="margin-left:193px;"> <input type="radio" class="radio-inline"
												style="margin-bottom: 5px;" name="requested_by" id="all"
												class="cencession" value="all" checked />&nbsp;All
											</label> <label style="margin-left:2%;"> <input type="radio" class="radio-inline"
												style="margin-bottom: 5px;" name="requested_by"
												id="individual" class="cencession" value="individual" />&nbsp;Individual
											</label> <label style="margin-left:23px;"> <input type="radio" class="radio-inline"
												style="margin-bottom: 5px;" name="requested_by" id="student"
												class="cencession" value="Student" />&nbsp;Student
											</label> <label style="margin-left:2%;"><input type="radio" class="radio-inline"
												style="margin-bottom: 5px;" name="requested_by"
												class="cencession" id="staff" value="staff" />&nbsp;Staff&nbsp;&nbsp;&nbsp;
											</label> <label style="margin-left:1%;"> <input type="radio" class="radio-inline"
												name="requested_by" style="margin-bottom: 5px;" id="others"
												class="cencession" value="others" />&nbsp;Others
											</label>
											<!-- <label> 
										<input type="radio"class="radio-inline" name="requested_by"
										style="margin-bottom: 5px;" id="book" class="cencession"
										value="book" />&nbsp;Book
									</label> -->
										</div>
									</div>
								</div>
								</fieldset>
						</div>
				<div class="col-md-12 subscriber" 
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top:-2px;margin-bottom:15px;display: none;">
							<fieldset class="fieldset4">
								<legend class="lengend-text">Subscriber:</legend>
								<div class="row" style="margin-top: 10px;">
									<div class="col-md-9 clearfix ">
										<div class="form-group clearfix with">


											<div class="col-md-6" id="txtstyle">
												<div class="form-group row">
													<label for="inputEmail" class="col-xs-5"
														style="text-align: right; line-height: 35px;">Subscriber No.</label>
													<div class="col-xs-7">
														<input type="text" id="subscriberno" name="subscriberno" autocomplete="off"
															 class="form-control ui-autocomplete-input"  value="" />
															<input type="hidden" id="hiddenstuid"/>
													</div>
												</div>


											</div>
											
											<div class="col-md-6 collapseOne" id="txtstyle">

												<div class="form-group row">
													<label for="inputEmail" class="col-xs-5"
														style="text-align: right; line-height: 35px;">Accession No.
													</label>
													<div class="col-xs-7">
														<select id="accession_no" name="Accession_no" class="form-control" >
														<option value="all">-------select-------</option>
													</select> <!-- style="width: 193px;margin-top: 18px;" -->
															
															 <input type="hidden"
															name="accessionName" id="hidden_accessionNo" value="" />
													</div>
												</div>
												
											</div>
											
										
											<div class="col-md-6">	

												<div class="form-group row">
													<label for="inputPassword" class="control-label col-xs-5"
														align="right" id="inputnames">FromDate</label>
													<div class="col-xs-7">
														<input type="text" id="fromdate" name="fromdate" readonly="readonly" 
															 class="form-control" value="" />
													</div>
												</div>



											</div>
											<div class="col-md-6">
											<div class="form-group row">
													<label for="inputEmail" class="control-label col-xs-5"
														style="text-align: right; line-height: 35px;">ToDate
													</label>
													<div class="col-xs-7">
														<input type="text" class="form-control" id="todate" readonly="readonly"
															name="todate" class="form-control" value="" /> 
															
															<input type="hidden" name="accessionName"
															id="hidden_accessionNo" value="" />
													</div>
												</div>
											</div>
											
											
											
										</div>
									</div>
                                 	<div class="col-md-12">
											     <div class="form-group row">
													<label for="inputEmail" class="control-label col-xs-2"
														style="text-align: right; line-height: 35px;">Title</label>
													   <div class="col-xs-7">
														<!-- <input type="text" id="title" name="title"readonly="readonly" 
															 class="form-control" style="width: 388%;"
															value="" /> -->
															<input type="text" id="hiddentitle" readonly="readonly" class="form-control" style="width: 98%;"  value=""  />
													</div>
												</div>
											</div>

								</div>

							</fieldset>
						</div>
						<div class="col-md-12"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top:-6px;margin-bottom: 10px;">
							<fieldset class="fieldset2">
								<legend class="lengend-text">Order
									By:</legend>
								<div class="row" style="margin-top: 10px;">
									<div class="col-md-6 clearfix ">
										<div class="form-group clearfix with" style="width: 200%;">

											<label style="margin-left:191px;"> <input type="radio" style="top: -2px;"
												class="radio-inline" name="order_by" id="subno"
												class="cencession" value="subno" checked="checked" />&nbsp;
												Subscriber No.
											</label> <label style="margin-left:2%;"><input type="radio" style="top: -2px;"
												class="radio-inline" name="order_by" class="cencession"
												id="name" value="name" />&nbsp;Name&nbsp;&nbsp;&nbsp; </label> <label style="margin-left:7px;">
												<input type="radio" class="radio-inline" name="order_by"
												style="top: -2px;" id="accno" class="cencession"
												value="accno" />&nbsp;Accession No.
											</label> <label style="margin-left:2%;"> <input type="radio" class="radio-inline"
												name="order_by" style="top: -2px;" id="title"
												class="cencession" value="title" />&nbsp;Title
											</label> <label style="margin-left:24px;"> <input type="radio" class="radio-inline"
												name="order_by" style="top: -2px;" id="author"
												class="cencession" value="author" />&nbsp;Author
											</label>

										</div>
									</div>

								</div>
							</fieldset>
						</div>
					</div>
					<div class="allstudenttable">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<table class='table allstudent' id='allstudent'>
								<thead>
									<tr>
										<th>Sl.No</th>
										<th>Subscriber No.</th>
										<th>Name</th>
										<th>Accession No.</th>
										<th>Title</th>
										<th>Author</th>
										<th>Date</th>
									</tr>
								</thead>

								<tbody>

								</tbody>
							</table>
						</div>
					</div>
					<div class='pagebanner'>
						<select id='show_per_page'>
							<option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option>
							<option value='500'>500</option>
						</select> <span class='numberOfItem'></span>
					</div>
					<div class='pagination pagelinks'></div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>