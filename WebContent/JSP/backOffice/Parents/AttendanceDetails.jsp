<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<title>Campus School Stream</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link rel="stylesheet" href="CSS/Admin/CommonTable.css" />
<script type="text/javascript" src="JS/backOffice/Parents/AttendanceDetails.js"></script>
<style>
.buttons{

vertical-align: 0px;

}
.navbar-right {
   margin: -18px -14px;
}
</style>
</head>

<body>

	<div class="col-md-10" id="div-main" style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">

		<div class="col-md-7" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Attendance Details</span>
			</p>
		</div>
			
			
			<div class="input-group col-md-5" style="margin: 20px 0px;">

			<div class="form-group">
				<label for="inputPassword" class="control-label col-xs-3" style="text-align: left; line-height: 35px; color: #000; font-size: 13px; font-family: Roboto,sans-serif;">Student</label>
			<div class="col-xs-7">
					<select class="form-control" name="studSectionId" id="studAttnId">
					<logic:present name="studentlist" scope="request">
						<logic:iterate id="stream" name="studentlist" scope="request">
							<option value='<bean:write name='stream' property='studentid'/>'>
								<bean:write name='stream' property='studentFnameVar'/></option>
						</logic:iterate>
						</logic:present>
					 </select>
			</div>
			</div>
		</div>
			
	
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="fail" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="fail" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Attendance
						Details
					</h3></a>
				
			
			<div class="navbar-right">
				 <span id="viewAttendanceId" class="buttons" style="top:25px;margin-right:13px;">View</span>
			</div>
			</div>
			
			<input	type="hidden" name="studenthidden" id="studentid" value='<logic:present name="attnstudentid"><bean:write name="attnstudentid" /></logic:present>'></input>	
		 	<input	type="hidden" name="attnhidden" id="monthid" value=""/>		
		 	<input	type="hidden" name="attnhidden1" id="currentyearid" value=""/>		
			<input	type="hidden" name="parenthidden" id="parenthidden" value="<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="parentID"/></logic:present>"/>		
			<input	type="hidden" name="studenthidden" id="studentid1" value='<logic:present name="hiddenstudentid"><bean:write name="hiddenstudentid" /></logic:present>'></input>			
			
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
						
					<table id='allstudent' class='table'>
						<thead>
						<tr>
							<th>Select</th>
							<th>Month</th>
							<th>TotalDays</th>
							<th>Total WorkingDays</th>
							<th>Total PresentDays</th>
							<th>Total AbsentDays</th>
							<th>Total Leaves</th>
							<th>Total Holidays</th>
						</tr>
						</thead>
						<tbody>
							<logic:present name="attendancelist" scope="request">
								<logic:iterate id="attendancelist" name="attendancelist">
									<tr>
										<td><input type="radio" name = 'record' class="select" onClick='getvaldetails(this)' value="<bean:write name='attendancelist' property="month"/>,<bean:write name='attendancelist' property="currentyear"/>"></td>
										<td><bean:write name='attendancelist' property="month"/></td>
										<td><bean:write name='attendancelist' property="totaldays"/></td>
										<td><bean:write name='attendancelist' property="tot_count"/></td>
										<td><bean:write name='attendancelist' property="present_count"/></td>
										<td><bean:write name='attendancelist' property="absent_count"/></td>
										<td><bean:write name='attendancelist' property="leave_count"/></td>
										<td><bean:write name='attendancelist' property="holiday_count"/></td>
									</tr>
								</logic:iterate>
							</logic:present>
						</tbody>
					</table>	
				</div>
				<br />
			</div>
		</div>
</body>
</html:html>