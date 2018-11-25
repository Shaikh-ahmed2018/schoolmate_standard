package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.StreamDetailsVO;


public interface StreamDetailsDao {

	
	public int insertStreamDetailsDao(StreamDetailsPojo detailsPojo, UserLoggingsPojo userLoggingsVo);
	public List<StreamDetailsPojo> getStreamDetailsDao(String schoolLocation,UserLoggingsPojo custdetails);
	public StreamDetailsVO editStreamDetailsDao(StreamDetailsVO detailsVo, UserLoggingsPojo custdetails);
	public String deleteStreamDetailsDao(StreamDetailsVO detailsVo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<StreamDetailsVO> searchStreamDetailsDao(
			String searchTerm, UserLoggingsPojo custdetails);
	public String validateStreamNameDao(StreamDetailsVO detailsVo, UserLoggingsPojo userLoggingsVo);
	public int updateStreamDetailsDao(StreamDetailsPojo detailsPojo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<StreamDetailsVO> searchByLocationOnly(String locationId,String accYear,String status, UserLoggingsPojo custdetails);
}
