package ua.nure.admin.summarytask.entity;

public class Doctor {

    private int id;
    private String firstname;
    private String lastname;
    private int countPatient;
    private String username;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getCountPatient() {
        return countPatient;
    }

    public void setCountPatient(int countPatient) {
        this.countPatient = countPatient;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return  id + "\t" + firstname + "\t" +  lastname + "\t" + countPatient + "\t" + category;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
