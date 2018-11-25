package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.TransportCategoryForm;
import com.centris.campus.forms.TransportForm;
import com.centris.campus.pojo.TransportPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
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

public interface TransportDao {

	public ArrayList<VehicleDetailsVO> getAllvehicleDetails(UserLoggingsPojo custdetails, String status, String locId);

	public String saveVehicleDetails(VehicleDetailsVO transportForm,String createUser, String vehiclecode, UserLoggingsPojo userLoggingsVo);

	public boolean checkingVehicleInsuranceDate(VehicleDetailsVO vehiclecode);

	public VehicleDetailsVO getSingleVehicleDetails(String vehiclecode, UserLoggingsPojo userLoggingsVo);

	public String deleteVehicleDetails(String[] vehiclecode,String log_audit_session,UserLoggingsPojo userLoggingsVo, VehicleDetailsVO vo, String button);

	public String registernumberValidation(String vehicleregno, String locId, UserLoggingsPojo userLoggingsVo, String vehicleId);

	public String updateregisternumberValidation(String vehicleregno,
			String vehicleCode, String locId, UserLoggingsPojo userLoggingsVo);

	public boolean chassisnovalidationvalidation(String chassisno, String locId, UserLoggingsPojo userLoggingsVo);

	public boolean updatechassisnovalidation(String chassisno,String vehicleCode, String locId, UserLoggingsPojo userLoggingsVo);

	public ArrayList<VehicleDetailsVO> searchvehicledetails(String searchTerm, UserLoggingsPojo custdetails, String locId, String status);

	public boolean checkforduplicateAddTime(VehicleDetailsVO obj, UserLoggingsPojo custdetails);

	public boolean checkforduplicateUpdateTime(VehicleDetailsVO obj, UserLoggingsPojo userLoggingsVo);

	// route master start
	public List<TransportPojo> getTransportMasterDaoDetails(String loc, UserLoggingsPojo custdetails);
	
	public List<TransportPojo> getTransportMasterDaoDetailsXLS();


	public int getTpRouteCheckDao(String tpName);

	public String insertRouteMasterDetails(TransportPojo tpPojo, UserLoggingsPojo userLoggingsVo);

	public String removeRouteMasterDetails(TransportVo vo,UserLoggingsPojo userLoggingsVo);

	public boolean addRoute(TransportForm routeForm);

	public boolean checkrouteNo(TransportPojo Pojo, UserLoggingsPojo userLoggingsVo);

	public TransportVo editRouteMasterDetails(String routecode, String loc, UserLoggingsPojo userLoggingsVo);

	public List<TransportVo> searchDetails(String SearchName, UserLoggingsPojo custdetails);


	// route master end

	public ArrayList<DriverMsaterListVo> getdriverListDao(UserLoggingsPojo custdetails);

	public boolean addDriverDao(DriverMsaterListVo drivervo, String createuser, UserLoggingsPojo custdetails);

	public DriverMsaterListVo editDriverDao(DriverMsaterListVo drivervo, UserLoggingsPojo custdetails);

	public String deleteDriverDao(DriverMsaterListVo vo,UserLoggingsPojo custdetails);

	public ArrayList<DriverMsaterListVo> searchDriverDao(String searchName);

	public boolean updateDriverDao(DriverMsaterListVo drivervo, String createuser, UserLoggingsPojo custdetails);

	public boolean validateDriverDao(DriverMsaterListVo drivervo, UserLoggingsPojo custdetails);

	public ArrayList<DriverMsaterListVo> getDriverNamesDetails(VehicleDetailsVO vo, UserLoggingsPojo custdetails);

	public ArrayList<DriverMsaterListVo> getDriverEntireDetails(String driverid, UserLoggingsPojo custdetails);

	public ArrayList<DriverMsaterListVo> getDriverDetailsWhileUpdate(
			String vehiclecode);

	public DriverMsaterListVo getSingleDriverDetails(String vehiclecode, UserLoggingsPojo userLoggingsVo);

	public ArrayList<StageMasterVo> getStopNames(String searchTerm, UserLoggingsPojo custdetails);

	public ArrayList<TransportVo> getRouteDetails();

	public ArrayList<TransportVo> GetRouteEntireDetails(String route, UserLoggingsPojo pojo);

	public TransportVo getRouteDetails(String vehiclecode, UserLoggingsPojo userLoggingsVo);

	public List<TransportVo> editRouteStages(TransportPojo tpMasterPojo, UserLoggingsPojo userLoggingsVo);

	public List<TransportVo> unmappedRouteStages(TransportPojo tpMasterPojo, UserLoggingsPojo userLoggingsVo);

	public ArrayList<TransportVo> getRouteDetailsByLocation(String transferlocation, UserLoggingsPojo pojo);

	public ArrayList<TransportVo> getRouteDetailsByName(VehicleDetailsVO vo, UserLoggingsPojo pojo);

	public boolean validateLicenseDao(DriverMsaterListVo drivervo, UserLoggingsPojo custdetails);

	public ArrayList<TransportVo> getStudentBusCardDetails(TransportVo obj);

	public String insertVehicleType(TransportCategoryForm form, String createCode, UserLoggingsPojo custdetails);

	public List<VehicleTypeVo> getAllvehicletypeDetails(UserLoggingsPojo custdetails);

	public VehicleTypeVo edittransporttype(String transportCatergory,UserLoggingsPojo userLoggingsVo);
	
	public String deleteVehicleType(TransportVo vo,UserLoggingsPojo custdetails);

	public String validateVehicleType(VehicleTypeVo vehicleadd, UserLoggingsPojo custdetails);

	public List<VehicleTypeVo> searchVehicletypeDetails(String searchName, UserLoggingsPojo custdetails);

	public TransportVo gettransportdetailsstudentwise(TransportVo tvo, UserLoggingsPojo userLoggingsVo);

	public ArrayList<TransportVo> settranporttermdetailsforstudent(
			TransportVo tvo, UserLoggingsPojo userLoggingsVo);

	public ArrayList<TransportVo> getRouteNamelist(TransportVo tvo, UserLoggingsPojo userLoggingsVo);

	public ArrayList<TransportVo> getstoplist(TransportVo tvo, UserLoggingsPojo userLoggingsVo);

	public String getamountandstatus(TransportVo tvo, UserLoggingsPojo userLoggingsVo);

	public String savetransportrequest(TransportPojo pojo, UserLoggingsPojo custdetails);

	public List<StudentRegistrationVo> searchAllAcademicYearDetailsTrans(String locationId, String accYear,UserLoggingsPojo custdetails);

	public List<StudentRegistrationVo> getStudentList(String academic_year, String schoolLocation);

	public List<StudentRegistrationVo> getStudentListByClass(String locationid, String accyear, String classname);

	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname, String sectionid);

	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm);

	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear);

	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid);

	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear,
			String classname);

	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,
			String classname);

	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,
			String classname, String sectionid);

	public String waivedOfftransportrequest(TransportPojo pojo, UserLoggingsPojo userLoggingsVo);

	public ArrayList<TransportVo> getMonthList(String accyear, UserLoggingsPojo userLoggingsVo, String loc_id);

	public ArrayList<ExaminationDetailsVo> schoolListStage(String loc, UserLoggingsPojo custdetails);

	public ArrayList<StageMasterVo> getStage(String accyear, String loc, UserLoggingsPojo userLoggingsVo);

	public ArrayList<ExaminationDetailsVo> schoolList(String accyear, String loc,UserLoggingsPojo userLoggingsVo);

	public ArrayList<AcadamicYearVO2> getPreAcc(String accyear, String loc, UserLoggingsPojo userLoggingsVo);

	public String addStageWiseAmount(String accyear, String loc, String[] stageid, String[] amount, String user,
			UserLoggingsPojo userLoggingsVo, String log_audit_session);

	public ArrayList<AcadamicYearVO2> getPresentAndNextAccyear(String accyear, String loc,
			UserLoggingsPojo userLoggingsVo);

	public ArrayList<ExaminationDetailsVo> schoolListRoutMaster(String loc, UserLoggingsPojo userLoggingsVo);

	public List<TransportVo> routeMasterSettingBySearch(TransportVo vo, UserLoggingsPojo custdetails);

	public List<TransportVo> transportCategoryListByStatus(TransportVo vo, UserLoggingsPojo custdetails);

	public List<DriverMsaterListVo> driverListByStatus(DriverMsaterListVo vo, UserLoggingsPojo custdetails);

	public String saveTransportConcession(StudentConcessionVo vo, UserLoggingsPojo userLoggingsVo);

	public String valideDriverCode(String drivername, String locId, UserLoggingsPojo custdetails);

	public String getTransportYearCollection(UserLoggingsPojo userLoggingsVo, String accyear, String location_id);

	public String getTodayCollection(UserLoggingsPojo userLoggingsVo, String location_id);

	

	

/*	public ArrayList<TransportVo> GetRouteEntireDetails(TransportVo transportVo);
*/}
