<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <script type="text/javascript" src="JS/backOffice/Settings/SubjectSylabusDisplay.js"></script>



</head>

<body>

	<div class="content" id="div1">
		<div id="dialog"></div>

			<input type="hidden" name="searchterm" class="searchtermclass"
				id="searchexamid"
				value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />
		</logic:present>'></input>

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
	    		<h3 class="panel-title" style="color: #000000;">Subject Details</h3>
			</div>
			<!-- pop up -->


			<div id="subjectOne" class="accordion-body collapse in">
				<div class="col-md-6"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">

					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Branch</label>
						<div class="col-xs-7">
							<select id="locationname" name="locationnid" class="form-control"
								required>
							
								<logic:present name="locationList">
									<logic:iterate id="Location" name="locationList">
										<option
											value="<bean:write name="Location" property="locationId"/>"><bean:write
												name="Location" property="locationName" /></option>
									</logic:iterate>
								</logic:present>
							</select>
						</div>
					</div>
                     
                     <div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Class</label>
						<div class="col-xs-7">
							<select name="classname" id="classname" class="form-control"
								onkeypress="HideError()">
								<option value="">ALL</option>
								

							</select>
						</div>
					</div>
				</div>
				
			<div class="col-md-6"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000; margin-top: 20px;">
	<input type="hidden" id="hiddenAcademicYear" value="<logic:present name="academic_year"><bean:write name="academic_year"/></logic:present>">
					
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Academic Year</label>
						<div class="col-xs-7">
							<select id="academicYear" name="academicYear"
								class="form-control">
                               <logic:present name="AccYearList">
									<logic:iterate id="academicYear" name="AccYearList">
										<option
											value="<bean:write name="academicYear" property="accyearId"/>"><bean:write
												name="academicYear" property="accyearname" /></option>
									</logic:iterate>
								</logic:present>
							</select>
						</div>
					</div>
					
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-5"
							style="text-align: right; line-height: 35px;">Specialization</label>
						<div class="col-xs-7">
							<select id="specialization" name="specialization"
								class="form-control">
								<option value="all">ALL</option>

							</select>
						</div>
					</div>
				
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h3 class="modal-title" id="myModalLabel">Download</h3>
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
				<div class="panel-body"
					style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

					<div id="collapseOne" class="accordion-body collapse in">
					<form>
						<div class="panel-body"
							style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">

						
								<table class="table" id="allstudent">
									<thead>
										<tr>
														<!--  <th><input type='checkbox' name='selectall'
															id='selectall' style='text-align: center'
															onClick='selectAll()' /></th>-->
														<th>SL No.</th>
														<th>Branch</th>
														<th>Class Name</th>
														<th>Subject Name</th>
														<th>Upload</th>
														<th>Download</th>
													</tr>
									</thead>
									
											<tbody>
												      <!--<tr>
												        <td>1</td>
														<td>Branch</td>
														<td>Subject Name</td>
														<td><div><input type="file" name="uploadLabSylabus" id ="uploadLabSylabus" /></div></td>
														<td><a href="#" ><img src="images/download.png"  height="28" width="30"></a></td>
												      </tr> -->
											</tbody>
										
								

								</table>

								<div class='pagebanner'>
									<select id='show_per_page'><option value='50'>50</option>
										<option value='100'>100</option>
										<option value='200'>200</option>
										<option value='300'>300</option>
										<option value='400'>400</option>
										<option value='500'>500</option></select> <span class='numberOfItem'></span>
								</div>
								<div class='pagination pagelinks' style="margin-bottom: 10px"></div>
							

						</div>
					</form>	
					</div>
				</div>
			</div>
			<input type="hidden" id='curr_loc' value='<logic:present name='curr_loc'><bean:write name='curr_loc'/></logic:present>'>
</div>
</div>
</body>
</html>
