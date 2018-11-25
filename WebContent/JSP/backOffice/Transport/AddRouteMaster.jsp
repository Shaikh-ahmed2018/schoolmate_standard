<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	
<script type="text/javascript" src="JS/backOffice/Transport/addrouteMaster.js"></script>

<style>
.input-group-addon{
padding: 4px 12px;
}
.save:hover {
	cursor: pointer;
}
 #myDialog{
 height : 400px !important;
	overflow-y: scroll;

 } 
.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons{
width : 600px !important;
height : 500px !important;
}

</style>

<style>
#list:hover {
	cursor: pointer;
}
.add-on{
position: absolute;
top:1px;
right: 8px;
}
</style>

<style>
.buttons{

vertical-align: 0px;

}



.stagebutton{ padding: 5px;
    font-size: 14px;
  
    vertical-align: 5px;
    color: #fff;
    border-radius: 3px;
    cursor: pointer;
  
    }
    
.stagetable{
max-height:350px !important;
overflow-y: scroll;

}
</style>


</head>

<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -190px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: 0;margin: auto;" src="./images/ajax-loading.gif"/></div>
      <div id="myDialog" style="display: none;">
			<br/>
			<input type="checkbox" name="selectBoxD" id="selectAll" class="selectAll" id="displayid" value=""/> Select All Stages
			<table id="tableid" class="table table-bordered">
		 			<tr>
					<th class="headth">Select</th>
					<th class="headth">Stages</th>
					</tr>
					 <logic:present name="StageDetails" scope="request">
					 <logic:iterate id="StageDetails" name="StageDetails" scope="request">
					<tr>
					<td><input type="checkbox" name="selectBox"  class="selectBox"  value="<bean:write name="StageDetails" property="stageCode"  />"></input></td>
					<td><bean:write name="StageDetails" property="stageName" /></td>
					</tr>
					</logic:iterate>
					</logic:present>
					</table>

</div>
	<div class="content" id="div-main">
		<p class="transportheader">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="Route" scope="request"><bean:write name="Route"></bean:write></logic:present></span>
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


	<input type="hidden" id="historystatus"
    value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>'></input>
    
    <input type="hidden" id="historysearch"
    value='<logic:present name="historysearch" scope="request"><bean:write name="historysearch"/></logic:present>'></input>	

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #000000;vertical-align: text;"><h3 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;New Route
							Details
						</h3></a>
					

						<div class="navbar-right">
						
							<span class="buttons"  id="save" >Save</span> 
							
		 					<span class="buttons" id="list" >Back</span>
						
						</div>
					
				</div>
				
				
				
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">

						<div class="col-md-6" id="txtstyle">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Route No  <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<input type="text" name="routeNo" id="routeNo"  onclick="HideError(this)" maxlength="29" tabindex="1"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="routeNo"></bean:write></logic:present>'
										 class="form-control" onblur="checkRouteNo()" />
								</div>
							</div>
							



							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Route
									Short Name </label>
								<div class="col-xs-7">
									<input type="text" name="routeLogicName" id="routeLogicName" tabindex="3"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="routeLogicName"></bean:write></logic:present>'
										maxlength="6" class="form-control" />
								</div>
							</div>

						
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Start Time<font color="red">*</font></label>
								<div class="col-xs-7">
									<div style="width:100%;" class="input-group date form_time col-md-8" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii"><input id="starttime1" class="form-control inputcolor starttime1" type="text" name="starttime1" value="" readonly="" placeholder="Click Here" ><span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span></div>
								</div>
								</div>
								
							<%-- <div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Start
									Time <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<!-- <div id="datetimepicker3" class="input-append"> -->
										<input type="text" data-format="hh:mm:ss" size="8"
											readonly="readonly" name="starttime" id="starttime" onclick="HideError(this)"
											class="form-control"
											value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="stratTime"></bean:write></logic:present>' /><span class="add-on"><!-- <img src="images/time2.png" width="30" height="8"  align="top"/> -->
													<img src="./images/time1.jpg" width="25" height="25" 
											class="add-on starttime" />
												</span>
										<div style="width:100%;" class="input-group date form_time col-md-8" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii"><input id="stms" tabindex="5" class="form-control inputcolor stms" type="text" name="stms" value=""><span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span></div>		
											
  
									<!-- </div> -->
								</div>
								
							</div> --%>
							
							<input type="hidden" id="time1" value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="stratTime"></bean:write></logic:present>'/>
							<input type="hidden" id="time2" value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="endTime"></bean:write></logic:present>' />
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Halt Time(min)
									
								</label>
								<div class="col-xs-7">
									<input type="text" name="halttime" id="halttime" maxlength="2" onclick="HideError(this)" tabindex="7"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="halttime"></bean:write></logic:present>'
										class="form-control" />
								</div>
							</div>
							
							<br />
							
						</div>
						
						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Route
									Name <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<input type="text" name="routeName" id="routeName" onclick="HideError(this)" 
										maxlength="49" class="form-control" tabindex="2"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="routeName"></bean:write></logic:present>' />
								</div>
							</div>

							

						

							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Total
									Distance(km)
								</label>
								<div class="col-xs-7">
									<input type="text" name="totalDistance" id="totalDistance" tabindex="4"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="totalDistance"></bean:write></logic:present>'
										maxlength="7" class="form-control" />
								</div>
							</div>
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">End Time<font color="red">*</font></label>
									<div class="col-xs-7">
										<div style="width:100%;" class="input-group date form_time col-md-8" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii"><input id="endtime1" class="form-control inputcolor endtime1" type="text" name="endtime1" value="" readonly="" placeholder="Click Here" ><span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span></div>
									</div>
								</div>
							<!-- <div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">End Time <font color="red">*</font></label>
								<div class="col-xs-7">
									
									<div style="width:100%;" class="input-group date form_time col-md-8" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii"><input id="etms" tabindex="6" class="form-control inputcolor etms" type="text" name="etms" value=""><span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span></div>
									
								</div>
							</div> -->
						
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Total
									Stops
								</label>
								<div class="col-xs-7">
									<input type="text" name="totalStops" id="totalStops" readonly
										maxlength="5" onclick="HideError(this)" placeholder="0" tabindex="8"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="totalStops"></bean:write></logic:present>'
										class="form-control" />
								</div>
							</div>
							<br />
				
					 <input type="hidden" name="locationid" class="locationid" id="locationid" 
         value='<logic:present name="locationid" scope="request"><bean:write name="locationid"></bean:write></logic:present>'></input>		
							
						
							<span id="checklocations" class="buttons" tabindex="9" style="float: right; margin-right:11%; margin-top:-9px; margin-bottom:2%;font-family: Roboto,sans-serif !important;">Add Stages</span>
							
							<!-- <span class="buttons"  id="save" >Save</span>   -->
							
						</div>
						<div class="stagetable col-md-12">
						<table id="myTable" class="table allstudent" 
							style=" display: none;">

							<thead>
								<tr>
								<th class="headth" style="text-align: left;">Sl.No.</th>
								<th class="headth" style="text-align: left;">Stage Name</th>
								</tr>
							</thead>
							<tbody>
							<logic:present name="unmappedStages" scope="request">
								<logic:iterate id="unmappedStages" name="unmappedStages" scope="request"> 
									<tr id="<bean:write name="unmappedStages" property="stage_id"></bean:write>">
										<td><bean:write name="unmappedStages" property="count"></bean:write></td>
										<td><bean:write name="unmappedStages" property="stage_name"></bean:write></td>
										
									</tr>
								</logic:iterate>
							
							</logic:present>
									
							</tbody>
						</table>
                     </div>
						
						
						<input type="hidden" name="routeid" id="routeid" 
							value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="routeCode"/></logic:present>' />
						
					</div>
				</div>

			</div>
		</div>
	</div>
</body>

</html>
