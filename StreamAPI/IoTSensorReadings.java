import java.util.*;
import java.util.stream.*;

public class IoTSensorReadings {
    public static void main(String[] args) {
        List<Integer> readings = Arrays.asList(25, 40, 55, 70, 30, 85, 90);
        int threshold = 50;

        System.out.println("Sensor readings above threshold (" + threshold + "):");
        readings.stream()
                .filter(value -> value > threshold)   // keep only values > threshold
                .forEach(value -> System.out.println("Reading: " + value));
    }
}
