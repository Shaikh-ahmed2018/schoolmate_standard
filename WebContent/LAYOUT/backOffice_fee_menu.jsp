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
			
			<div class="panel panel-primary">
			<div class="panel-heading  leftNav" >
					<a data-toggle="collapse" data-parent="#accordion2" href="#menucollapseOne" style="color:#fff;"><h3 class="panel-title active" ><i class="fa fa-money sideMenuIcon" style="margin-top: 0px !important;"></i>Fee<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i></h3></a>		
				</div>
				<div id="menucollapseOne" class="accordion-body collapse in">
					<div class="panel-body" >
						<ul class="nav nav-pills nav-stacked">
						
						<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEEDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
						
										<li><a href="menuslist.html?method=feeTypeList" id="sub_module_4_9">Fee Type</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
					  	</logic:present> --%>
						
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEESDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
						
										<li><a href="menuslist.html?method=feeDetailsList" id="sub_module_4_1">Fee Setup</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
											
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="TRMDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=termList" id="sub_module_4_2">Term Setup</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present> 
							
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CLSFEEDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
										<li><a href="menuslist.html?method=getClassFeeSetup" id="sub_module_4_3">Class Fee Setup</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FCONFDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=fineConfiguration" id="sub_module_4_5">Fine Configuration</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
								<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CONDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="feecollection.html?method=FeeConcessionRequest" id="sub_module_4_11">Fee Concession Request/Cancel</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="CONDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="feecollection.html?method=FeeScholorship" id="sub_module_4_7">Fee Concession</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
						
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEECOLLDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
										<li><a href="feeCollectionNew.html?method=feeCollectionList" id="sub_module_4_13">Fee Collection</a></li>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
						
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FCANDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="feecollection.html?method=feeCancellation" id="sub_module_4_8">Fee Cancellation</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
								
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FCUDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="feeupload.html?method=feeCollectionUpload" id="sub_module_4_6">Fee Collection Upload</a></li>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXPDIS" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<li><a href="menuslist.html?method=expenditureDetailsList" id="sub_module_4_10">Expenditure</a></li>
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
</body>
</html>
