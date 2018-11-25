<!DOCTYPE html>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html lang="en"> 

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<script type="text/javascript" src="JS/backOffice/Settings/ClassTeacherMapping.js"></script>
<script type="text/javascript">
	function handle(e) {
		var searchTerm = $("#searchterm").val();
		if (e.keyCode === 13) {
			e.preventDefault(); // Ensure it is only this code that rusn

			window.location.href="menuslist.html?method=getclassandteacherList&searchTerm="+searchTerm;
		}
	}
</script>
<style>
#feeedit:hover {
	cursor: pointer;
}
#editdep:hover {
	cursor: pointer;
}
#deleteid:hover {
	cursor: pointer;
}
#xls:hover {
	cursor: pointer;
}
#iconsimg:hover{

cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
</style><style>
.buttons{

vertical-align: 6px;

}
</style>
</head>

<body>
     <div class="errormessagediv1" style="display: none;">
				<div class="message-item1"></div></div>
	<div class="content" id="div1">
		<div class="col-md-12 input-group" id="div2">

			<p class="transportheader">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Class Teacher Mapping</span>
			</p>
		</div>
	<%-- <!-- 	<form id="myForm" action="menuslist.html?method=searchQuota" method="post" > -->
		
		

		<div class="input-group col-md-4" style="margin: 20px 0px; visibility:hidden;">
			 <input type="text" class="form-control" name="searchname" id="search" Placeholder="" 
			  value='<logic:present name="searchTerm"><bean:write name="searchTerm" />
		</logic:present>'>
			
			<span class="input-group-btn">
				<<!-- button class="btn btn-default" type="button" id="search" onclick="myFunction()" value="Submit form">
					<i class="fa fa-search"></i>
				</button> -->
			</span> 
		</div>
		<!-- </form> --> --%>
		
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
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
		
		
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Class Teacher Mapping
						
					</h3></a>
					
			   <div class="navbar-right" >
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="CLTMAPADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
								<span class="buttons " id="editId">Modify</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
                          
                          
                          <logic:present name="UserDetails" scope="session">
						 <logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="CLTMAPDWN" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
								<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>
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
							<span id="xls"><img src="images/xl.png"
								class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>
				

			<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>
			
			
		 <input	type="hidden" name="classid" id="classid" value=""/>		
		 <input	type="hidden" name="sectionid" id="sectionid" value=""/>		
		  <input	type="hidden" name="teacherid" id="teacherid" value=""/>	
			
			<div id="dialog1" style="display: none;">
			                  <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select id="dlocation" name="dlocation" class="form-control" required disabled="disabled" style="cursor: default;">
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
		                        <div class="form-group clearfix">
								<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								id="inputnames" style="text-align: right;">Academic Year</label>
							<div class="col-xs-7">
								<select id="accyearp" name="accyearp" class="form-control" disabled="disabled" style="cursor: default;"
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
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Class Name
								</label>		
									<div class="col-xs-7">
									<input type="text" name="classname" id="classname"
										class="form-control" placeholder="" maxlength="30"></input>
								</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division Name</label>
									<div class="col-xs-7">
									<input type="text" name="sectionname" id="sectionname"
										class="form-control" placeholder="" maxlength="30"></input>
								</div>
								</div>
							
							
								<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Teacher Name
								</label>
								<div class="col-xs-7">
									<%-- <logic:present name="teacherlist" scope="request"> --%>
								         <select class="form-control" name="teachername" id="teachernameid" onkeypress="HideError()">
								           <option value="">----------Select----------</option>
									      <%--  <logic:iterate id="stream" name="teacherlist" scope="request">
											<option value='<bean:write name='stream' property='teacherId'/>'>
											<bean:write name='stream' property='tfastname'/></option>
										    </logic:iterate> --%>
							                </select>
											<%-- </logic:present> --%>
								</div>
		</div>
		<input type="hidden" name="hclassid" class="classididclass" id="hclassid" ></input>					
		<input type="hidden" name="hsectionid" class="sectionididclass" id="hsectionid"></input>
		<input type="hidden" name="hteachername" class="hteachernameclass" id="hteachername"></input>				
		<input type="hidden" name="hteacherid" class="classididclass" id="hteacherid" ></input>
		<input type="hidden" name="haccyear" class="haccyear" id="haccyear" value="<logic:present name = 'haccyear' scope = 'request'><bean:write name = 'haccyear'></bean:write></logic:present>"></input>
		</div>
			
</div>

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; margin-bottom: 18px;font-size: 13px; color: #000;">
												
				<%-- 	
					<logic:present name="classteacherlist" scope="request">
						<display:table class="meeting table" 
							name="requestScope.classteacherlist"
							requestURI="menuslist.html?method=getclassandteacherList"
							id="allstudent">
							
							
							<display:column property="check" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />



	            <display:column title="Select" headerClass="heading1">
							<input type="checkbox" name="getempid" onClick='getvaldetails(this)' value="Get Salary Details"
							id="${allstudent.classId},${allstudent.sectionId},${allstudent.teacherId}"
							> </>
							</display:column>	




							<display:column property="className" sortable="true"
								title="Class Name <img src='images/sort1.png' style='float: right'/>"></display:column>
								
							<display:column property="sectionName" sortable="true"
								title="Section Name<img src='images/sort1.png' style='float: right'/>"></display:column>
								
							<display:column property="teacherName" sortable="true"
								title="ClassTeacher Name <img src='images/sort1.png' style='float: right'/>"></display:column>

								
							
						</display:table> --%>
						<div class="tab-row">
					<div class="col-md-6" id="txtstyle">
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
									<option value="all">----------Select----------</option>
							</select>
							</div>
						</div>
						<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Department</label>
									<div class="col-xs-7">
										<select id="departmentid" name="department" class="form-control" required>
											<option value="all">ALL</option>
											<logic:present name="deptlist">
												<logic:iterate id="deptname" name="deptlist">
													<option value="<bean:write name="deptname" property="depId"/>"><bean:write name="deptname" property="depName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
						<!-- <div class="form-group clearfix" id="termsid">
							<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Search</label>
							<div class="col-xs-7" id="checkboxlist" style="margin-top: 6px;">
								<input type="text" name="searchName" id="searchName"
										class="form-control" placeholder="" maxlength="30"></input>
							</div>
						</div> -->
					</div>

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Academic Year</label>
							<div class="col-xs-7">
								<select id="accyear" name="accyear" class="form-control"
									required>
									<!-- <option value="all">ALL</option> -->
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
						
						<!-- Scholastic  Areas: -->
						
					
						<div class="col-xs-4"></div>
					<!-- <div class="col-xs-8">
						<button type="button" class="btn btn-info " id="search" >Search</button>
						<button type="reset" class="btn btn-info " id="reset" >Reset</button>
					</div> -->
					</div>
					</div>
						<logic:present name="classteacherlist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Select</th>
							<th>Class Name</th>
							<th>Division Name</th>
							<th>Class Teacher Name</th>
							
							</tr>
							</thead>
							<tbody>
							<%-- <logic:iterate id="classteacherlist" name="classteacherlist">
								<tr>
								<td><input type="checkbox" name="getempid"  value="Get Salary Details"  class='<bean:write name="classteacherlist" property='classId'/> <bean:write name="classteacherlist" property='sectionId'/> <bean:write name="classteacherlist" property='teacherId'/> <bean:write name="classteacherlist" property='locationId'/>' /> </td>
								<td class='clsName'><bean:write name="classteacherlist" property='className'/></td>
								<td class='secName'><bean:write name="classteacherlist" property='sectionName'/></td>
								<td><bean:write name="classteacherlist" property='teacherName'/></td>
								</tr>
							</logic:iterate> --%>
							</tbody>
						</table>
					</logic:present>
                           <div class='clearfix banners'><div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem' style='padding-left: 3px;'></span></div><div class='pagination pagelinks'></div></div>
					<%-- </logic:present> --%>
						
						
					
		
		<!-- 
					<p style="float: left; margin: 0;">&nbsp;&nbsp;Showing 1 to 10
						of 50 Entries</p> -->
					<!-- <ul class="pagination" style="float: right; margin: 0;">
						<li><a href="#">&laquo;</a></li>
						<li class="active"><a href="#">1</a></li>
						<li class=""><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul> -->
				</div>
		
			</div>
		</div>
	</div>
<!-- 	<script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script> -->
</body>
</html>