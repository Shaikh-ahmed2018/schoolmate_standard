<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<script type="text/javascript" src="JS/backOffice/Settings/SpecializationExcelUpload1.js">
</script>

</head>
<body>
  
  <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
  
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<span class="glyphicon glyphicon-user"></span>&nbsp;<span id="pageHeading">Specialization Excel upload</span>
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

	<form action="settingXLUpload.html?method=SpecializationExcelDataInsert"
			id="excelfileupload" name="SettingExcelUpload" method="post"
			enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a href="#collapsebOne" class="topPannel" style="color: #000000"><h3 class="panel-title"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Upload Specialization Details
							</h3></a>
						<%-- <div class="navbar-right">
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="UPLSPLADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
                                			<span id="saveid" class="buttons">Upload</span> 
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
						</div> --%>
					</div>
					<script>
						$(document).ready(function() {
							$('[data-toggle="tooltip"]').tooltip();
						});
					</script>
					<div class="feebody panel-group" id="accordion" role="tablist"
						aria-multiselectable="true">
						<div id="collapsebOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne" align="center">
							<div class="panel-body">
								<div class="col-md-6" id="txtstyle" style="padding: 0;">
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4"
											id="inputnames"></label>
										<div class="col-xs-7">
											<input type="file" name="formFile" id="specfile"
												class="form-control" style="height: auto;"></input>
										</div>
										<br />
									</div>
								</div>

								<div class="col-md-1" id="txtstyle">
									 <logic:present name="UserDetails" scope="session">
									   <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
										 <logic:equal value="UPLSTRADD" name="daymap" property="key">
											<logic:equal value="true" name="daymap" property="value">
								                <button id="saveid" class="buttons">Upload</button>
								            </logic:equal>
										</logic:equal>
									  </logic:iterate>
								   </logic:present>
								</div>

								<div class="col-md-3" id="txtstyle">
									<a href="settingXLUpload.html?method=SpecializationExcelFormat">Download Instructions</a>
									<br/>
									<a href="settingXLUpload.html?method=SpecializationSampleData">Download Template</a>
								
								</div>
								
								
								
					  <logic:present name="FailedSpecList" scope="request">
								<div class="col-md-12 excel-error-table">
										<p class="theading">
											<span class="displayno"></span>
										</p>
										<p style="color: #ff1010;font-family: Roboto Medium;">
											<font size="4">Following Records are not Uploaded!</font>
										</p>

							<display:table id="allstudent" name="requestScope.FailedSpecList" class="table"
											requestURI="/SettingXLUpload.html?method=SpecializationExcelDataInsert">
							<display:column property="reason" title="Reason" headerClass="heading1" style="width:400px;text-align:left"/>
							<display:column property="locationName" title="Branch" headerClass="heading1" />
							<display:column property="streamName" title="Stream Name" headerClass="heading1" />
							<display:column property="className" title="Class Name" headerClass="heading1" />
							<display:column property="specializationName" title="Specialization" headerClass="heading1" />
							</display:table>
						</div>
									<div class="col-md-12" style="top:12px;">
										<div class='pagebanner' style="left:-2px;"><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
											<span  class='numberOfItem'></span>	
										</div>
										<div class='pagination pagelinks' style="right:-11px;"></div>
									</div>
								</logic:present> 
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
</body>
</html>
