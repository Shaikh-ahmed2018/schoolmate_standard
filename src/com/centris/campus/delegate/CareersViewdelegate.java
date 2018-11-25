package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.pojo.CareersViewPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.CareersViewService;
import com.centris.campus.serviceImpl.CareersViewServiceImpl;
import com.centris.campus.vo.CareerUpdatePopuVO;
import com.centris.campus.vo.CareersViewVo;

public class CareersViewdelegate {

	public CareersViewdelegate() {
		super();
	}

	CareersViewService carser;
	List<CareersViewVo> careervolist;
	List<CareerUpdatePopuVO> listcareview;

	public List<CareersViewVo> getAllcareerdetails(UserLoggingsPojo userLoggingsVo) throws Exception {

		carser = new CareersViewServiceImpl();
		careervolist = carser.getAllCareersView(userLoggingsVo);

		return careervolist;
	}

	public List<CareersViewVo> getActivecareerdetails() throws Exception {

		carser = new CareersViewServiceImpl();
		careervolist = carser.getCareersView();

		return careervolist;
	}

	public CareersViewVo getValues(CareersViewPojo pojo) {

		carser = new CareersViewServiceImpl();
		CareersViewVo list = carser.getValues(pojo);

		return list;
	}

	public boolean getDelete(CareersViewPojo pojo) {
		boolean result;
		carser = new CareersViewServiceImpl();
		result = carser.getDelete(pojo);
		return result;
	}

	public String addJobs(CareersViewPojo pojo) {

		carser = new CareersViewServiceImpl();

		String success = carser.addJobs(pojo);
		return success;
	}

	public boolean checkTitle(CareersViewPojo pojo) {
		boolean result;
		carser = new CareersViewServiceImpl();
		result = carser.checkTitle(pojo);
		return result;
	}

	public List<CareersViewVo> searchDetails(String name, UserLoggingsPojo userLoggingsVo) {

		carser = new CareersViewServiceImpl();
		List<CareersViewVo> list = carser.searchDetails(name,userLoggingsVo);

		return list;
	}
}
