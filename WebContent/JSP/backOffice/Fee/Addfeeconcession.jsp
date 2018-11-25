<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%><!DOCTYPE html>
<html lang="en">
<head>
  <script type="text/javascript" src="JS/backOffice/Fee/concession.js"></script>
</head>


<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
<div id="dialog"></div>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="concession" scope="request"><bean:write name="concession"></bean:write></logic:present></span>
		</p>
		
			<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
		
		
		<%-- 	<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present> --%>

		<%-- <logic:present name="errormessagediv" scope="request">
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present> --%>

		<div class="errormessagediv1"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 35%; font-size: 13px; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>
		
		<form  method="post" >
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#" style="color: #000000"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Concession Details
							</h3></a>
							
							
							<div class="navbar-right">
								<span id="submit" class="buttons" data-placement="bottom" title="Save" >Save
								</span> &nbsp;
								 <span id="back1" class="buttons">Back</span>
							</div>
						
					</div>
      <input type="hidden" id="historylocId"  
		 value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>'></input>

	<input type="hidden" id="historysearchvalue"  
		 value='<logic:present name="historysearchvalue" scope="request"><bean:write name="historysearchvalue"/></logic:present>'></input>
	
	<input type="hidden" id="historystatus"  
		 value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>'></input>	 			
					
		 <input type="hidden" id="concessionId" value='<logic:present name="ConcessionList"><bean:write name="ConcessionList" property="concessionId"/></logic:present>'></input>
		 <input type="hidden" id="hconcessionType" value='<logic:present name="ConcessionList"><bean:write name="ConcessionList" property="concessionType"/></logic:present>'></input>
		 <input type="hidden" id="hisApplicable" value='<logic:present name="ConcessionList"><bean:write name="ConcessionList" property="isApplicable"/></logic:present>'></input>
		 <input type="hidden" id="hconcession" value='<logic:present name="ConcessionList"><bean:write name="ConcessionList" property="concession"/></logic:present>'></input>
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6" id="txtstyle">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" onchange="HideError(this)" tabindex="1"
											class="form-control locationname" required>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write
															name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
						<input type="hidden" name="hiddenlocId" id="hiddenlocId"  
									 value='<logic:present name="ConcessionList"><bean:write name="ConcessionList" property="locationnid"/></logic:present>'></input>
								<div class="form-group clearfix">
									<label for="inputEmail"  class="control-label col-xs-5" style="text-align: right;  line-height: 35px;">Concession Name</label>
									<div class="col-xs-7">
										<input type="text" name="Concessionname" class="form-control" id="concessionname"  onkeypress="HideError(this)" maxlength="29" tabindex="2"
											 value='<logic:present name="ConcessionList"><bean:write name="ConcessionList" property="concesionName"/></logic:present>'></input> 						
									</div>
								</div>
								
								
									<%-- <div class="form-group">
									<label for="inputEmail"  class="control-label col-xs-4"
										style="text-align: right;  line-height: 35px;">Percentage</label>
									<div class="col-xs-7">
										<input type="text" name="percentage" class="form-control" id="percentage"  onkeypress="HideError()"
											 value='<logic:present name="ConcessionList"><bean:write name="ConcessionList" property="percentage"/></logic:present>'></input> 						
									</div>
								</div> --%>
								
								<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Concession Type</label>
						<div class="col-xs-7">
							<select id="concessiontype" name="concessiontype" class="form-control">
								<option value="">----------Select----------</option>
						    </select>
						</div>
					</div>
								</div>
                           <div class="col-md-6" id="txtstyle">
                                <!-- <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Is Applicable For</label>
									<div class="col-xs-7">
									 <div class="col-xs-12">
										<label for="inputPassword" class="control-label" style="text-align: left; line-height: 35px;">School Fees</label>
										<div class="col-xs-1">
										<input type="checkbox" id="schoolfees" name="schoolfees">
										</div>
										<label for="inputPassword" class="control-label col-xs-5"  style="text-align: left; line-height: 35px;padding: 0;">Transport Fees</label>
										<div class="col-xs-1">
										<input type="checkbox" id="transportfees" name="transportfees">
										</div>
										</div>
									</div>
								  </div> -->
								  <div class="form-group clearfix ">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Applicable For</label>
					<div class="col-xs-7" style="padding-left: 5px;">
					<!-- <label class='radio-inline'><input type='checkbox' value='TF' name='transportfee' >Transport-Fee</label> -->
					<label class='radio-inline'><input type='Checkbox' value='SF' name='schoolfee' checked="checked">School-Fee</label>
					</div>
				</div>
				<div class="form-group clearfix ">
											<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Concession By</label>
										<div class="col-xs-7">
											<label class='radio-inline col-xs-8' style="margin-left: 10px;"><input type='radio' value='P' class="concession" name='concession' checked>Percentage Wise</label>
											<label class='radio-inline col-xs-8'><input type='radio' value='A' class="concession" name='concession' >Amount Wise</label>
										</div>
								</div>
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
										<textarea style="resize:none" rows="3" cols="25"  class="form-control" tabindex="3"	 name="description" class="form-control" id="description"><logic:present name="ConcessionList"><bean:write name="ConcessionList" property="description"/></logic:present></textarea>
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
