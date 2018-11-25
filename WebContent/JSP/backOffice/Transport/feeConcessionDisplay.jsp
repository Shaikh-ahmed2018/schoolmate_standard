<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>


<script type="text/javascript" src="JS/backOffice/Transport/TransportFeeConcession.js"></script>




<style>
#termwiseconcession{
width: 100%;
}
#termwiseconcession th{
width: 20%;
}
#editDesignationId:hover {
	cursor: pointer;
}

.glyphicon-pencil:hover {
	cursor: pointer;
}

#edit:hover {
	cursor: pointer;
}

.ui-dialog{
    position:fixed;
    top:132px !important;          
}

.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all {
	z-index: 1;
	display: block;
	left: 478px;
	width: 346px;
	height: 300px;
	overflow: scroll;
	position: absolute;
}

.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all {
	overflow: scroll;
	max-height: 300px;
}

#trash:hover {
	cursor: pointer;
}
table tr[class^='STU'] td{
vertical-align: middle;
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
.paymentStatus span {
     display: inline-block;
    padding: 0 5px;
    margin: 0 2px;
    min-width: 80px;
    text-align: center;
    color: #fff;
    font-weight: 600;
    border-radius: 6px;
    box-shadow: 5px 5px 5px #ccc;
}
span.Not.Paid{
background-color: #f00;
}
span.Paid{
background-color: rgba(0, 158, 0, 0.66);
}
.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons {
    left: 0 !important;
    right: 0 !important;
    margin: auto;
}
.concessionType{
text-align: left;
}
input[type='text']{
width: 100%;
}
.equalconcession,.partialconcession{
display: none;
}
 
/* .ui-dialog {
    position: fixed;
    top: 130px !important;
} */
legend {
    display: block;
    width: 100%;
    padding: 0;
    font-size: 21px;
    line-height: inherit;
    color: #333;
    border: 0;
    border-bottom: 1px solid #e5e5e5;
    font-family: sans-serif;
    font-size: 15px;
    font-weight: bold;
    margin: 0;
}
</style>
</head>

<body class="feeconcession">
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>

	<div class="successmessagediv" style="display: none;">
		<div class="message-item">
			<!-- Warning -->
			<a href="#" class="msg-success bg-msg-succes"
				style="text-align: center;"><span
				class="sucessmessage"> </span></a>
		</div>
	</div>
	<div class="errormessagediv" style="display: none;">
		<div class="message-item">
			<!-- Warning -->
			<a href="#" class="msg-warning bg-msg-warning"
				style="text-align: center;"><span
				class="validateTips"></span></a>
		</div>
	</div>
	<div id="myDialog" style="display:none;">
	<div class='row'>
			<div class='col-md-6'>
			
				<!-- <div class='form-group clearfix'>
					<label for='labelforStudentID' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>StudentID No.</label>
					<div class="col-xs-7">
						<input type="text" name='StudentID' id='StudentID' />
					</div>
				</div> -->
				<div class="form-group clearfix" style="position:relative;">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
									<select id="locId" name="locationnid"
										class="form-control" required>
										<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option
													value="<bean:write name="Location" property="locationId"/>"><bean:write
														name="Location" property="locationName" /></option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
				<input type="hidden" name='hstudentId' class='hstudentId' />
						<input type="hidden" name='hlocID' class='hlocID' />
						<input type="hidden" name='hrouteId' class='hrouteId' />
						<input type="hidden" name='hstageId' class='hstageId' />
				<div class='form-group clearfix'>
					<label for='labelforAdmissionNo' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Admission
						No.<font color="red"> *</font></label>
					<div class="col-xs-7">
						<input type="text" name='admissionNo' id='admissionNo' class='form-control'/>
						<input type="hidden" name='hstudentId' class='hstudentId' />
						<input type="hidden" name='hlocID' class='hlocID' />
					</div>
				</div>
				<div class='form-group clearfix'>
					<label for='labelforName' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Stage Name</label>
					<div class="col-xs-7">
						<input type="text" name='stagename' id='stagename' class='form-control' readonly="readonly" />
					</div>
				</div>
				<div class='form-group clearfix'>
					<label for='labelforName' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Route Name</label>
					<div class="col-xs-7">
						<input type="text" name='routename' id='routename' class='form-control' readonly="readonly"  />
					</div>
				</div>
				<div class='form-group clearfix concessionDiv' style="display: none;">
					<label for='labelforName' class='control-label col-xs-5'
						style='text-align: right; line-height: 35px;'>Concession Type</label>
					<div class="col-xs-7">
						<!-- <div class="concessionType"><label for='labelforFullConcession' class='control-label' >Full Concession </label> <input type="radio" name='contype' value="full"  /></div>
						<div class="concessionType"><label for='labelforEqualConcession' class='control-label' >Equally Concession </label> <input type="radio" name='contype' value="equal" /></div>
						 --><div class="concessionType"><label for='labelforPartialConcession' class='control-label' >Concession Term wise</label> <input type="radio" name='contype' value="partial" /></div>
					</div>
				</div>
				
			</div>
			<div class='col-md-6'>
			<div class='form-group clearfix' style="position:relative;">
					<label for='AcademicYear' class='control-label col-xs-4'
						style='text-align: right; line-height: 35px;'>Academic
						Year</label>
					<div class="col-xs-7">
						<select id="AcademicYearFor" name="AcademicYearFor" class="form-control" required class='from-control'>
							<logic:present name="AccYearList">
								<logic:iterate id="AccYear" name="AccYearList">
									<option
										value="<bean:write name="AccYear" property="accyearId"/>"><bean:write
											name="AccYear" property="accyearname" /></option>
								</logic:iterate>
							</logic:present>
						</select>
					</div>
				</div>
				<div class='form-group clearfix'>
					<label for='labelforName' class='control-label col-xs-4'
						style='text-align: right; line-height: 35px;'>Student Name</label>
					<div class="col-xs-7">
						<input type="text" name='student' id='student' class='form-control' readonly="readonly"/>
					</div>
				</div>
			
				<div class='form-group clearfix'>
					<label for='labelforclass' class='control-label col-xs-4'
						style='text-align: right; line-height: 35px;'>Class-Division</label>
					<div class="col-xs-7">
						<input type="text" name='class_section' id='class_section'  class='form-control' readonly="readonly"/>
						<input type="hidden" name='hclassId' id='hclassId' />
						<input type="hidden" name='hspecialization' id='hspecialization' />
					</div>

				</div>
				<div class='form-group clearfix equalconcession'>
					<label for='labelforscholorshipAmount' class='control-label col-xs-4'
						style='text-align: right; line-height: 35px;'>Concession Amount</label>
					<div class="col-xs-8">
						<input type="text" name='scholorshipAmount' id='scholorshipAmount'  />
					</div>

				</div>
			</div>
			<div class='col-md-12'>
			<div class="form-group clearfix partialconcessionname" style="display: none;"><label for='labelforscholorshipAmount' class='control-label col-xs-4'
						style='text-align: left; line-height: 35px;'>Concession Amount</label></div>
				<div class='form-group clearfix partialconcession'>
					
					<div class="col-xs-12">
						<fieldset style="width: auto;">
						<legend style="position: static !important;">Term wise :</legend>
						<table id="termwiseconcession" class="allstudent" style="font-size: 14px;">
							<thead style="background-color:#dedcdc;">
								<tr>
									<th style="text-align: center;">Term Name</th>
									<th style="text-align: center;">No Of Months</th>
									<th style="text-align: center;">Stage Amount</th>
									<th style="text-align: center;">Fee Amount</th>
									<th style="text-align: center;">Concession Amount</th>
								</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
						</fieldset>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div id="dialog" style="display:none;"></div>
	<div class="content" id="div1">
		
			<div class="" id="div2">
				<p class="transportheader">
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Transport Fee Concession Details</span>
				</p>
			</div>
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading clearfix" role="tab" id="headingOne">

					<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
						style="color: #000; vertical-align: text-top; ">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Transport Fee Concession Student List
						</h3>
					</a>
				<div class="navbar-right">
				 <span class="buttons" id="add">New</span>
				 <span id="delete" class="buttons">Remove</span>
				</div>
				
				
			</div>
				<div id="collapseOne" class="accordion-body collapse in" role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body own-panel">
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; color: #000; margin-top: 20px;">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
									<select id="locationname" name="locationnid"
										class="form-control" required>
										<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option
													value="<bean:write name="Location" property="locationId"/>"><bean:write
														name="Location" property="locationName" /></option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"> Class</label>
								<div class="col-xs-7">

									<select class="form-control" onkeypress="HideError()"
										name="classname" id="classname">
										<option value="all">ALL</option>
									</select>

								
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Search</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" id="searchvalue"
										Placeholder="Search......"
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
								</div>
							</div>
							
							
							<div class="form-group clearfix">
								<div class="col-xs-5"></div>
								<div class="col-xs-7">
								<span type="button" class="buttons" id="search">Search</span>
								<span type="reset" class="buttons" id="resetbtn">Reset</span>
								</div>
							</div>
							
						

						</div>

						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-7">
									<select id="Acyearid" name="accyear" class="form-control" required>

										<logic:present name="AccYearList">
											<logic:iterate id="AccYear" name="AccYearList">
												<option
													value="<bean:write name="AccYear" property="accyearId"/>"><bean:write
														name="AccYear" property="accyearname" /></option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Division</label>
								<div class="col-xs-7">
									<select id="sectionid" name="sectionid" class="form-control"
										required>
										<option value="all">ALL</option>
									</select>
								</div>
							</div>

							
						</div>
						<input type="hidden" name="Acyearid" id="Acyearid"
							value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>



						<div id="collapseOne" class="accordion-body collapse in">
							<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
								<table class="table" id="allstudent" width="100%">
									<thead>
										<tr>
											<th><input type='checkbox' id='selectAll' /></th>
											<!-- <th class="sortable">Student Id No</th> -->
											<th class="sortable">Admission No</th>
											<th class="sortable">Student Name</th>
											<th class="sortable">Class - Division</th>
											<th class="sortable">Concession Type</th>
											<th class="sortable">Photo</th>

										</tr>
									</thead>
									<tbody></tbody>
								</table>
								<div class="pagebanner">
									<select id="ShowPerPage"><option value="50">50</option>
										<option value="100" selected>100</option>
										<option value="200">200</option>
										<option value="300">300</option>
										<option value="400">400</option>
										<option value="500">500</option>
									</select> <span class='numberOfItem'></span>
								</div>
								<div class="pagination pagelinks"></div>

							</div>
							<br />
						</div>
					</div>
				</div>
				</div>
				</div>
					</div>
</body>
</html>