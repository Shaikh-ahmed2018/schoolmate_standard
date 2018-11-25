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
import com.centris.campus.pojo.UploadStageXlsPOJO;

public class UploadStageXSLUtility {

	FileOutputStream outstream;
    String errorId=null;
	private static Logger logger = Logger.getLogger(UploadStageXSLUtility.class);


	public Map<String, Object> getExcelData(File fileURL, FormFile file,String extension) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLUtility : getExcelData : Starting");

		ArrayList<UploadStageXlsPOJO> stagelist = new ArrayList<UploadStageXlsPOJO>();
		
		ArrayList<UploadStageXlsPOJO> stagelistCollection = new ArrayList<UploadStageXlsPOJO>();
	
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

				for (int i = 0; i < 5; i++) {

					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_BLANK:
						System.out.println("blank===>>>"+ cell.getStringCellValue() + "\t");
						String listofString1 = cell.getStringCellValue().toString().trim();
						sheetcountlist.add(listofString1);
						System.out.println("count" + sheetcountlist.size());
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
							String sheetNumber = ((long) cell.getNumericCellValue()) + "";
							System.out.println("cell numeric value===>>"+ sheetNumber);
							sheetcountlist.add(sheetNumber.trim());
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
							/*System.out.println("string===>>>"+ cell.getStringCellValue() + "\t");
							System.out.println("ccell values****************^^^^^^^^^^^^^^^"+listofString);*/
							sheetcountlist.add(listofString);
							break;
						}
					}
			}
			}
			
		
			for (int i = 5; i <=sheetcountlist.size() - 5;) {
				
				UploadStageXlsPOJO uploadStageXSLPOJO = new UploadStageXlsPOJO();
				
				//String stageid=sheetcountlist.get(i).toString();
				uploadStageXSLPOJO.setAccyear(sheetcountlist.get(i).toString());
				i++;
				
				uploadStageXSLPOJO.setLocationname(sheetcountlist.get(i).toString());
				i++;
				
				uploadStageXSLPOJO.setStage_name(sheetcountlist.get(i).toString());
				i++;
				 
				uploadStageXSLPOJO.setAmount(sheetcountlist.get(i).toString());
				i++;
				
				uploadStageXSLPOJO.setStage_description(sheetcountlist.get(i).toString());
				i++;
				
				/*boolean duplicateList=checkDuplicate(stagelistCollection,stageName, position);
				if(!duplicateList){
					uploadStageXSLPOJO.setDuplicateInExcel(1);
					stagelist.add(uploadStageXSLPOJO);
				}
				else{
					uploadStageXSLPOJO.setDuplicateInExcel(0);
					stagelist.add(uploadStageXSLPOJO);
					//map.put("Error"," This StageName \" :"+stageName +"\" is Already Exits in Excel Sheet");
				}*/
				
				 
				
				//without checking duplicate.
				stagelist.add(uploadStageXSLPOJO);
			
				map.put("List",stagelist);
				
		   }
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
		
		/*System.out.println("li.size()::: and current positon: of  "+stageName+" "+li.size()+" - "+position);*/
		for(int i=0;i<position;i++){
			/*System.out.println("Stage name in List: "+li.get(i).getStage_name());*/
			if(li.get(i).getStage_name().equalsIgnoreCase(stageName)){
				c++;
				errorId="Duplicate Entry of Stage Name Please Check It";
				flag=true;
				break;
			}
		 }
		/*System.out.println("Duplciate in Excel: "+c);*/
		return flag;
	}
}
