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

<script src="JS/newUI/jquery.js"></script>
<!-- <script src="JS/newUI/bootstrap.min.js"></script>
 <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
 -->
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Teacher/AssignmentList.js"></script>

<title>eCampus Pro</title>

<script type="text/javascript">

function handle(e){
	var searchText = $("#searchname").val();
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn

        window.location.href ="teachermenuaction.html?method=assignmentView&searchTerm="
			+searchText;
    }
}


</script>

<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
h3.panel-title {
    display: inline !important;
    vertical-align: text-top;
    color: #000000;
    cursor: pointer;
}
h3.panel-title:active {
background: transparent;}
</style>
</head>

<body>

	<div class="content" id="div1">
	<div id="dialog"></div>
	<div class="searchWrap">
		<div class="col-md-8" id="div2">

			<p >
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Assignment Details</span>
			</p>
		</div>

		<div class="input-group col-md-4">

			<input type="text" name="searchname" id="searchname"  onkeypress="handle(event)" 
					class="form-control" Placeholder="Search......" style = "height:35px;"
					value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'> 	
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" id="search" onclick="myFunction()" value="Submitform">
						<i class="fa fa-search"></i>
					</button>
				</span>
		</div>
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

		<div class="panel panel-primary">
			<div class="panel-heading">
				
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-menu-hamburger"></i>
						&nbsp;&nbsp;Assignment Details</h3>
			
						
				<div class="navbar-right">
					 <a href="teachermenuaction.html?method=addassignment" id="plus">
					 	<span class="buttons">New</span>
					 </a>
					  
					  <span class="buttons" style="cursor: pointer;" id="edit">Modify</span>
					
					  
					  	 <span id="trash" class="buttons" style="cursor: pointer;">Remove</span>
				
					
					<!-- <a href=""><img src="images/download.png" class="download"></a> -->
				</div>
			</div>  

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					
					<%-- <display:table class="table" id="allstudent"
					
						name="requestScope.AssignmentList"
						requestURI="/teachermenuaction.html?method=assignmentView"
						decorator="com.centris.campus.decorator.AssignmentDecorator">


						<display:column property="check" style="text-align:center"
							title="<input type='checkbox' class='select' name='selectAll' style='text-align:center' id='selectAll'/>"></display:column>

						<display:column property="classname" title="Class"
							media="html"></display:column>

						<display:column property="section" title="Section "
							media="html"></display:column>

						<display:column property="assigName" title="Assignment Name"
							media="html"></display:column>

						<display:column property="startdate" title="Assignment Date"
							media="html"></display:column>

						<display:column property="enddate" title="Completion Date"
							media="html"></display:column>

						<display:column property="subjectname" title="Subject Name"
							media="html"></display:column>
							
						<display:column property="marks" title="Max Marks"
							media="html"></display:column>	
								
					</display:table --%>
					
					<logic:present name="AssignmentList" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' class='select' name='selectAll' style='text-align:center' id='selectAll'/></th>
							<th>Class</th>
							<th>Section</th>
							<th>Assignment Name</th>
							<th>Assignment Date</th>
							<th>Completion Date</th>
							<th>Subject Name</th>
							<th>Max Marks</th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="AssignmentList" name="AssignmentList">
								<tr>
								<td><input type='checkbox' name='select' class='select'style='text-align: center' id='<bean:write name="AssignmentList" property='assignmentId'/>' /></td>
								<td><bean:write name="AssignmentList" property='classname'/></td>
								<td><bean:write name="AssignmentList" property='section'/></td>
								<td><bean:write name="AssignmentList" property='assigName'/></td>
								<td><bean:write name="AssignmentList" property='startdate'/></td>
								<td><bean:write name="AssignmentList" property='enddate'/></td>
								<td><bean:write name="AssignmentList" property='subjectname'/></td>
								<td><bean:write name="AssignmentList" property='marks'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
					
					

					
				</div>
				<br />
				<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks' style='top:-9px'></div>
			</div>
				
				</div>
				<br />
			</div>
		</div>
	</div>


</body>
</html>