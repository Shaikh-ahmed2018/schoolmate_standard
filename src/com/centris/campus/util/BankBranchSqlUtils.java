package com.centris.campus.util;

public class BankBranchSqlUtils 
{
	public static final String GET_BANK_BRANCH_NAME ="SELECT COUNT(*)`IFSCCode`,`isActive` FROM campus_bank_branch  WHERE IFSCCode=?";
	public static final String SAVE_BANK_BRANCH = "INSERT INTO `campus_bank_branch` (`BranchId`,`BranchName`,`BranchShortName`,`IFSCCode`,`BranchAddress`,`BankId`,`createdBy`,`createdDate`) VALUES (?,?,?,?,?,?,?,now())";
	public static final String GET_BRANCH_LIST = "SELECT brh.BranchId,brh.BranchName,brh.BranchShortName,brh.IFSCCode,brh.BranchAddress,brh.isActive,bnk.`BankName`,case when brh.Reason is null then '' when brh.Reason='' then '' else brh.Reason end Reason FROM campus_bank_branch brh JOIN campus_bank bnk ON bnk.`BankId`= brh.`BankId` AND bnk.isActive='Y' WHERE brh.isActive='Y' order by bnk.`BankName`,brh.BranchName";
    public static final String REMOVE_BRANCH_DETAILS="UPDATE  campus_bank_branch  SET `isActive`= ?,`Reason`=? WHERE BranchId=?";
	public static final String GET_BRANCH_DETAILS = "SELECT `BranchName`,`BranchShortName`,`IFSCCode`,`BranchAddress`,`BankId` FROM campus_bank_branch WHERE `BranchId`=?"; 
	public static final String UPDATE_BANK_BRANCH = "UPDATE `campus_bank_branch` SET  BranchName=?, BranchShortName=?, IFSCCode=?,BranchAddress=?,modifyBy=? WHERE BankId=? and BranchId=? ";
	public static final String GET_BRANCH_ALL = "SELECT `BranchId`,BranchName FROM campus_bank_branch WHERE BankId=?";
	public static final String GET_BRANCH_IFSC = "SELECT IFSCCode FROM campus_bank_branch WHERE BranchId=?";
	public static final String GET_SEARCH_BRANCH_LIST ="SELECT brh.BranchId,brh.BranchName,brh.BranchShortName,brh.IFSCCode,brh.BranchAddress,brh.isActive,bnk.`BankName`,case when brh.Reason is null then '' when brh.Reason='' then '' else brh.Reason end Reason FROM campus_bank_branch brh JOIN campus_bank bnk ON bnk.`BankId`= brh.`BankId` AND bnk.isActive='Y' and  brh.`isActive`=?  where (brh.BranchName like ? or brh.BranchShortName like ? or brh.IFSCCode like ? or bnk.BankName like ?) ORDER BY bnk.`BankName`,brh.BranchName";

}
