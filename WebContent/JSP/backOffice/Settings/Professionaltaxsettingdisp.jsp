<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">

<head>
<script type="text/javascript" src="JS/backOffice/Settings/CountryStateCity.js"></script>
  <script type="text/javascript" src="JS/backOffice/Settings/ProfessionalTax.js"></script>
<script type="text/javascript">
	
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<style>
.glyphicon:hover{

cursor: pointer;
}
#classSave:hover {
	cursor: pointer;
}
#addPTSettings .form-control{
text-align: right;
}
</style>

<style>
#addPTSettings th:nth-child(1),#addPTSettings td:nth-child(1){
width: auto;
}
.buttons{

vertical-align: 0px;

}
.maxMonthlysalary{
text-transform: uppercase;
}
</style>
</head>

<body>
<div>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">PT Settings</span>
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
						
							<a href="#" style="color: #000000"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;PT Settings
							</h3></a>
							

							<div class="navbar-right" >
								<logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="PTADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">

											 <span id="saveid" class="buttons">Save</span>

										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
							
									
									
									
							</div>
						
					</div>
				
					
						<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class='col-md-12' id="txtstyle">
							<div class="col-md-6">
							
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Country</label>
									<div class="col-xs-7">
										<select class="form-control countries" name="country" id="countryId">
											<option value=''>-----SELECT-----</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
							
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">State</label>
									<div class="col-xs-7">
										<select class="form-control states"   name="state" id="stateId">
												<option value=''>-----SELECT-----</option>
										</select>
									</div>
								</div>
							</div>
							
						</div>
							<div class='row'>
								<div class='col-md-12'>
									<div class="form-group">
										<table style="background: #fff;" class="table allstudent" id="addPTSettings">
											<thead>
												<tr class='firstrow'>
												<th style="font-size: 13px; text-align: center;" colspan="2">Monthly Salary</th>
												<th style="font-size: 13px; text-align: center;">Professional Tax Levied (P.M) </th>
												
												<th align="center" style="text-align: center;"><span
													style="cursor: pointer;" class="glyphicon glyphicon-plus"
													onclick="addMorePTSeetingsRow();"></span></th>
												</tr>
											</thead>
											<tbody>
												
												<tr>
													<td><input type="text" class="form-control upto" value='UPTO' readonly="readonly"/></td>
													<td><input type="text" class="form-control maxMonthlysalary" value='0' /></td>
													<td><input type="text" class="form-control " value='NIL' /></td>
													<td align="center"></td>
												</tr>
						
										</tbody>
										</table>
									</div>
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
