<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>
 <script type="text/javascript" src="JS/backOffice/Student/StudentIdCard.js"></script>

<style>
.modal-body {
	text-align: center; 
}
</style>
</head>

<body>

 
	<div class="col-md-10"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
	
	<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading"> Design Student ID Card</span>
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
		
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				
				<div class="panel panel-primary">

					<div class="panel-heading" role="tab" id="headingOne" >

						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #000000"><h3 class="panel-title" id="beforeparent"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp; Find Student Details
							</h3></a>
						
					</div>
					<!-- pop up -->

					<!-- Filters -->
					
   <input type="hidden" id="historyaccyear" 
			 value='<logic:present name="historyaccyear"  scope="request" ><bean:write name="historyaccyear"/></logic:present>' />					
	
   <input type="hidden" id="historyschoolId" 
			 value='<logic:present name="historyschoolId"  scope="request" ><bean:write name="historyschoolId"/></logic:present>' />
	
   <input type="hidden" id="historystreamId" 
			 value='<logic:present name="historystreamId"  scope="request" ><bean:write name="historystreamId"/></logic:present>' />					

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt" style="padding: 15px;">

							<div class="col-md-6" id="txtstyle">
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Branch</label>
									<div class="col-xs-7">
										<select id="locationname" name="location" class="form-control"
											required>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>">
																   <bean:write name="Location" property="locationName" />
													</option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
							
									
									
								<div class="form-group clearfix clearfix clearfix clearfix clearfix clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Stream</label>
									<div class="col-xs-7">
										<select id="streamId" name="stream" class="form-control" required="required">
											<option value="">-------Select-------</option>
										</select>
									</div>
								</div>
						
							</div>
							<div class="col-md-6" id="txtstyle">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
										<select id="accyear" name="accyear" class="form-control" required="required">
											<option value="">-------Select-------</option>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option value="<bean:write name="AccYear" property="accyearId"/>">
													<bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								<br />
								
							</div>
							
 				<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
					 <table class='table' id='allstudent' width='100%'>
					 <thead>
					 <tr><th>Sl.No.</th>
						<th style="text-align:center">Academic Year</th>
						<th style="text-align:center">Branch</th>
						<th style="text-align:center">Stream Name</th>
						</tr>
						</thead>
						<tbody>
						
						</tbody>
			
						</table>
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks'></div>
						</div>
					</div>
				</div>
			</div>
			<!-- Button trigger modal -->

		
	</div>




	<span>&nbsp;</span>


	<!-- jQuery -->
	<script src="js/jquery.js"></script>


	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>


	<script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</body>

</html>
