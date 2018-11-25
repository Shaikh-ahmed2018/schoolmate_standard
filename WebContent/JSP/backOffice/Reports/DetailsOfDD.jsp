<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.menu.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
 --><link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<!-- <link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css"> -->
<script type="text/javascript" src="JS/common.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Reports/ddreport.js"></script>
<script type="text/javascript">

function handle(e){ 
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn

        searchList();
    }
}

</script>

<style>
#individualstudenttable th:nth-child(1),th:nth-child(2),th:nth-child(3),th:nth-child(4),th:nth-child(5){
  text-align: center;
  }
#individualstudenttable td:nth-child(1),td:nth-child(2),td:nth-child(3),td:nth-child(4),td:nth-child(5){
  text-align: center;
  }
  #allstudent tbody tr{
position: relative;
}
</style>

</head>


<body>
<div id="dialog" style="display: none;">		
		<div id="dialog1">
			<div class="col-md-12" id="txtstyle"
				style="font-size: 13px; color: #000;">
				
				<table style="width: 100%;" id="dependency">
							<tr>
								<td ><label for="inputEmail"style="text-align: center; line-height: 35px; width: 88px">Select Exam</label></td>
								<td ><select id="Exam" style="width: 150px;"></select></td>
							</tr>

						</table>
				
			</div>
		</div>
	</div>
<input type="hidden" id="hstudentId"/>
<input type="hidden" id="haccyear"/>
<input type="hidden" id="hlocationId"/>
<input type="hidden" id="hclassId"/>
<input type="hidden" id="hSectionId"/>
<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><img src="images/addstu.png" /><span id="pageHeading">Details Of DD</span></p>
				<div class="panel-body clearfix"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					
				</div>

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
				
									<!-- pop up start -->

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
                      <!-- pop up end -->
				
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;DD Reports</h3></a>
						
					   <div class="navbar-right">
					    
					        <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="DTAOFDDDWD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
							               <span class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" style="top:0px;">Download</span>
							            </logic:equal>
									</logic:equal>
								</logic:iterate>
							 </logic:present>
							
							<!-- <span class="buttons" id="print" style="top:4px;">Print</span> -->
						</div>
						
					</div>
					
					

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationname" class="form-control" required>
											<option value="all">---------select---------</option>
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
										style="text-align: right; line-height: 35px;">Term</label>
									<div class="col-xs-7">
										<select id="Termid" name="Termid" class="form-control" required>
											<option value="all">---------select---------</option>
										</select>
									</div>
								</div>
								</div>
							
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
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
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
							
							<div id="individualstudenttable"></div>
							<div id="page">
						 <div class='pagebanner'>
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	
				          	</div>
					<div class='pagination pagelinks'></div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
</html>
