<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Biometric Attendence Solution</title>

<script type="text/javascript" src="JQUERY/Newjs/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.menu.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script> 
<script type="text/javascript" src="Newjs/BreakMaster.js"></script>
<link href="css/NewUI/bootstrap.min.css" rel="stylesheet" />
<link href="css/NewUI/backTop.css" rel="stylesheet" type="text/css" />
<link href="css/NewUI/modern-business.css" rel="stylesheet">
<link href="css/NewUI/custome.css" rel="stylesheet">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<style type="text/css">
	.tdtxt{
		text-align:center;
		border-right:1px solid #ddd;
	}
	.secondtd{
		border-right:1px solid #ddd;
	}
	.form-group {
    margin-bottom: 0px;
}
.col-md-2{
	text-align: right;
}
</style>
</head>


<body>
<form name="frm_usercreation" method="post" >
	<div class="col-md-11" id="topmenu">
	<p><span class="setup">Masters</span>&nbsp;&nbsp;<img alt="" src="NewImages/rightarrow.png">&nbsp;&nbsp;<span class="dmaster">Break Master</span>&nbsp;&nbsp;<img alt="" src="NewImages/rightarrow.png">&nbsp;&nbsp;<span class="dmaster">Add Break</span>
           <hr style="margin-right: 26px !important;">
			<h1 class="demaster">Add Break</h1>
			<hr style="margin-right: 26px !important;">	
			
		
		       
		<div class="col-md-11" id="sucesshover">
				<p class="success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<logic:present name="message" scope="request"><bean:write name="message"></bean:write></logic:present><span id="successmsg">X</span></p>
		</div>
		
		    <div class="col-md-12" id="errorhover" style="display: none;padding: 9px 21px;margin: 0 0px;width: 97.3%;">
				<p class="error"></p>
			</div>		
		
				
			<div class="col-md-11" style="padding:0px;    background: #EBEAEA;" >
				<p class="searchcc">DETAILS >&nbsp; <span class="searchd">CONFIRMATION</span></p>
				
			</div>
			
			<div class="col-md-11"> 	
		
		<div class="col-md-8 col-md-offset-2">
		<div class="col-md-12">
		
				<div class="col-md-10" id="divtop">
									
									<div class="form-group">
										<label for="inputPassword" class="control-label col-xs-4" id="inputnames" style="text-align: right; font-size: 15px;font-weight: 700;">Shift Name</label>
										<div class="col-xs-7" id="selecti">
											<select class="form-control" name="shift" id="shiftId">
											<option value="">----Select----</option>
											<logic:present name="shiftList" scope="request">
											<logic:iterate id="shift" name="shiftList">
											<option value='<bean:write name="shift" property="ai_shift_id"/>'><bean:write name="shift" property="ai_shift_name"/></option>
											
											</logic:iterate>
											
											</logic:present>
										</select>
										</div>
									</div><br/>
															
		</div>
		</div>
		
	
		
									
		
</div>

	
		
	
		<div class="col-md-12" id="divtop">
									
									<div class="form-group">
										<label for="inputEmail" class="control-label col-md-2" id="inputnames">Start Time</label>
										<div class="col-xs-2" >
										<input type="text" name="starttimne" id="starttime"  readonly="readonly"
										class="form-control"  value="<logic:present name="shiftDetails" scope="request"><bean:write name="shiftDetails" property="ai_shift_start_time"/></logic:present>"></input>
						       </div></div>
						       
									<div class="form-group" style="">
										<label for="inputEmail" class="control-label col-md-2" id="inputnames" style="margin-left: -85px;">End Time</label>
										<div class="col-xs-2" >
										<input type="text" name="endtimne"  id="endtime" readonly="readonly"
										 class="form-control" value="<logic:present name="shiftDetails" scope="request"><bean:write name="shiftDetails" property="ai_shift_end_time"/></logic:present>"></input>
						       </div></div>
						       
						       <div class="form-group" style="">
										<label for="inputEmail" class="control-label col-md-2" id="inputnames" style="margin-left: -55px;">Total Shift Time</label>
										<div class="col-xs-2" >
										<input type="text" name="shifttime"  id="shifttime" readonly="readonly"	 class="form-control" ></input>
						       </div></div>
						
			</div>	
		
	
		
	<div class="col-md-12"><p style="margin:0">&nbsp;</p></div>

				     
				
		      <input	type="hidden" name='ai_shift_break1stime' value='' /> <input
					type="hidden" name='ai_shift_break1etime' value='' /> <input
					type="hidden" name='ai_shift_break2stime' value='' /> <input
					type="hidden" name='ai_shift_break2etime' value='' /> <input
					type="hidden" name='ai_shift_break3stime' value='' /> <input
					type="hidden" name='ai_shift_break3etime' value='' /> 
					<input
					type="hidden" name='ai_shift_total1time' value='' /> 
					<input
					type="hidden" name='ai_shift_total2time' value='' /> 
					<input
					type="hidden" name='ai_shift_total3time' value='' /> 
					<input
					type="hidden" name='ai_shift_shiftid' value='' /> 
							
		<table class="table" style="border:1px solid #ddd">
		
		<tr>
		<td class="tdtxt">1 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
		<td><label for="inputEmail" class="control-label col-xs-12" id="inputnames">Break Start Time</label></td> 
		    <td class="secondtd"><select name="ai_shift_start_t" id="ai_shift_start_t1"        style="border: 1px solid #ccc;color:#555;">
										<option value="00" selected="selected">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<%
											for (int i = 10; i <= 23; i++) {
										%>
										<option value="<%=i%>"><%=i%></option>
										<%
											}
										%>
								</select> hrs <select name="ai_shift_start_m1" id="ai_shift_start_m1"  onchange="break1TotalTime()" style="border: 1px solid #ccc;color:#555;">
										<option value="00" selected="selected">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<%
											for (long k = 10; k <= 59; k++) {
										%>
										<option value="<%=k%>"><%=k%></option>

										<%
											}
										%>
								</select> min</td>
		    <td><label for="inputEmail" class="control-label col-xs-12" id="inputnames">Break End Time</label></td>
		    <td class="secondtd"><select name="ai_shift_end_t" id="ai_shift_end_t1" onchange="break1TotalTime()" style="border: 1px solid #ccc;color:#555;">
										<option value="00" selected="selected">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<%
											for (int i = 10; i <= 23; i++) {
										%>
										<option value="<%=i%>"><%=i%></option>
										<%
											}
										%>
								</select> hrs <select name="ai_shift_end_m1"  id="ai_shift_end_m1" onchange="break1TotalTime()" style="border: 1px solid #ccc;color:#555;">
										<option value="00" selected="selected">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<%
											for (long k = 10; k <= 59; k++) {
										%>
										<option value="<%=k%>"><%=k%></option>
										<%
											}
										%>
								</select> min</td>
								
			 <td><label for="inputEmail" class="control-label col-xs-12" id="inputnames">Total  Break Time</label></td>	
			  <td><input type="text" name="totaltimne"  id="totaltimne1"  readonly="readonly"   class="form-control" value=""></input></td>					
		  </tr>
		
		<tr>
		
		<td class="tdtxt">2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
		<td><label for="inputEmail" class="control-label col-xs-12" id="inputnames">Break Start Time</label></td> 
		    <td class="secondtd"><select name="ai_shift_start_t" id="ai_shift_start_t2"     style="border: 1px solid #ccc;color:#555;">
										<option value="00" selected="selected">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<%
											for (int i = 10; i <= 23; i++) {
										%>
										<option value="<%=i%>"><%=i%></option>
										<%
											}
										%>
								</select> hrs <select name="ai_shift_start_m2" id="ai_shift_start_m2"  onchange="break2TotalTime()"  style="border: 1px solid #ccc;color:#555;">
										<option value="00" selected="selected">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<%
											for (long k = 10; k <= 59; k++) {
										%>
										<option value="<%=k%>"><%=k%></option>

										<%
											}
										%>
								</select> min</td>
		    <td><label for="inputEmail" class="control-label col-xs-12" id="inputnames">Break End Time</label></td>
		    <td class="secondtd"><select name="ai_shift_end_t" id="ai_shift_end_t2" onchange="break2TotalTime()"  style="border: 1px solid #ccc;color:#555;">
										<option value="00" selected="selected">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<%
											for (int i = 10; i <= 23; i++) {
										%>
										<option value="<%=i%>"><%=i%></option>
										<%
											}
										%>
								</select> hrs <select name="ai_shift_end_m2"  id="ai_shift_end_m2" onchange="break2TotalTime()"  style="border: 1px solid #ccc;color:#555;">
										<option value="00" selected="selected">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<%
											for (long k = 10; k <= 59; k++) {
										%>
										<option value="<%=k%>"><%=k%></option>
										<%
											}
										%>
								</select> min</td>
								
			 <td><label for="inputEmail" class="control-label col-xs-12" id="inputnames">Total Break Time</label></td>	
			  <td><input type="text" name="totaltimne"  id="totaltimne2"  readonly="readonly"    class="form-control" value=""></input></td>	
			  
		  </tr>
		  <tr>
		  
		  <td class="tdtxt">3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
		<td><label for="inputEmail" class="control-label col-xs-12" id="inputnames">Break Start Time</label></td> 
		    <td class="secondtd"><select name="ai_shift_start_t" id="ai_shift_start_t3"       style="border: 1px solid #ccc;color:#555;">
										<option value="00" selected="selected">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<%
											for (int i = 10; i <= 23; i++) {
										%>
										<option value="<%=i%>"><%=i%></option>
										<%
											}
										%>
								</select> hrs <select name="ai_shift_start_m3" id="ai_shift_start_m3"  onchange="break3TotalTime()"  style="border: 1px solid #ccc;color:#555;">
										<option value="00" selected="selected">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<%
											for (long k = 10; k <= 59; k++) {
										%>
										<option value="<%=k%>"><%=k%></option>

										<%
											}
										%>
								</select> min</td>
		    <td><label for="inputEmail" class="control-label col-xs-12" id="inputnames">Break End Time</label></td>
		    <td class="secondtd"><select name="ai_shift_end_t" id="ai_shift_end_t3" onchange="break3TotalTime()"  style="border: 1px solid #ccc;color:#555;">
										<option value="00" selected="selected">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<%
											for (int i = 10; i <= 23; i++) {
										%>
										<option value="<%=i%>"><%=i%></option>
										<%
											}
										%>
								</select> hrs <select name="ai_shift_end_m3"  id="ai_shift_end_m3" onchange="break3TotalTime()"  style="border: 1px solid #ccc;color:#555;">
										<option value="00" selected="selected">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<%
											for (long k = 10; k <= 59; k++) {
										%>
										<option value="<%=k%>"><%=k%></option>
										<%
											}
										%>
								</select> min</td>
								
			 <td><label for="inputEmail" class="control-label col-xs-12" id="inputnames">Total Break Time</label></td>	
			  <td><input type="text" name="totaltimne"  id="totaltimne3"   readonly="readonly"  class="form-control" value=""></input></td>
		  </tr>
		</table>
		
			<div class="col-md-12" style="padding:0;"> &nbsp; </div>
			</div>
			
			<div class="col-md-11">
				<div class="col-md-12" style="background: #F9F9F9;">
				<br/>
					<!-- <input type="button" value="Back" class="backbutton" > -->
					       <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="BRKINSERT" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
							      <input type="button" value="Save Changes" class="savechanges" onclick="return validations()"/>	
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
					
				
					<br/><br/>
				</div>


	
	
	<input type="hidden" id="hshift" value='<logic:present name="selectedShift" scope="request"><bean:write name="selectedShift"></bean:write></logic:present>'>
	
			
		
  </div>
  	</div>
  	</form>
  	<logic:present name="breakDetails" scope="request">
  	<table id="hiddendata">
  	<logic:iterate id ="iddd" name="breakDetails" scope="request">
  	
    <tr><td class="bstarttime" style="display:none;"><bean:write name="iddd" property="breakstarttime"></bean:write></td>
    <td class="bendtime" style="display:none;"><bean:write name="iddd" property="breakendttime"></bean:write></td>
     <td class="btotaltime" style="display:none;"><bean:write name="iddd" property="bbreaktotaltime"></bean:write></td></tr>
  	</logic:iterate>
  	 </table>
  	
  	</logic:present>
  	
  	
</body>

</html>

