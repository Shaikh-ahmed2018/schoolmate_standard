package com.centris.campus.delegate;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SettingsFileUploadPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.StudentExcelUploadPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.SettingsXLUploadServiceImpl;
import com.centris.campus.vo.SettingsFileUploadVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.classVo;
import com.centris.campus.vo.studentExcelUploadVo;

public class SettingsXLUploadBD {



	public Set<StreamDetailsVO> insertStreamXSL(List<StreamDetailsPojo> list, String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo, String currentLocId) {
		return new SettingsXLUploadServiceImpl().insertStreamXSL(list,userId,log_audit_session,userLoggingsVo,currentLocId);
	}

	public Set<classVo> insertClassXSL(List<ClassPojo> list, String userId, String log_audit_session,UserLoggingsPojo userLoggingsVo, String currentLocId) {
		return new SettingsXLUploadServiceImpl().insertClassXSL(list,userId,log_audit_session,userLoggingsVo,currentLocId);
	}

	public Set<classVo> insertDivisionXSL(List<ClassPojo> list, String userId, String log_audit_session, String currentLocId, UserLoggingsPojo custdetails) {
		return new SettingsXLUploadServiceImpl().insertDivisionXSL(list,userId,log_audit_session,currentLocId,custdetails);
	}

	public Set<classVo> insertOccupationXSL(List<ClassPojo> list, String userId,String log_audit_session, UserLoggingsPojo userLoggingsVo, String currentLocId) {
		return new SettingsXLUploadServiceImpl().insertOccupationXSL(list,userId,log_audit_session,userLoggingsVo,currentLocId);
	}

	public Set<classVo> insertReligionXSL(List<ClassPojo> list, String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		return new SettingsXLUploadServiceImpl().insertReligionXSL(list,userId,log_audit_session,userLoggingsVo);
	}

	public Set<classVo> insertCasteXSL(List<ClassPojo> list, String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		return new SettingsXLUploadServiceImpl().insertCasteXSL(list,userId,log_audit_session,userLoggingsVo);
	}

	public Set<classVo> insertSpecXSL(List<ClassPojo> list, String userId,String log_audit_session, UserLoggingsPojo userLoggingsVo, String currentLocId) {
		return new SettingsXLUploadServiceImpl().insertSpecXSL(list,userId,log_audit_session,userLoggingsVo,currentLocId);
	}

	public Set<classVo> insertHolidayXSL(List<ClassPojo> list, String userId, String accyear, String log_audit_session, UserLoggingsPojo userLoggingsVo, String currentLocId) {
		return new SettingsXLUploadServiceImpl().insertHolidayXSL(list,userId,accyear,log_audit_session,userLoggingsVo,currentLocId);
	}
	public Set<studentExcelUploadVo> saveStudentExam(List<StudentExcelUploadPojo> list, String log_audit_session,UserLoggingsPojo userLoggingsVo, UserDetailVO user) throws SQLException {
		return new SettingsXLUploadServiceImpl().saveStudentExam(list,log_audit_session,userLoggingsVo,user);
		}
	public Set<studentExcelUploadVo> SaveGradeSetting(List<StudentExcelUploadPojo> list, String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo, String currentLoc) {
		return new SettingsXLUploadServiceImpl().SaveGradeSetting(list,userId,log_audit_session,userLoggingsVo,currentLoc);
	}

	public Set<classVo> insertSubjectXSL(List<ClassPojo> list, String userId, String log_audit_session,
			UserLoggingsPojo userLoggingsVo, String currentLoc) {
		return new SettingsXLUploadServiceImpl().insertSubjectXLS(list,userId,log_audit_session,userLoggingsVo,currentLoc);
	}

}
