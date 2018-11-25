
package com.centris.campus.actions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.alibaba.fastjson.JSONObject;
import com.centris.campus.daoImpl.FeeCollectionDaoImpl;
import com.centris.campus.daoImpl.LibraryDAOIMPL;
import com.centris.campus.daoImpl.StaffAttendanceDaoImpl;
import com.centris.campus.daoImpl.TimeTableDaoImpl;
import com.centris.campus.daoImpl.TransportDaoImpl;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.ExamTimeTableBD;
import com.centris.campus.delegate.HolidayMasterBD;
import com.centris.campus.delegate.LeaveBankDelegate;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.RoleMasterBD;
import com.centris.campus.delegate.SmsDeligate;
import com.centris.campus.delegate.SpecializationBD;
import com.centris.campus.delegate.StaffAttendanceBD;
import com.centris.campus.delegate.TeacherRegistrationBD;
import com.centris.campus.delegate.TemporaryRegBD;
import com.centris.campus.delegate.TransportBD;
import com.centris.campus.delegate.TeacherAttendanceBD;
import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.HelperClassVo;
import com.centris.campus.vo.HolidayMasterVo;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LibraryVO;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.StaffAttendanceVo;
import com.centris.campus.vo.SmsCountVO;
import com.centris.campus.vo.SpecializationVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.VehicleDetailsVO;
import com.itextpdf.text.log.SysoLogger;
public class MenusAction extends DispatchAction {

	private static final Logger logger = Logger.getLogger(MenusAction.class);

	public ActionForward Admission(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: Admission Starting");

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ADMIN);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ADMIN);

		try {
			String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false)
					.getAttribute("token_information");

			UserDetailVO usedetails = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			HashMap<String, String> permission = usedetails.getPermissionmap();
			List<String> DashBoardList = new ArrayList<String>();

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String location_id = (String) request.getSession(false).getAttribute("current_schoolLocation");


			List<Issuedmenuvo> appliedList = new ArrayList<Issuedmenuvo>();
			appliedList = new TemporaryRegBD().getissuedforms("%%", userLoggingsVo,location_id+","+accYear);
			int appliedForm = (appliedList.size());

			
			String approvedDetails = new TemporaryRegBD().getApprovedFormsCount(academic_year, userLoggingsVo,
					location_id);
/*
			String[] regDetails = approvedDetails.split(":");
			int approved = Integer.parseInt(regDetails[0]);
			int rejected = Integer.parseInt(regDetails[1]);
			int cancelled = Integer.parseInt(regDetails[2]);
			int processed = Integer.parseInt(regDetails[3]);*/

		/*	System.out.println(appliedForm);
			
			System.out.println(approved);
			System.out.println(rejected);
			System.out.println(cancelled);
			System.out.println(processed);*/

			/*String heading3 = "<div class='row'>" + "<h4 style='text-align:left;padding: 5px;'>Admission Application Details</h4>"
					+ "<div id='piechart' style='margin-left: 235px;margin-top: -50px;'></div>"

					+ "</div>" + "<script type='text/javascript'>"

					+ "google.charts.load('current', {'packages':['corechart']});"
					+ "google.charts.setOnLoadCallback(drawChart);"

					// Draw the chart and set the chart values
					+ " function drawChart() {" + "	var data = google.visualization.arrayToDataTable(["
					+ "  ['Task', 'Hours per Day']," + "                                                  ['Applied', "
					+ appliedForm + "]," + "                                                  ['Approved', " + approved
					+ "]," + "                                                  ['Processing'," + processed + "],"
					+ "                                                  ['Cancelled'," + cancelled + "],"
					+ "                                                  ['Rejected '," + rejected + "],"
					+ "                                                  ]);"

					// Optional; add a title and set the width and height of the
					// chart
					+ "  var options = {title: ' ','width':750, 'height':600};" // colour
																				// adjustment

					// Display the chart inside the <div> element with
					// id="piechart"
					+ " var chart = new google.visualization.PieChart(document.getElementById('piechart'));"
					+ "chart.draw(data, options);" + "}" + "</script>";

			DashBoardList.add(heading3);*/
			request.setAttribute("dashboard", "JS/admissionDashboard.js");
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : Admission Ending");
		return mapping.findForward("AdmissionLanding");
	}

public ActionForward Settings(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException
	{

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: admission Starting");
		String schoolName = "";
		String address = "";
		String contact = "";
		String emailId = "";
		String website = "";
		String boardLogo = "";
		String schoolLogo = "";

		String location_id = (String) request.getSession(false).getAttribute("current_schoolLocation");

		
		
		try {

			UserDetailVO usedetails = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false)
					.getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);

			LocationBD obj = new LocationBD();
			ArrayList<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationAllDetails(location_id, userLoggingsVo);

			LocationVO custSchoolInfo = new LocationVO();

			List<LocationVO> lst = new LocationBD().SchoolList(custSchoolInfo, userLoggingsVo);

			schoolName = lst.get(0).getLocationname();
			address = lst.get(0).getAddress();
			emailId = lst.get(0).getEmailId();
			website = lst.get(0).getWebsite();
			schoolLogo = lst.get(0).getSchoollogo();

			HashMap<String, String> permission = usedetails.getPermissionmap();
			List<String> DashBoardList = new ArrayList<String>();

			if (permission.get("SLCDIS").equalsIgnoreCase("true")) {

				String heading = "<h4>School Details</h4>" + "<div class='row'>" + "<div class='col-md-12'>"
						+ "<div class='col-md-6'style='height: 100%;'><ul class='school-info'>" + "<li >" + schoolName
						+ "</li><li>" + address + "</li><li>" + contact + "</li>" + "<li>" + emailId + "</li><li>"
						+ website + "</li>" + "</ul></div>" + "<div class='col-md-6' style='height: 120px;' >"
						+ "<img src='" + schoolLogo + "' alt='Logo' class='school-details'>" + "</div>" + "</div>"
						+ "</div>";

				DashBoardList.add(heading);
			}

			
			
			if (permission.get("HOLDIS").equalsIgnoreCase("true")) {

				String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

				ArrayList<HolidayMasterVo> vo = new HolidayMasterBD().getHolidayDetails(academic_year, location_id,
						userLoggingsVo);

					String body ="";
					
					if (vo.size() > 0) {
						
						for (int i = 0; i < vo.size(); i++) {
							
							String[] splitDate = vo.get(i).getDate().split("-");
							String month = Month.of(Integer.parseInt(splitDate[1])).toString().substring(0, 3);
							
							body += "<div class='row content-row'><div class='col-md-12' style='text-align:  center;'>"
									+ "<div class='col-md-2 row-data'>"
									+  month  
									+  splitDate[0]
									+ "</div><div class='col-md-10 row-data'>"
									+ vo.get(i).getHolidaysName()
									+ "</div></div></div>";
						}
					String gradelist = "<h4>Holiday Details</h4>"
							+ "<div style='height: 228px;overflow:scroll;'><div class='subtable-header-content'><div><div  class='row subtable-header'><div  class='col-md-2' style='left:15px'>Date</div><div  class='col-md-10' style='left: 10px;'>Name</div></div></div>"
							+ "<div style='overflow:auto;'>" + body + "</div></div></div>";
					
					DashBoardList.add(gradelist);
					
				} else {

					String gradelist = "<h4>Holiday Details</h4>"
							+ "<div><div class='subtable-header-content'><div><div  class='row subtable-header'><div  class='col-md-6'>Subject</div><div  class='col-md-6'>Specialization</div></div></div>"
							+ "<div style='overflow:auto;'><h3>No holiday added</h3></div></div></div>";
					
					DashBoardList.add(gradelist);
				}
			}
			
			
			
			

			if (permission.get("ROLDIS").equalsIgnoreCase("true")) {

				RoleMasterBD masterBD = new RoleMasterBD();
				
				List<RoleMasterPojo> vo = new ArrayList<RoleMasterPojo>();
				
				vo = masterBD.getRoles(userLoggingsVo,location_id);

				
				
				String body ="";
				
				if (vo.size() > 0) {
					for (int i = 0; i < vo.size(); i++) {
						
						body += "<div class='row content-row'><div class='col-md-12' style='text-align:  center;'>"
								+ "<div class='col-md-6 row-data' style='left:-15px'>"
								+vo.get(i).getRoleName()
								+ "</div><div class='col-md-6 row-data' style='left:-83px'>"
								+  vo.get(i).getRoleDescription()
								+ "</div></div></div>";
					}
				}
				
				String gradelist = "<h4>Role Details</h4>"
						+ "<div style='height: 228px;overflow:scroll;'><div class='subtable-header-content'><div><div  class='row subtable-header'><div  class='col-md-4'>Role</div><div  class='col-md-8'>Description</div></div></div>"
						+ "<div style='overflow:auto;'>" + body + "</div></div></div>";
				
				
				DashBoardList.add(gradelist);

			}

			if (permission.get("SPLDIS").equalsIgnoreCase("true")) {

				SpecializationBD sbd = new SpecializationBD();
				ArrayList<SpecializationVo> vo = new ArrayList<SpecializationVo>();
				vo = sbd.getspecializationList(location_id, userLoggingsVo);

				
               String body ="";
				
				if (vo.size() > 0) {
					for (int i = 0; i < vo.size(); i++) {
						
						body += "<div class='row content-row'><div class='col-md-12' style='text-align:  center;'>"
								+ "<div class='col-md-6 row-data'>"
								+ vo.get(i).getStream_Name()
								+ "</div><div class='col-md-6 row-data'>"
								+  vo.get(i).getSpec_Name()
								+ "</div></div></div>";
					}
				}
				
				String gradelist = "<h4>Subject Specialization</h4>"
						+ "<div style='height: 228px;overflow:scroll;'><div class='subtable-header-content'><div><div  class='row subtable-header'><div  class='col-md-6' style='left: 14px;'>Subject</div><div  class='col-md-6'>Specialization</div></div></div>"
						+ "<div style='overflow:auto;'>" + body + "</div></div></div>";
				
				
				DashBoardList.add(gradelist);
				
			}

			request.setAttribute("Dashboard", DashBoardList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : Setting Ending");

		return mapping.findForward("SettingLanding");
	}

	public ActionForward Student(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: Student Menu Starting");

		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false)
				.getAttribute("token_information");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_STUDENT);

			request.setAttribute("dashboard", "JS/script.js");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : Student Menu Ending");

		return mapping.findForward("StudentLanding");
	}

	public ActionForward Staff(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: admission Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_STAFF);

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false)
					.getAttribute("token_information");
			UserDetailVO usedetails = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			HashMap<String, String> permission = usedetails.getPermissionmap();
			List<String> DashBoardList = new ArrayList<String>();

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String accid = (String) request.getSession(false).getAttribute("current_academicYear");
			
			List<StaffAttendanceVo> attendanceList =  new StaffAttendanceDaoImpl().StaffAttendanceforDashboard(location,accid,userLoggingsVo);
			
			long total_staff = 0;
			AllTeacherDetailsVo obj = new AllTeacherDetailsVo();
			obj.setLocid(location);
			obj.setDeptid("%%");
			obj.setDesgId("%%");
			obj.setStatus("Y");
			
			ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();
			
			list = new TeacherRegistrationBD().getAllTeacherDetails(userLoggingsVo, obj);
			
			total_staff = list.size();

			String startdate = HelperClass.getCurrentSqlDate().toString();

			String heading = "<h4 >Staff Strength</h4>"
					+ "<div style='position:relative;height:100%;text-align:center;'>"
					+ "<div class='col-md-4' style='left: 35%; '><div class='dynamic-circle teacher'><span style='right: 0 !important;' class='badge zoom primarythemebackgound'>"
					+ total_staff + "</span></div><label class='control-label student'>Staff</label></div>" + "" + ""
					+ "" + "</div>";
			
			
			
			DashBoardList.add(heading);

			String msg = "attendance";
			ArrayList<StaffAttendanceVo> vo = new StaffAttendanceBD().getStaffAttendanceCount(startdate,
					userLoggingsVo, msg,location);
			
			String body = "";
			if (vo.size() > 0) {
				for (int i = 0; i < vo.size(); i++) {
					
					body += "<div class='row content-row'><div class='col-md-12' style='text-align:  center;'>"
							+ "<div class='col-md-6 row-data'>"
							+ vo.get(i).getDepartment()
							+ "</div><div class='col-md-6 row-data'>"
							+  vo.get(i).getAbsent_count()
							+ "</div></div></div>";
				}
			}
			
			String gradelist = "<h4>Absent - Department wise</h4>"
					+ "<div><div class='subtable-header-content' style='overflow: scroll;height: 227px;'><div><div  class='row subtable-header'><div  class='col-md-6' style='right:-14px'>Department</div><div  class='col-md-6' style='right:43px'>Total Absentee</div></div></div>"
					+ "<div style='overflow:auto;'>" + body + "</div></div></div>";
			
			
			DashBoardList.add(gradelist);
			
			

			ArrayList<TimeTableVo> teacherSustituteLis = new TimeTableDaoImpl()
					.getTeacherTimeTableTodayListByClass(userLoggingsVo);
			
			String vacant = "";
			String head = "";
			String heading2 = "";
			
			if (teacherSustituteLis.size() > 0) {
				for (int i = 0; i < teacherSustituteLis.size(); i++) {
					if (teacherSustituteLis.get(i).getStatus().equalsIgnoreCase("substituted")) {
						head = "Substituted Period";

						System.out.println(
								"LENGTH OF CLASS " + teacherSustituteLis.get(i).getClassname().split(",").length);

						if (teacherSustituteLis.get(i).getClassname().split(",").length > 1) {
							vacant = vacant + "<tr><td style='text-align: left;' rowspan='"
									+ teacherSustituteLis.get(i).getClassname().split(",").length + "'>"
									+ teacherSustituteLis.get(i).getTeachername().split("-")[1] + "</td>";
							for (int k = 0; k < teacherSustituteLis.get(i).getClassname().split(",").length; k++) {
								vacant = vacant + "<td style='text-align: left;'>"
										+ teacherSustituteLis.get(i).getClassname().split(",")[k] + "</td><td>"
										+ teacherSustituteLis.get(i).getPeriod1().split("-")[0].split(",")[k]
										+ "</td><td>"
										+ teacherSustituteLis.get(i).getPeriod1().split("-")[1].split(",")[k]
										+ "</td></tr>";
							}
						} else {
							vacant = vacant + "<tr><td style='text-align: left;'>"
									+ teacherSustituteLis.get(i).getTeachername().split("-")[1] + "</td><td>"
									+ teacherSustituteLis.get(i).getClassname() + "</td><td>"
									+ teacherSustituteLis.get(i).getPeriod1().split("-")[0] + "</td><td>"
									+ teacherSustituteLis.get(i).getPeriod1().split("-")[1] + "</td></tr>";
						}

					} else {
						head = "Vacant Period";
						if (teacherSustituteLis.get(i).getClassname().split(",").length > 1) {
							vacant = vacant + "<tr><td style='text-align: left;' rowspan='"
									+ teacherSustituteLis.get(i).getClassname().split(",").length + "'>"
									+ teacherSustituteLis.get(i).getTeachername().split("-")[1] + "</td>";
							for (int j = 0; j < teacherSustituteLis.get(i).getClassname().split(",").length; j++) {
								vacant = vacant + "<td style='text-align: left;'>"
										+ teacherSustituteLis.get(i).getClassname().split(",")[j] + "</td><td>"
										+ teacherSustituteLis.get(i).getPeriod1().split("-")[0].split(",")[j]
										+ "</td><td>"
										+ teacherSustituteLis.get(i).getPeriod1().split("-")[1].split(",")[j]
										+ "</td></tr>";
							}
						} else {
							vacant = vacant + "<tr><td style='text-align: left;' rowspan=''>"
									+ teacherSustituteLis.get(i).getTeachername().split("-")[1] + "</td><td>"
									+ teacherSustituteLis.get(i).getClassname() + "</td><td>"
									+ teacherSustituteLis.get(i).getPeriod1().split("-")[0] + "</td><td>"
									+ teacherSustituteLis.get(i).getPeriod1().split("-")[1] + "</td></tr>";
						}
					}
				}
				heading2 = "<h4>" + head + "</h4>"
						+ "<div id='table' style='padding-top: 4px;height: 100%; overflow-y: scroll;'><table id='allstudent' style='width:95%; margin-left: 3%;'><thead><tr style='background-color: #e6e6e6;'><th style='text-align: center;'>Staff</th><th style='text-align: center;'>Class</th><th style='text-align: center;'>Period</th><th style='text-align: center;'>Subject</th></tr></thead>"
						+ "<tbody>" + "</tbody>" + vacant + "</table></div>";

			} else {

				heading2 = "<h4>Time Table Details</h4>"
						+ "<div style='position:relative;height:100%;text-align:center;'>"
						+ "<div style='padding: 18%;font-weight: bold;'>" + "<lable>No Vacant Periods</lable>"
						+ "</div>" + "" + "" + "" + "" + "" + "</div>";
			}
			DashBoardList.add(heading2);

			msg = "information";
			ArrayList<StaffAttendanceVo> information = new StaffAttendanceBD().getStaffAttendanceCount(startdate,
					userLoggingsVo, msg,location);

			String winf = "";
			String withtinf = "";

			if (information.size() > 0) {
				for (int i = 0; i < information.size(); i++) {
					if (information.get(i).getStatus().equalsIgnoreCase("Absent")) {
						withtinf = withtinf + "<tr style='background-color:#e6e6e6;height: 22px;'><td style='text-align: left;'>" + information.get(i).getTeacherName()
								+ "</td><td>" + information.get(i).getStatus() + "</td></tr>";
					} else {
						winf = winf + "<tr style='background-color:#e6e6e6;height: 22px;'><td style='text-align: left;'>" + information.get(i).getTeacherName()
								+ "</td><td>" + information.get(i).getStatus() + "</td></tr>";
					}
				}

			} else {
				/*
				 * heading2="<h3>Time Table Details</h3>" +
				 * "<div style='position:relative;height:162px;text-align:center;'>"
				 * + "<div>" + "Today Attendance Not Added" + "</div>" + "" + ""
				 * + "" + "" + "" + "</div>";
				 */
			}
			String absentStatus="";
			for(int i=0;i<attendanceList.size();i++) {
				String leaveStatus="No";
				String absentstatus="Yes";
				if(attendanceList.get(i).getStatus().equalsIgnoreCase("Leave")) {
					leaveStatus="Yes";
					absentstatus="No";
				}
				else {
					leaveStatus="No";
					absentstatus="Yes";
				}
				absentStatus=absentStatus+"<tr><td style='text-align: center;width: 50%;'>"+attendanceList.get(i).getTeacherName()+"</td><td style='text-align: center;'>"+leaveStatus+"</td><td style='text-align: center;'>"+absentstatus+"</td></tr>";
			}
			

			String heading3 = "<h4>Absent</h4>"
					+ "<div id='table' style='padding-top: 4px;height: 100%; overflow-y: auto;' class='subtable-header-content'><table  style='width:100%;'><thead><tr style='background-color:#e6e6e6;height: 22px;'><th style='text-align: center;width: 50%;'>Staff Name</th><th style='text-align: center;'>Leave</th><th style='text-align: center;'>Absent</th></tr></thead>"
					+ "<tbody>"+absentStatus+"</tbody></table></div>";
			DashBoardList.add(heading3);
/*
			String heading4 = "<h4>Today Absent Without Information</h4>"
					+ "<div id='table' style='padding-top: 4px;height: 100%; overflow-y: auto;' class='subtable-header-content'><table  style='width:100%;'><thead><tr style='background-color: #e6e6e6;height: 22px;'><th style='text-align: center;width: 50%;'>Staff Name</th><th style='text-align: center;'>Status</th></tr></thead>"
					+ "<tbody>" + "</tbody>" + withtinf + "</table></div>";
			DashBoardList.add(heading4);*/

			request.setAttribute("Dashboard", DashBoardList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : storeAbsentSms Ending");

		return mapping.findForward("StaffLanding");
	}

	public ActionForward Fee(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: admission Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_FEE);

			request.setAttribute("DashboardJs", "JS/newUI/feedashboard.js");

			UserDetailVO usedetails = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false)
					.getAttribute("token_information");

	/*		Double todayTransactionAmount = new FeeCollectionDaoImpl().getTodayTransaction(userLoggingsVo);
			Double monthlyTransactionAmount = new FeeCollectionDaoImpl().getMonthlyTransaction(userLoggingsVo);

			int noOfStudentsPayedInCurrentDate = new FeeCollectionDaoImpl().getNoOfStudentsPayed(userLoggingsVo);
			int noOfStudentsPayedInCurrentMonth = new FeeCollectionDaoImpl()
					.getNoOfStudentsPayedInMonth(userLoggingsVo);*/

			List<HelperClassVo> date = HelperClass.getAllAcademicYearStartDate(userLoggingsVo);

			String startDate = date.get(0).getStartDate().split("-")[1].replace("0", "");

			List<StudentRegistrationVo> list = new FeeCollectionDaoImpl().getPrevMonthStatus(startDate, userLoggingsVo);

			List<String> DashBoardList = new ArrayList<String>();

			

			// 1.Transport Fee - current_year
			String msgg1 = "<div id='FeeCollection'></div>"; // animated
																								// graph
			DashBoardList.add(msgg1);

			request.setAttribute("Dashboard", DashBoardList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : storeAbsentSms Ending");

		return mapping.findForward("FeeLanding");
	}

	public ActionForward Exam(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: Exam Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_EXAM);

			UserDetailVO usedetails = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false)
					.getAttribute("token_information");


			List<String> DashBoardList = new ArrayList<String>();
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String locationId = (String) request.getSession(false).getAttribute("current_schoolLocation");

			// HEADER ONE
			String classid = "%%";
			String location = locationId;
			String body = "";

			// 1.Grade Details
			ArrayList<ExamDetailsPojo> listg = new ArrayList<ExamDetailsPojo>();
			listg = new ExamDetailsBD().displayGradeSettings(academic_year, location, custdetails);
			String body22 = "";

			if (listg.size() > 0) {
				for (int i = 0; i < listg.size(); i++) {
					body22 = body22
							+ "<div class='row content-row'><div class='col-md-12 row-data'><div class='col-md-4 row-data' style='left: -14px;'>"
							+ listg.get(i).getGradename() + "</div><div class='col-md-4 row-data' style='left: -6px;'  >"
							+ listg.get(i).getMax_marks() + "</div><div class='col-md-4 row-data exam-one-three' style='left: 5px;'>"
							+ listg.get(i).getMin_marks() + "</div></div></div>";
				}
			}
			String gradelist = "<div ><h4>Grade Details</h4>"
					+ "<div><div><div><div  class='row subtable-header'><div  class='col-md-4'>Grade Name</div><div  class='col-md-4'>Max Marks</div><div  class='col-md-4'>Min Marks</div></div></div>"
					+ "<div>" + body22 + "</div></div></div></div>";
			DashBoardList.add(gradelist);

			// 2.Exam Details
			ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
			list = new ExamTimeTableBD().getexamsettingsDetailsonClass(academic_year, location, custdetails, classid);
			String body1 = "";
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					body1 = body1
							+ "<div class='row content-row'><div class='col-md-12' style='text-align:  left;'><div class='col-md-3' style='left: -15px;width: 36%;'>"
							+ list.get(i).getExamName() + "</div><div class='col-md-3' style='left: -6px;width: 22%;'>"
							+ list.get(i).getClassname() + "</div><div class='col-md-3' style='left: -6px;width: 21%;'>"
							+ list.get(i).getStartDate() + "</div><div class='col-md-3'  style='left: 7px;width: 21%;'>"
							+ list.get(i).getEndDate() + "</div></div></div>";
				}
			}
			String heading5 = "<div ><h4>Exam Details</h4>"
					+ "<div  style='display: block;overflow: auto;max-height: 219px;'><div><div><div  class='row subtable-header'><div  class='col-md-3' style='width: 36%;'>Exam Name</div><div  class='col-md-3' style='width: 22%;'>Class Name</div><div  class='col-md-3' style='width: 21%;'>Start Date</div><div  class='col-md-3' style='width: 21%;'>End Date</div></div></div>"
					+ "<div>" + body1 + "</div></div></div></div>";
			DashBoardList.add(heading5);

			// 3.Today Scheduled Exams
			ArrayList<ExaminationDetailsVo> list33 = new ArrayList<ExaminationDetailsVo>();
			list33 = new ExamTimeTableBD().getexamsettingsDetailsonClass(academic_year, location, custdetails, classid);
			String body2 = "";
			if (list33.size() > 0) {
				for (int i = 0; i < list33.size(); i++) {
					body2 = body2
							+ "<div class='row content-row'><div class='col-md-12' style='text-align:  left;'><div class='col-md-4' style='width: 36%;padding:0;'>"
							+ list33.get(i).getExamName() + "</div><div class='col-md-2' style='width: 22%;'>"
							+ list33.get(i).getClassname() + "</div><div class='col-md-2'style='width: 21%;'>"
							+ list33.get(i).getStartDate() + "</div><div class='col-md-2'style='width: 21%;'>"
							+ list33.get(i).getEndDate() + "</div></div></div>";
				}
			}
			String heading56 = "<div ><h4>Scheduled Exams</h4>"
					+ "<div style='display: block;overflow: auto;max-height: 218px;'><div><div><div  class='row subtable-header'><div  class='col-md-4' style='width:36%;'>Exam Name</div><div  class='col-md-3'  style='width: 22%;'>Class Name</div><div  class='col-md-3'  style='width: 21%;'>Start Date</div><div  class='col-md-3'  style='width: 21%;'>End Date</div></div></div>"
					+ "<div>" + body2 + "</div></div></div></div>";
			DashBoardList.add(heading56);

			// 3.Result Details
			ArrayList<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
			list1 = new ExamTimeTableBD().getexamDeclared(academic_year, location, custdetails, classid);
			String body3 = "";
			if (list1.size() > 0) {
				for (int i = 0; i < list1.size(); i++) {
					body3 = body3
							+ "<div class='row content-row'><div class='col-md-12' style='text-align:  left;'><div class='col-md-4' style='padding-left: 0px;'>"
							+ list1.get(i).getExamName() + "</div><div class='col-md-4' style='left: -4px;' >"
							+ list1.get(i).getStatus() + "</div><div class='col-md-4' style='padding-left: 21px;'>"
							+ list1.get(i).getStatus1() + "</div></div></div>";
				}
			}
			String heading2 = "<div ><h4>Result Status</h4>"
					+ "<div><div><div  class='row subtable-header'><div  class='col-md-4'>Exam Name</div><div  class='col-md-4'>Status</div><div  class='col-md-4'>Declared Date</div></div></div>"
					+ "<div>" + body3 + "</div></div></div>";
			DashBoardList.add(heading2);

			request.setAttribute("color", "backGround");
			request.setAttribute("Dashboard", DashBoardList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : Exam Ending");
		return mapping.findForward("ExamLanding");
	}

	public ActionForward Leave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: admission Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false)
					.getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_LEAVE);

			UserDetailVO usedetails = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			HashMap<String, String> permission = usedetails.getPermissionmap();

			List<String> DashBoardList = new ArrayList<String>();

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String body = " ";
			
			ArrayList<LeaveBankVO> vo = new ArrayList<LeaveBankVO>();
			vo = new LeaveBankDelegate().getleavetypeDetails("%%", academic_year, userLoggingsVo);
			
			
			
			if (vo.size() > 0) {
				for (int i = 0; i < vo.size(); i++) {
					
					body += "<div class='row content-row'><div class='col-md-12' style='text-align:  center;'>"
							+ "<div class='col-md-4 row-data' style='left: -16px;'>"
							+vo.get(i).getLeaveName()
							+ "</div><div class='col-md-4 row-data' style='left:-4px'>"
							+ vo.get(i).getShortName()
							+ "</div><div class='col-md-4 row-data' style='left: 3px;' >"
							+ vo.get(i).getNoofleaves()
							+ "</div></div></div>";
				}
			}
			
			String gradelist = "<h4>Leave Details</h4>"
					+ "<div><div class='subtable-header-content'><div><div  class='row subtable-header'><div  class='col-md-4'>Name</div><div  class='col-md-4'>Code</div><div  class='col-md-4'>No. Of Leaves</div></div></div>"
					+ "<div style='overflow:auto;'>" + body + "</div></div></div>";
			
			
			DashBoardList.add(gradelist);

			
			
			
			request.setAttribute("Dashboard", DashBoardList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : storeAbsentSms Ending");

		return mapping.findForward("LeaveLanding");
	}

	public ActionForward Transport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: admission Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_TRANSPORT);

			request.setAttribute("DashboardJs", "JS/newUI/dashboard.js");
			
			List<String> DashBoardList = new ArrayList<String>();

	
			
			//String locId = usedetails.getLocationid();
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false)
					.getAttribute("token_information");
			String status = "Y";

			TransportBD obj = new TransportBD();
			
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String accyear = academic_year;
			
			String currentLocid=(String) request.getSession(false).getAttribute("current_schoolLocation");
			String location_id = currentLocid;
			String accyearface = HelperClass.getAcademicYearFace(academic_year, userLoggingsVo);
			
			List<VehicleDetailsVO> getvehiclelist = new ArrayList<VehicleDetailsVO>();
			getvehiclelist = obj.getAllvehicleDetails(userLoggingsVo, status, location_id);
			String withtinf = "";
			String driverContactList = "";
			
	

			String tolat_year_collection = "0";
			String tolat_year_collection_Percentage = "25";

			tolat_year_collection = new TransportBD().getTransportYearCollection(userLoggingsVo, accyear, location_id);
			String tolat_today_collection = "0";
			tolat_today_collection = new TransportBD().getTodayCollection(userLoggingsVo, location_id);
			String tolat_today_collection_Percentage = "0";

			if (tolat_today_collection != "0" && 2 < tolat_today_collection.length()) {
				tolat_today_collection_Percentage = tolat_today_collection.substring(0, 3);
			}
			if (Integer.parseInt(tolat_year_collection_Percentage) < 25) {
				tolat_year_collection_Percentage = tolat_year_collection.substring(0, 2);
			}

			String msgg = "<h3  class='headerdashboardBox primarythemebackgound primarythemecolor'>Transportation Fee Collection</h3>"
					+ "<div style='position:relative;height:162px;text-align:center;'>" + "<div style='height:"
					+ tolat_year_collection_Percentage
					+ "%;width:75px;background-color: #2bb58f;color: #fff !important;position:absolute;bottom:0;left: 20%;font-weight:bold;font-size:12px;color:#000;min-height: -1px;'>"
					+ "<pre>" + "This Year" + "</pre>" + " " + tolat_year_collection + "</div>" + "<div style='height:"
					+ tolat_today_collection_Percentage
					+ "%;width:75px;background-color: #309245;color:#fff;position:absolute;bottom:0;left: 185px;font-size:12px;color: #fff;min-height: 33px;'>"
					+ "<pre>" + "Today" + "</pre>" + " " + tolat_today_collection + "</div>" + "</div>";

			// 1.Transport Fee - current_year
			
			String msgg1 = "<h4>Term wise Transport Fee : "+accyearface+"</h4><div id='myChart' style='height:100%; width:100%;'></div>"; // animated
			DashBoardList.add(msgg1);

			// 2.Transport Category
			String heading1 = "<div id='TransportCateg' style='height: 350px; width: 100%;'></div>"; // animated
																										// pie
																										// chart
			DashBoardList.add(heading1);

			// 3.Insurance Details
			if (getvehiclelist.size() > 0) {
				for (int i = 0; i < getvehiclelist.size(); i++) {
					withtinf = withtinf
							+ "<div class='row' style='border-bottom: 1px solid #76d2814f; '><div class='col-md-12 row-data'><div class='col-md-4 pl0'>"
							+ getvehiclelist.get(i).getVehicleregno()
							+ "</div><div class='col-md-4 dtrns32' >"
							+ getvehiclelist.get(i).getVehiclename()
							+ "</div><div class='col-md-4 row-data dtrns33'>"
							+ getvehiclelist.get(i).getPermitvalidity() + "</div></div></div>";
				}
			}else{
				withtinf = withtinf
						+ "<div class='row' style='border-bottom: 1px solid #76d2814f; '><div class='col-md-12 row-data'>No Records Found</div></div>";
			}
			String heading5 = "<h4>Insurance Details</h4>"
					+ "<div><div class='vehicle'style='height:100%; overflow: scroll;height:219px;'><div><div  class='row subtable-header'><div  class='col-md-4'>Reg No.</div><div  class='col-md-4'>Vehicle Name</div><div  class='col-md-4'>Expiry</div></div></div>"
					+ "<div>" + withtinf + "</div></div></div>";
			DashBoardList.add(heading5);

			// 4. driver details
			List<FeeCollectionVo> vo = new TransportDaoImpl().getTransportfee(userLoggingsVo, accyear, location_id,"transport");
			if (vo.get(0).getDriverContactNoList().size() > 0) {
				for (int i = 0; i < vo.get(0).getDriverContactNoList().size(); i++) {
					driverContactList = driverContactList
							+ "<div class='row' style='border-bottom: 1px solid #76d2814f; '><div class='col-md-12 row-data'><div class='col-md-4 pl0'>"
							+ vo.get(0).getDriverContactNoList().get(i).getDriverName()
							+ "</div><div class='col-md-4 row-data' >"
							+ vo.get(0).getDriverContactNoList().get(i).getAge()
							+ "</div><div class='col-md-4 dtrns43'>"
							+ vo.get(0).getDriverContactNoList().get(i).getContactNo() + "</div></div></div>";
				}
			}else{
				driverContactList = driverContactList+ "<div class='row' style='border-bottom: 1px solid #76d2814f; '><div class='col-md-12 row-data'>No Records Found</div></div>";
			}

			String heading4 = "<h4>Driver Details</h4>"
					+ "<div style='height: 84%; overflow: scroll;'><div class='vehicle' style='width:100%;' ><div><div  class='row subtable-header primarythemelightcolor' ><div  class='col-md-4'>Driver Name</div><div  class='col-md-4'> Age</div><div  class='col-md-4'>Contact Number</div></div></div>"
					+ "<div>" + driverContactList + "</div></div></div>";
			DashBoardList.add(heading4);
			request.setAttribute("Dashboard", DashBoardList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : storeAbsentSms Ending");

		return mapping.findForward("TransportLanding");
	}

	
	
	// dashboard for Transport (dynamic value append)
	public ActionForward getTransportValues(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: admission Starting");
		try {

			UserDetailVO usedetails = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String current_Location=(String) request.getSession(false).getAttribute("current_schoolLocation");

			String accyear = HelperClass.getCurrentYearID(userLoggingsVo);
			String accyearFace = HelperClass.getAcademicYearFace(accyear, userLoggingsVo);
			
			String flagName = request.getParameter("flag");
			
			System.out.println("flagName  : "+flagName);
		

			// 1.Transport Fee - 2018-2019

			List<FeeCollectionVo> vo = new TransportDaoImpl().getTransportfee(userLoggingsVo, accyear, current_Location,flagName);

			JSONObject obj = new JSONObject();
			obj.put("list", vo);
			obj.put("accyearFace", accyearFace);

			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : storeAbsentSms Ending");

		return null;
	}

	
	public ActionForward Event(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: Event Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_EVENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_EVENT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : Event Ending");

		return mapping.findForward("EventLanding");
	}

	public ActionForward Election(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: Election Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : Election Ending");

		return mapping.findForward("ElectionLanding");
	}

	public ActionForward Interaction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: Interaction Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SMS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : Interaction Ending");

		return mapping.findForward("InteractionLanding");
	}

	public ActionForward report(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: Report Starting");

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_REPORTS);

		try {
			
			UserDetailVO usedetails = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			HashMap<String, String> permission = usedetails.getPermissionmap();
			List<String> DashBoardList = new ArrayList<String>();

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String location_id = (String) request.getSession(false).getAttribute("current_schoolLocation");

			if (location_id != null && location_id.equalsIgnoreCase("all")) {
				location_id = "%%";
			}

			int total_staff;
			int present_staff;
			int absent_staff;

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false)
					.getAttribute("token_information");

			total_staff = Integer.parseInt(new TeacherAttendanceBD().getTotalStaffcount(userLoggingsVo, location_id));
			present_staff = Integer
					.parseInt(new TeacherAttendanceBD().getTotalPresentStaffcount(userLoggingsVo, location_id));
			absent_staff = Integer
					.parseInt(new TeacherAttendanceBD().getTotalAbsentStaffcount(userLoggingsVo, location_id));

			System.out.println(total_staff);
			System.out.println(present_staff);
			System.out.println(absent_staff);

			/*
			 * String
			 * heading="<h3 class='headerdashboardBox primarythemebackgound primarythemecolor'>Today's&nbsp; Staff Attendance</h3>"
			 * +
			 * "<div style='position:relative;height:162px;text-align:center;'>"
			 * +
			 * "<div style='height:100%;width:52px;background-color: #2bb58f;color: #fff !important;position:absolute;bottom:0;left: 8%;font-weight:bold;font-size:12px;color:#000;min-height: -1px;'>"
			 * + "<pre>" + "TOTAL" + "</pre>" + " "+total_staff + "</div>" +
			 * "<div style='height:"
			 * +present_percentage+"%;width:52px;background-color: #309245;color:#fff;position:absolute;bottom:0;left: 130px;font-size:12px;color: #fff;min-height: 33px;'>"
			 * + "<pre>" + "PRESENT" + "</pre>" + " "+present_staff + "</div>" +
			 * "<div style='height:"
			 * +absent_percentage+"%;width:52px;background-color: #cc2c33;color:#fff;position:absolute;bottom:0;left: 230px;font-size:12px;color: #fff;min-height: 33px;'>"
			 * + "<pre>" + "ABSENT" + "</pre>" + " "+absent_staff + "</div>" +
			 * "</div>"; DashBoardList.add(heading);
			 */

			String heading1 = "<div class='row'>"
					+ "<div class='col-md-8'><div id='c' style='width:450px;height:200px;margin-top: 27px;margin-left: 32px;'></div></div>"

					+ "</div>" + "<script type='text/javascript'>"

					+ "google.charts.load('current', {'packages':['corechart']});"
					+ "google.charts.setOnLoadCallback(drawChart);"

					// Draw the chart and set the chart values
					+ " function drawChart() {" + "	var data = google.visualization.arrayToDataTable(["
					+ "     ['Element', ' ', { role: 'style' } ],"
					+ "                                                ['Total Staff', " + total_staff
					+ " , '#b87333']," + "                                                ['Defaulter Student',"
					+ present_staff + ", 'silver'],"
					+ "                                                ['Total Collection'," + absent_staff
					+ ", 'gold']," + "                                                  ]);"

					// Optional; add a title and set the width and height of the
					// chart
					+ " var view = new google.visualization.DataView(data);"
					+ "  view.setColumns([0, 1, { calc: 'stringify',  sourceColumn: 1,type:'string',  role: 'annotation' },   2]);"
					+ "var options = {title: 'Staff Attendance Details', bar: {groupWidth: '50%'}, legend: { position: 'none' },};"

					// Display the chart inside the <div> element with
					// id="piechart"
					+ "var chart = new google.visualization.ColumnChart(document.getElementById('c'));"
					+ "chart.draw(view, options);" + "}" + "</script>";

			DashBoardList.add(heading1);

			int defaulter = Integer.parseInt(new ReportsMenuBD().getDefaulterStudentsCount(userLoggingsVo));
			double collection = Double.parseDouble(new ReportsMenuBD().getCollectionCount(userLoggingsVo));
			int student = Integer
					.parseInt(new ReportsMenuBD().getStudentCount(location_id, academic_year, userLoggingsVo));

			/*
			 * String
			 * heading1="<h3 class='headerdashboardBox primarythemebackgound primarythemecolor'>Student Fee</h3><div>"
			 * +
			 * "<div style='position:relative;height:162px;text-align:center;'>"
			 * +
			 * "<div style='height:100%;width:62px;background-color: #528c26;color: #fff !important;position:absolute;bottom:0;left: 8%;font-weight:bold;font-size:12px;color:#000;min-height: -1px;'>"
			 * + "<pre style='font-size: 8px;'>" + "TOTAL" + "</pre>" +
			 * " "+student + "</div>" + "<div style='height:"
			 * +"%;width:61px;background-color: #efcc21;color:#fff;position:absolute;bottom:0;left: 130px;font-size:12px;color: #fff;min-height: 33px;'>"
			 * + "<pre style='font-size: 8px;'>" + "Fee Defaulter" + "</pre>" +
			 * " " + defaulter + "</div>" + "<div style='height:"
			 * +collection_percentage+"%;width:63px;background-color: #466ddcf2;color:#fff;position:absolute;bottom:0;left: 230px;font-size:12px;color: #fff;min-height: 33px;'>"
			 * + "<pre style='font-size: 8px;'>" + "Fee Collection" + "</pre>" +
			 * " " +collection+" /-" + "</div>" + "</div>";
			 * DashBoardList.add(heading1);
			 * 
			 */

			String heading2 = "<div class='row'>" + "<div class='col-md-8'><div id='columnchart_values'></div></div>"

					+ "</div>" + "<script type='text/javascript'>"

					+ "google.charts.load('current', {'packages':['corechart']});"
					+ "google.charts.setOnLoadCallback(drawChart);"

					// Draw the chart and set the chart values
					+ " function drawChart() {" + "	var data = google.visualization.arrayToDataTable(["
					+ "     ['Element', ' ', { role: 'style' } ],"
					+ "                                                ['Total Student',  " + student + " , '#3366CC],"
					+ "                                                ['Defaulter Student'," + defaulter
					+ ", '#3366CC']," + "                                                ['Total Collection',"
					+ collection + ", '#DC3912']," + "                                                  ]);"

					// Optional; add a title and set the width and height of the
					// chart
					+ " var view = new google.visualization.DataView(data);"
					+ "  view.setColumns([0, 1, { calc: 'stringify',  sourceColumn: 1,type:'string',  role: 'annotation' },   2]);"
					+ "var options = {title: 'Student Fee Details', bar: {groupWidth: '50%'}, legend: { position: 'none' },};"

					// Display the chart inside the <div> element with
					// id="piechart"
					+ "var chart = new google.visualization.ColumnChart(document.getElementById('columnchart_values'));"
					+ "chart.draw(view, options);" + "}" + "</script>";

			DashBoardList.add(heading2);
			request.setAttribute("Dashboard", DashBoardList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : Report Ending");

		return mapping.findForward("ReportLanding");
	}

	public ActionForward SMS(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: Report Starting");

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SMS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SMS);
	
		try {
			String locid = (String) request.getSession(false).getAttribute("current_schoolLocation");
			List<String> DashBoardList = new ArrayList<String>();
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false)
					.getAttribute("token_information");
			BufferedWriter bw = null;
			FileWriter fw = null;
			BufferedReader br = null;
			FileReader fr = null;
			String FILENAME="";
			File file = new File(request.getRealPath("/")+ "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/theme.scss");
			if(file.exists()){
				 FILENAME =request.getRealPath("/")+ "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/theme.scss";
			}
			else{
				 FILENAME =request.getRealPath("/")+ "CSS/Theme/theme.scss";
			}
			
			
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;
			String themeBackground="";
			br = new BufferedReader(new FileReader(FILENAME));
			while ((sCurrentLine = br.readLine()) != null) {
				//.out.println(sCurrentLine);
				String sCurrentLineArray[] = sCurrentLine.split("}");
				int len=sCurrentLineArray[0].split(":")[1].length();
				themeBackground=sCurrentLineArray[0].split(":")[1].substring(0, len-1);
				

			}
		
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear").toString();
			
			String locId = locid;
			String heading1=null;
			LstmsUpcomingMeetingVO vo = new SmsDeligate().getSmsDetails(userLoggingsVo,accyear,locid);
			ResultSet rs = new SmsDeligate().getClassIdName(locId, userLoggingsVo,accyear);
			String heading2 =null;
			int count = 0;

			if (rs != null) {
				String heading = "<h4>Home Work SMS</h4>" + "<div class='row'>" + "<div class='col-md-12 boxdiv'>";
				while (rs.next()) {
					String smsCount = "0";
					ResultSet rst = new SmsDeligate().getClassSmsCount(locId, userLoggingsVo,accyear);
					while (rst.next()) {
						if (rst.getString("classid").equalsIgnoreCase(rs.getString("classdetail_id_int"))) {
							smsCount = rst.getString("total");
						}
					}

					heading += "<div class='col-md-2 smsdiv'>" + "<div class='blockdiv primarythemebackgound'>"
							+ "<p class = 'smsp primarythemecolor' id =" + rs.getString("classdetail_id_int") + ">"
							+ rs.getString("classdetails_name_var") + "</p>" + "<p class='smsp primarythemecolor'>"
							+ smsCount + "</p>" + "</div>" + "</div>";

					count++;
					if (count % 6 == 0) {
						heading += "</div><div class='col-md-12 boxdiv'>";
					}
					rst.close();
				}

				rs.close();
				heading += "</div>" + "</div>";
				DashBoardList.add(heading);
				request.setAttribute("Dashboard", DashBoardList);
			}

			// 2) Today SMS status till today
			
			if(vo.getTotalsms()>0){
				
				 heading1 = "<div class='row'>"
						 +"<h4>SMS package</h4>"
						+ "<div class='col-md-8'><div id='SMSstatus'></div></div>"

						+ "</div>" + "<script type='text/javascript'>"

						+ "google.charts.load('current', {'packages':['corechart']});"
						+ "google.charts.setOnLoadCallback(drawChart);"

						// Draw the chart and set the chart values
						+ " function drawChart() {" + "	var data = google.visualization.arrayToDataTable(["
						+ "     ['Element', '', { role: 'style' } ],"
						+ "     ['Total SMS', "+vo.getTotalsms()+", '"+themeBackground+"'],"  
						+"      ['Sent SMS', "+vo.getSuccesscount()+", '"+themeBackground+"'],"
						+ "     ['Remaining SMS',"+vo.getFailurecount()+", '"+themeBackground+"'], ]);"


						// Optional; add a title and set the width and height of the
						// chart
						+ " var view = new google.visualization.DataView(data);"
						+ "  view.setColumns([0, 1, { calc: 'stringify',  sourceColumn: 1,type:'string',  role: 'annotation' },   2]);"
						+ "var options = { bar: {groupWidth: '50%'}, legend: { position: 'none' },width:500,heigt:600};"

						// Display the chart inside the <div> element with
						// id="piechart"
						+ "var chart = new google.visualization.ColumnChart(document.getElementById('SMSstatus'));"
						+ "chart.draw(view, options);" + "}" + "</script>";

				
				
			}else{
				heading1 = "<h2 class='dashboard-else'>No SMS Transaction today</h2>";
			 }
			 DashBoardList.add(heading1);
			
			
			// 3) Class wise SMS Records
			rs = new SmsDeligate().getClassIdName(locId, userLoggingsVo,accyear);
			String body3 = "";

			while (rs.next()) {
				ArrayList<SmsCountVO> list1 = null;
				ArrayList<SmsCountVO> list2 = null;
				ArrayList<SmsCountVO> list3 = null;
				ArrayList<SmsCountVO> list4 = null;

				String absentCount = "0";
				String eventCount = "0";
				String lateComeCount = "0";
				String homeWorkCount = "0";

				list1 = new SmsDeligate().getAbsentSmsCount(userLoggingsVo);
				for (SmsCountVO smsCountVO : list1) {
					if (smsCountVO.getClassId().equalsIgnoreCase(rs.getString("classdetail_id_int"))) {
						absentCount = smsCountVO.getCount();
					}
				}
				list2 = new SmsDeligate().getEventSmsCount(userLoggingsVo);
				for (SmsCountVO smsCountVO : list2) {
					if (smsCountVO.getClassId().equalsIgnoreCase(rs.getString("classdetail_id_int"))) {
						eventCount = smsCountVO.getCount();
					}
				}
				list3 = new SmsDeligate().getLateComeSmsCount(userLoggingsVo, locId);

				for (SmsCountVO smsCountVO : list3) {
					if (smsCountVO.getClassId().equalsIgnoreCase(rs.getString("classdetail_id_int"))) {
						lateComeCount = smsCountVO.getCount();
					}
				}

				list4 = new SmsDeligate().getHomeworkSmsCount(locId, userLoggingsVo);
				for (SmsCountVO smsCountVO : list4) {
					if (smsCountVO.getClassId().equalsIgnoreCase(rs.getString("classdetail_id_int"))) {
						homeWorkCount = smsCountVO.getCount();
					}
				}
				body3 = body3
						+ "<div class='row content-row'><div class='col-md-12' style='text-align:center;scroll:overflow'><div class='col-md-2 row-data pl0'>"
						+ rs.getString("classdetails_name_var")
						+ "</div><div class='col-md-3 row-data dsms32'>" + homeWorkCount
						+ "</div><div class='col-md-3 row-data dsms33'>" + lateComeCount
						+ "</div><div class='col-md-2 row-data dsms34'>" + absentCount
						+ "</div><div class='col-md-2 row-data dsms35'>" + eventCount
						+ "</div></div></div>";
			}

			 heading2 = "<h4>Class Wise SMS Records</h4>"
					+ "<div style='overflow-y:scroll;height: 228px;'><div><div  class='row subtable-header'><div  class='col-md-2'>Class</div><div  class='col-md-3'>Homework</div><div  class='col-md-3'>Late Come</div><div  class='col-md-2'>Absent</div><div  class='col-md-2'>Events</div></div></div>"
					+ "<div>" + body3 + "</div></div>";
			DashBoardList.add(heading2);

			
			if(vo.getTodayTotalSms()>0){

				heading1 = "<div class='row'>"
						 +"<h4>Today SMS Status</h4>"
						+ "<div class='col-md-8'><div id='todaySMS'></div></div>"

						+ "</div>" + "<script type='text/javascript'>"

						+ "google.charts.load('current', {'packages':['corechart']});"
						+ "google.charts.setOnLoadCallback(drawChart);"

						// Draw the chart and set the chart values
						+ " function drawChart() {" + "	var data = google.visualization.arrayToDataTable(["
						+ "     ['Element', '', { role: 'style' } ],"
						+ "     ['Total Sent', "+vo.getTodayTotalSms()+", '"+themeBackground+"'],"  
						+"      ['Success SMS', "+vo.getTodaySuccessSms()+", '"+themeBackground+"'],"
						+ "     ['Failed SMS',"+vo.getTodayFailureSms()+", '"+themeBackground+"'], ]);"


						// Optional; add a title and set the width and height of the
						// chart
						+ " var view = new google.visualization.DataView(data);"
						+ "  view.setColumns([0, 1, { calc: 'stringify',  sourceColumn: 1,type:'string',  role: 'annotation' },   2]);"
						+ "var options = {title: '', bar: {groupWidth: '50%'}, legend: { position: 'none' },width:500,heigt:600};"

						// Display the chart inside the <div> element with
						// id="piechart"
						+ "var chart = new google.visualization.ColumnChart(document.getElementById('todaySMS'));"
						+ "chart.draw(view, options);" + "}" + "</script>";
				
			}else{
				heading1 = "<h2 class='dashboard-else'>No SMS Transaction today</h2>";
			 }
			 DashBoardList.add(heading1);
			
			request.setAttribute("Dashboard", DashBoardList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : Report Ending");

		return mapping.findForward("SMSLanding");
	}

	public ActionForward Library(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction: Report Starting");

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_LIBRARY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_LIBRARY);
     	request.setAttribute("DashboardJs", "JS/newUI/LibraryDashboard.js");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false)
					.getAttribute("token_information");			
			
			BufferedWriter bw = null;
			FileWriter fw = null;
			BufferedReader br = null;
			FileReader fr = null;
			String FILENAME="";
			File file = new File(request.getRealPath("/")+ "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/theme.scss");
			if(file.exists()){
				 FILENAME =request.getRealPath("/")+ "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/theme.scss";
			}
			else{
				 FILENAME =request.getRealPath("/")+ "CSS/Theme/theme.scss";
			}
			
			
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;
			String themeBackground="";
			br = new BufferedReader(new FileReader(FILENAME));
			while ((sCurrentLine = br.readLine()) != null) {
				//.out.println(sCurrentLine);
				String sCurrentLineArray[] = sCurrentLine.split("}");
				int len=sCurrentLineArray[0].split(":")[1].length();
				themeBackground=sCurrentLineArray[0].split(":")[1].substring(0, len-1);
				

			}
			
			String accYears = (String) request.getSession(false).getAttribute("current_academicYear");
			String currentLoc = (String) request.getSession(false).getAttribute("current_schoolLocation");
			System.out.println("currentLoc "+currentLoc);
			
			
			
			
			
			LibraryVO info = new LibraryDAOIMPL().getCountSubscribers(userLoggingsVo, accYears,currentLoc);
			String heading3 =null;

			
			List<String> DashBoardList = new ArrayList<String>();

			String heading1 = "<div class='row'>" + "<p class='sub-details'> Subscriber Details</p>"
					+ "<div class='sub-details-container'><div class='col-md-4'><div class='dynamic-circle teacher'><span class='badge zoom primarythemebackgound'>"
					+ info.getStudentCount() + "</span></div><label class='control-label student'>Student</label></div>"
					+ "<div class='col-md-4'><div class='dynamic-circle student'><span class='badge zoom primarythemebackgound'>"
					+ info.getTeacherCount() + "</span></div><label class='control-label student'>Staff</label></div>"
					+ "<div class='col-md-4'><div class='dynamic-circle others'><span class='badge zoom primarythemebackgound'>"
					+ info.getOthersCount() + "</span></div><label class='control-label student'>Others</label></div>"
					+ "</div></div>";

			DashBoardList.add(heading1);

			// -------------------------------------------------


			String heading2 = "<div class='row'>"
					+ "<div class='col-md-8'><div id='columnchart_values_today'></div></div>"

					+ "</div>" + "<script type='text/javascript'>"

					+ "google.charts.load('current', {'packages':['corechart']});"
					+ "google.charts.setOnLoadCallback(drawChart);"

					// Draw the chart and set the chart values
					+ " function drawChart() {" + "	var data = google.visualization.arrayToDataTable(["
					+ "     ['Element', '', { role: 'style' } ],"
					+ "                                                ['Issues', " + info.getIssueCount()
					+ ", '"+themeBackground+"']," + "                                                ['Returns', "
					+ info.getReturnCount() + ", '"+themeBackground+"'],"
					+ "                                                ['Reservations'," + info.getReservationCount()
					+ ", '"+themeBackground+"'],"

					+ "                                                  ]);"

					// Optional; add a title and set the width and height of the
					// chart
					+ " var view = new google.visualization.DataView(data);"
					+ "  view.setColumns([0, 1, { calc: 'stringify',  sourceColumn: 1,type:'string',  role: 'annotation' },   2]);"
					+ "var options = {title: 'Transactions as per day', bar: {groupWidth: '50%'}, legend: { position: 'none' },width:500,heigt:600};"

					// Display the chart inside the <div> element with
					// id="piechart"
					+ "var chart = new google.visualization.ColumnChart(document.getElementById('columnchart_values_today'));"
					+ "chart.draw(view, options);" + "}" + "</script>";

			DashBoardList.add(heading2);

/*			
			System.out.println("info.getReportSubscriber() @@@@@@@@@@@@@@@@@@@@@@@@@ "+info.getReportSubscriber());
			System.out.println("info.getReportSubscriber() @@@@@@@@@@@@@@@@@@@@@@@@@ "+info.getReportStock());
			System.out.println("info.getReportSubscriber() @@@@@@@@@@@@@@@@@@@@@@@@@ "+info.getReportOverdue());
			System.out.println("info.getReportSubscriber() @@@@@@@@@@@@@@@@@@@@@@@@@ "+info.getReportReservations());*/
			
			
			// -------------------------------------------------
			 if(info.getReportSubscriber()>0 || info.getReportReservations()>0 || info.getReportStock()>0 || info.getReportOverdue()>0){
			  heading3 = "<h3 style='background: #fff;'>Reports</h3>"
					
			+"<div class='row'>" + "<div class='col-md-8'><div id='libraryReports'></div></div>"

					+ "</div>" + "<script type='text/javascript'>"

					+ "google.charts.load('current', {'packages':['corechart']});"
					+ "google.charts.setOnLoadCallback(drawChart);"

					// Draw the chart and set the chart values
					+ " function drawChart() {" + "	var data = google.visualization.arrayToDataTable(["
					+ "  ['Task', 'Hours per Day'],"
					+ "                                                  ['Overdue', "+info.getReportOverdue()+"],"
					+ "                                                  ['Stock', "+info.getReportStock()+"],"
					+ "                                                  ['Reservation List',"+info.getReportReservations()+"],"
					+ "                                                  ['Subscriber List',"+info.getReportSubscriber()+"],"
					+ "                                                  ]);"

					// Optional; add a title and set the width and height of the
					// chart
					+ "  var options = {title: 'Reports','width':600, 'height':300};" // colour
																						// adjustment

					// Display the chart inside the <div> element with
					// id="piechart"
					+ " var chart = new google.visualization.PieChart(document.getElementById('libraryReports'));"
					+ "chart.draw(data, options);" + "}" + "</script>";

					 }else{
						 heading3 = "<div class='dashboard-content'><h4>Reports</h4></div><h2 class='dashboard-else'>No records to display</h2>";
						
					 }

			 
			 DashBoardList.add(heading3);
			// ------------------------------------------------------------------------------------------------------
		
			String heading4 = "<div class='row'>" 
					+"<div class='col-md-8'><div id='todayTransactions'></div></div>"

					+ "</div>" + "<script type='text/javascript'>"

					+ "google.charts.load('current', {'packages':['corechart']});"
					+ "google.charts.setOnLoadCallback(drawChart);"

					// Draw the chart and set the chart values
					+ " function drawChart() {" + "	var data = google.visualization.arrayToDataTable(["
					+ "     ['Element', '', { role: 'style' } ],"
					+ "     ['Issues', "+info.getTodayIssueCount()+", '"+themeBackground+"'],"  
					+"      ['Returns', "+info.getTodayReturnCount()+", '"+themeBackground+"'],"
					+ "     ['Reservations',"+info.getTodayReservationCount()+", '"+themeBackground+"'], ]);"

					// Optional; add a title and set the width and height of the
					// chart
					+ " var view = new google.visualization.DataView(data);"
					+ "  view.setColumns([0, 1, { calc: 'stringify',  sourceColumn: 1,type:'string',  role: 'annotation' },   2]);"
					+ "var options = {title: 'Transactions', bar: {groupWidth: '50%'}, legend: { position: 'none' },width:500,heigt:600};"

					// Display the chart inside the <div> element with
					// id="piechart"
					+ "var chart = new google.visualization.ColumnChart(document.getElementById('todayTransactions'));"
					+ "chart.draw(view, options);" + "}" + "</script>";
					
					

		
			DashBoardList.add(heading4);

			request.setAttribute("Dashboard", DashBoardList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in MenusAction : Report Ending");

		return mapping.findForward("LibraryLanding");
	}

}
