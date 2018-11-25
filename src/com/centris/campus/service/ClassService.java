package com.centris.campus.service;
import java.util.List;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.UserLoggingsPojo;

public interface ClassService{
	
	List<ClassPojo> getClassDetails(String schoolLocation, UserLoggingsPojo custdetails);
	public String createClass(ClassPojo classPojo,String createUser, String vehiclecode, UserLoggingsPojo userLoggingsVo);
	public List<ClassPojo> getStreamDetailService(String school, UserLoggingsPojo userLoggingsVo);
	public String classNameCheck(ClassPojo classPojo, UserLoggingsPojo userLoggingsVo);
	public String updateclassNameCheck(ClassPojo classPojo, UserLoggingsPojo userLoggingsVo);
	public ClassPojo editClass(String classCode, String custid, UserLoggingsPojo dbdetails);
	public boolean deleteClass(ClassPojo classPojo, UserLoggingsPojo userLoggingsVo);
	public boolean updateClass(ClassPojo classPojo);
	public List<ClassPojo> searchClassDetails(String searchText, UserLoggingsPojo custdetails);


}
