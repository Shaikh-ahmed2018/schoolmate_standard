<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<title>eCampus Pro</title>
<script type="text/javascript" src="JS/common.js"></script>


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>

<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script src="JS/newUI/bootstrap.min.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet"
	type="text/css">
<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="JS/backOffice/Fee/ExpenditureDetails.js"></script>



<title>eCampus Pro</title>
<style>
#feeedit:hover {
	cursor: pointer;
}
</style>

<style>
#delete:hover {
	cursor: pointer;
}

#excelDownload :hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

#iconsimg:hover {
	cursor: pointer;
}

.pagebanner {
	margin-left: 5px;
}
</style>

<script type="text/javascript">

function handle(e){

	var searchname = $("#searchvalue").val().trim();
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn
        window.location.href = "menuslist.html?method=expenditureDetailsList&searchvalue="+ searchname;
    }
}

</script>


</head>

<body>




	<div class="content" id="div1">
		<div id="dialog"></div>
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Expenditure Details</span>
			</p>
		</div>
		<div class="input-group col-md-4" style="margin: 20px 0px;">

			<input type="text" class="form-control" id="searchvalue"
				onkeypress="handle(event)" Placeholder="Search......"> <span
				class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>


		<input type="hidden" id="feesearchid"
			value='<logic:present name="searchfee"><bean:write name="searchfee"  /></logic:present>'></input>




		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<logic:present name="errormessagediv" scope="request">
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv1"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 35%; font-size: 13px; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="sucessmessage"></span></a>
			</div>
		</div>



		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2" href="#"
					style="color: #fff;"><h3 class="panel-title class"
						style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Expenditure
						Details
					</h3> </a>

				<div class="navbar-right">
				             <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXPADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											 <a href="addExpenditure.html?method=addExpenditure"> <span
						class="buttons" data-toggle="" data-placement="bottom" title="">New</span></a>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							  <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXPUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										<span id="expenditureedit" class="buttons" data-toggle="" data-placement="bottom" title="">Modify</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							  <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="EXPDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											  <span id="delete" class="buttons" data-toggle="" data-placement="bottom" title="">InActive</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
				

						
						
					<!-- <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="" data-placement="bottom" title="">Download </span> -->

				</div>

			</div>
			<!-- pop up -->

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

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

					<div class="col-md-6">
						<div class="form-group clearfix ">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Status</label>
							<div class="col-xs-7">
								<select name="isActive" tabindex="1" id="status"
									class="form-control">
									<option value="Y" selected>Active</option>
									<option value="N">InActive</option>
								</select>
							</div>
						</div>
					</div>

					<div id="collapseOne" class="accordion-body collapse in">
						<div class="panel-body"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							<logic:present name="expndList" scope="request">

								<table class="table" id="allstudent">
									<thead>
										<tr>
											<th><input type='checkbox' name='selectall'
												id='selectall' style='text-align: center'
												onClick='selectAll()' /></th>
											<th>Branch</th>
											<th>Expenditure Title</th>
											<th>Amount</th>
											<th>Description</th>
											<th>Date</th>
											<th>Status</th>
											<th>Remarks</th>
										</tr>
									</thead>
									<tbody>
										<logic:iterate name='expndList' id="expndList">
											<tr>
												<td><input type='checkbox' name='select' class='select'
													style='text-align: center'
													value='<bean:write name='expndList' property="expId"/>'
													id='<bean:write name='expndList' property="expId"/>' /></td>
												<td><bean:write name='expndList'
														property="locationname" /></td>
												<td><bean:write name='expndList'
														property="expenditureTitle" /></td>
												<td><bean:write name='expndList' property="amount" /></td>
												<td><bean:write name='expndList' property="description" /></td>
												<td><bean:write name='expndList' property="date" /></td>
												<td><bean:write name='expndList' property="isActive" /></td>
												<td><bean:write name='expndList' property="remark" /></td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>

								<div class='pagebanner' style="margin-left: 10px;">
									<select id='show_per_page'>
										<option value='50'>50</option>
										<option value='100'>100</option>
										<option value='200'>200</option>
										<option value='300'>300</option>
										<option value='400'>400</option>
										<option value='500'>500</option>
									</select> <span class='numberOfItem'></span>
								</div>
								<div class='pagination pagelinks' style="margin-bottom: 10px;"></div>
							</logic:present>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- <script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script> -->
</body>
</html>