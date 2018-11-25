<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">
<head>
   <script type="text/javascript" src="JS/backOffice/Exam/DisMaxMarks.js"></script>
</head>


<style>

.Not{
background-color:#FF0000;  
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
.Set{
background-color:green;
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}

</style>


<body>
	<form method="POST" action="examCreationPath.html?method=classMaxMarksSetUp" id="myform">
		<input type="hidden" name="myValue" value="" id="myValue"/>
	</form>	
	<div id="dialog"></div>

	<div class="content" id="div1">
		<div class="" id="div2">
				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Report Card Setup</span>
				</p>
			</div>
	
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
			</div>
		</div>
			
		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#" style="color: #fff;"><h3 class="panel-title grade" style="color: #000000; line-height: 18px;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Report Card Setup
					</h3></a>
			
			</div>
		
			<div id="gradeOne" class="accordion-body collapse in">
				<div class="panel-body">
					<div class="col-md-12" id="inputcolor">
						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()" tabindex="1" name="location" id="location" >
										<logic:present name="locationDetailsList" scope="request">
										<logic:iterate id="locationDetailsList" name="locationDetailsList">
										<option value='<bean:write name="locationDetailsList" property="locationId"/>'><bean:write name="locationDetailsList" property="locationName"/></option>
										</logic:iterate>
										</logic:present>
									</select>
								</div>
								<input type="hidden" name="curr_loc" id="curr_loc" value='<bean:write name="curr_loc"/>'/>
								<input type="hidden" name="curr_accy" id="curr_accy" value='<bean:write name="curr_accy"/>'/>
							</div>
						</div>
						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Academic Year</label>
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
				<div id="markstable"></div>
			</div>
		</div>
	</div>
</body>
</html>
