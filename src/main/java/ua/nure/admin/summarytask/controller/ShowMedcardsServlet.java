package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.MedcardService;
import ua.nure.admin.summarytask.service.PatientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/showMedcards"})
public class ShowMedcardsServlet extends HttpServlet {

    private static final String SHOW_MEDCARDS_PAGE = "pages/showMedcardsPage.jsp";
    private static final String ALL_MEDCARDS = "list_med";
    private static MedcardService medcardService;
    private static PatientService patientService;

    @Override
    public void init(ServletConfig servletConfig) {
        medcardService = (MedcardService) servletConfig.getServletContext().getAttribute(Constant.MEDCARD_SERVICE);
        patientService = (PatientService) servletConfig.getServletContext().getAttribute(Constant.PATIENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        User user = (User) httpServletRequest.getSession().getAttribute(Constant.USER);
        httpServletRequest.setAttribute(ALL_MEDCARDS, medcardService.getAllMedcards(patientService.getId(user.getUsername())));
        httpServletRequest.getRequestDispatcher(SHOW_MEDCARDS_PAGE).forward(httpServletRequest, httpServletResponse);
    }
}
