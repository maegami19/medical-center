package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.PatientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/showMyPatients"})
public class ShowMyPatientsServlet extends HttpServlet {

    private static final String ALL_PATIENTS = "list_pat";
    private static final String SHOW_PATIENTS_PAGE = "pages/showPatientsPage.jsp";
    private static DoctorService doctorService;
    private static PatientService patientService;

    @Override
    public void init(ServletConfig servletConfig) {
        doctorService = (DoctorService) servletConfig.getServletContext().getAttribute(Constant.DOCTOR_SERVICE);
        patientService = (PatientService) servletConfig.getServletContext().getAttribute(Constant.PATIENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        User user = (User) httpServletRequest.getSession().getAttribute(Constant.USER);
        httpServletRequest.setAttribute(ALL_PATIENTS, patientService.getPatientByDoctorId(doctorService.getDoctorId(user.getUsername())));
        httpServletRequest.getRequestDispatcher(SHOW_PATIENTS_PAGE).forward(httpServletRequest, httpServletResponse);
    }
}
