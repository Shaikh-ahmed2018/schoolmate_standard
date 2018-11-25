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


	
<script src="JS/backOffice/Library/TransferSubscriberDetails.js"></script>

<style>
#editStudent:hover {
	cursor: pointer;
}

#trash:hover {
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

.class1 {
	width: 41.66666667%;
	margin-left: -65px;
	margin-top: 12px;
}

.fsetRight {
	width: 610px;
	margin-bottom: 15px;
	border: 0.5px solid #ccc;
	padding: 5px;
}

.fieldset {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	min-height: 99px;
}

.fieldset1 {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	min-height: 98px;
}

.fieldset2 {
	display: block;
	padding-bottom: 0.625em;
	padding-left: 55px;;
	padding-right: 0px;
	border: 0.5px solid #ccc;
	min-height: 97px;
}

legend {
	display: inline-block;
	width: auto;
	padding: 0;
	margin-bottom: 0px;
	font-size: 16px;
	line-height: inherit;
	color: #333;
	border: 0;
}

.with {
	margin-left: 60px;
}

.page {
	margin-top: 25px;
}

.search1 {
	width: 186px;
	left: -15px;
	color: #6f6f6f;
	font-size: 14px;
	font-family: Roboto,sans-serif;
}

.navbar-right {
	right: 7px;
	top: 0;
	float: none !important;
	display: block;
	bottom: 0;
	vertical-align: middle;
}

.staffstand {
	display: none;
}

.stafftable {
	display: none;
}

.otherstable {
	display: none;
}

.setsearch {
	padding-left: 45px;
}

#others th:nth-child(6){
  text-align: left;
  width:28%;
  }
  #others th:nth-child(5){
  text-align: left;
  width:20%;
  }
    #others th:nth-child(2){
  text-align: left;
  width:10%;
  }
  #others td:nth-child(2){
  text-align:center;
  }
  #others td:nth-child(7),th:nth-child(7){
  text-align:center;
  }
   #allstudent th:nth-child(2),th:nth-child(4),th:nth-child(5),th:nth-child(6),th:nth-child(7),th:nth-child(8){
  text-align:center;
  }
</style>

</head>

<body>
	<div class="content" id="div1">
		
		     
			<div  id="div2">
			<div class="col-md-12 input-group">
				<p>
					<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span
						id="pageHeading">Transfer Subscriber</span>
				</p>
			  </div>
		 
		</div>
		
		

		<input type="hidden" id="succmsg"
			value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />

		<div id="successmessages" class="successmessagediv"
			style="display: none;">
			<div class="message-item">
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>

		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne">
					<h3 class="panel-title" style="color: #000000;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>
						&nbsp;&nbsp;Transfer Subscriber
					</h3>
				</a>

				<div class="navbar-right">

                         <span class="buttons" id="transfer">Transfer</span></a> 

					<div>
						
					</div>
				</div>
			</div>

			<!-- pop up -->

			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">

					<div class="row">
					 <div id="TransferDialog" style="display: none">
					     <div class="col-md-12">
						<div class="col-md-10"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
                            <p>Are You Sure Want To Transfer?</p>
							<div class="form-group clearfix" style="margin-top:21px;margin-left:-37%;">
							<label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right;left:12px;">Location
									</label>
									<div class="col-xs-7">
										<select  name="location" style="width:151%;"class="form-control" onchange="HideError(this)" id="libloc">
										 <option value="">----------Select----------</option>
											<%-- <logic:present name="libloc" scope="request">
											<logic:iterate id="libloc" name="libloc">
											<option value="<bean:write name="libloc" property="librarylocid"/>"><bean:write name="libloc" property="libraryLocations"/></option>
											</logic:iterate>
										</logic:present> --%>
										
										</select>
									</div>
							</div>

						</div>
						</div>
						<div id="TransferDialog1" style="dispaly:none;">
						<p>Are You Sure Want To Transfer</p>
						</div>
						
						</div>
						
						
						
						
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Branch<font color="red"> *</font></label>
								<div class="col-xs-7">
									<select id="locationname" name="locationnid"
										class="form-control" required>
									
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
							
							
							<div class="form-group clearfix" >
							<label  for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Library Location
									</label>
									<div class="col-xs-7">
										<select  name="liblocation" class="form-control" id="liblocation">
										 <option value="all">ALL</option>
											<%-- <logic:present name="librarylocationsDetails">
											<logic:iterate id="librarylocationsDetails" name="librarylocationsDetails">
											<option value="<bean:write name="librarylocationsDetails" property="librarylocid"/>"><bean:write name="librarylocationsDetails" property="libraryLocations"/></option>
											</logic:iterate>
											</logic:present> --%>
										</select>
									</div>
							</div>
                        
                         
                                <div class="form-group clearfix">
									
                                     <label  for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Subscriber Type
									</label>
									<label style="width: 100px; margin-left: 15px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="student" class="cencession"
										value="Student" checked="checked" />&nbsp;Student
									</label> <label style="width: 81px;"><input type="radio"
										style="top: -2px;" class="radio-inline" name="requested_by"
										class="cencession" id="staff" value="staff" />&nbsp;Staff&nbsp;&nbsp;&nbsp;
									</label> <label style="width: 86px; margin-left: 8px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="others" class="cencession"
										value="others" />&nbsp;Others
									</label>

								</div>
								
								
								<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Search</label>
							<div class="input-group col-xs-7">
								<input type="text" name="searchname" id="searchValue"
									style="margin-left: 15px;width: 275px;" class="form-control"
									Placeholder="Search......" 
									value="<logic:present name='searchnamelistValue' scope='request'><bean:write name='searchnamelistValue'/></logic:present>">
								
							</div>
						</div>
                         
                         	<div class="form-group clearfix">
						          <div class="col-xs-5"></div>
						             <div class="col-xs-7" >
							           <span class="buttons" type="button" id="search"> Search</span>
						              	<span type="reset" class="buttons" id="resetbtn">Reset</span>
						           </div>
						           </div>
                         
                         
						</div>
						
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-7">
									<select id="Acyearid" name="accyear" class="form-control">
										<!-- <option value="all"></option> -->
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
							
								<div class="stand">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Class
								</label>
								<div class="col-xs-7">
								<select name="state" class="form-control" id="classname">
								<option value="all">ALL</option>
								</select>
								</div>
							</div>
							
							
							<div class="form-group clearfix">
											<label for="inputPassword" class="control-label col-xs-5"
												style="text-align: right; line-height: 35px;">Division</label>

											<div class="col-xs-7">
												<select name="state" id="sectionid" class="form-control">
													<option value="">ALL</option>
												</select>
											</div>
										</div>
										
										</div>
										<div class="staffstand">
							            <div class="form-group clearfix">
											<label for="inputPassword"
													class="control-label col-xs-5 standard"
													style="text-align: right; line-height: 35px;">Department</label>
												<div class="col-xs-7">
													<select name="state" style="width:280px; font-size: 14px;margin-left:0px" id="department">
														<option>ALL</option>
													</select>
												</div>
									          </div>
									          
									           <div class="form-group clearfix">
												<label for="inputPassword" class="control-label col-xs-5"
													style="text-align:right; line-height: 35px;">Designation</label>

												<div class="col-xs-7">
													<select name="state" style="width:280px; font-size: 14px;margin-left:0px"
														id="designation">
														<option>ALL</option>
													</select>
												</div>
											</div>
											</div>
											
							    
											
									          
								     </div>
							

	<input type="hidden" id="historylocId" 
	value="<logic:present name='historylocId' scope='request'><bean:write name='historylocId'/></logic:present>"/>
	
	<input type="hidden" id="historyliblocId" 
	value="<logic:present name='historyliblocId' scope='request'><bean:write name='historyliblocId'/></logic:present>"/>
	
	<input type="hidden" id="historysubType" 
	value="<logic:present name='historysubType' scope='request'><bean:write name='historysubType'/></logic:present>"/>
	
	<input type="hidden" id="historyAcyearid" 
	value="<logic:present name='historyAcyearid' scope='request'><bean:write name='historyAcyearid'/></logic:present>"/>
	
	<input type="hidden" id="historyclassId" 
	value="<logic:present name='historyclassId' scope='request'><bean:write name='historyclassId'/></logic:present>"/>
	
	<input type="hidden" id="historysectionid" 
	value="<logic:present name='historysectionid' scope='request'><bean:write name='historysectionid'/></logic:present>"/>
	
	<input type="hidden" id="historysearchValue" 
	value="<logic:present name='historysearchValue' scope='request'><bean:write name='historysearchValue'/></logic:present>"/>
	
	<input type="hidden" id="historydesigId" 
	value="<logic:present name='historydesigId' scope='request'><bean:write name='historydesigId'/></logic:present>"/>				
					
	<input type="hidden" id="historydepId" 
	value="<logic:present name='historydepId' scope='request'><bean:write name='historydepId'/></logic:present>"/>				
					
					
<input type="hidden" id="hidenaccyear" value="<logic:present name='accademic_year' scope='request'><bean:write name='accademic_year'/></logic:present>"/>
					<!-------------- Student table -------------->
					<div class="allstudenttable" style="margin-top:6px;">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<table class='table allstudent' id='allstudent'>
								<thead>
									<tr>
										<th style="text-align: center"><input type='checkbox' name='select' class='selectall' id='selectall' style='text-align: center' /></th>
										<th>Subscriber No</th>
										<th>Name</th>
										<th>Admission No</th>
										<th>Roll No</th>
										<th>Class</th>
										<th>Section</th>
										<th>Library Location</th>
										<th>Status</th>
									</tr>
								</thead>

								<tbody>

								</tbody>

							</table>
							
						</div>
					</div>
					<!------------- Staff table ----------->
					<div class="stafftable" style="margin-top:6px;">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<table class='table allstudent'  id="allstaff">

								<thead>
									<tr>
										<th style="text-align: center"><input type='checkbox' name='select' class='selectall' id='selectstaff' style='text-align: center' /></th>
										<th>Subscriber No</th>
										<th>Name</th>
										<th>Department</th>
										<th>Designation</th>
										<th>Library Location</th>
										<th>Status</th>
									</tr>
								</thead>

								<tbody>

								</tbody>

							</table>
							
						</div>
					</div>
					<!---------------------------- others table------------------------------ -->
					<div class="otherstable" style="margin-top:6px;">
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<table class='table allstudent'  id="others">

								<thead>
									<tr>
									<th style="text-align: center"><input type='checkbox' name='select' class='selectall' id='selectother' style='text-align: center' /></th>
										<th style="width: 109px;">Subscriber No</th>
										<th>Name</th>
										<th style="width: 94px;">Contact</th>
										<th>Mail Id</th>
										<th>Address</th>
										<th>Library Location</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
								
								</tbody>
							</table>
						</div>
					</div>
					<!-- <div class='pagebanner page'> -->
							<div class='pagebanner' style="margin-top:2px;margin-left:15px;">
								<select id='show_per_page'>
								     <option value='50'>50</option>
									<option value='100'>100</option>
									<option value='200'>200</option>
									<option value='300'>300</option>
									<option value='400'>400</option>
									<option value='500'>500</option>
								</select>
							</div>
							<div class='pagination pagelinks' style="font-size:10px;"></div>
							<div style="padding-top: 8px;margin-left:55px;font-weight:bold;"><span  class='numberOfItem' style="margin-left:2%;"></span></div>
				</div>
			</div>
			
					
		</div>
	</div>
	
</body>
</html>