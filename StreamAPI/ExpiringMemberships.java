import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.*;

class Member {
    private String name;
    private LocalDate expiryDate;

    public Member(String name, LocalDate expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
    }

    public String getName() { return name; }
    public LocalDate getExpiryDate() { return expiryDate; }

    @Override
    public String toString() {
        return name + " (Expires: " + expiryDate + ")";
    }
}

public class ExpiringMemberships {
    public static void main(String[] args) {
        List<Member> members = Arrays.asList(
            new Member("Alice", LocalDate.now().plusDays(10)),
            new Member("Bob", LocalDate.now().plusDays(40)),
            new Member("Charlie", LocalDate.now().plusDays(25)),
            new Member("David", LocalDate.now().plusDays(5)),
            new Member("Emma", LocalDate.now().plusDays(60))
        );

        LocalDate today = LocalDate.now();

        // Filter members expiring within 30 days
        List<Member> expiringSoon = members.stream()
                .filter(m -> {
                    long daysLeft = ChronoUnit.DAYS.between(today, m.getExpiryDate());
                    return daysLeft >= 0 && daysLeft <= 30;
                })
                .collect(Collectors.toList());

        System.out.println("Members expiring within 30 days:");
        expiringSoon.forEach(System.out::println);
    }
}
