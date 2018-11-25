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


<script type="text/javascript" src="JS/backOffice/Exam/subjectWiseMarksList.js"></script>
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
text-align: center;
width:100px;
}
</style>


<body>
<div id="dialog">

</div>

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
					</h3></a>
					<div class="navbar-right" >
									<span id="back1" class="buttons">Back</a></span>
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
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
						
					<div class="clearfix" id="inputcolor">
					<div class="col-md-12" >
						<div class="col-md-6"  id="txtstyle" style="font-size: 13px; color: #000;">
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: left; line-height: 35px;">Branch</label>
								<div class="col-xs-6">
								<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear"
											class="form-control" value='<logic:present name="currentlocation" scope="request"><bean:write name="currentlocation" ></bean:write></logic:present>' />
								</div>
								<input type="hidden" name="hiddenlocid" id="hiddenlocid" value='<logic:present name="locId" scope="request"><bean:write name="locId"></bean:write></logic:present>'/>
						
							</div>
								
						</div>
						<div class="col-md-6"  id="txtstyle" style="font-size: 13px; color: #000;">
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: left; line-height: 35px;">Academic Year</label>
								<div class="col-xs-6">
								<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear"
											class="form-control" value='<logic:present name="accyName" scope="request"><bean:write name="accyName" ></bean:write></logic:present>' />
								</div>
								<input type="hidden" name="hiddenaccyid" id="hiddenaccyid" value='<logic:present name="accyId" scope="request"><bean:write name="accyId"></bean:write></logic:present>'/>
						
							</div>
						</div>
					</div>
						
					</div>
						
				<!-- <div id = "markstable"></div> -->
						<div class="form-group">
						<table style="background: #fff;" class="table" id="allstudent">
						<thead>
						<tr>
						<th style="font-size: 13px; text-align: center;">Sl.No</th>
						<th style="font-size: 13px; text-align: left;">Class</th>
						<th style="font-size: 13px;" >Exam Code</th>
						<th style="font-size: 13px;">Exam Name</th>
						<th style="font-size: 13px; text-align: center;">Start Date</th>
						<th style="font-size:13px; text-align: center;">End Date
						</th>
						<th style="font-size:15px; text-align: center;">Status</th>
					   </tr>
					   </thead>
					   <tbody>
					   <logic:present name="subjectmarksList" scope="request">
						   <logic:iterate id="subjectmarksList" name="subjectmarksList" scope="request">
						   		<tr>
						   		<td class="sno" style="text-align: center;"><bean:write name="subjectmarksList" property="count"/></td>
						   		<td class="classid" id="<bean:write name="subjectmarksList" property="classid"/>"><bean:write name="subjectmarksList" property="classname" /></td>
						   		<td class="examCode" id="<bean:write name="subjectmarksList" property="examid" />"><bean:write name="subjectmarksList" property="examCode" /></td>
						   		<td class=""><bean:write name="subjectmarksList" property="examName"/></td>
						   		<td class="" style="text-align: center;"><bean:write name="subjectmarksList" property="startDate"/></td>
						   		<td class="" style="text-align: center;"><bean:write name="subjectmarksList" property="endDate"/></td>
								<td style="text-align: center;"><span class="<bean:write name="subjectmarksList" property="status"/>"><bean:write name="subjectmarksList" property="status"/></span></td>
						   		</tr>
						   </logic:iterate>
					   </logic:present>
					   </tbody>
					  </table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
