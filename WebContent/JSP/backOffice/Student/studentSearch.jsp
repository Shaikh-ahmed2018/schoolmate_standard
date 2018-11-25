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
<!-- <link href="CSS/Admin/StudentNew.css" rel="stylesheet" type="text/css"> -->


<script type="text/javascript" src="JS/backOffice/Student/StudentSearch.js"></script>
<!-- <script type="text/javascript" src="JS/backOffice/Student/individualStudentSearch.js"></script> -->
<script type="text/javascript">
$(document).ready(function(){
	$("#close").click(function(){
		$(".showHide").show();
	});
});
</script>

</head>


<body>
<div class="content" id="div-main">
<input type="hidden" id="hiddenlocId" value="<logic:present name="hloc"><bean:write name="hloc" /></logic:present>">

<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -80px;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
<div id="pageLoader" style="display: none;">
<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" style="background-color:#eeeeee !important;" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion1" href="#" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title"><!-- <i class="glyphicon glyphicon-menu-hamburger"></i> -->&nbsp;&nbsp;Student Detail
							</h3></a>
						
						
						<div class="navbar-right">
							<span class="search"  style="top:3px">  
							<label style="text-align: right;font-family: Roboto,sans-serif; font-size: 10pt; color: #000;font-weight: 500;padding: 4px;">Select Action</label>	 
							 				<select class="nil" id="nil1" onclick="HideError(this)" style="color:#000000;font-family:Roboto,sans-serif;font-size: 14px;padding: 2px">
							 					<option value="">-----Select-----</option>
							 					<option value="adm_Form">Registration Form</option>
							 					<option value="conf_Report">Disciplinary Action</option>
							 					<option value="mis_Report">MIS Report</option>
							 					<!-- <option value="tc">Transfer Certificate</option> -->
											 </select>
							</span>
							<span class="buttons" style="font-family: Roboto,sans-serif;top:0px;margin-right: 1px;margin-left:4px;" id="goPage" class="save">Go</span>
							<span id="close" style="font-family: Roboto,sans-serif;top:0px;" class="buttons">Close</span>
						</div>
					</div>
					<div id="collapseOnePop" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="academicYear" tabindex="1"	onkeypress="HideError()" id="academicYear"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<input type="text" name="schoolName" tabindex="1"	onkeypress="HideError()" id="schoolName"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFullName" tabindex="1"	onkeypress="HideError()" id="studentFullName"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="admissionNo" tabindex="4" onkeypress="HideError()" id="admissionNo"
											onchange="" maxlength="25" readonly="readonly" 
											value='' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Roll No</label>

									<div class="col-xs-7">
										<input type="text" name="studentRollNo" tabindex="1"	onkeypress="HideError()" id="studentRollNo"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="classId" tabindex="1"	onkeypress="HideError()" id="classId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<input type="text" name="sectionId" tabindex="1"	onkeypress="HideError()" id="sectionId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								
								 <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Bus Route </label>
									<div class="col-xs-7">
										<input type="text" name="routeId" tabindex="1"	onkeypress="HideError()" id="routeId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Bus Boarding Point</label>
									<div class="col-xs-7">
										<input type="text" name="stageId" tabindex="1"	onkeypress="HideError()" id="stageId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
							</div>
							
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
												<img id="imagePreview" class="setImage" alt="image" src="#" style="height: 45mm; width: 45mm;">
												<div style="position: absolute;top: 0;height: 160px;">
												<input type="file" id="studentImageId1" name="studentImage" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;">
												</div>
										</div>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student Status</label>
									<div class="col-xs-7">
										<input type="text" name="studentStatusId" tabindex="1"	onkeypress="HideError()" id="studentStatusId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Disciplinary Action</label>
									<div class="col-xs-7">
										<input type="text" name="confidentialStatusId" tabindex="1"	onkeypress="HideError()" id="confidentialStatusId" 
											maxlength="25" class="form-control" readonly="readonly" 
											value='' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House</label>
									<div class="col-xs-7">
										<input type="text" name="houseId" tabindex="1"	onkeypress="HideError()" id="houseId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Second Language</label>
									<div class="col-xs-7">
										<input type="text" name="secondLanguageId" tabindex="1"	onkeypress="HideError()" id="secondLanguageId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Third Language</label>
									<div class="col-xs-7">
										<input type="text" name="thirdLanguageId" tabindex="1"	onkeypress="HideError()" id="thirdLanguageId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='' />
									</div>
								</div>
								
								<input type="hidden" id="hstudentid" name="studentId"
									value=""/>
									
								<input type="hidden" id="hacademicYearId" name="academicYearId"
									value=""/>
									
								<input type="hidden" id="hschoolNameId" name="schoolNameId"
									value=""/>
								
								<input type="hidden" id="photohiddenid" name="previousImage"
									value="">
							</div>
						</div>
						<hr style="height:1px;border:none;color:#333;background-color:#ddd;"/>
					
					
						<div>
						<div class="slideTab clearfix">
						<div class="tab">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#contacts"  id="contacts">Contacts</a></li>
								<li><a data-toggle="tab" href="#classHistory" id="classHistory">Class History</a></li>
							<li><a data-toggle="tab" href="#ContactAddr" id="ContactAddr">Address</a></li>
							</ul>
						
							<div id="contacts" class="tab-pane">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap">
									<div class="col-md-8" id=div2></div>
									<div id="studenttable"></div>
									<div id="individualstudenttable"></div>	
									<div id="Addressstudenttable"></div>	
								</div>
								</div>
							</div>
							
						</div>
					</div>
					</div>
					
				
				
			</div>

</div>
</div>


	<div class="showHide">

		<p>
			<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;<span id="pageHeading">Student
				Search</span>
		</p>
		<div class="panel-body clearfix"
			style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;padding-top: 0;">

		</div>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">

					<a data-toggle="collapse" data-parent="#accordion1"
						href="#collapseOne"
						style="color: #000000; vertical-align: text-top;">
						<h3 class="panel-title" style="vertical-align: super;">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Student Search
						</h3>
					</a>

				</div>
				<div class="errormessagediv" style="display: none;" align="center">
						<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning" style="width: 30%;"><span
					class="validateTips"></span></a>
				</div>
		</div>
				<div id="collapseOne" class="panel-collapse collapse in "
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body own-panel">
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 10pt; color: #000;">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
									<select id="locationname" name="locationnid" class="form-control" required>
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
									style="text-align: right; line-height: 35px;">Sort by</label>
								<div class="col-xs-7">

									<select class="form-control" onkeypress="HideError()"
										name="sortingby" id="sortingby">
										<option value="Alphabetical">Alphabetical Order</option>
										<option value="Gender">Gender Wise</option>
										<option value="Admission">Admission No.</option>
										<option value="House">House Wise</option>
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
								<div class="col-xs-5">
								
								</div>
								<div class="col-xs-7">
								<span class="buttons" id="search">Search</span>
							    <span class="buttons" id="resetbtn">Reset</span>
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
                        <input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">

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




                              <!--  <div class="form-group clearfix" id="hiddenstatus">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Specialization</label>
									<div class="col-xs-7">
										<select id="specialization" name="specialization" class="form-control" required>
											<option value="">----------Select--------</option>
											
										</select>
									</div>
								</div>
 -->




								<div class="form-group clearfix" id="orderbyalp" style="display:none;">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"></label>
								<div class="col-xs-7" id="orderbyalp">
									<input type="radio" value="ASC" name="sorting" class="sorting"
										id="ASC">
									<p1>Ascending</p1>
									<input type="radio" value="DESC" style="margin-left: 30px"
										name="sorting" class="sorting" id="DESC">
									<p2>Descending</p2>
								</div>
								</div>
								
								
								
							
								
								
								
								<div class="form-group clearfix" id="orderbygen" style="display:none;">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"></label>
								<div class="col-xs-7" id="orderbygen">
									<input type="radio" value="FEMALE" name="sorting1" class="sorting"
										id="Female">
									<p3>Female</p3>
									<input type="radio" value="MALE" style="margin-left: 30px"
										name="sorting1" class="sorting" id="Male" >
									<p4>Male</p4>
								</div>
								</div>
							<div class="form-group clearfix" id="house" style="display:none;">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"> House</label>
								<div class="col-xs-7">

									<select class="form-control" name="housename" id="housename">
										<option value="all">ALL</option>
									</select>
								</div>
							</div>
							
							
                            <div class="form-group clearfix" id="hiddenstatus">
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
						<input type="hidden" name="Acyearid" id="Acyearid"
							value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>

						
							<div class="panel-body"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

								<logic:present name="studentList" scope="request">
									<table class="table indiviualsearch" id="allstudent">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Admission No</th>
												<th>Student Name</th>
												<th>Roll No</th>
												<th>Class and Division</th>
												<th>House Name</th>
												<th>Status</th>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</logic:present>


							</div>
							<div class='pagebanner'>
								<select id='show_per_page'><option value='50'>50</option>
									<option value='100'>100</option>
									<option value='200'>200</option>
									<option value='300'>300</option>
									<option value='400'>400</option>
									<option value='500'>500</option></select> <span class='numberOfItem'></span>
							</div>
							<div class='pagination pagelinks' style='top: -9px'></div>
						</div>

					</div>
			</div>
		</div>
	</div>
	</div>
	</body>
</html>
