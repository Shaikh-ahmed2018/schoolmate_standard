<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="JS/backOffice/Admin/StaffAttendance.js"></script>



<style>
#allstudent th,.allstudemt th{
background: #f5f5f5 !important;
}
#editDesignationId:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
.hiddenclass{

	display: none;
}
.sno{
text-align: center;
}
.buttons{

vertical-align: 0px;

}
.navbar-right span{
vertical-align: 21px;
}
</style>

</head>

<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
	<div class="col-md-10" id="div-main"
		style="font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
<div class="searchWrap" style="padding: 14px 5px 0 0;">
		<div class="col-md-7" id="div2">

			<p>
				<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Staff Attendance</span>
			</p>
		</div>
		<div class="input-group col-md-5">
		
		
			
			<label  class="form-control" style=" width: 20% !important; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Department</label> 
		
							
			<select id="department" style="bottom: -3px;width: 30%;margin-left: 3%;" class="form-control">
			
			<option value="all">All</option>
			<logic:iterate id="department" name="departmentList">
			
			<option value="<bean:write name="department" property="depId"/>"><bean:write name="department" property="depName"/></option>
			
			</logic:iterate>
			
			</select>
			
			<label style=" width: 13%; border: none;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;"
				class="form-control">Date</label>
				
			<input type="text" name="year" style="bottom: -3px;width: 30%;"
							id="date" maxlength="25" class="form-control"  readonly="readonly" value="<logic:present name='TodayDate'><bean:write name='TodayDate'/></logic:present>" />
			
			<span class="input-group-btn" style="right: 18px;">
					<button class="btn btn-default" type="button" id="search">
						<i class="fa fa-search"></i>
					</button>
				</span>
<input type="hidden" id="hdeptId" value="<logic:present name="departmentname"><bean:write name="departmentname"/></logic:present>"/>

		</div>
</div>
			<logic:present name="message" scope="request">
				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
									name="message" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>
			<logic:present name="error" scope="request">
				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
									name="error" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>
			
			<div class="successmessagediv" style="display: none;">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
			
			<div class="errormessagediv" style="display: none;">
						<div class="message-item">
							<!-- Warning -->
							<a href="#" class="msg-warning bg-msg-warning"><span
								class="validateTips"></span></a>
						</div>
		</div>
			
		</center>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Staff
						Attendance

					</h3></a>
				<div class="navbar-right" >

					<span class="buttons" id="save2">Save</span>
					
					 	 <span id="back1" class="buttons" > Back </a></span>
					 

				</div>
			 
			</div>
		
		 <input type="hidden" id="historystatus" 
		value="<logic:present name="historystatus" scope="request"><bean:write name="historystatus"/></logic:present>" />
			
       <input type="hidden" id="historylocId" 
		value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>" />
				
		<input type="hidden" id="historyacademicId" 
		value="<logic:present name="historyacademicId" scope="request"><bean:write name="historyacademicId"/></logic:present>" />
		
		<input type="hidden" id="historystartdate" 
		value="<logic:present name="historystartdate" scope="request"><bean:write name="historystartdate"/></logic:present>" />				
		
		<input type="hidden" id="historyenddate" 
		value="<logic:present name="historyenddate" scope="request"><bean:write name="historyenddate"/></logic:present>" />
			
		<input type="hidden" id="historyback" 
		value="<logic:present name="historyback" scope="request"><bean:write name="historyback"/></logic:present>" />
			
			<input type="hidden" name="tatt" class="tatt" id="tatt" value=""></input>
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"	style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<display:table class="table" id="allstudent" style="font-family: Roboto,sans-serif;color: #897676;"
						name="requestScope.attendanceList" requestURI="/staffattendancepath.html?method=staffattendaceUpload">

						<display:column property="teacherId" class="hiddenclass" headerClass="hiddenclass"></display:column>
						
						<display:column property="count" 
							title="S.No" media="html" class="sno"></display:column>
						
						<display:column property="regid" 
							title="Staff Id" media="html"></display:column>

						<display:column property="teacherName" 
							title="Staff Name" media="html"></display:column>
							
						<display:column property="designation" 
							title="Designation" media="html"></display:column>
							
						<display:column property="department" 
							title="Department" media="html"></display:column>

						<display:column  
							title="Actual Completion Date" media="html">
							<select name="status" class="form-control statusclass" id="${allstudent.teacherId}status">
									<option value="${allstudent.status}">${allstudent.status}</option>
							</select></display:column>
					</display:table>
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks'></div>
				</div>
				<br>
			</div>
		</div>

	</div>


</body>
</html>