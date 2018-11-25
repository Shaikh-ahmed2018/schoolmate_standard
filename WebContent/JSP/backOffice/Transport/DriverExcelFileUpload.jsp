<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<script type="text/javascript"
	src="JS/backOffice/Transport/DriverExcelFileUpload.js"></script>

<style>
#allstudent th:nth-child(1) {
    text-align: center;
    width: 28%;
}
</style>

</head>

<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="col-md-10" style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p class="transportheader">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Upload Driver Excel Data File</span>
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
			<div class="errormessagediv" align="center" style="width: 1200px;">
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


		<form action="uploadDriverXSL.html?method=insertDriverXSL"
			id="excelfileupload" name="UploadDriverXSLForm" method="post"
			enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #000000;vertical-align: text;"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Upload Driver Excel Data File
							</h3></a>
						
						<!-- <div class="navbar-right">
							<span id="saveid" class="buttons"  data-toggle="tooltip"
								data-placement="bottom">Upload
							</Span>
						</div> -->
					</div>
					<script>
						$(document).ready(function() {
							$('[data-toggle="tooltip"]').tooltip();
						});
					</script>


					<div class="feebody panel-group" id="accordion" role="tablist"
						aria-multiselectable="true">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne" align="center">
							<div class="panel-body" style="margin-top: 20px;">
								<div class="col-md-6" id="txtstyle">
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4" style="text-align: right;"
											id="inputnames"></label>
										<div class="col-xs-7">
											<input type="file" name="formFile" id="driverFile" onchange="checkFileSize(this)";
												class="form-control" style="height:auto;"></input>
										</div>  
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
									<a href="uploadDriverXSL.html?method=downloadDriverInstructionFormat">Download Instructions</a>
									<br/>
									<a href="uploadDriverXSL.html?method=downloadDriverFileFormat">Download Template</a>
								</div>
								
								<%-- <logic:present name="failedDriverList" scope="request">
									<div class="col-md-12" style="padding: 0px; height: 250px; overflow: scroll;">
										
										<p class="theading">
											<span class="displayno"></span>
										</p>
										<p>
											<font size="4">Following Records are not Uploaded!</font>
										</p>
									
										<display:table id="allstudent" name="requestScope.failedDriverList" class="table"
											requestURI="/uploadDriverXSL.html?method=insertDriverXSL">
											<display:column property="driverName" title="Driver Name" headerClass="heading1" style="font-weiht normal;" />
											<display:column property="drivingLicenseNo" title="License No." headerClass="heading1" />
											<display:column property="licenseToDrive" title="License To Drive" headerClass="heading1" />
											<display:column property="reason" title="Reason" headerClass="heading1" />
										</display:table>
								
									</div>
									<div class="paginationbox">
										<p class="paginationp"></p>
									</div>

								</logic:present> --%>
								
		 <div class="panel-body val1" style="font-family: Roboto,sans-serif;font-size: 13px; color: #000; overflow:scroll;height:300px; width: 100%;">
						<logic:present name="failedDriverList" scope="request">
						  <p style="color: #07aab9;font-family: Roboto Medium;">
							 <font size="4">Following Records are not Uploaded!</font>
						 </p>
						 <table class="table" id="allstudent">
								<thead>
									<tr>
										<th>Driver Name</th>
										<th>License No.</th>
										<th>License To Drive</th>
										<th>Reason</th>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name='failedDriverList' id="failedDriverList">
										<tr>
											<td><bean:write name='failedDriverList' property="driverName" /></td>
											<td><bean:write name='failedDriverList' property="drivingLicenseNo" /></td>
											<td><bean:write name='failedDriverList' property="licenseToDrive" /></td>
											<td><bean:write name='failedDriverList' property="reason" /></td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>

						</logic:present>
					</div>
                        <!-- <div class='pagebanner val1' style="margin-top:5px;margin-left:-47%;">
								<select id='show_per_page'><option value='50'>50</option>
									<option value='100'>100</option>
									<option value='200'>200</option>
									<option value='300'>300</option>
									<option value='400'>400</option>
									<option value='500'>500</option></select> <span class='numberOfItem'></span>
							</div>
							<div class='pagination pagelinks val1' style="margin-top:4px;"></div> -->
                            
                            <div class="col-md-12 val1" style="top:12px;">
										<div class='pagebanner' style="left:-2px;"><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
											<span  class='numberOfItem'></span>	
										</div>
										<div class='pagination pagelinks' style="right:-11px;"></div>
							 </div>

							</div>

						</div>

					</div>

				</div>

			</div>

		</form>
</body>

</html>
