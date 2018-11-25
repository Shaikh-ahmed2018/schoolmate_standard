package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ClassFeeSetupForm;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentRegistrationVo;

public interface FeeCollectionService {
	
	public ArrayList<FeeNameVo> getFeeCollectionDetails(String currentYear, UserLoggingsPojo userLoggingsVo);
	public FeeCollectionVo getFeeCollectionAmount(String FeeCodeDetails, UserLoggingsPojo custdetails);
	public String saveFeeCollection(FeeCollectionVo collectionVo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<FeeNameVo> getSearchFeeCollectionDetails(String currentYear,String classid,String sectionId, String termId, String stuId);
	public List<ParentVO> getAllStudentNamesReportService(String sectionid, String classname, String accyear);
	public ArrayList<ClassFeeSetupVo> getAllFeesService(ClassFeeSetupPojo feeSetupPojo);
	public ClassFeeSetupVo getStudentValService(ClassFeeSetupPojo feeSetupPojo);
	public ArrayList<ClassFeeSetupVo> getPaymentTypeService(ClassFeeSetupPojo feeSetupPojo);
	public boolean inserfeecollection(ClassFeeSetupForm form1);
	public FeeCollectionVo getTranportFeeCollectionAmount(String feeCodeDetails, UserLoggingsPojo userLoggingsVo);
	public String saveTransportFeeCollection(FeeCollectionVo collectionVo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<FeeNameVo> getFeePaidDetails(String feeCodeDetails, UserLoggingsPojo userLoggingsVo);
	public ArrayList<StudentConcessionVo> getConcessions(String locId, UserLoggingsPojo userLoggingsVo);
	public List<StudentConcessionVo> getTermFees(String loc_id, String acy_id, String studentid, UserLoggingsPojo userLoggingsVo, String class_id);
	public List<StudentConcessionVo> getTermWiseAllFees(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo);
	public String saveFeeConcessionRequest(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo);
	public List<StudentConcessionVo> getRequestedFees(StudentConcessionVo convo, UserLoggingsPojo userLoggingsVo);
	public String getClassWiseAllFees(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo);
	public String cancelRequestConcessionFees(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<StudentRegistrationVo> getFeeCancelledStudentList(AddFeeVO vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<FeeCollectionVo> getIndividualFeeCancelledStudent(AddFeeVO vo, UserLoggingsPojo userLoggingsVo);
	public String checkFeesPaidStatus(String studId, String acyId, String termCode, UserLoggingsPojo userLoggingsVo);
	public List<StudentConcessionVo> getTermConcessionFees(StudentConcessionVo convo);

}
