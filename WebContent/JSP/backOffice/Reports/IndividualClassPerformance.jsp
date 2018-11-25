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
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.chart-1.11.1.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.canvasjsChart.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.menu.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
 --><link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/backOffice/Reports/IndividualClassPerformance.js"></script>
<style type="text/css">
@page  
{ 
    size: auto;   
    margin: 0 10% 0 0;  
} 
</style>
</head>
<body>
<input type="hidden" id="accYear" value="<logic:present name="accYear" scope="request"><bean:write name="accYear"/></logic:present>"/>
<input type="hidden" id="location" value="<logic:present name="locationId" scope="request"><bean:write name="locationId"/></logic:present>"/>
<input type="hidden" id="classId" value="<logic:present name="classId" scope="request"><bean:write name="classId"/></logic:present>"/>
<input type="hidden" id="SectionId" value="<logic:present name="SectionId" scope="request"><bean:write name="SectionId"/></logic:present>"/>
<input type="hidden" id="ExamCode" value="<logic:present name="ExamCode" scope="request"><bean:write name="ExamCode"/></logic:present>"/>
<input type="hidden" id="studentId" value="<logic:present name="studentId" scope="request"><bean:write name="studentId"/></logic:present>"/>
<input type="hidden" id="accYear" value="<logic:present name="accYear" scope="request"><bean:write name="accYear"/></logic:present>"/>
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Class Performance</span></p>
				<div class="panel-body clearfix"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;padding-top: 0">
				</div>
				<div id="forPrint" style="display: none;text-align: center;color: #000;">
				<input type="hidden" name="accYearName" id="accYearName" value='<logic:present name="academicYearName"><bean:write name="academicYearName"/></logic:present>'></input>
				<input type="hidden" name="schoolName" id="schoolName" value='<logic:present name="SchoolName"><bean:write name="SchoolName"/></logic:present>'></input>
				<input type="hidden" name="className" id="className" value='<logic:present name="ClassName"><bean:write name="ClassName"/></logic:present>'></input>
				<input type="hidden" name="sectionName" id="sectionName" value='<logic:present name="SectionName"><bean:write name="SectionName"/></logic:present>'></input>
				
			<div class="col-md-12">
			<table style="width: 100%;border-collapse: unset;">
			<tr><td colspan="2"><label style="font-size: 162%;"><logic:present name="SchoolName"><bean:write name="SchoolName"/></logic:present></label></td></tr>
			<tr style="height: 33px;"><td></td></tr>
			<tr style="height: 33px;"><td></td></tr>
			<tr><td><label style="font-size: 110%;">Academic Year: <logic:present name="academicYearName"><bean:write name="academicYearName"/></logic:present></label></td><td><label style="font-size: 110%;">Class-Divison: <logic:present name="ClassName"><bean:write name="ClassName"/></logic:present> - <logic:present name="SectionName"><bean:write name="SectionName"/></logic:present></label></td></tr>
					</table></div>
					</div>
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
							<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Class Performance</h3></a>
							
							<div class="navbar-right">
                          <span class="buttons" id="print" style="vertical-align: top;">Print</span>
                          <span class="buttons" id="back" style="vertical-align: top;">Back</span>
				</div>
					</div>
					
					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
					
					<div id="chartContainer" style="height: 450px; width: 90%;margin-left: 69px;"></div>
						</div>
				</div>
			</div>
	</div>
</html>
