package ua.nure.admin.summarytask.service.impl;

import com.itextpdf.text.DocumentException;
import ua.nure.admin.summarytask.entity.Medcard;
import ua.nure.admin.summarytask.pdf.PDFGenerator;
import ua.nure.admin.summarytask.service.PdfService;

import java.io.FileNotFoundException;

public class PdfServiceImpl implements PdfService {

    @Override
    public void createPdf(Medcard medcard, String doctorName, String patientName) throws FileNotFoundException, DocumentException {
        PDFGenerator.createPDFFile(medcard, doctorName, patientName);
    }
}
