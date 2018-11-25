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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<script type="text/javascript" src="JS/backOffice/Admin/feedetails.js"></script>

</head>
<body class="feeconcession">

<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div id="dialog"></div>

	<div class="content" id="div1">
		<div class="searchWrap">
			<div class="" id="div2">

				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Fee Setup</span>
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

		<div class="errormessagediv1"
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
						List
					</h3> </a>

				<div class="navbar-right" style="right:-5px">
				
				            <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEESADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											<a href="addfee.html?method=addfeedetails"> <span class="buttons">New</span></a> 
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEESUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											 <span id="feeedit" class="buttons">Modify</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEESDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											 <span id="delete" class="buttons" Style="margin-right: 10px;">InActive</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEESDWD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
											  <span Style="display: none;" class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>
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
	
	<input type="hidden" id="currentstatus"
	 value='<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>'></input>
			
	<input type="hidden" id="historylocId"
	 value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>'></input>
					
     <input type="hidden" id="historyacyearId"
	 value='<logic:present name="historyacyearId" scope="request"><bean:write name="historyacyearId"/></logic:present>'></input>	 		
	
	<input type="hidden" id="historystatus"
    value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>'></input>		

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<div class=row>
						<div class="col-md-6">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Branch<span style="color: red;">*</span>
								</label>
								<div class="col-xs-7">
									<select id="locationname" name="locationId"
										class="form-control">
										<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option
													value="<bean:write name="Location" property="locationId"/>"><bean:write
														name="Location" property="locationName" /></option>
											</logic:iterate>
										</logic:present>

									</select>
								</div>
								<input type="hidden" name="schoolId" class="form-control"
									id="schoolId"
									value='<logic:present name="list"><bean:write name="list" property="locationId" /></logic:present>'></input>
							</div>

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
						<div class="col-md-6">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-7">
									<select id="Acyearid" name="accyear" class="form-control"
										required>

										<logic:present name="AccYearList">
											<logic:iterate id="AccYear" name="AccYearList">
												<option
													value="<bean:write name="AccYear" property="accyearId"/>"><bean:write
														name="AccYear" property="accyearname" /></option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>

						</div>

					</div>

					<div id="feedetaillist">
						<logic:present name="feelist" scope="request">
							<table class="table" id="allstudent">
								<thead>
									<tr>
										<th><input type='checkbox' name='selectall' id='selectall' /></th>
										<th>Academic Year</th>
										<th>Branch</th>
										<th>Fee Type</th>
										<th>Fee Name</th>
										<th>Description</th>
										<th>Status</th>
										<th>Remark</th>

									</tr>
								</thead>
								<tbody>
									<%-- <logic:iterate id="feelist" name="feelist">
										<tr>
											<td><input type='checkbox' name='select' class='select'
												value='<bean:write name="feelist" property='id'/>' /></td>
											<td><bean:write name="feelist"
													property='academicYearName' /></td>
											<td><bean:write name="feelist" property='locationName' /></td>
											<td><bean:write name="feelist" property='feeType' /></td>
											<td><bean:write name="feelist" property='name' /></td>
											<td><bean:write name="feelist" property='description' /></td>
											<td><bean:write name="feelist" property='status' /></td>
											<td><bean:write name="feelist" property='remark' /></td>
										</tr>
									</logic:iterate> --%>
								</tbody>
							</table>
						</logic:present>
					</div>
					<div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select> <span class='numberOfItem'></span>
					</div>
					<div class='pagination pagelinks' style="margin-bottom: 10px"></div>


				</div>
			</div>
		</div>
	</div>

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</body>
</html>