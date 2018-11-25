package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ClassFeeSetupForm;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.FeeCollectionService;
import com.centris.campus.serviceImpl.FeeCollectionServiceImpl;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentRegistrationVo;

public class FeeCollectionBD {

	FeeCollectionService feeCollection = new FeeCollectionServiceImpl();
	
	public ArrayList<FeeNameVo> getFeeCollectionDetails(String currentYear, UserLoggingsPojo userLoggingsVo){
		return feeCollection.getFeeCollectionDetails(currentYear,userLoggingsVo);
	}
	
	public ArrayList<FeeNameVo> getSearchFeeCollectionDetails(String currentYear,String classid,String sectionId, String termId, String stuId){
		return feeCollection.getSearchFeeCollectionDetails(currentYear,classid,sectionId,termId,stuId);
	}
	

	public FeeCollectionVo getFeeCollectionAmount(String FeeCodeDetails, UserLoggingsPojo custdetails){
		return feeCollection.getFeeCollectionAmount(FeeCodeDetails,custdetails);
	}
	
	public String saveFeeCollection(FeeCollectionVo collectionVo, UserLoggingsPojo userLoggingsVo){
		return feeCollection.saveFeeCollection(collectionVo,userLoggingsVo);
	}
	
	public List<ParentVO> getAllStudentNamesReportBD(String sectionid, String classname, String accyear) {
		return feeCollection.getAllStudentNamesReportService(sectionid,classname,accyear);
	}
	
	public static ArrayList<ClassFeeSetupVo> getAllFees(ClassFeeSetupPojo feeSetupPojo) {
		 FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
			
			return feeCollection.getAllFeesService(feeSetupPojo);
	}

	public static ClassFeeSetupVo getStudentValBd(ClassFeeSetupPojo feeSetupPojo) {
		 FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
		return feeCollection.getStudentValService(feeSetupPojo);
	}

	public static ArrayList<ClassFeeSetupVo> getPaymentTypeBD(
			ClassFeeSetupPojo feeSetupPojo) {
		
		 FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
			return feeCollection.getPaymentTypeService(feeSetupPojo);
	}

	public static boolean inserfeecollection(ClassFeeSetupForm form1) {
		 FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
			return feeCollection.inserfeecollection(form1);	}

	public FeeCollectionVo getTranportFeeCollectionAmount(String feeCodeDetails, UserLoggingsPojo userLoggingsVo) {
		FeeCollectionService feeCollection=new FeeCollectionServiceImpl();
		
		return feeCollection.getTranportFeeCollectionAmount(feeCodeDetails,userLoggingsVo);
	}

	public String saveTransportFeeCollection(FeeCollectionVo collectionVo, UserLoggingsPojo userLoggingsVo) {
		return feeCollection.saveTransportFeeCollection(collectionVo,userLoggingsVo);
	}

	public ArrayList<FeeNameVo> getFeePaidDetails(String feeCodeDetails, UserLoggingsPojo userLoggingsVo) {
		return feeCollection.getFeePaidDetails(feeCodeDetails,userLoggingsVo);
	}

	public ArrayList<AddFeeVO> getDefaulterFeeList(String locId,String classId, String divId, String termId, String accId, UserLoggingsPojo cpojo) {
		return new FeeCollectionServiceImpl().getDefaulterFeeList(locId,classId,divId,termId,accId,cpojo);
	}

	public ArrayList<StudentConcessionVo> getConcessions(String locId, UserLoggingsPojo userLoggingsVo) {
		return feeCollection.getConcessions(locId,userLoggingsVo);
	}

	public List<StudentConcessionVo> getTermFees(String loc_id, String acy_id, String studentid,UserLoggingsPojo userLoggingsVo, String class_id) {
		return feeCollection.getTermFees(loc_id,acy_id,studentid,userLoggingsVo,class_id);
	}

	public List<StudentConcessionVo> getTermWiseAllFees(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo) {
		return feeCollection.getTermWiseAllFees(feevo,userLoggingsVo);
	}

	public String saveFeeConcessionRequest(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo) {
		return feeCollection.saveFeeConcessionRequest(feevo,userLoggingsVo);
	}

	public List<StudentConcessionVo> getRequestedFees(StudentConcessionVo convo, UserLoggingsPojo userLoggingsVo) {
		return feeCollection.getRequestedFees(convo,userLoggingsVo);
	}

	public String getClassWiseAllFees(StudentConcessionVo feevo,UserLoggingsPojo userLoggingsVo) {
		return feeCollection.getClassWiseAllFees(feevo,userLoggingsVo);
	}

	public ArrayList<FeeCollectionVo> getSchoolFeeCollectionAmount(String feeCodeDetails, UserLoggingsPojo pojo) {
		return new FeeCollectionServiceImpl().getSchoolFeeCollectionAmount(feeCodeDetails,pojo);
	}

	public ArrayList<FeeCollectionVo> getTransportFeeCollectionAmount(String feeCodeDetails, UserLoggingsPojo pojo) {
		return new FeeCollectionServiceImpl().getTransportFeeCollectionAmount(feeCodeDetails,pojo);
	}

	public String cancelRequestConcessionFees(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo) {
		return feeCollection.cancelRequestConcessionFees(feevo,userLoggingsVo);
	}
	public ArrayList<StudentRegistrationVo> getFeeCancelledStudentList(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {
		return feeCollection.getFeeCancelledStudentList(vo,userLoggingsVo);
		}

	public ArrayList<FeeCollectionVo> getIndividualFeeCancelledStudent(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {
		return feeCollection.getIndividualFeeCancelledStudent(vo,userLoggingsVo);
		}

	public String checkFeesPaidStatus(String studId, String acyId, String termCode, UserLoggingsPojo userLoggingsVo) {
		return feeCollection.checkFeesPaidStatus(studId,acyId,termCode,userLoggingsVo);
	}

	public List<StudentConcessionVo> getTermConcessionFees(StudentConcessionVo convo) {
		return feeCollection.getTermConcessionFees(convo);
	}

}
