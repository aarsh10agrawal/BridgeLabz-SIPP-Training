import java.util.function.Function;

public class StringLengthChecker {
    public static void main(String[] args) {
        int limit = 20; // character limit

        // Function to calculate string length
        Function<String, Integer> lengthCalculator = String::length;

        // Sample messages
        String[] messages = {
            "Hello HR team",
            "This is an urgent notification regarding project updates",
            "OK"
        };

        System.out.println(" Message Length Check:");
        for (String msg : messages) {
            int length = lengthCalculator.apply(msg);
            if (length > limit) {
                System.out.println(" Message exceeds limit (" + length + " chars): " + msg);
            } else {
                System.out.println(" Message within limit (" + length + " chars): " + msg);
            }
        }
    }
}
