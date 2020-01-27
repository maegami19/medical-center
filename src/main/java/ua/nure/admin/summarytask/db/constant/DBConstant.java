package ua.nure.admin.summarytask.db.constant;

public class DBConstant {

    public static final String UPDATE_SCHEDULE_STATUS = "UPDATE schedules SET status='accepted' WHERE id=?";

    public static final String ADD_SCHEDULE = "INSERT INTO schedules VALUES(DEFAULT, ?, ?, 'created')";

    public static final String FIND_SCHEDULES_BY_DOCTOR_ID = "SELECT * FROM schedules WHERE doctor_id=? AND status='created'";

    public static final String FIND_SCHEDULES_BY_DOCTOR_ID_AND_STATUS = "SELECT * FROM schedules WHERE doctor_id=? AND status='accepted'";

    public static final String UPDATE_COUNT = "UPDATE doctors SET " +
            "count_patient = count_patient - 1 WHERE username=?";

    public static final String DELETE_USER = "DELETE FROM users WHERE username=?";

    public static final String DELETE_PATIENT = "DELETE FROM patients WHERE username=?";

    public static final String DELETE_DOCTOR = "DELETE FROM doctors WHERE username=?";

    public static final String DELETE_NURSE = "DELETE FROM nurses WHERE username=?";

    public static final String UPDATE_USER_PASSWORD = "UPDATE users SET password=? WHERE username=?";

    public static final String CHECK_USER = "SELECT * FROM users WHERE username=? AND password=?";

    public static final String CHECK_USER_BY_USERNAME = "SELECT * FROM users WHERE username=?";

    public static final String DB_URL = "java:comp/env/jdbc/medical_center";

    public static final String FIND_ALL_USERS = "SELECT * FROM users";

    public static final String ADD_USER = "INSERT INTO users VALUES(DEFAULT, ?, ?, ?, ?)";

    public static final String ADD_DOCTOR = "INSERT INTO doctors VALUES(DEFAULT, ?, ?, 0, ?, ?)";

    public static final String ADD_PATIENT = "INSERT INTO patients VALUES(DEFAULT, ?, ?, ?, ?, ?)";

    public static final String ADD_NURSE = "INSERT INTO nurses VALUES(DEFAULT, ?, ?, ?)";

    public static final String GET_CATEGORIES = "SELECT category FROM categories";

    public static final String CLOSE_MEDCARD = "UPDATE medcards SET status='closed', diagnosis=? WHERE id=?";

    public static final String ADD_NOTE_TO_MEDCARD = "INSERT INTO medcards VALUES(DEFAULT, ?, ?, ?, ?, ?, NULL)";

    public static final String GET_DOCTOR_ID = "SELECT id FROM doctors WHERE username=?";

    public static final String GET_NURSE_ID = "SELECT id FROM nurses WHERE username=?";

    public static final String GET_NAME_BY_ID = "SELECT firstname, lastname FROM patients WHERE id=?";

    public static final String GET_MEDCARD_BY_PATIENT = "SELECT * FROM medcards WHERE to_patient=? AND status='closed'";

    public static final String GET_MEDCARD_BY_ID = "SELECT * FROM medcards WHERE id=?";

    public static final String GET_DOCTOR_NAME_BY_ID = "SELECT firstname, lastname FROM doctors WHERE id=?";

    public static final String GET_PATIENTS_BY_DOCTOR_ID = "SELECT * FROM patients WHERE doctor_id=?";

    public static final String GET_PATIENTS_BY_BIRTHDAY = "SELECT * FROM patients ORDER BY birthday ASC";

    public static final String GET_PATIENTS_BY_ALPHABET = "SELECT * FROM patients ORDER BY lastname ASC";

    public static final String GET_DOCTORS_BY_ALPHABET = "SELECT * FROM doctors ORDER BY lastname ASC";

    public static final String GET_DOCTORS_BY_CATEGORY = "SELECT * FROM doctors ORDER BY category ASC";

    public static final String GET_MEDCARD_BY_DOCTOR = "SELECT * FROM medcards WHERE from_medic=? AND status='active'";

    public static final String GET_DOCTORS_BY_COUNT_OF_PATIENT = "SELECT * FROM doctors ORDER BY count_patient ASC";

    public static final String UPDATE_COUNT_PATIENT = "UPDATE doctors SET " +
            "count_patient = count_patient + 1 WHERE id=?";

    public static final String GET_ALL_MEDCARDS = "SELECT * FROM medcards WHERE to_patient=?";

    public static final String FIND_ALL_DOCTORS = "SELECT * FROM doctors";

    public static final String GET_PATIENT_ID = "SELECT id FROM patients WHERE username=?";

    public static final String GET_DOCTOR_ID_BY_USERNAME = "SELECT doctor_id FROM patients WHERE username=?";

    public static final String GET_NURSE_NAME_BY_ID = "SELECT firstname, lastname FROM nurses WHERE id=?";
}
