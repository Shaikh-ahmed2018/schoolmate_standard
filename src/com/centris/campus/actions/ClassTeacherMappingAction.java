package com.centris.campus.actions;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.daoImpl.ClassTeacherMappingDAOIMPL;
import com.centris.campus.delegate.ClassTeacherMappingDelegate;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ClassTeacherMappingVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.TeacherVo;
import com.centris.campus.vo.classVo;


public class ClassTeacherMappingAction extends DispatchAction 

{
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	public ActionForward adddisplaydetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
		{
			
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingAction : adddisplaydetails Starting");
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_ADMIN);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_ADMIN);
		String status = request.getParameter("result"); 
		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.SuccessMappingMsg)) {

				request.setAttribute("successmessagediv", MessageConstants.SuccessMappingMsg);
			}
			if (status.equalsIgnoreCase(MessageConstants.UpdateMappingMsg)) {

				request.setAttribute("successmessagediv", MessageConstants.UpdateMappingMsg);
			}
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingAction : adddisplaydetails Ending");
		
		
			return mapping.findForward(MessageConstants.ADD_DISPLAY_DETAILS);
		
		
		
		}
	
	
	public ActionForward getclass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
			
		{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingAction : getclass Starting");
		
		
		try {
			
			ClassTeacherMappingVO vo= new ClassTeacherMappingVO();
			
			vo.setClassteacherid(request.getParameter("Classteacherid")); 
			
			JSONObject jsonObject=new JSONObject();
			
			if( null==vo.getClassteacherid() || !vo.getClassteacherid().equalsIgnoreCase(""))
				
			{
				
				ArrayList<ClassTeacherMappingVO> updateclasslist = new ClassTeacherMappingDelegate().getclassupdate(vo);
				jsonObject.put("jsonupdateResponse", updateclasslist);
			
			}
			
			ArrayList<ClassTeacherMappingVO> classlist = new ClassTeacherMappingDelegate().getclass(vo);
			
			jsonObject.put("jsonResponse", classlist);
			
			response.getWriter().println(jsonObject);
			
			
		
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingAction : getclass Ending");
			return null;
		
		} 
		
		
		
		public ActionForward getsection(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception 
				
			{
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassTeacherMappingAction : getsection Starting");
			
			
			try {
				
				ClassTeacherMappingVO vo= new ClassTeacherMappingVO();
				vo.setClassid(request.getParameter("classid"));
				vo.setClassteacherid(request.getParameter("classteacherid").trim());
				
				
				
				if(vo.getClassteacherid()!=null && !( "".equalsIgnoreCase(vo.getClassteacherid())))
					
				{
					
				ArrayList<ClassTeacherMappingVO> updatesectionlist = new ClassTeacherMappingDelegate().getsectionupdate(vo);
				
				JSONObject jsonObject=new JSONObject();
				
				jsonObject.put("updatesectionlist", updatesectionlist);
				
				response.getWriter().println(jsonObject);
				
				}
				
				
				
				else{
					

					
					ArrayList<ClassTeacherMappingVO> sectionlist = new ClassTeacherMappingDelegate().getsection(vo);
					
					JSONObject jsonObject=new JSONObject();
					
					jsonObject.put("jsonResponse", sectionlist);
					
					response.getWriter().println(jsonObject);
					
					
					
				}
				
			
			
				
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
			
			
			
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassTeacherMappingAction : getsection Ending");
				return null;
			
			}
			
			
			
			public ActionForward getteacher(ActionMapping mapping,
					ActionForm form, HttpServletRequest request,
					HttpServletResponse response) throws Exception 
					
				{
				
				
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in ClassTeacherMappingAction : getteacher Starting");
				
				
				try {
					
					ClassTeacherMappingVO vo= new ClassTeacherMappingVO();
					
					vo.setClassteacherid(request.getParameter("Classteacherid")); 
					
					if(vo.getClassteacherid().equalsIgnoreCase("")||vo.getClassteacherid()==null)
					
					{
					
					ArrayList<ClassTeacherMappingVO> teacherlist = new ClassTeacherMappingDelegate().getteacher(vo);
					
					JSONObject jsonObject=new JSONObject();
					
					jsonObject.put("jsonResponse", teacherlist);
					
					response.getWriter().println(jsonObject);
					
					}
					
					
					if(!vo.getClassteacherid().equalsIgnoreCase(""))
						
					{
					
					ArrayList<ClassTeacherMappingVO> updateteacherlist = new ClassTeacherMappingDelegate().getupdateteacherlist(vo);
					ArrayList<ClassTeacherMappingVO> upteacherlist = new ClassTeacherMappingDelegate().getupteacherlist(vo);
					
					JSONObject jsonObject=new JSONObject();
					
					jsonObject.put("jsonupdateteacherResponse", updateteacherlist);
					jsonObject.put("jsonupteacherlist", upteacherlist);
					
					response.getWriter().println(jsonObject);
					
					}
					
				
					
				} catch (Exception exception) {
					logger.error(exception.getMessage(), exception);
					exception.printStackTrace();
				}
				
				
				
				
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.END_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in ClassTeacherMappingAction : getteacher Ending");
					return null;
				
				}
				
				public ActionForward addmappingdetails(ActionMapping mapping,
						ActionForm form, HttpServletRequest request,
						HttpServletResponse response) throws Exception 
						
					{

					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : addmappingdetails Starting");
					
					
					try {
						
						ClassTeacherMappingVO vo=new ClassTeacherMappingVO();
						vo.setClassid(request.getParameter("classid"));
						vo.setSectionid(request.getParameter("sectionid"));
						vo.setTeacherid(request.getParameter("teacherid"));
						vo.setClassteacherid(request.getParameter("classteacherid"));
						vo.setCreateuser(HelperClass.getCurrentUserID(request));
							
						ClassTeacherMappingDelegate delegate=new ClassTeacherMappingDelegate();
						
						String result=delegate.addmappingdetails(vo);
						
						JSONObject jsonobj = new JSONObject();
						
						jsonobj.put("jsonResponse", result);
						
						response.getWriter().print(jsonobj);
						
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}

					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : addmappingdetails Ending");

					
					return null;
					
					
				
				}
				
				
				
				public ActionForward editclassdetails(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				
				{

					

					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : editclassdetails Starting");
					
					
					String classteacherid=request.getParameter("id");
					
					try {
						
						ClassTeacherMappingVO vo=new ClassTeacherMappingVO();
						
						vo.setClassteacherid(classteacherid);
						request.setAttribute("classteacherid", vo);
						
					

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}

					
					
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : editclassdetails Ending");

					return mapping.findForward(MessageConstants.ADD_DISPLAY_DETAILS);
				
				}
				
				
				public ActionForward deletemappingDetails(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				
				{
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : deletemappingDetails Starting");
					
					
					String result="";
					
					
					try {
						
						ClassTeacherMappingVO vo=new ClassTeacherMappingVO();
						
						vo.setClassteacherid(request.getParameter("classteacher"));
						
						result = new ClassTeacherMappingDelegate()
						.deletemappingDetails(vo);
						

						JSONObject jsonObject=new JSONObject();
						
						jsonObject.put("jsonResponse", result);
						
						response.getWriter().println(jsonObject);
					

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}

					
					
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : deletemappingDetails Ending");

					

					return null;
					
				}
				
				
				public ActionForward downloadteachermapping(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : downloadteachermapping Starting");
					
					
					try {

						File pdfxls = null;
						FileInputStream input = null;
						BufferedInputStream buf = null;
						ServletOutputStream stream = null;

						String sourceFileName = request
								.getRealPath("Reports/TeacherMappingDetailsXLSReport.jrxml");
						JasperDesign design = JRXmlLoader.load(sourceFileName);
						JasperReport jasperreport = JasperCompileManager
								.compileReport(design);
						List<ClassTeacherMappingVO> MasterList = new ArrayList<ClassTeacherMappingVO>();
						MasterList = (List<ClassTeacherMappingVO>) request.getSession(false)
								.getAttribute("Exceldownload");
					

						JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
								MasterList);
						Map parameters = new HashMap();

						stream = response.getOutputStream();
						JasperPrint print = JasperFillManager.fillReport(jasperreport,
								parameters, beanColDataSource);
						JRXlsExporter exporter = new JRXlsExporter();
						File outputFile = new File(
								request.getRealPath("Reports/TeacherMappingDetailsXLSReport.xls"));
						FileOutputStream fos = new FileOutputStream(outputFile);
						String[] sheetNames = { "Teacher Mapping Details Excel Report" };
						exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
						exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
						exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
								Boolean.FALSE);
						exporter.setParameter(
								JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
								Boolean.TRUE);
						exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
								sheetNames);
						exporter.setParameter(
								JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
								Boolean.TRUE);
						exporter.setParameter(
								JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
								Boolean.FALSE);
						exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,
								Boolean.TRUE);
						exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
								Boolean.FALSE);

						exporter.exportReport();

						pdfxls = new File(
								request.getRealPath("Reports/TeacherMappingDetailsXLSReport.xls"));
						response.setContentType("application/octet-stream");
						response.addHeader("Content-Disposition",
								"attachment; filename=TeacherMappingDetailsXLS.xls");
						response.setContentLength((int) pdfxls.length());
						input = new FileInputStream(pdfxls);
						buf = new BufferedInputStream(input);
						int readBytes = 0;
						stream = response.getOutputStream();
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
							+ " Control in ClassTeacherMappingAction : downloadteachermapping Ending");
					
					return null;
					
					
}
				
				public ActionForward AddClassTeacherMapping(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : AddClassTeacherMapping Starting");
					
					try {
						
						request.setAttribute(MessageConstants.MODULE_NAME,
								MessageConstants.BACKOFFICE_STAFF);
						request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
								MessageConstants.MODULE_STAFF);
						request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
								LeftMenusHighlightMessageConstant.MODULE_STAFF_CLASSTEACHER_MAPPING);
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						
						String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
						
						ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
						ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
						
						
						
						List<TeacherVo> accyearLocList = new ClassTeacherMappingDelegate().getAccyearLocationList(custdetails,accYear,"%%");
						
						request.setAttribute("locationList", locationList);
						request.setAttribute("AccYearList", accYearList);
						request.setAttribute("accyearLocList", accyearLocList);
						request.setAttribute("accyearHidden", accYear);
						
						request.setAttribute("historyacayear",request.getParameter("historyacayear"));
						request.setAttribute("historylocation",request.getParameter("historylocation"));


					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : AddClassTeacherMapping Ending");
					
				return mapping.findForward("addClassTeacherMapping");
					
					
}		
				
			
				public ActionForward Class_spec_teacherDisplay(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : Class_spec_teacherDisplay Starting");
					
					
					try {
						
						request.setAttribute(MessageConstants.MODULE_NAME,
								MessageConstants.BACKOFFICE_STAFF);
						request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
								MessageConstants.MODULE_STAFF);
						request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
								LeftMenusHighlightMessageConstant.MODULE_STAFF_CLASSTEACHER_MAPPING);
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
						ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
						request.setAttribute("locationList", locationList);
						request.setAttribute("AccYearList", accYearList);
						
						String accId = request.getParameter("ids").split(",")[0];
						String locId = request.getParameter("ids").split(",")[1];
						String teacherId = request.getParameter("teacherId");
						String locationName = HelperClass.getLocationFace(locId, custdetails);
						
						classVo vo = new classVo();
						vo.setAccyearId(accId);
						vo.setLocationId(locId);
						vo.setTeacherId(teacherId);
						
						HelperClass.getAcademicYearFace(accId, custdetails);
						
						ArrayList<TeacherVo> teacherInfo = new ClassTeacherMappingDelegate().getTeacherByLoc(custdetails,locId,accId);
					
					
						request.setAttribute("accId", accId);
						request.setAttribute("locId", locId);
						
						request.setAttribute("accname",	HelperClass.getAcademicYearFace(accId, custdetails));
						request.setAttribute("locName",locationName);
						
						
						String status = new ClassTeacherMappingDelegate().checkTeacherStatus(vo,custdetails);
						
						System.out.println("status"+status);
						request.setAttribute("teacherInfo",teacherInfo);
						request.setAttribute("teacherStatus",status);
						
						request.setAttribute("historyacayear",request.getParameter("historyacayear"));
						request.setAttribute("historylocation",request.getParameter("historylocation"));
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : Class_spec_teacherDisplay Ending");
					
				return mapping.findForward("Class_spec_teacherMpn");
		
				}
				
				
				
				
				public ActionForward ClassTeacherMappingInfo(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : ClassTeacherMappingInfo Starting");
					
					
					try {
						
						request.setAttribute(MessageConstants.MODULE_NAME,
								MessageConstants.BACKOFFICE_STAFF);
						request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
								MessageConstants.MODULE_STAFF);
						request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
								LeftMenusHighlightMessageConstant.MODULE_STAFF_CLASSTEACHER_MAPPING);
						
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
						ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
						request.setAttribute("locationList", locationList);
						request.setAttribute("AccYearList", accYearList);
						
						String currentLoc = (String) request.getSession(false).getAttribute("current_schoolLocation");
				
						String locId = currentLoc;
						String teacherId = request.getParameter("teacherId");
						String accId =  request.getParameter("accyear");
						
						
						classVo vo = new classVo();
						
						vo.setAccyearId(accId);
						vo.setLocationId(locId);
						vo.setTeacherId(teacherId);
						
						
						ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassByLocation(locId,custdetails);
				
						ArrayList<TeacherVo> teacherInfo = new ClassTeacherMappingDelegate().getTeacherByLoc(custdetails,locId,accId);
						
						
						request.setAttribute("teacherInfo",teacherInfo);
						request.setAttribute("teacherName",HelperClass.getTeacherNameFace(teacherId, custdetails));
						request.setAttribute("accname",	HelperClass.getAcademicYearFace(accId, custdetails)); 
						request.setAttribute("locationName",HelperClass.getLocationFace(locId, custdetails));
						
						
						request.setAttribute("classList", classList);
						request.setAttribute("accId", accId);
						request.setAttribute("locId", locId);
						request.setAttribute("teacherId", teacherId);
						
						String classId =  request.getParameter("classId");
						
						ArrayList<ReportMenuVo> sectionByClass = new ReportsMenuBD().getSectionsByClassLoc(classId, locId,custdetails);
						request.setAttribute("sectionByClass", sectionByClass);

						
						List<classVo> displayList = new ClassTeacherMappingDelegate().displayClassTeacherList(custdetails,vo);

						request.setAttribute("displayList", displayList);
						
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : ClassTeacherMappingInfo Ending");
					
				return mapping.findForward("ClassTeacherMappingInfo");
		
				}
				
				
				public ActionForward getAccyear(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : AddClassTeacherMapping Starting");
					
					try {
						
						request.setAttribute(MessageConstants.MODULE_NAME,
								MessageConstants.BACKOFFICE_STAFF);
						request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
								MessageConstants.MODULE_STAFF);
						request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
								LeftMenusHighlightMessageConstant.MODULE_STAFF_CLASSTEACHER_MAPPING);
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						
						String accId =  request.getParameter("accId");
						String locId = request.getParameter("locId");
						
						if(accId.equalsIgnoreCase("all") || accId.equalsIgnoreCase("") || accId==null ){
							accId="%%";
						}
						
						
						System.out.println("accId"+accId);
						System.out.println("locId"+locId);
						
						ArrayList<ReportMenuVo> accYearList = new ClassTeacherMappingDAOIMPL().getAccYearsOnchange(custdetails,accId);
						

						JSONObject obj = new JSONObject();
						obj.put("accyearList", accYearList);
						response.getWriter().print(obj);
						

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : AddClassTeacherMapping Ending");
					
				return null;
				
				
				
				}
				
				
				
				public ActionForward getSectionByClass(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : getSectionByClass Starting");
					
					try {
					
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						
						String classId =  request.getParameter("classId");
						String locId =  request.getParameter("locId");
						
						
						System.out.println("locId"+locId);
						System.out.println("classId"+classId);
						
						ArrayList<ReportMenuVo> sectionByClass = new ReportsMenuBD().getSectionsByClassLoc(classId, locId,custdetails);

						JSONObject obj = new JSONObject();
						obj.put("data", sectionByClass);
						response.getWriter().print(obj);
						

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : getSectionByClass Ending");
					
				return null;
				
				
				}
				
				
				
				public ActionForward getSpecbyClassLoc(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : getSpecbyClassLoc Starting");
					
					try {
					
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						String classId =  request.getParameter("classId");
						String locId =  request.getParameter("locId");
						
						System.out.println("classId"+classId);
						System.out.println("locId"+locId);
						
						
						ArrayList<ReportMenuVo> specList = new ReportsMenuBD().getSpecByClassLoc(classId, locId,custdetails);

						JSONObject obj = new JSONObject();
						obj.put("data", specList);
						response.getWriter().print(obj);
						

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : getSpecbyClassLoc Ending");
					
				return null;
				
				}
				
				
				
				
				public ActionForward getClassByLoc(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : getClassByLoc Starting");
					
					try {
						
						
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						String locId =  request.getParameter("locId");
						
						System.out.println("locId"+locId);
						
						ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassByLocation(locId,custdetails);
						

						JSONObject obj = new JSONObject();
						obj.put("data", classList);
						response.getWriter().print(obj);
						

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : getClassByLoc Ending");
					
				return null;
				
				}
				
	 public ActionForward saveTeacherClassMapping(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
	 {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassTeacherMappingAction : saveTeacherClassMapping Starting");
			
			try {
			
				UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				String classId =  request.getParameter("classId");
				String secId =  request.getParameter("secId");
				String specId =  request.getParameter("specId");
				
				
				String accId =  request.getParameter("accId");
				String locId =  request.getParameter("locId");
				String teacherId =  request.getParameter("teacherId");

				
				classVo vo = new classVo();
				vo.setClassId(classId);
				vo.setSecId(secId);
				vo.setSpecId(specId);
				vo.setAccyearId(accId);
				vo.setLocationId(locId);
				vo.setTeacherId(teacherId);
				
				String status = new ClassTeacherMappingDelegate().saveTeacherClassMapping(vo,custdetails,request);
				
				System.out.println("status save info............" +status);

				JSONObject obj = new JSONObject();
				obj.put("data", status);
				response.getWriter().print(obj);
				

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassTeacherMappingAction : saveTeacherClassMapping Ending");
			
		return null;
		
		}


				public ActionForward getAccyearLocStatus(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : getAccyearLocStatus Starting");
					
					try {
						
						String accId = request.getParameter("accId");
						String locId = request.getParameter("locId");
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						if(locId.equalsIgnoreCase("all")){
							locId="%%";
						}
						
						List<TeacherVo> accyearLocList = new ClassTeacherMappingDelegate().getAccyearLocationList(custdetails,accId,locId);
						
						JSONObject obj = new JSONObject();
						obj.put("data", accyearLocList);
						response.getWriter().print(obj);
						
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : getAccyearLocStatus Ending");
					
				return null;
					
}		
				
				
				
				public ActionForward deleteClassTeacherMap(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : deleteClassTeacherMap Starting");
					
					try {
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						String id = request.getParameter("id");
				
							System.out.println("id:::::::::::::"+id);
							
						
						
						String status = new ClassTeacherMappingDelegate().deleteClassTeacherMap(custdetails,id);
						
						JSONObject obj = new JSONObject();
						obj.put("data", status);
						response.getWriter().print(obj);
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : deleteClassTeacherMap Ending");
					
				return null;
				
				
				}
		
				
				
				public ActionForward validateClassSectionSpec(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : validateClassSectionSpec Starting");
					
					try {
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						String classId =  request.getParameter("classId");
						String secId =  request.getParameter("secId");
						String specId =  request.getParameter("specId");
						String teacherId = request.getParameter("teacherId");
						
						classVo vo = new classVo();
						vo.setClassId(classId);
						vo.setSecId(secId);
						vo.setSpecId(specId);
						vo.setTeacherId(teacherId);
						
				
						String status = new ClassTeacherMappingDelegate().validateClassSectionSpec(custdetails,vo);
						
						JSONObject obj = new JSONObject();
						obj.put("data", status);
						response.getWriter().print(obj);
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : validateClassSectionSpec Ending");
					
				return null;
				
				
				}		
				
				
				
				
				
				
				
/*	submodule 4: subjectwise teacher mapping */
				
				
				public ActionForward AddsubjectTeacherMapping(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : AddsubjectTeacherMapping Starting");
					
					try {
						
						request.setAttribute(MessageConstants.MODULE_NAME,
								MessageConstants.BACKOFFICE_STAFF);
						request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
								MessageConstants.MODULE_STAFF);
						request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
								LeftMenusHighlightMessageConstant.MODULE_STAFF_SUBJECTTEACHER_MAPPING);
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						
						String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
						
						ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
						ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
						request.setAttribute("AccYearList", accYearList);
						request.setAttribute("accyearHidden", accYear); //current accyear
						request.setAttribute("locationList", locationList);
						
						List<TeacherVo> accyearLocList = new ClassTeacherMappingDelegate().getAccyearLocationList(custdetails,accYear,"%%");
						request.setAttribute("accyearLocList", accyearLocList);

						request.setAttribute("historyacayear", request.getParameter("historyacayear"));
						request.setAttribute("historylocId", request.getParameter("historylocId"));

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : AddsubjectTeacherMapping Ending");
					
				return mapping.findForward("AddsubjectTeacherMapping");
					
					
}				
						
				
				
				public ActionForward SubjectwiseTeacherList(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : SubjectwiseTeacherList Starting");
					
					try {
						
						request.setAttribute(MessageConstants.MODULE_NAME,
								MessageConstants.BACKOFFICE_STAFF);
						request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
								MessageConstants.MODULE_STAFF);
						request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
								LeftMenusHighlightMessageConstant.MODULE_STAFF_SUBJECTTEACHER_MAPPING);
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	
						String accId = request.getParameter("id").split(",")[0];
						String locId = request.getParameter("id").split(",")[1];
						
						String accyearName = HelperClass.getAcademicYearFace(accId, custdetails);
						String locationName = HelperClass.getLocationFace(locId, custdetails);
						request.setAttribute("accyearName", accyearName);
						request.setAttribute("locationName", locationName);
						request.setAttribute("accId", accId);
						request.setAttribute("locId", locId);
						
						TeacherVo vo = new TeacherVo();
						vo.setAccyearId(accId);
						vo.setLocationId(locId);
						
						
						List<TeacherVo> teachersList = new ClassTeacherMappingDelegate().SubjectwiseTeacherList(vo,custdetails);
						request.setAttribute("teachersList", teachersList);
						
						request.setAttribute("historyacayear", request.getParameter("historyacayear"));
						request.setAttribute("historylocId", request.getParameter("historylocId"));

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : SubjectwiseTeacherList Ending");
					
				return mapping.findForward("SubjectwiseTeacherList");
					
		}			
				
				
				public ActionForward SubjectwiseTeacherAdd(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : SubjectwiseTeacherAdd Starting");
					
					try {
						
						request.setAttribute(MessageConstants.MODULE_NAME,
								MessageConstants.BACKOFFICE_STAFF);
						request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
								MessageConstants.MODULE_STAFF);
						request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
								LeftMenusHighlightMessageConstant.MODULE_STAFF_SUBJECTTEACHER_MAPPING);
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						String accId  = request.getParameter("accyear");
						String locId  = request.getParameter("location");
						String teacherId  = request.getParameter("teacherId");
						String classId = request.getParameter("classId");
						String status = request.getParameter("status");
						
						System.out.println("statussssssssssss "+status);
						
						ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassByLocation(locId,custdetails);
						
						request.setAttribute("teacherName",HelperClass.getTeacherNameFace(teacherId, custdetails));
						request.setAttribute("accname",	HelperClass.getAcademicYearFace(accId, custdetails)); 
						request.setAttribute("locationName",HelperClass.getLocationFace(locId, custdetails));
						
						
						request.setAttribute("classList", classList);
						request.setAttribute("accId", accId);
						request.setAttribute("locId", locId);
						request.setAttribute("teacherId", teacherId);
						
						
						TeacherVo vo = new TeacherVo();
						vo.setAccyearId(accId);
						vo.setLocationId(locId);
						vo.setTeacherId(teacherId);
						vo.setStatus(status);
						
						List<TeacherVo> teachersList = new ClassTeacherMappingDelegate().SubjectwiseTeacherList(vo,custdetails);
						request.setAttribute("teachersList", teachersList);
						
						
					
						List<classVo>  teacherSubjectList= new ClassTeacherMappingDelegate().getSubjectTeacherList(custdetails,vo);
						
						request.setAttribute("displayList", teacherSubjectList);
						
						
						
						

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : SubjectwiseTeacherAdd Ending");
					
				return mapping.findForward("SubjectwiseTeacherAdd");
				
				
				}	
				
				
				
				
				
				public ActionForward getAllSubjectsByClass(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : getAllSubjectsByClass Starting");
					
					try {
						
						request.setAttribute(MessageConstants.MODULE_NAME,
								MessageConstants.BACKOFFICE_STAFF);
						request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
								MessageConstants.MODULE_STAFF);
						request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
								LeftMenusHighlightMessageConstant.MODULE_STAFF_SUBJECTTEACHER_MAPPING);
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
						String locId  = request.getParameter("locId");
						String classId = request.getParameter("classId").split(":")[0];
						String specId = request.getParameter("classId").split(":")[1];
						String secId = request.getParameter("classId").split(":")[2];
						
						
						
						TeacherVo vo = new TeacherVo();
						vo.setLocationId(locId);
						vo.setClassId(classId);
						vo.setSpecId(specId);
						
						List<TeacherVo> subjectList = new ClassTeacherMappingDelegate().getAllSubject(vo,custdetails);
						
						JSONObject obj = new JSONObject();
						obj.put("data", subjectList);
						response.getWriter().print(obj);
						

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : getAllSubjectsByClass Ending");
					
				return null;
				
				}	
				
				
					
				
				
				
				public ActionForward saveTeacherSubjectMapInfo(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
				{
					
					logger.setLevel(Level.DEBUG);
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.START_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : saveTeacherSubjectMapInfo Starting");
					
					try {
						
						request.setAttribute(MessageConstants.MODULE_NAME,
								MessageConstants.BACKOFFICE_STAFF);
						request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
								MessageConstants.MODULE_STAFF);
						request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
								LeftMenusHighlightMessageConstant.MODULE_STAFF_SUBJECTTEACHER_MAPPING);
						
						UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
				
						
						
						String classSecSpecIds  = request.getParameter("classSecSpecIds");
						
						String locId = request.getParameter("locId");
						String teacherId  = request.getParameter("teacherId");
						String accId = request.getParameter("accId");
						String updateMapIdArray = request.getParameter("updateMapIdArray");
						
						
						
						String subjectIdArray   = request.getParameter("subjectIdArray");
						
						
						System.out.println("44444444444444444 "+subjectIdArray);
						
						String currentuser = HelperClass.getCurrentUserID(request);
						
						TeacherVo vo = new TeacherVo();
						vo.setLocationId(locId);
				
						vo.setTeacherId(teacherId);
						vo.setAccyearId(accId);
						vo.setCurrentUser(currentuser);
						vo.setSubjectNameArray(subjectIdArray);
						vo.setUpdateMapIdArray(updateMapIdArray);
						
						
						
						String status = new ClassTeacherMappingDelegate().saveTeacherSubjectMapInfo(vo,custdetails,classSecSpecIds);
						
						JSONObject obj = new JSONObject();
						obj.put("data", status);
						response.getWriter().print(obj);
						

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					
					
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in ClassTeacherMappingAction : saveTeacherSubjectMapInfo Ending");
					
				return null;
				
				}	
				
				
				
				
				
				
				
				
				
				
				
}


