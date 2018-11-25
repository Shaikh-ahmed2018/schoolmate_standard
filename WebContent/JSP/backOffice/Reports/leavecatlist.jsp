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
<script type="text/javascript" src="JS/backOffice/Leave/LeaveTypes.js"></script>
<script>
	$(document).ready(function(){
    	$('[data-toggle="tooltip"]').tooltip({
       	 placement : 'bottom'
    	});
	});
</script>
<style>
#editleavebank:hover {
	cursor: pointer;
}

#deleteleavebank:hover {
	cursor: pointer;
}
#addleavebank:hover {
	cursor: pointer;
}

#xlss:hover {
	cursor: pointer;
}

.accyear{

	border : none;
	background: transparent;

}
#anchortag a:hover {
    text-decoration: underline !important;
    color : blue;
    font-weight: bold;
}

#allstudent th:nth-child(1) {
    text-align: center;
    width: 100px;
}
/* .ui-dialog{
top:50px !important;
} */
.leaveno,.headth{
	text-align: center;
}
.allstudent th:nth-child(1) {
    text-align: center;
    width: 100px;
}
.allstudent{
width : 100%;
}
.leavename{
width:100%;
}
.delete{
top : 0px;
}
/* #locationname:focus {
	min-width : 190px;
    width: auto;
} */
.errormessagediv1 .message-item a.msg-warning:before {
    display: inline-block;
    content: "\f071";
    font-family: "FontAwesome";
    font-size: 13px;
    font-weight: 200;
    margin-right: 8px;
    background: #cc0000;
    width: 20px;
    font-size: 15px;
    line-height: 15px;
    text-align: center;
    border-radius: 100%;
    color: #fff;
    margin-left: 8px;
   
 
}
.errormessagediv1 .message-item a.msg-warning {
 
    display: inline-block;
    margin: 3px 0px; 
    text-decoration: none;
    font-size: 13px;
    line-height: 21px;
    font-weight: 200;
    border: 1px solid rgba(0, 0, 0, 0.3);
    border-radius: 9px;
    padding: 4px;

    color: red;
    padding-right: 10px;
    position:relative;
}

.bg-msg-warning {
    background-color: #ffffff;
}
.glyphipos{
float: right;
}
.glyphicon-edit{
top: 5px;
}
.glyphicon-trash{
top: -7px;
} 

</style>

</head>

<body>

	<div id="loader" style="position: absolute; height: 800px; width: 800px; left: 0px; right: 0px; top: -40px; bottom: 0px; margin: auto; z-index: 99999; display: none;"><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"></div>
	<div id="leavedelete" style="display: none;">
	<p>Are You Sure to Delete?</p>
	</div>
	<div id="leaveEdit" style="display: none;">
		<div class='row'>
			<div class='col-md-12' id='txtstyle'>
				<div class="form-group clearfix ">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Branch</label>
					<div class="col-xs-7">
					<input type="text" id="schlname" tabindex="1" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group clearfix ">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Academic Year</label>
					<div class="col-xs-7">
					<input type="text" id="accyear" tabindex="1" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group clearfix ">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Leave Code</label>
					<div class="col-xs-7">
					<input type="text" id="lcode" tabindex="1" class="form-control" maxlength='4' size='4'>
					</div>
					<input type='hidden' id='lvid'>
					<input type='hidden' id='schlid'>
				</div>
				<div class="form-group clearfix ">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Leave Name</label>
					<div class="col-xs-7">
					<input type="text" id="leaname" tabindex="1" class="form-control" onkeypress='return isAlphbets(event)'>
					</div>
				</div>
				<div class="form-group clearfix ">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">No.Of Leaves</label>
					<div class="col-xs-7">
					<input type="text" id="nleave" tabindex="1" class="form-control" onkeypress='return isNumberKey(event)' maxlength='4' size='4'>
					</div>
				</div>
				<div class="form-group clearfix ">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Accumulation</label>
					<div class="col-xs-7">
					<label class='radio-inline'><input type='radio' value='YA' name='leaaccu' >Yearly</label>
					<label class='radio-inline'><input type='radio' value='MA' name='leaaccu' >Monthly</label>
					</div>
				</div>
				<div class="form-group clearfix ">
					<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Carry Forward</label>
					<div class="col-xs-7">
					<label class='radio-inline'><input type='radio' value='YC' name='carryfor'>Yearly</label>
					<label class='radio-inline'><input type='radio' value='MC' name='carryfor'>Monthly</label>
					</div>
				</div>
			</div>
		</div>
	
	</div>
	<div id="leaveDialog" style="display: none;">
			
			<!-- <div class ="col-md-6">
				<input type="checkbox" name="selectBoxD"  class="selectAll" id="displayid" value=""/> Select All
			</div> -->
		     	<div class="errormessagediv1" align="center" style = "display:none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips1"></span></a>
							</div>
				</div>
			
			
				<div class='col-md-12' id='txtstyle'>
					<div class='col-md-6'>
						<div class="form-group clearfix">
							<label for="inputPassword" style="text-align: right;" id="inputnames">Branch</label>
								
								<select id="locationname" name="location" class="form-control" tabindex="1">
								<logic:present name="locationList">
								<logic:iterate id="Location" name="locationList">
								<option value="<bean:write name="Location" property="locationId"/>">
								<bean:write name="Location" property="locationName" />
								</option>
								</logic:iterate>
								</logic:present>
								</select>	
								
						</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" style="text-align: right; line-height: 35px;">Leave name</label>
						<span style="color: red;">*</span>	
						<input type='text' name='leavename' id='leavename' class="form-control" onkeyup="convertUpperCase(this)" onkeypress='return isAlphbets(event)' onclick = "HideError(this)" tabindex="3"/>
						<input type='hidden' id='hlname' class="form-control"/>
					</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" style="text-align: right; line-height: 35px;">No.Of Leaves</label>
						<span style="color: red;">*</span>	
						<input type='text' name='noofleaves' id='noofleaves' class="form-control" maxlength='4' size='4' onkeypress='return isNumberKey(event)' onclick = "HideError(this)" tabindex="5"/>
					</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" style="text-align: right; line-height: 35px;">Any probation period before the leaves becomes valid?</label>
						<span style="color: red;">*</span>	
						<input type="text" name='isprob' id = 'isprob'  class="form-control" placeholder="months" value='0' onclick = "HideError(this)" placeholder="months" tabindex="7">
					</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" style="text-align: right; line-height: 35px;">Max.Leaves allowed per month</label>
						<span style="color: red;">*</span>	
							<input type="text" id="maxlm"  class="form-control" maxlength='3' value='0' onclick = "HideError(this)" tabindex="9">
					</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" style="text-align: right; line-height: 35px;">Leave Cycle</label>
						
							<select id="lcycle" name="lcycle" class="form-control" onclick = "HideError(this)" tabindex="11">
								<option value="A">Academic Year</option>
								<option value="M">Monthly</option>
							</select>	
						
					</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" style="text-align: right; line-height: 35px;">Count week off as leaves?</label>
						
							<select id="weekoff" name="weekoff" class="form-control" onclick = "HideError(this)" tabindex="13">
								<option value="N">No</option>
								<option value="Y">Yes</option>
							</select>	
					</div>
				</div>
					
					<div class='col-md-6'>
					<div class="form-group clearfix">
					<label for="inputPassword" align="right" id="inputnames">Academic Year</label>
						
							<select id="academicyear" name="academicyear" class="form-control" onclick = "HideError(this)" tabindex="2">
								<logic:present name="AccYearList">
								<logic:iterate id="AccYear" name="AccYearList">
								<option value="<bean:write name="AccYear" property="accyearId"/>">
								<bean:write name="AccYear" property="accyearname" />
								</option>
								</logic:iterate>
								</logic:present>
							</select>
						
					</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" style="text-align: right; line-height: 35px;">Leave Code</label>
						<span style="color: red;">*</span>						
						<input type='text' name='leavecode' id='leavecode' onkeyup="convertUpperCase(this)" onclick = "HideError(this)"  class="form-control" maxlength='4' tabindex="4"/>
						<input type='hidden' id='hlcode' class="form-control" />
					</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" style="text-align: right; line-height: 35px;">Gender Specific?</label>
						
							<select id="isgender" name="isgender" class="form-control" onclick = "HideError(this)" tabindex="6">
								<option value="A">ALL</option>
								<option value="F">Female</option>
								<option value="M">Male</option>
							</select>
						
					</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" style="text-align: right; line-height: 35px;">Min.Leaves allowed per month</label>
						<span style="color: red;">*</span>	
						<input type="text" id="minlm" class="form-control" maxlength='3' value='0' onclick = "HideError(this)" tabindex="8">
					</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" style="text-align: right; line-height: 35px;">Max consecutive leaves per month</label>
						<span style="color: red;">*</span>	
						<input type="text" id="maxcl" class="form-control" maxlength='3' value='0' onclick = "HideError(this)" tabindex="10">
					</div>
					<div class="form-group clearfix ">
						<label for="inputEmail" style="text-align: right; line-height: 35px;">Carry Forward?</label>
						
							<select id="carryf" name="carryf" onclick = "HideError(this)" class="form-control"  tabindex="12">
								<option value="N">No</option>
								<option value="MY">Both</option>
								<option value="MC">Month</option>
								<option value="YC">Year</option>
							</select>
						
					</div>
					
					</div>
				</div>
	</div>



	<div class="content" id="div1">
		<div id="pageHeading">
			<p>
				<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span
					id="pageHeading">Leave Type</span>
			</p>
		</div>
	
		<logic:present name="successmessagediv"  scope="request">
			<div class="successmessagediv"  align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">

				<a href="#" class="msg-success bg-msg-succes"><span
					class="success"></span></a>
			</div>
		</div>


		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">

				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="errormessagediv1" align="center" style="display: none;">
			<div class="message-item1"></div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #000000;vertical-align: text-top">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Leave
						Type Listing
					</h3></a>
				<div class="navbar-right">
					<span id="addleavebank" class="buttons">New</span>
				</div>
			</div>

			<input type="hidden" name="attnhidden" id="snoid" value="" />

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
							<span id="xlss"><img src="images/xl.png" class="xl"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>
			<input type="hidden" name="curr_loc" class="curr_loc" id="curr_loc" value='<logic:present name="curr_loc"><bean:write name="curr_loc"/></logic:present>'></input>
			<input type="hidden" name="curacayear" class="curacayear" id="curacayear" value='<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>'></input>
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
								<select id="locId" name="location" class="form-control" onchange="HideError(this)">
								<logic:present name="locationList">
								<logic:iterate id="Location" name="locationList">
								<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
								</logic:iterate>
								</logic:present>
								</select>
								</div>
						</div>
					</div>	
					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Academic Year</label>
							<div class="col-xs-7">
								<select id="accyearp" name="accyearp" class="form-control" required>
									<logic:present name="AccYearList">
										<logic:iterate id="AccYear" name="AccYearList">
											<option
												value="<bean:write name="AccYear" property="accyearId"/>">
												<bean:write name="AccYear" property="accyearname" />
											</option>
										</logic:iterate>
									</logic:present>
								</select>
							</div>
						</div>	
					</div>
					
					<logic:present name="LeaveListDetails" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Sl No.</th>
							<th>Leave Code</th>
							<th>Leave Name</th>
							<th>No of Leaves</th>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="LeaveListDetails" name="LeaveListDetails">
								<tr id='<bean:write name='LeaveListDetails' property="leaveid"/>'>
								<td class='slno'><bean:write name='LeaveListDetails' property="count"/></td>
								<td class='sname'><bean:write name="LeaveListDetails" property='shortName'/></td>
								<td class='lname'><bean:write name="LeaveListDetails" property='leaveName'/></td>
								<td class='accu'><bean:write name="LeaveListDetails" property='noofleaves'/><span class="glyphicon glyphicon-trash glyphipos" data-toggle="tooltip" data-original-title="Remove Leave"></span><span class="glyphicon glyphicon-edit glyphipos" data-toggle="tooltip" data-original-title="Update Leave"></span></td>
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

	<!-- <script src="JS/newUI/jquery.js"></script>
	<script src="JS/newUI/bootstrap.min.js"></script>
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script> -->
</body>
</html>