package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AryasmartschoolVo;
import com.centris.campus.vo.ParentRequiresAppointmentVO;
import com.centris.campus.vo.ThirdformformatVO;
import com.centris.campus.vo.secadmissionformformatVO;
import com.centris.campus.vo.SecondAdmissionformVo;
import com.centris.campus.vo.ThirdAddmissionApplicationVo;

public interface ParentRequiresAppointmentService {

	List<ParentRequiresAppointmentVO> getAdmisssionProcessingListDetails(UserLoggingsPojo userLoggingsVo);

	ParentRequiresAppointmentVO EditingForAdmissionApproval(ParentRequiresAppointmentVO detailsVo);

	String UpdatingFirstLevelAdmissionApproval(ParentRequiresAppointmentVO detailsVo);

	String InsertTemporaryStudentRegistration(ParentRequiresAppointmentForm parentform, String log_audit_session,UserLoggingsPojo userLoggingsVo);

	List<ParentRequiresAppointmentVO> searchadmissionsList(String searchName,UserLoggingsPojo userLoggingsVo);

	String DeleteParentRequiresAppointment(ParentRequiresAppointmentVO vo);

	List<ParentRequiresAppointmentVO> CalledForEvaluationList(UserLoggingsPojo userLoggingsVo);

	ParentRequiresAppointmentVO EditingForCalledAdmissionDetails(ParentRequiresAppointmentVO detailsVo,UserLoggingsPojo userLoggingsVo);

	String UpdatingCalledForEvaluationStatus(ParentRequiresAppointmentVO details);

	List<ParentRequiresAppointmentVO> searchCalledForEvaluationList(
			String searchName, UserLoggingsPojo userLoggingsVo);

	List<ParentRequiresAppointmentVO> FinalAdmisssionList(UserLoggingsPojo userLoggingsVo);

	ParentRequiresAppointmentVO EditingForConfirmingAdmissionDetails(
			ParentRequiresAppointmentVO detailsVo);

	String UpdatingFinalApprovalAdmissionStatus(
			ParentRequiresAppointmentVO details,UserLoggingsPojo userLoggingsVo);


	ArrayList<AryasmartschoolVo> getimageName(String string);

	ArrayList<SecondAdmissionformVo> downloadsecadmissionapplication(String parameter);



	String Insertthirdadmissionformat(ParentRequiresAppointmentForm parentform,UserLoggingsPojo userLoggingsVo);

	List<secadmissionformformatVO> getsecformadmissiondetails(UserLoggingsPojo custdetails);

	List<ThirdformformatVO> getthirdadmissiondetailslist();

	ArrayList<ThirdAddmissionApplicationVo> downloadthirdAppform(String parameter);

	String getsecform();

	List<ParentRequiresAppointmentVO> getAdmissionRegDetails(ParentRequiresAppointmentVO appointmentVo,UserLoggingsPojo userLoggingsVo);

	String getValidateAdmissionNo(ParentRequiresAppointmentVO appointmentVo);

	ParentRequiresAppointmentVO getSchoolDetails(String enquiryid,UserLoggingsPojo userLoggingsVo);


}
