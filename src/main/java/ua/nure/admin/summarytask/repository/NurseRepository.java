package ua.nure.admin.summarytask.repository;

import ua.nure.admin.summarytask.entity.Nurse;

public interface NurseRepository {

    void addNurse(Nurse nurse);
    int getNurseId(String username);
}
