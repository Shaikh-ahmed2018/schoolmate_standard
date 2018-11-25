<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<title>Campus School Stream</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/backOffice/Parents/ExamDetails.js"></script>
<link rel="stylesheet" href="CSS/Admin/CommonTable.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.buttons {
	vertical-align: 0px;
}
.result{
	background: #09860c;
    padding: 5px;
    border-radius: 5px;
    color: #fff;
}
</style>
</head>

<body>

	<div class="col-md-10" id="div-main" style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<div class="col-md-7" id="div2">
			<p style="margin: 16px 0px;">
				<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Exam Details </span>
			</p>
		</div>

		<div class="input-group col-md-5" style="margin: 20px 0px;font-size: 13px;">

			<div class="form-group">
				<label for="inputPassword" class="control-label col-xs-3" style='text-align: left; line-height: 35px; color: #000; font-size: 13px; font-family: Roboto,sans-serif;'>Student</label>
				<div class="col-xs-7">
					<select name="studentFname" id="parentchild" class="form-control">
						<logic:present name="studentlist" scope="request">
							<logic:iterate id="stream" name="studentlist" scope="request">
								<option value='<bean:write name='stream' property='studentid'/>'>
									<bean:write name='stream' property='studentFnameVar' /></option>
							</logic:iterate>
						</logic:present>
					</select>
				</div>
			</div>
		</div>

		<input type="hidden" name="hidden" class="hiddenclass" id="hiddenid" value='<logic:present name="studentexam"><bean:write name="studentexam" />
	  	</logic:present>'></input>
		<input type="hidden" name="parenthidden" id="parenthidden" value="<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="parentID"/></logic:present>" />

		<input type='hidden' id='hiddenloc' value='<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="locid"/></logic:present>'>
		<input type='hidden' id='hiddenclassid' value='<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="classDetailId"/></logic:present>'>
		<input type='hidden' id='hiddensectionid' value='<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="sectionid"/></logic:present>'>

		<div class="errormessagediv" align="left" style="display: none;">
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
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="successmessagediv" align="left" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="fail" scope="request">
			<div class="successmessagediv1">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write name="fail" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>


		<div class="panel panel-primary">
			<div class="panel-heading">
				
				<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Exam Details
					</h3></a>
			</div>
			

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

					<logic:present name="examlist" scope="request">
						<table class='table' id='allstudent'>
							<thead>
								<tr>
								<th>S.No</th>
								<th>Exam Type</th>
								<th>Exam Code</th>
								<th>Exam Name</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>Result</th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate id='examlist' name='examlist'>
									<tr>
									<td><bean:write name='examlist' property="sno"/></td>
									<td><bean:write name='examlist' property="examtypename"/></td>
									<td><bean:write name='examlist' property="examCode"/></td>
									<td><bean:write name='examlist' property="examName"/></td>
									<td><bean:write name='examlist' property="startDate"/></td>
									<td><bean:write name='examlist' property="endDate"/></td>
									<td id = '<bean:write name='examlist' property="examid"/>' class='<bean:write name='examlist' property="status"/> <bean:write name='examlist' property="examtypeprefix"/>'></td>
								</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:present>
				</div>
				<br>
			</div>
		</div>
		</div>
</body>
</html:html>