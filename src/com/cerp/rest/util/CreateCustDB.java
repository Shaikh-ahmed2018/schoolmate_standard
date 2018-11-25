/**
 * @author Asha Mestha
 * 15/03/2018
 * 
 */

package com.cerp.rest.util;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.cerp.rest.dao.CustomerDaoImpl;
import com.cerp.rest.model.Customer;
import com.cerp.rest.model.CustomerDB;
import com.cerp.rest.model.Productdetails;

public class CreateCustDB extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CreateCustDB.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	static final String JDBC_DRIVER = res.getString("JDBC_DRIVER");  
	static final String MDBUSER = res.getString("username"); 
	static final String MDBPWD = res.getString("JDBC_DRIVER"); 
	static final String MDBIP = res.getString("password"); 
	
	static final String PROTOCOL = res.getString("protocal"); 
	
	static final String PUSERNAME = res.getString("pusername"); 
	static final String PPWD = res.getString("ppassword"); 
	
	static final String DOMAIN_NAME = res.getString("domain_name"); 
	static final String WEB_SPACE_ID = res.getString("domain_webspace_id"); 
	
	
	public static  String createDB(Customer obj){
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CreateCustDB: createDB Starting");
			
			Connection conn = null;
			String status = null;
			String tablestatus = "fail";
			String tabledata = null;
			Statement stmt = null;
			String stprocedures = null;
			
			try {
				
				//testing purpose in local
				String DB_URL = "jdbc:mysql://localhost/";
				String USER = "root";
				String PASS = "root";
//				
				//For production purpose
//				String DB_URL = PROTOCOL+"://"+customerdetails.getDbinfo().getDbhost()+"/"+customerdetails.getDbinfo().getDbname();
//				String USER = customerdetails.getDbinfo().getDbusername().trim();
//				String PASS = customerdetails.getDbinfo().getDbpwd().trim();
				
				System.out.println(DB_URL+USER+PASS);

				Class.forName(JDBC_DRIVER).newInstance();
				conn = DriverManager.getConnection(DB_URL+"?user="+USER+"&password="+PASS);

//				development purpose in local
				stmt = conn.createStatement();
				int myResult = stmt.executeUpdate("CREATE DATABASE"+" "+obj.getProdinfo().getUrlname());
				if(myResult > 0){

					stmt.execute("USE"+" "+obj.getProdinfo().getUrlname());
//	
					status = new CreateCustDB().importSQL(conn,"eCampus.sql"); //to import table structure
				
					if(status.equals("success")){
						stprocedures = new CreateCustDB().importSQL(conn,"proceandEvents.sql"); // to import stored procedures and other
						tabledata = new CreateCustDB().importSQL(conn,"defaultinfo.sql"); // to import default info like caste,roles,permissions etc
					}
				
					if(tabledata.equals("success")){
						int result = CustomerDaoImpl.insertUserdetails(obj, conn);
						if(result >0){
							tablestatus = "success";
						}
					}
		}
//
			}
			catch(SQLException se){
			      se.printStackTrace();
			}catch(Exception e){
			      e.printStackTrace();
			}finally{
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			}
		
		JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
					+ " Control in CreateCustDB : createDB Ending");
		return tablestatus;
	}

	public String importSQL(Connection conn,String fileName)  throws SQLException, IOException{
		
		InputStream is = null;
		try{
			
			is = getClass().getResourceAsStream("/com/cerp/campus/dbfiles/"+fileName);
			
			Reader reader = new BufferedReader(new InputStreamReader(is));
			ScriptRunner sr = new ScriptRunner(conn, false, false);
			sr.runScript(reader);
			reader.close();
			is.close();
		}
		catch (Exception e) {
			
			System.err.println("Failed to Execute"+" "+ is
					+ " The error is " + e.getMessage());
			return "fail";
		}
		return "success";
	}
	
	public String createDBservice(Customer customerdetails) {
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CreateCustDB: createDBservice Starting");
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement insertsubdomain = null;
			PreparedStatement insertcustdbdetails = null;
			ResultSet rs =null;
			String status = null;
			
			try{
				conn = DBConnection.getmasterConnection();
				
				// can br used for future reference
				/*pstmt = conn.prepareStatement(SQLConstants.GET_DOMAIN_DETAILS);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				while(rs.next()){
					domainname = rs.getString("domain_name").trim();
					webspaceid = rs.getInt("domain_webspace_id");
				}*/
				
				PleskApiClient client = new PleskApiClient(DOMAIN_NAME);
		        client.setCredentials(PUSERNAME, PPWD);
		        
		        //Create Subdomain for customer
		        String url = "/"+customerdetails.getProdinfo().getUrlname();
		        String createsubdomain = "<packet>"+
        				"<subdomain>"+
        				"<add>"+
        				"<parent>"+ DOMAIN_NAME +"</parent>"+
        				"<name>"+ customerdetails.getProdinfo().getUrlname() +"</name>"+ 
        				"<property>"+
        				"<name>www_root</name>"+
        				"<value>"+url+"</value>"+
        				"</property>"+
        				"<property>"+
        				"<name>ssi</name>"+
        				"<value>false</value>"+
        				"</property>"+
        				"<property>"+
        				"<name>ssi_html</name>"+
        				"<value>true</value>"+
        				"</property>"+
        				"</add>"+
        				"</subdomain>"+
        				"</packet>";
				
		        String response = client.request(createsubdomain);
		    	System.out.println(response);
				int subid = new DomParserExample().getsubdoaminstatus(response);
		        
		        //Create DB for customer in plesk
		        String createDb = "<packet>"+
		    	        "<database>"+
		    	        "<add-db>"+
		    	           "<webspace-id>"+ WEB_SPACE_ID +"</webspace-id>"+
		    	           "<name>"+ customerdetails.getDbinfo().getDbname() +"</name>"+ 
		    	           "<type>mysql</type>"+
		    	        "</add-db>"+
		    	        "</database>"+
		    	        "</packet>";
		        String dbresponse = client.request(createDb);
		        System.out.println(dbresponse);
		        int dbid = new DomParserExample().getdbid(dbresponse);
		       
		        //Create DB User
		        String createDbUser = "<packet>"+
		        					  "<database>"+
		        					  "<add-db-user><db-id>"+dbid+"</db-id>"+
		        					  "<login>"+customerdetails.getDbinfo().getDbusername().trim()+"</login>"+
		        					  "<password>"+customerdetails.getDbinfo().getDbpwd().trim()+"</password>"+
		        					  "<role>readWrite</role>"+
		        					  "</add-db-user>"+
		        					  "</database>"+
		        					  "</packet>";
		        String userdbresponse = client.request(createDbUser);
		       
		        //System.out.println(createDbUser);
		        System.out.println(userdbresponse);
		        
		        int userid = new DomParserExample().getdbuserid(userdbresponse);
		        
		        System.out.println(subid+"\n"+dbid+"\n"+userid);
		        
		        //inserting details into sub domain table 
		        insertsubdomain = conn.prepareStatement(SQLConstants.INSERT_SUBDOMAIN_DETAILS);
		        if(subid > 0) {
		        insertsubdomain.setInt(1, customerdetails.getCustId());
		        insertsubdomain.setInt(2, subid);
		        insertsubdomain.setString(3, customerdetails.getProdinfo().getUrlname());
		        insertsubdomain.setString(4, url);
		        insertsubdomain.setString(5, "Y");
		        insertsubdomain.setString(6, "Created");
		        insertsubdomain.setInt(7, customerdetails.getProdinfo().getSub_id());
		        insertsubdomain.executeUpdate();
		        status = "OK";
		        }else{
				        insertsubdomain.setInt(1, customerdetails.getCustId());
				        insertsubdomain.setInt(2, subid);
				        insertsubdomain.setString(3, customerdetails.getProdinfo().getUrlname());
				        insertsubdomain.setString(4, url);
				        insertsubdomain.setString(5, "N");
				        insertsubdomain.setString(6, "Pending");
				        insertsubdomain.setInt(7, customerdetails.getProdinfo().getSub_id());
				        insertsubdomain.executeUpdate();
				        
				        status = "Pending";
		        }
		        System.out.println(insertsubdomain);
		        //inserting details into customer DB details
		        insertcustdbdetails = conn.prepareStatement(SQLConstants.INSERT_CUSTOMER_DBDETAILS);
		        if(dbid > 0 && userid > 0 && status.equals("OK")) {
		        insertcustdbdetails.setInt(1, dbid);
		        insertcustdbdetails.setInt(2, customerdetails.getCustId());
		        insertcustdbdetails.setString(3, customerdetails.getDbinfo().getDbname());
		        insertcustdbdetails.setString(4, customerdetails.getDbinfo().getDbusername());
		        insertcustdbdetails.setString(5, customerdetails.getDbinfo().getDbpwd());
		        insertcustdbdetails.setString(6, customerdetails.getDbinfo().getDbhost());
		        insertcustdbdetails.setString(7, "Y");
		        insertcustdbdetails.setString(8, "Created");
		        insertcustdbdetails.setInt(9, userid);
		        insertcustdbdetails.setInt(10, customerdetails.getProdinfo().getSub_id());
		        insertcustdbdetails.executeUpdate();
		        status = "OK";
		        }
		        else{
			        insertcustdbdetails.setInt(1, dbid);
			        insertcustdbdetails.setInt(2, customerdetails.getCustId());
			        insertcustdbdetails.setString(3, customerdetails.getDbinfo().getDbname());
			        insertcustdbdetails.setString(4, customerdetails.getDbinfo().getDbusername());
			        insertcustdbdetails.setString(5, customerdetails.getDbinfo().getDbpwd());
			        insertcustdbdetails.setString(6, customerdetails.getDbinfo().getDbhost());
			        insertcustdbdetails.setString(7, "N");
			        insertcustdbdetails.setString(8, "Pending");
			        insertcustdbdetails.setInt(9, userid);
			        insertcustdbdetails.setInt(10, customerdetails.getProdinfo().getSub_id());
			        insertcustdbdetails.executeUpdate();
			        status = "Pending";
		        }
		        System.out.println(insertcustdbdetails);
		        
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					if(rs!=null){
						rs.close();
					}
					if(pstmt!=null){
						pstmt.close();
					}
					if(insertsubdomain!=null){
						insertsubdomain.close();
					}
					if(insertcustdbdetails!=null){
						insertcustdbdetails.close();
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
					+ " Control in CreateCustDB : createDBservice Ending");
		return status;
	}
	
	 public void createCustDB(Customer custinfo){
		 
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CreateCustDB: createCustDB Starting");
		try{
			 String status = createDB(custinfo);
			 if(status.equals("success")) {
				 new Thread() {
						public void run(){
							
							if(custinfo.getProdinfo().getSub_type().equalsIgnoreCase(StreamConstants.PROTYPEB)){
								custinfo.getProdinfo().setSub_type(StreamConstants.BASIC);
							}else if(custinfo.getProdinfo().getSub_type().equalsIgnoreCase(StreamConstants.PROTYPES)){
								custinfo.getProdinfo().setSub_type(StreamConstants.STD);
							}else if(custinfo.getProdinfo().getSub_type().equalsIgnoreCase(StreamConstants.PROTYPEE)){
								custinfo.getProdinfo().setSub_type(StreamConstants.ENTERPRISE);
							}
							
							String mailstatus = SendMail.sendConfirmToCustomer(DOMAIN_NAME,custinfo);
							CustomerDaoImpl.updateActivationMail(custinfo.getCustId(),mailstatus,custinfo.getProdinfo().getSub_id());
						}
					}.start();
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
					+ " Control in CreateCustDB : createCustDB Ending");
		 
	 }
	 
	 public static String CreateDataBase(Customer customerdetails){
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CreateCustDB: createCustDB Starting");
		 try{
				 	//Create Subdomain,DB,DB User in Plesk
					System.out.println("Hello Asha - Create DB");
					String status1 = new CreateCustDB().createDBservice(customerdetails);
							        	 
					//Add tables in the created database
					System.out.println("Hello Asha - Create Table");
					if(status1!=null){
						new CreateCustDB().createCustDB(customerdetails);
					}
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
					+ " Control in CreateCustDB : createCustDB Ending");
		 return null;
	 }
	
}
