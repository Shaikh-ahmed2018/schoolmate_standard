package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.FeeStatusReportPojo;
import com.centris.campus.pojo.MarksPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;

public interface ReportsMenuDao {
	
	public ArrayList<ReportMenuVo> getAccYears(UserLoggingsPojo dbdetails);
	public ArrayList<ReportMenuVo> getStream(UserLoggingsPojo dbdetails);
	public ArrayList<ReportMenuVo> getClassesByStream(String streamId,UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getSectionsByClass(String classId, String location, UserLoggingsPojo custdetails);
	public ArrayList<StudentInfoVO> getStudentDetailsReport(ReportMenuForm reform, UserLoggingsPojo dbdetails);
	public ReportMenuVo  getSelectedItems(ReportMenuForm reform, UserLoggingsPojo dbdetails);
	public HashMap<String, ArrayList<FeeReportDetailsVo>> getStdFeeStatusReportDetails(ReportMenuForm reform,UserLoggingsPojo userLoggingsVo);
	public ArrayList<FeeReportDetailsVo> getStdFeeStatusReportDownload(FeeStatusReportPojo reform, UserLoggingsPojo custdetails);
	public HashMap<String, ArrayList<MarksUploadVO>> getStdMarksDetails(ReportMenuForm reform,UserLoggingsPojo userLoggingsVo);
	public  ArrayList<MarksUploadVO> getStdMarksDetailsDownload(MarksPOJO reform, UserLoggingsPojo custdetails);
	public ArrayList<ExaminationDetailsVo> examReportClassWiseDetails(ReportMenuForm reform, UserLoggingsPojo custdetails);
	public ArrayList<StudentInfoVO> geInactivetStudentDetailsReport(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<StudentInfoVO> geInactivetStudentFeeDetailsReport(ReportMenuForm reform, UserLoggingsPojo custdetails);
	public ReportMenuVo  getSelectedoneItems(ReportMenuForm reform, UserLoggingsPojo custdetails);
	public String gettempregid();
	public String getthirdRegNo();
	public ArrayList<ReportMenuVo> getStudentClass(String location, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getstudentDOBWise(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentFatherOccuWise(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentMotherOccuWise(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentDetailsReligionWise(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentCategoryWise(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getclasssectionDetails(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentParentWise(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentList(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentContactDetails(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentStandardWise(ReportMenuVo vo, UserLoggingsPojo custdetails);
	ArrayList<ReportMenuVo> getClassesByStream(String streamId, String location,UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getSectionsByClassLoc(String classId,
			String location, UserLoggingsPojo custid);

	


}
