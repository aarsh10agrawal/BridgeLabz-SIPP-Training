// Exercise 5: Create and Use a Repeatable Annotation
import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class)
@interface BugReport {
    String description();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
    BugReport[] value();
}

class PaymentService {
    @BugReport(description = "Null pointer when card is null")
    @BugReport(description = "Timeout under heavy load")
    public void processPayment() {
        System.out.println("Processing payment...");
    }
}

public class Exercise5_Repeatable_BugReport {
    public static void main(String[] args) throws Exception {
        Method m = PaymentService.class.getDeclaredMethod("processPayment");
        if (m.isAnnotationPresent(BugReport.class) || m.isAnnotationPresent(BugReports.class)) {
            BugReport[] reports = m.getAnnotationsByType(BugReport.class);
            System.out.println("Bug reports for method: " + m.getName());
            for (BugReport br : reports) {
                System.out.println("- " + br.description());
            }
        }
        new PaymentService().processPayment();
    }
}