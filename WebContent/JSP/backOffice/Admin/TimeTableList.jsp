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
<script type="text/javascript" src="JS/backOffice/Admin/TimeTable.js"></script>



<title>eCampus Pro</title>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

.glyphicon-pencil:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}

.glyphicon:hover {
	cursor: pointer;
}

.download:hover {
	cursor: pointer;
}
/* #xls:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
} */
#excelDownload :hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

#iconsimg:hover {
	cursor: pointer;
}

.scrollBar {
	max-height: 440px;
	overflow-y: scroll;
}
</style>
</head>

<body>

	<div class="content" id="div1">
			<div class="input-group col-md-12" id="div2">
				<p class="transportheader">
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Time Table Management</span>
				</p>
			</div>

			<input type="hidden" id="hviewBy"
				value="<logic:present name="ViewBy"><bean:write name="ViewBy"/></logic:present>" />

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
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Time
						Table Management
					</h3> </a>

				<div class="navbar-right">
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="TMTUPD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span class="buttons" id="edit">Modify</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="TMTDWD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span class="buttons" id="iconsimg" data-toggle="modal"
										data-target="#myModal">Download </span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>

				</div>

			</div>
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
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

					
   <input type="hidden" id="historylocId1" 
	value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>" />
	
	<input type="hidden" id="historyacademicId1" 
	value="<logic:present name="historyacademicId" scope="request"><bean:write name="historyacademicId"/></logic:present>" />				
	
	<input type="hidden" id="historyclass1" 
	value="<logic:present name="historyclass" scope="request"><bean:write name="historyclass"/></logic:present>" />
	
	<input type="hidden" id="historysection1" 
	value="<logic:present name="historysection" scope="request"><bean:write name="historysection"/></logic:present>" />				
					

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
                    



					<logic:present name='ClassTimeTableList'>
						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th>Select</th>
									<th>Class-Division</th>
									<th>Teacher Name</th>
									<th>Status</th>
									<th>Created By</th>
									<th>Create Date</th>
									<th>Updated By</th>
									<th>Updated Date</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</logic:present>
					<div class='pagebanner'>
						<select id='show_per_page'>
							<option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option>
						</select> <span class='numberOfItem'></span>
					</div>
					<div class='pagination pagelinks mypage' style="top: -4px;"></div>


					<logic:present name="TeacherTimeTableList">
						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th><input type='checkbox' name='selectall' id='selectall'
										onClick='selectAll()' /></th>
									<th>Teacher Id</th>
									<th>Teacher Name</th>
									<th>Status</th>
									<th>Created By</th>
									<th>Create Date</th>
									<th>Updated By</th>
									<th>Updated Date</th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name='TeacherTimeTableList'
									id="TeacherTimeTableList">
									<tr>
										<td><input type='radio' name='selectBox'
											class='selectBox' style='text-align: center'
											value='<bean:write name='TeacherTimeTableList' property="timetableId"/>,<bean:write name='TeacherTimeTableList' property="classid"/>' /></td>
										<td><bean:write name='TeacherTimeTableList'
												property="regno" /></td>
										<td><bean:write name='TeacherTimeTableList'
												property="teachername" /></td>
										<td><bean:write name='TeacherTimeTableList'
												property="timetableStatus" /></td>
										<td><bean:write name='TeacherTimeTableList'
												property="createdby" /></td>
										<td><bean:write name='TeacherTimeTableList'
												property="createddate" /></td>
										<td><bean:write name='TeacherTimeTableList'
												property="lastupdatedby" /></td>
										<td><bean:write name='TeacherTimeTableList'
												property="lastupdated" /></td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>

						<!-- <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select></div><div class='pagination pagelinks'></div> -->
					</logic:present>


				</div>

			</div>
		</div>
	</div>


</body>
</html>