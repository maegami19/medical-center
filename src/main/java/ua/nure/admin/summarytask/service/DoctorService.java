package ua.nure.admin.summarytask.service;

import ua.nure.admin.summarytask.entity.Doctor;

import java.util.List;

public interface DoctorService {

    /**
     * This method allows you to check the parameters for subsequent transfer to the database.
     *
     * @param doctor - doctor that needs to be added.
     */
    void addDoctor(Doctor doctor);

    /**
     * This method allows you to take data from the category table from the database.
     *
     * @return list of categories of doctors.
     */
    List<String> getCategories();

    /**
     * This method allows you to get all the doctors from the database.
     *
     * @return list of doctors.
     */
    List<Doctor> getAllDoctors();

    /**
     * This method allows to get doctors from the database in alphabetical order.
     *
     * @return list of doctors.
     */
    List<Doctor> getDoctorsByAlphabet();

    /**
     * This method allows you to get doctors from the database by category.
     *
     * @return list of doctors.
     */
    List<Doctor> getDoctorsByCategory();

    /**
     * This method allows you to get doctors from the database by the number of patients.
     *
     * @return list of doctors.
     */
    List<Doctor> getDoctorsByCountOfPatient();

    /**
     * This method allows to get the doctor's ID from the database by his username.
     *
     * @param username - doctor's username.
     * @return doctor's id.
     */
    int getDoctorId(String username);

    /**
     * This method allows you to get the full name of the doctor by ID.
     *
     * @param id - doctor's ID.
     * @return doctor's name.
     */
    String getNameById(int id);
}
