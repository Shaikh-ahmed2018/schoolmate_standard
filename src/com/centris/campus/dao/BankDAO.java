package com.centris.campus.dao;
import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.BankVO;
public interface BankDAO {

	String saveBank(BankVO vo, String usercode, UserLoggingsPojo custdetails);

	String validateBankName(String name, UserLoggingsPojo custdetails);

	List<BankVO> getBankList(UserLoggingsPojo custdetails);

	BankVO editBankDetail(String bankId, UserLoggingsPojo custdetails);

	String removeBank(String[] bankId, UserLoggingsPojo custdetails, String reason,String status, String log_audit_session, String button);

	List<BankVO> getSearchBankList(String searchText, UserLoggingsPojo custdetails, String status);

}
