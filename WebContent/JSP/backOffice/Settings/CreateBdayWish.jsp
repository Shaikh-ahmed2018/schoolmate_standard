<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script> 

<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>

<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>

<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
 --><link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript" src="JS/backOffice/Settings/BirthdaySms.js"></script>  

<!-- <script >
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<script>
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
</script> -->
</head>

<body>
	<div class="col-md-10"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">New BirthDay </span>
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
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #000000"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;BirthDay
							</h3></a>
							

							<div class="navbar-right">
							
								
							
								  <span id="bdaysave" class="save2">
								 <img src="images/save.png" 
									 data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="Save">
									</span> 
							
							
							<!-- <a href="" id="bdaysave"><img src="images/save.png"
									style="font-size: 20px; line-height: 34px; color: #989898; margin-top: -5px;"></a> -->	
									
							
								
						<a href="menuslist.html?method=getbdaylist"><span class="glyphicon glyphicon-list2"
									data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="List"></span></a>
							</div>
						</h3>
					</div>
					

<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>


			<input	type="hidden" name="drivercode" id="drivercode" value="<logic:present name="driverlist" ><bean:write name="driverlist" property="driverCode"/></logic:present>"/>
		    <input	type="hidden" name="vehiclecode" id="vehiclecode" value="<logic:present name="fuellist" ><bean:write name="fuellist" property="vehicleCode"/></logic:present>"/>
            <input type="hidden" name="remarkcode" id="remarkcode"  value="<logic:present name="fuellist" ><bean:write name="fuellist" property="fuelCode"/></logic:present>"/>

			<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								id="txtstyle">
								
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Wishes To</label>
									<div class="col-xs-7" id="radiostyle"  style="margin-top: 8px">
									
									<input type="hidden" id="radio" value='<logic:present name="driverlist"><bean:write name="driverlist" property="" /></logic:present>'></input>
										<input type="radio" class="radioactive" name="gender" id="genderId" value="S" /><label for="Student">Student</label>
										
										<input type="radio" class="radioactive" name="gender" id="genderId" value="T" /><label for="Teacher">Teacher</label>
									
									</div>
								</div>
								<br>
								
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<select name="classname" id="classid" class="form-control" onkeypress="HideError()" >
									      <option value=""></option>
										
							    </select>
									</div>
								</div>
								
							    <br />
								
								
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">
										Date</label>
									<div class="col-xs-7">
										<input type="text"  class="form-control" readonly="readonly" name="date" id="dateId" onkeypress="HideError()"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dateofJoin"/></logic:present>"/>
									</div>
								</div>
								
								<br />
								
								
								
								
								 
                                <div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Content
									</label>
									<div class="col-xs-7">
										<textarea name="remarks" id="remarks" class="form-control" style=" width: 100%; height: 64px;" onkeypress="HideError()"><logic:present name="driverlist"><bean:write name="driverlist" property="address"/></logic:present></textarea>
										<span class="LblDialog" style="font-size: 13px !important">Character Remaining :&nbsp;</span><span id="maxlimit" class="LblDialog" style="font-size: 13px !important"></span>
									</div>
								</div>
								
								 
                               
							
								 <br />
								  <br />
								   <br />
								   
								 
								 	
								
							</div>
							
							
							
							
							
							
							<div class="col-md-6"
								id="txtstyle">
								
			
								
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4" id="streamlableid"
										style="text-align: right; line-height: 35px;">Stream</label>
									<div class="col-xs-7">
										
								       <logic:present name="streamlist" scope="request">
								          <select name="streamname" id="streamname" class="form-control" onkeypress="HideError()" >
									      <option value=""></option>
										    <logic:iterate id="stream" name="streamlist" scope="request">
											<option value='<bean:write name='stream' property='streamId'/>'>
											<bean:write name='stream' property='streamName'/></option>
										    </logic:iterate>
							                </select>
											</logic:present>
							   
									</div>
								</div>
								<br />
								
								
								
                                
							
							    
							    
							    
							    
							    
							    
                                <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select name="sectionname" id="sectionid" class="form-control" onkeypress="HideError()" >
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
								
							 <br />
									
							
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Student/Teacher</label>
									<div class="col-xs-7">
										<select name="accyear" multiple id="remarksid" class="form-control"  onkeypress="HideError()">
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
							
							
						
						</div>
					</div>
					</div>
					</div>
					
</form>
				</div>
			
				
			
	
</body>

</html>
