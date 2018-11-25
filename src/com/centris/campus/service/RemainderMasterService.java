package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.RemainderMasterVO;

public interface RemainderMasterService {

	boolean getnamecount(RemainderMasterVO vo);

	String addremainderdata(RemainderMasterVO vo);

	ArrayList<RemainderMasterVO> remainderdetails(RemainderMasterVO vo,UserLoggingsPojo userLoggingsVo);

	RemainderMasterVO editremainderDetails(RemainderMasterVO vo);

	String deleteRemainderDetails(RemainderMasterVO vo);

}
