package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.StudentEnquiryForm;
import com.centris.campus.pojo.StudentEnquiryPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StudentEnquiryService;
import com.centris.campus.serviceImpl.StudentEnquiryServiceImpl;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.StudentAttendanceReportVo;
import com.centris.campus.vo.StudentEnquiryVo;
import com.centris.campus.vo.StudentInfoVO;

public class StudentEnquiryBD {
	List<StudentEnquiryVo> details = null;
	StudentEnquiryService obj = new StudentEnquiryServiceImpl();

	ArrayList<StudentEnquiryForm> enquirylist = null;

	public List<StudentEnquiryVo> getAllEnquiryDetails(UserLoggingsPojo userLoggingsVo) {
		details = obj.getAllEnquiryDetails(userLoggingsVo);
		return details;
	}

	public List<StudentEnquiryVo> getSelectedEnquiryDetails(String Id) {
		details = obj.getSelectedEnquiryDetails(Id);
		return details;
	}

	public String saveStudentEnquiry(StudentEnquiryForm studentEnquiryForm) {
		String status = obj.saveStudentEnquiry(studentEnquiryForm);
		return status;
	}

	public boolean duplicateStudentChecking(
			StudentEnquiryPojo studentenquiryPojo) {
		boolean status = obj.duplicateStudentChecking(studentenquiryPojo);
		return status;
	}

	public boolean validationMobileno(String mobileno) {
		boolean status1 = obj.validationMobileno(mobileno);
		return status1;
	}

	public List<StudentEnquiryVo> getEnquiryDetailsBySearch(String date,
			String name, String interactionStatus, String AdmissionStatus) {
		details = obj.getEnquiryDetailsBySearch(date, name, interactionStatus,
				AdmissionStatus);
		return details;
	}

	public boolean updateStudentEnquiry(StudentEnquiryVo studentenquiry) {
		boolean status = obj.updateStudentEnquiry(studentenquiry);
		return status;
	}

	public boolean applicationNoValidate(String appnumber) {
		boolean status = obj.applicationNoValidate(appnumber);
		return status;
	}

	public boolean validationPhoneno(String mobileno, String enq_Id) {
		boolean status1 = obj.validationPhoneno(mobileno, enq_Id);
		return status1;
	}

	public boolean deleteEnquiryDetails(String Id) {
		boolean status = obj.deleteEnquiryDetails(Id);
		return status;
	}

	public List<StudentEnquiryVo> getSearchEnquiryDetails(String text,UserLoggingsPojo userLoggingsVo) {
		details = obj.getSearchEnquiryDetails(text,userLoggingsVo);
		return details;
	}

	public List<StudentInfoVO> getStudentParentDetails(String usercode, UserLoggingsPojo custdetails, String accYears) {
		return obj.getStudentParentDetails(usercode,custdetails,accYears);
	}

	public List<StudentAttendanceReportVo> getAttendanceDetail(String usercode, UserLoggingsPojo custdetails) {
		return obj.getAttendanceDetail(usercode,custdetails);
		}

	public List<ExaminationDetailsVo> getDeclaredExam(String usercode, UserLoggingsPojo custdetails, String accYears) {
		return obj.getDeclaredExam(usercode,custdetails,accYears);
		}
}
