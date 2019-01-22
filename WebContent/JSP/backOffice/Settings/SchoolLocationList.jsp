<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript"  src="JS/backOffice/Settings/SchoolAddressList.js"></script>

<style>
.glyphicon:hover {
	cursor: pointer;
}
</style>

<style>
.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

#allstudent th:nth-child(2),td:nth-child(2) {
	width: 30%;
	text-align: left;
}

#allstudent th:nth-child(3) {
    width: 45%;
}

.tablecontents
{
  font-style: italic;
}

.ui-dialog{
    position:fixed;
    top:130px !important;          
}

 
</style>

<%--  <th>Status</th>
       <td><bean:write name='locationDetailsList' property="status" /></td>  --%>
</head>

<body>

	<div class="content" id="div1">
		<div id="dialog"></div>
		 
			

				<input type="hidden" name="searchterm" class="searchtermclass"
					id="searchexamid"
					value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />
		</logic:present>'></input>
			<!-- </form> -->
		 

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


	
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;">
					<h3 class="panel-title" style="color: #000000;">
						&nbsp;&nbsp;Branch Details
					</h3></a>
					
					
				<div class="navbar-right">
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SLCADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span class="btn btn-xs btn-primary margin-t-5 addClass"
										data-toggle="modal" data-target="#myModal"> <span
										class="glyphicon glyphicon-plus"></span>Add Branch </span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
			    </div>
			</div>



<%-- 
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SLCADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<a href="menuslist.html?method=addSchoolLocation"> <span
										id="add" class="buttons">New</span>
									</a>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SLCUPD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span class="buttons" id="editstream">Modify</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SLCDEL" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span class="buttons" id="inactive">InActive</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
 --%>

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
						<h3 class="modal-title" id="myModalLabel">Branches</h3>
						</div>
						
						<div class="modal-body">
							<div class="panel-body">
							
							<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-bottom: 9px;">

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">School
									Name<font color="red">*</font></label>
								<div class="col-xs-7">
										<select id="schollid" name="locationnid" tabindex="1" class="form-control" required>
											<!-- <option value="all">ALL</option> -->
											<logic:present name="locationList">
												<logic:iterate id="locationList" name="locationList">
													<option value="<bean:write name="locationList" property="schoolId"/>"><bean:write name="locationList" property="schoolName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
							</div>
						
						<input type="hidden" id="operation" name="operation"
						 value="<logic:present name="editlist"><bean:write name="operation" /></logic:present>" />

                         <div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">School Code<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="schoolcode" onclick="HideError(this)"
										class="form-control" id="schoolcode" tabindex="3"
										value='<logic:present name="editlist"><bean:write name="editlist" property="schoolcode" /></logic:present>'></input>
								   </div>
						 </div>

                    <div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Mobile No.<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="contactno" onclick="HideError(this)" tabindex="5"
										class="form-control" id="contactno" maxlength="10" tabindex="7"
										value='<logic:present name="editlist"><bean:write name="editlist" property="contactno" /></logic:present>'></input>
								   </div> <!-- maxlength="12" -->
					 </div>
					 
					  <div class="form-group clearfix" style="margin-bottom:10px;">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Address1<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<textarea style="" tabindex="7" class="form-control" name="address" id="address" onclick="HideError(this)"><logic:present name="editlist"><bean:write name="editlist" property="address" /></logic:present></textarea>
									</div>
					     </div>
					 
					      <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Country<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select class="form-control countries" onchange="HideError(this)" tabindex="9" name="country" id="countryId">
											<option value="">---------Select---------</option>
										</select>
									</div>
					     </div>
					     
					     <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">City/District<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select class="form-control cities" onchange="HideError(this)" tabindex="11" name="city" id="cityId">
											<option value="">---------Select---------</option>
										</select>
									</div>
						 </div>
					 
					     <div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Affiliated to<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="board" onclick="HideError(this)"
										class="form-control" id="board" tabindex="13"
										value='<logic:present name="editlist"><bean:write name="editlist" property="board" /></logic:present>'></input>
								   </div>
							</div>
						
						<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Board Logo</label>
									<div class="col-xs-7" style="margin-bottom:5px;">
										<input type="file" id="boardlogo" name="boardlogo" class="form-control" onclick="HideError(this)"
										 style="width:76%;display: inline;" tabindex="15" value=""/> 
										 
									    <input type="button" id="downloadlicenceid" name="profile" class="downloadDoc1" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteProfile">  x</span>
										 
										 <span><img id="boardimagePreview" src="#" alt="image" width="35px" 
											height="35px" style="margin-left:5px;display: none;margin-top: -6px;"/></span> 
									</div>
						 </div>
						    	
				 <input type="hidden" id="hiddenschoollogoId" name="hiddenschoollogoId"
				  value="<logic:present name="editlist"><bean:write name="editlist" property="schoollogo" /></logic:present>" />
								
							<input type="hidden" id="hiddenboardlogoId" name="hiddenboardlogoId" value="<logic:present name="editlist"><bean:write name="editlist" property="boardlogo" /></logic:present>" />
						
						 </div>
							
							
							
                        <div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-bottom: 9px;">
								
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Location<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="locAddId" onclick="HideError(this)"
										class="form-control" id="locAddId" tabindex="2"
										value='<logic:present name="editlist"><bean:write name="editlist" property="locationname" /></logic:present>'></input>
								   </div>
							</div>
							
				 <input type="hidden" id="hiddenlocaddressId" 
				 value='<logic:present name="hiddenlocId" scope="request"><bean:write name="hiddenlocId"/></logic:present>'></input>
					
					<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Email Id<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="emailId" onclick="HideError(this)"
										class="form-control" id="emailId" tabindex="4"
										value='<logic:present name="editlist"><bean:write name="editlist" property="emailId" /></logic:present>'></input>
								</div>
				    </div>
						
					 <div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Land line No.</label>
								<div class="col-xs-7">
									<input type="text" name="landlineno" onclick="HideError(this)"
										class="form-control" id="landlineno"  tabindex="6"
										value='<logic:present name="editlist"><bean:write name="editlist" property="landLineNo" /></logic:present>'></input>
								   </div> <!-- maxlength="12" -->
					 </div>	
					
					     <div class="form-group clearfix" style="margin-bottom:10px;">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Address2
									</label>
									<div class="col-xs-7">
										<textarea style="" tabindex="8" class="form-control" name="address2" id="address2" onclick="HideError(this)"><logic:present name="editlist"><bean:write name="editlist" property="address2" /></logic:present></textarea>
									</div>
					      </div>
						
						   <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">State<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select class="form-control states" onchange="HideError(this)" tabindex="10" name="state" id="stateId">
											<option value="">---------Select---------</option>
										</select>
									</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Pin Code<font color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="pincode" tabindex="12" onclick="HideError(this)" class="form-control" id="pincode"
										value='<logic:present name="editlist"><bean:write name="editlist" property="pinCode" /></logic:present>'></input>
								</div>
							</div>
								
							 <div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Affilation No.</label>
								<div class="col-xs-7">
									<input type="text" name="affilno" onclick="HideError(this)"
										class="form-control" id="affilno" tabindex="14"
										value='<logic:present name="editlist"><bean:write name="editlist" property="affilno" /></logic:present>'></input>
								   </div>
					       </div>
							
		<input type="hidden" name="hiddenaddId" name="hiddenaddId" id="hiddenaddId" value='<logic:present name="editlist"><bean:write name="editlist" property="locAddId" /></logic:present>'></input>
		<input type="hidden" name="hiddencountry" id="hiddencountry" value='<logic:present name="editlist"><bean:write name="editlist" property="countryId" /></logic:present>'></input>
		<input type="hidden" name="hiddenstate" id="hiddenstate" value='<logic:present name="editlist"><bean:write name="editlist" property="stateId" /></logic:present>'></input>
		<input type="hidden" name="hiddencity" id="hiddencity" value='<logic:present name="editlist"><bean:write name="editlist" property="cityId" /></logic:present>'></input>
						
						 </div>
							
							</div>
						
						
						</div>
						<div class="modal-footer">
						<span id="save" class="buttons button-blue">Save</span> <span
							class="buttons button-simple" data-dismiss="modal">Close</span>
					</div>
					
					<input type="hidden" id="actionstatus" value="add" />	

					</div>
				</div>
			</div>
      
      <input type="hidden" id="custstatus" value="<logic:present name='schlstatus' scope='request' ><bean:write name='schlstatus'/></logic:present>">

			<div id="collapseOne" class="accordion-body collapse in">
			
				<div class="row">
				   <%-- <div class="col-md-6"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top: 1%;">	
				              <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locId" name="locationnid" class="form-control" required>
											<!-- <option value="all">ALL</option> -->
												<logic:iterate id="locationList" name="locationList">
													<option value="<bean:write name="locationList" property="schoolId"/>"><bean:write name="locationList" property="schoolName" /></option>
												</logic:iterate>
										</select>
									</div>
				         </div>
                </div> --%>	
					
					<div class="col-md-6"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top: 1%;">
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
				
				<input type="hidden" id="hiddenstatus" name="hiddenstatus"
					value="<logic:present name='status' scope='request' ><bean:write name='status'/></logic:present>" />

				<input type="hidden" id="hiddensclst" name="hiddensclst"
					value="<logic:present name='schlstatus' scope='request' ><bean:write name='schlstatus'/></logic:present>" />

				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

					<%-- <logic:present name="locationDetailsList" scope="request"> --%>

						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th><input type='checkbox' name='selectall' id='selectall' style='text-align: center' onClick='selectAll()' /></th>
									<th>Branch</th>
									<th>Contact Details</th>
									<th>Remarks</th>

								</tr>
							</thead>
							<tbody>
							</tbody>

						</table>
						<div class='pagebanner'>
							<select id='show_per_page'><option value='50'>50</option>
								<option value='100'>100</option>
								<option value='200'>200</option>
								<option value='300'>300</option>
								<option value='400'>400</option>
								<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
						<div class='pagination pagelinks' style="margin-bottom: 10px;"></div>
					<%-- </logic:present> --%>

				</div>

			</div>
		
	</div>
</body>
</html>