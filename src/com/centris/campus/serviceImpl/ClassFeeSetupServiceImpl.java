package com.centris.campus.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ClassFeeSetupDao;
import com.centris.campus.daoImpl.ClassFeeSetupDaoImpl;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ClassFeeSetupService;

import com.centris.campus.util.FeeExcelUploadUtil;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ClassFeeSetupVo;



public class ClassFeeSetupServiceImpl implements ClassFeeSetupService {

	private static final Logger logger = Logger.getLogger(ClassFeeSetupServiceImpl.class);

	static ClassFeeSetupDao dao = null;
	static{
		dao = new ClassFeeSetupDaoImpl();
	}
	 
	public ArrayList<ClassFeeSetupVo> getFeeSetupDetails(String currentaccyear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getFeeSetupDetails Starting");
		ArrayList<ClassFeeSetupVo> feelist = null;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		try {
			feelist = dao.getFeeSetupDetails(currentaccyear, userLoggingsVo);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getFeeSetupDetails Ending");
		return feelist;
	}



	@Override
	public ArrayList<ClassFeeSetupVo> getSearchFeeSetupDetails(String searchTerm,String currentaccyear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getSearchFeeSetupDetails Starting");
		
		ArrayList<ClassFeeSetupVo> feelist = null;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			feelist = dao.getSearchFeeSetupDetails(searchTerm,currentaccyear, userLoggingsVo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getSearchFeeSetupDetails Ending");
		return feelist;
	}

	public ArrayList<ClassFeeSetupVo> getApprovedFees(ClassFeeSetupPojo feeSetupPojo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getApprovedFees Starting");
		
		ArrayList<ClassFeeSetupVo> feelist = null;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			feelist = dao.getApprovedFees(feeSetupPojo,userLoggingsVo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getApprovedFees Ending");
	
		return feelist;
	}



	@Override
	public ArrayList<ClassFeeSetupVo> getAllFees(ClassFeeSetupPojo feeSetupPojo,String location, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getAllFees Starting");
		
		ArrayList<ClassFeeSetupVo> feelist = null;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			feelist = dao.getAllFees(feeSetupPojo,location,userLoggingsVo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getAllFees Ending");
	
		return feelist;
	}
	
	public int insertApproveFees(ArrayList<ClassFeeSetupPojo> approvefeelist,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertApproveFees Starting");
		
		int count=0;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			count = dao.insertApproveFees(approvefeelist,userLoggingsVo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertApproveFees Ending");
	
		return count;
	}


	@Override
	public boolean deleteFees(ClassFeeSetupPojo feeSetupPojo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : deleteFees Starting");
		
		boolean flag=false;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			flag = dao.deleteFees(feeSetupPojo,userLoggingsVo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : deleteFees Ending");
	
		return flag;
	}



	@Override
	public int insertFeeAmount(ArrayList<ClassFeeSetupPojo> feeSetupList,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertFeeAmount Starting");
		
		int count=0;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			count = dao.insertFeeAmount(feeSetupList,userLoggingsVo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertFeeAmount Ending");
	
		return count;
	}



	@Override
	public ArrayList<ClassFeeSetupVo> getHeading(ClassFeeSetupPojo feeSetupPojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getHeading Starting");
		
		ArrayList<ClassFeeSetupVo> feelist = null;
		ClassFeeSetupDao dao = new ClassFeeSetupDaoImpl();
		
		try {
			
			feelist = dao.getHeading(feeSetupPojo,userLoggingsVo);
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : getHeading Ending");
	
		return feelist;
	}

	@Override
	public String getSpecialization(String classId, String accYear) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ClassFeeSetupVo> insertStudentXSL(List<ClassFeeSetupPojo> list, String username,
			String demo,String log_audit_session,UserLoggingsPojo userLoggingsVo,String branchName){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertStudentXSL Starting");
		
		List<ClassFeeSetupPojo> successlist=new ArrayList<ClassFeeSetupPojo>();
		ArrayList<ClassFeeSetupVo> getstudentList = new ArrayList<ClassFeeSetupVo>();
		Connection connection = null;
		
		FeeExcelUploadUtil sqlutil= new FeeExcelUploadUtil();
		Set<ClassFeeSetupVo> failurelist = new LinkedHashSet<ClassFeeSetupVo>();
		UploadStudentXLSDaoImpl daoImpl=new UploadStudentXLSDaoImpl();
		String student =null;
		String accyearId = null;
		String classid =null;
		String termid =null;
		String studentdetails =null;
		String checkfeesettingcode = null;
		failurelist.clear();
		successlist.clear();
	String dbl="^[0-9]*(\\.[0-9]{1,4})?$";
	/*String datePattern = "\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
	String datePattern1 = "\\d{1,2}\\/\\d{1,2}\\/\\d{4}";
	String string_regx="([a-zA-Z.,-]+\\s+)*[a-zA-Z.,-]+";*/
		try{
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			for (Iterator<ClassFeeSetupPojo> iterator = list.iterator(); iterator.hasNext();) {
			
				
				ClassFeeSetupPojo uploadFeespojo = (ClassFeeSetupPojo) iterator.next();
				
				//System.out.println("*-*--*--*----*"+uploadFeespojo.getDdDate());
				
				String ddDate = null;
				String ddFlag = "false";
				String locationid="";
				if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("cheque")) && !uploadFeespojo.getDdDate().equalsIgnoreCase(" ")){
					ddDate=HelperClass.convertExceltoUIformat(uploadFeespojo.getDdDate());
					ddFlag=HelperClass.validateDate(ddDate);
				}
				String paymentDate = null;
				String paymentFlag = "false";
				
				if(uploadFeespojo.getPaidDate()!=null && !(uploadFeespojo.getPaidDate().trim().equalsIgnoreCase(""))){
					//System.out.println("uploadFeespojo.getPaidDate() "+uploadFeespojo.getPaidDate()); 
					paymentDate=HelperClass.convertExceltoUIformat(uploadFeespojo.getPaidDate());
					 paymentFlag=HelperClass.validateDate(paymentDate);
				}
				
				uploadFeespojo.setCreatedBy(username);
				ClassFeeSetupVo classFeeSetupVo = new ClassFeeSetupVo();
				classFeeSetupVo.setAdmissionNo(uploadFeespojo.getAdmissionNo());
				classFeeSetupVo.setAcadamicyear(uploadFeespojo.getAcadamicYear());
				classFeeSetupVo.setLocationName(uploadFeespojo.getLocation_id());
				classFeeSetupVo.setTermid(uploadFeespojo.getTermId());
				classFeeSetupVo.setFeeamount(uploadFeespojo.getTotalfee());
				classFeeSetupVo.setPaymentdate(paymentDate);
				classFeeSetupVo.setPaymentmode(uploadFeespojo.getPaymentmode());
				classFeeSetupVo.setParticularNo(uploadFeespojo.getParticularNo());
				classFeeSetupVo.setBankname(uploadFeespojo.getBankName());  
				classFeeSetupVo.setLog_audit_session(log_audit_session);
				classFeeSetupVo.setDddate(ddDate);
				
				if(!uploadFeespojo.getLocation_id().equalsIgnoreCase("")){
					locationid = daoImpl.getSchoolLocationId(uploadFeespojo.getLocation_id(),connection);
				}
				
				getstudentList.add(classFeeSetupVo);
				if(!uploadFeespojo.getAdmissionNo().equalsIgnoreCase("")){
					student = sqlutil.checkStudentAdmin(uploadFeespojo.getAdmissionNo(),locationid,userLoggingsVo);
				}
				System.out.println(student);
				
				
				
				if(student!=null && !student.equalsIgnoreCase("notfound")){
					/*int count=0;*/
					if(!uploadFeespojo.getAcadamicYear().equalsIgnoreCase("")){
						accyearId = daoImpl.getAcademicYearId(uploadFeespojo.getAcadamicYear(),connection);
						
					}
				/*	if(accyearId !=null && !accyearId.equalsIgnoreCase("notfound") && !locationid.equalsIgnoreCase("notfound")){
						count = daoImpl.validateStudent(student,accyearId,locationid);
					}*/
					if(accyearId !=null && !accyearId.equalsIgnoreCase("notfound")){
						studentdetails = daoImpl.getstudetails(student,accyearId,userLoggingsVo);
					}
					/*if(!uploadFeespojo.getLocation_id().equalsIgnoreCase("")){
						locationid = daoImpl.getSchoolLocationId(uploadFeespojo.getLocation_id(),connection);
					}*/
					
					/*if(!uploadFeespojo.getClassId().equalsIgnoreCase("") && locationid!=null && !locationid.equalsIgnoreCase("notfound")){
						classid = daoImpl.getClassIdByLoc(uploadFeespojo.getClassId(),connection,locationid);
						
					}*/
					if(!uploadFeespojo.getTermId().equalsIgnoreCase("") && accyearId !=null && studentdetails !=null && !accyearId.equalsIgnoreCase("notfound") && !studentdetails.equalsIgnoreCase("notfound")){
						termid = sqlutil.getTermId(uploadFeespojo.getTermId(),connection,accyearId,studentdetails);
						//checkfeesettingcode = FeeExcelUploadUtil.getfeesettingCode(termid,accyearId,studentdetails);
					}
					
				}
			
					 if(uploadFeespojo.getLocation_id().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Branch Name Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					 else if(locationid.equalsIgnoreCase("notfound")){
							classFeeSetupVo.setReason("Branch Name Not Found");
							failurelist.add(classFeeSetupVo);
					 }
					else if(!locationid.equals(branchName)){
						classFeeSetupVo.setReason("You are not authorized to upload the data with this Branch");
						failurelist.add(classFeeSetupVo);
					}
				   else if(classFeeSetupVo.getAdmissionNo().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Student Admission No. should not be empty");
						failurelist.add(classFeeSetupVo);
					}
				   else if(student.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Student Admission No doesn't exists");
						failurelist.add(classFeeSetupVo);
					}
				   else if(studentdetails!=null && studentdetails.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Student details not found");
						failurelist.add(classFeeSetupVo);
					}
					else if(uploadFeespojo.getAcadamicYear().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Academic Year should not be empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(accyearId.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Academic Year not found");
						failurelist.add(classFeeSetupVo);
					}
					
					else if(uploadFeespojo.getTermId().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Term name should not be empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(classFeeSetupVo.getFeeamount().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Paid amount should not be empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(termid.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Term not found");
						failurelist.add(classFeeSetupVo);
					}/*else if(checkfeesettingcode.equalsIgnoreCase("NotSet")){
						classFeeSetupVo.setReason("Fees is Not Configured for this Class");
						failurelist.add(classFeeSetupVo);
					}*/
					else if(!(classFeeSetupVo.getFeeamount().matches(dbl))){
						classFeeSetupVo.setReason("Invalid paid amount");
						failurelist.add(classFeeSetupVo);
					}else if(classFeeSetupVo.getPaymentdate()==null){
						classFeeSetupVo.setReason("Paid date should not be empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(classFeeSetupVo.getPaymentdate().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Paid date should not be empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(paymentFlag=="false"){
						classFeeSetupVo.setReason("Invalid payment date");
						classFeeSetupVo.setPaymentdate(classFeeSetupVo.getPaymentdate());
						failurelist.add(classFeeSetupVo);
					}
					else if(uploadFeespojo.getPaymentmode().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Payment mode should not be empty");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && uploadFeespojo.getParticularNo().equalsIgnoreCase("") ){
						classFeeSetupVo.setReason("DD/Cheque No. should not be empty when payment mode is DD/Cheque");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && uploadFeespojo.getBankName().equalsIgnoreCase("") ){
						classFeeSetupVo.setReason("Bank name should not be empty");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && uploadFeespojo.getDdDate().equalsIgnoreCase("") ){
						classFeeSetupVo.setReason("DD/Cheque date should not be ");
						classFeeSetupVo.setPaymentdate(classFeeSetupVo.getDddate());
						failurelist.add(classFeeSetupVo);
					}
					else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && ddFlag=="false"){
						classFeeSetupVo.setReason("Invalid DD/Cheque date");
						failurelist.add(classFeeSetupVo);
					}
					else{
						uploadFeespojo.setFeeSettingCode(checkfeesettingcode);
						uploadFeespojo.setStudentId(student);
						uploadFeespojo.setAcadamicYear(accyearId);
						uploadFeespojo.setLocation_id(studentdetails);
						uploadFeespojo.setTermId(termid);
						uploadFeespojo.setTotFeeAmt(Double.parseDouble(classFeeSetupVo.getFeeamount()));
						uploadFeespojo.setTermName(classFeeSetupVo.getTermid());
 						uploadFeespojo.setAdmissionNo(classFeeSetupVo.getAdmissionNo());
						uploadFeespojo.setAccYear(classFeeSetupVo.getAcadamicyear());
						uploadFeespojo.setLocationName(classFeeSetupVo.getLocationName());
						uploadFeespojo.setPaidDate(paymentDate);
						uploadFeespojo.setDdDate(ddDate);
						uploadFeespojo.setLog_audit_session(log_audit_session);
						successlist.add(uploadFeespojo);
					}
			}
			Set<ClassFeeSetupVo> failureListFromDiompl=new LinkedHashSet<ClassFeeSetupVo>();
			if(successlist.size()>0){
				failureListFromDiompl=dao.insertStudentXSL(successlist,connection,userLoggingsVo);
			}
			
			for (Iterator<ClassFeeSetupVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				ClassFeeSetupVo failureDiomplVo = (ClassFeeSetupVo) it.next();
				ClassFeeSetupVo classFeeSetupVo = new ClassFeeSetupVo();
				
				classFeeSetupVo.setAdmissionNo(failureDiomplVo.getAdmissionNo());
				classFeeSetupVo.setAcadamicyear(failureDiomplVo.getAcadamicyear());
				classFeeSetupVo.setLocationName(failureDiomplVo.getLocationName());
				classFeeSetupVo.setTermid(failureDiomplVo.getTermid());
				classFeeSetupVo.setFeeamount(failureDiomplVo.getFeeamount()+"");
				classFeeSetupVo.setFineAmount(failureDiomplVo.getFineAmount()+"");
				classFeeSetupVo.setPaymentmode(failureDiomplVo.getPaymentmode());
				classFeeSetupVo.setPaymentdate(failureDiomplVo.getPaymentdate());
				classFeeSetupVo.setReason(failureDiomplVo.getReason());
				classFeeSetupVo.setLog_audit_session(log_audit_session);
				failurelist.add(classFeeSetupVo);
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(connection!=null && !connection.isClosed()){
					connection.close();
				}
				
			} catch(SQLException sql){
				sql.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertStudentXSL Ending");
	
		return failurelist;
	}



	@Override
	public Set<ClassFeeSetupVo> insertTransportStudentXSL(List<ClassFeeSetupPojo> list,
			String username, String demo,String log_audit_session,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertTransportStudentXSL Starting");
		
		List<ClassFeeSetupPojo> successlist=new ArrayList<ClassFeeSetupPojo>();
		ArrayList<ClassFeeSetupVo> getstudentList = new ArrayList<ClassFeeSetupVo>();
		Connection connection = null;
		
		FeeExcelUploadUtil sqlutil= new FeeExcelUploadUtil();
		Set<ClassFeeSetupVo> failurelist = new LinkedHashSet<ClassFeeSetupVo>();
		UploadStudentXLSDaoImpl daoImpl=new UploadStudentXLSDaoImpl();
		String student =null;
		String accyearId = null;
		String classid =null;
		String termid =null;
		String locationid =null;
		//String checkfeesettingcode = null;
		failurelist.clear();
		successlist.clear();
	String dbl="^[0-9]*(\\.[0-9]{1,4})?$";
	String datePattern = "\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
/*	String datePattern1 = "\\d{1,2}\\/\\d{1,2}\\/\\d{4}";*/
	String string_regx="([a-zA-Z.,-]+\\s+)*[a-zA-Z.,-]+";
	String regex = "\\d+";
	int count = 0;
		try{
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			for (Iterator<ClassFeeSetupPojo> iterator = list.iterator(); iterator.hasNext();) {
				ClassFeeSetupPojo uploadFeespojo = (ClassFeeSetupPojo) iterator.next();
				uploadFeespojo.setCreatedBy(username);
				ClassFeeSetupVo classFeeSetupVo = new ClassFeeSetupVo();
				
				classFeeSetupVo.setAdmissionNo(uploadFeespojo.getAdmissionNo());
				classFeeSetupVo.setAcadamicyear(uploadFeespojo.getAcadamicYear());
				classFeeSetupVo.setTermid(uploadFeespojo.getTermId());
				classFeeSetupVo.setFeeamount(uploadFeespojo.getTotalfee());
				classFeeSetupVo.setPaymentdate(uploadFeespojo.getPaidDate());
				classFeeSetupVo.setPaymentmode(uploadFeespojo.getPaymentmode());
				classFeeSetupVo.setParticularNo(uploadFeespojo.getParticularNo());
				classFeeSetupVo.setBankname(uploadFeespojo.getBankName());
				classFeeSetupVo.setDddate(uploadFeespojo.getDdDate());
				classFeeSetupVo.setNoofmnths(uploadFeespojo.getNoofmnths());
				classFeeSetupVo.setStmnth(uploadFeespojo.getStmnth());
				classFeeSetupVo.setEndmnth(uploadFeespojo.getEndmnth());
				getstudentList.add(classFeeSetupVo);
				if( !uploadFeespojo.getAdmissionNo().trim().equalsIgnoreCase("")){
					student = sqlutil.checkStudentAdmin(uploadFeespojo.getAdmissionNo().trim(),locationid,userLoggingsVo);
				}
				
				if(student!=null && !student.equalsIgnoreCase("notfound")){
					
					if(!uploadFeespojo.getAcadamicYear().equalsIgnoreCase("")){
						accyearId = daoImpl.getAcademicYearId(uploadFeespojo.getAcadamicYear(),connection);
					}
					if(accyearId !=null && !accyearId.equalsIgnoreCase("notfound")){
						locationid = daoImpl.getstuloc(student,accyearId,userLoggingsVo);
					}
					if(accyearId !=null && locationid !=null && !accyearId.equalsIgnoreCase("notfound") && !locationid.equalsIgnoreCase("notfound")){
						count = daoImpl.validateStudent(student,accyearId,locationid,userLoggingsVo);
					}
					
					
					
					
				}
					
					if(classFeeSetupVo.getAdmissionNo().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Student Admission No Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(student.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Admission No. doesn't Exists");
						failurelist.add(classFeeSetupVo);
					}else if(uploadFeespojo.getAcadamicYear().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Academic Year Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if(accyearId.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Academic Year Not Found");
						failurelist.add(classFeeSetupVo);
					}else if(count == 0){
						classFeeSetupVo.setReason("Student Details Not Found");
						failurelist.add(classFeeSetupVo);
					}
					/*else if(uploadFeespojo.getTermId().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Term Name Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(termid.equalsIgnoreCase("notfound")){
						classFeeSetupVo.setReason("Transport Fee Setup Not Found");
						failurelist.add(classFeeSetupVo);
					}/*else if(checkfeesettingcode.equalsIgnoreCase("NotSet")){
						classFeeSetupVo.setReason("Fees is Not Configured for this Class");
						failurelist.add(classFeeSetupVo);
					}*/
					else if(classFeeSetupVo.getFeeamount().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Paid Amount Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if(!(classFeeSetupVo.getFeeamount().matches(dbl))){
						classFeeSetupVo.setReason("Invalid Paid Amount");
						failurelist.add(classFeeSetupVo);
					}
					else if(classFeeSetupVo.getNoofmnths().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("No Of Months Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(!(classFeeSetupVo.getNoofmnths().matches(regex))){
						classFeeSetupVo.setReason("Invalid No Of Months");
						failurelist.add(classFeeSetupVo);
					}
					else if(classFeeSetupVo.getStmnth().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Start Month Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(!classFeeSetupVo.getStmnth().matches(string_regx)){
						classFeeSetupVo.setReason("Invalid Start Month Name");
						failurelist.add(classFeeSetupVo);
					}
					else if(classFeeSetupVo.getEndmnth().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("End Month Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(!classFeeSetupVo.getEndmnth().matches(string_regx)){
						classFeeSetupVo.setReason("Invalid End Month Name");
						failurelist.add(classFeeSetupVo);
					}
					else if(classFeeSetupVo.getPaymentdate().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Paid Date Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}
					else if(!(classFeeSetupVo.getPaymentdate().matches(datePattern))){
						classFeeSetupVo.setReason("Invalid Payment Date");
						failurelist.add(classFeeSetupVo);
					}
					else if(uploadFeespojo.getPaymentmode().equalsIgnoreCase("")){
						classFeeSetupVo.setReason("Payment Mode Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && uploadFeespojo.getParticularNo().equalsIgnoreCase("") ){
						classFeeSetupVo.setReason("DD/Cheque No. Should Not Empty When Payment Mode is DD/Cheque");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && uploadFeespojo.getBankName().equalsIgnoreCase("") ){
						classFeeSetupVo.setReason("Bank Name Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && uploadFeespojo.getDdDate().equalsIgnoreCase("") ){
						classFeeSetupVo.setReason("DD/Cheque Date Should Not Empty");
						failurelist.add(classFeeSetupVo);
					}else if((uploadFeespojo.getPaymentmode().equalsIgnoreCase("DD") || uploadFeespojo.getPaymentmode().equalsIgnoreCase("Cheque")) && (!(uploadFeespojo.getDdDate().matches(datePattern)))){
						classFeeSetupVo.setReason("Invalid DD/Cheque Date");
						failurelist.add(classFeeSetupVo);
					}
					else{
						//uploadFeespojo.setFeeSettingCode(checkfeesettingcode);
						uploadFeespojo.setStudentId(student);
						uploadFeespojo.setAcadamicYear(accyearId);
						uploadFeespojo.setLocation_id(locationid);
						uploadFeespojo.setClassId(classid);
						uploadFeespojo.setTermId(termid);
						uploadFeespojo.setTotFeeAmt(Double.parseDouble(classFeeSetupVo.getFeeamount()));
						uploadFeespojo.setTermName(classFeeSetupVo.getTermid());
						uploadFeespojo.setStuFname(classFeeSetupVo.getStuFname());
 						uploadFeespojo.setAdmissionNo(classFeeSetupVo.getAdmissionNo());
						uploadFeespojo.setClassName(classFeeSetupVo.getClassname());
						uploadFeespojo.setAccYear(classFeeSetupVo.getAcadamicyear());
						uploadFeespojo.setLocationName(classFeeSetupVo.getLocationName());
						uploadFeespojo.setCreatedby(username);
						uploadFeespojo.setLog_audit_session(log_audit_session);
						successlist.add(uploadFeespojo);
					}
			}
			Set<ClassFeeSetupVo> failureListFromDiompl=new LinkedHashSet<ClassFeeSetupVo>();
			if(successlist.size()>0){
				failureListFromDiompl=dao.insertTransportStudentXSL(successlist,connection,log_audit_session,userLoggingsVo);
			}
			
			for (Iterator<ClassFeeSetupVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				ClassFeeSetupVo failureDiomplVo = (ClassFeeSetupVo) it.next();
				ClassFeeSetupVo classFeeSetupVo = new ClassFeeSetupVo();
				classFeeSetupVo.setAdmissionNo(failureDiomplVo.getAdmissionNo());
				classFeeSetupVo.setAcadamicyear(failureDiomplVo.getAcadamicyear());
				classFeeSetupVo.setTermid(failureDiomplVo.getTermid());
				classFeeSetupVo.setFeeamount(failureDiomplVo.getFeeamount()+"");
				classFeeSetupVo.setNoofmnths(failureDiomplVo.getNoofmnths());
				classFeeSetupVo.setStmnth(failureDiomplVo.getStmnth());
				classFeeSetupVo.setEndmnth(failureDiomplVo.getEndmnth());
				classFeeSetupVo.setPaymentmode(failureDiomplVo.getPaymentmode());
				classFeeSetupVo.setReason(failureDiomplVo.getReason());
				failurelist.add(classFeeSetupVo);
		    }
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(connection!=null && !connection.isClosed()){
					connection.close();
				}
				
			} catch(SQLException sql){
				sql.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassFeeSetupServiceImpl : insertTransportStudentXSL Ending");
	
		return failurelist;
	}
	
}
