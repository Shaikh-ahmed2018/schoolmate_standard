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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.json.JSONObject;

import com.centris.campus.daoImpl.LocationDAOImpl;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;

import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;

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

public class LocationDetailsAction extends DispatchAction{
	
	private static final Logger logger = Logger
			.getLogger(LocationDetailsAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	boolean append = true;
	
	public ActionForward insertSchool(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : insertSchool Starting");
		try{
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
		
			FileOutputStream osf = null;
			FileInputStream is = null;
			String realPath = "",value="";
			final String SAVE_DIR="images/Logo";
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			
			LocationMasterForm form1 = (LocationMasterForm) form;
			FormFile file1 = form1.getSchoollogo();
			/*FormFile file2 = form1.getBoardlogo();*/
			
			FormFile file2 = null;
			File file = new File(request.getRealPath("/")+ SAVE_DIR);
			if (!file.exists()) {
				file.mkdirs();
			}
			
				try {
					if (file1 != null) {
						String extension=null;
						int k = form1.getSchoollogo().getFileName().lastIndexOf('.');
						if (k >= 0) {
							extension = form1.getSchoollogo().getFileName().substring(k+1);
						}
						
						/*if(pojo.getDomain().contains(".")){
							 value=pojo.getDomain().split(".")[0];
						}else{
							value=pojo.getDomain();
						}*/
						
						 value=pojo.getDomain();
						 realPath = "SCHOOLINFO/"+pojo.getDomain()+"/Logo/" +value+"Logo."+extension;
						 File folder = new File(request.getRealPath("/") +"SCHOOLINFO/"+pojo.getDomain()+"/Logo");
						 if(!folder.exists()) {
							 folder.mkdirs();
						 }
						 String filePath = request.getRealPath("/") + "SCHOOLINFO/"+pojo.getDomain()+"/Logo/" +value+"Logo."+extension;
						
						 System.out.println(filePath);
						 if (form1.getSchoollogo().getFileSize() > 0) {
							byte[] btDataFile = form1.getSchoollogo().getFileData();
							
							File of = new File(filePath);
							
							 osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();
						} else {
							realPath = "";
						}
						form1.setSchoollogoFilePath(realPath);
					}
					else if(form1.getHiddenschoollogoId()!="" ||form1.getHiddenschoollogoId()!=null){
						form1.setSchoollogoFilePath(form1.getHiddenschoollogoId());
					}
					else{
						form1.setSchoollogoFilePath("");
					}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				if (is != null) {
					is.close();
				}
				if (osf != null) {
					osf.close();
				}
			}
				try {/*
					if (file2 != null) {
						String extension=null;
						int k = form1.getBoardlogo().getFileName().lastIndexOf('.');
						if (k >= 0) {
							extension = form1.getBoardlogo().getFileName().substring(k+1);
						}
						 realPath = "images/Logo/" +request.getParameter("locationname")+"BoardLogo."+extension;

						String filePath = request.getRealPath("/") + "images/Logo/" +request.getParameter("locationname")+"BoardLogo."+extension;

						if (form1.getBoardlogo().getFileSize() > 0) {

							byte[] btDataFile = form1.getBoardlogo().getFileData();
							File of = new File(filePath);
							is = new FileInputStream(of);
						   osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();
						} else {
							realPath = "";
						}
						form1.setBoardlogoFilePath(realPath);
					}else{
						form1.setBoardlogoFilePath(form1.getHiddenboardlogoId());
					}

				*/} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (is != null) {
						is.close();
					}
					if (osf != null) {
						osf.close();
					}
				}
			
			
			
			String createCode = HelperClass.getCurrentUserID(request);
			String locationName=request.getParameter("locationname");
			String address=request.getParameter("address");
			String locationId=request.getParameter("update_loc");
			/*String board=request.getParameter("board");
			String affilno=request.getParameter("affilno"); 
			String schoolcode=request.getParameter("schoolcode");*/
			String website=request.getParameter("website");
			String emailId=request.getParameter("emailId");
			/*String contactno=request.getParameter("contactno");
			String landlineno=request.getParameter("landlineno");*/
			String address2=request.getParameter("address2");
			String countryId=request.getParameter("countryId");
			String stateId=request.getParameter("stateId");
			String cityId=request.getParameter("cityId");
			String pincode=request.getParameter("pincode"); 
			/*String hiddenaddId=request.getParameter("hiddenaddId");*/
			
			form1.setCperson(request.getParameter("cperson"));
			form1.setCemailId(request.getParameter("cemailId"));
			form1.setClandline(request.getParameter("clandline"));
			form1.setAffilno(request.getParameter("saffiliationno"));
			form1.setCmobileno(request.getParameter("cmobileno"));
			
			form1.setLocationname(locationName.trim());
			form1.setAddress(address);
			form1.setLoc_id(locationId);
			form1.setCreatedBy(createCode);
			/*form1.setBoard(board);
			form1.setAffilno(affilno);
			form1.setSchoolcode(schoolcode);*/
			form1.setWebsite(website);
			form1.setEmailId(emailId);
			
			form1.setLog_audit_session(log_audit_session);
			form1.setCustomerId(pojo.getCustId());
			/*form1.setContactno(contactno);
			form1.setLandlineno(landlineno);
			form1.setHiddenaddId(hiddenaddId);*/
			form1.setAddress2(address2);
			form1.setCountry(countryId);
			form1.setState(stateId);  
			form1.setCity(cityId);
			form1.setPincode(pincode);
			
			
			LocationBD details=new LocationBD();
			String result = details.insertLocationDetailsAction(form1,locationId,pojo);
			
			JSONObject object = new JSONObject();
			object.put("status", result);
			response.getWriter().print(object);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		 
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : insertSchool Ending");
		
		return null;
	}
	
	public ActionForward validatelocationname(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : validatelocationname: Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String locname = request.getParameter("locname");
			String locid = request.getParameter("locid");
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			LocationMasterForm form1= new LocationMasterForm();

			form1.setLocationname(locname);
			form1.setLocation_id(locid);
			form1.setCustomerId(pojo.getCustId());

			String locname_available = new LocationBD().validateLocName(form1,userLoggingsVo);

			JSONObject object = new JSONObject();
			object.put("des_available", locname_available);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : validatelocationname : Ending");
		return null;
	}
	
	public ActionForward validateLocNameUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction: validatelocationname: Starting");

		try {
			String locname = request.getParameter("locname");
			String locid = request.getParameter("locid");
			
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			LocationVO validateUpdate = new LocationVO();
			validateUpdate.setLocationname(locname);
			validateUpdate.setLocation_id(locid); 
			validateUpdate.setCustid(pojo.getCustId());
			
			String desname_available = new LocationBD().validateLocNameUpdate(validateUpdate,pojo);

			JSONObject object = new JSONObject();
			object.put("des_available", desname_available);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction: validatelocationname: Ending");
		return null;
	}
	
	public ActionForward editSchool(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : editSchool Starting");
		try {
			
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			
			String title = "Modify School";
			request.setAttribute("Location", title);
			
			String LocId = request.getParameter("locid");
			
			LocationVO edit_list = new LocationBD().editSchool(LocId,pojo);
			List<LocationVO> edit_array=new ArrayList<LocationVO>();
			edit_array.add(edit_list);
			
			
			request.setAttribute("editlist",edit_list);
			
			request.setAttribute("operation","Edit");
			
			JSONObject obj = new JSONObject();
			obj.put("editlist",edit_array);
			obj.put("operation", "Edit");
			response.getWriter().print(obj);
			
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : editSchool Ending");
		 return null;
	}
	
	public ActionForward InactiveLocation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : InactiveLocation Starting");
		@SuppressWarnings("unused")
		String username = null;
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String locid[] = request.getParameter("locid").split(",");    
			String inactiveReason = request.getParameter("inactivereason");
			String otherReason = request.getParameter("otherreason"); 
			String status = request.getParameter("status");  
			String activeReason = request.getParameter("activereason"); 
			
			LocationVO locvo = new LocationVO();
			locvo.setInactiveReason(inactiveReason);
			locvo.setOtherReason(otherReason);
			locvo.setActiveReason(activeReason);
			locvo.setStatus(status);
			locvo.setLog_audit_session(log_audit_session);
			locvo.setCustid(pojo.getCustId());
			
			String result =new LocationBD().InactiveLocation(locid,locvo,pojo);

			JSONObject object = new JSONObject();
			object.put("status", result);
			response.getWriter().print(object);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : InactiveLocation Ending");
		return null;
	}
	
	public ActionForward downloadLocationDetailsXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : downloadLocationDetailsXL  Starting");
		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = getServlet().getServletContext().getRealPath("Reports/LocationDetailsXLReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			String SearchName = request.getParameter("searchTerm");
			String status = request.getParameter("status");
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			
			if(SearchName != null){
				list =obj.searchLocationDetails(SearchName,status,pojo);
				request.setAttribute("locationDetailsList", list);
				request.setAttribute("searchnamelist", SearchName);
			}
			else
			{
				list = obj.getLocationDetails(pojo);
			}

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					getServlet().getServletContext().getRealPath("Reports/LocationDetailsXLReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Location Details Excel Report" };
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,sheetNames);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,Boolean.FALSE);

			exporter.exportReport();

			pdfxls = new File(
					getServlet().getServletContext().getRealPath("Reports/LocationDetailsXLReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=LocationDetailsXLReport.xls");
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
				+ " Control in LocationDetailsAction : downloadLocationDetailsXL  Ending");
		return null;
	}

	public ActionForward downloadLocationDetailsPDF(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : downloadLocationDetailsPDF  Starting");
		try {

			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			String SearchName = request.getParameter("searchTerm");
			String status = request.getParameter("status");
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			if (SearchName != null) {

				list = obj.searchLocationDetails(SearchName, status,pojo);
				request.setAttribute("locationDetailsList", list);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				list = obj.getLocationDetails(pojo);
			}

			String sourceFileName = getServlet().getServletContext()
					.getRealPath("Reports/LocationDetailsPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			String PropfilePath = getServlet().getServletContext().getRealPath("") + "\\images\\" + ImageName.trim();

			/*
			 * String schName = res.getString("SchoolName"); String schAddLine1
			 * = res.getString("AddressLine1");
			 */

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("Image", PropfilePath);
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport, parameters, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition",
					"outline; filename=\"" + "LocationDetailsPDFReport - " + ".pdf\"");
			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
			outstream.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : downloadLocationDetailsPDF  Ending");
		return null;
	}

	public ActionForward InActiveLocationList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction: InActiveLocationList: Starting");
		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			String status = request.getParameter("status");
			/*String locId = request.getParameter("locId");*/
			
			LocationVO locvo = new LocationVO();
			locvo.setStatus(status);
			locvo.setCustid(pojo.getCustId());
			/*locvo.setLocation_id(locId);*/
			
			List<LocationVO> list = new LocationBD().InActiveLocationList(locvo,pojo);

			JSONObject object = new JSONObject();
			object.put("LocationList", list);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction: InActiveLocationList: Ending");
		return null;
	}
	
	public ActionForward getCountries(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction: getCountries: Starting");
		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");

			LocationVO locvo = new LocationVO();
		 
			
			
			List<LocationVO> list = new LocationDAOImpl().getCountries(locvo,pojo);

			JSONObject object = new JSONObject();
			object.put("list", list);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction: getCountries: Ending");
		return null;
	}
	
	public ActionForward getStates(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction: getStates: Starting");
		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			String countryId = request.getParameter("countryId");
			
			LocationVO locvo = new LocationVO();
		 
			
			locvo.setCountryId(countryId);
			
			List<LocationVO> list = new LocationDAOImpl().getStates(locvo,pojo);

			JSONObject object = new JSONObject();
			object.put("list", list);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction: getCountries: Ending");
		return null;
	}
	
	public ActionForward getCities(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction: getCities: Starting");
		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			String stateId = request.getParameter("stateId");
			
			LocationVO locvo = new LocationVO();
			locvo.setStateId(stateId);
			
			List<LocationVO> list = new LocationDAOImpl().getCities(locvo,pojo);

			JSONObject object = new JSONObject();
			object.put("list", list);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction: getCities: Ending");
		return null;
	}
	
	public ActionForward SchoolList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction: SchoolList: Starting");
		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");

			LocationVO locvo = new LocationVO();
			 
			locvo.setCustid(pojo.getCustId());
			
			List<LocationVO> list = new LocationBD().SchoolList(locvo,pojo);

			JSONObject object = new JSONObject();
			object.put("LocationList", list);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction: SchoolList: Ending");
		return null;
	}

	public ActionForward editSchoolLocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : editSchoolLocation Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LOCATION_ADDRESS);
			
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			
			String title = "Modify Branch";
			request.setAttribute("Location", title);
			
			String LocId = request.getParameter("locid").split(",")[0];
			String addId = request.getParameter("locid").split(",")[1];
			
			LocationVO edit_list = new LocationBD().editLocation(LocId,addId,pojo);
			request.setAttribute("editlist",edit_list);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getSchoolList(pojo);
			request.setAttribute("locationList", locationList);
			
			request.setAttribute("operation","Edit");
			request.setAttribute("hiddenlocId",addId);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : editSchoolLocation Ending");
		return mapping.findForward(MessageConstants.ADDSCHOOLADDRESS);
	}
	
	public ActionForward insertLocationAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : insertLocationAddress Starting");
		try{
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
		
			FileOutputStream osf = null;
			FileInputStream is = null;
			String realPath = "",value="";
			
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			String locationName=request.getParameter("locationname");
			final String SAVE_DIR="SCHOOLINFO/"+pojo.getDomain()+"/Logo/";
			
			LocationMasterForm form1 = (LocationMasterForm) form;
			FormFile file1 = form1.getSchoollogo();
			FormFile file2 = form1.getBoardlogo();
			
			File file = new File(request.getRealPath("/")+ SAVE_DIR);
			if (!file.exists()) {
				file.mkdirs();
			}
			
				try {/*
					if (file1 != null) {
						String extension=null;
						int k = form1.getSchoollogo().getFileName().lastIndexOf('.');
						if (k >= 0) {
							extension = form1.getSchoollogo().getFileName().substring(k+1);
						}
						 realPath = "SCHOOLINFO"+pojo.getDomain()+"/Logo/" +request.getParameter("locationname")+"Logo."+extension;
						String filePath = request.getRealPath("/") + "SCHOOLINFO"+pojo.getDomain()+"/Logo/" +request.getParameter("locationname")+"Logo."+extension;
						if (form1.getSchoollogo().getFileSize() > 0) {
							byte[] btDataFile = form1.getSchoollogo().getFileData();
							File of = new File(filePath);
							 
							 osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();
						} else {
							realPath = "";
						}
						form1.setSchoollogoFilePath(realPath);
					}
					else if(form1.getHiddenschoollogoId()!="" ||form1.getHiddenschoollogoId()!=null){
						form1.setSchoollogoFilePath(form1.getHiddenschoollogoId());
					}
					else{
						form1.setSchoollogoFilePath("");
					}

			*/} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				if (is != null) {
					is.close();
				}
				if (osf != null) {
					osf.close();
				}
			}
				try {
					if (file2 != null) {
						
						String extension=null;
						int k = form1.getBoardlogo().getFileName().lastIndexOf('.');
						if (k >= 0) {
							extension = form1.getBoardlogo().getFileName().substring(k+1);
						}
				
						 value=locationName.replace(" ", "");
						 realPath = "SCHOOLINFO/"+pojo.getDomain()+"/Logo/" +value+"BoardLogo."+extension;

						 File folder = new File(request.getRealPath("/") +"SCHOOLINFO/"+pojo.getDomain()+"/Logo");
						 if(!folder.exists()) {
							 folder.mkdirs();
						 }
						String filePath = request.getRealPath("/") +"SCHOOLINFO/"+pojo.getDomain()+"/Logo/"+value+"BoardLogo."+extension;

						
						if (form1.getBoardlogo().getFileSize() > 0) {

							byte[] btDataFile = form1.getBoardlogo().getFileData();
							File of = new File(filePath);
						
						   osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();
						} else {
							realPath = "";
						}
						form1.setBoardlogoFilePath(realPath);
					}else{
						form1.setBoardlogoFilePath(form1.getHiddenboardlogoId());
					}

				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (is != null) {
						is.close();
					}
					if (osf != null) {
						osf.close();
					}
				}
			
			
			String createCode = HelperClass.getCurrentUserID(request);
			String address=request.getParameter("address");
			String locationId=request.getParameter("update_loc");
			String board=request.getParameter("board");
			String affilno=request.getParameter("affilno"); 
			String schoolcode=request.getParameter("schoolcode");
			String website=request.getParameter("website");
			String emailId=request.getParameter("emailId");
			String contactno=request.getParameter("contactno");
			String landlineno=request.getParameter("landlineno");
			String address2=request.getParameter("address2");
			String countryId=request.getParameter("countryId");
			String stateId=request.getParameter("stateId");
			String cityId=request.getParameter("cityId");
			String pincode=request.getParameter("pincode"); 
			String hiddenaddId=request.getParameter("hiddenaddId");
			String locAddId=request.getParameter("locAddId");
			String hiddenlocaddressId=request.getParameter("hiddenlocaddressId");
			
			form1.setLocationname(locationName);
			form1.setLocAddId(locAddId);
			form1.setAddress(address);
			form1.setLoc_id(locationId);
			form1.setCreatedBy(createCode);
			form1.setBoard(board);
			form1.setAffilno(affilno);
			form1.setSchoolcode(schoolcode);
			form1.setWebsite(website);
			form1.setEmailId(emailId);
			
			form1.setLog_audit_session(log_audit_session);
			form1.setCustomerId(pojo.getCustId());
			form1.setContactno(contactno);
			form1.setLandlineno(landlineno);
			form1.setAddress2(address2);
			form1.setCountry(countryId);
			form1.setState(stateId);  
			form1.setCity(cityId);
			form1.setPincode(pincode);
			form1.setHiddenaddId(hiddenaddId); 
			form1.setHiddenlocaddressId(hiddenlocaddressId);
			
			LocationBD details=new LocationBD();
			String result = details.insertLocationAddress(form1,locationId,pojo);
			
			JSONObject object = new JSONObject();
			object.put("status", result);
			response.getWriter().print(object);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		 
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDetailsAction : insertLocationAddress Ending");
		
		return null;
	}
	
	public ActionForward downloadSchoolLogo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		System.out.println("ownload file is working");

		try {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LocationDetailsAction: downloadSchoolLogo Starting");

			try {
				String docPath = request.getParameter("Path");
				response.setContentType("application/octet-stream");
				String fileName = "FileName";
				fileName=docPath;

				response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
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
					+ " Control in LocationDetailsAction: downloadSchoolLogo Ending");

			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ActionForward downloadBoardLogo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		System.out.println("ownload file is working");

		try {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LocationDetailsAction: downloadBoardLogo Starting");

			try {
				String docPath = request.getParameter("Path");
				response.setContentType("application/octet-stream");
				String fileName = "FileName";
				fileName=docPath;

				response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
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
					+ " Control in LocationDetailsAction: downloadBoardLogo Ending");

			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
