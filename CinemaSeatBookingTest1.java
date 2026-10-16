
import java.io.*;

public class CinemaSeatBookingTest1 {

    public static void main(String[] args) throws Exception {

        String input = "2\nRenu\n6\n4\n";

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

        if (result.contains("Ticket price: Rs. 200")) {
            System.out.println("PASS: Ticket price is Rs. 200");
        } else {
            System.out.println("FAIL: Ticket price is incorrect");
        }
    }
}
