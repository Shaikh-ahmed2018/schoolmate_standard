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
<script type="text/javascript" src="JS/backOffice/Library/librarypublishersettings.js"></script>
<script type="text/javascript" src="">
	

</script>

<script type="text/javascript">

function CheckIsNumeric1(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode !=43 && charCode !=32 && charCode !=45 && charCode !=40 && charCode !=41  && charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
</script>

<style>
.form-group {
	margin-bottom: 5px;
}

.save:hover {
	cursor: pointer;
}

fieldset {
	width: 512px;
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

.text2 {
	margin-left: 211px !important;
	width: 819px !important;
}
</style>

<style>
.buttons {
	vertical-align: -5px;
}

.navbar-right {
	top: -3px;
}

.panel-primary>.panel-heading {
	margin-bottom: 0px;
}

form .panel.panel-primary.panel-list {
	padding-bottom: 0px;
}

.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all {
	z-index: 1;
	display: block;
	left: 478px;
	width: 346px;
	height: 300px;
	overflow: scroll;
	position: absolute;
	top: 200px !important;
}

.back {
	display: none;
}

.navbar-right {
	top: -1px;
}

textarea {
	resize: none;
}
</style>
</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" style="vertical-align: top;"/><span id="pageHeading"><logic:present
					name="msg">
					<bean:write name="msg" />
				</logic:present></span>
		</p>


		<div class="successmessagediv"
			style="width: auto !important; display: none" align="center">
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

		<div class="panel-group" id="accordion1" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingpub">

					<a data-toggle="collapse" data-parent="#accordion1"
						href="#collapsepub"
						style="color: #000000; vertical-align: text-top;"><h3
							class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Publisher Setting
						</h3></a>


					<div class="navbar-right">
						<span class="buttons" id="save">Save</span>&nbsp; <span
							class="buttons" id="back1">Back</span> &nbsp;
					</div>
				</div>
				<input type="hidden" id="hiddenid" name="entry_id"
					value='<logic:present name="pubid"><bean:write name="pubid"/></logic:present>'>
				<input type="hidden" id="hiddenpubname"
					value='<logic:present name="editlist"><bean:write name="editlist" property="publisher"/></logic:present>'>
				<input type="hidden" id="hiddenpubadd" class="hiddenpubadd"
					value='<logic:present name="editlist"><bean:write name="editlist" property="address" /></logic:present>'>


				<input type="hidden" id="historysearch"
					value='<logic:present name="historysearch" scope="request"><bean:write name="historysearch" /></logic:present>' />

				<div id="collapsepub" class="panel-collapse collapse in "
					role="tabpanel" aria-labelledby="headingpub">

					<div class="panel-body own-panel">
						<div class="row">
							<div class="col-md-6"
								style="font-family: Roboto, sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Publisher
										Name<span class='asteric'>*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" id="Publisher" name="publisher"
											tabindex="1" class="form-control" maxlength="50"
											value='<logic:present name="editlist"><bean:write name="editlist" property="publisher" /></logic:present>'></input>
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Email Id
										<span class='asteric'>*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" id="Email" name="email"
											class="form-control" tabindex="3"
											value='<logic:present name="editlist"><bean:write name="editlist" property="email" /></logic:present>'></input>
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Publisher
										Address <span class='asteric'>*</span>
									</label>
									<div class="col-xs-7">
										<textarea rows="3.8" cols="37" id="Address" name="address" class="address" tabindex="5"><logic:present name="editlist"><bean:write name="editlist" property="address" /></logic:present></textarea>
									</div>
								</div>


							</div>

							<div class="col-md-6"
								style="font-family: Roboto, sans-serif; font-size: 13px; color: #000; margin-top: 10px;">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Published
										Date <span class='asteric'>*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" id="pubdate" name="pubdate" tabindex="2"
											class="form-control"
											value='<logic:present name="editlist"><bean:write name="editlist" property="pubdate" /></logic:present>'></input>
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Telephone
										Number <span class='asteric'>*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" id="telephone" name="telephone"  class="form-control"
											maxlength="20" onkeypress="return CheckIsNumeric1(this);" tabindex="4"
											value='<logic:present name="editlist"><bean:write name="editlist" property="telephone" /></logic:present>'></input>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Mobile
										Number <span class='asteric'>*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" id="mobilenum" name="mobilenum"
											tabindex="6" class="form-control" maxlength="20"
											onkeypress="return CheckIsNumeric1(this);"
											value='<logic:present name="editlist"><bean:write name="editlist" property="mobilenum" /></logic:present>'></input>

									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Fax</label>
									<div class="col-xs-7">
										<input type="text" id="Fax" name="fax" tabindex="1"
											class="form-control" tabindex="7" maxlength="20" 
											onkeypress="return CheckIsNumeric1(this);"
											value='<logic:present name="editlist"><bean:write name="editlist" property="fax" /></logic:present>'></input>
									</div>
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
