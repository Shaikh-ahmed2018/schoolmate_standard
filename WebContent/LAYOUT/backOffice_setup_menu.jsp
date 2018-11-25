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


		<div class="panel panel-primary">
				<div class="panel-heading leftNav" role="tab" id="headingZero"
					>
					<h3 class="panel-title">
						<a href="#" style="color: #000000" aria-expanded="false"
							aria-controls="collapseZero">
							<h3 class="panel-title active">
							<i class="glyphicon glyphicon-calendar sideMenuIcon"></i>
								Academic Year &nbsp;&nbsp;<i
									class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h3>
				</div>
				<div id="menucollapseZero" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingFour">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

						<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="ACCYRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=academicyear"
												id="sub_module_1_29">Academic Year</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>


						</ul>

					</div>
				</div>
			</div>


		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading leftNav" role="tab" id="headingOne">
					<h3 class="panel-title" id="beforeparent">
						<a href="#" style="color: #000000">
						  <h3 class="panel-title active">
							<i class="glyphicon glyphicon-equalizer sideMenuIcon"></i>
								Master Setup&nbsp;&nbsp;
							<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>

					</h3>
				</div>
				<div id="menucollapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne" style="overflow-y:  scroll;max-height: 350px;">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SLCDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=schoolList"
												id="sub_module_1_1">School</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SLCDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=schoolLocation"
												id="sub_module_1_1_1">Branch</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=streamList"
												id="sub_module_1_2">Stream</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>


							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="CLSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=classList"
												id="sub_module_1_3">Class</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SECDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=sectionList"
												id="sub_module_1_4">Division</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
                          
                          <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SECDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="periodmaster.html?method=periodList"
												id="sub_module_1_48">Period Details</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
                          

                                 


							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SPLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=SpecializationList"
												id="sub_module_1_5">Specialization</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>



							<%-- 
							
							

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=QuotaDetails">Quota
													Details</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>





							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SUBDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=subjectdetails"
												id="sub_module_1_8">Subject</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SUBDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=subjectSylabus"
												id="sub_module_1_8_8">Subject Syllabus</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LABDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=laboratory"
												id="sub_module_1_9">Practical</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LABDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=laboratorySylabus"
												id="sub_module_1_9_9">Practical Syllabus</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
						<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="RELDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=religionDetails"
												id="sub_module_1_11">Religion </a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="CASDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=casteDetails"
												id="sub_module_1_12">Caste </a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="CASCATDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=casteCategoryDetails"
												id="sub_module_1_13">Caste Category </a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="OCUDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=occupationDetails"
												id="sub_module_1_10">Parent-Occupation </a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							 <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CONDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=FeeConcessionList" id="sub_module_1_45">Concession</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="DEPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=departmentDetails"
												id="sub_module_1_6"> Staff Department</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="DESDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=designationList"
												id="sub_module_1_7">Staff Designation</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="OCUDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
								 <li><a href="menuslist.html?method=bankEntry" id="sub_module_1_46">Bank Master</a></li>										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

	
						
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="OCUDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
								 <li><a href="menuslist.html?method=bankbranchList" id="sub_module_1_47">Bank Branch Master</a></li>										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							

							


			<%-- 				<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="TMTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="menuslist.html?method=gettimetable"
												id="sub_module_1_14">Time Table Management</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="HOLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=holidaymaster"
												id="sub_module_1_15">Holiday </a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
						


							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="ACTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=acdamicYearPlanList"
												id="sub_module_1_16">Activities </a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

                          

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="PTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=professionalTaxSettings"
												id="sub_module_1_pt">Prof. Tax Settings </a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<%-- <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="CNGDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=changeBackground"
												id="sub_module_1_17">Theme</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
							
				
							
						
							

							<%-- <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STUPROMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=studentIdCreation">
													Design ID Card</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>


							<%-- <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STUPROMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=rollnumbergenerationsettting" id="sub_module_1_18">
													General Settings</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>



						</ul>

					</div>
				</div>
			</div>
			<!-- Excel Uploads  -->
			
			<div class="panel panel-primary">
				<div class="panel-heading leftNav" role="tab" id="headingFour"
					>
					<h3 class="panel-title">
						<a href="#" style="color: #000000" aria-expanded="false"
							aria-controls="collapseFour">
							<h3 class="panel-title active">
							<i class="glyphicon glyphicon-upload sideMenuIcon"></i>
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

					<%-- 	<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SCEXDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
                    			<li><a href="settingXLUpload.html?method=LocationXLupload" id="sub_module_1_33">Schools</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="UPLSTRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="settingXLUpload.html?method=StreamXLupload"
												id="sub_module_1_34">Stream</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="UPLCLSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="settingXLUpload.html?method=ClassXLupload"
												id="sub_module_1_35">Class</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="UPLDIVDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a
												href="settingXLUpload.html?method=DivisionExcelUpload"
												id="sub_module_1_36">Division</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="UPLSPLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a
												href="settingXLUpload.html?method=SpecializationExcelUpload"
												id="sub_module_1_42">Specialization</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="UPLSPLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a
												href="settingXLUpload.html?method=SubjectExcelUpload"
												id="sub_module_1_50">Subject</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="UPLOCCDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a
												href="settingXLUpload.html?method=OccupationExcelUpload"
												id="sub_module_1_37">Parent-Occupation </a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="UPLRELDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a
												href="settingXLUpload.html?method=ReligionExcelUpload"
												id="sub_module_1_38">Religion</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="UPLCASDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a
												href="settingXLUpload.html?method=CasteExcelUpload"
												id="sub_module_1_39">Caste</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="UCASCATDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a
												href="settingXLUpload.html?method=CasteCategoryExcelUpload"
												id="sub_module_1_40">Caste Category</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="UHOLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a
												href="settingXLUpload.html?method=HolodaylistExcelUpload"
												id="sub_module_1_43">Holiday List</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>


						</ul>

					</div>
				</div>
			</div>
			
			
			<!-- ID CARD DESIGN PHASE begins---------------------------------------- -->
			<div class="panel panel-primary">
			
			<logic:present name="UserDetails" scope="session">
				<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
				  <logic:equal value="IDCARDDIS" name="daymap" property="key">
						<logic:equal value="true" name="daymap" property="value">
                         <div class="panel-heading leftNav" role="tab" id="headingTwo" >
					      <h3 class="panel-title">
						 <a href="#" style="color: #000000" aria-expanded="false"
							aria-controls="collapseTwo">
							<h3 class="panel-title active" >
							<i class="fa fa-newspaper-o sideMenuIcon"></i>
							Identity Card &nbsp;&nbsp;<i
								class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>
					</h3>
				</div>
				</logic:equal>
			</logic:equal>
		</logic:iterate>
	</logic:present>
					
	<div id="menucollapseTwo" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo" >
				
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">





							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STFIDDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=DesignStaffIDCard" id="sub_module_1_19"> Design Staff ID Card</a>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STUIDDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=NewstudentIdCardDesign" id="sub_module_1_20">	Design Student ID Card</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="TRNSIDDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=DesignTransportIDCard" id="sub_module_1_21">Design Transport ID Card</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					
					
					
					<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STFMULIDDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=printMultiStaffIDCardDesign" id="sub_module_1_23">  Print Staff ID Card</a>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							

							

							<!-- <li><a href="menuslist.html?method=remainderdetails">Remainder Details</a></li> -->

							<%-- <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STUIDSDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

								<li><a href="menuslist.html?method=DesignStudentSingleIDCard" id="sub_module_1_24">Print Student ID Card - Single</a>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
									<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STUIDMULDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=PrintStudentMultipleIDCard" id="sub_module_1_25">Print Student ID Card</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
							

							<!-- <li><a href="menuslist.html?method=remainderdetails">Remainder Details</a></li> -->

						
				
						
									<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="TRNSIDMULDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=PrintTransportMultipleIDCard" id="sub_module_1_27">Print Transport ID Card - Multiple</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						</ul>

					</div>
				</div>
			</div>
			<!-- END ID CARD DESIGN PHASE-------------------------------------------------------------------------- -->

			<div class="panel panel-primary">
				<div class="panel-heading leftNav" role="tab" id="headingFour"
					>
					<h3 class="panel-title">
						<a href="#" style="color: #000000" aria-expanded="false"
							aria-controls="collapseFour">
							<h3 class="panel-title active">
							<div class="fa fa-gears" style="padding-right: 26px; padding-left: 7px;"></div>
								Permissions &nbsp;&nbsp;<i
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
									<logic:equal value="ROLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=roleList"
												id="sub_module_1_30">Role Master</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="RPMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a
												href="menuslist.html?method=getUserRolePermission"
												id="sub_module_1_31">Assign Permissions</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="PWMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=getUserRecords"
												id="sub_module_1_32">Password Maintenance</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<%-- <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="USRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=getUsersettings"
												id="sub_module_1_44">User Settings</a></li>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="IJPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											<li><a href="menuslist.html?method=careerupdate"
												id="sub_module_1_28">Internal Job Posting</a></li>

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
