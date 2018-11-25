package com.centris.campus.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.joda.time.LocalDate;

import com.centris.campus.daoImpl.JDBCConnection;

public class SecurityCheckSum {

	public static void main(String args[]){
		
	
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<SecuritySelector> records = new ArrayList<SecuritySelector>();
		int option1Length = 0;
		int option2Length = 0;
		int option3Length = 0;
		int option4Length = 0;
		try{
			
			//creating the checksum for past 7days transactions
			
			// Get the current date
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();  
			System.out.println(dateFormat.format(calendar.getTime()));
			
			//to get the last 7th day from current day
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			System.out.println("Date = "+ dateFormat.format(cal.getTime()));
			
			//calculate date between two dates
			LocalDate dateStart = new LocalDate(cal.getTime());
			LocalDate dateEnd = new LocalDate(calendar.getTime());
			List<LocalDate> dates = new ArrayList<LocalDate>();
			// day by day:
			while(dateStart.isBefore(dateEnd)){
			    dates.add(dateStart);
			    dateStart = dateStart.plusDays(1);
			}
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("SELECT module_name,sub_module_name,activity,activity_sql FROM log_activity WHERE action_time > CAST(? AS DATE) OR action_time < CAST(? AS DATE)");
			pstmt.setString(1,dateFormat.format(cal.getTime())+"%%");
			pstmt.setString(2,dateFormat.format(calendar.getTime())+"%%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				SecuritySelector selector = new SecuritySelector();
				selector.setOption1(rs.getString("module_name"));
				selector.setOption2(rs.getString("sub_module_name"));
				selector.setOption3(rs.getString("activity"));
				selector.setOption4(rs.getString("activity_sql"));
				records.add(selector);
			}
			
			for(SecuritySelector record:records){
				option1Length = option1Length + record.getOption1().length();
				option2Length = option2Length + record.getOption2().length();
				option3Length = option3Length + record.getOption3().length();
				option4Length = option4Length + record.getOption4().length();
			}
			System.out.println(option1Length);
			System.out.println(Integer.toBinaryString(option1Length));
			
			System.out.println(Integer.toBinaryString(~option1Length));
			System.out.println(Integer.toBinaryString(option2Length));
			System.out.println(Integer.toBinaryString(option3Length));
			System.out.println(Integer.toBinaryString(option4Length));
			
			 int bits = (int) Long.parseLong((Integer.toBinaryString(~option1Length)),2);
		     System.out.println(bits);
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			}
			catch(SQLException sql){
				sql.printStackTrace();
			}
		}
		
	}
}
