
<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>
  <script type="text/javascript" src="JS/backOffice/Bank/addBank.js"></script>
</head>
<script type="text/javascript">
function  CheckIsNumeric(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode!=32 && (charCode < 48 || charCode > 57) && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode >122)) {
        
        return false;
    }
    else {
       
        return true;
    }
}
function  CheckIsNumeric1(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode!=40 && charCode != 41 && charCode != 91 && charCode != 93 && charCode != 45 && charCode != 44  && charCode!=32 && (charCode < 48 || charCode > 57) && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode >122) )  {
        
        return false;
    }
    else {
       
        return true;
    }
}

</script>


<body>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin-bottom: 5px; margin-top: 5px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="msg"><bean:write name="msg"/></logic:present></span>
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
					class="successmessage"></span></a>
			</div>
		</div>
		
 <input type="hidden" id="historysearchname" 
	value='<logic:present name="searchname1" scope="request"><bean:write name="searchname1" /></logic:present>'></input>
	
	<input type="hidden" id="historystatus" 
	value='<logic:present name="status1" scope="request"><bean:write name="status1" /></logic:present>'></input>		

		<form id="bankForm">
			<input type="hidden" id="hbankId"
				value="<logic:present name="bankrecord" scope="request"><bean:write name="bankrecord" property="id" ></bean:write></logic:present>" />
			<input type="hidden" id="hbankStatus"
				value="<logic:present name="bankrecord" scope="request"><bean:write name="bankrecord" property="status" ></bean:write></logic:present>" />
			<input type="hidden" id="hbankName"
				value="<logic:present name="bankrecord" scope="request"><bean:write name="bankrecord" property="name" ></bean:write></logic:present>" />

			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">

						<a data-toggle="collapse" data-parent="#accordion" href="#"
							style="color: #000000"><h3 class="panel-title">
								<i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;New Bank dsfd
							</h3></a>

						<div class="navbar-right">
							<span id="savechanges" class="buttons" style="margin-right: 0px;">Save</span>
							<span id="back1" class="buttons" style="margin-right: 2px;">Back</span>
						</div>
					</div>



					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body clearfix">

							<div class="col-md-6"
								style="font-family: Roboto, sans-serif; font-size: 13px; color: #000; padding-bottom: 9px;">

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right;" id="inputnames">Bank Name</label>
									<div class="col-xs-7">
										<input type="text" name="bankname" id="bankname"
											class="form-control" maxlength="50" onkeypress="return CheckIsNumeric1(event);" 
											value="<logic:present name="bankrecord" scope="request"><bean:write name="bankrecord" property="name" ></bean:write></logic:present>" />
									</div>
								</div>
                                  
                                  <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right;" 	id="inputnames">Short Name</label>
									<div class="col-xs-7">
										<input type="text" name="bankshortname" id="bankshortname"
											maxlength="6" class="form-control" onkeypress="return CheckIsNumeric(event);" 
											value="<logic:present name="bankrecord" scope="request"><bean:write name="bankrecord" property="shortname" ></bean:write></logic:present>" />
									</div>
								</div>

                		</div>

				
							</div>
					</div>
				</div>
			</div>
		</form>
	</div>

</body>

</html>