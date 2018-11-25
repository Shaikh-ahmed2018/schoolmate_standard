package com.centris.campus.actions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.centris.campus.util.HelperClass;
import com.centris.campus.vo.ReportMenuVo;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

public class HouseWiseExcelFile {

	 private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	            Font.BOLD);
	    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL, BaseColor.RED);
	    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	            Font.BOLD);
	    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.BOLD);
	    private static Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
	public void download(ArrayList<ReportMenuVo> arr, String filePath, String accyearid, String location, String branch) throws FileNotFoundException, DocumentException {
		
		Document document = new Document();
		PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(filePath));
	    document.open();
	    addContent(document,arr,writer,accyearid,location,branch);
	    document.close();
	    //("INSIDE MY CLASS");
	}
	 private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	 }
	 public static void onEndPage(PdfWriter writer, Document document, String accyearid, String location, String branch) {
		 Paragraph pn=new Paragraph();
		 addEmptyLine(pn,2);
		 try {
			document.add(pn);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
         PdfContentByte cb = writer.getDirectContent();
         Phrase header = new Phrase("STUDENT HOUSE WISE REPORT", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20f));
         ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                 header,
                 (document.right() - document.left()) / 2 + document.leftMargin(),
                 document.top() + 0, 0);
         

         Chunk glue = new Chunk(new VerticalPositionMark());
         Paragraph p = new Paragraph("School Name : "+location);
         
         p.setMultipliedLeading(0);
         p.add(new Chunk(glue));
         p.add("Academic Year : "+accyearid);
         Paragraph p1 = new Paragraph("Branch : "+branch);
         p1.setSpacingAfter(10);
         try {
        	 addEmptyLine(p,2);
        	 document.add(p);
        	 document.add(p1);
         } catch (DocumentException e) {
        	 e.printStackTrace();
         }
        
     }
    private static void addContent(Document document,ArrayList<ReportMenuVo> arr,PdfWriter writer, String accyearid, String location, String branch) throws DocumentException {
    	Paragraph p=new Paragraph();
        addEmptyLine(p,1);
        try {
			document.add(p);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
       
    	onEndPage(writer,document,accyearid,location,branch);
        createTable(document,arr);
    }
  
    private static void createTable(Document document,ArrayList<ReportMenuVo> arr)
            throws BadElementException {
        PdfPTable table = new PdfPTable(4+arr.get(0).getExamMarkList().size());
        table.setWidthPercentage(100);
        int[] size=new int[4+arr.get(0).getExamMarkList().size()];
        size[0]=1;
    	size[1]=3;
    	size[2]=2;
    	size[3]=2;
        for(int l=4; l<4+arr.get(0).getExamMarkList().size(); l++){
        	 size[l]=2;
        }
        
        try {
			table.setWidths(size);
			table.setSpacingAfter(20);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
       
        PdfPCell c1 = new PdfPCell(new Phrase("Sl.No"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        c1.setColspan(1);
        table.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase("Class-Division"));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        c2.setRowspan(2);
        table.addCell(c2);

        PdfPCell c3 = new PdfPCell(new Phrase("Total Strength"));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        c3.setRowspan(2);
        table.addCell(c3);
        
        PdfPCell c4 = new PdfPCell(new Phrase("Not Allocated"));
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
          c4.setRowspan(2);
        table.addCell(c4);
        
        PdfPCell c5= new PdfPCell(new Phrase("HOUSES"));
        c5.setHorizontalAlignment(Element.ALIGN_CENTER);
        c5.setColspan(arr.get(0).getExamMarkList().size());
        c5.setFixedHeight(20f);
        table.addCell(c5);
        table.setHeaderRows(1);
       /* 
        PdfPCell c6 = new PdfPCell(new Phrase("S.No"));
        c6.setHorizontalAlignment(Element.ALIGN_CENTER);
       c6.setRowspan(2);
        table.addCell(c6);

        PdfPCell c7 = new PdfPCell(new Phrase("Class-Division"));
        c7.setHorizontalAlignment(Element.ALIGN_CENTER);
        c7.setRowspan(2);
        table.addCell(c7);

        PdfPCell c8 = new PdfPCell(new Phrase("Total"));
        c8.setHorizontalAlignment(Element.ALIGN_CENTER);
        c8.setRowspan(2);
        table.addCell(c8);
        
        PdfPCell c9 = new PdfPCell(new Phrase("Not Allocated"));
        c9.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c9);*/
        
        for(int k=0; k<arr.get(0).getExamMarkList().size(); k++){
        	 PdfPCell c10 = new PdfPCell(new Phrase(arr.get(0).getExamMarkList().get(k).getHouseName()));
             c10.setHorizontalAlignment(Element.ALIGN_CENTER);
             table.addCell(c10);
        }
        table.setHeaderRows(2);
       
        for(int i=0; i<arr.size(); i++){
        	PdfPCell cn= new PdfPCell(new Phrase(Integer.toString(arr.get(i).getHousecount())));
        	cn.setHorizontalAlignment(Element.ALIGN_CENTER);
        	cn.setFixedHeight(20f);
        	table.addCell(cn);

        	PdfPCell cn1= new PdfPCell(new Phrase(arr.get(i).getClass_and_section()));
        	cn1.setHorizontalAlignment(Element.ALIGN_LEFT);
        	 cn1.setFixedHeight(20f);
        	table.addCell(cn1);

        	PdfPCell cn2= new PdfPCell(new Phrase(Integer.toString(arr.get(i).getCount())));
        	cn2.setHorizontalAlignment(Element.ALIGN_CENTER);
        	 cn2.setFixedHeight(20f);
        	table.addCell(cn2);

        	PdfPCell cn3= new PdfPCell(new Phrase(arr.get(i).getNoStudentcount()));
        	cn3.setHorizontalAlignment(Element.ALIGN_CENTER);
        	 cn3.setFixedHeight(20f);
        	table.addCell(cn3);


        	for(int j=0; j<arr.get(i).getExamMarkList().size(); j++){

        		PdfPCell cn4= new PdfPCell(new Phrase(arr.get(i).getExamMarkList().get(j).getStudentcount()));
        		cn4.setHorizontalAlignment(Element.ALIGN_CENTER);
        		 cn4.setFixedHeight(20f);
        		table.addCell(cn4);
        	}
        }
        try {
			document.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
