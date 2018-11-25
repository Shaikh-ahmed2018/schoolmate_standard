<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">
<head>

<script src="JS/backOffice/Reports/FeeCollectionReport.js"></script>


<style>
.modal-body {
	text-align: center;
}
</style>
<style>

.pagebanner{
left: 20px !important;
}
.pagination{
margin-right: -10px;
}
#allstudent tfoot tr td{
    border-right: 1px solid #ddd;
    border-left: 1px solid #ddd;
    border-bottom: 1px solid #ddd;
}
.clearfixes{
	margin-top: 10px;
	margin-bottom: 10px;
}
</style>
</head>



<body>

<div id="loder" style="display: none; position:absolute; height: 200px;width: 200px;left: 0;right: 0;top: 0;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 50px;position: absolute;left: 0;right: 0;height: 50px;bottom: 0;top: 0;margin: auto;" src="./images/ajax-loader-blue.gif"/></div>
	<div class="col-md-10"
		style="font-family: Roboto Medium; font-size: 20pt; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px; color: #07aab9;">

		<p>

			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Fee Collection </span>

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
					class="successmessage"></span></a>
			</div>
		</div>
		
		<p id="parent1" style="display: none;">
			<a href="">Expand all</a>
		</p>
		<div class=" -group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">

					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #000000"><h3 class="panel-title" id="beforeparent"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Fee Collection
						</h3></a>
					
<input type="hidden" id="hiddenstartdate" value="<logic:present name="startdate"><bean:write name="startdate"/></logic:present>">
  <input type="hidden" id="hiddenenddate" value="<logic:present name="enddate"><bean:write name="enddate"/></logic:present>">
	


				 <div class="navbar-right">
				            <!--  <span class="buttons" id="print" >Print</span> -->
				      <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="FEECOLDWD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">      
					                      <span  class="buttons" id="download" data-toggle="modal" data-target="#myModal" style="vertical-align: 13px;">Download </span>
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

			<!-- Filters -->

			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt">
				
					<div class="col-md-6 clearfixes" >
						<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="location" name="location" class="form-control" required>
										
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
								style="text-align: right; line-height: 35px;"> Class</label>
							<div class="col-xs-7">
							<select class="form-control" onkeypress="HideError()" 
									name="classname" id="class">
									<option value="">----------Select----------</option>
							</select>
							</div>
						</div>
						  
						  <div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								id="inputnames" style="text-align: right;">Payment Type</label>
							<div class="col-xs-7">
								<select id="PaymentType" name="" class="form-control"
									>
									<option value="ALL">----------Select----------</option>
									<option value="ONLINE">ONLINE</option>
									<option value="OFFLINE">OFFLINE</option>
									
								</select>
							</div>
						</div>
						
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								id="inputnames" style="text-align: right;">Start Date</label>
							<div class="col-xs-7">
								<input type="text" name="date" readonly="readonly" class="form-control" placeholder="Click Here."  id='start' >
							</div>
						</div>
						
					<div class="form-group clearfix">
						<div class="col-xs-5">
						</div>
						<div class="col-xs-7">
							<span class="buttons" id="searchs">Search</span>
			    			<span class="buttons reset">Reset</span>
						</div>
					</div>
						
					</div>

					<div class="col-md-6 clearfixes">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Academic Year</label>
							<div class="col-xs-7">
								<select id="accyear" name="accyear" class="form-control">
									<logic:present name="AccYearList">
										<logic:iterate id="AccYear" name="AccYearList">
											<option
												value="<bean:write name="AccYear" property="accyearId"/>">
												<bean:write name="AccYear" property="accyearname" />
											</option>
										</logic:iterate>
									</logic:present>
								</select>
							</div>
						</div>
					

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Division</label>
							<div class="col-xs-7">
								<select id="section" name="section" class="form-control">
									<option value="all">----------Select---------</option>
								</select>
							</div>
						</div>
						
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Term</label>
							<div class="col-xs-7">
								<select id="termName" name="termName" class="form-control">
									<option value="">----------Select---------</option>
								</select>
							</div>
						</div>
						
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">End Date</label>
							<div class="col-xs-7">
								<input type="text" name="date" readonly="readonly" class="form-control" placeholder="Click Here." id='end' >
							</div>
						</div>
						
						<div class="form-group clearfix paymenttype" style="display:none;">
							<label for="inputPassword" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Pay Mode</label>
							<div class="col-xs-7">
							<select class="form-control paymode" name="" id="paymode">
									<option value="ALL">----------Select----------</option>
									<option value="Cash">Cash</option>
									<option value="Card">Card</option>
									<option value="Card+Cash">Card+Cash</option>
									<option value="Cheque">Cheque</option>
									<option value="DD">DD</option>
									
							</select>
							</div>
						</div>
							
						<div class="form-group clearfix paymenttypeonline" style="display:none;">
							<label for="inputPassword" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Pay Mode</label>
							<div class="col-xs-7">
							<select class="form-control paymode" name="" id="paymode">
									<option value="all">----------Select----------</option>
									<option value="Online">Online</option>
									
							</select>
							</div>
					 </div>
							
						
				<!-- 		<div class="col-xs-4"></div>
					<div class="col-xs-8">
						<button type="button" class="btn btn-info " id="search"  Style ="margin-bottom: 9px;">Search</button>
					</div> -->
					</div>
					
					</div>
					
					<div id="studentlisttable" ></div>
					<div id="pagebars" style="display: none"></div>
				</div>
			</div>
			</div>
		</div>
	<!-- Button trigger modal -->
	<span>&nbsp;</span>
	
	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>

</html>
