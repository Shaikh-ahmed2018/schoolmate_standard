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
<script type="text/javascript"
	src="JS/backOffice/Parents/viewResultDetails.js"></script>

<script type="text/javascript">
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<style>
.glyphicon:hover {
	cursor: pointer;
}

#classSave:hover {
	cursor: pointer;
}

.navbar-right {
	margin: 0px;
}
.borderless td,.borderless tbody {
    border: none !important;
}
.title{
	width: 120px;
}
.title2{
	width: 160px;
}
.seperator{
	width: 10px;
}
</style>
</head>

<body>
	<div class="col-md-10"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Result Details</span>
		</p>
		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<logic:present name="error" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="error" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>


		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="errormessagediv1"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 50%; font-size: 13px; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>
		<input type="hidden" id="hiddenloc" value='<logic:present name="dataList"><bean:write name="dataList" property="locid"></bean:write></logic:present>'>
		<input type="hidden" id="hiddenexamid" value='<logic:present name="dataList"><bean:write name="dataList" property="examid"></bean:write></logic:present>'>
		<input type="hidden" id="hiddensecrtionid" value='<logic:present name="dataList"><bean:write name="dataList" property="sectionid"></bean:write></logic:present>'>
		<input type="hidden" id="hiddenclass" value='<logic:present name="dataList"><bean:write name="dataList" property="classDetailId"></bean:write></logic:present>'>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne" 
							 style="color: #000000"><h3 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Result Details
						</h3></a>
					
					<div class="navbar-right">
						 <span id="back" class="buttons">Back</span>
					</div>
				</div>

				<div id="collapseOne" class="accordion-body collapse in" role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body" id="txtstyle">
						<div class="col-md-12" style="margin-top: 15px;font-weight: 600;">
						<div class="col-md-1"></div>
							<div class="col-md-5">
								<table class='table borderless'>
								<tr>
									<td class='title'>Admission No.</td>
									<td>:</td>
									<td>9652</td>
								</tr>
								<tr>
									<td class='title'>Student's Name</td>
									<td class='seperator'>:</td>
									<td>Ashish</td>
								</tr>
								<tr>
									<td class='title'>Father's Name</td>
									<td class='seperator'>:</td>
									<td>Test1</td>
								</tr>
								<tr>
									<td class='title'>Mother's Name</td>
									<td class='seperator'>:</td>
									<td>Test2</td>
								</tr>
								</table>
						 </div>
					<div class="col-md-5">
						<table class='table borderless'>
						<tr>
							<td class='title2'>Class/Section/Roll No.</td>
							<td class='seperator'>:</td>
							<td>Pre-Kg A</td>
						</tr>
						<tr>
							<td class='title2'>Date of Birth</td>
							<td class='seperator'>:</td>
							<td>15-3-2000</td>
						</tr>
						</table>
					</div>	
					<div class="col-md-1"></div>					
					</div>
					
					<div class="T1">
						<table class='table table-bordered'>
							<thead>
								<tr>
									<th rowspan="2">Subject Name</th>
									<th colspan="2">Note Book</th>
									<th colspan="2">Sub Enrichment</th>
									<th colspan="2">Half Yearly Exam (80)</th>
								</tr>
								<tr>
									<th>Max.marks</th>
									<th>Min.marks</th>
									<th>Max.marks</th>
									<th>Min.marks</th>
									<th>Max.marks</th>
									<th>Min.marks</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Maths</td>
									<td>25</td>
									<td>20</td>
									<td>25</td>
									<td>20</td>
									<td>25</td>
									<td>20</td>
								</tr>
								<tr>
									<td>Physics</td>
									<td>25</td>
									<td>20</td>
									<td>25</td>
									<td>20</td>
									<td>25</td>
									<td>20</td>
								</tr>
							</tbody>
						</table>
					</div>
					</div>
					</div>
				</div>
			</div>
		</div>
</body>

</html>
