package ua.nure.admin.summarytask.service;

import ua.nure.admin.summarytask.entity.Medcard;

import java.util.List;

public interface MedcardService {

    void addNote(Medcard medcard);
    List<Medcard> getMedcardByDoctor(String username);
    List<Medcard> getAllMedcards(int id);
    void closeNote(Medcard medcard);
}
