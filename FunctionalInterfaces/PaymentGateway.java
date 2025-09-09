// PaymentProcessor interface
interface PaymentProcessor {
    void pay(double amount);

    // New default refund() method
    default void refund(double amount) {
        System.out.println(" Refund not supported by this provider.");
    }
}

// Existing UPI provider (no refund support)
class UPIProcessor implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.println(" Paid ₹" + amount + " via UPI.");
    }
    // No refund() override → fallback to default
}

// CreditCard provider (supports refund)
class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.println(" Paid ₹" + amount + " via Credit Card.");
    }

    @Override
    public void refund(double amount) {
        System.out.println(" Refunded ₹" + amount + " back to Credit Card.");
    }
}

// Wallet provider (supports refund)
class WalletProcessor implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.println(" Paid ₹" + amount + " via Wallet.");
    }

    @Override
    public void refund(double amount) {
        System.out.println(" Refunded ₹" + amount + " back to Wallet balance.");
    }
}

public class PaymentGateway {
    public static void main(String[] args) {
        PaymentProcessor upi = new UPIProcessor();
        PaymentProcessor card = new CreditCardProcessor();
        PaymentProcessor wallet = new WalletProcessor();

        System.out.println(" Payments:");
        upi.pay(500);
        card.pay(1200);
        wallet.pay(300);

        System.out.println("\n Refunds:");
        upi.refund(200);    // default message
        card.refund(200);   // overridden logic
        wallet.refund(150); // overridden logic
    }
}
