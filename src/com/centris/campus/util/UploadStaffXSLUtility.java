package com.centris.campus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import java.util.Map;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.upload.FormFile;


import com.centris.campus.pojo.UploadStaffXlsPOJO;




public class UploadStaffXSLUtility {

	FileOutputStream outstream;
    String errorId=null;
	private static Logger logger = Logger.getLogger(UploadStaffXSLUtility.class);


	public Map<String, Object> getExcelData(File fileURL, FormFile file,String extension) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in UploadStageXSLUtility : getExcelData : Starting");

		ArrayList<UploadStaffXlsPOJO> stafflist = new ArrayList<UploadStaffXlsPOJO>();
		
		ArrayList<Object> sheetcountlist = new ArrayList<Object>();
        Map<String,Object> map=new HashMap<String,Object>();
         
		try {
			outstream = new FileOutputStream(fileURL);
			outstream.write(file.getFileData());

			if (!fileURL.exists()) {
				FileOutputStream fileOutStream = new FileOutputStream(fileURL);
				fileURL.mkdir();
				fileOutStream.write(file.getFileData());
				fileOutStream.flush();
				fileOutStream.close();
			}
						
			/*FileInputStream inputstream = new FileInputStream(fileURL);
			XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			XSSFRow row;
			XSSFCell cell;

			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();

				for (int i = 0; i < 30; i++) {

					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue().toString().trim();
						sheetcountlist.add(listofString1);
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						//System.out.println("boolean===>>>"+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString().trim();

							DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR);
							sheetcountlist.add(formatedDate);

						} else {

							String sheetNumber = ((long) cell.getNumericCellValue()) + "";
							sheetcountlist.add(sheetNumber.trim());
						}

						break;
					    case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						//System.out.println("string===>>>"	+ cell.getStringCellValue() + "\t");
						sheetcountlist.add(listofString);
						break;
					}
				}
			}*/
			
            FileInputStream inputstream = new FileInputStream(fileURL);
			Iterator<Row> rowIterator=null;
			Iterator rows=null;
			
			if(extension.equalsIgnoreCase("xls")){
				HSSFWorkbook	workbook=new HSSFWorkbook(inputstream);
				HSSFSheet	sheet = workbook.getSheetAt(0);
				rowIterator = sheet.iterator();
				rows = sheet.rowIterator();
				HSSFRow row=null;
				HSSFCell cell=null;
			
				while (rows.hasNext()) {
					row = (HSSFRow) rows.next();
					for (int i = 0; i < 30; i++) {
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BLANK:
							String listofString1 = cell.getStringCellValue().toString().trim();
							sheetcountlist.add(listofString1);
							break;

						case Cell.CELL_TYPE_BOOLEAN:
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								String dateStr = cell.getDateCellValue().toString().trim();

								DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
								Date date1 = (Date) formatter.parse(dateStr);

								Calendar cal = Calendar.getInstance();
								cal.setTime(date1);
								String formatedDate = cal.get(Calendar.DATE) + "-"
										+ (cal.get(Calendar.MONTH) + 1) + "-"
										+ cal.get(Calendar.YEAR);
								sheetcountlist.add(formatedDate);

							} else {
								String sheetNumber = ((long) cell.getNumericCellValue()) + "";
								sheetcountlist.add(sheetNumber.trim());
							}
							break;
						case Cell.CELL_TYPE_STRING:
							String listofString = cell.getStringCellValue().toString().trim();
							sheetcountlist.add(listofString);
							break;
						}
					}
				}
			}else{ //for xlsx format

				XSSFWorkbook	workbook=new XSSFWorkbook(inputstream);
				XSSFSheet	sheet = workbook.getSheetAt(0);
				rowIterator = sheet.iterator();
				rows = sheet.rowIterator();
				
				XSSFRow row=null;
				XSSFCell cell=null;
			
				while (rows.hasNext()) {
					row = (XSSFRow) rows.next();
					for (int i = 0; i < 30; i++) {
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BLANK:
							String listofString1 = cell.getStringCellValue().toString().trim();
							sheetcountlist.add(listofString1);
							System.out.println("count" + sheetcountlist.size());
							break;

						case Cell.CELL_TYPE_BOOLEAN:
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								String dateStr = cell.getDateCellValue().toString().trim();

								DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
								Date date1 = (Date) formatter.parse(dateStr);

								Calendar cal = Calendar.getInstance();
								cal.setTime(date1);
								String formatedDate = cal.get(Calendar.DATE) + "-"
										+ (cal.get(Calendar.MONTH) + 1) + "-"
										+ cal.get(Calendar.YEAR);
								sheetcountlist.add(formatedDate);

							} else {
								String sheetNumber = ((long) cell.getNumericCellValue()) + "";
								sheetcountlist.add(sheetNumber.trim());
							}
							break;
						case Cell.CELL_TYPE_STRING:
							String listofString = cell.getStringCellValue().toString().trim();
							sheetcountlist.add(listofString);
							break;
						}
					}
			   }
			}
		
			int position=0;
			for (int i = 30; i <= sheetcountlist.size() - 30;) {
				

				UploadStaffXlsPOJO uploadStaffXSLPOJO = new UploadStaffXlsPOJO();
				
				//String stageid=sheetcountlist.get(i).toString();
				
				uploadStaffXSLPOJO.setRegistrationId(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setAbbreviation(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setFirstName(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setLastName(sheetcountlist.get(i).toString());
				i++;
				
				uploadStaffXSLPOJO.setLocation(sheetcountlist.get(i).toString());
				i++;
				
				uploadStaffXSLPOJO.setDateOfJoining(sheetcountlist.get(i).toString());
				i++;
				
				uploadStaffXSLPOJO.setDepartment(sheetcountlist.get(i).toString());
				i++;
				
				uploadStaffXSLPOJO.setDesignation(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setTeachingType(sheetcountlist.get(i).toString());
				i++;
				/*uploadStaffXSLPOJO.setPrimarySubject(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setSecondarySubject(sheetcountlist.get(i).toString());
				i++;*/
				uploadStaffXSLPOJO.setGender(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setDob(sheetcountlist.get(i).toString());
				i++;
				
				uploadStaffXSLPOJO.setQualification(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setMobileNo(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setEmail(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setBloodGroup(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setBankName(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setAccountNumber(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setPanNumber(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setAadharNo(sheetcountlist.get(i).toString());
				i++;
				 
				uploadStaffXSLPOJO.setIsStudentStudying(sheetcountlist.get(i).toString());
				i++;
				/*uploadStaffXSLPOJO.setStudentName(sheetcountlist.get(i).toString());
				i++;*/
				uploadStaffXSLPOJO.setAdmissionNo(sheetcountlist.get(i).toString());
				i++;
				
				uploadStaffXSLPOJO.setFatherName(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setFatherMobile(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setMotherName(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setMotherMobile(sheetcountlist.get(i).toString());
				i++;
				
				uploadStaffXSLPOJO.setMaritalStatus(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setSpouseName(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setSpouseMobile(sheetcountlist.get(i).toString());
				i++;

				uploadStaffXSLPOJO.setPresentAddress(sheetcountlist.get(i).toString());
				i++;
				uploadStaffXSLPOJO.setPermanentAddress(sheetcountlist.get(i).toString());
				i++;
				
				//stafflist.add(uploadStaffXSLPOJO);
				
				/*boolean duplicateList=checkDuplicate(stafflistCollection,registrationId, position);
				if(!duplicateList){
					uploadStaffXSLPOJO.setDuplicateInExcel(0);
					stafflist.add(uploadStaffXSLPOJO);
				}
				else{
					uploadStaffXSLPOJO.setDuplicateInExcel(1);
					stafflist.add(uploadStaffXSLPOJO);
					//map.put("Error"," This StageName \" :"+stageName +"\" is Already Exits in Excel Sheet");
				}*/
				stafflist.add(uploadStaffXSLPOJO);
				position++;
				map.put("List",stafflist);
		  }
			outstream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLUtility : getExcelData : Ending");
		return map;
	}
	
	public boolean checkDuplicate(List<UploadStaffXlsPOJO> li,String registrationId, int position){
		boolean flag=false;
		//int c=0;
		
		/*System.out.println("li.size()::: and current positon: of  "+registrationId+" "+li.size()+" - "+position);*/
		for(int i=0;i<position;i++){
			//System.out.println("Registration Id in List: "+li.get(i).getRegistrationId());
			if(li.get(i).getRegistrationId().equalsIgnoreCase(registrationId)){
				//c++;
				errorId="Duplicate Entry of Registration Id. Please Check It";
				flag=true;
				break;
			}
		}
		/*System.out.println("Duplciate in Excel: "+c+" flag value= "+flag);*/
		return flag;
	}
}
