<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
 
<script type="text/javascript" src="JS/backOffice/Teacher/LeaveRequest.js"></script>

<style>
.save:hover {
	cursor: pointer;
}
</style>

<style>
#list:hover {
	cursor: pointer;
}
</style>


</head>

<body>
		<div class="col-md-10">
			<p id="pageHeading">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span>Leave Approval</span>
			</p>
			
				<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#" style="color: #000000"><h3 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Leave Approval Details
						</h3></a>

						<div class="navbar-right">
							<span id="back" class="buttons" style="margin-right: 7px;">Back</span>
						</div>
					</div>

					<input type="hidden" name="userid" id="userid"
						value="<logic:present name="parentid" ><bean:write name="parentid"  /></logic:present>" />

					<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt">

							<div class="col-md-6" id="txtstyle" style="padding-top: 15px;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved By</label>
									<div class="col-xs-7">
										<input type="text" name="approvedby" id="approvedby"
											class="form-control" readonly="readonly"
											value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="userid"/></logic:present>"></input>
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved Start Date</label>
									<div class="col-xs-7">
										<input type="text" name="approvedstartdate"
											id="approvedstartdate" class="form-control"
											readonly="readonly"
											value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="approvedstartdate"/></logic:present>"></input>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved Session(S) </label>
									<div class="col-xs-7">
										<select name="approvedstartsession" id="approvedstartsession"
											class="form-control" readonly="readonly" disabled="true">
											<option value="">----Select----</option>
											<option value="FH">First Half</option>
											<option value="SH">Second Half</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved
										Leaves </label>
									<div class="col-xs-7">
										<input type="text" name="approvedleaves" id="approvedleaves" class="form-control" readonly="readonly" value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="leavesapproved"/></logic:present>"></input>
									</div>
								</div>

								<input type="hidden" name="approvedstartsession"
									id="happrovedstartsession"
									value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="approvedstartsessionDay"/></logic:present>"></input>

							</div>

							<div class="col-md-6" id="txtstyle" style="padding-top: 15px;">

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved Date</label>
									<div class="col-xs-7">
										<input type="text" name="approveddate" id="approveddate"
											class="form-control" readonly="readonly"
											value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="approveddate"/></logic:present>"></input>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved
										End Date </label>
									<div class="col-xs-7">
										<input type="text" name="approveendddate" id="approveendddate"
											class="form-control" readonly="readonly"
											value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="approvedenddate"/></logic:present>"></input>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Approved
										Session(E) </label>
									<div class="col-xs-7">
										<select name="approveendsession" id="approveendsession"
											class="form-control" readonly="readonly" disabled="true">
											<option value="">----Select----</option>
											<option value="FH">First Half</option>
											<option value="SH">Second Half</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Comments
									</label>
									<div class="col-xs-7">
										<textarea style="font-size: 12px;" name="comments" readonly="readonly" class="form-control" id="comments"> <logic:present name="leaveapprovaldetails"><bean:write name="leaveapprovaldetails" property="comments" /></logic:present></textarea>
									</div>
								</div>

								<input type="hidden" name="happroveendsession" id="happroveendsession" value="<logic:present name="leaveapprovaldetails" ><bean:write name="leaveapprovaldetails" property="approvedendsessionDay"/></logic:present>"></input>

							</div>
						</div>
					</div>
				</div>
			</div>
</body>

</html>
