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
<script type="text/javascript" src="JS/backOffice/Admin/RoleMaster.js"></script>
<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">

  <script type="text/javascript" src="JS/backOffice/Admin/RoleMaster.js"></script>

  

<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */
</style>



</head>

<body>

	
	<div id="dialog"></div>
	 <div class="content" id="div1">
	 <div class="searchWrap">	
		<div class="" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Role details</span>
			</p>
		</div>
	</div>	
			
	<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" /></logic:present>'></input>		
		
		
		
		
	<div class="errormessagediv" align="center" style="display: none;">
							<div class="message-item" >
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
				
						<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" >
						<h3	class="panel-title" style="color: #000000;vertical-align: text-top;"><span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Role Details
					</h3></a>
				<div class="navbar-right">
				
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="ROLADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
					<a href="role.html?parameter=getRoleEntry"><span class="buttons">New</span></a>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>
					
					
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="ROLUPD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
					<span class="buttons" id="edit">Modify</span>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>
					
					
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="ROLDEL" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
					<span class="buttons" id="delete">Remove</span>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present>
					
					
							<%-- <logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="ROLDWD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
					<span  class="buttons" id="iconsimg" data-target="#myModal">Download </span>
					</logic:equal>
					</logic:equal>
					</logic:iterate>
					</logic:present> --%>
					
				</div>
				
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
							<div id="dialog-confirm"></div>
					<display:table name="requestScope.roleMasterList"  
							requestURI="/menuslist.html?method=roleList" pagesize="10" export="false"
							decorator="com.centris.campus.decorator.RoleMasterDecorator"
							class="table" id="allstudent">

						<display:column property="checkBox" sortable="true"
							title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" media="html">
							</display:column>

						<display:column property="roleName" sortable="true"
							title="Role Name <img src='images/sort1.png' style='float: right'/>" />

						<display:column property="roleDescription" sortable="true"
							title="Description <img src='images/sort1.png' style='float: right'/>" />


						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>

					</display:table>

				</div>
				 
			</div> 
		</div>
	</div>

	
</body>
</html>