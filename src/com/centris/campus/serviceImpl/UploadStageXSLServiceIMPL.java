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
import org.json.JSONArray;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.UploadStageXLSDaoImpl;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.pojo.UploadStageXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.UploadStageXSLservice;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.HelperClassVo;
import com.centris.campus.vo.UploadStageXlsVO;



public class UploadStageXSLServiceIMPL implements UploadStageXSLservice {
	
	private static Logger logger = Logger.getLogger(UploadStageXSLServiceIMPL.class);


	public Set<UploadStageXlsVO> insertStageXSL(
			List<UploadStageXlsPOJO> list, String username, String duplicate,String log_audit_session,
			UserLoggingsPojo userLoggingsVo,String branchId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLServiceImpl : insertStageXSL : Starting");
		
		Connection connection = null;
		
		List<UploadStageXlsPOJO> successlist=new ArrayList<UploadStageXlsPOJO>();
		UploadStageXLSDaoImpl daoImpl=new UploadStageXLSDaoImpl();
		UploadStudentXLSDaoImpl daoImpl1=new UploadStudentXLSDaoImpl();
	
		int count = 0;
	
		Set<UploadStageXlsVO> failurelist = new LinkedHashSet<UploadStageXlsVO>();
	
		failurelist.clear();
		successlist.clear();
		String locationName = null;
		
		String locationId = null;

		try {
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			
			String string_regx="([a-zA-Z.]+\\s+)*[a-zA-Z.]+";
			String int_regex="^[0-9]*$";
			for (Iterator<UploadStageXlsPOJO> iterator = list.iterator(); iterator.hasNext();) {

				UploadStageXlsPOJO uploadStageXSLPOJO = (UploadStageXlsPOJO) iterator.next();
									
				UploadStageXlsVO uploadStageXSLVo = new UploadStageXlsVO();
			
				uploadStageXSLPOJO.setCreatedby(username);
				
				List<HelperClassVo> locationList = HelperClass.getAllLocation(userLoggingsVo);
				for (HelperClassVo l : locationList){
								 locationName = l.getLocationName().trim();
								 locationId = l.getLocationId();
					 }
			    
				uploadStageXSLVo.setLocationname(uploadStageXSLPOJO.getLocationname().trim());
				uploadStageXSLVo.setAccyear(uploadStageXSLPOJO.getAccyear().trim());
				uploadStageXSLVo.setStage_name(uploadStageXSLPOJO.getStage_name().trim());
				uploadStageXSLVo.setAmount(uploadStageXSLPOJO.getAmount().trim());
				uploadStageXSLVo.setStage_description(uploadStageXSLPOJO.getStage_description());
				String schoolLocationId=daoImpl1.getSchoolLocationId(uploadStageXSLPOJO.getLocationname().trim(),connection);
				String academicYearId=daoImpl1.getAcademicYearId(uploadStageXSLPOJO.getAccyear().trim(),connection);
				
				uploadStageXSLPOJO.setAccyearid(academicYearId);
				uploadStageXSLPOJO.setLocationid(schoolLocationId);
				
				if(uploadStageXSLPOJO.getAccyear().equals("")|| uploadStageXSLPOJO.getAccyear()==null){
					uploadStageXSLVo.setReason("Academic Year Should Not Empty");
					failurelist.add(uploadStageXSLVo);
				}
				else if(uploadStageXSLPOJO.getLocationname().trim().equalsIgnoreCase("") || uploadStageXSLPOJO.getLocationname().trim()==null){
					uploadStageXSLVo.setReason("Branch Name Should Not Empty");
					failurelist.add(uploadStageXSLVo);
				}
				
				else if(schoolLocationId.equals("notfound")){
					uploadStageXSLVo.setReason("Invalid Branch Name.");
					failurelist.add(uploadStageXSLVo);
				}
				else if(!schoolLocationId.trim().equals(branchId)){
					uploadStageXSLVo.setReason("You are not authorized to upload records to this location - "+uploadStageXSLPOJO.getLocationname().trim());
					failurelist.add(uploadStageXSLVo);
				}
				
				else if(uploadStageXSLPOJO.getStage_name().trim().equalsIgnoreCase("") || uploadStageXSLPOJO.getStage_name().equals("-") || uploadStageXSLPOJO.getStage_name().trim()==null){
					uploadStageXSLVo.setReason("Stage Name Should Not Empty");
					failurelist.add(uploadStageXSLVo);
				}
			   
			   else if((!uploadStageXSLPOJO.getAmount().matches(int_regex))){
					uploadStageXSLVo.setReason("Amount Should be Number.");
					failurelist.add(uploadStageXSLVo);
				}
			   else if(uploadStageXSLPOJO.getAmount().equalsIgnoreCase("")){
					uploadStageXSLVo.setReason("Amount Should Not be Empty.");
					failurelist.add(uploadStageXSLVo);
				}
			   else if(daoImpl.checkStageName(uploadStageXSLPOJO.getStage_name(),uploadStageXSLPOJO.getAccyearid(),uploadStageXSLPOJO.getLocationid(),uploadStageXSLPOJO.getAmount(),connection) !=0){
					failurelist.add(uploadStageXSLVo);
					uploadStageXSLVo.setReason("Already Exist in Database.");
				}
			   else
			   {
				   successlist.add(uploadStageXSLPOJO);
				   JSONArray j=new JSONArray(successlist);
			   }
		}
			
			failurelist=daoImpl.insertStageXSL(successlist,connection,failurelist,log_audit_session,userLoggingsVo);
		
		
		}catch (SQLException sqle) {

			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);

		}finally{
			
			try {
				
				if(connection!=null && (!connection.isClosed())){
					
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

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLServiceImpl : insertStageXSL : Ending");

		return failurelist;
	}


	@Override
	public Set<UploadStageXlsVO> insertStageXSL(List<UploadStageXlsPOJO> list, String username, String studentid,
			String log_audit_session) {
		// TODO Auto-generated method stub
		return null;
	}


	public String updateStageXSL(String[] stageId, String[] acc, String[] loc, String[] amount, String user, UploadStageXlsPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		UploadStageXLSDaoImpl daoImpl=new UploadStageXLSDaoImpl();
		return daoImpl.updateStageXSL(stageId,acc,loc,amount,user,pojo,userLoggingsVo);
	}

}
