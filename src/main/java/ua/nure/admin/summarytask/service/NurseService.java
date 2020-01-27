package ua.nure.admin.summarytask.service;

import ua.nure.admin.summarytask.entity.Nurse;

public interface NurseService {

    /**
     * This method allows you to add a nurse to the database.
     *
     * @param nurse - nurse, who needs to be added.
     */
    void addNurse(Nurse nurse);

    /**
     * This method allows you to get the ID of the nurse by username.
     *
     * @param username - nurse's username.
     * @return nurse ID.
     */
    int getNurseId(String username);

    /**
     * This method allows you to get the full name of the nurse by ID.
     *
     * @param id - nurse's ID.
     * @return nurse full name.
     */
    String getNameById(int id);
}
