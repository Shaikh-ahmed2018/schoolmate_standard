<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>

<link href="CSS/newUI/headerIcon.css" rel="stylesheet">

<title>eCampus Pro</title>
<body>
		<div class="col-md-2 leftmenu">
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading leftNav" >
					<!-- <a data-toggle="collapse" data-parent="#accordion" href="#menucollapseOne">
					<h3 class="panel-title active" ><i class="fa fa-list-alt" ></i>Exam<i class="glyphicon glyphicon-triangle-bottom dropdowns" ></i></h3>
					</a>	 -->
					<h3 class="panel-title">
						<a href="#" style="color: #000000" aria-expanded="false" aria-controls="collapseFour">
							<h3 class="panel-title active">
							<i class='fa fa-list-alt sideMenuIcon'></i>
								Exam &nbsp;&nbsp;<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>
					</h3>	
				</div>
				<div id="menucollapseOne" class="accordion-body collapse in">
					<div class="panel-body" >
						<ul class="nav nav-pills nav-stacked">
							
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="menuslist.html?method=examList">Exam Details</a></li>
											</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
									
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="parentMenu.html?method=examdetails">View Exam Details</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="GRDDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=gradeList" id="sub_module_5_1">Grade Setting</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examTimetablePath.html?method=gradeDependency" id="sub_module_5_2">Grade Dependency</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examTimetablePath.html?method=getEaxmListYear" id="sub_module_5_3">Exam Setting</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							<!-- MAXMSET -->
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examCreationPath.html?method=getMaxMarksSetUp" id="sub_module_5_9">Report Card Setup</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXMTMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
											<li><a href="examTimetablePath.html?method=getExamDetails" id="sub_module_5_5">Exam Timetable Old</a></li> 
											
											</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examTimetablePath.html?method=examdependency" id="sub_module_5_4">Exam Dependency</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examTimetablePath.html?method=getEaxmTimeTableListYear" id="sub_module_5_5">Exam Time Table</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examTimetablePath.html?method=getExamMarksStudentwise" id="sub_module_5_6">Exam Marks-Studentwise</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="examTimetablePath.html?method=subjectmarksList" id="sub_module_5_7">Exam Marks-Subjectwise</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="uploadStudentXSL.html?method=excelUploadForStudentsMarks" id="sub_module_5_8">Mark Sheet Excel Upload</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							

						<%-- <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUPROMDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=studentPromotionList">
												Promotion</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%>
							
							
							
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="">Revision Question Bank</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
							
								
				
						</ul>
						
					</div>
				  <br/>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading leftNav" role="tab" id="headingTwo">
					<h3 class="panel-title">
						<a href="#" style="color: #000000" aria-expanded="false" aria-controls="collapseFour">
							<h3 class="panel-title active">
							<i class='glyphicon glyphicon-upload sideMenuIcon'></i>
								Bulk Uploads &nbsp;&nbsp;<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>
					</h3>
				</div>
				<div id="menucollapseTwo" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
											<li><a href="uploadStudentXSL.html?method=excelUploadForStudentsMarks" id="sub_module_5_8">Upload Student Marks</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
						</ul>
					</div>
				</div>
			</div>
		</div>
		</div>
	<script>
		$(document).ready(function() {
			$(".leftNav").click(function(e) {
			$("div[id^='menucollapse']").not($(this).next("div[id^='menucollapse']")).removeClass("in");
			$(this).next("div[id^='menucollapse']").toggleClass("in");
			}); 
		});
	</script>
</body>
</html>
