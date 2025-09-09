// VehicleRental interface
interface VehicleRental {
    void rent();
    void returnVehicle();
}

// Car implementation
class Car implements VehicleRental {
    @Override
    public void rent() {
        System.out.println(" Car rented successfully.");
    }
    @Override
    public void returnVehicle() {
        System.out.println(" Car returned. Thank you!");
    }
}

// Bike implementation
class Bike implements VehicleRental {
    @Override
    public void rent() {
        System.out.println(" Bike rented successfully.");
    }
    @Override
    public void returnVehicle() {
        System.out.println(" Bike returned. Thank you!");
    }
}

// Bus implementation
class Bus implements VehicleRental {
    @Override
    public void rent() {
        System.out.println(" Bus rented successfully.");
    }
    @Override
    public void returnVehicle() {
        System.out.println(" Bus returned. Thank you!");
    }
}

public class MultiVehicleRentalSystem {
    public static void main(String[] args) {
        // Store all vehicles in one list
        VehicleRental[] vehicles = { new Car(), new Bike(), new Bus() };

        System.out.println(" Renting all vehicles:");
        for (VehicleRental vehicle : vehicles) {
            vehicle.rent();
        }

        System.out.println("\n Returning all vehicles:");
        for (VehicleRental vehicle : vehicles) {
            vehicle.returnVehicle();
        }
    }
}
