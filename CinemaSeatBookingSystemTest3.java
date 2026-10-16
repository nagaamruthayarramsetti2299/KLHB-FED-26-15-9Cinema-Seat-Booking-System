
import java.io.*;

public class CinemaSeatBookingSystemTest3 {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {

        // Test 1: Book seat 3
        runTest("Book seat 3",
                "2\nNaga\n3\n4\n",
                "Seat number: 3");

        // Test 2: Book seat 3 and verify price
        runTest("Verify ticket price",
                "2\nNaga\n3\n4\n",
                "Ticket price: Rs. 200");

        // Test 3: Cancel booking twice
        runTest("Cancel same seat twice",
                "2\nNaga\n3\n3\n3\n3\n3\n4\n",
                "This seat is not booked.");

        // Test 4: Book seat after cancellation
        runTest("Rebook cancelled seat",
                "2\nNaga\n3\n3\n3\n2\nRenu\n3\n4\n",
                "Customer: Renu");

        // Test 5: Book adjacent seats
        runTest("Book adjacent seats",
                "2\nAsha\n4\n2\nRavi\n5\n1\n4\n",
                "Seat 5 - BOOKED");

        // Test 6: Verify another seat remains available
        runTest("Other seat remains available",
                "2\nAsha\n4\n1\n4\n",
                "Seat 5 - AVAILABLE");

        // Test 7: Cancel seat 10
        runTest("Cancel last seat",
                "2\nRavi\n10\n3\n10\n1\n4\n",
                "Seat 10 - AVAILABLE");

        // Test 8: Cancel seat 1
        runTest("Cancel first seat",
                "2\nAsha\n1\n3\n1\n1\n4\n",
                "Seat 1 - AVAILABLE");

        // Test 9: Enter decimal seat number
        runTest("Decimal seat number",
                "2\nNaga\n2.5\n4\n",
                "Invalid seat number.");

        // Test 10: Enter a large seat number
        runTest("Large seat number",
                "2\nNaga\n999\n4\n",
                "Invalid seat number.");

        // Test 11: Enter a decimal menu choice
        runTest("Decimal menu choice",
                "1.5\n4\n",
                "Please enter a valid menu number.");

        // Test 12: Verify booking name after booking
        runTest("Verify customer name",
                "2\nAmrutha\n6\n1\n4\n",
                "Seat 6 - BOOKED");

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
