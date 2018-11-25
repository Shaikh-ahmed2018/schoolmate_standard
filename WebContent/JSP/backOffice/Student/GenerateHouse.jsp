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

<script type="text/javascript" src="JS/backOffice/Student/generateHouse.js"></script>
<style type="text/css">

.Not{
background-color:#FF0000;
min-width:80px;
display:inline-block; 
text-align:center;
 color:#fff;
}
.Set{
background-color:rgba(0, 158, 0, 0.66);
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
#markstable{
position: relative;
padding-bottom: 20px;
}
</style>

</head>

<body>
	<div class="col-md-10" id="div-main" style="font-size: 16pt;border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><span class="glyphicon glyphicon-user"></span>&nbsp;<span id="pageHeading">Generate House</span></p>
			<div class="errormessagediv" align="center" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>
			
					
						<div class="successmessagediv" align="center"  style="display: none;">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne" style="margin-bottom: 10px;">
						
							<a  href="#" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title class"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Generate House</h3></a>
						
					</div>

        <input type="hidden" id="historyaccyear" 
		value='<logic:present name="historyaccyear" scope="request"><bean:write name="historyaccyear"/></logic:present>'/>					
		
	    <input type="hidden" id="historylocId" 
		value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>'/>					

					<div id="classOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							
							
					<input type="hidden" name="hiddenlocationname" id="hiddenlocationname" value='<bean:write name="currentlocation"/>'/>
					<input type="hidden" name="hiddenlocationid" id="hiddenlocationid" value='<bean:write name="locationId"/>'/>
					<input type="hidden" name="academic_year" id="hiddenaccyear" value='<bean:write name="academic_year"/>'/>	
					<div class="clearfix"></div>
					
					<div class="row">
						 <div class="col-md-6">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1"name="accyear" id="accyear" >
												<logic:present name="accYearList" scope="request">
												<logic:iterate id="accYearList" name="accYearList">
												<option value='<bean:write name="accYearList" property="accyearId"/>'><bean:write name="accYearList" property="accyearname"/></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								</div>
								
							<div class="col-md-6">
							<input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">				
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="3"
											name="location" id="location" >
											
											<logic:present name="locationDetailsList" scope="request">
											<logic:iterate id="locationDetailsList" name="locationDetailsList">
											<option value='<bean:write name="locationDetailsList" property="location_id"/>'><bean:write name="locationDetailsList" property="locationname"/></option>
											</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
						</div>
				</div>	
					
						<!-- <div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Filter</label>
								<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()" tabindex="2"name="selection" id="selection" >
										<option value="">----------Select----------</option>
										<option value="namewise">Name Wise</option>
										<option value="namewisedesc">Name Wise Desc</option>
										<option value="orderadmission">By Admission No</option>
										<option value="orderadmiDesc">By Admission No Desc</option>
										<option value="orderadmiEven">By Admission No Even</option>
										<option value="orderadmiOdd">By Admission No Odd</option>
										<option value="admisnamewise">By Admission No Name Wise</option>
										<option value="admisnamewisedesc">By Admission No Name Wise Desc</option>
									</select>
								</div>
							</div>
						</div>	 -->
					</div>	
								
						<div id="markstable" class="clearfix">	</div>
						
						
						</div>
					</div>
				</div>
			</div>

</html>
