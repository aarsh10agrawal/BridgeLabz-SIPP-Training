import java.util.*;

public class EventWelcome {
    public static void main(String[] args) {
        List<String> attendees = Arrays.asList(
            "Monika", "Abhi", "Deepak", "Kavita", "Zara"
        );

        System.out.println("Welcome Messages:");
        attendees.forEach(name -> 
            System.out.println("Welcome to the event, " + name + "!")
        );
    }
}
