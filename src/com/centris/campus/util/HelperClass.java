package com.centris.campus.util;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.BankBranchVO;
import com.centris.campus.vo.BankVO;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.HelperClassVo;
import com.centris.campus.vo.LibrarySearchIssueDetailsVO;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.SmsIntegrationApiVO;
import com.centris.campus.vo.TeacherVo;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.classVo;
import com.cerp.rest.util.DBConnection;

public class HelperClass implements Comparator<LibrarySearchIssueDetailsVO>{
	private static final Logger logger = Logger.getLogger(HelperClass.class);
	public static boolean con = true;

	private static Pattern pattern;
	private static Matcher matcher;

	public static Time getCurrentTime() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentTime Starting");
		Time t = null;
		try {
			t = new Time(new Date().getTime());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentTime Ending");
		return t;
	}
	public static java.sql.Date getCurrentSqlDate(String date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentSqlDate Starting");
		java.util.Date sd;
		java.sql.Date currdate = null;

		try {
			sd = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			currdate = new java.sql.Date(sd.getTime());

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentSqlDate Ending");
		return currdate;
	}



	public static java.sql.Date getCurrentSqlDate() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentSqlDate Starting");
		java.util.Date sd = new java.util.Date();
		java.sql.Date currdate = null;
		try {
			currdate = new java.sql.Date(sd.getTime());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentSqlDate Ending");
		return currdate;
	}

	public static Time getStringToTime(String getTime) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getStringToTime Starting");
		Time time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(getTime);
			time = new Time(d.getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getStringToTime Ending");
		return time;

	}

	public static java.sql.Date getSQLDate(java.util.Date date1) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSQLDate Starting");

		java.sql.Date sqldate = new java.sql.Date(date1.getTime());

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSQLDate Ending");
		return sqldate;
	}

	public static String getCurrentUserID(HttpServletRequest request) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentUserID Starting");

		String current_user = null;

		try {

			HttpSession ses = request.getSession(false);

			UserDetailVO userDetailsVo = (UserDetailVO)ses.getAttribute(MessageConstants.USER_DETAILS);
			current_user=userDetailsVo.getUserId();

			/*if (user.equalsIgnoreCase("Admin")) {
				lstmsUser Admin_user = (lstmsUser) ses
						.getAttribute(MessageConstants.USER);
				current_user = Admin_user.getId();

			} else if (user.equalsIgnoreCase("Teacher")) {
				LstmsTeachers teacher_user = (LstmsTeachers) ses
						.getAttribute(MessageConstants.USER);
				current_user = teacher_user.getTeacherId();
			} else if (user.equalsIgnoreCase("Principle")) {
				LstmsPrinciple teacher_user = (LstmsPrinciple) ses
						.getAttribute(MessageConstants.USER);
				current_user = teacher_user.getId();
			} else {
				LstmsParents parent_user = (LstmsParents) ses
						.getAttribute(MessageConstants.USER);
				current_user = parent_user.getParentId();
			}*/
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentUserID Ending");

		return current_user;
	}

	public static String getCurrentUser(HttpServletRequest request) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentUser Starting");
		String user = null;

		HttpSession ses = request.getSession(false);
		try {
			user = (String) ses.getAttribute("user");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentUser Ending");
		return user;
	}

	public static String getPrivigeValue(HttpServletRequest request) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getPrivigeValue Starting");

		String user = null;

		HttpSession ses = request.getSession(false);
		try {
			user = (String) ses.getAttribute("Priveliges");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getPrivigeValue Ending");
		return user;
	}

	public static Timestamp getCurrentTimestamp() {

		return new Timestamp(System.currentTimeMillis());

	}

	public static String getCurrentYearID(UserLoggingsPojo custdetails) throws SQLException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentYearID Starting");  

		CallableStatement proc = null;
		String accYear = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			proc = conn.prepareCall("{ call getCurrentAccYear() }");
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				accYear = rs.getString("ACCYEARID");
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentYearID Ending");
		return accYear;
	}

	public static String getCurrentAcadamicYear(UserLoggingsPojo custdetails) throws SQLException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentAcadamicYear Starting");
		CallableStatement proc = null;
		String accYear = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			proc = conn.prepareCall("{ call getCurrentAccYear() }");
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				accYear = rs.getString("acadamic_year");

			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentAcadamicYear Ending");
		return accYear;
	}

	public static java.sql.Date getSqlDateFromDdMmYyFormat(String date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSqlDateFromDdMmYyFormat Starting");
		java.util.Date sd;
		java.sql.Date currdate = null;

		try {
			sd = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			currdate = new java.sql.Date(sd.getTime());

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSqlDateFromDdMmYyFormat Ending");

		return currdate;
	}

	public static String getDateFromSQLDateinDDMMYYYYFormat(java.sql.Date date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getDateFromSQLDateinDDMMYYYYFormat Starting");
		String strDate = null;

		try {
			java.util.Date utildate = new Date(date.getTime());
			strDate = new SimpleDateFormat("dd-MM-yyyy").format(utildate);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getDateFromSQLDateinDDMMYYYYFormat Ending");
		return strDate;
	}

	public static String convertDatabaseToUI(String date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : convertDatabaseToUI Starting");
		String currdate = null;

		try {
			Date sd = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			currdate = new SimpleDateFormat("dd-MM-yyyy").format(sd);

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : convertDatabaseToUI Ending");

		return currdate;
	}

	public static String convertUIToDatabase(String date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : convertUIToDatabase Starting");
		String currdate = null;

		try {
			Date sd = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			currdate = new SimpleDateFormat("yyyy-MM-dd").format(sd);

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : convertUIToDatabase Ending");
		return currdate;
	}

	/* Written by Arul gor Getting PreviousYear */
	public static int getPreviousYear() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getPreviousYear Starting");
		Calendar prevYear = Calendar.getInstance();
		prevYear.add(Calendar.YEAR, -1);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getPreviousYear Ending");
		return prevYear.get(Calendar.YEAR);
	}

	/* Written by Arul gor Getting NextYear */
	public static int getNextYear() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getNextYear Starting");
		Calendar nextYear = Calendar.getInstance();
		nextYear.add(Calendar.YEAR, +1);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getNextYear Ending");
		return nextYear.get(Calendar.YEAR);
	}

	/* Written by Arul gor Getting CurrentMonthNo */
	public static int getCurrentMonthNo() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentMonthNo Starting");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentMonthNo Ending");
		return cal.get(Calendar.MONTH);
	}

	public static String getAcademicYear() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAcademicYear Starting");
		int currentyear = Calendar.getInstance().get(Calendar.YEAR);
		int nextYear = getNextYear();
		int prevYear = getPreviousYear();
		int currentMonth = getCurrentMonthNo();

		String accYear = null;
		if (currentMonth <= 6) {
			accYear = Integer.toString(prevYear) + "/"
					+ Integer.toString(currentyear);
		} else {
			accYear = Integer.toString(currentyear) + "/"
					+ Integer.toString(nextYear);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAcademicYear Ending");
		return accYear;
	}

	public static long getDateDifference(String startdate, String endDate) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getDateDifference Starting");
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		long dateDifference = 0l;
		try {
			Date sdate = sd.parse(startdate);
			long slong = sdate.getTime();
			Date edate = sd.parse(endDate);
			long elong = edate.getTime();
			dateDifference = elong - slong;
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getDateDifference Ending");

		return dateDifference;
	}
	public static String getMonthName(String month) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getMonthName Starting");

		Map<String, String> monthMap = new HashMap<String, String>();
		monthMap.put("01", "JAN");
		monthMap.put("02", "FEB");
		monthMap.put("03", "MAR");
		monthMap.put("04", "APR");
		monthMap.put("05", "MAY");
		monthMap.put("06", "JUN");
		monthMap.put("07", "JUL");
		monthMap.put("08", "AUG");
		monthMap.put("09", "SEP");
		monthMap.put("10", "OCT");
		monthMap.put("11", "NOV");
		monthMap.put("12", "DEC");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getMonthName Ending");
		return monthMap.get(month.length() < 2 ? "0" + month : month);
	}

	public static String getMonthFullName(String month) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getMonthFullName Starting");
		Map<String, String> monthMap = new HashMap<String, String>();
		monthMap.put("01", "January");
		monthMap.put("02", "February");
		monthMap.put("03", "March");
		monthMap.put("04", "April");
		monthMap.put("05", "May");
		monthMap.put("06", "June");
		monthMap.put("07", "July");
		monthMap.put("08", "August");
		monthMap.put("09", "September");
		monthMap.put("10", "October");
		monthMap.put("11", "November");
		monthMap.put("12", "December");
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getMonthFullName Ending");
		return monthMap.get(month.length() < 2 ? "0" + month : month);
	}

	public static int getCurrentYear() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentYear Starting");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentYear Ending");
		return cal.get(Calendar.YEAR);
	}

	public static int getMonthDifference(String startDate, String endDate) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getMonthDifference Starting");

		int monthDiff = 0;

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(format.parse(startDate));
			Calendar c2 = Calendar.getInstance();
			c2.setTime(format.parse(endDate));
			if (c2.get(Calendar.YEAR) > c1.get(Calendar.YEAR)) {
				int temp = 11 - c1.get(Calendar.MONTH);
				monthDiff = temp + c2.get(Calendar.MONTH) + 1;
			} else if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
				monthDiff = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
			}

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getMonthDifference Ending");
		return monthDiff;
	}

	public static java.sql.Date getSqlDateFromYyMmDdFormat(String date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSqlDateFromYyMmDdFormat Starting");

		java.util.Date sd;
		java.sql.Date currdate = null;

		try {
			sd = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			currdate = new java.sql.Date(sd.getTime());

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSqlDateFromYyMmDdFormat Ending");

		return currdate;
	}

	public static String convertDatabaseToUIWithDateTime(String date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : convertDatabaseToUIWithDateTime Starting");

		String currdate = null;

		try {
			Date sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
			currdate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(sd);

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : convertDatabaseToUIWithDateTime Ending");

		return currdate;
	}

	public static String convertUItoDatabaseWithDateTime(String date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : convertUItoDatabaseWithDateTime Starting");

		String currdate = null;

		try {
			Date sd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
			currdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sd);

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : convertUItoDatabaseWithDateTime Ending");

		return currdate;
	}

	public static java.sql.Date getTwentythDayDate() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getTwentythDayDate Starting");
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sqlDate);
			cal.add(Calendar.DATE, -20);
			sqlDate = new java.sql.Date(cal.getTimeInMillis());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getTwentythDayDate Ending");
		return sqlDate;
	}

	public static int getDaysByMonthAndYear(int month, int year) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getDaysByMonthAndYear Starting");
		int days = 0;
		try {
			Calendar c1 = Calendar.getInstance();
			c1.clear();
			c1.set(year, (month - 1), 1);
			days = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getDaysByMonthAndYear Ending");
		return days;
	}

	public static List<String> getDateListBetweenDates(String fromDate,
			String toDate) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getDateListBetweenDates Starting");

		List<String> dateList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date fDate = sdf.parse(fromDate);
			Date tDate = sdf.parse(toDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(fDate);
			cal.add(Calendar.DATE, -1);
			while (cal.getTime().before(tDate)) {
				cal.add(Calendar.DATE, 1);
				if (sdf.format(cal.getTime()) != null)
					dateList.add(sdf.format(cal.getTime()));
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getDateListBetweenDates Ending");
		return dateList;
	}

	public static ArrayList<String> getNextAccYearDetails(String curentyear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getNextAccYearDetails Starting");

		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_NEXT_ACCADAMIC_YEAR);

			String num = curentyear.substring(3);

			int temper = Integer.parseInt(num) + 1;

			pstmt.setString(1, "ACY" + temper);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				list.add(rs.getString("acadamic_id"));
				list.add(rs.getString("acadamic_year"));

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
			catch(SQLException sql){
				sql.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getNextAccYearDetails Ending");

		return list;
	}

	public static List<HelperClassVo> getAllAcademicYear(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllAcademicYear Starting");

		CallableStatement proc = null;
		List<HelperClassVo> accYear = new ArrayList<HelperClassVo>();
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			proc = conn.prepareCall("{ call getAllAccYear() }");
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				HelperClassVo vo = new HelperClassVo();
				vo.setAccId(rs.getString("ACCYEARID"));
				vo.setAccName(rs.getString("acadamic_year"));
				accYear.add(vo);
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllAcademicYear Ending");
		return accYear;
	}

	public static String getCurrentYearIDSelected(HttpServletRequest request)
			throws SQLException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentYearIDSelected Starting");
		String accYear = null;
		try {
			HttpSession session = request.getSession(false);
			accYear = (String) session.getAttribute("accYear");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCurrentYearIDSelected Ending");
		return accYear;
	}

	public static String genaratePasswordForTeacher(TeacherRegistrationPojo teaPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : genaratePasswordForTeacher Starting");
		String pswd = null;
		String[] sp = { "@", "#", "%", "&", "*", "_" };
		Random rand = new Random();

		String pswd1 = teaPojo.getTfastname().toString().substring(teaPojo.getTfastname().length() - 3,	teaPojo.getTfastname().length()).toLowerCase();
		String pswd2 = teaPojo.getTeachermobno().toString().substring(1, 3);

		String pswd4 = sp[rand.nextInt((5 - 1) + 1) + 1];
		/*String pswd5 = teaPojo.getTeacheremail().toString().substring(1, 3)
				.toUpperCase();*/

		pswd = pswd1 + pswd2 + pswd4 ;
		pswd = pswd.replace(" ", "").trim();

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : genaratePasswordForTeacher Ending");
		return pswd;
	}
	//	public static String generatePasswordForStaff(
	//			List<UploadStaffXlsPOJO> successlist) {
	//
	//		String pswd = null;
	//		String[] sp = { "@", "#", "%", "&", "*", "_" };
	//		Random rand = new Random();
	//
	//		String pswd1 = ((UploadStaffXlsPOJO) successlist).getFirstname().toString().substring(successlist.getFirstname().length() - 3,	successlist.getFirstname().length()).toLowerCase();
	//		String pswd2 = successlist.getMobileno().toString().substring(1, 3);
	//
	//		String pswd4 = sp[rand.nextInt((5 - 1) + 1) + 1];
	//		String pswd5 = successlist.getEmail().toString().substring(1, 3)
	//				.toUpperCase();
	//
	//		pswd = pswd1 + pswd2 + pswd4 + pswd5;
	//		pswd = pswd.replace(" ", "").trim();
	//		return pswd;
	//	}



	public static String getLastThirtyDateFromNow(String date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getLastThirtyDateFromNow Starting");
		String lastThirdDate = "";
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {

			Calendar now = Calendar.getInstance();
			Date fDate = sd.parse(date);
			now.setTime(fDate);
			now.add(Calendar.DATE, -30);
			lastThirdDate = sd.format(now.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getLastThirtyDateFromNow Ending");
		return lastThirdDate;
	}

	public static String getWeekDay(String date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getWeekDay Starting");

		String finalDay=null;

		try {
			SimpleDateFormat format1=new SimpleDateFormat("dd-MM-yyyy");
			Date dt1;
			dt1 = format1.parse(date);
			DateFormat format2=new SimpleDateFormat("EEEE"); 
			finalDay=format2.format(dt1);

			System.out.println("finalDay :: "+finalDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getWeekDay Ending");
		return finalDay;
	}

	public static String addTimeArray(String[] getTime) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : addTimeArray Starting");
		String time = null;
		long cal_time = 0l;
		TimeZone UTC = TimeZone.getTimeZone("UTC");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(UTC);
		Date d = null;
		try {
			for (int i = 0; i < getTime.length; i++) {
				d = sdf.parse(getTime[i]);
				cal_time = cal_time + d.getTime();
			}
			long seconds = (cal_time / 1000);
			long lhours = seconds / 3600;
			long min = seconds % 3600;
			long lmin1 = min / 60;
			long lsec = min % 60;

			String hours = (lhours + "").length() > 1 ? (lhours + "") : "0"
					+ lhours;
			String min1 = (lmin1 + "").length() > 1 ? (lmin1 + "") : "0"
					+ lmin1;
			String sec = (lsec + "").length() > 1 ? (lsec + "") : "0" + lsec;
			time = hours + ":" + min1 + ":" + sec;

		} catch (ParseException e) {

			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : addTimeArray Ending");

		return time;

	}
	public static String className(String name){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : className Starting");

		String className = null;
		switch(name){
		case "1": className="Pre-KG";break;
		case "2": className="LKG";break;
		case "3": className="UKG";break;
		case "4": className="I";break;
		case "5": className="II";break;
		case "6": className="III";break;
		case "7": className="IV";break;
		case "8": className="V";break;
		case "9": className="VI";break;
		case "10": className="VII";break;
		case "11": className="VIII";break;
		case "12": className="IX";break;
		case "13": className="X";break;
		case "14": className="XI";break;
		case "15": className="XII";break;
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : className Ending");
		return className;
	}
	public static int getPastDateofAcademicYear(HttpServletRequest request,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getPastDateofAcademicYear Starting");
		java.util.Date sd = new java.util.Date();
		java.sql.Date currdate = null;
		PreparedStatement pstmt=null;

		int pastDate =0;
		ResultSet rs = null;
		Connection conn = null;
		HttpSession ses = request.getSession(false);
		try {
			String academic_year=(String) ses.getAttribute("current_academicYear");

			currdate = new java.sql.Date(sd.getTime());

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt=conn.prepareStatement("SELECT acadamic_id ACCYEARID,startDate,endDate,acadamic_year FROM campus_acadamicyear WHERE acadamic_id=?");
			pstmt.setString(1, academic_year);


			rs = pstmt.executeQuery();

			while (rs.next()) {

				String startDate=rs.getString("startDate");
				List<String> dateList = new ArrayList<String>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				Date fDate = sdf.parse(startDate);
				Date tDate = sdf.parse(currdate.toString());
				Calendar cal = Calendar.getInstance();
				cal.setTime(fDate);
				cal.add(Calendar.DATE, -1);
				while (cal.getTime().before(tDate)) {
					cal.add(Calendar.DATE, 1);
					if (sdf.format(cal.getTime()) != null)
						dateList.add(sdf.format(cal.getTime()));
				}
				pastDate=dateList.size();
				pastDate=-pastDate;

				System.out.println("pastDate"+pastDate);
				if(dateList.size()==0){
					fDate = sdf.parse(currdate.toString());
					tDate = sdf.parse(startDate);
					cal = Calendar.getInstance();
					cal.setTime(fDate);
					cal.add(Calendar.DATE, -1);
					while (cal.getTime().before(tDate)) {
						cal.add(Calendar.DATE, 1);
						if (sdf.format(cal.getTime()) != null)
							dateList.add(sdf.format(cal.getTime()));
					}
					pastDate=dateList.size()-2;
				}

			}




		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getPastDateofAcademicYear Ending");
		return pastDate;
	}

	public static List<HelperClassVo> getAllAcademicYearStartDate(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllAcademicYearStartDate Starting");
		CallableStatement proc = null;
		List<HelperClassVo> startDate = new ArrayList<HelperClassVo>();
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			proc = conn.prepareCall("{ CALL getCurrentAccYearStartDateAndEndDate() }");
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				HelperClassVo vo = new HelperClassVo();
				vo.setStartDate(rs.getString("startDate"));
				startDate.add(vo);
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllAcademicYearStartDate Ending");
		return startDate;
	}

	public static List<HelperClassVo> getAllAcademicYearEndDate(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllAcademicYearEndDate Starting");

		CallableStatement proc = null;
		List<HelperClassVo> endDate = new ArrayList<HelperClassVo>();
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			proc = conn.prepareCall("{ CALL getCurrentAccYearStartDateAndEndDate() }");
			proc.execute();
			rs = proc.getResultSet();


			while (rs.next()) {
				HelperClassVo vo = new HelperClassVo();
				vo.setEndDate(rs.getString("endDate"));
				endDate.add(vo);
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllAcademicYearEndDate Ending");
		return endDate;
	}

	public static int getForDateofAcademicYear(HttpServletRequest request, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getForDateofAcademicYear Starting");

		java.util.Date sd = new java.util.Date();
		java.sql.Date currdate = null;

		PreparedStatement pstmt=null;

		int forDate =0;
		ResultSet rs = null;
		Connection conn = null;

		HttpSession ses = request.getSession(false);
		try {
			String academic_year=(String) ses.getAttribute("current_academicYear");

			currdate = new java.sql.Date(sd.getTime());

			conn = JDBCConnection.getSeparateConnection( userLoggingsVo);
			pstmt=conn.prepareStatement("SELECT acadamic_id ACCYEARID,startDate,endDate,acadamic_year FROM campus_acadamicyear WHERE acadamic_id=?");
			pstmt.setString(1, academic_year);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				String endDate=rs.getString("endDate");
				List<String> dateList = new ArrayList<String>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				Date fDate = sdf.parse(currdate.toString());
				Date tDate = sdf.parse(endDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(fDate);
				cal.add(Calendar.DATE, -1);
				while (cal.getTime().before(tDate)) {
					cal.add(Calendar.DATE, 1);
					if (sdf.format(cal.getTime()) != null)
						dateList.add(sdf.format(cal.getTime()));
				}
				forDate=dateList.size();	
				if(forDate==0){
					fDate = sdf.parse(endDate);
					tDate = sdf.parse(currdate.toString());
					cal = Calendar.getInstance();
					cal.setTime(fDate);
					cal.add(Calendar.DATE, -1);
					while (cal.getTime().before(tDate)) {
						cal.add(Calendar.DATE, 1);
						if (sdf.format(cal.getTime()) != null)
							dateList.add(sdf.format(cal.getTime()));
					}

					forDate=dateList.size();
					forDate=-forDate+2;
				}
			}




		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getForDateofAcademicYear Ending");
		return forDate;
	}
	public static int getForDateofAcademicYear(String academic_year,UserLoggingsPojo dbinfo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getForDateofAcademicYear Starting");
		java.util.Date sd = new java.util.Date();
		java.sql.Date currdate = null;

		PreparedStatement pstmt=null;

		int forDate =0;
		ResultSet rs = null;
		Connection conn = null;

		try {

			currdate = new java.sql.Date(sd.getTime());

			conn = JDBCConnection.getSeparateConnection(dbinfo);
			pstmt=conn.prepareStatement("SELECT acadamic_id ACCYEARID,startDate,endDate,acadamic_year FROM campus_acadamicyear WHERE acadamic_id=?");
			pstmt.setString(1, academic_year);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				String endDate=rs.getString("endDate");
				List<String> dateList = new ArrayList<String>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				Date fDate = sdf.parse(currdate.toString());
				Date tDate = sdf.parse(endDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(fDate);
				cal.add(Calendar.DATE, -1);
				while (cal.getTime().before(tDate)) {
					cal.add(Calendar.DATE, 1);
					if (sdf.format(cal.getTime()) != null)
						dateList.add(sdf.format(cal.getTime()));
				}
				forDate=dateList.size();	
				if(forDate==0){
					fDate = sdf.parse(endDate);
					tDate = sdf.parse(currdate.toString());
					cal = Calendar.getInstance();
					cal.setTime(fDate);
					cal.add(Calendar.DATE, -1);
					while (cal.getTime().before(tDate)) {
						cal.add(Calendar.DATE, 1);
						if (sdf.format(cal.getTime()) != null)
							dateList.add(sdf.format(cal.getTime()));
					}

					forDate=dateList.size();
					forDate=-forDate+2;
				}
			}




		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getForDateofAcademicYear Ending");
		return forDate;
	}
	public static int getPastDateofAcademicYear(String academic_year,UserLoggingsPojo dbinfo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getPastDateofAcademicYear Starting");

		java.util.Date sd = new java.util.Date();
		java.sql.Date currdate = null;

		PreparedStatement pstmt=null;
		int pastDate =0;
		ResultSet rs = null;
		Connection conn = null;
		try {

			currdate = new java.sql.Date(sd.getTime());

			conn = JDBCConnection.getSeparateConnection(dbinfo);

			pstmt=conn.prepareStatement("SELECT acadamic_id ACCYEARID,startDate,endDate,acadamic_year FROM campus_acadamicyear WHERE acadamic_id=?");
			pstmt.setString(1, academic_year);


			rs = pstmt.executeQuery();

			while (rs.next()) {

				String startDate=rs.getString("startDate");
				List<String> dateList = new ArrayList<String>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				Date fDate = sdf.parse(startDate);
				Date tDate = sdf.parse(currdate.toString());
				Calendar cal = Calendar.getInstance();
				cal.setTime(fDate);
				cal.add(Calendar.DATE, -1);
				while (cal.getTime().before(tDate)) {
					cal.add(Calendar.DATE, 1);
					if (sdf.format(cal.getTime()) != null)
						dateList.add(sdf.format(cal.getTime()));
				}
				pastDate=dateList.size();
				pastDate=-pastDate;

				System.out.println("pastDate"+pastDate);
				if(dateList.size()==0){
					fDate = sdf.parse(currdate.toString());
					tDate = sdf.parse(startDate);
					cal = Calendar.getInstance();
					cal.setTime(fDate);
					cal.add(Calendar.DATE, -1);
					while (cal.getTime().before(tDate)) {
						cal.add(Calendar.DATE, 1);
						if (sdf.format(cal.getTime()) != null)
							dateList.add(sdf.format(cal.getTime()));
					}
					pastDate=dateList.size()-2;
				}

			}




		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getPastDateofAcademicYear Ending");
		return pastDate;
	}


	public static String getAcademicYearFace(String academic_year,UserLoggingsPojo dbinfo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAcademicYearFace Starting");

		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		String academicYearFace=null;
		try {
			conn = JDBCConnection.getSeparateConnection(dbinfo);
			pstmt=conn.prepareStatement("SELECT acadamic_year FROM campus_acadamicyear WHERE acadamic_id=?");
			pstmt.setString(1, academic_year);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				academicYearFace=rs.getString("acadamic_year");
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAcademicYearFace Ending");
		return academicYearFace;
	}

	public static int totalDaysBetweenTwoDates(String startdate,String enddate) throws ParseException{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : totalDaysBetweenTwoDates Starting");

		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		Date date = sdf.parse(startdate);
		cal1.setTime(date);
		date = sdf.parse(enddate);
		cal2.setTime(date);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : totalDaysBetweenTwoDates Ending");
		return daysBetween(cal1.getTime(),cal2.getTime());
	}

	public static int daysBetween(Date d1, Date d2){

		return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));

	}



	public static List<HelperClassVo> getAllLocation(UserLoggingsPojo custid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllLocation Starting");

		CallableStatement proc = null;                                                                                               
		List<HelperClassVo> location = new ArrayList<HelperClassVo>();
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(custid);
			proc = conn.prepareCall("{ call getLocationList() }");
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				HelperClassVo vo = new HelperClassVo();
				vo.setLocationId(rs.getString("Location_Id"));
				vo.setLocationName(rs.getString("Location_Name"));
				location.add(vo);
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllLocation Ending");
		return location;
	}

	public static List<HelperClassVo> getHouse(String locationId, String academicYear,UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getHouse Starting");
		CallableStatement proc = null;                                                                                               
		List<HelperClassVo> house = new ArrayList<HelperClassVo>();
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			proc = conn.prepareCall("{ call getHouse(?,?) }");
			proc.setString(1, locationId);
			proc.setString(2, academicYear);
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				HelperClassVo vo = new HelperClassVo();
				vo.setHouseId(rs.getString("house_id"));
				vo.setHouseName(rs.getString("housename"));
				house.add(vo);
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getHouse Ending");
		return house;
	}
	public static String[] removeElements(String[] input, String deleteMe) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : removeElements Starting");

		if (input != null) {
			List<String> list = new ArrayList<String>(Arrays.asList(input));
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(deleteMe)) {
					list.remove(i);
				}
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in HelperClass : removeElements Ending");
			return list.toArray(new String[0]);
		} else {
			return new String[0];
		}

	}


	public static String getMothNumberByShortName(String stmonth,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getMothNumberByShortName Starting");
		String monthname = null;
		if(stmonth.toLowerCase().startsWith("jan")){
			monthname = "01";
		}
		else if(stmonth.toLowerCase().startsWith("feb")){
			monthname ="02";
		}
		else if(stmonth.toLowerCase().startsWith("mar")){
			monthname = "03";
		}else if(stmonth.toLowerCase().startsWith("apr")){
			monthname = "04";
		}else if(stmonth.toLowerCase().startsWith("may")){
			monthname = "05";
		}else if(stmonth.toLowerCase().startsWith("jun")){
			monthname = "06";
		}
		else if(stmonth.toLowerCase().startsWith("jul")){
			monthname = "07";
		}
		else if(stmonth.toLowerCase().startsWith("aug")){
			monthname = "08";
		}
		else if(stmonth.toLowerCase().startsWith("sep")){
			monthname ="09";
		}
		else if(stmonth.toLowerCase().startsWith("oct")){
			monthname = "10";
		}
		else if(stmonth.toLowerCase().startsWith("nov")){
			monthname = "11";
		}
		else if(stmonth.toLowerCase().startsWith("dec")){
			monthname = "12";
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getMothNumberByShortName Ending");
		return monthname;
	}

	public static String getSchoolName(String locationId,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSchoolName Starting");

		CallableStatement proc = null; 
		ResultSet rs = null;
		Connection conn = null;
		String schoolName=null;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);

			proc=conn.prepareCall("{ call getSchoolName(?) }");
			proc.setString(1, locationId);


			rs = proc.executeQuery();

			while (rs.next()) {

				schoolName=rs.getString("school_name");
			}




		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSchoolName Ending");
		return schoolName;
	}

	public static String convertExceltoUIformat(String ddDate) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : convertExceltoUIformat Starting");
		String dateformat = null;
		if(ddDate.split("/").length>1) {
			dateformat = ddDate.split("/")[1]+"-"+ddDate.split("/")[0]+"-"+ddDate.split("/")[2];
		}
		else if(ddDate.split("-").length>1){
			dateformat = ddDate.split("-")[1]+"-"+ddDate.split("-")[0]+"-"+ddDate.split("-")[2];
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : convertExceltoUIformat Ending");
		return dateformat;
	}

	public static String validateDate(String date) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : validateDate Starting");
		String flag="false";
		if(date.split("-").length>1) {
			if(Integer.parseInt(date.split("-")[0])<=31 && Integer.parseInt(date.split("-")[1]) <=12){
				flag="true";
			}
		}
		else if(date.split("/").length>1) {
			if(Integer.parseInt(date.split("/")[0])<=31 && Integer.parseInt(date.split("/")[1]) <=12){
		flag="true";
			}
		}
		else {
			flag="false";
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : validateDate Ending");
		return flag;
	}

	@Override
	public int compare(LibrarySearchIssueDetailsVO o1, LibrarySearchIssueDetailsVO o2) {


		return o1.getSubname().compareTo(o2.getSubname());
	}

	public static List<TeacherVo> getAllTeacherList(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllTeacherList Starting");

		CallableStatement proc = null; 
		ResultSet rs = null;
		Connection conn = null;
		List<TeacherVo> teacherList=new ArrayList<TeacherVo>();
		try {


			conn = JDBCConnection.getSeparateConnection(custdetails);

			proc=conn.prepareCall("{ call getAllTeacher() }");
			System.out.println("STORED PROCEDURE"+proc);	
			rs = proc.executeQuery();

			while (rs.next()) {
				TeacherVo vo=new TeacherVo();
				vo.setTeacherId(rs.getString("TeacherID"));				
				vo.setTeacherName(rs.getString("FirstName")+" "+rs.getString("LastName"));
				vo.setAbbrvation(rs.getString("Abbreviative_Id"));
				teacherList.add(vo);

			}




		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllTeacherList Ending");
		return teacherList;
	}
	
	
	public static List<TeacherVo> getAllTeacherListForTimetable(String subid, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllTeacherList Starting");

		CallableStatement proc = null; 
		ResultSet rs = null;
		Connection conn = null;
		List<TeacherVo> teacherList=new ArrayList<TeacherVo>();
		try {


			conn = JDBCConnection.getSeparateConnection(custdetails);

			proc=conn.prepareCall("{ call getAllTeacherForTimeTable(?) }");
			proc.setString(1, subid);
			System.out.println("STORED PROCEDURE"+proc);	
			rs = proc.executeQuery();

			while (rs.next()) {
				TeacherVo vo=new TeacherVo();
				vo.setTeacherId(rs.getString("TeacherID"));				
				vo.setTeacherName(rs.getString("FirstName")+" "+rs.getString("LastName"));
				vo.setAbbrvation(rs.getString("Abbreviative_Id"));
				teacherList.add(vo);

			}




		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllTeacherList Ending");
		return teacherList;
	}
	public static List<TeacherVo> getTodayTeacherList(String object,String timetableId,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllTeacherList Starting");

		CallableStatement proc = null; 
		ResultSet rs = null;
		Connection conn = null;
		List<TeacherVo> teacherList=new ArrayList<TeacherVo>();
		try {


			conn = JDBCConnection.getSeparateConnection(custdetails);

			proc=conn.prepareCall("{ call getTodayTeacher(?,?) }");
			proc.setString(1, object);
			proc.setString(2, timetableId);
			rs = proc.executeQuery();

			while (rs.next()) {
				TeacherVo vo=new TeacherVo();
				vo.setTeacherId(rs.getString("TeacherID"));				
				vo.setTeacherName(rs.getString("FirstName")+" "+rs.getString("LastName"));
				vo.setAbbrvation(rs.getString("Abbreviative_Id"));
				teacherList.add(vo);

			}




		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllTeacherList Ending");
		return teacherList;
	}

	public static Comparator<TimeTableVo> TimeTableVoComparator = new Comparator<TimeTableVo>() {

		public int compare(TimeTableVo s1, TimeTableVo s2) {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in HelperClass : compare Starting");
			String Dayname1 = s1.getDayid().toUpperCase();
			String Dayname12 = s2.getDayid().toUpperCase();

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in HelperClass : compare Ending");
			//ascending order
			return Dayname1.compareTo(Dayname12);

			//descending order
			//return StudentName2.compareTo(StudentName1);
		}
	};



	/*
		Record the log_activity details
		Written by Asha Mestha (01-02-2018)
	 */

	public static void recordLog_Activity(String sessid,String mod_name,String submodule,String activity,String activity_sql,UserLoggingsPojo userLoggingsVo){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : recordLog_Activity Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;

		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.RECORD_LOG_ACTIVITY);
			pstmt.setString(1,sessid);
			pstmt.setString(2,mod_name);
			pstmt.setString(3,submodule);
			pstmt.setString(4,activity);
			pstmt.setString(5,activity_sql.substring(activity_sql.indexOf(":")+1,activity_sql.length()));

			pstmt.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  HelperClass : recordLog_Activity Ending");
	}

	


	/*public static void recordLog_Activity(String sessid,String mod_name,String submodule,String activity,String activity_sql){

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in HelperClass : recordLog_Activity Starting");
	Connection conn = null;
	PreparedStatement pstmt = null;

	try{

		conn = JDBCConnection.getSeparateConnection();
		pstmt = conn.prepareStatement(SQLUtilConstants.RECORD_LOG_ACTIVITY);
		pstmt.setString(1,sessid);
		pstmt.setString(2,mod_name);
		pstmt.setString(3,submodule);
		pstmt.setString(4,activity);
		pstmt.setString(5,activity_sql.substring(activity_sql.indexOf(":")+1,activity_sql.length()));

		pstmt.executeUpdate();

	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			if(pstmt!=null && !pstmt.isClosed()){
				pstmt.close();
			}
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in  HelperClass : recordLog_Activity Ending");
}*/


	public static String classNameExcelUpload(String name){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : classNameExcelUpload Starting");
		String className=null;
		switch(name){
		case "Pre-KG": className="1";break;
		case "LKG": className="2";break;
		case "UKG": className="3";break;
		case "I": className="4";break;
		case "II": className="5";break;
		case "III": className="6";break;
		case "IV": className="7";break;
		case "V": className="8";break;
		case "VI": className="9";break;
		case "VII": className="10";break;
		case "VIII": className="11";break;
		case "IX": className="12";break;
		case "X": className="13";break;
		case "XI": className="14";break;
		case "XII": className="15";break;

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : classNameExcelUpload Ending");
		return className;	
	}


	public static String validateDateholiday(String date) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : validateDateholiday Starting");
		String flag="false";
		if(date.split("-").length==3){
			boolean valid=true;
			for(int i=0;i<date.split("-").length;i++){
				valid=StringUtils.isNumeric(date.split("-")[i]);
				if(!valid){
					break;
				}
			}
			if(valid){

				if(Integer.parseInt(date.split("-")[0])<=31 && Integer.parseInt(date.split("-")[1]) <=12){
					flag="true";
				}
			}
		}
		else if(date.split("/").length==3){
			boolean valid=true;
			for(int i=0;i<date.split("/").length;i++){
				valid=StringUtils.isNumeric(date.split("/")[i]);
				if(!valid){
					break;
				}
			}
			if(valid){
				if(Integer.parseInt(date.split("/")[0])<=31 && Integer.parseInt(date.split("/")[1]) <=12){
					flag="true";
				}	
			}

		}
		else{
			flag="false";
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : validateDateholiday Ending");
		return flag;
	}


	public static String convertUIToDatabaseSettingReports(String date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : convertUIToDatabaseSettingReports Starting");
		String currdate = null;

		try {
			if(date.contains("-")){
				System.out.println("it contais - ");
				Date sd = new SimpleDateFormat("dd-MM-yyyy").parse(date);
				currdate = new SimpleDateFormat("yyyy-MM-dd").format(sd);

			}else{
				System.out.println("it contais /");
				Date sd = new SimpleDateFormat("dd/MM/yyyy").parse(date);
				currdate = new SimpleDateFormat("yyyy-MM-dd").format(sd);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : convertUIToDatabaseSettingReports Ending");
		return currdate;
	}

	public static String getWeekDaySettingReports(String date) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getWeekDaySettingReports Starting");
		String finalDay=null;
		try {
			if(date.contains("-")){
				SimpleDateFormat format1=new SimpleDateFormat("dd-MM-yyyy");
				Date dt1;
				dt1 = format1.parse(date);
				DateFormat format2=new SimpleDateFormat("EEEE"); 
				finalDay=format2.format(dt1);

				System.out.println("finalDay :: "+finalDay);
			}
			else{
				SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
				Date dt1;
				dt1 = format1.parse(date);
				DateFormat format2=new SimpleDateFormat("EEEE"); 
				finalDay=format2.format(dt1);
				System.out.println("finalDay :: "+finalDay);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getWeekDaySettingReports Ending");
		return finalDay;
	}
	public static String getvaliddateforExcel(String dateofJoin) throws ParseException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getvaliddateforExcel Starting");

		String lvarStrDateOfTransaction = null;
		DateFormat formatter = null;
		Date lvarObjDateOfTransaction = null;

		String result = "valid";

		if (dateofJoin.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
			formatter = new SimpleDateFormat("dd/MM/yyyy");
		} else if (dateofJoin.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
			formatter = new SimpleDateFormat("dd-MM-yyyy");
		} else if (dateofJoin.matches("([0-9]{4})([0-9]{2})([0-9]{2})")) {
			formatter = new SimpleDateFormat("yyyyMMdd");
		} else if (dateofJoin.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		} else if (dateofJoin.matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
			formatter = new SimpleDateFormat("yyyy/MM/dd");
		}else{
			result = "invalid";
		}

		lvarObjDateOfTransaction = formatter.parse(lvarStrDateOfTransaction);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getvaliddateforExcel Ending");
		return result+"-"+lvarObjDateOfTransaction.toString();
	}

	public static String getexceldateformat(String dateofJoin){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getexceldateformat Starting");

		String newDateString = null;
		try{
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
			Date startDate;
			startDate = df.parse(dateofJoin);
			newDateString = df.format(startDate); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getexceldateformat Ending");
		return newDateString;

	}

	public static Boolean DateValidator(String date){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : DateValidator Starting");

		
		String DATE_PATTERN = 
		          "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

		pattern = Pattern.compile(DATE_PATTERN);
		
		 matcher = pattern.matcher(date);
		 if(matcher.matches()){
			 matcher.reset();
					  
			 if(matcher.find()){
		             String day = matcher.group(1);
			     String month = matcher.group(2);
			     int year = Integer.parseInt(matcher.group(3));
						 
			     if (day.equals("31") && 
				  (month.equals("4") || month .equals("6") || month.equals("9") ||
		                  month.equals("11") || month.equals("04") || month .equals("06") ||
		                  month.equals("09"))) {
					return false; // only 1,3,5,7,8,10,12 has 31 days
			     } else if (month.equals("2") || month.equals("02")) {
		                  //leap year
				  if(year % 4==0){
					  if(day.equals("30") || day.equals("31")){
						  return false;
					  }else{
						  return true;
					  }
				  }else{
				         if(day.equals("29")||day.equals("30")||day.equals("31")){
						  return false;
				         }else{
						  return true;
					  }
				  }
			      }else{				 
				return true;				 
			      }
			   }else{
		    	      return false;
			   }		  
		     }else{
			  return false;
		     }	
		 
		   }

	public static LocationVO getCustSchoolInfo(UserLoggingsPojo userLoggingsVo,String locId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCustSchoolInfo Starting");

		CallableStatement proc = null; 
		ResultSet rs = null;
		Connection conn = null;
		LocationVO vo=new LocationVO();
		try {


			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			proc=conn.prepareCall("{ call getCustSchoolInfo(?) }");
			proc.setString(1, locId);
			System.out.println("2222222222222222"+proc);
			rs = proc.executeQuery();
			//System.out.println("getCustSchoolInfo -->>"+proc);
			while (rs.next()) {

				vo.setLocation_id(rs.getString("Location_Id"));
				vo.setLocationname(rs.getString("Location_Name"));
				vo.setEmailId(rs.getString("emailId"));
				vo.setWebsite(rs.getString("website"));
				vo.setBoard(rs.getString("board"));
				vo.setAffilno(rs.getString("affilation"));
				vo.setSchoolcode(rs.getString("schoolcode"));
				vo.setSchoollogo(rs.getString("school_logo"));
				vo.setBoardlogo(rs.getString("boardlogo"));
				
				vo.setAddress(rs.getString("address_line1"));
		        String addres1=rs.getString("address_line1");
				/*vo.setAddress(rs.getString("address_line1"));*/

				String addres2 = rs.getString("address_line2");
				if(!addres2.equalsIgnoreCase("-")){
					vo.setAddress(addres1+"\n"+addres2+"\n"+rs.getString("inform"));
					vo.setAddress2(addres2+"\n"+rs.getString("inform"));
					vo.setLocAddId(addres1+"\n"+addres2+"\n"+rs.getString("locadd"));
				}else{
					vo.setAddress(addres1+"\n"+rs.getString("inform"));
					vo.setAddress2(rs.getString("inform"));
					vo.setLocAddId(addres1+"\n"+rs.getString("locadd"));
				}
				vo.setTelphonno(rs.getString("tel_phone"));
				vo.setMobno(rs.getString("mobile_no"));
				vo.setInform(rs.getString("inform"));
				vo.setSchname(rs.getString("school_name"));
				vo.setCountryName(rs.getString("country"));
				vo.setBranchAddress(rs.getString("inform"));
				vo.setAddress3(rs.getString("address_line1"));
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getCustSchoolInfo Ending");
	return vo;
}




	public static List<HelperClassVo> getAllClassName(UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllClassName Starting");

		CallableStatement proc = null;                                                                                               
		List<HelperClassVo> className = new ArrayList<HelperClassVo>();
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			proc = conn.prepareCall("{ call getAllClassName() }");
			System.out.println("###########################################"+proc);
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				HelperClassVo vo = new HelperClassVo();
				vo.setClassName(rs.getString("classdetails_name_var"));
				className.add(vo);
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAllClassName Ending");
		return className;
	}
	public static SmsIntegrationApiVO getSmsApiInfo(){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSmsApiInfo Starting");
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		SmsIntegrationApiVO vo= new SmsIntegrationApiVO();
		try{
			conn = DBConnection.getmasterConnection();
			pstmt=conn.prepareStatement("SELECT `sms_url`,`apikey`,`sender_id`,`service_name` FROM `campus_smsapi_details` WHERE isActive='Y'");
			rs=pstmt.executeQuery();
			while(rs.next()){
				vo.setSmsURL(rs.getString("sms_url"));
				vo.setApiKey(rs.getString("apikey"));
				vo.setSenderId(rs.getString("sender_id"));
				vo.setServiceName(rs.getString("service_name"));

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
			catch(SQLException sql){
				sql.printStackTrace();

			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSmsApiInfo Ending");

		return vo;

	}
	public static String classNameByWords(String name){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : className Starting");

		String className = null;
		switch(name){
		case "Pre-KG": className="Pre-KG";break;
		case "LKG": className="Lower KG";break;
		case "UKG": className="Upper KG";break;
		case "I": className="First Standard";break;
		case "II": className="Second Standard";break;
		case "III": className="Third Standard";break;
		case "IV": className="Fourth Standard";break;
		case "V": className="Fifth Standard";break;
		case "VI": className="Sixth Standard ";break;
		case "VII": className="Seventh Standard";break;
		case "VIII": className="Eighth Standard";break;
		case "IX": className="Ninth Standard";break;
		case "X": className="Tenth Standard";break;
		case "XI": className="Eleventh Standard";break;
		case "XII": className="Twelth Standard";break;
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : className Ending");
		return className;
	}

	public static  long getNumberOFStudentCount(String loc, String acayear,UserLoggingsPojo custdetails){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getNumberOFStudentCount Starting");
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		long count=0;

		try{
			conn=JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement("{ call getNumberOFStudentCount() }");
			rs=pstmt.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}

		finally{
			try{

				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}

			}
			catch(SQLException sql){
				sql.printStackTrace();

			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getNumberOFStudentCount Ending");

		return count;

	}
	public static String getSepecializationName(String classId, String sectionId, String locationId,
			String specilizationId,UserLoggingsPojo userLoggingsVo) {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSepecializationName Starting");

		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		String specname=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt=conn.prepareStatement("SELECT `Specialization_name` specname FROM `campus_class_specialization` WHERE `Specialization_Id`=?");
			pstmt.setString(1, specilizationId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				specname=rs.getString("specname");
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSepecializationName Ending");
		return specname;
	}
	public static String getSectionName(String classId, String sectionId, String locationId, UserLoggingsPojo userLoggingsVo) {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSectionName Starting");
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		String sectionname=null;
		try {


			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt=conn.prepareStatement("SELECT `classsection_name_var` FROM `campus_classsection` WHERE classdetail_id_int=? AND `classsection_id_int`=? AND locationId=?");
			pstmt.setString(1, classId);
			pstmt.setString(2, sectionId);
			pstmt.setString(3, locationId);


			rs = pstmt.executeQuery();

			while (rs.next()) {

				sectionname=rs.getString("classsection_name_var");
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSectionName Ending");

		return sectionname;
	}
	public static String getClassName(String classId,String locationId,UserLoggingsPojo userLoggingsVo) {


		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		String classname=null;
		try {


			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt=conn.prepareStatement("SELECT classdetails_name_var FROM campus_classdetail WHERE classdetail_id_int=? AND locationId=?");
			pstmt.setString(1, classId);
			pstmt.setString(2, locationId);


			rs = pstmt.executeQuery();

			while (rs.next()) {

				classname=rs.getString("classdetails_name_var");
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return classname;
	}

	public static String getSubjectName(String subjectid,String classId,String locationId,UserLoggingsPojo custdetails) {


		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		String subjectname=null;
		try {


			conn = JDBCConnection.getSeparateConnection(custdetails);

			pstmt=conn.prepareStatement("SELECT `subjectName` FROM `campus_subject` WHERE `subjectID`=? AND `classid`=? AND locationId=?");
			pstmt.setString(1, subjectid);
			pstmt.setString(2, classId);
			pstmt.setString(3, locationId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				subjectname=rs.getString("subjectName");
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return subjectname;
	}

	public static String getExamTypeName(String examid,String classId,String locationId,UserLoggingsPojo custdetails) {


		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		String examtypename=null;
		try {


			conn = JDBCConnection.getSeparateConnection(custdetails);

			pstmt=conn.prepareStatement("SELECT examname FROM `campus_examination` WHERE `examid`=? AND `classid`=? AND `loc_id`=?");
			pstmt.setString(1, examid);
			pstmt.setString(2, classId);
			pstmt.setString(3, locationId);


			rs = pstmt.executeQuery();

			while (rs.next()) {

				examtypename=rs.getString("examname");
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return examtypename;
	}
	public static String getExamType(String examid,String classId,String locationId,UserLoggingsPojo custdetails, String accyear) {


		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		String examtypename=null;
		try {


			conn = JDBCConnection.getSeparateConnection(custdetails);

			pstmt=conn.prepareStatement("SELECT `exam_prefix` FROM `campus_examtype` WHERE `examtypeid`=(SELECT `examtype` FROM `campus_examination` WHERE `examid`=? AND `classid`=? AND `loc_id`=? AND `acadamicyear`=?)");
			pstmt.setString(1, examid);
			pstmt.setString(2, classId);
			pstmt.setString(3, locationId);
			pstmt.setString(4, accyear);


			rs = pstmt.executeQuery();

			while (rs.next()) {

				examtypename=rs.getString("exam_prefix");
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return examtypename;
	}

	public static Comparator<ReportMenuVo> SubjectComparator = new Comparator<ReportMenuVo>() {

		public int compare(ReportMenuVo s1, ReportMenuVo s2) {
			String Subname1 = s1.getSubjectName().toUpperCase();
			String Subname2 = s2.getSubjectName().toUpperCase();

			//ascending order
			return Subname1.compareTo(Subname2);

			//descending order
			//return StudentName2.compareTo(StudentName1);
		}
	};


	

	public static String getAcademicYearStartAndEndDate(String academic_year,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAcademicYearStartAndEndDate Starting");

		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		String dates=null;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement("SELECT startDate,endDate FROM campus_acadamicyear WHERE acadamic_id=?");
			pstmt.setString(1, academic_year);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dates =(rs.getString("startDate"))+","+(rs.getString("endDate"));		
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getAcademicYearStartAndEndDate Ending");
		return dates;
	}

	
	public static void recordLog_Activity(String sessid, String mod_name, String submodule, String activity,
			String activity_sql, UserLoggingsPojo pojo,Connection conn) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : recordLog_Activity Starting");
		PreparedStatement pstmt = null;

		try{
			pstmt = conn.prepareStatement(SQLUtilConstants.RECORD_LOG_ACTIVITY);
			pstmt.setString(1,sessid);
			pstmt.setString(2,mod_name);
			pstmt.setString(3,submodule);
			pstmt.setString(4,activity);
			pstmt.setString(5,activity_sql.substring(activity_sql.indexOf(":")+1,activity_sql.length()));

			pstmt.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  HelperClass : recordLog_Activity Ending");
	}



	public static String getLocationFace(String locId,UserLoggingsPojo dbdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getLocationFace Starting");

		CallableStatement cpstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		String locationFace=null;
		try {
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			cpstmt=conn.prepareCall("{CALL `getLocationName`(?)}");
			cpstmt.setString(1, locId);
			rs = cpstmt.executeQuery();
			while (rs.next()) {
				locationFace=rs.getString("Location_Name");
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (cpstmt != null && !cpstmt.isClosed()) {
					cpstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getLocationFace Ending");
		return locationFace;
	}



	public static String getTeacherNameFace(String teacherId, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getTeacherNameById Starting");

		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		String teacherName=null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement("SELECT CONCAT(`FirstName`,' ',`LastName`) teacherName FROM `campus_teachers` WHERE `TeacherID`=?");
			pstmt.setString(1, teacherId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				teacherName=rs.getString("teacherName");
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getTeacherNameById Ending");
		return teacherName;
	}

	public static String getPreAcadamicYearId(UserLoggingsPojo userLoggingsVo) throws SQLException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getPreAcadamicYearId Starting");
		CallableStatement proc = null;
		String accYear = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			proc = conn.prepareCall("{ call getPreAccYear() }");
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				accYear = rs.getString("acadamic_id");
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getPreAcadamicYearId Ending");
		return accYear;
	}






// returns true if student is old else false new student 
public static Boolean getStudentType(String accyear, String stuId, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getStudentType Starting");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		int count = 0;
		try{

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM `campus_student_classdetails` WHERE `student_id_int` = ? AND `fms_acadamicyear_id_int` = ?");
			pstmt.setString(1, stuId);
			pstmt.setString(2, accyear);
			rs = pstmt.executeQuery();

			System.out.println("getStudentType : "+ pstmt);

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if(count > 0){
				flag = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(rs != null && (!rs.isClosed())){
					rs.close();
				}if(pstmt != null && (!pstmt.isClosed())){
					pstmt.close();
				}if(conn != null && (!conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in HelperClass: getStudentType: Ending");

		return flag;
	
}


	public static String getNextAcadamicYearDates(UserLoggingsPojo custdetails, String academic_year) throws SQLException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getNextAcadamicYearDates Starting");
		CallableStatement proc = null;
		String dates = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			proc = conn.prepareCall("{ call getNextAcademicYear(?) }");
			proc.setString(1, academic_year);
			rs = proc.executeQuery();
			while (rs.next()){
				dates = rs.getString("dates");
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getNextAcadamicYearDates Ending");
		return dates;
	}

	public static void recordLog_Activity(String sessid,String mod_name,String submodule,String activity,String activity_sql,Connection conn){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : recordLog_Activity Starting");
		PreparedStatement pstmt = null;

		try{

			pstmt = conn.prepareStatement(SQLUtilConstants.RECORD_LOG_ACTIVITY);
			pstmt.setString(1,sessid);
			pstmt.setString(2,mod_name);
			pstmt.setString(3,submodule);
			pstmt.setString(4,activity);
			pstmt.setString(5,activity_sql.substring(activity_sql.indexOf(":")+1,activity_sql.length()));

			pstmt.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  HelperClass : recordLog_Activity Ending");
	}
	
	
	public static List<LocationVO> getSingleLocationDetails(String locId, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: getLocationDetails : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("select cl.*,csa.address_line1,csa.address_line2,csa.tel_phone,csa.pin_code,csi.website web from campus_school_info csi,campus_location cl join campus_school_address csa on cl.loc_add_id=csa.scl_address_id where isActive='Y' AND Location_Id=? order by Location_Name");
			pstmt.setString(1, locId);
			
			rs=pstmt.executeQuery();
			 
			while(rs.next()){
				LocationVO locationList=new LocationVO();
				locationList.setLocation_id(rs.getString("Location_Id"));
				locationList.setLocationname(rs.getString("Location_Name"));
				locationList.setAddress(rs.getString("address_line1")+" "+rs.getString("address_line2")+","+rs.getString("pin_code"));
				locationList.setContactno(rs.getString("tel_phone"));
				locationList.setEmailId(rs.getString("emailId"));
				locationList.setBoard(rs.getString("board"));
				locationList.setAffilno(rs.getString("affilation"));
				locationList.setSchoollogo(rs.getString("schoollogo"));
				locationList.setWebsite(rs.getString("web"));
				
				list.add(locationList);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
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
				+ " Control in LocationDAOImpl : getLocationDetails : Ending");
		return list;
	}
	public static int getMaxperiod(UserLoggingsPojo custdetails) throws SQLException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getNextAcadamicYearDates Starting");
		CallableStatement proc = null;
		int periods = 0;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			proc = conn.prepareCall("{ call getMaxPeriod() }");
			
			rs = proc.executeQuery();
			while (rs.next()){
				periods = rs.getInt(1);
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getNextAcadamicYearDates Ending");
		return periods;
	}
	
	public static List<BankVO> getBankdetails(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getBankdetails Starting");

		CallableStatement proc = null; 
		ResultSet rs = null;
		Connection conn = null;
		List<BankVO> bankList=new ArrayList<BankVO>();
		try {


			conn = JDBCConnection.getSeparateConnection(custdetails);

			proc=conn.prepareCall("{ call getBank() }");
			rs = proc.executeQuery();

			while (rs.next()) {
				BankVO vo=new BankVO();
				vo.setId(rs.getString("BankId"));				
				vo.setName(rs.getString("BankName"));
				vo.setShortname(rs.getString("BankShortName"));
				bankList.add(vo);

			}




		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getBankdetails Ending");
		return bankList;
	}
	
	public static List<BankBranchVO> getBankBranch(String bankId,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getBankBranch Starting");

		CallableStatement proc = null; 
		ResultSet rs = null;
		Connection conn = null;
		List<BankBranchVO> branchList=new ArrayList<BankBranchVO>();
		try {


			conn = JDBCConnection.getSeparateConnection(custdetails);

			proc=conn.prepareCall("{ call getBankBranch(?) }");
			proc.setString(1, bankId);
			System.out.println("getBankBranch  -->>"+proc);
			rs = proc.executeQuery();

			while (rs.next()) {
				BankBranchVO vo=new BankBranchVO();
				vo.setId(rs.getString("BranchId"));				
				vo.setBranchName(rs.getString("BranchName"));
				vo.setBranchShortName(rs.getString("BranchShortName"));
				branchList.add(vo);

			}




		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getBankBranch Ending");
		return branchList;
	}
	
	
	
	public static String getIFSCCode(String branchId,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getIFSCCode Starting");

		CallableStatement proc = null; 
		ResultSet rs = null;
		Connection conn = null;
		String ifscCode="";
		try {


			conn = JDBCConnection.getSeparateConnection(custdetails);

			proc=conn.prepareCall("{ call getIFSCCode(?) }");
			proc.setString(1, branchId);
			rs = proc.executeQuery();

			if(rs.next()) {
				BankBranchVO vo=new BankBranchVO();
				ifscCode=rs.getString("IFSCCode");				
				

			}




		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getIFSCCode Ending");
		return ifscCode;
	}
	public static List<classVo> getAllClassList(String location,UserLoggingsPojo custdetails) {

		PreparedStatement proc = null;                                                                                               
		List<classVo> classList = new ArrayList<classVo>();
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			proc = conn.prepareStatement("SELECT * FROM `campus_classdetail` WHERE locationId=? order by LENGTH(classdetail_id_int),classdetail_id_int");
			proc.setString(1, location);
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				classVo vo = new classVo();
				vo.setClassId(rs.getString("classdetail_id_int"));
				vo.setClassName(rs.getString("classdetails_name_var"));
				classList.add(vo);
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return classList;
	}

	public static List<FeeNameVo> getTermList(String location,String accyear,UserLoggingsPojo custdetails) {

		PreparedStatement proc = null;                                                                                               
		List<FeeNameVo> TermList = new ArrayList<FeeNameVo>();
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			proc = conn.prepareStatement("SELECT * FROM `campus_fee_termdetails` WHERE locationId=? AND accyear=? order by LENGTH(termid),termid");
			proc.setString(1, location);
			proc.setString(2, accyear);
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				FeeNameVo vo = new FeeNameVo();
				vo.setTermId(rs.getString("termid"));
				vo.setTerm(rs.getString("termname"));
				TermList.add(vo);
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return TermList;
	}
	public static String getClassStudentCount(String locId, String classId, String sectionId,UserLoggingsPojo userLoggingsVo, String academicYear) {
		PreparedStatement proc = null;                                                                                               
		ResultSet rs = null;
		Connection conn = null;
		String result = "" ;
		
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			proc = conn.prepareStatement("SELECT DISTINCT (SELECT `classsection_strength_int` FROM  `campus_classsection` WHERE `classsection_id_int` = ? AND `classdetail_id_int` = ? AND `locationId` = ?) AS TotalStr  , COUNT(*) AS Total FROM `campus_student_classdetails` WHERE student_status='STUDYING' AND locationId = ? AND `fms_acadamicyear_id_int` = ? AND `classdetail_id_int` = ? AND `classsection_id_int` = ?");
			
			
			proc.setString(1, sectionId);
			proc.setString(2, classId);
			proc.setString(3, locId);
			proc.setString(4, locId);
			proc.setString(5, academicYear);
			proc.setString(6, classId);
			proc.setString(7, sectionId);
			
			proc.execute();
			rs = proc.getResultSet();

			while (rs.next()) {
				result = rs.getString("TotalStr")+","+rs.getString("Total");
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (proc != null && !proc.isClosed()) {
					proc.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return result;
	}
	
	public static SmsIntegrationApiVO getSmsApiInfo(Connection conn){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSmsApiInfo Starting");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		SmsIntegrationApiVO vo= new SmsIntegrationApiVO();
		try{
			conn = DBConnection.getmasterConnection();
			pstmt = conn.prepareStatement("SELECT `sms_url`,`apikey`,`sender_id`,`service_name` FROM `campus_smsapi_details` WHERE isActive='Y'");
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo.setSmsURL(rs.getString("sms_url"));
				vo.setApiKey(rs.getString("apikey"));
				vo.setSenderId(rs.getString("sender_id"));
				vo.setServiceName(rs.getString("service_name"));

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
			catch(SQLException sql){
				sql.printStackTrace();

			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in HelperClass : getSmsApiInfo Ending");

		return vo;

	}
	
	public static String getClassIDByStudent(String studentId,String accyearId,UserLoggingsPojo userLoggingsVo) {


		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		String classid=null;
		try {


			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt=conn.prepareStatement("SELECT classdetail_id_int FROM campus_student_classdetails WHERE student_id_int=? AND fms_acadamicyear_id_int=?");
			pstmt.setString(1, studentId);
			pstmt.setString(2, accyearId);


			rs = pstmt.executeQuery();

			while (rs.next()) {

				classid=rs.getString("classdetail_id_int");
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		return classid;
	}
}