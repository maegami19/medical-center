package ua.nure.admin.summarytask.entity;

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
