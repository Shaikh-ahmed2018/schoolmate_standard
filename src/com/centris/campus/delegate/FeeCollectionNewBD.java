package com.centris.campus.delegate;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.feeCollectionNewServiceImpl;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.StudentRegistrationVo;

public class FeeCollectionNewBD {

	public FeeCollectionVo getNewFeeCollectionAmount(String feeCodeDetails, UserLoggingsPojo userLoggingsVo) {
		return new feeCollectionNewServiceImpl().getNewFeeCollectionAmount(feeCodeDetails,userLoggingsVo);
	}

	public FeeCollectionVo paymenthistory(String feeCodeDetails, UserLoggingsPojo userLoggingsVo) {
		return new feeCollectionNewServiceImpl().paymenthistory(feeCodeDetails,userLoggingsVo);
	}

	public java.util.List<StudentRegistrationVo> getStudentDetailsByJs(StudentRegistrationVo data, UserLoggingsPojo userLoggingsVo) {
		return new feeCollectionNewServiceImpl().getStudentDetailsByJs(data,userLoggingsVo);
	}

	public String paybalFees(FeeCollectionVo vo, UserLoggingsPojo userLoggingsVo) {
		return new feeCollectionNewServiceImpl().paybalFees(vo,userLoggingsVo);
	}

}
