<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<title>eCampus Pro</title>

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">
<script src="JS/backOffice/Reports/ExpenditureReport.js"></script>

<style>
.modal-body {
	text-align: center;
}
</style>
<style>
.buttons {
	vertical-align: -28px;
}


</style>
</head>



<body>
<form id="stuattnid" action="reportaction.html?method=getExpenditureReport" method="post" enctype="multipart/form-data">
<div id="loder" style="display: none; position:absolute; height: 200px;width: 200px;left: 0;right: 0;top: 0;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 50px;position: absolute;left: 0;right: 0;height: 50px;bottom: 0;top: 0;margin: auto;" src="./images/ajax-loader-blue.gif"/></div>
	<div class="col-md-10"
		style="font-family: Roboto Medium; font-size: 20pt; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px; color: #07aab9;">

		<p>
			<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">Income and Expenditure</span>
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
							&nbsp;&nbsp;Income and Expenditure Report
						</h3></a>
					

				            <div class="navbar-right">
				          <!--    <span class="buttons" id="print" >Print</span> -->
					      <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" style="vertical-align: 13px;">Download </span>
         	            
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
<input type="hidden" name="userid"	id="userid" value="<logic:present name="parentid" ><bean:write name="parentid"  /></logic:present>" />			
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">
				<div class="tab-row" style="margin-bottom: 125px;">
					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locationid" name="locationname" class="form-control" required>
											<option value="">----------Select----------</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
									
								</div>
					<input type="hidden" id="hiddenlocation"  name="locName" value='<logic:present name='locName' scope='request'><bean:write name='locName'/></logic:present>'/>
						
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">To Date</label>
								<div class="col-xs-7">
									<input type="text" name="todate" id="todate" readonly="readonly" class="form-control"/>
								</div>
						</div>
					</div>

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">From Date
								</label>
								<div class="col-xs-7">
									<input type="text" name="fromdate" id="Fromdate" readonly="readonly" class="form-control"/>
								</div>
								</div>
						</div>
						
						<div class="col-md-12" style="text-align:center">
							<button type="button" class="btn btn-info"
								id="search" onclick="return validate()">Search</button>
							<!-- data-toggle="modal" data-target="#myModal" -->
							<br />	
						
						</div>
					</div>
					<input type="hidden" name="userhidden" class="userhiddenclass" id="userhiddenid" value='<logic:present name="parentid"><bean:write name="parentid" /></logic:present>'></input>
					<input type="hidden" id="successid" value='<logic:present name="success"><bean:write name="success" /></logic:present>'></input>
					
				
							
					</div>
					
					
							
				
							
						
						<div id="allstudent">
								<logic:present name="expenditure">
                      				<display:table class="table" id="allstudent"
									name="requestScope.expenditure">

								    	<display:column property="sno" sortable="true"
										title="S.No	<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="date" sortable="true"
										title="Date	<img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="opeingBalance" sortable="true"
										title="Opening Balance <img src='images/sort1.png' style='float: right'/>"
										></display:column>

									<display:column property="feeAmount" sortable="true"
										title="Income<img src='images/sort1.png' style='float: right'/>"></display:column>
										
										<display:column property="expenditure" sortable="true"
										title="Expenditure <img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
										
										<display:column property="balance" sortable="true"
										title="Balance Amount<img src='images/sort1.png' style='float: right'/>"
										
										></display:column>
										
								

									
								</display:table>

							</logic:present>
						</div>
						
							
					
				</div>
			</div>
			</div>

	</form>
	<!-- Button trigger modal -->
	<span>&nbsp;</span>
	
	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>

</html>