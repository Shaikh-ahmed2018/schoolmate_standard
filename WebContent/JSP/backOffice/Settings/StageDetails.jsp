<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en"> 

<head>


<script type="text/javascript" src="JS/backOffice/Settings/StageDetails.js"></script>



</head>

<body>
   <!-- <div id="loder" style="display: none; position:absolute; height: 800px;width: 800px;left: 0;right: 0;top: -40px;;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 165px;position: absolute;left: 0;right: 0;height: 165px;bottom: 0;top: -190px;margin: auto;" src="./images/ajax-loading.gif"/></div> -->
	 <div class="content" id="div1">
	   	<div id="dialog"></div>
	    <div class="searchWraps">
			<div class="col-md-12 input-group" id="div2">
				<p class="transportheader">
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Stage Details</span>
				</p>
			</div>

			
				<%-- <div class="input-group col-md-4">
					<input type="text" class="form-control" name="searchvalue" id="searchvalue" Placeholder="Search......" style="height:35px;" value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'> 
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" id="search" value="Submitform">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
				
				<input type="hidden" name="searchterm" class="searchtermclass" id="searchexamid"
					value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" /></logic:present>'></input> --%>	
			
		
		</div>
		
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
	
		
		<div class="panel panel-primary">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" style="color: #fff;">
					<h3 class="panel-title" style="color: #000;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Stage
						Details
					</h3>
				</a>

				<div class="navbar-right" style="margin-right:-2px;">
					<span class="buttons" id="add">New</a></span>  
					<span id="editStageId" class="buttons" >Modify</span>
					<span class="buttons" id="inactive">InActive</span>
					<span class="buttons" id="back1">Back</span>
				</div>
			</div>
					<div id="addStageDetails" style="display: none;">
					<input type="hidden" id="stageid" value='<logic:present name="StageLIst"><bean:write name="StageLIst" property="stageid"/></logic:present>'></input>
			        <input type="hidden" id="accyear" value="<logic:present name="accyId" scope="request"><bean:write name="accyId"></bean:write></logic:present>"/>
			        <input type="hidden" id="location" value="<logic:present name="locId" scope="request"><bean:write name="locId"></bean:write></logic:present>"/>
			        <input type="hidden" id="hiddenstagename">
								<div class="form-group clearfix">
									<label for="inputEmail"  class="control-label col-xs-4"
										style="text-align: right;font-size: 14px;font-family: Roboto,sans-serif;line-height: 35px;font-weight: lighter;">Stage<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" name="stagename" class="form-control" id="stagename" onclick="HideError1(this)" maxlength="99"
											 value='<logic:present name="StageLIst"><bean:write name="StageLIst" property="stage_name"/></logic:present>'></input> 						
											<!-- stage_id,stage_name -->
									</div>
								</div>

								<div class="form-group clearfix des">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right;font-size: 14px;font-family: Roboto,sans-serif; line-height: 35px;font-weight: lighter;">Description</label>
									<div class="col-xs-7">
										<textarea name="description" style="resize: none;height: 87px;" class="form-control" maxlength="249" id="description"><logic:present name="StageLIst"><bean:write name="StageLIst" property="stage_description"/></logic:present></textarea>
									</div>
								</div>
								
					</div>
		
		<input type="hidden" id="currentstatus" 
		value='<logic:present name="currentstatus" scope="request"><bean:write name="currentstatus"/></logic:present>'></input>
					
		<input type="hidden" id="historylocId" 
		value='<logic:present name="historylocId" scope="request"><bean:write name="historylocId"/></logic:present>'></input>			
					
			<div id="collapseOne" class="accordion-body collapse in">
			    <div class="panel-body">
							<div class="col-md-6" id="txtstyle">
			                   <div class="form-group clearfix">
									<label for="inputEmail"  class="control-label col-xs-4"
										style="text-align: right;  line-height: 35px;">Branch</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" onkeypress="HideError()" id="locname" readonly="readonly" style="background-color: #fff;"
											 value='<logic:present name="locname" scope="request"><bean:write name="locname"></bean:write></logic:present>'></input> 						
											<!-- stage_id,stage_name -->
									</div>
								</div>
						<div class="form-group clearfix">
						<label for="inputEmail"  class="control-label col-xs-4"
										style="text-align: right;  line-height: 35px;">Search</label>
										<div class="col-xs-7">
							<input type="text" class="form-control" name="searchvalue"
								id="searchvalue" Placeholder="Search......"
								style="height: 35px;"
								value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'>
								</div>
							<!-- <span class="input-group-btn">
								<button class="btn btn-default" type="button" id="search"
									value="Submitform">
									<i class="fa fa-search"></i>
								</button>
							</span> -->
						</div>
						<div class="form-group clearfix">
						<div class='col-xs-4'>
						</div>
					<div class='col-xs-7' style="text-align: left;">
					<span class="buttons" id="search" style="font-weight: normal;">Search</span>  
					<span class="buttons" id="resetbtn" style="font-weight: normal;">Reset</span>
					</div></div>
					</div>
				 <div class="col-md-6" id="txtstyle">
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-3"
							style="text-align: left; line-height: 35px;">Status</label>
						<div class="col-xs-7">
							<select id="status" name="status" class="form-control">
								<option value="Y">Active</option>
								<option value="N">InActive</option>
							</select>
						</div>
					</div>
				</div>
				  </div>
				<%-- <div class="col-md-6"  id="txtstyle" style="font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
					<div class="form-group clearfix" id="inputcolor">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Academic Year</label>
						<div class="col-xs-5">
						<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear"
									class="form-control" value='<logic:present name="accyName" scope="request"><bean:write name="accyName" ></bean:write></logic:present>' />
						</div>
						<input type="hidden" name="hiddenaccyid" id="hiddenaccyid" value='<logic:present name="accyId" scope="request"><bean:write name="accyId"></bean:write></logic:present>'/>
						<br />
					</div>
					<div class="form-group clearfix" id="inputcolor">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Branch</label>
						<div class="col-xs-5">
						<input type="text" readonly="readonly" name="location" onkeypress="HideError()" id="location"
									class="form-control" value='<logic:present name="locName" scope="request"><bean:write name="locName" ></bean:write></logic:present>' />
						</div>
						<input type="hidden" name="hiddenloc" id="hiddenloc" value='<logic:present name="locId" scope="request"><bean:write name="locId"></bean:write></logic:present>'/>
						<br />
					</div>
				</div> --%>
				<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
					<%-- <logic:present name="StageDetails" scope="request"> --%>
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th><input type='checkbox' name='selectall' style='text-align:center' id='selectall'/></th>
							<th style="width: 300px;">Stage Name</th>
							<th>Stage Amount</th>
							<th>Description</th>
							<th>Remarks</th>
							
							</tr>
							</thead>
							<tbody>
							<%-- <logic:iterate id="StageDetails" name="StageDetails">
								<tr>
								<td><input type='checkbox' name='select' class='select'  style='text-align:center' id='<bean:write name="StageDetails" property='stageCode'/>,<bean:write name="StageDetails" property='stageName'/>,<bean:write name="StageDetails" property='description'/>' /></td>								
								<td><bean:write name="StageDetails" property='stageName'/></td>
								<td><bean:write name="StageDetails" property='amount'/></td>
								<td><bean:write name="StageDetails" property='description'/></td> 
								<td><bean:write name="StageDetails" property='remarks'/></td>
								</tr>
								
							</logic:iterate> --%>
							</tbody>
						</table>
					<%-- </logic:present> --%>
					<div class='pagebanner' style="position: relative !important;">
					  <select id='show_per_page'>
						  <option value='50'>50</option>
						  <option value='100'>100</option>
						  <option value='200'>200</option>
						  <option value='300'>300</option>
						  <option value='400'>400</option>
						  <option value='500'>500</option>
					  </select>
						<span class='numberOfItem'></span>	
					</div>
					<div class='pagination pagelinks' style="margin-bottom: 10px;"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>