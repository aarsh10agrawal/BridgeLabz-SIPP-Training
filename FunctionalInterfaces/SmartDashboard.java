// Vehicle interface
interface Vehicle {
    void displaySpeed();

    // New default method for electric vehicles
    default void displayBattery() {
        System.out.println(" Battery information not available for this vehicle.");
    }
}

// Car (non-electric, doesn’t override displayBattery)
class Car implements Vehicle {
    private int speed;

    public Car(int speed) {
        this.speed = speed;
    }

    @Override
    public void displaySpeed() {
        System.out.println(" Car speed: " + speed + " km/h");
    }
}

// Bike (non-electric)
class Bike implements Vehicle {
    private int speed;

    public Bike(int speed) {
        this.speed = speed;
    }

    @Override
    public void displaySpeed() {
        System.out.println(" Bike speed: " + speed + " km/h");
    }
}

// ElectricCar (overrides battery display)
class ElectricCar implements Vehicle {
    private int speed;
    private int battery;

    public ElectricCar(int speed, int battery) {
        this.speed = speed;
        this.battery = battery;
    }

    @Override
    public void displaySpeed() {
        System.out.println(" Electric Car speed: " + speed + " km/h");
    }

    @Override
    public void displayBattery() {
        System.out.println(" Battery: " + battery + "%");
    }
}

public class SmartDashboard {
    public static void main(String[] args) {
        Vehicle car = new Car(80);
        Vehicle bike = new Bike(60);
        Vehicle eCar = new ElectricCar(100, 85);

        System.out.println(" Dashboard Readings:");
        car.displaySpeed();
        car.displayBattery(); // uses default

        bike.displaySpeed();
        bike.displayBattery(); // uses default

        eCar.displaySpeed();
        eCar.displayBattery(); // overridden
    }
}
