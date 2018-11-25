package com.cerp.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;

import com.cerp.rest.model.Customer;
import com.cerp.rest.model.CustomerDB;
import com.cerp.rest.model.CustomerInfo;
import com.cerp.rest.model.CustomerLoginDetails;
import com.cerp.rest.model.DomainVerification;
import com.cerp.rest.model.Productdetails;
import com.cerp.rest.util.CreateCustDB;
import com.cerp.rest.util.DBConnection;
import com.cerp.rest.util.GenerateCrendentials;
import com.cerp.rest.util.GenerateId;
import com.cerp.rest.util.PleskApiClient;
import com.cerp.rest.util.SQLConstants;
import com.cerp.rest.util.SendMail;
import com.cerp.rest.util.StreamConstants;

public class CustomerDaoImpl extends CustomerRepository{
	private static final Logger logger = Logger.getLogger(CustomerDaoImpl.class);
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	static final String JDBC_DRIVER = res.getString("JDBC_DRIVER");  
	static final String DB_HOST = res.getString("ipaddress");
	static final String USER = res.getString("username");
	static final String PASS = res.getString("password");
	static final String PROTOCOL = res.getString("protocal"); 
	static final String DOMAIN_NAME = res.getString("domain_name");
	
	static final String PUSERNAME = res.getString("pusername"); 
	static final String PPWD = res.getString("ppassword"); 
	
	static final String CUSTDBHOST = res.getString("custdbhost");
	static final String PRODNAME = res.getString("productname");
	/*@Override
	public String addCustomer(Customer customerdetails) {
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: addCustomer Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String id = null;
		String result = null;
		try{
			id = GenerateId.getPrimaryKeyID("campus_customer_details");
			customerdetails.setCustId(id);
			
			
			customerdetails.setApppwd(String.valueOf(GenerateCrendentials.generatePwd(8)));
			customerdetails.setCustdbpassword(customerdetails.getApppwd());
			
			//int status = saveCustomerdetails(customerdetails);
			customerdetails.setCustdbname(customerdetails.getUrlname()); 
			customerdetails.setCustdbusername(customerdetails.getUrlname());
			customerdetails.setAppusername(customerdetails.getCustfname());
			customerdetails.setCustdbhost("localhost");
						
			customerdetails.setCustdomain(customerdetails.getUrlname()+"."+DOMAIN_NAME);
						
			conn = DBConnection.getmasterConnection();
						
			pstmt = conn.prepareStatement(SQLConstants.INSERT_CUSTOMER);
			pstmt.setString(1,customerdetails.getCustId());
			pstmt.setString(2,customerdetails.getCustfname());
			pstmt.setString(3,customerdetails.getCustlname());
			pstmt.setString(4,customerdetails.getPhone());
			pstmt.setString(5,customerdetails.getSubtype());
			pstmt.setString(6,customerdetails.getPurchasedate().substring(0,10));
			pstmt.setString(7,customerdetails.getAppusername());
			pstmt.setString(8,customerdetails.getApppwd());
			pstmt.setString(9,customerdetails.getEmail());
			pstmt.setString(10,customerdetails.getAddress());
			pstmt.setString(11,customerdetails.getLicsencekey());
			pstmt.setString(12,customerdetails.getLic_expirydate());
			pstmt.setString(13,customerdetails.getCust_refno());
			pstmt.setString(14,customerdetails.getUrlname());
			pstmt.setString(15,customerdetails.getSchoolname());
			pstmt.setString(16,customerdetails.getNoofusers());
			pstmt.setString(17,customerdetails.getCustdomain());
			pstmt.setString(18,customerdetails.getNoofstudents());
			int status = pstmt.executeUpdate();
			if(status > 0){
			
				String auserid = insertAppusersDetails(customerdetails,conn);
				customerdetails.setAuserid(auserid);
				result = id;
				new Thread() {
						public void run() {
							
							String mailstatus = SendMail.sendToCustomer(customerdetails.getEmail(),customerdetails.getCustfname());
							updatemailStaus(customerdetails.getCustId(),mailstatus);
							
							//Create DB in Plesk
							System.out.println("Hello Asha - Create DB");
							String status1 = new CreateCustDB().createDBservice(customerdetails);
									        	 
							//Add tables in the created database
							System.out.println("Hello Asha - Create Table");
							if(status1!=null){
								new CreateCustDB().createCustDB(customerdetails);
							}
						}
						
				}.start();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(conn !=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CustomerDaoImpl : addCustomer Ending");
	 return result;
	}*/

	@Override
	public int addCustomer(Customer customerdetails) {
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: addCustomer Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String id = null;
		String result = null;
		int custid = 0;
		try{
			conn = DBConnection.getmasterConnection();
			// Insert into customer_details			
			pstmt = conn.prepareStatement(SQLConstants.INSERT_CUSTOMERS, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,customerdetails.getCustfname());
			pstmt.setString(2,customerdetails.getCustlname());
			pstmt.setString(3,customerdetails.getPhone());
			pstmt.setString(4,customerdetails.getEmail());
			pstmt.setString(5,customerdetails.getAddress());
			pstmt.setString(6,customerdetails.getCust_refno());
			int status = pstmt.executeUpdate();
		
			if(status > 0){
				
				ResultSet keys = pstmt.getGeneratedKeys();    
				keys.next();  
				custid = keys.getInt(1);
				customerdetails.setCustId(custid);
				new Thread() {
					public void run() {
						String mailstatus = SendMail.sendToCustomer(customerdetails.getEmail(),customerdetails.getCustfname());
						updatemailStaus(customerdetails.getCustId(),mailstatus);
						saveProductInfo(customerdetails);
					}
			}.start();
		}
	}
	catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(conn !=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
	+ " Control in CustomerDaoImpl : addCustomer Ending");
	 return custid;
	
	}
	
	public String saveProductInfo(Customer customerdetails){
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: saveProductInfo Starting");
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		String status = null;
		
		try{
			
			conn = DBConnection.getmasterConnection();
			pstmt = conn.prepareStatement(SQLConstants.INSERT_CUSTOMER_PRODUCT_DETAILS, Statement.RETURN_GENERATED_KEYS);
			
			for(Productdetails prod:customerdetails.getProductinfo()){
			
				String username = GenerateCrendentials.generateUname(customerdetails.getCustfname(), customerdetails.getCustlname(), PRODNAME);
				String pwd = String.valueOf(GenerateCrendentials.generatePwd(8));
				
				//Inserting product details
				pstmt.setInt(1,customerdetails.getCustId());
				pstmt.setString(2,prod.getSub_type());
				pstmt.setString(3,prod.getSub_start_date());
				pstmt.setString(4,prod.getCust_domain()+"."+DOMAIN_NAME);
				pstmt.setInt(5,prod.getNo_of_users());
				pstmt.setString(6,prod.getUrlname());
				pstmt.setString(7,prod.getSchoolname());
				pstmt.setInt(8,prod.getNo_of_students());
				pstmt.setString(9,prod.getLicense_key());
				pstmt.setString(10,prod.getLic_expdate());
				int proresult = pstmt.executeUpdate();
				if(proresult > 0){
					ResultSet keysub = pstmt.getGeneratedKeys();    
					keysub.next();  
					prod.setSub_id(keysub.getInt(1));
					
					//creating login info for each prod
					CustomerLoginDetails login = new CustomerLoginDetails();
					login.setAppusername(username);
					login.setApppwd(pwd);
					
					//creating db info for each product
					CustomerDB db = new CustomerDB();
					db.setDbhost(CUSTDBHOST);
					db.setDbname(prod.getUrlname());
					db.setDbpwd(pwd);
					db.setDbusername(username);
					
					customerdetails.setDbinfo(db);
					customerdetails.setProdinfo(prod);
					customerdetails.setLoginfo(login);
					
					CreateCustDB.CreateDataBase(customerdetails);
				
//					String auserid = insertAppusersDetails(customerdetails,conn);
//					customerdetails.setAuserid(auserid);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
		+ " Control in CustomerDaoImpl : addCustomer Ending");
		return null;
	}
	
	
	public String insertAppusersDetails(Customer customerdetails, Connection conn) {
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: insertAppusersDetails Starting");
			PreparedStatement pstmt = null;
			String result = null;
			try{
				String auserid = GenerateId.getPrimaryKeyID("campus_cust_userdetails");
				pstmt = conn.prepareStatement(SQLConstants.INSERT_USER_DETAILS);
				pstmt.setString(1, auserid);
				pstmt.setInt(2, customerdetails.getCustId());
				pstmt.setString(3, customerdetails.getLoginfo().getAppusername());
				pstmt.setString(4, customerdetails.getLoginfo().getApppwd());
				pstmt.setString(5, customerdetails.getEmail());
				pstmt.setString(6, customerdetails.getPhone());
				int count = pstmt.executeUpdate();
				pstmt.close();
				if(count > 0){
					result = auserid;
				}else{
					result = "fail";
				}
				
			}catch(Exception e){
			e.printStackTrace();
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl : insertAppusersDetails Ending");
			
		return result;
	}
	
	
	@Override
	public int verifyCustomer(Customer customerdetails) {
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: verifyCustomer Starting");
		
		
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1=null;
		
		ResultSet rs = null;
		int result = 0;
		int status = 0;
		try{
		
			conn = DBConnection.getmasterConnection();
			pstmt = conn.prepareStatement(SQLConstants.VERIFY_CUSTOMER);
			pstmt.setString(1,customerdetails.getCust_refno());
			pstmt.setString(2,customerdetails.getProdinfo().getUrlname());
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			while(rs.next()){
				status = rs.getInt(1);
				if(status > 0){
					result =  rs.getInt(2);
					customerdetails.setCustId(result);
					pstmt1 = conn.prepareStatement(SQLConstants.UPDATE_CUSTOMER);
					pstmt1.setString(1,customerdetails.getProdinfo().getLicense_key());
					pstmt1.setString(2,customerdetails.getProdinfo().getLic_expdate());
					pstmt1.setString(3,customerdetails.getProdinfo().getSub_type());
					pstmt1.setInt(4,customerdetails.getProdinfo().getNo_of_users());
					pstmt1.setString(5,customerdetails.getCust_refno());
					int count = pstmt1.executeUpdate();
					
					if(count > 0){
						new Thread() {
							public void run() {
								String mailstatus = SendMail.sendSerRenewMail(customerdetails.getEmail(),customerdetails.getCustfname());
								renewalMailStaus(customerdetails.getCustId(),mailstatus);
								updatelicsencePeriod(customerdetails);
							}
						}.start();
					}
				}else{
					result = addCustomer(customerdetails);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(pstmt1 != null){
					pstmt1.close();
				}
				if(conn !=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CustomerDaoImpl : verifyCustomer Ending");
		
		return result;
	}

	@Override
	public Customer getcustomerDetails(String custId) {
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: getcustomerDetails Starting");
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Customer custdetails = new Customer();
			try {
				
				conn = DBConnection.getmasterConnection();
				pstmt = conn.prepareStatement(SQLConstants.GET_CUSTOMER_DETAILS);
				pstmt.setString(1, custId);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					custdetails.getLoginfo().setAppusername(rs.getString("cust_uname"));
					custdetails.getLoginfo().setApppwd(rs.getString("cust_pwd"));
					custdetails.getProdinfo().setUrlname(DOMAIN_NAME+rs.getString("subdoamin_name"));
					custdetails.setEmail(rs.getString("cust_email"));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null) {
						rs.close();
					}
					if(pstmt!=null) {
						pstmt.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl : getcustomerDetails Ending");
			
		return custdetails;
	}

	@Override
	public Object validateCustomer(String userName, String password,String domainname) {
		 logger.setLevel(Level.ERROR);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					
					+ " Control in CustomerDaoImpl: getcustomerDetails Starting");
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement getlicde = null;
			
			ResultSet rs = null;
			ResultSet getlicders = null;
			
			CustomerInfo custdetails = new CustomerInfo();
			Productdetails prod = new Productdetails();
			int found = 0;
			int licValid =0;
			try {
				
				conn = DBConnection.getmasterConnection();
				
				//validate the user username & pwd
				pstmt = conn.prepareStatement(SQLConstants.VALIDATE_USER_DOMAIN);
				pstmt.setString(1, domainname.trim());
				rs = pstmt.executeQuery();
				System.out.println("#"+pstmt);
				while(rs.next()){
					found = rs.getInt(1);
					System.out.println("found details ####################### "+found);
					
					if(found == 0){
						custdetails.setStatus("Invalid URL");
					}
					//valid user
					else{
						custdetails.setCustId(rs.getString("cust_id"));
						custdetails.setSub_id(rs.getInt("sub_id"));
						//check lic_expiry
						getlicde = conn.prepareStatement(SQLConstants.CHECK_LIC_VALIDATY);
						getlicde.setString(1, custdetails.getCustId());
						getlicde.setInt(2, custdetails.getSub_id());
						getlicders = getlicde.executeQuery();
						System.out.println(getlicde);
						while(getlicders.next()){
							licValid = getlicders.getInt(1);
							//if valid
							if(licValid > 0){
								//check domain
									custdetails.setAuserid("all");
									custdetails.setSubtype(getlicders.getString("sub_type"));
									custdetails.setCust_refno(getlicders.getString("cust_refno"));
									custdetails.setLicsencekey(getlicders.getString("license_key"));
									custdetails.setNoofstudents(getlicders.getString("no_of_students"));
									custdetails.setUrlname(getlicders.getString("urlname"));
									custdetails.setStatus("licvalid");
									custdetails.setDomain(getlicders.getString("cust_domain"));
									CustomerDB dbdetails = getCustDBdetails(custdetails.getCustId(),custdetails.getSub_id());
									custdetails.setCustdbname(dbdetails.getDbname());
									custdetails.setCustdbpwd(dbdetails.getDbpwd());
									custdetails.setCustdbnuame(dbdetails.getDbusername());
									custdetails.setCustdbhost(dbdetails.getDbhost());
							}
							// lic_expired
							else{
								custdetails.setStatus("License Expired!!!");
							}
						}
					}
				}
			}				
			catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null) {
						rs.close();
					}
					if(pstmt!=null) {
						pstmt.close();
					}
					if(getlicde!=null) {
						getlicde.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}
				
				catch(SQLException e1){
					e1.printStackTrace();
					logger.error(e1.getMessage(), e1);
				}
				catch(Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl : getcustomerDetails Ending");
			
		return custdetails;
	}
	
	public static void updatemailStaus(int i,String mailstatus){
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: updatemailStaus Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = DBConnection.getmasterConnection();
			pstmt = conn.prepareStatement(SQLConstants.INSERT_REG_MAIL);
			pstmt.setInt(1,i);
			pstmt.setString(2,mailstatus);
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl : updatemailStaus Ending");
	}

	public static void updateActivationMail(int custid, String mailstatus, int subid) {
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: updateActivationMail Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = DBConnection.getmasterConnection();
			pstmt = conn.prepareStatement(SQLConstants.INSERT_ACTIVATION_MAIL);
			pstmt.setInt(1,custid);
			pstmt.setString(2,mailstatus);
			pstmt.setInt(3,subid);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl : updateActivationMail Ending");
	}
	
	private void renewalMailStaus(int i, String mailstatus) {
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: renewalMailStaus Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = DBConnection.getmasterConnection();
			pstmt = conn.prepareStatement(SQLConstants.INSERT_RENEWAL_MAIL);
			pstmt.setInt(1,i);
			pstmt.setString(2,mailstatus);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl : renewalMailStaus Ending");
	}
	
	// insert the details into indiviual customer db
	public static int insertUserdetails(Customer customerdetails,Connection conn) {
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: insertUserdetails Starting");
		
		PreparedStatement pstmt = null;
		int status = 0;
		try{
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(SQLConstants.INSERT_CUSTOMER);
				pstmt.setString(1,String.valueOf(customerdetails.getCustId()));
				pstmt.setString(2,customerdetails.getCustfname());
				pstmt.setString(3,customerdetails.getCustlname());
				pstmt.setString(4,customerdetails.getPhone());
				pstmt.setString(5,customerdetails.getProdinfo().getSub_type());
				pstmt.setString(6,customerdetails.getPurchasedate());
				pstmt.setString(7,customerdetails.getEmail());
				pstmt.setString(8,customerdetails.getAddress());
				pstmt.setString(9,customerdetails.getProdinfo().getLicense_key());
				pstmt.setString(10,customerdetails.getProdinfo().getLic_expdate());
				pstmt.setString(11,customerdetails.getCust_refno());
				pstmt.setString(12,customerdetails.getProdinfo().getUrlname());
				pstmt.setString(13,customerdetails.getProdinfo().getSchoolname());
				pstmt.setInt(14,customerdetails.getProdinfo().getNo_of_users());
				pstmt.setString(15,customerdetails.getProdinfo().getCust_domain());
				pstmt.setInt(16,customerdetails.getProdinfo().getNo_of_students());
				int dbstatus = pstmt.executeUpdate();
				pstmt.close();
				if(dbstatus > 0){
				
					String userid = GenerateId.getPrimaryKeyIDForUser("campus_user",conn);
					pstmt = conn.prepareStatement(SQLConstants.INSERT_USER);
					pstmt.setString(1,userid);
					pstmt.setInt(2,customerdetails.getCustId());
					pstmt.setString(3,customerdetails.getLoginfo().getAppusername());
					pstmt.setString(4,customerdetails.getLoginfo().getApppwd());
					pstmt.setString(5,StreamConstants.role);
					pstmt.setString(6,StreamConstants.type);
					pstmt.setInt(7,customerdetails.getCustId());
					pstmt.setInt(8,customerdetails.getCustId());
					pstmt.setString(9,customerdetails.getEmail());
					pstmt.setString(10,customerdetails.getPhone());
					pstmt.setInt(11,customerdetails.getCustId());
					
					int count = pstmt.executeUpdate();
					
					if(count > 0){
						conn.commit();
						status = 1;
					}else{
						conn.rollback();
						status = 0;
					}
				}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(pstmt!=null){
					pstmt.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl : insertUserdetails Ending");
			return status;
	}
	
	private void updatelicsencePeriod(Customer customerdetails) {
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: updatelicsencePeriod Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbhost = null;
		String USER = null;
		String PASS = null;
		String dbName = null;
		Connection dbconn = null;
		
		try{
			conn = DBConnection.getmasterConnection();
			pstmt = conn.prepareStatement(SQLConstants.GET_DB_DETAILS);
			pstmt.setInt(1,customerdetails.getCustId());
			pstmt.setInt(2,customerdetails.getProdinfo().getSub_id());
			rs = pstmt.executeQuery();
			while(rs.next()){
				dbhost = rs.getString("dbhost");
				USER = rs.getString("dbusername");
				PASS = rs.getString("dbpwd");
				dbName = rs.getString("dbname");
			}
			pstmt.close();
			
			//to get customerdb details and update lic details
			String DB_URL = PROTOCOL+"://"+dbhost+"/"+dbName;
			Class.forName(JDBC_DRIVER).newInstance();
			dbconn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			pstmt = dbconn.prepareStatement(SQLConstants.UPDATE_LICENSE_PERIOD);
			pstmt.setString(1,customerdetails.getProdinfo().getLicense_key());
			pstmt.setString(2,customerdetails.getProdinfo().getLic_expdate());
			pstmt.setInt(3,customerdetails.getProdinfo().getNo_of_users());
			pstmt.setInt(4,customerdetails.getProdinfo().getNo_of_students());
			pstmt.setString(5,customerdetails.getProdinfo().getSub_type());
			pstmt.setString(6,customerdetails.getProdinfo().getSub_start_date());
			pstmt.setInt(7,customerdetails.getCustId());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(pstmt!=null){
					pstmt.close();
				}
				if(dbconn!=null){
					dbconn.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl : updatelicsencePeriod Ending");
	}

	@Override
	public DomainVerification verifySubDomain(int statusid) {
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: verifySubDomain Starting");
		
			PleskApiClient client = new PleskApiClient(DOMAIN_NAME);
	        client.setCredentials(PUSERNAME, PPWD);
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		DomainVerification verifysubdomain = new DomainVerification();
		try{
			conn = DBConnection.getmasterConnection();
			pstmt = conn.prepareStatement(SQLConstants.CHECK_SUBDOMAIN_DETAILS);
			pstmt.setInt(1,statusid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				verifysubdomain.setLicstatus(rs.getString("licstatus"));
				verifysubdomain.setCutomerrefno(rs.getString("cust_refno"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl : verifySubDomain Ending");
			return verifysubdomain;
	}

	public CustomerDB getCustDBdetails(String custid, int subid) {
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl: getCustDBdetails Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		CustomerDB dbdetails = new CustomerDB();
		try{
			conn = DBConnection.getmasterConnection();
			pstmt = conn.prepareStatement(SQLConstants.GET_DB_DETAILS);
			pstmt.setString(1,custid);
			pstmt.setInt(2,subid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				dbdetails.setDbname(rs.getString("dbname"));
				dbdetails.setDbusername(rs.getString("dbusername"));
				dbdetails.setDbpwd(rs.getString("dbpwd"));
				dbdetails.setDbhost(rs.getString("dbhost"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl : getCustDBdetails Ending");
			return dbdetails;
	}
	
	public Object validateCustomer1(String userName, String password,String domainname) {
		 logger.setLevel(Level.ERROR);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					
					+ " Control in CustomerDaoImpl: getcustomerDetails Starting");
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement getlicde = null;
			
			ResultSet rs = null;
			ResultSet getlicders = null;
			
			CustomerInfo custdetails = new CustomerInfo();
			Productdetails prod = new Productdetails();
			int found = 0;
			int licValid =0;
			try {
				
				conn = DBConnection.getmasterConnection();
				
				//validate the user username & pwd
				pstmt = conn.prepareStatement(SQLConstants.VALIDATE_APP_USER);
				pstmt.setString(1, userName.trim());
				pstmt.setString(2, password.trim());
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				while(rs.next()){
					found = rs.getInt(1);
					if(found == 0){
						custdetails.setStatus("Enter valid username and password");
					}
					//valid user
					else{
						custdetails.setCustId(rs.getString("cust_id"));
						custdetails.setSub_id(rs.getInt("sub_id"));
						//check lic_expiry
						getlicde = conn.prepareStatement(SQLConstants.CHECK_LIC_VALIDATY);
						getlicde.setString(1, custdetails.getCustId());
						getlicde.setInt(2, custdetails.getSub_id());
						getlicders = getlicde.executeQuery();
						System.out.println(getlicde);
						while(getlicders.next()){
							licValid = getlicders.getInt(1);
							//if valid
							if(licValid > 0){
								//check domain
								if(getlicders.getString("cust_domain").equalsIgnoreCase(domainname)){
									custdetails.setAuserid(rs.getString("user_id"));
									custdetails.setSubtype(getlicders.getString("sub_type"));
									custdetails.setCust_refno(getlicders.getString("cust_refno"));
									custdetails.setLicsencekey(getlicders.getString("license_key"));
									custdetails.setNoofstudents(getlicders.getString("no_of_students"));
									custdetails.setUrlname(getlicders.getString("urlname"));
									custdetails.setStatus("licvalid");
									custdetails.setDomain(getlicders.getString("cust_domain"));
									CustomerDB dbdetails = getCustDBdetails(custdetails.getCustId(),custdetails.getSub_id());
									custdetails.setCustdbname(dbdetails.getDbname());
									custdetails.setCustdbpwd(dbdetails.getDbpwd());
									custdetails.setCustdbnuame(dbdetails.getDbusername());
									custdetails.setCustdbhost(dbdetails.getDbhost());
								}else{
									custdetails.setStatus("Invalid URL");
								}
							}
							// lic_expired
							else{
								custdetails.setStatus("License Expired!!!");
							}
						}
					}
				}
			}				
			catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null) {
						rs.close();
					}
					if(pstmt!=null) {
						pstmt.close();
					}
					if(getlicde!=null) {
						getlicde.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}
				
				catch(SQLException e1){
					e1.printStackTrace();
					logger.error(e1.getMessage(), e1);
				}
				catch(Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerDaoImpl : getcustomerDetails Ending");
			
		return custdetails;
	}
	
	
	
	
}
