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

<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link href="CSS/Admin/Admission/StudentNew.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Student/AddStudentAppraisalReport.js"></script>

<style>

.edit.buttons {
	position: absolute;
    right: 110px;
    top: 52px;
}

.delete.buttons {
	position: absolute;
    right: 40px;
    top: 52px;
}


</style>
</head>

<body>

  <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>

	<div class="col-md-10" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" /><span id="pageHeading">Student
				Appraisal Action</span>
		</p>
		
 <input type="hidden" id="historyaccYear" 
 value="<logic:present name="historyaccYear" scope="request"><bean:write name="historyaccYear"/></logic:present>">
 
 <input type="hidden" id="historylocId" 
 value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>">
 
 <input type="hidden" id="historyclassname" 
 value="<logic:present name="historyclassname" scope="request"><bean:write name="historyclassname"/></logic:present>">
 
 <input type="hidden" id="historysectionid" 
 value="<logic:present name="historysectionid" scope="request"><bean:write name="historysectionid"/></logic:present>">
 
 <input type="hidden" id="historysearchvalue" 
 value="<logic:present name="historysearchvalue" scope="request"><bean:write name="historysearchvalue"/></logic:present>">
 
 <input type="hidden" id="historystatus" 
 value="<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>">
			<div id="admissionDialog" style="display: none">
				<div class="col-md-12">
					<div class="col-md-6 abc">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Appreciation<font color="red">*</font></label>
							<div class="col-xs-7">
								<select name="" class="form-control" id="actiontaken">
									<option value="">---------Select-------</option>
									<option value="Good">Good</option>
									<option value="Very Good">Very Good</option>
									<option value="excellent">excellent</option>

								</select>
							</div>
							
						</div>

						<div class="form-group clearfix">

							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Date<font color="red">*</font></label>
							<div class="col-xs-7">
								<input type="text" class="form-control" onkeypress="HideError()"
									name="entryDate" id="schedualdate" class="entryDates" >

							</div>
						</div>
						<!-- <div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Meeting Date</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" onkeypress="HideError()"
									name="meetingdate" id="meetingDate" >

							</div>
						</div> -->


					</div>


					<div class="col-md-6 abc">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Recommended By<font color="red">*</font></label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="recommented" ><input type="hidden" id="hiddenstaffId" value="" />

							</div>
						</div>
						<!-- <div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Meeting With </label>
							<div class="col-xs-7">
								<select name="" class="form-control" id="meetingwith">
									<option value="">---------Select-------</option>
									<option value="Father">Father</option>
									<option value="Mother">Mother</option>
									<option value="Guardian">Guardian</option>
								</select>
							</div>
							
						</div> -->


						<%-- <div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Meeting On</label>
							<div class="col-xs-7">
								<div id="datetimepicker3" class="input-append DatePicker">
									<input type="text" data-format="hh:mm:ss" size="8"
										readonly="readonly" name="starttime" id="starttime"
										onchange="HideError()" class="form-control"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="stratTime"></bean:write></logic:present>' />
									<span class="add-on"> <img src="./images/time1.jpg"
										width="30" height="30" class="add-on" />
									</span>
								</div>

							</div>
						</div> --%>


					</div>
					<div class="col-md-12 abc">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-2"
								style="text-align: left;"> Reason<font color="red">*</font></label>
							<div class="col-xs-10" style="padding-left: 40px;">
								<textarea style="border: 1px solid #ccc; width: 100%" maxlength="249" name="comment" id="remark" rows="3" ></textarea>

							</div>
						</div>
					</div>
				</div>
			</div>


			<div id="editDialog" style="display: none">
				<div class="col-md-12">
					<div class="col-md-6 abc">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Appreciation<font color="red">*</font></label>
							<div class="col-xs-7">
								<select name="" class="form-control" id="actiontaken1">
									<option value="">---------Select-------</option>
									<option value="Good">Good</option>
									<option value="Very Good">Very Good</option>
									<option value="excellent">excellent</option>

								</select>
							</div>
						</div>

						<div class="form-group clearfix">

							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Schedule Date<font color="red">*</font></label>
							<div class="col-xs-7">
								<input type="text" class="form-control" onkeypress="HideError()"
									name="entryDate" id="schedualdate1" class="entryDates" >

							</div>
						</div>
						<!-- <div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Meeting Date</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" onkeypress="HideError()"
									name="meetingdate" id="meetingDate1" >

							</div>
						</div> -->


					</div>


					<div class="col-md-6 abc">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Recommended By<font color="red">*</font></label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="recommented1"><input type="hidden" id="hiddenstaffId1" value="" />

							</div>
							<input type="hidden" id="hiddenstaffId2" value='' />
						</div>
						<!-- <div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Meeting With </label>
							<div class="col-xs-7">
								<select name="" class="form-control" id="meetingwith1">
									<option value="Null">---------Select-------</option>
									<option value="Father">Father</option>
									<option value="Mother">Mother</option>
									<option value="Guardian">Guardian</option>
								</select>
							</div>
						</div> -->

<input type="hidden" id="hiddenid"/> 
						<%-- <div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Meeting On</label>
							<div class="col-xs-7">
								<div id="datetimepicker4" class="input-append DatePicker">
									<input type="text" data-format="hh:mm:ss" size="8"
										readonly="readonly" name="starttime" id="starttime1"
										onchange="HideError()" class="form-control"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="stratTime"></bean:write></logic:present>' />
									<span class="add-on"> <img src="./images/time1.jpg"
										width="30" height="30" class="add-on" />
									</span>
								</div>

							</div>
						</div> --%>


					</div>
					<div class="col-md-12 abc">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-2"
								style="text-align: left;"> Remark <font color="red">*</font></label>
							<div class="col-xs-10" style="padding-left: 40px;">
								<textarea style="border: 1px solid #ccc; width: 100%"
									name="comment" id="remark1" rows="3"></textarea>

							</div>
						</div>
					</div>
				</div>
			</div>



			<div id="deleteDialog" style="display: none">
				<p>Are you sure you want to Remove?</p>
			</div>
		<div class="errormessagediv1" style="display: none;" align="center">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1" style="width: 30%;"><span
					class="validateTips1"></span></a>
			</div>
		</div>


		<div class="errormessagediv" style="display: none;" align="center">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<div id="successmessages" class="successmessagediv"
			style="display: none;">
			<div class="message-item">
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>
<input type="hidden" id="hiddenstartdate" value="<logic:present name="startDate"><bean:write name="startDate"/></logic:present>">
<input type="hidden" id="hiddenenddate" value="<logic:present name="enddate"><bean:write name="enddate"/></logic:present>">
		<!-- chiru -->

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion1"
							href="#collapseOne"
							style="color: #000000; vertical-align: text-top;"><h3 class="panel-title"> <i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Student Appraisal Action
						</h3></a>
					

					<div class="navbar-right">
						<span class="buttons" id="back1">Back</span>

					</div>
				</div>
				 
					<div id="collapseOne" class="panel-collapse collapse in "
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic
										Year</label>
									<div class="col-xs-7">
										<input type="text" name="academicYear" tabindex="1"
											onkeypress="HideError()" id="academicYear" maxlength="25"
											class="form-control" readonly="readonly"
											value='<logic:present name="studentSearchList" scope='request'><bean:write name="studentSearchList" property="academicYear"/></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<input type="text" name="schoolName" id="schoolNames"
											maxlength="25" class="form-control" readonly="readonly"
											value='<bean:write name="studentSearchList" property="branchName"/>' />
									</div>
								</div>
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student
										Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFullName" id="studentFullName"
											maxlength="25" class="form-control" readonly="readonly"
											value='<bean:write name="studentSearchList" property="studentFullName"/>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="admissionNo"
											id="admissionNo" onchange="" maxlength="25"
											readonly="readonly"
											value='<bean:write name="studentSearchList" property="studentAdmissionNo"></bean:write>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Roll No</label>

									<div class="col-xs-7">
										<input type="text" name="studentRollNo" id="studentRollNo"
											maxlength="25" class="form-control" readonly="readonly"
											value='<bean:write name="studentSearchList" property="studentrollno"/>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="classId" id="classId" maxlength="25"
											class="form-control" readonly="readonly"
											value='<bean:write name="studentSearchList" property="classname"/>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<input type="text" name="sectionId" tabindex="1"
											id="sectionId" maxlength="25" class="form-control"
											readonly="readonly"
											value='<bean:write name="studentSearchList" property="sectionnaem"/>' />
									</div>
								</div>

							</div>

							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div
											style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
											<img id="imagePreview" class="setImage" alt="image" src="#"
												style="height: 45mm; width: 45mm;">
											<div style="position: absolute; top: 0; height: 160px;">
												<!-- <input type="file" id="studentImageId1" name="studentImage" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;"> -->
											</div>

										</div>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student
										Status</label>
									<div class="col-xs-7">
										<input type="text" name="studentStatusId" id="studentStatusId"
											maxlength="25" class="form-control" readonly="readonly"
											value='<bean:write name="studentSearchList" property="studentStatus"/>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House</label>
									<div class="col-xs-7">
										<input type="text" name="houseId" tabindex="1"
											onkeypress="HideError()" id="houseId" maxlength="25"
											class="form-control" readonly="readonly"
											value='<bean:write name="studentSearchList" property="houseName"/>' />
									</div>
								</div>


								<input type="hidden" id="hstudentid" name="studentId"
									value="<bean:write name="studentSearchList" property="studentId"/>" />

								<input type="hidden" id="hacademicYearId" name="academicYearId"
									value="<bean:write name="studentSearchList" property="academicYearId"/>" />

								<input type="hidden" id="hschoolNameId" name="schoolNameId"
									value="<bean:write name="studentSearchList" property="locationId"/>" />

								<input type="hidden" id="photohiddenid" name="previousImage"
									value="<bean:write name="studentSearchList" property="studentPhoto" />">
							</div>
						</div>
				 
			</div>
		</div>
		<hr
			style="height: 1px; border: none; color: #333; background-color: #ddd;" />

		<div>
			<div class="slideTab clearfix">
				<div class="tab">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#reportHistory"
							id="reportHistory">Report History</a></li>
						<li><a data-toggle="tab" href="#contacts" id="contacts">Contacts</a></li>

						<span class="buttons" id="addconfidential" style="position: absolute; right: 38px; margin-top: 6px;">New</span>
					</ul>


					<div id="contacts" class="tab-pane">
						<div class="col-md-12"
							style="border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
							<div class="searchWrap">
								<div class="col-md-8" id=div2></div>
								<div id="studenttable"></div>
								<div id="individualstudenttable"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</html>
