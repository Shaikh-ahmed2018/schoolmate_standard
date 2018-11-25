<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <script type="text/javascript" src="JS/backOffice/Settings/editSpec.js"></script>


</head>

<body>

	<div class="content" id="div1">
		<div id="dialog"></div>

		<p>
			<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;<span
				id="pageHeading">Specialization Details</span>
		</p>

		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" /></logic:present>'></input>



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


		<div class="panel panel-primary">
			<div class="panel-heading clearfix ">
				<a data-toggle="collapse" data-parent="#accordion2" href="#classOne"
					style="color: #fff;"><h3 class="panel-title specializations" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Specialization
						Details
					</h3></a>



				<div class="navbar-right" style="right: 2px;">
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SPLADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<a
										href="menuslist.html?method=addSpecialization"><span class="buttons" style="right:1px;">New</span></a>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SPLUPD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span class="buttons" id="editspec">Modify</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SPLDEL" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span class="buttons" id="inactive">InActive</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>



					<!--  <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span> -->


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
			<!-- pop up end -->
			
	<input type='hidden' id='butstatus'>
	
	<input type="hidden" name="curr_loc" id="curr_loc"
	 value='<logic:present name="curr_loc" scope="request"><bean:write name="curr_loc"/></logic:present>'></input>
	
	<input type="hidden" name="currentstatus" id="currentstatus"
	 value='<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>'></input>
			
	<input type="hidden" name="hiddenlocId" id="hiddenlocId"
	 value='<logic:present name="locId" scope="request"><bean:write name="locId"/></logic:present>'></input>
	 
	 <input type="hidden" name="hiddenstreamId" id="hiddenstreamId"
	 value='<logic:present name="streamId" scope="request"><bean:write name="streamId"/></logic:present>'></input>				
	
	<input type="hidden" name="hiddenclassname" id="hiddenclassname"
	 value='<logic:present name="classname" scope="request"><bean:write name="classname"/></logic:present>'></input>
	 
	 <input type="hidden" name="hiddenstatus" id="hiddenstatus"
	 value='<logic:present name="status" scope="request"><bean:write name="status"/></logic:present>'></input>		
			

			<div id="classOne" class="accordion-body collapse in">
				<div class="col-md-6"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">

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
							style="text-align: right; line-height: 35px;"> Stream</label>
						<div class="col-xs-7">

							<select class="form-control" onkeypress="HideError()"
								name="streamId" id="streamId">
								<option value="">ALL</option>
							</select>

						</div>
					</div>

					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;"> Class</label>
						<div class="col-xs-7">

							<select class="form-control" onkeypress="HideError()"
								name="classname" id="classname">
								<option value="">ALL</option>
							</select>

						</div>
					</div>

				</div>

						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top: 2%;">
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
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

						<%-- <logic:present name="SpecializationList" scope="request"> --%>

							<table class="table" id="allstudent">
								<thead>
									<tr>
										<th><input type='checkbox' name='selectall'
											id='selectall' style='text-align: center'
											/></th>
										<th>Branch</th>
										<th>Stream</th>
										<th>Class</th>
										<th>Specialization</th>
										<th>Remarks</th>
									</tr>
								</thead>
								<tbody>
									<%-- <logic:iterate name='SpecializationList'
										id="SpecializationList">
										<tr>
											<td><input type='checkbox' name='select' class='select'
												style='text-align: center'
												id='<bean:write name='SpecializationList' property="spec_Id"/>' /></td>
											<td><bean:write name='SpecializationList'
													property="locationName" /></td>
											<td><bean:write name='SpecializationList'
													property="stream_Name" /></td>
											<td><bean:write name='SpecializationList'
													property="class_Name" /></td>
											<td><bean:write name='SpecializationList'
													property="spec_Name" /></td>
											<td><bean:write name='SpecializationList'
													property="remarks" /></td>
										</tr>
									</logic:iterate> --%>
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
						<%-- </logic:present> --%>

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