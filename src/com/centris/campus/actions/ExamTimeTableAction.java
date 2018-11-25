package com.centris.campus.actions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.daoImpl.ExamDetailsDaoImpl;
import com.centris.campus.daoImpl.ExamTimeTableDaoImpl;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.ExamTimeTableBD;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.forms.ExamDetailsForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;

public class ExamTimeTableAction extends DispatchAction {
	private static Logger logger = Logger.getLogger(ExamTimeTableAction.class);

	public ActionForward getAllSubjects(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableAction: getAllSubjects Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMTIMETABLE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String examclassiddetails = request.getParameter("classAndExamId");

			String[] examclassid = examclassiddetails.split(",");

			String classId = examclassid[0];
			String exam = examclassid[1];

			ExamTimetablePojo examinationList = new ExamTimetablePojo();
			
			examinationList = new ExamTimeTableBD().getExamDate(exam,userLoggingsVo);
			
			ArrayList<ExamTimetablePojo> subject_list = new ExamTimeTableBD().getExamTimeTableDetails(classId,exam,userLoggingsVo);
			
			ArrayList<ExamTimetablePojo> exam_list = new ArrayList<ExamTimetablePojo>();
			ExamTimetablePojo obj = new ExamTimetablePojo();
			obj.setClassId(classId);
			obj.setExamId(exam);
			exam_list.add(obj);

			request.setAttribute("subject_list", subject_list);
			request.setAttribute("exam_list", exam_list);
			request.setAttribute("examDate", examinationList);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableAction: getAllSubjects Ending");
		return mapping.findForward(MessageConstants.ADD_TIME_TABLE);
	}

	public ActionForward getExamDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableAction: getExamDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMTIMETABLE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute("examList",new ExamTimeTableBD().getExamdetails(userLoggingsVo));
			
			request.setAttribute("classList",new ExamTimeTableBD().getclassdetails(userLoggingsVo));

			if ("true".equalsIgnoreCase(request.getParameter("Status"))) {
				request.setAttribute("successMessage",
						MessageConstants.SUCCESSMSG);
			} else if ("false".equalsIgnoreCase(request.getParameter("Status"))) {
				request.setAttribute("errorMessage",
						MessageConstants.UNSUCCESSMSG);
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableAction: getExamDetails Ending");
		return mapping.findForward(MessageConstants.EXAM_TIME_TABLE);
	}

	public ActionForward saveExaminationClassMapping(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableAction : saveExaminationClassMapping  Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			String classid = request.getParameter("classId");
			String examid = request.getParameter("examId");

			String subid = request.getParameter("subjectid_str");
			String date = request.getParameter("date_str");
			String starttime = request.getParameter("time_str");
			String endtime = request.getParameter("end_time_str");
			String crreatedby = HelperClass.getCurrentUserID(request);

			String[] datearray = date.split(",");
			String[] subjectarray = subid.split(",");
			String[] timearray = starttime.split(",");
			String[] endtimearray = endtime.split(",");

			ArrayList<ExamTimetablePojo> examinationclassmappinglist = new ArrayList<ExamTimetablePojo>();

			for (int i = 0; i < subjectarray.length; i++) {

				ExamTimetablePojo voObj = new ExamTimetablePojo();
				voObj.setClassId(classid);
				voObj.setExamId(examid);

				voObj.setSubId(subjectarray[i]);

				voObj.setExamDate(HelperClass.convertUIToDatabase(datearray[i]));

				voObj.setExamStartTime(timearray[i]);

				voObj.setExamEndTime(endtimearray[i]);

				voObj.setCreatedBy(crreatedby);

				examinationclassmappinglist.add(voObj);

			}

			String status = new ExamTimeTableBD().saveExaminationClassMapping(examinationclassmappinglist,userLoggingsVo,log_audit_session);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableAction : saveExaminationClassMapping Ending");
		return null;
	}

	public ActionForward getEaxmListYear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMSETTING);

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			
			String academic_year = (String) request.getSession(false)
					.getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			//("current school Location:" + schoolLocation);
			String currentlocation = null;
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
				request.setAttribute("currentlocation", "All");
				request.setAttribute("locationId", schoolLocation);
			} else {
				currentlocation = new ExamDetailsBD().getlocationname(schoolLocation,userLoggingsVo);
				request.setAttribute("currentlocation", currentlocation);
			}
			request.setAttribute("locationId", schoolLocation);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails(userLoggingsVo);
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears(userLoggingsVo);
			request.setAttribute("accYearList", accYearList);
			
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historylocId", request.getParameter("historylocId"));

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Ending");

		return mapping.findForward(MessageConstants.EXAMLISTYEAR);
	}

	public ActionForward getEaxmTimeTableListYear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmTimeTableListYear Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMTIMETABLE);

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			//("current school Location:" + schoolLocation);
			String currentlocation = null;
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
				request.setAttribute("currentlocation", "All");
				request.setAttribute("locationId", schoolLocation);
			} else {
				currentlocation = new ExamDetailsBD().getlocationname(schoolLocation,userLoggingsVo);
				request.setAttribute("currentlocation", currentlocation);
			}
			request.setAttribute("locationId", schoolLocation);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails(userLoggingsVo);
			request.setAttribute("locationDetailsList",list);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("accYearList",accYearList);
			/*
			 * ArrayList<ReportMenuVo> classList = new
			 * ReportsMenuBD().getStudentClass(schoolLocation);
			 * request.setAttribute("classList", classList);
			 */
			
			request.setAttribute("historyaccyear", request.getParameter("historyaccyear"));
			request.setAttribute("historylocation", request.getParameter("historylocation"));

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmTimeTableListYear Ending");

		return mapping.findForward(MessageConstants.EXAM_TIMETABLE_LISTYEAR);
	}

	public ActionForward getaccyeardetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getaccyeardetails  Starting");
		try {

			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ExaminationDetailsVo> examTimeTableListYear = new ExamDetailsBD().examTimeTableListYear(accyear,location,userLoggingsVo);

			JSONObject json = new JSONObject();
			json.put("examTimeTableListYear", examTimeTableListYear);
			response.getWriter().print(json);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward setExamDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : setExamDetails Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMSETTING);
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
		
		String currentaccyear = request.getSession(false).getAttribute("current_academicYear").toString();

		String myValues = request.getParameter("myValue");
		
		/*String accyear = myValues.split(",")[0];
		String location = myValues.split(",")[1];*/
		
		String accyearid = myValues.split(",")[0];
		String location = myValues.split(",")[1];

		// getting startdate based on the academic year
		String startdate = Integer.toString(HelperClass.getPastDateofAcademicYear(accyearid,userLoggingsVo) + 1);

		// getting enddate based on the academic year
		String enddate = Integer.toString(HelperClass.getForDateofAcademicYear(accyearid,userLoggingsVo));
		ArrayList<ExaminationDetailsVo> examListYear = new ArrayList<ExaminationDetailsVo>();
		examListYear = new ExamTimeTableBD().getEaxmListYear(accyearid,userLoggingsVo);
		request.setAttribute("startDate", startdate);
		request.setAttribute("endDate", enddate);
		request.setAttribute("examListYear", examListYear);

		// For setting Academic year based on academic year id
		String accyear =  myValues.split(",")[0];
		String accyname = new ExamDetailsBD().getaccyName(accyearid,userLoggingsVo);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);

		// getting locname
		String currentlocation = new ExamDetailsBD().getlocationname(location,userLoggingsVo);
		request.setAttribute("locName", currentlocation);
		request.setAttribute("locid", location);
		
		request.setAttribute("historyacademicId", myValues.split(",")[2]);
		request.setAttribute("historylocId", myValues.split(",")[3]);

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : setExamDetails Ending");
		return mapping.findForward(MessageConstants.SETEXAMLIST);
	}

	public ActionForward getexamdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getexamdetails  Starting");
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String accyearid = request.getParameter("accyear");
		String location = request.getParameter("location");
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getexamsettingsDetails(accyearid,location,custdetails);
		request.setAttribute("examsettings", list);

		JSONObject object = new JSONObject();
		object.put("examsettings", list);
		response.getWriter().print(object);
		return null;
	}

	public ActionForward insertexamdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : insertexamdetails  Starting");
		
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		String user = HelperClass.getCurrentUserID(request);
		String academiyear = request.getParameter("academiyear");
		String examcode = request.getParameter("examcode");
		String examname = request.getParameter("examname");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String location = request.getParameter("location");
		String fromclassid = request.getParameter("fromclassid");
		String toClassId = request.getParameter("toClassId");
		String examtype = request.getParameter("examtype");
		String isapplicable = request.getParameter("isapplicable");
		

		ExamDetailsForm addExam = new ExamDetailsForm();
		addExam.setExamcode(examcode);
		addExam.setExamname(examname);
		addExam.setStartdate(startdate);
		addExam.setEnddate(enddate);
		addExam.setAccyear(academiyear);
		addExam.setUser(user);
		addExam.setLocationid(location);
		addExam.setFromClassId(fromclassid);
		addExam.setToClassId(toClassId);
		addExam.setExamtype(examtype);
		addExam.setIsapplicableperiodic(isapplicable);
		addExam.setCustid(custdetails);
		addExam.setLog_audit_session(log_audit_session);
		
		String addexam = new ExamTimeTableBD().insertExam(addExam);
		JSONObject object = new JSONObject();
		object.put("status", addexam);
		response.getWriter().print(object);
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : insertexamdetails  Ending");
		return null;
	}

	public ActionForward deleteExamSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Time Table Action : deleteExamSettings Starting");
		try{
			ExamDetailsForm addExam = new ExamDetailsForm();
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String deleteid = request.getParameter("examid");
			String location = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			addExam.setLog_audit_session(log_audit_session);
			addExam.setCustid(custdetails);
			String delteresult = new ExamTimeTableBD().deleteexamSettings(deleteid,location, accyear, addExam);
			JSONObject object = new JSONObject();
			object.put("status", delteresult);
			response.getWriter().print(object);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Time Table Action : deleteExamSettings Ending");
		return null;
	}

	public ActionForward editExamSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : editExamSettings  Starting");
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		String userid=HelperClass.getCurrentUserID(request);
		String examcode = request.getParameter("examcode");
		String examname = request.getParameter("examname");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String accyear = request.getParameter("academiyear");
		String location = request.getParameter("location");
		String fromclassid = request.getParameter("fromclassid");
		String examtype = request.getParameter("examtype");
		String isapplicable = request.getParameter("isapplicable");
		ExamDetailsForm editExam = new ExamDetailsForm();
		editExam.setExamcode(examcode);
		editExam.setExamname(examname);
		editExam.setStartdate(startdate);
		editExam.setEnddate(enddate);
		editExam.setAccyear(accyear);
		editExam.setLocationid(location);
		editExam.setFromClassId(fromclassid);
		editExam.setExamtype(examtype);
		editExam.setIsapplicableperiodic(isapplicable);
		editExam.setUserid(userid);
		editExam.setCustid(custdetails);
		editExam.setLog_audit_session(log_audit_session);
		
		String editid = request.getParameter("examid");
		String editresult = new ExamTimeTableBD().editexamsettings(editid,editExam);
		request.setAttribute("editexamsettings", editresult);
		JSONObject object = new JSONObject();
		object.put("status", editresult);
		response.getWriter().print(object);
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : editExamSettings  Ending");
		return null;
	}

	public ActionForward checkduplicateExamcode(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : checkduplicateExamcode  Starting");
		try {
		
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = request.getParameter("accyear");
			String examcode = request.getParameter("exmcode");
			String location = request.getParameter("location");
			String classid= request.getParameter("classid");

			String result = new ExamTimeTableBD().checkduplicateExam(accyear,examcode, location,custdetails,classid);

			JSONObject json = new JSONObject();
			json.put("status", result);
			response.getWriter().print(json);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : checkduplicateExamcode  Ending");
		return null;
	}

	public ActionForward getEaxmTimeTableClassList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmTimeTableClassList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			String accYear = request.getParameter("accYear");

			ArrayList<ExaminationDetailsVo> classTimeTableList = new ArrayList<ExaminationDetailsVo>();

			classTimeTableList = new ExamTimeTableBD()
					.getEaxmTimeTableClassList(accYear);

			request.setAttribute("startDate", classTimeTableList.get(0)
					.getStartDate());
			request.setAttribute("endDate", classTimeTableList.get(0)
					.getEndDate());
			request.setAttribute("classTimeTableList", classTimeTableList);
			request.setAttribute("accYear", classTimeTableList.get(0)
					.getAccyear());

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmTimeTableClassList Ending");

		return mapping.findForward(MessageConstants.EXAM_TIMETABLE_CLASSLIST);
	}

	public ActionForward setClassExamTimeTableDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getFeeSetupDetails Starting");

		String forward = "";
		try {

			String accYear = request.getParameter("accYear");
			String classId = request.getParameter("classId");
			ExamTimetablePojo exampojo = new ExamTimetablePojo();
			exampojo.setAccyearid(accYear);
			exampojo.setClassId(classId);

			ExamTimeTableBD classExamSetupBD = new ExamTimeTableBD();

			ArrayList<ExaminationDetailsVo> getClassExamWithOutSpecializationDetails = null;
			ArrayList<ExaminationDetailsVo> getHeading = null;

			try {

				getHeading = classExamSetupBD.getHeading(exampojo);
				if (getHeading.get(0).getSpecilazationCount() == 0) {

					getClassExamWithOutSpecializationDetails = classExamSetupBD
							.getClassExamTimeTableDetails(exampojo);
					request.setAttribute(
							"getClassExamWithOutSpecializationDetails",
							getClassExamWithOutSpecializationDetails);
					request.setAttribute("accYear",
							getClassExamWithOutSpecializationDetails.get(0)
									.getAccyear());
					request.setAttribute("className",
							getClassExamWithOutSpecializationDetails.get(0)
									.getClassname());

					forward = MessageConstants.SET_EXAMTIMETABLE_UPTO_TENTH;

				}
				/*
				 * else{ getSpecializationFeeMasterSetupDetails =
				 * feeSetupBD.getApprovedFees(feeSetupPojo);
				 * 
				 * 
				 * request.setAttribute("getSpecializationFeeMasterSetupDetails",
				 * getSpecializationFeeMasterSetupDetails);
				 * forward=MessageConstants.FEE_SETUP_ENTRY_WITH_SPECIALIZATION;
				 * }
				 */

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getFeeSetupDetails Ending");

		return mapping.findForward(forward);
	}

	public ActionForward setClassSubjectExamTimeTableDetails(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : setClassSubjectExamTimeTableDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			String accYear = request.getParameter("accYear");
			String classId = request.getParameter("classId");
			String examId = request.getParameter("examId");

			ExamTimetablePojo exampojo = new ExamTimetablePojo();
			exampojo.setAccyearid(accYear);
			exampojo.setClassId(classId);
			exampojo.setExamId(examId);

			ArrayList<ExaminationDetailsVo> classSubjectTimeTableList = new ArrayList<ExaminationDetailsVo>();

			classSubjectTimeTableList = new ExamTimeTableBD()
					.getEaxmTimeTableClassSubjectList(exampojo);

			request.setAttribute("classSubjectTimeTableList",
					classSubjectTimeTableList);

			request.setAttribute("accYear", classSubjectTimeTableList.get(0)
					.getAccyear());
			request.setAttribute("examCode", classSubjectTimeTableList.get(0)
					.getExamCode());
			request.setAttribute("examName", classSubjectTimeTableList.get(0)
					.getExamName());
			request.setAttribute("examStartDate", classSubjectTimeTableList
					.get(0).getExamstartdate());
			request.setAttribute("examEndDate", classSubjectTimeTableList
					.get(0).getExamenddate());
			request.setAttribute("className", classSubjectTimeTableList.get(0)
					.getClassname());
			request.setAttribute("sectionName", classSubjectTimeTableList
					.get(0).getSection());

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : setClassSubjectExamTimeTableDetails Ending");

		return mapping
				.findForward(MessageConstants.EXAM_TIMETABLE_CLASSSUBJECTLIST);
	}

	public ActionForward getExamMarksStudentwise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getExamMarksStudentwise Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSTUDENTWISE);

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			//String myValue=request.getParameter("myValue");
			
			String currentlocation = null;
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
				request.setAttribute("currentlocation", "All");
				request.setAttribute("locationId", schoolLocation);
			} else {
				currentlocation = new ExamDetailsBD().getlocationname(schoolLocation,userLoggingsVo);
				request.setAttribute("currentlocation", currentlocation);
			}
			request.setAttribute("locationId", schoolLocation);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails(userLoggingsVo);
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("accYearList", accYearList);
			
			//request.setAttribute("historyacayearId", myValue.split(",")[0]);
			//request.setAttribute("historylocId", myValue.split(",")[1]);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getExamMarksStudentwise Ending");
		
		return mapping.findForward(MessageConstants.EXAMARKS_STUDENTWISELIST);
	
	}

	public ActionForward setMarkEntryDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : setMarkEntryDetails Starting");
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSTUDENTWISE);
		String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		
		String myValues = request.getParameter("myValue");
		
		String accyear = myValues.split(",")[0];
		String hschoolLocation = myValues.split(",")[1];
		
		String accyname = new ExamDetailsBD().getaccyName(accyear,custdetails);
		
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableDaoImpl().getClassWiseExamListDetails(accyear,hschoolLocation,custdetails);
		
		request.setAttribute("examsettings", list);
		String currentlocation = new ExamDetailsBD().getlocationname(hschoolLocation,custdetails);
		request.setAttribute("currentlocation", currentlocation);
		request.setAttribute("locationid", hschoolLocation);
		
		request.setAttribute("historyacayearId", accyear);
		request.setAttribute("historylocId", hschoolLocation);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : setMarkEntryDetails Ending");
		return mapping.findForward(MessageConstants.SETMARK_ENTRY_DETAILS);

	}

	public ActionForward setMarkEntryStudentandClasswise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : setMarkEntryStudentandClasswise Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSTUDENTWISE);
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}

		String myValues = request.getParameter("formValue");
		
		String accyear = myValues.split(",")[0];
		String locid = myValues.split(",")[1];
		String examid = myValues.split(",")[2];
		String classid = myValues.split(",")[3];
		String custid = custdetails.getCustId();

		String accyname = new ExamDetailsBD().getaccyName(accyear,custdetails);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);


		String examname = new ExamDetailsBD().getexamName(examid, accyear,locid,custdetails);
		String examarray[] = examname.split(",");
		String examName = examarray[0];
		String examCode = examarray[1];

		request.setAttribute("examName", examName);
		request.setAttribute("examCode", examCode);
		request.setAttribute("examid", examid);
		//("classid is "+classid);
		/*if(classid.equals("CCD14") || classid.equals("CCD15")){
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getSubjectClassBySpec(accyear, examid, locid,classid,custid);
			request.setAttribute("subjectSpecClassList", list);

		}else{

		}*/
		ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getSubjectClass(accyear, examid, locid,classid,custdetails);
		request.setAttribute("subjectClassList", list);

		ArrayList<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
		list1 = new ExamTimeTableBD().getstatusexamsettingsDetails(accyear,schoolLocation,custdetails);
		request.setAttribute("examsettings", list1);

		String currentlocation = new ExamDetailsBD().getlocationname(locid,custdetails);
		//(currentlocation);
		request.setAttribute("currentlocation", currentlocation);
		request.setAttribute("locationid", locid);

		request.setAttribute("historyacayearId", accyear);
		request.setAttribute("historylocId", locid);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : setMarkEntryStudentandClasswise Ending");
		return mapping.findForward(MessageConstants.SETMARK_ENTRY_CLASSWISEDETAILS);

	}

	public ActionForward setMarkEntryStudentwise(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : setMarkEntryStudentwise  Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSTUDENTWISE);
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}

			String myValue=request.getParameter("myValue");
			//accyear+","+hschoolLocation+""+classId+","+sectionId+","+examid+","+specId
			String accyear = myValue.split(",")[0];
			String hschoolLocation =  myValue.split(",")[1];
			String classId =  myValue.split(",")[2];
			String sectionId = myValue.split(",")[3];
			String examid =  myValue.split(",")[4];
			//String specilizationId =  myValue.split(",")[5];
			
			String custid=custdetails.getCustId();
			//String specializationname=HelperClass.getSepecializationName(classId,sectionId,hschoolLocation,specilizationId,custdetails);
			String sectionname=HelperClass.getSectionName(classId, sectionId,hschoolLocation,custdetails);
			ExaminationDetailsVo obj = new ExaminationDetailsVo();

			obj.setAccyearid(accyear);
			obj.setExamid(examid);
			obj.setSection(sectionId);
			obj.setClassId(classId);
			obj.setLocationid(hschoolLocation);
			//obj.setSpecializationId(specilizationId);
			obj.setCustid(custid);
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getexamclassDetails(obj,custdetails);
			request.setAttribute("examid", examid);
			request.setAttribute("classId", classId);
			request.setAttribute("sectionId", sectionId);
		//	request.setAttribute("specilizationId", specilizationId);
			request.setAttribute("accyear", accyear);
			request.setAttribute("accyName", list.get(0).getAccyear());
			request.setAttribute("classname", list.get(0).getClassname());
			//if(specilizationId.equals("-")){
			request.setAttribute("sectionname", sectionname);
			/*}else{
				request.setAttribute("sectionname",specializationname);
			}*/
			
			request.setAttribute("examcode", list.get(0).getExamCode());
			request.setAttribute("examname", list.get(0).getExamName());
			request.setAttribute("startdate", list.get(0).getStartDate());
			request.setAttribute("enddate", list.get(0).getEndDate());

			ArrayList<ExaminationDetailsVo> studentlist = new ArrayList<ExaminationDetailsVo>();
			studentlist = new ExamDetailsBD().classWiseStudent(obj,custdetails);
			//("classwise student:" + studentlist);

			request.setAttribute("studentlist", studentlist);
			String currentlocation = new ExamDetailsBD().getlocationname(hschoolLocation,custdetails);
			request.setAttribute("currentlocation", currentlocation);
			request.setAttribute("locationid", hschoolLocation);
			
			request.setAttribute("historyacayearId", accyear);
			request.setAttribute("historylocId", hschoolLocation);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : setMarkEntryStudentwise  Ending");
		return mapping.findForward(MessageConstants.SETMARK_ENTRY_STUDENTWISE);
	}

	public ActionForward AddMarkEntryStudentWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : AddMarkEntryStudentWise  Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSTUDENTWISE);
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}
			
			String myValue=request.getParameter("myValue");
			String accyear = myValue.split(",")[0];
			String hschoolLocation = myValue.split(",")[1];
			String examid = myValue.split(",")[2];
			String classId = myValue.split(",")[3];
			String sectionId = myValue.split(",")[4];
			String admissionno = myValue.split(",")[5];
			String studentname = myValue.split(",")[6];
			String gstid = myValue.split(",")[7];
			String specializationId = myValue.split(",")[8];
			
			
			String custid = custdetails.getCustId();
			String specializationname=HelperClass.getSepecializationName(classId,sectionId,hschoolLocation,specializationId,custdetails);
			String sectionname=HelperClass.getSectionName(classId, sectionId, hschoolLocation,custdetails);
			request.setAttribute("admno", admissionno);
			request.setAttribute("stdname", studentname);
			ExaminationDetailsVo obj = new ExaminationDetailsVo();
			obj.setAccyearid(accyear);
			obj.setExamid(examid);
			obj.setSection(sectionId);
			obj.setClassId(classId);
			obj.setLocationid(hschoolLocation);
			obj.setSpecializationId(specializationId);
			obj.setCustid(custid);
			obj.setStudentid(gstid);
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getexamclassDetails(obj,custdetails);
			request.setAttribute("examid", examid);
			request.setAttribute("classId", classId);
			request.setAttribute("sectionId", sectionId);
			request.setAttribute("accyear", accyear);
			request.setAttribute("accyName", list.get(0).getAccyear());
			request.setAttribute("classname", list.get(0).getClassname());
			request.setAttribute("specilizationId", specializationId);
			if(specializationId.equals("-")){
				request.setAttribute("sectionname", sectionname);
			}else{
				request.setAttribute("sectionname",specializationname);
			}
			request.setAttribute("examcode", list.get(0).getExamCode());
			request.setAttribute("examname", list.get(0).getExamName());
			request.setAttribute("startdate", list.get(0).getStartDate());
			request.setAttribute("enddate", list.get(0).getEndDate());
			request.setAttribute("workedu_marks", list.get(0).getWorkedu_grade());
			request.setAttribute("artedu_marks", list.get(0).getArtedu_grade());
			request.setAttribute("healthedu_marks", list.get(0).getHealthedu_grade());
			request.setAttribute("discipline_marks", list.get(0).getDisciplinedu_grade());
			request.setAttribute("examtypeprefix", list.get(0).getExamtypeprefix());
			String examtypeprefix=(String) request.getAttribute("examtypeprefix");
			//("examtypeprefix"+examtypeprefix);

			ArrayList<ExaminationDetailsVo> studentlist = new ArrayList<ExaminationDetailsVo>();
			studentlist = new ExamDetailsBD().classWiseStudent(obj,custdetails);

			request.setAttribute("admissionno", studentlist.get(0).getAdmissionno());
			request.setAttribute("studentname", studentlist.get(0).getStudentname());
			request.setAttribute("studentid", studentlist.get(0).getStudentid());
			request.setAttribute("studentlist", studentlist);
			String classId1 = classId;
			String hschoolLocation1 = hschoolLocation;
			String studentid = gstid;
			ExaminationDetailsVo obj1 = new ExaminationDetailsVo();
			obj1.setClassid(classId1);
			obj1.setLocationid(hschoolLocation1);
			obj1.setStudentid(studentid);
			obj1.setExamid(examid);
			obj1.setExamtypeprefix(examtypeprefix);
			obj1.setAccyearid(accyear);
			obj1.setSpecializationId(specializationId);
			obj1.setCustid(custid);
			ArrayList<ExaminationDetailsVo> resultlist = new ExamDetailsBD().getStudentDetails(obj1,custdetails);
			ArrayList<ExaminationDetailsVo> lablist=null;
			System.out.println("examtypeprefix "+examtypeprefix);
			if(examtypeprefix.equals("yrlym")){
				lablist = new ExamDetailsDaoImpl().getStudentLabDetails(resultlist,custdetails,classId1,hschoolLocation1,studentid,examid,accyear,obj1);
				request.setAttribute("labdetailslist", lablist);
			}
			
			request.setAttribute("markdetailslist", resultlist);
			
			String currentlocation = new ExamDetailsBD().getlocationname(hschoolLocation,custdetails);
			request.setAttribute("currentlocation", currentlocation);
			request.setAttribute("locationid", hschoolLocation);
			request.setAttribute("hiddenstudentid", gstid);
			request.setAttribute("historyacayearId",myValue.split(",")[0]);
			request.setAttribute("historylocId", myValue.split(",")[1]);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : AddMarkEntryStudentWise  Ending");
		return mapping.findForward(MessageConstants.ADDMARK_ENTRY_STUDENTWISE);

	}

	public ActionForward SaveMarkEntryStudentWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : SaveMarkEntryStudentWise  Starting");
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		String custid=userLoggingsVo.getCustId();
		String createdby = HelperClass.getCurrentUserID(request);
		String subids[] = request.getParameterValues("subids[]");
		String statusvalues[] = request.getParameterValues("statusvalues[]");
		String scoremarks[] = request.getParameterValues("scoredmarks[]");
		String primaryidstu[] = request.getParameterValues("primaryidstu[]");
		String notebookmarks[] = request.getParameterValues("notebookmarks[]");
		String subjectenrichment[] = request.getParameterValues("subjectenrichment[]");
		String maxnotebookmarks[] = request.getParameterValues("maxnotebookmarks[]");
		String maxsubjectenrichmarks[] = request.getParameterValues("maxsubjectenrichmarks[]");
		String maxscoredmarks[] = request.getParameterValues("maxscoredmarks[]");
		String maxperiodicmarks[] = request.getParameterValues("maxperiodicmarks[]");
		String periodicscoredmarks[] = request.getParameterValues("periodicscoredmarks[]");
		String accyear = request.getParameter("accyear");
		String examid = request.getParameter("examid");
		String classId = request.getParameter("classId");
		String sectionId = request.getParameter("sectionId");
		String studentid = request.getParameter("studentid");
		String locationid = request.getParameter("hschoolLocation");
		String studentname = request.getParameter("studentname");
		String workedu = request.getParameter("workedu");
		String artedu = request.getParameter("artedu");
		String disciplineedu = request.getParameter("disciplineedu");
		String healthedu = request.getParameter("healthedu");
		String examtypeprfix = request.getParameter("examtypeprfix");
		ExaminationDetailsVo obj = new ExaminationDetailsVo();
		obj.setAccyear(accyear);
		obj.setExamid(examid);
		obj.setClassid(classId);
		obj.setSectionid(sectionId);
		obj.setSubid(subids);
		obj.setScoremarks(scoremarks);
		obj.setStatusvalues(statusvalues);
		obj.setStudentname(studentname);
		obj.setStudentid(studentid);
		obj.setLocationid(locationid);
		obj.setStuprimaryid(primaryidstu);
		obj.setNotebookmarks(notebookmarks);
		obj.setSubjectenrichment(subjectenrichment);
		obj.setMaxnotebookmarks(maxnotebookmarks);
		obj.setMaxsubenrichmentmarks(maxsubjectenrichmarks);
		obj.setMax_scored_marks(maxscoredmarks);
		obj.setWorkedu_grade(workedu);
		obj.setArtedu_grade(artedu);
		obj.setDisciplinedu_grade(disciplineedu);
		obj.setHealthedu_grade(healthedu);
		obj.setCreatedBy(createdby);
		obj.setMaxperiodicmarks(maxperiodicmarks);
		obj.setPeriodicscoredmarks(periodicscoredmarks);
		obj.setExamtypeprefix(examtypeprfix);
		obj.setCustid(custid);
		obj.setLog_audit_session(log_audit_session);
		
		String result = null;
		result = new ExamDetailsBD().insertmarkentrydetails(obj,userLoggingsVo);
		JSONObject object = new JSONObject();
		object.put("status", result);
		response.getWriter().print(object);
		String currentlocation = new ExamDetailsBD()
				.getlocationname(schoolLocation,userLoggingsVo);
		request.setAttribute("currentlocation", currentlocation);
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : SaveMarkEntryStudentWise  Ending");
		return null;
	}

	public ActionForward subjectmarksList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Time Table Action : subjectmarksList Starting");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSUBJECTWISE);

			String academic_year = (String) request.getSession(false)
					.getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);

			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			//("current school Location:" + schoolLocation);
			String currentlocation = null;
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
				request.setAttribute("currentlocation", "All");
				request.setAttribute("locationId", schoolLocation);
			} else {
				currentlocation = new ExamDetailsBD().getlocationname(schoolLocation,userLoggingsVo);
				request.setAttribute("currentlocation", currentlocation);
			}
			request.setAttribute("locationId", schoolLocation);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails(userLoggingsVo);
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("accYearList", accYearList);
			
			request.setAttribute("historyacayear", request.getParameter("historyacayear"));
			request.setAttribute("historylocation",request.getParameter("historylocation"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("subjetmarkList");
	}

	public ActionForward getExamCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		ArrayList<ExaminationDetailsVo> examcodes = new ExamDetailsBD()
				.getlistofExamCodes(schoolLocation);
		return null;
	}

	public ActionForward ExamDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		
		String currentaccyear = request.getSession(false).getAttribute("current_academicYear").toString();
		// getting startdate based on the academic year
		String startdate = Integer.toString(HelperClass.getPastDateofAcademicYear(currentaccyear,custdetails) + 1);
		// getting enddate based on the academic year
		String enddate = Integer.toString(HelperClass
				.getForDateofAcademicYear(currentaccyear,custdetails));
		ArrayList<ExaminationDetailsVo> examListYear = new ArrayList<ExaminationDetailsVo>();
		examListYear = new ExamTimeTableBD().getEaxmListYear(currentaccyear,custdetails);
		request.setAttribute("startDate", startdate);
		request.setAttribute("endDate", enddate);
		request.setAttribute("examListYear", examListYear);
		// For setting Academic year based on academic year id
		String accyear = request.getParameter("accyear");
		String accyname = new ExamDetailsBD().getaccyName(currentaccyear,custdetails);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getexamsettingsDetails(currentaccyear,schoolLocation,custdetails);
		request.setAttribute("examsettings", list);

		JSONObject obj = new JSONObject();
		obj.put("examsettings", list);
		response.getWriter().print(obj);
		return null;
	}

	public ActionForward getexamtimetableclass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try{
			
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMTIMETABLE);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		String myValues = request.getParameter("myValue");
		
		
		String accyear = myValues.split(",")[0];
		String locid = myValues.split(",")[1];

		String currentlocation = new ExamDetailsBD().getlocationname(locid,custdetails);
		request.setAttribute("location", currentlocation);
		String accyname = new ExamDetailsBD().getaccyName(accyear,custdetails);
		request.setAttribute("accyName", accyname);

		request.setAttribute("accyearid", accyear);
		request.setAttribute("locid", locid);
		
		request.setAttribute("historyaccyear", myValues.split(",")[0]);
		request.setAttribute("historylocation", myValues.split(",")[1]);
		//request.setAttribute("historyexam", request.getParameter("historyexam"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward(MessageConstants.EXAM_TIME_TABLE_CLASS);
	}

	public ActionForward getexamtimetableclasssection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMTIMETABLE);
		String myValues = request.getParameter("myValue1");
		String accyear = myValues.split(",")[0];
		String locid = myValues.split(",")[1];
		String classid = myValues.split(",")[2];
		String examid = myValues.split(",")[3];
		String currentlocation = new ExamDetailsBD().getlocationname(locid,custdetails);
		request.setAttribute("location", currentlocation);
		String accyname = new ExamDetailsBD().getaccyName(accyear,custdetails);
		request.setAttribute("accyName", accyname);

		request.setAttribute("classid", classid);
		request.setAttribute("accyearid", accyear);
		request.setAttribute("locid", locid);
		request.setAttribute("classname", HelperClass.getClassName(classid, locid, custdetails));
		request.setAttribute("examid", examid);
		List<ExaminationDetailsVo> ClassList = new ExamDetailsBD()
				.getExamClassByLocation(locid,accyear,examid,custdetails);
		request.setAttribute("ClassList", ClassList);
		
		request.setAttribute("historyaccyear", request.getParameter("historyaccyear"));
		request.setAttribute("historylocation", request.getParameter("historylocation"));
		request.setAttribute("historysection", request.getParameter("historysection")); 
		request.setAttribute("historyexam", request.getParameter("historyexam"));
		
		
		return mapping.findForward(MessageConstants.EXAM_TIME_TABLE_CLASS_SECTION);
	}

	public ActionForward getexamlistbyclass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		try{
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			 
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}
	
			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classid = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String examid = request.getParameter("examid");
			
			if(sectionid==null){
				sectionid="%%";
			}else if(sectionid.equalsIgnoreCase("all")){
				sectionid="%%";
			}
			
			/*
			 * String currentlocation =new ExamDetailsBD().getlocationname(locid);
			 * request.setAttribute("location",currentlocation); String accyname
			 * =new ExamDetailsBD().getaccyName(accyear);
			 * request.setAttribute("accyName",accyname);
			 */
			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(location);
			pojo.setAccyearid(accyear);
			pojo.setClassId(classid);
			pojo.setSectionid(sectionid);
			pojo.setExamId(examid);
			List<ExaminationDetailsVo> examlist = new ExamDetailsBD().getexamlistbyclass(pojo,userLoggingsVo);
	
			JSONObject obj = new JSONObject();
			obj.put("examlist", examlist);
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward dispalyexamsavepage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMTIMETABLE);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String myValues = request.getParameter("myValue2");
			
			String accyear = myValues.split(",")[0];
			String locid = myValues.split(",")[1];
			String classid = myValues.split(",")[2];
			String section = myValues.split(",")[4];
			String examid = myValues.split(",")[3];
			
			String currentlocation = new ExamDetailsBD().getlocationname(locid,custdetails);
			request.setAttribute("location", currentlocation);
			String accyname = new ExamDetailsBD().getaccyName(accyear,custdetails);
			request.setAttribute("accyName", accyname);

			request.setAttribute("locid", locid);
			request.setAttribute("accyear", accyear);
			request.setAttribute("classid", classid);
			request.setAttribute("section", section);
			request.setAttribute("examid", examid);

			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(locid);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);
			pojo.setSectionid(section);

			ExaminationDetailsVo examdetails = new ExamDetailsBD().getexamdetails(pojo,custdetails);
			request.setAttribute("examdetails", examdetails);
			
			int minDate = HelperClass.totalDaysBetweenTwoDates(HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString()), examdetails.getExamstartdate());
			request.setAttribute("minDate", minDate);
			int maxDate = HelperClass.totalDaysBetweenTwoDates(HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString()), examdetails.getExamenddate());
			request.setAttribute("maxDate", maxDate);
			
			request.setAttribute("historyaccyear",myValues.split(",")[5]);
			request.setAttribute("historylocation", myValues.split(",")[6]);
			request.setAttribute("historysection", myValues.split(",")[7]);
			request.setAttribute("historyexam", myValues.split(",")[8]);
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(MessageConstants.EXAM_TIME_TABLE_DISPLAY_SAVE);
	}

	public ActionForward getsubdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			

			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classid = request.getParameter("classid");
			String examid = request.getParameter("examid");

			request.setAttribute("locid", location);
			request.setAttribute("accyear", accyear);
			request.setAttribute("classid", classid);
			request.setAttribute("examid", examid);

			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(location);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);

			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD()
					.getsubdetails(pojo,userLoggingsVo);

			JSONObject obj = new JSONObject();
			obj.put("sublist", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward savetimetabledetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			//("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classid");
			String examid = request.getParameter("examid");
			String sectionid = request.getParameter("sectionid");

			System.out.println("SECTIOn ID "+sectionid);
			
			if (sectionid.equalsIgnoreCase("all")) {
				sectionid = "%%";
			}

			String subid1[] = request.getParameterValues("subid1[]");
			String starttime1[] = request.getParameterValues("starttime1[]");
			String endtime1[] = request.getParameterValues("endtime1[]");
			String startdate[] = request.getParameterValues("startdate[]");
			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(locid);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);
			pojo.setSectionid(sectionid);
			pojo.setLog_audit_session(log_audit_session);
			
			String list = new ExamDetailsBD().savetimetabledetails(pojo,subid1,starttime1,endtime1,startdate,userLoggingsVo);

			JSONObject obj = new JSONObject();
			obj.put("status", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward updatetimetabledetailsset(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			//("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classid");
			String examid = request.getParameter("examid");
			String sectionid = request.getParameter("sectionid");
			String ttid = request.getParameter("timetable");
			if (sectionid.equalsIgnoreCase("all")) {
				sectionid = "%%";
			}

			String subid1[] = request.getParameterValues("subid1[]");
			String starttime1[] = request.getParameterValues("starttime1[]");
			String endtime1[] = request.getParameterValues("endtime1[]");
			String startdate[] = request.getParameterValues("startdate[]");
			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(locid);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);
			pojo.setSectionid(sectionid);
			pojo.setTimetableid(ttid);
			String list = new ExamDetailsBD().updatetimetabledetailsset(pojo,
					subid1, starttime1, endtime1, startdate);

			JSONObject obj = new JSONObject();
			obj.put("status", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward getexamsbtselection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ArrayList<ExaminationDetailsVo> examlist = new ExamDetailsBD()
					.getexamsbtselection(accyear,locid,userLoggingsVo);

			JSONObject obj = new JSONObject();
			obj.put("examlist", examlist);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward disstumarksdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : disstumarksdetails  Starting");
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		try {

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");

			ArrayList<ExaminationDetailsVo> examListYear = new ArrayList<ExaminationDetailsVo>();
			examListYear = new ExamTimeTableBD().getEaxmMarksListYear(accyear,locid,userLoggingsVo);

			JSONObject obj = new JSONObject();
			obj.put("markslist", examListYear);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : disstumarksdetails  Ending");
		return null;
	}

	public ActionForward displaysubmarkslist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : displaysubmarkslist  Starting");
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");

			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().getAccYearsSubject(accyear,locid,userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);

			JSONObject obj = new JSONObject();
			obj.put("examlist", accYearList);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : displaysubmarkslist  Starting");
		return null;
	}

	public ActionForward getclasslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");
			String examcode = request.getParameter("examcode");

			List<ExaminationDetailsVo> ClassList = new ArrayList<ExaminationDetailsVo>();

			/*if (examcode == "" || examcode == null) {
				ClassList = new ExamDetailsBD().getExamClassByLocation(locid,accyear,userLoggingsVo);
				request.setAttribute("ClassList", ClassList);
			} else {
				ClassList = new ExamDetailsBD().getExamClassByLocation(locid,accyear,examcode,userLoggingsVo);
				request.setAttribute("ClassList", ClassList);
			}*/

			ClassList = new ExamDetailsBD().getExamClassByLocation(locid,accyear,userLoggingsVo);
			request.setAttribute("ClassList", ClassList);
			
			JSONObject obj = new JSONObject();
			obj.put("ClassList", ClassList);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward checkduplicatedate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Time Table Action : checkduplicatedate Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");
			String location = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classid = request.getParameter("classid");
			String custid = custdetails.getCustId();

			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setExamStartdate(HelperClass.convertUIToDatabase(startdate));
			pojo.setExamEndDate(HelperClass.convertUIToDatabase(enddate));
			pojo.setLocid(location);
			pojo.setAccyearid(accyear);
			pojo.setCustid(custdetails);
			pojo.setClassId(classid);
			String duplicate = new ExamDetailsBD().checkduplicatedate(pojo);
			JSONObject obj = new JSONObject();
			obj.put("status", duplicate);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Time Table Action : checkduplicatedate Ending");
		return null;
	}

	public ActionForward dispalyexamsavepagedata(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
               request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMTIMETABLE);
			
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			//("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String myValues = request.getParameter("myValue1");
			
			String accyear =myValues.split(",")[0];
			String locid =myValues.split(",")[1];
			String classid =myValues.split(",")[2];
			String section = myValues.split(",")[4];
			String examid = myValues.split(",")[3];
			String timetableid = myValues.split(",")[5];
			//(section);
			String currentlocation = new ExamDetailsBD().getlocationname(locid,custdetails);
			request.setAttribute("location", currentlocation);
			String accyname = new ExamDetailsBD().getaccyName(accyear,custdetails);
			request.setAttribute("accyName", accyname);

			request.setAttribute("locid", locid);
			request.setAttribute("accyear", accyear);
			request.setAttribute("classid", classid);
			request.setAttribute("section", section);
			request.setAttribute("examid", examid);
			request.setAttribute("timetableid", timetableid);
			
			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(locid);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);
			pojo.setSectionid(section);
			pojo.setTimetableid(timetableid);
			
			ExaminationDetailsVo examdetails = new ExamDetailsBD().getexamdetailsbyset(pojo,custdetails);
			
			request.setAttribute("examdetails", examdetails);
			
			int minDate = HelperClass.totalDaysBetweenTwoDates(HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate()
							.toString()), examdetails.getExamstartdate());
			//(minDate);
			
			//(HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString()));
			
			request.setAttribute("minDate", minDate);
			
			int maxDate = HelperClass.totalDaysBetweenTwoDates(HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate()
							.toString()), examdetails.getExamenddate());
			
			request.setAttribute("maxDate", maxDate);
			
			request.setAttribute("historyaccyear", myValues.split(",")[6]);
			request.setAttribute("historylocation",myValues.split(",")[7]);
			request.setAttribute("historysection", myValues.split(",")[8]);
			request.setAttribute("historyexam", myValues.split(",")[9]);
			
			//(maxDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(MessageConstants.EXAM_TIME_TABLE_DISPLAY_SAVE_PAGE);
	}

	public ActionForward getsubdetailsset(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			//("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}

			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");
			String classid = request.getParameter("classid");
			String examid = request.getParameter("examid");
			String timetableid = request.getParameter("timetableid");

			request.setAttribute("locid", location);
			request.setAttribute("accyear", accyear);
			request.setAttribute("classid", classid);
			request.setAttribute("examid", examid);
			request.setAttribute("timetableid", timetableid);

			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(location);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);
			pojo.setTimetableid(timetableid);
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getsubdetailsset(pojo,custdetails);
		
			JSONObject obj = new JSONObject();
			obj.put("sublist", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward savetimetabledetailsset(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			//("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classid");
			String examid = request.getParameter("examid");
			String sectionid = request.getParameter("sectionid");
			String timetableid = request.getParameter("timetable");

			

			String subid1[] = request.getParameterValues("subid1[]");
			
			
			/*String subcode1[] = request.getParameterValues("subcode1[]");
			String subname1[] = request.getParameterValues("subname1[]");*/
			String starttime1[] = request.getParameterValues("starttime1[]");
			String endtime1[] = request.getParameterValues("endtime1[]");
			String startdate[] = request.getParameterValues("startdate[]");
			ExamTimetablePojo pojo = new ExamTimetablePojo();
			pojo.setLocid(locid);
			pojo.setAccyearid(accyear);
			pojo.setExamId(examid);
			pojo.setClassId(classid);
			pojo.setSectionid(sectionid);
			pojo.setTimetableid(timetableid);
			pojo.setLog_audit_session(log_audit_session);
			String list = new ExamDetailsBD().savetimetabledetailsset(pojo,subid1, starttime1, endtime1, startdate,custdetails);

			JSONObject obj = new JSONObject();
			obj.put("status", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward examdependency(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Starting");

		try {
			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String academic_year = (String) request.getSession(false)
					.getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			String currentlocation = null;
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
				request.setAttribute("currentlocation", "All");
				request.setAttribute("locationId", schoolLocation);
			} else {
				currentlocation = new ExamDetailsBD()
						.getlocationname(schoolLocation,userLoggingsVo);
				request.setAttribute("currentlocation", currentlocation);
			}
			request.setAttribute("locationId", schoolLocation);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails(userLoggingsVo);
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("accYearList", accYearList);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME , LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMDEPENDANCY);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Ending");
		return mapping.findForward(MessageConstants.EXAMDEPENDENCY);
	}

	public ActionForward setExamDependency(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME ,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMDEPENDANCY);
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String currentaccyear = request.getSession(false)
				.getAttribute("current_academicYear").toString();

		String accyearid = request.getParameter("accyear");
		String location = request.getParameter("location");

		// getting startdate based on the academic year
		String startdate = Integer.toString(HelperClass
				.getPastDateofAcademicYear(accyearid,dbdetails) + 1);

		// getting enddate based on the academic year
		String enddate = Integer.toString(HelperClass
				.getForDateofAcademicYear(accyearid,dbdetails));
		ArrayList<ExaminationDetailsVo> examListYear = new ArrayList<ExaminationDetailsVo>();
		examListYear = new ExamTimeTableBD().getEaxmListYear(currentaccyear,dbdetails);
		request.setAttribute("startDate", startdate);
		request.setAttribute("endDate", enddate);
		request.setAttribute("examListYear", examListYear);

		// For setting Academic year based on academic year id
		String accyear = request.getParameter("accyear");
		String accyname = new ExamDetailsBD().getaccyName(accyear,dbdetails);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);

		// getting locname
		String currentlocation = new ExamDetailsBD().getlocationname(location,dbdetails);
		request.setAttribute("locName", currentlocation);
		request.setAttribute("locid", location);

		return mapping.findForward(MessageConstants.SETEXAMDEPENDENCY);
	}

	public ActionForward getExamcodeForDependency(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableAction: getExamDetails Starting");
		try {
			
			String locid = request.getParameter("locid");
			String accYear = request.getParameter("accYear"); 
			String startdate = request.getParameter("stDate"); 
			String enddate = request.getParameter("enDate"); 
			String examCode = request.getParameter("exCode"); 
			
			ArrayList<ExamTimetablePojo>  examList =new ExamTimeTableBD().getExamcodeForDependency(locid,accYear,startdate,enddate,examCode);

			JSONObject json = new JSONObject();

			json.put("exam_Lists",examList);

			response.getWriter().print(json);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableAction: getExamDetails Ending");
		return null;
	}

	public ActionForward gradeDependency(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_GRADEDEPENDANCY);

			String academic_year = (String) request.getSession(false)
					.getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			//("current school Location:" + schoolLocation);
			String currentlocation = null;
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
				request.setAttribute("currentlocation", "All");
				request.setAttribute("locationId", schoolLocation);
			} else {
				currentlocation = new ExamDetailsBD()
						.getlocationname(schoolLocation,userLoggingsVo);
				request.setAttribute("currentlocation", currentlocation);
			}
			request.setAttribute("locationId", schoolLocation);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails(userLoggingsVo);
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("accYearList", accYearList);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getEaxmListYear Ending");
		return mapping.findForward(MessageConstants.GRADE_DEP);
	}
	
	public ActionForward setGradeDependency(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_GRADEDEPENDANCY);
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		String accyear = request.getParameter("accyear");
		String hschoolLocation = request.getParameter("hschoolLocation");
		String accyname = new ExamDetailsBD().getaccyName(accyear,custdetails);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getstatusgrdDepDetails(accyear,
				hschoolLocation);
		request.setAttribute("examsettings", list);
		String currentlocation = new ExamDetailsBD()
				.getlocationname(hschoolLocation,custdetails);
		request.setAttribute("currentlocation", currentlocation);
		request.setAttribute("locationid", hschoolLocation);
		return mapping.findForward(MessageConstants.SET_GRADE_DETAILS);

	}

	public ActionForward setGradeDependencyinDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : setGradeDependencyinDetail Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_GRADEDEPENDANCY);
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("hschoolLocation");

		String accyname = new ExamDetailsBD().getaccyName(accyear,custdetails);
		request.setAttribute("accyName", accyname);
		request.setAttribute("accyear", accyear);

		String examid = request.getParameter("examid");
		String examname = new ExamDetailsBD().getexamName(examid, accyear,locid,custdetails);
		String examarray[] = examname.split(",");
		String examName = examarray[0];
		String examCode = examarray[1];

		request.setAttribute("examName", examName);
		request.setAttribute("examCode", examCode);
		request.setAttribute("examid", examid);
		//ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getSubjectClass(accyear, examid, locid,custdetails.getCustId());

	//	request.setAttribute("subjectClassList", list);
		ArrayList<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
		list1 = new ExamTimeTableBD().getstatusexamsettingsDetails(accyear,schoolLocation,custdetails);
		request.setAttribute("examsettings", list1);

		String currentlocation = new ExamDetailsBD()
				.getlocationname(schoolLocation,custdetails);
		request.setAttribute("currentlocation", currentlocation);
		request.setAttribute("locationid", locid);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : setGradeDependencyinDetail Starting");
		return mapping.findForward(MessageConstants.GRADE_DEPENDENCY_DETAIL);

	}
	
	public ActionForward saveExamDependency(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		String[] examcode=request.getParameterValues("myselectArray[]");
		String[] dependency=request.getParameterValues("myinputArray[]");
		String mainexamcode=request.getParameter("examcode");
		String mainexamname=request.getParameter("examname");
		String examId=request.getParameter("examId");
	      String result=new ExamTimeTableBD().saveDependency(examcode,dependency,mainexamcode,mainexamname,examId,log_audit_session);
	
		JSONObject json = new JSONObject();
		json.put("status", result);
		response.getWriter().print(json);

		return null;

	}
	public ActionForward insertGradeDependent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		
		String project=request.getParameter("project");
		String assignment=request.getParameter("assignment");
		String practical=request.getParameter("practical");
		String attendance=request.getParameter("attendance");
		String classId=request.getParameter("classId");
		String sectionId=request.getParameter("sectionId");
		String exam=request.getParameter("exam");
		String location=request.getParameter("location");
		String accyear=request.getParameter("accyear");
		
	    String result=new ExamTimeTableBD().insertGradeDependent(project,assignment,practical,attendance,classId,sectionId,exam,location,accyear,log_audit_session);
	
		JSONObject json = new JSONObject();
		json.put("status", result);
		response.getWriter().print(json);
		return null;
	}
	
	public ActionForward disstudepdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");

			ArrayList<ExaminationDetailsVo> examListYear = new ArrayList<ExaminationDetailsVo>();
			examListYear = new ExamTimeTableBD().disstudepdetails(accyear,
					locid);

			JSONObject obj = new JSONObject();
			obj.put("markslist", examListYear);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward getExamTypeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getExamTypeList Starting");
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getExamTypeList(custdetails);

		JSONObject object = new JSONObject();
		object.put("ExamList", list);
		response.getWriter().print(object);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamTimeTableAction : getExamTypeList Ending");
		
		return null;
	}
	public ActionForward saveMarkEntrySubjectWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : saveMarkEntrySubjectWise  Starting");
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		String custid=userLoggingsVo.getCustId();
		String createdby = HelperClass.getCurrentUserID(request);
		String subids = request.getParameter("subids");
		String statusvalues[] = request.getParameterValues("statusvalues[]");
		String scoremarks[] = request.getParameterValues("scoredmarks[]");
		String primaryidstu[] = request.getParameterValues("primaryidstu[]");
		String notebookmarks[] = request.getParameterValues("notebookmarks[]");
		String subjectenrichment[] = request.getParameterValues("subjectenrichment[]");
		String maxnotebookmarks[] = request.getParameterValues("maxnotebookmarks[]");
		String maxsubjectenrichmarks[] = request.getParameterValues("maxsubjectenrichmarks[]");
		String maxscoredmarks[] = request.getParameterValues("maxscoredmarks[]");
		String maxperiodicmarks[] = request.getParameterValues("maxperiodicmarks[]");
		String periodicscoredmarks[] = request.getParameterValues("periodicscoredmarks[]");
		String accyear = request.getParameter("accyear");
		String examid = request.getParameter("examid");
		String classId = request.getParameter("classId");
		String sectionId = request.getParameter("sectionId");
		String studentid[] = request.getParameterValues("studentId[]");
		String locationid = request.getParameter("hschoolLocation");
	//	String studentname = request.getParameter("studentname");
	//	String workedu = request.getParameter("workedu");
	//	String artedu = request.getParameter("artedu");
	//	String disciplineedu = request.getParameter("disciplineedu");
	//	String healthedu = request.getParameter("healthedu");
		String examtypeprfix = request.getParameter("examtypeprfix");
		ExaminationDetailsVo obj = new ExaminationDetailsVo();
		obj.setAccyear(accyear);
		obj.setExamid(examid);
		obj.setClassid(classId);
		obj.setSectionid(sectionId);
		obj.setSubId(subids);
		obj.setScoremarks(scoremarks);
		obj.setStatusvalues(statusvalues);
		//obj.setStudentname(studentname);
		obj.setStudentids(studentid);
		obj.setLocationid(locationid);
		obj.setStuprimaryid(primaryidstu);
		obj.setNotebookmarks(notebookmarks);
		obj.setSubjectenrichment(subjectenrichment);
		obj.setMaxnotebookmarks(maxnotebookmarks);
		obj.setMaxsubenrichmentmarks(maxsubjectenrichmarks);
		obj.setMax_scored_marks(maxscoredmarks);
	/*	obj.setWorkedu_grade(workedu);
		obj.setArtedu_grade(artedu);
		obj.setDisciplinedu_grade(disciplineedu);
		obj.setHealthedu_grade(healthedu);*/
		obj.setCreatedBy(createdby);
		obj.setMaxperiodicmarks(maxperiodicmarks);
		obj.setPeriodicscoredmarks(periodicscoredmarks);
		obj.setExamtypeprefix(examtypeprfix);
		obj.setCustid(custid);
		obj.setLog_audit_session(log_audit_session);
		
		String result = null;
		result = new ExamDetailsBD().insertmarkentrydetailsSubjectWise(obj,userLoggingsVo);
		JSONObject object = new JSONObject();
		object.put("status", result);
		response.getWriter().print(object);
		String currentlocation = new ExamDetailsBD()
				.getlocationname(schoolLocation,userLoggingsVo);
		request.setAttribute("currentlocation", currentlocation);
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : saveMarkEntrySubjectWise  Ending");
		return null;
	}
	
	public ActionForward getexamdetailsOnClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getexamdetailsOnClass  Starting");
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String accyearid = request.getParameter("accyear");
		String location = request.getParameter("location");
		String classid = request.getParameter("classid");
		if(classid.equalsIgnoreCase("all")){
			classid="%%";
		}

		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getexamsettingsDetailsonClass(accyearid,location,custdetails,classid);
		request.setAttribute("examsettings", list);

		JSONObject object = new JSONObject();
		object.put("examsettings", list);
		response.getWriter().print(object);
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getexamdetailsOnClass  Ending");
		return null;
	}
	public ActionForward getExamNameList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getExamNameList  Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String locationid=request.getParameter("locationid");
		String classid=request.getParameter("classid");
		String accyear=request.getParameter("accyear");
		
		
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getExamNameList(locationid,classid,accyear,custdetails);
		
		JSONObject object = new JSONObject();
		object.put("ExamNameList", list);
		response.getWriter().print(object);
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getExamNameList  Ending");
		return null;
	}
	public ActionForward getExamNameList1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getExamNameList  Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String locationid=request.getParameter("locationid");
		String classid=request.getParameter("classid");
		String accyear=request.getParameter("accyear");
		
		
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getExamNameList(locationid,classid,accyear,custdetails);
		
		JSONObject object = new JSONObject();
		object.put("ExamNameList", list);
		response.getWriter().print(object);
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getExamNameList  Ending");
		return null;
	}
	public ActionForward resultDeclare(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : resultDeclare  Starting");
		try {
		
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = request.getParameter("accyear");
			String examcode = request.getParameter("exmcode");
			String location = request.getParameter("location");
			String msg = request.getParameter("msg");
			String custid= custdetails.getCustId();

			String result = new ExamTimeTableBD().resultDeclare(accyear,examcode, location,custdetails,msg);

			JSONObject json = new JSONObject();
			json.put("status", result);
			response.getWriter().print(json);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : resultDeclare  Ending");
		return null;
	}
	public ActionForward timetableset(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : timetableset  Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String examid = request.getParameter("examid");
			String location = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			
			String result = new ExamTimeTableBD().timetableset(examid,location,accyear,custdetails);
			
			JSONObject json = new JSONObject();
			json.put("status", result);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : timetableset  Ending");
		return null;
	}
	public ActionForward getExamByClassWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String schoolLocation = (String) request.getSession(false)
				.getAttribute("current_schoolLocation");
		//("current school Location:" + schoolLocation);
		if (schoolLocation.equalsIgnoreCase("all")) {
			schoolLocation = "%%";
		}
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
		
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMTIMETABLE);
		
		String myValues = request.getParameter("myValue");
		String accyear = myValues.split(",")[0];
		String locid =myValues.split(",")[1];
		String classid =myValues.split(",")[2];
		
		String currentlocation = new ExamDetailsBD().getlocationname(locid,custdetails);
		request.setAttribute("location", currentlocation);
		
		String accyname = new ExamDetailsBD().getaccyName(accyear,custdetails);
		request.setAttribute("accyName", accyname);

		String className=HelperClass.getClassName(classid, locid, custdetails);
		
		request.setAttribute("classid", classid);
		request.setAttribute("accyearid", accyear);
		request.setAttribute("locid", locid);
		request.setAttribute("className", className);
		request.setAttribute("historyaccyear", request.getParameter("historyaccyear"));
		request.setAttribute("historylocation", request.getParameter("historylocation"));
		request.setAttribute("historyaccyear", accyear);
		request.setAttribute("historylocation", locid);
		List<ExaminationDetailsVo> examlist = new ExamDetailsBD().getExamByClassWise(locid,accyear,classid,custdetails);
		request.setAttribute("examlist", examlist);

		return mapping.findForward(MessageConstants.EXAM_CLASS_LIST);
	}
	
	public ActionForward getStudentMarkListSearch(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getStudentMarkListSearch Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			String locationId = request.getParameter("locationId");
			String accyearId = request.getParameter("accyearId");
			String specId = request.getParameter("specId");
			String searchvalue = request.getParameter("searchvalue").trim();
			ExaminationDetailsVo vo=new ExaminationDetailsVo();
			vo.setClassId(classId);
			vo.setSectionid(sectionId);
			vo.setLocationid(locationId);
			vo.setAccyearId(accyearId);
			vo.setSpecializationId(specId);
			vo.setSearchText(searchvalue);
			
			List<ExaminationDetailsVo> studentList = new ExamDetailsBD().getStudentMarkListSearch(vo,custdetails);
			
			JSONObject json = new JSONObject();
			json.put("studentList", studentList);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getStudentMarkListSearch Ending");
		return null;
	}
	
	public ActionForward getMaximumMarkClassList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");
			String classId = request.getParameter("classId");

			List<ExaminationDetailsVo> ClassList = new ArrayList<ExaminationDetailsVo>();

			ClassList = new ExamDetailsBD().getMaximumMarkClassList(locid,accyear,userLoggingsVo,classId);
			request.setAttribute("ClassList", ClassList);
			
			JSONObject obj = new JSONObject();
			obj.put("ClassList", ClassList);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward getExamNameListByPeriodicExam(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getExamNameListByPeriodicExam  Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String locationid=request.getParameter("locationid");
		String classid=request.getParameter("classid");
		String accyear=request.getParameter("accyear");
		
		
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getExamNameListByPeriodicExam(locationid,classid,accyear,custdetails);
		
		JSONObject object = new JSONObject();
		object.put("ExamNameList", list);
		response.getWriter().print(object);
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getExamNameListByPeriodicExam  Ending");
		return null;
	}
	
	public ActionForward getHalflyExamNameList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getHalflyExamNameList  Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String locationid=request.getParameter("locationid");
		String classid=request.getParameter("classid");
		String accyear=request.getParameter("accyear");
		
		
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getHalflyExamNameList(locationid,classid,accyear,custdetails);
		
		JSONObject object = new JSONObject();
		object.put("ExamNameList", list);
		response.getWriter().print(object);
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getHalflyExamNameList  Ending");
		return null;
	}
	
	public ActionForward getYearlyExamNameList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getYearlyExamNameList  Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String locationid=request.getParameter("locationid");
		String classid=request.getParameter("classid");
		String accyear=request.getParameter("accyear");
		
		
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		list = new ExamTimeTableBD().getYearlyExamNameList(locationid,classid,accyear,custdetails);
		
		JSONObject object = new JSONObject();
		object.put("ExamNameList", list);
		response.getWriter().print(object);
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getYearlyExamNameList  Ending");
		return null;
	}
}
