import java.util.*;

public class EmailNotifications {
    public static void main(String[] args) {
        List<String> emails = Arrays.asList(
            "user1@example.com",
            "user2@example.com",
            "user3@example.com",
            "user4@example.com"
        );

        // Sending notification emails
        emails.forEach(email -> sendEmailNotification(email));
    }

    // Simulated email sending method
    private static void sendEmailNotification(String email) {
        System.out.println("📧 Notification sent to: " + email);
    }
}
