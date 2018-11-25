<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">
 
<head>
<title>eCampus Pro</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script src="JS/backOffice/Fee/StudentWiseFeeConcessionRequest.js"></script>
<style type="text/css">
.myPosition {
    position: absolute;
    right: 200px; 
    top: 113px ! important;
    left: 244px ! important;
}
</style>
</head>

<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div id="dialog" class="selector" style="display:none;">
		
		<div class="form-group clearfix">
			<label for="inputEmail" class="control-label col-xs-5"
					style="text-align: left; line-height: 35px;">Concession Name</label>
				<div class="col-xs-6">
					<select id="termId" class="termName">
						<option value="">----------Select----------</option>
						<%-- <logic:present name="feeConcession">
							<logic:iterate id="concession" name="feeConcession">
								<option value="<bean:write name="concession" property="concessionType"/>,<bean:write name="concession" property="concessionId"/>,<bean:write name="concession" property="concessionBy"/>"><bean:write name="concession" property="concessionName" /></option>
							</logic:iterate>
						</logic:present> --%>
					</select>
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
	
	  <input type="hidden" id="historysearchvalue"
	   value='<logic:present name="historysearchvalue" scope="request"><bean:write name="historysearchvalue"></bean:write></logic:present>' />
	 
	  <input type="hidden" id="historyisConcession"
	   value='<logic:present name="historyisConcession" scope="request"><bean:write name="historyisConcession"></bean:write></logic:present>' />
	              		
			<div class='form-group clearfix' id="partialConcession" style="display: none">
					<label for='inputEmail' class='control-label col-xs-5'
						style='text-align: left; line-height: 35px;'>Concession Amount</label>
					<div class="col-xs-6">
						<input type="text" name='scholorshipAmount' id='scholorshipAmount' />
					</div>
			</div>
 
			<div class="form-group clearfix" id="termConcession" style="display: none">
				<div class="col-xs-12">
					<label for='inputEmail' class='control-label col-xs-4' style='text-align: left; line-height: 35px;'>TermWise Concession :</label>
				</div>
				<div class="col-xs-12">
					<div class="col-xs-6">
						<table class="table" id="allstudent" style="width:100%">
							<thead>
								<tr>
									<th style="width : 5%;" class="sno">S.No</th>
									<th style="text-align: center;width : 30%;">Term Name</th>
									<th style="text-align: center;width : 20%;">Fee Amount</th>
									<th style="text-align: center;width : 15%;">Status</th>
									<th style="text-align: center;width : 10%;">Select</th>
								</tr>
							</thead>
							<tbody>
								<%-- <logic:present name="termfees" scope="request">
									<logic:iterate id="getActiveFee" name="termfees" scope="request">
										<tr>
											<td class="selectTerm" id="<bean:write name="getActiveFee" property="termId"/>,<bean:write name="getActiveFee" property="term" />"><bean:write name="getActiveFee" property="sno" /></td>										
											<td style="text-align: center;"><bean:write name="getActiveFee" property="term" /></td>										
											<td style="text-align: center;"><bean:write name="getActiveFee" property="termAmount" /></td>										
											<td style="text-align: center;" class="paidstatus"><bean:write name="getActiveFee" property="status" /></td>										
											<td style="text-align: center;"><input type='checkbox' name='selecTerms' class='selectTerms' style='text-align: center;' id='<bean:write name="getActiveFee" property="termId"/>,<bean:write name="getActiveFee" property="term" />,<bean:write name="getActiveFee" property="sno" />'
																value='<bean:write name="getActiveFee" property="termId"/>'/></td>										
										</tr>
									</logic:iterate>
								</logic:present> --%>
							</tbody>
						</table>
					</div>
					<div class="col-xs-6"><div class="tb"></div></div>
				</div>
				
			</div>
	</div>
	<div class="col-md-10"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Fee Concession Request/Cancel</span>
		</p>
		
	<div id="dialog1"></div>
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

		<div class="panel panel-primary">
			<div class="panel-heading clearfix" role="tab" id="headingOne">
				<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title" style="color: #000000;vertical-align: text;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Concession 
							Details
						</h3></a>
				<div class="navbar-right">
						<span class="buttons" id="add">New</span>
						<span class="buttons" id="cancel">Cancel</span>  
						<span class="buttons" id="edit">Modify</span>
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
									value='<logic:present name="studentDetailsList" scope="request" ><bean:write name="studentDetailsList" property="studentFullName"/></logic:present>' />
						</div>
						<input type="hidden" name="hiddenstudentid" id="hiddenstudentid"
							value='<logic:present name="stuid" scope="request"><bean:write name="stuid"></bean:write></logic:present>' />

					</div>
				</div>
			</div>

			<div class="col-md-12" style="margin-bottom:-107px">
				
				<div class="col-md-6" id="txtstyle"
					style="font-size: 13px; color: #000;">
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Admission No</label>
						<div class="col-xs-6">

							<input type="text" readonly="readonly" name="admissionno"
								onkeypress="HideError()" id="subcode" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="admissionNo"></bean:write></logic:present>' />
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
							<textarea name="identificationMarks" rows="5" id="identificationMarksId" readonly="true" class="form-control"><logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="address"></bean:write></logic:present></textarea>
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
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="studentStatus"></bean:write></logic:present>' />
						</div>
					</div>

					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Division</label>
						<div class="col-xs-6">
							<input type="text" readonly="readonly" name="sectionid"
								onkeypress="HideError()" id="sectionid" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="sectionnaem"></bean:write></logic:present>'  />
						</div>
						<input type="hidden" name="hiddensectionid" id="hiddensectionid"
							value='<logic:present name="sectionId" scope="request"><bean:write name="sectionId"></bean:write></logic:present>' />
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Fee Concession
							Status</label>
						<div class="col-xs-6">
							<input type="text" readonly="readonly" name="examName"
								onkeypress="HideError()" id="examName" class="form-control"
								value='<logic:present name="studentDetailsList" scope="request"><bean:write name="studentDetailsList" property="status"></bean:write></logic:present>'  />
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="hiddenImage" value="<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="image"/></logic:present>"/>
		<input type="hidden" id = "hiddenstuid" value='<logic:present name="stuid"><bean:write name="stuid"/></logic:present>'/>
		<input type="hidden" id="istransport" value='<logic:present name="studentDetailsList"><bean:write name="studentDetailsList" property="status"/></logic:present>'>
		
		<hr style="height:1px;border:none;color:#333;background-color:#ddd;"/>
			<div>
		 <div class="slideTab clearfix">
						<div class="tab">
						<ul class="nav nav-tabs">
								<li ><a class="primarythemebackgound primarythemecolor" data-toggle="tab" href="#contacts"  style="font-weight: 700; id="contacts">Concession Details</a></li></ul>
						<div id="contacts" class="tab-pane">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap">
									<div id="studenttable" class="TermTable commontable">
										<table style="background: #fff;width: 100%;" class="trm_table" id="allstudent">
											<thead>
											<tr>
												<th style="font-size: 15px; text-align: center;width: 70px;"><input type='checkbox' class='selectFees' style='text-align: center'/></th>
											    <th style="font-size: 15px; text-align: center;">Concession Name</th>
											    <th style="font-size: 15px; text-align: center;">Term Name</th>
											    <th style="font-size: 15px; text-align: center;">Fee Name</th>
												<th style="font-size: 15px; text-align: center;">Fee Amount</th>
												<th style="font-size: 15px; text-align: center;">Concession Amount</th>
											</tr>
											</thead>
											<tbody>
											</tbody>
									</table> 
									</div>
									<div id="studenttable" class="partialTable commontable">
										<table style="background: #fff;width: 90%;" class="par_table" id="allstudent">
											<thead>
											<tr>
												<th style="font-size: 15px; text-align: center;width: 50px;"><input type='checkbox' class='selectFees' style='text-align: center'/></th>
												<th style="font-size: 15px; text-align: center;">Concession Name</th>
												<th style="font-size: 15px; text-align: center;">Concession Amount</th>
											</tr>
											</thead>
											<tbody>
											</tbody>
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
