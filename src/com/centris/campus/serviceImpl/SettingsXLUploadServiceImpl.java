package com.centris.campus.serviceImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.SettingsFileUploadDaoImpl;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SettingsFileUploadPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.StudentExcelUploadPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.HelperClassVo;
import com.centris.campus.vo.SettingsFileUploadVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.classVo;
import com.centris.campus.vo.studentExcelUploadVo;

public class SettingsXLUploadServiceImpl {
	private static Logger logger = Logger.getLogger(SettingsXLUploadServiceImpl.class);


	public Set<StreamDetailsVO> insertStreamXSL(List<StreamDetailsPojo> list, String userId, String log_audit_session, UserLoggingsPojo custdetails, String currentLocId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : insertStreamXSL : Starting");
		
		Connection connection = null;
		PreparedStatement pstmt=null,pstm1=null,pstmt2=null;
		ResultSet rs=null,rs3=null;
		List<StreamDetailsPojo> successlist=new ArrayList<StreamDetailsPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		
		Set<StreamDetailsVO> failurelist = new LinkedHashSet<StreamDetailsVO>();
		failurelist.clear();
		successlist.clear();
		
		try {
			connection=JDBCConnection.getSeparateConnection(custdetails);
			int cont=0;
			
			String int_regex="^[0-9a-zA-Z]*$";
			String string_regx="^[a-zA-Z0-9_ ]*$";
			String locationName = null;
			String locationId = null;
			String specialCharacters="!#$%&()*+/;<=>?@[]^_{}";
			String specialError= "false";

			for (Iterator<StreamDetailsPojo> iterator = list.iterator(); iterator.hasNext();) {
				cont++;
				int count=0;
				String checknum ="false";
				String checknum1 ="false";
			
				boolean flag=true;
				int count1=0;
				StreamDetailsPojo pojo = (StreamDetailsPojo) iterator.next();
				StreamDetailsVO vo = new StreamDetailsVO();
				
				int streamLength = pojo.getStreamName().length();
				
					vo.setLocationName(pojo.getLocationName());
					vo.setStreamName(pojo.getStreamName());
					vo.setDescription(pojo.getDescription());
					
					List<HelperClassVo> locationList = HelperClass.getAllLocation(custdetails);
					for (HelperClassVo l : locationList){
									 locationName = l.getLocationName().trim();
									 locationId = l.getLocationId();
						 }
					
					
					for(int i = 0; i < pojo.getLocationName().length(); i++) {
					     if(Character.isWhitespace(pojo.getLocationName().charAt(i))){
					    	 count++;
					     }
					     else{
					    	 if(count<2){
					    		 count=0;
					    	 }
					     }
					}
					for (int i = 0; i < pojo.getStreamName().length(); i++) {
					    if (specialCharacters.contains(Character.toString(pojo.getStreamName().charAt(i))))
					    {
					    	specialError="true";
					    }  
					  }
					/* System.out.println("specialError ::::::::::"+specialError);*/
					for(int i = 0; i < pojo.getStreamName().length(); i++) {
					     if(Character.isWhitespace(pojo.getStreamName().charAt(i))){
					    	 count1++;
					     }
					     else{
					    	 if(count1<2){
					    		 count1=0;
					    	 }
					     }
					}
					for(int i = 0; i < pojo.getLocationName().length(); i++) {
						if(Character.isDigit(pojo.getLocationName().charAt(0)))
							checknum="true";
						}
					for(int i = 0; i < pojo.getStreamName().length(); i++) {
						if(Character.isDigit(pojo.getStreamName().charAt(0)))
							checknum1="true";
						}
					
					
					for(int i=0;i<list.size();i++){
						if(i==cont-1){
							continue;
						}

						if(pojo.getLocationName().equalsIgnoreCase(list.get(i).getLocationName()) && pojo.getStreamName().equalsIgnoreCase(list.get(i).getStreamName())){
							if(cont-1<i){
								flag =false;
							}
							
						}
					}
					
					/*System.out.println("pojo.getStreamName().length()"+pojo.getStreamName().length());*/
					
					 if(pojo.getStreamName().equals("") || pojo.getStreamName().equals("-")){
						vo.setReason("Stream Name should not be empty.");
						failurelist.add(vo);
					}
					 else if(count>1){ //for location
							vo.setReason("More than one Space in "+pojo.getLocationName()+"");
							failurelist.add(vo);
						}
					 else if(count1>1){ //for stream
							vo.setReason("More than one Space in "+pojo.getStreamName()+"");
							failurelist.add(vo);
						}
					else if((pojo.getLocationName().equals("") || pojo.getLocationName().equals("-"))){
						vo.setReason("Branch should not be empty");
						failurelist.add(vo);
					}
					else if(streamLength > 20){
						vo.setReason("Stream name should not be exceed more than 20 charactors");
						failurelist.add(vo);
					}
					/*else if(!(pojo.getLocationName().equalsIgnoreCase(locationName))){
						//System.out.println("pojo.getLocationName()"+pojo.getLocationName());
						//System.out.println("locationName"+locationName.trim());
						
						vo.setReason("Invalid/Inactive Location Name");
						failurelist.add(vo);
					}*/
					else if(checknum.equalsIgnoreCase("true")){
						vo.setReason("Branch should not begin with numbers");
						failurelist.add(vo);
					}
					
					else if(checknum1.equalsIgnoreCase("true")){
						vo.setReason(""+pojo.getStreamName()+" should not begin with numbers");
						failurelist.add(vo);
					}
					
					else if(specialError.equalsIgnoreCase("true")){
						vo.setReason("Stream name restricted for special Characters like !#$%&()*+/;<=>?@[]^_{}");
						failurelist.add(vo);
					}
					
					else if(!flag){
						vo.setReason("Information duplicated in excel sheet ");
						failurelist.add(vo);
					}
					else {
						pstmt= connection.prepareStatement("SELECT `Location_Id` FROM `campus_location` WHERE `Location_Name`=? and `isActive`='Y'");
						pstmt.setString(1,pojo.getLocationName());
						rs = pstmt.executeQuery();
						System.out.println(pstmt);
						if(rs.next()){
							System.out.println(currentLocId.equals(rs.getString("Location_Id")));
							if(currentLocId.equals(rs.getString("Location_Id"))){
							
								pojo.setLocationId(rs.getString("Location_Id"));
								pstm1=connection.prepareStatement("SELECT count(classstream_name_var) FROM `campus_classstream` WHERE `locationId`=? AND `classstream_name_var`=? ");
								pstm1.setString(1,pojo.getLocationId());
								pstm1.setString(2,pojo.getStreamName());
								rs3 = pstm1.executeQuery();
								while(rs3.next()){
									count1=rs3.getInt(1);
								}
								if(count1>0){
									vo.setReason("Duplicate Stream name");
									failurelist.add(vo);
								}
								successlist.add(pojo);
						}else{
							vo.setReason("Incorrect Branch Name.");
							failurelist.add(vo);
						}
						}
						else{
							vo.setReason("Incorrect/Inactive Branch Name");
							failurelist.add(vo);
							}
						rs.close();
					}
			}
			Set<StreamDetailsVO> failureListFromDiompl=new LinkedHashSet<StreamDetailsVO>();
			for (Iterator<StreamDetailsVO> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				StreamDetailsVO failureDiomplVo = (StreamDetailsVO) it.next();
				StreamDetailsVO uploadStream = new StreamDetailsVO();

				uploadStream.setLocationName(failureDiomplVo.getLocationName());
				uploadStream.setStreamName(failureDiomplVo.getStreamName());
				uploadStream.setDescription(failureDiomplVo.getDescription());
				uploadStream.setReason(failureDiomplVo.getReason());
				failurelist.add(uploadStream);
			}
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertStream(successlist,failurelist,connection,userId,log_audit_session,custdetails);
			}
			
			
	}catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}finally{
		try {
			if(rs!=null && (!rs.isClosed())){
				rs.close();
			}
			if(pstmt!=null && (!pstmt.isClosed())){
				pstmt.close();
			}if(pstm1!=null && (!pstm1.isClosed())){
				pstm1.close();
			}if(pstmt2!=null && (!pstmt2.isClosed())){
				pstmt2.close();
			}
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
				+ " Control in SettingsXLUploadServiceImpl : insertStreamXSL : Ending");
		return failurelist;
	}

	public Set<classVo> insertClassXSL(List<ClassPojo> list, String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo, String currentLocId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : insertClassXSL : Starting");
		
		Connection connection = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null;
		ResultSet rs=null,rs1=null,rs2=null;
		List<ClassPojo> successlist=new ArrayList<ClassPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		
		
		Set<classVo> failurelist = new LinkedHashSet<classVo>();
		failurelist.clear();
		successlist.clear();
	
		
		try {
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			int cont=0;
			String status=null;
			String className = null;
			String string_regx="^[a-zA-Z0-9_ ]*$";

			for (Iterator<ClassPojo> iterator = list.iterator(); iterator.hasNext();) {
				cont++;
				int count=0,count1=0,count2=0;
				String checknum ="false";
				boolean flag=true;
				
				ClassPojo pojo = (ClassPojo) iterator.next();
				classVo vo = new classVo();
					vo.setLocationName(pojo.getLocationName());
					vo.setStreamName(pojo.getStreamName());
					vo.setClassName(pojo.getClassName());
					
					//List<classVo> classList = HelperClass.getAllClassList(currentLocId, userLoggingsVo);
					
					String[] classList = {"Pre-KG","LKG","UKG","I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII"};
					
					List<HelperClassVo>  loclist = HelperClass.getAllLocation(userLoggingsVo);
					for(int i=0;i<loclist.size();i++){
						if(loclist.get(i).getLocationName().trim().equals(vo.getLocationName().trim())){
							status="true";
						}else{
							status="false";
						}
					}
					
				  for (String l : classList){
						 className = l;
						}
					
					for(int i = 0; i < pojo.getClassName().length(); i++) {
					     if(Character.isWhitespace(pojo.getClassName().trim().charAt(i))){
					    	 count++;
					     }
					     else{
					    	 if(count<2){
					    		 count=0;
					    	 }
					     }
					}
					for(int i = 0; i < pojo.getLocationName().length(); i++) {
					     if(Character.isWhitespace(pojo.getLocationName().trim().charAt(i))){
					    	 count1++;
					     }
					     else{
					    	 if(count1<2){
					    		 count1=0;
					    	 }
					     }
					}
					
					for(int i = 0; i < pojo.getStreamName().length(); i++) {
					     if(Character.isWhitespace(pojo.getStreamName().trim().charAt(i))){
					    	 count2++;
					     }
					     else{
					    	 if(count2<2){
					    		 count2=0;
					    	 }
					     }
					}
					
					
					for(int i=0;i<list.size();i++){
						if(i==cont-1){
							continue;
						}

						if(pojo.getLocationName().equalsIgnoreCase(list.get(i).getLocationName()) && pojo.getStreamName().equalsIgnoreCase(list.get(i).getStreamName()) && pojo.getClassName().equalsIgnoreCase(list.get(i).getClassName())){
							if(cont-1<i){
								flag =false;
							}
						}
					}
					
					for(int i = 0; i < pojo.getClassName().length(); i++) {
					if(Character.isDigit(pojo.getClassName().charAt(0)))
						checknum="true";
					}  
					
					/* if(status.equals("false")){
						vo.setReason("Invalid/Inactive Location Name");
						failurelist.add(vo);
					}
					else*/ if(pojo.getStreamName().equals("") || pojo.getStreamName().equals("-")){
						vo.setReason("Stream name should not be empty");
						failurelist.add(vo);
					}
					else if(pojo.getLocationName().equals("") || pojo.getLocationName().equals("-")){
						vo.setReason("Branch name should not be empty");
						failurelist.add(vo);
					}
					else if(pojo.getClassName().equals("") || pojo.getClassName().equals("-")){
						vo.setReason("Class name should not be empty");
						failurelist.add(vo);
					}
					else if(!(Arrays.asList(classList).contains(pojo.getClassName()))){
						vo.setReason("Class name not valid.");
						failurelist.add(vo);
					}
				
					else if(count>1){
						vo.setReason("More than one space issue in "+pojo.getClassName()+"");
						failurelist.add(vo);
					}
					else if(checknum.equalsIgnoreCase("true")){
						vo.setReason("Class name should not begin with numbers");
						failurelist.add(vo);
					}
					/*else if(count1>1){
						vo.setReason("More than one space issue in "+pojo.getLocationName()+"");
						failurelist.add(vo);
					}
					else if(count2>1){
						vo.setReason("More than one space bewteen words are not valid");
						failurelist.add(vo);
					}*/
					else if(!flag){
						vo.setReason("Data duplicate entry in excel sheet.");
						failurelist.add(vo);
					}
					else if(!flag){
						vo.setReason("Duplicate entry in excel sheet");
						failurelist.add(vo);
					}
					
					else {
						pstmt= connection.prepareStatement("SELECT `Location_Id` FROM `campus_location` WHERE `Location_Name`=? and `isActive`='Y'");
						pstmt.setString(1,pojo.getLocationName());
						rs = pstmt.executeQuery();
						if(rs.next()){
							
							if(currentLocId.equals(rs.getString("Location_Id"))){
							
								pojo.setLocationId(rs.getString("Location_Id"));
								
								pstmt1= connection.prepareStatement("SELECT `classstream_id_int` FROM `campus_classstream` WHERE `classstream_name_var`=? and locationId=? and `isActive`='Y'" );
								pstmt1.setString(1,pojo.getStreamName());
								pstmt1.setString(2,pojo.getLocationId());
								rs1 = pstmt1.executeQuery();
								if(rs1.next()){
										pojo.setStreamId(rs1.getString("classstream_id_int"));
										successlist.add(pojo);
										
										pstmt2= connection.prepareStatement("SELECT COUNT(`classstream_name_var`) FROM `campus_classstream` WHERE  classstream_name_var=? and `locationId`=?  and `isActive`='Y'");
										pstmt2.setString(1,pojo.getStreamName());
										pstmt2.setString(2,pojo.getLocationId());
										rs2 = pstmt2.executeQuery();
										if(rs2.next()){
											count=rs2.getInt(1);
										}
										if(count==1){
										}else{
											vo.setReason("No stream name  for location Exists");
											failurelist.add(vo);
										}
										rs2.close();
									}
								else{
									vo.setReason("Stream Name Does not Exists for '"+pojo.getLocationName()+"' ");
									failurelist.add(vo);
								}
								rs1.close();
							}
							else{
								vo.setReason("Incorrect Branch Name.");
								failurelist.add(vo);
								}
						}
					
						else{
							vo.setReason("Incorrect Branch Name.");
							failurelist.add(vo);
						}
						rs.close();
					}
					
					
			}
			Set<classVo> failureListFromDiompl=new LinkedHashSet<classVo>();
			for (Iterator<classVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				classVo failureDiomplVo = (classVo) it.next();
				classVo uploadStream = new classVo();

				uploadStream.setLocationName(failureDiomplVo.getLocationName());
				uploadStream.setStreamName(failureDiomplVo.getStreamName());
				uploadStream.setClassName(failureDiomplVo.getClassName());
				uploadStream.setReason(failureDiomplVo.getReason());
				failurelist.add(uploadStream);
			}
			
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertClass(successlist,failurelist,connection,userId,log_audit_session,userLoggingsVo);
			}
			
		
			
	}catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}finally{
		try {
			if(rs!=null && (!rs.isClosed())){
				rs.close();
			}
			if(rs1!=null && (!rs1.isClosed())){
				rs1.close();
			}
			if(rs2!=null && (!rs2.isClosed())){
				rs2.close();
			}
			if(pstmt!=null && (!pstmt.isClosed())){
				pstmt.close();
			}
			if(pstmt1!=null && (!pstmt1.isClosed())){
				pstmt1.close();
			}
			if(pstmt2!=null && (!pstmt2.isClosed())){
				pstmt2.close();
			}
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
				+ " Control in SettingsXLUploadServiceImpl : insertClassXSL : Ending");
		return failurelist;
	}




	public Set<classVo> insertDivisionXSL(List<ClassPojo> list, String userId, String log_audit_session, String currentLocId, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : insertDivisionXSL : Starting");
		
		Connection connection = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null,pstmt3=null;
		ResultSet rs=null,rs1=null,rs2=null;
		List<ClassPojo> successlist=new ArrayList<ClassPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		
		
		Set<classVo> failurelist = new LinkedHashSet<classVo>();
		failurelist.clear();
		successlist.clear();
		
		try {
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			int cont=0;
			String int_regex="^[0-9a-zA-Z]*$";
			String status="^[a-zA-Z]*$";
			String string_regx="^[a-zA-Z0-9_ ]*$";
			int count5=0;
			String specialCharacters="!#$%&'()*+,./:;<=>?@[]^_`{|}";
			String specialError="false";
			String checknum="false";
			String status1=null;
			
			String numregex = "[0-9]+";

			for (Iterator<ClassPojo> iterator = list.iterator(); iterator.hasNext();) {
				cont++;
				int count=0;
				boolean flag=true;
				
				ClassPojo pojo = (ClassPojo) iterator.next();
				classVo vo = new classVo();
					vo.setLocationName(pojo.getLocationName());
					vo.setStreamName(pojo.getStreamName());
					vo.setClassName(pojo.getClassName());
					vo.setDivisionName(pojo.getDivisionName());
					vo.setStrength(pojo.getStrength());
					
					List<HelperClassVo>  loclist = HelperClass.getAllLocation(userLoggingsVo);
					/*for(int i=0;i<loclist.size();i++){
						if(loclist.get(i).getLocationName().equals(vo.getLocationName())){
							status1="true";
						}else{
							status1="false";
						}
					}*/
					
					for (int i = 0; i < pojo.getClassName().length(); i++) {
					    if (specialCharacters.contains(Character.toString(pojo.getClassName().charAt(i))))
					    {
					    	specialError="true";
					    }  
					  }
					
					for(int i = 0; i < pojo.getClassName().length(); i++) {
					     if(Character.isWhitespace(pojo.getClassName().charAt(i))){
					    	 count++;
					     }
					     else{
					    	 if(count<2){
					    		 count=0;
					    	 }
					     }
					}
					
					for(int i=0;i<list.size();i++){
						if(i==cont-1){
							continue;
						}

						if(pojo.getLocationName().equalsIgnoreCase(list.get(i).getLocationName()) && pojo.getStreamName().equalsIgnoreCase(list.get(i).getStreamName()) && 
								pojo.getClassName().equalsIgnoreCase(list.get(i).getClassName())  &&  pojo.getDivisionName().equalsIgnoreCase(list.get(i).getDivisionName())){
							if(cont-1<i){
								flag =false;
							}
						}
					}
					
					
					for(int i = 0; i < pojo.getClassName().length(); i++) {
					if(Character.isDigit(pojo.getClassName().charAt(0)))
						checknum="true";
					} 
					
					 /*if(status1.equals("false")){
							vo.setReason("Invalid/Inactive Branch Name");
							failurelist.add(vo);
						}*/
					
					if(pojo.getStreamName().equalsIgnoreCase("") || pojo.getStreamName().equals("-")){
						vo.setReason("Stream name should not be empty");
						failurelist.add(vo);
					}
					
					else if(pojo.getLocationName().equals("") || pojo.getLocationName().equals("-")){
						vo.setReason("Branch name should not be empty");
						failurelist.add(vo);
					}
					else if(pojo.getClassName().equals("") || pojo.getClassName().equals("-")){
						vo.setReason("Class name should not be empty");
						failurelist.add(vo);
					}
					
					else if(pojo.getDivisionName().equals("") || pojo.getDivisionName().equals("-")){
						vo.setReason("Division name should not be empty");
						failurelist.add(vo);
					}
					else if(count>1){
						vo.setReason("More than one space bewteen words are not valid");
						failurelist.add(vo);
					}
					else if(checknum.equalsIgnoreCase("true")){
						vo.setReason("Division  should not begin with numbers");
						failurelist.add(vo);
					}
					else if(!(pojo.getDivisionName().matches(string_regx))){
						vo.setReason("Please enter valid Division");
						failurelist.add(vo);
					}
					
					else if(!(pojo.getStrength().matches(numregex))){
						vo.setReason("Invalid Strength");
						failurelist.add(vo);
						}
				/*	else if(pojo.getStrength()==null || pojo.getStrength()==""){
						vo.setReason("Strength cannot be empty");
						failurelist.add(vo);
					}*/
					else {
						//successlist.add(pojo);
						pstmt= connection.prepareStatement("SELECT `Location_Id` FROM `campus_location`WHERE `Location_Name`=? and `isActive`='Y'");
						pstmt.setString(1,pojo.getLocationName());
						System.out.println("Location_Id  -->>"+pstmt);
						rs = pstmt.executeQuery();
						if(rs.next()){
							
							if(currentLocId.equals(rs.getString("Location_Id"))){
								
								pojo.setLocationId(rs.getString("Location_Id"));
								pstmt1= connection.prepareStatement("SELECT `classstream_id_int` FROM `campus_classstream` WHERE `classstream_name_var`=? and locationId=? and `isActive`='Y'" );
								pstmt1.setString(1,pojo.getStreamName());
								pstmt1.setString(2,pojo.getLocationId());
								rs1 = pstmt1.executeQuery();
								if(rs1.next()){
										pojo.setStreamId(rs1.getString("classstream_id_int"));
										
										pstmt3= connection.prepareStatement("SELECT `classdetail_id_int` FROM `campus_classdetail` WHERE `classdetails_name_var`=? AND `classstream_id_int`=? AND `locationId`=?  and `isActive`='Y'");
										pstmt3.setString(1,pojo.getClassName());
										pstmt3.setString(2,pojo.getStreamId());
										pstmt3.setString(3,pojo.getLocationId());
										rs2 = pstmt3.executeQuery();
										if(rs2.next()){
											pojo.setClassId(rs2.getString("classdetail_id_int"));
											successlist.add(pojo);
										}
										else{
											vo.setReason("Class not mapped with Stream and Branch ");
											failurelist.add(vo);
										}
										rs2.close();
									}
								else{
									vo.setReason("Stream Does not Exists for '"+pojo.getLocationName()+"' ");
									failurelist.add(vo);
								}
								rs1.close();
							}
					
						else{
							vo.setReason("Incorrect Branch Name");
							failurelist.add(vo);
						  }
						}
						else{
							System.out.println("Inside");
							vo.setReason("Incorrect/Inactive Branch Name");
							failurelist.add(vo);
						}
						rs.close();
					
					}
			}
			Set<classVo> failureListFromDiompl=new LinkedHashSet<classVo>();
			
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertDivision(successlist,failurelist,connection,userId,log_audit_session,userLoggingsVo);
			}
			for (Iterator<classVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				classVo failureDiomplVo = (classVo) it.next();
				classVo uploadStream = new classVo();
				
				uploadStream.setLocationName(failureDiomplVo.getLocationName());
				uploadStream.setStreamName(failureDiomplVo.getStreamName());
				uploadStream.setClassName(failureDiomplVo.getClassName());
				uploadStream.setDivisionName(failureDiomplVo.getDivisionName());
				uploadStream.setStrength(failureDiomplVo.getStrength());
				uploadStream.setReason(failureDiomplVo.getReason());
				failurelist.add(uploadStream);
			}
		
	}catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}finally{
		try {
			if(rs!=null && (!rs.isClosed())){
				rs.close();
			}
			if(rs1!=null && (!rs1.isClosed())){
				rs1.close();
			}
			if(rs2!=null && (!rs2.isClosed())){
				rs2.close();
			}
			if(pstmt!=null && (!pstmt.isClosed())){
				pstmt.close();
			}
			if(pstmt1!=null && (!pstmt1.isClosed())){
				pstmt1.close();
			}
			if(pstmt2!=null && (!pstmt2.isClosed())){
				pstmt2.close();
			}
			if(pstmt3!=null && (!pstmt3.isClosed())){
				pstmt3.close();
			}
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
				+ " Control in SettingsXLUploadServiceImpl : insertDivisionXSL : Ending");
	
		return failurelist;
	}




	public Set<classVo> insertOccupationXSL(List<ClassPojo> list, String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo, String currentLocId) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : insertOccupationXSL : Starting");
	
		Connection connection = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null;
		ResultSet rs=null;
		List<ClassPojo> successlist=new ArrayList<ClassPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		
		int count = 0;
		boolean flag=true;
		
		Set<classVo> failurelist = new LinkedHashSet<classVo>();
		failurelist.clear();
		successlist.clear();
		
		try {
			
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			String stringRegex = "^[a-zA-Z_\\.\\)\\(\\[\\]\\- ]*$";

			int cont=0;
			for (Iterator<ClassPojo> iterator = list.iterator(); iterator.hasNext();) {
				cont++;
				ClassPojo pojo = (ClassPojo) iterator.next();
				classVo vo = new classVo();
				String specialError="false";
				String checknum ="false";
				vo.setOccupationName(pojo.getOccupationName());
					
					int count1=0;
					
					pstmt= connection.prepareStatement("SELECT COUNT(`occupation`) FROM `campus_occupation` WHERE `occupation`=?  and `isActive`='Y'");
					pstmt.setString(1,pojo.getOccupationName());
					rs = pstmt.executeQuery();
					while(rs.next()){
						count1=rs.getInt(1);
					}
					rs.close();
					
					
				/*	for (int i = 0; i < pojo.getOccupationName().length(); i++) {
					    if (specialCharacters.contains(Character.toString(pojo.getOccupationName().charAt(i))))
					    {
					    	specialError="true";
					    }  
					  }*/
					
					for(int i = 0; i < pojo.getOccupationName().length(); i++) {
					     if(Character.isWhitespace(pojo.getOccupationName().charAt(i))){
					    	 count++;
					     }
					     else{
					    	 if(count<2){
					    		 count=0;
					    	 }
					     }
					}
					
					for(int i = 0; i < pojo.getOccupationName().length(); i++) {
					if(Character.isDigit(pojo.getOccupationName().charAt(0)))
						checknum="true";
					}
					
					for(int i=0;i<list.size();i++){
						if(i==cont-1){
							continue;
						}
						if(pojo.getOccupationName().equalsIgnoreCase(list.get(i).getLocationName())){
							if(cont-1<i){
								flag =false;
							}
						}
					}
					
				
				  if(pojo.getOccupationName().equals("") || pojo.getOccupationName().equals("-")){
						vo.setReason("Occupation name should not be empty");
						failurelist.add(vo);
					}
					else if(count>1){
						vo.setReason("More than one space bewteen words are not valid");
						failurelist.add(vo);
					}
					else if(!(pojo.getOccupationName().matches(stringRegex))){
						vo.setReason("Occupation Name should not contain Special characters / Numbers");
						failurelist.add(vo);
					}
					else if(checknum.equalsIgnoreCase("true")){
						vo.setReason("Occupation should not begin with numbers");
						failurelist.add(vo);
					}
					else if(!flag){
						vo.setReason("Duplicate entry in excel sheet");
						failurelist.add(vo);
					}
					else if(count1>0){
					  	vo.setReason("Occupation '"+vo.getOccupationName()+"' already exists.");
						failurelist.add(vo);
					}	
					else{
						successlist.add(pojo);
					}
			}
			Set<classVo> failureListFromDiompl=new LinkedHashSet<classVo>();
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertOccupation(successlist,connection,userId,log_audit_session,userLoggingsVo);
			}
			
			for (Iterator<classVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				classVo failureDiomplVo = (classVo) it.next();
				classVo uploadOccupation = new classVo();
				uploadOccupation.setOccupationName(failureDiomplVo.getOccupationName());
				failurelist.add(uploadOccupation);
			}
			
	}catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}finally{
		try {
			if(rs!=null && (!rs.isClosed())){
				rs.close();
			}
			if(pstmt!=null && (!pstmt.isClosed())){
				pstmt.close();
			}
			if(pstmt1!=null && (!pstmt1.isClosed())){
				pstmt1.close();
			}
			if(pstmt2!=null && (!pstmt2.isClosed())){
				pstmt2.close();
			}
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
				+ " Control in SettingsXLUploadServiceImpl : insertOccupationXSL : Ending");
		
		return failurelist;
	}




	public Set<classVo> insertReligionXSL(List<ClassPojo> list, String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : insertReligionXSL : Starting");
		
		Connection connection = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null;
		ResultSet rs=null;
		List<ClassPojo> successlist=new ArrayList<ClassPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		
		Set<classVo> failurelist = new LinkedHashSet<classVo>();
		failurelist.clear();
		successlist.clear();
		
		try {
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			String specialCharacters="!#$%&'()*+;<=>?@[]^`|";
			int cont=0;
			for (Iterator<ClassPojo> iterator = list.iterator(); iterator.hasNext();) {
				cont++;
				ClassPojo pojo = (ClassPojo) iterator.next();
				classVo vo = new classVo();
				vo.setReligionName(pojo.getReligionName());
				String specialError="false";
				String checknum ="false";
				boolean flag=true;
				int count=0;
					int count1=0;
					
					pstmt= connection.prepareStatement("SELECT COUNT(`religion`) FROM `campus_religion` WHERE `religion`=?  and `isActive`='Y'");
					pstmt.setString(1,pojo.getReligionName());
					rs = pstmt.executeQuery();
					while(rs.next()){
						count1=rs.getInt(1);
					}
					rs.close();
					
					for (int i = 0; i < pojo.getReligionName().length(); i++) {
					    if (specialCharacters.contains(Character.toString(pojo.getReligionName().charAt(i))))
					    {
					    	specialError="true";
					    }  
					  }
					
					for(int i = 0; i < pojo.getReligionName().length(); i++) {
					     if(Character.isWhitespace(pojo.getReligionName().charAt(i))){
					    	 count++;
					     }
					     else{
					    	 if(count<2){
					    		 count=0;
					    	 }
					     }
					}
					
					for(int i=0;i<list.size();i++){
						if(i==cont-1){
							continue;
						}
						if(pojo.getReligionName().equalsIgnoreCase(list.get(i).getReligionName())){
							if(cont-1<i){
								flag =false;
							}
						}
					}
					
					for(int i = 0; i < pojo.getReligionName().length(); i++) {
					if(Character.isDigit(pojo.getReligionName().charAt(0)))
						checknum="true";
					}
					if(count1>0){
							vo.setReason("Religion Name already Exists");
							failurelist.add(vo);
						}	
					else if(pojo.getReligionName().equals("")|| pojo.getReligionName().equals("-")){
						vo.setReason("Religion name should not empty");
						failurelist.add(vo);
					}
					else if(specialError.equalsIgnoreCase("true")){
						vo.setReason("Religion Name should not include special charector like  !#$%&'()*+;<=>?@[]^`|");
						failurelist.add(vo);
					}
					else if(count>1){
						vo.setReason("More than one space bewteen words are not valid");
						failurelist.add(vo);
					}
					else if(checknum.equalsIgnoreCase("true")){
						vo.setReason("Religion name should not begin with numbers");
						failurelist.add(vo);
					}
					else if(userId.equalsIgnoreCase("") || userId.equals(null)){
						vo.setReason("Session Time Out, Try again");
						failurelist.add(vo);
					}
					else if(!flag){
						vo.setReason("Duplicate entry in excel sheet");
						failurelist.add(vo);
					}
					else{
						successlist.add(pojo);
					}
			}
			Set<classVo> failureListFromDiompl=new LinkedHashSet<classVo>();
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertReligion(successlist,connection,userId,log_audit_session,userLoggingsVo);
			}
			
			for (Iterator<classVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				classVo failureDiomplVo = (classVo) it.next();
				classVo uploadOccupation = new classVo();
				uploadOccupation.setReligionName(failureDiomplVo.getReligionName());
				failurelist.add(uploadOccupation);
			}
			
	}catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}finally{
		try {
			if(rs!=null && (!rs.isClosed())){
				rs.close();
			}
			if(pstmt!=null && (!pstmt.isClosed())){
				pstmt.close();
			}if(pstmt1!=null && (!pstmt1.isClosed())){
				pstmt1.close();
			}if(pstmt2!=null && (!pstmt2.isClosed())){
				pstmt2.close();
			}if(connection!=null & !(connection.isClosed())){
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
				+ " Control in SettingsXLUploadServiceImpl : insertReligionXSL : Ending");
		
		return failurelist;
	}




	public Set<classVo> insertCasteXSL(List<ClassPojo> list, String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : insertCasteXSL : Starting");
		
		Connection connection = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null;
		ResultSet rs=null;
		List<ClassPojo> successlist=new ArrayList<ClassPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		
		
		Set<classVo> failurelist = new LinkedHashSet<classVo>();
		failurelist.clear();
		successlist.clear();
		
		try {
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			int cont=0;
			String specialCharacters="#%&'*+,.:;<=>?@[]^`{|}";

			for (Iterator<ClassPojo> iterator = list.iterator(); iterator.hasNext();) {
				cont++;
				
				ClassPojo pojo = (ClassPojo) iterator.next();
				classVo vo = new classVo();
				String specialError="false";
				String checknum ="false";
				boolean flag=true;
				int count=0;
					vo.setReligionName(pojo.getReligionName());
					vo.setCasteName(pojo.getCasteName());
					
					for (int i = 0; i < pojo.getCasteName().length(); i++) {
					    if (specialCharacters.contains(Character.toString(pojo.getCasteName().charAt(i))))
					    {
					    	specialError="true";
					    }  
					  }
					
					for(int i = 0; i < pojo.getCasteName().length(); i++) {
					     if(Character.isWhitespace(pojo.getCasteName().charAt(i))){
					    	 count++;
					     }
					     else{
					    	 if(count<2){
					    		 count=0;
					    	 }
					     }
					}
					
					
					for(int i=0;i<list.size();i++){
						if(i==cont-1){
							continue;
						}
						if(pojo.getCasteName().equalsIgnoreCase(list.get(i).getCasteName()) && pojo.getReligionName().equalsIgnoreCase(list.get(i).getReligionName())){
							if(cont-1<i){
								flag =false;
							}
						}
					}
					
					if(pojo.getReligionName().equals("")|| pojo.getReligionName().equals("-")){
						vo.setReason("Religion name should not empty");
						failurelist.add(vo);
					}
					else if(specialError.equalsIgnoreCase("true")){
						vo.setReason("Caste Name should not include special charector like #%&'*+,.:;<=>?@[]^`{|} ");
						failurelist.add(vo);
					}
					else if(count>1){
						vo.setReason("More than one space bewteen words are not valid");
						failurelist.add(vo);
					}
					else if(pojo.getCasteName().equals("") || pojo.getCasteName().equals("-")){
						vo.setReason("Caste name should not empty");
						failurelist.add(vo);
					}
					else if(!flag){
						vo.setReason("Duplicate entry in excel sheet");
						failurelist.add(vo);
					}
					else {
						pstmt= connection.prepareStatement("SELECT `religionId` FROM `campus_religion` WHERE `religion`=?  and `isActive`='Y'");
						pstmt.setString(1,pojo.getReligionName());
						rs = pstmt.executeQuery();
						if(rs.next()){
								pojo.setReligionId(rs.getString("religionId"));
								successlist.add(pojo);
							}
						else{
							vo.setReason("Religion Name Does not Exists");
							failurelist.add(vo);
						}
						rs.close();
					}
				}
			
			Set<classVo> failureListFromDiompl=new LinkedHashSet<classVo>();
			for (Iterator<classVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				classVo failureDiomplVo = (classVo) it.next();
				classVo uploadCaste = new classVo();
					uploadCaste.setCasteName(failureDiomplVo.getCasteName());
					uploadCaste.setReligionName(failureDiomplVo.getReligionName());
				failurelist.add(uploadCaste);
			}
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertCaste(successlist,failurelist,connection,userId,log_audit_session,userLoggingsVo);
			}
	}catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}finally{
		try {
			if(rs!=null && (!rs.isClosed())){
				rs.close();
			}
			if(pstmt!=null && (!pstmt.isClosed())){
				pstmt.close();
			}
			if(pstmt1!=null && (!pstmt1.isClosed())){
				pstmt1.close();
			}
			if(pstmt2!=null && (!pstmt2.isClosed())){
				pstmt2.close();
			}
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
				+ " Control in SettingsXLUploadServiceImpl : insertCasteXSL : Ending");
		return failurelist;
	}




	public Set<classVo> insertCasteCategoryExcel(List<ClassPojo> list, String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : insertCasteCategoryExcel : Starting");
		Connection connection = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null,pstmt3=null;
		ResultSet rs=null,rs1=null;
		List<ClassPojo> successlist=new ArrayList<ClassPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		
		Set<classVo> failurelist = new LinkedHashSet<classVo>();
		failurelist.clear();
		successlist.clear();
		
		try {
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			int cont=0;
			String specialCharacters="!#$%&*+,.;<=>?@[]^`{|}";

			for (Iterator<ClassPojo> iterator = list.iterator(); iterator.hasNext();) {
				cont++;
				
				ClassPojo pojo = (ClassPojo) iterator.next();
				classVo vo = new classVo();
				String specialError="false";
				String checknum ="false";
				boolean flag=true;
				int count=0;
		
					vo.setReligionName(pojo.getReligionName());
					vo.setCasteName(pojo.getCasteName());
					vo.setCasteCategoryName(pojo.getCasteCategoryName());
					
					pstmt3=connection.prepareStatement("SELECT `religionId` FROM `campus_religion` WHERE `religion`=?  and `isActive`='Y'");
					pstmt3.setString(1,pojo.getReligionName());
					{
						
					}
					
					
					for (int i = 0; i < pojo.getCasteCategoryName().length(); i++) {
					    if (specialCharacters.contains(Character.toString(pojo.getCasteCategoryName().charAt(i))))
					    {
					    	specialError="true";
					    }  
					  }
					
					for(int i = 0; i < pojo.getCasteCategoryName().length(); i++) {
					     if(Character.isWhitespace(pojo.getCasteCategoryName().charAt(i))){
					    	 count++;
					     }
					     else{
					    	 if(count<2){
					    		 count=0;
					    	 }
					     }
					}
					
					for(int i=0;i<list.size();i++){
						if(i==cont-1){
							continue;
						}
						if(pojo.getCasteName().equalsIgnoreCase(list.get(i).getCasteName()) && pojo.getReligionName().equalsIgnoreCase(list.get(i).getReligionName()) && pojo.getCasteCategoryName().equalsIgnoreCase(list.get(i).getCasteCategoryName())){
							if(cont-1<i){
								flag =false;
							}
						}
					}
					
					if(pojo.getReligionName().equals("")|| pojo.getReligionName().equals("-")){
						vo.setReason("Religion name should not empty");
						failurelist.add(vo);
					}
					else if(pojo.getCasteName().equals("") || pojo.getCasteName().equals("-")){
						vo.setReason("Caste name should not empty");
						failurelist.add(vo);
					}
					else if(pojo.getCasteCategoryName().equals("") || pojo.getCasteCategoryName().equals("-")){
						vo.setReason("Caste Category name should not empty");
						failurelist.add(vo);
					}
					else if(count>1){
						vo.setReason("More than one space bewteen words are not valid");
						failurelist.add(vo);
					}
					else if(specialError.equalsIgnoreCase("true")){
						vo.setReason("Caste Category name will not accept !#$%&*+,.;<=>?@[]^`{|} charecters");
						failurelist.add(vo);
					}
					else if(userId.equalsIgnoreCase("") || userId.equals(null)){
						vo.setReason("Session Time Out, Try again");
						failurelist.add(vo);
					}
					else if(!flag){
						vo.setReason("Duplicate entry in excel sheet");
						failurelist.add(vo);
					}
					else {
						pstmt= connection.prepareStatement("SELECT `religionId` FROM `campus_religion` WHERE `religion`=? and `isActive`='Y'");
						pstmt.setString(1,pojo.getReligionName());
						rs = pstmt.executeQuery();
						if(rs.next()){
								pojo.setReligionId(rs.getString("religionId"));
								
								pstmt1= connection.prepareStatement("SELECT `casteId` FROM `campus_caste` WHERE `caste`=?  and `isActive`='Y'");
								pstmt1.setString(1,pojo.getCasteName());
								rs1 = pstmt1.executeQuery();
								if(rs1.next()){
										pojo.setCasteId(rs1.getString("casteId"));
										successlist.add(pojo);
									}
								else{
										vo.setReason("Caste Name Does not Exists");
										failurelist.add(vo);
								}
								rs1.close();
							}
						else{
							vo.setReason("Religion Name Does not Exists");
							failurelist.add(vo);
						}
						rs.close();
					}
			}
			
			Set<classVo> failureListFromDiompl=new LinkedHashSet<classVo>();
			for (Iterator<classVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				classVo failureDiomplVo = (classVo) it.next();
				classVo uploadCaste = new classVo();
					uploadCaste.setCasteName(failureDiomplVo.getCasteName());
					uploadCaste.setReligionName(failureDiomplVo.getReligionName());
					uploadCaste.setCasteCategoryName(failureDiomplVo.getCasteCategoryName());
					uploadCaste.setReligionId(failureDiomplVo.getReligionId());
					uploadCaste.setCasteId(failureDiomplVo.getCasteId());
					failurelist.add(uploadCaste);
			}
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertCasteCategory(successlist,failurelist,connection,userId,log_audit_session,userLoggingsVo);
			}
	}catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}finally{
		try {
			if(rs!=null && (!rs.isClosed())){
				rs.close();
			}
			if(pstmt!=null && (!pstmt.isClosed())){
				pstmt.close();
			}
			if(pstmt1!=null && (!pstmt1.isClosed())){
				pstmt1.close();
			}
			if(pstmt2!=null && (!pstmt2.isClosed())){
				pstmt2.close();
			}
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
				+ " Control in SettingsXLUploadServiceImpl : insertCasteCategoryExcel : Ending");
	
		return failurelist;
	}





	public Set<classVo> insertSpecXSL(List<ClassPojo> list, String userId,String log_audit_session, UserLoggingsPojo userLoggingsVo, String currentLocId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : insertSpecXSL : Starting");
		Connection connection = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null,pstmt3=null;
		ResultSet rs=null,rs1=null,rs2=null;
		List<ClassPojo> successlist=new ArrayList<ClassPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		
		
		Set<classVo> failurelist = new LinkedHashSet<classVo>();
		failurelist.clear();
		successlist.clear();
		
		try {
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			int cont=0,count=0;

			for (Iterator<ClassPojo> iterator = list.iterator(); iterator.hasNext();) {
				cont++;
				boolean flag=true;
				String checknum="false";
				ClassPojo pojo = (ClassPojo) iterator.next();
				classVo vo = new classVo();
					vo.setLocationName(pojo.getLocationName());
					vo.setStreamName(pojo.getStreamName());
					vo.setClassName(pojo.getClassName());
					vo.setSpecializationName(pojo.getSpecializationName());
					
					for(int i=0;i<list.size();i++){
						if(i==cont-1){
							continue;
						}
						if(pojo.getLocationName().equalsIgnoreCase(list.get(i).getLocationName()) && pojo.getStreamName().equalsIgnoreCase(list.get(i).getStreamName()) && pojo.getClassName().equalsIgnoreCase(list.get(i).getClassName())&& pojo.getSpecializationName().equalsIgnoreCase(list.get(i).getSpecializationName()) ){
							if(cont-1<i){
								flag =false;
							}
						}
					}
					
					for(int i = 0; i < pojo.getSpecializationName().length(); i++) {
					     if(Character.isWhitespace(pojo.getSpecializationName().charAt(i))){
					    	 count++;
					     }
					     else{
					    	 if(count<2){
					    		 count=0;
					    	 }
					     }
					}
					
					for(int i = 0; i < pojo.getSpecializationName().length(); i++) {
					if(Character.isDigit(pojo.getSpecializationName().charAt(0)))
						checknum="true";
					}
					
					if(pojo.getStreamName().equals("") || pojo.getStreamName().equals("-")){
						vo.setReason("Stream should not empty");
						failurelist.add(vo);
					}
					else if(count>1){
						vo.setReason("More than one space bewteen words are not valid");
						failurelist.add(vo);
					}
					else if(checknum.equalsIgnoreCase("true")){
						vo.setReason("Specialization should not begin with numbers");
						failurelist.add(vo);
					}
					else if(pojo.getLocationName().equals("") || pojo.getLocationName().equals("-")){
						vo.setReason("Branch should not empty");
						failurelist.add(vo);
					}
					else if(pojo.getClassName().equals("") || pojo.getClassName().equals("-")){
						vo.setReason("Class should not empty");
						failurelist.add(vo);
					}
					else if(pojo.getSpecializationName().equals("") || pojo.getSpecializationName().equals("-")){
						vo.setReason("Specialization should not be empty");
						failurelist.add(vo);
					}
					else if(!flag){
						vo.setReason("Duplicate entry in excel sheet");
						failurelist.add(vo);
					}
					else {
						//successlist.add(pojo);
					
						pstmt= connection.prepareStatement("SELECT `Location_Id` FROM `campus_location` WHERE `Location_Name`=? and `isActive`='Y'");
						pstmt.setString(1,pojo.getLocationName());
						rs = pstmt.executeQuery();
						if(rs.next()){
							
							if(currentLocId.equals(rs.getString("Location_Id"))){
								pojo.setLocationId(rs.getString("Location_Id"));
								
								pstmt1= connection.prepareStatement("SELECT `classstream_id_int` FROM `campus_classstream` WHERE `classstream_name_var`=? and locationId=? and `isActive`='Y'" );
								pstmt1.setString(1,pojo.getStreamName());
								pstmt1.setString(2,pojo.getLocationId());
								rs1 = pstmt1.executeQuery();
								if(rs1.next()){
										pojo.setStreamId(rs1.getString("classstream_id_int"));
										
										pstmt3= connection.prepareStatement("SELECT `classdetail_id_int` FROM `campus_classdetail` WHERE `classdetails_name_var`=? AND `classstream_id_int`=? AND `locationId`=?  and `isActive`='Y'");
										pstmt3.setString(1,pojo.getClassName());
										pstmt3.setString(2,pojo.getStreamId());
										pstmt3.setString(3,pojo.getLocationId());
										rs2 = pstmt3.executeQuery();
										if(rs2.next()){
											pojo.setClassId(rs2.getString("classdetail_id_int"));
											successlist.add(pojo);
										}
										else{
											vo.setReason("Class is not mapped with Location and Stream");
											failurelist.add(vo);
										}
										rs2.close();
									}
								else{
									vo.setReason("Stream is not mapped with Location");
									failurelist.add(vo);
								}
								rs1.close();
							}
							else{
								vo.setReason("Incorrect Branch Name.");
								failurelist.add(vo);
							}
						}
						else{
							vo.setReason("Incorrect Branch Name.");
							failurelist.add(vo);
						}
						rs.close();
					}
			}
			Set<classVo> failureListFromDiompl=new LinkedHashSet<classVo>();
			for (Iterator<classVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				classVo failureDiomplVo = (classVo) it.next();
				classVo uploadStream = new classVo();

				uploadStream.setLocationName(failureDiomplVo.getLocationName());
				uploadStream.setStreamName(failureDiomplVo.getStreamName());
				uploadStream.setClassName(failureDiomplVo.getClassName());
				uploadStream.setSpecializationName(failureDiomplVo.getSpecializationName());
				uploadStream.setReason(failureDiomplVo.getReason());
				failurelist.add(uploadStream);
			}
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertSpec(successlist,failurelist,connection,userId,log_audit_session,userLoggingsVo);
			}
	}catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}finally{
		try {
			if(rs!=null && (!rs.isClosed())){
				rs.close();
			}
			if(rs1!=null && (!rs1.isClosed())){
				rs1.close();
			}
			if(rs2!=null && (!rs2.isClosed())){
				rs2.close();
			}
			
			if(pstmt!=null && (!pstmt.isClosed())){
				pstmt.close();
			}
			if(pstmt1!=null && (!pstmt1.isClosed())){
				pstmt1.close();
			}
			if(pstmt2!=null && (!pstmt2.isClosed())){
				pstmt2.close();
			}
			if(pstmt3!=null && (!pstmt3.isClosed())){
				pstmt3.close();
			}if(connection!=null && (!connection.isClosed())){
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
				+ " Control in SettingsXLUploadServiceImpl : insertSpecXSL : Ending");
		return failurelist;
	}




	public Set<classVo> insertHolidayXSL(List<ClassPojo> list, String userId, String accyear, String log_audit_session, UserLoggingsPojo userLoggingsVo, String currentLocId) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : insertHolidayXSL : Starting");
		
		Connection connection = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null,pstmt11=null;
		ResultSet rs=null,rs1=null,rs2 = null;
		int count4=0;
		List<ClassPojo> successlist=new ArrayList<ClassPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		
		Set<classVo> failurelist = new LinkedHashSet<classVo>();
		failurelist.clear();
		successlist.clear();
		
		try {
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			int cont=0;
			String specialCharacters="!#$%&*+;<=>?@[]^`{|}";
			
			String dateregex = "^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$";
			String specialError="false";
			String checknum="false";
			
			
			int count=0;
			String dateformat=null;

			for (Iterator<ClassPojo> iterator = list.iterator(); iterator.hasNext();) {
				boolean flag=true;
				boolean flag1=true;
				boolean dateflag =true;
				boolean flagYear=true;
				boolean monthExceedFlag=true;
				boolean flag3=true;
				cont++;
				
				ClassPojo pojo = (ClassPojo) iterator.next();
				classVo vo = new classVo();
					vo.setLocationName(pojo.getLocationName());
					vo.setHolidayDate(pojo.getHolidayDate());
					vo.setHolidayName(pojo.getHolidayName());
					vo.setHolidaytype(pojo.getHolidaytype());
					
					String[] holidayTypeList ={"First Half","Second Half","Full Day"};
					
					dateformat=pojo.getHolidayDate();
			
					String data[] = dateformat.split("[-/]");
					String year = data[2];
					String date = data[0];
					String month = data[1];
					int chkyear=Integer.parseInt(year);
					int chkdate=Integer.parseInt(date);
					int chkmonth=Integer.parseInt(month);
				
					if ( chkyear % 100 == 0 ){
						if(chkyear % 400 == 0 && chkdate >= 29  && chkmonth==2  ){
							flagYear = true;
						}
						else{
							if(chkdate >= 29 && chkmonth==2 )
								flagYear = false;
						}
					}else{
						if(chkyear % 4 == 0 && chkdate >= 29 && chkmonth==2 ){
							flagYear = true;
						}
						else{
							if(chkdate >= 29 && chkmonth==2 )
							flagYear = false;
						}
					}
					
					
					if( (chkmonth==1 || chkmonth==3 || chkmonth==5 || chkmonth==7 || chkmonth==8 || chkmonth==10 ||  chkmonth==12) && chkdate>31 ){
						monthExceedFlag=false;
						
					}
					else if( (chkmonth==4 || chkmonth==6 || chkmonth==9 || chkmonth==11) && chkdate>30 ){
						monthExceedFlag=false;
					}
					
					for (int i = 0; i < pojo.getHolidayName().length(); i++) {
					    if (specialCharacters.contains(Character.toString(pojo.getHolidayName().charAt(i))))
					    {
					    	specialError="true";
					    }  
					  }
					
					for(int i = 0; i < pojo.getHolidayName().length(); i++) {
					     if(Character.isWhitespace(pojo.getHolidayName().charAt(i))){
					    	 count++;
					     }
					     else{
					    	 if(count<2){
					    		 count=0;
					    	 }
					     }
					}
					
				
					for(int i=0;i<list.size();i++){
						
						
						if(i==cont-1){
							continue;
						}
						if(pojo.getLocationName().equalsIgnoreCase(list.get(i).getLocationName()) && pojo.getHolidayDate().equalsIgnoreCase(list.get(i).getHolidayDate()) && pojo.getHolidayName().equalsIgnoreCase(list.get(i).getHolidayName())&& pojo.getHolidaytype().equalsIgnoreCase(list.get(i).getHolidaytype()) ){
							if(cont-1<i){
								flag =false;
							}
						}
						else if(pojo.getLocationName().equalsIgnoreCase(list.get(i).getLocationName()) && pojo.getHolidayDate().equalsIgnoreCase(list.get(i).getHolidayDate()) ){
							if(cont-1<i){
								flag1 =false;
							}
						}
					}
					
					for(int i = 0; i < pojo.getHolidayName().length(); i++) {
					if(Character.isDigit(pojo.getHolidayName().charAt(0)))
						checknum="true";
					}
				
						pstmt= connection.prepareStatement("SELECT `Location_Id` FROM `campus_location` WHERE `Location_Name`=?  and `isActive`='Y'");
						pstmt.setString(1, pojo.getLocationName());
						rs = pstmt.executeQuery();
						if(rs.next()){
							
								if(pojo.getHolidayName().equals("") || pojo.getHolidayName().equals("-")){
									vo.setReason("Holiday name should not be empty");
									failurelist.add(vo);
								}
								else if(count>1){
									vo.setReason("More than one space bewteen words are not valid");
									failurelist.add(vo);
								}
								else if(checknum.equalsIgnoreCase("true")){
									vo.setReason("Holiday name should not begin with numbers");
									failurelist.add(vo);
								}
								else {
								/*	
									if(currentLocId.equals(anObject)){*/
									if(currentLocId.equals(rs.getString("Location_Id"))){
									pojo.setLocationId(rs.getString("Location_Id"));
									pstmt1= connection.prepareStatement("SELECT `acadamic_id`,`acadamic_year`,`startDate`,`endDate` FROM `campus_acadamicyear` WHERE ?  BETWEEN startDate AND endDate");
									pstmt1.setString(1, HelperClass.convertUIToDatabaseSettingReports(vo.getHolidayDate()));
									rs1 = pstmt1.executeQuery();
									
									if(rs1.next()){
											pojo.setAccyearId(rs1.getString("acadamic_id"));
											
											pstmt2= connection.prepareStatement("SELECT COUNT(*) FROM `campus_holidaymaster` WHERE  HOLIDAY_DATE=? and `LOC_ID`=? and `HOLIDAY_NAME`=? AND `CURRENT_YEAR`=?  and `isActive`='Y'");
											pstmt2.setString(1, HelperClass.convertUIToDatabaseSettingReports(pojo.getHolidayDate()));
											pstmt2.setString(2, pojo.getLocationId());
											pstmt2.setString(3, pojo.getHolidayName());
											pstmt2.setString(4, pojo.getAccyearId());
											rs2 = pstmt2.executeQuery();
											while(rs2.next()){
												count4= rs2.getInt(1);
											}
											rs2.close();	
											if(count4 > 0){
												vo.setReason("Duplicate Holiday setup in Location");
												failurelist.add(vo);
											}	else{
												 if(pojo.getHolidayDate().equals("") || pojo.getHolidayDate().equals("-")){
													vo.setReason("Holiday Date should not be empty");
													failurelist.add(vo);
												 }
												else if(pojo.getHolidayDate().equals("") || pojo.getHolidayDate().equals("-")){
													vo.setReason("Holiday Date should not be empty");
													failurelist.add(vo);
												 }
												else if(!(Arrays.asList(holidayTypeList).contains(pojo.getHolidaytype()))){
													vo.setReason("Invalid Holiday type");
													failurelist.add(vo);
												 }
												else if(!flag){
													vo.setReason("Duplicate entry in Excel sheet");
													failurelist.add(vo);
												 }
												else if(!flag1){
													vo.setReason("Duplicate Holiday date with Location");
													failurelist.add(vo);
												 }
												else if(!flagYear){
													vo.setReason("29 February "+year+" not available");
													failurelist.add(vo);
												 }
												else if(!monthExceedFlag){
													vo.setReason("date:"+date+" not valid for month: "+month+" ");
													failurelist.add(vo);
												 }
												else {
													successlist.add(pojo);
												}
											}
										}
									else{
										vo.setReason("Invalid Academic year / date format");
										failurelist.add(vo);
									   }
								 }
									else{
										vo.setReason("Incorrect/Inactive Branch Name");
										failurelist.add(vo);
									}
							
							rs1.close();
						}
						}
						else{
							
							if(pojo.getLocationName().equals("") || pojo.getLocationName().equals("-")){
								vo.setReason("Location name should not empty");
								failurelist.add(vo);
							}
							else {
								vo.setReason("Invalid/Inactive Location Name");
							}
							
							failurelist.add(vo);
						}
						rs.close();
						
						
					
			}
			Set<classVo> failureListFromDiompl=new LinkedHashSet<classVo>();
			for (Iterator<classVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				classVo failureDiomplVo = (classVo) it.next();
				classVo uploadStream = new classVo();

				uploadStream.setLocationName(failureDiomplVo.getLocationName());
				uploadStream.setLocationId(failureDiomplVo.getLocationId());
				uploadStream.setHolidayDate(failureDiomplVo.getHolidayDate());
				uploadStream.setHolidayName(failureDiomplVo.getHolidayName());
				uploadStream.setHolidaytype(failureDiomplVo.getHolidaytype());
				uploadStream.setAccyearName(failureDiomplVo.getAccyearName());
				uploadStream.setReason(failureDiomplVo.getReason());
				failurelist.add(uploadStream);
			}
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertHoliday(successlist,failurelist,connection,userId,accyear,log_audit_session,userLoggingsVo);
			}
			
	}catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}finally{
		try {
			if(rs!=null && (!rs.isClosed())){
				rs.close();
			}
			if(rs1!=null && (!rs1.isClosed())){
				rs1.close();
			}
			if(rs2!=null && (!rs2.isClosed())){
				rs2.close();
			}
			if(pstmt!=null && (!pstmt.isClosed())){
				pstmt.close();
			}
			if(pstmt1!=null && (!pstmt1.isClosed())){
				pstmt1.close();
			}
			if(pstmt2!=null && (!pstmt2.isClosed())){
				pstmt2.close();
			}
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
				+ " Control in SettingsXLUploadServiceImpl : insertHolidayXSL : Ending");
		return failurelist;
	}

	
	public Set<studentExcelUploadVo> saveStudentExam(List<StudentExcelUploadPojo> list, String log_audit_session,UserLoggingsPojo userLoggingsVo, UserDetailVO user) throws SQLException, NumberFormatException {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : insertStreamXSL : Starting");
		
		Connection connection = null;
		PreparedStatement pstmt=null;
		PreparedStatement pstm1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null,pstmt4=null,pstmt5=null,pstmt6=null,pstmt7=null,pstm2=null,pstmt1=null;
		ResultSet rs1=null,rs2=null,rs=null,rs3=null,rs4=null,rs5=null,rs6=null,rs7=null,rs8=null,rs0=null;
		List<StudentExcelUploadPojo> successlist=new ArrayList<StudentExcelUploadPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		Set<studentExcelUploadVo> failureListFromDiompl=new LinkedHashSet<studentExcelUploadVo>();
		Set<studentExcelUploadVo> failurelist = new LinkedHashSet<studentExcelUploadVo>();
		failurelist.clear();
		successlist.clear();
		
		try {
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			int cont=0;
			String id =null;
			String name=null;
			String studentName=null;
			String accyaerId = null;
			String startDateCheck = null;
			String endDateCheck = null;
			
			
			//String int_regex="^[0-9a-zA-Z]*$";
			String string_regx="^[a-zA-Z0-9_ ]*$";

			String int_regex = "[0-9]+";
			
			for (Iterator<StudentExcelUploadPojo> iterator = list.iterator(); iterator.hasNext();) {
				
				String examType[] ={"Periodic Exam","Half Yearly Exam","Yearly Exam"};
				String attendance[] = {"Present","present","PRESENT","Absent","ABSENT","absent"};
				int count=0;
				cont++;
				int count1=0;
				StudentExcelUploadPojo pojo = (StudentExcelUploadPojo) iterator.next();
				studentExcelUploadVo vo = new studentExcelUploadVo();
					vo.setStudentName(pojo.getStudentName());
					vo.setStudentIdNo(pojo.getStudentIdNo());
					vo.setSchoolName(pojo.getSchoolName());
					vo.setAcademicYear(pojo.getAcademicYear());
					vo.setStreamName(pojo.getStreamName());
					vo.setClassName(pojo.getClassName());
					vo.setSectionName(pojo.getSectionName());
					vo.setAttandance(pojo.getAttandance());
					vo.setExamType(pojo.getExamType());
					vo.setExamName(pojo.getExamName());
					vo.setExamCode(pojo.getExamCode());
					vo.setStartdate(pojo.getStartdate());
					vo.setEnddate(pojo.getEnddate());
					vo.setSubjectName(pojo.getSubjectName());
					String academicId=daoImpl.getAccyearId(pojo.getAcademicYear(),connection);
					String schoolId = daoImpl.getLocationId(pojo.getSchoolName(),connection);
					String studentId= daoImpl.getStudentId(pojo.getStudentIdNo(),connection);
					String examTypeId = daoImpl.getExamTypeId(pojo.getExamType(),connection).split(",")[0];
					String examTypePrefix = daoImpl.getExamTypeId(pojo.getExamType(),connection).split(",")[1];
					String streamId = daoImpl.getstreamId(pojo.getStreamName(),schoolId,connection);
					String classId = daoImpl.getClassId(pojo.getClassName(),streamId,schoolId,connection);
					String subjectId = daoImpl.getsubjectId(pojo.getSubjectName(),schoolId,classId,connection);
					String sectionId = daoImpl.getsectionId(pojo.getSectionName(),classId,schoolId,connection);
					String checkValidStartEnddates =  daoImpl.checkValidStartEnddates(pojo.getStartdate(),pojo.getAcademicYear(),connection);
					String examId = daoImpl.getExamIdValue(pojo.getExamCode(),schoolId,academicId,examTypeId,classId,connection,pojo.getExamName());
					String examCode = daoImpl.getexamCode(pojo.getExamName(),pojo.getExamCode(),connection);
					List<studentExcelUploadVo> dates = daoImpl.getstartEndDates(examTypeId,classId,pojo.getExamName(),academicId,schoolId,connection);
					for (studentExcelUploadVo s : dates){
						startDateCheck = s.getStartdateCheck();
						endDateCheck = s.getEnddateCheck();
					}
					id = daoImpl.checkStudentIdNoMatchId(pojo.getStudentName(),sectionId,connection,pojo.getStudentIdNo());
					studentName = daoImpl.checkStudentIdNoMatchName(pojo.getStudentName(),sectionId,connection,pojo.getStudentIdNo());
					pojo.setAccyearId(academicId);
					pojo.setLocationId(schoolId);
					pojo.setExamType(examTypeId);
					pojo.setExamTypeName(vo.getExamType());
					pojo.setStreamId(streamId);
					pojo.setClassId(classId);
					pojo.setSubjectId(subjectId);
					pojo.setSectionId(sectionId);
					pojo.setExamId(examId);
					pojo.setStudentId(studentId);
					pojo.setExamTypePrefix(examTypePrefix);
					
					
					if(pojo.getNotebookMaximumMarks().trim().equals("")){
						vo.setNotebookMaximumMarks("0");
					}
					else{
						vo.setNotebookMaximumMarks(pojo.getNotebookMaximumMarks());
					}
					
					if(pojo.getNotebookScoredMarks().trim().equals("")){
						vo.setNotebookScoredMarks("0");
					}
					else{
						vo.setNotebookScoredMarks(pojo.getNotebookScoredMarks());
					}
					
					if(pojo.getSubjectMaximumMarks().trim().equals("")){
						vo.setSubjectMaximumMarks("0");
					}
					else{
						vo.setSubjectMaximumMarks(pojo.getSubjectMaximumMarks());
					}
					
					if(pojo.getSubjectScoredMarks().trim().equals("")){
						vo.setSubjectScoredMarks("0");
					}
					else if(!(pojo.getSubjectScoredMarks().matches(int_regex))){
						vo.setReason("Invalid Subject Scored Marks");
						failurelist.add(vo);	
					}
					else{
						vo.setSubjectScoredMarks(pojo.getSubjectScoredMarks());
					}
					
					if(pojo.getTestMaximumMarks().trim().equals("")){
						vo.setTestMaximumMarks("0");
					}
					else{
						vo.setTestMaximumMarks(pojo.getTestMaximumMarks());
					}
					
					if(pojo.getTestScoredMarks().trim().equals("")){
						vo.setTestScoredMarks("0");
					}
					else{
						vo.setTestScoredMarks(pojo.getTestScoredMarks());
					}
					
					if(!(vo.getTestMaximumMarks().matches(int_regex))){
						vo.setReason("Invalid Test Scored Marks");
						failurelist.add(vo);	
					}
					else if(!(vo.getTestMaximumMarks().matches(int_regex))){
							vo.setReason("Invalid Test Maximum Marks");
							failurelist.add(vo);
					}
					int maxTestmarks=0,scoredTestmarks = 0,notebookMaxmarks = 0,notebookScoredMarks = 0,subjectMaxmarks = 0,subjectScoredMarks = 0;
					boolean scoredTestmarksValidation=true,notebookMaxmarksValidation=true,subjectMaxmarksValidation=true,subjectScoredMarksValidation=true,maxTestmarksValidation=true,notebookScoredMarksValidation=true,emptyMaximumMarks=true;
					

					if(examTypePrefix.trim().equalsIgnoreCase("prdxm")){
						 if(pojo.getTestMaximumMarks().trim().equals("")||pojo.getTestMaximumMarks().trim().equals("0")){
							 emptyMaximumMarks=false;
						}else if(pojo.getTestScoredMarks().trim().equals("")){
							 emptyMaximumMarks=false;
						}
					}
					else{
						 if(pojo.getNotebookMaximumMarks().trim().equals("")||pojo.getNotebookMaximumMarks().trim().equals("0")){
							 emptyMaximumMarks=false;
						}
						else if(pojo.getSubjectMaximumMarks().trim().equals("")){
							
							 emptyMaximumMarks=false;
						}
						else if(pojo.getTestMaximumMarks().trim().equals("")||pojo.getTestMaximumMarks().trim().equals("0")){
							
							 emptyMaximumMarks=false;
						}
						else if(pojo.getSubjectScoredMarks().trim().equals("")){
							
							emptyMaximumMarks=false;
						}
						else if(pojo.getTestScoredMarks().trim().equals("")){
							
							emptyMaximumMarks=false;
						}
						else if(pojo.getNotebookScoredMarks().trim().equals("")){
							
							emptyMaximumMarks=false;
						}
					}
				
					
					
					try {
						maxTestmarks = Integer.parseInt(vo.getTestMaximumMarks());
					} catch (NumberFormatException e) {
						maxTestmarksValidation=false;
					}
					
					try {
						scoredTestmarks = Integer.parseInt(vo.getTestScoredMarks());
					} catch (NumberFormatException e) {
						scoredTestmarksValidation=false;
					}
					
					
					try {
						notebookMaxmarks = Integer.parseInt(vo.getNotebookMaximumMarks());
					} catch (NumberFormatException e) {
						notebookMaxmarksValidation=false;
					}
					
					try {
						notebookScoredMarks = Integer.parseInt(vo.getNotebookScoredMarks());
					} catch (NumberFormatException e) {
						notebookScoredMarksValidation=false;
					}
				
					
					try {
						subjectMaxmarks = Integer.parseInt(vo.getSubjectMaximumMarks());
					} catch (NumberFormatException e) {
						subjectMaxmarksValidation=false;
					}
					
					try {
						subjectScoredMarks = Integer.parseInt(vo.getSubjectScoredMarks());
					} catch (NumberFormatException e) {
						subjectScoredMarksValidation=false;
					}
					
					pstm2=connection.prepareStatement("UPDATE campus_examination SET `isapplicableperiodic`= CASE WHEN `examtype` IN ('EMT3','EMT1') THEN 'N' ELSE 'Y' END");
					pstm2.executeUpdate();
					pstm2.close();
					
					if(pojo.getStudentName().trim().equals("") || pojo.getStudentName().equals("-")){
						vo.setReason("Student Name Can Not Be Empty");
						failurelist.add(vo);
					}
					else if(studentId.equals("notfound") || pojo.getStudentId().equalsIgnoreCase("")){
						vo.setReason("Invalid Student Name ");
						failurelist.add(vo);
					}
					
					else if(schoolId.equals("notfound") || pojo.getSchoolName().equalsIgnoreCase("")){
						vo.setReason("Invalid Location Name");
						failurelist.add(vo);	
					}
					else if(pojo.getAcademicYear().equals("")){
						vo.setReason("Academic Year Can Not Be Empty");
						failurelist.add(vo);
					}
					else if(academicId.equals("notfound") || pojo.getAcademicYear().equalsIgnoreCase("") ){
						vo.setReason("Invalid Academic Year");
						failurelist.add(vo);	
					}
					else if(pojo.getStreamName().equals("")){
						vo.setReason("Stream Name Can Not Be Empty");
						failurelist.add(vo);
					}
					else if(streamId.equals("notfound")){
						vo.setReason("Invalid Stream Name");
						failurelist.add(vo);
					}
					else if(pojo.getClassName().equals("")){
						vo.setReason("Class Name Can Not Be Empty");
						failurelist.add(vo);
					}
					else if(classId.equals("notfound")){
						vo.setReason("Invalid Class Name");
						failurelist.add(vo);
					}
					else if(sectionId.equals("notfound")){
						vo.setReason("Invalid Section Name");
						failurelist.add(vo);
					}
					else if(pojo.getSectionName().equals("")){
						vo.setReason("Section Name Can Not Be Empty");
						failurelist.add(vo);
					}
					else if(!(pojo.getStudentName().trim().equals(studentName.trim()))){
						////System.out.println(pojo.getStudentName()+"  Match "+studentName);
						vo.setReason("Student Name is not matching with Student ID, Class and Section ");
						failurelist.add(vo);
					}
					else if(pojo.getExamType().trim().equals("") || pojo.getExamType()==""){
						vo.setReason("Exam Type Can Not Be Empty");
						failurelist.add(vo);
					}
					else if(pojo.getStudentIdNo().equals("")){
						vo.setReason("Student ID No Should Not Be Empty");
						failurelist.add(vo);
					}
					else if(!(pojo.getStudentIdNo().equals(id))){
						vo.setReason("Student ID No not matching with Student Name, Class and Section");
						failurelist.add(vo);
					}
					else if(pojo.getExamName().equals("")){
						vo.setReason("Exam Name Can Not Be Empty");
						failurelist.add(vo);
					}
					else if(examId.equals("notfound")){
						vo.setReason("Invalid Exam Name");
						failurelist.add(vo);
					}
					else if(pojo.getExamCode().trim().equals("")){
						vo.setReason("Exam Code Can Not Be Empty");
						failurelist.add(vo);
					}
					else if(examCode.equals("notfound")){
						vo.setReason("Exam Code is not matching with Exam Name");
						failurelist.add(vo);
					}
					
					else if(examTypeId.equals("notfound")){
						vo.setReason("Invalid/Empty Exam Type");
						failurelist.add(vo);
					}
				
					else if(checkValidStartEnddates.equals("notfound")){
						vo.setReason("Not Valid Start and End Dates");
						failurelist.add(vo);
					}
					else if(subjectId.equals("notfound")){
						vo.setReason("Invalid Subject Name");
						failurelist.add(vo);
					}
					else if(pojo.getAttandance().trim().equals("")){
						vo.setReason("Invalid/empty Attendance");
						failurelist.add(vo);
					}
					else if(!pojo.getAttandance().trim().equalsIgnoreCase("present")&&!pojo.getAttandance().trim().equalsIgnoreCase("absent")){
						
						vo.setReason("Invalid/empty Attendance");
						failurelist.add(vo);
					}
					else if(!emptyMaximumMarks){
						vo.setReason("Max Marks/Obtained Marks Should Not Be Empty");
						failurelist.add(vo);
					}
					else if(!(notebookMaxmarksValidation)){
						vo.setReason("Invalid Notebook Maximum Marks");
						failurelist.add(vo);	
					}
					else if(!(notebookScoredMarksValidation)){
						vo.setReason("Invalid Notebook Scored Marks");
						failurelist.add(vo);	
					}
					else if( scoredTestmarks > maxTestmarks){
						vo.setReason("Test Scored Marks cannot be Greater than Maximim Marks");
						failurelist.add(vo);	
					}
					else if(!(subjectMaxmarksValidation)){
						vo.setReason("Invalid Subject Maximum Marks");
						failurelist.add(vo);	
					}
					else if(!(subjectScoredMarksValidation)){
						vo.setReason("Invalid Subject Scored Marks");
						failurelist.add(vo);	
					}
					else if( subjectScoredMarks > subjectMaxmarks){
						vo.setReason("Subject Scored Marks cannot be Greater than Maximim Marks");
						failurelist.add(vo);
					}
					else if(!(scoredTestmarksValidation)){
						vo.setReason("Invalid test Scored Marks");
						failurelist.add(vo);	
					}
					else if(!(maxTestmarksValidation)){
						vo.setReason("Invalid test Maximum Marks");
						failurelist.add(vo);	
					}
					else if( notebookScoredMarks > notebookMaxmarks){
						vo.setReason("Notebook Scored Marks cannot be Greater than Maximim Marks");
						failurelist.add(vo);
					}
					else if(count>0){
						vo.setReason("Duplicate serviceImpl");
						failurelist.add(vo);
					}
					else{
						successlist.add(pojo);
					}
					
				}//for loop
			
			for (Iterator<studentExcelUploadVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				studentExcelUploadVo failureDiomplVo = (studentExcelUploadVo) it.next();
				studentExcelUploadVo uploadStream = new studentExcelUploadVo();

				uploadStream.setAcademicYear(failureDiomplVo.getAcademicYear());
				uploadStream.setStudentName(failureDiomplVo.getStudentName());
				uploadStream.setStudentIdNo(failureDiomplVo.getStudentIdNo());
				uploadStream.setSchoolName(failureDiomplVo.getSchoolName());
				uploadStream.setClassName(failureDiomplVo.getClassName());
				uploadStream.setSectionName(failureDiomplVo.getSectionName());
				uploadStream.setExamType(failureDiomplVo.getExamType());
				uploadStream.setExamName(failureDiomplVo.getExamName());
				uploadStream.setExamCode(failureDiomplVo.getExamCode());
				uploadStream.setStartdate(failureDiomplVo.getStartdate());
				uploadStream.setEnddate(failureDiomplVo.getEnddate());
				uploadStream.setSubjectName(failureDiomplVo.getSubjectName());
				uploadStream.setAttandance(failureDiomplVo.getAttandance());
				uploadStream.setTestMaximumMarks(failureDiomplVo.getTestMaximumMarks());
				uploadStream.setTestScoredMarks(failureDiomplVo.getTestScoredMarks());
				uploadStream.setNotebookMaximumMarks(failureDiomplVo.getNotebookMaximumMarks());
				uploadStream.setNotebookScoredMarks(failureDiomplVo.getNotebookScoredMarks());
				uploadStream.setSubjectMaximumMarks(failureDiomplVo.getSubjectMaximumMarks());
				uploadStream.setSubjectScoredMarks(failureDiomplVo.getSubjectScoredMarks());
				failurelist.add(uploadStream);
			}
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertExamReport(successlist,failurelist,connection,userLoggingsVo,log_audit_session,user);
			}
		}//try
		catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}
		
		finally{

		try{
			if(pstmt !=null && !(pstmt.isClosed())){
				pstmt.close();
			}
			 if(pstm1 !=null &&!(pstm1.isClosed())){
				pstm1.close();
			}
			 if(pstmt2 !=null && !(pstmt2.isClosed())){
				pstmt2.close();
			}
			 if(pstmt3 !=null && !(pstmt3.isClosed())){
				pstmt3.close();
			}
			 if(pstmt4 !=null && !(pstmt4.isClosed())){
				pstmt4.close();
			}
			 if(pstmt5 !=null && !(pstmt5.isClosed())){
				pstmt5.close();
			}
			 if(pstmt6 !=null && !(pstmt6.isClosed())){
				pstmt6.close();
			}
			
			 if(pstmt7 !=null && !(pstmt7.isClosed())){
				pstmt7.close();
			}
			 if(pstm2 !=null && !(pstm2.isClosed())){
				pstm2.close();
			}
			 if(pstmt1 !=null && !(pstmt1.isClosed())){
				pstmt1.close();
			}
			 if(rs1 !=null && !(rs1.isClosed())){
				rs1.close();
			}
			 if(rs2 !=null && !(rs2.isClosed())){
				rs2.close();
			}
			 if(rs !=null && !(rs.isClosed())){
				rs.close();
			}
			 if(rs3 !=null && !(rs3.isClosed())){
				rs3.close();
			}
			 if(rs4 !=null && !(rs4.isClosed())){
				rs4.close();
			}
			 if(rs5 !=null && !(rs5.isClosed())){
				rs5.close();
			}
			 if(rs6 !=null && !(rs6.isClosed())){
				rs6.close();
			}
			if(rs7 !=null && (!rs7.isClosed())){
				rs7.close();
			}
			 if(rs8 !=null && (!rs8.isClosed())){
				rs8.close();
			}
			 if (connection != null && (!connection.isClosed())) {
				 connection.close();
				}
		}	
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : insertStreamXSL : Ending");
		return failurelist;
}
	public Set<studentExcelUploadVo> SaveGradeSetting(List<StudentExcelUploadPojo> list, String userId, String log_audit_session,UserLoggingsPojo userLoggingsVo, String currentLoc) {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : SaveGradeSetting : Starting");
		Connection connection = null;
		PreparedStatement pstmt=null;
		PreparedStatement pstm1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null,pstmt4=null,pstmt5=null,pstmt6=null,pstmt7=null,pstm2=null,pstmt1=null;
		ResultSet rs1=null,rs2=null,rs=null,rs3=null,rs4=null,rs5=null,rs6=null,rs7=null,rs8=null,rs0=null;
		List<StudentExcelUploadPojo> successlist=new ArrayList<StudentExcelUploadPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		Set<studentExcelUploadVo> failureListFromDiompl=new LinkedHashSet<studentExcelUploadVo>();
		Set<studentExcelUploadVo> failurelist = new LinkedHashSet<studentExcelUploadVo>();
		failurelist.clear();
		successlist.clear();
		
		try {
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			int cont=0;
			String string_regx="^[a-zA-Z0-9_ ]*$";
			String int_regex = "\\d+";
			String ExamTypeName=null;
			for (Iterator<StudentExcelUploadPojo> iterator = list.iterator(); iterator.hasNext();) {
				
				String grade[] ={"A","B","C","D","E"};
				String examType[] ={"Periodic Exam","Half Yearly Exam","Yearly Exam"};
				int count=0;
				cont++;
				int count1=0;
				StudentExcelUploadPojo pojo = (StudentExcelUploadPojo) iterator.next();
				studentExcelUploadVo vo = new studentExcelUploadVo();
					vo.setStudentName(pojo.getStudentName());
					vo.setStudentIdNo(pojo.getStudentIdNo());
					vo.setSchoolName(pojo.getSchoolName());
					vo.setAcademicYear(pojo.getAcademicYear());
					vo.setStreamName(pojo.getStreamName());
					vo.setClassName(pojo.getClassName());
					vo.setSectionName(pojo.getSectionName());
					vo.setAttandance(pojo.getAttandance());
					vo.setExamType(pojo.getExamType());
					vo.setWorkEducation(pojo.getWorkEducation());
					vo.setArtEducation(pojo.getArtEducation());
					vo.setHealthEducation(pojo.getHealthEducation());
					vo.setDescipline(pojo.getDescipline());
					vo.setRemarks(pojo.getRemarks());
					
					
					 ExamTypeName = pojo.getExamType();
					//System.out.println("pojo.getStudentIdNo()::::::::::"+pojo.getStudentIdNo());
					/*System.out.println("pojo.getStudentIdNo()::::::::::"+pojo.getStudentIdNo());*/

					 if(!(Arrays.asList(grade).contains(pojo.getWorkEducation().trim()))){
						vo.setReason("Invalid Work Education Grades");
						failurelist.add(vo);	
					}else if(!(Arrays.asList(grade).contains(pojo.getArtEducation().trim()))){
						vo.setReason("Invalid Art Education Grades");
						failurelist.add(vo);	
					}else if(!(Arrays.asList(grade).contains(pojo.getHealthEducation().trim()))){
						vo.setReason("Invalid Health & Physical Grades");
						failurelist.add(vo);	
					}else if(!(Arrays.asList(grade).contains(pojo.getDescipline().trim()))){
						vo.setReason("Invalid Descipline Grade");
						failurelist.add(vo);	
					}
					else if(!(Arrays.asList(examType).contains(pojo.getExamType().trim()))){
						vo.setReason("Invalid Exam Type");
						failurelist.add(vo);
					}
					else {
						pstmt= connection.prepareStatement("SELECT `acadamic_id` FROM `campus_acadamicyear` WHERE `acadamic_year`=? ");
						pstmt.setString(1,pojo.getAcademicYear());
						rs = pstmt.executeQuery();
						if(rs.next()){
								pojo.setAccyearId(rs.getString("acadamic_id"));
								pstm1=connection.prepareStatement("SELECT `Location_Id` FROM `campus_location`WHERE `Location_Name`=? and `isActive`='Y'");
								pstm1.setString(1,pojo.getSchoolName());
								rs1 = pstm1.executeQuery();
								if(rs1.next()){
									
									if(currentLoc.equals(rs1.getString("Location_Id"))){
									pojo.setLocationId(rs1.getString("Location_Id"));
									
										pstmt2=connection.prepareStatement("SELECT `student_id_int` FROM `campus_student` WHERE student_admissionno_var=?");
										pstmt2.setString(1,pojo.getStudentIdNo());
										rs2 = pstmt2.executeQuery();
										if(rs2.next()){
											pojo.setStudentId(rs2.getString("student_id_int"));
													pstm1=connection.prepareStatement("SELECT `examtypeid`,exam_prefix FROM `campus_examtype` WHERE `examtypename`=?");
													pstm1.setString(1,pojo.getExamType());
													if(rs8.next()){
														pojo.setExamType(rs8.getString("examtypeid"));
														pojo.setExamTypePrefix(rs8.getString("exam_prefix"));
														
														pstmt4=connection.prepareStatement("SELECT `classstream_id_int` FROM `campus_classstream` WHERE `classstream_name_var`=? AND `locationId`=? and `isActive`='Y' ");
														pstmt4.setString(1,pojo.getStreamName());
														pstmt4.setString(2,pojo.getLocationId());
														rs4 = pstmt4.executeQuery();
															if(rs4.next()){
																pojo.setStreamId(rs4.getString("classstream_id_int"));
																
																pstmt5=connection.prepareStatement("SELECT `classdetail_id_int` FROM `campus_classdetail` WHERE `classdetails_name_var`=? AND `classstream_id_int`=? AND `locationId`=? and `isActive`='Y'");
																pstmt5.setString(1,pojo.getClassName());
																pstmt5.setString(2,pojo.getStreamId());
																pstmt5.setString(3,pojo.getLocationId());
																rs5 = pstmt5.executeQuery();
																if(rs5.next()){
																	pojo.setClassId(rs5.getString("classdetail_id_int"));
																	
																	pstmt7=connection.prepareStatement("SELECT `classsection_id_int` FROM `campus_classsection` WHERE `classsection_name_var`=? AND `classdetail_id_int`=? AND `locationId`=? and `isActive`='Y'");
																	pstmt7.setString(1,pojo.getSectionName());
																	pstmt7.setString(2,pojo.getClassId());
																	pstmt7.setString(3,pojo.getLocationId());
																	rs7 = pstmt7.executeQuery();
																	if(rs7.next()){
																		pojo.setSectionId(rs7.getString("classsection_id_int"));
																		successlist.add(pojo);
																	}
																	else{
																		vo.setReason("Section Name Is Not Valid");
																		failurelist.add(vo);
																	}
																}
																else{
																	vo.setReason("Class Name Is Not Valid");
																	failurelist.add(vo);
																}
																rs5.close();
																
															}
															else{
															vo.setReason("Stream Name Is Not Valid");
															failurelist.add(vo);
												}
											}
											else{
												vo.setReason("Exam Type  Is Not Valid");
												failurelist.add(vo);
											}
										}
										else{
											vo.setReason("Student Id Number Is Not Valid");
											failurelist.add(vo);
										}
									}else{
										vo.setReason("Incorrect Branch Name");
										failurelist.add(vo);
									}
								}
										else{
							vo.setReason("Incorrect/Inactive Branch Name");
							failurelist.add(vo);
						}
					}
					
				else{
					vo.setReason("Academic Year Is Not Valid");
					failurelist.add(vo);
				}
				rs.close();
			}

		
			for (Iterator<studentExcelUploadVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				studentExcelUploadVo failureDiomplVo = (studentExcelUploadVo) it.next();
				studentExcelUploadVo uploadStream = new studentExcelUploadVo();

				uploadStream.setAcademicYear(failureDiomplVo.getAcademicYear());
				uploadStream.setStudentName(failureDiomplVo.getStudentName());
				uploadStream.setStudentIdNo(failureDiomplVo.getStudentIdNo());
				uploadStream.setSchoolName(failureDiomplVo.getSchoolName());
				uploadStream.setClassName(failureDiomplVo.getClassName());
				uploadStream.setSectionName(failureDiomplVo.getSectionName());
				uploadStream.setStreamName(failureDiomplVo.getStreamName());
				uploadStream.setAttandance(failureDiomplVo.getAttandance());
				uploadStream.setWorkEducation(failureDiomplVo.getWorkEducation());
				uploadStream.setArtEducation(failureDiomplVo.getArtEducation());
				uploadStream.setHealthEducation(failureDiomplVo.getHealthEducation());
				uploadStream.setDescipline(failureDiomplVo.getDescipline());
				
				failurelist.add(uploadStream);
			}
		}
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.GradeSetting(successlist,failurelist,connection,userId, log_audit_session,ExamTypeName,userLoggingsVo);
			}
		}//try
		catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}finally{

		try{
			if(pstmt !=null && !(pstmt.isClosed())){
				pstmt.close();
			}
			 if(pstm1 !=null &&!(pstm1.isClosed())){
				pstm1.close();
			}
			 if(pstmt2 !=null && !(pstmt2.isClosed())){
				pstmt2.close();
			}
			 if(pstmt3 !=null && !(pstmt3.isClosed())){
				pstmt3.close();
			}
			 if(pstmt4 !=null && !(pstmt4.isClosed())){
				pstmt4.close();
			}
			 if(pstmt5 !=null && !(pstmt5.isClosed())){
				pstmt5.close();
			}
			 if(pstmt6 !=null && !(pstmt6.isClosed())){
				pstmt6.close();
			}
			
			 if(pstmt7 !=null && !(pstmt7.isClosed())){
				pstmt7.close();
			}
			 if(pstm2 !=null && !(pstm2.isClosed())){
				pstm2.close();
			}
			 if(pstmt1 !=null && !(pstmt1.isClosed())){
				pstmt1.close();
			}
			 if(rs1 !=null && !(rs1.isClosed())){
				rs1.close();
			}
			 if(rs2 !=null && !(rs2.isClosed())){
				rs2.close();
			}
			 if(rs !=null && !(rs.isClosed())){
				rs.close();
			}
			 if(rs3 !=null && !(rs3.isClosed())){
				rs3.close();
			}
			 if(rs4 !=null && !(rs4.isClosed())){
				rs4.close();
			}
			 if(rs5 !=null && !(rs5.isClosed())){
				rs5.close();
			}
			 if(rs6 !=null && !(rs6.isClosed())){
				rs6.close();
			}
			if(rs7 !=null && (!rs7.isClosed())){
				rs7.close();
			}
			 if(rs8 !=null && (!rs8.isClosed())){
				rs8.close();
			}
			 if (connection != null && (!connection.isClosed())) {
				 connection.close();
				}
		}	
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : SaveGradeSetting : Ending");
		return failurelist;

	}

	public Set<classVo> insertSubjectXLS(List<ClassPojo> list, String userId, String log_audit_session,
			UserLoggingsPojo userLoggingsVo, String currentLoc) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsXLUploadServiceImpl : insertSubjectXLS : Starting");
		Connection connection = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null,pstmt3=null,pstmt5=null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null;
		List<ClassPojo> successlist=new ArrayList<ClassPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		
		Set<classVo> failurelist = new LinkedHashSet<classVo>();
		failurelist.clear();
		successlist.clear();
		
		try {
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			int cont=0,count=0;

			for (Iterator<ClassPojo> iterator = list.iterator(); iterator.hasNext();) {
				cont++;
				boolean flag=true;
				String checknum="false";
				ClassPojo pojo = (ClassPojo) iterator.next();
				classVo vo = new classVo();
					vo.setLocationName(pojo.getLocationName());
					vo.setClassName(pojo.getClassName());
					vo.setSpecializationName(pojo.getSpecializationName());
					vo.setSubjectName(pojo.getSubjectName());
					vo.setSubjectCode(pojo.getSubjectCode().toUpperCase());
					vo.setSubjectType(pojo.getSubjectType());
					vo.setIsLanguage(pojo.getIsLanguage().toUpperCase());
					vo.setIsLaboratory(pojo.getIsLaboratory().toUpperCase());
					vo.setDescription(pojo.getDescription());
					
					//System.out.println("getLocationName"+pojo.getLocationName());
					//System.out.println("getClassName"+pojo.getClassName());
					//System.out.println("getSpecializationName"+pojo.getSpecializationName());
					//System.out.println("getSubjectName"+pojo.getSubjectName());
					//System.out.println("getSubjectCode"+pojo.getSubjectCode());
					//System.out.println("getSubjectType"+pojo.getSubjectType());
					//System.out.println("getIsLanguage"+pojo.getIsLanguage());
					//System.out.println("getIsLaboratory"+pojo.getIsLaboratory());
					//System.out.println("getDescription"+pojo.getDescription());
					/*System.out.println("getLocationName"+pojo.getLocationName());
					System.out.println("getClassName"+pojo.getClassName());
					System.out.println("getSpecializationName"+pojo.getSpecializationName());
					System.out.println("getSubjectName"+pojo.getSubjectName());
					System.out.println("getSubjectCode"+pojo.getSubjectCode());
					System.out.println("getSubjectType"+pojo.getSubjectType());
					System.out.println("getIsLanguage"+pojo.getIsLanguage());
					System.out.println("getIsLaboratory"+pojo.getIsLaboratory());
					System.out.println("getDescription"+pojo.getDescription());*/
					
					
					for(int i=0;i<list.size();i++){
						if(i==cont-1){
							continue;
						}
						if(pojo.getLocationName().equalsIgnoreCase(list.get(i).getLocationName()) && pojo.getClassName().equalsIgnoreCase(list.get(i).getClassName())
								&& pojo.getSpecializationName().equalsIgnoreCase(list.get(i).getSpecializationName())&& pojo.getSubjectName().equalsIgnoreCase(list.get(i).getSubjectName())
								&& pojo.getSubjectCode().equalsIgnoreCase(list.get(i).getSubjectCode()) && pojo.getSubjectType().equalsIgnoreCase(list.get(i).getSubjectType())
								&& pojo.getIsLanguage().equalsIgnoreCase(list.get(i).getIsLanguage())&& pojo.getIsLaboratory().equalsIgnoreCase(list.get(i).getIsLaboratory())
								){
							if(cont-1<i){
								flag =false;
							}
						}
					}
					
					for(int i = 0; i < pojo.getSubjectName().length(); i++) {
					     if(Character.isWhitespace(pojo.getSubjectName().charAt(i))){
					    	 count++;
					     }
					     else{
					    	 if(count<2){
					    		 count=0;
					    	 }
					     }
					}
					
					for(int i = 0; i < pojo.getSubjectName().length(); i++) {
					if(Character.isDigit(pojo.getSubjectName().charAt(0)))
						checknum="true";
					}
					
					
					if(pojo.getLocationName().equals("") || pojo.getClassName().equals("-") || pojo.getSubjectName().equals("") || 
							pojo.getSubjectCode().equals("-") || pojo.getSubjectType().equals("") || pojo.getIsLanguage().equals("-") 
							|| pojo.getIsLaboratory().equals("")){
						vo.setReason("* Mark Columns should not empty");
						failurelist.add(vo);
					}
					else if(count>1){
						vo.setReason("More than one space bewteen "+pojo.getSubjectName()+" are not valid");
						failurelist.add(vo);
					}
					else if(checknum.equalsIgnoreCase("true")){
						vo.setReason("Subject Name should not begin with numbers");
						failurelist.add(vo);
					}
					
					else if(!(pojo.getSubjectType().equals("Major") || pojo.getSubjectType().equals("Minor"))){
						vo.setReason("Invalid Entry in Excel Column 6");
						failurelist.add(vo);
					}
					else if(!(pojo.getIsLanguage().equals("Y") || pojo.getIsLanguage().equals("N"))){
						vo.setReason("Invalid Entry in Excel Column 7");
						failurelist.add(vo);
					}
					else if(!(pojo.getIsLaboratory().equals("Y") || pojo.getIsLaboratory().equals("N"))){
						vo.setReason("Invalid Entry in Excel Column 8");
						failurelist.add(vo);
					}
					else if(!flag){
						vo.setReason("Duplicate entry in excel sheet");
						failurelist.add(vo);
					}
					
				
					else {
						//successlist.add(pojo);
						pstmt= connection.prepareStatement("SELECT `Location_Id` FROM `campus_location` WHERE `Location_Name`=? and `isActive`='Y'");
						pstmt.setString(1,pojo.getLocationName());
						//System.out.println("check condition::::::::::::"+pstmt);
						/*System.out.println("check condition::::::::::::"+pstmt);*/
						rs = pstmt.executeQuery();
						if(rs.next()){
							
							
							if(currentLoc.equals(rs.getString("Location_Id"))){
								pojo.setLocationId(rs.getString("Location_Id"));
								//System.out.println("pojo.getLocationId()"+pojo.getLocationId());
								/*System.out.println("pojo.getLocationId()"+pojo.getLocationId());*/
										
										pstmt3= connection.prepareStatement("SELECT `classdetail_id_int` FROM `campus_classdetail` WHERE `classdetails_name_var`=?  AND `locationId`=?  and `isActive`='Y'");
										pstmt3.setString(1,pojo.getClassName());
										pstmt3.setString(2,pojo.getLocationId());
										//System.out.println("check condition::::::::::::"+pstmt3);
										/*System.out.println("check condition::::::::::::"+pstmt3);*/
										rs2 = pstmt3.executeQuery();
										if(rs2.next()){
											pojo.setClassId(rs2.getString("classdetail_id_int"));
											
											//System.out.println("pojo.getSpecializationName() ############ "+pojo.getSpecializationName());
											/*System.out.println("pojo.getSpecializationName() ############ "+pojo.getSpecializationName());*/
											
											pstmt5 =connection.prepareStatement("SELECT `Sub_Code` FROM `campus_subject` WHERE `isActive`='Y' AND `Sub_Code`=? AND `locationId`=?");
											pstmt5.setString(1, pojo.getSubjectCode());
											pstmt5.setString(2, pojo.getLocationId());
											//System.out.println(pstmt5);
											/*System.out.println(pstmt5);*/
											rs4 = pstmt5.executeQuery();
											if(rs4.next()){
												vo.setReason(""+pojo.getSubjectCode()+" already exists");
												failurelist.add(vo);
												
											}
											rs4.close();
											
											if(pojo.getSpecializationName()!=null && !(pojo.getSpecializationName().trim().equalsIgnoreCase(""))){
												pstmt2= connection.prepareStatement("SELECT `Specialization_Id` FROM `campus_class_specialization` WHERE `isActive`='Y' AND `locationId`=? AND `ClassDetails_Id`=? and Specialization_name=?");
												pstmt2.setString(1, pojo.getLocationId());
												pstmt2.setString(2, pojo.getClassId());
												pstmt2.setString(3, pojo.getSpecializationName());
												//System.out.println("check condition::::::::::::"+pstmt2);
												/*System.out.println("check condition::::::::::::"+pstmt2);*/
												rs3 = pstmt2.executeQuery();
												if(rs3.next()){
													pojo.setSpecId(rs3.getString("Specialization_Id"));
													successlist.add(pojo);
												}else{
													vo.setReason(""+pojo.getSpecializationName()+" not mapped with "+pojo.getClassName()+"");
													pojo.setSpecId("-");
													failurelist.add(vo);
												}
											}
											else{
												pojo.setSpecId("-");
												successlist.add(pojo);
											}
										}
										else{
											vo.setReason(""+vo.getClassName()+" is not mapped with "+vo.getLocationName()+"");
											failurelist.add(vo);
										}
										rs2.close();
							}else{
								vo.setReason("Incorrect Branch Name");
								failurelist.add(vo);
							}
						}
						else{
							vo.setReason("Incorrect/Inactive Branch Name");
							failurelist.add(vo);
						}
						rs.close();
					}
					
			}
			
			Set<classVo> failureListFromDiompl=new LinkedHashSet<classVo>();
			for (Iterator<classVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				classVo failureDiomplVo = (classVo) it.next();
				classVo uploadStream = new classVo();

				uploadStream.setLocationName(failureDiomplVo.getLocationName());
				uploadStream.setClassName(failureDiomplVo.getClassName());
				uploadStream.setSpecializationName(failureDiomplVo.getSpecializationName());
				uploadStream.setSubjectName(failureDiomplVo.getSubjectName());
				uploadStream.setSubjectCode(failureDiomplVo.getSubjectCode());
				uploadStream.setSubjectType(failureDiomplVo.getSubjectType());
				uploadStream.setIsLanguage(failureDiomplVo.getIsLanguage());
				uploadStream.setIsLaboratory(failureDiomplVo.getIsLaboratory());
				uploadStream.setReason(failureDiomplVo.getReason());
				failurelist.add(uploadStream);
			}
			
			//System.out.println("successlist.size() ++ "+successlist.size());
			/*System.out.println("successlist.size() ++ "+successlist.size());*/
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertSubject(successlist,failurelist,connection,userId,log_audit_session,userLoggingsVo);
			}
	}catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}finally{
		try {
			if(rs!=null && (!rs.isClosed())){
				rs.close();
			}
			if(rs1!=null && (!rs1.isClosed())){
				rs1.close();
			}
			if(rs2!=null && (!rs2.isClosed())){
				rs2.close();
			}
			
			if(pstmt!=null && (!pstmt.isClosed())){
				pstmt.close();
			}
			if(pstmt1!=null && (!pstmt1.isClosed())){
				pstmt1.close();
			}
			if(pstmt2!=null && (!pstmt2.isClosed())){
				pstmt2.close();
			}
			if(pstmt3!=null && (!pstmt3.isClosed())){
				pstmt3.close();
			}if(connection!=null && (!connection.isClosed())){
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
				+ " Control in SettingsXLUploadServiceImpl : insertSubjectXLS : Ending");
		return failurelist;
	}



	


}
