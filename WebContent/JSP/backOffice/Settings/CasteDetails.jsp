<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <script type="text/javascript" src="JS/backOffice/Settings/Caste.js"></script>
</head>

<body>

	<div class="content" id="div1">
		<div id="dialog"></div>
		<div class="col-md-12 input-group" id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Caste Details</span>
			</p>
		</div>
			<input type="hidden" name="searchterm" class="searchtermclass"
				id="searchexamid"
				value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" /></logic:present>'></input>

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
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title" style="color: #000000;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Caste
							Details
						</h3></a>


					<div class="navbar-right">

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="CASCRE" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<a href="religionCastCasteCategory.html?method=addCaste">
											<span class="buttons">New</span>
										</a>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="CASUPD" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<span class="buttons" id="editReligion">Modify</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>

						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="CASDEL" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<span class="buttons" id="inactive">InActive</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>


						<!-- <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span> -->


					</div>
				</div>
				<!-- pop up -->
    
    <input type="hidden" id="currentstatus" name="currentstatus" 
    value="<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>" />

    <input type="hidden" id="historysearchname" 
	  value='<logic:present name="historysearchname" scope="request"><bean:write name="historysearchname" /></logic:present>'></input>
	
	 <input type="hidden" id="historystatus" 
	  value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus" /></logic:present>'></input>


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

				<input type="hidden" id="hiddenstatus" name="hiddenstatus"
					value="<logic:present name='status' scope='request' ><bean:write name='status'/></logic:present>" />
	

		<div id="collapseOne" class="accordion-body collapse in">
			<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-bottom: 10px">
					
				<div class="col-md-6"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
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
				<logic:present name="religionList" scope="request">
					<table class="table" id="allstudent">
						<thead>
							<tr>
								<th><input type='checkbox' name='selectall' id='selectall'
									style='text-align: center' /></th>
								<th>Religion</th>
								<th>Caste</th>
								<th>Remarks</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name='religionList' id="religionList">
								<tr>
									<td><input type='checkbox' name='select' class='select'
										style='text-align: center'
										id='<bean:write name='religionList' property="casteId"/>' /></td>
									<td><bean:write name='religionList' property="religion" /></td>
									<td><bean:write name='religionList' property="caste" /></td>
									<td><bean:write name='religionList' property="remarks" /></td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>

					<div class='pagebanner'>
						<select id='show_per_page'>
							<option value='50'>50</option>
							<option value='100'>100</option>
							<option value='200'>200</option>
							<option value='300'>300</option>
							<option value='400'>400</option>
							<option value='500'>500</option>
						</select> <span class='numberOfItem'></span>
					</div>
					<div class='pagination pagelinks'></div>
				</logic:present>

			</div>
		</div>
	</div>
	</div>

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>