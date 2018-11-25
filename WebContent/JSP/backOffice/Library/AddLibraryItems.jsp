<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>

<script type="text/javascript" src="JS/backOffice/Library/AddLibraryItem.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><style>
.buttons{

vertical-align: 0px;

}
.own-panel{
padding : 15px;
}

</style>

</head>

<body>
 
	<div class="col-md-10 addLibraryItem" id="div-main">
		<p>
			<img src="images/addstu.png" / style="    vertical-align: top;">&nbsp;<span id="pageHeading">
			<logic:present name="library" scope="request"><bean:write name="library"></bean:write></logic:present>
			</span>
		</p>
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

		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">

					<a href="#" style="color: #000000; vertical-align: text-top;"><h3
							class="panel-title" style="vertical-align: text-top;">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Library Item
						</h3></a>


					<div class="navbar-right">
						<span id="save" class="buttons">Save</span>
						<span id="back1" class="buttons">Back</span>
					</div>
				</div>

	<input type="hidden" id="historystatus" 
	value='<logic:present name="historystatus" scope="request"><bean:write name="historystatus" /></logic:present>' />				
 			 
				<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body own-panel">
						<div class="col-md-6">
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5">Item Name<font color="red"> *</font></label>
								<div class="col-xs-7">
									<input type="text" name="name" id="name" class="form-control"  value='<logic:present name="editlist" ><bean:write name="editlist" property="name"></bean:write></logic:present>'></input>
									<input type="hidden" id="hiddenid" value='<logic:present name="editlist"><bean:write name="editlist" property="id"/></logic:present>'>
								</div>
							</div>
						
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-5">Status</label>
							<div class="col-xs-7">
								<select id="status" name="status" class="form-control">
									<option value="Active">Active</option>
									<option value="InActive">InActive</option>
								</select>
								<input type="hidden" id="hiddenstatus" value='<logic:present name="editlist"><bean:write name="editlist" property="status"/></logic:present>'>
							</div>
						</div>	
					</div>
						<div class="col-md-6" >
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5">Description </label>
								<div class="col-xs-7">
									<textarea style="resize:none" rows="4" cols="25"  class="form-control" name="description" id="description" ><logic:present name="editlist"><bean:write name="editlist" property="description"></bean:write></logic:present></textarea>
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html:html>