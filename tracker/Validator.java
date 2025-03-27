package tracker;
/**
 * A class for validating data entered by the user.
 * Contains methods to validate data entry for students and courses.
 */
public class Validator {
    private static final String NAME_REGEX = "^(?!.*['-]{2})[A-Za-z][A-Za-z' -]*[A-Za-z]$";

    public static boolean isValidName(String name) {
        return name.matches(NAME_REGEX) && name.length() >= 2;
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[^@]+@[^@]+\\.[^@]+$");
    }
}