package ua.nure.admin.summarytask.repository;

import ua.nure.admin.summarytask.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    /**
     * This method allows you to add an entry to the schedule.
     *
     * @param schedule - schedule's note, which needs to be added.
     */
    void addSchedule(Schedule schedule);

    /**
     * This method allows you to get a list of records by doctor ID.
     *
     * @param id - doctor's ID.
     * @return list of schedule's notes.
     */
    List<Schedule> getSchedulesByDoctorId(int id);

    /**
     * This method allows you to update the status of the record to the doctor.
     *
     * @param id - schedule's note ID.
     */
    void updateScheduleStatus(int id);

    /**
     * This method allows you to get a list of records by doctor ID and status.
     *
     * @param id - doctor's ID.
     * @return list of schedule's notes.
     */
    List<Schedule> getSchedulesByDoctorIdAndStatus(int id);
}
