public class Main {

    public static void main(String[] args) {

        RegistrationService service = new RegistrationService();

        try {

            boolean result = service.registerUser(
                    "user@example.com",
                    20);

            System.out.println(
                    "Registration Successful: " + result);

        } catch (InvalidEmailException e) {

            System.out.println(e.getMessage());

        } catch (UnderageException e) {

            System.out.println(e.getMessage());
        }
    }
}