<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">
<head>
   <script type="text/javascript" src="JS/backOffice/Exam/DisSubMaxMarks.js"></script>
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
	width: 80%;
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
						id="pageHeading">Maximum Marks Setup</span>
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
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Subject List
					</h3></a>
				<div class="navbar-right">
					<span class="buttons" id="save">Save</span>
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
								<input type="hidden" id="hiddenmarkid"
									value='<logic:present name="markid"  scope="request"><bean:write name="markid" /></logic:present>' />
								<input type="hidden" id="hiddenperiodicmark"
									value='<logic:present name="periodicmark"  scope="request"><bean:write name="periodicmark" /></logic:present>' />
								<input type="hidden" id="hiddennotebookmark"
									value='<logic:present name="notemark"  scope="request"><bean:write name="notemark" /></logic:present>' />
								<input type="hidden" id="hiddensubenrichmark"
									value='<logic:present name="subenrichmark"  scope="request"><bean:write name="subenrichmark" /></logic:present>' />
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Class</label>
								<div class="col-xs-7">
									<input type="text" readonly="readonly" name="classname" onkeypress="HideError()" id="classname" class="form-control" style="text-align: left;"
										value='<logic:present name="classname" scope="request" ><bean:write name="classname" scope="request"></bean:write></logic:present>' />
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Periodic Max. Marks<font color="red">*</font></label>
								<div class="col-xs-7">
									<select id="periodicexam" name="periodicexam" class="form-control">
										<option value="">----------Select----------</option>
										<option value="10">10</option>
										<option value="20">20</option>
										<option value="30">30</option>
										<option value="40">40</option>
										<option value="50">50</option>
									</select>
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
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Max Limit</label>
								<div class="col-xs-7">
									<input type="text" readonly="readonly" name="maxlimit" id="maxlimit" class="form-control" style="text-align: left;"
									value='100' />
									<!-- <select id="maxlimit" name="maxlimit" class="form-control">
										<option value="100">100</option>
									</select> -->
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Notebook Max. Marks<font color="red">*</font></label>
								<div class="col-xs-7">
									<select id="notebook" name="notebook" class="form-control">
										<option value="">----------Select----------</option>
										<option value="5">5</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="25">25</option>
										<option value="30">30</option>
										<option value="35">35</option>
										<option value="40">40</option>
										<option value="45">45</option>
										<option value="50">50</option>
									</select>
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">SubEnrich Max. Marks<font color="red">*</font></label>
								<div class="col-xs-7">
									<select id="subenrichment" name="subenrichment" class="form-control">
										<option value="">----------Select----------</option>
										<option value="5">5</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="25">25</option>
										<option value="30">30</option>
										<option value="35">35</option>
										<option value="40">40</option>
										<option value="45">45</option>
										<option value="50">50</option>
									</select>
								</div>
							</div>
						</div>
				 	</div>	
				</div>
			</div>
				
			<div class="panel-group" id="accordion3">
				<div class="panel panel-default panel-style" >
				    <div class="panel-heading">
				      <h4 class="panel-title text-size">
				        <a data-toggle="collapse" data-parent="#accordion3" href="#collapse1">
				         <span class="glyphicon glyphicon-menu-hamburger menu-color"></span>&nbsp;&nbsp;Non Practical/ Non Project Subject</a>
				      </h4>
				    </div>
				    <div id="collapse1" class="panel-collapse collapse in">
				      	<div class="panel-body">
				      		<div class="col-md-12 clearfixes">
				      			<div id="nonpracticalsubject"></div>
							</div>
				    	</div>
				    </div>
				</div>
				
				<div class="panel panel-default panel-style">
			   		<div class="panel-heading">
				        <h4 class="panel-title text-size">
					         <a data-toggle="collapse" data-parent="#accordion3" href="#collapse2">
					         	<span class="glyphicon glyphicon-menu-hamburger menu-color"></span>&nbsp;&nbsp;Subject with Practical/ Project
					      	 </a>
				      	</h4>
			    	</div>
			    	<div id="collapse2" class="panel-collapse collapse in">
			      		<div class="panel-body"> 
			      			<div class="col-md-12 clearfixes">
			      				<div id="practicalsubject"></div>
							</div>
						</div>
			    	</div>
			  	</div>
			</div>
		</div>
	</div>
</body>
</html>
