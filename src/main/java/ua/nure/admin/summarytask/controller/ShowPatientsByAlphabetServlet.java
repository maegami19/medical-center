package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.service.PatientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/showPatientsByAlph"})
public class ShowPatientsByAlphabetServlet extends HttpServlet {

    private static final String ALL_PATIENTS = "list_pat";
    private static final String SHOW_PATIENTS_PAGE = "pages/showPatientsPage.jsp";
    private static PatientService patientService;

    @Override
    public void init(ServletConfig servletConfig) {
        patientService = (PatientService) servletConfig.getServletContext().getAttribute(Constant.PATIENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setAttribute(ALL_PATIENTS, patientService.getPatientsByAlphabet());
        httpServletRequest.getRequestDispatcher(SHOW_PATIENTS_PAGE).forward(httpServletRequest, httpServletResponse);
    }
}
