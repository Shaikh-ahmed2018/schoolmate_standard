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
import org.apache.struts.upload.FormFile;
import org.json.JSONObject;

import com.centris.campus.delegate.ReportsMenuBD;

import com.centris.campus.delegate.TransportBD;
import com.centris.campus.forms.DriverTransportForm;

import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;

import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;


public class AddDriverDetailsAction extends DispatchAction {


	private static final Logger logger = Logger
			.getLogger(AddDriverDetailsAction.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");

	private static String ImageName = res.getString("ImageName");

	public ActionForward addDriver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : addDriver Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_DRIVERMASTER);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String arg = "New Driver";
			request.setAttribute("driverdetails", arg);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			request.setAttribute("historysearch", request.getParameter("historysearch"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : addDriver  Ending");

		return mapping.findForward(MessageConstants.ADD_DRIVER_DETAILS);
	}


	/*public ActionForward savedriverval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		//ln("savedriver action");



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FuelMaintenanceAction : saveDriver Starting");


		//ln("savedriver action");


		String status = request.getParameter("result");

		if (status != null) {

			if (status.equalsIgnoreCase("Driver Created Successfully")) {

				request.setAttribute("successmessagediv",
						"Driver Created Successfully");
			}
		}



			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);

			TransportForm formobj = new TransportForm();



			try {	

				 String createUser = HelperClass.getCurrentUserID(request);

			    String license = request.getParameter("license");

				formobj.setLicense(license);
				formobj.setDrivercode(request.getParameter("drivercode"));
				formobj.setDriverName(request.getParameter("name"));
				formobj.setFather_name(request.getParameter("fatherName"));
				formobj.setDateofBirth(request.getParameter("dob"));
				formobj.setGender(request.getParameter("gender"));
				formobj.setMobile(request.getParameter("mobile"));
				formobj.setEmerg_contact(request.getParameter("emerg_contact"));
				formobj.setDateofJoin(request.getParameter("enq_dateofJoin"));
				formobj.setExperience(request.getParameter("exp"));
				formobj.setAddress(request.getParameter("address"));
				formobj.setAge(request.getParameter("age"));
				formobj.setDrivingliecenseNo(request
						.getParameter("drivingLicenseNo"));
				formobj.setDl_issued_date(request.getParameter("dl_issued_date"));
				formobj.setDl_validity(request.getParameter("dlValidity"));
				formobj.setCreateUser(createUser);



				String result = new TransportBD().addDriverBD(formobj);


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
				+ " Control in FuelMaintenanceAction : saveDriver  Ending");

		return mapping.findForward(MessageConstants.ADD_DRIVER_DETAILS);

	}*/

	public ActionForward editDriver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : editDriver Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_DRIVERMASTER);

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
		
			
			String args = "Modify Driver";
			request.setAttribute("driverdetails", args);

			String drivercode = request.getParameter("driverCode");

			DriverMsaterListVo drivervo = new DriverMsaterListVo();

			drivervo.setDriverCode(drivercode);
			DriverMsaterListVo result = new TransportBD().editDriverBD(drivervo,custdetails);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("driverlist", result);
			request.setAttribute("drivercode", drivercode);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			request.setAttribute("historysearch", request.getParameter("historysearch"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : editDriver  Ending");
		return mapping.findForward(MessageConstants.ADD_DRIVER_DETAILS);
	}

	public ActionForward deleteDriver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in AddDriverDetailsAction : deleteDriver Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
		
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String[] drivercode  = request.getParameter("driverCode").split(",");
			String inactivereason = request.getParameter("inactivereason"); 
			String activereason = request.getParameter("activereason"); 
			String otherreason = request.getParameter("otherreason");
			String status = request.getParameter("status");
			
			DriverMsaterListVo vo=new DriverMsaterListVo();
			vo.setLog_audit_session(log_audit_session);
			vo.setDriverCodes(drivercode);
			vo.setInactivereason(inactivereason);
			vo.setActivereason(activereason);
			vo.setOtherreason(otherreason);
			vo.setStatus(status);

			String check =new TransportBD().deleteDriverBD(vo,userLoggingsVo);

			JSONObject json= new JSONObject();
			json.put("status", check);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : deleteDriver  Ending");
		return null;

	}

	public ActionForward validateDriver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : validateDriver Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
		
			
			String drivercode = request.getParameter("drivercode");
			String name = request.getParameter("name");
			String dob = request.getParameter("dob");
			String mob = request.getParameter("mobile");
			String doj = request.getParameter("enq_dateofJoin");

			DriverMsaterListVo drivervo = new DriverMsaterListVo();
			drivervo.setDriverCode(drivercode);
			drivervo.setDriverName(name);
			drivervo.setDateofBirth(dob);
			drivervo.setMobile(mob);
			drivervo.setDateofJoin(doj);

			boolean driver_Available= new TransportBD().validateDriverBD(drivervo,custdetails);
			JSONObject jsonobject= new JSONObject();

			if(driver_Available){

				jsonobject.put("status", "true");
			}else{
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
				+ " Control in AddDriverDetailsAction : validateDriver  Ending");
		return null;
	}

	// for license validation//


	public ActionForward validateLicense(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : validateLicense Starting");


		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String drivingliecenseNo = request.getParameter("drivingliecenseNo");

			DriverMsaterListVo drivervo = new DriverMsaterListVo();
			drivervo.setDrivingliecenseNo(drivingliecenseNo); 
			drivervo.setLocId(request.getParameter("locId"));

			boolean License_Available= new TransportBD().validateLicenseBD(drivervo,custdetails);
			JSONObject jsonobject= new JSONObject();
			jsonobject.put("status", License_Available);
			response.getWriter().print(jsonobject);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : validateLicense  Ending");
		return null;
	}


	public ActionForward DriverDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : DriverDetailsXLS  Starting");

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String status=request.getParameter("status");
			String searchvalue=request.getParameter("searchvalue");
			String locId=request.getParameter("locId");
			String locIdtext=request.getParameter("loctext");
			String statustext=request.getParameter("statustext");
			
			if(locId.equalsIgnoreCase("all")){
				locId="%%";
			}
			
			if(searchvalue==null){
				searchvalue="";
			}else{
				searchvalue=searchvalue.trim();
			}
			
			LocationVO schoolInfo=HelperClass.getCustSchoolInfo(userLoggingsVo,locId);
			
			DriverMsaterListVo vo=new DriverMsaterListVo();
			vo.setStatus(status);
			vo.setSearch(searchvalue);
			vo.setLocId(locId);
			
			List<DriverMsaterListVo> List = new TransportBD().driverListByStatus(vo,userLoggingsVo);
			 
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/DriverDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(List);
			Map parameters = new HashMap();
			parameters.put("locIdtext", " "+schoolInfo.getSchname()+" ( "+schoolInfo.getLocationname()+" ) "); 
			parameters.put("statustext", " "+statustext);

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(request.getRealPath("Reports/DriverDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Driver Details Excel Report" };
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

			pdfxls = new File(request.getRealPath("Reports/DriverDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment; filename=DriverDetailsXLSReport.xls");
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
				+ " Control in AddDriverDetailsAction : DriverDetailsXLS   Ending");
		return null;


	}

	public ActionForward DriverDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : DriverDetailsPDFReport  Starting");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	
			String status=request.getParameter("status");
			String searchvalue=request.getParameter("searchvalue");
			String locId=request.getParameter("locId");
			String locIdtext=request.getParameter("loctext");
			String statustext=request.getParameter("statustext");
			
			if(locId.equalsIgnoreCase("all")){
				locId="%%";
			}
			
			if(searchvalue==null){
				searchvalue="";
			}else{
				searchvalue=searchvalue.trim();
			}
			
			LocationVO schoolInfo=HelperClass.getCustSchoolInfo(userLoggingsVo,locId);
			
			DriverMsaterListVo vo=new DriverMsaterListVo();
			vo.setStatus(status);
			vo.setSearch(searchvalue);
			vo.setLocId(locId);
			
			List<DriverMsaterListVo> Details = new TransportBD().driverListByStatus(vo,userLoggingsVo);

			 String sourceFileName = request.getRealPath("Reports/DriverDetailsPDF.jrxml");
			 JasperDesign design = JRXmlLoader.load(sourceFileName);

			 JasperReport jasperreport = JasperCompileManager.compileReport(design);

			 JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(Details);

			 String PropfilePath = getServlet().getServletContext().getRealPath(
					 "")
					 + "\\images\\" + ImageName.trim();


			 Map parameters = new HashMap();

			 parameters.put("locIdtext", " "+schoolInfo.getSchname()+" ( "+schoolInfo.getLocationname()+" ) "); 
			 parameters.put("statustext", " "+statustext);


			 byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					 parameters, beanColDataSource);

			 response.setContentType("application/pdf");

			 response.setContentLength(bytes.length);

			 response.setHeader("Content-Disposition", "outline; filename=\""
					 + "DriverDetailsPDF - " + ".pdf\"");
			 ServletOutputStream outstream = response.getOutputStream();
			 outstream.write(bytes, 0, bytes.length);
			 outstream.flush();

		}

		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+"Control in AddDriverDetailsAction : DriverDetailsPDFReport Ending");
		return null;
	}



	public ActionForward searchDriverDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : searchDriverDetails Starting");


		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);


		try {

			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			String SearchName = request.getParameter("searchname");

			ArrayList<DriverMsaterListVo> driverlist = new TransportBD().searchDriverBD(SearchName.trim());



			request.setAttribute("driverList", driverlist);
			request.setAttribute("searchname", SearchName);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : searchDriverDetails  Ending");

		return mapping.findForward(MessageConstants.driver_list);

	}

	public ActionForward savedriverval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : savedriverval Starting");

		//ln("Action Class Working");

		String path = null;
		int i = 0;  
		File fileURL = null;
		FileOutputStream outputStream = null;


		DriverTransportForm driverform =(DriverTransportForm)form;
		/*TransportForm driverform = new TransportForm();*/

		FormFile file = driverform.getLicensedrive();


		DriverMsaterListVo drivervo  = new DriverMsaterListVo();
		String success=null;

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String args = "Modify Driver Details";
			request.setAttribute("driverdetails", args);

			String createuser = HelperClass.getCurrentUserID(request);

			driverform.setCreateUser(createuser);

			try {
				if(driverform.getLicensedrive() == null || driverform.getLicensedrive().getFileSize()==0){
					drivervo.setUploadinglicense(driverform.getLicenseupload()); 
					//ln("getLicensedrive action:===> hidden : "+driverform.getLicensedrive());

				}else{

					String extension=null;

					int j = driverform.getLicensedrive().getFileName().lastIndexOf('.');
					if (j >= 0) {
						extension = driverform.getLicensedrive().getFileName().substring(i+1);
					}

					String birthcertificate_path = "FIles/TRANSPORT/DriverLicence_" +driverform.getDriver_code()+ "." +extension;

					//ln("birthcertificate_path::"+birthcertificate_path);

					String filePath = request.getRealPath("/") + "FIles/TRANSPORT/DriverLicence_" +driverform.getDriver_code()+ "." +extension;
					if (driverform.getLicensedrive().getFileSize() > 0) {

						byte[] btDataFile = driverform.getLicensedrive().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();

					} else {

						birthcertificate_path = "";
					}
					drivervo.setUploadinglicense(birthcertificate_path);
				}
				

				drivervo.setDriverName(driverform.getDriverName());	
				drivervo.setFather_name(driverform.getFather_name());	
				drivervo.setDateofBirth(driverform.getDateofBirth());
				drivervo.setGender(driverform.getGender());
				drivervo.setMobile(driverform.getMobile());
				drivervo.setEmerg_contact(driverform.getEmerg_contact());
				drivervo.setDateofJoin(driverform.getDateofJoin());
				drivervo.setExperience(driverform.getExperience());
				drivervo.setAddress(driverform.getAddress());
				drivervo.setDrivingliecenseNo(driverform.getDrivingliecenseNo());
				drivervo.setDl_validity(driverform.getDl_validity());
				drivervo.setAge(driverform.getAge());
				drivervo.setStatus(driverform.getStatus());
				drivervo.setDriverCode(driverform.getDriver_code());
				drivervo.setDriving_license_types(driverform.getDriving_license_types());
				drivervo.setLog_audit_session(log_audit_session);
				drivervo.setLocId(driverform.getLocationnid());
			 
				boolean status = new TransportBD().addDriverBD(drivervo, createuser,custdetails);

				JSONObject object = new JSONObject();
				object.put("status", drivervo.getStatus());
				response.getWriter().print(object);
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddDriverDetailsAction : savedriverval  Ending");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		//return mapping.findForward(MessageConstants.ADD_DRIVER_DETAILS);
		return null;
	}




	public ActionForward downloaddriverlicenc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		//ln("ownload file is working");

		try {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddDriverDetailsAction: downloaddriverlicenc Starting");

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
					+ " Control in AddDriverDetailsAction: downloaddriverlicenc Ending");

			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward driverListByStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
		{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : driverListByStatus Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_DRIVERMASTER);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String status=request.getParameter("status");
			String searchvalue=request.getParameter("searchvalue");
			String locId=request.getParameter("locId");
			
			if(locId.equalsIgnoreCase("all")){
				locId="%%";
			}
			
			if(searchvalue==null){
				searchvalue="";
			}else{
				searchvalue=searchvalue.trim();
			}
			
			List<DriverMsaterListVo> list = new ArrayList<DriverMsaterListVo>();
			
			DriverMsaterListVo vo=new DriverMsaterListVo();
			vo.setStatus(status);
			vo.setSearch(searchvalue);
			vo.setLocId(locId);
			
			TransportBD delegate= new TransportBD();
			list=delegate.driverListByStatus(vo,custdetails);
			
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("list", list);
			response.getWriter().println(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDriverDetailsAction : driverListByStatus Ending");

		return null;
	 }


}
