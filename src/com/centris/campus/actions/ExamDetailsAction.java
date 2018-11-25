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

import com.alibaba.fastjson.JSONArray;
import com.centris.campus.delegate.CreateExaminationBD;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.forms.CreateExaminationForm;
import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.UserDetailVO;

public class ExamDetailsAction extends DispatchAction {

	private static final Logger logger = Logger
			.getLogger(StreamDetailsAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	public ActionForward createExamAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createExamAction Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			CreateExaminationForm examform = new CreateExaminationForm();

			String createUser = HelperClass.getCurrentUser(request);

			String examid = request.getParameter("examid").trim();
			String eamName = request.getParameter("examname");
			String examdate = request.getParameter("examdate");
			String endDate = request.getParameter("enddate");
			String accadamicyear = request.getParameter("accadamicyear");
			String description = request.getParameter("description");

			examform.setExamId(examid);
			examform.setExamname(eamName);
			examform.setExamdate(examdate);
			examform.setEnddate(endDate);
			examform.setAccyear(accadamicyear);
			examform.setDescription(description);
			examform.setCreateUser(createUser);

			String result = new CreateExaminationBD()
					.createExamination(examform);

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
				+ " Control in AdminMenuAction :createExamAction Ending");

		return null;
	}

	public ActionForward editExamAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : editExam Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String examid = request.getParameter("examid");

			ExaminationDetailsVo examvo = new ExaminationDetailsVo();

			examvo.setExamid(examid);

			ExaminationDetailsVo result = new CreateExaminationBD()
					.editExamination(examvo);

			CreateExaminationForm examform = new CreateExaminationForm();
			List<Object> examnameslist = null;
			String accyear = examform.getAccyear();

			examnameslist = new CreateExaminationBD().getAllExamNames(examform,custdetails);

			Map<String, String> map = (Map<String, String>) new CreateExaminationBD()
					.getAccadamicYearsBD(custdetails);

			request.setAttribute("ALLACCYEARS", map);
			request.setAttribute("examlist", result);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : editExam Ending");

		return mapping.findForward(MessageConstants.EXAM_CREATION);

	}

	public ActionForward getExam(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getExam Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getExam Ending");

		return mapping.findForward(MessageConstants.EXAM_CREATION);

	}

	public ActionForward deleteExamAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : deleteExam Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			String examid = request.getParameter("examid");

			ExaminationDetailsVo examvo = new ExaminationDetailsVo();

			examvo.setExamid(examid);

			String check = new CreateExaminationBD().deleteExamination(examvo);

			request.setAttribute("delete", check);
			JSONObject json = new JSONObject();

			json.put("status", check);

			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : deleteExam Ending");

		return null;

	}

	public ActionForward searchexamdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchExam Starting");

		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String SearchName = request.getParameter("searchname");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			ArrayList<ExaminationDetailsVo> examlist = new CreateExaminationBD()
					.searchExamination(SearchName,custdetails);

			request.setAttribute("examDetailsList", examlist);
			request.setAttribute("searchname", SearchName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchExam Ending");

		return mapping.findForward(MessageConstants.EXAM_LIST);

	}

	public ActionForward validateExamName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : validateExam Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			String examid = request.getParameter("examid");
			String examname = request.getParameter("examname");
			String accyear = request.getParameter("accyear");

			ExaminationDetailsVo examvo = new ExaminationDetailsVo();

			examvo.setExamid(examid);
			examvo.setExamName(examname);
			examvo.setAccyear(accyear);

			boolean examname_Available = new CreateExaminationBD()
					.validateExamination(examvo);

			JSONObject jsonobject = new JSONObject();
			if (examname_Available) {

				jsonobject.put("status", "true");
			} else {
				jsonobject.put("status", "false");
			}
			response.getWriter().print(jsonobject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : validateEnding");
		return null;

	}

	public ActionForward downloadExamDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : downloadExamDetailsXLS Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			//("download exam action");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/ExamDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			/*
			 * List<ExaminationDetailsVo> examList = new
			 * ArrayList<ExaminationDetailsVo>(); examList =
			 * (List<ExaminationDetailsVo>) request.getSession(false)
			 * .getAttribute("EXcelDownLoad");
			 */

			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
						.searchExamination(searchTerm,userLoggingsVo);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate(userLoggingsVo);
				//("listing is working");
			}

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					examvo);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/ExamDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Exam Details Excel Report" };
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
					request.getRealPath("Reports/ExamDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=ExamDetailsXLSReport.xls");
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
				+ " Control in ExamDetailsAction : downloadExamDetailsXLS");
		return null;

	}

	public ActionForward downloadExamDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : downloadExamDetailsPDF  Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			//("downloading pdf");

		/*	List<ExaminationDetailsVo> examList = new ArrayList<ExaminationDetailsVo>();
			examList = (List<ExaminationDetailsVo>) request.getSession(false)
					.getAttribute("EXcelDownLoad");*/
			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
						.searchExamination(searchTerm,userLoggingsVo);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate(userLoggingsVo);
				//("listing is working");
			}


			String sourceFileName = request
					.getRealPath("Reports/ExamDetailsPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					examvo);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();

			parameters.put("Image", PropfilePath);

			/*
			 * parameters.put("Image", clientImage);
			 * 
			 * parameters.put("ClientName", ClientName);
			 * 
			 * parameters.put("ClientAddress", ClientAddress_l1);
			 */

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "ExamDetailsPDF - " + ".pdf\"");

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
				+ " Control in ExamDetailsAction : downloadExamDetailsPDF  Ending");
		return null;

	}

	public ActionForward addGradeSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ExamDetailsAction : addGradeSettings  Starting");

		 try{
			 
			 	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			 
			 	request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_EXAM);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_EXAM);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_EXAM_GRADESETTINGS);
				
				String myValues = request.getParameter("myValue");
				
				String accyear = myValues.split(",")[0];
				String location = myValues.split(",")[1];
				
				String accyname =new ExamDetailsBD().getaccyName(accyear,custdetails);
				String currentlocation =new ExamDetailsBD().getlocationname(location,custdetails);
				
				ArrayList<ExamDetailsPojo> list = new ArrayList<ExamDetailsPojo>();
				list = new ExamDetailsBD().displayGradeSettings(accyear,location,custdetails);
				request.setAttribute("gradeSettingsList",list);
				
				request.setAttribute("accyName",accyname);
				request.setAttribute("locName", currentlocation);
				request.setAttribute("accyId",accyear);
				request.setAttribute("locId",location);
				
				request.setAttribute("historyacademicId",myValues.split(",")[0]);
				request.setAttribute("historylocId",myValues.split(",")[1]);
				
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ExamDetailsAction : addGradeSettings  Ending");
		 return mapping.findForward(MessageConstants.ADDGRADESETTINGS);
	 }
	public ActionForward gradeSettingsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ExamDetailsAction : downloadExamDetailsPDF  Starting");

		 try{
			 UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			 
			 request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_EXAM);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_EXAM);
				String accyear = request.getParameter("accyear");
				String location = request.getParameter("location");
				String accyname =new ExamDetailsBD().getaccyName(accyear,custdetails);
				String currentlocation =new ExamDetailsBD().getlocationname(location,custdetails);
				
				ArrayList<ExamDetailsPojo> list = new ArrayList<ExamDetailsPojo>();
				list = new ExamDetailsBD().displayGradeSettings(accyear,location,custdetails);
				
				JSONObject json = new JSONObject();
				json.put("gradeSettingsList",list);
				response.getWriter().print(json);
				
				request.setAttribute("accyName",accyname);
				request.setAttribute("locName", currentlocation);
				request.setAttribute("accyId",accyear);
				request.setAttribute("locId",location);
				
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 return null;
	 }
	public ActionForward insertGradeSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : insertGradeSettings  Starting");
		UserDetailVO userDetailVO = (UserDetailVO) request
				.getSession(false).getAttribute("UserDetails");

		 UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		String userCode = userDetailVO.getUserId();
		String location = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String grade = request.getParameter("grade");
		String comments = request.getParameter("comments");
		String minmarks = request.getParameter("minmarks");
		String maxmarks = request.getParameter("maxmarks");
		
		ExamDetailsPojo obj = new ExamDetailsPojo();
		obj.setGradename(grade);
		obj.setComments(comments);
		obj.setMin_marks(minmarks);
		obj.setMax_marks(maxmarks);
		obj.setCreatedBy(userCode);
		obj.setAccyearId(accyear);
		obj.setLocid(location);
		obj.setLog_audit_session(log_audit_session);
		obj.setCustid(custdetails);
		String status = new ExamDetailsBD().insertGradeSettings(obj);
		/*ArrayList<ExamDetailsPojo> list = new ExamDetailsBD().insertGradeSettings(obj);*/
		
		JSONObject json = new JSONObject();
		json.put("status",status);
		response.getWriter().print(json);
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : insertGradeSettings  Ending");
	return null;
	}
	
	public ActionForward deleteGradeSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : deleteGradeSettings  Starting");
		ExamDetailsPojo obj = new ExamDetailsPojo();
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		obj.setLog_audit_session(log_audit_session);
		obj.setCustid(custdetails);
		String gradeid = request.getParameter("gradeid");
		String location = request.getParameter("loaction");
		String accyear = request.getParameter("accyear");
		
		String result = new ExamDetailsBD().deleteGradeSettings(gradeid,location,accyear,obj);
		JSONObject json = new JSONObject();
		json.put("status",result);
		response.getWriter().print(json);
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : deleteGradeSettings  Ending");
		return null;
	}
	public ActionForward editGradeSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : editGradeSettings  Starting");
		try{
		    UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			String gradeid = request.getParameter("editid");
			String gradename = request.getParameter("gradename");
			String comments = request.getParameter("comments");
			String min_marks = request.getParameter("min_marks");
			String max_marks = request.getParameter("max_marks");
			String location = request.getParameter("loaction");
			String accyear = request.getParameter("accyear");
			
			ExaminationDetailsVo list = new ExaminationDetailsVo();
			list.setGradeid(gradeid);
			list.setGradename(gradename);
			list.setComments(comments);
			list.setMax_marks(max_marks);
			list.setMin_marks(min_marks);
			list.setLocationid(location);
			list.setAccyear(accyear);
			list.setLog_audit_session(log_audit_session);
			list.setCustid(custdetails.getCustId());
			String msg = new ExamDetailsBD().editGradeSettings(list,custdetails);
			
			JSONObject json = new JSONObject();
			json.put("status",msg);
			response.getWriter().print(json);
		}catch(Exception e){
			e.printStackTrace();
		}
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : editGradeSettings  Ending");
		 return null;
	}
	public ActionForward checkduplicateGrade(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : checkduplicateGrade  Starting");
		try{
			 UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accyear = request.getParameter("accyear");
			String gradename = request.getParameter("gradename");
			String loc = request.getParameter("loc");
			
			
			String result= new ExamDetailsBD().checkduplicateGrade(accyear,gradename,loc,custdetails);
			
			//(result);
			
			JSONObject json = new JSONObject();
			json.put("status",result);
			response.getWriter().print(json);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : checkduplicateGrade  Ending");
		return null;
	}
	
	public ActionForward getSubjectmarksStatus(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getSubjectmarksStatus  Starting");
		
		try{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSUBJECTWISE);

			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   //("current school Location:" +schoolLocation);
	   		      if(schoolLocation.equalsIgnoreCase("all")){
	   			   schoolLocation="%%";
	   		}
	   		   UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	   		
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			String accyear = request.getParameter("accyear");
			String locname = request.getParameter("hschoolLocation");
			
			String accyname =new ExamDetailsBD().getaccyName(accyear,custdetails);
			request.setAttribute("accyName",accyname);
			request.setAttribute("accyId",accyear);
			
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getSubjectmarksList(accyear,locname,custdetails);
			request.setAttribute("subjectmarksList",list);
			
			String currentlocation =new ExamDetailsBD().getlocationname(locname,custdetails);
			request.setAttribute("currentlocation", currentlocation);
			request.setAttribute("locId",locname);
			
			request.setAttribute("historyacayear", request.getParameter("historyacayear"));
			request.setAttribute("historylocation",request.getParameter("historylocation"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getSubjectmarksStatus  Ending");
		
		 return mapping.findForward("subjectwisemarksList");
	}
	public ActionForward getSubjectClass(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getSubjectClass  Starting");
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSUBJECTWISE);
		try{
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   //("current school Location:" +schoolLocation);
	   		      if(schoolLocation.equalsIgnoreCase("all")){
	   			   schoolLocation="%%";
	   		}

	   		   UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	   		
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			
			String accyear = request.getParameter("accyear");
			String examid = request.getParameter("examid");
			String classid = request.getParameter("classid");
			//("examid from action:" +examid);
			//("Accyear from action:" +accyear);
			String locid=request.getParameter("hschoolLocation");
			String accyname =new ExamDetailsBD().getaccyName(accyear,custdetails);
			request.setAttribute("accyName",accyname);
			request.setAttribute("accyId",accyear);
			
			String examname = new ExamDetailsBD().getexamName(examid,accyear,locid,custdetails);
			
			String examarray[];
			String examName = "";
			String examCode = "";
			if(examname !="" && examname !=null){
				 examarray = examname.split(",");
				 examName = examarray[0];
				 examCode = examarray[1];
			}
			
			
			request.setAttribute("examName",examName);
			request.setAttribute("examCode",examCode);
			request.setAttribute("examid",examid);
			
			ArrayList<ExaminationDetailsVo> list = new ExamDetailsBD().getsubjectstudent(accyear,examid,locid,custdetails,classid);
			request.setAttribute("subjectClassList",list);


			 String currentlocation =new ExamDetailsBD().getlocationname(locid,custdetails);
			 request.setAttribute("currentlocation", currentlocation);
			 request.setAttribute("locid", locid);
			 
			 request.setAttribute("historyacayear", request.getParameter("historyacayear"));
			 request.setAttribute("historylocation",request.getParameter("historylocation"));
			
			/*JSONObject json = new JSONObject();
			json.put("subjectExamList",list);
			response.getWriter().print(json);*/
		}catch(Exception e){
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getSubjectClass  Ending");
		
		 return mapping.findForward("getSubjectClass");
	}
	
	public ActionForward classWiseSubject(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : classWiseSubject  Starting");
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSUBJECTWISE);
		try{
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   //("current school Location:" +schoolLocation);
	   		      if(schoolLocation.equalsIgnoreCase("all")){
	   			   schoolLocation="%%";
	   		}
			
			String accyear = request.getParameter("accyear");
			String examid = request.getParameter("examid");
			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			String location = request.getParameter("hschoolLocation");
			String specId = request.getParameter("specId");
			
			ExaminationDetailsVo obj = new ExaminationDetailsVo();
			obj.setAccyearid(accyear);
			obj.setExamid(examid);
			obj.setSection(sectionId);
			obj.setClassId(classId);
			obj.setLocationid(location);
			obj.setCustid(userLoggingsVo.getCustId());
			ArrayList<ExaminationDetailsVo>  list = new ExamDetailsBD().getexamclassDetails(obj,userLoggingsVo);
			request.setAttribute("examid",examid);
			request.setAttribute("classId",classId);
			request.setAttribute("sectionId",sectionId);
			request.setAttribute("accyear",accyear);
			request.setAttribute("accyName",list.get(0).getAccyear());
			request.setAttribute("classname",list.get(0).getClassname());
			request.setAttribute("sectionname",list.get(0).getSectionName());
			request.setAttribute("examcode",list.get(0).getExamCode());
			request.setAttribute("examname",list.get(0).getExamName());
			request.setAttribute("startdate",list.get(0).getStartDate());
			request.setAttribute("enddate",list.get(0).getEndDate());
			
			ArrayList<ExaminationDetailsVo> subjectList = new ArrayList<ExaminationDetailsVo>();
			subjectList = new ExamDetailsBD().classWiseSubject(obj,userLoggingsVo);
			request.setAttribute("subjectList",subjectList);
			
			String currentlocation =new ExamDetailsBD().getlocationname(location,userLoggingsVo);
			request.setAttribute("currentlocation", currentlocation);
			request.setAttribute("locid", location);
			request.setAttribute("specId", specId);
			
			request.setAttribute("historyacayear", request.getParameter("historyacayear"));
			 request.setAttribute("historylocation",request.getParameter("historylocation"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : classWiseSubject  Ending");
		 return mapping.findForward("classWiseSubject");
	}

	public ActionForward savesubWiseMarksDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : savesubWiseMarksDetails  Starting");
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_EXAM_EXAMMARKSSUBJECTWISE);
		try{
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   //("current school Location:" +schoolLocation);
	   		      if(schoolLocation.equalsIgnoreCase("all")){
	   			   schoolLocation="%%";
	   		}
	   		
			String accyear = request.getParameter("accyear");
			String examid = request.getParameter("examid");
			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			String subid = request.getParameter("subid");
			String specid = request.getParameter("spec");
			String location = request.getParameter("hschoolLocation");
			 String currentlocation =new ExamDetailsBD().getlocationname(location,userLoggingsVo);
				request.setAttribute("currentlocation", currentlocation);
				request.setAttribute("locationid", location);
			ExaminationDetailsVo obj = new ExaminationDetailsVo();
			
			obj.setAccyearid(accyear);
			obj.setExamid(examid);
			obj.setSection(sectionId);
			obj.setClassId(classId);
			obj.setSubId(subid);
			obj.setLocationid(location);
			obj.setCustid(userLoggingsVo.getCustId());
			obj.setSpecializationId(specid);
			ArrayList<ExaminationDetailsVo>  list = new ExamDetailsBD().getexamclassDetails(obj,userLoggingsVo);
			request.setAttribute("examid",examid);
			request.setAttribute("classId",classId);
			request.setAttribute("sectionId",sectionId);
			request.setAttribute("accyear",accyear);
			request.setAttribute("subid",subid);
			request.setAttribute("accyName",list.get(0).getAccyear());
			request.setAttribute("classname",list.get(0).getClassname());
			request.setAttribute("sectionname",list.get(0).getSectionName());
			request.setAttribute("examcode",list.get(0).getExamCode());
			request.setAttribute("examname",list.get(0).getExamName());
			request.setAttribute("startdate",list.get(0).getStartDate());
			request.setAttribute("enddate",list.get(0).getEndDate());
			request.setAttribute("examtypeprefix", list.get(0).getExamtypeprefix());
			String examtypeprefix=(String) request.getAttribute("examtypeprefix");
			//(examtypeprefix);
			String  result = new ExamDetailsBD().getsubDetails(obj,userLoggingsVo);
			String arr[]=result.split(",");
			request.setAttribute("subjectName",arr[0]);
			request.setAttribute("subjectCode",arr[1]);
			request.setAttribute("total_marks",arr[2]);
			request.setAttribute("passmarks",arr[3]); 
			request.setAttribute("specid",specid); 
			request.setAttribute("examtypeprefix",examtypeprefix); 
			
			request.setAttribute("historyacayear", request.getParameter("historyacayear"));
			request.setAttribute("historylocation",request.getParameter("historylocation"));
			
			ArrayList<ExaminationDetailsVo> studentlist = new ExamDetailsBD().getstudentsList(obj,location,userLoggingsVo);
			
			if(studentlist.size()!=0){
				request.setAttribute("studentname", studentlist.get(0).getStudentname());
				request.setAttribute("admissionno", studentlist.get(0).getAddmisiionno());
				request.setAttribute("studentlist",studentlist);
			}else{
				request.setAttribute("size","nodata");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : savesubWiseMarksDetails  Ending");
		 return mapping.findForward("savesubWiseMarksDetails");
	}
	public ActionForward InsertMarkEntrySubjectWise(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
            
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			//("current school Location:" +schoolLocation);
			if(schoolLocation.equalsIgnoreCase("all")){
				schoolLocation="%%";
			}



			String studentids[] = request.getParameterValues("studentids[]");
			String statusvalues[] = request.getParameterValues("statusvalues[]");
			String scoredmarks[] = request.getParameterValues("scoredmarks[]");
			String accyear = request.getParameter("accyear");
			String examid = request.getParameter("examid");
			String classId = request.getParameter("classId");
			String primaryid[] = request.getParameterValues("primaryid[]");
			String location = request.getParameter("location");
			//("classid from action:" +classId);
			String sectionId = request.getParameter("sectionId");
			String hiddensubid = request.getParameter("hiddensubid");
			
			//(scoredmarks.length);
			
			
			ExaminationDetailsVo obj = new ExaminationDetailsVo();
			obj.setClassid(classId);
			obj.setAccyear(accyear);
			obj.setExamid(examid);
			obj.setSectionid(sectionId);
			obj.setStudentids(studentids);
			obj.setStatusvalues(statusvalues);
			obj.setSubId(hiddensubid);
			obj.setScoremarks(scoredmarks);
			obj.setSubmarksid(primaryid);
			//("Lenght from action:" +primaryid.length);
			String result=null;
			/*for(int i=0;i<primaryid.length;i++){
				if(primaryid[i].equals("")){
					 result=new ExamDetailsBD().insertmarkentrysubjectwise(obj,schoolLocation);
					
				}else{
					 result=new ExamDetailsBD().updatemarkentrysubjectwise(obj,schoolLocation);
					
				}
			}*/
			
			result=new ExamDetailsBD().insertmarkentrysubjectwise(obj,location);
			JSONObject object = new JSONObject();
			object.put("status",result);
			response.getWriter().print(object);



		return null;
	}
	public ActionForward gradeList(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try{
			
			String accyear = request.getParameter("accyear");
			String location = request.getParameter("location");
			 UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			 
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatusGrade(accyear,location,pojo);

			JSONObject obj=new JSONObject();
			obj.put("accYearList",accYearList);
			response.getWriter().print(obj);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
public ActionForward getexamsettingslist(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		   logger.setLevel(Level.DEBUG);
	   		JLogger.log(0, JDate.getTimeString(new Date())
	   				+ MessageConstants.START_POINT);
	   		logger.info(JDate.getTimeString(new Date())
	   				+ " Control in ExamDetailsAction : getexamsettingslist  Starting");
	   		   UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		try{
			
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");
			ArrayList<ExaminationDetailsVo> examlist= new ExamDetailsBD().getexamsettingslist(accyear,locid,custdetails);
			JSONObject obj=new JSONObject();
			obj.put("examlist",examlist);
			response.getWriter().print(obj);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
   		JLogger.log(0, JDate.getTimeString(new Date())
   				+ MessageConstants.START_POINT);
   		logger.info(JDate.getTimeString(new Date())
   				+ " Control in ExamDetailsAction : getexamsettingslist Ending");
		return null;
	}

public ActionForward getexamsettingslistfordep(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try{
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");
		
			ArrayList<ExaminationDetailsVo> examlist= new ExamDetailsBD().getexamsettingslistfordep(accyear,locid);
			//(examlist.size());
			JSONObject obj=new JSONObject();
			obj.put("examlist",examlist);
			response.getWriter().print(obj);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

// Max.Marks SetUp
public ActionForward getMaxMarksSetUp(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : getMaxMarksSetUp  Starting");
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_REPORTCARD);

		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		String accYear=(String) request.getSession(false).getAttribute("current_academicYear");

		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
		request.setAttribute("locationDetailsList", locationList);

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
		request.setAttribute("accYearList", accYearList);

		request.setAttribute("curr_accy", accYear);
		request.setAttribute("curr_loc", schoolLocation);

	}catch (Exception e) {
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : getMaxMarksSetUp Ending");
	return mapping.findForward(MessageConstants.DISPLAY_MAX_MARKS_SET_UP);
}

public ActionForward classMaxMarksSetUp(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : classMaxMarksSetUp  Starting");
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_REPORTCARD);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String myValues = request.getParameter("myValue");
		String accyear = myValues.split(",")[0];
		String locid = myValues.split(",")[1];

		String currentlocation = new ExamDetailsBD().getlocationname(locid,custdetails);
		request.setAttribute("location", currentlocation);
		String accyname = new ExamDetailsBD().getaccyName(accyear,custdetails);
		request.setAttribute("accyName", accyname);

		request.setAttribute("accyearid", accyear);
		request.setAttribute("locid", locid);
		
	}catch (Exception e){
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : classMaxMarksSetUp Ending");
	
	return mapping.findForward(MessageConstants.DISPLAY_CLASS_MAX_MARKS_SET_UP);
}
public ActionForward subMaxMarksSetUp(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : subMaxMarksSetUp  Starting");
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_REPORTCARD);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String myValue=request.getParameter("myValue").toString();
		String accyear=myValue.split(",")[0];
		String location=myValue.split(",")[1];
		String classId=myValue.split(",")[2];
		String markid=myValue.split(",")[3];
		String periodicmark=myValue.split(",")[4];
		String notemark=myValue.split(",")[5];
		String subenrichmark=myValue.split(",")[6];
		
		String currentlocation = new ExamDetailsBD().getlocationname(location,custdetails);
		request.setAttribute("location", currentlocation);
		String accyname = new ExamDetailsBD().getaccyName(accyear,custdetails);
		request.setAttribute("accyName", accyname);
		String classname=HelperClass.getClassName(classId, location, custdetails);
		request.setAttribute("classname", classname);
		
		request.setAttribute("accyearid", accyear);
		request.setAttribute("locid", location);
		request.setAttribute("classId", classId);
		request.setAttribute("markid", markid);
		request.setAttribute("periodicmark", periodicmark);
		request.setAttribute("notemark", notemark);
		request.setAttribute("subenrichmark", subenrichmark);
	}catch (Exception e){
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : subMaxMarksSetUp Ending");
	
	return mapping.findForward(MessageConstants.DISPLAY_SUB_MAX_MARKS_SET_UP);
}

public ActionForward getReportCardList(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
{
	   logger.setLevel(Level.DEBUG);
   		JLogger.log(0, JDate.getTimeString(new Date())
   				+ MessageConstants.START_POINT);
   		logger.info(JDate.getTimeString(new Date())
   				+ " Control in ExamDetailsAction : getReportCardList  Starting");
   		   UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	try{
		
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("location");
		ArrayList<ExaminationDetailsVo> examlist= new ExamDetailsBD().getReportCardSettingList(accyear,locid,custdetails);
		JSONObject obj=new JSONObject();
		obj.put("reportcardsettinglist",examlist);
		response.getWriter().print(obj);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsAction : getReportCardList Ending");
	return null;
}

public ActionForward getSubjectClassList(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : getSubjectClassList  Starting");
	try{

		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");


		String accyear=request.getParameter("accyear");
		String location=request.getParameter("location");
		String classId=request.getParameter("classId");
		String markId=request.getParameter("markId");

		ExaminationDetailsVo obj = new ExaminationDetailsVo();
		obj.setAccyearid(accyear);
		obj.setClassId(classId);
		obj.setLocationid(location);
		obj.setMarkId(markId);

		ArrayList<ExaminationDetailsVo> subjectList = new ArrayList<ExaminationDetailsVo>();
		subjectList = new ExamDetailsBD().classWiseSubjectInMaximumMark(obj,custdetails);

		ArrayList<ExaminationDetailsVo> subjectList1 = new ArrayList<ExaminationDetailsVo>();
		subjectList1 = new ExamDetailsBD().classWiseSubjectWithLabMaximumMark(obj,custdetails);

		JSONObject json=new JSONObject();
		json.put("subjectList",subjectList);
		json.put("subjectListWithPractical",subjectList1);
		response.getWriter().print(json);

	}catch (Exception e) {
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : getSubjectClassList Ending");
	return null;
}

public ActionForward insertMaximumExammarkDetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : insertMaximumExammarkDetails  Starting");
	try{

		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		String accyear=request.getParameter("academiyear");
		String location=request.getParameter("location");
		String classId=request.getParameter("classid");
		String maxlimit=request.getParameter("maxlimit");
		String periodicexam=request.getParameter("periodicexam");
		String notebook=request.getParameter("notebook");
		String subenrichment=request.getParameter("subenrichment");
		String markId=request.getParameter("markId");
		String subjectId=request.getParameter("subjectId").toString();
		String labId=request.getParameter("labId").toString();
		String yearlytheory=request.getParameter("yearlytheory").toString();
		String yearlypractical=request.getParameter("yearlypractical").toString();
		String createUser = HelperClass.getCurrentUserID(request);

		ExaminationDetailsVo obj = new ExaminationDetailsVo();
		obj.setAccyearid(accyear);
		obj.setClassId(classId);
		obj.setLocationid(location);
		obj.setSubId(subjectId);
		obj.setTheoryMarks(yearlytheory);
		obj.setPracticalMarks(yearlypractical);
		obj.setMax_marks(maxlimit);
		obj.setPeriodicmark(periodicexam);
		obj.setCreatedBy(createUser);
		obj.setMarkId(markId);
		obj.setMax_notebook_marks(notebook);
		obj.setMax_subenrich_marks(subenrichment);
		obj.setLabid(labId);

		String status=new ExamDetailsBD().insertMaximumExammarkDetails(obj,custdetails);
		JSONObject json=new JSONObject();
		json.put("status",status);
		response.getWriter().print(json);

	}catch (Exception e) {
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : insertMaximumExammarkDetails Ending");
	return null;
}

public ActionForward termBasedSetUp(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : termBasedSetUp  Starting");
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_REPORTCARD);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String myValue=request.getParameter("termValue").toString();
		String accyear=myValue.split(",")[0];
		String location=myValue.split(",")[1];
		String classId=myValue.split(",")[2];
		String setupId=myValue.split(",")[3];
		String reporttype=myValue.split(",")[4];
		String maxlimit=myValue.split(",")[5];
		
		String currentlocation = new ExamDetailsBD().getlocationname(location,custdetails);
		request.setAttribute("location", currentlocation);
		String accyname = new ExamDetailsBD().getaccyName(accyear,custdetails);
		request.setAttribute("accyName", accyname);
		String classname=HelperClass.getClassName(classId, location, custdetails);
		request.setAttribute("classname", classname);
		
		request.setAttribute("accyearid", accyear);
		request.setAttribute("locid", location);
		request.setAttribute("classId", classId);
		request.setAttribute("setupId", setupId);
		request.setAttribute("reporttype", reporttype);
		request.setAttribute("maxlimit", maxlimit);
	}catch (Exception e){
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : termBasedSetUp Ending");
	
	return mapping.findForward("term_marks_setup");
}

public ActionForward insertReportSetupDetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : insertReportSetupDetails  Starting");
	try{

		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		String accyear=request.getParameter("academiyear");
		String location=request.getParameter("location");
		String classId=request.getParameter("classid");
		String maxlimit=request.getParameter("maxlimit");
		String reporttype=request.getParameter("reporttype");
		
		
		String termname=request.getParameter("termname").toString();
		String periodictestmark=request.getParameter("periodictestmark").toString();
		String periodictestmark1=request.getParameter("periodictestmark1").toString();
		String notebookmark=request.getParameter("notebookmark").toString();
		String subenrichmentmark=request.getParameter("subenrichmentmark").toString();
		String yearlytheorymork=request.getParameter("yearlytheorymork").toString();
		String yearlypractical=request.getParameter("yearlypractical").toString();
		String periodicexam=request.getParameter("periodicexam").toString();
		String periodicexam1=request.getParameter("periodicexam1").toString();
		String halfyearlyexam=request.getParameter("halfyearlyexam").toString();
		String halfyearlymark=request.getParameter("halfyearlymark").toString();
		String finalexam=request.getParameter("finalexam").toString();
		String nonpracticalmark=request.getParameter("nonpracticalmark").toString();
		String setupId=request.getParameter("setupId");
		
		String createUser = HelperClass.getCurrentUserID(request);

		ExaminationDetailsVo obj = new ExaminationDetailsVo();
		obj.setAccyearid(accyear);
		obj.setClassId(classId);
		obj.setLocationid(location);
		obj.setMax_marks(maxlimit);
		obj.setCreatedBy(createUser);
		obj.setSetupId(setupId);
		obj.setReporttype(reporttype);
		
		obj.setTermname(termname);
		obj.setPeriodicmark(periodictestmark);
		obj.setPeriodicmark1(periodictestmark1);
		obj.setNotebookmark(notebookmark);
		obj.setSubenrichmentmark(subenrichmentmark);
		obj.setPeriodicexam(periodicexam);
		obj.setPeriodicexam1(periodicexam1);
		obj.setHalfyearlyexam(halfyearlyexam);
		obj.setFinalexam(finalexam);
		obj.setTheoryMarks(yearlytheorymork);
		obj.setPracticalMarks(yearlypractical);
		obj.setHalfyearlymark(halfyearlymark);
		obj.setNonpracticalmark(nonpracticalmark);

		String status=new ExamDetailsBD().insertReportSetupDetails(obj,custdetails);
		JSONObject json=new JSONObject();
		json.put("status",status);
		response.getWriter().print(json);

	}catch (Exception e) {
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : insertReportSetupDetails Ending");
	return null;
}
public ActionForward getReportSetupDetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : getSubjectClassList  Starting");
	try{

		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		

		String accyear=request.getParameter("accyear");
		String location=request.getParameter("location");
		String classId=request.getParameter("classId");
		String setupId=request.getParameter("setupId");
		String reporttype=request.getParameter("reporttype");

		ExaminationDetailsVo obj = new ExaminationDetailsVo();
		obj.setAccyearid(accyear);
		obj.setClassId(classId);
		obj.setLocationid(location);
		obj.setSetupId(setupId);
		
		JSONObject json=new JSONObject();
		if(reporttype.equalsIgnoreCase("termbased")){
			ExaminationDetailsVo term1details=new ExamDetailsBD().getTerm1ReportSetupDetails(obj,custdetails);
			ExaminationDetailsVo term2details=new ExamDetailsBD().getTerm2ReportSetupDetails(obj,custdetails);
			json.put("term1periodicexam",term1details.getPeriodicexam());
			json.put("term1finalexam",term1details.getFinalexam());
			json.put("term1periodicmark",term1details.getPeriodicmark());
			json.put("term1notebookmark",term1details.getNotebookmark());
			json.put("term1subenrichmentmark",term1details.getSubenrichmentmark());
			json.put("term1theorymark",term1details.getTheoryMarks());
			
			json.put("term2periodicexam",term2details.getPeriodicexam());
			json.put("term2finalexam",term2details.getFinalexam());
			json.put("term2periodicmark",term2details.getPeriodicmark());
			json.put("term2notebookmark",term2details.getNotebookmark());
			json.put("term2subenrichmentmark",term2details.getSubenrichmentmark());
			json.put("term2theorymark",term2details.getTheoryMarks());
		}else{
			ExaminationDetailsVo academicdetails=new ExamDetailsBD().getAcademicReportSetupDetails(obj,custdetails);
			/*vo.setPeriodicexam(rs.getString("periodic_exam1"));
			vo.setPeriodicexam1(rs.getString("periodic_exam2"));
			vo.setHalfyearlyexam(rs.getString("halfyearlyexam"));
			vo.setFinalexam(rs.getString("yearly_exam"));
			vo.setPeriodicmark(rs.getString("periodic_mark1"));
			vo.setPeriodicmark1(rs.getString("periodic_mark2"));
			vo.setTheoryMarks(rs.getString("yearlytheorymark"));
			vo.setHalfyearlymark(rs.getString("halfyearlytheorymark"));
			vo.setPracticalMarks(rs.getString("yearlypracticalmark"));*/
			json.put("academicperiodicexam",academicdetails.getPeriodicexam());
			json.put("academicperiodicexam1",academicdetails.getPeriodicexam1());
			json.put("academichalfyearlyexam",academicdetails.getHalfyearlyexam());
			json.put("academicyearlyexam",academicdetails.getFinalexam());
			json.put("academicperiodicmark",academicdetails.getPeriodicmark());
			json.put("academicperiodicmark1",academicdetails.getPeriodicmark1());
			json.put("academichalfyearlymark",academicdetails.getHalfyearlymark());
			json.put("academictheorymark",academicdetails.getNonpracticalmark());
			json.put("academicpracticalmark",academicdetails.getPracticalMarks());
			json.put("academicnonpracticalmark",academicdetails.getTheoryMarks());
		}
		json.put("reporttype",reporttype);
		response.getWriter().print(json);

	}catch (Exception e) {
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ExamDetailsAction : getSubjectClassList Ending");
	return null;
}

}
	

