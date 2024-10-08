<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>

<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
 --><link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />



<link href="CSS/newUI/custome.css" rel="stylesheet">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Teacher/Project.js"></script>


<style >

.hiddenclass,.hhhiddenclass{
display: none;
}
.control-label{
text-align: right; font-family: sans-serif; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;
}
</style>

</head>

<body>
	<div class="col-md-10"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Modify Project</span>
		</p>
		
		<div class="errormessagediv" align="center" style="display: none;">
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

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
				
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #000000">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Update Project
							</h3></a>
				
					
					<div class="navbar-right">
						<span class="buttons" id="saveid">Save</span> 
						<span id="back" class="buttons" >Back</span>
					</div>
				</div>

			<div class="feebody panel-group" id="accordion" role="tablist" aria-multiselectable="true">

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne" align="center">
						<div class="panel-body">
						
						<input type="hidden" id="projectId" value="<logic:present name="ProjectDetailsVo"><bean:write name="ProjectDetailsVo" property="projectId"/></logic:present>"/>

							
							<div class="col-md-6" id="txtstyle">

								<div class="form-group clearfix">
									<label class="control-label col-xs-6">Class &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Roboto,sans-serif" id="addmissionNo"><logic:present name="ProjectDetailsVo"><bean:write name="ProjectDetailsVo" property="classname"/></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label class="control-label col-xs-6">Project Name
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Roboto,sans-serif"><logic:present name="ProjectDetailsVo"><bean:write name="ProjectDetailsVo" property="projectname"/></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label class="control-label col-xs-6">Assignment Date
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Roboto,sans-serif"><logic:present name="ProjectDetailsVo"><bean:write name="ProjectDetailsVo" property="startdate"/></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label class="control-label col-xs-6">Completion Date
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Roboto,sans-serif" id="concessionPercent"><logic:present name="ProjectDetailsVo"><bean:write name="ProjectDetailsVo" property="enddate"/></logic:present>
										</label>
									</div>
								</div>
														
								</div>

							<div class="col-md-6" id="txtstyle">

								<div class="form-group clearfix">
									<label class="control-label col-xs-6">Section
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Roboto,sans-serif"><logic:present name="ProjectDetailsVo"><bean:write name="ProjectDetailsVo" property="sectionname"/></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label class="control-label col-xs-6">Max Marks
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12" id="maxmarks"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Roboto,sans-serif"><logic:present name="ProjectDetailsVo"><bean:write name="ProjectDetailsVo" property="maxmarks"/></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label class="control-label col-xs-6">Specialization
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Roboto,sans-serif"><logic:present name="ProjectDetailsVo"><bean:write name="ProjectDetailsVo" property="specialization"/></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label class="control-label col-xs-6">Subject
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Roboto,sans-serif"><logic:present name="ProjectDetailsVo"><bean:write name="ProjectDetailsVo" property="subjectname"/></logic:present>
										</label>
									</div>
								</div>
							</div>
						
						<display:table class="table allstudent" id="projectList" style="font-family: Roboto,sans-serif; color: #897676;border-top: 2px solid #ddd;"
						name="requestScope.ProjectDetailsList" 
						requestURI="/teachermenuaction.html?method=projectAssign">
						
						 
						<display:column property="studentid" class="hiddenclass" headerClass="hhhiddenclass"></display:column>
						
						<display:column property="sno" sortable="true"
							title="Sno  <img src='images/sort1.png' style='float: right'/>"
							media="html">
									
						</display:column>
						
						<display:column property="admissionNo" sortable="true" 
							title="Admission Number  <img src='images/sort1.png' style='float: right'/>"
							media="html">
						</display:column>

						<display:column property="studentname" sortable="true"
							title="Student Name <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column  sortable="true"
							title="Actual Completion Date  <img src='images/sort1.png' style='float: right'/>"
							media="html">
							<input type='text' name='actualcompleteDate'
									value='${projectList.actualcompletedate}' id='${projectList.sno}date'
									style="width: 95% !important;height: 30px" class="form-control actualcompleteDate" />
							
							
							</display:column>

						<display:column  sortable="true"
							title="Acquired Marks <img src='images/sort1.png' style='float: right'/>"
							media="html">
							
							<input type='text' name='acquiredmarks'
									value='${projectList.acquiredmarks}'
									id="${projectList.projectId}--${projectList.studentid}acquiredmarks" 
									 class="form-control acquiredmarks" />
							
						</display:column>

						<display:column sortable="true"
							title="Remarks <img src='images/sort1.png' style='float: right'/>"
							media="html">
							
							<textarea rows="1" cols="40" style="resize:none" class="form-control remarks" id="${projectList.projectId}--${projectList.studentid}remarks">${projectList.remarks}</textarea>
							
						</display:column>
					</display:table>
					
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
</div>
</body>

</html>
