package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.DriverVo;
import com.centris.campus.vo.VehicleDriverMappingPojo;
import com.centris.campus.vo.VehicleDriverMappingVo;

public interface VehicleDriverMappingService {
	
	public ArrayList<DriverVo> getAllDriversDetails();
	public ArrayList<VehicleDriverMappingVo> getAvailableVehicles();
	public ArrayList<VehicleDriverMappingVo> getMappedVehicles(String driverID);
	public String mapVehicle(VehicleDriverMappingPojo pojo);
	public ArrayList<VehicleDriverMappingVo> getVehicleDriverMappingList();
	public VehicleDriverMappingVo editVehicleDriverMapping(VehicleDriverMappingPojo pojo);
	public String updateMappedVehicle(VehicleDriverMappingPojo pojo);
	public ArrayList<VehicleDriverMappingVo> getSearchVehicleDriverMappingList(String searchTerm,UserLoggingsPojo userLoggingsVo);
	public String deleteVehicleDriverMapping(VehicleDriverMappingPojo pojo);

}
