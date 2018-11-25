package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.AcademicYearPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AcademicYearVO;

public interface AcademicYearMasterService {

	public ArrayList<AcademicYearVO> getAcademicYearDetails(UserLoggingsPojo custdetails);

	public String createAcademicYear(AcademicYearPojo ACY_pojo, UserLoggingsPojo userLoggingsVo);

	public boolean deleteAcademicYear(AcademicYearVO vo, UserLoggingsPojo userLoggingsVo);

	public ArrayList<AcademicYearVO> editAcademicYear(String ACY_code, UserLoggingsPojo userLoggingsVo);

	public String accyearNameCheck(AcademicYearPojo ACY_pojo, UserLoggingsPojo userLoggingsVo);

	public ArrayList<AcademicYearVO> searchAcademicYearDetails(String searchname, UserLoggingsPojo custdetails);

	public List<AcademicYearVO> getAccYear();

	public ArrayList<AcademicYearPojo> getAcademicYearList();

	public ArrayList<AcademicYearVO> getAcademicYearDetailsByBranchId(
			String brancid);

	public ArrayList<AcademicYearVO> getAcademicYearDetailsbysearch(
			String brancid, String searchname);

	public ArrayList<AcademicYearVO> AcademicYeardetailsListBystatus(AcademicYearPojo pojo, UserLoggingsPojo userLoggingsVo);

}
