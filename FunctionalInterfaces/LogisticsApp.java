interface UnitConverter {
    // Convert kilometers to miles
    static double kmToMiles(double km) {
        return km * 0.621371;
    }

    // Convert miles to kilometers
    static double milesToKm(double miles) {
        return miles / 0.621371;
    }

    // Convert kilograms to pounds
    static double kgToLbs(double kg) {
        return kg * 2.20462;
    }

    // Convert pounds to kilograms
    static double lbsToKg(double lbs) {
        return lbs / 2.20462;
    }
}

public class LogisticsApp {
    public static void main(String[] args) {
        double km = 10.0;
        double kg = 5.0;

        System.out.println(" Unit Conversions:");
        System.out.println(km + " km = " + UnitConverter.kmToMiles(km) + " miles");
        System.out.println("6.2 miles = " + UnitConverter.milesToKm(6.2) + " km");
        System.out.println(kg + " kg = " + UnitConverter.kgToLbs(kg) + " lbs");
        System.out.println("11 lbs = " + UnitConverter.lbsToKg(11) + " kg");
    }
}
