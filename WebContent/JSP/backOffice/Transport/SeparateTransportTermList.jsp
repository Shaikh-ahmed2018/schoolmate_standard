<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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

<script type="text/javascript" src="JS/backOffice/Transport/SeparateTermDetails.js"></script>

<style>
#delete:hover {
	cursor: pointer;
}

#allstudent tbody tr td{position:relative}

.note{
color:#f00;
font-size: 13px;
margin-left: 10px;

}

</style>

</head>
<body class="feeconcession">


<div id="dialog"></div>

	<div class="content" id="div1">
	 <!--  <div class="searchWrap"> -->
		<div class="col-md-12 input-group" id="div2">
			<p class="transportheader">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Transport Terms List</span>
			</p>
		</div>
		
	<!-- </div> -->


<input type="hidden" id="termsearchid"
			value='<logic:present name="searchterm"><bean:write name="searchterm"  /></logic:present>'></input>

		
			<div class="successmessagediv" align="center" style="display:none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span class="validateTips"></span></a>
				</div>
			</div>
		

		<logic:present name="errormessagediv" scope="request">
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv1"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 35%; font-size: 13px; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>



		<div class="errormessagediv" style="display: none;">
			<div class="message-item">

				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000;vertical-align: text;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Transport Terms
						List
					</h3></a>
				<div class="navbar-right">
				
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="TTADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									 <span class="buttons" id="add" style="margin-right:12px;">New</span>  
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
				        
					
						
						<!-- <span id="termedit" class="buttons">Edit</span> -->
						<!--  <span id="delete" class="buttons">Delete</span>
						 <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span> -->

				</div>
				
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
			
	  <input type="hidden" id="historyacademicId"
      value="<logic:present name="historyacademicId" scope="request"><bean:write name="historyacademicId" /></logic:present>" />

      <input type="hidden" id="historylocId"
      value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId" /></logic:present>" />	
			
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<div class=row>
				<div class="col-md-6">
					<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Branch </label>
									<div class="col-xs-7">
									<select id="locationname" name="locationId" class="form-control">
										
										
										
							<logic:present name="locationList" scope="request">
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
					
					<%-- <logic:present name="termlist" scope="request"> --%>
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Sl.No</th>
							<th>Academic Year</th>
							<th>Branch</th>
							<th>Term Name</th>
							<th>Start Date</th>
							<th>End Date</th>
					
							</tr>
							</thead>
							<tbody>
							<%-- <logic:iterate id="termlist" name="termlist">
								<tr>
								<td><span name='select' class='academic_Checkbox_Class' id='<bean:write name='termlist' property="termid"/>,'></span><bean:write name='termlist' property="sno"/></td>
								<td><bean:write name='termlist' property="accyear"/></td>
								<td><bean:write name="termlist" property='locationName'/></td>
								<td><bean:write name="termlist" property='termname'/></td>
								<td><bean:write name="termlist" property='startdate'/></td>
								<td><bean:write name="termlist" property='enddate'/></td>
								</tr>
							</logic:iterate> --%>
							</tbody>
						</table>
					<%-- </logic:present> --%>
					
					
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks'></div>
				</div>
				
				<span class="note">Note :</span>
				<div class="note">1 - Modification not allowed in between records.</div>
				<div class="note">2 - Delete last record and enter new record.</div>
				<!-- <span class="note">2 - Delete last record and enter new record.</span>	 -->
				
			</div>
			<br />
		</div>
	</div>

</body>
</html>