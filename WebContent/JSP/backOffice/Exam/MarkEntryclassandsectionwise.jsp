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

<link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Exam/MarkEntryClassandStudentwise.js"></script>
<style>

#allstudent {
	width : 100%;
}
.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
    background-color: #eee;
    opacity: 1;
    width: 191px;
}
.scrollbar{

	overflow-y:scroll; 
	height: 400px;
}


</style>
</head>
<body>
	<form method="POST" action="examTimetablePath.html?method=setMarkEntryStudentwise" id="myform">
		<input type="hidden" name="myValue" value="" id="myValue"/>
	</form>	
	
	<form method="POST" action="examTimetablePath.html?method=setMarkEntryDetails" id="backform">
		<input type="hidden" name="myValue" value="" id="backValue"/>
	</form>	
		
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Mark Entry Student Wise</span>
		</p>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"><bean:write name="successmessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

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
		
			
  <input type="hidden" id="historyacayearId"
  value='<logic:present name="historyacayearId"  scope="request"><bean:write name="historyacayearId" /></logic:present>' />
  
  <input type="hidden" id="historylocId"
  value='<logic:present name="historylocId"  scope="request"><bean:write name="historylocId" /></logic:present>' />	
		
		
		 <input type="hidden" id="hiddenlocid" value='<logic:present name="locationid"  scope="request"><bean:write name="locationid" /></logic:present>' />
       <input type="hidden" id="hiddenclassid" value='<logic:present name="subjectClassList"  scope="request"><bean:write name="subjectClassList" /></logic:present>' />
	   <input type="hidden" id="hiddenstartaccyear" value='<logic:present name="startDate"  scope="request"><bean:write name="startDate" /></logic:present>' />
		<input type="hidden" id="hiddenendaccyear" value='<logic:present name="endDate" scope="request"><bean:write name="endDate"  /></logic:present>' />		

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					<a href="#" style="color: #767676"><h4 class="panel-title examdetails">
						<i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Mark Entry-Student Wise</h4>
					</a>
					<div class="navbar-right">
		 				<span class="buttons" id="back1" >Back</span>
					</div>
				</div>
				<div class='clearfix' id="inputcolor">
	   				<div class="col-md-6"  id="txtstyle" style="font-size: 11pt; color: #5d5d5d;padding-top: 10px;">
	                   <div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: left; line-height: 35px;">Branch</label>
							<div class="col-xs-6">
								<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear"
									class="form-control" style="text-align:left;width: 100%" value='<logic:present name="currentlocation" scope="request"><bean:write name="currentlocation" ></bean:write></logic:present>' />
							</div>
							<input type="hidden" name="hiddenaccyid" id="hiddenaccyid" value='<logic:present name="accyId" scope="request"><bean:write name="accyId"></bean:write></logic:present>'/>
						</div>
						<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Exam Code</label>
									<div class="col-xs-6">
									 <input type="text" readonly="readonly" name="examCode" onkeypress="HideError()" id="examCode" class="form-control" style="text-align:left;width: 100%" value='<logic:present name="examCode" scope="request"><bean:write name="examCode" ></bean:write></logic:present>'/>
									</div>
									<input type="hidden" name="hiddenexamid" id="hiddenexamid" value='<logic:present name="examid" scope="request"><bean:write name="examid"></bean:write></logic:present>'/>
							</div>
					</div>
					<div class="col-md-6"  id="txtstyle" style="font-size: 11pt; color: #5d5d5d;padding-top: 10px;">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: left; line-height: 35px;">Academic Year</label>
							<div class="col-xs-6">
								<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear"
									class="form-control" style="text-align:left; width: 100%" value='<logic:present name="accyName" scope="request"><bean:write name="accyName" ></bean:write></logic:present>' />
							</div>
							
						</div>
						<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Exam Name</label>
									<div class="col-xs-6">
									 <input type="text" readonly="readonly" name="examName" onkeypress="HideError()" id="examName" class="form-control" style="text-align:left;width: 100%" value='<logic:present name="examName" scope="request"><bean:write name="examName" ></bean:write></logic:present>'/>
									</div>
									
							</div>
					</div>
				</div>								
				<input type="hidden" id="hiddenaccyear"
					value='<logic:present name="accyear"  scope="request"><bean:write name="accyear" /></logic:present>' />
					           <div class="form-group scrollbar">
                             
								<table class="allstudent" id="allstudent">
									<logic:present name="subjectClassList" scope="request">
										<thead>
										<tr  style="background: #f5f5f5;"">
											<th style="font-size: 13px; text-align: center;width:95px;padding:9px;" class="sticky">Sl. No</th>
											<th style="font-size: 13px; text-align: left;padding:9px;" class="sticky">Class</th>
											<th style="font-size: 13px; text-align: center;padding:9px;" class="sticky">Division</th>
											<th style="font-size: 13px; text-align: center;padding:9px;" class="sticky">No Of Students</th>
											<th style="font-size: 13px; text-align: center;padding:9px;" class="sticky">Status</th>
										</tr>
										</thead>
										<tbody>
											<logic:iterate id="subjectClassList" name="subjectClassList" scope="request">
												<tr>
													<td class="examid"><bean:write name="subjectClassList" property="sno1" /></td>
													<td style="text-align: left;" class="classId" id="<bean:write name="subjectClassList" property="classId"/>"><bean:write name="subjectClassList" property="classname"/></td>
			   		                                <td class="sectionId" id="<bean:write name="subjectClassList" property="section" />"><bean:write name="subjectClassList" property="sectionName"/></td>
													<td class="tot_strenght" id="<bean:write name="subjectClassList" property="specialization" />"><bean:write name="subjectClassList" property="tot_strength" /></td>
													<td><span class="<bean:write name="subjectClassList" property="status"/>"><bean:write name="subjectClassList" property="status"/></span></td>
												</tr>
											</logic:iterate>
										</tbody>
									</logic:present>
									
									<logic:present name="subjectSpecClassList" scope="request">
										<thead>
											<tr style="background: #f5f5f5;">
												<th style="font-size: 13px; text-align: center;width:95px;padding:9px;" class="sticky">Sl. No</th>
												<th style="font-size: 13px; text-align: left;padding:9px;" class="sticky">Class</th>
												<th style="font-size: 13px; text-align: center;padding:9px;" class="sticky">Specialization-Division</th>
												<th style="font-size: 13px; text-align: center;padding:9px;" class="sticky">No Of Students</th>
												<th style="font-size: 13px; text-align: center;padding:9px;" class="sticky">Status</th>
											</tr>
										</thead>
										<tbody>
											<logic:iterate id="subjectSpecClassList" name="subjectSpecClassList" scope="request">
												<tr>
													<td class="examid"><bean:write name="subjectSpecClassList" property="sno1" /></td>
													<td style="text-align: left;" class="classId" id="<bean:write name="subjectSpecClassList" property="classId"/>"><bean:write name="subjectSpecClassList" property="classname"/></td>
			   		                                <td class="sectionId" id="<bean:write name="subjectSpecClassList" property="section" />"><bean:write name="subjectSpecClassList" property="sectionName"/></td>
													<td class="tot_strenght" id="<bean:write name="subjectSpecClassList" property="specialization" />"><bean:write name="subjectSpecClassList" property="tot_strength" /></td>
													<td><span class="<bean:write name="subjectSpecClassList" property="status"/>"><bean:write name="subjectSpecClassList" property="status"/></span></td>
													<%-- <input type="hidden" id="hiddenspec" value="<bean:write name="subjectSpecClassList" property="specialization"/>"/> --%>
												</tr>
											</logic:iterate>
										</tbody>
									</logic:present>
								</table>
								</div>
								</div>
							</div>
						</div>
</body>

</html>
