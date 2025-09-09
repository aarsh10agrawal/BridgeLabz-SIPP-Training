import java.util.*;
import java.util.stream.*;

class Claim {
    private String type;
    private double amount;

    public Claim(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return type + " - $" + amount;
    }
}

public class InsuranceClaimAnalysis {
    public static void main(String[] args) {
        List<Claim> claims = Arrays.asList(
            new Claim("Health", 12000),
            new Claim("Health", 8000),
            new Claim("Auto", 15000),
            new Claim("Auto", 18000),
            new Claim("Home", 22000),
            new Claim("Home", 20000),
            new Claim("Health", 10000)
        );

        // Average claim amount for each type
        Map<String, Double> avgClaims = claims.stream()
                .collect(Collectors.groupingBy(
                        Claim::getType,
                        Collectors.averagingDouble(Claim::getAmount)
                ));

        System.out.println("Average Claim Amount by Type:");
        avgClaims.forEach((type, avg) ->
                System.out.println(type + " → $" + avg));
    }
}
