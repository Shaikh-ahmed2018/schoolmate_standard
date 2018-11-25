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


<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src='<logic:present name="DashboardJs" scope="request"><bean:write name="DashboardJs" /></logic:present>' ></script>
<link href="CSS/newUI/dashboard.css" rel="stylesheet" /> 
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
  
  <link rel="stylesheet" type="text/css" href="CSS/style.css">
  
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
.navbar-right span:last-child {
    margin-right: 10px;
}
.navbar-right span {
	top: -3px;
}

</style>
<!-- <script src= "https://cdn.zingchart.com/zingchart.min.js"></script>
		<script> zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
		ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9","ee6b7db5b51705a13dc2339db3edaf6d"];</script> -->
		
		
		
</head>
<body>
<script src='${requestScope.dashboard}'></script>
	<div class="content" id="div-main">
		<div class="row">
		 <div class="dashboard" >
				<div class="col-md-12">
				<div class="dashboard-content" style="height: 500px;">
				   <h4 style="font-size: 20px;font-family: Roboto Medium;">Academic Admission Details</h4>
					<div class = "wrapper" style="margin-left: 5px;">
					<div><input type="text" readonly="readonly" style="position: absolute;right: 350px;width: 380px;margin-top: -13px;border: 0px;"/></div>
						<canvas id="student"></canvas>
					</div>
					
					<div class="navbar-right" style="right: 70px;top: 225px;">
					
						<div class="form-group clearfix"><span class="buttons" style="background-color: #3366cc; padding: 1px 30px;"></span>Applied</div>
						<div class="form-group clearfix"><span class="buttons" style="background-color: #109618; padding: 1px 30px;"></span>Approved</div>
						<div class="form-group clearfix"><span class="buttons" style="background-color: #ff9900; padding: 1px 30px;"></span>Cancelled</div>
						<div class="form-group clearfix"><span class="buttons" style="background-color: #990066; padding: 1px 30px;"></span>Pending</div>
						<div class="form-group clearfix"><span class="buttons" style="background-color: #1EABB4; padding: 1px 30px;"></span>Processed</div>
						<div class="form-group clearfix"><span class="buttons" style="background-color: #dc3912; padding: 1px 30px;"></span>Rejected</div>
					</div>
				
				</div>
				</div>
			</div>
			</div>

		 
	</div>
</html>