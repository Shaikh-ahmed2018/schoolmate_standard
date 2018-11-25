<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>
 <script type="text/javascript" src="JS/backOffice/Settings/AddLab.js"></script> 
<script type="text/javascript">

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


</head>

<body>
	<div class="content" id="div-main">
		<p>
			<img src="images/addstu.png" style="vertical-align: top;"/>&nbsp;<span id="pageHeading">New Practical</span>
		</p>
					<logic:present name="successmessagediv" scope="request">
						<div class="successmessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span><bean:write
											name="successmessagediv" scope="request" /></span></a>
							</div>
						</div>
					</logic:present>
					
						<logic:present name="errormessagediv" scope="request">

							<div class="errormessagediv" align="center">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-warning bg-msg-warning"><span style="color:red ;">
											<bean:write name="errorMessage" scope="request" />
									</span></a>
								</div>

							</div>

						</logic:present>


					<div class="errormessagediv" align="center" style="display: none;">
						<div class="message-item">
						
							<a href="#" class="msg-warning bg-msg-warning"><span
								class="validateTips"></span></a>
						</div>
					</div>
					
					
					
					<div class="successmessagediv" align="center">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span class="validateTips">
											</span></a>
							</div>
						</div>
					
				
		      
		<form id="SubjectForm" enctype="multipart/form-data" >
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#" style="color: #000000"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Practical Details
							</h3></a>
							

							<div class="navbar-right">
								 <span id="save" class="buttons">Save</span> 
								<span id="back" class="buttons" >Back</span> 
							</div>
					</div>
					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="clearfix addLabForm">
							<div class="col-md-6" id="txtstyle" style="font-size: 13px;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Branch<span style="color: red;">*</span></label>
									<div class="col-xs-7">
									<select id="locationname" name="locationName" class="form-control">
									<logic:present name="locationList">
										<logic:iterate id="Location" name="locationList">
											<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
										</logic:iterate>
										</logic:present>
						
									</select>
									</div>
								<input type="hidden" name="schoolId" class="form-control" id="schoolId" value='<logic:present name="list"><bean:write name="list" property="locationId" /></logic:present>'></input>
							</div>
						
                              
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Specialization</label>
									<div class="col-xs-7">
									<select id="specialization" name="specialization" class="form-control">
										
										<option value="-" selected="selected">----------Select----------</option>
										<logic:present name="specialization">
										<logic:iterate id="specialization" name="specialization">
											<option value="<bean:write name="specialization" property="specializationId"/>"><bean:write name="Location" property="specializationName" /></option>
										</logic:iterate>
										</logic:present>
						
									</select>
									</div>
								</div>	
							
								
							
								
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Practical Name<span style="color: red;">*</span></label>
									<div class="col-xs-7">
											<input type="text" style="text-transform: capitalize;" maxlength="25"  name="lab_name" onkeypress="return isNumberKey(event);" onclick="HideError(this)" id="lab_name"  class="form-control"    
											value='' />
									</div>
								</div>
								
						
									<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
											<textarea rows="3px" cols="3px" name="description" id="description" class="form-control" placeholder="" ><logic:present name="singlelabdetails"><bean:write name="singlelabdetails" property="description" /></logic:present></textarea>
									</div>
								</div>
								
								
							</div>
							<div class="col-md-6" id="txtstyle">
								
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class<span style="color: red;">*</span></label>
									<div class="col-xs-7">
										<select name="classname" id="classname" class="form-control" onkeypress="HideError()" onchange="HideError(this)">
									<option value="">----------Select----------</option>
									
							    </select>
									</div>
								</div>
								
								 <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Subject<span style="color: red;">*</span></label>
										<div class="col-xs-7">
										<select id="subject" name="subjtname" class="form-control" onchange="HideError(this)">
										<option value="">----------Select----------</option>
										
				                        </select>
				                        </div>
									
								</div>
								
								
								
						
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Practical Code<span style="color: red;">*</span></label>
									<div class="col-xs-7">
											<input type="text" maxlength="10" name="subjectCode" id="subjectCode" onkeypress="return isNumberKey(event);" class="form-control"   onclick="HideError(this)" placeholder="" 
											value='<logic:present name="singlelabdetails"><bean:write name="singlelabdetails" property="subjectCode" /></logic:present>' />
									</div>
								</div>
							
							</div>
							
						</div>
					</div>
					
				
				   </div>
				
					
		</div>
			   <input type="hidden" name="hiddenlocation" id="hiddenlocationid" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="locationName"/></logic:present>"/>   
               <input type="hidden" name="hiddenclass" id="hiddenclassid" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="classname"/></logic:present>"/>
               <input type="hidden" name="hiddensubject" id="hiddensubject" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="subjtname"/></logic:present>"/>
               <input type="hidden" name= "hiddendescription" id="hiddendescription" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="description"/></logic:present>"/>
             <%--   <input type="hidden" name="hiddenfile" id="hiddenfile" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property=""/></logic:present>"/> --%>
               <input type="hidden" name="hiddenlabid" id="hiddenlabid" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="lab_id"/></logic:present>"/>
             <%--   <input type="hidden" name="status" id="statusid" value="<logic:present name="singlelabdetails" ><bean:write name="singlelabdetails" property="status"/></logic:present>"/> --%>
				
		</form>
	</div>
	
	
	
</body>

</html>
