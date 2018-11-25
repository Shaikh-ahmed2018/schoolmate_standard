package com.centris.campus.actions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.daoImpl.SmsDaoIMPL;
import com.centris.campus.delegate.CommunicationSettingsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.SmsDeligate;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.FeeSMSVO;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.SuddenHolidaySMSVO;
import com.centris.campus.vo.TermMasterVo;

public class SmsAction extends DispatchAction {

	private static final Logger logger = Logger.getLogger(SmsAction.class);

	public ActionForward getSection(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : getSection Starting");

		try {

			String classid = request.getParameter("classid");

			ArrayList<SectionPojo> seclist = new SmsDeligate().getsectionlist(classid);

			JSONObject object = new JSONObject();

			object.put("seclist", seclist);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  :getSection Ending");

		return null;

	}

	public ActionForward getSubject(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : getSubject Starting");

		try {

			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			
			String classid = request.getParameter("classid");
			String locationId = request.getParameter("locationId");

			ArrayList<SectionPojo> sublist = new SmsDeligate().getsubjectlist(classid,locationId,cpojo);

			JSONObject object = new JSONObject();

			object.put("sublist", sublist);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : getSubject Ending");

		return null;

	}

	public ActionForward insertsms(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : insertsms Starting");

		try {
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String createuser = HelperClass.getCurrentUserID(request);
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String dateId = request.getParameter("dateId");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String subjectid = request.getParameter("subjectid");
			String description = request.getParameter("description");
			String[] studentlist = request.getParameter("studentlist").split(",");
			String locId = request.getParameter("locId");

			SmsVo vo = new SmsVo();

			vo.setDate(dateId);
			vo.setClassid(classid);
			vo.setSectionid(sectionid);
			vo.setSubjectid(subjectid);
			vo.setDescription(description);
			vo.setCreateuser(createuser);
			vo.setStudentList(studentlist);
			vo.setLocId(locId);
			vo.setLog_audit_session(log_audit_session);
			vo.setDbDetails(cpojo);
			vo.setAccid(academic_year);
			vo.setBalanceSMS(Integer.parseInt(request.getParameter("balanceSMS")));
			
			String result = new SmsDeligate().inserthomework(vo);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("jsonResponse", result);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : insertsms Ending");

		return null;

	}

	public ActionForward getSectionMeeting(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : getSectionMeeting Starting");

		try {
			SectionPojo secpojo = new SectionPojo();

			String classid = request.getParameter("classid");
			String[] categoryval = classid.split(",");

			ArrayList<SectionPojo> seclist = new CommunicationSettingsBD().getSectionListDetails(categoryval);

			JSONObject object = new JSONObject();

			object.put("seclist", seclist);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : getSectionMeeting Ending");

		return null;
	}

	public ActionForward getStudentMeeting(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : getStudentMeeting Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String sectionid = request.getParameter("sectionid");
			String locId = request.getParameter("locId");

			ArrayList<StudentInfoVO> studentlist = new SmsDeligate().getStudentListDetails(locId,sectionid,userLoggingsVo);

			JSONObject object = new JSONObject();

			object.put("studentlist", studentlist);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : getStudentMeeting Ending");

		return null;
	}

	public ActionForward getSubjectMeeting(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : getSubjectMeeting Starting");

		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			SubjectPojo subpojo = new SubjectPojo();
			
			subpojo.setCustid(pojo.getCustId());
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");

			String[] categoryval = classid.split(",");

			ArrayList<SubjectPojo> subjectlist = new SmsDeligate().getSubjectListDetails(categoryval,subpojo,pojo);

			JSONObject object = new JSONObject();

			object.put("subjectlist", subjectlist);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : getSubjectMeeting Ending");

		return null;
	}

	public ActionForward insertmeeting(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : insertmeeting Starting");

		try {

			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			LstmsUpcomingMeetingVO meetingvo = new LstmsUpcomingMeetingVO();
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String createUser = HelperClass.getCurrentUser(request);
			String accyear = HelperClass.getCurrentYearID(userLoggingsVo);
			String dateId = request.getParameter("dateId");
			String classid = request.getParameter("classid");
			String starttime = request.getParameter("starttime");
			String subjectid = request.getParameter("subjectid");
			String studentid[] = request.getParameter("studentid").split(",");
			String titleid = request.getParameter("titleid");
			String sectionid[] = request.getParameter("sectionid").split(",");
			String locId = request.getParameter("locId");
			String endtime = request.getParameter("endtime");
			String description = request.getParameter("description");
			String SMSCharacters = request.getParameter("SMSCharacters");
			
			if (subjectid == "" || subjectid == null)
			{
				meetingvo.setSubjectid("-");
			}
			else
			{
				meetingvo.setSubjectid(subjectid);
			}
			meetingvo.setMeetingDate(dateId);
			meetingvo.setLocId(locId);
			meetingvo.setClassid(classid);
			meetingvo.setStartTime(starttime);
			meetingvo.setStudentname(studentid);
			meetingvo.setSectionName(sectionid);
			meetingvo.setTitle(titleid);
			meetingvo.setEndTime(endtime);
			meetingvo.setDescription(description);
			meetingvo.setCreatedby(createUser);
			meetingvo.setAccyearid(accyear);
			meetingvo.setSmsCharacters(Integer.parseInt(SMSCharacters));
			meetingvo.setLog_audit_session(log_audit_session);
			meetingvo.setBalanceSMS(Integer.parseInt(request.getParameter("balanceSMS")));
			
			String result = new SmsDeligate().saveMeetingDetails(meetingvo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("jsonResponse", result);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : insertmeeting Ending");

		return null;
	}

	public ActionForward addlatecomers(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : addlatecomers Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			LstmsUpcomingMeetingVO meetingvo = new LstmsUpcomingMeetingVO();
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String createUser = HelperClass.getCurrentUserID(request);
			String accyear = HelperClass.getCurrentYearID(userLoggingsVo);

			String dateId = request.getParameter("dateId");
			String locId = request.getParameter("locId");
			String classid = request.getParameter("classid");
			String studentid[] = request.getParameter("studentid").split(",");
			String description = request.getParameter("description");
			

			meetingvo.setMeetingDate(dateId);
			meetingvo.setLocId(locId);
			meetingvo.setClassid(classid);
			meetingvo.setStudentname(studentid);
			meetingvo.setDescription(description);
			meetingvo.setCreatedby(createUser);
			meetingvo.setAccyearid(accyear);
			meetingvo.setLog_audit_session(log_audit_session);
			meetingvo.setBalanceSMS(Integer.parseInt(request.getParameter("balanceSMS")));
 
 
			String result = new SmsDeligate().addlatecomers(meetingvo,userLoggingsVo); 

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("jsonResponse", result);
			response.getWriter().print(jsonobj);

		} 
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : addlatecomers Ending");

		return null;
	}

	public ActionForward SendUniform(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : SendUniform Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String classvame = request.getParameter("classid");
			String[] studentlist = request.getParameter("studentlist").split(",");
			
			UniformSmsPojo upojo = new UniformSmsPojo();
			upojo.setClassname(classvame);
			upojo.setStudentid(studentlist);
			upojo.setDate(HelperClass.convertUIToDatabase(request.getParameter("date")));
			upojo.setSmstext(request.getParameter("smstext"));
			upojo.setCreatedby(HelperClass.getCurrentUserID(request)); 
			upojo.setCreatedate(HelperClass.getCurrentTimestamp());
			upojo.setAccYearId(HelperClass.getCurrentYearID(userLoggingsVo));
			upojo.setLocId(request.getParameter("locId"));
			upojo.setLog_audit_session(log_audit_session);
			
			String status = new SmsDeligate().storeUniformDetails(upojo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : SendUniform Ending");

		return null;
	}

	public ActionForward deleteHomeWork(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : deleteHomeWork Starting");
		try {
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String[] Id = request.getParameterValues("Id[]");

			String result = new SmsDaoIMPL().deleteHomeWork(Id,cpojo);

			JSONObject object = new JSONObject();
			object.put("result", result);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : deleteHomeWork Ending");

		return null;
	}
	
	public ActionForward getStudentMeetingAndEvent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : getStudentMeetingAndEvent Starting");
		try {
			
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			String classid=request.getParameter("classid");

			
			UniformSmsPojo upojo = new UniformSmsPojo();
			
			upojo.setLocId(locId);
			upojo.setClassnameid(classid);
			upojo.setCustid(cpojo.getCustId());
			ArrayList<StudentInfoVO> studentlist = new SmsDaoIMPL().getStudentMeetingAndEvent(upojo,cpojo);

			JSONObject object = new JSONObject();
			object.put("studentlist", studentlist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : getStudentMeetingAndEvent Ending");
		return null;
	} 
	
	public ActionForward getStudentMeetingAndEventBySections(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : getStudentMeetingAndEventBySections Starting");
		try {
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			String classid=request.getParameter("classid"); 
			String [] sectionlist=request.getParameter("sectionlist").split(",");
			
			UniformSmsPojo upojo = new UniformSmsPojo();
			
		
	        upojo.setLocId(locId);          
			upojo.setClassnameid(classid);
			upojo.setSectionid(sectionlist);
			upojo.setCustid(cpojo.getCustId());
			ArrayList<StudentInfoVO> studentlist = new SmsDaoIMPL().getStudentMeetingAndEventBySections(upojo,cpojo);

			JSONObject object = new JSONObject();
			object.put("studentlist", studentlist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : getStudentMeetingAndEventBySections Ending");
		return null;
	}
	
	public ActionForward searchlatecomerList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in SmsAction : searchlatecomerList Starting");
		try{
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String locationid = request.getParameter("locid");
			String accyear = request.getParameter("accid");
			String classid = request.getParameter("clasid");
			String sectionid = request.getParameter("secid");
			String startdate=request.getParameter("startdate");
			String enddate=request.getParameter("enddate");
			
			if(locationid.equalsIgnoreCase("all") || locationid.equals("")){
				locationid = "%%";
			}
			if(classid.equalsIgnoreCase("all") || classid.equals("")){
				classid = "%%";
			}
			if(sectionid.equalsIgnoreCase("all") || sectionid.equals("")){
				sectionid = "%%";
			}
			
			
			if(startdate==null || startdate.trim().equalsIgnoreCase("")){
				startdate = HelperClass.getAcademicYearStartAndEndDate(accyear, userLoggingsVo).split(",")[0];
			}else{
				startdate = HelperClass.convertUIToDatabase(startdate);
			}
			if(enddate==null || enddate.trim().equalsIgnoreCase("")){
				 enddate = HelperClass.getAcademicYearStartAndEndDate(accyear, userLoggingsVo).split(",")[1];
            }else{
            	enddate = HelperClass.convertUIToDatabase(enddate);
             }
			 List<LstmsUpcomingMeetingVO > list=new ArrayList<LstmsUpcomingMeetingVO >();
			
			 list = new SmsDeligate().getSearchLatecomerList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("latecommerlist", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : searchlatecomerList Ending");
		return null;
	}
	
	public ActionForward SendOtherSMS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : SendUniform Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			LstmsUpcomingMeetingVO meetingvo = new LstmsUpcomingMeetingVO();
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String createUser = HelperClass.getCurrentUserID(request);
			String accyear = HelperClass.getCurrentYearID(custdetails);
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String dateId = request.getParameter("dateId");
			String locId = request.getParameter("locId");
			String classid = request.getParameter("classid");
			String studentid[] = request.getParameter("studentid").split(",");
			String description = request.getParameter("description");
			

			meetingvo.setMeetingDate(dateId);
			meetingvo.setLocId(locId);
			meetingvo.setClassid(classid);
			meetingvo.setStudentname(studentid);
			meetingvo.setDescription(description);
			meetingvo.setCreatedby(createUser);
			meetingvo.setAccyearid(accyear);
			meetingvo.setLog_audit_session(log_audit_session);
			meetingvo.setCustid(custdetails.getCustId());
			meetingvo.setAccid(academic_year);
			String status=new SmsDeligate().SendOtherSMS(meetingvo,custdetails);
			
			ClassPojo pojo = new ClassPojo();
			pojo.setCustid(custdetails);
			List<ClassPojo> classpojo =new CommunicationSettingsBD().getClassListDetails(pojo,custdetails);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("jsonResponse", status);
			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


	JLogger.log(0, JDate.getTimeString(new Date())
		+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
		+ " Control in  SmsAction  : SendUniform Ending");

	return null;
	}
	
	public ActionForward getOtherSmsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in SmsAction : getOtherSmsList Starting");
		try{
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String locationid = request.getParameter("locid").trim();
			String accyear = request.getParameter("accid").trim();
			String classid = request.getParameter("clasid").trim();
			String sectionid = request.getParameter("secid").trim();
			String startdate=request.getParameter("startdate");
			String enddate=request.getParameter("enddate");
			
			
			String searchname = request.getParameter("searchname");
			
			if(locationid.equalsIgnoreCase("all") || locationid.equals("")){
				locationid = "%%";
			}
			if(classid.equalsIgnoreCase("all") || classid.equals("")){
				classid = "%%";
			}
			if(sectionid.equalsIgnoreCase("all") || sectionid.equals("")){
				sectionid = "%%";
			}
			if(startdate==null || startdate.trim().equalsIgnoreCase("")){
				startdate = HelperClass.getAcademicYearStartAndEndDate(accyear, userLoggingsVo).split(",")[0];
			}else{
				startdate = HelperClass.convertUIToDatabase(startdate);
			}
			if(enddate==null || enddate.trim().equalsIgnoreCase("")){
				 enddate = HelperClass.getAcademicYearStartAndEndDate(accyear, userLoggingsVo).split(",")[1];
            }else{
            	enddate = HelperClass.convertUIToDatabase(enddate);
             }
			
			/*if(searchname.equals("") || searchname.equals("null") || searchname.equalsIgnoreCase("all")){
				searchname = "";
			}*/
			 List<LstmsUpcomingMeetingVO > list=new ArrayList<LstmsUpcomingMeetingVO >();
			


			list = new SmsDeligate().getOtherSmsList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate);
			/*request.setAttribute("StartDate", startdate);
			request.setAttribute("EndDate", enddate);*/
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("othersmslist", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : getOtherSmsList Ending");
		return null;
	}
	
	public ActionForward getSuddenHolidaySmsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in SmsAction : getSuddenHolidaySmsList Starting");
		try{
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String locationid = request.getParameter("locid").trim();
			String accyear = request.getParameter("accid").trim();
			String classid = request.getParameter("clasid").trim();
			String sectionid = request.getParameter("secid").trim();
			String startdate=request.getParameter("startdate");
			String enddate=request.getParameter("enddate");
			
			
			String searchname = request.getParameter("searchname");
			
			if(locationid.equalsIgnoreCase("all") || locationid.equals("")){
				locationid = "%%";
			}
			if(classid.equalsIgnoreCase("all") || classid.equals("")){
				classid = "%%";
			}
			if(sectionid.equalsIgnoreCase("all") || sectionid.equals("")){
				sectionid = "%%";
			}
			if(startdate==null || startdate.trim().equalsIgnoreCase("")){
				startdate = HelperClass.getAcademicYearStartAndEndDate(accyear, userLoggingsVo).split(",")[0];
			}else{
				startdate = HelperClass.convertUIToDatabase(startdate);
			}
			if(enddate==null || enddate.trim().equalsIgnoreCase("")){
				 enddate = HelperClass.getAcademicYearStartAndEndDate(accyear, userLoggingsVo).split(",")[1];
            }else{
            	enddate = HelperClass.convertUIToDatabase(enddate);
             }
			
			/*if(searchname.equals("") || searchname.equals("null") || searchname.equalsIgnoreCase("all")){
				searchname = "";
			}*/
			 List<SuddenHolidaySMSVO > list=new ArrayList<SuddenHolidaySMSVO >();
			


			list = new SmsDeligate().getSuddenHolidaySmsList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate);
		
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("suddenholidaysmslist", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : getSuddenHolidaySmsList Ending");
		return null;
	}

	public ActionForward getAbsentSmsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in SmsAction : getAbsentSmsList Starting");
		try{
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String locationid = request.getParameter("locid").trim();
			String accyear = request.getParameter("accid").trim();
			String classid = request.getParameter("clasid").trim();
			String sectionid = request.getParameter("secid").trim();
			String startdate=request.getParameter("startdate");
			String enddate=request.getParameter("enddate");
			
			
			String searchname = request.getParameter("searchname");
			
			if(locationid.equalsIgnoreCase("all") || locationid.equals("")){
				locationid = "%%";
			}
			if(classid.equalsIgnoreCase("all") || classid.equals("")){
				classid = "%%";
			}
			if(sectionid.equalsIgnoreCase("all") || sectionid.equals("")){
				sectionid = "%%";
			}
			if(startdate==null || startdate.trim().equalsIgnoreCase("")){
				startdate = HelperClass.getAcademicYearStartAndEndDate(accyear, userLoggingsVo).split(",")[0];
			}else{
				startdate = HelperClass.convertUIToDatabase(startdate);
			}
			if(enddate==null || enddate.trim().equalsIgnoreCase("")){
				 enddate = HelperClass.getAcademicYearStartAndEndDate(accyear, userLoggingsVo).split(",")[1];
            }else{
            	enddate = HelperClass.convertUIToDatabase(enddate);
             }
			
			/*if(searchname.equals("") || searchname.equals("null") || searchname.equalsIgnoreCase("all")){
				searchname = "";
			}*/
			 List<AbsentsSMSPojo > list=new ArrayList<AbsentsSMSPojo >();
			


			list = new SmsDeligate().getAbsentSmsList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate);
		
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("absentsmslist", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : getAbsentSmsList Ending");
		return null;
	}
	
	public ActionForward getMeetingEventSmsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in SmsAction : getMeetingEventSmsList Starting");
		try{
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String locationid = request.getParameter("locid").trim();
			String accyear = request.getParameter("accid").trim();
			String classid = request.getParameter("clasid").trim();
			String sectionid = request.getParameter("secid").trim();
			String startdate=request.getParameter("startdate");
			String enddate=request.getParameter("enddate");
			String title=request.getParameter("title");
			
			String searchname = request.getParameter("searchname");
			
			if(locationid.equalsIgnoreCase("all") || locationid.equals("")){
				locationid = "%%";
			}
			if(classid.equalsIgnoreCase("all") || classid.equals("")){
				classid = "%%";
			}
			if(sectionid.equalsIgnoreCase("all") || sectionid.equals("")){
				sectionid = "%%";
			}
			if(startdate==null || startdate.trim().equalsIgnoreCase("")){
				startdate = HelperClass.getAcademicYearStartAndEndDate(accyear, userLoggingsVo).split(",")[0];
			}else{
				startdate = HelperClass.convertUIToDatabase(startdate);
			}
			if(enddate==null || enddate.trim().equalsIgnoreCase("")){
				 enddate = HelperClass.getAcademicYearStartAndEndDate(accyear, userLoggingsVo).split(",")[1];
            }else{
            	enddate = HelperClass.convertUIToDatabase(enddate);
             }
			
			/*if(searchname.equals("") || searchname.equals("null") || searchname.equalsIgnoreCase("all")){
				searchname = "";
			}*/
			 List<LstmsUpcomingMeetingVO > list=new ArrayList<LstmsUpcomingMeetingVO >();
			


			list = new SmsDeligate().getMeetingEventSmsList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate,title);
		
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("meeteventlist", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : getMeetingEventSmsList Ending");
		return null;
	}
	
	public ActionForward feeSmsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : feeSmsList : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_FEE_SMS);
		
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		    request.setAttribute("current_loc", schoolLocation);
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(pojo);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(pojo);
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(pojo);
			request.setAttribute("classlist", classlist);
			

			ArrayList<FeeSMSVO> arrayList = new ArrayList<FeeSMSVO>();
			
			arrayList = new SmsDeligate().getFeeSMSList(pojo,schoolLocation);
			String startDate=HelperClass.getAcademicYearStartAndEndDate(academic_year, pojo).split(",")[0];
			String enddate=HelperClass.getAcademicYearStartAndEndDate(academic_year, pojo).split(",")[1];
			
			request.setAttribute("FeeSMSList", arrayList);
			request.setAttribute("startDate", startDate);
			request.setAttribute("enddate", enddate);
			


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : feeSmsList : Ending");

		return mapping.findForward(MessageConstants.FEE_SMS_LIST);
	}

	
	public ActionForward addFeeSMS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : addFeeSMS : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_FEE_SMS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String startDate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0];
			String enddate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1];
			
			ClassPojo pojo = new ClassPojo();
			
			List<ClassPojo> classpojo =new CommunicationSettingsBD().getClassListDetails(pojo,custdetails);
			request.setAttribute("classpojo", classpojo);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			
			request.setAttribute("historystartdate", startDate);
			request.setAttribute("historyenddate", enddate);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : addFeeSMS : Ending");

		return mapping.findForward(MessageConstants.ADD_FEE_SMS);
	}
	
	
	public ActionForward gettearm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : getTearm Starting");

		try {

			String locId = request.getParameter("locId");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			ArrayList<TermMasterVo> termList = new SmsDeligate().getTearm(locId,custdetails,academic_year);

			JSONObject object = new JSONObject();

			object.put("termlist", termList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  :getTearm Ending");

		return null;

	}
	
	
	public ActionForward getUnPaidStuList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : getUnPaidStuList Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String locId = request.getParameter("locId");
			String clsid=request.getParameter("classid");
			String termid=request.getParameter("termid");
			String [] divid=request.getParameter("divid").split(",");
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			ArrayList<StudentInfoVO> studentlist = new SmsDeligate().getUnPaidStuList(locId,clsid,termid,userLoggingsVo,divid,academic_year);

			JSONObject object = new JSONObject();

			object.put("studentlist", studentlist);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : getUnPaidStuList Ending");

		return null;
	}
	
	
	
	public ActionForward InserFeeSMS(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : InserFeeSMS Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
		
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String createUser = HelperClass.getCurrentUserID(request);
			String accyear = HelperClass.getCurrentYearID(userLoggingsVo);

			String dateId = request.getParameter("dateId");
			String locId = request.getParameter("locId");
			String classid = request.getParameter("classid");
			String studentid[] = request.getParameter("studentid").split(",");
			String[] divid=request.getParameter("sectionid").split(",");
			String description = request.getParameter("description");
			String termid=request.getParameter("termid");
			
			FeeSMSVO vo=new FeeSMSVO();
            vo.setLocId(locId);
            vo.setClsId(classid);
            vo.setDiv(divid);
            vo.setDate(dateId);
            vo.setSmstext(description);
            vo.setTerm(termid);
            vo.setCreatedby(createUser);
            vo.setStudeid(studentid);
            vo.setLog_audit_session(log_audit_session);
			vo.setAccid(accyear);
			vo.setBalanceSMS(Integer.parseInt(request.getParameter("balanceSMS")));
			
           String result = new SmsDeligate().InserFeeSMS(vo,userLoggingsVo); 

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("result", result);
			response.getWriter().print(jsonobj);

		} 
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  : InserFeeSMS Ending");

		return null;

	}
	
	
	
	public ActionForward getFeeSmsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : getFeeSmsList : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_FEE_SMS);
		
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			
			String locid=request.getParameter("locid");
			String clasid=request.getParameter("clasid");
			String secid=request.getParameter("secid");
			String accid=request.getParameter("accid");
			String startdate=request.getParameter("startdate");
			String enddate=request.getParameter("enddate");
		
			
			FeeSMSVO vo =new FeeSMSVO();
			vo.setLocId(locid);
			vo.setClsId(clasid);
			vo.setDivId(secid);
			vo.setAccid(accid);
			vo.setStartdate(startdate);
			vo.setEnddate(enddate);
			
			if(vo.getClsId().equalsIgnoreCase("all")){
				vo.setClsId("%%");
			}
			if(vo.getDivId().equalsIgnoreCase("all")){
				vo.setDivId("%%");
			}
			if(vo.getStartdate()==null || vo.getStartdate().trim().equalsIgnoreCase("")){
				vo.setStartdate(HelperClass.getAcademicYearStartAndEndDate(accid, pojo).split(",")[0]);
			}else{
				vo.setStartdate( HelperClass.convertUIToDatabase(startdate));
			}
			if(vo.getEnddate()==null || vo.getEnddate().trim().equalsIgnoreCase("")){
				vo.setEnddate(HelperClass.getAcademicYearStartAndEndDate(accid, pojo).split(",")[1]);
            }else{
            	vo.setEnddate(HelperClass.convertUIToDatabase(enddate));
             }
			ArrayList<FeeSMSVO> arrayList = new ArrayList<FeeSMSVO>();
			
			arrayList = new SmsDeligate().FeeSmsList(pojo,vo);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("feesmsList", arrayList);
			response.getWriter().print(jsonobj);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsAction : getFeeSmsList : Ending");

		return null;
	}
	
	public ActionForward getBalanceSMSCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsAction : getBalanceSMSCount Starting");

		try {
			
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");

			String locationid = request.getParameter("locationid");
			
			int smsCount = new SmsDaoIMPL().getBalanceSMSCount(pojo,locationid);

			JSONObject object = new JSONObject();
			object.put("smsCount", smsCount);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in  SmsAction  :getBalanceSMSCount Ending");

		return null;
	}
}

