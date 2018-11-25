<!DOCTYPE html>
<html lang="en">
<head>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
<script src='<logic:present name="DashboardJs" scope="request"><bean:write name="DashboardJs" /></logic:present>' ></script>
<link href="CSS/newUI/dashboard.css" rel="stylesheet" /> 
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<script src="JS/calendar.js"></script>
<link href="CSS/calendar.css" rel="stylesheet"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<link href="CSS/newUI/dashboard.css" rel="stylesheet" /> 
<style type="text/css">
.modulenamecl{
   text-align: center;
}
.exmtable th{
font-size: 12px;
}
.exmtable td{
font-size: 12px;
}
.grade tbody tr th{
font-size: 13px;
}
.grade tbody tr td{
font-size: 12px;
}

</style>
</head>
<body onload="displayCalendar()">
	<div class="content" id="div-main">
		<div class="dashboard">
			<div class="row">
				<logic:present name="Dashboard" scope="request">
					<logic:iterate id="dashboard" name="Dashboard" scope="request">
						<div class="col-md-6 col-sm-6">
							<div class="dashboard-content">
							   	${dashboard}
							</div>
						</div>
					</logic:iterate>
				</logic:present>
			</div>
		</div>
		 
	</div>
</html>