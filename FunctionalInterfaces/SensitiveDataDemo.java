// Marker interface for sensitive data
interface SensitiveData {
    // no methods, only a tag
}

// A class marked as sensitive
class CreditCardInfo implements SensitiveData {
    private String cardNumber;
    private String cvv;

    public CreditCardInfo(String cardNumber, String cvv) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "CreditCardInfo{cardNumber='" + cardNumber + "', cvv='" + cvv + "'}";
    }
}

// A class that is NOT sensitive
class PublicInfo {
    private String message;

    public PublicInfo(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PublicInfo{message='" + message + "'}";
    }
}

// Encryption utility
class EncryptionProcessor {
    public static void encrypt(Object obj) {
        if (obj instanceof SensitiveData) {
            System.out.println(" Encrypting sensitive data: " + obj);
            // dummy encryption logic
            System.out.println(" Encrypted successfully!");
        } else {
            System.out.println(" No encryption needed: " + obj);
        }
    }
}

// Demo
public class SensitiveDataDemo {
    public static void main(String[] args) {
        CreditCardInfo card = new CreditCardInfo("1234-5678-9876-5432", "123");
        PublicInfo info = new PublicInfo("Welcome to the portal!");

        System.out.println("🔹 Data Handling:");
        EncryptionProcessor.encrypt(card);  // Will be encrypted
        EncryptionProcessor.encrypt(info);  // Skipped
    }
}
