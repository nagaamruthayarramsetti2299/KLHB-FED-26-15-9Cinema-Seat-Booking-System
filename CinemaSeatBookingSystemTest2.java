

import java.io.*;

public class CinemaSeatBookingSystemTest2 {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {

        // Test 1: Name containing spaces around it
        runTest("Name with surrounding spaces",
                "2\n  Amrutha  \n2\n4\n",
                "Customer: Amrutha");

        // Test 2: Name containing only spaces
        runTest("Name containing only spaces",
                "2\n     \n4\n",
                "Name cannot be empty.");

        // Test 3: Check ticket price
        runTest("Ticket price verification",
                "2\nRenu\n6\n4\n",
                "Ticket price: Rs. 200");

        // Test 4: Display booked seat
        runTest("Display booked seat",
                "2\nAkshitha\n8\n1\n4\n",
                "Seat 8 - BOOKED");

        // Test 5: Book two different seats
        runTest("Book two different seats",
                "2\nAmrutha\n2\n2\nRenu\n9\n1\n4\n",
                "Seat 9 - BOOKED");

        // Test 6: Verify cancellation customer name
        runTest("Cancellation shows customer name",
                "2\nAmrutha\n4\n3\n4\n4\n",
                "Booking cancelled for Amrutha");

        // Test 7: Cancel with non-numeric input
        runTest("Invalid cancellation input",
                "3\nhello\n4\n",
                "Invalid seat number.");

        // Test 8: Negative menu choice
        runTest("Negative menu choice",
                "-1\n4\n",
                "Invalid choice. Try again.");

        // Test 9: Check seat availability after cancellation
        runTest("Seat available after cancellation",
                "2\nRenu\n7\n3\n7\n1\n4\n",
                "Seat 7 - AVAILABLE");

        // Test 10: Verify all seats initially available
        runTest("All seats initially available",
                "1\n4\n",
                "Seat 10 - AVAILABLE");

        System.out.println("\n======= FINAL RESULTS =======");
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

            CinemaSeatBookingSystemUpdated system =
                    new CinemaSeatBookingSystemUpdated();

            system.start();

        } catch (Exception e) {
            failed++;
            System.setIn(oldIn);
            System.setOut(oldOut);
            System.out.println("FAIL: " + name);
            System.out.println(e);
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
