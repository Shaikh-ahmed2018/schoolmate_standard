package com.centris.campus.serviceImpl;

import java.util.ArrayList;

import com.centris.campus.dao.StudentCertificatesDao;
import com.centris.campus.daoImpl.StudentCertificatesDaoImpl;
import com.centris.campus.pojo.StudentCertificatesPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StudentCertificatesService;
import com.centris.campus.vo.StudentCertificatesVo;

public class StudentCertificatesServiceImpl implements StudentCertificatesService{

	static StudentCertificatesDao dao;
	
	static{
		dao = new StudentCertificatesDaoImpl();
	}
	@Override
	public ArrayList<StudentCertificatesVo> getStudentsDetails(String loc,String acyear,String status,UserLoggingsPojo cpojo) {
		return dao.getStudentsDetails(loc,acyear,status, cpojo);
	}
	@Override
	public StudentCertificatesVo getStudentCertificate(StudentCertificatesPOJO pojo,UserLoggingsPojo cpojo) {
		return dao.getStudentCertificate(pojo, cpojo);
	}
	@Override
	public String saveAgeCertificateData(StudentCertificatesPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		return dao.saveAgeCertificateData(pojo, userLoggingsVo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> getissueddetails(StudentCertificatesPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		return dao.getissueddetails(pojo,userLoggingsVo);
	}
	@Override
	public String saveBonafiedCertificateData(StudentCertificatesPOJO pojo,UserLoggingsPojo cpojo) {
		return dao.saveBonafiedCertificateData(pojo,cpojo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> getbonafiedissueddetails(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo) {
		return dao.getbonafiedissueddetails(pojo,cpojo);
	}
	@Override
	public String saveCounductedCertificateData(StudentCertificatesPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		return dao.saveCounductedCertificateData(pojo, userLoggingsVo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> getconductissueddetails(StudentCertificatesPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		return dao.getconductissueddetails(pojo,userLoggingsVo);
	}
	@Override
	public String saveCourseCounductedCertificateData(StudentCertificatesPOJO pojo, UserLoggingsPojo cpojo) {
		return dao.saveCourseCounductedCertificateData(pojo,cpojo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> getcourseconductissueddetails(StudentCertificatesPOJO pojo,UserLoggingsPojo cpojo) {
		return dao.getcourseconductissueddetails(pojo,cpojo);
	}
	@Override
	public String saveStudentVisaCertificateData(StudentCertificatesPOJO pojo,UserLoggingsPojo cpojo) {
		return dao.saveStudentVisaCertificateData(pojo,cpojo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> getstudentvisaissueddetails(StudentCertificatesPOJO pojo,UserLoggingsPojo cpojo) {
		return dao.getstudentvisaissueddetails(pojo,cpojo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> displayageissueddetails(String stuid, String agecetiid,String selection,UserLoggingsPojo cpojo) {
		return dao.displayageissueddetails(stuid,agecetiid,selection,cpojo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> displayconductdetails(String stuid, String agecetiid, String selection,UserLoggingsPojo cpojo) {
		return dao.displayconductdetails(stuid,agecetiid,selection,cpojo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> displaystudentvisadetails(String stuid, String agecetiid,UserLoggingsPojo cpojo) {
		return dao.displaystudentvisadetails(stuid,agecetiid,cpojo);
	}
	
	
	
	
}
