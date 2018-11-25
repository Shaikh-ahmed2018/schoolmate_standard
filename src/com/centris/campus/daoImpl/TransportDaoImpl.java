package com.centris.campus.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.TransportDao;
import com.centris.campus.forms.TransportCategoryForm;
import com.centris.campus.forms.TransportForm;
import com.centris.campus.pojo.TransportPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.TransportUtilConstants;
import com.centris.campus.vo.AcadamicYearVO2;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.VehicleDetailsVO;
import com.centris.campus.vo.VehicleTypeVo;

public class TransportDaoImpl implements TransportDao {

	private static final Logger logger = Logger
			.getLogger(TransportDaoImpl.class);

	public ArrayList<VehicleDetailsVO> getAllvehicleDetails(UserLoggingsPojo custdetails,String status,String locId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getAllvehicleDetails Starting");
		ArrayList<VehicleDetailsVO> getvehiclelist = new ArrayList<VehicleDetailsVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		int sno = 0;
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = connection.prepareStatement(TransportUtilConstants.GET_ALL_VEHICLE_DETAILS);
			pstmt.setString(1,status);
			pstmt.setString(2, locId);

			//System.out.println("GET_ALL_VEHICLE_DETAILS  -->>"+pstmt);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;
				VehicleDetailsVO detailsVO = new VehicleDetailsVO();
				detailsVO.setSno(String.valueOf(sno));
				detailsVO.setVehiclecode(rs.getString("VehicleCode"));
				detailsVO.setVehicleregno(rs.getString("Vehicle_Reg_No"));
				detailsVO.setVehiclename(rs.getString("VehicleName"));
				detailsVO.setVehicletype(rs.getString("VehicleType"));
				detailsVO.setPermitvalidity(HelperClass.convertDatabaseToUI(rs.getString("Permit_validity")));
				detailsVO.setDriverName(rs.getString("DriverName"));
				detailsVO.setRoutename(rs.getString("RouteName"));
				detailsVO.setReson(rs.getString("Remark"));
				getvehiclelist.add(detailsVO);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getAllvehicleDetails  Ending");
		return getvehiclelist;
	}

	public String saveVehicleDetails(VehicleDetailsVO transportForm,
			String createUser, String vehiclecode,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : saveVehicleDetails Starting");
		int count = 0;
		String status = "";
		PreparedStatement pstmt = null;
		Connection connection = null;
		PreparedStatement updatepstmt = null;

		PreparedStatement updatepstmt2 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt3 = null;

		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt4 = null;

		try {

			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			System.out.println("Status in DIOMPL:: "+transportForm.getStatus());
			
			if (!transportForm.getStatus().equals("update")) {
				
				pstmt = connection.prepareStatement(TransportUtilConstants.SAVE_VEHICLE_DETAILS);
				
				System.out.println("In DIOMPL RC file "+transportForm.getRcfile());
				pstmt.setString(1, vehiclecode);
				pstmt.setString(2, transportForm.getVehicleregno());
				pstmt.setString(3, transportForm.getVehiclename());
				pstmt.setString(4, transportForm.getEnginenumber());
				pstmt.setString(5, transportForm.getChassisno());
				pstmt.setString(6, transportForm.getVehicletype());
				pstmt.setString(7, HelperClass.convertUIToDatabase(transportForm.getTaxpaid()));
				pstmt.setString(8, HelperClass.convertUIToDatabase(transportForm.getTaxexpirydate()));
				pstmt.setString(9, HelperClass.convertUIToDatabase(transportForm.getPollution()));
				pstmt.setString(10, transportForm.getRcfile());
				pstmt.setString(11, createUser);
				pstmt.setString(12, transportForm.getLocid());
				System.out.println("VEHICLE_DETAILS::"+pstmt);

				count = pstmt.executeUpdate();
				if(count>0){
					HelperClass.recordLog_Activity(transportForm.getLog_audit_session(),"Transport","Vehicle Master","Insert",pstmt.toString(),userLoggingsVo);
			
				}

				pstmt1 = connection.prepareStatement(TransportUtilConstants.SAVE_VEHICLE_INSURANCE_DETAILS);
			
				
				pstmt1.setString(1, vehiclecode);
				pstmt1.setString(2, transportForm.getCompanyname());
				pstmt1.setString(3, HelperClass.convertUIToDatabase(transportForm.getIssueddate()));
				pstmt1.setString(4, HelperClass.convertUIToDatabase(transportForm.getExpirydate()));
				pstmt1.setString(5, transportForm.getDoneby());
				pstmt1.setString(6, HelperClass.convertUIToDatabase(transportForm.getFc()));
				pstmt1.setString(7, HelperClass.convertUIToDatabase(transportForm.getPermitvalidity()));
				pstmt1.setString(8, transportForm.getInsurancefile());
				pstmt1.setString(9, createUser);
				
				System.out.println("11111111111111111111"+pstmt1);
				
		      /*  pstmt.setString(7, createUser);*/

				count = pstmt1.executeUpdate();
				if(count>0){
					HelperClass.recordLog_Activity(transportForm.getLog_audit_session(),"Transport","Vehicle Master","Insert",pstmt1.toString(),userLoggingsVo);
				}
				System.out.println(transportForm.getDriverCode().length());
				
				if (transportForm.getDriverCode() == ""|| transportForm.getDriverCode() == null||transportForm.getDriverCode().length()==0) 
				
				{
					
					
				}
				
				else
				{
					updatepstmt2 = connection.prepareStatement(TransportUtilConstants.MAP_DRIVER_VEHICLE_INSERTING);
					
					updatepstmt2.setString(1, transportForm.getDriverCode());
					updatepstmt2.setString(2, vehiclecode);
					updatepstmt2.setString(3, createUser);
					System.out.println("DIRverCOde in MAP_DRIVER_VEHICLE_INSERTING:  "+updatepstmt2);
					count=updatepstmt2.executeUpdate();
					if(count > 0){
						HelperClass.recordLog_Activity(transportForm.getLog_audit_session(),"Transport","Vehicle Master","Insert",updatepstmt2.toString(),userLoggingsVo);
					}
				}
				if (transportForm.getRoutecodeid() == ""|| transportForm.getRoutecodeid() == null||transportForm.getRoutecodeid().length()==0) 
					
				{
					
					
				}
				else{
					pstmt3 = connection.prepareStatement(TransportUtilConstants.MAP_DRIVER_VEHICLE_ROUTE_MAPPING);
					
					pstmt3.setString(1, transportForm.getRoutecodeid());
					pstmt3.setString(2, vehiclecode);
					pstmt3.setString(3, createUser);
					System.out.println("222222222222222"+pstmt3);
					int count1=pstmt3.executeUpdate();
					System.out.println("DIRverCOde in MAP_DRIVER_VEHICLE_INSERTING:  "+pstmt3);
					
					if(count1 > 0){
						HelperClass.recordLog_Activity(transportForm.getLog_audit_session(),"Transport","Vehicle Master","Insert",pstmt3.toString(),userLoggingsVo);
					}
				}
				
				
				if (count > 0) {
					status = "save";
				}
			} 
			
			else {
				System.out.println("else updTE");
				
				System.out.println("In DIOMPL RC file (update):::  "+transportForm.getRcfile());
				
				updatepstmt = connection.prepareStatement(TransportUtilConstants.UPDATE_VEHICLE_DETAILS);

				
				updatepstmt.setString(1, transportForm.getVehicleregno());
				updatepstmt.setString(2, transportForm.getVehiclename());
				updatepstmt.setString(3, transportForm.getEnginenumber());
				updatepstmt.setString(4, transportForm.getChassisno());
				updatepstmt.setString(5, transportForm.getVehicletype());
				updatepstmt.setString(6, HelperClass.convertUIToDatabase(transportForm.getTaxpaid()));
				updatepstmt.setString(7, HelperClass.convertUIToDatabase(transportForm.getTaxexpirydate()));
				updatepstmt.setString(8, HelperClass.convertUIToDatabase(transportForm.getPollution()));
				updatepstmt.setString(9, transportForm.getRcfile());
				updatepstmt.setTimestamp(10, HelperClass.getCurrentTimestamp());
				updatepstmt.setString(11, createUser);
				updatepstmt.setString(12, transportForm.getLocid());
				updatepstmt.setString(13, transportForm.getUpdateVehicleCode());
				
				System.out.println("Vehicle Update: "+updatepstmt);
				count = updatepstmt.executeUpdate();
				if(count > 0){
					HelperClass.recordLog_Activity(transportForm.getLog_audit_session(),"Transport","Vehicle Master","Update",updatepstmt.toString(),userLoggingsVo);
				}
				updatepstmt = connection.prepareStatement(TransportUtilConstants.UPDATE_VEHICLE_INSURANCE_DETAILS);
		
				
				updatepstmt.setString(1, transportForm.getCompanyname());
				updatepstmt.setString(2, HelperClass.convertUIToDatabase(transportForm.getIssueddate()));
				updatepstmt.setString(3, HelperClass.convertUIToDatabase(transportForm.getExpirydate()));
				updatepstmt.setString(4, transportForm.getDoneby());
				updatepstmt.setString(5, HelperClass.convertUIToDatabase(transportForm.getFc()));
				updatepstmt.setString(6, HelperClass.convertUIToDatabase(transportForm.getPermitvalidity()));
				updatepstmt.setString(7, transportForm.getInsurancefile());
				updatepstmt.setString(8, createUser);
				updatepstmt.setTimestamp(9, HelperClass.getCurrentTimestamp());
				updatepstmt.setString(10, transportForm.getUpdateVehicleCode());
				
				System.out.println("Insurance Update: "+updatepstmt);
				count = updatepstmt.executeUpdate();
				if(count > 0){
					HelperClass.recordLog_Activity(transportForm.getLog_audit_session(),"Transport","Vehicle Master","Update",updatepstmt.toString(),userLoggingsVo);
				}
				if (transportForm.getDriverCode() != ""	|| transportForm.getDriverCode() != null) {

					pstmt2 = connection.prepareStatement(TransportUtilConstants.CHECKING_DRIVER);

					pstmt2.setString(1, transportForm.getDriverCode());
                    System.out.println("00000000000000"+pstmt2);
					ResultSet rst = pstmt2.executeQuery();
					int count1 = 0;
					while (rst.next()) {
						count1 = rst.getInt(1);
					}
                    
					if(transportForm.getDriverCode() == ""|| transportForm.getDriverCode() == null||transportForm.getDriverCode().length()==0){
						
					}
					else{
					if (count1 > 0) {
						updatepstmt2 = connection.prepareStatement(TransportUtilConstants.MAP_DRIVER_VEHICLE_WHILE_UPDATE);
						updatepstmt2.setString(1, transportForm.getDriverCode());
						updatepstmt2.setString(2,transportForm.getUpdateVehicleCode());
						updatepstmt2.setString(3, createUser);
						updatepstmt2.setString(4,transportForm.getUpdateVehicleCode());
                        System.out.println("333333333333333333"+updatepstmt2);
						int count2=updatepstmt2.executeUpdate();
						if(count2 > 0){
							HelperClass.recordLog_Activity(transportForm.getLog_audit_session(),"Transport","Vehicle Master","Update",updatepstmt2.toString(),userLoggingsVo);
						}
					} else {
						updatepstmt2 = connection.prepareStatement(TransportUtilConstants.MAP_DRIVER_VEHICLE_INSERTING);
						updatepstmt2.setString(1, transportForm.getDriverCode());
						updatepstmt2.setString(2,transportForm.getUpdateVehicleCode());
						updatepstmt2.setString(3, createUser);
						System.out.println("44444444444444"+updatepstmt2);
						int count2=updatepstmt2.executeUpdate();
						if(count2 > 0){
							HelperClass.recordLog_Activity(transportForm.getLog_audit_session(),"Transport","Vehicle Master","Insert",updatepstmt2.toString(),userLoggingsVo);
						}
					}
				}
				}
				pstmt4 = connection.prepareStatement(TransportUtilConstants.CHECK_VEHICLE_MAPPING);
				pstmt4.setString(1, transportForm.getUpdateVehicleCode());
				System.out.println("5555555555555"+pstmt4);
				ResultSet rs = pstmt4.executeQuery();
				int cou = 0;
				while (rs.next()) {
					cou = rs.getInt(1);
				 System.out.println("000000000"+cou);
				}
                if (transportForm.getRoutecodeid() == ""|| transportForm.getRoutecodeid() == null||transportForm.getRoutecodeid().length()==0) 
					
				{
					
					
				}
               
                else{
				if (cou > 0) {
					pstmt3 = connection.prepareStatement(TransportUtilConstants.MAP_DRIVER_VEHICLE_ROUTE_MAPPING_UPDATING);
					pstmt3.setString(1, transportForm.getRoutecodeid());
					pstmt3.setString(2, transportForm.getUpdateVehicleCode());
					pstmt3.setString(3, createUser);
					pstmt3.setString(4, transportForm.getUpdateVehicleCode());
					System.out.println("66666666666"+pstmt3);
					int count2=pstmt3.executeUpdate();
					if(count2 > 0){
						HelperClass.recordLog_Activity(transportForm.getLog_audit_session(),"Transport","Vehicle Master","Update",pstmt3.toString(),userLoggingsVo);
					}
				} else {
					pstmt3 = connection.prepareStatement(TransportUtilConstants.MAP_DRIVER_VEHICLE_ROUTE_MAPPING);
					pstmt3.setString(1, transportForm.getRoutecodeid());
					pstmt3.setString(2, transportForm.getUpdateVehicleCode());
					pstmt3.setString(3, createUser);
					System.out.println("777777777777777"+pstmt3);
					int count2=pstmt3.executeUpdate();
					if(count2 > 0){
					HelperClass.recordLog_Activity(transportForm.getLog_audit_session(),"Transport","Vehicle Master","Insert",pstmt3.toString(),userLoggingsVo);
					}
				}
                }
				if (count > 0) {
					status = "update";
				}
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (updatepstmt != null && (!updatepstmt.isClosed())) {
					updatepstmt.close();
				}
				if (updatepstmt2 != null && (!updatepstmt2.isClosed())) {
					updatepstmt2.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt3 != null && (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt4 != null && (!pstmt4.isClosed())) {
					pstmt4.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : saveVehicleDetails  Ending");
		System.out.println("status"+status);
		return status;
	}

	public boolean checkingVehicleInsuranceDate(VehicleDetailsVO vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : checkingVehicleInsuranceDate Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection
					.prepareStatement(TransportUtilConstants.CHECKING_VEHICLE_INSURANCE_DATE);
			pstmt.setString(1, vehiclecode.getVehiclecode());
			pstmt.setString(2, HelperClass.convertUIToDatabase(vehiclecode
					.getIssueddate()));
			pstmt.setString(3, HelperClass.convertUIToDatabase(vehiclecode
					.getExpirydate()));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : checkingVehicleInsuranceDate  Ending");
		return status;
	}

	public VehicleDetailsVO getSingleVehicleDetails(String vehiclecode,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getSingleVehicleDetails Starting");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		VehicleDetailsVO obj = null;
		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = connection.prepareStatement(TransportUtilConstants.GET_VEHCILE_INSURANCE_DETAILS);
			pstmt.setString(1, vehiclecode);
			
			
			System.out.println("edit vehicle pstmt::"+pstmt);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				obj = new VehicleDetailsVO();
				obj.setVehiclecode(rs.getString("VehicleCode"));
				obj.setVehicleregno(rs.getString("Vehicle_Reg_No"));
				obj.setVehiclename(rs.getString("VehicleName"));
				obj.setEnginenumber(rs.getString("Engine_Number"));
				obj.setChassisno(rs.getString("Chassis_No"));
				obj.setVehicletype(rs.getString("VehicleType"));
				obj.setTaxpaid(HelperClass.convertDatabaseToUI(rs.getString("Tax_Paid")));
				obj.setTaxexpirydate(HelperClass.convertDatabaseToUI(rs.getString("TaxExpiryDate")));
				obj.setPollution(HelperClass.convertDatabaseToUI(rs.getString("Pollution")));
				obj.setCompanyname(rs.getString("CompanyName"));
				obj.setIssueddate(HelperClass.convertDatabaseToUI(rs.getString("IssuedDate")));
				obj.setExpirydate(HelperClass.convertDatabaseToUI(rs.getString("ExpiryDate")));
				obj.setDoneby(rs.getString("doneby"));
				obj.setFc(HelperClass.convertDatabaseToUI(rs.getString("Fc")));
				obj.setPermitvalidity(HelperClass.convertDatabaseToUI(rs.getString("Permit_validity")));
				obj.setRcfile(rs.getString("RCFileUpload"));
				obj.setInsurancefile(rs.getString("InsuranceFleUpload"));
				obj.setLocid(rs.getString("Location_Id"));
				obj.setStatus("update");
				System.out.println(""+rs.getString("RCFileUpload"));
				System.out.println(""+rs.getString("InsuranceFleUpload"));
			}
/*
			System.out.println("regditration no in dao "
					+ obj.getVehicleregno());
			*/
			

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getSingleVehicleDetails  Ending");
		return obj;
	}

	@SuppressWarnings("resource")
	public String deleteVehicleDetails(String[] vehiclecode,String log_audit_session,UserLoggingsPojo userLoggingsVo,VehicleDetailsVO vo, String button) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : deleteVehicleDetails Starting");
		int count = 0;
		
		String status = null;
		PreparedStatement pstmt = null;

		Connection connection = null;
		
		try {
			
			for(int i=0;i<vehiclecode.length;i++){
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = connection.prepareStatement(TransportUtilConstants.DELETE_VEHICLE_DETAILS);
				pstmt.setString(1, vo.getStatus());
				pstmt.setString(2, vo.getReson());
				pstmt.setString(3, vehiclecode[i]);
				System.out.println("vehicle code while Deleting"+pstmt);
				count = pstmt.executeUpdate();
				
              /*  if(count>0){
                	HelperClass.recordLog_Activity(log_audit_session,"Transport","Vehicle Master",button,pstmt.toString(),custid);
                }
				pstmt = connection.prepareStatement(TransportUtilConstants.DELETE_INSURANCE_DETAILS);
				pstmt.setString(1, vo.getStatus());
				pstmt.setString(2, vo.getReson());
				pstmt.setString(3, vehiclecode[i]);
				count = pstmt.executeUpdate();*/
				/*if(count>0){
                	HelperClass.recordLog_Activity(log_audit_session,"Transport","Vehicle Master",button,pstmt.toString(),custid);
                }
				
				pstmt = connection.prepareStatement(TransportUtilConstants.DELETE_DRIVER_VEHICLE_MAPPING);
				pstmt.setString(1, vo.getStatus());
				pstmt.setString(2, vo.getReson());
				pstmt.setString(3, vehiclecode[i]);
				count = pstmt.executeUpdate();
				if(count>0){
                	HelperClass.recordLog_Activity(log_audit_session,"Transport","Vehicle Master",button,pstmt.toString(),custid);
                }
				
				pstmt = connection.prepareStatement(TransportUtilConstants.DELETE_VEHICLE_ROUTE_MAPPIG);
				pstmt.setString(1, vo.getStatus());
				pstmt.setString(2, vo.getReson());
				pstmt.setString(3, vehiclecode[i]);
				count = pstmt.executeUpdate();
				if(count>0){
                	HelperClass.recordLog_Activity(log_audit_session,"Transport","Vehicle Master",button,pstmt.toString(),custid);
                }*/
				if (count > 0 ) {
					HelperClass.recordLog_Activity(log_audit_session,"Transport","Vehicle Master",button,pstmt.toString(),userLoggingsVo);
					 status ="true";
				}
				else{
					status = "false";
				}
			}
			

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : deleteVehicleDetails  Ending");
		return status;
	}

	public String registernumberValidation(String vehicleregno,String locId,UserLoggingsPojo userLoggingsVo,String vehicleId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : registernumberValidation Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		String value=null;
		String result=null;
		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = connection.prepareStatement(TransportUtilConstants.VALIDATE_VEHICLE_REG_NO);

			pstmt.setString(1, vehicleregno.trim()); 
			pstmt.setString(2, vehicleId);
			/*System.out.println("VALIDATE_VEHICLE_REG_NO -->>"+pstmt);*/
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
				value=rs.getString("isActive");
			}
			
			/*if (count > 0 && value.equalsIgnoreCase("N")) {
				result="inactive";

			} else if(count > 0 && value.equalsIgnoreCase("Y")) {

				result="true";
			}*/
			if(count > 0) {
				result="true";
			}
			else{
				result="false";
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : registernumberValidation  Ending");
		return result;
	}

	public String updateregisternumberValidation(String vehicleregno,String vehicleCode,String locId,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateregisternumberValidation Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		String value=null;
		String result=null;
		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = connection.prepareStatement(TransportUtilConstants.VALIDATE_UPDATE_VEHICLE_REG_NO);

			pstmt.setString(1, vehicleregno.trim());
			pstmt.setString(2, vehicleCode); 
			/*pstmt.setString(3, locId);*/
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
				value=rs.getString("isActive");
			}
			if (count > 0 && value.equalsIgnoreCase("N")) {
				result="inactive";
				
				

			} else if(count > 0 && value.equalsIgnoreCase("Y")) {

				result="true";
			}
             
			else{
				result="false";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateregisternumberValidation  Ending");

		return result;
	}

	public boolean chassisnovalidationvalidation(String chassisno,String locId,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : chassisnovalidationvalidation Starting");
		boolean status = false;
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {

			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = connection.prepareStatement(TransportUtilConstants.VALIDATE_VEHICLE_CHASSIS_NO);

			pstmt.setString(1, chassisno);
			pstmt.setString(2, locId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : chassisnovalidationvalidation  Ending");
		return status;
	}

	public boolean updatechassisnovalidation(String chassisno,String vehicleCode,String locId,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updatechassisnovalidation Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = connection.prepareStatement(TransportUtilConstants.VALIDATE_UPDATE_VEHICLE_CHASSIS_NO);

			pstmt.setString(1, chassisno);
			pstmt.setString(2, vehicleCode); 
			pstmt.setString(3, locId);
			System.out.println("7777777777777777"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updatechassisnovalidation  Ending");
		return status;
	}

	public ArrayList<VehicleDetailsVO> searchvehicledetails(String searchTerm,UserLoggingsPojo custdetails,String locId, String status) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchvehicledetails Starting");
		ArrayList<VehicleDetailsVO> getvehiclelist = new ArrayList<VehicleDetailsVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		int sno = 0;
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = connection.prepareStatement(TransportUtilConstants.SEARCH_VEHICLE_DETAILS);
			pstmt.setString(1,  searchTerm + "%");
			pstmt.setString(2,  searchTerm + "%");
			pstmt.setString(3,  searchTerm + "%");
			pstmt.setString(4,  searchTerm + "%");
			pstmt.setString(5,  searchTerm + "%");
			pstmt.setString(6,  searchTerm + "%");
			pstmt.setString(7,  locId);
			pstmt.setString(8,  status);
		  /*System.out.println("SEARCH_VEHICLE_DETAILS -->>"+pstmt);*/
		 
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;
				VehicleDetailsVO detailsVO = new VehicleDetailsVO();
				detailsVO.setSno(String.valueOf(sno));
				detailsVO.setVehiclecode(rs.getString("VehicleCode"));
				detailsVO.setVehicleregno(rs.getString("Vehicle_Reg_No"));
				detailsVO.setVehiclename(rs.getString("VehicleName"));
				detailsVO.setVehicletype(rs.getString("VehicleType"));
				detailsVO.setPermitvalidity(HelperClass.convertDatabaseToUI(rs.getString("Permit_validity")));
				detailsVO.setDriverName(rs.getString("DriverName"));
				detailsVO.setRoutename(rs.getString("RouteName"));
				detailsVO.setReson(rs.getString("Remark"));
				getvehiclelist.add(detailsVO);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchvehicledetails  Ending");
		return getvehiclelist;
	}

	public boolean checkforduplicateAddTime(VehicleDetailsVO obj,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updatechassisnovalidation Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			
			connection = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = connection.prepareStatement(TransportUtilConstants.CHECK_FOR_DUPLICATE_ADD_TIME);
			System.out.println("simple date"+pstmt);
			pstmt.setString(1, obj.getVehicleregno());
			pstmt.setString(2, obj.getVehiclename());
			pstmt.setString(3, obj.getEnginenumber());
			pstmt.setString(4, obj.getChassisno());
			pstmt.setString(5, obj.getVehicletype());
			pstmt.setString(6, HelperClass.convertUIToDatabase(obj.getTaxpaid()));
			pstmt.setString(7,	HelperClass.convertUIToDatabase(obj.getPollution()));
			pstmt.setString(8, obj.getLocid());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				status = true;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updatechassisnovalidation  Ending");
		return status;

	}

	public boolean checkforduplicateUpdateTime(VehicleDetailsVO obj,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : checkforduplicateUpdateTime Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			System.out.println("tax paid "+obj.getTaxpaid());
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = connection.prepareStatement(TransportUtilConstants.CHECK_FOR_DUPLICATE_UPDATE_TIME);
			//Vehicle_Reg_No=? and VehicleName=? and VehicleType=? and Engine_number=? and Chassis_No=? and VehicleType=?  and Tax_Paid=? 
			//and Pollution=? and VehicleCode!=?
			pstmt.setString(1, obj.getVehicleregno());
			pstmt.setString(2, obj.getVehiclename());
			pstmt.setString(3, obj.getEnginenumber());
			pstmt.setString(4, obj.getChassisno());
			pstmt.setString(5, obj.getVehicletype());
			pstmt.setString(6,	HelperClass.convertUIToDatabase(obj.getTaxpaid()));
			pstmt.setString(7,	HelperClass.convertUIToDatabase(obj.getPollution()));
			pstmt.setString(8, obj.getVehiclecode());
			pstmt.setString(9, obj.getLocid());
			System.out.println("check for duplicate data"+pstmt);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				HelperClass.recordLog_Activity(obj.getLog_audit_session(), "Transport", "vehicle master", "insert", obj.toString(),userLoggingsVo);
				status = true;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : checkforduplicateUpdateTime  Ending");
		return status;

	}

	// route master start
	public List<TransportPojo> getTransportMasterDaoDetails(String loc,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportSetupDaoImpl: getTransportMasterDaoDetails Starting");
		ArrayList<TransportPojo> tpMasterPojoList = new ArrayList<TransportPojo>();
		PreparedStatement pstmt = null;
		TransportPojo obj = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = connection.prepareStatement(TransportUtilConstants.ROUTE_ALLLISTDATAS);
			pstmt.setString(1, loc);
			/*System.out.println("ROUTE_ALLLISTDATAS -->>"+pstmt);*/
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {

					obj = new TransportPojo();
					obj.setRouteNo(resultSet.getString("Route_No"));
					obj.setRouteName(resultSet.getString("RouteName"));
					obj.setRouteCode(resultSet.getString("RouteCode"));
					obj.setStartTime(resultSet.getString("Start_Time"));
					obj.setEndTime(resultSet.getString("End_Time"));
					obj.setTotalStops(resultSet.getString("Total_Stops"));
					obj.setTotalDistance(resultSet.getString("TotalDistance"));
					obj.setHalttime(resultSet.getString("HaltTime"));
					//obj.setDestination(resultSet.getString("Destination"));
					tpMasterPojoList.add(obj);
				}
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: getRouteMasterDaoDetails Ending");
		return tpMasterPojoList;
	}

	public String insertRouteMasterDetails(TransportPojo tpPojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl: insertRouteMasterDetails Starting");
		String result_Status = null;

		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection connection = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		int count = 0;
		try {

			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);

			if ("NULL".equalsIgnoreCase(tpPojo.getCheck())) {
				pstmt1 = connection.prepareStatement(TransportUtilConstants.TRANSPORT_ROUTECHECK);
				pstmt1.setString(1, tpPojo.getRouteNo());
				pstmt1.setString(2, tpPojo.getLocid());
				/*System.out.println("TRANSPORT_ROUTECHECK  -->>"+pstmt1);*/
				rs = pstmt1.executeQuery();

				while (rs.next()) {
					count = rs.getInt(1);
				}
				if (count == 0) {
				
					pstmt = connection.prepareStatement(TransportUtilConstants.ROUTE_INSERT);
					pstmt.setString(1, tpPojo.getRouteCode());
					pstmt.setString(2, tpPojo.getRouteName());
					pstmt.setString(3, tpPojo.getRouteNo());
					pstmt.setString(4, tpPojo.getRouteLogicName());
					/*pstmt.setString(5, tpPojo.getDestination());*/
					pstmt.setString(5, tpPojo.getHalttime());
					pstmt.setString(6, tpPojo.getStartTime());
					pstmt.setString(7, tpPojo.getEndTime());
					pstmt.setString(8, tpPojo.getTotalStops());
					pstmt.setString(9, tpPojo.getTotalDistance());
					pstmt.setTimestamp(10, HelperClass.getCurrentTimestamp());
					pstmt.setString(11, tpPojo.getCreateUser());
					pstmt.setString(12, tpPojo.getLocid());
					
					/*System.out.println("ROUTE_INSERT  -->>"+pstmt);*/
					
					result1 = pstmt.executeUpdate();
					
					if (result1 > 0) {
						HelperClass.recordLog_Activity(tpPojo.getLog_audit_session(),"Transport","RouteMaster","Insert",pstmt.toString(),userLoggingsVo);
						
						for(int j=0;j< tpPojo.getStagesidArray().length;j++){
							
							PreparedStatement pstmt2=connection.prepareStatement(TransportUtilConstants.ROUTE_INSERT_STAGE);
							pstmt2.setString(1, tpPojo.getRouteCode());
							pstmt2.setString(2, tpPojo.getStagesidArray()[j]);
							pstmt2.setString(3, tpPojo.getCreateUser());
							pstmt2.setString(4, tpPojo.getLocid());
							
							/*System.out.println("ROUTE_INSERT_STAGE -->>"+pstmt2);*/
							
							int stagers=pstmt2.executeUpdate();	
							if (stagers > 0) {
								HelperClass.recordLog_Activity(tpPojo.getLog_audit_session(),"Transport","RouteMaster","Insert",pstmt.toString(),userLoggingsVo);
							}
							
							pstmt2.close();
						}	
						result_Status = MessageConstants.SUCCESS;
						
					} else {
						result_Status = MessageConstants.FAILD;
					}
					
				}
			

			} else {

				pstmt = connection.prepareStatement(TransportUtilConstants.ROUTE_UPDATEROUTEMASTER);
				pstmt.setString(1, tpPojo.getRouteName());
				pstmt.setString(2, tpPojo.getRouteNo());

				pstmt.setString(3, tpPojo.getRouteLogicName());
				pstmt.setString(4, tpPojo.getHalttime());
				pstmt.setString(5, tpPojo.getStartTime());
				pstmt.setString(6, tpPojo.getEndTime());
				pstmt.setString(7, tpPojo.getTotalStops());
				pstmt.setString(8, tpPojo.getTotalDistance());
				pstmt.setTimestamp(9, HelperClass.getCurrentTimestamp());
				pstmt.setString(10, tpPojo.getCreateUser());
				pstmt.setString(11, tpPojo.getRouteCode());
				
				/*System.out.println("ROUTE_UPDATEROUTEMASTER -->>"+pstmt);*/
				
				result1 = pstmt.executeUpdate();
				if (result1 > 0) {
					HelperClass.recordLog_Activity(tpPojo.getLog_audit_session(),"Transport","Route Master","Update",pstmt.toString(),userLoggingsVo);
					
					PreparedStatement pstmt3=connection.prepareStatement(TransportUtilConstants.DELETE_INSERT_STAGE);
					pstmt3.setString(1, tpPojo.getRouteCode());
					
					/*System.out.println("DELETE_INSERT_STAGE -->>"+pstmt3);*/
					
					int rtd=pstmt3.executeUpdate();
					if(rtd > 0){
						HelperClass.recordLog_Activity(tpPojo.getLog_audit_session(),"Transport","Route Master","Delete",pstmt3.toString(),userLoggingsVo);
						
					for(int j=0;j< tpPojo.getStagesidArray().length;j++){

						PreparedStatement pstmt2=connection.prepareStatement(TransportUtilConstants.ROUTE_INSERT_STAGE);
						pstmt2.setString(1, tpPojo.getRouteCode());
						pstmt2.setString(2, tpPojo.getStagesidArray()[j]);
						pstmt2.setString(3, tpPojo.getCreateUser());
						pstmt2.setString(4, tpPojo.getLocid());
						
						/*System.out.println("ROUTE_INSERT_STAGE -->>"+pstmt2);*/
						
						int stagers=pstmt2.executeUpdate();	
						if (stagers > 0) {
							HelperClass.recordLog_Activity(tpPojo.getLog_audit_session(),"Transport","Route Master","Insert",pstmt2.toString(),userLoggingsVo);
						}
						pstmt2.close();
					}
					}
					result_Status = MessageConstants.SUCCESS1;
					pstmt3.close();
				} else {
					result_Status = MessageConstants.FAILD;
				}

			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl: insertRouteMasterDetails Ending");

		return result_Status;
	}

	@Override
	public int getTpRouteCheckDao(String tpName) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RouteSetupDaoImpl: getRouteCheckDao Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = (PreparedStatement) JDBCConnection.getStatement(TransportUtilConstants.TRANSPORT_ROUTECHECK);
			pstmt.setString(1, tpName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: getFeeNameCheckDao Ending");
		return count;
	}

	public String removeRouteMasterDetails(TransportVo vo,UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDAOImpl: removeRouteMasterDetails : Starting");
		PreparedStatement pstmt = null;
		String status = null,value=null;
		int count=0;
		Connection conn = null;
		try {
			
			 conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			 pstmt = conn.prepareStatement(TransportUtilConstants.ROUTE_DETAILS_UPDATED_BY_STATUS);
			
			 for(int i=0;i<vo.getRouteId().length;i++){
				 if(vo.getStatus().equalsIgnoreCase("InActive")){
					 pstmt.setString(1, "N");
					 if(vo.getOtherreason()=="" || vo.getOtherreason()==null){
						 pstmt.setString(2, vo.getInactivereason()); 
					 }
					 else{
						 pstmt.setString(2, vo.getOtherreason());
					 }
					 value="InActive";
				 }
				 else{
					 pstmt.setString(1, "Y");
					 if(vo.getOtherreason()=="" || vo.getOtherreason()==null){
						 pstmt.setString(2, vo.getActivereason()); 
					 }
					 else{
						 pstmt.setString(2, vo.getOtherreason());
					 }
					 value="Active";
				 }
				 
				 pstmt.setString(3, vo.getRouteId()[i]);
				 pstmt.setString(4, vo.getLoc_id());
				 count= pstmt.executeUpdate();
				 if(count > 0){
					 HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Transport","RouteDetails",value,pstmt.toString(),userLoggingsVo);
					}
			}
			if(count > 0){
				status="true";
			}
			else{ 
				status="false";
			  }
		}
		catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		} finally {
			try {
				 
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDAOImpl: removeRouteMasterDetails : Ending");
		return status;
	}


	
	public boolean addRoute(TransportForm routeForm) {
		boolean status = false;

		PreparedStatement pstm = null;
		Connection connection = null;
		int result = 0;
		try {

			connection = JDBCConnection.getSeparateConnection();

			if (routeForm.getRouteCode() != null) {

				String noArray[] = routeForm.getStopNoArray().trim().split(",");
				String nameArray[] = routeForm.getStopNameArray().trim()
						.split(",");
				String arrivalArray[] = routeForm.getStopArrivalTimeArray()
						.trim().split(",");
				String haltArray[] = routeForm.getStopHaltTimeArray().trim()
						.split(",");
				String deptArray[] = routeForm.getStopDepartureTimeArray()
						.trim().split(",");
				String distArray[] = routeForm.getStopDistanceArray()
						.split(",");

				for (int i = 0; i < noArray.length; i++) {

					pstm = connection
							.prepareStatement(TransportUtilConstants.ADD_ROUTE_STOPDETAILS);
					pstm.setString(
							1,
							IDGenerator.getPrimaryKeyID(
									"transport_stopsdetails").trim());
					pstm.setString(2, routeForm.getRouteCode());

					pstm.setString(3, nameArray[i].trim());
					pstm.setString(4, arrivalArray[i].trim());
					pstm.setString(5, haltArray[i].trim());
					pstm.setString(6, deptArray[i].trim());
					pstm.setDouble(7,
							Double.parseDouble(distArray[i].toString()));
					result = pstm.executeUpdate();

				}

			}

		}

		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null && (!pstm.isClosed())) {
					pstm.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		if (result == 1) {
			status = true;
		} else {
			status = false;
		}

		return status;

	}

	public boolean checkrouteNo(TransportPojo Pojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : checkrouteNo Starting");
		int count = 0;
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {

			if ("NULL".equalsIgnoreCase((Pojo.getRouteCode()))) {
				connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = connection.prepareStatement(TransportUtilConstants.TRANSPORT_ROUTECHECK);
				pstmt.setString(1, Pojo.getRouteNo());
				pstmt.setString(2, Pojo.getLocid());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}
				if (count > 0) {
					status = true;
				}
			} else {
				connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = connection.prepareStatement(TransportUtilConstants.TRANSPORT_ROUTECHECK_WHILE_UPDATING);

				pstmt.setString(1, Pojo.getRouteNo());
				pstmt.setString(2, Pojo.getRouteCode());
				pstmt.setString(3, Pojo.getLocid());
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}
				if (count > 0) {
					status = true;
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : checkrouteNo  Ending");
		return status;
	}

	public TransportVo editRouteMasterDetails(String routecode,String loc,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : editRouteMasterDetails Starting");
		TransportVo obj = new TransportVo();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = connection.prepareStatement(TransportUtilConstants.ETID_ROUTE_ALLLISTDATAS);
			pstmt.setString(1, routecode);
			pstmt.setString(2, loc);
			System.out.println("ETID_ROUTE_ALLLISTDATAS -->>"+pstmt);
			
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					obj.setRouteName(resultSet.getString("RouteName"));
					obj.setRouteNo(resultSet.getString("Route_No"));
					obj.setRouteCode(resultSet.getString("RouteCode"));
					obj.setRouteLogicName(resultSet.getString("Route_logical_name"));
					/*obj.setDestination((resultSet.getString("Destination")));*/
					obj.setHalttime(resultSet.getString("HaltTime"));
					obj.setStratTime(resultSet.getString("Start_Time"));
					obj.setEndTime(resultSet.getString("End_Time"));
					obj.setTotalStops(resultSet.getString("Total_Stops"));
					obj.setTotalDistance(resultSet.getString("TotalDistance"));
				}
			}
		} 
		
		catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : editRouteMasterDetails  Ending");

		return obj;
	}

	public List<TransportVo> searchDetails(String SearchName,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchDetails Starting");
		ArrayList<TransportVo> list = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		
		ResultSet resultSet = null;
		Connection connection = null;
		int sno = 0;
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = connection
					.prepareStatement(TransportUtilConstants.ROUTE_SEARCHLISTDATAS);
		
			
			pstmt.setString(1,  SearchName + "%");
			pstmt.setString(2, SearchName + "%");
			pstmt.setString(3,  SearchName + "%");
			pstmt.setString(4, SearchName + "%");
			
			
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					sno++;
					
					TransportVo obj = new TransportVo();
					obj = new TransportVo();
					obj.setRouteNo(resultSet.getString("Route_No"));
					obj.setRouteName(resultSet.getString("RouteName"));
					obj.setRouteCode(resultSet.getString("RouteCode"));
					obj.setRouteLogicName(resultSet
							.getString("Route_logical_name"));
					obj.setRouteType(resultSet.getString("RouteType"));
					obj.setCostPerPerson(resultSet.getString("Cost_per_person"));
					obj.setStratTime(resultSet.getString("Start_Time"));
					obj.setEndTime(resultSet.getString("End_Time"));
					obj.setTotalStops(resultSet.getString("Total_Stops"));
					obj.setTotalDistance(resultSet.getString("TotalDistance"));
					obj.setHalttime(resultSet.getString("HaltTime"));
					list.add(obj);
				}
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchDetails  Ending");

		return list;

	}

	public ArrayList<DriverMsaterListVo> getdriverListDao(UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getdriverListDao Starting");

		System.out.println("driver dao");

		ArrayList<DriverMsaterListVo> driverlist = new ArrayList<DriverMsaterListVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_DRIVER_LIST);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DriverMsaterListVo listvo = new DriverMsaterListVo();
				String IssuedDate = "";
				String validity = "";
				String dlno = "";

				if (rs.getString("DLIssuedDate") != null && !rs.getString("DLIssuedDate").isEmpty()) {
					IssuedDate = HelperClass.convertDatabaseToUI(rs.getString("DLIssuedDate"));

				}

				if (rs.getString("DLExpirayDate") != null && !rs.getString("DLExpirayDate").isEmpty()) {
					validity = HelperClass.convertDatabaseToUI(rs.getString("DLExpirayDate"));
				}

				if (rs.getString("DLNo") != null&& !rs.getString("DLNo").isEmpty()) {
					dlno = rs.getString("DLNo");

				}

				listvo.setType(rs.getString("type"));
				listvo.setDriverName(rs.getString("Name"));
				listvo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("DOB")));
				listvo.setDateofJoin(HelperClass.convertDatabaseToUI(rs.getString("DOJ")));
				listvo.setMobile(rs.getString("MobileNo"));
				listvo.setDrivingliecenseNo(dlno);
				listvo.setDriverCode(rs.getString("DriverCode"));
				listvo.setFather_name(rs.getString("FatherName"));
				listvo.setEmerg_contact(rs.getString("EmergencyContactNo"));
				listvo.setAddress(rs.getString("Address"));
				listvo.setAge(rs.getString("Age"));
				listvo.setGender(rs.getString("Gender"));
				listvo.setDl_issued_date(IssuedDate);
				listvo.setDl_validity(validity);
				listvo.setLicense(rs.getString("LicencetoDrive"));
				listvo.setExperience(rs.getString("Experience"));
				driverlist.add(listvo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
          
		finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getdriverListDao  Ending");
		return driverlist;
	}

/*	public int addDriverDao(DriverMsaterListVo drivervo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : addDriverDao Starting");

		PreparedStatement pstmt = null;
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		Connection connection = null;
		int result = 0;
		int rs = 0;
		try {

			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.ADD_DRIVER);

			pstmt.setString(1, IDGenerator.getPrimaryKeyID("transport_driver"));
			pstmt.setString(2, "driver");
			pstmt.setString(3, drivervo.getDriverName());
			pstmt.setString(4, drivervo.getFather_name());
			pstmt.setString(5,
					HelperClass.convertUIToDatabase(drivervo.getDateofBirth()));
			pstmt.setString(6, drivervo.getMobile());
			pstmt.setString(7, drivervo.getEmerg_contact());
			pstmt.setString(8, drivervo.getAddress());
			pstmt.setString(9,
					HelperClass.convertUIToDatabase(drivervo.getDateofJoin()));
			pstmt.setString(10, drivervo.getAge());
			pstmt.setString(11, drivervo.getGender());
			pstmt.setString(12, drivervo.getDrivingliecenseNo());
			pstmt.setString(13, HelperClass.convertUIToDatabase(drivervo
					.getDl_issued_date()));
			pstmt.setString(14,
					HelperClass.convertUIToDatabase(drivervo.getDl_validity()));
			pstmt.setString(15, drivervo.getLicense());
			pstmt.setTimestamp(16, time_stamp);
			pstmt.setString(17, drivervo.getCreateUser());
			pstmt.setString(18, drivervo.getExperience());

			rs = pstmt.executeUpdate();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : addDriverDao  Ending");
		return result;
	}
*/
	public DriverMsaterListVo editDriverDao(DriverMsaterListVo drivervo, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : editDriverDao Starting");

		DriverMsaterListVo driverobj = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			driverobj = new DriverMsaterListVo();
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_DRIVER_DETAILS);
			pstmt.setString(1, drivervo.getDriverCode());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				driverobj.setDriverCode(rs.getString("DriverCode"));
				driverobj.setDriverName(rs.getString("Name"));
				driverobj.setFather_name(rs.getString("FatherName"));
				driverobj.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("DOB")));
				driverobj.setMobile(rs.getString("MobileNo"));
				driverobj.setEmerg_contact(rs.getString("EmergencyContactNo"));
				driverobj.setExperience(rs.getString("Experience"));
				driverobj.setAddress(rs.getString("Address"));
				driverobj.setDateofJoin(HelperClass.convertDatabaseToUI(rs.getString("DOJ")));
				driverobj.setAge(rs.getString("Age"));
				driverobj.setGender(rs.getString("Gender"));
				driverobj.setDrivingliecenseNo(rs.getString("DLNo"));
				driverobj.setDl_validity(HelperClass.convertDatabaseToUI(rs.getString("DLExpirayDate")));
				driverobj.setLicense(rs.getString("LicencetoDrive"));
				driverobj.setLicensedrive(rs.getString("DrivingLicenceFile"));
				driverobj.setLocId(rs.getString("locId"));
				
			    driverobj.setStatus("update");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally {
			 try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : editDriverDao  Ending");
		return driverobj;
	}

	public String deleteDriverDao(DriverMsaterListVo vo,UserLoggingsPojo custdetails)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl: deleteDriverDao : Starting");

		PreparedStatement pstmt = null;
		String status = null,value=null;
		int count=0;
		Connection conn = null;
		try {
			
			 conn = JDBCConnection.getSeparateConnection(custdetails);
			 pstmt = conn.prepareStatement(TransportUtilConstants.UPDATE_DRIVER_MASTER_STATUS);
			
			 for(int i=0;i<vo.getDriverCodes().length;i++){
				 if(vo.getStatus().equalsIgnoreCase("InActive")){
					 pstmt.setString(1, "N");
					 if(vo.getOtherreason()=="" || vo.getOtherreason()==null){
						 pstmt.setString(2, vo.getInactivereason()); 
					 }
					 else{
						 pstmt.setString(2, vo.getOtherreason());
					 }
					 value="InActive";
				 }
				 else{
					 pstmt.setString(1, "Y");
					 if(vo.getOtherreason()=="" || vo.getOtherreason()==null){
						 pstmt.setString(2, vo.getActivereason()); 
					 }
					 else{
						 pstmt.setString(2, vo.getOtherreason());
					 }
					 value="Active";
				 }
				 pstmt.setString(3, vo.getDriverCodes()[i]);
				 count= pstmt.executeUpdate();
				 if(count > 0){
					 HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Transport","DriversMaster",value,pstmt.toString(),custdetails);
					}
			}
			if(count > 0){
				status="true";
			}
			else{ 
				status="false";
			  }
		}
		catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		} finally {
			try {
				 
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl: deleteDriverDao : Ending");
		return status;
	}

	public ArrayList<DriverMsaterListVo> searchDriverDao(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchDriverDao Starting");

		ArrayList<DriverMsaterListVo> driverlist = new ArrayList<DriverMsaterListVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TransportUtilConstants.SEARCH_DRIVER_DETAILS);

			pstmt.setString(1,  searchName + "%");
			pstmt.setString(2,  searchName + "%");
			pstmt.setString(3,  searchName + "%");
			pstmt.setString(4,  searchName + "%");
			pstmt.setString(5,  searchName + "%");
			pstmt.setString(6,  searchName + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;
				DriverMsaterListVo driverobj = new DriverMsaterListVo();

				driverobj.setSno(String.valueOf(sno));
				 driverobj.setDriverCode(rs.getString("DriverCode"));
				driverobj.setDriverName(rs.getString("Name"));
				driverobj.setDateofJoin(rs.getString("DOJ"));
				driverobj.setMobile(rs.getString("MobileNo"));
				driverobj.setDrivingliecenseNo(rs.getString("DLNo"));
				driverobj.setDl_issued_date(rs.getString("DLIssuedDate"));
				driverobj.setDl_validity(rs.getString("DLExpirayDate"));

				driverlist.add(driverobj);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchDriverDao  Ending");
		return driverlist;
	}

/*	public int updateDriverDao(DriverMsaterListVo drivervo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateDriverDao Starting");

		PreparedStatement pstmt = null;
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		int result = 0;
		Connection connection = null;

		try {

			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.UPDATE_DRIVER);

			 pstmt.setString(1, drivervo.getType()); 
			pstmt.setString(1, drivervo.getDriverName());
			pstmt.setString(2, drivervo.getFather_name());
			pstmt.setString(3,
					HelperClass.convertUIToDatabase(drivervo.getDateofBirth()));
			pstmt.setString(4, drivervo.getMobile());
			pstmt.setString(5, drivervo.getEmerg_contact());
			pstmt.setString(6, drivervo.getAddress());
			pstmt.setString(7,
					HelperClass.convertUIToDatabase(drivervo.getDateofJoin()));
			pstmt.setString(8, drivervo.getAge());
			pstmt.setString(9, drivervo.getGender());
			pstmt.setString(10, drivervo.getDrivingliecenseNo());
			pstmt.setString(11, HelperClass.convertUIToDatabase(drivervo
					.getDl_issued_date()));
			pstmt.setString(12,
					HelperClass.convertUIToDatabase(drivervo.getDl_validity()));
			pstmt.setString(13, drivervo.getLicense());
			pstmt.setTimestamp(14, time_stamp);
			pstmt.setString(15, drivervo.getCreateUser());
			pstmt.setString(16, drivervo.getExperience());
			pstmt.setString(17, drivervo.getDriverCode());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateDriverDao  Ending");
		return result;
	}
*/
	public boolean validateDriverDao(DriverMsaterListVo drivervo, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : validateDriverDao Starting");

		boolean driver_validate = false;

		int count = 0;
		PreparedStatement pscheckExamName = null;
		ResultSet rsCheckExamName = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);

			pscheckExamName = conn.prepareStatement(TransportUtilConstants.VALIDATE_DRIVER);
			pscheckExamName.setString(1, drivervo.getDriverName());
			pscheckExamName.setString(2, drivervo.getDateofBirth());
			pscheckExamName.setString(3, drivervo.getMobile());
			pscheckExamName.setString(4, drivervo.getAddress());
			pscheckExamName.setString(5, drivervo.getDateofJoin());

			rsCheckExamName = pscheckExamName.executeQuery();

			while (rsCheckExamName.next()) {
				count = rsCheckExamName.getInt(1);
			}
			if (count > 0) {
				driver_validate = true;
			} else {
				driver_validate = false;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (rsCheckExamName != null && (!rsCheckExamName.isClosed())) {
					rsCheckExamName.close();
				}
				if (pscheckExamName != null && (!pscheckExamName.isClosed())) {
					pscheckExamName.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : validateDriverDao  Ending");

		return driver_validate;
	}

	// for driver license validation//
	
	public boolean validateLicenseDao(DriverMsaterListVo drivervo, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : validateDriverDao Starting");

		boolean license_validate = false;

		int count = 0;
		PreparedStatement pscheckExamName = null;
		ResultSet rsCheckExamName = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);

			pscheckExamName = conn.prepareStatement(TransportUtilConstants.VALIDATE_LICENSE);
			pscheckExamName.setString(1, drivervo.getDrivingliecenseNo());
			pscheckExamName.setString(2, drivervo.getLocId());
			
			rsCheckExamName = pscheckExamName.executeQuery();
			while (rsCheckExamName.next()) {
				count = rsCheckExamName.getInt(1);
			}
			if (count > 0) {
				license_validate = true;
			} else {
				license_validate = false;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (rsCheckExamName != null && (!rsCheckExamName.isClosed())) {
					rsCheckExamName.close();
				}
				if (pscheckExamName != null && (!pscheckExamName.isClosed())) {
					pscheckExamName.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : validateDriverDao  Ending");

		return license_validate;
	}
	
	
	public ArrayList<DriverMsaterListVo> getDriverNamesDetails(VehicleDetailsVO vo,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getDriverNamesDetails Starting");
		ArrayList<DriverMsaterListVo> driverlist = new ArrayList<DriverMsaterListVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_UNMAPPED_DRIVER_DETAILS1);
			pstmt.setString(1, vo.getLocid());
			System.out.println("GET_UNMAPPED_DRIVER_DETAILS1 -->>"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DriverMsaterListVo listVo = new DriverMsaterListVo();

				listVo.setDriverCode(rs.getString("DriverCode"));
				listVo.setDriverName(rs.getString("Name"));
				driverlist.add(listVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getDriverNamesDetails  Ending");
		return driverlist;

	}

	public ArrayList<DriverMsaterListVo> getDriverEntireDetails(String driverid,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getDriverEntireDetails Starting");
		ArrayList<DriverMsaterListVo> driverlist = new ArrayList<DriverMsaterListVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_DRIVER_DETAILS_BY_ID);
			pstmt.setString(1, driverid);
			
			System.out.println("details of driver"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DriverMsaterListVo listVo = new DriverMsaterListVo();

				listVo.setDriverCode(rs.getString("DriverCode"));
				listVo.setDriverName(rs.getString("Name"));
				listVo.setMobile(rs.getString("MobileNo"));
				listVo.setExperience(rs.getString("Experience"));
				listVo.setDateofJoin(HelperClass.convertDatabaseToUI(rs.getString("DOJ")));
				listVo.setDrivingliecenseNo(rs.getString("DLNo"));
				/*listVo.setDl_issued_date(HelperClass.convertDatabaseToUI(rs.getString("DLIssuedDate")));*/
				listVo.setDl_validity(HelperClass.convertDatabaseToUI(rs.getString("DLExpirayDate")));
				listVo.setLicense(rs.getString("LicencetoDrive"));
				driverlist.add(listVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getDriverEntireDetails  Ending");

		return driverlist;

	}

	public ArrayList<DriverMsaterListVo> getDriverDetailsWhileUpdate(
			String vehiclecode) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getDriverDetailsWhileUpdate Starting");
		ArrayList<DriverMsaterListVo> driverlist = new ArrayList<DriverMsaterListVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(TransportUtilConstants.GET_UNMAPPED_DRIVER_DETAILS_WHILE_UPDATE);
			pstmt.setString(1, vehiclecode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DriverMsaterListVo listVo = new DriverMsaterListVo();

				listVo.setDriverCode(rs.getString("DriverCode"));
				listVo.setDriverName(rs.getString("Name"));
				driverlist.add(listVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getDriverDetailsWhileUpdate  Ending");
		return driverlist;

	}

	public DriverMsaterListVo getSingleDriverDetails(String vehiclecode,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getSingleDriverDetails Starting");
		DriverMsaterListVo driverlist = new DriverMsaterListVo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_SINGLE_DRIVER_DETAILS);
			pstmt.setString(1, vehiclecode);
			pstmt.setString(2, vehiclecode);
			rs = pstmt.executeQuery();
			System.out.println("istiyak driver code"+pstmt);
			while (rs.next()) {
				driverlist.setLocId(rs.getString("locId"));
				driverlist.setDriverCode(rs.getString("DriverCode"));
				driverlist.setDriverName(rs.getString("Name"));
				driverlist.setMobile(rs.getString("MobileNo"));
				driverlist.setExperience(rs.getString("Experience"));
				driverlist.setDateofJoin(HelperClass.convertDatabaseToUI(rs.getString("DOJ")));
				driverlist.setDrivingliecenseNo(rs.getString("DLNo"));

				//driverlist.setDl_issued_date(HelperClass.convertDatabaseToUI(rs.getString("DLIssuedDate")));
				driverlist.setDl_validity(HelperClass.convertDatabaseToUI(rs.getString("DLExpirayDate")));
				driverlist.setLicense(rs.getString("LicencetoDrive"));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getSingleDriverDetails  Ending");

		return driverlist;

	}

	public ArrayList<StageMasterVo> getStopNames(String searchTerm, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getStopNames Starting");
		ArrayList<StageMasterVo> stoplist = new ArrayList<StageMasterVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn
					.prepareStatement(TransportUtilConstants.GET_STOP_NAMES);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				StageMasterVo stopvo = new StageMasterVo();
				stopvo.setStageCode(rs.getString("stage_id"));
				stopvo.setStageName(rs.getString("stage_name"));
				stoplist.add(stopvo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getStopNames  Ending");

		return stoplist;

	}

	public ArrayList<TransportVo> getRouteDetails() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteDetails Starting");
		ArrayList<TransportVo> transportlist = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_ROUTE_NAMES);

			System.out.println("GET_ROUTE_NAMES -->>"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TransportVo vo = new TransportVo();
				vo.setRouteCode(rs.getString("RouteCode"));
				vo.setRouteName(rs.getString("RouteName"));
				transportlist.add(vo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteDetails  Ending");

		return transportlist;
	}

	public ArrayList<TransportVo> GetRouteEntireDetails(String route,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : GetRouteEntireDetails Starting");
		ArrayList<TransportVo> transportlist = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_ROUTE_DETAILS);
			pstmt.setString(1, route);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TransportVo vo = new TransportVo();
				vo.setRouteCode(rs.getString("RouteCode"));
				vo.setRouteName(rs.getString("RouteName"));
				vo.setTotalDistance(rs.getString("TotalDistance"));
				vo.setRouteCode(rs.getString("Route_No"));
				vo.setTotalStops(rs.getString("Total_Stops"));
				vo.setDestination(rs.getString("Destination"));
				vo.setHalttime(rs.getString("HaltTime"));
				transportlist.add(vo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.isClosed();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : GetRouteEntireDetails  Ending");

		return transportlist;

	}

	public TransportVo getRouteDetails(String vehiclecode,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRouteDetails Starting");
		TransportVo vo = new TransportVo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn
					.prepareStatement(TransportUtilConstants.GET_ROUTE_DETAILS_EDIT);
			pstmt.setString(1, vehiclecode);

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo.setRouteCode(rs.getString("RouteCode"));
				vo.setRouteName(rs.getString("RouteName"));
				vo.setTotalDistance(rs.getString("TotalDistance"));
				vo.setRouteNo(rs.getString("Route_No"));
				vo.setTotalStops(rs.getString("Total_Stops"));
				//vo.setDestination(rs.getString("Destination"));
				vo.setHalttime(rs.getString("HaltTime"));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : getRouteDetails  Ending");

		return vo;

	}

	@Override
	public List<TransportPojo> getTransportMasterDaoDetailsXLS() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportSetupDaoImpl: getTransportMasterDaoDetails Starting");
		ArrayList<TransportPojo> tpMasterPojoList = new ArrayList<TransportPojo>();
		PreparedStatement pstmt = null;
		TransportPojo obj = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			System.out.println("DownloadDAOIMPLis WORKING");
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TransportUtilConstants.ROUTE_ALLLISTDATASTOP);
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {

					obj = new TransportPojo();
					obj.setRouteName(resultSet.getString("RouteName"));
					obj.setRouteNo(resultSet.getString("Route_No"));
					obj.setRouteCode(resultSet.getString("RouteCode"));
					obj.setStartTime(resultSet.getString("Start_Time"));
					obj.setEndTime(resultSet.getString("End_Time"));
					obj.setTotalStops(resultSet.getString("Total_Stops"));
					obj.setTotalDistance(resultSet.getString("TotalDistance"));
					obj.setHalttime(resultSet.getString("HaltTime"));
					obj.setDestination(resultSet.getString("Destination"));
					obj.setStopname(resultSet.getString("Stop_Name"));
					obj.setArrivaltime(resultSet.getString("ArrivalTime"));
					obj.setDeparturetime(resultSet.getString("DepTime"));
					obj.setDistance(resultSet.getString("Distance"));
					
					tpMasterPojoList.add(obj);
				}
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: getRouteMasterDaoDetails Ending");
		return tpMasterPojoList;
	}

	@Override
	
    public boolean addDriverDao(DriverMsaterListVo drivervo, String createuser, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : addDriverDao Starting");

		PreparedStatement pstmt = null;
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		Connection connection = null;
		boolean result = false;
		int rs = 0;
		try {

			connection = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = connection.prepareStatement(TransportUtilConstants.ADD_DRIVER);

			pstmt.setString(1, drivervo.getDriverCode());
			pstmt.setString(2, "driver");
			pstmt.setString(3, drivervo.getDriverName());
			pstmt.setString(4, drivervo.getFather_name());
			pstmt.setString(5, HelperClass.convertUIToDatabase(drivervo.getDateofBirth()));
			pstmt.setString(6, drivervo.getMobile());
			pstmt.setString(7, drivervo.getEmerg_contact());
			pstmt.setString(8, drivervo.getAddress());
			pstmt.setString(9, HelperClass.convertUIToDatabase(drivervo.getDateofJoin()));
			pstmt.setString(10, drivervo.getAge());
			pstmt.setString(11, drivervo.getGender());
			pstmt.setString(12, drivervo.getDrivingliecenseNo());
			pstmt.setString(13, HelperClass.convertUIToDatabase(drivervo.getDl_validity()));
			pstmt.setString(14, drivervo.getDriving_license_types());
			/*pstmt.setTimestamp(15, time_stamp);*/
			pstmt.setString(15, createuser);
			pstmt.setString(16, drivervo.getExperience());
			pstmt.setString(17, drivervo.getUploadinglicense());
			pstmt.setString(18, drivervo.getLocId());
			 System.out.println("ADD_DRIVER -->>"+pstmt);
			rs = pstmt.executeUpdate();
			
			if (rs > 0) {
			    HelperClass.recordLog_Activity(drivervo.getLog_audit_session(),"Transport","DriverMaster","Insert",pstmt.toString(),custdetails);
				result = true;
				drivervo.setStatus("success1");

			} else {

				result = false;
				drivervo.setStatus("success2");

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : addDriverDao  Ending");
		return result;
	}

	@Override
	public boolean updateDriverDao(DriverMsaterListVo drivervo, String createuser, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateDriverDao Starting");
		PreparedStatement pstmt = null;
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		boolean result = false;
		Connection connection = null;
		int rs = 0;

		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = connection.prepareStatement(TransportUtilConstants.UPDATE_DRIVER);
			
			/* pstmt.setString(1, drivervo.getType()); */
			pstmt.setString(1, drivervo.getDriverName());
			pstmt.setString(2, drivervo.getFather_name());
			pstmt.setString(3,HelperClass.convertUIToDatabase(drivervo.getDateofBirth()));
			pstmt.setString(4, drivervo.getMobile());
			pstmt.setString(5, drivervo.getEmerg_contact());
			pstmt.setString(6, drivervo.getAddress());
			pstmt.setString(7,HelperClass.convertUIToDatabase(drivervo.getDateofJoin()));
			pstmt.setString(8, drivervo.getAge());
			pstmt.setString(9, drivervo.getGender());
			pstmt.setString(10, drivervo.getDrivingliecenseNo());
			pstmt.setString(11,HelperClass.convertUIToDatabase(drivervo.getDl_validity()));
			
			pstmt.setString(12,drivervo.getDriving_license_types());
			pstmt.setTimestamp(13, time_stamp);
			pstmt.setString(14, drivervo.getExperience());
			pstmt.setString(15, drivervo.getUploadinglicense());
			pstmt.setString(16, drivervo.getLocId());
			pstmt.setString(17, createuser);
			pstmt.setString(18, drivervo.getDriverCode());
             System.out.println("UPDATE_DRIVER -->>"+pstmt);
			rs = pstmt.executeUpdate();
			
			if (rs > 0) {
				 HelperClass.recordLog_Activity(drivervo.getLog_audit_session(),"Transport","Driver Master","Update",pstmt.toString(),custdetails);
				result = true;
				drivervo.setStatus("success3");
			} else {
				result = false;
				drivervo.setStatus("success4");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			 try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : updateDriverDao  Ending");
		return result;
	}

	@Override
	public List<TransportVo> editRouteStages(TransportPojo tpMasterPojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : editRouteStages Starting");
		
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		int count=0;
		ArrayList<TransportVo> list = new ArrayList<TransportVo>();
		
		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = connection.prepareStatement(TransportUtilConstants.ETID_ROUTE_STAGES);
			pstmt.setString(1, tpMasterPojo.getRouteCode());
			pstmt.setString(2, tpMasterPojo.getLocid());
			resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					count++;
					TransportVo vo = new TransportVo();
					vo.setStage_name(resultSet.getString("stage_name"));
					vo.setStage_id(resultSet.getString("StageCode"));
					
					vo.setCount(count);
					list.add(vo);
				}
		} 
		
		catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : editRouteStages  Ending");

		return list;
	}

	
	public List<TransportVo> unmappedRouteStages(TransportPojo tpMasterPojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : unmappedRouteStages Starting");
		
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		int count=0;
		ArrayList<TransportVo> list = new ArrayList<TransportVo>();
		
		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = connection.prepareStatement(TransportUtilConstants.UNMMAPEDSTAGES);

			pstmt.setString(1, tpMasterPojo.getRouteCode());
			pstmt.setString(2, tpMasterPojo.getLocid());
			
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					count++;
					TransportVo vo = new TransportVo();
					vo.setStage_name(resultSet.getString("stage_name"));
					vo.setStageName(resultSet.getString("stage_name"));
					vo.setStage_id(resultSet.getString("stage_id"));
					vo.setStageCode(resultSet.getString("stage_id"));
					vo.setCount(count);
			
					list.add(vo);
				}
		} 
		
		catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : unmappedRouteStages  Ending");

		return list;
	}

	@Override
	public ArrayList<TransportVo> getRouteDetailsByLocation(String transferlocation,UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteDetails Starting");
		ArrayList<TransportVo> transportlist = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_ROUTE_NAMES_BY_LOCATION);
			pstmt.setString(1, transferlocation.trim());

			System.out.println("GET_ROUTE_NAMES_BY_LOCATION -->>"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TransportVo vo = new TransportVo();
				vo.setRouteCode(rs.getString("RouteCode"));
				vo.setRouteName(rs.getString("RouteName"));
				transportlist.add(vo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteDetails  Ending");

		return transportlist;
	}

	public ArrayList<TransportVo> getRouteDetailsByName(VehicleDetailsVO vo1,UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteDetails Starting");
		ArrayList<TransportVo> transportlist = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_ROUTE_DETAILS_BY_VECHILE);
			pstmt.setString(1, vo1.getLocid());
			System.out.println("GET_ROUTE_DETAILS_BY_VECHILE -->>"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TransportVo vo = new TransportVo();
				vo.setRouteCode(rs.getString("RouteCode"));
				vo.setRouteName(rs.getString("RouteName"));
				transportlist.add(vo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteDetails  Ending");

		return transportlist;
	}

	@Override
	public ArrayList<TransportVo> getStudentBusCardDetails(TransportVo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getRouteDetails Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<TransportVo> studentData = new ArrayList<TransportVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(TransportUtilConstants.GETSTUDENTBUSCARDDETAILS);
			pstmt.setString(1, obj.getAcy_id());
			pstmt.setString(2, obj.getTermId());
			pstmt.setString(3, obj.getStudentId());
			pstmt.setString(4, obj.getAcy_id());
			pstmt.setString(5, obj.getLoc_id());
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				TransportVo vo = new TransportVo();
				vo.setStudent_name(rs.getString("student"));
				vo.setClassname(rs.getString("class"));
				vo.setReceiptNo(rs.getString("reciept_no"));
				vo.setStopname(rs.getString("stage_name"));
				vo.setRouteName(rs.getString("RouteName"));
				vo.setMobileNo(rs.getString("mobileno"));
				studentData.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return studentData;
	}

	public String insertVehicleType(TransportCategoryForm form,String createCode, UserLoggingsPojo custdetails) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : insertVehicleType Starting");
		int count = 0;
		String status = "";
		PreparedStatement pstmt = null;
		Connection connection = null;

		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		try
		{
			connection = JDBCConnection.getSeparateConnection(custdetails);
			
			if(form.getUpdateid()== null || form.getUpdateid().equalsIgnoreCase("")){
				pstmt = connection.prepareStatement(TransportUtilConstants.INSERT_VECHICLE_TYPE);
				
				pstmt.setString(1, form.getVehicle_id());
				pstmt.setString(2, form.getVehicleType().trim());
				pstmt.setString(3, form.getDescription().trim());
				pstmt.setString(4, createCode);
				/*System.out.println("INSERT_VECHICLE_TYPE -->>"+pstmt);*/
				count = pstmt.executeUpdate();
				if(count>0)
				{
					HelperClass.recordLog_Activity(form.getLog_audit_session(),"Transport","TransportCategory","Insert",pstmt.toString(),custdetails);
					status = "Success";
				}
				else
				{
					status = "Failure";
				}
			}else
			{
				pstmt = connection.prepareStatement(TransportUtilConstants.UPDATE_VECHICLE_TYPE);
				pstmt.setString(1, form.getVehicleType());
				pstmt.setString(2, form.getDescription());
				pstmt.setString(3, createCode);
				pstmt.setTimestamp(4, time_stamp);
				pstmt.setString(5, form.getUpdateid());//Here the values are coming from action class.
				/*System.out.println("UPDATE_VECHICLE_TYPE -->>"+pstmt);*/
				count = pstmt.executeUpdate();
				if(count>0)
				{
					HelperClass.recordLog_Activity(form.getLog_audit_session(),"Transport","TransportCategory","Update",pstmt.toString(),custdetails);
					status = "UpdateSuccess";
				}
				else
				{
					status = "UpdateFailure";
				}
			}
			
		}
		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public List<VehicleTypeVo> getAllvehicletypeDetails(UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getAllvehicletypeDetails Starting");
		List<VehicleTypeVo> getvehiclelist = new ArrayList<VehicleTypeVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = connection.prepareStatement(TransportUtilConstants.GET_ALL_VEHICLE_TYPE_DETAILS);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				VehicleTypeVo tform = new VehicleTypeVo();
				tform.setVehicle_id(rs.getString("type_id"));
				tform.setVehicleType(rs.getString("type_name"));
				tform.setDesc(rs.getString("type_description"));
				getvehiclelist.add(tform);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getAllvehicletypeDetails  Ending");
		return getvehiclelist;
	}
	
	public VehicleTypeVo edittransporttype(String transportCatergory,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : edittransporttype Starting");
		VehicleTypeVo tform = new VehicleTypeVo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn
					.prepareStatement(TransportUtilConstants.EDIT_TRANSPORT_CATEGORY_DETAILS);
			pstmt.setString(1, transportCatergory);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tform.setVehicle_id(rs.getString("type_id"));
				tform.setVehicleType(rs.getString("type_name"));
				tform.setDesc(rs.getString("type_description"));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportServiceImpl : edittransporttype  Ending");

		return tform;

	}
	
	public String deleteVehicleType(TransportVo vo,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : deleteVehicleType Starting");
		int count = 0;
		
		String status = null,value=null;
		PreparedStatement pstmt = null;
		Connection connection = null;
	
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			 pstmt = connection.prepareStatement(TransportUtilConstants.UPDATE_VEHICLE_TYPE_DETAILS_BY_STATUS);
			
			for(int i=0;i<vo.getVehicleIds().length;i++){
				 if(vo.getStatus().equalsIgnoreCase("InActive")){
					 pstmt.setString(1, "N");
					 if(vo.getOtherreason()=="" || vo.getOtherreason()==null){
						 pstmt.setString(2, vo.getInactivereason()); 
					 }
					 else{
						 pstmt.setString(2, vo.getOtherreason());
					 }
					 value="InActive";
				 }
				 else{
					 pstmt.setString(1, "Y");
					 if(vo.getOtherreason()=="" || vo.getOtherreason()==null){
						 pstmt.setString(2, vo.getActivereason()); 
					 }
					 else{
						 pstmt.setString(2, vo.getOtherreason());
					 }
					 value="Active";
				 }
				 
				 pstmt.setString(3, vo.getVehicleIds()[i]);
				 count= pstmt.executeUpdate();
				 if(count > 0){
					 HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Transport","TransportCategory",value,pstmt.toString(),custdetails);
					}
			}
			
			if(count > 0){
				status="true";
			}
			else{ 
				status="false";
			  }
			

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : deleteVehicleType  Ending");
		return status;
	}

	public String validateVehicleType(VehicleTypeVo vehicleadd, UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : validateVehicleType Starting");
		int count = 0;
		String status = null,value=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = connection.prepareStatement(TransportUtilConstants.VALIDATE_VEHICLE_TYPE);
			pstmt.setString(1, vehicleadd.getVehicleType().trim());
			pstmt.setString(2, vehicleadd.getVehicle_id());
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
				value= rs.getString(2);
			}
			if (count > 0 && value.equalsIgnoreCase("Y")) 
			{
				status = "true";
			}
			else if (count > 0 && value.equalsIgnoreCase("N")) 
			{
				status = "inactive";
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : validateVehicleType  Ending");
		return status;
	}

	
	@SuppressWarnings("finally")
	public List<VehicleTypeVo> searchVehicletypeDetails(String searchName,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchVehicletypeDetails Starting");

		ArrayList<VehicleTypeVo> getvehiclelist = new ArrayList<VehicleTypeVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(TransportUtilConstants.SEARCH_VEHICLE_TYPE);

			pstmt.setString(1, searchName+"%");
			pstmt.setString(2, searchName+"%");
			pstmt.setString(3, searchName+"%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				VehicleTypeVo detailsearch = new VehicleTypeVo();
				
				detailsearch.setVehicle_id(rs.getString("type_id"));
				detailsearch.setVehicleType(rs.getString("type_name"));
				detailsearch.setDesc(rs.getString("type_description"));
				getvehiclelist.add(detailsearch);
			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} 
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl: searchVehicletypeDetails Ending");

		return getvehiclelist;
	}
}

	public TransportVo gettransportdetailsstudentwise(TransportVo tvo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : gettransportdetailsstudentwise Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		TransportVo tvo1 = null;
		String months[] = {"January", "February", "March", "April",
                "May", "June", "July", "August", "September",
                "October", "November", "December"}; 
//select cs.student_admissionno_var,concat(cs.student_fname_var,' ',cs.student_lname_var) as student,cp.parentid,cc.classdetail_id_int,cc.classdetails_name_var  from campus_student cs join campus_parentchildrelation cp  on cs.student_id_int= cp.stu_addmissionNo and cs.student_id_int=? and cp.stu_addmissionNo=? and cs.fms_acadamicyear_id_int=? and cs.locationId=? join campus_classdetail cc  where cc.classdetail_id_int=? and cc.locationId=? ";
			try {
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(TransportUtilConstants.GET_STU_DETAILS);
				pstmt.setString(1,tvo.getStudentId());
				pstmt.setString(2,tvo.getLoc_id());
				pstmt.setString(3,tvo.getClassId());
				pstmt.setString(4,tvo.getSectionId());
				pstmt.setString(5,tvo.getAcy_id());
				pstmt.setString(6,tvo.getStatus());
		 		System.out.println("GET_STU_DETAILS -->>"+pstmt);
				rs = pstmt.executeQuery();
				while(rs.next()){
					tvo1=new TransportVo();
					tvo1.setStudent_name(rs.getString("student"));
					tvo1.setAdmisssion_no(rs.getString("student_admissionno_var"));
					tvo1.setClassname(rs.getString("classdetails_name_var"));
					tvo1.setSection_name(rs.getString("classsection_name_var"));
					tvo1.setStudent_status(rs.getString("student_status"));
					tvo1.setAddress(rs.getString("address").trim());
					tvo1.setStmonth(rs.getString("start_month"));
					tvo1.setEndmonth(rs.getString("end_month"));
					tvo1.setStatus(rs.getString("isTransport"));
					tvo1.setStudentImage(rs.getString("student_imgurl_var"));
					if(rs.getString("isTransport").equalsIgnoreCase("Y")){
						tvo1.setTransport_status("AVAILABLE");
						tvo1.setStage_id(rs.getString("StageId"));
						tvo1.setRouteCode(rs.getString("route"));
						tvo1.setVehicleType(rs.getString("TransportType"));
						
						if(rs.getString("start_month")==null){        //.equalsIgnoreCase("0000-00")
							tvo1.setNoofmonths(months[0]+" - "+months[11]);
							tvo1.setStmonth("0000-00");
						}else{
							String stmnthno[] = rs.getString("start_month").split("-");
							String endmntno[] = rs.getString("end_month").split("-");
							int stmnth = Integer.parseInt(stmnthno[1]);
							int endmnth = Integer.parseInt(endmntno[1]);
							if(stmnth == endmnth){
								tvo1.setNoofmonths(months[stmnth-1]);
							}else{
								tvo1.setNoofmonths(months[stmnth-1]+" - "+months[endmnth-1]);
							}
							tvo1.setStmonth(rs.getString("start_month"));
							tvo1.setEndmonth(rs.getString("end_month"));
						}
					}else{
						tvo1.setTransport_status("NOTAVAILABLE");
					}
				}
			}
	
			 catch (Exception e) 
			   {
					e.printStackTrace();
				}

		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportDaoImpl: gettransportdetailsstudentwise Ending");

	return tvo1;
	}

	@Override
	public ArrayList<TransportVo> settranporttermdetailsforstudent(
			TransportVo tvo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : settranporttermdetailsforstudent Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		int sno=0;
		ArrayList<TransportVo> getranportdetails = new ArrayList<TransportVo>();
     		try {
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement("select termid,termname,startdate,enddate from campus_fee_transport_termdetails where accyear=? AND locationId=?");
				pstmt.setString(1,tvo.getAcy_id());
				pstmt.setString(2,tvo.getLoc_id());
				System.out.println("settranporttermdetailsforstudent -->>"+pstmt);
				rs = pstmt.executeQuery();
				while(rs.next())
				{  
					String termname = null;
					String termid = null;
					String startdate = null;
					String enddate = null;
	                termname = rs.getString("termname");
					termid = rs.getString("termid");
					startdate=(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
					enddate=(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
					
					
						String state_month=startdate.substring(3,5);
						int start =Integer.parseInt(state_month);
						String end_Month=enddate.substring(3,5);
						int end=Integer.parseInt(end_Month);
						for(int i=start-1;i<end;i++)
						{
							
						TransportVo setvo = new TransportVo();
						sno++;
						setvo.setSno(sno);
						System.out.println("state_month:" +state_month);
						System.out.println("end_Month:" +end_Month);
						 String months[] = {"January", "February", "March", "April",
			                     "May", "June", "July", "August", "September",
			                     "October", "November", "December"};
						setvo.setTerm_name(termname); 
						setvo.setTermId(termid);
					    setvo.setMonths(months[i]);
						getranportdetails.add(setvo);
						}
					}
			}
			 catch (Exception e) 
			   {
					e.printStackTrace();
				}

		try {
			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
	}

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportDaoImpl: settranporttermdetailsforstudent Ending");

	return getranportdetails;
}

	@Override
	public ArrayList<TransportVo> getRouteNamelist(TransportVo tvo,UserLoggingsPojo userLoggingsVo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TransportVo> getroutenamelist = new ArrayList<TransportVo>();
         try{
         conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		 pstmt =conn.prepareStatement("select RouteCode,RouteName from transport_route where location_id=? AND isActive='Y'");
		 pstmt.setString(1,tvo.getLoc_id());
	     rs = pstmt.executeQuery();
	     while(rs.next())
		 {
			 TransportVo setvo = new TransportVo();
			 setvo.setRouteCode(rs.getString("RouteCode"));
			 setvo.setRouteName(rs.getString("RouteName"));
			 getroutenamelist.add(setvo);
		 }
		 } 
          catch (Exception e) 
          {
          e.printStackTrace();
          }

         try {
        	 if (rs != null && (!rs.isClosed())) {
        		 rs.close();
        	}
        	if (pstmt != null && (!pstmt.isClosed())){
        		pstmt.close();
        	}
        	if (conn != null && !conn.isClosed()) {
        		conn.close();
        	}
         	} catch (SQLException e) {
         		logger.error(e.getMessage(), e);
         		e.printStackTrace();
         	} catch (Exception e1) {
         		logger.error(e1.getMessage(), e1);
         		e1.printStackTrace();
         	}

         logger.setLevel(Level.DEBUG);
         JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
         logger.info(JDate.getTimeString(new Date())+ " Control in TransportDaoImpl: searchVehicletypeDetails Ending");

return getroutenamelist;
}

	@Override
	public ArrayList<TransportVo> getstoplist(TransportVo tvo,UserLoggingsPojo userLoggingsVo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<TransportVo> getroutenamelist = new ArrayList<TransportVo>();
         try{
         String stagecode =null;
         conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		 pstmt =conn.prepareStatement("select trsm.StageCode from transport_route_stage_mapping trsm join campus_fee_stage cfs ON cfs.stage_id=trsm.StageCode where trsm.RouteCode=? AND trsm.loc_id=? AND cfs.isActive='Y' ");
		 pstmt.setString(1, tvo.getRouteCode());
		 pstmt.setString(2, tvo.getLoc_id());
		/* System.out.println("getstoplist11 -->>"+pstmt);*/
	     rs = pstmt.executeQuery();
	     while(rs.next())
		 {   
			  TransportVo setvo = new TransportVo();
			  stagecode=(rs.getString("StageCode"));
			  PreparedStatement pstmt1 =conn.prepareStatement("SELECT DISTINCT cfs.stage_id,cfs.stage_name,cfsa.stageamount FROM campus_fee_stage cfs JOIN campus_fee_stage_amount cfsa ON cfs.stage_id=cfsa.stageId AND cfs.location=cfsa.locationId WHERE cfs.stage_id=? AND cfs.location=? AND cfs.isActive='Y'");
			  pstmt1.setString(1, stagecode);
			  pstmt1.setString(2, tvo.getLoc_id());
			  ResultSet rs1 = pstmt1.executeQuery();
			 /*System.out.println("getstoplist -->>" +pstmt1);*/
			 
				 while(rs1.next())
				  {   
					  setvo.setStage_id(rs1.getString("stage_id"));
					  setvo.setStopname(rs1.getString("stage_name"));
					  setvo.setAmount(rs1.getInt("stageamount"));
				  }

				  getroutenamelist.add(setvo);
  		      }
		 } 
          catch (Exception e) 
          {
          e.printStackTrace();
          }
  
  finally{
         try {
        if (rs != null && (!rs.isClosed())) {
		rs.close();
        	}
        	if (pstmt != null && (!pstmt.isClosed())) {
		pstmt.close();
        	}
        	if (conn != null && !conn.isClosed()) {
        	conn.close();
        	}
         	} 
         catch (Exception e1) {
         		logger.error(e1.getMessage(), e1);
         		e1.printStackTrace();
         	         	}
    }

logger.setLevel(Level.DEBUG);
JLogger.log(0, JDate.getTimeString(new Date())
+ MessageConstants.END_POINT);
logger.info(JDate.getTimeString(new Date())
+ " Control in TransportDaoImpl: searchVehicletypeDetails Ending");

return getroutenamelist;
}

	@Override
	public String getamountandstatus(TransportVo tvo,UserLoggingsPojo userLoggingsVo) {
		 
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getamountandstatus Starting");
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String status = null;
		String amountstatus=null;
	    double amount =0.0;
         try{
         conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			   pstmt =conn.prepareStatement("SELECT DISTINCT cfsa.stageamount,cfs.status FROM campus_fee_stage cfs JOIN campus_fee_stage_amount cfsa ON cfs.stage_id=cfsa.stageId AND cfs.location=cfsa.locationId WHERE cfs.stage_id=? AND cfsa.accyearId=? AND cfsa.locationId=? AND cfs.isActive='Y'");
			   pstmt.setString(1, tvo.getStage_id());
			   pstmt.setString(2, tvo.getAcy_id());
			   pstmt.setString(3, tvo.getLoc_id());
			   rs = pstmt.executeQuery();
		     System.out.println("getamountandstatus -->>" +pstmt);
			   
			   while(rs.next())
			  {  
				  amount=Double.parseDouble(new DecimalFormat("####.##").format(rs.getDouble("stageamount")));
				  status=(rs.getString("status"));
				  if(status.equalsIgnoreCase("unmapped"))
				  {
					  status="unpaid";
				  }
				  amountstatus = amount +"," + status;  
			  }
            } 
          catch (Exception e) 
          {
          e.printStackTrace();
          }

            try {
             if (rs != null && (!rs.isClosed())) {
     		rs.close();
             	}
             	if (pstmt != null && (!pstmt.isClosed())) {
     		pstmt.close();
             	}
             	if (conn != null && !conn.isClosed()) {
             	conn.close();
             	}
              	} catch (SQLException e) {
     	logger.error(e.getMessage(), e);
     	e.printStackTrace();
              	} catch (Exception e1) {
     	logger.error(e1.getMessage(), e1);
     	e1.printStackTrace();
              	}

logger.setLevel(Level.DEBUG);
JLogger.log(0, JDate.getTimeString(new Date())
+ MessageConstants.END_POINT);
logger.info(JDate.getTimeString(new Date())
+ " Control in TransportDaoImpl: getamountandstatus Ending");

return amountstatus;
}

	@Override
	public String savetransportrequest(TransportPojo pojo,UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : savetransportrequest Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		String result = null;
		int count = 0;
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(TransportUtilConstants.UPDATE_TRANSPORT_REQUEST);
			pstmt.setString(1,pojo.getStageid());
			pstmt.setString(2,pojo.getRouteCode());
			pstmt.setString(3,pojo.getStmonth());
			pstmt.setString(4,pojo.getEndmonth());
			pstmt.setString(5,pojo.getMonthCount());
			pstmt.setString(6,pojo.getVehicleType());
			pstmt.setString(7,pojo.getStuid());
			pstmt.setString(8,pojo.getAccyear());
			pstmt.setString(9,pojo.getLocid());
			
			/*System.out.println("UPDATE_TRANSPORT_REQUEST  -->>"+pstmt);*/
			count = pstmt.executeUpdate();
			if(count > 0){
				HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Transport","Transport Request/Cancel","Update",pstmt.toString(),conn);
				result = "success";
			}else{
				result = "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<StudentRegistrationVo> searchAllAcademicYearDetailsTrans(String locationId, String accYear,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAcademicYearDetails Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String acc_year = accYear;
		String loc_Id = locationId;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_BY_LOCATION_ACCYEAR);
			pst.setString(1, loc_Id);
			pst.setString(2, acc_year);
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				//raji
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt1.setString(1, accYear);
				ResultSet  rs1 =pstmt1.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSection_id(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : searchAllAcademicYearDetails Ending");
		
		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentList(String academic_year, String schoolLocation) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentList  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;
	

		try {
			conn = JDBCConnection.getSeparateConnection();

			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENT_LIST);
			pst.setString(1, schoolLocation);
			pst.setString(2, academic_year);

			rs = pst.executeQuery();
			//System.out.println("Student list from fee Collection:" +pst);
			
			
			while (rs.next()) {
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setClassSectionId(rs.getString("classsection_id_int"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentList  Ending");

		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentListByClass(String locationid, String accyear, String classname){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			if(classname.equalsIgnoreCase("all"))
			{
				classname="%%";
				
				conn = JDBCConnection.getSeparateConnection();
				pst = conn.prepareStatement(TransportUtilConstants.GET_FILTERED_STUDENTDETAILS);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				
			}else{
				conn = JDBCConnection.getSeparateConnection();
				pst = conn.prepareStatement(TransportUtilConstants.GET_FILTERED_STUDENTDETAILS);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
			}
		

			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Ending");

		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			if(sectionid.equalsIgnoreCase("all")){
				sectionid="%%";
				conn = JDBCConnection.getSeparateConnection();
				pst = conn.prepareStatement(TransportUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
			}
			else{
				conn = JDBCConnection.getSeparateConnection();
				pst = conn.prepareStatement(TransportUtilConstants.GET_FILTERED_STUDENTD_BY_SECTION);
				pst.setString(1, locationid.trim());
				pst.setString(2, accyear.trim());
				pst.setString(3, classname.trim());
				pst.setString(4, sectionid.trim());
			}
		
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSectioncode(rs.getString("classsection_id_int"));
				registrationVo.setGender(rs.getString("student_gender_var"));
				registrationVo.setAdmissionno(rs.getString("student_admissionno_var"));
				registrationVo.setSecondLanguage(rs.getString("secondlanguage"));
				registrationVo.setThirdLanguage(rs.getString("thirdlanguage"));
				registrationVo.setSpecilization(rs.getString("specilization"));
		PreparedStatement SecondlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
		SecondlanguageName.setString(1, rs.getString("secondlanguage"));
		ResultSet SecondLangaugeRs=SecondlanguageName.executeQuery();
		while(SecondLangaugeRs.next()){
			registrationVo.setSecondLanguageName(SecondLangaugeRs.getString("subjectName"));
		}
		SecondLangaugeRs.close();
		SecondlanguageName.close();
		PreparedStatement thirdlanguageName=conn.prepareStatement("SELECT subjectName FROM campus_subject WHERE subjectID=?");
		thirdlanguageName.setString(1, rs.getString("thirdlanguage"));
		ResultSet thirdlanguageRs=thirdlanguageName.executeQuery();
		while(thirdlanguageRs.next()){
			registrationVo.setThirdLanguageName(thirdlanguageRs.getString("subjectName"));
		}		
				list.add(registrationVo);
				thirdlanguageRs.close();
				thirdlanguageName.close();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentListBySection  Ending");

		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByList Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_SEARCH_BY_LIST);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByList Ending");
		
		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByAccYear Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String accYear = accyear;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_SEARCH_BY_ACCYEAR);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, accYear);
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByAccYear Ending");
		
		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();


		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_SEARCH_BY_LOCATION);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				//raji
				PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_ACADEMICYEAR_NAME);
				pstmt1.setString(1, stuReg.getAcademicYearId());
				ResultSet  rs1 =pstmt1.executeQuery();
				while(rs1.next())
				{
					stuReg.setAcademicYear(rs1.getString("acadamic_year"));
				}
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchListByLocation Ending");
		
		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear,
			String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
	
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_SEARCH_BY_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");

	
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByFilter Ending");
		
		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,
			String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByClass Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_SEARCH_BY_CLASS);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");
			pst.setString(10, "%"+className+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				list.add(stuReg);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByClass Ending");
		return list;
	}

	@Override
	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,
			String classname, String sectionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Starting");
		
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String searchValue = searchTerm;
		String locationId = locationid;
		String accYear = accyear;
		String className = classname;
		String section = sectionid;
		int sno = 0;
		
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement(TransportUtilConstants.GET_STUDENTS_SEARCH_BY_ALL_FILTER);
			pst.setString(1, "%"+searchValue+"%");
			pst.setString(2, "%"+searchValue+"%");
			pst.setString(3, "%"+searchValue+"%");
			pst.setString(4, "%"+searchValue+"%");
			pst.setString(5, "%"+searchValue+"%");
			pst.setString(6, "%"+searchValue+"%");
			pst.setString(7, "%"+searchValue+"%");
			pst.setString(8, "%"+locationId+"%");
			pst.setString(9, "%"+accYear+"%");
			pst.setString(10, className);
			pst.setString(11, section);
			rs = pst.executeQuery();
			while(rs.next()){
				StudentRegistrationVo stuReg = new StudentRegistrationVo();
				sno++;
				stuReg.setSno(String.valueOf(sno));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				stuReg.setLocationId(rs.getString("locationId"));
				stuReg.setStudentrollno(rs.getString("student_rollno"));
				stuReg.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentFullName(rs.getString("studentName"));
				stuReg.setClassname(rs.getString("classdetails_name_var"));
				stuReg.setSectionnaem(rs.getString("classsection_name_var"));
				stuReg.setSectioncode(rs.getString("classsection_id_int"));
				stuReg.setClassDetailId(rs.getString("classdetail_id_int"));
				list.add(stuReg);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentSearchByAllFilter Ending");
		
		return list;
	}

	@Override
	public String waivedOfftransportrequest(TransportPojo pojo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : savetransportrequest Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		String result = null;
		int count = 0;
		try{
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TransportUtilConstants.UPDATE_TRANSPORT_WAIVEDOFF);
			pstmt.setString(1,pojo.getStuid());
			pstmt.setString(2,pojo.getAccyear());
			pstmt.setString(3,pojo.getLocid());
			System.out.println("UPDATE_TRANSPORT_WAIVEDOFF -->>"+pstmt);
			count = pstmt.executeUpdate();
			if(count > 0){
				HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Transport","Transport Request/Cancel","Cancel",pstmt.toString(),userLoggingsVo);
				result = "success";
			}else{
				result = "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : waivedOfftransportrequest Ending");
		return result;
	}

	@Override
	public ArrayList<TransportVo> getMonthList(String accyear,UserLoggingsPojo userLoggingsVo,String locId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getMonthList Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int j=0;
		String monthname[] = {"January", "February", "March", "April",
                "May", "June", "July", "August", "September",
                "October", "November", "December"};
		ArrayList<TransportVo> list = new ArrayList<TransportVo>();
		try{
			
			System.out.println("Current month  -->>"+Calendar.getInstance().get(Calendar.MONTH));
			
				String [] currdate = HelperClass.getCurrentSqlDate().toString().split("-");
				/*int currdate1 = Integer.parseInt(currdate[1]);*/
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement("select termid,termname,MIN(startdate) as startdate,MAX(enddate) as enddate from campus_fee_transport_termdetails where accyear=? AND locationId=?");
				pstmt.setString(1,accyear);
				pstmt.setString(2,locId);
				/*System.out.println("getMonthList -->>"+pstmt);*/
				rs = pstmt.executeQuery();
				while(rs.next())
				{  
					String termname = null;
					String termid = null;
					String startdate = null;
					String enddate = null;
	                termname = rs.getString("termname");
					termid = rs.getString("termid");
					startdate=(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
					enddate=(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
					
						int start =Calendar.getInstance().get(Calendar.MONTH)+1;
						int end=Integer.parseInt(enddate.substring(3,5));
						
						/*System.out.println("start  -->>"+start+"  end  -->>"+end);*/
						
						if(start>end){
							for(int i=start-1;i<monthname.length;i++)
							{
								/*System.out.println("Start date -->>"+i+" "+monthname[i]);*/
								TransportVo vo = new TransportVo();
								vo.setMonths(monthname[i]);
								vo.setMonthNo(accyear+"-"+(i+1));
								list.add(vo);
					        }
							for(int i=0;i<end;i++)
							{
								/*System.out.println("end date -->>"+i+" "+monthname[i]);*/
								TransportVo vo = new TransportVo();
								vo.setMonths(monthname[i]);
								vo.setMonthNo(accyear+"-"+(i+1));
								list.add(vo);
					        }
						}else{
							for(int i=start-1;i<end;i++)
							{
								TransportVo vo = new TransportVo();
								vo.setMonths(monthname[i]);
								vo.setMonthNo(accyear+"-"+(i+1));
								list.add(vo);
					        }
						}
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getMonthList Ending");
		return list;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> schoolListStage(String loc,UserLoggingsPojo custdetails) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: accYearListStatus : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("select Location_Id,Location_Name from campus_location where Location_Id like ? and isActive='Y'");
			pstmt.setString(1,loc);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setLocationid(rs.getString("Location_Id"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				PreparedStatement statuspstmt = conn.prepareStatement("SELECT COUNT(*) FROM `campus_fee_stage` WHERE `location`=? and isActive='Y' ");
				statuspstmt.setString(1,rs.getString("Location_Id"));
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				if(count > 0){
					obj.setStatus("Set");
				}
				else{
					obj.setStatus("Not Set");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		return list;

	}

	@Override
	public ArrayList<StageMasterVo> getStage(String accyear, String loc, UserLoggingsPojo userLoggingsVo) {logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportDaoImpl : getMonthList Starting");
	
	Connection conn = null;
	PreparedStatement pstmt = null,pstmt1 = null;
	ResultSet rs =null,rs1 =null;
	ArrayList<StageMasterVo> list = new ArrayList<StageMasterVo>();
	try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_STAGE_LIST);
			pstmt.setString(1,loc);
			rs = pstmt.executeQuery();
			while(rs.next())
			{  
				StageMasterVo vo=new StageMasterVo();
				vo.setStageCode(rs.getString("stage_id"));
				vo.setStageName(rs.getString("stage_name"));
				pstmt1 = conn.prepareStatement("SELECT `stageamount`,id FROM `campus_fee_stage_amount` WHERE `accyearId`=? AND `locationId`=? AND stageId=?");
				pstmt1.setString(1, accyear);
				pstmt1.setString(2, loc); 
				pstmt1.setString(3, rs.getString("stage_id"));
				rs1=pstmt1.executeQuery();
					while(rs1.next()) {
						vo.setStageAmount(rs1.getDouble("stageamount"));
						vo.setFeeSetupCode(rs1.getString("id"));
				     }
				
				list.add(vo);
			}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TransportDaoImpl : getMonthList Ending");
	return list;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> schoolList(String accyear, String loc, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuDaoImpl: schoolList : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		long count1=0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("select ca.acadamic_id,ca.acadamic_year,cl.Location_Id,cl.Location_Name from campus_acadamicyear ca,campus_location cl where ca.acadamic_id like ? and cl.Location_Id like ? and cl.isActive='Y' and ca.isActive='Y' order by startDate");
			pstmt.setString(1,accyear);
			pstmt.setString(2,loc);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(rs.getString("acadamic_id"));
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setLocationid(rs.getString("Location_Id"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				PreparedStatement statuspstmt = conn.prepareStatement("SELECT COUNT(*),SUM(cfsa.stageamount) FROM campus_fee_stage_amount cfsa LEFT JOIN campus_fee_stage cs ON cs.stage_id=cfsa.stageId AND cfsa.accyearId =? WHERE   cfsa.locationId = ? AND cs.isActive='Y'");
				statuspstmt.setString(1,rs.getString("acadamic_id"));
				statuspstmt.setString(2,rs.getString("Location_Id"));
				System.out.println("schoolList -->>"+statuspstmt);
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
					count1=statusrs.getLong(2);
				}
				if(count > 0 && count1>0){
					obj.setStatus("Set");
				}
				else{
					obj.setStatus("Not Set");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} 
			  catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : schoolList Ending");
		return list;

	}

	@Override
	public ArrayList<AcadamicYearVO2> getPreAcc(String accyear, String loc, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl: getPreAcc: Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<AcadamicYearVO2> list = new ArrayList<AcadamicYearVO2>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT `acadamic_id`,`acadamic_year` FROM campus_acadamicyear WHERE startDate<(SELECT startDate FROM `campus_acadamicyear` WHERE `acadamic_id`=?) and isActive='Y'");
			pstmt.setString(1,accyear);
			rs = pstmt.executeQuery();
			while(rs.next()){
				AcadamicYearVO2 vo2=new AcadamicYearVO2();
				vo2.setAcadamicyear_id_int(rs.getString("acadamic_id"));
				vo2.setAcadamicyear_year(rs.getString("acadamic_year"));
				list.add(vo2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} 
			   catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getPreAcc Ending");
		return list;
	 }

	@Override
	public String addStageWiseAmount(String accyear, String loc, String[] stageid, String[] amount, String user,
			UserLoggingsPojo userLoggingsVo,String log_audit_session) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl: addStageWiseAmount : Starting");
		PreparedStatement pstmt= null,pstmt1= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		String status=null,id=null;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			/*pstmt2=conn.prepareStatement("SELECT COUNT(*) FROM `campus_fee_stage_amount` WHERE `accyearId`=? AND `locationId`=?");
			int m=pstmt2.executeUpdate();
			if(m>0) {
				
				
			}*/
			/*conn.setAutoCommit(false);*/
			pstmt1 = conn.prepareStatement("DELETE FROM `campus_fee_stage_amount` WHERE `stageId`=? AND accyearId=? AND locationId=?");
			for(int i=0; i<stageid.length; i++) {
				pstmt1.setString(1, stageid[i]);
				pstmt1.setString(2, accyear);
				pstmt1.setString(3, loc);
				pstmt1.executeUpdate();
			}
			pstmt = conn.prepareStatement("INSERT INTO campus_fee_stage_amount(`stageId`,`accyearId`,`locationId`,`stageamount`,`createdby`,`createdtime`) VALUES(?,?,?,?,?,NOW())");
			
			for (int i=0; i<stageid.length; i++) {
				pstmt.setString(1,stageid[i]);
				pstmt.setString(2,accyear);
				pstmt.setString(3,loc);
				pstmt.setString(4,amount[i]);
				pstmt.setString(5,user);
				
			    count=pstmt.executeUpdate();
			    if (count > 0) {
					HelperClass.recordLog_Activity(log_audit_session,"Transport","Stage Wise Amount Setup","Insert",pstmt.toString(),userLoggingsVo);
				} 
			   // System.out.println("addStageWiseAmount -->>"+pstmt);
			}
			
			if(count>0) {
				status="success";
				/*conn.commit();*/
			}else {
				status="failure";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return status;

	}

	@Override
	public ArrayList<AcadamicYearVO2> getPresentAndNextAccyear(String accyear, String loc,
			UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl: getPresentAndNextAccyear: Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<AcadamicYearVO2> list = new ArrayList<AcadamicYearVO2>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT `acadamic_id`,`acadamic_year` FROM campus_acadamicyear WHERE startDate>=(SELECT startDate FROM `campus_acadamicyear` WHERE `acadamic_id`=?) AND isActive='Y' LIMIT 2");
			pstmt.setString(1,accyear);
			rs = pstmt.executeQuery();
			while(rs.next()){
				AcadamicYearVO2 vo2=new AcadamicYearVO2();
				vo2.setAcadamicyear_id_int(rs.getString("acadamic_id"));
				vo2.setAcadamicyear_year(rs.getString("acadamic_year"));
				list.add(vo2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} 
			   catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getPresentAndNextAccyear Ending");
		return list;
	 }

	@Override
	public ArrayList<ExaminationDetailsVo> schoolListRoutMaster(String loc, UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl: schoolListRoutMaster : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("select Location_Id,Location_Name from campus_location where Location_Id like ? and isActive='Y'");
			pstmt.setString(1,loc);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setLocationid(rs.getString("Location_Id"));
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				PreparedStatement statuspstmt = conn.prepareStatement("SELECT COUNT(*) FROM transport_route WHERE location_id=? and isActive='Y'");
				statuspstmt.setString(1,rs.getString("Location_Id"));
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				if(count > 0){
					obj.setStatus("Set");
				}
				else{
					obj.setStatus("Not Set");
				}
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;

	}

	@Override
	public List<TransportVo> routeMasterSettingBySearch(TransportVo vo, UserLoggingsPojo custdetails) 
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportSetupDaoImpl: getTransportMasterDaoDetails Starting");
		ArrayList<TransportVo> list = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		TransportVo obj = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = connection.prepareStatement(TransportUtilConstants.ROUTE_LIST_BY_STATUS_AND_SEARCH);
			pstmt.setString(1, vo.getLoc_id());
			pstmt.setString(2, vo.getSearchName()+"%");
			pstmt.setString(3, vo.getSearchName()+"%");
			pstmt.setString(4, vo.getSearchName()+"%");
			pstmt.setString(5, vo.getSearchName()+"%");
			pstmt.setString(6, vo.getSearchName()+"%");
			pstmt.setString(7, vo.getSearchName()+"%");
			pstmt.setString(8, vo.getSearchName()+"%");
			pstmt.setString(9, vo.getSearchName()+"%");
			pstmt.setString(10, vo.getStatus());
			
			/*System.out.println("ROUTE_LIST_BY_STATUS_AND_SEARCH -->>"+pstmt);*/
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {

					obj = new TransportVo();
					obj.setRouteNo(resultSet.getString("Route_No"));
					obj.setRouteName(resultSet.getString("RouteName"));
					obj.setRouteCode(resultSet.getString("RouteCode"));
					obj.setStratTime(resultSet.getString("Start_Time"));
					obj.setEndTime(resultSet.getString("End_Time"));
					obj.setTotalStops(resultSet.getString("Total_Stops"));
					obj.setTotalDistance(resultSet.getString("TotalDistance"));
					obj.setHalttime(resultSet.getString("HaltTime"));
					obj.setRemarks(resultSet.getString("remarks"));
					//obj.setDestination(resultSet.getString("Destination"));

					list.add(obj);
				}
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: getRouteMasterDaoDetails Ending");
		return list;
	}

	@Override
	public List<TransportVo> transportCategoryListByStatus(TransportVo vo, UserLoggingsPojo custdetails)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : transportCategoryListByStatus Starting");
		List<TransportVo> getvehiclelist = new ArrayList<TransportVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = connection.prepareStatement(TransportUtilConstants.GET_ALL_VEHICLE_TYPE_DETAILS_BY_STATUS);
			pstmt.setString(1, vo.getStatus());
			pstmt.setString(2, vo.getSearchName()+"%");
			pstmt.setString(3, vo.getSearchName()+"%");
			pstmt.setString(4, vo.getSearchName()+"%");
			System.out.println("GET_ALL_VEHICLE_TYPE_DETAILS_BY_STATUS -->>"+pstmt);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				TransportVo tform = new TransportVo();
				tform.setVehicleId(rs.getString("type_id"));
				tform.setVehicleType(rs.getString("type_name"));
				tform.setDescription(rs.getString("type_description"));
				tform.setRemarks(rs.getString("remarks"));
				getvehiclelist.add(tform);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : transportCategoryListByStatus  Ending");
		return getvehiclelist;
	}

	@Override
	public List<DriverMsaterListVo> driverListByStatus(DriverMsaterListVo vo, UserLoggingsPojo custdetails) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : driverListByStatus Starting");

		System.out.println("driver dao");

		ArrayList<DriverMsaterListVo> driverlist = new ArrayList<DriverMsaterListVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
         int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(TransportUtilConstants.GET_DRIVER_LIST_BY_STATUS_AND_SEARCH);
			pstmt.setString(1, vo.getStatus());
			pstmt.setString(2, vo.getSearch()+"%");
			pstmt.setString(3, vo.getSearch()+"%");
			pstmt.setString(4, vo.getSearch()+"%");
			pstmt.setString(5, vo.getSearch()+"%");
			pstmt.setString(6, vo.getSearch()+"%");
			pstmt.setString(7, vo.getSearch()+"%");
			pstmt.setString(8, vo.getLocId());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				DriverMsaterListVo listvo = new DriverMsaterListVo();
				String validity = "";
				String dlno = "";


				if (rs.getString("DLExpirayDate") != null && !rs.getString("DLExpirayDate").isEmpty()) {
					validity = HelperClass.convertDatabaseToUI(rs.getString("DLExpirayDate"));
				}

				if (rs.getString("DLNo") != null&& !rs.getString("DLNo").isEmpty()) {
					dlno = rs.getString("DLNo");
				}
				
				listvo.setSlno(count);
				listvo.setType(rs.getString("type"));
				listvo.setDriverName(rs.getString("Name"));
				listvo.setDateofBirth(HelperClass.convertDatabaseToUI(rs.getString("DOB")));
				listvo.setDateofJoin(HelperClass.convertDatabaseToUI(rs.getString("DOJ")));
				listvo.setMobile(rs.getString("MobileNo"));
				listvo.setDrivingliecenseNo(dlno);
				listvo.setDriverCode(rs.getString("DriverCode"));
				listvo.setFather_name(rs.getString("FatherName"));
				listvo.setEmerg_contact(rs.getString("EmergencyContactNo"));
				listvo.setAddress(rs.getString("Address"));
				listvo.setAge(rs.getString("Age"));
				listvo.setGender(rs.getString("Gender"));
				listvo.setDl_validity(validity);
				listvo.setLicense(rs.getString("LicencetoDrive"));
				listvo.setExperience(rs.getString("Experience"));
				listvo.setRemarks(rs.getString("remarks"));
				
				driverlist.add(listvo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
          
		finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : driverListByStatus  Ending");
		return driverlist;
	}


	public String getLicenceValidationCount(String locId, String drivingLicenseNo, Connection connection)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getLicenceValidationCount : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String licence=null;
	
		try {
			psmt = connection.prepareStatement(TransportUtilConstants.VALIDATE_LICENSE);
			psmt.setString(1, drivingLicenseNo.trim());
			psmt.setString(2, locId);
			System.out.println("VALIDATE_LICENSE-->>"+psmt);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				licence=rs.getString("driverCount");
			}
			else{
				licence = "notfound";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getLicenceValidationCount :  Ending");
		return licence;
	}

	@Override
	public String saveTransportConcession(StudentConcessionVo vo, UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in TransportSetupDaoImpl: saveTransportConcession : Starting");
		Connection conn = null;
		PreparedStatement ps_insertPlan = null;
		PreparedStatement ps_check = null;
		ResultSet rs_check = null;
		int count = 0;
		int countcheck = 0;
		String status = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			ps_check = conn.prepareStatement("SELECT COUNT(*) FROM campus_transport_concessiondetails WHERE studentIdNo=? AND academic_year=?");

			ps_check.setString(1, vo.getStudentId());
			ps_check.setString(2, vo.getAcademicYear());
			rs_check = ps_check.executeQuery();
			if (rs_check.next()) {
				countcheck = rs_check.getInt(1);
			}
			if (countcheck == 0) {
				for (int i = 0; i < vo.getConcessionAmount().split(",").length; i++) {
					ps_insertPlan = conn.prepareStatement("INSERT INTO campus_transport_concessiondetails (studentIdNo,classId,termcode,concessionType,concession,academic_year) VALUES(?,?,?,?,?,?)");
					ps_insertPlan.setString(1, vo.getStudentId());
					ps_insertPlan.setString(2, vo.getClassId());
					ps_insertPlan.setString(3, vo.getTermcode().split(",")[i]);
					ps_insertPlan.setString(4, vo.getContype());
					ps_insertPlan.setString(5, vo.getConcessionAmount().split(",")[i]);
					ps_insertPlan.setString(6, vo.getAcademicYear());
					System.out.println("saveTransportConcession 11-->>"+ps_insertPlan);
					count = ps_insertPlan.executeUpdate();
					if (count > 0) {
						status = "true";
					} else {
						status = "false";
					}
				}
			} else {
				PreparedStatement pstmtd = conn.prepareStatement("DELETE FROM campus_transport_concessiondetails WHERE studentIdNo=? AND academic_year=?");
				pstmtd.setString(1, vo.getStudentId());
				pstmtd.setString(2, vo.getAcademicYear());
				int abc = pstmtd.executeUpdate();
				pstmtd.close();
				for (int i = 0; i < vo.getConcessionAmount().split(",").length; i++) {

					ps_insertPlan = conn.prepareStatement("INSERT INTO campus_transport_concessiondetails (studentIdNo,classId,termcode,concessionType,concession,academic_year) VALUES(?,?,?,?,?,?)");
					ps_insertPlan.setString(1, vo.getStudentId());
					ps_insertPlan.setString(2, vo.getClassId());
					ps_insertPlan.setString(3, vo.getTermcode().split(",")[i]);
					ps_insertPlan.setString(4, vo.getContype());
					ps_insertPlan.setString(5, vo.getConcessionAmount().split(",")[i]);
					ps_insertPlan.setString(6, vo.getAcademicYear());
					System.out.println("saveTransportConcession 12-->>"+ps_insertPlan);
					count = ps_insertPlan.executeUpdate();
					if (count > 0) {
						status = "true";
					} else {
						status = "false";
					}
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (ps_insertPlan != null && (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
				}

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in TransportSetupDaoImpl: saveTransportConcession: Ending");

		return status;
	}

	public String deleteTranportConcessionDetails(AddFeeVO vo, UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in TransportSetupDaoImpl: deleteTranportConcessionDetails : Starting");
		Connection conn = null;
		PreparedStatement ps_insertPlan = null;
		int count = 0;
		String status = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			for (int i = 0; i < vo.getGetDataArray().length; i++) {
				ps_insertPlan = conn.prepareStatement("DELETE FROM campus_transport_concessiondetails WHERE studentIdNo=? AND academic_year=?");
				ps_insertPlan.setString(1, vo.getGetDataArray()[i]);
				ps_insertPlan.setString(2, vo.getAccYearArray()[i]);
				count = ps_insertPlan.executeUpdate();
				
				if (count > 0) {
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Transport","TransportFeeConcessionDetails","Delete",ps_insertPlan.toString(),userLoggingsVo);
					status = "true";
				} else {
					status = "false";
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if (ps_insertPlan != null && (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
				}

				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in TransportSetupDaoImpl: deleteTranportConcessionDetails : Ending");

		return status;
	}

	public ArrayList<TransportVo> getVehicleTypeList(TransportVo tvo, UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in TransportSetupDaoImpl: getVehicleTypeList : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TransportVo> getroutenamelist = new ArrayList<TransportVo>();
         try{
         conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		 pstmt =conn.prepareStatement("SELECT type_id,type_name FROM transport_typedetails WHERE isActive='Y'");
		System.out.println("getVehicleTypeList -->>"+pstmt);
	     rs = pstmt.executeQuery();
	     while(rs.next())
		 {
			 TransportVo setvo = new TransportVo();
			 setvo.setTypeId(rs.getString("type_id"));
			 setvo.setTypeName(rs.getString("type_name"));
			 getroutenamelist.add(setvo);
		   }
		 } 
          catch (Exception e) 
          {
          e.printStackTrace();
          }

         try {
        	 if (rs != null && (!rs.isClosed())) {
        		 rs.close();
        	}
        	if (pstmt != null && (!pstmt.isClosed())){
        		pstmt.close();
        	}
        	if (conn != null && !conn.isClosed()) {
        		conn.close();
        	}
         	} catch (SQLException e) {
         		logger.error(e.getMessage(), e);
         		e.printStackTrace();
         	} catch (Exception e1) {
         		logger.error(e1.getMessage(), e1);
         		e1.printStackTrace();
         	}

         logger.setLevel(Level.DEBUG);
         JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
         logger.info(JDate.getTimeString(new Date())+ " Control in TransportDaoImpl: searchVehicletypeDetails Ending");

       return getroutenamelist;
}

	@Override
	public String valideDriverCode(String drivername,String locId,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : valideDriverCode : Starting");
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String status=null;
        int count=0;
	
		try {
			conn=JDBCConnection.getSeparateConnection(custdetails);
			psmt = conn.prepareStatement(TransportUtilConstants.VALIDATE_DRIVER_CODE);
			psmt.setString(1, drivername);
			psmt.setString(2, locId);
			
			System.out.println("VALIDATE_DRIVER_CODE-->>"+psmt);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				count=rs.getInt(1);
			}
			if(count>0){
				status="true";
			}
			else{
				status="false";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : valideDriverCode :  Ending");
		return status;
	}

	@Override
	public String getTransportYearCollection(UserLoggingsPojo userLoggingsVo, String accyear,
			String location_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getTransportYearCollection : Starting");
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String status=null;
        
	
		try {
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			psmt = conn.prepareStatement(TransportUtilConstants.GET_TOTAL_YEAR_COLLECTION);
			psmt.setString(1, accyear); 
			psmt.setString(2, location_id);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				status=rs.getString("total");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getTransportYearCollection :  Ending");
		return status;
	}

	@Override
	public String getTodayCollection(UserLoggingsPojo userLoggingsVo, String location_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getTodayCollection : Starting");
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String status=null;
        
	
		try {
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			psmt = conn.prepareStatement(TransportUtilConstants.GET_TODAY_COLLECTION);
			
			java.sql.Date date  = HelperClass.getCurrentSqlDate();
			
			psmt.setDate(1, date);
			psmt.setString(2, location_id);
			
			rs = psmt.executeQuery();
			System.out.println(psmt);
			while (rs.next()) {
				status=rs.getString("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getTodayCollection :  Ending");
		return status;
	}

	public ArrayList<FeeCollectionVo> getTransportPaymentHistory(String feeCodeDetails,
			UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportAvailableStudent Starting");
		
		PreparedStatement pst = null,pst1=null;
		ResultSet rs = null,rs1=null;
		Connection conn = null;
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		try {
			String[] feeDatails = feeCodeDetails.split(",");
			String stuidno = feeDatails[0];
			String accyearId = feeDatails[1];
			String receiptno="";
			double amount_paid=0,balance_amount=0;
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pst = conn.prepareStatement("SELECT DISTINCT reciept_no FROM `campus_tranport_fee_collection_details` WHERE admissionNo=? AND accYear=? GROUP BY reciept_no");
			pst.setString(1, stuidno);
			pst.setString(2, accyearId);
			rs = pst.executeQuery();
			while (rs.next()) {
				FeeCollectionVo vo=new FeeCollectionVo();
				String termname="",termID="";
				String comma=",";
				int count=0;
				receiptno=rs.getString("reciept_no");
				pst1 = conn.prepareStatement("SELECT DISTINCT ctfc.MonthName,ctfp.advance,ctfc.`reciept_no`,ctfc.`amount_paid`,ctfp.`balance`,ctfc.`paidDate`,ctfc.`modeofpayment`,cftt.`termname`,ctfc.termcode FROM `campus_tranport_fee_collection_details` ctfc LEFT JOIN `campus_fee_transport_termdetails` cftt ON cftt.`termid`=ctfc.`termcode` LEFT JOIN campus_transport_fees_payments ctfp ON ctfp.receiptno=ctfc.reciept_no WHERE ctfc.admissionNo=? AND ctfc.accYear=? and ctfc.`reciept_no`=?");
				pst1.setString(1, stuidno);
				pst1.setString(2, accyearId);
				pst1.setString(3, receiptno);
				/*System.out.println("getTransportPaymentHistory -->>"+pst1);*/
				rs1 = pst1.executeQuery();
				while (rs1.next()) {
					count++;
					vo.setReceiptno(rs1.getString("reciept_no"));
					amount_paid+=rs1.getDouble("amount_paid");
					balance_amount+=rs1.getDouble("balance");
					vo.setBilldate(HelperClass.convertDatabaseToUI(rs1.getString("paidDate")));
					vo.setPaymentMode(rs1.getString("modeofpayment"));
					if(count > 1){
						termname=termname+comma+rs1.getString("termname")+" ("+rs1.getString("MonthName")+") \n";
						termID=termID+comma+rs1.getString("termcode");
					}else{
						termname=termname+rs1.getString("termname")+" ("+rs1.getString("MonthName")+") \n";
						termID=termID+rs1.getString("termcode");
					}
					vo.setTermid(termID);
					vo.setTot_paid_amt(amount_paid);
					vo.setOutstanding_balance(balance_amount);
					vo.setTermName(termname);
					vo.setMonths(rs1.getString("MonthName"));
					vo.setAdvanceAmount(rs1.getDouble("advance"));
				}
				list.add(vo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportAvailableStudent Ending");

		return list;
	}

	
	//transport dashboard
	public List<FeeCollectionVo> getTransportfee(UserLoggingsPojo userLoggingsVo, String accyear, String locId, String flagName) {
		
		PreparedStatement pst = null,pst1=null,pst2=null,pst3=null,pst4=null,pst5=null,pst6=null,pst7=null,pst8=null;
		ResultSet rs = null,rs1=null,rs2=null,rs3=null,rs4=null,rs6=null,rs5=null,rs7=null,rs8=null;
		Connection conn = null;
		String termName[]=null;
		
		List<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		List<FeeCollectionVo> termList=new ArrayList<FeeCollectionVo>();
		List<FeeCollectionVo> vechileNameList=new ArrayList<FeeCollectionVo>();
		List<FeeCollectionVo> driverList=new ArrayList<FeeCollectionVo>();
		List<FeeCollectionVo> transportFee=new ArrayList<FeeCollectionVo>();
		List<FeeCollectionVo> dashboardFee=new ArrayList<FeeCollectionVo>();

		try {
			
			FeeCollectionVo mainVo = new FeeCollectionVo();
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
		//transport dashboard
		if(flagName.equalsIgnoreCase("transport")){
			pst5 = conn.prepareStatement("SELECT `termid`,`termname` FROM `campus_fee_transport_termdetails` WHERE `locationId`=? AND `accyear`=?  AND `isTransportTerm`='Y' ORDER BY LENGTH(`termid`)");
			pst5.setString(1, locId);
			pst5.setString(2, accyear);
			/*System.out.println(pst5);*/
			rs5=pst5.executeQuery();
			while(rs5.next()){
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setTransportTermname(rs5.getString("termname"));
				
				pst6 = conn.prepareStatement("SELECT SUM(`totalamount`) totAmt FROM `campus_tranport_fee_collection_details` WHERE `termcode`=? AND `accYear`=? AND `is_paid`='Y'");
				pst6.setString(1, rs5.getString("termid"));
				pst6.setString(2, accyear);
				/*System.out.println(pst6);*/
				rs6=pst6.executeQuery();
				while(rs6.next()){
					vo.setTransportTermwiseFeeCollection(rs6.getDouble("totAmt"));
					transportFee.add(vo);
				}
			}
			
			
				//-------------------2. transport category
			pst2 = conn.prepareStatement("SELECT DISTINCT `type_name`,`type_id` FROM `transport_typedetails` WHERE `isActive`='Y' ");
			rs2 = pst2.executeQuery();
			while(rs2.next()){
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setTrasVechicleName(rs2.getString("type_name"));
				pst3 = conn.prepareStatement("SELECT DISTINCT COUNT(`student_id_int`) totStudents  FROM `campus_student_transportdetails` WHERE `isTransport`='Y' AND `TransportType`=? AND `fms_acadamicyear_id_int`=? AND `locationId`=?");
				pst3.setString(1, rs2.getString("type_id"));
				pst3.setString(2, accyear);
				pst3.setString(3, locId);
				rs3 = pst3.executeQuery();
				if(rs3.next()){
					vo.setNoOfStudents(rs3.getInt("totStudents"));
					vechileNameList.add(vo);
				}
			    }
			
			//------------------- DriverName and contact details
			
			pst4 = conn.prepareStatement("SELECT DISTINCT `Name`,`MobileNo`,`EmergencyContactNo`,Age  FROM `transport_driver` WHERE `isActive`='Y' AND `locId`=? AND `type`='driver' ORDER BY NAME");
			pst4.setString(1, locId);
			rs4 = pst4.executeQuery();
			while(rs4.next()){
				FeeCollectionVo vo=new FeeCollectionVo();
				vo.setDriverName(rs4.getString("Name"));
				vo.setContactNo(rs4.getString("MobileNo"));
				vo.setEmergenceyContactNo(rs4.getString("EmergencyContactNo"));
				vo.setAge(rs4.getString("Age"));
				driverList.add(vo);
			}
			
			
		}
			
			
			//fee dashboard
		else{
				pst7=conn.prepareStatement("SELECT `termid`,`termname` FROM `campus_fee_termdetails` WHERE `isActive`='Y' AND `locationId`=? AND `accyear`=? ORDER BY LENGTH(`termid`)");
				pst7.setString(1, locId);
				pst7.setString(2, accyear);
				/*System.out.println(pst7);*/
				rs7=pst7.executeQuery();
				while(rs7.next()){
					FeeCollectionVo vo = new FeeCollectionVo();
					vo.setFeeTermName(rs7.getString("termname"));
					pst8=conn.prepareStatement("SELECT SUM(`amount_paid`) totAmt FROM `campus_fee_collection` WHERE `termcode`=? AND `accYear`=?");
					pst8.setString(1, rs7.getString("termid"));
					pst8.setString(2, accyear);
					/*System.out.println(pst8);*/
					rs8=pst8.executeQuery();
					while(rs8.next()){
						vo.setFeeTermwiseCollection(rs8.getDouble("totAmt"));
						dashboardFee.add(vo);
					}
				}
			
			}
			
			mainVo.setTermNameList(termList);
			mainVo.setVechileNameList(vechileNameList);
			mainVo.setDriverContactNoList(driverList);
			mainVo.setTransportDashboard(transportFee);
			mainVo.setDashboardFeeCollection(dashboardFee);
			list.add(mainVo);
			
			
			//------------ transport dashboard : Transport fee
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rs7 != null && (!rs7.isClosed())) {
					rs7.close();
				}
				if (rs8 != null && (!rs8.isClosed())) {
					rs8.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (pst7 != null && (!pst7.isClosed())) {
					pst7.close();
				}
				if (pst8 != null && (!pst8.isClosed())) {
					pst8.close();
				}
				
				
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportAvailableStudent Ending");

		return list;
	}

}

