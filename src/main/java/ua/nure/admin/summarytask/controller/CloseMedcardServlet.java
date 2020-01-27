package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.entity.Medcard;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.MedcardService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/closeMedcard"})
public class CloseMedcardServlet extends HttpServlet {

    private static final String CLOSE_MEDCARD_PAGE = "pages/closeMedcardPage.jsp";
    private static final String MEDCARDS = "medcards";
    private static final String MEDCARD = "medcard";
    private static final String DIAGNOSIS = "diagnosis";
    private static MedcardService medcardService;

    @Override
    public void init(ServletConfig servletConfig) {
        medcardService = (MedcardService) servletConfig.getServletContext().getAttribute(Constant.MEDCARD_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        User user = (User) httpServletRequest.getSession().getAttribute(Constant.USER);
        httpServletRequest.setAttribute(MEDCARDS, medcardService.getMedcardByDoctor(user.getUsername()));
        httpServletRequest.getRequestDispatcher(CLOSE_MEDCARD_PAGE).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Medcard medcard = new Medcard();
        medcard.setId(Integer.parseInt(httpServletRequest.getParameter(MEDCARD)));
        medcard.setDiagnosis(httpServletRequest.getParameter(DIAGNOSIS));
        medcardService.closeNote(medcard);
        doGet(httpServletRequest, httpServletResponse);
    }
}
