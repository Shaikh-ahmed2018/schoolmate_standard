<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>
 <script type="text/javascript" src="JS/backOffice/Settings/AddDesignation.js"></script>

<title>eCampus Pro</title>


</head>

<body>

	<div class="content" id="div1">

		<div id="dialog"></div>


		<div class="col-md-12 input-group" id="div2">
			
		</div>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">

				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="errormessagediv1" align="center" style="display: none;">
			<div class="message-item1"></div>
		</div>

			<div class="panel-heading clearfix">
				<h3 class="panel-title" style="color: #000000;">Designation Details</h3>
				
				<div class="navbar-right">
				<!-- add btn -->
				<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="DESCRE" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">	
											<span id="savebutton" class="btn btn-xs btn-primary margin-t-5" data-toggle="modal" data-target="#myModal">
											<span class="glyphicon glyphicon-plus" ></span>Add Designation</span>				
					 					 </logic:equal>
									  </logic:equal>
								  </logic:iterate>
							  </logic:present>
					<!-- : ends-->
					
					<!-- edit & delete-->
					<input type="hidden" id="editPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="DESUPD" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
						<input type="hidden" id="delPermission" value="<logic:present name="UserDetails" scope="session"><logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session"><logic:equal value="DESDEL" name="daymap" property="key"><logic:equal value="true" name="daymap" property="value">true</logic:equal></logic:equal></logic:iterate></logic:present>">
					<!--  :ends-->	
				</div>
				

				<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>

			</div>



			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="myModalLabel">Designation</h3>
						</div>
						<div class="modal-body">
						<div class="panel-body clearfix">
						<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Designation</label>
								<div class="col-xs-7">
									<input type="text" name="designationname" class="form-control"
										id="designation" onkeypress="HideError(this)"
										value='<logic:present name="DesignationLIst"><bean:write name="DesignationLIst" property="designation_name"/></logic:present>'></input>
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Description</label>
								<div class="col-xs-7">
									<textarea style="resize: none" rows="4" cols="25" class="form-control" name="description" class="form-control"id="description"> <logic:present name="DesignationLIst"><bean:write name="DesignationLIst" property="designation_description" /></logic:present> </textarea>
								</div>
							</div>
							
						
						</div>
						</div>
							<div class="modal-footer">
						<span id="save" class="buttons button-blue">Save</span>
          				<span class="buttons button-simple" data-dismiss="modal">Close</span>
       				 </div>

					</div>
				</div>
			</div>
		
		<input type="hidden" id="currentstatus" name="currentstatus" 
      value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />
			
        <input type="hidden" id="hiddenstatus1"
	    value='<logic:present name="status" scope="request"><bean:write name="status" /></logic:present>'></input>

        <input type="hidden" id="hiddensearchname1"
	    value='<logic:present name="searchname" scope="request"><bean:write name="searchname" /></logic:present>'></input>			

			<div id="collapseOne" class="accordion-body collapse in">
			
			<div class="row">
					<div class="col-md-6"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top: 1%;">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Status</label>
							<div class="col-xs-7">
								<select id="status" name="status" class="form-control">
									<option value="Y">Active</option>
									<option value="N">InActive</option>
								</select>
							</div>
						</div>
						<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Search</label>
							<div class="col-xs-7">
								<input type="text" name="searchname" id="searchname"
								class="form-control" Placeholder="Search......">
							</div>
							<!-- <span class="input-group-btn">
								<button class="btn btn-default" type="button" id="search"
									onclick="myFunction()" value="Submitform">
									<i class="fa fa-search"></i>
								</button>
							</span> -->
						</div>
						
						<div class="form-group clearfix"> 
						<div class="col-xs-5">
							</div>
						<div class="col-xs-7" style="text-align: left;">
								<span class="buttons" id="search" style="font-weight: normal;">Search</span>  
								<span class="buttons" id="resetbtn" style="font-weight: normal;">Reset</span>
						</div></div>
					</div>
				</div>
			
			
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

					<logic:present name="DesignationDetails" scope="request">


						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th><input type='checkbox' name='selectall' id='selectall'
										style='text-align: center' onClick='selectAll()' /></th>
									<th>Designation Name</th>
									<th>Description</th>
									<th>Remarks</th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name='DesignationDetails' id="DesignationDetails">
									<tr>
										<td><input type='checkbox' name='select' class='select' style='text-align: center' id='<bean:write name='DesignationDetails' property="desgid"/>' /></td>
										<td><bean:write name='DesignationDetails' property="desgname" /></td>
										<td><bean:write name='DesignationDetails' property="desgdes" /></td> 
										<td><bean:write name='DesignationDetails' property="remarks" /></td>
									</tr>
								</logic:iterate>
							</tbody>

						</table>

						<div class='pagebanner'>
							<select id='show_per_page'><option value='50'>50</option>
								<option value='100'>100</option>
								<option value='200'>200</option>
								<option value='300'>300</option>
								<option value='400'>400</option>
								<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
						<div class='pagination pagelinks'></div>

					</logic:present>



				</div>
				<br />
			</div>

	<!-- <script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script> -->
</body>
</html>