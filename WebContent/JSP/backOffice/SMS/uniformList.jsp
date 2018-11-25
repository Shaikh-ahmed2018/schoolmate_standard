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

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/SMS/uniformList.js"></script>

<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet"> -->

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">


<style>
.glyphicon:hover {
	cursor: pointer;
}

.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}
</style>
<style>
.buttons {
	vertical-align: -28px;
}

#allstudent  th:nth-child(2), td:nth-child(2), th:nth-child(3), td:nth-child(3),
	th:nth-child(5), td:nth-child(5), th:nth-child(6), td:nth-child(6) {
	text-align: center;
	width: 9%;
}

#allstudent th:nth-child(4), td:nth-child(4) {
	text-align: left;
	width: 12%;
}

#allstudent th:nth-child(7) {
	text-align: center;
}
</style>

</head>

<body>

	<div class="content" id="div1">
		<div class="col-md-8" id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Uniform</span>
			</p>
		</div>


		<!-- <form id="myForm"
			action="menuslist.html?method=homeworklist" method="post"> -->

		<%-- <div class="input-group col-md-4" style="margin: 20px 0px;">
				<input type="text" name="searchname" id="searchname"
					class="form-control" Placeholder="Search......"
					value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'>
				<span class="input-group-btn">
					<!-- <button class="btn btn-default" type="button" id="search"
						onclick="myFunction()" value="Submitform">
						<i class="fa fa-search"></i>
					</button> -->
				</span>
			</div> --%>

		<br /> <input type="hidden" name="searchterm" class="searchtermclass"
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
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Uniform

					</h3></a>



				<div class="navbar-right">
				
				<logic:present name="UserDetails" scope="session">
								 <logic:iterate id="daymap" name="UserDetails" property="permissionmap" scope="session">
									<logic:equal value="USADD" name="daymap" property="key">
										<logic:equal value="true" name="daymap" property="value">
										
										<a href="menuslist.html?method=addUniform"><span class="buttons" data-placement="bottom">Add</span> </a>
										
										</logic:equal>
									</logic:equal>
								</logic:iterate>
							</logic:present>

					
						
					<!-- <span
						class="glyphicon glyphicon-pencil" id="editstream"
						data-toggle="tooltip" data-placement="bottom" title="Edit"></span> -->


					<!-- 	<span class="glyphicon glyphicon-trash" id="delete"
						data-toggle="tooltip" data-placement="bottom" title="Delete"></span>
 -->

					<!-- <img src="images/download.png" class="download" id="iconsimg"
						data-toggle="modal" data-target="#myModal" data-toggle="tooltip"
						data-placement="bottom" title="Download"> -->

				</div>
				<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>
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




			<input type="hidden" name="searchterm" class="searchtermclass"
				id="hmeetingid" />




			<div id="collapseOne" class="accordion-body collapse in clearfix">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

					<logic:present name="meetinglist" scope="request">
						<table class="table" id="allstudent">
							<thead>
								<tr>
									<!-- <th><input type='checkbox' name='selectall' id='selectall'/></th> -->
									<th>Sl.No</th>
									<th>Date</th>
									<th>Branch</th>
									<th>StudentName</th>
									<th>ClassName</th>
									<th>SectionName</th>
									<th>smstext</th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate id="meetinglist" name="meetinglist">
									<tr>
										<%-- <td><input type='checkbox' name='selectall' class='select' value='<bean:write name="meetinglist" property='uniformcode'/>' id='select' onClick='selectAll()' /></td> --%>
										<td><bean:write name="meetinglist" property='slNo' /></td>
										<td><bean:write name="meetinglist" property='date' /></td>
										<td><bean:write name="meetinglist" property='locId' /></td>
										<td><bean:write name="meetinglist" property='studentname' /></td>
										<td><bean:write name="meetinglist" property='classname' /></td>
										<td><bean:write name="meetinglist" property='sectionname' /></td>
										<td><bean:write name="meetinglist" property='smstext' /></td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:present>



				</div>
				<div class="pagebanner">
					<select id="show_per_page"><option value="50">50</option>
						<option value="100">100</option>
						<option value="200">200</option>
						<option value="300">300</option>
						<option value="400">400</option>
						<option value="500">500</option></select> <span class="numberOfItem"></span>
				</div>
				<div class="pagination pagelinks"></div>
				<br />
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