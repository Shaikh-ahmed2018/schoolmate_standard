<!DOCTYPE html>
<html lang="en">
<head>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>

<script src='<logic:present name="DashboardJs" scope="request"><bean:write name="DashboardJs" /></logic:present>' ></script>
<link href="CSS/newUI/dashboard.css" rel="stylesheet" /> 
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
 
<script src= "https://cdn.zingchart.com/zingchart.min.js"></script>
<script src="JS/smsDashboard.js"></script>


		<script> zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
		ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9","ee6b7db5b51705a13dc2339db3edaf6d"];</script>
		

<!-- or from surge.sh -->
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
.canvasjs-chart-toolbar {
    display: none;
}
</style>
<script>
$(document).ready(function(){
	startTime();
});
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

</script>
		
		
			<!-- <script >
			
			google.charts.load('current', {'packages':['corechart']});
			google.charts.setOnLoadCallback(drawChart);
			function drawChart() {
				var data = google.visualization.arrayToDataTable([
				   ['Element', '', { role: 'style' } ],
				     ['Total SMS', 1, '#ccccc'], 
				      ['Sent SMS', 1,'#ccccc'],
				    ['Remaining SMS',1, '#ccccc'], ]);
				 var view = new google.visualization.DataView(data);
			     view.setColumns([0, 1, { calc: 'stringify',  sourceColumn: 1,type:'string',  role: 'annotation' },   2]);
				var options = {title: 'SMS Details ', bar: {groupWidth: '50%'}, legend: { position: 'none' },width:500,heigt:600};

				
				var chart = new google.visualization.ColumnChart(document.getElementById('SMSdetails'));
				chart.draw(view, options);}  
			</script>
		 -->
		
</head>
<body onload="startTime()">
	<div class="content" id="div-main">
		<div class="dashboard">
			<div class="row">
				<logic:present name="Dashboard" scope="request">
					<logic:iterate id="dashboard" name="Dashboard" scope="request">
						<div class="col-md-6 col-sm-6">
							<div class="dashboard-content">
							<div>
							   	${dashboard}
							</div>
							</div>
						</div>
					</logic:iterate>
				</logic:present>
			</div>
		</div>
		 
	</div>
			
			
			
	</body>
</html>