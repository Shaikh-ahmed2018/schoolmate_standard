package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.OnlinePaymentVo;

public interface OnlineFeePaymentDAO {

	List<OnlinePaymentVo> getStudentOnlineFeePaymentDetails(String userType,
			String userCode, UserLoggingsPojo custdetails);

}
