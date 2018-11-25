package com.centris.campus.actions;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.delegate.AbsentSMSBD;
import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;

/**
 * @author sathish
 *
 */
public class AbsentsSMSAction extends  DispatchAction {
	
	private static final Logger logger = Logger.getLogger(AbsentsSMSAction.class);
	
	public ActionForward storeAbsentSms(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AbsentsSMSAction: storeAbsentSms Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String[] studentlist = request.getParameter("studentlist").split(",");
			
			AbsentsSMSPojo absentpojo = new AbsentsSMSPojo();
			
			absentpojo.setClassname(request.getParameter("classid"));
			absentpojo.setStudentid(studentlist);
			absentpojo.setDate(HelperClass.convertUIToDatabase(request.getParameter("date")));
			absentpojo.setLocId(request.getParameter("locId"));
			absentpojo.setSmstext(request.getParameter("smstext"));
			absentpojo.setAccYearId(HelperClass.getCurrentYearID(custdetails));
			absentpojo.setIsstudent(1);
			absentpojo.setIssection(0);
			absentpojo.setCreatedby(HelperClass.getCurrentUserID(request));
			absentpojo.setCreatedate(HelperClass.getCurrentTimestamp());
			absentpojo.setLog_audit_session(log_audit_session);
			absentpojo.setBalanceSMS(Integer.parseInt(request.getParameter("balanceSMS")));
			
			String status=new AbsentSMSBD().storeAbsentSms(absentpojo,custdetails);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status", status);
			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentsSMSAction : storeAbsentSms Ending");
		
		return null;
	}
	public ActionForward validateAbsentSms(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AbsentsSMSAction: validateAbsentSms Starting");
		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			String date =HelperClass.convertUIToDatabase(request.getParameter("date"));
			String smstext =request.getParameter("smstext");
			AbsentsSMSPojo abpojo=new AbsentsSMSPojo();
		    abpojo.setCustid(pojo.getCustId());
			
			boolean status=new AbsentSMSBD().validateAbsentSms(date,smstext,abpojo,pojo);
			
			JSONObject object=new JSONObject();
			object.put("status", status);
			
			response.getWriter().print(object);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentsSMSAction : validateAbsentSms Ending");
		
		
		return null;
	}
	
	
}