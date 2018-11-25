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



<script type="text/javascript" src="JS/backOffice/Staff/AddSubjectTeacherMappn.js"></script>


<style type="text/css">
#markstable{
padding-bottom: 10px;
}
.Not,.Set{
background-color:#FF0000; 
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
.Set{
background-color:green;
min-width:70px;
display:inline-block;
text-align:center;
 color:#fff;
}
.Pending {
    background-color: #5b42e4;
    min-width: 70px;
    display: inline-block;
    text-align: center;
    color: #fff;
}
</style>
</head>
<body>
<div id="dialog"></div>
	<div class="content" id="div1">
	<div  class='col-md-12 input-group' id="div2">
		<p class="transportheader">
			<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span	id="pageHeading">Subject Wise Teacher Mapping</span>
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
			<div class="panel-heading clearfix" style="margin-bottom: -1px;">
			<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" style="color: #fff;">
				<h3 class="panel-title exam" style="color: #000000; line-height: 18px;">
					<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Subject Wise Teacher Mapping
				</h3></a>
			</div>

       <input type="hidden" id="historyacayear" 
		value='<logic:present name="historyacayear" scope="request"><bean:write name="historyacayear"></bean:write></logic:present>' />
		
		<input type="hidden" id="historylocId" 
		value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"></bean:write></logic:present>' />

			<div id="ExamOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
			
				<div class="clearfix">
			
					<div class="col-md-6" id="txtstyle" style="font-size: 13px; color: #000;margin-top:10px;">
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1"name="location" id="location" >
										
											<logic:present name="locationList">
											<logic:iterate id="list" name="locationList">
												<option value='<bean:write name="list" property="locationId"/>'/><bean:write name="list" property="locationName"/>
											</logic:iterate>
											</logic:present>
										</select>
										
										
						<%-- 	<input type="hidden" id="locationh" value='<logic:present name="locationList" scope="request"><bean:write name="locationList" property="locationId" /></logic:present>' /> --%>	
							<input type="hidden" id="accyearh" value='<logic:present name="accyearHidden"  scope="request"><bean:write name="accyearHidden"/></logic:present>' />		
									</div>
									
									
									
									
										
					</div>
						
						
						
						
					
					 </div>
					 <div class="col-md-6" id="txtstyle" style="font-size: 13px; color: #000;margin-top:10px;">
				      
				      	<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select class="form-control" name="acayear" id="acayear" >
											<logic:present name="AccYearList">
											<logic:iterate id="list" name="AccYearList">
												<option value='<bean:write name="list" property="accyearId"/>'/><bean:write name="list" property="accyearname"/>
											</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
				</div>
	
			</div>
			
			
			
				<!-- table info -->
				
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">


						
						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th>Sl.No</th>
									<th>Academic Year</th>
									<th>Branch</th>
									<th>Status</th>
								</tr>
							</thead>
							
							<tbody>
							<%-- <logic:present name="accyearLocList">
								<logic:iterate id="list" name="accyearLocList">
							<tr id='<bean:write name="list" property="accyearId"/>,<bean:write name="list" property="locationId"/>'>
								<td><bean:write name="list" property="sno"/>
								<td><bean:write name="list" property="accyearName"/>
								<td><bean:write name="list" property="locationName"/>
								<td><span class="<bean:write name="list" property="status"/>"><bean:write name="list" property="status"/></span>
								</logic:iterate>
							</logic:present> --%>
							
					
							</tbody>
						</table>
					
					

				</div>
					
			
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
