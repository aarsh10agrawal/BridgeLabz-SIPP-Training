import java.util.*;
import java.util.stream.*;

public class StockPriceLogger {
    public static void main(String[] args) {
        List<Double> stockPrices = Arrays.asList(
            101.5, 102.3, 99.8, 105.6, 110.2
        );

        System.out.println("Live Stock Price Updates:");
        stockPrices.stream()
                .forEach(price -> System.out.println("Stock Price: $" + price));
    }
}
