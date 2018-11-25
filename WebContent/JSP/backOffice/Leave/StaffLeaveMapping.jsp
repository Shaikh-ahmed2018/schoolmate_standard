<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/backOffice/Leave/staffLeaveMapping.js"></script>
</head>

<style>
.glyphicon:hover {
	cursor: pointer;
}
.Not,.Set{
background-color:#FF0000;  
display:inline-block;
text-align:center;
 color:#fff;
}
.Set{
background-color:green;
display:inline-block;
text-align:center;
 color:#fff;
}
</style>

<body>

	<div class="content" id="div1">
		<p id="pageHeading">
				<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span
					id="pageHeading">Staff Leave</span>
		</p>

	<logic:present name="success" scope="request">

			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
								name="success" scope="request" />
					</span></a>
				</div>
			</div>
	</logic:present>
		<logic:present name="error" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="error" scope="request" />
					</span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		
		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#" style="color: #fff;"><h3
						class="panel-title grade" style="color: #000000; line-height: 18px;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Staff Leave</h3></a>
			</div>
			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h3 class="modal-title" id="myModalLabel">Download</h3>
						</div>
						<div class="modal-body">
							<span id="excelDownload"><img src="images/xl.png"
								class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>
			<div id="gradeOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<div class="clearfix">
						<div class="col-md-12" id="txtstyle" >
							<div class="col-md-6">
												<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1"name="location" id="location" >
												<logic:present name="locationList" scope="request">
												<logic:iterate id="locationList" name="locationList">
												<option value='<bean:write name="locationList" property="locationId"/>'><bean:write name="locationList" property="locationName"/></option>
												</logic:iterate>
												</logic:present>
										</select>
									</div>
								</div>
								<input type='hidden' id='academic_year' value='<logic:present name='academic_year' scope='request'><bean:write name='academic_year'/></logic:present>'>
							</div>
							<div class="col-md-6">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1" name="accyear" id="accyear" >
												<logic:present name="accYearList" scope="request">
												<logic:iterate id="accYearList" name="accYearList">
												<option value='<bean:write name="accYearList" property="accyearId"/>'><bean:write name="accYearList" property="accyearname"/></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
							</div>
						 </div>	
					</div>
					
						<div id="markstable">
							<table class='table' id='allstudent'>
								<thead>
								<tr>
									<th>Sl No.</th>
									<th>Branch</th>
									<th>Academic Year</th>
									<th>Status</th>
								</tr>
								</thead>
								<tbody>
								<logic:present name='leave_map'>
									<logic:iterate id="leave_map" name='leave_map'>
									<tr>
										<td><bean:write name='leave_map' property="slno"/></td>
										<td class='mloc' id='<bean:write name='leave_map' property="locId"/>'><bean:write name='leave_map' property="locationName"/></td>
										<td class='maccy <bean:write name='leave_map' property="accyearcode"/>'><bean:write name='leave_map' property="accyear"/></td>
										<td class='<bean:write name='leave_map' property="status"/>'><span class='<bean:write name='leave_map' property="mapstatus"/>'><bean:write name='leave_map' property="mapstatus"/></span></td>
									</tr>
									</logic:iterate>
								</logic:present>
								</tbody>
							</table>
						</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>
