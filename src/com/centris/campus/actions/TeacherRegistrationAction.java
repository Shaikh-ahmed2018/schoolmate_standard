package com.centris.campus.actions;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.json.JSONArray;
import org.json.JSONObject;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.daoImpl.TeacherDaoImpl;
import com.centris.campus.delegate.AddDesignationBD;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.DepartmentMasterBD;
import com.centris.campus.delegate.LeaveBankDelegate;
import com.centris.campus.delegate.ReligionCasteCasteCategoryBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.TeacherRegistrationBD;
import com.centris.campus.delegate.UserRolePermissionBD;
import com.centris.campus.forms.TeacherForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.TeacherServiceImpl;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.DepartmentMasterVO;
import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TeacherMappingClassesVo;
import com.centris.campus.vo.ViewallSubjectsVo;

public class TeacherRegistrationAction extends DispatchAction {

	private static final Logger logger = Logger
			.getLogger(TeacherRegistrationAction.class);
	FormFile imagePath;
	String profilepath;
	FileOutputStream outputStream = null;
	FormFile formFile1;
	String imagepath;
	FormFile idproof;
	String idproofpath;
	FileOutputStream outputStream1 = null;
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	public ActionForward getTeachers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getTeachers Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFREGISTRATION);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String academicYear = HelperClass.getCurrentYearID(userLoggingsVo);
			
			request.setAttribute("RolePermission",new UserRolePermissionBD().getUserRolePermission(userLoggingsVo));
			request.setAttribute("reportingToList", new TeacherRegistrationBD().reportingToList(userLoggingsVo));
			request.setAttribute("StudentAdmissionNumber", new TeacherRegistrationBD().StudentAdmissionNumber(academicYear,userLoggingsVo));
			request.setAttribute("successmessagediv", "");
			/*request.setAttribute("StudentAdmissionNumber", "");*/

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getTeachers Ending");
		return mapping.findForward(MessageConstants.ADD_STAFF);
	}

	public ActionForward searchStaffDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : searchStaffDetails Starting");
		try {
			System.out.println("search details acion");
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFREGISTRATION);
		
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			AllTeacherDetailsVo obj = new AllTeacherDetailsVo();
			obj.setLocid(request.getParameter("locationId"));
			obj.setDeptid(request.getParameter("depName"));
			obj.setDesgId(request.getParameter("designameid"));
			obj.setStatus(request.getParameter("status"));
			obj.setSearchTerm(request.getParameter("searchname".trim()));
		
			
			if(obj.getLocid().equalsIgnoreCase("all")){
				obj.setLocid("%%");
			}		
			if(obj.getDeptid().equalsIgnoreCase("all")){
				obj.setDeptid("%%");
			}
			if(obj.getDesgId().equalsIgnoreCase("all")){
				obj.setDesgId("%%");
			}
			
			ArrayList<AllTeacherDetailsVo> allTeacherDetailsList = new ArrayList<AllTeacherDetailsVo>();
			
			allTeacherDetailsList = new TeacherRegistrationBD().searchStaffDetails(obj,userLoggingsVo);
			
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("allTeacherDetailsList", allTeacherDetailsList);
			response.getWriter().print(jsonobject);
			
			
			/*request.setAttribute("searchTerm",request.getParameter("searchname".trim()));*/

			/*System.out.println("list size in action class "+ allTeacherDetailsList.size());*/
		/*	request.setAttribute("allTeacherDetailsList", allTeacherDetailsList);*/

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : searchStaffDetails Ending");

		return null;
	}

	public ActionForward deleteStaffDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : deleteStaffDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			String teachercode[] = request.getParameter("teachercode").split(",");
			String button=request.getParameter("button");
            TeacherForm obj=new TeacherForm();
            obj.setLog_audit_session(log_audit_session);
            obj.setStatus(request.getParameter("status"));
            obj.setReason(request.getParameter("reason"));
			TeacherRegistrationBD allTeacherDetailsBD = new TeacherRegistrationBD();
			boolean status = allTeacherDetailsBD.deleteStaffDetails(teachercode,obj,button,userLoggingsVo);
			
			System.out.println(status);
			
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("status", status);
			response.getWriter().print(jsonobject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : deleteStaffDetails Ending");

		return null;
	}

	public ActionForward getSubjects(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getSubjects Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String classId=request.getParameter("classId");
			String locationId=request.getParameter("locationId");
			
			List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();
			subjectlist = new TeacherServiceImpl().getSubjects(classId,locationId,userLoggingsVo);

			JSONObject jsonobject = new JSONObject();
			jsonobject.accumulate("SubjectList", subjectlist);
			response.getWriter().print(jsonobject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getSubjects Ending");
		return null;
	}

	public ActionForward getDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getDepartment Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ArrayList<DepartmentMasterVO> DEPARTMENTLIST = new DepartmentMasterBD().getDepartmentDetails(custdetails);

			JSONObject object = new JSONObject();
			object.put("DEPARTMENTLIST", DEPARTMENTLIST);

			response.getWriter().print(object);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getDepartment Ending");

		return null;
	}

	public ActionForward getDesignation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getDesignation Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			AddDesignationVO vo = new AddDesignationVO();
			ArrayList<AddDesignationVO> DESIGNATIONLIST = new AddDesignationBD().DesignationDetails(vo, userLoggingsVo);

			JSONObject object = new JSONObject();
			object.put("DESIGNATIONLIST", DESIGNATIONLIST);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getDesignation Ending");
		return null;
	}

	public ActionForward checkUsername(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: checkUsername Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String username = request.getParameter("username");
			String teacherId = request.getParameter("teacherId");

			if (teacherId == null) {
				teacherId = "";
			}

			boolean status = new TeacherServiceImpl().checkUsername(username,teacherId,userLoggingsVo);

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
				+ " Control in TeacherRegistrationAction : checkUsername Ending");
		return null;
	}
	
	public ActionForward checkRegistrationNo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: checkRegistrationNo Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String regidtrionno = request.getParameter("registrtno");
			String teacherId = request.getParameter("teacherId");
			String locId = request.getParameter("locId");

			if (teacherId == null) {
				teacherId = "";
			}

			String status = new TeacherDaoImpl().checkRegistrationNo(regidtrionno,teacherId,locId,userLoggingsVo);

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
				+ " Control in TeacherRegistrationAction : checkRegistrationNo Ending");
		return null;
	}

	public ActionForward checkMail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: checkMail Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String mailid = request.getParameter("mailid");
			String teacherId = request.getParameter("teacherId");

			if (teacherId == null) {
				teacherId = "";
			}

			boolean status = new TeacherServiceImpl().checkMailId(mailid,
					teacherId,userLoggingsVo);

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
				+ " Control in TeacherRegistrationAction : checkMail Ending");
		return null;
	}

	public ActionForward teacherregister(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : teacherregister Starting");

		String teacherID = null;

		String wordFileFormat = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		String pdfFileFormat = "application/pdf";

		TeacherForm tform = (TeacherForm) form;
		String TeacherImagePath=null;
		

		
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		teacherID = IDGenerator.getPrimaryKeyID("campus_teachers",userLoggingsVo);

		String user = HelperClass.getCurrentUserID(request);
		tform.setCreatedBy(user);
		
		
		try {
			/*UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");*/
			
			
			
			if (tform.getTfastname() != null) {

				try {
					
					System.out.println("file from form :: "+tform.getTeacherImage());
					
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIMAGES";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					
					if (tform.getTeacherImage() != null) {
						
						String extension=null;
						
						 int i = tform.getTeacherImage().getFileName().lastIndexOf('.');
						 if (i >= 0) {
						     extension = tform.getTeacherImage().getFileName().substring(i+1);
						 }
						 
						
						TeacherImagePath= "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIMAGES/" + tform.getTfastname() + "_"
							+ teacherID + "."+extension;
						
						String filePath = request.getServletContext().getRealPath("/")+TeacherImagePath;
						if (tform.getTeacherImage().getFileSize() > 0) {
							byte[] btDataFile = tform.getTeacherImage().getFileData();
							File of = new File(filePath);
							FileOutputStream osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();
						} else {
							TeacherImagePath = "";
						}

					}
					
					tform.setImagestring(TeacherImagePath);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();

				} finally {
					if (outputStream1 != null) {
						outputStream1.close();
					}
				}
				
				try {
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERRESUMES";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					
					if (tform.getTeacherprofile() != null) {
						
							String extension=null;
						
						 int i = tform.getTeacherprofile().getFileName().lastIndexOf('.');
						 if (i >= 0) {
						     extension = tform.getTeacherprofile().getFileName().substring(i+1);
						 }
						
						String profile_path = "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERRESUMES/Resume_"+ teacherID
								+ "." + extension;
						

						String filePath = request.getServletContext().getRealPath("/")+profile_path;
						if (tform.getTeacherprofile().getFileSize() > 0) {
							byte[] btDataFile = tform.getTeacherprofile().getFileData();
							File of = new File(filePath);
							FileOutputStream osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();
						} else {
							profile_path = "";
						}
					
						tform.setProfilestring(profile_path);
						
						
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}

				try {
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}

					if (tform.getIdproof() != null) {
						
					String extension=null;
					
					 int i = tform.getIdproof().getFileName().lastIndexOf('.');
					 if (i >= 0) {
					     extension = tform.getIdproof().getFileName().substring(i+1);
					 }
					
					String idProof_path = "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF/IdProof_" + teacherID
							+ "." + extension;
					

					String filePath = request.getServletContext().getRealPath("/")+idProof_path;
					if (tform.getTeacherprofile().getFileSize() > 0) {
						byte[] btDataFile = tform.getIdproof().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {
						idProof_path = "";
					}
					
					tform.setIdproofstring(idProof_path);
						
					}
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
				
				//additionla attachements
				
				try{
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}

					System.out.println("id path ::: "+tform.getFilaattachment1());
					
					String extension=null;
				
					int i = tform.getFilaattachment1().getFileName().lastIndexOf('.');
					if (i >= 0) {
				     extension = tform.getFilaattachment1().getFileName().substring(i+1);
					}
				
					String doc1_path = "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF/Doc1__" + teacherID
						+ "." + extension;
				

					String filePath =request.getServletContext().getRealPath("/")+doc1_path;
					if (tform.getFilaattachment1().getFileSize() > 0) {
						byte[] btDataFile =tform.getFilaattachment1().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {
						doc1_path = "";
					}
			
					tform.setAttachment1string(doc1_path);
				
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
				
				
				try{
                    
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					

					String extension=null;
				
					int i = tform.getFilaattachment2().getFileName().lastIndexOf('.');
					if (i >= 0) {
				     extension = tform.getFilaattachment2().getFileName().substring(i+1);
					}
				
					String doc1_path = "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF/Doc2__"+ teacherID
						+ "." + extension;
				

					String filePath =request.getServletContext().getRealPath("/")+doc1_path;
					if (tform.getFilaattachment2().getFileSize() > 0) {
						byte[] btDataFile =tform.getFilaattachment2().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {
						doc1_path = "";
					}
			
					tform.setAttachment2string(doc1_path);
				
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
				
				try{
                    
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					
					String extension=null;
				
					int i = tform.getFilaattachment3().getFileName().lastIndexOf('.');
					if (i >= 0) {
				     extension = tform.getFilaattachment3().getFileName().substring(i+1);
					}
				
					String doc1_path = "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF/Doc3__"+ teacherID
						+ "." + extension;
				

					String filePath =request.getServletContext().getRealPath("/")+doc1_path;
					if (tform.getFilaattachment3().getFileSize() > 0) {
						byte[] btDataFile =tform.getFilaattachment3().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {
						doc1_path = "";
					}
			
					tform.setAttachment3string(doc1_path);
				
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
				
				
				try{
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					
					
					String extension=null;
				
					int i = tform.getFilaattachment4().getFileName().lastIndexOf('.');
					if (i >= 0) {
				     extension = tform.getFilaattachment4().getFileName().substring(i+1);
					}
				
					String doc1_path = "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF/Doc4__"+ teacherID
						+ "." + extension;
				

					String filePath =request.getServletContext().getRealPath("/")+doc1_path;
					if (tform.getFilaattachment4().getFileSize() > 0) {
						byte[] btDataFile =tform.getFilaattachment4().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {
						doc1_path = "";
					}
			
					tform.setAttachment4string(doc1_path);
				
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
				
				
				try{
					
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}

					String extension=null;
				
					int i = tform.getFilaattachment5().getFileName().lastIndexOf('.');
					if (i >= 0) {
				     extension = tform.getFilaattachment5().getFileName().substring(i+1);
					}
				
					String doc1_path ="SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF/Doc5__"+ teacherID
						+ "." + extension;
				

					String filePath =request.getServletContext().getRealPath("/")+doc1_path;
					if (tform.getFilaattachment5().getFileSize() > 0) {
						byte[] btDataFile =tform.getFilaattachment5().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {
						doc1_path = "";
					}
			
					tform.setAttachment5string(doc1_path);
				
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}

				
				
				String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
				String academicYear = (String)request.getSession(false).getAttribute("current_academicYear");
				tform.setAcademicYear(academicYear);
				tform.setLog_audit_session(log_audit_session);
				
				
				boolean result = new TeacherRegistrationBD().teacherregister(tform,userLoggingsVo);
			
				System.out.println("result::::"+result);
				
				String status=null;
				
				if (result) {
					//request.setAttribute("successmessagediv","Adding Record Progressing...");
					status="success";
				} else if (result == false) {
					//request.setAttribute("errormessagediv","Staff already Exists");
					status="duplicate";
				} else {
					//request.setAttribute("errormessagediv","Staff registration not Completed");
				}

				JSONObject object=new JSONObject();
				object.put("status", status);
				response.getWriter().print(object);
				
			}

			request.setAttribute("RolePermission",new UserRolePermissionBD().getUserRolePermission(userLoggingsVo));
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : teacherregister Ending");
		
		//return mapping.findForward(MessageConstants.ADD_STAFF);
		return null;
	}

	public ActionForward getTeacherDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getTeacherDetails Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFREGISTRATION);

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String academicYear = HelperClass.getCurrentYearID(userLoggingsVo);
			
			String teacherid = request.getParameter("teachercode");
			
			
			TeacherRegistrationPojo obj = new TeacherRegistrationPojo();
			obj.setTeacherId(teacherid);
			
      
			obj = new TeacherRegistrationBD().getTeacherDetails(obj,userLoggingsVo);

			request.setAttribute("RolePermission",new UserRolePermissionBD().getUserRolePermission(userLoggingsVo));
			request.setAttribute("reportingToList", new TeacherRegistrationBD().reportingToList(userLoggingsVo));
			request.setAttribute("StudentAdmissionNumber", new TeacherRegistrationBD().StudentAdmissionNumber(academicYear,userLoggingsVo));

			request.setAttribute("teacherdetails", obj);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historydepName", request.getParameter("historydepName"));
			request.setAttribute("historydesigId", request.getParameter("historydesigId"));
			request.setAttribute("historysearchvalue", request.getParameter("historysearchvalue")); 
			request.setAttribute("historystatus", request.getParameter("historystatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getTeacherDetails Ending");
		return mapping.findForward(MessageConstants.UPDATE_STAFF);
	}

	public ActionForward teacherUpdation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: teacherUpdation Starting");
		java.util.Date today = new java.util.Date();

		final String OLD_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy HH:mm:ss");
		Date dbdate = null;
		Date date = null;
		String result = null;
		
		System.out.println("update teacher ");
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String user = HelperClass.getCurrentUserID(request);
		
		String TeacherImagePath=null;
		

		TeacherForm tform = (TeacherForm) form;
		
		  Map dynformValues = tform.getValues();
	        int count = dynformValues.size()/2;
	         
	        System.out.println("Map Size: " + dynformValues.size());
	        
	        for(int i =0; i<count ; i++){
	        	
	            String name = (String)dynformValues.get("name"+i);
	            String value = (String)dynformValues.get("value"+i);
	            System.out.println("Name:" + name + " Value:" + value);
	        }
		
		try {

			if (tform.getTfastname() != null) {

			try {
					date = sdf.parse(sdf.format(today));
					dbdate = new java.sql.Date(date.getTime());
					sdf.applyPattern(OLD_FORMAT);

				} catch (ParseException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();

				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();

				}

				TeacherRegistrationPojo tecpojo = new TeacherRegistrationPojo();
                
				String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
				imagePath = tform.getTeacherprofile();
				String teacherID = tform.getTeacherid();

				
				
				try {
					
					System.out.println("profile path ::: "+tform.getTeacherprofile());
					System.out.println("default image ::: "+tform.getDefaultprofile());

					if (tform.getTeacherprofile() != null) {
						
						String savePath = request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERRESUMES";
						
						File file = new File(savePath);
						if (!file.exists()) {
							file.mkdirs();
						}
						
					String extension=null;
						
						 int i = tform.getTeacherprofile().getFileName().lastIndexOf('.');
						 if (i >= 0) {
						     extension = tform.getTeacherprofile().getFileName().substring(i+1);
						 }
						
						String profile_path ="SCHOOLINFO/"+userLoggingsVo.getDomain()+ "/TEACHERRESUMES/Resume_"+ teacherID+ "." + extension;
						
						String filePath = request.getServletContext().getRealPath("/")+profile_path;
						
						if (tform.getTeacherprofile().getFileSize() > 0) {
							byte[] btDataFile = tform.getTeacherprofile().getFileData();
							File of = new File(filePath);
							FileOutputStream osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();
						} else {
							profile_path = "";
						}
					
						tecpojo.setProfilepath(profile_path);
						
						
					}else{
					
						tecpojo.setProfilepath(tform.getDefaultprofile());
					}
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}

				try {

					System.out.println("id path ::: "+tform.getIdproof());
					System.out.println("default id image ::: "+tform.getDefaultidproof());
					
					if (tform.getIdproof() != null) {
						
						String savePath = request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF";
						
						File file = new File(savePath);
						if (!file.exists()) {
							file.mkdirs();
						}
					String extension=null;
					
					 int i = tform.getIdproof().getFileName().lastIndexOf('.');
					 if (i >= 0) {
					     extension = tform.getIdproof().getFileName().substring(i+1);
					 }
					
					String idProof_path = "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF/IdProof_" + teacherID
							+ "." + extension;
					

					String filePath = request.getServletContext().getRealPath("/")+idProof_path;
					if (tform.getTeacherprofile().getFileSize() > 0) {
						byte[] btDataFile = tform.getIdproof().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {
						idProof_path = tform.getDefaultidproof();
					}
					
					tecpojo.setIdproof(idProof_path);
						
					}else{
						tecpojo.setIdproof(tform.getDefaultidproof());
					}
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}


				formFile1 = tform.getTeacherImage();
				
				
				if (formFile1 != null) {
					
					if (formFile1.getFileSize() != 0) {
						try {
							String savePath = request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIMAGES";
							File file = new File(savePath);
							if (!file.exists()) {
								file.mkdirs();
							}
							TeacherImagePath="SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIMAGES/"+ tform.getTfastname() + "_"
									+ teacherID + ".jpg";
								
								String filePath = request.getServletContext().getRealPath("/")+TeacherImagePath;
								
								System.out.println("File size :: "+tform.getTeacherImage().getFileSize());
								
								if (tform.getTeacherImage().getFileSize() > 0) {
									byte[] btDataFile = tform.getTeacherImage().getFileData();
									File of = new File(filePath);
									FileOutputStream osf = new FileOutputStream(of);
									osf.write(btDataFile);
									osf.flush();
									
									tecpojo.setImgpath(TeacherImagePath);
									
									System.out.println("TeacherImagePath ::: "+TeacherImagePath);
									
								}

						} catch (Exception e) {
							logger.error(e.getMessage(), e);
							e.printStackTrace();
						} finally {
							if (outputStream1 != null) {
								outputStream1.close();

							}
						}
					} else {
						tecpojo.setImgpath(tform.getDefaultimage());
					}
				} else {
					tecpojo.setImgpath(tform.getDefaultimage());
				}
				
				try{
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					
						System.out.println("id path ::: "+tform.getFilaattachment1());
						
						String extension=null;
					
						int i = tform.getFilaattachment1().getFileName().lastIndexOf('.');
						if (i >= 0) {
					     extension = tform.getFilaattachment1().getFileName().substring(i+1);
						}
					
						String doc1_path = "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF/Doc1__" + teacherID
							+ "." + extension;
					

						String filePath = request.getServletContext().getRealPath("/")+doc1_path;
						if (tform.getFilaattachment1().getFileSize() > 0) {
							byte[] btDataFile =tform.getFilaattachment1().getFileData();
							File of = new File(filePath);
							FileOutputStream osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();
						} else {
							doc1_path = "";
						}
				
						tform.setAttachment1string(doc1_path);
					
					
				
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
				
				
				try{
                    
					String savePath = request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}

					String extension=null;
				
					int i = tform.getFilaattachment2().getFileName().lastIndexOf('.');
					if (i >= 0) {
				     extension = tform.getFilaattachment2().getFileName().substring(i+1);
					}
				
					String doc1_path = "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF/Doc2__" + teacherID
						+ "." + extension;
				

					String filePath =request.getServletContext().getRealPath("/")+doc1_path;
					if (tform.getFilaattachment2().getFileSize() > 0) {
						byte[] btDataFile =tform.getFilaattachment2().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {
						doc1_path = "";
					}
			
					tform.setAttachment2string(doc1_path);
				
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
				
				try{
                       
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					
					String extension=null;
				
					int i = tform.getFilaattachment3().getFileName().lastIndexOf('.');
					if (i >= 0) {
				     extension = tform.getFilaattachment3().getFileName().substring(i+1);
					}
				
					String doc1_path = "SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF/Doc3__" + teacherID
						+ "." + extension;
				

					String filePath =request.getServletContext().getRealPath("/")+doc1_path;
					if (tform.getFilaattachment3().getFileSize() > 0) {
						byte[] btDataFile =tform.getFilaattachment3().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {
						doc1_path = "";
					}
			
					tform.setAttachment3string(doc1_path);
				
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
				
				
				try{
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}

					String extension=null;
				
					int i = tform.getFilaattachment4().getFileName().lastIndexOf('.');
					if (i >= 0) {
				     extension = tform.getFilaattachment4().getFileName().substring(i+1);
					}
				
					String doc1_path ="SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF/Doc4__"+ teacherID
						+ "." + extension;
				

					String filePath =request.getServletContext().getRealPath("/")+doc1_path;
					if (tform.getFilaattachment4().getFileSize() > 0) {
						byte[] btDataFile =tform.getFilaattachment4().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {
						doc1_path = "";
					}
			
					tform.setAttachment4string(doc1_path);
				
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
				
				
				try{
					String savePath =request.getServletContext().getRealPath("/")+"SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					
					String extension=null;
				
					int i = tform.getFilaattachment5().getFileName().lastIndexOf('.');
					if (i >= 0) {
				     extension = tform.getFilaattachment5().getFileName().substring(i+1);
					}
				
					String doc1_path ="SCHOOLINFO/"+userLoggingsVo.getDomain()+"/TEACHERIDPROOF/Doc5__"+ teacherID
						+ "." + extension;
				

					String filePath = request.getServletContext().getRealPath("/")+doc1_path;
					if (tform.getFilaattachment5().getFileSize() > 0) {
						byte[] btDataFile =tform.getFilaattachment5().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {
						doc1_path = "";
					}
			
					tform.setAttachment5string(doc1_path);
				
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}

				
				
                System.out.println("8888888888888"+tform.getPayrollid());
                  
				System.out.println("teacher id in action " +tform.getTeacherid());
				tecpojo.setTeacherId(tform.getTeacherid());
				tecpojo.setTfastname(tform.getTfastname());
				tecpojo.setTlastname(tform.getTlastname());
				tecpojo.setUsername(tform.getUsername());
				tecpojo.setTqualification(tform.getTqualification());
				tecpojo.setTeachermobno(tform.getTeachermobno());
				tecpojo.setTeacheremail(tform.getTeacheremail());
				tecpojo.setDateofbirth(tform.getDateofbirth());
				tecpojo.setJoiningdate(tform.getJoiningdate());
				/*tecpojo.setTeacherpermanentadd(tform.getTeacherpermanentadd());*/
				tecpojo.setDesignation(tform.getDesignation());
				tecpojo.setModifiedby(HelperClass.getCurrentUserID(request));
				tecpojo.setTeachingtype(tform.getTeachingtype());
				tecpojo.setDept(tform.getDepartment());
				/* tecpojo.setRole(tform.getRole()); */
				tecpojo.setGender(tform.getGender());
				tecpojo.setPresentadd(tform.getPresentadd());
				tecpojo.setPermanentadd(tform.getPermanentadd());
				tecpojo.setFathername(tform.getFathername());
				tecpojo.setMothername(tform.getMothername());
				tecpojo.setBlood(tform.getBlood());
				tecpojo.setCreatedby(user);
				tecpojo.setRole(tform.getRoleId());
				tecpojo.setReportingTo(tform.getReportingTo());
				tecpojo.setAccountNumber(tform.getAccountNumber());
				tecpojo.setBankName(tform.getBankName());
				tecpojo.setPanNumber(tform.getPanNumber());
				tecpojo.setDocument1(tform.getAttachment1string());
				tecpojo.setDocument2(tform.getAttachment2string());
				tecpojo.setDocument3(tform.getAttachment3string());
				tecpojo.setDocument4(tform.getAttachment4string());
				tecpojo.setDocument5(tform.getAttachment5string());
				
				tecpojo.setIs_student_studying(tform.getIs_student_studying());
				tecpojo.setStudentName(tform.getStudentName());
				tecpojo.setStudent_admission_id(tform.getStudent_admission_id());
				tecpojo.setFatherMobile(tform.getFatherMobile());
				tecpojo.setMotherMobile(tform.getMotherMobile());
				tecpojo.setAadhaarnumber(tform.getAadhaarnumber());
				tecpojo.setAbbreviate(tform.getAbbreviate());
				tecpojo.setMaritalstatus(tform.getMaritalstatus());
				tecpojo.setSpouseMobile(tform.getSpouseMobile());
				tecpojo.setSpousename(tform.getSpousename());
				tecpojo.setSchoolName(tform.getSchoolName());
				tecpojo.setLog_audit_session(log_audit_session);
			
				tecpojo.setUsertype(tform.getUsertype());
				
				System.out.println("Action class teacherUpdate: father Mobile "+tform.getFatherMobile());
				System.out.println("Action class teacherUpdate: mother Mobile "+tform.getMotherMobile());
				System.out.println("Action class is studying "+tform.getIs_student_studying());
				
				
				System.out.println("Action Class file 1: "+tform.getAttachment1string());
				System.out.println("000000000000000"+tecpojo.getImgpath());
				
				/*
				 * tecpojo.setTransport(tform.getTransport());
				 * tecpojo.setLocation(tform.getLocation());
				 */

				result = new TeacherRegistrationBD().teacherUpdate(tecpojo, userLoggingsVo);
				System.out.println("Result: "+result);
				String status=null;
				if (result.equalsIgnoreCase("true")) {
					//request.setAttribute("successmessagediv","Updating Record Progressing...");
					status="success";
				} else {
					//request.setAttribute("errormessagediv","Staff Updatation Failed");
				}
  
				JSONObject object=new JSONObject();
				object.put("status", status);
				response.getWriter().print(object);
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : teacherUpdation Ending");
		
		//return mapping.findForward(MessageConstants.UPDATE_STAFF);
		return null;
	}
	
	public ActionForward getCLasses(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getCLasses Starting");
		//String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String schoolLocation = request.getParameter("schoolId");
			
			List<ClassPojo> classList = new ArrayList<ClassPojo>();
			classList = new ClassBD().getClassDetails(schoolLocation,userLoggingsVo);

			JSONObject object=new JSONObject();
			object.put("classList", classList);
			
			response.getWriter().print(object);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getCLasses Ending");
		return null;
	}
	
	public ActionForward getSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getSection Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String classidVal = request.getParameter("classidVal");
			
			System.out.println("classidVal ::: "+classidVal);
			
			List<StudentRegistrationVo> List = new StudentRegistrationDelegate()
					.getSection(classidVal.trim(),custdetails);
			
			JSONArray array=new JSONArray();
			array.put(List);
			
			System.out.println("section list ::: "+array);
			
			JSONObject jsonObject = new JSONObject(List);
			jsonObject.put("sectionList", List);
			response.getWriter().print(jsonObject);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getSection Ending");
		return null;
	}
	
	public ActionForward getMappingClasses(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getMappingClasses Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String TeacherID = request.getParameter("TeacherID");
			
			System.out.println("TeacherID ::: "+TeacherID);
			
			List<TeacherMappingClassesVo> List = new TeacherRegistrationBD().getMappingClasses(TeacherID,custdetails);
			
			
			JSONObject jsonObject = new JSONObject(List);
			jsonObject.put("MappingList", List);
			response.getWriter().print(jsonObject);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getMappingClasses Ending");
		return null;
	}
	
	public ActionForward getMappedSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				
				+ " Control in TeacherRegistrationAction: getMappedSection Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String TeacherID = request.getParameter("TeacherID");
			String ClassID = request.getParameter("classidVal");
			String SectionID =request.getParameter("sectionID");
			
			System.out.println("TeacherID ::: "+TeacherID);
			System.out.println("ClassID ::: "+ClassID);
			
			List<TeacherMappingClassesVo> List = new TeacherRegistrationBD().getMappedSection(TeacherID,ClassID,SectionID,custdetails);
			
			
			JSONObject jsonObject = new JSONObject(List);
			jsonObject.put("sectionList", List);
			response.getWriter().print(jsonObject);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getMappedSection Ending");
		return null;
	}
	
	public ActionForward getSubject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getSubject Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String classidVal = request.getParameter("classidVal");
			System.out.println("classidVal ::: "+classidVal);
			ArrayList<ViewallSubjectsVo> subjectlist =  new StudentRegistrationDelegate().getSubjectByClass(classidVal.trim(),custdetails);
			JSONObject jsonObject = new JSONObject(subjectlist);
			jsonObject.put("subjectList", subjectlist);
			response.getWriter().print(jsonObject);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getSubject Ending");
		return null;
	}
	
	public ActionForward getMappingSubjects(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				
				+ " Control in TeacherRegistrationAction: getMappingSubjects Starting");

		try {
			
			String TeacherID = request.getParameter("TeacherID");
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			List<TeacherMappingClassesVo> List = new TeacherRegistrationBD().getMappingSubjects(TeacherID,custdetails);
			
			
			JSONObject jsonObject = new JSONObject(List);
			jsonObject.put("subjectList", List);
			response.getWriter().print(jsonObject);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getMappingSubjects Ending");
		return null;
	}
	
	public synchronized ActionForward getTeacherSalary(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				
				+ " Control in TeacherRegistrationAction: getTeacherSalary Starting");
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getTeacherSalary Ending");
		
		
		
		
		return mapping.findForward(MessageConstants.Teacher_Salary);
		
	}
	
	public synchronized ActionForward downloadDocument(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: downloadDocument Starting");

		try {
			String docPath = request.getParameter("Path");
			response.setContentType("application/octet-stream");
			String fileName = "FileName";
			fileName=docPath;
			System.out.println("docPath ::: "+docPath);
			
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
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
				+ " Control in TeacherRegistrationAction: downloadDocument Ending");

		return null;
	}

	public ActionForward downloadStaffDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : downloadStaffDetailsXLS  Starting");
		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/staffdetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			
			AllTeacherDetailsVo obj = new AllTeacherDetailsVo();
			obj.setLocid(request.getParameter("locationId"));
			obj.setDeptid(request.getParameter("depName"));
			obj.setDesgId(request.getParameter("designameid"));
			obj.setStatus(request.getParameter("status"));
			
		
			
			if(obj.getLocid().equalsIgnoreCase("all")){
				obj.setLocid("%%");
			}		
			if(obj.getDeptid().equalsIgnoreCase("all")){
				obj.setDeptid("%%");
			}
			if(obj.getDesgId().equalsIgnoreCase("all")){
				obj.setDesgId("%%");
			}
			
			String locname=request.getParameter("locationname");
            String deptname=request.getParameter("Deptname");
            String designame=request.getParameter("designame");
            String status=request.getParameter("statusname");
            
            if(locname.equalsIgnoreCase("all")){
         	   locname="All";
            }
				
            if(deptname.equalsIgnoreCase("all")){
         	   deptname="All";
            }
            if(designame.equalsIgnoreCase("all")){
         	   designame="All";
            }
            
			ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();
			list = new TeacherRegistrationBD().getAllTeacherDetails(custdetails,obj);
			 LocationVO custSchoolInfo=new LocationVO();
			 custSchoolInfo=HelperClass.getCustSchoolInfo(custdetails,obj.getLocid());
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			Map parameters = new HashMap();
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("schoolname",locname);
			parameters.put("deptname",deptname );
			parameters.put("designname",designame );
			parameters.put("status",status );
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/staffdetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Staff Details Excel Report" };
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
					request.getRealPath("Reports/staffdetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=staffdetailsxls.xls");
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
				+ " Control in TeacherRegistrationAction : downloadStaffDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloadStaffDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TeacherRegistrationAction : downloadStaffDetailsPDF  Starting");

			try {
				UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				System.out.println("downloading pdf");
		
				AllTeacherDetailsVo obj = new AllTeacherDetailsVo();
				obj.setLocid(request.getParameter("locationId"));
				obj.setDeptid(request.getParameter("depName"));
				obj.setDesgId(request.getParameter("designameid"));
				obj.setStatus(request.getParameter("status"));
			
				
				if(obj.getLocid().equalsIgnoreCase("all")){
					obj.setLocid("%%");
				}		
				if(obj.getDeptid().equalsIgnoreCase("all")){
					obj.setDeptid("%%");
				}
				if(obj.getDesgId().equalsIgnoreCase("all")){
					obj.setDesgId("%%");
				}
               String locname=request.getParameter("locationname");
               String deptname=request.getParameter("Deptname");
               String designame=request.getParameter("designame");
               String status=request.getParameter("statusname");
               
               if(locname.equalsIgnoreCase("all")){
            	   locname="All";
               }
				
               if(deptname.equalsIgnoreCase("all")){
            	   deptname="All";
               }
               if(designame.equalsIgnoreCase("all")){
            	   designame="All";
               }
               
				ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();
				list = new TeacherRegistrationBD().getAllTeacherDetails(custdetails,obj);
				
				 LocationVO custSchoolInfo=new LocationVO();
				 custSchoolInfo=HelperClass.getCustSchoolInfo(custdetails,obj.getLocid());
			
				
				String sourceFileName = request
						.getRealPath("Reports/staffDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						list);

				Map parameters = new HashMap();
				parameters.put("locationName",custSchoolInfo.getSchname());
				parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
				parameters.put("schoolname",locname);
				parameters.put("deptname",deptname );
				parameters.put("designname",designame );
				parameters.put("status",status );
				
			

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "staffDetailsPDF - " + ".pdf\"");

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
					+ " Control in TeacherRegistrationAction : downloadStaffDetailsPDF  Ending");
			return null;

		}

	public ActionForward OpeningCameraSoftware(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: OpeningCameraSoftware Starting");

	        
		 String line=null;
	 
	        try
	        {
                Runtime rt = Runtime.getRuntime();
                //Process pr = rt.exec("cmd /c dir");
                Process pr = rt.exec("C:\\Program Files (x86)\\Canon\\ImageBrowser EX\\ImageBrowserEX.exe");
                
 
                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
 
                while((line=input.readLine()) != null) {
                    System.out.println(line);
                }
 
                int exitVal = pr.waitFor();
                System.out.println("Exited with error code "+exitVal);
 
            }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }

			JSONObject object = new JSONObject();
			object.put("status", line);

			response.getWriter().print(object);
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TeacherRegistrationAction : OpeningCameraSoftware  Ending");
		
		return null;
	}
	
	public ActionForward getStudentDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getStudentDetails Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STAFF);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String admissionno = request.getParameter("student_admission_id");
			
			String studentName=new StudentRegistrationDaoImpl().getStudentName(admissionno,userLoggingsVo);
			System.out.println(studentName);
					
			JSONObject object = new JSONObject();
			object.put("status", studentName);

			response.getWriter().print(object);

			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getStudentDetails Ending");
		return null;
	}
	
	public ActionForward getTeacherName(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getTeacherName Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String teacherid = request.getParameter("teachercode");
			System.out.println("teacher code is "+teacherid);

			TeacherRegistrationPojo obj = new TeacherRegistrationPojo();
			obj.setTeacherId(teacherid);
			
			
			List<TeacherRegistrationPojo>	objVo = new TeacherDaoImpl().getTeacherDetailsSingle(obj,userLoggingsVo);
			
			 JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("jsonResponse", objVo);
				
				response.getWriter().print(jsonobj);
			
			
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistration : getTeacherName Ending");

		 return null;   
	}
	
	public ActionForward getOccupation(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getOccupation Starting");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			List<ReligionCasteCasteCategoryVo>	objVo = new ReligionCasteCasteCategoryBD().getOccupationDetails(userLoggingsVo);
			
			 JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("jsonResponse", objVo);
				
				response.getWriter().print(jsonobj);
			
			
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getOccupation Ending");

		 return null;   
	}

	public ActionForward checkAbbreviativeId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: checkAbbreviativeId Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String abbreviate = request.getParameter("abbreviate");
			String locId = request.getParameter("locId");
			
			String status = new TeacherDaoImpl().checkAbbreviativeId(abbreviate,locId,userLoggingsVo);

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
				+ " Control in TeacherRegistrationAction : checkAbbreviativeId Ending");
		return null;
	}
	

	public ActionForward getLeaveTypes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getLeaveTypes Starting");

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			List<LeaveBankVO> leaveTypeList = new ArrayList<LeaveBankVO>();
			
			//List<ClassPojo> classList = new ArrayList<ClassPojo>();
			
			String location=request.getParameter("location");
			
			String academicYear = (String)request.getSession(false).getAttribute("current_academicYear");
			leaveTypeList=new LeaveBankDelegate().getLeaveTypes(academicYear, location,userLoggingsVo);
			System.out.println("leaveTypeList "+leaveTypeList.size());
			JSONObject object=new JSONObject();
			object.put("leaveTypeList", leaveTypeList);
			
			response.getWriter().print(object);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getLeaveTypes Ending");
		return null;
	}
	
	
	public ActionForward getNoOfLeave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction: getNoOfLeave Starting");

		try {
			
			List <LeaveBankVO> noOfLeave = new ArrayList<LeaveBankVO>();
			
			//List<ClassPojo> classList = new ArrayList<ClassPojo>();
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String leaveType=request.getParameter("leaveType");
			String location=request.getParameter("location");
			String academicYear = (String)request.getSession(false).getAttribute("current_academicYear");
			
			System.out.println("leaveType: "+leaveType);
			System.out.println("location: "+location);
			System.out.println("academicYear: "+academicYear);
			System.out.println("");
			noOfLeave=new LeaveBankDelegate().getNoOfLeave(academicYear, location,leaveType,userLoggingsVo);
			
			System.out.println("no of leave: "+noOfLeave.size());
			
			JSONObject object=new JSONObject();
			object.put("noOfLeave", noOfLeave);
			
			response.getWriter().print(object);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherRegistrationAction : getNoOfLeave Ending");
		return null;
	}

	
	public ActionForward studentListbyAdmissionNo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : studentListbyAdmissionNo Starting");

	
		try {
			
			UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			StudentConcessionVo data= new StudentConcessionVo();
			String admissionNo = request.getParameter("admissionNo");
			String locId = request.getParameter("locId");
			
			List<StudentConcessionVo> list =  new TeacherDaoImpl().getStudentListbyAdmissionNo(admissionNo,locId,pojo);

			JSONObject obj=new JSONObject();
			obj.put("stuList",list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : studentListbyAdmissionNo Ending");

		return null;
	}
	
	
}
