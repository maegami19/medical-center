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
        patientRepository.addPatient(patient);
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
        return patientRepository.getId(username);
    }
}
