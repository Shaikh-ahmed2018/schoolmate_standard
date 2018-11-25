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
<title>eCampus Pro</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="JS/backOffice/Staff/TeacherTimeTable.js"></script>

 <!-- <script type="text/javascript" src="JS/backOffice/Parents/StudentTimeTable.js"></script>  -->

<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */
</style>

<style>
.download:hover {
	cursor: pointer;
}

#excelDownload:hover {
	cursor: pointer;
}

#pdfDownload:hover {
	cursor: pointer;
}

#dialog p {
	font-size: 13px;
}
#allstudent td:nth-child(1) {
    text-align: left;
}
 #allstudent th:nth-child(1) {
    text-align: left;
    width: 70px;
}

.ui-dialog{
    position:fixed;
    top:130px !important;          
}
.distance{
margin-bottom: 0px;
}
.schoolHeading{
display: none;
font-weight: 900px;
}
</style>

</head>

<body>

	<div class="content" id="div1">
		<div id="dialog"></div>
			<div class="col-md-12 input-group" id="div2">
				<p class='transportheader'>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Time Table</span>
				</p>
			</div>
	<input type="hidden" name="hidden" class="hiddenclass" id="hiddenid"  value='<logic:present name="studentexam"><bean:write name="studentexam" /></logic:present>'></input>
													
<input	type="hidden" name="parenthidden" id="parenthidden" value="<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="parentID"/></logic:present>"/>

		

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

		<div class="panel panel-primary clearfix distance">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3 class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Time Table
						
					</h3></a>



				<div class="navbar-right" style="right:3px;">
				
				<%-- <logic:present name="UserDetails" scope="session">
						<logic:iterate id="daymap" name="UserDetails"
							property="permissionmap" scope="session">
							<logic:equal value="VTSDWN" name="daymap" property="key">
								<logic:equal value="true" name="daymap" property="value">
									<span  class="buttons" style="margin-right:10px;"  id="iconsimg" data-toggle="modal" data-target="#myModal">Download </span>
								</logic:equal>
							</logic:equal>
						</logic:iterate>
					</logic:present> --%>
					
					<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="VTSDWN" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
											<span class="buttons" id="print"  style="top:7px;">Print</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					
					
					

			   
					   
                <a href="teachermenuaction.html?method=viewTeacherListForTimeTable"><span class="buttons" style="margin-right:10px;">Back</span></a>
			

		

				</div>
			</div>
			<!-- pop up -->

			<div id="popup">
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
			</div>

		

			
           
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-bottom: 10px;">
					
				<div class="col-md-6"
				style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 10px;">
			
			</div>
			
		<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
			
			
						<div class="col-md-12" id="scrolid"
						style="padding: 0;  overflow-x: scroll;">
					 <div class="schoolHeading"><div class="row"><div class="col-md-12"><div class="col-xs-6"><h3>School Name: <logic:present name="timeTableDetails" scope="request"><bean:write name="timeTableDetails" property="locationName"/></logic:present></h3>
					 <h3>Teacher Name: <logic:present name="timeTableDetails" scope="request"><bean:write name="timeTableDetails" property="teacherName"/></logic:present></h3></div>
					 <div class="col-xs-6"><h3 style="float:right">Academic Year: <logic:present name="accname" scope="request"><bean:write name="accname"/></logic:present></h3></div>
					  </div></div></div>
						<table id="allstudent"  class="table">
						<thead>
							<tr>
							
							<th>Day</th>
							<th>Period 1</th>
							<th>Period 2</th>
							<th>Period 3</th>
							<th>Period 4</th>
							<th>Period 5</th>
							<th>Period 6</th>
							<th>Period 7</th>
							<th>Period 8</th>
							<th>Period 9</th>
							</tr>
						</thead>
							<tbody></tbody>
							
						</table>
					
					
					</div>
			    
				<br>
			</div>
		
			
		</div>
	</div>



</div>
	
	</div>
	
	

        
		<div id="collapseTwo" class="accordion-body collapse in">
		<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-bottom: 10px;">
	
		<div class="panel panel-primary" style="margin-top: 15px;">
     		<div class="panel-heading clearfix">
				<h3 class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Today Time Table
						<div class="navbar-right" style="margin-top: 3px;" >
				
			
					
					<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="VTSDWN" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
											<span class="buttons" id="print1">Print</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					
					
					

			   
					   
          
			

		

				</div>
					</h3>
				
			</div>
			
		<div id="collapseOne" class="accordion-body collapse in">
		     
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
			      
			           
			           <div class="col-md-12" id="scrolid1"
						style="padding: 0;  overflow-x: scroll;">
						<div class="schoolHeading"><div class="row"><div class="col-md-12"><div class="col-xs-6"><h3>School Name: <logic:present name="timeTableDetails" scope="request"><bean:write name="timeTableDetails" property="locationName"/></logic:present></h3>
					 <h3>Teacher Name: <logic:present name="timeTableDetails" scope="request"><bean:write name="timeTableDetails" property="teacherName"/></logic:present></h3></div>
					 <div class="col-xs-6"><h3 style="float:right">Academic Year: <logic:present name="accname" scope="request"><bean:write name="accname"/></logic:present></h3></div>
					  </div></div></div>
			          
					<table id="todayTimeTable" class="table allstudent">
						<thead>
							<tr>
							<th class="dateColumn" style="width:105px;">Date</th>
							<th>Day</th>
							<th>Period 1</th>
							<th>Period 2</th>
							<th>Period 3</th>
							<th>Period 4</th>
							<th>Period 5</th>
							<th>Period 6</th>
							<th>Period 7</th>
							<th>Period 8</th>
							<th>Period 9</th>
							</tr>
						</thead>
							<tbody></tbody>
							

						</table>
					
				</div>
				
				<br>
			</div>
		
			
		</div>
	   </div>
	
		</div>
		
			
			
			
			
	</div>		
	
	</div>

	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
	
	<textarea style="display: none;" id="printing-css">
	table tbody tr td{border:1px solid #000 !important;}
	table thead tr th{border:1px solid #000 !important;}
	#scrolid1{
	border:25px solid #000;
	}
	</textarea>
</body>
</html>