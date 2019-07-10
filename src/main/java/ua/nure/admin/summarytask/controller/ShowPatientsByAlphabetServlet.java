package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.service.PatientService;
import ua.nure.admin.summarytask.service.impl.PatientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/showpatients_by_alphabet"})
public class ShowPatientsByAlphabetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        PatientService patientService = (PatientService) httpServletRequest.getServletContext().getAttribute("patientService");;
        httpServletRequest.setAttribute("list_pat", patientService.getPatientsByAlphabet());
        httpServletRequest.getRequestDispatcher("pages/showpat.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
