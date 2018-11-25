package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import com.centris.campus.daoImpl.createFeeReportDaoImpl;
import com.centris.campus.vo.feeReportVO;

public class FeeReportServiceImpl {

	public static ArrayList<feeReportVO> expandAll(feeReportVO obj) {
		return createFeeReportDaoImpl.expandAll(obj);
	}

	public static ArrayList<feeReportVO> getclasssectionDetails(feeReportVO obj) {
		return createFeeReportDaoImpl.getclasssectionDetails(obj);
	}


	

}
