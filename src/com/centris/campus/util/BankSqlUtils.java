package  com.centris.campus.util;

public class BankSqlUtils {

	public static final String SAVE_BANK = "INSERT INTO `campus_bank` (`BankId`,`BankName`,`BankShortName`,`createdBy`,`createdDate`) VALUES(?,?,?,?,?)";
	public static final String CHECK_BANK_NAME="SELECT COUNT(*)`BankName`,`isActive` FROM campus_bank WHERE BankName=?";
	public static final String GET_BANK_LIST = "SELECT `BankId`,`BankName`,`BankShortName`,`isActive`,case when Reason is null then '' when Reason=''then '' else Reason end Reason FROM campus_bank where isActive='Y' order by BankName ASC";
	public static final String GET_BANK_SINGLE_RECORD = "SELECT `BankId`,`BankName`,`BankShortName` FROM campus_bank where BankId=?";
	public static final String UPDATE_BANK = "update campus_bank set BankName=?,BankShortName=?,modifyBy=?,modifyDate=? where BankId=?";

	public static final String REMOVE_BANK = "update campus_bank set isActive=?,Reason=? where BankId=?";
	public static final String GET_BANK_SEARCH_LIST = "SELECT `BankId`,`BankName`,`BankShortName`,`isActive`,case when Reason is null then ''when Reason=''then '' else Reason end Reason FROM campus_bank where isActive=? and (BankName like ? or BankShortName like ?) order by BankName ASC";

	
}
