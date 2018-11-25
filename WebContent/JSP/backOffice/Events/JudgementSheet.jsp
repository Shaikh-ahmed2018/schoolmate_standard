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

<script type="text/javascript" src="JS/common.js"></script>


<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<!-- <script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
 --><script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
 --><link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/fancybox/1.3.4/jquery.fancybox-1.3.4.pack.min.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Events/JudgementSheet.js"></script>

<style>
.glyphicon:hover {
	cursor: pointer;
}

.buttons {
	top: 10px !important;
}

.pagebanner {
	margin-top: 13px;
}

.pagination {
	margin-right: 10px;
	float: right;
	margin-top: 10px;
}

.form-control multiple {
	height: auto !important;
}

.glyphicon-plus:before {
	content: "\2b";
	margin-right: -3px;
}

div[class^=col-md] {
	font-family: Roboto,sans-serif;
	font-size: 13px;
	color: #000;
}

table[id^=participants] {
	width: 100%;
}

select#participantsList {
	width: 116%;
	height: 190px !important;
}

.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons
	{
	width: 75% !important;
	left: 0 !important;
	right: 0 !important;
	margin: auto;
}

#allstudent tbody  tr table tr.captainHighlight td {
	background-color: #07aab9;
	color: #fff;
}

#allstudent tbody td {
	border: none;
	vertical-align: middle;
}

.navbar-right {
	top: -3px;
}
</style>
</head>
<body>
	<script>
		$(function($) {
			var addToAll = false;
			var gallery = true;
			var titlePosition = 'inside';
			$(addToAll ? 'img' : 'img.fancybox').each(
					function() {
						var $this = $(this);
						var title = $this.attr('title');
						var src = $this.attr('data-big') || $this.attr('src');
						var a = $('<a href="#" class="fancybox"></a>').attr(
								'href', src).attr('title', title);
						$this.wrap(a);
					});
			if (gallery)
				$('a.fancybox').attr('rel', 'fancyboxgallery');
			$('a.fancybox').fancybox({
				titlePosition : titlePosition
			});
		});
		$.noConflict();
	</script>

	<div class="content" id="div1">
		<div id="dialog"></div>
		<div id="deleteDialog"></div>
		<div class="searchWrap">
			<div id="div2">
				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Judgement Sheet</span>
				</p>
			</div>
		</div>
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

		<div class="panel panel-primary clearfix">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2" href="#"
					style="color: #fff;"><h3 class="panel-title class"
						style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Judgement Sheet
					</h3></a>

				<div class="navbar-right">
					<span class="buttons" id="print">Print</span>
				</div>

			</div>

			<div id="classOne" class="accordion-body collapse in">
				<div class="panel-body">

					<div class="col-md-6 groupData" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames">Event Name</label>
							<div class="col-xs-7">
								<select id="eventname" name="eventname" class="form-control">
									<logic:present name="eventList">
										<option value="">----------select----------</option>
										<logic:iterate id="eventList" name="eventList">
											<option
												value="<bean:write name="eventList" property="eventId"/>">
												<bean:write name="eventList" property="eventName" />
											</option>
										</logic:iterate>
									</logic:present>
								</select>
							</div>
						</div>
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames">Category Name</label>
							<div class="col-xs-7">
								<select id="categoryName" name="categoryName"
									class="form-control">
									<option value="">----------select----------</option>
								</select>
							</div>
						</div>
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames">Program Name</label>
							<div class="col-xs-7">
								<select id="programName" name="programName" class="form-control">
									<option value="">----------select----------</option>
								</select>
							</div>
						</div>
					</div>


					<div class="col-md-6 groupData" id="txtstyle">
					
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								align="right" id="inputnames">Stage Name<span style="color: red;"></span></label>
							<div class="col-xs-7">
									<select name="stageName" id="stageName" class="form-control">
									<option value="">----------select----------</option>
									</select>
							</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Programme Date</label>
								<div class="col-xs-7">
									<input type="text" name="event" id="event" readonly="readonly" class="form-control"  />
								</div>
							</div>
					</div>
				
				</div>
			</div>


			<div>
				<div class="slideTab clearfix"></div>
			</div>

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h3 class="modal-title" id="myModalLabel">Download</h3>
						</div>
						<div class="modal-body">
							<span id="excelDownload"><img src="images/xl.png"
								class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in "></div>
		</div>
	</div>
</body>
</html>