package ua.nure.admin.summarytask.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
import ua.nure.admin.summarytask.entity.Medcard;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFGenerator {

    private static int FONT_SIZE_SMALL = 16;
    private static int FONT_SIZE_BIG = 32;

    public static void createPDFFile(Medcard medcard, String doctorName, String patientName) throws FileNotFoundException, DocumentException {
        Document document = new Document();

        Font font1 = new Font(Font.FontFamily.HELVETICA,
                FONT_SIZE_BIG, Font.BOLD);
        Font font2 = new Font(Font.FontFamily.HELVETICA,
                FONT_SIZE_SMALL, Font.ITALIC);

        PdfWriter.getInstance(document,
                new FileOutputStream("src/main/webapp/temppdf/medcard.pdf"));

        document.open();

        Paragraph title = new Paragraph("Medcard", font1);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(FONT_SIZE_BIG);
        document.add(title);

        Paragraph fromParagraph = new Paragraph("From: " + doctorName, font2);
        fromParagraph.setFont(font2);
        fromParagraph.setSpacingAfter(8);
        document.add(fromParagraph);

        Paragraph toParagraph = new Paragraph("To: " + patientName, font2);
        toParagraph.setFont(font2);
        toParagraph.setSpacingAfter(8);
        document.add(toParagraph);

        Paragraph typeParagraph = new Paragraph("Type: " + medcard.getType(), font2);
        typeParagraph.setFont(font2);
        typeParagraph.setSpacingAfter(8);
        document.add(typeParagraph);

        Paragraph descriptionParagraph = new Paragraph("Description: " + medcard.getDescription(), font2);
        descriptionParagraph.setFont(font2);
        descriptionParagraph.setSpacingAfter(8);
        document.add(descriptionParagraph);

        Paragraph statusParagraph = new Paragraph("Status: " + medcard.getStatus(), font2);
        statusParagraph.setFont(font2);
        statusParagraph.setSpacingAfter(8);
        document.add(statusParagraph);

        Paragraph diagnosisParagraph = new Paragraph("Diagnosis: " + medcard.getDiagnosis(), font2);
        diagnosisParagraph.setFont(font2);
        diagnosisParagraph.setSpacingAfter(8);
        document.add(diagnosisParagraph);

        document.close();
    }
}
