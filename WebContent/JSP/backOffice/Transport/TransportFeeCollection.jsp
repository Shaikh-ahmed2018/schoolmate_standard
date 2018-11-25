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


<script type="text/javascript" src="JS/backOffice/Transport/TransportFeeCollectionView.js"></script>

</head>
<script type="text/javascript">
	
function CheckIsNumeric(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode > 31 && charCode != 46 && charCode != 45 &&(charCode < 48 || charCode > 57)) {
        /* document.getElementById("maxmarks").style.backgroundColor = "#FFB2B2"; */
        return false;
    }
    else {
        /* document.getElementById("maxmarks").style.backgroundColor = "#B2D8B2"; */
        return true;
    }
}


</script>

<style>
.receipt{
margin-left: 29%;
}
.paiddate{
margin-left: 20%;
}
.view{
     margin-right: -4px;
    margin-left: 1px;
}

.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons{
width:500px !important;
top:84px  !important;

margin: auto;
}
.glyphicon:hover{

cursor: pointer;
}
.navbar-right span{
margin-right: 3px;
}
 
.allstudent th:nth-child(2) {
    width: 95px;
}
.allstudent th:nth-child(7) {
    width: 170px;
}
.allstudent th{
font-weight: 300;
}
input[readonly="readonly"]{
background-color: #ccc;
}
input[readonly]{
background-color: #ccc;
} 
.allstudent tbody tr:nth-child(odd){
background-color: #f2fafc !important;
}
.allstudent tbody tr:nth-child(even){
background-color: #fff !important;
}
.heading{
cursor: pointer;
}
#myDialog #allstudent  tr td,#myDialog #allstudent tr th {
text-align: center !important;
}
.ui-dialog-titlebar{
color:#fff;
}
span.Paid{
background-color: rgba(0, 158, 0, 0.66);
padding:0px 2px;
color:#fff;
}
span.Not.Paid{
background-color:#f00;
color:#fff;
padding:0px 2px;
}
.print{
margin-left: 5px;
}
.paymentType{
padding: 2px 0px;
}
#paymentMode{
width:100%;
}
.desctable tbody,.desctable tbody{
	border: none;
}
.desctable td{
	border-top: none !important;
	padding-bottom: 0 !important;
	padding-top: 0 !important;
	margin-bottom: 0;
	margin-top: 0;
}
#printtable thead th:nth-child(n){
	border: 1px solid #000;
}
#printtable tbody th:nth-child(1){
	border-left: 1px solid #000;
	width:10%;
}
#printtable tbody th:nth-child(n){
	border-right: 1px solid #000;
}

#printtable tbody tr:last-child
{
   border-bottom: 1px solid #000;
}

#printtable tfoot tr:last-child
{
   border-bottom: 1px solid #000;
}
#printtable tfoot tr:nth-child(n)
{
   border-right: 1px solid #000;
   border-left: 1px solid #000;
}
#printtable tfoot th:nth-child(1)
{
   border-right: 1px solid #000;
}


</style>

<body>
<div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div>
<div class="mainDialog"><div id='Dialog'></div><div id='printDialog'></div><div id='viewDialog'></div></div>
<div id="mainDialog">
	<div class="paymentMode clearfix">
	<div class="col-md-6">
	<div class="paymentMode paymentType">
		<select id="paymentMode" onchange="HideError(this)">
			<option value="">Payment Mode</option>
			<option value="cash">Cash</option>
			<option value="cheque">Cheque</option>
			<option value="DD">DD</option>
		</select>
	</div>
	<div class="dd_cheque_bank paymentType">
	<input type='text' id='dd_cheque_bank' class='dd_cheque_bank_input' name='dd_cheque_bank' value='' onclick="HideError(this)"  style='display:none;background-color:rgba(255, 224, 0, 0.22);' placeholder='Enter Bank Name' />
	</div>
	</div>
	
	<div class="col-md-6">
	  <div class="dd_cheque_no paymentType">
		<input type='text' id='paymentParticulars' class='paymentParticulars' name='paymentParticulars' maxlength="6" value='' onclick="HideError(this)" style='display:none;background-color:rgba(255, 224, 0, 0.22);' placeholder='Enter Cheque Number' />
	  </div>
	  <div class="dd_cheque_date paymentType">
		<input type='text' id='dd_cheque_date' class='dd_cheque_date_input' name='dd_cheque_date' value='' onclick="HideError(this)" style='display:none;background-color:rgba(255, 224, 0, 0.22);' placeholder='Enter Cheque/DD Date' readonly="readonly" />
	  </div>
	</div>
	</div>
	<div id="myDialog">
	</div>
</div>


	<div class="content" id="div1">
		<div id="div2">
			<p class="transportheader">
				<img src="images/addstu.png"/>&nbsp;<span id="pageHeading">Transport Fee Details</span>
			</p>
		</div>


			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
				</div>
			</div>
		

		
			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<a href="#" class="msg-success bg-msg-succes"><span class="sucessmessage"></span></a>
				</div>
			</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title" style="color: #000000; vertical-align: text-top;">
					<span class="glyphicon glyphicon-menu-hamburger"></span> Transport Fee Details
				</h3>
				
				<div class="navbar-right">
				
					 <span id="back1" class="buttons">Back</a></span>
				</div>
				
				
				<!-- <script>
					$(document).ready(function() {$('[data-toggle="tooltip"]').tooltip();
					});
				</script> -->
			</div>
  <input type="hidden" id="loc_Id"
    value="<logic:present name="loc_Id" scope="request"><bean:write name="loc_Id"/></logic:present>"/>

    <input type="hidden" id="historylocId"
    value="<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>"/>
	
	<input type="hidden" id="historyacademicId"
    value="<logic:present name="historyacademicId" scope="request"><bean:write name="historyacademicId"/></logic:present>"/>
   
    <input type="hidden" id="historyclassname"
    value="<logic:present name="historyclassname" scope="request"><bean:write name="historyclassname"/></logic:present>"/>
   
    <input type="hidden" id="historysectionid"
    value="<logic:present name="historysectionid" scope="request"><bean:write name="historysectionid"/></logic:present>"/>
    
    <input type="hidden" id="historysearch"
    value="<logic:present name="historysearch" scope="request"><bean:write name="historysearch"/></logic:present>"/>
    
    		
			<input type="hidden" id="hiddenaddress" value="<logic:present name="custSchoolAddres"><bean:write name="custSchoolAddres"/></logic:present>"/>
			<input type="hidden" id="hiddenaddress1" value="<logic:present name="custSchoolAddres1"><bean:write name="custSchoolAddres1"/></logic:present>"/>
		
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 10pt; font-weight: 700; color: #000;">
					<div
						style="padding: 7px 170px; max-height: 500px; overflow: scroll;">
						<table class="table" id="allstudent" cellpadding="5"
							cellspacing="0" border="1" width="100%">
							<tr>
								<th colspan="2" style="font-size: 17px; font-family: Roboto Regular !important; background-color: #f5f5f5; text-align: center;">
								     <logic:present name="schoolName" scope="request"><bean:write name="schoolName"/></logic:present> (<logic:present name="branchName" scope="request"><bean:write name="branchName"/></logic:present>)
								</th>
							</tr>
							<tr>
								<th colspan="2" style="font-size: 17px; font-family: Roboto Regular !important; background-color: #f5f5f5; text-align: center;" class="feeName">TRANSPORT FEE FOR THE YEAR OF  <b><logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="accYearname"/></logic:present></b> 
								</th>
							</tr>

							<tr>
								<td colspan="1" style="font-size: 10pt; font-weight: 700; text-align: right;width:50%;">Student Name</td>
								<td colspan="1" style="font-size: 10pt; font-weight: 700; text-align: left;">
									<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="studentname"/></logic:present>
								</td>
							</tr>
							
							<tr>
								<td colspan="1"
									style="font-size: 10pt; font-weight: 700; text-align: right;width:50%;">Class-Section Name</td>
								<td colspan="1"
									style="font-size: 10pt; font-weight: 700; text-align: left;">
								<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="classname"/></logic:present>-<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="sectionname"/></logic:present>

								</td>
							</tr>						


						</table>
						<br /> 
			
			<input type="hidden" id="hiddenbranchname" 
			value='<logic:present name="branchName" scope="request"><bean:write name="branchName"/></logic:present>' />			
			
			<input type="hidden" id="hiddenAcademicYearName" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="accYearname"/></logic:present>' />			
			<input type="hidden" id="hiddenSchoolName" value='<logic:present name="schoolName"><bean:write name="schoolName"/></logic:present>' />
			<input type="hidden" id="hstudentid" value='<logic:present name="FeeCollectionVo" scope="request" ><bean:write name="FeeCollectionVo" property="studentid" /></logic:present>' />
			<input type="hidden" id="haddmissionno" value='<logic:present name="FeeCollectionVo" scope="request" ><bean:write name="FeeCollectionVo" property="addmissionno" /></logic:present>' />
			<input type="hidden" id="haccYear" value='<logic:present name="FeeCollectionVo" scope="request" ><bean:write name="FeeCollectionVo" property="accYear" /></logic:present>' />
			<input type="hidden" id="hclassId" value='<logic:present name="FeeCollectionVo" scope="request" ><bean:write name="FeeCollectionVo" property="classId" /></logic:present>' />
			<input type="hidden" id="hspecialization" value='<logic:present name="FeeCollectionVo" scope="request" ><bean:write name="FeeCollectionVo" property="specialization" /></logic:present>' />			
			<input type="hidden" id="hstuName" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="studentname"/></logic:present>' />
			<input type="hidden" id="hclassname" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="classname"/>-<bean:write name="FeeCollectionVo" property="sectionname"/></logic:present>' />
			<logic:present name="FeeCollectionVo" scope="request">
					
					
			
					<!-- Term Name -->
					
						<table class="table allstudent payablefee" cellpadding="5" cellspacing="0" border="1" width="100%" >
							<tr class="heading" >
								<th  colspan="7" style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto Regular !important;"><span
									class="glyphicon glyphicon-menu-hamburger"
									style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>Payable Fees</th>
							
							</tr>
							<tr class="collapsable">
							<th  style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Select</th>
								<th  style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Term Name</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Month Name</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Total Fee Amount</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Payment Status</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Payment Date</th>
								<th style="font-size: 14px; text-align: left; background: #f9f9f9 !important; font-family: Roboto Medium;"><input type="button"  class="pay buttons" maxlength="50" value="Pay Now" style="padding: 1px 5px;border:none;width:83px;" /></th>
							</tr>
						<logic:iterate id="FeeCollection" name="FeeCollectionVo" property="feeNamelist" > 	
							<tr class="collapsable <logic:present name='FeeCollection'><bean:write name="FeeCollection" property="recieptNo" /></logic:present>">
								<td style="font-size: 10pt; font-weight: 700; text-align: center;" ><input type="checkbox" class="selectmonth <logic:present name="FeeCollection"><bean:write name="FeeCollection" property="status" /></logic:present>" value="<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="termId" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="monthName" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="actualAmt" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="recieptNo" /></logic:present>"  /></td>
								<td class="heading" style="font-size: 10pt; font-weight: 700; text-align: center;" id="<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="termId" /></logic:present>"><logic:present name="FeeCollection"><bean:write name="FeeCollection" property="term" /></logic:present></td>
								<td style="font-size: 10pt; font-weight: 700; text-align: center;" class="transportMonth"><logic:present name="FeeCollection"><bean:write name="FeeCollection" property="monthName" /></logic:present></td>
								<td style="font-size: 10pt; font-weight: 700; text-align: center;" class="transportfee"><logic:present name="FeeCollection"><bean:write name="FeeCollection" property="actualAmt" /></logic:present></td>
								<td style="font-size: 10pt; font-weight: 700; text-align: center; " id='status'><span class='<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="status" /></logic:present>'><logic:present name="FeeCollection"><bean:write name="FeeCollection" property="status" /></logic:present> </span></td>
								<td style="font-size: 10pt; font-weight: 700; text-align: center;" class="paidDate"><logic:present name="FeeCollection"><bean:write name="FeeCollection" property="paidDate"/></logic:present></td>
								<td><input type="button" id="<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="termId" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="monthName" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="actualAmt" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="recieptNo" /></logic:present>" class="buttons refund" value="Refund" style="display:none;padding: 1px 5px;border: none;"/>
							     <input type="button" name="<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="status"/></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="termId" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="monthName" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="actualAmt" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="recieptNo" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="paidDate"/></logic:present>" class="buttons view" value="view" style="display:none;padding: 1px 5px;border: none;"/>
							     <input type="button" name="<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="status"/></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="termId" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="monthName" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="actualAmt" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="recieptNo" /></logic:present>,<logic:present name="FeeCollection"><bean:write name="FeeCollection" property="paidDate"/></logic:present>" class="buttons print" value="Print" style="display:none;padding: 1px 5px;border: none;"/>
							  </td>
							</tr>
							 </logic:iterate>
							

						</table>
					
				</logic:present> 
				
				<logic:present name="paymenthistory" scope="request">
							<table class="table allstudent receiptlist" cellpadding="5" cellspacing="0" border="1" width="100%" style="font-weight: 500;">
								<tr class="heading" >
									<th  colspan="8" style="font-size: 17px; text-align: left;width:100%; background-color: #f5f5f5; font-family: Roboto Regular !important;">
									<span class="glyphicon glyphicon-menu-hamburger"
										style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>Payment History
										<input type="button"  class="print1 buttons" value="Print" style="padding: 1px 5px;border: none;float: right;" />
									</th>
								</tr>
								<tr class="collapsable">
									<th style="vertical-align: middle; font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Select</th>
									<th style="vertical-align: middle; width:91px; font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Receipt No.</th>
									<th style="vertical-align: middle; font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;width:17%;">Terms Name</th>
									<th style="vertical-align: middle; font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Payment Date</th>
									<th style="vertical-align: middle; font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Paid Amount</th>
									<th style="vertical-align: middle; font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Balance Amount</th>
									<th style="vertical-align: middle; font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Advance Amount</th>
									<th style="vertical-align: middle; font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Payment Mode</th>
								</tr>
								<logic:iterate id="paymenthistory" name="paymenthistory">
									<tr class="collapsable">
										<td style="vertical-align: middle; font-size: 10pt; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">
											<input type="radio" name="receiptno" class="receiptno" id="<logic:present name="paymenthistory"><bean:write name="paymenthistory" property="termid"/></logic:present>" value="<logic:present name="paymenthistory"><bean:write name="paymenthistory" property="receiptno"/></logic:present>">	
										</td>
										<td style="vertical-align: middle; font-size: 10pt; text-align: center; font-family: Roboto Medium;" class="receipt"><logic:present name="paymenthistory"><bean:write name="paymenthistory" property="receiptno"/></logic:present></td>
										<td style="vertical-align: middle; font-size: 10pt; text-align: center; font-family: Roboto Medium;" class="terms"><logic:present name="paymenthistory"><bean:write name="paymenthistory" property="termName"/></logic:present></td>
										<td style="vertical-align: middle; font-size: 10pt; text-align: center; font-family: Roboto Medium;" class="billdate"><logic:present name="paymenthistory"><bean:write name="paymenthistory" property="billdate"/></logic:present></td>
										<td style="vertical-align: middle; font-size: 10pt; text-align: center; font-family: Roboto Medium;"><logic:present name="paymenthistory"><bean:write name="paymenthistory" property="tot_paid_amt"/></logic:present></td>
										<td style="vertical-align: middle; font-size: 10pt; text-align: center; font-family: Roboto Medium;"><logic:present name="paymenthistory"><bean:write name="paymenthistory" property="outstanding_balance"/></logic:present></td>
										<td style="vertical-align: middle;width: 12%; font-size: 10pt; text-align: center; font-family: Roboto Medium;"><logic:present name="paymenthistory"><bean:write name="paymenthistory" property="advanceAmount"/></logic:present></td>
										<td style="vertical-align: middle; font-size: 10pt; text-align: center; font-family: Roboto Medium;"><logic:present name="paymenthistory"><bean:write name="paymenthistory" property="paymentMode"/></logic:present></td>
									</tr>
								</logic:iterate>
							</table>
						</logic:present>
			
					</div>
				</div>
			
			</div>
		</div>
	</div>
	<textarea id="printing-css" style="display:none;">
		#Dialog{
		width:450px;
		}
		
		
    .mainDialog{padding:0px;margin:0px;}
    body {
         -webkit-print-color-adjust: exact;
         margin:0 auto;
         padding:0;
          width:450px !important;
        }
  #printtable thead th:nth-child(1){
  	width : 15%;
  }
  
  #printtable thead th{
  	border-right: 1px solid #000;
  	border-top: 1px solid #000;
  	border-bottom: 1px solid #000;
  }     
 #printtable thead th:nth-child(1){
  	border-left: 1px solid #000;
  } 
 #printtable tbody th:nth-child(n){
  	border-right: 1px solid #000;
  } 
 #printtable tbody th:nth-child(1){
  	border-left: 1px solid #000;
  } 
#printtable tbody tr:last-child th {
    border-bottom: 1px solid #000 !important;
}
#printtable tfoot tr:last-child th {
    border-bottom: 1px solid #000 !important;
}
#printtable tfoot tr:nth-child(n) th {
    border-right: 1px solid #000 !important;
}
#printtable tfoot th:nth-child(1){
    border-left: 1px solid #000 !important;
}
.table_info{
    margin : auto;
    width : 90%;
}

	</textarea>
	
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</body>
</html>



	