package com.centris.campus.actions;
import java.awt.print.PrinterJob;
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
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.TransportDaoImpl;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StageDelegateBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.delegate.TransportBD;
import com.centris.campus.forms.TransportCategoryForm;
import com.centris.campus.forms.TransportForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.TransportPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.AddStageVO;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TermMasterVo;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.VehicleTypeVo;

public class TransportAction extends DispatchAction {
	private static final Logger logger = Logger
			.getLogger(TransportAction.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	public ActionForward addvehicledetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addvehicledetails Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_VEHICLEMASTER);
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String title = "New Vehicle";
			request.setAttribute("vehicledetails", title);
			
			String succesmessage = request.getParameter("message");
        	request.setAttribute("successmessagediv", succesmessage);
    		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
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
				+ " Control in TransportAction : addvehicledetails Ending");

		return mapping.findForward(MessageConstants.ADD_VEHICLE);
	}

	public ActionForward saveVehicleDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : saveVehicleDetails Starting");
		
		String args = "Vehicle Details";
		request.setAttribute("vehicledetails", args);
		String path = null;
		int i = 0; 
		
		File fileURL = null;
		FileOutputStream outputStream = null;
		TransportForm vehicliform =(TransportForm)form;
		VehicleDetailsVO vehiclivo = new VehicleDetailsVO();
		
		try {
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				String createuser = HelperClass.getCurrentUserID(request);
				String vehiclecode = IDGenerator.getPrimaryKeyID("transport_vehicle",userLoggingsVo);
				vehicliform.setVehiclecode(vehiclecode);
				vehicliform.setCreateuser(createuser);

				try {
					
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TRANSPORT/Vehicle/"+vehicliform.getVehicleregno().replace(' ', '_');
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					
					if(vehicliform.getRcfile() == null || vehicliform.getRcfile().getFileSize()==0){
						vehiclivo.setRcfile(vehicliform.getHrcfileid()); 
						
						
					}
					else{


						String extension=null;

						int j = vehicliform.getRcfile().getFileName().lastIndexOf('.');

						if (j >= 0) {
							extension = vehicliform.getRcfile().getFileName().substring(i+1);
						}


						String rcfilepath = "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TRANSPORT/Vehicle/"+vehicliform.getVehicleregno().replace(' ', '_')+"/RCUpload"+"."+extension;

						String filePath = request.getServletContext().getRealPath("/") +"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TRANSPORT/Vehicle/"+vehicliform.getVehicleregno().replace(' ', '_')+"/RCUpload"+"."+extension;

						//System.out.println("Action Class--> RC filePath: "+filePath);

						if (vehicliform.getRcfile().getFileSize() > 0) {

							byte[] btDataFile = vehicliform.getRcfile().getFileData();
							File of = new File(filePath);
							FileOutputStream osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();
						}
						else{
							rcfilepath = ""; 
						}

						/* vehicliform.setRcfile1(rcfilepath);*/
						
						vehiclivo.setRcfile(rcfilepath); 
						System.out.println("Rcfile1 action:===> "+rcfilepath);
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
				
				try{
					System.out.println("getInsurancefile text box Value: "+vehicliform.getInsurancefile());
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TRANSPORT/Vehicle/"+vehicliform.getVehicleregno().replace(' ', '_');
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					
					if(vehicliform.getInsurancefile() == null || vehicliform.getInsurancefile().getFileSize() == 0){
						vehiclivo.setInsurancefile(vehicliform.getHinsurancefileid()); 
						System.out.println("Insurancefile1 action: hidden====>     "+vehicliform.getHinsurancefileid());
					}
					else{


						String extension=null;	

						int j = vehicliform.getInsurancefile().getFileName().lastIndexOf('.');

						if (j >= 0) {
							extension = vehicliform.getInsurancefile().getFileName().substring(i+1);
						}
						String insurancefilepath = "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TRANSPORT/Vehicle/"+vehicliform.getVehicleregno().replace(' ', '_')+"/insurance"+"."+extension;
						String filePath = request.getServletContext().getRealPath("/") + "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TRANSPORT/Vehicle/"+vehicliform.getVehicleregno().replace(' ', '_')+"/insurance"+ "." +extension;

						//System.out.println("INSURANCE filePath"+ filePath);

						if (vehicliform.getInsurancefile().getFileSize() > 0) {

							byte[] btDataFile = vehicliform.getInsurancefile().getFileData();
							File of = new File(filePath);
							FileOutputStream osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();
						}
						else{
							insurancefilepath = ""; 
						}
						
						vehiclivo.setInsurancefile(insurancefilepath); 
						System.out.println("Insurancefile1 action:====>     "+insurancefilepath);
					
						
					}

				} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}


				vehiclivo.setUpdateVehicleCode(vehicliform.getUpdatevehicleCode());	
				vehiclivo.setVehicleregno(vehicliform.getVehicleregno());	
				vehiclivo.setVehicletype(vehicliform.getVehicletype());
				vehiclivo.setMakersname(vehicliform.getMakersname());
				vehiclivo.setChassisno(vehicliform.getChassisno());
				vehiclivo.setFuelusedintheengine(vehicliform.getFuelengine());
				vehiclivo.setVehiclename(vehicliform.getVehiclename());
				vehiclivo.setTypeofbody(vehicliform.getTypeofbody());
				vehiclivo.setManufacturerdate(vehicliform.getManufacturerdate());
				vehiclivo.setSettingcapacity(vehicliform.getSettingcapacity());
				vehiclivo.setColorofbody(vehicliform.getColorofbody());
				vehiclivo.setIssueddate(vehicliform.getIssueddate());
				vehiclivo.setCompanyname(vehicliform.getCompanyname());
				vehiclivo.setDoneby(vehicliform.getDoneby());
				vehiclivo.setDriverCode(vehicliform.getDriverName());
				vehiclivo.setRoutecodeid(vehicliform.getRoutename());
				vehiclivo.setExpirydate(vehicliform.getExpirydate());
				vehiclivo.setDoneby(vehicliform.getDoneby());
				vehiclivo.setDriverName(vehicliform.getDriverName());
				vehiclivo.setExperience(vehicliform.getExperience());
				vehiclivo.setDl_issued_date(vehicliform.getDl_issued_date());
				vehiclivo.setLicensetodrive(vehicliform.getLicensetodrive());
				vehiclivo.setMobile(vehicliform.getMobile());
				vehiclivo.setDateofJoin(vehicliform.getDateofJoin());
				vehiclivo.setDl_validity(vehicliform.getDl_validity());
				vehiclivo.setDrivingliecenseNo(vehicliform.getDrivingliecenseNo());
				vehiclivo.setRoutename(vehicliform.getRoutename());
				vehiclivo.setHalttime(vehicliform.getHalttime());
				vehiclivo.setTotaldistance(vehicliform.getTotaldistance());
				vehiclivo.setRouteno(vehicliform.getRouteno());
				vehiclivo.setDestination(vehicliform.getDestination());
				vehiclivo.setTotalstops(vehicliform.getTotalstops());
				vehiclivo.setStatus(vehicliform.getStatus());
				vehiclivo.setEnginenumber(vehicliform.getEnginenumber());
				vehiclivo.setTaxpaid(vehicliform.getTaxpaid());
				vehiclivo.setTaxexpirydate(vehicliform.getTaxexpirydate());	
				vehiclivo.setPollution(vehicliform.getPollution());
				vehiclivo.setFc(vehicliform.getFc());
				vehiclivo.setPermitvalidity(vehicliform.getPermitvalidity());
				vehiclivo.setLog_audit_session(log_audit_session);
				vehiclivo.setLocid(vehicliform.getLocid());

				String status = new TransportBD().saveVehicleDetails(vehiclivo, createuser, vehiclecode,userLoggingsVo);
				
				System.out.println("status in action" + status);
				if(status=="save"){
					request.setAttribute("successmessagediv", "Adding Record Progressing...");
				}else if(status == "update"){
					request.setAttribute("successmessagediv", "Updating Record Progressing...");
				}
				else{
					request.setAttribute("errormessagediv1", "Vehicle Details not Added");
				}

				JSONObject object = new JSONObject();
				object.put("status", status);
				response.getWriter().print(object);

			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
	     	
		request.setAttribute("vehicleidDetails", vehicliform);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : saveVehicleDetails  Ending");

		return mapping.findForward(MessageConstants.ADD_VEHICLE);
	}

	public ActionForward checkingVehicleInsuranceDate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : checkingVehicleInsuranceDate Starting");
		try {

			System.out.println("checking vehicle details issued date ");

			VehicleDetailsVO vehiclecode = new VehicleDetailsVO();

			vehiclecode.setVehiclecode(request.getParameter("vehiclename"));
			vehiclecode.setIssueddate(request.getParameter("issueddate"));
			vehiclecode.setExpirydate(request.getParameter("expirydate"));

			boolean status = new TransportBD()
					.checkingVehicleInsuranceDate(vehiclecode);

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
				+ " Control in TransportAction : checkingVehicleInsuranceDate  Ending");

		return null;
	}

	public ActionForward getSingleVehicleDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getSingleVehicleDetails Starting");

		try {
			
			String args = "Modify Vehicle";
			request.setAttribute("vehicledetails", args);
			String vehiclecode = request.getParameter("vehicleIdlist");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_VEHICLEMASTER);

			System.out.println("edit vehile"+vehiclecode);
		
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			VehicleDetailsVO vehicleDetails = new TransportBD().getSingleVehicleDetails(vehiclecode,userLoggingsVo);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			DriverMsaterListVo driverDetails = new TransportBD().getSingleDriverDetails(vehiclecode,userLoggingsVo);
			
			System.out.println("Action class Driver Code: "+driverDetails.getDriverCode());

			TransportVo RouteDetails = new TransportBD().getRouteDetails(vehiclecode,userLoggingsVo);
			
			request.setAttribute("vehiclecode", vehiclecode);
			request.setAttribute("vehicleDetails", vehicleDetails);
			request.setAttribute("driverDetails", driverDetails);
			request.setAttribute("RouteDetails", RouteDetails);
			
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
				+ " Control in TransportAction : getSingleVehicleDetails Ending");

		return mapping.findForward(MessageConstants.ADD_VEHICLE);
	}

	public ActionForward deleteVehicleDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : deleteVehicleDetails Starting");

		try {

			System.out.println("delete action");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String vehiclecode = request.getParameter("getDataArray");
			System.out.println(vehiclecode);
			String[] code=vehiclecode.split(",");
			String button=request.getParameter("button");
			VehicleDetailsVO vo=new VehicleDetailsVO();
			vo.setStatus(request.getParameter("status"));
			vo.setReson(request.getParameter("reason"));
			
			System.out.println("get Parameter Values:" +code.length);
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String status = new TransportBD().deleteVehicleDetails(code,log_audit_session,userLoggingsVo,vo,button);
			

			System.out.println("delete status " + status);

			 request.setAttribute("vehicleDetails", status); 

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
				+ " Control in TransportAction : deleteVehicleDetails Ending");

		return null;
	}

	public ActionForward registernumberValidation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : registernumberValidation Starting");
		try {
             
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String vehicleregno = request.getParameter("vehicleregno").trim();
			String locId = request.getParameter("locId"); 
			String vehicleId = request.getParameter("vehicleId");

			if(vehicleId==null){
				vehicleId="";
			}
			
			String status = new TransportBD().registernumberValidation(vehicleregno,locId,userLoggingsVo,vehicleId);

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
				+ " Control in TransportAction : registernumberValidation  Ending");

		return null;
	}

	public ActionForward updateregisternumberValidation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : updateregisternumberValidation Starting");
		try {

			String vehicleregno = request.getParameter("vehicleregno").trim();
			String vehicleCode = request.getParameter("vehicleCode");
			String locId = request.getParameter("locId");
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String status = new TransportBD().updateregisternumberValidation(vehicleregno,vehicleCode,locId,userLoggingsVo);
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
				+ " Control in TransportAction : updateregisternumberValidation  Ending");

		return null;
	}

	public ActionForward chassisnovalidationvalidation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : chassisnovalidationvalidation Starting");
		try {

			String chassisno = request.getParameter("chassisno");
			String locId = request.getParameter("locId");
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			boolean status = new TransportBD().chassisnovalidationvalidation(chassisno,locId,userLoggingsVo);

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
				+ " Control in TransportAction : chassisnovalidationvalidation  Ending");

		return null;
	}

	public ActionForward updatechassisnovalidation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : updatechassisnovalidation Starting");
		try {

			String chassisno = request.getParameter("chassisno");
			String vehicleCode = request.getParameter("vehicleCode");
			String locId = request.getParameter("locId");
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			boolean status = new TransportBD().updatechassisnovalidation(chassisno,vehicleCode,locId,userLoggingsVo);

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
				+ " Control in TransportAction : updatechassisnovalidation  Ending");

		return null;
	}

	public ActionForward searchvehicledetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : searchvehicledetails Starting");
		System.out.println("search vehicle");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			
			String SearchName = request.getParameter("searchname").trim();
			String locId=request.getParameter("locationname");
			String status=request.getParameter("status");
			
			if(locId.equalsIgnoreCase("all")){
				locId = "%%";
			}
			
			if(SearchName == null){
				SearchName = "";
			}else if(SearchName == ""){
				SearchName = SearchName.trim();
			}
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ArrayList<VehicleDetailsVO> getvehiclelist = new TransportBD().searchvehicledetails(SearchName,custdetails,locId,status);
	
			JSONObject obj= new JSONObject();
			obj.put("getvehiclelist", getvehiclelist);
			response.getWriter().print(obj);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : searchvehicledetails Ending");

		return null;
	}

	public ActionForward checkforduplicateAddTime(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : checkforduplicateAddTime Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			VehicleDetailsVO obj = new VehicleDetailsVO();
			obj.setVehicleregno(request.getParameter("vehicleregno"));
			obj.setVehiclename(request.getParameter("vehiclename"));
			obj.setVehiclename(request.getParameter("enginenumber"));
			obj.setChassisno(request.getParameter("chassisno"));
			obj.setVehicletype(request.getParameter("vehicletype"));
			obj.setTaxpaid(request.getParameter("taxpaid"));
			obj.setPollution(request.getParameter("pollution"));
			obj.setLocid(request.getParameter("locId"));
		
			boolean status = new TransportBD().checkforduplicateAddTime(obj,custdetails);

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
				+ " Control in TransportAction : checkforduplicateAddTime  Ending");

		return null;
	}

	public ActionForward checkforduplicateUpdateTime(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : checkforduplicateUpdateTime Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			VehicleDetailsVO obj = new VehicleDetailsVO();
			obj.setVehicleregno(request.getParameter("vehicleregno"));
			obj.setVehiclename(request.getParameter("vehiclename"));
			obj.setVehicletype(request.getParameter("enginenumber"));
			obj.setTypeofbody(request.getParameter("chassisno"));
			obj.setMakersname(request.getParameter("vehicletype"));
			obj.setManufacturerdate(request.getParameter("manufacturerdate"));
			obj.setChassisno(request.getParameter("chassisno"));
			obj.setFuelusedintheengine(request.getParameter("fuelengine"));
			obj.setVehiclecode(request.getParameter("vehicleCode"));
			obj.setTaxpaid(request.getParameter("taxpaid"));
			obj.setPollution(request.getParameter("pollution"));
			obj.setLocid(request.getParameter("locId"));
	
			obj.setLog_audit_session(log_audit_session);
			boolean status = new TransportBD().checkforduplicateUpdateTime(obj,userLoggingsVo);

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
				+ " Control in TransportAction : checkforduplicateUpdateTime  Ending");

		return null;
	}

	public ActionForward removeMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : removeMessage Starting");
		System.out.println("remove message");
		try {
			/* request.setAttribute("errormessagediv", null); */
			request.setAttribute("successmessagediv", "");
			response.getWriter().print("Message Removed");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : removeMessage  Ending");
		return null;
	}

	// Route master Start

	public ActionForward addRouteScreen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addRouteScreen Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_ROUTEMASTER);
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String loc=request.getParameter("locationid");
			
			String title = "New Route Master";
			request.setAttribute("Route", title);

			AddStageVO vo = new AddStageVO();

			vo.setStageName(request.getParameter("searchvalue"));
			vo.setLocId(loc);
			 
			ArrayList<AddStageVO> list = new StageDelegateBD().SelectAllSatges(vo,userLoggingsVo);

			request.setAttribute("StageDetails", list);
			request.setAttribute("locationid", loc);
			
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			request.setAttribute("historysearch", request.getParameter("historysearch"));
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addRouteScreen  Ending");

		return mapping.findForward(MessageConstants.ADDROUTESCREEN);

	}

		
	

	public ActionForward insertRouteMasterDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : insertRouteMasterDetails Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			TransportPojo tpMasterPojo = new TransportPojo();
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String routeName = request.getParameter("routeName");
			String routeNo = request.getParameter("routeNo");
			String routeLogicName = request.getParameter("routeLogicName");
			/*String destination = request.getParameter("destination");*/
			String halttime = request.getParameter("haltTime");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String totalStops = request.getParameter("totalStops");
			String totalDistance = request.getParameter("totalDistance");
			String stagesid=request.getParameter("stagesidArray");
			String stagesidArray[]=stagesid.split(",");
			
			String createUser = HelperClass.getCurrentUserID(request);
			
			tpMasterPojo.setStagesidArray(stagesidArray);
			tpMasterPojo.setHalttime(halttime);
			tpMasterPojo.setEndTime(endTime);
			tpMasterPojo.setRouteLogicName(routeLogicName);
			tpMasterPojo.setRouteName(routeName);
			tpMasterPojo.setRouteNo(routeNo);
			/*tpMasterPojo.setDestination(destination);*/
			tpMasterPojo.setStartTime(startTime);
			tpMasterPojo.setTotalDistance(totalDistance);
			tpMasterPojo.setTotalStops(totalStops);
			tpMasterPojo.setCreateUser(createUser);
			tpMasterPojo.setLog_audit_session(log_audit_session);
			tpMasterPojo.setLocid(request.getParameter("location"));

			if ("NULL".equalsIgnoreCase(request.getParameter("routeid"))) {
				tpMasterPojo.setRouteCode(IDGenerator.getPrimaryKeyID("transport_route",userLoggingsVo));
				tpMasterPojo.setCheck("NULL");
			} else {
				tpMasterPojo.setRouteCode(request.getParameter("routeid"));
			}

			String status = new TransportBD().insertRouteMasterDetails(tpMasterPojo,userLoggingsVo);
			
			

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("status", status);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : insertRouteMasterDetails Ending");
		return null;
	}

	public ActionForward editRouteMasterDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : editRouteMasterDetails Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_ROUTEMASTER);
			String args = "Modify Route Master";
			request.setAttribute("Route", args);
			

			/*tpMasterPojo.setRouteCode(request.getParameter("Code"));*/
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String loc=request.getParameter("locationid");
			String routecode = request.getParameter("routeIdlist");
			
			TransportPojo tpMasterPojo = new TransportPojo();
		
			
			TransportVo masterDetails = new TransportBD().editRouteMasterDetails(routecode,loc,userLoggingsVo);
			tpMasterPojo.setRouteCode(routecode);
			tpMasterPojo.setLocid(loc);
			
			List<TransportVo> Stages=new TransportBD().editRouteStages(tpMasterPojo,userLoggingsVo);
			
			
			System.out.println("stage size in Action class: "+Stages.size());
			
			List<TransportVo> stageDetails=new TransportBD().unmappedRouteStages(tpMasterPojo,userLoggingsVo);
			
			System.out.println("unmappedRouteStages size in Action class: "+Stages.size());
			request.setAttribute("masterDetails", masterDetails);
			
			request.setAttribute("StageDetails", stageDetails);
			
			request.setAttribute("unmappedStages", Stages);
			request.setAttribute("locationid", loc);
			// for popup //
			
			AddStageVO vo = new AddStageVO();

			vo.setStageName(request.getParameter("searchvalue"));
			
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			request.setAttribute("historysearch", request.getParameter("historysearch"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : editRouteMasterDetails Ending");
		return mapping.findForward(MessageConstants.ADDROUTESCREEN);
	}

	public ActionForward removeRouteMasterDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : removeRouteMasterDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
	        String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String routecode = request.getParameter("routeCode");
			String[] code=routecode.split(","); 
			String inactivereason = request.getParameter("inactivereason"); 
			String activereason = request.getParameter("activereason"); 
			String otherreason = request.getParameter("otherreason");
			String status = request.getParameter("status");
			
			TransportVo vo = new TransportVo();
			vo.setStatus(status);
			vo.setRouteId(code);
			vo.setInactivereason(inactivereason);
			vo.setActivereason(activereason);
			vo.setOtherreason(otherreason);
			vo.setLog_audit_session(log_audit_session);
			vo.setLoc_id(request.getParameter("locationid"));
			
		    String result = new TransportBD().removeRouteMasterDetails(vo,userLoggingsVo);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("status", result);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : removeRouteMasterDetails Ending");
		return null;
	}

	public ActionForward addRoute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addRoute Starting");

		try{
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String loc=request.getParameter("location");
			
			TransportForm routeForm = (TransportForm) form;

			routeForm.setCurrentUser(HelperClass.getCurrentUser(request));

			boolean status = new TransportBD().addRoute(routeForm);

			List<TransportVo> getTpMasterDetails = new TransportBD()
					.getTransportMasterDetails(loc,userLoggingsVo);

			if (status) {
				request.setAttribute("message", "Route Details Saved SuccessFully");
			} else {
				request.setAttribute("error",
						"Route Details not-Saved, Please try again");
			}

			request.setAttribute("getTpMasterDetails", getTpMasterDetails);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addRoute Ending");

		return mapping.findForward(MessageConstants.TRANSPORTMASTER);
	}

	public ActionForward checkrouteNo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : checkrouteNo Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			TransportPojo Pojo = new TransportPojo();

			Pojo.setRouteNo(request.getParameter("routeNo"));
			Pojo.setRouteCode(request.getParameter("routeid"));
			Pojo.setLocid(request.getParameter("locationid"));
			
			boolean status = new TransportBD().checkrouteNo(Pojo,userLoggingsVo);

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
				+ " Control in TransportAction : checkrouteNo  Ending");

		return null;
	}

	public ActionForward stopDetailsScreen(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : stopDetailsScreen Starting");
		try {
			String stopdetail = request.getParameter("stopdetails");

			String temp[] = stopdetail.split(",");

			TransportVo transportVo = new TransportVo();

			transportVo.setTotalStops(temp[0]);
			transportVo.setRouteCode(temp[1]);
			transportVo.setHalttime(temp[2]);

			System.out
					.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
							+ temp[2]);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);

			request.setAttribute("stopdetails", transportVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : stopDetailsScreen  Ending");

		return mapping.findForward(MessageConstants.STOPDETAILSSCREEN);

	}

	// Route master End

	public ActionForward getDriverDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getDriverDetails Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			VehicleDetailsVO vo=new VehicleDetailsVO();
			vo.setLocid(request.getParameter("locid"));
			
			ArrayList<DriverMsaterListVo> drivernamelist = new TransportBD().getDriverNamesDetails(vo,custdetails);

			JSONObject object = new JSONObject();
			object.put("drivernamelist", drivernamelist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getDriverDetails  Ending");

		return null;
	}

	public ActionForward getDriverEntireDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getDriverEntireDetails Starting");
		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			ArrayList<DriverMsaterListVo> driverlist = new TransportBD().getDriverEntireDetails(request.getParameter("driverid"),custdetails);
			
			System.out.println(request.getParameter("driverid"));

			JSONObject object = new JSONObject();
			object.put("driverlist", driverlist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getDriverEntireDetails  Ending");

		return null;
	}

	public ActionForward getDriverDetailsWhileUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getDriverDetailsWhileUpdate Starting");
		try {


			/*ArrayList<DriverMsaterListVo> drivernamelist = new TransportBD()
					.getDriverDetailsWhileUpdate(request
							.getParameter("vehicleIdlist"));*/

			ArrayList<DriverMsaterListVo> drivernamelist = new TransportBD().getDriverDetailsWhileUpdate(request.getParameter("vehicleCode"));


			JSONObject object = new JSONObject();
			object.put("drivernamelist", drivernamelist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getDriverDetailsWhileUpdate  Ending");

		return null;
	}

	public ActionForward getStopNames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getStopNames Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			ArrayList<StageMasterVo> list = new TransportBD().getStopNames(request.getParameter("searchTerm"),custdetails);

			JSONObject object = new JSONObject();
			object.put("stopslist", list);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getStopNames  Ending");

		return null;
	}

	public ActionForward getRouteDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getRouteDetails Starting");
		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String transferlocation = request.getParameter("value");
			System.out.println("transferlocation is "+transferlocation);
			
			ArrayList<TransportVo> routelist = new TransportBD().getRouteDetailsByLocation(transferlocation,pojo);

			JSONObject object = new JSONObject();
			object.put("routelist", routelist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getRouteDetails  Ending");

		return null;
	}
	
	
	// for new method getting  route data //
	
	
	public ActionForward getrouteDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getrouteDetails Starting");
		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			VehicleDetailsVO vo=new VehicleDetailsVO();
			vo.setLocid(request.getParameter("locid"));
			
			ArrayList<TransportVo> routelist = new TransportBD().getRouteDetailsByName(vo,pojo);

			JSONObject object = new JSONObject();
			object.put("routelist", routelist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getrouteDetails  Ending");

		return null;
	}

	public ActionForward GetRouteEntireDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : GetRouteEntireDetails Starting");
		try {
            UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
            
			ArrayList<TransportVo> routelist = new TransportBD().GetRouteEntireDetails(request.getParameter("routeid"),pojo);

			JSONObject object = new JSONObject();
			object.put("routelist", routelist);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : GetRouteEntireDetails  Ending");

		return null;
	}
	
	public ActionForward VehicleDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : VehicleDetailsXLS  Starting");
		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
		System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/VehicleDetailsXLSReport1.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			/*List<VehicleDetailsVO> List = new ArrayList<VehicleDetailsVO>();
			List = (List<VehicleDetailsVO>) request.getSession(false).getAttribute("vehiclelistdownload");
*/           
			String status="Y";
			ArrayList<VehicleDetailsVO> List = new TransportBD().getAllvehicleDetails(custdetails,status,"%%");
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					List);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/VehicleDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "vehicle Details Excel Report" };
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
					request.getRealPath("Reports/VehicleDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=VehicleDetailsXLSReport.xls");
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
				+ " Control in TransportAction : VehicleDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward VehicleDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TransportAction : VehicleDetailsPDFReport  Starting");

			try {
				UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

				System.out.println("downloading pdf");

				/*List<VehicleDetailsVO> Details = new ArrayList<VehicleDetailsVO>();
				Details = (List<VehicleDetailsVO>) request.getSession(false).getAttribute("vehiclelistdownload");
*/
				String status="Y";
				ArrayList<VehicleDetailsVO> Details = new TransportBD().getAllvehicleDetails(custdetails,status,"%%");
				
				
				
				
				String sourceFileName = request
						.getRealPath("Reports/VehicleDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						Details);

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
						+ "VehicleDetailsPDF - " + ".pdf\"");

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
					+ " Control in TransportAction : VehicleDetailsPDFReport  Ending");
			return null;

		}
	
	public ActionForward RouteDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : RouteDetailsXLS  Starting");
		
		try {
		System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			
			
			
			
			String sourceFileName = request
					.getRealPath("Reports/RouteDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			/*List<AddFeeVO> List = new ArrayList<AddFeeVO>();
			List = (List<AddFeeVO>) request.getSession(false)
					.getAttribute("feelistdownload");*/
			
			List<TransportVo> List=new ArrayList<TransportVo>();
			List = new TransportBD().getTransportMasterDetailsXLS();
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					List);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/RouteDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Route Details XLS Report" };
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
					request.getRealPath("Reports/RouteDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=RouteDetailsXLSReport.xls");
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
				+ " Control in TransportAction : RouteDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward RouteDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TransportAction : RouteDetailsPDFReport  Starting");

			try {

				System.out.println("downloading pdf");

				List<TransportVo> Details=new ArrayList<TransportVo>();
				Details = new TransportBD().getTransportMasterDetailsXLS();
				
				String sourceFileName = request
						.getRealPath("Reports/RouteDetailsPDFReport.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						Details);

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
						+ "RouteDetailsPDFReport - " + ".pdf\"");

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
					+ " Control in TransportAction : RouteDetailsPDFReport  Ending");
			return null;

		}
	
	
	
	public ActionForward downloadInsuranceFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : downloadInsuranceFile  Starting");
		
		
		try {
			
			
			String inspath = request.getParameter("Path");
			
			System.out.println("inspath "+inspath);
			
		/*	String filepath = new ParentExamdetailsBD().getfeedbackfilepath(inspath);*/
			
			System.out.println("filepath "+inspath);
			
			String fileName = "FileName";
			fileName=inspath;
			
			
			
			
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			File docFile = new File(request.getRealPath("/") + inspath);
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
				+ " Control in TransportAction : downloadInsuranceFile  Ending");
		return null;
	}
		
		
	public ActionForward downloadRcFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : downloadRcFile  Starting");
		
		
try {
			
			
			String rcpath = request.getParameter("Path");
			
			/*String filepath = new ParentExamdetailsBD().getfeedbackfilepath(rcpath);*/
			
			System.out.println("filepath "+rcpath);
			
			String fileName = "FileName";
			fileName=rcpath;
			
			
			
			
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			File docFile = new File(request.getRealPath("/") + rcpath);
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
				+ " Control in TransportAction : downloadRcFile  Ending");
		
		return null;
	}
		
		
		
		
	// separete transport add termdetails//
	
	public ActionForward addtSeparateTransportTermdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addtSeparateTransportTermdetails  Starting");

		String status = request.getParameter("result");

		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.SuccesstermMsg)) {
				request.setAttribute("successmessagediv",MessageConstants.SuccesstermMsg);
			}
			if (status.equalsIgnoreCase(MessageConstants.SuccesstermUpMsg)) {
				request.setAttribute("successmessagediv",MessageConstants.SuccesstermUpMsg);
			}
		}

		String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
		TermMasterDelegate delegates = new TermMasterDelegate();
		TermMasterVo vo = new TermMasterVo();
		vo.setAccid(accyear);

		try
		{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TERMSETUP);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
			
			request.setAttribute("current_academicYear",accYear);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			String locationId="%%";
			String date=HelperClass.getCurrentSqlDate().toString();
			
			String statuss = delegates.separatedateOverLapValidate(date,accyear,locationId,custdetails);
			if(statuss==null || statuss.equalsIgnoreCase(null) || statuss.equalsIgnoreCase("")){
				statuss=Integer.toString(HelperClass.getPastDateofAcademicYear(request,custdetails)+1);
			}
			
			TermMasterDelegate delegate = new TermMasterDelegate();
			
			String enddate=Integer.toString(HelperClass.getForDateofAcademicYear(request,custdetails));
		
			TermMasterVo acclist = delegate.getaccdetails(vo,custdetails);
			request.setAttribute("statuss", statuss);
			request.setAttribute("acclist", acclist);
			request.setAttribute("enddate", enddate);
			
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historylocId", request.getParameter("historylocId"));
		}

		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : addtSeparateTransportTermdetails Ending");

		return mapping.findForward(MessageConstants.ADD_SEPARATE_TRANSPORT_TERM);
	}
	
	// separate term for transport//
	

public ActionForward addtermSeparatefeedetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterAction : addtermSeparatefeedetails Starting");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);

			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			TermMasterVo vo = new TermMasterVo();
			vo.setTermid(request.getParameter("id"));
			vo.setTermname(request.getParameter("name"));
			vo.setDescription(request.getParameter("description"));
			vo.setStartdate(request.getParameter("startdate"));
			vo.setEnddate(request.getParameter("enddate"));
			vo.setAccyear(request.getParameter("academic_year"));
			vo.setLocationId(request.getParameter("locationId"));
			vo.setTransporttype(request.getParameter("transId"));
			vo.setNoofmonths(request.getParameter("noofmonths"));
			vo.setCreateuser(HelperClass.getCurrentUserID(request));
			vo.setLog_audit_session(log_audit_session);
			
			
			TermMasterDelegate delegate = new TermMasterDelegate();

			String result = delegate.addtermSeparatefeedetails(vo,userLoggingsVo);

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
				+ " Control in TermMasterAction : addtermSeparatefeedetails Ending");

		return null;

	}
	
	
	
	
public ActionForward deleteSeparateTermDetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response)

{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TermMasterAction : deleteSeparateTermDetails Starting");

	String result = "";
	UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
	String name = request.getParameter("getDataArray");//Name should be according to js name4
	String getDataArray[]=name.split(",");

	try {

		TermMasterVo vo = new TermMasterVo();
		vo.setGetDataArray(getDataArray);//-------------5
		vo.setLog_audit_session(log_audit_session);
		

		result = new TermMasterDelegate().deleteSeparateTermDetails(vo,userLoggingsVo);

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("jsonResponse", result);

		response.getWriter().println(jsonObject);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TermMasterAction : deleteSeparateTermDetails Ending");

	return null;

}
	
	// Separate term date validation


	
	
public ActionForward getTermnamecount(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)

{

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getTermnamecount Starting");

	String name = request.getParameter("name");
	String accyear = request.getParameter("accyear");
	String id = request.getParameter("id");
	String locationId=request.getParameter("locationId");

	boolean status = false;

	try {
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		TermMasterVo vo = new TermMasterVo();

		vo.setTermname(name);
		vo.setAccyear(accyear);
		vo.setTermid(id);
		vo.setLocationId(locationId);
      
		TermMasterDelegate delegate = new TermMasterDelegate();

		status = delegate.getTermnamecount(vo,userLoggingsVo);

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("message", status);

		response.getWriter().println(jsonObject);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getTermnamecount Ending");

	return null;

}
	
public ActionForward getStudentBusCard(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getStudentBusCard Starting");
	
	try{
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String stuId = request.getParameter("stuId");
		String classId = request.getParameter("ClassId");
		String sectionId = request.getParameter("sectionId");
		
		String term = request.getParameter("termId").trim();
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");
		String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
		String date = HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString());
		TransportVo obj = new TransportVo();
		obj.setStudentId(stuId);
		obj.setClassId(classId);
		obj.setSectionId(sectionId);
		obj.setTermId(term);
		obj.setLoc_id(locid);
		obj.setAcy_id(accyear);
		ArrayList<TransportVo> list = new TransportBD().getStudentBusCardDetails(obj);
		List<TermMasterVo> termList = new TermMasterDelegate().getTermDetails(accyear,locid,userLoggingsVo);
		String sourceFileName = null;
		String termName = null;
		
		
		if(term.equalsIgnoreCase(termList.get(0).getTermid()) ){
			termName = "Term1";
		sourceFileName = request
				.getRealPath("Reports/buspasscard.jrxml");
		}
		else if(term.equalsIgnoreCase(termList.get(1).getTermid().trim())){
			termName = "Term2";
			sourceFileName = request
					.getRealPath("Reports/buspasscard2term.jrxml");
		}
		else if(term.equalsIgnoreCase(termList.get(2).getTermid())){
			termName = "Term3";
			sourceFileName = request
					.getRealPath("Reports/buspasscard3term.jrxml");
		}
		JasperDesign design = JRXmlLoader.load(sourceFileName);

		JasperReport jasperreport = JasperCompileManager
				.compileReport(design);
		
		
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				list);

		String PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();

		String schName = res.getString("SchoolName");
		String schAddLine1 = res.getString("AddressLine1");

		Map parameters = new HashMap();
		parameters.put("locname", locName);
		parameters.put("date", date);
		byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters,beanColDataSource);

		response.setContentType("application/pdf");

		response.setContentLength(bytes.length);

		response.setHeader("Content-Disposition", "outline; filename=\""
				+ "buspasscard"+termName+".pdf\"");

		ServletOutputStream outstream = response.getOutputStream();

		outstream.write(bytes, 0, bytes.length);

		outstream.flush();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getStudentBusCard Ending");
	
	return null;
}
public ActionForward transportFeeSearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction    : transportFeeSearch Starting");
	
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

	String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
	UserDetailVO userDetailVO = (UserDetailVO) request
			.getSession(false).getAttribute("UserDetails");
	String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	String userType = userDetailVO.getUserNametype();
	String userCode = userDetailVO.getUserId();
	String location = "%%";
	try {
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTREQUESTORCANCEL);
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);

		List<StudentRegistrationVo> List = null;
		List = new StudentRegistrationDelegate().getStudentDetails1(
				userType, userCode, academic_year,schoolLocation,userLoggingsVo);
	if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
		academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
	}
	   String currentlocation =null;
	 if(schoolLocation.equalsIgnoreCase("all")){
		   schoolLocation="%%";
		   request.setAttribute("currentlocation", "All");
		   request.setAttribute("locationId",schoolLocation);
	   }
	  else{
		   currentlocation =new ExamDetailsBD().getlocationname(schoolLocation,userLoggingsVo);
		   request.setAttribute("currentlocation", currentlocation);
		   request.setAttribute("locationId",schoolLocation);
	   }
	 
	 request.setAttribute("accyear",academic_year);
	
	ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
	request.setAttribute("locationList", locationList);

	ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
	request.setAttribute("AccYearList", accYearList);


	List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
	request.setAttribute("classlist", classlist);
	list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location,userLoggingsVo);
	request.setAttribute("studentList", list);

	request.setAttribute("historylocId", request.getParameter("historylocId"));
    request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
    request.setAttribute("historyclassname", request.getParameter("historyclassname"));
    request.setAttribute("historysectionid", request.getParameter("historysectionid"));
    request.setAttribute("historysearch", request.getParameter("historysearch"));
    request.setAttribute("historyistransport", request.getParameter("historyistransport"));

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : transportFeeSearch Ending");

	return mapping.findForward(MessageConstants.TRANSPORT_FEE_STUDENT_LIST);
}
public ActionForward getStudentSearchByTransport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
return null;	
}
public ActionForward settransportstudentwise(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction    : settransportstudentwise Starting");
	
	
	try{
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTREQUESTORCANCEL);
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
		
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String studentid = request.getParameter("student_id");
		 String loc_id = request.getParameter("loc_id");
		 String class_id = request.getParameter("classid");
		 String sectionid = request.getParameter("sectionid");
		 String acy_id= request.getParameter("acy_id");
		 String istransport= request.getParameter("istransport");
		 String isTrans = request.getParameter("istransport");
		 String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
		  TransportVo tvo = new TransportVo();
		  tvo.setStudentId(studentid);
		  tvo.setLoc_id(loc_id);
		  tvo.setClassId(class_id);
		  tvo.setSectionId(sectionid);
		  tvo.setAcy_id(acy_id);
		  tvo.setStatus(istransport);
		
		  TransportVo  tvolist = new TransportBD().gettransportdetailsstudentwise(tvo,userLoggingsVo);
	  
	        String currentlocation =new ExamDetailsBD().getlocationname(loc_id,userLoggingsVo);
			String accyname =new ExamDetailsBD().getaccyName(acy_id,userLoggingsVo);
			
			request.setAttribute("currentlocation", currentlocation);
			request.setAttribute("accyname",accyname);
			request.setAttribute("accyearid",acy_id);
			request.setAttribute("locid",loc_id);
			request.setAttribute("stuid",studentid);
	        request.setAttribute("studentDetailsList",tvolist);
	        ArrayList<TransportVo>  termtransport = new TransportBD().settranporttermdetailsforstudent(tvo,userLoggingsVo);
	        request.setAttribute("termdetailslist", termtransport);
	       
	        ArrayList<TransportVo> monthList = new TransportBD().getMonthList(acy_id,userLoggingsVo,loc_id);
	        request.setAttribute("monthList", monthList);
	        
	        request.setAttribute("historylocId", request.getParameter("historylocId"));
	        request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
	        request.setAttribute("historyclassname", request.getParameter("historyclassname"));
	        request.setAttribute("historysectionid", request.getParameter("historysectionid"));
	        request.setAttribute("historysearch", request.getParameter("historysearch"));
	        request.setAttribute("historyistransport", request.getParameter("istransport"));
	        
	        if(isTrans.equalsIgnoreCase("N")){
	        	request.setAttribute("heading","Transport Request");
	        }else{
	        	request.setAttribute("heading","Transport Request/Cancel");
	        }
	        
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : settransportstudentwise Ending");
	
	
        return mapping.findForward(MessageConstants.SET_TRANSPORT_DETAILS_FOR_INDIVIDUAL_STUDENT);
}
public ActionForward getRouteNameList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction    : getRouteNameList Starting");
	try
	{
	 UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	 
	 String loc_id =request.getParameter("locationid");
	 TransportVo tvo = new TransportVo();
	 tvo.setLoc_id(loc_id);

	 ArrayList<TransportVo>  routelist = new TransportBD().getRouteNamelist(tvo,userLoggingsVo);
	
	 JSONObject jsonObject = new JSONObject(routelist);
	 jsonObject.accumulate("routelist", routelist);
	 response.getWriter().print(jsonObject);
	 
	
	}
	 catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getRouteNameList Ending");
	
	return null;
}


public ActionForward getstoplist(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction    : getstoplist Starting");
	
	
	try
	{
	 UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		 
	 String route_id =request.getParameter("routeid");
	 String locId =request.getParameter("locId");
	
	 TransportVo tvo = new TransportVo();
	 tvo.setRouteCode(route_id);
	 tvo.setLoc_id(locId); 
	 
	 ArrayList<TransportVo>  stoplist = new TransportBD().getstoplist(tvo,userLoggingsVo);
	 System.out.println("stop Name List:" +stoplist);
	 JSONObject jsonObject = new JSONObject(stoplist);
	 jsonObject.accumulate("stoplist", stoplist);
	 response.getWriter().print(jsonObject);
	 
	
	}
	 catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getstoplist Ending");
	
	return null;
}
public ActionForward getamount(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction    : getamount Starting");
	
	
	try
	{
	 UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
	  String stageid =request.getParameter("stageid");
	  String accId =request.getParameter("accId"); 
	  String locId =request.getParameter("locId");
	  
	  TransportVo tvo = new TransportVo();
	  tvo.setStage_id(stageid);
	  tvo.setAcy_id(accId);
	  tvo.setLoc_id(locId);
	  
	String  getamount = new TransportBD().getamountandstatus(tvo,userLoggingsVo);
	 
	  JSONObject obj=new JSONObject();
	  obj.put("getamount", getamount);
	  response.getWriter().print(obj);
	}
	 catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getamount Ending");
	return null;
}
public ActionForward savetransportrequest(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : savetransportrequest Starting");
	try{
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		
		TransportPojo pojo = new TransportPojo();
		pojo.setStuid(request.getParameter("stuid"));
		pojo.setAccyear(request.getParameter("accyear"));
		pojo.setLocid(request.getParameter("locid"));
		pojo.setRouteCode(request.getParameter("routeid"));
		pojo.setStageid(request.getParameter("stageid"));
		pojo.setStmonth(request.getParameter("stmonths"));
		pojo.setEndmonth(request.getParameter("endmonth"));
		pojo.setMonthCount(request.getParameter("monthCount"));
		pojo.setVehicleType(request.getParameter("vehicletype"));
		pojo.setLog_audit_session(log_audit_session);
	
		
		String  savetransport = new TransportBD().savetransportrequest(pojo,custdetails);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("status",savetransport);
		response.getWriter().print(jsonobj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : savetransportrequest Ending");
	 return null;
}
public ActionForward getStudentListByLoc(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getStudentListByLoc Starting");
	
	String accYear = request.getParameter("accyear");
	String locationId = request.getParameter("location");
	
		
	List<StudentRegistrationVo> list= null;
	
	try {
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			if(locationId.equalsIgnoreCase("all") && !(accYear.equalsIgnoreCase("all"))){
			locationId="%%";
			list = new TransportBD().searchAllAcademicYearDetailsTrans(locationId,accYear,custdetails);
		}
		else if(accYear.equalsIgnoreCase("all") && !(locationId.equalsIgnoreCase("all"))){
			accYear="%%";
			list = new TransportBD().searchAllAcademicYearDetailsTrans(locationId,accYear,custdetails);
		}
		else if(accYear.equalsIgnoreCase("all") && locationId.equalsIgnoreCase("all")){
			locationId="%%";
			accYear="%%";
			list = new TransportBD().searchAllAcademicYearDetailsTrans(locationId,accYear,custdetails);
		}else{
			list = new TransportBD().searchAllAcademicYearDetailsTrans(locationId,accYear,custdetails);
		}
		
		request.setAttribute("SearchList", list);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("SearchList", list);
		response.getWriter().print(jsonobj);

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getStudentListByLoc Ending");

	return null;
}
public ActionForward getStudentListByClass(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getStudentListByClass Starting");

	try {

		String locationid = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String classname = request.getParameter("classId");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		list = new TransportBD().getStudentListByClass(locationid,accyear,classname);
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getClassWiseList", list);

			response.getWriter().print(jsonobj);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	e.printStackTrace();
}


	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getStudentListByClass Ending");

	return null;

}
public ActionForward getStudentListBySection(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())

			+ " Control in TransportAction : getStudentListBySection Starting");
	
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

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		list = new TransportBD().getStudentListBySection(locationid,accyear,classname,sectionid);
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
			+ " Control in TransportAction : getStudentListBySection Ending");

	return null;
}

public ActionForward getStudentSearchByList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : getStudentSearchByList Starting");
	
	List<StudentRegistrationVo> list = null;
	
	try {
		
		String locationid = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String classname = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");

		String searchTerm = request.getParameter("searchname".trim());

		System.out.println(locationid+" "+accyear+" "+classname+" "+sectionid+" "+searchTerm);
		
		if(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")){
			
			list = new TransportBD().getStudentSearchByList(searchTerm);
		}
		else if(locationid.equalsIgnoreCase("all") && !(accyear.equalsIgnoreCase("all"))){
			
			list =  new TransportBD().getStudentSearchListByAccYear(searchTerm,accyear);
		}
		else if(accyear.equalsIgnoreCase("all") && !(locationid.equalsIgnoreCase("all"))){
			
			list = new TransportBD().getStudentSearchListByLocation(searchTerm,locationid);
		}
		else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")) && classname.equalsIgnoreCase("all")){
			
			list = new TransportBD().getStudentSearchByFilter(searchTerm,locationid,accyear,classname);
		}
		else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all")) && sectionid.equalsIgnoreCase("all")){
			
			list = new TransportBD().getStudentSearchByClass(searchTerm,locationid,accyear,classname);
		}
		else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all") && sectionid.equalsIgnoreCase("all"))){
			
			list = new TransportBD().getStudentSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
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
			+ " Control in TransportAction : getStudentSearchByList Ending");

	return null;
}
public ActionForward waivedOfftransportrequest(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : waivedOfftransportrequest Starting");
	try{
		 UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		TransportPojo pojo = new TransportPojo();
		pojo.setStuid(request.getParameter("stuid"));
		pojo.setAccyear(request.getParameter("accyear"));
		pojo.setLocid(request.getParameter("locid"));
		pojo.setRouteCode(request.getParameter("routeid"));
		pojo.setStageid(request.getParameter("stageid"));
		pojo.setLog_audit_session(log_audit_session);
	
		
		String  savetransport = new TransportBD().waivedOfftransportrequest(pojo,userLoggingsVo);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("status",savetransport);
		response.getWriter().print(jsonobj);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : waivedOfftransportrequest Ending");
	 return null;
}
public ActionForward addTransportType(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : addTransportType Starting");
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
			LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTCATEGORY);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
	try {
		String arg = "Add New Transport Type";
		request.setAttribute("pageName", arg);

		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : addTransportType Ending");

	return mapping.findForward(MessageConstants.ADD_TRANSPORT_TYPE);
}

	public ActionForward insertVehicleType(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : insertVehicleType Starting");
	try{
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		TransportCategoryForm tform = new TransportCategoryForm();
		
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		String createCode = HelperClass.getCurrentUserID(request);
		String s1=null;
		
		String vehicletype=request.getParameter("vehicleType".trim());
		String desc=request.getParameter("desc".trim());
		String veh_id = request.getParameter("upd_vehicle_id");
		s1=veh_id;//For insertion s1 is empty.
		
		if (veh_id == "" || veh_id.equalsIgnoreCase("") || veh_id == null) 
		{
			 s1 = IDGenerator.getPrimaryKeyID("transport_typedetails",custdetails);
		}//This is for inserting.
	
		tform.setVehicleType(vehicletype);
		tform.setDescription(desc);
		tform.setVehicle_id(s1);
		tform.setUpdateid(veh_id);
		tform.setLog_audit_session(log_audit_session);
		
		TransportBD details=new TransportBD();
		
		String result = details.insertVehicleType(tform,createCode,custdetails);
		
		
		JSONObject object = new JSONObject();
		object.put("status", result);//Here status in one key having the value of result.
		response.getWriter().print(object);//Always pass the refrence of JSON Object.
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : insertVehicleType Ending");
	
	return null;
}

	public ActionForward validateVehicleType(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction: validateVehicleType Starting");

	try {
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String vehicletype = request.getParameter("completeurl");//Here the name is forwading from data passes in $.ajax() of  AddTransportDetails.js.
		String id = request.getParameter("id");
		
		VehicleTypeVo vehicleadd = new VehicleTypeVo();
		vehicleadd.setVehicleType(vehicletype);
		vehicleadd.setVehicle_id(id);
		String vehname_available = new TransportBD().validateVehicleType(vehicleadd,custdetails);
		JSONObject object = new JSONObject();
		object.put("vehi_available", vehname_available);
		response.getWriter().print(object);
		

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction: validateVehicleType Ending");
	return null;
}

	public ActionForward edittransporttype(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : edittransporttype Starting");

	try {
		String args = "Modify Transport Type";
		request.setAttribute("pageName", args);
		
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTCATEGORY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String transportCatergory = request.getParameter("vehicleid");
		
		VehicleTypeVo transportdetails = new TransportBD().edittransporttype(transportCatergory,custdetails);
		
		request.setAttribute("transportTypedetails", transportdetails);//Here the name in "" equals the name in jsp.
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : edittransporttype Ending");
	
	return mapping.findForward(MessageConstants.ADD_TRANSPORT_TYPE);
}

	public ActionForward deleteVehicleType(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportAction : deleteVehicleType Starting");
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String[] vehiclecode = request.getParameter("vehicleid").split(","); 
		String inactivereason = request.getParameter("inactivereason");
		String activereason = request.getParameter("activereason");
		String otherreason = request.getParameter("otherreason"); 
		String status = request.getParameter("status");
        String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
        
        TransportVo vo=new TransportVo();
        
        vo.setVehicleIds(vehiclecode);
        vo.setInactivereason(inactivereason);
        vo.setActivereason(activereason);
        vo.setOtherreason(otherreason);
        vo.setStatus(status);
        vo.setLog_audit_session(log_audit_session);
        
		String result = new TransportBD().deleteVehicleType(vo,custdetails);
		
		request.setAttribute("vehicleDetails", result); 

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
			+ " Control in TransportAction : deleteVehicleType Ending");

	return null;
}
	public ActionForward printStudentBusCard(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : printStudentBusCard Starting");
		try{
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String printFileName = null;
			String stuId = request.getParameter("stuId");
			String classId = request.getParameter("ClassId");
			String sectionId = request.getParameter("sectionId");
			String term = request.getParameter("termId").trim();
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			
			String date = HelperClass.convertDatabaseToUI(HelperClass.getCurrentSqlDate().toString());
			TransportVo obj = new TransportVo();
			obj.setStudentId(stuId);
			obj.setClassId(classId);
			obj.setSectionId(sectionId);
			obj.setTermId(term);
			obj.setLoc_id(locid);
			obj.setAcy_id(accyear);
			ArrayList<TransportVo> list = new TransportBD().getStudentBusCardDetails(obj);
			List<TermMasterVo> termList = new TermMasterDelegate().getTermDetails(accyear,locid,userLoggingsVo);
			String sourceFileName = null;
			String termName = null;
			
			
			if(term.equalsIgnoreCase(termList.get(0).getTermid()) ){
				termName = "Term1";
			sourceFileName = request
					.getRealPath("Reports/buspasscard.jrxml");
			}
			else if(term.equalsIgnoreCase(termList.get(1).getTermid().trim())){
				termName = "Term2";
				sourceFileName = request.getRealPath("Reports/buspasscard2term.jrxml");
			}
			else if(term.equalsIgnoreCase(termList.get(2).getTermid())){
				termName = "Term3";
				sourceFileName = request.getRealPath("Reports/buspasscard3term.jrxml");
			}
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

			String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("locname", locName);
			parameters.put("date", date);
			
			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
	
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
			
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setVisible(true);
			
			PrinterJob job = PrinterJob.getPrinterJob();
			   int selectedService = 0;
			   selectedService = 0;
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
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : printStudentBusCard Ending");
		
		return mapping.findForward(MessageConstants.STUDENTBUSCARD);
	}
	public ActionForward addStageWiseAmountCollection(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addStageWiseAmountCollection Starting");
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.STAGE_WISE_AMMOUNT_COLLECTION);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String schoolLocation = (String) request.getSession(false)
					.getAttribute("current_schoolLocation");
			System.out.println("current school Location:" + schoolLocation);
			if (schoolLocation.equalsIgnoreCase("all")) {
				schoolLocation = "%%";
			}
			String currentaccyear = request.getSession(false).getAttribute("current_academicYear").toString();
			String accyearid = request.getParameter("accyear");
			String location = request.getParameter("location");

			
			// For setting Academic year based on academic year id
			String accyear = request.getParameter("accyear");
			String accyname = new ExamDetailsBD().getaccyName(accyear,userLoggingsVo);
			request.setAttribute("accyName", accyname);
			request.setAttribute("accyear", accyear);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("accYearList", accYearList);
			// getting locname
			String currentlocation = new ExamDetailsBD().getlocationname(location,userLoggingsVo);
			request.setAttribute("locName", currentlocation);
			request.setAttribute("locid", location);
			
			LocationVO custSchoolInfo=HelperClass.getCustSchoolInfo(userLoggingsVo,location);
			
			request.setAttribute("printLocationName", custSchoolInfo.getSchname());
			request.setAttribute("custSchoolAddres", custSchoolInfo.getAddress());
			request.setAttribute("printAddress2", custSchoolInfo.getBranchAddress());
			
			
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : addStageWiseAmountCollection Ending");
		return mapping.findForward(MessageConstants.ADD_STAGE_WISE_AMMOUT_COLLECT);
	}
	
	public ActionForward transportFeeConcession(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in TransportAction : transportFeeConcession Starting");
		
		try {

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_FEECONCESSION);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in TransportAction : transportFeeConcession Ending");

		return mapping.findForward(MessageConstants.TRANSPORT_FEE_CONCESSION);
	}
	
	
	public ActionForward routeMasterSettingBySearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction: routeMasterSettingBySearch settings");
		try
		{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_ROUTEMASTER);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String loc=request.getParameter("location");
			String searchvalue=request.getParameter("searchvalue");
			String status=request.getParameter("status");
			
			if(searchvalue==null){
				searchvalue="";
			}else{
				searchvalue=searchvalue.trim();
			}
			
			String schoolname=HelperClass.getSchoolName(loc,custdetails);
			
			TransportBD obj = new TransportBD();
			List<TransportVo> list = new ArrayList<TransportVo>();
			
			TransportVo vo=new TransportVo();
			vo.setLoc_id(loc);
			vo.setSearchName(searchvalue);
			vo.setStatus(status);
			
			list = obj.routeMasterSettingBySearch(vo,custdetails);
			
			request.setAttribute("schoolname", schoolname);
			request.setAttribute("locationid", loc);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("list", list);
			response.getWriter().print(jsonobj);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction: routeMasterSettingBySearch Ending");

		return null;
	}
	
	public ActionForward transportCategoryListByStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction: transportCategoryListByStatus settings");
		try
		{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTCATEGORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String searchvalue=request.getParameter("searchvalue");
			String status=request.getParameter("status");
			
			if(searchvalue==null){
				searchvalue="";
			}else{
				searchvalue=searchvalue.trim();
			}
			
			
			TransportBD obj = new TransportBD();
			List<TransportVo> list = new ArrayList<TransportVo>();
			
			TransportVo vo=new TransportVo();
			vo.setSearchName(searchvalue);
			vo.setStatus(status);
			
			list = obj.transportCategoryListByStatus(vo,custdetails);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("list", list);
			response.getWriter().print(jsonobj);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction: transportCategoryListByStatus Ending");

		return null;
	}
	
	
	public ActionForward getListOfVehicleDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction: getListOfVehicleDetails settings");
		try
		{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTCATEGORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			String status=request.getParameter("status");
			String locId=request.getParameter("locationname");
			
			if(locId.equalsIgnoreCase("all")){
				locId="%%";
			}
			
			
			TransportBD obj = new TransportBD();
			List<VehicleDetailsVO> getvehiclelist = new ArrayList<VehicleDetailsVO>();
			
			TransportVo vo=new TransportVo();
			vo.setStatus(status);
			
			getvehiclelist= obj.getAllvehicleDetails(userLoggingsVo,status,locId);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("getvehiclelist", getvehiclelist);
			response.getWriter().print(jsonobj);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction: getListOfVehicleDetails Ending");

		return null;
	}
	
	public ActionForward saveTransportConcession(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in TransportAction : saveTransportConcession Starting");
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String studentIdNo = request.getParameter("studentIdNo");
			String academicYear = request.getParameter("AcademicYearFor");
			String classId = request.getParameter("classId");
			String term = request.getParameter("term");
			String consfeeamount = request.getParameter("consfeeamount");
			String conType = request.getParameter("conType");

			StudentConcessionVo vo = new StudentConcessionVo();

			vo.setAcademicYear(academicYear);
			vo.setStudentId(studentIdNo);
			vo.setClassId(classId);
			vo.setConcessionAmount(consfeeamount);
			vo.setTermcode(term);
			vo.setContype(conType);
			vo.setFeecode("");

			String status = new TransportBD().saveTransportConcession(vo,userLoggingsVo);
			JSONObject obj = new JSONObject();
			obj.put("status", status);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction: saveTransportConcession Ending");

		return null;
	}
	
	public ActionForward deleteTranportConcessionDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in TransportAction : deleteTranportConcessionDetails Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			String getDataArray[] = request.getParameter("getDataArray").split(",");
			String accYearArray[] = request.getParameter("accYearArray").split(",");

			AddFeeVO vo = new AddFeeVO();

			vo.setGetDataArray(getDataArray);
			vo.setAccYearArray(accYearArray);
			vo.setLog_audit_session(log_audit_session);

			String status = new TransportDaoImpl().deleteTranportConcessionDetails(vo,userLoggingsVo);
			JSONObject obj = new JSONObject();
			obj.put("status", status);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in TransportAction : deleteTranportConcessionDetails Ending");

		return null;
	}
	
	public ActionForward getVehicleTypeList(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in TransportAction : getVehicleTypeList Starting");
		
		try
		{
		 UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		 
		 TransportVo tvo = new TransportVo();
		 
		 ArrayList<TransportVo>  routelist = new TransportDaoImpl().getVehicleTypeList(tvo,userLoggingsVo);
		
		 JSONObject jsonObject = new JSONObject(routelist);
		 jsonObject.accumulate("routelist", routelist);
		 response.getWriter().print(jsonObject);
		 
		
		}
		 catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction: getVehicleTypeList Ending");
		return null;
	}
	
	
	public ActionForward valideDriverCode(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : valideDriverCode Starting");
		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String drivername=request.getParameter("drivername");
			String locId=request.getParameter("locId");
			
			 String status = new TransportBD().valideDriverCode(drivername,locId,custdetails);
			
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
				+ " Control in TransportAction : valideDriverCode  Ending");

		return null;
	}
	
}






	

	


