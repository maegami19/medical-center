package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.entity.Medcard;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.MedcardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/close_medcard"})
public class CloseMedcardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        MedcardService medcardService = (MedcardService) httpServletRequest.getServletContext().getAttribute("medcardService");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        httpServletRequest.setAttribute("medcards", medcardService.getMedcardByDoctor(user.getUsername()));
        httpServletRequest.getRequestDispatcher("pages/closemedcard.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        MedcardService medcardService = (MedcardService) httpServletRequest.getServletContext().getAttribute("medcardService");
        Medcard medcard = new Medcard();
        medcard.setId(Integer.parseInt(httpServletRequest.getParameter("medcard")));
        medcard.setDiagnosis(httpServletRequest.getParameter("diagnosis"));
        medcardService.closeNote(medcard);
    }
}
