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

<script type="text/javascript" src="JS/backOffice/Parents/StudentTimeTable.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#editAssId:hover {
	cursor: pointer;
}
.glyphicon-user{
	top: 2px !important;
}
</style>
</head>

<body>
 
	<div class="col-md-10" style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">

		<div class="col-md-7" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Time Table</span>
			</p>
		</div>

		<div class="input-group col-md-5" style="margin: 20px 0px;">
			<div class="form-group">
				<label for="inputPassword" class="control-label col-xs-3" style="text-align: left; line-height: 35px; color: #000; font-size: 13px; font-family: Roboto,sans-serif;">Student</label>
				<div class="col-xs-7">
						<select class="form-control" name="stuid" id="timetableid">
							<logic:present name="studentlist" scope="request">
							<logic:iterate id="stuid" name="studentlist" scope="request">
								<option value='<bean:write name='stuid' property='studentid'/>'>
									<bean:write name='stuid' property='studentFnameVar' /></option>
							</logic:iterate>
							</logic:present>
						</select>
				</div>
			</div>
		</div>
		
		<input type="hidden"  id="hiddenclass" value='<logic:present name="classid"><bean:write name="classid" /></logic:present>'></input>
		<input type="hidden"  id="hiddenloc" value="<logic:present name="locid" ><bean:write name="locid"/></logic:present>" />
		<input type="hidden"  id="hiddensec" value="<logic:present name="secid" ><bean:write name="secid" /></logic:present>" />
		<input type="hidden"  id="parenthidden" value="<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" /></logic:present>" />
		
		<div class="errormessagediv" align="left" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
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
				<a href="#" class="msg-success bg-msg-succes"><span class="validateTips"></span></a>
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
				<h3 class="panel-title" style="color: #000000;">
					<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Time Table
					</a>
				</h3>
				<div class="navbar-right">
					<!--  <span id="editAssId" class="glyphicon glyphicon-pencil2"   data-toggle="tooltip" data-placement="bottom" title="Edit"></span>
				 -->
				</div>
			</div>

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<div id="mytable" style="margin-top : 10px;"></div>
					<br>
				</div>
			</div>
	</div>
	</div>
</body>
</html:html>