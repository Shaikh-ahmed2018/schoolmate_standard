<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="JS/backOffice/Library/Reservations.js"></script>

<script type="text/javascript" >
function CheckIsNumeric(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode > 31 && charCode != 46 && charCode != 45 &&(charCode < 48 || charCode > 57)) {
        /* document.getElementById("maxmarks").style.backgroundColor = "#FFB2B2"; */
        return false;
    }
    else {
        /* document.getElementById("maxmarks").style.backgroundColor = "#B2D8B2"; */
        return true;
    }
}
function isNumberKey(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode != 43 && charCode > 31 && charCode != 32 && charCode != 45 && (charCode < 48 || charCode > 57) && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122))
        {
        return false;
        }
    else{
    return true;
    }
} 


</script>



<style>
.form-group {
	margin-bottom: 5px;
}

.save:hover {
	cursor: pointer;
}

fieldset {
	width: 512px;
	display: block;
	/*  margin-left: auto;
    margin-right: 2px; */
	margin-bottom: 5px;
	padding-bottom: 0.625em;
	padding-left: 7px;
	padding-right: 0px;
	border: 0.5px solid #ccc;
}

legend {
	display: inline-block;
	width: auto;
	padding: 0;
	margin-bottom: 0px;
	font-size: 16px;
	line-height: inherit;
	color: #333;
	border: 0;
}

.text2 {
	margin-left: 211px !important;
	width: 819px !important;
}
</style>

<style>
.buttons {
	vertical-align: -5px;
}

.panel-primary>.panel-heading {
	margin-bottom: 0px;
}

form .panel.panel-primary.panel-list {
	padding-bottom: 0px;
}
</style>
</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" style="vertical-align: top;"/><span id="pageHeading">Reservation </span>
		</p>
		<div id="admissionDialog" style="display: none">
			<div id="admissionclose" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
				</div>
				<br />
			</div>
		</div>

	
		<div class="successmessagediv"
			style="width: auto !important; display: none" align="center">
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



		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"
							style="color: #000000;vertical-align: text-top;"><h3 class="panel-title" style="vertical-align: text-top;"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Reservation 
						</h3></a>
					

					<div class="navbar-right">
					   <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="LIBTADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
						                     <span class="buttons" id="reserve">Reserve</span>
						                </logic:equal>
						            </logic:equal>
						         </logic:iterate>
						</logic:present>
						 
			 			   <span class="buttons" id="back">Back</span>
					</div>
				</div>

				<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body own-panel">
						<div class="row">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
										<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Accession
										No <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control ui-autocomplete-input"
											autocomplete="off" id="accession_no" onkeypress="return CheckIsNumeric(event);" name="Accession No "
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="accessionNo"/></logic:present>" />
										<input type="hidden" name="accessionName" id="hidden_accessionNo" value='<logic:present name='studentList' scope='request'><bean:write name='studentList' property="entry_id"/></logic:present>'>
									</div>
								</div>



								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">ItemType<font
										color="red">*</font></label>
									<div class="col-xs-7">
										<input type="hidden" readonly="readonly"
											onkeypress="HideError()" class="form-control" id="itemtype"
											maxlength="20"
											value="<logic:present name="studentList"><bean:write name="studentList" property="itemType"></bean:write></logic:present>" />
											<input type="text" readonly="readonly"
											onkeypress="HideError()" class="form-control" id="item"
											maxlength="20"
											value="<logic:present name="studentList"><bean:write name="studentList" property="item"></bean:write></logic:present>" />
										
									</div>
								</div>

								<div class="form-group clearfix" style="display: none">
									<div class="col-xs-7">
										<input type="text" id="hiddenlocation" class="form-control"
											name="hiddenlocation" readonly="readonly" value="" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Book
										Title <font color="red">*</font>
									</label>
									<div class="col-xs-7">
																				<input type="text" readonly="readonly" name="Book Title"
											id="bookTitle" class="form-control"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="bookTitle"/></logic:present>" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Author <font
										color="red">*</font></label>
									<div class="col-xs-7">

											
											<input type="text" readonly="readonly" class="form-control"
											id="author" name="Author" tabindex="4" maxlength="25"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="author"/></logic:present>" />
											
									</div>
								</div>


								<div class="form-group clearfix" style="display: none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Category 
									</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											name="Category" id="category" tabindex="5" maxlength="25"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="category"/></logic:present>" />

									</div>
								</div>


								<div class="form-group clearfix Book">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">DDC</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											id="ddc" name="DDC"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="ddc"/></logic:present>" />


									</div>
								</div>


								<div class="form-group clearfix">

									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Current
										Status </label>
									<div class="col-xs-7">
										<input class="form-control" readonly="readonly"
											name="currentstatus" tabindex="5" id="currentstatus"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="status"/></logic:present>" />

									</div>
								</div>
	
								<div class="form-group clearfix">
								
									<label for="inputPassword" class="control-label col-xs-5"
										style="margin-top: 8px;text-align: right; line-height: 35px; padding: 0;"> Issuing To<font color="red">*</font></label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
									<div>
									<span id="teacher" style="vertical-align: -4px;"><input
												type="radio" class="radio-inline" style="top: -2px;"
												class="requested_by" name="requested_by" class="cencession"
												id="teachername" value="Teacher" />&nbsp;Teacher&nbsp;&nbsp;&nbsp;
												<input type="hidden" name="TeacherName"
												value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>"></span>
											<span id="studentname" style="vertical-align: -4px;"><input
												type="radio" class="radio-inline" style="top: -2px;"
												name="requested_by" class="requested_by" id="student"
												class="cencession" value="Student" />&nbsp;Student <input
												type="hidden" name="StudentName"
												value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>" />
											</span> 
											<span id="othersname" style="margin-left: 13px;vertical-align: -4px;"><input type="radio"
												class="radio-inline" style="top: -2px;" name="requested_by"
												class="requested_by cencession" id="other"
												value="other"/>&nbsp;Other<input type="hidden"
												name="OthertName"
												value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>" />
											</span>
										
									</div>
										
									</div>
								</div>
								<div class="form-group clearfix" id="usertype"
									style="display: none;">
									<label for="inputPassword" class="control-label col-xs-5"
										id="radiotype" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<input type="text" class="form-control ui-autocomplete-input"
											autocomplete="off" name="usertype" id="userType"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="subscriberName"/></logic:present>" />
										<input type="hidden" id="hiddenstuid" />
									</div>
								</div>
								
								
<input type="hidden" id="hiddenreserveid" value="<logic:present name="editlist"><bean:write name="editlist" property="hiddenReserveId"></bean:write></logic:present>"/>
						
							
							
						<div class="form-group clearfix admissionno" style="display:none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission No</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											id="admissionno" name="admissionno" />
                                      <!-- value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="ddc"/></logic:present>"  -->
									</div>
								</div>
						
								
					<div  id="class"
						style="font-size: 13px;display: none;left:22%;">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Standard</label>
							<div class="col-xs-7">
								<input type="text" name="hiddenclassId" id="hiddenclassId" readonly="readonly" value="" class="form-control"/>
								<!-- value="<logic:present name="religionList"><bean:write name="religionList" property="religionId" /></logic:present>" -->
							</div>
						</div>
					</div>
					<div  id="divission"
						style="font-size: 13px;display: none;left:54px;">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Divission</label>
							<div class="col-xs-7">
								<input type="text" name="hiddendivissionId" id="hiddendivissionId" readonly="readonly" value="" class="form-control"/>
								<!-- value="<logic:present name="religionList"><bean:write name="religionList" property="casteId" /></logic:present>" -->
							</div>
						</div>
					</div>
						
						
						
						<div  id="department"
						style="font-size: 13px;display: none;left:22%;">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Department</label>
							<div class="col-xs-7">
								<input type="text" name="hiddendepartment" id="hiddendepartment" readonly="readonly" value="" class="form-control"/>
								<!-- value="<logic:present name="religionList"><bean:write name="religionList" property="religionId" /></logic:present>" -->
							</div>
						</div>
					</div>
					<div  id="designation"
						style="font-size: 13px;display: none;left:54px;">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Designation</label>
							<div class="col-xs-7">
								<input type="text" name="hiddendesignation" id="hiddendesignation" readonly="readonly" value="" class="form-control"/>
								<!-- value="<logic:present name="religionList"><bean:write name="religionList" property="casteId" /></logic:present>" -->
							</div>
						</div>
					</div>		
					
				    	<div class="form-group clearfix admissionno" id="contactno" style="display:none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Contact No</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											id="hiddencontactno" name="hiddencontactno" />
                                      <!-- value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="ddc"/></logic:present>"  -->
									</div>
								</div>
								
								<div class="form-group clearfix " id="addr" style="display:none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Address</label>
									<div class="col-xs-7">
										<textarea  readonly="readonly" class="form-control"
											id="hiddenotheraddr" name="hiddenotheraddr"> </textarea>
                                      <!-- value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="ddc"/></logic:present>"  -->
									</div>
								</div>

							</div>

							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div
											style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 173px; height: 172px;">
											<img id="imagePreview" class="setImage" alt="image"
												src="images/profile_photo.png"
												style="height: 45mm; width: 45mm;"> <input
												type="hidden" id="hidenimage"
												value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property='imageUrl'/></logic:present>" />
											
											<span id="removeSpanId" class="close"
												style="position: absolute; top: 0px; right: 118px; color: red;"></span>
										</div>
									</div>

								</div>
						<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Shelf No</label>
									<div class="col-xs-7">
									<input type="text" readonly="readonly" class="form-control"
											name="" tabindex="5" id="shelfNo" maxlength="25"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="shelfNno"/></logic:present>" />
									</div>
								</div>
								
								
						<div class="form-group clearfix">

									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Library Branch</label>
									<div class="col-xs-7">
										<input type="text" id="locationname" class="form-control"
											name="locationnid" readonly="readonly"
											value="<logic:present name='studentList' scope='request'><bean:write name='studentList' property="locationName"/></logic:present>" />

									</div>
								</div>
							
						
						<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"> From Date<font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" class="form-control" readonly="readonly" onkeypress="HideError()"
											name="fromdate" id="Fromdate" value""/>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"> To Date<font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" class="form-control" readonly="readonly" onkeypress="HideError()"
											name="fromdate" id="ToDate" value""/>
									</div>
								</div>
							
								<!-- <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"> Priority </label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="priority" value""/>
									</div>
								</div>	
								 -->
								
								
								
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
	
</html>
