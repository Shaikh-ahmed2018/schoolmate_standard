package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.TransportCategoryForm;
import com.centris.campus.forms.TransportForm;
import com.centris.campus.pojo.TransportPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.TransportService;
import com.centris.campus.serviceImpl.LocationServiceImpl;
import com.centris.campus.serviceImpl.TransportServiceImpl;
import com.centris.campus.vo.AcadamicYearVO2;
import com.centris.campus.vo.DriverMasterVO;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.VehicleTypeVo;

public class TransportBD {

	static TransportService service;
	static{
		 service = new TransportServiceImpl();
	 }

	public ArrayList<VehicleDetailsVO> getAllvehicleDetails(UserLoggingsPojo custdetails, String status, String locId) {
		return service.getAllvehicleDetails(custdetails,status,locId);
	}

	public String saveVehicleDetails(VehicleDetailsVO transportForm,String createUser, String vehiclecode, UserLoggingsPojo userLoggingsVo) {
		return service.saveVehicleDetails(transportForm, createUser,vehiclecode,userLoggingsVo);
	}

	public boolean checkingVehicleInsuranceDate(VehicleDetailsVO vehiclecode) {
		return service.checkingVehicleInsuranceDate(vehiclecode);
	}

	public VehicleDetailsVO getSingleVehicleDetails(String vehiclecode, UserLoggingsPojo userLoggingsVo) {
		return service.getSingleVehicleDetails(vehiclecode,userLoggingsVo);
	}

	public String deleteVehicleDetails(String[] vehiclecode,String log_audit_session,UserLoggingsPojo userLoggingsVo, VehicleDetailsVO vo, String button) {
		return service.deleteVehicleDetails(vehiclecode,log_audit_session,userLoggingsVo,vo,button);
	}

	public String registernumberValidation(String vehicleregno, String locId, UserLoggingsPojo userLoggingsVo, String vehicleId) {
		return service.registernumberValidation(vehicleregno,locId,userLoggingsVo,vehicleId);
	}

	public String updateregisternumberValidation(String vehicleregno,
			String vehicleCode, String locId, UserLoggingsPojo userLoggingsVo) {
		return service.updateregisternumberValidation(vehicleregno, vehicleCode,locId,userLoggingsVo);
	}

	public boolean chassisnovalidationvalidation(String chassisno, String locId, UserLoggingsPojo userLoggingsVo) {
		return service.chassisnovalidationvalidation(chassisno,locId,userLoggingsVo);
	}

	public boolean updatechassisnovalidation(String chassisno,
			String vehicleCode, String locId, UserLoggingsPojo userLoggingsVo) {
		return service.updatechassisnovalidation(chassisno,vehicleCode,locId,userLoggingsVo);
	}

	public ArrayList<VehicleDetailsVO> searchvehicledetails(String searchTerm, UserLoggingsPojo custdetails, String locId, String status) {
		return service.searchvehicledetails(searchTerm,custdetails,locId,status);
	}

	public boolean checkforduplicateAddTime(VehicleDetailsVO obj, UserLoggingsPojo custdetails) {
		return service.checkforduplicateAddTime(obj,custdetails);
	}

	public boolean checkforduplicateUpdateTime(VehicleDetailsVO obj, UserLoggingsPojo userLoggingsVo) {
		return service.checkforduplicateUpdateTime(obj,userLoggingsVo);
	}

	// route master start

	public List<TransportVo> getTransportMasterDetails(String loc, UserLoggingsPojo custdetails) {
		return service.getTransportMasterServiceDetails(loc,custdetails);
	}

	public String insertRouteMasterDetails(TransportPojo tpMasterPojo, UserLoggingsPojo userLoggingsVo) {
		return service.insertRouteMasterDetails(tpMasterPojo,userLoggingsVo);
	}

	public String removeRouteMasterDetails(TransportVo vo,UserLoggingsPojo userLoggingsVo) {
		return service.removeRouteMasterDetails(vo,userLoggingsVo);
	}

	public boolean addRoute(TransportForm routeForm) {
		return service.addRoute(routeForm);
	}

	public boolean checkrouteNo(TransportPojo Pojo, UserLoggingsPojo userLoggingsVo) {
		return service.checkrouteNo(Pojo,userLoggingsVo);
	}

	public TransportVo editRouteMasterDetails(String routecode, String loc, UserLoggingsPojo userLoggingsVo) {
		return service.editRouteMasterDetails(routecode,loc,userLoggingsVo);
	}

	public List<TransportVo> searchDetails(String SearchName, UserLoggingsPojo custdetails) {
		return service.searchDetails(SearchName,custdetails);
	}

	public List<TransportVo> getTransportMasterDetailsXLS() {
		return service.getTransportMasterServiceDetailsXLS();
	}

	// route master end

	public List<DriverMsaterListVo> getdriverList(UserLoggingsPojo custdetails) {

		return service.getdriverListService(custdetails);
	}

	public DriverMsaterListVo editDriverBD(DriverMsaterListVo drivervo, UserLoggingsPojo custdetails) {
		return service.editDriverService(drivervo,custdetails);
	}

	public String deleteDriverBD(DriverMsaterListVo vo,UserLoggingsPojo userLoggingsVo) {
		return service.deleteDriverService(vo,userLoggingsVo);
	}

	public ArrayList<DriverMsaterListVo> searchDriverBD(String searchName) {
		return service.searchDriverService(searchName);
	}

	public boolean validateDriverBD(DriverMsaterListVo drivervo, UserLoggingsPojo custdetails) {
		return service.validateDriverService(drivervo,custdetails);
	}

	public ArrayList<DriverMsaterListVo> getDriverNamesDetails(VehicleDetailsVO vo, UserLoggingsPojo custdetails) {
		return service.getDriverNamesDetails(vo,custdetails);
	}

	public ArrayList<DriverMsaterListVo> getDriverEntireDetails(String driverid, UserLoggingsPojo custdetails) {
		return service.getDriverEntireDetails(driverid,custdetails);
	}

	public ArrayList<DriverMsaterListVo> getDriverDetailsWhileUpdate(
			String vehiclecode) {
		return service.getDriverDetailsWhileUpdate(vehiclecode);
	}

	public DriverMsaterListVo getSingleDriverDetails(String vehiclecode, UserLoggingsPojo userLoggingsVo) {
		return service.getSingleDriverDetails(vehiclecode,userLoggingsVo);
	}

	public ArrayList<StageMasterVo> getStopNames(String searchTerm, UserLoggingsPojo custdetails) {
		return service.getStopNames(searchTerm,custdetails);
	}

	public ArrayList<TransportVo> getRouteDetails() {
		return service.getRouteDetails();
	}

	public ArrayList<TransportVo> GetRouteEntireDetails(String route, UserLoggingsPojo pojo) {
		return service.GetRouteEntireDetails(route,pojo);
	}

	public TransportVo getRouteDetails(String vehiclecode,UserLoggingsPojo userLoggingsVo) {
		return service.getRouteDetails(vehiclecode,userLoggingsVo);
	}

	public boolean addDriverBD(DriverMsaterListVo drivervo,String createuser, UserLoggingsPojo custdetails) {
		return service.addDriverService( drivervo,createuser,custdetails);
	}

	public List<TransportVo> editRouteStages(TransportPojo tpMasterPojo, UserLoggingsPojo userLoggingsVo) {
		return service.editRouteStages(tpMasterPojo,userLoggingsVo);
	}

	public List<TransportVo> unmappedRouteStages(TransportPojo tpMasterPojo, UserLoggingsPojo userLoggingsVo) {
		return service.unmappedRouteStages(tpMasterPojo,userLoggingsVo);
	}


	public ArrayList<TransportVo> getRouteDetailsByLocation(String transferlocation, UserLoggingsPojo pojo) {
		return service.getRouteDetailsByLocation(transferlocation,pojo);
	}

	public List<VehicleDetailsVO> searchVehicleDetails(String searchName,UserLoggingsPojo custdetails,String locId, String status) {
		return service.searchvehicledetails(searchName,custdetails,locId,status);
	}

	public ArrayList<TransportVo> getRouteDetailsByName(VehicleDetailsVO vo, UserLoggingsPojo pojo) {
		return service.getRouteDetailsByName(vo,pojo);
	}

	public boolean validateLicenseBD(DriverMsaterListVo drivervo, UserLoggingsPojo custdetails) {
		return service.validateLicenseService(drivervo,custdetails);
	}

	public ArrayList<TransportVo> getStudentBusCardDetails(TransportVo obj) {
		return service.getStudentBusCardDetails(obj);
	}
	
	public List<VehicleTypeVo> searchVehicletypeDetails(String searchName, UserLoggingsPojo custdetails) {
			return service.searchVehicletypeDetails(searchName,custdetails);
	}

	public String insertVehicleType(TransportCategoryForm form,String createCode, UserLoggingsPojo custdetails) {
		return service.insertVehicleType(form,createCode,custdetails);
	}

	public List<VehicleTypeVo> getAllvehicletypeDetails(UserLoggingsPojo custdetails) {
		return service.getAllvehicletypeDetails(custdetails);
	}

	public VehicleTypeVo edittransporttype(String transportCatergory,UserLoggingsPojo userLoggingsVo) {
		return service.edittransporttype(transportCatergory,userLoggingsVo);
	}

	public String deleteVehicleType(TransportVo vo,UserLoggingsPojo custdetails) {
		return service.deleteVehicleType(vo,custdetails);
	}

	public String validateVehicleType(VehicleTypeVo vehicleadd, UserLoggingsPojo custdetails) {
		return service.validateVehicleType(vehicleadd,custdetails);
	}

	public TransportVo gettransportdetailsstudentwise(
			TransportVo tvo, UserLoggingsPojo userLoggingsVo) {
		return service.gettransportdetailsstudentwise(tvo,userLoggingsVo);
	}

	public ArrayList<TransportVo> settranporttermdetailsforstudent(
			TransportVo tvo, UserLoggingsPojo userLoggingsVo) {
		return service.settranporttermdetailsforstudent(tvo,userLoggingsVo);
	}

	public ArrayList<TransportVo> getRouteNamelist(TransportVo tvo, UserLoggingsPojo userLoggingsVo) {
		return service.getRouteNamelist(tvo,userLoggingsVo);
	}

	public ArrayList<TransportVo> getstoplist(TransportVo tvo, UserLoggingsPojo userLoggingsVo) {
		return service.getstoplist(tvo,userLoggingsVo);
	}

	public String getamountandstatus(TransportVo tvo, UserLoggingsPojo userLoggingsVo) {
		return service.getamountandstatus(tvo,userLoggingsVo);

	}

	public String savetransportrequest(TransportPojo pojo, UserLoggingsPojo custdetails) {
		return service.savetransportrequest(pojo,custdetails);

	}

	public List<StudentRegistrationVo> searchAllAcademicYearDetailsTrans(String locationId, String accYear, UserLoggingsPojo custdetails) {
		return service.searchAllAcademicYearDetailsTrans(locationId,accYear,custdetails);
	}

	public java.util.List<StudentRegistrationVo> getStudentList(String academic_year, String schoolLocation) {
		return service.getStudentList(academic_year,schoolLocation);
	}

	public List<StudentRegistrationVo> getStudentListByClass(String locationid, String accyear, String classname) {
		return service.getStudentListByClass(locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,
			String sectionid) {
		return service.getStudentListBySection(locationid,accyear,classname,sectionid);
	}

	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm) {
		return service.getStudentSearchByList(searchTerm);
	}

	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear) {
		return service.getStudentSearchListByAccYear(searchTerm,accyear);
	}

	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid) {
		return service.getStudentSearchListByLocation(searchTerm,locationid);
	}

	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear,
			String classname) {
		return service.getStudentSearchByFilter(searchTerm,locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,
			String classname) {
		return service.getStudentSearchByClass(searchTerm,locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,
			String classname, String sectionid) {
		return service.getStudentSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
	}

	public String waivedOfftransportrequest(TransportPojo pojo, UserLoggingsPojo userLoggingsVo) {
		return service.waivedOfftransportrequest(pojo,userLoggingsVo);
	}

	public ArrayList<TransportVo> getMonthList(String accyear, UserLoggingsPojo userLoggingsVo, String loc_id) {
		return service.getMonthList(accyear,userLoggingsVo,loc_id);
	}

	public ArrayList<ExaminationDetailsVo> schoolListStage(String loc, UserLoggingsPojo custdetails) {
		return service.schoolListStage(loc,custdetails);
		}

	public ArrayList<StageMasterVo> getStage(String accyear, String loc, UserLoggingsPojo userLoggingsVo) {
		return service.getStage(accyear,loc,userLoggingsVo);
	}

	public ArrayList<ExaminationDetailsVo> schoolList(String accyear, String loc,UserLoggingsPojo userLoggingsVo) {
		return service.schoolList(accyear,loc,userLoggingsVo);
	}

	public ArrayList<AcadamicYearVO2> getPreAcc(String accyear, String loc, UserLoggingsPojo userLoggingsVo) {
		return service.getPreAcc(accyear,loc,userLoggingsVo);
	}

	public String addStageWiseAmount(String accyear, String loc, String[] stageid, String[] amount, String user,
			UserLoggingsPojo userLoggingsVo, String log_audit_session) {
		return service.addStageWiseAmount(accyear,loc,stageid,amount,user,userLoggingsVo,log_audit_session);
	}

	public ArrayList<AcadamicYearVO2> getPresentAndNextAccyear(String accyear, String loc,
			UserLoggingsPojo userLoggingsVo) {
		return service.getPresentAndNextAccyear(accyear,loc,userLoggingsVo);
	}

	public ArrayList<ExaminationDetailsVo> schoolListRoutMaster(String loc, UserLoggingsPojo userLoggingsVo) {
		return service.schoolListRoutMaster(loc,userLoggingsVo);
	}

	public List<TransportVo> routeMasterSettingBySearch(TransportVo vo, UserLoggingsPojo custdetails) {
		return service.routeMasterSettingBySearch(vo,custdetails);
	}

	public List<TransportVo> transportCategoryListByStatus(TransportVo vo, UserLoggingsPojo custdetails) {
		return service.transportCategoryListByStatus(vo,custdetails);
	}

	public List<DriverMsaterListVo> driverListByStatus(DriverMsaterListVo vo, UserLoggingsPojo custdetails) {
		return service.driverListByStatus(vo,custdetails);
	}

	public String saveTransportConcession(StudentConcessionVo vo, UserLoggingsPojo userLoggingsVo) {
		return service.saveTransportConcession(vo,userLoggingsVo);
	}

	public String valideDriverCode(String drivername, String locId, UserLoggingsPojo custdetails) {
		
		return service.valideDriverCode(drivername,locId,custdetails);
	}

	public String getTransportYearCollection(UserLoggingsPojo userLoggingsVo, String accyear, String location_id) {
		
		return service.getTransportYearCollection( userLoggingsVo,  accyear,  location_id);
	}

	public String getTodayCollection(UserLoggingsPojo userLoggingsVo, String location_id) {
		return service.getTodayCollection( userLoggingsVo,  location_id);
	}

}
