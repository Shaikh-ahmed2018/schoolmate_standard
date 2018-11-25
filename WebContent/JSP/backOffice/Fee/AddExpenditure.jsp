<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Campus School Stream</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="JS/backOffice/Fee/AddExpenditure.js"></script>

<style>
#save:hover {
	cursor: pointer;
}
</style>

<style>
#view:hover {
	cursor: pointer;
}
</style>

<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">New
				Expenditure Details</span>
		
		</p>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<logic:present name="errormessagediv" scope="request">
			<div class="errormessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv1" align="center"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 35%; font-size: 13px; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>

<input type="hidden" id="expId" value='<logic:present name="editlist"><bean:write name="editlist" property="expId" /> </logic:present>'></input>

			<input type="hidden" id="hdescription" value="<logic:present name="editlist"><bean:write name="editlist" property="description"></bean:write></logic:present>"/>
									
	<input type="hidden" id="hdnlocation" value="<logic:present name="editlist"><bean:write name="editlist" property="locationname"></bean:write></logic:present>"/>								


		
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #000000"><h3 class="panel-title"> <i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp; New Expenditure 
							</h3></a>
						

						<div class="navbar-right">
							<span id="save" class="buttons" data-toggle="modal" data-target="#myModal">Save</span>
							<span id="back" class="buttons" >Back</span>

						</div>

					</div>
					<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
         
						<div class="panel-body">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							        	<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locationid" name="locationname" class="form-control" required>
											<option value="">----------Select----------</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
									
								</div>
							
								
							      <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Date</label>
									<div class="col-xs-7">
									<input type="text" name="date" id="date"
										class="form-control" readonly="readonly" onkeypress="HideError()"
										placeholder="---Select Date----"
										value='<logic:present name="editlist"><bean:write name="editlist" property="date" /></logic:present>'></input>
								</div>
								</div>
								
								
								
							 <div class="form-group clearfix">
								<label for="inputDescription" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px; margin-right: 1px;">Description
								</label>
								<div class="col-xs-7">
									<textarea  style="resize: none" rows="4" class="form-control"
										maxlength="250" name="description" id="description">
										
									</textarea>
								</div>
								</div>
							  	
							</div>

							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								  
								  	<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Expenditure Title</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="expenditureTitle"
											id="expenditureTitle"  onkeypress="HideError()"
											value='<logic:present name="editlist"><bean:write name="editlist" property="expenditureTitle"/></logic:present>'></input>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Amount</label>
									<div class="col-xs-7">
										<input type="number" class="form-control" name="amount"
											id="amount"  onkeypress="HideError()"
											value='<logic:present name="editlist"><bean:write name="editlist" property="amount"/></logic:present>'></input>
									</div>
								</div>
								
								
							</div>

						</div>
					
					</div>
				</div>
			</div>
	</div>

	
	
</body>

</html>
