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


<script type="text/javascript" src="JS/backOffice/Staff/SubjectwiseTeacherList.js"></script>



<style type="text/css">

.Not, Set{
background-color:#FF0000; 
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
.Set{
background-color:green;
min-width:71px;
display:inline-block;
text-align:center;
 color:#fff;
}

#allstudent tbody tr td{
text-align: left;
}

#allstudent thead tr th:NTH-CHILD(1){
width: 15px;
}

</style>
</head>
<body>
<div id="dialog"></div>
	<div class="content" id="div1">
		<p class="transportheader"> 
			<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;<span	id="pageHeading">Subject Wise Teacher Mapping</span>
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
			<div class="panel-heading clearfix" style="margin-bottom: -1px;">
			<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" style="color: #fff;">
				<h3 class="panel-title exam" style="color: #000000; line-height: 18px;">
					<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Subject Wise Teacher Mapping
				</h3></a>
				
				<div class="navbar-right">
						<span class="buttons"  id="back1">Back</span>
					</div>
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
										<input type="text" class="form-control" readonly="readonly" id="locationName" value='<logic:present name="locationName" scope="request"><bean:write name="locationName"></bean:write></logic:present>' />
									</div>
									
								<input type="hidden" id="hiddenlocationName" 
								value='<logic:present name="locationName" scope="request"><bean:write name="locationName"></bean:write></logic:present>' />	
					</div>
						
					
							</div>	
					<div class="col-md-6" id="txtstyle" style="font-size: 13px; color: #000;margin-top:10px;">
				<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" readonly="readonly" id="accyearName" value='<logic:present name="accyearName" scope="request"><bean:write name="accyearName"></bean:write></logic:present>' />
									</div>
								</div>
				</div>
	
			</div>
	
				</div>
			</div>
			
			
			
			
			
			
				<!-- table info -->
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

					 <logic:present name="teachersList" scope="request"> 
						<table class="table" id="allstudent">
							<thead>
								<tr>
									<th>Sl.No</th>
									<th>Academic Year</th>
									<th>Branch</th>
									<th>Registration Id</th>
									<th>Teacher Name</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
					
							<logic:iterate id="list" name="teachersList" indexId="index" >
								<tr id="<bean:write name="list" property='teacherId'/>" class="<bean:write name="list" property='status'/>">
									<td><bean:write name="list" property='sno'/></td>
									<td><bean:write name="list" property='accyearName'/></td>
									<td><bean:write name="list" property='locationName'/></td>
									<td><bean:write name="list" property='registerId'/></td>
									<td><bean:write name="list" property='teacherName'/></td>
									<td><span class="<bean:write name="list" property='status'/>"><bean:write name="list" property='status'/></span></td>
								</tr>
							</logic:iterate> 
							</tbody>

						</table>
					</logic:present> 

				</div>
					
					<div class='clearfix'><div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem' style='padding-left: 3px;'></span></div><div class='pagination pagelinks'></div></div>



				</div>
		
			</div>
			
			
			
				<input type="hidden" name="accyearh" id="accyearh" readonly="readonly"
								value='<logic:present name="accId" scope="request"><bean:write name="accId"/></logic:present>' />
								
				<input type="hidden" name="locationh" id="locationh" 
				value='<logic:present name="locId" scope="request"><bean:write name="locId"/></logic:present>' />
				
			
			
			
			
		</div>
	</div>
	
</body>
</html>
