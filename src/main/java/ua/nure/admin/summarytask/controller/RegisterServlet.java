package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.entity.Doctor;
import ua.nure.admin.summarytask.entity.Nurse;
import ua.nure.admin.summarytask.entity.Patient;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.repository.DoctorRepository;
import ua.nure.admin.summarytask.repository.NurseRepository;
import ua.nure.admin.summarytask.repository.PatientRepository;
import ua.nure.admin.summarytask.repository.UserRepository;
import ua.nure.admin.summarytask.repository.impl.DoctorRepositoryImpl;
import ua.nure.admin.summarytask.repository.impl.NurseRepositoryImpl;
import ua.nure.admin.summarytask.repository.impl.PatientRepositoryImpl;
import ua.nure.admin.summarytask.repository.impl.UserRepositoryImpl;
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.NurseService;
import ua.nure.admin.summarytask.service.PatientService;
import ua.nure.admin.summarytask.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        DoctorService doctorService = (DoctorService) httpServletRequest.getServletContext().getAttribute("doctorService");
        httpServletRequest.setAttribute("list", doctorService.getCategories());
        httpServletRequest.setAttribute("list_doctor", doctorService.getAllDoctors());
        httpServletRequest.getRequestDispatcher("pages/register.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        UserService userService = (UserService) httpServletRequest.getServletContext().getAttribute("userService");
        String password = new Random().ints(10, 33, 122).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        User user = new User();
        user.setUsername(httpServletRequest.getParameter("username"));
        user.setEmail(httpServletRequest.getParameter("email"));
        user.setRole(httpServletRequest.getParameter("role"));
        user.setPassword(password);
        userService.addUser(user);

        if ("doctor".equals(httpServletRequest.getParameter("role"))) {
            DoctorService doctorService = (DoctorService) httpServletRequest.getServletContext().getAttribute("doctorService");
            Doctor doctor = new Doctor();
            doctor.setFirstname(httpServletRequest.getParameter("firstname"));
            doctor.setLastname(httpServletRequest.getParameter("lastname"));
            doctor.setUsername(httpServletRequest.getParameter("username"));
            doctor.setCategory(httpServletRequest.getParameter("category"));
            doctorService.addDoctor(doctor);
        } else if ("patient".equals(httpServletRequest.getParameter("role"))) {
            PatientService patientService = (PatientService) httpServletRequest.getServletContext().getAttribute("patientService");
            Patient patient = new Patient();
            patient.setFirstname(httpServletRequest.getParameter("firstname"));
            patient.setLastname(httpServletRequest.getParameter("lastname"));
            patient.setBirthday(httpServletRequest.getParameter("birthday_patient"));
            patient.setUsername(httpServletRequest.getParameter("username"));
            patient.setDoctorId(httpServletRequest.getParameter("doctor_id"));
            patientService.addPatient(patient);
        } else if ("nurse".equals(httpServletRequest.getParameter("role"))) {
            NurseService nurseService = (NurseService) httpServletRequest.getServletContext().getAttribute("nurseService");
            Nurse nurse = new Nurse();
            nurse.setFirstname(httpServletRequest.getParameter("firstname"));
            nurse.setLastname(httpServletRequest.getParameter("lastname"));
            nurse.setUsername(httpServletRequest.getParameter("username"));
            nurseService.addNurse(nurse);
        }
    }
}
