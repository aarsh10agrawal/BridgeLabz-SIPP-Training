import java.time.LocalDateTime;
import java.util.*;

public class TransactionLogger {
    public static void main(String[] args) {
        List<String> transactionIds = Arrays.asList(
            "TXN1001", "TXN1002", "TXN1003", "TXN1004"
        );

        // Log each transaction with a timestamp
        transactionIds.forEach(id -> 
            System.out.println(LocalDateTime.now() + " - Transaction: " + id)
        );
    }
}
