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
<title>eCampus Pro</title>
<script type="text/javascript" src="JS/common.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet"> -->
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/backOffice/SMS/OtherSMSList.js"></script>

<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */
</style><style>
.buttons{

vertical-align: -28px;

}
</style>

<style>
.download:hover{

cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
.panel-title{
font-size: 15px;
	color: #000000;
    font-family: Roboto,sans-serif;
    margin-left: 10px;
    left: 10px;

}
.panel-primary{
margin-top: -30px;
}
</style>

</head>

<body>

	<div class="content" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Other SMS Details</span>
			</p>
		</div>


		<form id="myForm"
			action="menuslist.html?method=streamList" method="post">
				<br /><br />
			
		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" /></logic:present>'></input>
		</form>
		

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


		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Other SMS Details
						
					</h3></a>



				<div class="navbar-right">

                         <logic:present name="UserDetails" scope="session">
									<logic:iterate id="daymap" name="UserDetails"
										property="permissionmap" scope="session">
										<logic:equal value="OTHSMSADD" name="daymap" property="key">
											<logic:equal value="true" name="daymap" property="value">
			                                 	<span id="add" class="buttons" data-placement="bottom">Add</span>  
			                              </logic:equal>
										</logic:equal>
									</logic:iterate>
								 </logic:present>
				
					<!-- 	
						<!-- <span class="glyphicon glyphicon-pencil" id="editstream"
						data-toggle="tooltip" data-placement="bottom" title="Edit"></span> 


					<span class="glyphicon glyphicon-trash" id="delete"
						data-toggle="tooltip" data-placement="bottom" title="Delete"></span>


					<img src="images/download.png" class="download" id="iconsimg"
						data-toggle="modal" data-target="#myModal" data-toggle="tooltip"
						data-placement="bottom" title="Download"> -->

				</div>
				<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>
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
							<h4 class="modal-title" id="myModalLabel">Download</h4>
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
<input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">

<input type="hidden" id="hiddenstartdate" value="<logic:present name="startDate"><bean:write name="startDate"/></logic:present>">
<input type="hidden" id="hiddenenddate" value="<logic:present name="enddate"><bean:write name="enddate"/></logic:present>">
			<div id="collapseOne" class="accordion-body collapse in clearfix">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
					<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">School
									Name</label>
								<div class="col-xs-7">
									<select id="locationname" name="locationnid"
										class="form-control" required>
										<option value="all">----------Select----------</option>
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
										<option value="">----------Select----------</option>
									</select>

								</div>
							</div>
                           
                           
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Start Date</label>
									<div class="col-xs-7">
										<input type="text" name="year" id="startdate" maxlength="25"  class="form-control"  readonly="readonly" value="<logic:present name='StartDate'><bean:write name='StartDate'/></logic:present>" />
									</div>
								</div>
                          

						</div>

						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top:10px;">
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
										<input type="text" name="year" id="enddate" maxlength="25" class="form-control"  readonly="readonly" value="<logic:present name='EndDate'><bean:write name='EndDate'/></logic:present>" />
									</div>
								</div>

							
						</div>
					
					
					

					<logic:present name="othersmslist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<!-- <th><input type='checkbox' name='selectall' id='selectall'/></th> -->
							<th>Sl.No</th>
							<th>Date</th>
							<th>Student Name</th>
							<th>Class & Section</th>
							<th>Reason</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="othersmslist" name="othersmslist">
								<tr>
								<!-- <td><input type='checkbox' name='selectall' class='select' id='select' onClick='selectAll()' /></td> -->
								<td><bean:write name="othersmslist" property='slNo'/></td>
								<td><bean:write name="othersmslist" property='date'/></td>
								<td><bean:write name="othersmslist" property='student'/></td>
								<td><bean:write name="othersmslist" property='classname'/></td>
								<td><bean:write name="othersmslist" property='smstext'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>

				</div>
				<div class="pagebanner"><select id="show_per_page"><option value="50">50</option><option value="100">100</option><option value="200">200</option><option value="300">300</option><option value="400">400</option><option value="500">500</option></select>
					<span class="numberOfItem"></span>	
				</div>
				<div class="pagination pagelinks"></div>
				<br />
				<br />
			</div>
		</div>
	</div>


</body>
</html>