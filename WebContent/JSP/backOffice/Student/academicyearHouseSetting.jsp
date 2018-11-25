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

<script type="text/javascript" src="JS/backOffice/Student/AcademicyearHouseSetting.js"></script>

<script type="text/javascript">
function isNumberKey(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122)))){
        return false;
    }   
    else{
    return true;
    }
} 

</script>

<style>
#allstudent {
	width : 100%;
}
#allstudent td{
	text-align: left;
}
#allstudent td:nth-child(3){
	padding: 0 7px;
}

#allstudent th:nth-child(1){
	width:100px;
}

</style>
</head>

<body>

  <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
  
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p class="col-md-12 input-group"><img src="images/addstu.png" style="vertical-align:top;"/><span id="pageHeading">House Setting</span></p>
					<div id="editDialog" style="display: none">
							<div class="col-md-12">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House Name <font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" id="housenameedit" class="form-control" onkeypress ="return isNumberKey(event);"/>
										<input type="hidden" id="hiddenhousename" class="form-control" />
									</div>
								</div>
							</div>
					</div>
					
					<div id="addDialog" style="display: none">
							<div class="col-md-12" style="margin-top:50px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: left; line-height: 35px;">House Name <font color="red">*</font></label>
									<div class="col-xs-7">
										<input type="text" name="housename" id="housename" class="form-control" onkeypress ="return isNumberKey(event);"/>
									</div>
								</div>
							</div>
					</div>
					
					<div id="deleteDialog" style="display: none">
					<p>Are you sure you want to Remove?</p>
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
				
				
			<!-- <div class="clearfix">
				
			</div> -->
			
	<input type="hidden" id="historyaccyear" 
	value='<logic:present name="historyaccyear" scope="request"><bean:write name="historyaccyear"></bean:write></logic:present>'/>
	
	<input type="hidden" id="historylocId" 
	value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"></bean:write></logic:present>'/>			
			
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne" style="margin-bottom: 10px;">
						
							<a  href="#" style="color: #000000;"> 
							<h3 class="panel-title grade"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;House Setting
							</h3></a>
						
							<div class="navbar-right" >
								<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="HOUSETADD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
						                   <span class="buttons" id="addgrade">New</span>
									</logic:equal>
								</logic:equal>
								</logic:iterate>
								</logic:present>
									<span id="back1" class="buttons">Back</span>
							</div>
						</div>
				

				<div id="gradeOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
						
						<div class="col-md-6" id="inputcolor"style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	
											maxlength="25" class="form-control" readonly="readonly"
											value='<logic:present name="locName"><bean:write name="locName"/></logic:present>' />
									</div>
									<input type="hidden" name="hiddenlocid" id="hiddenlocid" value='<logic:present name="locid"><bean:write name="locid"></bean:write></logic:present>'/>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="accyName" tabindex="1"	maxlength="25" class="form-control" readonly="readonly"
											value='<logic:present name="accyName"><bean:write name="accyName"/></logic:present>' />
									</div>
									<input type="hidden" name="hiddenaccyear" id="hiddenaccyear" value='<logic:present name="accid"><bean:write name="accid"></bean:write></logic:present>'/>
								</div>
				</div>
				<div class ="col-md-6"></div>
						
			<div class = "col-md-12">
					 
		        <logic:present name="gethouseSettingsList" scope="request">			
					<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Sl.No </th>
							<th>HouseName</th>
							<th></th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="gethouseSettingsList" name="gethouseSettingsList">
								<tr>
								<td><bean:write name='gethouseSettingsList' property="slno"/></td>
								<td class="housename"><bean:write name='gethouseSettingsList' property="housename"/></td>
								<td>
								 <logic:present name="UserDetails" scope="session">
								  <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									  <logic:equal value="HOUSETUPD" name="daymap" property="key">
									     <logic:equal value="true" name="daymap" property="value">
						                   <span class="buttons editing" id='<bean:write name="gethouseSettingsList" property='houseid'/>'>Modify</span>
									    </logic:equal>
								      </logic:equal>
								  </logic:iterate>
								</logic:present>
								
								<logic:present name="UserDetails" scope="session">
								   <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									 <logic:equal value="HOUSETDEL" name="daymap" property="key">
									   <logic:equal value="true" name="daymap" property="value">
						                  <span id='<bean:write name="gethouseSettingsList" property='houseid'/>' class="buttons delete" style="line-height: 40px;margin-left: 10px;">Remove</span>
									 </logic:equal>
								   </logic:equal>
								  </logic:iterate>
								</logic:present>
								
								</td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					
					</logic:present>
		
			
				<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks' style="margin-bottom: 10px" id="page"></div>
			</div>	
		</div>
			
				</div>
		</div>
	</div>
</div>
</html>


	
