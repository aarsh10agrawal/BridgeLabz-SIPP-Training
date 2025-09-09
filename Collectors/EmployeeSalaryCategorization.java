import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name;
    String department;
    double salary;

    Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + " (" + department + ", $" + salary + ")";
    }
}

public class EmployeeSalaryCategorization {
    public static void main(String[] args) {
        // Sample data
        List<Employee> employees = Arrays.asList(
            new Employee("Manvi", "IT", 60000),
            new Employee("Love", "IT", 75000),
            new Employee("Monika", "HR", 50000),
            new Employee("Dhruv", "HR", 55000),
            new Employee("Mohan", "Finance", 80000)
        );

        // Group employees by department and calculate average salary
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));

        // Print result
        avgSalaryByDept.forEach((dept, avgSalary) ->
                System.out.println(dept + " -> $" + avgSalary));
    }
}
