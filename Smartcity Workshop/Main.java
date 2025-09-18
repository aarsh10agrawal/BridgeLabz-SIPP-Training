import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<TransportService> services = Arrays.asList(
                new BusService(),
                new AmbulanceService()
        );

        services.get(0).schedule("Central Park", "08:30 AM");
        services.get(0).schedule("Central Park", "09:30 AM");
        services.get(1).schedule("High Street", "ASAP");

        // Lambda for simple fare calculation
        FareCalculator calculator = (distance, baseFare) -> baseFare + distance * 2;

        // Use static method
        double dist = GeoUtils.calculateDistance(12.0, 77.0, 12.9, 78.1);
        System.out.printf("Distance Example: %.2f km%n", dist);

        System.out.println("--- Service Details ---");
        services.forEach(TransportService::printServiceDetails);

        // Detect and prioritize emergencies
        services.stream()
                .filter(s -> s instanceof EmergencyService)
                .forEach(s -> System.out.println("Priority given to emergency: " + s.getClass().getSimpleName()));

        // Grouping example with Stream and Collectors
        List<String> passengers = Arrays.asList("Anna:Bus", "John:Ambulance", "Elena:Bus");
        Map<String, Long> grouped = passengers.stream()
                .collect(Collectors.groupingBy(
                        p -> p.split(":")[1],
                        Collectors.counting()
                ));
        System.out.println("Grouped by service: " + grouped);

        // Fare reporting
        List<Double> fares = Arrays.asList(
                calculator.calculateFare(5, 10),
                calculator.calculateFare(12, 10)
        );
        DoubleSummaryStatistics fareStats = fares.stream().collect(Collectors.summarizingDouble(Double::doubleValue));
        System.out.println("Fare summary: " + fareStats);

        System.out.println("--- Thank You Message ---");
        services.forEach(TransportService::showThanks);
    }
}
