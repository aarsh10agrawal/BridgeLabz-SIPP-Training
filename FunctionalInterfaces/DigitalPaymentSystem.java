// PaymentMethod interface
interface PaymentMethod {
    void pay(double amount);
}

// UPI Payment implementation
class UPI implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println(" Paid ₹" + amount + " via UPI.");
    }
}

// Credit Card Payment implementation
class CreditCard implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println(" Paid ₹" + amount + " via Credit Card.");
    }
}

// Wallet Payment implementation
class Wallet implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println(" Paid ₹" + amount + " via Wallet.");
    }
}

public class DigitalPaymentSystem {
    public static void main(String[] args) {
        // Create payment methods
        PaymentMethod upi = new UPI();
        PaymentMethod card = new CreditCard();
        PaymentMethod wallet = new Wallet();

        // Perform payments
        upi.pay(500.0);
        card.pay(1200.5);
        wallet.pay(300.0);
    }
}
