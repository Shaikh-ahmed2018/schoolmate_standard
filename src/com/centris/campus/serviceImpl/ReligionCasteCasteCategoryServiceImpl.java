package com.centris.campus.serviceImpl;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.daoImpl.ReligionCasteCasteCategoryDaoImpl;
import com.centris.campus.forms.ReligionCasteCasteCategoryForm;
import com.centris.campus.pojo.ReligionCasteCasteCategoryPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ReligionCasteCasteCategoryService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;


public class ReligionCasteCasteCategoryServiceImpl implements ReligionCasteCasteCategoryService {
	private static final Logger logger = Logger
			.getLogger(ReligionCasteCasteCategoryServiceImpl.class);
	
	ReligionCasteCasteCategoryDaoImpl daoImpl = new ReligionCasteCasteCategoryDaoImpl();

	public String insertReligion(ReligionCasteCasteCategoryForm detailsForm,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertReligion Starting");

		ReligionCasteCasteCategoryPojo detailsPojo=new ReligionCasteCasteCategoryPojo();
		
		String result = null;
		
		if(detailsForm.getReligionId()==null || detailsForm.getReligionId().equalsIgnoreCase("")){
		
			String religion=detailsForm.getReligion();
			System.out.println("religion name is "+religion);
			detailsPojo.setReligion(religion);
			
			
			if(religion !=null){

				int nameFound = daoImpl.validateReligion(detailsPojo,userLoggingsVo);
				if(nameFound==1){
					result="Religion Already Exist";
				}
				else if(nameFound==10){
					result="inactive";
				}
				else{
					try {
						detailsPojo.setCreateUser(detailsForm.getCreateUser());
						detailsPojo.setReligion(detailsForm.getReligion());
						detailsPojo.setLog_audit_session(detailsForm.getLog_audit_session());

						int res = daoImpl.insertReligion(detailsPojo,userLoggingsVo);

						if(res==1){

							result = "Religion not Added Successfully";
						}
						else {
							result = "Religion Added Successfully";
						}

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
			
			}else{
				result="Enter Religion";
			}
		}else{
			String religion=detailsForm.getReligion();
			String hiddenreligion=detailsForm.getHiddenreligion().trim();
			System.out.println("religion name is "+hiddenreligion);
			detailsPojo.setReligion(religion);
			
			System.out.println(religion != hiddenreligion);
			if(!religion.equalsIgnoreCase(hiddenreligion)){
				int nameFound = daoImpl.validateReligion(detailsPojo,userLoggingsVo);
				if(nameFound==1 ){
					result="Religion Already Exist";
				}else{
					try {
						detailsPojo.setReligionId(detailsForm.getReligionId());
						detailsPojo.setReligion(detailsForm.getReligion());
						detailsPojo.setCreateUser(detailsForm.getCreateUser());
						detailsPojo.setLog_audit_session(detailsForm.getLog_audit_session());
						detailsPojo.setCustid(detailsForm.getCustid());
						
						int res = daoImpl.updateReligion(detailsPojo,userLoggingsVo);

						if(res==1){

							result = "Religion Updated Successfully";
						}
						else {
							result = "Religion not Updated Successfully";
						}
					}catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
			}else{
				try {
					detailsPojo.setReligionId(detailsForm.getReligionId());
					detailsPojo.setReligion(detailsForm.getReligion());
					detailsPojo.setCreateUser(detailsForm.getCreateUser());

					int res = daoImpl.updateReligion(detailsPojo,userLoggingsVo);

					if(res==1){

						result = "Religion Updated Successfully";
					}
					else {
						result = "Religion not Updated Successfully";
					}
				}catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);

		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertReligion Ending");

		return result;
	}


	public List<ReligionCasteCasteCategoryVo> searchReligion(String searchName,String status,UserLoggingsPojo custdetails) {
		return daoImpl.searchReligion(searchName,status,custdetails);
	}

	public List<ReligionCasteCasteCategoryVo> getReligionDetails(UserLoggingsPojo custdetails) {
		return daoImpl.getReligionDetails(custdetails);
	}


	
	public ReligionCasteCasteCategoryVo getSingleReligion(ReligionCasteCasteCategoryPojo detailsPojo,UserLoggingsPojo userLoggingsVo) {
		return daoImpl.getSingleReligion(detailsPojo,userLoggingsVo);
	}



	public String deleteReligion(ReligionCasteCasteCategoryPojo detailsPojo,UserLoggingsPojo userLoggingsVo) {
		return daoImpl.deleteReligion(detailsPojo,userLoggingsVo);
	}



	public List<ReligionCasteCasteCategoryVo> searchCaste(String searchName,String sts,UserLoggingsPojo custdetails) {
		return daoImpl.searchCaste(searchName,sts,custdetails);
	}


	public List<ReligionCasteCasteCategoryVo> getCasteDetails(String religionId,UserLoggingsPojo custdetails) {
		return daoImpl.getCasteDetails(religionId,custdetails);
	}


	
	public String deleteCaste(ReligionCasteCasteCategoryPojo detailsPojo,UserLoggingsPojo userLoggingsVo) {
		return daoImpl.deleteCaste(detailsPojo,userLoggingsVo);
	}
	
	public String insertCaste(ReligionCasteCasteCategoryForm detailsForm,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertCaste Starting");

		ReligionCasteCasteCategoryPojo detailsPojo=new ReligionCasteCasteCategoryPojo();
		detailsPojo.setCustid(detailsForm.getCustid());
		String result = null;


		System.out.println("Service class: getCasteId: "+detailsForm.getCasteId());
		System.out.println("Service class: getCasteId: "+detailsForm.getCaste());

		if(detailsForm.getCasteId()==null || detailsForm.getCasteId().equalsIgnoreCase("")){

			String caste=detailsForm.getCaste();
			detailsPojo.setCaste(caste);
			detailsPojo.setMain_religion(detailsForm.getMain_religion());
			
			
			if(caste !=null){

				int nameFound = daoImpl.validateCaste(detailsPojo,userLoggingsVo);
				if(nameFound==1){
					result="Caste Already Exist";
				}
				else if(nameFound==10){
					result="inactive";
				}
				else{
					try {

						detailsPojo.setCaste(detailsForm.getCaste());
						detailsPojo.setLog_audit_session(detailsForm.getLog_audit_session());
						detailsPojo.setCreateUser(detailsForm.getCreateUser());
						int res = daoImpl.insertCaste(detailsPojo,userLoggingsVo);

						if(res==1){

							result = "Caste not Added Successfully";
						}
						else {
							result = "Caste Added Successfully";
						}

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}

			}else{
				result="Enter Caste";
			}
		}else{
			String caste=detailsForm.getCaste();
			String hiddencaste=detailsForm.getHiddencaste().trim();
			detailsPojo.setCaste(caste);
			detailsPojo.setMain_religion(detailsForm.getMain_religion());
			detailsPojo.setCustid(detailsForm.getCustid());
			
			if(!caste.equalsIgnoreCase(hiddencaste)){

				int nameFound = daoImpl.validateCaste(detailsPojo,userLoggingsVo);
				if(nameFound==1){
					result="Caste Already Exist";
				}else{
					try{
						detailsPojo.setCasteId(detailsForm.getCasteId());
						detailsPojo.setCaste(detailsForm.getCaste());
						detailsPojo.setCreateUser(detailsForm.getCreateUser());
						detailsPojo.setMain_religion(detailsForm.getMain_religion());
						detailsPojo.setLog_audit_session(detailsForm.getLog_audit_session());

						int res = daoImpl.updateCaste(detailsPojo,userLoggingsVo);

						if(res==1){

							result = "Caste Updated Successfully";
						}
						else {
							result = "Caste not Updated Successfully";
						}
					}catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
			}else{
				try{
					detailsPojo.setCasteId(detailsForm.getCasteId());
					detailsPojo.setCaste(detailsForm.getCaste());
					detailsPojo.setCreateUser(detailsForm.getCreateUser());
					detailsPojo.setMain_religion(detailsForm.getMain_religion());

					int res = daoImpl.updateCaste(detailsPojo,userLoggingsVo);

					if(res==1){

						result = "Caste Updated Successfully";
					}
					else {
						result = "Caste not Updated Successfully";
					}
				}catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);

		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertCaste Ending");

		return result;
	}


	
	public ReligionCasteCasteCategoryVo getSingleCaste(
			ReligionCasteCasteCategoryPojo detailsPojo,UserLoggingsPojo userLoggingsVo) {
		return daoImpl.getSingleCaste(detailsPojo,userLoggingsVo);
	}



	public List<ReligionCasteCasteCategoryVo> searchCasteCategory(String searchName,String status,UserLoggingsPojo custdetails) {
		return daoImpl.searchCasteCategory(searchName,status,custdetails);
	}



	public List<ReligionCasteCasteCategoryVo> getCasteCategoryDetails(UserLoggingsPojo custdetails) {
		return daoImpl.getCasteCategoryDetails(custdetails);
	}


	
	public String insertCasteCategory(ReligionCasteCasteCategoryForm detailsForm,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertCasteCategory Starting");

		ReligionCasteCasteCategoryPojo detailsPojo=new ReligionCasteCasteCategoryPojo();

		String result = null;

		if(detailsForm.getCasteCatId()==null || detailsForm.getCasteCatId().equalsIgnoreCase("")){

			String caste=detailsForm.getCasteCategory();
			String religionId=detailsForm.getReligionId();
			String casteId=detailsForm.getCasteId();
			
			detailsPojo.setCreateUser(detailsForm.getCreateUser());
			detailsPojo.setCasteCategory(caste);
			detailsPojo.setCasteId(casteId);
			detailsPojo.setReligionId(religionId);
			detailsPojo.setCustid(detailsForm.getCustid());
			
			if(caste !=null){

				int nameFound = daoImpl.validateCasteCategory(detailsPojo,userLoggingsVo);
				if(nameFound==1){
					result="Caste Category Already Exist";
				}
				else if(nameFound==10){
					result="inactive";
				}
				else{
					try {

						detailsPojo.setCaste(detailsForm.getCaste());
						detailsPojo.setLog_audit_session(detailsForm.getLog_audit_session());
						detailsPojo.setCreateUser(detailsForm.getCreateUser());
						int res = daoImpl.insertCasteCategory(detailsPojo,userLoggingsVo);

						if(res==1){
							result = "Record added successfully";
						}
						else {
							result = "Failed to add record";
						}

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}

			}else{
				result="Enter Caste Category";
			}

		}else{
			
			detailsPojo.setCreateUser(detailsForm.getCreateUser());
			detailsPojo.setCasteCategory(detailsForm.getCasteCategory());
			detailsPojo.setCasteId(detailsForm.getCasteId());
			detailsPojo.setReligionId(detailsForm.getReligionId());
			detailsPojo.setCustid(detailsForm.getCustid());
			
			if(detailsForm.getHiddencastecategory()=="" &&!detailsForm.getCasteId().equalsIgnoreCase(detailsForm.getHiddencastecategory().trim())){
				int nameFound = daoImpl.validateCasteCategory(detailsPojo,userLoggingsVo);
				if(nameFound==1){
					result="Caste Category Already Exist";
				}else{
					try{
						detailsPojo.setCasteCategoryId(detailsForm.getCasteCatId());
						detailsPojo.setCasteCategory(detailsForm.getCasteCategory());
						detailsPojo.setCreateUser(detailsForm.getCreateUser());
						detailsPojo.setCasteId(detailsForm.getCasteId());
						detailsPojo.setReligionId(detailsForm.getReligionId());
						detailsPojo.setLog_audit_session(detailsForm.getLog_audit_session());
						
						int res = daoImpl.updateCasteCategory(detailsPojo,userLoggingsVo);

						if(res==1){

							result = "Caste Category Updated Successfully";
						}
						else {
							result = "Caste Category not Updated Successfully";
						}
					}catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
			}else{
				try{
					detailsPojo.setCasteCategoryId(detailsForm.getCasteCatId());
					detailsPojo.setCasteCategory(detailsForm.getCasteCategory());
					detailsPojo.setCreateUser(detailsForm.getCreateUser());
					detailsPojo.setCasteId(detailsForm.getCasteId());
					detailsPojo.setReligionId(detailsForm.getReligionId());
					int res = daoImpl.updateCasteCategory(detailsPojo,userLoggingsVo);

					if(res==1){

						result = "Caste Category Updated Successfully";
					}
					else {
						result = "Caste Category not Updated Successfully";
					}
				}catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);

		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertCasteCategory Ending");

		return result;
	}

	public String deleteCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo,UserLoggingsPojo userLoggingsVo) {
		return daoImpl.delteCasteCategory(detailsPojo,userLoggingsVo);
	}

	public ReligionCasteCasteCategoryVo getSingleCasteCategory(
			ReligionCasteCasteCategoryPojo detailsPojo,UserLoggingsPojo userLoggingsVo) {
	    return daoImpl.getSingleCasteCategory(detailsPojo,userLoggingsVo);
	}


	
	public String insertOccupation(ReligionCasteCasteCategoryForm detailsForm,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertOccupation Starting");
		
		ReligionCasteCasteCategoryPojo detailsPojo=new ReligionCasteCasteCategoryPojo();
		String result = null;
		
		if(detailsForm.getOccupationId() == null || detailsForm.getOccupationId().equalsIgnoreCase("")){
			
			String occupation=detailsForm.getOccupation();
			detailsPojo.setOccupation(occupation);
			detailsPojo.setCustid(detailsForm.getCustid());
			
			if(occupation !=null){
					
				int nameFound = daoImpl.validateOccupation(detailsPojo,userLoggingsVo);
				if(nameFound==1){
					result="Occupation Already Exist";
			    }
				else if(nameFound==10){
					result="inactive";
			    }
				else{
					try {
						detailsPojo.setCreateUser(detailsForm.getCreateUser());
						detailsPojo.setOccupation(detailsForm.getOccupation());
						detailsPojo.setLog_audit_session(detailsForm.getLog_audit_session());
						
						int res = daoImpl.insertOccupation(detailsPojo,userLoggingsVo);
						
						if(res==1){
							result = "Occupation Not Added Successfully";
						}
						else {
							result = "Occupation Added Successfully";
						}
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
			}else{
				result="Field Required - Occupation";
			}
		}else{
			String occupation=detailsForm.getOccupation();
			String hiddenoccupation=detailsForm.getHiddenoccupation().trim();
			detailsPojo.setOccupation(occupation);
			detailsPojo.setCustid(detailsForm.getCustid());
			
			if(!occupation.equalsIgnoreCase(hiddenoccupation)){
				
				int nameFound = daoImpl.validateOccupation(detailsPojo,userLoggingsVo);
				if(nameFound==1){
					result="Occupation Already Exist";
			}else{
					try {
						detailsPojo.setOccupationId(detailsForm.getOccupationId());
						detailsPojo.setOccupation(detailsForm.getOccupation());
						detailsPojo.setCreateUser(detailsForm.getCreateUser());
						detailsPojo.setLog_audit_session(detailsForm.getLog_audit_session());
						
						int res = daoImpl.updateOccupation(detailsPojo,userLoggingsVo);
						
						 if(res==1){
								
								result = "Occupation Updated Successfully";
							}
							else {
								result = "Occupation Not Updated Successfully";
							}
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
			
			}else{
				
				try {
					
					detailsPojo.setOccupationId(detailsForm.getOccupationId());
					detailsPojo.setOccupation(detailsForm.getOccupation());
					detailsPojo.setCreateUser(detailsForm.getCreateUser());
					
					int res = daoImpl.updateOccupation(detailsPojo,userLoggingsVo);
					
					 if(res==1){
							
							result = "Occupation Updated Successfully";
						}
						else {
							result = "Occupation Not Updated Successfully";
						}
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
				
			}
			
			
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);

		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertOccupation Ending");

		return result;
	
	}


	public List<ReligionCasteCasteCategoryVo> getOccupationDetails(UserLoggingsPojo custdetails) {
		return daoImpl.getOccupationDetails(custdetails);
	}

	public ReligionCasteCasteCategoryVo getSingleOccupation(
			ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
		return daoImpl.getSingleOccupation(detailsPojo,userLoggingsVo);
	}


	public String deleteOccupation(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
		return daoImpl.deleteOccupation(detailsPojo,userLoggingsVo);
	}


	@Override
	public List<ReligionCasteCasteCategoryVo> getCasteDetailsList(String religionId,UserLoggingsPojo userLoggingsVo) {
		return daoImpl.getCasteDetailsList(religionId,userLoggingsVo);
	}
	
	public List<ReligionCasteCasteCategoryVo> getListOfCaste() {
		return daoImpl.getListOfCaste();
	}
	
	public List<ReligionCasteCasteCategoryVo> getCasteCategoryListDetails(String casteId,UserLoggingsPojo userLoggingsVo) {
		return daoImpl.getCasteCategoryListDetails(casteId, userLoggingsVo);
	}


	@Override
	public List<ReligionCasteCasteCategoryVo> getOccupationDetailsSearch(String searchName,String status,UserLoggingsPojo custdetails) {
		return daoImpl.getOccupationDetailsSearch(searchName,status,custdetails);
	}

	@Override
	public List<ReligionCasteCasteCategoryVo> ReligionListByStatus(String status,UserLoggingsPojo userLoggingsVo) {
		return daoImpl.ReligionListByStatus(status,userLoggingsVo);
	}

	@Override
	public List<ReligionCasteCasteCategoryVo> CasteListingByStatus(ReligionCasteCasteCategoryVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoImpl.CasteListingByStatus(vo,userLoggingsVo);
	}

	@Override
	public List<ReligionCasteCasteCategoryVo> CasteCategoryListingByStatus(ReligionCasteCasteCategoryVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoImpl.CasteCategoryListingByStatus(vo, userLoggingsVo);
	}
		
}
