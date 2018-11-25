package com.centris.campus.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
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
import com.centris.campus.pojo.UploadDriverXlsPOJO;
import com.centris.campus.pojo.UploadStageXlsPOJO;




public class UploadDriverXSLUtility {

	FileOutputStream outstream;
    String errorId=null;
	private static Logger logger = Logger.getLogger(UploadDriverXSLUtility.class);


	public Map<String, Object> getExcelData(File fileURL, FormFile file,String extension) {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLUtility : getExcelData : Starting");

		ArrayList<UploadDriverXlsPOJO> driverList = new ArrayList<UploadDriverXlsPOJO>();
		
		ArrayList<UploadDriverXlsPOJO> driverListCollection = new ArrayList<UploadDriverXlsPOJO>();
	
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

				for (int i = 0; i < 13; i++) {

					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue().toString().trim();
						sheetcountlist.add(listofString1);
						break;

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("boolean===>>>"+ cell.getBooleanCellValue() + "\t");
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
							System.out.println("formatedDate" + formatedDate);
							sheetcountlist.add(formatedDate);

						} else {
							 Double value = cell.getNumericCellValue();
			                 Long longValue = value.longValue();
			                 String  strCellValue = new BigDecimal(cell.getNumericCellValue()).toPlainString();
							String sheetNumber = ((long) cell.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"+ sheetNumber);
							sheetcountlist.add(strCellValue.toString());
						}

						break;
					    case Cell.CELL_TYPE_STRING:
						String listofString = cell.getStringCellValue().toString().trim();
						System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
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
					for (int i = 0; i < 13; i++) {
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

								DateFormat formatter = new SimpleDateFormat(
										"E MMM dd HH:mm:ss Z yyyy");
								Date date1 = (Date) formatter.parse(dateStr);

								Calendar cal = Calendar.getInstance();
								cal.setTime(date1);
								String formatedDate = cal.get(Calendar.DATE) + "-"
										+ (cal.get(Calendar.MONTH) + 1) + "-"
										+ cal.get(Calendar.YEAR);
								sheetcountlist.add(formatedDate);

							} else {
								String sheetNumber = ((long) cell
										.getNumericCellValue()) + "";
								 
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
			for (int i = 13; i <= sheetcountlist.size() -13;) {

				UploadDriverXlsPOJO uploadDriverXSLPOJO = new UploadDriverXlsPOJO();
				
				//String stageid=sheetcountlist.get(i).toString();
				
				uploadDriverXSLPOJO.setLocationnid(sheetcountlist.get(i).toString());
				i++;
				
				uploadDriverXSLPOJO.setDriverName(sheetcountlist.get(i).toString());
				i++;
				
				uploadDriverXSLPOJO.setFatherName(sheetcountlist.get(i).toString());
				i++;
				
				uploadDriverXSLPOJO.setDob(sheetcountlist.get(i).toString());
				i++;
				
				uploadDriverXSLPOJO.setGender(sheetcountlist.get(i).toString());
				i++;
				uploadDriverXSLPOJO.setMobile(sheetcountlist.get(i).toString());
				i++;
				
				uploadDriverXSLPOJO.setEmergencyContactNo(sheetcountlist.get(i).toString());
				i++;
				
				uploadDriverXSLPOJO.setDoj(sheetcountlist.get(i).toString());
				i++;
				
				uploadDriverXSLPOJO.setExperiance(sheetcountlist.get(i).toString());
				i++;
				
				uploadDriverXSLPOJO.setAddress(sheetcountlist.get(i).toString());
				i++;
				
				uploadDriverXSLPOJO.setDrivingLicenseNo(sheetcountlist.get(i).toString());
				i++;
				
				uploadDriverXSLPOJO.setDrivingLicenseValidityDate(sheetcountlist.get(i).toString());
				i++;
				uploadDriverXSLPOJO.setLicenseToDrive(sheetcountlist.get(i).toString());
				i++;
				
				driverList.add(uploadDriverXSLPOJO);
				
				position++;
				
				//without checking duplicate.
				map.put("List",driverList);
			
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
	
	public boolean checkDuplicate(List<UploadStageXlsPOJO> li,String stageName, int position){
		boolean flag=false;
		int c=0;
		
		//System.out.println("li.size()::: and current positon: of  "+stageName+" "+li.size()+" - "+position);
		for(int i=0;i<position;i++){
			//System.out.println("Stage name in List: "+li.get(i).getStage_name());
			if(li.get(i).getStage_name().equalsIgnoreCase(stageName)){
				c++;
				errorId="Duplicate Entry of Driver Name Please Check It";
				flag=true;
				break;
			}
		}
		//System.out.println("Duplciate in Excel: "+c);
		return flag;
		
	}
	
	

}
