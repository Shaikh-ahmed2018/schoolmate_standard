package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.dao.LibraryDAO;
import com.centris.campus.daoImpl.LibraryDAOIMPL;
import com.centris.campus.forms.LibraryStockEntryDetailsForm;
import com.centris.campus.pojo.LibraryItems;
import com.centris.campus.pojo.LibraryLocationPojo;
import com.centris.campus.pojo.LibrarySubsciberDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.LibraryService;
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

public class LibraryServiceImpl implements LibraryService{
	private static final String String = null;

	@Override
	public String insertCategoryTypeDetail(CategoryTypeVO insert_categoryType,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertCategoryTypeDetail(insert_categoryType,custdetails);
	}

	@Override
	public List<CategoryTypeVO> getCategoryDetails(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getCategoryDetails(custdetails);
	}
	
	public List<StudentRegistrationVo> studentSearchbyadmissionNo(StudentRegistrationVo registrationVo,String locid,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.studentSearchbyadmissionNo(registrationVo,locid,custdetails);
		
	}
	public List<CategoryTypeVO> getSubCategoryDetails(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryDetails(custdetails);
	}
	
	public List<CategoryTypeVO> getSubCategoryDetails1(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryDetails1(custdetails);
	}
	
	public List<CategoryTypeVO> getSubCategoryDetails(String cattype,String status,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryDetails(cattype,status,custdetails);
	}

	@Override
	public List<TeacherVo> teacherSearchbyId(TeacherVo teacherVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.teacherSearchbyId(teacherVo,custdetails);
		
	}


	@Override
	public CategoryTypeVO editCategoryType(String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editCategoryType(id,custdetails);
	}
	
	public SubCategoryTypeVO editSubCategoryType(String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editSubCategoryType(id,custdetails);
	}

	@Override
	public String inactiveCategoryType(String[] catIdlist,String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.inactiveCategoryType(catIdlist,log_audit_session,custdetails);
		
	}
	
	@Override
	public String inactiveSubCategoryType(String[] id,SubCategoryTypeVO vo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.inactiveSubCategoryType(id,vo,custdetails);
		
	}
 

	public ArrayList<LibrarySubscribVO> getStaffData(String staffid,String locId, UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getStaffData(staffid, locId,custdetails);
	}
    public ArrayList<LibrarySubscribVO> getStudentData(String academicYear,String admissionNo, UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getStudentData(academicYear, admissionNo,custdetails);
    }



	@Override
	public String insertSubCategoryType1Detail(SubCategoryType1VO sub1,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertSubCategoryType1Detail(sub1,custdetails);
	}

	@Override
	public List<SubCategoryType1VO> getSubCategoryType1Details(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryType1Details(custdetails);
	}

	@Override
	public SubCategoryType1VO editSubCategoryType1(String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editSubCategoryType1(id,custdetails);
	}

	@Override
	public List<SubCategoryTypeVO> getSubCategoryByCategory(String categoryCode,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryByCategory(categoryCode,custdetails);
	}
	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getStudentIssuedList(String locid, String accyear,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStudentIssuedList(locid,accyear,custdetails);
	}
	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStudentListDetails(String academic_year, String location,
			String select,String classname,String sectionid,UserLoggingsPojo custdetails,String searchValue) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStudentListDetails(academic_year,location,select,classname,sectionid,custdetails,searchValue);
	}

	public String insertSubCategoryTypeDetail(SubCategoryTypeVO insert_SubcategoryType,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().insertSubCategoryTypeDetail(insert_SubcategoryType,custdetails);

	}
	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentClassList(
			String locid, String accyear, String classname,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getIssueStudentClassList(locid,accyear,classname,custdetails);
	}
	


	public String saveSubscriberDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().saveSubscriberDetails(pojo,custdetails);
	}


	@Override
	public String inactiveSubCategoryType1(String[] id,String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.inactiveSubCategoryType1(id,log_audit_session,custdetails);
		
	}

	@Override
	public boolean validateSubcategoryType1(SubCategoryType1VO sub1,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.validateSubcategoryType1(sub1,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStudentListByClassName(String academic_year, String location,
			String classname,String sectionid,String select,UserLoggingsPojo custdetails,String searchValue) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStudentListByClassName(academic_year,location,classname,sectionid,select,custdetails,searchValue);
	}

	public ArrayList<LibrarySubscribVO> getSubscriberDetailsListPage(String academicYear, String locId, String classId,String sectionName, String suscriberType, String department, String designation, String otherName,UserLoggingsPojo custdetails) {
		return  new LibraryDAOIMPL().getSubscriberDetailsListPage( academicYear,  locId,  classId, sectionName,  suscriberType,department,designation,otherName,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentSectionList(
			String locid, String accyear, String classname, String sectionnm,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getIssueStudentSectionList(locid,accyear, classname,sectionnm,custdetails);
	}


	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStudentListBySection(String academic_year, String location,
			String classname, String sectionid,String select,UserLoggingsPojo custdetails,String searchValue) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStudentListBySection(academic_year,location,classname,sectionid,select,custdetails,searchValue);
	}

	@Override
	public List<LibrarySearchSubscriberVO> searchsubscriberList(String searchTextVal,String academic_year, String location,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.searchsubscriberList(searchTextVal,academic_year,location,custdetails);
	}

	

	public List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByEndsWith(String searchTextVal,String location,String select,
			java.lang.String academic_year,java.lang.String classname,java.lang.String sectionid,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchSubscriberDetailsByEndsWith(searchTextVal,location,select,academic_year,classname,sectionid,custdetails);
	}



	public List<LibrarySearchIssueDetailsVO> getIssueDetailsByAnyWhere(String searchTextVal, String locid, String accyear,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getIssueDetailsByAnyWhere(searchTextVal,locid,accyear,custdetails);
	}


	@Override
	public String insertLibraryLocations(LibraryLocationPojo insert_libLoc,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertLibraryLocations(insert_libLoc,custdetails);
	}

	@Override
	public LibraryLocationVO editLibraryLocation(String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editLibraryLocation(id,custdetails);	}

	@Override
	public String updateLibLocations(LibraryLocationPojo insert_libLoc,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.updateLibLocations(insert_libLoc,custdetails);
	}

	@Override
	public ArrayList<LibraryLocationPojo> getLibLocationsDetails(String location,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getLibLocationsDetails(location,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStaffListDetails(String accyear_ID, String loc_ID,
			String select,String department,String designation,UserLoggingsPojo custdetails,String searchValue) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStaffListDetails(accyear_ID,loc_ID,select,department,designation,custdetails,searchValue);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDepartment(String accyear_ID, String Loc_ID,
			String department,String designation,UserLoggingsPojo custdetails,String searchValue) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStaffdetailsByDepartment(accyear_ID,Loc_ID,department,designation,custdetails,searchValue);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDesignation(String accyear_ID, String Loc_ID,
			String department, String designation,UserLoggingsPojo custdetails,String searchValue) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStaffdetailsByDesignation (accyear_ID,Loc_ID, department,designation,custdetails,searchValue);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByAnyWhere(String searchTextVal,String location,String select,String department,
			String designation,String accyear,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchStaffDetailsByAnyWhere(searchTextVal,location,select,department,designation,accyear,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByStartWith(String searchTextVal,String location,String select,
			String department,String designation,String accyear,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchStaffDetailsByStartWith(searchTextVal,location,select,department,designation,accyear,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByEndsWith(String searchTextVal,String location,
			String select,String department,String designation,String accyear,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchStaffDetailsByEndsWith(searchTextVal,location,select,department,designation,accyear,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStaffListFilterByLocationAndAcyearid(String accyear_ID,
			String loc_ID,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStaffListFilterByLocationAndAcyearid(accyear_ID,loc_ID,custdetails);
	}

	

	public List<LibrarySearchIssueDetailsVO> getIssueDetailsByStartwith(String searchTextVal, String locid, String accyear, String selection,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getIssueDetailsByStartwith(searchTextVal,locid,accyear,selection,custdetails);
	}


	@Override
	public ArrayList<LibraryLocationVO> getSchoolLocations(String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSchoolLocations(id,custdetails);
	}


	@Override
	public List<SubCategoryType2VO> getSubCategoryType2Details(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryType2Details(custdetails);
	}

	@Override
	public SubCategoryType2VO editSubCategoryType2(String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editSubCategoryType2(id,custdetails);
	}

	@Override
	public List<SubCategoryType1VO> getSubCategory1ByCategoryAndSubCategory(String subCategoryTypeCode,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategory1ByCategoryAndSubCategory(subCategoryTypeCode,custdetails);
	}

	@Override
	public String insertSubCategoryType2Detail(SubCategoryType2VO sub2,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertSubCategoryType2Detail(sub2,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherList(String locid,String accyear,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeacherList( locid, accyear,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherDeptList(String locid, String accyear, String dept,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeacherDeptList( locid, accyear,dept,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherDesgList(String locid, String accyear, String dept, String desg,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeacherDesgList( locid, accyear,dept,desg,custdetails);
	}

	@Override
	public List<LibrarySearchIssueDetailsVO> getIssueByStartwith(LibrarySearchIssueDetailsVO vo, String selection,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getIssueByStartwith( vo,selection,custdetails);
	}

	@Override
	public boolean validateLibLocationUpdate(LibraryLocationVO lib,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.validateLibLocationUpdate(lib,custdetails);
	}

	@Override
	public List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByAnyWhere(String searchTextVal,String location,String academic_year,String select,String classname, String sectionid,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchSubscriberDetailsByAnyWhere(searchTextVal,location,academic_year,select,classname,sectionid,custdetails);
	}

	@Override
	public List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByStartWith(String searchTextVal,String location,
			String select,String classname, String sectionid,String academic_year,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchSubscriberDetailsByStartWith(searchTextVal,location,select,classname,sectionid,academic_year,custdetails);
	}

	@Override
	public String inactiveSubCategoryType2(String[] id,String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.inactiveSubCategoryType2(id,log_audit_session,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getOthersList(String locid,String accyear,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOthersList(locid,accyear,custdetails);
	}

	@Override
	public List<LibrarySearchIssueDetailsVO> getIssueotherByStartwith(
			String searchTextVal, String locid, String accyear, String selection,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getIssueotherByStartwith( searchTextVal, locid,  accyear,selection,custdetails);
	}

	@Override
	public List<CategoryTypeVO> getSubCategoryTypeName(String categoryName,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryTypeName(categoryName,custdetails);
	}
	
	@Override
	public List<CategoryTypeVO> getSubCategoryList(String catcode,String subcatcode,String status,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryList(catcode,subcatcode,status,custdetails);
	}
	
	@Override
	public List<CategoryTypeVO> getbystatusList(String catcode,String subcatcode,String status,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getbystatusList(catcode,subcatcode,status,custdetails);
	}
	
	@Override
	public List<CategoryTypeVO> SearchCategoryTypeList(String catcode,String subcatcode,String status,String searchname,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchCategoryTypeList(catcode,subcatcode,status,searchname,custdetails);
	}

	@Override
	public String insertSubCategoryTypeDetail3(SubCategoryTypeVO insert_SubcategoryType,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().insertSubCategoryTypeDetail3(insert_SubcategoryType,custdetails);

	}


	@Override
	public String deleteLibraryLocations(String[] librarylocid,String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.deleteLibraryLocations(librarylocid,log_audit_session,custdetails);
	}


	@Override
	public List<SubCategoryType1VO> getTabByCategoryType(String cattype, String status, String subcacode, String subcacode1,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTabByCategoryType(cattype,status,subcacode,subcacode1,custdetails);
	}

	@Override
	public List<SubCategoryType1VO> getTableBycategorytypeandSub(String cattype, String status, String category, String subcacode1,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTableBycategorytypeandSub(cattype,status,category,subcacode1,custdetails);
	}

	@Override
	public List<SubCategoryType1VO> getTableBycategorytypeandSub1(String cattype, String status, String category, String subcacode,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTableBycategorytypeandSub1(cattype,status,category,subcacode,custdetails);
	}

	@Override
	public List<SubCategoryType1VO> getTableByStatus(String status,String categorycode, String subcategorycode, String subcategory1code,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTableByStatus(status,categorycode,subcategorycode,subcategory1code,custdetails);
	}

	@Override
	public List<SubCategoryType2VO> getTabBySub2CategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTabBySub2CategoryType(cattype,status,subcategory,subcategory1,subcategory2,custdetails);
	}

	@Override
	public List<SubCategoryType2VO> getTabBySub2subCategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTabBySub2subCategoryType(cattype,status,subcategory,subcategory1,subcategory2,custdetails);
	}

	@Override
	public List<SubCategoryType2VO> getTabBySub2subCategory1Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTabBySub2subCategory1Type(cattype,status,subcategory,subcategory1,subcategory2,custdetails);
	}

	@Override
	public List<SubCategoryType1VO> searchSubCatType1(String searchname, String catcode, String subcatcode, String subcatcode1,String status,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.searchSubCatType1(searchname,catcode,subcatcode,subcatcode1,status,custdetails);
	}

	@Override
	public List<SubCategoryType2VO> getTableBySub2Status(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTableBySub2Status(cattype,status,subcategory,subcategory1,subcategory2,custdetails);
	}

	
	public List<SubCategoryTypeVO> getSubCategoryDetails3(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryDetails3(custdetails);
	}

	public ArrayList<LibrarySubscribVO> getOtherSubscribeNunmber(String loc, String subscriberType,UserLoggingsPojo custdetails) {
		return new  LibraryDAOIMPL().getOtherSubscribeNunmber(loc,subscriberType,custdetails);
	}

	public ArrayList<LibrarySubscribVO> showBlockListedData(String loc,String subscriberType, String subscriberNo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().showBlockListedData(loc,subscriberType,subscriberNo,custdetails);
	}

	@Override
	public ArrayList<CategoryTypeVO> getcategorylist(String cateid ,String status,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getcategorylist(cateid,status,custdetails);
	}


	@Override
	public ArrayList<LibraryStockEntryVO> getAccessionNoList(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getAccessionNoList(custdetails);
	}


	@Override
	public ArrayList<CategoryTypeVO> getclassdescrlist(String cateid,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao. getclassdescrlist(cateid,custdetails);
	}

	@Override
	public ArrayList<CategoryTypeVO> getlibcategorysectionlist(String cateid,
			String classid,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao. getlibcategorysectionlist(cateid,classid,custdetails);
	}

	@Override
	public ArrayList<CategoryTypeVO> getlibcategorydivisionlist(String sectionid,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao. getlibcategorydivisionlist(sectionid,custdetails);
	}

	@Override
	public String saveStockEnteryDetails(LibraryStockEntryDetailsForm libform,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao. saveStockEnteryDetails(libform,custdetails);
	}



	@Override
	public List<LibraryStockEntryVO> getAccessionNo(
			LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getAccessionNo(registrationVo,custdetails);
		
	}

	@Override
	public List<LibraryStockEntryVO> getBookIssueDetailsByAccessionNo(
			LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getBookIssueDetailsByAccessionNo(libVo,custdetails);
		
	}

	@Override
	public String insertBookIssueDetails(LibraryStockEntryVO insert_issue,UserLoggingsPojo custdetails) {
		
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertBookIssueDetails(insert_issue,custdetails);
	}

	
	public SubCategoryTypeVO editSubCategoryType3(String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editSubCategoryType3(id,custdetails);
	}

	
	@Override
	public List<SubCategoryType1VO> getSubCategory2ByCategoryAndSubCategory(String subCategoryTypeCode,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategory2ByCategoryAndSubCategory(subCategoryTypeCode,custdetails);
	}

	@Override
	public String inactiveSubCategoryType3(String[] id,SubCategoryTypeVO vo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.inactiveSubCategoryType3(id,vo,custdetails);		
	}
	
	@Override
	public List<CategoryTypeVO> getSubCategoryDetails3(String cattype,String subcatcode, String subcatcode1, String subcatcode2, String subcatcode3, String status,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryDetails3(cattype,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,custdetails);
	}
	
	@Override
	public List<CategoryTypeVO> getSubCategoryList3(String catcode,String subcatcode,String status,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryList3(catcode,subcatcode,status,custdetails);
	}
 
	@Override
	public List<SubCategoryType1VO> getSubCategory3ByCategoryAndSubCategory(String subCategoryTypeCode,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategory3ByCategoryAndSubCategory(subCategoryTypeCode,custdetails);
	}
	
	@Override
	public List<CategoryTypeVO> SearchCategoryType3List(String catcode,String subcatcode,String subcatcode1,String subcatcode2,String subcatcode3,String status,String searchname,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchCategoryType3List(catcode,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname,custdetails);
	}
	
	@Override
	public String ValidateSubcat(String subname,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.ValidateSubcat(subname,custdetails);
	}
	
	
	
	@Override
	public String ValidateSubcatupdate(String subname,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.ValidateSubcatupdate(subname,custdetails);
	}
	
	@Override
	public String ValidateSubcat3(String subname,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.ValidateSubcat3(subname,custdetails);
	}

	@Override
	public List<SubCategoryType2VO> getTabBySub2subCategory2Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTabBySub2subCategory2Type(cattype,status,subcategory,subcategory1,subcategory2,custdetails);
	}

	@Override
	public List<SubCategoryType2VO> searchSubCatType2(
			String searchname, String categorytype, String subcategorytype, String subcategorytype1, String subcategorytype2,String status,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.searchSubCatType2(searchname,categorytype,subcategorytype,subcategorytype1,subcategorytype2,status,custdetails);
	}
	
	@Override
	public ArrayList<LibrarySearchSubscriberVO> getOthersListDetails( String location,
			String select,String academic_year,UserLoggingsPojo custdetails,String searchValue) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOthersListDetails(location,select,academic_year,custdetails,searchValue);
	}
	
	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByAnyWhere(String searchTextVal,String location,String select,String accyear,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchOthersDetailsByAnyWhere(searchTextVal,location,select,accyear,custdetails);
	}
	
	@Override

	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByStartWith(String searchTextVal,String location,String select,String accyear,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchOthersDetailsByStartWith(searchTextVal,location,select,accyear,custdetails);
	}
	
	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByEndsWith(String searchTextVal,String location,String select,String accyear,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchOthersDetailsByEndsWith(searchTextVal,location,select,accyear,custdetails);
	}
	

	@Override
	public LibrarySubscribVO gotosubscribersDetails(String location, String subId, String academic_year,String subscriberType,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.gotosubscribersDetails(location,subId,academic_year,subscriberType,custdetails);
	}
	
	@Override
	public String updateSubscriberDetails(LibrarySubscribVO resultData,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.updateSubscriberDetails(resultData,custdetails);
	}

	public LibrarySubscribVO IssueStatementBySubScriberType(String location,String subId,String academic_year,String subscriberType,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.IssueStatementBySubScriberType(location,subId,academic_year,subscriberType,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> IssueStatementTable(String location, String subId, String academic_year,
			String subscriberType,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.IssueStatementTable(location,subId,academic_year,subscriberType,custdetails);
	}

	@Override
	public LibrarySubscribVO issuestatementissue(String subId,String issueId,String subscriberType,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.issuestatementissue(subId,issueId,subscriberType,custdetails);
	}
	@Override
	public List<LibraryStockEntryDetailsForm> getStockEntryBookList(UserLoggingsPojo custdetails, StockEntryVo vo) {
	LibraryDAO dao=new LibraryDAOIMPL();
	return dao.getStockEntryBookList(custdetails,vo);
	}

	@Override
	public LibraryStockEntryDetailsForm editStockEntryDetail(String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editStockEntryDetail(id,custdetails);
	}

	public String blockTheSubscriber(String subscriberNo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().blockTheSubscriber( subscriberNo,custdetails);
	}

	public ArrayList<LibrarySubscribVO> getStaffRegId(String loc, java.lang.String searchterm,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getStaffRegId(loc,searchterm,custdetails);
	}

	public String duplicateDataCheck(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().duplicateDataCheck(pojo,custdetails);
	}
	
	@Override
	public String activeSubCategoryType(String[] id,SubCategoryTypeVO vo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.activeSubCategoryType(id,vo,custdetails);
		
	}
	
	@Override
	public String activeSubCategoryType3(String[] id,SubCategoryTypeVO vo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.activeSubCategoryType3(id,vo,custdetails);
		
	}

	public ArrayList<LibrarySubscribVO> getBlockListedStaffData(String accyear,String location,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getBlockListedStaffData( accyear, location,custdetails);
	}

	public ArrayList<LibrarySubscribVO> getBlockListedStudentData(String accyear, String location,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getBlockListedStudentData( accyear,  location,custdetails);
	}

	public ArrayList<LibrarySubscribVO> getBlockListedOtherData(String accyear,String location,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getBlockListedOtherData( accyear, location,custdetails);
	}

	public String unblockSubscriber(String id,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().unblockSubscriber(id,custdetails);
	}

	@Override
	public java.lang.String validateStockEnteryDetails(java.lang.String accno,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.validateStockEnteryDetails(accno,custdetails);
	}

	public ArrayList<CategoryTypeVO> getCategoryListBySearch(String cateid, String status, String searchname,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.getCategoryListBySearch(cateid,status,searchname,custdetails);
	}

	@Override
	public java.lang.String insertBookReturnDetails(LibraryStockEntryVO insert_issue,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertBookReturnDetails(insert_issue,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getBookReturnDetailsByAccessionNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getBookReturnDetailsByAccessionNo(libVo,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getAccessionNoByIssue(LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getAccessionNoByIssue(registrationVo,custdetails);
	}


	@Override
	public java.lang.String activeSubCategoryType1(java.lang.String[] id,
			SubCategoryType1VO vo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.activeSubCategoryType1(id,vo,custdetails);
		
	}

	@Override
	public java.lang.String activeSubCategoryType2(java.lang.String[] id,
			SubCategoryType2VO vo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.activeSubCategoryType2(id,vo,custdetails);
		
	}

	@Override
	public java.lang.String activeCategoryType(java.lang.String[] id,
			CategoryTypeVO vo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.activeCategoryType(id,vo,custdetails);
		
	}

	@Override
	public LibrarySubscribVO GOtOIssueReturns(String subId,String subscriberType,String issueId,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		  return  dao.GOtOIssueReturns(subId,subscriberType,issueId,custdetails);
	}

	@Override
	public java.lang.String publisherSettings(LibraryStockEntryVO obj,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.publisherSettings(obj,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getPublisherSettingList(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getPublisherSettingList(custdetails);	
		}

	@Override
	public LibraryStockEntryVO editpublisherSetting(java.lang.String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editpublisherSetting(id,custdetails);
	}

	@Override
	public java.lang.String deletepublisherSetting(String deleteId[],String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.deletepublisherSetting(deleteId,log_audit_session,custdetails);
	}

	@Override
	public java.lang.String validationpubsettings(String pub, String address, String email, String telphone, String mobilenum,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.validationpubsettings(pub,address,email,telphone,mobilenum,custdetails);
		
	}

	@Override
	public ArrayList<ReportMenuVo> getLibraryLocation(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getLibraryLocation(custdetails);
	}

	@Override
	public java.lang.String TransferStudent(java.lang.String[] subscriberId,
			java.lang.String locid,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.TransferStudent(subscriberId,locid,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTranferStudentListDetails(java.lang.String academic_year, java.lang.String location,java.lang.String select, java.lang.String classname,java.lang.String sectionid, java.lang.String libloc,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTranferStudentListDetails(academic_year,location,select,classname,sectionid,libloc,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListBySection(java.lang.String academic_year, java.lang.String location,java.lang.String classname, java.lang.String sectionid,java.lang.String select,java.lang.String liblocation,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao. getTrasferStudentListBySection(academic_year,location,classname,sectionid,select,liblocation,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListByClassName(java.lang.String academic_year, java.lang.String location,java.lang.String classname,java.lang.String select,java.lang.String liblocation,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao. getTrasferStudentListByClassName(academic_year,location,classname,select,liblocation,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffListDetails(java.lang.String accyear_ID, 
			java.lang.String loc_ID,java.lang.String select, java.lang.String department,java.lang.String designation,java.lang.String libloc,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTransferStaffListDetails(accyear_ID,loc_ID,select,department,designation,libloc,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO>getTransferOthersListDetails(String location, String select,String academic_year,String libloc,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTransferOthersListDetails(location,select,academic_year,libloc,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getlocationStudentList(
			java.lang.String libloc, java.lang.String select,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getlocationStudentList(libloc,select,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getliblocationstafflist(
			java.lang.String libloc, java.lang.String select,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getliblocationstafflist(libloc,select,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getliblocatinotherlist(java.lang.String academic_year, java.lang.String location,java.lang.String libloc, java.lang.String select,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getliblocatinotherlist(academic_year,location,libloc,select,custdetails);
	}

	@Override
	public List<LibrarySearchSubscriberVO> TransferSubscriberbySearch(java.lang.String searchTextVal, java.lang.String location,
			java.lang.String academic_year, java.lang.String liblocid,java.lang.String select,java.lang.String classname,java.lang.String sectionid,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.TransferSubscriberbySearch(searchTextVal,location,academic_year,liblocid,select,classname,sectionid,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyStaffSearch(java.lang.String searchTextVal, java.lang.String location,java.lang.String liblocid,java.lang.String select,java.lang.String department,
			java.lang.String designation,java.lang.String accyear,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.TransferSubscriberbyStaffSearch(searchTextVal,location,liblocid,select,department,designation,accyear,custdetails);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyotherSearch(java.lang.String searchTextVal, java.lang.String location,
			java.lang.String select, java.lang.String liblocid,java.lang.String accyear,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.TransferSubscriberbyotherSearch(searchTextVal,location,select,liblocid,accyear,custdetails);
	}

	@Override
	public java.lang.String addSupplierSettings(LibraryStockEntryVO obj,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.addSupplierSettings(obj,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getSupplierSettingList(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSupplierSettingList(custdetails);
	}

	@Override
	public LibraryStockEntryVO editSupplierSetting(java.lang.String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editSupplierSetting(id,custdetails);
	}

	@Override
	public java.lang.String deleteSupplierSetting(java.lang.String[] deleteId,String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.deleteSupplierSetting(deleteId,log_audit_session,custdetails);
	}

	@Override
	public java.lang.String validationsubsettings(java.lang.String suplier,
			java.lang.String supadd, java.lang.String emailid, java.lang.String telephone, java.lang.String supnum,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.validationsubsettings(suplier,supadd,emailid,telephone,supnum,custdetails);
	}

	@Override
	public List<TeacherVo> othersSearchbyId(TeacherVo registrationVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.othersSearchbyId(registrationVo,custdetails);
	}


	@Override
	public ArrayList<LibraryStockEntryVO> publisherDetailsSearch(
			java.lang.String searchTextVal, java.lang.String pub,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.publisherDetailsSearch(searchTextVal,pub,custdetails);
	}

	@Override
	public ArrayList<LibraryStockEntryVO> SupplierDetailsSearch(
			java.lang.String searchTextVal, java.lang.String sup,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SupplierDetailsSearch(searchTextVal,sup,custdetails);
	}


	public List<LibraryStockEntryVO> getStudentIssueDetailsBySubscriberNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStudentIssueDetailsBySubscriberNo(libVo,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getTeacherIssueDetails(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeacherIssueDetails(libVo,custdetails);
	}

	@Override
	public java.lang.String insertBookReservationDetails(
			LibraryStockEntryVO insert_issue,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertBookReservationDetails(insert_issue,custdetails);
	}

	@Override
	public List<LibraryStockEntryDetailsForm> getReservationListDetails(UserLoggingsPojo custdetails, LibraryVO vo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getReservationListDetails(custdetails,vo);
	}
	@Override
	public List<LibraryStockEntryVO> getOtherIssueDetails(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOtherIssueDetails(libVo,custdetails);
	}


	@Override
	public List<LibraryStockEntryVO> getBookReservationDetailsByAccNo(
			LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getBookReservationDetailsByAccNo(libVo,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getAccessionList(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getAccessionList(custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getTeachSubscriberName(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeachSubscriberName(libVo,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getStuSubscriberName(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStuSubscriberName(libVo,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getStudentSubNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStudentSubNo(libVo,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getTeacherSubNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeacherSubNo(libVo,custdetails);
	}
	@Override
	public List<LibraryStockEntryVO> getStuAccessionNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStuAccessionNo(libVo,custdetails);
	}
	
	@Override
	public List<LibraryStockEntryVO> getTeachAccessionNo(
			LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeachAccessionNo(libVo,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getOtherSubName(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOtherSubName(libVo,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getOtherSubNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOtherSubNo(libVo,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getOtherAccessionNo(
			LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOtherAccessionNo(libVo,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getFromDateList(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getFromDateList(libVo,custdetails);
	}

	@Override

	public List<LibraryStockEntryVO> getToDateList(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getToDateList(libVo,custdetails);
	}


	@Override

	public ArrayList<LibrarySearchSubscriberVO> IssueReturnTable(java.lang.String location, java.lang.String subId,
			java.lang.String academic_year, java.lang.String subscriberType,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.IssueReturnTable(location,subId,academic_year,subscriberType,custdetails);
	}

	@Override

	public java.lang.String addGeneralSettings(LibraryStockEntryVO obj,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.addGeneralSettings(obj,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getGenarelSettingList(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getGenarelSettingList(custdetails);
	}

	@Override
	public LibraryStockEntryVO editGenarelSetting(java.lang.String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editGenarelSetting(id,custdetails);
	}

	@Override
	public java.lang.String editGenarelSetting(java.lang.String[] deleteId,String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editGenarelSetting(deleteId,log_audit_session,custdetails);
	}

	@Override
	public ArrayList<LibraryStockEntryVO> GenarelDetailsSearch(
			java.lang.String searchTextVal,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.GenarelDetailsSearch(searchTextVal,custdetails);
	}
	
	public LibrarySubscribVO IssueReturnBySubScriberType(String location,String subId,String academic_year,String subscriberType,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.IssueReturnBySubScriberType(location,subId,academic_year,subscriberType,custdetails);
	}

	@Override
	public LibraryStockEntryVO editReservationBook(java.lang.String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editReservationBook(id,custdetails);
	}


	@Override
	public ArrayList<LibraryVO> getcodeList(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getcodeList(custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getcodeName(LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getcodeName(registrationVo,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getCodeByCodeName(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getCodeByCodeName(libVo,custdetails);
	}

	@Override
	public java.lang.String updateBookReservationDetails(
			LibraryStockEntryVO insert_issue,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.updateBookReservationDetails(insert_issue,custdetails);
	}

	@Override
	public java.lang.String deleteReservedBook(java.lang.String[] librarylocid,String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.deleteReservedBook(librarylocid,log_audit_session,custdetails);
	}

	@Override
	public boolean validateReservedBook(LibraryStockEntryVO reserve,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.validateReservedBook(reserve,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListDetails(String academic_year, String location,
			String select, String classname, String sectionid,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getMostWantedStudentListDetails(academic_year,location,select,classname,sectionid,custdetails);
	}

	public List<LibrarySearchSubscriberVO> SearchMostWantedStudentDetailsByAnyWhere(String searchTextVal,String location,
			String academic_year, String select, String startwith, java.lang.String classname, java.lang.String sectionid,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().SearchMostWantedStudentDetailsByAnyWhere(searchTextVal,location,academic_year,select,startwith,classname,sectionid,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListByClassName(String academic_year,
			String location, String classname, String sectionid, String select,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getMostWantedStudentListByClassName(academic_year,location,classname,sectionid,select,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListBySection(String academic_year, String location,
			String classname, String sectionid, String select,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getMostWantedStudentListBySection(academic_year,location,classname,sectionid,select,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffListDetails(String academic_year, String location,
			String select, String department, String designation,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getMostWantedStaffListDetails(academic_year,location,select,department,designation,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchMostWantedStaffDetailsByAnyWhere(String searchTextVal,
			String location, String select, String startwith,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().SearchMostWantedStaffDetailsByAnyWhere(searchTextVal,location,select,startwith,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffdetailsByDepartment(String accyear_ID, String loc_ID,
			String department, String designation, java.lang.String select,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getMostWantedStaffdetailsByDepartment(accyear_ID,loc_ID,department,designation,select,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffdetailsByDesignation(String accyear_ID, String loc_ID,
			String department, String designation, String select,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getMostWantedStaffdetailsByDesignation(accyear_ID,loc_ID,department,designation,select,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedOthersListDetails(String location, String select,
			String academic_year,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getMostWantedOthersListDetails(location,select,academic_year,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchMostWantedOthersDetailsByAnyWhere(String searchTextVal,
			String location, String select, String startwith, UserLoggingsPojo custdetails){
		return new LibraryDAOIMPL().SearchMostWantedOthersDetailsByAnyWhere(searchTextVal,location,select,startwith,custdetails);
	}
	@Override
	public java.lang.String savejournalsubscriptiondetail(
			LibraryJournalSubscriptionVo obj,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.savejournalsubscriptiondetail(obj,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getAllBookDetails(pojo,custdetails);
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByaccNoandTitleAnyWhere(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().SearchBookSearchByaccNoandTitleAnyWhere(pojo,custdetails);
	}


	@Override
	public List<LibraryJournalSubscriptionVo> getJournalSubscriptionList(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getJournalSubscriptionList(custdetails);
	}

	@Override
	public LibraryJournalSubscriptionVo editeLibraryJournalSubscription(
			java.lang.String id,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editeLibraryJournalSubscription(id,custdetails);
	}

	@Override
	public java.lang.String deleteJournalSbscription(java.lang.String[] deleteId,String log_audit_session,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.deleteJournalSbscription(deleteId,log_audit_session,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookPublisherDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getAllBookPublisherDetails(pojo,custdetails);
	}
	@Override
	public List<LibraryJournalSubscriptionVo> getJournalSubscriptioncodelist(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getJournalSubscriptioncodelist(custdetails);
	}

	@Override
	public List<LibraryJournalSubscriptionVo> getnamelist(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getnamelist(custdetails);
	}

	@Override
	public ArrayList<LibraryJournalSubscriptionVo> journalsubscriptionDetailsSearch(
			java.lang.String searchTextVal,java.lang.String name,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.journalsubscriptionDetailsSearch(searchTextVal,name,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getstockEntryList(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.getstockEntryList(locId,itemId,regdateId,booktitle,authorId,pubId,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getlocationlist(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.getlocationlist(locId,itemId,regdateId,booktitle,authorId,pubId,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getitemlistList(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.getitemlistList(custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> booklist(UserLoggingsPojo custdetails,String itemType) {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.booklist(custdetails,itemType);
	}

	@Override
	public List<LibraryStockEntryVO> authorlist(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.authorlist(custdetails);
	}
	
	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getSubscriberDetailStudentExcelReport(
			LibrarySearchIssueDetailsVO obj,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubscriberDetailStudentExcelReport(obj,custdetails);
	}
	
	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getStaffSubscriberDetailReport(
			LibrarySearchIssueDetailsVO obj,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStaffSubscriberDetailReport(obj,custdetails);
	}


	@Override
	public List<LibraryStockEntryVO> getReservationListReport(String location, String accId, String subId, String accNo, String bookId, String fromdat, String todate,String date,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.getReservationListReport(location,accId,subId,accNo,bookId,fromdat,todate,date,custdetails);
	}

	@Override
	public List<LibraryStockEntryDetailsForm> getReservationAccNo(String subtype, String accyear,
			UserLoggingsPojo custdetails,String locId) {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.getReservationAccNo(subtype,accyear,custdetails,locId);
	}

	@Override
	public List<LibraryStockEntryDetailsForm> getbooktitleList(String subtype, String accyear, String accNo,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getbooktitleList(subtype,accyear,accNo,custdetails);
	}
	
	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getOtherSubscriberDetailReport(
			LibrarySearchIssueDetailsVO obj,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOtherSubscriberDetailReport(obj,custdetails);
	}

	@Override
	public List<LibraryStockEntryVO> getNewArrivalListReport(
			java.lang.String checkedVal,java.lang.String fromdate,java.lang.String toDate,UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getNewArrivalListReport(checkedVal,fromdate,toDate,custdetails);
	}

	
	public List<LibrarySearchSubscriberVO> SearchBookSearchByPublisherAnyWhere(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().SearchBookSearchByPublisherAnyWhere(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookItemTypeDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getAllBookItemTypeDetails(pojo,custdetails);
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByItemTypeAnyWhere(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().SearchBookSearchByItemTypeAnyWhere(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookDDCDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getAllBookDDCDetails(pojo,custdetails);
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByDDCAnyWhere(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().SearchBookSearchByDDCAnyWhere(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookContentDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getAllBookContentDetails(pojo,custdetails);
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByContentAnyWhere(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().SearchBookSearchByContentAnyWhere(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookLanguageDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getAllBookLanguageDetails(pojo,custdetails);
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByLanguageAnyWhere(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().SearchBookSearchByLanguageAnyWhere(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookSupplierDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getAllBookSupplierDetails(pojo,custdetails);
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchBySupplierAnyWhere(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().SearchBookSearchBySupplierAnyWhere(pojo,custdetails);
	}

	public LibraryStockEntryDetailsForm gotostockDetails(String id,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().gotostockDetails(id,custdetails);
	}

	public LibrarySubscribVO IssueStatementByStockEntryId(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().IssueStatementByStockEntryId(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> IssueStatementTableByStockEntryId(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().IssueStatementTableByStockEntryId(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> ReturnStatementTableByStockEntryId(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().ReturnStatementTableByStockEntryId(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getOverDueStatement(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getOverDueStatement(pojo,custdetails);
	}

	public List<LibrarySubscribVO> getClassByLibraryLocation(String locationid,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getClassByLibraryLocation(locationid,custdetails);
	}

	public List<LibrarySubscribVO> getLibraryClassSection(String classidVal,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getLibraryClassSection(classidVal,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentOverDueStatementByClass(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getStudentOverDueStatementByClass(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentOverDueStatementBySection(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getStudentOverDueStatementBySection(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchOverDueStudentDetailsByAnyWhere(
			LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().SearchOverDueStudentDetailsByAnyWhere(pojo,custdetails);
	}

	public ArrayList<LibraryStockEntryDetailsForm> getStudentOverDueByOrderwise(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getStudentOverDueByOrderwise(pojo,custdetails);
	}

	public List<LibrarySearchIssueDetailsVO> getAllOverDueListDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getAllOverDueListDetails(pojo,custdetails);
	}

	public ArrayList<LibrarySubsciberDetailsPojo> getAllIssueReturnDetails(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getAllIssueReturnDetails(pojo,custdetails);
	}

	public List<LibrarySubsciberDetailsPojo> IndividualSearchInIssueReturnStatement(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().IndividualSearchInIssueReturnStatement(pojo,custdetails);
	}

	public List<LibraryStockEntryVO> getBookIssueReturnDetailsByAccessionNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getBookIssueReturnDetailsByAccessionNo(libVo,custdetails);
	}

	@Override
	public ArrayList<LibraryStockEntryVO> getJournalNameList(
			java.lang.String accyear,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getJournalNameList(accyear,custdetails);
	}

	@Override  
	public List<LibraryJournalSubscriptionVo> getJournalListReport(
			java.lang.String checkedVal, java.lang.String fromdate,
			java.lang.String toDate,String accyear,String journalName,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getJournalListReport(checkedVal,fromdate,toDate,accyear,journalName,custdetails);
	}
	
	public ArrayList<LibrarySearchSubscriberVO> getAllBookDetailsDownloadandPrint(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getAllBookDetailsDownloadandPrint(pojo,custdetails);
	}

	public List<LibraryStockEntryVO> getIssueReturnAccessionNo(LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getIssueReturnAccessionNo(registrationVo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffdetailsByDepartment(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getTransferStaffdetailsByDepartment(pojo,custdetails);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffdetailsByDesignation(LibrarySubsciberDetailsPojo pojo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getTransferStaffdetailsByDesignation(pojo,custdetails);
	}

	public List<LibraryStockEntryVO> getAccessionNoByIssueStatus(
			LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails) {
		return new LibraryDAOIMPL().getAccessionNoByIssueStatus(registrationVo,custdetails);
	}

	@Override
	public ArrayList<LibraryItems> getLibItemsDetails(UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return  dao.getLibItemsDetails(custdetails);
	}

	@Override
	public LibraryItems getSingleLibItemsDetails(String id, UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSingleLibItemsDetails(id,custdetails);
	}

	@Override
	public java.lang.String InsertLibraryItem(LibraryItems obj, UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.InsertLibraryItem(obj,custdetails);
	}

	@Override
	public List<LibraryItems> getLibraryItemByJS(LibraryItems obj, UserLoggingsPojo custdetails) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getLibraryItemByJS(obj,custdetails);
	}
	
}
	

