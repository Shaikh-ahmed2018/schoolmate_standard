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

<link href="CSS/Exam/MarkEntryStudentList.css" rel="stylesheet"
	type="text/css">
<link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Exam/Examtimatableclasssection.js"></script>
<style>
/* #allstudent tr td {
	color: black;
	text-align: center;
} */

#allstudent {
	width: 100%;
}
#allstudent td:nth-child(2){
	text-align: left;
}
#allstudent td:nth-child(1){
	text-align: center;
}
#allstudent tr:nth-child(3),tr:nth-child(1){
	text-align: center;
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
</style>
</head>
<body>
		<form method="POST" action="examTimetablePath.html?method=getExamByClassWise" id="myform">
		<input type="hidden" name="myValue" value="" id="myValue"/>
		</form>
		
		<form method="POST" action="examTimetablePath.html?method=dispalyexamsavepagedata" id="myform1">
		<input type="hidden" name="myValue1" value="" id="myValue1"/>
		</form>
		
		<form method="POST" action="examTimetablePath.html?method=dispalyexamsavepage" id="myform2">
		<input type="hidden" name="myValue2" value="" id="myValue2"/>
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
	
		<input type="hidden" id="hiddenaccyear" value='<logic:present name="accyearid"  scope="request"><bean:write name="accyearid" /></logic:present>' />
		<input type="hidden" id="hiddenloc" value='<logic:present name="locid" scope="request"><bean:write name="locid"  /></logic:present>' />
        <input type="hidden" id="hiddenclass" value='<logic:present name="classid" scope="request"><bean:write name="classid"  /></logic:present>' />
        <input type="hidden" id="hiddenexamid" value='<logic:present name="examid" scope="request"><bean:write name="examid"  /></logic:present>' />
		
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a href="#" style="color: #000000"><h3 class="panel-title class"><i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Exam Details</h3></a>
					
					 <div class="navbar-right">
	 		<span class="buttons" id="back1" style="top: 7.5px;">Back</span>
		</div>
				</div>
				
				<div id="classOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
		<div class="clearfix">
		
	<input type="hidden" id="historyaccyear"  
	 value='<logic:present name="historyaccyear" scope="request" ><bean:write name="historyaccyear"></bean:write></logic:present>' />				
	
	 <input type="hidden" id="historylocation"  
	 value='<logic:present name="historylocation" scope="request" ><bean:write name="historylocation"></bean:write></logic:present>' />	
	
	<input type="hidden" id="historysection"  
	 value='<logic:present name="historysection" scope="request" ><bean:write name="historysection"></bean:write></logic:present>' />
	 
	 <input type="hidden" id="historyexam"  
	 value='<logic:present name="historyexam" scope="request" ><bean:write name="historyexam"></bean:write></logic:present>' />
	 
		<div class="col-md-12" id="inputcolor">
			<div class="col-md-6" id="txtstyle"
				style="font-size: 13px; color: #000;">
				
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Academic Year</label>
					<div class="col-xs-5">
						<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear" class="form-control"
							style="text-align: left;" value='<logic:present name="accyName" scope="request" ><bean:write name="accyName" scope="request"></bean:write></logic:present>' />
					</div>
				</div>
					<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Class</label>
					<div class="col-xs-5">
						<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="class" class="form-control"
							style="text-align: left;" value='<logic:present name="classname" scope="request" ><bean:write name="classname" scope="request"></bean:write></logic:present>' />
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
							onkeypress="HideError()" id="accyear" class="form-control"
							style="text-align: left;"
							value='<logic:present name="location" scope="request" ><bean:write name="location" scope="request"></bean:write></logic:present>' />

					</div>
				</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Division</label>
					<div class="col-xs-5">
						<select class="form-control" onkeypress="HideError()" name="section" id="section">
							<option value="all">All</option>
						</select>
					</div>
				</div>
			</div>
			
		</div>
	</div>
						<div id="mytable"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
