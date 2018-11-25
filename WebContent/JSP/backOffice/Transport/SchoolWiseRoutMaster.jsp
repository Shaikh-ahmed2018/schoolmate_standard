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

<script type="text/javascript" src="JS/backOffice/Transport/SchoolWiseRoutMaster.js"></script>
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
#allstudent th:nth-child(2){
width:250px;
}
#allstudent th:nth-child(3),td:nth-child(3){
   width:450px;
  text-align: center;
}
#markstable{
    font-family: Roboto,sans-serif;
    font-size: 13px;
    color: #000;
}

#allstudent th {
    
    border-bottom: 1px solid #ddd;
    border-top: 1px solid #ddd;
    border-right: 1px solid #ddd;
    font-family: Roboto Medium;
    font-size: 14px;
    font-weight: lighter;
    border-left: 1px solid #ddd;
    color: #000000 !important;
}

.Not{
background-color:#FF0000;  
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
.Set{
background-color:rgba(0, 158, 0, 0.66);
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
</style>


<body>
<div id="dialog"></div>

	<div class="content" id="div1">
		<p class="transportheader">
				<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;<span
					id="pageHeading">Route Master</span>
		</p>

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
						class="panel-title grade" style="color: #000; line-height: 18px;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Route Master
					</h3></a>
			
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
							<h4 class="modal-title" id="myModalLabel">Download</h4>
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

			<input type="hidden" id="classfeesetupsearchid"
				value='<logic:present name="classfeesetupSerchTerm"><bean:write name="classfeesetupSerchTerm"  /></logic:present>'></input>
  
       <input type="hidden" id="historylocId"
	   value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>'></input>


			<div id="gradeOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<div class="clearfix">
			<div class="col-md-12" id="inputcolor">
					<div class="col-md-6" id="txtstyle" style="font-family: Roboto,sans-serif;font-size: 13px; color: #000;">
						<%-- <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1" name="accyear" id="accyear" >
												<logic:present name="accYearList" scope="request">
												<logic:iterate id="accYearList" name="accYearList">
												<option value='<bean:write name="accYearList" property="accyearId"/>'><bean:write name="accYearList" property="accyearname"/></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div> --%>
					
					<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1"name="location" id="location" >
											
												<logic:present name="locationDetailsList" scope="request">
												<logic:iterate id="locationDetailsList" name="locationDetailsList">
												<option value='<bean:write name="locationDetailsList" property="location_id"/>'><bean:write name="locationDetailsList" property="locationname"/></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
										<input type="hidden" name="hiddenlocationname" id="hiddenlocationname" value='<bean:write name="currentlocation"/>'/>
										<input type="hidden" name="hiddenlocationid" id="hiddenlocationid" value='<bean:write name="locationId"/>'/>
										<input type = "hidden" name="hiddenaccyear" id="hiddenaccyear" value='<bean:write name="academic_year"/>'/>
					</div>
				</div>
			 </div>	
			</div>

					<%-- <display:table name="requestScope.accYearList"
						requestURI="/menuslist.html?method=gradeList" pagesize="10"
						export="false" class="table" id="allstudent" decorator="com.centris.campus.decorator.GradeSettingDecorator">

						<display:column property="sno1" title="Sl.No." ></display:column>

						<display:column property="accyear" sortable="true" class="${allstudent.accyearid} accyear" title="Academic Year <img src='images/sort1.png' style='float: right'/>" />

						<display:column property="status" sortable="true" title="Status <img src='images/sort1.png' style='float: right'/>" />
						
						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>

					</display:table> --%>
					<div id="markstable"></div>

				</div>
				
			</div>
		</div>
	</div>
	
</body>
</html>
