package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.RelievingOrderPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StaffReleivingOrderService;
import com.centris.campus.serviceImpl.StaffReleivingOrderServiceImpl;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReleivingOrderVo;
import com.centris.campus.vo.UserDetailVO;

public class StaffReleivingReportBD {
	

	public List<AllTeacherDetailsVo> getTeachingListBD(AllTeacherDetailsVo vo,UserLoggingsPojo userLoggingsVo) {
		
		
		StaffReleivingOrderService service = new StaffReleivingOrderServiceImpl();
		
		return service.getTeachingListService(vo,userLoggingsVo);
	}

	public List<AllTeacherDetailsVo> getNonTeachingListBD(AllTeacherDetailsVo vo,UserLoggingsPojo userLoggingsVo) {
		
    StaffReleivingOrderService service = new StaffReleivingOrderServiceImpl();
		
		return service.getNonTeachingListService(vo,userLoggingsVo);
	}

	public List<UserDetailVO> getUsersList(UserLoggingsPojo userLoggingsVo) {
	
		   StaffReleivingOrderService service = new StaffReleivingOrderServiceImpl();
			
			return service.getUsersListService(userLoggingsVo);
	}

	public List<ReleivingOrderVo> getReleivingDetailsBD(RelievingOrderPojo pojo,UserLoggingsPojo userLoggingsVo) {
		
		  StaffReleivingOrderService service = new StaffReleivingOrderServiceImpl();
			
			return service.getReleivingDetailsService(pojo,userLoggingsVo);
	}

	public List<RelievingOrderPojo> SearchRelievingOrder(RelievingOrderPojo pojo, UserLoggingsPojo custdetails) {
		  StaffReleivingOrderService service = new StaffReleivingOrderServiceImpl();
		return service.SearchRelievingOrder(pojo,custdetails);
	}

	public List<RelievingOrderPojo> staffReleivingPDFReport(String[] teachercode, RelievingOrderPojo pojo, UserLoggingsPojo userLoggingsVo) {
		StaffReleivingOrderService service = new StaffReleivingOrderServiceImpl();
		return service.staffReleivingPDFReport(teachercode,pojo,userLoggingsVo);
	}

}
