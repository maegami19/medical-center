package ua.nure.admin.summarytask.repository;

import ua.nure.admin.summarytask.entity.Doctor;

import java.util.List;

public interface DoctorRepository {

    void addDoctor(Doctor doctor);
    List<String> getCategories();
    List<Doctor> getAllDoctors();
    List<Doctor> getDoctorsByAlphabet();
    List<Doctor> getDoctorsByCategory();
    List<Doctor> getDoctorsByCountOfPatient();
    int getDoctorId(String username);
}