package com.centris.campus.actions;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import org.apache.log4j.Level;
import com.centris.campus.converter.DobINWord;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentCertificatesBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.pojo.StudentCertificatesPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentCertificatesVo;
import com.centris.campus.vo.StudentRegistrationVo;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class StudentCertificatesAction extends DispatchAction{

	private static final Logger logger = Logger
			.getLogger(StudentCertificatesAction.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	
	public ActionForward studentCertificates(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction  : studentCertificates Starting");
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_AGEANDBONAFIEDCERTIFICATE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			  
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			  
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	System.out.println("current school Location:" +schoolLocation);
	    	  
	    	String currentlocation =null;
	    	
	    	if(schoolLocation.equalsIgnoreCase("all")){
	    		schoolLocation="%%";
	    		request.setAttribute("currentlocation", "All");
	    	}
	    	  else{
	    		   currentlocation =new ExamDetailsBD().getlocationname(schoolLocation,userLoggingsVo);
	    		   request.setAttribute("currentlocation", currentlocation);
	    	   }
	    	   request.setAttribute("locationId",schoolLocation);
	    	   
	    	   LocationBD obj = new LocationBD();
				List<LocationVO> list = new ArrayList<LocationVO>();
				list = obj.getLocationDetails(userLoggingsVo);
				request.setAttribute("locationDetailsList", list);
				
				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
				request.setAttribute("accYearList", accYearList);
				
				ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(schoolLocation,userLoggingsVo);
				request.setAttribute("classList", classList);
				
				request.setAttribute("historyaccyear",request.getParameter("historyaccyear")); 
				request.setAttribute("historylocation",request.getParameter("historylocation"));
				request.setAttribute("historyclassid",request.getParameter("historyclassid"));
				request.setAttribute("historysection",request.getParameter("historysection"));
				request.setAttribute("historysearchtext",request.getParameter("historysearchtext"));
				request.setAttribute("historystatus",request.getParameter("historystatus"));
				request.setAttribute("historyfindcerti",request.getParameter("historyfindcerti"));
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction : studentCertificates Ending");
		
		return mapping.findForward(MessageConstants.STUDENT_CERTIFICATES);
	}
	
	public ActionForward ageCertificate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction  : ageCertificate Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_AGEANDBONAFIEDCERTIFICATE);
			
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			 
			String locationid = request.getParameter("locationid");
			
			LocationVO custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locationid);
			
			
			String accyear = request.getParameter("accyear");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String stuid = request.getParameter("stuid");
			
			request.setAttribute("stuid",stuid);
			StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
			pojo.setLocid(locationid);
			pojo.setAccyear(accyear);
			pojo.setClassid(classid);
			pojo.setSectionid(sectionid);
			pojo.setStuid(stuid);
			
			
			StudentCertificatesVo list =new StudentCertificatesBD().getStudentCertificate(pojo,cpojo);
			
			request.setAttribute("stuList",list);
			request.setAttribute("locationid",list.getLocid());
			request.setAttribute("accyearid",list.getAccyear());
			request.setAttribute("sectionid",list.getSectionid());
			request.setAttribute("classid",list.getClassid());
			request.setAttribute("currentdate", HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString()));
			request.setAttribute("custSchoolAddres", custSchoolInfo.getAddress());
			request.setAttribute("custSchoolAddres1", custSchoolInfo.getAddress2());
			request.setAttribute("schoolLogo", custSchoolInfo.getSchoollogo());
			
			request.setAttribute("schoolName", list.getSchool_name());
			request.setAttribute("pincode", list.getPincode());
			
			
			System.out.println("schoolName"+list.getSchool_name());
			

			request.setAttribute("historyaccyear",accyear);
			request.setAttribute("historylocation",request.getParameter("historylocation"));
			request.setAttribute("historyclassid",request.getParameter("historyclassid"));
			request.setAttribute("historysection",request.getParameter("historysection"));
			request.setAttribute("historysearchtext",request.getParameter("historysearchtext"));
			request.setAttribute("historystatus",request.getParameter("historystatus"));
			request.setAttribute("historyfindcerti",request.getParameter("historyfindcerti"));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction : ageCertificate Ending");
		
		return mapping.findForward(MessageConstants.AGE_CERTIFICATE);
	}
public ActionForward AgeCertificatePDF(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction  : AgeCertificatePDF Starting");
			try {	
				
				/*String locationid = request.getParameter("locationid");
				String accyear = request.getParameter("accyear");
				String classid = request.getParameter("classid");
				String sectionid = request.getParameter("sectionid");*/
				/*String stuid = request.getParameter("stuname");*/
				ArrayList<StudentCertificatesVo> list = new ArrayList<StudentCertificatesVo>();
				/*System.out.println(stuid);*/
				String sourceFileName = request
						.getRealPath("Reports/age&bonafiedCertificate.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						list);
				String PropfilePath = getServlet().getServletContext().getRealPath("")+"\\images\\"+ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				
				parameters.put("Image", PropfilePath);
/*				parameters.put("stuname",stuid);*/
				
				/*String result = "true";
	        	JSONObject jsonobj = new JSONObject();
				jsonobj.put("status",result);
				response.getWriter().print(jsonobj);*/
				
				JasperPrint  jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
		        JasperViewer.viewReport(jasperPrint, false);
		           
		        
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
						
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction : AgeCertificatePDF Ending");
		return null;
	}
	public ActionForward getStudentsDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction  : getStudentsDetails Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			
			String loc = request.getParameter("location");
			String acyear = request.getParameter("acyear"); 
			String status = request.getParameter("status");
			ArrayList<StudentCertificatesVo> stuList = new StudentCertificatesBD().getStudentsDetails(loc,acyear,status,cpojo);
			
			
			JSONObject obj=new JSONObject();
			obj.put("stuList",stuList);
			response.getWriter().print(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction : getStudentsDetails Ending");
		return null;
	}
	public ActionForward getStudentsDetailsByClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction  : getStudentsDetailsByClass Starting");
		
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			String loc = request.getParameter("location");
			String acyear = request.getParameter("acyear");
			String classname = request.getParameter("classid");
			String status = request.getParameter("status");
			
			list = new StudentRegistrationDelegate().getStudentDetailsLByFilter(loc,acyear,classname,status,userLoggingsVo);
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("getClassWiseList", list);
			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction : getStudentsDetailsByClass Ending");
		return null;
	}
	
	public ActionForward getClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction  : getClass Starting");
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			String loc = request.getParameter("location");
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(loc,userLoggingsVo);
			JSONObject obj=new JSONObject();
			obj.put("classList",classList);
			response.getWriter().print(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction : getClass Ending");
		return null;
	}
	public ActionForward getStudentListBySection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in StudentCertificatesAction : getStudentListBySection Starting");
		
		try{
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatus(userLoggingsVo);
			
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid"); 
			String status = request.getParameter("status");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDelegate().getStudentListBySection(locationid,accyear,classname,sectionid,status,userLoggingsVo);
				JSONObject jsonobj = new JSONObject();

				jsonobj.put("getSectionWiseList", list);

				response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction : getStudentListBySection Ending");

		return null;
	}
	
	public ActionForward findStudent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction  : findStudent Starting");
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction : findStudent Ending");
		
		return mapping.findForward(MessageConstants.FIND_STUDENT);
	}
	public ActionForward saveAgeCertificateData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction  : saveAgeCertificateData Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String stuname = request.getParameter("stuname");
			String admissionno = request.getParameter("admissionno");
			String fathername = request.getParameter("fathername");
			String dob = request.getParameter("dob");
			String purpose = request.getParameter("purpose");
			String otherinfo = request.getParameter("otherinfo");
			String studentstatus = request.getParameter("studentstatus");
			String motherName = request.getParameter("motherName");
			String dobwords = request.getParameter("dobwords");
			
			StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
			
			pojo.setAccyear(accyear);
			pojo.setLocid(locid);
			pojo.setClassid(classid);
			pojo.setSectionid(sectionid);
			pojo.setAdmissionno(admissionno);
			pojo.setStuname(stuname);
			pojo.setDob(dob);
			pojo.setDobwords(dobwords);
			pojo.setMothername(motherName);
			pojo.setOtherinfo(otherinfo);
			pojo.setPurpose(purpose);
			pojo.setFatherName(fathername);
			pojo.setStudentstatus(studentstatus);
			pojo.setLog_audit_session(log_audit_session);
		
			
			String result = new StudentCertificatesBD().saveAgeCertificateData(pojo,userLoggingsVo);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status",result);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction : saveAgeCertificateData Ending");
		
		return null;
	}
	
	public ActionForward getStudentSearchByConfReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction : getStudentSearchByConfReport Starting");
		
		List<StudentRegistrationVo> list = null;
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			
			String searchTerm = request.getParameter("searchname".trim());

			if(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")){
				
				list = new StudentRegistrationDelegate().getStudentSearchByConfReport(searchTerm,userLoggingsVo);
			}
			else if(locationid.equalsIgnoreCase("all") && !(accyear.equalsIgnoreCase("all"))){
				
				list = new StudentRegistrationDelegate().getConfSearchListByAccYear(searchTerm,accyear,userLoggingsVo);
			}
			else if(accyear.equalsIgnoreCase("all") && !(locationid.equalsIgnoreCase("all"))){
				
				list = new StudentRegistrationDelegate().getConfSearchListByLocation(searchTerm,locationid,userLoggingsVo);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")) && classname.equalsIgnoreCase("all") && sectionid.equalsIgnoreCase("all")){
				
				list = new StudentRegistrationDelegate().getConfSearchByFilter(searchTerm,locationid,accyear,userLoggingsVo);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all")) && sectionid.equalsIgnoreCase("all")){
				
				list = new StudentRegistrationDelegate().getConfSearchByClass(searchTerm,locationid,accyear,classname,userLoggingsVo);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all") && sectionid.equalsIgnoreCase("all"))){
				
				list = new StudentRegistrationDelegate().getConfSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid,userLoggingsVo);
			}
			
			request.setAttribute("SearchList", list);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("SearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction : getStudentSearchByConfReport Ending");

		return null;
	}

	public ActionForward getissueddetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
						
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction  : getissueddetails Starting");
			try {	
				
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				String locid = request.getParameter("locid");
				String accyear = request.getParameter("accyear");
				String classid = request.getParameter("classid");
				String sectionid = request.getParameter("sectionid");
				String stuid = request.getParameter("stuname");
				
				StudentCertificatesPOJO pojo =  new StudentCertificatesPOJO();
				pojo.setLocid(locid);
				pojo.setAccyear(accyear);
				pojo.setClassid(classid);
				pojo.setSectionid(sectionid);
				pojo.setStuid(stuid);
			
				
				ArrayList<StudentCertificatesVo> list = new StudentCertificatesBD().getissueddetails(pojo,userLoggingsVo);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("stuList",list);
				response.getWriter().print(jsonobj);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
						
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction : getissueddetails Ending");
		return null;
	}
	
	public ActionForward bonafideCertificate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction  : bonafideCertificate Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_AGEANDBONAFIEDCERTIFICATE);
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction  : AgeCertificate Starting");
			
			 UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			
				
				String locationid = request.getParameter("locationid");
				
				LocationVO custSchoolInfo = HelperClass.getCustSchoolInfo(cpojo,locationid);
				
				String accyear = request.getParameter("accyear");
				String classid = request.getParameter("classid");
				String sectionid = request.getParameter("sectionid");
				String stuid = request.getParameter("stuid");
				
				
				
				
				request.setAttribute("locationid",locationid);
				request.setAttribute("accyearid",accyear);
				request.setAttribute("sectionid",sectionid);
				request.setAttribute("classid",classid);
				request.setAttribute("stuid",stuid);
				
				StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
				pojo.setLocid(locationid);
				pojo.setAccyear(accyear);
				pojo.setClassid(classid);
				pojo.setSectionid(sectionid);
				pojo.setStuid(stuid);
			
				
				request.setAttribute("currentdate", HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString()));
				request.setAttribute("custSchoolAddres", custSchoolInfo.getAddress());
				request.setAttribute("custSchoolAddres1", custSchoolInfo.getAddress2());
				request.setAttribute("schoolLogo", custSchoolInfo.getSchoollogo());
				
				
				StudentCertificatesVo list =new StudentCertificatesBD().getStudentCertificate(pojo,cpojo);
				request.setAttribute("stuList",list);
				
				request.setAttribute("schoolName", list.getSchool_name());
				request.setAttribute("pincode", list.getPincode());
				request.setAttribute("fatherName", list.getFathername());
				
				
				
				
				request.setAttribute("historyaccyear",accyear);
				request.setAttribute("historylocation",request.getParameter("historylocation"));
				request.setAttribute("historyclassid",request.getParameter("historyclassid"));
				request.setAttribute("historysection",request.getParameter("historysection"));
				request.setAttribute("historysearchtext",request.getParameter("historysearchtext"));
				request.setAttribute("historystatus",request.getParameter("historystatus"));
				request.setAttribute("historyfindcerti",request.getParameter("historyfindcerti"));
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction : bonafideCertificate Ending");
		
		return mapping.findForward(MessageConstants.BONAFIDE_CERTIFICATE);
	}
	public ActionForward saveBonafiedCertificateData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction  : saveBonafiedCertificateData Starting");
		try {
			
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String stuname = request.getParameter("stuname");
			String admissionno = request.getParameter("admissionno");
			String fathername = request.getParameter("fathername");
			String dob = request.getParameter("dob");
			String purpose = request.getParameter("purpose");
			String otherinfo = request.getParameter("otherinfo");
			String studentstatus = request.getParameter("studentstatus");
			String motherName = request.getParameter("motherName");
			String dobwords = request.getParameter("dobwords");
			
			StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
			
			pojo.setAccyear(accyear);
			pojo.setLocid(locid);
			pojo.setClassid(classid);
			pojo.setSectionid(sectionid);
			pojo.setAdmissionno(admissionno);
			pojo.setStuname(stuname);
			pojo.setDob(dob);
			pojo.setDobwords(dobwords);
			pojo.setMothername(motherName);
			pojo.setOtherinfo(otherinfo);
			pojo.setPurpose(purpose);
			pojo.setFatherName(fathername);
			pojo.setStudentstatus(studentstatus);
			pojo.setLog_audit_session(log_audit_session);
			
			
			String result = new StudentCertificatesBD().saveBonafiedCertificateData(pojo,cpojo);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status",result);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentCertificatesAction : saveBonafiedCertificateData Ending");
		
		return null;
	}
	public ActionForward getbonafiedissueddetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction  : getbonafiedissueddetails Starting");
			try {	
				UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
				
				String locid = request.getParameter("locid");
				String accyear = request.getParameter("accyear");
				String classid = request.getParameter("classid");
				String sectionid = request.getParameter("sectionid");
				String stuid = request.getParameter("stuname");
				
				StudentCertificatesPOJO pojo =  new StudentCertificatesPOJO();
				pojo.setLocid(locid);
				pojo.setAccyear(accyear);
				pojo.setClassid(classid);
				pojo.setSectionid(sectionid);
				pojo.setStuid(stuid);
			
				
				ArrayList<StudentCertificatesVo> list = new StudentCertificatesBD().getbonafiedissueddetails(pojo,cpojo);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("stuList",list);
				response.getWriter().print(jsonobj);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
						
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction : getbonafiedissueddetails Ending");
		return null;
	}
public ActionForward BonafiedCertificatePDF(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction  : BonafiedCertificatePDF Starting");
			try {	
				
				String locationid = request.getParameter("locationid");
				String accyear = request.getParameter("accyear");
				String classid = request.getParameter("classid");
				String sectionid = request.getParameter("sectionid");
				String stuid = request.getParameter("stuid");
				
				System.out.println(locationid);
				System.out.println(stuid);
				System.out.println(accyear);
				System.out.println(classid);
				System.out.println(sectionid);
				
				String sourceFileName = request
						.getRealPath("Reports/bonafiedCertificate.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);
				
				ArrayList<StudentCertificatesVo> list = new ArrayList<StudentCertificatesVo>();
				
				String PropfilePath = getServlet().getServletContext().getRealPath("")+"\\images\\"+ImageName.trim();
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				parameters.put("Image", PropfilePath);
				
		        JasperPrint jp = JasperFillManager.fillReport(jasperreport,null,beanColDataSource);
		        JasperViewer.viewReport(jp, false);
		        
		        	/*JSONObject jsonobj = new JSONObject();
					jsonobj.put("status","true");
					response.getWriter().print(jsonobj);*/
		        
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
						
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction : BonafiedCertificatePDF Ending");
		return null;
	}

public ActionForward conductCertificate(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction  : conductCertificate Starting");
	
	try {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_AGEANDBONAFIEDCERTIFICATE);
		

    UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
    
  
		String locationid = request.getParameter("locationid");
		if(locationid == null){
			locationid = "%%";
		}
		LocationVO custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locationid);
		String accyear = request.getParameter("accyear");
		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String stuid = request.getParameter("stuid");
		request.setAttribute("locationid",locationid);
		request.setAttribute("accyearid",accyear);
		request.setAttribute("sectionid",sectionid);
		request.setAttribute("classid",classid);
		request.setAttribute("stuid",stuid);
		StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
		pojo.setLocid(locationid);
		pojo.setAccyear(accyear);
		pojo.setClassid(classid);
		pojo.setSectionid(sectionid);
		pojo.setStuid(stuid);
		
		
		StudentCertificatesVo list =new StudentCertificatesBD().getStudentCertificate(pojo,cpojo);
		
		
		request.setAttribute("stuList",list);
		request.setAttribute("currentdate", HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString()));
		request.setAttribute("custSchoolAddres", custSchoolInfo.getAddress());
		/*request.setAttribute("custSchoollogo", custSchoolInfo.getSchoollogo());
		request.setAttribute("custSchoolboardlogo", custSchoolInfo.getBoardlogo());
		request.setAttribute("custSchoolaffilno", custSchoolInfo.getAffilno());
		request.setAttribute("custSchoolno", custSchoolInfo.getSchoolcode());
		request.setAttribute("custSchoolwebsit", custSchoolInfo.getWebsite());
		request.setAttribute("custSchoolEmail", custSchoolInfo.getEmailId());*/
		
		request.setAttribute("historyaccyear",accyear);
		request.setAttribute("historylocation",request.getParameter("historylocation"));
		request.setAttribute("historyclassid",request.getParameter("historyclassid"));
		request.setAttribute("historysection",request.getParameter("historysection"));
		request.setAttribute("historysearchtext",request.getParameter("historysearchtext"));
		request.setAttribute("historystatus",request.getParameter("historystatus"));
		request.setAttribute("historyfindcerti",request.getParameter("historyfindcerti"));
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction : conductCertificate Ending");
	
	return mapping.findForward(MessageConstants.CONDUCT_CERTIFICATE);
}
public ActionForward course_conductCertificate(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction  : course_conductCertificate Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_AGEANDBONAFIEDCERTIFICATE);
		
	    UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
	    
	   
		String locationid = request.getParameter("locationid");
		
		LocationVO custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locationid);
		
		String accyear = request.getParameter("accyear");
		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String stuid = request.getParameter("stuid");
		
		request.setAttribute("locationid",locationid);
		request.setAttribute("accyearid",accyear);
		request.setAttribute("sectionid",sectionid);
		request.setAttribute("classid",classid);
		request.setAttribute("stuid",stuid);
		StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
		pojo.setLocid(locationid);
		pojo.setAccyear(accyear);
		pojo.setClassid(classid);
		pojo.setSectionid(sectionid);
		pojo.setStuid(stuid);

		
		StudentCertificatesVo list =new StudentCertificatesBD().getStudentCertificate(pojo,cpojo);
		request.setAttribute("stuList",list);
		request.setAttribute("currentdate", HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString()));
		request.setAttribute("custSchoolAddres", custSchoolInfo.getAddress());
		request.setAttribute("custSchoolAddres1", custSchoolInfo.getAddress2());
		request.setAttribute("schoolLogo", custSchoolInfo.getSchoollogo());
	    
		request.setAttribute("board", custSchoolInfo.getBoard());
		
		request.setAttribute("schoolName", list.getSchool_name());
		request.setAttribute("pincode", list.getPincode());
		
		
		
		request.setAttribute("historyaccyear",accyear);
		request.setAttribute("historylocation",request.getParameter("historylocation"));
		request.setAttribute("historyclassid",request.getParameter("historyclassid"));
		request.setAttribute("historysection",request.getParameter("historysection"));
		request.setAttribute("historysearchtext",request.getParameter("historysearchtext"));
		request.setAttribute("historystatus",request.getParameter("historystatus"));
		request.setAttribute("historyfindcerti",request.getParameter("historyfindcerti"));
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction : course_conductCertificate Ending");
	
	return mapping.findForward(MessageConstants.COURSE_CONDUCT_CERTIFICATE);
}

public ActionForward studentVisaCertificate(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction  : studentVisaCertificate Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_AGEANDBONAFIEDCERTIFICATE);
		
		UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
		   
		    
		String locationid = request.getParameter("locationid");
		
		LocationVO custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locationid);
		
		String accyear = request.getParameter("accyear");
		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String stuid = request.getParameter("stuid");
		
		
		request.setAttribute("locationid",locationid);
		request.setAttribute("accyearid",accyear);
		request.setAttribute("sectionid",sectionid);
		request.setAttribute("classid",classid);
		request.setAttribute("stuid",stuid);
		StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
		pojo.setLocid(locationid);
		pojo.setAccyear(accyear);
		pojo.setClassid(classid);
		pojo.setSectionid(sectionid);
		pojo.setStuid(stuid);

		
		StudentCertificatesVo list =new StudentCertificatesBD().getStudentCertificate(pojo,cpojo);
		
		request.setAttribute("stuList",list);
		request.setAttribute("currentdate", HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString()));
		request.setAttribute("custSchoolAddres", custSchoolInfo.getAddress());
		request.setAttribute("custSchoolAddres1", custSchoolInfo.getAddress2());
	
		request.setAttribute("board", custSchoolInfo.getBoard());
		request.setAttribute("schoolLogo", custSchoolInfo.getSchoollogo());
		
		request.setAttribute("schoolName", list.getSchool_name());
		request.setAttribute("pincode", list.getPincode());
		
		
		
		
		request.setAttribute("historyaccyear",accyear);
		request.setAttribute("historylocation",request.getParameter("historylocation"));
		request.setAttribute("historyclassid",request.getParameter("historyclassid"));
		request.setAttribute("historysection",request.getParameter("historysection"));
		request.setAttribute("historysearchtext",request.getParameter("historysearchtext"));
		request.setAttribute("historystatus",request.getParameter("historystatus"));
		request.setAttribute("historyfindcerti",request.getParameter("historyfindcerti"));
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction : studentVisaCertificate Ending");
	
	return mapping.findForward(MessageConstants.STUDENT_VISA_CERTIFICATE);
}
public ActionForward saveCounductedCertificateData(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction  : saveCounductedCertificateData Starting");
	try {
		
		UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
		
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");
		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String stuname = request.getParameter("stuname");
		String admissionno = request.getParameter("admissionno");
		String fathername = request.getParameter("fathername");
		String conductNo = request.getParameter("conductNo");
		String purpose = request.getParameter("purpose");
		String otherinfo = request.getParameter("otherinfo");
		String studentstatus = request.getParameter("studentstatus");
		String motherName = request.getParameter("motherName");
		String conduct = request.getParameter("conduct");
		
		StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
		
		pojo.setAccyear(accyear);
		pojo.setLocid(locid);
		pojo.setClassid(classid);
		pojo.setSectionid(sectionid);
		pojo.setAdmissionno(admissionno);
		pojo.setStuname(stuname);
		pojo.setConductno(conductNo);
		pojo.setConduct(conduct);
		pojo.setMothername(motherName);
		pojo.setOtherinfo(otherinfo);
		pojo.setPurpose(purpose);
		pojo.setFatherName(fathername);
		pojo.setStudentstatus(studentstatus);
		pojo.setLog_audit_session(log_audit_session);
		pojo.setCustid(cpojo.getCustId());
		
		String result = new StudentCertificatesBD().saveCounductedCertificateData(pojo,cpojo);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("status",result);
		response.getWriter().print(jsonobj);

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction : saveCounductedCertificateData Ending");
	
	return null;
}
public ActionForward getconductissueddetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction  : getconductissueddetails Starting");
		try {
			
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			
			String locid = request.getParameter("locid");
			String accyear = request.getParameter("accyear");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String stuid = request.getParameter("stuname");
			
			StudentCertificatesPOJO pojo =  new StudentCertificatesPOJO();
			pojo.setLocid(locid);
			pojo.setAccyear(accyear);
			pojo.setClassid(classid);
			pojo.setSectionid(sectionid);
			pojo.setStuid(stuid);
			pojo.setCustid(cpojo.getCustId());
			
			ArrayList<StudentCertificatesVo> list = new StudentCertificatesBD().getconductissueddetails(pojo,cpojo);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("stuList",list);
			response.getWriter().print(jsonobj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
					
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction : getconductissueddetails Ending");
	return null;
}
public ActionForward saveCourseCounductedCertificateData(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction  : saveCourseCounductedCertificateData Starting");
	try {
		UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
		
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");
		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String stuname = request.getParameter("stuname");
		String admissionno = request.getParameter("admissionno");
		String fathername = request.getParameter("fathername");
		String conductNo = request.getParameter("conductNo");
		String purpose = request.getParameter("purpose");
		String otherinfo = request.getParameter("otherinfo");
		String studentstatus = request.getParameter("studentstatus");
		String motherName = request.getParameter("motherName");
		String conduct = request.getParameter("conduct");
		
		StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
		
		pojo.setAccyear(accyear);
		pojo.setLocid(locid);
		pojo.setClassid(classid);
		pojo.setSectionid(sectionid);
		pojo.setAdmissionno(admissionno);
		pojo.setStuname(stuname);
		pojo.setConductno(conductNo);
		pojo.setConduct(conduct);
		pojo.setMothername(motherName);
		pojo.setOtherinfo(otherinfo);
		pojo.setPurpose(purpose);
		pojo.setFatherName(fathername);
		pojo.setStudentstatus(studentstatus);
		pojo.setLog_audit_session(log_audit_session);
		
		
		String result = new StudentCertificatesBD().saveCourseCounductedCertificateData(pojo,cpojo);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("status",result);
		response.getWriter().print(jsonobj);

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction : saveCourseCounductedCertificateData Ending");
	
	return null;
}
public ActionForward getcourseconductissueddetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction  : getcourseconductissueddetails Starting");
		try {
			
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			
			String locid = request.getParameter("locid");
			String accyear = request.getParameter("accyear");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String stuid = request.getParameter("stuname");
			
			StudentCertificatesPOJO pojo =  new StudentCertificatesPOJO();
			pojo.setLocid(locid);
			pojo.setAccyear(accyear);
			pojo.setClassid(classid);
			pojo.setSectionid(sectionid);
			pojo.setStuid(stuid);
			
			
			ArrayList<StudentCertificatesVo> list = new StudentCertificatesBD().getcourseconductissueddetails(pojo,cpojo);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("stuList",list);
			response.getWriter().print(jsonobj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
					
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction : getcourseconductissueddetails Ending");
	return null;
}
public ActionForward saveStudentVisaCertificateData(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction  : saveStudentVisaCertificateData Starting");
	try {
		
		UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
		
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");
		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String stuname = request.getParameter("stuname");
		String admissionno = request.getParameter("admissionno");
		String fathername = request.getParameter("fathername");
		String purpose = request.getParameter("purpose");
		String studentstatus = request.getParameter("studentstatus");
		String motherName = request.getParameter("motherName");
		String pasportno = request.getParameter("passportno");
		StudentCertificatesPOJO pojo = new StudentCertificatesPOJO();
		
		pojo.setAccyear(accyear);
		pojo.setLocid(locid);
		pojo.setClassid(classid);
		pojo.setSectionid(sectionid);
		pojo.setAdmissionno(admissionno);
		pojo.setStuname(stuname);
		pojo.setMothername(motherName);
		pojo.setPurpose(purpose);
		pojo.setFatherName(fathername);
		pojo.setStudentstatus(studentstatus);
		pojo.setPassportno(pasportno);
		pojo.setLog_audit_session(log_audit_session); 
		
		
		String result = new StudentCertificatesBD().saveStudentVisaCertificateData(pojo,cpojo);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("status",result);
		response.getWriter().print(jsonobj);

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction : saveStudentVisaCertificateData Ending");
	
	return null;
}
public ActionForward getstudentvisaissueddetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction  : getstudentvisaissueddetails Starting");
		try {
			
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			
			String locid = request.getParameter("locid");
			String accyear = request.getParameter("accyear");
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String stuid = request.getParameter("stuname");
			
			StudentCertificatesPOJO pojo =  new StudentCertificatesPOJO();
			pojo.setLocid(locid);
			pojo.setAccyear(accyear);
			pojo.setClassid(classid);
			pojo.setSectionid(sectionid);
			pojo.setStuid(stuid);
		
			
			ArrayList<StudentCertificatesVo> list = new StudentCertificatesBD().getstudentvisaissueddetails(pojo,cpojo);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("stuList",list);
			response.getWriter().print(jsonobj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
					
	JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in StudentCertificatesAction : getstudentvisaissueddetails Ending");
	return null;
}
public ActionForward displayageissueddetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction  : displayageissueddetails Starting");
	
	try {
		
		
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		
		UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
		
		String stuid = request.getParameter("stuid");
		String agecetiid = request.getParameter("agecetiid");
		String selection = request.getParameter("selection");
		
		ArrayList<StudentCertificatesVo> stuList = new StudentCertificatesBD().displayageissueddetails(stuid,agecetiid,selection,cpojo);
		
		JSONObject obj=new JSONObject();
		obj.put("stuList",stuList);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction : displayageissueddetails Ending");
	return null;
}
public ActionForward displayconductdetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction  : displayconductdetails Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		
		UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
		
		String stuid = request.getParameter("stuid");
		String agecetiid = request.getParameter("agecetiid");
		String selection = request.getParameter("selection");
		ArrayList<StudentCertificatesVo> stuList = new StudentCertificatesBD().displayconductdetails(stuid,agecetiid,selection,cpojo);
		
		JSONObject obj=new JSONObject();
		obj.put("stuList",stuList);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction : displayconductdetails Ending");
	return null;
}
public ActionForward displaystudentvisadetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction  : displaystudentvisadetails Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		
		UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
		
		String stuid = request.getParameter("stuid");
		String agecetiid = request.getParameter("agecetiid");
	
		ArrayList<StudentCertificatesVo> stuList = new StudentCertificatesBD().displaystudentvisadetails(stuid,agecetiid,cpojo);
		
		JSONObject obj=new JSONObject();
		obj.put("stuList",stuList);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction : displaystudentvisadetails Ending");
	return null;
}

public ActionForward printCCCertificate(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction  : printCCCertificate Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		String stuname = request.getParameter("stuname");
		String fathername = request.getParameter("fathername");
		String accyear = request.getParameter("accyear");
		String school = request.getParameter("school");
		String dob = request.getParameter("dob");
		String classname = request.getParameter("classname");
		String conduct = request.getParameter("conduct");
		
		DobINWord WordDOB=new DobINWord();
		String d1=dob;
		String[] split=d1.split("-");
		int day = Integer.parseInt(split[0]);
		int month = Integer.parseInt(split[1]);
		int year = Integer.parseInt(split[2]);
		String dm=null;
		String y=null;

		if(year<2000){
			int	year1=year/100;
			int	year2=year%100;
				System.out.println("Today's Date(in words) is: "+WordDOB.convert(day)+" "+WordDOB.getMonth(month)+" "+WordDOB.convert(year1)+" "+WordDOB.convert(year2));
				dm=WordDOB.convert(day)+" "+WordDOB.getMonth(month);
				y=WordDOB.convert(year1)+" "+WordDOB.convert(year2);
			}
			else{
				System.out.println("Today's Date(in words) is: "+WordDOB.convert(day)+" "+WordDOB.getMonth(month)+" "+WordDOB.convert(year));
				dm=WordDOB.convert(day)+" "+WordDOB.getMonth(month);
				y=WordDOB.convert(year);
			}
		
		System.out.println("Today's Date is: "+d1.toString());
		System.out.println(dob);
		String[] acc=accyear.split("-");
		
		
		ArrayList<StudentRegistrationVo> list=new ArrayList<StudentRegistrationVo>();
		StudentRegistrationVo vo= new StudentRegistrationVo();
		vo.setStudClassId("1");
		list.add(vo);
		String sourceFileName =null;
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(!classname.equalsIgnoreCase("XII")){
			
			 
				String Bracket2=res.getString("Bracket2");
				String Bracket3=res.getString("Bracket3");
				String Bracket4=res.getString("Bracket4");
				String Bracket5=res.getString("Bracket6");
			    parameters.put("bracket2", Bracket2);
				parameters.put("bracket3", Bracket3);
				parameters.put("bracket4", Bracket4);
				parameters.put("bracket5", Bracket5);
				sourceFileName = request.getRealPath("Reports/CourceAndConductCertificate.jrxml");
		}else{
			String Bracket0=res.getString("Bracket0");
			String Bracket2=res.getString("Bracket2");
			String Bracket3=res.getString("Bracket3");
			String Bracket8=res.getString("Bracket8");
			String Bracket5=res.getString("Bracket7");
				parameters.put("bracket2", Bracket0);
				parameters.put("bracket3", Bracket2);
				parameters.put("bracket4", Bracket3);
				parameters.put("bracket5", Bracket5);
				parameters.put("bracket6", Bracket8);
			sourceFileName = request.getRealPath("Reports/CourceAndConductCertificateofXII.jrxml");
			
			
		}
		// sourceFileName = request.getRealPath("Reports/CourceAndConductCertificate.jrxml");
		JasperDesign design = JRXmlLoader.load(sourceFileName);
		JasperReport jasperreport = JasperCompileManager.compileReport(design);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

		String PropfilePath = getServlet().getServletContext().getRealPath("")+"\\images\\" + ImageName.trim();

		String schName = res.getString("SchoolName");
		
		System.out.println("SCHOOL NAME "+schName);
		parameters.put("PropfilePath", PropfilePath);
		parameters.put("schoolname", school);
		parameters.put("stuname", stuname);
		parameters.put("fathername",fathername);
		parameters.put("accyear", accyear);
		parameters.put("laccyear", acc[1]);
		parameters.put("dob", dob);
		parameters.put("conduct", conduct);
		parameters.put("dm", dm);
		parameters.put("y", y);
		
		JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
		System.out.println("JASPER VIVER");
		JasperViewer viewer = new JasperViewer(jasperPrint, false);
		System.out.println("AFTER JASPER VIVER");
		viewer.setVisible(true);
		PrinterJob job = PrinterJob.getPrinterJob();
		   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
		   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
		   printRequestAttributeSet.add(new Copies(1));
		   JRPrintServiceExporter exporter;
		   exporter = new JRPrintServiceExporter();
		   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
		   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
		   exporter.exportReport();
		   job.print(printRequestAttributeSet);

	
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentCertificatesAction : printCCCertificate Ending");
	return null;
}
}
