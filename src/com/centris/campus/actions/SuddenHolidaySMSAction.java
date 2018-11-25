package com.centris.campus.actions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.delegate.CommunicationSettingsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.SuddenHolidayListBD;
import com.centris.campus.forms.SuddenHolidayForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public class SuddenHolidaySMSAction  extends DispatchAction{
	private static final Logger logger = Logger.getLogger(SuddenHolidaySMSAction.class);

	public ActionForward SuddenHolidayFilter(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaySMSAction : SuddenHolidayFilter : Starting");


		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_SUDDENHOLIDAYS);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SMS);

			ClassPojo pojo = new ClassPojo();
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			List<ClassPojo> classpojo = new CommunicationSettingsBD().getClassListDetails(pojo,custdetails);
			request.setAttribute("classpojo", classpojo);

			request.setAttribute("historystartdate", HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0]);
			request.setAttribute("historyenddate", HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1]);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaySMSAction : SuddenHolidayFilter : Ending");


		return mapping.findForward(MessageConstants.SUDDEN_HOLIDAY_ENTRY);

	}

	public ActionForward AddSuddenHoliday(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaySMSAction : AddSuddenHoliday : Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_SUDDENHOLIDAYS);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SMS);

			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");			
			SuddenHolidayListBD holidayListBD = new SuddenHolidayListBD();

			ClassPojo pojo = new ClassPojo();

			List<ClassPojo> classpojo = new CommunicationSettingsBD().getClassListDetails(pojo,userLoggingsVo);
			request.setAttribute("classpojo", classpojo);

			UserLoggingsPojo pojo1 = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			SuddenHolidaySMSVO form2 = new SuddenHolidaySMSVO();

			String[] classList = request.getParameter("classCode").split(",");
			String[] secList = request.getParameter("sectionCode").split(",");
			String locId = request.getParameter("locationId");
			
			form2.setAccYear(HelperClass.getCurrentYearID(userLoggingsVo));
			form2.setClassListArray(classList);
			form2.setSectionListArray(secList);
			form2.setLocId(locId);
			form2.setLog_audit_session(log_audit_session);
			String createdUser = HelperClass.getCurrentUserID(request);
			form2.setCreatedUser(createdUser);
			form2.setDbDetails(pojo1);
			form2.setSmstext(request.getParameter("smstext"));
			form2.setDate(request.getParameter("date"));
			form2.setHdate(request.getParameter("Holidaydate"));
			form2.setSmsCharacters(Integer.parseInt(request.getParameter("SMSCharacters")));
			form2.setBalanceSMS(Integer.parseInt(request.getParameter("balanceSMS")));


			String success = holidayListBD.AddSuddenHoliday(form2,pojo1);
			
			ArrayList<SuddenHolidaySMSVO> arrayList = new ArrayList<SuddenHolidaySMSVO>();

			arrayList = holidayListBD.SuddenHolidayList(pojo1,locId);

			request.setAttribute("holidayList", arrayList);
			
			JSONObject object = new JSONObject();
			object.put("status", success);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaySMSAction : AddSuddenHoliday : Ending");

		return null;
	}

	public ActionForward validateSuddenHolidaysSms(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaySMSAction : validateSuddenHolidaysSms : Starting");

		try {

			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			SuddenHolidayListBD holidayListBD = new SuddenHolidayListBD();

			String date =HelperClass.convertUIToDatabase(request.getParameter("date"));
			String smstext =request.getParameter("smstext");

			boolean status = holidayListBD.validateSuddenHolidaysSms(date,smstext,pojo);

			JSONObject object=new JSONObject();
			object.put("status", status);

			response.getWriter().print(object);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidaySMSAction : validateSuddenHolidaysSms : Ending");


		return null;
	}

}
