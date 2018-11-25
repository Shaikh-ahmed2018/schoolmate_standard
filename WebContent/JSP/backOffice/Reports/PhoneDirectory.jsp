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
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>

<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>

<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>

<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
 --><link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="JS/backOffice/Reports/PhoneDirectoryReport.js"></script>
<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">


<style>
.save:hover {
	cursor: pointer;
}
</style>

<style>
#list:hover {
	cursor: pointer;
}
</style>
<style>
.buttons {
	vertical-align: -28px;
}
</style>

</head>



<body>
	
		<div class="col-md-10"
			style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
			<p style="margin: 20px 0px;">
				<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">Phone
					Directory </span>
			</p>
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

			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary" style="margin-top: -20px;">
					<div class="panel-heading" role="tab" id="headingOne">

						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne"
							style="color: #000000; vertical-align: text-top"><h3
								class="panel-title">
								<i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Phone Directory
							</h3> </a>



						<div class="navbar-right">
						<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="PHDDWD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
                                       	<span class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" data-toggle="tooltip"
								data-placement="bottom" style="top: -8px;">Download </span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

						


						</div>

						<script>
							$(document).ready(function() {
								$('[data-toggle="tooltip"]').tooltip();
							});
						</script>

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


					<input type="hidden" name="userid" id="userid"
						value="<logic:present name="parentid" ><bean:write name="parentid"  /></logic:present>" />








					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">

						<div class="panel-body" id="tabletxt" style="margin-top: 25px;">

							<div class="col-md-6" id="txtstyle">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School
										Name</label>
									<div class="col-xs-7">
										<select id="location" name="location" class="form-control"
											required>
											<option value="all">----------Select----------</option>
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
										style="text-align: right; line-height: 35px;">Category
									</label>



									<div class="col-xs-7">
										<select id="category" name="category" class="form-control"
											required>
											<option value="all">----------Select----------</option>
											<option value="admin">Admin</option>
											<option value="students">Students</option>
											<!-- <option value="teachers">Teachers</option> -->
										</select>
									</div>
								</div>

							</div>
							<div class="col-md-6" id="txtstyle">



								<%--   <div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;"> </label>
									<div class="col-xs-7">
									<input type="text" name="departmentname" class="form-control" id="departmentid" 
									value='<logic:present name="editlist"><bean:write name="editlist" property="depName" /></logic:present>'></input>
									</div>
								</div> --%>



								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Name </label>
									<div class="col-xs-7">
										<select id="selectname" name="name" class="form-control"
											required>
											<option value="">ALL</option>
											<logic:present name="streamlist">
												<logic:iterate id="AccYear" name="streamlist">
													<option
														value="<bean:write name="AccYear" property="streamId"/>">
														<bean:write name="AccYear" property="streamName" />
													</option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								<div class="col-xs-4"></div>
								<div class="col-xs-8">
									<button type="submit" class="btn btn-info" id="search"
										onclick="return validate()"
										style="margin-top: 11px; margin-bottom: 25px;">Search</button>
								</div>
							</div>
							<br /> <input type="hidden" id="plocation"
								value='<logic:present name="plocation"><bean:write name="plocation" />
													</logic:present>'></input>

							<input type="hidden" id="successid"
								value='<logic:present name="success"><bean:write name="success" />
													</logic:present>'></input>
							<!-- data-toggle="modal" data-target="#myModal" -->
							<br />
							<div class="col-md-12 selecteditems">
								<br /> <input type="hidden" id="hcategory" name="hcategory"
									value='<logic:present name="selectedvalue" scope="request"><bean:write name="selectedvalue" scope="request" property="category" /></logic:present>'>

								<input type="hidden" id="hname" name="hname"
									value='<logic:present name="selectedvalue" scope="request"><bean:write name="selectedvalue" scope="request" property="name" /></logic:present>'>


								<span><b>Category :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="selectedvalue">
										<bean:write name="selectedvalue" property="category" />

									</logic:present></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Name
										:</b></span>&nbsp;&nbsp;&nbsp;<span> <logic:present
										name="selectedlist">
										<bean:write name="selectedlist" property="name" />

									</logic:present>

								</span>
							</div>
							<br />
							<%--  <div id="allstudent">
								<logic:present name="phonedirectorylist">
                      				<display:table class="table" id="allstudent"  pagesize="10"
									name="requestScope.phonedirectorylist"
									requestURI="/phonedirectory.html?method=phonedirectoryAction?">

								    	<display:column property="count" sortable="true"
										title="S.No	<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="name" sortable="true"
										title="Name<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="category" sortable="true"
										title="Category<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="phone" sortable="true"
										title="Contact Number <img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
										
										<display:column property="email" sortable="true"
										title="Email Id<img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
										
										<display:column property="address" sortable="true"
										title="Address<img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
						 --%>
							<%-- <display:column property="attdancedate" sortable="true"
										title="Attendance Date<img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
										
										<display:column property="attendancestatus" sortable="true"
										title="Attendance Status <img src='images/sort1.png' style='float: right'/>"
										
										></display:column> --%>


							<%-- 			</display:table>

							</logic:present>
							
						</div> 
						 --%>
							
								<table class="table" id="allstudent">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Name</th>
											<th>Category</th>
											<th>Contact Number</th>
											<th>Email Id</th>
											<th>Address</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
							

						</div>


					</div>
				</div>
			</div>
		</div>


</body>

</html>
