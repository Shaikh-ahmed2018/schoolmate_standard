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
import com.centris.campus.daoImpl.ReligionCasteCasteCategoryDaoImpl;
import com.centris.campus.delegate.ReligionCasteCasteCategoryBD;
import com.centris.campus.forms.ReligionCasteCasteCategoryForm;
import com.centris.campus.pojo.ReligionCasteCasteCategoryPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;

public class ReligionCasteCastCategory extends DispatchAction {

	private static final Logger logger = Logger
			.getLogger(ReligionCasteCastCategory.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	// Religion Caste CasteCategory

	public ActionForward addReligion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : addReligion Starting");

		try {
			String title ="New Religion";
			request.setAttribute("title", title);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_RELIGION);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : addReligion Ending");

		return mapping.findForward(MessageConstants.ADD_RELIGION);
	}

	public ActionForward addCaste(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : addCaste Starting");

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String title ="New Caste";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTE);

			request.setAttribute("religiondetails",
					new ReligionCasteCasteCategoryBD().getReligionDetails(userLoggingsVo));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : addCaste Ending");

		return mapping.findForward(MessageConstants.ADD_CASTE);
	}

	public ActionForward addCasteCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : addCasteCatogory Starting");

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String title ="New Caste Category";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTECATEGORY);
			
			request.setAttribute("religiondetails",new ReligionCasteCasteCategoryBD().getReligionDetails(userLoggingsVo));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : addCasteCatogory Ending");

		return mapping.findForward(MessageConstants.ADD_CASTE_CATEGORY);
	}

	public ActionForward insertReligion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : insertReligion Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			ReligionCasteCasteCategoryForm detailsForm = new ReligionCasteCasteCategoryForm();

			String createCode = HelperClass.getCurrentUserID(request);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			String religionId = request.getParameter("religionId");
			String religion = request.getParameter("religion");
			String hiddenreligion=request.getParameter("hiddenreligion");

			////.out.println("hiddenreligion : " + hiddenreligion);

			detailsForm.setReligionId(religionId);
			detailsForm.setReligion(religion);
			detailsForm.setHiddenreligion(hiddenreligion);
			detailsForm.setCreateUser(createCode);
			detailsForm.setLog_audit_session(log_audit_session);
			

			String result = new ReligionCasteCasteCategoryBD().insertReligion(detailsForm,userLoggingsVo);
			////.out.println("Action class Result: " + result);

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
				+ " Control in ReligionCasteCastCategory : insertReligion  Ending");
		return null;

	}

	public ActionForward editReligion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : editReligion  Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String title = "Modify Religion";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_RELIGION);

			String religionId = request.getParameter("religionId");
			////.out.println("Action class Religion Id: "
					//+ request.getParameter("religionId"));
			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();
			detailsPojo.setReligionId(religionId);
			detailsPojo.setCustid(userLoggingsVo.getCustId());
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();
			ReligionCasteCasteCategoryVo ckeck = detailsBD.getSingleReligion(detailsPojo,userLoggingsVo);
			////.out.println("action class getSingleReligion: "+ ckeck.getReligion());
			request.setAttribute("religionList", ckeck);
			
			request.setAttribute("historystatus", request.getParameter("status"));
			request.setAttribute("historysearchname", request.getParameter("searchname"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : editReligion   Ending");

		return mapping.findForward(MessageConstants.ADD_RELIGION);

	}

	public ActionForward ActiveAndInActiveReligion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : ActiveAndInActiveReligion  Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			String religionId[] = request.getParameter("religionList").split(","); 
			String inactivereason= request.getParameter("inactivereason");  
			String activereason= request.getParameter("activereason"); 
			String status= request.getParameter("status");  
			String otherreason= request.getParameter("otherreason");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();

			detailsPojo.setReligionIdArray(religionId);
			detailsPojo.setInactiveReason(inactivereason);
			detailsPojo.setActiveReason(activereason); 
			detailsPojo.setStatus(status);
			detailsPojo.setOtherReason(otherreason);
			detailsPojo.setLog_audit_session(log_audit_session);
			
			
			String ckeck = new ReligionCasteCasteCategoryBD().deleteReligion(detailsPojo,userLoggingsVo);

			JSONObject json = new JSONObject();
			json.put("status", ckeck);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : ActiveAndInActiveReligion   Ending");
		return null;
	}

	// deleteCaste caste 
	public ActionForward deleteCaste(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : deleteCaste  Starting");

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);

			String casteId[] = request.getParameter("casteList").split(",");
			String inactivereason= request.getParameter("inactivereason");  
			String activereason= request.getParameter("activereason"); 
			String status= request.getParameter("status");  
			String otherreason= request.getParameter("otherreason");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();
			detailsPojo.setCasteIdArray(casteId);
			detailsPojo.setInactiveReason(inactivereason);
			detailsPojo.setActiveReason(activereason); 
			detailsPojo.setStatus(status);
			detailsPojo.setOtherReason(otherreason);
			detailsPojo.setLog_audit_session(log_audit_session);
			
			
			String ckeck = new ReligionCasteCasteCategoryBD().deleteCaste(detailsPojo,userLoggingsVo); 
			
			JSONObject json = new JSONObject();
			json.put("status", ckeck);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : deleteCaste   Ending");

		return null;

	}

	public ActionForward insertCaste(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : insertCaste Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			ReligionCasteCasteCategoryForm detailsForm = new ReligionCasteCasteCategoryForm();

			String createCode = HelperClass.getCurrentUserID(request);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			String casteId = request.getParameter("religionId");
			String caste = request.getParameter("religion");
			String main_religion = request.getParameter("main_religion");
			String hiddencaste = request.getParameter("hiddencaste");
			////.out.println("hiddencaste :::"+hiddencaste);
			
			detailsForm.setCasteId(casteId);
			detailsForm.setCaste(caste);
			detailsForm.setCreateUser(createCode);
			detailsForm.setHiddencaste(hiddencaste);
			detailsForm.setMain_religion(main_religion);
			detailsForm.setLog_audit_session(log_audit_session);
			
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			String result = detailsBD.insertCaste(detailsForm,userLoggingsVo);

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
				+ " Control in ReligionCasteCastCategory : insertCaste  Ending");
		return null;

	}

	public ActionForward editCaste(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : editCaste  Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String title = "Modify Caste";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTE);

			String casteId = request.getParameter("casteId");

			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();
			detailsPojo.setCasteId(casteId);
			
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();
			ReligionCasteCasteCategoryVo ckeck = detailsBD.getSingleCaste(detailsPojo,userLoggingsVo);
			
			request.setAttribute("religiondetails",new ReligionCasteCasteCategoryBD().getReligionDetails(userLoggingsVo));

			request.setAttribute("religionList", ckeck);
			
			request.setAttribute("historystatus", request.getParameter("status"));
			request.setAttribute("historysearchname", request.getParameter("searchname"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : editCaste   Ending");

		return mapping.findForward(MessageConstants.ADD_CASTE);

	}

	public ActionForward insertCasteCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : insertCasteCategory Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			ReligionCasteCasteCategoryForm detailsForm = new ReligionCasteCasteCategoryForm();

			String createCode = HelperClass.getCurrentUserID(request);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String religionId = request.getParameter("religionId");
			String casteId = request.getParameter("casteId");
			String casteCategory = request.getParameter("casteCategoryId");
			String casteCategoryIdVal=request.getParameter("castecateVal");
			String hiddencastecategory=request.getParameter("hiddencastecategory");
			
			detailsForm.setReligionId(religionId);
			detailsForm.setCasteId(casteId);
			detailsForm.setCasteCategory(casteCategory);
			detailsForm.setCasteCatId(casteCategoryIdVal);
			detailsForm.setHiddencastecategory(hiddencastecategory);
			detailsForm.setCreateUser(createCode);
			detailsForm.setLog_audit_session(log_audit_session);
			
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			String result = detailsBD.insertCasteCategory(detailsForm,userLoggingsVo);

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
				+ " Control in ReligionCasteCastCategory : insertCasteCategory  Ending");
		return null;

	}

	public ActionForward deleteCasteCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : deleteCasteCategory  Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);

			String casteCategoryId[] = request.getParameter("casteCategoryList").split(",");
			String inactivereason= request.getParameter("inactivereason");  
			String activereason= request.getParameter("activereason"); 
			String status= request.getParameter("status");  
			String otherreason= request.getParameter("otherreason");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();
			detailsPojo.setCasteCategoryIdArray(casteCategoryId);
			detailsPojo.setInactiveReason(inactivereason);
			detailsPojo.setActiveReason(activereason); 
			detailsPojo.setStatus(status);
			detailsPojo.setOtherReason(otherreason);
			detailsPojo.setLog_audit_session(log_audit_session);
			
			
			String ckeck = new ReligionCasteCasteCategoryBD().deleteCasteCategory(detailsPojo,userLoggingsVo);

			JSONObject json = new JSONObject();
			json.put("status", ckeck);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : deleteCasteCategory   Ending");

		return null;

	}

	public ActionForward editCasteCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : editCasteCategory  Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String title = "Modify Caste Category";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTECATEGORY);

			String casteCategoryId = request.getParameter("religionId");

			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();
			detailsPojo.setCasteCategoryId(casteCategoryId);
			
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();
			ReligionCasteCasteCategoryVo ckeck = detailsBD.getSingleCasteCategory(detailsPojo,userLoggingsVo);

			request.setAttribute("religionList", ckeck);
			
			request.setAttribute("historystatus", request.getParameter("status"));
			request.setAttribute("historysearchname", request.getParameter("searchname"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : editCasteCategory   Ending");

		return mapping.findForward(MessageConstants.ADD_CASTE_CATEGORY);

	}

	public ActionForward getReligionForDropDown(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : getReligionForDropDown Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			List<ReligionCasteCasteCategoryVo> result = detailsBD.getReligionDetails(userLoggingsVo);


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
				+ " Control in ReligionCasteCastCategory : getReligionForDropDown  Ending");
		return null;

	}

	public ActionForward getCasteForDropDown(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : getCasteForDropDown Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			String religionId=request.getParameter("religionId");
			////.out.println("DropdownCast Action: religion Id: "+religionId);
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			
			List<ReligionCasteCasteCategoryVo> result = detailsBD.getCasteDetailsList(religionId,userLoggingsVo);
			
			/*//.out.println("Action class Result: "+result);
			
	          JSONObject jsonobj = new JSONObject();
			


			List<ReligionCasteCasteCategoryVo> result = detailsBD
					.getCasteDetails();
*/
			////.out.println("Action class Result: " + result);

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
				+ " Control in ReligionCasteCastCategory : getCasteForDropDown  Ending");
		return null;

	}

	public ActionForward getCasteCategoryForDropDown(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : getCasteCategoryForDropDown Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			String casteId=request.getParameter("casteId");
			
			////.out.println("casteid is "+casteId);
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();

			List<ReligionCasteCasteCategoryVo> result = detailsBD.getCasteCategoryListDetails(casteId,userLoggingsVo);

			////.out.println("Action class Result: " + result);

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
				+ " Control in ReligionCasteCastCategory : getCasteCategoryForDropDown  Ending");
		return null;

	}

	public ActionForward addOccupation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : addOccupation Starting");

		try {
			
			String title="New Occupation";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_OCCUPATION);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : addOccupation Ending");

		return mapping.findForward(MessageConstants.ADD_OCCUPATION);
	}
	

	public ActionForward insertOccupation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : insertOccupation Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ReligionCasteCasteCategoryForm detailsForm = new ReligionCasteCasteCategoryForm();
			
			String createCode = HelperClass.getCurrentUserID(request);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			String occupationId = request.getParameter("occupationId");
			String occupation = request.getParameter("occupation");
			String hiddenoccupation = request.getParameter("hiddenoccupation");
			
			
			detailsForm.setOccupationId(occupationId);
			detailsForm.setOccupation(occupation);
			detailsForm.setHiddenoccupation(hiddenoccupation);
			detailsForm.setCreateUser(createCode);
			detailsForm.setLog_audit_session(log_audit_session);
	
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();
			
			String result = detailsBD.insertOccupation(detailsForm,userLoggingsVo);
			
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
				+ " Control in ReligionCasteCastCategory : insertOccupation  Ending");
	return null;

	}

	public ActionForward editOccupation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : editOccupation  Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_OCCUPATION);
			
            UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String title ="Modify Occupation";
			request.setAttribute("title",title);

			String occupationId = request.getParameter("occupationId");
			
			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();
			detailsPojo.setOccupationId(occupationId);
			
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();
			ReligionCasteCasteCategoryVo ckeck = detailsBD.getSingleOccupation(detailsPojo,userLoggingsVo);
			request.setAttribute("religionList", ckeck);
			
			request.setAttribute("status1", request.getParameter("status"));
			request.setAttribute("searchname1", request.getParameter("searchname"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in ReligionCasteCastCategory : editOccupation   Ending");
		
		 return mapping.findForward(MessageConstants.ADD_OCCUPATION);
	
	}
	

	public ActionForward deleteOccupation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : deleteOccupation  Starting");
		
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			String occupationId[] = request.getParameter("occupationId").split(",");
			String inactivereason=request.getParameter("inactivereason");
			String activereason=request.getParameter("activereason");
			String otherreason=request.getParameter("otherreason");
			String status=request.getParameter("status");
			
			ReligionCasteCasteCategoryPojo detailsPojo = new ReligionCasteCasteCategoryPojo();
			detailsPojo.setOccupationIdArray(occupationId);
			detailsPojo.setInactiveReason(inactivereason);
			detailsPojo.setActiveReason(activereason);
			detailsPojo.setOtherReason(otherreason);
			detailsPojo.setStatus(status);
		
			
			ReligionCasteCasteCategoryBD detailsBD = new ReligionCasteCasteCategoryBD();
			
			String ckeck = detailsBD.deleteOccupation(detailsPojo,userLoggingsVo);
			
			JSONObject json= new JSONObject();
			json.put("status", ckeck);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : deleteOccupation Ending");
		return null;
	}
		
	public ActionForward getOccupationListByStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : getOccupationListByStatus Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_OCCUPATION);

			ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
			List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();

			String status = request.getParameter("status");
			list=new ReligionCasteCasteCategoryDaoImpl().getOccupationListByStatus(status,userLoggingsVo);

             JSONObject json= new JSONObject();
			json.put("list", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : getOccupationListByStatus Ending");

		return null;
	}
	
	public ActionForward ReligionListByStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : ReligionListByStatus Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_RELIGION);

			ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
			List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();

			  String status = request.getParameter("status");
			  
				list = obj.ReligionListByStatus(status,userLoggingsVo);
				    JSONObject json= new JSONObject();
					json.put("list", list);
					response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : ReligionListByStatus Ending");

		return null;
	}
	
	public ActionForward CasteListingByStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : CasteListingByStatus Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTE);

			ReligionCasteCasteCategoryVo vo=new ReligionCasteCasteCategoryVo();
			
			String status = request.getParameter("status");
			vo.setStatus(status);
			
			
			List<ReligionCasteCasteCategoryVo> list=new ReligionCasteCasteCategoryBD().CasteListingByStatus(vo,userLoggingsVo);

			JSONObject json= new JSONObject();
			json.put("list", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : CasteListingByStatus Ending");

		return null;
	}
	
	public ActionForward CasteCategoryListingByStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : CasteCategoryListingByStatus Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTECATEGORY);

			String status = request.getParameter("status");
			
			List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();
			ReligionCasteCasteCategoryVo vo=new ReligionCasteCasteCategoryVo();
			vo.setStatus(status);
			list=new ReligionCasteCasteCategoryBD().CasteCategoryListingByStatus(vo,userLoggingsVo);
			
			JSONObject json= new JSONObject();
			json.put("list", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : CasteCategoryListingByStatus Ending");

		return null;
	}

	public ActionForward validateCaste(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : validateCaste Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTECATEGORY);
			
			String religionId = request.getParameter("religionId");
			String casteId = request.getParameter("casteId");
		    
			ReligionCasteCasteCategoryPojo pojo=new ReligionCasteCasteCategoryPojo();
			pojo.setMain_religion(religionId);
			pojo.setCaste(casteId);
			pojo.setCustid(userLoggingsVo.getCustId());
			
			int list=new ReligionCasteCasteCategoryDaoImpl().validateCaste(pojo,userLoggingsVo);
			
			JSONObject json= new JSONObject();
			json.put("status", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : validateCaste Ending");

		return null;
	}
	
	public ActionForward validateCasteCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : validateCasteCategory Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTECATEGORY);
			
			String religionId = request.getParameter("religionId");
			String casteId = request.getParameter("casteId"); 
			String castecategoryId = request.getParameter("castecategoryId");
		    
			ReligionCasteCasteCategoryPojo pojo=new ReligionCasteCasteCategoryPojo();
			pojo.setReligionId(religionId);
			pojo.setCasteId(casteId);
			pojo.setCasteCategory(castecategoryId);
			pojo.setCustid(userLoggingsVo.getCustId());
			
			int status=new ReligionCasteCasteCategoryDaoImpl().validateCasteCategory(pojo,userLoggingsVo);
			
			JSONObject json= new JSONObject();
			json.put("status", status);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCastCategory : validateCasteCategory Ending");

		return null;
	}
}
