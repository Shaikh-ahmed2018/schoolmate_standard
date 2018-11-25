package com.centris.campus.serviceImpl;

import java.util.List;

import com.centris.campus.daoImpl.feeCollectionNewDaoImpl;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.StudentRegistrationVo;

public class feeCollectionNewServiceImpl extends FeeCollectionVo {

	public FeeCollectionVo getNewFeeCollectionAmount(String feeCodeDetails, UserLoggingsPojo userLoggingsVo) {
		return new feeCollectionNewDaoImpl().getNewFeeCollectionAmount(feeCodeDetails,userLoggingsVo);
	}

	public FeeCollectionVo paymenthistory(String feeCodeDetails, UserLoggingsPojo userLoggingsVo) {
		return new feeCollectionNewDaoImpl().paymenthistory(feeCodeDetails,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentDetailsByJs(StudentRegistrationVo data, UserLoggingsPojo userLoggingsVo) {
		return new feeCollectionNewDaoImpl().getStudentDetailsByJs(data,userLoggingsVo);
	}

	public String paybalFees(FeeCollectionVo vo, UserLoggingsPojo userLoggingsVo) {
		return new feeCollectionNewDaoImpl().paybalFees(vo,userLoggingsVo);
	}

}
