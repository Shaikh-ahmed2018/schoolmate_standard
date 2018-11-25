N<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
<script type="text/javascript" src="JS/backOffice/SMS/FeeSMSList.js"></script>

<style>
#div1 {
	margin-top: -22px;
}
</style>

</head>

<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="content" id="div1">
		<div class="" id="div2">
			<p style="">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Fee SMS Details</span>
			</p>
		</div>
		
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


		<div class="panel panel-primary clearfix">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Fee SMS List
						
					</h3></a>



				   <div class="navbar-right">
                                <logic:present name="UserDetails" scope="session">
									<logic:iterate id="daymap" name="UserDetails"
										property="permissionmap" scope="session">
										<logic:equal value="SHDADD" name="daymap" property="key">
											<logic:equal value="true" name="daymap" property="value">
			                                 <span id="add" class="buttons" data-placement="bottom">New</span> 
			                              </logic:equal>
										</logic:equal>
									</logic:iterate>
								 </logic:present>
	

				</div>
				<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>
			</div>
	

		
<input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">
<input type="hidden" id="hiddenlocId" value="<logic:present name="current_loc"><bean:write name="current_loc"/></logic:present>">

<input type="hidden" id="hiddenstartdate" value="<logic:present name="startDate"><bean:write name="startDate"/></logic:present>">
<input type="hidden" id="hiddenenddate" value="<logic:present name="enddate"><bean:write name="enddate"/></logic:present>">


   

			<div id="collapseOne" class="accordion-body collapse in clearfix">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
            
            <div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
									<select id="locationname" name="locationnid"
										class="form-control" required>
										
										<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option
													value="<bean:write name="Location" property="locationId"/>"><bean:write
														name="Location" property="locationName" /></option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"> Class</label>
								<div class="col-xs-7">

									<select class="form-control" onkeypress="HideError()"
										name="classname" id="classname">
										<option value="">ALL</option>
									</select>

								</div>
							</div>
                      
                                 <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Start Date</label>
									<div class="col-xs-7">
										<input type="text" name="year" id="startdate" maxlength="25"  class="form-control"  readonly="readonly" placeholder="Click Here" value="<logic:present name='StartDate'><bean:write name='StartDate'/></logic:present>" />
									</div>
								</div>
						</div>

						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-7">
									<select id="Acyearid" name="accyear" class="form-control"
										required>
										<logic:present name="AccYearList">
											<logic:iterate id="AccYear" name="AccYearList">
												<option
													value="<bean:write name="AccYear" property="accyearId"/>"><bean:write
														name="AccYear" property="accyearname" /></option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
							
							

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Division</label>
								<div class="col-xs-7">
									<select id="sectionid" name="sectionid" class="form-control"
										required>
										<option value="all">----------Select----------</option>
									</select>
								</div>
							</div>
                            
                            
                               <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">End Date</label>
									<div class="col-xs-7">
										<input type="text" name="year" id="enddate" maxlength="25" class="form-control"  readonly="readonly" placeholder="Click Here" value="<logic:present name='EndDate'><bean:write name='EndDate'/></logic:present>" />
									</div>
								</div>
						</div>
					  
					<logic:present name="FeeSMSList" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>

							<th style="min-width:90px;">Sl.No</th>
							<th style="min-width:100px;">SMS Sent Date</th>
							<th style="min-width:114px;">Fee Date</th>
							<th style="min-width:95px;">Class - Division</th>
							<th style="min-width:95px;">Student Name</th>
							<th style="min-width:120px;">Status</th>

							</tr>
							</thead>
							<tbody>
							<logic:iterate id="FeeSMSList" name="FeeSMSList">
								<tr>
								<td><bean:write name="FeeSMSList" property='slno'/></td>
								<td><bean:write name="FeeSMSList" property='createdtime'/></td>
								<td><bean:write name="FeeSMSList" property='date'/></td>					
								<td><bean:write name="FeeSMSList" property='clsId'/>-<bean:write name="FeeSMSList" property='divId'/></td>
								<td><bean:write name="FeeSMSList" property='studentname'/></td>
								<td><bean:write name="FeeSMSList" property='status'/></td>
								
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
                  
				</div>
				<div class="pagebanner">
				     <select id="show_per_page">
				       <option value="50">50</option>
				       <option value="100">100</option>
				       <option value="200">200</option>
				       <option value="300">300</option>
				       <option value="400">400</option>
				       <option value="500">500</option>
				      </select>
					<span class="numberOfItem"></span>	
				</div>
				<div class="pagination pagelinks"></div>
				 
			</div>
		</div>
	</div>

</body>
</html>