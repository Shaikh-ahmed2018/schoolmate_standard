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

import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.delegate.ExpenditureBD;
import com.centris.campus.delegate.FeeMasterDelegate;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ExpenditureVO;
import com.centris.campus.vo.ReportMenuVo;

public class ExpenditureAction extends DispatchAction

{
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	public ActionForward addExpenditure(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{

		String status = request.getParameter("result");

		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.SuccessMsg)) {

				request.setAttribute("successmessagediv",
						MessageConstants.SuccessMsgExpenditure);
			} else if ((status.equalsIgnoreCase(MessageConstants.ErrorMsg))) {

				request.setAttribute("errormessagediv",
						MessageConstants.ErrorMsgExpenditure);
			} else if (status.equalsIgnoreCase(MessageConstants.SuccessUpMsg)) {
				request.setAttribute("successmessagediv",
						MessageConstants.SuccessUpMsgExpenditure);
			} else if (status.equalsIgnoreCase(MessageConstants.ErrorUpMsg)) {
				request.setAttribute("successmessagediv",
						MessageConstants.ErrorUpMsgExpenditure);
			}

		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExpenditureAction : addExpenditure Starting");
		try {
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXPENDITURE);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);
			request.setAttribute("locationList", locationList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExpenditureAction : addExpenditure Ending");

		return mapping.findForward(MessageConstants.EXPENDITURE_ADD);

	}
	

	
	public ActionForward insertExpenditure(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExpenditureAction : insertExpenditure Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXPENDITURE);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			ExpenditureVO vo = new ExpenditureVO();

			vo.setExpId(request.getParameter("expId"));
			vo.setLocationname(request.getParameter("schoolname"));
			vo.setExpenditureTitle(request.getParameter("expenditureTitle"));
			vo.setAmount(Double.parseDouble(request.getParameter("amount")));
			vo.setDescription(request.getParameter("description"));
			vo.setDate(request.getParameter("date"));
			vo.setLog_audit_session(log_audit_session);
			vo.setCreateUser(HelperClass.getCurrentUserID(request));

			ExpenditureBD delegate = new ExpenditureBD();

			String result = delegate.insertExpenditure(vo);

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
				+ " Control in ExpenditureAction : insertExpenditure Ending");

		/* return mapping.findForward(MessageConstants.FEE_DETAILS_LIST); */
		return null;

	}
	
	
	public ActionForward editExpenditure(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExpenditureAction : editExpenditure Starting");

		String name = request.getParameter("name");
		

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXPENDITURE);

			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);
			request.setAttribute("locationList", locationList);
			
			ExpenditureVO vo = new ExpenditureVO();

			vo.setExpId(name);
			//("--------------Edit method action class:----------- " +name);

			ExpenditureVO editlist = new ExpenditureBD().editExpenditure(vo);

			request.setAttribute("editlist", editlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExpenditureAction : editExpenditure Ending");

		return mapping.findForward(MessageConstants.EXPENDITURE_ADD);

	}
	
	public ActionForward deleteExpenditure(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExpenditureAction : deleteExpenditure Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXPENDITURE);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String status=request.getParameter("status");
			String remark=request.getParameter("reason");
			String button=request.getParameter("button");
			ExpenditureVO vo = new ExpenditureVO();

			vo.setExpIds(request.getParameter("getDataArray").split(","));
			vo.setLog_audit_session(log_audit_session);
			vo.setCreateUser(HelperClass.getCurrentUserID(request));
			vo.setIsActive(status);
			vo.setRemark(remark);
			 
			ExpenditureBD delegate = new ExpenditureBD();
			String result = delegate.deleteExpenditure(vo,button);
			
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
				+ " Control in ExpenditureAction : deleteExpenditure Ending");

		/* return mapping.findForward(MessageConstants.FEE_DETAILS_LIST); */
		return null;

	}
	
	
	
}