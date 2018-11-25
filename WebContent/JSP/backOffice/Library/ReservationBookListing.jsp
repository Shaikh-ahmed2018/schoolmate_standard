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

<script type="text/javascript" src="JS/backOffice/Library/ReservationBookListing.js"></script>




<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */
</style>

<style>
.download:hover{

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

	<div class="content" id="div1">
		<div id="dialog"></div>
		<div class="col-md-12 input-group" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Reservation List</span>
			</p>
		</div>


		
			<!-- <div class=" col-md-4" style="margin-bottom: 10px;margin-top: 12px;">
				<input type="text" name="searchname" id="searchname" style="height: 35px;"
					class="form-control" Placeholder="Search......"
					value=""/> 	
					<div class= "form-group clearfix" style="margin-bottom:33px;"></div>
				
			</div> -->
			
		
			
				
		
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<div class="successmessagediv1"  style="display: none;" >
			<div class="message-item" style="text-align: center;">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel panel-primary clearfix">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;;vertical-align: text-top;"><h3 class="panel-title"  style="color: #000; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp; Reservation List
					</h3></a>
					

				<div class="navbar-right">

							<a href="LibraryMenu.html?method=reservations"> <span
							class="buttons" id="add">New</span></a> 
							<span class="buttons" id="edit">Modify</span>
							<span class="buttons" id="delete">Remove</span>			
											
				</div>
			</div>
						
				<!-- 	<div class="col-md-6"></div> -->
			<!-- pop up -->


			<div id="collapseOne" class="accordion-body collapse in">
		
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top:2%; padding-bottom:10px;">
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Branch</label>
						<div class="col-xs-7">
							<select id="locationname" name="locationnid" class="form-control" required>
								<!-- <option value="all">ALL</option> -->
								<logic:present name="locationList">
									<logic:iterate id="Location" name="locationList">
										<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
							</select>
						</div>
					</div>
				</div>
				
			 <div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-top:2%; padding-bottom:10px;">
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Library Branch</label>
						<div class="col-xs-7">
							<select id="liblocId" name="liblocId" class="form-control" required>
								<option value="all">ALL</option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
				
				 
						<table class='table' id='allstudent' style="width: 100%">
							<thead>
								<tr>
									<th style="text-align: center"><input type='checkbox' name='select' class='selectall' id='selectall' style='text-align: center' /></th>
									<th>Accession No</th>
									<th>Book Title</th>
									<th>Author</th>
									<th>Library Branch</th>
								    <th>Subscriber Name</th>
									<th>From date</th>
									<th>To date</th>
									<th>User Type</th>
								</tr>
							</thead>
							<tbody>

							</tbody>

						</table>
						
				 <div class='pagebanner'>
					<select id='show_per_page' >
						<option value='50'>50</option>
						<option value='100'>100</option>
						<option value='200'>200</option>
						<option value='300'>300</option>
						<option value='400'>400</option>
						<option value='500'>500</option>
					</select>
						<span  class='numberOfItem'></span>	</div>
					<div class='pagination pagelinks'></div>
				
			 </div>
     	  
	      </div>
   </div>
</div>


<!-- 
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script> -->
</body>
</html>