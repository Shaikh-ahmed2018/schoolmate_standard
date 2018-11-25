<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">
<head>
   <script type="text/javascript" src="JS/backOffice/Exam/termBasedMarkSetup.js"></script>
</head>


<style>

.Not{
	background-color:#FF0000;  
	min-width:80px;
	display:inline-block;
	text-align:center;
	color:#fff;
}
.Set{
	background-color:green;
	min-width:80px;
	display:inline-block;
	text-align:center;
	color:#fff;
}
.panel-style{
	width: 90%;
	margin: auto;
}
.allstudent{
	width: 100%;
}
.menu-color{
	color: #000000;
	top: 2px;
}
.clearfixes{
	margin-bottom: 10px;
}
.realign{
	width: 91%;
}
.text-size{
	font-size: 14px !important;
}
.max_markss{
	width: 25%;
}
</style>


<body>
	<form method="POST" action="examCreationPath.html?method=classMaxMarksSetUp" id="backform">
		<input type="hidden" name="myValue" value="" id="backValue"/>
	</form>
	<div class="content" id="div1">
		<div class="" id="div2">
				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Report Type Setup</span>
				</p>
			</div>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
		
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#" style="color: #fff;"><h3 class="panel-title grade" style="color: #000000; line-height: 18px;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Report Type Setup
					</h3></a>
				<div class="navbar-right">
					<span class="buttons" id="save" >Save</span>
					<span class="buttons" id="back1">Back</span>
				</div>
			</div>

			<div id="gradeOne" class="accordion-body collapse in">
				<div class="panel-body">
			
					<div class="col-md-12 realign" id="inputcolor">
						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Branch</label>
								<div class="col-xs-7">
									<input type="text" readonly="readonly" name="location" onkeypress="HideError()" id="location" class="form-control" style="text-align: left;"
									value='<logic:present name="location" scope="request" ><bean:write name="location" scope="request"></bean:write></logic:present>' />
								</div>
								<input type="hidden" id="hiddenaccyear"
									value='<logic:present name="accyearid"  scope="request"><bean:write name="accyearid" /></logic:present>' />
								<input type="hidden" id="hiddenloc"
									value='<logic:present name="locid"  scope="request"><bean:write name="locid" /></logic:present>' />
								<input type="hidden" id="hiddenclassid"
									value='<logic:present name="classId"  scope="request"><bean:write name="classId" /></logic:present>' />
								<input type="hidden" id="setupId" value="<logic:present name="setupId"  scope="request"><bean:write name="setupId" /></logic:present>"/>
								<input type="hidden" id="hreporttype" value="<logic:present name="reporttype"  scope="request"><bean:write name="reporttype" /></logic:present>"/>
								<input type="hidden" id="hmaxlimit" value="<logic:present name="maxlimit"  scope="request"><bean:write name="maxlimit" /></logic:present>"/>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Class</label>
								<div class="col-xs-7">
									<input type="text" readonly="readonly" name="classname" onkeypress="HideError()" id="classname" class="form-control" style="text-align: left;"
										value='<logic:present name="classname" scope="request" ><bean:write name="classname" scope="request"></bean:write></logic:present>' />
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Max.Limit</label>
								<div class="col-xs-7">
									<input type="text" readonly="readonly" name="maxlimit" id="maxlimit" class="form-control" style="text-align: left;"
										value='100' />
								</div>
							</div>
						</div>
						<div class="col-md-6" id="txtstyle">
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Academic Year</label>
								<div class="col-xs-7">
									<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear" class="form-control" style="text-align: left;"
									value='<logic:present name="accyName" scope="request" ><bean:write name="accyName" scope="request"></bean:write></logic:present>' />
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Report Type<font color="red">*</font></label>
								<div class="col-xs-7">
									<select id="reporttype" name="reporttype" class="form-control">
										<option value="">----------Select----------</option>
										<option value="termbased">Term Based</option>
										<option value="academic">Academic Based</option>
									</select>
								</div>
							</div>
						</div>
				 	</div>	
				</div>
			</div>
				
			<div class="panel-group" id="accordion1">
				<div class="panel panel-default panel-style termbased" style="display: none;" >
				    <div class="panel-heading">
				      	<h4 class="panel-title text-size">
				        	<a data-toggle="collapse" data-parent="#accordion2" href="#collapse1">
				         		<span class="glyphicon glyphicon-menu-hamburger menu-color"></span>&nbsp;&nbsp;Term I
				         	</a>
				      	</h4>
				    </div>
				    <div id="collapse1" class="panel-collapse collapse in">
				      	<div class="panel-body">
				      		<div class="col-md-12 clearfixes">
				      			<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-6" style="text-align: center; line-height: 35px;">Periodic Test Mark</label>
										<label for="inputPassword" class="control-label col-xs-6" style="text-align: center; line-height: 35px;">Note Book Mark</label>
				      				</div>
									<div class="form-group clearfix">
										<div class="col-xs-6">
											<input type="text" name="periodictest" id="periodictest" class="form-control periodictest" style="text-align: left;"
												value='10' onkeypress="return isNumberKey(this);"/>
											<input type="hidden" class="termname" value="term1"/>
											<input type="hidden" class="periodictest1" value="0"/>
											<input type="hidden" class="practicalmark" value="0"/>
											<input type="hidden" class="periodicexam1" value=""/>
											<input type="hidden" class="halfyearlyexam" value=""/>
											<input type="hidden" class="halfyearlymark" value="0"/>
										</div>
										<div class="col-xs-6">
											<input type="text" name="notebook" id="notebook" class="form-control notebook" style="text-align: left;"
												value='5' onkeypress="return isNumberKey(this);"/>
										</div>
										<div class="col-xs-6">
											<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;margin-top: 5px;">Periodic Exam</label>
										</div>
				      				</div>
				      				<div class="form-group clearfix">
				      					<div class="col-xs-6">
											<select class="form-control examname" id="periodicexam">
												<option>----------Select----------</option>
											</select>
										</div>
				      				</div>
				      			</div>
				      			<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-6" style="text-align: center; line-height: 35px;">Sub Enrichment Mark</label>
										<label for="inputPassword" class="control-label col-xs-6" style="text-align: center; line-height: 35px;">Half Yearly Mark</label>
				      				</div>
				      				<div class="form-group clearfix">
										<div class="col-xs-6">
											<input type="text" name="subenrichment" id="subenrichment" class="form-control subenrichment" style="text-align: left;"
												value='5' onkeypress="return isNumberKey(this);"/>
										</div>
										<div class="col-xs-6">
											<input type="text" name="halfyearly" readonly="readonly" id="halfyearly" class="form-control halfyearly" style="text-align: left;"
												value='80' onkeypress="return isNumberKey(this);"/>
										</div>
										<div class="col-xs-6">
											<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;"></label>
										</div>
										<div class="col-xs-6">
											<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;margin-top: 5px;">Half Yearly Exam</label>
										</div>
				      				</div>
				      				<div class="form-group clearfix">
				      					<div class="col-xs-6">
											<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;"></label>
										</div>
				      					<div class="col-xs-6">
											<select class="form-control halflyexam commonexam" id="halflyexam">
												<option>----------Select----------</option>
											</select>
										</div>
				      				</div>
				      			</div>
							</div>
				    	</div>
				    </div>
				</div>
				
				<div class="panel panel-default panel-style termbased" style="display: none;" >
			   		<div class="panel-heading">
				        <h4 class="panel-title text-size">
					         <a data-toggle="collapse" data-parent="#accordion2" href="#collapse2">
					         	<span class="glyphicon glyphicon-menu-hamburger menu-color"></span>&nbsp;&nbsp;Term II
					      	 </a>
				      	</h4>
			    	</div>
			    	<div id="collapse2" class="panel-collapse collapse in">
			      		<div class="panel-body"> 
			      			<div class="col-md-12 clearfixes">
				      			<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-6" style="text-align: center; line-height: 35px;">Periodic Test Mark</label>
										<label for="inputPassword" class="control-label col-xs-6" style="text-align: center; line-height: 35px;">Note Book Mark</label>
				      				</div>
									<div class="form-group clearfix">
										<div class="col-xs-6">
											<input type="text" name="periodictest1" id="periodictest1" class="form-control periodictest" style="text-align: left;"
												value='10' onkeypress="return isNumberKey(this);"/>
											<input type="hidden" class="termname" value="term2"/>
											<input type="hidden" class="periodictest1" value="0"/>
											<input type="hidden" class="practicalmark" value="0"/>
											<input type="hidden" class="periodicexam1" value=""/>
											<input type="hidden" class="halfyearlyexam" value=""/>
											<input type="hidden" class="halfyearlymark" value="0"/>
										</div>
										<div class="col-xs-6">
											<input type="text" name="notebook1" id="notebook1" class="form-control notebook" style="text-align: left;"
												value='5' onkeypress="return isNumberKey(this);"/>
										</div>
										<div class="col-xs-6">
											<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;margin-top: 5px;">Periodic Exam</label>
										</div>
				      				</div>
				      				<div class="form-group clearfix">
				      					<div class="col-xs-6">
											<select class="form-control examname" id="periodicexam1">
												<option>----------Select----------</option>
											</select>
										</div>
				      				</div>
				      			</div>
				      			<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-6" style="text-align: center; line-height: 35px;">Sub Enrichment Mark</label>
										<label for="inputPassword" class="control-label col-xs-6" style="text-align: center; line-height: 35px;">Yearly Mark</label>
				      				</div>
				      				<div class="form-group clearfix">
										<div class="col-xs-6">
											<input type="text" name="subenrichment1" id="subenrichment1" class="form-control subenrichment" style="text-align: left;"
												value='5' onkeypress="return isNumberKey(this);"/>
										</div>
										<div class="col-xs-6">
											<input type="text" name="halfyearly1" readonly="readonly" id="halfyearly1" class="form-control halfyearly" style="text-align: left;"
												value='80' onkeypress="return isNumberKey(this);"/>
										</div>
										<div class="col-xs-6">
											<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;"></label>
										</div>
										<div class="col-xs-6">
											<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px; margin-top: 5px;">Yearly Exam</label>
										</div>
				      				</div>
				      				<div class="form-group clearfix">
				      					<div class="col-xs-6">
											<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;"></label>
										</div>
				      					<div class="col-xs-6">
											<select class="form-control yearlyexam commonexam" id="halflyexam1">
												<option>----------Select----------</option>
											</select>
										</div>
				      				</div>
				      			</div>
							</div>
						</div>
			    	</div>
			  	</div>
			  	
				<div class="panel panel-default panel-style academicyearbased" style="display: none;" >
			   		<div class="panel-heading">
				        <h4 class="panel-title text-size">
					         <a data-toggle="collapse" data-parent="#accordion2" href="#collapse3">
					         	<span class="glyphicon glyphicon-menu-hamburger menu-color"></span>&nbsp;&nbsp;Academic Year
					      	 </a>
				      	</h4>
			    	</div>
			    	<div id="collapse3" class="panel-collapse collapse in">
			      		<div class="panel-body"> 
			      			<div class="col-md-12 clearfixes">
				      			<div class="col-md-3" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;">Periodic Test I Mark</label>
				      				</div>
									<div class="form-group clearfix">
										<div class="col-xs-12">
											<input type="text" name="periodictest" id="aperiodictest" class="form-control periodictest" style="text-align: left;"
												value='10' onkeypress="return isNumberKey(this);"/>
											<input type="hidden" class="termname" value="academic"/>
										</div>
				      				</div>
				      				<div class="form-group clearfix">
				      					<div class="col-xs-12">
											<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;margin-top: 5px;">Periodic Exam I</label>
										</div>
				      					<div class="col-xs-12">
											<select class="form-control examname periodicexam" id="aperiodicexam">
												<option>----------Select----------</option>
											</select>
										</div>
				      				</div>
				      			</div>
				      			<div class="col-md-3" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;">Periodic Test II Mark</label>
				      				</div>
									<div class="form-group clearfix">
										<div class="col-xs-12">
											<input type="text" name="periodictest1" id="aperiodictest1" class="form-control periodictest1" style="text-align: left;"
												value='10' onkeypress="return isNumberKey(this);"/>
										</div>
										<div class="col-xs-12">
											<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;margin-top: 5px;">Periodic Exam II</label>
										</div>
				      				</div>
				      				<div class="form-group clearfix">
				      					<div class="col-xs-12">
											<select class="form-control examname periodicexam1" id="aperiodicexam1">
												<option>----------Select----------</option>
											</select>
										</div>
				      				</div>
				      			</div>
				      			<div class="col-md-3" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;">Half Yearly Exam Mark</label>
				      				</div>
									<div class="form-group clearfix">
										<div class="col-xs-12">
											<input type="text" name="halfyearlymark" id="halfyearlymark" class="form-control halfyearlymark" style="text-align: left;"
												value='30' onkeypress="return isNumberKey(this);"/>
										</div>
										<div class="col-xs-12">
											<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;margin-top: 5px;">Half Yearly Exam</label>
										</div>
				      				</div>
				      				<div class="form-group clearfix">
				      					<div class="col-xs-12">
											<select class="form-control halflyexam halfyearlyexam" id="ahalflyexam">
												<option>----------Select----------</option>
											</select>
										</div>
				      				</div>
				      			</div>
				      			<div class="col-md-3" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;">Yearly Exam Mark</label>
				      				</div>
									<div class="form-group clearfix">
										<div class="col-xs-12">
											<div class="col-xs-4" style="padding: 0px 2px 0px 0px;margin-top: -18px;">
												<span style="font-size: 10px;margin-left: 10px;">Non-PS<font style="font-size: 10px;color: red;">*</font></span>
												<input type="text" name="nonpractialmark" id="nonpractialmark" class="form-control nonpractialmark" style="text-align: left;"
													value="0" onkeypress="return isNumberKey(this);"/>
											</div>
											<div class="col-xs-4" style="padding: 0px 2px 0px 0px;margin-top: -18px;">
												<span style="font-size: 10px;margin-left: 16px;">PS-T<font style="font-size: 10px;color: red;">*</font></span>
												<input type="text" name="halfyearly" id="yearlytheorymarks" class="form-control halfyearly" style="text-align: left;"
													value="0" onkeypress="return isNumberKey(this);"/>
											</div>
											<div class="col-xs-4" style="padding: 0px 0px 0px 2px;margin-top: -18px;">
												<span style="font-size: 10px;margin-left: 16px;">PS-P<font style="font-size: 10px;color: red;">*</font></span>
												<input type="text" name="practicalmark" id="practicalmark" class="form-control practicalmark" style="text-align: left;"
													value="0" onkeypress="return isNumberKey(this);"/>
											</div>
										</div>
										<div class="col-xs-12">
											<label for="inputPassword" class="control-label col-xs-12" style="text-align: center; line-height: 35px;margin-top: 5px;">Yearly Exam</label>
										</div>
				      				</div>
				      				<div class="form-group clearfix">
				      					<div class="col-xs-12">
											<select class="form-control yearlyexam commonexam" id="ayearlyexam">
												<option>----------Select----------</option>
											</select>
										</div>
				      				</div>
				      			</div>
							</div>
							<div class="pagenote" style="margin-left: 12px;">
								<span class="note">Note :- </span>
								<div class="note">Non-PS -> Non Practical Subject Mark.</div>
								<div><span class="note">PS-T -> Practical Subject Theory Mark.</span></div>
								<div><span class="note">PS-P -> Practical Subject Practical Mark.</span></div>
							</div>
						</div>
			    	</div>
			  	</div>
			</div>
		</div>
	</div>
</body>
</html>
