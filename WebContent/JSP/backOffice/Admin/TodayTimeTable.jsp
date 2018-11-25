<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/backOffice/Admin/TodayTimeTable.js"></script>

<style >

.subject{

width: 150px !important;
height: 30px !important;

}

.days{

	background: #f9f9f9 !important;
    border-bottom: 1px solid #ddd;
    border-top: 1px solid #ddd;
    font-family: Roboto Medium !important;
    font-size: 13px !important;
    color: #000000 !important;
}
.aligntext{
text-align: center;
}

#allstudent th {
    color: #000000 !important;
    text-align: center;
}

#allstudent td {
    color: #000000 !important;
}
.table{
margin-top: 20px;
}
</style>

<style>
.buttons{

vertical-align: 0px;

}

.ui-dialog .ui-dialog-buttonpane {
    text-align: left;
    border-width: 1px 0 0 0;
    background-image: none;
    margin: 0.5em 0 0 0;
    padding: 1.3em 5em .5em .4em;
}
#allstudent tr:nth-child(n), .allstudent tr:nth-child(n), #allstudents tr:nth-child(n) {
background-color: #b4b4bb;
}
</style>
</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<!-- <p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Syllabus Details</span>
		</p> -->
		<div id="dialog"></div>
		<div class="searchWrap" style="padding: 10px 5px 0;">
			<div class="col-md-5" id="div2">
				<p>
					<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"></bean:write></logic:present></span>
				</p>
			</div>

			<div class="input-group col-md-7">
					
				<label class="hedderstyle form-control"
					style="bottom: 5px;text-align:right;width: 11% !important; border: none; font-family: Roboto Medium; font-size: 14px; font-weight: lighter; margin-left: 0% !important;background-color:transparent">Class</label>

				<input type="text" name="emppfno" readonly="readonly"
					style="width: 11%;" id="classid" maxlength="25"
					class="form-control"
					value="<logic:present name="selected_classid"><bean:write name="selected_classid"/></logic:present>" />

				<label
					style="bottom: 5px;width: 10% !important; border: none; font-family: Roboto Medium; font-size: 14px; font-weight: lighter;background-color:transparent"
					class="hedderstyle form-control">Division</label> 
					
				<input type="text"
					name="section" style="width: 10%;" id="section" maxlength="25"
					class="form-control" readonly="readonly"
					value="<logic:present name="selected_sectionid"><bean:write name="selected_sectionid"/></logic:present>" />
				<input type="hidden" id="timetableId"
					value="<logic:present name="timetableId"><bean:write name="timetableId"/></logic:present>" />
				<input type="hidden" id="hiddenClass"
					value="<logic:present name="selected_classid"><bean:write name="selected_classid"/></logic:present>" />
				
				<input type="hidden" id="hiddenClassId"
					value="<logic:present name="classId"><bean:write name="classId"/></logic:present>" />
					
				<input type="hidden" id="hiddenSection"
					value="<logic:present name="selected_sectionid"><bean:write name="selected_sectionid"/></logic:present>" />
				<input type="hidden" id="hviewBy" value="class" />
			</div>
		</div>


		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="successmessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>

		<!-- <form method="post"> -->
		<div class="col-md-12" style="padding: 0;">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #000000"><h3 class="hedderstyle panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp; 
						Time Table Details</h3></a>
					
					

					<div class="navbar-right">

						<span id="UpdateTimeTable" class="buttons" style="display: disable;" >Save</span> 
						<span id="back" class="buttons">Back</span>
					</div>

				</div>
				<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					<div id="scrolid" style="padding: 0; overflow: scroll;">
						<logic:present name="timeTableDetails" scope="request">
							
							<display:table id="allstudent" name="timeTableDetails" class="table"
								requestURI="/TimeTableActionPath.html?parameter=gettodaytimetableDetail"
								decorator="com.centris.campus.decorator.TimeTableDecorator"	export="false">

								<display:column property="dayid" title="Day" class="hidden"	headerClass="hidden" />

								<display:column property="dayname" title="Day" class="Day"/>
								<display:column property="period1" title="Period 1" class="aligntext"/>
								<display:column property="period2" title="Period 2" class="aligntext"/>
								<display:column property="period3" title="Period 3" class="aligntext"/>
								<display:column >SHORT BREAK</display:column>
								<display:column property="period4" title="Period 4" class="aligntext"/>
								<display:column property="period5" title="Period 5" class="aligntext"/>
								<display:column >LUNCH BREAK</display:column>
								<display:column property="period6" title="Period 6" class="aligntext"/>
								<display:column property="period7" title="Period 7" class="aligntext"/>
								<display:column >SHORT BREAK</display:column>
								<display:column property="period8" title="Period 8" class="aligntext"/>
								<display:column property="period9" title="Period 9" class="aligntext"/>

							</display:table>

						</logic:present>

					</div>


				</div>
			</div>
			<br />
		</div>
	</div>


	<!-- </form>  -->
	
</body>

</html>
