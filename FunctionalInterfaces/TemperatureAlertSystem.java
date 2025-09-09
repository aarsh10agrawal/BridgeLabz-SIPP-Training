import java.util.function.Predicate;

public class TemperatureAlertSystem {
    public static void main(String[] args) {
        double threshold = 38.0; // Example threshold in °C

        // Predicate to check if temperature crosses threshold
        Predicate<Double> isHighTemperature = temp -> temp > threshold;

        // Test temperatures
        double[] readings = {36.5, 37.8, 38.2, 39.0, 35.9};

        System.out.println(" Temperature Alerts:");
        for (double temp : readings) {
            if (isHighTemperature.test(temp)) {
                System.out.println(" Alert! Temperature " + temp + "°C exceeds threshold.");
            } else {
                System.out.println(" Temperature " + temp + "°C is normal.");
            }
        }
    }
}
