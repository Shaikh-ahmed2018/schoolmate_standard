package com.centris.campus.delegate;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.centris.campus.forms.InventoryForm;
import com.centris.campus.forms.InventoryTransactionForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.Inventory_Service;
import com.centris.campus.serviceImpl.Inventory_ServiceIMPL;
import com.centris.campus.vo.AddorModifyorDeleteVO;
import com.centris.campus.vo.InventoryTransactionVO;
import com.centris.campus.vo.InventoryVO;

public class Inventory_Delegate {
	
	Inventory_Service service = new Inventory_ServiceIMPL();

	public List<InventoryVO> InventoryTypesList(UserLoggingsPojo custdetails) {
		 
		return service.InventoryTypesList(custdetails);
	}

	public List<InventoryVO> searchInventoryTypeList(String searchName,UserLoggingsPojo userLoggingsVo) {
		 
		return service.searchInventoryTypeList(searchName,userLoggingsVo);
	}

	public String AddInventoryType(InventoryForm form) {
		 
		return service.AddInventoryType(form);
	}

	public InventoryVO EditInventoryType(InventoryVO vo) {
		 
		return service.EditInventoryType(vo);
	}

	public String DeleteInventoryType(InventoryVO vo) {
		 
		return service.DeleteInventoryType(vo);
	}

	public List<AddorModifyorDeleteVO> AddorModifyorDeleteList(UserLoggingsPojo userLoggingsVo) {
		 
		return service.AddorModifyorDeleteList(userLoggingsVo);
	}
	
//Add or Modify or Delete

	public String CreatingAddorModifyorDelete(InventoryForm form) {
		 
		return service.CreatingAddorModifyorDelete(form);
	}

	public AddorModifyorDeleteVO EditAddorModifyorDelete(AddorModifyorDeleteVO vo) {
		 
		return service.EditAddorModifyorDelete(vo);
	}

	public String DeleteAddorModifyorDelete(AddorModifyorDeleteVO vo) {
		 
		return service.DeleteAddorModifyorDelete(vo);
	}

	public List<AddorModifyorDeleteVO> SearchAddorModifyorDeleteList(
			String searchName, UserLoggingsPojo userLoggingsVo) {
		 
		return service.SearchAddorModifyorDeleteList(searchName,userLoggingsVo);
	}

	public List<AddorModifyorDeleteVO> InventoryList(String custid) {
		 
		return service.InventoryList(custid);
	}

	public List<AddorModifyorDeleteVO> SearchInventoryList(String searchName,UserLoggingsPojo userLoggingsVo) {
		 
		return service.SearchInventoryList(searchName,userLoggingsVo);
	}

	public List<InventoryTransactionVO> InventoryTransactionList() {
		 
		return service.InventoryTransactionList();	}

	
	public String CreateTransactionDetails(InventoryTransactionForm form, String tid) {
		return service.CreateTransactionDetails(form,tid);
	}

	
	public String deleteInventoryTransaction(String id){
		return service.deleteInventoryTransaction(id);
	}

	public List<AddorModifyorDeleteVO> singleItemDetails(String id) {
		return service.singleItemDetails(id);
	}

	public List<AddorModifyorDeleteVO> returnInventoryItem(String id) {
		return service.returnInventoryItem(id);
	}

	public String updateReturnItem(InventoryTransactionForm form, String tid) {
		return service.updateReturnItem(form,tid);
	}

	

	public String getAvailableQuantity(String id,String issued ) {
		return service.getAvailableQuantity(id, issued);
	}

	public List<InventoryTransactionVO> usageReportList(InventoryTransactionForm invenTranForm,UserLoggingsPojo userLoggingsVo) {
		return service.usageReportList(invenTranForm,userLoggingsVo);
	}

	public List<InventoryVO> getItemtypeByDepartmnet(String department) {
		return service.getItemtypeByDepartmnet(department);
	}

	public List<InventoryTransactionVO> getNotReturnedReportAction(InventoryTransactionForm invenTranForm) {
		return service.getNotReturnedReportAction(invenTranForm);
	}

	public List<InventoryTransactionVO> getItemnameByItemtype(String item_type) {
		return service.getItemnameByItemtype(item_type);
	}

		
}
