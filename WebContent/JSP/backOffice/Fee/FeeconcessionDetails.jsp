<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript" src="JS/backOffice/Fee/concession.js"></script>

</head>

<body class="feeconcession">
	<div id="loder"
		style="display: none; position: absolute; height: 800px; width: 800px; left: 0; right: 0; top: -40px;; bottom: 0; margin: auto; z-index: 99999;">
		<img
			style="width: 165px; position: absolute; left: 0; right: 0; height: 165px; bottom: 0; top: -190px; margin: auto;"
			src="./images/ajax-loading.gif" />
	</div>

	<div class="content" id="div1">
		<div id="dialog"></div>
		<input type="hidden" id="feeconcessionsearchid"
			value='<logic:present name="concessiondetailsearch"><bean:write name="concessiondetailsearch"  /></logic:present>'></input>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>



		<div class="errormessagediv1" style="display: none;">
			<div class="message-item1"></div>
		</div>

		<div class="panel-heading clearfix">
			<h3 class="panel-title" style="color: #000000;">Concession
				details</h3>

			<div class="navbar-right">
				<!-- add btn -->
				<logic:present name="UserDetails" scope="session">
					<logic:iterate id="daymap" name="UserDetails"
						property="permissionmap" scope="session">
						<logic:equal value="CLSADD" name="daymap" property="key">
							<logic:equal value="true" name="daymap" property="value">
								<span id="savebutton" class="btn btn-xs btn-primary margin-t-5"
									data-toggle="modal" data-target="#myModal"> <span
									class="glyphicon glyphicon-plus"></span>Add Fee Concession
								</span>
							</logic:equal>
						</logic:equal>
					</logic:iterate>
				</logic:present>
				<!-- : ends-->

				<!-- edit & delete-->
				<!-- no permission specified  -->
				<input type="hidden" id="editPermission"
					value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="CLSUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
				<input type="hidden" id="delPermission"
					value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="CLSDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
				<!--  :ends-->
			</div>

			<!-- 
					<a href="menuslist.html?method=addfeeconcession"><span class="buttons">New</span></a> 
					<span id="editfee" class="buttons">Modify</span>

					<span id="inactive" style="margin-right:5px;" class="buttons">InActive</span>
 -->


		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" id="myModalLabel">Fee Concession</h3>
					</div>
					<div class="modal-body">
						<div class="panel-body clearfix">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
									<select id="locationname" name="locationnid"
										onchange="HideError(this)" tabindex="1"
										class="form-control locationname" required>
										<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option
													value="<bean:write name="Location" property="locationId"/>"><bean:write
														name="Location" property="locationName" /></option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
							<input type="hidden" name="hiddenlocId" id="hiddenlocId"
								value='<logic:present name="ConcessionList"><bean:write name="ConcessionList" property="locationnid"/></logic:present>'></input>
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Concession
									Name</label>
								<div class="col-xs-7">
									<input type="text" name="Concessionname" class="form-control"
										id="concessionname" onkeypress="HideError(this)"
										maxlength="29" tabindex="2"
										value='<logic:present name="ConcessionList"><bean:write name="ConcessionList" property="concesionName"/></logic:present>'></input>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Concession
									Type</label>
								<div class="col-xs-7">
									<select id="concessiontype" name="concessiontype"
										class="form-control">
										<option value="">----------Select----------</option>
									</select>
								</div>
							</div>
							 <div class="form-group clearfix ">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Applicable For</label>
					<div class="col-xs-7" style="padding-left: 5px;">
					<!-- <label class='radio-inline'><input type='checkbox' value='TF' name='transportfee' >Transport-Fee</label> -->
					<label class='radio-inline'><input type='Checkbox' value='SF' name='schoolfee' checked="checked">School-Fee</label>
					</div>
				</div>
				
					<div class="form-group clearfix ">
											<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Concession By</label>
										<div class="col-xs-7">
											<label class='radio-inline col-xs-8' style="margin-left: 10px;"><input type='radio' value='P' class="concession" name='concession' checked>Percentage Wise</label>
											<label class='radio-inline col-xs-8'><input type='radio' value='A' class="concession" name='concession' >Amount Wise</label>
										</div>
								</div>
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
										<textarea style="resize:none" rows="3" cols="25"  class="form-control" tabindex="3"	 name="description" class="form-control" id="description"><logic:present name="ConcessionList"><bean:write name="ConcessionList" property="description"/></logic:present></textarea>
									</div>
								</div>
						</div>
					</div>
							<div class="modal-footer">
						<span id="save" class="buttons button-blue">Save</span>
          				<span class="buttons button-simple" data-dismiss="modal">Close</span>
       				 </div>
				</div>
			</div>
			<!-- <script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script> -->

			<input type="hidden" id="hidstatus" name="hidstatus" /> <input
				type="hidden" id="currentstatus" name="currentstatus"
				value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />

			<input type="hidden" id="hiddenlocId"
				value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>'></input>

			<input type="hidden" id="hiddensearchvalue"
				value='<logic:present name="historysearchvalue" scope="request"><bean:write name="historysearchvalue"/></logic:present>'></input>

			<input type="hidden" id="hiddenstatus"
				value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>'></input>

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="col-md-6"
					style="font-family: Roboto, sans-serif; font-size: 13px; color: #000; margin-top: 20px;">
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Branch</label>
						<div class="col-xs-7">
							<select id="locationname" name="locationnid" class="form-control"
								required>
								<logic:present name="locationList">
									<logic:iterate id="Location" name="locationList">
										<option
											value="<bean:write name="Location" property="locationId"/>"><bean:write
												name="Location" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
							</select>
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Search</label>
						<div class="col-xs-7">
							<input type="text" class="form-control" id="searchvalue"
								autocomplete="off" Placeholder="Search......">
						</div>
						<!-- <span
							class="input-group-btn">
							<button class="btn btn-default" type="button" id="search">
								<i class="fa fa-search"></i>
							</button>
						</span> -->
					</div>
					
					<div class="form-group clearfix">
						<div class="col-xs-5"></div>
						<div class="col-xs-7" style="text-align: left;">
							<span class="buttons" id="search" style="font-weight: normal;">Search</span>
							<span class="buttons" id="resetbtn" style="font-weight: normal;">Reset</span>
						</div>
					</div>
				</div>
				<div class="col-md-6"
					style="font-family: Roboto, sans-serif; font-size: 13px; color: #000; margin-top: 20px;">
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Status</label>
						<div class="col-xs-7">
							<select id="status" name="status" class="form-control">
								<option value="Y">Active</option>
								<option value="N">InActive</option>
							</select>
						</div>
					</div>
				</div>

				<div class="panel-body"
					style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">
					<table class="table" id="allstudent">
						<thead>
							<tr>
								<th><input type='checkbox' name='selectall' id='selectall'
									style='text-align: center' onClick='selectAll()' /></th>
								<th>Branch</th>
								<th>Concession Name</th>
								<th>Description</th>
								<th>Remarks</th>
							</tr>
						</thead>
						<tbody>
						</tbody>

					</table>
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

		<!-- <script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script> -->
</body>
</html>