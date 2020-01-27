package ua.nure.admin.summarytask.repository;

import ua.nure.admin.summarytask.entity.Medcard;

import java.util.List;

public interface MedcardRepository {

    /**
     * This method allows you to add a new record to the database.
     *
     * @param medcard - medcard's note, which needs to be added.
     * @return method result.
     */
    boolean addNote(Medcard medcard);

    /**
     * This method allows you to get records for the doctor's username from the database.
     *
     * @param username - doctor's username.
     * @return list of medcards.
     */
    List<Medcard> getMedcardByDoctor(String username);

    /**
     * This method allows you to get records by patient ID from the database.
     *
     * @param id - patient's ID.
     * @return list of medcards.
     */
    List<Medcard> getMedcardByPatient(int id);

    /**
     * This method allows you to close an entry in the medical record.
     *
     * @param medcard - medcard, which needs to be closed.
     * @return method result.
     */
    boolean closeNote(Medcard medcard);

    /**
     * This method allows you to get records by patient ID from the database.
     *
     * @param id - patient's ID.
     * @return list of medcards.
     */
    List<Medcard> getAllMedcards(int id);

    /**
     * This method allows you to get records by ID from the database.
     *
     * @param id - medcard's ID.
     * @return list of medcards.
     */
    List<Medcard> getMedcardsById(int id);
}
