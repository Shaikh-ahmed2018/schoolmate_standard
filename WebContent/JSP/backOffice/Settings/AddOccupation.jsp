<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>

  <script type="text/javascript" src="JS/backOffice/Settings/AddOccupation.js"></script>
 


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
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#" style="color: #000000"><h3 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Occupation Details
						</h3></a>
					

					<div class="navbar-right">
						<span id="saveReligionId" class="buttons">Save
						</span>


						<span id="back1" class="buttons">Back</a></span>
					</div>
				</div>
	
	<input type="hidden" id="hiddensearchname" 
	value='<logic:present name="searchname1" scope="request"><bean:write name="searchname1" /></logic:present>'></input>
	
	<input type="hidden" id="hiddenstatus" 
	value='<logic:present name="status1" scope="request"><bean:write name="status1" /></logic:present>'></input>			
	
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-bottom:6px;">
							<div class="form-group">

					 <input type="hidden" name="occupationId" class="streamidclass"
					      id="occupationId" value='<logic:present name="religionList"><bean:write name="religionList" property="occupationId" />
													</logic:present>'></input> 

								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Occupation</label>
								<div class="col-xs-7">
									<input type="text" name="religion" id="occupation" onclick="HideError(this)"
										class="form-control" placeholder="" maxlength="30"
										value='<logic:present name="religionList"><bean:write name="religionList" property="occupation" /></logic:present>'></input>
								</div>
								<input type="hidden" id="hiddenoccupation" value="<logic:present name="religionList"><bean:write name="religionList" property="occupation" /></logic:present>"/>
							</div>
							<br />

							

						</div>
					</div>
				</div>


			</div>

		</div>

	</div>






</body>
</html:html>