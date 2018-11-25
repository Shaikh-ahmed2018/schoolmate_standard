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

<script type="text/javascript" src="JS/backOffice/Transport/VehicleList.js"></script>

<style>
.download:hover{

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

<body>

	<div class="content" id="div1">
	 <div id="dialog"></div>
	<div class="searchWraps">
		<div class="col-md-12 input-group" id="div2">

			<p class="transportheader">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Vehicle
					Master</span>
			</p>
		</div>


			<!-- <form id="myForm" action="menuslist.html?method=vehicleList"
				method="post"> -->
				

				<%-- <input type="hidden" name="searchterm" class="searchtermclass"
					id="searchexamid"
					value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" /></logic:present>'></input> --%>

			</form>
		</div>

		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		
		

		<!-- <div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				Warning
				<span
					class="validateTips"><a href="#" class="msg-success bg-msg-succes"></span></a>
			</div>
		</div> -->
		
		
		<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a href="#" style="color: #fff;"><h3
						class="panel-title" id="vehicle" style="color: #000000; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Vehicles 
						List
					</h3></a>
				<div class="navbar-right" >
                     
                     <logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="VEHCRE" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									 <span class="buttons" id="add">New</span> 
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
                        
                  

                     <logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="VEHUPD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span class="buttons" id="editVehicle" style="cursor: pointer">Modify</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
					
					   <logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="VEHDEL" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span class="buttons" id="deleteVehicle">InActive</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
					
						
						
						

				</div>
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

     <input type="hidden" id="currentstatus"
    value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>"/>

    <input type="hidden" id="historylocId"
    value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>"/>

    <input type="hidden" id="historystatus"
    value="<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>"/>
    
    <input type="hidden" id="historysearch"
    value="<logic:present name="historysearch" scope="request"><bean:write name="historysearch"/></logic:present>"/>

			<div id="vehicleclose" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					
					<div class="col-md-6">
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
					<input type="text" name="searchname" id="searchname"style="height: 35px;" class="form-control"Placeholder="Search......">
					</div>
					<!-- <span class="input-group-btn">
						<button class="btn btn-default" type="button" id="search">
							<i class="fa fa-search"></i>
						</button>
					</span> -->
					
				</div>
					<div class="form-group clearfix"> 
					<div class='col-xs-5'>
					</div>
					<div class='col-xs-7' style="text-align: left;">
					<span class="buttons" id="search" style="font-weight: normal;">Search</span>  
					<span class="buttons" id="resetbtn" style="font-weight: normal;">Reset</span>
					</div></div>
					</div>
					
					 <div class="col-md-6">
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
					
					<!-- <table class="table" id="allstudent"> -->


					<%-- <logic:present name="getvehiclelist" scope="request">
						<display:table class="table" id="allstudent" name="getvehiclelist"
							decorator="com.centris.campus.decorator.VehicleDecorator"
							
							requestURI="menuslist.html?method=vehicleList">

						 <display:column property="check" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" /> 
								
								 <display:column  
									
									title="<input type='checkbox' name='selectall' style='text-align:center' id='selectall'/>"><input type='checkbox' name='select' class='select' style='text-align:center' id='${allstudent.vehiclecode}'  /></display:column> 
								
								
								
								
							<display:column property="vehicleregno" sortable="true"
								title="Registration No<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="vehiclename" sortable="true"
								title="Vehicle Name<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="enginenumber" sortable="true"
								title="Engine Number<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="vehicletype" sortable="true"
								title="Vehicle Type<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="taxpaid" sortable="true"
								title="Tax Issued Date<img src='images/sort1.png' style='float: right'/>" />
								
								<display:column property="taxexpirydate" sortable="true"
								title="Tax Expiry Date<img src='images/sort1.png' style='float: right'/>" />
								
					 		
					

						</display:table>
						
						<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	
							</div><div class='pagination pagelinks'></div>
					
					</logic:present> --%>
					
					<logic:present name="getvehiclelist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' style='text-align:center' id='selectall'/></th>
							<th>Registration No</th>
							<th>Vehicle Name</th>
							<th>Vehicle Type</th>
							<th>Insurance Exp. Date</th>
					    	<th>Driver Name</th>
					    	<th>Route Name</th>
							<th>Remark</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="getvehiclelist" name="getvehiclelist">
								<tr>
								<td><input type='checkbox' name='select' class='select' style='text-align:center' id='<bean:write name="getvehiclelist" property='vehiclecode'/>' /></td>
								<td><bean:write name="getvehiclelist" property='vehicleregno'/></td>
								<td><bean:write name="getvehiclelist" property='vehiclename'/></td>
								<td><bean:write name="getvehiclelist" property='vehicletype'/></td>
								<td><bean:write name="getvehiclelist" property='permitvalidity'/></td>
								<td><bean:write name="getvehiclelist" property='driverName'/></td>
								<td><bean:write name="getvehiclelist" property='routename'/></td>
								<td><bean:write name="getvehiclelist" property='reson'/></td>
								
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	</div>
							
							<div class='pagination pagelinks'></div>
					
					
					
				</div>
				<br />
			</div>
		</div>
	</div>
<!-- <script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script> -->
	<!-- <script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script> -->
</body>
</html>