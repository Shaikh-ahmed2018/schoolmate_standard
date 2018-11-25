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

<body>
	<div class="col-md-2 leftmenu">

		<div class="panel panel-primary">
			<div class="panel-heading leftNav" >
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#menucollapseOne" style="color: #fff;"><h3 class="panel-title active">
					<i class="fa fa-calendar" style="padding-right: 50px;"></i>
						Leave<i class="glyphicon glyphicon-triangle-bottom dropdowns"></i>
					</h3></a>
			</div>
			<div id="menucollapseOne" class="accordion-body collapse in">
				<div class="panel-body">
					<ul class="nav nav-pills nav-stacked">

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LEAVEAPPROVE" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
									<li><a href="leavebank.html?method=LeaveCategoryList" id="sub_module_14_1">Leave Types</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LEAVEAPPROVE" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="leavebank.html?method=StaffLeaveBank" id="sub_module_14_5">Staff Leave Mapping</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 
						
					<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LEAVEAPPROVE" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="leavebank.html?method=StaffLeaveBankDetails" id="sub_module_14_6">Staff Leave Bank</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> 

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LEAVEREQ" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="teachermenuaction.html?method=leaveRequest" id="sub_module_14_2">Leave Request & Status</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LEAVEAPPROVE" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="teachermenuaction.html?method=leaveApproval" id="sub_module_14_3">Approve/Reject Leave</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<%-- <logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LEAVEAPPROVE" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">

										<li><a href="menuslist.html?method=LeaveBankList">Leave
												Bank</a></li>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present> --%>
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="LEAVEREQ" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<li><a href="teachermenuaction.html?method=getviewLeaveDetails" id="sub_module_14_4">Leave Bank</a></li>
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
</body>
</html>
