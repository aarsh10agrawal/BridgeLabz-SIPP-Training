import java.util.*;
import java.util.stream.Collectors;

class Order {
    String customer;
    double total;

    Order(String customer, double total) {
        this.customer = customer;
        this.total = total;
    }

    public String getCustomer() {
        return customer;
    }

    public double getTotal() {
        return total;
    }
}

public class OrderRevenueSummary {
    public static void main(String[] args) {
        // Sample data
        List<Order> orders = Arrays.asList(
            new Order("Monika", 250.50),
            new Order("Love", 150.75),
            new Order("Kavita", 300.00),
            new Order("Chirag", 500.25),
            new Order("Babli", 200.25)
        );

        // Group orders by customer and sum totals
        Map<String, Double> revenueSummary = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomer,                // group by customer
                        Collectors.summingDouble(Order::getTotal) // sum totals
                ));

        // Print result
        revenueSummary.forEach((customer, total) ->
                System.out.println(customer + " -> $" + total));
    }
}
