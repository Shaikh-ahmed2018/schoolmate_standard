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

<script type="text/javascript" src="JS/backOffice/Exam/savesubjectwise.js"></script>
<script>
function CheckIsNumeric(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode > 31 && charCode != 46 &&(charCode < 48 || charCode > 57)) {
        /* document.getElementById("maxmarks").style.backgroundColor = "#FFB2B2"; */
        return false;
    }
    else {
        /* document.getElementById("maxmarks").style.backgroundColor = "#B2D8B2"; */
        return true;
    }
}
</script>
</head>


<style>

/* #allstudent tbody tr td{
width :10%;
} */
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
background-color:rgba(0, 158, 0, 0.66);
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
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
<div id="loader" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="content" id="div1">
		<div class="" id="div2">
			<p style="">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Marks Entry - Subject Wise</span>
			</p>
		</div>
		
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
		

		
		
		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#" style="color: #fff;"><h3
						class="panel-title grade" style="color: #000000; line-height: 18px;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Subject Wise Marks
						List
					</h3></a>
					<div class="navbar-right">
         				  <span class="buttons" id="save">Save</span>
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
	<div class='clearfix' id='inputcolor'>
			<div class="col-md-12">
				<div class="col-md-6"  id="txtstyle"
								style="font-size: 13px; color: #000;">
				
				<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: left; line-height: 35px;">Branch</label>
									<div class="col-xs-6">
										<input type="text" readonly="readonly" name="location" onkeypress="HideError()" id="location"
											class="form-control" value='<logic:present name="currentlocation" scope="request" ><bean:write name="currentlocation" ></bean:write></logic:present>'/>
									</div>
									<input type="hidden" name="hiddenlocid" id="hiddenlocid" value='<logic:present name="locationid" scope="request"><bean:write name="locationid"></bean:write></logic:present>'/>
								</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Subject Code</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="subcode" onkeypress="HideError()" id="subcode" class="form-control" value='<logic:present name="subjectCode" scope="request" ><bean:write name="subjectCode"></bean:write></logic:present>'/>
						</div>
						<input type="hidden" name="hiddensubid" id="hiddensubid" value='<logic:present name="subid" scope="request"><bean:write name="subid"></bean:write></logic:present>'/>
				</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Class</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="classid" onkeypress="HideError()" id="classid" class="form-control" value='<logic:present name="classname" scope="request" ><bean:write name="classname"></bean:write></logic:present>'/>
						</div>
						<input type="hidden" name="classhiddenid" id="classhiddenid" value='<logic:present name="classId" scope="request"><bean:write name="classId"></bean:write></logic:present>'/>
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
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: left; line-height: 35px;">Academic Year</label>
									<div class="col-xs-6">
										<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear"
											class="form-control" value='<logic:present name="accyName" scope="request" ><bean:write name="accyName" ></bean:write></logic:present>'/>
									</div>
									<input type="hidden" name="hiddenaccyid" id="hiddenaccyid" value='<logic:present name="accyear" scope="request"><bean:write name="accyear"></bean:write></logic:present>'/>
								</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: left; line-height: 35px;">Subject Name</label>
						<div class="col-xs-6">
						 <input type="text" readonly="readonly" name="subjectname" onkeypress="HideError()" id="subjectname" class="form-control" value='<logic:present name="subjectName" scope="request" ><bean:write name="subjectName" ></bean:write></logic:present>'/>
						</div>
						
				</div>				
				
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
					<input type="hidden" id="tablesize" value='<logic:present name="size" scope="request"><bean:write name="size"/></logic:present>'/>
					<div class="form-group scrollbar">
						<table style="background: #fff;" class="table" id="allstudent">
						<thead><tr style="background: #f5f5f5;">
						<th style="font-size: 15px;text-align: center;vertical-align:  middle;" rowspan="2">Sl.No</th>
						<th style="font-size: 15px;text-align: center;vertical-align:  middle;" rowspan="2">Roll No</th>
						<th style="font-size: 15px;text-align: center;vertical-align:  middle;" rowspan="2">Admission No</th>
						<th style="font-size: 15px;text-align: center;vertical-align:  middle;" rowspan="2">Student Name</th>
						<th style="font-size: 15px;text-align: center;vertical-align:  middle;" rowspan="2">Attendance</th>
						<th class="sticky dnotebook" style="font-size:15px; text-align: center;" colspan="2">Note Book</th>
						<th class="sticky dsubjectenrichment" style="font-size:15px; text-align: center;" colspan="2">Subject Enrichment</th>
						<th class="sticky dperiodictest" colspan="2" style="text-align: center;">Periodic Test Marks</th>
						<th class="sticky dhalfyearly" style="font-size:15px; text-align: center;" colspan="2">Half Yearly Marks</th>
						<!-- <th style="font-size:15px; text-align: center;">Scored Marks</th> -->
					   </tr>
					    <tr style="background: #f5f5f5;">
							<th class="sticky dnotebook" style="text-align: center;">Max.Marks</th>
							<th class="sticky dnotebook" style="text-align: center;">Scored Marks</th>
							<th class="sticky dsubjectenrichment" style="text-align: center;">Max.Marks</th>
							<th class="sticky dsubjectenrichment" style="text-align: center;">Scored Marks</th>
							<th class="sticky dperiodictest" style="text-align: center;width: 200px;">Max.Marks</th>
							<th class="sticky dperiodictest" style="text-align: center;width: 200px;">Scored Marks</th>
							<th class="sticky dhalfyearly" style="text-align: center;">Max.Marks</th>
							<th class="sticky dhalfyearly" style="text-align: center;">Scored Marks</th>
					   </tr>
					   </thead>
					   <tbody>
					   <logic:present name="studentlist" scope="request">
					   	<logic:iterate id="studentlist" name="studentlist" scope="request">
					   		<tr id='rowId<bean:write name="studentlist" property="rollno"/>'>
					   		<td><bean:write name="studentlist" property="count"/></td>
					   		<td style="text-align: left;"><bean:write name="studentlist" property="rollno" /><input type="hidden" class="subjectid" value='<bean:write name="studentlist"  property="primaryid"/>' /> </td>
					   		<td style="text-align: left;" class="stuid <bean:write name="studentlist" property="studentid"/> <bean:write name="studentlist" property="studenprimid"/>"><bean:write name="studentlist" property="addmisiionno"/></td>
					   		<td style="text-align: left;"><bean:write name="studentlist" property="studentname"/></td>
							<td><select class="statusvalue <bean:write name="studentlist" property="attendace"/>" id="<bean:write name="studentlist" property="sno1"/>" style="background-color:aqua;"><option value="Present">Present</option>
								<option value="Absent">Absent</option>
							</select></td>
							<td class="dnotebook" align="center"><input type="text" class="maxnotebook" onkeypress ="return CheckIsNumeric(event);" style="background-color: turquoise; width: 85%;" value="<bean:write name="studentlist" property="max_notebook_marks"/>"/></td>
							<td class="dnotebook" align="center"><input type="text"  class="notebook" onkeypress ="return CheckIsNumeric(event);" style="background-color: turquoise; width: 85%;" value="<bean:write name="studentlist" property="notebooks"/>"/></td>
							<td class="dsubjectenrichment" align="center"><input type="text" class="maxsubjectenrichment" onkeypress ="return CheckIsNumeric(event);"style="background-color: turquoise; width: 85%;" value="<bean:write name="studentlist" property="max_subenrich_marks"/>"/></td>
							<td class="dsubjectenrichment" align="center"><input type="text" class="subjectenrichment" onkeypress ="return CheckIsNumeric(event);" style="background-color: turquoise; width: 85%;" value="<bean:write name="studentlist" property="subjectenrichmarks"/>"/></td>
							<td class="dperiodictest" align="center"><input type="text" class="maxperiodic"	onkeypress ="return CheckIsNumeric(event);" style="background-color: turquoise; width: 85%;" value="<bean:write name="studentlist" property="maxperiodicmark"/>"/></td>
							<td class="dperiodictest" align="center"><input type="text" class="scoredperiodic" onkeypress ="return CheckIsNumeric(event);"  style="background-color: turquoise; width: 85%;" value="<bean:write name="studentlist" property="pertest"/>"/></td>
							<td class="dhalfyearly" align="center"><input type="text" class="maxhalfyearly" onkeypress ="return CheckIsNumeric(event);" style="background-color: turquoise; width: 85%;" value="<bean:write name="studentlist" property="tot_marks"/>"/></td>
							<td class="dhalfyearly" align="center"><input type="text" class="scoredhalfyearly" onkeypress ="return CheckIsNumeric(event);" style="background-color: turquoise; width: 85%;" value="<bean:write name="studentlist" property="scoredmarks"/>"/></td>
							<%-- <td class="marks"><input type="text" onkeypress ="return CheckIsNumeric(event);" class="scoredmarks" style="background-color: turquoise;" value="<bean:write name="studentlist" property="scoredmarks"/>"/></td> --%>
							</tr>
					   	</logic:iterate>
					   </logic:present>
					  </tbody>
					  </table>
					</div>
				</div>
				<input type="hidden" id="hiddenexamtypeprefix" value="<logic:present name="examtypeprefix" scope="request"><bean:write name="examtypeprefix"></bean:write></logic:present>"/>
				<input type="hidden" id="hiddenspecialization" value="<logic:present name="specid" scope="request"><bean:write name="specid"></bean:write></logic:present>"/>
			</div>
		</div>
	</div>
	
</body>
</html>
