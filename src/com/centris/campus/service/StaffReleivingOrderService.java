package com.centris.campus.service;
import java.util.List;
import com.centris.campus.pojo.RelievingOrderPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReleivingOrderVo;
import com.centris.campus.vo.UserDetailVO;

public interface StaffReleivingOrderService {

	List<AllTeacherDetailsVo> getTeachingListService(AllTeacherDetailsVo vo,UserLoggingsPojo userLoggingsVo);

	List<AllTeacherDetailsVo> getNonTeachingListService(AllTeacherDetailsVo vo,UserLoggingsPojo userLoggingsVo);

	List<UserDetailVO> getUsersListService(UserLoggingsPojo userLoggingsVo);

	List<ReleivingOrderVo> getReleivingDetailsService(RelievingOrderPojo pojo,UserLoggingsPojo userLoggingsVo);

	List<RelievingOrderPojo> SearchRelievingOrder(RelievingOrderPojo pojo, UserLoggingsPojo custdetails);

	List<RelievingOrderPojo> staffReleivingPDFReport(String[] teachercode, RelievingOrderPojo pojo, UserLoggingsPojo userLoggingsVo);

}
