import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

interface DateFormatUtil {
    // Static method to format date into given pattern
    static String formatDate(LocalDate date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }
}

public class InvoiceGenerator {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        System.out.println(" Invoice Date Formats:");
        System.out.println("dd-MM-yyyy : " + DateFormatUtil.formatDate(today, "dd-MM-yyyy"));
        System.out.println("MM/dd/yyyy : " + DateFormatUtil.formatDate(today, "MM/dd/yyyy"));
        System.out.println("yyyy-MM-dd : " + DateFormatUtil.formatDate(today, "yyyy-MM-dd"));
        System.out.println("EEEE, MMMM dd yyyy : " + DateFormatUtil.formatDate(today, "EEEE, MMMM dd yyyy"));
    }
}
