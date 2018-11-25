<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<title>eCampus Pro</title>
<body>
	<div class="col-md-2 leftmenu">
	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading leftNav" role="tab" id="headingOne">
					<h3 class="panel-title" id="beforeparent">
						<a href="#" style="color: #000000">
						  <h3 class="panel-title active">
						<i class="fa fa-user sideMenuIcon"></i>
								Student Details &nbsp;&nbsp;
							<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h3>
				</div>
				<div id="menucollapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">
						
						 <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=studentList" id="sub_module_2_3">Registration</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
					</logic:present>
                    
                    <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUPHODIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=studentPhotosheet" id="sub_module_2_14">Photosheet</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
					</logic:present>
                    
                   <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUSDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=studentSearch" id="sub_module_2_1">Student Search</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
					</logic:present>
                    
                    <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUATTDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="teachermenuaction.html?method=attendaceStatus" id="sub_module_2_4">Attendance</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="GRNDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=generateRollNo" id="sub_module_2_8">Generate Roll No</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>	
					<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="HOUSETDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="houseSettings.html?method=houseSetting" id="sub_module_2_9">House Setting</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="GENHOUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="houseSettings.html?method=generateHouse" id="sub_module_2_10">Generate House</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>	
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="MISDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=miscellaneousReport" id="sub_module_2_2">MIS Report</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>	
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUPROMDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=NewstudentPromotionList" id="sub_module_2_5">Student Promotion</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>	
						</ul>
					</div>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading leftNav" role="tab" id="headingFour"
					>
					<h3 class="panel-title">
						<a href="#" style="color: #000000" aria-expanded="false"
							aria-controls="collapseFour">
							<h3 class="panel-title active">
							<i class="fa fa-user sideMenuIcon"></i>
								Student Actions &nbsp;&nbsp;<i
									class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h3>
				</div>
				<div id="menucollapseFour" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingFour">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="MISDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=analyticalperformance" id="sub_module_2_16">Analytical performance </a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="DISACTDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=studentConfidentialReport" id="sub_module_2_6">Disciplinary Action</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUADIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">


										<li><a href="menuslist.html?method=StudentAppraisalReport" id="sub_module_2_7">Student Appraisal</a></li>


									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
				
						 <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUWDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=studentWithheldList" id="sub_module_2_13">Student Withheld</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					</ul>


					</div>
				</div>
			</div>
			
		<div class="panel panel-primary">
           <div class="panel-heading leftNav" role="tab" id="headingTwo" >
				<h3 class="panel-title">
					<a href="#" style="color: #000000" aria-expanded="false" aria-controls="collapseTwo">
						<h3 class="panel-title active" >
							<i class="glyphicon glyphicon-certificate sideMenuIcon"></i>
							Certificates &nbsp;&nbsp;<i
								class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
						</h3>
					</a>
				</h3>
			</div>
					
	<div id="menucollapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo" >
				
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="AABCDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="studentcertificate.html?method=studentCertificates" id="sub_module_2_11">General Certificates</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
							
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUPROMDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=tcgeneration" id="sub_module_2_15">Transfer Certificate</a></li>


									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						</ul>
					</div>
				</div>
			</div>

			<div class="panel panel-primary">
				<div class="panel-heading leftNav" role="tab" id="headingFour"
					>
					<h3 class="panel-title">
						<a href="#" style="color: #000000" aria-expanded="false"
							aria-controls="collapseFour">
							<h3 class="panel-title active">
							<i class='glyphicon glyphicon-upload sideMenuIcon'></i>
								Bulk Uploads &nbsp;&nbsp;<i
									class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h3>
				</div>
				<div id="menucollapseFour" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingFour">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

							<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="USDFDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=studentfileupload" id="sub_module_2_12">Upload Student Excel Data File</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUPHUPDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=studentphotoupload" id="sub_module_2_1u">Upload Photo</a></li>
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

<%--  	<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUENQDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=studentEnquiryList">Admission
												Enquiry</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%>

						<!-- <li><a href="parentMenu.html?method=meetingandeventsdetails">Meetings&Events</a></li> 
													<li><a href="parentMenu.html?method=studentTimeTable">Student TimeTable</a></li> -->
						
						
						<%-- <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUPROMDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=studentAppraisalList">Student Appraisal</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=schoolTransfer">School Transfer</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>  --%>

