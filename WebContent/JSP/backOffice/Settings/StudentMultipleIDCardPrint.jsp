<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>
   <script type="text/javascript" src="JS/backOffice/Settings/StudentSearchPrint.js"></script>

</head>

<body>
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><span class="glyphicon glyphicon-user"></span>&nbsp;<span id="pageHeading">Print Student ID Card - Multiple</span></p>
				<div class="errormessagediv" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning" style="text-align: center;"><span class="validateTips"></span></a>
							</div>
						</div>

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Students Search	</h3></a>
					
						
						<div class="navbar-right">
						<span class="buttons" id="print">Print</span>
						</div>
					</div>

<input type="hidden" id="historyacademicId"
	 value='<logic:present name="historyacademicId" scope="request" ><bean:write name="historyacademicId" /></logic:present>' />
   
    <input type="hidden" id="historylocId"
	 value='<logic:present name="historylocId" scope="request" ><bean:write name="historylocId" /></logic:present>' />
	 
	<input type="hidden" id="historystreamId"
	 value='<logic:present name="historystreamId" scope="request" ><bean:write name="historystreamId" /></logic:present>' />
	 
	<input type="hidden" id="historyclassname"
	 value='<logic:present name="historyclassname" scope="request" ><bean:write name="historyclassname" /></logic:present>' />

 <input type="hidden" id="historysectionid"
	 value='<logic:present name="historysectionid" scope="request" ><bean:write name="historysectionid" /></logic:present>' />

				
					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
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
									style="text-align: right; line-height: 35px;"> Stream</label>
								<div class="col-xs-7">

									<select class="form-control" onkeypress="HideError()"
										name="streamId" id="streamId">
										<option value="">----------Select----------</option>
									</select>

								</div>
							</div>

							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="">----------Select----------</option>
										</select>
									</div>
								</div>
								<br/>
								
							</div>
							
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
											<option value="">----------Select----------</option>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
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
											<option value="">----------Select----------</option>
										</select>
								
									</div>
								</div>
								
								
								
								
							</div>
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
							
				<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">	
					<table class="table" id="allstudent">
					<thead>
					 <tr>
						<th><input type='checkbox' class='selectall'  /></th>
						<th>Admission No</th>
						<th>Student Name</th>
						<th>Roll No</th>
						<th>Class</th>
						<th>Division</th>
						<th>Photo</th>
					 </tr>
					</thead>
					<tbody>
					</tbody>
					</table>
	<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
		<span  class='numberOfItem'></span>	
		</div><div class='pagination pagelinks'></div>				
					
					</div>
					</div>
					
						</div>
					</div>
				</div>
			</div>
			
	</div>
	</body>
</html>
