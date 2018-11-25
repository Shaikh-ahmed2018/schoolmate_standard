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

<title>eCampus Pro</title>
<body>
	<div class="col-md-2 leftmenu">
		<div class="panel-group" id="accordion4">
		<div class="panel panel-primary">
			<div class="panel-heading leftNav">
				<a data-toggle="collapse" data-parent="#accordion4"
					href="#menucollapseOne" ><h3 class="panel-title active">
						<i class="fa fa-users sideMenuIcon" style="margin-top: 0px !important;"></i>
						Staff <i class="glyphicon glyphicon-triangle-bottom"
							style="float: right;"></i>
					</h3></a>
			</div>
			<div id="menucollapseOne" class="accordion-body collapse in">
				<div class="panel-body">
					<ul class="nav nav-pills nav-stacked">

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STAFFDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=staffExcelFileUpload" id="sub_module_3_11">Upload Staff Excel Data File</a></li>
										<li><a href="menuslist.html?method=staffList" id="sub_module_3_1">Staff Registration</a></li>
										<li><a href="menuslist.html?method=staffEmployementList" id="sub_module_3_2">Staff Remuneration</a></li>
										<li><a href="menuslist.html?method=getStaffAttendance" id="sub_module_3_3">Staff Attendance</a></li>
										<li><a href="teachermenuaction.html?method=generatePayroll" id="sub_module_3_7"> Payroll </a></li>
										
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
					<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="SFDTUPDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value"> 
						
						 <li><a href="menuslist.html?method=staffExcelFileUpload" id="sub_module_3_11">Upload Staff Excel Data File</a></li>
						</logic:equal>
								</logic:equal>
							</logic:iterate>
					</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STFFATTDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=getStaffAttendance" id="sub_module_3_3">Staff Attendance</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STFFEMPDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=staffEmployementList" id="sub_module_3_2">Staff Remuneration</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STAFFPAYDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="teachermenuaction.html?method=generatePayroll" id="sub_module_3_7"> Payroll </a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 

     					<%-- <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STFFEMPDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=TDSComputationDetails" id="sub_module_3_6">IT Declaration</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%> 
						
						
						
						

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="UPASSDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="teachermenuaction.html?method=assignmentView" id="sub_module_3_4">Upload
												Assignment</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="UPASSDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="teachermenuaction.html?method=projectAssign" id="sub_module_3_12">Project</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
<%-- 						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="UPASSDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=stafffileupload">Upload
												Staff Data From File</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
 --%>

					<%-- 	<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STAFFPAYDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=getPayrollList" id="sub_module_3_5">Salary
												Details</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%>

					<!--	<li><a href="teachermenuaction.html?method=downloadPayslip" id="sub_module_3_10">Download
								PaySlip</a></li>
 -->
						
					</ul>
				</div>
				<br />
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading leftNav">
				<a data-toggle="collapse" data-parent="#accordion4"
					href="#menucollapsetwo" ><h3 class="panel-title">
						<i class="fa fa-users sideMenuIcon" style="margin-top: 0px !important;"></i>
						Staff - Student<i class="glyphicon glyphicon-triangle-bottom"
							style="float: right;"></i>
					</h3></a>
			</div>
			<div id="menucollapsetwo" class="accordion-body collapse">
				<div class="panel-body">
					<ul class="nav nav-pills nav-stacked">
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="CLTMAPDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=getclassandteacherList" id="sub_module_3_8">Class Teacher Mapping</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="CLTMAPDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a
											href="teachmap.html?method=AddClassTeacherMapping" id="sub_module_3_15">
											Class Wise Teacher Mapping</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
							<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="CLTMAPDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a
											href="teachmap.html?method=AddsubjectTeacherMapping" id="sub_module_3_16">
											Subject Wise Teacher Mapping</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="TMTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=gettimetable"
												id="sub_module_3_14">Time Table Management</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="VTMDIS" name="daymap" property="key">
							  <logic:equal value="true" name="daymap" property="value"> 
						 <li><a href="teachermenuaction.html?method=viewTeacherListForTimeTable" id="sub_module_3_9">View Time Table</a></li>
						</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
							<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="SFTTSUBDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value"> 
						
						  <li><a
							href="menuslist.html?method=gettodaytimetablelist" id="sub_module_3_13">Time Table Substitution</a></li>
						</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="UPASSDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="teachermenuaction.html?method=assignmentView" id="sub_module_3_4">Upload Assignment</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="UPASSDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="teachermenuaction.html?method=projectAssign" id="sub_module_3_12">Project</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					</ul>
				</div>
				<br />
			</div>
		</div>
</div>
	</div>
</body>
</html>
