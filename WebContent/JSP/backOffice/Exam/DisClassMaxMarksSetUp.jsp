<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">
<head>
   <script type="text/javascript" src="JS/backOffice/Exam/ClassDisMaxMarks.js"></script>
</head>


<style>

.Not{
background-color:#FF0000;  
min-width:80px !important;
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
	<form method="POST" action="examCreationPath.html?method=subMaxMarksSetUp" id="myform">
		<input type="hidden" name="myValue" value="" id="myValue"/>
	</form>	
	
	<form method="POST" action="examCreationPath.html?method=termBasedSetUp" id="termform">
		<input type="hidden" name="termValue" value="" id="termValue"/>
	</form>	
	
	<form method="POST" action="examCreationPath.html?method=getMaxMarksSetUp" id="backform">
		<input type="hidden" name="myValue" value="" id="backValue"/>
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
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		
	<div class="panel panel-primary">
		<div class="panel-heading clearfix">
			<a data-toggle="collapse" data-parent="#accordion1"
				href="#" style="color: #fff;"><h3 class="panel-title grade" style="color: #000000; line-height: 18px;">
					<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Class List
			</h3></a>
			<div class="navbar-right">
				<span class="buttons" id="back1">Back</span>
			</div>	
		</div>

		<div id="gradeOne" class="accordion-body collapse in">
			<div class="panel-body">
				<div class="clearfix" id="inputcolor">
					<div class="col-md-6" id="txtstyle"
						style="font-size: 13px; color: #000;">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Branch</label>
							<div class="col-xs-5">
								<input type="text" readonly="readonly" name="location" onkeypress="HideError()" id="location" class="form-control" style="text-align: left;"
									value='<logic:present name="location" scope="request" ><bean:write name="location" scope="request"></bean:write></logic:present>' />
							</div>
							<input type="hidden" id="hiddenaccyear"
								value='<logic:present name="accyearid"  scope="request"><bean:write name="accyearid" /></logic:present>' />
							<input type="hidden" id="hiddenloc"
								value='<logic:present name="locid"  scope="request"><bean:write name="locid" /></logic:present>' />
						
						</div>
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Class</label>
							<div class="col-xs-5">
								<select id="mainclass" name="mainclass" class="form-control">
									<option value="all">ALL</option>
								</select>
							</div>
						</div>
					</div>
					
					<div class="col-md-6" id="txtstyle" style="font-size: 13px; color: #000;">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Academic Year</label>
							<div class="col-xs-5">
								<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear" class="form-control" style="text-align: left;"
									value='<logic:present name="accyName" scope="request" ><bean:write name="accyName" scope="request"></bean:write></logic:present>' />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
			<div class="col-md-6">
			<div class="panel panel-primary ">
				<div class="panel-heading clearfix">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#" style="color: #fff;"><h3 class="panel-title marksetup" style="color: #000000; line-height: 18px;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Maximum Marks Setup
						</h3></a>
				</div>
				<div id="marksetup" class="accordion-body collapse collapse in">
					<div class="panel-body">
						<div id="markstable"></div>
					</div>
				</div>
			</div>
			</div>
			<div class="col-md-6">
			<div class="panel panel-primary collapse in">
				<div class="panel-heading clearfix">
					<a data-toggle="collapse" data-parent="#accordion3"
						href="#" style="color: #fff;"><h3 class="panel-title typesetup" style="color: #000000; line-height: 18px;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Conversion Mark Setup Based on Maximum Marks
						</h3></a>
				</div>
				<div id="typesetup" class="accordion-body collapse collapse in">
					<div class="panel-body">
						<div id="reporttable"></div>
					</div>
				</div>
			</div>
			</div>
			</div>
			</div>
			
				<!-- <div id="markstable">
					<table class='table' id='allstudent'>
						<thead>
						<tr>
						<th>Sl.No</th>
						<th>Class</th>
						<th>Status</th>
						</tr>
						</thead>
						<tbody>
							<tr>
								<td>1.</td>
								<td>Pre-Kg</td>
								<td><span class='Not Set'>Not Set</span></td>
							</tr>
						</tbody>
					</table>
				</div> -->
		</div>
	</div>
	</div>
</body>
</html>
