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

 <script type="text/javascript" src="JS/backOffice/Reports/ReleivingOrder.js"></script>
	


</head>

<body>

	<div class="col-md-10">
		<div id="dialog"></div>
		<p>
			<img src="images/addstu.png" style="vertical-align: top;"/>&nbsp;<span id="pageHeading">Relieving Order
			</span>
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
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #000000;vertical-align: text-top"><h3 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Relieving Order
						</h3>	 
						</a>
						
						

						<div class="navbar-right">
						<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" >Download </span>

						</div>
						
						<script>
							$(document).ready(function() {
								$('[data-toggle="tooltip"]').tooltip();
							});
						</script>
				
				</div>
				
				
				<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel">
						<div class="modal-dialog dialog" role="document">
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
					</div> -->
				
				
		      <input type="hidden" name="userid"	id="userid" value="<logic:present name="parentid" ><bean:write name="parentid"  /></logic:present>"  />			
	
					<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					
					<div class="panel-body" id="tabletxt" >
					<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
										
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								     	<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Department</label>
									<div class="col-xs-7">
										<select id="depName" name="depName" class="form-control" required>
											<option value="All">ALL</option>
											<logic:present name="deptlist">
												<logic:iterate id="deptname" name="deptlist">
													<option value="<bean:write name="deptname" property="depId"/>"><bean:write name="deptname" property="depName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
                          <div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Resignation Date</label>
								<div class="col-xs-7">
									<input type="text" name="resignationdate" id="dateofRelievingId"
										readonly="readonly" placeholder="Click Here."
										class="form-control" value="<logic:present name="leavedatails" ><bean:write name="leavedatails" property="todate"/></logic:present>">
								</div>
							</div>					
					 </div>
					
					    <div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
                           <div class="form-group clearfix">
						  <label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Staff Type</label>	
								<div class="col-xs-7">
									<select name="teachertype" id="teachertype" class="form-control">
										<option value="all">ALL</option>
											<option value="TEACHING">Teaching</option>
											<option value="NON-TEACHING">Non-Teaching</option>
											<option value="GENERAL">General</option>
											<option value="OFFICE STAFF">Office Staff</option>
									</select>
								</div>	
							</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Designation</label>
									<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()" 
											name="desgname" id="designameid">
											<option value="all">ALL</option>
												<logic:present name="designattionlist">
												<logic:iterate id="designame" name="designattionlist">
													<option value="<bean:write name="designame" property="desgid"/>"><bean:write name="designame" property="desgname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									
									</div>
									
								</div>
				
							</div>
							 
							<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' style='text-align:center' id='selectall' /></th>
							<th>Registration Id</th>
							<th>Staff Name</th>
							<th>Department</th>
							<th>Designation</th>
							<th>Mobile No </th>
							<th>Email</th>	
							</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
							
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
                      	<span  class='numberOfItem'></span>	
                      	</div><div class='pagination pagelinks'></div>
			
		         	</div>
					
			<br />
				</div>

			</div>
		</div>
	</div>

</body>

</html>
