<!DOCTYPE html>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>eCampus Pro</title>
<body>
	<div class="leftmenu primarythemebackgound">

		<ul class="navigation_menu">

			<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails"
					property="permissionmap" scope="session">
					<logic:equal name="daymap" property="key" value="SETUPDIS">
						<logic:equal value="true" name="daymap" property="value">

							<li class="main_menus"><a class="headerNavBarA"
								href="Menus.html?method=Settings" id="module_1"> <b
									class="headerNavBarB glyphicon glyphicon-cog"></b> <span
									class="headerNavBarS">Settings</span>
							</a> <i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>

								<ul class="nav nav-pills nav-stacked primarythemebackgound primarythemecolor">
										<li><a href="menuslist.html?method=academicyear" id="sub_module_1_29">Academic Year</a></li>

													<li><a href="menuslist.html?method=schoolList"
														id="sub_module_1_1">School</a></li>
												
													<li><a href="menuslist.html?method=schoolLocation"
														id="sub_module_1_1_1">Branch</a></li>
									
													<li><a href="menuslist.html?method=streamList"
														id="sub_module_1_2">Stream</a></li>
												
													<li><a href="menuslist.html?method=classList"
														id="sub_module_1_3">Class</a></li>
												

													<li><a href="menuslist.html?method=sectionList"
														id="sub_module_1_4">Division</a></li>

													<li><a href="periodmaster.html?method=periodList"
														id="sub_module_1_48">Period Details</a></li>

													<li><a href="menuslist.html?method=SpecializationList"
														id="sub_module_1_5">Specialization</a></li>

													<li><a href="menuslist.html?method=subjectdetails"
														id="sub_module_1_8">Subject</a></li>

													<li><a href="menuslist.html?method=subjectSylabus"
														id="sub_module_1_8_8">Subject Syllabus</a></li>

													<li><a href="menuslist.html?method=laboratory"
														id="sub_module_1_9">Practical</a></li>
													<li><a href="menuslist.html?method=laboratorySylabus"
														id="sub_module_1_9_9">Practical Syllabus</a></li>
													<li><a href="menuslist.html?method=religionDetails"
														id="sub_module_1_11">Religion </a></li>

													<li><a href="menuslist.html?method=casteDetails"
														id="sub_module_1_12">Caste </a></li>

													<li><a
														href="menuslist.html?method=casteCategoryDetails"
														id="sub_module_1_13">Caste Category </a></li>

													<li><a href="menuslist.html?method=occupationDetails"
														id="sub_module_1_10">Parent-Occupation </a></li>


													<li><a href="menuslist.html?method=FeeConcessionList"
														id="sub_module_1_45">Concession</a></li>

													<li><a href="menuslist.html?method=departmentDetails"
														id="sub_module_1_6"> Staff Department</a></li>

													<li><a href="menuslist.html?method=designationList"
														id="sub_module_1_7">Staff Designation</a></li>

													<li><a href="menuslist.html?method=bankEntry"
														id="sub_module_1_46">Bank Master</a></li>

													<li><a href="menuslist.html?method=bankbranchList"
														id="sub_module_1_47">Bank Branch Master</a></li>

													<li><a href="menuslist.html?method=holidaymaster"
														id="sub_module_1_15">Holiday </a></li>

													<li><a
														href="menuslist.html?method=acdamicYearPlanList"
														id="sub_module_1_16">Activities </a></li>

													<li><a
														href="menuslist.html?method=professionalTaxSettings"
														id="sub_module_1_pt">Prof. Tax Settings </a></li>

								</ul></li>



						</logic:equal>
					</logic:equal>
				</logic:iterate>
			</logic:present>

					<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STFIDDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li class="main_menus"><a href="#"> <b class="headerNavBarB glyphicon glyphicon-print"> </b> <span
									class="headerNavBarS">ID Card</span></a>
									<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
									<ul class="nav nav-pills nav-stacked primarythemebackgound primarythemecolor">
										<li><a href="menuslist.html?method=DesignStaffIDCard" id="sub_module_1_19"> Staff ID Card Design</a></li>
										<li><a href="menuslist.html?method=NewstudentIdCardDesign" id="sub_module_1_20"> Student ID Card Design</a></li>
										<li><a href="menuslist.html?method=printMultiStaffIDCardDesign" id="sub_module_1_23"> Staff ID Card Print</a></li>
										<li><a href="menuslist.html?method=PrintStudentMultipleIDCard" id="sub_module_1_25"> Student ID Card Print</a></li>
									</ul>
										</li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

			<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails"
					property="permissionmap" scope="session">
					<logic:equal name="daymap" property="key" value="ADMDIS">
						<logic:equal value="true" name="daymap" property="value">
							<li class="main_menus"><a class="headerNavBarA"
								href="Menus.html?method=Admission" id="module_6"> <b
									class="headerNavBarB fa fa-graduation-cap"> </b> <span
									class="headerNavBarS">Online Admission</span>
							</a>
 							<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
								<ul
									class="nav nav-pills nav-stacked primarythemebackgound primarythemecolor">

													<li><a href="menuslist.html?method=tempadmissionMenu"
														id="sub_module_6_1">Registration</a></li>


								</ul></li>
						</logic:equal>
					</logic:equal>
				</logic:iterate>
			</logic:present>



			<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails"
					property="permissionmap" scope="session">
					<logic:equal name="daymap" property="key" value="STUDDIS">
						<logic:equal value="true" name="daymap" property="value">

							<li class="main_menus"><a class="headerNavBarA"
								href="Menus.html?method=Student" id="module_2"> <b
									class="headerNavBarB"> <i class="fa fa-user"></i>
								</b> <span class="headerNavBarS">Student</span>
							</a>

 							<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
								<ul
									class="nav nav-pills nav-stacked primarythemebackgound primarythemecolor">

									<logic:present name="UserDetails" scope="session">
										<logic:iterate id="regmap" name="UserDetails"
											property="permissionmap" scope="session">
											<logic:equal value="STUDIS" name="regmap" property="key">
												<logic:equal value="true" name="regmap" property="value">

													<li><a href="menuslist.html?method=studentList"
														id="sub_module_2_3">Registration</a></li>
												</logic:equal>
											</logic:equal>
										</logic:iterate>
									</logic:present>

									<logic:present name="UserDetails" scope="session">
										<logic:iterate id="psheetmap" name="UserDetails"
											property="permissionmap" scope="session">
											<logic:equal value="STUPHODIS" name="psheetmap"
												property="key">
												<logic:equal value="true" name="psheetmap" property="value">

													<li><a href="menuslist.html?method=studentPhotosheet"
														id="sub_module_2_14">Photosheet</a></li>
												</logic:equal>
											</logic:equal>
										</logic:iterate>
									</logic:present>

									<logic:present name="UserDetails" scope="session">
										<logic:iterate id="searchmap" name="UserDetails"
											property="permissionmap" scope="session">
											<logic:equal value="STUSDIS" name="searchmap" property="key">
												<logic:equal value="true" name="searchmap" property="value">

													<li><a href="menuslist.html?method=studentSearch"
														id="sub_module_2_1">Student Search</a></li>
												</logic:equal>
											</logic:equal>
										</logic:iterate>
									</logic:present>

									<logic:present name="UserDetails" scope="session">
										<logic:iterate id="mismap" name="UserDetails"
											property="permissionmap" scope="session">
											<logic:equal value="MISDIS" name="mismap" property="key">
												<logic:equal value="true" name="mismap" property="value">

													<li><a
														href="menuslist.html?method=miscellaneousReport"
														id="sub_module_2_2">MIS Report</a></li>
												</logic:equal>
											</logic:equal>
										</logic:iterate>
									</logic:present>

									<logic:present name="UserDetails" scope="session">
										<logic:iterate id="attnmap" name="UserDetails"
											property="permissionmap" scope="session">
											<logic:equal value="STUATTDIS" name="attnmap" property="key">
												<logic:equal value="true" name="attnmap" property="value">

													<li><a
														href="teachermenuaction.html?method=attendaceStatus"
														id="sub_module_2_4">Attendance</a></li>

												</logic:equal>
											</logic:equal>
										</logic:iterate>
									</logic:present>
									<logic:present name="UserDetails" scope="session">
										<logic:iterate id="rolnomap" name="UserDetails"
											property="permissionmap" scope="session">
											<logic:equal value="GRNDIS" name="rolnomap" property="key">
												<logic:equal value="true" name="rolnomap" property="value">

													<li><a href="menuslist.html?method=generateRollNo"
														id="sub_module_2_8">Generate Roll No</a></li>
												</logic:equal>
											</logic:equal>
										</logic:iterate>
									</logic:present>

									<logic:present name="UserDetails" scope="session">
										<logic:iterate id="promotionmap" name="UserDetails"
											property="permissionmap" scope="session">
											<logic:equal value="STUPROMDIS" name="promotionmap"
												property="key">
												<logic:equal value="true" name="promotionmap"
													property="value">

													<li><a
														href="menuslist.html?method=NewstudentPromotionList"
														id="sub_module_2_5">Student Promotion</a></li>

												</logic:equal>
											</logic:equal>
										</logic:iterate>
									</logic:present>

									<logic:present name="UserDetails" scope="session">
										<logic:iterate id="hosemap" name="UserDetails"
											property="permissionmap" scope="session">
											<logic:equal value="HOUSETDIS" name="hosemap" property="key">
												<logic:equal value="true" name="hosemap" property="value">

													<li><a href="houseSettings.html?method=houseSetting"
														id="sub_module_2_9">House Setting</a></li>
												</logic:equal>
											</logic:equal>
										</logic:iterate>
									</logic:present>

									<logic:present name="UserDetails" scope="session">
										<logic:iterate id="hgenmap" name="UserDetails"
											property="permissionmap" scope="session">
											<logic:equal value="GENHOUDIS" name="hgenmap" property="key">
												<logic:equal value="true" name="hgenmap" property="value">

													<li><a href="houseSettings.html?method=generateHouse"
														id="sub_module_2_10">Generate House</a></li>
												</logic:equal>
											</logic:equal>
										</logic:iterate>
									</logic:present>

									<logic:present name="UserDetails" scope="session">
							<logic:iterate id="anamap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="MISDIS" name="anamap" property="key">
									<logic:equal value="true" name="anamap" property="value">
										<li><a href="menuslist.html?method=analyticalperformance" id="sub_module_2_16">Analytical performance </a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="dispmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="DISACTDIS" name="dispmap" property="key">
									<logic:equal value="true" name="dispmap" property="value">

										<li><a href="menuslist.html?method=studentConfidentialReport" id="sub_module_2_6">Disciplinary Action</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="appmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUADIS" name="appmap" property="key">
									<logic:equal value="true" name="appmap" property="value">


										<li><a href="menuslist.html?method=StudentAppraisalReport" id="sub_module_2_7">Student Appraisal</a></li>


									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
				
						 <logic:present name="UserDetails" scope="session">
							<logic:iterate id="withhmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUWDIS" name="withhmap" property="key">
									<logic:equal value="true" name="withhmap" property="value">

										<li><a href="menuslist.html?method=studentWithheldList" id="sub_module_2_13">Student Withheld</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
									<logic:present name="UserDetails" scope="session">
							<logic:iterate id="certmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="AABCDIS" name="certmap" property="key">
									<logic:equal value="true" name="certmap" property="value">
										<li><a href="studentcertificate.html?method=studentCertificates" id="sub_module_2_11">General Certificates</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
							
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="transmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUPROMDIS" name="transmap" property="key">
									<logic:equal value="true" name="transmap" property="value">

										<li><a href="menuslist.html?method=tcgeneration" id="sub_module_2_15">Transfer Certificate</a></li>


									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="sexcelmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="USDFDIS" name="sexcelmap" property="key">
									<logic:equal value="true" name="sexcelmap" property="value">

										<li><a href="menuslist.html?method=studentfileupload" id="sub_module_2_12">Upload Student Excel Data File</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="photomap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUPHUPDIS" name="photomap" property="key">
									<logic:equal value="true" name="photomap" property="value">

										<li><a href="menuslist.html?method=studentphotoupload" id="sub_module_2_1u">Upload Photo</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						
								</ul></li>

						</logic:equal>
					</logic:equal>
				</logic:iterate>
			</logic:present>



			<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails"
					property="permissionmap" scope="session">
					<logic:equal name="daymap" property="key" value="FEEMODDIS">
						<logic:equal value="true" name="daymap" property="value">
							<li class="main_menus"><a class="headerNavBarA" href="Menus.html?method=Fee"
								id="module_4"> <b class="headerNavBarB"> <i
										class="fa fa-money"></i>
								</b> <span class="headerNavBarS">Fee</span>
							</a>
								<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							
							
							<ul class="nav nav-pills nav-stacked">
						
						
						
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="fsetupmap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEESDIS" name="fsetupmap" property="key">
										<logic:equal value="true" name="fsetupmap" property="value">
						
										<li><a href="menuslist.html?method=feeDetailsList" id="sub_module_4_1">Fee Setup</a></li>
										
										<li><a href="menuslist.html?method=termList" id="sub_module_4_2">Term Setup</a></li>
										
										<li><a href="menuslist.html?method=getClassFeeSetup" id="sub_module_4_3">Class Fee Setup</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="fconfigmap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FCONFDIS" name="fconfigmap" property="key">
										<logic:equal value="true" name="fconfigmap" property="value">
										<li><a href="menuslist.html?method=fineConfiguration" id="sub_module_4_5">Fine Configuration</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="fconcrqmap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CONDIS" name="fconcrqmap" property="key">
										<logic:equal value="true" name="fconcrqmap" property="value">
										<li><a href="feecollection.html?method=FeeConcessionRequest" id="sub_module_4_11">Fee Concession Request/Cancel</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="fconcmap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CONDIS" name="fconcmap" property="key">
										<logic:equal value="true" name="fconcmap" property="value">
										<li><a href="feecollection.html?method=FeeScholorship" id="sub_module_4_7">Fee Concession</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
						
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="fcollcmap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEECOLLDIS" name="fcollcmap" property="key">
										<logic:equal value="true" name="fcollcmap" property="value">
										
										<li><a href="feeCollectionNew.html?method=feeCollectionList" id="sub_module_4_13">Fee Collection</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
						
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="fcancelmap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FCANDIS" name="fcancelmap" property="key">
										<logic:equal value="true" name="fcancelmap" property="value">
										<li><a href="feecollection.html?method=feeCancellation" id="sub_module_4_8">Fee Cancellation</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
								
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="feeupldmap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FCUDIS" name="feeupldmap" property="key">
										<logic:equal value="true" name="feeupldmap" property="value">
										<li><a href="feeupload.html?method=feeCollectionUpload" id="sub_module_4_6">Fee Collection Upload</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
				
						</ul>
							
							</li>
						</logic:equal>
					</logic:equal>
				</logic:iterate>
			</logic:present>



			<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails"
					property="permissionmap" scope="session">
					<logic:equal name="daymap" property="key" value="EXMMODDIS">
						<logic:equal value="true" name="daymap" property="value">

							<li class="main_menus"><a class="headerNavBarA" href="Menus.html?method=Exam"
								id="module_5"> <b class="headerNavBarB"> <i
										class="glyphicon glyphicon-tasks"></i>
								</b> <span class="headerNavBarS">Exam</span>
							</a>
							
							<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							<ul class="nav nav-pills nav-stacked">
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="grademap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="GRDDIS" name="grademap" property="key">
										<logic:equal value="true" name="grademap" property="value">
											<li><a href="menuslist.html?method=gradeList" id="sub_module_5_1">Grade Setting</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="exsetmap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXMDIS" name="exsetmap" property="key">
										<logic:equal value="true" name="exsetmap" property="value">
										
											<li><a href="examTimetablePath.html?method=getEaxmListYear" id="sub_module_5_3">Exam Setting</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							<!-- MAXMSET -->
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="rptcrdmat" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXMDIS" name="rptcrdmat" property="key">
										<logic:equal value="true" name="rptcrdmat" property="value">
										
											<li><a href="examCreationPath.html?method=getMaxMarksSetUp" id="sub_module_5_9">Report Card Setup</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="extimemap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="extimemap" property="key">
										<logic:equal value="true" name="extimemap" property="value">
										
											<li><a href="examTimetablePath.html?method=getEaxmTimeTableListYear" id="sub_module_5_5">Exam Time Table</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="stwisemap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="stwisemap" property="key">
										<logic:equal value="true" name="stwisemap" property="value">
										
											<li><a href="examTimetablePath.html?method=getExamMarksStudentwise" id="sub_module_5_6">Exam Marks-Studentwise</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="subwisemap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="VEXMDIS" name="subwisemap" property="key">
										<logic:equal value="true" name="subwisemap" property="value">
										
											<li><a href="examTimetablePath.html?method=subjectmarksList" id="sub_module_5_7">Exam Marks-Subjectwise</a></li>
								
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
						
						</ul>
							</li>

						</logic:equal>
					</logic:equal>
				</logic:iterate>
			</logic:present>




			<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails"
					property="permissionmap" scope="session">
					<logic:equal name="daymap" property="key" value="TRNSPORTDIS">
						<logic:equal value="true" name="daymap" property="value">
							<li class="main_menus"><a class="headerNavBarA"
								href="Menus.html?method=Transport" id="module_7"> <b
									class="headerNavBarB fa fa-bus"> </b> <span
									class="headerNavBarS">Transport</span>
							</a>
							<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							<ul class="nav nav-pills nav-stacked">
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="stagmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STGDIS" name="stagmap" property="key">
									<logic:equal value="true" name="stagmap" property="value">
										<li><a href="menuslist.html?method=SchoolWiseStageList" id="sub_module_7_1">Boarding
												Point Master </a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="stgamtmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STGDIS" name="stgamtmap" property="key">
									<logic:equal value="true" name="stgamtmap" property="value">
										<li><a href="menuslist.html?method=stageWiseFeeSetup" id="sub_module_15_2">Boarding Wise Amount Setup</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="routemap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="routemap" property="key">
									<logic:equal value="true" name="routemap" property="value">

										<li><a href="menuslist.html?method=schoolWiseRoutMaster" id="sub_module_7_3">Route Master</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="trancatmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="trancatmap" property="key">
									<logic:equal value="true" name="trancatmap" property="value">
										<li><a href="menuslist.html?method=transportCategory" id="sub_module_7_6">Transport Category</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="divermap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="DRVDIS" name="divermap" property="key">
									<logic:equal value="true" name="divermap" property="value">

										<li><a href="menuslist.html?method=driverList" id="sub_module_7_8">Driver Master</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="vehiclemap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="VEHDIS" name="vehiclemap" property="key">
									<logic:equal value="true" name="vehiclemap" property="value">

										<li><a href="menuslist.html?method=vehicleList" id="sub_module_7_9">Vehicle Master</a></li>

									</logic:equal>

								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="buscardmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="FUELDIS" name="buscardmap" property="key">
									<logic:equal value="true" name="buscardmap" property="value">

										<li><a href="menuslist.html?method=studentBusCard" id="sub_module_7_13">Student Bus Card</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="transtsetmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="TTDIS" name="transtsetmap" property="key">
									<logic:equal value="true" name="transtsetmap" property="value">

										<li><a
											href="menuslist.html?method=separateTransportTermList" id="sub_module_7_2">Term Setup</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
											
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="transfconcmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="transfconcmap" property="key">
									<logic:equal value="true" name="transfconcmap" property="value">
										<li><a href="transport.html?method=transportFeeConcession" id="sub_module_7_15">Fee Concession</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="transfcollecmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="transfcollecmap" property="key">
									<logic:equal value="true" name="transfcollecmap" property="value">
										<li><a href="menuslist.html?method=transportFeeCollection" id="sub_module_7_4">Fee Collection</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="tranrqmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="tranrqmap" property="key">
									<logic:equal value="true" name="tranrqmap" property="value">
										<li><a href="transport.html?method=transportFeeSearch" id="sub_module_7_5">Request/Cancel</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="upstagemap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="upstagemap" property="key">
									<logic:equal value="true" name="upstagemap" property="value">

										<li><a href="menuslist.html?method=stagefileupload" id="sub_module_7_11">Upload Stages</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 
						
						
						<logic:present name="UserDetails" scope="session">

							<logic:iterate id="uplddrivmap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="uplddrivmap" property="key">
									<logic:equal value="true" name="uplddrivmap" property="value">
										<li><a href="menuslist.html?method=driverfileupload" id="sub_module_7_12">Upload Drivers</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 
						
						</ul>
							</li>
						</logic:equal>
					</logic:equal>
				</logic:iterate>
			</logic:present>

			<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails"
					property="permissionmap" scope="session">
					<logic:equal name="daymap" property="key" value="LIBMENUS">
						<logic:equal value="true" name="daymap" property="value">
							<li class="main_menus library"><a class="headerNavBarA"
								href="Menus.html?method=Library" id="module_11"> <b
									class="headerNavBarB fa fa-align-center"> </b> <span
									class="headerNavBarS">Library</span>
							</a>
							<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							
							<ul class="nav nav-pills nav-stacked">
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="lbrmap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBMSDIS" name="lbrmap" property="key">
										<logic:equal value="true" name="lbrmap" property="value">

											<li><a href="LibraryMenu.html?method=libraryLocations"
												id="sub_module_11_1">Library Branch</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="litemmap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBMSDIS" name="litemmap" property="key">
										<logic:equal value="true" name="litemmap" property="value">

											<li><a href="LibraryMenu.html?method=libraryItem"
												id="sub_module_11_it">Library Item</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="libcatmap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBCTDIS" name="libcatmap" property="key">
										<logic:equal value="true" name="libcatmap" property="value">

											<li><a href="LibraryMenu.html?method=categoryType"
												id="sub_module_11_2">Category Type</a></li>


											<li><a href="LibraryMenu.html?method=subCategoryType"
												id="sub_module_11_3">Sub Category Type</a></li>


											<li><a href="LibraryMenu.html?method=subCategoryType1"
												id="sub_module_11_4">Sub Category Type 1</a></li>

											<li><a href="LibraryMenu.html?method=subCategoryType2"
												id="sub_module_11_5">Sub Category Type 2</a></li>


											<li><a href="LibraryMenu.html?method=subCategoryType3"
												id="sub_module_11_6">Sub Category Type 3</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="publishermap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBMSDIS" name="publishermap" property="key">
										<logic:equal value="true" name="publishermap" property="value">

											<li><a
												href="LibraryMenu.html?method=ListpublisherSettings"
												id="sub_module_11_7">Publishers</a></li>

											<li><a
												href="LibraryMenu.html?method=ListsupplierSettings"
												id="sub_module_11_8">Suppliers</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="gensetmap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBGSDIS" name="gensetmap" property="key">
										<logic:equal value="true" name="gensetmap" property="value">

											<li><a
												href="LibraryMenu.html?method=ListgeneralSettings"
												id="sub_module_11_9">General Settings</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="subscribermap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRDIS" name="subscribermap" property="key">
										<logic:equal value="true" name="subscribermap" property="value">
											<li><a href="LibraryMenu.html?method=subscribersDetails"
												id="sub_module_11_24">Subscriber</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="searchsubsmap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRDIS" name="searchsubsmap" property="key">
										<logic:equal value="true" name="searchsubsmap" property="value">

											<li><a
												href="LibraryMenu.html?method=SearchSubscriberDetails"
												id="sub_module_11_28">Search Subscribers</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="stockentrymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRDIS" name="stockentrymap" property="key">
										<logic:equal value="true" name="stockentrymap" property="value">

											<li><a
												href="LibraryMenu.html?method=LibraryStockEntryDetailsList"
												id="sub_module_11_25">Stock Entry</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="blocksubsmap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRDIS" name="blocksubsmap" property="key">
										<logic:equal value="true" name="blocksubsmap" property="value">

											<li><a
												href="LibraryMenu.html?method=BlackListSubscriber"
												id="sub_module_11_26">Block Subscribers</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>



							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="booksearchmap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRDIS" name="booksearchmap" property="key">
										<logic:equal value="true" name="booksearchmap" property="value">

											<li><a href="LibraryMenu.html?method=GetBookSearch"
												id="sub_module_11_27">Book Search</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="journalmap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRJSDIS" name="journalmap" property="key">
										<logic:equal value="true" name="journalmap" property="value">

											<li><a
												href="LibraryMenu.html?method=LibraryjournalSubscriptionList"
												id="sub_module_11_35">Journal Subscription </a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="transsubsmap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRDIS" name="transsubsmap" property="key">
										<logic:equal value="true" name="transsubsmap" property="value">
											<li><a
												href="LibraryMenu.html?method=TransferSubscriberDetails"
												id="sub_module_11_10">Transfer Subscribers</a></li>

										</logic:equal>

									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="wantedmap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRMSDIS" name="wantedmap" property="key">
										<logic:equal value="true" name="wantedmap" property="value">

											<li><a
												href="LibraryMenu.html?method=LibraryMostWantedDetails"
												id="sub_module_11_11">Most Wanted</a></li>

										</logic:equal>

									</logic:equal>
								</logic:iterate>
							</logic:present>
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="issuemap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBTDIS" name="issuemap" property="key">
										<logic:equal value="true" name="issuemap" property="value">

											<li><a href="LibraryMenu.html?method=issues"
												id="sub_module_11_12">Issue</a>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>



							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="returnmap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBTDIS" name="returnmap" property="key">
										<logic:equal value="true" name="returnmap" property="value">

											<li><a href="LibraryMenu.html?method=returns"
												id="sub_module_11_13">Returns</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="reservmap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBTDIS" name="reservmap" property="key">
										<logic:equal value="true" name="reservmap" property="value">

											<li><a
												href="LibraryMenu.html?method=BookReservationDetailslist"
												id="sub_module_11_14">Reservations</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="overduemap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LIBRPDIS" name="overduemap" property="key">
										<logic:equal value="true" name="overduemap" property="value">
											<li><a href="LibraryMenu.html?method=LibraryHome"
												id="sub_module_11_15">Over Due Statement</a></li>

											<li><a href="LibraryMenu.html?method=StockStatements"
												id="sub_module_11_16">Stock Statements</a></li>

											<li><a href="LibraryMenu.html?method=issuestatements"
												id="sub_module_11_18">Issue/ReIssue Statements</a></li>

											<li><a href="LibraryMenu.html?method=ReservationList"
												id="sub_module_11_17">Reservation List</a></li>

											<li><a href="LibraryMenu.html?method=journalreports"
												id="sub_module_11_19">Journal Report</a></li>
											<li><a href="LibraryMenu.html?method=newarrivalList"
												id="sub_module_11_20">New Arrival List</a></li>
											<li><a href="LibraryMenu.html?method=subscriberList"
												id="sub_module_11_21">Subscriber List</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
										
							
						</ul>
							
							</li>
						</logic:equal>
					</logic:equal>
				</logic:iterate>
			</logic:present>
			
			

			<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails"
					property="permissionmap" scope="session">
					<logic:equal name="daymap" property="key" value="STFFDIS">
						<logic:equal value="true" name="daymap" property="value">

							<li class="main_menus staff"><a class="headerNavBarA" href="Menus.html?method=Staff"
								id="module_3"> <b class="headerNavBarB fa fa-users"> </b> <span
									class="headerNavBarS">Staff</span>
							</a>
							<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							<ul class="nav nav-pills nav-stacked">
							<li><a href="menuslist.html?method=staffExcelFileUpload" id="sub_module_3_11">Upload Staff Excel Data File</a></li>
							<li><a href="menuslist.html?method=staffList" id="sub_module_3_1">Staff Registration</a></li>
							<li><a href="menuslist.html?method=staffEmployementList" id="sub_module_3_2">Staff Remuneration</a></li>
							<li><a href="menuslist.html?method=getStaffAttendance" id="sub_module_3_3">Staff Attendance</a></li>
							<li><a href="teachermenuaction.html?method=generatePayroll" id="sub_module_3_7"> Payroll </a></li>
							<li><a href="teachermenuaction.html?method=assignmentView" id="sub_module_3_4">Upload Assignment</a></li>
							<li><a href="teachermenuaction.html?method=projectAssign" id="sub_module_3_12">Project</a></li>
							<li><a href="menuslist.html?method=getclassandteacherList" id="sub_module_3_8">Class Teacher Mapping</a></li>
							<li><a href="teachmap.html?method=AddsubjectTeacherMapping" id="sub_module_3_16">Subject Wise Teacher Mapping</a></li>
							 <li><a href="teachermenuaction.html?method=viewTeacherListForTimeTable" id="sub_module_3_9">View Time Table</a></li>
							  <li><a href="menuslist.html?method=gettodaytimetablelist" id="sub_module_3_13">Time Table Substitution</a></li>
							</ul>
							</li>
						</logic:equal>
					</logic:equal>
				</logic:iterate>
			</logic:present>

			<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails"
					property="permissionmap" scope="session">
					<logic:equal name="daymap" property="key" value="LEAVEDIS">
						<logic:equal value="true" name="daymap" property="value">
							<li class="main_menus leave"><a class="headerNavBarA" href="Menus.html?method=Leave"
								id="module_14"> <b
									class="headerNavBarB glyphicon glyphicon-calendar"> </b> <span
									class="headerNavBarS">Leave</span>
							</a>
							<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="leavebank.html?method=LeaveCategoryList" id="sub_module_14_1">Leave Types</a></li>
									<li><a href="leavebank.html?method=StaffLeaveBank" id="sub_module_14_5">Staff Leave Mapping</a></li>
									<li><a href="leavebank.html?method=StaffLeaveBankDetails" id="sub_module_14_6">Staff Leave Bank</a></li>
									<li><a href="teachermenuaction.html?method=leaveRequest" id="sub_module_14_2">Leave Request & Status</a></li>
									<li><a href="teachermenuaction.html?method=leaveApproval" id="sub_module_14_3">Approve/Reject Leave</a></li>
									<li><a href="teachermenuaction.html?method=getviewLeaveDetails" id="sub_module_14_4">Leave Bank</a></li>
							
							</ul>
							</li>
						</logic:equal>
					</logic:equal>
				</logic:iterate>
			</logic:present>

			<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails"
					property="permissionmap" scope="session">
					<logic:equal name="daymap" property="key" value="INTRDIS">
						<logic:equal value="true" name="daymap" property="value">

							<li class="main_menus sms"><a class="headerNavBarA" href="Menus.html?method=SMS"
								id="module_20"> <b class="headerNavBarB fa fa-comments-o">
								</b> <span class="headerNavBarS">SMS</span>
							</a>
							<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							<ul class="nav nav-pills nav-stacked">
									<li><a href="menuslist.html?method=homeworklist" id="sub_module_20_1">Home Works</a></li>
										<li><a href="menuslist.html?method=suddenholiodayslist" id="sub_module_20_2">Sudden Holidays</a></li>
										<li><a href="menuslist.html?method=meetingslist" id="sub_module_20_3">Meetings/Events</a></li>
										<li><a href="menuslist.html?method=latecomingstudentslist" id="sub_module_20_4">Late Coming Students</a></li>
											<li><a href="menuslist.html?method=absentlist" id="sub_module_20_6">Absent</a></li>
											<li><a href="smsPath.html?method=feeSmsList" id="sub_module_20_8">Fee</a></li>
							</ul>
							</li>
						</logic:equal>
					</logic:equal>
				</logic:iterate>
			</logic:present>

			<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails"
					property="permissionmap" scope="session">
					<logic:equal name="daymap" property="key" value="REPORTDIS">
						<logic:equal value="true" name="daymap" property="value">
							<li class="main_menus report"><a class="headerNavBarA"
								href="reportaction.html?method=StudentReports" id="module_8">

									<b class="headerNavBarB"> <i class="material-icons">receipt</i>
								</b> <span class="headerNavBarS">Reports</span>
							</a>
							<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							<ul class="nav nav-pills nav-stacked">
							<li><a href="reportaction.html?method=StudentReports" id="sub_module_8_1">Student</a></li>
							<li><a href="reportaction.html?method=CustomizableStudentReports" id="sub_module_8_19">Customizable Student Reports </a></li>
							<li><a href="studentattendanceReport.html?method=studentAttendanceReport" id="sub_module_8_3">Attendance Details</a></li>
							<li><a href="phonedirectory.html?method=phonedirectoryAction" id="sub_module_8_5">Phone directory</a></li>
							<li><a href="reportaction.html?method=InactivatedstudentList" id="sub_module_8_7">InActive Students</a></li>
							<li><a href="staffattendancereport.html?method=staffAttendanceReport" id="sub_module_8_8">Staff Attendance Details</a></li>
							<li><a href="teacherLeaveDetailsReport.html?method=getLeaveDetailsFilters" id="sub_module_8_9">StaffLeave Report</a></li>
							<li><a href="staffreleivingorder.html?method=staffReleivingOrderReport" id="sub_module_8_10">Staff Releiving Order</a></li>
							<li><a href="staffservicereport.html?method=staffServiceReport" id="sub_module_8_11">Staff Service Certificate</a></li>
							<li><a href="staffpayreport.html?method=staffPayReport" id="sub_module_8_12">Staff Pay Certificate</a></li>
							<li><a href="transportfeereceipt.html?method=TransportFeeReceipt" id="sub_module_8_14">Student Transport Fee</a></li>
							<li><a href="reportaction.html?method=defaultFeeReport" id="sub_module_8_16">Fee Defaulter</a></li>
							<li><a href="reportaction.html?method=FeeCollectionReport" id="sub_module_8_17">Fee Collection</a></li>
							<li class="nav nav-pills nav-stacked"><li><a href="transportfeereceipt.html?method=TransportFeeReceipt" id="sub_module_8_14">Transport Fee</a></li>
							<li><a href="reportaction.html?method=classPerformance" id="sub_module_8_22">Class Performance</a></li>
							<li><a href="reportaction.html?method=reportCardDownload" id="sub_module_8_23">Report Card</a></li>
							</ul>
							</li>

						</logic:equal>
					</logic:equal>
				</logic:iterate>
			</logic:present>


		</ul>


	</div>
</body>
</html>
