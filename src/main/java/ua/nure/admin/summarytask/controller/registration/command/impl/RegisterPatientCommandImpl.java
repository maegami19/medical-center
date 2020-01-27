package ua.nure.admin.summarytask.controller.registration.command.impl;

import ua.nure.admin.summarytask.controller.registration.command.RegisterServletCommand;
import ua.nure.admin.summarytask.entity.Patient;
import ua.nure.admin.summarytask.service.PatientService;

import javax.servlet.http.HttpServletRequest;

public class RegisterPatientCommandImpl implements RegisterServletCommand {

    @Override
    public void registerUser(HttpServletRequest request) {
        PatientService patientService = (PatientService) request.getServletContext().getAttribute("patientService");
        Patient patient = new Patient();
        patient.setFirstname(request.getParameter("firstname"));
        patient.setLastname(request.getParameter("lastname"));
        patient.setBirthday(request.getParameter("birthday_patient"));
        patient.setUsername(request.getParameter("username"));
        patient.setDoctorId(request.getParameter("doctor_id"));
        patientService.addPatient(patient);
    }
}
