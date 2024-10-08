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

<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>

<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/backOffice/SMS/addlatecomers.js"></script>

<style>
.buttons{
vertical-align:0px;
}

select[multiple], select[size] {
	height: 100px !important;
}
.panel-title{
font-size: 15px;
	color: #000000;
    font-family: Roboto,sans-serif;
    margin-left: 10px;
    left: 10px;

}
.panel-primary>.panel-heading {
	margin-left: 1px;
}
</style>
</head>

<body>
	<div class="col-md-10"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">New Late Comers</span>
		</p>
		
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
				

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;LateComers
							</h4></a>
							

							<div class="navbar-right" >
								
							
							  <span id="save" class="buttons" data-toggle="modal"
								data-placement="bottom">Save
									</span> &nbsp;
							
							<!-- <a href="" id="meetingsave"><img src="images/save.png"
									style="font-size: 20px; line-height: 34px; color: #989898; margin-top: -5px;"></a>	 -->
						<span id="back1" class="buttons">Back</span></a>
							</div>
					</div>
			<input	type="hidden" name="drivercode" id="drivercode" value="<logic:present name="driverlist" ><bean:write name="driverlist" property="driverCode"/></logic:present>"/>
		    <input	type="hidden" name="vehiclecode" id="vehiclecode" value="<logic:present name="fuellist" ><bean:write name="fuellist" property="vehicleCode"/></logic:present>"/>
            <input type="hidden" name="remarkcode" id="remarkcode"  value="<logic:present name="fuellist" ><bean:write name="fuellist" property="fuelCode"/></logic:present>"/>

     <input type="hidden" id="historylocId"
	 value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>" />
	 
	 <input type="hidden" id="historyacademicId"
	 value="<logic:present name="historyacademicId" scope="request"><bean:write name="historyacademicId"/></logic:present>" />
	 
	 <input type="hidden" id="historyclassname"
	 value="<logic:present name="historyclassname" scope="request"><bean:write name="historyclassname"/></logic:present>" />
	 
	 <input type="hidden" id="historysectionid"
	 value="<logic:present name="historysectionid" scope="request"><bean:write name="historysectionid"/></logic:present>" />
	 
	 <input type="hidden" id="historystartdate"
	 value="<logic:present name="historystartdate" scope="request"><bean:write name="historystartdate"/></logic:present>" />
	 
	 <input type="hidden" id="historyenddate"
	 value="<logic:present name="historyenddate" scope="request"><bean:write name="historyenddate"/></logic:present>" />


			<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" style="margin-top: 11px;">
							<div class="col-md-6" id="txtstyle">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Branch<font color="red">*</font></label>
									<div class="col-xs-7">
										<select name="locId" id="locId" onclick="HideError(this)"
											class="form-control">
											
											<logic:notEmpty name="locationList" scope="request">
												<logic:iterate id="locationList" name="locationList"
													scope="request">
													<option
														value='<bean:write name='locationList' property='locationId'/>'>
														<bean:write name="locationList" property="locationName" />
													</option>

												</logic:iterate>

											</logic:notEmpty>
										</select>
									</div>
								</div>
								
							
								
						
							 <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class<font color="red">*</font></label>

									<div class="col-xs-7">
										<select name="classname" id="classid" class="form-control"  onclick="HideError(this)" >
											<option value="">----------Select----------</option>
										</select>
									</div>
						     </div>
								 
								 <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Students<font color="red">*</font></label>
									<div class="col-xs-7">
										<select name="accyear" multiple id="studentid" class="form-control" onclick="HideError(this)" style="height: 174px !important;">
									<option value=""></option>
								<logic:notEmpty name="ALLACCYEARS" scope="request">
										<logic:iterate id="map" name="ALLACCYEARS" scope="request">
											<option value='<bean:write name='map' property='key'/>'>
												<bean:write name="map" property="value" />
											</option>

										</logic:iterate>
											
									</logic:notEmpty>
							    </select>
									</div>
								</div> 
							
							</div>
							
							
							
							<div class="col-md-6" id="txtstyle">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Late Arrived Date<font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text"  class="form-control" readonly="readonly" name="date" id="dateId" onclick="HideError(this)"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dateofJoin"/></logic:present>"/>
									</div>
								</div>
								
					
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Divisions<font color="red">*</font></label>
									<div class="col-xs-7">
										<select name="sectionname" multiple id="sectionid" onclick="HideError(this)" class="form-control">
											<option value=""></option>
											<logic:notEmpty name="ALLACCYEARS" scope="request">
												<logic:iterate id="map" name="ALLACCYEARS" scope="request">
													<option value='<bean:write name='map' property='key'/>'>
														<bean:write name="map" property="value" />
													</option>

												</logic:iterate>

											</logic:notEmpty>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">SMS Text
									</label>
									<div class="col-xs-7">
										<textarea  readonly="readonly"  name="remarks" id="description" class="form-control" onclick="HideError()" style=" width: 100%; height: 108px; margin-top: 0px; margin-bottom:"><logic:present name="driverlist"><bean:write name="driverlist" property="address"/></logic:present></textarea>
									</div>
								</div>
								
							</div>	
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
   </form>
</div>
	
</body>

</html>
