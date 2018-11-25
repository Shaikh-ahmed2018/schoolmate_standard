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
<script type="text/javascript"  src="JS/backOffice/Settings/editLocation.js"></script>

<style>
	
	#allstudent td:nth-child(1){
		width : 300px;
	}

</style>
</head>

<body>

	<div class="content" id="div1">
		<div id="dialog"></div>
		 
			<div class="" id="div2">

				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">School Details</span>
				</p>
			</div>
	
				<input type="hidden" name="searchterm" class="searchtermclass"
					id="searchexamid"
					value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />
		</logic:present>'></input>
			<!-- </form> -->
		 

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
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;School
						Details
					</h3></a>

				<div class="navbar-right">

					<logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="SLCADD" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<a href="menuslist.html?method=addSchool"> <span
										id="add" class="buttons">New</span>
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
									<input type='hidden' id='isvalid' value='modify'>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present>
				</div>
			</div>
      
      <input type="hidden" id="custstatus" value="<logic:present name='schlstatus' scope='request' ><bean:write name='schlstatus'/></logic:present>">

			<div id="collapseOne" class="accordion-body collapse in">

				<input type="hidden" id="hiddenstatus" name="hiddenstatus"
					value="<logic:present name='status' scope='request' ><bean:write name='status'/></logic:present>" />

				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th>School</th>
									<th>Contact Details</th>
									<th>School Logo</th>
								</tr>
							</thead>
							<tbody>
								
							</tbody>

						</table>
						<!-- <div class='pagebanner'>
							<select id='show_per_page'><option value='50'>50</option>
								<option value='100'>100</option>
								<option value='200'>200</option>
								<option value='300'>300</option>
								<option value='400'>400</option>
								<option value='500'>500</option></select> <span class='numberOfItem'></span>
						</div>
						<div class='pagination pagelinks' style="margin-bottom: 10px;"></div> -->
					<%-- </logic:present> --%>

				</div>

			</div>
		</div>
	</div>
</body>
</html>