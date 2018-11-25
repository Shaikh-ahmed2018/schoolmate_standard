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

<script type="text/javascript" src="JS/backOffice/Fee/AddFeeType.js"></script>

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
.buttons {
	vertical-align: 0px;
}
</style>
</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present
					name="Fee" scope="request">
					<bean:write name="Fee"></bean:write>
				</logic:present></span>
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





		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" style="color: #000000"><h3
							class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;
							Fee Type Details
						</h3></a>


					<div class="navbar-right">
						<span id="save" class="buttons" data-toggle="modal"
							data-target="#myModal">Save</span> <span id="back" style="right:-2px;"
							class="buttons">Back</span>

					</div>

				</div>
				<input type="hidden" id="hiddentypeid"
					value='<logic:present name="feedetails"><bean:write name="feedetails" property="sno" /></logic:present>'></input>
				<input type="hidden" id="hiddenfeeid"
					value='<logic:present name="feedetails"><bean:write name="feedetails" property="feeId" /></logic:present>'></input>
				<input type="hidden" id="hiddenfeename"
					value='<logic:present name="feedetails"><bean:write name="feedetails" property="feename" /></logic:present>'></input>
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Fee Type
									Id<span style="color: #F00;">*</span>
								</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="feetypeid"
										id="feetypeid" onclick="HideError(this)"
										value='<logic:present name="feedetails"><bean:write name="feedetails" property="feeId"/></logic:present>'></input>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Fee Type<span
									style="color: #F00;">*</span></label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="feetypename"
										id="feetypename" onclick="HideError(this)"
										value='<logic:present name="feedetails"><bean:write name="feedetails" property="feename"/></logic:present>'></input>
								</div>
							</div>
						</div>

						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- <button type="submit" class="btn btn-info col-md-offset-5">Savechanges</button>
				<button type="reset" class="btn btn-info">Clear</button> -->


</body>

</html>
