
<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<!-- <link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
 --><link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="JS/backOffice/Library/AddSubCategoryType3.js"></script>
<style>
.buttons {
	vertical-align: 0px;
}

.navbar-right span:last-child {
	margin-right: 10px;
}
</style>
</head>
<body>
	<div class="col-md-10" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin-bottom: 5px; margin-top: 5px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">
				<logic:present name="category" scope="request">
					<bean:write name="category"></bean:write>
				</logic:present>
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
					class="successmessage"></span></a>
			</div>
		</div>
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion" href="#"
							style="color: #000000;vertical-align: text-top;"><h3 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;<logic:present name="category" scope="request"><bean:write name="category"></bean:write></logic:present>
						</h3></a>
					

					<div class="navbar-right">
						<span id="save" class="buttons" style="margin-right: -2px;">Save</span>

						<span id="" class="buttons" style="margin-right: 1px;"><a href="LibraryMenu.html?method=subCategoryType3">Back</a></span>
					</div>
				</div>
				<input type="hidden" id="hiddenSubcategoryId3"
					value='<logic:present name="editlist"><bean:write name="editlist" property="subcategory4_id" />	</logic:present>'></input>
				<input type="hidden" id="hiddenCategoryId"
					value='<logic:present name="editlist"><bean:write name="editlist" property="category_id" /> </logic:present>'></input>
				<input type="hidden" id="hiddenSubCategoryId"
					value='<logic:present name="editlist"><bean:write name="editlist" property="subcategory_id" /> </logic:present>'></input>
				<input type="hidden" id="hiddenSubCategoryId1"
					value='<logic:present name="editlist"><bean:write name="editlist" property="subcategory_id1" /> </logic:present>'></input>
				<input type="hidden" id="hiddenSubCategoryId2"
					value='<logic:present name="editlist"><bean:write name="editlist" property="subcategory_id2" /> </logic:present>'></input>
				 <input type="hidden" id="hiddenstatus" value='<logic:present name="editlist"><bean:write name="editlist" property="status" /></logic:present>'/>
				
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body clearfix" style="margin-top: 15px;">
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-bottom: 9px;">

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left; line-height: 35px;">Category
									Type Name</label>
								<div class="col-xs-7">
									<select id="cattypeid" name="status" class="form-control">
										<logic:present name="CategoryDetails"><option value="">----------Select----------</option>
											<logic:iterate id="Category" name="CategoryDetails">
												<option
													value="<bean:write name="Category" property="categorytypecode"/>">
													<bean:write name="Category" property="categorytypename" />
												</option>
											</logic:iterate></logic:present>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left; line-height: 35px;">Sub
									Category Type1 Name</label>
								<div class="col-xs-7">
									<select id="subcatid1" name="status" class="form-control">
										<option value="all">----------Select----------</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left; line-height: 35px;">Sub
									Category Type3 Code</label>
								<div class="col-xs-7">
									<input type="text" name="subcatcodeid3"
										  class="form-control"
										id="subcatcodeid3"
										value='<logic:present name="editlist"><bean:write name="editlist" property="subcategorytypecode3" /></logic:present>'></input>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left; line-height: 35px;">Sub
									Category Type3 Name</label>
								<div class="col-xs-7">
									<input type="text" name="categorytypename"
										  class="form-control"
										id="subcatnameid3"
										value='<logic:present name="editlist"><bean:write name="editlist" property="subcategorytypename3" /></logic:present>'></input>
								</div>
							</div>
						</div>
						<div class="col-md-6"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; padding-bottom: 9px;">

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left; line-height: 35px;">Sub
									Category Type Name</label>
								<div class="col-xs-7">
									<select id="subcatid" name="status" class="form-control">
										<option value="all">----------Select----------</option>
									</select>
								</div>
							</div>
							<%-- <input type="hidden" id="hiddencat" value='<logic:present name="editlist"><bean:write name="editlist" property="category_code" /></logic:present>'/>
									<input type="hidden" id="hiddensubcat" value='<logic:present name="editlist"><bean:write name="editlist" property="subcategory_code" /></logic:present>'/>
									<input type="hidden" id="hiddensubcat1" value='<logic:present name="editlist"><bean:write name="editlist" property="subcategory_code1" /></logic:present>'>
								    <input type="hidden" id="hiddensubcat2" value='<logic:present name="editlist"><bean:write name="editlist" property="subcategory_code2" /></logic:present>'>	
									<input type="hidden" id="hiddenstatus" value='<logic:present name="editlist"><bean:write name="editlist" property="status" /></logic:present>'/> --%>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left; line-height: 35px;">Sub
									Category Type2 Name</label>
								<div class="col-xs-7">
									<select id="subcatid2" name="status" class="form-control">
										<option value="">----------Select----------</option>
									</select>
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: left; line-height: 35px;">Status</label>
								<div class="col-xs-7">
									<select id="status" name="status" class="form-control">
											<logic:present name="editlist"><bean:write name="editlist" property="status" /></logic:present>
										<option value="Active" selected="selected">Active</option>
										<option value="Inactive">Inactive</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: left; line-height: 35px;">Description
								</label>
								<div class="col-xs-7">
									<textarea style="resize: none" rows="4" cols="25"
										class="form-control" name="description" id="description"><logic:present name="editlist"><bean:write name="editlist" property="description" /></logic:present></textarea>
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


