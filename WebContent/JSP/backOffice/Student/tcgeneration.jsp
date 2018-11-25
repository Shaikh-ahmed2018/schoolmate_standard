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

<script type="text/javascript" src="JS/backOffice/Student/tcgeneration.js"></script>
 
<style>
.tab-pane.active {
	display: block !important;
}
</style>


<script>
	function handle(e) {
		if (e.keyCode === 13) {
			$("input:checkbox").prop('checked', false);
			e.preventDefault(); // Ensure it is only this code that rusn
			searchList();
		}
	}

	function handle1(e) {
		if (e.keyCode === 13) {
			$("input:checkbox").prop('checked', false);
			e.preventDefault(); // Ensure it is only this code that rusn
			studentDemotedListSearch();
		}
	}
</script>
</head>

<body>
  <input type="hidden" id="hiddenlocId" value="<logic:present name="hloc"><bean:write name="hloc" /></logic:present>">
  
  <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
  
	<div class="col-md-10" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
				id="pageHeading">TC Generate</span>
		</p>

		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">

					<a data-toggle="collapse" data-parent="#accordion1"
						href="#collapseOne"
						style="color: #000000; vertical-align: text-top;">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;TCGenerate
						</h3>
					</a>

				</div>

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

				<div id="dialog"></div>
				<div id="collapseOne" class="panel-collapse collapse in "
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body own-panel">

						<div>
							<div class="slideTab clearfix">
								<div class="tab">
									<ul class="nav nav-tabs">
										<li class="active"><a data-toggle="tab"
											href="#notGeneratedDiv" id="notGenerated"
											class="notGenerated">Student Details</a></li>
										<li><a data-toggle="tab" href="#GeneratedDiv"
											id="Generated" class="Generated">TC Generated</a></li>

										<logic:present name="UserDetails" scope="session">
											<logic:iterate id="daymap" name="UserDetails"
												property="permissionmap" scope="session">
												<logic:equal value="TCGADD" name="daymap" property="key">
													<logic:equal value="true" name="daymap" property="value">
														<span class="buttons" id="generatetc" style="float: right; margin-right: 20px; margin-top: 5px;">GenerateTC</span>
													</logic:equal>
												</logic:equal>
											</logic:iterate>
										</logic:present>
										
										<logic:present name="UserDetails" scope="session">
											<logic:iterate id="daymap" name="UserDetails"
												property="permissionmap" scope="session">
												<logic:equal value="TCGDWD" name="daymap" property="key">
													<logic:equal value="true" name="daymap" property="value">
														<span class="buttons" id="download"
															style="float: right; margin-right: 20px; margin-top: 5px; width: 124px; text-align: center;"
															hidden="hidden">Download </span>
													</logic:equal>
												</logic:equal>
											</logic:iterate>
										</logic:present>
										<span class="buttons" id="TCCancel" style="float: right;margin-right: 20px;margin-top: 5px;width:124px;text-align: center;" hidden="hidden">TC Cancel</span>

									</ul>

									<div class="modal fade" id="myModal" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel">
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
													<span id="excelDownload" hidden><img
														src="images/xl.png" class="xl"></span>
													<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
													<span id="pdfDownload"><img src="images/pdf.png"
														class="pdf"></span>
												</div>

											</div>
										</div>
									</div>



									<div id="notGeneratedDiv" class="tab-pane active"
										style="display: none;">
										<div class="col-md-12"
											style="border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
											<div class="searchWrap"
												style="margin-top: 10px; margin-bottom: -5px;">
												<div class="col-md-8 clearfix" id=div2></div>
												<div class="col-md-6 clearfix"
													style="font-family: Roboto,sans-serif; font-size: 10pt; color: #000;">

													<div class="form-group clearfix">
														<label for="inputPassword" class="control-label col-xs-5"
															style="text-align: right; line-height: 35px;">Branch</label>
														<div class="col-xs-7">
															<select id="locationname" name="locationnid"
																class="form-control" onclick="HideError(this)" required>
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
															style="text-align: right; line-height: 35px;">
															Class</label>
														<div class="col-xs-7">

															<select class="form-control" onkeypress="HideError(this)"
																name="classname" id="classname">
																<option value="all">----------Select----------</option>
															</select>
														</div>
													</div>

													<div class="form-group clearfix">
														<label for="inputPassword" class="control-label col-xs-5"
															style="text-align: right; line-height: 35px;">Sort
															by</label>
														<div class="col-xs-7">

															<select class="form-control" onkeypress="HideError(this)"
																name="sortingby" id="sortingby">
																<option value="Alphabetical">Alphabetical Order</option>
																<option value="Gender">Gender Wise</option>
																<option value="Admission">Admission No.</option>
															</select>

														</div>
													</div>


													<div class="form-group clearfix">
														<label for="inputPassword" class="control-label col-xs-5"
															style="text-align: right; line-height: 35px;">Search</label>
														<div class="col-xs-7">
															<input type="text" class="form-control" id="searchvalue"
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
															<select id="Acyearid" name="accyear" class="form-control" onclick="HideError(this)"
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

			               <input type="hidden" id="hiddenboardname" value="<logic:present name="boardname"><bean:write name="boardname"/></logic:present>">
                <input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">
                
													<div class="form-group clearfix">
														<label for="inputPassword" class="control-label col-xs-5"
															style="text-align: right; line-height: 35px;">Division</label>
														<div class="col-xs-7">
															<select id="sectionid" name="sectionid"
																class="form-control" onclick="HideError(this)" required>
																<option value="all">----------Select----------</option>
															</select>
														</div>
													</div>

													<div class="form-group clearfix">
														<label for="inputPassword" class="control-label col-xs-5"
															style="text-align: right; line-height: 35px;"></label>
														<div class="col-xs-7" id="orderby">
															<input type="radio" value="ASC" name="sorting"
																class="sorting" id="ASC" checked>
															<p1>Ascending</p1>
															<input type="radio" value="DESC"
																style="margin-left: 30px" name="sorting" class="sorting"
																id="DESC" title="Male">
															<p2>Descending</p2>

															<input type="radio" value="ASC" name="sorting1"
																class="sorting" id="Female" checked hidden>
															<p3 hidden>Female</p3>
															<input type="radio" value="DESC"
																style="margin-left: 30px" name="sorting1"
																class="sorting" id="Male" hidden>
															<p4 hidden>Male</p4>
														</div>
													</div>
												</div>

												<input type="hidden" name="Acyearid" id="Acyearid"
													value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
												<div id="collapseOne" class="accordion-body collapse in">
													<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
													
													<table class="table" id="allstudent">
													<thead>
													<tr>
													<th><input type="checkbox" name="selectall" style="text-align: center" id="selectall"></th>
													<th style="text-align: center;">Admission No</th>
													<th style="text-align: left;">Student Name</th>
													<th>Roll No</th>
													<th>Class and Division</th>
													</tr>
													</thead>
													<tbody>
													</tbody>
													</table>
													<div id='stupagination'>
													</div>
													</div>
												</div>
											</div>
										</div>
									</div>


									<div id="GeneratedDiv" class="tab-pane" style="display: none;">
										<div class="col-md-12" >
											<div class="searchWrap" style="margin-top: 10px; margin-bottom: -5px;">
												<div class="col-md-8" id=div2></div>
												<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 10pt; color: #000;">

													<div class="form-group clearfix">
														<label for="inputPassword" class="control-label col-xs-5"
															style="text-align: right; line-height: 35px;">Branch</label>
														<div class="col-xs-7">
															<select id="locationname1" name="locationnid" onclick="HideError(this)"
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
															style="text-align: right; line-height: 35px;">
															Class</label>
														<div class="col-xs-7">

															<select class="form-control" onkeypress="HideError(this)"
																name="classname" id="classname1">
																<option value="all">ALL</option>
															</select>
														</div>
													</div>

													<div class="form-group clearfix">
														<label for="inputPassword" class="control-label col-xs-5"
															style="text-align: right; line-height: 35px;">Sort
															by</label>
														<div class="col-xs-7">

															<select class="form-control" onkeypress="HideError(this)"
																name="sortingby" id="sortingby1">
																<option value="Alphabetical">Alphabetical Order</option>
																<option value="Gender">Gender Wise</option>
																<option value="Admission">Admission No.</option>
															</select>

														</div>
													</div>


													<div class="form-group clearfix">
														<label for="inputPassword" class="control-label col-xs-5"
															style="text-align: right; line-height: 35px;">Search</label>
														<div class="col-xs-7">
															<input type="text" class="form-control" id="searchvalue1"
																Placeholder="Search......" onkeypress="handle1(event)"
																value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
														</div>
													</div>
									
														
													<div class="form-group clearfix">
														<div class="col-xs-5"></div>
														<div class="col-xs-7">
														<span type="button" class="buttons" id="search1">Search</span>
														<span type="reset" class="buttons" id="resetbtn1"
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
															<select id="Acyearid1" name="accyear" onclick="HideError(this)"
																class="form-control" required >
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


													<div class="form-group clearfix">
														<label for="inputPassword" class="control-label col-xs-5"
															style="text-align: right; line-height: 35px;">Division</label>
														<div class="col-xs-7">
															<select id="sectionid1" name="sectionid"
																class="form-control" onclick="HideError(this)" required>
																<option value="all">ALL</option>
															</select>
														</div>
													</div>

													<div class="form-group clearfix">
														<label for="inputPassword" class="control-label col-xs-5"
															style="text-align: right; line-height: 35px;"></label>
														<div class="col-xs-7" id="orderby1">
															<input type="radio" value="ASC1" name="sorting2"
																class="sorting" id="ASC1" checked>
															<p5>Ascending</p5>
															<input type="radio" value="DESC1"
																style="margin-left: 30px" name="sorting2"
																class="sorting" id="DESC1" title="Male">
															<p6>Descending</p6>

															<input type="radio" value="ASC1" name="sorting3"
																class="sorting" id="Female1" checked hidden>
															<p7 hidden>Female</p7>
															<input type="radio" value="DESC1"
																style="margin-left: 30px" name="sorting3"
																class="sorting" id="Male1" hidden>
															<p8 hidden>Male</p8>
														</div>
													</div>
												</div>
												<div id="demotedTable"></div>
											<div id="generateStu">
											
											</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</html>
