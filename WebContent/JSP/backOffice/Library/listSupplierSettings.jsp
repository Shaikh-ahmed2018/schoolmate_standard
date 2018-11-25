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

<script type="text/javascript" src="JS/backOffice/Library/ListSupplierSettings.js"></script>



<style>

</style>

</head>

<body>

	<div class="content" id="div1">
		<div id="dialog"></div>
		<div class="col-md-12 input-group" id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Supplier Details</span>
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
		<div class="successmessagediv"  style="display: none;" >
			<div class="message-item" style="text-align: center;">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="panel panel-primary clearfix">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;vertical-align: text-top;"><h3 class="panel-title"  style="color: #000000;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp; Supplier Details
					</h3></a>
					

				<div class="navbar-right">
							<span class="buttons" id="add">New</span> 
							<span class="buttons" id="editId">Modify</span>
							<span class="buttons" id="delete">Remove</span>
			</div>
			</div>
			<!-- pop up -->

  <input type="hidden" id="historysearch"  
  value='<logic:present name="historysearch" scope="request"><bean:write name="historysearch"/></logic:present>' />
  
			<div id="collapseOne" class="accordion-body collapse in">
		
							<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 16px; color: #000;margin-top: 20px;">
									<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Search</label>
							<div class="input-group col-xs-7">
								<input type="text" name="searchname" id="searchValue"
									style="margin-left: 15px;width: 275px;" class="form-control"
									value="<logic:present name='searchnamelistValue' scope='request'><bean:write name='searchnamelistValue'/></logic:present>">
								
							</div>
						</div>
								<div class="form-group clearfix">
								<div class="col-xs-5"></div>
								<div class="col-xs-7">
								<span type="button" class="buttons" id="search">Search</span>
								<!-- <span type="reset" class="buttons" id="resetbtn">Reset</span> -->
								</div>
							</div>
							
							</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="" style="padding: 15px;">
				
					<div class="row">
			<div id="collapseOne" class="panel-collapse collapse in ">
					<div class="panel-body own-panel">
						<div class="row"></div>
                        
						<table class='table' id='allstudent' style="width: 100%">
							<thead>
								<tr>
									<th style="text-align: center"><input type='checkbox' name='select' class='selectall' id='selectall' style='text-align: center' /></th>
									<th>Supplier Name</th>
									<th>Contact Details</th>
									<th>Address</th>
									</tr>
							</thead>
							<tbody>

							</tbody>

						</table>
						
					

			</div>
     	 </div>
     	 
					
					<div class='pagebanner' class="panel panel-primary clearfix">
				
					<select id='show_per_page' >
					<option value='50'>50</option>
					<option value='100'>100</option>
					<option value='200'>200</option>
					<option value='300'>300</option>
					<option value='400'>400</option>
					<option value='500'>500</option></select>
					<span  class='numberOfItem'></span>	
					
					
				</div>
				<div class='pagination pagelinks'></div>
				<br />
			</div>
		</div>
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