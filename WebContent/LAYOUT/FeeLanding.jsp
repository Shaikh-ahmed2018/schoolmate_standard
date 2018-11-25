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
<script src="JS/calendar.js"></script>
<link href="CSS/calendar.css" rel="stylesheet"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>

<script src= "https://cdn.zingchart.com/zingchart.min.js"></script>
		<script> zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
		ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9","ee6b7db5b51705a13dc2339db3edaf6d"];</script>
<script src='<logic:present name="DashboardJs" scope="request"><bean:write name="DashboardJs" /></logic:present>' ></script>		
		
<link href="CSS/newUI/dashboard.css" rel="stylesheet" /> 
<style type="text/css">
.modulenamecl{
   text-align: center;
}
.dashboard-content {
    height: 500px;
}

</style>
<script>

function startTime() 
{
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
	    h = checkHour(h);
	    h = checkTime(h);
	    m = checkTime(m);
	    s = checkTime(s);
	    if(h=="00"){
	    	h = "12";
	    }
       document.getElementById('txt').innerHTML =
          h + ":" + m + ":" + s;
    var t = setTimeout(startTime, 500);
}

function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}

function checkHour(h){
	h = h % 12 ; 
	return h ;
}
$(document).ready(function(){
	startTime();
});
</script>

</head>
<body onload="displayCalendar()">
	<div class="content" id="div-main">
		<div class="dashboard">
			<div class="row">
				<logic:present name="Dashboard" scope="request">
					<logic:iterate id="dashboard" name="Dashboard" scope="request">
						<div class="col-md-12 col-sm-12">
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