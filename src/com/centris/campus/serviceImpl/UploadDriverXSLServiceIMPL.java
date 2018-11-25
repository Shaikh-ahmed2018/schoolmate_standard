package com.centris.campus.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.TransportDaoImpl;
import com.centris.campus.daoImpl.UploadDriverXLSDaoImpl;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.pojo.UploadDriverXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.UploadDriverXSLservice;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.UploadDriverXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;

public class UploadDriverXSLServiceIMPL implements UploadDriverXSLservice {

	private static Logger logger = Logger.getLogger(UploadDriverXSLServiceIMPL.class);

	public Set<UploadDriverXlsVO> insertDriverXSL(List<UploadDriverXlsPOJO> list, String username, String duplicate,
			String log_audit_session, UserLoggingsPojo userLoggingsVo,String branchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in UploadStageXSLServiceImpl : insertStageXSL : Starting");

		Connection connection = null;

		List<UploadDriverXlsVO> successlist = new ArrayList<UploadDriverXlsVO>();
		UploadDriverXLSDaoImpl daoImpl = new UploadDriverXLSDaoImpl();

		Set<UploadDriverXlsVO> failurelist = new LinkedHashSet<UploadDriverXlsVO>();

		String schoolLocationId = null, licencevalidation = null, success = null;

		failurelist.clear();
		successlist.clear();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String dateOfBirth = null,val=null,gender="";
		Boolean dobisvalid = true,validFatherName=true, validDriveName=true,dojisvalid = true, dlValidateUpTo = true, licenceToDriver = true,gendervalid=true,validexpe=false;
		int yearval = 0;

		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);

			String string_regx = "([a-zA-Z.]+\\s+)*[a-zA-Z.]+";
			String int_regex = "^[0-9]*$";
			String experience = "^[0-9.]*$";
			String valid_experience = "(^[0-9]*$)\\.(^[0-9]*$){1}"; 

			for (Iterator<UploadDriverXlsPOJO> iterator = list.iterator(); iterator.hasNext();) 
			{
				UploadDriverXlsPOJO uploadDriverXSLPOJO = (UploadDriverXlsPOJO) iterator.next();

				UploadDriverXlsVO uploadDriverXSLVo = new UploadDriverXlsVO();

				uploadDriverXSLVo.setDriverName(uploadDriverXSLPOJO.getDriverName());
				uploadDriverXSLVo.setDrivingLicenseNo(uploadDriverXSLPOJO.getDrivingLicenseNo());
				uploadDriverXSLVo.setLicenseToDrive(uploadDriverXSLPOJO.getLicenseToDrive());
				
				
				schoolLocationId = new UploadStudentXLSDaoImpl().getSchoolLocationId(uploadDriverXSLPOJO.getLocationnid(), connection);
				licencevalidation = new TransportDaoImpl().getLicenceValidationCount(schoolLocationId,uploadDriverXSLPOJO.getDrivingLicenseNo(),connection);

				uploadDriverXSLPOJO.setCreatedby(username);
				
				if(uploadDriverXSLPOJO.getDriverName().matches("^[ A-Za-z]*$")){
					validDriveName=false;
				}
				if(uploadDriverXSLPOJO.getFatherName().matches("^[ A-Za-z]*$")){
					validFatherName=false;
				}
				
				dobisvalid = HelperClass.DateValidator(uploadDriverXSLPOJO.getDob().replace("-", "/"));
				dojisvalid = HelperClass.DateValidator(uploadDriverXSLPOJO.getDoj().replace("-", "/"));
				dlValidateUpTo = HelperClass.DateValidator(uploadDriverXSLPOJO.getDrivingLicenseValidityDate().replace("-", "/"));

				if((uploadDriverXSLPOJO.getDob().trim()!="" || !uploadDriverXSLPOJO.getDob().equalsIgnoreCase("") )&&(dobisvalid == true)){
					dateOfBirth = uploadDriverXSLPOJO.getDob().replace("/", "-");
					dateOfBirth = dateOfBirth.split("-")[2];
					
					val = String.valueOf(year - Integer.parseInt(dateOfBirth));
					yearval = year - Integer.parseInt(dateOfBirth);
				}
				

				if (!uploadDriverXSLPOJO.getExperiance().matches("^[.0-9]*")) {
					validexpe = true;
				}
				if (uploadDriverXSLPOJO.getLicenseToDrive().equalsIgnoreCase("CYCL") || uploadDriverXSLPOJO.getLicenseToDrive().equalsIgnoreCase("LMV") || uploadDriverXSLPOJO.getLicenseToDrive().equalsIgnoreCase("HMV")) {
					licenceToDriver = false;
				}
				if (uploadDriverXSLPOJO.getGender().equalsIgnoreCase("male") || uploadDriverXSLPOJO.getGender().equalsIgnoreCase("female")) {
					gendervalid = false; 
				}
				if (uploadDriverXSLPOJO.getGender().equalsIgnoreCase("male")) {
					gender = "Male"; 
				}else if (uploadDriverXSLPOJO.getGender().equalsIgnoreCase("female")){
					gender = "Fale";
				}
				if (uploadDriverXSLPOJO.getLocationnid().trim() == "" || uploadDriverXSLPOJO.getLocationnid() == null) {
					uploadDriverXSLVo.setReason("Branch Name Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (schoolLocationId.trim() == "" || schoolLocationId.equalsIgnoreCase("notfound") || schoolLocationId == null) {
					uploadDriverXSLVo.setReason("Enter valid Branch Name");
					failurelist.add(uploadDriverXSLVo);
				}  
				else if (!schoolLocationId.trim().equals(branchName)) {
					uploadDriverXSLVo.setReason("Change Branch to "+uploadDriverXSLPOJO.getLocationnid().trim());
					failurelist.add(uploadDriverXSLVo);
				}
				else if (uploadDriverXSLPOJO.getDriverName().trim() == "" || uploadDriverXSLPOJO.getDriverName().equals("-") || uploadDriverXSLPOJO.getDriverName() == null) {
					uploadDriverXSLVo.setReason("Driver Name Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				}  
				else if (validDriveName)
				{
					uploadDriverXSLVo.setReason("Driver Name Should contains only alphabets");
					failurelist.add(uploadDriverXSLVo);
				}
				else if (validFatherName)
				{
					uploadDriverXSLVo.setReason("Father Name Should contains only alphabets"); 
					failurelist.add(uploadDriverXSLVo);
				}
				else if (uploadDriverXSLPOJO.getFatherName().trim() == "" || uploadDriverXSLPOJO.getFatherName().equals("-") || uploadDriverXSLPOJO.getFatherName() == null) {
					uploadDriverXSLVo.setReason("Father Name Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (uploadDriverXSLPOJO.getDob().trim() == "" || uploadDriverXSLPOJO.getDob() == null)
				{
					uploadDriverXSLVo.setReason("Date Of Birth Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				}
				else if (uploadDriverXSLPOJO.getGender().trim() == "" || uploadDriverXSLPOJO.getGender() == null)
				{
					uploadDriverXSLVo.setReason("Gender Should Not Empty"); 
					failurelist.add(uploadDriverXSLVo);
				}
				else if (gendervalid) {
					uploadDriverXSLVo.setReason("Gender Should be 'Male' or 'Female'");
					failurelist.add(uploadDriverXSLVo);
				}
				else if (dobisvalid == false) {
					uploadDriverXSLVo.setReason("Enter valid Date Of Birth");
					failurelist.add(uploadDriverXSLVo);
				}
				else if (yearval < 18) {
					uploadDriverXSLVo.setReason("Driver Age should be greater than 18 year..Enter valid Date Of Birth");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (!(uploadDriverXSLPOJO.getMobile().matches(int_regex))) {
					uploadDriverXSLVo.setReason("Enter Valid Mobile Number");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (!(uploadDriverXSLPOJO.getMobile().matches(int_regex))) {
					uploadDriverXSLVo.setReason("Enter Valid Mobile Number");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (!(uploadDriverXSLPOJO.getEmergencyContactNo().matches(int_regex))) {
					uploadDriverXSLVo.setReason("Enter Valid Emergency Contact Number");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (uploadDriverXSLPOJO.getDoj().trim() == "" || uploadDriverXSLPOJO.getDoj() == null) {
					uploadDriverXSLVo.setReason("Date Of Join Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (dojisvalid == false) {
					uploadDriverXSLVo.setReason("Enter valid Date Of Joining");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (uploadDriverXSLPOJO.getExperiance().trim() == "" || uploadDriverXSLPOJO.getExperiance() == null) {
					uploadDriverXSLVo.setReason("Experiance Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (validexpe) {
					uploadDriverXSLVo.setReason("Enter valid Experience");
					failurelist.add(uploadDriverXSLVo);
				}
				else if ((double) (year - Integer.parseInt(dateOfBirth)) < Double
						.parseDouble(uploadDriverXSLPOJO.getExperiance())) {
					uploadDriverXSLVo.setReason("Enter valid Experience");
					failurelist.add(uploadDriverXSLVo);
				}
				else if (!(uploadDriverXSLPOJO.getExperiance().matches(experience))) {
					uploadDriverXSLVo.setReason("Enter valid Experience");
					failurelist.add(uploadDriverXSLVo);
				}
				else if (uploadDriverXSLPOJO.getAddress().trim() == "" || uploadDriverXSLPOJO.getAddress() == null) {
					uploadDriverXSLVo.setReason("Address Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (uploadDriverXSLPOJO.getAddress().length() < 8) {
					uploadDriverXSLVo.setReason("Enter Address Should be contain minimum 10 charecters");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (uploadDriverXSLPOJO.getDrivingLicenseNo().trim() == "" || uploadDriverXSLPOJO.getDrivingLicenseNo().equals("-") || uploadDriverXSLPOJO.getDrivingLicenseNo() == null) {
					uploadDriverXSLVo.setReason("Driver License No Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				}
				else if (uploadDriverXSLPOJO.getDrivingLicenseNo().trim() == "" || uploadDriverXSLPOJO.getDrivingLicenseNo() == null) {
					uploadDriverXSLVo.setReason("Driving License No Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (licencevalidation.equalsIgnoreCase("notfound") || licencevalidation.equalsIgnoreCase("")) {
					uploadDriverXSLVo.setReason("Enter valid Driving License No.");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (uploadDriverXSLPOJO.getDrivingLicenseValidityDate().trim() == ""
						|| uploadDriverXSLPOJO.getDrivingLicenseValidityDate() == null) {
					uploadDriverXSLVo.setReason("DL VALIDITY UPTO Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (dlValidateUpTo == false) {
					uploadDriverXSLVo.setReason("Enter valid DL VALIDITY UPTO Date");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (uploadDriverXSLPOJO.getLicenseToDrive().trim() == ""
						|| uploadDriverXSLPOJO.getLicenseToDrive() == null) {
					uploadDriverXSLVo.setReason("LICENSE TO DRIVE Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				} 
				else if (licenceToDriver) {
					uploadDriverXSLVo.setReason("LICENSE TO DRIVE Should be Like 'CYCL' or 'LMV' or 'HMV'");
					failurelist.add(uploadDriverXSLVo);
				} 
				else {
					String val1=null;
					if(!uploadDriverXSLPOJO.getExperiance().contains(".")){
						val1=uploadDriverXSLPOJO.getExperiance();
					}else{
						val1=uploadDriverXSLPOJO.getExperiance().substring(0,uploadDriverXSLPOJO.getExperiance().indexOf(".")+2);
					}
					
					uploadDriverXSLVo.setLocationnid(schoolLocationId);
					uploadDriverXSLVo.setCreatedby(username);
					uploadDriverXSLVo.setDriverName(uploadDriverXSLPOJO.getDriverName());
					uploadDriverXSLVo.setFatherName(uploadDriverXSLPOJO.getFatherName());
					uploadDriverXSLVo.setEmergencyContactNo(uploadDriverXSLPOJO.getEmergencyContactNo());
					uploadDriverXSLVo.setGender(gender);
					uploadDriverXSLVo.setDob(uploadDriverXSLPOJO.getDob());
					uploadDriverXSLVo.setAge(val);
					uploadDriverXSLVo.setMobile(uploadDriverXSLPOJO.getMobile());
					uploadDriverXSLVo.setDrivingLicenseNo(uploadDriverXSLPOJO.getDrivingLicenseNo());
					uploadDriverXSLVo.setLicenseToDrive(uploadDriverXSLPOJO.getLicenseToDrive());
					uploadDriverXSLVo.setDoj(uploadDriverXSLPOJO.getDoj());
					uploadDriverXSLVo.setExperiance(val1);
					uploadDriverXSLVo.setAddress(uploadDriverXSLPOJO.getAddress());
					uploadDriverXSLVo.setDrivingLicenseValidityDate(uploadDriverXSLPOJO.getDrivingLicenseValidityDate());
					successlist.add(uploadDriverXSLVo);
				}
			}
			
			Set<UploadDriverXlsVO> failureListFromDiompl = new LinkedHashSet<UploadDriverXlsVO>();

			if (successlist.size() > 0) {
				failureListFromDiompl = daoImpl.insertDriverXSL(successlist, connection, log_audit_session,userLoggingsVo);
			}

			for (Iterator<UploadDriverXlsVO> it = failureListFromDiompl.iterator(); it.hasNext();) {
				UploadDriverXlsVO failureDiomplVo = (UploadDriverXlsVO) it.next(); 
				UploadDriverXlsVO uploadDriverXSLVo = new UploadDriverXlsVO();
				
				uploadDriverXSLVo.setDriverName(failureDiomplVo.getDriverName());
				uploadDriverXSLVo.setDrivingLicenseNo(failureDiomplVo.getDrivingLicenseNo());
				uploadDriverXSLVo.setLicenseToDrive(failureDiomplVo.getLicenseToDrive());
				uploadDriverXSLVo.setReason(failureDiomplVo.getReason());

				failurelist.add(uploadDriverXSLVo);
			}

		} catch (SQLException sqle) {

			sqle.printStackTrace();
			logger.error(sqle.getMessage(), sqle);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);

		} finally {

			try {

				if (connection != null && (!connection.isClosed())) {

					connection.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in UploadStageXSLServiceImpl : insertStageXSL : Ending");

		return failurelist;
	}

}
