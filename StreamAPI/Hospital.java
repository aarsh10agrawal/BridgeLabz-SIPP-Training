import java.util.*;
import java.util.stream.*;

class Doctor {
    private String name;
    private String specialty;
    private boolean weekendAvailable;

    public Doctor(String name, String specialty, boolean weekendAvailable) {
        this.name = name;
        this.specialty = specialty;
        this.weekendAvailable = weekendAvailable;
    }

    public String getName() { return name; }
    public String getSpecialty() { return specialty; }
    public boolean isWeekendAvailable() { return weekendAvailable; }

    @Override
    public String toString() {
        return name + " - " + specialty + " (Weekend: " + (weekendAvailable ? "Yes" : "No") + ")";
    }
}

public class Hospital {
    public static void main(String[] args) {
        List<Doctor> doctors = Arrays.asList(
            new Doctor("Dr. Sharma", "Cardiologist", true),
            new Doctor("Dr. Gupta", "Neurologist", false),
            new Doctor("Dr. Mehta", "Orthopedic", true),
            new Doctor("Dr. Singh", "Dermatologist", true),
            new Doctor("Dr. Iyer", "Pediatrician", false),
            new Doctor("Dr. Khan", "Cardiologist", true)
        );

        // Find doctors available on weekends and sort by specialty
        List<Doctor> weekendDoctors = doctors.stream()
                .filter(Doctor::isWeekendAvailable)  // filter by weekend availability
                .sorted(Comparator.comparing(Doctor::getSpecialty)) // sort by specialty
                .collect(Collectors.toList());

        System.out.println("Doctors available on weekends (sorted by specialty):");
        weekendDoctors.forEach(System.out::println);
    }
}
