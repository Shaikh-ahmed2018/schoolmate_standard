<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>

<script type="text/javascript" src="JS/backOffice/Parents/StudentPerformance.js"></script>
<link href="CSS/Admin/CommonTable.css" rel="stylesheet">

<style>

.result{
	background: #09860c;
    padding: 5px;
    border-radius: 5px;
    color: #fff;
    
}

.borderless td,.borderless tbody{
    border: none !important;
    font-weight: 600;
}
.borderless tr td:nth-child(2) { width: 10px; }
.borderless tr td:first-child { width: 120px; }
.borderless tr td:nth-child(3){
text-align: left;
}


</style>
</head>

<body>

	<div class="col-md-10" style="font-family: Roboto,sans-serif; font-size: 20pt; color: #000; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		
		<div class="col-md-8" id="div2">
			<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Student Performance Details</span>
			</p>
		</div>
		
		<div class="input-group col-md-4" style="margin: 15px 0px;">
		<div class="form-group" style="margin-bottom: 0;">
			<label for="inputPassword" class="control-label col-xs-3" style="text-align: right; font-size: 13px; line-height: 35px; color: #000;">Student</label>
			<div class="col-xs-9">
				<select class="form-control" name="stuid" id="parentchild">
				<logic:present name="stuList" scope="request">
				<logic:iterate id="stuList" name="stuList" scope="request">
				<option value='<bean:write name='stuList' property='studentid'/>'>
				<bean:write name='stuList' property='studentFnameVar' /></option>
				</logic:iterate>
				</logic:present>
				</select>
			</div>
		</div>
		</div>
		
		<div class="errormessagediv" style="display: none;" align="center">
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
				
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #000; vertical-align: text-top;"> 
							<h4 class="panel-title" style="vertical-align: super;"><i class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Student Performance</h4></a>
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-12" style="margin-top: 10px;font-family: Roboto,sans-serif">
								<div class="col-md-1"></div>
								<div class="col-md-5">
									<table class='table borderless'>
										<tr>
											<td>Master/ Ms</td>
											<td>:</td>
											<td id='student'><logic:present name="parenthiddenid"><bean:write name='parenthiddenid' property="firstName" /></logic:present></td>
										</tr>
										<tr>
											<td>Admission No</td>
											<td>:</td>
											<td id='admissionNo'><logic:present name="parenthiddenid"><bean:write name='parenthiddenid' property="stdAdmisiionNo" /></logic:present></td>
										</tr>
									</table>
						 		</div>
								<div class="col-md-5">
									<table class='table borderless'>
										<tr>
											<td>Class-Div</td>
											<td>:</td>
											<td id='classdiv'><logic:present name="parenthiddenid"><bean:write name='parenthiddenid' property="classSection" /></logic:present></td>
										</tr>
									</table>
						   		</div>
						   		<div class="col-md-1"></div>
						   </div>
				
			<input type="hidden" id="parenthidden" value="<logic:present name="parenthiddenid"><bean:write name='parenthiddenid' property="parentID" /></logic:present>">
			
			<input type="hidden" id="hiddenlocId" value="<logic:present name="parenthiddenid"><bean:write name="parenthiddenid" property='locid' /></logic:present>">
			<input type="hidden" id="hiddenclassId" value="<logic:present name="parenthiddenid"><bean:write name="parenthiddenid" property='classDetailId'/></logic:present>">
			<input type="hidden" id="hiddensectionId" value="<logic:present name="parenthiddenid"><bean:write name="parenthiddenid" property='sectionid'/></logic:present>">	
			<input type="hidden" id="hiddenaccyearId" value="<logic:present name="parenthiddenid"><bean:write name="parenthiddenid" property='accyear'/></logic:present>">
				
				<div id="collapseOne" class="accordion-body collapse in">
				  <div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">	
			    	<div class='row'>
			    		<div class='col-md-12'>
				    			<table class="remarks table" id='allstudent'>
				    				<thead>
				    					<tr>
				    						<th>S.No</th>
				    						<th>Exam Type</th>
				    						<th>Exam Code</th>
				    						<th>Exam Name</th>
				    						<th>Result</th>
				    					</tr>
				    				</thead>
				    				<tbody>
				    					<logic:present name="examlist" scope="request">
				    					<logic:iterate id="examlist" name='examlist'>
				    					<tr>
				    						<td><bean:write name='examlist' property="sno"/></td>
				    						<td><bean:write name='examlist' property="examtypename"/></td>
				    						<td><bean:write name='examlist' property="examCode"/></td>
				    						<td><bean:write name='examlist' property="examName"/></td>
				    						<td id='<bean:write name='examlist' property="examid"/>' class='<bean:write name='examlist' property="status"/> <bean:write name='examlist' property="examtypeprefix"/>'></td>
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
		</div>
		</div>
	</div>
	</div>
</body>
</html>
