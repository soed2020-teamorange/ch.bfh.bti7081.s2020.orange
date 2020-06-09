package ch.bfh.bti7081.s2020.orange.backend.data;

public class Role {

  public static final String PATIENT = "patient";
  public static final String MEDICAL_SPECIALIST = "medicalSpecialist";
  public static final String ADMIN = "admin";

  private Role() {
  }

  public static String[] getAllRoles() {
    return new String[]{Role.PATIENT, Role.MEDICAL_SPECIALIST, Role.ADMIN};
  }

}
