import java.util.*;
import java.util.stream.*;

public class TransformNames {
    public static void main(String[] args) {
        List<String> customers = Arrays.asList(
            "monika", "arjun", "love", "kavita", "zara", "abhi"
        );

        // Transform names to uppercase and sort alphabetically
        List<String> transformed = customers.stream()
                .map(String::toUpperCase)    // convert to uppercase
                .sorted()                    // sort alphabetically
                .collect(Collectors.toList());

        System.out.println("Customer Names (Uppercase & Sorted):");
        transformed.forEach(System.out::println);
    }
}
