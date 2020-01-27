package ua.nure.admin.summarytask.controller;

import com.itextpdf.text.DocumentException;
import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.entity.Medcard;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/download"})
public class DownloadPdfServlet extends HttpServlet {

    private static final String MEDCARD_PDF_LOCATION = "src/main/webapp/temppdf/medcard.pdf";
    private static final String MEDCARDS = "medcards";
    private static final String DOWNLOAD_MEDCARD_PAGE = "pages/downloadMedcardPage.jsp";
    private static final String DOCUMENT_TYPE = "pdf";

    private static MedcardService medcardService;
    private static PatientService patientService;
    private static DoctorService doctorService;
    private static PdfService pdfService;
    private static NurseService nurseService;
    private static File file;

    @Override
    public void init(ServletConfig servletConfig) {
        medcardService = (MedcardService) servletConfig.getServletContext().getAttribute(Constant.MEDCARD_SERVICE);
        patientService = (PatientService) servletConfig.getServletContext().getAttribute(Constant.PATIENT_SERVICE);
        doctorService = (DoctorService) servletConfig.getServletContext().getAttribute(Constant.DOCTOR_SERVICE);
        pdfService = (PdfService) servletConfig.getServletContext().getAttribute(Constant.PDF_SERVICE);
        nurseService = (NurseService) servletConfig.getServletContext().getAttribute(Constant.NURSE_SERVICE);
        file = new File(MEDCARD_PDF_LOCATION);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        User user = (User) httpServletRequest.getSession().getAttribute(Constant.USER);
        List<Medcard> medcards = medcardService.getMedcardByPatient(patientService.getId(user.getUsername()));
        httpServletRequest.setAttribute(MEDCARDS, medcards);
        httpServletRequest.getRequestDispatcher(DOWNLOAD_MEDCARD_PAGE).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        try {
            List<Medcard> medcards = medcardService.getMedcardsById(Integer.parseInt(httpServletRequest.getParameter(MEDCARDS)));
            String doctor = doctorService.getNameById(doctorService.getDoctorId(medcards.get(0).getFromMedic()));

            if ("".equals(doctor)) {
                pdfService.createPdf(medcards.get(0), nurseService.getNameById(nurseService.getNurseId(medcards.get(0).getFromMedic())),
                        patientService.getNameById(medcards.get(0).getToPatient()));
            } else {
                pdfService.createPdf(medcards.get(0), doctor,
                        patientService.getNameById(medcards.get(0).getToPatient()));
            }

            httpServletResponse.setContentType(DOCUMENT_TYPE);
            httpServletResponse.setHeader("Content-disposition", "attachment; filename=medcard.pdf");
            OutputStream out = httpServletResponse.getOutputStream();
            FileInputStream in = new FileInputStream(file);

            fileWrite(out, in);

            in.close();
            out.flush();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void fileWrite(OutputStream out, FileInputStream in) throws IOException {
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
    }
}
