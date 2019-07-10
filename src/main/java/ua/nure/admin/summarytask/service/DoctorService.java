package ua.nure.admin.summarytask.service;

import ua.nure.admin.summarytask.entity.Doctor;
import ua.nure.admin.summarytask.entity.Patient;

import java.util.List;

public interface DoctorService {

    void addDoctor(Doctor doctor);
    List<String> getCategories();
    List<Doctor> getAllDoctors();
    List<Doctor> getDoctorsByAlphabet();
    List<Doctor> getDoctorsByCategory();
    List<Doctor> getDoctorsByCountOfPatient();
    int getDoctorId(String username);
}
