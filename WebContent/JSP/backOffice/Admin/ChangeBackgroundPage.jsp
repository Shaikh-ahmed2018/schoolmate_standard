
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
<script type="text/javascript" 	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript" src="JS/spectrum.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/spectrum.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="JS/backOffice/Admin/ChangeBackgroundPage.js"></script>


</head>

<body>
	<div class="content" id="div-main">

		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Theme </span>

		</p>

		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"
					style="text-align: center;"><span class="validateTips"
					style="text-align: center;"></span></a>
			</div>
		</div>



			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips" style="text-align: center;"></span></a>
				</div>
			</div>
			<div class="container-main">
			<div class="header-css-row">
				<div class="row">
					<div class="col-md-3 col-sm-4">
						<div class="header-color theme">
							<span class="fa fa-eyedropper"></span><span>Change Theme Color</span>
						</div>
					</div>
					<div class="col-md-3 col-sm-4">
						<div class="header-color-box">
							<span class="prev-color"></span><span class="choosen-color"></span>
						</div>
					</div>
					<div class="col-md-3 col-sm-4">
						<div class="header-color-save">
							<span class="buttons" id="reset" style='display:none;'>Reset</span>
							<span class="buttons" id="headerbackgroundsave" style='display:none;'>Save</span>
						</div>
					</div>
					
				</div>
			</div>
		</div>


		

	</div>
</body>
</html>