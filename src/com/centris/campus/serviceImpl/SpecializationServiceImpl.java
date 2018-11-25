package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.SpecializationDao;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.SpecializationDaoImpl;
import com.centris.campus.forms.SpecializationForm;
import com.centris.campus.pojo.SpecializationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.SpecializationService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.SpecializationVo;



public class SpecializationServiceImpl implements SpecializationService {

	private static Logger logger = Logger
			.getLogger(DepartmentMasterServiceImpl.class);
		SpecializationDao dao;
	@Override
	public ArrayList<SpecializationVo> getspecializationList(String schoolLocation,UserLoggingsPojo custdetails) {
		dao= new SpecializationDaoImpl();
		return dao.getspecializationList(schoolLocation,custdetails);
	}
	@Override
	public String insertSpecialization(SpecializationForm spec, String specId,UserLoggingsPojo userLoggingsVo) {
		
		SpecializationDao specializationDao = new SpecializationDaoImpl();
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  SpecializationServiceImpl :sectiondetailsdownload Starting");
		String result =null;
		try{
			
			SpecializationPojo pojo =new SpecializationPojo();
		

			
			String s1 = IDGenerator.getPrimaryKeyID("campus_class_specialization",userLoggingsVo);

			pojo.setSpec_Id(spec.getSpec_Id());

			pojo.setSpec_Name(spec.getSpec_Name());
			pojo.setStream_Id(spec.getStream_Id());
			pojo.setCreate_User(spec.getCreate_User());
			pojo.setClass_Id(spec.getClass_Id());
			pojo.setSpecializationid(s1);
			pojo.setLocationId(spec.getLocationId());
			pojo.setLog_audit_session(spec.getLog_audit_session());
			pojo.setCustid(spec.getCustid());
			
			result = specializationDao.insertSpecialization(pojo,specId,userLoggingsVo);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SpecializationServiceImpl : insertHolidayXSL : Ending");
		return result;
	}
	@Override
	public SpecializationVo editSpecialization(String edit,UserLoggingsPojo custdetails) {
		dao= new SpecializationDaoImpl();
		return dao.editSpecialization(edit,custdetails);
	}
	@Override
	public String deleteSpec(SpecializationVo vo,UserLoggingsPojo userLoggingsVo) {
		dao= new SpecializationDaoImpl();
		return dao.deleteSpec(vo, userLoggingsVo);
	}
	
	public List<SpecializationVo> getSpecializationOnClassBased(String classId,UserLoggingsPojo userLoggingsVo) {
		dao= new SpecializationDaoImpl();
		return dao.getSpecializationOnClassBased(classId,userLoggingsVo);
	}
	@Override
	public String validateSpecialization(SpecializationForm form1,UserLoggingsPojo userLoggingsVo) {
		dao= new SpecializationDaoImpl();
		return dao.getSpecializationOnClassBased(form1, userLoggingsVo);
	}
	@Override
	public ArrayList<SpecializationVo> getSearchSpecializationList(String searchterm,String school,UserLoggingsPojo custdetails) {
		dao= new SpecializationDaoImpl();
		return dao.getSearchSpecializationList(searchterm,school,custdetails);
	}
	public List<SpecializationVo> getstreamdetailsforOnchange(String locationid, String classname, String streamId, String status, UserLoggingsPojo custdetails) {
	return new SpecializationDaoImpl().getstreamdetailsforOnchange( locationid,classname,streamId,status,custdetails);
	}
	
	public List<SpecializationVo> getSpecializationOnClassWithoutLocId(String classId) {
		dao= new SpecializationDaoImpl();
		return dao.getSpecializationOnClassWithoutLocId(classId);
	}
}
