package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.entity.Medcard;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.MedcardService;
import ua.nure.admin.summarytask.service.PatientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/openMedcard"})
public class OpenMedcardServlet extends HttpServlet {

    private static final String PATIENTS_BY_DOCTOR = "patients";
    private static final String ALL_PATIENTS = "allpatients";
    private static final String OPEN_MEDCARD_PAGE = "pages/openMedcardPage.jsp";
    private static final String ACTIVE_STATUS = "active";
    private static final String MEDCARD_TYPE = "type";
    private static final String MEDCARD_TO_PATIENT = "to_patient";
    private static final String MEDCARD_DESCRIPTION = "description";
    private static DoctorService doctorService;
    private static PatientService patientService;
    private static MedcardService medcardService;

    @Override
    public void init(ServletConfig servletConfig) {
        doctorService = (DoctorService) servletConfig.getServletContext().getAttribute(Constant.DOCTOR_SERVICE);
        patientService = (PatientService) servletConfig.getServletContext().getAttribute(Constant.PATIENT_SERVICE);
        medcardService = (MedcardService) servletConfig.getServletContext().getAttribute(Constant.MEDCARD_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        User user = (User) httpServletRequest.getSession().getAttribute(Constant.USER);
        httpServletRequest.setAttribute(PATIENTS_BY_DOCTOR, patientService.getPatientByDoctorId(doctorService.getDoctorId(user.getUsername())));
        httpServletRequest.setAttribute(ALL_PATIENTS, patientService.getPatientsByAlphabet());
        httpServletRequest.getRequestDispatcher(OPEN_MEDCARD_PAGE).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        User user = (User) httpServletRequest.getSession().getAttribute(Constant.USER);
        Medcard medcard = new Medcard();

        medcard.setFromMedic(user.getUsername());
        medcard.setToPatient(Integer.parseInt(httpServletRequest.getParameter(MEDCARD_TO_PATIENT)));
        medcard.setType(httpServletRequest.getParameter(MEDCARD_TYPE));
        medcard.setDescription(httpServletRequest.getParameter(MEDCARD_DESCRIPTION));
        medcard.setStatus(ACTIVE_STATUS);

        medcardService.addNote(medcard);
        doGet(httpServletRequest, httpServletResponse);
    }
}