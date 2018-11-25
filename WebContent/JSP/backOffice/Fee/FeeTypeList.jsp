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

<script type="text/javascript" src="JS/backOffice/Fee/FeeTypeList.js"></script>



<style>
#feeedit:hover {
	cursor: pointer;
}

#termedit:hover {
	cursor: pointer;
}

#delete:hover {
	cursor: pointer;
}

.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

</style>

</head>
<body class="feeconcession">


	<div id="dialog"></div>

	<div class="content" id="div1">
		<div class="searchWrap">
			<div class="" id="div2">

				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Fee Type</span>
				</p>
			</div>

		</div>
		<div class="successmessagediv feedetails" align="center"
			style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="sucessmessage"></span></a>
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

		<div class="errormessagediv1" id="errormessagediv1"
			style="display: none; width: 35%; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="font-size: 13px; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>
		<input type="hidden" id="feesearchid"
			value='<logic:present name="searchfee"><bean:write name="searchfee"  /></logic:present>'></input>




		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Fee
						Type List
					</h3> </a>

				<div class="navbar-right" style="right:-5px;">
				     <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEEADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
										<a href="addfee.html?method=addFeeTypeMaster"> <span class="buttons" style="right:1px;">New</span></a>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							     <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEEUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
										 <span id="feeedit" class="buttons">Modify</span>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							     <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEEDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											 <span id="delete" style="margin-right: 10px;" class="buttons">InActive</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

					
				

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
			<div class="col-md-6"
				style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">

				<div class="form-group clearfix ">
					<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Status</label>
					<div class="col-xs-7">
						<select name="status" tabindex="1" id="status"
							class="form-control">
							<option value="Y" selected>Active</option>
							<option value="N">InActive</option>
						</select>
					</div>
				</div>
			</div>

			<input type="hidden" id="hiddenlength" name="hiddenlength"
				value="<logic:present name='length' scope='request' ><bean:write name='length'/></logic:present>" />

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<div id="feetable">
						<display:table id="allstudent" class="table"
							name="requestScope.feetypelist"
							requestURI="/menuslist.html?method=feeTypeList">

							<display:column
								title="<input type='checkbox' name='selectall' id='selectall'/>">
								<input type='checkbox' name='select' class='select'
									value='${allstudent.sno}' />
							</display:column>
							<display:column property="feeId" sortable="true"
								title="Fee Type ID <img src='images/sort1.png' style='float: right'/> " />
							<display:column property="feename" sortable="true"
								title="Fee Type <img src='images/sort1.png' style='float: right'/> " />
							<display:column property="status" sortable="true"
								title="Status <img src='images/sort1.png' style='float: right'/> " />
							<display:column property="remark" sortable="true"
								title="Remark<img src='images/sort1.png' style='float: right'/> " />

						</display:table>
					</div>
					<div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select> <span class='numberOfItem'></span>
					</div>
					<div class='pagination pagelinks' style="margin-bottom: 10px;"></div>
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