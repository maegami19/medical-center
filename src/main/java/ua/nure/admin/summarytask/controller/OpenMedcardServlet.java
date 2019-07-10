package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.entity.Medcard;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.MedcardService;
import ua.nure.admin.summarytask.service.NurseService;
import ua.nure.admin.summarytask.service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/open_medcard"})
public class OpenMedcardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        DoctorService doctorService = (DoctorService) httpServletRequest.getServletContext().getAttribute("doctorService");
        PatientService patientService = (PatientService) httpServletRequest.getServletContext().getAttribute("patientService");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        httpServletRequest.setAttribute("patients", patientService.getPatientByDoctorId(doctorService.getDoctorId(user.getUsername())));
        httpServletRequest.setAttribute("allpatients", patientService.getPatientsByAlphabet());
        httpServletRequest.getRequestDispatcher("pages/openmedcard.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        MedcardService medcardService = (MedcardService) httpServletRequest.getServletContext().getAttribute("medcardService");
        DoctorService doctorService = (DoctorService) httpServletRequest.getServletContext().getAttribute("doctorService");
        NurseService nurseService = (NurseService) httpServletRequest.getServletContext().getAttribute("nurseService");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Medcard medcard = new Medcard();

        medcard.setFromMedic(user.getUsername());
        medcard.setToPatient(Integer.parseInt(httpServletRequest.getParameter("to_patient")));
        medcard.setType(httpServletRequest.getParameter("type"));
        medcard.setDescription(httpServletRequest.getParameter("description"));
        medcard.setStatus("active");

        medcardService.addNote(medcard);
    }
}