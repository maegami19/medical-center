package ua.nure.admin.summarytask.repository;

import ua.nure.admin.summarytask.entity.Medcard;

import java.util.List;

public interface MedcardRepository {

    void addNote(Medcard medcard);
    List<Medcard> getMedcardByDoctor(String username);
    void closeNote(Medcard medcard);
    List<Medcard> getAllMedcards(int id);
}
