<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>

<script src="JS/backOffice/Admin/UserRolePermission.js"></script>

<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">


<style>
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}

</style><style>
.table-striped tbody td,.table-striped thead th{
width:130px !important;
}
.table-striped tbody td:nth-child(1),.table-striped thead th:nth-child(1){
width:20px;
}
.table-striped tbody td:nth-child(2),.table-striped thead th:nth-child(2){
width:300px !important;
}
.buttons{

vertical-align: -32px;

}
.modal{
width:820px;
max-height:500px; 
left:0;
right:0;
margin:auto;

}

.modal-header{

/* width:618px; */
    padding: 8px 15px;
   
    text-align: center;
    color: #fff;
    border-radius: 5px 5px 0px 0


}
.table-striped table {
            width: 100%;
        }

  .table-striped thead,.table-striped tbody,.table-striped tr,.table-striped td,.table-striped th { 
  display: block;
   }

   .table-striped  tr:after {
            content: ' ';
            display: block;
            visibility: hidden;
            clear: both;
        }

       .table-striped thead th {
            height: 30px;
		float: left;
            /*text-align: left;*/
        }

       .table-striped tbody {
            height: 240px;
            overflow-y: auto;
        }
       .table-striped  thead {
            /* fallback */
        }


     .table-striped  tbody td,.table-striped thead th {
           
           /* display: inline-block;
           vertical-align: top; */
           float:left !important;
           
        }

</style>
</head>

<body>

 <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>

<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;"> 
		<p>
			<span class="glyphicon glyphicon-user"></span>&nbsp;<span id="pageHeading">Assign Permissions</span>
		</p>
		
		
				<div class="successmessagediv" align="left" style="display: none;">
						<div class="message-item">
							<!-- Warning -->
							<a href="#" class="msg-success bg-msg-succes"><span
								class="validateTips"></span></a>
						</div>
					</div>	
					

					
				<div class="errormessagediv" align="center" style="display: none;">
					<div class="message-item">
					<!-- Warning -->
				    <a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
					</div>
					</div>	
					
				
				

					<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
					

			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								  style="color: #000000"><h3 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Role Permissions
							</h3></a>
						
							<div class="navbar-right" >
								
							
							<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="RPMADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
									
							<span id="PreviewPermission" class="buttons">Save</span>
							</logic:equal>
							</logic:equal>
							</logic:iterate>
							</logic:present>
								
								
					
							</div>
						
					</div>
					
				
	<!-- Preview Start -->
	
	
	
		<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
		
		<div class = "modal-content" >
  			<div class = "modal-header primarythemebackgound primarythemecolor">
  				
  				
  		<button type="button" id="closepre" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title primarythemebackgound primarythemecolor" id="myModalLabel">Preview</h4>
  				
  				
  			</div>
  			
  	<table  class="table" id="allstudent" style="font-family: Open Sans Light;color: #897676;" >
	<thead>
	<tr>
	 <th >Module</th>
	 <th style="min-width : 150px; text-align : center" >Sub Module</th>
	 <th style="text-align: center;width:130px !important;">PageAccess</th>  
	 <th   style="text-align: center;width:130px !important;">New</th>
	 <th  style="text-align: center;width:130px !important;">Modify</th> 
	 <th style="text-align: center;width:130px !important;">Remove</th>
	  <th  style="text-align: center;width:130px !important;">Download</th>
	  
	
	</tr>
	</thead>
		<tbody>
	
	<logic:present name="RolePermission" scope="request">
	<logic:iterate id="creation" name="RolePermission" property="permissionList">
	<logic:notEmpty name="creation" property="submodule">
	<tr>
	
	
	 <td><bean:write name="creation" property="module"/></td>
	 <td >&nbsp;<input type="checkbox" id='${creation.submodule}All' />&nbsp;&nbsp;<bean:write name="creation" property="submodule"/></td>
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation3" name="RolePermission" property="permissionList">
		<logic:match name="operation3" value="${creation.submodule} Display"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation3" property="permissionId"/>' name='<bean:write name="operation3" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	<td style="text-align: center;">
	<logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	<logic:iterate id="operation" name="RolePermission" property="permissionList">
		<logic:match name="operation" value="${creation.submodule} Creation"  property="permissionName" >
		<input type="checkbox" class="permission permission${operation.submodule}" id='<bean:write name="operation" property="permissionId"/>' name='<bean:write name="operation" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation1" name="RolePermission" property="permissionList">
		<logic:match name="operation1" value="${creation.submodule} Update"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation1" property="permissionId"/>' name='<bean:write name="operation1" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation2" name="RolePermission" property="permissionList">
		<logic:match name="operation2" value="${creation.submodule} Delete"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation2" property="permissionId"/>' name='<bean:write name="operation2" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation4" name="RolePermission" property="permissionList">
		<logic:match name="operation4" value="${creation.submodule} Download"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation4" property="permissionId"/>' name='<bean:write name="operation4" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match></td>
	</tr>
	</logic:notEmpty>
	</logic:iterate>
	</logic:present>
	
	</tbody>
	</table>
	<div class="modal-footer">
          <div class="errormessagediv1" style="display: none;float: left;">
						<div class="message-item1">
							<!-- Warning -->
							<a href="#" class="msg-warning1 bg-msg-warning1"><span style="100%" class="validateTips"></span></a>
						</div>
					</div>
        <span id="savePermission" class="buttons" data-dismiss="modal">Save</span>
		<span id="closePreview" class="buttons" data-dismiss="modal">Close</span>
      </div>
	
	
	</div>
	
</div>
	<!-- Preview End -->			
		<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="filters">

					<div class="col-md-12" style="padding:0;margin-bottom: 10px;margin-top: 5px;" id="txtstyle">
								<div class="col-md-6">
									<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Select Role<font color="red"></font></label>
									<div class="col-xs-7" id="selecti">
									<select name="roleName" id="roleId" onchange="HideError(this)" class="form-control">
									<option value="">----------Select----------</option>
									<logic:present name="RolePermission" scope="request">
									<logic:iterate id="role" name="RolePermission" property="roleList" scope="request">
									<option value='<bean:write name="role" property="roleCode"/>,<bean:write name="role" property="roleName"/>'><bean:write name="role" property="roleName"/></option>
									</logic:iterate>
									</logic:present>
									</select>	
										</div> 
									</div><br/>
								</div>
							
								<div class="col-md-6">
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4" id="inputnames"><input type="checkbox" id='selectAll' name="selectall" >&nbsp;&nbsp;&nbsp;&nbsp;Select All<font color="red"></font></label>
									</div><br/>
								</div>
				  </div>
	<div class="col-md-12" style="padding:0; ">				
	<table  class="table table-striped" id="allstudents" style="font-family: Open Sans Light;color: #897676;height:300px;" >
		<thead>
		<tr>
	 		<th>Module</th>
			 <th >Sub Module</th>
			 <th style="text-align: center;">PageAccess</th>  
			 <th style="text-align: center;">New</th>
	 		 <th style="text-align: center;">Modify</th> 
	 	 	 <th style="text-align: center;">Remove</th>
	 		 <th style="text-align: center;">Download</th>
		</tr>
		</thead>
		<tbody>
	
	<logic:present name="RolePermission" scope="request">
	<logic:iterate id="creation" name="RolePermission" property="permissionList">
	<logic:notEmpty name="creation" property="submodule">
	<tr>
	 <td><bean:write name="creation" property="module"/></td>
	 <td >&nbsp;<input type="checkbox" id='${creation.submodule}All' class='submoduleCheck' />&nbsp;&nbsp;<bean:write name="creation" property="submodule"/></td>
	 <td style="text-align: center;">
	 <logic:match name="creation" value="${creation.submodule}" property="permissionName">
	 <logic:iterate id="operation3" name="RolePermission" property="permissionList">
	 <logic:match name="operation3" value="${creation.submodule} Display"  property="permissionName" >
	 <input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation3" property="permissionId"/>' name='<bean:write name="operation3" property="permissionShortName"/>'/>
	 </logic:match>
	 </logic:iterate>
	 </logic:match>
	 </td>
	
	<td style="text-align: center;">
	<logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	<logic:iterate id="operation" name="RolePermission" property="permissionList">
		<logic:match name="operation" value="${creation.submodule} Creation"  property="permissionName" >
		<input type="checkbox" class="permission permission${operation.submodule}" id='<bean:write name="operation" property="permissionId"/>' name='<bean:write name="operation" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation1" name="RolePermission" property="permissionList">
		<logic:match name="operation1" value="${creation.submodule} Update"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation1" property="permissionId"/>' name='<bean:write name="operation1" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation2" name="RolePermission" property="permissionList">
		<logic:match name="operation2" value="${creation.submodule} Delete"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation2" property="permissionId"/>' name='<bean:write name="operation2" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match>
	</td>
	
	
	<td style="text-align: center;"><logic:match name="creation" value="${creation.submodule}"  property="permissionName">
	  <logic:iterate id="operation4" name="RolePermission" property="permissionList">
		<logic:match name="operation4" value="${creation.submodule} Download"  property="permissionName" >
		<input type="checkbox" class="permission permission${creation.submodule}" id='<bean:write name="operation4" property="permissionId"/>' name='<bean:write name="operation4" property="permissionShortName"/>'/>
	</logic:match>
	</logic:iterate>
	</logic:match></td>
	
	</tr>
	</logic:notEmpty>
	</logic:iterate>
	</logic:present>
	
</tbody>
	
	</table>
	</div>				
					
					
					</div>
					</div>
				
					</div>
					
	</div>
					</div>

</body>
</html>