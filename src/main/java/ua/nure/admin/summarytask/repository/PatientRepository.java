package ua.nure.admin.summarytask.repository;

import ua.nure.admin.summarytask.entity.Patient;

import java.util.List;

public interface PatientRepository {

    void addPatient(Patient patient);
    List<Patient> getPatientsByAlphabet();
    List<Patient> getPatientsByBirthday();
    List<Patient> getPatientByDoctorId(int id);
    int getId(String username);
}
