package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import java.util.List;
import com.centris.campus.daoImpl.FormsManagementdaoImpl;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.Issuedmenuvo;


public class TempregServiceImpl {

	public ArrayList<Issuedmenuvo> getissuedforms(String searchtext, UserLoggingsPojo custdetails, String location) {
		FormsManagementdaoImpl	dao= new FormsManagementdaoImpl();
		return dao.getissuedforms(searchtext,custdetails, location);
	}

	public String insertapprovedlist(String idList, String reason, String othereason, String mobile_number,String log_audit_session, UserLoggingsPojo pojo, String locname) {
		FormsManagementdaoImpl	dao= new FormsManagementdaoImpl();
		return  dao.insertapprovedlist(idList,reason,othereason,mobile_number,log_audit_session,pojo,locname);

	}

	public ArrayList<Issuedmenuvo> getApprovedForms(String searchtext, UserLoggingsPojo userLoggingsVo, String accYear, String schoolLocation) {
		FormsManagementdaoImpl fdao= new FormsManagementdaoImpl();
		return fdao.getApprovedForms(searchtext,userLoggingsVo,accYear,schoolLocation); 
	}

	public String insertrejectedList(String idList, String rejectreason, String otherrsn,String log_audit_session, UserLoggingsPojo userLoggingsVo, String approveSt) {
		FormsManagementdaoImpl rlist=new FormsManagementdaoImpl();
		return rlist.insertrejectedList(idList,rejectreason,otherrsn,log_audit_session,userLoggingsVo,approveSt);
		
	}

	public ArrayList<Issuedmenuvo> getRejectedlist(String searchtext, UserLoggingsPojo custdetails, String accYear, String schoolLocation) {
		FormsManagementdaoImpl rejectlist=new FormsManagementdaoImpl();

		return rejectlist.getRejectedlist(searchtext,custdetails,accYear,schoolLocation);
	}

	public ArrayList<Issuedmenuvo> sendmailtoparents() {
		FormsManagementdaoImpl sendmail=new FormsManagementdaoImpl();
                return sendmail.sendmailtoparents();
	}


	public synchronized Issuedmenuvo getIssueddetails(Issuedmenuvo obj1) {
		FormsManagementdaoImpl issuedForm=new FormsManagementdaoImpl();
		return issuedForm.getIssueddetails(obj1);
		}

	public Issuedmenuvo editIssuedForm(String edit, UserLoggingsPojo userLoggingsVo) {
		FormsManagementdaoImpl editissuedform=new FormsManagementdaoImpl();

		return editissuedform.editIssuedForm(edit,userLoggingsVo);
	}

	public List<Issuedmenuvo> searchadmformDetails(String searchName, UserLoggingsPojo custdetails) {
		FormsManagementdaoImpl searchissuedform=new FormsManagementdaoImpl();

		return searchissuedform.searchadmformDetails(searchName,custdetails);
	}

	public List<Issuedmenuvo> searchapproveformDetails(String searchName) {
		FormsManagementdaoImpl searchapproveform=new FormsManagementdaoImpl();

		return searchapproveform.searchapproveformDetails(searchName);
	}

	public List<Issuedmenuvo> searchrejectformDetails(String searchName) {
		FormsManagementdaoImpl rejectapproveform=new FormsManagementdaoImpl();

		return rejectapproveform.searchrejectformDetails(searchName);
	}

	public Issuedmenuvo apprIssuedForm(String edit, UserLoggingsPojo userLoggingsVo) {
		FormsManagementdaoImpl detailsaprform=new FormsManagementdaoImpl();

		return detailsaprform.apprIssuedForm(edit,userLoggingsVo);
	}

	public Issuedmenuvo rejectFormdetails(String edit, UserLoggingsPojo userLoggingsVo) {
		FormsManagementdaoImpl detailsrejectform=new FormsManagementdaoImpl();

		return detailsrejectform.rejectFormdetails(edit,userLoggingsVo);
	}

	public String insertcancelledlist(String idList, String reason, String canreason,String log_audit_session, UserLoggingsPojo userLoggingsVo, String approveSt) {
		FormsManagementdaoImpl insertcancelledlist=new FormsManagementdaoImpl();

		return insertcancelledlist.insertcancelledlist(idList,reason,canreason,log_audit_session,userLoggingsVo,approveSt);
	}

	public ArrayList<Issuedmenuvo> getCancelledForms(String searchtext, UserLoggingsPojo custdetails, String accYear, String schoolLocation) {
		FormsManagementdaoImpl cancelledlist=new FormsManagementdaoImpl();

		return cancelledlist.getCancelledForms(searchtext,custdetails,accYear,schoolLocation);
	}

	public Issuedmenuvo cancelFormdetails(String edit, UserLoggingsPojo userLoggingsVo) {
		FormsManagementdaoImpl canceldetails=new FormsManagementdaoImpl();

		return canceldetails.cancelFormdetails(edit,userLoggingsVo);
	}

	public ArrayList<Issuedmenuvo> getSubmittedForms(String searchtext, UserLoggingsPojo custdetails) {
		FormsManagementdaoImpl submittedform=new FormsManagementdaoImpl();

		return submittedform.getSubmittedForms(searchtext,custdetails);
	}

	public ArrayList<Issuedmenuvo> getProcessedForms(String searchtext, UserLoggingsPojo custdetails, String accYear, String schoolLocation) {
		FormsManagementdaoImpl processed=new FormsManagementdaoImpl();

		return processed.getProcessedForms(searchtext,custdetails,accYear,schoolLocation);
	}

	public Issuedmenuvo submitFormdetails(String edit, UserLoggingsPojo userLoggingsVo) {
		FormsManagementdaoImpl submitted=new FormsManagementdaoImpl();

		return submitted.submitFormdetails(edit,userLoggingsVo);
	}

	public Issuedmenuvo processFormdetails(String edit, UserLoggingsPojo userLoggingsVo) {
		FormsManagementdaoImpl processed=new FormsManagementdaoImpl();

		return processed.processFormdetails(edit,userLoggingsVo);
	}

	public List<Issuedmenuvo> searchcancelformDetails(String searchName, UserLoggingsPojo userLoggingsVo) {
		FormsManagementdaoImpl searchcancel=new FormsManagementdaoImpl();

		return searchcancel.searchcancelformDetails(searchName,userLoggingsVo);
	}

	public List<Issuedmenuvo> searchsubmitformDetails(String searchName, UserLoggingsPojo userLoggingsVo) {
		FormsManagementdaoImpl searchsubmit=new FormsManagementdaoImpl();

		return searchsubmit.searchsubmitformDetails(searchName,userLoggingsVo);
	}

	public List<Issuedmenuvo> searchprocessformDetails(String searchName, UserLoggingsPojo userLoggingsVo) {
		FormsManagementdaoImpl searchprocess=new FormsManagementdaoImpl();

		return searchprocess.searchprocessformDetails(searchName,userLoggingsVo);
	}

	public String getApprovedFormsCount(String academic_year, UserLoggingsPojo userLoggingsVo, String location_id) {
		FormsManagementdaoImpl searchprocess=new FormsManagementdaoImpl();

		return searchprocess.getApprovedFormsCount( academic_year,  userLoggingsVo,  location_id);
	}

	

}
