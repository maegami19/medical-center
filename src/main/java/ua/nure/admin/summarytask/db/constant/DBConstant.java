package ua.nure.admin.summarytask.db.constant;

public class DBConstant {

    public static final String DB_URL = "java:comp/env/jdbc/medical_center";

    public static final String FIND_ALL_USERS = "SELECT * FROM users";

    public static final String ADD_USER = "INSERT INTO users VALUES(DEFAULT, ?, ?, ?, ?)";

    public static final String ADD_DOCTOR = "INSERT INTO doctors VALUES(DEFAULT, ?, ?, 0, ?, ?)";

    public static final String ADD_PATIENT = "INSERT INTO patients VALUES(DEFAULT, ?, ?, ?, ?, ?)";

    public static final String ADD_NURSE = "INSERT INTO nurses VALUES(DEFAULT, ?, ?, ?)";

    public static final String GET_CATEGORIES = "SELECT category FROM categories";

    public static final String CLOSE_MEDCARD = "UPDATE medcards SET status='closed', diagnosis=? WHERE id=?";

    public static final String ADD_NOTE_TO_MEDCARD = "INSERT INTO medcards VALUES(DEFAULT, ?, ?, ?, ?, ?, NULL)";

    public static final String GET_DOCTOR_ID = "SELECT id FROM doctors WHERE username=";

    public static final String GET_NURSE_ID = "SELECT id FROM nurses WHERE username=";

    public static final String GET_PATIENTS_BY_DOCTOR_ID = "SELECT * FROM patients WHERE doctor_id=";

    public static final String GET_PATIENTS_BY_BIRTHDAY = "SELECT * FROM patients ORDER BY birthday ASC";

    public static final String GET_PATIENTS_BY_ALPHABET = "SELECT * FROM patients ORDER BY lastname ASC";

    public static final String GET_DOCTORS_BY_ALPHABET = "SELECT * FROM doctors ORDER BY lastname ASC";

    public static final String GET_DOCTORS_BY_CATEGORY = "SELECT * FROM doctors ORDER BY category ASC";

    public static final String GET_MEDCARD_BY_DOCTOR = "SELECT * FROM medcards WHERE from_medic=";

    public static final String GET_DOCTORS_BY_COUNT_OF_PATIENT = "SELECT * FROM doctors ORDER BY count_patient ASC";

    public static final String UPDATE_COUNT_PATIENT = "UPDATE doctors SET " +
                "count_patient = count_patient + 1 WHERE id= ?";

    public static final String GET_ALL_MEDCARDS = "SELECT * FROM medcards WHERE to_patient=";

    public static final String FIND_ALL_DOCTORS = "SELECT * FROM doctors";

    public static final String GET_PATIENT_ID = "SELECT id FROM patients WHERE username=";
}
