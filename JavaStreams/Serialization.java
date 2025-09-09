import java.io.*;
import java.util.*;

// Employee class must implement Serializable
class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String department;
    private double salary;

    // Constructor
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // To display employee details
    @Override
    public String toString() {
        return "Employee [ID=" + id + ", Name=" + name +
               ", Department=" + department + ", Salary=" + salary + "]";
    }
}

public class EmployeeSerializationDemo {
    private static final String FILE_NAME = "employees.ser";

    // Serialize employees list
    public static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
            System.out.println("Employees saved successfully to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }

    // Deserialize employees list
    @SuppressWarnings("unchecked")
    public static List<Employee> loadEmployees() {
        List<Employee> employees = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employees = (List<Employee>) ois.readObject();
            System.out.println("Employees loaded successfully from " + FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employees: " + e.getMessage());
        }
        return employees;
    }

    public static void main(String[] args) {
        // Sample employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", "HR", 50000));
        employees.add(new Employee(2, "Bob", "IT", 70000));
        employees.add(new Employee(3, "Charlie", "Finance", 60000));

        // Serialize
        saveEmployees(employees);

        // Deserialize and display
        List<Employee> loadedEmployees = loadEmployees();
        if (loadedEmployees != null) {
            System.out.println("\nRetrieved Employees:");
            for (Employee emp : loadedEmployees) {
                System.out.println(emp);
            }
        }
    }
}
