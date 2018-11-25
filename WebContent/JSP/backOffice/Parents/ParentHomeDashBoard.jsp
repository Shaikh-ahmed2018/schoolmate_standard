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

<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script> -->
<link href="CSS/newUI/dashboard.css" rel="stylesheet" /> 
<script src="JS/backOffice/Parents/ParentDashBoard.js" type="text/javascript"></script>   
  
<style>
.stuimg{
width: 45px;
height: 40px;
}
.buttons{
	border: none;
}
.timetable{
	width: 800px !important;
}
</style>
</head>
<!-- <body>
<script src= "https://cdn.zingchart.com/zingchart.min.js"></script>
		<script> zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
		ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9","ee6b7db5b51705a13dc2339db3edaf6d"];</script>
</body>
<body> -->
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
	
	 <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog timetable">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Time Table</h4>
        </div>
        <div class="modal-body">
         <div id='mytable' class='table-responsive'></div>
        </div>
        <div class="modal-footer">
          <button type="button" class="buttons" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
	
	
</html>