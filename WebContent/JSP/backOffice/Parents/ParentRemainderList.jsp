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
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/backOffice/Teacher/TeacherMeetingAndEvent.js"></script>
<link rel="stylesheet" href="CSS/Admin/CommonTable.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#allstudent th:nth-child(1){
width: 20%;
text-align: left;
}
#allstudent td:nth-child(1){
text-align: left;
}
</style>
</head>

<body>

	<div class="col-md-10" id='div1'>
		
			<div class="col-md-7" id='div2'>
				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Circular & Remainder</span>
				</p>
			</div>
		
		<input type="hidden" name="hidden" class="hiddenclass" id="hiddenid"
			value='<logic:present name="studentmeeting"><bean:write name="studentmeeting" />
													</logic:present>'></input>

		<input type="hidden" name="parenthidden" id="parenthidden"
			value="<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" /></logic:present>" />
		<div class='row'>
		<div class='col-md-12'>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Circular & Remainder
					</h3></a>
			</div>
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
				<display:table class="meeting table" name="requestScope.remainderlist" length="0"  requestURI="parentMenu.html?method=circularRemainderParentAction"
				pagesize="10"  id="allstudent">
				<display:column property="name" sortable="true" title="Remainder-Title <img src='images/sort1.png' style='float: right'/>"></display:column>
				<display:column property="remtype" sortable="true" title="Remainder-To<img src='images/sort1.png' style='float: right'/>"></display:column>
				<display:column property="description" sortable="true" title="Description<img src='images/sort1.png' style='float: right'/>"></display:column>
				<display:setProperty name="paging.banner.placement" value="bottom" />
				</display:table>
				</div>
				<br>
			</div>
		</div>
		</div>
		</div>
		</div>
</body>
</html:html>