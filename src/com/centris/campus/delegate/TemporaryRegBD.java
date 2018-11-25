package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.TempregServiceImpl;
import com.centris.campus.vo.Issuedmenuvo;

public class TemporaryRegBD {
	
	public ArrayList<Issuedmenuvo> getissuedforms(String searchtext, UserLoggingsPojo custdetails, String location) {
		TempregServiceImpl	service = new TempregServiceImpl();
		return service.getissuedforms(searchtext,custdetails, location);

	}

	public String insertapprovedlist(String idList, String reason, String othereason, String mobile_number,String log_audit_session, UserLoggingsPojo pojo, String locname) {
		 
		TempregServiceImpl insertapp=new TempregServiceImpl();
		return insertapp.insertapprovedlist(idList,reason,othereason,mobile_number,log_audit_session,pojo,locname);
	}

	public ArrayList<Issuedmenuvo> getApprovedForms(String searchtext, UserLoggingsPojo userLoggingsVo, String accYear, String schoolLocation) {
		 
		TempregServiceImpl apprapp=new TempregServiceImpl();

		return apprapp.getApprovedForms(searchtext,userLoggingsVo,accYear,schoolLocation);
	}

	public String insertrejectedList(String idList, String rejectreason, String otherrsn,String log_audit_session, UserLoggingsPojo userLoggingsVo, String approveSt) {
	TempregServiceImpl reject = new TempregServiceImpl();
	 return reject.insertrejectedList(idList,rejectreason,otherrsn,log_audit_session,userLoggingsVo,approveSt);
		
	}

	public ArrayList<Issuedmenuvo> getRejectedlist(String searchtext, UserLoggingsPojo custdetails, String accYear, String schoolLocation) {
		// TODO Auto-generated method stub
		TempregServiceImpl rejectlist = new TempregServiceImpl();
		return rejectlist.getRejectedlist(searchtext,custdetails,accYear,schoolLocation);
	}

	public ArrayList<Issuedmenuvo> sendmailtoparents() {
		// TODO Auto-generated method stub
		TempregServiceImpl rejectlist = new TempregServiceImpl();
		return rejectlist.sendmailtoparents();
	}

	

	public Issuedmenuvo getIssueddetails(Issuedmenuvo obj1) {
		 
		TempregServiceImpl issuedlist = new TempregServiceImpl();

		return issuedlist.getIssueddetails(obj1);
	}

	public Issuedmenuvo editIssuedForm(String edit, UserLoggingsPojo userLoggingsVo) {
		 
		TempregServiceImpl editissued = new TempregServiceImpl();

		return editissued.editIssuedForm(edit,userLoggingsVo);
	}

	public List<Issuedmenuvo> searchadmformDetails(String searchName, UserLoggingsPojo custdetails) {
		 
		TempregServiceImpl searchissued = new TempregServiceImpl();

		return searchissued.searchadmformDetails(searchName,custdetails);
	}

	public List<Issuedmenuvo> searchapproveformDetails(String obj1) {
		 
		TempregServiceImpl searchapproved = new TempregServiceImpl();

		return searchapproved.searchapproveformDetails(obj1);
	}

	public List<Issuedmenuvo> searchrejectformDetails(String searchName) {
		
		TempregServiceImpl searchreject = new TempregServiceImpl();

		return searchreject.searchrejectformDetails(searchName);
	}

	public Issuedmenuvo apprIssuedForm(String edit, UserLoggingsPojo userLoggingsVo) {
		TempregServiceImpl apprforms = new TempregServiceImpl();

		return apprforms.apprIssuedForm(edit,userLoggingsVo);
	}

	public Issuedmenuvo rejectFormdetails(String edit, UserLoggingsPojo userLoggingsVo) {
		TempregServiceImpl rejectforms = new TempregServiceImpl();

		return rejectforms.rejectFormdetails(edit,userLoggingsVo);
		
	}

	public String insertcancelledlist(String idList, String reason, String canreason,String log_audit_session, UserLoggingsPojo userLoggingsVo, String approveSt) {
		TempregServiceImpl insercancellist = new TempregServiceImpl();

		return insercancellist.insertcancelledlist(idList,reason,canreason,log_audit_session,userLoggingsVo,approveSt);
	}

	public ArrayList<Issuedmenuvo> getCancelledForms(String searchtext, UserLoggingsPojo custdetails, String accYear, String schoolLocation) {
		TempregServiceImpl cancelform = new TempregServiceImpl();

		return cancelform.getCancelledForms(searchtext,custdetails,accYear,schoolLocation);
	}

	public Issuedmenuvo cancelFormdetails(String edit, UserLoggingsPojo userLoggingsVo) {
		TempregServiceImpl cancelformdetails = new TempregServiceImpl();

		return cancelformdetails.cancelFormdetails(edit,userLoggingsVo);
	}

	public ArrayList<Issuedmenuvo> getSubmittedForms(String searchtext, UserLoggingsPojo custdetails) {
		TempregServiceImpl submittedform = new TempregServiceImpl();

		return submittedform.getSubmittedForms(searchtext,custdetails);
	}

	public ArrayList<Issuedmenuvo> getProcessedForms(String searchtext, UserLoggingsPojo custdetails, String accYear, String schoolLocation) {
		TempregServiceImpl processedform = new TempregServiceImpl();

		return processedform.getProcessedForms(searchtext,custdetails,accYear,schoolLocation);
	}

	public Issuedmenuvo submitFormdetails(String edit, UserLoggingsPojo userLoggingsVo) {
		TempregServiceImpl submitform = new TempregServiceImpl();
         return submitform.submitFormdetails(edit,userLoggingsVo);
	}

	public Issuedmenuvo processFormdetails(String edit, UserLoggingsPojo userLoggingsVo) {
		TempregServiceImpl processform = new TempregServiceImpl();
        return processform.processFormdetails(edit,userLoggingsVo);
	}

	public List<Issuedmenuvo> searchcancelformDetails(String searchName,UserLoggingsPojo userLoggingsVo) {
		TempregServiceImpl searchcancel = new TempregServiceImpl();
        return searchcancel.searchcancelformDetails(searchName,userLoggingsVo);
	}

	public List<Issuedmenuvo> searchsubmitformDetails(String searchName, UserLoggingsPojo userLoggingsVo) {
		TempregServiceImpl searchsubmit = new TempregServiceImpl();
        return searchsubmit.searchsubmitformDetails(searchName,userLoggingsVo);
	}

	public List<Issuedmenuvo> searchprocessformDetails(String searchName,UserLoggingsPojo userLoggingsVo) {
		TempregServiceImpl searchprocess = new TempregServiceImpl();
        return searchprocess.searchprocessformDetails(searchName,userLoggingsVo);
	}

	public String getApprovedFormsCount(String academic_year, UserLoggingsPojo userLoggingsVo, String location_id) {
		TempregServiceImpl searchprocess = new TempregServiceImpl();
		return searchprocess.getApprovedFormsCount( academic_year,  userLoggingsVo,location_id);
	}

}
