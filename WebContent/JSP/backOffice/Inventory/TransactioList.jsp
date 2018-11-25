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

<script type="text/javascript"
	src="JS/backOffice/Inventory/InventoryTransactionList.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
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

.button {
    background-color: #07AAB9;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}


</style>
</head>

<body>

	<div class="content" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Inventory Transaction List</span>
			</p>
		</div>


		<%-- <form id="myForm"
			action="" method="post">
			<div class="input-group col-md-4" style="margin: 20px 0px;">
				<input type="text" name="searchname" id="searchname"
					class="form-control" Placeholder="Search......"
					value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'>
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" id="search"
						onclick="myFunction()" value="Submitform">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form> --%>
		<div align="right" class="input-group col-md-4"
			style="margin: 20px 0px;">


			<input type="text" class="form-control" Placeholder="Search......"
				id="searchterm"
				value="<logic:present name="searchTerm"><bean:write name="searchTerm"></bean:write></logic:present>">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="search">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>

		<input type="hidden" name="searchterm" class="searchtermclass"
			id="searchexamid"
			value='<logic:present name="searchexamlist"><bean:write name="searchexamlist" />

													</logic:present>'></input>


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

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title" style="color: #000000;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Inventory
							Transaction Details
						</h3></a>
					<div class="navbar-right">
					
					<input type="button" id="returnitem" value="Return" style="font-size: 15px;padding:2px; vertical-align: 7px;border-radius: 3px;    cursor: pointer; background-color: #07AAB9;" />
					
					
						<a href="menuslist.html?method=AddTransactionDetails">
						
						<span
							class="buttons" data-toggle="tooltip"
							data-placement="bottom" title="Add">Add
						</span></a> &nbsp;
							
							<span
							class="buttons" id="edit"
							data-toggle="tooltip" data-placement="bottom" title="Edit">Edit</span>
							
							<!-- <span
							class="glyphicon glyphicon-pencil" id="returnitem"
							data-toggle="tooltip" data-placement="bottom" title="Return"></span> -->
							

						<span class="buttons" id="delete"
							data-toggle="tooltip" data-placement="bottom" title="Delete">Delete</span>

						
					<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span>

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
					<div class="panel-body"
						style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">


						<logic:present name="TransactionDetailsList" scope="request">
							<display:table class="table" pagesize="10"
								name="requestScope.TransactionDetailsList"
								requestURI="menuslist.html?method=TransactionList"
								decorator="com.centris.campus.decorator.InventoryTransactionDecorator"
								id="allstudent">
								
								<display:column sortable="true"
									title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />"><input type='checkbox' name='check_box' class='TransactionDetailsList_Checkbox_Class' id='${allstudent.transaction_id},${allstudent.status}'/></display:column> 
								
								<display:column property="item_id" sortable="true"
									title="Item Id <img src='images/sort1.png' style='float: right'/>"></display:column>
								
								<display:column property="item_type" sortable="true"
									title="Item Type <img src='images/sort1.png' style='float: right'/>"></display:column>
								
								<display:column property="item_name" sortable="true"
									title="Item Name <img src='images/sort1.png' style='float: right'/>"></display:column>
								
								<display:column property="requested_by" sortable="true"
									title="Requested By <img src='images/sort1.png' style='float: right'/>"></display:column>
								
								<display:column property="issued_by" sortable="true"
									title="Issued By <img src='images/sort1.png' style='float: right'/>"></display:column>
									
									<display:column property="returned_by" sortable="true"
									title="Returned By <img src='images/sort1.png' style='float: right'/>"></display:column>
									
								<display:column property="issued_to" sortable="true"
									title="Issued To <img src='images/sort1.png' style='float: right'/>"></display:column>
								
								<display:column property="issued_date" sortable="true"
									title="Issued Date <img src='images/sort1.png' style='float: right'/>"></display:column>
								
								<display:column property="returned_date" sortable="true"
									title="Return Date <img src='images/sort1.png' style='float: right'/>"></display:column>
									
									<display:column property="remarks" sortable="true"
									title="remarks" ><img src='images/sort1.png' style='float: right'/>"></display:column>
								
								<display:column property="status" sortable="true"
									title="Status" ><img src='images/sort1.png' style='float: right'/>"></display:column>
									
								<%-- 	<display:column property="delayed_status" sortable="true"
									title=" Delayed Status" ><img src='images/sort1.png' style='float: right'/>"></display:column> --%>
									

								<display:setProperty name="paging.banner.page.separator"
									value=""></display:setProperty>

								<display:setProperty name="paging.banner.placement"
									value="bottom"></display:setProperty>
							</display:table>

						</logic:present>

					</div>
					<br />
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