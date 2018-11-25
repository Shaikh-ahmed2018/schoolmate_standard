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
<script type="text/javascript" src="JS/backOffice/Exam/AddnewExam.js"></script>
<style>
#allstudent tr td {
color: black;
text-align: center;

}
#allstudent {
	width : 100%;
}

#allstudent tr th{
	text-align: center;
}
.Declared{
background-color: green;
}
.Declare{
background-color: #f85e79;
    padding: 5px 9px;
}
#classname{
height: 130px !important;
}
</style>
</head>
<body>
	<div id="dialog" style="display: none;">
		<div id="dialog1"><label style="font-family:Roboto Medium;color: #000000;font-weight: lighter;font-size: 14px;">Are you sure to Remove?</label></div>
		<div class="col-md-12" id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
			
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
					style="text-align: left; line-height: 35px;">Exam Type<font color="red">*</font></label>
				<div class="col-xs-7">
					<select id="examtype" name="examtype" class="form-control">
						<option value="">----------Select----------</option>
					</select>
				</div>
			</div>
			
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
					style="text-align: left; line-height: 35px;">Class<font color="red">*</font></label>
				<div class="col-xs-7">
					<select id="classname" name="classname" class="form-control" multiple="multiple">
						
					</select>
				</div>
			</div>
			
			<!-- <div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
					style="text-align: left; line-height: 35px;">To Class</label>
				<div class="col-xs-5">
					<select id="toclass" name="toclass" class="form-control">
						<option value="all">ALL</option>
					</select>
				</div>
			</div> -->
			
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
					style="text-align: left; line-height: 35px;">Exam Code<font color="red">*</font></label>
				<div class="col-xs-7">
					<input type="text" class="form-control examname" id="examcode"/>
				</div>
			</div>
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
					style="text-align: left; line-height: 35px;">Exam Name<font color="red">*</font></label>
				<div class="col-xs-7">
					<input type="text" class="form-control" id="examname" />
				</div>
			</div>
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
					style="text-align: left; line-height: 35px;">Start Date<font color="red">*</font></label>
				<div class="col-xs-7">
					<input type="text" class="form-control exam" id="startdate" style="background-color: #fff;"
						readonly/>
				</div>
			</div>

			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-4"
					style="text-align: left; line-height: 35px;">End Date<font color="red">*</font></label>
				<div class="col-xs-7">
					<input type="text" class="form-control exam" id="enddate" style="background-color: #fff;"
						readonly/>
				</div>
			</div>
			
			<div class="form-group clearfix">
				<label for="inputEmail" class="control-label col-xs-8"
					style="text-align: left; line-height: 35px;">Applicable for Periodic Assessment ?</label>
				<div class="col-xs-4" style="padding: 0px;margin-top: 2px;">
					<span style="vertical-align: text-bottom;">Yes </span><span style="vertical-align: middle;"><input type="radio" name="isapplicale" value="Y" class="isapplicableyes"/> </span>&nbsp; <span style="vertical-align: text-bottom;">No </span><span style="vertical-align: middle;"> <input type="radio" name="isapplicale" value="N" class="isapplicableno"/></span>
				</div>
			</div>
				<span class="note" style="top: -20px;font-size: 12px;margin-left: 14px;">(Selected Automatically Based On Exam Type)</span>
			<!-- <div class="form-group clearfix isapplicable">
				<div class="col-xs-12" style="text-align: right;">
					<span>Note Book </span><span><input type="checkbox" name="notebook" value="Y" /> </span>&nbsp; <span>Subject Enrichment </span><span> <input type="checkbox" name="subjectenrichment" value="Y" /></span>
				</div>
			</div> -->
			
			
			
		</div>
		<div id="dialog2">
				<div class="col-md-12" id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Exam Type<font color="red">*</font></label>
						<div class="col-xs-7">
							<select id="examtype1" name="examtype" class="form-control">
								<option value="">----------Select----------</option>
							</select>
						</div>
						<input type="hidden" id="hiddenexamtypeid"/>
					</div>
			
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">From Class<font color="red">*</font></label>
						<div class="col-xs-7">
							<select id="fromclassid" name="classname" class="form-control">
								<option value="all">ALL</option>
							</select>
						</div>
						<input type="hidden" id="hiddenfromclassid"/>
					</div>
				
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Exam Code<font color="red">*</font></label>
						<div class="col-xs-7">
							<input type="text" class="form-control examname" id="examcode1"  />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Exam Name<font color="red">*</font></label>
						<div class="col-xs-7">
							<input type="text" class="form-control" id="examname1" />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">Start Date<font color="red">*</font></label>
						<div class="col-xs-7">
							<input type="text" class="form-control exam" id="startdate1" style="background-color: #fff;"
								readonly/>
						</div>
					</div>

					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
							style="text-align: left; line-height: 35px;">End Date<font color="red">*</font></label>
						<div class="col-xs-7">
							<input type="text" class="form-control exam" id="enddate1" style="background-color: #fff;"
								readonly/>
						</div>
					</div>
				
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-8"
							style="text-align: left; line-height: 35px;">Applicable for Periodic Assessment ?</label>
						<div class="col-xs-4" style="padding: 0px;">
							<span>Yes </span><span><input type="radio" name="hisapplicale" value="Y" class="hisapplicableyes"/> </span>&nbsp; <span>No </span><span> <input type="radio" name="hisapplicale" value="N" class="hisapplicableno"/></span>
						</div>
						<input type="hidden" id="hiddenisapplicable" />
					</div>
					<span class="note" style="top: -20px;font-size: 12px;margin-left: 14px;">(Selected Automatically Based On Exam Type)</span>
			</div>
		</div>
	</div>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Exam
				Setting</span>
		</p>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"><bean:write name="successmessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

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


		<input type="hidden" id="hiddenstartaccyear" value='<logic:present name="startDate"  scope="request"><bean:write name="startDate" /></logic:present>' />
		<input type="hidden" id="hiddenendaccyear" value='<logic:present name="endDate" scope="request"><bean:write name="endDate"  /></logic:present>' />		

		<input type="hidden" id="hiddenstartaccyear" value='<logic:present name="startDate"  scope="request"><bean:write name="startDate" /></logic:present>' />
		<input type="hidden" id="hiddenendaccyear" value='<logic:present name="endDate" scope="request"><bean:write name="endDate"  /></logic:present>' />		
	
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a href="#" style="color: #000000;"><h4 class="panel-title examdetails"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Exam
							Details
						</h4></a>
					

					<div class="navbar-right">
				  		<span style="cursor: pointer;" class=" buttons plus" id="addexam">New</span>
						<span class="buttons" id="back1">Back</span>
					</div>
				</div>
				<input type="hidden" id="hiddenaccyear"
					value='<logic:present name="accyear"  scope="request"><bean:write name="accyear" /></logic:present>' />
				<input type="hidden" id="hiddenloc"
					value='<logic:present name="locid"  scope="request"><bean:write name="locid" /></logic:present>' />

    <input type="hidden" id="historyacademicId" 
    value='<logic:present name="historyacademicId"  scope="request"><bean:write name="historyacademicId" /></logic:present>'/>

	<input type="hidden" id="historylocId" 
    value='<logic:present name="historylocId"  scope="request"><bean:write name="historylocId" /></logic:present>'/>
    			
				<div id="exmdetail" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<div id="inputcolor">
							<div class="col-md-6" id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix" >
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-6">
										<input type="text" readonly="readonly" name="locname" style="text-align: left;" id="locname" class="form-control"
											value='<logic:present name="locName" scope="request"><bean:write name="locName" scope="request"></bean:write></logic:present>' />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-6">
										<select id="mainclass" name="mainclass" class="form-control">
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6" id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix" >
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-6">
										<input type="text" readonly="readonly" name="accyear" style="text-align: left;" id="accyear" class="form-control"
											value='<logic:present name="accyName" scope="request"><bean:write name="accyName" scope="request"></bean:write></logic:present>' />
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div id="markstable"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
