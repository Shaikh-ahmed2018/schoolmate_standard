package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.json.JSONArray;
import org.json.JSONObject;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.LoginDaoImpl;
import com.centris.campus.daoImpl.OnlineFeePaymentDaoImpl;
import com.centris.campus.daoImpl.ParentSettingsDaoImpl;
import com.centris.campus.delegate.LoginBD;
import com.centris.campus.delegate.OnlineFeePaymentDelegate;
import com.centris.campus.delegate.ParentExamdetailsBD;
import com.centris.campus.delegate.ParentLeaveRequestBD;
import com.centris.campus.delegate.PeriodDetailsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentAttendanceBD;
import com.centris.campus.delegate.StudentEnquiryBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.TeacherLeaveModuleBD;
import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.forms.LoginForm;
import com.centris.campus.forms.ParentFeedbackform;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AssignmentViewVO;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.OnlinePaymentVo;
import com.centris.campus.vo.ParentFeedbackVo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceReportVo;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.tranxactive.j2pay.gateways.core.AvailableGateways;
import com.tranxactive.j2pay.gateways.core.Gateway;
import com.tranxactive.j2pay.gateways.core.GatewayFactory;
import com.tranxactive.j2pay.gateways.parameters.Country;
import com.tranxactive.j2pay.gateways.parameters.Currency;
import com.tranxactive.j2pay.gateways.parameters.Customer;
import com.tranxactive.j2pay.gateways.parameters.CustomerCard;
import com.tranxactive.j2pay.net.HTTPResponse;

public class ParentsMenuAction extends DispatchAction {
	
	private static final Logger logger = Logger.getLogger(ParentsMenuAction.class);

	public ActionForward Home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentsMenuAction : Home Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
		String startDate=Integer.toString(HelperClass.getPastDateofAcademicYear(accYear,custdetails));
		String endDate=Integer.toString(HelperClass.getForDateofAcademicYear(accYear,custdetails));
		
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			String accYears = (String) request.getSession(false).getAttribute("current_academicYear");
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String usercode = userDetailVO.getUserId();
			List<StudentInfoVO> list=new ArrayList<StudentInfoVO>();
			list=new StudentEnquiryBD().getStudentParentDetails(usercode,custdetails,accYears);
			
			List<String> DashBoardList=new ArrayList<String>();
			String content="";
			String heading4="";
			if(list.size()>0){
				String examdetail=""; 
				for(int i=0; i<list.size(); i++){
					String studetails = list.get(i).getStudentId()+","+list.get(i).getClassId()+","+list.get(i).getSectionId()+","+accYears+","+list.get(i).getLocationId();
					content=content+"<tr data-toggle='modal' title='Click here to view the time table' data-target='#myModal' id='"+studetails+"'><td>"+(i+1)+"</td><td>"+list.get(i).getStudentnamelabel()+"</td><td>"+list.get(i).getClass_and_section()+"</td><td><img class='stuimg' src="+list.get(i).getImageUrl()+"></td></tr>";
				}
				for(int k=0; k<list.get(0).getExamdetail().size(); k++){
					examdetail=examdetail+"<tr><td>"+(k+1)+"</td><td>"+list.get(0).getClassname()+"</td><td>"+list.get(0).getExamdetail().get(k).getExamName()+"</td><td>"+list.get(0).getExamdetail().get(k).getStartDate()+"</td></tr>";
				}
				//System.out.println("THIS MONTH NUMBER "+HelperClass.getCurrentMonthNo());
				
				 heading4="<h4>EXAM DETAILS</h4>"
						 + "<div style='position:relative;height:100%;text-align:center;'>"
						 + "<table style='top: 0px;width: 100%;' class='allstudent'><thead><tr><th>S.No</th><th>Class Name</th><th>Exam Name</th><th>Start Date</th></tr></thead>"
						 + "<tbody>"
						 + examdetail
						 + "</tbody></table>"
						 + "</div>";
			}
			
			String heading2="<h4>STUDENT INFORMATION</h4>"
					+ "<div style='position:relative;height:100%;text-align:center;'>"
					+ "<table style='top: 0px;width: 100%;' id='stuinfo' class='allstudent'><thead><tr><th>S.No</th><th>Student Name</th><th>Class-Division</th><th>Photo</th></tr></thead>"
					+ "<tbody>"
					+ content
					+ "</tbody></table>"
					+ "</div>";
			 DashBoardList.add(heading2);
			 
			 List<StudentAttendanceReportVo> list1=new ArrayList<StudentAttendanceReportVo>();
			 list1=new StudentEnquiryBD().getAttendanceDetail(usercode,custdetails);
			 
			 String content1="";
			 
			 if(list.size()>0){
					for(int j=0; j<list1.size(); j++){
						content1=content1+"<tr><td>"+(j+1)+"</td><td><img class='stuimg' src="+list1.get(j).getImageUrl()+"></td><td>"+list1.get(j).getPresentCount()+"</td><td>"+list1.get(j).getAbsentCount()+"</td><td>"+list1.get(j).getLeaveCount()+"</td></tr>";
					}
				}
			 
			 String heading3="<h4>"+HelperClass.getMonthName(Integer.toString(HelperClass.getCurrentMonthNo()+1))+" "+"ATTENDANCE</h4>"
					 + "<div style='position:relative;height:100%;text-align:center;'>"
					 + "<table style='top: 0px;width: 100%;' class='allstudent'><thead><tr><th>S.No</th><th>Student</th><th>Present</th><th>Absent</th><th>Leave</th></tr></thead>"
					 + "<tbody>"
					 +content1
					 + "</tbody></table>"
					 + "</div>";
			 DashBoardList.add(heading3);
			/* String heading4="<h3 class='headerdashboardBox primarythemebackgound primarythemecolor'>"+HelperClass.getMonthName(Integer.toString(HelperClass.getCurrentMonthNo()))+" EXAM DETAILS</h3>"
					 + "<div style='position:relative;height:100%;text-align:center;'>"
					 + "<table style='top: 0px;width: 100%;' class='allstudent'><thead><tr><th>S.No</th><th>Student</th><th>Present</th><th>Absent</th><th>Leave</th></tr></thead>"
					 + "<tbody>"
					 + "</tbody></table>"
					 + "</div>";
			 DashBoardList.add(heading4);*/
			 DashBoardList.add(heading4);
			 
			 
			 List<RemainderMasterVO> remainderlist =new ParentLeaveRequestBD().getRemainderlistBD(custdetails);	
			 String reminders="";
			 
			 List<ExaminationDetailsVo> dres=new StudentEnquiryBD().getDeclaredExam(usercode,custdetails,accYears);
			 int no=0;
			 if(remainderlist.size()>0){
				 for(int l=0; l<remainderlist.size(); l++){
					 reminders=reminders+"<tr><td>"+(l+1)+"</td><td>"+remainderlist.get(l).getName()+"</td><td>"+remainderlist.get(l).getRemtype()+"</td></tr>";
					 no++;
				 }
			 }
			 if(dres.size()>0){
				 for(int l=0; l<dres.size(); l++){
					 reminders=reminders+"<tr><td>"+(no+1)+"</td><td>"+dres.get(l).getStatus()+" of "+dres.get(l).getExamName()+" Exam on "+dres.get(l).getStatus1()+"</td><td>Parent</td></tr>";
					 no++;
				 }
			 }
			 String heading5="<h4>NOTICE</h4>"
					 + "<div style='position:relative;height:100%;text-align:center;'>"
					 + "<table style='top: 0px;width: 100%;' class='allstudent'><thead><tr><th>S.No</th><th>Reminder Title</th><th>Remind To</th></tr></thead>"
					 + "<tbody>"
					 +	reminders
					 + "</tbody></table>"
					 + "</div>";
			 DashBoardList.add(heading5);
			 
			 request.setAttribute("Dashboard", DashBoardList);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentsMenuAction : Home Ending");

		return mapping.findForward(MessageConstants.parentLogin);
	}
	
	public ActionForward studentinformation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentsMenuAction : studentinformation Starting");
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_STUDENT);
		
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			StudentRegistrationVo studentinfo = new StudentRegistrationVo();
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setDbdetails(custdetails);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			String studentid = student.get(0).getStudentid();
			vo.setStudentid(studentid);

			studentinfo = new ParentExamdetailsBD().getStudentInfoBD(vo);
			
			request.setAttribute("studentdetails", studentinfo);
			request.setAttribute("studentlist", student);
			request.setAttribute("parenthiddenid", vo);
			request.setAttribute("studentexam", studentid);
			
	    }catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentsMenuAction : studentinformation Ending");
		
		return mapping.findForward(MessageConstants.STUDENT_INFORMATION_LIST);
	}
	
	public ActionForward getnextchildInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentsMenuAction : getnextchildInfo Starting");
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_STUDENT);
		
		
		
		try {
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD()
			.getStudentlist(vo);
			
			
			
			String studentid=request.getParameter("studentid");
			String hiddenid=request.getParameter("hiddenid");
			
		
			
			
			StudentRegistrationVo studentinfo = new StudentRegistrationVo();
			
			
			studentinfo.setStudentId(studentid);
			studentinfo.setHiddenid(hiddenid);
			
			//studentinfo = new ParentExamdetailsBD().getStudentInfoBD1(vo);
			
			
			studentinfo = new ParentExamdetailsBD()
			.getnextStudentInfoBD(studentinfo);
			
			
			
			
			request.setAttribute("studentdetails", studentinfo);
			
			
			
			
			
			request.setAttribute("studentexam", studentid);
			
			request.setAttribute("studentlist", student);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
	
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentsMenuAction : getnextchildInfo Ending");
		
		return mapping.findForward(MessageConstants.STUDENT_INFORMATION_LIST);
		
	}
	
	public ActionForward attendancedetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : attendancedetails Starting");
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");

			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setDbdetails(custdetails);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			String studentid = student.get(0).getStudentid();
			vo.setStudentid(studentid);
			vo.setDbdetails(custdetails);	
			vo.setLocid(student.get(0).getLocid());
			vo.setClassDetailId(student.get(0).getClassDetailId());
			vo.setSectionid(student.get(0).getSectionid());
			vo.setSpecId(student.get(0).getSpecId());
			
			request.setAttribute("studentlist", student);
			request.setAttribute("attnstudentid", studentid);
			request.setAttribute("parenthiddenid", vo);
			
			String month = null;
			String year = null;
			ArrayList<StudentAttendanceVo> list = new ParentExamdetailsBD().getAttendanceMonthList(year,month,vo);
			
			request.setAttribute("attendancelist",list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : attendancedetails Ending");
		return mapping.findForward(MessageConstants.attendancedetails);
		
	}
	
	public ActionForward getNextChildAttendance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : getNextChildAttendance Starting");
		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			StudentAttendanceVo studvo = new StudentAttendanceVo();
			
			String studentid = request.getParameter("studentid");
			String parentid = request.getParameter("parentid");
			
			studvo.setStudentid(studentid);
			studvo.setParentid(parentid);
			studvo.setAccYear(accyear);
			studvo.setDbdetails(custdetails);
			
			String month = null;
			String year = null;
			
			ParentVO vo = new ParentVO();
			vo.setParentID(parentid);
			vo.setAccyear(accyear);
			vo.setDbdetails(custdetails);
			vo.setStudentid(studentid);
			
			ArrayList<ParentVO> name= new ParentSettingsDaoImpl().getStudentNameAndAdmissionNo(vo);
			studvo.setClassId(name.get(0).getClassid());
			studvo.setSectionId(name.get(0).getSectionid());
			studvo.setLocationId(name.get(0).getLocid());
			studvo.setSpecId(name.get(0).getSpecId());
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			ArrayList<StudentAttendanceVo> attnlist = new ParentExamdetailsBD().getNextChildAttendanceMonthList(year,month,studvo);
			
			request.setAttribute("hiddenstudentid", studentid);
			request.setAttribute("attendancelist", attnlist);
			request.setAttribute("studentlist", student);
			request.setAttribute("parenthiddenid",vo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : getNextChildAttendance Ending");
		
		return mapping.findForward(MessageConstants.attendancedetails);
	}
	
	public ActionForward getAttendanceView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAttendanceView Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setDbdetails(custdetails);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
		/*	String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);*/
			
			String studentid = request.getParameter("studentid");
			String monthid = request.getParameter("monthid");
			String currentyearid = request.getParameter("currentyearid");
			String studentid1 = request.getParameter("studentid1");
			
			StudentAttendanceVo attvo = new StudentAttendanceVo();
			attvo.setStudentid(studentid);
			attvo.setMonth(monthid);
			attvo.setYear(currentyearid);
			attvo.setStudentid1(studentid1);
			attvo.setAccYear(accyear);
			attvo.setDbdetails(custdetails);
			
			if(studentid != null){
				ArrayList<StudentAttendanceVo> attendancelist = new ParentExamdetailsBD().getAttendanceDayList(attvo);
				request.setAttribute("attendancelist", attendancelist);
			}
			
			if(studentid1 != null){
				ArrayList<StudentAttendanceVo> attendancelist1 = new ParentExamdetailsBD().getmoreAttendanceDayList(attvo);
				request.setAttribute("attendancelist1", attendancelist1);
			}
			
			request.setAttribute("studentlist", student);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : getAttendanceView Ending");
		
		return mapping.findForward(MessageConstants.attendanceview);
		
	}
	public ActionForward assignmentdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : assignmentdetails Starting");
		
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
					
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setDbdetails(custdetails);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);
			vo.setDbdetails(custdetails);
			
			ArrayList<AssignmentViewVO> assignmentlist = new ParentExamdetailsBD().getAssignmentListBD(vo);
			
			request.setAttribute("studentlist", student);
			request.setAttribute("accyearid",accyear);
			request.setAttribute("assignmentlist",assignmentlist);
			request.setAttribute("parenthiddenid",vo);
			request.setAttribute("assstudentid",studentid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : assignmentdetails Ending");
		return mapping.findForward(MessageConstants.assignmentdetails);
	}
	
	
	public ActionForward viewAssignmentDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : viewAssignmentDetails Starting");
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			String studentid = request.getParameter("studentid");
			String assgnmentid = request.getParameter("assgnmentid");
			String accyear = request.getParameter("hiddenaccyear");
			AssignmentViewVO assvo = new AssignmentViewVO();
			assvo.setStudentid(studentid);
			assvo.setAssignmentid(assgnmentid);
			assvo.setAccid(accyear);
			
			AssignmentViewVO result = new ParentExamdetailsBD().getviewAssignmentBD(assvo);
			
			request.setAttribute("assdetails", result);
			request.setAttribute("studentid", studentid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : viewAssignmentDetails+" +
				"- Ending");
		return mapping.findForward(MessageConstants.viewassignment);
	}
	
	public ActionForward getmoreassimentlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getmoreassimentlist Starting");
		
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			AssignmentViewVO assvo = new AssignmentViewVO();
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			String studentid=request.getParameter("studentid");
			String hiddenid=request.getParameter("hiddenid");
			
		//	String assgnmentid = request.getParameter("assgnmentid");
		
			assvo.setStudentid(studentid);
			assvo.setHiddenid(hiddenid);
			assvo.setAccid(accyear);
			//assvo.setAssignmentid(assgnmentid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			ArrayList<AssignmentViewVO> assignmentlist = new ParentExamdetailsBD().getmoreAssidnmentListBD(assvo);
			
			request.setAttribute("studentexam", studentid);
			request.setAttribute("assignmentlist", assignmentlist);
			request.setAttribute("studentlist", student);
			request.setAttribute("assstudentid", studentid);
			request.setAttribute("parenthiddenid", vo);
			request.setAttribute("accyearid",accyear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getmoreassimentlist Ending");
		return mapping.findForward(MessageConstants.assignmentdetails);
	}
	
	
	public ActionForward syllabusdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : syllabusdetails Starting");
		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setDbdetails(custdetails);
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			vo.setStudentid(student.get(0).getStudentid());
			vo.setClassDetailId(student.get(0).getClassDetailId());
			vo.setLocid(student.get(0).getLocid());
			vo.setSpecId(student.get(0).getSpecId());
			
			//List<StreamDetailsVO> streamvo =new ParentExamdetailsBD().getStreamListDetails();
			//request.setAttribute("streamlist", streamvo);
			request.setAttribute("stuList",student);
			request.setAttribute("studentData",vo);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : syllabusdetails Ending");
		
		return mapping.findForward(MessageConstants.syllabusdetails);
	}

	
	public ActionForward sendfeedback(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : sendfeedback Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			String userid = HelperClass.getCurrentUserID(request);
			
			ParentVO vo = new ParentVO();
			vo.setDbdetails(custdetails);
			vo.setAccyear(accyear);
			vo.setParentID(userid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);	
			request.setAttribute("studentlist", student);
			
			ParentFeedbackVo fbvo = new ParentFeedbackVo();
			
			ArrayList<ParentFeedbackVo> fdlist = new ParentExamdetailsBD().getFeedBackDetailsBD(fbvo,custdetails);
			
			request.setAttribute("parentid", vo);
			request.setAttribute("feedbacklist", fdlist);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : sendfeedback Ending");
		
		
		return mapping.findForward(MessageConstants.sendfeedback);
		
	}
	

	/*

	
	
	public ActionForward getsyllabus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : sendfeedback Starting");
		
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_STUDENT);
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : sendfeedback Ending");
		
		
		
		return mapping.findForward(MessageConstants.getsyllabus);
		
	}
	*/


	public ActionForward examdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examdetails Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setDbdetails(custdetails);
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);
			vo.setLocid(student.get(0).getLocid());
			vo.setClassDetailId( student.get(0).getClassDetailId());
			vo.setSectionid(student.get(0).getSectionid());
			vo.setDbdetails(custdetails);
			
			ArrayList<ExaminationDetailsVo> examlist = new ParentExamdetailsBD().getExamListDetails(vo);
			
			request.setAttribute("examlist", examlist);
			request.setAttribute("studentlist", student);
			request.setAttribute("parenthiddenid", vo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examdetails Ending");
		
		return mapping.findForward(MessageConstants.examdetails);
	}
	
	public ActionForward getexamlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getexamlist Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			ExaminationDetailsVo exmvo = new ExaminationDetailsVo();
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setDbdetails(custdetails);
		
			
			String studentid=request.getParameter("studentid");
			String hiddenid=request.getParameter("hiddenid");
			vo.setStudentid(studentid);
			exmvo.setStudentid(studentid);
			exmvo.setHiddenid(hiddenid);
		
			ArrayList<ParentVO> data= new ParentSettingsDaoImpl().getStudentNameAndAdmissionNo(vo);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			exmvo.setLocationid(data.get(0).getLocid());
			exmvo.setClassId( data.get(0).getClassid());
			exmvo.setSectionid( data.get(0).getSectionid());
			exmvo.setAccyear(accyear);
			exmvo.setDbdetails(custdetails);
			vo.setStdAdmisiionNo(data.get(0).getStdAdmisiionNo());
			vo.setClassSection(data.get(0).getClassSection());
			vo.setFirstName(data.get(0).getStudentFnameVar());
			
			ArrayList<ExaminationDetailsVo> examslist = new ParentExamdetailsBD().getMoreChildExamListDetails(exmvo);
			
			request.setAttribute("studentexam", studentid);
			request.setAttribute("examlist", examslist);
			request.setAttribute("studentlist", student);
			request.setAttribute("parenthiddenid", vo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getexamlist Ending");
		
		return mapping.findForward(MessageConstants.examdetails);
	}
	
	
	public ActionForward getClassDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getClassDetails Starting");
		
		
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			ClassPojo pojo = new ClassPojo();
			String streamname=request.getParameter("streamname");
			
			pojo.setStreamId(streamname);
			
			List<ClassPojo> classpojo =new ParentExamdetailsBD().getClassListDetails(pojo);

			JSONObject object=new JSONObject();
			 
			 object.put("classlist", classpojo);
			 
			 response.getWriter().print(object);
			 
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getClassDetails Ending");
		
		return null;
	}
	
	public ActionForward getSubjectDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getSubjectDetails Starting");
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ViewallSubjectsVo subvo = new ViewallSubjectsVo();
			
			String classid= request.getParameter("classid");
			String specId = request.getParameter("specId");
			if(specId!=null && specId.trim().equalsIgnoreCase("")){
				specId = "-";
			}
			
			subvo.setClassid(classid);
			subvo.setLocationId(request.getParameter("locid"));
			subvo.setDbdetails(custdetails);
			subvo.setAccyear((String)request.getSession(false).getAttribute("current_academicYear"));
			subvo.setSpecializationId(specId);
			
			List<ViewallSubjectsVo> subjectlist =new ParentExamdetailsBD().getSubjectDetails(subvo);
			//List<StreamDetailsVO> streamvo =new ParentExamdetailsBD().getStreamListDetails();
			//List<ClassPojo> classpojo =new ParentExamdetailsBD().getClassListDetails(pojo);
			
			List<ViewallSubjectsVo> lablist =new ParentExamdetailsBD().getLabDetails(subvo);
		
			JSONObject obj =new JSONObject();
			obj.accumulate("dataList",subjectlist);
			obj.accumulate("labList",lablist);
			response.getWriter().print(obj);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : getSubjectDetails Ending");
		
		///return mapping.findForward(MessageConstants.syllabusdetails);
		return null;
	}
	
	
	
	public ActionForward downloadSubject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : downloadSubject Starting");
		
		try {
			
			String docPath = request.getParameter("subjectid");
			String fileName = "";
			File docFile = new File(request.getServletContext().getRealPath("/")+docPath);
			
			int l = docPath.lastIndexOf('/');
			if (l >= 0)
			  {
				fileName = docPath.substring(l+1);
		      }
			ServletContext ctx = request.getServletContext();
			InputStream fis = new FileInputStream(docFile);
			String mimeType = ctx.getMimeType(docFile.getAbsolutePath());
			
			OutputStream  out = response.getOutputStream();
	        
	        response.setContentType(mimeType != null? mimeType:"application/octet-stream");
			response.setContentLength((int) docFile.length());
			response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+ "\"");
			ServletOutputStream os = response.getOutputStream();
			byte[] bufferData = new byte[1024];
			int read=0;
			while((read = fis.read(bufferData))!= -1)
			{
				os.write(bufferData, 0, read);
			}
			os.flush();    
			os.close();    
			fis.close(); 
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : downloadSubject Ending");
		
		return null;
			
	}
	
	
	public ActionForward downloadFeedback(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : downloadFeedback Starting");
		
		try {
			
			String docPath = request.getParameter("feedback_url");
			String fileName = "";
			
			File docFile = new File(request.getServletContext().getRealPath("/")+docPath);
			
			int l = docPath.lastIndexOf('/');
			if (l >= 0)
			  {
				fileName = docPath.substring(l+1);
		      }
			ServletContext ctx = request.getServletContext();
			InputStream fis = new FileInputStream(docFile);
			String mimeType = ctx.getMimeType(docFile.getAbsolutePath());
			
			 OutputStream  out = response.getOutputStream();
	        
	        response.setContentType(mimeType != null? mimeType:"application/octet-stream");
			response.setContentLength((int) docFile.length());
			response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+ "\"");
			ServletOutputStream os = response.getOutputStream();
			byte[] bufferData = new byte[1024];
			int read=0;
			while((read = fis.read(bufferData))!= -1)
			{
				os.write(bufferData, 0, read);
			}
			os.flush();    
			os.close();    
			fis.close();   
		 
			
			
			/*String fbid = request.getParameter("feedbackId");
			
			String filepath = new ParentExamdetailsBD().getfeedbackfilepath(fbid);
			
			String fileName = "FileName";
			fileName=filepath;
			
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			File docFile = new File(request.getRealPath("/") + filepath);
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}*/
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : downloadFeedback Ending");
		
		
		return null;
	
	}
	
	
	public ActionForward downloadTCfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : downloadTCfile Starting");
		
		try {
			String docPath = request.getParameter("Path");
			response.setContentType("application/octet-stream");
			String fileName = "FileName";
			fileName=docPath;
			
			
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			File docFile = new File(request.getRealPath("/") + docPath);
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : downloadTCfile Ending");
		
		return null;
			
	}
	
	
	
	public ActionForward meetingandeventsdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : meetingandeventsdetails Starting");
		
		try {
			
			

			
			

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD()
			.getStudentlist(vo);	
			request.setAttribute("studentlist", student);
		
	List<LstmsUpcomingMeetingVO> meetinglist =new ParentExamdetailsBD().getMeetingListDetails();
	
	
	
			
			request.setAttribute("meetinglist", meetinglist);
			
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : meetingandeventsdetails Ending");
		
		return mapping.findForward(MessageConstants.getmeetingandevent);
		
	}
	
	
	
	public ActionForward getstudentmeetinglist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getstudentmeetinglist Starting");
		
		try {
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(userid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD()
			.getStudentlist(vo);	
			request.setAttribute("studentlist", student);
		   
			LstmsUpcomingMeetingVO meetvo = new LstmsUpcomingMeetingVO();
			
			String studentid=request.getParameter("studentid");
			String parentid1=request.getParameter("hiddenid");
			
			
			meetvo.setStudentid(studentid);
			meetvo.setParentid(parentid1);
			
			List<LstmsUpcomingMeetingVO> meetinglist =new ParentExamdetailsBD().getstudentmeetinglistBD(meetvo);	
		
			request.setAttribute("studentmeeting", studentid);
			request.setAttribute("meetinglist", meetinglist);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getstudentmeetinglist Ending");
		
		return mapping.findForward(MessageConstants.getmeetingandevent);
		
	}
	
	public ActionForward studentTimeTable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : studentTimeTable Starting");
		
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			
	      	vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setDbdetails(custdetails);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);	
			request.setAttribute("studentlist", student);
			request.setAttribute("classid", student.get(0).getClassDetailId());
			request.setAttribute("secid", student.get(0).getSectionid());
			request.setAttribute("locid", student.get(0).getLocid());
		
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	    
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : studentTimeTable Ending");
		
		return mapping.findForward(MessageConstants.studentTimeTable);
	
	}
	
	public ActionForward getStudentTimetable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentTimetable Starting");
		
		try {
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ParentVO vom = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
	      	vom.setParentID(userid);
			vom.setAccyear(accyear);
			vom.setStudentid(request.getParameter("studentid"));
			vom.setClassDetailId(request.getParameter("classid"));
			vom.setSectionid(request.getParameter("secid"));
			vom.setLocid(request.getParameter("locid"));
			vom.setDbdetails(custdetails);
			
			int noofpeiods = new StudentAttendanceBD().getperiodcount(vom.getLocid(),vom.getClassDetailId(),custdetails);
			
			//ArrayList<TimeTableVo> timeTableDetails= new ParentExamdetailsBD().getFirstStudentTimeTableBD(vom);
			
			ArrayList<TimeTableVo> gettimetabledetails = new ParentSettingsDaoImpl().getTimeTableDetails(vom);
			
			System.out.println("List Size : "+gettimetabledetails.size());
			
			/*List<TimeTableVo> todaytimeTableDetailsList=new ArrayList<TimeTableVo>();
			ArrayList<String> dayname = new ArrayList<String>();
			 HashMap<String, String> map = new HashMap<>();
			for(int i=0;i<timeTableDetails.size();i++){
				
				TimeTableVo vo=new TimeTableVo();
				vo.setDayid(timeTableDetails.get(i).getDayid().toUpperCase());
				vo.setDayname(timeTableDetails.get(i).getDayname());
				vo.setPeriod1(timeTableDetails.get(i).getPeriod().get("period1"));
				vo.setPeriod2(timeTableDetails.get(i).getPeriod().get("period2"));
				vo.setPeriod3(timeTableDetails.get(i).getPeriod().get("period3"));
				vo.setPeriod4(timeTableDetails.get(i).getPeriod().get("period4"));
				vo.setPeriod5(timeTableDetails.get(i).getPeriod().get("period5"));
				vo.setPeriod6(timeTableDetails.get(i).getPeriod().get("period6"));
				vo.setPeriod7(timeTableDetails.get(i).getPeriod().get("period7"));
				vo.setPeriod8(timeTableDetails.get(i).getPeriod().get("period8"));
				vo.setPeriod9(timeTableDetails.get(i).getPeriod().get("period9"));
				todaytimeTableDetailsList.add(vo);
				Collections.sort(todaytimeTableDetailsList,HelperClass.TimeTableVoComparator);
				dayname.add(timeTableDetails.get(i).getDayid().toUpperCase());
			}
			
			HashSet<String> hashSet = new HashSet<String>();
			hashSet.addAll(dayname);
			dayname.clear();
			dayname.addAll(hashSet);
			dayname.sort((p1, p2) -> p1.compareTo(p2));

			System.out.println(dayname);
			for(String s:dayname){
				System.out.println(s);
			}
			*/
			JSONObject obj = new JSONObject();
			obj.put("List", gettimetabledetails);
			//obj.put("Days", dayname);
			obj.put("noofper",noofpeiods);
			response.getWriter().print(obj);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentTimetable Ending");
		return null;
	}
	
	
	public ActionForward requestLeavescreenadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : requestLeavescreenadd Starting");
		
		
		try {
		
				
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false)
					.getAttribute("UserDetails");
			String parentid = userDetailVO.getUserId();
			
			
			ParentVO vo = new ParentVO();
			/*String parentid = HelperClass.getCurrentUserID(request);*/
			
			vo.setParentID(parentid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD()
			.getStudentlist(vo);
			
			request.setAttribute("studentlist", student);
			
			request.setAttribute("parentid", vo);
			
			/*UserDetailVO vo = new UserDetailVO();
			
			String userid = request.getParameter("hiddenid");
			vo.setUserId(userid);*/
			
		
			
         List<UserDetailVO> userlist =new ParentExamdetailsBD().getRequestUserListDetails(parentid);
			

         request.setAttribute("userlist", userlist);
          
          
          
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : requestLeavescreenadd Ending");

		
		return mapping.findForward(MessageConstants.LEAVEREQUESTSCREEN);
		
	}
	
	
	
	public ActionForward leaveRequestEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : leaveRequestEntry Starting");
		
		
	

		String path = null;
		String extension = null;
		File fileURL = null;
		FileOutputStream fos = null;
		
		try {
			LeaveRequestForm leaveform =(LeaveRequestForm)form;
			LeaveRequestVo leavevo = new LeaveRequestVo();
			String parentid = HelperClass.getCurrentUserID(request);
			leaveform.setCreateuser(parentid);
			
			/*UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false)
					.getAttribute("UserDetails");
			String parentid = userDetailVO.getUserId();*/
			
			/*leaveform.setCreateuser(HelperClass.getCurrentUserID(request));*/
			
			ParentVO vo = new ParentVO();
			
			vo.setParentID(parentid);
			
		/*	leaveform.setCreateuser(parentid);*/
		/*	
			String createuser=HelperClass.getCurrentUserID(request);*/
			
			
			
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
		   List<UserDetailVO> userlist =new ParentExamdetailsBD().getRequestUserListDetails(HelperClass.getCurrentUserID(request));
			

           request.setAttribute("userlist", userlist);
			
           request.setAttribute("studentlist", student);
			
			request.setAttribute("parentid", vo);
			
			
		
			
		/*	
		    leaveform.setCreateuser(HelperClass.getCurrentUserID(request));
			
			
         	ParentVO vo = new ParentVO();
			//String parentid = HelperClass.getCurrentUserID(request);
			
			vo.setParentID(parentid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD()
			.getStudentlist(vo);
			
			
			String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);
			leaveform.setStudentid(studentid);*/
         
			
			
			
			
			
			FormFile formFile = leaveform.getFileupload();
			
			
			if(formFile.getFileSize()>0){
				
				path = getServlet().getServletContext().getRealPath("/")+ "FIles\\LEAVEREQUEST";
				File files = new File(path);
				if (!files.exists()) {
					if (files.mkdirs()) {
					}
				}
				path = files.getAbsolutePath();
				
				int i = formFile.getFileName().lastIndexOf('.');
				if (i > 0) {
					extension = formFile.getFileName().substring(i + 1);
				}
				
				String fileName = HelperClass.getCurrentSqlDate() + "_"	+ formFile ;
				fileURL = new File(path, fileName);
	
				fos = new FileOutputStream(fileURL);
				fos.write(formFile.getFileData());
	
				String file1 = fileURL.getPath();
	
				file1 = file1.substring(file1.indexOf("FIles\\"));
	
				if (!fileURL.exists()) {
					FileOutputStream fileOutStream = new FileOutputStream(fileURL);
	
					fileURL.mkdir();
					fileOutStream.write(formFile.getFileData());
	
					fileOutStream.flush();
	
					fileOutStream.close();
				}
				
				leavevo.setFileupload(file1);
				
				
			}
			else{
				
				leavevo.setFileupload(leaveform.getFileupload1());
				
			
				
			}
			
			
			leavevo.setRequestto(leaveform.getRequestto());
			leavevo.setTotalleave(leaveform.getTotalleave());
			leavevo.setFromdate(HelperClass.convertUIToDatabase(leaveform.getFromdate()));
			leavevo.setTodate(HelperClass.convertUIToDatabase(leaveform.getTodate()));
			leavevo.setStarttime(leaveform.getStarttime());
			leavevo.setEndtime(leaveform.getEndtime());
			leavevo.setLeavetype(leaveform.getLeavetype());
			leavevo.setReason(leaveform.getReason());
			leavevo.setCreateuser(leaveform.getCreateuser());
			leavevo.setStudentFname(leaveform.getStudentFname());
			
			leavevo.setSno(leaveform.getSno());
			
			
		
			
			String result =  new ParentLeaveRequestBD()
			.leaveRequestEntryBD(leavevo);
			
			
			
			/*  List<UserDetailVO> userlist =new TeacherLeaveModuleBD().getRequestUserListDetails(HelperClass.getCurrentUserID(request));
		         request.setAttribute("userlist", userlist);*/
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : leaveRequestEntry Ending");
		
		
		return mapping.findForward(MessageConstants.LEAVEREQUESTSCREEN);
	}
	
	
	
	public ActionForward editRequestLeaveAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : editRequestLeaveAction Starting");
		
		try {
			
			
			String parentid = HelperClass.getCurrentUserID(request);
			ParentVO vo = new ParentVO();
			
			vo.setParentID(parentid);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			  request.setAttribute("studentlist", student);
			
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false)
					.getAttribute("UserDetails");
			String user = userDetailVO.getUserId();
			
		
         List<UserDetailVO> userlist =new TeacherLeaveModuleBD().getRequestUserListDetails(user);
			

         request.setAttribute("userlist", userlist);
		
			String sno =(String) request.getParameter("snoid");
		
			LeaveRequestVo result = new ParentLeaveRequestBD().getRequestLeaveBD(Integer.parseInt(sno));
			
			
			request.setAttribute("leavedatails", result);
		
		
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : editRequestLeaveAction Ending");
		
		
		return mapping.findForward(MessageConstants.LEAVEREQUESTSCREEN);
	}
	
	
	
	public ActionForward downloadfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadfile Starting");
		
	try {
			
			String docPath = request.getParameter("Path");
			response.setContentType("application/octet-stream");
			String fileName = "FileName";
			fileName=docPath;
			
			
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			
			File docFile = new File(request.getRealPath("/") + docPath);
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : downloadfile Ending");
		return null;
	}
		
	public ActionForward circularRemainderParentAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : circularRemainderParentAction Starting");
		
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT); 
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			List<RemainderMasterVO> remainderlist =new ParentLeaveRequestBD().getRemainderlistBD(custdetails);	
			request.setAttribute("remainderlist", remainderlist);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenuAction : circularRemainderParentAction Ending");
		
		return mapping.findForward(MessageConstants.parentRemainder);
	}
	
	public ActionForward studentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			StudentRegistrationVo studentinfo = new StudentRegistrationVo();
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setDbdetails(custdetails);
			
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);
			
			studentinfo = new ParentExamdetailsBD().getStudentInfoBD(vo);
			
			request.setAttribute("studentdetails", studentinfo);
			request.setAttribute("studentlist",student);
		
			request.setAttribute("parenthiddenid", vo);
			request.setAttribute("studentexam", studentid);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Ending");

		return mapping.findForward(MessageConstants.PARENT_STUDENT_LIST);
	}
	
	
	public ActionForward onlinefeePayment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userType=userDetailVO.getUserNametype();
			String userCode=userDetailVO.getUserId();
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_PARENT_ONLINE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);

			List<OnlinePaymentVo> List = new OnlineFeePaymentDelegate().getStudentOnlineFeePaymentDetails(userType,userCode,custdetails);
			request.setAttribute("OnlineFeePayment", List);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Ending");

		return mapping.findForward(MessageConstants.PARENT_ONLINE_PAYMENT);
	}
	
	public ActionForward leaveRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : leaveRequest Starting");
		try {

			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String usertype=userDetailVO.getRoleCode();
			String empId=userDetailVO.getUserId();
			String searchTerm = request.getParameter("searchTerm");

			ArrayList<LeaveRequestVo> leavelist = new ArrayList<LeaveRequestVo>();

			ParentExamdetailsBD leavedeligate = new ParentExamdetailsBD();

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TEACHERS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);

			String parentid = HelperClass.getCurrentUserID(request);

			request.setAttribute("parentid", parentid);

			

			LeaveRequestVo leavevo = new LeaveRequestVo();

			if (searchTerm != null) {
				

				leavelist = leavedeligate.searchleaverequest(parentid,searchTerm);

				request.setAttribute("searchterm", searchTerm);
			} else {

				
					leavelist = leavedeligate.getleaveRequestDetailsBD(leavevo,empId,usertype);	
				
			}

			request.setAttribute("leavelist", leavelist);
			request.getSession(false).setAttribute("excelDownLoad", leavelist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherMenuAction : leaveRequest Ending");

		return mapping.findForward(MessageConstants.LEAVEREQUEST);
	}
	
	public ActionForward viewExamdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : viewExamdetails Starting");
		try{
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			String examid = request.getParameter("examid");
			String classid= request.getParameter("classid");
			String sectionid = request.getParameter("secid");
			String locid= request.getParameter("locid");
			
			ParentVO obj = new ParentVO();
			obj.setAccyear(accyear);
			obj.setLocid(locid);
			obj.setExamid(examid);
			obj.setSectionid(sectionid);
			obj.setClassDetailId(classid);
			obj.setDbdetails(custdetails);
			
			request.setAttribute("dataList",obj);
			obj = new ParentExamdetailsBD().getexamname(obj);
			request.setAttribute("examlist", obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : viewExamdetails Ending");
		return mapping.findForward(MessageConstants.VIEWEXAMDETAILS);
	}
	public ActionForward displayExamdetail(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in ParentMenu : viewExamdetails Starting");
				try{
					String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
					String examid = request.getParameter("examid");
					String classid= request.getParameter("classid");
					String sectionid = request.getParameter("secid");
					String locid= request.getParameter("locid");
					
					ParentVO obj = new ParentVO();
					obj.setAccyear(accyear);
					obj.setLocid(locid);
					obj.setExamid(examid);
					obj.setSectionid(sectionid);
					obj.setClassDetailId(classid);
					
					ArrayList<ParentVO> list = new ArrayList<ParentVO>();
					list = new ParentExamdetailsBD().viewExamdetails(obj);
					JSONObject jsonobj = new JSONObject();
					jsonobj.put("dataList",list);
					response.getWriter().print(jsonobj);
					
				}catch(Exception e){
					e.printStackTrace();
				}

				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in ParentMenu : viewExamdetails Ending");
				return null;
			}
	
	public ActionForward getStudentDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : viewExamdetails Starting");
		try{
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			String stuid = request.getParameter("studid");
			
			ParentVO obj =new ParentVO();
			obj.setAccyear(accyear);
			obj.setStudentid(stuid);
			obj.setDbdetails(custdetails);
			
			obj = new ParentExamdetailsBD().getStudentDetails(obj);
			
			JSONObject json = new JSONObject();
			json.put("details", obj.getClassDetailId()+" "+obj.getLocid()+" "+obj.getSectionid());
			response.getWriter().print(json);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : viewExamdetails Ending");
		return null;
	}
	
	public ActionForward getStuDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : getStuDetails Starting");
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_STUDENT);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
		StudentRegistrationVo studentinfo = new StudentRegistrationVo();
		ParentVO vo = new ParentVO();
		String userid = HelperClass.getCurrentUserID(request);
		vo.setParentID(userid);
		vo.setAccyear(accyear);
		vo.setDbdetails(custdetails);
		
		ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
		
		String studentid = student.get(0).getStdAdmisiionNo();
		vo.setStudentid(studentid);
		vo.setStudentid(request.getParameter("studentid"));
		vo.setAccyear(accyear);
		
		studentinfo = new ParentExamdetailsBD().getStudentInfoBD(vo);
		request.setAttribute("studentdetails", studentinfo);
		request.setAttribute("studentlist", student);
		request.setAttribute("studentid", request.getParameter("studentid"));
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : getStuDetails Ending");
		return mapping.findForward(MessageConstants.STUDENT_INFORMATION_LIST);
	}
	
	public ActionForward viewResultDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : viewResultDetails Starting");
		try{
			String[] tableheader;
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			String examid = request.getParameter("examid");
			String classid= request.getParameter("classid");
			String sectionid = request.getParameter("secid");
			String locid= request.getParameter("locid");
			String examprfx = request.getParameter("examprfx");
			
			ParentVO obj = new ParentVO();
			obj.setAccyear(accyear);
			obj.setLocid(locid);
			obj.setExamid(examid);
			obj.setSectionid(sectionid);
			obj.setClassDetailId(classid);
			obj.setDbdetails(custdetails);
			obj.setExmprfx(examprfx);
			
			request.setAttribute("dataList",obj);
			obj = new ParentExamdetailsBD().getexamname(obj);
			request.setAttribute("examlist", obj);
			
			/*if(examprfx!=null && examprfx.trim().equalsIgnoreCase("hlfym")){
				tableheader[] = {"Note Book","Subject Enrichment","Yearly Marks"};
			}*/
			
			
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : viewResultDetails Ending");
		return mapping.findForward(MessageConstants.VIEWRESULTDETAILS);
	} 
	public ActionForward showExamResult(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in ParentMenu : showExamResult Starting");
	try{
					
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
					
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
					
		String classId = request.getParameter("classId");
		String locationId = request.getParameter("locationId");
		String accyearId = request.getParameter("accyearId");
		String term1 = request.getParameter("terms1");
		String term2 = request.getParameter("terms2");
		String examstypeid = request.getParameter("examstypeid");
		String examstypeidterm2 = request.getParameter("examstypeidterm2");
		String stdId = request.getParameter("stdId");
		String sectionId = request.getParameter("sectionId");
			
		ReportMenuVo vo=new ReportMenuVo();
		vo.setAccyear(accyearId);
		vo.setClassId(classId);
		vo.setLocationId(locationId);
		//vo.setTermname(checkedTermValue);
		vo.setExamtypeid(examstypeid);
		vo.setExamstypeidterm2(examstypeidterm2);
		vo.setStudentId(stdId);
		vo.setSectionId(sectionId);
		vo.setTerm1(term1);
		vo.setTerm2(term2);
		
		List<ReportMenuVo> stuList = new ReportsMenuBD().getTermWiseReportCard(vo,custdetails);
					
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ParentMenu : showExamResult Ending");
		return mapping.findForward(MessageConstants.VIEWRESULTDETAILS);
	}
	
	public ActionForward feedbackentry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feedbackentry Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_STUDENT);



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feedbackentry Ending");


		return mapping.findForward(MessageConstants.feedbackentry);

	}

	public ActionForward savefeedback(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : savefeedback Starting");

		String path = null;
		String extension = null;
		File fileURL = null;
		FileOutputStream fos = null;

		try {

			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			
			LeaveRequestForm fbform = (LeaveRequestForm)form; 
			ParentFeedbackVo fbvo = new ParentFeedbackVo();
			String createUser = "Parent";

			String fbid = IDGenerator.getPrimaryKeyID("campus_feedback",pojo);
			fbform.setFeedbackid(fbid);
			fbform.setCreateUser(createUser);

			FormFile formFile = fbform.getAddfile();

			if(formFile.getFileSize()>0){

				path = getServlet().getServletContext().getRealPath("/")+ "FIles\\FEEDBACK";
				File files = new File(path);
				if (!files.exists()) {
					if (files.mkdirs()) {
					}
				}
				path = files.getAbsolutePath();

				int i = formFile.getFileName().lastIndexOf('.');
				if (i > 0) {
					extension = formFile.getFileName().substring(i + 1);
				}

				String fileName = HelperClass.getCurrentSqlDate() + "_"	+ formFile ;
				fileURL = new File(path, fileName);

				fos = new FileOutputStream(fileURL);
				fos.write(formFile.getFileData());

				String file1 = fileURL.getPath();

				file1 = file1.substring(file1.indexOf("FIles\\"));

				if (!fileURL.exists()) {
					FileOutputStream fileOutStream = new FileOutputStream(fileURL);

					fileURL.mkdir();
					fileOutStream.write(formFile.getFileData());

					fileOutStream.flush();

					fileOutStream.close();
				}

				fbvo.setAddfile(file1);
			}
			else{

				fbvo.setAddfile(fbform.getAddfile1());
			}

			fbvo.setFeedbackid(fbform.getFeedbackid());
			fbvo.setFeedbackto(fbform.getFeedbackto());
			fbvo.setDescription(fbform.getDescription());
			fbvo.setCreateUser(fbform.getCreateUser());
			fbvo.setDbdetails(pojo);


			String result =  new ParentExamdetailsBD().saveFeedBackDetails(fbvo);
			
			JSONObject json = new JSONObject();
			json.put("status", result);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : savefeedback Ending");

		return null;

	}
	public ActionForward studentanalyticalperformance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : studentanalyticalperformance Starting");
		try{
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			String stuid = request.getParameter("stuId");
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setStudentid(stuid);
			vo.setDbdetails(pojo);
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
             /*ParentVO name= new ParentSettingsDaoImpl().getStudentNameAndAdmissionNo(vo);
			request.setAttribute("name",name);*/
			
			request.setAttribute("admissiono",student.get(0).getStdAdmisiionNo());
			request.setAttribute("classSection",student.get(0).getClassSection());
			request.setAttribute("studentname",student.get(0).getStudentFnameVar());
			request.setAttribute("locid",student.get(0).getLocid()); 
			request.setAttribute("classid",student.get(0).getClassDetailId());
			request.setAttribute("sectionid",student.get(0).getSectionid());
			request.setAttribute("accyear",student.get(0).getAccyear());
			
			String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);
			
			request.setAttribute("stuList",student);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : studentanalyticalperformance Ending");
		return mapping.findForward(MessageConstants.PARENTS_STUDENT_ANALYTICAL_PERFORMANCE);
	}	
	
	public ActionForward Savebuttonhidefunction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : Savebuttonhidefunction Starting");
		
		try{
			String accyear = request.getParameter("accyearId");
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			String stuid = request.getParameter("stuId");
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setStudentid(stuid);
			vo.setDbdetails(pojo);
			
			String  status= new ParentSettingsDaoImpl().Savebuttonhidefunction(vo);
			
			JSONObject json = new JSONObject();
			json.put("status",status);
			response.getWriter().print(json);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : Savebuttonhidefunction Ending");
		return null;
	}
	public ActionForward studentanalyticalperformanceBYStudentId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : studentanalyticalperformance Starting");
		
		try{
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			String stuid = request.getParameter("stuId");
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setStudentid(stuid);
			vo.setDbdetails(pojo);
			
			ArrayList<ParentVO> list = new ParentExamdetailsBD().getStudentAnalyticalPerformanceForParent(vo);
			
			JSONObject json = new JSONObject();
			json.put("studentAnalyticPerformanceList", list);
			response.getWriter().print(json);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : studentanalyticalperformance Ending");
		return null;
	}
	
	public ActionForward getStudentNameAndAdmissionNo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : studentanalyticalperformance Starting");
		
		try{
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			String stuid = request.getParameter("stuId");
			vo.setParentID(userid);
			vo.setAccyear((String)request.getSession(false).getAttribute("current_academicYear"));
			vo.setStudentid(stuid);
			vo.setDbdetails(pojo);
			
			ArrayList<ParentVO> name= new ParentSettingsDaoImpl().getStudentNameAndAdmissionNo(vo);
			
			JSONObject json = new JSONObject();
			json.put("data",name);
			response.getWriter().print(json);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : studentanalyticalperformance Ending");
		return null;
	}
	
	public ActionForward getStudentAnalyticalPerformanceDetailForUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : getStudentAnalyticalPerformanceDetailForUpdate Starting");
		try{
			String accyear = request.getParameter("accyearId");
			String stuid = request.getParameter("stuId");
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			ParentVO obj =new ParentVO();
			obj.setAccyear(accyear);
			obj.setStudentid(stuid);
			obj.setDbdetails(pojo);
			
			ArrayList<ParentVO> list = new ParentSettingsDaoImpl().getStudentAnalyticalPerformanceDetailForUpdate(obj);
			
			JSONObject json = new JSONObject();
			json.put("list", list);
			response.getWriter().print(json);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : getStudentAnalyticalPerformanceDetailForUpdate Ending");
		return null;
	}
	public ActionForward SaveStudentAnalyticalPerformanceByParent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : SaveStudentAnalyticalPerformanceByParent Starting");
		try{
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			UserLoggingsPojo pojo = (UserLoggingsPojo )request.getSession(false).getAttribute("token_information");
			
			String stuid = request.getParameter("stuId");
			String opinion[] = request.getParameter("opinion").split(",");
			String locId = request.getParameter("locId");
			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			String userid = HelperClass.getCurrentUserID(request);
			
			ParentVO obj =new ParentVO();
			obj.setAccyear(accyear);
			obj.setStudentid(stuid);
			obj.setOpinion(opinion);
			obj.setLocid(locId);
			obj.setClassid(classId);
			obj.setSectionid(sectionId);
			obj.setUserName(userid);
			obj.setDbdetails(pojo);
			
			String status = new ParentExamdetailsBD().SaveStudentAnalyticalPerformanceByParent(obj);
			
			JSONObject json = new JSONObject();
			json.put("status", status);
			response.getWriter().print(json);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : SaveStudentAnalyticalPerformanceByParent Ending");
		return null;
	} 
	
	public ActionForward studentPerformance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : studentPerformance Starting");
		try{
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			
			ParentVO vo = new ParentVO();
			String userid = HelperClass.getCurrentUserID(request);
			vo.setParentID(userid);
			vo.setAccyear(accyear);
			vo.setDbdetails(custdetails);
			ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(vo);
			
			String studentid = student.get(0).getStdAdmisiionNo();
			vo.setStudentid(studentid);
			vo.setLocid(student.get(0).getLocid());
			vo.setClassDetailId(student.get(0).getClassDetailId());
			vo.setSectionid(student.get(0).getSectionid());
			vo.setDbdetails(custdetails);
			vo.setStdAdmisiionNo(student.get(0).getStdAdmisiionNo());
			vo.setClassSection(student.get(0).getClassSection());
			vo.setFirstName(student.get(0).getStudentFnameVar());
			
			ArrayList<ExaminationDetailsVo> examlist = new ParentExamdetailsBD().getExamListDetails(vo);
			
			request.setAttribute("examlist", examlist);
			request.setAttribute("stuList", student);
			request.setAttribute("parenthiddenid", vo);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : studentPerformance Ending");
		return mapping.findForward(MessageConstants.STUDNETPERFORMANCE);
	}

	public ActionForward onlinefeetransactionId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse hresponse)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : onlinefeetransactionId Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		
		try{
			String studentId = request.getParameter("studentId");
			String feeCode= request.getParameter("feeCode");
			String termcode=request.getParameter("termcode");
			String bank=request.getParameter("bank");
			String grandtotal=request.getParameter("grandtotal");
			String fineAmt=request.getParameter("fineAmt");
			String bankcode=request.getParameter("bankcode");
			
			
			request.getSession(false).setAttribute("hstudentId", studentId);
			request.getSession(false).setAttribute("hfeeCode", feeCode);
			request.getSession(false).setAttribute("htermcode", termcode);
			request.getSession(false).setAttribute("hbank",bank);
			request.getSession(false).setAttribute("hgrandtotal", grandtotal);
			request.getSession(false).setAttribute("hfineAmt", fineAmt);
			request.getSession(false).setAttribute("hbankcode", bankcode);
			String tranID="";
			try {
				tranID = IDGenerator.getPrimaryKeyID("online_transactionid_table",custdetails);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			HttpSession session = request.getSession(true);
			session.setAttribute("transactionId", tranID);
		
		
		
			 
			 Gateway gateway = GatewayFactory.getGateway(AvailableGateways.AUTHORIZE);
			 gateway.setTestMode(true);
			 
			 JSONObject apiSampleParameters = gateway.getApiSampleParameters();
			    System.out.println(apiSampleParameters);
			  
			    
			    apiSampleParameters.put("name", "");
			    apiSampleParameters.put("transactionKey", "");
			    
			    
			    Customer customer = new Customer();
		        
			    customer
			        .setFirstName("test first name")
			        .setLastName("test last name")
			        .setCountry(Country.IND)
			        .setState("TX")
			        .setCity("test city")
			        .setAddress("test address")
			        .setZip("12345")
			        .setPhoneNumber("1234567890")
			        .setEmail("email@domain.com")
			        .setIp("127.0.0.1");
			        
			    CustomerCard customerCard = new CustomerCard();
			    
			    customerCard
			        .setName("MD KAMRAN ANSARI")
			        .setNumber("5196190291757146") //Authorize test card
			        .setCvv("278")
			        .setExpiryMonth("10")
			        .setExpiryYear("2035");
			    
			    HTTPResponse purchaseResponse = gateway.purchase(apiSampleParameters, customer, customerCard, Currency.INR, 10);	    
			    if(purchaseResponse.isSuccessful()){
			        //some code
			    }
			    
			    
			    System.out.println(purchaseResponse.getJSONResponse());
			    
			    //output
			    /*{
			        "lr": {
			            "success": true,
			            "message": "SUCCESS",
			            "transactionId": "3902990127",
			            "amount": 45,
			            "cardExpiryYear": "2017",
			            "cardFirst6": "601160",
			            "cardExpiryMonth": "12",
			            "maskedCard": "601160******6611",
			            "rebillParams": {
			                "customerVaultId": "174302554"
			            },        
			            "voidParams": {
			                "transactionId": "3902990127"
			            },
			            "currencyCode": "USD",
			            "cardLast4": "6611",
			            "refundParams": {
			                "transactionId": "3902990127"
			            }
			        },
			        "gr": { // long gateway response }
			    }*/
			    
		 
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : onlinefeetransactionId Ending");
		return null;
	}
	
public ActionForward getNextChildPerformanceDetails(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in ParentMenu : studentPerformance Starting");
	try{
				
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		ParentVO vo = new ParentVO();
		String userid = HelperClass.getCurrentUserID(request);
		String stuid = request.getParameter("stuId");
		vo.setParentID(userid);
		vo.setAccyear((String)request.getSession(false).getAttribute("current_academicYear"));
		vo.setStudentid(stuid);
		vo.setDbdetails(custdetails);
		
		ArrayList<ParentVO> data= new ParentSettingsDaoImpl().getStudentNameAndAdmissionNo(vo);
		
		vo.setStudentid(stuid);
		vo.setLocid(data.get(0).getLocid());
		vo.setClassDetailId(data.get(0).getClassid());
		vo.setSectionid(data.get(0).getSectionid());
				
		ArrayList<ExaminationDetailsVo> examslist = new ParentExamdetailsBD().getExamListDetails(vo);
		
		JSONObject json = new JSONObject();
		json.put("examlist", examslist);
		json.put("student", data);
		response.getWriter().print(json);

	}catch(Exception e){
			e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
	+ " Control in ParentMenu : studentPerformance Ending");
		return null;
	}		
					
	public ActionForward viewPerformanceDetails(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
							throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
		+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
								+ " Control in ParentMenu : studentPerformance Starting");
			try{
				
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_STUDENT);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_STUDENT);
				
				String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
				UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				String examid = request.getParameter("examid");
				String exampfx = request.getParameter("examprefx");
				String stuId = request.getParameter("stuId");
				
				
				ParentVO vo = new ParentVO();
				String userid = HelperClass.getCurrentUserID(request);
				String stuid = request.getParameter("stuId");
				vo.setParentID(userid);
				vo.setAccyear(accyear);
				vo.setStudentid(stuid);
				vo.setDbdetails(custdetails);
				
				ArrayList<ParentVO> data= new ParentSettingsDaoImpl().getStudentNameAndAdmissionNo(vo);
				
				request.setAttribute("examid",examid);
				request.setAttribute("exampfx",exampfx);
				request.setAttribute("stuId",stuId);
				request.setAttribute("accyear",accyear);

			}catch(Exception e){
					e.printStackTrace();
			}
			JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
			+ " Control in ParentMenu : studentPerformance Ending");
				return mapping.findForward(MessageConstants.VIEW_STU_PERFORMANCE_DETAILS);
		}
	
	public ActionForward getPerformanceDetails(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
		+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
						+ " Control in ParentMenu : studentPerformance Starting");
		
		try{
			String accyear = (String)request.getSession(false).getAttribute("current_academicYear");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String examid = request.getParameter("examid");
			String exampfx = request.getParameter("examprefx");
			String stuId = request.getParameter("stuId");
			
			ExaminationDetailsVo exam = new ExaminationDetailsVo();
			exam.setAccyear(accyear);
			exam.setStudentid(stuId);
			exam.setExamid(examid);
			exam.setExamtypeprefix(exampfx);
			exam.setDbdetails(custdetails);
			
			ExaminationDetailsVo details = new ParentSettingsDaoImpl().viewPerformanceDetails(exam);
			
			System.out.println(details.getSubName());
			System.out.println(details.getTotscoredmarks());
			
			JSONObject json = new JSONObject();
			json.put("subnames", details.getSubName());
			json.put("scoredmarks",details.getTotscoredmarks());
			response.getWriter().print(json);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
