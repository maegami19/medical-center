package ua.nure.admin.summarytask.service;

import ua.nure.admin.summarytask.entity.Nurse;

public interface NurseService {

    void addNurse(Nurse nurse);
    int getNurseId(String username);
}
