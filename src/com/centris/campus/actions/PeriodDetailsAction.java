package com.centris.campus.actions;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.delegate.PeriodDetailsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PeriodVo;
import com.centris.campus.vo.ReportMenuVo;


public class PeriodDetailsAction extends DispatchAction{
	private static final Logger logger = Logger.getLogger(PeriodDetailsAction.class);

	public ActionForward periodList(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsAction :periodList Starting");
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PERIOD_MASTER);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			request.setAttribute("curr_loc", schoolLocation);
			
			PeriodVo vo = new PeriodVo();
			vo.setLocId(schoolLocation);
			vo.setStreamId("%%");
			vo.setClsId("%%");
			
			ArrayList<PeriodVo> list = new PeriodDetailsBD().getperiodlist(vo,custdetails);
			request.setAttribute("periodList",list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsAction :periodList Ending");

		return mapping.findForward(MessageConstants.Period_List);
	}


	public ActionForward addPeriod(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsAction :addPeriod Starting");

		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		String title="New Period";
		request.setAttribute("title", title);

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PERIOD_MASTER);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsAction :addPeriod Ending");

		return mapping.findForward(MessageConstants.Add_Period);
	}


	public ActionForward insertPeroidAction(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsAction :insertPeroidAction Starting");

		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");


		try {
			PeriodVo vo= new PeriodVo();
			String slno=request.getParameter("hiddenId");
			vo.setLocId(request.getParameter("locationId"));
			vo.setStreamId(request.getParameter("stream"));
			vo.setClsId(request.getParameter("className"));
			vo.setNoofperiod(Integer.parseInt(request.getParameter("noofperiod")));
			vo.setLog_audit_session(log_audit_session);
			vo.setCreatedby(HelperClass.getCurrentUserID(request));
			if(slno!=null && !slno.trim().equalsIgnoreCase("")){
				vo.setSlno(Integer.parseInt(slno));
			}


			PeriodDetailsBD periodbd=new PeriodDetailsBD();
			String status=periodbd.insertperiod(vo,userLoggingsVo);

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
				+ " Control in PeriodDetailsAction :insertPeroidAction Ending");

		return null;
	}

	public ActionForward editPeriod(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsAction :editPeriod Starting");

		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		String title="MOdify Period";
		request.setAttribute("title", title);

		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PERIOD_MASTER);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);

			String periodId=request.getParameter("periodId");
			PeriodVo vo= new PeriodVo();
			vo=new PeriodDetailsBD().editPeriod(periodId,userLoggingsVo);
			request.setAttribute("editelist", vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsAction :editPeriod Ending");

		return mapping.findForward(MessageConstants.Add_Period);
	}


	public ActionForward deletePeriod(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsAction :deletePeriod Starting");

		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");


		try {

			String periodId[]=request.getParameter("PeriodId").split(",");

			String status=new PeriodDetailsBD().deletePeriod(periodId,userLoggingsVo);

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
				+ " Control in PeriodDetailsAction :deletePeriod Ending");

		return null;
	}

	public ActionForward getperiodlist(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsAction :getperiodlist Starting");

		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");



		try {

			PeriodVo vo=new PeriodVo();
			vo.setLocId(request.getParameter("locid"));
			vo.setStreamId(request.getParameter("streamId"));
			vo.setClsId(request.getParameter("clsId"));
			if(vo.getLocId().equalsIgnoreCase("all")){
				vo.setLocId("%%");
			}
			if(vo.getStreamId().equalsIgnoreCase("")){
				vo.setStreamId("%%");
			}
			if(vo.getClsId().equalsIgnoreCase("")){
				vo.setClsId("%%");
			}
			ArrayList<PeriodVo> list=new ArrayList<PeriodVo>();
			list=new PeriodDetailsBD().getperiodlist(vo,custdetails);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("list",list);
			response.getWriter().print(jsonobj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsAction :getperiodlist Ending");

		return null;
	}
public ActionForward validateclassName(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsAction : validateclassName Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_MASTER);
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
					
			String clsId = request.getParameter("clsId");
			String locId = request.getParameter("locId");
			
			String result=new PeriodDetailsBD().validateclassName(clsId,custdetails,locId);
			
			JSONObject jobj=new JSONObject();
			jobj.put("result", result);
			response.getWriter().print(jobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsAction : validateclassName Ending");
		return null;
	}


}
