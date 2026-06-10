import java.util.regex.Pattern;

public class RegistrationService {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public boolean registerUser(String email, int age)
            throws InvalidEmailException {

        assert email != null : "System Error: Email input is null";

        if (email == null || email.trim().isEmpty()) {

            throw new InvalidEmailException(
                    "Registration failed: Email cannot be null or empty.");
        }

        if (!pattern.matcher(email).matches()) {

            throw new InvalidEmailException(
                    "Registration failed: Invalid email format -> " + email);
        }

        if (age < 18) {

            throw new UnderageException(
                    "Registration failed: User age " + age +
                            " is below the minimum required age of 18.");
        }

        return true;
    }
}