package ua.nure.admin.summarytask.service.impl;

import ua.nure.admin.summarytask.entity.Schedule;
import ua.nure.admin.summarytask.repository.ScheduleRepository;
import ua.nure.admin.summarytask.service.ScheduleService;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void addSchedule(Schedule schedule) {
        scheduleRepository.addSchedule(schedule);
    }

    @Override
    public List<Schedule> getSchedulesByDoctorId(int id) {
        return scheduleRepository.getSchedulesByDoctorId(id);
    }

    @Override
    public void updateScheduleStatus(int id) {
        scheduleRepository.updateScheduleStatus(id);
    }

    @Override
    public List<Schedule> getSchedulesByDoctorIdAndStatus(int id) {
        return scheduleRepository.getSchedulesByDoctorIdAndStatus(id);
    }
}
