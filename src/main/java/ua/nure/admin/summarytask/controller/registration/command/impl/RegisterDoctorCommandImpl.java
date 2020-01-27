package ua.nure.admin.summarytask.controller.registration.command.impl;

import ua.nure.admin.summarytask.controller.registration.command.RegisterServletCommand;
import ua.nure.admin.summarytask.entity.Doctor;
import ua.nure.admin.summarytask.service.DoctorService;

import javax.servlet.http.HttpServletRequest;

public class RegisterDoctorCommandImpl implements RegisterServletCommand {

    @Override
    public void registerUser(HttpServletRequest request) {
        DoctorService doctorService = (DoctorService) request.getServletContext().getAttribute("doctorService");
        Doctor doctor = new Doctor();
        doctor.setFirstname(request.getParameter("firstname"));
        doctor.setLastname(request.getParameter("lastname"));
        doctor.setUsername(request.getParameter("username"));
        doctor.setCategory(request.getParameter("category"));
        doctorService.addDoctor(doctor);
    }
}
