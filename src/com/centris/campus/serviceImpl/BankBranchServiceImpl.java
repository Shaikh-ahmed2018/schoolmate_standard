package com.centris.campus.serviceImpl;

import java.util.List;

import com.centris.campus.daoImpl.BankBranchDAOImpl;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.BankBranchService;
import com.centris.campus.vo.BankBranchVO;



public class BankBranchServiceImpl implements BankBranchService {

	@Override
	public String checkBranchName(String name, UserLoggingsPojo custdetails) {
		return new BankBranchDAOImpl().checkBranchName(name,custdetails);
	}

	public String saveBranchDetails(BankBranchVO vo, String usercode, UserLoggingsPojo custdetails) {

		return new BankBranchDAOImpl().saveBranchDetails(vo,usercode,custdetails);
	}

	@Override
	public List<BankBranchVO> getBranchList(UserLoggingsPojo custdetails) {
		
		return new BankBranchDAOImpl().getBranchList(custdetails);
	}

	public String removeBranch(String[] removeId, UserLoggingsPojo custdetails, String status, String reason, String button, String log_audit_session) {
		return new BankBranchDAOImpl().removeBranch(removeId,custdetails,status,reason,button,log_audit_session);
	}

	public BankBranchVO editBranchGet(String branchId, UserLoggingsPojo custdetails) {
		return new BankBranchDAOImpl().editBranchGet(branchId,custdetails);
	}

	public List<BankBranchVO> getSearchBranchList(String searchText, UserLoggingsPojo custdetails, String status) {
		return new BankBranchDAOImpl().getSearchBranchList(searchText,custdetails,status);
	}

	
	

	

}
