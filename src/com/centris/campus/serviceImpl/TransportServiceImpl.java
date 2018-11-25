package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.TransportDao;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.TransportDaoImpl;
import com.centris.campus.forms.TransportCategoryForm;
import com.centris.campus.forms.TransportForm;
import com.centris.campus.pojo.TransportPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.TransportService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AcadamicYearVO2;
import com.centris.campus.vo.DriverMasterVO;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.VehicleTypeVo;

public class TransportServiceImpl implements TransportService {

	private static final Logger logger = Logger
			.getLogger(TransportServiceImpl.class);

	TransportDao transportdao = new TransportDaoImpl();

	public ArrayList<VehicleDetailsVO> getAllvehicleDetails(UserLoggingsPojo custdetails,String status,String locId ) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAllvehicleDetails Starting");
		ArrayList<VehicleDetailsVO> getvehiclelist = null;
		try {
			getvehiclelist = transportdao.getAllvehicleDetails(custdetails,status,locId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAllvehicleDetails  Ending");
		return getvehiclelist;
	}

	public String saveVehicleDetails(VehicleDetailsVO transportForm,
			String createUser, String vehiclecode,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : saveVehicleDetails Starting");
		
		System.out.println("save vehicle service impl");
		
		String status = "";
		try {
			
			System.out.println("ServiceIMPL RC File: "+ transportForm.getRcfile());
			status = transportdao.saveVehicleDetails(transportForm, createUser,vehiclecode,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : saveVehicleDetails  Ending");
		return status;
	}

	public boolean checkingVehicleInsuranceDate(VehicleDetailsVO vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkingVehicleInsuranceDate Starting");
		boolean status = false;
		try {
			status = transportdao.checkingVehicleInsuranceDate(vehiclecode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkingVehicleInsuranceDate  Ending");
		return status;
	}

	public VehicleDetailsVO getSingleVehicleDetails(String vehiclecode,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkingVehicleInsuranceDate Starting");
		VehicleDetailsVO ovj = null;
		try {
			ovj = transportdao.getSingleVehicleDetails(vehiclecode,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkingVehicleInsuranceDate  Ending");
		return ovj;
	}

	public String deleteVehicleDetails(String[] vehiclecode,String log_audit_session,UserLoggingsPojo userLoggingsVo,VehicleDetailsVO vo, String button) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : deleteVehicleDetails Starting");
		String status = null;
		try {
			status = transportdao.deleteVehicleDetails(vehiclecode,log_audit_session,userLoggingsVo,vo,button);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : deleteVehicleDetails  Ending");
		return status;
	}

	public String registernumberValidation(String vehicleregno,String locId,UserLoggingsPojo userLoggingsVo,
			String vehicleId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : registernumberValidation Starting");
		String status = null;
		try {
			status = transportdao.registernumberValidation(vehicleregno,locId,userLoggingsVo,vehicleId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : registernumberValidation  Ending");
		return status;
	}

	public String updateregisternumberValidation(String vehicleregno,String vehicleCode,String locId,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : updateregisternumberValidation Starting");
		String status = null;
		try {
			status = transportdao.updateregisternumberValidation(vehicleregno,vehicleCode,locId,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : updateregisternumberValidation  Ending");
		return status;
	}

	public boolean chassisnovalidationvalidation(String chassisno,String locId,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : registernumberValidation Starting");
		boolean status = false;
		try {
			status = transportdao.chassisnovalidationvalidation(chassisno,locId,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : registernumberValidation  Ending");
		return status;
	}

	public boolean updatechassisnovalidation(String chassisno,
			String vehicleCode,String locId,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : updatechassisnovalidation Starting");
		boolean status = false;
		try {
			status = transportdao.updatechassisnovalidation(chassisno,vehicleCode,locId,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : updatechassisnovalidation  Ending");
		return status;
	}

	public ArrayList<VehicleDetailsVO> searchvehicledetails(String searchTerm,UserLoggingsPojo custdetails,String locId, String status) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAllvehicleDetails Starting");
		ArrayList<VehicleDetailsVO> getvehiclelist = null;
		try {
			getvehiclelist = transportdao.searchvehicledetails(searchTerm,custdetails,locId,status);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAllvehicleDetails  Ending");
		return getvehiclelist;
	}

	public boolean checkforduplicateAddTime(VehicleDetailsVO obj,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkforduplicateAddTime Starting");
		boolean status = false;
		try {
			status = transportdao.checkforduplicateAddTime(obj,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkforduplicateAddTime  Ending");
		return status;
	}

	public boolean checkforduplicateUpdateTime(VehicleDetailsVO obj,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkforduplicateUpdateTime Starting");
		boolean status = false;
		try {
			status = transportdao.checkforduplicateUpdateTime(obj,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkforduplicateUpdateTime  Ending");
		return status;
	}

	// route master start
	public List<TransportVo> getTransportMasterServiceDetails(String loc,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Transport ServiceImpl: getTransportMasterServiceDetails Starting");
		TransportDao tpSetupDao = new TransportDaoImpl();
		List<TransportVo> transportVOList = new ArrayList<TransportVo>();
		try {
			List<TransportPojo> tplist = tpSetupDao.getTransportMasterDaoDetails(loc,custdetails);
			for (TransportPojo gatDatas : tplist) {
				
				TransportVo tpVoObj = new TransportVo();
				tpVoObj.setRouteNo(gatDatas.getRouteNo());
				tpVoObj.setEndTime(gatDatas.getEndTime());
				tpVoObj.setRouteName(gatDatas.getRouteName());
				tpVoObj.setStratTime(gatDatas.getStartTime());
				tpVoObj.setTotalDistance(gatDatas.getTotalDistance());
				tpVoObj.setTotalStops(gatDatas.getTotalStops());
				tpVoObj.setRouteCode(gatDatas.getRouteCode());
				tpVoObj.setHalttime(gatDatas.getHalttime());
				//tpVoObj.setDestination(gatDatas.getDestination());
				
				transportVOList.add(tpVoObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl: getTransportMasterServiceDetails Ending");
		return transportVOList;
	}

	public String insertRouteMasterDetails(TransportPojo tpMasterPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportSetupServiceImpl:insertRouteMasterDetails  Starting");
		
		String check = null;
		TransportDao tpSetupDao = new TransportDaoImpl();
		try {

			check = tpSetupDao.insertRouteMasterDetails(tpMasterPojo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl:insertRouteMasterDetails Ending");
		return check;
	}

	public String removeRouteMasterDetails(TransportVo vo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl: removeRouteMasterDetails Starting");
		TransportDao routeDaoObj = new TransportDaoImpl();
		String message = null;
		try {
			message = routeDaoObj.removeRouteMasterDetails(vo,userLoggingsVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl: removeRouteMasterDetails Ending");
		return message;
	}

	public boolean addRoute(TransportForm routeForm) {
		return new TransportDaoImpl().addRoute(routeForm);
	}

	public boolean checkrouteNo(TransportPojo Pojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkrouteNo Starting");
		boolean status = false;
		try {
			status = transportdao.checkrouteNo(Pojo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : checkrouteNo  Ending");
		return status;
	}

	public TransportVo editRouteMasterDetails(String routecode,String loc,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editRouteMasterDetails Starting");
		TransportVo list = null;
		try {
			list = transportdao.editRouteMasterDetails(routecode,loc,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editRouteMasterDetails  Ending");

		return list;
	}

	public List<TransportVo> searchDetails(String SearchName,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchDetails Starting");
		List<TransportVo> list = null;
		try {
			list = transportdao.searchDetails(SearchName,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchDetails  Ending");

		return list;

	}// route master end

	public List<DriverMsaterListVo> getdriverListService(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getdriverListService Starting");

		ArrayList<DriverMsaterListVo> driverlist = null;
		try {

			driverlist = transportdao.getdriverListDao(custdetails);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getdriverListService  Ending");

		return driverlist;
	}

	/*public String addDriverService(TransportForm obj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : addDriverService Starting");

		DriverMsaterListVo drivervo = new DriverMsaterListVo();
		String result = null;

		if (obj.getDrivercode().equalsIgnoreCase("")|| obj.getDrivercode() == null)

		{

			try {
				
				int res = transportdao.addDriverDao(drivervo);

				if (res == 1) {

					result = "Driver Creation Failed";
				} else {
					result = "Driver Created Successfully";
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		}

		else if (!obj.getDrivercode().equalsIgnoreCase("")
				|| obj.getDrivercode() == null) {

			try {

				int res = transportdao.updateDriverDao(drivervo);

				if (res == 1) {

					result = "Driver Update Successfully";
				} else {
					result = "Driver not Update Successfully";
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		}

		
		 * try {
		 * 
		 * drivervo.setAddress(obj.getAddress()); drivervo.setAge(obj.getAge());
		 * drivervo.setDateofBirth(obj.getDateofBirth());
		 * drivervo.setDateofJoin(obj.getDateofJoin());
		 * drivervo.setDl_issued_date(obj.getDl_issued_date());
		 * drivervo.setDl_validity(obj.getDl_validity());
		 * drivervo.setDriverName(obj.getDriverName());
		 * drivervo.setDrivingliecenseNo(obj.getDrivingliecenseNo());
		 * drivervo.setEmerg_contact(obj.getEmerg_contact());
		 * drivervo.setExperience(obj.getExperience());
		 * drivervo.setFather_name(obj.getFather_name());
		 * drivervo.setGender(obj.getGender());
		 * drivervo.setLicense(obj.getLicense());
		 * drivervo.setMobile(obj.getMobile()); drivervo.setType(obj.getType());
		 * drivervo.setDriverCode(obj.getDriver_code());
		 * drivervo.setCreateUser(obj.getCreateUser());
		 * 
		 * 
		 * } catch (Exception e) { logger.error(e.getMessage(), e);
		 * e.printStackTrace(); }
		 

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : addDriverService  Ending");
		return result;
	}*/

	public DriverMsaterListVo editDriverService(DriverMsaterListVo drivervo,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editDriverService Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editDriverService  Ending");

		return transportdao.editDriverDao(drivervo,custdetails);
	}

	public String deleteDriverService(DriverMsaterListVo vo,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : deleteDriverService Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : deleteDriverService  Ending");
		return transportdao.deleteDriverDao(vo,custdetails);
	}

	public ArrayList<DriverMsaterListVo> searchDriverService(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchDriverService Starting");

		ArrayList<DriverMsaterListVo> driverlist = transportdao
				.searchDriverDao(searchName);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchDriverService  Ending");

		return driverlist;
	}

	public boolean validateDriverService(DriverMsaterListVo drivervo, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : validateDriverService Starting");

		boolean driver_validate = false;

		driver_validate = transportdao.validateDriverDao(drivervo,custdetails);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : validateDriverService  Ending");
		return driver_validate;
	}
	
	
	// for driver license validation //
	
	public boolean validateLicenseService(DriverMsaterListVo drivervo, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : validateLicenseService Starting");

		boolean license_validate = false;

		license_validate = transportdao.validateLicenseDao(drivervo,custdetails);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : validateLicenseService  Ending");
		return license_validate;
	}
	
	
	
	
	

	public ArrayList<DriverMsaterListVo> getDriverNamesDetails(VehicleDetailsVO vo,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getDriverNamesDetails Starting");
		ArrayList<DriverMsaterListVo> driverlist = null;
		try {
			driverlist = transportdao.getDriverNamesDetails(vo,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getDriverNamesDetails  Ending");

		return driverlist;

	}

	public ArrayList<DriverMsaterListVo> getDriverEntireDetails(String driverid,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getDriverEntireDetails Starting");
		ArrayList<DriverMsaterListVo> driverlist = null;
		try {
			driverlist = transportdao.getDriverEntireDetails(driverid,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getDriverEntireDetails  Ending");

		return driverlist;

	}

	public ArrayList<DriverMsaterListVo> getDriverDetailsWhileUpdate(
			String vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getDriverDetailsWhileUpdate Starting");
		ArrayList<DriverMsaterListVo> driverlist = null;
		try {
			driverlist = transportdao.getDriverDetailsWhileUpdate(vehiclecode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getDriverDetailsWhileUpdate  Ending");

		return driverlist;

	}

	public DriverMsaterListVo getSingleDriverDetails(String vehiclecode,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getSingleDriverDetails Starting");
		DriverMsaterListVo driverlist = null;
		try {
			driverlist = transportdao.getSingleDriverDetails(vehiclecode,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getSingleDriverDetails  Ending");

		return driverlist;

	}

	public ArrayList<StageMasterVo> getStopNames(String searchTerm, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getStopNames Starting");
		ArrayList<StageMasterVo> driverlist = null;
		try {
			driverlist = transportdao.getStopNames(searchTerm,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getStopNames  Ending");

		return driverlist;

	}

	public ArrayList<TransportVo> getRouteDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRouteDetails Starting");
		ArrayList<TransportVo> transportlist = null;
		try {
			transportlist = transportdao.getRouteDetails();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRouteDetails  Ending");

		return transportlist;
	}

	public ArrayList<TransportVo> GetRouteEntireDetails(String route, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : GetRouteEntireDetails Starting");
		ArrayList<TransportVo> transportlist = null;
		try {
			transportlist = transportdao.GetRouteEntireDetails(route,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : GetRouteEntireDetails  Ending");

		return transportlist;

	}

	public TransportVo getRouteDetails(String vehiclecode,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRouteDetails Starting");
		TransportVo transportlist = null;
		try {
			transportlist = transportdao.getRouteDetails(vehiclecode,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRouteDetails  Ending");

		return transportlist;

	}
	
	public List<TransportVo> getTransportMasterServiceDetailsXLS() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Transport ServiceImpl: getTransportMasterServiceDetails Starting");
		TransportDao tpSetupDao = new TransportDaoImpl();
		List<TransportVo> transportVOList = new ArrayList<TransportVo>();
		try {
			List<TransportPojo> tplist = tpSetupDao
					.getTransportMasterDaoDetailsXLS();
			for (TransportPojo gatDatas : tplist) {
				TransportVo tpVoObj = new TransportVo();

				tpVoObj.setEndTime(gatDatas.getEndTime());
				tpVoObj.setRouteName(gatDatas.getRouteName());
				tpVoObj.setRouteNo(gatDatas.getRouteNo());
				tpVoObj.setStratTime(gatDatas.getStartTime());
				tpVoObj.setTotalDistance(gatDatas.getTotalDistance());
				tpVoObj.setTotalStops(gatDatas.getTotalStops());
				tpVoObj.setRouteCode(gatDatas.getRouteCode());
				tpVoObj.setHalttime(gatDatas.getHalttime());
				tpVoObj.setDestination(gatDatas.getDestination());
				tpVoObj.setStopname(gatDatas.getStopname());

				tpVoObj.setArrivaltime(gatDatas.getArrivaltime());

				tpVoObj.setDeparturetime(gatDatas.getDeparturetime());

				tpVoObj.setDistance(gatDatas.getDistance());

				transportVOList.add(tpVoObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl: getFeeMasterServiceDetails Ending");
		return transportVOList;
	}

	@Override
	public boolean addDriverService(DriverMsaterListVo drivervo, String createuser, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : addDriverService Starting");
		boolean result = false;
		
		if (drivervo.getDriverCode() ==null  || drivervo.getDriverCode().equalsIgnoreCase("")) 
		{
			try {
				String drivercode1 = IDGenerator.getPrimaryKeyID("transport_driver",custdetails);
				drivervo.setDriverCode(drivercode1);
				result = transportdao.addDriverDao( drivervo,createuser,custdetails);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		} else{
			try {
				result = transportdao.updateDriverDao( drivervo,createuser,custdetails);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

		}

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : addDriverService  Ending");
		return result;
	}
	
	public List<TransportVo> editRouteStages(TransportPojo tpMasterPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editRouteStages Starting");
		List<TransportVo> list = null;
		try {
			list = transportdao.editRouteStages(tpMasterPojo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : editRouteStages  Ending");

		return list;

	}
	
	public List<TransportVo> unmappedRouteStages(TransportPojo tpMasterPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : unmappedRouteStages Starting");
		List<TransportVo> list = null;
		try {
			list = transportdao.unmappedRouteStages(tpMasterPojo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : unmappedRouteStages  Ending");

		return list;

	}

	@Override
	public ArrayList<TransportVo> getRouteDetailsByLocation(String transferlocation,UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : unmappedRouteStages Starting");
		ArrayList<TransportVo> list = null;
		try {
			list = transportdao.getRouteDetailsByLocation(transferlocation, pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : unmappedRouteStages  Ending");

		return list;

	}

	
	
	public ArrayList<TransportVo> getRouteDetailsByName(VehicleDetailsVO vo,UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : unmappedRouteStages Starting");
		ArrayList<TransportVo> list = null;
		try {
			list = transportdao.getRouteDetailsByName(vo,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : unmappedRouteStages  Ending");

		return list;

	}

	@Override
	public ArrayList<TransportVo> getStudentBusCardDetails(TransportVo obj) {
		TransportDao list = new TransportDaoImpl();
		return list.getStudentBusCardDetails(obj);
	}

	public List<VehicleTypeVo> searchVehicletypeDetails(String SearchName,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchVehicletypeDetails Starting");
		List<VehicleTypeVo> list = null;
		try {
			list = transportdao.searchVehicletypeDetails(SearchName,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : searchVehicletypeDetails  Ending");

		return list;

	}
	
	public String insertVehicleType(TransportCategoryForm form,String createCode, UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : insertVehicleType Starting");
		
		String status = "";
		try {
			
			status = transportdao.insertVehicleType(form,createCode,custdetails);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : insertVehicleType  Ending");
		return status;
	}
	
	public List<VehicleTypeVo> getAllvehicletypeDetails(UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAllvehicleTypeDetails Starting");
		List<VehicleTypeVo> getvehiclelist = null;
		try {
			getvehiclelist = transportdao.getAllvehicletypeDetails(custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getAllvehicleTypeDetails  Ending");
		return getvehiclelist;
	}

	public VehicleTypeVo edittransporttype(String transportCatergory,UserLoggingsPojo userLoggingsVo) {
		return transportdao.edittransporttype(transportCatergory,userLoggingsVo);
	}

	public String deleteVehicleType(TransportVo vo,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : deleteVehicleType Starting");
		String status = null;
		try {
			status = transportdao.deleteVehicleType(vo,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : deleteVehicleType  Ending");
		return status;
	}
	
	public String validateVehicleType(VehicleTypeVo vehicleadd, UserLoggingsPojo custdetails) 
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : validateVehicleType Starting");

		String vehi_validate = null;

		vehi_validate = transportdao.validateVehicleType(vehicleadd,custdetails);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : validateVehicleType  Ending");
		return vehi_validate;
	}

	public TransportVo gettransportdetailsstudentwise(TransportVo tvo,UserLoggingsPojo userLoggingsVo) {
		TransportVo list = null;
		list= transportdao.gettransportdetailsstudentwise(tvo,userLoggingsVo);
		return list;
		 
	}

	@Override
	public ArrayList<TransportVo> settranporttermdetailsforstudent(
			TransportVo tvo, UserLoggingsPojo userLoggingsVo) {
		ArrayList<TransportVo> list = null;
		list= transportdao.settranporttermdetailsforstudent(tvo,userLoggingsVo);
		return list;
	}

	@Override
	public ArrayList<TransportVo> getRouteNamelist(TransportVo tvo,UserLoggingsPojo userLoggingsVo) {
		ArrayList<TransportVo> list = null;
		list= transportdao.getRouteNamelist(tvo,userLoggingsVo);
		return list;
	}

	@Override
	public ArrayList<TransportVo> getstoplist(TransportVo tvo,UserLoggingsPojo userLoggingsVo) {
		ArrayList<TransportVo> list = null;
		list= transportdao.getstoplist(tvo,userLoggingsVo);
		return list;
	}

	@Override
	public String getamountandstatus(TransportVo tvo,UserLoggingsPojo userLoggingsVo) {
	
		return transportdao.getamountandstatus(tvo,userLoggingsVo);
		 
	}

	@Override
	public String savetransportrequest(TransportPojo pojo,UserLoggingsPojo custdetails) {
		return transportdao.savetransportrequest(pojo,custdetails);
	}

	@Override
	public List<StudentRegistrationVo> searchAllAcademicYearDetailsTrans(String locationId, String accYear,UserLoggingsPojo custdetails) {
		return transportdao.searchAllAcademicYearDetailsTrans(locationId,accYear,custdetails);
	}

	@Override
	public List<StudentRegistrationVo> getStudentList(String academic_year, String schoolLocation) {
		return transportdao.getStudentList(academic_year,schoolLocation);
	}

	@Override
	public List<StudentRegistrationVo> getStudentListByClass(String locationid, String accyear, String classname) {
		return transportdao.getStudentListByClass(locationid,accyear,classname);
	}

	@Override
	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,String sectionid) {
		return transportdao.getStudentListBySection(locationid,accyear,classname,sectionid);
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm) {
		return transportdao.getStudentSearchByList(searchTerm);
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear) {
		return transportdao.getStudentSearchListByAccYear(searchTerm,accyear);
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid) {
		return transportdao.getStudentSearchListByLocation(searchTerm,locationid);
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear,
			String classname) {
		return transportdao.getStudentSearchByFilter(searchTerm,locationid,accyear,classname);
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,
			String classname) {
		return transportdao.getStudentSearchByClass(searchTerm,locationid,accyear,classname);
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,
			String classname, String sectionid) {
		return transportdao.getStudentSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);	}

	@Override
	public String waivedOfftransportrequest(TransportPojo pojo, UserLoggingsPojo userLoggingsVo) {
		return transportdao.waivedOfftransportrequest(pojo,userLoggingsVo);
	}

	@Override
	public ArrayList<TransportVo> getMonthList(String accyear,UserLoggingsPojo userLoggingsVo,String loc_id) {
		return transportdao.getMonthList(accyear,userLoggingsVo,loc_id);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> schoolListStage(String loc,  UserLoggingsPojo custdetails) {
		return transportdao.schoolListStage(loc,custdetails);
	}

	@Override
	public ArrayList<StageMasterVo> getStage(String accyear, String loc, UserLoggingsPojo userLoggingsVo) {
		return transportdao.getStage(accyear,loc,userLoggingsVo);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> schoolList(String accyear, String loc,UserLoggingsPojo userLoggingsVo) {
		return transportdao.schoolList(accyear,loc,userLoggingsVo);
	}

	@Override
	public ArrayList<AcadamicYearVO2> getPreAcc(String accyear, String loc, UserLoggingsPojo userLoggingsVo) {
		return transportdao.getPreAcc(accyear,loc,userLoggingsVo);
	}

	@Override
	public String addStageWiseAmount(String accyear, String loc, String[] stageid, String[] amount, String user,
			UserLoggingsPojo userLoggingsVo,String log_audit_session ) {
		return transportdao.addStageWiseAmount(accyear,loc,stageid,amount,user,userLoggingsVo,log_audit_session);
	}

	@Override
	public ArrayList<AcadamicYearVO2> getPresentAndNextAccyear(String accyear, String loc,
			UserLoggingsPojo userLoggingsVo) {
		return transportdao.getPresentAndNextAccyear(accyear,loc,userLoggingsVo);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> schoolListRoutMaster(String loc, UserLoggingsPojo userLoggingsVo) {
		return transportdao.schoolListRoutMaster(loc,userLoggingsVo);
	}

	@Override
	public List<TransportVo> routeMasterSettingBySearch(TransportVo vo, UserLoggingsPojo custdetails) {
		return transportdao.routeMasterSettingBySearch(vo,custdetails);
	}

	@Override
	public List<TransportVo> transportCategoryListByStatus(TransportVo vo, UserLoggingsPojo custdetails) {
		return transportdao.transportCategoryListByStatus(vo,custdetails);
	}

	@Override
	public List<DriverMsaterListVo> driverListByStatus(DriverMsaterListVo vo, UserLoggingsPojo custdetails) {
		return transportdao.driverListByStatus(vo,custdetails);
	}

	@Override
	public String saveTransportConcession(StudentConcessionVo vo, UserLoggingsPojo userLoggingsVo) {
		return transportdao.saveTransportConcession(vo,userLoggingsVo);
	}

	@Override
	public String valideDriverCode(String drivername,String locId, UserLoggingsPojo custdetails) {
		
		return transportdao.valideDriverCode(drivername,locId,custdetails);
	}

	@Override
	public String getTransportYearCollection(UserLoggingsPojo userLoggingsVo, String accyear, String location_id) {
		
		return transportdao.getTransportYearCollection( userLoggingsVo,  accyear,  location_id);
	}

	@Override
	public String getTodayCollection(UserLoggingsPojo userLoggingsVo, String location_id) {
		
		return transportdao.getTodayCollection( userLoggingsVo,location_id);
	}
		
}






