
import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    String grade;

    Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }
}

public class StudentGrouping {
    public static void main(String[] args) {
        // Sample data
        List<Student> students = Arrays.asList(
            new Student("Abhi", "A"),
            new Student("Monika", "B"),
            new Student("Saloni", "A"),
            new Student("Mohini", "C"),
            new Student("Ayush", "B")
        );

        // Group students by grade and collect names
        Map<String, List<String>> groupedByGrade = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGrade,                     // Grouping key
                        Collectors.mapping(Student::getName,   // Extract only names
                                Collectors.toList())          // Collect into List
                ));

        // Print result
        groupedByGrade.forEach((grade, names) ->
                System.out.println("Grade " + grade + " -> " + names));
    }
}
