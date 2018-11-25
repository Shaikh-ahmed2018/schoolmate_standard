package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.forms.StudentEnquiryForm;
import com.centris.campus.pojo.StudentEnquiryPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.StudentAttendanceReportVo;
import com.centris.campus.vo.StudentEnquiryVo;
import com.centris.campus.vo.StudentInfoVO;

public interface StudentEnquiryDao {

	public List<StudentEnquiryVo> getAllEnquiryDetails(UserLoggingsPojo userLoggingsVo);

	public List<StudentEnquiryVo> getSelectedEnquiryDetails(String Id);

	public String saveStudentEnquiry(StudentEnquiryForm studentEnquiryForm);

	public boolean duplicateStudentChecking(
			StudentEnquiryPojo studentenquiryPojo);

	public boolean validationMobileno(String mobileno);

	public List<StudentEnquiryVo> getEnquiryDetailsBySearch(String date,
			String name, String interactionStatus, String AdmissionStatus);

	public boolean updateStudentEnquiry(StudentEnquiryVo studentenquiry);

	public boolean applicationNoValidate(String appnumber);

	public boolean validationPhoneno(String mobileno, String enq_Id);

	public boolean deleteEnquiryDetails(String Id);

	public List<StudentEnquiryVo> getSearchEnquiryDetails(String text, UserLoggingsPojo userLoggingsVo);

	public List<StudentInfoVO> getStudentParentDetails(String usercode, UserLoggingsPojo custdetails, String accYears);

	public List<StudentAttendanceReportVo> getAttendanceDetail(String usercode, UserLoggingsPojo custdetails);

	public List<ExaminationDetailsVo> getDeclaredExam(String usercode, UserLoggingsPojo custdetails, String accYears);
}
