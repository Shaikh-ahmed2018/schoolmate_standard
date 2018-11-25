package com.centris.campus.actions;
import java.io.File;
import java.net.URI;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.daoImpl.LoginDaoImpl;
import com.centris.campus.daoImpl.ParentRequiresAppointmentDAOIMPL;
import com.centris.campus.delegate.ExamTimeTableBD;
import com.centris.campus.delegate.LoginBD;
import com.centris.campus.delegate.ParentLeaveRequestBD;
import com.centris.campus.delegate.ParentRequiresAppointmentDELEGATE;
import com.centris.campus.delegate.StudentEnquiryBD;
import com.centris.campus.forms.LoginForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.HelperClassVo;
import com.centris.campus.vo.LoginVo;
import com.centris.campus.vo.ParentRequiresAppointmentVO;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.StudentAttendanceReportVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.UserLoggingsVo;
import com.cerp.campus.apiservices.ApiCallService;


public class LoginAction extends DispatchAction {
	
	private static final Logger logger = Logger.getLogger(LoginAction.class);
	static ResourceBundle res = ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	private static String licser_service = res.getString("licser_service");
	
	
	public ActionForward checkValidateuser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : checkValidateuser  Starting");
		try {
			
			HttpSession session = request.getSession();
			
			String userName = request.getParameter("UserName");
			String Password = request.getParameter("password");
			
			//get the application URL
			URI contextUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath());
			String domainName = contextUrl.toString().split("//")[1].split(":")[0];
			
			//Check Authorization
			UserLoggingsPojo pojo = new UserLoggingsPojo();
			pojo = new ApiCallService().validateCustomer(userName,Password,domainName);
				
			if(pojo.getStatus().equals("licvalid")) {
				
				LoginBD loginBD = new LoginBD();
				LoginVo loginVo = loginBD.validateUserBD(userName,Password,pojo);
				pojo.setStatus(loginVo.getStatus());
				System.out.println("loginVo.getStatus()==="+loginVo.getStatus());
				
				
				
				// for testing purpose
				pojo.setNoOfStudent(Long.parseLong(pojo.getNoofstudents()));
				
				session.setAttribute("token_information",pojo);
			}
			JSONObject object = new JSONObject();
			object.put("Status", pojo.getStatus());
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : checkValidateuser Ending");

		return null;

	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login  Starting");

		String userdefined = null;

		try {
			
			HttpSession session = request.getSession(true);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			createSchoolInfoFolder(request,custdetails.getDomain());
			
			LoginForm lform = (LoginForm) form;
			String userName = lform.getUsername();
			String Password = lform.getPassword();
			
			UserLoggingsPojo pojo = new UserLoggingsPojo();
			pojo.setIpaddress(custdetails.getIpaddress());
			pojo.setUsercode(new LoginDaoImpl().getUserID(userName,Password,custdetails));
			pojo.setLogintime(HelperClass.getCurrentTimestamp());
            pojo.setTokenNo(custdetails.getTokenNo());
			pojo.setCustId(custdetails.getCustId());
            
			session.setAttribute("UserLoggingsVo",new LoginDaoImpl().InsertUserLoggings(pojo,custdetails));
			UserLoggingsVo UserLoggingsVo = (UserLoggingsVo) request.getSession(false).getAttribute("UserLoggingsVo");
             
            session.setAttribute("log_audit_session",UserLoggingsVo.getSessionId());
			String custid = custdetails.getCustId();

			UserDetailVO usrVo = new LoginBD().validateNewUserBD(pojo.getUsercode(),custdetails);
			
			System.out.println("IP:::"+lform.getInternal_ip());
			session.setAttribute("IP", lform.getInternal_ip());
			
			String usercode =null;
			
			if(pojo.getUsercode() == "" || pojo.getUsercode() == null)
			{
				System.out.println("Inside If Loop");
				usercode = HelperClass.getCurrentUserID(request);

				usrVo.setUserNametype(HelperClass.getCurrentUser(request));
				usrVo.setUserId(usercode);
				
				

				request.getSession(false).setAttribute("Priveliges", HelperClass.getPrivigeValue(request));
			}
			else
			{
				System.out.println("Inside Else Loop");
				usercode = usrVo.getUserId();
				request.getSession(false).setAttribute("Priveliges", usrVo.getIsAdministrator());
			}
					
			if (usercode.contains("eCamp") || usercode.contains("TEA")) {

				List<HelperClassVo> accYear = new ArrayList<HelperClassVo>();

				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_HOMENAME);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME, null);

				session = request.getSession(false);
				String selected = request.getParameter("accYear");
				String schoolCode=request.getParameter("school");

				accYear = HelperClass.getAllAcademicYear(custdetails);
				
				session.setAttribute(MessageConstants.ACADAMICYEAR, accYear);

				if (selected == null || selected.equalsIgnoreCase("") || selected.equalsIgnoreCase("null")) {
					session.setAttribute(MessageConstants.CURRENT_ACADAMICYEAR,	HelperClass.getCurrentYearID(custdetails));
				} else {
					session.setAttribute(MessageConstants.CURRENT_ACADAMICYEAR,selected.trim());
				}
				
				String accYears = (String) request.getSession(false).getAttribute("current_academicYear");
				
				
				String startDate = Integer.toString(HelperClass.getPastDateofAcademicYear(accYears,custdetails));
				String endDate = Integer.toString(HelperClass.getForDateofAcademicYear(accYears,custdetails));
				
				request.setAttribute("startDate", startDate);
				request.setAttribute("endDate", endDate);

				usrVo.setAcademicyear(selected);
				session.setAttribute("TEACHERNAMEDETAILS", usrVo.getUserName());
				session.setAttribute(MessageConstants.USER_DETAILS, usrVo);
				
				session.setAttribute(MessageConstants.CURRENT_SCHOOLLOCATION,usrVo.getLocationid());
				
				
				session.setAttribute("user", usrVo.getUserNametype());
				userdefined = "TEACHER_AND_PARENT";
			}
			else if(usercode.contains("PAR")){
				
				List<HelperClassVo> accYear = new ArrayList<HelperClassVo>();
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_HOMENAME);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME, null);

				session = request.getSession(false);
				String selected = request.getParameter("accYear");
				if (selected == null || selected.equalsIgnoreCase("") || selected.equalsIgnoreCase("null")) {
					String currentY = HelperClass.getCurrentYearID(custdetails);
					session.setAttribute(MessageConstants.CURRENT_ACADAMICYEAR,currentY);
					session.setAttribute("accYname",HelperClass.getAcademicYearFace(currentY, custdetails));
					
				} else {
					session.setAttribute(MessageConstants.CURRENT_ACADAMICYEAR,selected.trim());
				}
				
				String accYears=(String) request.getSession(false).getAttribute("current_academicYear");
				String startDate=Integer.toString(HelperClass.getPastDateofAcademicYear(accYears,custdetails));
				String endDate=Integer.toString(HelperClass.getForDateofAcademicYear(accYears,custdetails));
				
				request.setAttribute("startDate", startDate);
				request.setAttribute("endDate", endDate);

				
				usrVo.setAcademicyear(selected);
				session.setAttribute(MessageConstants.USER_DETAILS, usrVo);
				session.setAttribute(MessageConstants.CURRENT_SCHOOLLOCATION,"all");
				

				session.setAttribute("user", usrVo.getUserNametype());
				userdefined = "PARENT";
				
				
				
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
				 
				 if(list1.size()>0){
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
				
			}
			else if(usercode.contains("PNQ")) {
				
				ParentRequiresAppointmentVO appointmentVo = new ParentRequiresAppointmentVO();
				String enquiryid=usrVo.getUserId();
				System.out.println("enquiryid is "+ enquiryid);
				appointmentVo.setEnquiryId(enquiryid);
				appointmentVo.setCurrent_user(custid);
				
				List<ParentRequiresAppointmentVO> admissionList = new ParentRequiresAppointmentDELEGATE().getAdmissionRegDetails(appointmentVo,custdetails);
				ParentRequiresAppointmentVO classId=new ParentRequiresAppointmentDAOIMPL().getStudentPreferedClass(appointmentVo,custdetails);
				
				ParentRequiresAppointmentVO locationName=new ParentRequiresAppointmentDAOIMPL().getStudentPreferedSchool(appointmentVo,custdetails);
				
				session.setAttribute("locationName", locationName);
				
				session.setAttribute("classId", classId);
				
				session.setAttribute("user", "ENQUIRY");
				session.setAttribute("enquiryid", enquiryid);
				userdefined = "ENQUIRY";
			}

			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : login Ending");

	
		
		if (userdefined == "TEACHER_AND_PARENT" || userdefined.equalsIgnoreCase("TEACHER_AND_PARENT")) {
			String previlage=(String) request.getSession(false).getAttribute("Priveliges");
			if(previlage.equalsIgnoreCase("Y")){
				return mapping.findForward(MessageConstants.adminLogin);
				//return mapping.findForward(MessageConstants.chooseLocation);
			}
			else {
				return mapping.findForward(MessageConstants.adminLogin);
			}
			
		}
		
		else if(userdefined=="PARENT" || userdefined.equalsIgnoreCase("PARENT")){
			return mapping.findForward(MessageConstants.parentLogin);
		}
		else {
			return mapping.findForward(MessageConstants.enquirylogin);
		}

	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : logout  Starting");

		try {
			String user = (String) request.getParameter("usernamehidden");

			HttpSession session = request.getSession(false);
			UserLoggingsPojo pojo = (UserLoggingsPojo) session.getAttribute("token_information");
			UserLoggingsVo UserLoggingsVo = (UserLoggingsVo) request.getSession(false).getAttribute("UserLoggingsVo");
			
			Timestamp logintime = (Timestamp)UserLoggingsVo.getLogintime();
			
			new LoginDaoImpl().updateUserLoggings(UserLoggingsVo.getSessionId(),logintime,pojo);
			
			
			JSONObject jsonserver = new JSONObject();
			jsonserver.put("tokenNumber",pojo.getTokenNo());
			
			System.out.println(jsonserver);
			/*ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			WebTarget service = client.target(getBaseURI());
		      
			service.path("TokenExpire").request().put(Entity.entity(jsonserver.toString(),MediaType.APPLICATION_JSON),String.class);*/

			if (request.isRequestedSessionIdValid() && session != null) {
				session.invalidate();
			}
			handleLogOutResponse(response,request);
			//session.invalidate();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : logout Ending");

		return mapping.findForward("Home");
	}


	private void handleLogOutResponse(HttpServletResponse response,HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
			cookie.setValue(null);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}
	
	public ActionForward changePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : changePassword  Starting");

		try {
			int count = 0;
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String newPassword = (String) request.getParameter("newPassword");
			String currentuser = (String) request.getSession(false).getAttribute("user");
			String currentuserId = HelperClass.getCurrentUserID(request);
			
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			
			//Api to change the password in Master table
			// Not in use at present
			//String pwdChange = new LoginBD().callPWDChangeService(pojo.getAuserid(),pojo.getCustId(),newPassword);
			//if(pwdChange.equalsIgnoreCase("true"))
			
			count = new LoginBD().changePassword(currentuser,newPassword,currentuserId,log_audit_session,pojo);

			JSONObject object = new JSONObject();

			if (count > 0) {
				object.put("status", "true");
			} else {
				object.put("status", "false");
			}
			
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : changePassword Ending");

		return null;
	}

	public ActionForward checkCurrentPassword(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		logger.error("any error occured");
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : checkCurrentPassword  Starting");

		try {

			String currentuser = (String) request.getSession(false).getAttribute("user");
			String currentPassword = (String) request.getParameter("oldPassword");
			String currentuserId = HelperClass.getCurrentUserID(request);

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			int count = new LoginBD().checkCurrentPassword(currentuser,currentPassword,currentuserId,custdetails);

			JSONObject object = new JSONObject();
			if (count > 0) {
				object.put("status", "true");
			} else {
				object.put("status", "false");
			}
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction : checkCurrentPassword Ending");

		return null;
	}

	public ActionForward userValidCase(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction:userValidCase: Starting");
		
		try{
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String username=(String)request.getParameter("uname");
		String password=(String)request.getParameter("pword");
		
		
		System.out.println(username+"   "+password);
		LoginBD loginservice = new LoginBD();
		if (loginservice.userValidCase(username.trim(),
				password.trim(),custdetails).equals(MessageConstants.TRUE)) {
			
			JSONObject object=new JSONObject();
			object.put("status", true);
			
			response.getWriter().print(object);
		}else{
			
			JSONObject object=new JSONObject();
			object.put("status", false);
			
			response.getWriter().print(object);
		}
	
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		
		}
			
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction :userValidCase: Ending");
		
		return null;
	}
	public ActionForward Dairy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction:Dairy: Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		UserDetailVO user=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String sessionid = (String)request.getSession(false).getAttribute("log_audit_session");
		
		String userId=user.getUserId();
		String userType=user.getUserNametype();
		try{
		
		String content=request.getParameter("content");
		String rowid=request.getParameter("rowid");
		String date=request.getParameter("commentdate");
		
		System.out.println(date);
		LoginBD loginservice = new LoginBD();
		String status=loginservice.saveDairy(content,rowid,date,userId,userType,custdetails,sessionid);
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		
		}
			
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginAction:Dairy: Ending");
		
		return null;
	}
	
	 private static URI getBaseURI() {  
		 return UriBuilder.fromUri(licser_service).build();  
	 } 
	 
	 public void createSchoolInfoFolder(HttpServletRequest request, String custdomain) {
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: createSchoolInfoFolder Starting");
		try{
			  String path = request.getServletContext().getRealPath("/")+"SCHOOLINFO"+"/";
		      File f = new File (path +custdomain);
		      if (!f.exists()) {
		    	  f.mkdir();
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CustomerDaoImpl : createSchoolInfoFolder Ending");
	}
}