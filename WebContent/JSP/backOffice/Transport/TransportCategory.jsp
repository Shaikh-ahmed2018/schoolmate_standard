<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>eCampus Pro</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script type="text/javascript" src="JS/backOffice/Transport/TransportDetails.js"></script>

<style>
.glyphicon:hover {
	cursor: pointer;
}

.ui-dialog {
	position: fixed;
	top: 130px !important;
}
</style>

</head>

<body>
<!-- <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div> -->
	<div id="dialog"></div>

	<div class="content" id="div1">
		<div class="searchWraps">
			<div class="input-group col-md-12" id="div2">
				<p class="transportheader">
					<span class="glyphicon glyphicon-user"></span>&nbsp;<span
						id="pageHeading">Transport Category</span>
				</p>
			</div>

				<%-- <input type="hidden" name="searchterm" class="searchtermclass"
					id="searchexamid"
					value='<logic:present name="searchnamelist"><bean:write name="searchnamelist"/></logic:present>'>
				</input> --%>
			 
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
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;">
					<h3 class="panel-title"
						style="color: #000000; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Transport
						Category
					</h3>
				</a>

				<div class="navbar-right" style="margin-right:-2px;">
					<span class="buttons" id="add">New</span> 
					<span class="buttons" id="edit">Modify</span>
					<span class="buttons" id="inactive">InActive</span>

				</div>
			</div>
			
		<input type="hidden" id="currentstatus" 
		value='<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>'></input>	
			
	    <input type ="hidden" name ="transporttypeid" id="transporttypeid" value=''></input>				
	    <input type="hidden" id="hiddenvechiletype" value=""></input>

			<div id="addTransportcategory" style="display: none;">
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; font-size: 14px; font-family: Roboto, sans-serif; line-height: 35px;font-weight: lighter;">Transport Type<font
						color="red"> *</font></label>
					<div class="col-xs-7">
						<input type="text" name="vehicleType" onclick="HideError1(this)" class="form-control" id="vehicleType" maxlength="29"
									    value='<logic:present name="transportTypedetails"><bean:write name="transportTypedetails" property="vehicleType" /></logic:present>'></input>
					</div>
				</div>

				<div class="form-group clearfix des">
					<label for="inputPassword" class="control-label col-xs-4"
						style="text-align: right; font-size: 14px; font-family: Roboto, sans-serif; line-height: 35px;font-weight: lighter;">Description</label>
					<div class="col-xs-7">
						<textarea style="resize:none" rows="4" cols="25" maxlength="249" class="form-control" name="desc" id="descriptionid" ><logic:present name="transportTypedetails"><bean:write name="transportTypedetails" property="desc"/></logic:present></textarea>
					</div>
				</div>

			</div>

			<div id="collapseOne" class="accordion-body collapse in">
			  <div class="panel-body">
				 <div class="col-md-6" id="txtstyle">
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
						onkeypress="handle(event)" class="form-control"
						Placeholder="Search......" style="height: 35px;">
						</div>
					<!-- <span class="input-group-btn">
						<button class="btn btn-default" type="button" id="search"
							onclick="myFunction()" value="Submitform">
							<i class="fa fa-search"></i>
						</button>
					</span> -->
				</div>
				<div class="form-group clearfix"> 
				<div class='col-xs-5'>
				</div>
					<div class='col-xs-7'  style="text-align: left;">
					<span class="buttons" id="search" style="font-weight: normal;">Search</span>  
					<span class="buttons" id="resetbtn" style="font-weight: normal;">Reset</span>
					</div></div>
				     </div>
				  </div>
				<div class="panel-body" style="font-family: Roboto, sans-serif; font-size: 13px; color: #000;">
					<%-- <logic:present name="vehicleTypeList" scope="request"> --%>
						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th><input type='checkbox' name='selectall' style='text-align: center' id='selectall' /></th>
									<th>Transport Type</th>
									<th>Description</th>
                                    <th>Remarks</th>
								</tr>
							</thead>
							<tbody>
								<%-- <logic:iterate id="vehicleTypeList" name="vehicleTypeList">
									<tr>
										<td><input type='checkbox' name='select' class='select'
											style='text-align: center'
											id='<bean:write name="vehicleTypeList" property='vehicle_id'/>' /></td>
										<td><bean:write name="vehicleTypeList"
												property='vehicleType' /></td>
										<td><bean:write name="vehicleTypeList" property='desc' /></td>

									</tr>
								</logic:iterate> --%>
							</tbody>
						</table>
					<%-- </logic:present> --%>


					<div class='pagebanner' style="position: relative !important;">
						<select id='show_per_page'><option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option></select> <span class='numberOfItem'></span>
					</div>
					<div class='pagination pagelinks' style="margin-bottom: 10px"></div>



				</div>


			</div>
		</div>
	</div>

</body>
</html>