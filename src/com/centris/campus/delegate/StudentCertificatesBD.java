package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.StudentCertificatesPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StudentCertificatesService;
import com.centris.campus.serviceImpl.StudentCertificatesServiceImpl;
import com.centris.campus.vo.StudentCertificatesVo;
import com.centris.campus.vo.StudentRegistrationVo;

public class StudentCertificatesBD {

	static StudentCertificatesService service;
	
	static{
		 service = new StudentCertificatesServiceImpl();
	}
	
	public ArrayList<StudentCertificatesVo> getStudentsDetails(String loc, String acyear, String status, UserLoggingsPojo cpojo) {
		return service.getStudentsDetails(loc,acyear,status,cpojo);
	}

	public StudentCertificatesVo getStudentCertificate(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo) {
		return service.getStudentCertificate(pojo,cpojo);
	}

	public String saveAgeCertificateData(StudentCertificatesPOJO pojo, UserLoggingsPojo userLoggingsVo) {
		return service.saveAgeCertificateData(pojo,userLoggingsVo);
	}

	public ArrayList<StudentCertificatesVo> getissueddetails(StudentCertificatesPOJO pojo, UserLoggingsPojo userLoggingsVo) {
		return service.getissueddetails(pojo,userLoggingsVo);
	}

	public String saveBonafiedCertificateData(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo) {
		return service.saveBonafiedCertificateData(pojo,cpojo);
	}

	public ArrayList<StudentCertificatesVo> getbonafiedissueddetails(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo) {
		return service.getbonafiedissueddetails(pojo,cpojo);
	}

	public String saveCounductedCertificateData(StudentCertificatesPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		return service.saveCounductedCertificateData(pojo,userLoggingsVo);
	}

	public ArrayList<StudentCertificatesVo> getconductissueddetails(StudentCertificatesPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		return service.getconductissueddetails(pojo,userLoggingsVo);
	}

	public String saveCourseCounductedCertificateData(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo) {
		return service.saveCourseCounductedCertificateData(pojo,cpojo);
	}

	public ArrayList<StudentCertificatesVo> getcourseconductissueddetails(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo) {
		return service.getcourseconductissueddetails(pojo,cpojo);
	}

	public String saveStudentVisaCertificateData(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo) {
		return service.saveStudentVisaCertificateData(pojo,cpojo);
	}

	public ArrayList<StudentCertificatesVo> getstudentvisaissueddetails(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo) {
		return service.getstudentvisaissueddetails(pojo,cpojo);
	}

	public ArrayList<StudentCertificatesVo> displayageissueddetails(String stuid, String agecetiid, String selection, UserLoggingsPojo cpojo) {
		return service.displayageissueddetails(stuid,agecetiid,selection,cpojo);
	}

	public ArrayList<StudentCertificatesVo> displayconductdetails(String stuid, String agecetiid, String selection, UserLoggingsPojo cpojo) {
		return service.displayconductdetails(stuid,agecetiid,selection,cpojo);
	}

	public ArrayList<StudentCertificatesVo> displaystudentvisadetails(String stuid, String agecetiid, UserLoggingsPojo cpojo) {
		return service.displaystudentvisadetails(stuid,agecetiid,cpojo);
	}

	public ArrayList<StudentRegistrationVo> getDetalofStu(String accyear,
			String stuname, String admissionno) {
		return null;
		//return service.getDetalofStu(accyear,stuname,admissionno);
		}

}
