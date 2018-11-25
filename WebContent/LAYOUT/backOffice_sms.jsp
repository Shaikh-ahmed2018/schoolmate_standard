<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>

	<link href="CSS/newUI/custome.css" rel="stylesheet">
<title>eCampus Pro</title>
<body>
		<div class="col-md-2 leftmenu">
			
			
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						  <div class="panel panel-primary">
						  
							<div class="panel-heading leftNav" role="tab" id="headingOne">
							
							  <h3 class="panel-title" id="beforeparent">
								<a data-toggle="collapse" data-parent="#accordion" href="#menucollapseOne" style="color:#000000" >

									<h3 class="panel-title active"><i class="fa fa-comments-o sideMenuIcon" style="margin-top: 0px !important;"></i>SMS Alerts&nbsp;&nbsp;<i class="glyphicon glyphicon-triangle-bottom" style="float:right;"></i></h3>



								</a>
								
							</h3>
							</div>
							<div id="menucollapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							  <div class="panel-body">
								<ul class="nav nav-pills nav-stacked">
								
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="HMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="menuslist.html?method=homeworklist" id="sub_module_20_1">Home Works</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="SHDDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
							
											<li><a href="menuslist.html?method=suddenholiodayslist" id="sub_module_20_2">Sudden Holidays</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
										
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="MDDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="menuslist.html?method=meetingslist" id="sub_module_20_3">Meetings/Events</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
						<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="STRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
									  
									  	<li><a href="menuslist.html?method=eventlist">Events</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
										
						 <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LCSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=latecomingstudentslist" id="sub_module_20_4">Late Coming Students</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>		
										
						<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="USDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=uniformlist" id="sub_module_20_5">Uniform</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>		
									
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="ABDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=absentlist" id="sub_module_20_6">Absent</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="ABDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="smsPath.html?method=feeSmsList" id="sub_module_20_8">Fee</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
								
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="OTHSMSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=othersmslist" id="sub_module_20_7">Other SMS</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
								
					<%-- 	<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="SUBDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=birthdaywisheslist">Birthday Wishes</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>	
							
							
									
					
					<%-- 	<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="TMTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=gettimetable">Time Table </a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
							
						<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="TMTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=getclassandteacherList">ClassTeacher Mapping</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>		 --%>
								 			
								
								</ul>
								
							  </div>
							</div>
						  </div>
						  
						  
						   <%-- <div class="panel panel-default" style="margin: -1px 0px;">
							<div class="panel-heading" role="tab" id="headingTwo" style="background: #07AAB9;">
							  <h3 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#menucollapseTwo"  style="color:#000000" aria-expanded="false" aria-controls="collapseTwo">
								  <h3 class="panel-title active" style="    color: #fff;">General Settings &nbsp;&nbsp;<i class="glyphicon glyphicon-triangle-bottom" style="float:right;margin: 1px -8px 0px 0px;"></i></h3>
								</a>
								
							  </h3>
							</div>
							<div id="menucollapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
							  <div class="panel-body">
								<ul class="nav nav-pills nav-stacked">
								
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CMSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
								
										<li><a href="menuslist.html?method=communicationRemarksList">Communication Settings</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							
							
							
	<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CMSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
								
										<li><a href="menuslist.html?method=remainderdetails">Remainder Details</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>						
							
							
							
							
							
							
							
									
									
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="IJPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
										<li><a href="menuslist.html?method=careerupdate">Internal Job Posting</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
									
									
									<!-- <li><a href="menuslist.html?method=remainderdetails">Remainder Details</a></li> -->
								
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="ACCYRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
								
								<li><a href="menuslist.html?method=academicyear">Academic Year Details</a></li>
									
									</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="AYPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">		
							
									<li><a href="menuslist.html?method=acdamicYearPlanList">Academic Year Plan</a></li>
								
									</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
									<li>&nbsp;</li>
									<li>&nbsp;</li>
									<li>&nbsp;</li>
									<li>&nbsp;</li>
				
								</ul>
								
							  </div>
							</div>
						  </div> --%>
						  
						  
						   <%-- <div class="panel panel-default" style="margin: -1px 0px;">
							<div class="panel-heading" role="tab" id="headingThree" style="background: #07AAB9;">
							  <h3 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#menucollapseThree"  style="color:#000000" aria-expanded="false" aria-controls="collapseTwo">
								  <h3 class="panel-title active" style="    color: #fff;">Security  Settings &nbsp;&nbsp;<i class="glyphicon glyphicon-triangle-bottom" style="float:right;margin: 1px -8px 0px 0px;"></i></h3>
								</a>
								
							  </h3>
							</div>
							<div id="menucollapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
							  <div class="panel-body">
								<ul class="nav nav-pills nav-stacked">
								
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="STRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="menuslist.html?method=roleList">Role Master</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
							
								<logic:present name="UserDetails" scope="session">
									 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
										<logic:equal value="RPMDIS" name="daymap" property="key">
											<logic:equal value="true" name="daymap" property="value">	
									
												<li><a href="menuslist.html?method=getUserRolePermission">Assign Permissions</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
							
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="PWMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
										
											<li><a href="menuslist.html?method=getUserRecords">Password Maintenance</a></li>
									
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>	
									<li>&nbsp;</li>
									<li>&nbsp;</li>
				
								</ul>
								
							  </div>
							</div>
						  </div> --%>
						  
						  </div>
			
		
		</div>
</body>
</html>
