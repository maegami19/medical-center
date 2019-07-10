package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/show_my_patients"})
public class ShowMyPatientsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        DoctorService doctorService = (DoctorService) httpServletRequest.getServletContext().getAttribute("doctorService");
        PatientService patientService = (PatientService) httpServletRequest.getServletContext().getAttribute("patientService");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        httpServletRequest.setAttribute("list_pat", patientService.getPatientByDoctorId(doctorService.getDoctorId(user.getUsername())));
        httpServletRequest.getRequestDispatcher("pages/showpat.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
