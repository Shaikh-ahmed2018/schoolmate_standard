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

<script type="text/javascript" src="JS/backOffice/Student/NewStudentPromotionList.js"></script>
<style>
.form-group{
margin-bottom: 5px;}  
.save:hover {
	cursor: pointer;
}

#individualstudenttable th:nth-child(2),th:nth-child(3),th:nth-child(6){
  text-align: center;
  }
#individualstudenttable td:nth-child(2),td:nth-child(4){
  text-align: center;
  width: 20%;
  }
  
.allstudent th:nth-child(1), td:nth-child(1) {
     width: 5%;
}
.allstudent th:nth-child(2), td:nth-child(2),th:nth-child(3),td:nth-child(3),th:nth-child(7),td:nth-child(7) {
     text-align: center;
}
.allstudent th:nth-child(5), td:nth-child(5) {
     width: 7%;
}
#allstudent th:nth-child(5), td:nth-child(5) {
     width: 7%;
}
  
#studenttable th:nth-child(2),th:nth-child(4),th:nth-child(5){
width:20%;
  }
#studenttable th:nth-child(2),th:nth-child(4),th:nth-child(5){
  text-align: center;
  }

#studenttable td:nth-child(2),td:nth-child(4),td:nth-child(5),td:nth-child(6){
	text-align:center;
  }
  
 #allstudent th, .allstudent th{
    background: #f9f9f9 !important;
    border-bottom: 1px solid #ddd;
    border-top: 1px solid #ddd;
    border-right: 1px solid #ddd;
    
    font-family: Roboto,sans-serif;
    
    font-size: 14px;
    font-weight: lighter;
    border-left: 1px solid #ddd;
    color: #000000 !important;
    text-align: center;
  }
.allstudent tr td {
    border-right: 1px solid #ddd;
    border-left: 0;
    border-top: 0;
    border-bottom: 0;
    color: #000000;
    font-family: Roboto,sans-serif;
}
 
fieldset { 
	width:512px;
    display: block;
   /*  margin-left: auto;
    margin-right: 2px; */
    margin-bottom: 5px;
    padding-bottom: 0.625em;
    padding-left: 7px;
    padding-right: 0px;
    border: 0.5px solid #ccc;
   
}
legend {
    display: inline-block;
    width: auto;
    padding: 0;
    margin-bottom: 0px;
    font-size: 16px;
    line-height: inherit;
    color: #333;
    border: 0;
   }
   .tab-pane.active{
display: block !important;
}
</style>
<style>
.buttons{

vertical-align:-5px;

}
.navbar-right {
    top: -3px;
}
.panel-primary>.panel-heading{
margin-bottom: 0px;
}
form .panel.panel-primary.panel-list{
padding-bottom: 0px;

}    

@media (min-width:320px) and (max-width:767px){
br{
display: none;
}

.slideTab{
	padding:10px;
	font-family: Roboto Medium;
    font-size: 14px;
    font-weight: lighter;
}
}
</style>
</head>

<body>

<input type="hidden" id="hiddenlocId" value="<logic:present name="hloc"><bean:write name="hloc" /></logic:present>">




<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
<input type="hidden" id="backtab" value="<logic:present name='tabstatus' scope='request' ><bean:write name='tabstatus'/></logic:present>" /> 
	<div class="col-md-10" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Promotion</span>
			</p>
	
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Promotion</h3></a>
						

						<div class="navbar-right">
						     <logic:present name="UserDetails" scope="session">
								<logic:iterate id="daymap" name="UserDetails"
									property="permissionmap" scope="session">
									<logic:equal value="STUPROMADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
							               <span class="buttons" id="addnew">New</span>
							           </logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>
						</div>
					</div>
					
<input type="hidden" id="hiddentabstatus"
		 value="<logic:present name='tabstatus' scope='request' ><bean:write name='tabstatus'/></logic:present>">					

 <input type="hidden" id="promaccYear" 
  value="<logic:present name="promaccYear" scope="request"><bean:write name="promaccYear" /></logic:present>" />					
<input type="hidden" id="promlocId" 
  value="<logic:present name="promlocId" scope="request"><bean:write name="promlocId" /></logic:present>" />
  <input type="hidden" id="promclassname" 
  value="<logic:present name="promclassname" scope="request"><bean:write name="promclassname" /></logic:present>" />
  <input type="hidden" id="promsectionid" 
  value="<logic:present name="promsectionid" scope="request"><bean:write name="promsectionid" /></logic:present>" />
  <input type="hidden" id="prosearch" 
  value="<logic:present name="prosearch" scope="request"><bean:write name="prosearch" /></logic:present>" />

  
  <input type="hidden" id="demoaccYear" 
  value="<logic:present name="demoaccYear" scope="request"><bean:write name="demoaccYear" /></logic:present>" />					
 <input type="hidden" id="demolocId" 
  value="<logic:present name="demolocId" scope="request"><bean:write name="demolocId" /></logic:present>" />
  <input type="hidden" id="democlassname" 
  value="<logic:present name="democlassname" scope="request"><bean:write name="democlassname" /></logic:present>" />
  <input type="hidden" id="demosectionid" 
  value="<logic:present name="demosectionid" scope="request"><bean:write name="demosectionid" /></logic:present>" />
  <input type="hidden" id="demosearch" 
  value="<logic:present name="demosearch" scope="request"><bean:write name="demosearch" /></logic:present>" />


					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
					
						<div>
						<div class="slideTab clearfix">
						<div class="tab">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#promotedId"  id="promoted"  class="promoted">Promoted</a></li>
								<li><a data-toggle="tab"  href="#demotedDiv" id="demoted" class="demoted">Demoted</a></li>
							</ul>
						
						<div id="promotedId" class="tab-pane active" style="display: none;">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap" style="margin-top: 10px; margin-bottom: -5px;">
									<div class="col-md-8 clearfix" id=div2></div>
									<div class="col-md-6 clearfix" style="font-family: Roboto,sans-serif; font-size: 10pt; color: #000;">
								 
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
										<select class="form-control" 
											name="classname" id="classname">
											<option value="all"></option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search</label>
									<div class="col-xs-7">
										<input type="text" name="searchBy" tabindex="1" placeholder="Search......"
											id="searchBy"
											maxlength="25" class="form-control" 
											value='' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<div class="col-xs-5"></div>
									<div class="col-xs-7">
									<span type="button" class="buttons" id="search" >Search</span>
									<span type="button" class="buttons" id="Reset" >Reset</span>
									</div>
								</div>
								
								
								
								
							</div>
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 10pt; color: #000;">
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
			
			<input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">					
								
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
									
								<!-- 	<div id="studenttable"></div>
									<div id="individualstudenttable"></div>	 -->
									
			<div id="collapseOne" class="accordion-body collapse in">
								
				<div class="panel-body clearfix" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					
					<div class="col-md-12" style="margin-top: 10px;">
					
					<%-- <logic:present name="studentdetailslist" scope="request"> --%>
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Sl No.</th>
							<th>To Academic Year</th>
							<th>Adm No.</th>
							<th>Student</th>
							<th>From Class-Div</th>
							<th>To Class-Div</th>
							<th>From Specilization</th>
							<th>To Specilization</th>
							</tr>
							</thead>
							<tbody>
							<%-- <logic:iterate id="studentdetailslist" name="studentdetailslist">
								<tr class="<bean:write name='studentdetailslist' property="studentId"/> <bean:write name='studentdetailslist' property="locationId"/> <bean:write name='studentdetailslist' property="academicYearId"/> <bean:write name='studentdetailslist' property="promotionId"/> studentid">
								<td><bean:write name='studentdetailslist' property="count"/></td>
								<td><bean:write name="studentdetailslist" property='studentAdmissionNo'/></td>
								<td><bean:write name="studentdetailslist" property='studentFullName'/></td>
								<td><bean:write name="studentdetailslist" property='studentrollno'/></td>
								<td><bean:write name="studentdetailslist" property='classname'/></td>
								<td><bean:write name="studentdetailslist" property='sectionnaem'/></td>
								<td><bean:write name="studentdetailslist" property='specilizationname'/></td>
								
								</tr>
							</logic:iterate> --%>
							</tbody>
						</table>
					<%-- </logic:present> --%>
					
					</div>
					<div class="col-md-12">
					<div class="row">
				<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
					<span  class='numberOfItem'></span>	
					</div>
					<div class='pagination pagelinks'></div>
				</div>
				</div>
			</div>
			</div>		
					
								</div>
								</div>
		</div>
							
					<div id="demotedDiv" class="tab-pane" style="display: none;">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap" style="margin-top: 10px; margin-bottom: -5px;">
									<div class="col-md-8" id=div2></div>
									<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 10pt; color: #000;">
								 
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="locationname1" name="locationnid" class="form-control locationname" required>
											
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
										<select class="form-control" 
											name="classname" id="classid">
											<option value="all"></option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search</label>
									<div class="col-xs-7">
										<input type="text" name="searchBy" tabindex="1"
											id="searchBy1"  placeholder="Search......"
											maxlength="25" class="form-control" 
											value='' />
									</div>
								</div>
								
									
								<div class="form-group clearfix">
									<div class="col-xs-5"></div>
									<div class="col-xs-7">
										<span type="button" class="buttons" id="searchdemoted" >Search</span>
									    <span type="button" class="buttons" id="demotedReset" >Reset</span>
									</div>
								</div>
								
							
								
							</div>
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 10pt; color: #000;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="acyearid" name="accyear" class="form-control Acyearid" required>
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
										<select id="sectionid1" name="sectionid" class="form-control" required>
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
								
								
							</div>
							
						</div>
						<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000000;">
						 <div id="demotedTable"></div>
						   <div class='pagebanner'>
						   <select id='show_per_page1'>
							   <option value='50'>50</option>
							   <option value='100'>100</option>
							   <option value='200'>200</option>
							   <option value='300'>300</option>
							   <option value='400'>400</option>
							   <option value='500'>500</option>
						  </select>
						 <span  class='numberOfItem1'></span>
						</div>
						<div class='pagination pagelinks' style='top: -4px'></div>
						
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</div>
</div>
</body>					
</html>
