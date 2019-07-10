package ua.nure.admin.summarytask.service.impl;

import ua.nure.admin.summarytask.entity.Nurse;
import ua.nure.admin.summarytask.repository.NurseRepository;
import ua.nure.admin.summarytask.service.NurseService;

public class NurseServiceImpl implements NurseService {

    private NurseRepository nurseRepository;

    public NurseServiceImpl(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    @Override
    public void addNurse(Nurse nurse) {
        nurseRepository.addNurse(nurse);
    }

    @Override
    public int getNurseId(String username) {
        return nurseRepository.getNurseId(username);
    }
}
