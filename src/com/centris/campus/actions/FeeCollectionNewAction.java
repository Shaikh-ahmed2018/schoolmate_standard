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
import com.centris.campus.daoImpl.feeCollectionNewDaoImpl;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.FeeCollectionNewBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.UserDetailVO;

public class FeeCollectionNewAction extends DispatchAction{
	
	private static Logger logger = Logger.getLogger(FeeCollectionNewAction.class);
	
	static ResourceBundle res = ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	//private static String ImageName = res.getString("ImageName");
	private static String SchholName = res.getString("SchoolName");
	private static String Address = res.getString("AddressLine1");
	
	public ActionForward feeCollectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionNewAction : feeCollectionList Starting");
		
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_COLLECTION_NEW);
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		
			/*	request.setAttribute("ClassList", new ClassBD().getClassDetails(schoolLocation,userLoggingsVo));

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();*/

			
			String userType = userDetailVO.getUserNametype();
			String userCode = userDetailVO.getUserId();
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			

		/*	if(schoolLocation.equalsIgnoreCase("all")){
			schoolLocation = "%%";
				list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location,userLoggingsVo);
			}
		     else{
				list = new StudentRegistrationDelegate().getStudentList(academic_year,location,userLoggingsVo);
				
			}*/
			
			if (academic_year == null || academic_year == ""
					|| academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}

			//List<StudentRegistrationVo> List = null;
			String searchTerm = request.getParameter("stuId");
			/*if (searchTerm != null && !searchTerm.equalsIgnoreCase("")) {
				List = new StudentRegistrationDelegate().getStudentDetails(searchTerm,schoolLocation,userLoggingsVo);
				request.setAttribute("searchTerm",searchTerm);

			} else {
				List = new StudentRegistrationDelegate().getStudentDetails1(userType, userCode, academic_year,schoolLocation,userLoggingsVo);
				
			     }	*/	
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionId", request.getParameter("historysectionId"));
			request.setAttribute("historysearch", request.getParameter("historysearch"));
			
			
			//request.setAttribute(MessageConstants.STUDENTDETAILSLIST, List);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionNewAction : feeCollectionList Ending");

		return mapping.findForward(MessageConstants.FEE_COLLECTION_LIST_NEW);

	}
	
	public ActionForward studentListByJs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentListByJs Starting");

		List<StudentRegistrationVo> List = null;
		try {
			
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			StudentRegistrationVo data= new StudentRegistrationVo();
			String locationId=request.getParameter("locationId");
			String academicYear=request.getParameter("academicYear");
			String classId=request.getParameter("classId");
			String divisionId=request.getParameter("divisionId");
			String searchTerm=request.getParameter("searchTerm");

			data.setLocationId(locationId);
			data.setAcademicYear(academicYear);
			data.setClasscode(classId);
			data.setSection_id(divisionId);
			data.setSearchTerm(searchTerm);

			List = new FeeCollectionNewBD().getStudentDetailsByJs(data,userLoggingsVo);

			JSONObject obj=new JSONObject();
			obj.put(MessageConstants.STUDENTDETAILSLIST, List);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentListByJs Ending");

		return null;
	}
	
	public  ActionForward feeCollectionStudentWise(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionNewAction : feeCollectionStudentWise Starting");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_COLLECTION_NEW);
			

			String FeeCodeDetails = request.getParameter("student")+","+request.getParameter("academicYear")+","
					+ request.getParameter("loc_id")+","+request.getParameter("classid")+","+request.getParameter("spec");


			FeeCollectionVo collectionVo = new FeeCollectionNewBD().getNewFeeCollectionAmount(FeeCodeDetails,userLoggingsVo);
			
			FeeCollectionVo paymenthistory = new FeeCollectionNewBD().paymenthistory(FeeCodeDetails,userLoggingsVo);
			
			String schoolName =	HelperClass.getSchoolName(request.getParameter("loc_id"), userLoggingsVo);
			
			
			request.setAttribute("locid",request.getParameter("loc_id"));
			request.setAttribute("schoolName",schoolName);
			request.setAttribute("",Address);
			if(collectionVo.getReason()!=null && collectionVo.getReason().equalsIgnoreCase("notfound"))
				request.setAttribute("Reason","notfound");
			else{
				request.setAttribute("FeeCollectionVo", collectionVo);
			}
			if(paymenthistory.getStatus()!=null && paymenthistory.getStatus().equalsIgnoreCase("notpaid")){
				request.setAttribute("feepaidReason", "notfound");
			}else{
				request.setAttribute("paymenthistory", paymenthistory);
			}
			
			request.setAttribute("studentDetails", collectionVo);
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
			request.setAttribute("historyclassname", request.getParameter("historyclassname"));
			request.setAttribute("historysectionId", request.getParameter("historysectionId"));
			request.setAttribute("historysearch", request.getParameter("historysearch"));
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in FeeCollectionNewAction : feeCollectionStudentWise Ending");

		return mapping.findForward(MessageConstants.FEE_COLLECTION_STUDENT_WISE_NEW);
	}

	public ActionForward feeCollection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeCollection Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_FEECOLLECTION);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
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
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}

			List<StudentRegistrationVo> List = null;
			String searchTerm = request.getParameter("stuId");
			if (searchTerm != null && !searchTerm.equalsIgnoreCase("")) {
				List = new StudentRegistrationDelegate()
				.getStudentDetails(searchTerm,schoolLocation,userLoggingsVo);
				request.setAttribute("searchTerm",searchTerm);

			} else {
				List = new StudentRegistrationDelegate().getStudentDetails1(
						userType, userCode, academic_year,schoolLocation,userLoggingsVo);
				
			     }		
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);

			request.setAttribute("AccYearList", accYearList);
			request.setAttribute(MessageConstants.STUDENTDETAILSLIST, List);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeCollection Ending");

		return mapping.findForward(MessageConstants.FEE_COLLECTION_LIST_NEW);

	}
	
	
	 public ActionForward paybalFees(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date()) + " Control in FeeCollectionNewAction : paybalFees Starting");
			
			try{
				
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				String value = request.getParameter("value");
				String id = request.getParameter("id");
				String cashAmount = request.getParameter("cashAmount");
				String cardAmount = request.getParameter("cardAmount");
				
				FeeCollectionVo vo = new FeeCollectionVo();
				vo.setTermid(value.split("-")[0]);
				vo.setFeecolcode(value.split("-")[1]);
				//vo.setFeeindetcode(value.split("-")[2]);
				vo.setCurrent_payment(Double.parseDouble(id.split("-")[0]));
				vo.setPaymentMode(request.getParameter("paymentMode"));
				vo.setDd_cheque_bank(request.getParameter("dd_cheque_bank"));
				vo.setDd_cheque_date(request.getParameter("dd_cheque_date"));
			
				vo.setPaymentPatriculars(request.getParameter("paymentParticulars"));
				vo.setAddmissionno(request.getParameter("addmissionNo"));
				vo.setAccYear(request.getParameter("accodemicyear"));
				vo.setTot_actual_amt(Double.parseDouble(request.getParameter("totalAmt")));
				vo.setTot_paid_amt(Double.parseDouble(request.getParameter("payingAmount")));
				if(cashAmount!=null && cashAmount.trim().equalsIgnoreCase(""))
				vo.setCashAmount(Double.parseDouble(request.getParameter("cashAmount")));
				if(request.getParameter("cardAmount")!=null && request.getParameter("cardAmount").trim().equalsIgnoreCase(""))
				vo.setCardAmount(Double.parseDouble(request.getParameter("cardAmount")));
				vo.setUserID(HelperClass.getCurrentUserID(request));
				
				String status = new FeeCollectionNewBD().paybalFees(vo,userLoggingsVo);
				
				JSONObject json = new JSONObject();
				json.put("status",status);
				response.getWriter().print(json);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date()) + " Control in FeeCollectionNewAction : paybalFees Ending");

		 return null;
	 }
	 
	 
	 
		public synchronized ActionForward feeCollectionDetailsDues(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
			logger.info(
					JDate.getTimeString(new Date()) + " Control in FeeCollectionNewAction : feeCollectionDetailsDues Starting");
			try {

				
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_FEE);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_FEE);

				String academic_year = request.getParameter("accYear");
				String classId = request.getParameter("classId");
				String termId = request.getParameter("term");
				String specialization = request.getParameter("specialization");
				String feecollectioncode = request.getParameter("feesetcode");
				String FeeCodeDetails = request.getParameter("student") + "," + academic_year + "," + classId + "," + termId
						+ "," + specialization+","+feecollectioncode;
				
				ArrayList<FeeNameVo> FeeCollectionDetails = new feeCollectionNewDaoImpl().feeCollectionDetailsDues(FeeCodeDetails,userLoggingsVo);
				JSONObject obj = new JSONObject();
				obj.put("FeeCollectionDetails", FeeCollectionDetails);
				response.getWriter().print(obj);

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date()) + " Control in FeeCollectionNewAction : feeCollectionDetailsDues Ending");

			return null;
		}
	
		
		public synchronized ActionForward feeCollectionDetails(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
			logger.info(
					JDate.getTimeString(new Date()) + " Control in FeeCollectionNewAction : feeCollectionDetails Starting");
			try {
				
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

				request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_FEE);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_FEE);

				String academic_year = request.getParameter("accYear");
				String classId = request.getParameter("classId");
				String termId = request.getParameter("term");
				String specialization = request.getParameter("specialization");
				String FeeCodeDetails = request.getParameter("studentId") + "," + academic_year + "," + classId + "," + termId
						+ "," + specialization;

				ArrayList<FeeCollectionVo> FeeCollectionDetails = new feeCollectionNewDaoImpl().getFeeCollectionDetailsDiscount(FeeCodeDetails,userLoggingsVo);
				
				JSONObject obj = new JSONObject();
				obj.put("FeeCollectionDetails", FeeCollectionDetails);
				response.getWriter().print(obj);

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date()) + " Control in FeeCollectionNewAction : feeCollectionDetails Ending");

			return null;
		}

		
		
		public synchronized ActionForward feePaidDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date()) + " Control in FeeCollectionAction : feePaidDetails Starting");
			try {

				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
			
				
				UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
				String cashierName = userDetailVO.getUserName();
				
				
				request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_FEE);
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_FEE);
				
				String locationId = request.getParameter("locationId");
				String accyearid = request.getParameter("accYearId");
				String studentId = request.getParameter("studentId");
				String receiptno = request.getParameter("receiptNo");
				String locId = request.getParameter("locId");
				
				String schoolName =	HelperClass.getSchoolName(locId, userLoggingsVo);
				
				FeeCollectionVo vo = new FeeCollectionVo();
				vo.setStudentid(studentId);
				vo.setAccYearId(accyearid);
				vo.setReceiptno(receiptno);
				vo.setLocationId(locationId);
				FeeCollectionVo list = new feeCollectionNewDaoImpl().getFeeNewPaidDetails(vo,userLoggingsVo);
				
				List<LocationVO> list1 = HelperClass.getSingleLocationDetails(locId,userLoggingsVo);
				
				JSONObject obj = new JSONObject();
				obj.put("FeeCollectionDetails", list.getFeeNamelist());
				obj.put("actualamt",list.getTot_actual_amt());
				obj.put("paidamt",list.getTot_paid_amt());
				obj.put("balanceamt",list.getOpening_balance());
				obj.put("advanceamt",list.getAdvanceAmount());
				obj.put("amountinwords",list.getAmountInWords());
				obj.put("paymentmode",list.getPaymentMode());
				obj.put("bankname",list.getBankname());
				obj.put("chequeno",list.getChequeno());
				obj.put("chequedate",list.getDd_cheque_date());
				obj.put("username",list.getUsername());
				obj.put("billdate",list.getBilldate());
				obj.put("cashamt",list.getCashAmount());
				obj.put("cardamt",list.getCardAmount());
				obj.put("fineamt",list.getFineAmount());
				obj.put("schoolName",schoolName);
				obj.put("board",list1.get(0).getBoard());
				obj.put("address",list1.get(0).getAddress());
				obj.put("contactNo",list1.get(0).getContactno());
				obj.put("affilNo",list1.get(0).getAffilno());
				obj.put("website",list1.get(0).getWebsite());
				obj.put("email",list1.get(0).getEmailId());
				obj.put("cashier",cashierName);
				
				response.getWriter().print(obj);
				
			

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date()) + " Control in FeeCollectionAction : feePaidDetails Ending");

			return null;
		}
		
		
		public synchronized ActionForward saveFeeCollection(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date()) + " Control in FeeCollectionAction : saveFeeCollection Starting");
			try {
				
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

				String extraAmtCarryFrwd =   request.getParameter("extraAmtCarryFrwd");
				String addmissionNo = request.getParameter("addmissionNo");
				String accYear = request.getParameter("accodemicyear");
				String termId = request.getParameter("termid");
				String classId = request.getParameter("classd");
				String feeId = request.getParameter("feeIdArray");
				String feeAmountArray = request.getParameter("feeAmountArray");
				String totalAmount = request.getParameter("totalAmount");
				String feeSetting = request.getParameter("feeSetting");
				String fineAmount = request.getParameter("fineAmount");
				String payingAmount = request.getParameter("payingAmount");
				String advanceCarry = request.getParameter("advanceCarry");
				String duesCarry = request.getParameter("duesCarry");
				String paymentParticulars = request.getParameter("paymentParticulars");
				String paymentMode = request.getParameter("paymentMode");
				String dd_cheque_bank = request.getParameter("dd_cheque_bank");
				String dd_cheque_date = request.getParameter("dd_cheque_date");
				String concessionAmount = request.getParameter("concessionAmount");
				String actualamtarray = request.getParameter("actualamtarray");
				String feesetcode = request.getParameter("feesetcode");
				String feetypearray = request.getParameter("feetypeId");
				String discountAmt = request.getParameter("discountAmt");
				String distermid = request.getParameter("distermid");
				String billdate = request.getParameter("billdate");
				String prefeecolcode = request.getParameter("prefeecol");
				String cashAmount = request.getParameter("cashAmount");
				if(cashAmount == null){
					cashAmount="0";
				}
				String cardAmount = request.getParameter("cardAmount");
				if(cardAmount == null){
					cardAmount="0";
				}
				
				List<FeeCollectionVo> termwise = new ArrayList<FeeCollectionVo>();
				
				
				for(int i=0 ; i<termId.split(",").length;i++){
					FeeCollectionVo collectionVo = new FeeCollectionVo();
					collectionVo.setTermid(termId.split(",")[i]);
					collectionVo.setFineAmount(Double.parseDouble(fineAmount.split(",")[i]));
					collectionVo.setAddmissionno(addmissionNo);
					collectionVo.setAccYear(accYear);
					collectionVo.setClassId(classId);
					
					collectionVo.setUserID(HelperClass.getCurrentUserID(request));
					collectionVo.setTot_actual_amt(Double.parseDouble(totalAmount.split(",")[i]));
					collectionVo.setCurrent_payment(Double.parseDouble(payingAmount.split(",")[i]));
					collectionVo.setAdvanceCarry(Double.parseDouble(advanceCarry.split(",")[i]));
					collectionVo.setDuesCarry(Double.parseDouble(duesCarry.split(",")[i]));
					if(collectionVo.getDuesCarry()!=0)
					collectionVo.setPrefeecolcode(prefeecolcode.split(",")[i]);
					
					collectionVo.setPaymentMode(paymentMode);
					collectionVo.setBilldate(billdate);
					collectionVo.setDd_cheque_bank(dd_cheque_bank);
					collectionVo.setDd_cheque_date(dd_cheque_date);
					collectionVo.setPaymentPatriculars(paymentParticulars);
					collectionVo.setConcession(Double.parseDouble(concessionAmount.split(",")[i]));
					collectionVo.setFeeSettingList(feeSetting);
					collectionVo.setFeesettingCode(feesetcode);
					collectionVo.setCashAmount(Double.parseDouble(cashAmount));
					collectionVo.setCardAmount(Double.parseDouble(cardAmount));
					ArrayList<FeeNameVo> feeList = new ArrayList<FeeNameVo>();
					String feeids = feeId.split(",")[i];
					String feetypearrays = feetypearray.split(",")[i];
					String feeAmountArrays = feeAmountArray.split(",")[i];
					String actualamtarrays = actualamtarray.split(",")[i];
					for (int j = 0; j < feeids.split("-").length; j++) {
						FeeNameVo feevo = new FeeNameVo();
						feevo.setFeecode(feeids.split("-")[j]);
						feevo.setFeetype(feetypearrays.split("-")[j]);
						feevo.setFeeAmountArray(Double.parseDouble(feeAmountArrays.split("-")[j]));
						feevo.setActualamtarray(Double.parseDouble(actualamtarrays.split("-")[j]));
						feeList.add(feevo);
					}
					
					collectionVo.setFeeNamelist(feeList);
					termwise.add(collectionVo);
				}
				String status = null;
				
				status = new feeCollectionNewDaoImpl().saveFeeCollection(termwise,discountAmt,distermid,termId,userLoggingsVo,extraAmtCarryFrwd);
		
				
				JSONObject object = new JSONObject();
				object.put("status", status);
				response.getWriter().print(object);

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date()) + " Control in FeeCollectionAction : saveFeeCollection Ending");

			return null;
		}

	

}
