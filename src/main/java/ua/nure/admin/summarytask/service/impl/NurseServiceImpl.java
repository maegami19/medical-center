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
        if (validateNurse(nurse)) {
            nurseRepository.addNurse(nurse);
        }
    }

    @Override
    public int getNurseId(String username) {
        if (username != null) {
            return nurseRepository.getNurseId(username);
        } else {
            return -1;
        }
    }

    @Override
    public String getNameById(int id) {
        return nurseRepository.getNameById(id);
    }

    private static boolean validateNurse(Nurse nurse) {
        if (nurse.getFirstname() == null || nurse.getLastname() == null || nurse.getUsername() == null) {
            return false;
        }
        return true;
    }
}
