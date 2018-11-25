package com.centris.campus.dao;
import java.util.List;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.UserLoggingsPojo;


public interface ClassDao {
	
	public List<ClassPojo> getClassDetails(String schoolLocation, UserLoggingsPojo custdetails) ;
	public String createClass(ClassPojo classPojo,String createUser, String classcode, UserLoggingsPojo userLoggingsVo);
	List<ClassPojo> getStreamDetailDao(String school, UserLoggingsPojo userLoggingsVo);
	public String classNameCheck(ClassPojo classPojo, UserLoggingsPojo userLoggingsVo);
	public ClassPojo editClass(String classCode, String locId, UserLoggingsPojo dbdetails);
	public boolean updateclassNameCheck(ClassPojo classPojo);
	public boolean deleteClass(ClassPojo classPojo, UserLoggingsPojo userLoggingsVo);
	public boolean updateClass(ClassPojo classPojo);
	public List<ClassPojo> searchClassDetails(String  searchText, UserLoggingsPojo custdetails);

}
