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
<style>
#iconsimg {
	vertical-align: 0;
}
</style>
</head>

<body>
	<div class="col-md-2 leftmenu">
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">

				<div class="panel-heading leftNav" role="tab" id="headingOne"
					>

					<h3 class="panel-title" id="beforeparent">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#menucollapseOne">
							<h3 class="panel-title active" >
								<div class="fa fa-user sideMenuIcon"></div>
								Student&nbsp;&nbsp;<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							</h3>
						</a>
					</h3>
				</div>
				<div id="menucollapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">
							<!--  <li><a href="reportaction.html?method=studentDetailsReport">Student old </a></li>  -->

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STUREPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="reportaction.html?method=StudentReports"
												id="sub_module_8_1">Student</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="CUSTSTRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a
												href="reportaction.html?method=CustomizableStudentReports"
												id="sub_module_8_19">Customizable Student Reports </a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="CUSTSTRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a
												href="studentattendanceReport.html?method=studentAttendanceReport"
												id="sub_module_8_3">Attendance Details</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="PHDDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a
												href="phonedirectory.html?method=phonedirectoryAction"
												id="sub_module_8_5">Phone directory</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="INACTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a
												href="reportaction.html?method=InactivatedstudentList"
												id="sub_module_8_7">InActive Students</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>



							<!-- <li><a href="reportaction.html?method=marksDetailsReport" id="sub_module_8_4">Marks Details</a></li> -->

							<!-- <li><a href="studentBonafiedReport.html?method=studentBonafiedCertificateReportAction" id="sub_module_8_6">Bonafide Certificate</a></li> -->

						</ul>

					</div>
				</div>
			</div>


			<div class="panel panel-primary">

				<logic:present name="UserDetails" scope="session">
					<logic:iterate id="daymap" name="UserDetails"
						property="permissionmap" scope="session">
						<logic:equal value="STAREPDIS" name="daymap" property="key">
							<logic:equal value="true" name="daymap" property="value">
								<div class="panel-heading leftNav" role="tab" id="headingTwo"
									>
									<h3 class="panel-title">
										<a class="collapsed" role="button" data-toggle="collapse"
											data-parent="#accordion" href="#menucollapseTwo" aria-expanded="false"
											aria-controls="collapseTwo">
											<h3 class="panel-title active">
												<i class="fa fa-users sideMenuIcon"></i>
												Staff <i
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
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">
							<!-- <li><a href="#">Staff Information</a></li> -->
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="RELRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a
												href="staffattendancereport.html?method=staffAttendanceReport"
												id="sub_module_8_8">Attendance Details</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="LEAREPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a
												href="teacherLeaveDetailsReport.html?method=getLeaveDetailsFilters"
												id="sub_module_8_9">Leave Report</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="RELRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a
												href="staffreleivingorder.html?method=staffReleivingOrderReport"
												id="sub_module_8_10">Releiving Order</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							 <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="SERCETDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a
												href="staffservicereport.html?method=staffServiceReport"
												id="sub_module_8_11">Service Certificate</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="PAYCETDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="staffpayreport.html?method=staffPayReport"
												id="sub_module_8_12">Pay Certificate</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> 
						</ul>
					</div>
				</div>
			</div>

			<div class="panel panel-primary">

				<logic:present name="UserDetails" scope="session">
					<logic:iterate id="daymap" name="UserDetails"
						property="permissionmap" scope="session">
						<logic:equal value="FEERDIS" name="daymap" property="key">
							<logic:equal value="true" name="daymap" property="value">
                                   <div class="panel-heading leftNav" role="tab" id="headingThree" >
									<h3 class="panel-title">
										<a class="collapsed" role="button" data-toggle="collapse"
											data-parent="#accordion" href="#menucollapseThree"
											 aria-expanded="false"
											aria-controls="collapseTwo">
											<h3 class="panel-title active">
												<i class="fa fa-money sideMenuIcon"></i>
												Fee<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
											</h3>
										</a>
									</h3>
								</div>
							</logic:equal>
						</logic:equal>
					</logic:iterate>
				</logic:present>

				

				<div id="menucollapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingThree">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">
							<!-- <li><a href="reportaction.html?method=studentFeeStatusReport" id="sub_module_8_13">Current Student Fee Payments </a></li> -->
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="TRAFEEDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="transportfeereceipt.html?method=TransportFeeReceipt" id="sub_module_8_14">Transport Fee</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="DTAOFDDDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="reportaction.html?method=DDReport" id="sub_module_8_20">Details Of DD</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="DEFLISTDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="reportaction.html?method=defaultFeeReport" id="sub_module_8_16">Fee Defaulter</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="FEECOLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="reportaction.html?method=FeeCollectionReport" id="sub_module_8_17">Fee Collection</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="ITFCOLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="reportaction.html?method=ITFeeCollectionReport" id="sub_module_8_18">IT Fee Collection</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="INAEXDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="reportaction.html?method=getExpenditureReport" id="sub_module_8_21"> Income and Expenditure</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
						</ul>

					</div>
				</div>
			</div>


			<div class="panel panel-primary">
						<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="EXREPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<div class="panel-heading leftNav" role="tab" id="headingFour"
					                              >
												<h3 class="panel-title">
												 <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#menucollapseFive"  aria-expanded="false" aria-controls="collapseTwo">
					                        <h3 class="panel-title active">
							                 	<div class="fa fa-list-alt sideMenuIcon"></div>
							                 	Exam<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							                     </h3>
						                        </a>
					                         </h3>
					                         	</div>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
				<div id="menucollapseFive" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">
						     
							<%--  <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="PROREPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="reportaction.html?method=progressReport" id="sub_module_8_2">Progress Report</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> --%>
							
								<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="CLSPERDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="reportaction.html?method=classPerformance" id="sub_module_8_22">Class Performance</a></li>
										</logic:equal>
									</logic:equal> 
								</logic:iterate>
							</logic:present>
						
						
						
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="CLSPERDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li><a href="reportaction.html?method=reportCardDownload" id="sub_module_8_23">Report Card</a></li>
										</logic:equal>
									</logic:equal> 
								</logic:iterate>
							</logic:present>
						
						
						
						
						
						
					<!-- <li><a href="reportaction.html?method=examReportClassWise" id="sub_module_8_15">Exam Report Classwise</a></li> -->
						</ul>
					</div>
				</div>
				
				
				
				
				
				
				
		
			
			
			
			
	<!-- 			 <div class="panel panel-primary">
							  <h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#"  style="color:#767676" aria-expanded="false" aria-controls="collapseFive">
								  <h3 class="panel-title active" style="    color: #fff;">Transport Fee<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3>
								</a>
								
							  </h4>
							</div>
							<div id="menucollapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
							  <div class="panel-body">
								<ul class="nav nav-pills nav-stacked"><li><a href="transportfeereceipt.html?method=TransportFeeReceipt" id="sub_module_8_24">Transport Fee</a></li>
								
								
						
								<li><a href="reportaction.html?method=classPerformance" id="sub_module_8_22">Class Performance</a></li>
				
								
								
								
								
								
								</ul>
								
							  </div>
							</div>
						  </div>
				
			 -->
			</div>
			
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 	<div class="panel panel-primary">
						<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="RELRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<div class="panel-heading leftNav" role="tab" id="headingSix">
												<h3 class="panel-title">
												 <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#menucollapseFive"  aria-expanded="false" aria-controls="collapseTwo">
					                           <h3 class="panel-title active">
							                 	
							                 	<i class="fa fa-bus sideMenuIcon"></i>
							                 	Transport<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
							                     </h3>
						                        </a>
					                         </h3>
					                         	</div>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
				<div id="menucollapseSix" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">

							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="RELRDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<li class="nav nav-pills nav-stacked"><li><a href="transportfeereceipt.html?method=TransportFeeReceipt" id="sub_module_8_14">Transport Fee</a></li>
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
$(document).ready(function(){
	
	$("#headingOne").click(function(e){
		
		$("#menucollapseOne").toggle();
		$("#menucollapseTwo").slideUp();
		$("#menucollapseThree").slideUp();
		$("#menucollapseFive").slideUp();
		});
	$("#headingTwo").click(function(e){
	
		$("#menucollapseTwo").toggle();
		$("#menucollapseOne").slideUp();
		$("#menucollapseThree").slideUp();
		$("#menucollapseFive").slideUp();
		});
	$("#headingThree").click(function(e){
		
		$("#menucollapseThree").toggle();
		$("#menucollapseOne").slideUp();
		$("#menucollapseTwo").slideUp();
		$("#menucollapseFive").slideUp();
		});
	

$("#headingFour").click(function(e){
		
		$("#menucollapseThree").slideUp();
		$("#menucollapseOne").slideUp();
		$("#menucollapseTwo").slideUp();
		$("#menucollapseFive").toggle();
		});
		
$("#headingSix").click(function(e){
	$("#menucollapseSix").toggle();
	$("#menucollapseOne").slideUp();
	$("#menucollapseTwo").slideUp();
	$("#menucollapseFive").slideUp();
	
	
	});
	
	
});

</script>
</body>
</html>
