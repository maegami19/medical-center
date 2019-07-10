package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.MedcardService;
import ua.nure.admin.summarytask.service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/showmed"})
public class ShowMedcardsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        MedcardService medcardService = (MedcardService) httpServletRequest.getServletContext().getAttribute("medcardService");
        PatientService patientService = (PatientService) httpServletRequest.getServletContext().getAttribute("patientService");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        httpServletRequest.setAttribute("list_med", medcardService.getAllMedcards(patientService.getId(user.getUsername())));
        httpServletRequest.getRequestDispatcher("pages/showmed.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
