package ua.nure.admin.summarytask.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.constant.DBConstant;
import ua.nure.admin.summarytask.entity.Schedule;
import ua.nure.admin.summarytask.repository.ScheduleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepositoryImpl extends AbstractRepository implements ScheduleRepository {

    private final Logger log = Logger.getLogger(ScheduleRepositoryImpl.class);

    @Override
    public void addSchedule(Schedule schedule) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.ADD_SCHEDULE);
            preparedStatement.setString(1, schedule.getDatetime());
            preparedStatement.setInt(2, schedule.getDoctorId());
            preparedStatement.execute();
            log.info("Schedule added successfully.");
        } catch (SQLException e) {
            log.error("Cannot add schedule: ", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public List<Schedule> getSchedulesByDoctorId(int id) {
        List<Schedule> schedules = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.FIND_SCHEDULES_BY_DOCTOR_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            schedules = extractSchedules(resultSet);
            log.info("Schedules got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get schedules: ", e);
        } finally {
            close(resultSet);
            close(connection);
            close(preparedStatement);
        }
        return schedules;
    }

    @Override
    public void updateScheduleStatus(int id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.UPDATE_SCHEDULE_STATUS);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            log.info("Schedule updated successfully.");
        } catch (SQLException e) {
            log.error("Cannot update schedule: ", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public List<Schedule> getSchedulesByDoctorIdAndStatus(int id) {
        List<Schedule> schedules = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.FIND_SCHEDULES_BY_DOCTOR_ID_AND_STATUS);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            schedules = extractSchedules(resultSet);
            log.info("Schedules got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get schedules: ", e);
        } finally {
            close(resultSet);
            close(connection);
            close(preparedStatement);
        }
        return schedules;
    }

    private List<Schedule> extractSchedules(ResultSet resultSet) throws SQLException {
        List<Schedule> doctors = new ArrayList<>();

        while (resultSet.next()) {
            Schedule schedule = new Schedule();

            schedule.setId(resultSet.getInt("id"));
            schedule.setDatetime(resultSet.getString("datetime"));
            schedule.setDoctorId(resultSet.getInt("doctor_id"));
            schedule.setStatus(resultSet.getString("status"));

            doctors.add(schedule);
        }
        return doctors;
    }
}
