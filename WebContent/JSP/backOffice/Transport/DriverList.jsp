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



<script type="text/javascript" src="JS/backOffice/Transport/DriverEditingPage.js"></script>


<style>
.glyphicon:hover{

cursor: pointer;
}

#excelDownload :hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
#iconsimg:hover {
	cursor: pointer;
}

.ui-dialog{
    position:fixed;
    top:129px !important;          
}
.ui-dialog-buttonset {
    border-radius: 3px !important;
    cursor: pointer !important;
    font-family: Roboto,sans-serif !important;
    }
    
</style>

</head>

<body>
<!-- <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div> -->
	<div class="content" id="div1">
	<div id="dialog"></div>
	               <div class="searchWraps">
	
		<div class="col-md-12 input-group" id="div2">

			<p class="transportheader">
				<span class="glyphicon glyphicon-user" ></span>&nbsp;&nbsp;<span id="pageHeading">Drivers List</span>
			</p>
		</div>
		</div>
			


	<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
	<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		
		
		<div class="errormessagediv1" align="center"
				style="display: none; text-align: center;">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 37%; font-size: 10pt; color: red;"><span
						class="validateTips1"></span></a>
				</div>
			</div>

		<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
		<div class="panel panel-primary">
			<div class="panel-heading" role="tab" id="headingOne">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Drivers
						List
					</h3></a>
					
				
				<div class="navbar-right" style="margin-right:-3px;">
				        <span id="add" class="buttons">New</span> 
						<span class="buttons" id="editdriver">Modify</span>
						<span class="buttons" id="inactive">InActive</span>
						
						<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 >Download </span>
						
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
   value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>"/>

   <input type="hidden" id="historylocId" 
   value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>"/>
   
   <input type="hidden" id="historystatus" 
   value="<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>"/>
   
   <input type="hidden" id="historysearch" 
   value="<logic:present name="historysearch" scope="request"><bean:write name="historysearch"/></logic:present>"/>

			<div id="collapseOne" class="accordion-body collapse in">
			  <div class="panel-body">
                   <div class="col-md-6" id="txtstyle">
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Branch</label>
							<div class="col-xs-7">
								<select id="locationname" name="locationnid" class="form-control" required>
									
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
			<input type="text" name="searchname" id="searchname" class="form-control" Placeholder="Search......"style="height: 35px;">
			</div>
			<!-- <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search" value="Submitform">
					<i class="fa fa-search"></i>
				</button>
			</span> -->
		</div>
		<div class="form-group clearfix"> 
					<div class='col-xs-5'>
					</div>
					<div class='col-xs-7'  style="text-align: left;">
					<span class="buttons" id="search" style="font-weight: normal;">Search</span>  
					<span class="buttons" id="resetbtn" style="font-weight: normal;">Reset</span>
					</div></div>
                   </div>
				<div class="col-md-6" id="txtstyle">
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
			  </div>
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
						<%-- <logic:present name="driverList" scope="request"> --%>
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' style='text-align:center' id='selectall'  /></th>
							<th>Driver Name</th>
							<th>Date Of Joining</th>
							<th>Mob No.</th>
							<th>DL No.</th>
							<!-- <th>DL Issued Date</th> -->
							<th>DL Experied Date</th>
							<th>Remarks</th>
							</tr>
							</thead>
							<tbody>
							<%-- <logic:iterate id="driverList" name="driverList">
								<tr>
								<td><input type='checkbox' name='select' class='select' style='text-align: center' id='<bean:write name="driverList" property='driverCode'/>'/></td>
								<td><bean:write name="driverList" property='driverName'/></td>
								<td><bean:write name="driverList" property='dateofJoin'/></td>
								<td><bean:write name="driverList" property='mobile'/></td>
								<td><bean:write name="driverList" property='drivingliecenseNo'/></td>
								<td><bean:write name="driverList" property='dl_issued_date'/></td>
								<td><bean:write name="driverList" property='dl_validity'/></td>
								</tr>
							</logic:iterate> --%>
							</tbody>
						</table>
					<%-- </logic:present> --%>
						
					
					<div class='pagebanner' style="position: relative !important;">
					<select id='show_per_page'>
						<option value='50'>50</option>
						<option value='100'>100</option>
						<option value='200'>200</option>
						<option value='300'>300</option>
						<option value='400'>400</option>
						<option value='500'>500</option>
					</select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks' style="margin-bottom: 10px;"></div>
				
				</div>
			
			</div>
		</div>
	</div>
</div>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>