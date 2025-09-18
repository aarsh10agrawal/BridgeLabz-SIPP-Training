// Exercise 4: Create a Custom Annotation and Use It
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TaskInfo {
    String priority();
    String assignedTo();
}

class TaskManager {
    @TaskInfo(priority = "HIGH", assignedTo = "Aditi")
    public void generateReport() {
        System.out.println("Generating report...");
    }

    @TaskInfo(priority = "LOW", assignedTo = "Rahul")
    public void cleanTempData() {
        System.out.println("Cleaning temp data...");
    }
}

public class Exercise4_CustomAnnotation_TaskInfo {
    public static void main(String[] args) throws Exception {
        TaskManager tm = new TaskManager();
        tm.generateReport();
        tm.cleanTempData();

        // Reflection: read annotation details
        for (Method m : TaskManager.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(TaskInfo.class)) {
                TaskInfo info = m.getAnnotation(TaskInfo.class);
                System.out.println("Method: " + m.getName()
                        + " | priority=" + info.priority()
                        + " | assignedTo=" + info.assignedTo());
            }
        }
    }
}