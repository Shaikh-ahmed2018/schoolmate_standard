<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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

<script type="text/javascript" src="JS/backOffice/Fee/NewFeeCollection.js"></script>

</head>

<body class="feeconcession">

	<div class="content" id="div1">
		<div class="searchWrap">
			<div class="" id="div2">
				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Fee Collection</span>
				</p>
			</div>
		</div>
			<div class="panel panel-primary">
			<div class="panel-heading clearfix">
		
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Fee Collection
					</h3> </a>

					</div>

   <input type='hidden' id='historylocId' 
	value='<logic:present name='historylocId' scope='request'><bean:write name='historylocId'></bean:write></logic:present>'/>
	
	<input type='hidden' id='historyacademicId' 
	value='<logic:present name='historyacademicId' scope='request'><bean:write name='historyacademicId'></bean:write></logic:present>'/>
	
	<input type='hidden' id='historyclassname' 
	value='<logic:present name='historyclassname' scope='request'><bean:write name='historyclassname'></bean:write></logic:present>'/>
	
	<input type='hidden' id='historysectionId' 
	value='<logic:present name='historysectionId' scope='request'><bean:write name='historysectionId'></bean:write></logic:present>'/>
	
	<input type='hidden' id='historysearch' 
	value='<logic:present name='historysearch' scope='request'><bean:write name='historysearch'></bean:write></logic:present>'/>
		
					<div id="collapseOne" class="panel-collapse collapse in "
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">

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
											<option value="all">ALL</option>
										</select>

										<%-- <select id="" name="" class="form-control" required>
											<option value="%%">All</option>

											<logic:present name="">
												<logic:iterate id="a" name="">
													<option	value="<bean:write name="" property=""/>">
														<bean:write name="" property=""/>
													</option>
												</logic:iterate>
											</logic:present>
										</select> --%>
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Search</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue"
											Placeholder="Search......"
											value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
								
									<div class="form-group clearfix">
									<div class="col-xs-5"></div>
									<div class="col-xs-7">
										<span type="button" class="buttons" id="search">Search</span>
									<span type="reset" class="buttons" id="resetbtn">Reset</span>
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

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control"
											required>
											<option value="all">----------Select----------</option>
										</select>
									</div>
								</div>

								
							</div>
							<input type="hidden" name="Acyearid" id="Acyearid"
								value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
							<%-- <input type="hidden" name="clasdetailids" id="classdetailid" value='<logic:present name="studentdetailslist" scope="request"><logic:iterate id="studentdetailslist" name="studentdetailslist"><bean:write name="studentdetailslist" property="classDetailId"/></logic:iterate></logic:present>'></input> --%>



							<div id="collapseOne" class="accordion-body collapse in">
								<div class="panel-body"
									style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">


									<table class="table" id="allstudent" width="100%">
										<thead>
											<tr>
												<th>Sl.No.</th>
												<th class="sortable">Academic Year</th>
												<th class="sortable">Admission No</th>
												<th class="sortable">Student Name</th>
												<th class="sortable">Class and Section</th>
												<th class="sortable">Photo</th>
												<th class="sortable">Status</th>

											</tr>
										</thead>
										<tbody></tbody>
									</table>
									<div class="pagebanner">
										<select id="ShowPerPage"><option value="50">50</option>
											<option value="100">100</option>
											<option value="200">200</option>
											<option value="300">300</option>
											<option value="400">400</option>
											<option value="500">500</option>
										</select> <span class='numberOfItem'></span>
									</div>
									<div class="pagination pagelinks" style="margin-bottom: 10px"></div>

								</div>
								 
							</div>
						</div>
					</div>
					</div>
					</div>
					
</body>
</html>