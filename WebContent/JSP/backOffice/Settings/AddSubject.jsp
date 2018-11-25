<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>
  <script type="text/javascript" src="JS/backOffice/Settings/AddSubject.js"></script> 
</head>
<body>
	<div class="content" id="div-main">
		<p>
			<img src="images/addstu.png" style="vertical-align: top;"/>&nbsp;<span id="pageHeading">New Subject</span>
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
		      
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne" style="margin-bottom: 10px;">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#" style="color: #000000"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Subject Details
							</h3></a>
							<div class="navbar-right">
								 <span id="save" class="buttons">Save</span> 
								<span id="back" class="buttons">Back</a></span> 
							</div>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="clearfix">
							<div class="col-md-6" id="txtstyle" style="font-size: 13px;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Branch <span style="color: red;">*</span></label>
									<div class="col-xs-7">
									<select id="locationname" name="locationId" class="form-control">
										<logic:present name="locationList">
										<logic:iterate id="Location" name="locationList">
											<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
										</logic:iterate>
										</logic:present>
						
									</select>
									</div>
								<input type="hidden" name="schoolId" class="form-control" id="schoolId" value='<logic:present name="list"><bean:write name="list" property="locationId" /></logic:present>'></input>
							</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Subject Name<span style="color: red;">*</span></label>
									<div class="col-xs-7">
											<input type="text" name="subjtname" id="subjtname" onkeypress="HideError()" class="form-control"   placeholder="" value=""/>
									</div>
								</div>
						          	<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Subject Code<span style="color: red;">*</span></label>
									<div class="col-xs-7">
											<input type="text" name="subjectCode" id="subjectCode" onkeypress="HideError()" class="form-control"   placeholder="" value=""/>
									</div>
								</div>
							<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Sub Type<span style="color: red;">*</span></label>
										<div class="col-xs-7">
												<!-- <input type="text" name="passMarks" id="passMarks" onkeypress="HideError()" class="form-control"   placeholder="" value=""/> -->
											<select id="subtype" name="subType" class="form-control">
											<option value="">-------------Select-----------</option>
											<option value="Major">Major</option>
											<option value="Minor">Minor</option>
								
										</select>
												
										</div>
									</div>
						<input type="hidden" name="clsnam"  id="clsname"></input>
							</div>
							<div class="col-md-6" id="txtstyle">
								
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class<span style="color: red;">*</span></label>
									<div class="col-xs-7">
										<select name="classname" id="classname" class="form-control" onkeypress="HideError()" >
									<option value="">-------------Select-----------</option>
									
							    </select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Language<span style="color: red;">*</span> ?</label>
									<div class="col-xs-7">
									<select id="isLangauge" name="isLang" class="form-control">
										<option value="N">No</option>
										<option value="Y">Yes</option>
									</select>
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Specialization<span style="color: red;display: none;" class="notmandatory">*</span></label>
									<div class="col-xs-7">
									<select id="specialization" name="specialization" class="form-control">
										<option value="-" selected="selected">NIL</option>
										<logic:present name="specialization">
										<logic:iterate id="specialization" name="specialization">
											<option value="<bean:write name="specialization" property="specializationId"/>"><bean:write name="Location" property="specializationName" /></option>
										</logic:iterate>
										</logic:present>
						
									</select>
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Practical Available<span style="color: red;">*</span></label>
									<div class="col-xs-7">
											<!-- <input type="text" name="totalMarks" id="totalMarks" onkeypress="HideError()" class="form-control"   placeholder="" value=""/> -->
										<select id="isLab" name="islab" class="form-control">
										<option value="N">No</option>
										<option value="Y">Yes</option>
										
							
									</select>
									</div>
								</div>
						
						        
						      <!--   <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Syllabus</label>
									<div class="col-xs-7">
										<input type="file" class="form-control" id="file" name="file"
											placeholder="">
									</div>
								</div> -->
						
		                    <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
											<textarea rows="3px" cols="3px" name="description" id="description" class="form-control"    placeholder="" >
											</textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
					
     </div>
			   
	 <input type="hidden" name="hiddensubjectId" id="hiddensubjectid" 
	 value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="subjectid"/></logic:present>"/>   
			      
               <input type="hidden" name="hiddenclass" id="hiddenclassid" value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="classname"/></logic:present>"/>
               <input type="hidden" name="hiddensubject" id="hiddensubject" value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="subjectname"/></logic:present>"/>
               <input type="hidden" name= "hiddendescription" id="hiddendescription" value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="description"/></logic:present>"/>
               <input type="hidden" name="hiddenfile" id="hiddenfile" value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="path"/></logic:present>"/>
               
               <input type="hidden" name="status" id="statusid" value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="status"/></logic:present>"/>
				
		 
	</div>
	
	
	
</body>

</html>
