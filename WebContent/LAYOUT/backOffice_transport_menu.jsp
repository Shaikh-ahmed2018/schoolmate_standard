<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
	<script type="text/javascript" src="JS/login/tranport.js"></script>
	</head>
<body>
		<div class="col-md-2 leftmenu">
			<div class="panel-group" id="accordion3">
			<div class="panel panel-primary" style="border: none; background-color: transparent; margin: 1px 0px; box-shadow: none;">
			
			<div class="panel-heading leftNav transport">
					<a data-toggle="collapse" data-parent="#accordion3" href="#menucollapseOne" >
					<h3 class="panel-title" ><i class="fa fa-bus sideMenuIcon" style="margin-top: 0px !important;"></i>Transport<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3></a>		
				</div>
				
				<div id="menucollapseOne" class="accordion-body collapse in">
					<div class="panel-body" >
						<ul class="nav nav-pills nav-stacked">
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STGDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=SchoolWiseStageList" id="sub_module_7_1">Stage
												Master </a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STGDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=stageWiseFeeSetup" id="sub_module_15_2">Stage Wise Amount Setup</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=schoolWiseRoutMaster" id="sub_module_7_3">Route Master</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=transportCategory" id="sub_module_7_6">Transport Category</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<%-- <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="menuslist.html?method=destWiseStudentdetails" id="sub_module_7_7">Student
												Report Destination Details</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%>


						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="DRVDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=driverList" id="sub_module_7_8">Driver Master</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="VEHDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=vehicleList" id="sub_module_7_9">Vehicle Master</a></li>

									</logic:equal>

								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						<%-- <logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="TTYDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=studentBusIDGeneration" id="sub_module_7_10">Print Bus Id Card</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>  --%>

							<%-- <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="feeupload.html?method=transportFeeCollectionUpload" id="sub_module_7_14">Upload Transport Fee</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%> 

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="FUELDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=studentBusCard" id="sub_module_7_13">Student Bus Card</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 
						</ul>
					</div>
				  <br/>
				</div>
			</div>
			
			<div class="panel panel-primary" style="border: none; background-color: transparent; margin: 1px 0px; box-shadow: none;">
			
			<div class="panel-heading leftNav transport">
					<a data-toggle="collapse" data-parent="#accordion3" href="#menucollapseTwo" >
					<h3 class="panel-title" ><i class="fa fa-money sideMenuIcon" style="margin-top: 0px !important;"></i>Transport Fee<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3></a>		
				</div>
				
				<div id="menucollapseTwo" class="accordion-body collapse">
					<div class="panel-body" >
						<ul class="nav nav-pills nav-stacked">
					
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="TTDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a
											href="menuslist.html?method=separateTransportTermList" id="sub_module_7_2">Term Setup</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
											
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="transport.html?method=transportFeeConcession" id="sub_module_7_15">Fee Concession</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=transportFeeCollection" id="sub_module_7_4">Fee Collection</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="transport.html?method=transportFeeSearch" id="sub_module_7_5">Request/Cancel</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
						</ul>
						
					</div>
				  <br/>
				</div>
			</div>
			<div class="panel panel-primary" style="border: none; background-color: transparent; margin: 1px 0px; box-shadow: none;">
			
			<div class="panel-heading leftNav transport">
					<a data-toggle="collapse" data-parent="#accordion3" href="#menucollapseThree" >
					<h3 class="panel-title" ><i class="glyphicon glyphicon-upload sideMenuIcon" style="margin-top: 0px !important;"></i>Uploads<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3></a>		
				</div>
				
				<div id="menucollapseThree" class="accordion-body collapse">
					<div class="panel-body" >
						<ul class="nav nav-pills nav-stacked">
											
					
							<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=stagefileupload" id="sub_module_7_11">Upload Stages</a></li>

									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 
						
						
						<logic:present name="UserDetails" scope="session">

							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="ROUDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=driverfileupload" id="sub_module_7_12">Upload Drivers</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 
						
						</ul>
						
					</div>
				  <br/>
				</div>
			</div>
			</div>
		</div>
</body>
</html>
