package ua.nure.admin.summarytask.service;

import ua.nure.admin.summarytask.entity.Patient;

import java.util.List;

public interface PatientService {

    /**
     * This method allows you to add a patient to the database.
     *
     * @param patient - patient, who needs to be added.
     */
    void addPatient(Patient patient);

    /**
     * This method allows you to get a list of patients in alphabetical order.
     *
     * @return list of patients.
     */
    List<Patient> getPatientsByAlphabet();

    /**
     * This method allows you to get a list of patients by date of birth.
     *
     * @return list of patients.
     */
    List<Patient> getPatientsByBirthday();

    /**
     * This method allows you to get a list of patients by a specific doctor.
     *
     * @param id - doctor's ID.
     * @return list of patients.
     */
    List<Patient> getPatientByDoctorId(int id);

    /**
     * This method allows you to get the patient's ID by username.
     *
     * @param username - patient's username.
     * @return patient's ID.
     */
    int getId(String username);

    /**
     * This method allows you to get the full name of the patient by ID.
     *
     * @param id - patient's ID.
     * @return patient's full name.
     */
    String getNameById(int id);

    /**
     * This method allows you to get the doctor's id on the patient's username.
     *
     * @param username - patient's username.
     * @return doctor's ID.
     */
    int getDoctorId(String username);
}
