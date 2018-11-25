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
<script type="text/javascript" src="JS/backOffice/Admin/TeacherTimeTable.js"></script>


<script type="text/javascript">
$(document).ready(function(){
	
	$("#allstudent tbody tr").click(function(){
		window.location.href="teachermenuaction.html?method=viewTeacherTimeTable&userId="+$(this).closest('tr').attr('id');
		});
});

</script>

<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */
</style>

<style>
.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

#dialog p {
	font-size: 13px;
}

.ui-dialog{
    position:fixed;
    top:130px !important;          
}
</style>

</head>

<body>

	<div class="content" id="div1">
		<div id="dialog"></div>
			<div class="col-md-12  input-group" id="div2">
				<p class="transportheader">
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Teacher List</span>
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
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Teacher List
						
					</h3></a>



				<div class="navbar-right" style="right:3px;">

					


					<!-- <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span> -->

				</div>
			</div>
			<!-- pop up -->

			

		

			

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-bottom: 10px;">
					
			 <div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">
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
							<select class="form-control" onkeypress="HideError()" 
									name="classname" id="class">
									<option value="all">ALL</option>
							</select>
							</div>
						</div>
 </div>
                   <div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">
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
						<input type="hidden" id="haccyear" value="<logic:present name = 'accyearid' scope = 'request'><bean:write name = 'accyearid'></bean:write></logic:present>"/>
						<input type="hidden" id="hlocid" value="<logic:present name = 'curent_loc' scope = 'request'><bean:write name = 'curent_loc'></bean:write></logic:present>"/>
						
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Division</label>
							<div class="col-xs-7">
								<select id="section" name="section" class="form-control"
									required>
									<option value="all">ALL</option>
								</select>
							</div>
						</div>
					
                    
                    </div>
                    
					
					 <logic:present name="timeTableDetailsList" scope="request">
						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th style="width: 20%;text-align: left;">Staff Id</th>
									<th style="width: 33%;">Abbreviate Name</th>
									<th>Staff Name</th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name='timeTableDetailsList' id="timeTableDetailsList">
									<tr id='<bean:write name='timeTableDetailsList' property="teacherId" />'>
									    <td style="text-align: left;"><bean:write name='timeTableDetailsList' property="subscriberId" /></td>
										<td><bean:write name='timeTableDetailsList' property="abbrvation" /></td>
										<td><bean:write name='timeTableDetailsList' property="teacherName" /></td>
										
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					<div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select> <span class='numberOfItem'></span>
					</div>
					<div class='pagination pagelinks'></div>
					</logic:present>

				</div>
			</div>
		</div>
	</div>

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>