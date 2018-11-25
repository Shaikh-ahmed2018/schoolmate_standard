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

<script type="text/javascript" src="JS/backOffice/Admin/ClassFeeSetup.js"></script>
</head>





<body>
<div id="dialog"></div>

	<div class="content" id="div1">
	<div class="searchWrap">
		

			<p>
				<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span
					id="pageHeading">Class Fee Setup</span>
			</p>


		
		
	</div>
		<logic:present name="success" scope="request">

			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
								name="success" scope="request" />
					</span></a>
				</div>
			</div>
		</logic:present>
		<logic:present name="error" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="error" scope="request" />
					</span></a>
				</div>
			</div>
		</logic:present>



		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000; line-height: 18px;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Class
						Fee Setup

					</h3></a>
			
				
			</div>
			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h3 class="modal-title" id="myModalLabel">Download</h3>
						</div>
						<div class="modal-body">
							<span id="excelDownload"><img src="images/xl.png"
								class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>

			<input type="hidden" id="classfeesetupsearchid"
				value='<logic:present name="classfeesetupSerchTerm"><bean:write name="classfeesetupSerchTerm"  /></logic:present>'></input>


   <input type="hidden" id="historylocId" 
	 value='<logic:present name="historylocId" scope="request" ><bean:write name="historylocId" /></logic:present>'/>

   <input type="hidden" id="historyacademicId" 
	 value='<logic:present name="historyacademicId" scope="request" ><bean:write name="historyacademicId" /></logic:present>'/>

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<div class=row>
				<div class="col-md-6">
					<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Branch<span style="color: red;">*</span></label>
									<div class="col-xs-7">
									<select id="locationname" name="locationId" class="form-control">
										<logic:present name="locationList">
										<logic:iterate id="Location" name="locationList">
											<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
										</logic:iterate>
										</logic:present>
						
									</select>
									</div>
								<input type="hidden" name="schoolId" class="form-control" id="schoolId" value='<logic:present name="list"><bean:write name="list" property="locationId" /></logic:present>'></input>
							</div>
							
				</div>
				<div class="col-md-6">
					<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
										
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
				</div>
				
			</div>

					<%-- <display:table name="requestScope.classSetupList"
						requestURI="/menuslist.html?method=getClassFeeSetup" 
						export="false" class="table" id="allstudent" decorator="com.centris.campus.decorator.ClassFeeSetupDecorator">

						<display:column property="sno" title="Sl.No." class="${allstudent.accyearid},${allstudent.classid},${allstudent.locationId}"></display:column>

						<display:column property="accyear" sortable="true" title="Academic Year <img src='images/sort1.png' style='float: right'/>" />
						<display:column property="locationName" sortable="true" title="School Name <img src='images/sort1.png' style='float: right'/>" />
						<display:column property="classname" sortable="true" title="Class Name <img src='images/sort1.png' style='float: right'/>" />
				
						<display:column property="status" sortable="true" title="Status <img src='images/sort1.png' style='float: right'/>" />
						
						

					</display:table> --%>
					
					<logic:present name="classSetupList" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Sl.No.</th>
							<th>Academic Year </th>
							<th>Branch</th>
							<th>Class Name </th>
							<th>Status</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="classSetupList" name="classSetupList">
								<tr>
								<td class='<bean:write name="classSetupList" property='accyearid'/>,<bean:write name="classSetupList" property='classid'/>,<bean:write name="classSetupList" property='locationId'/>'><bean:write name='classSetupList' property="sno"/></td>
								<td><bean:write name="classSetupList" property='accyear'/></td>
								<td><bean:write name="classSetupList" property='locationName'/></td>
								<td><bean:write name="classSetupList" property='classname'/></td>
								<td><span class='class_name <bean:write name="classSetupList" property='status'/>' style='min-width:80px;display:inline-block;text-align:center; color:#fff;'><bean:write name="classSetupList" property='status'/></span></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
					
                   <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
	               <span  class='numberOfItem'></span>	
	             </div><div class='pagination pagelinks'></div>


				</div>
				<br />
			</div>
		</div>
	</div>
	
</body>
</html>
