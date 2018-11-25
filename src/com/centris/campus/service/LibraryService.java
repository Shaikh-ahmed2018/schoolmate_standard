package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LibraryStockEntryDetailsForm;
import com.centris.campus.pojo.LibraryItems;
import com.centris.campus.pojo.LibraryLocationPojo;
import com.centris.campus.pojo.LibrarySubsciberDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
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

public interface LibraryService {

	String insertCategoryTypeDetail(CategoryTypeVO insert_categoryType,UserLoggingsPojo custdetails);

	List<CategoryTypeVO> getCategoryDetails(UserLoggingsPojo custdetails);

	List<StudentRegistrationVo> studentSearchbyadmissionNo(StudentRegistrationVo registrationVo, String locid,UserLoggingsPojo custdetails);

	List<TeacherVo> teacherSearchbyId(TeacherVo teacherVo,UserLoggingsPojo custdetails);

	CategoryTypeVO editCategoryType(String id,UserLoggingsPojo custdetails);
	
	SubCategoryTypeVO editSubCategoryType(String id,UserLoggingsPojo custdetails);

	String inactiveCategoryType(String[] catIdlist, String log_audit_session,UserLoggingsPojo custdetails);


	String insertSubCategoryType1Detail(SubCategoryType1VO sub1,UserLoggingsPojo custdetails);

	List<SubCategoryType1VO> getSubCategoryType1Details(UserLoggingsPojo custdetails);

	SubCategoryType1VO editSubCategoryType1(String id,UserLoggingsPojo custdetails);

	List<SubCategoryTypeVO> getSubCategoryByCategory(String categoryCode,UserLoggingsPojo custdetails);


	ArrayList<LibrarySearchIssueDetailsVO> getStudentIssuedList(String locid, String accyear,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> getStudentListDetails(String academic_year, String location, String select, String classname, String sectionid,UserLoggingsPojo custdetails, String searchValue);


	String inactiveSubCategoryType1(String[] id,String log_audit_session,UserLoggingsPojo custdetails);

	boolean validateSubcategoryType1(SubCategoryType1VO sub1,UserLoggingsPojo custdetails);



	ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentClassList(String locid, String accyear, String classname,UserLoggingsPojo custdetails);


	ArrayList<LibrarySearchSubscriberVO> getStudentListByClassName(String academic_year, String location,
			String classname, String sectionid, String select,UserLoggingsPojo custdetails, String searchValue);

	String insertSubCategoryTypeDetail(SubCategoryTypeVO insert_SubcategoryType,UserLoggingsPojo custdetails);

	String inactiveSubCategoryType(String[] id, SubCategoryTypeVO vo,UserLoggingsPojo custdetails);



	ArrayList<LibrarySearchSubscriberVO> getStudentListBySection(String academic_year, String location,
			String classname, String sectionid, String select,UserLoggingsPojo custdetails, String searchValue);

	List<LibrarySearchSubscriberVO> searchsubscriberList(String searchTextVal, String academic_year, String location,UserLoggingsPojo custdetails);


	ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentSectionList(String locid, String accyear, String classname, String sectionnm,UserLoggingsPojo custdetails);

	LibraryLocationVO editLibraryLocation(String id,UserLoggingsPojo custdetails);

	String updateLibLocations(LibraryLocationPojo insert_libLoc,UserLoggingsPojo custdetails);

	String insertLibraryLocations(LibraryLocationPojo insert_libLoc,UserLoggingsPojo custdetails);

	ArrayList<LibraryLocationPojo> getLibLocationsDetails(String location,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> getStaffListDetails(String accyear_ID, String loc_ID, String select, String department, String designation,UserLoggingsPojo custdetails, String searchValue);

	ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDepartment(String accyear_ID, String Loc_ID, String department,
			String designation,UserLoggingsPojo custdetails, String searchValue);

	ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDesignation(String accyear_ID, String loc_ID, String department,
		String designation,UserLoggingsPojo custdetails, String searchValue);

	ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByAnyWhere(String searchTextVal,String location,String select,String department,String designation,String accyear,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByStartWith(String searchTextVal,String location,String select, String department, String designation, String accyear,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByEndsWith(String searchTextVal,String location,String select, String department, String designation, String accyear,UserLoggingsPojo custdetails);
	

	ArrayList<LibrarySearchSubscriberVO> getStaffListFilterByLocationAndAcyearid(String accyear_ID, String loc_ID,UserLoggingsPojo custdetails);

	List<SubCategoryType1VO> getSubCategory1ByCategoryAndSubCategory(String subCategoryTypeCode,UserLoggingsPojo custdetails);

	String insertSubCategoryType2Detail(SubCategoryType2VO sub2,UserLoggingsPojo custdetails);

	String deleteLibraryLocations(String[] librarylocid, String log_audit_session,UserLoggingsPojo custdetails);

	List<SubCategoryType2VO> getSubCategoryType2Details(UserLoggingsPojo custdetails);

	SubCategoryType2VO editSubCategoryType2(String id,UserLoggingsPojo custdetails);

	ArrayList<LibraryLocationVO> getSchoolLocations(String id,UserLoggingsPojo custdetails);

	List<CategoryTypeVO> getSubCategoryDetails(UserLoggingsPojo custdetails);
	
	List<CategoryTypeVO> getSubCategoryDetails1(UserLoggingsPojo custdetails);
	
	
	List<CategoryTypeVO> getSubCategoryDetails(String cattype,String status,UserLoggingsPojo custdetails);


	ArrayList<LibrarySearchIssueDetailsVO> getTeacherList(String locid,String accyear,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchIssueDetailsVO> getTeacherDeptList(String locid,String accyear, String dept,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchIssueDetailsVO> getTeacherDesgList(String locid,String accyear, String dept, String desg,UserLoggingsPojo custdetails);

	List<LibrarySearchIssueDetailsVO> getIssueByStartwith(LibrarySearchIssueDetailsVO vo, String selection,UserLoggingsPojo custdetails);


	boolean validateLibLocationUpdate(LibraryLocationVO lib,UserLoggingsPojo custdetails);

	List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByAnyWhere(String searchTextVal,String location, String academic_year, String select,String classname, String sectionid,UserLoggingsPojo custdetails);

	List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByStartWith(String searchTextVal, String location, String select,String classname,String sectionid, String academic_year,UserLoggingsPojo custdetails);

	String inactiveSubCategoryType2(String[] id, String log_audit_session,UserLoggingsPojo custdetails);


	ArrayList<LibrarySearchIssueDetailsVO> getOthersList(String locid,String accyear,UserLoggingsPojo custdetails);

	List<LibrarySearchIssueDetailsVO> getIssueotherByStartwith(	String searchTextVal, String locid, String accyear, String selection,UserLoggingsPojo custdetails);


	List<CategoryTypeVO> getSubCategoryTypeName(String categoryName,UserLoggingsPojo custdetails);
	
	List<CategoryTypeVO> getSubCategoryList(String catcode,String subcatcode,String status,UserLoggingsPojo custdetails);
	
	List<CategoryTypeVO> getbystatusList(String catcode,String subcatcode,String status, UserLoggingsPojo custdetails) ;


	List<CategoryTypeVO> SearchCategoryTypeList(String catcode,String subcatcode,String status,String searchname, UserLoggingsPojo custdetails) ;


	List<SubCategoryType1VO> getTabByCategoryType(String cattype, String status, String subcacode, String subcacode1,UserLoggingsPojo custdetails);

	List<SubCategoryType1VO> getTableBycategorytypeandSub(String cattype, String status, String category, String subcacode1,UserLoggingsPojo custdetails);

	String insertSubCategoryTypeDetail3(SubCategoryTypeVO insert_SubcategoryType,UserLoggingsPojo custdetails);
	
	List<SubCategoryTypeVO> getSubCategoryDetails3(UserLoggingsPojo custdetails);
	
	SubCategoryTypeVO editSubCategoryType3(String id,UserLoggingsPojo custdetails);

	List<SubCategoryType1VO> getSubCategory2ByCategoryAndSubCategory(String subCategoryTypeCode,UserLoggingsPojo custdetails);

	String inactiveSubCategoryType3(String[] id, SubCategoryTypeVO vo,UserLoggingsPojo custdetails);
	
	List<CategoryTypeVO> getSubCategoryDetails3(String cattype,String subcatcode, String subcatcode1, String subcatcode2, String subcatcode3, String status,UserLoggingsPojo custdetails);

	List<CategoryTypeVO> getSubCategoryList3(String catcode,String subcatcode,String status,UserLoggingsPojo custdetails);

	ArrayList<CategoryTypeVO> getcategorylist(String cateid,String status,UserLoggingsPojo custdetails);


	List<SubCategoryType1VO> getTableBycategorytypeandSub1(String cattype, String status, String category, String subcacode,UserLoggingsPojo custdetails);

	List<SubCategoryType1VO> getTableByStatus(String status, String categorycode, String subcategorycode, String subcategory1code,UserLoggingsPojo custdetails);

	List<SubCategoryType2VO> getTabBySub2CategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails);

	List<SubCategoryType2VO> getTabBySub2subCategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails);

	List<SubCategoryType2VO> getTabBySub2subCategory1Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails);

	List<SubCategoryType1VO> searchSubCatType1(String searchname, String catcode, String subcatcode, String subcatcode1, String status,UserLoggingsPojo custdetails);

	List<SubCategoryType2VO> getTableBySub2Status(String status, String categorycode, String subcategorycode, String subcategory1code, String subcategory2code,UserLoggingsPojo custdetails);

	ArrayList<LibraryStockEntryVO> getAccessionNoList(UserLoggingsPojo custdetails);

	ArrayList<CategoryTypeVO> getclassdescrlist(String cateid,UserLoggingsPojo custdetails);

	ArrayList<CategoryTypeVO> getlibcategorysectionlist(String cateid,String classid,UserLoggingsPojo custdetails);

	ArrayList<CategoryTypeVO> getlibcategorydivisionlist(String sectionid,UserLoggingsPojo custdetails);


	String saveStockEnteryDetails(LibraryStockEntryDetailsForm libform,UserLoggingsPojo custdetails);
	

	List<LibraryStockEntryVO> getAccessionNo(LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getBookIssueDetailsByAccessionNo(
			LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<SubCategoryType1VO> getSubCategory3ByCategoryAndSubCategory(String subCategoryTypeCode,UserLoggingsPojo custdetails);
	
	List<CategoryTypeVO> SearchCategoryType3List(String catcode,String subcatcode,String subcatcode1,String subcatcode2,String subcatcode3,String status,String searchname, UserLoggingsPojo custdetails) ;

	String ValidateSubcat(String subname,UserLoggingsPojo custdetails);

	
	String ValidateSubcatupdate(String subname,UserLoggingsPojo custdetails);


	String ValidateSubcat3(String subname,UserLoggingsPojo custdetails);





	String insertBookIssueDetails(LibraryStockEntryVO insert_issue,UserLoggingsPojo custdetails);




	List<SubCategoryType2VO> getTabBySub2subCategory2Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2,UserLoggingsPojo custdetails);


	List<SubCategoryType2VO> searchSubCatType2(String searchname, String categorytype, String subcategorytype, String subcategorytype1, String subcategorytype2, String status,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> getOthersListDetails(String location, String select, String academic_year,UserLoggingsPojo custdetails, String searchValue);

	ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByAnyWhere(String searchTextVal,String location, String select, String accyear,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByStartWith(String searchTextVal,String location,String select,String accyear,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByEndsWith(String searchTextVal,String location, String select,String accyear,UserLoggingsPojo custdetails);

	LibrarySubscribVO gotosubscribersDetails(String location, String subId, String academic_year,
			String subscriberType,UserLoggingsPojo custdetails);

	String updateSubscriberDetails(LibrarySubscribVO resultData,UserLoggingsPojo custdetails);
	
	String activeSubCategoryType(String[] id, SubCategoryTypeVO vo,UserLoggingsPojo custdetails);
	
	String activeSubCategoryType3(String[] id, SubCategoryTypeVO vo,UserLoggingsPojo custdetails);



	LibrarySubscribVO IssueStatementBySubScriberType(String location, String subId, String academic_year,String subscriberType,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> IssueStatementTable(String location, String subId, String academic_year,
			String subscriberType,UserLoggingsPojo custdetails);


	LibrarySubscribVO issuestatementissue(String subId,String issueId,String subscriberType,UserLoggingsPojo custdetails);

	String validateStockEnteryDetails(String accno,UserLoggingsPojo custdetails);

	String activeSubCategoryType1(String[] id, SubCategoryType1VO vo,UserLoggingsPojo custdetails);

	String activeSubCategoryType2(String[] id, SubCategoryType2VO vo,UserLoggingsPojo custdetails);

	String activeCategoryType(String[] id, CategoryTypeVO vo,UserLoggingsPojo custdetails);






	List<LibraryStockEntryDetailsForm> getStockEntryBookList(UserLoggingsPojo custdetails, StockEntryVo vo);

	LibraryStockEntryDetailsForm editStockEntryDetail(String id,UserLoggingsPojo custdetails);

	String insertBookReturnDetails(LibraryStockEntryVO insert_issue,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getBookReturnDetailsByAccessionNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getAccessionNoByIssue(LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails);




	LibrarySubscribVO GOtOIssueReturns(String subId, String subscriberType, String issueId,UserLoggingsPojo custdetails);


	String publisherSettings(LibraryStockEntryVO obj,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getPublisherSettingList(UserLoggingsPojo custdetails);

	LibraryStockEntryVO editpublisherSetting(String id,UserLoggingsPojo custdetails);

	String deletepublisherSetting(String[] deleteId, String log_audit_session,UserLoggingsPojo custdetails);

	String validationpubsettings(String pub, String address, String email, String telphone, String mobilenum,UserLoggingsPojo custdetails);

	ArrayList<ReportMenuVo> getLibraryLocation(UserLoggingsPojo custdetails);
	String TransferStudent(String[] subscriberId, String locid,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> getTranferStudentListDetails(String academic_year, String location, String select,String classname, String sectionid, String libloc,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListBySection(String academic_year, String location, String classname,String sectionid, String select, String liblocation,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListByClassName(String academic_year, String location, String classname,String select,String liblocation,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> getTransferStaffListDetails(String accyear_ID, String loc_ID, String select, String department,String designation,String libloc,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> getTransferOthersListDetails(String location, String select,String academic_year, String libloc,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> getlocationStudentList(String libloc,String select,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> getliblocationstafflist(String libloc,String select,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> getliblocatinotherlist(String academic_year, String location, String libloc, String select,UserLoggingsPojo custdetails);

	List<LibrarySearchSubscriberVO> TransferSubscriberbySearch(String searchTextVal, String location, String academic_year,String liblocid, String select,String classname,String sectionid,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyStaffSearch(String searchTextVal, String location, String liblocid,String select, String department, String designation, String accyear,UserLoggingsPojo custdetails);

	ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyotherSearch(String searchTextVal, String location, String select,String liblocid, String accyear,UserLoggingsPojo custdetails);

	String addSupplierSettings(LibraryStockEntryVO obj,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getSupplierSettingList(UserLoggingsPojo custdetails);

	LibraryStockEntryVO editSupplierSetting(String id,UserLoggingsPojo custdetails);

	String deleteSupplierSetting(String[] deleteId, String log_audit_session,UserLoggingsPojo custdetails);

	String validationsubsettings(String suplier, String supadd,String emailid,String telephone,String supnum ,UserLoggingsPojo custdetails);
	
	List<TeacherVo> othersSearchbyId(TeacherVo registrationVo,UserLoggingsPojo custdetails);


	ArrayList<LibraryStockEntryVO> publisherDetailsSearch(String searchTextVal,String pub,UserLoggingsPojo custdetails);

	ArrayList<LibraryStockEntryVO> SupplierDetailsSearch(String searchTextVal,String sup,UserLoggingsPojo custdetails);


	List<LibraryStockEntryVO> getStudentIssueDetailsBySubscriberNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getTeacherIssueDetails(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	String insertBookReservationDetails(LibraryStockEntryVO insert_issue,UserLoggingsPojo custdetails);

	List<LibraryStockEntryDetailsForm> getReservationListDetails(UserLoggingsPojo custdetails, LibraryVO vo);

	List<LibraryStockEntryVO> getOtherIssueDetails(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);



	List<LibraryStockEntryVO> getBookReservationDetailsByAccNo(
			LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getAccessionList(UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getTeachSubscriberName(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getStuSubscriberName(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getStudentSubNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getTeacherSubNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getStuAccessionNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);
	
	List<LibraryStockEntryVO> getTeachAccessionNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getOtherSubName(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getOtherSubNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getOtherAccessionNo(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getFromDateList(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getToDateList(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);



	ArrayList<LibrarySearchSubscriberVO> IssueReturnTable(String location, String subId, String academic_year,
			String subscriberType,UserLoggingsPojo custdetails);


	String addGeneralSettings(LibraryStockEntryVO obj,UserLoggingsPojo custdetails);


	List<LibraryStockEntryVO> getGenarelSettingList(UserLoggingsPojo custdetails);

	LibraryStockEntryVO editGenarelSetting(String id,UserLoggingsPojo custdetails);

	String editGenarelSetting(String[] deleteId, String log_audit_session,UserLoggingsPojo custdetails);

	ArrayList<LibraryStockEntryVO> GenarelDetailsSearch(String searchTextVal,UserLoggingsPojo custdetails);


	LibraryStockEntryVO editReservationBook(String id,UserLoggingsPojo custdetails);


	LibrarySubscribVO IssueReturnBySubScriberType(String location, String subId, String academic_year,
			String subscriberType,UserLoggingsPojo custdetails);

	ArrayList<LibraryVO> getcodeList(UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getcodeName(LibraryStockEntryVO registrationVo,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getCodeByCodeName(LibraryStockEntryVO libVo,UserLoggingsPojo custdetails);

	String updateBookReservationDetails(LibraryStockEntryVO insert_issue,UserLoggingsPojo custdetails);

	String deleteReservedBook(String[] librarylocid, String log_audit_session,UserLoggingsPojo custdetails);

	boolean validateReservedBook(LibraryStockEntryVO reserve,UserLoggingsPojo custdetails);

	String savejournalsubscriptiondetail(LibraryJournalSubscriptionVo obj,UserLoggingsPojo custdetails);

	List<LibraryJournalSubscriptionVo> getJournalSubscriptionList(UserLoggingsPojo custdetails);

	LibraryJournalSubscriptionVo editeLibraryJournalSubscription(String id,UserLoggingsPojo custdetails);

	String deleteJournalSbscription(String[] deleteId, String log_audit_session,UserLoggingsPojo custdetails);

	List<LibraryJournalSubscriptionVo> getJournalSubscriptioncodelist(UserLoggingsPojo custdetails);

	List<LibraryJournalSubscriptionVo> getnamelist(UserLoggingsPojo custdetails);

	ArrayList<LibraryJournalSubscriptionVo> journalsubscriptionDetailsSearch(String searchTextVal, String name,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getstockEntryList(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getlocationlist(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getitemlistList(UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> booklist(UserLoggingsPojo custdetails, String itemType);

	List<LibraryStockEntryVO> authorlist(UserLoggingsPojo custdetails);
	
	ArrayList<LibrarySearchIssueDetailsVO> getSubscriberDetailStudentExcelReport(LibrarySearchIssueDetailsVO obj,UserLoggingsPojo custdetails);


	ArrayList<LibrarySearchIssueDetailsVO> getStaffSubscriberDetailReport(LibrarySearchIssueDetailsVO obj,UserLoggingsPojo custdetails);




	List<LibraryStockEntryDetailsForm> getReservationAccNo(String subtype, String accyear,UserLoggingsPojo custdetails, String locId);

	List<LibraryStockEntryDetailsForm> getbooktitleList(String subtype, String accyear, String accNo,UserLoggingsPojo custdetails);
	
	ArrayList<LibrarySearchIssueDetailsVO> getOtherSubscriberDetailReport(LibrarySearchIssueDetailsVO obj,UserLoggingsPojo custdetails);


	List<LibraryStockEntryVO> getNewArrivalListReport(String checkedVal, String fromdate, String toDate,UserLoggingsPojo custdetails);

	List<LibraryStockEntryVO> getReservationListReport(String location,String accId, String subId, String accNo, String bookId,String fromdat, String todate, String date,UserLoggingsPojo custdetails);

	ArrayList<LibraryStockEntryVO> getJournalNameList(String accyear,UserLoggingsPojo custdetails);

	List<LibraryJournalSubscriptionVo> getJournalListReport(String checkedVal,
			String fromdate, String toDate, String accyear, String journalName,UserLoggingsPojo custdetails);

	ArrayList<LibraryItems> getLibItemsDetails(UserLoggingsPojo custdetails);

	LibraryItems getSingleLibItemsDetails(String id, UserLoggingsPojo custdetails);

	String InsertLibraryItem(LibraryItems obj, UserLoggingsPojo custdetails);

	List<LibraryItems> getLibraryItemByJS(LibraryItems obj, UserLoggingsPojo custdetails);


	
}
