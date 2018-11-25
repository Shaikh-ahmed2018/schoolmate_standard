package com.centris.campus.serviceImpl;

import java.util.List;

import com.centris.campus.dao.BankDAO;
import com.centris.campus.daoImpl.BankDAOImpl;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.BankService;
import com.centris.campus.vo.BankVO;



public class BankServiceImpl implements BankService{

	BankDAO dao=new BankDAOImpl();
	@Override
	public String saveBank(BankVO vo, String usercode,UserLoggingsPojo custdetails) {
		
		return dao.saveBank(vo,usercode,custdetails);
	}
	@Override
	public String validateBankName(String name,UserLoggingsPojo custdetails) {
		return dao.validateBankName(name,custdetails);
	}
	@Override
	public List<BankVO> getBankList(UserLoggingsPojo custdetails) {
		return dao.getBankList(custdetails);
	}
	@Override
	public BankVO editBankDetail(String bankId,UserLoggingsPojo custdetails) {
		return dao.editBankDetail(bankId,custdetails);
	}
	@Override
	public String removeBank(String[] bankId,UserLoggingsPojo custdetails,String reason,String status, String log_audit_session, String button) {

		return dao.removeBank(bankId,custdetails,reason,status,log_audit_session,button);
	}
	@Override
	public List<BankVO> getSearchBankList(String searchText,UserLoggingsPojo custdetails,String status) {
		
		return dao.getSearchBankList(searchText,custdetails,status);
	}

}
