<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>
<script type="text/javascript" src="JS/backOffice/Parents/ViewStuPerformance.js"></script>
<link href="CSS/Admin/CommonTable.css" rel="stylesheet">

<style>
.result{
	background: #09860c;
    padding: 5px;
    border-radius: 5px;
    color: #fff;
}
.chartclass{
	margin: auto;
}

</style>
</head>

<body>

	<div class="col-md-10" style="font-family: Roboto,sans-serif; font-size: 20pt; color: #000; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<div style="overflow: hidden;">
		<div class="col-md-8" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Student Performance Details</span>
			</p>
		</div>
		</div>
		
		<input type="hidden" id="examid" value='<logic:present name="examid"><bean:write name="examid"></bean:write></logic:present>'>
		<input type="hidden" id="exampfx" value='<logic:present name="exampfx"><bean:write name="exampfx"></bean:write></logic:present>'>
		<input type="hidden" id="stuId" value='<logic:present name="stuId"><bean:write name="stuId"></bean:write></logic:present>'>
		<input type="hidden" id="accyear" value='<logic:present name="accyear"><bean:write name="accyear"></bean:write></logic:present>'>
		
		
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #000; vertical-align: text-top;"> 
							<h4 class="panel-title" style="vertical-align: super;"><i class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Student Performance</h4></a>
					</div>

				<div id="collapseOne" class="accordion-body collapse in">
					
					<div class="panel-body">
						<div class='row'>
							<div class='col-md-12'>
								<div class='col-md-1'></div>
								<div class='col-md-10'>
									<div class='chartclass'>
										<canvas id="bar-chart" width="450" height="200"></canvas>
									</div>
								</div>
								<div class='col-md-1'></div>
							</div>
						</div>
					</div>
					
				</div>
				
			</div>
	</div>
	</div>
</body>
</html>
