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

<script type="text/javascript" src="JS/backOffice/Reports/leavebank.js"></script>

<style>
#editleavebank:hover {
	cursor: pointer;
}

#deleteleavebank:hover {
	cursor: pointer;
}
#addleavebank:hover {
	cursor: pointer;
}

#xlss:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
.download:hover {
	cursor: pointer;
}
.row{
    margin-left: -0px;
}
#allstudent th:nth-child(1) {
    min-width: 150px !important;
}
</style>

</head>

<body>

	<div class="content" id="div1">
		
		<div class="row">
		<div class="col-md-12 input-group" id="pageHeading">
			<p>
				<span class="glyphicon glyphicon-user" style="font-size: 27px;"></span>&nbsp;&nbsp;<span
					id="pageHeading">Leave Bank Details</span>
			</p>
			</div>
		</div>

		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;vertical-align: text-top"><h3
						class="panel-title" style="color: #000000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span> Leave Bank Details
					</h3></a>
			</div>

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"	style="font-family: Roboto,sans-serif; font-size: 13px; ">
				
						 <logic:present name="ViewLeaveDetails" scope="request">
      			 <table class='table' id="allstudent" >
         		<thead>
         		 <tr>
              	<th>Academic Year</th>
             	<th>Leave Type</th>
              	<th>Opening Balance</th>
             	<th>Leaves Consumed</th>
              	<th>Closing Balance</th>
         		 </tr>
         		 </thead>
         		 <tbody>
       			<logic:iterate id="iterateid" name="ViewLeaveDetails">
          		 <tr>
             		 <td><bean:write name="iterateid" property="accyear"/></td>
              		<td><bean:write name="iterateid" property="leavetypeName"/></td>
              		<td><bean:write name="iterateid" property="totalleaves"/></td>
              		 <td><bean:write name="iterateid" property="consumedLeave"/></td>
             		 <td><bean:write name="iterateid" property="available_leave"/></td>
           		</tr>
      			 </logic:iterate>
      			  </tbody>
       			</table>
    		</logic:present>
   		<div>
    	<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
   			<span  class='numberOfItem'></span>	
   		</div>
   		<div class='pagination pagelinks'></div>
   		</div>
   		</div>
   		</div>
		</div>
		</div>

</body>
</html>