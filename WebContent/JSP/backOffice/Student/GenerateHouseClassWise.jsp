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

<script type="text/javascript" src="JS/backOffice/Student/HouseSettingClassWise.js"></script>
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
#allstudent{
width: 100%;
}
</style>

</head>

<body>
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><img src="images/addstu.png"/><span id="pageHeading">Generate House</span></p>
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
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a  href="#" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title class"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Generate House</h3></a>
						
						
						<div class="navbar-right">
						<span id="back1" class="buttons" style="line-height: 1.9;">Back</a></span>
						</div>
						
					</div>

		<input type="hidden" id="historyaccyear" 
		value='<logic:present name="historyaccyear" scope="request"><bean:write name="historyaccyear"/></logic:present>'/>					
		
	    <input type="hidden" id="historylocId" 
		value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>'/>			

					<div id="classOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							
							<div class="col-md-12" id="inputcolor">
				<div class="col-md-6" id="txtstyle" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<input type="text" name="locName" id="locName"class="form-control" readonly="readonly"
											value='<logic:present name="locName"><bean:write name="locName"/></logic:present>' />
											<input type="hidden" id="hiddenaccyear" value='<logic:present name="accid"><bean:write name="accid"/></logic:present>'/>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text"   id="accyName" class="form-control" readonly="readonly"
											value='<logic:present name="accyName"><bean:write name="accyName"/></logic:present>' />
										<input type="hidden" id = "hiddenlocid" value='<logic:present name="locid"><bean:write name="locid"/></logic:present>'/>
									</div>
								</div>
								
								<div class="form-group clearfix filters" style="display: none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Filter</label>
									<div class="col-xs-7">
										<%-- <input type="text" id="nameWise" class="form-control" readonly="readonly"
											value='<logic:present name="filter"><bean:write name="filter"/></logic:present>' /> --%>
											
											<input type="text" id="filter" class="form-control" readonly="readonly"
											value=""/>
											
									
									</div>
								</div>
							  </div>
								<input type="hidden" id="filter2" class="form-control" readonly="readonly" value='<logic:present name="filter1"><bean:write name="filter1"/></logic:present>' />
					
								<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">No. Of Students</label>
									<div class="col-xs-7">
										<input type="text" name="noofstudents" id="noofstudents" class="form-control" readonly="readonly"
											value='<logic:present name="noofstudents"><bean:write name="noofstudents" /></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Allocated Students</label>
									<div class="col-xs-7">
										<input type="text" name="allocatedstudents" id="allocatedstudents" class="form-control" readonly="readonly"
											value='<logic:present name="allocatedstudents"><bean:write name="allocatedstudents"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix selections" style="display: none;">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Filter<font color="red">*</font></label>
								<div class="col-xs-7 add">
									<select class="form-control" onkeypress="HideError()" tabindex="2"name="selection" id="selection" >
										<option value="">----------Select----------</option>
										<option value="namewise">Name Wise Ascending</option>
										<option value="namewisedesc">Name Wise Descending</option>
										<option value="admissionwise">Admission No. Ascending</option>
										<option value="byadminodesc">Admission No. Descending</option>
										<!-- <option value="byadminoeven">By Admission No. Even</option>
										<option value="byadminoodd">By Admission No. Odd</option> -->
										<!-- <option value="admisnamewise">By Admission No Name Wise</option>
										<option value="admisnamewisedesc">By Admission No Name Wise Desc</option> -->
									</select>
								</div>
							</div>
							</div>
				</div>	
							
							
					<table class='table studenttable' id='allstudent' width='100%'>
						<thead>
						<tr>
							<th>Sl.No.</th>
							<th style="text-align:center">Class</th>
							<th style="text-align:center">Strength</th>
							<th style="text-align:center">Allocated</th>
							<th style="text-align:center">Status</th>
						</tr>
						</thead>
						<tbody>
						<logic:present name="classwisehouse" scope="request">
					   	<logic:iterate id="classwisehouse" name="classwisehouse" scope="request">
					   		<tr >
					   		<td style="text-align:center" class="genehouid" id="<bean:write name="classwisehouse" property="genhouid"/>"><bean:write name="classwisehouse" property="slno" /></td>
					   		<td style="text-align:center" class="classId" id="<bean:write name="classwisehouse" property="classid"/>"><bean:write name="classwisehouse" property="classname"/></td>
					   		<td style="text-align:center" class="total"><bean:write name="classwisehouse" property="totalclassStrength"/></td>
					   		<td style="text-align:center" class="allocated"><bean:write name="classwisehouse" property="allocatedstudents"/></td>
							<td style="text-align:center"><span class="<bean:write name="classwisehouse" property="statusid"/> status"><bean:write name="classwisehouse" property="status"/></span></td>
					   		</tr>
					   	</logic:iterate>
					   </logic:present>
					   </tbody>
						</table>
						<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	
							</div><div class='pagination pagelinks'></div>
						</div>
					</div>
				</div>
			</div>
	</div>
</html>
