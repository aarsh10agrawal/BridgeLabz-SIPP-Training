import java.util.*;

public class BusService implements TransportService {
    private List<String> schedules = new ArrayList<>();
    private String route;

    @Override
    public void schedule(String route, String timing) {
        this.route = route;
        schedules.add(timing);
    }

    @Override
    public void printServiceDetails() {
        System.out.println("BusService for route: " + route);
        schedules.forEach(time -> System.out.println("Bus Timing: " + time));
    }
}
