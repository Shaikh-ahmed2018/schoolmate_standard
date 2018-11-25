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



<script type="text/javascript" src="JS/backOffice/Teacher/LeaveRequest.js"></script>


<script type="text/javascript">

	function handle(e){
	
	var searchText = $("#searchterm").val();
    if(e.keyCode === 13){
        e.preventDefault(); 

        window.location.href ="teachermenuaction.html?method=leaveRequest&searchTerm="
			+ searchText;
    }
}

</script>

<style>
#plus:hover {
	cursor: pointer;
}

#editID:hover {
	cursor: pointer;
}

#delete:hover {
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
</head>

<body>

	<div id="dialog" style="display:none;"><p>Are You Sure to Cancel?</p></div>
	<div class="content" id="div1">
		<div class="col-md-12 input-group" id="div2">
			<p id="pageHeading">
				<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span>Leave Request Details</span>
		</p>
		</div>
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
			</div>
		</div>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span class="validateTips"></span></a>
			</div>
		</div>

		<input type="hidden" name="userhidden" class="userhiddenclass" id="userhiddenid" value='<logic:present name="parentid"><bean:write name="parentid" /></logic:present>'></input>
		
		<input type="hidden" name="searchterm" class="searchtermclass" id="searchterms" value='<logic:present name="searchterm"><bean:write name="searchterm" />
		</logic:present>'></input>

		 <input type="hidden" name="hleavelist" id="leavestatusid" value=''/>	

		<input type="hidden" name="attnhidden" id="snoid" value="" /> <input
			type="hidden" name="attnhidden1" id="requesttoid" value="" />

		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Leave
						Request Details
					</h3></a>
				<div class="navbar-right">
					<!--  <a href="parentMenu.html?method=requestLeavescreenadd"  id="plus" > -->
					<a onClick='sendrequest()' id="plus"> <span
						class="buttons">New</span>
					</a>
					<!--  <a href="teachermenuaction.html?method=requestLeavescreen" id="editID"> -->
					<a  id="editID"> <span
						class="buttons">Modify</span>
					</a>
					<!--  </a> -->

					<!--  <a href="teachermenuaction.html?method=leaveRequest">  -->
					<span id="delete" class="buttons">Cancel</span>
					<!--  </a> -->

				<!-- 	 <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span> -->

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
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<div class="col-md-6">
				<div class="form-group clearfix">
				<label class="form-lable col-xs-5" style="text-align: right;">Search</label>
				<div class="col-xs-7">
				<input type="text" class="form-control" Placeholder="Search......" onkeypress="handle(event)" style = "height:35px;" id="searchterm" value="<logic:present name="searchterm"><bean:write name="searchterm"/></logic:present>" /> 
			</div>
			<!-- <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span> -->


			<div class="form-group">
			</div>
		</div>
		<div class="form-group clearfix">
		<div class="col-xs-5">
		</div>
		<div class="col-xs-7">
		<span class="buttons" id="search">Search</span>
		<span class="buttons" id="resetbtn">Reset</span>
		</div>
		</div>
		</div>
					
					
					<logic:present name="leavelist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Select</th>
							<th>Requested To</th>
							<th>Leave Name</th>
							<th>No. Of Leaves</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Leave Status</th>
							<th>Leaves Approved</th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="leavelist" name="leavelist">
								<tr>
								<td><input type="radio" name="getempid" class="select" value='<bean:write name="leavelist" property='status'/>' id='<bean:write name="leavelist" property='sno'/>'></td>
								<td><bean:write name="leavelist" property='requestto'/></td>
								<td><bean:write name="leavelist" property='leavetype'/></td>
								<td><bean:write name="leavelist" property='totalleave'/></td>
								<td><bean:write name="leavelist" property='fromdate'/></td>
								<td><bean:write name="leavelist" property='todate'/></td>
								<td><bean:write name="leavelist" property='status'/></td>
								<td><bean:write name="leavelist" property='leavesapproved'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
				<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
   				<span  class='numberOfItem'></span>	
   				</div><div class='pagination pagelinks'></div>
				</div>
				<br />
			</div>
		</div>
	</div>


</body>
</html>