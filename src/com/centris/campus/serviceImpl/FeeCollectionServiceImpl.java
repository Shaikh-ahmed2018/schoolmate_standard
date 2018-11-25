package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.FeeCollectionDao;
import com.centris.campus.daoImpl.FeeCollectionDaoImpl;
import com.centris.campus.forms.ClassFeeSetupForm;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.FeeCollectionService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentRegistrationVo;

public class FeeCollectionServiceImpl implements FeeCollectionService{

	private static Logger logger = Logger.getLogger(FeeCollectionServiceImpl.class);

	@Override
	public ArrayList<FeeNameVo> getFeeCollectionDetails(String currentYear, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getFeeCollectionDetails Starting");

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		ArrayList<FeeNameVo> feeCollectionList=new ArrayList<FeeNameVo>();

		try {

			feeCollectionList=dao.getFeeCollectionDetails(currentYear,userLoggingsVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getFeeCollectionDetails Ending");

		return feeCollectionList;
	}

	@Override
	public FeeCollectionVo getFeeCollectionAmount(String FeeCodeDetails,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getFeeCollectionAmount Starting");

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		FeeCollectionVo feeCollection=new FeeCollectionVo();

		try {

			feeCollection=dao.getFeeCollectionAmount(FeeCodeDetails, custdetails);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getFeeCollectionAmount Ending");

		return feeCollection;
	}

	@Override
	public String saveFeeCollection(FeeCollectionVo collectionVo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : saveFeeCollection Starting");

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		String status=null;

		try {

			status=dao.saveFeeCollection(collectionVo,userLoggingsVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : saveFeeCollection Ending");

		return status;
	}

	@Override
	public ArrayList<FeeNameVo> getSearchFeeCollectionDetails(String currentYear,String classid,
			String sectionId,String termId,String stuId)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getSearchFeeCollectionDetails Starting");

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		ArrayList<FeeNameVo> feeCollectionList=new ArrayList<FeeNameVo>();

		try {

			feeCollectionList=dao.getSearchFeeCollectionDetails(currentYear,classid,sectionId,termId,stuId);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getSearchFeeCollectionDetails Ending");

		return feeCollectionList;
	}

	public List<ParentVO> getAllStudentNamesReportService(String sectionid,String classname,String accyear) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getAllStudentNamesReportService Starting");	

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		List<ParentVO> feeCollectionList=new ArrayList<ParentVO>();

		try {


			feeCollectionList=dao.getAllStudentNamesReportDao(sectionid,classname,accyear);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getAllStudentNamesReportService Ending");


		return feeCollectionList;
	}

	public ArrayList<ClassFeeSetupVo> getAllFeesService(
			ClassFeeSetupPojo feeSetupPojo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getAllFeesService Starting");	

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		ArrayList<ClassFeeSetupVo> feeCollectionList=new ArrayList<ClassFeeSetupVo>();

		try {


			feeCollectionList=dao.getAllFeesDao(feeSetupPojo);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getAllFeesService Ending");

		return feeCollectionList;
	}


	public ClassFeeSetupVo getStudentValService(ClassFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getStudentValService Starting");	

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		ClassFeeSetupVo studentval=null;


		try {

			studentval = dao.getStudentValDao(feeSetupPojo);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getStudentValService Ending");

		return studentval;
	}


	public ArrayList<ClassFeeSetupVo> getPaymentTypeService(ClassFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getPaymentTypeService Starting");	

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		ArrayList<ClassFeeSetupVo> studentval=null;

		try {



			studentval = dao.getPaymentTypeDAO(feeSetupPojo);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getPaymentTypeService Ending");

		return studentval;
	}

	@Override
	public boolean inserfeecollection(ClassFeeSetupForm form1) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : inserfeecollection Starting");	

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		boolean inserfeecollection=false;



		try
		{
			System.out.println("Service IMPL");

			String accyear =form1.getAccyear().trim();
			String fmsname =form1.getHfmsname().trim();
			String studentid =form1.getStudentid().trim();
			String admissionNum =form1.getAdmissionNum().trim();
			String payment_mode =form1.getPayment_mode().trim();
			String paymentType =form1.getPaymentType().trim();
			double paidamount =form1.getPayingamount();
			double totalfeeamt =	form1.getTotalfeeamt();
			double dueamount =	form1.getDueamount();
			String payment_date_id =form1.getPayment_date_id().trim();
			String hclassname =form1.getHclassname().trim();
			String paymentMonth =form1.getPaymentMonth().trim();
			String monthlist =form1.getMonthlist().trim();
			String cheque_no=form1.getCheque_no();


			ClassFeeSetupVo vo = new ClassFeeSetupVo();



			int count = dao.getstudentcount(studentid);

			if(count==0)
			{
				System.out.println("Insert Service Impl");

				vo.setAcadamicyear(accyear);
				vo.setFeecode(fmsname);
				vo.setStudentid(studentid);
				vo.setStdadmissionNo(admissionNum);
				vo.setPaymentmode(payment_mode);
				vo.setPaymenttype(paymentType);
				vo.setPaidamount(paidamount);
				vo.setTotalfeeamount(totalfeeamt);
				vo.setDueamount(dueamount);
				vo.setPaymentdate(payment_date_id);
				vo.setClassname(hclassname);
				vo.setPaymentmonth(paymentMonth);
				vo.setMonthlist(monthlist);
				vo.setCurrentuser(form1.getCurrentuserid());
				vo.setCheque_no(cheque_no);
				vo.setStudentname(fmsname);

				inserfeecollection=dao.inserfeecollection(vo);

			}
			else
			{
				System.out.println("Update Operation");
				vo.setAcadamicyear(accyear);
				vo.setFeecode(fmsname);
				vo.setStudentid(studentid);
				vo.setStdadmissionNo(admissionNum);
				vo.setPaymentmode(payment_mode);
				vo.setPaymenttype(paymentType);
				vo.setPaidamount(paidamount);
				vo.setTotalfeeamount(totalfeeamt);
				vo.setDueamount(dueamount);
				vo.setPaymentdate(payment_date_id);
				vo.setClassname(hclassname);
				vo.setPaymentmonth(paymentMonth);
				vo.setMonthlist(monthlist);
				vo.setCurrentuser(form1.getCurrentuserid());
				vo.setCheque_no(cheque_no);


				inserfeecollection=dao.updatefeecollection(vo);

			}

		} 

		catch (Exception e) 
		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : inserfeecollection Ending");


		return inserfeecollection;
	}

	@Override
	public FeeCollectionVo getTranportFeeCollectionAmount(String feeCodeDetails,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getTranportFeeCollectionAmount Starting");

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		FeeCollectionVo feeCollection=new FeeCollectionVo();

		try {

			feeCollection=dao.getTranportFeeCollectionAmount(feeCodeDetails,userLoggingsVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getTranportFeeCollectionAmount Ending");

		return feeCollection;
	}

	@Override
	public String saveTransportFeeCollection(FeeCollectionVo collectionVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : saveTransportFeeCollection Starting");

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		String status=null;

		try {

			status=dao.saveTransportFeeCollection(collectionVo,userLoggingsVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : saveTransportFeeCollection Ending");

		return status;
	}

	@Override
	public ArrayList<FeeNameVo> getFeePaidDetails(String feeCodeDetails, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getFeePaidDetails Starting");

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		ArrayList<FeeNameVo> feeCollectionList=new ArrayList<FeeNameVo>();

		try {
			feeCollectionList=dao.getFeePaidDetails(feeCodeDetails,userLoggingsVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getFeePaidDetails Ending");

		return feeCollectionList;
	}

	public ArrayList<AddFeeVO> getDefaulterFeeList(String locId, String classId,String divId, String termId, String accId, UserLoggingsPojo cpojo) {
		return new FeeCollectionDaoImpl().getDefaulterFeeList(locId,classId,divId,termId,accId,cpojo);
	}

	@Override
	public ArrayList<StudentConcessionVo> getConcessions(String locId, UserLoggingsPojo userLoggingsVo) {
		return new FeeCollectionDaoImpl().getConcessions(locId, userLoggingsVo);
	}

	@Override
	public List<StudentConcessionVo> getTermFees(String loc_id, String acy_id, String studentid, UserLoggingsPojo userLoggingsVo,String class_id) {
		return new FeeCollectionDaoImpl().getTermFees(loc_id,acy_id,studentid,  userLoggingsVo,class_id);
	}

	@Override
	public List<StudentConcessionVo> getTermWiseAllFees(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo) {
		return new FeeCollectionDaoImpl().getTermWiseAllFees(feevo,userLoggingsVo);
	}

	@Override
	public String saveFeeConcessionRequest(StudentConcessionVo feevo,UserLoggingsPojo userLoggingsVo) {
		return new FeeCollectionDaoImpl().saveFeeConcessionRequest(feevo,userLoggingsVo);
	}

	public ArrayList<FeeCollectionVo> getSchoolFeeCollectionAmount(String feeCodeDetails, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getSchoolFeeCollectionAmount Starting");

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		ArrayList<FeeCollectionVo> feeCollectionList=new ArrayList<FeeCollectionVo>();

		try {
			feeCollectionList=dao.getSchoolFeeCollectionAmount(feeCodeDetails,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getSchoolFeeCollectionAmount Ending");

		return feeCollectionList;
	}

	public ArrayList<FeeCollectionVo> getTransportFeeCollectionAmount(String feeCodeDetails, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getTransportFeeCollectionAmount Starting");

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		ArrayList<FeeCollectionVo> feeCollectionList=new ArrayList<FeeCollectionVo>();

		try {
			feeCollectionList=dao.getTransportFeeCollectionAmount(feeCodeDetails,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getTransportFeeCollectionAmount Ending");

		return feeCollectionList;
	}

	@Override
	public List<StudentConcessionVo> getRequestedFees(StudentConcessionVo convo,UserLoggingsPojo userLoggingsVo) {
		return new FeeCollectionDaoImpl().getRequestedFees(convo,userLoggingsVo);
	}

	@Override
	public String getClassWiseAllFees(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo) {
		return new FeeCollectionDaoImpl().getClassWiseAllFees(feevo,userLoggingsVo);
	}

	public String cancelRequestConcessionFees(StudentConcessionVo feevo,UserLoggingsPojo userLoggingsVo) {
		return new FeeCollectionDaoImpl().cancelRequestConcessionFees(feevo,userLoggingsVo);
	}
	@Override
	public ArrayList<StudentRegistrationVo> getFeeCancelledStudentList(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getFeeCancelledStudentList Starting");

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		ArrayList<StudentRegistrationVo> feeCancelList=new ArrayList<StudentRegistrationVo>();

		try {
			feeCancelList=dao.getFeeCancelledStudentList(vo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getFeeCancelledStudentList Ending");

		return feeCancelList;
	}
	@Override
	public ArrayList<FeeCollectionVo> getIndividualFeeCancelledStudent(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getIndividualFeeCancelledStudent Starting");

		FeeCollectionDao dao=new FeeCollectionDaoImpl();
		ArrayList<FeeCollectionVo> feeCancelList=new ArrayList<FeeCollectionVo>();

		try {
			feeCancelList=dao.getIndividualFeeCancelledStudent(vo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionServiceImpl : getIndividualFeeCancelledStudent Ending");

		return feeCancelList;
	}
	@Override
	public String checkFeesPaidStatus(String studId, String acyId, String termCode, UserLoggingsPojo userLoggingsVo) {
		return new FeeCollectionDaoImpl().checkFeesPaidStatus(studId,acyId,termCode,userLoggingsVo);
	}

	@Override
	public List<StudentConcessionVo> getTermConcessionFees(StudentConcessionVo convo) {
		return new FeeCollectionDaoImpl().getTermConcessionFees(convo);
	}


}
