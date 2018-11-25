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
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript" src="JS/backOffice/Settings/SubjectAmendment.js"></script>
<script type="text/javascript">
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
<!-- <script>
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
</script> -->

<style>
.buttons {
	vertical-align: 0px;
}

.navbar-right span {
	margin-right: 3px;
}

.navbar-right span:last-child {
	margin-right: 10px;
}
</style>
</head>

<body>
	<div class="content" id="div-main">
		<p>
			<img src="images/addstu.png" style="vertical-align: top;"/>&nbsp;<span id='pageHeading'>Modify Subject</span>
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
						
							<a data-toggle="collapse" data-parent="#accordion" href="#"
								style="color: #000000"><h3 class="panel-title"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Subject Details
							</h3></a>
						
						<div class="navbar-right">

							<span id="save" class="buttons">Save</span>
						<span id="back1" class="buttons">Back</span></a>
						</div>

					</div>
					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">

<input type="hidden" id="hiddenlocId"
			 value='<logic:present name="locId1" scope="request"><bean:write name="locId1"/></logic:present>'></input>

<input type="hidden" id="hiddenclassname"
			 value='<logic:present name="classname1" scope="request"><bean:write name="classname1"/></logic:present>'></input>

<input type="hidden" id="hiddenspecId"
			 value='<logic:present name="specialization1" scope="request"><bean:write name="specialization1"/></logic:present>'></input>

<input type="hidden" id="hiddenstatus"
			 value='<logic:present name="status1" scope="request"><bean:write name="status1"/></logic:present>'></input>
			 			 			 
						<div class="clearfix">
							<div class="col-md-6" id="txtstyle" style="font-size: 13px;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">School
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationId"
											class="form-control">
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option
														value="<bean:write name="Location" property="locationId"/>">
														<bean:write name="Location" property="locationName" />
													</option>
												</logic:iterate>
											</logic:present>

										</select>
									</div>
									<input type="hidden" name="schoolId" class="form-control" id="schoolId"
										value='<logic:present name="singlesubjectdetails"><bean:write name="singlesubjectdetails" property="locationId" /></logic:present>'></input>
								</div>
								
                               <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Subject
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" name="subjtname" id="subjtname"
											class="form-control" placeholder="Subject Name"
											value='<logic:present name="singlesubjectdetails"><bean:write name="singlesubjectdetails" property="subjectname" /></logic:present>' />
									</div>
								</div>
                               <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Subject
										Code<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" name="subjectCode" id="subjectCode"
											onkeypress="HideError()" class="form-control" placeholder=""
											value='<logic:present name="singlesubjectdetails"><bean:write name="singlesubjectdetails" property="subjectCode" /></logic:present>' />
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
							
						</div>
							<div class="col-md-6" id="txtstyle">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class<span
										style="color: red;">*</span></label>
									<div class="col-xs-7">
										<select name="classname" id="classnameid" class="form-control"
											onkeypress="HideError()">
											<option value=""></option>

										</select>
									</div>
								</div>
								
								<input type="hidden" name="clsnam"  id="clsname"></input>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Language<span style="color: red;">*</span> ?</label>
									<div class="col-xs-7">
									<select id="isLangauge" name="isLanguage" class="form-control">
										<option value="N">No</option>
										<option value="Y">Yes</option>
									</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Specialization<span style="color: red;display: none;" class="notmandatory">*</span></label>
									<div class="col-xs-7">
										<select id="specialization" name="specialization"
											class="form-control">
											<option value="-" selected="selected">NIL</option>
											<logic:present name="specialization">
												<logic:iterate id="specialization" name="specialization">
													<option
														value="<bean:write name="specialization" property="specializationId"/>">
														<bean:write name="specialization" property="specializationName" />
													</option>
												</logic:iterate>
											</logic:present>

										</select> <input type="hidden" name="hspecialization"
											class="form-control" id="hspecialization"
											value='<logic:present name="singlesubjectdetails"><bean:write name="singlesubjectdetails" property="specializationId" /></logic:present>'></input>
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
								
							<!-- 	<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Syllabus</label>
									<div class="col-xs-7">
										<input type="file" class="form-control" id="file" name="file"
											placeholder="Syllabus"> <input type="button"
											id="document1btn" name="profile" class="downloadDoc"
											value="Download" /> <span
											style="font-size: 20px; color: red; cursor: pointer;"
											id="deleteProfile"> x</span>

									</div>
									
								</div> -->
								
								
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
										<textarea rows="3px" cols="3px" name="description"
											id="description" class="form-control" placeholder="">
											<logic:present name="singlesubjectdetails">
												<bean:write name="singlesubjectdetails"
													property="description" />
											</logic:present>
										</textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<input type="hidden" name="hiddenclass" id="hiddenclassid"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="classname"/></logic:present>" />
			<input type="hidden" name="hiddensubject" id="hiddensubject"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="subjectname"/></logic:present>" />
			<input type="hidden" name="hiddendescription" id="hiddendescription"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="description"/></logic:present>" />
				
				
				<input type="hidden" name="hiddenislab" id="hiddenislab"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="islab"/></logic:present>" />
				
				<input type="hidden" name="hiddensubtype" id="hiddensubtype"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="subType"/></logic:present>" />
				
				<input type="hidden" name="hiddenislangu" id="hiddenislangu"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="isLanguage"/></logic:present>" />
				
				
				<input type="hidden" name="hiddensubcode" id="hiddensubcode"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="subjectCode"/></logic:present>" />
				
				
			<input type="hidden" name="hiddenfile" id="hiddenfile"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="path"/></logic:present>" />
			<input type="hidden" name="hiddensubjectId" id="hiddensubjectid"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="subjectid"/></logic:present>" />
			<input type="hidden" name="status" id="statusid"
				value="<logic:present name="singlesubjectdetails" ><bean:write name="singlesubjectdetails" property="status"/></logic:present>" />

			<input type="hidden" name="status" id="hsuccessid"
				value="<logic:present name="success" ><bean:write name="success"/></logic:present>" />

		 
	</div>

</body>

</html>
