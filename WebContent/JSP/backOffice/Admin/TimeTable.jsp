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

<script type="text/javascript" src="JS/backOffice/Admin/TimeTable.js"></script>

<style >


.ui-dialog .ui-dialog-buttonpane {
    text-align: left;
    border-width: 1px 0 0 0;
    background-image: none;
    margin: 0.5em 0 0 0;
    padding: 1.3em 5em .5em .4em;
}
.loader {
display:none;
  margin: 0 auto;
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 120px;
  height: 120px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

.ui-dialog .ui-dialog-buttonpane {
padding:0;
}
#dialog{
	font-family: Roboto,sans-serif;
    font-size: 14px;
    color: #000;

}
</style>
</head>

<body>

<div id="loader" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<!-- <p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Syllabus Details</span>
		</p> -->
		<div id="dialog" style="display: none;">Are You Sure ?</div>
		<div class="searchWrap" style="padding: 5px 5px 0">
			<div class="input-group col-md-5" id="div2">
				<p>
					<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"></bean:write></logic:present></span>
				</p>
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
					
						<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #000000"><h3 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp; 
						Modify Time Table</h3></a>
					
					

					<div class="navbar-right">

						<span id="UpdateTimeTable" class="buttons" style="display: disable;" >Save</span> 
						<span id="backpage" class="buttons">Back</span>
					</div>
<div class="loader"></div>
				</div>

     <input type="hidden" id="editoperation" 
		value="<logic:present name="editoperation" scope="request"><bean:write name="editoperation"/></logic:present>" />
				
		<input type="hidden" id="historylocId" 
		value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>" />
		
		<input type="hidden" id="historyacademicId" 
		value="<logic:present name="historyacademicId" scope="request"><bean:write name="historyacademicId"/></logic:present>" />				
		
		<input type="hidden" id="historyclass" 
		value="<logic:present name="historyclass" scope="request"><bean:write name="historyclass"/></logic:present>" />
		
		<input type="hidden" id="historysection" 
		value="<logic:present name="historysection" scope="request"><bean:write name="historysection"/></logic:present>" />
				
				<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					<div id="scrolid" style="padding: 0; overflow: scroll;">
					<div class="col-md-5" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">
					<div class="form-group clearfix">
								<label class="control-label col-xs-4" style="text-align: right;">Academic Year</label>
						<div class="col-xs-7">
								<input type="text" name="emppfno" readonly="readonly" class="form-control"
									value="<logic:present name="accyear"><bean:write name="accyear"/></logic:present>" />
							</div>
					</div>
					<div class="form-group clearfix">
								<label class="control-label col-xs-4" style="text-align: right;">Branch</label>
						<div class="col-xs-7">
								<input type="text" name="emppfno" readonly="readonly" class="form-control"
									value="<logic:present name="locationname"><bean:write name="locationname"/></logic:present>" />
							</div>
					</div>
					</div>
					
					
					
					<div class="col-md-5" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">
					<div class="form-group clearfix">
								<label class="control-label col-xs-4" style="text-align: right;">Teacher Name</label>
						<div class="col-xs-7">
								<input type="text" name="emppfno" readonly="readonly" class="form-control"
									value="<logic:present name="selected_teacher"><bean:write name="selected_teacher"/></logic:present>" />
							</div>
					</div>
					<div class="form-group clearfix">
								<label class="control-label col-xs-4" style="text-align: right;">Class - Division</label>
						<div class="col-xs-7">
								<input type="text" name="emppfno" readonly="readonly" class="form-control"
									value="<logic:present name="selected_classid"><bean:write name="selected_classid"/></logic:present> - <logic:present name="selected_sectionid"><bean:write name="selected_sectionid"/></logic:present>" />
							</div>
					</div>
					
					<input type="hidden" id="classid" value="<logic:present name="selected_classid"><bean:write name="selected_classid"/></logic:present>" />
					<input type="hidden" id="locationId" value="<logic:present name="locationId"><bean:write name="locationId"/></logic:present>" />
					<input type="hidden" id="accyearId" value="<logic:present name="accyearId"><bean:write name="accyearId"/></logic:present>" />
					<input type="hidden" id="hiddenClass" value="<logic:present name="selected_classid"><bean:write name="selected_classid"/></logic:present>" />
				
					<input type="hidden" id="hiddenClassId" value="<logic:present name="classId"><bean:write name="classId"/></logic:present>" />
					<input type="hidden" id="hiddenSecId" value="<logic:present name="sectionId"><bean:write name="sectionId"/></logic:present>" />
					<input type="hidden" id="hiddenSection" value="<logic:present name="selected_sectionid"><bean:write name="selected_sectionid"/></logic:present>" />
					<input type="hidden" id="hviewBy" value="class" />
					</div>
					
					
							<input type="hidden" value="<bean:write name="TimetableID"/>" id="hiddenId" />
							<table id="timetable"  class="table allstudent" style="width: 125%;max-width: 125%;">
							</table>

							<logic:present name="timeTableDetails" scope="request">
							<logic:iterate id="timeTableDetails" name="timeTableDetails" >
								<input type="hidden" name='<bean:write name="timeTableDetails" property="dayid" />-<bean:write name="timeTableDetails" property="periods" />' value='<bean:write name="timeTableDetails" property="teacherId" />-<bean:write name="timeTableDetails" property="subjectid" />' class='hiddenClassForUpdate'  />
							</logic:iterate>
							
							</logic:present>
					</div>


				</div>
			</div>
			
		</div>
	</div>


	<!-- </form>  -->
	
</body>

</html>
