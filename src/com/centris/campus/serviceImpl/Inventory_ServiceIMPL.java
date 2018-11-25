package com.centris.campus.serviceImpl;

import java.util.List;



import com.centris.campus.dao.Inventory_DAO;
import com.centris.campus.daoImpl.Inventory_DAOIMPL;
import com.centris.campus.forms.InventoryForm;
import com.centris.campus.forms.InventoryTransactionForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.Inventory_Service;
import com.centris.campus.vo.AddorModifyorDeleteVO;
import com.centris.campus.vo.InventoryTransactionVO;
import com.centris.campus.vo.InventoryVO;

public class Inventory_ServiceIMPL implements Inventory_Service {
	
	Inventory_DAO dao = new Inventory_DAOIMPL();


	public List<InventoryVO> InventoryTypesList(UserLoggingsPojo custdetails) {

		return dao.InventoryTypesList(custdetails);
	}


	public List<InventoryVO> searchInventoryTypeList(String searchName,UserLoggingsPojo userLoggingsVo) {

		return dao.searchInventoryTypeList(searchName,userLoggingsVo);
	}


	public String AddInventoryType(InventoryForm form) {
		

		return dao.AddInventoryType(form);
	}


	public InventoryVO EditInventoryType(InventoryVO vo) {

		return dao.EditInventoryType(vo);
	}


	public String DeleteInventoryType(InventoryVO vo) {

		return dao.DeleteInventoryType(vo);
	}

	//Add or Modify or Delete
	public List<AddorModifyorDeleteVO> AddorModifyorDeleteList(UserLoggingsPojo userLoggingsVo) {

		return dao.AddorModifyorDeleteList(userLoggingsVo);
	}

	public String CreatingAddorModifyorDelete(InventoryForm form) {

		return dao.CreatingAddorModifyorDelete(form);
	}


	public AddorModifyorDeleteVO EditAddorModifyorDelete(AddorModifyorDeleteVO vo) {

		return dao.EditAddorModifyorDelete(vo);
	}


	public String DeleteAddorModifyorDelete(AddorModifyorDeleteVO vo) {

		return dao.DeleteAddorModifyorDelete(vo);
	}


	public List<AddorModifyorDeleteVO> SearchAddorModifyorDeleteList(
			String searchName,UserLoggingsPojo userLoggingsVo) {

		return dao.SearchAddorModifyorDeleteList(searchName,userLoggingsVo);
	}


	@Override
	public List<AddorModifyorDeleteVO> InventoryList(String custid) {

		return dao.InventoryList();
	}


	@Override
	public List<AddorModifyorDeleteVO> SearchInventoryList(String searchName,UserLoggingsPojo userLoggingsVo) {
		 
		return dao.SearchInventoryList(searchName,userLoggingsVo);
	}


	@Override
	public List<InventoryTransactionVO> InventoryTransactionList() {
		// TODO Auto-generated method stub
		return dao.InventoryTransactionList();
	}	
	
	
	
	public String CreateTransactionDetails(InventoryTransactionForm form ,String tid) {

		return dao.CreateTransactionDetails(form,tid);
	}


	@Override
	public String deleteInventoryTransaction(String id) {
		return dao.deleteInventoryTransaction(id);
	}
	
	public List<AddorModifyorDeleteVO> singleItemDetails(String id) {
		return dao.singleItemDetails(id);
	}
	
	public List<AddorModifyorDeleteVO> returnInventoryItem(String id) {
		return dao.returnInventoryItem(id);
	}
	
	public String updateReturnItem(InventoryTransactionForm form ,String tid) {

		return dao.updateReturnItem(form,tid);
	}
	public String getAvailableQuantity(String id, String issued) {
		return dao.getAvailableQuantity(id,issued);
	}
	
	public List<InventoryTransactionVO> usageReportList(InventoryTransactionForm invenTranForm,UserLoggingsPojo userLoggingsVo) {
		return dao.usageReportList(invenTranForm,userLoggingsVo);
	}
	public List<InventoryVO> getItemtypeByDepartmnet(String department) {
		return dao.getItemtypeByDepartmnet(department);
	}
	
	public List<InventoryTransactionVO> getNotReturnedReportAction(InventoryTransactionForm invenTranForm) {
		return dao.getNotReturnedReportAction(invenTranForm);
	}
	public List<InventoryTransactionVO> getItemnameByItemtype(String item_type) {
		return dao.getItemnameByItemtype(item_type);
	}
	
	
}
