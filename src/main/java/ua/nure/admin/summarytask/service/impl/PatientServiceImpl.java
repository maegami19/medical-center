package ua.nure.admin.summarytask.service.impl;

import ua.nure.admin.summarytask.entity.Patient;
import ua.nure.admin.summarytask.repository.PatientRepository;
import ua.nure.admin.summarytask.service.PatientService;

import java.util.List;

public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void addPatient(Patient patient) {
        if (validatePatient(patient)) {
            patientRepository.addPatient(patient);
        }
    }

    @Override
    public List<Patient> getPatientsByAlphabet() {
        return patientRepository.getPatientsByAlphabet();
    }

    @Override
    public List<Patient> getPatientsByBirthday() {
        return patientRepository.getPatientsByBirthday();
    }

    @Override
    public List<Patient> getPatientByDoctorId(int id) {
        return patientRepository.getPatientByDoctorId(id);
    }

    @Override
    public int getId(String username) {
        if (username != null) {
            return patientRepository.getId(username);
        } else {
            return -1;
        }
    }

    @Override
    public String getNameById(int id) {
        return patientRepository.getNameById(id);
    }

    @Override
    public int getDoctorId(String username) {
        return patientRepository.getDoctorId(username);
    }

    private static boolean validatePatient(Patient patient) {
        if (patient.getFirstname() == null || patient.getLastname() == null || patient.getBirthday() == null
                || patient.getUsername() == null || patient.getDoctorId() == null) {
            return false;
        }
        return true;
    }
}
