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

<title>eCampus Pro</title>
<script type="text/javascript" src="JS/backOffice/Exam/getClassWiseSubject.js"></script>
</head>


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
.Pending{
background-color:#FF0000;
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
.Completed{
background-color:green;
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
#allstudent th:nth-child(1){
width:100px;
}
#allstudent td{
text-align: center;
}
.form-group{
margin-bottom: 10px;}

.scrollbar{

	overflow-y:scroll; 
	height: 250px;
}

</style>


<body>
<div id="dialog"></div>

	<div class="content" id="div1">
		<div class="" id="div2">
			<p style="">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Marks Entry - Subject Wise</span>
			</p>
		</div>
		
		<logic:present name="success" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
								name="success" scope="request" />
					</span></a>
				</div>
			</div>
		</logic:present>
		<logic:present name="error" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="error" scope="request" />
					</span></a>
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
		
		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#" style="color: #fff;"><h3
						class="panel-title grade" style="color: #000000; line-height: 18px;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Subject Wise Marks
						List
	 				</h3>
					</a>
					<div class="navbar-right">
						<span class="buttons" id="back1">Back</span>
					</div>
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
    
    <input type="hidden" id="historyacayear"
    value='<logic:present name="historyacayear" scope="request"><bean:write name="historyacayear"></bean:write></logic:present>'/>
    
    <input type="hidden" id="historylocation"
    value='<logic:present name="historylocation" scope="request"><bean:write name="historylocation"></bean:write></logic:present>'/>			

			<div id="gradeOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					
						<div class='clearfix' id="inputcolor">
		<div class="col-md-12" style="margin-top: 10px;">
			<div class="col-md-6"  id="txtstyle" style="font-size: 13px; color: #000;">
			
			
			<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: left; line-height: 35px;">Branch</label>
									<div class="col-xs-6">
										<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear"
											class="form-control" value='<logic:present name="currentlocation" scope="request" ><bean:write name="currentlocation" ></bean:write></logic:present>'/>
									</div>
									<input type="hidden" name="hiddenlocid" id="hiddenlocid" value='<logic:present name="locid" scope="request"><bean:write name="locid"></bean:write></logic:present>'/>
									<input type="hidden" name="hiddenspec" id="hiddenspec" value='<logic:present name="specId" scope="request"><bean:write name="specId"></bean:write></logic:present>'/>
								</div>
								
			</div>
			<div class="col-md-6"  id="txtstyle" style="font-size: 13px; color: #000;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: left; line-height: 35px;">Academic Year</label>
									<div class="col-xs-6">
										<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear"
											class="form-control" value='<logic:present name="accyName" scope="request" ><bean:write name="accyName" ></bean:write></logic:present>'/>
									</div>
									<input type="hidden" name="hiddenaccyid" id="hiddenaccyid" value='<logic:present name="accyear" scope="request"><bean:write name="accyear"></bean:write></logic:present>'/>
								</div>
			</div>
			
			</div>
			<div class="col-md-12">
				<div class="col-md-6"  id="txtstyle"
								style="font-size: 13px; color: #000;">
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Class</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="classid" onkeypress="HideError()" id="classid" class="form-control" value='<logic:present name="classname" scope="request" ><bean:write name="classname"></bean:write></logic:present>'/>
						</div>
						<input type="hidden" name="hiddenclassid" id="hiddenclassid" value='<logic:present name="classId" scope="request"><bean:write name="classId"></bean:write></logic:present>'/>
				</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Exam Code</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="examCode" onkeypress="HideError()" id="examCode" class="form-control" value='<logic:present name="examcode"><bean:write name="examcode"></bean:write></logic:present>'/>
						</div>
						<input type="hidden" name="hiddenexamid" id="hiddenexamid" value='<logic:present name="examid" scope="request"><bean:write name="examid"></bean:write></logic:present>'/>
				</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Start Date</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="startdate" onkeypress="HideError()" id="startdate" class="form-control" value='<logic:present name="startdate" scope="request"><bean:write name="startdate"></bean:write></logic:present>'/>
						</div>
				</div>
			</div>	
			
			<div class="col-md-6"  id="txtstyle"
								style="font-size: 13px; color: #000;">
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Division</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="sectionid" onkeypress="HideError()" id="sectionid" class="form-control" value='<logic:present name="sectionname" scope="request" ><bean:write name="sectionname" ></bean:write></logic:present>'/>
						</div>
						<input type="hidden" name="hiddensectionid" id="hiddensectionid" value='<logic:present name="sectionId" scope="request"><bean:write name="sectionId"></bean:write></logic:present>'/>
				</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Exam Name</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="examName" onkeypress="HideError()" id="examName" class="form-control" value='<logic:present name="examname" scope="request"><bean:write name="examname"></bean:write></logic:present>'/>
						</div>
				</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">End Date</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="enddate" onkeypress="HideError()" id="enddate" class="form-control" value='<logic:present name="enddate" scope="request"><bean:write name="enddate"></bean:write></logic:present>'/>
						</div>
				</div>
			</div>	
			</div>	
		</div>
				<!-- <div id = "markstable"></div> -->
					<div class="form-group scrollbar">
						<table style="background: #fff;" class="table" id="allstudent">
						<thead>
						<tr>
						<th style="font-size: 15px; text-align: center;" >Sl.No</th>
						<th style="font-size: 15px; text-align: left;">Subject Code</th>
						<th style="font-size: 15px; text-align: left;">Subject Name</th>
						
						<th style="font-size:15px; text-align: center;">Status</th>
					   </tr>
					   </thead>
					  
					   <logic:present name="subjectList" scope="request">
					    <tbody>
					   	<logic:iterate id="subjectList" name="subjectList" scope="request">
					   		<tr>
					   		<td><bean:write name="subjectList" property="sno1" /></td>
					   		<td style="text-align: left;" class="subId" id="<bean:write name="subjectList" property="subId"/>"><bean:write name="subjectList" property="subCode"/></td>
					   		<td style="text-align: left;"><bean:write name="subjectList" property="subjectName"/></td>
					   		
							<td><span class="<bean:write name="subjectList" property="status"/>"><bean:write name="subjectList" property="status"/></span></td>
					   		</tr>
					   	</logic:iterate>
					   	</tbody>
					   </logic:present>
					  </table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
