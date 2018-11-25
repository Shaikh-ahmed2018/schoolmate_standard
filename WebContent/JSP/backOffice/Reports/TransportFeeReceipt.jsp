<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<title>eCampus Pro</title>

<script src="JS/backOffice/Reports/TransportFeeReport.js"></script>


<style>
.modal-body {
	text-align: center;
}


</style>
</head>
<body>
	<div class="col-md-10">
		<p style="margin-bottom: 0px;">
			<img src="images/addstu.png" style="vertical-align: top;"/>&nbsp;<span id="pageHeading">Transport Fee</span>
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
					class="successmessage"></span></a>
			</div>
		</div>




		<p id="parent1" style="display: none;">
			<a href="">Expand all</a>
		</p>
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #000000;vertical-align: text-top"><h3 class="panel-title" id="beforeparent"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Transport
						</h3></a>
					

					<div class="navbar-right">
						<span class="buttons" id="iconsimg" data-toggle="modal"
							data-target="#myModal" 
							data-placement="bottom">Download </span>
					</div>

					
				</div>
				<!-- pop up -->

				<div class="modal" id="myModals" tabindex="-1" role="dialog"
					 style="display: none;">
					<div class="modal-dialog" >
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h3 class="modal-title" id="myModalLabel">Download</h3>
								
							</div>
							<div class="modal-body">
								<span id="excelDownload"><img src="images/xl.png" class="xl"></span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<span id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
							</div>

						</div>
					</div>
				</div>

				<!-- Filters -->

				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body" id="tabletxt" style="padding: 15px;">

						<div class="col-md-6" id="txtstyle">

							
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Branch</label>
								<div class="col-xs-7">
									<select id="location" name="location" class="form-control"
										required>
										<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option
													value="<bean:write name="Location" property="locationId"/>">
													<bean:write name="Location" property="locationName" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
							
							<input type="hidden" id="hacademicyaer" value='<logic:present name="accYear"><bean:write name="accYear" ></bean:write></logic:present>'></input>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Class</label>
								<div class="col-xs-7">
									<select id="class" name="classname" class="form-control"
										required >
										<option value="">----------Select----------</option>
										<%-- <logic:present name="classDetails">
											<logic:iterate id="ClassList" name="classDetails">
												<option value="<bean:write name="ClassList" property="classId"/>"><bean:write name="ClassList" property="classname" /></option>
											</logic:iterate>
										</logic:present> --%>
									</select>
								</div>
							</div>
						
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Term</label>
								<div class="col-xs-7">
									<select id="term" name="term" class="form-control" required>
										<option value="">----------Select----------</option>
										
										<logic:present name="termlist">
											<logic:iterate id="Term" name="termlist">
												<option
													value=" <bean:write name="Term" property="termId"/>">
													<bean:write name="Term" property="termname" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
							
							
						</div>

						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Academic Year</label>
								<div class="col-xs-7">
									<select id="accyear" name="accyear" class="form-control"
										required>
										<option value="">----------Select----------</option>
										<logic:present name="AccYearList">
											<logic:iterate id="AccYear" name="AccYearList">
												<option value="<bean:write name="AccYear" property="accyearId"/>">
													<bean:write name="AccYear" property="accyearname" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
						

					<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Division</label>
								<div class="col-xs-7">
									<select id="section" name="section" class="form-control" required>
										<option value="">----------Select----------</option>
									</select>
								</div>
							</div>
							
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Status</label>
								<div class="col-xs-7">
									<select id="termstatus" name="termstatus" class="form-control" required>
									<option value="">----------Select----------</option>
									<option value="Y">Paid</option>
									<option value="N">Unpaid</option>
									</select>
								</div>
							</div>
							<br/>

							<!-- <button type="button" class="btn btn-info" id="search">Search</button> -->
							<br/>
						</div>		
						<div id="studenttable"></div>
					</div>
				</div>
			</div>
			<!-- Button trigger modal -->

		</div>
<script>
$(document).ready(function(){
$(".close").click(function(){
	$("#myModals").removeClass("in");
	$("#myModals").hide();
});
	$("#iconsimg").click(function(){
		if(validateloc()==false)
			{
			$("#myModals").removeClass("in");
		
			}
		else
			{
			$("#myModals").addClass("in");
			$("#myModals").show();
			}
		
	});
});
</script>
		
  
  </div>
</body>

</html>
