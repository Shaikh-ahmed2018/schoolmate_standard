package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.vo.DriverMasterVO;
import com.centris.campus.vo.ExpenditureVO;
import com.centris.campus.vo.FuelMaintenanceVO;
import com.centris.campus.vo.VehicleDetailsVO;

public interface ExpenditureService {

	public ArrayList<ExpenditureVO> getExpenditureDetails(ExpenditureVO vo);
	

}
