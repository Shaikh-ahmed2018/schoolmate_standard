<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/backOffice/Fee/ClassFeeSetupSpecialization.js"></script>
</head>



<body>

	<div class="content" id="div1">
		<div id="dialog"></div>
		<div id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Class Fee Setup</span>
			</p>
		</div>

			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
				</div>
			</div>
		

		
			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<a href="#" class="msg-success bg-msg-succes"><span class="sucessmessage"></span></a>
				</div>
			</div>
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span> Class Fee Setup Details
					</h3></a>
				
				<div class="navbar-right">
					
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
							<logic:equal value="CLSFEEADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									  <span class="buttons" id="save">Save </span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
					 <span id="back1" class="buttons">Back</span>
				</div>
				
				
				<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>
			</div>

      <input type="hidden" id="historylocId" 
		value='<logic:present name="historylocId" scope="request" ><bean:write name="historylocId" /></logic:present>'/>

      <input type="hidden" id="historyacademicId" 
		value='<logic:present name="historyacademicId" scope="request" ><bean:write name="historyacademicId" /></logic:present>'/>

		
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 10pt; font-weight: 700; color: #000;">
					<div
						style="padding: 7px 170px; max-height: 500px; overflow: scroll;">
						<table class="table" id="allstudent" cellpadding="5"
							cellspacing="0" border="1" width="100%">
							<tr>
								<th colspan="2"
									style="font-size: 17px; font-family: Roboto,sans-serif !important; background-color: #f5f5f5; text-align: center;"><logic:present name="locationname"><bean:write name="locationname"/></logic:present></th>
							</tr>
							<tr>
								<th colspan="2"
									style="font-size: 17px; font-family: Roboto,sans-serif !important; background-color: #f5f5f5; text-align: center;">CLASS FEE SETUP FOR THE YEAR OF <b><logic:present name="acadamicYearVal"><bean:write name="acadamicYearVal" /></logic:present></b>
								</th>
							</tr>


							<tr>
								<td colspan="1"
									style="font-size: 10pt; font-weight: 700; text-align: right;width:50%;">Class Name</td>
								<td colspan="1"
									style="font-size: 10pt; font-weight: 700; text-align: left;">
								<logic:present name="classidVal"><bean:write name="classidVal" /></logic:present>

								</td>
							</tr>


							


						</table>
						<br />
			<input type="hidden" id="haccYear" value='<logic:present name="accYear" scope="request" ><bean:write name="accYear" /></logic:present>' />
			<input type="hidden" id="hclassId" value='<logic:present name="classId" scope="request" ><bean:write name="classId" /></logic:present>' />
			<input type="hidden" id="hlocationId" value='<logic:present name="locationId" scope="request" ><bean:write name="locationId" /></logic:present>' />
			
			<logic:present name="getSpecializationFeeMasterSetupDetails" scope="request">
				<%-- <logic:iterate id="getActiveFee" name="getSpecializationFeeMasterSetupDetails" scope="request"> --%>
					<logic:iterate id="getTermFee" name="getSpecializationFeeMasterSetupDetails" >
					
					<!-- Term Name -->
					
						<table class="table classfeesetup <bean:write name="getTermFee" property="specialization_name"  />" cellpadding="5" cellspacing="0" border="1" width="100%" name='<bean:write name="getTermFee" property="specialization_name"  />'>
						
							<tr class="heading">
								<th  colspan="4" style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto,sans-serif !important;"><span
									class="glyphicon glyphicon-menu-hamburger"
									style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>SPECIALIZATION  <bean:write name="getTermFee" property="specialization_name"  />-<bean:write name="getTermFee" property="termname"  /> Fees</th>
							</tr>
							<tr class="collapsable">
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto,sans-serif;"><input type="checkbox" class="selectAll" /></th>
								<th style="font-size: 14px; text-align: left; background: #f9f9f9 !important; font-family: Roboto,sans-serif;">Fee Description</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto,sans-serif;">Fee Amount (New Students)</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto,sans-serif;">Fee Amount (Old Students)</th>
							</tr>
							<logic:iterate id="getFee" name="getTermFee" property="feeNamelist">
							<tr class="collapsable">
							
								<td style="font-size: 10pt; font-weight: 700; text-align: center;">
									<input type="checkbox" name="<bean:write name="getTermFee" property="specialization_id"  />" class="select <bean:write name="getFee" property="status" /> <bean:write name="getFee" property="feesettingcode" />" value='<bean:write name="getFee" property="feecode" />,<bean:write name="getTermFee" property="termid"  />' />
								</td>
								<td style="font-size: 10pt; font-weight: 700; text-align: left;"><bean:write name="getFee" property="feename" /></td>
								<td style="font-size: 13px; font-weight: 700; text-align: center;">
									<input type="text" name="classFeeAmountNew" class="classFeeAmountNew" maxlength="50" value="<bean:write name="getFee" property="newStuFee" />" style="width: 75%; text-align: right;"  readonly='readonly'/>
								</td>
								<td style="font-size: 10pt; font-weight: 700; text-align: center; width: 40%;">
									<input type="text" name="classFeeAmount" class="classFeeAmount" maxlength="50" value="<bean:write name="getFee" property="feeAmount" />" style="width: 54%; text-align: right;"  readonly='readonly'/>
								</td>
								
							</tr>
							</logic:iterate>
							<tr>
								<th style="font-size: 14px; background: #f9f9f9 !important;"></th>
								<th style="font-size: 14px; background: #f9f9f9 !important; font-family: Roboto,sans-serif; text-align: right;">SPECIALIZATION  <bean:write name="getTermFee" property="specialization_name"  />-<bean:write name="getTermFee" property="termname"  /> Fees Total</th>
								<th style="font-size: 14px; background: #f9f9f9 !important;  text-align:center;">
									<input type="text" name="totalFeeAmountNew" class="totalFeeAmountNew" maxlength="50" value="" style="width: 75%; text-align: right;background-color: rgba(255, 224, 0, 0.22);" readonly="readonly" />
								</th>
								<th style="font-size: 14px; background: #f9f9f9 !important; font-family: Roboto,sans-serif; text-align:center;">
									<input type="text" name="totalFeeAmount" class="totalFeeAmount" maxlength="50" value="" style="width: 54%; text-align: right; background-color: rgba(255, 224, 0, 0.22);" readonly="readonly" />
								</th>
							</tr>

						</table>
						
						</logic:iterate>
					
					<%-- </logic:iterate> --%>
			</logic:present>
						
						
						
						
	
					


					</div>
				</div>
			
			</div>
		</div>
	</div>
	
</body>
</html>



	