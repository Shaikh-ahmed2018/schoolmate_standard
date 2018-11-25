package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.StudentCertificatesPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.StudentCertificatesVo;

public interface StudentCertificatesDao {

	ArrayList<StudentCertificatesVo> getStudentsDetails(String loc, String acyear, String status, UserLoggingsPojo cpojo);

	StudentCertificatesVo getStudentCertificate(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo);

	String saveAgeCertificateData(StudentCertificatesPOJO pojo, UserLoggingsPojo userLoggingsVo);

	ArrayList<StudentCertificatesVo> getissueddetails(StudentCertificatesPOJO pojo, UserLoggingsPojo userLoggingsVo);

	String saveBonafiedCertificateData(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo);

	ArrayList<StudentCertificatesVo> getbonafiedissueddetails(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo);

	String saveCounductedCertificateData(StudentCertificatesPOJO pojo ,UserLoggingsPojo userLoggingsVo);

	ArrayList<StudentCertificatesVo> getconductissueddetails(StudentCertificatesPOJO pojo,UserLoggingsPojo userLoggingsVo);

	String saveCourseCounductedCertificateData(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo);

	ArrayList<StudentCertificatesVo> getcourseconductissueddetails(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo);

	String saveStudentVisaCertificateData(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo);

	ArrayList<StudentCertificatesVo> getstudentvisaissueddetails(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo);

	ArrayList<StudentCertificatesVo> displayageissueddetails(String stuid, String agecetiid, String selection, UserLoggingsPojo cpojo);

	ArrayList<StudentCertificatesVo> displayconductdetails(String stuid, String agecetiid, String selection, UserLoggingsPojo cpojo);

	ArrayList<StudentCertificatesVo> displaystudentvisadetails(String stuid, String agecetiid, UserLoggingsPojo cpojo);

}
