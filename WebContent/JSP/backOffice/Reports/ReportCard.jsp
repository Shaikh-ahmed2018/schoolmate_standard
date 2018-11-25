<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>
	<script src="JS/backOffice/Reports/ReportCard.js"></script>
	<style>
		.modal-body {
			text-align: center;
		}
		.reportcard{
		    font-family: Roboto,sans-serif;
		    font-size: 11pt;
		    color: #000000;
		}
	</style>
</head>
<body>
	<div class="col-md-10">

		<p style="margin-bottom: 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Report Card</span>
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
		
		<p id="parent1" style="display: none;">
			<a href="">Expand all</a>
		</p>
		<div class="form-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">

					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #767676"><h4 class="panel-title reportcard" id="beforeparent"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Report Card
						</h4></a>
					

					<div class="navbar-right">
						<span class="buttons" id="download" style="top:6px;">Download</span>
					</div>
				</div>

			<!-- Filters -->
			<input type='hidden' id = 'hiddenaccyear' value = '<logic:present name = 'accYear' scope = 'request'><bean:write name = 'accYear'></bean:write></logic:present>'/>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="margin-top: 12px;">
				<div class="tab-row">
					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="location" name="location" class="form-control" required>
											
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
					
						
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;"> Class</label>
							<div class="col-xs-7">
							<select class="form-control" onkeypress="HideError()"  name="classname" id="class">
								<option value="all">----------Select----------</option>
							</select>
							</div>
						</div>
						
						<div class="form-group clearfix" id="termsid" style="display: none;">
							<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Scholastic  Areas</label>
							<div class="col-xs-7" id="checkboxlist" style="margin-top: 4px;">
								<span id="spanterms" style="display: none;"><input type="checkbox" name="term1" id="term1" style="margin-right: 5px;" ><span style="vertical-align: text-bottom;">Term I</span>
								<input type="checkbox" name="term2" id="term2" style="margin-right: 5px; margin-left: 9px;" ><span style="vertical-align: text-bottom;">Term II</span></span>
								<span id="spanaccyear" style="display: none;"><input type="checkbox" name="academicyear" id="reportaccyear" style="margin-right: 5px;" ><span style="vertical-align: text-bottom;">Academic Year</span></span>
								<input type="hidden" id="examstypeid"/>
								<input type="hidden" id="examstypeidterm2"/>
								<input type="hidden" id="examstypeidaccyear"/>
								<input type="hidden" id="examstypeprefix"/>
								<input type="hidden" id="examstypeprefixforterm2"/>
								<input type="hidden" id="reporttype"/>
								<input type="hidden" id="setup"/>
							</div>
						</div>
					</div>

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Academic Year</label>
							<div class="col-xs-7">
								<select id="accyear" name="accyear" class="form-control"
									required>
									
									<logic:present name="AccYearList">
										<logic:iterate id="AccYear" name="AccYearList">
											<option
												value="<bean:write name="AccYear" property="accyearId"/>">
												<bean:write name="AccYear" property="accyearname" />
											</option>
										</logic:iterate>
									</logic:present>
								</select>
							</div>
						</div>
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Division</label>
							<div class="col-xs-7">
								<select id="section" name="section" class="form-control"
									required>
									<option value="all">----------Select----------</option>
								</select>
							</div>
						</div>
						
						<!-- Scholastic  Areas: -->
						
					
						<div class="col-xs-4"></div>
					<div class="col-xs-8">
						<!-- <button type="button" class="btn btn-info " id="search" >Search</button> -->
						<span class="buttons" id="reset" >Reset</span>
					</div>
					</div>
					</div>
			
		
			
			
					<div id="report" style="display: none;">
					<table class="table" id="allstudent">
						<thead>
							<tr  style="background-color: #f5f5f5">
								<th><input type='checkbox' name='selectAll' class='selectAll'/></th>
								<th>Admission No</th>
								<th>Student Name</th>
								<th>Roll No</th>
								<th>Class</th>
								<th>Division</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
					</div><div class='pagination pagelinks' style='top:-9px'></div>
					</div>
					</div>
				</div>
			</div>
		</div>
		</div>
</body>

</html>
