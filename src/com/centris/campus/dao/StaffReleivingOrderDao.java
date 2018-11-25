package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.pojo.RelievingOrderPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReleivingOrderVo;
import com.centris.campus.vo.UserDetailVO;

public interface StaffReleivingOrderDao {

	List<AllTeacherDetailsVo> getTeachingListDaoImpl(AllTeacherDetailsVo vo,UserLoggingsPojo userLoggingsVo);

	List<AllTeacherDetailsVo> getNonTeachingListDaoImpl(AllTeacherDetailsVo vo,UserLoggingsPojo userLoggingsVo);

	List<UserDetailVO> getUsersListDaoImpl(UserLoggingsPojo userLoggingsVo);

	List<ReleivingOrderVo> getReleivingDetailsDaoImpl(RelievingOrderPojo pojo,UserLoggingsPojo userLoggingsVo);

	List<RelievingOrderPojo> SearchRelievingOrder(RelievingOrderPojo pojo, UserLoggingsPojo custdetails);

	List<RelievingOrderPojo> staffReleivingPDFReport(String[] teachercode, RelievingOrderPojo pojo, UserLoggingsPojo userLoggingsVo);

}
