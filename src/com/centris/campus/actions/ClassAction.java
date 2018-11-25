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
import java.util.ResourceBundle;
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
import org.json.JSONObject;
import com.centris.campus.daoImpl.ClassDaoImpl;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReportMenuVo;

public class ClassAction extends DispatchAction{
	
	private static final Logger logger = Logger
			.getLogger(ClassAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	public ActionForward insertClassAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction: insertClassAction Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String academic = request.getParameter("adddetails");
			ClassPojo classStreamForm = new ClassPojo();
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String streamId = request.getParameter("stream");
			String classID=request.getParameter("className");
			String locationId=request.getParameter("locationId");
			String secDetailsName = request.getParameter("className");
			String createUser = HelperClass.getCurrentUserID(request);
			String hiddenStreamId = request.getParameter("hiddenStreamId");	
			classStreamForm.setLocationId(locationId);
			classStreamForm.setStreamId(streamId);
			classStreamForm.setCreateUser(createUser);
			classStreamForm.setSecDetailName(HelperClass.className(classID));
			classStreamForm.setClassId(classID);
			classStreamForm.setStatus(request.getParameter("status"));
			classStreamForm.setUpdateClassCode(request.getParameter("updateClassCode"));
			classStreamForm.setLog_audit_session(log_audit_session);

			classStreamForm.setClassName(HelperClass.className(classID));
			
			classStreamForm.setHiddenStreamId(hiddenStreamId);
			
			ClassBD delegate = new ClassBD();
			
			String status = delegate.createClass(classStreamForm,classID,createUser,userLoggingsVo);
			
			System.out.println(status);
			
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
				+ " Control in ClassAction: insertClassAction Ending");

		return null;

	}
	public ActionForward getLocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : getLocation Starting");
		try {
			
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			String Message = request.getParameter("msg");
			request.setAttribute("successmessagediv", Message);
			
			String title = "Add New Class";
			request.setAttribute("title", title);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(dbdetails);
			request.setAttribute("AccYearList", accYearList);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : getLocation Ending");

		return mapping.findForward(MessageConstants.ADD_CLASS);
	}
	public ActionForward getSchoolforClassDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : getSchoolforClassDetails Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			//request.setAttribute("AccYearList", accYearList);
			
			JSONObject obj=new JSONObject();
			obj.put("locationList", locationList);
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : getSchoolforClassDetails Ending");

		return null;
	}
	public ActionForward getStream(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : getStream Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			
			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream(userLoggingsVo);
			//request.setAttribute("AccYearList", accYearList);
			
			JSONObject obj=new JSONObject();
			obj.put("streamList", streamList);
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : getStream Ending");

		return null;
	}
	public ActionForward addClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : addClass Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CLASSDETAILS);
			
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String Message = request.getParameter("msg");
			request.setAttribute("successmessagediv", Message);
			
			String title = "New Class";
			request.setAttribute("title", title);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);
			request.setAttribute("locationList", locationList);

			/*ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(dbdetails);
			request.setAttribute("AccYearList", accYearList);*/
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : addClass Ending");

		return mapping.findForward(MessageConstants.ADD_CLASS);
	}
	
	public ActionForward getStreamDetailAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction :getStreamDetailAction Starting");
		String school=request.getParameter("school");
	
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			List<ClassPojo> streamList = new ArrayList<ClassPojo>();
			ClassBD delegate = new ClassBD();
			streamList = delegate.getStreamDetailBD(school,userLoggingsVo);

		
			JSONObject jsonObject = new JSONObject(streamList);
			jsonObject.accumulate("streamList", streamList);
			response.getWriter().print(jsonObject);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : getStreamDetailAction Ending");

		return null;
	}
	
	public ActionForward classNameCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : classNameCheck  Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			
			System.out.println(request.getParameter("className"));
			
			ClassPojo classPojo = new ClassPojo();
			classPojo.setLocationId(request.getParameter("school"));
			classPojo.setStreamId(request.getParameter("stream"));
			classPojo.setClassName(HelperClass.className(request.getParameter("className")));
			classPojo.setUpdateClassCode(request.getParameter("updateClassCode"));

			String status = new ClassBD().classNameCheck(classPojo,userLoggingsVo);

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
				+ " Control in ClassAction : classNameCheck  Ending");
		return null;

	}
	
	public ActionForward checkUpdateClassName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : checkUpdateClassName  Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			ClassPojo classPojo = new ClassPojo();

			classPojo.setStreamId(request.getParameter("stream"));
			classPojo.setClassName(request.getParameter("className"));
			classPojo.setUpdateClassCode(request.getParameter("updateClassCode"));

			String status = new ClassBD().updateclassNameCheck(classPojo,userLoggingsVo);

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
				+ " Control in ClassAction : checkUpdateClassName  Ending");
		return null;

	}
	
	public ActionForward editClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : editClass  Starting");
		try {
			
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String title = "Modify Class";
			request.setAttribute("title", title);
			String locId=request.getParameter("locId");
			
			String streamId=request.getParameter("streamId");
			String status=request.getParameter("status");
			
			ClassPojo editClasslist = new ClassBD().editClass(request.getParameter("classCode"),locId,dbdetails);
			
			System.out.println(editClasslist.getClassName());
			
			List<ClassPojo> list=new ArrayList<ClassPojo>();
			list.add(editClasslist);
			request.setAttribute("editClasslist", list);
			
			JSONObject obj=new JSONObject();
			obj.put("list", list);
			response.getWriter().print(obj);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(dbdetails);
			request.setAttribute("AccYearList", accYearList);
			
			request.setAttribute("locId", locId);
			request.setAttribute("streamId", streamId);
			request.setAttribute("status", status);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : editClass  Ending");
		return null;

	}
	
	public ActionForward deleteClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : deleteClass  Starting");
		try {
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			String classIds[]=request.getParameterValues("classIds[]");
			String locid[]=request.getParameterValues("locid[]");
			String inactivereason=request.getParameter("inactivereason");
			String activereason=request.getParameter("activereason");
			String otherreason=request.getParameter("otherreason");
			String status=request.getParameter("status");
			
			ClassPojo classPojo = new ClassPojo();
			classPojo.setClassIds(classIds);
			classPojo.setLocIds(locid);
			classPojo.setInactiveReason(inactivereason);
			classPojo.setActiveReason(activereason);
			classPojo.setOtherReason(otherreason);
			classPojo.setStatus(status);
			classPojo.setLog_audit_session(log_audit_session);

			classPojo.setCustid(userLoggingsVo);

			
			boolean value = new ClassBD().deleteClass(classPojo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status", value);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : deleteClass  Ending");
		return null;
	}
	
	public ActionForward updateClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : updateClass  Starting");
		try {

			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			ClassPojo classPojo = new ClassPojo();

			String streamId = request.getParameter("stream");
			String secDetailsName = request.getParameter("className");
			String createUser = HelperClass.getCurrentUserID(request);
			classPojo.setStreamId(streamId);
			classPojo.setCreateUser(createUser);
			classPojo.setSecDetailName(secDetailsName);
			//classPojo.setClassId(classId);

			boolean status = new ClassBD()
					.updateClass(classPojo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : updateClass  Ending");
		return null;

	}
	
	public ActionForward searchClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : searchClass  Starting");

		String usertype = null;
		List<ClassPojo> classList = null;
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ClassBD bd = new ClassBD();
			usertype = HelperClass.getCurrentUser(request);
			
			String searchTextVal = request.getParameter("searchText");

			if (searchTextVal == null || searchTextVal == "") {
				classList = bd.getClassDetails("%%",userLoggingsVo);
			} else {

				classList = bd.searchClassDetails(searchTextVal,userLoggingsVo);
				
			}

			request.setAttribute("classList", classList);
			request.getSession(false).setAttribute("classList",
					classList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : searchClass Ending");

		return mapping.findForward(MessageConstants.CLASS_LIST);

	}

	public ActionForward classPathDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : classPathDetailsXLS  Starting");
		
		try {
		
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/ClassDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			
			/*List<ClassPojo> classList = new ArrayList<ClassPojo>();
			
			
			classList = (List<ClassPojo>) request.getSession(false)
					.getAttribute("EXcelDownLoad");*/
			
			
			
			List<ClassPojo> classList = new ArrayList<ClassPojo>();
			
			ClassBD delegate = new ClassBD();
			

			
			String searchTextVal = request.getParameter("searchTerm");
			
			
			
			if(searchTextVal != null){
				
				
				classList = delegate.searchClassDetails(searchTextVal,userLoggingsVo);
				request.setAttribute("searchnamelist", searchTextVal);
				
			}
			else{
				
				classList = delegate.getClassDetails("%%",userLoggingsVo);
				
			}
			
			
			
			
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(classList);
			
			
			
			
			
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/ClassDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Class Details Excel Report" };
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
					request.getRealPath("Reports/ClassDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=ClassDetailsXLSReport.xls");
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
				+ " Control in ClassAction : classPathDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward classPathDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassAction : classPathDetailsPDFReport  Starting");

			try {

				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
				
				List<ClassPojo> classList = new ArrayList<ClassPojo>();
				
				ClassBD delegate = new ClassBD();
				
				String searchTextVal = request.getParameter("searchTerm");
				
				if(searchTextVal != null){
					
					classList = delegate.searchClassDetails(searchTextVal,userLoggingsVo);
					request.setAttribute("searchnamelist", searchTextVal);
					
				}
				else{
					
					classList = delegate.getClassDetails("%%",userLoggingsVo);
					
				}
				
				String sourceFileName = request
						.getRealPath("Reports/ClassDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						classList);

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
						+ "ClassDetailsPDF - " + ".pdf\"");

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
					+ " Control in ClassAction : classPathDetailsPDFReport  Ending");
			return null;
		}
	
	public ActionForward validateDuplicateLocation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassAction : classNameCheck  Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			System.out.println(request.getParameter("className"));
			
			ClassPojo classPojo = new ClassPojo();
			classPojo.setLocationId(request.getParameter("locationId"));
			classPojo.setStreamId(request.getParameter("streamname"));
			classPojo.setClassName(HelperClass.className(request.getParameter("className")));
			classPojo.setUpdateClassCode(request.getParameter("updateClassCode"));
			classPojo.setCustid(userLoggingsVo);
			String status = new ClassDaoImpl().validateDuplicateLocation(classPojo,userLoggingsVo);

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
				+ " Control in ClassAction : classNameCheck  Ending");
		return null;

	}

}
