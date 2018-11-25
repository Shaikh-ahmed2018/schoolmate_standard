<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">
 
<head>


<script src="JS/backOffice/Transport/SettransportDetails.js"></script>
<script>
</script>
</head>
<style>
.glyphicon:hover {
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

.Pending {
	background-color: #FF0000;
	min-width: 80px;
	display: inline-block;
	text-align: center;
	color: #fff;
}

.Completed {
	background-color: rgba(0, 158, 0, 0.66);
	min-width: 80px;
	display: inline-block;
	text-align: center;
	color: #fff;
}
.table {
    width: 70%;
    max-width: 100%;
    margin-bottom: 20px;
    margin: 0 auto;
}
.allstudent th:nth-child(1) {
	text-align: center;
	width:10%;
}
#allstudent th:nth-child(1) {
	text-align: center;
	width:200px;
}
#allstudent th:nth-child(2) {
	width: 250px;
}
#allstudent th:nth-child(3) {
	width: 200px;
}
#allstudent th:nth-child(4) {
	width: 250px;
}
#allstudent td{
	text-align: center;
}
 
.scrollbar {
	overflow-y: scroll;
	height: 250px;
}
.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
    background-color: white;
    /* opacity: 1; */
}
.month{
width:100%;
}

.ui-dialog{
    position:fixed;
    top:130px !important;          
}

#allstudent th:nth-child(5){
	width: 17%;
} 

</style>


<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="content" id="div1">
		<div id="dialog1"></div>
		<div id="dialog" style="display:none;">
		<input type="checkbox" name="selectBoxD"  class="selectAll" value=""/> <span>Select All</span>
		<table class="table allstudent table-bordered month">
		<thead>
			<tr>
			<th>Select</th>
			<th>Month</th>
			</tr>
		</thead>
		<tbody>
		<logic:present name="monthList">
			<logic:iterate name="monthList" id="monthList">
			<tr>
				<td><input type="checkbox" name="selectList" class="select" id='<bean:write name="monthList" property="monthNo"/>' value='<bean:write name="monthList" property="monthNo"/>'/></td>
				<td class="monthname"><bean:write name="monthList" property="months"/></td>
			</tr>
		</logic:iterate>
		</logic:present>
		</tbody>
		</table>
	</div>
	
	
		<p class="transportheader">
			<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp<span
				id="pageHeading"><logic:present name='heading'><bean:write name="heading"></bean:write></logic:present></span>
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

    <input type="hidden" id="historylocId"
    value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"></bean:write></logic:present>' />
	
	<input type="hidden" id="historyacademicId"
    value='<logic:present name="historyacademicId" scope="request"><bean:write name="historyacademicId"></bean:write></logic:present>' />
    
    <input type="hidden" id="historyclassname"
    value='<logic:present name="historyclassname" scope="request"><bean:write name="historyclassname"></bean:write></logic:present>' />
    
    <input type="hidden" id="historysectionid"
    value='<logic:present name="historysectionid" scope="request"><bean:write name="historysectionid"></bean:write></logic:present>' />
    
    <input type="hidden" id="historysearch"
    value='<logic:present name="historysearch" scope="request"><bean:write name="historysearch"></bean:write></logic:present>' />
    
    <input type="hidden" id="historyistransport"
    value='<logic:present name="historyistransport" scope="request"><bean:write name="historyistransport"></bean:write></logic:present>' />
    
		<div class="panel panel-primary">
			<div class="panel-heading clearfix" role="tab" id="headingOne">
				<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title" style="color: #000000;vertical-align: text;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Route 
							Details
						</h3></a>
				<div class="navbar-right">
						<span class="buttons" id="waivedOff" style="display:none;">Waived Off</span> 
						<span class="buttons" id="save">Save</span> 
		 				<span class="buttons" id="back1">Back</span>
				</div>
			</div>
			<div id="gradeOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							<div class='clearfix'>
			<div class="col-md-12" style="margin-top: 10px;">
				<div class="col-md-6" id="txtstyle"
					style="font-size: 13px; color: #000;">
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Academic
							Year</label>
						<div class="col-xs-6">
							<input type="text" readonly="readonly" name="accyear"
								onkeypress="HideError()" id="accyear" class="form-control"
								value='<logic:present name="accyname" scope="request" ><bean:write name="accyname" ></bean:write></logic:present>' />
						</div>
						<input type="hidden" name="hiddenaccyid" id="hiddenaccyid"
							value='<logic:present name="accyearid" scope="request"><bean:write name="accyearid"></bean:write></logic:present>' />
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Branch</label>
						<div class="col-xs-6">

							<input type="text" readonly="readonly" name="accyear"
								onkeypress="HideError()" id="locationId" class="form-control"
								value='<logic:present name="currentlocation" scope="request" ><bean:write name="currentlocation" ></bean:write></logic:present>' />
						</div>
						<input type="hidden" name="hiddenlocid" id="hiddenlocid" 
						value='<logic:present name="locid" scope="request"><bean:write name="locid"></bean:write></logic:present>'/>
					</div>

					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Student Name</label>
						<div class="col-xs-6">
								<input type="text" readonly="readonly" name="studentname"
									onkeypress="HideError()" id="nameofstudent" class="form-control"
									value='<logic:present name="studentDetailsList" scope="request" ><bean:write name="studentDetailsList" property="student_name"/></logic:present>' />
						</div>
						<input type="hidden" name="hiddenstudentid" id="hiddenstudentid"
							value='<logic:present name="hiddenstudentid" scope="request"><bean:write name="hiddenstudentid"></bean:write></logic:present>' />

					</div>
				</div>
			</div>

			<div class="col-md-12" style="margin-bottom:-107px">
				
				<div class="col-md-6" id="txtstyle"
					style="font-size: 13px; color: #000;">
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Admission Number</label>
						<div class="col-xs-6">

							<input type="text" readonly="readonly" name="admissionno"
								onkeypress="HideError()" id="subcode" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="admisssion_no"></bean:write></logic:present>' />
						</div>
						<input type="hidden" name="hiddenclassid" id="hiddenclassid"
							value='<logic:present name="subid" scope="request"><bean:write name="subid"></bean:write></logic:present>' />
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Class</label>
						<div class="col-xs-6">

							<input type="text" readonly="readonly" name="classid"
								onkeypress="HideError()" id="classid" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="classname"></bean:write></logic:present>'  />
						</div>
						<input type="hidden" name="classidhidden" id="classidhidden"
							value='<logic:present name="classId" scope="request"><bean:write name="classId"></bean:write></logic:present>' />
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Address</label>
						  <div class="col-xs-6">
							<textarea name="identificationMarks" style="resize: none" rows="5" id="identificationMarksId" readonly="true" class="form-control"><logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="address"></bean:write></logic:present></textarea>
						  </div>
						</div>
					</div>

				<div class="col-md-6" id="txtstyle" style="font-size: 13px; color: #000;top: -117px;">

					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;"></label>
						<div class="col-xs-7">
							<div style="border: 1px solid #B3B0B0; margin-bottom: 10px;width:172px;height:172px;margin-left:-42px;">
								<img id="imagePreview" class="setImage" alt="image" src="#"
									style="height: 45mm; width: 45mm;">
							</div>
						</div>
					</div>
				  
				  <div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Student
							Status</label>
						<div class="col-xs-6">
							<input type="text" readonly="readonly" name="studentname" onkeypress="HideError()" id="studentname" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="student_status"></bean:write></logic:present>' />
						</div>
						<input type="hidden" name="hiddenstudentid" id="hiddenstudentid" value='<logic:present name="hiddenstudentid" scope="request"><bean:write name="hiddenstudentid"></bean:write></logic:present>' />
					</div>

					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Division</label>
						<div class="col-xs-6">
							<input type="text" readonly="readonly" name="sectionid"
								onkeypress="HideError()" id="sectionid" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="section_name"></bean:write></logic:present>'  />
						</div>
						<input type="hidden" name="hiddensectionid" id="hiddensectionid"
							value='<logic:present name="sectionId" scope="request"><bean:write name="sectionId"></bean:write></logic:present>' />
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Transport
							Status</label>
						<div class="col-xs-6">
							<input type="text" readonly="readonly" name="examName"
								onkeypress="HideError()" id="examName" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="transport_status"></bean:write></logic:present>'  />
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="hiddenImage" value="<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="studentImage"/></logic:present>"/>
		<input type="hidden" id = "hiddenstuid" value='<logic:present name="stuid"><bean:write name="stuid"/></logic:present>'/>
		<input type="hidden" id="istransport" value='<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="status"/></logic:present>'>
		<input type="hidden" id="stageid" value='<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="stage_id"/></logic:present>'>
		<input type="hidden" id="routeid" value='<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="routeCode"/></logic:present>'>
		<input type="hidden" id="hiddenmonth" value="<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="noofmonths"/></logic:present>"/>
 		<input type="hidden" id="startmonth" value="<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="stmonth"/></logic:present>"/>
		<input type="hidden" id="endmonth" value='<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="endmonth"/></logic:present>'/>
		<input type="hidden" id="hiddenvehicle" value='<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="vehicleType"/></logic:present>'/>
		<hr style="height:1px;border:none;color:#333;background-color:#ddd;"/>
			<div>
		<div class="slideTab clearfix">
						<div class="tab">
						<ul class="nav nav-tabs">
								<li class="active" style="margin-bottom: 4px;margin-left: 6px;"><span class="buttons" id="contacts">Route Details</span></li></ul>
				<div id="contacts" class="tab-pane">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap">
									<div class="col-md-8" id=div2></div>
									<div id="studenttable">
										<table style="background: #fff;width: 90%;" class="table" id="allstudent">
											<tr>
											    <th style="font-size: 15px; text-align: center;">Transport Type</th>
												<th style="font-size: 15px; text-align: center;">Route</th>
												<th style="font-size: 15px; text-align: center;">Stage</th>
												<th style="font-size: 15px; text-align: center;">Amount</th>
												<th style="font-size: 15px; text-align: center;">Month</th>
											</tr>
											<tr id='rowId'>
											    <td><select class="vehicletype" onchange="HideError(this)"  id ="vehicletype" name="vehicletype" style="width:100%;">
													<option value="">---Select---</option>
							    				</select>
							    				</td>
												<td><select class="routename" onchange="HideError(this)"  id ="routename" name="route" style="width:100%;">
													<option value="">----------Select----------</option>
							    				</select>
							    				</td>
												<td><select class="stopname" id="stopname" name="stop" onchange="HideError(this)" style="width:100%;">
													<option value="">----------Select----------</option>
							    				</select>
							    				</td>
												<td><input type="text" class="amount"  style="width:100%;" name="amount" readonly="readonly"></td>
												<td><input type="text" class="month"  style="width:100%;" id="month" onclick="HideError(this)" readonly="readonly"></td>
										</tr>
									</table> 
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
</body>
</html>
