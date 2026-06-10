import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationServiceTest {

    private RegistrationService service;

    @BeforeEach
    void setUp() {

        service = new RegistrationService();
    }

    @Test
    void testValidRegistration()
            throws InvalidEmailException {

        boolean result = service.registerUser(
                "user@example.com",
                20);

        assertTrue(result);
    }

    @Test
    void testInvalidEmail() {

        assertThrows(
                InvalidEmailException.class,
                () -> service.registerUser(
                        "wrongemail",
                        20));
    }

    @Test
    void testUnderage() {

        assertThrows(
                UnderageException.class,
                () -> service.registerUser(
                        "teen@gmail.com",
                        15));
    }
}