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

<script type="text/javascript" src="JS/backOffice/Leave/LeaveStaffList.js"></script>

</head>
<script type="text/javascript">
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip({
        placement : 'bottom'
    });
});
</script>

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
.allstudent{
	width: 100%;
    max-width: 100%;
    margin-top: 20px;
}

.allstudent td:nth-child(4){
	text-align: right;
}
.allstudent td:nth-child(5){
	text-align: center;
}
.lapplicable,.updateappl{
	text-align: right;
}
.ui-dialog{
top:50px !important;
}
.edit{
float: right;
top: 5px;
}
.addml{
	float: right;
	top: 6px;
}
#addmorel{
margin-right: 10px;
}
input[readonly]{
    background-color: #f8f3f3 !important;
}
</style>


<body>
	<div id = leavedialog>
			<div class='col-md-12'>
				<table class = "allstudent allLeaves">
					<thead>
						<tr>
							<th><input type='checkbox' id = "allLeaves"></th>
							<th>Leave Code</th>
							<th>Leave</th>
							<th>No.Of Leaves</th>
							<th>Applicable</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name = 'allleaves'>
							<logic:iterate id='allleaves' name='allleaves'>
								<tr>
									<td><input type='checkbox' class="leaveselect" value='<bean:write name='allleaves' property='leaveid'/>'></td>
									<td><bean:write name='allleaves' property='shortName'/></td>
									<td><bean:write name='allleaves' property='leaveName'/></td>
									<td class='nol'><bean:write name='allleaves' property='noofleaves'/></td>
									<td><input size="4" maxlength="4" type='text' class='form-control lapplicable' readonly onkeypress="return isNumberKey(event)" value='<bean:write name='allleaves' property='noofleaves'/>'></td>
								</tr>
							</logic:iterate>
						</logic:present>
					</tbody>
				</table>
			</div>
	</div>
	<div id = 'addedit'>
			<div class='col-md-12'>
				<div id='updatetable' style="display: none;">
						<table class = "allstudent updateLeaves">
						<thead>
							<tr>
								<th>Sl No.</th>
								<th>Leave Code</th>
								<th>Leave</th>
								<th>No.Of Leaves</th>
								<th>Applicable</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
						</table>
				</div>
				<div id='addtable' style="display: none;">
						<table class = "allstudent addLeaves">
						<thead>
							<tr>
								<th><input type='checkbox' id='addmul' value='5'></th>
								<th>Leave Code</th>
								<th>Leave</th>
								<th>No.Of Leaves</th>
								<th>Applicable</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
						</table>
				</div>
			</div>
	</div>
	
	<input type='hidden' id = 'mapid'>
	<input type='hidden' id = 'teaid'>
	
	<div class="content" id="div1">
		<p>
				<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;<span
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
		
	<div class="successmessagediv" align="center">
		<div class="message-item">
			<a href="#" class="msg-success bg-msg-succes"><span class="success"> 
			</span></a>
		</div>
	</div>
		
		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#" style="color: #fff;"><h3
						class="panel-title grade" style="color: #000000; line-height: 18px;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Staff Leave</h3></a>
				
				<div class="navbar-right">
					<span id="addleaves" class="buttons">New</span>
					<span id="leaveback" class="buttons">Back</span>
				</div>
			
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
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="accyname" value='<logic:present name='academicname' scope='request'><bean:write name='academicname'/></logic:present>'>
									</div>
								</div>
								<input type='hidden' id='accyid' value='<logic:present name='accyid' scope='request'><bean:write name='accyid'/></logic:present>'>
							</div>
							<div class="col-md-6">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="location" value='<logic:present name='locatinname' scope='request'><bean:write name='locatinname'/></logic:present>'>
									</div>
								</div>
								<input type='hidden' id='locid' value='<logic:present name='locid' scope='request'><bean:write name='locid'/></logic:present>'>
							</div>
						 </div>	
					</div>
						<div id="markstable">
							<table class='table' id='allstudent'>
								<thead>
								<tr>
									<th><input type="checkbox" id="selectall"></th>
									<th>Reg. Id</th>
									<th>Staff</th>
									<th>Status</th>
								</tr>
								</thead>
								<tbody>
								<logic:present name='getStatus'>
									<logic:iterate id="getStatus" name='getStatus'>
									<tr>
										<td><input type="checkbox" class="select <bean:write name='getStatus' property='status'/>" value='<bean:write name='getStatus' property='teacherId'/>' id='<bean:write name='getStatus' property='leaveid'/>'></td>
										<td><bean:write name='getStatus' property='teaRegId'/></td>
										<td><bean:write name='getStatus' property='teacherName'/></td>
										<td class='<bean:write name='getStatus' property='status'/>'><span class='<bean:write name='getStatus' property='mapstatus'/>'><bean:write name='getStatus' property='mapstatus'/></span></td>
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
