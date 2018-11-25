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

<script type="text/javascript" src="JS/backOffice/Transport/StageExcelFileUpload.js"></script>

<style>
  #allstudent th:nth-child(1) {
    text-align: center;
    width: 30%;
}
/* .panel-body {
    padding: 0px;
    padding-top: 18px;
} */
</style>
</head>

<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="col-md-10"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p class="transportheader">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Upload Stage Excel Data File</span>
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
				<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="errorMessage" scope="request">
			<div class="errormessagediv" align="center" style="width: 1200px;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span class="emptyclass"><bean:write
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


		<form action="uploadStageXSL.html?method=insertStageXSL"
			id="excelfileupload" name="UploadStudentXSLForm" method="post"
			enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #000000;vertical-align: text-top;"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Upload Stage Excel Data File
							</h3></a>
						
						<!-- <div class="navbar-right">
							<span id="saveid" class="buttons"  data-toggle="tooltip" data-placement="bottom">Upload</Span>
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
							<div class="panel-body" style="padding-top: 18px;">
							 <div class="col-md-6" id="txtstyle" style="padding: 0;">
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4" id="inputnames"></label>
										<div class="col-xs-7">
											<input type="file" name="formFile" id="stageFile"  onchange="checkFileSize(this)";
												class="form-control"></input>
										</div>
									</div>
								</div> 
								
								
								<!-- <div class="col-md-6" id="txtstyle">
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4" id="inputnames"></label>
										<div class="col-xs-7">
											<input type="file" name="formFile" id="studentfile" onchange="checkFileSize(this)" class="form-control"></input>
										</div>
									</div>
								</div> -->

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
									<a href="uploadStageXSL.html?method=StageInstructionFormat">Download Instructions</a>
									<br/>
									<a href="uploadStageXSL.html?method=downloadStageFileFormat">Download Template</a>
								</div>
								
								<%-- <logic:present name="failedStageList" scope="request">
									<div class="col-md-12" style="padding: 0px; height: 250px;">
										
										<p class="theading">
											<span class="displayno"></span>
										</p>
										<p>
											<font size="4" color="red" >Following Records are not Uploaded!</font>
										</p>
									<div class="col-md-12">
									<input type="button" class="buttons" id="override" value="Over Ride" /></div>
										<display:table id="allstudent"
											name="requestScope.failedStageList" class="table"
											requestURI="/uploadStageXSL.html?method=insertStageXSL">
				
<display:column style="text-align:center"
								title="<input type='checkbox' name='selectall' style='text-align:center' id='selectall'  />">
								<input type='checkbox' name='select' class='select'
									style='text-align: center' id='${allstudent.stageid }'/>
							</display:column>							
											<display:column property="reason"
												title="Reason" headerClass="heading1" />
											<display:column property="stage_name"
												title="Stage" headerClass="heading1" style="font-weiht normal;" />
											<display:column property="amount"
												title="Amount" headerClass="heading1" />
											<display:column property="stage_description" title="Description"
												headerClass="heading1" />
											
												<display:column  title="Over Ride" ><input type="button" class="buttons override"  value="Over Ride" /></display:column>
										</display:table>
										
					<logic:present name="failedStageList" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' style='text-align:center' id='selectall' /></th>
							<th>Reason</th>
							<th>Stage Name</th>
							<th>Amount</th>
							<th>Description</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="failedStageList" name="failedStageList">
								<tr>
								<td><input type='checkbox' name='select' class='select'style='text-align: center' id='<bean:write name="failedStageList" property='stageid'/>,<bean:write name="failedStageList" property='accyearid'/>,<bean:write name="failedStageList" property='locationid'/>,<bean:write name="failedStageList" property='amount'/>' /></td>
								<td class='reason'><bean:write name="failedStageList" property='reason'/></td>
								<td><bean:write name="failedStageList" property='stage_name'/></td>
								<td><bean:write name="failedStageList" property='amount'/></td>
								<td><bean:write name="failedStageList" property='stage_description'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
									</div>
									<div class="paginationbox">
										<p class="paginationp"></p>
									</div>
								</logic:present> --%>
								
								
		 <div class="panel-body val1" style="font-family: Roboto,sans-serif;font-size: 13px; color: #000; overflow:scroll;height:300px; width: 100%;padding-top: 22px;">
						<logic:present name="failedStageList" scope="request">
						<p style="color: #ff1010;font-family: Roboto Medium;">
							 <font size="4">Following Records are not Uploaded!</font>
						 </p>
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<!-- <th><input type='checkbox' name='selectall' style='text-align:center' id='selectall' /></th> -->
							<th>Stage Name</th>
							<th>Amount</th>
							<th>Description</th>
							<th>Reason</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="failedStageList" name="failedStageList">
								<tr>
								<%-- <td><input type='checkbox' name='select' class='select'style='text-align: center' id='<bean:write name="failedStageList" property='stageid'/>,<bean:write name="failedStageList" property='accyearid'/>,<bean:write name="failedStageList" property='locationid'/>,<bean:write name="failedStageList" property='amount'/>' /></td> --%>
								<td><bean:write name="failedStageList" property='stage_name'/></td>
								<td><bean:write name="failedStageList" property='amount'/></td>
								<td><bean:write name="failedStageList" property='stage_description'/></td>
								<td class='reason'><bean:write name="failedStageList" property='reason'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
					</div>
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
