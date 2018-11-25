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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<!-- <script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
 --><script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
 --><link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Student/NewStudentPromotion.js"></script>
<style>
.form-group{
margin-bottom: 5px;}
.save:hover {
	cursor: pointer;
}

fieldset { 
	width:512px;
    display: block;
   /*  margin-left: auto;
    margin-right: 2px; */
    margin-bottom: 5px;
    padding-bottom: 0.625em;
    padding-left: 7px;
    padding-right: 0px;
    border: 0.5px solid #ccc;
   
}
legend {
    display: inline-block;
    width: auto;
    padding: 0;
    margin-bottom: 0px;
    font-size: 16px;
    line-height: inherit;
    color: #333;
    border: 0;
   }
</style>
<style>
.buttons{

vertical-align:-5px;

}
.navbar-right {
    top: -3px;
}
.panel-primary>.panel-heading{
margin-bottom: 0px;
}
form .panel.panel-primary.panel-list{
padding-bottom: 0px;

}    

@media (min-width:320px) and (max-width:767px){
br{
display: none;
}
}

   #allstudent th, .allstudent th{
       color: #fff;
    background-color: #f5f5f5 !important;
    padding: 10px 5px;
    position: relative;
}

</style>
</head>

<body>

<input type="hidden" id="hiddenlocId" value="<logic:present name="hloc"><bean:write name="hloc" /></logic:present>">


<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p>
			<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Bulk Promotion</span>
		</p>
		<div id="dialog"><p>Are you sure to Save Bulk Promotion</p></div>
		<div id="loader"
			style="position: absolute; top: 50%; left: 45%; display: none;">
			<img src="images/loaderimage2.GIF" width="150px" height="150px" />
		</div>

			<div class="errormessagediv1" style="display: none;" align="center">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>

			<div class="errormessagediv" style="display: none;" align="center">
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

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Bulk Promotion</h3></a>
						

						 <div class="navbar-right">
		 					<span class="buttons" id="save">Save</span>
							<span class="buttons" id="back1">Back</span>
						</div> 
					</div>

<input type="hidden" id="tabstatus"
		 value="<logic:present name='tabstatus' scope='request' ><bean:write name='tabstatus'/></logic:present>">

<input type="hidden" id="promaccYear" 
  value="<logic:present name="promaccYear" scope="request"><bean:write name="promaccYear" /></logic:present>" />					

<input type="hidden" id="promlocId" 
  value="<logic:present name="promlocId" scope="request"><bean:write name="promlocId" /></logic:present>" />
  
  <input type="hidden" id="promclassname" 
  value="<logic:present name="promclassname" scope="request"><bean:write name="promclassname" /></logic:present>" />
  
  <input type="hidden" id="promsectionid" 
  value="<logic:present name="promsectionid" scope="request"><bean:write name="promsectionid" /></logic:present>" />
  
  <input type="hidden" id="prosearch" 
  value="<logic:present name="prosearch" scope="request"><bean:write name="prosearch" /></logic:present>" />
  
  
  <input type="hidden" id="demoaccYear" 
  value="<logic:present name="demoaccYear" scope="request"><bean:write name="demoaccYear" /></logic:present>" />					

<input type="hidden" id="demolocId" 
  value="<logic:present name="demolocId" scope="request"><bean:write name="demolocId" /></logic:present>" />
  
  <input type="hidden" id="democlassname" 
  value="<logic:present name="democlassname" scope="request"><bean:write name="democlassname" /></logic:present>" />
  
  <input type="hidden" id="demosectionid" 
  value="<logic:present name="demosectionid" scope="request"><bean:write name="demosectionid" /></logic:present>" />
  
  <input type="hidden" id="demosearch" 
  value="<logic:present name="demosearch" scope="request"><bean:write name="demosearch" /></logic:present>" />
  
					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Location<font color="red">*</font></label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
										
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class<font color="red">*</font></label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="">----------Select----------</option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search</label>
									<div class="col-xs-7">
										<input type="text" tabindex="1"  
											id="searchBy" maxlength="25" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix">
								<div class="col-xs-5"></div>
								<div class="col-xs-7">
								<span class="buttons" id="search" >Search</span>
								<span class="buttons" id="Reset" >Reset</span>
								</div>
								</div>
								
							</div>
							
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year<font color="red">*</font></label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division<font color="red">*</font></label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="all">----------Select----------</option>
										</select>
									</div>
								</div>
								
								
								
							</div>
					
							<div id="collapseOne" class="accordion-body collapse in" >
				<div class="panel-body" style="display: none" id="divIdVal" style="font-family:Roboto,sans-serif;font-size:13px;color:#000;">
					<table id="allstudent" class="table"></table>
					<%-- <display:table class="table" id="allstudent"
						name="requestScope.studentdetailslist" 
						requestURI="/menuslist.html?method=studentList?"
						decorator="com.centris.campus.decorator.StudentPromotionDecorator">
						<display:column title="Select">
    						  <input type="radio" name="selectBox" id="selectBox" value="${allstudent.studentId }" />      
						</display:column>
						<display:column property="count" sortable="true"
							title="Sl No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						
						<display:column property="studentAdmissionNo" sortable="true"
							title="Admission No	<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="studentrollno" sortable="true"
							title="Roll No.<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="classname" sortable="true"
							title="Class <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="sectionnaem" sortable="true"
							title="Division <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="specilizationname" sortable="true"
							title="Specilization <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column sortable="true"
							title="Status <img src='images/sort1.png' style='float: right'/>"
							media="html"><select class="status" style="color: white;font-weight: 700;"><option value="promoted" style="background-color:green;" selected>Promoted</option><option value="demoted" style="background-color:red;">Demoted</option></select></display:column>
						
						<display:column sortable="true"
							title="New Division <img src='images/sort1.png' style='float: right'/>"
							media="html"><select class="sectionlist"><option value=""></option></select></display:column>
						
						<display:column sortable="true"
							title="New Specilization <img src='images/sort1.png' style='float: right'/>"
							media="html"><select class="specilizationlist"><option value=""></option></select><span class="settingpromotion" >Click ></span></display:column>

						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
							
					</display:table>
				 --%>
				</div>
				<br />
			</div>
		</div>
	</div>
				</div>
			</div>
	</div>
	
	</body>
</html>
