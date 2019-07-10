package ua.nure.admin.summarytask.service;

import ua.nure.admin.summarytask.entity.Patient;

import java.util.List;

public interface PatientService {

    void addPatient(Patient patient);
    List<Patient> getPatientsByAlphabet();
    List<Patient> getPatientsByBirthday();
    List<Patient> getPatientByDoctorId(int id);
    int getId(String username);
}
