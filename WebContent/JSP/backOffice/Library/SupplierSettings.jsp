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

<script type="text/javascript" src="JS/backOffice/Library/LibrarySupplierSettings.js"></script>


<script type="text/javascript">
	
function CheckIsNumeric(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode !=43 && charCode !=32 && charCode !=45 && charCode !=40 && charCode !=41  && charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
</script>



<style>

textarea {
	resize: none;
}
</style>
</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<div class="col-md-12 input-group">
		<p>
			<img src="images/addstu.png" style="vertical-align: top;" /><span id="pageHeading"><logic:present name="msg"><bean:write name="msg"/></logic:present></span></p>
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

		<div class="panel-group" id="accordion1" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingsup">
					
						<a data-toggle="collapse" data-parent="#accordion1"
							href="#collapsesup"
							style="color: #000000; vertical-align: text-top;"><h3 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Supplier Setting
							</h3></a>
				
   <input type="hidden" id="hiddenid" name="entry_id" value='<logic:present name="supid"><bean:write name="supid"/></logic:present>'>
   <input type="hidden" id="hiddensubnm"  value='<logic:present name="editlist"><bean:write name="editlist" property="suppliedBy"/></logic:present>'>
    <input type="hidden" id="hiddensubph"  value='<logic:present name="editlist"><bean:write name="editlist" property="suppliedBy"/></logic:present>'>
     <input type="hidden" id="hiddensubtel"  value='<logic:present name="editlist"><bean:write name="editlist" property="telephone" /></logic:present>'>
      <input type="hidden" id="hiddensubadd"  value='<logic:present name="editlist"><bean:write name="editlist" property="supplieraddress" /></logic:present>'>
       <input type="hidden" id="hiddensubemail"  value='<logic:present name="editlist"><bean:write name="editlist" property="email" /></logic:present>'>
        
                     <div class="navbar-right">
					<span class="buttons" id="save">Save</span>&nbsp;
					<span class="buttons" id="back1">Back </span>  &nbsp;


				</div>
			</div>
  
  <input type="hidden" id="historysearch"  
  value='<logic:present name="historysearch" scope="request"><bean:write name="historysearch"/></logic:present>' />
  
			<div id="collapsesup" class="panel-collapse collapse in "
				role="tabpanel" aria-labelledby="headingsup">


				<div class="panel-body own-panel">
					<div class="row">
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
							<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Supplier Name<span class='asteric'>*</span>
								</label>
								<div class="col-xs-7">
									<input type="text" id="Supplier"  name="suppliedBy" tabindex="1" maxlength="50" class="form-control" value='<logic:present name="editlist"><bean:write name="editlist" property="suppliedBy" /></logic:present>'></input>
								
								</div>
							</div>

							
							<div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Email Id<span class='asteric'>*</span></label>
								<div class="col-xs-7">
							
									<input type="text" id="emailid" name="email" tabindex="3" class="form-control" value='<logic:present name="editlist"><bean:write name="editlist" property="email" /></logic:present>'></input>

								</div>
							</div>
							
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Supplier Address<span class='asteric'>*</span></label>
								<div class="col-xs-7">
                            <textarea rows="3" cols="37"  id="Supplieraddress" name="supplieraddress" tabindex="5" ><logic:present name="editlist"><bean:write name="editlist" property="supplieraddress" /></logic:present></textarea>
                            
								</div>
							</div>
							
							
						</div>

						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">

					    	<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Supply Date<span class='asteric'>*</span>
									</label>
								<div class="col-xs-7">
								
									<input type="text" id="supdate" name="supdate" tabindex="2" class="form-control"   value='<logic:present name="editlist"><bean:write name="editlist" property="supdate" /></logic:present>'></input>
								</div>
							</div>


							<div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Telephone Number<span class='asteric'>*</span></label>
								<div class="col-xs-7">
							
									<input type="text" id="telephone" name="telephone" maxlength="20"  class="form-control"  tabindex="4"  onkeypress="return CheckIsNumeric(this);"  value='<logic:present name="editlist"><bean:write name="editlist" property="telephone" /></logic:present>'></input>

								</div>
							</div>
							<div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Mobile Number<span class='asteric'>*</span></label>
								<div class="col-xs-7">
							
							<input type="text" id="supmobnum" name="mobilenum" tabindex="6" maxlength="20" class="form-control" onkeypress="return CheckIsNumeric(this);" value='<logic:present name="editlist"><bean:write name="editlist" property="mobilenum" /></logic:present>'></input>

								</div>
							</div>
                                 <div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Fax</label>
								<div class="col-xs-7">
									<input type="text" id="Fax" name="fax" tabindex="8" class="form-control" maxlength="20" onkeypress="return CheckIsNumeric(this);"  value='<logic:present name="editlist"><bean:write name="editlist" property="fax" /></logic:present>'></input>
								</div>
							</div>


							


						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
