package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.BankBranchServiceImpl;
import com.centris.campus.vo.BankBranchVO;



public class BankBranchBD {

	public String checkBranchName(String name, UserLoggingsPojo custdetails) {
		return new BankBranchServiceImpl().checkBranchName(name,custdetails);
	}

	public String saveBranchDetails(BankBranchVO vo, String usercode, UserLoggingsPojo custdetails) {
		return new BankBranchServiceImpl().saveBranchDetails(vo,usercode,custdetails);
	}

	public List<BankBranchVO> getBranchList(UserLoggingsPojo custdetails) {
		return new BankBranchServiceImpl().getBranchList(custdetails);
	}

	public String removeBranch(String[] removeId, UserLoggingsPojo custdetails, String status, String reason, String button, String log_audit_session) {
		return new BankBranchServiceImpl().removeBranch(removeId,custdetails,status,reason,button,log_audit_session);
	}

	public BankBranchVO editBranchGet(String branchId, UserLoggingsPojo custdetails) {
		return new BankBranchServiceImpl().editBranchGet(branchId,custdetails);
	}

	public List<BankBranchVO> getSearchBranchList(String searchText, UserLoggingsPojo custdetails, String status) {
		return new BankBranchServiceImpl().getSearchBranchList(searchText,custdetails,status);
	}



	

}
