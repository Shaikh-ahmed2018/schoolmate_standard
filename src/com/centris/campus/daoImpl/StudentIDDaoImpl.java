package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentIDDao;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.StudentIDVo;




public class StudentIDDaoImpl implements StudentIDDao {
 
	private static final Logger logger= Logger.getLogger(StudentIDDaoImpl.class);
	@Override
	public List<StudentIDVo> getstudentIDPDFReport(String accyear, String section, String className, String student) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentIDDaoImpl: getstudentIDPDFReport : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<StudentIDVo> list=new ArrayList<StudentIDVo>() ;
		
		try{
			
			String studentid[]=student.split(",");
			conn=JDBCConnection.getSeparateConnection();
			for(int i=0;i<studentid.length;i++){
			psmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_ID_DETAILS);
			psmt.setString(1, studentid[i].trim());
			psmt.setString(2, accyear.trim());
			psmt.setString(3, className.trim());
			psmt.setString(4, section.trim());
			rs=psmt.executeQuery();
			while(rs.next()){
				
				StudentIDVo studentlist = new StudentIDVo();
				studentlist.setStuName(rs.getString("studentName"));
				studentlist.setAdress(rs.getString("address"));
				studentlist.setClassName(rs.getString("classdetails_name_var"));
				studentlist.setSection(rs.getString("classsection_name_var"));
				studentlist.setPhone(rs.getString("mobileno"));
				studentlist.setValidity(HelperClass.convertDatabaseToUI(rs.getString("endDate")));
				studentlist.setAdmissionno(rs.getString("student_admissionno_var"));
				
				String image=rs.getString("student_imgurl_var");
		
				if(image!=null){
				String[] x=image.split("/");
				studentlist.setImage(x[2]);
				}
				list.add(studentlist);
				
				/*String images=GetStudentImages.imageName(x[2]);
				//ln("images----------------------->"+images);*/
			}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentIDDaoImpl : getstudentIDPDFReport : Ending");
		
		return list;
	}
	public List<StudentIDVo> getStudentData(String acadamicYear,
			String section, String classVal, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentIDDaoImpl: getStudentData : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<StudentIDVo> list=new ArrayList<StudentIDVo>() ;
		
		try{
			
		
			conn=JDBCConnection.getSeparateConnection(custdetails);
		
			psmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_ID_SEARCH);
	
			psmt.setString(1, acadamicYear.trim());
			psmt.setString(2, classVal.trim());
			psmt.setString(3, section.trim());
			rs=psmt.executeQuery();
			while(rs.next()){
				
				StudentIDVo studentlist = new StudentIDVo();
				studentlist.setStuId(rs.getString("student_id_int"));
				studentlist.setStuName(rs.getString("studentName"));
				studentlist.setStreamName(rs.getString("classstream_name_var"));
				
				studentlist.setClassName(rs.getString("classdetails_name_var"));
				studentlist.setSection(rs.getString("classsection_name_var"));
			
				studentlist.setValidity(HelperClass.convertDatabaseToUI(rs.getString("endDate")));
			
			
				studentlist.setAdmissionno(rs.getString("student_admissionno_var"));
				String image=rs.getString("student_imgurl_var");
		
				
			
				list.add(studentlist);
				
				/*String images=GetStudentImages.imageName(x[2]);
				//ln("images----------------------->"+images);*/
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentIDDaoImpl : getStudentData : Ending");
		
		return list;
	}
	@Override
	public List<StudentIDVo> getstudentBusIDPDFReport(String accyear[],
			String locationId[], String streamId[], String studentid[]) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentIDDaoImpl: getstudentBusIDPDFReport : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<StudentIDVo> list=new ArrayList<StudentIDVo>() ;
		
		try{
			
			
			conn=JDBCConnection.getSeparateConnection();
			for(int i=0;i<studentid.length;i++){
			psmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_BUS_STUDENT_ID_DETAILS);
			psmt.setString(1, locationId[i].trim());
			psmt.setString(2, accyear[i].trim());
			psmt.setString(3, streamId[i].trim());
			psmt.setString(4, studentid[i].trim());
			rs=psmt.executeQuery();
			while(rs.next()){
				
				StudentIDVo studentlist = new StudentIDVo();
				studentlist.setStuName(rs.getString("studentName"));
				studentlist.setClassName(rs.getString("classdetails_name_var"));
				studentlist.setSection(rs.getString("classsection_name_var"));
				studentlist.setValidity(HelperClass.convertDatabaseToUI(rs.getString("endDate")));
				studentlist.setAdmissionno(rs.getString("student_admissionno_var"));
				studentlist.setRoute_no(rs.getString("Route_No"));
				studentlist.setPoint_name(rs.getString("stage_name"));
				studentlist.setSchoolName(rs.getString("Location_Name"));
				String image=rs.getString("student_imgurl_var");
				studentlist.setImage(image);
				list.add(studentlist);
				
			}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentIDDaoImpl : getstudentBusIDPDFReport : Ending");
		return list;
	}
	public List<PageFilterVo> getNewstudentIdCardDesignList(
			PageFilterpojo filterpojo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		
		try{
			conn=JDBCConnection.getSeparateConnection(custdetails);/*here*/
		
			psmt=conn.prepareStatement("SELECT ca.acadamic_id,ca.acadamic_year,caddr.address_line1,caddr.address_line2,caddr.tel_phone,csi.school_logo,csi.school_name,csi.address1 address1, csi.address2 address2,cl.Location_Id,cl.Location_Name,cc.classstream_id_int,cc.classstream_name_var,SUBSTR(cl.Location_Id,4) FROM campus_school_info csi,campus_acadamicyear ca,campus_classstream cc JOIN campus_location cl ON cc.locationId=cl.Location_Id JOIN campus_school_address caddr ON caddr.scl_address_id=cl.loc_add_id WHERE cc.locationId LIKE ? AND ca.acadamic_id LIKE ? AND cc.classstream_id_int LIKE ? AND cl.isActive='Y' AND ca.isActive='Y' AND cc.isActive='Y' ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cc.classstream_id_int,4)AS UNSIGNED)");
			psmt.setString(1, filterpojo.getLocationId());
			psmt.setString(2, filterpojo.getAcademicYear());
			psmt.setString(3, filterpojo.getStreamId());
		
			//ln("getNewstudentIdCardDesignList -->>"+psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
				filterVo.setSno(sno);
				filterVo.setAcademicYear(rs.getString("acadamic_year"));
				filterVo.setAcademicYearCode(rs.getString("acadamic_id"));
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocation_address(rs.getString("address_line1")+" "+rs.getString("address_line2")+","+rs.getString("Location_Name"));
				filterVo.setLocation_phone(rs.getString("tel_phone"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setStreamId(rs.getString("classstream_id_int"));
				filterVo.setStreamName(rs.getString("classstream_name_var"));
				filterVo.setTemplateName(rs.getString("classstream_name_var")+" Transport"+" "+rs.getString("Location_Name")+" "+rs.getString("acadamic_year")+" ");
				filterVo.setTemplateId(rs.getString("acadamic_id")+rs.getString("Location_Id")+rs.getString("classstream_id_int")+"transport");
				filterVo.setSchoollogourl(rs.getString("school_logo"));
				filterVo.setSchoolName(rs.getString("school_name"));
				filterVo.setSchoolAddress(rs.getString("address1")+","+rs.getString("address2"));
				list.add(filterVo);
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Ending");
		
		return list;
	}
	public List<StudentIDVo> getstudentIDPDFReport(String[] accyear,
			String[] locationId, String[] studentId,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentIDDaoImpl: getstudentIDPDFReport : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<StudentIDVo> list=new ArrayList<StudentIDVo>() ;
		
		try{
			
			
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			for(int i=0;i<studentId.length;i++){
				psmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_ID_MULTIPLE_DETAILS);
				psmt.setString(1, studentId[i].trim());
				psmt.setString(2, accyear[i].trim());
				psmt.setString(3, locationId[i].trim());
				//ln(psmt);
				
				rs=psmt.executeQuery();
				while(rs.next()){
					
					StudentIDVo studentlist = new StudentIDVo();
					studentlist.setStuName(rs.getString("studentName"));
					studentlist.setAdress(rs.getString("address"));
					studentlist.setClassName(rs.getString("classdetails_name_var"));
					studentlist.setSection(rs.getString("classsection_name_var"));
					studentlist.setPhone(rs.getString("mobileno"));
					studentlist.setValidity(HelperClass.convertDatabaseToUI(rs.getString("endDate")));
					studentlist.setAdmissionno(rs.getString("student_admissionno_var"));
					studentlist.setSecodaryPhone(rs.getString("student_mothermobileno_var"));
					studentlist.setEmergencyNo(rs.getString("student_gardian_mobileno"));
					studentlist.setFatherName(rs.getString("FatherName"));
					studentlist.setMotherName(rs.getString("student_mothername_var"));
					studentlist.setHouseName(rs.getString("housename"));
					studentlist.setLocation_address(rs.getString("address_line1")+" "+rs.getString("address_line2")+","+rs.getString("Location_Name"));
					studentlist.setLocation_phone(rs.getString("tel_phone"));
					studentlist.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
					studentlist.setAdharNo(rs.getString("adharNo"));
					studentlist.setBgroup(rs.getString("student_bloodgroup_var"));
					studentlist.setSchoollogo(rs.getString("school_logo"));
					String image=rs.getString("student_imgurl_var");
					studentlist.setSchoolName(rs.getString("school_name"));
					studentlist.setSchoolAddress(rs.getString("address1")+","+rs.getString("address2"));
					
					if(!image.equalsIgnoreCase("noImage")){
						
						studentlist.setImage(image);
						}
						list.add(studentlist);
					
				}
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentIDDaoImpl : getstudentIDPDFReport : Ending");
		
		return list;
	}
	
	
	public List<PageFilterVo> getstaffIDPDFReport(String[] locationId, String[] teacherID, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentIDDaoImpl: getstaffIDPDFReport : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		
		try{
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			for(int i=0;i<teacherID.length;i++){
			psmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_STAFF_ID_MULTIPLE_DETAILS);
				psmt.setString(1, teacherID[i].trim());
				psmt.setString(2, locationId[i].trim());
				
				//ln(psmt);
				rs=psmt.executeQuery();
				
				while(rs.next()){
					
					PageFilterVo filterVo = new PageFilterVo();
				
					filterVo.setTeacherName(rs.getString("teacherName"));
					filterVo.setTeacherID(rs.getString("registerId"));
					filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
					filterVo.setDesignationName(rs.getString("designationName"));
					filterVo.setMobileNo(rs.getString("mobileNo"));
					filterVo.setAddress(rs.getString("presentAddress"));
					filterVo.setAbbrivateId(rs.getString("Abbreviative_Id"));
					filterVo.setSchoollogourl(rs.getString("school_logo"));
					filterVo.setSchoolName(rs.getString("school_name"));
					filterVo.setLocation_address(rs.getString("address_line1")+" "+rs.getString("address_line2")+","+rs.getString("Location_Name"));
					filterVo.setLocationId(locationId[i].trim());
					filterVo.setDesignationId(rs.getString("DesignationCode"));
					filterVo.setDesignationName(rs.getString("designationName"));
					filterVo.setDepartmentId(rs.getString("DEPT_ID"));
					
					String image=rs.getString("imagePath");
					//ln("image   ::"+image);
							
							if(!(image.trim().equalsIgnoreCase("noImage"))){
							
								filterVo.setImage(image);
								}else
								{
									filterVo.setImage("noImage");	
								}
								
					list.add(filterVo);
			}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentIDDaoImpl : getstaffIDPDFReport : Ending");
		
		return list;
	}
	public List<PageFilterVo> getNewstafftIdCardDesignList(PageFilterpojo filterpojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstafftIdCardDesignList : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		
		try{
			
			
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);/*here*/
		
			psmt=conn.prepareStatement("SELECT ca.acadamic_id,ca.acadamic_year,cl.Location_Id,cl.Location_Name,cl.Location_Address,cl.Location_Phone,cc.DEPT_ID,cc.DEPT_NAME,SUBSTR(cl.Location_Id,4) FROM campus_acadamicyear ca,campus_department cc ,campus_location cl WHERE cl.location_Id LIKE ? AND ca.acadamic_id LIKE ? AND cc.DEPT_ID LIKE ? ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cc.DEPT_ID,4)AS UNSIGNED)");
			psmt.setString(1, filterpojo.getLocationId());
			psmt.setString(2, filterpojo.getAcademicYear());
			
			psmt.setString(3, filterpojo.getDepartmentId());
		
			rs=psmt.executeQuery();
			while(rs.next()){
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
				filterVo.setSno(sno);
				filterVo.setAcademicYear(rs.getString("acadamic_year"));
				filterVo.setAcademicYearCode(rs.getString("acadamic_id"));
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDepartmentId(rs.getString("DEPT_ID"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				filterVo.setLocation_address(rs.getString("Location_Address"));
				filterVo.setLocation_phone(rs.getString("Location_Phone"));
				filterVo.setTemplateName(rs.getString("DEPT_NAME")+" "+rs.getString("Location_Name")+" "+rs.getString("acadamic_year")+" ");
				filterVo.setTemplateId(rs.getString("acadamic_id")+rs.getString("Location_Id")+rs.getString("DEPT_ID"));
			
				list.add(filterVo);
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstafftIdCardDesignList : Ending");
		
		return list;
	}
		


}
