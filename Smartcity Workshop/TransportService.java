public interface TransportService {
    void schedule(String route, String timing);
    void printServiceDetails();

    // Default method
    default void showThanks() {
        System.out.println("Thank you for using our service!");
    }
}
