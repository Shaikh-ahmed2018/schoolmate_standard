<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script> 

<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>

<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>

<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" /> -->
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
	
	
<!-- <script type="text/javascript"
	src="JS/backOffice/Teacher/LeaveRequest.js"></script> -->
	
	
	<script type="text/javascript"
	src="JS/backOffice/Parents/LeaveRequestEntry.js"></script>
	
	
	
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

<style>
.buttons{

vertical-align: 0px;

}
</style>


</head>

<body>
<form id="leaverequestid" action="parentMenu.html?method=leaveRequestEntry" method="post" enctype="multipart/form-data">
	<div class="col-md-10"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Leave Request
			</span>
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

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #000000"><h3 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Leave
							Request Details
					</h3>	</a>
						
						

						<div class="navbar-right" >

						<!-- 	<a href="teachermenuaction.html?method=leaveRequest"> -->
								<span class="buttons" id="save"/>Save</span>
							<!-- </a> -->
							
							
								<span id="back" class="buttons">Back</span>
							</a>
							
						</div>
				
				</div>
				
				
				
				
				
		<input type="hidden" name="studentid"	id="studentid" value="<logic:present name="parentid" ><bean:write name="parentid" property="parentID" /></logic:present>"  />			
				
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">

						<div class="col-md-6" id="txtstyle">
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Requested
									To
								</label>
								<div class="col-xs-7">
								
									<logic:present name="userlist" scope="request">
								         <select class="form-control" name="requestto" id="requesttoid">
								           <option value=""> </option>
									       <logic:iterate id="stream" name="userlist" scope="request">
											<option value='<bean:write name='stream' property='teacherid'/>'>
											<bean:write name='stream' property='teachername'/></option>
										    </logic:iterate>
							                </select>
											</logic:present>
								</div>
							</div>
							
					
							
					<%-- <div class="form-group">
				<label for="inputPassword" class="control-label col-xs-3"
					style="text-align: left; line-height: 35px; color: #797676; font-size: 16px; font-family: Roboto,sans-serif;">Student</label>
				<div class="col-xs-7">
					

							<logic:present name="studentlist" scope="request">
								         <select class="form-control" name="studSectionId" id="studAssiId">
									       <logic:iterate id="stream" name="studentlist" scope="request">
											<option value='<bean:write name='stream' property='stdAdmisiionNo'/>'>
											<bean:write name='stream' property='studentFnameVar'/></option>
										    </logic:iterate>
							                </select>
											</logic:present>
				</div>
			</div>	 --%>	
							
							
					
							
							
							
							
							<br />
							
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">From Date
								</label>
								<div class="col-xs-7">
									<input type="text" name="fromdate" id="Fromdate"
										readonly="readonly"
										class="form-control" value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="fromdate"/></logic:present>"></input>
								</div>
							</div>
							<br />
							
							
						
						<div class="form-group">
						
						<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Session(Start)
									
								</label>
								
								<div class="col-xs-7">
									<select name="starttime" id="startsessionDay" class="form-control">
										<option value="">----Select----</option>
										<option value="FH">First Half</option>
										<option value="SH">Second Half</option>
									</select>
								</div>
							</div>		
							
								<br />
							
							
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Leave
									Type
								</label>
								<div class="col-xs-7">
									<select name="leavetype" id="leavetype" class="form-control">
										<option value="">----Select----</option>
										<option value="SL">SL</option>
										<option value="PL">PL</option>
										<option value="CL">CL</option>

									</select>
								</div>
							</div>
							<br />
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Reason
								</label>
								<div class="col-xs-7">
									<textarea style="font-size: 12px;" name="reason"
										class="form-control" id="reason" >
										<logic:present name="leavedatails">
											<bean:write name="leavedatails" property="reason" />
										</logic:present>
										</textarea>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="txtstyle">



           <div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Student
									
								</label>
								<div class="col-xs-7">
								
									<logic:present name="studentlist" scope="request">
								         <select class="form-control" name="studentFname" id="parentchild">
								           <option value=""> </option>
									       <logic:iterate id="stream" name="studentlist" scope="request">
											<option value='<bean:write name="stream" property="stdAdmisiionNo"/>'>
											<bean:write name="stream" property="studentFnameVar"/></option>
										    </logic:iterate>
							                </select>
											</logic:present>
								</div>
							</div>	

<br />

							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">To Date</label>
								<div class="col-xs-7">
									<input type="text" name="todate" id="todate"
										readonly="readonly" 
										class="form-control"
										
										 value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="todate"/></logic:present>">
										</input>

								</div>
							</div>
							<br />
							
							<div class="form-group">
						
						<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Session(End)
									
								</label>
								
								<div class="col-xs-7">
									<select name="endtime" id="endsessionDay" class="form-control">
										<option value="">----Select----</option>
										<option value="FH">First Half</option>
										<option value="SH">Second Half</option>
										

									</select>
								</div>
								
									
							</div>	
							
						<%-- <div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">End Time</label>
								<div class="col-xs-7">
									<div id="datetimepicker4" class="input-append">
										<input type="text" data-format="hh:mm:ss" size="8"
											readonly="readonly" name="endtime" id="endtime"
											class="form-control"
											value='<logic:present name="leavedatails"><bean:write name="leavedatails" property="endtime"></bean:write></logic:present>' /><img
											src="./images/time1.jpg" width="30" height="30"
											class="add-on" style="margin-left: 88%; margin-top: -12%;" />
									</div>
								</div>
							</div> --%>	
								<br />		
							
							
							
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;"> Total
									Leave
								</label>
								<div class="col-xs-7">
									<input type="text" name="totalleave" id="totalleaves" readonly="readonly"
										class="form-control" value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="totalleave"/></logic:present>"></input>
								</div>
							</div>


							<br />
							
							
							
							
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">File
									Upload
								</label>
								<div class="col-xs-7">
									<input type="file" name="fileupload" id="fileupload"
										class="form-control"></input>

	<input type="button" id="document2btn" name="idproof" class="downloadDoc" value="Download" />
										<span id="downloadIdTitle"> dowmloadFile</span>


								</div>
							</div>
							
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
								
								
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	</form>
</body>

</html>
