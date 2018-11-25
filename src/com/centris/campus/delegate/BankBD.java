package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.BankService;
import com.centris.campus.serviceImpl.BankServiceImpl;
import com.centris.campus.vo.BankVO;



public class BankBD {
	
	BankService service = new BankServiceImpl();
	
	public String saveBank(BankVO vo, String usercode, UserLoggingsPojo custdetails) {
		
		return service.saveBank(vo,usercode,custdetails);
	}

	public String validateBankName(String name, UserLoggingsPojo custdetails) {
		return service.validateBankName(name,custdetails);
	}

	public List<BankVO> getBankList(UserLoggingsPojo custdetails) {
		return service.getBankList(custdetails);
	}

	public BankVO editBankDetail(String bankId, UserLoggingsPojo custdetails) {
		return service.editBankDetail(bankId,custdetails);
	}

	public String removeBank(String[] bankId, UserLoggingsPojo custdetails, String reason, String status, String log_audit_session, String button) {
		return  service.removeBank(bankId,custdetails,reason,status,log_audit_session,button); 
		
	}
	
	public List<BankVO> getSearchBankList(String searchText, UserLoggingsPojo custdetails, String status) {
		return  service.getSearchBankList(searchText,custdetails,status); 
		
	}
	
}
