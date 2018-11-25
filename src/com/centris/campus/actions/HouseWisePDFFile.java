package com.centris.campus.actions;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import com.centris.campus.util.HelperClass;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public class HouseWisePDFFile {
	
    int rownum = 1;
    HSSFSheet firstSheet;
    Collection<File> files;
    HSSFWorkbook workbook;
    File exactFile;

    CellStyle styleAbsent=null;
    CellStyle stylePresent=null;
    CellStyle styleWeekOff=null;
    CellStyle styleNone=null;
    CellStyle styleHoliday=null;
    CellStyle styleHeader=null;
    CellStyle stylemainHeader = null;
    Font fontAbsent=null;
    Font fontPresent=null;
    Font fontWeekOff=null;
    Font fontNone=null;
    Font fontHoliday=null;
    Font fontHeader=null;
    Font fontmainHeader = null;
    
    CellStyle styleReortName = null;
    Font fontReortName = null;
 
    public CellStyle AbsentCss() {
    	if(styleAbsent==null)
		 styleAbsent = workbook.createCellStyle();
    	if(fontAbsent==null)
		 fontAbsent = workbook.createFont();
    	fontAbsent.setColor(HSSFColor.RED.index);
		styleAbsent.setFont(fontAbsent);
		styleAbsent.setAlignment(styleAbsent.ALIGN_CENTER);
		styleAbsent.setBorderBottom((short) 1);
		styleAbsent.setBorderTop((short) 1);
		styleAbsent.setBorderLeft((short) 1);
		styleAbsent.setBorderRight((short) 1);
		return styleAbsent;
	}

	public CellStyle PresentCss() {
		if(stylePresent==null)
			stylePresent = workbook.createCellStyle();
		if(fontPresent==null)
		 fontPresent = workbook.createFont();
		fontPresent.setColor(HSSFColor.GREEN.index);
		stylePresent.setFont(fontPresent);
		stylePresent.setAlignment(stylePresent.ALIGN_CENTER);
		stylePresent.setBorderBottom((short) 1);
		stylePresent.setBorderTop((short) 1);
		stylePresent.setBorderLeft((short) 1);
		stylePresent.setBorderRight((short) 1);
		return stylePresent;
	}

	public CellStyle WeekOffCss() {
		if(styleWeekOff==null)
		styleWeekOff = workbook.createCellStyle();
		if(fontWeekOff==null)
		 fontWeekOff = workbook.createFont();
		fontWeekOff.setColor(HSSFColor.BLUE.index);
		styleWeekOff.setFont(fontWeekOff);
		styleWeekOff.setAlignment(styleWeekOff.ALIGN_CENTER);
		styleWeekOff.setBorderBottom((short) 1);
		styleWeekOff.setBorderTop((short) 1);
		styleWeekOff.setBorderLeft((short) 1);
		styleWeekOff.setBorderRight((short) 1);
		return styleWeekOff;
	}
	
	public CellStyle LeaveCss() {
		if(styleWeekOff==null)
		styleWeekOff = workbook.createCellStyle();
		if(fontWeekOff==null)
			fontWeekOff = workbook.createFont();
		fontWeekOff.setColor(HSSFColor.BLACK.index);
		styleWeekOff.setFont(fontWeekOff);
		styleWeekOff.setAlignment(styleHeader.ALIGN_LEFT);
		styleWeekOff.setVerticalAlignment(styleHeader.VERTICAL_CENTER);
		return styleWeekOff;
	}

	public CellStyle NoneCss() {
		if(styleNone==null)
		 styleNone = workbook.createCellStyle();
		if(fontNone==null)
		 fontNone = workbook.createFont();
		fontNone.setColor(HSSFColor.BLACK.index);
		styleNone.setFont(fontNone);
		styleNone.setAlignment(styleNone.ALIGN_CENTER);
		styleNone.setBorderBottom((short) 1);
		styleNone.setBorderTop((short) 1);
		styleNone.setBorderLeft((short) 1);
		styleNone.setBorderRight((short) 1);

		return styleNone;
	}
	public CellStyle HouseCss() {
		if(styleNone==null)
			styleNone = workbook.createCellStyle();
		if(fontNone==null)
			fontNone = workbook.createFont();
		fontNone.setColor(HSSFColor.BLACK.index);
		styleNone.setFont(fontNone);
		styleNone.setAlignment(styleNone.ALIGN_CENTER);
		styleNone.setBorderBottom((short) 8);
		styleNone.setBorderTop((short) 8);
		styleNone.setBorderLeft((short) 8);
		styleNone.setBorderRight((short) 8);
		styleNone.setVerticalAlignment(styleNone.VERTICAL_CENTER);
		
		return styleNone;
	}

	public CellStyle HeaderCss() {
		if(styleHeader==null)
		styleHeader = workbook.createCellStyle();
		if(fontHeader==null)
		 fontHeader = workbook.createFont();
		fontHeader.setColor(HSSFColor.BLACK.index);
		fontHeader.setBoldweight(Font.BOLDWEIGHT_BOLD);
		styleHeader.setFont(fontHeader);
		styleHeader.setWrapText(true);
		styleHeader.setAlignment(styleHeader.ALIGN_CENTER);
		styleHeader.setVerticalAlignment(styleHeader.VERTICAL_CENTER);
		styleHeader.setBorderBottom((short) 1);
		styleHeader.setBorderTop((short) 1);
		styleHeader.setBorderLeft((short) 1);
		styleHeader.setBorderRight((short) 1);
		styleHeader.setFillBackgroundColor(HSSFColor.GREY_80_PERCENT.index);
		return styleHeader;
	}
    
	public CellStyle HolidayCss() {
		if(styleHoliday==null)
		 styleHoliday = workbook.createCellStyle();
		if(fontHoliday==null)
			fontHoliday = workbook.createFont();
		fontHoliday.setColor(HSSFColor.BLACK.index);
		styleHoliday.setFont(fontHoliday);
		styleHoliday.setAlignment(styleHeader.ALIGN_LEFT);
		styleHoliday.setVerticalAlignment(styleHeader.VERTICAL_CENTER);
		return styleHoliday;
	}
	
	public CellStyle reportName() {
		if(styleReortName==null)
		 styleReortName = workbook.createCellStyle();
		if(fontReortName==null)
			fontReortName = workbook.createFont();
		fontReortName.setColor(HSSFColor.BLACK.index);
		fontReortName.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontReortName.setFontName(HSSFFont.FONT_ARIAL);
		styleReortName.setWrapText(true);
		styleReortName.setFont(fontReortName);
		styleReortName.setAlignment(styleHeader.ALIGN_CENTER);

		return styleReortName;
	}
	
	
	public CellStyle MainHeaderCss() {
		if (stylemainHeader == null)
			stylemainHeader = workbook.createCellStyle();
		if (fontmainHeader == null)
			fontmainHeader = workbook.createFont();
		fontmainHeader.setFontName(HSSFFont.FONT_ARIAL);
		fontmainHeader.setFontHeightInPoints((short) 13);
		fontmainHeader.setColor(HSSFColor.GREEN.index);
		fontmainHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		stylemainHeader.setFont(fontmainHeader);
		stylemainHeader.setAlignment(stylemainHeader.ALIGN_CENTER);
		stylemainHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		return stylemainHeader;
	}
	
	
	public void download( ArrayList<ReportMenuVo > list,String filePath, String locname, String accYearName, String branch) throws Exception {

		workbook = new HSSFWorkbook();
		firstSheet = workbook.createSheet("House Wise Report");

		Row row1 = firstSheet.createRow((short) 0);
		Cell cell = row1.createCell((short) 0);
		cell.setCellValue("STUDENT HOUSE WISE REPORT");
		cell.setCellStyle(reportName());
		row1.setHeight((short) 500);
		firstSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3+list.get(0).getExamMarkList().size()));
		
		Row headerRow1 = firstSheet.createRow(rownum);
		headerRow1.setHeightInPoints(15);
		
		Cell cellhn= headerRow1.createCell(0);
		cellhn.setCellValue("Branch : "+branch);
		cellhn.setCellStyle(HolidayCss()); 
		firstSheet.setColumnWidth(4,(short)1500);
		firstSheet.addMergedRegion(new CellRangeAddress(1, 1, 0,3));
		
		Cell cellhn2= headerRow1.createCell(4);
		cellhn2.setCellValue("Academic Year : "+accYearName);
		cellhn2.setCellStyle(LeaveCss()); 
		firstSheet.setColumnWidth((3+list.get(0).getExamMarkList().size())/2,(short)5000);
		firstSheet.addMergedRegion(new CellRangeAddress(1, 1,4,3+list.get(0).getExamMarkList().size()));
		/*
		for(int m=0; m<list.get(0).getExamMarkList().size()+3; m++){
			Cell cellh5n= headerRow1.createCell(m);
			cellh5n.setCellStyle(HeaderCss());
		}*/

		
		
		rownum=2;
		Row headerRow = firstSheet.createRow(rownum);
		headerRow.setHeightInPoints(30);
		Cell cellh0= headerRow.createCell(0);
		cellh0.setCellValue("Sl.No");
		cellh0.setCellStyle(HeaderCss()); 
		firstSheet.setColumnWidth(0,(short)1500);
		firstSheet.addMergedRegion(new CellRangeAddress(2, 3, 0,0));

		Cell cellh1= headerRow.createCell(1);
		cellh1.setCellValue("Class-Division");
		cellh1.setCellStyle(HeaderCss()); 
		firstSheet.setColumnWidth(1,(short)5000);
		firstSheet.addMergedRegion(new CellRangeAddress(2, 3, 1,1));

		Cell cellh2= headerRow.createCell(2);
		cellh2.setCellValue("Total Strength");
		cellh2.setCellStyle(HeaderCss()); 
		firstSheet.setColumnWidth(2,(short)3000);
		firstSheet.addMergedRegion(new CellRangeAddress(2, 3, 2,2));


		Cell cellh3= headerRow.createCell(3);
		cellh3.setCellValue("Not Allocated");
		cellh3.setCellStyle(HeaderCss()); 
		firstSheet.setColumnWidth(3,(short)3000);
		firstSheet.addMergedRegion(new CellRangeAddress(2, 3, 3,3));
		
		Cell cellh4= headerRow.createCell(4);
		cellh4.setCellValue("HOUSES");
		cellh4.setCellStyle(HeaderCss()); 
		firstSheet.addMergedRegion(new CellRangeAddress(2, 2, 4,3+list.get(0).getExamMarkList().size()));

		for(int m=5; m<5+list.get(0).getExamMarkList().size()-1; m++){
			headerRow.createCell(m).setCellStyle(HeaderCss());
		}
		
		int g=4;
		rownum=3;
		Row row2 = firstSheet.createRow(rownum);
		Cell cellh5= row2.createCell(0);
		cellh5.setCellStyle(HeaderCss());
		firstSheet.setColumnWidth(0,(short)1500);
		Cell cellh6= row2.createCell(1);
		cellh6.setCellStyle(HeaderCss());
		firstSheet.setColumnWidth(1,(short)5000);
		Cell cellh7= row2.createCell(2);
		cellh7.setCellStyle(HeaderCss());
		firstSheet.setColumnWidth(2,(short)3000);
		Cell cellh8= row2.createCell(3);
		cellh8.setCellStyle(HeaderCss());
		firstSheet.setColumnWidth(3,(short)3000);
		for (int i = 0; i <list.get(0).getExamMarkList().size(); i++) {
			Cell cellh9= row2.createCell(g++);
			cellh9.setCellValue(list.get(0).getExamMarkList().get(i).getHouseName());
			cellh9.setCellStyle(HeaderCss()); 
			firstSheet.setColumnWidth(4,(short)3000);
		}
		rownum=4;
		try {
			for (int j = 0; j < list.size(); j++) {
				Row row = firstSheet.createRow(rownum);
				ReportMenuVo vo   = list.get(j);
				int i=0;
				Cell cell0 = row.createCell(i++);
				cell0.setCellValue(j+1);
				cell0.setCellStyle(NoneCss()); 

				Cell cell1 = row.createCell(i++);
				cell1.setCellValue(vo.getClass_and_section());
				cell1.setCellStyle(NoneCss()); 

				Cell cell2= row.createCell(i++);
				cell2.setCellValue(vo.getCount());
				cell2.setCellStyle(NoneCss()); 

				Cell cell3 = row.createCell(i++);
				cell3.setCellValue(vo.getNoStudentcount());
				cell3.setCellStyle(NoneCss()); 

				
				int p=0;
				for (int k = 0; k < vo.getExamMarkList().size(); k++) {
					//String str = (String) iterator.next();
					Cell cell06 = row.createCell(i++);
					cell06.setCellValue(vo.getExamMarkList().get(k).getStudentcount());
					cell06.setCellStyle(NoneCss()); 
				}
                  rownum++;
			}
			int rowCount = firstSheet.getLastRowNum();
			firstSheet.shiftRows(0,rowCount,1); 
			
			Row schlrow = firstSheet.createRow((short) 0);
			Cell schlcell = schlrow.createCell((short) 0);
			schlcell.setCellValue(locname);
			schlcell.setCellStyle(MainHeaderCss());
			schlrow.setHeight((short) 500);
			firstSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3+list.get(0).getExamMarkList().size()));
			
			
			
			/*Row newheaderrow =  firstSheet.createRow(2);
			newheaderrow.setHeightInPoints(18);
			Cell newrow= newheaderrow.createCell(0);
			newrow.setCellValue("Branch : "+branch);
			newrow.setCellStyle(HolidayCss()); 
			firstSheet.setColumnWidth(4,(short)1500);
			firstSheet.addMergedRegion(new CellRangeAddress(1, 1, 0,(3+list.get(0).getExamMarkList().size())/2));*/
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		FileOutputStream fos1 = null;
		try {
			fos1=new FileOutputStream(new File(filePath));
			HSSFCellStyle hsfstyle=workbook.createCellStyle();

			workbook.write(fos1);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
