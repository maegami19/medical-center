package ua.nure.admin.summarytask.service.impl;

import ua.nure.admin.summarytask.entity.Medcard;
import ua.nure.admin.summarytask.repository.MedcardRepository;
import ua.nure.admin.summarytask.service.MedcardService;

import java.util.List;

public class MedcardServiceImpl implements MedcardService {

    private MedcardRepository medcardRepository;

    public MedcardServiceImpl(MedcardRepository medcardRepository) {
        this.medcardRepository = medcardRepository;
    }

    @Override
    public void addNote(Medcard medcard) {
        if (validateMedcard(medcard)) {
            medcardRepository.addNote(medcard);
        }
    }

    @Override
    public List<Medcard> getMedcardByDoctor(String username) {
        return medcardRepository.getMedcardByDoctor(username);
    }

    @Override
    public List<Medcard> getMedcardByPatient(int id) {
        return medcardRepository.getMedcardByPatient(id);
    }

    @Override
    public List<Medcard> getAllMedcards(int id) {
        return medcardRepository.getAllMedcards(id);
    }

    @Override
    public void closeNote(Medcard medcard) {
        medcardRepository.closeNote(medcard);
    }

    @Override
    public List<Medcard> getMedcardsById(int id) {
        return medcardRepository.getMedcardsById(id);
    }

    private static boolean validateMedcard(Medcard medcard) {
        if (medcard.getFromMedic() == null || medcard.getToPatient() == 0 || medcard.getType() == null
                || medcard.getDescription() == null) {
            return false;
        }
        return true;
    }
}
