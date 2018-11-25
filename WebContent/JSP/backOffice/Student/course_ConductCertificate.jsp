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

<script type="text/javascript" src="JS/backOffice/Student/courseCertificate.js"></script>
 

<script>

function isNumberKey(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode
    if ((charCode != 43) && (charCode > 31) && (charCode != 32) && (charCode < 48 || charCode > 57) && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122))
        return false;
    else
    return true;
} 

</script>
<style type="text/css">
 @page 
    {
        size:  auto;   
        margin: 0mm;
    }
</style>

</head>

<body>
	
	<div id="dialog" style="display:none;"><p>Are you sure want to Save?</p></div>
	<div id="dialog1" style="display:none;"></div>
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Character & Conduct Certificate</span></p>
				<div class="panel-body clearfix"
					style="font-family: Roboto,sans-serif; font-size: s13px; color: #000;padding-top: 0;">
				</div>
			
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
		
	<input type="hidden" id="schName"
 value='<logic:present name="schoolName" scope="request"><bean:write name="schoolName"/></logic:present>'/>					
	
	
	<input type="hidden" id="pincode"
 value='<logic:present name="pincode" scope="request"><bean:write name="pincode"/></logic:present>'/>
 					
		<input type="hidden" id="doj" value="<logic:present name="stuList"><bean:write name="stuList" property="entryDate"/></logic:present>">
		<input type="hidden" id="endDate" value="<logic:present name="stuList"><bean:write name="stuList" property="endDate"/></logic:present>">

<input type="hidden" id="schoolLogo"
 value='<logic:present name="schoolLogo" scope="request"><bean:write name="schoolLogo"/></logic:present>'/>					
			
	
	
		
		
						
	<input type="hidden" id="hiddencustSchoolAddres" value='<logic:present name="custSchoolAddres" scope="request"><bean:write name="custSchoolAddres"/></logic:present>'/>
	<input type="hidden" id="hiddenAddres2" value='<logic:present name="custSchoolAddres1" scope="request"><bean:write name="custSchoolAddres1"/></logic:present>'/>
	<%-- <input type="hidden" id="hiddencustSchoollogo" value='<logic:present name="custSchoollogo" scope="request"><bean:write name="custSchoollogo"/></logic:present>'/>
	<input type="hidden" id="custSchoolboardlogo" value='<logic:present name="custSchoolboardlogo" scope="request"><bean:write name="custSchoolboardlogo"/></logic:present>'/>
	<input type="hidden" id="hiddencustSchoolaffilno" value='<logic:present name="custSchoolaffilno" scope="request"><bean:write name="custSchoolaffilno"/></logic:present>'/>
	<input type="hidden" id="hiddencustSchoolno" value='<logic:present name="custSchoolno" scope="request"><bean:write name="custSchoolno" /></logic:present>'/>
	<input type="hidden" id="hiddencustSchoolwebsit" value='<logic:present name="custSchoolwebsit" scope="request"><bean:write name="custSchoolwebsit"/></logic:present>'/>
	<input type="hidden" id="hiddencustSchoolEmail" value='<logic:present name="custSchoolEmail" scope="request"><bean:write name="custSchoolEmail"/></logic:present>'/>
	 --%><input type="hidden" id="hiddenboard" value='<logic:present name="board" scope="request"><bean:write name="board"/></logic:present>'/>
	<input type="hidden" id="dateofBirthInWords" value="<logic:present name="stuList"><bean:write name="stuList" property="dateofBirthInWords"/></logic:present>"/>	

<input type="hidden" id="historyaccyear"
 value='<logic:present name="historyaccyear" scope="request"><bean:write name="historyaccyear"/></logic:present>'/>
 
<input type="hidden" id="historylocation"
 value='<logic:present name="historylocation" scope="request"><bean:write name="historylocation"/></logic:present>'/>
 
<input type="hidden" id="historyclassid"
 value='<logic:present name="historyclassid" scope="request"><bean:write name="historyclassid"/></logic:present>'/>
 
<input type="hidden" id="historysection"
 value='<logic:present name="historysection" scope="request"><bean:write name="historysection"/></logic:present>'/>
 
<input type="hidden" id="historysearchtext"
 value='<logic:present name="historysearchtext" scope="request"><bean:write name="historysearchtext"/></logic:present>'/>
 
<input type="hidden" id="historystatus"
 value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>'/>
 
<input type="hidden" id="historyfindcerti"
 value='<logic:present name="historyfindcerti" scope="request"><bean:write name="historyfindcerti"/></logic:present>'/>

		
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Character & Conduct Certificate
							</h3></a>
						
						<div class="navbar-right">
						
							<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="AABCADD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
											<span class="buttons" id="save" style="top:4px;">Save</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
							</logic:present>
						
						
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="AABCDWD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
											<span class="buttons" id="print" style="top:4px;">Print</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
							</logic:present>
						
						
						
						
						
							<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="AABCUPD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
											<span class="buttons" id="rst"  style="top:4px;">Reset</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
							</logic:present>
							
							
						
							<span id="back1" class="buttons" style="top:4px;">Back</span>
						</div>
						
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
							
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" style="background-color: #fff;" tabindex="1"	 maxlength="25" class="form-control" readonly="readonly"
											id="accyearname" value='<logic:present name="stuList"><bean:write name="stuList" property="accyearid"/></logic:present>' />
									<input type="hidden" id="hiddenaccyearid" value="<logic:present name="accyearid"><bean:write name="accyearid"/></logic:present>"/>
									</div>
								</div>
								
								
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly"  style="background-color: #fff;" tabindex="2" id="schoolNames"class="form-control" value='<logic:present name="stuList"><bean:write name="stuList" property="locname"/></logic:present>' />
										<input type="hidden" id="hiddenlocationid" value="<logic:present name="locationid"><bean:write name="locationid" /></logic:present>"/>
									</div>
								</div>
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" style="background-color: #fff;" tabindex="1"	id="stuname"
											maxlength="25" class="form-control" 
											value='<logic:present name="stuList"  scope="request"><bean:write name="stuList" property="stuname"/></logic:present>' />
									<input type="hidden" id="hiddenstuid" value='<logic:present name="stuid" scope="request"><bean:write name="stuid"/></logic:present>'/>
									<input type="hidden" id="hiddenstugender" value='<logic:present name="stuList" scope="request"><bean:write name="stuList" property="gender"/></logic:present>'/>
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" style="background-color: #fff;" class="form-control" tabindex="4" id="admissionno"
											value='<logic:present name="stuList" scope="request"><bean:write name="stuList" property="admissionno"></bean:write></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" id="classname" style="background-color: #fff;" class="form-control" 
											value='<logic:present name="stuList" scope="request"><bean:write name="stuList" property="classname"/></logic:present>' />
										<input type="hidden" id="hiddenclassid" value='<logic:present name="classid" scope="request"><bean:write name="classid"/></logic:present>'/>
									</div>
								</div>



								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Father's Name</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" style="background-color: #fff;" tabindex="1"	id="fathername" class="form-control" 
											value='<logic:present name="stuList"><bean:write name="stuList" property="fathername"/></logic:present>' />
									</div>
								</div>
								
								<%-- <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Date Of Birth</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" style="background-color: #fff;" tabindex="1"	id="dob"
											maxlength="25" class="form-control" 
											value='<logic:present name="stuList"><bean:write name="stuList" property="dob"/></logic:present>' />
									</div>
								</div>
								 --%>
					<input type="hidden" id="dob" value="<logic:present name="stuList"><bean:write name="stuList" property="dob"/></logic:present>"/>			
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Conduct No<font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" name="conductno" id="conductno" maxlength="40" class="form-control" onkeypress="HideError(this)""/>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Purpose<font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" 	id="purpose" maxlength="240" class="form-control" onkeypress="HideError(this)" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Other Info</label>
									<div class="col-xs-7">
										<input type="text" tabindex="1"	id="otherinfo"
											maxlength="25" class="form-control" />
									</div>
								</div>
							</div>
							
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<!-- <div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 132px; height: 125px;">
										</div> -->							
												<!-- <div style="position: absolute;top: 0;height: 160px;"></div> -->
												<div class="form-group clearfix">
												<img alt="no image found" height="132px" width="125px" src='<logic:present name="stuList"><bean:write name="stuList" property="imgurl"/></logic:present>'>
												</div>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student Status</label>
									<div class="col-xs-7">
										<input type="text"  class="form-control" id="studentstatus" readonly="readonly" style="background-color: #fff;"
											value='<logic:present name="stuList"><bean:write name="stuList" property="studentstatus"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<input type="text"  id="sectionname" readonly="readonly" style="background-color: #fff;" tabindex="1" maxlength="25" class="form-control" 
											value='<logic:present name="stuList"><bean:write name="stuList" property="sectionname"/></logic:present>' />
										<input type="hidden" id="hiddensectionid" value='<logic:present name="sectionid"><bean:write name="sectionid"/></logic:present>'>
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Mother's Name</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" style="background-color: #fff;" tabindex="1"	id="motherName" class="form-control" 
											value='<logic:present name="stuList"><bean:write name="stuList" property="motherName"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Award<font color="red">*</font></label>
									<div class="col-xs-7">
										<select class="form-control"  name="conduct"  id="conduct">
										<option value="">----------Select----------</option>
										<option value="Good">Good</option>
										<option value="Very Good">Very Good</option>
										<option value="Excellent">Excellent</option>
										</select>
										<input type="hidden" name="hconduct"  id="hconduct" maxlength="25" class="form-control" onkeypress="HideError(this)"
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
							</div>
						</div>
						
						<hr id="heading" style="height:1px;border:none;color:#333;background-color:#ddd;"/>
					
						<div>
						<div class="slideTab clearfix" id="issued">
						<div class="tab">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="issue"  id="issue">Issue History</a></li>
							</ul>
							
							<!-- <ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#contacts" onclick="OpenContacts" id="#contacts" class="contacts">Contacts</a></li>
								<li><a data-toggle="tab" href="#classHistory" onclick="classHistory" id="#classHistory" class="classhistory">Class History</a></li>
							</ul> -->
						
						<div id="issue" class="tab-pane">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap">
									<div class="col-md-8" id=div2></div>
									<div id="certificatetable"></div>
								</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</html>


	
