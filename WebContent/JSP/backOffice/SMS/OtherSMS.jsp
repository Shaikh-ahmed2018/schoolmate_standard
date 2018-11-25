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
<title>eCampus Pro</title>
<script type="text/javascript" src="JS/common.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"rel="stylesheet" type="text/css">
<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="JS/backOffice/SMS/OtherSMS.js"></script>

<style>
.buttons{
   vertical-align: 0px;
}

select[multiple], select[size] {
	height: 100px !important;
}

</style>
</head>

<body>
	<div class="col-md-10"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Other SMS</span>
		</p>


		<%-- <logic:present name="message" scope="request">
			<div class="successmessagediv1">
				<div class="message-item">
					<a href="#" class="msg-success bg-msg-succes"> <span
						class="validateTips"><bean:write name="message"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present> --%>


		<logic:present name="message" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="message" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

  <logic:present name="message" scope="request">
			<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"><bean:write
								name="message" scope="request" /></span></a>
			</div>
		</div>
		</logic:present>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
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


			   <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapseOne" style="color: #767676"> <h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Add Other SMS
							</h4></a>
						<div class="navbar-right">
							<span id="save" class="buttons"
								data-toggle="modal"
								data-placement="bottom">Save
							</span>
                         &nbsp;
							<!-- <a href="" id="meetingsave"><img src="images/save.png"
									style="font-size: 20px; line-height: 34px; color: #989898; margin-top: -5px;"></a>	 -->
							<span class="buttons" id="back" >Back</span><!-- </a> -->
						</div>
					</div>

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" style="margin-top: 11px;">
							<div class="col-md-6" id="txtstyle">
                              
                                 <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select  id="locId" onclick="HideError(this)"
											class="form-control">
											<option value="">------Select------</option>
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
										style="text-align: right; line-height: 35px;">Class</label>

									<div class="col-xs-7">
										<select  id="classid" class="form-control" onclick="HideError(this)">
											<option value="">------Select------</option>
										</select>
									</div>
						        </div> 

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Student</label>
									<div class="col-xs-7">
										<select  multiple id="studentid" class="form-control" onclick="HideError(this)" >
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
										style="text-align: right; line-height: 35px;"> Date</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" readonly="readonly"
											name="date" id="date" onclick="HideError(this)""
											value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dateofJoin"/></logic:present>" />
									</div>
								</div>
								
								

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select  multiple id="sectionid" onclick="HideError(this)" class="form-control">
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


								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">SMS Text
									</label>
									<div class="col-xs-7">
										<textarea name="smstext" id="smstext" class="form-control" onclick="HideError(this)"	style="width: 100%; height: 108px; margin-top: 0px; margin-bottom:10px;"><logic:present name="driverlist"><bean:write name="driverlist" property="address" /></logic:present></textarea>
									</div>
								</div>

							</div>

						</div>

					</div>

				</div>
			</div>

	</div>




</body>

</html>
