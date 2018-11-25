<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/backOffice/Exam/SetExam.js"></script>
<link rel="stylesheet" href="CSS/Exam/ExamList.css"/>
<style type="text/css">
#markstable{
padding-bottom: 10px;
}
.Not{
background-color:#FF0000; 
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
.Set{
background-color:green;
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}

</style>
</head>
<body>
<form method="POST" action="examTimetablePath.html?method=setExamDetails" id="myform">
		<input type="hidden" name="myValue" value="" id="myValue"/>
	</form>	
<div id="dialog"></div>
	<div class="content" id="div1">
	<div class="col-md-12 input-group">
		<p>
			<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span	id="pageHeading">Exam Setup</span>
		</p>
	</div>
		<logic:present name="success" scope="request">

			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
								name="success" scope="request" />
					</span></a>
				</div>
			</div>
		</logic:present>
		<logic:present name="error" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="error" scope="request" />
					</span></a>
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
		
		<input type="hidden" id="hiddenstartaccyear" value='<logic:present name="startDate"  scope="request"><bean:write name="startDate" /></logic:present>' />
		<input type="hidden" id="hiddenendaccyear" value='<logic:present name="endDate" scope="request"><bean:write name="endDate"  /></logic:present>' />		

		
		<div class="panel panel-primary">
			<div class="panel-heading clearfix" style="margin-bottom: -1px;">
			<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" style="color: #fff;">
				<h3 class="panel-title exam" style="color: #000000; line-height: 18px;">
					<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Exam List
				</h3></a>
			</div>

  <input type="hidden" id="historyacademicId" 
    value='<logic:present name="historyacademicId"  scope="request"><bean:write name="historyacademicId" /></logic:present>'/>

	<input type="hidden" id="historylocId" 
    value='<logic:present name="historylocId"  scope="request"><bean:write name="historylocId" /></logic:present>'/>

			<div id="ExamOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
			
				<div class="clearfix">
			
					<div class="col-md-6" id="txtstyle" style="font-size: 13px; color: #000;margin-top:10px;">
						<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1"name="location" id="location" >
												<logic:present name="locationDetailsList" scope="request">
												<logic:iterate id="locationDetailsList" name="locationDetailsList">
												<option value='<bean:write name="locationDetailsList" property="location_id"/>'><bean:write name="locationDetailsList" property="locationname"/></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
										<input type="hidden" name="hiddenlocationname" id="hiddenlocationname" value='<bean:write name="currentlocation"/>'/>
										<input type="hidden" name="hiddenlocationid" id="hiddenlocationid" value='<bean:write name="locationId"/>'/>
										<input type = "hidden" name="hiddenaccyear" id="hiddenaccyear" value='<bean:write name="academic_year"/>'/>
					</div>
						
					
					
				</div>
				<div class="col-md-6" id="txtstyle" style="font-size: 13px; color: #000;margin-top:10px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1"name="acayear" id="acayear" >
												<logic:present name="accYearList" scope="request">
												<logic:iterate id="accYearList" name="accYearList">
												<option value='<bean:write name="accYearList" property="accyearId"/>'><bean:write name="accYearList" property="accyearname"/></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
					</div>
			</div>
					<%-- <display:table name="requestScope.examListYear"
						requestURI="/examTimetablePath.html?method=getEaxmListYear" pagesize="10"
						export="false" class="table" id="allstudent" decorator="com.centris.campus.decorator.ExamAccYearDecorator">

						<display:column property="sno1" title="Sl.No." ></display:column> 

                       <display:column property="accyear" sortable="true" class="${allstudent.accyearid} accyear" title="Academic Year <img src='images/sort1.png' style='float:right;'/>" />						

						<display:column property="classname" sortable="true" title="Class Name <img src='images/sort1.png' style='float: right'/>" />
				
						<display:column property="status" sortable="true" title="Status <img src='images/sort1.png' style='float: right'/>" />
						
						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>

					</display:table> --%>
					<div id="markstable"></div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
