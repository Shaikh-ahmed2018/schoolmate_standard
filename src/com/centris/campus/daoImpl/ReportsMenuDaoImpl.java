package com.centris.campus.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.ReportsMenuDao;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.FeeStatusReportPojo;
import com.centris.campus.pojo.MarksPOJO;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.ITFeeVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;
import com.cerp.rest.util.SQLConstants;
import com.itextpdf.text.log.SysoLogger;



public class ReportsMenuDaoImpl implements ReportsMenuDao{
	
	private static final Logger logger = Logger.getLogger(ReportsMenuDaoImpl.class);

	@Override
	public ArrayList<ReportMenuVo> getAccYears(UserLoggingsPojo dbdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getAccYears : Starting");
			
			CallableStatement cstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> accYearList=new ArrayList<ReportMenuVo>();
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection(dbdetails);
				
				cstmt = conn.prepareCall("{CALL `getAllAccYear`}");
				rs=cstmt.executeQuery();
				
				while(rs.next()){
					
					ReportMenuVo yearVo=new ReportMenuVo();
					yearVo.setAccyearId(rs.getString("ACCYEARID").trim());
					yearVo.setAccyearname(rs.getString("acadamic_year"));
					
					accYearList.add(yearVo);
				}
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (cstmt != null&& (!cstmt.isClosed())) {
						cstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getAccYears : Ending");
			
			return accYearList;
		}

	@Override
	public ArrayList<ReportMenuVo> getStream(UserLoggingsPojo dbdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getStream : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> streamList=new ArrayList<ReportMenuVo>();
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection(dbdetails);
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STREAMS);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					ReportMenuVo streamVo=new ReportMenuVo();
					
					streamVo.setStreamId(rs.getString("classstream_id_int"));
					streamVo.setStreamname(rs.getString("classstream_name_var"));
					
					streamList.add(streamVo);
					
				}
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getStream : Ending");
			
			return streamList;
		}

	public ArrayList<ReportMenuVo> getStudentClass(String location, UserLoggingsPojo userLoggingsVo) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentClass : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> classList=new ArrayList<ReportMenuVo>();
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS);
			pstmt.setString(1,location);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				ReportMenuVo classVo=new ReportMenuVo();
				
				classVo.setClassId(rs.getString("classdetail_id_int"));
				classVo.setClassname(rs.getString("classdetails_name_var"));
				
				classList.add(classVo);
				
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentClass : Ending");
		
		return classList;
	}
	
	@Override
	public ArrayList<ReportMenuVo> getClassesByStream(String streamId,String location,UserLoggingsPojo userLoggingsVo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getClassesByStream : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> streamList=new ArrayList<ReportMenuVo>();
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASSES);
				pstmt.setString(1, streamId);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					ReportMenuVo streamVo=new ReportMenuVo();
					
					streamVo.setClassId(rs.getString("classdetail_id_int"));
					streamVo.setClassname(rs.getString("classdetails_name_var"));
					
					streamList.add(streamVo);
					
				}
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getClassesByStream : Ending");
			
			return streamList;
		}

	@Override
	public ArrayList<ReportMenuVo> getSectionsByClass(String classId,String location,UserLoggingsPojo custdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getSectionsByClass : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> streamList=new ArrayList<ReportMenuVo>();
		 	
			try {
				conn = JDBCConnection.getSeparateConnection(custdetails);
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_SECTIONS);
				pstmt.setString(1, classId);
				pstmt.setString(2, location);
			
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					ReportMenuVo streamVo=new ReportMenuVo();
					streamVo.setSectionId(rs.getString("classsection_id_int"));
					streamVo.setSectionname(rs.getString("classsection_name_var"));
					
					streamList.add(streamVo);
					
				}
				
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getSectionsByClass : Ending");
			
			return streamList;
		}

	@Override
	public ArrayList<StudentInfoVO> getStudentDetailsReport(ReportMenuForm reform,UserLoggingsPojo dbdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getStudentDetailsReport : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<StudentInfoVO> studentInfoList=new ArrayList<StudentInfoVO>();
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection(dbdetails);
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENTSiNFORMATION);
				pstmt.setString(1, reform.getAccyear().trim());
				pstmt.setString(2, reform.getStream().trim());
				pstmt.setString(3, reform.getClassname().trim());
				pstmt.setString(4, reform.getSection().trim());
				
				
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					StudentInfoVO studentinfo=new StudentInfoVO();
					
					count++;
					studentinfo.setSno(count);
					studentinfo.setAdmissionno(rs.getString("student_admissionno_var").trim());
					studentinfo.setName(rs.getString("studentname").trim());
					studentinfo.setAge(rs.getString("student_age_int").trim());
					studentinfo.setDoj(rs.getString("student_doj_var").trim());
					studentinfo.setFathername(rs.getString("FatherName").trim());
					studentinfo.setFathermobno(rs.getString("mobileno").trim());
					studentinfo.setMothername(rs.getString("student_mothername_var").trim());
					studentinfo.setMonthermobno(rs.getString("student_mothermobileno_var").trim());
					studentinfo.setStudentId(rs.getString("student_id_int"));
					studentInfoList.add(studentinfo);
					
				}
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getStudentDetailsReport : Ending");
			
			return studentInfoList;
		}

	@Override
	public ReportMenuVo getSelectedItems(ReportMenuForm reform,UserLoggingsPojo dbdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getSelectedItems : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ReportMenuVo selecteditems=new ReportMenuVo();
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection(dbdetails);
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_SELECTED_ITEMS);
				pstmt.setString(1, reform.getAccyear().trim());
				pstmt.setString(2, reform.getStream().trim());
				pstmt.setString(3, reform.getClassname().trim());
				pstmt.setString(4, reform.getSection().trim());
				pstmt.setString(5, reform.getLocation().trim());
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					count++;
					selecteditems.setAccyearname(rs.getString("acadamic_year"));
					selecteditems.setStreamname(rs.getString("classstream_name_var"));
					selecteditems.setClassname(rs.getString("classdetails_name_var"));
					selecteditems.setSectionname(rs.getString("classsection_name_var"));
					selecteditems.setLocationName(rs.getString("Location_Name"));
					selecteditems.setAccyearId(reform.getAccyear().trim());
					selecteditems.setStreamId(reform.getStream().trim());
					selecteditems.setClassId(reform.getClassname().trim());
					selecteditems.setSectionId(reform.getSection().trim());
					selecteditems.setLocationId(reform.getLocationid());
					
				}
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getSelectedItems : Ending");
			
			return selecteditems;
		}

	@Override
	public HashMap<String, ArrayList<FeeReportDetailsVo>> getStdFeeStatusReportDetails(
			ReportMenuForm reform,UserLoggingsPojo userLoggingsVo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getStdFeeStatusReportDetails : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			PreparedStatement ps_classfesAmt = null;
			ResultSet rs_classfesAmt=null;
			
			PreparedStatement ps_stagefesAmt = null;
			ResultSet rs_stagefesAmt=null;
			
			Connection conn = null;
			ArrayList<FeeReportDetailsVo> feeStatusList=new ArrayList<FeeReportDetailsVo>();
			HashMap<String, ArrayList<FeeReportDetailsVo>> feeMap=new HashMap<String, ArrayList<FeeReportDetailsVo>>();
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				
				double classFeeAmt=0.0;
				
				ps_classfesAmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_FEE_AMOUNT);
				
				ps_classfesAmt.setString(1, reform.getClassname().trim());
				ps_classfesAmt.setString(2, reform.getAccyear().trim());
				ps_classfesAmt.setString(3, reform.getTerm().trim());
			
				
				
				rs_classfesAmt=ps_classfesAmt.executeQuery();
				
				while(rs_classfesAmt.next()){
					
					classFeeAmt=rs_classfesAmt.getInt("ActualAmount");
					
				}
				
				double stageFeeAmt=0.0;
				
				ps_stagefesAmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STAGE_FEE_AMOUNT);
				
				ps_stagefesAmt.setString(1, reform.getClassname().trim());
				ps_stagefesAmt.setString(2, reform.getAccyear().trim());
				ps_stagefesAmt.setString(3, reform.getTerm().trim());
			
				
				
				rs_stagefesAmt=ps_stagefesAmt.executeQuery();
				
				while(rs_stagefesAmt.next()){
					
					stageFeeAmt=rs_stagefesAmt.getInt("transportfee");
					
				}
				
				
				if(reform.getStatus().equalsIgnoreCase("ALL")){
					
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_STATUS_REPORT);
					pstmt.setString(1, reform.getTerm().trim());
					pstmt.setString(2, reform.getAccyear().trim());
					pstmt.setString(3, reform.getClassname().trim());
					if(reform.getSection().trim().equalsIgnoreCase("all")){
						pstmt.setString(4, "%%");
					}else{
						pstmt.setString(4, reform.getSection().trim());
					}
					pstmt.setString(5,reform.getStudentId());
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
						
					
						FeeReportDetailsVo vo=new FeeReportDetailsVo();
					
					
					count++;
					
					vo.setSno(count);
					vo.setAdmissionNo(rs.getString("student_admissionno_var"));
					vo.setStudentName(rs.getString("studentname"));
					vo.setTotalAmount(rs.getString("totalamount"));
					vo.setStudentId(rs.getString("student_id_int"));
					vo.setActualAmount(rs.getDouble("actualamount"));
					vo.setBalanceAmount(rs.getDouble("balance_amount"));
					vo.setPaidAmount(Math.round(rs.getDouble("amount_paid")));
					vo.setClassName(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
					vo.setSectionname(rs.getString("classsection_name_var"));
					vo.setSectioncode(rs.getString("classsection_id_int"));
					
					if(rs.getString("is_paid")=="N"||rs.getString("is_paid").equalsIgnoreCase("N"))
					{
					vo.setStatus("Not Paid");
					}
					else
					{
						vo.setStatus("Paid");	
					}
					
					
					feeStatusList.add(vo);
					
					}
					
					
					
					if(feeStatusList.size()!=0){
						
						feeMap.put(feeStatusList.get(feeStatusList.size()-1).getClassName()+"-"+feeStatusList.get(feeStatusList.size()-1).getSectionname(), feeStatusList);
					}
					
				}
				
				
				
				else if(reform.getStatus().equalsIgnoreCase("Paid")){
					
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_FEEPAID_STATUS_REPORT);
					pstmt.setString(1, reform.getClassname().trim());
					if(reform.getSection().trim().equalsIgnoreCase("all")){
						pstmt.setString(2, "%%");
					}else{
						pstmt.setString(2, reform.getSection().trim());
					}
					pstmt.setString(3, reform.getTerm().trim());
					pstmt.setString(4, reform.getAccyear().trim());
					pstmt.setString(5,reform.getStudentId());
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					

						FeeReportDetailsVo vo=new FeeReportDetailsVo();
						
						if(feeStatusList.size()!=0){
						
						if(!(feeStatusList.get(feeStatusList.size()-1).getSectioncode().equalsIgnoreCase(rs.getString("classsection_id_int")))){
							
							feeMap.put(feeStatusList.get(feeStatusList.size()-1).getClassName()+"-"+feeStatusList.get(feeStatusList.size()-1).getSectionname(), feeStatusList);
							
							count=0;
							feeStatusList = new ArrayList<FeeReportDetailsVo>();
						}
						
						}
						
						count++;
						
						vo.setSno(count);
						vo.setAdmissionNo(rs.getString("student_admissionno_var"));
						vo.setStudentName(rs.getString("studentname"));
						vo.setActualAmount(Math.round(rs.getDouble("actualamount")));
						vo.setConcAmount(rs.getDouble("conc_amount"));
						vo.setPaidAmount(Math.round(rs.getDouble("totalamount")));
						vo.setPaidDate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
						vo.setBalanceAmount(Math.round(rs.getDouble("actualamount")-rs.getDouble("totalamount")-rs.getDouble("conc_amount")));
						vo.setStatus("Paid");
						vo.setClassName(rs.getString("classdetails_name_var"));
						vo.setSectioncode(rs.getString("classsection_id_int"));
						vo.setSectionname(rs.getString("classsection_name_var"));
						feeStatusList.add(vo);
						
					}
					
					if(feeStatusList.size()!=0){
						
						feeMap.put(feeStatusList.get(feeStatusList.size()-1).getClassName()+"-"+feeStatusList.get(feeStatusList.size()-1).getSectionname(), feeStatusList);
					}
					
				
				}else if(reform.getStatus().equalsIgnoreCase("NotPaid")){
					
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_FEENOTPAID_STATUS_REPORT);
					pstmt.setString(1, reform.getClassname().trim());
					if(reform.getSection().trim().equalsIgnoreCase("all")){
						pstmt.setString(2, "%%");
					}else{
						pstmt.setString(2, reform.getSection().trim());
					}
					pstmt.setString(3, reform.getTerm().trim());
					pstmt.setString(4, reform.getAccyear().trim());
					pstmt.setString(5,reform.getStudentId());
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					

						FeeReportDetailsVo vo=new FeeReportDetailsVo();
						
						if(feeStatusList.size()!=0){
						
						if(!(feeStatusList.get(feeStatusList.size()-1).getSectioncode().equalsIgnoreCase(rs.getString("classsection_id_int")))){
							
							feeMap.put(feeStatusList.get(feeStatusList.size()-1).getClassName()+"-"+feeStatusList.get(feeStatusList.size()-1).getSectionname(), feeStatusList);
							
							count=0;
							feeStatusList = new ArrayList<FeeReportDetailsVo>();
						}
						
						}
						
						count++;
						
						vo.setSno(count);
						vo.setAdmissionNo(rs.getString("student_admissionno_var"));
						vo.setStudentName(rs.getString("studentname"));
						vo.setActualAmount(Math.round(classFeeAmt+stageFeeAmt));
						vo.setConcAmount(0.0);
						vo.setPaidDate("-");
						vo.setBalanceAmount(Math.round(classFeeAmt+stageFeeAmt));
						vo.setStatus("Not Paid");
						vo.setClassName(rs.getString("classdetails_name_var"));
						vo.setSectioncode(rs.getString("classsection_id_int"));
						vo.setSectionname(rs.getString("classsection_name_var"));
						feeStatusList.add(vo);
						
					}
					
					if(feeStatusList.size()!=0){
						
						feeMap.put(feeStatusList.get(feeStatusList.size()-1).getClassName()+"-"+feeStatusList.get(feeStatusList.size()-1).getSectionname(), feeStatusList);
					}
					
				
					
					
					
				}
				
				
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (rs_classfesAmt != null&& (!rs_classfesAmt.isClosed())) {
						rs_classfesAmt.close();
					}
					
					if (rs_stagefesAmt != null&& (!rs_stagefesAmt.isClosed())) {
						rs_stagefesAmt.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (ps_classfesAmt != null&& (!ps_classfesAmt.isClosed())) {
						ps_classfesAmt.close();
					}
					if (ps_stagefesAmt != null&& (!ps_stagefesAmt.isClosed())) {
						ps_stagefesAmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} 
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getStdFeeStatusReportDetails : Ending");
			
			
			
			return feeMap;
		}
	
	@Override
	
	
	public ArrayList<FeeReportDetailsVo> getStdFeeStatusReportDownload(FeeStatusReportPojo feestatuspojo,UserLoggingsPojo custdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getStdFeeStatusReportDownload : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			PreparedStatement ps_classfesAmt = null;
			ResultSet rs_classfesAmt=null;
			
			PreparedStatement ps_stagefesAmt = null;
			ResultSet rs_stagefesAmt=null;
			
			Connection conn = null;
			ArrayList<FeeReportDetailsVo> feeStatusList=new ArrayList<FeeReportDetailsVo>();
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection(custdetails);
				
				double classFeeAmt=0.0;
				
				ps_classfesAmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_FEE_AMOUNT);
				
				ps_classfesAmt.setString(1, feestatuspojo.getClassname().trim());
				ps_classfesAmt.setString(2, feestatuspojo.getAccyear().trim());
				ps_classfesAmt.setString(3, feestatuspojo.getTerm().trim());
			
				
				
				rs_classfesAmt=ps_classfesAmt.executeQuery();
				
				while(rs_classfesAmt.next()){
					
					classFeeAmt=rs_classfesAmt.getInt("ActualAmount");
					
				}
				
				double stageFeeAmt=0.0;
				
				ps_stagefesAmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STAGE_FEE_AMOUNT);
				
				ps_stagefesAmt.setString(1, feestatuspojo.getClassname().trim());
				ps_stagefesAmt.setString(2, feestatuspojo.getAccyear().trim());
				ps_stagefesAmt.setString(3, feestatuspojo.getTerm().trim());
			
				
				
				rs_stagefesAmt=ps_stagefesAmt.executeQuery();
				
				while(rs_stagefesAmt.next()){
					
					stageFeeAmt=rs_stagefesAmt.getInt("transportfee");
					
				}
				
				if(feestatuspojo.getStatus().trim().equalsIgnoreCase("ALL")){
					
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_STATUS_REPORT);
					pstmt.setString(1, feestatuspojo.getTerm().trim());
					pstmt.setString(2, feestatuspojo.getAccyear().trim());
					pstmt.setString(3, feestatuspojo.getClassname().trim());
					if(feestatuspojo.getSection().trim().equalsIgnoreCase("all")){
						pstmt.setString(4, "%%");
					}else{
						pstmt.setString(4, feestatuspojo.getSection().trim());
					}
					pstmt.setString(5, feestatuspojo.getStudent().trim());
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
						
					
						FeeReportDetailsVo vo=new FeeReportDetailsVo();
						count++;
					
					vo.setSno(count);
					vo.setAdmissionNo(rs.getString("student_admissionno_var"));
					vo.setStudentName(rs.getString("studentname"));
					vo.setTotalAmount(rs.getString("totalamount"));
					vo.setActualAmount(rs.getDouble("actualamount"));
					vo.setBalanceAmount(rs.getDouble("balance_amount"));
					vo.setPaidAmount(Math.round(rs.getDouble("amount_paid")));
					vo.setClassName(rs.getString("classdetails_name_var"));
					vo.setSectionname(rs.getString("classsection_name_var"));
					vo.setSectioncode(rs.getString("classsection_id_int"));
					
					if(rs.getString("is_paid")=="N"||rs.getString("is_paid").equalsIgnoreCase("N"))
					{
					vo.setStatus("Not Paid");
					}
					else
					{
						vo.setStatus("Paid");	
					}
					
					
					feeStatusList.add(vo);
					
					}
					
					
					
					
				}else if(feestatuspojo.getStatus().trim().equalsIgnoreCase("Paid")){
					
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_FEEPAID_STATUS_REPORT);
					pstmt.setString(1, feestatuspojo.getClassname().trim());
					if(feestatuspojo.getSection().trim().equalsIgnoreCase("all")){
						pstmt.setString(2, "%%");
					}else{
						pstmt.setString(2, feestatuspojo.getSection().trim());
					}
					pstmt.setString(3, feestatuspojo.getTerm().trim());
					pstmt.setString(4, feestatuspojo.getAccyear().trim());
					pstmt.setString(5, feestatuspojo.getStudent().trim());
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					

						FeeReportDetailsVo vo=new FeeReportDetailsVo();
											
						count++;
						
						vo.setSno(count);
						vo.setAdmissionNo(rs.getString("student_admissionno_var"));
						vo.setStudentName(rs.getString("studentname"));
						vo.setActualAmount(Math.round(rs.getDouble("actualamount")));
						vo.setConcAmount(rs.getDouble("conc_amount"));
						vo.setPaidAmount(Math.round(rs.getDouble("totalamount")));
						vo.setPaidDate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
						vo.setBalanceAmount(Math.round(rs.getDouble("actualamount")-rs.getDouble("totalamount")-rs.getDouble("conc_amount")));
						vo.setStatus("Paid");
						vo.setClassName(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
						vo.setSectioncode(rs.getString("classsection_id_int"));
						vo.setSectionname(rs.getString("classsection_name_var"));
						feeStatusList.add(vo);
						
					}
					
								
				}else if(feestatuspojo.getStatus().trim().equalsIgnoreCase("NotPaid")){
					
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_FEENOTPAID_STATUS_REPORT);
					pstmt.setString(1, feestatuspojo.getClassname().trim());
					if(feestatuspojo.getSection().trim().equalsIgnoreCase("all")){
						pstmt.setString(2, "%%");
					}else{
						pstmt.setString(2, feestatuspojo.getSection().trim());
					}
					pstmt.setString(3, feestatuspojo.getTerm().trim());
					pstmt.setString(4, feestatuspojo.getAccyear().trim());
					pstmt.setString(5, feestatuspojo.getStudent().trim());
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					

						FeeReportDetailsVo vo=new FeeReportDetailsVo();
						
						count++;
						
						vo.setSno(count);
						vo.setAdmissionNo(rs.getString("student_admissionno_var"));
						vo.setStudentName(rs.getString("studentname"));
						vo.setActualAmount(Math.round(classFeeAmt+stageFeeAmt));
						vo.setConcAmount(0.0);
						vo.setPaidDate("-");
						vo.setBalanceAmount(Math.round(classFeeAmt+stageFeeAmt));
						vo.setStatus("Not Paid");
						vo.setClassName(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
						vo.setSectioncode(rs.getString("classsection_id_int"));
						vo.setSectionname(rs.getString("classsection_name_var"));
						feeStatusList.add(vo);
						
					}
					
									
				}
				
				
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (rs_classfesAmt != null&& (!rs_classfesAmt.isClosed())) {
						rs_classfesAmt.close();
					}
					
					if (rs_stagefesAmt != null&& (!rs_stagefesAmt.isClosed())) {
						rs_stagefesAmt.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (ps_classfesAmt != null&& (!ps_classfesAmt.isClosed())) {
						ps_classfesAmt.close();
					}
					if (ps_stagefesAmt != null&& (!ps_stagefesAmt.isClosed())) {
						ps_stagefesAmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} 
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getStdFeeStatusReportDownload : Ending");
			
	
			
			return feeStatusList;
		}

	@Override
	public HashMap<String, ArrayList<MarksUploadVO>> getStdMarksDetails(
			ReportMenuForm reform,UserLoggingsPojo userLoggingsVo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getStdMarksDetails : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			Connection conn = null;
			ArrayList<MarksUploadVO> marksList=new ArrayList<MarksUploadVO>();
			
			HashMap<String, ArrayList<MarksUploadVO>> marksMap=new HashMap<String, ArrayList<MarksUploadVO>>();
			
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				
									
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_MARKS);
					pstmt.setString(1, reform.getExam().trim());
					pstmt.setString(2, reform.getClassname().trim());
					pstmt.setString(3, reform.getSection().trim());
					pstmt.setString(4, reform.getStudentId().trim());
					pstmt.setString(5, reform.getAccyear().trim());
					
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					
						MarksUploadVO vo=new MarksUploadVO();
					
						count++;
						
						vo.setSno(count);
						vo.setAdmissionno(rs.getString("student_admissionno_var"));
						vo.setStudentname(rs.getString("studentName"));
						vo.setExamname(rs.getString("examname"));
						vo.setSubjectname(rs.getString("subjectName"));
						vo.setMaxmarks(rs.getString("maximum_marks"));
						vo.setReqmarks(rs.getString("required_marks"));
						vo.setScoredmarks(rs.getString("scoredmarks"));
						
						vo.setMarkspercent((Double.parseDouble(rs.getString("scoredmarks"))/Double.parseDouble(rs.getString("maximum_marks")))*100);
						
						
						marksList.add(vo);
						
					}
					
					if(marksList.size()!=0){
						marksMap.put(marksList.get(marksList.size()-1).getStudentname()+"-"+marksList.get(marksList.size()-1).getExamname(), marksList);
					}
					
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getStdMarksDetails : Ending");
			
			return marksMap;
		}
	
	@Override
	public  ArrayList<MarksUploadVO> getStdMarksDetailsDownload(MarksPOJO reform,UserLoggingsPojo custdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getStdMarksDetailsDownload : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			Connection conn = null;
			ArrayList<MarksUploadVO> marksList=new ArrayList<MarksUploadVO>();
			
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection(custdetails);
				
									
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_MARKS);
					pstmt.setString(1, reform.getExamid().trim());
					pstmt.setString(2, reform.getClassid().trim());
					pstmt.setString(3, reform.getSection().trim());
					pstmt.setString(4, reform.getStudentid().trim());
					pstmt.setString(5, reform.getAccyear().trim());
					
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					
						MarksUploadVO vo=new MarksUploadVO();
					
						count++;
						
						vo.setSno(count);
						vo.setAdmissionno(rs.getString("student_admissionno_var"));
						vo.setStudentname(rs.getString("studentName")+"-"+rs.getString("examname"));
						vo.setExamname(rs.getString("examname"));
						vo.setSubjectname(rs.getString("subjectName"));
						vo.setMaxmarks(rs.getString("maximum_marks"));
						vo.setReqmarks(rs.getString("required_marks"));
						vo.setScoredmarks(rs.getString("scoredmarks"));
						
						vo.setMarkspercent((Double.parseDouble(rs.getString("scoredmarks"))/Double.parseDouble(rs.getString("maximum_marks")))*100);
						
						
						marksList.add(vo);
						
					}
					
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getStdMarksDetailsDownload : Ending");
			
			return marksList;
		}

	@Override
	public ArrayList<ExaminationDetailsVo> examReportClassWiseDetails(ReportMenuForm reform,UserLoggingsPojo custdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: examReportClassWiseDetails : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			Connection conn = null;
			ArrayList<ExaminationDetailsVo> examlist=new ArrayList<ExaminationDetailsVo>();
			
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection(custdetails);
				
									
					pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_WISE_EXAM_DETAILS);
				
					pstmt.setString(1, reform.getClassname().trim());
					pstmt.setString(2, reform.getAccyear().trim());
					
					
					rs=pstmt.executeQuery();
					
					while(rs.next()){
					
						ExaminationDetailsVo vo=new ExaminationDetailsVo();
					
						count++;
						
						vo.setSno1(count);
						vo.setExamName(rs.getString("examname"));
						vo.setSubjectName(rs.getString("subjectName"));
						vo.setMaxmarks(rs.getString("maximum_marks"));
						vo.setRequiredmarks(rs.getString("required_marks"));
						vo.setExaminationdate(HelperClass.convertDatabaseToUI(rs.getString("examdate")));
						vo.setStartTime(rs.getString("examtime"));
						vo.setEndTime(rs.getString("endtime"));
						
						examlist.add(vo);
						
					}
					
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : examReportClassWiseDetails : Ending");
			
			return examlist;
		}
	
	public ArrayList<StudentInfoVO> geInactivetStudentDetailsReport(ReportMenuVo vo,UserLoggingsPojo custdetails) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: geInactivetStudentDetailsReport : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentInfoVO> studentInfoList=new ArrayList<StudentInfoVO>();
		int count=0;
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_INACTIVE_STUDENTSiNFORMATION);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				StudentInfoVO studentinfo=new StudentInfoVO();
				
				count++;
				studentinfo.setSno(count);
				studentinfo.setAdmissionno(rs.getString("student_admissionno_var").trim());
				studentinfo.setName(rs.getString("studentname").trim());
				studentinfo.setAge(rs.getString("student_age_int").trim());
				studentinfo.setDoj(rs.getString("student_doj_var").trim());
				studentinfo.setFathername(rs.getString("FatherName").trim());
				studentinfo.setFathermobno(rs.getString("mobileno").trim());
				studentinfo.setMothername(rs.getString("student_mothername_var").trim());
				studentinfo.setMonthermobno(rs.getString("student_mothermobileno_var").trim());
				
				
			
				studentInfoList.add(studentinfo);
				
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : geInactivetStudentDetailsReport : Ending");
		
		return studentInfoList;
	}

	public ArrayList<StudentInfoVO> geInactivetStudentFeeDetailsReport(ReportMenuForm reform,UserLoggingsPojo custdetails) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: geInactivetStudentFeeDetailsReport : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentInfoVO> studentInfoList=new ArrayList<StudentInfoVO>();
		int count=0;
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_INACTIVE_STUDENTS_FEE_INFORMATION);
			pstmt.setString(1, reform.getAccyear().trim());
			
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				StudentInfoVO studentinfo=new StudentInfoVO();
				
				count++;
				studentinfo.setSno(count);
				studentinfo.setAdmissionno(rs.getString("student_admissionno_var").trim());
				studentinfo.setName(rs.getString("studentname").trim());
				studentinfo.setClassnmae(rs.getString("ClassName").trim());
				studentinfo.setAmount(rs.getDouble("totalamount"));
				
				if(rs.getString("is_paid").trim()=="Y" ||rs.getString("is_paid").equalsIgnoreCase("Y"))
				{
					studentinfo.setIspaid("Paid");
				}
				else
				{
					studentinfo.setIspaid("Not Paid");
				}
				
				
				
				
				if(rs.getString("paidDate")==null||rs.getString("paidDate")=="")
				{
					studentinfo.setPaiddate("-");
				}
				else
				{
					studentinfo.setPaiddate(rs.getString("paidDate"));
				}
				
				
				studentinfo.setTermname(rs.getString("termname"));
				
				if (rs.getString("FatherName") == null)
				{
					if (rs.getString("student_mothername_var") == null)
					{
						
						studentinfo.setFathername(rs.getString("student_gaurdianname_var"));
					} 
					else 
					{
						studentinfo.setFathername(rs.getString("student_mothername_var"));
					}
				} 
				else
				{
					studentinfo.setFathername(rs.getString("FatherName"));
				}

				if (rs.getString("mobileno") == null) 
				{
					if (rs.getString("student_mothermobileno_var") == null)
					{
						studentinfo.setFathermobno(rs.getString("student_gardian_mobileno"));
					} 
					else
					{
						studentinfo.setFathermobno(rs.getString("student_mothermobileno_var"));
					}
				}
				else 
				{
					studentinfo.setFathermobno(rs.getString("mobileno"));
				}
				
				
				
				studentInfoList.add(studentinfo);
				
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : geInactivetStudentFeeDetailsReport : Ending");
		
		return studentInfoList;
	}

	public ReportMenuVo getSelectedoneItems(ReportMenuForm reform, UserLoggingsPojo custdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getSelectedoneItems : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ReportMenuVo selecteditems=new ReportMenuVo();
			int count=0;
		 	
			try {
				
				conn = JDBCConnection.getSeparateConnection(custdetails);
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_ONE_SELECTED_ITEMS);
				pstmt.setString(1, reform.getAccyear().trim());
			
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					count++;
					selecteditems.setAccyearname(rs.getString("acadamic_year"));
					
					
					selecteditems.setAccyearId(reform.getAccyear().trim());
					
					
				}
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getSelectedoneItems : Ending");
			
			return selecteditems;
		}
	
	public ArrayList<FeeReportDetailsVo> getSingleStdFeeStatusReportDetails(
			String stdId, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getSingleStdFeeStatusReportDetails : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1=null;
		
		
		
		Connection conn = null;
		ArrayList<FeeReportDetailsVo> feeStatusList=new ArrayList<FeeReportDetailsVo>();
		
	
	 	
		try {
				String feeCollectionCode=null;
				conn = JDBCConnection.getSeparateConnection(custdetails);
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_SINGLE_FEE_STATUS_REPORT);
				pstmt.setString(1,stdId);
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
				
					FeeReportDetailsVo vo=new FeeReportDetailsVo();
			
					vo.setAdmissionNo(rs.getString("admissionNo"));
					vo.setStudentName(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
					vo.setTotalAmount(rs.getString("actualamount"));
					vo.setActualAmount(rs.getDouble("actualamount"));
					vo.setBalanceAmount(rs.getDouble("balance_amount"));
					vo.setPaidAmount(Math.round(rs.getDouble("amount_paid")));
					feeCollectionCode=rs.getString("feeCollectionCode");
					
					feeStatusList.add(vo);
					
				}
				int count=0;
				pstmt1 = conn.prepareStatement(ReportsMenuSqlConstants.GET_SINGLE_FEE_STATUS_REPORT_DAYWISE);
				pstmt1.setString(1,feeCollectionCode);
				rs1=pstmt1.executeQuery();
				while(rs1.next()){
					
					
					FeeReportDetailsVo vo=new FeeReportDetailsVo();
					
				
				count++;
				
				vo.setSno(count);
				vo.setFeeName(rs1.getString("FeeName"));
			
				vo.setOpeningfeeAmount(rs1.getDouble("finalFeeAmtCollected")+rs1.getDouble("outstandingfee"));
				vo.setFeeAmountCollected(rs1.getDouble("finalFeeAmtCollected"));
				vo.setBlancefeeAmount(rs1.getDouble("outstandingfee"));
				vo.setPaidDate(HelperClass.convertDatabaseToUI(rs1.getString("feepaiddate")));
				
				
				feeStatusList.add(vo);
				
				}
				
			
			
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getSingleStdFeeStatusReportDetails : Ending");
		
		
		
		return feeStatusList;
	}

	@Override
	public String gettempregid() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: gettempregid : Starting");
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String schl_adm_id=null;
		String  currid="";
		
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
		
			pstmt =conn.prepareStatement(ReportsMenuSqlConstants.GET_TEMP_ID);
			   rs = pstmt.executeQuery();
				while(rs.next())
				{
					schl_adm_id=rs.getString("schl_adm_id");
				String regno=schl_adm_id.substring(0,3);	
				String befsplit=schl_adm_id.substring(3);
				
				
				int preid =Integer.parseInt(befsplit);
		        currid = regno + ++preid;
				}
				
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : gettempregid : Ending");
		return currid;
	
	}

	@Override
	public String getthirdRegNo() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getthirdRegNo : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String schl_third_id=null;
		String  currid="";
		
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
		
			pstmt =conn.prepareStatement(ReportsMenuSqlConstants.GET_THIRD_ID);
			   rs = pstmt.executeQuery();
				while(rs.next())
				{
					schl_third_id=rs.getString("third_admid");
				String regno=schl_third_id.substring(0,3);	
				String befsplit=schl_third_id.substring(3);
				
				
				int preid =Integer.parseInt(befsplit);
		        currid = regno + ++preid;
				}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getthirdRegNo : Ending");
		return currid;
	}

	public ArrayList<ReportMenuVo> getlocationList(UserLoggingsPojo dbdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getlocationList : Starting");
		
		CallableStatement cstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			cstmt =conn.prepareCall("{CALL getLocationList()}");
			rs = cstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo =new ReportMenuVo();
				vo.setLocationId(rs.getString("Location_Id"));
				vo.setLocationName(rs.getString("Location_Name"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (cstmt != null&& (!cstmt.isClosed())) {
					cstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getlocationList : Ending");
		
		return list;

	}

	public ArrayList<ReportMenuVo> getstudentDOBWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentDOBWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSDOBWISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				obj.setClass_and_section(rs.getString("classdetails_name_var")+" "+rs.getString("classsection_name_var"));
				
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentDOBWise : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentFatherOccuWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentFatherOccuWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTS_FATHER_OCC_WISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setFatherName(rs.getString("FatherName"));
				obj.setOccupation(rs.getString("occupation"));
				
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentFatherOccuWise : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getclasssectionDetails(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getclasssectionDetails : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("select cay.acadamic_year,cl.Location_Name,ccs.classsection_strength_int,cd.classdetails_name_var,ccs.classsection_name_var from campus_acadamicyear cay,campus_classdetail cd,campus_classsection ccs,campus_location cl where cd.classdetail_id_int like ? and ccs.classsection_id_int like ? and cay.acadamic_id=? and cl.Location_Id LIKE ?");
			pstmt.setString(1, vo.getClassId().trim());
			pstmt.setString(2, vo.getSectionId().trim());
			pstmt.setString(3, vo.getAccyearId().trim());
			pstmt.setString(4, vo.getLocationId().trim());
			rs = pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo obj =  new ReportMenuVo();
				obj.setAccYear(rs.getString("acadamic_year"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setClass_and_section(rs.getString("classdetails_name_var")+" - "+rs.getString("classsection_name_var"));
				obj.setTotal_strength(rs.getString("classsection_strength_int"));
				obj.setLocationName(rs.getString("Location_Name"));
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getclasssectionDetails : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentMotherOccuWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentMotherOccuWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTS_MOTHER_OCC_WISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setStudent_mothername_var(rs.getString("student_mothername_var"));
				obj.setOccupation(rs.getString("occupation"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentMotherOccuWise : Ending");
		return list;

	}

	public ArrayList<ReportMenuVo> getstudentDetailsReligionWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentDetailsReligionWise: Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSRELIGIONWISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setReligion(rs.getString("religion"));
				obj.setCaste(rs.getString("caste"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentDetailsReligionWise : Ending");
		return list;
	}
	
	public ArrayList<ReportMenuVo> getClassDetails(UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getClassDetails : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GETCLASSDETAILS);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo = new ReportMenuVo();
				vo.setClassId(rs.getString("classdetail_id_int"));
				vo.setClassname(rs.getString("classdetails_name_var"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getClassDetails : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentCategoryWise(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentCategoryWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSCATEGORYWISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setReligion(rs.getString("religion"));
				obj.setCaste(rs.getString("caste"));
				obj.setCasteCategory(rs.getString("casteCategory"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentCategoryWise : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentParentWise(ReportMenuVo vo,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentParentWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSPARENTWISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setFatherName(rs.getString("FatherName"));
				obj.setMobileno(rs.getString("mobileno"));
				obj.setStudent_mothername_var(rs.getString("student_mothername_var"));
				obj.setStudent_mothermobileno_var(rs.getString("student_mothermobileno_var"));
				obj.setAddress(rs.getString("address"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentParentWise : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentList(ReportMenuVo vo,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentList : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			
			if(vo.getStatus().equalsIgnoreCase("active")){
				pstmt.setString(6, "STUDYING");
			}else{
				pstmt.setString(6, "INACTIVE");
			}
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setClass_and_section(rs.getString("classdetails_name_var")+" - "+rs.getString("classsection_name_var"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentList : Ending");
		return list;
	}
	
	public ArrayList<ReportMenuVo> getstudentDetailsList(ReportMenuVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentDetailsList : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSLIST);
			pstmt.setString(1, vo.getSection());
			pstmt.setString(2, vo.getClassId());
			pstmt.setString(3, vo.getAccyear());
			pstmt.setString(4, vo.getLocation());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setRollNo(0);
				obj.setStudentnamelabel(rs.getString("student"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentDetailsList : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentStandardWise(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentStandardWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSSTANDARDLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setStudent_gender_var(rs.getString("student_gender_var"));
				obj.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				obj.setReligion(rs.getString("religion"));
				obj.setCaste(rs.getString("caste"));
				obj.setCasteCategory(rs.getString("casteCategory"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentStandardWise : Ending");
		return list;
	}
	
	public ArrayList<ReportMenuVo> getstudentContactDetails(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentContactDetails : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.STUDENTSCONTACTDETAILS);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setAddress(rs.getString("address"));
				obj.setFatherName(rs.getString("FatherName"));
				obj.setMobileno(rs.getString("mobileno"));
				obj.setStudent_mothername_var(rs.getString("student_mothername_var"));
				obj.setStudent_mothermobileno_var(rs.getString("student_mothermobileno_var"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentContactDetails : Ending");
		return list;
	}
	
	public ArrayList<ExaminationDetailsVo> accYearListStatus(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: accYearListStatus : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("select acadamic_id,acadamic_year from campus_acadamicyear");
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(rs.getString("acadamic_id"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setSno1(slno);
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_exam_gradesettings where accyear LIKE ?");
				statuspstmt.setString(1,rs.getString("acadamic_id"));
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				if(count > 0){
					obj.setStatus("Set");
				}
				else{
					obj.setStatus("Not Set");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : accYearListStatus : Ending");
		return list;

	}

	public ArrayList<ReportMenuVo> gettransportfeeDetails(ReportMenuVo obj, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: gettransportfeeDetails : Starting");
		PreparedStatement pstmt= null;
		PreparedStatement pstmt2=null;
	
		ResultSet rs=null;
		Connection conn = null;
	
		int slno = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		ArrayList<ReportMenuVo> termlist=new ArrayList<ReportMenuVo>();
	
		try{
			
				
		conn = JDBCConnection.getSeparateConnection(custdetails);
		
		 if(obj.getTermStatusId().equals("%%")){ //first time pass all %%
			
			pstmt = conn.prepareStatement(SQLConstants.TRANSPORT_FEE_LIST_ALL);

			pstmt.setString(1,obj.getAccyearId());
			pstmt.setString(2,obj.getClassId());
			pstmt.setString(3,obj.getSectionId());
			pstmt.setString(4,obj.getLocationId());
			pstmt.setString(5,obj.getTermId());
		}
		
		
		
		 else if(obj.getTermStatusId().equalsIgnoreCase("Y")) //paid
			{
				pstmt = conn.prepareStatement(SQLConstants.TRANSPORT_FEE_LIST_PAID);
			
				pstmt.setString(1,obj.getAccyearId());
				pstmt.setString(2,obj.getAccyearId());
				pstmt.setString(3,obj.getClassId());
				pstmt.setString(4,obj.getSectionId());
				pstmt.setString(5,obj.getLocationId());
				pstmt.setString(6,obj.getTermId());
			
			}
		 
				else { //unpaid
				pstmt = conn.prepareStatement(SQLConstants.TRANSPORT_FEE_LIST_UNPAID);
		
				pstmt.setString(1,obj.getAccyearId());
				pstmt.setString(2,obj.getLocationId());
				pstmt.setString(3, obj.getTermId());
				pstmt.setString(4,obj.getAccyearId());
				pstmt.setString(5,obj.getClassId());
				pstmt.setString(6,obj.getSectionId());
				pstmt.setString(7,obj.getLocationId());
				pstmt.setString(8, obj.getTermId());
		}
			
		
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo vo = new ReportMenuVo();
				slno++;
				vo.setSno(slno);
				vo.setStudentnamelabel(rs.getString("student"));
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setSectionname(rs.getString("classsection_name_var"));
				vo.setClass_and_section(rs.getString("classdetails_name_var")+" '" +rs.getString("classsection_name_var")+"'");
				vo.setTermname(rs.getString("termname"));
				vo.setAdmissionNo(rs.getString("student_admissionno_var"));
	
				
				
				if(obj.getTermStatusId().equalsIgnoreCase("Y"))
				{
							vo.setStatus("PAID");
				
							pstmt2=conn.prepareStatement("SELECT SUM(amount_paid)as total FROM `campus_tranport_fee_collection_details` WHERE `admissionNo`=? AND`accYear`=? AND `termcode`LIKE ?");
							pstmt2.setString(1, rs.getString("student_id_int"));
							pstmt2.setString(2, obj.getAccyearId());
							pstmt2.setString(3, rs.getString("termid"));
							ResultSet rs2=pstmt2.executeQuery();
							while(rs2.next())
							{
								vo.setAmount_paid(rs2.getString("total"));
							}
							rs2.close();
				}
				else
				{
					vo.setStatus("NOT PAID");
					vo.setAmount_paid("-");
				}
							
				list.add(vo);
				}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : gettransportfeeDetails : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getTerm(String accyear, String location, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			pstmt = conn.prepareStatement("select distinct termid,termname from campus_fee_transport_termdetails");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ReportMenuVo vo = new ReportMenuVo();
				vo.setTermId(rs.getString("termid"));
				vo.setTermname(rs.getString("termname"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return list;

	}

	public ArrayList<ExaminationDetailsVo> getAccYearsSubject(String accyear, String locid,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getAccYearsSubject : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int examidcount=0;
	
		int slno = 0;
		
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("select acadamic_id,acadamic_year,l.Location_Id,l.Location_Name from campus_acadamicyear,campus_location l where acadamic_id like ? and l.Location_Id like ? and l.isActive='Y' order by startDate,Location_Name");
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int total=0;
				int marksstotal=0;
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(rs.getString("acadamic_id"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setLocationid(rs.getString("Location_Id"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				/*PreparedStatement statuspstmt = conn.prepareStatement("select count(examid) from campus_examination where loc_id=? and acadamicyear=?");
				statuspstmt.setString(1,rs.getString("Location_Id"));
				statuspstmt.setString(2,rs.getString("acadamic_id"));
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}*/
				//PreparedStatement pstmt1 = conn.prepareStatement("select count(distinct ExamId) from campus_subject_marks_wise where Accyear_Id=? and loc_id=?");
			
				
				//SELECT `examid`,`classid` FROM `campus_examination` WHERE `acadamicyear`=? AND `loc_id`=?
				PreparedStatement pstmt1 = conn.prepareStatement("SELECT DISTINCT e.`examid`,e.`classid` FROM `campus_examination` e JOIN `campus_classdetail` cc ON cc.`classdetail_id_int`=e.`classid` WHERE `acadamicyear`=? AND `loc_id`=? AND cc.`isActive`='Y'");
				pstmt1.setString(1,rs.getString("acadamic_id"));
				pstmt1.setString(2,rs.getString("Location_Id"));
			//	System.out.println(pstmt1);
				ResultSet rs1 = pstmt1.executeQuery();
				int classcount=0;
				int subcount=0;
				int count = 0;
				while(rs1.next())
				{
			//		pstmtstatus.setString(3, rs1.getString("classid"));
					//System.out.println("3014"+pstmtstatus);
					PreparedStatement pstmtstatus = conn.prepareStatement("SELECT COUNT(*) FROM `campus_student` st JOIN `campus_student_classdetails` ccd ON st.`student_id_int`=ccd.`student_id_int` WHERE ccd.`fms_acadamicyear_id_int`=? AND ccd.`locationId`=? AND ccd.`classdetail_id_int`=? AND st.`student_status_var`='active'");
					pstmtstatus.setString(1,  rs.getString("acadamic_id"));
					pstmtstatus.setString(2, rs.getString("Location_Id"));
					pstmtstatus.setString(3, rs1.getString("classid"));
					ResultSet statusrs = pstmtstatus.executeQuery();
					while(statusrs.next()){
						count =statusrs.getInt(1);
					}
					//PreparedStatement classcnt=conn.prepareStatement("SELECT `subjectID` FROM `campus_subject` WHERE `classid`=? AND `locationId`=? and STATUS='active'");
					PreparedStatement classcnt=conn.prepareStatement("SELECT COUNT(*) FROM `campus_subject` WHERE `classid`=? AND `locationId`=? AND isActive='Y'");
					
					classcnt.setString(1, rs1.getString("classid"));
					classcnt.setString(2,rs.getString("Location_Id"));
					//System.out.println(" classcnt "+classcnt);
				//	classcnt.setString(3, rs.getString("examid"));
					ResultSet clsc = classcnt.executeQuery();
					while(clsc.next()){
						subcount++;
						classcount=clsc.getInt(1);
						/*PreparedStatement pstmtcountstatus = conn.prepareStatement("SELECT COUNT(DISTINCT `stu_id`) FROM `campus_studentwise_mark_details` WHERE classid=? AND `Academic_yearid` = ? AND `location_id` = ?");
						pstmtcountstatus.setString(1,  rs1.getString("classid"));
						pstmtcountstatus.setString(2, rs.getString("acadamic_id"));
						pstmtcountstatus.setString(3, rs.getString("Location_Id"));
						//pstmtcountstatus.setString(5, rs1.getString("examid"));
						ResultSet rs2 = pstmtcountstatus.executeQuery();
						while(rs2.next())
						{
							classcount=classcount+rs2.getInt(1);
						}*/
						/*rs2.close();
						pstmtcountstatus.close();*/
					}
					clsc.close();
					classcnt.close();
					//examidcount=rs1.getInt(1);
					
					total=total+classcount*count;
				}
				/*statusrs.close();
				pstmtstatus.close();*/
				PreparedStatement pstmtcountstatus = conn.prepareStatement("SELECT COUNT(csm.`stu_id`) FROM `campus_studentwise_mark_details` csm JOIN `campus_student` cs ON cs.`student_id_int`=csm.`stu_id` WHERE csm.`Academic_yearid` = ? AND csm.`location_id` = ? AND cs.`student_status_var`='active'");
				//pstmtcountstatus.setString(1,  rs1.getString("classid"));
				pstmtcountstatus.setString(1, rs.getString("acadamic_id"));
				pstmtcountstatus.setString(2, rs.getString("Location_Id"));
				//pstmtcountstatus.setString(5, rs1.getString("examid"));
				ResultSet rs2 = pstmtcountstatus.executeQuery();
				while(rs2.next())
				{
					marksstotal=marksstotal+rs2.getInt(1);
				}
				rs2.close();
				pstmtcountstatus.close();
				rs1.close();
				pstmt1.close();
				/*if( examidcount > 0){
					obj.setStatus("Completed");
				}//if(count == examidcount)
				else{
					obj.setStatus("Pending");
				}*/
			/*	System.err.println(count+" "+classcount/subcount);
				if(count!=0 && classcount!=0 &&(count == classcount/subcount)){
					obj.setStatus("Set");
				}else{
					obj.setStatus("Not Set");
				}*/
				if(total==marksstotal){
					obj.setStatus("Set");
				}else{
					obj.setStatus("Not Set");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getAccYearsSubject : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getAllLocationName() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getAllLocationName : Starting");
		
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETLOCATION);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo =new ReportMenuVo();
				vo.setLocationId(rs.getString("Location_Id"));
				vo.setLocationName(rs.getString("Location_Name"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getAllLocationName : Ending");
		
		return list;

	}

	public ArrayList<ExaminationDetailsVo> accYearhouseSettings(String locid, String accyear,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: accYearhouseSettings : Starting");
		PreparedStatement pstmt= null,statuspstmt=null;
		ResultSet rs=null,statusrs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		List<LocationVO> locationList = new ArrayList<LocationVO>();
		try{
			
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			pstmt = conn.prepareStatement("select acadamic_id,acadamic_year,Location_Id,Location_Name from campus_acadamicyear,campus_location where acadamic_id = ? and Location_Id like ? order by startDate");
			
			if(locid.equalsIgnoreCase("%%") || locid==""){
				locationList = new  LocationBD().getLocationDetails(custdetails);
			}
			
			if(locationList.size()!=0){
				for(int j=0;j<locationList.size();j++){
					locid = locationList.get(j).getLocation_id();
					 pstmt.setString(1,accyear);
				     pstmt.setString(2,locationList.get(j).getLocation_id());
				     rs = pstmt.executeQuery();
			 
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(rs.getString("acadamic_id"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setLocationid(rs.getString("Location_Id"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				
				statuspstmt = conn.prepareStatement("select count(*) from campus_house_settings where accyear_id = ? and loc_id = ? and status='active' ");
		
					statuspstmt.setString(1,rs.getString("acadamic_id"));
				    statuspstmt.setString(2,locid);
				    statusrs = statuspstmt.executeQuery();
				    
				    while(statusrs.next()){
						count = statusrs.getInt(1);
					}
					if(count > 0){
						obj.setStatus("Set");
					}
					else{
						obj.setStatus("Not Set");
					}
					list.add(obj);
			}
				}
			}
			else{
				 pstmt.setString(1,accyear);
			     pstmt.setString(2,locid);
			     rs = pstmt.executeQuery();
		 
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(rs.getString("acadamic_id"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setLocationid(rs.getString("Location_Id"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				
				statuspstmt = conn.prepareStatement("select count(*) from campus_house_settings where accyear_id = ? and loc_id = ? ");
		
					statuspstmt.setString(1,rs.getString("acadamic_id"));
				    statuspstmt.setString(2,locid);
				    statusrs = statuspstmt.executeQuery();
				    
				    while(statusrs.next()){
						count = statusrs.getInt(1);
					}
					if(count > 0){
						obj.setStatus("Set");
					}
					else{
						obj.setStatus("Not Set");
					}
					list.add(obj);
			     }
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (statusrs != null&& (!statusrs.isClosed())) {
					statusrs.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (statuspstmt != null&& (!statuspstmt.isClosed())) {
					statuspstmt.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : accYearhouseSettings : Ending");
		return list;
	}

	public ArrayList<ExaminationDetailsVo> accYeargeneratehouseSettings(String locid, String accyear, UserLoggingsPojo userLoggingsVo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: accYeargeneratehouseSettings : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int count1 = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("select ca.acadamic_id,ca.acadamic_year,cl.Location_Id,cl.Location_Name from campus_acadamicyear ca,campus_location cl where ca.acadamic_id = ? and cl.Location_Id like ? and cl.isActive='Y' and ca.isActive='Y' order by startDate");
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(rs.getString("acadamic_id"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setLocationid(rs.getString("Location_Id"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_student_classdetails where fms_acadamicyear_id_int = ? and locationId = ?");
				statuspstmt.setString(1,rs.getString("acadamic_id"));
				statuspstmt.setString(2,rs.getString("Location_Id"));
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				statusrs.close();
				statuspstmt.close();
				PreparedStatement allocatedpstmt = conn.prepareStatement("select sum(allocated_strength)as total from campus_generate_house where accyearid = ? and locid = ?");
				allocatedpstmt.setString(1,rs.getString("acadamic_id"));
				allocatedpstmt.setString(2,rs.getString("Location_Id"));
				ResultSet allocatedrs = allocatedpstmt.executeQuery();
				while(allocatedrs.next()){
					count1 = allocatedrs.getInt("total");
				}
				allocatedrs.close();
				allocatedpstmt.close();
				if(count!=0 && count1!=0){
					if(count == count1){
					obj.setStatus("Set");
					}
					else{
						obj.setStatus("Not Set");
					}
				}
				else{
					obj.setStatus("Not Set");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : accYeargeneratehouseSettings : Ending");
		return list;
	
	}

	public ArrayList<ReportMenuVo> getstudentDepartmentList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentDepartmentList  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSDEPARTMENTLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setSpecializationName(rs.getString("Specialization_name"));
			 
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentDepartmentList  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentBusRouteWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentBusRouteWise  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSBUSROUTEWISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId()); 
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setBusStageName(rs.getString("stage_name"));
				obj.setStageAmount(rs.getString("amount"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentBusRouteWise  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentOptionalSubjectDetails(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentOptionalSubjectDetails  Starting");
		PreparedStatement pstmt= null;
		PreparedStatement pstmt1= null;
		ResultSet rs=null;
		ResultSet rst=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSOPTIONALSUBJECT);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setClass_and_section(rs.getString("classdetails_name_var")+" "+rs.getString("classsection_name_var"));
				
				if(rs.getString("secondlanguage") != null || rs.getString("secondlanguage") != "-"){
					pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECT);
					pstmt1.setString(1, rs.getString("secondlanguage"));
					rst=pstmt1.executeQuery();
					if(rst.next()){
						obj.setSecondLanguage(rst.getString("subjectName"));
					}else{
						obj.setSecondLanguage("-");
					}
					rst.close();
					pstmt1.close();
				}
				

				if(rs.getString("thirdlanguage") != null || rs.getString("thirdlanguage") != "-"){
					pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECT);
					pstmt1.setString(1, rs.getString("thirdlanguage"));
                    rst=pstmt1.executeQuery();
					if(rst.next()){
						obj.setThirdLanguage(rst.getString("subjectName"));
					}else{
						obj.setThirdLanguage("-");
					}
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
			
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null&& (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentOptionalSubjectDetails  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getstudentWithPhoneNumber(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentWithPhoneNumber  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSWITHPHONENUMBER);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setFatherName(rs.getString("FatherName"));
				obj.setFatherMobileNo(rs.getString("mobileno"));
				obj.setStudent_mothername_var(rs.getString("student_mothername_var"));
				obj.setStudent_mothermobileno_var(rs.getString("student_mothermobileno_var"));
				obj.setGuardianName(rs.getString("student_gaurdianname_var"));
				obj.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentWithPhoneNumber  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getOldStudentsList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getOldStudentsList  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETOLDSTUDENTLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getOldStudentsList  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getStudentsStrength(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentsStrength  Starting");
		String accYear = vo.getAccyearId();
		String location = vo.getLocationId();
		/*String className = vo.getClassId();
		String sectionName = vo.getSectionId();*/
		
		PreparedStatement pstmt= null;
		PreparedStatement pstmt2= null;
		
		PreparedStatement pstmObj2= null;
		ResultSet rs=null;
		ResultSet rst=null;
		ResultSet rst1=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		int total = 0;
		int gcount =0;
		int bcount =0;
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT DISTINCT sc.fms_acadamicyear_id_int,cd.classdetails_name_var,cd.classdetail_id_int,cs.classsection_id_int,cs.classsection_name_var FROM campus_classdetail cd JOIN campus_student_classdetails sc ON cd.classdetail_id_int = sc.classdetail_id_int AND cd.`locationId`=sc.`locationId` AND cd.`isActive`='Y'  JOIN campus_classsection cs ON cs.classsection_id_int = sc.classsection_id_int AND cs.`locationId`=sc.`locationId` AND cs.`isActive`='Y' WHERE sc.fms_acadamicyear_id_int LIKE ? AND sc.locationId LIKE ? ORDER BY LENGTH(cd.classdetail_id_int),cd.classdetail_id_int,cs.classsection_name_var");
			
			pstmt.setString(1, accYear);
			pstmt.setString(2, location);
			rs=pstmt.executeQuery();
			while(rs.next()){
				
				String clsName = rs.getString("classdetail_id_int");
				String secName = rs.getString("classsection_id_int");
				pstmt2=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSSTRENGTH);
				pstmt2.setString(1, accYear);
				pstmt2.setString(2, location);
				pstmt2.setString(3, clsName);
				pstmt2.setString(4, secName);
				rst = pstmt2.executeQuery();
				ReportMenuVo obj = new ReportMenuVo();
				while(rst.next())
				{					
					if(rst.getString("student_gender_var").equalsIgnoreCase("female")){
						
						gcount = rst.getInt(1);
					}else{
						bcount = rst.getInt(1);
					}
				}
				rst.close();
				pstmt2.close();
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setGirls(gcount);
				obj.setBoys(bcount);
				total = gcount+bcount;
				obj.setTotalStudentsInDiv(total);
				
				pstmObj2=conn.prepareStatement("SELECT COUNT(student_id_int) FROM campus_student_classdetails WHERE classdetail_id_int like ? AND fms_acadamicyear_id_int =? AND locationId =? ");
				pstmObj2.setString(1, clsName);
				pstmObj2.setString(2, accYear);
				pstmObj2.setString(3, location);
				rst1 = pstmObj2.executeQuery();
				while(rst1.next()){
					obj.setTotalStudentsInCls(rst1.getInt(1));
				}
				list.add(obj);
				
				rst1.close();
				pstmObj2.close();
				/*if(rs.getString("secondlanguage") != null ){
					pstmObj1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECT);
					pstmObj1.setString(1, rs.getString("secondlanguage"));
					rs1=pstmObj1.executeQuery();
					if(rs1.next()){
						obj.setSecondLanguage(rs1.getString("subjectName"));
					}
				}

				if(rs.getString("thirdlanguage") != null ){
					pstmObj1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECT);
					pstmObj1.setString(1, rs.getString("thirdlanguage"));
					System.out.println(pstmObj1);
					rs1=pstmObj1.executeQuery();
					if(rs1.next()){
						obj.setThirdLanguage(rs1.getString("subjectName"));
					}
				}
				obj.setSubjectName((obj.getSecondLanguage())+" / "+(obj.getThirdLanguage()));*/
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null&& (!rst.isClosed())) {
					rs.close();
				}
				if (rst != null&& (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				
				if (pstmObj2 != null&& (!pstmObj2.isClosed())) {
					pstmObj2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentsStrength  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getStudentsNewAdmissionList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentsNewAdmissionList  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSNEWADMISSONLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setDateOfJoining(HelperClass.convertDatabaseToUI(rs.getString("student_doj_var")));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentsNewAdmissionList  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getStudentPromotionList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentPromotionList  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSPROMOTIONLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getClassId());
			pstmt.setString(3, vo.getSectionId());
			pstmt.setString(4, vo.getLocationId());
			pstmt.setString(5, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				obj.setPromotionStatus(rs.getString("studentpromotion_status"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentPromotionList  Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getStudentListGenderWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentListGenderWise : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSTUDENTSLISTGENDERWISE);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getStatus());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setStudentnamelabel(rs.getString("studentName"));
				
				if(rs.getString("student_gender_var").equalsIgnoreCase("Male"))
				{
					obj.setGender("Boys");
				}else{
					obj.setGender("Girls");
				}
				
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentListGenderWise : Ending");
		return list;
	}

	public ArrayList<ExaminationDetailsVo> accYearListStatusGrade(String accyear, String location,UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: accYearListStatusGrade : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement("select acadamic_id,acadamic_year,l.Location_Id,l.Location_Name from campus_acadamicyear,campus_location l where acadamic_id like ? and Location_Id like ? and l.isActive='Y' order by startDate");
			pstmt.setString(1,accyear);
			pstmt.setString(2,location);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(rs.getString("acadamic_id"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setLocationid(rs.getString("Location_Id"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_exam_gradesettings where accyear = ? and loc_id = ?");
				statuspstmt.setString(1,rs.getString("acadamic_id"));
				statuspstmt.setString(2,rs.getString("Location_Id"));
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				statusrs.close();
				statuspstmt.close();
				if(count > 0){
					obj.setStatus("Set");
				}
				else{
					obj.setStatus("Not Set");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : accYearListStatusGrade : Ending");
		return list;

	}


	public ArrayList<SubjectPojo> getSubjectByClass(String classId, String locationId, UserLoggingsPojo userLoggingsVo, String spec) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getSubjectByClass : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		
	
		ArrayList<SubjectPojo> list = new ArrayList<SubjectPojo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETSUBJECTDETAILS);
			
			pstmt.setString(1, classId);
			pstmt.setString(2, locationId);
			pstmt.setString(3, spec);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				SubjectPojo sub=new SubjectPojo();
				sub.setSubjectId(rs.getString("subjectID"));
				sub.setSubjectName(rs.getString("subjectName"));
				list.add(sub);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getSubjectByClass : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getaccessionNo(UserLoggingsPojo custdetails){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getaccessionNo : Starting");
		
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GETACCESSION_NO);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo =new ReportMenuVo();
				
				vo.setAccessionNo(rs.getString("Accession_number"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getaccessionNo : Ending");
		
		return list;

	}

	
public List<ExaminationDetailsVo> getSubjectOnClass(String classId, String studentId, String accYear, String locationId, String examCode, UserLoggingsPojo  userLoggingsVo){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getSubjectOnClass : Starting");
		PreparedStatement pstmt=null,pstmt1= null,pstmt2=null,pstmt3=null
				          ,pstmt4=null,pstmt5=null,pstmt6=null,pstmt7=null,
				          pstmt8=null,pstmt9=null,pstmt10=null,pstmt11=null,
				          pstmt12=null;
		ResultSet rs=null,rs0=null,rs1=null,rs2=null,
				  rs3=null,rs4=null,rs5=null,rs6=null,
				  rs7=null,rs8=null,rs9=null,rs10=null;
		ResultSet rsscored=null;
		ResultSet rsgrade=null;
		Connection conn = null;
		int Totalmark=0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT `subjectID`,`subjectName`,`totalMarks`,`passMarks`,`Sub_Code`,`isLanguage` FROM `campus_subject` WHERE `classid`=? AND `locationId`=?");
			pstmt.setString(1,classId);
			pstmt.setString(2,locationId);
			rs = pstmt.executeQuery();
			String gradename="";
			while(rs.next()){
				ExaminationDetailsVo objresult = new ExaminationDetailsVo();
				objresult.setSubId(rs.getString("subjectID"));
				objresult.setSubCode(rs.getString("Sub_Code"));
				objresult.setSubjectName(rs.getString("subjectName"));
				objresult.setTot_marks(rs.getString("totalMarks"));
				objresult.setPassmarks(rs.getString("passMarks"));
				PreparedStatement pstscoredmarks = conn.prepareStatement("SELECT attendance_status,Stu_mark_id,scored_marks FROM campus_studentwise_mark_details WHERE stu_id=? and sub_id=? and exam_id=?");
				pstscoredmarks.setString(1,studentId);
				pstscoredmarks.setString(2,objresult.getSubId());
				pstscoredmarks.setString(3,examCode);
				rsscored=pstscoredmarks.executeQuery();
				while(rsscored.next())
				{
					
					objresult.setStudenprimid(rsscored.getString("Stu_mark_id"));
					objresult.setAttendace(rsscored.getString("attendance_status"));
					
					//PROJECT
					pstmt1=conn.prepareStatement("SELECT `obtainedMarks`,`MaxMarks` FROM `campus_project_studentwise` WHERE `studentId`=? AND `projectCode`=(SELECT `projectCode` FROM `campus_project` WHERE `SubjectId`=? AND `ClassId`=? AND `LocationId`=? AND `AccYear`=?)");
					pstmt1.setString(1,studentId);
					pstmt1.setString(2,rs.getString("subjectID"));
					pstmt1.setString(3,classId);
					pstmt1.setString(4,locationId);
					pstmt1.setString(5,accYear);
					rs0=pstmt1.executeQuery();
					
					int projectdep=0;
					int assigndep=0;
					int labdep=0;
					int attendacedep=0;
					while(rs0.next())
					{
						objresult.setOther(rs0.getString("MaxMarks"));
						pstmt2=conn.prepareStatement("SELECT `project`,`assignment`,`practical`,`attendance` FROM `campus_grade_dependency` WHERE `loc_id`=? AND `class_Id`=? AND `exam_code`=?");
						pstmt2.setString(1,locationId);
						pstmt2.setString(2,classId);
						pstmt2.setString(3,examCode);
						rs1=pstmt2.executeQuery();
						while(rs1.next()){
							int total=(rs.getInt("totalMarks")*rs1.getInt("project"))/100;
							projectdep=rs1.getInt("project");
							double project=((rs0.getInt("obtainedMarks")*total)/(double)rs0.getInt("MaxMarks"));
							int project_round=(int) Math.round(project);
							objresult.setProject_scored(project_round);
							
						}
					}
					//ASSIGNMENT
					pstmt3=conn.prepareStatement("SELECT `AcquiredMarks`,`MaxMarks` FROM `campus_assignmentdetails` WHERE `student_id`=? AND `AssignmentCode`=(SELECT `AssignmentCode` FROM `campus_assignment` WHERE `SubjectID`=? AND `ClassID`=? AND `locationId`=? AND `AcadamicID`=?)");
					pstmt3.setString(1,studentId);
					pstmt3.setString(2,rs.getString("subjectID"));
					pstmt3.setString(3,classId);
					pstmt3.setString(4,locationId);
					pstmt3.setString(5,accYear);
					rs2=pstmt3.executeQuery();
					while(rs2.next()){
						pstmt4=conn.prepareStatement("SELECT `project`,`assignment`,`practical`,`attendance` FROM `campus_grade_dependency` WHERE `loc_id`=? AND `class_Id`=? AND `exam_code`=?");
						pstmt4.setString(1,locationId);
						pstmt4.setString(2,classId);
						pstmt4.setString(3,examCode);
						rs3=pstmt4.executeQuery();
						while(rs3.next()){
							assigndep=rs3.getInt("assignment");
							int total=(rs.getInt("totalMarks")*rs3.getInt("assignment"))/100;
							double assignment=((rs2.getInt("AcquiredMarks")*total)/(double)rs2.getInt("MaxMarks"));
							int assignment_round=(int) Math.round(assignment);
							objresult.setScoredmarks(Integer.toString(assignment_round));
							objresult.setAssignment_scored(assignment_round);
						}
					}
					//LAB
					
					pstmt5=conn.prepareStatement("SELECT `lab_id`,`Total_Marks`,`Lab_Name`,`Pass_Marks` FROM `laboratory_details` WHERE `Subject`=? AND `Class_Name`=? AND `School_Name`=?");
					pstmt5.setString(1,rs.getString("subjectID"));
					pstmt5.setString(2,classId);
					pstmt5.setString(3,locationId);
					rs4=pstmt5.executeQuery();
					while(rs4.next()){
						pstmt6=conn.prepareStatement("SELECT csmd.`scored_marks` FROM `campus_studentwise_mark_details` csmd WHERE csmd.`sub_id`=? AND csmd.`stu_id`=? AND csmd.`exam_id`=? AND csmd.`classid`=? AND csmd.`location_id`=?");
						pstmt6.setString(1,rs4.getString("lab_id"));
						pstmt6.setString(2,studentId);
						pstmt6.setString(3,examCode);
						pstmt6.setString(4,classId);
						pstmt6.setString(5,locationId);
						rs5=pstmt6.executeQuery();
						while(rs5.next()){
							pstmt7=conn.prepareStatement("SELECT `project`,`assignment`,`practical`,`attendance` FROM `campus_grade_dependency` WHERE `loc_id`=? AND `class_Id`=? AND `exam_code`=?");
							pstmt7.setString(1,locationId);
							pstmt7.setString(2,classId);
							pstmt7.setString(3,examCode);
							rs6=pstmt7.executeQuery();
							while(rs6.next()){
								labdep=rs6.getInt("practical");
								int total=(rs.getInt("totalMarks")*rs6.getInt("practical"))/100;
								double lab=((rs5.getInt("scored_marks")*total)/(double)rs4.getInt("Total_Marks"));
								int lab_round=(int) Math.round(lab);
								objresult.setScoredmarks(Integer.toString(lab_round));
								objresult.setLab_scored(lab_round);
							}
							
						}
						
					}
					//ATTENDANCE
					int total_attendance=0;
					int attended=0;
					int attdep=0;
					pstmt8=conn.prepareStatement("SELECT COUNT(`attendencedate`) FROM `campus_attendence` WHERE  `addmissionno`=?");
					pstmt8.setString(1, studentId);
			    	rs7=pstmt8.executeQuery();
			    	while(rs7.next()){
			    		total_attendance=rs7.getInt(1);
			    	}
			    	pstmt9=conn.prepareStatement("SELECT COUNT(`attendence`) FROM `campus_attendence` WHERE  `addmissionno`=? AND `attendence`='Present'");
					pstmt9.setString(1, studentId);
			    	rs8=pstmt9.executeQuery();
			    	while(rs8.next()){
			    		attended=rs8.getInt(1);
			    	}
			    	pstmt10=conn.prepareStatement("SELECT `project`,`assignment`,`practical`,`attendance` FROM `campus_grade_dependency` WHERE `loc_id`=? AND `class_Id`=? AND `exam_code`=?");
					pstmt10.setString(1,locationId);
					pstmt10.setString(2,classId);
					pstmt10.setString(3,examCode);
					rs9=pstmt10.executeQuery();
					while(rs9.next()){
						attdep=rs9.getInt("attendance");
						int total=(rs.getInt("totalMarks")*rs9.getInt("attendance"))/100;
						double attendance=((attended*total)/(double)total_attendance);
						int att_round=(int) Math.round(attendance);
						objresult.setScoredmarks(Integer.toString(att_round));
						objresult.setAttendence_per(att_round);
					}
					//TOTAL
					int main=projectdep+assigndep+labdep+attdep;
					double theory=(rsscored.getInt("scored_marks")*(100-main))/(double)100;
					int theory1=(int) Math.round(theory);
					objresult.setScoredmarks(Integer.toString((theory1+objresult.getProject_scored()+objresult.getAssignment_scored()+objresult.getLab_scored()+objresult.getAttendence_per())));
					Totalmark=Integer.parseInt(objresult.getScoredmarks());
				    projectdep=0;
				    assigndep=0;
				    labdep=0;
				    attdep=0;
				    
				    
					
					if(rsscored.getString("attendance_status").equalsIgnoreCase("Absent"))
					{
						gradename="Absent";
					}
					else
					{
					PreparedStatement pstgrade = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
					pstgrade.setInt(1,Integer.parseInt(objresult.getScoredmarks()));
					pstgrade.setInt(2,Integer.parseInt(objresult.getScoredmarks()));
					rsgrade=pstgrade.executeQuery();
					while(rsgrade.next())
					{
						gradename=rsgrade.getString("grade_name");
					}
				}
					if(gradename==null){
						gradename="F";
					}
					objresult.setGradename(gradename);
					gradename=null;
					}
	/*END------>*/list.add(objresult);
				}
			
			int insert=0;
			int ind_scored=0;
			int ind_total=0;
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getScoredmarks() !=null){
					ind_scored=ind_scored+Integer.parseInt(list.get(i).getScoredmarks());
				}else{
					ind_scored=0;
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getScoredmarks() !=null){
					ind_total=ind_total+Integer.parseInt(list.get(i).getTot_marks());
				}else{
					ind_total=0;
				}
			}
				//INDIVIDUAL INTO CAMPUS_MARKS
				
			    pstmt11=conn.prepareStatement("SELECT COUNT(*) FROM `campus_marks` WHERE `StudentId`=? AND `Exam`=? AND `Accyear`=? AND `Classs`=? AND `LocationId`=?");
				pstmt11.setString(1, studentId);
				pstmt11.setString(2, examCode);
				pstmt11.setString(3, accYear);
				pstmt11.setString(4, classId);
				pstmt11.setString(5, locationId);
				rs10=pstmt11.executeQuery();
				while(rs10.next()){
					insert=rs10.getInt(1);
				}
				if(insert==0&&ind_total>0){
					
					pstmt12=conn.prepareStatement("INSERT INTO `campus_marks`(`StudentId`,`Exam`,`ScoredMarks`,`TotalMarks`,`Accyear`,`Classs`,`LocationId`) VALUES(?,?,?,?,?,?,?)");
					pstmt12.setString(1, studentId);
					pstmt12.setString(2, examCode);
					pstmt12.setString(3, Integer.toString(ind_scored));
					pstmt12.setString(4, Integer.toString(ind_total));
					pstmt12.setString(5, accYear);
					pstmt12.setString(6, classId);
					pstmt12.setString(7, locationId);
					pstmt12.executeUpdate();
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rsscored != null&& (!rsscored.isClosed())) {
					rsscored.close();
				}if (rsgrade != null&& (!rsgrade.isClosed())) {
					rsgrade.close();
				}if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}if (rs0 != null&& (!rs0.isClosed())) {
					rs0.close();
				}if (rs1 != null&& (!rs1.isClosed())) {
					rs.close();
				}if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}if (rs3 != null&& (!rs3.isClosed())) {
					rs3.close();
				}if (rs4 != null&& (!rs4.isClosed())) {
					rs4.close();
				}if (rs5 != null&& (!rs5.isClosed())) {
					rs5.close();
				}if (rs6 != null&& (!rs6.isClosed())) {
					rs6.close();
				}if (rs7 != null&& (!rs7.isClosed())) {
					rs7.close();
				}if (rs8 != null&& (!rs8.isClosed())) {
					rs8.close();
				}if (rs9 != null&& (!rs9.isClosed())) {
					rs9.close();
				}if (rs10 != null&& (!rs10.isClosed())) {
					rs10.close();
				}if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}if (pstmt3 != null&& (!pstmt3.isClosed())) {
					pstmt3.close();
				}if (pstmt4 != null&& (!pstmt4.isClosed())) {
					pstmt4.close();
				}if (pstmt5 != null&& (!pstmt5.isClosed())) {
					pstmt5.close();
				}if (pstmt6 != null&& (!pstmt6.isClosed())) {
					pstmt6.close();
				}if (pstmt7 != null&& (!pstmt7.isClosed())) {
					pstmt7.close();
				}if (pstmt8 != null&& (!pstmt8.isClosed())) {
					pstmt8.close();
				}if (pstmt9 != null&& (!pstmt9.isClosed())) {
					pstmt9.close();
				}if (pstmt10 != null&& (!pstmt10.isClosed())) {
					pstmt10.close();
				}if (pstmt11 != null&& (!pstmt11.isClosed())) {
					pstmt11.close();
				}if (pstmt12 != null&& (!pstmt12.isClosed())) {
					pstmt12.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getSubjectOnClass : Ending");
		return list;

	}
	public ArrayList<ExaminationDetailsVo> getExam(String studentId,String accyear, String locationId, String classDetailId,String sectionId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getExam : Starting");
		
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		ResultSet rs0=null;
		Connection conn = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
		//	pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_EXAM);
			pstmt=conn.prepareStatement("SELECT `examid`,`examname` FROM `campus_examination` WHERE `acadamicyear`=? AND `loc_id`=? AND `classid`=?");
			pstmt.setString(1, accyear);
			pstmt.setString(2, locationId);
			pstmt.setString(3, classDetailId);
			//pstmt.setString(4, sectionId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo vo =new ExaminationDetailsVo();
				/*vo.setExamName(rs.getString("examcode"));
				PreparedStatement pstmtcode=conn.prepareStatement("SELECT `examcode` FROM `campus_examination` WHERE `examid`=?");
				pstmtcode.setString(1, rs.getString("examcode"));
				rs0=pstmtcode.executeQuery();
				while(rs0.next()){
					vo.setExamCode(rs0.getString("examcode"));
				       }
				    list.add(vo);
				    pstmtcode.close();       */
				vo.setExamCode(rs.getString("examid"));
				vo.setExamName(rs.getString("examname"));
				list.add(vo);
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs0 != null&& (!rs0.isClosed())) {
					rs0.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getExam : Ending");
		
		return list;

	}

	public ArrayList<ExaminationDetailsVo> getExamDependencides(String examCode,String studentId, String accYear, String locationId, String classId, String sectionId, int scored, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getExamDependencides : Starting");
		
		PreparedStatement pstmt= null,pstmt1= null;
		ResultSet rs=null,rs1=null,rs0=null;
		Connection conn = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		ArrayList<ExaminationDetailsVo> setExamlist = new ArrayList<ExaminationDetailsVo>();
		
		int totaldepScoredMarks = 0;
		
		int outofftotal=0;
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.EXAM_NAME);
			pstmt.setString(1,examCode);
			pstmt.setString(2,studentId);
			pstmt.setString(3,classId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo vo =new ExaminationDetailsVo();
				vo.setMainexam(rs.getString("examname"));
				vo.setMainexammark(scored);
				vo.setMaintotal(rs.getInt("totalmarks"));
				int scoredmark=scored;
				if(scoredmark != 0 ){
					int totalmarks=rs.getInt("totalmarks");
					double grademark1=(scoredmark/(double)totalmarks);
					int grademark=(int) (grademark1*100);
					String grade=getGradeBasedOnMarks(grademark,userLoggingsVo);
					vo.setMainexamgrade(grade);
				}else{
					String grade="-";
					vo.setMainexamgrade(grade);
				}
				
				pstmt1=conn.prepareStatement(ReportsMenuSqlConstants.DEPENDENT_EXAM);
				pstmt1.setString(1,examCode);
				rs1=pstmt1.executeQuery();
				while(rs1.next()){
					ExaminationDetailsVo vo1 =new ExaminationDetailsVo();
					vo1.setExamCode(rs1.getString("Exam_code"));
					vo1.setDependentExamCode(rs1.getString("Dependency_Exam_code"));
					vo1.setDepPerce(rs1.getString("Dependency_perce"));
					PreparedStatement namepstmt=conn.prepareStatement(ReportsMenuSqlConstants.DEPENDENT_EXAM_SCORE);
					namepstmt.setString(1, studentId);
					namepstmt.setString(2, rs1.getString("Dependency_Exam_code"));
					namepstmt.setString(3,accYear);
					namepstmt.setString(4,classId);
					namepstmt.setString(5,locationId);
					rs0=namepstmt.executeQuery();
					while(rs0.next()){
						vo1.setExamName(rs0.getString("examname"));
						int depscoredmark=((rs0.getInt("ScoredMarks")*Integer.parseInt(rs1.getString("Dependency_perce")))/100);
						int deptotalmark=((rs0.getInt("TotalMarks")*Integer.parseInt(rs1.getString("Dependency_perce")))/100);
						vo1.setDepExamScoredMarks(depscoredmark);
						vo1.setDepExamTotalMarks(deptotalmark);
						if(depscoredmark != 0 ){
							double depgrademark1=(depscoredmark/(double)deptotalmark);
							int depgrademark=(int) (depgrademark1*100);
							String depgrade=getGradeBasedOnMarks(depgrademark,userLoggingsVo);
							vo1.setDepExamGrade(depgrade);
						}else{
							String grade="-";
							vo1.setDepExamGrade(grade);
						}
						totaldepScoredMarks = totaldepScoredMarks+depscoredmark;
                        outofftotal=outofftotal+deptotalmark;
						setExamlist.add(vo1);
					}
					vo.setTotalDepScoredMarks(totaldepScoredMarks);
					vo.setOutOffG(outofftotal);
					vo.setExamlist(setExamlist);
				}
				list.add(vo);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs0 != null&& (!rs0.isClosed())) {
					rs0.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getExamDependencides : Ending");
		
		return list;

	}

	public String getGradeBasedOnMarks(int grademark, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getGradeBasedOnMarks : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		String grade=null;
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
			pstmt.setInt(1,grademark);
			pstmt.setInt(2,grademark);
			rs=pstmt.executeQuery();
			while(rs.next()){
				grade=rs.getString("grade_name");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getGradeBasedOnMarks : Ending");
		
		return grade;
	}


	public ArrayList<ReportMenuVo> getAccessionNo() {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getAccessionNo : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> accessionList=new ArrayList<ReportMenuVo>();
		 	
			try {
				conn = JDBCConnection.getSeparateConnection();
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_ACCYEAR);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					ReportMenuVo yearVo=new ReportMenuVo();
					yearVo.setAccyearId(rs.getString("acadamic_id").trim());
					yearVo.setAccyearname(rs.getString("acadamic_year"));
					
					accessionList.add(yearVo);
					
				}
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getAccessionNo : Ending");
			
			return accessionList;
		}




	public List<ExaminationDetailsVo> getIndividualStudentMarksClass(String classId, String studentId, String accYear,
			String locationId, String examCode, String sectionId, UserLoggingsPojo userLoggingsVo) {
		 
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getIndividualStudentMarksClass : Starting");
		
		ArrayList<ExaminationDetailsVo> list=new ArrayList<ExaminationDetailsVo>();
		PreparedStatement pstmt= null,pstscoredmarks=null,pstscoredmarks1=null,pstmt1= null,pstmt2= null,pstmtn= null;
		ResultSet rs=null,rsscored=null,rs1=null,rsscored1=null,rs2=null,rsn=null;
		Connection conn = null;
		int count=0,count1=0,count2=0,count3=0,count4=0,count5=0,count6=0,count7=0,count8=0,count9=0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		 TreeMap<String, Integer> sorted_map = null;
		try {
			int maintotmark=0;
			int mainscored=0;
			int mperse=0;
			ExaminationDetailsVo objresult = new ExaminationDetailsVo();
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt1 = conn.prepareStatement("SELECT `student_id_int` FROM `campus_student_classdetails` WHERE `locationId`=? AND `classdetail_id_int`=? AND `classsection_id_int`=? AND fms_acadamicyear_id_int=?");
			pstmt1.setString(1, locationId);
			pstmt1.setString(2, classId);
			pstmt1.setString(3, sectionId);
			pstmt1.setString(4, accYear);
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				int totmark=0,scored=0,perse=0;
				pstmt = conn.prepareStatement("SELECT `subjectID`,`subjectName`,`Sub_Code`,`isLanguage` FROM `campus_subject` WHERE `classid`=? AND `locationId`=?");
				pstmt.setString(1, classId);
				pstmt.setString(2, locationId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					
					objresult.setSubId(rs.getString("subjectID"));
					pstscoredmarks = conn.prepareStatement("SELECT `total_marks`,`scored_marks`,`notebook_marks`,`subject_enrich_marks`,`max_periodic_marks`,`periodic_test`,`max_notebook_marks`,`max_subjenrich_marks` FROM campus_studentwise_mark_details WHERE stu_id=? and sub_id=? and exam_id=? AND Academic_yearid=? AND location_id=?");
					//`total_marks`,`scored_marks`,`notebook_marks`,`subject_enrich_marks`,`max_periodic_marks`,`periodic_test`,`max_notebook_marks`,`max_subjenrich_marks`
					pstscoredmarks.setString(1, rs1.getString("student_id_int"));
					pstscoredmarks.setString(2, objresult.getSubId());
					pstscoredmarks.setString(3, examCode);
					pstscoredmarks.setString(4, accYear);
					pstscoredmarks.setString(5, locationId);
					rsscored = pstscoredmarks.executeQuery();
					while (rsscored.next()) {
						totmark = totmark + rsscored.getInt("total_marks")+rsscored.getInt("max_periodic_marks")+rsscored.getInt("max_notebook_marks")+rsscored.getInt("max_subjenrich_marks");
						scored = scored + rsscored.getInt("scored_marks")+rsscored.getInt("periodic_test")+rsscored.getInt("notebook_marks")+rsscored.getInt("subject_enrich_marks");
					}
				}
				
				perse = (int) Math.round((scored * 100) / (double) totmark);
				/*<<<HashMap>>>*/
				ComparatorDaoImpl val = new ComparatorDaoImpl(map);
				sorted_map = new TreeMap<String, Integer>(val);
		        map.put(rs1.getString("student_id_int"), perse);
		        sorted_map.putAll(map);
				/*<<<HashMap>>>*/
				if (perse > 90) {
					count9++;
				} else if (perse > 80) {
					count8++;
				} else if (perse > 70) {
					count7++;
				} else if (perse > 60) {
					count6++;
				} else if (perse > 50) {
					count5++;
				} else if (perse > 40) {
					count4++;
				} else if (perse > 30) {
					count3++;
				} else if (perse > 20) {
					count2++;
				} else if (perse > 10) {
					count1++;
				} else if (perse >= 0) {
					count++;
				}
				
			}
			objresult.setCount(count);
			objresult.setCount1(count1);
			objresult.setCount2(count2);
			objresult.setCount3(count3);
			objresult.setCount4(count4);
			objresult.setCount5(count5);
			objresult.setCount6(count6);
			objresult.setCount7(count7);
			objresult.setCount8(count8);
			objresult.setCount9(count9);
			pstmt2 = conn.prepareStatement("SELECT `subjectID`,`subjectName`,`Sub_Code`,`isLanguage` FROM `campus_subject` WHERE `classid`=? AND `locationId`=?");
			pstmt2.setString(1, classId);
			pstmt2.setString(2, locationId);
			rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				
				objresult.setSubId(rs2.getString("subjectID"));
				pstscoredmarks1 = conn.prepareStatement("SELECT `total_marks`,`scored_marks`,`notebook_marks`,`subject_enrich_marks`,`max_periodic_marks`,`periodic_test`,`max_notebook_marks`,`max_subjenrich_marks` FROM campus_studentwise_mark_details WHERE stu_id=? and sub_id=? and exam_id=?");
			//	pstscoredmarks1 = conn.prepareStatement("SELECT scored_marks FROM campus_studentwise_mark_details WHERE stu_id=? and sub_id=? and exam_id=?");
				pstscoredmarks1.setString(1, studentId);
				pstscoredmarks1.setString(2, objresult.getSubId());
				pstscoredmarks1.setString(3, examCode);
				rsscored1 = pstscoredmarks1.executeQuery();
				while (rsscored1.next()) {
				/*	maintotmark = maintotmark + rs2.getInt("totalMarks");
					mainscored = mainscored + rsscored1.getInt("scored_marks");*/
					maintotmark = maintotmark + rsscored1.getInt("total_marks")+rsscored1.getInt("max_periodic_marks")+rsscored1.getInt("max_notebook_marks")+rsscored1.getInt("max_subjenrich_marks");
					mainscored = mainscored + rsscored1.getInt("scored_marks")+rsscored1.getInt("periodic_test")+rsscored1.getInt("notebook_marks")+rsscored1.getInt("subject_enrich_marks");
				}
			}
			
			
			mperse = (int) Math.round((mainscored * 100) / (double) maintotmark);
			
			if (mperse > 90) {
				objresult.setMainCount(100);
			} else if (mperse > 80) {
				objresult.setMainCount(90);
			} else if (mperse > 70) {
				objresult.setMainCount(80);
			} else if (mperse > 60) {
				objresult.setMainCount(70);
			} else if (mperse > 50) {
				objresult.setMainCount(60);
			} else if (mperse > 40) {
				objresult.setMainCount(50);
			} else if (mperse > 30) {
				objresult.setMainCount(40);
			} else if (mperse > 20) {
				objresult.setMainCount(30);
			} else if (mperse > 10) {
				objresult.setMainCount(20);
			} else if (mperse >= 0) {
				objresult.setMainCount(10);
			}
			List key=new ArrayList(sorted_map.keySet());
	        for (int i = 0; i < key.size(); i++) {
	        	if(key.get(i).equals(studentId)){
				i=i+1;
				objresult.setRank("Rank "+i);
	        	}
			}
			pstmtn=conn.prepareStatement("SELECT `student_fname_var` FROM `campus_student` WHERE `student_id_int`=? AND `locationId`=? AND `fms_acadamicyear_id_int`=?");
			pstmtn.setString(1,studentId);
			pstmtn.setString(2,locationId);
			pstmtn.setString(3,accYear);
			rsn=pstmtn.executeQuery();
			while(rsn.next()){
				objresult.setMainName(rsn.getString("student_fname_var"));
			}
			list.add(objresult);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (rsscored != null&& (!rsscored.isClosed())) {
					rsscored.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rsscored1 != null&& (!rsscored1.isClosed())) {
					rsscored1.close();
				}
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (rsn != null&& (!rsn.isClosed())) {
					rsn.close();
				}
				if (pstscoredmarks != null&& (!pstscoredmarks.isClosed())) {
					pstscoredmarks.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstscoredmarks1 != null&& (!pstscoredmarks1.isClosed())) {
					pstscoredmarks1.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmtn != null&& (!pstmtn.isClosed())) {
					pstmtn.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getIndividualStudentMarksClass : Ending");
		return list;
	}

	public ArrayList<ReportMenuVo> getTermBaseOnLocation(String locId, String accId, UserLoggingsPojo userLoggingsVo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTermBaseOnLocation : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list=new ArrayList<ReportMenuVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement("SELECT `termid`,`termname` FROM `campus_fee_termdetails` WHERE `locationId`=? and accyear=? ORDER BY startdate");
			pstmt.setString(1, locId);
			pstmt.setString(2, accId);
			rs= pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo= new ReportMenuVo();
				vo.setTermname(rs.getString("termid"));
				vo.setTermId(rs.getString("termname"));
				list.add(vo);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}  finally {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getTermBaseOnLocation : Ending");
		
		return list;
	
	}


	public ArrayList<FeeCollectionVo> getFeeCollectionReport(String locationid, String accyear, String termId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getFeeCollectionReport : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			
			
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_COLLECTION_REPORT);
			pstmt.setString(1, locationid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, termId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				count++;
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setSno(count);
				vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
				vo.setChlnno(rs.getString("chln_no"));
				vo.setClassname(rs.getString("class"));
				vo.setStudentname(rs.getString("student"));
				vo.setPermanentaddress(rs.getString("address"));
				vo.setPaymentMode(rs.getString("paymentMode"));
				vo.setAmount_paid_so_far(rs.getDouble("amount_paid"));
				vo.setTermName(rs.getString("termname"));
				list.add(vo);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
			try{
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			}
			
			catch(SQLException e){
					e.printStackTrace();
				}
			
			
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getFeeCollectionReport : Ending");
		
		return list;
	}

	public ArrayList<FeeCollectionVo> getfeecollectionclasslist(
			String locationid, String accyear, String classid, String termId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getfeecollectionclasslist : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			
			
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_COLLECTION_CLASS_REPORT);
			pstmt.setString(1, locationid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, classid);
			pstmt.setString(4, termId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				count++;
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setSno(count);
				vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
				vo.setChlnno(rs.getString("chln_no"));
				vo.setClassname(rs.getString("class"));
				vo.setStudentname(rs.getString("student"));
				vo.setPermanentaddress(rs.getString("address"));
				vo.setPaymentMode(rs.getString("paymentMode"));
				vo.setAmount_paid_so_far(rs.getDouble("amount_paid"));
				vo.setTermName(rs.getString("termname"));
				list.add(vo);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} 
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getfeecollectionclasslist : Ending");
		
		return list;
	}

	public ArrayList<FeeCollectionVo> getFeeCollectionSectionReport(
			String locationid, String accyear, String classid, String setionid, String termId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getFeeCollectionSectionReport : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			
			
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_COLLECTION_SECTION_REPORT);
			pstmt.setString(1, locationid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, classid);
			pstmt.setString(4, setionid);
			pstmt.setString(5, termId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				count++;
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setSno(count);
				vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
				vo.setChlnno(rs.getString("chln_no"));
				vo.setClassname(rs.getString("class"));
				vo.setStudentname(rs.getString("student"));
				vo.setPermanentaddress(rs.getString("address"));
				vo.setPaymentMode(rs.getString("paymentMode"));
				vo.setAmount_paid_so_far(rs.getDouble("amount_paid"));
				vo.setTermName(rs.getString("termname"));
				list.add(vo);
		
			
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}	
			}
			catch(SQLException e){
					e.printStackTrace();
					
				}
		
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getFeeCollectionSectionReport : Ending");
		
		return list;
	}

	public ArrayList<FeeCollectionVo> getFeeCollectionPaymodeReport(
			String locationid, String accyear, String classid, String setionid,
			String paymodeid, String paymenttype, String termId, UserLoggingsPojo userLoggingsVo, String startdate, String enddate) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getFeeCollectionPaymodeReport : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
        String query = null;
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			
				if(paymenttype.equalsIgnoreCase("OFFLINE")){
					query = ReportsMenuSqlConstants.GET_FEE_COLLECTION_PAYMODE_REPORT_OFFLINE;
				}else {
					query = ReportsMenuSqlConstants.GET_FEE_COLLECTION_PAYMODE_REPORT;
				}
			
			    conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			    pstmt=conn.prepareStatement(query);
				pstmt.setString(1, locationid);
				pstmt.setString(2, accyear);
				pstmt.setString(3, classid);
				pstmt.setString(4, setionid);
				pstmt.setString(5, paymodeid);
				pstmt.setString(6, termId);
				pstmt.setString(7, startdate);
				pstmt.setString(8, enddate);
				//System.out.println(pstmt);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					count++;
					FeeCollectionVo vo=new FeeCollectionVo();
					vo.setSno(count);
					vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
					vo.setChlnno(rs.getString("chln_no"));
					vo.setClassname(rs.getString("class"));
					vo.setStudentname(rs.getString("student"));
					vo.setPermanentaddress(rs.getString("address"));
					vo.setPaymentMode(rs.getString("pay_mode"));
					vo.setAmount_paid_so_far(rs.getDouble("Amt_paid"));
					vo.setTermName(rs.getString("termname"));
					list.add(vo);
				}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			}
			
			catch(SQLException e){
					e.printStackTrace();
				}
			
			
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getFeeCollectionPaymodeReport : Ending");
		
		return list;
	}


	public ArrayList<ReportMenuVo> getterms(String location, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getterms : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> termList=new ArrayList<ReportMenuVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_TERMS);
			pstmt.setString(1,location);
			
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo termVo=new ReportMenuVo();
				termVo.setTermId(rs.getString("termid"));
				termVo.setTermname(rs.getString("termname"));
				termList.add(termVo);
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getterms : Ending");
		
		return termList;
	}

	public ArrayList<ReportMenuVo> DDReportList(String termid, String academic_year, String locationid, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: DDReportList : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> termList=new ArrayList<ReportMenuVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_DD_REPORT_LIST);
			pstmt.setString(1,termid);
			pstmt.setString(2,academic_year);
			pstmt.setString(3,locationid);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo termVo=new ReportMenuVo();
				termVo.setTermId(rs.getString("termcode"));
				termVo.setBankName(rs.getString("bank_name"));
				termVo.setDdNo(rs.getString("paymentParticulars"));
				termVo.setDdDate(rs.getString("dd_cheque_date"));
				termVo.setPaidDate(rs.getString("paidDate"));
				termVo.setAmount_paid(rs.getString("amount_paid"));
				termList.add(termVo);
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : DDReportList : Ending");
		
		return termList;
	}


	public ArrayList<FeeCollectionVo> getonlinelist(String locationid,
			String accyear, String classid, String setionid, String paymodeid,
			String paymenttype, String termId, UserLoggingsPojo userLoggingsVo, String startdate, String endate) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getonlinelist : Starting");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        int count=0;
     
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try{
			    conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			    pstmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_FEE_COLLECTION_PAYMODE_ONLINE_REPORT);
				pstmt.setString(1, locationid);
				pstmt.setString(2, accyear);
				pstmt.setString(3, classid);
				pstmt.setString(4, setionid);
				pstmt.setString(5, termId);
				pstmt.setString(6, startdate);
				pstmt.setString(7, endate);
				
				rs=pstmt.executeQuery();
				while(rs.next()){
					count++;
					FeeCollectionVo vo=new FeeCollectionVo();
					vo.setSno(count);
					vo.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paidDate")));
					vo.setChlnno(rs.getString("chln_no"));
					vo.setClassname(rs.getString("class"));
					vo.setStudentname(rs.getString("student"));
					vo.setPermanentaddress(rs.getString("address"));
					vo.setPaymentMode(rs.getString("paymentMode"));
					vo.setTermName(rs.getString("termname"));
					vo.setAmount_paid_so_far(rs.getDouble("amount_paid"));
					list.add(vo);
			
				
				}
			
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			}
			catch(SQLException e){
					e.printStackTrace();
				}
			
			
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getonlinelist : Ending");
		
		return list;
	}


	@Override

	public ArrayList<ReportMenuVo> getClassesByStream(String streamId,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getClassesByStream : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> streamList=new ArrayList<ReportMenuVo>();
	 	
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASSES);
			pstmt.setString(1, streamId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				
				ReportMenuVo streamVo=new ReportMenuVo();
				
				streamVo.setClassId(rs.getString("classdetail_id_int"));
				streamVo.setClassname(rs.getString("classdetails_name_var"));
				
				streamList.add(streamVo);
				
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getClassesByStream : Ending");
		
		return streamList;
	}

	@Override
	public ArrayList<ReportMenuVo> getSectionsByClassLoc(String classId,String location,UserLoggingsPojo custdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: getSectionsByClassLoc : Starting");
			
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<ReportMenuVo> streamList=new ArrayList<ReportMenuVo>();
		 	
			try {
			
				conn = JDBCConnection.getSeparateConnection(custdetails);
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.get_sections_by_class_loc);
				pstmt.setString(1, classId);
				pstmt.setString(2,location);
			
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					
					ReportMenuVo streamVo=new ReportMenuVo();
					
					streamVo.setSectionId(rs.getString("classsection_id_int"));
					streamVo.setSectionname(rs.getString("classsection_name_var"));
					
					streamList.add(streamVo);
					
				}
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : getSectionsByClassLoc : Ending");
			
			return streamList;
		}

	

	public ITFeeVo gettgetITFeeerms(String studentId, String accyer,
			String locationId, UserLoggingsPojo userLoggingsVo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl: gettgetITFeeerms : Starting");
			
			PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null,pstmt3 = null,pstmt4= null,pstmt5= null;
			ResultSet rs=null, rs1=null, rs2=null,rs3=null,rs4=null,rs5=null;
			Connection conn = null;
			String FeeName=null;
			double TutionFeeAmount=0.0;
			double LabFeeAmount=0.0;
			String TutionFeeAmountinString=null;
			String LabFeeAmountString=null;
			ITFeeVo Vo=new ITFeeVo();
			NumberToWord nw=new NumberToWord();
			try {
				
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_TERM);
				pstmt.setString(1, studentId);
				pstmt.setString(2,accyer);
				rs=pstmt.executeQuery();
				while(rs.next()){
					
					pstmt1=conn.prepareStatement(ReportsMenuSqlConstants.GET_IT_FEE);
					pstmt1.setString(1, accyer);
					pstmt1.setString(2,rs.getString("termcode"));
					pstmt1.setString(3,locationId);
					rs1=pstmt1.executeQuery();
					while(rs1.next()){
						FeeName=rs1.getString("FeeName");
						
						if(FeeName.toLowerCase().startsWith("tution")){
							TutionFeeAmount = TutionFeeAmount+ rs1.getDouble("feeAmount");
						}
						if(FeeName.toLowerCase().startsWith("compu")||FeeName.toLowerCase().startsWith("lab")){
							LabFeeAmount = LabFeeAmount+rs1.getDouble("feeAmount");
						}
					}
				}
				pstmt2=conn.prepareStatement(ReportsMenuSqlConstants.GET_PARENT);
				pstmt2.setString(1, studentId);
				rs2=pstmt2.executeQuery();
				while(rs2.next()){
					Vo.setParentname(rs2.getString("FatherName"));
				 }
				pstmt3=conn.prepareStatement(ReportsMenuSqlConstants.GET_YEAR);
				pstmt3.setString(1, accyer);
				rs3=pstmt3.executeQuery();
				while(rs3.next()){
					Vo.setAccyear(rs3.getString("acadamic_year"));
				 }
				pstmt4=conn.prepareStatement("SELECT `classsection_id_int`,`classdetail_id_int` FROM `campus_student_classdetails` WHERE `student_id_int`=?");
				pstmt4.setString(1, studentId);
				rs4=pstmt4.executeQuery();
				while(rs4.next()){
					pstmt5=conn.prepareStatement("SELECT CONCAT(cs.`student_fname_var`,' ',cs.`student_lname_var`) stuName,CONCAT(ca.`classdetails_name_var`,' ',cc.`classsection_name_var`) Class FROM `campus_student` cs LEFT JOIN `campus_classdetail` ca ON  cs.`locationId`=ca.`locationId` LEFT JOIN `campus_classsection` cc ON cs.`locationId`=cc.`locationId` WHERE cs.`student_id_int`=? AND ca.`classdetail_id_int`=? AND cc.`classsection_id_int`=?");
					pstmt5.setString(1, studentId);
					pstmt5.setString(2, rs4.getString("classdetail_id_int"));
					pstmt5.setString(3, rs4.getString("classsection_id_int"));
					rs5=pstmt5.executeQuery();
					while(rs5.next()){
						Vo.setStuName(rs5.getString("stuName"));
						Vo.setClassSec(rs5.getString("Class"));
					}
				}
				
				
				TutionFeeAmountinString=nw.convert(Math.round(TutionFeeAmount));
				LabFeeAmountString=nw.convert(Math.round(LabFeeAmount));
				
				Vo.setStringtution(TutionFeeAmountinString);
				Vo.setStringclab(LabFeeAmountString);
				Vo.setClab(LabFeeAmount);
				Vo.setTutorial(TutionFeeAmount);
			
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (rs1 != null&& (!rs1.isClosed())) {
						rs1.close();
					}
					if (rs2 != null&& (!rs2.isClosed())) {
						rs2.close();
					}
					if (rs3 != null&& (!rs3.isClosed())) {
						rs3.close();
					}
					if (rs4 != null&& (!rs4.isClosed())) {
						rs4.close();
					}
					if (rs5 != null&& (!rs5.isClosed())) {
						rs5.close();
					}
					if (pstmt != null&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (pstmt1 != null&& (!pstmt1.isClosed())) {
						pstmt1.close();
					}
					if (pstmt2 != null&& (!pstmt2.isClosed())) {
						pstmt2.close();
					}
					if (pstmt3 != null&& (!pstmt3.isClosed())) {
						pstmt3.close();
					}
					if (pstmt4 != null&& (!pstmt4.isClosed())) {
						pstmt4.close();
					}
					if (pstmt5 != null&& (!pstmt5.isClosed())) {
						pstmt5.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ReportsMenuDaoImpl : gettgetITFeeerms : Ending");
			return Vo;
		}

	public ArrayList<ReportMenuVo> getStudentsTempNewAdmissionList(
			ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentsTempNewAdmissionList  Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(userLoggingsVo);
			/*pstmt=conn.prepareStatement("SELECT  * FROM `campus_temporary_admisssion_details`  WHERE accyear=? AND classname LIKE ?");*/
			/*pstmt=conn.prepareStatement(SQLUtilConstants.TEMP_ADMISSION_LIST);*///this is for parent mail sending 
			pstmt=conn.prepareStatement(SQLUtilConstants.NEW_TEMP_ADMISSION_LIST);
			pstmt.setString(1, vo.getLocationId());
			pstmt.setString(2, vo.getAccyearId());
			pstmt.setString(3, vo.getClassId());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ReportMenuVo obj = new ReportMenuVo();
				count ++;
				obj.setCount(count);
				obj.setPequeryId(rs.getString("enquiry_id"));
				obj.setStudentnamelabel(rs.getString("Student"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setFatherName(rs.getString("parents_name"));
				obj.setEmail(rs.getString("email"));
				obj.setMobileno(rs.getString("mobile_number"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentsTempNewAdmissionList  Ending");
		return list;
	}

	public List<ReportMenuVo> getStudentListAdmiWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentListAdmiWise  Starting");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =null;
		List<ReportMenuVo> stuList = new ArrayList<ReportMenuVo>();
		int count = 0;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			if(!vo.getLocationId().equalsIgnoreCase("all") && !vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_ADMISSION_LIST);
			}
			else if(vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_ADMISSION_CLASS_LIST);
			}else if(vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_ADMISSION_SECTION_LIST);
			}else if(!vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_ADMISSIONLIST);
			}else{
				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_NEWADMISSION_LIST);
			}
			
			if(vo.getLocationId().equalsIgnoreCase("all")){
				vo.setLocationId("%%");
			}
			if(vo.getClassId().equalsIgnoreCase("all")){
				vo.setClassId("%%");
			}
			if(vo.getSectionId().equalsIgnoreCase("all")){
				vo.setSectionId("%%");
			}
			
			pstmt.setString(1,vo.getClassId());
			pstmt.setString(2,vo.getSectionId());
			pstmt.setString(3,vo.getLocationId());
			pstmt.setString(4,vo.getAccyearId());
			pstmt.setString(5, vo.getStatus());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count ++;
				ReportMenuVo obj = new ReportMenuVo();
				obj.setSno(count);
				obj.setStudentnamelabel(rs.getString("student"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				stuList.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentListAdmiWise  Ending");
		return stuList;
	}

	public List<ReportMenuVo> getstudentRollNoWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentRollNoWise  Starting");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =null;
		List<ReportMenuVo> stuList = new ArrayList<ReportMenuVo>();
		int count = 0;
		String value="";
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			if(!vo.getLocationId().equalsIgnoreCase("all") && !vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
 
				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_LIST_ROLLNO_WISE);
				value="STUDENT_LIST_ROLLNO_WISE";
			}
			else if(vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
 
				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_LIST_ROLLNO_WISE_CLASS);
				value="STUDENT_LIST_ROLLNO_WISE_CLASS";
 
			}else if(vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
 
				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_LIST_ROLL_NO_WISE_SECTION);
				value="STUDENT_LIST_ROLL_NO_WISE_SECTION";
			}else if(!vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
 
				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_LIST_ROLL_NO_WISE_NEW_LOC);
				value="STUDENT_LIST_ROLL_NO_WISE_NEW_LOC";
			}else{
 
				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_LIST_ROLLWISE);
				value="STUDENT_LIST_ROLLWISE";
			}
			
			if(vo.getLocationId().equalsIgnoreCase("all")){
				vo.setLocationId("%%");
			}
			if(vo.getClassId().equalsIgnoreCase("all")){
				vo.setClassId("%%");
			}
			if(vo.getSectionId().equalsIgnoreCase("all")){
				vo.setSectionId("%%");
			}
			
			pstmt.setString(1,vo.getClassId());
			pstmt.setString(2,vo.getSectionId());
			pstmt.setString(3,vo.getLocationId());
			pstmt.setString(4,vo.getAccyearId());
			pstmt.setString(5,vo.getStatus());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count ++;
				ReportMenuVo obj = new ReportMenuVo();
				obj.setSno(count);
				obj.setStudentnamelabel(rs.getString("student"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				stuList.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentRollNoWise  Ending");
		return stuList;
	}

	public List<ReportMenuVo> getstudentAlphaWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentAlphaWise  Starting");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =null;
		List<ReportMenuVo> stuList = new ArrayList<ReportMenuVo>();
		int count = 0;
		String value="";
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			if(!vo.getLocationId().equalsIgnoreCase("all") && !vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
 
				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_LIST_ALPHABETIC_WISE);
				value="STUDENT_LIST_ALPHABETIC_WISE";
			/*	pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_LOC_LIST);*/
 
			}
			else if(vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
 
				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_ADMISSION_CLASS_LIST_ALPHABETIC);
				value="STUDENT_ADMISSION_CLASS_LIST_ALPHABETIC";
/*				pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_LOC_CLASS_LIST);*/
 
			}else if(vo.getClassId().equalsIgnoreCase("all") && !vo.getSectionId().equalsIgnoreCase("all")){
 
				pstmt = conn.prepareStatement(SQLUtilConstants.STU_ADMISSION_SECTION_LIST_ALP);
				value="STU_ADMISSION_SECTION_LIST_ALP";
			/*	pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_LOC_SEC_LIST);*/
 
			}else if(!vo.getClassId().equalsIgnoreCase("all") && vo.getSectionId().equalsIgnoreCase("all")){
 
				pstmt = conn.prepareStatement(SQLUtilConstants.STU_NEW_LIST);
				value="STU_NEW_LIST";
				/*pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_LOC_NEW_ADD_LIST);*/
 
			}else{
 
				pstmt = conn.prepareStatement(SQLUtilConstants.STU_NEW_LOC_LIST);
				value="STU_NEW_LOC_LIST";
				/*pstmt = conn.prepareStatement(SQLUtilConstants.STUDENT_LOC_NEW_ADMISSION_LIST);*/
 
			}
			
			if(vo.getLocationId().equalsIgnoreCase("all")){
				vo.setLocationId("%%");
			}
			if(vo.getClassId().equalsIgnoreCase("all")){
				vo.setClassId("%%");
			}
			if(vo.getSectionId().equalsIgnoreCase("all")){
				vo.setSectionId("%%");
			}
			 
			pstmt.setString(1,vo.getClassId());
			pstmt.setString(2,vo.getSectionId());
			pstmt.setString(3,vo.getLocationId());
			pstmt.setString(4,vo.getAccyearId());
			pstmt.setString(5,vo.getStatus());
			
			/*System.out.println(value+" -->>"+pstmt);*/
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				count ++;
				ReportMenuVo obj = new ReportMenuVo();
				obj.setSno(count);
				obj.setStudentnamelabel(rs.getString("student"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionname(rs.getString("classsection_name_var"));
				obj.setStudentRollNo(rs.getString("student_rollno"));
				obj.setAdmissionNo(rs.getString("student_admissionno_var"));
				stuList.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentAlphaWise  Ending");
		return stuList;
	}

	public ArrayList<HashMap<String, String>> getCustomizableStudentReportsExcell(String formValueArray, String location, String accyear, String className, String section, UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getCustomizableStudentReportsExcell  Starting");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =null;
		
		ArrayList<HashMap<String, String>> fullstudentList=new ArrayList<HashMap<String,String>>();
		
		
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			if(location.equalsIgnoreCase("all")){
				pstmt=conn.prepareStatement("SELECT DISTINCT "+formValueArray+" FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int AND st.`locationId`=cs.`locationId` JOIN campus_location loc ON loc.Location_Id = cs.locationId AND loc.`isActive`='Y' JOIN `campus_acadamicyear` ca ON ca.`acadamic_id`=cs.`fms_acadamicyear_id_int` AND ca.`isActive`='Y' JOIN campus_classstream ccst ON ccst.classstream_id_int=cs.fms_classstream_id_int AND ccst.`isActive`='Y' JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND cd.locationId = cs.locationId AND cd.`isActive`='Y' JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId AND sec.`isActive`='Y' LEFT JOIN campus_class_specialization csp ON cs.specilization=csp.Specialization_Id  AND csp.`isActive`='Y' JOIN campus_parentchildrelation pra ON st.student_id_int=pra.stu_addmissionNo JOIN campus_parents pr ON pr.ParentID=pra.parentid LEFT JOIN campus_caste_category cct ON cct.castCatId=st.casteCategory LEFT JOIN campus_caste ct ON ct.casteId=st.student_caste LEFT JOIN campus_religion crl ON crl.religionId=st.student_religion_var LEFT JOIN campus_house_settings chs ON chs.house_id=cs.student_house LEFT JOIN campus_students_contacts ccn ON st.student_id_int=ccn.studentId WHERE cs.fms_acadamicyear_id_int=? AND cs.locationId=? AND st.student_status_var='active' ORDER BY student_fname_var");
			
				pstmt.setString(1, accyear);
				pstmt.setString(2, location);
			}
			else if(className.equalsIgnoreCase("all")){
				pstmt=conn.prepareStatement("SELECT DISTINCT "+formValueArray+" FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int AND st.`locationId`=cs.`locationId` JOIN campus_location loc ON loc.Location_Id = cs.locationId AND loc.`isActive`='Y' JOIN `campus_acadamicyear` ca ON ca.`acadamic_id`=cs.`fms_acadamicyear_id_int` AND ca.`isActive`='Y' JOIN campus_classstream ccst ON ccst.classstream_id_int=cs.fms_classstream_id_int AND ccst.locationId=cs.locationId AND ccst.`isActive`='Y' JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND cd.locationId = cs.locationId AND cd.`isActive`='Y' JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId AND sec.`isActive`='Y' LEFT JOIN campus_class_specialization csp ON cs.specilization=csp.Specialization_Id AND csp.`isActive`='Y' JOIN campus_parentchildrelation pra ON st.student_id_int=pra.stu_addmissionNo JOIN campus_parents pr ON pr.ParentID=pra.parentid LEFT JOIN campus_caste_category cct ON cct.castCatId=st.casteCategory LEFT JOIN campus_caste ct ON ct.casteId=st.student_caste LEFT JOIN campus_religion crl ON crl.religionId=st.student_religion_var LEFT JOIN campus_house_settings chs ON chs.house_id=cs.student_house LEFT JOIN campus_students_contacts ccn ON st.student_id_int=ccn.studentId WHERE cs.fms_acadamicyear_id_int=? AND cs.locationId=? AND st.student_status_var='active' ORDER BY student_fname_var");
			
				pstmt.setString(1, accyear);
				pstmt.setString(2, location);
				
			}
			else if(section.equalsIgnoreCase("all")){
				pstmt=conn.prepareStatement("SELECT DISTINCT "+formValueArray+" FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int AND st.`locationId`=cs.`locationId` JOIN campus_location loc ON loc.Location_Id = cs.locationId AND loc.`isActive`='Y' JOIN `campus_acadamicyear` ca ON ca.`acadamic_id`=cs.`fms_acadamicyear_id_int` AND ca.`isActive`='Y' JOIN campus_classstream ccst ON ccst.classstream_id_int=cs.fms_classstream_id_int AND ccst.locationId=cs.locationId AND ccst.`isActive`='Y' JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND cd.locationId = cs.locationId AND cd.`isActive`='Y' JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId AND sec.`isActive`='Y' LEFT JOIN campus_class_specialization csp ON cs.specilization=csp.Specialization_Id AND csp.`isActive`='Y' JOIN campus_parentchildrelation pra ON st.student_id_int=pra.stu_addmissionNo JOIN campus_parents pr ON pr.ParentID=pra.parentid LEFT JOIN campus_caste_category cct ON cct.castCatId=st.casteCategory LEFT JOIN campus_caste ct ON ct.casteId=st.student_caste LEFT JOIN campus_religion crl ON crl.religionId=st.student_religion_var LEFT JOIN campus_house_settings chs ON chs.house_id=cs.student_house LEFT JOIN campus_students_contacts ccn ON st.student_id_int=ccn.studentId WHERE cs.fms_acadamicyear_id_int=? AND cs.locationId=? AND cs.classdetail_id_int=? AND st.student_status_var='active' ORDER BY student_fname_var");
			
				pstmt.setString(1, accyear);
				pstmt.setString(2, location);
				pstmt.setString(3, className);
				
			}
			else{
				pstmt=conn.prepareStatement("SELECT DISTINCT "+formValueArray+" FROM campus_student st JOIN campus_student_classdetails cs ON st.student_id_int = cs.student_id_int JOIN campus_location loc ON loc.Location_Id = cs.locationId AND loc.`isActive`='Y' JOIN `campus_acadamicyear` ca ON ca.`acadamic_id`=cs.`fms_acadamicyear_id_int` AND ca.`isActive`='Y' JOIN campus_classstream ccst ON ccst.classstream_id_int=cs.fms_classstream_id_int AND ccst.locationId=cs.locationId AND ccst.`isActive`='Y'  JOIN campus_classdetail cd ON cd.classdetail_id_int = cs.classdetail_id_int AND cd.locationId = cs.locationId AND cd.`isActive`='Y' JOIN campus_classsection sec ON sec.classsection_id_int = cs.classsection_id_int AND sec.locationId = cs.locationId  AND sec.`isActive`='Y'  LEFT JOIN campus_class_specialization csp ON cs.specilization=csp.Specialization_Id  AND csp.`isActive`='Y' JOIN campus_parentchildrelation pra ON st.student_id_int=pra.stu_addmissionNo JOIN campus_parents pr ON pr.ParentID=pra.parentid LEFT JOIN campus_caste_category cct ON cct.castCatId=st.casteCategory LEFT JOIN campus_caste ct ON ct.casteId=st.student_caste LEFT JOIN campus_religion crl ON crl.religionId=st.student_religion_var LEFT JOIN campus_house_settings chs ON chs.house_id=cs.student_house LEFT JOIN campus_students_contacts ccn ON st.student_id_int=ccn.studentId WHERE cs.fms_acadamicyear_id_int=? AND cs.locationId=? AND cs.classdetail_id_int=? AND cs.classsection_id_int=? AND st.student_status_var='active' ORDER BY student_fname_var");
			
				pstmt.setString(1, accyear);
				pstmt.setString(2, location);
				pstmt.setString(3, className);
				pstmt.setString(4, section);
				
			}
			rs=pstmt.executeQuery();
		   while(rs.next()){
			   HashMap<String, String> studentList=new HashMap<String, String>();
			   for(int i=0;i<formValueArray.split(",").length;i++){
			   studentList.put(formValueArray.split(",")[i],rs.getString(i+1));
			   }
			   fullstudentList.add(studentList);
		   }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getCustomizableStudentReportsExcell  Ending");
		return fullstudentList;
	}

	public ArrayList<ReportMenuVo> getExpenditureReport(ReportMenuForm reform, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getExpenditureReport : Starting");
		
		PreparedStatement pstmt = null;
		PreparedStatement oblancepstmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet obalancers=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> incomeBalanceList=new ArrayList<ReportMenuVo>();
		int count=0;
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			oblancepstmt=conn.prepareStatement(ReportsMenuSqlConstants.GET_OPENING_BALANCE);
			oblancepstmt.setString(1, HelperClass.convertUIToDatabase(reform.getFromdate()));
			oblancepstmt.setString(2, HelperClass.convertUIToDatabase(reform.getFromdate()));
			oblancepstmt.setString(3, reform.getLocation());
			obalancers=oblancepstmt.executeQuery();
			double totalIncome=0.0;
			double totalExpense=0.0;
			while(obalancers.next()){
				totalIncome=obalancers.getDouble("Recieved");
				totalExpense=obalancers.getDouble("Amount");
			}
			java.util.List<String> dates=HelperClass.getDateListBetweenDates(HelperClass.convertUIToDatabase(reform.getFromdate()), HelperClass.convertUIToDatabase(reform.getTodate()));
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_INCOME_BALANCE);
			PreparedStatement pstmt1 = conn.prepareStatement(ReportsMenuSqlConstants.GET_INCOME_BALANCE1);
			double openingBalance =totalIncome-totalExpense;
			double carryonBalnace=openingBalance;
			double balance=0.0;
			for (int d=0;d<dates.size();d++){
				pstmt.setString(1, dates.get(d));
				pstmt1.setString(1, dates.get(d));
				rs=pstmt.executeQuery();
				rs1=pstmt1.executeQuery();
				while(rs.next() && rs1.next()){
					ReportMenuVo expenditureVO=new ReportMenuVo();
					count++;
					expenditureVO.setSno(count);
					expenditureVO.setDate(HelperClass.convertDatabaseToUI(dates.get(d)));
					openingBalance=carryonBalnace;
					expenditureVO.setOpeingBalance(openingBalance);
					expenditureVO.setExpenditure(rs.getDouble("Amount"));
					expenditureVO.setFeeAmount(rs1.getDouble("amount_paid"));
					balance=(openingBalance+rs1.getDouble("amount_paid"))-rs.getDouble("Amount");
					expenditureVO.setBalance(balance);
					carryonBalnace=balance;
					incomeBalanceList.add(expenditureVO);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getExpenditureReport : Ending");
		
		return incomeBalanceList;
	
	}

	public ArrayList<ReportMenuVo> getClassesByStreamAndLocation(String streamId, String locationname, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getClassesByStreamAndLocation : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> streamList=new ArrayList<ReportMenuVo>();
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASSESBY_LOCATION);
			pstmt.setString(1, locationname);
			pstmt.setString(2, streamId);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				ReportMenuVo streamVo=new ReportMenuVo();
				
				streamVo.setClassId(rs.getString("classdetail_id_int"));
				streamVo.setClassname(rs.getString("classdetails_name_var"));
				
				streamList.add(streamVo);
				
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getClassesByStreamAndLocation : Ending");
		
		return streamList;
	}
	public List<ReportMenuVo> getStudentClassSectionWiseListForReport(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentClassSectionWiseListForReport : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> stuList=new ArrayList<ReportMenuVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_LIST_BY_SECTION);
			pstmt.setString(1, vo.getLocationId());
			pstmt.setString(2, vo.getAccyearId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getExamId());
			pstmt.setString(6, vo.getSubjectid());
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo1=new ReportMenuVo();
				vo1.setStudentIdNo(rs.getString("student_admissionno_var"));
				vo1.setAccyearname(rs.getString("acadamic_year"));
				vo1.setStudentnamelabel(rs.getString("studentname"));
				vo1.setClassname(rs.getString("classdetails_name_var"));
				vo1.setSectionname(rs.getString("classsection_name_var"));
				vo1.setLocationName(rs.getString("Location_Name"));
				vo1.setStreamname(rs.getString("classstream_name_var"));
				vo1.setExamname(rs.getString("examname"));
				vo1.setExamcode(rs.getString("examcode"));
				vo1.setExamTypeName(rs.getString("examtypename"));
				vo1.setSubjectName(rs.getString("subjectName"));
				vo1.setExamStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				vo1.setExamEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				vo1.setExamtypePrifix(rs.getString("exam_prefix"));
				stuList.add(vo1);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentClassSectionWiseListForReport : Ending");
		return stuList;
	}
	
	public List<ReportMenuVo> getStudentClassSectionWiseListForReportByAll(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentClassSectionWiseListForReport : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> stuList=new ArrayList<ReportMenuVo>();
	 	
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_LIST_BY_SECTION1);
			pstmt.setString(1, vo.getLocationId());
			pstmt.setString(2, vo.getAccyearId());
			pstmt.setString(3, vo.getClassId());
			pstmt.setString(4, vo.getSectionId());
			pstmt.setString(5, vo.getExamId());
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReportMenuVo vo1=new ReportMenuVo();
				vo1.setStudentIdNo(rs.getString("student_admissionno_var"));
				vo1.setAccyearname(rs.getString("acadamic_year"));
				vo1.setStudentnamelabel(rs.getString("studentname"));
				vo1.setClassname(rs.getString("classdetails_name_var"));
				vo1.setSectionname(rs.getString("classsection_name_var"));
				vo1.setLocationName(rs.getString("Location_Name"));
				vo1.setStreamname(rs.getString("classstream_name_var"));
				vo1.setExamname(rs.getString("examname"));
				vo1.setExamcode(rs.getString("examcode"));
				vo1.setExamTypeName(rs.getString("examtypename"));
				vo1.setExamStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				vo1.setExamEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				stuList.add(vo1);
				
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentClassSectionWiseListForReport : Ending");
		
		return stuList;
	}

	public ReportMenuVo getTerm1Exams(String accyear, String classId, String locationid,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		Connection conn = null;
		String startdate=null,examid="",enddate=null,examtype="",exam_prefix="";
		ReportMenuVo vo=new ReportMenuVo();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			//String enddate=getClassTermEndDate();
			
			pstmt = conn.prepareStatement("SELECT `startDate`,`endDate` FROM `campus_acadamicyear` WHERE `acadamic_id`=?");
			pstmt.setString(1,accyear);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				startdate=rs.getString("startDate");
				//enddate=rs.getString("endDate");
				
				pstmt1 = conn.prepareStatement("SELECT `examid`,examtype FROM `campus_examination` WHERE `startDate` BETWEEN ? AND '"+startdate.split("-")[0]+"-10-15' and `classid`=? AND `loc_id`=? ORDER BY examtype");
				pstmt1.setString(1,startdate);
				//pstmt1.setString(1,enddate);
				pstmt1.setString(2,classId);
				pstmt1.setString(3,locationid);
				rs1 = pstmt1.executeQuery();
			
				while(rs1.next()){
					examid+=rs1.getString("examid")+",";
				}
				vo.setExamname(examid);
				//SELECT COUNT(`examtype`) FROM `campus_examination` WHERE `startDate` BETWEEN '2017-04-01' AND '2018-03-31' AND `classid`='CCD12' AND `loc_id`='LOC1' GROUP BY `examtype`

				pstmt2 = conn.prepareStatement("SELECT `examtype`,et.`exam_prefix` FROM `campus_examination` LEFT JOIN `campus_examtype` et ON et.`examtypeid`=examtype WHERE `startDate` BETWEEN ? AND '"+startdate.split("-")[0]+"-10-15' AND `classid`=? AND `loc_id`=? GROUP BY `examtype`");
				pstmt2.setString(1,startdate);
				//pstmt2.setString(1,enddate);
				pstmt2.setString(2,classId);
				pstmt2.setString(3,locationid);
			
				rs2 = pstmt2.executeQuery();
				while(rs2.next()){
					examtype+=rs2.getString("examtype")+",";
					exam_prefix+=rs2.getString("exam_prefix")+",";
				}
				vo.setExamtypeid(examtype);
				vo.setExamtypeprefix(exam_prefix);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return vo;

	
	}
	public ReportMenuVo getTerm2Exams(String accyear, String classId, String locationid,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		Connection conn = null;
		String startdate=null,examid="",enddate=null,examtype="",exam_prefix="";
		ReportMenuVo vo=new ReportMenuVo();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pstmt = conn.prepareStatement("SELECT `startDate`,`endDate` FROM `campus_acadamicyear` WHERE `acadamic_id`=?");
			pstmt.setString(1,accyear);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				//startdate=rs.getString("startDate");
				enddate=rs.getString("endDate");
				
				pstmt1 = conn.prepareStatement("SELECT `examid`,examtype FROM `campus_examination` WHERE `startDate` BETWEEN '2017-10-16' AND ? and `classid`=? AND `loc_id`=? ORDER BY examtype");
				//pstmt1.setString(1,startdate);
				pstmt1.setString(1,enddate);
				pstmt1.setString(2,classId);
				pstmt1.setString(3,locationid);
				rs1 = pstmt1.executeQuery();
				while(rs1.next()){
					examid+=rs1.getString("examid")+",";
				}
				vo.setExamname(examid);
				//SELECT COUNT(`examtype`) FROM `campus_examination` WHERE `startDate` BETWEEN '2017-04-01' AND '2018-03-31' AND `classid`='CCD12' AND `loc_id`='LOC1' GROUP BY `examtype`

				pstmt2 = conn.prepareStatement("SELECT `examtype`,et.`exam_prefix` FROM `campus_examination` LEFT JOIN `campus_examtype` et ON et.`examtypeid`=examtype WHERE `startDate` BETWEEN '2017-10-16' AND ? AND `classid`=? AND `loc_id`=? GROUP BY `examtype`");
				//pstmt2.setString(1,startdate);
				pstmt2.setString(1,enddate);
				pstmt2.setString(2,classId);
				pstmt2.setString(3,locationid);
				rs2 = pstmt2.executeQuery();
				while(rs2.next()){
					examtype+=rs2.getString("examtype")+",";
					exam_prefix+=rs2.getString("exam_prefix")+",";
				}
				vo.setExamtypeid(examtype);
				vo.setExamtypeprefix(exam_prefix);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return vo;

	
	}
	
	public ReportMenuVo getFinalExams(String accyear, String classId, String locationid,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		Connection conn = null;
		String startdate=null,examid="",enddate=null,examtype="",exam_prefix="";
		ReportMenuVo vo=new ReportMenuVo();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pstmt = conn.prepareStatement("SELECT `startDate`,`endDate` FROM `campus_acadamicyear` WHERE `acadamic_id`=?");
			pstmt.setString(1,accyear);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				startdate=rs.getString("startDate");
				enddate=rs.getString("endDate");
				
				pstmt1 = conn.prepareStatement("SELECT `examid`,examtype FROM `campus_examination` WHERE `startDate` BETWEEN ? AND ? and `classid`=? AND `loc_id`=? ORDER BY examtype");
				pstmt1.setString(1,startdate);
				pstmt1.setString(2,enddate);
				pstmt1.setString(3,classId);
				pstmt1.setString(4,locationid);
				rs1 = pstmt1.executeQuery();
				while(rs1.next()){
					examid+=rs1.getString("examid")+",";
				}
				vo.setExamname(examid);
				//SELECT COUNT(`examtype`) FROM `campus_examination` WHERE `startDate` BETWEEN '2017-04-01' AND '2018-03-31' AND `classid`='CCD12' AND `loc_id`='LOC1' GROUP BY `examtype`

				pstmt2 = conn.prepareStatement("SELECT `examtype`,et.`exam_prefix` FROM `campus_examination` LEFT JOIN `campus_examtype` et ON et.`examtypeid`=examtype WHERE `startDate` BETWEEN ? AND ? AND `classid`=? AND `loc_id`=? GROUP BY `examtype`");
				pstmt2.setString(1,startdate);
				pstmt2.setString(2,enddate);
				pstmt2.setString(3,classId);
				pstmt2.setString(4,locationid);
				rs2 = pstmt2.executeQuery();
				while(rs2.next()){
					examtype+=rs2.getString("examtype")+",";
					exam_prefix+=rs2.getString("exam_prefix")+",";
				}
				vo.setExamtypeid(examtype);
				vo.setExamtypeprefix(exam_prefix);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return vo;

	
	}
	
	public List<ReportMenuVo> getTermWiseReportCard(ReportMenuVo vo, UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement ps=null,ps1=null,pstmt = null,pstmt1 = null,pstgrade=null,pstmt2=null,pstmt3=null,pstmt4=null,pstgrade1=null;
		ResultSet rs = null,rs1 = null,rsgrade=null,rs2=null,rs3=null,rs4=null,rsgrade1=null,rss=null,rss1=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		int periodicmark=0,notebookmark=0,subenrichment=0,yearlymark=0;
		int periodicmark1=0,notebookmark1=0,subenrichment1=0,yearlymark1=0;
		
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			
			ps=conn.prepareStatement("SELECT stpd.`periodic_mark1`,stpd.`notebook_mark`,stpd.`subenrichment`,stpd.`yearlytheorymark` FROM `campus_exam_report_setup` stp LEFT JOIN `campus_exam_report_setup_detail` stpd ON stpd.`report_setup_id`=stp.`report_setup_id` WHERE stp.`class_id`=? AND stp.`accy_id`=? AND stp.`loc_id`=? AND stpd.`report_name`='term1'");
			ps.setString(1, vo.getClassId());
			ps.setString(2, vo.getAccYear());
			ps.setString(3, vo.getLocationId());
			rss = ps.executeQuery();
			while(rss.next()){
				periodicmark=rss.getInt("periodic_mark1");
				notebookmark=rss.getInt("notebook_mark");
				subenrichment=rss.getInt("subenrichment");
				yearlymark=rss.getInt("yearlytheorymark");
			}
			
			ps1=conn.prepareStatement("SELECT stpd.`periodic_mark1`,stpd.`notebook_mark`,stpd.`subenrichment`,stpd.`yearlytheorymark` FROM `campus_exam_report_setup` stp LEFT JOIN `campus_exam_report_setup_detail` stpd ON stpd.`report_setup_id`=stp.`report_setup_id` WHERE stp.`class_id`=? AND stp.`accy_id`=? AND stp.`loc_id`=? AND stpd.`report_name`='term2'");
			ps1.setString(1, vo.getClassId());
			ps1.setString(2, vo.getAccYear());
			ps1.setString(3, vo.getLocationId());
			rss1 = ps1.executeQuery();
			while(rss1.next()){
				periodicmark1=rss1.getInt("periodic_mark1");
				notebookmark1=rss1.getInt("notebook_mark");
				subenrichment1=rss1.getInt("subenrichment");
				yearlymark1=rss1.getInt("yearlytheorymark");
			}
			
			String examtypeId=vo.getExamtypeid();
			String examtypeIdterm2=vo.getExamstypeidterm2();
			String termexam1=vo.getTerm1();
			String termexam2=vo.getTerm2();
			String[] splitExamTypeId=examtypeId.split(",");
			String[] splitExamTypeIdTerm2=examtypeIdterm2.split(",");
			String[] splitTerm1Exam=termexam1.split(",");
			String[] splitTerm2Exam=termexam2.split(",");
			String stuid[]=vo.getStudentId().split(",");
			for(int i=0; i<stuid.length; i++){
				if(vo.getStudentId() != "" && !vo.getSectionId().equalsIgnoreCase("all")){
					pstmt=conn.prepareStatement("SELECT DISTINCT csmd.`stu_id`,acc.`acadamic_year`,ccs.`classdetails_name_var`,CONCAT(ccs.`classdetails_name_var`,'/',sec.`classsection_name_var`) AS classsection,csc.`student_rollno`,stu.student_application_no,stu.`student_admissionno_var`,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentname,par.`FatherName` AS fathername,par.`student_mothername_var` AS mothername,stu.`student_dob_var`,par.`mobileno`,par.`address` FROM `campus_studentwise_mark_details` csmd LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=csmd.`stu_id` AND csc.`fms_acadamicyear_id_int`=csmd.`Academic_yearid` AND csc.`locationId`=csmd.`location_id` LEFT JOIN `campus_acadamicyear` acc ON acc.`acadamic_id`=csmd.`Academic_yearid` LEFT JOIN `campus_classdetail` ccs ON ccs.`classdetail_id_int`=csmd.`classid` LEFT JOIN `campus_classsection` sec ON sec.`classsection_id_int`=csmd.`sectionid` AND sec.`classdetail_id_int`=ccs.`classdetail_id_int` LEFT JOIN `campus_student` stu ON stu.`student_id_int`=csc.`student_id_int` LEFT JOIN `campus_parentchildrelation` parc ON parc.`stu_addmissionNo`=csmd.`stu_id` LEFT JOIN `campus_parents` par ON par.`ParentID`=parc.`parentid` WHERE csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? AND csmd.sectionid=? AND csmd.`stu_id`=? GROUP BY csmd.`stu_id`");
					pstmt.setString(1, vo.getAccYear());
					pstmt.setString(2, vo.getLocationId());
					pstmt.setString(3, vo.getClassId());
					pstmt.setString(4, vo.getSectionId());
					pstmt.setString(5, stuid[i]);
				}else if(vo.getStudentId() != "" && vo.getSectionId().equalsIgnoreCase("all")){
					pstmt=conn.prepareStatement("SELECT DISTINCT csmd.`stu_id`,acc.`acadamic_year`,ccs.`classdetails_name_var`,CONCAT(ccs.`classdetails_name_var`,'/',sec.`classsection_name_var`) AS classsection,csc.`student_rollno`,stu.student_application_no,stu.`student_admissionno_var`,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentname,par.`FatherName` AS fathername,par.`student_mothername_var` AS mothername,stu.`student_dob_var`,par.`mobileno`,par.`address` FROM `campus_studentwise_mark_details` csmd LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=csmd.`stu_id` AND csc.`fms_acadamicyear_id_int`=csmd.`Academic_yearid` AND csc.`locationId`=csmd.`location_id` LEFT JOIN `campus_acadamicyear` acc ON acc.`acadamic_id`=csmd.`Academic_yearid` LEFT JOIN `campus_classdetail` ccs ON ccs.`classdetail_id_int`=csmd.`classid` LEFT JOIN `campus_classsection` sec ON sec.`classsection_id_int`=csmd.`sectionid` AND sec.`classdetail_id_int`=ccs.`classdetail_id_int` LEFT JOIN `campus_student` stu ON stu.`student_id_int`=csc.`student_id_int` LEFT JOIN `campus_parentchildrelation` parc ON parc.`stu_addmissionNo`=csmd.`stu_id` LEFT JOIN `campus_parents` par ON par.`ParentID`=parc.`parentid` WHERE csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? and csmd.`stu_id`=? GROUP BY csmd.`stu_id`");
					pstmt.setString(1, vo.getAccYear());
					pstmt.setString(2, vo.getLocationId());
					pstmt.setString(3, vo.getClassId());
					pstmt.setString(4, stuid[i]);
				}
				rs = pstmt.executeQuery();
				while(rs.next()){

					ArrayList<ReportMenuVo> list1 = new ArrayList<ReportMenuVo>();				
					ArrayList<ReportMenuVo> list2 = new ArrayList<ReportMenuVo>();				
					ReportMenuVo vo1=new ReportMenuVo();
					String studentid=rs.getString("stu_id");
					vo1.setAccyearname(rs.getString("acadamic_year"));
					vo1.setClassname(rs.getString("classdetails_name_var"));
					if(rs.getString("student_rollno") != null){
						vo1.setClass_and_section(rs.getString("classsection")+"/"+rs.getString("student_rollno"));
					}else{
						vo1.setClass_and_section(rs.getString("classsection")+"/-");
					}
					//vo1.setStudentRollNo(rs.getString("student_rollno"));
					vo1.setAdmissionNo(rs.getString("student_admissionno_var"));
					vo1.setStudentnamelabel(rs.getString("studentname"));
					vo1.setFatherName(rs.getString("fathername"));
					vo1.setMotherName(rs.getString("mothername"));
					vo1.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
					vo1.setMobileno(rs.getString("mobileno"));
					vo1.setAddress(rs.getString("address"));
					vo1.setRegNo(rs.getString("student_application_no"));
					vo1.setFinalperiodicmark("("+periodicmark+")");
					vo1.setFinalperiodicmark1("("+periodicmark1+")");
					vo1.setFinalnotebook("("+notebookmark+")");
					vo1.setFinalnotebook1("("+notebookmark1+")");
					vo1.setFinalsubenrichment("("+subenrichment+")");
					vo1.setFinalsubenrichment1("("+subenrichment1+")");
					vo1.setFinalyearlymark("("+yearlymark+")");
					vo1.setFinalyearlymark1("("+yearlymark1+")");

					String subjectid="";
					int maxperiodic=0,maxtheorymarks=0,maxnotebook=0,maxsubenrichment=0;
					int maxperiodic1=0,maxtheorymarks1=0,maxnotebook1=0,maxsubenrichment1=0;

					if(vo.getTerm2() != ""){
						ReportMenuVo grade=getGradePoints(studentid,vo,splitExamTypeId, dbdetails);

						if(grade.getWork_edu_grade() != null){
							vo1.setWork_edu_grade(grade.getWork_edu_grade());
						}else{
							vo1.setWork_edu_grade("");
						}
						if(grade.getArt_edu_grade() != null){
							vo1.setArt_edu_grade(grade.getArt_edu_grade());
						}else{
							vo1.setArt_edu_grade("");
						}
						if(grade.getHeath_edu_grade() != null){
							vo1.setHeath_edu_grade(grade.getHeath_edu_grade());
						}else{
							vo1.setHeath_edu_grade("");
						}
						if(grade.getDiscipline_grade() != null){
							vo1.setDiscipline_grade(grade.getDiscipline_grade());
						}else{
							vo1.setDiscipline_grade("");
						}
						if(grade.getRemarks() != null){
							vo1.setRemarks(grade.getRemarks());
						}else{
							vo1.setRemarks("");
						}
						//pstmt1 = conn.prepareStatement("SELECT csmd.sub_id FROM campus_studentwise_mark_details csmd WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.sub_id ");
						pstmt1 = conn.prepareStatement("SELECT csmd.sub_id,cem.`periodic_exam`,csub.`theory_marks`,csub.`practical_marks`,cem.notebook,cem.subenrichment FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_exam_max_marks_setup` cem ON cem.`class_id`=csmd.`classid` LEFT JOIN `campus_exam_max_marks_subwise` csub ON csub.`sub_id`=csmd.sub_id WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.sub_id ");
						pstmt1.setString(1, studentid);
						pstmt1.setString(2, vo.getAccYear());
						pstmt1.setString(3, vo.getLocationId());
						pstmt1.setString(4, vo.getClassId());
						rs1 = pstmt1.executeQuery();

						while(rs1.next())
						{
							subjectid=rs1.getString("sub_id");
							maxperiodic=rs1.getInt("periodic_exam");
							maxtheorymarks=rs1.getInt("theory_marks");
							maxnotebook=rs1.getInt("notebook");
							maxsubenrichment=rs1.getInt("subenrichment");
							pstmt2 = conn.prepareStatement("SELECT CAST(csmd.`max_periodic_marks` AS UNSIGNED) max_periodic_marks,CAST(csmd.scored_marks AS UNSIGNED) scored_marks,CAST(csmd.`periodic_test` AS UNSIGNED) periodic_test,ce.`exam_prefix`,sub.`subjectName`,sub.`subType`,CAST(csmd.notebook_marks AS UNSIGNED) notebook_marks,CAST(csmd.subject_enrich_marks AS UNSIGNED) subject_enrich_marks,CAST(csmd.total_marks AS UNSIGNED) total_marks,CAST(csmd.max_notebook_marks AS UNSIGNED) max_notebook_marks,CAST(csmd.max_subjenrich_marks AS UNSIGNED) max_subjenrich_marks,cscsa.`remarks`,cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`health_edu_grade`,cscsa.`discipline_grade` FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_subject` sub ON sub.`subjectID`=csmd.`sub_id` LEFT JOIN `campus_examination` cex ON csmd.exam_id=cex.examid LEFT JOIN `campus_examtype` ce ON cex.examtype=ce.examtypeid LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`exam_id`=cex.examid WHERE csmd.`sub_id`=? AND csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? AND ce.examtypeid=? and cex.`examid`=? GROUP BY csmd.`sub_id`");
							pstmt2.setString(1, subjectid);
							pstmt2.setString(2, studentid);
							pstmt2.setString(3, vo.getAccYear());
							pstmt2.setString(4, vo.getLocationId());
							pstmt2.setString(5, vo.getClassId());

							float pt=0,sm=0,nbm=0,sem=0;
							String subjectType="";
							ReportMenuVo vo2 = new ReportMenuVo();
							for(int s=0;s<splitExamTypeId.length;s++){

								pstmt2.setString(6, splitExamTypeId[s]);
								pstmt2.setString(7, splitTerm1Exam[s]);
								rs2 = pstmt2.executeQuery();

								if(rs2.next())
								{
									subjectType=rs2.getString("subType");
									/*if(rs2.getString("exam_prefix").equalsIgnoreCase("prdxm")){
										pt=((rs2.getFloat("periodic_test"))*10)/(rs2.getFloat("max_periodic_marks"));
									}
									else{
										sm=((rs2.getFloat("scored_marks"))*80)/(rs2.getFloat("total_marks"));
										nbm=((rs2.getFloat("notebook_marks"))*5)/(rs2.getFloat("max_notebook_marks"));
										sem=((rs2.getFloat("subject_enrich_marks"))*5)/(rs2.getFloat("max_subjenrich_marks"));
									}*/
									if(rs2.getString("exam_prefix").equalsIgnoreCase("prdxm")){
										float maxperiodicscoredmarks=((rs2.getFloat("periodic_test"))*maxperiodic)/(rs2.getFloat("max_periodic_marks"));
										pt=((maxperiodicscoredmarks)*periodicmark)/(maxperiodic);
									}
									else{
										float maxtotalscoredmarks=((rs2.getFloat("scored_marks"))*maxtheorymarks)/(rs2.getFloat("total_marks"));
										sm=((maxtotalscoredmarks)*yearlymark)/(maxtheorymarks);

										float maxnotescoredmarks=((rs2.getFloat("notebook_marks"))*maxnotebook)/(rs2.getFloat("max_notebook_marks"));
										nbm=((maxnotescoredmarks)*notebookmark)/(maxnotebook);

										float maxsubenrichscoredmarks=((rs2.getFloat("subject_enrich_marks"))*maxsubenrichment)/(rs2.getFloat("max_subjenrich_marks"));
										sem=((maxsubenrichscoredmarks)*subenrichment)/(maxsubenrichment);
									}
								}
							}

							vo2.setSubjectName(getSubjectName(subjectid,dbdetails));
							vo2.setPeriodictest(Float.toString((Math.round(pt))));
							vo2.setScored_marks(Float.toString(Math.round(sm)));
							vo2.setNotebook_marks(Float.toString(Math.round(nbm)));
							vo2.setSubject_enrich_marks(Float.toString(Math.round(sem)));
							vo2.setSubjectType(subjectType);
							if(vo2.getSubjectType().equalsIgnoreCase("major")){
								int obtainmark=(int)(Float.parseFloat(vo2.getScored_marks())+Float.parseFloat(vo2.getPeriodictest())+Float.parseFloat(vo2.getSubject_enrich_marks())+Float.parseFloat(vo2.getNotebook_marks()));
								vo2.setObtainedmarks(Integer.toString(obtainmark));
								String gradename=null;
								pstgrade = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=? AND accyear=? AND loc_id=?");
								pstgrade.setInt(1,obtainmark);
								pstgrade.setInt(2,obtainmark);
								pstgrade.setString(3, vo.getAccYear());
								pstgrade.setString(4, vo.getLocationId());

								rsgrade=pstgrade.executeQuery();

								while(rsgrade.next())
								{
									gradename=rsgrade.getString("grade_name");
								}
								vo2.setGrade(gradename);
								vo2.setMajorGrade(gradename);
							}else{
								int obtainmark=(int)(Float.parseFloat(vo2.getScored_marks())+Float.parseFloat(vo2.getPeriodictest())+Float.parseFloat(vo2.getSubject_enrich_marks())+Float.parseFloat(vo2.getNotebook_marks()));
								vo2.setObtainedmarks(Integer.toString(obtainmark));
								String gradename=null;
								pstgrade = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=? AND accyear=? AND loc_id=?");
								pstgrade.setInt(1,obtainmark);
								pstgrade.setInt(2,obtainmark);
								pstgrade.setString(3, vo.getAccYear());
								pstgrade.setString(4, vo.getLocationId());
								rsgrade=pstgrade.executeQuery();

								while(rsgrade.next())
								{
									gradename=rsgrade.getString("grade_name");
								}
								vo2.setGrade(gradename);
							}
							list1.add(vo2);
						}

						Collections.sort(list1,HelperClass.SubjectComparator);

						ReportMenuVo grade1=getGradePoints(studentid,vo,splitExamTypeIdTerm2, dbdetails);

						if(grade1.getWork_edu_grade() != null){
							vo1.setWork_edu_grade1(grade1.getWork_edu_grade());
						}else{
							vo1.setWork_edu_grade1("");
						}
						if(grade1.getArt_edu_grade() != null){
							vo1.setArt_edu_grade1(grade1.getArt_edu_grade());
						}else{
							vo1.setArt_edu_grade1("");
						}
						if(grade1.getHeath_edu_grade() != null){
							vo1.setHealth_edu_grade1(grade1.getHeath_edu_grade());
						}else{
							vo1.setHealth_edu_grade1("");
						}
						if(grade1.getDiscipline_grade() != null){
							vo1.setDiscipline_grade1(grade1.getDiscipline_grade());
						}else{
							vo1.setDiscipline_grade1("");
						}
						if(grade1.getRemarks() != null){
							vo1.setRemarks(grade1.getRemarks());
						}else{
							vo1.setRemarks("");
						}

						//pstmt3 = conn.prepareStatement("SELECT csmd.sub_id FROM campus_studentwise_mark_details csmd WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.sub_id ");
						pstmt3 = conn.prepareStatement("SELECT csmd.sub_id,cem.`periodic_exam`,csub.`theory_marks`,csub.`practical_marks`,cem.notebook,cem.subenrichment FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_exam_max_marks_setup` cem ON cem.`class_id`=csmd.`classid` LEFT JOIN `campus_exam_max_marks_subwise` csub ON csub.`sub_id`=csmd.sub_id WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.sub_id ");
						pstmt3.setString(1, studentid);
						pstmt3.setString(2, vo.getAccYear());
						pstmt3.setString(3, vo.getLocationId());
						pstmt3.setString(4, vo.getClassId());
						rs3 = pstmt3.executeQuery();

						while(rs3.next())
						{
							subjectid=rs3.getString("sub_id");
							maxperiodic1=rs3.getInt("periodic_exam");
							maxtheorymarks1=rs3.getInt("theory_marks");
							maxnotebook1=rs3.getInt("notebook");
							maxsubenrichment1=rs3.getInt("subenrichment");
							pstmt4 = conn.prepareStatement("SELECT CAST(csmd.`max_periodic_marks` AS UNSIGNED) max_periodic_marks,CAST(csmd.scored_marks AS UNSIGNED) scored_marks,CAST(csmd.`periodic_test` AS UNSIGNED) periodic_test,ce.`exam_prefix`,sub.`subjectName`,sub.`subType`,CAST(csmd.notebook_marks AS UNSIGNED) notebook_marks,CAST(csmd.subject_enrich_marks AS UNSIGNED) subject_enrich_marks,CAST(csmd.total_marks AS UNSIGNED) total_marks,CAST(csmd.max_notebook_marks AS UNSIGNED) max_notebook_marks,CAST(csmd.max_subjenrich_marks AS UNSIGNED) max_subjenrich_marks,cscsa.`remarks`,cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`health_edu_grade`,cscsa.`discipline_grade` FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_subject` sub ON sub.`subjectID`=csmd.`sub_id` LEFT JOIN `campus_examination` cex ON csmd.exam_id=cex.examid LEFT JOIN `campus_examtype` ce ON cex.examtype=ce.examtypeid LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`exam_id`=cex.examid WHERE csmd.`sub_id`=? AND csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? AND ce.examtypeid=? and cex.`examid`=?");
							pstmt4.setString(1, subjectid);
							pstmt4.setString(2, studentid);
							pstmt4.setString(3, vo.getAccYear());
							pstmt4.setString(4, vo.getLocationId());
							pstmt4.setString(5, vo.getClassId());

							float pt=0,sm=0,nbm=0,sem=0;
							String subjectType="";
							ReportMenuVo vo3 = new ReportMenuVo();
							for(int s=0;s<splitExamTypeIdTerm2.length;s++){
								pstmt4.setString(6, splitExamTypeIdTerm2[s]);
								pstmt4.setString(7, splitTerm2Exam[s]);
								rs4 = pstmt4.executeQuery();

								if(rs4.next())
								{
									subjectType=rs4.getString("subType");
									/*if(rs4.getString("exam_prefix").equalsIgnoreCase("prdxm")){
										pt=((rs4.getFloat("periodic_test"))*10)/(rs4.getFloat("max_periodic_marks"));
									}
									else{
										sm=((rs4.getFloat("scored_marks"))*80)/(rs4.getFloat("total_marks"));
										nbm=((rs4.getFloat("notebook_marks"))*5)/(rs4.getFloat("max_notebook_marks"));
										sem=((rs4.getFloat("subject_enrich_marks"))*5)/(rs4.getFloat("max_subjenrich_marks"));
									}*/
									
									if(rs4.getString("exam_prefix").equalsIgnoreCase("prdxm")){
										float maxperiodicscoredmarks=((rs4.getFloat("periodic_test"))*maxperiodic1)/(rs4.getFloat("max_periodic_marks"));
										pt=((maxperiodicscoredmarks)*periodicmark)/(maxperiodic1);
									}
									else{
										float maxtotalscoredmarks=((rs4.getFloat("scored_marks"))*maxtheorymarks1)/(rs4.getFloat("total_marks"));
										sm=((maxtotalscoredmarks)*yearlymark)/(maxtheorymarks1);

										float maxnotescoredmarks=((rs4.getFloat("notebook_marks"))*maxnotebook1)/(rs4.getFloat("max_notebook_marks"));
										nbm=((maxnotescoredmarks)*notebookmark)/(maxnotebook1);

										float maxsubenrichscoredmarks=((rs4.getFloat("subject_enrich_marks"))*maxsubenrichment1)/(rs4.getFloat("max_subjenrich_marks"));
										sem=((maxsubenrichscoredmarks)*subenrichment)/(maxsubenrichment1);
									}
								}
							}

							vo3.setSubjectName(getSubjectName(subjectid,dbdetails));

							vo3.setPeriodictest(Float.toString((Math.round(pt))));
							vo3.setScored_marks(Float.toString(Math.round(sm)));
							vo3.setNotebook_marks(Float.toString(Math.round(nbm)));
							vo3.setSubject_enrich_marks(Float.toString(Math.round(sem)));
							vo3.setSubjectType(subjectType);
							if(vo3.getSubjectType().equalsIgnoreCase("major")){
								int obtainmark=(int)(Float.parseFloat(vo3.getScored_marks())+Float.parseFloat(vo3.getPeriodictest())+Float.parseFloat(vo3.getSubject_enrich_marks())+Float.parseFloat(vo3.getNotebook_marks()));
								vo3.setObtainedmarks(Integer.toString(obtainmark));
								String gradename=null;
								pstgrade1 = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
								pstgrade1.setInt(1,obtainmark);
								pstgrade1.setInt(2,obtainmark);
								rsgrade1=pstgrade1.executeQuery();
								while(rsgrade1.next())
								{
									gradename=rsgrade1.getString("grade_name");
								}
								vo3.setGrade(gradename);
								vo3.setMajorGrade(gradename);
							}else{
								int obtainmark=(int)(Float.parseFloat(vo3.getScored_marks())+Float.parseFloat(vo3.getPeriodictest())+Float.parseFloat(vo3.getSubject_enrich_marks())+Float.parseFloat(vo3.getNotebook_marks()));
								vo3.setObtainedmarks(Integer.toString(obtainmark));
								String gradename=null;
								pstgrade1 = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
								pstgrade1.setInt(1,obtainmark);
								pstgrade1.setInt(2,obtainmark);
								rsgrade1=pstgrade1.executeQuery();
								while(rsgrade1.next())
								{
									gradename=rsgrade1.getString("grade_name");
								}
								vo3.setGrade(gradename);
							}
							list2.add(vo3);
						}

						int ecount=0;
						for(ReportMenuVo li : list1) {
							if(li.getMajorGrade() !=null && li.getMajorGrade().equalsIgnoreCase("E")){
								ecount++;
							}
							if(ecount > 1){
								vo1.setGrade1("Demoted");
								break;
							}else{
								vo1.setGrade1("Promoted");
							}
						}

						int ecount1=0;
						for(ReportMenuVo s : list2) {
							if(s.getMajorGrade() !=null && s.getMajorGrade().equalsIgnoreCase("E")){
								ecount1++;
							}
							if(ecount1 > 1){
								vo1.setGrade2("Demoted");
								break;
							}else{
								vo1.setGrade2("Promoted");
							}
						}
						if((vo1.getGrade1() == "Promoted") && (vo1.getGrade2() == "Promoted") ){
							vo1.setResult("Passed");
						}else{
							vo1.setResult("Failed");
						}
						Collections.sort(list2,HelperClass.SubjectComparator);
						vo1.setExamMarkList(list1);
						vo1.setExamMarkList1(list2);
					}else{
						ReportMenuVo grade=getGradePoints(studentid,vo,splitExamTypeId, dbdetails);
						if(grade.getWork_edu_grade() != null){
							vo1.setWork_edu_grade(grade.getWork_edu_grade());
						}else{
							vo1.setWork_edu_grade("");
						}
						if(grade.getArt_edu_grade() != null){
							vo1.setArt_edu_grade(grade.getArt_edu_grade());
						}else{
							vo1.setArt_edu_grade("");
						}
						if(grade.getHeath_edu_grade() != null){
							vo1.setHeath_edu_grade(grade.getHeath_edu_grade());
						}else{
							vo1.setHeath_edu_grade("");
						}
						if(grade.getDiscipline_grade() != null){
							vo1.setDiscipline_grade(grade.getDiscipline_grade());
						}else{
							vo1.setDiscipline_grade("");
						}
						if(grade.getRemarks() != null){
							vo1.setRemarks(grade.getRemarks());
						}else{
							vo1.setRemarks("");
						}
						
						//pstmt1 = conn.prepareStatement("SELECT csmd.sub_id FROM campus_studentwise_mark_details csmd WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.sub_id ");
						pstmt1 = conn.prepareStatement("SELECT csmd.sub_id,cem.`periodic_exam`,csub.`theory_marks`,csub.`practical_marks`,cem.notebook,cem.subenrichment FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_exam_max_marks_setup` cem ON cem.`class_id`=csmd.`classid` LEFT JOIN `campus_exam_max_marks_subwise` csub ON csub.`sub_id`=csmd.sub_id WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.sub_id ");
						pstmt1.setString(1, studentid);
						pstmt1.setString(2, vo.getAccYear());
						pstmt1.setString(3, vo.getLocationId());
						pstmt1.setString(4, vo.getClassId());
						rs1 = pstmt1.executeQuery();
						while(rs1.next())
						{
							subjectid=rs1.getString("sub_id");
							maxperiodic=rs1.getInt("periodic_exam");
							maxtheorymarks=rs1.getInt("theory_marks");
							maxnotebook=rs1.getInt("notebook");
							maxsubenrichment=rs1.getInt("subenrichment");
							pstmt2 = conn.prepareStatement("SELECT CAST(csmd.`max_periodic_marks` AS UNSIGNED) max_periodic_marks,CAST(csmd.scored_marks AS UNSIGNED) scored_marks,CAST(csmd.`periodic_test` AS UNSIGNED) periodic_test,ce.`exam_prefix`,sub.`subjectName`,sub.`subType`,CAST(csmd.notebook_marks AS UNSIGNED) notebook_marks,CAST(csmd.subject_enrich_marks AS UNSIGNED) subject_enrich_marks,CAST(csmd.total_marks AS UNSIGNED) total_marks,CAST(csmd.max_notebook_marks AS UNSIGNED) max_notebook_marks,CAST(csmd.max_subjenrich_marks AS UNSIGNED) max_subjenrich_marks FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_subject` sub ON sub.`subjectID`=csmd.`sub_id` LEFT JOIN `campus_examination` cex ON csmd.exam_id=cex.examid LEFT JOIN `campus_examtype` ce ON cex.examtype=ce.examtypeid WHERE csmd.`sub_id`=? AND csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? AND ce.examtypeid=? and cex.`examid`=? GROUP BY csmd.`sub_id`");
							pstmt2.setString(1, subjectid);
							pstmt2.setString(2, studentid);
							pstmt2.setString(3, vo.getAccYear());
							pstmt2.setString(4, vo.getLocationId());
							pstmt2.setString(5, vo.getClassId());

							float pt=0,sm=0,nbm=0,sem=0;
							String subjectType="";
							ReportMenuVo vo2 = new ReportMenuVo();
							for(int s=0;s<splitExamTypeId.length;s++){
								pstmt2.setString(6, splitExamTypeId[s]);
								pstmt2.setString(7, splitTerm1Exam[s]);
								rs2 = pstmt2.executeQuery();
								if(rs2.next())
								{
									subjectType=rs2.getString("subType");
									if(rs2.getString("exam_prefix").equalsIgnoreCase("prdxm")){
										float maxperiodicscoredmarks=((rs2.getFloat("periodic_test"))*maxperiodic)/(rs2.getFloat("max_periodic_marks"));
										pt=((maxperiodicscoredmarks)*periodicmark)/(maxperiodic);
									}
									else{
										float maxtotalscoredmarks=((rs2.getFloat("scored_marks"))*maxtheorymarks)/(rs2.getFloat("total_marks"));
										sm=((maxtotalscoredmarks)*yearlymark)/(maxtheorymarks);

										float maxnotescoredmarks=((rs2.getFloat("notebook_marks"))*maxnotebook)/(rs2.getFloat("max_notebook_marks"));
										nbm=((maxnotescoredmarks)*notebookmark)/(maxnotebook);

										float maxsubenrichscoredmarks=((rs2.getFloat("subject_enrich_marks"))*maxsubenrichment)/(rs2.getFloat("max_subjenrich_marks"));
										sem=((maxsubenrichscoredmarks)*subenrichment)/(maxsubenrichment);
									}
								}
							}

							vo2.setSubjectName(getSubjectName(subjectid,dbdetails));

							vo2.setPeriodictest(Float.toString((Math.round(pt))));
							vo2.setScored_marks(Float.toString(Math.round(sm)));
							vo2.setNotebook_marks(Float.toString(Math.round(nbm)));
							vo2.setSubject_enrich_marks(Float.toString(Math.round(sem)));
							vo2.setSubjectType(subjectType);
							if(vo2.getSubjectType().equalsIgnoreCase("major")){
								int obtainmark=(int)(Float.parseFloat(vo2.getScored_marks())+Float.parseFloat(vo2.getPeriodictest())+Float.parseFloat(vo2.getSubject_enrich_marks())+Float.parseFloat(vo2.getNotebook_marks()));
								vo2.setObtainedmarks(Integer.toString(obtainmark));
								String gradename=null;
								pstgrade = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
								pstgrade.setInt(1,obtainmark);
								pstgrade.setInt(2,obtainmark);
								rsgrade=pstgrade.executeQuery();

								while(rsgrade.next())
								{
									gradename=rsgrade.getString("grade_name");
								}
								vo2.setGrade(gradename);
								vo2.setMajorGrade(gradename);
							}else{
								int obtainmark=(int)(Float.parseFloat(vo2.getScored_marks())+Float.parseFloat(vo2.getPeriodictest())+Float.parseFloat(vo2.getSubject_enrich_marks())+Float.parseFloat(vo2.getNotebook_marks()));
								vo2.setObtainedmarks(Integer.toString(obtainmark));
								String gradename=null;

								pstgrade = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
								pstgrade.setInt(1,obtainmark);
								pstgrade.setInt(2,obtainmark);
								rsgrade=pstgrade.executeQuery();

								while(rsgrade.next())
								{
									gradename=rsgrade.getString("grade_name");
								}
								vo2.setGrade(gradename);
							}

							list1.add(vo2);
						}
						int ecount=0;
						for(ReportMenuVo s : list1) {
							if(s.getMajorGrade() !=null && s.getMajorGrade().equalsIgnoreCase("E")){
								ecount++;
							}
							if(ecount > 1){
								vo1.setResult("Failed");
							}else{
								vo1.setResult("Passed");
							}
						}
						Collections.sort(list1,HelperClass.SubjectComparator);
						vo1.setExamMarkList(list1);
					}

					list.add(vo1);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rsgrade1 != null&& (!rsgrade1.isClosed())) {
					rsgrade1.close();
				}
				if (rsgrade != null&& (!rsgrade.isClosed())) {
					rsgrade.close();
				}
				if (rs4 != null&& (!rs4.isClosed())) {
					rs4.close();
				}
				if (rs3 != null&& (!rs3.isClosed())) {
					rs3.close();
				}
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstgrade1 != null&& (!pstgrade1.isClosed())) {
					pstgrade1.close();
				}
				if (pstgrade != null&& (!pstgrade.isClosed())) {
					pstgrade.close();
				}
				if (pstmt4 != null&& (!pstmt4.isClosed())) {
					pstmt4.close();
				}
				if (pstmt3 != null&& (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return list;
	}
	private ReportMenuVo getGradePoints(String studentid, ReportMenuVo vo, String[] splitExamTypeId,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getStudentClassSectionWiseListForReport : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ReportMenuVo vo1=new ReportMenuVo();
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CO_SHCOLASTIC_GRADES);
			for(int s=0;s<splitExamTypeId.length;s++){
				String examtypeId=splitExamTypeId[s];
				if(examtypeId.equals("EMT2") || examtypeId.equals("EMT3")){
					pstmt.setString(1, studentid);
					pstmt.setString(2, vo.getAccYear());
					pstmt.setString(3, vo.getLocationId());
					pstmt.setString(4, vo.getClassId());
					pstmt.setString(5, vo.getSectionId());
					pstmt.setString(6, examtypeId);
					rs=pstmt.executeQuery();
					while(rs.next()){
						vo1.setRemarks(rs.getString("remarks"));
						vo1.setWork_edu_grade(rs.getString("work_edu_grade"));
						vo1.setArt_edu_grade(rs.getString("art_edu_grade"));
						vo1.setHeath_edu_grade(rs.getString("health_edu_grade"));
						vo1.setDiscipline_grade(rs.getString("discipline_grade"));
					}
				}
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getStudentClassSectionWiseListForReport : Ending");
		return vo1;
	}
	private String getSubjectName(String subjectid,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null,pstmt1 = null;
		ResultSet rs = null,rs1 = null;
		Connection conn = null;
		String subjectname=null;
		
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pstmt = conn.prepareStatement("SELECT `subjectName` FROM `campus_subject` WHERE `subjectID`=?");
			pstmt.setString(1,subjectid);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				subjectname=rs.getString("subjectName");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return subjectname;

	
	}

	public List<ReportMenuVo> getAcademicYearWiseReportCard(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getTerm Starting");
		
		PreparedStatement pstmt = null,pstmt1 = null,pstgrade=null,pstmt2=null;
		ResultSet rs = null,rs1 = null,rsgrade=null,rs2=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		
		
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			String term1=vo.getTermname();
			String examtypeId=vo.getExamtypeid();
			String[] arrStringVal=term1.split(",");
			String[] splitExamTypeId=examtypeId.split(",");
			String[] stuid=vo.getStudentId().split(",");
			for(int i=0; i<stuid.length; i++){
			if(vo.getStudentId() != "" && !vo.getSectionId().equalsIgnoreCase("all")){
				pstmt=conn.prepareStatement("SELECT DISTINCT csmd.`stu_id`,acc.`acadamic_year`,ccs.`classdetails_name_var`,CONCAT(ccs.`classdetails_name_var`,'/',sec.`classsection_name_var`) AS classsection,csc.`student_rollno`,stu.student_application_no,stu.`student_admissionno_var`,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentname,par.`FatherName` AS fathername,par.`student_mothername_var` AS mothername,stu.`student_dob_var`,par.`mobileno`,par.`address` FROM `campus_studentwise_mark_details` csmd LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=csmd.`stu_id` AND csc.`fms_acadamicyear_id_int`=csmd.`Academic_yearid` AND csc.`locationId`=csmd.`location_id` LEFT JOIN `campus_acadamicyear` acc ON acc.`acadamic_id`=csmd.`Academic_yearid` LEFT JOIN `campus_classdetail` ccs ON ccs.`classdetail_id_int`=csmd.`classid` LEFT JOIN `campus_classsection` sec ON sec.`classsection_id_int`=csmd.`sectionid` AND sec.`classdetail_id_int`=ccs.`classdetail_id_int` LEFT JOIN `campus_student` stu ON stu.`student_id_int`=csc.`student_id_int` LEFT JOIN `campus_parentchildrelation` parc ON parc.`stu_addmissionNo`=csmd.`stu_id` LEFT JOIN `campus_parents` par ON par.`ParentID`=parc.`parentid` WHERE csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? AND csmd.sectionid=? AND csmd.`stu_id`=? GROUP BY csmd.`stu_id`");
				pstmt.setString(1, vo.getAccYear());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getClassId());
				pstmt.setString(4, vo.getSectionId());
				pstmt.setString(5, stuid[i]);
			}else if(vo.getStudentId() != "" && vo.getSectionId().equalsIgnoreCase("all")){
				pstmt=conn.prepareStatement("SELECT DISTINCT csmd.`stu_id`,acc.`acadamic_year`,ccs.`classdetails_name_var`,CONCAT(ccs.`classdetails_name_var`,'/',sec.`classsection_name_var`) AS classsection,csc.`student_rollno`,stu.student_application_no,stu.`student_admissionno_var`,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentname,par.`FatherName` AS fathername,par.`student_mothername_var` AS mothername,stu.`student_dob_var`,par.`mobileno`,par.`address` FROM `campus_studentwise_mark_details` csmd LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=csmd.`stu_id` AND csc.`fms_acadamicyear_id_int`=csmd.`Academic_yearid` AND csc.`locationId`=csmd.`location_id` LEFT JOIN `campus_acadamicyear` acc ON acc.`acadamic_id`=csmd.`Academic_yearid` LEFT JOIN `campus_classdetail` ccs ON ccs.`classdetail_id_int`=csmd.`classid` LEFT JOIN `campus_classsection` sec ON sec.`classsection_id_int`=csmd.`sectionid` AND sec.`classdetail_id_int`=ccs.`classdetail_id_int` LEFT JOIN `campus_student` stu ON stu.`student_id_int`=csc.`student_id_int` LEFT JOIN `campus_parentchildrelation` parc ON parc.`stu_addmissionNo`=csmd.`stu_id` LEFT JOIN `campus_parents` par ON par.`ParentID`=parc.`parentid` WHERE csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? and csmd.`stu_id`=? GROUP BY csmd.`stu_id`");
				pstmt.setString(1, vo.getAccYear());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getClassId());
				pstmt.setString(4, stuid[i]);
			}
			rs = pstmt.executeQuery();
			while(rs.next()){

				ArrayList<ReportMenuVo> list1 = new ArrayList<ReportMenuVo>();				
				ReportMenuVo vo1=new ReportMenuVo();
				String studentid=rs.getString("stu_id");
				vo1.setAccyearname(rs.getString("acadamic_year"));
				vo1.setClassname(rs.getString("classdetails_name_var"));
				vo1.setClass_and_section(rs.getString("classsection")+"/"+rs.getString("student_rollno"));
				vo1.setStudentRollNo(rs.getString("student_rollno"));
				vo1.setAdmissionNo(rs.getString("student_admissionno_var"));
				vo1.setStudentnamelabel(rs.getString("studentname"));
				vo1.setFatherName(rs.getString("fathername"));
				vo1.setMotherName(rs.getString("mothername"));
				vo1.setDob(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				vo1.setMobileno(rs.getString("mobileno"));
				vo1.setAddress(rs.getString("address"));
				vo1.setWork_edu_grade(rs.getString("work_edu_grade"));
				vo1.setArt_edu_grade(rs.getString("art_edu_grade"));
				vo1.setHeath_edu_grade(rs.getString("health_edu_grade"));
				vo1.setDiscipline_grade(rs.getString("discipline_grade"));
				vo1.setRegNo(rs.getString("student_application_no"));
				if(rs.getString("remarks") != null){
					vo1.setRemarks(rs.getString("remarks"));
				}else{
					vo1.setRemarks("");
				}

				String subjectid="";
				//pstmt1 = conn.prepareStatement("SELECT csmd.sub_id FROM campus_studentwise_mark_details csmd WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.sub_id ");
				pstmt1 = conn.prepareStatement("SELECT csmd.sub_id FROM campus_studentwise_mark_details csmd WHERE csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? GROUP BY csmd.sub_id ");
				pstmt1.setString(1, studentid);
				pstmt1.setString(2, vo.getAccYear());
				pstmt1.setString(3, vo.getLocationId());
				pstmt1.setString(4, vo.getClassId());
				rs1 = pstmt1.executeQuery();

				while(rs1.next())
				{
					subjectid=rs1.getString("sub_id");
					pstmt2 = conn.prepareStatement("SELECT exam_prefix,subjectName,subType,SUM(periodic_test) as periodic_test,SUM(scored_marks) AS scored_marks,SUM(`notebook_marks`) AS notebookmarks,SUM(`subject_enrich_marks`) AS subject_enrich_marks,SUM(`total_marks`) AS total_marks,SUM(`max_notebook_marks`) AS max_notebook_marks,SUM(`max_subjenrich_marks`) AS max_subjenrich_marks,SUM(`max_periodic_marks`) AS max_periodic_marks  FROM (SELECT CAST(csmd.`max_periodic_marks` AS UNSIGNED) max_periodic_marks,CAST(csmd.scored_marks AS UNSIGNED) scored_marks,CAST(csmd.`periodic_test` AS UNSIGNED) periodic_test,ce.`exam_prefix`,sub.`subjectName`,sub.`subType`,CAST(csmd.notebook_marks AS UNSIGNED) notebook_marks,CAST(csmd.subject_enrich_marks AS UNSIGNED) subject_enrich_marks,CAST(csmd.total_marks AS UNSIGNED) total_marks,CAST(csmd.max_notebook_marks AS UNSIGNED) max_notebook_marks,CAST(csmd.max_subjenrich_marks AS UNSIGNED) max_subjenrich_marks FROM campus_studentwise_mark_details csmd LEFT JOIN `campus_subject` sub ON sub.`subjectID`=csmd.`sub_id` LEFT JOIN `campus_examination` cex ON csmd.exam_id=cex.examid LEFT JOIN `campus_examtype` ce ON cex.examtype=ce.examtypeid WHERE csmd.`sub_id`=? AND csmd.stu_id=? AND csmd.`Academic_yearid`=? AND csmd.`location_id`=? AND csmd.`classid`=? AND ce.examtypeid=? ORDER BY CAST(csmd.`periodic_test` AS UNSIGNED) DESC LIMIT 2) AS subt;");
					pstmt2.setString(1, subjectid);
					pstmt2.setString(2, studentid);
					pstmt2.setString(3, vo.getAccYear());
					pstmt2.setString(4, vo.getLocationId());
					pstmt2.setString(5, vo.getClassId());

					float pt=0,sm=0,nbm=0,sem=0;
					String subjectType="";
					ReportMenuVo vo2 = new ReportMenuVo();
					for(int s=0;s<splitExamTypeId.length;s++){

						pstmt2.setString(6, splitExamTypeId[s]);
						rs2 = pstmt2.executeQuery();

						if(rs2.next())
						{
							subjectType=rs2.getString("subType");
							if(rs2.getString("exam_prefix").equalsIgnoreCase("prdxm")){
								pt=((rs2.getFloat("periodic_test")/2)*10)/(rs2.getFloat("max_periodic_marks")/2);
							}
							else{
								sm=((rs2.getFloat("scored_marks"))*80)/(rs2.getFloat("total_marks"));
								nbm=((rs2.getFloat("notebookmarks"))*5)/(rs2.getFloat("max_notebook_marks"));
								sem=((rs2.getFloat("subject_enrich_marks"))*5)/(rs2.getFloat("max_subjenrich_marks"));
							}
						}
					}
					vo2.setSubjectName(getSubjectName(subjectid,userLoggingsVo));
					vo2.setScored_marks(Float.toString(Math.round(sm)));
					vo2.setNotebook_marks(Float.toString(Math.round(nbm)));
					vo2.setSubject_enrich_marks(Float.toString(Math.round(sem)));
					vo2.setSubjectType(subjectType);
					if(vo2.getSubjectType().equalsIgnoreCase("major")){
						int obtainmark=(int)(Float.parseFloat(vo2.getScored_marks())+Float.parseFloat(vo2.getPeriodictest())+Float.parseFloat(vo2.getSubject_enrich_marks())+Float.parseFloat(vo2.getNotebook_marks()));
						vo2.setObtainedmarks(Integer.toString(obtainmark));
						String gradename=null;

						pstgrade = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
						pstgrade.setInt(1,(int)(obtainmark));
						pstgrade.setInt(2,(int)(obtainmark));
						rsgrade=pstgrade.executeQuery();
						while(rsgrade.next())
						{
							gradename=rsgrade.getString("grade_name");
						}
						vo2.setGrade(gradename);
						vo2.setMajorGrade(gradename);
					}else{
						int obtainmark=(int)(Float.parseFloat(vo2.getScored_marks())+Float.parseFloat(vo2.getPeriodictest())+Float.parseFloat(vo2.getSubject_enrich_marks())+Float.parseFloat(vo2.getNotebook_marks()));
						vo2.setObtainedmarks(Integer.toString(obtainmark));
						String gradename=null;

						pstgrade = conn.prepareStatement("SELECT grade_name FROM campus_exam_gradesettings WHERE min_marks<=? AND max_marks>=?");
						pstgrade.setInt(1,(int)(obtainmark));
						pstgrade.setInt(2,(int)(obtainmark));
						rsgrade=pstgrade.executeQuery();
						while(rsgrade.next())
						{
							gradename=rsgrade.getString("grade_name");
						}

						vo2.setGrade(gradename);
					}
					list1.add(vo2);
				}
				int ecount=0;
				for(ReportMenuVo s : list1) {
					if(s.getMajorGrade() !=null && s.getMajorGrade().equalsIgnoreCase("E")){
						ecount++;
					}
					if(ecount > 1){
						vo1.setResult("Failed");
					}else{
						vo1.setResult("Passed");
					}
				}
				Collections.sort(list1,HelperClass.SubjectComparator);
				vo1.setExamMarkList(list1);
				list.add(vo1);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rsgrade != null&& (!rsgrade.isClosed())) {
					rsgrade.close();
				}
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstgrade != null&& (!pstgrade.isClosed())) {
					pstgrade.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getTerm Ending");
		return list;

	}

	public ArrayList<ReportMenuVo> getClassByLocation(String locId, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getClassByLocation : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> classList=new ArrayList<ReportMenuVo>();
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			pstmt = conn.prepareStatement("SELECT ccd.classdetail_id_int,ccd.classdetails_name_var FROM campus_classdetail ccd join campus_classstream ccs on ccs.classstream_id_int=ccd.classstream_id_int join campus_location cl on cl.Location_Id=ccs.locationId WHERE ccd.locationId=? AND cl.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y' ORDER BY LENGTH (ccd.classdetail_id_int),ccd.classdetail_id_int ASC");
			pstmt.setString(1, locId);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				ReportMenuVo vo=new ReportMenuVo();
				
				vo.setClassId(rs.getString("classdetail_id_int"));
				vo.setClassname(rs.getString("classdetails_name_var"));
				
				classList.add(vo);
				
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getClassByLocation : Ending");
		
		return classList;
	}

	
	
	public ArrayList<ReportMenuVo> getSpecByClassLoc(String classId, String locId, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getSpecByClassLoc : Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<ReportMenuVo> specList=new ArrayList<ReportMenuVo>();
	 	
		try {
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			pstmt = conn.prepareStatement("SELECT `Specialization_Id`,`Specialization_name` FROM `campus_class_specialization`  WHERE `isActive`='Y' and `locationId`=? AND `ClassDetails_Id`=?");
			pstmt.setString(1, locId);
			pstmt.setString(2, classId);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				ReportMenuVo vo=new ReportMenuVo();
				
				vo.setSpecializationId(rs.getString("Specialization_Id"));
				vo.setSpecializationName(rs.getString("Specialization_name"));
				
				
				if(vo.getSpecializationName().equalsIgnoreCase("") || vo.getSpecializationName().equalsIgnoreCase(null)){
					vo.setSpecializationName("Not Found");
				}
				
				specList.add(vo);
				
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getSpecByClassLoc : Ending");
		
		return specList;
	}

	public ArrayList<ReportMenuVo> getstudentsHouseWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: getstudentsHouseWise : Starting");
		PreparedStatement pstmt= null,pstmt1= null,pstmt2= null,pstmt3= null,pstmt4= null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null;
		Connection conn = null;
		int count1 = 0;
		int count = 0;
		int housecountcount = 0;
		ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		try{
				conn= JDBCConnection.getSeparateConnection(custdetails);
				
				String mquery="SELECT c.`classdetail_id_int`,c.`classdetails_name_var`,s.`classsection_id_int`,s.`classsection_name_var` FROM `campus_classdetail` c JOIN `campus_classsection` s ON c.`classdetail_id_int`=s.`classdetail_id_int` AND c.`locationId`=s.`locationId` WHERE ";
				
				HashMap map = new HashMap();
				Vector vec = new Vector();
				String key = null;
				String val = null;
				String wheresql = null;
						
				if(!vo.getLocationId().equalsIgnoreCase("%%")) {
					map.put("c.locationId",vo.getLocationId());
					vec.add("c.locationId");
				}
				/*if(!accyear.equalsIgnoreCase("%%") ) {
					map.put("csc.fms_acadamicyear_id_int",accyear);
					vec.add("csc.fms_acadamicyear_id_int");
				}*/
				if(!vo.getClassId().equalsIgnoreCase("%%")) {
					map.put("c.classdetail_id_int",vo.getClassId());
					vec.add("c.classdetail_id_int");
				}
				if(!vo.getSectionId().equalsIgnoreCase("%%")) {
					map.put("s.classsection_id_int",vo.getSectionId());
					vec.add("s.classsection_id_int");
				}
				
				Enumeration<String> e = vec.elements();

				while ( e.hasMoreElements() ) {
					key = e.nextElement().toString();
					val = map.get(key).toString();

					if(count == 0) {
						wheresql= key+" = '"+val+"'";
						count++;
					}else {
						wheresql = wheresql+" and "+key+"='"+val+"'";
					}
				}
				String query=mquery+" "+wheresql+"  AND c.`isActive`='Y' AND s.`isActive`='Y' ORDER BY CAST(SUBSTRING(c.`classdetail_id_int`,4,LENGTH(c.`classdetail_id_int`)-3) AS SIGNED),s.`classsection_name_var`";
				System.out.println("QUERY "+query);
				pstmt2=conn.prepareStatement(query);
				//pstmt2.setString(1, vo.getLocationId());
				rs2=pstmt2.executeQuery();
				while(rs2.next()){
					ReportMenuVo obj1 = new ReportMenuVo();
					count1++;
					obj1.setHousecount(count1);
					obj1.setClass_and_section(rs2.getString("classdetails_name_var")+"-"+rs2.getString("classsection_name_var"));
					pstmt4=conn.prepareStatement("SELECT COUNT(*) FROM `campus_student_classdetails` WHERE `fms_acadamicyear_id_int`=? AND `locationId`=? AND `classdetail_id_int`=? AND `classsection_id_int`=? and  `student_status` = 'STUDYING'");
					pstmt4.setString(1, vo.getAccyearId());
					pstmt4.setString(2, vo.getLocationId());
					pstmt4.setString(3, rs2.getString("classdetail_id_int"));
					pstmt4.setString(4, rs2.getString("classsection_id_int"));
					rs4=pstmt4.executeQuery();
					while(rs4.next()){
						obj1.setCount(rs4.getInt(1));
					}
					ArrayList<ReportMenuVo> list1 = new ArrayList<ReportMenuVo>();
					if(list1.size()==0){
						pstmt=conn.prepareStatement("SELECT `house_id`,`housename` FROM `campus_house_settings` WHERE `accyear_id`=? AND `loc_id`=? AND `status`='active' ORDER BY housename");
						pstmt.setString(1, vo.getAccyearId());
						pstmt.setString(2, vo.getLocationId());
						/*pstmt.setString(3, vo.getClassId());
						pstmt.setString(4, vo.getSectionId());
						pstmt.setString(5, vo.getStatus());*/
						rs=pstmt.executeQuery();
						while(rs.next()){
							ReportMenuVo obj = new ReportMenuVo();
							//obj.setHousecount(housecountcount);
							obj.setHouseName(rs.getString("housename"));
							pstmt1=conn.prepareStatement("SELECT COUNT(*) FROM `campus_student_classdetails` WHERE `fms_acadamicyear_id_int`=? AND `locationId`=? AND `student_house` =? AND `classdetail_id_int`=? AND `classsection_id_int`=? and `student_status` = 'STUDYING'");
							pstmt1.setString(1, vo.getAccyearId());
							pstmt1.setString(2, vo.getLocationId());
							pstmt1.setString(3, rs.getString("house_id"));
							pstmt1.setString(4, rs2.getString("classdetail_id_int"));
							pstmt1.setString(5, rs2.getString("classsection_id_int"));
							//System.out.println("ST COUNT "+pstmt1);
							rs1=pstmt1.executeQuery();
							while(rs1.next()){
								obj.setStudentcount(Integer.toString(rs1.getInt(1)));
							}
							list1.add(obj);
						}
				}
				pstmt3=conn.prepareStatement("SELECT COUNT(*) FROM `campus_student_classdetails` WHERE `fms_acadamicyear_id_int`=? AND `locationId`=? AND  `classdetail_id_int`=? AND `classsection_id_int`=? AND (`student_house` ='' OR student_house IS NULL) and `student_status` = 'STUDYING'");
				pstmt3.setString(1, vo.getAccyearId());
				pstmt3.setString(2, vo.getLocationId());
				pstmt3.setString(3, rs2.getString("classdetail_id_int"));
				pstmt3.setString(4, rs2.getString("classsection_id_int"));
				rs3=pstmt3.executeQuery();
				while(rs3.next()){
					obj1.setNoStudentcount(rs3.getString(1));
				}
				obj1.setExamMarkList(list1);
				list.add(obj1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs3 != null&& (!rs3.isClosed())) {
					rs3.close();
				}
				if (pstmt3 != null&& (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl : getstudentsHouseWise : Ending");
		return list;
	}

public String getDefaulterStudentsCount(UserLoggingsPojo userLoggingsVo) {
		
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl: getDefaulterStudentsCount : Starting");
	
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Connection conn = null;
	String result = "";
	ArrayList<ReportMenuVo> classList=new ArrayList<ReportMenuVo>();
 	
	try {
		
		conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
		pstmt = conn.prepareStatement("SELECT COUNT(*) as total FROM campus_fee_master fm,campus_fee_setup fs,campus_fee_setupdetails fsd WHERE fs.feeSettingcode=fsd.feeSettingCode AND fm.FeeCode=fsd.feecode ");
		rs=pstmt.executeQuery();
		
		while(rs.next()){
			result = rs.getString("total");
		}
	
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e1) {
		logger.error(e1.getMessage(), e1);
		e1.printStackTrace();
	} finally {
		try {
			if (rs != null&& (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null&& (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl : getDefaulterStudentsCount : Ending");
	
	return result;		
		
		
	}

public String getCollectionCount(UserLoggingsPojo userLoggingsVo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl: getCollectionCount : Starting");
	
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Connection conn = null;
	String result = "";
 	
	try {
		
		conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
		pstmt = conn.prepareStatement("SELECT COALESCE(SUM(totalamount), 0) AS total FROM `campus_fee_collection`");
		rs=pstmt.executeQuery();
		
		while(rs.next()){
			result = rs.getString("total");
		}
	
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e1) {
		logger.error(e1.getMessage(), e1);
		e1.printStackTrace();
	} finally {
		try {
			if (rs != null&& (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null&& (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl : getCollectionCount : Ending");
	
	return result;		

}

public String getStudentCount(String location_id, String academic_year, UserLoggingsPojo userLoggingsVo) {
	
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl: getCollectionCount : Starting");
	
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Connection conn = null;
	String result = "";
 	
	try {
		
		conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		pstmt = conn.prepareStatement("SELECT  COUNT(*) AS total FROM `campus_student` WHERE `locationId` = ? AND `fms_acadamicyear_id_int` = ?");
		pstmt.setString(1, location_id);
		pstmt.setString(2, academic_year);
		rs=pstmt.executeQuery();
		while(rs.next()){
			result = rs.getString("total");
		}
	
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e1) {
		logger.error(e1.getMessage(), e1);
		e1.printStackTrace();
	} finally {
		try {
			if (rs != null&& (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null&& (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl : getCollectionCount : Ending");
	
	return result;		

}

public ArrayList<ReportMenuVo> getAbsenteesDetails(String loaction_id, UserLoggingsPojo userLoggingsVo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl: getAbsenteesDetails : Starting");
	
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Connection conn = null;
	ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
	try {
		conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		pstmt = conn.prepareStatement("SELECT CONCAT_WS(' ',`student_fname_var` ,`student_lname_var`) AS `Name`,`student_rollno` FROM `campus_student` s  JOIN `campus_attendence` a ON a.`addmissionno` = s.`student_id_int`   WHERE  a.`attendence`='absent' AND  a.`locationId` = ? AND a.`attendencedate` = ?");
		pstmt.setString(1, loaction_id);
		pstmt.setDate(2, HelperClass.getCurrentSqlDate());
		rs=pstmt.executeQuery();
		while(rs.next()){
			ReportMenuVo vo = new ReportMenuVo();
			vo.setStudentnamelabel( rs.getString("name")); 
			vo.setRegNo(rs.getString("student_rollno"));
			list.add(vo);
		}
	
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e1) {
		logger.error(e1.getMessage(), e1);
		e1.printStackTrace();
	} finally {
		try {
			if (rs != null&& (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null&& (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl : getAbsenteesDetails : Ending");
	
	return list;		

}

public ArrayList<ReportMenuVo> getSchoolList(UserLoggingsPojo pojo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl: getlocationList : Starting");
	
	CallableStatement cstmt= null;
	ResultSet rs=null;
	Connection conn = null;
	ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
	try{
		conn = JDBCConnection.getSeparateConnection(pojo);
		cstmt =conn.prepareCall("{CALL getAllSchoolList()}");
		 
		rs = cstmt.executeQuery();
	
		while(rs.next()){
			ReportMenuVo vo =new ReportMenuVo();
			vo.setSchoolId(rs.getString("school_id"));
			vo.setSchoolName(rs.getString("school_name"));
			list.add(vo);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try {
			if (rs != null&& (!rs.isClosed())) {
				rs.close();
			}
			if (cstmt != null&& (!cstmt.isClosed())) {
				cstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl : getlocationList : Ending");
	
	return list;

}

public String getReportType(String accyear, String classId, String locationid, UserLoggingsPojo userLoggingsVo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl: getReportType : Starting");
	
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Connection conn = null;
	String reporttype="";
	try {
		conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		pstmt = conn.prepareStatement("SELECT COUNT(*) as no,`report_type` FROM `campus_exam_report_setup` WHERE `accy_id`=? AND `loc_id`=? AND `class_id`=?");
		pstmt.setString(1, accyear);
		pstmt.setString(2, locationid);
		pstmt.setString(3, classId);
		rs=pstmt.executeQuery();
		while(rs.next()){
			if(rs.getInt("no") > 0){
				reporttype=rs.getString("report_type");
			}else{
				reporttype="notfound";
			}
		}
	
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e1) {
		logger.error(e1.getMessage(), e1);
		e1.printStackTrace();
	} finally {
		try {
			if (rs != null&& (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null&& (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl : getReportType : Ending");
	
	return reporttype;		

}

public String getMaximumMarkSetup(String accyear, String classId, String locationid, UserLoggingsPojo userLoggingsVo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl: getReportType : Starting");
	
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Connection conn = null;
	String setup="";
	try {
		conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		pstmt = conn.prepareStatement("SELECT COUNT(*) FROM `campus_exam_max_marks_setup` WHERE `accy_id`=? AND `loc_id`=? AND `class_id`=?");
		pstmt.setString(1, accyear);
		pstmt.setString(2, locationid);
		pstmt.setString(3, classId);
		rs=pstmt.executeQuery();
		while(rs.next()){
			if(rs.getInt(1) > 0){
				setup="found";
			}else{
				setup="notfound";
			}
		}
	
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} catch (Exception e1) {
		logger.error(e1.getMessage(), e1);
		e1.printStackTrace();
	} finally {
		try {
			if (rs != null&& (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null&& (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl : getReportType : Ending");
	
	return setup;		
}

public ReportMenuVo getTerm1ExamDetails(String accyear, String classId, String locationid, UserLoggingsPojo userLoggingsVo) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl: getTerm Starting");
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	ReportMenuVo vo=new ReportMenuVo();
	try{
		conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
		pstmt = conn.prepareStatement("SELECT COUNT(*),CONCAT(rspd.`periodic_exam1`,',',rspd.`yearly_exam`) AS examid,CONCAT(exm.`examtype`,',',exm1.`examtype`) AS examtype,CONCAT(et.`exam_prefix`,',',et1.`exam_prefix`) AS examprefix FROM `campus_exam_report_setup` rsp LEFT JOIN `campus_exam_report_setup_detail` rspd ON rspd.`report_setup_id`=rsp.`report_setup_id` AND rspd.`periodic_exam1`!='' AND rspd.`yearly_exam` !='' LEFT JOIN `campus_examination` exm ON exm.`examid`=rspd.`periodic_exam1` LEFT JOIN `campus_examination` exm1 ON exm1.`examid`=rspd.`yearly_exam` LEFT JOIN `campus_examtype` et ON et.`examtypeid`=exm.examtype LEFT JOIN `campus_examtype` et1 ON et1.`examtypeid`=exm1.examtype WHERE rsp.`accy_id`=? AND rsp.`loc_id`=? AND rsp.`class_id`=? AND rspd.`report_name`='term1'");
		pstmt.setString(1,accyear);
		pstmt.setString(2,locationid);
		pstmt.setString(3,classId);
		rs = pstmt.executeQuery();
		while(rs.next())
		{
			if(rs.getInt(1) > 0){
				vo.setCount(rs.getInt(1));
				vo.setExamId(rs.getString(2));
				vo.setExamtypeid(rs.getString(3));
				vo.setExamtypeprefix(rs.getString(4));
			}else{
				vo.setCount(rs.getInt(1));
				vo.setExamname("");
				vo.setExamtypeid("");
				vo.setExamtypeprefix("");
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try {
			if (rs != null&& (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null&& (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl : getTerm Ending");
	return vo;
}

public ReportMenuVo getTerm2ExamDetails(String accyear, String classId, String locationid, UserLoggingsPojo userLoggingsVo) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl: getTerm Starting");
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	ReportMenuVo vo=new ReportMenuVo();
	try{
		conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
		pstmt = conn.prepareStatement("SELECT COUNT(*),CONCAT(rspd.`periodic_exam1`,',',rspd.`yearly_exam`) AS examid,CONCAT(exm.`examtype`,',',exm1.`examtype`) AS examtype,CONCAT(et.`exam_prefix`,',',et1.`exam_prefix`) AS examprefix FROM `campus_exam_report_setup` rsp LEFT JOIN `campus_exam_report_setup_detail` rspd ON rspd.`report_setup_id`=rsp.`report_setup_id` AND rspd.`periodic_exam1`!='' AND rspd.`yearly_exam` !='' LEFT JOIN `campus_examination` exm ON exm.`examid`=rspd.`periodic_exam1` LEFT JOIN `campus_examination` exm1 ON exm1.`examid`=rspd.`yearly_exam` LEFT JOIN `campus_examtype` et ON et.`examtypeid`=exm.examtype LEFT JOIN `campus_examtype` et1 ON et1.`examtypeid`=exm1.examtype WHERE rsp.`accy_id`=? AND rsp.`loc_id`=? AND rsp.`class_id`=? AND rspd.`report_name`='term2'");
		pstmt.setString(1,accyear);
		pstmt.setString(2,locationid);
		pstmt.setString(3,classId);
		rs = pstmt.executeQuery();
		while(rs.next())
		{
			if(rs.getInt(1) > 0){
				vo.setCount(rs.getInt(1));
				vo.setExamId(rs.getString(2));
				vo.setExamtypeid(rs.getString(3));
				vo.setExamtypeprefix(rs.getString(4));
			}else{
				vo.setCount(rs.getInt(1));
				vo.setExamname("");
				vo.setExamtypeid("");
				vo.setExamtypeprefix("");
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try {
			if (rs != null&& (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null&& (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl : getTerm Ending");
	return vo;
}

public ReportMenuVo getAcademicExamDetails(String accyear, String classId, String locationid, UserLoggingsPojo userLoggingsVo) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl: getTerm Starting");
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	ReportMenuVo vo=new ReportMenuVo();
	try{
		conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
		pstmt = conn.prepareStatement("SELECT COUNT(*),CONCAT(rspd.`periodic_exam1`,',',rspd.`periodic_exam2`,',',rspd.`halfyearlyexam`,',',rspd.`yearly_exam`) AS termexam,CONCAT(exm.`examtype`,',',exm2.`examtype`,',',exm3.`examtype`) AS examtype,CONCAT(et.`exam_prefix`,',',et2.`exam_prefix`,',',et3.`exam_prefix`) AS examprefix FROM `campus_exam_report_setup` rsp LEFT JOIN `campus_exam_report_setup_detail` rspd ON rspd.`report_setup_id`=rsp.`report_setup_id` AND rspd.`periodic_exam1`!='' AND rspd.`periodic_exam2` !='' AND rspd.halfyearlyexam !='' AND rspd.`yearly_exam` !='' LEFT JOIN `campus_examination` exm ON exm.`examid`=rspd.`periodic_exam1` LEFT JOIN `campus_examination` exm1 ON exm1.`examid`=rspd.`periodic_exam2` LEFT JOIN `campus_examination` exm2 ON exm2.`examid`=rspd.`halfyearlyexam` LEFT JOIN `campus_examination` exm3 ON exm3.`examid`=rspd.`yearly_exam` LEFT JOIN `campus_examtype` et ON et.`examtypeid`=exm.examtype LEFT JOIN `campus_examtype` et1 ON et1.`examtypeid`=exm1.examtype LEFT JOIN `campus_examtype` et2 ON et2.`examtypeid`=exm2.examtype LEFT JOIN `campus_examtype` et3 ON et3.`examtypeid`=exm3.examtype WHERE rsp.`accy_id`=? AND rsp.`loc_id`=? AND rsp.`class_id`=? AND rspd.`report_name`='academic'");
		pstmt.setString(1,accyear);
		pstmt.setString(2,locationid);
		pstmt.setString(3,classId);
		rs = pstmt.executeQuery();
		while(rs.next())
		{
			if(rs.getInt(1) > 0){
				vo.setCount(rs.getInt(1));
				vo.setExamId(rs.getString(2));
				vo.setExamtypeid(rs.getString(3));
				vo.setExamtypeprefix(rs.getString(4));
			}else{
				vo.setCount(rs.getInt(1));
				vo.setExamname("");
				vo.setExamtypeid("");
				vo.setExamtypeprefix("");
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try {
			if (rs != null&& (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null&& (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ReportsMenuDaoImpl : getTerm Ending");
	return vo;
}

}
   
