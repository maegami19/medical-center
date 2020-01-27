package ua.nure.admin.summarytask.service;

import ua.nure.admin.summarytask.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    /**
     * This method allows you to add a note to the schedule.
     *
     * @param schedule - note, which needs to be added.
     */
    void addSchedule(Schedule schedule);

    /**
     * This method allows you to get a list of records by doctor ID.
     *
     * @param id - doctor's ID.
     * @return list of schedules.
     */
    List<Schedule> getSchedulesByDoctorId(int id);

    /**
     * This method allows you to update the status of an entry in the schedule.
     *
     * @param id - schedule's ID.
     */
    void updateScheduleStatus(int id);

    /**
     * This method allows you to get a list of records by doctor ID and status.
     *
     * @param id - schedule's ID.
     * @return list of schedules
     */
    List<Schedule> getSchedulesByDoctorIdAndStatus(int id);
}
