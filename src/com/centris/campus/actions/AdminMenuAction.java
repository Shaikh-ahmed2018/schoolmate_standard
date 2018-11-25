package com.centris.campus.actions;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.json.JSONArray;
import org.json.JSONObject;
import com.centris.campus.dao.LocationDao;
import com.centris.campus.daoImpl.ElectionDaoImpl;
import com.centris.campus.daoImpl.FeeMasterDAOIMPL;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.LocationDAOImpl;
import com.centris.campus.daoImpl.StaffPayrollDaoImpl;
import com.centris.campus.daoImpl.StageDAOIMPL;
import com.centris.campus.daoImpl.StudentIDDaoImpl;
import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.daoImpl.TimeTableDaoImpl;
import com.centris.campus.delegate.AbsentSMSBD;
import com.centris.campus.delegate.AcadamicYearPlanBD;
import com.centris.campus.delegate.AddDesignationBD;
import com.centris.campus.delegate.ClassFeeSetupBD;
import com.centris.campus.delegate.ClassTeacherMappingDelegate;
import com.centris.campus.delegate.CommunicationSettingsBD;
import com.centris.campus.delegate.CreateExaminationBD;
import com.centris.campus.delegate.CareersViewdelegate;
import com.centris.campus.delegate.DepartmentMasterBD;
import com.centris.campus.delegate.AcademicYearMasterBD;
import com.centris.campus.delegate.ElectionBD;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.ExpenditureBD;
import com.centris.campus.delegate.AddSubjectDelegate;
import com.centris.campus.delegate.AddSyllabusDelegate;
import com.centris.campus.delegate.BankBD;
import com.centris.campus.delegate.BankBranchBD;
import com.centris.campus.delegate.FeeMasterDelegate;
import com.centris.campus.delegate.FeeSetupBD;
import com.centris.campus.delegate.FuelMasterBD;
import com.centris.campus.delegate.HolidayMasterBD;
import com.centris.campus.delegate.Inventory_Delegate;
import com.centris.campus.delegate.LeaveBankDelegate;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.LoginBD;
import com.centris.campus.delegate.ParentRequiresAppointmentDELEGATE;
import com.centris.campus.delegate.QuotaMasterBD;
import com.centris.campus.delegate.ReligionCasteCasteCategoryBD;
import com.centris.campus.delegate.RemainderMasterDelegate;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.RoleMasterBD;
import com.centris.campus.delegate.SectionBD;
import com.centris.campus.delegate.SmsDeligate;
import com.centris.campus.delegate.SpecializationBD;
import com.centris.campus.delegate.StaffAttendanceBD;
import com.centris.campus.delegate.StaffPayrollBD;
import com.centris.campus.delegate.StaffServiceReportBD;
import com.centris.campus.delegate.StageDelegateBD;
import com.centris.campus.delegate.StageFeeSetupBD;
import com.centris.campus.delegate.StreamDetailsBD;
import com.centris.campus.delegate.StudentEnquiryBD;
import com.centris.campus.delegate.StudentIDBD;
import com.centris.campus.delegate.StudentPramotionBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.delegate.SuddenHolidayListBD;
import com.centris.campus.delegate.TDSComputationBD;
import com.centris.campus.delegate.TeacherRegistrationBD;
import com.centris.campus.delegate.TemporaryRegBD;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.delegate.TimeTableBD;
import com.centris.campus.delegate.TransportBD;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.TransportTypeBD;
import com.centris.campus.delegate.UserManagementBD;
import com.centris.campus.delegate.UserRolePermissionBD;
import com.centris.campus.delegate.VehicleDriverMappingBD;
import com.centris.campus.forms.AddDesignation;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.forms.ConcessionForm;
import com.centris.campus.forms.CreateExaminationForm;
import com.centris.campus.forms.InventoryTransactionForm;
import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.pojo.AcadamicYearPlanPOJO;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.ElectionPojo;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.pojo.ProfessionalTaxPojo;
import com.centris.campus.pojo.TransportTypePOJO;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AcadamicYearPlanVO;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.AddStageVO;
import com.centris.campus.vo.AddorModifyorDeleteVO;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.BankBranchVO;
import com.centris.campus.vo.BankVO;
import com.centris.campus.vo.CareersViewVo;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.ClassPromotionList;
import com.centris.campus.vo.ClassTeacherMappingVO;
import com.centris.campus.vo.ClassTeacherVo;
import com.centris.campus.vo.DairyDetailsVo;
import com.centris.campus.vo.DepartmentMasterVO;
import com.centris.campus.vo.AcademicYearVO;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.ElectionVo;
import com.centris.campus.vo.FuelMaintenanceVO;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.ExpenditureVO;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.HolidayMasterVo;
import com.centris.campus.vo.IDcardVo;
import com.centris.campus.vo.InventoryTransactionVO;
import com.centris.campus.vo.InventoryVO;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.ParentRequiresAppointmentVO;
import com.centris.campus.vo.QuotaMasterVO;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.SpecializationVo;
import com.centris.campus.vo.StaffAttendanceVo;
import com.centris.campus.vo.StaffEmployementVo;
import com.centris.campus.vo.StaffPayrollListVo;
import com.centris.campus.vo.StageFeeSetupVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentEnquiryVo;
import com.centris.campus.vo.StudentIDVo;
import com.centris.campus.vo.StudentPramotionVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.SuddenHolidaySMSVO;
import com.centris.campus.vo.SyllabusVO;
import com.centris.campus.vo.TeacherVo;
import com.centris.campus.vo.TermMasterVo;
import com.centris.campus.vo.TimeTableVo;
import com.centris.campus.vo.TransportTypeVO;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.UpcomingBdayVo;
import com.centris.campus.vo.UpcomingRemarksVO;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.UserRecordVO;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.VehicleDriverMappingVo;
import com.centris.campus.vo.VehicleTypeVo;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.centris.campus.vo.feeReportVO;
import com.centris.campus.vo.secadmissionformformatVO;

public class AdminMenuAction extends DispatchAction {
	
	static ResourceBundle res = ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	private static String EcampusPro_TC_Dir = res.getString("EcampusPro_TC_Dir");

	/*	private static String EcampusPro_TC_Dir_Excel = res.getString("EcampusPro_TC_Dir_Excel");
	 */	
	private static final Logger logger = Logger.getLogger(AdminMenuAction.class);

	private static String ImageName = res.getString("ImageName");

	public ActionForward Home(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :Home Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
		String startDate=Integer.toString(HelperClass.getPastDateofAcademicYear(accYear,custdetails));
		String endDate=Integer.toString(HelperClass.getForDateofAcademicYear(accYear,custdetails));
		
		request.setAttribute("moduleName", "Dashboard");
		
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		
		try {

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :Home Ending");

		return mapping.findForward(MessageConstants.adminLogin);
	}

	public ActionForward studentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Starting");
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			try {
				UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
				request.setAttribute("locationList", locationList);

				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
				request.setAttribute("AccYearList", accYearList);
			
				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_REGISTRATION);
				long noofstudent=custdetails.getNoOfStudent();
				long count =HelperClass.getNumberOFStudentCount(null, null,custdetails);
				 
				if(count<noofstudent){
					request.setAttribute("status", "valid");
				}
				else{
					request.setAttribute("status", "invalid");
				}
			
				String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
				List<StudentRegistrationVo> List = null;
				String searchTerm = request.getParameter("searchname".trim());
				if (searchTerm != null && !searchTerm.equalsIgnoreCase("")) {
					List = new StudentRegistrationDelegate().getStudentDetails(searchTerm,academic_year+","+location,custdetails);
                      
					request.setAttribute("searchTerm", searchTerm);

				}/* else {
				List = new StudentRegistrationDelegate().getStudentDetails(userType, userCode, academic_year+","+location);
			}*/

				request.setAttribute(MessageConstants.STUDENTDETAILSLIST, List);
				request.setAttribute("academic_year", academic_year);
				request.setAttribute("hloc", location);
				
				request.setAttribute("historystatus", request.getParameter("historystatus"));
				request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
				request.setAttribute("historylocId", request.getParameter("historylocId"));
				request.setAttribute("historyclassname", request.getParameter("historyclassname"));
				request.setAttribute("historysectionid", request.getParameter("historysectionid"));
				request.setAttribute("historysearchvalue", request.getParameter("historysearchvalue"));
				
				request.setAttribute("currentstatus", request.getParameter("currentstatus"));
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : studentList Ending");

			return mapping.findForward(MessageConstants.STUDENT_LIST);
	}

	public ActionForward staffList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffList Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFREGISTRATION);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			
			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD().getdepartmentlist(custdetails); 
		     request.setAttribute("deptlist", DEPARTMENTLIST);	
            ArrayList<AddDesignationVO> designattion = new AddDesignationBD().getdesignationList(custdetails);
            request.setAttribute("designattionlist", designattion);	
        	AllTeacherDetailsVo obj = new AllTeacherDetailsVo();
        	obj.setLocid("%%");
			obj.setDeptid("%%");
			obj.setDesgId("%%");
			obj.setStatus("Y");
          
			ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();

			/*list = new TeacherRegistrationBD().getAllTeacherDetails(custdetails,obj);
			request.setAttribute("allTeacherDetailsList", list);*/
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historydepName", request.getParameter("historydepName"));
			request.setAttribute("historydesigId", request.getParameter("historydesigId"));
			request.setAttribute("historysearchvalue", request.getParameter("historysearchvalue")); 
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffList Ending");

		return mapping.findForward(MessageConstants.STAFF_LIST);
	}

	public ActionForward examList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			String searchTerm = request.getParameter("searchTerm");
			
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
				.searchExamination(searchTerm,pojo);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate(pojo);

			}

			request.setAttribute("examDetailsList", examvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : examList Ending");

		return mapping.findForward(MessageConstants.EXAM_LIST);
	}

	public ActionForward streamList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : streamList Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_STREAMDETAILS);

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			StreamDetailsBD obj = new StreamDetailsBD();
			List<StreamDetailsVO> list = new ArrayList<StreamDetailsVO>();

			String LocId = request.getParameter("LocId");
			String status = request.getParameter("status");
			
			
			String SearchName = request.getParameter("searchname");
			String school=request.getParameter("school");
			
			
			
			request.setAttribute("curr_loc",schoolLocation);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			
			request.setAttribute("locId", LocId);
			request.setAttribute("status", status);
			
			request.setAttribute("statuscurrent", request.getParameter("statuscurrent"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Ending");

		return mapping.findForward(MessageConstants.STREAM_LIST);
	}

	public ActionForward termList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : termList Starting");

		

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_TERMSETUP);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String status = request.getParameter("result");
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			if (status != null) {

				if (status.equalsIgnoreCase(MessageConstants.TERM_SUCCESS)) {

					request.setAttribute("successmessagediv",MessageConstants.TERM_SUCCESS);
				}
			}
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			String name = request.getParameter("searchvalue");
			request.setAttribute("searchvalue", name);
			TermMasterVo vo = new TermMasterVo();
			vo.setTermname(name);
			vo.setAccyear(academic_year);
            vo.setStatus("Y");
			request.setAttribute("searchterm", name);
			ArrayList<TermMasterVo> termlist = new TermMasterDelegate().termList(vo,custdetails);
			request.setAttribute("termlist", termlist);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : termList Ending");

		return mapping.findForward(MessageConstants.TERM_LIST);
	}

	public ActionForward addStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addStudent Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_REGISTRATION);
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails(custdetails);
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute("studentSearchList", registrationVo1);
			request.setAttribute("curr_loc", schoolLocation);
			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addStudent Ending");

		return mapping.findForward(MessageConstants.ADD_STUDENT);
	}

	public ActionForward departmentDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : departmentDetails Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DEPARTMENTDETAILS);

			ArrayList<DepartmentMasterVO> deplist = new ArrayList<DepartmentMasterVO>();

			String SearchName = request.getParameter("searchname");
			String status=request.getParameter("status");
			/*DepartmentMasterVO searchvo = new DepartmentMasterVO();
			searchvo.setSearch_name(SearchName);*/
            
			if (SearchName != null) {

				deplist = new DepartmentMasterBD().searchDepartment(SearchName.trim(),custdetails,status);
				request.setAttribute("DepartmentDetails", deplist);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchexamid", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				deplist = new DepartmentMasterBD().getDepartmentDetails(custdetails);

			}

			request.setAttribute("DepartmentDetails", deplist);
			request.getSession(false).setAttribute("EXcelDownLoad", deplist);
			
			request.setAttribute("searchname", SearchName);
			request.setAttribute("status", status);
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : departmentDetails Ending");

		return mapping.findForward(MessageConstants.DEPARTMENT_DETAILS);

	}

	public ActionForward classList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : classList Starting");
		
		try {
			
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			if(schoolLocation.equalsIgnoreCase("all")){
				schoolLocation="%%";
			}
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CLASSDETAILS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			
			
			String Message = request.getParameter("msg");
			
			String locId = request.getParameter("locId");
			String streamId = request.getParameter("streamId");
			String status = request.getParameter("status");
			String searchTextVal = request.getParameter("searchname");
			String school=request.getParameter("school");
			
			/*if (searchTextVal != null) {

				classList = delegate.searchClassDetails(searchTextVal.trim()+","+school,custdetails);
				request.setAttribute("searchname", searchTextVal);
				request.setAttribute("searchnamelist", searchTextVal);

			} else {

				classList = delegate.getClassDetails(schoolLocation,custdetails);

			}*/

			//request.setAttribute("classList", classList);
			
			request.setAttribute("cuur_loc", schoolLocation);
			request.setAttribute("successmessagediv", Message);
			request.setAttribute("locId", locId);
			request.setAttribute("streamId", streamId);
			request.setAttribute("status", status);
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : classList Ending");

		return mapping.findForward(MessageConstants.CLASS_LIST);
	}

	public ActionForward academicyear(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : academicyear Starting");
		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String status=request.getParameter("status");
			
			ArrayList<AcademicYearVO> editacademicyearlist = null;
			String searchName = request.getParameter("searchText");

			if (searchName != null) {

				editacademicyearlist = new AcademicYearMasterBD()
				.searchAcademicYearDetails(searchName,custdetails);

				request.setAttribute("searchname", searchName);
				request.setAttribute("searchnamelist", searchName);

			} else {
				editacademicyearlist = new AcademicYearMasterBD().getAcademicYearDetails(custdetails);
			}

			request.setAttribute("academicyearlist", editacademicyearlist);
			request.setAttribute("status", status);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_ACADEMICYEARDETAILS);
			
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : academicyear Ending");

		return mapping.findForward(MessageConstants.ACADEMIC_YEAR);
	}

	public ActionForward vehicleList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : vehicleList Starting");
		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String Message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_VEHICLEMASTER);

			TransportBD obj = new TransportBD();
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			List<VehicleDetailsVO> getvehiclelist = new ArrayList<VehicleDetailsVO>();
            String status="Y";

			String SearchName = request.getParameter("searchname");
			if(SearchName != null){
				getvehiclelist = obj.searchvehicledetails(SearchName.trim(),custdetails,"%%",status);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			}
			else {
				getvehiclelist= obj.getAllvehicleDetails(custdetails,status,"%%");
			}
			request.setAttribute("getvehiclelist", getvehiclelist);

			/*	String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				getvehiclelist = obj.searchvehicledetails(SearchName);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				getvehiclelist = obj.getAllvehicleDetails();
			}
			request.setAttribute("getvehiclelist", getvehiclelist);
			 */

			request.getSession(false).setAttribute("vehiclelistdownload",getvehiclelist);

			request.setAttribute("successmessagediv", Message);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			request.setAttribute("historysearch", request.getParameter("historysearch"));
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : vehicleList Ending");

		return mapping.findForward(MessageConstants.VEHICLE_LIST);

	}

	public ActionForward roleList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : roleList Starting");
		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_ROLEMASTER);


			List<RoleMasterPojo> roleMasterList = new ArrayList<RoleMasterPojo>();

			String searchTerm = request.getParameter("searchTerm");
			RoleMasterBD masterBD = new RoleMasterBD();

			if (searchTerm != null) {

				roleMasterList = masterBD.searchRole(searchTerm,custdetails);

				request.setAttribute("searchnamelist", searchTerm);

			} else {
				
				roleMasterList = masterBD.getRoles(custdetails, location);

			}

			request.setAttribute("roleMasterList", roleMasterList);
			/*
			 * request.getSession(false).setAttribute("EXcelDownLoad",
			 * roleMasterList);
			 */

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : roleList Ending");

		return mapping.findForward(MessageConstants.ROLE_LIST);
	}

	public ActionForward getUserRecords(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: getUserRecords Starting");
		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PASSWORDMAINTAINANCE);

			List<UserRecordVO> userRecords = new ArrayList<UserRecordVO>();

			String searchText = request.getParameter("searchText");
			String type = request.getParameter("type");

			if (searchText != null && type != null) {

				UserManagementPojo userManagementPojo = new UserManagementPojo();
				userManagementPojo.setType(type);
				userManagementPojo.setSearchtext(searchText);
				
				userRecords = new UserManagementBD().getSearchUserDetailsBD(userManagementPojo,custdetails);

				request.setAttribute("SearchText", searchText);
				request.setAttribute("Type", type);

			} else {

				userRecords = new UserManagementBD().getUserRecordsBD(custdetails);

			}
			request.setAttribute("userRecords", userRecords);
			
			request.setAttribute("historytypename", request.getParameter("typename"));
			request.setAttribute("historystatus", request.getParameter("status"));
			request.setAttribute("historysearchTextId", request.getParameter("searchTextId"));
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: getUserRecords Ending");

		return mapping.findForward(MessageConstants.USERRECORD);

	}
	
/*	temporary
	*/
	
	
	public ActionForward sectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: sectionList Starting");

		try {
			
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DIVISIONDETAILS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			
			/*ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);*/
			
			List<SectionForm> ClassSectionAndClassDetailsList = new ArrayList<SectionForm>();

			//List<SectionForm> classDetailsIDandClassDetailsNameList = new ArrayList<SectionForm>();
			String Message = request.getParameter("msg");

			SectionBD sectionDelegate = new SectionBD();

			//classDetailsIDandClassDetailsNameList = sectionDelegate.getCampusClassDetailsIDandClassDetailsNameBD(schoolLocation,custdetails);

			SectionForm secForm = new SectionForm();

			String searchTextVal = request.getParameter("searchText");
			String locationId=request.getParameter("school");
			
			String locId=request.getParameter("locId");
			String streamId=request.getParameter("streamId");
			String classname=request.getParameter("classname");
			String status=request.getParameter("status");
			
			secForm.setSectionName(searchTextVal);
			secForm.setLocationId(locationId);
			if(status!=null && !status.trim().equalsIgnoreCase(""))
			ClassSectionAndClassDetailsList = sectionDelegate.getCampusClassSectionAndClassDetailsBD(schoolLocation,custdetails);
			
			/*if (searchTextVal != null) {

				ClassSectionAndClassDetailsList = bd.searchSection(secForm);
				request.setAttribute("searchnamelist", searchTextVal);
				request.setAttribute("searchname", searchTextVal);

			} else {

				ClassSectionAndClassDetailsList = sectionDelegate.getCampusClassSectionAndClassDetailsBD(schoolLocation);
			}*/

//			request.setAttribute("classDetailsIDandClassDetailsNameList",
//					classDetailsIDandClassDetailsNameList);

			// for list
			request.setAttribute("ClassSectionAndClassDetailsList",ClassSectionAndClassDetailsList);

			request.setAttribute("successmessagediv", Message);
			request.setAttribute("curr_loc", schoolLocation);
			request.setAttribute("locId", locId);
			request.setAttribute("streamId", streamId);
			request.setAttribute("classname", classname);
			request.setAttribute("status", status);
			request.setAttribute("currentstatus",request.getParameter("currentstatus"));
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: sectionList Ending");

		return mapping.findForward(MessageConstants.SECTION_LIST);
	}

	
	public ActionForward feeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeList Ending");

		return mapping.findForward(MessageConstants.FEE_LIST);
	}

	// Download need to do
	public ActionForward feeDetailsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeDetailsList Starting");
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_FEE);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_FEE_FEESETUP);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_FEE);
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String academicYear=(String) request.getSession(false).getAttribute("current_academicYear");
			String locationId=(String) request.getSession(false).getAttribute("current_schoolLocation");
			String status = request.getParameter("result");
			
			if (status != null) {
				if (status.equalsIgnoreCase("true")) {
					request.setAttribute("successmessagediv",MessageConstants.DeleteMsg);
				} 
				else {
				request.setAttribute("errormessagediv",MessageConstants.DeleteErrorMsg);
			}
			} 
			else {
				request.setAttribute("errormessagediv", "Fee Alraedy Mapped");
			}
			
			if(academicYear == null || academicYear == "" || academicYear.equalsIgnoreCase("")) {
				academicYear = HelperClass.getCurrentYearID(custdetails);
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			AddFeeVO vo = new AddFeeVO();
			vo.setName(request.getParameter("searchvalue"));
			vo.setAcademicYear(academicYear); 
			vo.setLocationId(locationId);
			vo.setStatus("Y");
			String search =request.getParameter("searchvalue");
			 
			request.setAttribute("searchfee",search);

			ArrayList<AddFeeVO> feelist = new FeeMasterDelegate().getfeedetails(vo,custdetails);
			request.setAttribute("feelist", feelist);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacyearId", request.getParameter("historyacyearId"));
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeDetailsList Ending");

		return mapping.findForward(MessageConstants.FEE_DETAILS_LIST);
	}
	

	public ActionForward transportTypeHome(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportTypeHome Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

			ArrayList<TransportTypeVO> typeList = new ArrayList<TransportTypeVO>();

			String searchTerm = request.getParameter("searchTerm");

			if (searchTerm != null) {
				
				TransportTypePOJO transportTypePOJO = new TransportTypePOJO();
				transportTypePOJO.setSearchtext(searchTerm);

				typeList = new TransportTypeBD().getSearchDetails(transportTypePOJO,custdetails);
				request.setAttribute("searchtransporttype", searchTerm);

			} else {

				typeList = new TransportTypeBD().getAllTransportypeDetails(custdetails);
			}

			request.setAttribute("typelist", typeList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportTypeHome Ending");

		return mapping.findForward(MessageConstants.TRANSPORT_TYPELIST);
	}

	public ActionForward addStream(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addStream Starting");

		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_STREAMDETAILS);
			String title = "New Stream";
			request.setAttribute("title", title);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addStream Ending");

		return mapping.findForward(MessageConstants.ADD_STREAM);
	}

	public ActionForward createExam(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createExam Starting");

		String status = request.getParameter("result");

		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			if (status != null) {

				if (status.equalsIgnoreCase("Exam Created Successfully")) {

					request.setAttribute("successmessagediv",
							"Exam Created Successfully");
				}
			}
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			CreateExaminationForm examform = new CreateExaminationForm();
			List<Object> examnameslist = null;
			/*String accyear = examform.getAccyear();*/

			examnameslist = new CreateExaminationBD().getAllExamNames(examform,custdetails);

			Map<String, String> map = (Map<String, String>) new CreateExaminationBD()
			.getAccadamicYearsBD(custdetails);

			request.setAttribute("ALLACCYEARS", map);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createExam Ending");

		return mapping.findForward(MessageConstants.EXAM_CREATION);

	}

	public ActionForward QuotaDetails(ActionMapping mapping, ActionForm form,

			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : QuotaDetails Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ArrayList<QuotaMasterVO> quota_list = new ArrayList<QuotaMasterVO>();

			QuotaMasterVO searchvo = new QuotaMasterVO();
			String SearchName = request.getParameter("searchname");

			searchvo.setSearch_name(SearchName);

			if (SearchName != null) {

				quota_list = new QuotaMasterBD().searchQuota(searchvo,custdetails);
				request.setAttribute("searchdetails", SearchName.trim());

				request.setAttribute("searchnamelist", SearchName.trim());
			} else {

				//quota_list = new QuotaMasterBD().getQuotaDetails(userLoggingsVo.getCustId());
			}

			request.setAttribute("Quotalist", quota_list);
			/*
			 * request.getSession(false).setAttribute("EXcelDownLoad",
			 * quota_list);
			 */
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : QuotaDetails Ending");

		return mapping.findForward(MessageConstants.QUOTA_DETAILS);

	}

	public ActionForward designationList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : designationList Starting");

		String result = request.getParameter("value");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		if (result != null) {

			if (result.equalsIgnoreCase(MessageConstants.DESIGNATION_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.DESIGNATION_SUCCESS);
			}

		}

		String result1 = request.getParameter("result");

		if (result1 != null) {

			if (result1
					.equalsIgnoreCase(MessageConstants.ADD_DESIGNATION_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.ADD_DESIGNATION_SUCCESS);
			} else if ((result1
					.equalsIgnoreCase(MessageConstants.ADD_DESIGNATION_FAIL))) {

				request.setAttribute("errormessagediv",
						MessageConstants.ADD_DESIGNATION_FAIL);
			} else if (result1
					.equalsIgnoreCase(MessageConstants.UPDATE_DESIGNATION_SUCCESS)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_DESIGNATION_SUCCESS);
			} else if (result1
					.equalsIgnoreCase(MessageConstants.UPDATE_DESIGNATION_FAIL)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_DESIGNATION_FAIL);
			}

		}
		String username = null;

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNATIONDETAILS);

			username = HelperClass.getCurrentUserID(request);
			
			String status = request.getParameter("status");
			String searchname = request.getParameter("searchname");

			AddDesignationVO vo = new AddDesignationVO();
			String searchvalue = request.getParameter("searchvalue");

			vo.setDesgname(searchvalue);
			vo.setCustid(custdetails.getCustId());

			request.setAttribute("searchvalue", searchvalue);

			ArrayList<AddDesignationVO> list = new AddDesignationBD().DesignationDetails(vo,custdetails);

			request.setAttribute("DesignationDetails", list);
			
			request.setAttribute("status", status);
			request.setAttribute("searchname", searchname);
			
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));
			
			request.getSession(false).setAttribute("EXcel", list);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : designationList Ending");

		return mapping.findForward(MessageConstants.DESIGNATION_LIST);
	}

	public ActionForward adddesignation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :adddesignation");

		try {
		
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNATIONDETAILS);
			
			String title = "New Designation";
			request.setAttribute("title", title);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :adddesignation");

		return mapping.findForward(MessageConstants.ADD_DESIGNATION);
	}

	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submit Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			AddDesignation aform = new AddDesignation();
			
			aform.setLog_audit_session(log_audit_session);
			aform.setDesignation_name(request.getParameter("name"));
			aform.setDesignation_description(request.getParameter("description"));
			aform.setDesignationid(request.getParameter("id"));
			aform.setCreatedby(HelperClass.getCurrentUserID(request));
			aform.setCustid(HelperClass.getCurrentUserID(request));

			String name = request.getParameter("name");

			String description = request.getParameter("description");

			String createUser = HelperClass.getCurrentUserID(request);

			String id = request.getParameter("id");

			String result = new AddDesignationBD().insertDesignationDetails(aform,custdetails);

			//.out.println("delegate:::" + result);

			JSONObject jsonobject = new JSONObject();
			jsonobject.put("status", result);
			response.getWriter().print(jsonobject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submit Ending");

		return null;

	}

	public ActionForward careerupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: careerupdate Starting");

		CareersViewdelegate careerview;
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_INTERNALJOBPOSTING);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			List<CareersViewVo> career = new ArrayList<CareersViewVo>();

			careerview = new CareersViewdelegate();

			String searchName = request.getParameter("searchText");

			if (searchName != null) {

				career = careerview.searchDetails(searchName,custdetails);
				request.setAttribute("searchname", searchName);
				request.setAttribute("searchnamelist", searchName);

			} else {
				career = careerview.getAllcareerdetails(custdetails);
			}
			request.setAttribute("career", career);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: careerupdate Ending");

		return mapping.findForward(MessageConstants.UPDATE_CAREERS);

	}

	public ActionForward addJob(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: addJob Starting");

		try {

			String title = "Add New Internal Job Posting";
			request.setAttribute("title", title);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_INTERNALJOBPOSTING);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: addJob Ending");

		return mapping.findForward(MessageConstants.ADDJOB);

	}

	public ActionForward remainderdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: remainderdetails Starting");

		String status = request.getParameter("result");
		
		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.REM_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.REM_SUCCESS);
			}
		}

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			RemainderMasterVO vo = new RemainderMasterVO();
			vo.setName(request.getParameter("searchvalue"));
			vo.setCustid(custdetails.getCustId());

			ArrayList<RemainderMasterVO> remainderlist = new RemainderMasterDelegate()
			.remainderdetails(vo,custdetails);
			request.setAttribute("remainderlist", remainderlist);

			request.setAttribute("searchremainder",
					request.getParameter("searchvalue"));

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: remainderdetails Ending");

		return mapping.findForward(MessageConstants.REMAINDER_DETAILS);

	}

	public ActionForward acdamicYearPlanList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : acdamicYearPlanList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_ACTIVITIES);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String schoolName=request.getSession(false).getAttribute("current_schoolLocation").toString();
			if(schoolName.equalsIgnoreCase("all")){
				schoolName="%%";
			}
			ArrayList<AcadamicYearPlanVO> eventlist = new ArrayList<AcadamicYearPlanVO>();

			String searchTerm = request.getParameter("searchTerm");
			String school=request.getParameter("school");
			if (searchTerm != null) {

				AcadamicYearPlanPOJO acadamicYearPlanPOJO = new AcadamicYearPlanPOJO();
				acadamicYearPlanPOJO.setSerachText(searchTerm);
				acadamicYearPlanPOJO.setLocationId(school);
				acadamicYearPlanPOJO.setAcadamicYear(request.getSession(false)
						.getAttribute("current_academicYear").toString());

				eventlist = new AcadamicYearPlanBD()
				.getSearchDetails(acadamicYearPlanPOJO);
				request.setAttribute("searchTerm", searchTerm);

				request.setAttribute("searchnamelist", searchTerm);

			} else {

				AcadamicYearPlanPOJO acadamicYearPlanPOJO = new AcadamicYearPlanPOJO();
				acadamicYearPlanPOJO.setAcadamicYear(request.getSession(false).getAttribute("current_academicYear").toString());
				acadamicYearPlanPOJO.setLocationId(schoolName);
				acadamicYearPlanPOJO.setCustid(custdetails);
				
				eventlist = new AcadamicYearPlanBD().getAllEventDetails(acadamicYearPlanPOJO);

			}

			request.setAttribute("AcadamicYearPlanList", eventlist);
			request.getSession(false).setAttribute("EXcel1", eventlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : acdamicYearPlanList Ending");

		return mapping.findForward(MessageConstants.ACADAMICYEAR_PLANlIST);
	}

	public ActionForward changeBackground(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : changeBackground Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CHANGEBACKGROUND);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : changeBackground Ending");

		return mapping.findForward(MessageConstants.CHANGE_BACKGROUND);
	}

	public ActionForward fuelMaintenance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: fuelMaintenance Starting");

		String status = request.getParameter("result");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXPENSES);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXPENSES);

			List<FuelMaintenanceVO> fuelList = new FuelMasterBD()
			.getfuelMaintenanceList();
			request.setAttribute("fuelList", fuelList);

			if (status != null) {

				if (status
						.equalsIgnoreCase(MessageConstants.DELETE_FUEL_MAINTENANCE_SUCCESS)) {

					request.setAttribute("successmessagediv",
							MessageConstants.DELETE_FUEL_MAINTENANCE_SUCCESS);
				}

			}

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: fuelMaintenance Ending");

		return mapping.findForward(MessageConstants.FUEL_MAINTENANCE);

	}

	public ActionForward routeMasterSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: routeMasterSettings settings");
		try
		{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_ROUTEMASTER);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String loc=request.getParameter("location");
			
			String schoolname=HelperClass.getLocationFace(loc,custdetails);
			
			TransportBD obj = new TransportBD();
			List<TransportVo> list = new ArrayList<TransportVo>();
			String SearchName = request.getParameter("routeIdlist");
			if (SearchName != null) {
				list = obj.searchDetails(SearchName.trim(),custdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				list = obj.getTransportMasterDetails(loc,custdetails);
			}
			
			request.setAttribute("schoolname", schoolname);
			request.setAttribute("getTpMasterDetails", list);
			request.setAttribute("locationid", loc); 
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			request.setAttribute("historysearch", request.getParameter("historysearch"));
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: routeMasterSettings Ending");

		return mapping.findForward(MessageConstants.TRANSPORTMASTER);
	}

	public ActionForward StageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StageList Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_STAGEMASTER);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			
			String result = request.getParameter("value");
			String accyear = request.getParameter("accyear");
			String loc = request.getParameter("location");

			String username = null;
			
			if (result != null) {
				if (result.equalsIgnoreCase(MessageConstants.STAGE_SUCCESS)) {
					request.setAttribute("successmessagediv",MessageConstants.STAGE_SUCCESS);
				}
			}
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String locname = HelperClass.getLocationFace(loc,custdetails);
			String accId = HelperClass.getCurrentYearID(custdetails);
			
			username = HelperClass.getCurrentUserID(request);
			AddStageVO vo=new AddStageVO();
			StageDelegateBD obj = new StageDelegateBD();
			List<AddStageVO> list = new ArrayList<AddStageVO>();
			/*String SearchName = request.getParameter("searchvalue");

			if (SearchName != null) {
				list = obj.searchStage(SearchName.trim(),custdetails,loc);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {*/
				list = obj.StageDetailsList(loc+"-"+accId,custdetails);

			/*}*/
			
			request.setAttribute("accyId", accyear);
			request.setAttribute("locId", loc);
			request.setAttribute("locname", locname);
			request.setAttribute("StageDetails", list);
			
			request.setAttribute("historylocId", request.getParameter("historylocId")); 
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StageList Ending");

		return mapping.findForward(MessageConstants.STAGE_LIST);
	}

	public ActionForward addstage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addstage:Starting");

		String title = "Add New Stage";
		request.setAttribute("Stage", title);

		String result = request.getParameter("result");

		if (result != null) {

			if (result.equalsIgnoreCase(MessageConstants.ADD_STAGE_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.ADD_STAGE_SUCCESS);
			} else if ((result
					.equalsIgnoreCase(MessageConstants.ADD_STAGE_FAIL))) {

				request.setAttribute("errormessagediv",
						MessageConstants.ADD_STAGE_FAIL);
			} else if (result
					.equalsIgnoreCase(MessageConstants.UPDATE_STAGE_SUCCESS)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_STAGE_SUCCESS);
			} else if (result
					.equalsIgnoreCase(MessageConstants.UPDATE_STAGE_FAIL)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_STAGE_FAIL);
			}

		}

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_STAGEMASTER);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addstage:Ending");

		return mapping.findForward(MessageConstants.ADD_STAGE);
	}

	public ActionForward savestage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : savestage Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			/*AddStageForm aform = new AddStageForm();*/
			
			AddStageVO vo=new AddStageVO();

			vo.setStageName(request.getParameter("name"));
			vo.setDescription(request.getParameter("description"));
			vo.setStage_id(request.getParameter("id"));
			vo.setCreatedby(HelperClass.getCurrentUserID(request));
			vo.setLog_audit_session(log_audit_session);
			vo.setLocId(request.getParameter("location"));
			vo.setAccyearCode(HelperClass.getCurrentYearID(custdetails));
			
			String delegate = new StageDelegateBD().insertStage(vo,custdetails);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("jsonResponse", delegate);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : savestage Ending");

		return null;

	}

	public ActionForward getUserRolePermission(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getUserRolePermission Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_ASSIGNPERMISSION);

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute("RolePermission",new UserRolePermissionBD().getUserRolePermission(custdetails));
			
		
			
			//UserRolePermissionVO VO = new UserRolePermissionBD().getUserRolePermission(custdetails.getCustId());

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: getUserRolePermission Ending");

		return mapping.findForward(MessageConstants.USER_ROLE_PERMISSIONS);
	}

	public ActionForward subjectdetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : subjectdetails  Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SUBJECTDETAILS);
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String message = request.getParameter("message");
			
			String locId = request.getParameter("locId");
			String classname = request.getParameter("classname");
			String specialization = request.getParameter("specId");
			String status1 = request.getParameter("status");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			/*ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);*/
			
			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();

			String searchTerm = request.getParameter("searchname");
			String school=request.getParameter("school");
			String status="Y";
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			
			obj.setLocationId(school);
			obj.setSearchName(searchTerm);
			obj.setStatus(status);

			/*if (searchTerm != null) {

				subjectlist = new AddSubjectDelegate().searchsubjectdetails(obj,custdetails);

				request.setAttribute("searchTerm", searchTerm);
				request.setAttribute("searchnamelist", searchTerm);

			}*/ if(status1 == null){
				subjectlist = new AddSubjectDelegate().subjectdetails(schoolLocation,custdetails);
			}

			
			request.setAttribute("curr_loc", schoolLocation);
			request.setAttribute("allsubjects", subjectlist);

			request.setAttribute("successmessagediv", message);
			
			request.setAttribute("locId", locId);
			request.setAttribute("classname", classname);
			request.setAttribute("specialization", specialization);
			request.setAttribute("status", status1);
			
			request.setAttribute("currentstatus",request.getParameter("currentstatus"));

			request.getSession(false).setAttribute("EXcelDownLoad", subjectlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : subjectdetails Ending");

		return mapping.findForward(MessageConstants.SUBJECT_LIST);
	}

	
	
	
	public ActionForward subjectSylabus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : subjectdetails  Starting");
		
		try {
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SUBJECT_SYLABUS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("academic_year", academic_year);
			request.setAttribute("curr_loc", schoolLocation);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : subjectdetails Ending");

		return mapping.findForward(MessageConstants.SUBJECT_SYLABUS_LIST);
	}
	
	
	
	public ActionForward laboratorySylabus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : subjectdetails  Starting");
		
		try {
			
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LABORATORY_SYLABUS);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("academic_year", academic_year);
			request.setAttribute("curr_loc", schoolLocation);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : subjectdetails Ending");

		return mapping.findForward(MessageConstants.LABORATORY_SYLABUS_LIST);
	}
	
	
	
	
	
	public ActionForward syllabusListforListOnchangeMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : syllabusListforListOnchangeMethod  Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
					
			String location_id = request.getParameter("location");
			String classname = request.getParameter("classname");
			String specialization = request.getParameter("specialization");
			String academicYear = request.getParameter("academicYear");
			String isApplicable = request.getParameter("isApplicable");
			
			if(location_id.equalsIgnoreCase("all") || location_id == ""){
				location_id="%%";
			}
			if(classname.equalsIgnoreCase("all")|| classname == ""){
				classname="%%";
			}
			if(specialization.equalsIgnoreCase("all") || specialization == "" ){
				specialization="%%";
			}
			//.out.println("sylabus  : Action inside ");
			//.out.println("sylabus  :" +  isApplicable );
			//.out.println("sylabus  : data"+location_id+classname+specialization+academicYear+isApplicable );
			
			ArrayList<SyllabusVO> syllabusDetails =	new AddSyllabusDelegate().syllabusListforListOnchangeMethod(location_id,classname,specialization,academicYear,userLoggingsVo,isApplicable);
			
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("jsonResponse", syllabusDetails);
			response.getWriter().print(jsonobj);
		}         
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : syllabusListforListOnchangeMethod Ending");

		return null;
	}
	
	
	
	
	
	
	
	
	public ActionForward teachermapping(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception

					{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: teachermapping Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_ADMIN);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_ADMIN);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		String status = request.getParameter("result");
		if (status != null) {

			if (status.equalsIgnoreCase(MessageConstants.DEL_MAP_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.DEL_MAP_SUCCESS);
			}
		}

		ClassTeacherMappingVO vo = new ClassTeacherMappingVO();
		vo.setCustid(custdetails.getCustId());

		ArrayList<ClassTeacherMappingVO> getDownloadDetails = new ClassTeacherMappingDelegate().getDownloadDetails(vo,custdetails);

		LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>> mappinglist = new ClassTeacherMappingDelegate().getclassdetails(vo,custdetails);

		JSONArray arralist = new JSONArray();
		arralist.put(mappinglist);

		request.setAttribute("mappinglist", mappinglist);

		request.getSession(false).setAttribute("Exceldownload",
				getDownloadDetails);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: teachermapping Ending");

		return mapping.findForward(MessageConstants.TEACHERMAPPING);

					}
	

	
	

	public ActionForward getVehicleDriverMapping(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getVehicleDriverMapping Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String searchTerm = request.getParameter("searchTerm");

			ArrayList<VehicleDriverMappingVo> vehicleDriverMapList = new ArrayList<VehicleDriverMappingVo>();

			if (searchTerm == null || "".equalsIgnoreCase(searchTerm)) {

				vehicleDriverMapList = new VehicleDriverMappingBD()
				.getVehicleDriverMappingList();

			} else {

				vehicleDriverMapList = new VehicleDriverMappingBD()
				.getSearchVehicleDriverMappingList(searchTerm.trim(),custdetails);

			}

			request.setAttribute("SerchTerm", searchTerm);

			request.setAttribute("VehicleDriverMapList", vehicleDriverMapList);

			JSONArray array = new JSONArray();
			array.put(vehicleDriverMapList);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getVehicleDriverMapping Ending");

		return mapping.findForward(MessageConstants.vehicle_driver_mapping);
	}

	public ActionForward driverList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : driverList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_DRIVERMASTER);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			
			List<DriverMsaterListVo> driverList = new TransportBD().getdriverList(custdetails);

			request.setAttribute("driverList", driverList);

			request.getSession(false).setAttribute("DownLoad", driverList);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			request.setAttribute("historysearch", request.getParameter("historysearch"));
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : driverList Ending");

		return mapping.findForward(MessageConstants.driver_list);

	}

	public ActionForward driverget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : driverget Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : driverget Ending");

		return mapping.findForward(MessageConstants.ADD_DRIVER_DETAILS);

	}

	public ActionForward getClassFeeSetup(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getClassFeeSetup Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_CLASSFEESETUP);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);

			//.out.println(request.getSession(false)
					//.getAttribute("current_academicYear").toString());
			String currentaccyear = request.getSession(false)
					.getAttribute("current_academicYear").toString();
			String searchTerm = request.getParameter("searchTerm");

			String locationId=(String) request.getSession(false).getAttribute("current_schoolLocation");
			ArrayList<ClassFeeSetupVo> classSetupList = new ArrayList<ClassFeeSetupVo>();
             String custid=userLoggingsVo.getCustId();
			
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			if (searchTerm == null || "".equalsIgnoreCase(searchTerm)) {
				classSetupList = new ClassFeeSetupBD().getFeeSetupDetails(currentaccyear+","+locationId,userLoggingsVo);
			} else {
				classSetupList = new ClassFeeSetupBD().getSearchFeeSetupDetails(searchTerm, currentaccyear,userLoggingsVo);
			}

			request.setAttribute("classfeesetupSerchTerm", searchTerm);
			request.setAttribute("classSetupList", classSetupList);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getClassFeeSetup Ending");

		return mapping.findForward(MessageConstants.CLASS_FEE_SETUP);
	}

	public ActionForward getStageFeeSetup(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStageFeeSetup Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String currentaccyear = request.getSession(false).getAttribute("current_academicYear").toString();
			String searchTerm = request.getParameter("searchTerm");

			ArrayList<StageFeeSetupVo> classSetupList = new ArrayList<StageFeeSetupVo>();

			if (searchTerm == null || "".equalsIgnoreCase(searchTerm)) {

				classSetupList = new StageFeeSetupBD()
				.getStageFeeSetupDetails(currentaccyear,custdetails);

			} else {

				classSetupList = new StageFeeSetupBD()
				.getSearchStageFeeSetupDetails(searchTerm,currentaccyear,custdetails);

			}
			request.setAttribute("SerchTermstagesetup", searchTerm);
			request.setAttribute("classSetupList", classSetupList);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStageFeeSetup Ending");

		return mapping.findForward(MessageConstants.STAGE_FEE_SETUP);
	}

	public ActionForward communicationRemarksList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : communicationRemarksList Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		ArrayList<UpcomingRemarksVO> remarkslist = new CommunicationSettingsBD()
		.getRemarksListDetails(custdetails);

		request.setAttribute("remarkslist", remarkslist);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : communicationRemarksList Ending");

		return mapping.findForward(MessageConstants.REMARKS_LIST);

	}

	public ActionForward feeCollection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeCollection Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			
			/*request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_FEECOLLECTION);*/
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_COLLECTION_NEW);
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			
			request.setAttribute("ClassList", new ClassBD().getClassDetails(schoolLocation,userLoggingsVo));

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String userType = userDetailVO.getUserNametype();
			String userCode = userDetailVO.getUserId();
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			

			if(schoolLocation.equalsIgnoreCase("all")){
			schoolLocation = "%%";
				list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location,userLoggingsVo);
			}
		     else{
				list = new StudentRegistrationDelegate().getStudentList(academic_year,location,userLoggingsVo);
				
			}
			if (academic_year == null || academic_year == ""
					|| academic_year.equalsIgnoreCase("")) {
				//.out.println("HelperClass.getCurrentYearID()"
						//+ HelperClass.getCurrentYearID(userLoggingsVo));
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}

			List<StudentRegistrationVo> List = null;
			String searchTerm = request.getParameter("stuId");
			
			if (searchTerm != null && !searchTerm.equalsIgnoreCase("")) {
				List = new StudentRegistrationDelegate()
				.getStudentDetails(searchTerm,academic_year+","+schoolLocation,userLoggingsVo);
				request.setAttribute("searchTerm",searchTerm);

			} else {
				List = new StudentRegistrationDelegate().getStudentDetails1(userType, userCode, academic_year,schoolLocation,userLoggingsVo);
				
			     }		
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);

			request.setAttribute("AccYearList", accYearList);
			request.setAttribute(MessageConstants.STUDENTDETAILSLIST, List);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionId", request.getParameter("historysectionId"));
			request.setAttribute("historysearch", request.getParameter("historysearch"));

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeCollection Ending");

		return mapping.findForward(MessageConstants.FEE_COLLECTION_LIST);

	}

	public ActionForward payRollList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : payRollList Starting");

		/*
		 * request.setAttribute(MessageConstants.MODULE_NAME,
		 * MessageConstants.BACKOFFICE_FEE);
		 * 
		 * request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
		 * MessageConstants.MODULE_FEE);
		 */
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : payRollList Ending");

		return mapping.findForward(MessageConstants.PAYROLL);

	}

	public ActionForward getStaffAttendance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStaffAttendance Starting");

		try {
 
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFATTENDANCE);
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String startdate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String locationId = "%%";
			String accYear = request.getParameter("accYear");
			String tatt = request.getParameter("tatt");

			ArrayList<StaffAttendanceVo> staffAttendanceList = new StaffAttendanceBD().getStaffAttendanceList(startdate,endDate,custdetails,locationId,academic_year);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			request.setAttribute("currentAccYear", academic_year);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("attendancelist", staffAttendanceList);
			request.setAttribute("StartDate", startdate);
			request.setAttribute("EndDate", endDate);
			request.setAttribute("tatt", tatt);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historystartdate", request.getParameter("historystartdate"));
			request.setAttribute("historyenddate", request.getParameter("historyenddate"));

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStaffAttendance Ending");
		return mapping.findForward(MessageConstants.StaffAttendance);

	}

	public ActionForward gettimetable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gettimetable Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_TIMETABLEMANAGEMNET);

			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			/* String viewBy=request.getParameter("viewBy"); */
			String classId = request.getParameter("classId");
			request.setAttribute("SchoolLocation", HelperClass.getAllLocation(custdetails));
			/*
			 * String accyearid =
			 * request.getAttribute("current_academicYear").toString();
			 */
			String accyearid = request.getSession(false).getAttribute("current_academicYear").toString();
			/* String accyearid = HelperClass.getCurrentYearID(); */
			
			request.setAttribute("ClassTimeTableList",new TimeTableBD().getClassTimeTableList(accyearid,classId,custdetails));
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("accyearid", accyearid);

			/*
			 * if (viewBy != null) { if (viewBy.equalsIgnoreCase("class")) {
			 * 
			 * request.setAttribute("ClassTimeTableList",new
			 * TimeTableBD().getClassTimeTableList(accyearid,viewBy));
			 * 
			 * } else {
			 * 
			 * request.setAttribute("TeacherTimeTableList",new
			 * TimeTableBD().getClassTimeTableList(accyearid,viewBy)); }
			 * 
			 * request.setAttribute("ViewBy", viewBy);
			 * 
			 * } else {
			 * 
			 * viewBy = "class"; request.setAttribute("ClassTimeTableList", new
			 * TimeTableBD().getClassTimeTableList(accyearid, viewBy)); }
			 */
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclass",request.getParameter("historyclass"));
			request.setAttribute("historysection",request.getParameter("historysection")); 
			request.setAttribute("editoperation",request.getParameter("editoperation"));

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gettimetable Ending");

		return mapping.findForward(MessageConstants.timetable);

	}

	public ActionForward studentPromotionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionList Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ArrayList<StudentPramotionVO> StudentPramotionlist = new StudentPramotionBD()
			.getpromotionslist(custdetails);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

			request.setAttribute("StudentPramotionlist", StudentPramotionlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionList Ending");

		return mapping.findForward(MessageConstants.STUDENTPROMOTION);
	}

	public ActionForward studentPromotionscreen(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreen Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreen Ending");

		return mapping.findForward(MessageConstants.STUDENTPROMOTIONSCREEN);
	}

	public ActionForward studentPromotionscreenedit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreenedit Starting");
		try {
			String status = "status";
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TEACHERS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);
			request.setAttribute("status", status);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionscreenedit Ending");

		return mapping.findForward(MessageConstants.STUDENTPROMOTIONSCREEN);
	}

	public ActionForward studentEnquiryList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentEnquiryList Starting");
		StudentEnquiryBD delegateObj = new StudentEnquiryBD();
		List<StudentEnquiryVo> allDetails = new ArrayList<StudentEnquiryVo>();
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String text = request.getParameter("Code");
			if (text == "" || text == null) {
				allDetails = delegateObj.getAllEnquiryDetails(custdetails);
			} else {
				allDetails = delegateObj.getSearchEnquiryDetails(text,custdetails);
			}

			request.setAttribute("EnquiryDetails", allDetails);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentEnquiryList Ending");

		return mapping.findForward(MessageConstants.STUDENTENQUIRY);
	}

	public ActionForward enquiryCreateScreen(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : enquiryCreateScreen Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : enquiryCreateScreen Ending");

		return mapping.findForward(MessageConstants.STUDENTENQUIRYCREATE);
	}

	public ActionForward enquiryCreateScreenedit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : enquiryCreateScreenedit Starting");
		try {
			String status = "status";
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TEACHERS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);
			request.setAttribute("status", status);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : enquiryCreateScreenedit Ending");

		return mapping.findForward(MessageConstants.STUDENTENQUIRYCREATE);
	}

	public ActionForward FeeconcessionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : FeeconcessionDetails Starting");
		try {

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : FeeconcessionDetails Ending");

		return mapping.findForward(MessageConstants.STUDENTENQUIRYCREATE);
	}

	public ActionForward FeeConcessionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : FeeConcessionList Starting");
		 
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS); 
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CONCESSIONDETAILS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails); 
			request.setAttribute("locationList", locationList);

			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historysearchvalue", request.getParameter("historysearchvalue"));
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : FeeConcessionList Ending");

		return mapping.findForward(MessageConstants.FEE_CONCESSION_LIST);
	}

	public ActionForward addfeeconcession(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addfeeconcession Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS); 
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CONCESSIONDETAILS);
			
			String header="New Concession";
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails); 
			request.setAttribute("locationList", locationList);
			
			request.setAttribute("concession", header);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addfeeconcession Ending");

		return mapping.findForward(MessageConstants.ADD_FEE_CONCESSION);
	}

	public ActionForward insertConcesssionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupAction : insertConcesssionDetails  Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);
			
			ConcessionForm detailsForm = new ConcessionForm();
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");

			String createCode = HelperClass.getCurrentUserID(request);

			String concessionName = request.getParameter("concessionname");
			String locId = request.getParameter("locId");
			String description = request.getParameter("description");
			String concessionId = request.getParameter("concessionId");
			String concessionType = request.getParameter("concessionType");
			String concession = request.getParameter("concession");
			String isApplicable = request.getParameter("isApplicable");
			
			detailsForm.setConcesionName(concessionName);
			detailsForm.setDescription(description);
			detailsForm.setCreateUser(createCode);
			detailsForm.setLocationnid(locId);
			detailsForm.setConcessionId(concessionId);
			detailsForm.setLog_audit_session(log_audit_session);
			detailsForm.setConcessionType(concessionType);
			detailsForm.setIsApplicable(isApplicable);
			detailsForm.setConcession(concession);

			String check = new FeeSetupBD().insertConcesssionDetails(detailsForm,custdetails);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("jsonResponse", check);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupAction : insertConcesssionDetails  Ending");
		return null;

	}

	public ActionForward staffEmployementList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffEmployementList Starting");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFRENUMERATION);
			AllTeacherDetailsVo obj = new AllTeacherDetailsVo();
        	obj.setLocid("%%");
			obj.setDeptid("%%");
			obj.setDesgId("%%");
			obj.setStatus("Y");
			String searhname = request.getParameter("searhname");

			ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();

			if (searhname == null) {

				list = new TeacherRegistrationBD().getAllTeacherDetails(userLoggingsVo,obj);
			} else {

				list = new TeacherRegistrationBD().getSearchTeacherDetails(searhname,userLoggingsVo);
			}

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList); 
			
			request.setAttribute("allTeacherDetailsList", list);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historydepartment", request.getParameter("historydepartment"));
			request.setAttribute("historysearchname", request.getParameter("historysearchname"));
			request.setAttribute("salaryupdate", request.getParameter("salaryupdate"));
			request.setAttribute("Itdeclaration", request.getParameter("Itdeclaration"));
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffEmployementList Ending");

		return mapping.findForward(MessageConstants.STAFF_EMPLOYEMENT);
	}

	public ActionForward getPayrollList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getPayrollList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_SALARYDETAILS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String month = null;
			String year = null;
			ArrayList<StaffPayrollListVo> list = new StaffPayrollBD().getPayrollList(year,month,custdetails);

			request.setAttribute("PayrollList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getPayrollList Ending");

		return mapping.findForward(MessageConstants.STAFF_PAYROLL_LIST);
	}

	public ActionForward studPromotion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: studPromotion Starting");

		try {

			ArrayList<StudentPramotionVO> notptomotedStudentList = (ArrayList<StudentPramotionVO>) request.getSession(false).getAttribute("notpromotedStud");

			if (notptomotedStudentList != null) {

				if (notptomotedStudentList.size() != 0) {

					request.setAttribute("error",
							"Displayed students are not promoted,Addmission number already exist");

					request.setAttribute("notptomotedStudentList",
							notptomotedStudentList);
				} else {

					request.setAttribute("success",
							"Selected Students Promoted Succeessfully");
				}
			}

			request.getSession(false).setAttribute("notpromotedStud", null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: studPromotion Ending");

		return mapping.findForward(MessageConstants.STUD_PROMOTION);
	}

	public ActionForward getmeeting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getmeeting Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new CommunicationSettingsBD()
		.getMeetingListDetails(custdetails);

		String meet = "meeting";

		request.setAttribute("meetinglist", meetinglist);
		request.setAttribute("meeting", meet);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getmeeting Ending");

		return mapping.findForward(MessageConstants.REMARKS_LIST);

	}

	public ActionForward getbdaylist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getbdaylist Starting");

		String bday = "bday";

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		ArrayList<UpcomingBdayVo> bdaylist = new CommunicationSettingsBD().getBdayListDetails(custdetails);

		request.setAttribute("bdaylist", bdaylist);

		request.setAttribute("birthday", bday);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :getbdaylist Ending");
		return mapping.findForward(MessageConstants.REMARKS_LIST);
	}

	public ActionForward searchremark(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchremark Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			UpcomingRemarksVO remrakvo = new UpcomingRemarksVO();

			String remarks = request.getParameter("remarks");

			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");

			remrakvo.setRemarks(remarks);
			remrakvo.setFromdate(HelperClass.convertUIToDatabase(fromdate));
			remrakvo.setTodate(HelperClass.convertUIToDatabase(todate));
			remrakvo.setCustid(custdetails.getCustId());

			if (remrakvo.getRemarks().equalsIgnoreCase("remarks")) {

				ArrayList<UpcomingRemarksVO> remarkslist = new CommunicationSettingsBD()
				.searchRemarkDetails(remrakvo,custdetails);

				request.setAttribute("remarkslist", remarkslist);
				request.setAttribute("searchlist", remrakvo);

				request.setAttribute("communicatelist", remrakvo);

			}

			LstmsUpcomingMeetingVO meetingvo = new LstmsUpcomingMeetingVO();
			String meeting = request.getParameter("remarks");

			String fromdate1 = request.getParameter("fromdate");
			String todate1 = request.getParameter("todate");

			meetingvo.setTitle(meeting);
			meetingvo.setFromdate(HelperClass.convertUIToDatabase(fromdate1));
			meetingvo.setTodate(HelperClass.convertUIToDatabase(todate1));
			meetingvo.setCustid(custdetails.getCustId());

			if (meetingvo.getTitle().equalsIgnoreCase("meeting")) {

				ArrayList<LstmsUpcomingMeetingVO> meetinglist = new CommunicationSettingsBD()
				.searchMeetingDetails(meetingvo,custdetails);

				String meet = "meeting";

				request.setAttribute("meetinglist", meetinglist);
				request.setAttribute("searchlist", meetingvo);
				request.setAttribute("meeting", meet);

				request.setAttribute("meetingitems", meetingvo);

			}

			UpcomingBdayVo bdayvo = new UpcomingBdayVo();
			String content = request.getParameter("remarks");

			String fromdate2 = request.getParameter("fromdate");
			String todate2 = request.getParameter("todate");

			bdayvo.setContent(content);
			bdayvo.setFromdate(HelperClass.convertUIToDatabase(fromdate2));
			bdayvo.setTodate(HelperClass.convertUIToDatabase(todate2));
			bdayvo.setCustid(custdetails.getCustId());

			if (bdayvo.getContent().equalsIgnoreCase("bday")) {

				ArrayList<UpcomingBdayVo> bdaylist = new CommunicationSettingsBD()
				.searchBdayDetails(bdayvo,custdetails);

				String bday = "bday";

				request.setAttribute("bdaylist", bdaylist);
				request.setAttribute("searchlist", bdayvo);
				request.setAttribute("birthday", bday);

				request.setAttribute("bdayitems", bdayvo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :searchremark Ending");

		return mapping.findForward(MessageConstants.REMARKS_LIST);
	}

	public ActionForward LeaveBankList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : LeaveBankList Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_TEACHERS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_TEACHERS);

		String result = request.getParameter("deletekey");

		if (result != null) {

			if (result.equalsIgnoreCase(MessageConstants.LEAVEBANK_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.LEAVEBANK_SUCCESS);
			} else if (result
					.equalsIgnoreCase(MessageConstants.LEAVEBANK_ERROR)) {

				request.setAttribute("errormessagediv",
						MessageConstants.LEAVEBANK_ERROR);
			}

		}

		String result1 = request.getParameter("result");

		if (result1 != null) {

			if (result1
					.equalsIgnoreCase(MessageConstants.ADD_LEAVEBANK_SUCCESS)) {

				request.setAttribute("successmessagediv",
						MessageConstants.ADD_LEAVEBANK_SUCCESS);
			} else if ((result1
					.equalsIgnoreCase(MessageConstants.ADD_LEAVEBANK_FAILURE))) {

				request.setAttribute("errormessagediv",
						MessageConstants.ADD_LEAVEBANK_FAILURE);
			} else if (result1
					.equalsIgnoreCase(MessageConstants.UPDATE_LEAVEBANK_SUCCESS)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_LEAVEBANK_SUCCESS);
			} else if (result1
					.equalsIgnoreCase(MessageConstants.UPDATE_LEAVEBANK_FAIL)) {
				request.setAttribute("successmessagediv",
						MessageConstants.UPDATE_LEAVEBANK_FAIL);
			}

		}

		/*String username=null;*/

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);

	/*		username = HelperClass.getCurrentUserID(request);*/
			
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(pojo);
			request.setAttribute("AccYearList", accYearList);

			LeaveBankVO vo = new LeaveBankVO();

			vo.setAccyearcode(request.getParameter("searchvalue"));
			vo.setCustid(pojo.getCustId());

			ArrayList<LeaveBankVO> list = new LeaveBankDelegate().leavebanklist(vo,pojo);

			request.setAttribute("leaveBank", list);

			request.setAttribute("attnhidden", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : LeaveBankList Ending");

		return mapping.findForward(MessageConstants.LEAVEBANK_LIST);
	}

	public ActionForward booksmasterlist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : booksmasterlist Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_LIBRARY);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_LIBRARY);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : booksmasterlist Ending");

		return mapping.findForward(MessageConstants.BOOKSLIST_LIST);

	}

	public ActionForward getclassandteacherList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getclassandteacherList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_CLASSTEACHERMAPPING);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accYear = (String) request.getSession(false).getAttribute("current_academicYear");
			
			ArrayList<ClassTeacherVo> list = new ArrayList<ClassTeacherVo>();

			String searchTerm = request.getParameter("searchTerm");

			/*if (searchTerm != null) {

				list = new ClassTeacherLsisBD().getSearchClassTeacherListBD(searchTerm,custdetails.getCustId());

				request.setAttribute("searchTerm", searchTerm);
			}
			else {

				list = new ClassTeacherLsisBD().getClassTeacherListBD(custdetails.getCustId());
			}*/

			request.setAttribute("classteacherlist", list);
			ArrayList<TeacherRegistrationPojo> teacherlist =new StaffServiceReportBD().getTeacherListDetails(custdetails); 
			request.setAttribute("teacherlist", teacherlist);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			request.setAttribute("AccYearList", accYearList);
	
			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD().getdepartmentlist(custdetails); 
		     request.setAttribute("deptlist", DEPARTMENTLIST);	

			request.setAttribute("haccyear", accYear);
			request.getSession(false).setAttribute("EXcelDownLoad", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getclassandteacherList Ending");

		return mapping.findForward(MessageConstants.ClassTeacherMapping);
	}

	// support Work

	public ActionForward Support(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : Support Starting");
		try {/*
		 * request.setAttribute(MessageConstants.MODULE_NAME,
		 * MessageConstants.BACKOFFICE_SETTINGS);
		 * request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
		 * MessageConstants.MODULE_SETTINGS);
		 * 
		 * //.out.println("Support Action is Working");
		 * 
		 * StreamDetailsBD obj = new StreamDetailsBD();
		 * List<StreamDetailsVO> list = new ArrayList<StreamDetailsVO>();
		 * 
		 * String SearchName = request.getParameter("searchname");
		 * 
		 * 
		 * if(SearchName != null){
		 * 
		 * 
		 * 
		 * list=obj.searchStreamDetailsBD(SearchName);
		 * request.setAttribute("searchname", SearchName);
		 * request.setAttribute("searchnamelist", SearchName);
		 * 
		 * 
		 * 
		 * } else{
		 * 
		 * list =obj.getStreamDetailsBD();
		 * 
		 * }
		 * 
		 * request.setAttribute("streamDetailsList", list);
		 * 
		 * 
		 * 
		 * request.getSession(false).setAttribute("EXcelDownLoad",list);
		 */
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : Support Ending");

		return mapping.findForward(MessageConstants.SUPPORT_LIST);
	}

	// excel file upload

	public ActionForward studentfileupload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentfileupload : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_UPLOADSTUDENTEXCELDATAFILE);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentfileupload : Ending");

		return mapping.findForward(MessageConstants.STUDENT_EXCEL_UPLOAD);
	}

	

	public ActionForward homeworklist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : homeworklist Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_HOMEWORKS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		    request.setAttribute("current_loc", schoolLocation);
			
			request.setAttribute("academic_year", academic_year);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(custdetails);
			request.setAttribute("classlist", classlist);
			
			List<SmsVo> homeworklist = new ArrayList<SmsVo>();
	
			String startdate = HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0];
			String enddate = HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1];
							
			homeworklist = new SmsDeligate().getHomeWorklist(custdetails,schoolLocation,"%%","%%",academic_year,startdate,enddate);

			request.setAttribute("homeworklist", homeworklist);
			request.setAttribute("startdate", startdate);
			request.setAttribute("enddate", enddate);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			request.setAttribute("historystartdate", request.getParameter("historystartdate"));
			request.setAttribute("historyenddate", request.getParameter("historyenddate"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : homeworklist Ending");

		return mapping.findForward(MessageConstants.HOMEWORKS_LIST);
	}

	public ActionForward addHomeWork(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addHomeWork Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_HOMEWORKS);

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			/* List<ClassPojo> classList = new ArrayList<ClassPojo>(); */

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			request.setAttribute("historystartdate", request.getParameter("historystartdate"));
			request.setAttribute("historyenddate", request.getParameter("historyenddate"));
			
			/*List<ClassPojo> classList = new SmsDeligate().getclasslist();

			request.setAttribute("classList", classList);*/

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addHomeWork Ending");

		return mapping.findForward(MessageConstants.HOMEWORKS_ENTRY);
	}

	public ActionForward meetingslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : meetingslist Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_MEETINGSOREVENTS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		    request.setAttribute("current_loc", schoolLocation);
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(pojo);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(pojo);
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(pojo);
			request.setAttribute("classlist", classlist);
			
			ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
			LstmsUpcomingMeetingVO vo= new LstmsUpcomingMeetingVO();
		
		
			String searchTerm = request.getParameter("searchname");
		

			if (searchTerm == null) {
				meetinglist = new SmsDeligate().getMeetingListDetails(vo,pojo,schoolLocation);

				request.setAttribute("meetinglist", meetinglist);

			} else {
				meetinglist = new SmsDeligate().getMeetingSearchListDetails(searchTerm,vo,pojo,schoolLocation);

				request.setAttribute("meetinglist", meetinglist);

			}
              
			String startDate=HelperClass.getAcademicYearStartAndEndDate(academic_year, pojo).split(",")[0];
			String enddate=HelperClass.getAcademicYearStartAndEndDate(academic_year, pojo).split(",")[1];
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("enddate", enddate);
			
			request.setAttribute("historylocId",request.getParameter("historylocId"));
			request.setAttribute("historyacademicId",request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname",request.getParameter("historyclassname"));
			request.setAttribute("historysectionid",request.getParameter("historysectionid"));
			request.setAttribute("historystartdate",request.getParameter("historystartdate"));
			request.setAttribute("historyenddate",request.getParameter("historyenddate"));
			request.setAttribute("historytitleid",request.getParameter("historytitleid"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : meetingslist Ending");

		return mapping.findForward(MessageConstants.MEETING_LIST);
	}

	public ActionForward addMeeting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addMeeting Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_MEETINGSOREVENTS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ClassPojo Pojo=new ClassPojo();
			
			List<ClassPojo> classList = new SmsDeligate().getclasslist(Pojo,userLoggingsVo);
			request.setAttribute("classList", classList);
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			String startDate=HelperClass.getAcademicYearStartAndEndDate(academic_year, userLoggingsVo).split(",")[0];
			String enddate=HelperClass.getAcademicYearStartAndEndDate(academic_year, userLoggingsVo).split(",")[1];
			
		
			request.setAttribute("startDate",startDate);
			request.setAttribute("enddate",enddate);
			
			request.setAttribute("historylocId",request.getParameter("historylocId"));
			request.setAttribute("historyacademicId",request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname",request.getParameter("historyclassname"));
			request.setAttribute("historysectionid",request.getParameter("historysectionid"));
			request.setAttribute("historystartdate",request.getParameter("historystartdate"));
			request.setAttribute("historyenddate",request.getParameter("historyenddate"));
			request.setAttribute("historytitleid",request.getParameter("historytitleid"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addMeeting Ending");

		return mapping.findForward(MessageConstants.MEETING_ENTRY);
	}

	public ActionForward suddenholiodayslist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : suddenholiodayslist : Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_SUDDENHOLIDAYS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
		
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		    request.setAttribute("current_loc", schoolLocation);
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(pojo);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(pojo);
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(pojo);
			request.setAttribute("classlist", classlist);
			SuddenHolidayListBD holidayListBD = new SuddenHolidayListBD();

			ArrayList<SuddenHolidaySMSVO> arrayList = new ArrayList<SuddenHolidaySMSVO>();
			
			arrayList = holidayListBD.SuddenHolidayList(pojo,schoolLocation);
			String startDate=HelperClass.getAcademicYearStartAndEndDate(academic_year, pojo).split(",")[0];
			String enddate=HelperClass.getAcademicYearStartAndEndDate(academic_year, pojo).split(",")[1];
			
			request.setAttribute("holidayList", arrayList);
			request.setAttribute("startDate", startDate);
			request.setAttribute("enddate", enddate);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			request.setAttribute("historystartdate", request.getParameter("historystartdate"));
			request.setAttribute("historyenddate", request.getParameter("historyenddate"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : suddenholiodayslist : Ending");

		return mapping.findForward(MessageConstants.SUDDEN_HOLIDAY_LIST);
	}

	public ActionForward latecomingstudentslist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : latecomingstudentslist Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_LATECOMINGSTUDENTS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		    request.setAttribute("current_loc", schoolLocation);
		    
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(custdetails);
			request.setAttribute("classlist", classlist);
			
			String startDate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0];
			String enddate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1];
			
			ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();

			meetinglist = new SmsDeligate().getlatecomersListDetails(custdetails,schoolLocation);

			request.setAttribute("meetinglist", meetinglist);
			request.setAttribute("startDate", startDate);
			request.setAttribute("enddate", enddate);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			request.setAttribute("historystartdate", request.getParameter("historystartdate"));
			request.setAttribute("historyenddate", request.getParameter("historyenddate"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : latecomingstudentslist Ending");

		return mapping.findForward(MessageConstants.LATE_LIST);
	}

	public ActionForward addlatecomers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addlatecomers Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_LATECOMINGSTUDENTS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String startDate = HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0];
			String enddate = HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1];
		
			ClassPojo Pojo=new ClassPojo();
			
			List<ClassPojo> classList = new SmsDeligate().getclasslist(Pojo,custdetails);
			request.setAttribute("classList", classList);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			request.setAttribute("historystartdate", startDate);
			request.setAttribute("historyenddate", enddate);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addlatecomers Ending");
		
		return mapping.findForward(MessageConstants.LATECOMERSENTRYADD);
	}

	public ActionForward uniformlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : uniformlist Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_UNIFORM);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SMS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<UniformSmsPojo> uniformlist = new ArrayList<UniformSmsPojo>();

			uniformlist = new SmsDeligate().getUniformListDetails(custdetails);

			request.setAttribute("meetinglist", uniformlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : uniformlist Ending");

		return mapping.findForward(MessageConstants.UNIFORM_LIST);
	}

	public ActionForward addUniform(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addUniform Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_UNIFORM);
			
			ClassPojo pojo = new ClassPojo();
			
			List<ClassPojo> classpojo = new CommunicationSettingsBD().getClassListDetails(pojo,custdetails);
			request.setAttribute("classpojo", classpojo);
			
			 ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
				request.setAttribute("locationList", locationList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addUniform Ending");

		return mapping.findForward(MessageConstants.UNIFORM_ENTRY);
	}

	public ActionForward absentlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : absentlist Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_ABSENT);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SMS);
		
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		    request.setAttribute("current_loc", schoolLocation);
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(custdetails);
			request.setAttribute("classlist", classlist);
			
			ClassPojo Pojo=new ClassPojo();
		
			List<ClassPojo> classList = new SmsDeligate().getclasslist(Pojo,custdetails);
			request.setAttribute("classList", classList);
			AbsentsSMSPojo pojo= new AbsentsSMSPojo();
			
			ArrayList<AbsentsSMSPojo> list = new AbsentSMSBD().absentlist(pojo,custdetails,schoolLocation);
			request.setAttribute("absentList", list);
		
			String startDate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0];
			String enddate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1];
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("enddate", enddate);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			request.setAttribute("historystartdate", request.getParameter("historystartdate"));
			request.setAttribute("historyenddate", request.getParameter("historyenddate"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : absentlist Ending");

		return mapping.findForward(MessageConstants.SMS_ABSENT);
	}

	public ActionForward absentlistFilter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : absentlistFilter Starting");

		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_INTERACTION_ABSENT);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SMS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SMS);

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String startDate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0];
			String enddate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1];
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			request.setAttribute("historystartdate", startDate);
			request.setAttribute("historyenddate", enddate);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : absentlistFilter Ending");

		return mapping.findForward(MessageConstants.SMS_ABSENT_FILTER);
	}

	public ActionForward expenseslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : expenseslist Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXPENSES);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXPENSES);
			
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
				.searchExamination(searchTerm,pojo);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate(pojo);

			}

			request.setAttribute("examDetailsList", examvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : expenseslist Ending");

		return mapping.findForward(MessageConstants.EXPENSES_LIST);
	}

	public ActionForward inflowslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : inflowslist Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXPENSES);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXPENSES);
			
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
				.searchExamination(searchTerm,pojo);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate(pojo);

			}

			request.setAttribute("examDetailsList", examvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : inflowslist Ending");
		//.out.println("inflows");
		return mapping.findForward(MessageConstants.INFLOWS_LIST);
	}

	public ActionForward revelist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : revelist Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXPENSES);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXPENSES);
			
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
				.searchExamination(searchTerm,pojo);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate(pojo);

			}

			request.setAttribute("examDetailsList", examvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : revelist Ending");

		return mapping.findForward(MessageConstants.REVENUE_LIST);
	}

	public ActionForward createExpenses(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createExpenses Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXPENSES);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String searchTerm = request.getParameter("searchTerm");

			List<ExaminationDetailsVo> examvo = new ArrayList<ExaminationDetailsVo>();

			ExamDetailsBD examdeligate = new ExamDetailsBD();

			if (searchTerm != null) {

				examvo = new CreateExaminationBD()
				.searchExamination(searchTerm,pojo);

				request.setAttribute("searchexamlist", searchTerm);
			} else {

				examvo = examdeligate.getexamdeligate(pojo);

			}

			request.setAttribute("examDetailsList", examvo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createExpenses Ending");

		return mapping.findForward(MessageConstants.ADD_EXPENSES_LIST);
	}

	public ActionForward getTransactionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTransactionDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSACTIONS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTransactionDetails Ending");

		return mapping.findForward(MessageConstants.GET_TRANSACTION_LIST);
	}

	public ActionForward createTransaction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createTransaction Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSACTIONS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createTransaction Ending");

		return mapping.findForward(MessageConstants.ADD_TRANSACTION_LIST);
	}

	// <-----------------------------------INVENTORY
	// MODULE--------------------------------------------->

	public ActionForward InventoryTypeList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : InventoryTypeList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			Inventory_Delegate obj = new Inventory_Delegate();
			List<InventoryVO> list = new ArrayList<InventoryVO>();

			String SearchName = request.getParameter("searchname");

			if (SearchName != null) {

				list = obj.searchInventoryTypeList(SearchName,custdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {

				list = obj.InventoryTypesList(custdetails);

			}

			request.setAttribute("InventoryTypesList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : InventoryTypeList Ending");

		return mapping.findForward(MessageConstants.INVENTORY_TYPE_LIST);
	}

	public ActionForward createinventorytype(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createinventorytype Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD().getDepartmentDetails(custdetails);
			request.setAttribute("DepartmentDetails", DEPARTMENTLIST);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : createinventorytype Ending");

		return mapping.findForward(MessageConstants.INVENTORY_ADD);
	}

	public ActionForward InventoryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : InventoryList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

			Inventory_Delegate obj = new Inventory_Delegate();

			String SearchName = request.getParameter("searchTerm");

			//.out.println("SEarch Name:::" + SearchName);

			if (SearchName != null) {

				list = obj.SearchInventoryList(SearchName,custdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {

				list = new Inventory_Delegate().InventoryList(custdetails.getCustId());

			}

			request.setAttribute("list", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : InventoryList Ending");

		return mapping.findForward(MessageConstants.INVENTORY_LIST);
	}

	public ActionForward InventoryListExcelDownload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : InventoryListExcelDownload  Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/InventoryDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

			Inventory_Delegate obj = new Inventory_Delegate();

			String SearchName = request.getParameter("searchTerm");

			if (SearchName != null) {

				list = obj.SearchInventoryList(SearchName,custdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {

				list = new Inventory_Delegate().InventoryList(custdetails.getCustId());

			}

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/InventoryDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Inventory  Details Excel Report" };
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
					request.getRealPath("Reports/InventoryDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=InventoryDetailsXLSReport.xls");
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
				+ " Control in AddDesignationAction : InventoryListExcelDownload  Ending");
		return null;

	}

	public ActionForward InventoryListPDFDownload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : InventoryListPDFDownload  Starting");

		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

			Inventory_Delegate obj = new Inventory_Delegate();

			String SearchName = request.getParameter("searchTerm");

			if (SearchName != null) {

				list = obj.SearchInventoryList(SearchName,custdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {

				list = new Inventory_Delegate().InventoryList(custdetails.getCustId());

			}

			String sourceFileName = request
					.getRealPath("Reports/InventoryDetailsPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("Image", PropfilePath);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "Inventory Details PDF Report - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction : InventoryListPDFDownload  Ending");
		return null;

	}

	public ActionForward AddorModifyorDeleteList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddorModifyorDeleteList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

			Inventory_Delegate obj = new Inventory_Delegate();

			String SearchName = request.getParameter("searchname");

			if (SearchName != null) {

				list = obj.SearchAddorModifyorDeleteList(SearchName,custdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {

				list = new Inventory_Delegate().AddorModifyorDeleteList(custdetails);

			}

			request.setAttribute("list", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddorModifyorDeleteList Ending");

		return mapping
				.findForward(MessageConstants.ADD_or_MODIFY_or_DELETE_LIST);
	}

	public ActionForward AddPurchaseDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddPurchaseDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD()
			.getDepartmentDetails(custdetails);

			List<InventoryVO> list = new Inventory_Delegate()
			.InventoryTypesList(custdetails);

			request.setAttribute("InventoryTypesList", list);

			request.setAttribute("DepartmentDetails", DEPARTMENTLIST);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddPurchaseDetails Ending");

		return mapping.findForward(MessageConstants.ADD_PURCHASE_DETAILS);
	}

	public ActionForward TransactionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : TransactionList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			Inventory_Delegate obj = new Inventory_Delegate();
			List<InventoryTransactionVO> list = new ArrayList<InventoryTransactionVO>();

			/*
			 * String SearchName = request.getParameter("searchname");
			 * 
			 * if(SearchName != null){
			 * 
			 * list=obj.searchInventoryTypeList(SearchName);
			 * request.setAttribute("searchname", SearchName);
			 * request.setAttribute("searchnamelist", SearchName);
			 * 
			 * } else{
			 */

			list = obj.InventoryTransactionList();

			/* } */

			request.setAttribute("TransactionDetailsList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : TransactionList Ending");

		return mapping.findForward(MessageConstants.TRANSACTION_LIST);
	}

	public ActionForward usageReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : usageReport Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			//.out.println("usage report action");

			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD()
			.getDepartmentDetails(custdetails);

			List<InventoryVO> list = new Inventory_Delegate()
			.InventoryTypesList(custdetails);

			request.setAttribute("InventoryTypesList", list);

			request.setAttribute("DepartmentDetails", DEPARTMENTLIST);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : usageReport Ending");

		return mapping.findForward(MessageConstants.USAGE_REPORT);
	}

	// usage report of inventory items //

	public ActionForward getUsageReportAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getUsageReportAction Starting");
		try {

			//.out.println("<<<<<<<<<<< Hello >>>>>>>>>>>>>>");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			Inventory_Delegate obj = new Inventory_Delegate();
			List<InventoryTransactionVO> list = new ArrayList<InventoryTransactionVO>();

			InventoryTransactionForm invenTranForm = (InventoryTransactionForm) form;

			//.out.println("getDepartment Type: "
					//+ invenTranForm.getDepartment());

			/*
			 * String SearchName = request.getParameter("searchname");
			 * 
			 * if(SearchName != null){
			 * 
			 * list=obj.searchInventoryTypeList(SearchName);
			 * request.setAttribute("searchname", SearchName);
			 * request.setAttribute("searchnamelist", SearchName);
			 * 
			 * } else{
			 */
			invenTranForm.setCustid(custdetails.getCustId());

			list = obj.usageReportList(invenTranForm,custdetails);

			/* } */

			request.setAttribute("TransactionDetailsList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getUsageReportAction Ending");

		return mapping.findForward("usageReportList");
	}

	public ActionForward notReturnedReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : notReturnedReport Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			//.out.println("notReturnedReport report action");
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD()
			.getDepartmentDetails(custdetails);

			List<InventoryVO> list = new Inventory_Delegate()
			.InventoryTypesList(custdetails);

			request.setAttribute("InventoryTypesList", list);

			request.setAttribute("DepartmentDetails", DEPARTMENTLIST);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : notReturnedReport Ending");

		return mapping.findForward(MessageConstants.NOTRETURNED_REPORT);
	}

	// for not returned item report page//

	public ActionForward getNotReturnedReportAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getNotReturnedReportAction Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			//.out.println("<<<<<<<<<<< Hello >>>>>>>>>>>>>>");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			Inventory_Delegate obj = new Inventory_Delegate();
			List<InventoryTransactionVO> list = new ArrayList<InventoryTransactionVO>();

			InventoryTransactionForm invenTranForm = (InventoryTransactionForm) form;

			//.out.println("getDepartment Type: "
					//+ invenTranForm.getDepartment());

			/*
			 * String SearchName = request.getParameter("searchname");
			 * 
			 * if(SearchName != null){
			 * 
			 * list=obj.searchInventoryTypeList(SearchName);
			 * request.setAttribute("searchname", SearchName);
			 * request.setAttribute("searchnamelist", SearchName);
			 * 
			 * } else{
			 */

			list = obj.getNotReturnedReportAction(invenTranForm);

			/* } */

			request.setAttribute("TransactionDetailsList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getNotReturnedReportAction Ending");

		return mapping.findForward(MessageConstants.NOTRETURNED_REPORT_LIST);
	}

	public ActionForward admissionsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : admissionsList Starting");
		try {

			/*
			 * request.setAttribute(MessageConstants.MODULE_NAME,
			 * MessageConstants.BACKOFFICE_SETTINGS);
			 * request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
			 * MessageConstants.MODULE_SETTINGS);
			 */
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();

			List<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();

			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {

				list = obj.searchadmissionsList(SearchName,custdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {
				list = obj.getAdmisssionProcessingListDetails(custdetails);
			}

			request.setAttribute("TemporaryAdmissionDetailsList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : admissionsList Ending");

		return mapping.findForward(MessageConstants.ADMISSION_LIST);
	}

	public ActionForward CalledForEvaluationList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : CalledForEvaluationList Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();

			List<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();

			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {

				list = obj.searchCalledForEvaluationList(SearchName,custdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {
				list = obj.CalledForEvaluationList(custdetails);
			}

			request.setAttribute("TemporaryAdmissionDetailsList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : CalledForEvaluationList Ending");

		return mapping.findForward(MessageConstants.CALLED_FOR_ADMISSION_LIST);
	}

	public ActionForward FinalAdmisssionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : FinalAdmisssionList Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();

			List<ParentRequiresAppointmentVO> list = new ArrayList<ParentRequiresAppointmentVO>();

			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {

				list = obj.searchCalledForEvaluationList(SearchName,custdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);

			} else {
				list = obj.FinalAdmisssionList(custdetails);
			}

			request.setAttribute("TemporaryAdmissionDetailsList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : FinalAdmisssionList Ending");

		return mapping.findForward(MessageConstants.FINAL_ADMISSION_LIST);
	}

	public ActionForward AddTransactionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddTransactionDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			/*
			 * List<InventoryVO> list = new Inventory_Delegate()
			 * .InventoryTypesList();
			 * 
			 * request.setAttribute("InventoryTypesList", list);
			 */
			//.out.println("AddTransaction Details  :::");

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();
			Inventory_Delegate obj = new Inventory_Delegate();

			list = obj.InventoryList(custdetails.getCustId());

			request.setAttribute("list", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddTransactionDetails Ending");

		return mapping.findForward(MessageConstants.ADD_TRANSACTION);
	}

	public ActionForward EditTransactionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : EditTransactionDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String id = request.getParameter("transactionItemId");

			List<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();
			Inventory_Delegate obj = new Inventory_Delegate();

			list = obj.InventoryList(custdetails.getCustId());

			request.setAttribute("list", list);

			List<AddorModifyorDeleteVO> lists = new ArrayList<AddorModifyorDeleteVO>();
			Inventory_Delegate objs = new Inventory_Delegate();

			lists = objs.singleItemDetails(id);
			request.setAttribute("lists", lists);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : EditTransactionDetails Ending");

		return mapping.findForward(MessageConstants.ADD_TRANSACTION);
	}

	public ActionForward singleItemDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : singleItemDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			String id = request.getParameter("transactionItemId");

			List<AddorModifyorDeleteVO> lists = new ArrayList<AddorModifyorDeleteVO>();
			Inventory_Delegate objs = new Inventory_Delegate();

			lists = objs.singleItemDetails(id);

			JSONObject object = new JSONObject();
			object.put("status", lists);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : singleItemDetails Ending");

		return null;
	}

	// for get available quantity//

	public ActionForward getAvailableQuantity(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAvailableQuantity Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);
			String id = request.getParameter("item_id");

			String issued = request.getParameter("issued_quantity");
			//.out.println(issued);

			Inventory_Delegate objs = new Inventory_Delegate();

			String lists = objs.getAvailableQuantity(id, issued);

			JSONObject object = new JSONObject();
			object.put("status", lists);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAvailableQuantity Ending");

		return null;
	}

	// for getting department by item type//

	public ActionForward getItemtypeByDepartmnet(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getItemtypeByDepartmnet Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_INVENTORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_INVENTORY);

			String department = request.getParameter("department");
			//.out.println(department);

			List<InventoryVO> list = new ArrayList<InventoryVO>();
			Inventory_Delegate objs = new Inventory_Delegate();

			list = objs.getItemtypeByDepartmnet(department);

			JSONObject object = new JSONObject();
			object.put("status", list);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getItemtypeByDepartmnet Ending");

		return null;
	}

	public ActionForward stafffileupload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : stafffileupload : Starting");

		try {

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : stafffileupload : Ending");

		return mapping.findForward(MessageConstants.STAFF_EXCEL_UPLOAD);
	}

	public ActionForward destWiseStudentdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_STUDENTREPORTDESTINATIONDETAILS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);


		return mapping.findForward(MessageConstants.DESTINATIONWISESTUDENT);

	}

	public ActionForward transportFeeCollection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportFeeCollection Starting");
		
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTFEE);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			if(schoolLocation.equalsIgnoreCase("all")){
				schoolLocation="%%";
			}
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			
			request.setAttribute("ClassList", new ClassBD().getClassDetails(schoolLocation,custdetails));

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(custdetails);
			request.setAttribute("classlist", classlist);
			String userType = userDetailVO.getUserNametype();
			String userCode = userDetailVO.getUserId();
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			if (academic_year == null || academic_year == ""|| academic_year.equalsIgnoreCase("")) 
			{
				//.out.println("HelperClass.getCurrentYearID()"
						//+ HelperClass.getCurrentYearID(custdetails));
				academic_year = HelperClass.getCurrentYearID(custdetails);
			}

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			list = new StudentRegistrationDaoImpl().getStudentListForTransport(academic_year,schoolLocation,custdetails);

			request.setAttribute(MessageConstants.STUDENTDETAILSLIST, list);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			request.setAttribute("historysearch", request.getParameter("historysearch"));

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportFeeCollection Ending");
		return mapping.findForward(MessageConstants.TRANSPORTFEELIST);

	}

	public ActionForward studentDestList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentDestList Starting");
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_STUDENTREPORTDESTINATIONDETAILS);
		List<StudentRegistrationVo> desstudentlist = null;
		String transid = request.getParameter("transcategory");
		String transloc = request.getParameter("translocation");
		//.out.println("what transaction id is printing:" + transid);
		//.out.println("what transport location is printing:" + transloc);
		if (transid != null && transloc != null) {

			desstudentlist = new StudentRegistrationDelegate()
			.getTranscationcategory(transloc);
		}

		request.setAttribute("desstudentlist", desstudentlist);
		request.getSession(false).setAttribute("transloc", transloc);
		//.out.println("What it is printing:" + desstudentlist);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentDestList Ending");

		return mapping.findForward(MessageConstants.DESTINATIONWISESTUDENT);

	}

	public ActionForward destinationWiseStudentDetailsPDF(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : destinationWiseStudentDetailsPDF Starting");

		// String transloc=request.getSession().getAttribute("transloc");
		//.out.println("transloc"
				//+ request.getSession().getAttribute("transloc"));
		try {

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			Map mapdata = new HashMap();

			mapdata.put("SchoolName", schName);
			mapdata.put("address", schAddLine1);
			mapdata.put("Image", PropfilePath);
			mapdata.put("transloc",
					request.getSession().getAttribute("transloc"));

			String filepath = request
					.getRealPath("Reports/destinationWiseStudentPDF.jrxml");

			JasperDesign design = JRXmlLoader.load(filepath);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, JDBCConnection.getConnection(pojo));
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "destinationWiseStudentList" + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
			outstream.flush();

		} catch (Exception e) {
			e.printStackTrace();

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : destinationWiseStudentDetailsPDF Ending");


		return null;
	}

	public ActionForward destinationWiseStudentXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : destinationWiseStudentXL Starting");
		try {

			Map mapdata = new HashMap();
			mapdata.put("transloc",request.getSession().getAttribute("transloc"));
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/destinationWiseStudentXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					mapdata, JDBCConnection.getConnection(pojo));
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/destinationWiseStudentXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "DestinationWiseStudent Details" };
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
					request.getRealPath("Reports/destinationWiseStudentXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=destinationWiseStudentXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			stream.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : destinationWiseStudentXL Ending");


		return null;
	}

	public ActionForward studentIdCreation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentIdCreation Starting");


		try {



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentIdCreation Ending");
		return mapping.findForward("studentIdCreate");

	}

	public ActionForward schoolList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : schoolList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LOCATION);

			
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();

			String SearchName = request.getParameter("searchText");  
			String status = request.getParameter("status");
			 
			if (SearchName != null) {
				list = obj.searchLocationList(SearchName.trim(),status,dbdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			
			}else {
				list = obj.getLocationList(dbdetails);
				
			}
			LocationDao dao=new LocationDAOImpl();
			
			String locationstatus= dao.getLocationCount(dbdetails);
			
			request.setAttribute("locationDetailsList", list);
			request.setAttribute("status",status);
			/*if(list.size() >0){
				request.setAttribute("schlstatus",list.get(0).getCuststatus()); 
			}
			else{
				request.setAttribute("schlstatus","inactive"); 
			}*/
			request.setAttribute("schlstatus",locationstatus);


		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : schoolList Ending");
		return mapping.findForward(MessageConstants.LOCATIONLIST);
	}

	public ActionForward addSchool(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addSchool Starting");
		try {
			String arg = "Add New School";
			 
			request.setAttribute("Location", arg);

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LOCATION);

			request.setAttribute("operation","Add");
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addSchool Ending");

		return mapping.findForward(MessageConstants.ADD_LOCATION);

	}

	public ActionForward casteDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : casteDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTE);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
			List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();
			String religionId = "%%";
			String SearchName = request.getParameter("searchname"); 
			String sts = request.getParameter("status");
			
			if (SearchName != null) {
				list = obj.searchCaste(SearchName.trim(),sts,custdetails);
				request.setAttribute("searchname", SearchName);
			}
			else{
			      list = obj.getCasteDetails(religionId,custdetails);
			}
			request.setAttribute("religionList", list);
			request.setAttribute("status", sts);
			
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			request.setAttribute("historysearchname", request.getParameter("historysearchname"));
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : casteDetails Ending");

		return mapping.findForward(MessageConstants.CASTE_LIST);
	}

	public ActionForward casteCategoryDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : casteCategoryDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CASTECATEGORY);

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
			List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();

			String SearchName = request.getParameter("searchname"); 
			String status = request.getParameter("status");
			
			if (SearchName != null) {
				list = obj.searchCasteCategory(SearchName.trim(),status,custdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				list = obj.getCasteCategoryDetails(custdetails);
			}

			request.setAttribute("religionList", list);
			request.setAttribute("status", status);
			
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			request.setAttribute("historysearchname", request.getParameter("historysearchname"));
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : casteCategoryDetails Ending");

		return mapping.findForward(MessageConstants.CASTE_CATEGORY_LIST);
	}

	public ActionForward religionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : religionDetails Starting");

		ActionForward forward = null;
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_RELIGION);

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
			List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();

			String SearchName = request.getParameter("searchname"); 
			String status = request.getParameter("status");
			String searchbox = request.getParameter("searchbox");
			
			if (SearchName != null && searchbox.equalsIgnoreCase("searchbox")) {
				list = obj.searchReligion(SearchName.trim(),status,custdetails);
				JSONObject json = new JSONObject();
				json.put("list", list);
				response.getWriter().print(json);
				
			}else if(SearchName != null && searchbox.equalsIgnoreCase("searchbox")){
				list = obj.searchReligion("%",status,custdetails);
				JSONObject json = new JSONObject();
				json.put("list", list);
				response.getWriter().print(json);
			}
			else {
 				list = obj.getReligionDetails(custdetails);
 				forward =  mapping.findForward(MessageConstants.RELIGION_LIST);
 				request.setAttribute("religionList", list);
 				request.setAttribute("status", status);
 				request.setAttribute("length", list.size());
			}
			
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			request.setAttribute("historysearchname", request.getParameter("historysearchname"));
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : religionDetails Ending");

		return forward;
	}

	public ActionForward SpecializationList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : specializationList Starting");
		try {
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SPECIALIZATIONDETAILS);
			
			String locId=request.getParameter("locId");
			String streamId=request.getParameter("streamId"); 
			String classname=request.getParameter("classname");
			String status=request.getParameter("status");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			/*ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);*/
			
			SpecializationBD obj = new SpecializationBD();
			ArrayList<SpecializationVo> list = new ArrayList<SpecializationVo>();
			String searchterm = request.getParameter("searchText");

			if (searchterm != null) {
				list = obj.getSearchSpecializationList(searchterm.trim(),request.getParameter("SchoolName"),custdetails);
				request.setAttribute("searchname", searchterm);
				request.setAttribute("searchnamelist", searchterm);

			} else {
				list = obj.getspecializationList(schoolLocation,custdetails);
			}

			request.setAttribute("SpecializationList", list);
			
			request.setAttribute("locId",locId);
			request.setAttribute("streamId",streamId);
			request.setAttribute("classname",classname);
			request.setAttribute("status",status);
			
			request.setAttribute("currentstatus",request.getParameter("currentstatus"));
			request.setAttribute("curr_loc", schoolLocation);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : SpecializationList Ending");


		return mapping.findForward(MessageConstants.SPECIALIZATION_LIST);
	}

	public ActionForward addSpecialization(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addSpecialization Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String title = "New Specialization";
			request.setAttribute("title", title);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_SPECIALIZATIONDETAILS);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
		} catch (Exception e) {

			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addSpecialization Ending");


		return mapping.findForward(MessageConstants.ADD_SPECIALIZATION);
	}

	public ActionForward occupationDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : occupationDetails Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_OCCUPATION);

			ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
			List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();

			String SearchName = request.getParameter("searchname"); 
			String status = request.getParameter("status");
			
			if (SearchName != null) {
				list = obj.getOccupationDetailsSearch(SearchName.trim(),status,custdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
			 
				list = obj.getOccupationDetails(custdetails);
			}

			request.setAttribute("religionList", list);
			request.setAttribute("status", status);
			
			request.setAttribute("historysearchname", request.getParameter("historysearchname"));
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : occupationDetails Ending");

		return mapping.findForward(MessageConstants.OCCUPATION_LIST);
	}

	public ActionForward saveChangeImage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : saveChangeImage Starting");

		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			//.out.println("Inside the changeImage");
			InventoryTransactionForm formObj = (InventoryTransactionForm) form;
			//.out.println("formObj :" + formObj);
			FormFile file = formObj.getInputfile();
			String filepath = null, base = null, filecuurentpath = null;
			BufferedImage bufferedImage = null;
			filecuurentpath = request.getRealPath("/")+ "CSS/images/aryawatermark.jpg";
			//.out.println(filecuurentpath);
			File f1 = new File(filecuurentpath);
			if (f1 != null) {
				f1.delete();
			}

			String extension = "";
			int j = (file).getFileName().lastIndexOf('.');
			if (j >= 0) {
				base = (String) ((j == -1) ? file : (file).getFileName()
						.substring(0, j));
				extension = (file).getFileName().substring(j + 1);
				base = "aryawatermark";
			}
			//.out.println("extension is " + extension);
			if (extension.equalsIgnoreCase("jpg")) {

				filepath = request.getRealPath("/")+ "CSS/images/" + base + "." + extension;

				//.out.println(filepath);
				byte[] btDataFile = (file).getFileData();
				File of = new File(filepath);
				FileOutputStream osf = new FileOutputStream(of);
				osf.write(btDataFile);
				osf.close();
			} else if (extension.equalsIgnoreCase("jpeg")) {

				filepath = request.getRealPath("/")+ "CSS/images/" + base + "." + extension;
				//.out.println(filepath);

				byte[] btDataFile = (file).getFileData();
				File of = new File(filepath);
				FileOutputStream osf = new FileOutputStream(of);
				osf.write(btDataFile);
				osf.close();
			} else if (extension.equalsIgnoreCase("png")) {
				filepath = request.getRealPath("/")+ "CSS/images/" + base + "." + extension;
				//.out.println(filepath);

				HelperClass.recordLog_Activity(log_audit_session,"Settings","ChangeBackground","Insert",filepath,custdetails);
				// read image file
				bufferedImage = ImageIO.read(new File(filepath));

				// create a blank, RGB, same width and height, and a white
				// background
				BufferedImage newBufferedImage = new BufferedImage(
						bufferedImage.getWidth(), bufferedImage.getHeight(),BufferedImage.TYPE_INT_RGB);
				newBufferedImage.createGraphics().drawImage(bufferedImage, 0,0, Color.WHITE, null);

				// write to jpeg file
				ImageIO.write(newBufferedImage,"jpg",new File(request.getRealPath("/")+"CSS/images/aryawatermark.jpg"));

			}
			// byte[] btDataFile = (file).getFileData();

			// ImageIO.write(image, "png",new File("C:\\out.png"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : saveChangeImage Ending");

		return null;
	}

	public ActionForward holidaymaster(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : holidaymaster Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_HOLIDAY);

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");

			HolidayMasterBD obj = new HolidayMasterBD();
			ArrayList<HolidayMasterVo> list = new ArrayList<HolidayMasterVo>();

			String searchterm = request.getParameter("location"); 
			String status = request.getParameter("status");
			
			if (searchterm != null) {
				list = obj.searchLocationDetails(searchterm.trim(),academic_year,status,custdetails);
				request.setAttribute("searchnamelist", searchterm);
			} else {
				list = obj.getHolidayDetails(academic_year,schoolLocation,custdetails);
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails); 
			request.setAttribute("locationList", locationList);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			
			request.setAttribute("HolidayList", list);
			request.setAttribute("status", status);
			request.setAttribute("academic_year", academic_year);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacyearId", request.getParameter("historyacyearId"));
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : holidaymaster Ending");

		return mapping.findForward(MessageConstants.HOLIDAY_LIST);
	}

	public ActionForward addHoliday(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addHoliday Starting");
		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_HOLIDAY);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("currentyear",academic_year);
			request.setAttribute("startdate",HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0]);
			request.setAttribute("enddate",HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1]);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addHoliday Ending");

		return mapping.findForward(MessageConstants.ADD_HOLIDAY);
	}
	public ActionForward transportTermList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportTermList Starting");

		try {
			
			String status = request.getParameter("result");

			if (status != null) {

				if (status.equalsIgnoreCase(MessageConstants.TERM_SUCCESS)) {

					request.setAttribute("successmessagediv",
							MessageConstants.TERM_SUCCESS);
				}
			}

			String name = request.getParameter("searchvalue");
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

			TermMasterVo vo = new TermMasterVo();

			vo.setTermname(name);

			request.setAttribute("searchterm",
					request.getParameter("searchvalue"));

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate()
			.termList(vo,custdetails);
			request.setAttribute("termlist", termlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportTermList Ending");

		return mapping.findForward(MessageConstants.TERM_LIST);

	}

	public ActionForward TdsSlabDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : TdsSlabDetails Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : TdsSlabDetails Ending");

		return mapping.findForward(MessageConstants.TDS_SLAB_DETAILS);
	}

	public ActionForward TDSComputationDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : TDSComputationDetails Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFRENUMERATION);

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			UserDetailVO user=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userId=request.getParameter("teachercode");
			String userType=user.getUserNametype();
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			if (academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			if(location.equalsIgnoreCase("all")){
				location="%%";
			}
			
			StaffEmployementVo list = new StaffEmployementVo();
			StaffEmployementVo list1 = new StaffEmployementVo();
			
			list = new TDSComputationBD().getEmployeeDetails(userType,userId,academic_year,userLoggingsVo);
			
			request.setAttribute("list", list);
			
			request.setAttribute("SchoolName", HelperClass.getSchoolName(list.getLocationId(), userLoggingsVo));
			list1 = new TDSComputationBD().getStaffMaximumLimitDetails(academic_year,location,userLoggingsVo);
			
			request.setAttribute("maximumlist", list1);
			
			String year = new StudentRegistrationDaoImpl().getSingleAcademicYear(academic_year,userLoggingsVo);
			
			request.setAttribute("academic_year", year);
			request.setAttribute("teacherCode", userId);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historydepartment", request.getParameter("historydepartment"));
			request.setAttribute("historysearchname", request.getParameter("historysearchname"));
			request.setAttribute("Itdeclaration", "Itdeclaration");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : TDSComputationDetails Ending");

		return mapping.findForward(MessageConstants.TDS_COMPUTATION_DETAILS);
	}

	public ActionForward stagefileupload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : stagefileupload : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_UPLOADSTAGEEXCELDATAFILE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : stagefileupload : Ending");

		return mapping.findForward(MessageConstants.STAGE_EXCEL_UPLOAD);
	}

	public ActionForward gettimetablelist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gettimetablelist Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_TIMETABLEMANAGEMNET);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String classId = request.getParameter("classId");
			String viewBy = request.getParameter("viewBy");
			String locationId=request.getParameter("locationId");
			String accyear=request.getParameter("accyer");
			String section=request.getParameter("section");
			
			if(locationId==null){
				locationId="%%";
			}else if(locationId.equalsIgnoreCase("ALL")){
				locationId="%%";
			}
			
			if(classId==null){
				classId="%%";
			}else if(classId.equalsIgnoreCase("ALL")){
				classId="%%";
			}
			
			if(section==null){
				section="%%";
			}else if(section.equalsIgnoreCase("ALL")){
				section="%%";
			}
			 
			/*
			 * String accyearid =
			 * request.getAttribute("current_academicYear").toString();
			 */
			String accyearid = request.getSession(false).getAttribute("current_academicYear").toString();
			/* String accyearid = HelperClass.getCurrentYearID(); */

			//.out.println("viewBy::::::" + viewBy);
			ArrayList<TimeTableVo> arr =null;
			if (classId != null) {
				
				arr= new TimeTableBD().getClassTimeTableListByClass(accyear,viewBy,classId,locationId,custdetails,section);
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("ClassTimeTableList", arr);
				response.getWriter().print(jsonobj);
			}
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gettimetablelist Ending");

		return null;

	}

	public ActionForward gettimetablelistbysection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gettimetablelistbysection Starting");

		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_TIMETABLEMANAGEMNET);
			
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			String accyearid = request.getSession(false)
					.getAttribute("current_academicYear").toString();

			if (classId != null) {
				ArrayList<TimeTableVo> arr = new TimeTableBD()
				.getClassTimeTableListBySection(accyearid, classId,sectionId,custdetails);
				JSONObject jsonobj = new JSONObject();

				jsonobj.put("ClassTimeTableList", arr);

				response.getWriter().print(jsonobj);
			}

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gettimetablelistbysection Ending");

		return null;

	}

	public ActionForward tempadmissionMenu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminAction : tempadmissionMenu  Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_ADMISSION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_ADMIN);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_ADMISSION_REGISTRATION);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String accyearid = (String) request.getSession(false).getAttribute("current_academicYear");
			
			TemporaryRegBD obj = new TemporaryRegBD();
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
			
			String SearchName = request.getParameter("searchname");
			//.out.println("SearchName From Action:" +SearchName);
			
			if (SearchName != null) {
				list = obj.searchadmformDetails(SearchName.trim()+","+schoolLocation+","+accyearid,custdetails);
				request.setAttribute("searchname", SearchName);

			} else {
				list = new TemporaryRegBD().getissuedforms("%%",custdetails,schoolLocation+","+accyearid); 

			}
			request.setAttribute("issuedList", list);
			
			request.setAttribute("historysearch", request.getParameter("historysearch"));
			request.setAttribute("historytabstatus", request.getParameter("historytabstatus"));
			
		/*	
			ArrayList<Issuedmenuvo> getprocessedlist = new TemporaryRegBD()
			.getProcessedForms();
			request.setAttribute("getprocessedlist", getprocessedlist);*/

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminAction : tempadmissionMenu Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
	}

	public ActionForward staffExcelFileUpload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : staffExcelFileUpload : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_UPLOADSTAFFEXCELDATAFILE);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffExcelFileUpload : Ending");

		return mapping.findForward(MessageConstants.STAFF_EXCEL_FILE_UPLOAD);
	}

	public ActionForward studentIDGeneration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentIDGeneration Starting");


		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentIDGeneration Ending");

		return mapping.findForward(MessageConstants.STUDENTIDGENERATION);
	}

	public ActionForward studentBusIDGeneration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentBusIDGeneration Starting");


		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_PRINTBUSIDCARD);




		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentBusIDGeneration Ending");

		return mapping.findForward(MessageConstants.STUDENTBUSIDGENERATION);
	}
	public ActionForward searchStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchStudent Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			List<StudentIDVo> studentlist = new ArrayList<StudentIDVo>();

			String acadamicYear = request.getParameter("acadamicyear");
			String section = request.getParameter("section");
			String classVal = request.getParameter("class");
			studentlist = new StudentIDDaoImpl().getStudentData(acadamicYear,
					section, classVal,custdetails);

			JSONObject jsonobject = new JSONObject();
			jsonobject.accumulate("studentList", studentlist);
			response.getWriter().print(jsonobject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchStudent Ending");

		return null;
	}

	public ActionForward studentIdCreationPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentIdCreationPrint Starting");
		String accyear = request.getParameter("AccId");
		String section = request.getParameter("Section");
		String className = request.getParameter("Class");
		String student = request.getParameter("studentname");


		try {

			List<StudentIDVo> studentList = new StudentIDBD().getstudentIDPDFReport(accyear, section, className, student);
			List<StudentIDVo> list = new ArrayList<StudentIDVo>();

			for (int i = 0; i < studentList.size(); i++) {

				StudentIDVo vo = new StudentIDVo();
				String fileName = studentList.get(i).getImage();
				String filePath = request.getRealPath("/")+ "FIles/STUDENTIMAGES/" + fileName.trim();
				vo.setImages(".\\FIles\\STUDENTIMAGES\\" + fileName.trim());

				vo.setStuName(studentList.get(i).getStuName());
				vo.setClassName(studentList.get(i).getClassName() + "&"
						+ studentList.get(i).getSection());
				vo.setSection(studentList.get(i).getSection());
				vo.setFatherName(studentList.get(i).getFatherName());
				vo.setAdress(studentList.get(i).getAdress());
				vo.setPhone(studentList.get(i).getPhone());
				vo.setSecodaryPhone(studentList.get(i).getSecodaryPhone());
				vo.setValidity(studentList.get(i).getValidity());
				vo.setAdmissionno(studentList.get(i).getAdmissionno());
				vo.setMotherName(studentList.get(i).getMotherName());
				list.add(vo);

			}

			JSONObject jsonobject = new JSONObject();
			jsonobject.accumulate("studentList", list);
			response.getWriter().print(jsonobject);
			request.setAttribute("StudentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentIdCreationPrint Ending");
		return null;

	}

	public ActionForward Issuedsearchdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : Issuedsearchdetails Starting");

		try {

			String message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();

			String searchTerm = request.getParameter("searchname");
			ViewallSubjectsVo obj = new ViewallSubjectsVo();
			obj.setSearchName(searchTerm);
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			if (searchTerm != null) {

				subjectlist = new AddSubjectDelegate()
				.searchsubjectdetails(obj, custdetails);

				request.setAttribute("searchTerm", searchTerm);
				request.setAttribute("searchnamelist", searchTerm);

			} else {

				subjectlist = new AddSubjectDelegate().subjectdetails(schoolLocation,custdetails);

			}

			request.setAttribute("allsubjects", subjectlist);

			request.setAttribute("successmessagediv", message);

			request.getSession(false)
			.setAttribute("EXcelDownLoad", subjectlist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : Issuedsearchdetails Ending");

		return mapping.findForward(MessageConstants.SUBJECT_LIST);
	}

	public ActionForward issuedformEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : issuedformEdit Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMISSION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_ADMISSION_REGISTRATION);
			
			String edit = request.getParameter("curenquiryid");
			
			Issuedmenuvo edit_issuedlist = new TemporaryRegBD().editIssuedForm(edit,userLoggingsVo);
			request.setAttribute("edit_issuedlist", edit_issuedlist);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			List<ClassPojo> streamList = new ClassBD().getStreamDetailBD(edit_issuedlist.getLocation_id(),userLoggingsVo);
			request.setAttribute("StreamList", streamList);
			
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream(edit_issuedlist.getStreamId(),userLoggingsVo);
			request.setAttribute("ClassList", classList);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			
			request.setAttribute("historysearch", request.getParameter("historysearch"));
			request.setAttribute("historytabstatus", request.getParameter("historytabstatus"));
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : issuedformEdit Ending");
		return mapping.findForward(MessageConstants.DETAILS_ISSUED_FORM_DETAILS);

	}

	public ActionForward EditissuedList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		        logger.setLevel(Level.DEBUG);
		       JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		      logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : EditissuedList Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMISSION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_ADMISSION_REGISTRATION);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String edit = request.getParameter("enquiryid");
			
			Issuedmenuvo edit_issuedlist = new TemporaryRegBD().editIssuedForm(edit,userLoggingsVo);
			request.setAttribute("edit_issuedlist", edit_issuedlist);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			/*List<ClassPojo> streamList = new ClassBD().getStreamDetailBD(edit_issuedlist.getLocation_id(),userLoggingsVo);
			request.setAttribute("StreamList", streamList);
			
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream(edit_issuedlist.getStreamId(),userLoggingsVo);
			request.setAttribute("ClassList", classList);*/
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			
			request.setAttribute("historysearch", request.getParameter("historysearch"));
			request.setAttribute("historytabstatus", request.getParameter("historytabstatus"));
			
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : EditissuedList Ending");
		return mapping.findForward(MessageConstants.EDIT_ISSUED_FORM_DETAILS);
	}

	public ActionForward admissionaddmenu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : admissionaddmenu Starting");

		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_ADMISSION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_ADMIN);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_ADMISSION_REGISTRATION);
		
		String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
		request.setAttribute("locationList", locationList);
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
		 request.setAttribute("AccYearList", accYearList);
		 
		 request.setAttribute("academic_year", academic_year);
		 request.setAttribute("curr_loc", schoolLocation);
		 request.setAttribute("historysearch", request.getParameter("historysearch"));
		 request.setAttribute("historytabstatus", request.getParameter("historytabstatus"));
		 
		/*ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream();
		request.setAttribute("StreamList", streamList);*/
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : admissionaddmenu Starting");
		
		return mapping.findForward(MessageConstants.ADD_ADMISSION_FORM_DETAILS);
	}

	public ActionForward updateissuelist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : updateissuelist Starting");
		
		String firstname = request.getParameter("parentfirstName");
		String lastname = request.getParameter("parent_LastName");
		String parname = request.getParameter("parents_name");
		String paremailid = request.getParameter("parentEmailId");
		String address = request.getParameter("address");
		String stu_parrelation = request.getParameter("stu_parrelation");
		String mobile_no = request.getParameter("mobile_number");
		String stream = request.getParameter("stream");
		String classid = request.getParameter("classid");
		String enquriyid = request.getParameter("enq_code");
		String Acyearid = request.getParameter("Acyearid");
		
		Issuedmenuvo vo = new Issuedmenuvo();
		vo.setParentfirstName(firstname);
		vo.setParent_LastName(lastname);
		vo.setParents_name(parname);
		vo.setParentEmailId(paremailid);
		vo.setStu_parrelation(stu_parrelation);
		vo.setMobile_number(mobile_no);
		vo.setStreamId(stream);
		vo.setClassid(classid);
		vo.setAddress(address);
		vo.setAccyearId(Acyearid);
		vo.setLog_audit_session((String) request.getSession(false).getAttribute("log_audit_session"));
		vo.setPojo((UserLoggingsPojo) request.getSession(false).getAttribute("token_information"));		
		
		ReportsMenuBD details = new ReportsMenuBD();
		String result = details.insertadmissionDetailsAction(vo,enquriyid);
		JSONObject object = new JSONObject();
		object.put("status", result);
		response.getWriter().print(object);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : updateissuelist Ending");


		return null;

	}

	public ActionForward IdDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : IdDesign Starting");
		
		String mainCss = request.getParameter("mainCss");
		String layout=request.getParameter("layout");
		String imgUrl=request.getParameter("imgUrl");
		//.out.println("imgurl"+imgUrl);
		
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		//.out.println("realimgurl"+request.getRealPath("/")+imgUrl);
		if(!imgUrl.equalsIgnoreCase("noImage")){
			
			 FileInputStream is = null;
			         FileOutputStream os = null;
			         try {
			        	 File sourceFile=new File(request.getServletContext().getRealPath("/")+imgUrl);
			        	 File desFile=new File(request.getServletContext().getRealPath("/")+"images/IdCard/"+layout+".jpg");
			             is = new FileInputStream(sourceFile);
			             os = new FileOutputStream(desFile);
			             byte[] buffer = new byte[1024];
			             int length;
			             while ((length = is.read(buffer)) > 0) {
			                 os.write(buffer, 0, length);
			             }
			         }catch(Exception ex) {
			             //.out.println("Unable to copy file:"+ex.getMessage());
			         }   
			         finally {
			             try {
			                 is.close();
			                 os.close();
			             }catch(Exception ex) {}
			         }
			     }
		
		Date date= new Date();
		long time = date.getTime();
		String newCssArray[] = mainCss.split("}");
		
		File file = new File(request.getServletContext().getRealPath("/")+ "CSS/IdCard/"+layout+".css");
		if(file.exists()){
			
		}
		else{
			file.createNewFile();
		}
		
		for (String css : newCssArray) {
			//.out.println("each css" + css);

		}
		BufferedWriter bw = null;
		FileWriter fw = null;
		BufferedReader br = null;
		FileReader fr = null;
		String FILENAME =request.getServletContext().getRealPath("/")+ "CSS/IdCard/"+layout+".css";
		File temp=null;
		temp = new File("IdCard.css");
		String absolutePath = temp.getAbsolutePath();
		//.out.println("absolutePath  "+absolutePath);
		try {

			//.out.println(FILENAME);

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));
			HelperClass.recordLog_Activity(log_audit_session,"Settings","Design Student ID Card","Design",absolutePath,userLoggingsVo);
			while ((sCurrentLine = br.readLine()) != null) {
				//.out.println(sCurrentLine);
				String[] words = sCurrentLine.split("\\s");
				String sCurrentLineArray[] = sCurrentLine.split("}");
				for (String css : words) {
					//.out.println("each css" + css);
				}
			}

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(mainCss);

			//.out.println("Done");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : IdDesign Ending");
		return null;
	}

	public ActionForward ApprformDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		   logger.setLevel(Level.DEBUG);
		  JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :ApprformDetails Starting");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMISSION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_ADMISSION_REGISTRATION);
			
			String edit = request.getParameter("curenquiryid");
			
			Issuedmenuvo edit_issuedlist = new TemporaryRegBD().apprIssuedForm(edit,userLoggingsVo);
			request.setAttribute("edit_issuedlist", edit_issuedlist);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			/*List<ClassPojo> streamList = new ClassBD().getStreamDetailBD(edit_issuedlist.getLocation_id(),userLoggingsVo);
			request.setAttribute("StreamList", streamList);
			
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream(edit_issuedlist.getStreamId(),userLoggingsVo);
			request.setAttribute("ClassList", classList);*/
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			
			request.setAttribute("historysearch", request.getParameter("historysearch"));
		    request.setAttribute("historytabstatus", request.getParameter("historytabstatus"));
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : ApprformDetails Ending");
		return mapping.findForward(MessageConstants.DETAILS_APPR_FORM_DETAILS);
	}

	public ActionForward RejectformDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		   logger.setLevel(Level.DEBUG);
		     JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		    logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : RejectformDetails Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMISSION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_ADMISSION_REGISTRATION);
			
			String edit = request.getParameter("curenquiryid");
			
			Issuedmenuvo edit_issuedlist = new TemporaryRegBD().rejectFormdetails(edit,userLoggingsVo);
			request.setAttribute("edit_issuedlist", edit_issuedlist);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			List<ClassPojo> streamList = new ClassBD().getStreamDetailBD(edit_issuedlist.getLocation_id(),userLoggingsVo);
			request.setAttribute("StreamList", streamList);
			
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream(edit_issuedlist.getStreamId(),userLoggingsVo);
			request.setAttribute("ClassList", classList);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			
			request.setAttribute("historysearch", request.getParameter("historysearch"));
		    request.setAttribute("historytabstatus", request.getParameter("historytabstatus"));
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : RejectformDetails Ending");
		return mapping.findForward(MessageConstants.DETAILS_REJECT_FORM_DETAILS);
	}

	public ActionForward searchapprove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchapprove  Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			TemporaryRegBD obj = new TemporaryRegBD();
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchapproveformDetails(SearchName.trim());
				request.setAttribute("searchname", SearchName);
				ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms(SearchName,custdetails,schoolLocation+","+academic_year);
				request.setAttribute("issuedList", issuedList);
				ArrayList<Issuedmenuvo> rejectlist = new TemporaryRegBD().getRejectedlist("%%",custdetails,academic_year,schoolLocation);
				request.setAttribute("rejectlist", rejectlist);
				ArrayList<Issuedmenuvo> getcancelledlist = new TemporaryRegBD().getCancelledForms("%%",custdetails,academic_year,schoolLocation);
				request.setAttribute("getcancelledlist", getcancelledlist);

				ArrayList<Issuedmenuvo> getsubmittedlist = new TemporaryRegBD().getSubmittedForms("%%",custdetails);
				request.setAttribute("getsubmittedlist", getsubmittedlist);

				ArrayList<Issuedmenuvo> getprocessedlist = new TemporaryRegBD().getProcessedForms("%%",custdetails,academic_year,schoolLocation);
				request.setAttribute("getprocessedlist", getprocessedlist);


			} else {
				list = obj.getApprovedForms("%%",custdetails,academic_year,schoolLocation);
				request.setAttribute("getapprvedlist", list);
			}
			request.setAttribute("getapprvedlist",list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchapprove Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
	}

	public ActionForward searchreject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchreject  Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
			
			TemporaryRegBD obj = new TemporaryRegBD();
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchrejectformDetails(SearchName.trim());
				request.setAttribute("SearchName", SearchName);
				ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms(SearchName,custdetails,schoolLocation+","+accYear);
				request.setAttribute("issuedList", issuedList);
				ArrayList<Issuedmenuvo> getapprvedlist = new TemporaryRegBD()
				.getApprovedForms("%%",custdetails,accYear,schoolLocation);

				request.setAttribute("getapprvedlist", getapprvedlist);		

				ArrayList<Issuedmenuvo> getcancelledlist = new TemporaryRegBD()
				.getCancelledForms("%%",custdetails,accYear,schoolLocation);
				request.setAttribute("getcancelledlist", getcancelledlist);

				ArrayList<Issuedmenuvo> getsubmittedlist = new TemporaryRegBD()
				.getSubmittedForms("%%",custdetails);
				request.setAttribute("getsubmittedlist", getsubmittedlist);

				ArrayList<Issuedmenuvo> getprocessedlist = new TemporaryRegBD()
				.getProcessedForms("%%",custdetails,accYear,schoolLocation);
				request.setAttribute("getprocessedlist", getprocessedlist);
			} else {
				list = new TemporaryRegBD().getRejectedlist("%%",custdetails,accYear,schoolLocation);
			}
			request.setAttribute("rejectlist", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchreject Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
	}

	public ActionForward secondadmissionformat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : secondadmissionformat Starting");


		HttpSession session = request.getSession(true);
		session.setAttribute("user", "registration");

		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : secondadmissionformat Ending");


		return mapping.findForward(MessageConstants.SECONDADMISSIONFORMAT);

	}

	public ActionForward thirdadmissionformat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : thirdadmissionformat Starting");
		HttpSession session = request.getSession(true);
		session.setAttribute("user", "registration");
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : thirdadmissionformat Ending");

		return mapping.findForward(MessageConstants.THIRDADMISSIONFORMAT);

	}

	public ActionForward AddNewAdmissionApplication(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddNewAdmissionApplication Starting");

		//
		// logger.setLevel(Level.DEBUG);
		// JLogger.log(0, JDate.getTimeString(new Date())
		// + MessageConstants.START_POINT);
		// logger.info(JDate.getTimeString(new Date())
		// + " Control in AdminMenuAction : streamList Starting");
		// try {
		//
		// ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
		// .getAccYears();
		// request.setAttribute("AccYearList", accYearList);
		//
		// ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
		// .getClassesByStream("%%");
		//
		// ArrayList<ReportMenuVo> List = new ArrayList<ReportMenuVo>();
		// for (int i = 0; i < classList.size(); i++) {
		//
		// ReportMenuVo list = new ReportMenuVo();
		//
		// String className = classList.get(i).getClassname();
		// if (!(className.equalsIgnoreCase("X")
		// || className.equalsIgnoreCase("XI") || className
		// .equalsIgnoreCase("XII"))) {
		//
		// list.setClassname(className);
		// List.add(list);
		// }
		// }
		//
		// request.setAttribute("classList", List);
		//
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// e.printStackTrace();
		// }
		//
		// JLogger.log(0, JDate.getTimeString(new Date())
		// + MessageConstants.END_POINT);
		// logger.info(JDate.getTimeString(new Date())
		// + " Control in AdminMenuAction : streamList Ending");

		HttpSession session = request.getSession(true);
		session.setAttribute("user", "registration");
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddNewAdmissionApplication Ending");

		return mapping.findForward(MessageConstants.ADD_NEW_APPLICATION);
	}

	public ActionForward CancelformDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
	    	logger.setLevel(Level.DEBUG);
		   JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		     logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : CancelformDetails Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMISSION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_ADMISSION_REGISTRATION);
			
			String edit = request.getParameter("curenquiryid");
			Issuedmenuvo cancel_list = new TemporaryRegBD().cancelFormdetails(edit,userLoggingsVo);

			request.setAttribute("edit_issuedlist", cancel_list);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			List<ClassPojo> streamList = new ClassBD().getStreamDetailBD(cancel_list.getLocation_id(),userLoggingsVo);
			request.setAttribute("StreamList", streamList);
			
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream(cancel_list.getStreamId(),userLoggingsVo);
			request.setAttribute("ClassList", classList);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			
			request.setAttribute("historysearch", request.getParameter("historysearch"));
		    request.setAttribute("historytabstatus", request.getParameter("historytabstatus"));
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : CancelformDetails Ending");
		return mapping.findForward(MessageConstants.DETAILS_CANCELLED_FORM_DETAILS);
	}

	public ActionForward separateTransportTermList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : separateTransportTermList Starting");
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		String status = request.getParameter("result");

		

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TERMSETUP);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			if (status != null) {

				if (status.equalsIgnoreCase(MessageConstants.TERM_SUCCESS)) {

					request.setAttribute("successmessagediv",MessageConstants.TERM_SUCCESS);
				}
			}
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			String name = request.getParameter("searchvalue".trim());
			
			TermMasterVo vo = new TermMasterVo();

			vo.setTermname(name);
			vo.setAccyear(academic_year);
			
			
			request.setAttribute("searchvalue",request.getParameter("searchvalue".trim()));

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate().separateTransportTermList(vo,custdetails);
			request.setAttribute("termlist", termlist);
			
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historylocId", request.getParameter("historylocId"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : separateTransportTermList Ending");

		return mapping.findForward(MessageConstants.SEPARATE_TRANSPORT_TERM_LIST);
	}

	public ActionForward submitformDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    	logger.setLevel(Level.DEBUG);
	   	JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submitformDetails Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMISSION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_ADMISSION_REGISTRATION);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String edit = request.getParameter("curenquiryid");
			Issuedmenuvo cancel_list = new TemporaryRegBD().submitFormdetails(edit,userLoggingsVo);

			request.setAttribute("edit_issuedlist", cancel_list);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			List<ClassPojo> streamList = new ClassBD().getStreamDetailBD(cancel_list.getLocation_id(),userLoggingsVo);
			request.setAttribute("StreamList", streamList);
			
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream(cancel_list.getStreamId(),userLoggingsVo);
			request.setAttribute("ClassList", classList);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submitformDetails Ending");
		return mapping.findForward(MessageConstants.DETAILS_SUBMITTED_FORM_DETAILS);
	}

	public ActionForward processformDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : processformDetails Starting");

		     
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMISSION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_ADMISSION_REGISTRATION);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String edit = request.getParameter("curenquiryid");
			Issuedmenuvo process_list = new TemporaryRegBD().processFormdetails(edit,userLoggingsVo);

			request.setAttribute("edit_issuedlist", process_list);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			List<ClassPojo> streamList = new ClassBD().getStreamDetailBD(process_list.getLocation_id(),userLoggingsVo);
			request.setAttribute("StreamList", streamList);
			
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream(process_list.getStreamId(),userLoggingsVo);
			request.setAttribute("ClassList", classList);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			
			request.setAttribute("historysearch", request.getParameter("historysearch"));
		    request.setAttribute("historytabstatus", request.getParameter("historytabstatus"));
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : processformDetails Ending");
		
		return mapping.findForward(MessageConstants.DETAILS_PROCESSED_FORM_DETAILS);
	}

	public ActionForward OnlinereturnFormPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : OnlinereturnFormPage Starting");
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : OnlinereturnFormPage Ending");

		return mapping.findForward(MessageConstants.ONLINE_REGISTRATION_RORM_RETURNS);
		

	}

	public ActionForward searchcancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchcancel  Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
			TemporaryRegBD obj = new TemporaryRegBD();
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchcancelformDetails(SearchName.trim(),custdetails);
				request.setAttribute("Searchcancel", SearchName);
				ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms(SearchName,custdetails,schoolLocation+","+accYear);
				request.setAttribute("issuedList", issuedList);
				ArrayList<Issuedmenuvo> getapprvedlist = new TemporaryRegBD().getApprovedForms("%%",custdetails,accYear,schoolLocation);
				request.setAttribute("getapprvedlist", getapprvedlist);		
				ArrayList<Issuedmenuvo> rejectlist = new TemporaryRegBD().getRejectedlist("%%",custdetails,accYear,schoolLocation);
				request.setAttribute("rejectlist", rejectlist);

				ArrayList<Issuedmenuvo> getsubmittedlist = new TemporaryRegBD().getSubmittedForms("%%",custdetails);
				request.setAttribute("getsubmittedlist", getsubmittedlist);

				ArrayList<Issuedmenuvo> getprocessedlist = new TemporaryRegBD().getProcessedForms("%%",custdetails,accYear,schoolLocation);
				request.setAttribute("getprocessedlist", getprocessedlist);

			} else {
				list = new TemporaryRegBD().getCancelledForms("%%",custdetails,accYear,schoolLocation);
			}
			request.setAttribute("getcancelledlist", list);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchcancel Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
	}

	public ActionForward searchsubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchsubmit  Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
			
			TemporaryRegBD obj = new TemporaryRegBD();
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchsubmitformDetails(SearchName.trim(),custdetails);
				request.setAttribute("Searchsubmit", SearchName);
				ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms(SearchName,custdetails,schoolLocation+","+accYear);
				request.setAttribute("issuedList", issuedList);
				ArrayList<Issuedmenuvo> getapprvedlist = new TemporaryRegBD().getApprovedForms("%%",custdetails,accYear,schoolLocation);
				request.setAttribute("getapprvedlist", getapprvedlist);		
				ArrayList<Issuedmenuvo> rejectlist = new TemporaryRegBD().getRejectedlist("%%",custdetails,accYear,schoolLocation);
				request.setAttribute("rejectlist", rejectlist);
				ArrayList<Issuedmenuvo> getcancelledlist = new TemporaryRegBD().getCancelledForms("%%",custdetails,accYear,schoolLocation);
				request.setAttribute("getcancelledlist", getcancelledlist);

				ArrayList<Issuedmenuvo> getprocessedlist = new TemporaryRegBD().getProcessedForms("%%",custdetails,accYear,schoolLocation);
				request.setAttribute("getprocessedlist", getprocessedlist);
			} else {
				list = new TemporaryRegBD().getSubmittedForms("%%",custdetails);
			}
			request.setAttribute("getsubmittedlist", list);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchsubmit Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
	}

	public ActionForward searchprocess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchprocess  Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
			TemporaryRegBD obj = new TemporaryRegBD();
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
			String SearchName = request.getParameter("searchname");
			if (SearchName != null) {
				list = obj.searchprocessformDetails(SearchName.trim(),custdetails);
				request.setAttribute("Searchprocess", SearchName);
				ArrayList<Issuedmenuvo> issuedList = new TemporaryRegBD().getissuedforms(SearchName,custdetails,schoolLocation+","+accYear);
				request.setAttribute("issuedList", issuedList);
				ArrayList<Issuedmenuvo> getapprvedlist = new TemporaryRegBD().getApprovedForms("%%",custdetails,accYear,schoolLocation);
				request.setAttribute("getapprvedlist", getapprvedlist);		
				ArrayList<Issuedmenuvo> rejectlist = new TemporaryRegBD().getRejectedlist("%%",custdetails,accYear,schoolLocation);
				request.setAttribute("rejectlist", rejectlist);
				ArrayList<Issuedmenuvo> getsubmittedlist = new TemporaryRegBD().getSubmittedForms("%%",custdetails);
				request.setAttribute("getsubmittedlist", getsubmittedlist);
				ArrayList<Issuedmenuvo> getcancelledlist = new TemporaryRegBD().getCancelledForms("%%",custdetails,accYear,schoolLocation);
				request.setAttribute("getcancelledlist", getcancelledlist);



			} else {
				list = new TemporaryRegBD().getProcessedForms("%%",custdetails,accYear,schoolLocation);
			}
			request.setAttribute("getprocessedlist", list);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchprocess Ending");

		return mapping.findForward(MessageConstants.TEMPADM_MENU);
	}

	public ActionForward transportCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportCategory Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTCATEGORY);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			TransportBD obj = new TransportBD();
			List<VehicleTypeVo> getvehiclelist = new ArrayList<VehicleTypeVo>();

			String SearchName = request.getParameter("searchText".trim());
			if (SearchName != null) {
				getvehiclelist = obj.searchVehicletypeDetails(SearchName.trim(),custdetails);
				request.setAttribute("searchname", SearchName);
				request.setAttribute("searchnamelist", SearchName);
			} else {
				getvehiclelist= obj.getAllvehicletypeDetails(custdetails);
			}
			request.setAttribute("vehicleTypeList", getvehiclelist);
			
			request.setAttribute("currentstatus", request.getParameter("currentstatus"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportCategory Ending");

		return mapping.findForward(MessageConstants.TRANSPORT_CATEGORY);
	}

	

	public ActionForward driverfileupload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : driverfileupload : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_UPLOADDRIVEREXCELDATAFILE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : driverfileupload : Ending");

		return mapping.findForward(MessageConstants.DRIVER_EXCEL_UPLOAD);
	}

	public ActionForward studentBusCard(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentBusCard Starting");
		
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");
		
		//List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<StudentRegistrationVo> stuList = null;
				
		try {
			
			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_STUDENTBUSCARD);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("Acyearid",academic_year);
			
			if(location.equalsIgnoreCase("all")){
				
				location="%%";
				stuList=new TransportBD().searchAllAcademicYearDetailsTrans(location,academic_year,userLoggingsVo);
			}
			else{
				
				stuList=new TransportBD().searchAllAcademicYearDetailsTrans(location,academic_year,userLoggingsVo);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			request.setAttribute("classlist", classlist);
				

			request.setAttribute("studentList", stuList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentBusCard Ending");
		return mapping.findForward(MessageConstants.STUDENTBUSCARD);
}



	public ActionForward getAcademicNextYear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : getAcademicNextYear : Starting");

		String currentYear=request.getParameter("currentYear");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<String> nextYearList=HelperClass.getNextAccYearDetails(currentYear,custdetails);

			JSONObject obj=new JSONObject();
			obj.put("jsonResponse", nextYearList);
			response.getWriter().print(obj);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAcademicNextYear : Ending");

		return null;
	}


	public ActionForward getClassListForPromotion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : getClassListForPromotion : Starting");
		List<ClassPromotionList> classList=new ArrayList<ClassPromotionList>();
		String currentYear=request.getParameter("currentYear");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			StudentPramotionBD promotedList=new StudentPramotionBD();
			classList=promotedList.getClassListForPromotion(currentYear,custdetails);

			JSONObject obj=new JSONObject();
			obj.put("jsonResponse", classList);
			response.getWriter().print(obj);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getClassListForPromotion : Ending");

		return null;
	}

	public ActionForward busIdDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : busIdDesign Starting");
		String mainCss = request.getParameter("mainCss");
		String newCssArray[] = mainCss.split("}");

		for (String css : newCssArray) {
			//.out.println("each css" + css);

		}
		BufferedWriter bw = null;
		FileWriter fw = null;
		BufferedReader br = null;
		FileReader fr = null;
		String FILENAME = request.getRealPath("/")+ "CSS/BusIdCard.css";
		try {

			//.out.println(FILENAME);

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				//.out.println(sCurrentLine);
				String[] words = sCurrentLine.split("\\s");
				String sCurrentLineArray[] = sCurrentLine.split("}");
				for (String css : words) {
					//.out.println("each css" + css);

				}

			}

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(mainCss);

			//.out.println("Done");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : busIdDesign Ending");
		return null;

	}


	//--------------------------student ID card Design and print----------------------------

	public ActionForward NewstudentIdCardDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Starting");


		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNSTUDENTIDCARD);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("historyaccyear", request.getParameter("historyaccyear"));
			request.setAttribute("historyschoolId", request.getParameter("historyschoolId"));
			request.setAttribute("historystreamId", request.getParameter("historystreamId"));

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Ending");

		return mapping.findForward(MessageConstants.New_StdIDCard_Design);
	}

	public ActionForward NewstudentIdCardPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardPrint Starting");

		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNSTUDENTIDCARD);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(schoolId);
			filterpojo.setAcademicYear(accyear);
			filterpojo.setStreamId(streamId);
		
			
			list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo,userLoggingsVo);

			request.setAttribute("template", list);
			request.setAttribute("locationNmae", list.get(0).getLocationName());
			request.setAttribute("accYearName", list.get(0).getAcademicYear());
			request.setAttribute("locationAddress", list.get(0).getLocation_address());
			request.setAttribute("locationPhone", list.get(0).getLocation_phone());
			request.setAttribute("schoollogo", list.get(0).getSchoollogourl());
			request.setAttribute("schoolName", list.get(0).getSchoolName());
			request.setAttribute("templateClass", accyear+schoolId+streamId);
			request.setAttribute("schoolAddress", list.get(0).getSchoolAddress());
			request.setAttribute("historyaccyear", accyear);
			request.setAttribute("historyschoolId", schoolId);
			request.setAttribute("historystreamId", request.getParameter("historystreamId")); 
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			response.setHeader("Expires", "0");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardPrint Ending");

		return mapping.findForward(MessageConstants.New_StdIDCard_Print);
	}

	public ActionForward StaffSingleIDCardPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffSingleIDCardPrint Starting");
		
		String teacherID = request.getParameter("teacherID");
		String accyear=request.getParameter("accyear");
		String location=request.getParameter("locationId");
		String desigantion=request.getParameter("designation");
		String department=request.getParameter("departmentId");
		//.out.println("department------------------------ di +"+department);

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTAFFIDCARDSINGLE);

			PageFilterpojo filterpojo=new PageFilterpojo();
			
			filterpojo.setTeacherID(teacherID);
			filterpojo.setLocationId(location);
			filterpojo.setAcademicYear(accyear);
			filterpojo.setDesignationId(desigantion);
			filterpojo.setDepartmentId(department);

			list=new  IDGenerator().getstaffIdCardprintList(filterpojo);
			
			request.setAttribute("staffDetails", list);
			request.setAttribute("template", accyear+location+list.get(0).getDesignationId());
			//.out.println(list.size());
					
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffSingleIDCardPrint Ending");

		return mapping.findForward(MessageConstants.StaffSingleIDCardPrint);
	}

	public ActionForward DesignStaffIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : DesignStaffIDCard Starting");
		try{
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNSTAFFIDCARD);


			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
	
			request.setAttribute("historyaccyear", request.getParameter("historyaccyear"));
			request.setAttribute("historyschoolId", request.getParameter("historyschoolId"));
			request.setAttribute("historydepartmentId", request.getParameter("historydepartmentId"));

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : DesignStaffIDCard Ending");

		return mapping.findForward(MessageConstants.DesignStaffIDCard);
	}
	public ActionForward PrintStaffIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintStaffIDCard Starting");

		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String departmentId=request.getParameter("departmentId");

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNSTAFFIDCARD);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setCustid(userLoggingsVo.getCustId());
			filterpojo.setLocationId(schoolId);
			filterpojo.setAcademicYear(accyear);
			filterpojo.setDepartmentId(departmentId);
			list = new IDGenerator().getstaffIdCardDesignList(filterpojo,userLoggingsVo);

			request.setAttribute("template", list);
			request.setAttribute("locationNmae", list.get(0).getLocationName());
			request.setAttribute("accYearName", list.get(0).getAcademicYear());
			request.setAttribute("departmentName", list.get(0).getDepartmentName());
			request.setAttribute("locationAddress", list.get(0).getLocation_address());
			request.setAttribute("locationPhone", list.get(0).getLocation_phone());
			request.setAttribute("templateClass", accyear+schoolId+departmentId);
			request.setAttribute("schoolLogo", list.get(0).getSchoollogourl());
			request.setAttribute("historyaccyear", accyear);
			request.setAttribute("historyschoolId", schoolId);
			request.setAttribute("historydepartmentId", request.getParameter("historydepartmentId")); 
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintStaffIDCard Ending");

		return mapping.findForward(MessageConstants.PrintStaffIDCard);
	}

	public ActionForward getStaffIdDesignList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStaffIdDesignList Starting");
		
		
		try {
			
			List<IDcardVo> list = new ArrayList<IDcardVo>();
			
			
			
			
			File folder = new File(request.getRealPath("/")+ "CSS/IdCard/StaffId");
			File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	    		  IDcardVo template=new IDcardVo();
	    		  
	    		  List<PageFilterVo> templateNameList=new ArrayList<PageFilterVo>();
	    		  UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	  			
	  				PageFilterpojo filterpojo=new PageFilterpojo();
	  				filterpojo.setCustid(userLoggingsVo.getCustId());
	    		  	filterpojo.setLocationId(listOfFiles[i].getName().substring(4, 8));
	  				filterpojo.setAcademicYear(listOfFiles[i].getName().substring(0, 4));
	  				filterpojo.setDepartmentId(listOfFiles[i].getName().substring(8, 12));
	  				templateNameList=new  StudentIDDaoImpl().getNewstafftIdCardDesignList(filterpojo,userLoggingsVo);
	    		  
	  			
	  				if(templateNameList.size()>0){
	  					template.setIdCardtemplateValue(listOfFiles[i].getName());
	  					template.setIdCardtemplateName(templateNameList.get(0).getAcademicYear()+" "+templateNameList.get(0).getLocationName()+" "+templateNameList.get(0).getDepartmentName());
	  					list.add(template);
	  				}
	    	  
	      } 
	    }
	    JSONObject obj=new JSONObject();
		obj.put("List", list);
		response.getWriter().print(obj);
	    
	    
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStaffIdDesignList Ending");
		return null;

	}
	public ActionForward StaffSingleIDCardDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffSingleIDCardDesign Starting");
		
		try {
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_SETTINGS);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_SETTINGS);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTAFFIDCARDSINGLE);
				
				UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
				request.setAttribute("locationList", locationList);
				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
				request.setAttribute("AccYearList", accYearList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffSingleIDCardDesign Ending");

		return mapping.findForward(MessageConstants.StaffSingleIDCardDesign);
	}

	public ActionForward StaffSingleIDCardDesignList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffSingleIDCardDesignList Starting");
		
		
		
		
	/*	String accyear=request.getParameter("academicYear");
		String locationId11=request.getParameter("locationId");
		String designation=request.getParameter("designation");
		String department=request.getParameter("department");
		//.out.println("acc year========"+accyear);
		//.out.println("acc year========"+locationId11);
		//.out.println("acc year========"+designation);
		//.out.println("acc year========"+department);
		*/
		
		
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		
		try {
			
			String locationId=request.getParameter("locationId");
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			String academicYear=request.getParameter("academicYear");
			if(academicYear.equalsIgnoreCase("all")){
				academicYear="%%";
			}
			String designationId=request.getParameter("designationId");
			if(designationId.equalsIgnoreCase("all")){
				designationId="%%";
			}
			String departmentId=request.getParameter("departmentId");
			if(departmentId.equalsIgnoreCase("all") ){
				departmentId="%%";
			}
		
			/*String departmentId=request.getParameter("departmentId");
			//.out.println("departmentid=============="+departmentId);*/
			
			PageFilterpojo filterpojo=new PageFilterpojo();
	
			filterpojo.setAcademicYear(academicYear);
			filterpojo.setLocationId(locationId);
			filterpojo.setDesignationId(designationId);
			filterpojo.setDepartmentId(departmentId);
			
		
			//list=new IDGenerator().getSingleStaffCardDesignList(filterpojo);
			
			JSONObject obj=new JSONObject();
			obj.put("streamList", list);
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffSingleIDCardDesignList Ending");
		
		return null;
	}

	
	public ActionForward StaffMultipleIDCardPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffMultipleIDCardPrint Starting");





		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffMultipleIDCardPrint Ending");

		return mapping.findForward(MessageConstants.StaffMultipleIDCardPrint);
	}


	public ActionForward printMultiStaffIDCardDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : printMultiStaffIDCardDesign Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTAFFIDCARDMULTIPLE);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			
			
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historysearchvalue", request.getParameter("historysearchvalue"));
			request.setAttribute("historydepartId", request.getParameter("historydepartId"));
			request.setAttribute("historydesigId", request.getParameter("historydesigId"));
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : printMultiStaffIDCardDesign Ending");

		return mapping.findForward(MessageConstants.printMultiStaffIDCard);
	}

/*renamed*/
	public ActionForward StaffMultipleIDCardPrint1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffMultipleIDCardPrint1 Starting");





		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StaffMultipleIDCardPrint1 Ending");

		return mapping.findForward(MessageConstants.StaffMultipleIDCardPrint);
	}


	public ActionForward DesignStudentSingleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : DesignStudentSingleIDCard Starting");
		
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");
		
		//List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<StudentRegistrationVo> stuList = null;
				
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTUDENTIDCARDSINGLE);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);

			/*if(location.equalsIgnoreCase("all")){
				location="%%";
				stuList=new StudentRegistrationDelegate().getStudentLocationList(academic_year,location,userLoggingsVo.getCustId());
			}
			else{
				stuList=new StudentRegistrationDelegate().getStudentList(academic_year,location,userLoggingsVo.getCustId());
			}*/
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			request.setAttribute("classlist", classlist);
				

			/*request.setAttribute("studentList", stuList);*/
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		/*String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");
		
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		
		try {
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(schoolId);
			filterpojo.setAcademicYear(accyear);
			filterpojo.setStreamId(streamId);
			list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo);
			
			request.setAttribute("template", list);
			request.setAttribute("locationName", list.get(0).getLocationName());
			request.setAttribute("accYearName", list.get(0).getAcademicYear());
			request.setAttribute("templateClass", accyear+schoolId+streamId);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		*/

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : DesignStudentSingleIDCard Ending");

		return mapping.findForward(MessageConstants.DesignStudentSingleIDCard);
	}

	public ActionForward PrintStudentSingleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintStudentSingleIDCard Starting");


		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTUDENTIDCARDSINGLE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			//List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().getIDCard(studentId,accYear,locationId,userLoggingsVo);

			request.setAttribute("studentSearchList", list);
			request.setAttribute("template", accYear+locationId+list.get(0).getStreemcode());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintStudentSingleIDCard Ending");

		return mapping.findForward(MessageConstants.PrintStudentSingleIDCard);
	}
	public ActionForward PrintStudentMultipleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintStudentMultipleIDCard Starting");

		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");
		
		//List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<StudentRegistrationVo> stuList = null;
				
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTUDENTIDCARDMULTIPLE);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);

			if(location.equalsIgnoreCase("all")){
				
				location="%%";
				stuList=new StudentRegistrationDelegate().getStudentLocationList(academic_year,location,userLoggingsVo);
			}
			else{
				
				stuList=new StudentRegistrationDelegate().getStudentList(academic_year,location,userLoggingsVo);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			request.setAttribute("classlist", classlist);
				

			request.setAttribute("studentList", stuList);
			
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historystreamId", request.getParameter("historystreamId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
			JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintStudentMultipleIDCard Ending");

		return mapping.findForward(MessageConstants.PrintStudentMultipleIDCard);
	}
	public ActionForward PrintPreviewStudentMultipleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintPreviewStudentMultipleIDCard Starting");
      try{
    	  request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTUDENTIDCARDMULTIPLE);
			
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String locationId[]=request.getParameter("location").split(",");
		String accyear[]=request.getParameter("accyear").split(",");
		String studentId[]=request.getParameter("studentId").split(",");
		String streamId[]=request.getParameter("streamId").split(",");
		List<StudentIDVo> studentList = new StudentIDDaoImpl().getstudentIDPDFReport(accyear,locationId,studentId,userLoggingsVo);
		List<StudentIDVo> list = new ArrayList<StudentIDVo>();

		for (int i = 0; i < studentList.size(); i++) {

			StudentIDVo vo = new StudentIDVo();
			String fileName = studentList.get(i).getImage();
			vo.setImages("./"+fileName);

			vo.setStuName(studentList.get(i).getStuName());
			vo.setClassName(studentList.get(i).getClassName() + " - "
					+ studentList.get(i).getSection());
			vo.setSection(studentList.get(i).getSection());
			vo.setFatherName(studentList.get(i).getFatherName());
			vo.setAdress(studentList.get(i).getAdress());
			vo.setPhone(studentList.get(i).getPhone());
			vo.setSecodaryPhone(studentList.get(i).getSecodaryPhone());
			vo.setValidity(studentList.get(i).getValidity());
			vo.setAdmissionno(studentList.get(i).getAdmissionno());
			vo.setMotherName(studentList.get(i).getMotherName());
			vo.setHouseName(studentList.get(i).getHouseName());
			vo.setEmergencyNo(studentList.get(i).getEmergencyNo());
			vo.setSchoolName(studentList.get(i).getSchoolName());
			vo.setLocation_address(studentList.get(i).getLocation_address());
			vo.setLocation_phone(studentList.get(i).getLocation_phone());
			vo.setDob(studentList.get(i).getDob());
			vo.setAdharNo(studentList.get(i).getAdharNo());
			vo.setBgroup(studentList.get(i).getBgroup());
			vo.setSchoollogo(studentList.get(i).getSchoollogo());
			vo.setSchoolName(studentList.get(i).getSchoolName());
			list.add(vo);

		}
		
		request.setAttribute("template", accyear[0]+locationId[0]+streamId[0]);
		request.setAttribute("StudentList", list);
		
		request.setAttribute("historyacademicId", accyear[0]);
		request.setAttribute("historylocId", locationId[0]);
		request.setAttribute("historystreamId", streamId[0]);
		request.setAttribute("historyclassname", request.getParameter("classname"));
		request.setAttribute("historysectionid", request.getParameter("sectionid"));
		
	}catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintPreviewStudentMultipleIDCard Ending");

		return mapping.findForward(MessageConstants.PrintPreviewStudentMultipleIDCard);
	}
	public ActionForward DesignTransportIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : DesignTransportIDCard Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNTRANSPORTIDCARD);

		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
		request.setAttribute("locationList", locationList);
		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
		request.setAttribute("AccYearList", accYearList);
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : DesignTransportIDCard Ending");

		return mapping.findForward(MessageConstants.DesignTransportIDCard);
	}
	public ActionForward PrintTransportIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintTransportIDCard Starting");
		
		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNTRANSPORTIDCARD);

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(schoolId);
			filterpojo.setAcademicYear(accyear);
			filterpojo.setStreamId(streamId);
			list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo,custdetails);

			request.setAttribute("template", list);
			request.setAttribute("locationNmae", list.get(0).getLocationName());
			request.setAttribute("accYearName", list.get(0).getAcademicYear());
			request.setAttribute("templateClass", accyear+schoolId+streamId+"transport");

		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintTransportIDCard Ending");

		return mapping.findForward(MessageConstants.PrintTransportIDCard);
	}



	public ActionForward DesignTransportIDCardSingle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : DesignTransportIDCardSingle Starting");


		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");
		
		//List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<StudentRegistrationVo> stuList = null;
				
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTTRANSPORTIDCARDSINGLE);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			
	/*		
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(schoolId);
			filterpojo.setAcademicYear(accyear);
			filterpojo.setStreamId(streamId);
			list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo);
			
			request.setAttribute("template", list);
			request.setAttribute("locationName", list.get(0).getLocationName());
			request.setAttribute("accYearName", list.get(0).getAcademicYear());
			request.setAttribute("templateClass", accyear+schoolId+streamId);*/
			
			
			
			if(location.equalsIgnoreCase("all")){
				
				location="%%";
				stuList = new TransportBD().searchAllAcademicYearDetailsTrans(location,academic_year,userLoggingsVo);
			}
			else{
				
				stuList= new TransportBD().searchAllAcademicYearDetailsTrans(location,academic_year,userLoggingsVo);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			request.setAttribute("classlist", classlist);
				

			request.setAttribute("studentList", stuList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : DesignTransportIDCardSingle Ending");

		return mapping.findForward(MessageConstants.DesignTransportIDCardSingle);
	}



	public ActionForward PrintTransportIDCardSingle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintTransportIDCardSingle Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTTRANSPORTIDCARDSINGLE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			//List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().getIDCard(studentId,accYear,locationId,userLoggingsVo);

			request.setAttribute("studentSearchList", list);
			request.setAttribute("template", accYear+locationId+list.get(0).getStreemcode()+"transport");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintTransportIDCardSingle Ending");

		return mapping.findForward(MessageConstants.PrintTransportIDCardSingle);
	}

	public ActionForward PrintTransportMultipleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintTransportMultipleIDCard Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		String accyear=request.getParameter("accyear");
		String schoolId=request.getParameter("schoolId");
		String streamId=request.getParameter("streamId");
		
		//List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<StudentRegistrationVo> stuList = null;
				
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTTRANSPORTIDCARDMULTIPLE);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("Acyearid",academic_year);
			
			if(location.equalsIgnoreCase("all")){
				
				location="%%";
				stuList=new TransportBD().searchAllAcademicYearDetailsTrans(location,academic_year,userLoggingsVo);
			}
			else{
				
				stuList=new TransportBD().searchAllAcademicYearDetailsTrans(location,academic_year,userLoggingsVo);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			request.setAttribute("classlist", classlist);

			request.setAttribute("studentList", stuList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintTransportMultipleIDCard Ending");

		return mapping.findForward(MessageConstants.PrintTransportMultipleIDCard);
	}

	public ActionForward PrintPreviewTransportMultipleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintPreviewTransportMultipleIDCard Starting");

		String locationId[]=request.getParameter("location").split(",");
		String accyear[]=request.getParameter("accyear").split(",");
		String studentId[]=request.getParameter("studentId").split(",");
		String streamId[]=request.getParameter("streamId").split(",");
		
		//List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<StudentRegistrationVo> stuList = null;
				
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTTRANSPORTIDCARDMULTIPLE);
			
			List<StudentIDVo> studentList = new StudentIDBD().getstudentBusIDPDFReport(accyear, locationId, streamId, studentId);
			List<StudentIDVo> list = new ArrayList<StudentIDVo>();

			for (int i = 0; i < studentList.size(); i++) {

				StudentIDVo vo = new StudentIDVo();
				String fileName = studentList.get(i).getImage();
				String filePath = request.getRealPath("/")+ "FIles/STUDENTIMAGES/" + fileName.trim();
				vo.setImages("./"+fileName.trim());

				vo.setStuName(studentList.get(i).getStuName());
				vo.setClassName(studentList.get(i).getClassName() + " - "
						+ studentList.get(i).getSection());
				vo.setSection(studentList.get(i).getSection());
				vo.setFatherName(studentList.get(i).getFatherName());
				vo.setAdress(studentList.get(i).getAdress());
				vo.setPhone(studentList.get(i).getPhone());
				vo.setSecodaryPhone(studentList.get(i).getSecodaryPhone());
				vo.setValidity(studentList.get(i).getValidity());
				vo.setAdmissionno(studentList.get(i).getAdmissionno());
				vo.setMotherName(studentList.get(i).getMotherName());
				vo.setRoute_no(studentList.get(i).getRoute_no());
				vo.setPoint_name(studentList.get(i).getPoint_name());
				vo.setSchoolName(studentList.get(i).getSchoolName());
				list.add(vo);

			}

			JSONObject jsonobject = new JSONObject();
			jsonobject.accumulate("studentList", list);
			response.getWriter().print(jsonobject);
			
			
			
			request.setAttribute("template", accyear[0]+locationId[0]+streamId[0]+"transport");
			request.setAttribute("StudentList", list);
			request.setAttribute("templateClass", accyear[0]+locationId[0]+streamId[0]+"transport");
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintPreviewTransportMultipleIDCard Ending");

		return mapping.findForward(MessageConstants.PrintPreviewTransportMultipleIDCard);
	}
	
	

public ActionForward NewTransIdCardFilterList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : NewTransIdCardFilterList Starting");
	
	List<PageFilterVo> list=new ArrayList<PageFilterVo>();
	
	try {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DESIGNSTUDENTIDCARD);
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String streamId=request.getParameter("streamId");
		if(streamId.equalsIgnoreCase("all")){
			streamId="%%";
		}
		PageFilterpojo filterpojo=new PageFilterpojo();
		filterpojo.setLocationId(request.getParameter("locationId"));
		filterpojo.setAcademicYear(request.getParameter("academicYear"));
		filterpojo.setStreamId(streamId);
		list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo,userLoggingsVo);
		
		JSONObject obj=new JSONObject();
		obj.put("streamList", list);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : NewTransIdCardFilterList Ending");
	
	return null;
}
	
/*	Election module actions--------------------BEGIN-------------------------------*/
	
	
	




/*	Election module actions--------------------BEGIN-------------------------------*/
public ActionForward electionList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : electionList Starting");

	try{
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_ELECTIONSETTING);
		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		
		String academicYear = "%%";
		String groupId = "%%";
		ArrayList<ElectionVo> dataList = new ArrayList<ElectionVo>();
		dataList = new ElectionBD().getElectionDetails(academicYear,groupId);
		request.setAttribute("DataList", dataList);	
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : electionList Ending");

	return mapping.findForward(MessageConstants.electionList);
}

	
	public ActionForward GroupList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : GroupList Starting");

				
		try{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_ELECTION_GROUPSETTING);
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
			
			
			List<ElectionVo> Data = new ArrayList<ElectionVo>();
			
			Data = new ElectionBD().getGroupdetails();
			request.setAttribute("DataList", Data);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : GroupList Ending");

		return mapping.findForward(MessageConstants.GroupList);
	}

	public ActionForward addNewElection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addNewElection Starting");

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_ELECTIONSETTING);



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addNewElection Ending");

		return mapping.findForward(MessageConstants.addNewElection);
	}



	public ActionForward nominationApproval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : nominationApproval Starting");

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_NOMINATIONAPPROVAL);



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : nominationApproval Ending");

		return mapping.findForward(MessageConstants.nominationApproval);
	}

	public ActionForward addBoothDetais(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addBoothDetais Starting");

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_BOOTHSETTING);



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addBoothDetais Ending");

		return mapping.findForward(MessageConstants.addBoothDetais);
	}

	public ActionForward voterSearchList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : voterSearchList Starting");





		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : voterSearchList Ending");

		return mapping.findForward(MessageConstants.voterSearchList);
	}

	/*public ActionForward voterDetailsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");





		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.voterDetailsView);
	}*/


	public ActionForward voterMachineStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : voterMachineStart Starting");
		String forward=null;
		
		try {
			String systemIp=(String) request.getSession(false).getAttribute("IP");
			String accyear=request.getParameter("accyear");
			String group=request.getParameter("group");
			String titleID=request.getParameter("titleID");
			
			String status=new ElectionDaoImpl().checkIp(systemIp);
			
			request.setAttribute("accyear", accyear);
			request.setAttribute("group", group);
			request.setAttribute("titleID", titleID);
			if(status.equalsIgnoreCase("true")){
				ArrayList<ElectionVo> boothDetails=new ElectionDaoImpl().getBoothDetails(systemIp,accyear,group,titleID);
				request.setAttribute("boothDetails", boothDetails);
				
				
				forward=MessageConstants.voterMachineActivate;
			}
			else{
				forward=MessageConstants.voterMachineStart;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : voterMachineStart Ending");

		return mapping.findForward(forward);
	}

	public ActionForward showCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : showCategory Starting");
		String forward=null;
		try {
			
			String systemIp=(String) request.getSession(false).getAttribute("IP");
			String status=new ElectionDaoImpl().checkMachicneActivation(systemIp);
			if(status.equalsIgnoreCase("true")){
				
				ElectionPojo obj=new ElectionDaoImpl().getFilterationForCandidateList(systemIp);
				
				ArrayList<ElectionVo> collectionVo = null;

				 collectionVo = new ElectionDaoImpl().getCandidateList(obj.getAccid(),obj.getGroupId(),obj.getElectionTitleId(),obj.getActivationFor());
				
				request.setAttribute("CollectionVo", collectionVo);
				forward=MessageConstants.showCategory;
			}
			else{
				
				String abc=new ElectionDaoImpl().getMachicneDeActivation(systemIp);
				forward=MessageConstants.voterMachineStart;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : showCategory Ending");

		return mapping.findForward(forward);
	}


	public ActionForward electionCategoryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : electionCategoryList Starting");

		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_CATEGORYSETTING);
		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : electionCategoryList Ending");

		return mapping.findForward(MessageConstants.electionCategoryList);
	}


	public ActionForward nominationRegister(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : nominationRegister Starting");

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_ELECTION_NOMINATIONREGISTRATION);



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : nominationRegister Ending");

		return mapping.findForward(MessageConstants.nominationRegister);
	}

	public ActionForward getBranch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : getBranch : Starting");

		LocationBD objl = new LocationBD();
		List<LocationVO> schoolList = new ArrayList<LocationVO>();
		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");

			JSONObject obj=new JSONObject();
			obj.put("jsonResponse", HelperClass.getAllLocation(pojo));
			response.getWriter().print(obj);


		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage(), e);
			e.printStackTrace();

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getBranch Ending");

		return null;

	}

	

	public ActionForward changeBranch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : changeBranch : Starting");

		LocationBD objl = new LocationBD();
		List<LocationVO> schoolList = new ArrayList<LocationVO>();
		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");

			HttpSession session = request.getSession(true);
			session.setAttribute(MessageConstants.CURRENT_SCHOOLLOCATION,request.getParameter("branch"));
			
			JSONObject obj=new JSONObject();
			obj.put("status",true);
			response.getWriter().print(obj);


		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage(), e);
			e.printStackTrace();

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : changeBranch Ending");

		return null;

	}
	
	public ActionForward getSchool(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : getSchool : Starting");

		LocationBD objl = new LocationBD();
		List<LocationVO> schoolList = new ArrayList<LocationVO>();
		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");

			JSONObject obj=new JSONObject();
			obj.put("jsonResponse", HelperClass.getSchoolName(null, pojo));
			response.getWriter().print(obj);


		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage(), e);
			e.printStackTrace();

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getSchool Ending");

		return null;

	}

	public ActionForward studentConfidentialReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentConfidentialReport Starting");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			try {
				String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

				String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
				
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_STUDENT);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_STUDENT);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_STUDENT_DISCIPLINARYACTION);
			/*if(location.equalsIgnoreCase("all"))
			{				
				location="%%";
				list = new StudentRegistrationDelegate().getConfidentialReportStudents(academic_year,location,userLoggingsVo);
			}
			else{
				
				list = new StudentRegistrationDelegate().getConfidentialReportStudents(academic_year,location,userLoggingsVo);
			}*/
			
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			request.setAttribute("classlist", classlist);
				

			//request.setAttribute("studentList", list);
			request.setAttribute("hloc", location);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");
			request.setAttribute("academic_year", academic_year);
			
			request.setAttribute("historyaccYear", request.getParameter("historyaccYear"));
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			request.setAttribute("historysearchvalue", request.getParameter("historysearchvalue"));
			request.setAttribute("historystatus", request.getParameter("historystatus"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentConfidentialReport Ending");

		return mapping.findForward(MessageConstants.STUDENTCONFIDENTIAL);
	}

	public ActionForward AddStudentConfidentialReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddStudentConfidentialReport Starting");
		
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
			String srarchSt=request.getParameter("srarchSt");
			String branchName=request.getParameter("branchName");
			/*List<StudentRegistrationVo> list = new StudentRegistrationDelegate().singleStudentConfDetails(studentId,accYear,locationId);*/			
			
			StudentRegistrationVo list = new StudentRegistrationDelegate().singleStudentDetails(studentId,accYear,locationId,custdetails);
			request.setAttribute("studentSearchList", list);
			
			
			//.out.println("Ashish"+list.getBranchName());
			
		 
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_DISCIPLINARYACTION);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");
			request.setAttribute("srarchSt", srarchSt);
			
			request.setAttribute("historyaccYear", accYear);
			request.setAttribute("historylocId", locationId);
			request.setAttribute("branchName", branchName);
			request.setAttribute("historyclassname", request.getParameter("classname"));
			request.setAttribute("historysectionid", request.getParameter("sectionid"));
			request.setAttribute("historysearchvalue", request.getParameter("searchvalue"));
			request.setAttribute("historystatus", request.getParameter("status"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddStudentConfidentialReport Ending");

		return mapping.findForward(MessageConstants.ADDSTUDENTCONFIDENTIAL);
	}

	public ActionForward AddStudentWithHeld1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : AddStudentWithHeld1 Starting");
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTWITHHELDLIST);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			   
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
				
			/*List<StudentRegistrationVo> list = new StudentRegistrationDelegate().singleStudentWithHeld(studentId,accYear,locationId);*/			
			StudentRegistrationVo list = new StudentRegistrationDelegate().singleStudentDetails(studentId,accYear,locationId,custdetails);
			request.setAttribute("studentSearchList", list);
            
			String startDate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0];
			String enddate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1];
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("enddate", enddate);
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");
			
			request.setAttribute("historyaccYear", accYear);
			request.setAttribute("historylocId", locationId);
			request.setAttribute("historyclassname", request.getParameter("classname"));
			request.setAttribute("historysectionid", request.getParameter("sectionid"));
			request.setAttribute("historysearchvalue", request.getParameter("searchvalue"));
			request.setAttribute("historystatus", request.getParameter("status"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :  Ending");

		return mapping.findForward(MessageConstants.ADDSTUDENTWITHHELD);
	}


	public ActionForward studentWithheldList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheldList Starting");
	

//			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			try {
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTWITHHELDLIST);
				
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_STUDENT);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_STUDENT);
		
				/*list = new StudentRegistrationDelegate().getStudentWithheldList(academic_year,location,userLoggingsVo);
				list = new StudentRegistrationDelegate().getConfidentialReportStudents(academic_year,location);*/
				
//				list = new StudentRegistrationDelegate().getStudentWithheldList(academic_year,location,userLoggingsVo);
		
			
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);

//			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
//			request.setAttribute("classlist", classlist);
				

//			request.setAttribute("studentList", list);
			request.setAttribute("hloc", location);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");
			request.setAttribute("academic_year", academic_year);

			request.setAttribute("historyaccYear", request.getParameter("historyaccYear"));
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			request.setAttribute("historysearchvalue", request.getParameter("historysearchvalue"));
			request.setAttribute("historystatus", request.getParameter("historystatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheldList Ending");

		return mapping.findForward(MessageConstants.WITHHELDLIST);
	}

	
	/*public ActionForward getSudetnWithheldtList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheldList Starting");


		try {
			ArrayList<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			String academic_year = request.getParameter("accyear");
			String location = request.getParameter("location");
			String searchTerm = request.getParameter("searchname".trim());

			if(location.equalsIgnoreCase("all") || location.equalsIgnoreCase("")){
				location ="%%";
			}
			
			list = new StudentRegistrationDelegate().getSudetnWithheldtList(academic_year,location);

			JSONObject obj=new JSONObject();
			obj.put("studentData",list);
			response.getWriter().print(obj);


			request.setAttribute("studentList",list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheldList Ending");

		return null;
	}*/

	/*public ActionForward studentWithheld(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheld Starting");
		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		try {

			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails();
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute("studentSearchList", registrationVo1);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentWithheld Ending");

		return mapping.findForward(MessageConstants.STUDENTWITHHELD);
	}*/

	public ActionForward withheldCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : withheldCancel Starting");
		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails(userLoggingsVo);
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute("studentSearchList", registrationVo1);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : withheldCancel Ending");

		return mapping.findForward(MessageConstants.STUDENTWITHHELD);
	}

	public ActionForward withheldFindStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : withheldFindStudent Starting");
		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			request.setAttribute("classlist", classlist);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : withheldFindStudent Ending");

		return mapping.findForward(MessageConstants.FINDSTUDENT);
	}

	public ActionForward NewstudentPromotionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentPromotionList Starting");

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTPROMOTION);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			
			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			request.setAttribute("classlist", classlist);
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			if (academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			
			StudentRegistrationVo regVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			regVo.setAcademicYearId(academic_year);
			regVo.setLocationId(location);
			
			
			list = delegateObj.getPromotedListDetails(regVo,userLoggingsVo);

			request.setAttribute(MessageConstants.STUDENTDETAILSLIST, list);

			request.setAttribute("academic_year", academic_year);
			request.setAttribute("hloc", location);
			
			request.setAttribute("promaccYear", request.getParameter("promaccYear"));
			request.setAttribute("promlocId", request.getParameter("promlocId"));
			request.setAttribute("promclassname", request.getParameter("promclassname"));
			request.setAttribute("promsectionid", request.getParameter("promsectionid"));
			request.setAttribute("prosearch", request.getParameter("prosearch"));
			request.setAttribute("demoaccYear", request.getParameter("demoaccYear"));
			request.setAttribute("demolocId", request.getParameter("demolocId"));
			request.setAttribute("democlassname", request.getParameter("democlassname"));
			request.setAttribute("demosectionid", request.getParameter("demosectionid"));
			request.setAttribute("demosearch", request.getParameter("demosearch"));
			
			request.setAttribute("tabstatus", request.getParameter("tabstatus"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentPromotionList Ending");

		return mapping.findForward(MessageConstants.STUDENTPROM);
	}

	public ActionForward getStudentForPromotion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentForPromotion Starting");
	
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTPROMOTION);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("Acyearid",accYearList.get(0).getAccyearId());

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			request.setAttribute("classlist", classlist);

			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			if (academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
		
			
			request.setAttribute("tabstatus", request.getParameter("tabstatus"));
			request.setAttribute("hloc", location);
			request.setAttribute("promaccYear", request.getParameter("promaccYear"));
			request.setAttribute("promlocId", request.getParameter("promlocId"));
			request.setAttribute("promclassname", request.getParameter("promclassname"));
			request.setAttribute("promsectionid", request.getParameter("promsectionid"));
			request.setAttribute("prosearch", request.getParameter("prosearch"));
			request.setAttribute("demoaccYear", request.getParameter("demoaccYear"));
			request.setAttribute("demolocId", request.getParameter("demolocId"));
			request.setAttribute("democlassname", request.getParameter("democlassname"));
			request.setAttribute("demosectionid", request.getParameter("demosectionid"));
			request.setAttribute("demosearch", request.getParameter("demosearch"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentForPromotion Ending");

		return mapping.findForward(MessageConstants.GETSTUDENT);
	}





	public ActionForward studentPromotionPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionPage Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTPROMOTION);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
            
			StudentRegistrationVo list = new StudentRegistrationDelegate().getStudentWisePromotion(studentId,accYear,locationId,custdetails);

			request.setAttribute("studentPromotionSetting", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotionPage Ending");

		return mapping.findForward(MessageConstants.PROMOTIONPAGE);
	}

	public ActionForward studentAppraisalList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentAppraisalList Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails(custdetails);
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentAppraisalList Ending");

		return mapping.findForward(MessageConstants.APPRAISALLIST);
	}

	public ActionForward findStudentForAppraisal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : findStudentForAppraisal Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails(custdetails);
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : findStudentForAppraisal Ending");

		return mapping.findForward(MessageConstants.STUDENTAPPRAISAL);
	}

	public ActionForward studentAppraisalPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentAppraisalPage Starting");

		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails(custdetails);
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentAppraisalPage Ending");

		return mapping.findForward(MessageConstants.APPRAISALPAGE);
	}

	public ActionForward studentTransferCertificateList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentTransferCertificateList Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ParentRequiresAppointmentDELEGATE obj = new ParentRequiresAppointmentDELEGATE();
			
			List<secadmissionformformatVO> list = new ArrayList<secadmissionformformatVO>();
			list = obj.getsecformadmissiondetails(custdetails);
			request.setAttribute("getTempAdmissionDetailsList", list);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentTransferCertificateList Ending");

		return mapping.findForward(MessageConstants.TRANSFERCERTIFICATELIST);
	}

	public ActionForward findStudentForTransferCertificate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : findStudentForTransferCertificate Starting");
		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		try {


			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : findStudentForTransferCertificate Ending");

		return mapping.findForward(MessageConstants.FINDSTUDENTLIST);
	}


	public ActionForward transferCertificatePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transferCertificatePage Starting");
		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		try {


			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transferCertificatePage Ending");

		return mapping.findForward(MessageConstants.TRANSCERTIFICATEPAGE);
	}


	public ActionForward printTransferCertificate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : printTransferCertificate Starting");
		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		try {


			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : printTransferCertificate Ending");

		return mapping.findForward(MessageConstants.PRINTTRANSCERTIFICATE);
	}

	public ActionForward gradeList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gradeList Starting");

		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXAM_GRADESETTINGS);
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");
			  
	    	   String currentlocation =null;
	    	   if(schoolLocation.equalsIgnoreCase("all")){
	    		   schoolLocation="%%";
	    		   request.setAttribute("currentlocation", "All");
	    		   request.setAttribute("locationId",schoolLocation);
	    	   }
	    	  else{
	    		   currentlocation =new ExamDetailsBD().getlocationname(schoolLocation,pojo);
	    		   request.setAttribute("currentlocation", currentlocation);
	    	   }
	    	   request.setAttribute("locationId",schoolLocation);
	    	   LocationBD obj = new LocationBD();
				List<LocationVO> list = new ArrayList<LocationVO>();
				list = obj.getLocationDetails(pojo);
				request.setAttribute("locationDetailsList", list);
				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
						.getAccYears(pojo);
				request.setAttribute("accYearList", accYearList);
				
				request.setAttribute("historyacademicId",request.getParameter("historyacademicId"));
				request.setAttribute("historylocId",request.getParameter("historylocId"));
				
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gradeList Ending");

		return mapping.findForward(MessageConstants.STUDENTGRADELIST);
	}


	public ActionForward getStudentConfidentialReport(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : getStudentConfidentialReport Starting");

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("Acyearid",accYearList.get(0).getAccyearId());

			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			request.setAttribute("classlist", classlist);

			String accyear  = (String) request.getSession(false).getAttribute("current_academicYear");

			String locationid  = (String) request.getSession(false).getAttribute("current_schoolLocation");
			if(locationid.equalsIgnoreCase("all")){
				locationid="%%";
			}
			if (locationid == null || locationid == "" || locationid.equalsIgnoreCase("")) {
				locationid = HelperClass.getCurrentYearID(userLoggingsVo);
			}

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDelegate().getStudentDetailsList(locationid,accyear,userLoggingsVo);

			request.setAttribute("studentList", list);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentConfidentialReport Ending");

		return mapping.findForward(MessageConstants.GETSTUDENTCONFIDENTIAL);
	}	

	public ActionForward miscellaneousReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : miscellaneousReport Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_MISREPORT);
			
		if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
			academic_year = HelperClass.getCurrentYearID(custdetails);
		}
		
		StudentRegistrationVo vo = new StudentRegistrationVo();
		vo.setAccyear(academic_year);
		vo.setLocationId("%%");
		vo.setSection_id("%%");
		vo.setStatus("active");
		vo.setClassDetailId("%%");
		
		list = new StudentRegistrationDelegate().searchAllAcademicYearDetails(vo,custdetails);
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
		request.setAttribute("locationList", locationList);

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
		request.setAttribute("AccYearList", accYearList);
		
		request.setAttribute("studentList", list);
		request.setAttribute("academic_year", academic_year);
		request.setAttribute("hloc", location);
		
		request.setAttribute("historyaccYear", request.getParameter("historyaccYear"));
		request.setAttribute("historylocId", request.getParameter("historylocId"));
		request.setAttribute("historyclassname", request.getParameter("historyclassname"));
		request.setAttribute("historysectionid", request.getParameter("historysectionid"));
		request.setAttribute("historysearchvalue", request.getParameter("historysearchvalue"));
		request.setAttribute("historystatus", request.getParameter("historystatus"));

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : miscellaneousReport Ending");

		return mapping.findForward(MessageConstants.MISCELLANEOUS_REPORT);
	}

	
	public ActionForward singleStudentDetailsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : singleStudentDetailsList Starting");
		
		

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String studentId = request.getParameter("studentId");
			String accyear = request.getParameter("accyear");
			String locationid = request.getParameter("locationid");
			
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().singleStudentConfDetails(studentId,accyear,locationid,pojo);
			
			request.setAttribute("studentSearchList", list);
			
			JSONObject obj=new JSONObject();
			obj.put("studentSearchList", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : singleStudentDetailsList Ending");

		return mapping.findForward(MessageConstants.ADDSTUDENTCONFIDENTIAL);
	}

	public ActionForward NewstudentIdCardDesignList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesignList Starting");

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String streamId=request.getParameter("streamId");
			if(streamId.equalsIgnoreCase("all")){
				streamId="%%";
			}
			String locationId=request.getParameter("locationId");
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			String academicYear=request.getParameter("academicYear");
			if(academicYear.equalsIgnoreCase("all")){
				academicYear="%%";
			}
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(locationId);
			filterpojo.setAcademicYear(academicYear);
			filterpojo.setStreamId(streamId);
		
			
			list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo,custdetails);

			JSONObject obj=new JSONObject();
			obj.put("streamList", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesignList Ending");

		return null;
	}
	
	public ActionForward saveLayoutImage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : saveLayoutImage Starting");


		try {
			//.out.println("Inside the changeImage");
			InventoryTransactionForm formObj = (InventoryTransactionForm) form;
			//.out.println("formObj :" + formObj);
			
			FormFile file = formObj.getInputfile();
			String layout=formObj.getLayoutDetails();
			
			File filename = new File(request.getRealPath("/")+ "images/IdCard/"+layout+".jpg");
			if(filename.exists()){
				
			}
			else{
				filename.createNewFile();
			}
			
			String filepath = null, base = null, filecuurentpath = null;
			BufferedImage bufferedImage = null;
			filecuurentpath = request.getRealPath("/")+ "images/IdCard/"+layout+".jpg";
			//.out.println(filecuurentpath);
			File f1 = new File(filecuurentpath);
			if (f1 != null) {
				f1.delete();
			}

			String extension = "";
			int j = (file).getFileName().lastIndexOf('.');
			if (j >= 0) {
				base = (String) ((j == -1) ? file : (file).getFileName()
						.substring(0, j));
				extension = (file).getFileName().substring(j + 1);
				base = layout;
			}
			//.out.println("extension is " + extension);
			if (extension.equalsIgnoreCase("jpg")) {

				filepath = request.getRealPath("/")+ "images/IdCard/" + base + "." + extension;

				//.out.println(filepath);
				byte[] btDataFile = (file).getFileData();
				File of = new File(filepath);
				FileOutputStream osf = new FileOutputStream(of);
				osf.write(btDataFile);
				osf.close();
			} else if (extension.equalsIgnoreCase("jpeg")) {

				filepath = request.getRealPath("/")+ "images/IdCard/" + base + ".jpg";
				//.out.println(filepath);

				byte[] btDataFile = (file).getFileData();
				File of = new File(filepath);
				FileOutputStream osf = new FileOutputStream(of);
				osf.write(btDataFile);
				osf.close();
			} else if (extension.equalsIgnoreCase("png")) {
				filepath = request.getRealPath("/")+ "images/IdCard/" + base + ".jpg";
				//.out.println(filepath);

				byte[] btDataFile = (file).getFileData();
				File of = new File(filepath);
				FileOutputStream osf = new FileOutputStream(of);
				osf.write(btDataFile);
				osf.close();

			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : saveLayoutImage Ending");

		return null;
	}
	
	public ActionForward getStudentDetailsLByFilter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDetailsLByFilter Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String status = request.getParameter("status");
			if(status=="" || status==null){
				status="active";
			}
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list =  new StudentRegistrationDelegate().getStudentDetailsLByFilter(locationid,accyear,classname,status,custdetails);
		
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
				+ " Control in AdminMenuAction : getStudentDetailsLByFilter Ending");

		return null;

	}
	

	public ActionForward TransportIDCardDesignList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : TransportIDCardDesignList Starting");
	
	boolean bool = false;
	List<PageFilterVo> list=new ArrayList<PageFilterVo>();
	List<PageFilterVo> newlist=new ArrayList<PageFilterVo>();
	
	try {
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String streamId=request.getParameter("streamId");
		if(streamId.equalsIgnoreCase("all")){
			streamId="%%";
		}
		String locationId=request.getParameter("locationId");
		if(locationId.equalsIgnoreCase("all")){
			locationId="%%";
		}
		String academicYear=request.getParameter("academicYear");
		if(academicYear.equalsIgnoreCase("all")){
			academicYear="%%";
		}
		
		
		PageFilterpojo filterpojo=new PageFilterpojo();
		filterpojo.setLocationId(locationId);
		filterpojo.setAcademicYear(academicYear);
		filterpojo.setStreamId(streamId);
		list=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo,custdetails);
		for(int i=0;i<list.size();i++){
			PageFilterVo filterVo = new PageFilterVo();
			filterVo.setSno(list.get(i).getSno());
			filterVo.setAcademicYear(list.get(i).getAcademicYear());
			filterVo.setAcademicYearCode(list.get(i).getAcademicYearCode());
			filterVo.setLocationName(list.get(i).getLocationName());
			filterVo.setLocationId(list.get(i).getLocationId());
			filterVo.setStreamId(list.get(i).getStreamId());
			filterVo.setStreamName(list.get(i).getStreamName());
		
			filterVo.setTemplateId(list.get(i).getTemplateId());
	
		
			
			String template=list.get(i).getTemplateId();
			//.out.println("template="+template);
			File file = new File(request.getRealPath("/")+ "CSS/IdCard/"+template+".css");
			bool = file.exists();
			if(bool == true) {
				
				//.out.println("SET");
				filterVo.setStatus("SET");
				filterVo.setTemplateName(list.get(i).getTemplateName());
				
				
			}
			else{
				
				//.out.println("NOT SET");
				filterVo.setStatus("NOT SET");
				filterVo.setTemplateName("-");
				
				
			}
			newlist.add(filterVo);
			
		}
		
		JSONObject obj=new JSONObject();
		obj.put("streamList", newlist);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
		
	}

	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : TransportIDCardDesignList Ending");
	
	return null;
}

	public ActionForward getStudentListBySections(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : getStudentListBySections Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().accYearListStatus(custdetails);
			request.setAttribute("AccYearList", accYearList);
			
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String sortingby = request.getParameter("sortingby");
			String orderby = request.getParameter("orderby");
			String status = request.getParameter("status");
			String housename = request.getParameter("housename");
			String search = request.getParameter("search");
			String myorder = request.getParameter("myorder");
			String specialization = request.getParameter("specialization");
			
			
			int show_per_page=Integer.parseInt(request.getParameter("show_per_page"));
			int startPoint=Integer.parseInt(request.getParameter("startPoint"));
			StudentRegistrationVo data= new StudentRegistrationVo();
			
			
			if(housename.equalsIgnoreCase("all") || housename=="" || housename==null){
				housename="%%";
			}
			if(locationid.equalsIgnoreCase("all")){
				locationid = "%%";
			}
			if(classname.equalsIgnoreCase("all")){
				classname = "%%";
			}
			if(sectionid.equalsIgnoreCase("all")){
				sectionid = "%%";
			}
			if(specialization == null||specialization.equalsIgnoreCase("all") || specialization.equalsIgnoreCase("-") ){
				specialization = "%%";
			}
			
			
			if(search==null){
				search="";
			}else{
				search=search.trim();
			}
			
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list =new StudentRegistrationDaoImpl().getStudentListBySections(locationid,accyear,classname,sectionid,sortingby,orderby,status,housename,custdetails,search,myorder,show_per_page,startPoint,specialization);
			 
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
				+ " Control in AdminMenuAction : getStudentListBySections Ending");

		return null;

	}

	public ActionForward individualStudentSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : individualStudentSearch Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTSEARCH);
			
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId,pojo);

			request.setAttribute("studentSearchList", list);

			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : individualStudentSearch Ending");

		return mapping.findForward(MessageConstants.INDIVIDUAL_STUDENT_SEARCH);
	}
	
	public ActionForward individualMisreport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : individualMisreport Starting");

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_MISREPORT);
			
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
			String srarchSt=request.getParameter("srarchSt");
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId,pojo);

			request.setAttribute("studentSearchList", list);
			
			
			
			String classid = request.getParameter("classid");
			String sectionid = request.getParameter("sectionid");
			String studentid = request.getParameter("studentid");
			String loc_id = request.getParameter("locid");
			String acyyear = request.getParameter("acyyear");
			
			StudentRegistrationVo svo = new StudentRegistrationVo();
			svo.setClassDetailId(classid);
			svo.setClassSectionId(sectionid);
			svo.setStudentId(studentid);
			svo.setLocationId(loc_id);
			svo.setAcademicYearId(acyyear);
		
			
			List<ExaminationDetailsVo> list1 = new StudentRegistrationDelegate().getExaminationDetails(svo,pojo);
			request.setAttribute("examDetailsList", list1);
			request.setAttribute("srarchSt", srarchSt);
			request.setAttribute("locId", list.get(0).getLocationId());
			
			request.setAttribute("historyaccYear", accYear);
			request.setAttribute("historylocId", locationId);
			request.setAttribute("historyclassname", request.getParameter("classname"));
			request.setAttribute("historysectionid", request.getParameter("sectionid"));
			request.setAttribute("historysearchvalue", request.getParameter("searchvalue"));
			request.setAttribute("historystatus", request.getParameter("status"));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : individualMisreport Ending");

		return mapping.findForward(MessageConstants.INDIVIDUAL_MIS_REPORT);
	}
	
	public ActionForward studentSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentSearch Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTSEARCH);
		
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
		request.setAttribute("locationList", locationList);

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
		request.setAttribute("AccYearList", accYearList);
		request.setAttribute("hloc", location);

		request.setAttribute("studentList", list);
		request.setAttribute("academic_year", academic_year);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentSearch Ending");

		return mapping.findForward(MessageConstants.STUDENT_SEARCH);
	}
	
	public ActionForward getConfDetailsLByFilter(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : getConfDetailsLByFilter Starting");
			try {
				UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
				String locationid = request.getParameter("location");
				String accyear = request.getParameter("accyear");
				String classname = request.getParameter("classId");
				
				if(classname.equalsIgnoreCase("all")){
					
					classname="%%";
					list = new StudentRegistrationDelegate().getConfDetailsLByFilter(locationid,accyear,classname,custdetails);
				}
				else{
					
					list = new StudentRegistrationDelegate().getConfDetailsLByFilter(locationid,accyear,classname,custdetails);
				}
				
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
					+ " Control in AdminMenuAction : getConfDetailsLByFilter Ending");
			return null;
		}
		
	public ActionForward getStudentBySectionForConfReport(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : getStudentBySectionForConfReport Starting");
			try {
				UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
				String locationid = request.getParameter("location");
				String accyear = request.getParameter("accyear");
				String classname = request.getParameter("classId");
				String sectionid = request.getParameter("sectionid");
				
				if(sectionid.equalsIgnoreCase("all")){
					
					sectionid="%%";
					list = new StudentRegistrationDelegate().getStudentBySectionForConfReport(locationid,accyear,classname,sectionid,custdetails);
				}
				else{
					
					list = new StudentRegistrationDelegate().getStudentBySectionForConfReport(locationid,accyear,classname,sectionid,custdetails);
				}
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
					+ " Control in AdminMenuAction : getStudentBySectionForConfReport Ending");
			return null;
		} 
	 
	public ActionForward StudentListforPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StudentListforPrint Starting");

		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String streamId=request.getParameter("streamId");
			String flag=request.getParameter("flag");
			

			if(flag==null){
				flag="classCard";
			}
			if(locationid==null){
				locationid="%%";
			}
			if(locationid.equalsIgnoreCase("all")){
				locationid="%%";
			}
			if(accyear==null){
				accyear="%%";
			}
			if(classname.equalsIgnoreCase("all")){
				classname="%%";
			}
			if(sectionid.equalsIgnoreCase("all")){
				sectionid="%%";
			}
			if(streamId==null || streamId.equalsIgnoreCase("all")){
				streamId="%%";
			}

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new IDGenerator().getStudentListforPrint(locationid,accyear,classname,sectionid,streamId,flag,custdetails);
				
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
				+ " Control in AdminMenuAction : StudentListforPrint Ending");

		return null;

	}
	
	
	
	public ActionForward getAccYear(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAccYear Starting");
		
		

		
		String academic_year = (String) request.getSession(false).getAttribute("academicYear");

			try {
				
				UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
					academic_year = HelperClass.getCurrentYearID(custdetails);
				}
		
				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
				
				JSONObject jsonobj = new JSONObject();
		
				jsonobj.put("AccYearList", accYearList);
		
				response.getWriter().print(jsonobj);
	
	
			} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getAccYear Ending");

		return null;
	}
	
	public ActionForward generateRollNo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : generateRollNo Starting");
		
		try {
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEROLLNO);
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("academic_year", academic_year);
			request.setAttribute("hloc", location);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : generateRollNo Ending");
		
		return mapping.findForward(MessageConstants.GENERATE_ROLL_NO);
		}
	
	
	public ActionForward getStudentDetailsLByFeeFilter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDetailsLByFeeFilter Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
               if(classname.equalsIgnoreCase("all")){
				
				classname="%%";
				//.out.println("academic year for all classname:" +accyear);
				list = new StudentRegistrationDelegate().getStudentDetailsLByFeeFilter(locationid,accyear,classname,custdetails);
			  }
               else{
   				
   				list = new StudentRegistrationDelegate().getStudentDetailsLByFeeFilter(locationid,accyear,classname,custdetails);
   				//.out.println("class detail id::" +list.get(0).getClassDetailId());
   			     }


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
				+ " Control in AdminMenuAction : getStudentDetailsLByFeeFilter Ending");

		return null;

	}
	
	public ActionForward getStudentListByFeeSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : getStudentListByFeeSection Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatus(custdetails);
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

//raji
			   if(sectionid.equalsIgnoreCase("all"))
			   {
			      sectionid="%%";
			      list = new StudentRegistrationDelegate().getStudentListByFeeSection(locationid,accyear,classname,sectionid,custdetails);
			   }
			   else
			   {
				      list = new StudentRegistrationDelegate().getStudentListByFeeSection(locationid,accyear,classname,sectionid,custdetails);
			   }
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
				+ " Control in AdminMenuAction : getStudentListByFeeSection Ending");

		return null;

	}
	public ActionForward PrintPreviewStaffMultipleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : PrintPreviewStaffMultipleIDCard Starting");
     
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PRINTSTAFFIDCARDMULTIPLE);
			
	/*	String locationId[]=request.getParameter("location").split(",");
		String accyear[]=request.getParameter("accyear").split(",");
		String studentId[]=request.getParameter("studentId").split(",");
		String streamId[]=request.getParameter("streamId").split(",");
		List<StudentIDVo> studentList = new StudentIDDaoImpl().getstudentIDPDFReport(accyear,locationId,studentId);
		List<StudentIDVo> list = new ArrayList<StudentIDVo>();

		for (int i = 0; i < studentList.size(); i++) {

			StudentIDVo vo = new StudentIDVo();
			String fileName = studentList.get(i).getImage();
			String filePath = request.getRealPath("/")+ "FIles/STUDENTIMAGES/" + fileName.trim();
			vo.setImages(".\\FIles\\STUDENTIMAGES\\" + fileName.trim());

			vo.setStuName(studentList.get(i).getStuName());
			vo.setClassName(studentList.get(i).getClassName() + "&"
					+ studentList.get(i).getSection());
			vo.setSection(studentList.get(i).getSection());
			vo.setFatherName(studentList.get(i).getFatherName());
			vo.setAdress(studentList.get(i).getAdress());
			vo.setPhone(studentList.get(i).getPhone());
			vo.setSecodaryPhone(studentList.get(i).getSecodaryPhone());
			vo.setValidity(studentList.get(i).getValidity());
			vo.setAdmissionno(studentList.get(i).getAdmissionno());
			vo.setMotherName(studentList.get(i).getMotherName());
			list.add(vo);

	
		request.setAttribute("template", accyear[0]+locationId[0]+streamId[0]);
		request.setAttribute("StudentList", list);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");
*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : PrintPreviewStaffMultipleIDCard Ending");

		return mapping.findForward(MessageConstants.PrintPreviewStaffMultipleIDCard);
	}
	public ActionForward getStudentPromotedClassList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentPromotedClassList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
			String academic_year=request.getParameter("accyear");
			String location=request.getParameter("location");
			String classid=request.getParameter("classId");
			
			StudentRegistrationVo regVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			regVo.setAcademicYearId(academic_year);
			regVo.setLocationId(location);
			regVo.setCustid(custdetails.getCustId());
			

			if(classid.equalsIgnoreCase("all")){
				classid="%%";
				regVo.setClasscode(classid);
				list = delegateObj.getPromotedClassList(regVo,custdetails);
			}
			else{
				regVo.setClasscode(classid);
				list = delegateObj.getPromotedClassList(regVo,custdetails);
			}
			
			//list = delegateObj.getPromotedClassList(regVo);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getPromotedClassWiseList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentPromotedClassList Ending");

		return null;
	}
	public ActionForward getStudentPromotedClassSectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentPromotedClassSectionList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
			String academic_year=request.getParameter("accyear");
			String location=request.getParameter("location");
			String classid=request.getParameter("classId");
			String sectionid=request.getParameter("sectionid");
			
			StudentRegistrationVo regVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			regVo.setAcademicYearId(academic_year);
			regVo.setLocationId(location);
			regVo.setClasscode(classid);
		
			
			if(sectionid.equalsIgnoreCase("all")){
				sectionid="%%";
				regVo.setClassSectionId(sectionid);
				list = delegateObj.getPromotedClassSectionList(regVo,custdetails);
			}
			else{
				regVo.setClassSectionId(sectionid);
				list = delegateObj.getPromotedClassSectionList(regVo,custdetails);
			}
			
			//list = delegateObj.getPromotedClassSectionList(regVo);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getPromotedSectionWiseList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentPromotedClassSectionList Ending");

		return null;
	}
	
	public ActionForward studentDemotedList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentDemotedList Starting");

		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(custdetails);
			request.setAttribute("classlist", classlist);
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			if(location.equalsIgnoreCase("all")){
				location="%%";
			}
			if (academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(custdetails);
			}
			
			StudentRegistrationVo regVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			regVo.setAcademicYearId(academic_year);
			regVo.setLocationId(location);
		
			
			list = delegateObj.getDemotedListDetails(regVo,custdetails);

			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getStudentDemotedList", list);

			response.getWriter().print(jsonobj);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentDemotedList Ending");

		return null;
	}
	
	public ActionForward getStudentDemotedClassList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDemotedClassList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
			String academic_year=request.getParameter("accyear");
			String location=request.getParameter("location");
			String classid=request.getParameter("classId");
			
			StudentRegistrationVo regVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			regVo.setAcademicYearId(academic_year);
			regVo.setLocationId(location);
			regVo.setCustid(custdetails.getCustId());
			

			if(classid.equalsIgnoreCase("all")){
				classid="%%";
				regVo.setClasscode(classid);
				list = delegateObj.getDemotedClassList(regVo,custdetails);
			}
			else{
				regVo.setClasscode(classid);
				list = delegateObj.getDemotedClassList(regVo,custdetails);
			}
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getDemotedClassWiseList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDemotedClassList Ending");

		return null;
	}
	
	public ActionForward getStudentDemotedClassSectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDemotedClassList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			
			String academic_year=request.getParameter("accyear");
			String location=request.getParameter("location");
			String classid=request.getParameter("classId");
			String sectionid=request.getParameter("sectionid");
			
			StudentRegistrationVo regVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			
			regVo.setAcademicYearId(academic_year);
			regVo.setLocationId(location);
			regVo.setClasscode(classid);
			regVo.setCustid(custdetails.getCustId());
			
			if(sectionid.equalsIgnoreCase("all")){
				sectionid="%%";
				regVo.setClassSectionId(sectionid);
				list = delegateObj.getDemotedClassSectionList(regVo,custdetails);
			}
			else{
				regVo.setClassSectionId(sectionid);
				list = delegateObj.getDemotedClassSectionList(regVo,custdetails);
			}
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("getDemotedSectionWiseList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDemotedClassList Ending");

		return null;
	}
	
	public ActionForward studentPromotedPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotedPage Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTPROMOTION);
		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
			String promotedId = request.getParameter("promotedId");

			StudentRegistrationVo list = new StudentRegistrationDelegate().getStudentPromotedChange(studentId,accYear,locationId,promotedId,custdetails);
			 
			request.setAttribute("studentPromotedSetting", list);
			
			request.setAttribute("historyaccYear", accYear);
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			request.setAttribute("historysearchBy", request.getParameter("historysearchBy"));
			
            request.setAttribute("tabstatus", request.getParameter("tabstatus"));
			request.setAttribute("promaccYear", request.getParameter("promaccYear"));
			request.setAttribute("promlocId", request.getParameter("promlocId"));
			request.setAttribute("promclassname", request.getParameter("promclassname"));
			request.setAttribute("promsectionid", request.getParameter("promsectionid"));
			request.setAttribute("prosearch", request.getParameter("prosearch"));
			request.setAttribute("demoaccYear", request.getParameter("demoaccYear"));
			request.setAttribute("demolocId", request.getParameter("demolocId"));
			request.setAttribute("democlassname", request.getParameter("democlassname"));
			request.setAttribute("demosectionid", request.getParameter("demosectionid"));
			request.setAttribute("demosearch", request.getParameter("demosearch"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPromotedPage Ending");

		return mapping.findForward(MessageConstants.PROMOTEDPAGE);
	}
	public ActionForward GroupListbyJS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : GroupListbyJS Starting");

		//String accyear = request.getParameter("accyear");
		List<ElectionVo> Data = new ArrayList<ElectionVo>();
		try{
			
			String accyear=request.getParameter("accyear");
			//.out.println("check acc year=========="+accyear);
			if(accyear.equalsIgnoreCase("all")){
				accyear="%%";
			}
			//.out.println("check acc year after null=========="+accyear);
			Data = new ElectionDaoImpl().getGroupdetailsByJS(accyear);
			JSONObject obj = new JSONObject();
			obj.put("DataList", Data);
		response.getWriter().print(obj);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : GroupListbyJS Ending");

		return null;
	}
	
	public ActionForward studentDemotedToPromotingPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentDemotedToPromotingPage Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
			String promotedId = request.getParameter("promotedId");

			StudentRegistrationVo list = new StudentRegistrationDelegate().getStudentPromotedChange(studentId,accYear,locationId,promotedId,custdetails);

			request.setAttribute("studentPromotedSetting", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentDemotedToPromotingPage Ending");

		return mapping.findForward(MessageConstants.PROMOTINGPAGE);
	}
	
	public ActionForward getDairy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction:getDairy Starting");
		UserDetailVO user=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String userId=user.getUserId();
		String userType=user.getUserNametype();
		List<DairyDetailsVo> commentlist=new ArrayList<DairyDetailsVo>();
		try{
		
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");	
			
			LoginBD loginservice = new LoginBD();
			commentlist=loginservice.getDairy(userId,custdetails);
			
			JSONObject obj=new JSONObject();
			obj.put("commentlist", commentlist);
			response.getWriter().print(obj);
	
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		
		}
			
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction:getDairy: Ending");
		
		return null;
	}
	
	public ActionForward getElectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : electionList Starting");

		try{
			String academicYearID = request.getParameter("academicYear");
			
			////.out.println("accheckkkkkkkkkkkkkkk"+academicYearID);
		
			if(academicYearID.equalsIgnoreCase("all")){
				academicYearID="%%";
			}
	
			String groupNameID = request.getParameter("groupName");
			////.out.println("accheckkkkkkkkkkkkkkkGtrouppppppppppppp"+groupNameID);
		
			if(groupNameID.equalsIgnoreCase("all")){
				groupNameID="%%";
			}

			ElectionPojo pojo = new ElectionPojo();
			pojo.setAccyear(academicYearID);
			pojo.setGroupName(groupNameID);

			ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
			list = new ElectionBD().getElectionDetails(academicYearID,groupNameID);
			request.setAttribute("DataList", list);	
			
			JSONObject obj= new JSONObject();
			obj.put("DataList", list);
			response.getWriter().print(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : electionList Ending");

		return null;
	}
	
	public ActionForward searchByLocationOnly(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction    : searchByLocationOnly Starting");
		
		String locationId = request.getParameter("locationId"); 
		String status = request.getParameter("status"); 
		
		String accYear = (String) request.getSession(false).getAttribute("current_academicYear");
		
		StreamDetailsBD obj = new StreamDetailsBD();
		List<StreamDetailsVO> list = new ArrayList<StreamDetailsVO>(); 
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		try {
 			if(locationId!=null && locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
 			
 			list = new StreamDetailsBD().searchByLocationOnly(locationId,accYear,status,custdetails);
			
			//request.setAttribute("SearchList", list);
			
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
				+ " Control in StudentRegistrationAction : searchByLocationOnly Ending");

		return null;
	}
	
	
	
/*Election Category Setting MODEL-3------------------------------------------------------*/
	
	public ActionForward getElectionCategoryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getElectionCategoryList Starting");

		try{
			String academicYearID = request.getParameter("academicYear");
			
			//.out.println("accheckkkkkkkkkkkkkkk"+academicYearID);
		
			if(academicYearID.equalsIgnoreCase("all")){
				academicYearID="%%";
			}
			
	
			String groupNameID = request.getParameter("groupName");
			//.out.println("accheckkkkkkkkkkkkkkkGtrouppppppppppppp"+groupNameID);
		
			if(groupNameID.equalsIgnoreCase("all")){
				groupNameID="%%";
		
			}

			ElectionPojo pojo = new ElectionPojo();
			pojo.setAccyear(academicYearID);
			pojo.setGroupName(groupNameID);
			

			ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
			list = new ElectionBD().getElectionDetails(academicYearID,groupNameID);
			request.setAttribute("DataList", list);	
			JSONObject obj= new JSONObject();
			obj.put("DataList", list);
			response.getWriter().print(obj);
		
	/*	try{
			String academicYearID = request.getParameter("academicYear");
			
			//.out.println("accheckkkkkkkkkkkkkkk"+academicYearID);
		
			if(academicYearID.equalsIgnoreCase("all")){
				academicYearID="%%";
			}
			String groupNameID = request.getParameter("groupName");
			//.out.println("accheckkkkkkkkkkkkkkkGtrouppppppppppppp"+groupNameID);
		
			if(groupNameID.equalsIgnoreCase("all")){
				groupNameID="%%";
			}

			ElectionPojo pojo = new ElectionPojo();
			pojo.setAccyear(academicYearID);
			pojo.setGroupName(groupNameID);
			

			ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
			//list = new ElectionBD().getElectionDetails(academicYearID,groupNameID);
			list = new ElectionBD().getElectionCategoryList(academicYearID,groupNameID);
			
			request.setAttribute("DataList", list);	
			JSONObject obj= new JSONObject();
			obj.put("DataList", list);
			response.getWriter().print(obj);
			*/
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getElectionCategoryList Ending");

		return null;
	}	
	
	
	public ActionForward sectionListcheckTemporary(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: sectionListcheckTemporary Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		String user = HelperClass.getCurrentUser(request);

		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			/*ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);*/
			

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String streamId=request.getParameter("streamId"); 
			String status=request.getParameter("status");
			
			//.out.println("locationid is :-"+locationid);
			
			if(locationid==null || locationid.equalsIgnoreCase("all") || locationid.equalsIgnoreCase("")){
				locationid="%%";
				classname="%%";
				streamId="%%";
			}
		
			if(classname.equalsIgnoreCase("all") ||classname.equalsIgnoreCase("")){
				classname="%%";
			}
			if(streamId.equalsIgnoreCase("all") || streamId.equalsIgnoreCase("")){
				streamId="%%";
			}
		
			List<SectionForm> list = new ArrayList<SectionForm>();

			SectionBD sectionDelegate = new SectionBD();

			list = sectionDelegate.getstreamdetailsforOnchange(locationid,classname,streamId,status,custdetails);

			JSONObject obj= new JSONObject();
			obj.put("list",list);
			response.getWriter().print(obj);
			
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction: sectionListcheckTemporary Ending");

		return null;
	}
	
	public ActionForward SpecializationListforListOnchangeMethod(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : SpecializationListforListOnchangeMethod Starting");
		String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			
			SpecializationBD obj = new SpecializationBD();

			List<SpecializationVo> list = new ArrayList<SpecializationVo>();

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String streamId=request.getParameter("streamId");
			String status=request.getParameter("status");
			
			if(locationid==""||locationid==null || locationid.equalsIgnoreCase("all")){
				locationid="%%";
				classname="%%";
				streamId="%%";
			}
		
			if(classname==""||classname==null||classname.equalsIgnoreCase("all")){
				classname="%%";
			}
			if(streamId==""||streamId==null||streamId.equalsIgnoreCase("all")){
				streamId="%%";
			}
			
			list = obj.getstreamdetailsforOnchange(locationid,classname,streamId,status,custdetails);
			
			JSONObject obj1= new JSONObject();
			obj1.put("list", list);
			response.getWriter().print(obj1);
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AdminMenuAction : SpecializationListforListOnchangeMethod Ending");

		return null;
	}
	
	public ActionForward SubjectListforListOnchangeMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : SubjectListforListOnchangeMethod Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {

			String message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();
			
			String locationid = request.getParameter("location");
			String classname = request.getParameter("classname");
			String specialization=request.getParameter("specialization");
			String status=request.getParameter("status");
			//.out.println("locationid "+locationid);
			//.out.println("classname "+classname);
			//.out.println("specialization "+specialization);
			
			if(locationid==null || locationid.equalsIgnoreCase("all")||locationid.equalsIgnoreCase("")){
				locationid="%%";
			}
		
			if(classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")){
				classname="%%";
			}
			if(specialization.equalsIgnoreCase("-")){
				specialization="%%";
			}

			subjectlist = new AddSubjectDelegate().subjectdetailsOnchangeListingPage(locationid,classname,specialization,custdetails,status);

			JSONObject obj1= new JSONObject();
			obj1.put("list", subjectlist);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : SubjectListforListOnchangeMethod Ending");

		return null;
	}
	
	public ActionForward classListforListOnchangeMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : classListforListOnchangeMethod Starting");
	
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CLASSDETAILS);
			String locationid = request.getParameter("location");
			String streamId = request.getParameter("streamId");
			String status = request.getParameter("status");

			
			if(locationid==null || locationid.equalsIgnoreCase("") || locationid.equalsIgnoreCase("all")){
				locationid="%%";
				streamId="%%";
			}
			if(streamId==null || streamId.equalsIgnoreCase("") || streamId.equalsIgnoreCase("all")){
				streamId="%%";
			}
			/*if(streamId.equalsIgnoreCase("all")){
				streamId="%%";
			}*/
		
			List<ClassPojo> list = new ArrayList<ClassPojo>();

			ClassBD delegate = new ClassBD();
			list= delegate.getClassDetailsOnChangeFunction(streamId,locationid,status,custdetails);

			JSONObject obj1= new JSONObject();
			obj1.put("list", list);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : classListforListOnchangeMethod Ending");

		return null;
	}
	
	
	public ActionForward fineConfiguration(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : fineConfiguration Starting");
		List<feeReportVO> fineList=new ArrayList<feeReportVO>();
		
	try {
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String current_location=(String) request.getSession(false).getAttribute("current_schoolLocation");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_FEE);

		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_FEE);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_FEE_FINECONFIGURATION);
			
		
		
		request.setAttribute("schoolName", HelperClass.getAllLocation(custdetails));
		fineList=new FeeMasterDAOIMPL().getFineConfiguration(HelperClass.getCurrentYearID(custdetails),current_location,custdetails);
		request.setAttribute("fineList", fineList);
		request.setAttribute("AccyearId", HelperClass.getAllAcademicYear(custdetails));
		

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : fineConfiguration Ending");
		return mapping.findForward("fineConfiguration");
		
	}
	public ActionForward electionListForVoting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : electionListForVoting Starting");

		try{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_ELECTION_VOTERMACHINE);
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_ELECTION);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_ELECTION);
			
			String academicYear = "%%";
			String groupId = "%%";
			ArrayList<ElectionVo> dataList = new ArrayList<ElectionVo>();
			dataList = new ElectionBD().getElectionDetails(academicYear,groupId);
			request.setAttribute("DataList", dataList);	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : electionListForVoting Ending");

		return mapping.findForward(MessageConstants.electionList);
	}
	public ActionForward countingListForVoting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : countingListForVoting Starting");

		try{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_ELECTION_ELECTIONREPORT);
			
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(pojo);
			request.setAttribute("AccYearList", accYearList);
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : countingListForVoting Ending");

		return mapping.findForward(MessageConstants.countingListForVoting);
	}
	
	
	public ActionForward winnerList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : winnerList Starting");

		try{
			
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(pojo);
			request.setAttribute("AccYearList", accYearList);
			
			String accyear = request.getParameter("accyear");
			String group = request.getParameter("group");
			String titleID=request.getParameter("titleID");
			ArrayList<ElectionVo> dataList = new ArrayList<ElectionVo>();
			dataList = new ElectionDaoImpl().getWinnerList(accyear,group,titleID);
			request.setAttribute("DataList", dataList);
			if(dataList.size()>0){
			request.setAttribute("academicYear", dataList.get(0).getAccyear());	
			request.setAttribute("groupName", dataList.get(0).getGroupName());	
			request.setAttribute("electionName", dataList.get(0).getElectionTitle());	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : winnerList Ending");

		return mapping.findForward("winnerList");
	}
	
	public ActionForward transportFeeCollectionForFilteration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportFeeCollectionForFilteration Starting");
		
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			
			String accyear=request.getParameter("accyear");
			String location=request.getParameter("location");
			String classId=request.getParameter("classId");
			String divisionId=request.getParameter("divisionId");
			String searchby=request.getParameter("searchby").trim();
			if(accyear.equalsIgnoreCase("all")){
				accyear="%%";
			}
			if(location.equalsIgnoreCase("all")){
				location="%%";
			}
			if(classId.equalsIgnoreCase("all")){
				classId="%%";
			}
			if(divisionId.equalsIgnoreCase("all") || divisionId.equalsIgnoreCase("")){
				divisionId="%%";
			}
			if(searchby.equalsIgnoreCase("all")){
				searchby="%";
			}
			
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			list = new StudentRegistrationDaoImpl().getStudentListForTransportFilteration(accyear,location,classId,divisionId,searchby,pojo);
			
				JSONObject obj=new JSONObject();
				obj.put("List", list);
				response.getWriter().print(obj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : transportFeeCollectionForFilteration Ending");
		return null;




	}
	public ActionForward getStudentIdDesignList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentIdDesignList Starting");
		
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			List<IDcardVo> list = new ArrayList<IDcardVo>();
			
			File folder = new File(request.getRealPath("/")+ "CSS/IdCard/");
			File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	    	  if(!listOfFiles[i].getName().contains("transport") && listOfFiles[i].getName().contains("ACY")){
	    		  IDcardVo template=new IDcardVo();
	    		  
	    		  List<PageFilterVo> templateNameList=new ArrayList<PageFilterVo>();
	    		  
	    		  PageFilterpojo filterpojo=new PageFilterpojo();
	    		  	filterpojo.setLocationId(listOfFiles[i].getName().substring(4, 8));
	  				filterpojo.setAcademicYear(listOfFiles[i].getName().substring(0, 4));
	  				filterpojo.setStreamId(listOfFiles[i].getName().substring(8, 12));
	  				templateNameList=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo,custdetails);
	    		  
	  			
	  				if(templateNameList.size()>0){
	  					template.setIdCardtemplateValue(listOfFiles[i].getName());
	  					template.setIdCardtemplateName(templateNameList.get(0).getAcademicYear()+" "+templateNameList.get(0).getLocationName()+" "+templateNameList.get(0).getStreamName());
	  					list.add(template);
	  				}
	    	  }
	      } 
	    }
	    JSONObject obj=new JSONObject();
		obj.put("List", list);
		response.getWriter().print(obj);
	    
	    
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentIdDesignList Ending");
		return null;

	}
	public ActionForward getTemplateIdDesignList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTemplateIdDesignList Starting");
		
		
		try {
			String templateVal=request.getParameter("templateVal");
			List<IDcardVo> list = new ArrayList<IDcardVo>();
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			File folder = new File(request.getRealPath("/")+ "CSS/IdCard/");
			File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	    	  if(!listOfFiles[i].getName().contains("transport") && listOfFiles[i].getName().contains("ACY")){
	    		  IDcardVo template=new IDcardVo();
	    		  
	    		  List<PageFilterVo> templateNameList=new ArrayList<PageFilterVo>();
	    		  
	    		  PageFilterpojo filterpojo=new PageFilterpojo();
	    		  	filterpojo.setLocationId(listOfFiles[i].getName().substring(4, 8));
	  				filterpojo.setAcademicYear(listOfFiles[i].getName().substring(0, 4));
	  				filterpojo.setStreamId(listOfFiles[i].getName().substring(8, 12));
	  				templateNameList=new  StudentIDDaoImpl().getNewstudentIdCardDesignList(filterpojo,custdetails);
	    		  
	  			
	  				if(templateNameList.size()>0){
	  					template.setIdCardtemplateValue(listOfFiles[i].getName());
	  					template.setIdCardtemplateName(templateNameList.get(0).getAcademicYear()+" "+templateNameList.get(0).getLocationName()+" "+templateNameList.get(0).getStreamName());
	  					list.add(template);
	  				}
	    	  }
	      } 
	    }
	    JSONObject obj=new JSONObject();
		obj.put("List", list);
		response.getWriter().print(obj);
	    
	    
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTemplateIdDesignList Ending");
		return null;

	}
	

	
	public ActionForward StudentAppraisalReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StudentAppraisalReport Starting");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			try {
				
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_STUDENT);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_STUDENT);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTAPPRAISAL);
			/*if(location.equalsIgnoreCase("all"))
			{				
				location="%%";
				list = new StudentRegistrationDelegate().getConfidentialReportStudents(academic_year,location,userLoggingsVo);
			}
			else{
				
				list = new StudentRegistrationDelegate().getConfidentialReportStudents(academic_year,location,userLoggingsVo);
			}*/
			
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);

			/*List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			request.setAttribute("classlist", classlist);*/
				

			//request.setAttribute("studentList", list);
			request.setAttribute("hloc", location);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");
			request.setAttribute("academic_year", academic_year);
			
			request.setAttribute("historyaccYear", request.getParameter("historyaccYear"));
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionid", request.getParameter("historysectionid"));
			request.setAttribute("historysearchvalue", request.getParameter("historysearchvalue"));
			request.setAttribute("historystatus", request.getParameter("historystatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : StudentAppraisalReport Ending");

		return mapping.findForward(MessageConstants.STUDENTSAPPRAISAL);
	}
	
	
	
	public ActionForward AddStudentAppraisalReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :AddStudentAppraisalReport Starting");
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTAPPRAISAL);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
			String startDate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0];
			String enddate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1];
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
			
			StudentRegistrationVo list = new StudentRegistrationDelegate().singleStudentDetails(studentId,accYear,locationId,custdetails);			
		
			request.setAttribute("studentSearchList", list);
			request.setAttribute("startDate", startDate);
			request.setAttribute("enddate", enddate);

			/*JSONObject obj=new JSONObject();
			obj.put("studentSearchList", list);
			response.getWriter().print(obj);*/

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);

			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");
			
			request.setAttribute("historyaccYear", accYear);
			request.setAttribute("historylocId", locationId);
			request.setAttribute("historyclassname", request.getParameter("classname"));
			request.setAttribute("historysectionid", request.getParameter("sectionid"));
			request.setAttribute("historysearchvalue", request.getParameter("searchvalue"));
			request.setAttribute("historystatus", request.getParameter("status"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :AddStudentAppraisalReport Ending");

		return mapping.findForward(MessageConstants.ADDSTUDENTAPPRAISAL);
	}

	public ActionForward laboratory(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : laboratory Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LABORATORY);
			
            UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String message = request.getParameter("message");
			userLoggingsVo.setLocId(schoolLocation);
			
			List<ViewallSubjectsVo> lablist = new ArrayList<ViewallSubjectsVo>();
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			
            List<AddSubjectForm> list = new ArrayList<AddSubjectForm>();
            AddSubjectDelegate obj = new AddSubjectDelegate();
			list = obj.getLaboratoryDetails(userLoggingsVo);
			request.setAttribute("laboratoryDetails", list);
			
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(schoolLocation,userLoggingsVo);
			request.setAttribute("classList", classList);
			String classId = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			
			List<SpecializationVo> specializationList = new SpecializationBD().getSpecializationOnClassBased(classId+","+locationId,userLoggingsVo);
			
			lablist = new AddSubjectDelegate().labdetails(schoolLocation,userLoggingsVo);
		
			 //.out.println("LABORATORY"+list.size());
			//lablist = new AddSubjectDelegate().subjectdetails(schoolLocation);
			
			request.setAttribute("allsubjects", lablist);

		    request.setAttribute("locId1", request.getParameter("locId"));
		    request.setAttribute("classname1", request.getParameter("classname"));
		    request.setAttribute("specId1", request.getParameter("specId"));
		    request.setAttribute("status1", request.getParameter("status"));
		    
		    request.setAttribute("currentstatus", request.getParameter("currentstatus"));
		    request.setAttribute("curr_loc",schoolLocation);
			}
			catch (Exception e) {e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
 
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : laboratory Ending");

		return mapping.findForward(MessageConstants.LABORATORY);

}
	
	public ActionForward addLaboratory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addLaboratory Starting");
            String result=null;
            String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String classId = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			
			String title="New laboratory";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LABORATORY);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
		/*	ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(schoolLocation,userLoggingsVo);
			request.setAttribute("classList", classList);*/
			
			List<SpecializationVo> specializationList = new SpecializationBD().getSpecializationOnClassBased(classId+","+locationId,userLoggingsVo);
			
			String classname = request.getParameter("classId");
			String subjectname = request.getParameter("subject");
			
			String specialization=request.getParameter("specialization");
			String locationname=request.getParameter("locationname");
			AddSubjectForm form1= new AddSubjectForm();

			form1.setClassname(classname);
			form1.setSubjtname(subjectname);
			form1.setLocationName(locationname);
          
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addLaboratory Ending");

		return mapping.findForward(MessageConstants.ADD_LAB);
	}
	
	public ActionForward getSpecialization(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getSpecialization Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String classId = request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			//.out.println("Specilization Action classId: "+request.getParameter("classId"));
			List<SpecializationVo> specializationList = new SpecializationBD().getSpecializationOnClassBased(classId+","+locationId,custdetails);
			
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
				+ " Control in AdminMenuAction : getSpecialization Ending");

		 return null;   
	}
	
	public ActionForward LabListforListOnchangeMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : LabListforListOnchangeMethod Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {

			String message = request.getParameter("message");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

		/*	ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			
			request.setAttribute("AccYearList", accYearList);*/
			
			List<AddSubjectForm> lablist = new ArrayList<AddSubjectForm>();
			
			String locationid = request.getParameter("location");
			String classname = request.getParameter("classname");
			String specialization=request.getParameter("specialization");
			String status=request.getParameter("status");
		
			if(classname.equalsIgnoreCase("all") || classname.equalsIgnoreCase("")){
				classname="%%";
			}
			if(specialization.equalsIgnoreCase("all") || specialization.equalsIgnoreCase("") || specialization.equalsIgnoreCase("-")){
				specialization="%%";
			}

			lablist = new AddSubjectDelegate().labdetailsOnchangeListingPage(locationid,classname,specialization,custdetails,status);

			JSONObject obj1= new JSONObject();
			obj1.put("list", lablist);
			response.getWriter().print(obj1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : LabListforListOnchangeMethod Ending");

		return null;
	}
	
	public ActionForward getStudentDetailsLByClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentDetailsLByClass Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");

			String locationid = request.getParameter("location");
			String classname = request.getParameter("classId");
			String status = request.getParameter("status");
			if(status=="" || status==null){
				status="active";
			}
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list =  new StudentRegistrationDelegate().getStudentDetailsLByFilter(locationid,accyear,classname,status,custdetails);
		
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
				+ " Control in AdminMenuAction : getStudentDetailsLByClass Ending");

		return null;

	}
	
	public ActionForward getStudentListByDivision(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : getStudentListByDivision Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatus(custdetails);
			request.setAttribute("AccYearList", accYearList);
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			String locationid = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String status = request.getParameter("status");
			if(status=="" || status==null){
				status="active";
			}
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDelegate().getStudentListBySection(locationid,accyear,classname,sectionid,status,custdetails);
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
				+ " Control in AdminMenuAction : getStudentListByDivision Ending");

		return null;

	}
	
	public ActionForward getStudentListBySpecialization(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : getStudentListBySpecialization Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatus(custdetails);
			request.setAttribute("AccYearList", accYearList);
			
			String accyear = (String) request.getSession(false).getAttribute("current_academicYear");
			String locationid = request.getParameter("location");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String specName = request.getParameter("spec_Name");
			
			ExaminationDetailsVo details = new ExaminationDetailsVo();
			details.setAccyear(accyear);
			details.setLocationid(locationid);
			details.setClassid(classname);
			details.setSectionid(sectionid);
			details.setSpecialization(specName);
			details.setCustid(custdetails.getCustId()); 
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDelegate().getStudentListBySpecialization(details,custdetails);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("getSpecializationWiseList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentListBySpecialization Ending");

		return null;

	}
	
	//---------Student Photosheet Start--------
	
	public ActionForward studentPhotosheet(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentPhotosheet : Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		
		try {
			
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_PHOTOSHEET);
			CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
				
			
				list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location,userLoggingsVo);
			
		
				
				
	
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);


			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			
			
			request.setAttribute("classlist", classlist);
				

			request.setAttribute("studentList", list);
			request.setAttribute("hloc", location);


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
	

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPhotosheet : Ending");

		return mapping.findForward(MessageConstants.studentphotosheet);
	}
	
	public ActionForward studentPhotosheetAccList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPhotosheetAccList Starting");


		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			
			String locationId = request.getParameter("locationId");
			String accYear = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
		

			//List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().getIDCardPhotoSheet(sectionId,classId,accYear,locationId,userLoggingsVo);
			
			
			
			request.setAttribute("studentSearchList", list);
			
			/*request.setAttribute("template", accYear+locationId+list.get(0).getStreemcode());*/
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("studentSearchList", list);
			jsonobj.put("studentSearchList", list);

			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentPhotosheetAccList Ending");

		return null;
	}
	//---------Student Photosheet End--------	
	
	public ActionForward tcgeneration(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : tcgeneration Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
			
			 LocationVO custSchoolInfo=new LocationVO();	
			 custSchoolInfo=HelperClass.getCustSchoolInfo(userLoggingsVo,location);
		     request.setAttribute("boardname", custSchoolInfo.getBoard());
		     
		    StudentRegistrationVo vo = new StudentRegistrationVo();
		  	vo.setLocation(location);
			vo.setAcademicYear(academic_year); 
		
			list = new StudentRegistrationDelegate().getNotGenTC(vo,userLoggingsVo);
			
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			
			System.out.println(list.size());
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
	
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
	
		/*	List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails();
			request.setAttribute("classlist", classlist);*/
				
			request.setAttribute("studentList", list);
			request.setAttribute("academic_year", academic_year);
			request.setAttribute("hloc", location);
		
		} 
		   catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : tcgeneration Ending");

		return mapping.findForward(MessageConstants.TC_GENERATION);
	}
	
	
	public ActionForward newTcGeneration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : newTcGeneration Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		String location = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String studentid = request.getParameter("studentid");
		String admid = request.getParameter("admid");
		String classid = request.getParameter("classid");
		
		String examdetails = request.getParameter("examdetails");
		String reason = request.getParameter("reason");
		String remarks = request.getParameter("remarks");
		String result = request.getParameter("result");
		StudentRegistrationVo vo =new StudentRegistrationVo();
		vo.setMeetingdate(request.getParameter("meetingdate"));
		String noofmeeting=request.getParameter("noofmeeting");
		String meetattain=request.getParameter("meetattain");
		String nooffail=request.getParameter("nooffail");
		if(!noofmeeting.equals("")){
			vo.setNoofmeeting((noofmeeting));	
		}
		else{
			vo.setNoofmeeting("0");
		}
		if(!meetattain.equals("")){
			vo.setMeetattain((meetattain));
		}
		else{
			vo.setMeetattain("0");
		}
		if(!nooffail.equals("")){
			vo.setNooffail((nooffail));
		}
		else{
			vo.setNooffail("0");
		}
		vo.setGencond(request.getParameter("gencond"));
		//.out.println(location);
		//.out.println(accyear);
		//.out.println(studentid);
		//.out.println(admid);
		//.out.println(classid);
		//.out.println(examdetails);
		//.out.println(reason);
		//.out.println(remarks+"------");
		
		String[] splitlocation = location.split(",");
		String[] splitaccyear =accyear.split(",");
		String[] splitstudentid =studentid.split(",");
		String[] splitadmid =admid.split(",");
		String[] splitclassid =classid.split(",");
		String status=null;
		
		//.out.println(splitlocation+" "+splitaccyear+" "+splitstudentid+" "+splitadmid+" "+splitclassid);
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
			
			status = new StudentRegistrationDelegate().generateStudentTC(splitlocation,splitaccyear,splitstudentid,
					splitadmid,splitclassid,examdetails,reason,remarks,result,log_audit_session,custdetails,vo);
			
			//.out.println("SSTTAATTUUSS   "+status);
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status", status);
			response.getWriter().print(jsonobj);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : newTcGeneration Ending");

		return null;
	}
	
	public ActionForward notGenTClist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : notGenTClist Starting");
		
		try {
		
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
			StudentRegistrationVo vo = new StudentRegistrationVo();
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			vo.setLocation(location);
			vo.setAcademicYear(academic_year); 
		
			
			list = new StudentRegistrationDelegate().getNotGenTC(vo,custdetails);
			
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
				+ " Control in AdminMenuAction : notGenTClist Ending");

		return null;
	}
	
	
	public ActionForward getStudentListByTC(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : getStudentListByTC Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().accYearListStatus(custdetails);
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String sortingby = request.getParameter("sortingby");
			String orderby = request.getParameter("orderby");
			
			if(locationid == "all"){
				locationid = "%%";
			}
			if(classname == "all"){
				classname = "%%";
			}
			if(sectionid == "all"){
				sectionid = "%%";
			}
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDelegate().getStudentListByTC(locationid,accyear,classname,sectionid,sortingby,orderby,custdetails);
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
				+ " Control in AdminMenuAction : getStudentListByTC Ending");

		return null;

	}
	
	/*public ActionForward TransferCertificateDownload(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : TransferCertificateDownload: Starting");
		UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String empId=userDetailVO.getUserId();
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
			
			//.out.println("INSiDE SECOND AJAX METHOD");
			final FileChooserDemo fc = new FileChooserDemo();
			//.out.println("FILE PATH" +fc.filepath);
			//.out.println("vvvvvvvvv  " +fc.filename);
			String file = fc.filepath;
			String FolderName = fc.filename;
			if(file==null) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("status", "failure");
				response.getWriter().print(jsonobj);
			}
			else {
			//.out.println("mmmmmmmmm");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String locationId=request.getParameter("locationId");
			String accyear=request.getParameter("accyear");
			String studentid=request.getParameter("studentid");
			String admid=request.getParameter("admid");
			String classid=request.getParameter("classid");
			
			//.out.println("locationId  =  =  = " + locationId);
			
			String sourceFileName = request.getRealPath("Reports/transfercertificate.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			//.out.println("TC details gathering before line");
			List<StudentRegistrationVo> TCdetails = new StudentRegistrationDelegate().TransferCertificateDownload(locationId,accyear,studentid,admid,classid);
			////.out.println("TCdetails  ==  "+TCdetails);
			List<StudentRegistrationVo> TCdetailsList = null;
			FileOutputStream outputFile = null;
			File secondDir=null;
			File firstDir=null;

			firstDir=new File(file);
			if(firstDir.exists()){
				secondDir=new File(file+"/"+admid+"-"+accyear);
				secondDir.mkdir();
			}
			else{

				new File(file+"/"+admid+"-"+accyear).mkdirs();
			}
			
			JRBeanCollectionDataSource beanColDataSource = null;
			for (int i = 0; i < TCdetails.size(); i++) {
				String fileName = "filename:"+TCdetailsList.get(0).getStudentFullName().trim().replaceAll(" ", "_") + ".pdf";
				HelperClass.recordLog_Activity(log_audit_session,"Student","TCGeneration","TCDownload",fileName);
				//.out.println("STUDENT "+i);
				TCdetailsList = new ArrayList<StudentRegistrationVo>();
				TCdetailsList.add(TCdetails.get(i));
				beanColDataSource = new JRBeanCollectionDataSource(TCdetailsList);
				
				Map parameters = new HashMap();
				parameters.put("admNo", admid);
				parameters.put("accyear", HelperClass.getAcademicYear() );
				JasperPrint print = JasperFillManager.fillReport(jasperreport,parameters, beanColDataSource);
				
				outputFile = new FileOutputStream(new File(file + "/"
						+ admid
						+ "-"
						+ accyear
						+ "/"
						+ TCdetailsList.get(0).getStudentFullName().trim().replaceAll(" ", "_") + ".pdf"));
				
				JasperExportManager.exportReportToPdfStream(print, outputFile);
				outputFile.close();
			}
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("status", "TC Downloaded Successfully");
			response.getWriter().print(jsonobj);
				response.sendRedirect("menuslist.html?method=tcgeneration");
			//.out.println("ccccccccccvikkkkk");
			}	
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : TransferCertificateDownload: Ending");
		return null;
	}*/
	
	
	public ActionForward GenTCListFilter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : GenTCListFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		StudentRegistrationVo vo = new StudentRegistrationVo();
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().accYearListStatus(custdetails);
			request.setAttribute("AccYearList", accYearList);
			
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String sortingby = request.getParameter("sortingby");
			String orderby = request.getParameter("orderby");
			String searchvalue = request.getParameter("searchvalue");
			String status = request.getParameter("status");
			
			
			if(classname.equals("all")){
				classname = "%%";
			}
			if(sectionid.equals("all")){
				sectionid = "%%";
			}
			
			//.out.println(orderby + "orderby");
			
			vo.setLocationId(locationid);
			vo.setAccyear(accyear);
			vo.setClassname(classname);
			vo.setSection_id(sectionid);
			vo.setSortBy(sortingby);
			vo.setOrderBy(orderby);
			vo.setSearchTerm(searchvalue);
			vo.setStatus(status);
			
			
			list = new StudentRegistrationDelegate().GenTCListFilter(vo,custdetails);
			
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
				+ " Control in AdminMenuAction : GenTCListFilter Ending");

		return null;
	}
	
	
	public ActionForward studentDemotedListSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentDemotedListSearch Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		StudentRegistrationVo vo = new StudentRegistrationVo();
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().accYearListStatus(custdetails);
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String searchname = request.getParameter("searchname");
			
			if(locationid.equals("all")){
				locationid = "%%";
			}
			if(classname.equals("all")){
				classname = "%%";
			}
			if(sectionid.equals("all")){
				sectionid = "%%";
			}
			
			
			vo.setLocationId(locationid);
			vo.setAccyear(accyear);
			vo.setClassname(classname);
			vo.setSection_id(sectionid);
			vo.setSearchTerm(searchname);
		
			
			list = new StudentRegistrationDelegate().GenTCListSearch(vo,custdetails);
			
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
				+ " Control in AdminMenuAction : studentDemotedListSearch Ending");

		return null;
	}
	

	public ActionForward tcDownloadPdf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EventsAction : tcDownloadPdf Starting");

			try {
				request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
				request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
						LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
				
				UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				String locationId=request.getParameter("locationId");
				String accyear=request.getParameter("accyear");
				String studentid=request.getParameter("studentid");
				String admid=request.getParameter("admid");
				String classid=request.getParameter("classid");
				
				//.out.println("locationId  =  =  = " + locationId);
				List<StudentRegistrationVo> TCdetails = new StudentRegistrationDelegate().TransferCertificateDownload(locationId,accyear,studentid,admid,classid,pojo);
				
				String sourceFileName = request.getRealPath("Reports/transfercertificate.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(TCdetails);

				//String PropfilePath = getServlet().getServletContext().getRealPath("")+"\\images\\" + ImageName.trim();

				String schName = res.getString("SchoolName");
				
				//.out.println("SCHOOL NAME "+schName);
				String EventAddress=res.getString("EventAdd");
				Map<String, Object> parameters = new HashMap<String, Object>();
				
				for (int i = 0; i < TCdetails.size(); i++) {
				parameters.put("admNo", HelperClass.getMonthFullName(admid));
				parameters.put("year", accyear);
				}
				
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "Event Result - " + ".pdf\"");
				ServletOutputStream outstream = response.getOutputStream();
				outstream.write(bytes, 0, bytes.length);
				outstream.flush();
				
				
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in EventsAction : tcDownloadPdf ending");

			return null;
	}
	
	public ActionForward getTeacherList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : getTeacherList Starting");
		
	
		
		List<TeacherVo> list = new ArrayList<TeacherVo>();

		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			list=HelperClass.getAllTeacherList(custdetails);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("teacherList", list);
			response.getWriter().print(jsonobj);
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTeacherList Ending");

		return null;
	}
	
	public ActionForward getTodayTeacherList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : getTodayTeacherList Starting");
		
	
		
		List<TeacherVo> list = new ArrayList<TeacherVo>();

		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			list=HelperClass.getTodayTeacherList(request.getParameter("tperiod"),request.getParameter("timetableId"),custdetails);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("teacherList", list);
			response.getWriter().print(jsonobj);
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getTodayTeacherList Ending");

		return null;
	}
	public ActionForward gettodaytimetablelist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gettodaytimetablelist Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_TIMETABLESUB);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String classId = request.getParameter("classId");
			String sectionid=request.getParameter("sectionid");
			String locationId=request.getParameter("locationId");
			if(classId==null){
				classId="ALL";
			}
			if(sectionid==null){
				sectionid="ALL";
			}
			if(locationId==null){
				locationId="ALL";
			}
			/*
			 * String accyearid =
			 * request.getAttribute("current_academicYear").toString();
			 */
			String accyearid = request.getSession(false).getAttribute("current_academicYear").toString();
			/* String accyearid = HelperClass.getCurrentYearID(); */

			ArrayList<TimeTableVo> arr =null;
			if (classId != null) {
				arr= new TimeTableDaoImpl().getClassTimeTableTodayListByClass(accyearid, sectionid,classId,locationId,userLoggingsVo);
				request.setAttribute("ClassTodayTimeTableList", arr);
				
			}
			ArrayList<TimeTableVo> teacherSustituteLis=new TimeTableDaoImpl().getTeacherTimeTableTodayListByClass(userLoggingsVo);
			request.setAttribute("teacherSustituteLis", teacherSustituteLis);
			
			if(teacherSustituteLis.size()>0){
				if(teacherSustituteLis.get(0).getStatus().equalsIgnoreCase("notSubstituted")) {
					request.setAttribute("notSubstituted", "Teacher List For Substitution");
					request.setAttribute("heading", "Teacher List For Substitution");
					request.setAttribute("periodT", "Vacant");
					
				}
				else {
					request.setAttribute("Substituted", "Substituted Teacher List");
					request.setAttribute("heading", "Substituted Teacher List");
					request.setAttribute("periodT", "Substituted");
				}
			}else{
				request.setAttribute("Substituted", "Substituted Teacher List");
				request.setAttribute("heading", "No Vacant Periods");
				//request.setAttribute("periodT", "Substituted");
			}
			
			
		} catch (Exception e) {

			/*e.printStackTrace();
			logger.error(e.getMessage(), e);*/
			String tatt="Please Enter Today Attendance First";
			/*request.setAttribute("tatt", "Please Enter Today Attendance First");*/
			response.sendRedirect("menuslist.html?method=getStaffAttendance&tatt="+tatt);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : gettodaytimetablelist Ending");

		return mapping.findForward("ClassTodayTimeTableList");

	}
	
	public ActionForward feeTypeList(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : feeTypeList Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		StudentRegistrationVo vo = new StudentRegistrationVo();
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_TYPE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String isActive="Y";
			List<FeeNameVo> feetypelist = new FeeMasterDAOIMPL().getFeeTypeList(isActive,custdetails);
			request.setAttribute("feetypelist",feetypelist);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeTypeList Ending");

		return mapping.findForward(MessageConstants.FEE_TYPE_LIST);
	}
	
	public ActionForward expenditureDetailsList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	


		String status = request.getParameter("result");
		if (status != null) {
			if (status.equalsIgnoreCase("true")) {
				request.setAttribute("successmessagediv",MessageConstants.DeleteMsgExpenditure);
			} else {
				request.setAttribute("errormessagediv",MessageConstants.DeleteErrorMsgExpenditure);
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : expenditureDetailsList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXPENDITURE);
			
			ExpenditureVO vo = new ExpenditureVO();

			vo.setExpenditureTitle(request.getParameter("searchvalue"));
            vo.setIsActive("Y");
			request.setAttribute("searchfee",request.getParameter("searchvalue"));

			ArrayList<ExpenditureVO> expndList = new ExpenditureBD().getExpenditureDetails(vo);
			//.out.println(expndList.size());
			
			request.setAttribute("expndList", expndList);
			request.getSession().setAttribute("expndList1", expndList);
			
			// for downloading

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : expenditureDetailsList Ending");

		return mapping.findForward(MessageConstants.EXPENDITURE_DETAILS_LIST);
	}
	
/*	public ActionForward LocationXLupload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in AdminMenuAction : LocationXLupload Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_LOCATION);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in AdminMenuAction : LocationXLupload Ending");

		return mapping.findForward("LocationXLupload");
	}*/
	
	public ActionForward staffListByLocIdAndDeptId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffListByLocIdAndDeptId Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFRENUMERATION);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String locId = request.getParameter("locId");
			String department = request.getParameter("department"); 
			String searchname = request.getParameter("searchname");
			
			if(locId=="" || locId==null || locId.equalsIgnoreCase("all")){
				locId="%%";
			}
			if(department=="" || department==null || department.equalsIgnoreCase("all")){
				department="%%";
			}
			
			if(searchname==null){
				searchname="";
			}else{
				searchname=searchname.trim();
			}
			
			StaffAttendanceVo vo=new StaffAttendanceVo();
			vo.setLocId(locId);
			vo.setDepartment(department);
			vo.setSearchtearm(searchname);

			ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();
			list = new TeacherRegistrationBD().getAllTeacherDetailsByLocIdAndDeptId(vo,custdetails);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("teacherList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffListByLocIdAndDeptId Ending");

		return null;
	}
	
	
	
	
	public ActionForward TransferCertificateDownload(ActionMapping mapping,
 			ActionForm form, HttpServletRequest request,
 			HttpServletResponse response) throws Exception {

 		logger.setLevel(Level.DEBUG);
 		JLogger.log(0, JDate.getTimeString(new Date())
 				+ MessageConstants.START_POINT);
 		logger.info(JDate.getTimeString(new Date())
 				+ " Control in ReportsAction : TransferCertificateDownload Starting");
 		try {
 			
 			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
 			
 			ServletContext servletContext = request.getServletContext();
 			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
 			String locationId=request.getParameter("locationId");
			String accyear=request.getParameter("accyear");
			String studentid=request.getParameter("studentid");
			String admid=request.getParameter("admid");
			String classid=request.getParameter("classid");
			String locationname=request.getParameter("locName");

   
			LocationVO custSchoolInfo = HelperClass.getCustSchoolInfo(cpojo,locationId);

			List<StudentRegistrationVo> TCdetails = new StudentRegistrationDelegate().TransferCertificateDownload(locationId,accyear,studentid,admid,classid,cpojo);
			
 			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(TCdetails);
 			
 			String PropfilePath=null;
 			
 			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
 				 PropfilePath = getServlet().getServletContext().getRealPath("")+ "\\images\\" + ImageName.trim();
 			}
 			else{
 				
 				 PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
 			}
 		

 			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

		 
			String schName =res.getString("SchoolName");
		
 			Map parameters=new HashMap();

 			parameters.put("admNo", admid);
			parameters.put("accyear", HelperClass.getAcademicYear() );
			parameters.put("locname",custSchoolInfo.getSchname() );
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
 	
 			String sourceFileName=request.getRealPath("Reports/transfercertificate.jrxml");

 			JasperDesign design = JRXmlLoader.load(sourceFileName);
 			JasperReport jasperreport = JasperCompileManager.compileReport(design);

 			byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,parameters,beanColDataSource);

 			response.setContentType("application/pdf");
 			response.setContentLength(bytes.length);
 			response.setHeader("Content-Disposition", "outline; filename=\""
 					+ "transfercertificate" + ".pdf\"");

 			ServletOutputStream outstream = response.getOutputStream();
 			outstream.write(bytes,0,bytes.length);
 			outstream.flush();
 		
 		} catch (Exception e) {
 			logger.error(e.getMessage(), e);
 			e.printStackTrace();
 		}
 		JLogger.log(0, JDate.getTimeString(new Date())
 				+ MessageConstants.END_POINT);
 		logger.info(JDate.getTimeString(new Date())
 				+ " Control in ReportsAction : TransferCertificateDownload Ending");

 		return null;
 	}
	
	
	public ActionForward getStudentListBySection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : getStudentListBySection Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYearListStatus(custdetails);
			request.setAttribute("AccYearList", accYearList);
			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");
			String searchvalue = request.getParameter("searchvalue");
			
			if(locationid == "all"){
				locationid = "%%";
			}
			if(classname == "all"){
				classname = "%%";
			}
			if(sectionid == "all" || sectionid==""){
				sectionid = "%%";
			}
			if(searchvalue == null){
				searchvalue ="";
			}else{
				searchvalue = searchvalue.trim();
			}
			
			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			list = new StudentRegistrationDelegate().getStudentListBySection(locationid,accyear,classname,sectionid,custdetails,searchvalue);
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
				+ " Control in AdminMenuAction : getStudentListBySection Ending");

		return null;

	}
	
	public ActionForward getApprovedList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in AdminMenuAction : getApprovedList Starting");
		try{
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			
			String searchtext = request.getParameter("searchtext");
			
			if(searchtext==null){
				searchtext="";
			}else{
				searchtext=searchtext.trim();
			}
			
			ArrayList<Issuedmenuvo> getapprvedlist = new TemporaryRegBD().getApprovedForms(searchtext,custdetails,accYear,schoolLocation);
			
			JSONObject json = new JSONObject();
			json.put("approvedlist", getapprvedlist);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getApprovedList Ending");

		return null;

	}
	
	public ActionForward getIssuedList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getIssuedList Starting");
		try{
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String searchtext = request.getParameter("searchtext");
			String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
			if(searchtext==null){
				searchtext="";
			}else{
				searchtext=searchtext.trim();
			}
			
			//.out.println("searchtextsearchtext  "+searchtext);
			
			List<Issuedmenuvo> list = new ArrayList<Issuedmenuvo>();
			list = new TemporaryRegBD().getissuedforms(searchtext,custdetails,schoolLocation+","+accYear); 
			
			JSONObject json = new JSONObject();
			json.put("issuedlist", list);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getIssuedList Ending");

		return null;

	}
	

	public ActionForward getRejectedList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getRejectedList Starting");
		try{
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			String searchtext = request.getParameter("searchtext");
			
			if(searchtext==null){
				searchtext="";
			}else{
				searchtext=searchtext.trim();
			}
			
			ArrayList<Issuedmenuvo> rejectlist = new TemporaryRegBD().getRejectedlist(searchtext,custdetails,accYear,schoolLocation);
			
			JSONObject json = new JSONObject();
			json.put("rejectlist", rejectlist);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getRejectedList Ending");

		return null;

	}
	
	public ActionForward getCancelList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getCancelList Starting");
		try{
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String searchtext = request.getParameter("searchtext");
			String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			if(searchtext==null){
				searchtext="";
			}else{
				searchtext=searchtext.trim();
			}
			
			ArrayList<Issuedmenuvo> getcancelledlist = new TemporaryRegBD().getCancelledForms(searchtext,custdetails,accYear,schoolLocation);
			
			JSONObject json = new JSONObject();
			json.put("cancellist", getcancelledlist);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getCancelList Ending");

		return null;
	}
	
	public ActionForward getSubmittedList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getSubmittedList Starting");
		try{
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String searchtext = request.getParameter("searchtext").trim();
			ArrayList<Issuedmenuvo> getsubmittedlist = new TemporaryRegBD().getSubmittedForms(searchtext,custdetails);
			
			JSONObject json = new JSONObject();
			json.put("submittedlist", getsubmittedlist);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getSubmittedList Ending");

		return null;
	} 
	
	public ActionForward getProcessedList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getProcessedList Starting");
		try{
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accYear=(String) request.getSession(false).getAttribute("current_academicYear");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			String searchtext = request.getParameter("searchtext");
			
			if(searchtext==null){
				searchtext="";
			}else{
				searchtext=searchtext.trim();
			}
			
			ArrayList<Issuedmenuvo> getprocessedlist = new TemporaryRegBD().getProcessedForms(searchtext,custdetails,accYear,schoolLocation);
			
			JSONObject json = new JSONObject();
			json.put("processedlist", getprocessedlist);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getProcessedList Ending");

		return null;
	}
	public ActionForward studentphotoupload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : studentphotoupload : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_PHOTOUPLOAD);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentphotoupload : Ending");

		return mapping.findForward(MessageConstants.STUDENT_PHOTO_UPLOAD);
	}


public ActionForward getUsersettings(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction: getUsersettings Starting");
	try {
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_USER_SETTINGS);
		
		UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		
		List<UserRecordVO> list = new ArrayList<UserRecordVO>();

		list = new UserManagementBD().getUserDetails(pojo);
		
		request.setAttribute("userRecords", list);
		
		request.setAttribute("historystatus", request.getParameter("historystatus"));
		
		request.setAttribute("currentstatus", request.getParameter("currentstatus"));

	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction: getUsersettings Ending");

	return mapping.findForward(MessageConstants.USER_SETTINGS);
}

public ActionForward getDeptStatusList(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getDeptStatusList Starting");

	try {
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DEPARTMENTDETAILS);

		ArrayList<DepartmentMasterVO> deplist = new ArrayList<DepartmentMasterVO>();

		String SearchName = request.getParameter("searchname");
		String status=request.getParameter("status");
        
		/*DepartmentMasterVO searchvo = new DepartmentMasterVO();
		searchvo.setSearch_name(SearchName);*/
		
		
		if (SearchName != null) {

			deplist = new DepartmentMasterBD().searchDepartment(SearchName.trim(),userLoggingsVo,status);
			request.setAttribute("DepartmentDetails", deplist);
			request.setAttribute("searchname", SearchName);
			request.setAttribute("searchexamid", SearchName);
			request.setAttribute("searchnamelist", SearchName);
		} else {
			deplist = new DepartmentMasterBD().getDeptStatusList(userLoggingsVo,status);

		}

		JSONObject json = new JSONObject();
		json.put("DepartmentDetailsStatusList", deplist);
		response.getWriter().print(json);
	

	}

	catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getDeptStatusList Ending");

	return null;

}

public ActionForward searchOccupationDetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : searchOccupationDetails Starting");
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

		String SearchName = request.getParameter("searchname"); 
		String status = request.getParameter("status");

     	list = obj.getOccupationDetailsSearch(SearchName.trim(),status,userLoggingsVo);
			
		JSONObject json = new JSONObject();
		json.put("list", list);
		response.getWriter().print(json);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : searchOccupationDetails Ending");

	return null;
}

public ActionForward SchoolWiseStageList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : SchoolWiseStageList : Starting");
	
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_TRANSPORT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_STAGEMASTER);
		
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		   request.setAttribute("academic_year", academic_year);
		   String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
    	   //.out.println("current school Location:" +schoolLocation);
    	   String currentlocation =null;
    	   if(schoolLocation.equalsIgnoreCase("all")){
    		   schoolLocation="%%";
    		   request.setAttribute("currentlocation", "All");
    		   request.setAttribute("locationId",schoolLocation);
    	   }
    	  else{
    		   currentlocation =new ExamDetailsBD().getlocationname(schoolLocation,userLoggingsVo);
    		   request.setAttribute("currentlocation", currentlocation);
    	   }
    	   request.setAttribute("locationId",schoolLocation);
    	   LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails(userLoggingsVo);
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("accYearList", accYearList);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : SchoolWiseStageList : Ending");

	return mapping.findForward(MessageConstants.STAGEWISE_LIST_SCHOOLWISE);
 }

public ActionForward stageWiseFeeSetup(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
 
 logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : stageWiseFeeSetup : Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.STAGE_WISE_AMMOUNT_COLLECTION);
		
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		   request.setAttribute("academic_year", academic_year);
		   String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
    	   //.out.println("current school Location:" +schoolLocation);
    	   String currentlocation =null;
    	   if(schoolLocation.equalsIgnoreCase("all")){
    		   schoolLocation="%%";
    		   request.setAttribute("currentlocation", "All");
    		   request.setAttribute("locationId",schoolLocation);
    	   }
    	  else{
    		   currentlocation =new ExamDetailsBD().getlocationname(schoolLocation,userLoggingsVo);
    		   request.setAttribute("currentlocation", currentlocation);
    	   }
    	   request.setAttribute("locationId",schoolLocation);
    	   LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails(userLoggingsVo);
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("accYearList", accYearList);
			
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historylocId", request.getParameter("historylocId"));

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : stageWiseFeeSetup : Ending");

	return mapping.findForward(MessageConstants.STAGEWISE_FEE_SETUP_LIST);
 }

public ActionForward getTransportConcessionStudentList(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())

			+ " Control in AdminMenuAction : getTransportConcessionStudentList Starting");
	try{
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String locationid = request.getParameter("location").trim();
		String accyear = request.getParameter("accyear").trim();
		String classid = request.getParameter("classId").trim();
		String sectionid = request.getParameter("sectionid").trim();
		String sortingby = request.getParameter("sortingby");
		String searchname = request.getParameter("searchname");
		
		if(locationid.equalsIgnoreCase("all") || locationid.equals("")){
			locationid = "%%";
		}
		if(classid.equalsIgnoreCase("all") || classid.equals("")){
			classid = "%%";
		}
		if(sectionid.equalsIgnoreCase("all") || sectionid.equals("")){
			sectionid = "%%";
		}
		if(searchname.equals("") || searchname.equals("null") || searchname.equalsIgnoreCase("all")){
			searchname = "";
		}else{
			searchname = searchname.trim();
		}
		 
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		list = new StudentRegistrationDelegate().getTransportConcessionStudentList(locationid,accyear,classid,sectionid,searchname,userLoggingsVo);
		
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
			+ " Control in AdminMenuAction : getTransportConcessionStudentList Ending");
	return null;
}


public ActionForward seachHomeWorkList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : seachHomeWorkList Starting");

	try {

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SMS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SMS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_INTERACTION_HOMEWORKS);
		 
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		List<SmsVo> homeworklist = new ArrayList<SmsVo>();
		String searchTerm = request.getParameter("searchname");
        String locid=request.getParameter("locid");
        String clasid=request.getParameter("clasid");
        String secid=request.getParameter("secid");
        String accid=request.getParameter("accid");
        String startdate=request.getParameter("startdate");
		String enddate=request.getParameter("enddate");
		
        if(locid.equalsIgnoreCase("all")){
		       locid="%%";
		      }
		      if(clasid.equalsIgnoreCase("All")){
		    	  clasid="%%";
			      }
		      if(secid.equalsIgnoreCase("all")){
		    	  secid="%%";
			      }
		      if(startdate==null || startdate.trim().equalsIgnoreCase("")){
					startdate = HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0];
				}else{
					startdate = HelperClass.convertUIToDatabase(startdate);
				}
				if(enddate==null || enddate.trim().equalsIgnoreCase("")){
					 enddate = HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1];
	            }else{
	            	enddate = HelperClass.convertUIToDatabase(enddate);
	             }
		    homeworklist = new SmsDeligate().getHomeWorkSearchlist(custdetails,locid,clasid,secid,accid,startdate,enddate);

				
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("homeworklist", homeworklist);
		response.getWriter().print(jsonobj);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : seachHomeWorkList Ending");

	return null;
  }

public ActionForward validateStage(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : validateStage Starting");

	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		
		AddStageVO vo=new AddStageVO();

		vo.setStageName(request.getParameter("name"));
		vo.setStage_id(request.getParameter("id"));
		vo.setLocId(request.getParameter("locId"));
		vo.setAccyearCode(HelperClass.getCurrentYearID(custdetails));
	
		
		String status = new StageDAOIMPL().validateStage(vo,custdetails);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("jsonResponse", status);
		response.getWriter().print(jsonobj);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : validateStage Ending");

	return null;
  }

public ActionForward othersmslist(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : othersmslist : Starting");
	
	
	try {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_INTERACTION_OTHERSMS);
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SMS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_INTERACTION_OTHERSMS);
	
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		CustomerDBDetails dbdetails = (CustomerDBDetails) request.getSession(false).getAttribute("CustDBDetails");
		
		SuddenHolidayListBD holidayListBD = new SuddenHolidayListBD();
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		request.setAttribute("academic_year", academic_year);
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
		request.setAttribute("locationList", locationList);

		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
		request.setAttribute("AccYearList", accYearList);

		List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(custdetails);
		request.setAttribute("classlist", classlist);
		
		ArrayList<SuddenHolidaySMSVO> arrayList = new ArrayList<SuddenHolidaySMSVO>();
		
		arrayList= holidayListBD.OtherSMSList(custdetails);
		
		String startDate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0];
		String enddate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1];
		
		request.setAttribute("othersmslist",arrayList);
		request.setAttribute("startDate",startDate);
		request.setAttribute("enddate",enddate);
	
		
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : othersmslist : Ending");

	return mapping.findForward(MessageConstants.OTHER_SMS_LIST);
}

public ActionForward addOtherSMS(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : addOtherSMS Starting");
	
	try {
		
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_INTERACTION_OTHERSMS);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		ClassPojo pojo = new ClassPojo();
		pojo.setCustid(custdetails);
		
		List<ClassPojo> classpojo =new CommunicationSettingsBD().getClassListDetails(pojo,custdetails);
		request.setAttribute("classpojo", classpojo);
		
		 ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
		request.setAttribute("locationList", locationList);
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : addOtherSMS Ending");

	return mapping.findForward(MessageConstants.OTHER_SMS_ENTRY);
}

public ActionForward schoolWiseRoutMaster(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
 logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : schoolWiseRoutMaster : Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_ROUTEMASTER);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		   request.setAttribute("academic_year", academic_year);
		   String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
    	   String currentlocation =null;
    	   if(schoolLocation.equalsIgnoreCase("all")){
    		   schoolLocation="%%";
    		   request.setAttribute("currentlocation", "All");
    		   request.setAttribute("locationId",schoolLocation);
    	   }
    	  else{
    		   currentlocation =new ExamDetailsBD().getlocationname(schoolLocation,custdetails);
    		   request.setAttribute("currentlocation", currentlocation);
    	   }
    	   request.setAttribute("locationId",schoolLocation);
    	   LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails(custdetails);
			request.setAttribute("locationDetailsList", list);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("accYearList", accYearList);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : schoolWiseRoutMaster : Ending");

	return mapping.findForward(MessageConstants.SCHOOL_WISE_ROUTMASTER);
  }



public ActionForward getAcademicYearStartDate(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getAcademicYearStartDate Starting");

	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String academic_year=request.getParameter("accyear");
		String startDate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0];
		String enddate=HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1];
		
		JSONObject dates = new JSONObject();
		dates.put("startdate",startDate);
		dates.put("enddate",enddate);
		response.getWriter().print(dates);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getAcademicYearStartDate Ending");

	return null;
  }
public ActionForward substitution(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : substitution Starting");


	try {
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		UserDetailVO user=(UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String userId=user.getUserId();
		
		

		String status =new TimeTableDaoImpl().getSubstitute(userId,custdetails);
		
		
		
		JSONObject jsonobj = new JSONObject();

		jsonobj.put("status", status);

		response.getWriter().print(jsonobj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}


	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : substitution Ending");

	return null;
}

public ActionForward ConcessionDetailList(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : FeeConcessionList Starting");
	 
	String username = null; 
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS); 
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_CONCESSIONDETAILS);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		username = HelperClass.getCurrentUserID(request);

		 String search=request.getParameter("searchvalue");
		 String locId=request.getParameter("location");
		 
		 if(locId.equalsIgnoreCase("")){
			 locId="%%";
		 }
		 if(search==null){
			 search="";
		 }else{
			 search=search.trim();
		 }
		
		ConcessionDetailsPojo vo = new ConcessionDetailsPojo();
         
		
		vo.setSearchName(search);
		vo.setLocId(locId);
		vo.setStatus(request.getParameter("status"));
		

		List<ConcessionDetailsPojo> list = new FeeSetupBD().getconcessiondetails(vo,custdetails);

		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails); 
		request.setAttribute("locationList", locationList);
		
		request.setAttribute("concessiondetailsearch",request.getParameter("searchvalue"));
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("concessiondetailsList", list);
		response.getWriter().print(jsonobj);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : FeeConcessionList Ending");

	return null;
}

public ActionForward getStudentListByTransportRequestCancel(ActionMapping mapping,ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : getStudentListByTransportRequestCancel Starting");
	
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().accYearListStatus(custdetails);
		request.setAttribute("AccYearList", accYearList);
		
		String locationid = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String classname = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");
		String istransport = request.getParameter("istransport");
		String searchvalue = request.getParameter("searchvalue");
		
		if(searchvalue==null){
			searchvalue="";
		}else{
			searchvalue=searchvalue.trim();
		}
		if(locationid.equalsIgnoreCase("all")){
			locationid = "%%";
		}
		if(classname.equalsIgnoreCase("all")){
			classname = "%%";
		}
		if(sectionid.equalsIgnoreCase("all") || sectionid==""){
			sectionid = "%%";
		}
		StudentRegistrationVo vo=new StudentRegistrationVo();
		vo.setLocationId(locationid);
		vo.setAcademicYearId(accyear);
		vo.setClassname(classname);
		vo.setSection_id(sectionid);
		vo.setStatus(istransport);
		vo.setSearchTerm(searchvalue);
		
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		list = new StudentRegistrationDelegate().getStudentListByTransportRequestCancel(vo,custdetails);
		 
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
			+ " Control in AdminMenuAction : getStudentListByTransportRequestCancel Ending");

	return null;
  }
public ActionForward getStudentListBySectionReportCard(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())

			+ " Control in AdminMenuAction : getStudentListBySection Starting");

	try{

		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");


		ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
				.accYearListStatus(custdetails);

		request.setAttribute("AccYearList", accYearList);
		String locationid = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String classname = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");
		/*String searchvalue = request.getParameter("searchvalue");*/

		if(locationid == "all"){
			locationid = "%%";
		}
		if(classname == "all"){
			classname = "%%";
		}
		if(sectionid == "all" || sectionid==""){
			sectionid = "%%";
		}

		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		list = new StudentRegistrationDelegate().getStudentListBySectionReportCard(locationid,accyear,classname,sectionid,custdetails);
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
			+ " Control in AdminMenuAction : getStudentListBySection Ending");

	return null;

}

public ActionForward getStudentListByFeeRequestCancel(ActionMapping mapping,ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())+ " Control in AdminMenuAction : getStudentListByTransportRequestCancel Starting");
	
	try{
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().accYearListStatus(custdetails);
		request.setAttribute("AccYearList", accYearList);
		
		String locationid = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String classname = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");
		String isConcession = request.getParameter("isConcession");
		String searchvalue = request.getParameter("searchvalue");
		
		if(searchvalue==null){
			searchvalue="";
		}else{
			searchvalue=searchvalue.trim();
		}
		if(locationid.equalsIgnoreCase("all")){
			locationid = "%%";
		}
		if(classname.equalsIgnoreCase("all")){
			classname = "%%";
		}
		if(sectionid.equalsIgnoreCase("all") || sectionid==""){
			sectionid = "%%";
		}
		StudentRegistrationVo vo=new StudentRegistrationVo();
		vo.setLocationId(locationid);
		vo.setAcademicYearId(accyear);
		vo.setClassname(classname);
		vo.setSection_id(sectionid);
		vo.setStatus(isConcession);
		vo.setSearchTerm(searchvalue);
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		list = new StudentRegistrationDelegate().getStudentListByFeeRequestCancel(vo,custdetails);
		 
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
			+ " Control in AdminMenuAction : getStudentListByTransportRequestCancel Ending");

	return null;
  }

public ActionForward getConcessionTypes(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			
			+ " Control in AdminMenuAction : getConcessionTypes Starting");
	
	try{
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		List<ConcessionDetailsPojo> list = new ArrayList<ConcessionDetailsPojo>();
		list = new StudentRegistrationDelegate().getSubConcessionTypes(custdetails);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("concessionTypeList", list);
		response.getWriter().print(jsonobj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getConcessionTypes Ending");
	
	return null;
	
}

public ActionForward bankEntry(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in BankMasterAction:bankEntry Starting");
	
	List<BankVO> list  = null;
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_MASTER);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		String searchText = request.getParameter("searchText");
		String status="Y";
		if(searchText!=null){

			list = new BankBD().getSearchBankList(searchText,custdetails,status);
			
			request.setAttribute("searchText", searchText);

		}
		else{
		list = new BankBD().getBankList(custdetails);

		request.setAttribute("banklist", list);
		}
		
		request.setAttribute("searchname1", request.getParameter("historysearchname"));
		request.setAttribute("status1", request.getParameter("historystatus"));
		
		request.setAttribute("currentstatus", request.getParameter("currentstatus"));
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(),e);
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in BankMasterAction:bankEntry Ending");
	
	return mapping.findForward(MessageConstants.BANK_LIST);
}

public ActionForward bankbranchList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in BankBranchMasterAction: bankbranchList Starting");
	
	List<BankBranchVO> list = null;
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
									LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_BRANCH_MASTER);
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String searchText = request.getParameter("searchText");
		String status="Y";
		if(searchText!=null){
			
			list = new BankBranchBD().getSearchBranchList(searchText,custdetails,status);
			
			request.setAttribute("searchText", searchText);

		}else{

			list = new BankBranchBD().getBranchList(custdetails);
		}
		
		request.setAttribute("bankbranchlist", list);
		
		request.setAttribute("currentstatus", request.getParameter("currentstatus"));
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in BankBranchMasterAction: bankbranchList Ending");
	
	return mapping.findForward(MessageConstants.BANK_BRANCH_LIST);

}

public ActionForward StaffIdDesign(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : StaffIdDesign Starting");
	
	String mainCss = request.getParameter("mainCss");
	String layout=request.getParameter("layout");
	String imgUrl=request.getParameter("imgUrl");
	//.out.println("imgurl"+imgUrl);
	
	//.out.println("realimgurl"+request.getRealPath("/")+imgUrl);
	if(!imgUrl.equalsIgnoreCase("noImage")){
		
		 FileInputStream is = null;
		         FileOutputStream os = null;
		         try {
		        	 File sourceFile=new File(request.getRealPath("/")+imgUrl);
		        	 File desFile=new File(request.getRealPath("/")+"images/IdCard/"+layout+".jpg");
		             is = new FileInputStream(sourceFile);
		             os = new FileOutputStream(desFile);
		             byte[] buffer = new byte[1024];
		             int length;
		             while ((length = is.read(buffer)) > 0) {
		                 os.write(buffer, 0, length);
		             }
		         }catch(Exception ex) {
		             //.out.println("Unable to copy file:"+ex.getMessage());
		         }   
		         finally {
		             try {
		                 is.close();
		                 os.close();
		             }catch(Exception ex) {}
		         }
		     }
	

	
	String newCssArray[] = mainCss.split("}");
	
	File file = new File(request.getRealPath("/")+ "CSS/IdCard/StaffId/"+layout+".css");
	if(file.exists()){
		
	}
	else{
		file.createNewFile();
	}
	
	for (String css : newCssArray) {
		//.out.println("each css" + css);

	}
	BufferedWriter bw = null;
	FileWriter fw = null;
	BufferedReader br = null;
	FileReader fr = null;
	String FILENAME =request.getRealPath("/")+ "CSS/IdCard/StaffId/"+layout+".css";
	File temp=null;
	temp = new File("StaffIdCard.css");
	String absolutePath = temp.getAbsolutePath();
	//.out.println("absolutePath  "+absolutePath);
	//.out.println("hello="+request.getRealPath("/")+ "CSS/IdCard/StaffId/"+layout+".css");
	try {

		//.out.println(FILENAME);

		fr = new FileReader(FILENAME);
		br = new BufferedReader(fr);

		String sCurrentLine;

		br = new BufferedReader(new FileReader(FILENAME));

		while ((sCurrentLine = br.readLine()) != null) {
			//.out.println(sCurrentLine);
			String[] words = sCurrentLine.split("\\s");
			String sCurrentLineArray[] = sCurrentLine.split("}");
			for (String css : words) {
				//.out.println("each css" + css);

			}

		}

		fw = new FileWriter(FILENAME);
		bw = new BufferedWriter(fw);
		bw.write(mainCss);

		//.out.println("Done");

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {

		try {

			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : StaffIdDesign Ending");
	return null;
}

public ActionForward casteDetailsSearch(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {


	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : casteDetailsSearch Starting");
	try {
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
		List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();

		String SearchName = request.getParameter("searchname"); 
		String sts = request.getParameter("status");
		
		list = obj.searchCaste(SearchName.trim(),sts,custdetails);
		
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
			+ " Control in AdminMenuAction : casteDetailsSearch Ending");

	return null;
}

public ActionForward casteCategoryDetailsSearch(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : casteCategoryDetailsSearch Starting");
	try {
	
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		ReligionCasteCasteCategoryBD obj = new ReligionCasteCasteCategoryBD();
		List<ReligionCasteCasteCategoryVo> list = new ArrayList<ReligionCasteCasteCategoryVo>();

		String SearchName = request.getParameter("searchname"); 
		String status = request.getParameter("status");

		list = obj.searchCasteCategory(SearchName.trim(),status,custdetails);
		
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
			+ " Control in AdminMenuAction : casteCategoryDetailsSearch Ending");

	return null;
}
public ActionForward rollNoGeneration(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())

			+ " Control in AdminMenuAction : rollNoGeneration Starting");
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	try{
		
		ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
				.accYearListStatus(custdetails);
		request.setAttribute("AccYearList", accYearList);
		String locationid = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String classname = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");
		String order1 = request.getParameter("order1");
		String order2 = request.getParameter("order2");
		String order3 = request.getParameter("order3");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		list = new StudentRegistrationDaoImpl().rollNoGeneration(locationid,accyear,classname,sectionid,order1,order2,order3,custdetails);
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
			+ " Control in AdminMenuAction : rollNoGeneration Ending");

	return null;

}


public ActionForward getStudentListBySectionForROllNo(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())

			+ " Control in AdminMenuAction : getStudentListBySectionForROllNo Starting");
	
	try{
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().accYearListStatus(custdetails);
 
		request.setAttribute("AccYearList", accYearList);
		String locationid = request.getParameter("location");
		String accyear = request.getParameter("accyear");
		String classname = request.getParameter("classId");
		String sectionid = request.getParameter("sectionid");
		String sortingby = request.getParameter("sortingby");
		String orderby = request.getParameter("orderby");
		
		
		if(classname == "all"){
			classname = "%%";
		}
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		list = new StudentRegistrationDaoImpl().getStudentListBySectionForRollNo(locationid,accyear,classname,sectionid,custdetails);
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
			+ " Control in AdminMenuAction : getStudentListBySectionForROllNo Ending");

	return null;

}

public ActionForward professionalTaxSettingsSave(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())

			+ " Control in AdminMenuAction : professionalTaxSettings Starting");
	
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PT);
		
		UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");

		String userType = userDetailVO.getUserNametype();
		String userCode = userDetailVO.getUserId();
		
		ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().accYearListStatus(custdetails);
		ProfessionalTaxPojo PTPojo=new ProfessionalTaxPojo();
		
		PTPojo.setStateId(request.getParameter("stateId"));
		PTPojo.setMonthly_salary(request.getParameter("monthly_salary"));
		PTPojo.setPt_levied(request.getParameter("pt_levied"));
		PTPojo.setUserId(userCode);
			String status=new StaffPayrollDaoImpl().professionalTaxSettingsSave(PTPojo,custdetails);
			
		JSONObject obj=new JSONObject();
		obj.put("status", status);
		response.getWriter().print(obj);
		
		

	} catch (Exception e) {

		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : professionalTaxSettings Ending");

	return null;

}

public ActionForward getProfessionalTaxSettings(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())

			+ " Control in AdminMenuAction : professionalTaxSettings Starting");
	
	try{
		List<ProfessionalTaxPojo> ptList=new ArrayList<ProfessionalTaxPojo>();
		
		ProfessionalTaxPojo PTPojo=new ProfessionalTaxPojo();
		PTPojo.setCountryId(request.getParameter("countryId"));
		PTPojo.setStateId(request.getParameter("stateId"));
		
			ptList=new StaffPayrollDaoImpl().getProfessionalTaxSettings(PTPojo,custdetails);
			
		JSONObject obj=new JSONObject();
		obj.put("ptList", ptList);
		response.getWriter().print(obj);
		
		

	} catch (Exception e) {

		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : professionalTaxSettings Ending");

	return null;

}


public ActionForward professionalTaxSettings(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())

			+ " Control in AdminMenuAction : professionalTaxSettings Starting");
	
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PT);

			request.setAttribute("locationList", HelperClass.getAllLocation(custdetails));

	} catch (Exception e) {

		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : professionalTaxSettings Ending");

	return mapping.findForward(MessageConstants.PROFESSIONAL_TAX_DISP);

}

public ActionForward individualStudentSearchPopUp(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction    : individualStudentSearch Starting");

	try {
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_STUDENTSEARCH);
		
		String studentId = request.getParameter("studentId");
		String accYear = request.getParameter("accyear");
		String locationId = request.getParameter("locationId");

		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId,custdetails);

		JSONObject obj=new JSONObject();
		obj.put("studentSearchList", list);
		response.getWriter().print(obj);

		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : individualStudentSearch Ending");

	return null;
}
public ActionForward themeDesign(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : themeDesign Starting");
	
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	
	String background = request.getParameter("background");
	String forecolor=request.getParameter("forecolor");
	String hoverColor=request.getParameter("hoverColor");
	String lighthoverColor=request.getParameter("lighthoverColor");
	String lightThemeColor=request.getParameter("lightThemeColor");
	
	String mainCss = "#main-header,.buttons,.primarythemebackgound,.todayHilight{background-color:"+background+";}"
			+ ".glyphicon-menu-hamburger,.glyphicon-home,.glyphicon-user,.icon-color{color:"+background+";}"
			+ ".panel-heading.leftNav,.modal-header{background-color:"+background+";}"
			+ ".navbar-inverse .navbar-nav>li>a,.headerNavBarB,.headerNavBarS,.panel-heading.leftNav h3.panel-title,.buttons,.headerLogoutIcon,.sideMenuIcon,.close,.primarythemecolor{color:"+forecolor+";} #pageHeading{color:"+background+";} "
			+ ".navbar-inverse .navbar-nav>li>a:focus, .headerNavBarB, .navbar-inverse .navbar-nav>li>a:hover{background-color:"+hoverColor+";}"
			+ "#allstudent tr:nth-child(n),.allstudent tr:nth-child(n), #allstudents tr:nth-child(n),.primarythemelightcolor{background-color:"+lightThemeColor+";}"
			+ "#allstudent tr:nth-child(2n),.allstudent tr:nth-child(2n), #allstudents tr:nth-child(2n){background-color: #FFFFFF;}"
			+ "#allstudent tr:hover, .allstudent tr:hover, #allstudents tr:hover,#allstudent th, .allstudent th{background-color:"+lighthoverColor+";}"
			+ ".pagelinks strong, .pagelinks a.active{background-color:"+background+"}"
			+ ".pagelinks strong, .pagelinks a.active{color:"+forecolor+"}"
			+ ".myDairy header{background-color:"+lighthoverColor+";}"
			+ ".backGround{background-color:"+lighthoverColor+";}"
			+ ".ui-draggable .ui-dialog-titlebar,.ui-dialog-buttonset button,.ui-button-text,.ui-dialog-title,.hoverColor:hover{background-color:"+background+";color:"+forecolor+"}";
	
	File file = new File(request.getRealPath("/")+ "SCHOOLINFO/"+custdetails.getDomain()+"/theme.scss");
	if(file.exists()){
		
	}
	else{
		file.createNewFile();
	}
	
	PrintWriter writer = new PrintWriter(file);
	writer.print("");
	writer.close();
	
	BufferedWriter bw = null;
	FileWriter fw = null;
	BufferedReader br = null;
	FileReader fr = null;
	String FILENAME =request.getRealPath("/")+ "SCHOOLINFO/"+custdetails.getDomain()+"/theme.scss";
	File temp=null;
	temp = new File("Themes.scss");
	String absolutePath = temp.getAbsolutePath();
	try {
		
		//.out.println(FILENAME);

		fr = new FileReader(FILENAME);
		br = new BufferedReader(fr);

		String sCurrentLine;

		br = new BufferedReader(new FileReader(FILENAME));

	

		fw = new FileWriter(FILENAME);
		bw = new BufferedWriter(fw);
		
		bw.write(mainCss);

		//.out.println("Done");
		
		JSONObject obj=new JSONObject();
		obj.put("status", "Applying Theme...");
		response.getWriter().println(obj);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {

		try {

			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : themeDesign Ending");
	return null;
}
public ActionForward NewCancelStudentTC(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction    : NewCancelStudentTC Starting");
			
	String location = request.getParameter("location");
	String accyear = request.getParameter("accyear");
	String studentid = request.getParameter("studentid");
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	
	String[] splitlocation = location.split(",");
	String[] splitaccyear =accyear.split(",");
	String[] splitstudentid =studentid.split(",");
	
	String status=null;
	
	
	try{
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_TC_GENERATION);
		
		status = new StudentRegistrationDaoImpl().NewCancelStudentTC(splitlocation,splitaccyear,splitstudentid,custdetails);
		
		//.out.println("8888888888"+status);
		JSONObject jsonobj = new JSONObject();

		jsonobj.put("status", status);

		response.getWriter().print(jsonobj);
		
		
	}catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : NewCancelStudentTC Ending");

	return null;
}


public ActionForward schoolLocation(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : schoolLocation Starting");
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
									LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LOCATION_ADDRESS);

		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		LocationBD obj = new LocationBD();
		List<LocationVO> list = new ArrayList<LocationVO>();

		String SearchName = request.getParameter("searchText");  
		String status = request.getParameter("status");
		 
	    list = obj.getLocationList(dbdetails);
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getSchoolList(dbdetails);
		request.setAttribute("locationList", locationList);
		
		//.out.println(locationList.get(0).getLocationId());
		//.out.println(locationList.get(0).getLocationName());
		
		LocationDao dao=new LocationDAOImpl();
		
		String locationstatus= dao.getLocationCount(dbdetails);
		
		request.setAttribute("locationDetailsList", list);
		request.setAttribute("status",status);
		/*if(list.size() >0){
			request.setAttribute("schlstatus",list.get(0).getCuststatus()); 
		}
		else{
			request.setAttribute("schlstatus","inactive"); 
		}*/
		request.setAttribute("schlstatus",locationstatus);


	} catch (Exception e) {
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : schoolLocation Ending");
	return mapping.findForward(MessageConstants.SCHOOLADDRESS);
}
public ActionForward analyticalperformance(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction    : analyticalperformance Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_ANALYTICAL_PERFORMANCE);
		
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");	
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
	String accyear = request.getSession(false).getAttribute("current_academicYear").toString();
	
	if(location!=null && location.equalsIgnoreCase("all")){
		location = "%%";
	}
	
	if(accyear == null || accyear == "" || accyear.equalsIgnoreCase("")) {
		accyear = HelperClass.getCurrentYearID(custdetails);
	}
	
	ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
	request.setAttribute("locationList", locationList);
	
	request.setAttribute("hloc", location);
	
	ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
	request.setAttribute("AccYearList", accYearList);
	
/*	List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(custdetails);
	request.setAttribute("classlist", classlist);*/
	
	StudentRegistrationVo vo=new StudentRegistrationVo();
	vo.setAcademicYear(accyear);
	vo.setDbdetails(custdetails);
	vo.setLocation(location);
	
/*	list = new StudentRegistrationDelegate().getStudentListByAnalyticalPerformance(vo);
	request.setAttribute("studentList", list);*/
	
	/*list = new StudentRegistrationDaoImpl().getStudentAnalyticalPerformanceTable(vo);
	request.setAttribute("studentAnalyticPerformanceList", list);*/
	
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	} 
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : analyticalperformance Ending");
	return mapping.findForward(MessageConstants.STUDENT_ANALYTICAL_PERFORMANCE);

}

public ActionForward addSchoolLocation(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : addSchoolLocation Starting");
	try {
		String arg = "Add New Branch";
		 
		request.setAttribute("Location", arg);

		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_LOCATION_ADDRESS);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getSchoolList(custdetails);
		request.setAttribute("locationList", locationList);

		request.setAttribute("operation","Add");
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : addSchoolLocation Ending");

	return mapping.findForward(MessageConstants.ADDSCHOOLADDRESS);

}

public ActionForward getStudentAnalyticalPerformanceTable(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction    : getStudentAnalyticalPerformanceTable Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_ANALYTICAL_PERFORMANCE);
		
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");	
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
	String accyear = request.getSession(false).getAttribute("current_academicYear").toString();
	
	if(accyear == null || accyear == "" || accyear.equalsIgnoreCase("")) {
		accyear = HelperClass.getCurrentYearID(custdetails);
	}
	
	/*ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
	request.setAttribute("locationList", locationList);
	
	ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
	request.setAttribute("AccYearList", accYearList);
	
	List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(custdetails);
	request.setAttribute("classlist", classlist);*/
	
	StudentRegistrationVo vo=new StudentRegistrationVo();
	vo.setAcademicYear(accyear);
	vo.setDbdetails(custdetails);
	
	list = new StudentRegistrationDaoImpl().getStudentAnalyticalPerformanceTable(vo);
	
	JSONObject jsonobj = new JSONObject();
	jsonobj.put("studentAnalyticPerformanceList", list);
	response.getWriter().print(jsonobj);
	
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	} 
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getStudentAnalyticalPerformanceTable Ending");
	return null;
}

public ActionForward getStudentListByAnalyticalPerformance(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction    : studentSearch Starting");
	
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_ANALYTICAL_PERFORMANCE);
		
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");	
	List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
	
	String locationid = request.getParameter("location");
	String accyear = request.getParameter("accyear");
	String classname = request.getParameter("classId");
	String sectionid = request.getParameter("sectionid");
	String housename = request.getParameter("housename");
	
	if(locationid == "all" ||locationid.equalsIgnoreCase("all")){
		locationid = "%%";
	}
	if(accyear == null || accyear == "" || accyear.equalsIgnoreCase("")) {
		accyear = HelperClass.getCurrentYearID(custdetails);
	}
	if(classname.equalsIgnoreCase("all") || classname=="" || classname==null){
		classname = "%%";
	}
	if(sectionid.equalsIgnoreCase("all") || sectionid=="" || sectionid==null){
		sectionid = "%%";
	}
	
	if(housename == "" || housename==null || housename.equalsIgnoreCase("all")){
		housename = "%%";
	}
	
	StudentRegistrationVo vo=new StudentRegistrationVo();
	vo.setAcademicYear(accyear);
	vo.setLocation(locationid);
	vo.setClassname(classname);
	vo.setSection_id(sectionid);
	vo.setHouseId(housename);
	vo.setDbdetails(custdetails);
	
	list = new StudentRegistrationDelegate().getStudentFilteredListByAnalyticalPerformance(vo);

	JSONObject jsonobj = new JSONObject();
	jsonobj.put("studentList", list);
	response.getWriter().print(jsonobj);
	
	
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : studentSearch Ending");

	return null;
}

public ActionForward getTeacherListForTimeTable(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction    : getTeacherList Starting");
	

		
	List<TeacherVo> list = new ArrayList<TeacherVo>();

	
	try {
		
		String subid=request.getParameter("subid"); 
		String locationId=request.getParameter("locationId");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		list=HelperClass.getAllTeacherListForTimetable(subid,custdetails);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("teacherList", list);
		response.getWriter().print(jsonobj);
		

	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getTeacherList Ending");

	return null;
}


public ActionForward getAllSchool(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getAllSchool Starting");
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

	try {
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("schoolName", HelperClass.getAllLocation(custdetails));
		response.getWriter().print(jsonobj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}


	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getAllSchool Ending");

	return null;
}
public ActionForward getClassList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getAllSchool Starting");

	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	try {
		String schoolLocation=request.getParameter("location");
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("classList", HelperClass.getAllClassList(schoolLocation,custdetails));
		response.getWriter().print(jsonobj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}


	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getAllSchool Ending");

	return null;
}
public ActionForward getTermList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getAllSchool Starting");

	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	try {
		String schoolLocation=request.getParameter("location");
		String accyear=request.getParameter("accyear");
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("TermList", HelperClass.getTermList(schoolLocation, accyear,custdetails));
		response.getWriter().print(jsonobj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}


	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getAllSchool Ending");

	return null;
}
public ActionForward getAccyearList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getAllSchool Starting");

	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	try {

		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Accyear", HelperClass.getAllAcademicYear(custdetails));
		response.getWriter().print(jsonobj);
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);

	}


	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : getAllSchool Ending");

	return null;
}

public ActionForward fineConfigurationByAccyear(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : fineConfigurationByAccyear Starting");
	List<feeReportVO> fineList=new ArrayList<feeReportVO>();
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	String current_location=(String) request.getSession(false).getAttribute("current_schoolLocation");
	try {
	
	String accyear=request.getParameter("accyear");
	
	JSONObject obj=new JSONObject();
	
	obj.put("schoolName", HelperClass.getAllLocation(custdetails));
	fineList=new FeeMasterDAOIMPL().getFineConfiguration(accyear,current_location,custdetails);
	obj.put("fineList", fineList);
	obj.put("AccyearId", HelperClass.getAllAcademicYear(custdetails));	
	response.getWriter().print(obj);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : fineConfigurationByAccyear Ending");
	return null;
	
}

}
 
