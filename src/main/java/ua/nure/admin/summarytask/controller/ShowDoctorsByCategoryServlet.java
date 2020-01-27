package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.service.DoctorService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/showDoctorsByCategory"})
public class ShowDoctorsByCategoryServlet extends HttpServlet {

    private static final String SHOW_DOCTORS_PAGE = "pages/showDoctorsPage.jsp";
    private static final String ALL_DOCTORS = "list_doc";
    private static DoctorService doctorService;

    @Override
    public void init(ServletConfig servletConfig) {
        doctorService = (DoctorService) servletConfig.getServletContext().getAttribute(Constant.DOCTOR_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setAttribute(ALL_DOCTORS, doctorService.getDoctorsByCategory());
        httpServletRequest.getRequestDispatcher(SHOW_DOCTORS_PAGE).forward(httpServletRequest, httpServletResponse);
    }
}
