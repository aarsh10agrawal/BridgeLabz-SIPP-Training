public class AmbulanceService implements TransportService, EmergencyService {
    private String route, timing;

    @Override
    public void schedule(String route, String timing) {
        this.route = route;
        this.timing = timing;
    }

    @Override
    public void printServiceDetails() {
        System.out.println("Ambulance scheduled on route: " + route + " at " + timing + " (Emergency)");
    }
}
