<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <script type="text/javascript" src="JS/backOffice/Settings/AddPeriod.js"></script>

<script type="text/javascript">
	
function  CheckIsNumeric(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode < 48 || charCode > 57) {
        
        return false;
    }
    else {
       
        return true;
    }
}
</script>

</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"></bean:write></logic:present></span>
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
					<a href="#" class="msg-success bg-msg-succes"><span class="validateTips"></span></a>
				</div>
			</div>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#" style="color: #000000"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Period Details
							</h3></a>
							
							<div class="navbar-right" >
								 <span id="Save" class="buttons"
									 style="margin-right:6px;">Save
									</span> 
									<span class="buttons" id="back"
									 style="margin-right:2px;">Back</a></span>
							</div>
					</div>

					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
					   <div class="panel-body clearfix">
						 <div class="clearfix">  
							<div class="col-md-6"  id="txtstyle"
								style="font-size: 13px; color: #000;">
								
								<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
						        <select id="locationname" name="locId" class="form-control" onchange="HideError(this)">
								<logic:present name="locationList">
									<logic:iterate id="Location" name="locationList">
										<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
						
						</select>
						</div>
								
							</div>
				
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
								
					           <select name="clsId" id="classId"
						         class="form-control" onchange="HideError(this)">
							   <option value="">-------------------Select-------------------</option>
					          </select>
									</div>
								</div>
					
							</div>
							<div class="col-md-6"
								style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Stream
										</label>
									<div class="col-xs-7">
									         
									<select name="streamId" id="StreamName"
						            class="form-control" onchange="HideError(this)">
							        <option value="">-------------------Select-------------------</option>
					               </select>
									
										<%-- <input type="text" class="form-control" id="sectionName" onclick="HideError(this)"
											placeholder="" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="secDetailsName"/></logic:present>"/> --%>
											
									</div>
									
								</div>
					
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Number of Period</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="noofperiod" id="noofPeriod" onclick="HideError(this)" onkeypress="return CheckIsNumeric(event);"
										value='<logic:present name="editelist"><bean:write name="editelist" property="noofperiod"/></logic:present>'/>
											
									</div>
								</div>
									<input type="hidden" name="locId" id="hiddenlocId" value="<logic:present name="editelist" ><bean:write name="editelist" property="locId"/></logic:present>"/>
											<input type="hidden" name="streamId" id="hiddenstreamId" value="<logic:present name="editelist" ><bean:write name="editelist" property="streamId"/></logic:present>"/>
											<input type="hidden" name="clsId" id="hiddenclsId" value="<logic:present name="editelist" ><bean:write name="editelist" property="clsId"/></logic:present>"/>
											<input type="hidden" name="hiddenperiodId" id="hiddenperiodId" value="<logic:present name="editelist" ><bean:write name="editelist" property="slno"/></logic:present>"/>
						
						         </div>
							</div>
						</div>
					</div>
				</div>
	
	</div>
	</div>
</body>

</html>
