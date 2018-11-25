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
<script type="text/javascript" src="JS/backOffice/Fee/FeeCollectionView.js"></script>
</head>

<style>
.paiddate,.receipt{
margin-left: 20%;
}

.glyphicon:hover{

cursor: pointer;
}
.navbar-right span{
margin-right: 3px;
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
.allstudent tr th:nth-child(1),.allstudent tbody tr td:nth-child(1){
width: auto !important;
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
span.Pending{
background-color:#2911f1;
color:#fff;
padding:0px 2px;
}
.print{
margin-left: 5px;
}
#myDialog{
display: none;
}
#paymentMode,#isdiscountapp,#termsid,.percentages,#dd_cheque_bank,#paymentParticulars,#dd_cheque_date{
width:100%;
}
hr{
margin-top: 10px;
margin-bottom:10px;
}
.paymentType,.isdiscount{
padding: 2px 0px;
}
#Dialog h3{
	margin: 0px;
}
.heading{
cursor: pointer;
}
.collapsable{
 display: none;
}
#printtables tbody{
	border-color: #000;
}
</style>

<body>
<div class="mainDialog"><div id='Dialog'></div></div>
   <div id="myDialog">
   <div class="paymentMode clearfix">
	<div class="col-md-12">
	<div class="col-md-6">
	<div class="form-group clearfix">
		<label for="inputPassword" class="control-label" style="text-align: right; line-height: 35px;">Bill Date</label>
	</div>
	</div>
	<div class="col-md-6">
	<div class="form-group clearfix">
		<input type="text" name="admissiondate"  id="admissiondate" readonly="readonly" style='background-color:rgba(255, 224, 0, 0.22);'/>
	</div>
	</div>
	</div>
	<div class="col-md-6">
	<div class="isdiscount">
		<select id="isdiscountapp">
			<option value="">Discount</option>
			<option value="Y">Yes</option>
			<option value="N">No</option>
		</select>
	</div>
	<div class="terms" style="display: none;">
		<select id="termsid" >
			<option value="">-----Select-----</option>
		</select>
	</div>
	<div class="dd_cheque_bank paymentType">
	<input type='text' id='dd_cheque_bank' class='dd_cheque_bank_input' name='dd_cheque_bank' value=''  style='display:none;background-color:rgba(255, 224, 0, 0.22);' placeholder='Enter Bank Name' />
	</div>
	<div class="dd_cheque_date paymentType">
		<input type='text' id='dd_cheque_date' class='dd_cheque_date_input' name='dd_cheque_date' value=''  style='display:none;background-color:rgba(255, 224, 0, 0.22);' placeholder='Select Date' readonly="readonly" />
	  </div>
	</div>
	
	<div class="col-md-6">
	<div class="paymentMode paymentType">
		<select id="paymentMode" style="width: 216px;">
			<option value="">Payment Mode</option>
			<option value="Cash">Cash</option>
			<option value="Cheque">Cheque</option>
			<option value="Card">Card</option>
			<option value="Card+Cash">Card+Cash</option>
			<option value="DD">DD</option>
			<option value="Online">Online</option>
		</select>
	</div>
	  <div class="dd_cheque_no paymentType">
		<input type='text' id='paymentParticulars' class='paymentParticulars' name='paymentParticulars' value=''  style='display:none;background-color:rgba(255, 224, 0, 0.22);' placeholder='Enter Cheque Number' />
	  </div>
	</div>
	</div>
	<div class="myDailogTable"></div>
	<div class="grandtotal"></div>
	<div class="cashcardtotal"></div>
</div>
<div id="myDailogTable" style="display:none;"></div>
	<div class="content" id="div1">
		<div id="div2">

			<p>
				<span class="glyphicon glyphicon-user"></span><span id="pageHeading">Academic Fee Details</span>
			</p>
		</div>

		
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
				</div>
			</div>
		
			<input type='hidden' id='reason' value='<logic:present name='Reason' scope='request'><bean:write name='Reason'></bean:write></logic:present>'/>
		
			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<a href="#" class="msg-success bg-msg-succes"><span class="sucessmessage"></span></a>
				</div>
			</div>
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title" style="color: #767676;vertical-align: text-top;">
					<span class="glyphicon glyphicon-menu-hamburger"></span> ACADEMIC FEE DETAILS
				</h3>
				
				<div class="navbar-right">
				
					 <span id="back" class="buttons">Back</span>
				</div>
				
				
			
			</div>
		
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 10pt; font-weight: 700; color: #5d5d5d;">
					<div
						style="padding: 7px 170px; max-height: 500px; overflow: scroll;">
						<table class="table" id="allstudent" cellpadding="5" cellspacing="0" border="1" width="100%">
							<tr>
								<th class="schoolname" colspan="2" style="font-size: 17px; font-family: Roboto Regular !important; background-color: #f5f5f5; text-align: center;">
							<logic:present name="schoolName"><bean:write name="schoolName" /></logic:present>
								</th>
							</tr>
							<tr>
								<th colspan="2" style="font-size: 17px; font-family: Roboto Regular !important; background-color: #f5f5f5; text-align: center;">ACADEMIC FEE FOR THE YEAR OF  <b><logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="accYearname"/></logic:present></b> 
								</th>
							</tr>

							<tr>
								<td colspan="1" style="font-size: 10pt; font-weight: 700; text-align: right;width:50%;">Student Name</td>
								<td colspan="1" style="font-size: 10pt; font-weight: 700; text-align: left;">
									<logic:present name="studentDetails"><bean:write name="studentDetails" property="studentname"/></logic:present>
								</td>
							</tr>
							
							<tr>
								<td colspan="1"
									style="font-size: 10pt; font-weight: 700; text-align: right;width:50%;">Class-Section Name</td>
								<td colspan="1"
									style="font-size: 10pt; font-weight: 700; text-align: left;">
								<logic:present name="studentDetails"><bean:write name="studentDetails" property="classname"/></logic:present>-<logic:present name="studentDetails"><bean:write name="studentDetails" property="sectionname"/></logic:present>

								</td>
							</tr>						


						</table>
						<br />
			<input type="hidden" id="afterdis" value='' />			
			<input type="hidden" id="feepayment" value='' />
			<input type="hidden" id="termdetails" value='' class=''/>
			<input type="hidden" id="amtdetails" value='' class=''/>
			<input type='hidden' id='schadd' value = '<logic:present name='address' scope = 'request'><bean:write name='address'></bean:write></logic:present>'/>
			<input type="hidden" id="feesetcode" value='' />
			<input type="hidden" id="hstudentid" value='<logic:present name="studentDetails" scope="request" ><bean:write name="studentDetails" property="studentid" /></logic:present>' />
			<input type="hidden" id="hstudentidno" value='<logic:present name="studentDetails" scope="request" ><bean:write name="studentDetails" property="studentIdNo" /></logic:present>' />
			<input type="hidden" id="haddmissionno" value='<logic:present name="studentDetails" scope="request" ><bean:write name="studentDetails" property="addmissionno" /></logic:present>' />
			<input type="hidden" id="haccYear" value='<logic:present name="studentDetails" scope="request" ><bean:write name="studentDetails" property="accYear" /></logic:present>' />
			<input type="hidden" id="hclassId" value='<logic:present name="studentDetails" scope="request" ><bean:write name="studentDetails" property="classId" /></logic:present>' />
			<input type="hidden" id="hspecialization" value='<logic:present name="studentDetails" scope="request" ><bean:write name="studentDetails" property="specialization" /></logic:present>' />			
			<input type="hidden" id="hstuName" value='<logic:present name="studentDetails"><bean:write name="studentDetails" property="studentname"/></logic:present>' />
			<input type="hidden" id="hclassname" value='<logic:present name="studentDetails"><bean:write name="studentDetails" property="classname"/>-<bean:write name="studentDetails" property="sectionname"/></logic:present>' />
			<input type="hidden" id="locid" value='<logic:present name="locid"><bean:write name="locid" /></logic:present>' />
			<input type="hidden" id="haccyearname" value='<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="accYearname"/></logic:present>'/>
			<logic:present name="FeeCollectionVo" scope="request">
					
					
			
					<!-- Term Name -->
					
						<table class="table allstudent" id = "feestable" cellpadding="5" cellspacing="0" border="1" width="100%" >
							<tr class="heading" >
								<th  colspan="7" style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto Regular !important;"><span
									class="glyphicon glyphicon-menu-hamburger"
									style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>Payable Fees<input type="button" class="buttons print" value="Print" style="display:none;padding: 1px 5px;border: none;float: right;" /><input type="button" class="paynow buttons" value="Pay Now" style="padding: 1px 5px;border: none;float: right;" /></th>
							</tr>
							<tr>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;"><input type='checkbox' id='selectAll'></th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Term</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Total Fee Amt</span></th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Paid Amt</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Balance Amt</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Payment Status</th>
								
							</tr>
						<logic:iterate id="FeeCollection" name="FeeCollectionVo" property="feeNamelist" > 	
							<tr class="collapsable">
								<td  class="<bean:write name="FeeCollection" property="status" />" style="font-size: 10pt; font-weight: 700; text-align: center;"><input type="checkbox" class='select selectterm' value="<bean:write name="FeeCollection" property="termId" />-<bean:write name="FeeCollection" property="feesettingCode" />-<bean:write name="FeeCollection" property="feeindetailId" />" id='<bean:write name="FeeCollection" property="dueCarry" />-<bean:write name="FeeCollection" property="predues" />-<bean:write name="FeeCollection" property="fineAmount" />-<bean:write name="FeeCollection" property="advanceCarry" />-<bean:write name="FeeCollection" property="concessionAmt" />-<bean:write name="FeeCollection" property="prefeecolcode" />'/><input type="radio" style="display: none;" class='select selectterm' value="<bean:write name="FeeCollection" property="termId" />-<bean:write name="FeeCollection" property="feesettingCode" />-<bean:write name="FeeCollection" property="feeindetailId" />" id='<bean:write name="FeeCollection" property="dueCarry" />-<bean:write name="FeeCollection" property="predues" />-<bean:write name="FeeCollection" property="fineAmount" />-<bean:write name="FeeCollection" property="advanceCarry" />-<bean:write name="FeeCollection" property="concessionAmt" />-<bean:write name="FeeCollection" property="prefeecolcode" />'/></td>
								<td class="heading" style="font-size: 10pt; font-weight: 700; text-align: center;" id="<bean:write name="FeeCollection" property="termId" />,<bean:write name="FeeCollection" property="feesettingCode" />,<bean:write name="FeeCollection" property="feeindetailId" />"><bean:write name="FeeCollection" property="term" /></td>
								<td id ='termfees' style="font-size: 10pt; font-weight: 700; text-align: center;"><bean:write name="FeeCollection" property="actualAmt" /></td>
								<td style="font-size: 10pt; font-weight: 700; text-align: center;"><bean:write name="FeeCollection" property="paidAmt" /></td>
								<td class='balamt' style="font-size: 10pt; font-weight: 700; text-align: center;"><bean:write name="FeeCollection" property="dueCarry" /></td>
								<td style="font-size: 10pt; font-weight: 700; text-align: center; " id='status'><span class='<bean:write name="FeeCollection" property="status" />'><bean:write name="FeeCollection" property="status" /></span></td>
								<%-- <td class='balfees <bean:write name="FeeCollection" property="dueCarry" /> <bean:write name="FeeCollection" property="predues" /> <bean:write name="FeeCollection" property="fineAmount" />' id='<bean:write name="FeeCollection" property="fineAmount" />,<bean:write name="FeeCollection" property="advanceCarry" />,<bean:write name="FeeCollection" property="dueCarry" />,<bean:write name="FeeCollection" property="concessionAmt" />'><input type="button" id="<bean:write name="FeeCollection" property="paidDate" />" name="<bean:write name="FeeCollection" property="paidAmt" />,<bean:write name="FeeCollection" property="recieptNo" />,<bean:write name="FeeCollection" property="paidAmt" />,<bean:write name="FeeCollection" property="paymode" />,<bean:write name="FeeCollection" property="ddno" />,<bean:write name="FeeCollection" property="dddate" />" class="buttons view" value="View" style="display:none;padding: 1px 5px;border: none;"/><input type="button" id="<bean:write name="FeeCollection" property="termId" />,<bean:write name="FeeCollection" property="feesettingCode" />,<bean:write name="FeeCollection" property="feeindetailId" />" name="<bean:write name="FeeCollection" property="recieptNo" />,<bean:write name="FeeCollection" property="paidAmt" />,<bean:write name="FeeCollection" property="paymode" />,<bean:write name="FeeCollection" property="ddno" />,<bean:write name="FeeCollection" property="dddate" />" class="buttons print" value="Print" style="display:none;padding: 1px 5px;border: none;"/></td> --%>
							</tr>
						</logic:iterate>
							

						</table>
					
				</logic:present> 
			<logic:present name="paymenthistory" scope="request">
					<!-- Term Name -->
					
						<table class="table allstudent" id = "feestable" cellpadding="5" cellspacing="0" border="1" width="100%" >
							<tr class="heading" >
								<th  colspan="7" style="font-size: 17px; text-align: left; background-color: #f5f5f5; font-family: Roboto Regular !important;"><span
									class="glyphicon glyphicon-menu-hamburger"
									style="vertical-align: -5px; margin-right: 5px; padding-left: 5px;"></span>Payment History<input type="button" class="buttons print" value="Print" style="padding: 1px 5px;border: none;float: right;" /></th>
							</tr>
							<tr>
								<th style="vertical-align: middle; font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Select</th>
								<th style="vertical-align: middle; width:91px; font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Receipt No.</th>
								<th style="vertical-align: middle; font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Terms Name</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Total Fee Amt</span></th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Paid Date</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Paid Amt</th>
								<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">Balance Amt</th>
								<!-- <th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;"></th> -->
							</tr>
						<logic:iterate id="FeeCollection" name="paymenthistory" property="feeNamelist" > 	
							<tr class="collapsable">
								<td style="vertical-align: middle; font-size: 10pt; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">
									<input type="radio" name="receiptno" class="receiptno" id="<bean:write name="FeeCollection" property="term"/>" value="<bean:write name="FeeCollection" property="recieptNo"/>">	
								</td>
								<td style="vertical-align: middle; font-size: 10pt; text-align: center; font-family: Roboto Medium;" class="receipt"><bean:write name="FeeCollection" property="recieptNo"/></td>
								<td style="vertical-align: middle; font-size: 10pt; text-align: center; font-family: Roboto Medium;" class="term"><bean:write name="FeeCollection" property="term"/></td>
								<td id ='termfees' style="font-size: 10pt; font-weight: 700; text-align: center;"><bean:write name="FeeCollection" property="totalFeeAmt" /></td>
								<td id ='termfees' style="font-size: 10pt; font-weight: 700; text-align: center;"><bean:write name="FeeCollection" property="paidDate" /></td>
								<td style="font-size: 10pt; font-weight: 700; text-align: center;"><bean:write name="FeeCollection" property="paidAmt" /></td>
								<td class='balamt' style="font-size: 10pt; font-weight: 700; text-align: center;"><bean:write name="FeeCollection" property="dueCarry" /></td>
								<%-- <td class='balamt' style="font-size: 10pt; font-weight: 700; text-align: center;"><input type="button" id='<bean:write name="FeeCollection" property="recieptNo" />' class="buttons print" value="Print" style="padding: 1px 5px;border: none;" /></td> --%>
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
body {-webkit-print-color-adjust: exact;margin:0 auto;padding:0; width:450px !important;}
	</textarea>
	
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</body>
</html>



	