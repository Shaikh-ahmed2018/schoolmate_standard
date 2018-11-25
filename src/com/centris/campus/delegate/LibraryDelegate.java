package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LibraryStockEntryDetailsForm;
import com.centris.campus.pojo.LibraryItems;
import com.centris.campus.pojo.LibraryLocationPojo;
import com.centris.campus.pojo.LibrarySubsciberDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.LibraryService;
import com.centris.campus.service.ReportsMenuService;
import com.centris.campus.serviceImpl.LibraryServiceImpl;
import com.centris.campus.serviceImpl.ReportsMenuServiceImpl;
import com.centris.campus.serviceImpl.StudentRegistrationServiceImpl;
import com.centris.campus.vo.CategoryTypeVO;
import com.centris.campus.vo.LibraryJournalSubscriptionVo;
import com.centris.campus.vo.LibraryLocationVO;
import com.centris.campus.vo.LibrarySearchIssueDetailsVO;
import com.centris.campus.vo.LibrarySearchSubscriberVO;
import com.centris.campus.vo.LibraryStockEntryVO;
import com.centris.campus.vo.LibrarySubscribVO;
import com.centris.campus.vo.LibraryVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StockEntryVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.SubCategoryType1VO;
import com.centris.campus.vo.SubCategoryType2VO;
import com.centris.campus.vo.SubCategoryTypeVO;
import com.centris.campus.vo.TeacherVo;

public class LibraryDelegate {

	static LibraryService service;
	static {
		service=new LibraryServiceImpl();
	}

	public String insertCategoryTypeDetail(CategoryTypeVO insert_categoryType,UserLoggingsPojo custdetail,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.insertCategoryTypeDetail(insert_categoryType, custdetails);
	}

	public List<CategoryTypeVO> getCategoryDetails(UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getCategoryDetails(custdetails);
	}

	public List<CategoryTypeVO> getSubCategoryDetails(UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryDetails(custdetails);
	}
	
	public List<CategoryTypeVO> getSubCategoryDetails1(UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryDetails1(custdetails);
	}
	
	public List<CategoryTypeVO> getSubCategoryDetails(String cattype,String status,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryDetails(cattype,status, custdetails);
	}
	
	public List<StudentRegistrationVo> studentSearchbyadmissionNo(StudentRegistrationVo registrationVo, String locid,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.studentSearchbyadmissionNo(registrationVo,locid, custdetails);
	}

	public List<TeacherVo> teacherSearchbyId(TeacherVo TeacherVo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.teacherSearchbyId(TeacherVo, custdetails);
	}

	public CategoryTypeVO editCategoryType(String id,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.editCategoryType(id, custdetails);
	}

	public SubCategoryTypeVO editSubCategoryType(String id,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.editSubCategoryType(id, custdetails);
	}

	public String inactiveCategoryType(String[] catIdlist, String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.inactiveCategoryType(catIdlist,log_audit_session, custdetails);
	}
	
	public String inactiveSubCategoryType(String[] id, SubCategoryTypeVO vo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
	return service.inactiveSubCategoryType(id,vo, custdetails);
		
	}

	public String insertSubCategoryTypeDetail(SubCategoryTypeVO insert_SubcategoryType,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.insertSubCategoryTypeDetail(insert_SubcategoryType, custdetails);
	}

	public String insertSubCategoryType1Detail(SubCategoryType1VO sub1,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.insertSubCategoryType1Detail(sub1, custdetails);
	}

	public List<SubCategoryType1VO> getSubCategoryType1Details(UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryType1Details(custdetails);
	}

	public SubCategoryType1VO editSubCategoryType1(String id,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.editSubCategoryType1(id, custdetails);
	}

	public List<SubCategoryTypeVO> getSubCategoryByCategory(String categoryCode,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryByCategory(categoryCode, custdetails);
	}

	public ArrayList<LibrarySubscribVO> getStaffData(String staffid,String locId,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getStaffData(staffid,locId,custdetails);
	}


	public ArrayList<LibrarySubscribVO> getStudentData(String academicYear,String admissionNo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getStudentData(academicYear, admissionNo,custdetails);
	}


	public String saveSubscriberDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().saveSubscriberDetails(pojo,custdetails);
	}

	public List<StudentRegistrationVo> StudentList(
			StudentRegistrationVo registrationVo,String locid,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.studentSearchbyadmissionNo(registrationVo,locid, custdetails);
	}
	public ArrayList<LibrarySearchIssueDetailsVO> getStudentIssuedList(String locid, String accyear,UserLoggingsPojo custdetails) {
		return service.getStudentIssuedList(locid,accyear, custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentListDetails(String academic_year, String location, String select, String classname,
			String sectionid,UserLoggingsPojo custdetails, String searchValue) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStudentListDetails(academic_year,location,select,classname,sectionid, custdetails,searchValue);
	}

	public String inactiveSubCategoryType1(String[] id,String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.inactiveSubCategoryType1(id,log_audit_session, custdetails);

	}

	public boolean validateSubcategoryType1(SubCategoryType1VO sub1,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.validateSubcategoryType1(sub1, custdetails);
	}

	public ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentClassList(
			String locid, String accyear, String classname,UserLoggingsPojo custdetails) {

		return service.getIssueStudentClassList(locid, accyear,classname,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentListByClassName(String academic_year, String location,
			String classname, String sectionid, String select,UserLoggingsPojo custdetails, String searchValue) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStudentListByClassName(academic_year,location,classname,sectionid,select,custdetails,searchValue);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentListBySection(String academic_year, String location,
			String classname, String sectionid, String select,UserLoggingsPojo custdetails, String searchValue) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStudentListBySection(academic_year,location,classname,sectionid,select,custdetails,searchValue);
	}

	public java.util.List<LibrarySearchSubscriberVO> searchsubscriberList(String searchTextVal, String academic_year, String location,UserLoggingsPojo custdetails) {
		/*LibraryService service=new LibraryServiceImpl();*/
		return new LibraryServiceImpl().searchsubscriberList(searchTextVal,academic_year,location,custdetails);
	}

	

	public java.util.List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByEndsWith(String searchTextVal, String location, String select,String academic_year,
			String classname,String sectionid,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().SearchSubscriberDetailsByEndsWith(searchTextVal,location,select,academic_year,classname,sectionid,custdetails);
	}

	public ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentSectionList(String locid, String accyear, String classname, String sectionnm,UserLoggingsPojo custdetails) {
		return service.getIssueStudentSectionList(locid, accyear,classname,sectionnm,custdetails);
	}

	public String insertLibraryLocations(LibraryLocationPojo insert_libLoc,UserLoggingsPojo custdetails) {
		return service.insertLibraryLocations(insert_libLoc,custdetails);
	}


	public ArrayList<LibrarySubscribVO> getSubscriberDetailsListPage(String academicYear, String locId, String classId,String sectionName, String suscriberType, String department, String designation, String otherName,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getSubscriberDetailsListPage( academicYear,  locId,  classId, sectionName,  suscriberType,department,designation,otherName,custdetails);
	}


	public LibraryLocationVO editLibraryLocation(String id,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.editLibraryLocation(id,custdetails);
	}


	public String updateLibLocations(LibraryLocationPojo insert_libLoc,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.updateLibLocations(insert_libLoc,custdetails);
	}

	public java.util.List<LibrarySearchIssueDetailsVO> getIssueDetailsByAnyWhere(String searchTextVal, String locid, String accyear,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getIssueDetailsByAnyWhere(searchTextVal,locid,accyear,custdetails);
	}
	public ArrayList<LibraryLocationPojo> getLibLocationsDetails(String location, UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getLibLocationsDetails(location,custdetails);
	}
	public String deleteLibraryLocations(String[] librarylocid, String log_audit_session,UserLoggingsPojo custdetails) {
		return service.deleteLibraryLocations(librarylocid,log_audit_session,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStaffListDetails(String accyear_ID, String loc_ID, String select, String department, String designation,UserLoggingsPojo custdetails,
			String searchValue) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStaffListDetails(accyear_ID,loc_ID,select,department,designation,custdetails,searchValue);
	}


	public ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDepartment(String accyear_ID, String Loc_ID,
			String department,String designation,UserLoggingsPojo custdetails, String searchValue) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStaffdetailsByDepartment(accyear_ID, Loc_ID,department,designation,custdetails,searchValue);
	}


	public ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDesignation(String accyear_ID, String Loc_ID,
			String department, String designation,UserLoggingsPojo custdetails, String searchValue) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStaffdetailsByDesignation(accyear_ID,Loc_ID,department,designation,custdetails,searchValue);
	}


	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByAnyWhere(String searchTextVal, String location, String select,String department,String designation,String accyear,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchStaffDetailsByAnyWhere(searchTextVal,location,select,department,designation,accyear,custdetails);
	}


	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByStartWith(String searchTextVal, String location, String select, String department, String designation, String accyear,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchStaffDetailsByStartWith(searchTextVal,location,select,department,designation,accyear,custdetails);
	}


	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByEndsWith(String searchTextVal, String location, String select, String department, String designation, String accyear,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchStaffDetailsByEndsWith(searchTextVal,location,select,department,designation,accyear,custdetails);
	}


	public ArrayList<LibrarySearchSubscriberVO> getStaffListFilterByLocationAndAcyearid(String accyear_ID,
			String loc_ID,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStaffListFilterByLocationAndAcyearid(accyear_ID,loc_ID,custdetails);
	}


	public ArrayList<LibraryLocationVO> getSchoolLocations(String id,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSchoolLocations(id,custdetails);
	}
	public java.util.List<LibrarySearchIssueDetailsVO> getIssueDetailsByStartwith(String searchTextVal, String locid, String accyear, String selection,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getIssueDetailsByStartwith(searchTextVal,locid,accyear,selection,custdetails);
	}
	public List<SubCategoryType2VO> getSubCategoryType2Details(UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryType2Details(custdetails);
	}

	public SubCategoryType2VO editSubCategoryType2(String id,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.editSubCategoryType2(id,custdetails);
	}


	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherList(String locid,String accyear,UserLoggingsPojo custdetails) {
		return service. getTeacherList(locid,accyear,custdetails);
	}

	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherDeptList(String locid, String accyear, String dept,UserLoggingsPojo custdetails) {
		
		return service.getTeacherDeptList(locid,accyear, dept,custdetails);
	}

	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherDesgList(String locid, String accyear, String dept, String desg,UserLoggingsPojo custdetails) {
		
		return service.getTeacherDesgList(locid,accyear, dept,desg,custdetails);
	}

	public java.util.List<LibrarySearchIssueDetailsVO> getIssueByStartwith(LibrarySearchIssueDetailsVO vo, String selection,UserLoggingsPojo custdetails) {
		
		return service.getIssueByStartwith(vo,selection,custdetails);
	}



	public boolean validateLibLocationUpdate(LibraryLocationVO lib,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.validateLibLocationUpdate(lib,custdetails);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByAnyWhere(String searchTextVal, String location, String academic_year, String select,
			String classname, String sectionid,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchSubscriberDetailsByAnyWhere(searchTextVal,location,academic_year,select,classname,sectionid,custdetails);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByStartWith(String searchTextVal, String location, String select,
			String classname, String sectionid, String academic_year,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchSubscriberDetailsByStartWith(searchTextVal,location,select,classname,sectionid,academic_year,custdetails);
	}

	public List<SubCategoryType1VO> getSubCategory1ByCategoryAndSubCategory(
			String subCategoryTypeCode,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategory1ByCategoryAndSubCategory(subCategoryTypeCode,custdetails);
	}

	public String insertSubCategoryType2Detail(SubCategoryType2VO sub2,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.insertSubCategoryType2Detail(sub2,custdetails);
	}

	public String inactiveSubCategoryType2(String[] id, String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.inactiveSubCategoryType2(id,log_audit_session,custdetails);
	}

	public ArrayList<LibrarySearchIssueDetailsVO> getOthersList(String locid,String accyear,UserLoggingsPojo custdetails) {
	
		return service.getOthersList(locid, accyear,custdetails);
	}

	public java.util.List<LibrarySearchIssueDetailsVO> getIssueotherByStartwith(String searchTextVal, String locid, String accyear, String selection,UserLoggingsPojo custdetails) {
		
		return service.getIssueotherByStartwith( searchTextVal, locid, accyear, selection,custdetails);
	}



	public List<CategoryTypeVO> getSubCategoryTypeName(String categoryName,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryTypeName(categoryName,custdetails);
	}
	
	
	public List<CategoryTypeVO> getSubCategoryList(String catcode,String subcatcode,String status,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryList(catcode,subcatcode,status,custdetails);
	}
	
	public List<CategoryTypeVO> getbystatusList(String catcode,String subcatcode,String status,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getbystatusList(catcode,subcatcode,status,custdetails);
	}
	
	
	public List<CategoryTypeVO> SearchCategoryTypeList(String catcode,String subcatcode,String status,String searchname,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchCategoryTypeList(catcode,subcatcode,status,searchname,custdetails);
	}


	public List<SubCategoryType1VO> getTabByCategoryType(String cattype, String status, String subcacode, String subcacode1,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTabByCategoryType(cattype,status,subcacode,subcacode1,custdetails);
	}

	public List<SubCategoryType1VO> getTableBycategorytypeandSub(String cattype, String status, String category, String subcacode1,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTableBycategorytypeandSub(cattype,status,category,subcacode1,custdetails);
	}

	public List<SubCategoryType1VO> getTableBycategorytypeandSub1(String cattype, String status, String category, String subcacode,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTableBycategorytypeandSub1(cattype,status,category,subcacode
,custdetails);
	}

	
	public String insertSubCategoryTypeDetail3(SubCategoryTypeVO insert_SubcategoryType,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.insertSubCategoryTypeDetail3(insert_SubcategoryType,custdetails);
	}
	
	
	public List<SubCategoryTypeVO> getSubCategoryDetails3(UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryDetails3(custdetails);
	}
	
	
	public SubCategoryTypeVO editSubCategoryType3(String id,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.editSubCategoryType3(id,custdetails);
	}
	
	public List<SubCategoryType1VO> getSubCategory2ByCategoryAndSubCategory(
			String subCategoryTypeCode,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategory2ByCategoryAndSubCategory(subCategoryTypeCode,custdetails);
	}
	
	
	
	public String inactiveSubCategoryType3(String[] id, SubCategoryTypeVO vo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
	return service.inactiveSubCategoryType3(id,vo,custdetails);
		
	}
	
	public List<CategoryTypeVO> getSubCategoryDetails3(String cattype,String subcatcode, String subcatcode1, String subcatcode2, String subcatcode3, String status,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryDetails3(cattype,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,custdetails);
	}
	
	public List<CategoryTypeVO> getSubCategoryList3(String catcode,String subcatcode,String status,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryList3(catcode,subcatcode,status,custdetails);
	}
	
	public List<SubCategoryType1VO> getSubCategory3ByCategoryAndSubCategory(
			String subCategoryTypeCode,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategory3ByCategoryAndSubCategory(subCategoryTypeCode,custdetails);
	}
	
	public List<CategoryTypeVO> SearchCategoryType3List(String catcode,String subcatcode,String subcatcode1,String subcatcode2,String subcatcode3,String status,String searchname,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchCategoryType3List(catcode,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname,custdetails);
	}

	public ArrayList<CategoryTypeVO> getcategorylist(String cateid ,String status,UserLoggingsPojo custdetails) {
		
		return service.getcategorylist(cateid,status,custdetails);
	}

	public List<SubCategoryType1VO> getTableByStatus(String status, String categorycode, String subcategorycode, String subcategory1code,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTableByStatus(status,categorycode,subcategorycode,subcategory1code,custdetails);
	}

	public ArrayList<LibraryStockEntryVO> getAccessionNoList(UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getAccessionNoList(custdetails);
	}


	public ArrayList<CategoryTypeVO> getclassdescrlist(String cateid,UserLoggingsPojo custdetails) {
		return service. getclassdescrlist( cateid,custdetails);
	}

	public ArrayList<CategoryTypeVO> getlibcategorysectionlist(String cateid,String classid,UserLoggingsPojo custdetails) {
		
		return service.getlibcategorysectionlist(cateid, classid,custdetails);
	}

	public ArrayList<CategoryTypeVO> getlibcategorydivisionlist(String sectionid,UserLoggingsPojo custdetails) {
		
		return service.getlibcategorydivisionlist(sectionid,custdetails);
	}

	public String saveStockEnteryDetails(LibraryStockEntryDetailsForm libform,UserLoggingsPojo custdetails) {
		
		return service.saveStockEnteryDetails(libform,custdetails);
	}



	public List<LibraryStockEntryVO> getAccessionNo(
			LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getAccessionNo(registrationVo,custdetails);
		
	}

	public static  List<LibraryStockEntryVO> getBookIssueDetailsByAccessionNo(
			LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getBookIssueDetailsByAccessionNo(libVo,custdetails);
	}

	public String insertBookIssueDetails(LibraryStockEntryVO insert_issue,UserLoggingsPojo custdetails) {
		return service.insertBookIssueDetails(insert_issue,custdetails);
	}


	

	public List<SubCategoryType2VO> getTabBySub2CategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTabBySub2CategoryType(cattype,status,subcategory,subcategory1,subcategory2,custdetails);
	}

	public List<SubCategoryType2VO> getTabBySub2subCategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTabBySub2subCategoryType(cattype,status,subcategory,subcategory1,subcategory2,custdetails);
	}

	public List<SubCategoryType2VO> getTabBySub2subCategory1Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTabBySub2subCategory1Type(cattype,status,subcategory,subcategory1,subcategory2,custdetails);
	}

	public List<SubCategoryType1VO> searchSubCatType1(String searchname, String catcode, String subcatcode, String subcatcode1, String status,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.searchSubCatType1(searchname,catcode,subcatcode,subcatcode1,status,custdetails);
	}

	public List<SubCategoryType2VO> getTableBySub2Status(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTableBySub2Status(cattype,status,subcategory,subcategory1,subcategory2,custdetails);
	}




	public ArrayList<LibrarySubscribVO> getOtherSubscribeNunmber(String loc, String subscriberType,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getOtherSubscribeNunmber(loc,subscriberType,custdetails);
	}

	public ArrayList<LibrarySubscribVO> showBlockListedData(String loc,String subscriberType, String subscriberNo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().showBlockListedData(loc,subscriberType,subscriberNo,custdetails);
	}
	
public String ValidateSubcat(String subname,UserLoggingsPojo custdetails) {
		
		return service.ValidateSubcat(subname,custdetails);
	}


public String ValidateSubcatupdate(String subname,UserLoggingsPojo custdetails) {
	
	return service.ValidateSubcatupdate(subname,custdetails);
}




	public List<SubCategoryType2VO> getTabBySub2subCategory2Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTabBySub2subCategory2Type(cattype,status,subcategory,subcategory1,subcategory2,custdetails);
	}

	public List<SubCategoryType2VO> searchSubCatType2(String searchname, String categorytype, String subcategorytype, String subcategorytype1, String subcategorytype2, String status,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.searchSubCatType2(searchname,categorytype,subcategorytype,subcategorytype1,subcategorytype2,status,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getOthersListDetails( String location,String select, String academic_year,UserLoggingsPojo custdetails, String searchValue) {
		LibraryService service=new LibraryServiceImpl();
		return service.getOthersListDetails(location,select,academic_year,custdetails,searchValue);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByAnyWhere(String searchTextVal,String location, String select, String accyear,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchOthersDetailsByAnyWhere(searchTextVal,location,select,accyear,custdetails);
	}
	
	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByStartWith(String searchTextVal,String location,String select,String accyear,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchOthersDetailsByStartWith(searchTextVal,location,select,accyear,custdetails);
	}
	
	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByEndsWith(String searchTextVal,String location, String select,String accyear,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchOthersDetailsByEndsWith(searchTextVal,location,select,accyear,custdetails);
	}

	public String ValidateSubcat3(String subname,UserLoggingsPojo custdetails) {
	return service.ValidateSubcat3(subname,custdetails);
}

	

	public LibrarySubscribVO gotosubscribersDetails(String location, String subId, String academic_year, String subscriberType,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.gotosubscribersDetails(location,subId,academic_year,subscriberType,custdetails);
	}
	
	public String updateSubscriberDetails(LibrarySubscribVO resultData,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.updateSubscriberDetails(resultData,custdetails);
	}



	public LibrarySubscribVO IssueStatementBySubScriberType(String location, String subId, String academic_year,
			String subscriberType,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.IssueStatementBySubScriberType(location,subId,academic_year,subscriberType,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> IssueStatementTable(String location, String subId, String academic_year,
			String subscriberType,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.IssueStatementTable(location,subId,academic_year,subscriberType,custdetails);
	}

	public LibrarySubscribVO issuestatementissue(String subId,String issueId,String subscriberType,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.issuestatementissue(subId,issueId,subscriberType,custdetails);
	}




	public List<LibraryStockEntryDetailsForm> getStockEntryBookList(UserLoggingsPojo custdetails, StockEntryVo vo) {
		
		return service. getStockEntryBookList(custdetails,vo);
	}

	public LibraryStockEntryDetailsForm editStockEntryDetail(String id,UserLoggingsPojo custdetails) {
		
		return service.editStockEntryDetail(id,custdetails);
	}



	public String blockTheSubscriber(String subscriberNo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().blockTheSubscriber(subscriberNo,custdetails);
	}

	public ArrayList<LibrarySubscribVO> getStaffRegId(String loc, String searchterm,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getStaffRegId(loc,searchterm,custdetails);
	}

	public String duplicateDataCheck(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().duplicateDataCheck(pojo,custdetails);
	}
	
	public String activeSubCategoryType(String[] id, SubCategoryTypeVO vo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
	return service.activeSubCategoryType(id,vo,custdetails);
		
	}
	
	
	public String activeSubCategoryType3(String[] id, SubCategoryTypeVO vo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
	return service.activeSubCategoryType3(id,vo,custdetails);
		
	}

	public ArrayList<LibrarySubscribVO> getBlockListedStaffData(String accyear,String location,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getBlockListedStaffData(accyear, location,custdetails);
	}

	public ArrayList<LibrarySubscribVO> getBlockListedStudentData(String accyear, String location,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getBlockListedStudentData( accyear,  location,custdetails);
	}

	public ArrayList<LibrarySubscribVO> getBlockListedOtherData(String accyear,String location,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getBlockListedOtherData( accyear, location,custdetails);
	}

	public String unblockSubscriber(String id,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().unblockSubscriber(id,custdetails);
	}

	public String validateStockEnteryDetails(String accno,UserLoggingsPojo custdetails) {
		
		return service. validateStockEnteryDetails(accno,custdetails);
	}



	public String insertBookReturnDetails(LibraryStockEntryVO insert_issue,UserLoggingsPojo custdetails) {
		return service.insertBookReturnDetails(insert_issue,custdetails);
	}

	public static List<LibraryStockEntryVO> getBookReturnDetailsByAccessionNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getBookReturnDetailsByAccessionNo(libVo,custdetails);
	}

	public List<LibraryStockEntryVO> getAccessionNoByIssue(LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getAccessionNoByIssue(registrationVo,custdetails);
	}



	public ArrayList<CategoryTypeVO> getCategoryListBySearch(String cateid, String status, String searchname,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getCategoryListBySearch(cateid,status,searchname,custdetails);
	}

	public String activeSubCategoryType1(String[] id, SubCategoryType1VO vo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
	return service.activeSubCategoryType1(id,vo,custdetails);
		
	}

	public String activeSubCategoryType2(String[] id, SubCategoryType2VO vo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
	return service.activeSubCategoryType2(id,vo,custdetails);
		
	}

	public String activeCategoryType(String[] id, CategoryTypeVO vo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.activeCategoryType(id,vo,custdetails);
	}

	public LibrarySubscribVO GOtOIssueReturns(String subId, String subscriberType, String issueId,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.GOtOIssueReturns(subId,subscriberType,issueId,custdetails);
	}

	public String publisherSettings(LibraryStockEntryVO obj,UserLoggingsPojo custdetails) {
	
		return service.publisherSettings(obj,custdetails);
	}

	public List<LibraryStockEntryVO> getPublisherSettingList(UserLoggingsPojo custdetails) {
		return service.getPublisherSettingList(custdetails);
	}

	public LibraryStockEntryVO editpublisherSetting(String id,UserLoggingsPojo custdetails) {
		return service.editpublisherSetting(id,custdetails);
	}

	public String deletepublisherSetting(String[] deleteId, String log_audit_session,UserLoggingsPojo custdetails) {
		return service.deletepublisherSetting(deleteId,log_audit_session,custdetails);
	}

	public String validationpubsettings(String pub, String address,
			String email, String telphone, String mobilenum,UserLoggingsPojo custdetails) {
		return service.validationpubsettings(pub,address,email,telphone,mobilenum,custdetails);
	}

	public ArrayList<ReportMenuVo> getLibraryLocation(UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getLibraryLocation(custdetails);
	}

	public String TransferStudent(String[] subscriberId ,String locid,UserLoggingsPojo custdetails) {
	
		return service.TransferStudent(subscriberId,locid,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTranferStudentListDetails(String academic_year, String location, String select,
			String classname, String sectionid, String libloc,UserLoggingsPojo custdetails) {
		return service.getTranferStudentListDetails(academic_year,location,select,classname,sectionid,libloc,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListBySection(String academic_year, String location, String classname,String sectionid, String select, String liblocation,UserLoggingsPojo custdetails) {
		return service.getTrasferStudentListBySection(academic_year,location,classname,sectionid,select,liblocation,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListByClassName(
			String academic_year, String location, String classname,
			String select, String liblocation,UserLoggingsPojo custdetails) {
		return service.getTrasferStudentListByClassName(academic_year,location,classname,select,liblocation,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffListDetails(String accyear_ID, String loc_ID, String select,
			String department, String designation,String libloc,UserLoggingsPojo custdetails) {
			LibraryService service=new LibraryServiceImpl();
			return service.getTransferStaffListDetails(accyear_ID,loc_ID,select,department,designation,libloc,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferOthersListDetails(String location,String select,String academic_year,String libloc,UserLoggingsPojo custdetails) {
	
		return service.getTransferOthersListDetails(location,select,academic_year,libloc,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getlocationStudentList(String libloc, String select,UserLoggingsPojo custdetails) {
		return service.getlocationStudentList(libloc,select,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getliblocationstafflist(String libloc, String select,UserLoggingsPojo custdetails) {
		return service.getliblocationstafflist(libloc,select,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getliblocatinotherlist(String academic_year, String location, String libloc, String select,UserLoggingsPojo custdetails) {
		
		return service.getliblocatinotherlist(academic_year,location,libloc,select,custdetails);
	}

	public java.util.List<LibrarySearchSubscriberVO> TransferSubscriberbySearch(String searchTextVal, String location, String academic_year,String liblocid, String select,String classname, String sectionid,UserLoggingsPojo custdetails) {
		
		return service.TransferSubscriberbySearch(searchTextVal,location,academic_year,liblocid,select,classname,sectionid,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyStaffSearch(String searchTextVal, String location, String liblocid,String select, String department, String designation, String accyear,UserLoggingsPojo custdetails) {
		return service.TransferSubscriberbyStaffSearch(searchTextVal,location,liblocid,select,department,designation,accyear,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyotherSearch(
			String searchTextVal, String location, String select,String liblocid, String accyear,UserLoggingsPojo custdetails) {
		return service.TransferSubscriberbyotherSearch(searchTextVal,location,select,liblocid,accyear,custdetails);
	}

	public String addSupplierSettings(LibraryStockEntryVO obj,UserLoggingsPojo custdetails) {
		
		return service.addSupplierSettings(obj,custdetails);
	}

	public List<LibraryStockEntryVO> getSupplierSettingList(UserLoggingsPojo custdetails) {
		
		return service.getSupplierSettingList(custdetails);
	}

	public LibraryStockEntryVO editSupplierSetting(String id,UserLoggingsPojo custdetails) {
		
		return service.editSupplierSetting(id,custdetails);
	}

	public String deleteSupplierSetting(String[] deleteId, String log_audit_session,UserLoggingsPojo custdetails) {
	
		return service.deleteSupplierSetting(deleteId,log_audit_session,custdetails);
	}

	public String validationsubsettings(String suplier, String supadd,String emailid,String telephone,String supnum,UserLoggingsPojo custdetails) {
		
		return service.validationsubsettings(suplier,supadd,emailid,telephone,supnum,custdetails);
	}

	public List<TeacherVo> othersSearchbyId(TeacherVo registrationVo,UserLoggingsPojo custdetails) {
		return service.othersSearchbyId(registrationVo,custdetails);
	}

	public static List<LibraryStockEntryVO> getStudentIssueDetailsBySubscriberNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		return service.getStudentIssueDetailsBySubscriberNo(libVo,custdetails);
	}
	public String insertBookReservationDetails(LibraryStockEntryVO insert_issue,UserLoggingsPojo custdetails) {
		return service.insertBookReservationDetails(insert_issue,custdetails);
	}

	public List<LibraryStockEntryDetailsForm> getReservationListDetails(UserLoggingsPojo custdetails, LibraryVO vo) {
		return service.getReservationListDetails(custdetails,vo);
	}

	public ArrayList<LibraryStockEntryVO> publisherDetailsSearch(
			String searchTextVal, String pub,UserLoggingsPojo custdetails) {
		
		return service.publisherDetailsSearch(searchTextVal,pub,custdetails);
	}

	public ArrayList<LibraryStockEntryVO> SupplierDetailsSearch(
			String searchTextVal, String sup,UserLoggingsPojo custdetails) {
		return service.SupplierDetailsSearch(searchTextVal,sup,custdetails);
	}

	


	public static List<LibraryStockEntryVO> getTeacherIssueDetails(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		return service.getTeacherIssueDetails(libVo,custdetails);
	}

	public static List<LibraryStockEntryVO> getOtherIssueDetails(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		return service.getOtherIssueDetails(libVo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> IssueReturnTable(String location, String subId, String academic_year,
			String subscriberType,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.IssueReturnTable(location,subId,academic_year,subscriberType,custdetails);
	}

	public String addGeneralSettings(LibraryStockEntryVO obj,UserLoggingsPojo custdetails) {
		
		return service.addGeneralSettings(obj,custdetails);
	}

	public List<LibraryStockEntryVO> getGenarelSettingList(UserLoggingsPojo custdetails) {
		return service.getGenarelSettingList(custdetails);
	}

	public LibraryStockEntryVO editGenarelSetting(String id,UserLoggingsPojo custdetails) {
		return service.editGenarelSetting(id,custdetails);
	}

	public String deleteGenarelSetting(String[] deleteId, String log_audit_session,UserLoggingsPojo custdetails) {
		return service.editGenarelSetting(deleteId,log_audit_session,custdetails);
	}

	public ArrayList<LibraryStockEntryVO> GenarelDetailsSearch(
			String searchTextVal,UserLoggingsPojo custdetails) {
		return service.GenarelDetailsSearch(searchTextVal,custdetails);
	}

	public LibrarySubscribVO IssueReturnBySubScriberType(String location, String subId, String academic_year,
			String subscriberType,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.IssueReturnBySubScriberType(location,subId,academic_year,subscriberType,custdetails);
	}

	public ArrayList<LibraryVO> getcodeList(UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getcodeList(custdetails);
	}

	public List<LibraryStockEntryVO> getcodeName(LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getcodeName(registrationVo,custdetails);
	}

	public static List<LibraryStockEntryVO> getCodeByCodeName(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getCodeByCodeName(libVo,custdetails);
	}

	public LibraryStockEntryVO editReservationBook(String id,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.editReservationBook(id,custdetails);
	}

	public String updateBookReservationDetails(LibraryStockEntryVO insert_issue,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.updateBookReservationDetails(insert_issue,custdetails);
	}

	public static List<LibraryStockEntryVO> getBookReservationDetailsByAccNo(
			LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getBookReservationDetailsByAccNo(libVo,custdetails);
	}

	public String deleteReservedBook(String[] librarylocid, String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.deleteReservedBook(librarylocid,log_audit_session,custdetails);
	}

	public boolean validateReservedBook(LibraryStockEntryVO reserve,UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.validateReservedBook(reserve,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListDetails(String academic_year, String location,
			String select, String classname, String sectionid,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getMostWantedStudentListDetails(academic_year,location,select,classname,sectionid,custdetails);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchMostWantedStudentDetailsByAnyWhere(String searchTextVal,String location,String academic_year,
			String select, String startwith, String classname, String sectionid,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().SearchMostWantedStudentDetailsByAnyWhere(searchTextVal,location,academic_year,select,startwith,classname,sectionid,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListByClassName(String academic_year,
			String location, String classname, String sectionid, String select,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getMostWantedStudentListByClassName(academic_year,location,classname,sectionid,select,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListBySection(String academic_year, String location,
			String classname, String sectionid, String select,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getMostWantedStudentListBySection(academic_year,location,classname,sectionid,select,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffListDetails(String academic_year, String location,
			String select, String department, String designation,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getMostWantedStaffListDetails(academic_year,location,select,department,designation,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchMostWantedStaffDetailsByAnyWhere(String searchTextVal,String location,String select,String startwith,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().SearchMostWantedStaffDetailsByAnyWhere(searchTextVal,location,select,startwith,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffdetailsByDepartment(String accyear_ID, String loc_ID,
			String department, String designation,String select,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getMostWantedStaffdetailsByDepartment(accyear_ID,loc_ID, department,designation,select,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffdetailsByDesignation(String accyear_ID, String loc_ID,
			String department, String designation, String select,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getMostWantedStaffdetailsByDesignation(accyear_ID,loc_ID,department,designation,select,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedOthersListDetails(String location, String select,
			String academic_year,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getMostWantedOthersListDetails(location,select,academic_year,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchMostWantedOthersDetailsByAnyWhere(String searchTextVal,
			String location, String select, String startwith,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().SearchMostWantedOthersDetailsByAnyWhere(searchTextVal,location,select,startwith,custdetails);
	}

	public String savejournalsubscriptiondetail(LibraryJournalSubscriptionVo obj,UserLoggingsPojo custdetails) {
	
		return service.savejournalsubscriptiondetail(obj,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getAllBookDetails(pojo,custdetails);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchByaccNoandTitleAnyWhere(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().SearchBookSearchByaccNoandTitleAnyWhere(pojo,custdetails);
	}


	public List<LibraryJournalSubscriptionVo> getJournalSubscriptionList(UserLoggingsPojo custdetails) {
		
		return service.getJournalSubscriptionList(custdetails);
	}

	public LibraryJournalSubscriptionVo editeLibraryJournalSubscription(
			String id,UserLoggingsPojo custdetails) {
		
		return service.editeLibraryJournalSubscription(id,custdetails);
	}

	public String deleteJournalSbscription(String[] deleteId, String log_audit_session,UserLoggingsPojo custdetails) {
		
		return service.deleteJournalSbscription(deleteId,log_audit_session,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookPublisherDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getAllBookPublisherDetails(pojo,custdetails);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchByPublisherAnyWhere(
			LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().SearchBookSearchByPublisherAnyWhere(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookItemTypeDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getAllBookItemTypeDetails(pojo,custdetails);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchByItemTypeAnyWhere(
			LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().SearchBookSearchByItemTypeAnyWhere(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookDDCDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getAllBookDDCDetails(pojo,custdetails);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchByDDCAnyWhere(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().SearchBookSearchByDDCAnyWhere(pojo,custdetails);
	}
	public List<LibraryJournalSubscriptionVo> getJournalSubscriptioncodelist(UserLoggingsPojo custdetails) {
		
		return service.getJournalSubscriptioncodelist(custdetails);
	}

	public List<LibraryJournalSubscriptionVo> getnamelist(UserLoggingsPojo custdetails) {
		
		return service.getnamelist(custdetails);
	}

	public ArrayList<LibraryJournalSubscriptionVo> journalsubscriptionDetailsSearch(
			String searchTextVal, String name,UserLoggingsPojo custdetails) {
		
		return service.journalsubscriptionDetailsSearch(searchTextVal,name,custdetails);
	}

	public List<LibraryStockEntryVO> getstockEntryList(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId,UserLoggingsPojo custdetails) {
		
		return service.getstockEntryList(locId,itemId,regdateId,booktitle,authorId,pubId,custdetails);
	}

	public List<LibraryStockEntryVO> getlocationlist(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId,UserLoggingsPojo custdetails) {
		
		return service.getlocationlist(locId,itemId,regdateId,booktitle,authorId,pubId,custdetails);
	}

	public List<LibraryStockEntryVO> getitemlistList(UserLoggingsPojo custdetails) {
		
		return service.getitemlistList(custdetails);
	}

	public List<LibraryStockEntryVO> booklist(UserLoggingsPojo custdetails, String itemType) {
		
		return service.booklist(custdetails,itemType);
	}

	public List<LibraryStockEntryVO> authorlist(UserLoggingsPojo custdetails) {
		
		return service.authorlist(custdetails);
	}
	
	public ArrayList<LibrarySearchIssueDetailsVO> getSubscriberDetailStudentExcelReport(LibrarySearchIssueDetailsVO obj,UserLoggingsPojo custdetails) {
		
		return service.getSubscriberDetailStudentExcelReport(obj,custdetails);
	}

public ArrayList<LibrarySearchIssueDetailsVO> getStaffSubscriberDetailReport(LibrarySearchIssueDetailsVO obj,UserLoggingsPojo custdetails) {
		
		return service.getStaffSubscriberDetailReport(obj,custdetails);
	}
public List<LibraryStockEntryVO> getReservationListReport(String location, String accId, String subId, String accNo, String bookId, String fromdat, String todate, String date,UserLoggingsPojo custdetails) {
   
	 return service.getReservationListReport(location,accId,subId,accNo,bookId,fromdat,todate,date,custdetails);
	}

	
	public List<LibraryStockEntryDetailsForm> getbooktitleList(String subtype, String accyear, String accNo,UserLoggingsPojo custdetails) {
		
		return service.getbooktitleList(subtype,accyear,accNo,custdetails);
	}
	
public ArrayList<LibrarySearchIssueDetailsVO> getOtherSubscriberDetailReport(LibrarySearchIssueDetailsVO obj,UserLoggingsPojo custdetails) {
		
		return service.getOtherSubscriberDetailReport(obj,custdetails);
	}

	public List<LibraryStockEntryVO> getNewArrivalListReport(String checkedVal, String fromdate, String toDate,UserLoggingsPojo custdetails) {
		return service.getNewArrivalListReport(checkedVal,fromdate,toDate,custdetails);
	}


	
	public ArrayList<LibrarySearchSubscriberVO> getAllBookContentDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getAllBookContentDetails(pojo,custdetails);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchByContentAnyWhere(
			LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().SearchBookSearchByContentAnyWhere(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookLanguageDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getAllBookLanguageDetails(pojo,custdetails);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchByLanguageAnyWhere(
			LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().SearchBookSearchByLanguageAnyWhere(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookSupplierDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getAllBookSupplierDetails(pojo,custdetails);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchBySupplierAnyWhere(
			LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().SearchBookSearchBySupplierAnyWhere(pojo,custdetails);
	}

	public LibraryStockEntryDetailsForm gotostockDetails(String id,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().gotostockDetails(id,custdetails);
	}

	public LibrarySubscribVO IssueStatementByStockEntryId(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().IssueStatementByStockEntryId(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> IssueStatementTableByStockEntryId(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().IssueStatementTableByStockEntryId(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> ReturnStatementTableByStockEntryId(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().ReturnStatementTableByStockEntryId(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getOverDueStatement(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getOverDueStatement(pojo,custdetails);
	}

	public List<LibrarySubscribVO> getClassByLibraryLocation(String locationid,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getClassByLibraryLocation(locationid,custdetails);
	}

	public java.util.List<LibrarySubscribVO> getLibraryClassSection(String classidVal,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getLibraryClassSection(classidVal,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentOverDueStatementByClass(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getStudentOverDueStatementByClass(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentOverDueStatementBySection(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getStudentOverDueStatementBySection(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchOverDueStudentDetailsByAnyWhere(
			LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().SearchOverDueStudentDetailsByAnyWhere(pojo,custdetails);
	}

	public ArrayList<LibraryStockEntryDetailsForm> getStudentOverDueByOrderwise(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getStudentOverDueByOrderwise(pojo,custdetails);
	}

	public List<LibrarySearchIssueDetailsVO> getAllOverDueListDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getAllOverDueListDetails(pojo,custdetails);
	}
	
	public ArrayList<LibrarySearchSubscriberVO> getAllBookDetailsDownloadandPrint(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getAllBookDetailsDownloadandPrint(pojo,custdetails);
	}

	public List<LibraryStockEntryDetailsForm> getReservationAccNo(
			String subtype, String accyear,UserLoggingsPojo custdetails, String locId) {
		
		return service.getReservationAccNo(subtype,accyear,custdetails,locId);
	}

	public ArrayList<LibrarySubsciberDetailsPojo> getAllIssueReturnDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getAllIssueReturnDetails(pojo,custdetails);
	}

	public List<LibrarySubsciberDetailsPojo> IndividualSearchInIssueReturnStatement(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().IndividualSearchInIssueReturnStatement(pojo,custdetails);
	}
	public ArrayList<LibraryStockEntryVO> getJournalNameList(String accyear,UserLoggingsPojo custdetails) {
		return service.getJournalNameList(accyear,custdetails);
	}

	public List<LibraryJournalSubscriptionVo> getJournalListReport(String checkedVal,
			String fromdate, String toDate, String accyear, String journalName,UserLoggingsPojo custdetails) {
		return service.getJournalListReport(checkedVal,fromdate,toDate,accyear,journalName,custdetails);
	}


	public static List<LibraryStockEntryVO> getBookIssueReturnDetailsByAccessionNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getBookIssueReturnDetailsByAccessionNo(libVo,custdetails);
	}

	public List<LibraryStockEntryVO> getIssueReturnAccessionNo(LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getIssueReturnAccessionNo(registrationVo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffdetailsByDepartment(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getTransferStaffdetailsByDepartment(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffdetailsByDesignation(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getTransferStaffdetailsByDesignation(pojo,custdetails);
	}

	public List<LibraryStockEntryVO> getAccessionNoByIssueStatus(
			LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails) {
		return new LibraryServiceImpl().getAccessionNoByIssueStatus(registrationVo,custdetails);
	}
	public ArrayList<LibraryItems> getLibItemsDetails(UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getLibItemsDetails(custdetails);
	}

	public LibraryItems getSingleLibItemsDetails(String id, UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSingleLibItemsDetails(id,custdetails);
	}

	public String InsertLibraryItem(LibraryItems obj, UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.InsertLibraryItem(obj,custdetails);
	}

	public List<LibraryItems> getLibraryItemByJS(LibraryItems obj, UserLoggingsPojo custdetails) {
		LibraryService service=new LibraryServiceImpl();
		return service.getLibraryItemByJS(obj,custdetails);
	}
	
}


