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
<script type="text/javascript" src="JS/backOffice/Staff/SubjectwiseTeacherAdd.js"></script>


<style>
#feeedit:hover {
	cursor: pointer;
}

#iconsimg:hover{

cursor: pointer;
}

.buttons{
vertical-align: 6px;
}


#allstudent th:nth-child(1),#allstudent th:nth-child(2),#allstudent th:nth-child(3){
	
    text-align: left;
}

#allstudent tbody tr:nth-child(1),#allstudent tbody tr:nth-child(2),#allstudent tbody tr:nth-child(3){
    text-align: left;
}
#allstudent td:nth-child(1), #allstudents td:nth-child(1) {
     text-align: left; 
}


#allstudent tbody td:nth-child(2){
    width:100px;
}
#allstudent tbody td:nth-child(3){
    width:200px;
}

.specrow{
 width:185px;
}

.glyp-styles{
float: right;
height: 0px;
}
span.glyphicon.glyphicon-trash{
    float: inherit;
    padding-top: -17px;
    margin-top: -4px;
    padding-right: 0px;
}

.editinfo {
    top: 6px;
    left: -3px;
    float: right;
}

table#subjectTable {
    margin: auto;
    width: auto;
    margin-top: 15px;
    height:auto;
 }
 .select{
 text-align: center;
 }
 
/*  #subjectTable thead tr td:nth-child(3){
width:50px;
}
 
 #subjectTable tbody td:nth-child(3){
width:350px;
} */

 
</style>
</head>

<body>

	<div id="dialog"></div>
	<div id="dialog1"></div>
	<div id="dialog2">
			<table class="dialogInfo allstudent" id="subjectTable">
					<thead>
						<tr>
							<th>Select</th>
							<th>Subject Code</th>
							<th>Subject</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
	
	</div>
	
	
	
     <div class="errormessagediv1" style="display: none;">
				<div class="message-item1"></div></div>
	<div class="content" id="div1">
		<div class="input-group col-md-12" id="div2">

			<p class="transportheader">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Subject Wise Teacher Mapping</span>
			</p>
		</div>
		<%-- <div align="right" class="input-group col-md-4" style="margin: 20px 20px;visibility: hidden;" >
		

			<input type="text" class="form-control" Placeholder="Search......" id="searchterm" onkeypress="handle(event)" value="<logic:present name="searchTerm"><bean:write name="searchTerm"></bean:write></logic:present>">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div> --%>
		
					

		
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
									<a href="#" class="msg-success bg-msg-succes"><span class="validateTips"></span></a>
								</div>
						</div>
		
		
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Subject Wise Teacher Mapping
						
					</h3></a>
					
			   <div class="navbar-right" >
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="CLTMAPADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
								<span class="buttons " id="save">Save</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>			 
								 
								 
								 
					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="CLTMAPADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
								<span class="buttons " id="back">Back</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
			</div> 
			
		</div>
        




			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; margin-bottom: 18px;font-size: 13px; color: #000;">
												
							
					<div class="tab-row">
						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Branch</label>
										<div class="col-xs-7">
											<input type="text" readonly="readonly" class="form-control" name="location" id="locationName" 
											value='<logic:present name="locationName" scope="request"><bean:write name="locationName"></bean:write></logic:present>'/>
										</div>
									</div>
						
						<input type="hidden" name="hiddenlocation" id="hiddenlocationName" 
						 value='<logic:present name="locationName" scope="request"><bean:write name="locationName"></bean:write></logic:present>'/>
											
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"> Teacher Name</label>
								<div class="col-xs-7">
								<input type="text" readonly="readonly" class="form-control" name="t" id="teactherName" 
											value='<logic:present name="teacherName" scope="request"><bean:write name="teacherName"></bean:write></logic:present>'/>
								
								</div>
							</div>
					
						</div>
	
						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									id="inputnames" style="text-align: right;">Academic Year</label>
								<div class="col-xs-7">
										<input type="text" readonly="readonly" name="accname"  id="accname"
											class="form-control" value='<logic:present name="accname" scope="request"><bean:write name="accname" ></bean:write></logic:present>' />	 		
								</div>
							</div>
						</div>
					</div>
					
					
					
				<!-- table info -->
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th>Class</th>
									<th>Division</th>
									<th>Specialization</th>
									<th>Subjects </th>
								</tr>
							</thead>
							
							<tbody>
						 	<logic:present name="displayList" scope="request">
							<logic:iterate id="list" name="displayList"  indexId="index"> 
							
								<tr  class="<bean:write name="list" property="classId" />:<bean:write name="list" property="specId" />:<bean:write name="list" property="secId" />" id="<bean:write name="list" property="teacherMapId" />">
									<td><bean:write name="list" property="className" /></td>
									
									<td><bean:write name="list" property="secDetailName" /></td>
									
									<td><bean:write name="list" property="specializationName" /></td>
									
									<td class="appendValue <bean:write name="list" property="subjectId"/>"><span class="subinfo <bean:write name="list" property="subjectIdsUpdate" />"><bean:write name="list" property="subjectName"/></span>
									<span class="glyphicon glyphicon-edit editinfo" ></span></td>
								</tr>
								</logic:iterate>
							</logic:present>
							</tbody>
						</table>

				</div>
				
				<!-- dialog table  -->
			<!-- 	<div class="col-md-12"> -->
		
				</div>
				<!-- </div> -->
				
					
				<input type="hidden" name="accyearh" id="accyearh" readonly="readonly"
								value='<logic:present name="accId" scope="request"><bean:write name="accId"/></logic:present>' />
						
				<input type="hidden" name="locationh" id="locationh" 
				value='<logic:present name="locId" scope="request"><bean:write name="locId"/></logic:present>' />
				
					<input type="hidden" name="teacherh" id="teachrh" 
				value='<logic:present name="teacherId" scope="request"><bean:write name="teacherId"/></logic:present>' />

				</div>
		
			</div>
		</div>
	</div>
	<script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>