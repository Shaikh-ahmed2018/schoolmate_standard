<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/backOffice/Teacher/AttendaceStatus.js"></script>



</head>

<body>

<input type="hidden" id="hiddenlocId" value="<logic:present name="hloc"><bean:write name="hloc" /></logic:present>">




  <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>

	<div class="content" id="div1">
		<div class="col-md-12 input-group" id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Student Attendance</span>
			</p>
		</div>



		<%-- <div class=" col-md-6">

 		<label class="hedderstyle form-control"
				style="margin: 10px 0px; width: 20% !important; border: none; font-family: Roboto Medium; font-size: 14px; font-weight: lighter; background: transparent; color: #000000 !important;"></label>

			<input type="text" name="year" style="width: 30%; margin: 25px 0px ; margin-left: 0%;height:35px;" 
							id="startdate" maxlength="25" class="form-control"  readonly="readonly" value="<logic:present name='StartDate'><bean:write name='StartDate'/></logic:present>" />

			<!-- <label
				style="margin: 20px 0px; width: 21%; border: none; font-family: Roboto Medium; font-size: 14px; font-weight: lighter; background: transparent; color: #000000 !important;"
				class="form-control"></label> -->
			<input type="text" name="year" style="width: 30%; margin: 25px 0px; margin-left: -1%;height:35px;" 
							id="enddate" maxlength="25" class="form-control"  readonly="readonly" value="<logic:present name='EndDate'><bean:write name='EndDate'/></logic:present>" />

			<!-- <span class="input-group-btn">
					<button class="btn btn-default" type="button" id="searchAttendanceList">
						<i class="fa fa-search"></i>
					</button>
				</span> -->


		</div> --%>



		<div class="successmessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>

		<div class="errormessagediv1" style="display: none;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1" style="width: 30%;"><span
					class="validateTips1"></span></a>
			</div>
		</div>



	  <div class="panel panel-primary">
			<div class="panel-heading clearfix">
				
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;">
					<h3 class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Student Attendance
					</h3></a>	
					
					
				<div class="navbar-right">
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
							<logic:equal value="STUATTADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<a href="StudentAttendanceActionPath.html?method=studentattendaceUploadEntry"
									style="margin: 0px 12px 0px 0px;" id="plus">
									<span class="buttons" style="margin-right: -14px;">New</span> </a> 
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUATTDWD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<span class="buttons" id="iconsimg" data-toggle="modal"data-target="#myModal">Download </span>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

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

   <input type="hidden" id="historyacademicId" 
	value='<logic:present name='historyacademicId' scope="request"><bean:write name="historyacademicId"/></logic:present>'/>

   <input type="hidden" id="historylocId" 
	value='<logic:present name='historylocId' scope="request"><bean:write name="historylocId"/></logic:present>'/>
	
	<input type="hidden" id="historyclassname" 
	value='<logic:present name='historyclassname' scope="request"><bean:write name="historyclassname"/></logic:present>'/>

   <input type="hidden" id="historysectionid" 
	value='<logic:present name='historysectionid' scope="request"><bean:write name="historysectionid"/></logic:present>'/>
	
	<input type="hidden" id="historyspecId" 
	value='<logic:present name='historyspecId' scope="request"><bean:write name="historyspecId"/></logic:present>'/>

   <input type="hidden" id="historystartdate" 
	value='<logic:present name='historystartdate' scope="request"><bean:write name="historystartdate"/></logic:present>'/>
	
	<input type="hidden" id="historyenddate" 
	value='<logic:present name='historyenddate' scope="request"><bean:write name="historyenddate"/></logic:present>'/>
   

		 <div id="collapseOne" class="accordion-body collapse in">     
		      <div class="row">
				<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 10pt; color: #000; padding-top: 1%;" id="txtstyle">
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Branch</label>
											<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
							</div>
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;"	id="inputnames">Class</label>
									<div class="col-xs-7">
										<select id="classId" name="classname" class="form-control" onChange="HideError()" required>
											<option value="all">ALL</option>
										</select>
									</div>
							</div>
							
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="section" name="section" class="form-control" onChange="HideError()">
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
							
							
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Start Date</label>
									<div class="col-xs-7">
										<input type="text" name="year" id="startdate" maxlength="25" onclick="clickHideError(this)" class="form-control"  readonly="readonly" value="<logic:present name='StartDate'><bean:write name='StartDate'/></logic:present>" />
									</div>
								</div>
							</div>
							
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 10pt; color: #000; padding-top: 1%;" id="txtstyle">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
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
								<input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Specialization</label>
									<div class="col-xs-7">
										<select id="specialization" name="specialization" class="form-control" onChange="HideError()">
											<option value="">-------------Select------------</option>
										</select>
									</div>
								</div>
								
								<!-- /**** This is not Required to Basic Module  ***/ -->
								<!-- <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Teacher</label>
									<div class="col-xs-7">
										<input type="text" name="teacher"  


			<div class="col-md-6" id="txtstyle">
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						align="right" id="inputnames">Branch</label>
					<div class="col-xs-7">
						<select id="locationname" name="locationnid" class="form-control"
							required>
							<option value="">-------------Select------------</option>
							<option value="all" selected>ALL</option>
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
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;" id="inputnames">Class</label>
					<div class="col-xs-7">
						<select id="classId" name="classname" class="form-control"
							onChange="HideError()" required>
							<option value="all">ALL</option>
						</select>
					</div>
				</div>


				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						id="inputnames" style="text-align: right; line-height: 35px;">Division</label>
					<div class="col-xs-7">
						<select id="section" name="section" class="form-control"
							onChange="HideError()">
							<option value="all">ALL</option>
						</select>
					</div>
				</div>


				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Start Date</label>
					<div class="col-xs-7">
						<input type="text" name="year" id="startdate" maxlength="25"
							class="form-control" readonly="readonly"
							value="<logic:present name='StartDate'><bean:write name='StartDate'/></logic:present>" />
					</div>
				</div>
			</div>
			<div class="col-md-6" id="txtstyle">

				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						align="right" id="inputnames">Academic Year</label>
					<div class="col-xs-7">
						<select id="Acyearid" name="accyear" class="form-control" required>
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
					<label for="inputPassword" class="control-label col-xs-4"
						id="inputnames" style="text-align: right; line-height: 35px;">Specialization</label>
					<div class="col-xs-7">
						<select id="specialization" name="specialization"
							class="form-control" onChange="HideError()">
							<option value="">-------------Select------------</option>
						</select>
					</div>
				</div>


				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4"
						id="inputnames" style="text-align: right; line-height: 35px;">Teacher</label>
					<div class="col-xs-7">
						<!-- <input type="text" name="teacher"  

											 id="teacher" readonly onChange="HideError()"
											maxlength="25" class="form-control"
											value=''/>

										<select id="teacher" name="teacher" class="form-control" onChange="HideError()">
											<option value="">-------------Select------------</option>
										</select>
											
									</div>
									<input type="hidden" id="teacherid"></input>
								</div> -->
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">End Date</label>
									<div class="col-xs-7">
										<input type="text" name="year" id="enddate" maxlength="25" class="form-control" onclick="clickHideError(this)"  readonly="readonly" value="<logic:present name='EndDate'><bean:write name='EndDate'/></logic:present>" />
									</div>
								</div>
								<!-- <div class="form-group clearfix">

						<select id="teacher" name="teacher" class="form-control"
							onChange="HideError()">
							<option value="">-------------Select------------</option>
						</select>

					</div>
					<input type="hidden" id="teacherid"></input>
				</div>

				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">End Date</label>
					<div class="col-xs-7">
						<input type="text" name="year" id="enddate" maxlength="25"
							class="form-control" readonly="readonly"
							value="<logic:present name='EndDate'><bean:write name='EndDate'/></logic:present>" />
					</div>
				</div>
				<!-- <div class="form-group clearfix">

									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<select id="studentname" name="studentname" class="form-control" onChange="HideError()">
											<option value="">-------------Select------------</option>
										</select>
									</div>
									<input type="hidden" id="teacherid"></input>
								</div> -->

								<!-- <button type="button" class="col-md-offset-5 buttons style" id="search">Filter</button>
							<button type="reset" class="col-md-offset-1 buttons style" id="reset" style="margin-left :0">Reset</button> -->	
						 </div>
					</div>	 
			

				<!-- <button type="button" class="col-md-offset-5 buttons style" id="search">Filter</button>
							<button type="reset" class="col-md-offset-1 buttons style" id="reset" style="margin-left :0">Reset</button> -->
			


			

				<div class="panel-body"	style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					

				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

					 
						<table class="table" id="allstudent">
							<thead>

							<tr>
							<th>Sl.No</th>
							<th>Attendance Date</th>
							<th>Class & Division</th>
							<th>Specialization</th>
							<th>Total Strength</th>
							<th>Total Present</th>
							<th>Total Absent</th>
							<!-- <th>Total Holiday</th> -->
							<th>Total Leave</th>
							</tr>
							</thead>
							<tbody>
								

							</tbody>
						</table>
				
					
					<div class='pagebanner'>
							<select id='show_per_page'><option value='50'>50</option>
								<option value='100'>100</option>
								<option value='200'>200</option>
								<option value='300'>300</option>
								<option value='400'>400</option>
								<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
						<div class='pagination pagelinks' style="margin-bottom: 10px;"></div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>