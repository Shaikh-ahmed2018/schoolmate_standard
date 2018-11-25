package com.centris.campus.actions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.daoImpl.SpecializationDaoImpl;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.SpecializationBD;
import com.centris.campus.forms.SpecializationForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.SpecializationVo;

public class SpecializationAction extends DispatchAction{
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static final Logger logger = Logger
			.getLogger(SpecializationAction.class);

	private static String ImageName = res.getString("ImageName");
	
	
	public ActionForward insertSpecialization(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : insertSpecialization Starting");
		try{
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			SpecializationForm spec= new SpecializationForm();
			String createCode = HelperClass.getCurrentUserID(request);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String streamId=request.getParameter("stream");
			String classId=request.getParameter("className");
			String specName=request.getParameter("specialization");
			String specId=request.getParameter("status");
			String locationId=request.getParameter("locationId");
			
			System.out.println(specId);
			
			spec.setStream_Id(streamId);
			spec.setClass_Id(classId);
			spec.setSpec_Name(specName);
			spec.setSpec_Id(specId);
			spec.setCreate_User(createCode);
			spec.setLocationId(locationId);
			spec.setLog_audit_session(log_audit_session);
			
			
			String result = new SpecializationBD().insertSpecialization(spec,specId,userLoggingsVo);
			 
			JSONObject object = new JSONObject();
			object.put("status", result);
			response.getWriter().print(object);
			
			
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SpecializationAction : insertSpecialization  Ending");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public ActionForward editSpecialization(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : editSpecialization Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SPECIALIZATIONDETAILS);

			String title = "Modify Specialization";
			request.setAttribute("title", title);
			
			String locId=request.getParameter("locId");
			String streamId=request.getParameter("streamId"); 
			String classname=request.getParameter("classname");
			String status=request.getParameter("status");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			
			String edit = request.getParameter("specId");

			SpecializationVo edit_list = new SpecializationBD().editSpecialization(edit,custdetails);

			request.setAttribute("editlist",edit_list);

			request.setAttribute("locId",locId);
			request.setAttribute("streamId",streamId);
			request.setAttribute("classname",classname);
			request.setAttribute("status",status);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : editSpecialization Ending");

		 return mapping.findForward(MessageConstants.ADD_SPECIALIZATION);   
	}
	
	public ActionForward deleteSpec(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : deleteSpec Starting");
		@SuppressWarnings("unused")
		String username = null;

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);

			SpecializationVo vo=new SpecializationVo();
			vo.setSpecIds(request.getParameter("specid").split(","));
			vo.setInactiveReason(request.getParameter("inactivereason"));
			vo.setActiveReason(request.getParameter("activereason"));
			vo.setOtherReason(request.getParameter("otherreason"));
			vo.setStatus(request.getParameter("status"));
			vo.setLog_audit_session((String) request.getSession(false).getAttribute("log_audit_session"));
			
			
			String result = new SpecializationBD().deleteSpec(vo,userLoggingsVo);
			
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
				+ " Control in SpecializationAction : deleteSpec Ending");
		return null;
	}
	

	public ActionForward getSpecializationOnClassBased(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : getSpecializationOnClassBased Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String classId = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			//System.out.println("Specilization Action classId: "+request.getParameter("classId")+request.getParameter("locationId"));
			
			List<SpecializationVo> specializationList = new SpecializationBD().getSpecializationOnClassBased(classId+","+locationId,userLoggingsVo);
			//System.out.println("getSpecializationOnClassBased"+specializationList);
			 
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("jsonResponse", specializationList);
			response.getWriter().print(jsonobj);
			
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : getSpecializationOnClassBased Ending");

		 return null;   
	}
	
	public ActionForward validateSpecialization(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : validateSpecialization: Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String classId = request.getParameter("classId");
			String specialization = request.getParameter("specialization");
			String locationId=request.getParameter("locationId");

			SpecializationForm form1= new SpecializationForm();

			form1.setClass_Id(classId);
			form1.setSpec_Name(specialization);
			form1.setLocationId(locationId);
			
			
			String spec_available = new SpecializationBD().validateSpecialization(form1,userLoggingsVo);
			
			JSONObject object = new JSONObject();
			object.put("status", spec_available);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : validateSpecialization: Ending");
		return null;
	}
	
	public ActionForward getSpecializationOnClassWithoutLocId (ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : getSpecializationOnClassWithoutLocId Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String classId = request.getParameter("classId");
			List<SpecializationVo> specializationList = new SpecializationBD().getSpecializationOnClassWithoutLocId(classId);
			
			 JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("jsonResponse", specializationList);
				
				response.getWriter().print(jsonobj);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : getSpecializationOnClassWithoutLocId Ending");
		 return null;   
	}
	
	public ActionForward validateSpecializationNameByLocationstreamAndClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : validateSpecializationNameByLocationstreamAndClass: Starting");

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String classId = request.getParameter("classId");
			String specialization = request.getParameter("specialization");
			String locationId=request.getParameter("locationId");
			String streamId=request.getParameter("streamId");

			SpecializationVo vo=new SpecializationVo();
			vo.setLocationId(locationId);
			vo.setStream_Id(streamId);
			vo.setClass_Id(classId);
			vo.setSpec_Id(specialization);
		
			
			String spec_available = new SpecializationDaoImpl().validateSpecializationNameByLocationstreamAndClass(vo,userLoggingsVo);
			
			JSONObject object = new JSONObject();
			object.put("status", spec_available);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationAction : validateSpecializationNameByLocationstreamAndClass: Ending");
		return null;
	}

}
