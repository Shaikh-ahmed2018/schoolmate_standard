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


<link href="CSS/Exam/MarkEntryStudentList.css" rel="stylesheet" type="text/css">
<link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Exam/ExamtimatableSave.js"></script>	
<style>
.glyphicon {
top:0;
}
#allstudent {
	width: 100%;
}
#allstudent td:nth-child(2){
	text-align: left;
}
#allstudent td:nth-child(1){
	text-align: center;
}
#allstudent th:nth-child(3){
width:250px;
}

#allstudent tr:nth-child(1){
	text-align: center !important;
}
#allstudent th:nth-child(3),td:nth-child(3){
	text-align: left !important;
}

#allstudent th:nth-child(4),th:nth-child(5),th:nth-child(6){
	text-align: center !important;
	width:200px;
}
#accyear {
	text-align: center;
}
.set{
	background-color: rgba(0, 158, 0, 0.66);
    min-width: 80px;
    display: inline-block;
    text-align: center;
    color: #fff;
}
.not{
    background-color: #FF0000;
    min-width: 80px;
    display: inline-block;
    text-align: center;
    color: #fff;
}
#mytable input[type='text']{
background-color: transparent;
}
#mytable span{
background-color: transparent;
}
.ui-corner-all, .ui-corner-bottom, .ui-corner-right, .ui-corner-br{
z-index: 999999999999 !important;
}
.datetimepicker-hours table thead,.datetimepicker-minutes table thead{
display: none;
}
</style>
</head>
<body>
	<form method="POST" action="examTimetablePath.html?method=getexamtimetableclasssection" id="myform1">
		<input type="hidden" name="myValue1" value="" id="myValue1"/>
		</form>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Exam Time Table </span>
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
	

		<input type="hidden" id="hiddenaccyear" value='<logic:present name="accyear"  scope="request"><bean:write name="accyear" /></logic:present>' />
		<input type="hidden" id="hiddenloc" value='<logic:present name="locid" scope="request"><bean:write name="locid"  /></logic:present>' />
        <input type="hidden" id="hiddenclass" value='<logic:present name="classid" scope="request"><bean:write name="classid"  /></logic:present>' />
        <input type="hidden" id="hiddensection" value='<logic:present name="section" scope="request"><bean:write name="section"  /></logic:present>' />
		<input type="hidden" id="hiddenexamid" value='<logic:present name="examid" scope="request"><bean:write name="examid"  /></logic:present>' />
		<input type="hidden" id="minDate" value="<logic:present name="minDate" scope="request"><bean:write name="minDate"  /></logic:present>" />
		<input type="hidden" id="maxDate" value="<logic:present name="maxDate" scope="request"><bean:write name="maxDate"  /></logic:present>" />
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a href="#" style="color: #000000"> 
						<h3 class="panel-title class"><i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Subject Details</h3></a>
					
					 <div class="navbar-right">
			<span class="buttons" id="save" style="top: 7px;">Save</span>
	 		<span class="buttons" id="back1" style="top: 7px;">Back</span>
			
		</div>
				</div>
	
	 <input type="hidden" id="historyaccyear"  
	 value='<logic:present name="historyaccyear" scope="request" ><bean:write name="historyaccyear"></bean:write></logic:present>' />				
	
	 <input type="hidden" id="historylocation"  
	 value='<logic:present name="historylocation" scope="request" ><bean:write name="historylocation"></bean:write></logic:present>' />
	
	<input type="hidden" id="historysection"  
	 value='<logic:present name="historysection" scope="request" ><bean:write name="historysection"></bean:write></logic:present>' />
	
	<input type="hidden" id="historyexam"  
	 value='<logic:present name="historyexam" scope="request" ><bean:write name="historyexam"></bean:write></logic:present>' />
				
				<div id="classOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
					<div class="clearfix">
	
		<div class="col-md-12" id="inputcolor">
			<div class="col-md-6" id="txtstyle"
				style="font-size: 13px; color: #000;">
				
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Academic Year</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear"  id="accyear" class="form-control" style="text-align: left;width:100%;" value='<logic:present name="accyName" scope="request" ><bean:write name="accyName" scope="request"></bean:write></logic:present>' />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Class</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="class"  id="class" class="form-control" style="text-align: left;"
							value='<logic:present name="examdetails" scope="request" ><bean:write name="examdetails" scope="request" property="classname"></bean:write></logic:present>' />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Exam Code</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="examcode" class="form-control" style="text-align: left;"
							value='<logic:present name="examdetails" scope="request" ><bean:write name="examdetails" scope="request" property="examCode"></bean:write></logic:present>' />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Start Date</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="startdate" class="form-control" style="text-align: left;"
							value='<logic:present name="examdetails" scope="request" ><bean:write name="examdetails" scope="request" property="examstartdate"></bean:write></logic:present>' />
						</div>
					</div>
			</div>
			
			<div class="col-md-6" id="txtstyle"
				style="font-size: 13px; color: #000;">
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Branch</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear"
							onkeypress="HideError()" id="location" class="form-control"
							style="text-align: left;"
							value='<logic:present name="location" scope="request" ><bean:write name="location" scope="request"></bean:write></logic:present>' />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Division</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear"
							onkeypress="HideError()" id="section" class="form-control"
							style="text-align: left;"
							value='<logic:present name="examdetails" scope="request" ><bean:write name="examdetails" scope="request" property="sectionName"></bean:write></logic:present>' />
						
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Exam Name</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="examname" class="form-control" style="text-align: left;"
							value='<logic:present name="examdetails" scope="request" ><bean:write name="examdetails" scope="request" property="examName"></bean:write></logic:present>' />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">End Date</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="enddate" class="form-control" style="text-align: left;"
							value='<logic:present name="examdetails" scope="request" ><bean:write name="examdetails" scope="request" property="examenddate"></bean:write></logic:present>' />
						</div>
					</div>
					<div class="form-group clearfix">
					<div class="col-xs-12" style="text-align:right;">
						 <input type="checkbox" id='allsection' name='allsection' value='Y'/><span style="font-weight: 700;margin-left: 5px;">Apply for All Division</span>
					</div>
						
					</div>
					
			</div>
		
		</div>
	</div>				
						<div id="mytable" class='inputcolor'></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
