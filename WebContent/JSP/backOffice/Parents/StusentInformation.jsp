<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="JS/backOffice/Parents/StudentInformation.js"></script>

<style>
.save:hover {
	cursor: pointer;
}
.borderless td,.borderless tbody{
    border: none !important;
    font-weight: 600;
}

.borderless tr td:nth-child(2) { width: 10px; }
.borderless tr td:first-child { width: 150px; }

textarea{
	border: none;
}
</style>

</head>

<body>
	<div class="col-md-10" id="div-main"
		style="font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<div class="col-md-7" id="div2">
				<p style="margin: 20px 0px;">
				<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Student Information</span>
				</p>
		</div>

		<div class="input-group col-md-5" style="margin: 20px 0px;">
			<div class="form-group">
				<label for="inputPassword" class="control-label col-xs-3" style="text-align: left; line-height: 35px; color: #000; font-size: 13px; font-family: Roboto,sans-serif;">Student</label>
				<div class="col-xs-7">
							<select class="form-control" name="studSectionId" id="parentchild">
								<logic:present name="studentlist" scope="request">
 									<logic:iterate id="stream" name="studentlist" scope="request">
										<option value='<bean:write name='stream' property='studentid'/>'>
										<bean:write name='stream' property='studentFnameVar'/></option>
								   </logic:iterate>
								   </logic:present>
						   </select>
				   
				</div>
			</div>
		</div>
	<input type="hidden" name="hiddenid" class="hiddenclass" id="stuhiddenid" value='<logic:present name="studentexam"><bean:write name="studentexam" /></logic:present>'></input>
	<input	type="hidden" name="parenthidden" id="parenthidden" value="<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="parentID"/></logic:present>"/>	
	<input	type="hidden" name="hiddenstudentid" id="stuinfostudentId" value='<logic:present name="studentexam"><bean:write name="studentexam" /></logic:present>'></input>		
	
			<logic:present name="successMessage" scope="request">

				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
									name="successMessage" scope="request" />
						</span></a>
					</div>
				</div>

			</logic:present>

			<logic:present name="errorMessage" scope="request">

				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
									name="errorMessage" scope="request" />
						</span></a>
					</div>

				</div>

			</logic:present>

			<logic:present name="duplicateMessage" scope="request">

				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
									name="duplicateMessage" scope="request" />
						</span></a>
					</div>

				</div>

			</logic:present>

			<div class="errormessagediv1" style="display: none;">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>

			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>
	
		<form action="" enctype="multipart/form-data" id="formstudent" method="post">
					
		<div class="panel-group" id="accordion" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
    		<div class="panel panel-primary">
     		 	<div class="panel-heading">
        	 		<h4 class="panel-title" style="color: #000000;">
         	 		<a data-toggle="collapse" data-parent="#accordion" href="#collapse1"><span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Student Details</a>
       				</h4>
      			</div>
      			<div id="collapse1" class="panel-collapse collapse in">
        			<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
        				<div class='col-md-12' style='margin-bottom: 10px;margin-top: 5px;'>
        					
									<div style="border: 1px solid #B3B0B0;width: 160px; height: 160px;border-radius: 50%;margin: auto;text-align: center;">
										<img id="imagePreview" class="setImage" alt="image" src="<logic:present name="studentdetails" scope="request"><bean:write name="studentdetails" property="studentPhoto"></bean:write></logic:present>" style="height: 42mm; width: 42mm;border-radius: 50%;">
									<div style="position: absolute;top: 0;height: 160px;">
									</div>
									</div>
        				</div>
        				<div class="col-md-1"></div>
        				<div class="col-md-5">
							<table class='table borderless'>
								<tr>
									<td>First Name</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="studentFirstName"></bean:write></logic:present></td>
								</tr>
								<tr>
									<td>Last Name</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="studentLastName"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Branch</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="schoolLocation"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Admission No</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="studentAdmissionNo"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Admission Date</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="dateofJoin"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Application No</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="applicationNo"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Academic Year</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="academicYear"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>First Language</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="firstlang"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Second Language</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="secondlang"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Third Language</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="thirdlang"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Roll No.</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="rollNo"></bean:write>
									</logic:present></td>
								</tr>
							</table>
							</div>
							<div class="col-md-1"></div>
							<div class="col-md-5" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
							<table class='table borderless'>	
								<tr>
									<td>Transport</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="transport"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Transport Location</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="transportlocationName"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Route</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="route"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Stream</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="category"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Class</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="classname"></bean:write>
									</logic:present></td>
								</tr>
								<tr>
									<td>Division</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="sectionnaem"></bean:write>
									</logic:present></td>
								</tr>
									<tr>
									<td>Specialization</td>
									<td>:</td>
									<td><logic:present name="studentdetails"  scope="request">
										<bean:write name="studentdetails" property="specilization"></bean:write>
									</logic:present></td>
								</tr>
							</table>
							</div>
        				</div>
     				</div>
    			</div>
    		<div class='panel panel-primary'>
      			<div class="panel-heading">
        			<h4 class="panel-title" style="color: #000000;">
         			 <a data-toggle="collapse" data-parent="#accordion" href="#collapse2"><span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Personal Information</a>
       				</h4>
      			</div>
      			<div id="collapse2" class="panel-collapse collapse">
        			<div class="panel-body">
        			<div class="col-md-1"></div>
        			<div class="col-md-5">
					<table class='table borderless'>
					<tr>
						<td>Date Of Birth</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="dateofBirth"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Gender</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="gender"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Blood Group</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="bloodGroup"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Nationality</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="nationality"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Mother Tongue</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="mothertongue"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Aadhar No</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="aadharno"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Physically Challenged</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="physicallyChallenged"></bean:write></logic:present></td>
					</tr>
					</table>
				</div>
				<div class="col-md-1"></div>
				<div class="col-md-5">
					<table class='table borderless'>
					<tr>
						<td>Age</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="age"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Religion</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="religion"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Caste</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="studentcastename"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Caste Category</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="casteCategory"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Identification Marks1</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="identificationMarks"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Identification Marks2</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="identificationMarks1"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Medical History</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="previousHistory"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Remark's</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="remarks"></bean:write></logic:present></td>
					</tr>
					</table>
					</div>
       			</div>
      		 </div>
    	   </div>
  			<div class="panel panel-primary">
     		 	<div class="panel-heading">
        			 <h4 class="panel-title" style="color: #000000;">
         			 <a data-toggle="collapse" data-parent="#accordion" href="#collapse3"><span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Parent Information</a>
       		 		</h4>
      			</div>
      			<div id="collapse3" class="panel-collapse collapse">
        		<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
        		<div class="col-md-12">
        		<div class="col-md-1"></div>
				<div class="col-md-5">
				<table class='table borderless'>
					<tr>
						<td>Sibling Name</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="sibilingName"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Sibling Class</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="sibilingClass"></bean:write></logic:present></td>
					</tr>
				</table>
				</div>
				<div class="col-md-1"></div>
				<div class="col-md-5">
				<table class='table borderless'>
					<tr>
						<td>Admission</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="studentSibilingRegNo"></bean:write></logic:present></td>
					</tr>
				</table>
				</div>	
				</div>	
				<div class="col-md-12">	
				<div class="col-md-1"></div>	
				<div class="col-md-5">
				<table class='table borderless'>
					<tr>
						<td>Father's Name</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="fatherName"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Mobile No.</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="fatherMobileNo"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Occupation</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="fatheroccupation"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>PAN No.</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="fatherPanNo"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Annual Income</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="fatherAnnualIncome"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Email</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="fatheremailId"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Qualification</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="fatherQualification"></bean:write></logic:present></td>
					</tr>
					<tr>
					<td>Guardian's Name</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="guardianName"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>Mobile No.</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="guardianMobileNo"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>Email</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="guardianemailId"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>Occupation</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="guardianOccupation"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>PAN No.</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="guardianPanNo"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>Annual Income</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="guardianAnnualIncome"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>Email</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="guardianemailId"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>Qualification</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="guardianQualification"></bean:write></logic:present></td>
				</tr>
				</table>			
				</div>
				<div class="col-md-1"></div>
				<div class="col-md-5">
				<table class='table borderless'>
				<tr>
					<td>Mother's Name</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="motherName"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>Mobile No.</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="motherMobileNo"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>Occupation</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="motheroccupation"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>PAN No.</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="motherPanNo"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>Annual Income</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="motherAnnualIncome"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>Email</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="motheremailId"></bean:write></logic:present></td>
				</tr>
				<tr>
					<td>Qualification</td>
					<td>:</td>
					<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="motherQualification"></bean:write></logic:present></td>
				</tr>
				<tr>
						<td>Primary Person</td>
						<td>:</td>
						<td><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="primaryPerson"></bean:write></logic:present></td>
					</tr>
					<tr>
						<td>Permanent Address</td>
						<td>:</td>
						<td><textarea><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="address"></bean:write></logic:present></textarea></td>
					</tr>
					<tr>
						<td>Present Address</td>
						<td>:</td>
						<td><textarea><logic:present name="studentdetails"  scope="request"><bean:write name="studentdetails" property="presentaddress"></bean:write></logic:present></textarea></td>
					</tr>
				</table>	
			</div>
			</div>
		</div>
        </div>
        </div> 
    		<div class="panel panel-primary">
     		 	<div class="panel-heading">
        	 		<h4 class="panel-title" style="color: #000000;">
         	 		<a data-toggle="collapse" data-parent="#accordion" href="#collapse4"><span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Document Details</a>
       			 	</h4>
      			</div>
      		<div id="collapse4" class="panel-collapse collapse">
        	<div class="panel-body" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;">
        		<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
					<div class="form-group ">
						<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Birth Certificate</label>
						<input type="button" id="document2btn" name="idproof" class="downloadDoc" value="Download" />
						<span id="downloadIdTitle">downloadBirthCertificate</span>
					</div>
				</div>
				<div class="col-md-6" style="font-family: Roboto,sans-serif; font-size: 13px; color: #000;margin-top: 10px;">
					<div class="form-group ">
						<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Transfer Certificate</label>
						<input type="button" id="document1btn" name="profile" class="downloadDoc" value="Download" />
						<span id="downloadProfileTitle">downloadTransferCertificate</span>
					</div>
				</div>
        	</div>
     		</div>
    		</div>
  		</div>
		</form>	
					<input type="hidden" name="parentId" id="parentId" />
					<input type="hidden" name="sibilingClassId" id="sibilingClassID" />
					<input type="hidden" name="selected_Primary_Id" id="selected_Primary_Id" />
					<input type="hidden" name='' id="trnporttypestatus" />
					<input type="hidden" name="studentIDgenerator" id="studentIDgeneratorId" />
					<input type="hidden" name="previousParentId" id="previousParentId" />
					<input type="hidden" name="defaultimage" id="hiddenimage"   value="<logic:present name="studentdetails" ><bean:write name="studentdetails" property="studentPhoto" /></logic:present>"  />
					<input type="hidden" name="defaultidproof"	 id="hiddenidproof" value="<logic:present name="studentdetails" ><bean:write name="studentdetails" property="transferfile" /></logic:present>"  />
					<input type="hidden" name="defaultprofile"	 id="hiddenprofile" value="<logic:present name="studentdetails" ><bean:write name="studentdetails" property="birthcertificate" /></logic:present>" />				
					<input type="hidden" name="certificate1"	 id="certificate1" value="<logic:present name="studentdetails" ><bean:write name="studentdetails" property="certificate1" /></logic:present>"  />
					<input type="hidden" name="certificate2"	 id="certificate2" value="<logic:present name="studentdetails" ><bean:write name="studentdetails" property="certificate2" /></logic:present>" />				
					<input type="hidden" name="certificate3"	 id="certificate3" value="<logic:present name="studentdetails" ><bean:write name="studentdetails" property="certificate3" /></logic:present>" />
					<input type="hidden" name="certificate4"	 id="certificate4" value="<logic:present name="studentdetails" ><bean:write name="studentdetails" property="certificate4" /></logic:present>" />
					<input type="hidden" name="certificate5"	 id="certificate5" value="<logic:present name="studentdetails" ><bean:write name="studentdetails" property="certificate5" /></logic:present>" />
					<input type="hidden" name="studentid" id="studentid" value="<logic:present name="studentid" ><bean:write name="studentid" /></logic:present>" />
				</div>
	</body>
</html>
