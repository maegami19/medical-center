package ua.nure.admin.summarytask.entity;

import java.util.Objects;

public class Schedule {

    private int id;
    private String datetime;
    private int doctorId;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id == schedule.id &&
                doctorId == schedule.doctorId &&
                Objects.equals(datetime, schedule.datetime) &&
                Objects.equals(status, schedule.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datetime, doctorId, status);
    }
}
