
import java.io.*;

public class CinemaSeatBookingTest5 {

    public static void main(String[] args) throws Exception {

        String input = "2\nAkshitha\n8\n1\n4\n";

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(
                    input.getBytes("UTF-8")));

            System.setOut(new PrintStream(output));

            CinemaSeatBookingSystemUpdated cinema =
                    new CinemaSeatBookingSystemUpdated();

            cinema.start();

        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        String result = output.toString();

        if (result.contains("Seat 8 - BOOKED")) {
            System.out.println(
                    "PASS: Seat 8 is displayed as BOOKED");
        } else {
            System.out.println(
                    "FAIL: Seat 8 booking status is incorrect");
        }
    }
}
