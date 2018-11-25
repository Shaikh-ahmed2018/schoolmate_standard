<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
 <script type="text/javascript" src="JS/backOffice/Settings/AddCasteCategory.js"></script>

</head>

<body>

	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p >
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"></bean:write></logic:present></span>
		</p>
		<center>
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div></center>

		<center>
		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div></center>

		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne" style="margin-bottom: 5px;">
					
						<a href="#" style="color: #000000"><h3 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Caste Category Details
						</h3></a>
					

					<div class="navbar-right">
						<span id="saveCasteCategoryId" class="buttons">Save</span>
						<span id="back1" class="buttons">Back</span>
					</div>
				</div>

				<div class="panel-body">
					<div class="col-md-6"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Religion<font style="color: red;">*</font></label>
							<div class="col-xs-7">
								<select name="religionName" id="religion" class="form-control" onchange="HideError(this)">
									<option value=""></option>
								</select>
								<%-- <option value="<logic:present name="religionList"><bean:write name="religionList" property="religionId" /></logic:present>"><logic:present name="religionList"><bean:write name="religionList" property="religion" /></logic:present></option> --%>
								<input type="hidden" id="hiddenreligionId" value="<logic:present name="religionList"><bean:write name="religionList" property="religionId" /></logic:present>"/>
							</div>
						</div>
					</div>
					<div class="col-md-6"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Caste<font style="color: red;">*</font></label>
							<div class="col-xs-7">
								<select name="caste" id="casteId"
									class="form-control" onchange="HideError(this)">
									<option value="">----------Select----------</option>
								</select>
								<input type="hidden" id="hiddencasteId" value="<logic:present name="religionList"><bean:write name="religionList" property="casteId" /></logic:present>"/>
								<%-- <logic:present name="religionList"><bean:write name="religionList" property="caste" /></logic:present> --%>
							</div>
						</div>
					</div>
				</div>

   <input type="hidden" id="historysearchname" 
	  value='<logic:present name="historysearchname" scope="request"><bean:write name="historysearchname" /></logic:present>'></input>
	
   <input type="hidden" id="historystatus" 
	  value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus" /></logic:present>'></input>

				<div class="panel-body">
						<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-bottom: 8px;">
							<div class="form-group">
					 			<input type="hidden" name="castecategoryId" class="streamidclass"
					     		 	id="castecategoryId" value='<logic:present name="religionList"><bean:write name="religionList" property="casteCategoryId" /></logic:present>'></input> 

								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Caste Category<font style="color: red;">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="casteCategory" id="casteCategoryNameId" onclick="HideError(this)"
										class="form-control" placeholder="" maxlength="30"
										value='<logic:present name="religionList"><bean:write name="religionList" property="casteCategory" /></logic:present>'></input>
								</div>
								<input type="hidden" id="hiddencastecategory" value="<logic:present name="religionList"><bean:write name="religionList" property="casteCategory" /></logic:present>" />
							</div>
							<br />
						</div>
					</div>
				</div>


			</div>

		</div>
</body>
</html:html>