package com.centris.campus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.upload.FormFile;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SettingsFileUploadPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.StudentExcelUploadPojo;
import com.centris.campus.pojo.UserLoggingsPojo;

import jxl.Workbook;

public class SettingsFileUploadUtil {
	FileOutputStream outstream;
    String errorId=null;
	private static Logger logger = Logger.getLogger(SettingsFileUploadUtil.class);

public Map<String, Object> StreamSetting(File fileURL, FormFile file, String extension) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadUtil : StreamSetting : Starting");
		
		ArrayList<StreamDetailsPojo> Streamlist = new ArrayList<StreamDetailsPojo>();
		ArrayList<Object> sheetcountlist = new ArrayList<Object>();
        Map<String,Object> map=new HashMap<String,Object>();
        
       try{
    	   outstream = new FileOutputStream(fileURL);
		   outstream.write(file.getFileData());
		  
			if (!fileURL.exists()) {
				FileOutputStream fileOutStream = new FileOutputStream(fileURL);
				fileURL.mkdir();
				fileOutStream.write(file.getFileData());
				fileOutStream.flush();
				fileOutStream.close();
			}
			
			FileInputStream inputstream = new FileInputStream(fileURL);
			
			Iterator<Row> rowIterator=null;
			Iterator rows=null;
			
			/*System.out.println("extension daoImpl:"+extension);*/
			if(extension.equalsIgnoreCase("xls")){
				HSSFWorkbook	workbook=new HSSFWorkbook(inputstream);
				HSSFSheet	sheet = workbook.getSheetAt(0);
				rowIterator = sheet.iterator();
				rows = sheet.rowIterator();
				HSSFRow row=null;
				HSSFCell cell=null;
			
				while (rows.hasNext()) {
					row = (HSSFRow) rows.next();
					for (int i = 0; i < 3; i++) {
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BLANK:
							/*System.out.println("blank===>>>"
									+ cell.getStringCellValue() + "\t");*/
							String listofString1 = cell.getStringCellValue()
									.toString().trim();
							sheetcountlist.add(listofString1);
							/*System.out.println("count" + sheetcountlist.size());*/
							break;

						case Cell.CELL_TYPE_BOOLEAN:
							/*System.out.println("boolean===>>>"
									+ cell.getBooleanCellValue() + "\t");*/
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								String dateStr = cell.getDateCellValue().toString()
										.trim();

								DateFormat formatter = new SimpleDateFormat(
										"E MMM dd HH:mm:ss Z yyyy");
								Date date1 = (Date) formatter.parse(dateStr);

								Calendar cal = Calendar.getInstance();
								cal.setTime(date1);
								String formatedDate = cal.get(Calendar.DATE) + "-"
										+ (cal.get(Calendar.MONTH) + 1) + "-"
										+ cal.get(Calendar.YEAR);
								/*System.out.println("formatedDate" + formatedDate);*/
								sheetcountlist.add(formatedDate);

							} else {
								String sheetNumber = ((long) cell
										.getNumericCellValue()) + "";
								/*System.out.println("cell numeric value===>>"
										+ sheetNumber);*/
								sheetcountlist.add(sheetNumber.trim());
							}
							break;
						case Cell.CELL_TYPE_STRING:
							String listofString = cell.getStringCellValue().toString().trim();
							/*System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
							System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);*/
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
					for (int i = 0; i < 3; i++) {
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BLANK:
							/*System.out.println("blank===>>>"
									+ cell.getStringCellValue() + "\t");*/
							String listofString1 = cell.getStringCellValue()
									.toString().trim();
							sheetcountlist.add(listofString1);
							/*System.out.println("count" + sheetcountlist.size());*/
							break;

						case Cell.CELL_TYPE_BOOLEAN:
							/*System.out.println("boolean===>>>"
									+ cell.getBooleanCellValue() + "\t");*/
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								String dateStr = cell.getDateCellValue().toString()
										.trim();

								DateFormat formatter = new SimpleDateFormat(
										"E MMM dd HH:mm:ss Z yyyy");
								Date date1 = (Date) formatter.parse(dateStr);

								Calendar cal = Calendar.getInstance();
								cal.setTime(date1);
								String formatedDate = cal.get(Calendar.DATE) + "-"
										+ (cal.get(Calendar.MONTH) + 1) + "-"
										+ cal.get(Calendar.YEAR);
								/*System.out.println("formatedDate" + formatedDate);*/
								sheetcountlist.add(formatedDate);

							} else {
								String sheetNumber = ((long) cell
										.getNumericCellValue()) + "";
								/*System.out.println("cell numeric value===>>"
										+ sheetNumber);*/
								sheetcountlist.add(sheetNumber.trim());
							}
							break;
						case Cell.CELL_TYPE_STRING:
							String listofString = cell.getStringCellValue().toString().trim();
							/*System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
							System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);*/
							sheetcountlist.add(listofString);
							break;
						}
					}
			}
			}
			/*System.out.println("size of sheetcountlist "+sheetcountlist.size());*/
			for (int i = 3; i <= sheetcountlist.size() - 3;) {
				StreamDetailsPojo pojo = new StreamDetailsPojo();
				
				pojo.setLocationName(sheetcountlist.get(i).toString().trim());
				String locName = sheetcountlist.get(i).toString();
				i++;
				pojo.setStreamName(sheetcountlist.get(i).toString().trim());
				String streamName = sheetcountlist.get(i).toString();
				i++;
				pojo.setDescription(sheetcountlist.get(i).toString().trim());
				String descrip = sheetcountlist.get(i).toString();
				i++;
				
				
				if(locName.trim().equalsIgnoreCase("") && streamName.trim().equalsIgnoreCase("") && descrip.trim().equalsIgnoreCase("")) {
				}
				else{
					Streamlist.add(pojo);
				}
				map.put("List",Streamlist);
			}
       }catch(Exception e){
    	   e.printStackTrace();
       }
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadUtil : StreamSetting : Ending");
		return map;
}


public Map<String, Object> ClassSetting(File fileURL, FormFile file, String extension) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SettingsFileUploadUtil : ClassSetting : Starting");
	
	ArrayList<ClassPojo> classList = new ArrayList<ClassPojo>();
	ArrayList<Object> sheetcountlist = new ArrayList<Object>();
    Map<String,Object> map=new HashMap<String,Object>();
    
   try{
	   outstream = new FileOutputStream(fileURL);
	   outstream.write(file.getFileData());
	  
		if (!fileURL.exists()) {
			FileOutputStream fileOutStream = new FileOutputStream(fileURL);
			fileURL.mkdir();
			fileOutStream.write(file.getFileData());
			fileOutStream.flush();
			fileOutStream.close();
		}
		
		FileInputStream inputstream = new FileInputStream(fileURL);
		
		Iterator<Row> rowIterator=null;
		Iterator rows=null;
		
		System.out.println("extension daoImpl:"+extension);
		if(extension.equalsIgnoreCase("xls")){
			HSSFWorkbook	workbook=new HSSFWorkbook(inputstream);
			HSSFSheet	sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			rows = sheet.rowIterator();
			HSSFRow row=null;
			HSSFCell cell=null;
		
			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				for (int i = 0; i < 3; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
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
				for (int i = 0; i < 3; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
		}
		
		System.out.println("size of sheetcountlist "+sheetcountlist.size());
		for (int i = 3; i <= sheetcountlist.size() - 3;) {
			ClassPojo pojo = new ClassPojo();
			
			pojo.setLocationName(sheetcountlist.get(i).toString().trim());
			String locName = sheetcountlist.get(i).toString();
			i++;
			pojo.setStreamName(sheetcountlist.get(i).toString().trim());
			String streamName = sheetcountlist.get(i).toString();
			i++;
			pojo.setClassName(sheetcountlist.get(i).toString().trim());
			String className = sheetcountlist.get(i).toString();
			i++;
			if(locName.equalsIgnoreCase("") && streamName.equalsIgnoreCase("") && className.equalsIgnoreCase("")){
				
			}else{
				classList.add(pojo);
			}
			map.put("List",classList);
		}
   }catch(Exception e){
	   e.printStackTrace();
   }
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SettingsFileUploadUtil : ClassSetting : Ending");
	return map;
}



public Map<String, Object> DivisionSetting(File fileURL, FormFile file, String extension, UserLoggingsPojo custdetails) {

	ArrayList<ClassPojo> DivisionList = new ArrayList<ClassPojo>();
	ArrayList<Object> sheetcountlist = new ArrayList<Object>();
    Map<String,Object> map=new HashMap<String,Object>();
    
   try{
	   outstream = new FileOutputStream(fileURL);
	   outstream.write(file.getFileData());
		if (!fileURL.exists()) {
			FileOutputStream fileOutStream = new FileOutputStream(fileURL);
			fileURL.mkdir();
			fileOutStream.write(file.getFileData());
			fileOutStream.flush();
			fileOutStream.close();
		}
		
		FileInputStream inputstream = new FileInputStream(fileURL);
		
		Iterator<Row> rowIterator=null;
		Iterator rows=null;
		
		System.out.println("extension daoImpl:"+extension);
		if(extension.equalsIgnoreCase("xls")){
			HSSFWorkbook	workbook=new HSSFWorkbook(inputstream);
			HSSFSheet	sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			rows = sheet.rowIterator();
			HSSFRow row=null;
			HSSFCell cell=null;
		
			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				for (int i = 0; i < 5; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
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
				for (int i = 0; i < 5; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
		}
		
		System.out.println("size of sheetcountlist "+sheetcountlist.size());
		for (int i = 5; i <= sheetcountlist.size() - 5;) {
			String checknum="false";
			ClassPojo pojo = new ClassPojo();
			
			pojo.setLocationName(sheetcountlist.get(i).toString().trim());
			String location = sheetcountlist.get(i).toString();
			i++;
			pojo.setStreamName(sheetcountlist.get(i).toString().trim());
			String stream = sheetcountlist.get(i).toString().trim();
			i++;
			pojo.setClassName(sheetcountlist.get(i).toString().trim());
			String className = sheetcountlist.get(i).toString();
			i++;
			pojo.setDivisionName(sheetcountlist.get(i).toString().trim());
			String division = sheetcountlist.get(i).toString();
			i++;
		
			pojo.setStrength(sheetcountlist.get(i).toString().trim());
			String Strength = sheetcountlist.get(i).toString().trim();
			i++;
			
			if(location.equalsIgnoreCase("") && stream.equalsIgnoreCase("") && className.equalsIgnoreCase("") && division.equalsIgnoreCase("") && Strength.equalsIgnoreCase("")){
				
			}else{
				DivisionList.add(pojo);
			}
			
			map.put("List",DivisionList);
		}
   }
		catch(Exception e){
	   e.printStackTrace();
   }
	return map;
}



public Map<String, Object> OccupationSetting(File fileURL, FormFile file, String extension) {
	ArrayList<ClassPojo> OccupationList = new ArrayList<ClassPojo>();
	ArrayList<Object> sheetcountlist = new ArrayList<Object>();
    Map<String,Object> map=new HashMap<String,Object>();
    
   try{
	   outstream = new FileOutputStream(fileURL);
	   outstream.write(file.getFileData());
		if (!fileURL.exists()) {
			FileOutputStream fileOutStream = new FileOutputStream(fileURL);
			fileURL.mkdir();
			fileOutStream.write(file.getFileData());
			fileOutStream.flush();
			fileOutStream.close();
		}
		FileInputStream inputstream = new FileInputStream(fileURL);
		
		Iterator<Row> rowIterator=null;
		Iterator rows=null;
		
		System.out.println("extension daoImpl:"+extension);
		if(extension.equalsIgnoreCase("xls")){
			HSSFWorkbook	workbook=new HSSFWorkbook(inputstream);
			HSSFSheet	sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			rows = sheet.rowIterator();
			HSSFRow row=null;
			HSSFCell cell=null;
		
			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				for (int i = 0; i < 1; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
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
				for (int i = 0; i <1; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
		}
		
		System.out.println("size of sheetcountlist "+sheetcountlist.size());
		for (int i = 1; i <= sheetcountlist.size() - 1;) {
			ClassPojo pojo = new ClassPojo();
			
			pojo.setOccupationName(sheetcountlist.get(i).toString().trim());
			String occu = sheetcountlist.get(i).toString();
			i++;
			if(occu.equalsIgnoreCase("")){
				
			}else{
				OccupationList.add(pojo);
			}
			map.put("List",OccupationList);
		}
   }catch(Exception e){
	   e.printStackTrace();
   }
   return  map;
}



public Map<String, Object> ReligionSetting(File fileURL, FormFile file, String extension) {
	ArrayList<ClassPojo> ReligionList = new ArrayList<ClassPojo>();
	ArrayList<Object> sheetcountlist = new ArrayList<Object>();
    Map<String,Object> map=new HashMap<String,Object>();
    
   try{
	   outstream = new FileOutputStream(fileURL);
	   outstream.write(file.getFileData());
		if (!fileURL.exists()) {
			FileOutputStream fileOutStream = new FileOutputStream(fileURL);
			fileURL.mkdir();
			fileOutStream.write(file.getFileData());
			fileOutStream.flush();
			fileOutStream.close();
		}
		FileInputStream inputstream = new FileInputStream(fileURL);
		
		Iterator<Row> rowIterator=null;
		Iterator rows=null;
		
		System.out.println("extension daoImpl:"+extension);
		if(extension.equalsIgnoreCase("xls")){
			HSSFWorkbook	workbook=new HSSFWorkbook(inputstream);
			HSSFSheet	sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			rows = sheet.rowIterator();
			HSSFRow row=null;
			HSSFCell cell=null;
		
			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				for (int i = 0; i < 1; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
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
				for (int i = 0; i < 1; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
		}
		
		System.out.println("size of sheetcountlist "+sheetcountlist.size());
		for (int i = 1; i <= sheetcountlist.size() - 1;) {
			ClassPojo pojo = new ClassPojo();
			boolean emptyflag=false;
			pojo.setReligionName(sheetcountlist.get(i).toString().trim());
			if(emptyflag){
				if(sheetcountlist.get(i).toString().trim().equalsIgnoreCase("")){
					emptyflag=true;
				}else{
					emptyflag=false;
				}
			}
			String religion = sheetcountlist.get(i).toString();
			i++;
			if(religion.equalsIgnoreCase("")){
				
			}else{
			ReligionList.add(pojo);
			}
			
			map.put("List",ReligionList);
		}
   }catch(Exception e){
	   e.printStackTrace();
   }
   return  map;
}



public Map<String, Object> CasteSetting(File fileURL, FormFile file, String extension) {
	ArrayList<ClassPojo> CasteList = new ArrayList<ClassPojo>();
	ArrayList<Object> sheetcountlist = new ArrayList<Object>();
    Map<String,Object> map=new HashMap<String,Object>();
    
   try{
	   outstream = new FileOutputStream(fileURL);
	   outstream.write(file.getFileData());
		if (!fileURL.exists()) {
			FileOutputStream fileOutStream = new FileOutputStream(fileURL);
			fileURL.mkdir();
			fileOutStream.write(file.getFileData());
			fileOutStream.flush();
			fileOutStream.close();
		}
		FileInputStream inputstream = new FileInputStream(fileURL);
		
		Iterator<Row> rowIterator=null;
		Iterator rows=null;
		
		System.out.println("extension daoImpl:"+extension);
		if(extension.equalsIgnoreCase("xls")){
			HSSFWorkbook	workbook=new HSSFWorkbook(inputstream);
			HSSFSheet	sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			rows = sheet.rowIterator();
			HSSFRow row=null;
			HSSFCell cell=null;
		
			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				for (int i = 0; i < 2; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
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
				for (int i = 0; i < 2; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
		}
		
		System.out.println("size of sheetcountlist "+sheetcountlist.size());
		for (int i = 2; i <= sheetcountlist.size() - 2;) {
			ClassPojo pojo = new ClassPojo();
			
			pojo.setReligionName(sheetcountlist.get(i).toString().trim());
			String religion = sheetcountlist.get(i).toString();
			i++;
			pojo.setCasteName(sheetcountlist.get(i).toString().trim());
			String caste = sheetcountlist.get(i).toString();
			i++;
			if(religion.equalsIgnoreCase("") && caste.equalsIgnoreCase("")){
				
			}else{
				CasteList.add(pojo);
			}
			map.put("List",CasteList);
		}
   }catch(Exception e){
	   e.printStackTrace();
   }
   return  map;
}



public Map<String, Object> CasteCategorySetting(File fileURL, FormFile file, String extension) {
	ArrayList<ClassPojo> cclist = new ArrayList<ClassPojo>();
	ArrayList<Object> sheetcountlist = new ArrayList<Object>();
    Map<String,Object> map=new HashMap<String,Object>();
    
   try{
	   outstream = new FileOutputStream(fileURL);
	   outstream.write(file.getFileData());
		if (!fileURL.exists()) {
			FileOutputStream fileOutStream = new FileOutputStream(fileURL);
			fileURL.mkdir();
			fileOutStream.write(file.getFileData());
			fileOutStream.flush();
			fileOutStream.close();
		}
		FileInputStream inputstream = new FileInputStream(fileURL);
		
		Iterator<Row> rowIterator=null;
		Iterator rows=null;
		
		System.out.println("extension daoImpl:"+extension);
		if(extension.equalsIgnoreCase("xls")){
			HSSFWorkbook	workbook=new HSSFWorkbook(inputstream);
			HSSFSheet	sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			rows = sheet.rowIterator();
			HSSFRow row=null;
			HSSFCell cell=null;
		
			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				for (int i = 0; i < 3; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
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
				for (int i = 0; i < 3; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
		}
		
		System.out.println("size of sheetcountlist "+sheetcountlist.size());
		for (int i = 0; i <= sheetcountlist.size() - 2;) {
			
			ClassPojo pojo = new ClassPojo();
			pojo.setReligionName(sheetcountlist.get(i).toString().trim());
			String religion = sheetcountlist.get(i).toString();
			i++;
			pojo.setCasteName(sheetcountlist.get(i).toString().trim());
			String caste = sheetcountlist.get(i).toString();
			i++;
			pojo.setCasteCategoryName(sheetcountlist.get(i).toString().trim());
			String casteCat = sheetcountlist.get(i).toString();
			i++;
			if(religion.equalsIgnoreCase("") && caste.equalsIgnoreCase("") && casteCat.equalsIgnoreCase("")){
				
			}else{
			cclist.add(pojo);
			}
			map.put("List",cclist);
		}
   }catch(Exception e){
	   e.printStackTrace();
   }
   return  map;
}



public Map<String, Object> specSetting(File fileURL, FormFile file, String extension) {
	ArrayList<ClassPojo> classList = new ArrayList<ClassPojo>();
	ArrayList<Object> sheetcountlist = new ArrayList<Object>();
    Map<String,Object> map=new HashMap<String,Object>();
    
   try{
	   outstream = new FileOutputStream(fileURL);
	   outstream.write(file.getFileData());
		if (!fileURL.exists()) {
			FileOutputStream fileOutStream = new FileOutputStream(fileURL);
			fileURL.mkdir();
			fileOutStream.write(file.getFileData());
			fileOutStream.flush();
			fileOutStream.close();
		}
		FileInputStream inputstream = new FileInputStream(fileURL);
		
		Iterator<Row> rowIterator=null;
		Iterator rows=null;
		
		System.out.println("extension daoImpl:"+extension);
		if(extension.equalsIgnoreCase("xls")){
			HSSFWorkbook	workbook=new HSSFWorkbook(inputstream);
			HSSFSheet	sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			rows = sheet.rowIterator();
			HSSFRow row=null;
			HSSFCell cell=null;
		
			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				for (int i = 0; i < 4; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
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
				for (int i = 0; i < 4; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
		}
		
		System.out.println("size of sheetcountlist "+sheetcountlist.size());
		
		System.out.println("size of sheetcountlist "+sheetcountlist.size());
		for (int i = 4; i <= sheetcountlist.size() - 4;) {
			ClassPojo pojo = new ClassPojo();
			
			pojo.setLocationName(sheetcountlist.get(i).toString().trim());
			String locName = sheetcountlist.get(i).toString();
			i++;
			pojo.setStreamName(sheetcountlist.get(i).toString().trim());
			String stream = sheetcountlist.get(i).toString();
			i++;
			pojo.setClassName(sheetcountlist.get(i).toString().trim());
			String className = sheetcountlist.get(i).toString();
			i++;
			pojo.setSpecializationName(sheetcountlist.get(i).toString().trim());
			String spec = sheetcountlist.get(i).toString();
			i++;
			if(locName.equalsIgnoreCase("" ) && stream.equalsIgnoreCase("") && className.equalsIgnoreCase("") && spec.equalsIgnoreCase("")){
				
			}else{
			classList.add(pojo);
			}
			map.put("List",classList);
		}
   }catch(Exception e){
	   e.printStackTrace();
   }
	return map;
}


public Map<String, Object> holidaySetting(File fileURL, FormFile file, String extension) {
	ArrayList<ClassPojo> holidayList = new ArrayList<ClassPojo>();
	ArrayList<Object> sheetcountlist = new ArrayList<Object>();
    Map<String,Object> map=new HashMap<String,Object>();
    
   try{
	   outstream = new FileOutputStream(fileURL);
	   outstream.write(file.getFileData());
		if (!fileURL.exists()) {
			FileOutputStream fileOutStream = new FileOutputStream(fileURL);
			fileURL.mkdir();
			fileOutStream.write(file.getFileData());
			fileOutStream.flush();
			fileOutStream.close();
		}
		FileInputStream inputstream = new FileInputStream(fileURL);
		
		Iterator<Row> rowIterator=null;
		Iterator rows=null;
		
		System.out.println("extension daoImpl:"+extension);
		if(extension.equalsIgnoreCase("xls")){
			HSSFWorkbook	workbook=new HSSFWorkbook(inputstream);
			HSSFSheet	sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			rows = sheet.rowIterator();
			HSSFRow row=null;
			HSSFCell cell=null;
		
			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				for (int i = 0; i < 4; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
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
				for (int i = 0; i < 4; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
		}
		
		System.out.println("size of sheetcountlist "+sheetcountlist.size());
		for (int i = 4; i <= sheetcountlist.size() - 4;) {
			ClassPojo pojo = new ClassPojo();
			
			pojo.setLocationName(sheetcountlist.get(i).toString().trim());
			String loc = sheetcountlist.get(i).toString();
			i++;
			pojo.setHolidayDate(sheetcountlist.get(i).toString().trim());
			String date = sheetcountlist.get(i).toString();
			i++;
			pojo.setHolidayName(sheetcountlist.get(i).toString().trim());
			String name = sheetcountlist.get(i).toString();
			i++;
			pojo.setHolidaytype(sheetcountlist.get(i).toString().trim());
			String type = sheetcountlist.get(i).toString();
			i++;
			if(loc.equalsIgnoreCase("") && date.equalsIgnoreCase("") && name.equalsIgnoreCase("") && type.equalsIgnoreCase("")){
				
			}else{
			holidayList.add(pojo);
			}
			map.put("List",holidayList);
		}
   }catch(Exception e){
	   e.printStackTrace();
   }
	return map;
}
public Map<String, Object> StudentExamExcelUpload(File fileURL, FormFile file) throws IndexOutOfBoundsException {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SettingsFileUploadUtil : StudentExamExcelUpload : Starting");
	
	ArrayList<StudentExcelUploadPojo> uploaddata = new ArrayList<StudentExcelUploadPojo>();
	ArrayList<Object> sheetcountlist = new ArrayList<Object>();
    Map<String,Object> map=new HashMap<String,Object>();
   try{
	   outstream = new FileOutputStream(fileURL);
	   outstream.write(file.getFileData());
		if (!fileURL.exists()) {
			FileOutputStream fileOutStream = new FileOutputStream(fileURL);
			fileURL.mkdir();
			fileOutStream.write(file.getFileData());
			fileOutStream.flush();
			fileOutStream.close();
		}
		
		String filename=file.getFileName().split("\\.")[1];
		
		FileInputStream inputstream = new FileInputStream(fileURL);
		Iterator<Row> rowIterator=null;
		Iterator rows=null;
		
		if(filename.equalsIgnoreCase("xls")){
			XSSFWorkbook	workbook=new XSSFWorkbook(inputstream);
			XSSFSheet	sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			rows = sheet.rowIterator();
			XSSFRow row=null;
			XSSFCell cell=null;
		
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				for (int i = 0; i < 25; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						/*System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");*/
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						/*System.out.println("count" + sheetcountlist.size());*/
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						/*System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");*/
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							/*System.out.println("formatedDate" + formatedDate);*/
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							/*System.out.println("cell numeric value===>>"
									+ sheetNumber);*/
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						/*System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);*/
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
		}else{
			XSSFWorkbook	workbook=new XSSFWorkbook(inputstream);
			XSSFSheet	sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			rows = sheet.rowIterator();
			
			XSSFRow row=null;
			XSSFCell cell=null;
		
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				for (int i = 0; i < 25; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						/*System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");*/
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						/*System.out.println("count" + sheetcountlist.size());*/
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						/*System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");*/
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							/*System.out.println("cell numeric value===>>"
									+ sheetNumber);*/
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						/*System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);*/
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
			
		}
		
		
		for (int i =50 ; i < sheetcountlist.size();) {
			
			StudentExcelUploadPojo pojo = new StudentExcelUploadPojo();
			
			pojo.setStudentName(sheetcountlist.get(i).toString().trim());
			String stuName = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setStudentIdNo(sheetcountlist.get(i).toString().trim());
			String studentIdNo = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setSchoolName(sheetcountlist.get(i).toString().trim());
			String school = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setAcademicYear(sheetcountlist.get(i).toString().trim());
			String accyear = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setStreamName(sheetcountlist.get(i).toString().trim());
			i++;

			pojo.setClassName(sheetcountlist.get(i).toString().trim());
			String classname = sheetcountlist.get(i).toString();
			i++;
		
			pojo.setSectionName(sheetcountlist.get(i).toString().trim());
			String section = sheetcountlist.get(i).toString();
			i++;
			
			
			pojo.setExamType(sheetcountlist.get(i).toString().trim());
			String examType = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setExamName(sheetcountlist.get(i).toString().trim());
			String examname = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setExamCode(sheetcountlist.get(i).toString().trim());
			String examcode = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setStartdate(sheetcountlist.get(i).toString().trim());
			String startdate = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setEnddate(sheetcountlist.get(i).toString().trim());
			String enddate = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setSubjectName(sheetcountlist.get(i).toString().trim());
			String subname = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setAttandance(sheetcountlist.get(i).toString().trim());
			String attendance = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setTestMaximumMarks(sheetcountlist.get(i).toString().trim());
			String testmaxmarks = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setTestScoredMarks(sheetcountlist.get(i).toString().trim());
			String testscoremarks = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setNotebookMaximumMarks(sheetcountlist.get(i).toString().trim());
			String notebookmarks = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setNotebookScoredMarks(sheetcountlist.get(i).toString().trim());
			String notebookscoremarks = sheetcountlist.get(i).toString();
			i++;
			
			
			
			pojo.setSubjectMaximumMarks(sheetcountlist.get(i).toString().trim());
			String subjectMaxmarks = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setSubjectScoredMarks(sheetcountlist.get(i).toString().trim());
			String subjectscoremarks = sheetcountlist.get(i).toString();
			i++;
			
			
			
			
			pojo.setWorkEducation(sheetcountlist.get(i).toString().trim());
			String workedu = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setArtEducation(sheetcountlist.get(i).toString().trim());
			String artedu = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setHealthEducation(sheetcountlist.get(i).toString().trim());
			String healthedu = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setDescipline(sheetcountlist.get(i).toString().trim());
			String descipline = sheetcountlist.get(i).toString();
			i++;
			pojo.setRemarks(sheetcountlist.get(i).toString().trim());
			String remarks = sheetcountlist.get(i).toString();
			i++;
			
			
			if(stuName.trim().equalsIgnoreCase("") && studentIdNo.trim().equalsIgnoreCase("") && school.trim().equalsIgnoreCase("")
					&& accyear.trim().equalsIgnoreCase("") && classname.trim().equalsIgnoreCase("") && section.trim().equalsIgnoreCase("") && attendance.trim().equalsIgnoreCase("")
					&& examType.trim().equalsIgnoreCase("")	&& examname.trim().equalsIgnoreCase("") && examcode.trim().equalsIgnoreCase("") && startdate.trim().equalsIgnoreCase("") 
					&& enddate.trim().equalsIgnoreCase("") && subname.trim().equalsIgnoreCase("") 
					&& testmaxmarks.trim().equalsIgnoreCase("") && testscoremarks.trim().equalsIgnoreCase("") && notebookmarks.trim().equalsIgnoreCase("") && notebookscoremarks.trim().equalsIgnoreCase("")
					&& subjectMaxmarks.trim().equalsIgnoreCase("") && subjectscoremarks.trim().equalsIgnoreCase("")
					&& workedu.trim().equalsIgnoreCase("") && artedu.trim().equalsIgnoreCase("")
					&& healthedu.trim().equalsIgnoreCase("") && descipline.trim().equalsIgnoreCase("")
					&& remarks.trim().equalsIgnoreCase("")
					){
			}else{
				uploaddata.add(pojo);
			}
			map.put("List",uploaddata);
		}
		outstream.close();
   }catch(Exception e){
	   System.out.println("Exception");
	 e.printStackTrace();
   }
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SettingsFileUploadUtil : StudentExamExcelUpload : Ending");
	return map;
	
	
}
public Map<String, Object> ExamGrades(File fileURL, FormFile file, String extension) throws Exception{
	ArrayList<StudentExcelUploadPojo> uploaddata = new ArrayList<StudentExcelUploadPojo>();
	ArrayList<Object> sheetcountlist = new ArrayList<Object>();
    Map<String,Object> map=new HashMap<String,Object>();
   try{
	   outstream = new FileOutputStream(fileURL);
	   outstream.write(file.getFileData());
		if (!fileURL.exists()) {
			FileOutputStream fileOutStream = new FileOutputStream(fileURL);
			fileURL.mkdir();
			fileOutStream.write(file.getFileData());
			fileOutStream.flush();
			fileOutStream.close();
		}
		String filename=file.getFileName().split("\\.")[1];
		FileInputStream inputstream = new FileInputStream(fileURL);
		Iterator<Row> rowIterator=null;
		Iterator rows=null;
		if(filename.equalsIgnoreCase("xls")){
			XSSFWorkbook	workbook=new XSSFWorkbook(inputstream);
			XSSFSheet	sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			rows = sheet.rowIterator();
			XSSFRow row=null;
			XSSFCell cell=null;
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				for (int i = 0; i < 13; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
		}else{
			XSSFWorkbook	workbook=new XSSFWorkbook(inputstream);
			XSSFSheet	sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			rows = sheet.rowIterator();
			XSSFRow row=null;
			XSSFCell cell=null;
			
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();

				for (int i = 0; i < 13; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
		}
		System.out.println("size of sheetcountlist "+sheetcountlist.size());
		for (int i =13 ; i <= sheetcountlist.size()-13;) {
			
			StudentExcelUploadPojo pojo = new StudentExcelUploadPojo();
			
			pojo.setStudentName(sheetcountlist.get(i).toString().trim());
			String stuName = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setStudentIdNo(sheetcountlist.get(i).toString().trim());
			String studentIdNo = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setSchoolName(sheetcountlist.get(i).toString().trim());
			String school = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setAcademicYear(sheetcountlist.get(i).toString().trim());
			String accyear = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setStreamName(sheetcountlist.get(i).toString().trim());
			String stream = sheetcountlist.get(i).toString();
			i++;

			pojo.setClassName(sheetcountlist.get(i).toString().trim());
			String classname = sheetcountlist.get(i).toString();
			i++;
		
			pojo.setSectionName(sheetcountlist.get(i).toString().trim());
			String section = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setExamType(sheetcountlist.get(i).toString().trim());
			String examType = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setWorkEducation(sheetcountlist.get(i).toString().trim());
			String workedu = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setArtEducation(sheetcountlist.get(i).toString().trim());
			String artedu = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setHealthEducation(sheetcountlist.get(i).toString().trim());
			String healthedu = sheetcountlist.get(i).toString();
			i++;
			
			pojo.setDescipline(sheetcountlist.get(i).toString().trim());
			String descipline = sheetcountlist.get(i).toString();
			i++;
			pojo.setRemarks(sheetcountlist.get(i).toString().trim());
			String remarks = sheetcountlist.get(i).toString();
			i++;
			
			if(stuName.trim().equalsIgnoreCase("") && studentIdNo.trim().equalsIgnoreCase("") && school.trim().equalsIgnoreCase("")
					&& accyear.trim().equalsIgnoreCase("") && classname.trim().equalsIgnoreCase("") && section.trim().equalsIgnoreCase("")
					&& examType.trim().equalsIgnoreCase("")	&& stream.trim().equalsIgnoreCase("")
					&& workedu.trim().equalsIgnoreCase("") && artedu.trim().equalsIgnoreCase("")
					&& healthedu.trim().equalsIgnoreCase("") && descipline.trim().equalsIgnoreCase("")
					&& remarks.trim().equalsIgnoreCase("")
					){
			}else{
				uploaddata.add(pojo);
			}
			map.put("List",uploaddata);
		}
		outstream.close();
   }catch(Exception e){
	   System.out.println("EXCEPTION HANDELED");
	   e.printStackTrace();
   }
	return map;
}


public Map<String, Object> SubjectSetting(File fileURL, FormFile file, String extension) {
	ArrayList<ClassPojo> subjectList = new ArrayList<ClassPojo>();
	ArrayList<Object> sheetcountlist = new ArrayList<Object>();
    Map<String,Object> map=new HashMap<String,Object>();
    
   try{
	   outstream = new FileOutputStream(fileURL);
	   outstream.write(file.getFileData());
		if (!fileURL.exists()) {
			FileOutputStream fileOutStream = new FileOutputStream(fileURL);
			fileURL.mkdir();
			fileOutStream.write(file.getFileData());
			fileOutStream.flush();
			fileOutStream.close();
		}
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
				for (int i = 0; i < 9; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
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
				for (int i = 0; i < 9; i++) {
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"
								+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue()
								.toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"
								+ cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dateStr = cell.getDateCellValue().toString()
									.trim();

							DateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date1 = (Date) formatter.parse(dateStr);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date1);
							String formatedDate = cal.get(Calendar.DATE) + "-"
									+ (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							String sheetNumber = ((long) cell
									.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"
									+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
						System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);
						sheetcountlist.add(listofString);
						break;
					}
				}
			}
		}
		
		System.out.println("size of sheetcountlist "+sheetcountlist.size());
		for (int i = 9; i <= sheetcountlist.size() - 9;) {
			ClassPojo pojo = new ClassPojo();
			
			pojo.setLocationName(sheetcountlist.get(i).toString().trim());
			String locName = sheetcountlist.get(i).toString();
			i++;
			pojo.setClassName(sheetcountlist.get(i).toString().trim());
			String className = sheetcountlist.get(i).toString();
			i++;
			pojo.setSpecializationName(sheetcountlist.get(i).toString().trim());
			String spec = sheetcountlist.get(i).toString();
			i++;
			pojo.setSubjectName(sheetcountlist.get(i).toString().trim());
			String subject = sheetcountlist.get(i).toString();
			i++;
			pojo.setSubjectCode(sheetcountlist.get(i).toString().trim());
			String subcode = sheetcountlist.get(i).toString();
			i++;
			pojo.setSubjectType(sheetcountlist.get(i).toString().trim());
			String subtype = sheetcountlist.get(i).toString();
			i++;
			pojo.setIsLanguage(sheetcountlist.get(i).toString().trim());
			String isLang = sheetcountlist.get(i).toString();
			i++;
			pojo.setIsLaboratory(sheetcountlist.get(i).toString().trim());
			String isLab = sheetcountlist.get(i).toString();
			i++;
			pojo.setDescription(sheetcountlist.get(i).toString().trim());
			String desc = sheetcountlist.get(i).toString();
			i++;
			if(locName.equalsIgnoreCase("" ) && className.equalsIgnoreCase("") && spec.equalsIgnoreCase("") && subject.equalsIgnoreCase("") 
					&& subcode.equalsIgnoreCase("" ) && subtype.equalsIgnoreCase("") && isLang.equalsIgnoreCase("") && isLab.equalsIgnoreCase("") && desc.equalsIgnoreCase("")
					){
			}else{
				subjectList.add(pojo);
			}
			map.put("List",subjectList);
		}
   }catch(Exception e){
	   e.printStackTrace();
   }
   
	return map;
}
}
	
	
	
	
	
	
	
	
	
	
	
	
