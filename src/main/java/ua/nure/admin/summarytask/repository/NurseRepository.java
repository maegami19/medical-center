package ua.nure.admin.summarytask.repository;

import ua.nure.admin.summarytask.entity.Nurse;

public interface NurseRepository {

    /**
     * This method allows you to add a medical sister to the database.
     *
     * @param nurse - nurse, who needs to be added.
     * @return method result.
     */
    boolean addNurse(Nurse nurse);

    /**
     * This method allows you to get a nurse ID by username from the database.
     *
     * @param username - nurse's username.
     * @return nurse's ID.
     */
    int getNurseId(String username);

    /**
     * This method allows you to get the full name of the nurse from the database by ID.
     *
     * @param id - nurse's ID.
     * @return nurse's full name.
     */
    String getNameById(int id);
}
