package ua.nure.admin.summarytask.service.impl;

import ua.nure.admin.summarytask.entity.Doctor;
import ua.nure.admin.summarytask.repository.DoctorRepository;
import ua.nure.admin.summarytask.service.DoctorService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void addDoctor(Doctor doctor) {
        if (validateDoctor(doctor)) {
            doctorRepository.addDoctor(doctor);
        }
    }

    @Override
    public List<String> getCategories() {
        return doctorRepository.getCategories();
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.getAllDoctors();
    }

    @Override
    public List<Doctor> getDoctorsByAlphabet() {
        return doctorRepository.getDoctorsByAlphabet();
    }

    @Override
    public List<Doctor> getDoctorsByCategory() {
        return doctorRepository.getDoctorsByCategory();
    }

    @Override
    public List<Doctor> getDoctorsByCountOfPatient() {
        return doctorRepository.getDoctorsByCountOfPatient();
    }

    @Override
    public int getDoctorId(String username) {
        if (username != null) {
            return doctorRepository.getDoctorId(username);
        } else {
            return -1;
        }
    }

    @Override
    public String getNameById(int id) {
        return doctorRepository.getNameById(id);
    }

    private static boolean validateDoctor(Doctor doctor) {
        if (doctor.getFirstname() == null || doctor.getLastname() == null || doctor.getUsername() == null
                || doctor.getCategory() == null) {
            return false;
        }
        return true;
    }
}
