package com.centris.campus.actions;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.joda.time.LocalDate;
import org.json.JSONObject;
import com.centris.campus.delegate.AddDesignationBD;
import com.centris.campus.delegate.DepartmentMasterBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StaffReleivingReportBD;
import com.centris.campus.pojo.RelievingOrderPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.DepartmentMasterVO;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.UserDetailVO;


public class StaffReleivingOrderReportAction extends DispatchAction {
	
	static ResourceBundle res = ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	private static final Logger logger = Logger.getLogger(StaffReleivingOrderReportAction.class);
	
	
	public ActionForward staffReleivingOrderReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderReportAction : staffReleivingOrderReport Starting");
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_RELEIVINGORDER);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			UserDetailVO vo = new UserDetailVO();
			
			List<UserDetailVO> userList =  new StaffReleivingReportBD().getUsersList(userLoggingsVo);
			
			request.setAttribute("userList", userList);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD().getdepartmentlist(userLoggingsVo); 
		     request.setAttribute("deptlist", DEPARTMENTLIST);	
            ArrayList<AddDesignationVO> designattion = new AddDesignationBD().getdesignationList(userLoggingsVo);
            request.setAttribute("designattionlist", designattion);	
			
			/*vo.setTeacherName(reform.getTeacherlist());
			vo.setTodate(reform.getResignationdate());
			vo.setDescription(reform.getDescription());
			
			
			
			String userID = HelperClass.getCurrentUserID(request);
			List<ReportMenuVo> list = new ArrayList<ReportMenuVo>();
		

			ArrayList<TimeTableVo> TeacherTimeTableList =  new TimeTableBD().getClassTimeTableList( accyearid,viewBy);	
			
			
			list=new ReleivingOrderBD().getTeacherListBD();
			
			request.setAttribute("teacherList", list);
			
			request.setAttribute("selecteddetails", vo);*/
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderReportAction : staffReleivingOrderReport Ending");

		
		return  mapping.findForward(MessageConstants.releivingOrdeReport);
	}
	
	
	
	
	public ActionForward getTeachernameAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderReportAction : getTeachernameAction Starting");
		
		
	try {
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String teachertype = "";
		teachertype = request.getParameter("teachertype");
		String usertype = request.getParameter("usertype");
		
		AllTeacherDetailsVo vo = new AllTeacherDetailsVo();
		vo.setTeacherType(teachertype);
		vo.setUsertype(usertype);
	
		System.out.println("teachertype:::"+teachertype);
		
		
		
		if(teachertype.equalsIgnoreCase("teaching")){
			
			
			List<AllTeacherDetailsVo> teachinglist = new StaffReleivingReportBD().getTeachingListBD(vo,userLoggingsVo);
			
			
			 JSONObject object=new JSONObject();
			 
			 object.put("teachinglist", teachinglist);
			 
			 response.getWriter().print(object);	
		
		}
		else{
			
		
			List<AllTeacherDetailsVo> nonteachinglist = new StaffReleivingReportBD().getNonTeachingListBD(vo,userLoggingsVo);
			
			
			
			 JSONObject object=new JSONObject();
			 
			 object.put("nonteachinglist", nonteachinglist);
			 
			 response.getWriter().print(object);
			
		}
		
		
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderReportAction : getTeachernameAction Ending");

		
		return null;
	}
	
	
	public ActionForward staffReleivingPDFReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderReportAction : staffReleivingPDFReport Starting");
		
		List<RelievingOrderPojo> teacherdetails = new ArrayList<RelievingOrderPojo>();
			
		
		try {
            
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			ServletContext servletContext = request.getServletContext();
			LocalDate localDate = new LocalDate();
			String user = HelperClass.getCurrentUserID(request);
			String teachercode[] = request.getParameter("teacherId").split(",");
			String releivedate = request.getParameter("releivedate");
			String locname=request.getParameter("locname");
			String locId=request.getParameter("locId");
			RelievingOrderPojo pojo = new RelievingOrderPojo();
			pojo.setReleivingdate(releivedate);
		
			System.out.println("dddddddddddd"+localDate);
			System.out.println("9999999999"+releivedate);
				teacherdetails =  new StaffReleivingReportBD().staffReleivingPDFReport(teachercode,pojo,userLoggingsVo);	
			
			LocationVO custSchoolInfo=HelperClass.getCustSchoolInfo(userLoggingsVo,locId);
			String PropfilePath=null;
 			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
 				 PropfilePath = getServlet().getServletContext().getRealPath("")+ "\\images\\" + ImageName.trim();
 			}
 			else{
 				 PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
 			}
 		
 			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();
			Map mapdata = new HashMap();
			mapdata.put("currentdate", pojo.getReleivingdate());
			mapdata.put("locname",custSchoolInfo.getSchname());
			mapdata.put("custSchoolAddres", custSchoolInfo.getAddress());
			mapdata.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			mapdata.put("custSchoollogo", schoollogo);
			mapdata.put("custSchoolboardlogo", PropfilePath);
			mapdata.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			mapdata.put("custSchoolno", custSchoolInfo.getSchoolcode());
			mapdata.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			mapdata.put("custSchoolEmail", custSchoolInfo.getEmailId());
			mapdata.put("branch", locname);
			
			String filepath = request.getRealPath("Reports/RelievingOrder.jrxml");

			JasperDesign design = JRXmlLoader.load(filepath);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					teacherdetails);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,mapdata, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition",
					"outline; filename=\"" + "Relieving Order.pdf\"");

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
				+ " Control in StaffReleivingOrderReportAction : staffReleivingPDFReport Ending");

		
		return null;
	}
	
	
	
	public ActionForward SearchRelievingOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())

				+ " Control in StaffReleivingOrderReportAction : SearchRelievingOrder Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_EXAM);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String locId=request.getParameter("locationId");
			String deptId=request.getParameter("depName");
			String desgId=request.getParameter("designameid");
			String techType=request.getParameter("teachtype");
			String relievingdate=request.getParameter("relievedate");		
			RelievingOrderPojo pojo= new RelievingOrderPojo();
			pojo.setLocId(locId);
			pojo.setDeptId(deptId);
			pojo.setDesignation(desgId);
			pojo.setTeachertype(techType);
			pojo.setReleivingdate(relievingdate);
		 
			if(pojo.getLocId().equalsIgnoreCase("all")){
				pojo.setLocId("%%");
			}
			if(pojo.getDeptId().equalsIgnoreCase("all")){
				pojo.setDeptId("%%");
			}
			if(pojo.getDesignation().equalsIgnoreCase("all")){
				pojo.setDesignation("%%");
			}
		    if(pojo.getTeachertype().equalsIgnoreCase("all")){
		    	pojo.setTeachertype("%%");
		    }
		    
			List<RelievingOrderPojo> list = new ArrayList<RelievingOrderPojo>();

			list =  new StaffReleivingReportBD().SearchRelievingOrder(pojo,custdetails);
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("relievinglist", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderReportAction : SearchRelievingOrder Ending");

		return null;

	}
	
}
