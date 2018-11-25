<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<script type="text/javascript" src="JS/backOffice/Settings/addGradeSettings.js"></script>
<script type="text/javascript">
	
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
	
	function CheckIsNumeric(objEvt) {
        var charCode = (objEvt.which) ? objEvt.which : event.keyCode
        if (charCode > 31 && charCode == 46 && charCode != 45 &&(charCode < 48 || charCode > 57)) {
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

.glyphicon:hover{

cursor: pointer;
}
#classSave:hover {
	cursor: pointer;
}
</style>

<style>
.buttons{
vertical-align: 0px;
}
.form-group{
margin-bottom: 10px;
}

/* .marks{
	max-width: 100px;
} */

#allstudent {
	width : 100%;
}
#allstudent td{
	text-align: center;
}

#allstudent th:nth-child(1){
width : 150px;

.glyphicon-edit{
top:5px;
}
#allstudent{
color : #333;
}

</style>
</head>

<body>
	<div id="mydialog1" style="display:none;">
	
		<div class="col-md-12" id="txtstyle"
								style="font-size: 13px; color: #000;">
		<div class="form-group clearfix">
			<label for="inputEmail" class="control-label col-xs-4"
			style="text-align: left; line-height: 35px;">Grade Name<font color="red">*</font></label>
		<div class="col-xs-8">
			<input type = "text" name="gradeName1" class="form-control gradename" id="gradeName1" onkeypress ="return isNumberKey(event);" value="" />
		</div>
		</div>
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
				style="text-align: left; line-height: 35px;">Comments<font color="red">*</font></label>
				<div class="col-xs-8">
					<input type = "text" class="form-control" id="comments1" onkeypress ="return isNumberKey(event);"/>
				</div>
			</div>
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
				style="text-align: left; line-height: 35px;">Min Marks<font color="red">*</font></label>
				<div class="col-xs-8">
					<input type = "text" class="form-control marks" id="minmarks1" maxlength="3" onkeypress="return CheckIsNumeric(event);"/>
				</div>
			</div>

			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
				style="text-align: left; line-height: 35px;">Max Marks<font color="red">*</font></label>
				<div class="col-xs-8">
					<input type = "text" class="form-control marks" id = "maxmarks1" maxlength="3" onkeypress="return CheckIsNumeric(event);"/>
				</div>
			</div>
		</div>
	
	</div>
	<div id="mydialog" style="display:none;">
	<p>Are you sure to Remove?</p>
	</div>
	<div id="dialog" style="display:none;">
			
	<div class="col-md-12" id="txtstyle" style="font-size: 13px; color: #000;">
								
			<!-- <div class=" form-group errormessagediv1" align="center" style="display: none;">
				<div class="message-item">
					Warning
					<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips1"></span></a>
				</div>
			</div> -->
		<div class="form-group clearfix">
			<label for="inputEmail" class="control-label col-xs-4"
			style="text-align: left; line-height: 35px;">Grade Name<font color="red">*</font></label>
		<div class="col-xs-8">
			<input type = "text" class="form-control gradename" id="gradeName" onkeypress ="return isNumberKey(event);"/>
		</div>
		</div>
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
				style="text-align: left; line-height: 35px;">Comments<font color="red">*</font></label>
				<div class="col-xs-8">
					<input type = "text" class="form-control" id="comments" onkeypress ="return isNumberKey(event);"/>
				</div>
			</div>
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
				style="text-align: left; line-height: 35px;">Min Marks<font color="red">*</font></label>
				<div class="col-xs-8">
					<input type = "text" class="form-control marks" id="minmarks" maxlength="3" onkeypress="return CheckIsNumeric(event);"/>
				</div>
			</div>

			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
				style="text-align: left; line-height: 35px;">Max Marks<font color="red">*</font></label>
				<div class="col-xs-8">
					<input type = "text" class="form-control marks" id = "maxmarks" maxlength="3" onkeypress="return CheckIsNumeric(event);"/>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Grade Setting</span>
		</p>

                  <logic:present name="successmessagediv" scope="request">
						<div class="successmessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes" ><span class="validateTips"><bean:write
											name="successmessagediv" scope="request" /></span></a>
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
  
    <input type="hidden" id="historylocId" 
     value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"></bean:write></logic:present>'/>
	
	<input type="hidden" id="historyacademicId" 
     value='<logic:present name="historyacademicId" scope="request"><bean:write name="historyacademicId"></bean:write></logic:present>'/>
     
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a href="#" style="color: #000000">
							<h3 class="panel-title grade"><i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Grade Details
							</h3></a>

							<div class="navbar-right" >
							<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
										property="permissionmap" scope="session">
									<logic:equal value="GRDADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
									<span class="buttons" id="addgrade" style="line-height: 40px;top:-3px;">New</span>
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
									<span id="back1" class="buttons">Back</span>
							</div>
					</div>
					
					<!-- <center> -->
						
					
					<!-- </center> -->
					
					<div id="gradeOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
						<div class="col-md-12">
							<div class="col-md-6"  id="txtstyle" style="font-size: 13px; color: #000;">
							<div class="form-group clearfix" id="inputcolor">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-6">
								<input type="text" readonly="readonly" name="location" onkeypress="HideError()" id="location"
											class="form-control" value='<logic:present name="locName" scope="request"><bean:write name="locName" ></bean:write></logic:present>' />
								</div>
								<input type="hidden" name="hiddenloc" id="hiddenloc" value='<logic:present name="locId" scope="request"><bean:write name="locId"></bean:write></logic:present>'/>
							</div>
							</div>
							<div class="col-md-6"  id="txtstyle" style="font-size: 13px; color: #000;">
							<div class="form-group clearfix" id="inputcolor">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Academic Year</label>
								<div class="col-xs-6">
								<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear"
											class="form-control" value='<logic:present name="accyName" scope="request"><bean:write name="accyName" ></bean:write></logic:present>' />
								</div>
								<input type="hidden" name="hiddenaccyid" id="hiddenaccyid" value='<logic:present name="accyId" scope="request"><bean:write name="accyId"></bean:write></logic:present>'/>
							</div>
							</div>
						</div>
						<div class="form-group">
							<div id="markstable">
							</div>
							
						
						</div>
				</div>
				</div>
			
		
	</div>
	</div>
	<form method="POST" action="menuslist.html?method=gradeList" id="myform">
		<input type="hidden" name="myValue" value="" id="myValue"/>
	</form>
</div>

</body>

</html>
