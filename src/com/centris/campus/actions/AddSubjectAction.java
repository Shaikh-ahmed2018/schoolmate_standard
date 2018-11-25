package com.centris.campus.actions;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
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
import org.apache.struts.upload.FormFile;
import org.json.JSONObject;
import com.centris.campus.daoImpl.AddSubjectDaoImpl;
import com.centris.campus.delegate.AddSubjectDelegate;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.SectionBD;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.JPropertyReader;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddLabVO;
import com.centris.campus.vo.AddSubjectVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.google.gson.JsonObject;
public class AddSubjectAction extends DispatchAction {

	private static final Logger logger = Logger
			.getLogger(AddSubjectAction.class);
	static ResourceBundle res = ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	private static String EcampusPro_Documents_Dir  = JPropertyReader
			.getProperty("EcampusPro_Documents_Dir");
		private static String ImageName = res.getString("ImageName");
	
	
	
	public ActionForward classList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : classList Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			List<ClassPojo> classList = new ArrayList<ClassPojo>();
			ClassBD delegate = new ClassBD();
			classList = delegate.getClassDetails(schoolLocation,userLoggingsVo);

			JSONObject object = new JSONObject();
			object.put("classList", classList);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : classList Ending");

		return null;
	}

	public ActionForward getsubject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getsubject Starting");
		try {

			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String title="Add New Subject";
			request.setAttribute("title", title);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SUBJECTDETAILS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);
			request.setAttribute("locationList", locationList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getsubject Ending");

		return mapping.findForward(MessageConstants.ADD_SUBJECT);
	}

	@SuppressWarnings({ "resource", "null", "unused" })
	  
	public ActionForward addSubject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction:  addSubject Starting");
		
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			String userId = HelperClass.getCurrentUserID(request);
			String extension = null;
			AddSubjectForm addSubjectForm = (AddSubjectForm) form;
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			
			AddSubjectVo vo=new AddSubjectVo();
			
			vo.setLocationId(request.getParameter("locationId"));
			vo.setClassname(request.getParameter("classname"));
			vo.setSpecId(request.getParameter("specialization"));
			vo.setSubjtname(request.getParameter("subjtname"));
			vo.setSubjectCode(request.getParameter("subjectCode"));
			vo.setIsLanguage(request.getParameter("isLangauge"));
			vo.setIslab(request.getParameter("isLab"));
			vo.setSubType(request.getParameter("subtype"));
			vo.setDescription(request.getParameter("description"));
			vo.setLog_audit_session(log_audit_session);
			vo.setHiddensubjectId(request.getParameter("hiddensubId"));
			vo.setCreatedUserId(userId);
			
			String status = new AddSubjectDelegate().addSubject(vo,userLoggingsVo);
            
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status", status);
			response.getWriter().print(jsonobj);
		
			  
		}catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : addSubject Ending");

		return null;

	}
	
	@SuppressWarnings("unused")
	public ActionForward updateSubject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction:  updateSubject Starting");
		String user = HelperClass.getCurrentUser(request);

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			
			String userId = HelperClass.getCurrentUserID(request);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String extension = null;
			AddSubjectForm addSubjectForm = (AddSubjectForm) form;

			addSubjectForm.setLog_audit_session(log_audit_session);
			addSubjectForm.setCreatedUserId(userId);
			

			FormFile formFile = null;
			String path = null;

			File fileURL = null;
			FileOutputStream fileOutStream =null;
			FileOutputStream fos = null;
			if(formFile!=null){

			formFile = addSubjectForm.getFile();

			
			
			
			
			File secondDir=null;
			File firstDir=null;
			
			
			
			
		  /* firstDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS/");*/
			
			
			path = getServlet().getServletContext().getRealPath("/")
					+ "FIles\\SYLLABUS";
			File files = new File(path);
			if (!files.exists()) {
				if (files.mkdirs()) {
				}
			}
			path = files.getAbsolutePath();
			

		  
		    if(formFile != null){
		    int i = formFile.getFileName().lastIndexOf('.');
			if (i > 0) {
				extension = formFile.getFileName().substring(i + 1);
			}
			String sub=addSubjectForm.getSubjtname();
			String subname=sub.replace("/", "_");
			String fileName = subname + "_" + addSubjectForm.getClsnam() + "." + extension;

		
			fileURL = new File(path, fileName);
			fos = new FileOutputStream(fileURL);
			fos.write(formFile.getFileData());
			
			String file1 = fileURL.getPath();
			
			if(fileURL.exists()){
			
				/* secondDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS/"+addSubjectForm.getSubjtname() + "_"
							+ addSubjectForm.getClassname() + "." + extension);*/
				
				fileURL.mkdir();
				 fileOutStream = new FileOutputStream(fileURL);
				 fileOutStream.write(formFile.getFileData());
				 fileURL.mkdir();
				 addSubjectForm.setFilename(addSubjectForm.getSubjtname() + "_"
							+ addSubjectForm.getClsnam() + "." + extension);

				 
			}
			else{
				
				/*firstDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS");*/
				fileURL.mkdir();
				
			/*	secondDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS/"+addSubjectForm.getSubjtname() + "_"
						+ addSubjectForm.getClassname() + "." + extension);*/
				fileOutStream = new FileOutputStream(fileURL);
				 fileOutStream.write(formFile.getFileData());
				
				
				addSubjectForm.setFilename(addSubjectForm.getSubjtname() + "_"
						+ addSubjectForm.getClsnam()+ "." + extension);
			}
			
		
		   }
			}
			else{
				addSubjectForm.setFilename("-");
			}
			addSubjectForm.setFile(addSubjectForm.getFile());
			
			 boolean updatesubject = new AddSubjectDelegate().updateSubjectDetails(addSubjectForm,userLoggingsVo);

			 
			 
			
      	
						 if (updatesubject == false) {
								request.setAttribute("errorMessage",
										"Subject Already Exists");
							    } else if(updatesubject == true){
								request.setAttribute("successmessagediv",
										"Update Subject Progressing...");
							    }
							    else
							    {
							    	request.setAttribute("errorMessage",
											"Subject not Updated ! Try Again");
							    }


			fileOutStream.close();
			
			
			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();

			String searchTerm = request.getParameter("searchname");
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			obj.setSearchName(searchTerm);


			request.setAttribute("allsubjects", subjectlist);

			
		

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : updateSubject Ending");

		return mapping.findForward(MessageConstants.UPDATE_SUBJECT);

	}
	
	public ActionForward DeleteSubject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : DeleteSubject Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			//System.out.println("coming inside ActionL:");
			String subList = request.getParameter("subjectlist");
			String locationList[]=request.getParameter("locationList").split(",");
			//System.out.println("subList" +subList);
			String[] idList=subList.split(",");
			//System.out.println("idList" +idList.length);
             
			ViewallSubjectsVo vo=new ViewallSubjectsVo();
			vo.setStatus(request.getParameter("status"));
            vo.setRemark(request.getParameter("reason"));
           
            vo.setLog_audit_session(log_audit_session);
            String button =request.getParameter("button");
			String status = new AddSubjectDelegate().DeleteSubject(idList,locationList,vo,button,userLoggingsVo);
			//System.out.println("status" +status);

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
				+ " Control in AddSubjectAction : DeleteSubject Ending");

		return null;
	}

	public ActionForward searchsubjectdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : searchsubjectdetails Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ViewallSubjectsVo voOvj = new ViewallSubjectsVo();
			String SearchName = request.getParameter("searchname");
			voOvj.setSearchName(SearchName);
			voOvj.setCustid(userLoggingsVo.getCustId());
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();
			subjectlist = new AddSubjectDelegate().searchsubjectdetails(voOvj,userLoggingsVo);

			request.setAttribute("allsubjects", subjectlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : searchsubjectdetails Ending");

		return mapping.findForward(MessageConstants.SUBJECT_LIST);
	}

	public ActionForward getSubjectDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getSubjectDetails Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SUBJECTDETAILS);
			
            UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String title = "Modify Subject";
			request.setAttribute("title", title);
			
			String locId = request.getParameter("locId");
			String classname = request.getParameter("classname");
			String specialization = request.getParameter("specialization");
			String status = request.getParameter("status");
			
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			List<SectionForm> classDetailsIDandClassDetailsNameList = new ArrayList<SectionForm>();
			SectionBD sectionDelegate = new SectionBD();
			classDetailsIDandClassDetailsNameList = sectionDelegate.getCampusClassDetailsIDandClassDetailsNameBD(schoolLocation,dbdetails);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);
			request.setAttribute("locationList", locationList);
			
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			String subjectcode = request.getParameter("subjectcode");
			obj.setSubjectid(subjectcode);
		
			ViewallSubjectsVo subjectdetails = new AddSubjectDelegate().getSubjectDetails(obj,dbdetails);
			request.setAttribute("success", "success");
			request.setAttribute("singlesubjectdetails", subjectdetails);
			
			request.setAttribute("locId1",locId);
			request.setAttribute("classname1",classname);
			request.setAttribute("specialization1",specialization);
			request.setAttribute("status1",status);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getSubjectDetails Ending");

		return mapping.findForward(MessageConstants.UPDATE_SUBJECT);
	}

	public ActionForward getpathdownload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getpathdownload Starting");
		try {
			
			String docPath = request.getParameter("Path");
			
			response.setContentType("application/octet-stream");
			String fileName = "FileName";
			fileName=docPath;
			
			
			response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
			
			
			
			File docFile = new File( getServlet().getServletContext().getRealPath("/")
					+ "FIles\\SYLLABUS"+("/")+docPath);
			
			
		
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
				+ " Control in AddSubjectAction : getpathdownload  Ending");
		return null;
	}
	
	public ActionForward getsyllabusdownload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getsyllabusdownload Starting");
	
		try {
			
			String subjectid = request.getParameter("subjectid");
			
			response.setContentType("application/octet-stream");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String docPath = new AddSubjectDelegate().DdownloadsyllabuspathBD(subjectid,userLoggingsVo);
			
			String fileName = "FileName";
			fileName=docPath;
		
			if(!fileName.equalsIgnoreCase(""))
			
			response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
			
			File docFile = new File( getServlet().getServletContext().getRealPath("/")
					+ "FIles\\SYLLABUS"+("/")+docPath);
		
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
				+ " Control in AddSubjectAction : getsyllabusdownload  Ending");
		return null;
	}
	
	public ActionForward removeMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : removeMessage Starting");
		
		try {
			request.getSession().setAttribute("errormessagediv", null);
			request.getSession().setAttribute("successmessagediv", null);
			response.getWriter().print("Message Removed");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : removeMessage  Ending");
		return null;
	}
	
	public ActionForward downloadsubjectDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : downloadsubjectDetailsXLS  Starting");
		
		try {
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/subjectXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			
			
			List<ViewallSubjectsVo> streamList = new ArrayList<ViewallSubjectsVo>();
			
			streamList =  (List<ViewallSubjectsVo>) request.getSession(false)
					.getAttribute("EXcelDownLoad");
			
			
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					streamList);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/subjectXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Subject Details Excel Report" };
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
					request.getRealPath("Reports/subjectXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=subjectXLSReport.xls");
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
				+ " Control in AddSubjectAction : downloadsubjectDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloadsubjectDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddSubjectAction : downloadsubjectDetailsPDF  Starting");

			try {

				
/*
				List<ViewallSubjectsVo> Details = new ArrayList<ViewallSubjectsVo>();
				Details = new AddSubjectDelegate().subjectdetails();
				*/
				
				
				
				List<ViewallSubjectsVo> streamList = new ArrayList<ViewallSubjectsVo>();
				
				streamList =  (List<ViewallSubjectsVo>) request.getSession(false)
						.getAttribute("EXcelDownLoad");
				
				
				
				
				
				String sourceFileName = request
						.getRealPath("Reports/subjectPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						streamList);

				String PropfilePath = getServlet().getServletContext().getRealPath(
						"")
						+ "\\images\\" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				
				parameters.put("Image", PropfilePath);


				/*parameters.put("Image", clientImage);

				parameters.put("ClientName", ClientName);

				parameters.put("ClientAddress", ClientAddress_l1);*/

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "subjectdetailsPDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();

			}

			catch (Exception e)

			{
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddSubjectAction : downloadsubjectDetailsPDF  Ending");
			return null;

		}
	public ActionForward validateSubNameUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : validateSubNameUpdate: Starting");
              
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			//System.out.println("---------Inside the Action ----");
			String classname = request.getParameter("classId");
			String subjectname = request.getParameter("subject");
			String locationId=request.getParameter("locationId");
			String specialization=request.getParameter("specialization");
			String subjectcode=request.getParameter("subjectcode");
			AddSubjectForm form1= new AddSubjectForm();

			form1.setClassname(classname);
			form1.setSubjtname(subjectname);
			form1.setLocationId(locationId);
			form1.setSpecialization(specialization);
			form1.setCustid(userLoggingsVo.getCustId());
			form1.setSubjectCode(subjectcode);
			String subame_available = new AddSubjectDelegate().validateSubName(form1,userLoggingsVo);
			//System.out.println("subame_available" +subame_available);

			JSONObject object = new JSONObject();
			object.put("des_available", subame_available);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : validateSubNameUpdate: Ending");
		return null;
	}
	public ActionForward getLangauageOnClassBased(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : getLangauageOnClassBased: Starting");
               //System.out.println("whether it is coming inside the action:");
               List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();
          try {
		
        	UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
        	String classname = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			String specialization=request.getParameter("specializationId");
			AddSubjectForm form1= new AddSubjectForm();

			form1.setClassname(classname);
			form1.setLocationId(locationId);
			form1.setSpecialization(specialization);

			
			subjectlist = new AddSubjectDaoImpl().getLangauageOnClassBased(form1,userLoggingsVo);
		

			JSONObject object = new JSONObject();
			object.put("jsonResponse", subjectlist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : getLangauageOnClassBased: Ending");
		return null;
	}
	
	public ActionForward getSubjectByClass(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getSubjectByClass : getSubjectByClass Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			String classId = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			String spec=request.getParameter("specId");
			
			ArrayList<SubjectPojo> subjectlist = new ReportsMenuBD().getSubjectByClass(classId,locationId,userLoggingsVo,spec);
			request.setAttribute("subjectlist", subjectlist);
			
			
			 JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("jsonResponse", subjectlist);
				
				response.getWriter().print(jsonobj);
			
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getSubjectByClass : getSubjectByClass Ending");

		 return null;   
	}
	
	public ActionForward addLabDetails(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getSubjectByClass : getSubjectByClass Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String classId = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			String spec = request.getParameter("specId");
			
			ArrayList<SubjectPojo> subjectlist = new ReportsMenuBD().getSubjectByClass(classId,locationId,userLoggingsVo,spec);
			request.setAttribute("subjectlist", subjectlist);
			
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("jsonResponse", subjectlist);
			response.getWriter().print(jsonobj);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getSubjectByClass : getSubjectByClass Ending");

		 return null;   
	}
	
	@SuppressWarnings("resource")
	  
	public ActionForward addingLabDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction:  addingLabDetails Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String userId = HelperClass.getCurrentUserID(request);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			AddLabVO labvo = new AddLabVO();
			
			labvo.setUserId(userId);
			labvo.setLog_audit_session(log_audit_session);
			labvo.setClassname(request.getParameter("classname"));
			labvo.setDescription(request.getParameter("description"));
			labvo.setLab_name(request.getParameter("lab_name"));
			labvo.setLocationId(request.getParameter("locationId"));
			labvo.setSpecialization(request.getParameter("specialization"));
			labvo.setSubject(request.getParameter("subject"));
			labvo.setSubjectCode(request.getParameter("subjectCode"));
			
		//System.out.println(userId+log_audit_session+userLoggingsVo+request.getParameter("classname")+request.getParameter("description")+request.getParameter("lab_name")+
		//request.getParameter("locationId")+request.getParameter("specialization")+request.getParameter("subject")+request.getParameter("subjectCode"));
			
		 /* FormFile formFile = null;
			String path = null;
			File fileURL = null;
			FileOutputStream fos = null;
			formFile = addsubForm.getFile();
			File secondDir=null;
			File firstDir=null;
			  //firstDir=new File(EcampusPro_Documents_Dir+"/"+"SYLLABUS/");
			path = getServlet().getServletContext().getRealPath("/")+ "FIles\\Lab_SYLLABUS";
			//System.out.println("path is "+path);
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
			
			String subjectname = new AddSubjectDelegate().getSubjectName(sub);
			//System.out.println(sub);
			String subname=subjectname;

			String fileName = subname  + "." + extension;
			//System.out.println("file name printing>>>>>>>>>"+fileName);
			//System.out.println("fileName is "+fileName); */
			
			/*fileURL = new File(path, fileName);
			fos = new FileOutputStream(fileURL);
			fos.write(formFile.getFileData());

			String file1 = fileURL.getPath();

            FileOutputStream fileOutStream =null;
			if(!fileURL.exists()){

				fileURL.mkdir();

				fileOutStream = new FileOutputStream(fileURL);
				fileOutStream.write(formFile.getFileData());
				fileURL.mkdir();
				addsubForm.setFilename(subname+  "." + extension);
                 }
			else{
				fileURL.mkdir();
				fileOutStream = new FileOutputStream(fileURL);
				fileOutStream.write(formFile.getFileData());
				addsubForm.setFilename(subname +  "." + extension);
			}
		*/
			
		  boolean addlab = new AddSubjectDelegate().addLab(labvo,userLoggingsVo);
            String result = "fail";
			if(addlab){
				result="success";
            }
			JSONObject object = new JSONObject();
			object.put("status", result);
			response.getWriter().print(object);

			/*List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();

			String searchTerm = request.getParameter("searchname");
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			obj.setSearchName(searchTerm);
			request.setAttribute("allsubjects", subjectlist);*/


		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : addingLabDetails Ending");

		return null;

	}
	
	public ActionForward DeleteLab(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : DeleteLab Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			userLoggingsVo.setSessionId((String) request.getSession(false).getAttribute("log_audit_session"));
			
			String labList = request.getParameter("lablist");
			String locationList=request.getParameter("locationList");
			String[] idList=labList.split(",");
            
			String reason = request.getParameter("reason");
			String status = request.getParameter("status");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String result = new AddSubjectDelegate().DeleteLab(idList,locationList,reason,status,userLoggingsVo);
			
			JSONObject object = new JSONObject();
			object.put("status", result);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : DeleteLab Ending");

		return null;
	}

	public ActionForward getLabDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getLabDetails Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LABORATORY);
			
            UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String title = "Modify Lab";
			request.setAttribute("title", title);
			
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			
			List<SectionForm> classDetailsIDandClassDetailsNameList = new ArrayList<SectionForm>();
			SectionBD sectionDelegate = new SectionBD();
			classDetailsIDandClassDetailsNameList = sectionDelegate.getCampusClassDetailsIDandClassDetailsNameBD(schoolLocation,userLoggingsVo);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			AddSubjectForm obj = new AddSubjectForm();
			String labcode = request.getParameter("labcode");
			obj.setLab_id(labcode);
			
			String locationid = request.getParameter("location");
			String classname = request.getParameter("classname");
			String specialization=request.getParameter("specialization");
			
			//List<AddLabVO> list = new AddSubjectDelegate().getLabDetails(labcode);
			AddSubjectForm labdetails = new AddSubjectDelegate().getLabDetails(obj,userLoggingsVo);
			request.setAttribute("success", "success");
			request.setAttribute("singlelabdetails", labdetails);
			
			request.setAttribute("locId1", request.getParameter("locId"));
			request.setAttribute("classname1", request.getParameter("classname"));
			request.setAttribute("specId1", request.getParameter("specId"));
			request.setAttribute("status1", request.getParameter("status"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getLabDetails Ending");

		return mapping.findForward(MessageConstants.UPDATE_LAB);
	}

	public ActionForward updateLab(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction:  updateSubject Starting");
		String user = HelperClass.getCurrentUser(request);

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String userId = HelperClass.getCurrentUserID(request);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
            AddLabVO labvo = new AddLabVO();
			
            labvo.setUserId(userId);
			labvo.setLog_audit_session(log_audit_session);
			
			labvo.setClassname(request.getParameter("classname"));
			labvo.setDescription(request.getParameter("description"));
			labvo.setLab_name(request.getParameter("lab_name"));
			labvo.setLocationId(request.getParameter("locationId"));
			labvo.setSpecialization(request.getParameter("specialization"));
			labvo.setSubject(request.getParameter("subject"));
			labvo.setSubjectCode(request.getParameter("subjectCode"));
			labvo.setHiddenlocationid(request.getParameter("hiddenlocationid"));

		
			 boolean labsubject = new AddSubjectDelegate().updateLabDetails(labvo,userLoggingsVo);
                        
			                 if (labsubject == false)
			                    {
								request.setAttribute("errorMessage",
										"Subject Already Exists");
							    } else if(labsubject == true){
								request.setAttribute("successmessagediv",
										"Update Subject Progressing...");
							    }
							    else
							    {
							    	request.setAttribute("errorMessage",
											"Subject not Updated ! Try Again");
							    }


			
			
			
			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();
              
			String searchTerm = request.getParameter("searchname");
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			obj.setSearchName(searchTerm);

			String result = "fail";
			if(labsubject){
				result="success";
            }
			JSONObject object = new JSONObject();
			object.put("status", result);
			response.getWriter().print(object);
        
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}      

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : updateSubject Ending");

		return null;

	}
	
	public ActionForward validateLabNameUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Starting");
               //System.out.println("whether it is coming inside the lab action:");
        
        UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		try {
			
			////System.out.println("<<<<<<<<<<>>>>>>>>>>>---------Inside the Action ----");
			String classname = request.getParameter("classId");
			String subjectname = request.getParameter("subject");
			String locationId=request.getParameter("locationId");
			String lab_name=request.getParameter("lab_name");
			
			AddSubjectForm form1= new AddSubjectForm();

			form1.setClassname(classname);
			form1.setSubjtname(subjectname);
			form1.setLocationId(locationId);
			form1.setLab_name(lab_name);

			String subame_available = new AddSubjectDelegate().validateLabName(form1,userLoggingsVo);
			//System.out.println("subame_available" +subame_available);

			JSONObject object = new JSONObject();
			object.put("des_available", subame_available);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Ending");
		return null;
	}
	
	public ActionForward getpathdownloadOfLab(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getpathdownload Starting");
		try {

			
			String docPath = request.getParameter("Path");
			
			response.setContentType("application/octet-stream");
			String fileName = "FileName";
			fileName=docPath;
			
			response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
			
			File docFile = new File( getServlet().getServletContext().getRealPath("/")
					+ "FIles\\Lab_SYLLABUS"+("/") + docPath);
			
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		} 
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getpathdownload  Ending");
		return null;
	}
	
	public ActionForward getLabsyllabusdownload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getsyllabusdownload Starting");
	
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String subjectid = request.getParameter("labid");
			
			response.setContentType("application/octet-stream");
			
			String docPath = new AddSubjectDelegate().DdownloadLabsyllabuspathBD(subjectid,userLoggingsVo);
			String fileName = "FileName";
			fileName=docPath;
			
			response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
			File docFile = new File( getServlet().getServletContext().getRealPath("/")
					+ "FIles\\Lab_SYLLABUS"+("/")+docPath);
			
			
		
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			
		} 
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getsyllabusdownload  Ending");
		return null;
	}
	
	public ActionForward downloadLabDetailsXLS(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : downloadLabDetailsXLS  Starting");
		
		try {
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/subjectXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			List<ViewallSubjectsVo> streamList = new ArrayList<ViewallSubjectsVo>();
			streamList =  (List<ViewallSubjectsVo>) request.getSession(false)
					.getAttribute("EXcelDownLoad");
			streamList =  (List<ViewallSubjectsVo>) request.getSession(false).getAttribute("EXcelDownLoad");

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					streamList);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(request.getRealPath("Reports/subjectXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Subject Details Excel Report" };
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,	Boolean.FALSE);
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
					request.getRealPath("Reports/subjectXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=subjectXLSReport.xls");
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
				+ " Control in AddSubjectAction : downloadsubjectDetailsXLS   Ending");
				return null;
		
		
	}
public ActionForward downloadLabDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddSubjectAction : downloadsubjectDetailsPDF  Starting");

			try {
				
               /*
				List<ViewallSubjectsVo> Details = new ArrayList<ViewallSubjectsVo>();
				Details = new AddSubjectDelegate().subjectdetails();
				*/
				
				List<ViewallSubjectsVo> streamList = new ArrayList<ViewallSubjectsVo>();
				
				streamList =  (List<ViewallSubjectsVo>) request.getSession(false)
						.getAttribute("EXcelDownLoad");
				
				
				
				String sourceFileName = request
						.getRealPath("Reports/subjectPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						streamList);

				String PropfilePath = getServlet().getServletContext().getRealPath(
						"")
						+ "\\images\\" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				
				parameters.put("Image", PropfilePath);


				/*parameters.put("Image", clientImage);
				parameters.put("ClientName", ClientName);
				parameters.put("ClientAddress", ClientAddress_l1);*/

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "subjectdetailsPDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();

			}

			catch (Exception e)

			{
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddSubjectAction : downloadsubjectDetailsPDF  Ending");
			return null;

		}
	
public ActionForward getSubDetailsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getSubDetailsList Starting");
		
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {

			String message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();
			
			String locationid = request.getParameter("locationid");
			String classname = request.getParameter("classname");
			String specialization=request.getParameter("specialization");
			String status=request.getParameter("status");
			//System.out.println("locationid "+locationid);
			//System.out.println("classname "+classname);
			//System.out.println("specialization "+specialization);
			
			if(locationid==null || locationid.equalsIgnoreCase("") || locationid.equalsIgnoreCase("all")){
				locationid="%%";
			}
		
			if(classname.equalsIgnoreCase("")){
				classname="%%";
			}
			
			if(specialization.equalsIgnoreCase("-")){
				specialization="%%";
			}

			subjectlist = new AddSubjectDelegate().getSubDetailsList(locationid,classname,specialization,userLoggingsVo,status);


			JSONObject obj1= new JSONObject();
			obj1.put("list", subjectlist);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getSubDetailsList Ending");

		return null;
	}

public ActionForward insertSubSyllabus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : insertSubSyllabus  Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			FileOutputStream osf = null;
			FileInputStream is = null;
			String realPath = "";
			String subFoldername = "";
			
			
			AddSubjectForm form1 = (AddSubjectForm)form;
			FormFile file1 = form1.getAddSyllabus();
			
			String locationid = form1.getLocationId();
			String class_name = form1.getClassname();
			String sub_Ids = form1.getSubId();
			String syllabus_Id = form1.getSyllabusId();
			String academic_Year = form1.getAcademicYear();
			String type = form1.getType();
			String specialization = form1.getSpecialization();

			
			if(specialization.equalsIgnoreCase("all")){
				specialization = "-";
			}
			
			String schoolName = form1.getSchool();
			String subjectName = form1.getSub().trim();
			
			//System.out.println(locationid);
			//System.out.println(class_name);
			//System.out.println(sub_Ids);
			//System.out.println(syllabus_Id);
			//System.out.println(academic_Year);
			//System.out.println(type);
			//System.out.println(specialization);
			//System.out.println(schoolName);
			//System.out.println(subjectName);
			
			if(type.equalsIgnoreCase("S")){
				subFoldername="/Subject";
			}
			else if (type.equalsIgnoreCase("L"))
			{
				subFoldername="/Laboratory";
			}
			String academicYear2 = HelperClass.getAcademicYearFace(academic_Year, userLoggingsVo);
			
			final String SAVE_DIR="SCHOOLINFO/"+userLoggingsVo.getDomain()+"/SYLLABUS"+subFoldername;
			
			File file = new File(getServlet().getServletContext().getRealPath("/")+ SAVE_DIR+"/"+academicYear2);
			
			//System.out.println(!file.exists());
			
			if (!file.exists()) {
				
				file.mkdirs();
			}
			
			String classId="";
			String subjectId="";
		 
			try {
					if (file1 != null) {
						
						String extension=null;
						int k = form1.getAddSyllabus().getFileName().lastIndexOf('.');
						
						if (k >= 0) 
						{
							extension = form1.getAddSyllabus().getFileName().substring(k+1);
					    }
						
						String[] sub_Id = sub_Ids.split(",");
						classId = sub_Id[0];
						subjectId = sub_Id[2];
						String classname1 = new AddSubjectDelegate().getClassName(classId,userLoggingsVo);	
			           
			realPath = SAVE_DIR+"/"+academicYear2+"/"+subjectName.replace(' ','_')+classname1.trim()+"."+extension;
			String filePath = getServlet().getServletContext().getRealPath("/")+realPath;
					
				 if (form1.getAddSyllabus().getFileSize() > 0) {
					byte[] btDataFile = form1.getAddSyllabus().getFileData();
					File of = new File(filePath);
					 
					osf = new FileOutputStream(of);
					osf.write(btDataFile);
					osf.flush();
					} 
					else {
						realPath = "";
					}
					form1.setSyllabusFilePath(realPath);
					//System.out.println(realPath);
				  }
			   }
			catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					} 
			 finally {
						if (is != null) {
							is.close();
						}
						if (osf != null) {
							osf.close();
						}
					}
			//System.out.println( "action : "+realPath+ locationid+ class_name+classId+userLoggingsVo);
			String status =  new AddSubjectDelegate().updateSyllabusPath(realPath,locationid,classId,userLoggingsVo,academic_Year,type,specialization,subjectId,syllabus_Id);
			 
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status", status);
			response.getWriter().print(jsonobj);
		} 
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : insertSubSyllabus Ending");
		return  null ;
	}
 public ActionForward syllabusDownload(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException 
   {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getpathdownload Starting");
		try {
			
			String docPath = request.getParameter("syllabus_url");
			String fileName = "";
			
			//System.out.println(docPath);
			
			File docFile = new File(request.getServletContext().getRealPath("/")+docPath);
			
			//System.out.println(docFile.getPath());
			
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
		 } 
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : getpathdownload  Ending");
		return null;
	}
	
 
 
 public ActionForward checkDuplicateSubCodeCount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : checkDuplicateSubCodeCount: Starting");

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			//System.out.println("---------Inside the Action ----");
			String classname = request.getParameter("classId");
			String subjectname = request.getParameter("subject");
			String locationId=request.getParameter("locationId");
			String specialization=request.getParameter("specialization");
			String subjectcode=request.getParameter("subjectcode");
			AddSubjectForm form1= new AddSubjectForm();

			form1.setClassname(classname);
			form1.setSubjtname(subjectname);
			form1.setLocationId(locationId);
			form1.setSpecialization(specialization);
		
			form1.setSubjectCode(subjectcode);
			String subame_available = new AddSubjectDelegate().checkDuplicateSubCount(form1,userLoggingsVo);
			//System.out.println("subame_available" +subame_available);

			JSONObject object = new JSONObject();
			object.put("des_available", subame_available);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : checkDuplicateSubCodeCount: Ending");
		return null;
	}
 
 public ActionForward checkLabCode(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : checkLabCode Starting");
		try{
			
			SubjectPojo pojo = new  SubjectPojo();
			pojo.setLocId(request.getParameter("locId"));
			pojo.setLabCode(request.getParameter("labCode"));
			pojo.setDbdetails((UserLoggingsPojo) request.getSession(false).getAttribute("token_information"));
			String status = new AddSubjectDelegate().checkLabCode(pojo);
			
			JSONObject json = new JSONObject();
			json.put("status",status);
			response.getWriter().print(json);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectAction : checkLabCode Ending");
		return null;
 }
 
}
