package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.AcademicYearPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.AcademicYearMasterService;
import com.centris.campus.serviceImpl.AcademicYearMasterServiceImpl;
import com.centris.campus.vo.AcademicYearVO;

public class AcademicYearMasterBD {

	AcademicYearMasterService academicYear_obj = new AcademicYearMasterServiceImpl();

	public ArrayList<AcademicYearVO> getAcademicYearDetails(UserLoggingsPojo custdetails) {
		return academicYear_obj.getAcademicYearDetails(custdetails);
	}

	public String createAcademicYear(AcademicYearPojo ACY_pojo, UserLoggingsPojo userLoggingsVo) {
		return academicYear_obj.createAcademicYear(ACY_pojo,userLoggingsVo);
	}

	public boolean deleteAcademicYear(AcademicYearVO vo, UserLoggingsPojo userLoggingsVo) {
		return academicYear_obj.deleteAcademicYear(vo,userLoggingsVo);
	}

	public ArrayList<AcademicYearVO> editAcademicYear(String ACY_code, UserLoggingsPojo userLoggingsVo) {
		return academicYear_obj.editAcademicYear(ACY_code,userLoggingsVo);
	}

	public String accyearNameCheck(AcademicYearPojo ACY_pojo, UserLoggingsPojo userLoggingsVo) {
		return academicYear_obj.accyearNameCheck(ACY_pojo,userLoggingsVo);
	}

	public ArrayList<AcademicYearVO> searchAcademicYearDetails(String searchname, UserLoggingsPojo custdetails) {
		return academicYear_obj.searchAcademicYearDetails(searchname,custdetails);
	}

	public List<AcademicYearVO> getAccYear() throws Exception {
		return academicYear_obj.getAccYear();
	}

	public ArrayList<AcademicYearPojo> getAcademicYearList() {
		return academicYear_obj.getAcademicYearList();
	}

	public ArrayList<AcademicYearVO> getAcademicYearDetailsByBranchId(
			String brancid) {
		return academicYear_obj.getAcademicYearDetailsByBranchId(brancid);
	}

	public ArrayList<AcademicYearVO> getAcademicYearDetailsbysearch(
			String brancid, String searchname) {
		return academicYear_obj.getAcademicYearDetailsbysearch(brancid,
				searchname);
	}

	public ArrayList<AcademicYearVO> AcademicYeardetailsListBystatus(AcademicYearPojo pojo, UserLoggingsPojo userLoggingsVo) {
		return academicYear_obj.AcademicYeardetailsListBystatus(pojo,userLoggingsVo);
	}
}
