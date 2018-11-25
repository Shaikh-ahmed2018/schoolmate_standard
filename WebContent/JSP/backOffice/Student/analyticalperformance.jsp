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


<script type="text/javascript" src="JS/backOffice/Student/analyticalperformance.js"></script>
<link href='CSS/Admin/CommonTable.css' rel='stylesheet'/>
<script type="text/javascript">
function handle(e){ 
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn
        searchList();
    }
}

</script>

<style>
 .allstudent td:nth-child(8), th:nth-child(8),td:nth-child(3), th:nth-child(3),td:nth-child(4), th:nth-child(4),td:nth-child(5), th:nth-child(5),td:nth-child(6), th:nth-child(6),td:nth-child(7), th:nth-child(7) {
	text-align: center;
}
.appiconposition,.classInvoliconposition,.learncapiconposition,.homeworkiconposition,.readiconposition,.writeiconposition,.englishfluencyiconposition,.activitiesiconposition,.bagbooksiconposition,.behaviouriconposition{
    left: 4px;
    top: 2px;
    font-size: 15px;
}
 .class_name
 {
  min-width:80px;
  display:inline-block;
  text-align:center;
   color:#fff; 
 }
 .clearfix{
 	margin-top: 5px;
 }
 .allstudent{
 	width: 100%;
 }
 
 .ui-dialog{
    position:fixed;
}
 
 .row{
    margin-left: 0;
    margin-right: 0;
}
</style>
</head>

<body>
<input type="hidden" id="hiddenlocId" value="<logic:present name="hloc"><bean:write name="hloc" /></logic:present>">

  <div id="loader" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
     
   <div id="Dialog" style="display:none;">
   
      <div class="errormessagediv" style="display: none;" align="center">
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
   
	    <div class='row' style="overflow: hidden;">
	    	<div class='col-md-12'>
			  <div class='col-md-6'>
				<div class='form-group clearfix'>
					<label for='labelforAdmissionNo' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;margin-top:-4px;'> Master/ Ms:<span></label>
					<div class="col-xs-7">
						<span id="student"></span>
					</div>
				</div>
			</div>
			<input type="hidden" id="hiddenstatus" value=""/>
			<div class='col-md-6'>
				<div class='form-group clearfix'>
					<label for='labelforAdmissionNo' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;margin-top:-4px;'> Admission No:<span></label>
					<div class="col-xs-7">
						<span id="admissionNo"></span>
					</div>
				</div>
			</div>
			
			<div class='col-md-6'>
				<div class='form-group clearfix'>
					<label for='labelforclass' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;margin-top:-4px;'>Class-Div:</label>
					<div class="col-xs-7">
						<span id="classdiv"></span>
					</div>
				</div>
			</div>
			
			   <table class="allstudent">
							<thead>
							<tr>
							<th>S.No</th>
							<th>Remarks</th>
							<th>Excellent</th>
							<th>Very Good</th>
							<th>Good</th>
							<th>Average</th>
							<th>Needs Care</th>
							<th class="selectheader">Parent Opinion</th>
							</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
			<div class='col-md-12'>
			</div>
		</div>
	</div>
	</div>
	

	<div class="col-md-10" style="font-family: Roboto,sans-serif; font-size: 20pt; color: #000; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
	
		<div style="overflow: hidden;">
		<div class="col-md-8" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Analytical Performance</span>
			</p>
		</div>
		</div>
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" style='font-size: 13px; color: #000;padding-top:0;'>
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
							<a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne" style="color: #000; vertical-align: text-top;"> 
							<h4 class="panel-title" style="vertical-align: super;"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Student Details</h4></a>
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class='row clearfix'>
								<div class='col-md-12'>
									<div class="col-md-6">
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
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="">----------Select----------</option>
										</select>
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" onkeypress="handle(event)"
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
								<div class="form-group clearfix">
										<div class="col-xs-7" style="float: right;">
										<span class="buttons" id="search">Search</span>
										<span class="buttons" id="resetbtn">Reset</span>
										</div>
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
						
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="all">----------Select----------</option>
										</select>
									</div>
								</div>
									</div>
								</div>
							</div>
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
							
				<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">	
					
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>S.No</th>
							<th>Admission No</th>
							<th>Student Name</th>
							<th>Roll No</th>
							<th>Class and Division</th>
							<th>House Name</th>
							<th>Status</th>
							</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
				
				</div>
        <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
	    <span  class='numberOfItem'></span>	
	    </div><div class='pagination pagelinks' style='top:-9px'></div>
					</div>
						</div>
					</div>
				</div>
			</div>
	</div>
</html>
