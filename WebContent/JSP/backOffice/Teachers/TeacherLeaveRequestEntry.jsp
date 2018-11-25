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

<script type="text/javascript" src="JS/backOffice/Leave/StaffLeaveRequest.js"></script>
	
<style>
.save:hover {
	cursor: pointer;
}

#allstudent th:nth-child(1){
width : 150px;
}
#allstudent td:nth-child(2),td:nth-child(3),td:nth-child(4){
text-align: right;
}
#list:hover {
	cursor: pointer;
}
.buttons{

vertical-align: 0px;

}
.form-group{
margin-bottom: 10px;
}
</style>

</head>

<body>
<form id="leaverequestid" method="post" enctype="multipart/form-data">
	<div class="content" id="div-main">
		<p style="margin-top: 5px; margin-bottom: 5px;" id="pageHeading">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span>Leave Request</span>
		</p>
      <div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					<a data-toggle="collapse" data-parent="#accordion"
					href="#collapseOne" style="color: #000000"><h3 class="panel-title"><i
					class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Leave Request Details
					</h3></a>

						<div class="navbar-right">
								<span class="buttons" id="save" style ="margin-right:1px;">Save</span>
								<span id="back" class="buttons">Back</span>
						</div>
				</div>
				
		<input type="hidden" name="userid"	id="userid" value="<logic:present name="parentid" ><bean:write name="parentid"  /></logic:present>"  />			
		
		<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
			<div class="panel-body">
			<div class="col-md-12" id="txtstyle">
				<div class="col-md-6" >
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Leave Type
						</label>
						<div class="col-xs-7">
							<select class="form-control" name="leavetype" id="leavetype"  tabindex="2" onchange='HideError(this)'>
							<logic:present name="LeaveList" scope="request">
							<option value="">----------Select----------</option>
							<logic:iterate id="LeaveList" name="LeaveList" scope="request"><option value='<bean:write name='LeaveList' property='leaveID'/>'><bean:write name='LeaveList' property='leaveName'/></option>
							</logic:iterate>
							</logic:present>
							 </select>
						</div>
					</div>
					<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">From Date
					</label>
					<div class="col-xs-7">
					<input type="text" name="fromdate" id="Fromdate" tabindex="3" readonly="readonly"  onchange='HideError(this)'
					class="form-control" value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="fromdate"/></logic:present>"></input>
					</div>
					</div>
						
					<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Session(Start)
					</label>
					<div class="col-xs-7">
						<select name="starttime" id="startsessionDay"  class="form-control" tabindex="5" onchange='HideError(this)'>
						<option value="FH">First Half</option>
						<option value="SH">Second Half</option>
						</select>
					</div>
					</div>		
								
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Reason
								</label>
								<div class="col-xs-7" style = "padding-bottom: 10px;">
									<textarea style="font-size: 15px;" name="reason" rows="4" class="form-control" id="reason"  tabindex="7" onchange='HideError(this)'><logic:present name="leavedatails"><bean:write name="leavedatails" property="reason" /></logic:present></textarea>
								</div>
							</div>
							
						</div>
						<div class="col-md-6">

								 	<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Requesting To
								</label>
								<div class="col-xs-7">
								<select class="form-control" name="requestto" id="requesttoid"  tabindex="1" onchange='HideError(this)'>
								<option value="">----------Select----------</option>
								<logic:present name="requestto">
								<logic:iterate id='requestto' name='requestto'>
								<option value="<bean:write name="requestto" property="teacherId"/>"><bean:write name="requestto" property="teacherName"/></option>
								</logic:iterate>
								</logic:present>
							    </select>
								</div>
							</div> 
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">To Date</label>
								<div class="col-xs-7">
									<input type="text" name="todate" id="todate" tabindex="4" onchange='HideError(this)'
										readonly="readonly" 
										class="form-control" 
										 value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="todate"/></logic:present>" />
									
								</div>
							</div>
						
							
							<div class="form-group clearfix">
						
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Session(End)
								</label>
								
								<div class="col-xs-7">
									<select name="endtime" id="endsessionDay" class="form-control"  tabindex="6" onchange='HideError(this)'>
										<option value="FH">First Half</option>
										<option value="SH">Second Half</option>
									</select>
								</div>
									
							</div>	
					
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Total
									Leave
								</label>
								<div class="col-xs-7">
									<input type="text" name="totalleave" id="totalleaves" readonly tabindex="8" onchange='HideError(this)'
										class="form-control" value="<logic:present name="leavedatails"><bean:write name="leavedatails" property="totalleave"/></logic:present>"></input>
								</div>
								<!-- <span class="submitbutton" onclick="view()" data-toggle="modal" data-target="#myModal" style="cursor:pointer;float: right;    margin: 0px 56px -12px 0px;text-decoration:underline;">Leave Balance</span> -->
							</div>
						
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">File
									Upload
								</label>
								<div class="col-xs-7">
									<input type="file" name="fileupload" id="fileupload" tabindex="9"
										class="form-control">
										<input type="button" id="document2btn" name="idproof" class="downloadDoc" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="downloadIdTitle">x</span>
									
								</div>
							</div>
							
						<input type='hidden' id='academic_year' value='<logic:present name="academic_year"><bean:write name ="academic_year"/></logic:present>'>	
						
						<input type="hidden" name="leavecount" id="hiddenleavecount"  value='<logic:present name="leaveCalculation"><bean:write name ="leaveCalculation" property ="leaveCount"/></logic:present>'/>	
						
						<input type="hidden" name="hiddenleavesumcount" id="hiddenleavesumcount"  value='<logic:present name="leaveCalculation"><bean:write name ="leaveCalculation" property ="leaveCountSum"/></logic:present>'/>	
							
						<input type="hidden" name="defaultprofile"	id="hiddenprofile" value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="fileupload" /></logic:present>"  />							
							
							<input type="hidden" id="hiddenrequestto"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="requesttoid" /></logic:present>'>
								
								<input type="hidden" id="hiddenleavetype"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="leavetype"/></logic:present>'>
								
							<input type="hidden" id="hiddensno" name="sno"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="sno" /></logic:present>'>	
								
							<input type="hidden" id="hiddenstartsession"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="starttime" /></logic:present>'>		
								
							<input type="hidden" id="hiddenendsession" 
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="endtime" /></logic:present>'>		
						
							<input type="hidden" id="hiddenstudent" name="studentFname"
								value='<logic:present name="leavedatails" scope="request"><bean:write name="leavedatails" scope="request" property="studentFname" /></logic:present>'>		
							
							<input type="hidden" name="userhidden" class="userhiddenclass" id="userhiddenid" 
							 value='<logic:present name="parentid" scope="request"><bean:write name="parentid" scope="request"  />
							</logic:present>'></input>		
							
							<input type="hidden" id="successid" 
							value='<logic:present name="success"><bean:write name="success" />
							</logic:present>'></input>			
						</div>
						</div>
						<table class="table table-bordered" id="allstudent" >
				              <tr>
				              	 <th class="headth" style="text-align:center;">Leave Code</th>
                                 <th  class="headth" style="text-align:center;">Total Leave</th>
                                 <th class="headth"style="text-align:center;">Leaves Taken</th>
                                 <th class="headth" style = "text-align:center;">Balance</th>
				              </tr>
				              <logic:present name="BalanceList" scope="request">
							  <logic:iterate id="BalanceList" name="BalanceList" scope="request">
				              <tr id='<bean:write name="BalanceList" property="leaveid"/>'>
				              <td><bean:write name="BalanceList" property="leavetypeName" /></td>
				              <td class='<bean:write name="BalanceList" property="leaveid"/>'><bean:write name="BalanceList"  property="totalleaves" /></td>
				              <td><bean:write name="BalanceList" property="consumedLeave" /></td>
				              <td class='balL'><bean:write name="BalanceList"  property="available_leave" /></td>
				              </tr>
				              </logic:iterate>
							</logic:present>
						</table>	
						
						<input type='hidden' value='' id='MINLPM'>
						<input type='hidden' value='' id='MAXLPM'>
						<input type='hidden' value='' id='MAXCLPM'>
						<input type='hidden' value='' id='LACCU'>
						<input type='hidden' value='' id='LCYC'>
						<input type='hidden' value='' id='CLCSTD'>
						<input type='hidden' value='' id='CLCENDD'>
						<input type='hidden' value='' id='ISPROB'>
						<input type='hidden' value='' id='ISCARRY'>
						<input type='hidden' value='' id='TOTL'>
						<input type='hidden' value='' id='WKIN'>
						<input type='hidden' value='' id='ELEAVE'>
						<input type='hidden' value='' id='DATER'>
						
						<input type='hidden' value='<logic:present name='noofmonths'><bean:write name='noofmonths' /></logic:present>' id='DOJ'>
					</div>
				</div>

			</div>
		</div>
	</form>
</body>

</html>
