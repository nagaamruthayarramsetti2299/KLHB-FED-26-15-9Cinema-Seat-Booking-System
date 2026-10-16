
import java.io.*;

public class CinemaSeatBookingSystemTest4 {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {

        // Test 1: Book seat 2 with uppercase name
        runTest("Uppercase customer name",
                "2\nAMRUTHA\n2\n4\n",
                "Customer: AMRUTHA");

        // Test 2: Book seat with a name containing numbers
        runTest("Name containing numbers",
                "2\nAmrutha123\n4\n4\n",
                "Customer: Amrutha123");

        // Test 3: Book seat with a special character in name
        runTest("Name with special character",
                "2\nAmrutha-Renu\n4\n4\n",
                "Customer: Amrutha-Renu");

        // Test 4: Enter a negative seat number
        runTest("Negative seat number",
                "2\nRenu\n-5\n4\n",
                "Invalid seat number.");

        // Test 5: Enter a very large seat number
        runTest("Very large seat number",
                "3\n1000000\n4\n",
                "Invalid seat number.");

        // Test 6: Book seats 1, 5, and 10
        runTest("Book multiple seats",
                "2\nAsha\n1\n2\nRavi\n5\n2\nKiran\n10\n1\n4\n",
                "Seat 10 - BOOKED");

        // Test 7: Verify seat 2 is available
        runTest("Unbooked seat remains available",
                "2\nAsha\n1\n1\n4\n",
                "Seat 2 - AVAILABLE");

        // Test 8: Cancel booking and verify customer
        runTest("Verify cancelled customer",
                "2\nRenu\n8\n3\n8\n4\n",
                "Booking cancelled for Renu");

        // Test 9: Enter whitespace around seat number
        runTest("Seat number with spaces",
                "2\nAmrutha\n  6  \n4\n",
                "Seat number: 6");

        // Test 10: Enter whitespace around menu choice
        runTest("Menu choice with spaces",
                "  1  \n4\n",
                "Seat 10 - AVAILABLE");

        // Test 11: Invalid menu choice at boundary
        runTest("Menu choice 0",
                "0\n4\n",
                "Invalid choice. Try again.");

        // Test 12: Cancel seat using decimal input
        runTest("Decimal cancellation input",
                "3\n3.5\n4\n",
                "Invalid seat number.");

        System.out.println("\n===== TEST SUMMARY =====");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total: " + (passed + failed));
    }

    static void runTest(String name, String input,
                        String expected) {

        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;
        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(
                    input.getBytes("UTF-8")));
            System.setOut(new PrintStream(output));

            CinemaSeatBookingSystemUpdated cinema =
                    new CinemaSeatBookingSystemUpdated();

            cinema.start();

        } catch (Exception e) {
            System.setIn(oldIn);
            System.setOut(oldOut);
            failed++;
            System.out.println("FAIL: " + name);
            System.out.println("Error: " + e);
            return;
        }

        System.setIn(oldIn);
        System.setOut(oldOut);

        if (output.toString().contains(expected)) {
            passed++;
            System.out.println("PASS: " + name);
        } else {
            failed++;
            System.out.println("FAIL: " + name);
            System.out.println("Expected: " + expected);
        }
    }
}
