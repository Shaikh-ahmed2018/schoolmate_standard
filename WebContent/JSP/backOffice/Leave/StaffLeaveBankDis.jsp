<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=10; IE=9; IE=8; IE=7; IE=EDGE" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<script type="text/javascript" src="JS/backOffice/Leave/LeaveStaffList.js"></script>

<style>
.allstudent{
	width: 100%;
}
.allstudent th:nth-child(1){
	text-align: center;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip({
        placement : 'bottom'
    });
});

</script>
</head>
<body>
	
	<div class="col-md-10" id='div1' >
		
		<p id="pageHeading">
				<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span
					>Staff Leave Bank</span>
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
		
	<div class="successmessagediv" align="center">
		<div class="message-item">
			<a href="#" class="msg-success bg-msg-succes"><span class="success"> 
			</span></a>
		</div>
	</div>
		
		<div class="panel panel-primary" >
			
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#gradeOne" style="color: #fff;"><h3 class="panel-title grade" style="color: #000000; line-height: 18px;">
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
		<div class="panel-body">
				<div class="clearfix">
				<div class="col-md-12" id='txtstyle'>
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
								<input type='hidden' id='curr_accy' value='<logic:present name='curr_accy' scope='request'><bean:write name='curr_accy'/></logic:present>'>
							</div>
							<div class="col-md-6">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="2" name="accyear" id="accyear" >
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
				<div  style="margin-bottom: 13px;">
					<div  class="points_table_scrollbar" style="max-height: 400px; overflow: auto;">
					
				
					<table class='allstudent'>
						<thead>
							<tr>
								<th rowspan="2" >Reg.Id</th>
								<th rowspan="2" style="min-width: 160px !important;">Staff</th>
								<logic:present name='llist'>
									<logic:iterate id="llist" name='llist'>
										<th colspan="4" style="text-align: center;"><bean:write name='llist' property="shortName"/></th>
									</logic:iterate>
								</logic:present>
							</tr>
							<tr>
								<c:forEach var="llist" items="${requestScope.llist}">
								<c:forEach var="total" items="${requestScope.total}">
                    			<th><c:out value="${total}"></c:out></th>
               					</c:forEach>
               					</c:forEach>
							</tr>
						</thead>
						<tbody>
							<logic:present name='lbank' >
								<logic:iterate id="lbank" name='lbank'>
									<tr>
										<td><bean:write name='lbank' property="teaRegId"/></td>
										<td><bean:write name='lbank' property="teacherName"/></td>
											<logic:iterate id="ldetails" name='lbank' property="details">
											<td><bean:write name='ldetails' property="total_leave_year"/></td>
											<td><bean:write name='ldetails' property="totalleaves"/></td>
											<td><bean:write name='ldetails' property="lconsumed"/></td>
											<td><bean:write name='ldetails' property="lbal"/></td>
											</logic:iterate>
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
</div>
</body>
</html>
