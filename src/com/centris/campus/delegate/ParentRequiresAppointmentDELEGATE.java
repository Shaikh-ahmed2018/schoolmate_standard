package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ParentRequiresAppointmentService;
import com.centris.campus.serviceImpl.ParentRequiresAppointmentServiceImpl;
import com.centris.campus.vo.AryasmartschoolVo;
import com.centris.campus.vo.ParentRequiresAppointmentVO;
import com.centris.campus.vo.ThirdformformatVO;
import com.centris.campus.vo.secadmissionformformatVO;
import com.centris.campus.vo.SecondAdmissionformVo;
import com.centris.campus.vo.ThirdAddmissionApplicationVo;

public class ParentRequiresAppointmentDELEGATE {
	
	ParentRequiresAppointmentService service = new ParentRequiresAppointmentServiceImpl();

	public List<ParentRequiresAppointmentVO> getAdmisssionProcessingListDetails(UserLoggingsPojo userLoggingsVo) {
	 
		return service.getAdmisssionProcessingListDetails(userLoggingsVo);
	}

	public ParentRequiresAppointmentVO EditingForAdmissionApproval(ParentRequiresAppointmentVO detailsVo) {
		 
		return service.EditingForAdmissionApproval(detailsVo);
	}

	public String UpdatingFirstLevelAdmissionApproval(
			ParentRequiresAppointmentVO detailsVo) {
		 
		return service.UpdatingFirstLevelAdmissionApproval(detailsVo);
	}

	public String InsertTemporaryStudentRegistration(ParentRequiresAppointmentForm parentform,String log_audit_session,UserLoggingsPojo userLoggingsVo) {
		 
		return service.InsertTemporaryStudentRegistration(parentform,log_audit_session,userLoggingsVo);
	}

	public List<ParentRequiresAppointmentVO> searchadmissionsList(
			String searchName,UserLoggingsPojo userLoggingsVo) {
		 
		return service.searchadmissionsList(searchName,userLoggingsVo);
	}

	public String DeleteParentRequiresAppointment(ParentRequiresAppointmentVO vo) {
		 
		return service.DeleteParentRequiresAppointment(vo);
	}

	public List<ParentRequiresAppointmentVO> CalledForEvaluationList(UserLoggingsPojo userLoggingsVo) {
		 
		return service.CalledForEvaluationList(userLoggingsVo);
	}

	public ParentRequiresAppointmentVO EditingForCalledAdmissionDetails(
			ParentRequiresAppointmentVO detailsVo,UserLoggingsPojo userLoggingsVo) {
		 
		return service.EditingForCalledAdmissionDetails(detailsVo,userLoggingsVo);
	}

	public String UpdatingCalledForEvaluationStatus(
			ParentRequiresAppointmentVO details) {
		 
		return service.UpdatingCalledForEvaluationStatus(details);
	}

	public List<ParentRequiresAppointmentVO> searchCalledForEvaluationList(
			String searchName,UserLoggingsPojo userLoggingsVo) {
		 
		return service.searchCalledForEvaluationList(searchName,userLoggingsVo);
	}

	public List<ParentRequiresAppointmentVO> FinalAdmisssionList(UserLoggingsPojo userLoggingsVo) {
		 
		return service.FinalAdmisssionList(userLoggingsVo);
	}

	public ParentRequiresAppointmentVO EditingForConfirmingAdmissionDetails(
			ParentRequiresAppointmentVO detailsVo) {
		 
		return service.EditingForConfirmingAdmissionDetails(detailsVo);
	}

	public String UpdatingFinalApprovalAdmissionStatus(
			ParentRequiresAppointmentVO details,UserLoggingsPojo userLoggingsVo) {
		 
		return service.UpdatingFinalApprovalAdmissionStatus(details,userLoggingsVo);
	}


	public ArrayList<AryasmartschoolVo> getimageName(String string) {
		return service.getimageName(string);
	}

	public ArrayList<SecondAdmissionformVo> downloadsecadmissionapplication(String parameter) {
		return service.downloadsecadmissionapplication(parameter);
	}



	public String Insertthirdadmissionformat(
			ParentRequiresAppointmentForm parentform,UserLoggingsPojo userLoggingsVo) {
		 
	    return service.Insertthirdadmissionformat(parentform,userLoggingsVo);
	}

	

	public List<secadmissionformformatVO> getsecformadmissiondetails(UserLoggingsPojo custdetails) {
		 
		return service.getsecformadmissiondetails(custdetails);
	}

	public List<ThirdformformatVO> getthirdadmissiondetailslist() {
		 
		return service.getthirdadmissiondetailslist();
	}

	public ArrayList<ThirdAddmissionApplicationVo> downloadthirdAppform(String parameter) {
		return service.downloadthirdAppform(parameter);
	}

	public String getsecform() {
		 
		return service.getsecform();
	}

	public List<ParentRequiresAppointmentVO> getAdmissionRegDetails(ParentRequiresAppointmentVO appointmentVo,UserLoggingsPojo userLoggingsVo) {
		 
		return service.getAdmissionRegDetails(appointmentVo,userLoggingsVo);
	}

	public String getValidateAdmissionNo(ParentRequiresAppointmentVO appointmentVo) {
		 
		return service.getValidateAdmissionNo(appointmentVo);
	}

	public ParentRequiresAppointmentVO getSchoolDetails(String enquiryid, UserLoggingsPojo userLoggingsVo) {
		return service.getSchoolDetails(enquiryid,userLoggingsVo);
	}
	


}
