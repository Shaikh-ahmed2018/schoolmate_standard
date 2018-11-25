<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html>
<head>


<script type="text/javascript" src="JS/frontOffice/New_Registration_Of_Users/New_Registration_Of_Users.js"></script>
	
     <div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">
				Registration</span>
		</p>
		<logic:present name="successmessagediv" scope="request">
						<div class="successmessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span><bean:write
											name="successmessagediv" scope="request" /></span></a>
							</div>
						</div>
					</logic:present>
					
						<logic:present name="errormessagediv" scope="request">

							<div class="errormessagediv" align="center">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-warning bg-msg-warning"><span style="color:red ;">
											<bean:write name="errorMessage" scope="request" />
									</span></a>
								</div>

							</div>

						</logic:present>


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
					
	<input type="hidden" id="hiddenloc"  
   value="<logic:present name="curr_loc" scope="request"><bean:write name="curr_loc"/></logic:present>" />
   
					
		<input type="hidden" id="historysearch"  
   value="<logic:present name="historysearch" scope="request"><bean:write name="historysearch"/></logic:present>" />
   
   <input type="hidden" id="historytabstatus"  
   value="<logic:present name="historytabstatus" scope="request"><bean:write name="historytabstatus"/></logic:present>" />			
					

   <form action="parentrequiresappointment.html?method=InsertTemporaryStudentRegistration"
			enctype="multipart/form-data" id="formstudent" method="post">

			


			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#" style="color: #000000"> <h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;New Registration
							</h3></a>
  


						<div class="navbar-right">
		                  <span id="save2" class="buttons" style="top:4px;right:4px">Save</span>
		                  <span id="back1" class="buttons" style="top:4px;right:0px">Back</span>
		                  
		         
						</div>

					</div>



					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
                                
 				   <div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-7">
									<select id="Acyearid" name="accyear" class="form-control"
										required>
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
	<input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">		           
			            <input type="hidden" id="hiddenaccyear" name="hiddenaccyear"
				                       value="<bean:write name='academic_year' scope="request"/>" />
							
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;" id="inputnames">Stream
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<select id="stream" name="stream" class="form-control" onchange="HideErrors(this)"  tabindex="8">
											<option value="">----------Select----------</option>
										</select>
									</div>
								</div>
					        
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">First
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" name="parentfirstName"   tabindex="1"
											onclick="HideErrors(this)" id="parentfirstName" maxlength="25"
											class="form-control" value='' />
									</div>
								</div>
		



								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Parents
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="parents_name"  tabindex="3"
											id="parents_name" onclick="HideErrors(this)" 
											value='' />
									</div>
								</div>
			



								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Email Id <span
										style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text"  class="form-control" name="parentEmailId"  tabindex="5"
											onclick="HideErrors(this)" id="parentEmailId" maxlength="100"
											value='' />
									</div>
								</div>

						
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Address
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<textarea name="address" id="address" onclick="HideErrors(this)"  tabindex="7"
											class="form-control"></textarea>

									</div>
								</div>
								
							</div>

							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Branch<span style="color: red;">*</span></label>
										<div class="col-xs-7">
											<select id="locationname" name="locationnid" onchange="HideErrors(this)" class="form-control" required>
												
												<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
												<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
												</logic:present>
											</select>
										</div>
					             </div>
					            
					            <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;" id="inputnames">Class
									<span style="color: red;">*</span></label>
									<div class="col-xs-7">
									<select id="class" name="classname" class="form-control" onchange="HideErrors(this)" tabindex="9">
										<option value="">----------Select----------</option>
									</select>
									</div>
					          </div>
					             
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Last
										Name 
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control"  tabindex="2"
											name="parent_LastName" id="parent_LastName"
											onclick="HideErrors(this)" value='' />
									</div>
								</div>
								
                                    <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Relationship <span
										style="color: red;">*</span></label>
									<div class="col-xs-7">
									<select name="stu_parrelation" id="stu_parrelation"  tabindex="4"
											class="form-control" onchange="HideErrors(this)">
											<option value="">----------Select----------</option>
										    <option value="father">Father</option>
											<option value="mother">Mother</option>
											<option value="guardian">Guardian</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Mobile
										No <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="mobile_number"  tabindex="6"
											id="mobile_number" onclick="HideErrors(this)" maxlength="10"
											value='' />
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</div>
				</div>
	</form>
	</div>
	</head>
	</html>
	