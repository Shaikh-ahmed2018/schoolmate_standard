package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;
import com.centris.campus.pojo.StudentPromotionPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AcadamicYearVO2;
import com.centris.campus.vo.StudentPramotionVO;

public interface StudentPramotionService {
	public List<AcadamicYearVO2> getAcadamicYear(UserLoggingsPojo userLoggingsVo);

	public List<StudentPramotionVO> getStudentData(String acadamicYear,
			String section,UserLoggingsPojo userLoggingsVo);

	public StudentPramotionVO insertStudentPromotion(
			StudentPromotionPOJO studentPramotionPOJO,UserLoggingsPojo userLoggingsVo);

	public ArrayList<StudentPramotionVO> getpromotionslist(UserLoggingsPojo userLoggingsVo);
}
