package ua.nure.admin.summarytask.entity;

import java.util.Objects;

public class Medcard {

    private int id;
    private String fromMedic;
    private int toPatient;
    private String type;
    private String description;
    private String status;
    private String diagnosis;

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromMedic() {
        return fromMedic;
    }

    public void setFromMedic(String fromMedic) {
        this.fromMedic = fromMedic;
    }

    public int getToPatient() {
        return toPatient;
    }

    public void setToPatient(int toPatient) {
        this.toPatient = toPatient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Medcard{" +
                "id=" + id +
                ", fromMedic='" + fromMedic + '\'' +
                ", toPatient=" + toPatient +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medcard medcard = (Medcard) o;
        return id == medcard.id &&
                toPatient == medcard.toPatient &&
                Objects.equals(fromMedic, medcard.fromMedic) &&
                Objects.equals(type, medcard.type) &&
                Objects.equals(description, medcard.description) &&
                Objects.equals(status, medcard.status) &&
                Objects.equals(diagnosis, medcard.diagnosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromMedic, toPatient, type, description, status, diagnosis);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
