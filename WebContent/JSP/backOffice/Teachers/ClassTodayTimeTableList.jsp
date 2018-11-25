<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>

<script type="text/javascript" src="JS/backOffice/Teacher/TimeTableSubstitution.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#allstudent tbody tr").click(function(){
		window.location.href="TimeTableActionPath.html?method=gettodaytimetableDetail&timetableId="+$(this).find(".hrowId").text();
		});
});

</script>

 <!-- <script type="text/javascript" src="JS/backOffice/Parents/StudentTimeTable.js"></script>  -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
#absentieeTeacher thead tr{
height:20px;
border: 1px solid #c1c1c1;
}
.pagebanner,.pagelinks{
		display:none;
		}
#editAssId:hover {
	cursor: pointer;
}

.downloadlast:hover{

cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
.sub{
    font-family: Roboto,sans-serif;
    font-size: 13px;
    color: #000;
}
</style>
<style>
.buttons{

vertical-align:-28px;

}
</style>


</head>

<body>

	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">



		<div class="" id="div2">
			<p style="margin: 10px 0px 0;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Time Table Substitution</span>
			</p>
		</div>

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
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
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
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="fail" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title sub" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Class List
					</h3></a>
			</div>
			<!-- pop up -->
     <input	type="hidden" name="asshidden" id="assgnmentid" value=""/>			
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
				<div class="col-md-12" id="scrolid" style="padding: 0; overflow: scroll;">
						<logic:present name="ClassTodayTimeTableList" scope="request">
						<display:table id="allstudent" name="ClassTodayTimeTableList" class="table"
							requestURI="/teachermenuaction.html?method=ClassTodayTimeTableList"
				            export="false" pagesize="100">
							<display:column property="timetableId" title="TimetableId" class="hidden hrowId"
								headerClass="hidden" />
							<display:column property="classname" title="Class" />
							<display:column property="sectionname" title="Division" />
							<display:column property="count" title="Vacant Period" />
						</display:table>
					</logic:present> 
			     </div>
				<br>
			</div>
		</div>
		</div>
		<div class="panel panel-primary" style="margin-top: 31px;">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion3"
					href="#collapseTwo" style="color: #fff;"><h3
						class="panel-title sub">
						<span class="glyphicon glyphicon-menu-hamburger"></span> <logic:present name="heading" scope="request"><bean:write name="heading" /></logic:present>
						
					</h3></a>
					
					<div class="navbar-right"> <logic:present name="notSubstituted" scope="request"><span class="buttons" id="substitute">Substitute</span></logic:present>
					<logic:present name="Substituted" scope="request"><span class="buttons" id="print">Print</span></logic:present>
					</div>
			</div>
			<!-- pop up -->
			<div id="collapseTwo" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
			<div class="col-md-12" id="teacherscrolid" style="padding: 0; overflow: scroll;position:relative">
					<div class="hideme" style="display: none;"><logic:present name="heading" scope="request"><bean:write name="heading" /></logic:present></div>
						<logic:present name="teacherSustituteLis" scope="request">
					
						<display:table id="absentieeTeacher" name="teacherSustituteLis" class="table allstudent"
							requestURI="/teachermenuaction.html?method=ClassTodayTimeTableList"
				            export="false" pagesize="100">
						<display:column   class="hidden hrowId"
								headerClass="hidden" />
							<display:column property="teachername" title="Teacher Name"  />
								
							<display:column property="classname" title="Class" />
							<display:column property="period1" title='<logic:present name="periodT" scope="request"><bean:write name="periodT" /></logic:present> Period' />
							<display:column   class="hideme" title="Signature"
								headerClass="hideme" />
						</display:table>
					</logic:present> 
			     </div>
				<br>
			</div>
		</div>
		</div>
		<textarea id="printing-css" style="display:none">
		#absentieeTeacher{
		border:1px solid #000;
		}
		#absentieeTeacher tr{display:table-row; }
		#absentieeTeacher th,#absentieeTeacher td{
		border:1px solid #000;
		}
		.pagebanner,.pagelinks{
		display:none;
		}
		.hideme{
		display:block;
		color:#098uyt;
		font-size:18px;
		}
		#absentieeTeacher .hideme{
		display:table-cell;
		}
		.hrowId{display:none;}
		body {-webkit-print-color-adjust: exact;margin:0;padding:0;}
		</textarea>
		</div>
</body>
</html:html>