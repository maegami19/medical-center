package ua.nure.admin.summarytask.service;

import ua.nure.admin.summarytask.entity.Medcard;

import java.util.List;

public interface MedcardService {

    /**
     * This method allows you to add an entry to the medical card in the database.
     *
     * @param medcard, which must be added
     */
    void addNote(Medcard medcard);

    /**
     * This method allows you to get a list of records by doctor's username.
     *
     * @param username - doctor's username.
     * @return list of medical card's records.
     */
    List<Medcard> getMedcardByDoctor(String username);

    /**
     * This method allows you to get a list of records by patient ID.
     *
     * @param id - patient's ID.
     * @return list of medical card's records.
     */
    List<Medcard> getMedcardByPatient(int id);

    /**
     * This method allows you to get a list of all records by patient ID.
     *
     * @param id - patient's ID.
     * @return list of medical card's records.
     */
    List<Medcard> getAllMedcards(int id);

    /**
     * This method allows you to close the record with the final diagnosis.
     *
     * @param medcard - medcard note.
     */
    void closeNote(Medcard medcard);

    /**
     * This method allows you to get records by ID in the database.
     *
     * @param id - medcard's ID;
     * @return list of medical card's records.
     */
    List<Medcard> getMedcardsById(int id);
}
