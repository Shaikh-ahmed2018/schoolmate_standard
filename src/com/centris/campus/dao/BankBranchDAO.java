package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.BankBranchVO;



public interface BankBranchDAO {
	
	public String checkBranchName(String name,UserLoggingsPojo custdetails);
	public String saveBranchDetails(BankBranchVO vo, String usercode,UserLoggingsPojo custdetails);
	public List<BankBranchVO> getBranchList(UserLoggingsPojo custdetails);
	public String removeBranch(String[] removeId,UserLoggingsPojo custdetails,String status, String reason, String button,String log_audit_session);
	public BankBranchVO editBranchGet(String branchId, UserLoggingsPojo custdetails);
}
