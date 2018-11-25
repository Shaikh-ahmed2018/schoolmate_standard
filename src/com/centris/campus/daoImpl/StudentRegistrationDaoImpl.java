package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentRegistrationDao;
import com.centris.campus.forms.StudentRegistrationForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.SendMail;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.util.TeacherUtilsConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.StaffAttendanceVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportTypeVO;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.centris.campus.vo.registrationvo;
import com.centris.campus.vo.secadmissionformformatVO;

public class StudentRegistrationDaoImpl implements StudentRegistrationDao {
	ArrayList<String> dobList = new ArrayList<String>();
	String dob = null;
	String duplicateStudentName = null;
	String stuAdmissionNo = null;
	String academicYear = "";
	String regno = null;

	/**
	 * Logger instance.
	 */
	private static final Logger logger = Logger
			.getLogger(StudentRegistrationDaoImpl.class);


	// To Get student related dropdown values

	public List<StudentRegistrationVo> getAcademicYear(UserLoggingsPojo userLoggingsVo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAcademicYear Starting");
		List<StudentRegistrationVo> studentRegistrationVos = new ArrayList<StudentRegistrationVo>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ACADEMIC_YEAR);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setAcademicYear(resultSet
						.getString(MessageConstants.AcademicYear));
				studentRegistrationVo.setAcademicYearId(resultSet
						.getString(MessageConstants.AcademicYearId));

				studentRegistrationVos.add(studentRegistrationVo);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAcademicYear Ending");
		return studentRegistrationVos;
	}

	public int getStudentMaxId() throws SQLException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentMaxId Starting");
		PreparedStatement pst = null;
		ResultSet rs = null;
		int studentMaxid = 0;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_MAXID);
			rs = pst.executeQuery();
			while (rs.next()) {
				studentMaxid = rs.getInt(1);
			}
			if (studentMaxid == 0) {
				studentMaxid = 1;
			}

		} catch (Exception e) {

			logger.error(e);
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentMaxId Ending");
		return studentMaxid;
	}

	@Override
	public List<StudentRegistrationVo> getStudentquota(UserLoggingsPojo pojo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentquota Starting");
		List<StudentRegistrationVo> studentRegistrationVos = new ArrayList<StudentRegistrationVo>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_QUOTA);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setStudentquotaname(resultSet
						.getString("Quota_Name"));
				studentRegistrationVo.setStudentquotaid(resultSet
						.getString("Quota_Code"));

				studentRegistrationVos.add(studentRegistrationVo);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentquota Ending");
		return studentRegistrationVos;

	}

	@Override
	public List<StudentRegistrationVo> getStudentCaste(UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentCaste Starting");
		List<StudentRegistrationVo> studentReAdmissionVOList = new ArrayList<StudentRegistrationVo>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_CASTE);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				StudentRegistrationVo studentReAdmissionVO = new StudentRegistrationVo();
				studentReAdmissionVO.setStudentcastename(resultSet
						.getString("caste_name"));
				studentReAdmissionVO.setStudentcasteid(resultSet
						.getString("caste_id"));

				studentReAdmissionVOList.add(studentReAdmissionVO);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : getStudentCaste Ending");
		return studentReAdmissionVOList;
	}

	public List<StudentRegistrationVo> getChildCategory(String schoolLocation,UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getChildCategory  Starting");
		List<StudentRegistrationVo> categorylist = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		if(schoolLocation.equalsIgnoreCase("all")){
			schoolLocation="%%";
		}
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CATEGORY);
			pst.setString(1, schoolLocation);
			//ln("STUDENT_CATEGORY -->>"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo
				.setStreemcode(rs.getString("classstream_id_int"));
				registrationVo.setStreemname(rs
						.getString("classstream_name_var"));
				categorylist.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getChildCategory  Ending");

		return categorylist;
	}

	public List<StudentRegistrationVo> getClassDetails(String catecory, String location, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			//ln("catecory is "+catecory);
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CLASS);
			pst.setString(1, catecory.trim());
			pst.setString(2, location);
			//ln("student class "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));


				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getSection(String searchTerm, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getSection  Starting");
		String classval=searchTerm.split(",")[0];
		String location=searchTerm.split(",")[1];

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SECTION);
			pst.setString(1, classval.trim());
			pst.setString(2, location);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getSection  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConcessionDetails(UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConcessionDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CONCESSION);

			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setConcessionid(rs.getString("concessionid"));
				registrationVo.setConcession(rs.getString("concessionname"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getConcessionDetails  Ending");

		return list;
	}

	public List<TransportTypeVO> transportypedetails(UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : transportypedetails  Starting");
		List<TransportTypeVO> list = new ArrayList<TransportTypeVO>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.TRANSPORT_TYPE);
			//ln("TRANSPORT_TYPE -->>"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				TransportTypeVO registrationVo = new TransportTypeVO();
				registrationVo.setTransptyId(rs.getString("type_id"));
				registrationVo.setTransptyname(rs.getString("type_name"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : transportypedetails  Ending");


		return list;

	}

	public List<StageMasterVo> getStageDetails(UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStageDetails  Starting");
		List<StageMasterVo> list = new ArrayList<StageMasterVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.TRANSPORT_LOCATION);
			//ln("TRANSPORT_LOCATION -->>"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StageMasterVo registrationVo = new StageMasterVo();
				registrationVo.setStageCode(rs.getString("stage_id"));
				registrationVo.setStageName(rs.getString("stage_name"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStageDetails  Ending");
		return list;

	}

	public String getTransportCategoryType(String trnsportTypeId, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportCategoryType Starting");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String transportTypeStatus = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);

			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_TRANSPORTSTATUS_TYPE);
			preparedStatement.setString(1, trnsportTypeId);
			//ln(preparedStatement);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				transportTypeStatus = rs.getString("type_collectFee");

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : getTransportCategoryType Ending");
		return transportTypeStatus;
	}


	public ArrayList<ViewallSubjectsVo> getSubjectByClass(String classID, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl: getSubjectByClass Starting");
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<ViewallSubjectsVo> subjectList = new ArrayList<ViewallSubjectsVo>();

		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmObj = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_SUBJECT_DETAILS);
			pstmObj.setString(1, classID);
			rs = pstmObj.executeQuery();

			while (rs.next()) {

				ViewallSubjectsVo vo = new ViewallSubjectsVo();
				vo.setSubjectid(rs.getString("subjectID"));
				vo.setSubjectname(rs.getString("subjectName"));

				subjectList.add(vo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in StudentRegistrationDaoImpl: getSubjectByClass  Ending");
		return subjectList;
	}

	//For validation

	@Override
	public int validateDuplicateData(StudentRegistrationForm formObj,
			String type,UserLoggingsPojo dbdetails) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : validateDuplicateData Starting");
		PreparedStatement pstmt = null;

		int count = 0;
		ResultSet rst = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			if (type.equalsIgnoreCase("Add")) {

				pstmt = conn
						.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DUPLICATE_CHECK_ADDTIME);
				pstmt.setString(1, formObj.getStudentFirstName());
				pstmt.setString(2, formObj.getCategory());
				pstmt.setString(3, formObj.getStudClassId());
				pstmt.setString(4, formObj.getStudSectionId());

				if (formObj.getFatherName() != null) {
					pstmt.setString(5, formObj.getFatherName().trim());
				} else {
					pstmt.setString(5, "");
				}
				if (formObj.getMotherName() != null) {
					pstmt.setString(6, formObj.getMotherName().trim());
				} else {
					pstmt.setString(6, "");
				}
				/*if (formObj.getDateofBirth() != null) {
					pstmt.setString(7, HelperClass.convertUIToDatabase(formObj.getDateofBirth().trim()));
				} else {
					pstmt.setString(7, "");
				}*/
				if (formObj.getDateofJoin() != null) {
					pstmt.setString(7, HelperClass.convertUIToDatabase(formObj.getDateofJoin().trim()));
				} else {
					pstmt.setString(7, "");
				}

				pstmt.setString(8, formObj.getAcademicYear());
				//ln("pstmt is "+pstmt);
				rst = pstmt.executeQuery();

				while (rst.next()) {
					count = rst.getInt(1);
				}
			} else {
				pstmt = conn
						.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DUPLICATE_CHECK_UPDATETIME);
				pstmt.setString(1, formObj.getStudentFirstName());
				pstmt.setString(2, formObj.getCategory());
				pstmt.setString(3, formObj.getStudClassId());
				pstmt.setString(4, formObj.getStudSectionId());

				if (formObj.getFatherName() != null) {
					pstmt.setString(5, formObj.getFatherName());
				} else {
					pstmt.setString(5, "");
				}
				if (formObj.getMotherName() != null) {
					pstmt.setString(6, formObj.getMotherName());
				} else {
					pstmt.setString(6, "");
				}
				/*if (formObj.getDateofBirth() != null) {
					pstmt.setString(7, HelperClass.convertUIToDatabase(formObj.getDateofBirth()));
				} else {
					pstmt.setString(7, "");
				}*/
				if (formObj.getDateofJoin() != null) {
					pstmt.setString(7, HelperClass.convertUIToDatabase(formObj.getDateofJoin()));
				} else {
					pstmt.setString(7, "");
				}
				pstmt.setString(8, formObj.getAcademicYear());
				pstmt.setString(9, formObj.getStudentIDgenerator());
				rst = pstmt.executeQuery();

				while (rst.next()) {
					count = rst.getInt(1);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : validateDuplicateData Ending");
		return count;
	}

	public String validateRollNumber(String searchTerm, UserLoggingsPojo pojo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : validateRollNumber Starting");
		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;
		String rollNumber=searchTerm.split(",")[0];
		String location=searchTerm.split(",")[1];

		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CHECK_ROLL_NUMBER);
			preparedStatement.setString(1, rollNumber);
			preparedStatement.setString(2, location);
			
			rs = preparedStatement.executeQuery();

			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "True";
			} else {
				successMessage = "False";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : validateRollNumber Ending");
		return successMessage;

	}

	@Override
	public String checkApplicationNo(String applicationNo,UserLoggingsPojo userLoggingsVo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : checkApplicationNo Starting");
		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.CHECK_APPLICATION_NUMBER);
			preparedStatement.setString(1, applicationNo);
			rs = preparedStatement.executeQuery();

			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "Application No already Exists";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : checkApplicationNo Ending");
		return successMessage;
	}

	@Override
	public String validatePhoneNo(String phoneNo, UserLoggingsPojo pojo) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : validatePhoneNo Starting");
		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_PARENT_PHONENO);

			preparedStatement.setString(1, phoneNo);
			preparedStatement.setString(2, phoneNo);
			preparedStatement.setString(3, phoneNo);
			//ln(preparedStatement);
			rs = preparedStatement.executeQuery();

			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "true";
			}
			else{
				successMessage = "false";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : validatePhoneNo Ending");
		return successMessage;

	}

	@Override
	public String validateEmail(String email, UserLoggingsPojo pojo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : validateEmail Starting");
		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_PARENT_EMAILS);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, email);

			rs = preparedStatement.executeQuery();

			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "Email already Exists";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : validateEmail Ending");
		return successMessage;

	}

	//To save student

	@Override
	public Map<String, String> saveStudentRegistration(StudentRegistrationForm registration, UserLoggingsPojo dbdetails) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentRegistration Starting");


		int result = 0,result1=0;
		String relationship = "";
		Map<String, String> studentIDAdmissionNOMap = new HashMap<String, String>();
		PreparedStatement precategoryName = null;
		Connection conn = null;
		Savepoint sp = null;
		String categoryName=null;
		PreparedStatement academicYearpre=null;
		String className = null;
		String sectionName = null;
		PreparedStatement prclassName = null;
		PreparedStatement prsectionName = null;
		PreparedStatement pstmcount = null;


		try {
			//ln("11111111111111"+registration.getTrnsnoreason());
			//ln("???????????????"+registration.getTransport());

			conn = JDBCConnection.getSeparateConnection(dbdetails);

			conn.setAutoCommit(false);

			sp = conn.setSavepoint("SavaStudent");

			precategoryName = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_STREAM);
			precategoryName.setString(1, registration.getCategory());

			ResultSet resultSetcategoryName = precategoryName.executeQuery();

			while (resultSetcategoryName.next()) {

				categoryName = resultSetcategoryName.getString("classstream_name_var");
			}
			resultSetcategoryName.close();

			academicYearpre = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ACADEMIC_YEAR_BYID);
			academicYearpre.setString(1, registration.getAcademicYear());

			ResultSet rsacademicYear = academicYearpre.executeQuery();

			String academicYear = "";

			while (rsacademicYear.next()) {
				academicYear = rsacademicYear.getString("acadamic_year");
			}
			academicYearpre.close();

			prclassName = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_NAME);
			prclassName.setString(1, registration.getStudClassId());

			ResultSet resultSetclassName = prclassName.executeQuery();

			while (resultSetclassName.next()) {
				className = resultSetclassName.getString("classdetails_name_var");
			}
			resultSetclassName.close();
			prsectionName = conn.prepareStatement(StudentRegistrationSQLUtilConstants.SECTION_NAME);

			prsectionName.setString(1, registration.getStudSectionId());
			ResultSet resultSetsectionName = prsectionName.executeQuery();

			while (resultSetsectionName.next()) {

				sectionName = resultSetsectionName.getString("classsection_name_var");

			}
			resultSetsectionName.close();
			pstmcount = conn.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_ACADEMIC_YEAR);
			ResultSet rs = pstmcount.executeQuery();
			rs.next();
			int count = rs.getInt(1);

			/*if (categoryName != null)

				stuAdmissionNo = StringUtilContants.STUDENT_ADMISSION_NO + "-";
			if (count < 9) {
				int i = count;
				stuAdmissionNo = stuAdmissionNo + "000" + (++i) + "/"
						+ academicYear;
			} else if (count < 99) {
				int i = count;
				stuAdmissionNo = stuAdmissionNo + "00" + (++i) + "/"
						+ academicYear;
			} else if (count < 999) {
				int i = count;
				stuAdmissionNo = stuAdmissionNo + "0" + (++i) + "/"
						+ academicYear;
			} else {
				int i = count;
				stuAdmissionNo = stuAdmissionNo + (++i) + "/" + academicYear;
			}*/
			rs.close();
			PreparedStatement pstmclasscount = conn.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_CLASS);
			pstmclasscount.setString(1, registration.getStudClassId());
			ResultSet rsClass = pstmclasscount.executeQuery();
			rsClass.next();
			int classcount = rsClass.getInt(1);

			/*String studentRegNo = null;
			if (className != null)
				studentRegNo = StringUtilContants.STUDENT_REGISTRATION_NO + "-";

			if (classcount < 9) {
				int i = classcount;
				studentRegNo = studentRegNo + "000" + (++i);
			} else if (classcount < 99) {
				int i = classcount;
				studentRegNo = studentRegNo + "00" + (++i);
			} else if (classcount < 999) {
				int i = classcount;
				studentRegNo = studentRegNo + "0" + (++i);
			} else {
				int i = classcount;
				studentRegNo = studentRegNo + (++i);
			}
			 */
			rsacademicYear.close();

			int studentDuplicateCount = 0;


			//To check duplicate student

			PreparedStatement countDuplicate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DUPLICATE);
			countDuplicate.setString(1, registration.getStudentFirstName());
			countDuplicate.setString(2, registration.getCategory());
			countDuplicate.setString(3, registration.getStudClassId());
			countDuplicate.setString(4, registration.getStudSectionId());

			if (registration.getFatherName() != null) {
				countDuplicate.setString(5, registration.getFatherName());
			} else {
				countDuplicate.setString(5, "");
			}
			if (registration.getMotherName() != null) {
				countDuplicate.setString(6, registration.getMotherName());
			} else {
				countDuplicate.setString(6, "");
			}
			countDuplicate.setString(7, HelperClass.convertUIToDatabase(registration.getDateofBirth()));
			countDuplicate.setString(8, HelperClass.convertUIToDatabase(registration.getDateofJoin().trim()));

			ResultSet duplicateRs = countDuplicate.executeQuery();

			while (duplicateRs.next()) {

				studentDuplicateCount = duplicateRs.getInt(1);
			}
			duplicateRs.close();
			countDuplicate.close();
			if (studentDuplicateCount != 0) {

				studentIDAdmissionNOMap.put("duplicateMessage","Student Already Registered with these Details");
			} else {

				conn.setAutoCommit(false);

				String admission=registration.getStudentrollno();
				//To store student details

				PreparedStatement preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_REGISTRATION);

				Timestamp createdDate = HelperClass.getCurrentTimestamp();

				preparedStatement.setString(1, registration.getStudentIDgenerator().trim());
				preparedStatement.setString(2, registration.getStudentrollno().trim()); //For addmission Number
				preparedStatement.setString(3, registration.getAcademicYear().trim());
				//preparedStatement.setString(4, stuAdmissionNo); // For registartion number
				preparedStatement.setString(4, registration.getStudentFirstName().trim());
				preparedStatement.setString(5, registration.getStudentLastName().trim());
				preparedStatement.setString(6, registration.getStudentPhoto().trim());
				preparedStatement.setDate(7, HelperClass.getSqlDateFromDdMmYyFormat(registration.getDateofJoin().trim()));
				preparedStatement.setString(8,registration.getCreateUser().trim());
				preparedStatement.setTimestamp(9, createdDate);
				preparedStatement.setString(10, registration.getApplicationNo().trim());
				preparedStatement.setString(11, registration.getMedium());
				preparedStatement.setString(12, registration.getTransferCertificateNo());
				preparedStatement.setString(13, registration.getIsWorkingTeacherGuardian());
				preparedStatement.setString(14, registration.getWorkingTeacherGuardianId());
				preparedStatement.setString(15, registration.getWorkingTeacherName());
				preparedStatement.setString(16, HelperClass.convertUIToDatabase((registration.getDateofBirth().trim())));
				if(registration.getGender() != null && !registration.getGender().equalsIgnoreCase("")){
					preparedStatement.setString(17, registration.getGender().trim());
				}else{
					preparedStatement.setString(17, "");
				}
				preparedStatement.setString(18, registration.getAge().trim());
				preparedStatement.setString(19, registration.getBloodGroup().trim());
				preparedStatement.setString(20, registration.getNationality().trim());
				preparedStatement.setString(21, registration.getMothertongue());
				preparedStatement.setString(22, registration.getAadharno());
				preparedStatement.setString(23,	registration.getPhysicallyChallenged());
				preparedStatement.setString(24,	registration.getPhysicalchalreason());
				preparedStatement.setString(25, registration.getReligion().trim());
				preparedStatement.setString(26, registration.getCaste().trim());
				preparedStatement.setString(27, registration.getCasteCategory().trim());

				preparedStatement.setString(28, registration.getIdentificationMarks().trim());
				preparedStatement.setString(29, registration.getIdentificationMarks1().trim());
				preparedStatement.setString(30, registration.getPreviousHistory().trim());
				preparedStatement.setString(31, registration.getRemarks().trim());

				preparedStatement.setString(32,	registration.getStudentSibilingIdInt());

				if(registration.getPrimaryPerson()!=null && registration.getPrimaryPerson().equalsIgnoreCase("father")){
					preparedStatement.setDouble(33, registration.getFatherAnnualIncome());
				}else if(registration.getPrimaryPerson()!=null && registration.getPrimaryPerson().equalsIgnoreCase("mother")){
					preparedStatement.setDouble(33, registration.getMotherAnnualIncome());
				}else if(registration.getPrimaryPerson()!=null && registration.getPrimaryPerson().equalsIgnoreCase("guardian")){
					preparedStatement.setDouble(33, registration.getGuardianAnnualIncome());
				}else{
					preparedStatement.setDouble(33, 0.0);
				}

				preparedStatement.setString(34, registration.getTransferfile());
				preparedStatement.setString(35, registration.getBirthfile());
				preparedStatement.setString(36, registration.getCertificate1Url());
				preparedStatement.setString(37, registration.getCertificate2Url());
				preparedStatement.setString(38, registration.getCertificate3Url());
				preparedStatement.setString(39, registration.getCertificate4Url());
				preparedStatement.setString(40, registration.getCertificate5Url());
				preparedStatement.setString(41, registration.getTempRegId());
				preparedStatement.setString(42, registration.getStudentstatus());
				preparedStatement.setString(43, registration.getSchoolLocation());
				preparedStatement.setString(44, registration.getIstransferstudent());
				/*preparedStatement.setString(46, registration.getStudClassId());*/

				result = preparedStatement.executeUpdate();
				if(result > 0){
					HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Insert",preparedStatement.toString(),dbdetails);
				}
				preparedStatement.close();

				//To store student class details

				PreparedStatement scpstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CLASS_REGISTRATION);

				scpstmt.setString(1, registration.getStudentIDgenerator().trim());
				scpstmt.setString(2, registration.getAcademicYear().trim());
				scpstmt.setString(3, registration.getCategory().trim());
				scpstmt.setString(4, registration.getStudClassId().trim());
				scpstmt.setString(5, registration.getStudSectionId().trim());
				if(registration.getSpecilization() != null && !registration.getSpecilization().equalsIgnoreCase("")){
					scpstmt.setString(6, registration.getSpecilization());
				}else{
					scpstmt.setString(6, "-");
				}
				scpstmt.setString(7, registration.getStudentPhoto().trim());
				scpstmt.setString(8, registration.getCreateUser().trim());

				scpstmt.setTimestamp(9, createdDate);
				scpstmt.setString(10, registration.getSchoolLocation());
				scpstmt.setString(11, registration.getSchoolLocation());
				if(registration.getFirstlang().equalsIgnoreCase("all")){
					scpstmt.setString(12, "");
				}else{
					scpstmt.setString(12, registration.getFirstlang().trim());
				}
				if(registration.getSecondlang().equalsIgnoreCase("all")){
					scpstmt.setString(13, "");
				}else{
					scpstmt.setString(13, registration.getSecondlang().trim());
				}
				if(registration.getThirdlang().equalsIgnoreCase("all")){
					scpstmt.setString(14, "");
				}else{
					scpstmt.setString(14, registration.getThirdlang().trim());
				}
				/*scpstmt.setString(13, registration.getSecondlang().trim());
				scpstmt.setString(14, registration.getThirdlang().trim());*/
				scpstmt.setString(15, registration.getStudHouseId().trim());
				scpstmt.setString(16, registration.getIstransferstudent());
				scpstmt.setString(17, "STUDYING");
				/*scpstmt.setString(16, registration.getConcessiontype());

				if(registration.getFeeConcession().equalsIgnoreCase("Yes")){
					scpstmt.setString(17, "Y");
				}else{
					scpstmt.setString(17, "N");
				}*/


				int stu_cls_details  = scpstmt.executeUpdate();
				if(stu_cls_details > 0){
					HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Insert",scpstmt.toString(),dbdetails);
				}
				scpstmt.close();
				// To Store student contacts
				PreparedStatement contactpstmt=conn.prepareStatement("INSERT INTO campus_students_contacts (studentId,emergencyNo) VALUES(?,?)");
				contactpstmt.setString(1, registration.getStudentIDgenerator().trim());
				contactpstmt.setString(2, registration.getEmergencynumber());
				int stu_contacts = contactpstmt.executeUpdate();
				if(stu_contacts > 0)
					HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Insert",contactpstmt.toString(),dbdetails);

				contactpstmt.close();

				if(registration.getIstransferstudent().equalsIgnoreCase("Yes")) {
					PreparedStatement transeferpstmt=conn.prepareStatement("INSERT INTO campus_student_transfer_fee_applicaple (student_id_int,termid) VALUES(?,?)");
					for(int k=0;k<registration.getFeeapplicableterm().length;k++) {

						transeferpstmt.setString(1, registration.getStudentIDgenerator().trim());
						transeferpstmt.setString(2, registration.getFeeapplicableterm()[k]);
						int stu_tranfer = transeferpstmt.executeUpdate();
						if(stu_tranfer > 0)
							HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Insert",transeferpstmt.toString(),dbdetails);

					}

					transeferpstmt.close();
				}

				//To store student transport details

				PreparedStatement stpstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_TRANSPORT_REGISTRATION);

				stpstmt.setString(1, registration.getStudentIDgenerator().trim());
				stpstmt.setString(2, registration.getAcademicYear().trim());

				////ln("TRANSPORT IN NULL "+registration.getTransport()+" registration.getTranscategory() "+ registration.getTranscategory());

				if(registration.getTransport() !=null && !registration.getTransport().equalsIgnoreCase("N")){
					stpstmt.setString(3, registration.getTransport().trim());
					stpstmt.setString(4, registration.getTranscategory().trim());
					stpstmt.setString(5, registration.getTranslocation().trim());
					stpstmt.setString(6, registration.getRoute());
					/*stpstmt.setString(7, "-");*/
				}else{
					stpstmt.setString(3, "N");
					stpstmt.setString(4, null);
					stpstmt.setString(5, null);
					stpstmt.setString(6, null);
					/*stpstmt.setString(7,registration.getTrnsnoreason());*/
				}

				stpstmt.setString(7, registration.getCreateUser().trim());
				stpstmt.setTimestamp(8, createdDate);
				stpstmt.setString(9, registration.getSchoolLocation());
				//ln("STUDENT_TRANSPORT_REGISTRATION -->>"+stpstmt);
				int stu_transport = stpstmt.executeUpdate();

				if(stu_transport > 0){
					HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Insert",stpstmt.toString(),dbdetails);
				}
				stpstmt.close();

				//Parent Details 

				String parentId =null;
				String punamepwd = null;
				String pemail = null;
				////ln("registration.getParentId() "+ registration.getParentId());
				if (registration.getParentId()==null || "".equalsIgnoreCase(registration.getParentId())) {

					PreparedStatement preParentReg = (PreparedStatement) JDBCConnection.getStatement(StudentRegistrationSQLUtilConstants.PARENT_REG,dbdetails);

					parentId = IDGenerator.getPrimaryKeyID("campus_parents",dbdetails);

					String parentUserName = parentId.substring(3);

					if (registration.getPrimaryPerson().equals("father")) {

						relationship = "father";

						//String fatherName = registration.getFatherName();
						// = fatherName.replaceAll(" ", "");

						punamepwd = registration.getFatherMobileNo();

						preParentReg.setString(1, parentId);
						preParentReg.setString(2, registration.getFatherName());
						preParentReg.setString(3, registration.getFatherMobileNo());
						preParentReg.setString(4, registration.getFatherOccupation());
						preParentReg.setString(5, registration.getFatherPanNo());
						preParentReg.setDouble(6, registration.getFatherAnnualIncome());
						preParentReg.setString(7, registration.getFatherEmail());
						preParentReg.setString(8, registration.getFatherQualification());
						preParentReg.setString(9, registration.getFatherPhotoUrl());

						preParentReg.setString(10, registration.getMotherName());
						preParentReg.setString(11, registration.getMotherMobileNo());
						preParentReg.setString(12, registration.getMotherOccupation());
						preParentReg.setString(13, registration.getMotherPanNo());
						preParentReg.setDouble(14, registration.getMotherAnnualIncome());
						preParentReg.setString(15, registration.getMotherEmail());
						preParentReg.setString(16, registration.getMotherQualification());
						preParentReg.setString(17, registration.getMotherPhotoUrl());

						preParentReg.setString(18, registration.getGuardianName());
						preParentReg.setString(19, registration.getGuardianMobileNo());
						preParentReg.setString(20, registration.getGuardianOccupation());
						preParentReg.setString(21, registration.getGuardianPanNo());
						preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
						preParentReg.setString(23, registration.getGuardianEmail());
						preParentReg.setString(24, registration.getGuardianQualification());
						preParentReg.setString(25, registration.getGuardianPhotoUrl());

						preParentReg.setString(26,punamepwd);
						preParentReg.setString(27,punamepwd);

						preParentReg.setString(28, registration.getAddress());
						preParentReg.setString(29, registration.getPresentaddress());
						preParentReg.setString(30, registration.getCreateUser());
						preParentReg.setTimestamp(31, HelperClass.getCurrentTimestamp());
						preParentReg.setString(32, "Active");

					} else if (registration.getPrimaryPerson().equals("mother")) {

						relationship = "mother";
						//String motherName = registration.getMotherName();
						//motherName = motherName.replaceAll(" ", "");
						punamepwd = registration.getFatherMobileNo();

						preParentReg.setString(1, parentId);
						preParentReg.setString(2, registration.getFatherName());
						preParentReg.setString(3, registration.getFatherMobileNo());
						preParentReg.setString(4, registration.getFatherOccupation());
						preParentReg.setString(5, registration.getFatherPanNo());
						preParentReg.setDouble(6, registration.getFatherAnnualIncome());
						preParentReg.setString(7, registration.getFatherEmail());
						preParentReg.setString(8, registration.getFatherQualification());
						preParentReg.setString(9, registration.getFatherPhotoUrl());

						preParentReg.setString(10, registration.getMotherName());
						preParentReg.setString(11, registration.getMotherMobileNo());
						preParentReg.setString(12, registration.getMotherOccupation());
						preParentReg.setString(13, registration.getMotherPanNo());
						preParentReg.setDouble(14, registration.getMotherAnnualIncome());
						preParentReg.setString(15, registration.getMotherEmail());
						preParentReg.setString(16, registration.getMotherQualification());
						preParentReg.setString(17, registration.getMotherPhotoUrl());

						preParentReg.setString(18, registration.getGuardianName());
						preParentReg.setString(19, registration.getGuardianMobileNo());
						preParentReg.setString(20, registration.getGuardianOccupation());
						preParentReg.setString(21, registration.getGuardianPanNo());
						preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
						preParentReg.setString(23, registration.getGuardianEmail());
						preParentReg.setString(24, registration.getGuardianQualification());
						preParentReg.setString(25, registration.getGuardianPhotoUrl());

						preParentReg.setString(26, punamepwd);
						preParentReg.setString(27, punamepwd);

						preParentReg.setString(28, registration.getAddress());
						preParentReg.setString(29, registration.getPresentaddress());
						preParentReg.setString(30, registration.getCreateUser());
						preParentReg.setTimestamp(31, HelperClass.getCurrentTimestamp());
						preParentReg.setString(32, "Active");

						/*Thread.sleep(1000);

						if (!registration.getMotherMobileNo().trim().equalsIgnoreCase("") && registration.getMotherEmail() != null) {
							new SendMail().sendMailtoChild(registration
									.getMotherEmail().trim(), motherName
									.concat(parentUserName), motherName
									.concat(parentUserName));
						}*/

					} else {
						relationship = "guardian";

						//String guardianName = registration.getGuardianName();
						//guardianName = guardianName.replaceAll(" ", " ");

						punamepwd = registration.getFatherMobileNo();

						preParentReg.setString(1, parentId);
						preParentReg.setString(2, registration.getFatherName());
						preParentReg.setString(3, registration.getFatherMobileNo());
						preParentReg.setString(4, registration.getFatherOccupation());
						preParentReg.setString(5, registration.getFatherPanNo());
						preParentReg.setDouble(6, registration.getFatherAnnualIncome());
						preParentReg.setString(7, registration.getFatherEmail());
						preParentReg.setString(8, registration.getFatherQualification());
						preParentReg.setString(9, registration.getFatherPhotoUrl());

						preParentReg.setString(10, registration.getMotherName());
						preParentReg.setString(11, registration.getMotherMobileNo());
						preParentReg.setString(12, registration.getMotherOccupation());
						preParentReg.setString(13, registration.getMotherPanNo());
						preParentReg.setDouble(14, registration.getMotherAnnualIncome());
						preParentReg.setString(15, registration.getMotherEmail());
						preParentReg.setString(16, registration.getMotherQualification());
						preParentReg.setString(17, registration.getMotherPhotoUrl());

						preParentReg.setString(18, registration.getGuardianName());
						preParentReg.setString(19, registration.getGuardianMobileNo());
						preParentReg.setString(20, registration.getGuardianOccupation());
						preParentReg.setString(21, registration.getGuardianPanNo());
						preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
						preParentReg.setString(23, registration.getGuardianEmail());
						preParentReg.setString(24, registration.getGuardianQualification());
						preParentReg.setString(25, registration.getGuardianPhotoUrl());

						preParentReg.setString(26, punamepwd);
						preParentReg.setString(27, punamepwd);

						preParentReg.setString(28, registration.getAddress());
						preParentReg.setString(29, registration.getPresentaddress());
						preParentReg.setString(30, registration.getCreateUser());
						preParentReg.setTimestamp(31, HelperClass.getCurrentTimestamp());
						preParentReg.setString(32, "Active");

					}	

					preParentReg.setString(33, registration.getFatherDepartment());
					preParentReg.setString(34, registration.getFatherDesignation());
					preParentReg.setString(35, registration.getFatherOfficeAddress());
					preParentReg.setString(36, registration.getMotherDepartment());
					preParentReg.setString(37, registration.getMotherDesignation());
					preParentReg.setString(38, registration.getMotherOfficeAddress());
					preParentReg.setString(39, registration.getGuardianDepartment());
					preParentReg.setString(40, registration.getGuardianDesignation());
					preParentReg.setString(41, registration.getGuardianOfficeAddress());

					int parent = preParentReg.executeUpdate();
					if(parent > 0){
						HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Insert",preParentReg.toString(),dbdetails);
					}
					preParentReg.close();

					PreparedStatement preChildParentUpdate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_CHILD_INSERT);

					if (registration.getPrimaryPerson().equals("father")) {

						relationship = "father";

					} else if (registration.getPrimaryPerson().equals("mother")) {

						relationship = "mother";

					} else {

						relationship = "guardian";
					}

					preChildParentUpdate.setString(1, relationship);
					preChildParentUpdate.setString(2, parentId.trim());
					preChildParentUpdate.setString(3, registration.getStudentIDgenerator().trim());

					//ln("Parent-Child Registration "+preChildParentUpdate);
					int parent_child_rel =  preChildParentUpdate.executeUpdate();

					if(parent_child_rel > 0){
						HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Insert",preChildParentUpdate.toString(),dbdetails);
						String userId=IDGenerator.getPrimaryKeyID("campus_user",dbdetails); 
						PreparedStatement ps_insertuser=conn.prepareStatement(TeacherUtilsConstants.INSERT_USER_DETAILS);
						ps_insertuser.setString(1, userId);
						ps_insertuser.setString(2, parentId);
						ps_insertuser.setString(3,punamepwd);
						ps_insertuser.setString(4,punamepwd);
						ps_insertuser.setString(5,"PARENT");
						ps_insertuser.setString(6,"parent");
						ps_insertuser.setString(7, registration.getCreateUser());
						ps_insertuser.setTimestamp(8, HelperClass.getCurrentTimestamp());
						ps_insertuser.setString(9,registration.getLocationId());
						ps_insertuser.setString(10,dbdetails.getCustId());
						int user = ps_insertuser.executeUpdate();
						if(user > 0){
							HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Insert",preChildParentUpdate.toString(),dbdetails);
							//sending mail 
							if(registration.getFatherEmail()!=null && !registration.getFatherEmail().trim().equalsIgnoreCase("")){
								pemail = registration.getFatherEmail();
							}else if(registration.getMotherEmail()!=null && !registration.getMotherEmail().trim().equalsIgnoreCase("")){
								pemail = registration.getMotherEmail();
							}else if(registration.getGuardianEmail()!=null && !registration.getGuardianEmail().trim().equalsIgnoreCase("")){
								pemail = registration.getGuardianEmail();
							}

							if(pemail!=null){
								registration.setFatherEmail(pemail);
								registration.setLogindetails(punamepwd);
								new Thread(){
									public void run(){
										new SendMail().sendMailtoChild(registration.getFatherEmail(),registration.getLogindetails(),dbdetails.getDomain(),HelperClass.getSchoolName(registration.getSchoolLocation(),dbdetails));
									}
								}.start();
							}
							//send SMS
						}
					}
					////ln("77777777777777777:"+registration.getTempRegId());
				}
				/*
				 * if the student is having sibling who is studying in the same school we are not generating the parentid
				 * storing only in campus_parentchildrelation table
				 */
				else{

					PreparedStatement preChildParentUpdate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_CHILD_INSERT);

					if (registration.getPrimaryPerson()!=null && registration.getPrimaryPerson().equals("father")) {
						relationship = "father";
						punamepwd = registration.getFatherMobileNo();

					} else if (registration.getPrimaryPerson()!=null && registration.getPrimaryPerson().equals("mother")) {
						relationship = "mother";
						punamepwd = registration.getMotherMobileNo();
					} else {
						relationship = "guardian";
						punamepwd = registration.getFatherMobileNo();
					}

					preChildParentUpdate.setString(1, relationship);
					preChildParentUpdate.setString(2, registration.getParentId());
					preChildParentUpdate.setString(3, registration.getStudentIDgenerator().trim());

					//ln("Parent-Child Registration "+preChildParentUpdate);
					int campus_parentchildrelation = preChildParentUpdate.executeUpdate();
					if(campus_parentchildrelation > 0){
						HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Insert",preChildParentUpdate.toString(),dbdetails);
					}
				}

				/*temp admission status update*/

				if(registration.getTempRegId() != null || !registration.getTempRegId().equalsIgnoreCase("")){

					/*PreparedStatement updateTempRegStatus = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_TEMP_REGNO);
					updateTempRegStatus.setString(1, "processed");
					updateTempRegStatus.setString(2, registration.getTempRegId());
					//ln("999999999999999999999999:"+updateTempRegStatus);
					int temp_regno =  updateTempRegStatus.executeUpdate();
					if(temp_regno > 0){
						HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Update",updateTempRegStatus.toString(),registration.getCustid());
					}*/

					PreparedStatement updateEnquiryNoStatus = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_ENQUIRY_REGNO);
					updateEnquiryNoStatus.setString(1, "processed");
					updateEnquiryNoStatus.setString(2, registration.getEnquiryId());
					//ln("888888888888888888:"+updateEnquiryNoStatus);
					int temp_parent_enquiry = updateEnquiryNoStatus.executeUpdate();

					if(temp_parent_enquiry > 0)
						HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Update",updateEnquiryNoStatus.toString(),dbdetails);
				}


				if (result > 0) {

					//If school want the automatic admission No generation then we can uncomment the following code.....


					/*	PreparedStatement updateAutomaicAdmissionGen = null;
					PreparedStatement selectAutoGeneration=conn.prepareStatement(SQLUtilConstants.GET_ADMISSSION_NO);
					selectAutoGeneration.setString(1, registration.getSchoolLocation());
					rs=selectAutoGeneration.executeQuery();
					while(rs.next()){
						int IncrementValue = rs.getInt("IncrementValue")+1;
						updateAutomaicAdmissionGen = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_AUTO_GEN_ADMISSIONNO);
						updateAutomaicAdmissionGen.setInt(1, IncrementValue);
						updateAutomaicAdmissionGen.setString(2, registration.getSchoolLocation());
						result1=updateAutomaicAdmissionGen.executeUpdate();
					}*/


					if(result > 0){
						//HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Update",updateAutomaicAdmissionGen.toString());
						conn.commit();
						studentIDAdmissionNOMap.put("successMessage","Adding Record Progressing...");

					}else{
						conn.rollback();
						studentIDAdmissionNOMap.put("errorMessage","Student Not Registered");
					}
				} else {
					conn.rollback();
					studentIDAdmissionNOMap.put("errorMessage","Student Not Registered");
				}
			}

		} catch (Exception exception) {
			conn.rollback(sp);
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
			conn.rollback();
		} finally {
			try {

				if (precategoryName != null && (!precategoryName.isClosed())) {
					precategoryName.close();
				}
				if (academicYearpre != null && (!academicYearpre.isClosed())) {
					academicYearpre.close();
				}
				if (prclassName != null && (!prclassName.isClosed())) {
					prclassName.close();
				}
				if (prsectionName != null && (!prsectionName.isClosed())) {
					prsectionName.close();
				}
				if (pstmcount != null && (!pstmcount.isClosed())) {
					pstmcount.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentRegistration Ending");
		return studentIDAdmissionNOMap;
	}

	//TO modify Student

	//To modify student details

	@Override
	public String modifyStudentDetails(StudentRegistrationForm registration, UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : modifyStudentDetails Starting");

		String successMsg = null;
		String relationship = "";
		int resultModify = 0;
		ResultSet rsClass = null;
		ResultSet rsImagePath = null;
		PreparedStatement preChildParentUpdate = null;
		PreparedStatement preParentRegUpdate = null;
		PreparedStatement getImagePath = null;
		PreparedStatement pstmclasscount = null;
		PreparedStatement academicYearpre = null;
		ResultSet rsacademicYear = null;
		ResultSet resultSetclassName = null;
		PreparedStatement prclassName = null;
		ResultSet rsacademicYearId = null;
		ResultSet duplicateRs = null;
		PreparedStatement pstmmodifyObj = null,pstm=null;
		PreparedStatement pstmacademicYearId = null;
		Connection conn = null;


		try {

			conn = JDBCConnection.getSeparateConnection(dbdetails);

			/*pstm= conn.prepareStatement("UPDATE campus_student_classdetails SET fms_acadamicyear_id_int=?,locationId=? WHERE student_id_int=? AND fms_acadamicyear_id_int=?");
			pstm.setString(1, registration.getAcademicYear());
			pstm.setString(2, registration.getSchoolLocation());
			pstm.setString(3, registration.getStudentId().trim());
			pstm.setString(4, registration.getAcademicYear());
			//ln("modifyStudentDetails -->>"+pstm);*/

			pstmmodifyObj = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_REGISTRATION_MODIFY);

			pstmmodifyObj.setString(1, registration.getStudentFirstName());
			pstmmodifyObj.setString(2, registration.getStudentLastName());
			pstmmodifyObj.setString(3, registration.getStudentrollno());
			pstmmodifyObj.setString(4, registration.getApplicationNo());
			pstmmodifyObj.setString(5, HelperClass.convertUIToDatabase(registration.getDateofJoin()));
			pstmmodifyObj.setString(6, registration.getAcademicYear());
			pstmmodifyObj.setString(7, registration.getSchoolLocation());
			pstmmodifyObj.setString(8, registration.getTransferCertificateNo());
			pstmmodifyObj.setString(9,registration.getIsWorkingTeacherGuardian());
			pstmmodifyObj.setString(10,registration.getWorkingTeacherGuardianId());
			pstmmodifyObj.setString(11,registration.getWorkingTeacherName());
			pstmmodifyObj.setString(12,registration.getMedium());
			pstmmodifyObj.setString(13,registration.getImageFileName());
			pstmmodifyObj.setString(14, registration.getModifyUser());
			pstmmodifyObj.setString(15, HelperClass.getCurrentTimestamp().toString());
			pstmmodifyObj.setString(16, HelperClass.convertUIToDatabase(registration.getDateofBirth()));
			pstmmodifyObj.setString(17, registration.getGender());
			pstmmodifyObj.setString(18, registration.getAge());
			pstmmodifyObj.setString(19, registration.getBloodGroup());
			pstmmodifyObj.setString(20, registration.getNationality());
			pstmmodifyObj.setString(21, registration.getMothertongue());
			pstmmodifyObj.setString(22,registration.getAadharno());
			pstmmodifyObj.setString(23, registration.getPhysicallyChallenged());
			pstmmodifyObj.setString(24, registration.getPhysicalchalreason());
			pstmmodifyObj.setString(25, registration.getReligion());
			pstmmodifyObj.setString(26, registration.getCaste());
			pstmmodifyObj.setString(27, registration.getCasteCategory());
			/*pstmmodifyObj.setString(28, registration.getStudentstatus());*/
			pstmmodifyObj.setString(28, registration.getIdentificationMarks());
			pstmmodifyObj.setString(29, registration.getIdentificationMarks1());
			pstmmodifyObj.setString(30, registration.getPreviousHistory());
			pstmmodifyObj.setString(31, registration.getRemarks());

			pstmmodifyObj.setString(32,	registration.getStudentSibilingIdInt());

			if(registration.getPrimaryPerson().equalsIgnoreCase("father")){
				pstmmodifyObj.setDouble(33, registration.getFatherAnnualIncome());
			}else if(registration.getPrimaryPerson().equalsIgnoreCase("mother")){
				pstmmodifyObj.setDouble(33, registration.getMotherAnnualIncome());
			}else if(registration.getPrimaryPerson().equalsIgnoreCase("guardian")){
				pstmmodifyObj.setDouble(33, registration.getGuardianAnnualIncome());
			}else{
				pstmmodifyObj.setDouble(33, 0.0);
			}

			pstmmodifyObj.setString(34, registration.getTransferfile());
			pstmmodifyObj.setString(35, registration.getBirthfile());
			pstmmodifyObj.setString(36, registration.getCertificate1Url());
			pstmmodifyObj.setString(37, registration.getCertificate2Url());
			pstmmodifyObj.setString(38, registration.getCertificate3Url());
			pstmmodifyObj.setString(39, registration.getCertificate4Url());
			pstmmodifyObj.setString(40, registration.getCertificate5Url());
			pstmmodifyObj.setString(41, registration.getIstransferstudent());



			pstmmodifyObj.setString(42,registration.getStudentId().trim());
			//ln("<<-------------------------------STUDENT_REGISTRATION_MODIFY---------------------------------------->>");
			//ln("STUDENT_REGISTRATION_MODIFY -->>" +pstmmodifyObj);
			//ln("<<-------------------------------STUDENT_REGISTRATION_MODIFY---------------------------------------->>");
			resultModify = pstmmodifyObj.executeUpdate();

			PreparedStatement scpstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CLASS_REGISTRATION_MODIFY);

			scpstmt.setString(1, registration.getAcademicYear().trim());
			scpstmt.setString(2, registration.getCategory().trim());
			scpstmt.setString(3, registration.getStudClassId().trim());
			scpstmt.setString(4, registration.getStudSectionId().trim());
			if(registration.getSpecilization() != null && !registration.getSpecilization().equalsIgnoreCase("")){
				scpstmt.setString(5, registration.getSpecilization());
			}else{
				scpstmt.setString(5, "-");
			}
			scpstmt.setString(6, registration.getImageFileName().trim());
			scpstmt.setString(7, registration.getModifyUser().trim());
			scpstmt.setString(8, HelperClass.getCurrentTimestamp().toString());
			scpstmt.setString(9, registration.getSchoolLocation());
			scpstmt.setString(10, registration.getSchoolLocation());

			scpstmt.setString(11,registration.getFirstlang().trim());
			scpstmt.setString(12,registration.getSecondlang().trim());
			scpstmt.setString(13,registration.getThirdlang().trim());
			scpstmt.setString(14,registration.getStudHouseId().trim());
			/*scpstmt.setString(15,registration.getConcessiontype().trim());
			if(registration.getFeeConcession().trim().equalsIgnoreCase("Yes")){
				scpstmt.setString(16,"Y");
			}else{
				scpstmt.setString(16,"N");
			}*/
			scpstmt.setString(15, registration.getIstransferstudent());
			scpstmt.setString(16, registration.getAcademicYear().trim());
			scpstmt.setString(17,registration.getStudentId().trim());
			

			//ln("UPDATE STUDENT"+scpstmt);
			scpstmt.executeUpdate();
			scpstmt.close();
			

			PreparedStatement checkpstmt=conn.prepareStatement("SELECT COUNT(*) FROM campus_students_contacts WHERE studentId=?");
			checkpstmt.setString(1, registration.getStudentId().trim());
			ResultSet chckRs=checkpstmt.executeQuery();
			int chkcount=0;
			while(chckRs.next()) {
				chkcount=chckRs.getInt(1);
			}
			if(chkcount==0) {
				PreparedStatement contactpstmt=conn.prepareStatement("INSERT INTO campus_students_contacts (studentId,emergencyNo) VALUES(?,?)");
				contactpstmt.setString(1, registration.getStudentId().trim());
				contactpstmt.setString(2, registration.getEmergencynumber());
				contactpstmt.executeUpdate();
				contactpstmt.close();
			}
			else {
				PreparedStatement contactpstmt=conn.prepareStatement("UPDATE campus_students_contacts SET emergencyNo=? WHERE studentId=?");

				contactpstmt.setString(1, registration.getEmergencynumber());
				contactpstmt.setString(2, registration.getStudentId().trim());
				contactpstmt.executeUpdate();
				contactpstmt.close();
			}



			//To store student transport details

			PreparedStatement stpstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_TRANSPORT_REGISTRATION_MODIFY);


			stpstmt.setString(1, registration.getAcademicYear().trim());
			if(registration.getTransport() !=null && !registration.getTransport().equalsIgnoreCase("N")){
				stpstmt.setString(2, registration.getTransport().trim());
				stpstmt.setString(3, registration.getTranscategory().trim());
				stpstmt.setString(4, registration.getTranslocation().trim());
				stpstmt.setString(5, registration.getRoute());
				stpstmt.setString(6, "-");

			}else{
				stpstmt.setString(2, "N");
				stpstmt.setString(3, null);
				stpstmt.setString(4, null);
				stpstmt.setString(5, null);
				stpstmt.setString(6, registration.getTrnsnoreason());
			}

			stpstmt.setString(7, registration.getModifyUser().trim());
			stpstmt.setString(8, HelperClass.getCurrentTimestamp().toString());
			stpstmt.setString(9, registration.getSchoolLocation());
			stpstmt.setString(10, registration.getAcademicYear());
			stpstmt.setString(11, registration.getStudentId().trim());
			
			stpstmt.executeUpdate();
			stpstmt.close();

			//Parent Details 


			String parentId =null;


			if (registration.getParentId()==null || "".equalsIgnoreCase(registration.getParentId())) {

				PreparedStatement preParentReg = (PreparedStatement) JDBCConnection.getStatement(StudentRegistrationSQLUtilConstants.PARENT_REG,dbdetails);

				parentId = IDGenerator.getPrimaryKeyID("campus_parents",dbdetails);

				String parentUserName = parentId.substring(3);

				if (registration.getPrimaryPerson().equals("father")) {

					relationship = "father";

					String fatherName = registration.getFatherName();
					fatherName = fatherName.replaceAll(" ", "");

					preParentReg.setString(1, parentId);
					preParentReg.setString(2, registration.getFatherName());
					preParentReg.setString(3, registration.getFatherMobileNo());
					preParentReg.setString(4, registration.getFatherOccupation());
					preParentReg.setString(5, registration.getFatherPanNo());
					preParentReg.setDouble(6, registration.getFatherAnnualIncome());
					preParentReg.setString(7, registration.getFatherEmail());
					preParentReg.setString(8, registration.getFatherQualification());
					preParentReg.setString(9, registration.getFatherPhotoUrl());

					preParentReg.setString(10, registration.getMotherName());
					preParentReg.setString(11, registration.getMotherMobileNo());
					preParentReg.setString(12, registration.getMotherOccupation());
					preParentReg.setString(13, registration.getMotherPanNo());
					preParentReg.setDouble(14, registration.getMotherAnnualIncome());
					preParentReg.setString(15, registration.getMotherEmail());
					preParentReg.setString(16, registration.getMotherQualification());
					preParentReg.setString(17, registration.getMotherPhotoUrl());

					preParentReg.setString(18, registration.getGuardianName());
					preParentReg.setString(19, registration.getGuardianMobileNo());
					preParentReg.setString(20, registration.getGuardianOccupation());
					preParentReg.setString(21, registration.getGuardianPanNo());
					preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
					preParentReg.setString(23, registration.getGuardianEmail());
					preParentReg.setString(24, registration.getGuardianQualification());
					preParentReg.setString(25, registration.getGuardianPhotoUrl());

					preParentReg.setString(26, fatherName.concat(parentUserName));
					preParentReg.setString(27, fatherName.concat(parentUserName));

					preParentReg.setString(28, registration.getAddress());
					preParentReg.setString(29, registration.getPresentaddress());
					preParentReg.setString(30, registration.getCreateUser());
					preParentReg.setTimestamp(31, HelperClass.getCurrentTimestamp());
					preParentReg.setString(32, "Active");


					/*Thread.sleep(1000);
					if (registration.getFatherEmail() == null) {
						new SendMail().sendMailtoChild(registration
								.getFatherEmail().trim(), fatherName
								.concat(parentUserName), fatherName
								.concat(parentUserName));
					}*/

				} else if (registration.getPrimaryPerson().equals("mother")) {

					relationship = "mother";
					String motherName = registration.getMotherName();
					motherName = motherName.replaceAll(" ", "");

					preParentReg.setString(1, parentId);
					preParentReg.setString(2, registration.getFatherName());
					preParentReg.setString(3, registration.getFatherMobileNo());
					preParentReg.setString(4, registration.getFatherOccupation());
					preParentReg.setString(5, registration.getFatherPanNo());
					preParentReg.setDouble(6, registration.getFatherAnnualIncome());
					preParentReg.setString(7, registration.getFatherEmail());
					preParentReg.setString(8, registration.getFatherQualification());
					preParentReg.setString(9, registration.getFatherPhotoUrl());

					preParentReg.setString(10, registration.getMotherName());
					preParentReg.setString(11, registration.getMotherMobileNo());
					preParentReg.setString(12, registration.getMotherOccupation());
					preParentReg.setString(13, registration.getMotherPanNo());
					preParentReg.setDouble(14, registration.getMotherAnnualIncome());
					preParentReg.setString(15, registration.getMotherEmail());
					preParentReg.setString(16, registration.getMotherQualification());
					preParentReg.setString(17, registration.getMotherPhotoUrl());

					preParentReg.setString(18, registration.getGuardianName());
					preParentReg.setString(19, registration.getGuardianMobileNo());
					preParentReg.setString(20, registration.getGuardianOccupation());
					preParentReg.setString(21, registration.getGuardianPanNo());
					preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
					preParentReg.setString(23, registration.getGuardianEmail());
					preParentReg.setString(24, registration.getGuardianQualification());
					preParentReg.setString(25, registration.getGuardianPhotoUrl());

					preParentReg.setString(26, motherName.concat(parentUserName));
					preParentReg.setString(27, motherName.concat(parentUserName));

					preParentReg.setString(28, registration.getAddress());
					preParentReg.setString(29, registration.getPresentaddress());
					preParentReg.setString(30, registration.getCreateUser());
					preParentReg.setTimestamp(31, HelperClass.getCurrentTimestamp());
					preParentReg.setString(32, "Active");

					/*Thread.sleep(1000);
					if (registration.getMotherEmail() == null) {
						new SendMail().sendMailtoChild(registration
								.getMotherEmail().trim(), motherName
								.concat(parentUserName), motherName
								.concat(parentUserName));
					}*/

				} else {
					relationship = "guardian";

					String guardianName = registration.getGuardianName();
					guardianName = guardianName.replaceAll(" ", " ");

					preParentReg.setString(1, parentId);
					preParentReg.setString(2, registration.getFatherName());
					preParentReg.setString(3, registration.getFatherMobileNo());
					preParentReg.setString(4, registration.getFatherOccupation());
					preParentReg.setString(5, registration.getFatherPanNo());
					preParentReg.setDouble(6, registration.getFatherAnnualIncome());
					preParentReg.setString(7, registration.getFatherEmail());
					preParentReg.setString(8, registration.getFatherQualification());
					preParentReg.setString(9, registration.getFatherPhotoUrl());

					preParentReg.setString(10, registration.getMotherName());
					preParentReg.setString(11, registration.getMotherMobileNo());
					preParentReg.setString(12, registration.getMotherOccupation());
					preParentReg.setString(13, registration.getMotherPanNo());
					preParentReg.setDouble(14, registration.getMotherAnnualIncome());
					preParentReg.setString(15, registration.getMotherEmail());
					preParentReg.setString(16, registration.getMotherQualification());
					preParentReg.setString(17, registration.getMotherPhotoUrl());

					preParentReg.setString(18, registration.getGuardianName());
					preParentReg.setString(19, registration.getGuardianMobileNo());
					preParentReg.setString(20, registration.getGuardianOccupation());
					preParentReg.setString(21, registration.getGuardianPanNo());
					preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
					preParentReg.setString(23, registration.getGuardianEmail());
					preParentReg.setString(24, registration.getGuardianQualification());
					preParentReg.setString(25, registration.getGuardianPhotoUrl());

					preParentReg.setString(26, guardianName.concat(parentUserName));
					preParentReg.setString(27, guardianName.concat(parentUserName));

					preParentReg.setString(28, registration.getAddress());
					preParentReg.setString(29, registration.getPresentaddress());
					preParentReg.setString(30, registration.getCreateUser());
					preParentReg.setTimestamp(31, HelperClass.getCurrentTimestamp());
					preParentReg.setString(32, "Active");

					/*Thread.sleep(1000);
					if (registration.getGuardianEmail() == null) {
						new SendMail().sendMailtoChild(registration
								.getGuardianEmail().trim(), guardianName
								.concat(parentUserName), guardianName
								.concat(parentUserName));
					}*/
				}

				preParentReg.setString(33, registration.getFatherDepartment());
				preParentReg.setString(34, registration.getFatherDesignation());
				preParentReg.setString(35, registration.getFatherOfficeAddress());
				preParentReg.setString(36, registration.getMotherDepartment());
				preParentReg.setString(37, registration.getMotherDesignation());
				preParentReg.setString(38, registration.getMotherOfficeAddress());
				preParentReg.setString(39, registration.getGuardianDepartment());
				preParentReg.setString(40, registration.getGuardianDesignation());
				preParentReg.setString(41, registration.getGuardianOfficeAddress());
				preParentReg.execute();
				preParentReg.close();

			}else{

				PreparedStatement preParentReg = (PreparedStatement) JDBCConnection.getStatement(StudentRegistrationSQLUtilConstants.UPDATE_PARENT_INFORMATION,dbdetails);

				parentId = registration.getParentId().trim();
				String parentUserName = parentId.substring(3);


				//ParentID,FatherName,mobileno,student_father_occupation,fatherPanNo,fatherAnnualIncome,email,Qualification,fatherPhoto,student_mothername_var,
				//student_mothermobileno_var,student_mother_occupation,motherPanNo,motherAnnualIncome,student_mother_mailid,student_motherqualification_var,motherPhoto,
				//student_gaurdianname_var,student_gardian_mobileno,guardianOccupation,guardianPanNo,guardianQualification,student_gardian_mailid,guardianPhoto,
				//UserName,password,address,presentAddress,createdby,createdate,pstatus

				//update campus_parents set FatherName=?,mobileno=?,student_father_occupation=?,fatherPanNo=?,fatherAnnualIncome=?,email=?,Qualification=?,fatherPhoto=?,student_mothername_var=?,student_mothermobileno_var=?,student_mother_occupation=?,motherPanNo=?,motherAnnualIncome=?,student_mother_mailid=?,student_motherqualification_var=?,motherPhoto=?,student_gaurdianname_var=?,student_gardian_mobileno=?,guardianOccupation=?,guardianPanNo=?,guardianQualification=?,student_gardian_mailid=?,guardianPhoto=?,address=?,presentAddress=?,UserName=?,password=?,modifiedby=?,modifiedDate=? where ParentID=?

				if (registration.getPrimaryPerson().equalsIgnoreCase("father")) {

					relationship = "father";

					String fatherName = registration.getFatherName();
					fatherName = fatherName.replaceAll(" ", "");

					//FatherName=?,mobileno=?,student_father_occupation=?,fatherPanNo=?,fatherAnnualIncome=?,email=?,Qualification=?,fatherPhoto=?,student_mothername_var=?,
					//student_mothermobileno_var=?,student_mother_occupation=?,motherPanNo=?,motherAnnualIncome=?,student_mother_mailid=?,student_motherqualification_var=?,
					//motherPhoto=?,student_gaurdianname_var=?,student_gardian_mobileno=?,guardianOccupation=?,guardianPanNo=?,guardianQualification=?,student_gardian_mailid=?,
					//guardianPhoto=?,address=?,presentAddress=?,UserName=?,password=?,modifiedby=?,modifiedDate=? where ParentID=?

					preParentReg.setString(1, registration.getFatherName());
					preParentReg.setString(2, registration.getFatherMobileNo());
					preParentReg.setString(3, registration.getFatherOccupation());
					preParentReg.setString(4, registration.getFatherPanNo());
					preParentReg.setDouble(5, registration.getFatherAnnualIncome());
					preParentReg.setString(6, registration.getFatherEmail());
					preParentReg.setString(7, registration.getFatherQualification());
					preParentReg.setString(8, registration.getFatherPhotoUrl().trim());

					preParentReg.setString(9, registration.getMotherName());
					preParentReg.setString(10, registration.getMotherMobileNo());
					preParentReg.setString(11, registration.getMotherOccupation());
					preParentReg.setString(12, registration.getMotherPanNo());
					preParentReg.setDouble(13, registration.getMotherAnnualIncome());
					preParentReg.setString(14, registration.getMotherEmail());
					preParentReg.setString(15, registration.getMotherQualification());
					preParentReg.setString(16, registration.getMotherPhotoUrl().trim());

					preParentReg.setString(17, registration.getGuardianName());
					preParentReg.setString(18, registration.getGuardianMobileNo());
					preParentReg.setString(19, registration.getGuardianOccupation());
					preParentReg.setString(20, registration.getGuardianPanNo());
					preParentReg.setString(21, registration.getGuardianQualification());
					preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
					preParentReg.setString(23, registration.getGuardianEmail());
					preParentReg.setString(24, registration.getGuardianPhotoUrl().trim());

					preParentReg.setString(25, registration.getAddress());
					preParentReg.setString(26, registration.getPresentaddress());

					preParentReg.setString(27, fatherName.concat(parentUserName));
					preParentReg.setString(28, fatherName.concat(parentUserName));

					preParentReg.setString(29, registration.getModifyUser());
					preParentReg.setTimestamp(30, HelperClass.getCurrentTimestamp());
					preParentReg.setString(40, parentId);

					/*Thread.sleep(1000);
					if (registration.getFatherEmail() == null) {
						new SendMail().sendMailtoChild(registration
								.getFatherEmail().trim(), fatherName
								.concat(parentUserName), fatherName
								.concat(parentUserName));
					}*/

				}else if (registration.getPrimaryPerson().equalsIgnoreCase("mother")) {

					relationship = "mother";
					String motherName = registration.getMotherName();
					motherName = motherName.replaceAll(" ", "");

					preParentReg.setString(1, registration.getFatherName());
					preParentReg.setString(2, registration.getFatherMobileNo());
					preParentReg.setString(3, registration.getFatherOccupation());
					preParentReg.setString(4, registration.getFatherPanNo());
					preParentReg.setDouble(5, registration.getFatherAnnualIncome());
					preParentReg.setString(6, registration.getFatherEmail());
					preParentReg.setString(7, registration.getFatherQualification());
					preParentReg.setString(8, registration.getFatherPhotoUrl().trim());

					preParentReg.setString(9, registration.getMotherName());
					preParentReg.setString(10, registration.getMotherMobileNo());
					preParentReg.setString(11, registration.getMotherOccupation());
					preParentReg.setString(12, registration.getMotherPanNo());
					preParentReg.setDouble(13, registration.getMotherAnnualIncome());
					preParentReg.setString(14, registration.getMotherEmail());
					preParentReg.setString(15, registration.getMotherQualification());
					preParentReg.setString(16, registration.getMotherPhotoUrl().trim());

					preParentReg.setString(17, registration.getGuardianName());
					preParentReg.setString(18, registration.getGuardianMobileNo());
					preParentReg.setString(19, registration.getGuardianOccupation());
					preParentReg.setString(20, registration.getGuardianPanNo());
					preParentReg.setString(21, registration.getGuardianQualification());
					preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
					preParentReg.setString(23, registration.getGuardianEmail());
					preParentReg.setString(24, registration.getGuardianPhotoUrl().trim());

					preParentReg.setString(25, registration.getAddress());
					preParentReg.setString(26, registration.getPresentaddress());

					preParentReg.setString(27, motherName.concat(parentUserName));
					preParentReg.setString(28, motherName.concat(parentUserName));

					preParentReg.setString(29, registration.getModifyUser());
					preParentReg.setTimestamp(30, HelperClass.getCurrentTimestamp());
					preParentReg.setString(40, parentId);


					/*Thread.sleep(1000);
					if (registration.getMotherEmail() == null) {
						new SendMail().sendMailtoChild(registration
								.getMotherEmail().trim(), motherName
								.concat(parentUserName), motherName
								.concat(parentUserName));
					}*/
				} else {
					relationship = "guardian";

					String guardianName = registration.getGuardianName();
					guardianName = guardianName.replaceAll(" ", " ");

					preParentReg.setString(1, registration.getFatherName());
					preParentReg.setString(2, registration.getFatherMobileNo());
					preParentReg.setString(3, registration.getFatherOccupation());
					preParentReg.setString(4, registration.getFatherPanNo());
					preParentReg.setDouble(5, registration.getFatherAnnualIncome());
					preParentReg.setString(6, registration.getFatherEmail());
					preParentReg.setString(7, registration.getFatherQualification());
					preParentReg.setString(8, registration.getFatherPhotoUrl().trim());

					preParentReg.setString(9, registration.getMotherName());
					preParentReg.setString(10, registration.getMotherMobileNo());
					preParentReg.setString(11, registration.getMotherOccupation());
					preParentReg.setString(12, registration.getMotherPanNo());
					preParentReg.setDouble(13, registration.getMotherAnnualIncome());
					preParentReg.setString(14, registration.getMotherEmail());
					preParentReg.setString(15, registration.getMotherQualification());
					preParentReg.setString(16, registration.getMotherPhotoUrl().trim());

					preParentReg.setString(17, registration.getGuardianName());
					preParentReg.setString(18, registration.getGuardianMobileNo());
					preParentReg.setString(19, registration.getGuardianOccupation());
					preParentReg.setString(20, registration.getGuardianPanNo());
					preParentReg.setString(21, registration.getGuardianQualification());
					preParentReg.setDouble(22, registration.getGuardianAnnualIncome());
					preParentReg.setString(23, registration.getGuardianEmail());
					preParentReg.setString(24, registration.getGuardianPhotoUrl().trim());

					preParentReg.setString(25, registration.getAddress());
					preParentReg.setString(26, registration.getPresentaddress());

					preParentReg.setString(27, guardianName.concat(parentUserName));
					preParentReg.setString(28, guardianName.concat(parentUserName));

					preParentReg.setString(29, registration.getModifyUser());
					preParentReg.setTimestamp(30, HelperClass.getCurrentTimestamp());
					preParentReg.setString(40, parentId);

					/*Thread.sleep(1000);
					if (registration.getGuardianEmail() == null) {
						new SendMail().sendMailtoChild(registration
								.getGuardianEmail().trim(), guardianName
								.concat(parentUserName), guardianName
								.concat(parentUserName));
					}*/
				}

				preParentReg.setString(31, registration.getFatherDepartment());
				preParentReg.setString(32, registration.getFatherDesignation());
				preParentReg.setString(33, registration.getFatherOfficeAddress());
				preParentReg.setString(34, registration.getMotherDepartment());
				preParentReg.setString(35, registration.getMotherDesignation());
				preParentReg.setString(36, registration.getMotherOfficeAddress());
				preParentReg.setString(37, registration.getGuardianDepartment());
				preParentReg.setString(38, registration.getGuardianDesignation());
				preParentReg.setString(39, registration.getGuardianOfficeAddress());
				//ln("update preParentReg "+preParentReg);

				preParentReg.executeUpdate(); 

				preParentReg.close();


			}



			preChildParentUpdate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_CHILD_UPDATE);

			if (registration.getPrimaryPerson().equalsIgnoreCase("father")) {

				relationship = "father";

			} else if (registration.getPrimaryPerson().equalsIgnoreCase("mother")) {

				relationship = "mother";

			} else {

				relationship = "guardian";

			}

			preChildParentUpdate.setString(1, relationship);
			preChildParentUpdate.setString(2, parentId.trim());
			preChildParentUpdate.setString(3, registration.getStudentId().trim());
			preChildParentUpdate.executeUpdate();


			/*ps_parentChaildsUpdate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_CHILDS_UPDATE);

				if (registration.getPrimaryPerson().equals("father")) {

					relationship = "father";

				} else if (registration.getPrimaryPerson().equals("mother")) {

					relationship = "mother";

				} else {

					relationship = "guardian";

				}

				ps_parentChaildsUpdate.setString(1, relationship);
				ps_parentChaildsUpdate.setString(2, parentId.trim());


				ps_parentChaildsUpdate.executeUpdate();

				//For enquiry update

				if (registration.getEnquiryId() != null) {

					PreparedStatement enquiryUpdate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_ENQUIRY_DETAILS);

					enquiryUpdate.setString(1, "Y");
					enquiryUpdate.setString(2, registration.getStudentId().trim());
					enquiryUpdate.setString(3, registration.getModifyUser().trim());
					enquiryUpdate.setTimestamp(4,HelperClass.getCurrentTimestamp());
					enquiryUpdate.setString(5, registration.getEnquiryId());


					enquiryUpdate.executeUpdate();


				}*/


			if(registration.getIstransferstudent().equalsIgnoreCase("Yes")) {
				PreparedStatement transeferdelete=conn.prepareStatement("DELETE FROM campus_student_transfer_fee_applicaple WHERE student_id_int=?");
				transeferdelete.setString(1, registration.getStudentId().trim());
				transeferdelete.executeUpdate();
				transeferdelete.close();
				PreparedStatement transeferpstmt=conn.prepareStatement("INSERT INTO campus_student_transfer_fee_applicaple (student_id_int,termid) VALUES(?,?)");
				for(int k=0;k<registration.getFeeapplicableterm().length;k++) {

					transeferpstmt.setString(1, registration.getStudentId().trim());
					transeferpstmt.setString(2, registration.getFeeapplicableterm()[k]);
					int stu_tranfer = transeferpstmt.executeUpdate();
					if(stu_tranfer > 0)
						HelperClass.recordLog_Activity(registration.getLog_audit_session(),"Student","Registration","Update",transeferpstmt.toString(),dbdetails);

				}

				transeferpstmt.close();
			}

			///pstm.executeUpdate();

			if (resultModify > 0) {

				successMsg = "successMessage";


			} else {

				successMsg = "errorMessage";
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		} finally {
			try {
				if (rsClass != null && (!rsClass.isClosed())) {
					rsClass.close();
				}
				if (rsImagePath != null && (!rsImagePath.isClosed())) {
					rsImagePath.close();
				}
				if (duplicateRs != null && (!duplicateRs.isClosed())) {
					duplicateRs.close();
				}
				if (rsacademicYearId != null && (!rsacademicYearId.isClosed())) {
					rsacademicYearId.close();
				}
				if (resultSetclassName != null
						&& (!resultSetclassName.isClosed())) {
					resultSetclassName.close();
				}
				/*if (pstm != null && (!pstm.isClosed())) {
					pstm.close();
				}*/
				if (preChildParentUpdate != null
						&& (!preChildParentUpdate.isClosed())) {
					preChildParentUpdate.close();
				}
				if (preParentRegUpdate != null
						&& (!preParentRegUpdate.isClosed())) {
					preParentRegUpdate.close();
				}

				if (getImagePath != null && (!getImagePath.isClosed())) {
					getImagePath.close();
				}
				if (rsacademicYear != null && (!rsacademicYear.isClosed())) {
					rsacademicYear.close();
				}



				if (pstmclasscount != null && (!pstmclasscount.isClosed())) {
					pstmclasscount.close();
				}
				if (academicYearpre != null && (!academicYearpre.isClosed())) {
					academicYearpre.close();
				}
				if (prclassName != null && (!prclassName.isClosed())) {
					prclassName.close();
				}

				if (pstmmodifyObj != null && (!pstmmodifyObj.isClosed())) {
					pstmmodifyObj.close();
				}
				if (pstmacademicYearId != null
						&& (!pstmacademicYearId.isClosed())) {
					pstmacademicYearId.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : modifyStudentDetails Ending");
		return successMsg;
	}


	//To search student details


	//To serch student
	@Override
	public List<StudentRegistrationVo> studentSearch(
			StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearch Starting");
		String searchTerm = "%" + registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmObj = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_STCLSE);

			pstmObj.setString(1, registrationVo.getAcademicYearId());

			if (registrationVo.getClassDetailId() != null) {
				pstmObj.setString(2, "%" + registrationVo.getClassDetailId()
				+ "%");
			} else {
				pstmObj.setString(2, "%%");
			}
			if (registrationVo.getClassSectionId() != null) {
				pstmObj.setString(3, "%" + registrationVo.getClassSectionId()
				+ "%");
			} else {
				pstmObj.setString(3, "%%");
			}
			pstmObj.setString(4, searchTerm);
			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setStudentnamelabel(rs
						.getString("student_fname_var"));
				studentRegistrationVo.setStudentidlabel(rs
						.getString("student_id_int"));

				registrationList.add(studentRegistrationVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearch Ending");

		return registrationList;

	}


	@Override
	public List<StudentRegistrationVo> searchStudentResult(StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentResult Starting");


		List<StudentRegistrationVo> studentVoList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAIL_BY_SEARCH_TERM);
			pstmObj.setString(1, registrationVo.getSearchTerm());

			//ln("pstmObj---------------->"+pstmObj);
			rs = pstmObj.executeQuery();

			while (rs.next()) {

				StudentRegistrationVo studentvo1 = new StudentRegistrationVo();


				studentvo1.setStreemname(rs.getString("classstream_name_var"));
				studentvo1.setClassname(rs.getString("classdetails_name_var"));
				studentvo1
				.setSectionnaem(rs.getString("classsection_name_var"));
				studentvo1
				.setStudentIDgenerator(registrationVo.getSearchTerm());
				studentvo1.setStudentAdmissionNo(rs
						.getString("student_admissionno_var"));
				studentvo1.setStudentFirstName(rs
						.getString("student_fname_var"));

				studentvo1.setApplicationNo(rs
						.getString("student_application_no"));
				studentvo1.setTransport(rs.getString("isTransport"));
				/*studentvo1.setHostel(rs.getString("isHostel"));*/

				studentvo1
				.setStudentLastName(rs.getString("student_lname_var"));
				studentvo1.setStudentrollno(rs.getString("student_rollno"));
				studentvo1.setDateofBirth(HelperClass
						.getDateFromSQLDateinDDMMYYYYFormat(rs
								.getDate("student_dob_var")));

				studentvo1.setAge(rs.getString("student_age_int"));

				studentvo1.setGender(rs.getString("student_gender_var"));
				studentvo1.setPhysicallyChallenged(rs
						.getString("student_physicallychallenged"));
				studentvo1
				.setBloodGroup(rs.getString("student_bloodgroup_var"));

				studentvo1.setIdentificationMarks(rs
						.getString("student_identificationmarks_var"));

				studentvo1.setNationality(rs
						.getString("student_nationality_var"));
				studentvo1.setReligion(rs.getString("student_religion_var"));
				studentvo1.setDateofJoin(HelperClass
						.getDateFromSQLDateinDDMMYYYYFormat(rs
								.getDate("student_doj_var")));
				studentvo1.setAcademicYearId(rs
						.getString("fms_acadamicyear_id_int"));
				studentvo1.setCategory(rs.getString("fms_classstream_id_int"));
				studentvo1.setStudClassId(rs.getString("classdetail_id_int"));
				studentvo1
				.setStudSectionId(rs.getString("classsection_id_int"));
				/*studentvo1.setGrade(rs.getString("student_grade"));
				studentvo1.setStudentquotaid(rs.getString("student_quota"));*/
				studentvo1.setStudentcastename(rs.getString("student_caste"));
				studentvo1.setStudentStatus(rs.getString("student_status_var"));
				studentvo1.setPromotionStatus(rs
						.getString("student_promotionstatus"));
				studentvo1.setPreviousHistory(rs
						.getString("student_prehistory_var"));
				studentvo1.setRemarks(rs.getString("student_remarks_var"));

				studentvo1
				.setFatherName(rs.getString("FatherName"));
				studentvo1.setFatherQualification(rs
						.getString("Qualification"));
				studentvo1.setFatherMobileNo(rs
						.getString("mobileno"));

				studentvo1
				.setMotherName(rs.getString("student_mothername_var"));
				studentvo1.setMotherMobileNo(rs
						.getString("student_mothermobileno_var"));
				studentvo1.setMotherQualification(rs
						.getString("student_motherqualification_var"));

				studentvo1.setGuardianName(rs
						.getString("student_gaurdianname_var"));
				studentvo1.setPrimaryPerson(rs.getString("relationship"));
				studentvo1.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentvo1.setStudentRegNo(rs.getString("student_regno_var"));
				studentvo1.setParentId(rs.getString("ParentID"));

				studentvo1
				.setStudentIDgenerator(rs.getString("student_id_int"));

				studentvo1.setFatheremailId(rs
						.getString("email"));
				studentvo1.setMotheremailId(rs
						.getString("student_mother_mailid"));
				studentvo1.setGuardianemailId(rs
						.getString("student_gardian_mailid"));
				studentvo1.setGuardianMobileNo(rs
						.getString("student_gardian_mobileno"));

				studentvo1.setTransporttypeId(rs.getString("type_id"));
				studentvo1.setTransporttypeName(rs.getString("type_name"));
				studentvo1.setTransportlocationId(rs.getString("stage_id"));
				studentvo1.setTransportlocationName(rs.getString("stage_name"));
				studentvo1.setTransportcollectType(rs
						.getString("type_collectFee"));

				if (rs.getString("student_siblingId") != null) {
					PreparedStatement pstm = conn
							.prepareStatement(SQLUtilConstants.GET_SIBLING_DETAILS);
					pstm.setString(1, registrationVo.getSearchTerm());
					ResultSet rst = pstm.executeQuery();
					while (rs.next()) {
						studentvo1.setSibilingClassId(rst
								.getString("classdetails_name_var"));
						studentvo1.setStudentSibilingIdInt(rst
								.getString("student_id_int"));
						studentvo1.setStudentSibilingRegNo(rst
								.getString("student_admissionno_var"));
						studentvo1.setStudentSibilingName(rst
								.getString("siblingName"));
					}
				} else {
					studentvo1.setSibilingClassId("");
					studentvo1.setStudentSibilingIdInt("");
					studentvo1.setStudentSibilingRegNo("");
					studentvo1.setStudentSibilingName("");
				}

				studentVoList.add(studentvo1);

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			;
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentResult Ending");
		return studentVoList;

	}


	@Override
	public List<StudentRegistrationVo> studentSearchByParent(
			StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearchByParent Starting");
		List<StudentRegistrationVo> studentVoList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);

			pstmObj = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_DETAIL_BY_PARENTID);
			pstmObj.setString(1, registrationVo.getSearchTerm());
			rs = pstmObj.executeQuery();

			while (rs.next()) {
				//classdetail_id_int,student_id_int,student_fname_var,student_admissionno_var,FatherName,Qualification,mobileno,student_mothername_var,
				//student_mothermobileno_var,student_gaurdianname_var,student_siblingId,parentid,classdetails_name_var,email,student_mother_mailid,
				//student_gardian_mobileno,student_gardian_mailid,relationship,student_imgurl_var,address,student_mother_occupation,student_father_occupation
				StudentRegistrationVo form = new StudentRegistrationVo();
				form.setSibilingClassId(rs.getString("classdetail_id_int"));
				form.setStudentSibilingIdInt(rs.getString("student_id_int"));
				form.setStudentFirstName(rs.getString("student_fname_var"));
				form.setSibilingadminno(rs.getString("student_admissionno_var"));
				form.setFatherName(rs.getString("FatherName"));
				form.setFatherQualification(rs.getString("Qualification"));
				form.setFatherMobileNo(rs.getString("mobileno"));
				form.setMotherName(rs.getString("student_mothername_var"));
				form.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				form.setMotherQualification(rs.getString("student_motherqualification_var"));
				form.setGaurdianName(rs.getString("student_gaurdianname_var"));
				form.setSibilingName(rs.getString("student_siblingId"));
				form.setParentId(rs.getString("parentid"));
				form.setSibilingClass(rs.getString("classdetails_name_var"));
				form.setFatheremailId(rs.getString("email"));
				form.setMotheremailId(rs.getString("student_mother_mailid"));
				form.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				form.setGuardianemailId(rs.getString("student_gardian_mailid"));
				form.setPrimaryPerson(rs.getString("relationship"));
				form.setStudentIDgenerator(rs.getString("student_id_int"));
				form.setImageFileName(rs.getString("student_imgurl_var"));
				form.setAddress(rs.getString("address"));
				form.setMotheroccupation(rs.getString("student_mother_occupation"));
				form.setFatheroccupation(rs.getString("student_father_occupation"));
				
				form.setFatherPanNo(rs.getString("fatherPanNo"));
				form.setMotherPanNo(rs.getString("motherPanNo"));

				studentVoList.add(form);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearchByParent Ending");
		return studentVoList;
	}


	public List<StudentRegistrationVo> studentSearchbySibling(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearchbySibling Starting");

		String searchTerm = registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmObj = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_SIBLING);

			pstmObj.setString(1, searchTerm);
			pstmObj.setString(2, searchTerm);
			pstmObj.setString(3, registrationVo.getLocationId());

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setStudentnamelabel(rs.getString("StudentName"));
				studentRegistrationVo.setStudentidlabel(rs.getString("student_id_int"));
				registrationList.add(studentRegistrationVo);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearchbySibling Ending");

		return registrationList;
	}

	public List<StudentRegistrationVo> studentSearchbyClass(
			StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearchbyClass Starting");

		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			String searchTerm = "%" + registrationVo.getSearchTerm() + "%";
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_CLASSNAME);

			pst.setString(1, registrationVo.getAcademicYearId());

			if (registrationVo.getClassDetailId() != null) {
				pst.setString(2, "%" + registrationVo.getClassDetailId() + "%");
			} else {
				pst.setString(2, "%%");
			}

			pst.setString(3, searchTerm);
			rs = pst.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setStudentnamelabel(rs
						.getString("student_fname_var"));
				studentRegistrationVo.setStudentidlabel(rs
						.getString("student_id_int"));

				registrationList.add(studentRegistrationVo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentSearchbyClass Ending");

		return registrationList;
	}


	//To delete student
	public boolean deactivateStudent(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl:deactivateStudent Starting");
		PreparedStatement pstmObj = null,pstm1=null,pstm2=null,pstm3=null,pstm4=null,pstm5=null,pstm6=null,pstm7=null;
		Connection conn = null;
		ResultSet rs2 = null,rs3 = null,rs4= null;
		int count = 0,count1 = 0,count2= 0;
		boolean status = false;
		int result = 0;
		String value=null,studentstatus=null,acadyear=null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			conn.setAutoCommit(false);
			for(int i =0;i<registrationVo.getStudentIdArray().length;i++){
				pstmObj = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DEACTIVATE);
				pstmObj.setString(1, registrationVo.getStatus());
				pstmObj.setString(2, registrationVo.getRemarks());
				pstmObj.setString(3, registrationVo.getStudentIdArray()[i]);
				//pstmObj.setString(4, registrationVo.getAccyear());
				//ln("STUDENT_DEACTIVATE -->>"+pstmObj);
				count = pstmObj.executeUpdate();

				if (count > 0) {
					HelperClass.recordLog_Activity(registrationVo.getLog_audit_session(),"Student","Registration",registrationVo.getStatus(),pstmObj.toString(),pojo);
				}

				pstm1 = conn.prepareStatement("UPDATE campus_student_classdetails SET student_status=? WHERE student_id_int=?"); /*AND fms_acadamicyear_id_int=?*/

				if(registrationVo.getStatus().equalsIgnoreCase("inactive")){

					pstm1.setString(1, "INACTIVE");
					pstm1.setString(2, registrationVo.getStudentIdArray()[i]);
					//pstm1.setString(3, registrationVo.getAccyear());
					//ln("STUDENT_DEACTIVATE -->>"+pstm1);
					count2=pstm1.executeUpdate();

				}else{
					pstm2 = conn.prepareStatement("SELECT COUNT(student_id_int) FROM  campus_student_promotion  WHERE student_id_int=?");
					pstm2.setString(1, registrationVo.getStudentIdArray()[i]);
					//ln("STUDENT_DEACTIVATE  count1="+count1+" -->>"+pstm2);
					rs2 = pstm2.executeQuery();

					while(rs2.next()){
						count1=rs2.getInt(1);
					}

					if(count1>0){
						pstm3 = conn.prepareStatement("SELECT studentpromotion_acadamicyearfrom_var,studentpromotion_status FROM campus_student_promotion WHERE student_id_int=?");
						pstm3.setString(1, registrationVo.getStudentIdArray()[i]);
						rs3 = pstm3.executeQuery();
						while(rs3.next()){
							studentstatus=rs3.getString("studentpromotion_status");
							acadyear=rs3.getString("studentpromotion_acadamicyearfrom_var");

							if(studentstatus.equalsIgnoreCase("promoted")){
								value="PASSED";
							}else if(studentstatus.equalsIgnoreCase("demoted")){
								value="FAILED";
							}
							pstm4 = conn.prepareStatement("UPDATE campus_student_classdetails SET student_status=? WHERE student_id_int=? AND fms_acadamicyear_id_int=? ");
							pstm4.setString(1, value);
							pstm4.setString(2, registrationVo.getStudentIdArray()[i]);
							pstm4.setString(3, acadyear);
							//ln("STUDENT_DEACTIVATE UPDATE1  value="+value+" -->>"+pstm4);
							count2=pstm4.executeUpdate();

							if (count2 > 0) {
								HelperClass.recordLog_Activity(registrationVo.getLog_audit_session(),"Student","Registration",registrationVo.getStatus(),pstm4.toString(),pojo);
							}
						}

						pstm5 = conn.prepareStatement("SELECT MAX(studentpromotion_acadamicyearto_var) FROM campus_student_promotion WHERE student_id_int=?");
						pstm5.setString(1, registrationVo.getStudentIdArray()[i]);
						rs4 = pstm5.executeQuery();
						while(rs4.next()){
							acadyear=rs4.getString(1);
							value="STUDYING";

							pstm6 = conn.prepareStatement("UPDATE campus_student_classdetails SET student_status=? WHERE student_id_int=? AND fms_acadamicyear_id_int=? ");
							pstm6.setString(1, value);
							pstm6.setString(2, registrationVo.getStudentIdArray()[i]);
							pstm6.setString(3, acadyear);
							//ln("STUDENT_DEACTIVATE UPDATE2  value="+value+" -->>"+pstm6);
							count2=pstm6.executeUpdate();

							if (count2 > 0) {
								HelperClass.recordLog_Activity(registrationVo.getLog_audit_session(),"Student","Registration",registrationVo.getStatus(),pstm6.toString(),pojo);
							}
						}

					}else{
						pstm7 = conn.prepareStatement("UPDATE campus_student_classdetails SET student_status=? WHERE student_id_int=? AND fms_acadamicyear_id_int=? ");
						value="STUDYING";
						pstm7.setString(1, value);
						pstm7.setString(2, registrationVo.getStudentIdArray()[i]);
						pstm7.setString(3, registrationVo.getAccyear());
						//ln("STUDENT_DEACTIVATE UPDATE3  value="+value+" -->>"+pstm7);
						count2=pstm7.executeUpdate();
						if (count2 > 0) {
							HelperClass.recordLog_Activity(registrationVo.getLog_audit_session(),"Student","Registration",registrationVo.getStatus(),pstm7.toString(),pojo);
						}
					}
				}

				if (count2 > 0) {
					HelperClass.recordLog_Activity(registrationVo.getLog_audit_session(),"Student","Registration",registrationVo.getStatus(),pstm1.toString(),pojo);
					result++;
				}
			}
			if (result == registrationVo.getStudentIdArray().length) {
				conn.commit();
				status = true;
			}else{
				conn.rollback();
				status = false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstm7 != null && (!pstm7.isClosed())) {
					pstm7.close();
				}
				if (pstm6 != null && (!pstm6.isClosed())) {
					pstm6.close();
				}
				if (rs4 != null && (!rs4.isClosed())) {
					rs4.close();
				}
				if (pstm5 != null && (!pstm5.isClosed())) {
					pstm5.close();
				}
				if (pstm4 != null && (!pstm4.isClosed())) {
					pstm4.close();
				}
				if (rs3 != null && (!rs3.isClosed())) {
					rs3.close();
				}
				if (pstm3 != null && (!pstm3.isClosed())) {
					pstm3.close();
				}
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (pstm2 != null && (!pstm2.isClosed())) {
					pstm2.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (pstm1 != null && (!pstm1.isClosed())) {
					pstm1.close();
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
				+ " Control in StudentRegistrationDaoImpl:deactivateStudent  Ending");
		return status;
	}

	//To get student edit details



	//To edit student

	public StudentRegistrationVo editStudent(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : editStudent Starting");

		StudentRegistrationVo studentvo1 = new StudentRegistrationVo();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;

		try {

			//ln("Student Id "+registrationVo.getSearchTerm());
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAIL_BY_SEARCH_TERM);
			pstmObj.setString(1, registrationVo.getStatus());
			pstmObj.setString(2, registrationVo.getSearchTerm());
			pstmObj.setString(3, registrationVo.getAcademicYear());

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				studentvo1.setStudentId(rs.getString("student_id_int"));
				studentvo1.setStudentrollno(rs.getString("student_rollno"));
				studentvo1.setHouseId(rs.getString("student_house"));
				studentvo1.setApplicationNo(rs.getString("student_application_no"));
				studentvo1.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentvo1.setCategory(rs.getString("fms_classstream_id_int"));
				studentvo1.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentvo1.setStudClassId(rs.getString("classdetail_id_int"));
				studentvo1.setStudSectionId(rs.getString("classsection_id_int"));
				studentvo1.setStudentRegNo(rs.getString("student_regno_var"));
				studentvo1.setStudentFirstName(rs.getString("student_fname_var"));
				studentvo1.setStudentLastName(rs.getString("student_lname_var"));
				studentvo1.setDateofJoin(HelperClass.convertDatabaseToUI(rs.getString("student_doj_var")));
				studentvo1.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentvo1.setTransport(rs.getString("isTransport"));
				studentvo1.setTransferfile(rs.getString("student_tc_path"));
				studentvo1.setTransporttypeId(rs.getString("type_id"));
				studentvo1.setTransporttypeName(rs.getString("type_name"));
				studentvo1.setTransportlocationId(rs.getString("stage_id"));
				studentvo1.setTransportlocationName(rs.getString("stage_name"));
				studentvo1.setTransportcollectType(rs.getString("type_collectFee"));
				studentvo1.setStreemname(rs.getString("classstream_name_var"));
				studentvo1.setClassname(rs.getString("classdetails_name_var"));
				studentvo1.setSectionnaem(rs.getString("classsection_name_var"));
				studentvo1.setRoute(rs.getString("route"));
				studentvo1.setSchoolLocation(rs.getString("locationId"));
				studentvo1.setIsWorkingTeacherGuardian(rs.getString("isParentsGuardianWorking"));
				studentvo1.setWorkingTeacherGuardianId(rs.getString("workingParentsGuardianId"));
				studentvo1.setWorkingTeacherName(rs.getString("workingParentsGuardianName"));
				studentvo1.setMedium(rs.getString("medium"));
				studentvo1.setSpecilization(rs.getString("specilization"));
				studentvo1.setTransferCertificateNo(rs.getString("transferCertificateNo"));
				studentvo1.setFirstlang(rs.getString("firstlanguage"));
				studentvo1.setSecondlang(rs.getString("secondlanguage"));
				studentvo1.setThirdlang(rs.getString("thirdlanguage"));
				studentvo1.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				studentvo1.setGender(rs.getString("student_gender_var"));
				studentvo1.setAge(rs.getString("student_age_int"));
				studentvo1.setBloodGroup(rs.getString("student_bloodgroup_var"));
				studentvo1.setNationality(rs.getString("student_nationality_var"));
				studentvo1.setMothertongue(rs.getString("motherTounge"));
				studentvo1.setAadharno(rs.getString("adharNo"));
				studentvo1.setPhysicallyChallenged(rs.getString("student_physicallychallenged"));
				studentvo1.setPhysicalchalreason(rs.getString("physicallychallenged_reason"));
				studentvo1.setReligion(rs.getString("student_religion_var"));
				studentvo1.setStudentcastename(rs.getString("student_caste"));
				studentvo1.setCasteCategory(rs.getString("casteCategory"));
				studentvo1.setStudentStatus(rs.getString("student_status_var"));
				studentvo1.setIdentificationMarks(rs.getString("student_identificationmarks_var"));
				studentvo1.setIdentificationMarks1(rs.getString("student_identificationmarks1_var"));
				studentvo1.setPreviousHistory(rs.getString("student_prehistory_var"));
				studentvo1.setRemarks(rs.getString("student_remarks_var"));

				//certificate1,certificate2,certificate3,certificate4,certificate5,student_tc_path,student_birthcert_path
				studentvo1.setCertificate1(rs.getString("certificate1"));
				studentvo1.setCertificate2(rs.getString("certificate2"));
				studentvo1.setCertificate3(rs.getString("certificate3"));
				studentvo1.setCertificate4(rs.getString("certificate4"));
				studentvo1.setCertificate5(rs.getString("certificate5"));
				studentvo1.setBirthcertificate(rs.getString("student_birthcert_path"));
				studentvo1.setIstransferstudent(rs.getString("isTransferInMiddle"));
				studentvo1.setTrnsnoreason(rs.getString("trans_no_reason"));
				//ParentID,FatherName,mobileno,student_father_occupation,fatherPanNo,fatherAnnualIncome,email,Qualification,fatherPhoto,
				//student_mothername_var,student_mothermobileno_var,student_mother_occupation,motherPanNo,motherAnnualIncome,student_mother_mailid,
				//student_motherqualification_var,motherPhoto,
				//student_gaurdianname_var,student_gardian_mobileno,guardianOccupation,guardianPanNo,guardianAnnualIncome,guardianQualification,
				//student_gardian_mailid,guardianPhoto,
				//address,presentAddress,relationship
				studentvo1.setParentRelationship(rs.getString("relationship"));
				studentvo1.setParentId(rs.getString("ParentID"));
				studentvo1.setFatherName(rs.getString("FatherName"));
				studentvo1.setFatherMobileNo(rs.getString("mobileno"));
				studentvo1.setFatheroccupation(rs.getString("student_father_occupation"));
				studentvo1.setFatherPanNo(rs.getString("fatherPanNo"));
				studentvo1.setFatherAnnualIncome(rs.getDouble("fatherAnnualIncome"));
				studentvo1.setFatheremailId(rs.getString("email"));
				studentvo1.setFatherQualification(rs.getString("Qualification"));
				studentvo1.setFatherPhoto(rs.getString("fatherPhoto"));
				studentvo1.setMotherName(rs.getString("student_mothername_var"));
				studentvo1.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentvo1.setMotheroccupation(rs.getString("student_mother_occupation"));
				studentvo1.setMotherPanNo(rs.getString("motherPanNo"));
				studentvo1.setMotherAnnualIncome(rs.getDouble("motherAnnualIncome"));
				studentvo1.setMotheremailId(rs.getString("student_mother_mailid"));
				studentvo1.setMotherQualification(rs.getString("student_motherqualification_var"));
				studentvo1.setMotherPhoto(rs.getString("motherPhoto"));
				studentvo1.setGuardianName(rs.getString("student_gaurdianname_var"));
				studentvo1.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				studentvo1.setGuardianOccupation(rs.getString("guardianOccupation"));
				studentvo1.setGuardianPanNo(rs.getString("guardianPanNo"));
				studentvo1.setGuardianAnnualIncome(rs.getDouble("guardianAnnualIncome"));
				studentvo1.setGuardianQualification(rs.getString("guardianQualification"));
				studentvo1.setGuardianemailId(rs.getString("student_gardian_mailid"));
				studentvo1.setGuardianPhoto(rs.getString("guardianPhoto"));
				studentvo1.setAddress(rs.getString("address"));
				studentvo1.setPresentaddress(rs.getString("presentAddress"));
				studentvo1.setPrimaryPerson(rs.getString("relationship"));
				studentvo1.setEmergencynumber(rs.getString("emergencyNo"));
				studentvo1.setSmsnumber(rs.getString("smsNo"));
				studentvo1.setFatherDepartment(rs.getString("fatherDepartment"));
				studentvo1.setMotherDepartment(rs.getString("motherDepartment"));
				studentvo1.setFatherDesignation(rs.getString("fatherDesignation"));
				studentvo1.setFatherOfficeAddress(rs.getString("fatherOfficeAddress"));
				studentvo1.setMotherDesignation(rs.getString("motherDesignation"));
				studentvo1.setMotherOfficeAddress(rs.getString("motherOfficeAddress"));
				studentvo1.setGuardianDepartment(rs.getString("guardianDepartment"));
				studentvo1.setGuardianDesignation(rs.getString("guardianDesignation"));
				studentvo1.setGuardianOfficeAddress(rs.getString("guardianOfficeAddress"));
				/*studentvo1.setConcessionType(rs.getString("fee_concession_type"));
				if (rs.getString("isConcession").equalsIgnoreCase("Y")) {
					studentvo1.setFeeConcession("Yes");
				} else {
					studentvo1.setFeeConcession("No");
				}*/
				/*studentvo1.setAcademicYear(rs.getString("acadamic_year"));
				studentvo1.setStreemname(rs.getString("classstream_name_var"));
				studentvo1.setClassname(rs.getString("classdetails_name_var"));
				studentvo1.setSectionnaem(rs.getString("classsection_name_var"));
				studentvo1.setStudentIDgenerator(registrationVo.getSearchTerm());
				studentvo1.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentvo1.setStudentFirstName(rs.getString("student_fname_var"));
				studentvo1.setApplicationNo(rs.getString("student_application_no"));
				studentvo1.setTransport(rs.getString("isTransport"));
				//studentvo1.setHostel(rs.getString("isHostel"));
				studentvo1.setStudentLastName(rs.getString("student_lname_var"));
				studentvo1.setStudentrollno(rs.getString("student_rollno"));
				studentvo1.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				studentvo1.setAge(rs.getString("student_age_int"));
				studentvo1.setGender(rs.getString("student_gender_var"));
				studentvo1.setPhysicallyChallenged(rs.getString("student_physicallychallenged"));
				studentvo1.setBloodGroup(rs.getString("student_bloodgroup_var"));
				studentvo1.setIdentificationMarks(rs.getString("student_identificationmarks_var"));
				studentvo1.setNationality(rs.getString("student_nationality_var"));
				studentvo1.setReligion(rs.getString("student_religion_var"));
				studentvo1.setDateofJoin(HelperClass.convertDatabaseToUI(rs.getString("student_doj_var")));
				studentvo1.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentvo1.setCategory(rs.getString("fms_classstream_id_int"));
				studentvo1.setStudClassId(rs.getString("classdetail_id_int"));
				studentvo1.setStudSectionId(rs.getString("classsection_id_int"));
				//studentvo1.setGrade(rs.getString("student_grade"));
				//studentvo1.setAadharno(rs.getString("Aadhaar_No"));
				//studentvo1.setParent_annulIncome(rs.getString("Annual_parentIncome"));
				studentvo1.setMothertongue(rs.getString("mothertongue"));
				studentvo1.setMedium(rs.getString("medium"));
				//ln("DIOMPL: medium "+rs.getString("medium"));
				//studentvo1.setStudentquotaid(rs.getString("student_quota"));
				studentvo1.setStudentcastename(rs.getString("student_caste"));
				studentvo1.setStudentStatus(rs.getString("student_status_var"));
				studentvo1.setPromotionStatus(rs.getString("student_promotionstatus"));
				//studentvo1.setPreviousHistory(rs.getString("student_prehistory_var"));
				studentvo1.setRemarks(rs.getString("student_remarks_var"));
				//studentvo1.setStudentquotaname(rs.getString("student_quota"));
				studentvo1.setStudentcastename(rs.getString("student_caste"));
				studentvo1.setFatherName(rs.getString("FatherName"));
				studentvo1.setFatherQualification(rs.getString("Qualification"));
				studentvo1.setFatherMobileNo(rs.getString("mobileno"));
				studentvo1.setMotherName(rs.getString("student_mothername_var"));
				studentvo1.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentvo1.setMotherQualification(rs.getString("student_motherqualification_var"));
				studentvo1.setGuardianName(rs.getString("student_gaurdianname_var"));
				studentvo1.setPrimaryPerson(rs.getString("relationship"));
				studentvo1.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentvo1.setStudentRegNo(rs.getString("student_regno_var"));
				studentvo1.setParentId(rs.getString("ParentID"));
				studentvo1.setStudentIDgenerator(rs.getString("student_id_int"));
				studentvo1.setFatheremailId(rs.getString("email"));
				studentvo1.setMotheremailId(rs.getString("student_mother_mailid"));
				studentvo1.setGuardianemailId(rs.getString("student_gardian_mailid"));
				studentvo1.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				studentvo1.setIsconcession(rs.getString("isConcession"));

				studentvo1.setImageFileName(rs.getString("student_imgurl_var"));
				studentvo1.setBirthcertificate(rs.getString("student_birthcert_path"));
				studentvo1.setTransferfile(rs.getString("student_tc_path"));

				studentvo1.setAddress(rs.getString("address"));
				studentvo1.setMotheroccupation(rs.getString("student_mother_occupation"));
				studentvo1.setFatheroccupation(rs.getString("student_father_occupation"));
				studentvo1.setRoute(rs.getString("route"));
				//studentvo1.setEmisNo(rs.getString("emisNo"));
				studentvo1.setStudentId(registrationVo.getSearchTerm());

				////ln("  emis No: in DIOMPL : "+rs.getString("emisNo"));


				if (rs.getString("isConcession").equalsIgnoreCase("Y")) {

					studentvo1.setScholarShip(rs
							.getString("student_scholorship_var"));
				} else {

					studentvo1.setScholarShip("");
				}

				//studentvo1.setRte(rs.getString("isRTE").trim());
				studentvo1.setTransporttypeId(rs.getString("type_id"));
				studentvo1.setTransporttypeName(rs.getString("type_name"));
				studentvo1.setTransportlocationId(rs.getString("stage_id"));
				studentvo1.setTransportlocationName(rs.getString("stage_name"));
				studentvo1.setTransportcollectType(rs.getString("type_collectFee"));*/


				if (rs.getString("student_siblingId") != null) {

					PreparedStatement pstm = conn.prepareStatement(SQLUtilConstants.GET_SIBLING_DETAILS);
					pstm.setString(1, registrationVo.getSearchTerm());


					ResultSet rst = pstm.executeQuery();

					while (rst.next()) {

						studentvo1.setSibilingClassId(rst.getString("classdetails_name_var"));
						studentvo1.setStudentSibilingIdInt(rst.getString("siblingId"));
						studentvo1.setStudentSibilingRegNo(rst.getString("siblingAdmissionNo"));
						studentvo1.setStudentSibilingName(rst.getString("siblingName"));
					}

					rst.close();
					pstm.close();


				} else {


					studentvo1.setSibilingClassId("");
					studentvo1.setStudentSibilingIdInt("");
					studentvo1.setStudentSibilingRegNo("");
					studentvo1.setStudentSibilingName("");
				}

				if(rs.getString("isTransferInMiddle").equalsIgnoreCase("Yes")){
					ArrayList<String> termList=new ArrayList<String>();
					String termListString="";
					PreparedStatement pstm1 = conn.prepareStatement("SELECT * FROM `campus_student_transfer_fee_applicaple` WHERE student_id_int=?");
					pstm1.setString(1, registrationVo.getSearchTerm());

					ResultSet rst1 = pstm1.executeQuery();

					while (rst1.next()) {
						termList.add(rst1.getString("termid"));
						termListString=termListString+","+rst1.getString("termid");

					}
					rst1.close();
					pstm1.close();
					studentvo1.setFeeapplicableTerm(termListString.split(","));
					studentvo1.setFeeapplicableterm(termList);

				}
				int stcount = 0;
				String setst = null;
				PreparedStatement getstucount = conn.prepareStatement("SELECT COUNT(*) FROM `campus_student_classdetails` WHERE `student_id_int` = ?"); 
				getstucount.setString(1, registrationVo.getSearchTerm());
				ResultSet getrsst = getstucount.executeQuery();
				while(getrsst.next()){
					stcount = getrsst.getInt(1);
				}
				if(stcount > 1){
					setst = "notallow";
				}else{
					setst = "allow";
				}
				studentvo1.setAllowedmodi(setst);
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			;
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : editStudent Ending");
		return studentvo1;

	}

	//To get student list for listing page


	//Get student list
	@Override
	public List<registrationvo> StudentDetails(String academic_year, String location, UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<registrationvo> list = new ArrayList<registrationvo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		try {
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ALLSTUDENT_DETAILS_WITH_SCHOOL_LOGO); /* GET_ALLSTUDENT_DETAILS*/

			pst.setString(1, academic_year);
			pst.setString(2, "%"+location+"%");
			rs = pst.executeQuery();
			while (rs.next()) {
				registrationvo registrationVo = new registrationvo();
				sno++;
				registrationVo.setSno(sno);
				registrationVo.setLocation(rs.getString("Location_Name"));
				registrationVo.setAddress(rs.getString("Location_Address"));
				registrationVo.setSchoolLogo(rs.getString("schoollogo"));

				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs
						.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassSectionId(rs.getString("classsection"));
				registrationVo.setDateofBirth(HelperClass
						.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs
						.getString("student_status"));
				list.add(registrationVo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentDetails1(String userType, String userCode, String academic_year,String schoolLocation, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails1  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			if(userType.equalsIgnoreCase("perent")){
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_PARENT_STUDENT_DETAILS);
				pst.setString(1, userCode);
			}
			else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ALLSTUDENT_DETAILS);
				pst.setString(1, academic_year);
				pst.setString(2, schoolLocation);

				//ln("pst is "+pst);

			}
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSection_id(rs.getString("classsection_id_int"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassSectionId(rs.getString("classsection"));

				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails1  Ending");

		return list;
	}

	public ArrayList<StudentInfoVO> getAllStudentsDetails(String classname,
			String accodamicyr,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAllStudentsDetails Starting");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<StudentInfoVO> studentList = new ArrayList<StudentInfoVO>();
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_DETAILS);
			preparedStatement.setString(1, classname);
			preparedStatement.setString(2, accodamicyr);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				StudentInfoVO vo = new StudentInfoVO();

				vo.setId(rs.getString("student_id_int"));
				vo.setAdmissionno(rs.getString("student_admissionno_var"));
				vo.setName(rs.getString("student_fname_var"));

				studentList.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : getAllStudentsDetails Ending");
		return studentList;
	}


	public List<StudentRegistrationVo> getTranscationcategory(String transloc)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTranscationcategory  Starting");
		List<StudentRegistrationVo> desstudentlist = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_DESTINATIONWISE_STUDENT_DETAILS);
			pst.setString(1,transloc);

			//ln("What destination wise student is printing:" +pst);
			rs = pst.executeQuery();
			//ln("What it is printing inside:" +pst);
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();

				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setStudentLastName(rs.getString("Studentname"));
				registrationVo.setStudClassId(rs.getString("classdetail_id_int"));
				registrationVo.setStage_name(rs.getString("stage_name"));
				registrationVo.setStage_id(rs.getString("stage_id"));
				desstudentlist.add(registrationVo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getTranscationcategory  Ending");

		return desstudentlist;

	}


	public List<StudentRegistrationVo> getStudentDetails(String searchName, String location,UserLoggingsPojo dbinfo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl :getStudentDetails Starting");

		List<StudentRegistrationVo> searchlist = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;
		String accyear=location.split(",")[0];
		try {

			conn = JDBCConnection.getSeparateConnection(dbinfo);
			pstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.SEARCH_STUDENT_DETAILS);

			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			pstmt.setString(4, "%" + searchName + "%");
			pstmt.setString(5, "%" + searchName + "%");
			pstmt.setString(6, "%" + searchName + "%");
			pstmt.setString(7, "%" + searchName + "%");
			pstmt.setString(8, "%" + searchName + "%");
			pstmt.setString(9, "%" + searchName + "%");
			pstmt.setString(10, "%" + searchName + "%");
			pstmt.setString(11,location.split(",")[1]);
			pstmt.setString(12,accyear);

			//ln("SEARCH_STUDENT_DETAILS -->>"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				sno++;

				StudentRegistrationVo objvo = new StudentRegistrationVo();

				objvo.setSno(String.valueOf(sno));


				String studentname = (rs.getString("student_fname_var"))+" " + (rs.getString("student_lname_var"));
				String calssname  =  (rs.getString("classdetails_name_var"))+"-" + (rs.getString("classsection_name_var"));
				objvo.setStudentId(rs.getString("student_id_int"));
				objvo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				objvo.setStudentnamelabel(studentname);
				objvo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				objvo.setStudentStatus(rs.getString("student_status_var"));
				objvo.setClassSectionId(calssname);
				objvo.setAcademicYear(rs.getString("acadamic_year"));
				/*objvo.setStudentnamelabel(rs.getString("student_fname_var"));
				objvo.setStudentnamelabel(rs.getString("student_lname_var"));*/



				searchlist.add(objvo);

			}



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : getStudentDetails Ending");

		return searchlist;
	}


	public String getStudentName(String admissionno,UserLoggingsPojo userLoggingsVo) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String StaffStudent=null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STAFF_STUDENT_DETAILS);
			pst.setString(1,admissionno);

			//ln(pst);
			rs = pst.executeQuery();

			while (rs.next()) {

				StaffStudent= rs.getString("student");
				//(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getChildCategory  Ending");

		return StaffStudent;
	}

	public String getSingleAcademicYear(String academicYear, UserLoggingsPojo userLoggingsVo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAcademicYear Starting");

		/*	List<StudentRegistrationVo> studentRegistrationVos = new ArrayList<StudentRegistrationVo>();*/
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		String academicname=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			preparedStatement = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.GETSINGLEACADEMICYEAR);
			preparedStatement.setString(1, academicYear);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setAcademicYear(resultSet
						.getString(MessageConstants.AcademicYear));
				studentRegistrationVo.setAcademicYearId(resultSet
						.getString(MessageConstants.AcademicYearId));

				academicname = resultSet
						.getString(MessageConstants.AcademicYear);


			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAcademicYear Ending");
		return academicname;
	}

	public List<StudentRegistrationVo> getClassDetailWithOutStream(String location, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CLASS_LIST);
			pst.setString(1, location);
			//ln(pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getClassDetailsByTemp() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_LIST_OF_I_TO_X);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getClassDetailSrSecondary() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_LIST_OF_XI_TO_XII);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getTempRegistrationDetails(StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_TEMP_STUDENT_DETAILSLIST);
			pst.setString(1, registrationVo.getTempregid().trim());
			//ln("GET_TEMP_STUDENT_DETAILSLIST......"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {

				registrationVo.setLocationId(rs.getString("LocId"));
				registrationVo.setTempregid(rs.getString("temporary_admission_id"));
				registrationVo.setStudentFirstName(rs.getString("studentfirstName"));
				registrationVo.setStudentLastName(rs.getString("studentlastname"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("dateofBirthId").trim()));
				registrationVo.setGender(rs.getString("gender"));
				registrationVo.setNationality(rs.getString("nationality"));
				registrationVo.setReligion(rs.getString("religion"));
				registrationVo.setCaste(rs.getString("caste"));
				registrationVo.setMothertongue(rs.getString("mothertongue"));
				registrationVo.setAadharno(rs.getString("aadharNo"));
				registrationVo.setAddress(rs.getString("permanentaddress"));
				registrationVo.setPresentaddress(rs.getString("addressofcommunication"));
				registrationVo.setClassname(rs.getString("classname"));

				registrationVo.setSibilingClassId(rs.getString("siblingid"));
				registrationVo.setSibilingName(rs.getString("siblingname"));

				registrationVo.setFatherName(rs.getString("fathername"));
				registrationVo.setFatherMobileNo(rs.getString("fathermobileno"));
				registrationVo.setFatherQualification(rs.getString("fatherqualification"));
				registrationVo.setFatheroccupation(rs.getString("fatheroccupation"));
				registrationVo.setFatherDepartment(rs.getString("fatherdepartment"));
				registrationVo.setFatherDesignation(rs.getString("fatherdesignation"));
				registrationVo.setFatherOfficeAddress(rs.getString("fatherofficialaddress"));
				registrationVo.setFatherAnnualIncome(rs.getDouble("fathermonthincome"));
				registrationVo.setFatheremailId(rs.getString("fatheremailid"));

				registrationVo.setMotherName(rs.getString("mothername"));
				registrationVo.setMotherMobileNo(rs.getString("mothermobile"));
				registrationVo.setMotherQualification(rs.getString("motherqualification"));
				registrationVo.setMotheroccupation(rs.getString("motheroccupation"));
				registrationVo.setMotherDepartment(rs.getString("motherdepartment"));
				registrationVo.setMotherDesignation(rs.getString("motherdesignation"));
				registrationVo.setMotherOfficeAddress(rs.getString("motherofficialaddress"));
				registrationVo.setMotherAnnualIncome(rs.getDouble("mothermothlyincome"));
				registrationVo.setMotheremailId(rs.getString("motheremailid"));
				registrationVo.setBirthcertificate(rs.getString("BirthCertificateFile"));
				DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date d = f.parse(rs.getString("createdTime"));
				DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
				registrationVo.setCreateDate(date.format(d));
				registrationVo.setImageFileName(rs.getString("imageUrl"));
				registrationVo.setEnquiryId(rs.getString("enquiryid"));
				registrationVo.setStreemcode(rs.getString("stream"));
				registrationVo.setCasteCategory(rs.getString("castecategory"));
				registrationVo.setSpecilization(rs.getString("group_prefered"));
				registrationVo.setPrimaryPerson(rs.getString("relationship"));
				registrationVo.setSmsnumber(rs.getString("preferedphno"));

				registrationVo.setMothertongue(rs.getString("mothertongue"));

				list.add(registrationVo);
			}


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getClassDetailMontessori() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_LIST_OF_LKG);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getTransportStudentDetails(
			String userType, String userCode, String param) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		String academic_year=param.split(",")[0];
		String locationId=param.split(",")[1];
		try {
			conn = JDBCConnection.getSeparateConnection();
			if(userType.equalsIgnoreCase("perent")){
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_PARENT_STUDENT_DETAILS);
				pst.setString(1, userCode);
			}
			else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_TRANSPORT_ALL_STUDENT);
				pst.setString(1, academic_year);
				pst.setString(2, locationId);
			}
			//ln("transport"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassSectionId(rs.getString("classsection"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getTransportStudentDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getTransportStudentDetails(
			String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStreamDetailsDao Starting");

		List<StudentRegistrationVo> searchlist = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.SEARCH_TRANSPORT_STUDENT);

			pstmt.setString(1, "%" + searchTerm + "%");
			pstmt.setString(2, "%" + searchTerm + "%");
			pstmt.setString(3, "%" + searchTerm + "%");
			pstmt.setString(4, "%" + searchTerm + "%");
			pstmt.setString(5, "%" + searchTerm + "%");
			pstmt.setString(6, "%" + searchTerm + "%");
			pstmt.setString(7, "%" + searchTerm + "%");
			pstmt.setString(8, "%" + searchTerm + "%");
			pstmt.setString(9, "%" + searchTerm + "%");
			pstmt.setString(10, "%" + searchTerm + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;
				StudentRegistrationVo objvo = new StudentRegistrationVo();

				objvo.setSno(String.valueOf(sno));
				String studentname = (rs.getString("student_fname_var"))+" " + (rs.getString("student_lname_var"));
				String calssname  =  (rs.getString("classdetails_name_var"))+"-" + (rs.getString("classsection_name_var"));
				objvo.setStudentId(rs.getString("student_id_int"));
				objvo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				objvo.setStudentnamelabel(studentname);
				objvo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				objvo.setStudentStatus(rs.getString("student_status_var"));
				objvo.setClassSectionId(calssname);
				objvo.setAcademicYear(rs.getString("acadamic_year"));

				searchlist.add(objvo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : searchStreamDetailsDao Ending");

		return searchlist;
	}


	public List<StudentRegistrationVo> getStudentList(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		ResultSet rst = null;

		Connection conn = null;
		int sno=0;
		String academicYear = academic_year;
		String locationName = location;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST);
			pst.setString(1, locationName);
			pst.setString(2, academicYear);
			////ln("GET_STUDENT_LIST -->>"+pst);
			rs = pst.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setClassStreamId(rs.getString("fms_classstream_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSpecilization(rs.getString("specilization"));


				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}
				}
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentList  Ending");

		return list;
	}


	public List<StudentRegistrationVo> getClassByLocation(String locationid, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getClassByLocation  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_CLASS_BY_LOCATION);
			pst.setString(1, locationid.trim());
			////ln("STUDENT_CLASS_BY_LOCATION -->>"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getClassByLocation  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getAllStudentDetails(String academic_year, String location, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAllStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_PROMOTION_DEMOTION_ALL);
			pst.setString(1, "%"+location.trim()+"%");
			pst.setString(2, academic_year);
			//ln("student class "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}

				registrationVo.setStudentStatus(rs.getString("student_promotionstatus"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setCurrentAccyearId(rs.getString("acadamic_id"));
				String accyearid=getNextAcademicYearId(rs.getString("acadamic_id"),custdetails);
				registrationVo.setAcademicYearId(accyearid);
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getAllStudentDetails  Ending");


		return list;
	}

	public List<StudentRegistrationVo> studentFullList(String studentId, String accYear,String locationId, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentFullList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null,pstmObj1 = null;
		ResultSet rs = null,rs1 = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;
		int conf_count = 0;
		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_LIST_BY_SEARCH_TERM);
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);
			rs = pstmObj.executeQuery();
			while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
				conf_count = rs.getInt(1);
				count++;

				studentRegVo.setCount(count);
				studentRegVo.setStatus(rs.getString("student_status_var"));
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentRegVo.setClassDetailId(rs.getString("classdetail_id_int"));
				studentRegVo.setClassSectionId(rs.getString("classsection_id_int"));
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				studentRegVo.setRoute(rs.getString("RouteName"));
				studentRegVo.setStudentStatus(rs.getString("student_status"));
				studentRegVo.setLocation(rs.getString("Location_Name"));
				studentRegVo.setStage_name(rs.getString("stage_name"));

				if(rs.getString("secondlanguage") != null ){
					pstmObj1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECT);
					pstmObj1.setString(1, rs.getString("secondlanguage"));
					rs1=pstmObj1.executeQuery();
					if(rs1.next()){
						studentRegVo.setSecondLanguage(rs1.getString("subjectName"));
					}
				}

				if(rs.getString("thirdlanguage") != null ){
					pstmObj1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECT);
					pstmObj1.setString(1, rs.getString("thirdlanguage"));
					rs1=pstmObj1.executeQuery();
					if(rs1.next()){
						studentRegVo.setThirdLanguage(rs1.getString("subjectName"));
					}
				}
				studentRegVo.setHouseName(rs.getString("housename"));

				studentRegVo.setParentId(rs.getString("parentid"));
				studentRegVo.setFatherName(rs.getString("FatherName"));
				studentRegVo.setFatherMobileNo(rs.getString("mobileno"));
				studentRegVo.setMotherName(rs.getString("student_mothername_var"));
				studentRegVo.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentRegVo.setGaurdianName(rs.getString("student_gaurdianname_var"));
				studentRegVo.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));

				if(conf_count>0)
				{
					studentRegVo.setConfidentialStatus("AVAILABLE");
				}else{
					studentRegVo.setConfidentialStatus("NOT AVAILABLE");
				}

				list.add(studentRegVo);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}

				if (pstmObj1 != null && (!pstmObj1.isClosed())) {
					pstmObj1.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : studentFullList Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentDetailsList(String locationid, String accyear, UserLoggingsPojo userLoggingsVo ) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_REGISTERED_STUDENTDETAILS);
			pst.setString(1, locationid);
			pst.setString(2, accyear);

			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setLocationId(rs.getString("Location_Id"));
				registrationVo.setLocation(rs.getString("Location_Name"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentDetailsLByFilter(String locationid, String accyear, String classname, String status, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);

			if(classname.equalsIgnoreCase("all"))
			{
				classname="%%";
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTDETAILS);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim()); 
				pst.setString(4, status);
			}else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTDETAILS);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, status);
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setClassStreamId(rs.getString("fms_classstream_id_int"));

				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,
			String sectionid, String status, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			if(sectionid.equalsIgnoreCase("all")){
				sectionid="%%";

				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim()); 
				pst.setString(5, status);
				//ln("GET_FILTERED_STUDENTD_BY_SECTION 1-->>"+pst);
			}
			else{
				if(sectionid==""|| sectionid==null){
					sectionid="%%";
				}
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
				pst.setString(5, status);
				//ln("GET_FILTERED_STUDENTD_BY_SECTION 2-->>"+pst);
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				PreparedStatement SecondlanguageName=conn.prepareStatement(SQLUtilConstants.GET_SUBJECTNAME);
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
				while(SecondLangaugeRs.next()){
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}
				SecondLangaugeRs.close();
				SecondlanguageName.close();
				PreparedStatement thirdlanguageName=conn.prepareStatement(SQLUtilConstants.GET_SUBJECTNAME);
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
				while(thirdlanguageRs.next()){
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}	
				thirdlanguageRs.close();
				thirdlanguageName.close();
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement(SQLUtilConstants.GET_SPECIALIZATION_NAME);
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}


	public List<StudentRegistrationVo> getStudentListBySections(String locationid, String accyear, String classname,
			String sectionid,String sortingby,String orderby, String status,String housename, UserLoggingsPojo custdetails, String search, String myorder, int show_per_page, int startPoint, String specialization) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		if(locationid.equalsIgnoreCase("all")){
			locationid="%%";
		}
		if(classname.equalsIgnoreCase("all")){
			classname="%%";
		}
		if(sectionid.equalsIgnoreCase("all") || sectionid.equalsIgnoreCase("undefined") || sectionid.equalsIgnoreCase(null) ){
			sectionid="%%";
		}
		if(status==null || status==""){
			status="%%";
		}

		if(orderby==null){
			orderby="ASC";
		}

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			String query="SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-'  WHEN csc.student_house ='' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END house,ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,CASE WHEN csc.secondlanguage IS NULL THEN '-'   WHEN csc.secondlanguage ='' THEN '-' ELSE csc.secondlanguage END secondlanguage,CASE WHEN csc.thirdlanguage IS NULL THEN '-'   WHEN csc.thirdlanguage ='' THEN '-' ELSE csc.thirdlanguage END thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year , CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int LEFT JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id LEFT JOIN campus_house_settings hs ON (hs.loc_id=csc.locationId AND hs.house_id=csc.student_house) JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y'";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;
			if(!locationid.equalsIgnoreCase("%%")) {
				map.put("csc.locationId",locationid);
				vec.add("csc.locationId");
			}
			if(!accyear.equalsIgnoreCase("%%") ) {
				map.put("csc.fms_acadamicyear_id_int",accyear);
				vec.add("csc.fms_acadamicyear_id_int");
			}
			if(!classname.equalsIgnoreCase("%%")) {
				map.put("csc.classdetail_id_int",classname);
				vec.add("csc.classdetail_id_int");
			}
			if(!sectionid.equalsIgnoreCase("%%")) {
				map.put("csc.classsection_id_int",sectionid);
				vec.add("csc.classsection_id_int");
			}
			if(!status.equalsIgnoreCase("%%")) {
				map.put("stu.student_status_var",status);
				vec.add("stu.student_status_var");
			}
			if(!housename.equalsIgnoreCase("%%")) {
				map.put("hs.house_id",housename);
				vec.add("hs.house_id");
			}

			Enumeration<String> e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql=" and "+ key+" = '"+val+"'";
					count++;
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
				}
			}

			String finalquery="";
			String order="";

			String countQuery="";
			//ln("SORTING BY "+sortingby+" "+orderby);
			if(sortingby.equalsIgnoreCase("Alphabetical"))
			{
				order="ORDER BY studentname "+orderby+",stu.student_admissionno_var*1,LENGTH(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var";
			}
			if(sortingby.equalsIgnoreCase("Admission"))
			{
				order="ORDER BY stu.student_admissionno_var*1 "+orderby;
			}
			if(sortingby.equalsIgnoreCase("Gender"))
			{
				if(orderby.equalsIgnoreCase("FEMALE")){
					order="ORDER BY stu.student_gender_var LIKE 'f%' DESC,studentname,length(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,csc.student_rollno";
				}else{
					order="ORDER BY stu.student_gender_var LIKE 'm%' DESC,studentname,length(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,csc.student_rollno";
				}

			}if(sortingby.equalsIgnoreCase("House"))
			{
				order="ORDER BY studentname ASC,csc.student_rollno,LENGTH(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var";
			}

			if(wheresql != null) {
				finalquery=query+" "+wheresql+" and "+"(stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) "+order+" LIMIT  "+startPoint+","+show_per_page;
				countQuery="SELECT count(*) totalStrength FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int LEFT JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id LEFT JOIN campus_house_settings hs ON (hs.loc_id=csc.locationId AND hs.house_id=csc.student_house) JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y'"+" "+wheresql+" and "+"(stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?)";

			}else {
				finalquery=query+" "+"(stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) "+order+" LIMIT  "+startPoint+","+show_per_page; 
				countQuery="SELECT count(*) totalStrength FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int LEFT JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id LEFT JOIN campus_house_settings hs ON (hs.loc_id=csc.locationId AND hs.house_id=csc.student_house) JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y' AND (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?)";
			}
			pst = conn.prepareStatement(finalquery);
			pst.setString(1,  search + "%");
			pst.setString(2,  search + "%");
			pst.setString(3,  search + "%");
			pst.setString(4,  search + "%");
			pst.setString(5,  search + "%");
			pst.setString(6,  search + "%");
			pst.setString(7,  search + "%");
			pst.setString(8,  search + "%");
			rs=pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setStatus(rs.getString("student_status"));
				registrationVo.setCount(sno);
				registrationVo.setHouseName(rs.getString("house"));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));

				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				PreparedStatement SecondlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
				while(SecondLangaugeRs.next()){
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}
				SecondLangaugeRs.close();
				SecondlanguageName.close();
				PreparedStatement thirdlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
				while(thirdlanguageRs.next()){
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}	
				thirdlanguageRs.close();
				thirdlanguageName.close();
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				PreparedStatement pstmtcount=conn.prepareStatement(countQuery);
				pstmtcount.setString(1,  search + "%");
				pstmtcount.setString(2,  search + "%");
				pstmtcount.setString(3,  search + "%");
				pstmtcount.setString(4,  search + "%");
				pstmtcount.setString(5,  search + "%");
				pstmtcount.setString(6,  search + "%");
				pstmtcount.setString(7,  search + "%");
				pstmtcount.setString(8,  search + "%");
				ResultSet rsCount=pstmtcount.executeQuery();
				if(rsCount.next()){
					registrationVo.setTotalCount(rsCount.getInt("totalStrength"));
				}
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}


	public List<StudentRegistrationVo> getStudentListBySections(String locationid, String accyear, String classname,
			String sectionid,String sortingby,String orderby, String status,String housename, UserLoggingsPojo custdetails, String search, String myorder,int show_per_page,int startPoint) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		if(locationid.equalsIgnoreCase("all")){
			locationid="%%";
		}
		if(classname.equalsIgnoreCase("all")){
			classname="%%";
		}
		if(sectionid.equalsIgnoreCase("all") || sectionid.equalsIgnoreCase("undefined") || sectionid.equalsIgnoreCase(null) ){
			sectionid="%%";
		}
		if(status==null || status==""){
			status="%%";
		}

		if(orderby==null){
			orderby="ASC";
		}

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			String query="SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-'  WHEN csc.student_house ='' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END house,ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,CASE WHEN csc.secondlanguage IS NULL THEN '-'   WHEN csc.secondlanguage ='' THEN '-' ELSE csc.secondlanguage END secondlanguage,CASE WHEN csc.thirdlanguage IS NULL THEN '-'   WHEN csc.thirdlanguage ='' THEN '-' ELSE csc.thirdlanguage END thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year , CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int LEFT JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id LEFT JOIN campus_house_settings hs ON (hs.loc_id=csc.locationId AND hs.house_id=csc.student_house) JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y'";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;
			if(!locationid.equalsIgnoreCase("%%")) {
				map.put("csc.locationId",locationid);
				vec.add("csc.locationId");
			}
			if(!accyear.equalsIgnoreCase("%%")) {
				map.put("csc.fms_acadamicyear_id_int",accyear);
				vec.add("csc.fms_acadamicyear_id_int");
			}
			if(!classname.equalsIgnoreCase("%%")) {
				map.put("csc.classdetail_id_int",classname);
				vec.add("csc.classdetail_id_int");
			}
			if(!sectionid.equalsIgnoreCase("%%")) {
				map.put("csc.classsection_id_int",sectionid);
				vec.add("csc.classsection_id_int");
			}
			if(!status.equalsIgnoreCase("%%")) {
				map.put("stu.student_status_var",status);
				vec.add("stu.student_status_var");
			}
			if(!housename.equalsIgnoreCase("%%")) {
				map.put("hs.house_id",housename);
				vec.add("hs.house_id");
			}

			Enumeration<String> e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql=" and "+ key+" = '"+val+"'";
					count++;
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
				}
			}

			String finalquery="";
			String order="";
			//ln("SORTING BY "+sortingby+" "+orderby);
			if(sortingby.equalsIgnoreCase("Alphabetical"))
			{
				order="ORDER BY studentname "+orderby+",stu.student_admissionno_var*1,LENGTH(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var";
			}
			if(sortingby.equalsIgnoreCase("Admission"))
			{
				order="ORDER BY stu.student_admissionno_var*1 "+orderby;
			}
			if(sortingby.equalsIgnoreCase("Gender"))
			{
				if(orderby.equalsIgnoreCase("FEMALE")){
					order="ORDER BY stu.student_gender_var LIKE 'f%' DESC,studentname,length(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,csc.student_rollno";
				}else{
					order="ORDER BY stu.student_gender_var LIKE 'm%' DESC,studentname,length(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,csc.student_rollno";
				}

			}if(sortingby.equalsIgnoreCase("House"))
			{
				order="ORDER BY studentname ASC,csc.student_rollno,LENGTH(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var";
			}

			if(wheresql != null) {
				finalquery=query+" "+wheresql+" and "+"(stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) "+order+" LIMIT "+startPoint+","+show_per_page;
			}else {
				finalquery=query+" "+"(stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) "+order+" LIMIT "+startPoint+","+show_per_page; 
			}
			pst = conn.prepareStatement(finalquery);
			pst.setString(1,  search + "%");
			pst.setString(2,  search + "%");
			pst.setString(3,  search + "%");
			pst.setString(4,  search + "%");
			pst.setString(5,  search + "%");
			pst.setString(6,  search + "%");
			pst.setString(7,  search + "%");
			pst.setString(8,  search + "%");
			rs=pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setStatus(rs.getString("student_status"));
				registrationVo.setCount(sno);
				registrationVo.setHouseName(rs.getString("house"));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));

				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				PreparedStatement SecondlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
				while(SecondLangaugeRs.next()){
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}
				SecondLangaugeRs.close();
				SecondlanguageName.close();
				PreparedStatement thirdlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
				while(thirdlanguageRs.next()){
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}	
				thirdlanguageRs.close();
				thirdlanguageName.close();
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}


	public List<StudentRegistrationVo> singleStudentDetailsList(String studentId, String accYear, String locationId, String flag, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentDetailsList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		String flag_tab = flag;
		int count = 0;

		try{

			if(flag_tab.equalsIgnoreCase("history"))
			{
				conn = JDBCConnection.getSeparateConnection(pojo);
				pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_DETAILS_BY_HISTORY);
				pstmObj.setString(1, loc_Id);
				pstmObj.setString(2, stu_Id);
				pstmObj.setString(3, acc_year);
				//ln("GET_SINGLE_STUDENT_DETAILS_BY_HISTORY -->>"+pstmObj);
				rs = pstmObj.executeQuery();

				while(rs.next())
				{
					StudentRegistrationVo studentRegVo = new StudentRegistrationVo();

					count++;
					studentRegVo.setCount(count);
					studentRegVo.setConfidentialId(rs.getString("confidential_id"));
					studentRegVo.setStudentId(rs.getString("student_id_int"));
					studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
					studentRegVo.setLocationId(rs.getString("locationId"));
					studentRegVo.setConfidentialEntryDate(HelperClass.convertDatabaseToUI(rs.getString("entrydate")));
					studentRegVo.setConfidentialComments(rs.getString("comments"));

					list.add(studentRegVo);
				}
			}else{
				conn = JDBCConnection.getSeparateConnection(pojo);
				pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_DETAILS_BY_CONTACTS);
				pstmObj.setString(1, acc_year);
				pstmObj.setString(2, loc_Id);
				pstmObj.setString(3, stu_Id);

				//ln("GET_SINGLE_STUDENT_DETAILS_BY_CONTACTS -->>"+pstmObj);
				rs = pstmObj.executeQuery();

				while(rs.next())
				{
					StudentRegistrationVo studentRegVo = new StudentRegistrationVo();

					count++;
					studentRegVo.setCount(count);
					studentRegVo.setStudentId(rs.getString("student_id_int"));
					studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
					studentRegVo.setLocationId(rs.getString("locationId"));
					studentRegVo.setParentId(rs.getString("parentid"));
					studentRegVo.setFatherName(rs.getString("FatherName"));
					studentRegVo.setFatherMobileNo(rs.getString("mobileno"));
					studentRegVo.setMotherName(rs.getString("student_mothername_var"));
					studentRegVo.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
					studentRegVo.setGaurdianName(rs.getString("student_gaurdianname_var"));
					studentRegVo.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
					studentRegVo.setPrimaryPersondisables(rs.getString("relationship"));

					list.add(studentRegVo);
				}
			}

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentDetailsList Ending");
		return list;
	}

	@Override
	public String saveStudentPromotion(StudentRegistrationVo formObj,UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeExpenseDetailsDaoImpl : saveLocalExpenseDetails Starting");

		PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
		Connection con = null;
		String message="";
		int status = 0,uploadStatus=0,uploadStatus1 = 0;
		try {
			//ln("Inside the saveStudentPromotion DaoImpl");
			con = JDBCConnection.getSeparateConnection(pojo);
			con.setAutoCommit(false);
			Timestamp createdDate = HelperClass.getCurrentTimestamp();
			for (int i = 0; i < formObj.getStudentIdArray().length; i++) {
				pstmt = con.prepareStatement(StudentRegistrationSQLUtilConstants.INSERT_STUDENT_PROMOTION);
				pstmt.setString(1, formObj.getAdmissionNoArray()[i]);
				pstmt.setString(2, formObj.getStudentIdArray()[i]);
				pstmt.setString(3, formObj.getClass_fromArray()[i]);
				pstmt.setString(4, formObj.getClass_toArray()[i]);
				pstmt.setString(5, formObj.getAcademicyear_fromArray()[i]);
				pstmt.setString(6, formObj.getAcademicyear_toArray()[i]);
				pstmt.setString(7, formObj.getSection_fromArray()[i]);
				pstmt.setString(8, formObj.getSection_toArray()[i]);
				pstmt.setString(9, formObj.getSpecilization_fromArray()[i]);
				pstmt.setString(10, formObj.getSpecilization_toArray()[i]);
				pstmt.setString(11, formObj.getStatusArray()[i]);
				pstmt.setString(12, formObj.getCreateUser().trim());
				pstmt.setTimestamp(13, createdDate);
				pstmt.setString(14, formObj.getLocationIdArray()[i]);
				String fromstream=toGetStreamName(formObj.getClass_fromArray()[i],formObj.getLocationIdArray()[i],pojo);
				String tostream=toGetStreamName(formObj.getClass_toArray()[i],formObj.getLocationIdArray()[i],pojo);
				//studentpromotion_fromstream,studentpromotion_tostream
				pstmt.setString(15, fromstream);
				pstmt.setString(16, tostream);
				pstmt.setString(17, formObj.getComments());
				//ln("INSERT_STUDENT_PROMOTION -->> "+pstmt);
				status = pstmt.executeUpdate();	

				if(status > 0){
					HelperClass.recordLog_Activity(formObj.getLog_audit_session(),"Student","Promotion","Insert",pstmt.toString(),pojo);
				}
			}

			//ln("status"+status);
			if ((status > 0)) {
				if ((formObj.getStudentIdArray()[0] != null) && !(formObj.getStudentIdArray()[0].equalsIgnoreCase("")) ) {
					uploadStatus = saveStudentClassDetails(formObj,con,formObj.getLog_audit_session(),pojo);
					uploadStatus1 = saveStudentTransportDetails(formObj,con,formObj.getLog_audit_session(),pojo);
				}
				if ((formObj.getStudentIdArray()[0] != null) && !(formObj.getStudentIdArray()[0].equalsIgnoreCase("")) ){
					uploadStatus = updateStudentClassDetails(formObj, con,formObj.getLog_audit_session(),pojo); 
				}

				if ((uploadStatus > 0)) {
					con.commit();
					message = "success";

				} else {
					try {
						con.rollback();
					} catch (SQLException e1) {
						logger.error(e1.getMessage(), e1);
						e1.printStackTrace();
					}
					message = "fail";
				}

			} else {
				try {
					con.rollback();
				} catch (SQLException e1) {
					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
				message = "fail";
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{

			try {
				if (pstmt2 != null && (!pstmt2.isClosed())) {

					pstmt2.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {

					pstmt1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeExpenseDetailsDaoImpl : saveLocalExpenseDetails Ending");
		return message;
	}

	private int updateStudentClassDetails(StudentRegistrationVo formObj, Connection con, String session,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveTravelExpenseDetails Starting");

		PreparedStatement pstmt = null;
		Connection conn = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		try {
			conn = con;

			for (int i = 0; i < formObj.getStudentIdArray().length; i++) {
				pstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_STUDENT_CLASSDETAILS_PREVIOUS_RECORD);
				if(formObj.getStatusArray()[i].equalsIgnoreCase("promoted")){
					pstmt.setString(1,"PASSED");
				}else if(formObj.getStatusArray()[i].equalsIgnoreCase("demoted")){
					pstmt.setString(1,"FAILED");
				}else{
					pstmt.setString(1,"");
				}
				pstmt.setString(2, formObj.getStatusArray()[i]);
				pstmt.setString(3, formObj.getCreateUser().trim());
				pstmt.setTimestamp(4, createdDate);
				pstmt.setString(5, formObj.getStudentIdArray()[i]);
				pstmt.setString(6, formObj.getAcademicyear_fromArray()[i]);
				status=pstmt.executeUpdate();

				if(status > 0){
					HelperClass.recordLog_Activity(session,"Student","Promotion","Update",pstmt.toString(),userLoggingsVo);
				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{

			try {
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}

			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveTravelExpenseDetails Ending");
		return status;
	}

	private int saveStudentClassDetails(StudentRegistrationVo formObj, Connection con, String session,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Starting");

		PreparedStatement pstmt = null;
		Connection conn = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		try {
			conn = con;

			for (int i = 0; i < formObj.getStudentIdArray().length; i++) {
				pstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.INSERT_STUDENT_PROMOTION_IN_CLASSDETAILS);
				pstmt.setString(1, formObj.getStudentIdArray()[i]);
				pstmt.setString(2, formObj.getAcademicyear_toArray()[i]);
				pstmt.setString(3, formObj.getLocationIdArray()[i]);
				pstmt.setString(4, formObj.getClass_toArray()[i]);
				pstmt.setString(5, formObj.getSection_toArray()[i]);
				pstmt.setString(6, formObj.getSpecilization_toArray()[i]);
				pstmt.setString(7, formObj.getCreateUser().trim());
				pstmt.setTimestamp(8, createdDate);
				String tostream=toGetStreamName(formObj.getClass_toArray()[i],formObj.getLocationIdArray()[i],userLoggingsVo);
				pstmt.setString(9, tostream);
				status=pstmt.executeUpdate();

				if(status > 0){
					HelperClass.recordLog_Activity(session,"Student","Promotion","Insert",pstmt.toString(),userLoggingsVo);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{

			try {
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}

			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Ending");
		return status;
	}

	public String getNextAcademicYearId(String academicyearid, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportCategoryType Starting");

		PreparedStatement preparedStatement = null,preparedStatement1 = null,preparedStatement2=null;
		ResultSet rs = null,rs1 = null,rs2=null;
		String enddate = null;
		String academicYearId="",accyear="",year="";
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);

			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_NEXTACADEMIC_YEAR);
			preparedStatement.setString(1, academicyearid);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				enddate = rs.getString("endDate");
				accyear = rs.getString("acadamic_year");
				SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );   
				Calendar cal = Calendar.getInstance();    
				cal.setTime( dateFormat.parse(enddate));
				cal.add( Calendar.DATE, 1 );    
				String convertedDate=dateFormat.format(cal.getTime());    

				preparedStatement1 = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_NEXTYEAR_COUNT);
				preparedStatement1.setString(1, convertedDate);
				rs1 = preparedStatement1.executeQuery();
				while(rs1.next()){
					count=rs1.getInt("count");
					year=rs1.getString("acadamic_id");
				}
				if(count == 0){
					String[] accyearsplit=accyear.split("-");
					preparedStatement2 = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_NEXTYEARSPLIT_VAL);
					preparedStatement2.setString(1, accyearsplit[1].trim()+"%");
					rs2 = preparedStatement2.executeQuery();
					while(rs2.next()){
						academicYearId=rs2.getString("acadamic_id");
					}
				}else{
					academicYearId=year;
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (preparedStatement1 != null
						&& (!preparedStatement1.isClosed())) {
					preparedStatement1.close();
				}
				if (preparedStatement2 != null
						&& (!preparedStatement2.isClosed())) {
					preparedStatement2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : getTransportCategoryType Ending");
		return academicYearId;
	}

	public List<StudentRegistrationVo> getPromotedListDetails(StudentRegistrationVo regVo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getPromotedListDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null; 
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			//String nextYear=getNextAcademicYearId(regVo.getAcademicYearId());
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_PROMOTED_LIST);
			pst.setString(1, regVo.getLocationId());
			pst.setString(2, regVo.getAcademicYearId());
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getPromotedListDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentLocationList(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentLocationList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = academic_year;
		String loc_Id = location;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_BY_LOCATION);
			pst.setString(1, loc_Id);
			pst.setString(2, acc_year);
			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				list.add(stuReg);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentLocationList Ending");

		return list;
	}

	public List<StudentRegistrationVo> searchAllAcademicYearDetails(String locationId, String accYear, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAcademicYearDetails Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = accYear;
		String loc_Id = locationId;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_BY_LOCATION_ACCYEAR);
			pst.setString(1, loc_Id);
			pst.setString(2, acc_year);
			//ln("GET_STUDENTS_BY_LOCATION_ACCYEAR -->>"+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt1.setString(1, accYear);
				ResultSet  rs1 =pstmt1.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				rs1.close();
				pstmt1.close();
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAcademicYearDetails Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;

		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_LIST);
			pst.setString(1, searchValue+"%");
			pst.setString(2, searchValue+"%");
			pst.setString(3, searchValue+"%");
			pst.setString(4, searchValue+"%");
			pst.setString(5, searchValue+"%");
			pst.setString(6, searchValue+"%");
			pst.setString(7, searchValue+"%");
			pst.setString(8, searchValue+"%");
			rs = pst.executeQuery();
			//ln("pst :: "+pst);	
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClasssection(rs.getString("classdetails_name_var"+"-"+"classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByList Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByAccYear Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String accYear = accyear;

		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_ACCYEAR);
			pst.setString(1, searchValue+"%");
			pst.setString(2, searchValue+"%");
			pst.setString(3, searchValue+"%");
			pst.setString(4, searchValue+"%");
			pst.setString(5, searchValue+"%");
			pst.setString(6, searchValue+"%");
			pst.setString(7, searchValue+"%");
			pst.setString(8, searchValue+"%");
			pst.setString(9, accYear);
			//ln("2: "+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByAccYear Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;

		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_LOCATION);
			pst.setString(1, searchValue+"%");
			pst.setString(2, searchValue+"%");
			pst.setString(3, searchValue+"%");
			pst.setString(4, searchValue+"%");
			pst.setString(5, searchValue+"%");
			pst.setString(6, searchValue+"%");
			pst.setString(7, searchValue+"%");
			pst.setString(8, searchValue+"%");
			pst.setString(9, locationId);
			//ln("3 :: "+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				/*	//raji
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt1.setString(1, stuReg.getAcademicYearId());
				ResultSet  rs1 =pstmt1.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}*/
				stuReg.setAcademicYear(rs.getString("acadamic_year"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));

				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear, String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFilter Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_FILTER);
			pst.setString(1, searchValue+"%");
			pst.setString(2, searchValue+"%");
			pst.setString(3, searchValue+"%");
			pst.setString(4, searchValue+"%");
			pst.setString(5, searchValue+"%");
			pst.setString(6, searchValue+"%");
			pst.setString(7, searchValue+"%");
			pst.setString(8, searchValue+"%");
			pst.setString(9, locationId);
			pst.setString(10, accYear);

			//ln("4 :::"+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+'-'+rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFilter Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByClass Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_CLASS);
			pst.setString(1, searchValue+"%");
			pst.setString(2, searchValue+"%");
			pst.setString(3, searchValue+"%");
			pst.setString(4, searchValue+"%");
			pst.setString(5, searchValue+"%");
			pst.setString(6, searchValue+"%");
			pst.setString(7, searchValue+"%");
			pst.setString(8, searchValue+"%");
			pst.setString(9, locationId);
			pst.setString(10, accYear);
			pst.setString(11, className);
			//ln("5:::"+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				list.add(stuReg);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByClass Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid,
			String accyear,String classname, String sectionid, String housename, String status, String sortby,
			String orderbyAdmissionAndAlpha, String orderbyGender, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int sno = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			if(sortby.equalsIgnoreCase("House")){
				pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_ALL_FILTER_BY_HOUSE_WISE);
				pst.setString(1, searchValue+"%");
				pst.setString(2, searchValue+"%");
				pst.setString(3, searchValue+"%");
				pst.setString(4, searchValue+"%");
				pst.setString(5, searchValue+"%");
				pst.setString(6, searchValue+"%");
				pst.setString(7, searchValue+"%");
				pst.setString(8, searchValue+"%");

				pst.setString(9, locationId);
				pst.setString(10, accYear);
				pst.setString(11, className);
				pst.setString(12, section); 
				pst.setString(13, housename);
				if(status=="" || status==null){
					pst.setString(14, "active");
				}else{
					pst.setString(14, status);
				}
				//ln("GET_STUDENTS_SEARCH_BY_ALL_FILTER_BY_HOUSE_WISE -->>"+pst);
			}
			else if(sortby.equalsIgnoreCase("Alphabetical")){

				pst = conn.prepareStatement("SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-' WHEN csc.student_house ='' THEN '-' WHEN csc.student_house ='(NULL)' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END housename,csc.locationId,csc.classdetail_id_int,csc.classsection_id_int,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentName,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings hs ON (hs.house_id=csc.student_house AND hs.loc_id=csc.locationId  AND hs.house_id LIKE ? ) LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) AND csc.locationId LIKE ? AND csc.fms_acadamicyear_id_int =? AND ccd.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? and stu.student_status_var like ? AND cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y'  ORDER BY length(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,studentName "+orderbyAdmissionAndAlpha+"");	
				pst.setString(1, housename);

				pst.setString(2, searchValue+"%");
				pst.setString(3, searchValue+"%");
				pst.setString(4, searchValue+"%");
				pst.setString(5, searchValue+"%");
				pst.setString(6, searchValue+"%");
				pst.setString(7, searchValue+"%");
				pst.setString(8, searchValue+"%");
				pst.setString(9, searchValue+"%");
				pst.setString(10, locationId);
				pst.setString(11, accYear);
				pst.setString(12, className);
				pst.setString(13, section); 
				if(status=="" || status==null){
					pst.setString(14, "%%");
				}else{
					pst.setString(14, status);
				}
				//ln("GET_STUDENTS_SEARCH_BY_ALL_FILTER 11 -->>"+pst);
			}
			else if(sortby.equalsIgnoreCase("Admission")){

				pst = conn.prepareStatement("SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-' WHEN csc.student_house ='' THEN '-' WHEN csc.student_house ='(NULL)' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END housename,csc.locationId,csc.classdetail_id_int,csc.classsection_id_int,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentName,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings hs ON (hs.house_id=csc.student_house AND hs.loc_id=csc.locationId  AND hs.house_id LIKE ? ) LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) AND csc.locationId LIKE ? AND csc.fms_acadamicyear_id_int =? AND ccd.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? and stu.student_status_var like ? AND cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y'  ORDER BY stu.student_admissionno_var "+orderbyAdmissionAndAlpha+",ccd.classdetails_name_var,ccs.classsection_name_var");	
				pst.setString(1, housename);

				pst.setString(2, searchValue+"%");
				pst.setString(3, searchValue+"%");
				pst.setString(4, searchValue+"%");
				pst.setString(5, searchValue+"%");
				pst.setString(6, searchValue+"%");
				pst.setString(7, searchValue+"%");
				pst.setString(8, searchValue+"%");
				pst.setString(9, searchValue+"%");
				pst.setString(10, locationId);
				pst.setString(11, accYear);
				pst.setString(12, className);
				pst.setString(13, section); 
				if(status=="" || status==null){
					pst.setString(14, "%%");
				}else{
					pst.setString(14, status);
				}
				//ln("GET_STUDENTS_SEARCH_BY_ALL_FILTER 12 -->>"+pst);
			}
			else if(sortby.equalsIgnoreCase("Gender")){
				if(orderbyGender.equalsIgnoreCase("ASC")){
					pst = conn.prepareStatement("SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-' WHEN csc.student_house ='' THEN '-' WHEN csc.student_house ='(NULL)' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END housename,csc.locationId,csc.classdetail_id_int,csc.classsection_id_int,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentName,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings hs ON (hs.house_id=csc.student_house AND hs.loc_id=csc.locationId  AND hs.house_id LIKE ? ) LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) AND csc.locationId LIKE ? AND csc.fms_acadamicyear_id_int =? AND ccd.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? and stu.student_status_var like ? AND cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y'  ORDER BY stu.student_gender_var LIKE 'f%' DESC,length(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,csc.student_rollno ");
				}else{
					pst = conn.prepareStatement("SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-' WHEN csc.student_house ='' THEN '-' WHEN csc.student_house ='(NULL)' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END housename,csc.locationId,csc.classdetail_id_int,csc.classsection_id_int,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentName,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings hs ON (hs.house_id=csc.student_house AND hs.loc_id=csc.locationId  AND hs.house_id LIKE ? ) LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) AND csc.locationId LIKE ? AND csc.fms_acadamicyear_id_int =? AND ccd.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? and stu.student_status_var like ? AND cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y'  ORDER BY stu.student_gender_var LIKE 'm%' DESC,length(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,csc.student_rollno ");
				}

				pst.setString(1, housename);
				pst.setString(2, searchValue+"%");
				pst.setString(3, searchValue+"%");
				pst.setString(4, searchValue+"%");
				pst.setString(5, searchValue+"%");
				pst.setString(6, searchValue+"%");
				pst.setString(7, searchValue+"%");
				pst.setString(8, searchValue+"%");
				pst.setString(9, searchValue+"%");
				pst.setString(10, locationId);
				pst.setString(11, accYear);
				pst.setString(12, className);
				pst.setString(13, section); 
				if(status=="" || status==null){
					pst.setString(14, "%%");
				}else{
					pst.setString(14, status);
				}
				//ln("GET_STUDENTS_SEARCH_BY_ALL_FILTER 13 -->>"+pst);
			}
			else if(sortby.equalsIgnoreCase("printstudentidcard")){
				pst = conn.prepareStatement("SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-' WHEN csc.student_house ='' THEN '-' WHEN csc.student_house ='(NULL)' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END housename,csc.locationId,csc.classdetail_id_int,csc.classsection_id_int,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentName,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings hs ON (hs.house_id=csc.student_house AND hs.loc_id=csc.locationId  AND hs.house_id LIKE ? ) LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) AND csc.locationId LIKE ? AND csc.fms_acadamicyear_id_int =? AND ccd.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? and stu.student_status_var like ? AND cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y'  ORDER BY LENGTH(ccd.classdetail_id_int) ASC,ccd.classdetail_id_int");
				pst.setString(1, housename);

				pst.setString(2, searchValue+"%");
				pst.setString(3, searchValue+"%");
				pst.setString(4, searchValue+"%");
				pst.setString(5, searchValue+"%");
				pst.setString(6, searchValue+"%");
				pst.setString(7, searchValue+"%");
				pst.setString(8, searchValue+"%"); 
				pst.setString(9, searchValue+"%");
				pst.setString(10, locationId);
				pst.setString(11, accYear);
				pst.setString(12, className);
				pst.setString(13, section); 
				if(status=="" || status==null){
					pst.setString(14, "%%");
				}else{
					pst.setString(14, status);
				}
				//ln("printstudentidcard -->>"+pst);

			}
			else{
				pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_ALL_FILTER);
				pst.setString(1, housename);

				pst.setString(2, searchValue+"%");
				pst.setString(3, searchValue+"%");
				pst.setString(4, searchValue+"%");
				pst.setString(5, searchValue+"%");
				pst.setString(6, searchValue+"%");
				pst.setString(7, searchValue+"%");
				pst.setString(8, searchValue+"%");
				pst.setString(9, searchValue+"%");
				pst.setString(10, locationId);
				pst.setString(11, accYear);
				pst.setString(12, className);
				pst.setString(13, section); 
				if(status=="" || status==null){
					pst.setString(14, "%%");
				}else{
					pst.setString(14, status);
				}
				//ln("GET_STUDENTS_SEARCH_BY_ALL_FILTER 14 -->>"+pst);
			}

			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setHouseName(rs.getString("housename"));
				stuReg.setStatus(rs.getString("student_status"));
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Ending");

		return list;
	}

	public String AddConfidentialDetails(String logactivity,String entryDate,
			String comments, String studentId, String accyear, String locationid,String userName, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : AddConfidentialDetails Starting");


		PreparedStatement pst = null;
		Connection conn = null;
		String entrydate = entryDate;
		String comment = comments;
		String student_Id = studentId;
		String location_Id = locationid;
		String accYear = accyear;
		String username = userName;
		int result = 0;
		String status = null;

		try{


			String s1 = IDGenerator.getPrimaryKeyID("campus_confidential_report",pojo);

			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.ADD_CONFIDENTIAL_DETAIL);
			pst.setString(1,s1);
			pst.setString(2,student_Id);
			pst.setString(3,accYear);
			pst.setString(4,location_Id);
			pst.setString(5,HelperClass.convertUIToDatabase(entrydate));
			pst.setString(6,comment);
			pst.setString(7,username);

			result = pst.executeUpdate();


			if (result > 0) {
				HelperClass.recordLog_Activity(logactivity,"Student","Disciplinary Action","Insert",pst.toString(),pojo);
				status = MessageConstants.ADD_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.ADD_CONFIDENTIAL_REPORT_FAIL;

			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : AddConfidentialDetails Ending");

		return status;
	}

	public List<StudentRegistrationVo> getConfidentialReportStudents(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfidentialReportStudents Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = academic_year;
		String loc_Id = location;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_BY_CONFIDENTIAL_REPORT);
			pst.setString(1, loc_Id);
			pst.setString(2, acc_year);
			//ln("GET_STUDENTS_BY_CONFIDENTIAL_REPORT -->>"+pst);
			rs = pst.executeQuery();


			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfidentialReportStudents Ending");

		return list;
	}

	public List<StudentRegistrationVo> searchAllAccYearDetailsConfReport(String locationId, String accYear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAccYearDetailsConfReport Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = accYear;
		String loc_Id = locationId;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_CONF_BY_LOCATION_ACCYEAR);
			pst.setString(1, loc_Id);
			pst.setString(2, acc_year);

			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));

				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAccYearDetailsConfReport Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConfDetailsLByFilter(String locationid, String accyear, String classname,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfDetailsLByFilter  Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENT_CONF_DETAILS);
			pst.setString(1, locationid.trim());
			pst.setString(2, accyear.trim());
			pst.setString(3, classname.trim());

			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getConfDetailsLByFilter  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentBySectionForConfReport(
			String locationid, String accyear, String classname,String sectionid,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentBySectionForConfReport  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_CONF_STUDENTS_BY_SECTION);
			pst.setString(1, locationid.trim());
			pst.setString(2, accyear.trim());
			pst.setString(3, classname.trim());
			pst.setString(4, sectionid.trim());
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentBySectionForConfReport  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByConfReport(String searchTerm,UserLoggingsPojo userLoggingsVo ) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByConfReport Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;

		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_CONF_REPORT);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));

				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByConfReport Ending");

		return list;
	}

	public List<StudentRegistrationVo> singleStudentConfDetails(String studentId, String accYear, String locationId,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentConfDetails Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		PreparedStatement pstmObj2 = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_DETAILS);
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);
			//ln("GET_SINGLE_STUDENT_DETAILS  -->>"+pstmObj);
			rs = pstmObj.executeQuery();

			while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();

				count++;
				studentRegVo.setCount(count);
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				studentRegVo.setStudentStatus(rs.getString("student_status"));
				studentRegVo.setLocation(rs.getString("Location_Name"));
				studentRegVo.setParentId(rs.getString("parentid"));
				studentRegVo.setFatherName(rs.getString("FatherName"));
				studentRegVo.setFatherMobileNo(rs.getString("mobileno"));
				studentRegVo.setMotherName(rs.getString("student_mothername_var"));
				studentRegVo.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentRegVo.setGaurdianName(rs.getString("student_gaurdianname_var"));
				studentRegVo.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));

				pstmObj2 = conn.prepareStatement("SELECT csh.house_id,csh.student_id,chs.housename FROM campus_student_house csh LEFT JOIN campus_house_settings chs ON chs.house_id=csh.house_id WHERE csh.loc_id LIKE ? AND csh.student_id=? AND csh.academic_year=?");
				pstmObj2.setString(1, loc_Id);
				pstmObj2.setString(2, stu_Id);
				pstmObj2.setString(3, acc_year);

				rst = pstmObj2.executeQuery();

				while(rst.next())
				{
					if(rst.getString("house_id") == null || rst.getString("house_id") == ""){
						studentRegVo.setHouseName("");
					}
					else{
						studentRegVo.setHouseName(rst.getString("housename"));
					}
				}
				list.add(studentRegVo);

			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj2 != null && (!pstmObj2.isClosed())) {
					pstmObj2.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentConfDetails Ending");
		return list;
	}

	public List<StudentRegistrationVo> getConfSearchListByAccYear(String searchTerm, String accyear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchListByAccYear Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String accYear = accyear;

		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_CONF_REPORT_BY_ACCYEAR);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+accYear+"%");

			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));

				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchListByAccYear Ending");

		return list;
	}

	public List<FeeReportDetailsVo> getfeedetails(String studentId,
			String accYear, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getfeedetails Starting");

		List<FeeReportDetailsVo> list1 = new ArrayList<FeeReportDetailsVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_FEE_DETAILS);
			pst.setString(1, studentId);
			pst.setString(2, accYear);
			pst.setString(3, locationId);
			rs = pst.executeQuery();
			while(rs.next()){
				sno++;
				FeeReportDetailsVo freport = new FeeReportDetailsVo();
				freport.setSno(sno);
				freport.setTermName(rs.getString("termcode"));
				freport.setTotalAmount(rs.getString("totalamount"));
				freport.setFineamount(rs.getString("fineAmount"));
				freport.setBlancefeeAmount(rs.getDouble("balance_amount"));
				freport.setPaidAmount(rs.getDouble("amount_paid"));
				freport.setPaidStatus(rs.getString("is_paid"));
				if( freport.getPaidStatus().equalsIgnoreCase("Y"))
				{
					freport.setPaidStatus("paid");
				}
				else
				{
					freport.setPaidStatus("NotPaid");
				}

				list1.add(freport);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getfeedetails Ending");

		return list1;
	}

	public List<ExaminationDetailsVo> getExaminationDetails(
			StudentRegistrationVo svo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getExaminationDetails Starting");

		List<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_EXAMINATION_DETAILS);
			pst.setString(1,svo.getClassDetailId());
			pst.setString(2,svo.getClassSectionId());
			pst.setString(3,svo.getStudentId());
			pst.setString(4,svo.getAcademicYearId());
			pst.setString(5,svo.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				sno++;
				ExaminationDetailsVo exvo = new ExaminationDetailsVo();
				exvo.setSno1(sno);
				exvo.setSubCode(rs.getString("Sub_Code"));
				exvo.setSubjectName(rs.getString("subjectName"));
				exvo.setPassmarks(rs.getString("passMarks"));
				exvo.setTot_marks(rs.getString("totalMarks"));
				exvo.setScoredmarks(rs.getString("scoredmarks"));
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_GRADE_FOR_STUDENTS);
				pstmt1.setString(1, exvo.getScoredmarks());
				pstmt1.setString(2, exvo.getScoredmarks());
				ResultSet rs1=pstmt1.executeQuery(); 
				//ln("pstmt1:" +pstmt1);
				while(rs1.next())
				{
					exvo.setGradename(rs1.getString("grade_name"));
				}
				list1.add(exvo);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getExaminationDetails Ending");

		return list1;
	}

	public List<ExaminationDetailsVo> getExaminationCodes(String loc_id,
			String acyear_id, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getExaminationCodes Starting");

		List<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_EXAMINATION_CODES);
			pst.setString(1,loc_id);
			pst.setString(2,acyear_id);
			rs = pst.executeQuery();
			//ln("to run the query:" +pst);

			while(rs.next()){

				ExaminationDetailsVo exvo = new ExaminationDetailsVo();
				exvo.setExamid(rs.getString("examid"));
				exvo.setExamcode(rs.getString("examcode"));
				list1.add(exvo);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getExaminationCodes Ending");

		return list1;
	}

	public String getExamName(String examcode, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getExamName Starting");
		String ExamCode = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_EXAMINATION_NAMES);
			pst.setString(1,examcode);
			rs = pst.executeQuery();

			while(rs.next()){

				ExaminationDetailsVo exvo = new ExaminationDetailsVo();
				exvo.setExamName(rs.getString("examname"));
				ExamCode=exvo.getExamName();
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getExamName Ending");

		return ExamCode;
	}

	public List<StudentAttendanceVo> getStudentAttendance(String stud_id, String accId, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentAttendance Starting");
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		List<StudentAttendanceVo> list1 = new ArrayList<StudentAttendanceVo>();

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_ATTENDANCE);

			pst.setString(1,stud_id);
			pst.setString(2,accId);
			rs = pst.executeQuery();
			//ln("to run the query for attendance:" +pst);

			while(rs.next()){
				sno++;
				StudentAttendanceVo stuvo = new StudentAttendanceVo();
				stuvo.setSno(sno);
				stuvo.setDate(rs.getString("attendencedate"));
				stuvo.setAttendancestatus(rs.getString("atten_status"));
				list1.add(stuvo);

			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentAttendance Ending");

		return list1;
	}

	public List<StudentAttendanceVo> getStudentAppraisal(
			StudentRegistrationVo spvo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentAppraisal Starting");
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		List<StudentAttendanceVo> list1 = new ArrayList<StudentAttendanceVo>();

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_APPRAISAL);

			pst.setString(1,spvo.getLocationId());
			pst.setString(2,spvo.getAcademicYearId());
			pst.setString(3,spvo.getStudentId());
			//ln("GET_STUDENT_APPRAISAL -->>" +pst);
			rs = pst.executeQuery();

			while(rs.next()){
				sno++;
				StudentAttendanceVo stuvo = new StudentAttendanceVo();
				stuvo.setSno(sno);
				stuvo.setDate(rs.getString("schedule_date"));
				stuvo.setRecommendedby(rs.getString("NAME"));
				stuvo.setMeetingwith(rs.getString("meeting_with")); 
				stuvo.setActiontaken(rs.getString("action_taken")); 
				stuvo.setRemarksstatus(rs.getString("remarks")); 
				stuvo.setStatus(rs.getString("status")); 
				list1.add(stuvo);

			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentAppraisal Ending");

		return list1;
	}

	public List<ExaminationDetailsVo> getExaminationDetailsBasedonExams(
			StudentRegistrationVo svo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getExaminationDetailsBasedonExams Starting");

		List<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt=conn.prepareStatement(SQLUtilConstants.GET_EXAMID);
			pstmt.setString(1, svo.getExam_code());
			pstmt.setString(2, svo.getExam_name());
			ResultSet rs2 = pstmt.executeQuery();
			while(rs2.next())
			{
				svo.setExam_id(rs2.getString("examid"));
			}
			rs2.close();
			pstmt.close();
			//	pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_EXAMINATION_DETAILS_BASED_EXAMS);
			pst = conn.prepareStatement("SELECT cs.Sub_Code,cs.subjectName,csme.`total_marks`,csme.`scored_marks`,csme.`notebook_marks`,csme.`subject_enrich_marks`,csme.`max_periodic_marks`,csme.`periodic_test`,csme.`max_notebook_marks`,csme.`max_subjenrich_marks` FROM campus_subject cs LEFT JOIN `campus_studentwise_mark_details` csme ON cs.subjectID=csme.sub_id WHERE csme.classid=? AND csme.sectionid=? AND csme.stu_id=? AND csme.Academic_yearid=? AND csme.location_id=? AND csme.exam_id=?");
			pst.setString(1,svo.getClassDetailId());
			pst.setString(2,svo.getClassSectionId());
			pst.setString(3,svo.getStudentId());
			pst.setString(4,svo.getAcademicYearId());
			pst.setString(5,svo.getLocationId());
			pst.setString(6,svo.getExam_code());
			rs = pst.executeQuery();
			//ln("pst from query:" +pst);
			while(rs.next()){
				sno++;
				//csme.`scored_marks`,csme.`notebook_marks`,csme.`subject_enrich_marks`,csme.`max_periodic_marks`,csme.`periodic_test`,csme.`max_notebook_marks`,csme.`max_subjenrich_marks`
				ExaminationDetailsVo exvo = new ExaminationDetailsVo();
				exvo.setSno1(sno);
				exvo.setSubCode(rs.getString("Sub_Code"));
				exvo.setSubjectName(rs.getString("subjectName"));
				//exvo.setPassmarks(rs.getString("passMarks"));
				exvo.setTot_marks(Integer.toString(rs.getInt("max_periodic_marks")+rs.getInt("max_notebook_marks")+rs.getInt("max_subjenrich_marks")+rs.getInt("total_marks")));
				exvo.setScoredmarks(Integer.toString(rs.getInt("scored_marks")+rs.getInt("notebook_marks")+rs.getInt("subject_enrich_marks")+rs.getInt("periodic_test")));
				/*	PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_GRADE_FOR_STUDENTS);
				pstmt1.setString(1, exvo.getScoredmarks());
				pstmt1.setString(2, exvo.getScoredmarks());
			    ResultSet rs1=pstmt1.executeQuery(); 
			    //ln("pstmt1:" +pstmt1);
			    while(rs1.next())
			    {
			    exvo.setGradename(rs1.getString("grade_name"));
			    }*/
				list1.add(exvo);
				/*  rs1.close();
			    pstmt1.close();*/

			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getExaminationDetailsBasedonExams Ending");

		return list1;
	}

	public List<StudentRegistrationVo> getIDCard(String studentId, String accYear,String locationId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getIDCard Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;
		int conf_count = 0;


		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_ID_CARD);
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);

			//ln("query==========>"+pstmObj);
			rs = pstmObj.executeQuery();

			while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
				conf_count = rs.getInt(1);

				count++;
				studentRegVo.setCount(count);
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				studentRegVo.setRoute(rs.getString("RouteName"));
				studentRegVo.setStudentStatus(rs.getString("student_status"));
				studentRegVo.setLocation(rs.getString("Location_Name"));
				studentRegVo.setStage_name(rs.getString("stage_name"));
				studentRegVo.setStreemcode(rs.getString("fms_classstream_id_int"));
				studentRegVo.setValidaty(HelperClass.convertDatabaseToUI(rs.getString("endDate")));




				studentRegVo.setHouseName(rs.getString("housename"));

				studentRegVo.setParentId(rs.getString("parentid"));
				studentRegVo.setFatherName(rs.getString("FatherName"));
				studentRegVo.setFatherMobileNo(rs.getString("mobileno"));
				studentRegVo.setMotherName(rs.getString("student_mothername_var"));
				studentRegVo.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentRegVo.setGaurdianName(rs.getString("student_gaurdianname_var"));
				studentRegVo.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));

				studentRegVo.setPresentaddress(rs.getString("address"));



				list.add(studentRegVo);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getIDCard Ending");

		return list;
	}


	public List<PageFilterVo> getIDCardStaff(PageFilterpojo filterpojo, UserLoggingsPojo userLoggingsVo) {
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

			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);

			psmt=conn.prepareStatement("SELECT ct.registerId,ct.TeacherID,CONCAT(ct.FirstName,' ',ct.`LastName`)AS FirstName,CASE WHEN ct.isActive='Y' THEN 'ACTIVE' WHEN ct.isActive='N' THEN 'INACTIVE' "+ 
					"ELSE ct.isActive END isActive,ct.department,cd.DEPT_NAME,cd.DEPT_ID,ct.designation,des.designationName, "+
					"cay.acadamic_id,cay.acadamic_year,cl.Location_Id,cl.Location_Name FROM campus_acadamicyear cay,campus_teachers ct "+
					"LEFT JOIN campus_department cd ON cd.DEPT_ID=ct.department AND cd.`isActive` = 'Y' LEFT JOIN campus_designation des ON des.DesignationCode=ct.designation AND des.`isActive` = 'Y' "+
					"LEFT JOIN campus_location cl ON ct.Loc_ID=cl.Location_Id AND cl.`isActive` = 'Y' WHERE cd.DEPT_ID LIKE ? AND des.DesignationCode "+ 
					"LIKE ? AND ct.Loc_ID = ? AND cay.`isActive` = 'Y' and cay.acadamic_id = ? AND `dateofJoining` <=`endDate` "+
					"ORDER BY CAST(registerId AS SIGNED),FirstName,DEPT_NAME,`designationName`");

			psmt.setString(1, filterpojo.getDepartmentId());
			psmt.setString(2, filterpojo.getDesignationId());
			psmt.setString(3, filterpojo.getLocationId());
			psmt.setString(4, filterpojo.getAcademicYear());

			rs=psmt.executeQuery();
			while(rs.next()){
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
				filterVo.setSno(sno);
				filterVo.setAcademicYear(rs.getString("acadamic_year"));
				filterVo.setAcademicYearCode(rs.getString("acadamic_id"));
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDesignationId(rs.getString("designation"));
				filterVo.setDesignationName(rs.getString("designationName"));
				filterVo.setTeacherID(rs.getString("TeacherID"));
				filterVo.setTeacherName(rs.getString("FirstName"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				filterVo.setDepartmentId(rs.getString("DEPT_ID"));
				filterVo.setStatus(rs.getString("isActive"));
				filterVo.setStaffRegId(rs.getString("registerId"));
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


	public List<StudentRegistrationVo> getConfSearchListByLocation(String searchTerm, String locationid,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchListByLocation Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;

		int sno = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_CONF_SEARCH_BY_LOCATION);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");

			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));

				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchListByLocation Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConfSearchByFilter(String searchTerm,String locationid, String accyear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchByFilter Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_CONF_SEARCH_BY_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");
			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));

				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchByFilter Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConfSearchByClass(String searchTerm,String locationid,String accyear,String classname, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchByClass Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_CONF_SEARCH_BY_CLASS);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");
			pst.setString(10, className);
			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));

				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchByClass Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConfSearchByAllFilter(String searchTerm, String locationid, String accyear,
			String classname, String sectionid,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchByAllFilter Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int count = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement(SQLUtilConstants.GET_CONF_SEARCH_BY_ALL_FILTER);

			pst.setString(1, accYear);
			pst.setString(2, locationId);
			pst.setString(3, className);
			pst.setString(4, section);
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+searchValue+"%");
			pst.setString(9, "%"+searchValue+"%");
			pst.setString(10, "%"+searchValue+"%");
			pst.setString(11, "%"+searchValue+"%");
			pst.setString(12, "%"+searchValue+"%");
			pst.setString(13, "%"+searchValue+"%");

			//ln("GET_CONF_SEARCH_BY_ALL_FILTER -- ===="+pst);

			rs = pst.executeQuery();

			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setStudentFullName(rs.getString("studentName"));
				registrationVo.setStudentStatus(rs.getString("student_promotionstatus"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setCurrentAccyearId(rs.getString("acadamic_id"));
				String accyearid=getNextAcademicYearId(rs.getString("acadamic_id"),custdetails);
				registrationVo.setAcademicYearId(accyearid);
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));

				list.add(registrationVo);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getConfSearchByAllFilter Ending");

		return list;
	}

	public String deleteConfidentialDetails(String deleteId, String remarks, String log_audit_session, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteConfidentialDetails Starting");


		PreparedStatement pst = null;
		Connection conn = null;
		String deleteid = deleteId;
		int result = 0;
		String status = null;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.DELETE_CONFIDENTIAL_DETAIL);
			pst.setString(1,remarks);
			pst.setString(2,deleteid);
			result = pst.executeUpdate();
			//ln("from deleteConfidentialDetails :"+pst);

			if (result > 0) {
				HelperClass.recordLog_Activity(log_audit_session,"Student","Disciplinary Action","Delete",pst.toString(),pojo);
				status = MessageConstants.DELETE_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.DELETE_CONFIDENTIAL_REPORT_FAIL;
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteConfidentialDetails Ending");

		return status;
	}

	public String editConfidentialDetails(String entryDate, String comments, String editId, String log_audit_session, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : editConfidentialDetails Starting");


		PreparedStatement pst = null;
		Connection conn = null;
		String entrydate = entryDate;
		String comment = comments;
		String editid = editId;
		int result = 0;
		String status = null;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.EDIT_CONFIDENTIAL_DETAIL);
			pst.setString(1,HelperClass.convertUIToDatabase(entrydate));
			pst.setString(2,comment);
			pst.setString(3,editid);

			result = pst.executeUpdate();
			//ln("from editConfidentialDetails :"+pst);

			if (result > 0) {
				HelperClass.recordLog_Activity(log_audit_session,"Student","Disciplinary Action","Update",pst.toString(),pojo);
				status = MessageConstants.EDIT_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.EDIT_CONFIDENTIAL_REPORT_FAIL;

			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : editConfidentialDetails Ending");

		return status;
	}

	public List<StudentRegistrationVo> getStudentDetails(String userType, String userCode, String searchTerm,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		String academic_year=searchTerm.split(",")[0];
		String location=searchTerm.split(",")[1];
		String custid=searchTerm.split(",")[2];
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			if(userType.equalsIgnoreCase("perent")){
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_PARENT_STUDENT_DETAILS);
				pst.setString(1, userCode);
				//ln("vvvvvvvv = "+pst);
			}
			else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ALLSTUDENT_DETAILS);
				pst.setString(1, academic_year);
				pst.setString(2, location);
				//ln("vvvvvvvv1 = "+pst);
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassSectionId(rs.getString("classsection"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentFeeSearchByList(
			String searchTerm,String accyear, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentFeeSearchByList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;

		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_FEE_LIST);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			rs = pst.executeQuery();
			while(rs.next()){

				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				PreparedStatement pstmt = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt.setString(1, accyear);
				ResultSet rs1 = pstmt.executeQuery();
				while(rs1.next())
				{
					registrationVo.setAcademicYear(rs1.getString("acadamic_year"));
				}
				rs1.close();
				pstmt.close();
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setDateofBirth(rs.getString("student_dob_var"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setStatus(rs.getString("student_status_var"));

				list.add(registrationVo);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentFeeSearchByList Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentFeeSearchListByAccYear(
			String searchTerm, String accyear, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentFeeSearchListByAccYear Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;


		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_FEE_SEARCH_BY_ACCYEAR);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+searchValue+"%");


			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				//		
				PreparedStatement pstmt = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt.setString(1, accyear);
				ResultSet rs1 = pstmt.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				rs1.close();
				pstmt.close();
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setDateofBirth(rs.getString("student_dob_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));

				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentFeeSearchListByAccYear Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentFeeSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid, UserLoggingsPojo userLoggingsVo) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentFeeSearchByAllFilter Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_FEE_BY_ALL_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+locationId+"%");
			pst.setString(6, "%"+accYear+"%");
			pst.setString(7, className);
			pst.setString(8, section);

			rs = pst.executeQuery();			

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				PreparedStatement pstmt = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt.setString(1,accyear);
				ResultSet rs1 = pstmt.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				rs1.close();
				pstmt.close();
				stuReg.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setStudentStatus(rs.getString("student_status_var"));

				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentFeeSearchByAllFilter Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByFeeClass(
			String searchTerm, String locationid, String accyear,
			String classname,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFeeClass Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_FEE_CLASS);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");
			pst.setString(10, "%"+className+"%");

			rs = pst.executeQuery();
			//ln("Print Query For all the " +pst);
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				PreparedStatement pstmt1 =conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt1.setString(1, accyear);
				ResultSet rs1 = pstmt1.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				rs1.close();
				pstmt1.close();
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setStudentStatus(rs.getString("student_status_var"));

				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFeeClass Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByFeeFilter(
			String searchTerm, String locationid, String accyear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFeeFilter Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_FEE_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");

			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				//raji1
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt1.setString(1, accYear);
				ResultSet  rs1 =pstmt1.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				rs1.close();
				pstmt1.close();
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));

				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				stuReg.setStudentStatus(rs.getString("student_status_var"));

				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFeeFilter Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentDetailsLByFeeFilter(
			String locationid, String accyear, String classname, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetailsLByFeeFilter  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		//ln("Academic year for all Location:" +accyear);
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTDETAILS);
			pst.setString(1, locationid.trim());
			pst.setString(2, accyear.trim());
			pst.setString(3, classname.trim());

			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSection_id(rs.getString("classsection_id_int"));
				registrationVo.setStudentStatus(rs.getString("student_status_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentDetailsLByFeeFilter  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentListByFeeSection(
			String locationid, String accyear, String classname,
			String sectionid,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListByFeeSection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION);
			pst.setString(1, locationid.trim());
			pst.setString(2, accyear.trim());
			pst.setString(3, classname.trim());
			pst.setString(4, sectionid.trim());
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setStudentStatus(rs.getString("student_status_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListByFeeSection  Ending");

		return list;
	}

	public String updateStudentPromotion(StudentRegistrationVo formObj, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG); 
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : updateStudentPromotion Starting");
		PreparedStatement pstmt = null,pstmt1=null,pstmt2=null,pstmt3=null,pstmt4=null;
		ResultSet rs1 = null,rs3= null,rs4= null;
		Connection con = null;
		String message="",classId=null,sectionId=null,stream=null,newstream=null,accyearId=null;
		int status = 0,uploadStatus=0;
		try {

			con = JDBCConnection.getSeparateConnection(pojo);
			con.setAutoCommit(false);
			Timestamp createdDate = HelperClass.getCurrentTimestamp();
			pstmt = con.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_STUDENT_PROMOTION);
			pstmt.setString(1, formObj.getNewsection().trim());
			pstmt.setString(2, formObj.getClassTo().trim());
			pstmt.setString(3, formObj.getNewspecilaization().trim());
			pstmt.setString(4, formObj.getStudentStatus().trim());
			pstmt.setString(5, formObj.getComments());
			pstmt.setString(6, formObj.getCreateUser().trim());
			pstmt.setTimestamp(7, createdDate);
			pstmt.setString(8, formObj.getPromotedId().trim());
			//ln("UPDATE_STUDENT_PROMOTION "+pstmt);

			if(formObj.getStudentStatus().equalsIgnoreCase("demoted")) {
				pstmt1 = con.prepareStatement("SELECT DISTINCT csp.studentpromotion_acadamicyearfrom_var FROM campus_student_classdetails csc JOIN campus_student_promotion csp ON csp.student_id_int=csc.student_id_int WHERE csc.student_id_int=? AND csc.fms_acadamicyear_id_int=?");	 
				pstmt1.setString(1, formObj.getStudentId());
				pstmt1.setString(2, formObj.getAcademicYearId());
				//ln("updateStudentPromotion demoted 1-->>"+pstmt1);
				rs1=pstmt1.executeQuery();
				while(rs1.next()) {
					accyearId=rs1.getString(1);

					pstmt4 = con.prepareStatement("SELECT DISTINCT csc.fms_classstream_id_int,csc.classdetail_id_int,csc.classsection_id_int FROM campus_student_classdetails csc JOIN campus_student_promotion csp ON csp.student_id_int=csc.student_id_int WHERE csc.student_id_int=? AND csc.fms_acadamicyear_id_int=?");	 
					pstmt4.setString(1, formObj.getStudentId());
					pstmt4.setString(2, accyearId);
					rs4=pstmt4.executeQuery();
					while(rs4.next()) {
						stream=rs4.getString(1);
						classId=rs4.getString(2);
						sectionId=rs4.getString(3);
					}

				}
				pstmt2 = con.prepareStatement("UPDATE campus_student_classdetails SET fms_classstream_id_int=?,classdetail_id_int=?,classsection_id_int=? WHERE student_id_int=? AND fms_acadamicyear_id_int=?");
				pstmt2.setString(1, stream);
				pstmt2.setString(2, classId);
				pstmt2.setString(3, sectionId);
				pstmt2.setString(4, formObj.getStudentId());
				pstmt2.setString(5, formObj.getAcademicYearId());
				//ln("updateStudentPromotion demoted 2-->>"+pstmt2);
				pstmt2.executeUpdate();
			} 
			else if(formObj.getStudentStatus().equalsIgnoreCase("promoted")) {
				pstmt1 = con.prepareStatement("SELECT DISTINCT csc.fms_classstream_id_int,csc.classdetail_id_int,csc.classsection_id_int,csp.studentpromotion_acadamicyearfrom_var FROM campus_student_classdetails csc JOIN campus_student_promotion csp ON csp.student_id_int=csc.student_id_int WHERE csc.student_id_int=? AND csc.fms_acadamicyear_id_int=?");	 
				pstmt1.setString(1, formObj.getStudentId());
				pstmt1.setString(2, formObj.getAcademicYearId());
				//ln("updateStudentPromotion promoted 2-->>"+pstmt1);
				rs1=pstmt1.executeQuery();
				while(rs1.next()) {
					stream=rs1.getString(1);
					classId=rs1.getString(2);
					sectionId=rs1.getString(3);
					accyearId=rs1.getString(4);
				}

				pstmt3 = con.prepareStatement("SELECT DISTINCT classstream_id_int FROM campus_classdetail WHERE classdetail_id_int=? AND locationId=?");	 
				pstmt3.setString(1, formObj.getClassTo());
				pstmt3.setString(2, formObj.getLocationId());
				//ln("updateStudentPromotion promoted 3-->>"+pstmt3);
				rs3=pstmt3.executeQuery();
				while(rs3.next()) {
					newstream=rs3.getString(1);
				}

				pstmt2 = con.prepareStatement("UPDATE campus_student_classdetails SET fms_classstream_id_int=?,classdetail_id_int=?,classsection_id_int=? WHERE student_id_int=? AND fms_acadamicyear_id_int=?");
				pstmt2.setString(1, newstream);
				pstmt2.setString(2, formObj.getClassTo());
				pstmt2.setString(3, formObj.getNewsection());
				pstmt2.setString(4, formObj.getStudentId());
				pstmt2.setString(5, formObj.getAcademicYearId());
				//ln("updateStudentPromotion promoted 4-->>"+pstmt2);
				pstmt2.executeUpdate();
			}

			status = pstmt.executeUpdate();
			if ((status > 0)) {
				HelperClass.recordLog_Activity(formObj.getLog_audit_session(),"Student","Promotion","Update",pstmt.toString(),pojo);
				if ((formObj.getPromotedId()!= null)&&!(formObj.getPromotedId().equalsIgnoreCase(""))) {
					uploadStatus = updatePromotedStudentClassDetails(formObj,con,accyearId,newstream,pojo);
				}
				if ((uploadStatus > 0)) {
					con.commit();
					message = "success";
				} else {
					try {
						con.rollback();
					} catch (SQLException e1) {
						logger.error(e1.getMessage(), e1);
						e1.printStackTrace();
					}
					message = "fail";
				}
			} else {
				try {
					con.rollback();
				} catch (SQLException e1) {
					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
				message = "fail";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			try {
				if (rs3 != null && (!rs3.isClosed())) {
					rs3.close();
				}
				if (pstmt3 != null && (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (rs4 != null && (!rs4.isClosed())) {
					rs4.close();
				}
				if (pstmt4 != null && (!pstmt4.isClosed())) {
					pstmt4.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : updateStudentPromotion Ending");
		return message;
	}
	private int updatePromotedStudentClassDetails(StudentRegistrationVo formObj,Connection con,String accyearId,String newstream,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : updatePromotedStudentClassDetails Starting");
		PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
		Connection conn = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		try {
			conn = con;
			pstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_STUDENT_PROMOTED_CLASSSDETAILS);

			pstmt.setString(1, formObj.getNewspecilaization());
			pstmt.setString(2, formObj.getStudentStatus());
			pstmt.setString(3, formObj.getCreateUser().trim());
			pstmt.setTimestamp(4, createdDate);
			pstmt.setString(5, formObj.getStudentId());
			pstmt.setString(6, accyearId); /*formObj.getAcademicYearId()*/
			pstmt.setString(7, formObj.getLocationId());
			//ln("UPDATE_STUDENT_PROMOTED_CLASSSDETAILS-->>"+pstmt);
			status=pstmt.executeUpdate();

			//ln("formObj.getAcademicYearId() -->>"+formObj.getAcademicYearId());
			//ln("formObj.getAcademicYearId() -->>"+formObj.getAcademicYearId());
			//ln("accyearId -->>"+accyearId);
			//ln("accyearId -->>"+accyearId);

			pstmt1 = conn.prepareStatement("update campus_student_classdetails set student_status=? where student_id_int=? and fms_acadamicyear_id_int=? and locationId=?");
			if(formObj.getStudentStatus().equalsIgnoreCase("promoted")){
				pstmt1.setString(1,"STUDYING");
			}else if(formObj.getStudentStatus().equalsIgnoreCase("demoted")){
				pstmt1.setString(1,"STUDYING");
			}else{
				pstmt1.setString(1,"");
			}
			pstmt1.setString(2, formObj.getStudentId());
			pstmt1.setString(3, formObj.getAcademicYearId());  
			pstmt1.setString(4, formObj.getLocationId());
			pstmt1.executeUpdate();

			pstmt2 = conn.prepareStatement("update campus_student_classdetails set student_status=? where student_id_int=? and fms_acadamicyear_id_int=? and locationId=?");
			if(formObj.getStudentStatus().equalsIgnoreCase("promoted")){
				pstmt2.setString(1,"passed");
			}else if(formObj.getStudentStatus().equalsIgnoreCase("demoted")){
				pstmt2.setString(1,"failed");
			}else{
				pstmt2.setString(1,"");
			}
			pstmt2.setString(2, formObj.getStudentId());
			pstmt2.setString(3, accyearId);  
			pstmt2.setString(4, formObj.getLocationId());
			pstmt2.executeUpdate();

			if(status>0){
				HelperClass.recordLog_Activity(formObj.getLog_audit_session(),"Student","Promotion","Update",pstmt.toString(),userLoggingsVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{

			try {
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : updatePromotedStudentClassDetails Ending");
		return status;
	}
	public List<StudentRegistrationVo> getAllAcademicYearDemotedDetails(String locationId, String accYear, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAllAcademicYearDemotedDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null,pst1= null,pst2= null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		Connection conn = null;
		int count=0;
		String promotedAcademicId=null,promatedClassSection=null,promatedClass=null,promatedSection=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			//String nextYear=getNextAcademicYearId(accYear);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DEMOTED_ACCYEAR_LIST);
			pst.setString(1, locationId.trim());
			pst.setString(2, accYear.trim());
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}

				registrationVo.setClasssection(registrationVo.getClassname()+" - "+registrationVo.getSectionnaem());

				registrationVo.setSpecilization(rs.getString("specilization"));
				if(!rs.getString("Specialization_name").equalsIgnoreCase("-")){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				pst1 = conn.prepareStatement("SELECT DISTINCT ccd.classdetails_name_var,ccs.classsection_name_var,cay.acadamic_year FROM campus_student_promotion csp JOIN campus_acadamicyear cay ON csp.studentpromotion_acadamicyearto_var=cay.acadamic_id JOIN campus_classdetail ccd ON ccd.classdetail_id_int=csp.studentpromotion_toclass_var AND ccd.locationId=csp.locationId JOIN campus_classsection ccs ON ccs.classsection_id_int=csp.studentpromotion_tosection_var AND ccs.locationId=csp.locationId WHERE csp.studentpromotion_toclass_var=? AND csp.studentpromotion_tosection_var=? AND csp.studentpromotion_acadamicyearto_var=?");
				pst1.setString(1, rs.getString("studentpromotion_toclass_var"));
				pst1.setString(2, rs.getString("studentpromotion_tosection_var"));
				pst1.setString(3, rs.getString("studentpromotion_acadamicyearto_var"));
				rs1 = pst1.executeQuery();
				while (rs1.next()) {
					promotedAcademicId=rs1.getString("acadamic_year");
					promatedSection=rs1.getString("classsection_name_var");
				}

				pst2 = conn.prepareStatement("SELECT DISTINCT ccs.`classdetails_name_var` FROM campus_student_classdetails csc JOIN campus_classdetail ccs ON ccs.classdetail_id_int=csc.classdetail_id_int  WHERE csc.fms_acadamicyear_id_int=? AND csc.student_id_int=?");
				pst2.setString(1, rs.getString("studentpromotion_acadamicyearto_var"));
				pst2.setString(2, rs.getString("student_id_int"));
				rs2 = pst2.executeQuery();
				while (rs2.next()) {
					promatedClass=rs2.getString("classdetails_name_var");
				}

				registrationVo.setPromotedAcademicYearId(promotedAcademicId);
				registrationVo.setToClassSection(promatedClass+" - "+promatedSection);

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (pst2 != null && (!pst2.isClosed())) {
					pst2.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getAllAcademicYearDemotedDetails  Ending");

		return list;
	}


	public StudentRegistrationVo getStudentPromotedChange(String studentId, String accYear, 
			String locationId, String promotedId, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedChange Starting");

		StudentRegistrationVo studentRegVo = new StudentRegistrationVo();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);

			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_PROMOTED_CHANGE);
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);
			pstmObj.setString(4, promotedId);
			//ln("GET_STUDENT_PROMOTED_CHANGE -->>"+pstmObj);

			rs = pstmObj.executeQuery();
			while(rs.next())
			{
				count++;
				studentRegVo = new StudentRegistrationVo();

				studentRegVo.setCount(count);
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("studentName"));
				if(rs.getString("student_status").equalsIgnoreCase("passed")){
					studentRegVo.setStudentStatus("PASSED");
				}else if(rs.getString("student_status").equalsIgnoreCase("failed")){
					studentRegVo.setStudentStatus("FAILED");
				}else{
					studentRegVo.setStudentStatus(rs.getString("student_status"));
				}
				studentRegVo.setPromotionStatus(rs.getString("studentpromotion_status"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setClasscode(rs.getString("classdetail_id_int"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				studentRegVo.setSectioncode(rs.getString("classsection_id_int"));
				studentRegVo.setSpecilization(rs.getString("Specialization_Id"));
				
				if(!rs.getString("Specialization_name").equalsIgnoreCase("-")){
					studentRegVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					studentRegVo.setSpecilizationname("");
				}
				studentRegVo.setTospec(rs.getString("studentpromotion_toSpecialization"));
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setLocation(rs.getString("Location_Name"));

				studentRegVo.setCurrentAccyearId(rs.getString("acadamic_id"));
				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				if(rs.getString("housename") != null){
					studentRegVo.setHouseName(rs.getString("housename"));	
				}else{
					studentRegVo.setHouseName("");
				}
				studentRegVo.setPromotionId(rs.getInt("studentpromotion_id_int"));
				studentRegVo.setSectionto(rs.getString("studentpromotion_tosection_var"));

				studentRegVo.setClassTo(rs.getString("studentpromotion_toclass_var"));
				studentRegVo.setComments(rs.getString("comments"));

			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedChange Ending");

		return studentRegVo;
	}

	public List<StudentRegistrationVo> getStudentDemotedSearchByList(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDemotedSearchByList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;

		int count = 0;

		try{

			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_DEMOTED_SEARCH_BY_LIST);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");

			rs = pst.executeQuery();

			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}


				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				list.add(registrationVo);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDemotedSearchByList Ending");

		return list;
	}
	public List<StudentRegistrationVo> getAllAcademicYearPromotedDetails(String locationId, String accYear, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAllAcademicYearPromotedDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null,pst1= null,pst2= null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		Connection conn = null;
		int count=0;
		String promotedAcademicId=null,promatedClassSection=null,promatedClass=null,promatedSection=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			//String nextYear=getNextAcademicYearId(accYear);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_PROMOTED_ACCYEAR_LIST);
			pst.setString(1, locationId.trim());
			pst.setString(2, accYear.trim());
			//ln("STUDENT_PROMOTED_ACCYEAR_LIST -->>"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}

				registrationVo.setClasssection(registrationVo.getClassname()+" - "+registrationVo.getSectionnaem());

				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int")); 

				pst1 = conn.prepareStatement("SELECT DISTINCT ccd.classdetails_name_var,ccs.classsection_name_var,cay.acadamic_year FROM campus_student_promotion csp JOIN campus_acadamicyear cay ON csp.studentpromotion_acadamicyearto_var=cay.acadamic_id JOIN campus_classdetail ccd ON ccd.classdetail_id_int=csp.studentpromotion_toclass_var AND ccd.locationId=csp.locationId JOIN campus_classsection ccs ON ccs.classsection_id_int=csp.studentpromotion_tosection_var AND ccs.locationId=csp.locationId WHERE csp.studentpromotion_toclass_var=? AND csp.studentpromotion_tosection_var=? AND csp.studentpromotion_acadamicyearto_var=?");
				pst1.setString(1, rs.getString("studentpromotion_toclass_var"));
				pst1.setString(2, rs.getString("studentpromotion_tosection_var"));
				pst1.setString(3, rs.getString("studentpromotion_acadamicyearto_var"));
				//ln("Ashish pst1 -->>"+pst1);
				rs1 = pst1.executeQuery();
				while (rs1.next()) {
					promotedAcademicId=rs1.getString("acadamic_year");
					promatedSection=rs1.getString("classsection_name_var");
				}

				pst2 = conn.prepareStatement("SELECT DISTINCT ccs.`classdetails_name_var` FROM campus_student_classdetails csc JOIN campus_classdetail ccs ON ccs.classdetail_id_int=csc.classdetail_id_int  WHERE csc.fms_acadamicyear_id_int=? AND csc.student_id_int=?");
				pst2.setString(1, rs.getString("studentpromotion_acadamicyearto_var"));
				pst2.setString(2, rs.getString("student_id_int"));
				////ln("pst2 -->>"+pst2);
				rs2 = pst2.executeQuery();
				while (rs2.next()) {
					promatedClass=rs2.getString("classdetails_name_var");
				}

				registrationVo.setPromotedAcademicYearId(promotedAcademicId);
				registrationVo.setToClassSection(promatedClass+" - "+promatedSection);

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (pst2 != null && (!pst2.isClosed())) {
					pst2.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getAllAcademicYearPromotedDetails  Ending");


		return list;
	}


	public String toCheckNextYearAvailable(String academicyearid, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : toCheckNextYearAvailable Starting");

		PreparedStatement preparedStatement = null,preparedStatement1 = null,preparedStatement2=null;
		ResultSet rs = null,rs1 = null,rs2=null;
		String enddate = null,accyear=null;
		String academicYearId="";
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);

			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_NEXTACADEMIC_YEAR);
			preparedStatement.setString(1, academicyearid);
			rs = preparedStatement.executeQuery();
			int count=0,count1=0;
			while (rs.next()) {
				enddate = rs.getString("endDate");
				accyear = rs.getString("acadamic_year");
				SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );   
				Calendar cal = Calendar.getInstance();    
				cal.setTime( dateFormat.parse(enddate));
				cal.add( Calendar.DATE, 1 );    
				String convertedDate=dateFormat.format(cal.getTime());    

				preparedStatement1 = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_NEXTYEAR_COUNT);
				preparedStatement1.setString(1, convertedDate);
				rs1 = preparedStatement1.executeQuery();
				while(rs1.next()){
					count=rs1.getInt("count");
				}
				if(count == 0){
					String[] accyearsplit=accyear.split("-");
					preparedStatement2 = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_NEXTYEARSPLIT_COUNT);
					preparedStatement2.setString(1, accyearsplit[1].trim()+"%");
					rs2 = preparedStatement2.executeQuery();
					while(rs2.next()){
						count1=rs2.getInt("countval");
					}
					if(count1 == 0){
						academicYearId="Next Year Not Avaliable";
					}else{
						academicYearId="Next Year Avaliable";
					}
				}else{
					academicYearId="Next Year Avaliable";
				}
			}



		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (preparedStatement1 != null
						&& (!preparedStatement1.isClosed())) {
					preparedStatement1.close();
				}
				if (preparedStatement2 != null
						&& (!preparedStatement2.isClosed())) {
					preparedStatement2.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : toCheckNextYearAvailable Ending");
		return academicYearId;
	}

	public List<StudentRegistrationVo> getPromotedClassSectionList(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getPromotedClassSectionList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null,pst1= null;
		ResultSet rs = null,rs1= null;
		Connection conn = null;
		int count=0;
		String promotedAcademicId=null,promatedClassSection=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			//String nextYear=getNextAcademicYearId(regVo.getAcademicYearId());
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_PROMOTED_SECTIONLIST); 
			pst.setString(1, regVo.getLocationId());
			pst.setString(2, regVo.getAcademicYearId());
			pst.setString(3, regVo.getClasscode());
			pst.setString(4, regVo.getClassSectionId());

			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}

				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				registrationVo.setClasssection(registrationVo.getClassname()+" - "+registrationVo.getSectionnaem());

				pst1 = conn.prepareStatement("SELECT DISTINCT ccd.classdetails_name_var,ccs.classsection_name_var,cay.acadamic_year FROM campus_student_promotion csp JOIN campus_acadamicyear cay ON csp.studentpromotion_acadamicyearto_var=cay.acadamic_id JOIN campus_classdetail ccd ON ccd.classdetail_id_int=csp.studentpromotion_toclass_var AND ccd.locationId=csp.locationId JOIN campus_classsection ccs ON ccs.classsection_id_int=csp.studentpromotion_tosection_var AND ccs.locationId=csp.locationId WHERE csp.studentpromotion_toclass_var=? AND csp.studentpromotion_tosection_var=? AND csp.studentpromotion_acadamicyearto_var=?");
				pst1.setString(1, rs.getString("studentpromotion_toclass_var"));
				pst1.setString(2, rs.getString("studentpromotion_tosection_var"));
				pst1.setString(3, rs.getString("studentpromotion_acadamicyearto_var"));
				rs1 = pst1.executeQuery();
				while (rs1.next()) {
					promotedAcademicId=rs1.getString("acadamic_year");
					promatedClassSection=rs1.getString("classdetails_name_var")+" - "+rs1.getString("classsection_name_var");
				}
				registrationVo.setPromotedAcademicYearId(promotedAcademicId);
				registrationVo.setToClassSection(promatedClassSection); 

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getPromotedClassSectionList  Ending");
		return list;
	}

	public List<StudentRegistrationVo> getPromotedClassList(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getPromotedClassList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null,pst1= null;
		ResultSet rs = null,rs1= null;
		Connection conn = null;
		int count=0;
		String promotedAcademicId=null,promatedClassSection=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			//String nextYear=getNextAcademicYearId(regVo.getAcademicYearId());
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_PROMOTED_CLASSLIST); 
			pst.setString(1, regVo.getLocationId());
			pst.setString(2, regVo.getAcademicYearId());
			pst.setString(3, regVo.getClasscode());
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				registrationVo.setClasssection(registrationVo.getClassname()+" - "+registrationVo.getSectionnaem());

				pst1 = conn.prepareStatement("SELECT DISTINCT ccd.classdetails_name_var,ccs.classsection_name_var,cay.acadamic_year FROM campus_student_promotion csp JOIN campus_acadamicyear cay ON csp.studentpromotion_acadamicyearto_var=cay.acadamic_id JOIN campus_classdetail ccd ON ccd.classdetail_id_int=csp.studentpromotion_toclass_var AND ccd.locationId=csp.locationId JOIN campus_classsection ccs ON ccs.classsection_id_int=csp.studentpromotion_tosection_var AND ccs.locationId=csp.locationId WHERE csp.studentpromotion_toclass_var=? AND csp.studentpromotion_tosection_var=? AND csp.studentpromotion_acadamicyearto_var=?");
				pst1.setString(1, rs.getString("studentpromotion_toclass_var"));
				pst1.setString(2, rs.getString("studentpromotion_tosection_var"));
				pst1.setString(3, rs.getString("studentpromotion_acadamicyearto_var")); 
				rs1 = pst1.executeQuery();
				while (rs1.next()) {
					promotedAcademicId=rs1.getString("acadamic_year");
					promatedClassSection=rs1.getString("classdetails_name_var")+" - "+rs1.getString("classsection_name_var");
				}
				registrationVo.setPromotedAcademicYearId(promotedAcademicId);
				registrationVo.setToClassSection(promatedClassSection);

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getPromotedClassList  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getDemotedListDetails(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getDemotedListDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null,pst1= null,pst2= null;
		ResultSet rs = null,rs1= null,rs2= null;
		Connection conn = null;
		int count=0;
		String promotedAcademicId=null,promatedClassSection=null,promatedClass=null,promatedSection=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			//String nextYear=getNextAcademicYearId(regVo.getAcademicYearId());
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DEMOTED_LIST);
			pst.setString(1, regVo.getLocationId());
			pst.setString(2, regVo.getAcademicYearId());
			//ln("demoted list "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}

				registrationVo.setClasssection(registrationVo.getClassname()+" - "+registrationVo.getSectionnaem());

				registrationVo.setSpecilization(rs.getString("specilization"));
				if(!rs.getString("Specialization_name").equalsIgnoreCase("-")){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				pst1 = conn.prepareStatement("SELECT DISTINCT ccd.classdetails_name_var,ccs.classsection_name_var,cay.acadamic_year FROM campus_student_promotion csp JOIN campus_acadamicyear cay ON csp.studentpromotion_acadamicyearto_var=cay.acadamic_id JOIN campus_classdetail ccd ON ccd.classdetail_id_int=csp.studentpromotion_toclass_var AND ccd.locationId=csp.locationId JOIN campus_classsection ccs ON ccs.classsection_id_int=csp.studentpromotion_tosection_var AND ccs.locationId=csp.locationId WHERE csp.studentpromotion_toclass_var=? AND csp.studentpromotion_tosection_var=? AND csp.studentpromotion_acadamicyearto_var=?");
				pst1.setString(1, rs.getString("studentpromotion_toclass_var"));
				pst1.setString(2, rs.getString("studentpromotion_tosection_var"));
				pst1.setString(3, rs.getString("studentpromotion_acadamicyearto_var"));
				//ln("Promoted pst1 -->>"+pst1);
				rs1 = pst1.executeQuery();
				while (rs1.next()) {
					promotedAcademicId=rs1.getString("acadamic_year");
					promatedSection=rs1.getString("classsection_name_var");
				}

				pst2 = conn.prepareStatement("SELECT DISTINCT ccs.`classdetails_name_var` FROM campus_student_classdetails csc JOIN campus_classdetail ccs ON ccs.classdetail_id_int=csc.classdetail_id_int  WHERE csc.fms_acadamicyear_id_int=? AND csc.student_id_int=?");
				pst2.setString(1, rs.getString("studentpromotion_acadamicyearto_var"));
				pst2.setString(2, rs.getString("student_id_int"));
				//ln("Promoted pst2 -->>"+pst2);
				rs2 = pst2.executeQuery();
				while (rs2.next()) {
					promatedClass=rs2.getString("classdetails_name_var");
				}


				registrationVo.setPromotedAcademicYearId(promotedAcademicId);
				registrationVo.setToClassSection(promatedClass+" - "+promatedSection);

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (pst2 != null && (!pst2.isClosed())) {
					pst2.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getDemotedListDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentPromotingSearchByAllFilter(String searchTerm, String locationid, 
			String accyear,String classname, String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotingSearchByAllFilter Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int count = 0;
		//ln("Inside the dao impl");
		try{
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTING_SEARCH_BY_ALL_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, accYear);
			pst.setString(9, locationId);
			pst.setString(10, className);
			pst.setString(11, section);
			//ln("list of record "+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setStudentFullName(rs.getString("studentName"));
				registrationVo.setStudentStatus(rs.getString("student_promotionstatus"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setCurrentAccyearId(rs.getString("acadamic_id"));
				String accyearid=getNextAcademicYearId(rs.getString("acadamic_id"),null);
				registrationVo.setAcademicYearId(accyearid);
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));

				list.add(registrationVo);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotingSearchByAllFilter Ending");

		return list;
	}

	public List<StudentRegistrationVo> getDemotedClassSectionList(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getDemotedClassSectionList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null,pst1= null,pst2= null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		Connection conn = null;
		int count=0;
		String promotedAcademicId=null,promatedClassSection=null,promatedClass=null,promatedSection=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			//String nextYear=getNextAcademicYearId(regVo.getAcademicYearId());
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DEMOTED_SECTIONLIST);
			pst.setString(1, regVo.getLocationId());
			pst.setString(2, regVo.getAcademicYearId());
			pst.setString(3, regVo.getClasscode());
			pst.setString(4, regVo.getClassSectionId());

			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}

				registrationVo.setClasssection(registrationVo.getClassname()+" - "+registrationVo.getSectionnaem());

				registrationVo.setSpecilization(rs.getString("specilization"));
				if(!rs.getString("Specialization_name").equalsIgnoreCase("-")){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				pst1 = conn.prepareStatement("SELECT DISTINCT ccd.classdetails_name_var,ccs.classsection_name_var,cay.acadamic_year FROM campus_student_promotion csp JOIN campus_acadamicyear cay ON csp.studentpromotion_acadamicyearto_var=cay.acadamic_id JOIN campus_classdetail ccd ON ccd.classdetail_id_int=csp.studentpromotion_toclass_var AND ccd.locationId=csp.locationId JOIN campus_classsection ccs ON ccs.classsection_id_int=csp.studentpromotion_tosection_var AND ccs.locationId=csp.locationId WHERE csp.studentpromotion_toclass_var=? AND csp.studentpromotion_tosection_var=? AND csp.studentpromotion_acadamicyearto_var=?");
				pst1.setString(1, rs.getString("studentpromotion_toclass_var"));
				pst1.setString(2, rs.getString("studentpromotion_tosection_var"));
				pst1.setString(3, rs.getString("studentpromotion_acadamicyearto_var"));
				rs1 = pst1.executeQuery();
				while (rs1.next()) {
					promotedAcademicId=rs1.getString("acadamic_year");
					promatedSection=rs1.getString("classsection_name_var");
				}

				pst2 = conn.prepareStatement("SELECT DISTINCT ccs.`classdetails_name_var` FROM campus_student_classdetails csc JOIN campus_classdetail ccs ON ccs.classdetail_id_int=csc.classdetail_id_int  WHERE csc.fms_acadamicyear_id_int=? AND csc.student_id_int=?");
				pst2.setString(1, rs.getString("studentpromotion_acadamicyearto_var"));
				pst2.setString(2, rs.getString("student_id_int"));
				rs2 = pst2.executeQuery();
				while (rs2.next()) {
					promatedClass=rs2.getString("classdetails_name_var");
				}

				registrationVo.setPromotedAcademicYearId(promotedAcademicId);
				registrationVo.setToClassSection(promatedClass+" - "+promatedSection);

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (pst2 != null && (!pst2.isClosed())) {
					pst2.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getDemotedClassSectionList  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getDemotedClassList(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getDemotedClassList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null,pst1= null,pst2= null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		Connection conn = null;
		int count=0;
		String promotedAcademicId=null,promatedClassSection=null,promatedClass=null,promatedSection=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DEMOTED_CLASSLIST);
			pst.setString(1, regVo.getLocationId());
			pst.setString(2, regVo.getAcademicYearId());
			pst.setString(3, regVo.getClasscode());

			//ln("STUDENT_DEMOTED_CLASSLIST -->> "+pst);

			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}

				registrationVo.setClasssection(registrationVo.getClassname()+" - "+registrationVo.getSectionnaem());

				registrationVo.setSpecilization(rs.getString("specilization"));
				if(!rs.getString("Specialization_name").equalsIgnoreCase("-")){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				pst1 = conn.prepareStatement("SELECT DISTINCT ccd.classdetails_name_var,ccs.classsection_name_var,cay.acadamic_year FROM campus_student_promotion csp JOIN campus_acadamicyear cay ON csp.studentpromotion_acadamicyearto_var=cay.acadamic_id JOIN campus_classdetail ccd ON ccd.classdetail_id_int=csp.studentpromotion_toclass_var AND ccd.locationId=csp.locationId JOIN campus_classsection ccs ON ccs.classsection_id_int=csp.studentpromotion_tosection_var AND ccs.locationId=csp.locationId WHERE csp.studentpromotion_toclass_var=? AND csp.studentpromotion_tosection_var=? AND csp.studentpromotion_acadamicyearto_var=?");
				pst1.setString(1, rs.getString("studentpromotion_toclass_var"));
				pst1.setString(2, rs.getString("studentpromotion_tosection_var"));
				pst1.setString(3, rs.getString("studentpromotion_acadamicyearto_var"));
				rs1 = pst1.executeQuery();
				while (rs1.next()) {
					promotedAcademicId=rs1.getString("acadamic_year");
					promatedSection=rs1.getString("classsection_name_var");
				}

				pst2 = conn.prepareStatement("SELECT DISTINCT ccs.`classdetails_name_var` FROM campus_student_classdetails csc JOIN campus_classdetail ccs ON ccs.classdetail_id_int=csc.classdetail_id_int  WHERE csc.fms_acadamicyear_id_int=? AND csc.student_id_int=? AND ccs.isActive='Y'");
				pst2.setString(1, rs.getString("studentpromotion_acadamicyearto_var"));
				pst2.setString(2, rs.getString("student_id_int"));
				rs2 = pst2.executeQuery();
				while (rs2.next()) {
					promatedClass=rs2.getString("classdetails_name_var");
				}

				registrationVo.setPromotedAcademicYearId(promotedAcademicId);
				registrationVo.setToClassSection(promatedClass+" - "+promatedSection);

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (pst2 != null && (!pst2.isClosed())) {
					pst2.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getDemotedClassList  Ending");


		return list;
	}

	public StudentRegistrationVo getStudentWisePromotion(String studentId, String accYear, String locationId, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentWisePromotion Starting");

		StudentRegistrationVo studentRegVo = new StudentRegistrationVo();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_WISE_PROMOTION);
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);

			//ln("GET_STUDENT_WISE_PROMOTION -->>"+pstmObj);

			rs = pstmObj.executeQuery();
			while(rs.next())
			{
				count++;
				studentRegVo = new StudentRegistrationVo();

				studentRegVo.setCount(count);
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("studentName"));
				studentRegVo.setStudentStatus(rs.getString("student_status"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setClasscode(rs.getString("classdetail_id_int"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				studentRegVo.setSectioncode(rs.getString("classsection_id_int"));
				studentRegVo.setSpecilization(rs.getString("Specialization_Id"));
				if(rs.getString("Specialization_name") != null){
					studentRegVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					studentRegVo.setSpecilizationname("");
				}
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setLocation(rs.getString("Location_Name"));

				studentRegVo.setCurrentAccyearId(rs.getString("acadamic_id"));
				String accyearid=getNextAcademicYearId(rs.getString("acadamic_id"),custdetails);
				studentRegVo.setAcademicYearId(accyearid);

				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				if(rs.getString("housename") != null){
					studentRegVo.setHouseName(rs.getString("housename"));	
				}else{
					studentRegVo.setHouseName("");
				}


			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentWisePromotion Ending");

		return studentRegVo;
	}

	public String toCheckNextClassAvailable(String toclass, String locationId, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : toCheckNextClassAvailable Starting");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int count = 0;
		String status=null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);

			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CHECK_EXITING_CLASS);
			preparedStatement.setString(1, toclass);
			preparedStatement.setString(2, locationId);
			//ln(preparedStatement);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				count = rs.getInt("count");

			}
			if(count == 0){
				status="notpresent";
			}else{
				status="present";
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null
						&& (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : toCheckNextClassAvailable Ending");
		return status;
	}

	public List<StudentRegistrationVo> getAllStudentPromotionClassSectionDetails(String academic_year, String location,
			String classId, String sectionid, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAllStudentPromotionClassSectionDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_PROMOTION_DEMOTION);
			pst.setString(1, academic_year);
			pst.setString(2, location);
			pst.setString(3, classId);
			pst.setString(4, sectionid);

			//ln("STUDENT_PROMOTION_DEMOTION  --=="+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setStudentFullName(rs.getString("studentName"));
				registrationVo.setStudentStatus(rs.getString("student_promotionstatus"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setCurrentAccyearId(rs.getString("acadamic_id"));
				String accyearid=getNextAcademicYearId(rs.getString("acadamic_id"),custdetails);
				registrationVo.setAcademicYearId(accyearid);
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getAllStudentPromotionClassSectionDetails  Ending");


		return list;
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByList(String searchTerm, String status,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;

		int count = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTED_SEARCH_BY_LIST);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, status);
			//ln("lis "+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}


				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				list.add(registrationVo);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByList Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchListByAccYear(String searchTerm, String accyear,String locationid, String classname,
			String sectionid, String status,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchListByAccYear Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String accYear = accyear;

		int count = 0;

		try{
			//String nextYear=getNextAcademicYearId(accYear);
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTED_SEARCH_BY_ACCYEAR);
			pst.setString(1, locationid);
			pst.setString(2, accYear);
			pst.setString(3, classname);
			pst.setString(4, sectionid);

			pst.setString(5, "%"+searchValue+"%"); 
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+searchValue+"%");
			pst.setString(9, "%"+searchValue+"%");
			pst.setString(10, "%"+searchValue+"%");
			pst.setString(11, "%"+searchValue+"%"); 
			pst.setString(12, status);

			//ln("GET_STUDENTS_PROMOTED_SEARCH_BY_ACCYEAR ---==="+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				list.add(registrationVo);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchListByAccYear Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchListByLocation(String searchTerm, String locationid, String status,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchListByLocation Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;

		int count = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTED_SEARCH_BY_LOCATION);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, locationId);
			pst.setString(9, status);
			//ln("lis "+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}
				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				list.add(registrationVo);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchListByLocation Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByFilter(String searchTerm, String locationid, String accyear, String status,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByFilter Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		int count = 0;

		try{
			//String nextYear=getNextAcademicYearId(accYear);
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTED_SEARCH_BY_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, locationId);
			pst.setString(9, accYear);
			pst.setString(10, status);
			//ln("lis "+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}


				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				list.add(registrationVo);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByFilter Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByClass(String searchTerm, String locationid, String accyear,String classname, String status,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByClass Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		int count = 0;

		try{
			//String nextYear=getNextAcademicYearId(accYear);
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTED_SEARCH_BY_CLASS);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, locationId);
			pst.setString(9, accYear);
			pst.setString(10, className);
			pst.setString(11, status);
			//ln("execute query "+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}


				registrationVo.setSpecilization(rs.getString("specilization"));
				if(rs.getString("Specialization_name") != null){
					registrationVo.setSpecilizationname(rs.getString("Specialization_name"));
				}else{
					registrationVo.setSpecilizationname("-");
				}
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				list.add(registrationVo);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByClass Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname,
			String sectionid, String status,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByAllFilter Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null,pst1= null,pst2=null;
		ResultSet rs = null,rs1= null,rs2= null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int count = 0;
		String promotedAcademicId=null,promatedClassSection=null,promatedClass=null,promatedSection=null;
		try{
			//String nextYear=getNextAcademicYearId(accYear);
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_PROMOTED_SEARCH_BY_ALL_FILTER); 
			pst.setString(1, searchValue+"%");
			pst.setString(2, searchValue+"%");
			pst.setString(3, searchValue+"%");
			pst.setString(4, searchValue+"%");
			pst.setString(5, searchValue+"%");
			pst.setString(6, searchValue+"%");
			pst.setString(7, searchValue+"%");
			pst.setString(8, searchValue+"%");
			pst.setString(9, searchValue+"%");
			pst.setString(10, searchValue+"%");
			pst.setString(11, searchValue+"%");
			pst.setString(12, searchValue+"%");
			pst.setString(13, locationId);
			pst.setString(14, accYear);
			pst.setString(15, className);
			pst.setString(16, section);
			pst.setString(17, status);
			rs = pst.executeQuery();

			while(rs.next()){
				count++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(count);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				if(rs.getString("student_rollno") != null){
					registrationVo.setStudentrollno(rs.getString("student_rollno"));
				}else{
					registrationVo.setStudentrollno("");
				}
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				if(rs.getString("classsection_name_var") != null){
					registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				}else{
					registrationVo.setSectionnaem("Not Selected");
				}

				registrationVo.setClasssection(registrationVo.getClassname()+" - "+registrationVo.getSectionnaem());
				registrationVo.setSpecilization(rs.getString("fromspec"));
				
				registrationVo.setTospec(rs.getString("tospec"));
				registrationVo.setFromspec(rs.getString("fromspec"));
				
				registrationVo.setAcademicYearId(rs.getString("acadamic_id"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setPromotionId(rs.getInt("studentpromotion_id_int"));

				pst1 = conn.prepareStatement("SELECT DISTINCT ccd.classdetails_name_var,ccs.classsection_name_var,cay.acadamic_year FROM campus_student_promotion csp JOIN campus_acadamicyear cay ON csp.studentpromotion_acadamicyearto_var=cay.acadamic_id JOIN campus_classdetail ccd ON ccd.classdetail_id_int=csp.studentpromotion_toclass_var AND ccd.locationId=csp.locationId JOIN campus_classsection ccs ON ccs.classsection_id_int=csp.studentpromotion_tosection_var AND ccs.locationId=csp.locationId WHERE csp.studentpromotion_toclass_var=? AND csp.studentpromotion_tosection_var=? AND csp.studentpromotion_acadamicyearto_var=?");
				pst1.setString(1, rs.getString("studentpromotion_toclass_var"));
				pst1.setString(2, rs.getString("studentpromotion_tosection_var"));
				pst1.setString(3, rs.getString("studentpromotion_acadamicyearto_var"));
				////ln("GET_STUDENTS_PROMOTED_SEARCH_BY_ALL_FILTER pst1 -->>"+pst1);
				rs1 = pst1.executeQuery();
				while (rs1.next()) {
					promotedAcademicId=rs1.getString("acadamic_year");
					promatedSection=rs1.getString("classsection_name_var");
				}

				pst2 = conn.prepareStatement("SELECT DISTINCT ccs.`classdetails_name_var` FROM campus_student_classdetails csc JOIN campus_classdetail ccs ON ccs.classdetail_id_int=csc.classdetail_id_int  WHERE csc.fms_acadamicyear_id_int=? AND csc.student_id_int=?");
				pst2.setString(1, rs.getString("studentpromotion_acadamicyearto_var"));
				pst2.setString(2, rs.getString("student_id_int"));
				////ln("GET_STUDENTS_PROMOTED_SEARCH_BY_ALL_FILTER pst2 -->>"+pst2);
				rs2 = pst2.executeQuery();
				while (rs2.next()) {
					promatedClass=rs2.getString("classdetails_name_var");
				}

				registrationVo.setPromotedAcademicYearId(promotedAcademicId);
				registrationVo.setToClassSection(promatedClass+" - "+promatedSection);

				list.add(registrationVo);
			}


		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (pst2 != null && (!pst2.isClosed())) {
					pst2.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentPromotedSearchByAllFilter Ending");

		return list;
	}


	public List<StudentRegistrationVo> getStudentDetailsexcel(String searchTerm, String location,
			String currentaccyear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetailsexcel  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		try {
			//ln("Inside the getStudentDetails");
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ALLSTUDENT_DETAILS);
			pst.setString(1, currentaccyear);
			pst.setString(2, location);
			//ln("pst is "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassSectionId(rs.getString("classsection"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentDetailsexcel  Ending");

		return list;
	}



	public List<StudentRegistrationVo> getStudentDetailsByJs(
			StudentRegistrationVo data, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetailsByJs  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;


		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			if(data.getSearchTerm().equalsIgnoreCase("all") && data.getLocationId().equalsIgnoreCase("all")){
				pst = conn.prepareStatement("select distinct stu.student_status_var,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where  csc.fms_acadamicyear_id_int=?  and stu.student_admissionno_var NOT IN(select admissionNo from campus_scholorship where concessionType='full' and academic_year=?) AND stu.student_status_var='active' order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname");
				pst.setString(1, data.getAcademicYear());
				pst.setString(2, data.getAcademicYear());
			}
			else if(data.getSearchTerm().equalsIgnoreCase("all") && data.getClasscode().equalsIgnoreCase("all")){
				pst = conn.prepareStatement("select distinct stu.student_status_var,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where csc.locationId=? and csc.fms_acadamicyear_id_int=?  and stu.student_admissionno_var NOT IN(select admissionNo from campus_scholorship where concessionType='full' and academic_year=?) AND stu.student_status_var='active' order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname");
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());

				pst.setString(3, data.getAcademicYear());
			}
			else if(data.getSearchTerm().equalsIgnoreCase("all") && data.getSection_id().equalsIgnoreCase("all")){
				pst = conn.prepareStatement("select distinct stu.student_status_var,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=? and stu.student_admissionno_var NOT IN(select admissionNo from campus_scholorship where concessionType='full' and academic_year=?) AND stu.student_status_var='active' order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname");
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				pst.setString(3, data.getClasscode());
				pst.setString(4, data.getAcademicYear());
			}
			else if(data.getSearchTerm().equalsIgnoreCase("all")){
				pst = conn.prepareStatement("select distinct stu.student_status_var,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=? and ccs.classsection_id_int=? and stu.student_admissionno_var NOT IN(select admissionNo from campus_scholorship where concessionType='full' and academic_year=?) AND stu.student_status_var='active' order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname"); /*AND stu.student_status_var='active'*/
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				pst.setString(3, data.getClasscode());
				pst.setString(4, data.getSection_id());
				pst.setString(5, data.getAcademicYear());
			}
			else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST_SEARCH_BY_JS_FOR_FEE);
				if(data.getLocationId().equalsIgnoreCase("all")){
					pst.setString(1, "%%");
				}
				else{
					pst.setString(1, data.getLocationId());
				}

				if(data.getAcademicYear().equalsIgnoreCase("all")){
					pst.setString(2, "%%");
				}
				else{
					pst.setString(2, data.getAcademicYear());
				}
				if(data.getClasscode().equalsIgnoreCase("all")){
					pst.setString(3, "%%");
				}
				else{
					pst.setString(3, data.getClasscode());
				}
				if(data.getSection_id().equalsIgnoreCase("all")){
					pst.setString(4, "%%");
				}
				else{
					pst.setString(4, data.getSection_id());
				}

				pst.setString(5, data.getSearchTerm() + "%");
				pst.setString(6, data.getSearchTerm() + "%");
				pst.setString(7, data.getSearchTerm() + "%");
				pst.setString(8, data.getSearchTerm() + "%");
				pst.setString(9, data.getSearchTerm() + "%");
				pst.setString(10, data.getSearchTerm() + "%");
				pst.setString(11, data.getSearchTerm() + "%");
				pst.setString(12, data.getSearchTerm() + "%");
				pst.setString(13, data.getSearchTerm() + "%");
				pst.setString(14, data.getSearchTerm() + "%");
				pst.setString(15, data.getAcademicYear());

			}
			//ln("studentcCCCC "+pst);
			rs = pst.executeQuery();



			while (rs.next()) {
				ArrayList<FeeNameVo> feeStatusList=new ArrayList<FeeNameVo>();
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;

				if(rs.getString("student_status_var").equalsIgnoreCase("active")){
					registrationVo.setStatus("Active");
				}else if(rs.getString("student_status_var").equalsIgnoreCase("inactive")){
					registrationVo.setStatus("InActive");
				}
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setImage(rs.getString("imgurl"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setClassSectionId(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setDateofBirth(rs.getString("student_dob_var"));
				PreparedStatement termpstmt=conn.prepareStatement("SELECT termid,termname FROM `campus_fee_termdetails` WHERE locationId=? AND accyear=?");
				termpstmt.setString(1, rs.getString("locationId"));
				termpstmt.setString(2, data.getAcademicYear());
				ResultSet termRs=termpstmt.executeQuery();
				while(termRs.next()){
					int getcount=0;
					String paymentStatus="Not Paid";
					FeeNameVo fStatus=new FeeNameVo();
					PreparedStatement getStatus=conn.prepareStatement("SELECT COUNT(*) FROM  campus_fee_collection WHERE termcode=? AND admissionNo=?");
					getStatus.setString(1, termRs.getString("termid"));
					getStatus.setString(2, rs.getString("student_id_int"));
					ResultSet getstRs=getStatus.executeQuery();
					if(getstRs.next()){
						getcount=getstRs.getInt(1);
					}
					if(getcount > 0){
						paymentStatus="Paid";
					}
					fStatus.setTerm(termRs.getString("termname"));
					fStatus.setStatus(paymentStatus);
					feeStatusList.add(fStatus);
				}
				registrationVo.setFeeStatus(feeStatusList);
				termRs.close();
				termpstmt.close();


				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentDetailsByJs  Ending");

		return list;
	}

	public String getlocationteacher(String userCode) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getlocationteacher  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String locid=null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement("select Loc_ID from campus_teachers where TeacherID = ?");
			pst.setString(1,userCode);
			rs = pst.executeQuery();
			while(rs.next()){
				locid = rs.getString("Loc_ID");
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null	&& (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : getlocationteacher Ending");
		return locid;
	}

	public String toGetStreamName(String toClass, String locationId, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : toGetStreamName Starting");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String status=null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			preparedStatement = conn.prepareStatement(StudentRegistrationSQLUtilConstants.TOGET_STREAMNAME);
			preparedStatement.setString(1, toClass);
			preparedStatement.setString(2, locationId);
			//ln(preparedStatement);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				status = rs.getString("classstream_id_int");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null
						&& (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : toGetStreamName Ending");
		return status;
	}
	public String saveRollNo(StudentRegistrationVo svo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveRollNo Starting");


		PreparedStatement pst = null;
		Connection conn = null;
		int result = 0;
		String status = null;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.SAVE_ROLL_NO);
			for(int i=0;i<svo.getStudentIdArray().length;i++){
				pst.setString(1,svo.getRollNoArray()[i]);
				pst.setString(2,svo.getStudentIdArray()[i]);
				pst.setString(3,svo.getAcademicyear_toArray()[i]);
				pst.setString(4,svo.getLocationIdArray()[i]);

				result = pst.executeUpdate();
				//ln("saveRollNo:"+pst);

				if (result > 0) {
					HelperClass.recordLog_Activity(svo.getLog_audit_session(),"Student","Generate Roll No","Update",pst.toString(),pojo);
					status = "true";
				} else {
					status ="false";

				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveRollNo Ending");

		return status;
	}

	public List<StudentRegistrationVo> individualStudentSearch(String studentId, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : individualStudentSearch Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		int count = 0;


		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_CLASS_HISTORY);
			pstmObj.setString(1,studentId);
			//ln("GET_STUDENT_CLASS_HISTORY -->>"+pstmObj);
			rs = pstmObj.executeQuery();
			while(rs.next())
			{	
				count++;
				StudentRegistrationVo obj = new StudentRegistrationVo();
				obj.setCount(count);
				obj.setAcademicYear(rs.getString("acadamic_year"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSectionnaem(rs.getString("classsection_name_var"));
				obj.setStudentrollno(rs.getString("student_rollno"));
				obj.setStatus(rs.getString("student_status"));
				obj.setActive(rs.getString("student_status_var"));
				list.add(obj);

			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : individualStudentSearch Ending");

		return list;
	}

	public List<StudentRegistrationVo> individualStudentcontact(String studentId,UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : individualStudentcontact Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		int count = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmObj = conn.prepareStatement("SELECT DISTINCT CASE WHEN cp.student_gaurdianname_var IS NULL THEN '-' WHEN cp.student_gaurdianname_var='' THEN '-' ELSE cp.student_gaurdianname_var END student_gaurdianname_var,CASE WHEN cp.student_gardian_mobileno IS NULL THEN '-' WHEN cp.student_gardian_mobileno='' THEN '-' ELSE cp.student_gardian_mobileno END student_gardian_mobileno,cp.FatherName,cp.mobileno,cp.student_mothername_var,cp.student_mothermobileno_var FROM campus_parents cp LEFT JOIN campus_parentchildrelation cpr ON cp.ParentID=cpr.parentid  LEFT JOIN campus_student cs ON cpr.stu_addmissionNo=cs.student_id_int WHERE cs.student_id_int=?");
			pstmObj.setString(1,studentId);      /*GET_STUDENT_CONTACT_HISTORY*/
			rs = pstmObj.executeQuery();
			while(rs.next())
			{	
				count++;
				StudentRegistrationVo obj = new StudentRegistrationVo();
				obj.setCount(count);
				obj.setFatherName(rs.getString("FatherName"));
				obj.setFatherMobileNo(rs.getString("mobileno"));
				obj.setMotherName(rs.getString("student_mothername_var"));
				obj.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				obj.setGaurdianName(rs.getString("student_gaurdianname_var"));
				obj.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				list.add(obj);

			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : individualStudentcontact Ending");

		return list;
	}

	public List<StudentRegistrationVo> getLanguageList(String classId, String schoolLocation) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getLanguageList  Starting");
		if(schoolLocation.equalsIgnoreCase("all")){
			schoolLocation="%%";
		}

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SECTION);
			pst.setString(1, classId.trim());
			pst.setString(2, schoolLocation);
			//ln("pst "+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setSectioncode(rs
						.getString("classsection_id_int"));
				registrationVo.setSectionnaem(rs
						.getString("classsection_name_var"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getLanguageList  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentListForTransport(String academic_year, String location, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListForTransport  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		String academicYear = academic_year;
		String locationName = location;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);

			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST_FOR_TRANSPORT);
			pst.setString(1, locationName);
			pst.setString(2, academicYear);
			pst.setString(3, academicYear);

			rs = pst.executeQuery();
			//ln("Student list from fee Collection:" +pst);


			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListForTransport  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentListForTransportFilteration(
			String accyear, String location, String classId, String divisionId,
			String searchby, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListForTransportFilteration  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;


		try {
			conn = JDBCConnection.getSeparateConnection(pojo);

			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST_FOR_TRANSPORT_BY_FILTERATION);
			pst.setString(1, accyear);
			pst.setString(2, location);
			pst.setString(3, accyear);
			pst.setString(4, classId);
			pst.setString(5, divisionId);
			pst.setString(6, searchby+"%");
			pst.setString(7, searchby+"%");
			pst.setString(8, searchby+"%");
			pst.setString(9, searchby+"%");

			//ln("GET_STUDENT_LIST_FOR_TRANSPORT_BY_FILTERATION -->>" +pst);

			rs = pst.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListForTransportFilteration  Ending");

		return list;
	}


	public List<StudentRegistrationVo> getStudentWithheldList(String academic_year, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentWithheldList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = academic_year;
		String loc_Id = location;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_WITHHELD_LIST);
			pst.setString(1, loc_Id);/*GET_STUDENTS_BY_CONFIDENTIAL_REPORT*/
			pst.setString(2, acc_year);

			rs = pst.executeQuery();


			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));

				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentWithheldList Ending");

		return list;
	}

	public List<StudentRegistrationVo> singleStudentWithHeld(String studentId, String accYear, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentWithHeld Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		PreparedStatement pstmObj2 = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;

		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_WITHHELD_DETAIL);
			pstmObj.setString(1, loc_Id);                    
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);
			//ln("GET_SINGLE_STUDENT_WITHHELD_DETAIL -->>"+pstmObj);
			rs = pstmObj.executeQuery();

			while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();

				count++;
				studentRegVo.setCount(count);
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				studentRegVo.setStudentStatus(rs.getString("student_status"));
				studentRegVo.setLocation(rs.getString("Location_Name"));
				studentRegVo.setParentId(rs.getString("parentid"));
				studentRegVo.setFatherName(rs.getString("FatherName"));
				studentRegVo.setFatherMobileNo(rs.getString("mobileno"));
				studentRegVo.setMotherName(rs.getString("student_mothername_var"));
				studentRegVo.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentRegVo.setGaurdianName(rs.getString("student_gaurdianname_var"));
				studentRegVo.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));

				pstmObj2 = conn.prepareStatement("SELECT csh.house_id,csh.student_id,chs.housename FROM campus_student_house csh LEFT JOIN campus_house_settings chs ON chs.house_id=csh.house_id WHERE csh.loc_id LIKE ? AND csh.student_id=? AND csh.academic_year=?");
				pstmObj2.setString(1, loc_Id);
				pstmObj2.setString(2, stu_Id);
				pstmObj2.setString(3, acc_year);
				rst = pstmObj2.executeQuery();
				//ln("pstmObj2"+pstmObj2);
				while(rst.next())
				{
					if(rst.getString("house_id") == null || rst.getString("house_id") == ""){
						studentRegVo.setHouseName("");
					}
					else{
						studentRegVo.setHouseName(rst.getString("housename"));
					}
				}

				list.add(studentRegVo);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (pstmObj2 != null && (!pstmObj2.isClosed())) {
					pstmObj2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentWithHeld Ending");
		return list;
	}

	public String AddWithHeldDetails(StudentRegistrationVo studentvo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : AddWithHeldDetails Starting");


		PreparedStatement pst = null;
		Connection conn = null;

		int result = 0;
		String status = null;

		try{

			conn = JDBCConnection.getSeparateConnection(pojo);
			if(studentvo.getStatus().equalsIgnoreCase("yes")){
				pst = conn.prepareStatement(SQLUtilConstants.ADD_WITHELD_DETAIL1); /*ADD_CONFIDENTIAL_DETAIL*/

				pst.setString(1,studentvo.getAccyear());
				pst.setString(2,studentvo.getLocationId());
				pst.setString(3,studentvo.getStudentId());
				pst.setString(4,HelperClass.convertUIToDatabase(studentvo.getEntryDate()));
				pst.setString(5,studentvo.getStatus());
				pst.setString(6,studentvo.getComments());
				pst.setString(7,studentvo.getCreateUser().trim());
				pst.setString(8,studentvo.getWithheldId());

				//ln("///////////////////"+pst);

				result = pst.executeUpdate();
			}
			else{
				pst = conn.prepareStatement(SQLUtilConstants.ADD_WITHELD_DETAIL); /*ADD_CONFIDENTIAL_DETAIL*/

				pst.setString(1,studentvo.getAccyear());
				pst.setString(2,studentvo.getLocationId());
				pst.setString(3,studentvo.getStudentId());
				pst.setString(4,HelperClass.convertUIToDatabase(studentvo.getEntryDate()));
				pst.setString(5,studentvo.getStatus());
				pst.setString(6,studentvo.getComments());
				pst.setString(7,HelperClass.convertUIToDatabase(studentvo.getCancelDate()));
				pst.setString(8,studentvo.getCancelcomment());
				pst.setString(9,studentvo.getCreateUser().trim());
				pst.setString(10,studentvo.getWithheldId());

				//ln("99999999999999"+pst);

				result = pst.executeUpdate();
			}
			if (result > 0) {
				HelperClass.recordLog_Activity(studentvo.getLog_audit_session(),"Student","Student WithHeld Details","Insert",pst.toString(),pojo);
				status = MessageConstants.ADD_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.ADD_CONFIDENTIAL_REPORT_FAIL;

			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : AddWithHeldDetails Ending");

		return status;
	}

	public String EditWithHeldDetails(StudentRegistrationVo studentvo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : EditWithHeldDetails Starting");

		PreparedStatement pst = null;
		Connection conn = null;

		int result = 0;
		String status = null,value=null;

		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			if (studentvo.getStatus().equalsIgnoreCase("yes")) {
				pst = conn.prepareStatement(SQLUtilConstants.EDIT_WITHHELD_DETAIL1);  

				pst.setString(1, HelperClass.convertUIToDatabase(studentvo.getEntryDate()));
				pst.setString(2, studentvo.getStatus());
				pst.setString(3, studentvo.getComments());
				pst.setString(4, studentvo.getCreateUser());
				pst.setTimestamp(5, HelperClass.getCurrentTimestamp());
				pst.setString(6, studentvo.getWithheldId());
				result = pst.executeUpdate();
				value="Update";
				//ln("from editConfidentialDetails :" + pst);
			} else {
				pst = conn.prepareStatement(SQLUtilConstants.EDIT_WITHHELD_DETAIL);  
				pst.setString(1, HelperClass.convertUIToDatabase(studentvo.getEntryDate()));
				pst.setString(2, studentvo.getStatus());
				pst.setString(3, studentvo.getComments());
				pst.setString(4, HelperClass.convertUIToDatabase(studentvo.getCancelDate()));
				pst.setString(5, studentvo.getCancelcomment());
				pst.setString(6, studentvo.getCreateUser());
				pst.setTimestamp(7, HelperClass.getCurrentTimestamp());
				pst.setString(8, studentvo.getWithheldId());
				result = pst.executeUpdate();
				value="Cancel";
				//ln("from editConfidentialDetails 111111111111 :" + pst);
			}
			if (result > 0) {
				HelperClass.recordLog_Activity(studentvo.getLog_audit_session(),"Student","Student WithHeld Details",value,pst.toString(),pojo);
				status = MessageConstants.EDIT_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.EDIT_CONFIDENTIAL_REPORT_FAIL;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : EditWithHeldDetails Ending");

		return status;
	}


	//studentRegVo.setCancelcomment(rs.getString("withheld_cancel_reason"));


	public String AddApparisalDetails(StudentRegistrationVo vo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : AddApparisalDetails  Starting");

		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		String status=null;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		//ln(vo.getSchedualdate());
		//ln(vo.getMeetingon());
		try{

			if(!vo.getHiddenid().equalsIgnoreCase(""))
			{

				conn= JDBCConnection.getSeparateConnection(pojo);
				pstmt=conn.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_APPRAISAL);

				pstmt.setString(1, vo.getStudentId());
				pstmt.setString(2, vo.getAcademicYearId());
				pstmt.setString(3, vo.getLocationId());
				pstmt.setString(4, vo.getActiontaken());
				pstmt.setString(5, HelperClass.convertUIToDatabase(vo.getSchedualdate()));
				pstmt.setString(6, vo.getRecomendedby());
				/*pstmt.setString(7, HelperClass.convertUIToDatabase(vo.getMeetingdate()));*/
				/*pstmt.setString(8, vo.getMeetingwith());*/
				/*pstmt.setTime(9, HelperClass.getStringToTime(vo.getMeetingon()));*/
				pstmt.setString(7, vo.getRemarks());
				pstmt.setString(8, vo.getCreateUser());
				pstmt.setTimestamp(9, createdDate);
				pstmt.setString(10, vo.getHiddenid());

				//ln("UPDATE_APPRAISAL -->>"+pstmt);
				result = pstmt.executeUpdate();


				if (result > 0) {
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Student","Student Appraisal Action","Update",pstmt.toString(),pojo);
					status = MessageConstants.UPDATE_APPRAISAL_REPORT_SUCCESS;
				} else {
					status = MessageConstants.UPDATE_APPRAISAL_REPORT_FAIL;
				}



			}else{
				conn= JDBCConnection.getSeparateConnection(pojo);
				pstmt=conn.prepareStatement(StudentRegistrationSQLUtilConstants.ADD_APPRAISAL);

				pstmt.setString(1, vo.getStudentId());
				pstmt.setString(2, vo.getAcademicYearId());
				pstmt.setString(3, vo.getLocationId());
				pstmt.setString(4, vo.getActiontaken());
				pstmt.setString(5, HelperClass.convertUIToDatabase(vo.getSchedualdate()));
				pstmt.setString(6, vo.getRecomendedby());

				pstmt.setString(7, vo.getRemarks());
				pstmt.setTimestamp(8, createdDate);
				pstmt.setString(9, vo.getCreateUser());
				result = pstmt.executeUpdate();

				if (result > 0) {
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Student","Student Appraisal Action","Insert",pstmt.toString(),pojo);
					status = MessageConstants.ADD_APPRAISAL_REPORT_SUCCESS;
				} else {
					status = MessageConstants.ADD_APPRAISAL_REPORT_FAIL;

				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}

		}


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : AddApparisalDetails  Ending");


		return status;
	}

	public List<StudentRegistrationVo> singleStudentDetailReport(String studentId, String accyear, String locationid, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentDetailReport  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try{
			conn=JDBCConnection.getSeparateConnection(pojo);
			pstmt=conn.prepareStatement(StudentRegistrationSQLUtilConstants.APPRAISAL_REPORT);
			pstmt.setString(2, studentId);
			pstmt.setString(3, accyear);
			pstmt.setString(1, locationid);
			rs=pstmt.executeQuery();
			/*	//ln("999999999999999999"+pstmt);*/
			while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
				count++;
				studentRegVo.setCount(count);
				studentRegVo.setStudentappraisalid(rs.getString("student_appraisal_id"));
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));

				studentRegVo.setLocationId(rs.getString("locationId"));
				studentRegVo.setActiontaken(rs.getString("action_taken"));
				studentRegVo.setSchedualdate(HelperClass.convertDatabaseToUI(rs.getString("schedule_date")));
				if(rs.getString("meeting_date") == null || rs.getString("meeting_date").equalsIgnoreCase(""))
					studentRegVo.setMeetingdate(rs.getString("meeting_date"));
				else
					studentRegVo.setMeetingdate(HelperClass.convertDatabaseToUI(rs.getString("meeting_date")));
				studentRegVo.setMeetingwith(rs.getString("meeting_with"));
				studentRegVo.setMeetingon((rs.getString("meeting_on")));
				studentRegVo.setStaffname(rs.getString("staffname"));
				studentRegVo.setRecomendedby(rs.getString("recommented_by_Id"));
				studentRegVo.setRemark(rs.getString("remarks"));
				list.add(studentRegVo);
			}

		}
		catch(Exception e){
			e.printStackTrace();

		}
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentDetailReport  Ending");

		return list;
	}

	public String deleteApparaisalDetails(String deleteId, String log_audit_session, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteApparaisalDetails Starting");


		PreparedStatement pst = null;
		Connection conn = null;
		String deleteid = deleteId;
		int result = 0;
		String status = null;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.DETAIL_APPARAISAL_DETAIL);
			pst.setString(1,deleteid);

			result = pst.executeUpdate();
			//ln("from deleteConfidentialDetails :"+pst);

			if (result > 0) {
				HelperClass.recordLog_Activity(log_audit_session,"Student","Student Appraisal Action","Delete",pst.toString(),pojo);
				status = MessageConstants.DELETE_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.DELETE_CONFIDENTIAL_REPORT_FAIL;

			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteApparaisalDetails Ending");

		return status;
	}


	public List<secadmissionformformatVO> searchStudentByTempAdmission(String studentName, String parentName, String mobileNo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByTempAdmission Starting");

		List<secadmissionformformatVO> registrationList = new ArrayList<secadmissionformformatVO>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			secadmissionformformatVO list = new secadmissionformformatVO();

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			String sql = "select temporary_admission_id,concat(studentfirstName,' ',studentlastname) as studentname,dateofBirthId,preferedphno,schemeofstudy,classname,fathername,fathermobileno,mothername,mothermobile,createdTime from campus_temporary_admisssion_details where (";
			String remain_sql = null;
			String key = null;
			String val = null;
			String query = null;
			int count =0;

			HashMap map = new HashMap();
			ArrayList arr = new ArrayList();
			Vector vec = new Vector();


			map.put("concat(studentfirstName,' ',studentlastname)", studentName);
			vec.add("concat(studentfirstName,' ',studentlastname)");

			map.put("fathername", parentName);
			vec.add("fathername");

			map.put("mothername", parentName);
			vec.add("mothername");

			map.put("fathermobileno", mobileNo);
			vec.add("fathermobileno");

			map.put("mothermobile", mobileNo);
			vec.add("mothermobile");

			Iterator it = vec.iterator();

			while(it.hasNext())
			{
				key = it.next().toString();
				val = map.get(key).toString();


				if(count == 0)
				{
					remain_sql = key +" like '%"+val+"%'";
					count++;
				}
				else if(count == 1)
				{
					remain_sql = remain_sql + " and (";
					remain_sql = remain_sql + key +" like '"+val+"'";
					count++;
				}
				else if(count == 2)
				{
					remain_sql = remain_sql + " or ";
					remain_sql = remain_sql + key +" like '"+val+"'";
					remain_sql = remain_sql + ")";
					count++;
				}
				else if(count == 3)
				{
					remain_sql = remain_sql + " and (";
					remain_sql = remain_sql + key +" like '"+val+"'";
					count++;
				}
				else if(count == 4)
				{
					remain_sql = remain_sql + " or ";
					remain_sql = remain_sql + key +" like '"+val+"'";
					remain_sql = remain_sql + ")";

				}

			}

			remain_sql = remain_sql + ")";
			query = sql + remain_sql+" and STATUS='submitted' ORDER BY createdTime";

			//ln("query  "+query);
			pstmObj = conn.prepareStatement(query);

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				secadmissionformformatVO templist = new secadmissionformformatVO();
				secadmissionformformatVO vo = new secadmissionformformatVO();
				templist.setTemp_regid(rs.getString("temporary_admission_id"));
				templist.setStudentname(rs.getString("studentname"));
				templist.setDateofBirth(rs.getString("dateofBirthId"));
				templist.setFatherName(rs.getString("fathername"));

				if(rs.getString("fathername").equals(null) || rs.getString("fathername").equals(""))
				{
					templist.setFatherName("mothername");
				}

				templist.setFatherMobileNo(rs.getString("fathermobileno"));

				if((rs.getString("fathername").equals(null) || rs.getString("fathername").equals("")) && 
						(rs.getString("fathermobileno").equals(null) || rs.getString("fathermobileno").equals("")))
				{
					templist.setFatherMobileNo("mothermobile");
				}
				templist.setSchemeofstudy(rs.getString("schemeofstudy"));
				templist.setCreated_date(rs.getString("createdTime"));

				registrationList.add(templist);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByTempAdmission Ending");

		return registrationList;
	}

	public List<StudentRegistrationVo> searchStudentByStudentName(
			StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByStudentName Starting");
		String searchTerm = "%" + registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmObj = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_STUDENTNAME);

			pstmObj.setString(1, searchTerm);
			pstmObj.setString(2, searchTerm);

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setStudentnamelabel(rs.getString("studentname"));
				studentRegistrationVo.setStudentidlabel(rs.getString("temporary_admission_id"));
				registrationList.add(studentRegistrationVo);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByStudentName Ending");

		return registrationList;

	}

	public List<StudentRegistrationVo> searchStudentByParentName(
			StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByParentName Starting");

		String searchTerm = "%" + registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmObj = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_PARENTNAME);

			pstmObj.setString(1, searchTerm);
			pstmObj.setString(2, searchTerm);

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();

				if(rs.getString("fathername").equals(null) || rs.getString("fathername").equals(""))
				{
					studentRegistrationVo.setParentNameLabel(rs.getString("mothername"));
				}
				else
				{
					studentRegistrationVo.setParentNameLabel(rs.getString("fathername"));
				}

				registrationList.add(studentRegistrationVo);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByParentName Ending");

		return registrationList;

	}

	public List<StudentRegistrationVo> searchStudentByMobileNo(
			StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByMobileNo Starting");

		String searchTerm = "%" + registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmObj = conn
					.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SEARCH_BY_MOBILENO);

			pstmObj.setString(1, searchTerm);
			pstmObj.setString(2, searchTerm);

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();

				if(rs.getString("fathermobileno").equals(null) || rs.getString("fathermobileno").equals(""))
				{
					studentRegistrationVo.setParentMobileLabel(rs.getString("mothermobile"));
				}
				else
				{
					studentRegistrationVo.setParentMobileLabel(rs.getString("fathermobileno"));
				}

				registrationList.add(studentRegistrationVo);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByMobileNo Ending");

		return registrationList;

	}



	public List<StudentRegistrationVo> singleStudentWithHeldDetailsList(String studentId, String accyear,
			String locationid, String flag, String status, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentWithHeldDetailsList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accyear;
		String loc_Id = locationid;
		String flag_tab = flag;


		int count = 0;

		try{
			if(flag_tab.equalsIgnoreCase("withheld"))
			{
				conn = JDBCConnection.getSeparateConnection(pojo);
				pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_WITHHELD_DETAILS_BY_HISTORY);
				pstmObj.setString(1, loc_Id);
				pstmObj.setString(2, stu_Id);
				pstmObj.setString(3, acc_year);
				rs = pstmObj.executeQuery();
				//ln("GET_SINGLE_STUDENT_WITHHELD_DETAILS_BY_HISTORY -->>"+pstmObj);

				while(rs.next())
				{
					StudentRegistrationVo studentRegVo = new StudentRegistrationVo();
					count++;
					studentRegVo.setCount(count);
					studentRegVo.setConfidentialId(rs.getString("withheld_id"));
					studentRegVo.setStudentId(rs.getString("student_id_int"));
					studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
					studentRegVo.setLocationId(rs.getString("locationId"));
					studentRegVo.setConfidentialEntryDate(HelperClass.convertDatabaseToUI(rs.getString("withheld_date")));
					studentRegVo.setConfidentialComments(rs.getString("withheld_reason"));
					studentRegVo.setStatus(rs.getString("withheld_status"));
					if(rs.getString("withheld_cancelled_date") != null){
						studentRegVo.setCancelDate(HelperClass.convertDatabaseToUI(rs.getString("withheld_cancelled_date")));
					}else{
						studentRegVo.setCancelDate("");
					}

					if(rs.getString("withheld_cancel_reason") != null){
						studentRegVo.setCancelcomment(rs.getString("withheld_cancel_reason"));
					}else{
						studentRegVo.setCancelDate("");
					}

					list.add(studentRegVo);

				}
			}else if(flag_tab.equalsIgnoreCase("cancel")){
				conn = JDBCConnection.getSeparateConnection(pojo);
				pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_WITHHELD_DETAILS_BY_CANCEL);
				pstmObj.setString(1, loc_Id);
				pstmObj.setString(2, stu_Id);
				pstmObj.setString(3, acc_year);
				rs = pstmObj.executeQuery();

				while(rs.next())
				{
					StudentRegistrationVo studentRegVo = new StudentRegistrationVo();

					count++;
					studentRegVo.setCount(count);
					studentRegVo.setConfidentialId(rs.getString("withheld_id"));
					studentRegVo.setStudentId(rs.getString("student_id_int"));
					studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
					studentRegVo.setLocationId(rs.getString("locationId"));
					studentRegVo.setConfidentialEntryDate(HelperClass.convertDatabaseToUI(rs.getString("withheld_date")));
					studentRegVo.setConfidentialComments(rs.getString("withheld_reason"));
					studentRegVo.setStatus(rs.getString("withheld_status"));
					if(rs.getString("withheld_cancelled_date") != null){
						studentRegVo.setCancelDate(HelperClass.convertDatabaseToUI(rs.getString("withheld_cancelled_date")));
					}else{
						studentRegVo.setCancelDate("");
					}
					if(rs.getString("withheld_cancel_reason") != null){
						studentRegVo.setCancelcomment(rs.getString("withheld_cancel_reason"));
					}else{
						studentRegVo.setCancelDate("");
					}
					/*studentRegVo.setCancelcomment(rs.getString("withheld_cancel_reason"));*/

					list.add(studentRegVo);
				}
			}

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentWithHeldDetailsList Ending");
		return list;

	}

	public String deleteWithHeldDetails(String deleteId, String log_audit_session, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteWithHeldDetails Starting");


		PreparedStatement pst = null;
		Connection conn = null;
		String deleteid = deleteId;
		int result = 0;
		String status = null;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.DELETEL_WITHHELD_DETAIL);
			pst.setString(1,deleteid);

			result = pst.executeUpdate();
			//ln("from deleteWithHeldDetails :"+pst);

			if (result > 0) {
				HelperClass.recordLog_Activity(log_audit_session,"Student","Student WithHeld Details","Delete",pst.toString(),pojo);
				status = MessageConstants.DELETE_CONFIDENTIAL_REPORT_SUCCESS;
			} else {
				status = MessageConstants.DELETE_CONFIDENTIAL_REPORT_FAIL;

			}

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deleteWithHeldDetails Ending");

		return status;
	}

	public List<StudentRegistrationVo> getStudentListBySpecialization(ExaminationDetailsVo details,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySpecialization  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		try {

			String locationid = details.getLocationid();
			String accyear = details.getAccyear();
			String classname = details.getClassid();
			String sectionid = details.getSectionid();
			String specializationName = details.getSpecialization();

			if(specializationName.equalsIgnoreCase("all")){
				specializationName="%%";
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SPECIALIZATION);
				pst.setString(1, specializationName);
				pst.setString(2, locationid);
				pst.setString(3, classname);
				pst.setString(4, sectionid);
				pst.setString(5, accyear);

			}
			else{
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SPECIALIZATION);
				pst.setString(1, specializationName);
				pst.setString(2, locationid);
				pst.setString(3, classname);
				pst.setString(4, sectionid);
				pst.setString(5, accyear);

			}
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, specializationName);

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySpecialization  Ending");

		return list;
	}





	public java.util.List<StudentRegistrationVo> getStudentListByJsForScholorship(
			StudentRegistrationVo data, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListByJsForScholorship  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
		String locId=null,accYearId=null,ClassId=null,SectionId=null;
		locId=data.getLocationId();
		accYearId=data.getAcademicYear();
		ClassId=data.getClasscode();
		SectionId=data.getSection_id();
		if(locId=="" || locId.equalsIgnoreCase("all")){
			locId="%%";
		}
		if(accYearId=="" || accYearId.equalsIgnoreCase("all")){
			accYearId="%%";
		}
		if(ClassId=="" || ClassId.equalsIgnoreCase("all")){
			ClassId="%%";
		}
		if(SectionId=="" || SectionId.equalsIgnoreCase("all")){
			SectionId="%%";
		}

		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			String query="select distinct cct.concession_name,cshc.remark,cshc.isActive,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,stu.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var,cshc.concession,cshc.concessionType from campus_scholorship cshc join campus_concession_types cct on cshc.concessionType=cct.contype_id join campus_student stu on stu.student_admissionno_var=cshc.admissionNo join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id  where ";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!locId.equalsIgnoreCase("%%")) {
				map.put("csc.locationId",locId);
				vec.add("csc.locationId");
			}
			if(!accYearId.equalsIgnoreCase("%%") ) {
				map.put("csc.fms_acadamicyear_id_int",accYearId);
				vec.add("csc.fms_acadamicyear_id_int");
			}
			if(!ClassId.equalsIgnoreCase("%%")) {
				map.put("csc.classdetail_id_int",ClassId);
				vec.add("csc.classdetail_id_int");
			}
			if(!SectionId.equalsIgnoreCase("%%")) {
				map.put("ccs.classsection_id_int",SectionId);
				vec.add("ccs.classsection_id_int");
			}
			if(!data.getIsActive().equalsIgnoreCase("%%")) {
				map.put("cshc.isActive",data.getIsActive());
				vec.add("cshc.isActive");
			}

			Enumeration<String> e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql=key+" = '"+val+"'";
					count++;
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
				}
			}

			String finalquery="";
			if(wheresql != null) {
				finalquery=query+" "+wheresql+" and "+"(stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or stu.student_dob_var like ? or stu.student_status_var like ? or CONCAT(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ? or ca.acadamic_year like ?) GROUP BY stu.student_admissionno_var"; 
			}else {
				finalquery=query+" "+"(stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or stu.student_dob_var like ? or stu.student_status_var like ? or CONCAT(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ? or ca.acadamic_year like ?)  GROUP BY stu.student_admissionno_var";
			}
			pst = conn.prepareStatement(finalquery);
			/*	pst.setString(1,  searchvalue + "%");
			pst.setString(2,  searchvalue + "%");
			pst.setString(3,  searchvalue + "%");
			pst.setString(4,  searchvalue + "%");
			pst.setString(5,  searchvalue + "%");
			pst.setString(6,  searchvalue + "%");
			pst.setString(7,  searchvalue + "%");
			pst.setString(8,  searchvalue + "%");*/
			/*if(data.getSearchTerm().equalsIgnoreCase("all")){
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST_BY_JS_FOR_SCHOLORSHIP);
				pst.setString(1, locId);
				pst.setString(2, accYearId);
				pst.setString(3, ClassId);
				pst.setString(4, SectionId);
				pst.setString(5, data.getIsActive());
			}
			else{
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST_SEARCH_BY_JS_FOR_SCHOLORSHIP);*/

			pst.setString(1, data.getSearchTerm() + "%");
			pst.setString(2, data.getSearchTerm() + "%");
			pst.setString(3, data.getSearchTerm() + "%");
			pst.setString(4, data.getSearchTerm() + "%");
			pst.setString(5, data.getSearchTerm() + "%");
			pst.setString(6, data.getSearchTerm() + "%");
			pst.setString(7, data.getSearchTerm() + "%");
			pst.setString(8, data.getSearchTerm() + "%");
			pst.setString(9, data.getSearchTerm() + "%");
			pst.setString(10, data.getSearchTerm() + "%");
			/*}*/
			//ln("student "+pst);
			rs = pst.executeQuery();

			while (rs.next()) {
				ArrayList<FeeNameVo> feeStatusList=new ArrayList<FeeNameVo>();
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setImage(rs.getString("imgurl"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setClassSectionId(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setDateofBirth(rs.getString("student_dob_var"));
				registrationVo.setConcession(rs.getString("concession"));
				registrationVo.setConcessionType(rs.getString("concession_name"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")) {
					registrationVo.setIsActive("Active");
				}
				else {
					registrationVo.setIsActive("InActive");
				}
				if(rs.getString("remark")=="" || rs.getString("remark")==null){
					registrationVo.setRemark("");
				}else{
					registrationVo.setRemark(rs.getString("remark"));
				}



				PreparedStatement termpstmt=conn.prepareStatement("SELECT termid,termname FROM `campus_fee_termdetails` WHERE locationId=? AND accyear=?");
				termpstmt.setString(1, rs.getString("locationId"));
				termpstmt.setString(2, data.getAcademicYear());
				ResultSet termRs=termpstmt.executeQuery();
				while(termRs.next()){
					int getcount=0;
					String paymentStatus="Not Paid";
					FeeNameVo fStatus=new FeeNameVo();
					PreparedStatement getStatus=conn.prepareStatement("SELECT COUNT(*) FROM  campus_fee_collection WHERE termcode=? AND admissionNo=?");
					getStatus.setString(1, termRs.getString("termid"));
					getStatus.setString(2, rs.getString("student_id_int"));
					ResultSet getstRs=getStatus.executeQuery();
					if(getstRs.next()){
						getcount=getstRs.getInt(1);
					}
					if(getcount > 0){
						paymentStatus="Paid";
					}
					fStatus.setTerm(termRs.getString("termname"));
					fStatus.setStatus(paymentStatus);
					feeStatusList.add(fStatus);
				}
				registrationVo.setFeeStatus(feeStatusList);
				list.add(registrationVo);
				termRs.close();
				termpstmt.close();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListByJsForScholorship  Ending");

		return list;
	}


	public List<StudentRegistrationVo> getIDCardPhotoSheet(String sectionId,String classId, String accYear,String locationId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getIDCardPhotoSheet Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		String sec_Id = sectionId;
		String cls_Id = classId;
		String acc_year = accYear;
		String loc_Id = locationId;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_IDCARD_STU_PHOTOSHEET);
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, cls_Id);
			pstmObj.setString(3, sec_Id);
			pstmObj.setString(4, acc_year);
			rs = pstmObj.executeQuery();

			while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();

				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("student_fname_var"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));

				studentRegVo.setLocation(rs.getString("Location_Name"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));


				list.add(studentRegVo);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getIDCardPhotoSheet Ending");


		return list;
	}

	public List<StudentRegistrationVo> ShowStudentAddress(String studentId,
			String accYear, String locationId, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : ShowStudentAddress Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		int count = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_ADDRESS_HISTORY);
			pstmObj.setString(1,studentId);
			rs = pstmObj.executeQuery();
			while(rs.next())
			{	
				count++;
				StudentRegistrationVo obj = new StudentRegistrationVo();
				obj.setCount(count);
				obj.setAddress(rs.getString("address"));
				obj.setPresentaddress(rs.getString("presentAddress"));
				list.add(obj);

			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : ShowStudentAddress Ending");

		return list;
	}

	public String getAdmissionNo(String locationId, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAdmissionNo Starting");


		PreparedStatement pst = null;
		ResultSet rs=null;
		Connection conn = null;
		String admissionNo = null,ConstantId=null;
		int IncrementValue=0;
		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_ADMISSSION_NO);
			pst.setString(1,locationId);
			rs = pst.executeQuery();
			while(rs.next()){
				ConstantId=rs.getString("ConstantId");
				IncrementValue=rs.getInt("IncrementValue")+1;
				admissionNo=ConstantId+IncrementValue;
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAdmissionNo Ending");

		return admissionNo;
	}


	public String generateStudentTC(String[] splitlocation, String[] splitaccyear, String[] splitstudentid,String[] splitadmid,
			String[] splitclassid,String examdetails,String reason,String remarks,String result, String log_audit_session, UserLoggingsPojo custdetails, StudentRegistrationVo vo){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : generateStudentTC Starting");
		StudentRegistrationForm registration = new StudentRegistrationForm();
		PreparedStatement pstmt = null;
		Connection con = null;
		int status = 0;
		String smsg = null;
		//ln("result  ==  " + result);
		try {
			//ln("Inside the saveStudentPromotion DaoImpl");
			con = JDBCConnection.getSeparateConnection(custdetails);
			con.setAutoCommit(false);
			Timestamp createdDate = HelperClass.getCurrentTimestamp();



			for (int i = 0; i < splitstudentid.length; i++) {
				pstmt=con.prepareStatement(StudentRegistrationSQLUtilConstants.CHECKSTUID);
				pstmt.setString(1, splitstudentid[i]);
				pstmt.setString(2, splitlocation[i]);
				pstmt.setString(3, splitaccyear[i]);
				ResultSet rs =pstmt.executeQuery();
				while(rs.next()){
					int count=rs.getInt(1);

					if(count >0){
						pstmt = con.prepareStatement(StudentRegistrationSQLUtilConstants.UPDATE_TC);
						pstmt.setString(1, examdetails);
						pstmt.setString(2, result);
						pstmt.setString(3, reason);
						pstmt.setString(4, remarks);
						pstmt.setString(5, HelperClass.convertUIToDatabase(vo.getMeetingdate()));
						pstmt.setInt(6, (Integer.parseInt(vo.getNoofmeeting())));
						pstmt.setInt(7, (Integer.parseInt(vo.getMeetattain())));
						pstmt.setString(8, vo.getGencond());
						pstmt.setInt(9, (Integer.parseInt(vo.getNooffail())));
						pstmt.setTimestamp(10, createdDate);
						pstmt.setString(11, registration.getCreateUser());
						pstmt.setString(12, splitstudentid[i]);
						pstmt.setString(13, splitlocation[i]);
						pstmt.setString(14, splitaccyear[i]);
						status = pstmt.executeUpdate();	
						//ln("//////////////////////////////////////////+"+pstmt);

					}

					else{
						String idgen=IDGenerator.getPrimaryKeyID("campus_tc_details",custdetails);
						pstmt = con.prepareStatement(StudentRegistrationSQLUtilConstants.GENERATE_TC);
						pstmt.setString(1, idgen);
						pstmt.setString(2, splitlocation[i]);
						pstmt.setString(3, splitaccyear[i]);
						pstmt.setString(4, splitstudentid[i]);
						pstmt.setString(5, splitadmid[i]);
						pstmt.setString(6, splitclassid[i]);
						pstmt.setTimestamp(7, createdDate);
						pstmt.setString(8, registration.getCreateUser());
						pstmt.setString(9, examdetails);
						pstmt.setString(10, result);
						pstmt.setString(11, reason);
						pstmt.setString(12, remarks);
						pstmt.setString(13, HelperClass.convertUIToDatabase(vo.getMeetingdate()));
						pstmt.setInt(14, (Integer.parseInt(vo.getNoofmeeting())));
						pstmt.setInt(15, (Integer.parseInt(vo.getMeetattain())));
						pstmt.setString(16, vo.getGencond());
						pstmt.setInt(17, (Integer.parseInt(vo.getNooffail())));
						pstmt.setTimestamp(18, createdDate);
						pstmt.setString(19, registration.getCreateUser());
						status = pstmt.executeUpdate();	
					}

					if(status>0){
						PreparedStatement classDetaiUpdate=con.prepareStatement("UPDATE campus_student_classdetails SET student_status='TC Genrated' WHERE student_id_int=? AND fms_acadamicyear_id_int=?");
						classDetaiUpdate.setString(1, splitstudentid[i]);
						classDetaiUpdate.setString(2, splitaccyear[i]);
						int classDetaiUpdateInt=classDetaiUpdate.executeUpdate();

						if(classDetaiUpdateInt>0) {
							con.commit();
							HelperClass.recordLog_Activity(log_audit_session,"Student","TCGeneration","Insert",pstmt.toString(),custdetails);
							smsg="true";
						}

						else{
							con.rollback();
							smsg="TC Generation Failed";
						}

					}else{
						con.rollback();
						smsg="TC Generation Failed";
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{

			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : generateStudentTC Ending");
		return smsg;
	}

	public List<StudentRegistrationVo> TCGeneration(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : TCGeneration Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = academic_year;
		String loc_Id = location;
		int sno = 0;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.TC_GENERATION);
			pst.setString(1, loc_Id);
			pst.setString(2, acc_year);
			//ln("TC_GENERATION -->>"+pst);
			rs = pst.executeQuery();


			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : TCGeneration Ending");

		return list;
	}

	public List<StudentRegistrationVo> TCGeneration1(String academic_year, String location) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : TCGeneration1  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;
		String academicYear = academic_year;
		String locationName = location;

		try {


			conn = JDBCConnection.getSeparateConnection();

			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.TC_GENERATION1);
			pst.setString(1, locationName);
			pst.setString(2, academicYear);

			rs = pst.executeQuery();
			//ln(pst);
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setClassStreamId(rs.getString("fms_classstream_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement(SQLUtilConstants.GET_SPECIALIZATION_NAME);
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}
				}
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentList  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getNotGenTC(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getNotGenTC Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		try{

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement(SQLUtilConstants.TC_LIST);
			pst.setString(1,vo.getAcademicYear());		
			rs = pst.executeQuery();
			//System.out.println("STUDENT LISt "+pst);
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getNotGenTC Ending");

		return list;
	}


	public List<StudentRegistrationVo> getStudentListByTC(String locationid, String accyear, String classname,
			String sectionid,String sortingby,String orderby, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListByTC  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		if(locationid.equalsIgnoreCase("all")){
			locationid="%%";
		}
		if(classname.equalsIgnoreCase("all")){
			classname="%%";
		}
		if(sectionid.equalsIgnoreCase("all") || sectionid.equalsIgnoreCase("undefined") || sectionid.equalsIgnoreCase(null) ){
			sectionid="%%";
		}

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			if(sortingby.equalsIgnoreCase("Gender")){
				//ln("orderby"+orderby);
				if(orderby.equalsIgnoreCase("DESC")){

					pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION61);
					pst.setString(1, accyear.trim());
					pst.setString(2, locationid.trim());
					pst.setString(3, accyear.trim());
					pst.setString(4, classname.trim());
					pst.setString(5, sectionid.trim());
					rs = pst.executeQuery();
				}else{
					pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION71);
					pst.setString(1, accyear.trim());
					pst.setString(2, locationid.trim());
					pst.setString(3, accyear.trim());
					pst.setString(4, classname.trim());
					pst.setString(5, sectionid.trim());
					rs = pst.executeQuery();
				}
			}else if(sortingby.equalsIgnoreCase("Admission")){
				if(orderby.equalsIgnoreCase("DESC")){
					pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION51);
					pst.setString(1, accyear.trim());
					pst.setString(2, locationid.trim());
					pst.setString(3, accyear.trim());
					pst.setString(4, classname.trim());
					pst.setString(5, sectionid.trim());
					rs = pst.executeQuery();
				}else{
					pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION41);
					pst.setString(1, accyear.trim());
					pst.setString(2, locationid.trim());
					pst.setString(3, accyear.trim());
					pst.setString(4, classname.trim());
					pst.setString(5, sectionid.trim());
					rs = pst.executeQuery();
				}
			}else if(sortingby.equalsIgnoreCase("Alphabetical")){
				if(orderby.equalsIgnoreCase("DESC")){
					pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION31);
					pst.setString(1, accyear.trim());
					pst.setString(2, locationid.trim());
					pst.setString(3, accyear.trim());
					pst.setString(4, classname.trim());
					pst.setString(5, sectionid.trim());
					rs = pst.executeQuery();
				}else{
					pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION21);
					pst.setString(1, accyear.trim());
					pst.setString(2, locationid.trim());
					pst.setString(3, accyear.trim());
					pst.setString(4, classname.trim());
					pst.setString(5, sectionid.trim());
					rs = pst.executeQuery();
				}
			}
			//ln(pst);

			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				PreparedStatement SecondlanguageName=conn.prepareStatement(SQLUtilConstants.GET_SUBJECTNAME);
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
				while(SecondLangaugeRs.next()){
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}
				SecondLangaugeRs.close();
				SecondlanguageName.close();
				PreparedStatement thirdlanguageName=conn.prepareStatement(SQLUtilConstants.GET_SUBJECTNAME);
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
				while(thirdlanguageRs.next()){
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}	
				thirdlanguageRs.close();
				thirdlanguageName.close();
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement(SQLUtilConstants.GET_SPECIALIZATION_NAME);
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListByTC  Ending");

		return list;
	}

	public List<StudentRegistrationVo> TransferCertificateDownload(String locationId, String accyear, String studentid, String admid, String classid, UserLoggingsPojo cpojo){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl: TransferCertificateDownload : Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;


		ResultSet rs = null,rs1 = null,rs2 = null;

		Connection conn = null;
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat format2 = new SimpleDateFormat("dd-MMMMM-yyyy");

		try{

			conn = JDBCConnection.getSeparateConnection(cpojo);
			for(int i =0;i<studentid.split(",").length;i++)
			{
				pst = conn.prepareStatement(SQLUtilConstants.TC_PDF_LIST);
				pst.setString(1, admid.split(",")[i]);
				pst.setString(2, studentid.split(",")[i]);
				pst.setString(3, accyear.split(",")[i]);
				pst.setString(4, locationId);
				//ln("TC_PDF = "+pst);

				rs = pst.executeQuery();

				while(rs.next()){
					com.centris.campus.util.DateToWords num = new com.centris.campus.util.DateToWords();

					StudentRegistrationVo stuReg = new StudentRegistrationVo();

					stuReg.setAdmissionNo(rs.getString("admissionNo"));
					stuReg.setResult(rs.getString("result"));

					if(rs.getString("result").equalsIgnoreCase("pass")){
						stuReg.setResultstatus("YES");
					}else{
						stuReg.setResultstatus("No");
					}
					//ln("rs.getString(is_paid)  == " + rs.getString("is_paid"));
					if(rs.getString("is_paid")==null || rs.getString("is_paid").equalsIgnoreCase("N")){
						stuReg.setFeestat("NO, PAID UPTO:");
						stuReg.setPaidupto("---");
					}else if(rs.getString("is_paid").equalsIgnoreCase("Y")){
						stuReg.setFeestat(("YES, PAID UPTO:")+"-"+(HelperClass.convertDatabaseToUI(rs.getString("startdate"))));
						stuReg.setPaidupto(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
					}

					if(rs.getString("concessionType") != null){
						stuReg.setScholarShip("YES");
					}else {
						stuReg.setScholarShip("NO");
					}

					stuReg.setTccode(rs.getString("TCCode"));
					stuReg.setDateofjoin(HelperClass.convertDatabaseToUI(rs.getString("student_doj_var")));

					/*pst2 = conn.prepareStatement("SELECT ccls.classdetails_name_var FROM campus_student_classdetails cscd LEFT JOIN  campus_classdetail ccls  ON (cscd.classdetail_id_int  = ccls.classdetail_id_int AND cscd.locationId=ccls.locationId)  WHERE cscd.student_id_int = ? AND cscd.createdate LIKE ?");
			pst2.setString(1, studentid.split(",")[i]);
			pst2.setString(2,"%"+rs.getString("student_doj_var")+"%");
			rs2=pst2.executeQuery();
			//ln("sssssssssssssssssssss  "+pst2);
			while(rs2.next()){
				StudentRegistrationVo stuReg1 = new StudentRegistrationVo();*/
					stuReg.setClassname(rs.getString("classdetails_name_var"));
					/*}*/
					stuReg.setClassnamewords(HelperClass.classNameByWords(rs.getString("classdetails_name_var")));

					String promodt = rs.getString("createdate");
					String arr[] = promodt.split(" ", 2);
					String firstWord = arr[0];
					Date pd = format1.parse(firstWord);
					String prodt = format2.format(pd);
					stuReg.setPromotionDate(prodt);

					stuReg.setStudentFullName(rs.getString("studentname"));
					stuReg.setStudoj(rs.getString("student_doj_var"));
					stuReg.setTcno(rs.getString("TCCode"));
					stuReg.setFatherName(rs.getString("FatherName"));
					stuReg.setMotherName(rs.getString("MotherName"));
					stuReg.setNationality(rs.getString("Nationality"));
					String specialization=rs.getString("specilization");
					stuReg.setCasteCategory(rs.getString("casteCategory"));

					if(stuReg.getCasteCategory() == "SC" || stuReg.getCasteCategory() =="ST"){
						stuReg.setCasteCategoryStatus("YES");
					}else{
						stuReg.setCasteCategoryStatus(("No")+" "+"-"+" "+rs.getString("caste"));
					}

					stuReg.setCaste(rs.getString("caste"));

					Date date4 = format1.parse(rs.getString("student_birth_date"));
					String[] split=rs.getString("student_birth_date").split("-");
					int day = Integer.parseInt(split[2]);
					int month = Integer.parseInt(split[1]);
					int year = Integer.parseInt(split[0]);
					stuReg.setDateofBirthInWords("("+num.convert(day)+" "+num.getMonth(month)+" "+num.convert(year)+")");

					//ln("Today's Date is: "+rs.getString("student_birth_date").toString());

					//ln("Today's Date(in words) is: "+num.convert(day)+" "+num.getMonth(month)+" "+num.convert(year));
					String dateString4 = format2.format(date4);
					stuReg.setDateofBirth(dateString4);

					stuReg.setLatestclass(HelperClass.classNameByWords(rs.getString("class_latest")));
					stuReg.setCompulsorySub(rs.getString("compulsory_sub"));
					String electiveSubject="";
					pst1 = conn.prepareStatement(SQLUtilConstants.GET_SUBJECTNAME_BY_CLASS_AND_LOCATION);
					pst1.setString(1, classid.split(",")[i]);
					pst1.setString(2, locationId);
					pst1.setString(3, specialization);
					//ln("GET SUB NAME BUY CLASS "+pst1);
					rs1=pst1.executeQuery();
					while(rs1.next()){
						electiveSubject+=rs1.getString("subjectName")+",";

					}
					stuReg.setElectiveSub(electiveSubject);

					if(rs.getString("last_attendance") != null){
						Date date = format1.parse(rs.getString("last_attendance"));
						String dateString = format2.format(date);
						//ln("dateString = "+dateString);
						stuReg.setLastAttended(dateString);
					}else{
						stuReg.setLastAttended("---");
					}

					Date date1 = format1.parse(rs.getString("stuck_of_rolls"));
					String dateString1 = format2.format(date1);
					stuReg.setStuckOfRolls(dateString1);

					stuReg.setCertificateDateApply(rs.getString("date_applicatin_certificate"));
					Date date2 = format1.parse(stuReg.getCertificateDateApply());
					String dateString2 = format2.format(date2);
					stuReg.setCertificateDateApply(dateString2);

					Date date3 = format1.parse(rs.getString("date_issue_certificate"));
					String dateString3 = format2.format(date3);
					stuReg.setCertificateIssueDate(dateString3);
					stuReg.setExam_name(rs.getString("school_or_board_Examination")+"-"+(rs.getString("result"))+ "-"+ (rs.getString("reason")));
					stuReg.setReasonForTc(rs.getString("reason"));
					stuReg.setRemarks(rs.getString("remarks"));
					if(!rs.getString("last_date_stu_attend").equals("-")){
						stuReg.setMeetingdate(HelperClass.convertDatabaseToUI(rs.getString("last_date_stu_attend")));
					}
					else{
						stuReg.setMeetingdate(rs.getString("last_date_stu_attend"));
					}

					stuReg.setNoofmeeting(rs.getString("total_meeting"));
					stuReg.setMeetattain(rs.getString("meeting_attain"));
					stuReg.setGencond(rs.getString("genral_conduct"));
					stuReg.setNooffail(rs.getString("nooftime_stu_fail"));
					list.add(stuReg);

				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (pst2 != null && (!pst2.isClosed())) {
					pst2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : TransferCertificateDownload Ending");

		return list;

	}


	public List<StudentRegistrationVo> GenTCListFilter(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : GenTCListFilter Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		String locationid = vo.getLocationId();
		String accyear = vo.getAccyear();
		String classname = vo.getClassname();
		String sectionid = vo.getSection_id();
		String sortingby = vo.getSortBy();
		String orderby = vo.getOrderBy();

		try{

			conn = JDBCConnection.getSeparateConnection(custdetails);
			/*pst = conn.prepareStatement(SQLUtilConstants.TC_LIST);*/
			//ln("sortingby  ==  "+sortingby);
			//ln("orderby ==  "+orderby);

			if(sortingby.equalsIgnoreCase("Gender")){
				if(orderby.equalsIgnoreCase("DESC1")){

					pst = conn.prepareStatement(SQLUtilConstants.TC_LIST1);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());

					pst.setString(5, vo.getSearchTerm()+"%");
					pst.setString(6, vo.getSearchTerm()+"%");
					pst.setString(7, vo.getSearchTerm()+"%");

					rs = pst.executeQuery();
				}else{
					pst = conn.prepareStatement(SQLUtilConstants.TC_LIST2);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());

					pst.setString(5, vo.getSearchTerm()+"%");
					pst.setString(6, vo.getSearchTerm()+"%");
					pst.setString(7, vo.getSearchTerm()+"%");
				
					rs = pst.executeQuery();
				}
			}else if(sortingby.equalsIgnoreCase("Admission")){
				//ln(orderby);
				if(orderby.equalsIgnoreCase("DESC1")){
					pst = conn.prepareStatement(SQLUtilConstants.TC_LIST3);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());

					pst.setString(5, vo.getSearchTerm()+"%");
					pst.setString(6, vo.getSearchTerm()+"%");
					pst.setString(7, vo.getSearchTerm()+"%");
					
					//ln("TC_LIST3---------"+pst);
					rs = pst.executeQuery();
				}else{
					pst = conn.prepareStatement(SQLUtilConstants.TC_LIST4);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());

					pst.setString(5, vo.getSearchTerm()+"%");
					pst.setString(6, vo.getSearchTerm()+"%");
					pst.setString(7, vo.getSearchTerm()+"%");
					
					//ln("TC_LIST4---------"+pst);
					rs = pst.executeQuery();
				}
			}else if(sortingby.equalsIgnoreCase("Alphabetical")){
				if(orderby.equalsIgnoreCase("DESC1")){
					pst = conn.prepareStatement(SQLUtilConstants.TC_LIST5);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());

					pst.setString(5, vo.getSearchTerm()+"%");
					pst.setString(6, vo.getSearchTerm()+"%");
					pst.setString(7, vo.getSearchTerm()+"%");
					//pst.setString(8, vo.getStatus());
					//ln("TC_LIST5---------"+pst);
					rs = pst.executeQuery();
				}else{
					pst = conn.prepareStatement(SQLUtilConstants.TC_LIST6);
					pst.setString(1, locationid.trim());
					pst.setString(2, accyear.trim());
					pst.setString(3, classname.trim());
					pst.setString(4, sectionid.trim());

					pst.setString(5, vo.getSearchTerm()+"%");
					pst.setString(6, vo.getSearchTerm()+"%");
					/*pst.setString(7, vo.getSearchTerm()+"%");*/
					pst.setString(7, vo.getStatus());
					//ln("TC_LIST6---------"+pst);
					rs = pst.executeQuery();
				}
			}

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : GenTCListFilter Ending");

		return list;
	}


	public List<StudentRegistrationVo> GenTCListSearch(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : GenTCListSearch Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		String searchValue = vo.getSearchTerm();

		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement(SQLUtilConstants.TC_LIST7);
			pst.setString(1, searchValue+"%");
			pst.setString(2, searchValue+"%");
			pst.setString(3, searchValue+"%");

			pst.setString(4, vo.getLocationId());
			pst.setString(5, vo.getAccyear());
			pst.setString(6, vo.getClassname());
			pst.setString(7, vo.getSection_id());

			rs = pst.executeQuery();
			//ln("GenTCListSearch---------"+pst);

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : GenTCListSearch Ending");

		return list;
	}

	public List<StudentRegistrationVo> getAdmissionDetails(String tempadmissionid, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAdmissionDetails Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try{

			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_TEMP_REG_DETAILS);
			pst.setString(1,tempadmissionid);

			rs = pst.executeQuery();
			//ln("pst---------"+pst);

			while(rs.next()){
				String cuudate = HelperClass.getCurrentSqlDate().toString();
				List<String> datesize = HelperClass.getDateListBetweenDates(rs.getString("dateofBirthId"),cuudate);
				int years = (datesize.size()-1)/365;
				int months = ((datesize.size()-1)%365)/30;
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				stuReg.setStudentFirstName(rs.getString("studentfirstName")+" "+rs.getString("studentlastname"));
				stuReg.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("dateofBirthId")));
				stuReg.setGender(rs.getString("gender"));
				stuReg.setNationality(rs.getString("nationality"));
				stuReg.setReligion(rs.getString("religionname"));
				stuReg.setCaste(rs.getString("castename"));
				stuReg.setCasteCategory(rs.getString("casteCategoryname"));
				stuReg.setMothertongue(rs.getString("mothertongue"));
				stuReg.setAddress(rs.getString("permanentaddress"));
				stuReg.setPresentaddress(rs.getString("addressofcommunication"));
				stuReg.setFatherOfficeAddress(rs.getString("fatherofficialaddress"));
				stuReg.setMotherOfficeAddress(rs.getString("motherofficialaddress"));
				stuReg.setFatherMobileNo(rs.getString("fathermobileno"));
				stuReg.setMotherMobileNo(rs.getString("mothermobile"));
				stuReg.setFatherName(rs.getString("fathername"));
				stuReg.setMotherName(rs.getString("mothername"));
				stuReg.setFatheroccupation(rs.getString("fatheroccupation"));
				stuReg.setFatherDesignation(rs.getString("fatherdesignation"));
				stuReg.setMotherDesignation(rs.getString("motherdesignation"));
				stuReg.setMotheroccupation(rs.getString("motheroccupation"));
				stuReg.setImage(rs.getString("imageUrl"));
				stuReg.setSmsnumber(rs.getString("preferedphno"));
				stuReg.setMotherAnnualIncome(rs.getInt("mothermothlyincome"));
				stuReg.setFatherAnnualIncome(rs.getInt("fathermonthincome"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setAge(years+" Years "+months+" Months ");

				stuReg.setLocation(rs.getString("Location_Name"));
				stuReg.setLocationAddress(rs.getString("Location_Address"));

				com.centris.campus.util.DateToWords num = new com.centris.campus.util.DateToWords();
				String datewords = null;
				String[] split=stuReg.getDateofBirth().split("-");
				int day = Integer.parseInt(split[0]);
				int month = Integer.parseInt(split[1]);
				int year = Integer.parseInt(split[2]);
				datewords = (num.convert(day)+" "+num.getMonth(month)+" "+num.convert(year));
				stuReg.setDateofBirthInWords(datewords);
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getAdmissionDetails Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentDetailsByJsInRegistration(StudentRegistrationVo data,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetailsByJsInRegistration  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		PreparedStatement pst1 = null;
		ResultSet rs1 = null;
		int totalCount=0;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			if(data.getSearchTerm().equalsIgnoreCase("all") && data.getLocationId().equalsIgnoreCase("all")){
				pst = conn.prepareStatement("SELECT DISTINCT loc.Location_Name,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int JOIN campus_location loc ON loc.Location_Id = csc.locationId JOIN campus_classdetail ccd ON (csc.classdetail_id_int=ccd.classdetail_id_int AND csc.locationId=ccd.locationId) JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_acadamicyear ca ON csc.fms_acadamicyear_id_int=ca.acadamic_id JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE  csc.fms_acadamicyear_id_int=?  AND stu.student_status_var=? AND loc.isActive='Y' AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' ORDER BY loc.Location_Name,LENGTH(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,studentname LIMIT ?,?");
				pst.setString(1, data.getAcademicYear());
				pst.setString(2, data.getIsActive());
				pst.setInt(3, data.getStartPoint());
				pst.setInt(4, data.getShow_per_page());

				//ln("studentcCCCC1 "+pst);
				pst1=conn.prepareStatement("select COUNT(*) from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) JOIN campus_location loc ON loc.Location_Id = csc.locationId  join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where  csc.fms_acadamicyear_id_int=? AND  stu.student_status_var=? AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' AND loc.isActive='Y' ");
				pst1.setString(1, data.getAcademicYear());
				pst1.setString(2, data.getIsActive());
				//ln("total1 "+pst1);

			}
			else if(data.getSearchTerm().equalsIgnoreCase("all") && data.getClasscode().equalsIgnoreCase("all")){
				pst = conn.prepareStatement("select distinct loc.Location_Name,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int JOIN campus_location loc ON loc.Location_Id = csc.locationId join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where csc.locationId=? and csc.fms_acadamicyear_id_int=?  AND  stu.student_status_var=? AND loc.isActive='Y' AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' ORDER BY loc.Location_Name,LENGTH(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,studentname LIMIT ?,?");
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				pst.setString(3, data.getIsActive());
				pst.setInt(4, data.getStartPoint());
				pst.setInt(5, data.getShow_per_page());
				//ln("studentcCCCC2 "+pst);

				pst1=conn.prepareStatement("select COUNT(*) from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int JOIN campus_location loc ON loc.Location_Id = csc.locationId left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where  csc.locationId=? and csc.fms_acadamicyear_id_int=? AND  stu.student_status_var=? AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' AND loc.isActive='Y' ");
				pst1.setString(1, data.getLocationId());
				pst1.setString(2, data.getAcademicYear());
				pst1.setString(3, data.getIsActive());
				//ln("total2 "+pst1);
			}
			else if(data.getSearchTerm().equalsIgnoreCase("all") && data.getSection_id().equalsIgnoreCase("all")){
				pst = conn.prepareStatement("select distinct loc.Location_Name,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int JOIN campus_location loc ON loc.Location_Id = csc.locationId join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=? and stu.student_status_var=? AND loc.isActive='Y' AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' ORDER BY loc.Location_Name,LENGTH(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,studentname LIMIT ?,?");
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				pst.setString(3, data.getClasscode());
				pst.setString(4, data.getIsActive());
				pst.setInt(5, data.getStartPoint());
				pst.setInt(6, data.getShow_per_page());
				//ln("studentcCCCC3 "+pst);

				pst1=conn.prepareStatement("select COUNT(*) from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int JOIN campus_location loc ON loc.Location_Id = csc.locationId left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where  csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=?  AND stu.student_status_var=? AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' AND loc.isActive='Y' ");
				pst1.setString(1, data.getLocationId());
				pst1.setString(2, data.getAcademicYear());
				pst1.setString(3, data.getClasscode());
				pst1.setString(4, data.getIsActive());
				//ln("total3 "+pst1);
			}
			else if(data.getSearchTerm().equalsIgnoreCase("all")){
				pst = conn.prepareStatement("select distinct loc.Location_Name,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int JOIN campus_location loc ON loc.Location_Id = csc.locationId join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=? and ccs.classsection_id_int=?  and stu.student_status_var=? AND loc.isActive='Y' AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y'  ORDER BY loc.Location_Name,LENGTH(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,studentname LIMIT ?,?");
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				pst.setString(3, data.getClasscode());
				pst.setString(4, data.getSection_id());
				pst.setString(5, data.getIsActive());
				pst.setInt(6, data.getStartPoint());
				pst.setInt(7, data.getShow_per_page());
				//ln("studentcCCCC4 "+pst);

				pst1=conn.prepareStatement("select COUNT(*) from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int JOIN campus_location loc ON loc.Location_Id = csc.locationId left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where  csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=? and ccs.classsection_id_int=?  AND stu.student_status_var=? AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' AND loc.isActive='Y' ");
				pst1.setString(1, data.getLocationId());
				pst1.setString(2, data.getAcademicYear());
				pst1.setString(3, data.getClasscode());
				pst1.setString(4, data.getSection_id());
				pst1.setString(5, data.getIsActive());
				//ln("total4 "+pst1);
			}
			else{
				pst = conn.prepareStatement("select distinct loc.Location_Name,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,stu.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int JOIN campus_location loc ON loc.Location_Id = csc.locationId join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where csc.locationId like ? and csc.fms_acadamicyear_id_int like ? and csc.classdetail_id_int like ? and ccs.classsection_id_int like ?  and stu.student_status_var=?  and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or stu.student_dob_var like ? or stu.student_status_var like ? or CONCAT(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ? or ca.acadamic_year like ?)  AND loc.isActive='Y' AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' ORDER BY loc.Location_Name,LENGTH(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,studentname LIMIT ?,?");
				if(data.getLocationId().equalsIgnoreCase("all")){
					pst.setString(1, "%%");
				}
				else{
					pst.setString(1, data.getLocationId());
				}

				if(data.getAcademicYear().equalsIgnoreCase("all")){
					pst.setString(2, "%%");
				}
				else{
					pst.setString(2, data.getAcademicYear());
				}
				if(data.getClasscode().equalsIgnoreCase("all")){
					pst.setString(3, "%%");
				}
				else{
					pst.setString(3, data.getClasscode());
				}
				if(data.getSection_id().equalsIgnoreCase("all")){
					pst.setString(4, "%%");
				}
				else{
					pst.setString(4, data.getSection_id());
				}
				pst.setString(5, data.getIsActive());
				pst.setString(6, "%" + data.getSearchTerm() + "%");
				pst.setString(7, "%" + data.getSearchTerm() + "%");
				pst.setString(8, "%" + data.getSearchTerm() + "%");
				pst.setString(9, "%" + data.getSearchTerm() + "%");
				pst.setString(10, "%" + data.getSearchTerm() + "%");
				pst.setString(11, "%" + data.getSearchTerm() + "%");
				pst.setString(12, "%" + data.getSearchTerm() + "%");
				pst.setString(13, "%" + data.getSearchTerm() + "%");
				pst.setString(14, "%" + data.getSearchTerm() + "%");
				pst.setString(15, "%" + data.getSearchTerm() + "%");
				pst.setInt(16, data.getStartPoint());
				pst.setInt(17, data.getShow_per_page());
				//ln("studentcCCCC5 "+pst);


				pst1 = conn.prepareStatement("select COUNT(*) from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id join campus_location loc on loc.Location_Id=stu.locationId JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where csc.locationId like ? and csc.fms_acadamicyear_id_int like ? and csc.classdetail_id_int like ? and ccs.classsection_id_int like ? and stu.student_status_var = ? and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or stu.student_dob_var like ? or stu.student_status_var like ? or CONCAT(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ? or ca.acadamic_year like ?) AND stu.student_status_var='active' AND loc.isActive='Y' AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' ");
				if(data.getLocationId().equalsIgnoreCase("all")){
					pst1.setString(1, "%%");
				}
				else{
					pst1.setString(1, data.getLocationId());
				}

				if(data.getAcademicYear().equalsIgnoreCase("all")){
					pst1.setString(2, "%%");
				}
				else{
					pst1.setString(2, data.getAcademicYear());
				}
				if(data.getClasscode().equalsIgnoreCase("all")){
					pst1.setString(3, "%%");
				}
				else{
					pst1.setString(3, data.getClasscode());
				}
				if(data.getSection_id().equalsIgnoreCase("all")){
					pst1.setString(4, "%%");
				}
				else{
					pst1.setString(4, data.getSection_id());
				}
				pst1.setString(5, data.getIsActive());
				pst1.setString(6, "%" + data.getSearchTerm() + "%");
				pst1.setString(7, "%" + data.getSearchTerm() + "%");
				pst1.setString(8, "%" + data.getSearchTerm() + "%");
				pst1.setString(9, "%" + data.getSearchTerm() + "%");
				pst1.setString(10, "%" + data.getSearchTerm() + "%");
				pst1.setString(11, "%" + data.getSearchTerm() + "%");
				pst1.setString(12, "%" + data.getSearchTerm() + "%");
				pst1.setString(13, "%" + data.getSearchTerm() + "%");
				pst1.setString(14, "%" + data.getSearchTerm() + "%");
				pst1.setString(15, "%" + data.getSearchTerm() + "%");
				//ln("totalCount5 -->>"+pst1);
			}

			rs = pst.executeQuery();
			rs1=pst1.executeQuery();
			while(rs1.next()){
				totalCount=rs1.getInt(1);	
			}
			rs1.close();
			pst1.close();
			while (rs.next()) {
				ArrayList<FeeNameVo> feeStatusList=new ArrayList<FeeNameVo>();
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setTotalCount(totalCount);
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setImage(rs.getString("imgurl"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setClassSectionId(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setDateofBirth( HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				PreparedStatement termpstmt=conn.prepareStatement(SQLUtilConstants.GET_TERMID_TERMNAME_BY_LOCATION_ACCYEAR);
				termpstmt.setString(1, rs.getString("locationId"));
				termpstmt.setString(2, data.getAcademicYear());
				ResultSet termRs=termpstmt.executeQuery();
				while(termRs.next()){
					int getcount=0;
					String paymentStatus="Not Paid";
					FeeNameVo fStatus=new FeeNameVo();
					PreparedStatement getStatus=conn.prepareStatement(SQLUtilConstants.GET_COUNT_FROM_campus_fee_collection_BY_TERMCODE_ADMISSIONNO);
					getStatus.setString(1, termRs.getString("termid"));
					getStatus.setString(2, rs.getString("student_id_int"));
					ResultSet getstRs=getStatus.executeQuery();
					if(getstRs.next()){
						getcount=getstRs.getInt(1);
					}
					if(getcount > 0){
						paymentStatus="Paid";
					}
					fStatus.setTerm(termRs.getString("termname"));
					fStatus.setStatus(paymentStatus);
					feeStatusList.add(fStatus);
				}
				registrationVo.setFeeStatus(feeStatusList);
				list.add(registrationVo);

				termRs.close();
				termpstmt.close();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentDetailsByJsInRegistration  Ending");

		return list;
	}

	public List<StudentConcessionVo> getStudentListbyAdmissionNo(String admissionNo, String accyear,
			UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListbyAdmissionNo Starting");

		List<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		try{
			StudentConcessionVo stuReg = new StudentConcessionVo();
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement("SELECT st.student_id_int,st.student_fname_var,st.student_lname_var,cc.classdetail_id_int,cc.classdetails_name_var,cs.classsection_id_int,cs.classsection_name_var,csc.specilization FROM campus_student st JOIN campus_student_classdetails csc ON st.student_id_int=csc.student_id_int  JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) JOIN campus_classsection cs ON (csc.classsection_id_int=cs.classsection_id_int AND csc.locationId=cs.locationId) WHERE st.student_admissionno_var=? AND csc.fms_acadamicyear_id_int=?");
			pst.setString(1, admissionNo);
			pst.setString(2, accyear);
			////ln("pst---------"+pst);
			rs = pst.executeQuery();

			if(rs.next()){
				stuReg.setAdmissionNo(admissionNo);
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setStudent(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
				stuReg.setClassId(rs.getString("classdetail_id_int"));
				stuReg.setClass_section(rs.getString("classdetails_name_var")+" - "+rs.getString("classsection_name_var"));
				stuReg.setSpecialization(rs.getString("specilization"));
				stuReg.setStatus("found");
				list.add(stuReg);
			}else{
				stuReg.setStatus("notfound");
				list.add(stuReg);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListbyAdmissionNo Ending");

		return list;
	}

	public List<StudentConcessionVo> getTermdeatilsForConcession(String classId,
			String accyear, String specialization, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTermdeatilsForConcession Starting");

		List<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement("SELECT cft.termid,cft.termname,cfs.`feecode`,cfm.`FeeName`, cfs.`feeAmount` FROM `campus_fee_setupdetails` cfs JOIN `campus_fee_master` cfm ON cfs.feecode=cfm.FeeCode JOIN `campus_fee_setup` cfst ON cfs.feeSettingCode=cfst.feeSettingcode JOIN `campus_fee_termdetails` cft ON cfst.`Termcode`=cft.termid WHERE cfs.feeSettingCode IN(SELECT `feeSettingcode` FROM `campus_fee_setup` WHERE ClassCode=? AND AccyearCode=? AND `specialization`=?) AND cfm.feeType='TUF'");
			pst.setString(1, classId);
			pst.setString(2, accyear);
			pst.setString(3, specialization);
			//ln("getTermdeatilsForConcession ---------"+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				StudentConcessionVo stuReg = new StudentConcessionVo();
				stuReg.setTermcode(rs.getString("termid"));
				stuReg.setTerm(rs.getString("termname"));
				stuReg.setFeecode(rs.getString("feecode"));
				stuReg.setFeename(rs.getString("FeeName"));
				stuReg.setTermTuitionFeeAmount(rs.getString("feeAmount"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTermdeatilsForConcession Ending");

		return list;
	}

	public List<StudentRegistrationVo> deactivateReportDetail(StudentRegistrationVo studetails, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deactivateReportDetail Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		int count = 0;
		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_DEACTIVATE_REPORT_TYPE);
			pstmObj.setString(1, studetails.getLocationId());
			pstmObj.setString(2, studetails.getStudentId());
			pstmObj.setString(3, studetails.getAccyear());
			rs = pstmObj.executeQuery();
			//ln("pstmObjsadasds"+pstmObj);
			while(rs.next())
			{
				StudentRegistrationVo studentRegVo = new StudentRegistrationVo();

				count++;
				studentRegVo.setCount(count);
				studentRegVo.setConfidentialId(rs.getString("confidential_id"));
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentRegVo.setLocationId(rs.getString("locationId"));
				studentRegVo.setConfidentialEntryDate(HelperClass.convertDatabaseToUI(rs.getString("entrydate")));
				studentRegVo.setConfidentialComments(rs.getString("comments"));
				list.add(studentRegVo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : deactivateReportDetail Ending");

		return list;
	}

	public List<StudentRegistrationVo> searchAllAcademicYearDetails(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAcademicYearDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		if(vo.getLocationId().equalsIgnoreCase("all")){
			vo.setLocationId("%%");
		}
		if(vo.getClassDetailId().equalsIgnoreCase("all")){
			vo.setClassDetailId("%%");
		}
		if(vo.getSection_id().equalsIgnoreCase("all")){
			vo.setSection_id("%%");
		}

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION2);
			pst.setString(1, vo.getLocationId().trim());
			pst.setString(2, vo.getAccyear().trim());
			pst.setString(3, vo.getClassDetailId().trim());
			pst.setString(4, vo.getSection_id().trim());
			pst.setString(5, vo.getStatus());
			//ln("GET_FILTERED_STUDENTD_BY_SECTION2 -->>"+pst);
			rs = pst.executeQuery();

			//ln(pst);

			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));

				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : searchAllAcademicYearDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getHouseList(StudentRegistrationVo svo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsDAOImpl: getHouseList : Starting");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_HOUSE_LIST);
			pstmt.setString(1,svo.getAcademicYearId());
			pstmt.setString(2,svo.getLocationId());
			rs = pstmt.executeQuery();

			//ln("GET_HOUSE_SETTINGS -->>"+pstmt);

			while(rs.next()){
				StudentRegistrationVo obj= new StudentRegistrationVo();
				obj.setHouseId(rs.getString("house_id"));
				obj.setHouseName(rs.getString("housename"));
				list.add(obj);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
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
				+ " Control in StudentHouseSettingsDAOImpl : getHouseList : Ending");
		return list;
	}

	public List<StudentRegistrationVo> getStudentListByHouseWise(String locationid, String accyear, String housename,
			String classname, String sectionid,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListByHouseWise  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENTD_BY_HOUSENAME);
			pst.setString(1, locationid);
			pst.setString(2, accyear);
			pst.setString(3, housename);  
			pst.setString(4, classname);
			pst.setString(5, sectionid);

			//ln("GET_STUDENTD_BY_HOUSENAME -->>"+pst);
			rs = pst.executeQuery();

			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStatus(rs.getString("student_status"));
				registrationVo.setHouseName(rs.getString("housename"));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));

				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				PreparedStatement SecondlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
				while(SecondLangaugeRs.next()){
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}
				SecondLangaugeRs.close();
				SecondlanguageName.close();
				PreparedStatement thirdlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
				while(thirdlanguageRs.next()){
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}	
				thirdlanguageRs.close();
				thirdlanguageName.close();
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListByHouseWise  Ending");

		return list;
	}


	public String activedeleteConfidentialDetails(StudentRegistrationVo vo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : activedeleteConfidentialDetails Starting");


		PreparedStatement pst = null;
		Connection conn = null;

		int result = 0;
		String status = null;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.ACTIVE_DELETE_CONFIDENTIAL_DETAIL);
			pst.setString(1,vo.getRemarks());
			pst.setString(2,vo.getDeleteId());
			result = pst.executeUpdate();
			//ln("from deleteConfidentialDetails :"+pst);

			if (result > 0) {
				status ="true";
			} else {
				status ="false";
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : activedeleteConfidentialDetails Ending");

		return status;
	}

	public List<StudentRegistrationVo> getStudentdisciplinaryactionListDetails(StudentRegistrationVo vo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentdisciplinaryactionListDetails Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		int sno = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DISCIPLINARY_ACTION_LIST);
			pst.setString(1, vo.getLocationId());
			pst.setString(2, vo.getAccyear());
			pst.setString(3, vo.getClassDetailId());
			pst.setString(4, vo.getSection_id());
			pst.setString(5, vo.getStatus());
			//ln("GET_STUDENT_DISCIPLINARY_ACTION_LIST -->>"+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				//Siddu
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentdisciplinaryactionListDetails Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentDisciplinaryActionSearchByList(StudentRegistrationVo vo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDisciplinaryActionSearchByList Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		int sno = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DISCIPLINARY_ACTION_LIST_BY_SEARCH);

			pst.setString(1, vo.getSearchTerm()+"%");
			pst.setString(2, vo.getSearchTerm()+"%");
			pst.setString(3, vo.getSearchTerm()+"%");
			pst.setString(4, vo.getSearchTerm()+"%");
			pst.setString(5, vo.getSearchTerm()+"%");

			pst.setString(6, vo.getLocation());
			pst.setString(7, vo.getAccyear());
			pst.setString(8, vo.getClassname());
			pst.setString(9, vo.getSection_id());
			pst.setString(10, vo.getStatus());

			//ln("GET_STUDENT_DISCIPLINARY_ACTION_LIST_BY_SEARCH -->>"+pst);
			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				//Siddu
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDisciplinaryActionSearchByList Ending");

		return list;
	}


	public List<StudentRegistrationVo> newClassList(String location, String preClass, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : newClassList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null,pst1 = null;;
		ResultSet rs = null,rs1= null;
		Connection conn = null;
		int count=0;
		String[] strc=preClass.split("");
		String classId=null;
		//int[] cname=new int[14];

		ArrayList<Integer> cname =new ArrayList<Integer>();

		if(strc.length>4) {
			classId=strc[3]+""+strc[4];
		}else {
			classId=strc[3];
		}
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			//String nextYear=getNextAcademicYearId(regVo.getAcademicYearId());
			pst = conn.prepareStatement("SELECT SUBSTR(classdetail_id_int,4) classInt FROM campus_classdetail WHERE `locationId`=?");
			pst.setString(1,location);
			//ln("pst"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				if(rs.getInt(1)>Integer.parseInt(classId)) {
					cname.add(rs.getInt(1));
					count++;
				}
			}
			Collections.sort(cname);
			for(int i=0; i<cname.size(); i++){
				pst1 =conn.prepareStatement("SELECT`classdetails_name_var` FROM `campus_classdetail` WHERE `classdetail_id_int`=? AND locationId=?");
				pst1.setString(1, "CCD"+cname.get(i));
				pst1.setString(2, location);
				//ln("pst1"+pst1);
				rs1=pst1.executeQuery();
				while(rs1.next()){
					StudentRegistrationVo vo=new StudentRegistrationVo();
					vo.setClasscode("CCD"+cname.get(i));
					vo.setClassname(rs1.getString("classdetails_name_var"));
					list.add(vo);
				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
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
				+ " Control in StudentRegistrationDaoImpl : newClassList  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,
			String sectionid,UserLoggingsPojo custdetails,String searchvalue) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		if(locationid.equalsIgnoreCase("all")){
			locationid="%%";
		}
		if(classname.equalsIgnoreCase("all")){
			classname="%%";
		}
		if(sectionid.equalsIgnoreCase("all")){
			sectionid="%%";
		}
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			String query="SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-'  WHEN csc.student_house ='' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END house,ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,CASE WHEN csc.secondlanguage IS NULL THEN '-'   WHEN csc.secondlanguage ='' THEN '-' ELSE csc.secondlanguage END secondlanguage,CASE WHEN csc.thirdlanguage IS NULL THEN '-'   WHEN csc.thirdlanguage ='' THEN '-' ELSE csc.thirdlanguage END thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year , CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int LEFT JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int JOIN `campus_classstream` ccst ON ccst.`classstream_id_int`=ccd.`classstream_id_int` LEFT JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id LEFT JOIN campus_house_settings hs ON (hs.loc_id=csc.locationId AND hs.house_id=csc.student_house) JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccst.isActive='Y' AND ccd.isActive='Y' AND stu.student_status_var='active' AND ccs.`isActive`='Y'";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!locationid.equalsIgnoreCase("%%")) {
				map.put("csc.locationId",locationid);
				vec.add("csc.locationId");
			}
			if(!accyear.equalsIgnoreCase("%%") ) {
				map.put("csc.fms_acadamicyear_id_int",accyear);
				vec.add("csc.fms_acadamicyear_id_int");
			}
			if(!classname.equalsIgnoreCase("%%")) {
				map.put("csc.classdetail_id_int",classname);
				vec.add("csc.classdetail_id_int");
			}
			if(!sectionid.equalsIgnoreCase("%%")) {
				map.put("csc.classsection_id_int",sectionid);
				vec.add("csc.classsection_id_int");
			}

			Enumeration<String> e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql=" AND "+key+" = '"+val+"'";
					count++;
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
				}
			}

			String finalquery="";
			if(wheresql != null) {
				finalquery=query+" "+wheresql+" and "+"( stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or concat(stu.student_fname_var,' ',stu.student_lname_var) like ? OR  csc.student_rollno  LIKE ? or ccd.classdetails_name_var like ? or ccs.classsection_name_var like ?  or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ?) ORDER BY CAST(SUBSTRING(csc.`classdetail_id_int`,4,LENGTH(csc.`classdetail_id_int`)-3) AS SIGNED),ccs.classsection_name_var,studentname,stu.student_admissionno_var*1"; 
			}else {
				finalquery=query+" "+"( stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or concat(stu.student_fname_var,' ',stu.student_lname_var) like ? OR  csc.student_rollno  LIKE ? or ccd.classdetails_name_var like ? or ccs.classsection_name_var like ?  or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ?) ORDER BY CAST(SUBSTRING(csc.`classdetail_id_int`,4,LENGTH(csc.`classdetail_id_int`)-3) AS SIGNED),ccs.classsection_name_var,studentname,stu.student_admissionno_var*1";
			}
			pst = conn.prepareStatement(finalquery);
			pst.setString(1,  searchvalue + "%");
			pst.setString(2,  searchvalue + "%");
			pst.setString(3,  searchvalue + "%");
			pst.setString(4,  searchvalue + "%");
			pst.setString(5,  searchvalue + "%");
			pst.setString(6,  searchvalue + "%");
			pst.setString(7,  searchvalue + "%");
			pst.setString(8,  searchvalue + "%");
			//System.out.println("GET_FILTERED_STUDENTD_BY_SECTION2 22-->>"+pst);
			rs = pst.executeQuery();
			////ln("STUDENT "+pst);
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				PreparedStatement SecondlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
				while(SecondLangaugeRs.next()){
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}
				SecondLangaugeRs.close();
				SecondlanguageName.close();
				PreparedStatement thirdlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
				while(thirdlanguageRs.next()){
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}	
				thirdlanguageRs.close();
				thirdlanguageName.close();
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}

				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getSectionForSMS(String searchTerm, StudentRegistrationVo vo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getSectionForSMS  Starting");
		String classval=searchTerm.split(",")[0];
		String location=searchTerm.split(",")[1];

		if(classval.equalsIgnoreCase("all")){
			classval="%%";
		}

		if(location.equalsIgnoreCase("all")){
			location="%%";
		}

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_SECTION_FOR_ALLCLASS);
			pst.setString(1, classval.trim());
			pst.setString(2, location);
			//ln("STUDENT_SECTION_FOR_ALLCLASS -->>"+pst);

			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setClasscode(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setSectionnaem(rs.getString("classdetails_name_var")+" - "+rs.getString("classsection_name_var"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);


			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getSectionForSMS  Ending");

		return list;
	}

	public StudentRegistrationVo singleStudentDetails(String studentId, String accYear, String locationId, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentDetails Starting");

		StudentRegistrationVo studentRegVo = new StudentRegistrationVo();

		PreparedStatement pstmObj = null;
		PreparedStatement pstmObj2 = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		String stu_Id = studentId;
		String acc_year = accYear;
		String loc_Id = locationId;
		int count = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmObj = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_STUDENT_DETAILS);
			pstmObj.setString(1, loc_Id);
			pstmObj.setString(2, stu_Id);
			pstmObj.setString(3, acc_year);
			//ln("singleStudentDetails GET_SINGLE_STUDENT_DETAILS  -->>"+pstmObj);
			rs = pstmObj.executeQuery();

			while(rs.next())
			{


				count++;
				studentRegVo.setCount(count);
				studentRegVo.setStudentId(rs.getString("student_id_int"));
				studentRegVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				studentRegVo.setLocationId(rs.getString("Location_Id"));
				studentRegVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegVo.setStudentFullName(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
				studentRegVo.setStudentPhoto(rs.getString("student_imgurl_var"));
				studentRegVo.setAcademicYear(rs.getString("acadamic_year"));
				studentRegVo.setStudentrollno(rs.getString("student_rollno"));
				studentRegVo.setClassname(rs.getString("classdetails_name_var"));
				studentRegVo.setSectionnaem(rs.getString("classsection_name_var"));
				studentRegVo.setStudentStatus(rs.getString("student_status"));
				studentRegVo.setBranchName(rs.getString("Location_Name"));
				studentRegVo.setParentId(rs.getString("parentid"));
				studentRegVo.setFatherName(rs.getString("FatherName"));
				studentRegVo.setFatherMobileNo(rs.getString("mobileno"));
				studentRegVo.setMotherName(rs.getString("student_mothername_var"));
				studentRegVo.setMotherMobileNo(rs.getString("student_mothermobileno_var"));
				studentRegVo.setGaurdianName(rs.getString("student_gaurdianname_var"));
				studentRegVo.setGuardianMobileNo(rs.getString("student_gardian_mobileno"));
				studentRegVo.setHouseName(rs.getString("housename"));

				/*	pstmObj2 = conn.prepareStatement("SELECT csh.house_id,CASE WHEN chs.housename IS NULL THEN '-' WHEN chs.STATUS ='deleted' THEN '-' WHEN chs.housename ='' THEN '-' ELSE chs.housename END housename FROM campus_student_house csh JOIN campus_house_settings chs ON chs.house_id=csh.house_id and  WHERE csh.loc_id LIKE ? AND csh.student_id=? AND csh.academic_year=?");
			pstmObj2.setString(1, loc_Id);
			pstmObj2.setString(2, stu_Id);
			pstmObj2.setString(3, acc_year);

			rst = pstmObj2.executeQuery();

			while(rst.next())
			{
				studentRegVo.setHouseId(rst.getString("house_id"));
				studentRegVo.setHouseName(rst.getString("housename"));

			}*/

			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (pstmObj2 != null && (!pstmObj2.isClosed())) {
					pstmObj2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : singleStudentDetails Ending");
		return studentRegVo;
	}

	public List<StudentRegistrationVo> getTempRegistrationList(StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTempRegistrationList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement("SELECT * FROM campus_temporary_admisssion_details WHERE temporary_admission_id=? and status='approved'");
			pst.setString(1, registrationVo.getTempregid().trim());
			rs = pst.executeQuery();
			while (rs.next()) {

				registrationVo.setLocationId(rs.getString("LocId"));
				registrationVo.setTempregid(rs.getString("temporary_admission_id"));
				registrationVo.setStudentFirstName(rs.getString("studentfirstName"));
				registrationVo.setStudentLastName(rs.getString("studentlastname"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("dateofBirthId").trim()));
				registrationVo.setGender(rs.getString("gender"));
				registrationVo.setNationality(rs.getString("nationality"));
				registrationVo.setReligion(rs.getString("religion"));
				registrationVo.setCaste(rs.getString("caste"));
				registrationVo.setMothertongue(rs.getString("mothertongue"));
				registrationVo.setAadharno(rs.getString("aadharNo"));
				registrationVo.setAddress(rs.getString("permanentaddress"));
				registrationVo.setPresentaddress(rs.getString("addressofcommunication"));
				registrationVo.setClassname(rs.getString("classname"));

				registrationVo.setSibilingClassId(rs.getString("siblingid"));
				registrationVo.setSibilingName(rs.getString("siblingname"));

				registrationVo.setFatherName(rs.getString("fathername"));
				registrationVo.setFatherMobileNo(rs.getString("fathermobileno"));
				registrationVo.setFatherQualification(rs.getString("fatherqualification"));
				registrationVo.setFatheroccupation(rs.getString("fatheroccupation"));
				registrationVo.setFatherDepartment(rs.getString("fatherdepartment"));
				registrationVo.setFatherDesignation(rs.getString("fatherdesignation"));
				registrationVo.setFatherOfficeAddress(rs.getString("fatherofficialaddress"));
				registrationVo.setFatherAnnualIncome(rs.getDouble("fathermonthincome"));
				registrationVo.setFatheremailId(rs.getString("fatheremailid"));

				registrationVo.setMotherName(rs.getString("mothername"));
				registrationVo.setMotherMobileNo(rs.getString("mothermobile"));
				registrationVo.setMotherQualification(rs.getString("motherqualification"));
				registrationVo.setMotheroccupation(rs.getString("motheroccupation"));
				registrationVo.setMotherDepartment(rs.getString("motherdepartment"));
				registrationVo.setMotherDesignation(rs.getString("motherdesignation"));
				registrationVo.setMotherOfficeAddress(rs.getString("motherofficialaddress"));
				registrationVo.setMotherAnnualIncome(rs.getDouble("mothermothlyincome"));
				registrationVo.setMotheremailId(rs.getString("motheremailid"));
				registrationVo.setBirthcertificate(rs.getString("BirthCertificateFile"));
				DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date d = f.parse(rs.getString("createdTime"));
				DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
				registrationVo.setCreateDate(date.format(d));
				registrationVo.setImageFileName(rs.getString("imageUrl"));
				registrationVo.setEnquiryId(rs.getString("enquiryid"));
				registrationVo.setStreemcode(rs.getString("stream"));
				registrationVo.setCasteCategory(rs.getString("castecategory"));
				registrationVo.setSpecilization(rs.getString("group_prefered"));
				registrationVo.setPrimaryPerson(rs.getString("relationship"));
				registrationVo.setSmsnumber(rs.getString("preferedphno"));

				registrationVo.setMothertongue(rs.getString("mothertongue"));

				list.add(registrationVo);
			}


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getTempRegistrationList  Ending");

		return list;
	}

	public List<secadmissionformformatVO> searchStudentByApprove(String studentName, String parentName, String mobileNo,
			UserLoggingsPojo pojo, String locid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByApprove  Starting");
		List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{

			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement("SELECT `enquiry_id`,class_id,accyearId,loc_id,`student_first_name`,`student_last_name`,`email`,`mobile_number`,`address`,`Location_Name`,`acadamic_year`,`stu_parrelationship`,`classdetails_name_var`,`parents_name`,stream_id FROM `campus_parent_enquiry_details` JOIN `campus_acadamicyear` ON `acadamic_id` = `accyearId` JOIN `campus_location` ON  `Location_Id` = `loc_id` JOIN `campus_classdetail` cd ON (cd.`classdetail_id_int` = `class_id` AND cd.`locationId` = `loc_id`) WHERE `status` = 'approved' and loc_id = ?");
			pstmt.setString(1,locid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				secadmissionformformatVO templist = new secadmissionformformatVO();
				templist.setTemp_regid(rs.getString("enquiry_id"));
				templist.setStu_firstname(rs.getString("student_first_name"));
				templist.setStudentLastName(rs.getString("student_last_name"));
				templist.setStudentname(rs.getString("student_first_name")+" "+rs.getString("student_last_name"));
				templist.setPreferedphno(rs.getString("mobile_number"));
				templist.setEmailId(rs.getString("email"));
				templist.setAddress(rs.getString("address"));
				templist.setSchoolLocation(rs.getString("Location_Name"));
				templist.setAccyear(rs.getString("acadamic_year"));
				templist.setStreamId(rs.getString("stream_id"));
				templist.setClassId(rs.getString("class_id"));
				templist.setLocId(rs.getString("loc_id"));
				templist.setAcyId(rs.getString("accyearId"));
				templist.setClassname(rs.getString("classdetails_name_var"));
				templist.setParents(rs.getString("parents_name"));
				templist.setRelationship(rs.getString("stu_parrelationship"));
				templist.setStudentfirstName(rs.getString("student_first_name"));
				templist.setStudentLastName(rs.getString("student_last_name"));
				list.add(templist);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByApprove  Ending");

		return list;
	}

	public List<StudentRegistrationVo> searchStudentByApprove(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByApprove  Starting");

		String searchTerm = registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{

			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement("SELECT `enquiry_id`,class_id,accyearId,loc_id,`student_first_name`,`student_last_name`,CONCAT(`student_first_name`,' ',`student_last_name`)AS studentName,`email`,`mobile_number`,`address`,`Location_Name`,`acadamic_year`,`stu_parrelationship`,`classdetails_name_var`,`parents_name`,stream_id FROM `campus_parent_enquiry_details` JOIN `campus_acadamicyear` ON `acadamic_id` = `accyearId` JOIN `campus_location` ON  `Location_Id` = `loc_id` JOIN `campus_classdetail` cd ON (cd.`classdetail_id_int` = `class_id` AND cd.`locationId` = `loc_id`) WHERE `status` = 'approved' and (student_first_name like ? or student_last_name like ?) GROUP BY studentName");
			pstmt.setString(1, searchTerm);
			pstmt.setString(2, searchTerm);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setStudentnamelabel(rs.getString("student_first_name")+" "+rs.getString("student_last_name"));
				studentRegistrationVo.setStudentidlabel(rs.getString("enquiry_id"));
				registrationList.add(studentRegistrationVo);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByApprove  Ending");

		return registrationList;
	}

	public List<StudentRegistrationVo> searchStudentByPar(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByPar Starting");

		String searchTerm = registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(pojo);

			pstmObj = conn.prepareStatement("SELECT `enquiry_id`,class_id,accyearId,loc_id,`student_first_name`,`student_last_name`,`email`,`mobile_number`,`address`,`Location_Name`,`acadamic_year`,`stu_parrelationship`,`classdetails_name_var`,`parents_name`,stream_id FROM `campus_parent_enquiry_details` JOIN `campus_acadamicyear` ON `acadamic_id` = `accyearId` JOIN `campus_location` ON `Location_Id` = `loc_id` JOIN `campus_classdetail` cd ON (cd.`classdetail_id_int` = `class_id` AND cd.`locationId` = `loc_id`) WHERE `status` = 'approved' and parents_name like ? GROUP BY parents_name");
			pstmObj.setString(1, searchTerm);

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setParentNameLabel(rs.getString("parents_name"));
				studentRegistrationVo.setStudentidlabel(rs.getString("enquiry_id"));

				registrationList.add(studentRegistrationVo);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByPar Ending");

		return registrationList;

	}

	public List<StudentRegistrationVo> searchStudentByAprMobile(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByAprMobile Starting");

		String searchTerm = registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmObj = conn.prepareStatement("SELECT `enquiry_id`,class_id,accyearId,loc_id,`student_first_name`,`student_last_name`,`email`,`mobile_number`,`address`,`Location_Name`,`acadamic_year`,`stu_parrelationship`,`classdetails_name_var`,`parents_name`,stream_id FROM `campus_parent_enquiry_details` JOIN `campus_acadamicyear` ON `acadamic_id` = `accyearId` JOIN `campus_location` ON  `Location_Id` = `loc_id` JOIN `campus_classdetail` cd ON (cd.`classdetail_id_int` = `class_id` AND cd.`locationId` = `loc_id`) WHERE `status` = 'approved' AND mobile_number LIKE ? GROUP BY mobile_number");
			pstmObj.setString(1, searchTerm);
			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();

				studentRegistrationVo.setParentMobileLabel(rs.getString("mobile_number"));
				studentRegistrationVo.setStudentidlabel(rs.getString("enquiry_id"));

				registrationList.add(studentRegistrationVo);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByAprMobile Ending");

		return registrationList;

	}

	public List<secadmissionformformatVO> getTempAdmissionDetailsListById(secadmissionformformatVO registrationVo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTempAdmissionDetailsListById  Starting");
		List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try{

			conn = JDBCConnection.getSeparateConnection(pojo);
			
			String query = "SELECT `enquiry_id`,class_id,accyearId,loc_id,`student_first_name`,`student_last_name`,`email`,`mobile_number`,`address`,`Location_Name`,`acadamic_year`,`stu_parrelationship`,`classdetails_name_var`,`parents_name`,stream_id FROM `campus_parent_enquiry_details` JOIN `campus_acadamicyear` ON `acadamic_id` = `accyearId` JOIN `campus_location` ON  `Location_Id` = `loc_id` JOIN `campus_classdetail` cd ON (cd.`classdetail_id_int` = `class_id` AND cd.`locationId` = `loc_id`) WHERE ";
			
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;
			
			if(!registrationVo.getLocId().equalsIgnoreCase("")) {
				map.put("loc_id",registrationVo.getLocId());
				vec.add("loc_id");
			}
			
			if(!registrationVo.getMobile_number().equalsIgnoreCase("")) {
				map.put("mobile_number",registrationVo.getMobile_number());
				vec.add("mobile_number");
			}
			
			if(!registrationVo.getParentId().equalsIgnoreCase("")) {
				map.put("parents_name",registrationVo.getParentId());
				vec.add("parents_name");
			}
			
			Enumeration<String> e = vec.elements();

			while (e.hasMoreElements()) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql= key+" = '"+val+"'";
					count++;
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
				}
			}
			String finalquery = null;
			
			if(wheresql==null){
				finalquery = query+" status = 'approved' and (student_first_name like ? or student_last_name like ?)";
			}
			else{
				finalquery = query+" "+wheresql +" and status = 'approved' and (student_first_name like ? or student_last_name like ?)";
			}
			
			
			pstmt = conn.prepareStatement(finalquery);
			pstmt.setString(1, registrationVo.getStudentname().split(" ")[0].trim()+"%");
			pstmt.setString(2, registrationVo.getStudentname().trim()+"%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				secadmissionformformatVO templist = new secadmissionformformatVO();
				templist.setTemp_regid(rs.getString("enquiry_id"));
				templist.setStudentname(rs.getString("student_first_name")+" "+rs.getString("student_last_name"));
				templist.setPreferedphno(rs.getString("mobile_number"));
				templist.setEmailId(rs.getString("email"));
				templist.setAddress(rs.getString("address"));
				templist.setSchoolLocation(rs.getString("Location_Name"));
				templist.setAccyear(rs.getString("acadamic_year"));
				templist.setStreamId(rs.getString("stream_id"));
				templist.setClassId(rs.getString("class_id"));
				templist.setLocId(rs.getString("loc_id"));
				templist.setAcyId(rs.getString("accyearId"));
				templist.setClassname(rs.getString("classdetails_name_var"));
				templist.setParents(rs.getString("parents_name"));
				templist.setRelationship(rs.getString("stu_parrelationship"));
				templist.setStudentfirstName(rs.getString("student_first_name"));
				templist.setStudentLastName(rs.getString("student_last_name"));
				list.add(templist);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTempAdmissionDetailsListById  Ending");

		return list;
	}

	public int savePhotoUploadLink(StudentRegistrationForm studentRegistrationForm, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl: studentRegistrationForm : Starting");

		PreparedStatement pstmt = null;
		int count=0,count1=0;
		ResultSet rs=null;
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			for(int i=0;i<studentRegistrationForm.getPhotoUpload().size();i++){
				pstmt = conn.prepareStatement("SELECT student_id_int,`student_imgurl_var` FROM `campus_student` WHERE student_admissionno_var=?");
				pstmt.setString(1, studentRegistrationForm.getPhotoAdmissionNoArray().get(i));

				rs=pstmt.executeQuery();
				while(rs.next()){
					PreparedStatement update=conn.prepareStatement("UPDATE campus_student_classdetails SET student_imgurl_var=? WHERE student_id_int=?");
					update.setString(1, studentRegistrationForm.getPhotoRealPath().get(i));
					update.setString(2, rs.getString("student_id_int"));

					count=update.executeUpdate();
					if(count>0){
						PreparedStatement update1=conn.prepareStatement("update campus_student SET student_imgurl_var=? WHERE student_id_int=?");
						update1.setString(1, studentRegistrationForm.getPhotoRealPath().get(i));
						update1.setString(2, rs.getString("student_id_int"));
						count=update1.executeUpdate();
						if(count>0){
							
							HelperClass.recordLog_Activity(studentRegistrationForm.getLog_audit_session(),"Student","Upload Photo","Update",update.toString(),pojo);
						}
						count1++;
						HelperClass.recordLog_Activity(studentRegistrationForm.getLog_audit_session(),"Student","Upload Photo","Update",update.toString(),pojo);
					}
					update.close();
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
				+ " Control in StudentRegistrationDaoImpl : studentRegistrationForm : Ending");

		return count1;
	}

	public String validatestudentcount(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByAprMobile Starting");

		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		int value=0,count=0,totalstucount=0;
		String status="";
		try {
			count=Integer.parseInt(registrationVo.getStudentId().split("-")[0]);
			totalstucount=Integer.parseInt(registrationVo.getStudentId().split("-")[1]);

			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmObj = conn.prepareStatement("SELECT COUNT(`student_id_int`) FROM `campus_student` WHERE `student_status_var`='active'");
			//ln("validatestudentcount -->>"+pstmObj);
			rs = pstmObj.executeQuery();

			while (rs.next()) {
				value=rs.getInt(1);
			}

			if((totalstucount-value)<count){
				status="true-"+(totalstucount-value);
			}
			else{
				status="false-"+(totalstucount-value);
			}

			//ln("totalstucount -->>"+ totalstucount);
			//ln("value -->>"+ value);
			//ln("(totalstucount-count) -->>"+(totalstucount-value));
			//ln("status -->>"+status);

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchStudentByAprMobile Ending");

		return status;
	}

	/*public List<StudentRegistrationVo> getStudentRegistrationList(StudentRegistrationVo data, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentRegistrationList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		PreparedStatement pst1 = null;
		ResultSet rs1 = null;
		int totalCount=0;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateConnection(pojo);

			pst = conn.prepareStatement("select distinct stu.student_doj_var,loc.Location_Name,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,case when csc.student_house is null then '-' when csc.student_house='' then '-' else csc.student_house end student_house,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int JOIN campus_location loc ON loc.Location_Id = csc.locationId join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where stu.locationId like ? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int like ? and ccs.classsection_id_int like ?  and stu.student_status_var=?  and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or stu.student_dob_var like ? or stu.student_status_var like ? or CONCAT(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ? or ca.acadamic_year like ?)  AND loc.isActive='Y' AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' and stu.`fms_acadamicyear_id_int` LIKE ? ORDER BY loc.Location_Name,LENGTH(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,studentname LIMIT ?,?");

			if(data.getLocationId().equalsIgnoreCase("all")){
				pst.setString(1, "%%");
			}
			else{
				pst.setString(1, data.getLocationId());
			}
			pst.setString(2, data.getAcademicYear());

			if(data.getClasscode().equalsIgnoreCase("all")){
				pst.setString(3, "%%");
			}
			else{
				pst.setString(3, data.getClasscode());
			}
			if(data.getSection_id().equalsIgnoreCase("all")){
				pst.setString(4, "%%");
			}
			else{
				pst.setString(4, data.getSection_id());
			}
			pst.setString(5, data.getIsActive());


			pst.setString(6, "%" + data.getSearchTerm() + "%");
			pst.setString(7, "%" + data.getSearchTerm() + "%");
			pst.setString(8, "%" + data.getSearchTerm() + "%");
			pst.setString(9, "%" + data.getSearchTerm() + "%");
			pst.setString(10, "%" + data.getSearchTerm() + "%");
			pst.setString(11, "%" + data.getSearchTerm() + "%");
			pst.setString(12, "%" + data.getSearchTerm() + "%");
			pst.setString(13, "%" + data.getSearchTerm() + "%");
			pst.setString(14, "%" + data.getSearchTerm() + "%");
			pst.setString(15, "%" + data.getSearchTerm() + "%");
			pst.setString(16, data.getAcademicYear());
			pst.setInt(17, data.getStartPoint());
			pst.setInt(18, data.getShow_per_page());


			pst1 = conn.prepareStatement("select COUNT(*) from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int AND stu.fms_acadamicyear_id_int=csc.fms_acadamicyear_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id join campus_location loc on loc.Location_Id=stu.locationId JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where stu.locationId like ? and stu.fms_acadamicyear_id_int=? and csc.classdetail_id_int like ? and ccs.classsection_id_int like ? and stu.student_status_var = ? and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or stu.student_dob_var like ? or stu.student_status_var like ? or CONCAT(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ? or ca.acadamic_year like ?) AND loc.isActive='Y' AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' ");
			if(data.getLocationId().equalsIgnoreCase("all")){
				pst1.setString(1, "%%");
			}
			else{
				pst1.setString(1, data.getLocationId());
			}
			if(data.getAcademicYear().equalsIgnoreCase("all")){
				pst1.setString(2, "%%");
			}
			else{
				pst1.setString(2, data.getAcademicYear());
			}
			if(data.getClasscode().equalsIgnoreCase("all")){
				pst1.setString(3, "%%");
			}
			else{
				pst1.setString(3, data.getClasscode());
			}
			if(data.getSection_id().equalsIgnoreCase("all")){
				pst1.setString(4, "%%");
			}
			else{
				pst1.setString(4, data.getSection_id());
			}
			pst1.setString(5, data.getIsActive());
			pst1.setString(6, "%" + data.getSearchTerm() + "%");
			pst1.setString(7, "%" + data.getSearchTerm() + "%");
			pst1.setString(8, "%" + data.getSearchTerm() + "%");
			pst1.setString(9, "%" + data.getSearchTerm() + "%");
			pst1.setString(10, "%" + data.getSearchTerm() + "%");
			pst1.setString(11, "%" + data.getSearchTerm() + "%");
			pst1.setString(12, "%" + data.getSearchTerm() + "%");
			pst1.setString(13, "%" + data.getSearchTerm() + "%");
			pst1.setString(14, "%" + data.getSearchTerm() + "%");
			pst1.setString(15, "%" + data.getSearchTerm() + "%");
			//ln("totalCount in Studentcount -->>"+pst1);

			rs = pst.executeQuery();
			rs1=pst1.executeQuery();
			while(rs1.next()){
				totalCount=rs1.getInt(1);	
			}
			rs1.close();
			pst1.close();
			while (rs.next()) {
				ArrayList<FeeNameVo> feeStatusList=new ArrayList<FeeNameVo>();
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setTotalCount(totalCount);
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setImage(rs.getString("imgurl"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setClassSectionId(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setDateofBirth( HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setDateofjoin(HelperClass.convertDatabaseToUI(rs.getString("student_doj_var")));
				registrationVo.setHouseName(rs.getString("student_house"));

				PreparedStatement termpstmt=conn.prepareStatement(SQLUtilConstants.GET_TERMID_TERMNAME_BY_LOCATION_ACCYEAR);
				termpstmt.setString(1, rs.getString("locationId"));
				termpstmt.setString(2, data.getAcademicYear());
				ResultSet termRs=termpstmt.executeQuery();
				while(termRs.next()){
					int getcount=0;
					String paymentStatus="Not Paid";
					FeeNameVo fStatus=new FeeNameVo();
					PreparedStatement getStatus=conn.prepareStatement(SQLUtilConstants.GET_COUNT_FROM_campus_fee_collection_BY_TERMCODE_ADMISSIONNO);
					getStatus.setString(1, termRs.getString("termid"));
					getStatus.setString(2, rs.getString("student_id_int"));
					ResultSet getstRs=getStatus.executeQuery();
					if(getstRs.next()){
						getcount=getstRs.getInt(1);
					}
					if(getcount > 0){
						paymentStatus="Paid";
					}
					fStatus.setTerm(termRs.getString("termname"));
					fStatus.setStatus(paymentStatus);
					feeStatusList.add(fStatus);
				}
				registrationVo.setFeeStatus(feeStatusList);
				list.add(registrationVo);

				termRs.close();
				termpstmt.close();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentRegistrationList  Ending");

		return list;
	}*/

	public List<StudentRegistrationVo> getTransportConcessionStudentList(String locationid, String accyear, String classid,
			String sectionid, String searchname, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			String query=StudentRegistrationSQLUtilConstants.GET_TRANSPORT_CONCESSION_SEARCH;

			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!locationid.equalsIgnoreCase("%%")) {
				map.put("csc.locationId",locationid);
				vec.add("csc.locationId");
			}
			if(!accyear.equalsIgnoreCase("%%") ) {
				map.put("csc.fms_acadamicyear_id_int",accyear);
				vec.add("csc.fms_acadamicyear_id_int");
			}
			if(!classid.equalsIgnoreCase("%%")) {
				map.put("csc.classdetail_id_int",classid);
				vec.add("csc.classdetail_id_int");
			}
			if(!sectionid.equalsIgnoreCase("%%")) {
				map.put("csc.classsection_id_int",sectionid);
				vec.add("csc.classsection_id_int");
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

			String finalquery="";
			if(wheresql != null) {
				finalquery=query+" "+wheresql+" and "+"( stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or concat(stu.student_fname_var,' ',stu.student_lname_var) like ? OR  csc.student_rollno  LIKE ? or ccd.classdetails_name_var like ? or ccs.classsection_name_var like ?  or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ?) GROUP BY ctc.`studentIdNo` order by ccd.classdetails_name_var,ccs.classsection_name_var,studentname"; /*stu.`student_id_no` like ? or*/
			}else {
				finalquery=query+" "+"( stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or concat(stu.student_fname_var,' ',stu.student_lname_var) like ? OR  csc.student_rollno  LIKE ? or ccd.classdetails_name_var like ? or ccs.classsection_name_var like ?  or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ?) GROUP BY ctc.`studentIdNo` order by ccd.classdetails_name_var,ccs.classsection_name_var,studentname"; /*stu.`student_id_no` like ? or*/
			}
			pst = conn.prepareStatement(finalquery);
			pst.setString(1,  searchname + "%");
			pst.setString(2,  searchname + "%");
			pst.setString(3,  searchname + "%");
			pst.setString(4,  searchname + "%");
			pst.setString(5,  searchname + "%");
			pst.setString(6,  searchname + "%");
			pst.setString(7,  searchname + "%");
			pst.setString(8,  searchname + "%");
			//ln("finalquery is "+pst);
			rs=pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				/*registrationVo.setStudentIdNo(rs.getString("student_id_no"));*/
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(rs.getString("classdetails_name_var") + "-" + rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setImageFileName(rs.getString("student_imgurl_var"));
				registrationVo.setConcessionType(rs.getString("concessionType"));

				if (rs.getString("specilization").equalsIgnoreCase("-")) {
					registrationVo.setSpecilizationname("-");
				} else {
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));
					rst = pstmt.executeQuery();
					while (rst.next()) {
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}
				}
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}

	public List<StudentConcessionVo> getTransportTermDetails(String accyear, String locid, String stageId, UserLoggingsPojo pojo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportTermDetails Starting");

		List<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			int stageamount=getStageAmount(accyear,locid,stageId,pojo);
			pst = conn.prepareStatement("SELECT termid,termname,`noofmonths` FROM `campus_fee_transport_termdetails` WHERE accyear=? AND locationId = ?");
			pst.setString(1, accyear);
			pst.setString(2, locid);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentConcessionVo stuReg = new StudentConcessionVo();
				stuReg.setTermcode(rs.getString("termid"));
				stuReg.setTerm(rs.getString("termname"));
				stuReg.setNoofmonths(rs.getInt("noofmonths"));
				stuReg.setStageAmount(stageamount);
				stuReg.setTermAmount(rs.getInt("noofmonths")*stageamount);
				list.add(stuReg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportTermDetails Ending");

		return list;
	}

	public int getStageAmount(String accyear, String locid, String stageId,UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStageAmount Starting");
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int stageamount = 0;

		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement("SELECT `stageamount` FROM `campus_fee_stage_amount` WHERE `stageId`=? AND `accyearId`=? AND `locationId`=?");
			pst.setString(1, stageId);
			pst.setString(2, accyear);
			pst.setString(3, locid);

			rs = pst.executeQuery();
			while (rs.next()) {
				stageamount=rs.getInt("stageamount");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStageAmount Ending");

		return stageamount;
	}

	public String checkFeesOfStudent(String stuId, String accYear, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStageAmount Starting");
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int count = 0;
		String status=null;	
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement("SELECT COUNT(*) FROM `campus_fee_collection` WHERE `admissionNo`=? AND `accYear`=?");
			pst.setString(1, stuId);
			pst.setString(2, accYear);
			rs = pst.executeQuery();
			////ln("FAEAEA "+pst);
			while (rs.next()) {
				count=rs.getInt(1);
			}
			if(count>0){
				status="true";
			}else{
				status="false";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStageAmount Ending");

		return status;
	}


	public List<StaffAttendanceVo> getStaffName(StaffAttendanceVo vo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStaffName Starting");
		String searchTerm = vo.getSearchtearm() + "%";
		List<StaffAttendanceVo> staffList = new ArrayList<StaffAttendanceVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmObj = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STAFF_SEARCH_BY_AUTO);

			pstmObj.setString(1, searchTerm);
			pstmObj.setString(2, searchTerm);

			//ln("stafflist-->> "+pstmObj);

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StaffAttendanceVo vo1 = new StaffAttendanceVo();
				vo1.setTeacherId(rs.getString("TeacherID"));
				vo1.setTeacherName(rs.getString("staffName"));
				staffList.add(vo1);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStaffName Ending");

		return staffList;
	}


	private int saveStudentTransportDetails(StudentRegistrationVo formObj, Connection con,String session,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Starting");

		PreparedStatement pstmt = null;
		Connection conn = null;
		int status = 0;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();
		try {
			conn = con;
			for (int i = 0; i < formObj.getStudentIdArray().length; i++) {
				pstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.INSERT_STUDENT_PROMOTION_IN_TRANSPORTDETAILS);
				pstmt.setString(1, formObj.getStudentIdArray()[i]);
				pstmt.setString(2, formObj.getAcademicyear_toArray()[i]);
				pstmt.setString(3, formObj.getLocationIdArray()[i]);
				StudentRegistrationVo list = getTransportDetails(formObj.getStudentIdArray()[i],formObj.getAcademicyear_fromArray()[i], formObj.getLocationIdArray()[i],userLoggingsVo);
				if(list.getIsTransport()==null && list.getTransporttypeId()==null){
					pstmt.setString(4, "N");
					pstmt.setString(5, "");
					pstmt.setString(6, "");
					pstmt.setString(7, "");
				}else{
					pstmt.setString(4, list.getIsTransport());
					pstmt.setString(5, list.getTransporttypeId());
					pstmt.setString(6, list.getStage_id());
					pstmt.setString(7, list.getRoute());
				}
				pstmt.setString(8, formObj.getCreateUser().trim());
				pstmt.setTimestamp(9, createdDate);
				status = pstmt.executeUpdate();

				if(status>0){
					HelperClass.recordLog_Activity(session,"Student","StudentPromotion","Insert",pstmt.toString(),userLoggingsVo);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : saveStudentClassDetails Ending");
		return status;
	}

	private StudentRegistrationVo getTransportDetails(String studentId, String accYearId, String locationId,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportDetails Starting");

		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmObj = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GETSTUDENT_TRANSPORT_DETAILS);
			pstmObj.setString(1, studentId);
			pstmObj.setString(2, accYearId);
			pstmObj.setString(3, locationId);
			rs = pstmObj.executeQuery();

			while (rs.next()) {
				studentRegistrationVo.setIsTransport(rs.getString("isTransport"));
				studentRegistrationVo.setTransporttypeId(rs.getString("TransportType"));
				studentRegistrationVo.setStage_id(rs.getString("StageId"));
				studentRegistrationVo.setRoute(rs.getString("route"));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportDetails Ending");

		return studentRegistrationVo;

	}

	public StudentConcessionVo getTransportAvailableStudent(String stuidno, String accyear,String locId,UserLoggingsPojo pojo) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportAvailableStudent Starting");

		StudentConcessionVo stuReg = new StudentConcessionVo();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement("SELECT cs.locationId,st.student_admissionno_var,st.student_id_int,st.student_fname_var,st.student_lname_var,cc.classdetail_id_int,cc.classdetails_name_var,cs.classsection_id_int,cs.classsection_name_var,csc.specilization,cst.`isTransport`,cst.`StageId`,cfs.`stage_name`,cst.`route`,tr.`RouteName` FROM campus_student st JOIN campus_student_classdetails csc ON st.student_id_int=csc.student_id_int JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) JOIN campus_classsection cs ON (csc.classsection_id_int=cs.classsection_id_int AND csc.locationId=cs.locationId) LEFT JOIN `campus_student_transportdetails` cst ON (cst.student_id_int=csc.student_id_int AND cst.`fms_acadamicyear_id_int`=csc.fms_acadamicyear_id_int) LEFT JOIN `campus_fee_stage` cfs ON cfs.`stage_id`=cst.`StageId` LEFT JOIN `transport_route_stage_mapping` trsm ON trsm.`RouteCode`=cst.`route` AND trsm.`StageCode`=cfs.`stage_id` LEFT JOIN `transport_route` tr ON tr.`RouteCode`=cst.`route` WHERE st.student_admissionno_var = ? AND csc.fms_acadamicyear_id_int=? AND csc.locationId=?");
			pst.setString(1, stuidno);
			pst.setString(2, accyear);
			pst.setString(3, locId);
			rs = pst.executeQuery();
			while (rs.next()) {
				stuReg.setAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setStudent(rs.getString("student_fname_var") + " " + rs.getString("student_lname_var"));
				stuReg.setClassId(rs.getString("classdetail_id_int"));
				stuReg.setClass_section(rs.getString("classdetails_name_var") + " - " + rs.getString("classsection_name_var"));
				stuReg.setSpecialization(rs.getString("specilization"));
				stuReg.setLocID(rs.getString("locationId"));
				stuReg.setIsTransport(rs.getString("isTransport"));
				stuReg.setStageId(rs.getString("StageId"));
				stuReg.setStage_name(rs.getString("stage_name"));
				stuReg.setRoute(rs.getString("route"));
				stuReg.setRouteName(rs.getString("RouteName"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportAvailableStudent Ending");

		return stuReg;
	}
	
	/*public synchronized List<ConcessionDetailsPojo> getConcessionTypes(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : getconcessiondetails Starting");
		 
		PreparedStatement pstmt = null;
		List<ConcessionDetailsPojo> concessiondetailsList = new ArrayList<ConcessionDetailsPojo>();

		ResultSet rs = null;
		Connection conn = null;

			try {
				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn.prepareStatement("SELECT concessionid,concessionname FROM campus_fee_concessiondetails WHERE isActive='Y'");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ConcessionDetailsPojo pojo = new ConcessionDetailsPojo();
					pojo.setConcessionId(rs.getString("concessionid"));
					pojo.setConcessionName(rs.getString("concessionname"));
					concessiondetailsList.add(pojo);
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
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
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: getconcessiondetails : Ending");
		return concessiondetailsList;

	}*/
	
	
	
	public List<ConcessionDetailsPojo> getConcessionTypes(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : getconcessiondetails Starting");

		PreparedStatement pstmt = null;
		List<ConcessionDetailsPojo> concessiondetailsList = new ArrayList<ConcessionDetailsPojo>();

		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("SELECT concessionid,concessionname FROM campus_fee_concessiondetails WHERE isActive='Y'");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ConcessionDetailsPojo pojo = new ConcessionDetailsPojo();
				pojo.setConcessionId(rs.getString("concessionid"));
				pojo.setConcessionName(rs.getString("concessionname"));
				concessiondetailsList.add(pojo);
			}
		}
	catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
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
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: getconcessiondetails : Ending");
		return concessiondetailsList;

	}

	public List<StudentRegistrationVo> getStudentRegistrationList(StudentRegistrationVo data, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentRegistrationList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		PreparedStatement pst1 = null;
		ResultSet rs1 = null;
		int totalCount=0;
		Connection conn = null;
		int sno=0;

		try {
			    conn = JDBCConnection.getSeparateConnection(pojo);
				
				pst = conn.prepareStatement("select distinct stu.student_doj_var,loc.Location_Name,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,case when csc.student_house is null then '-' when csc.student_house='' then '-' else csc.student_house end student_house,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int JOIN campus_location loc ON loc.Location_Id = csc.locationId join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where stu.locationId like ? and stu.fms_acadamicyear_id_int=? and csc.classdetail_id_int like ? and ccs.classsection_id_int like ?  and stu.student_status_var=?  and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or stu.student_dob_var like ? or stu.student_status_var like ? or CONCAT(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ? or ca.acadamic_year like ?)  AND loc.isActive='Y' AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' and csc.`fms_acadamicyear_id_int` LIKE ? AND csc.student_status='STUDYING' ORDER BY loc.Location_Name,CAST(SUBSTRING(csc.classdetail_id_int,4,LENGTH(csc.classdetail_id_int)-3)AS UNSIGNED),ccs.classsection_name_var,studentname LIMIT ?,?");
				
				pst.setString(1, data.getLocationId());
				
				pst.setString(2, data.getAcademicYear());
				
				if(data.getClasscode().equalsIgnoreCase("all")){
					pst.setString(3, "%%");
				}
				else{
				pst.setString(3, data.getClasscode());
				}
				if(data.getSection_id().equalsIgnoreCase("all")){
					pst.setString(4, "%%");
				}
				else{
				pst.setString(4, data.getSection_id());
				}
				pst.setString(5, data.getIsActive());
				
				
				pst.setString(6, "%" + data.getSearchTerm() + "%");
				pst.setString(7, "%" + data.getSearchTerm() + "%");
				pst.setString(8, "%" + data.getSearchTerm() + "%");
				pst.setString(9, "%" + data.getSearchTerm() + "%");
				pst.setString(10, "%" + data.getSearchTerm() + "%");
				pst.setString(11, "%" + data.getSearchTerm() + "%");
				pst.setString(12, "%" + data.getSearchTerm() + "%");
				pst.setString(13, "%" + data.getSearchTerm() + "%");
				pst.setString(14, "%" + data.getSearchTerm() + "%");
				pst.setString(15, "%" + data.getSearchTerm() + "%");
				pst.setString(16, data.getAcademicYear());
				pst.setInt(17, data.getStartPoint());
				pst.setInt(18, data.getShow_per_page());
				
				pst1 = conn.prepareStatement("select COUNT(*) from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int AND stu.fms_acadamicyear_id_int=csc.fms_acadamicyear_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id join campus_location loc on loc.Location_Id=stu.locationId JOIN campus_classstream cstr ON cstr.locationId=loc.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where stu.locationId like ? and stu.fms_acadamicyear_id_int=? and csc.classdetail_id_int like ? and ccs.classsection_id_int like ? and stu.student_status_var = ? and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or stu.student_dob_var like ? or stu.student_status_var like ? or CONCAT(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ? or ca.acadamic_year like ?) AND loc.isActive='Y' AND ccd.isActive='Y' AND ccs.isActive='Y' AND ca.isActive='Y' AND cstr.isActive='Y' AND stu.`student_status_var` = 'active' AND csc.student_status='STUDYING' ");
				if(data.getLocationId().equalsIgnoreCase("all")){
					pst1.setString(1, "%%");
				}
				else{
					pst1.setString(1, data.getLocationId());
				}
				if(data.getAcademicYear().equalsIgnoreCase("all")){
					pst1.setString(2, "%%");
				}
				else{
				pst1.setString(2, data.getAcademicYear());
				}
				if(data.getClasscode().equalsIgnoreCase("all")){
					pst1.setString(3, "%%");
				}
				else{
				pst1.setString(3, data.getClasscode());
				}
				if(data.getSection_id().equalsIgnoreCase("all")){
					pst1.setString(4, "%%");
				}
				else{
				pst1.setString(4, data.getSection_id());
				}
				pst1.setString(5, data.getIsActive());
				pst1.setString(6, "%" + data.getSearchTerm() + "%");
				pst1.setString(7, "%" + data.getSearchTerm() + "%");
				pst1.setString(8, "%" + data.getSearchTerm() + "%");
				pst1.setString(9, "%" + data.getSearchTerm() + "%");
				pst1.setString(10, "%" + data.getSearchTerm() + "%");
				pst1.setString(11, "%" + data.getSearchTerm() + "%");
				pst1.setString(12, "%" + data.getSearchTerm() + "%");
				pst1.setString(13, "%" + data.getSearchTerm() + "%");
				pst1.setString(14, "%" + data.getSearchTerm() + "%");
				pst1.setString(15, "%" + data.getSearchTerm() + "%");
				//ln("totalCount in Studentcount -->>"+pst1);
			 
			rs = pst.executeQuery();
			rs1=pst1.executeQuery();
			while(rs1.next()){
			totalCount=rs1.getInt(1);	
			}
			rs1.close();
			pst1.close();
			while (rs.next()) {
				ArrayList<FeeNameVo> feeStatusList=new ArrayList<FeeNameVo>();
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setTotalCount(totalCount);
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setImage(rs.getString("imgurl"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setClassSectionId(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setDateofBirth( HelperClass.convertDatabaseToUI(rs.getString("student_dob_var")));
				registrationVo.setStudentStatus(rs.getString("student_status"));
				registrationVo.setDateofjoin(HelperClass.convertDatabaseToUI(rs.getString("student_doj_var")));
				registrationVo.setHouseName(rs.getString("student_house"));
				
				PreparedStatement termpstmt=conn.prepareStatement(SQLUtilConstants.GET_TERMID_TERMNAME_BY_LOCATION_ACCYEAR);
				termpstmt.setString(1, rs.getString("locationId"));
				termpstmt.setString(2, data.getAcademicYear());
				ResultSet termRs=termpstmt.executeQuery();
				while(termRs.next()){
					int getcount=0;
					String paymentStatus="Not Paid";
					FeeNameVo fStatus=new FeeNameVo();
					PreparedStatement getStatus=conn.prepareStatement(SQLUtilConstants.GET_COUNT_FROM_campus_fee_collection_BY_TERMCODE_ADMISSIONNO);
					getStatus.setString(1, termRs.getString("termid"));
					getStatus.setString(2, rs.getString("student_id_int"));
					ResultSet getstRs=getStatus.executeQuery();
					if(getstRs.next()){
						getcount=getstRs.getInt(1);
					}
					if(getcount > 0){
						paymentStatus="Paid";
					}
					fStatus.setTerm(termRs.getString("termname"));
					fStatus.setStatus(paymentStatus);
					feeStatusList.add(fStatus);
				}
				registrationVo.setFeeStatus(feeStatusList);
				list.add(registrationVo);
				
				termRs.close();
				termpstmt.close();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentRegistrationList  Ending");

		  return list;
	   }
	
	
	public List<StudentRegistrationVo> getStudentListByTransportRequestCancel(StudentRegistrationVo vo, UserLoggingsPojo custdetails)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListByTransportRequestCancel  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENTS_BY_TRANSPORT_REQUEST_CANCEL);
			pst.setString(1, vo.getLocationId());
			pst.setString(2, vo.getAcademicYearId());
			pst.setString(3, vo.getClassname());
			pst.setString(4, vo.getSection_id());
			pst.setString(5, vo.getStatus());
			pst.setString(6, vo.getSearchTerm()+"%");
			pst.setString(7, vo.getSearchTerm()+"%");
			pst.setString(8, vo.getSearchTerm()+"%");
			pst.setString(9, vo.getSearchTerm()+"%");
			//ln("GET_STUDENTS_BY_TRANSPORT_REQUEST_CANCEL -->>"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getClassDetails  Ending");

		return list;
	}
	public List<StudentRegistrationVo> getStudentListBySectionReportCard(String locationid, String accyear, String classname,
			String sectionid,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		if(locationid.equalsIgnoreCase("all")){
			locationid="%%";
		}
		if(classname.equalsIgnoreCase("all")){
			classname="%%";
		}
		if(sectionid.equalsIgnoreCase("all")){
			sectionid="%%";
		}
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			String query="select distinct csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-'  WHEN csc.student_house ='' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END house,ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,CASE WHEN csc.secondlanguage IS NULL THEN '-'   WHEN csc.secondlanguage ='' THEN '-' ELSE csc.secondlanguage END secondlanguage,CASE WHEN csc.thirdlanguage IS NULL THEN '-'   WHEN csc.thirdlanguage ='' THEN '-' ELSE csc.thirdlanguage END thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year , case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var)end studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu JOIN campus_student_classdetails csc on stu.student_id_int=csc.student_id_int left join campus_classdetail ccd on csc.classdetail_id_int=ccd.classdetail_id_int left join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear acy on csc.fms_acadamicyear_id_int=acy.acadamic_id LEFT JOIN campus_house_settings hs ON (hs.loc_id=csc.locationId AND hs.house_id=csc.student_house) JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int where cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y' and stu.student_status_var='active'";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!locationid.equalsIgnoreCase("%%")) {
				map.put("csc.locationId",locationid);
				vec.add("csc.locationId");
			}
			if(!accyear.equalsIgnoreCase("%%") ) {
				map.put("csc.fms_acadamicyear_id_int",accyear);
				vec.add("csc.fms_acadamicyear_id_int");
			}
			if(!classname.equalsIgnoreCase("%%")) {
				map.put("csc.classdetail_id_int",classname);
				vec.add("csc.classdetail_id_int");
			}
			if(!sectionid.equalsIgnoreCase("%%")) {
				map.put("csc.classsection_id_int",sectionid);
				vec.add("csc.classsection_id_int");
			}

			Enumeration<String> e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql=" AND "+key+" = '"+val+"'";
					count++;
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
				}
			}

			String finalquery="";
			if(wheresql != null) {
				finalquery=query+" "+wheresql+" ORDER BY CAST(SUBSTRING(csc.`classdetail_id_int`,4,LENGTH(csc.`classdetail_id_int`)-3) AS SIGNED),studentname,stu.student_admissionno_var*1"; 
			}else {
				finalquery=query+" "+"ORDER BY CAST(SUBSTRING(csc.`classdetail_id_int`,4,LENGTH(csc.`classdetail_id_int`)-3) AS SIGNED),studentname,stu.student_admissionno_var*1;";
			}
			pst = conn.prepareStatement(finalquery);
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				PreparedStatement SecondlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
				while(SecondLangaugeRs.next()){
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}
				SecondLangaugeRs.close();
				SecondlanguageName.close();
				PreparedStatement thirdlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
				while(thirdlanguageRs.next()){
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}	
				thirdlanguageRs.close();
				thirdlanguageName.close();
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}

				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentListByFeeRequestCancel(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListByFeeRequestCancel  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);

			if(vo.getStatus().equals("Y")) {
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENTS_BY_FEE_REQUEST_CANCEL);	
				
				pst.setString(1, vo.getLocationId());
				pst.setString(2, vo.getAcademicYearId());
				pst.setString(3, vo.getClassname());
				pst.setString(4, vo.getSection_id());
				pst.setString(5, vo.getSearchTerm()+"%");
				pst.setString(6, vo.getSearchTerm()+"%");
				pst.setString(7, vo.getSearchTerm()+"%");
				pst.setString(8, vo.getSearchTerm()+"%");
			}
			else {
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENTS_BY_FEE_REQUEST);
				
				pst.setString(1, vo.getAcademicYearId());
				pst.setString(2, vo.getClassname());
				pst.setString(3, vo.getLocationId());
				pst.setString(4, vo.getAcademicYearId());
				pst.setString(5, vo.getClassname());
				pst.setString(6, vo.getSection_id());
				pst.setString(7, vo.getSearchTerm()+"%");
				pst.setString(8, vo.getSearchTerm()+"%");
				pst.setString(9, vo.getSearchTerm()+"%");
				pst.setString(10, vo.getSearchTerm()+"%");
			}
	
			System.out.println("pst"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListByFeeRequestCancel  Ending");

		return list;

	}

	public StudentRegistrationVo getFeeRequestStudentWise(StudentRegistrationVo stuvo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListByFeeRequestCancel  Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		StudentRegistrationVo vo1 = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FEE_REQUEST_STU_DETAILS);
			pstmt.setString(1,stuvo.getStudentId());
			pstmt.setString(2,stuvo.getLocationId());
			pstmt.setString(3,stuvo.getClassname());
			pstmt.setString(4,stuvo.getSection_id());
			pstmt.setString(5,stuvo.getAcademicYearId());

			rs = pstmt.executeQuery();
			while(rs.next()){
				vo1 = new StudentRegistrationVo();
				vo1.setStudentFullName(rs.getString("student"));
				vo1.setAdmissionNo(rs.getString("student_admissionno_var"));
				vo1.setClassname(rs.getString("classdetails_name_var"));
				vo1.setSectionnaem(rs.getString("classsection_name_var"));
				vo1.setStudentStatus(rs.getString("student_status"));
				vo1.setAddress(rs.getString("address").trim());
				vo1.setImage(rs.getString("student_imgurl_var"));

				if(rs.getString("isConcession").equals("Y")) {
					vo1.setStatus("Yes");
				}else {
					vo1.setStatus("No");
				}
			}
		}

		catch (Exception e) 
		{
			e.printStackTrace();
		}

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
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListByFeeRequestCancel  Ending");

		return vo1;

	}

	public List<ConcessionDetailsPojo> getSubConcessionTypes(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : getSubConcessionTypes Starting");
		PreparedStatement pstmt = null;
		List<ConcessionDetailsPojo> concessiondetailsList = new ArrayList<ConcessionDetailsPojo>();
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("SELECT `contype_id`,`concession_name` FROM `campus_concession_types` WHERE `isActive`='Y'");
			////ln("CONCESSION TYPES"+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ConcessionDetailsPojo pojo = new ConcessionDetailsPojo();
				pojo.setConcessionId(rs.getString("contype_id"));
				pojo.setConcessionName(rs.getString("concession_name"));
				concessiondetailsList.add(pojo);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
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
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: getSubConcessionTypes : Ending");
		return concessiondetailsList;
	}

	public String validateRegistrationNo(String searchTerm, UserLoggingsPojo pojo, String stuId) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in StudentRegistrationDaoImpl : validateRollNumber Starting");

		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;
		String registerNo=searchTerm.split(",")[0];
		String location=searchTerm.split(",")[1];
		/*String academicYearId=searchTerm.split(",")[2];*/

		int count = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			preparedStatement = conn.prepareStatement("SELECT COUNT(*) FROM campus_student WHERE student_application_no=? AND student_id_int!=?");  /* locationId=? AND */
			/*preparedStatement.setString(1, location);*/
			preparedStatement.setString(1, registerNo);
			preparedStatement.setString(2, stuId);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "true";
			} else {
				successMessage = "false";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null && (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : validateRollNumber Ending");
		return successMessage;
	}
	
	public List<StudentRegistrationVo> rollNoGeneration(String locationid, String accyear, String classname,
			String sectionid, String order1, String order2, String order3,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;

		try {
			
//			String option = order1.split(" ")[0];
//			String option1 = order1.split(" ")[1];
//			
//			if(option.equalsIgnoreCase("student_admissionno_var")){
//				option = "cast(student_admissionno_var as signed)"+" "+option1+","+"student_fname_var ASC"+" ";
//			}else if(option.equalsIgnoreCase("student_gender_var")){
//				option = option+" "+option1+","+"student_fname_var ASC"+" ";
//			}else{
//				option = order1;
//			}
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			if(order2.trim().equalsIgnoreCase("NO")) {
				String option = order1.split(" ")[0];
				String option1 = order1.split(" ")[1];
				if(order1.contains("student_admissionno_var")){
					order1 = "cast(student_admissionno_var as signed)"+" "+option1+","+"student_fname_var ASC"+" ";
				}else if(order1.contains("student_gender_var")){
					order1 = option+" "+option1+","+"student_fname_var ASC"+" ";
				}
				
				pst = conn.prepareStatement("SELECT  ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,csc.secondlanguage,csc.thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int AND stu.`locationId`=csc.`locationId`JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id AND acy.`isActive`='Y' JOIN `campus_location` cl ON cl.`Location_Id`=csc.`locationId` AND cl.`isActive`='Y' JOIN `campus_classstream` stm ON stm.`classstream_id_int`=csc.`fms_classstream_id_int` AND stm.`isActive`='Y' JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int AND csc.locationId=ccd.locationId AND ccd.`isActive`='Y' JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int WHERE stu.`student_status_var`='active' AND csc.locationId=? AND csc.fms_acadamicyear_id_int=? AND csc.classdetail_id_int=? AND csc.classsection_id_int=? ORDER BY "+order1+",secondlanguage,thirdlanguage");
			}
			else if(order3.trim().equalsIgnoreCase("NO")) {
				String option = order1.split(" ")[0];
				String option1 = order1.split(" ")[1];
				
				String option2 = order2.split(" ")[0];
				String option3 = order2.split(" ")[1];
				
				if(order1.contains("student_admissionno_var")){
					order1 = "cast(student_admissionno_var as signed)"+" "+option1+","+"student_fname_var ASC"+" ";
				}else if(order1.contains("student_gender_var")){
					order1 = option+" "+option1+","+"student_fname_var ASC"+" ";
				}
				
				if(order2.contains("student_admissionno_var")){
					order2 = "cast(student_admissionno_var as signed)"+" "+option3+","+"student_fname_var ASC"+" ";
				}else if(order1.contains("student_gender_var")){
					order2 = option2+" "+option3+","+"student_fname_var ASC"+" ";
				}
				
				pst = conn.prepareStatement("SELECT  ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,csc.secondlanguage,csc.thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int AND stu.`locationId`=csc.`locationId`JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id AND acy.`isActive`='Y' JOIN `campus_location` cl ON cl.`Location_Id`=csc.`locationId` AND cl.`isActive`='Y' JOIN `campus_classstream` stm ON stm.`classstream_id_int`=csc.`fms_classstream_id_int` AND stm.`isActive`='Y' JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int AND csc.locationId=ccd.locationId AND ccd.`isActive`='Y' JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int WHERE stu.`student_status_var`='active' AND csc.locationId=? AND csc.fms_acadamicyear_id_int=? AND csc.classdetail_id_int=? AND csc.classsection_id_int=? ORDER BY "+order1+","+order2+",secondlanguage,thirdlanguage");
			}
			else {
				
				String option = order1.split(" ")[0];
				String option1 = order1.split(" ")[1];
				
				String option2 = order2.split(" ")[0];
				String option3 = order2.split(" ")[1];
				
				String option4 = order3.split(" ")[0];
				String option5 = order3.split(" ")[1];
				
				if(order1.contains("student_admissionno_var")){
					order1 = "cast(student_admissionno_var as signed)"+" "+option1+","+"student_fname_var ASC"+" ";
				}else if(order1.contains("student_gender_var")){
					order1 = option+" "+option1+","+"student_fname_var ASC"+" ";
				}
				
				if(order2.contains("student_admissionno_var")){
					order2 = "cast(student_admissionno_var as signed)"+" "+option3+","+"student_fname_var ASC"+" ";
				}else if(order1.contains("student_gender_var")){
					order2 = option2+" "+option3+","+"student_fname_var ASC"+" ";
				}
				
				if(order3.contains("student_admissionno_var")){
					order3 = "cast(student_admissionno_var as signed)"+" "+option5+","+"student_fname_var ASC"+" ";
				}else if(order3.contains("student_gender_var")){
					order3 = option4+" "+option5+","+"student_fname_var ASC"+" ";
				}
				
				pst = conn.prepareStatement("SELECT  ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,csc.secondlanguage,csc.thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int AND stu.`locationId`=csc.`locationId`JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id AND acy.`isActive`='Y' JOIN `campus_location` cl ON cl.`Location_Id`=csc.`locationId` AND cl.`isActive`='Y' JOIN `campus_classstream` stm ON stm.`classstream_id_int`=csc.`fms_classstream_id_int` AND stm.`isActive`='Y' JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int AND csc.locationId=ccd.locationId AND ccd.`isActive`='Y' JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int WHERE stu.`student_status_var`='active' AND csc.locationId=? AND csc.fms_acadamicyear_id_int=? AND csc.classdetail_id_int=? AND csc.classsection_id_int=? ORDER BY "+order1+","+order2+","+order3+",secondlanguage,thirdlanguage");
			}

			//pst = conn.prepareStatement("SELECT  ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,csc.secondlanguage,csc.thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int AND stu.`locationId`=csc.`locationId`JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id AND acy.`isActive`='Y' JOIN `campus_location` cl ON cl.`Location_Id`=csc.`locationId` AND cl.`isActive`='Y' JOIN `campus_classstream` stm ON stm.`classstream_id_int`=csc.`fms_classstream_id_int` AND stm.`isActive`='Y' JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int AND csc.locationId=ccd.locationId AND ccd.`isActive`='Y' JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int WHERE stu.`student_status_var`='active' AND csc.locationId=? AND csc.fms_acadamicyear_id_int=? AND csc.classdetail_id_int=? AND csc.classsection_id_int=? ORDER BY "+order1+",secondlanguage,thirdlanguage");
			pst.setString(1, locationid.trim());
			pst.setString(2, accyear.trim());
			pst.setString(3, classname.trim());
			pst.setString(4, sectionid.trim());
			rs = pst.executeQuery();

			//ln(pst);

			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				PreparedStatement SecondlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
				if(SecondLangaugeRs.next()){
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}
				else{
					registrationVo.setSecondLanguageName("-");	
				}
				PreparedStatement thirdlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
				if(thirdlanguageRs.next()){
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}	
				else{
					registrationVo.setThirdLanguageName("-");	
				}
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentListBySectionForRollNo(String locationid, String accyear, String classname,
			String sectionid,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;


		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement("SELECT  ccd.classdetail_id_int,csc.classsection_id_int,csc.fms_classstream_id_int,csc.secondlanguage, "+
										"csc.thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId, "+
										"csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year , "+ 
										"CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE "+ 
										"CONCAT(stu.student_fname_var,' ',stu.student_lname_var)END studentname, "+
										"CASE WHEN csc.student_rollno IS NULL THEN '-' ELSE csc.student_rollno END student_rollno, "+
										"ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu "+ 
										"JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int "+ 
										"JOIN `campus_location` loc ON loc.`Location_Id` = csc.locationId AND loc.`isActive` = 'Y' "+
										"JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int AND csc.locationId=ccd.locationId AND ccd.`isActive` = 'Y' "+
										"JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int AND ccs.`isActive` = 'Y' "+
										"JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id AND acy.`isActive` = 'Y' WHERE csc.locationId LIKE ? AND csc.fms_acadamicyear_id_int LIKE ? AND csc.classdetail_id_int LIKE ? AND csc.classsection_id_int LIKE ? AND csc.`student_status`='STUDYING' ORDER BY studentname,CAST(stu.student_admissionno_var AS UNSIGNED)");
			pst.setString(1, locationid.trim());
			pst.setString(2, accyear.trim());
			pst.setString(3, classname.trim());
			pst.setString(4, sectionid.trim());
			rs = pst.executeQuery();

			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				PreparedStatement SecondlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
				if(SecondLangaugeRs.next()){
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}
				else{
					registrationVo.setSecondLanguageName("-");	
				}
				PreparedStatement thirdlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
				if(thirdlanguageRs.next()){
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}	
				else{
					registrationVo.setThirdLanguageName("-");	
				}
				if(rs.getString("specilization").equalsIgnoreCase("-"))
				{
					registrationVo.setSpecilizationname("-");
				}
				else
				{
					pstmt = conn.prepareStatement("select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while(rst.next())
					{
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
			+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

	return list;
}


	public List<StudentRegistrationVo> feeCollectedStudents(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : feeCollectedStudents Starting");

		String searchTerm = registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmObj = conn.prepareStatement("SELECT DISTINCT CASE WHEN student_lname_var IS NULL THEN student_fname_var ELSE CONCAT(student_fname_var, ' ',student_lname_var) END StudentName,student_id_int FROM campus_student st JOIN `campus_fee_collection` cf ON cf.`admissionNo`=st.student_id_int where cf.accYear=? and student_status_var ='active' and student_fname_var like ? || student_lname_var like ?");

			pstmObj.setString(1, registrationVo.getAcademicYear());
			pstmObj.setString(2, searchTerm);
			pstmObj.setString(3, searchTerm);
			////ln("STUDENT_SEARCH_BY_SIBLING-->> "+pstmObj);

			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setStudentnamelabel(rs.getString("StudentName"));
				studentRegistrationVo.setStudentidlabel(rs.getString("student_id_int"));
				registrationList.add(studentRegistrationVo);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : feeCollectedStudents Ending");

		return registrationList;
	}

	public String NewCancelStudentTC(String[] splitlocation, String[] splitaccyear, String[] splitstudentid,
			UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : NewCancelStudentTC Starting");
		PreparedStatement pstmt = null;
		Connection con = null;
		int status = 0;
		String smsg = null;
		try {
			//ln("Inside the saveStudentPromotion DaoImpl");
			con = JDBCConnection.getSeparateConnection(custdetails);


			for (int i = 0; i < splitstudentid.length; i++) {
				con.setAutoCommit(false);
				pstmt = con.prepareStatement("DELETE FROM campus_tc_details WHERE locationId=? AND `acadamic_year`=? AND `student_id_int`=?");
				pstmt.setString(1, splitlocation[i]);
				pstmt.setString(2, splitaccyear[i]);
				pstmt.setString(3, splitstudentid[i]);

				status = pstmt.executeUpdate();	

				//ln("status "+pstmt);
				if(status>0){

					PreparedStatement classDetaiUpdate=con.prepareStatement("UPDATE campus_student_classdetails SET student_status='STUDYING' WHERE student_id_int=? AND fms_acadamicyear_id_int=?");
					classDetaiUpdate.setString(1, splitstudentid[i]);
					classDetaiUpdate.setString(2, splitaccyear[i]);
					int classDetaiUpdateInt=classDetaiUpdate.executeUpdate();
					if(classDetaiUpdateInt>0) {
						con.commit();
						smsg="TC Cancelled Successfully...";
					}
					else{
						con.rollback();
						smsg="TC Cancelled Failed";
					}

				}else{
					con.rollback();
					smsg="TC Cancelled Failed";
				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{

			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}
			}catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : NewCancelStudentTC Ending");
		return smsg;
	}

	public List<StudentRegistrationVo> getStudentAnalyticalPerformanceTable(StudentRegistrationVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentAnalyticalPerformanceTable Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;

		try {

			conn = JDBCConnection.getSeparateConnection(vo.getDbdetails());
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_ANALYTICAL_PERFORMANCE_TABLE);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("studentId"));
				stuReg.setAnalyticalRemarksId(rs.getString("analytical_remarks_id"));
				stuReg.setRemarksName(rs.getString("remarks_name"));
				if (rs.getString("remarks_name").equalsIgnoreCase("Activities (E/C/A)")) {
					stuReg.setRemark("ECA");
				}
				stuReg.setAnalyticalId(rs.getString("analytical_id"));
				stuReg.setExcellent(rs.getString("excellent"));
				stuReg.setVeryGood(rs.getString("verygood"));
				stuReg.setGood(rs.getString("good"));
				stuReg.setAverage(rs.getString("average"));
				stuReg.setNeedcare(rs.getString("needcare"));
				stuReg.setParentsOpinion(rs.getString("parents_opinion"));
				stuReg.setOtherComments(rs.getString("other_comments"));
				list.add(stuReg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentAnalyticalPerformanceTable Ending");

		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentListByAnalyticalPerformance(StudentRegistrationVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListByAnalyticalPerformance  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(vo.getDbdetails());
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENTD_LIST_BY_ANALYTICAL_PERFORMANCE);
			pst.setString(1, vo.getAcademicYear());
			pst.setString(2, vo.getLocation());
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				if (rs.getString("stuId") == "-" || rs.getString("stuId").equalsIgnoreCase("-")) {
					registrationVo.setStudentStatus("Not Set");
				} else {
					registrationVo.setStudentStatus("Set");
				}
				registrationVo.setHouseName(rs.getString("house"));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(
						rs.getString("classdetails_name_var") + "-" + rs.getString("classsection_name_var"));

				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));

				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));

				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				PreparedStatement SecondlanguageName = conn
						.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs = SecondlanguageName.executeQuery();
				while (SecondLangaugeRs.next()) {
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}

				PreparedStatement thirdlanguageName = conn
						.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs = thirdlanguageName.executeQuery();
				while (thirdlanguageRs.next()) {
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}

				if (rs.getString("specilization").equalsIgnoreCase("-")) {
					registrationVo.setSpecilizationname("-");
				} else {
					pstmt = conn.prepareStatement(
							"select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while (rst.next()) {
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListByAnalyticalPerformance  Ending");

		return list;
	}

	@Override
	public String SaveStudentAnalyticalPerformance(StudentRegistrationVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentAnalyticalPerformanceTable Starting");

		String status = null, Column = null, openian = null, othersopenian = null;
		PreparedStatement pst = null, pst1 = null;
		Connection conn = null;
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(vo.getDbdetails());
			pst1 = conn.prepareStatement("DELETE FROM campus_student_analytical_performance WHERE studentId=? and accyearId=?");
			pst1.setString(1, vo.getStudentId());
			pst1.setString(2, vo.getAccyear());
			pst1.executeUpdate();

			for (int i = 0; i < vo.getRowIds().length; i++) {
				if (vo.getRowIds()[i].split("-")[1].equalsIgnoreCase("undefined")
						|| vo.getRowIds()[i].split("-")[1].equalsIgnoreCase("")) {
					Column = "";
				} else {
					Column = vo.getRowIds()[i].split("-")[1];
				}
				pst = conn.prepareStatement(
						"INSERT INTO campus_student_analytical_performance (studentId,accyearId,locationId,classId,sectionId,analytical_remarks_id,parents_opinion,other_comments,created_by,"
								+ Column + ") VALUES(?,?,?,?,?,?,?,?,?,'Y')");
				pst.setString(1, vo.getStudentId());
				pst.setString(2, vo.getAccyear());
				pst.setString(3, vo.getLocation());
				pst.setString(4, vo.getClassname());
				pst.setString(5, vo.getSectionnaem());
				pst.setString(6, vo.getRowIds()[i].split("-")[0]);
				openian = vo.getRowIds()[i].split("-")[2];
				pst.setString(7, openian);
				othersopenian = vo.getRowIds()[i].split("-")[3];
				if (othersopenian == "" || othersopenian == null || othersopenian.equalsIgnoreCase("undefined")) {
					othersopenian = "";
				}
				pst.setString(8, othersopenian);
				pst.setString(9, vo.getCreateUser());

				count = pst.executeUpdate();
			}
			if (count > 0) {
				status = "true";
				HelperClass.recordLog_Activity(vo.getLog_audit_session(), "Student", "Student Analytical Performance", "Insert", pst.toString(), conn);
			} else {
				status = "false";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentAnalyticalPerformanceTable Ending");

		return status;
	}

	public List<StudentRegistrationVo> getStudentAnalyticalPerformanceForUpdate(StudentRegistrationVo vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentAnalyticalPerformanceForUpdate Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pstmt = null, pstmt1 = null;
		ResultSet rs = null, rs1 = null;
		Connection conn = null;
		int sno = 0, count = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(vo.getDbdetails());
			pstmt1 = conn.prepareStatement(
					"SELECT COUNT(*) FROM campus_student_analytical_performance WHERE studentId=? and accyearId=?");
			pstmt1.setString(1, vo.getStudentId());
			pstmt1.setString(2, vo.getAccyear());
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				count = rs1.getInt(1);
			}

			if (count > 0) {

				pstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_ANALYTICAL_PERFORMANCE_UPDATE_DETAILS);
				pstmt.setString(1, vo.getStudentId());
				pstmt.setString(2, vo.getAccyear());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					StudentRegistrationVo stuReg = new StudentRegistrationVo();
					sno++;
					stuReg.setSno(String.valueOf(sno));
					stuReg.setAnalyticalId(rs.getString("analytical_remarks_id"));
					stuReg.setParentsOpinion(rs.getString("opinion"));
					stuReg.setRemarksName(rs.getString("remarks_name"));

					if (rs.getString("excellent").equalsIgnoreCase("Y")) {
						stuReg.setSelection("excellent");
					} else if (rs.getString("verygood").equalsIgnoreCase("Y")) {
						stuReg.setSelection("verygood");
					} else if (rs.getString("good").equalsIgnoreCase("Y")) {
						stuReg.setSelection("good");
					} else if (rs.getString("average").equalsIgnoreCase("Y")) {
						stuReg.setSelection("average");
					} else if (rs.getString("needcare").equalsIgnoreCase("Y")) {
						stuReg.setSelection("needcare");
					}
					list.add(stuReg);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentAnalyticalPerformanceTable Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentListByHouseWiseAnalyticalPerformance(String locationid, String accyear,
			String housename, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno = 0;

		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENTD_BY_HOUSENAME);
			pst.setString(1, locationid);
			pst.setString(2, accyear);
			pst.setString(3, housename);

			rs = pst.executeQuery();

			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentStatus("InActive");
				registrationVo.setHouseName(rs.getString("housename"));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(
						rs.getString("classdetails_name_var") + "-" + rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));

				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				PreparedStatement SecondlanguageName = conn
						.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs = SecondlanguageName.executeQuery();
				while (SecondLangaugeRs.next()) {
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}

				PreparedStatement thirdlanguageName = conn
						.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs = thirdlanguageName.executeQuery();
				while (thirdlanguageRs.next()) {
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}

				if (rs.getString("specilization").equalsIgnoreCase("-")) {
					registrationVo.setSpecilizationname("-");
				} else {
					pstmt = conn.prepareStatement(
							"select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while (rst.next()) {
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentAnalyticPerformanceSearchByList(StudentRegistrationVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;

		try {

			conn = JDBCConnection.getSeparateConnection(vo.getDbdetails());
			pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_ANALYTICAL_PERFORMANCE_SEARCH_BY_ALL_FILTER);
			pst.setString(1, vo.getSearchTerm() + "%");
			pst.setString(2, vo.getSearchTerm() + "%");
			pst.setString(3, vo.getSearchTerm() + "%");
			pst.setString(4, vo.getSearchTerm() + "%");
			pst.setString(5, vo.getSearchTerm() + "%");
			pst.setString(6, vo.getSearchTerm() + "%");
			pst.setString(7, vo.getSearchTerm() + "%");
			pst.setString(8, vo.getSearchTerm() + "%");
			pst.setString(9, vo.getLocation());
			pst.setString(10, vo.getAcademicYear());
			pst.setString(11, vo.getClassname());
			pst.setString(12, vo.getSection_id());
			pst.setString(13, vo.getHouseId());

			rs = pst.executeQuery();
			System.out.println(pst);
			while (rs.next()) {
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setSno(String.valueOf(sno));
				if (rs.getString("stuId") == "-" || rs.getString("stuId").equalsIgnoreCase("-")) {
					stuReg.setStudentStatus("Not Set");
				} else {
					stuReg.setStudentStatus("Set");
				}
				stuReg.setHouseName(rs.getString("house"));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClasssection(
						rs.getString("classdetails_name_var") + "-" + rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				list.add(stuReg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Ending");

		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentFilteredListByAnalyticalPerformance(StudentRegistrationVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(vo.getDbdetails());
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTD_ANALYTICAL_PERFORMANCE_BY_SECTION2);
			pst.setString(1, vo.getLocation());
			pst.setString(2, vo.getAcademicYear());
			pst.setString(3, vo.getClassname());
			pst.setString(4, vo.getSection_id());

			rs = pst.executeQuery();

			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				if (rs.getString("stuId") == "-" || rs.getString("stuId").equalsIgnoreCase("-")) {
					registrationVo.setStudentStatus("Not Set");
				} else {
					registrationVo.setStudentStatus("Set");
				}
				registrationVo.setHouseName(rs.getString("house"));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClasssection(
						rs.getString("classdetails_name_var") + "-" + rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));

				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setSpecilization(rs.getString("specilization"));

				PreparedStatement SecondlanguageName = conn
						.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				SecondlanguageName.setString(1, rs.getString("secondlanguage"));
				ResultSet SecondLangaugeRs = SecondlanguageName.executeQuery();
				while (SecondLangaugeRs.next()) {
					registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
				}

				PreparedStatement thirdlanguageName = conn
						.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
				thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
				ResultSet thirdlanguageRs = thirdlanguageName.executeQuery();
				while (thirdlanguageRs.next()) {
					registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
				}

				if (rs.getString("specilization").equalsIgnoreCase("-")) {
					registrationVo.setSpecilizationname("-");
				} else {
					pstmt = conn.prepareStatement(
							"select Specialization_name from campus_class_specialization where Specialization_Id=?;");
					pstmt.setString(1, rs.getString("specilization"));

					rst = pstmt.executeQuery();
					while (rst.next()) {
						registrationVo.setSpecilizationname(rst.getString("Specialization_name"));
					}

				}

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByTC(String searchTerm, String locationid, String accyear,
			String classname, String sectionid, String housename, String status, String sortby,
			String orderbyAdmissionAndAlpha, String orderbyGender, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Starting");

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int sno = 0;

		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			if(sortby.equalsIgnoreCase("House")){
				pst = conn.prepareStatement(SQLUtilConstants.GET_STUDENTS_SEARCH_BY_ALL_FILTER_BY_HOUSE_WISE);
				pst.setString(1, searchValue+"%");
				pst.setString(2, searchValue+"%");
				pst.setString(3, searchValue+"%");
				pst.setString(4, searchValue+"%");
				pst.setString(5, searchValue+"%");
				pst.setString(6, searchValue+"%");
				pst.setString(7, searchValue+"%");
				pst.setString(8, searchValue+"%");

				pst.setString(9, locationId);
				pst.setString(10, accYear);
				pst.setString(11, className);
				pst.setString(12, section); 
				pst.setString(13, housename);
				if(status=="" || status==null){
					pst.setString(14, "active");
				}else{
					pst.setString(14, status);
				}
				//ln("GET_STUDENTS_SEARCH_BY_ALL_FILTER_BY_HOUSE_WISE -->>"+pst);
			}
			else if(sortby.equalsIgnoreCase("Alphabetical")){

				pst = conn.prepareStatement("SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-' WHEN csc.student_house ='' THEN '-' WHEN csc.student_house ='(NULL)' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END housename,csc.locationId,csc.classdetail_id_int,csc.classsection_id_int,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentName,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings hs ON (hs.house_id=csc.student_house AND hs.loc_id=csc.locationId  AND hs.house_id LIKE ? ) LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) AND csc.locationId LIKE ? AND csc.fms_acadamicyear_id_int =? AND ccd.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? and stu.student_status_var like ? AND cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y' and csc.`student_status` = 'STUDYING' ORDER BY length(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,studentName "+orderbyAdmissionAndAlpha+"");	
				pst.setString(1, housename);

				pst.setString(2, searchValue+"%");
				pst.setString(3, searchValue+"%");
				pst.setString(4, searchValue+"%");
				pst.setString(5, searchValue+"%");
				pst.setString(6, searchValue+"%");
				pst.setString(7, searchValue+"%");
				pst.setString(8, searchValue+"%");
				pst.setString(9, searchValue+"%");
				pst.setString(10, locationId);
				pst.setString(11, accYear);
				pst.setString(12, className);
				pst.setString(13, section); 
				if(status=="" || status==null){
					pst.setString(14, "%%");
				}else{
					pst.setString(14, status);
				}
				//ln("GET_STUDENTS_SEARCH_BY_ALL_FILTER 11 -->>"+pst);
			}
			else if(sortby.equalsIgnoreCase("Admission")){

				pst = conn.prepareStatement("SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-' WHEN csc.student_house ='' THEN '-' WHEN csc.student_house ='(NULL)' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END housename,csc.locationId,csc.classdetail_id_int,csc.classsection_id_int,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentName,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings hs ON (hs.house_id=csc.student_house AND hs.loc_id=csc.locationId  AND hs.house_id LIKE ? ) LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) AND csc.locationId LIKE ? AND csc.fms_acadamicyear_id_int =? AND ccd.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? and stu.student_status_var like ? AND cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y' AND csc.`student_status` = 'STUDYING' ORDER BY stu.student_admissionno_var "+orderbyAdmissionAndAlpha+",ccd.classdetails_name_var,ccs.classsection_name_var");	
				pst.setString(1, housename);

				pst.setString(2, searchValue+"%");
				pst.setString(3, searchValue+"%");
				pst.setString(4, searchValue+"%");
				pst.setString(5, searchValue+"%");
				pst.setString(6, searchValue+"%");
				pst.setString(7, searchValue+"%");
				pst.setString(8, searchValue+"%");
				pst.setString(9, searchValue+"%");
				pst.setString(10, locationId);
				pst.setString(11, accYear);
				pst.setString(12, className);
				pst.setString(13, section); 
				if(status=="" || status==null){
					pst.setString(14, "%%");
				}else{
					pst.setString(14, status);
				}
				//ln("GET_STUDENTS_SEARCH_BY_ALL_FILTER 12 -->>"+pst);
			}
			else if(sortby.equalsIgnoreCase("Gender")){
				if(orderbyGender.equalsIgnoreCase("ASC")){
					pst = conn.prepareStatement("SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-' WHEN csc.student_house ='' THEN '-' WHEN csc.student_house ='(NULL)' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END housename,csc.locationId,csc.classdetail_id_int,csc.classsection_id_int,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentName,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings hs ON (hs.house_id=csc.student_house AND hs.loc_id=csc.locationId  AND hs.house_id LIKE ? ) LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) AND csc.locationId LIKE ? AND csc.fms_acadamicyear_id_int =? AND ccd.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? and stu.student_status_var like ? AND cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y' AND csc.`student_status` = 'STUDYING' ORDER BY stu.student_gender_var LIKE 'f%' DESC,length(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,csc.student_rollno ");
				}else{
					pst = conn.prepareStatement("SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-' WHEN csc.student_house ='' THEN '-' WHEN csc.student_house ='(NULL)' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END housename,csc.locationId,csc.classdetail_id_int,csc.classsection_id_int,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentName,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings hs ON (hs.house_id=csc.student_house AND hs.loc_id=csc.locationId  AND hs.house_id LIKE ? ) LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) AND csc.locationId LIKE ? AND csc.fms_acadamicyear_id_int =? AND ccd.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? and stu.student_status_var like ? AND cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y' AND csc.`student_status` = 'STUDYING' ORDER BY stu.student_gender_var LIKE 'm%' DESC,length(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,csc.student_rollno ");
				}

				pst.setString(1, housename);
				pst.setString(2, searchValue+"%");
				pst.setString(3, searchValue+"%");
				pst.setString(4, searchValue+"%");
				pst.setString(5, searchValue+"%");
				pst.setString(6, searchValue+"%");
				pst.setString(7, searchValue+"%");
				pst.setString(8, searchValue+"%");
				pst.setString(9, searchValue+"%");
				pst.setString(10, locationId);
				pst.setString(11, accYear);
				pst.setString(12, className);
				pst.setString(13, section); 
				if(status=="" || status==null){
					pst.setString(14, "%%");
				}else{
					pst.setString(14, status);
				}
				//ln("GET_STUDENTS_SEARCH_BY_ALL_FILTER 13 -->>"+pst);
			}
			else if(sortby.equalsIgnoreCase("printstudentidcard")){
				pst = conn.prepareStatement("SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-' WHEN csc.student_house ='' THEN '-' WHEN csc.student_house ='(NULL)' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END housename,csc.locationId,csc.classdetail_id_int,csc.classsection_id_int,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentName,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings hs ON (hs.house_id=csc.student_house AND hs.loc_id=csc.locationId  AND hs.house_id LIKE ? ) LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) AND csc.locationId LIKE ? AND csc.fms_acadamicyear_id_int =? AND ccd.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? and stu.student_status_var like ? AND cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y' AND csc.`student_status` = 'STUDYING' ORDER BY LENGTH(ccd.classdetail_id_int) ASC,ccd.classdetail_id_int");
				pst.setString(1, housename);

				pst.setString(2, searchValue+"%");
				pst.setString(3, searchValue+"%");
				pst.setString(4, searchValue+"%");
				pst.setString(5, searchValue+"%");
				pst.setString(6, searchValue+"%");
				pst.setString(7, searchValue+"%");
				pst.setString(8, searchValue+"%"); 
				pst.setString(9, searchValue+"%");
				pst.setString(10, locationId);
				pst.setString(11, accYear);
				pst.setString(12, className);
				pst.setString(13, section); 
				if(status=="" || status==null){
					pst.setString(14, "%%");
				}else{
					pst.setString(14, status);
				}
				//ln("printstudentidcard -->>"+pst);

			}
			else{
				pst = conn.prepareStatement("SELECT DISTINCT csc.student_status,CASE WHEN csc.student_house IS NULL THEN '-' WHEN hs.status ='deleted' THEN '-' WHEN csc.student_house ='' THEN '-' WHEN csc.student_house ='(NULL)' THEN '-' WHEN hs.housename ='' THEN '-' WHEN hs.housename IS NULL THEN '-' ELSE hs.`housename` END housename,csc.locationId,csc.classdetail_id_int,csc.classsection_id_int,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,CASE WHEN stu.student_lname_var IS NULL THEN stu.student_fname_var ELSE CONCAT(stu.student_fname_var,' ',stu.student_lname_var) END studentName,CASE WHEN csc.student_rollno IS NULL THEN '-' WHEN csc.student_rollno='' THEN '-' WHEN csc.student_rollno='(NULL)' THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var FROM campus_student stu JOIN campus_student_classdetails csc ON stu.student_id_int=csc.student_id_int JOIN campus_classdetail ccd ON csc.classdetail_id_int=ccd.classdetail_id_int JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN campus_house_settings hs ON (hs.house_id=csc.student_house AND hs.loc_id=csc.locationId  AND hs.house_id LIKE ? ) LEFT JOIN campus_acadamicyear acy ON csc.fms_acadamicyear_id_int=acy.acadamic_id JOIN campus_location cl ON cl.Location_Id=csc.locationId JOIN campus_classstream cstr ON cstr.locationId=cl.Location_Id AND cstr.classstream_id_int=ccd.classstream_id_int WHERE (stu.student_admissionno_var LIKE ? OR stu.student_fname_var LIKE ? OR stu.student_lname_var LIKE ? OR CONCAT(stu.student_fname_var,' ',stu.student_lname_var) LIKE ? OR csc.student_rollno  LIKE ? OR ccd.classdetails_name_var LIKE ? OR ccs.classsection_name_var LIKE ? OR CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) LIKE ?) AND csc.locationId LIKE ? AND csc.fms_acadamicyear_id_int =? AND ccd.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? and stu.student_status_var like ? AND cl.isActive='Y' AND cstr.isActive='Y' AND acy.isActive='Y' AND ccs.isActive='Y' AND ccd.isActive='Y' AND csc.`student_status` = 'STUDYING' ORDER BY length(ccd.classdetail_id_int),ccd.classdetail_id_int,ccs.classsection_name_var,studentName");
				pst.setString(1, housename);

				pst.setString(2, searchValue+"%");
				pst.setString(3, searchValue+"%");
				pst.setString(4, searchValue+"%");
				pst.setString(5, searchValue+"%");
				pst.setString(6, searchValue+"%");
				pst.setString(7, searchValue+"%");
				pst.setString(8, searchValue+"%");
				pst.setString(9, searchValue+"%");
				pst.setString(10, locationId);
				pst.setString(11, accYear);
				pst.setString(12, className);
				pst.setString(13, section); 
				if(status=="" || status==null){
					pst.setString(14, "%%");
				}else{
					pst.setString(14, status);
				}
				//ln("GET_STUDENTS_SEARCH_BY_ALL_FILTER 14 -->>"+pst);
			}

			rs = pst.executeQuery();

			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();

				sno++;
				stuReg.setHouseName(rs.getString("housename"));
				stuReg.setStatus(rs.getString("student_status"));
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				list.add(stuReg);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Ending");

		return list;
	}

	public String validstudentaadharId(String aadharId, UserLoggingsPojo pojo, String stuId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in StudentRegistrationDaoImpl : validstudentaadharId Starting");

		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;

		int count = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			preparedStatement = conn.prepareStatement("SELECT COUNT(*) FROM campus_student st WHERE st.adharNo=? AND st.student_id_int!=?");   
			preparedStatement.setString(1, aadharId);
			preparedStatement.setString(2, stuId);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "true";
			} else {
				successMessage = "false";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null && (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : validstudentaadharId Ending");
		return successMessage;
	}

	public String validfatherPanNo(String fatherPanNo, UserLoggingsPojo pojo, String parentId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in StudentRegistrationDaoImpl : validstudentaadharId Starting");

		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;

		int count = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			preparedStatement = conn.prepareStatement("SELECT COUNT(*) FROM campus_parents WHERE fatherPanNo=? AND ParentID!=? ");   
			preparedStatement.setString(1, fatherPanNo);
			preparedStatement.setString(2, parentId);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "true";
			} else {
				successMessage = "false";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null && (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : validstudentaadharId Ending");
		return successMessage;
	}

	public String validmotherPanNo(String motherPanNo, UserLoggingsPojo pojo, String parentId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in StudentRegistrationDaoImpl : validmotherPanNo Starting");

		PreparedStatement preparedStatement = null;
		String successMessage = null;
		ResultSet rs = null;
		Connection conn = null;

		int count = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			preparedStatement = conn.prepareStatement("SELECT COUNT(*) FROM campus_parents WHERE motherPanNo=? AND ParentID!=? ");   
			preparedStatement.setString(1, motherPanNo);
			preparedStatement.setString(2, parentId);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				successMessage = "true";
			} else {
				successMessage = "false";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (preparedStatement != null && (!preparedStatement.isClosed())) {
					preparedStatement.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationDaoImpl : validmotherPanNo Ending");
		return successMessage;
	}

}
