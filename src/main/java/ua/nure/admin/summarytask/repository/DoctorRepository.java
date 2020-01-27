package ua.nure.admin.summarytask.repository;

import ua.nure.admin.summarytask.entity.Doctor;

import java.util.List;

public interface DoctorRepository {

    /**
     * This function implements adding a doctor to the table of doctors in the database.
     *
     * @param doctor - doctor that should be added to the database.
     * @return method result.
     */
    boolean addDoctor(Doctor doctor);

    /**
     * This method allows you to get a list of categories of doctors from the database.
     *
     * @return list of doctors.
     */
    List<String> getCategories();

    /**
     * This method allows you to get a list of all doctors from the table of doctors of the database.
     *
     * @return list of doctors.
     */
    List<Doctor> getAllDoctors();

    /**
     * This method allows you to get a list of all doctors in alphabetical order from
     * the table of doctors in the database.
     *
     * @return list of doctors.
     */
    List<Doctor> getDoctorsByAlphabet();

    /**
     * This method allows you to get a list of all doctors by category from the table of doctors of the database.
     *
     * @return list of doctors.
     */
    List<Doctor> getDoctorsByCategory();

    /**
     * This method allows you to get a list of all doctors by the number of patients from the
     * table of doctors of the database.
     *
     * @return list of doctors.
     */
    List<Doctor> getDoctorsByCountOfPatient();

    /**
     * This method allows you to get the doctor's ID by username.
     *
     * @param username - doctor's username.
     * @return doctor's ID.
     */
    int getDoctorId(String username);

    /**
     * This method allows you to get the full name of the doctor from the database by ID.
     *
     * @param id - doctor's ID.
     * @return full name.
     */
    String getNameById(int id);
}