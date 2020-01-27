package ua.nure.admin.summarytask.service;

import com.itextpdf.text.DocumentException;
import ua.nure.admin.summarytask.entity.Medcard;

import java.io.FileNotFoundException;

public interface PdfService {

    /**
     * This method allows you to create a PDF document with an extract on certain data.
     *
     * @param medcard     - patient's medcard.
     * @param doctorName  - doctor's full name.
     * @param patientName - patient's full name.
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    void createPdf(Medcard medcard, String doctorName, String patientName) throws FileNotFoundException, DocumentException;
}
