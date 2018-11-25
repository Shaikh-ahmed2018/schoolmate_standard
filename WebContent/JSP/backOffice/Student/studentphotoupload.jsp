<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>
<link href="CSS/newUI/custome.css" rel="stylesheet">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Student/StudentPhotoupload.js"></script>
<style>
.note {
	color: #f00;
	font-size: 12px;
}
#div1 .panel-body, #div-main .panel-body {
    padding-top: 16px;
}

</style>


</head>

<body>

<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>

	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Upload Students Photo</span>
		</p>



		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<logic:present name="errorMessage" scope="request">
			<div class="errormessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="errorMessage" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>


		<form action="studentRegistration.html?method=saveStudentPhotoUpload"
			id="excelfileupload" name="StudentRegistrationForm" method="post"
			enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion1"
								href="#collapseOne" style="color: #000000 !important"><h3 class="panel-title"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Upload Students Photo
							</h3></a>
						
						
					</div>
					<script>
						$(document).ready(function() {
							$('[data-toggle="tooltip"]').tooltip();
						});
					</script>
                         
                    <input type="hidden" id="hiddenstatus" value='<logic:present name="status"><bean:write name="status" /></logic:present>' /> 
                    <input type="hidden" id="hiddencount" value='<logic:present name="count"><bean:write name="count" /></logic:present>' />    

					<div class="feebody panel-group" id="accordion" role="tablist"
						aria-multiselectable="true">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne" align="center">
							<div class="panel-body">
								 <div class="col-md-6" id="txtstyle" style="padding: 0;">
									<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
											id="inputnames"></label>
										<div class="col-xs-7">
											<input type="file" name="photoUpload" id="studentfile" multiple="multiple" onclick="HideError(this)"
												class="form-control" style="height: auto;"></input>
									  </div>
								  </div>
								</div> 
								
								<div class="col-md-1" id="txtstyle">
								 <logic:present name="UserDetails" scope="session">
									<logic:iterate id="daymap" name="UserDetails"
										property="permissionmap" scope="session">
										<logic:equal value="STUPHUPADD" name="daymap" property="key">
											<logic:equal value="true" name="daymap" property="value">
												  <button id="saveid" class="buttons">Upload</button>
											</logic:equal>
										</logic:equal>
									</logic:iterate>
								</logic:present>
							  </div>
							  
									   <div class="col-md-5">
										 <div class="note">Note:</div>
									     <div class="note">1 - Student Photo accepts only .jpg  or .png format.</div>
									     <div class="note">2 - Image names must match with admission No.</div>
									    </div>
						            
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		</div>
		
</body>

</html>
