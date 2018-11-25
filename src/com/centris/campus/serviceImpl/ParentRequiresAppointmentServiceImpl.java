package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.dao.ParentRequiresAppointmentDAO;
import com.centris.campus.daoImpl.ParentRequiresAppointmentDAOIMPL;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ParentRequiresAppointmentService;
import com.centris.campus.vo.AryasmartschoolVo;
import com.centris.campus.vo.ParentRequiresAppointmentVO;
import com.centris.campus.vo.ThirdformformatVO;
import com.centris.campus.vo.secadmissionformformatVO;
import com.centris.campus.vo.SecondAdmissionformVo;
import com.centris.campus.vo.ThirdAddmissionApplicationVo;

public class ParentRequiresAppointmentServiceImpl implements
		ParentRequiresAppointmentService {
	
	ParentRequiresAppointmentDAO dao = new ParentRequiresAppointmentDAOIMPL();

	public List<ParentRequiresAppointmentVO> getAdmisssionProcessingListDetails(UserLoggingsPojo userLoggingsVo) {
		
		return dao.getAdmisssionProcessingListDetails(userLoggingsVo);
	}

	
	public ParentRequiresAppointmentVO EditingForAdmissionApproval(
			ParentRequiresAppointmentVO detailsVo) {
		
		return dao.EditingForAdmissionApproval(detailsVo);
	}

	
	public String UpdatingFirstLevelAdmissionApproval(
			ParentRequiresAppointmentVO detailsVo) {
		
		return dao.UpdatingFirstLevelAdmissionApproval(detailsVo);
	}

	
	public String InsertTemporaryStudentRegistration(ParentRequiresAppointmentForm parentform,String log_audit_session,UserLoggingsPojo userLoggingsVo) {
		
		return dao.InsertTemporaryStudentRegistration(parentform,log_audit_session, userLoggingsVo);
	}

	
	public List<ParentRequiresAppointmentVO> searchadmissionsList(
			String searchName,UserLoggingsPojo userLoggingsVo) {
		
		return dao.searchadmissionsList(searchName,userLoggingsVo);
	}

	
	public String DeleteParentRequiresAppointment(ParentRequiresAppointmentVO vo) {
		
		return dao.DeleteParentRequiresAppointment(vo);
	}

	
	public List<ParentRequiresAppointmentVO> CalledForEvaluationList(UserLoggingsPojo userLoggingsVo) {
		return dao.CalledForEvaluationList(userLoggingsVo);
	}

	
	public ParentRequiresAppointmentVO EditingForCalledAdmissionDetails(
			ParentRequiresAppointmentVO detailsVo,UserLoggingsPojo userLoggingsVo) {
		
		return dao.EditingForCalledAdmissionDetails(detailsVo,userLoggingsVo);
	}

	
	public String UpdatingCalledForEvaluationStatus(
			ParentRequiresAppointmentVO details) {
		
		return dao.UpdatingCalledForEvaluationStatus(details);
	}

	
	public List<ParentRequiresAppointmentVO> searchCalledForEvaluationList(
			String searchName,UserLoggingsPojo userLoggingsVo) {
		
		return dao.searchCalledForEvaluationList(searchName,userLoggingsVo);
	}


	@Override
	public List<ParentRequiresAppointmentVO> FinalAdmisssionList(UserLoggingsPojo userLoggingsVo) {
		 
		return dao.FinalAdmisssionList(userLoggingsVo);
	}


	@Override
	public ParentRequiresAppointmentVO EditingForConfirmingAdmissionDetails(
			ParentRequiresAppointmentVO detailsVo) {
		 
		return dao.EditingForConfirmingAdmissionDetails(detailsVo);
	}


	@Override
	public String UpdatingFinalApprovalAdmissionStatus(
			ParentRequiresAppointmentVO details,UserLoggingsPojo userLoggingsVo) {
		 
		return dao.UpdatingFinalApprovalAdmissionStatus(details, userLoggingsVo);
	}



	@Override
	public ArrayList<AryasmartschoolVo> getimageName(String id) {
		 
		return dao.getimageName(id);
	}


	@Override
	public ArrayList<SecondAdmissionformVo> downloadsecadmissionapplication(String parameter) {
		return dao.downloadsecadmissionapplication(parameter);
	}




	@Override
	public String Insertthirdadmissionformat(
			ParentRequiresAppointmentForm parentform,UserLoggingsPojo userLoggingsVo) {
		 
		return dao.Insertthirdadmissionformat(parentform, userLoggingsVo);
	}


	@Override
	public List<secadmissionformformatVO> getsecformadmissiondetails(UserLoggingsPojo custdetails) {
	 
		return dao.getsecformadmissiondetails(custdetails);
	}


	@Override
	public List<ThirdformformatVO> getthirdadmissiondetailslist() {
		 
		return dao.getthirdadmissiondetailslist();
	}


	@Override
	public ArrayList<ThirdAddmissionApplicationVo> downloadthirdAppform(String param) {
		return dao.downloadthirdAppform(param);
	}


	@Override
	public String getsecform() {
		 
		return dao.getsecform();
	}


	@Override
	public List<ParentRequiresAppointmentVO> getAdmissionRegDetails(ParentRequiresAppointmentVO appointmentVo,UserLoggingsPojo userLoggingsVo) {
		 
		return dao.getAdmissionRegDetails(appointmentVo,userLoggingsVo);
	}


	@Override
	public String getValidateAdmissionNo(ParentRequiresAppointmentVO appointmentVo) {
		 
		return dao.getValidateAdmissionNo(appointmentVo);
	}


	@Override
	public ParentRequiresAppointmentVO getSchoolDetails(String enquiryid,UserLoggingsPojo userLoggingsVo) {
		return dao.getSchoolDetails(enquiryid,userLoggingsVo);
	}




}
