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

<script  type="text/javascript" src="JS/backOffice/Reports/defaulterList.js"></script>

<style>

.pagebanner {
    margin-top: 20px;
    }
#trash:hover {    
	cursor: pointer;
}

.download:hover{
cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
.filteration{
padding: 15px;
}
textarea{
width:100%;
}
.navbar-right {
    top: 0px;
}
#deleteDialog{
display: none;
}
</style>
<script>
function ShowTable(){
	$("#collapseOne").toggleClass("in");
}
</script>
</head>

<body>

	<div class="content" id="div1">
	<div id="dialog"></div>
	<div id="deleteDialog"></div>
	
		<div class="eventRegistration">
			<p style="margin-bottom: 0px;"><img src="images/addstu.png" style="vertical-align: top;"/>&nbsp;&nbsp;<span	id="pageHeading">Defaulter List</span></p>
			<input type="hidden" id="succmsg" value="<logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present>" />
	
			<div id="successmessages" class="successmessagediv" style="display: none;">
				<div class="message-item">
					 <a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"><logic:present name='successMessage' scope='request' ><bean:write name='successMessage'  /></logic:present></span></a>
				</div>
			</div>
	
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><logic:present name='errorMessage'  scope='request' ><bean:write name='errorMessage'  /></logic:present></span></a>
				</div>
			</div>

		
		<div class="panel panel-primary clearfix">
			<div class="panel-heading">
				<a 	href="javascript:ShowTable();" >
					<h3 class="panel-title" style="color: #000000; vertical-align: middle;"> <span class="glyphicon glyphicon-menu-hamburger"></span>
					&nbsp;&nbsp;Defaulter Fee List
					</h3></a>
					
			<div class="navbar-right">
			<!-- 	<span class="buttons" id="print">Print</span> -->
			               <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="DEFLISTDWD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
				                           <span  class="buttons"  id="download" data-toggle="modal" data-target="#myModal" >Download </span>
				                        </logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
				
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
		
			</div>
			
		
			<!-- pop up -->



<!-- Filteration Tabs  -->
<div id="filteration" class="filteration clearfix">
	<div class="panel-body" id="tabletxt">

		<div class="col-md-6" id="txtstyle">
									
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Branch</label>
					<div class="col-xs-7">
						<select id="locationName" name="locationName" class="form-control">
						
							 <logic:present name="locationList">
								<logic:iterate id="Location" name="locationList">
									<option  value='<bean:write name="Location" property="locationId"/>'>
									<bean:write name="Location" property="locationName" />
								</logic:iterate>
							</logic:present> 
						</select>
					</div>
			</div>
				
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Class </label>
					<div class="col-xs-7">
						<select id="className" name="className" class="form-control">
							<option value="">----------Select----------</option>
						</select>
					</div>
			</div>
			
				<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Terms </label>
					<div class="col-xs-7">
						<select id="termName" class="form-control" 	onkeypress="HideError()">
							<option value="">----------Select----------</option>
						</select>
					</div>
			</div>
			<div class="form-group clearfix">
				<div class="col-xs-5">
				</div>
				<div class="col-xs-7" style="margin-bottom: 5px;">
					<span class="buttons" id="searchs">Search</span>
			    	<span class="buttons reset">Reset</span>
				</div>
			</div>
		</div>
		
		
		<div class="col-md-6" id="txtstyle">
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Academic Year</label>
					<div class="col-xs-7">
						<select id="academicYear" name="accyear" class="form-control">
								<logic:present name="AccYearList">
												<logic:iterate id="accyear" name="AccYearList">
													<option value="<bean:write name="accyear" property="accyearId"/>"><bean:write name="accyear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present> 
						</select>
					</div>
					</div>
					
				<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Division</label>
					<div class="col-xs-7">
						<select id="divisionName" class="form-control">
						<option value="">----------Select----------</option>
								<%-- <logic:present name="AccYearList">
												<logic:iterate id="accyear" name="AccYearList">
													<option value="<bean:write name="accyear" property="accId"/>"><bean:write name="accyear" property="accName" /></option>
												</logic:iterate>
											</logic:present>  --%>
						</select>
					</div>
					</div>
		</div>
</div>
</div><!-- Filteration OVER  -->

 <div id="collapseOne" class="panel-collapse collapse in table-responsive">
					<table class='table' id='allstudent' style="width: 100%;display: none;" >
						<thead>
							<tr>
								<th>Sl no</th>
								<th>Admission Number</th>
								<th>Student Name</th>
								<th>Branch</th>
								<th>Class</th>
								<th>Division</th> 
								<th>Term</th>
								<th>Due Amount</th> 
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div id="page" style="display: none;">
		           <div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	

					</div>
					<div class='pagination pagelinks' style="margin-top:20px;"></div>
					</div>
</div>
</div>
</div>
</div>
</body>
</html>					
				
				
				
