<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  isELIgnored="false" %>
    <%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
  
  <link rel="stylesheet" type="text/css" href="CSS/style.css">
<!--   <link rel="stylesheet" type="text/css" href="CSS/newUI/dashboard.css"> -->
  

  
</head>
  
<body>
	<script src='${requestScope.dashboard}'></script>
	<div class="content" id="div-main">
		<div class="row">
		 <div class="dashboard" >
		  
			<div class="row dashboardRow">
				<div class="col-md-6">
				 <div>
				<div class="dashboard-content">
				    <h4 style="font-size:16px;text-align:center;font-family:Roboto Medium;padding:0px;margin:0px;font-weight:500;">Student Strength Progress (Academic Year wise)</h4>
					<div class = "wrapper" style="padding-top: 15px; margin-left: 5px;">
						<canvas id="myChart3" width="400"  ></canvas>
					</div>
				</div>
				</div>
				</div>
				
				
				<div class="col-md-6">
				 <div>
				 <div class="dashboard-content">
				  <h4 style="font-size:16px;text-align:center;font-family:Roboto Medium;padding:0px;margin:0px;font-weight:500;">Today's Attendance (Student)</h4>
					<div class = "wrapper" style="padding-top: 15px;margin-left: 15px;">
						<canvas id="myChart2" width="400"  style="height: 265px !important;"></canvas>
					</div>
				</div>
				</div>
				</div>
			</div>
			<div class="row dashboardRow" >				 
				<div class="col-md-12">
				<div>
				<div class="dashboard-content">
				 <h4 style="font-size:16px;text-align:center;font-family:Roboto Medium;padding:0px;margin:0px;font-weight:500;">Class Strength</h4>
					<div class = "wrapper" style="padding-top: 5px; margin-left: 5px;">
						<canvas id="myChart" width="400"  style="height: 265px !important;"></canvas>
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