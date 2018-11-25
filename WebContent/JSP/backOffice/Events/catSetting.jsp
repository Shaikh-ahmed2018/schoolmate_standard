<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>

<script type="text/javascript" src="JS/common.js"></script>


<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
 -->
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="JS/backOffice/Events/categSetting.js"></script> 
<script>
function ShowTable(){
	$("#collapseOne").toggleClass("in");
}
</script>

<style>
.glyphicon:hover {
	cursor: pointer;
}
.pagebanner {
    margin-top: 13px;
}
.pagination {
    margin-right: 10px;
    float: right;
    margin-top: 10px;
}
.buttons {
	top: 10px !important;
}
.controls.style {
	margin-top: -32px;
}
.searchWrap {
	padding: 6px;
}
 #allstudent  th{
position: relative;
min-width:25px !important;
}

.glyphicon-plus {
    font-size: 20px;
    line-height: 34px;
    color: #989898;
    padding: 2px 12px;
    margin-top: -26px;
    height: 0px;
    position: relative;
    
}
.glyphicon-trash {
    font-size: 20px;
    line-height: 27px;
    color: #989898;
    padding: 0px 11px;
    margin-top: 0px;
    height: 20px;
    right: 0px;
    position: relative;
    }
.multiple{
    height: 163px !important;
}
 .glyphicon-plus:before {
	content: "\2b";
	margin-right: 3px;
}
#allstudent thead tr{
border:1px solid #dedede;
}
#allstudent thead tr,#allstudent thead th:nth-child(5){
border:none;
}
#allstudent tbody td{
border:1px solid #dedede
}
#allstudent tbody,#allstudent tbody td:nth-child(5){
border:none;
}
.errormessagediv,.successmessagediv{
margin:14px;
margin-left:200px !important;
}
.successmessagediv{
margin:18px;
margin-left:200px !important;
}
#allstudent tbody tr{
border:1px solid #dedede !imporatnt;
}
#allstudent tbody tr,#allstudent tbody tr td:nth-child(5){
border:none !important;
}
#allstudent tr:nth-child(n) td:last-child, .allstudent tr:nth-child(n) td:last-child, #allstudents tr:nth-child(n) td:last-child {
    background-color: transparent;
    border: 1px solid #dedede;
}
table#allstudent thead tr {
    background-color: #f5f5f5 !important;
}
</style>
</head>
<body>
	<div class="content" id="div1">
		<div id="dialog"></div>
		<div id="deleteDialog"></div>
		<div class="searchWrap">
			<div id="div2"><p><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Category Setting</span></p>
			</div>
		</div>
		
		
		
<!-- Pop up starts -->
			<div id="eventCatSetting" style="display: none">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
						</div>
					</div>
				</div>
					<div class="col-md-12" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
						<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right;">Event Name</label>
							<div class="col-xs-7">
							<input type="hidden"  id="eventIdHidden" value=""/>
					<select id="eventName" name="eventName" class="form-control"> 
							<option value="">----Select----</option>
								<logic:present name="eventList">
									<logic:iterate id="name" name="eventList">
										<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
									</logic:iterate>
								</logic:present>
							</select> 
							</div>
						</div>
			
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-4" style="text-align: right;"> Category Name</label>
						<div class="col-xs-7">
							<input type="text"  name="categoryName" id="categoryName" class="form-control"/>
							<input type="hidden"  name="categoryHidden" id="categoryHidden" value="" />
							<input type="hidden"  name="categoryNameHidden" id="categoryNameHidden" value="" />
						</div>
					</div> 
					
					<div class="form-group clearfix ">
						<label for="inputPassword" class="control-label col-xs-4"
							style="text-align: right;">Participate Class</label>
							<div class="col-xs-7">
							<select id="classList" name="classList" class="form-control multiple"  multiple>
									<logic:present name=" ">
										<logic:iterate id="classList" name="classList">
											<option value="<bean:write name="classList" property="classId"/>"><bean:write name="classList" property="className"/></option>
										</logic:iterate>
									</logic:present>
							</select>
							</div>
					</div>
				</div>
</div>
</div>
<!--Pop Up Ends  -->		
		
		
		
		<div class="errormessagediv"  align="center" style="display: none;">
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

		<div class="panel panel-primary clearfix">
			<div class="panel-heading clearfix">
				<a  href="javascript:ShowTable();" style="color: #fff;"><h3 class="panel-title class" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Category Setting </h3></a></div>

			<div id="classOne" class="accordion-body collapse in">
				<div class="panel-body">

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5" align="right" id="inputnames" style="text-align: right; line-height: 35px;">Event Name </label>
							<div class="col-xs-7">
							<select id="eventNameList" name="eventNameList" class="form-control"> 
								<option value="">----------Select---------</option>
								<logic:present name="eventList">
									<logic:iterate id="name" name="eventList">
										<option value='<bean:write name="name" property="eventId"/>'><bean:write name="name" property="eventName"/></option>
									</logic:iterate>
								</logic:present>
							</select>
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames" style="text-align: right; line-height: 35px;">Category Name</label>
							<div class="col-xs-7">
								<select name="categoryNameList" id="categoryNameList" class="form-control">
								<option value="">----------Select---------</option>
									<logic:present name="categoryName">
										<logic:iterate id="name" name="categoryName">
											<option value='<bean:write name="name" property="categoryId"/>'><bean:write name="name" property="categoryName"/>
											</option>
										</logic:iterate>
								</logic:present> 
							</select>
							
							</div>
							
							</div>
						</div>

					</div>
				</div>

		<div id="collapseOne" class="panel-collapse collapse in ">
						<table style="width: 100%;" class='table' id='allstudent'>
						<thead>	<tr>
								<th>Sl.No.</th>
								<th>Event Name</th>
								<th>Category Name</th>
								<th>Standard</th>
								<th><span id="addgroup" class="glyphicon glyphicon-plus"></span></th>
							</tr>
						</thead>
						<tbody>
						
						</tbody>
						</table>
<div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	
					</div>
					<div class='pagination pagelinks'></div>
					</div>
				</div>
			</div>

		</div>
</body>
</html>