<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <script type="text/javascript" src="JS/backOffice/Bank/BankBranch.js"></script>

</head>

<body>

	<div class="content" id="div1">
		<div id="dialog"></div>
		<div class="searchWrap">
			<div class="col-md-12 input-group" id="div2">
				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Bank Branch Master List</span>
				</p>
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
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;">
					<h3 class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Bank Branch Master List
					</h3></a>



				<div class="navbar-right">

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SLCADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<a href="bankBranchMaster.html?method=addBranch">
									 <span id="Add" class="buttons">New</span>
									</a>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SLCUPD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span class="buttons" id="Edit">Modify</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SLCDEL" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span class="buttons" id="Remove">InActive</span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>


				</div>

			</div>
	
	<input type="hidden" id="currentstatus" name="currentstatus" 
    value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />
			
    <input type="hidden" id="historysearchname" 
	value='<logic:present name="searchname1" scope="request"><bean:write name="searchname1" /></logic:present>'></input>
	
	<input type="hidden" id="historystatus" 
	value='<logic:present name="status1" scope="request"><bean:write name="status1" /></logic:present>'></input>
		

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
								<input type="text" name="searchname" id="searchtext"
									onkeypress="handle(event)" class="form-control"
									Placeholder="Search......" style="height: 35px;">
							</div>
					<!-- <span class="input-group-btn">
						<button class="btn btn-default" type="button" id="search">
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

					<logic:present name="bankbranchlist" scope="request">

						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th><input type='checkbox' name='selectall' id='selectall' style='text-align: center' onClick='selectAll()' /></th>
									<th>Bank Name</th>
									<th>Branch Name</th>
									<th>Branch Short Name</th>
									<th>IFSC Code</th>
									<th>Address</th>
									<th>Remarks</th>

								</tr>
							</thead>
							<tbody>
								<logic:iterate name='bankbranchlist' id="bankbranchlist">
								<tr>
									<td><input type='checkbox' name='select' class='select' style='text-align: center'
												value='<bean:write name='bankbranchlist' property="id"/>' /></td>
												<td><bean:write name='bankbranchlist' property="bankname" /></td>
												<td><bean:write name='bankbranchlist' property="branchName" /></td>
												<td><bean:write name='bankbranchlist' property="branchShortName" /></td>
												<td><bean:write name='bankbranchlist' property="ifscCode" /></td>
												<td style="width:250px;"><textarea rows="3" cols="30" readonly="readonly" style="resize: none;"><bean:write name='bankbranchlist' property="address" /></textarea></td>
												<td><bean:write name='bankbranchlist' property="reason" /></td>
																									
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
						<div class='pagination pagelinks' style="margin-bottom: 10px;"></div>
					</logic:present>
                  <input type='hidden' id = 'bankids'>
				</div>

			</div>
		</div>
	</div>
</body>
</html>