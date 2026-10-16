
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class CinemaSeatBookingSystemTest {

    public static void main(String[] args) {
        runTest("Display seats", "1\n4\n",
                "Seat 1 - AVAILABLE");

        runTest("Valid booking",
                "2\nAmrutha\n5\n4\n",
                "Booking successful!");

        runTest("Empty customer name",
                "2\n\n4\n",
                "Name cannot be empty.");

        runTest("Invalid seat number 0",
                "2\nAmrutha\n0\n4\n",
                "Invalid seat number.");

        runTest("Invalid seat number 11",
                "2\nAmrutha\n11\n4\n",
                "Invalid seat number.");

        runTest("Non-numeric seat number",
                "2\nAmrutha\nabc\n4\n",
                "Invalid seat number.");

        runTest("Invalid menu input",
                "abc\n4\n",
                "Please enter a valid menu number.");

        runTest("Invalid menu choice",
                "7\n4\n",
                "Invalid choice. Try again.");

        runTest("Cancel unbooked seat",
                "3\n5\n4\n",
                "This seat is not booked.");

        runTest("Cancel a booked seat",
                "2\nAmrutha\n5\n3\n5\n4\n",
                "Seat is now available.");

        runTest("Duplicate booking",
                "2\nAmrutha\n5\n2\nRenu\n5\n4\n",
                "Sorry! This seat is already booked.");

        runTest("Book after cancellation",
                "2\nAmrutha\n5\n3\n5\n2\nRenu\n5\n4\n",
                "Customer: Renu");

        runTest("Exit program", "4\n",
                "Thank you for using our system!");
    }

    static void runTest(String testName,
                        String input,
                        String expected) {
        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;

        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(
                    input.getBytes(StandardCharsets.UTF_8)));

            System.setOut(new PrintStream(output));

            CinemaSeatBookingSystemUpdated cinema =
                    new CinemaSeatBookingSystemUpdated();

            cinema.start();

        } catch (Exception e) {
            originalOut.println(testName + ": ERROR - "
                    + e.getMessage());
            return;
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        String actual = output.toString();

        if (actual.contains(expected)) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName);
            System.out.println("Expected: " + expected);
            System.out.println("Actual output:\n" + actual);
        }
    }
}
