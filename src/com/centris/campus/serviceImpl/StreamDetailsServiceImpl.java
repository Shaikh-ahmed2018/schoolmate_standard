package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.StreamDetailsDao;
import com.centris.campus.daoImpl.StreamDetailsDaoImpl;
import com.centris.campus.forms.StreamDetailsForm;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StreamDetailsService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.StreamDetailsVO;

public class StreamDetailsServiceImpl implements StreamDetailsService {
	private static final Logger logger = Logger
			.getLogger(StreamDetailsServiceImpl.class);
	StreamDetailsDao daoImpl = new StreamDetailsDaoImpl();

	public List<StreamDetailsVO> getStreamDetailsService(String schoolLocation,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : getStreamDetailsService Starting");
		
		List<StreamDetailsVO> streamVoList = new ArrayList<StreamDetailsVO>();
		List<StreamDetailsPojo> streamPojoList = new ArrayList<StreamDetailsPojo>();
		try {
			daoImpl = new StreamDetailsDaoImpl();
			streamPojoList = daoImpl.getStreamDetailsDao(schoolLocation,custdetails);

			for (StreamDetailsPojo streamPojo : streamPojoList) {
				StreamDetailsVO streamVo = new StreamDetailsVO();
				streamVo.setStreamId(streamPojo.getStreamId());
				streamVo.setStreamName(streamPojo.getStreamName());
				streamVo.setDescription(streamPojo.getDescription());
				streamVo.setLocationName(streamPojo.getLocationName());
				streamVo.setReason(streamPojo.getReason());
				streamVoList.add(streamVo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : getStreamDetailsService Ending");
		return streamVoList;
	}

	@Override
	public String insertStreamDetailsService(StreamDetailsForm detailsForm,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : insertStreamDetailsService Starting");

		
		StreamDetailsPojo detailsPojo = new StreamDetailsPojo();
		String result = null;
		
		if(detailsForm.getStreamId().equalsIgnoreCase("")||detailsForm.getStreamId()==null){
			
			
			try {
				
				detailsPojo.setStreamName(detailsForm.getStreamName());
				detailsPojo.setDescription(detailsForm.getDescription());
				detailsPojo.setCreateUser(detailsForm.getCreateUser());
				detailsPojo.setStreamId(detailsForm.getStreamId());
				detailsPojo.setLocationId(detailsForm.getLocationId());
				detailsPojo.setLog_audit_session(detailsForm.getLog_audit_session());
				detailsPojo.setCustid(detailsForm.getCustid());
				
				int res = daoImpl.insertStreamDetailsDao(detailsPojo,userLoggingsVo);
				
				if(res==1){
					result = "Added Successfully";
				}
				else {
					result = "Stream not Added Successfully";
				}
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
		}
		
		else if(!detailsForm.getStreamId().equalsIgnoreCase("")||detailsForm.getStreamId()==null){
			detailsPojo.setStreamName(detailsForm.getStreamName());
			detailsPojo.setDescription(detailsForm.getDescription());
			detailsPojo.setCreateUser(detailsForm.getCreateUser());
			detailsPojo.setStreamId(detailsForm.getStreamId());
			detailsPojo.setLocationId(detailsForm.getLocationId());
			detailsPojo.setLog_audit_session(detailsForm.getLog_audit_session());
			detailsPojo.setCustid(detailsForm.getCustid());
			
			int res = daoImpl.updateStreamDetailsDao(detailsPojo,userLoggingsVo);
			
			 if(res == 1){
					
					result = "Stream Update progressing";
				}
				else {
					result = "Stream not Updated Successfully";
				}
		}
		
		/*//daoImpl = new StreamDetailsDaoImpl();
	
		try {
			detailsPojo.setStreamName(detailsForm.getStreamName());
			detailsPojo.setDescription(detailsForm.getDescription());
			detailsPojo.setCreateUser(detailsForm.getCreateUser());
			detailsPojo.setStreamId(detailsForm.getStreamId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}*/
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);

		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : insertStreamDetailsService Ending");

		return result;
	}

	
	public StreamDetailsVO editStreamDetailsService(StreamDetailsVO detailsVo,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : editStreamDetailsService Starting");
		try {
			daoImpl = new StreamDetailsDaoImpl();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : editStreamDetailsService  Ending");
		return daoImpl.editStreamDetailsDao(detailsVo,custdetails);
	}

	public String deleteStreamDetailsService(StreamDetailsVO detailsVo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : deleteStreamDetailsService Starting");
		try {
			daoImpl = new StreamDetailsDaoImpl();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : deleteStreamDetailsService  Ending");
		return daoImpl.deleteStreamDetailsDao(detailsVo,userLoggingsVo);
	}

	public ArrayList<StreamDetailsVO> searchStreamDetailsService(
			String searchTerm,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : searchStreamDetailsService Starting");
		ArrayList<StreamDetailsVO> getstreamlist = null;
		daoImpl = new StreamDetailsDaoImpl();
		try {

			getstreamlist = daoImpl.searchStreamDetailsDao(searchTerm,custdetails);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : searchStreamDetailsService  Ending");
		return getstreamlist;

	}

	public String validateStreamNameService(StreamDetailsVO detailsVo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : validateStreamNameService Starting");

		String streamname_validate = null;
		daoImpl = new StreamDetailsDaoImpl();
		try {
			streamname_validate = daoImpl.validateStreamNameDao(detailsVo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : validateStreamNameService  Ending");
		return streamname_validate;
	}
	
	public ArrayList<StreamDetailsVO> searchByLocationOnly(String locationId,String accYear,String status, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : searchByLocationOnly Starting");
		ArrayList<StreamDetailsVO> getstreamlist = null;
		daoImpl = new StreamDetailsDaoImpl();
		try {
			getstreamlist = daoImpl.searchByLocationOnly(locationId,accYear,status,custdetails);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsServiceImpl : searchByLocationOnly  Ending");
		return getstreamlist;
	}
}
