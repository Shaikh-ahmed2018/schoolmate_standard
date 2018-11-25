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

<script type="text/javascript" src="JS/backOffice/Transport/TransportFeeCollectionList.js"></script>



<style>
#editDesignationId:hover {
	cursor: pointer;
}

.glyphicon-pencil:hover {
	cursor: pointer;
}

#edit:hover {
	cursor: pointer;
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
</style>
</head>

<body class="feeconcession">
  <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="content" id="div1">
		<p class="transportheader">
				<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;<span
					id="pageHeading">Fee Collection Master</span>
		</p>
	

		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #000000; vertical-align: text-top;"> 
							<h3 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Fee Collection Details</h3></a>
						
					</div>

     <input type="hidden" id="historylocId"
    value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>"/>
	
	<input type="hidden" id="historyacademicId"
    value="<logic:present name="historyacademicId" scope="request"><bean:write name="historyacademicId"/></logic:present>"/>
   
    <input type="hidden" id="historyclassname"
    value="<logic:present name="historyclassname" scope="request"><bean:write name="historyclassname"/></logic:present>"/>
   
    <input type="hidden" id="historysectionid"
    value="<logic:present name="historysectionid" scope="request"><bean:write name="historysectionid"/></logic:present>"/>
    
    <input type="hidden" id="historysearch"
    value="<logic:present name="historysearch" scope="request"><bean:write name="historysearch"/></logic:present>"/>


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
									<%-- <input type="hidden" name="hiddenlocationId" id="hiddenlocationId" value="<bean:write name="locationId"/>"> --%>
								</div>
									
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="all">ALL</option>
									</select>
									
									
									</div>
								</div>
									
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" 
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
								
							<div class="form-group clearfix">
								<div class="col-xs-5"></div>
								<div class="col-xs-7">
							    <span type="button" class="buttons" id="search" >Search</span>
								<span type="reset" class="buttons" id="resetbtn" >Reset</span></p>
								</div>
							</div>
								
							
								
							</div>
							
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 20px;">
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
									<%-- <input type="hidden" name="hiddenaccyear" id="hiddenaccyear" value="<bean:write name="accyear"/>"> --%>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
								</div>
								
								<div>

							</div>
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
						<%-- <input type="hidden" name="clasdetailids" id="classdetailid" value='<logic:present name="studentdetailslist" scope="request"><logic:iterate id="studentdetailslist" name="studentdetailslist"><bean:write name="studentdetailslist" property="classDetailId"/></logic:iterate></logic:present>'></input> --%>
						 


			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">	
					<%-- <display:table class="table" id="allstudent"
						name="requestScope.studentdetailslist"
						requestURI="/menuslist.html?method=studentSearch?">
						<display:column property="sno" sortable="true"	title="S.No"/>
						<display:column property="studentAdmissionNo" class="${allstudent.academicYearId} ${allstudent.locationId} studentid" sortable="true" title="Admission No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="studentFullName" class="${allstudent.studentId} stuid" sortable="true" title="Student Name <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="studentrollno" sortable="true" title="Roll No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="classname" class="classid ${allstudent.classDetailId}" sortable="true" title="Class <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="sectionnaem" class="sectionid ${allstudent.classSectionId}" sortable="true" title="Division <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						
					</display:table> --%>
					
					
					<%-- <logic:present name="studentdetailslist" scope="request"> --%>
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>S.No</th>
							<th>Admission No</th>
							<th>Student Name</th>
							<th>Class</th>
							<th>Division</th>
							</tr>
							</thead>
							<tbody>
							<%-- <logic:iterate id="studentdetailslist" name="studentdetailslist">
								<tr>
								<td><bean:write name='studentdetailslist' property="sno"/></td>
								<td class='<bean:write name="studentdetailslist" property='academicYearId'/> <bean:write name="studentdetailslist" property='locationId'/> studentid'><bean:write name='studentdetailslist' property="studentAdmissionNo"/></td>
								<td class='<bean:write name="studentdetailslist" property='studentId'/> stuid'><bean:write name="studentdetailslist" property='studentFullName'/></td>
								<td class='classid <bean:write name="studentdetailslist" property='classDetailId'/>'><bean:write name="studentdetailslist" property='classname'/></td>
								<td class='sectionid <bean:write name="studentdetailslist" property='classDetailId'/>'><bean:write name="studentdetailslist" property='sectionnaem'/></td>
								</tr>
							</logic:iterate> --%>
							</tbody>
						</table>
					<%-- </logic:present> --%>
			  <div>
		<div class="pagebanner"><select id="ShowPerPage">
		<option value="50">50</option>
		<option value="100">100</option>
		<option value="200">200</option>
		<option value="300">300</option>
		<option value="400">400</option>
		<option value="500">500</option>
		</select>
			<span  class='numberOfItem'></span>	
			 </div>
		<div class="pagination pagelinks"></div>
				
				</div>
				</div>
				<br />
			</div>
		</div>
	</div>
	</div>
	</div>
	
	</div>
	


</body>
</html>