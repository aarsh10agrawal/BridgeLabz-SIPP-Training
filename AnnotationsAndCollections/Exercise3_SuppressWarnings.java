// Exercise 3: Suppress Unchecked Warnings
import java.util.ArrayList;
import java.util.List;

public class Exercise3_SuppressWarnings {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // Using raw ArrayList (not recommended in real code)
        ArrayList rawList = new ArrayList();
        rawList.add("hello");
        rawList.add(123); // mixing types intentionally

        // Casting to a typed list (unsafe, but we are showing suppression)
        List<String> strings = rawList; // unchecked assignment
        System.out.println("Raw list contents: " + rawList);
        System.out.println("Strings view (unsafe): " + strings);
    }
}