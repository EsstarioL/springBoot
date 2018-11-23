# springBoot


import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Main {
	public static void main(String[] args) throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		PDDocument document = PDDocument.load(new File("C:/testFolder/markup.pdf"));
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		stripper.setSortByPosition(true);
		List<PDAnnotation> annot = new ArrayList<>();
			for (int i = 0; i < document.getNumberOfPages(); i++ )	{
				PDPage page = document.getPage(i);
				annot = page.getAnnotations();
					for (int k = 0; k < annot.size(); k++) {
						if (annot.get(k).getContents() !=null){
							System.out.println("Annotation author: " + annot.get(k).getCOSObject().getNameAsString(COSName.T));
							System.out.println("Annotation text: " + annot.get(k).getContents() + "\n");
							stripper.addRegion( "annotation", convertRectangle(annot.get(k).getRectangle(), page) );
							stripper.extractRegions(page);
							String string = stripper.getTextForRegion( "annotation" );
							System.out.println(stripper.getTextForRegion("annotation").contains("13L50138A-0200PR-FD100"));
							System.out.println("This annotation contains: "+"\n\n" + string);
							System.out.println("******************");
							stripper.removeRegion("annotation");
						  
			  	 }
						else {
//		  		 System.out.println("Annotations not found!");
//		  		 System.out.println("******************");
						}		   
					}
			}
	}
	public static Rectangle convertRectangle(PDRectangle pdRectangle, PDPage page) {
		if (page.getRotation() == 90) {
			return new Rectangle((int)pdRectangle.getLowerLeftX(), (int)pdRectangle.getLowerLeftY(),(int)pdRectangle.getHeight(), (int)pdRectangle.getWidth());
		}
		else {
			int pageHeight = (int)page.getMediaBox().getHeight();
			int leftUpperX = (int)pdRectangle.getLowerLeftX();
	    int leftUpperY = (int)(pageHeight - pdRectangle.getUpperRightY());
      return new Rectangle(leftUpperX, leftUpperY,(int)pdRectangle.getWidth(), (int)pdRectangle.getHeight());
		}
	}
}
