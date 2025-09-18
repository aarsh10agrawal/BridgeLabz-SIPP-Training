// Beginner 2: @Todo Annotation for pending tasks
import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

class FeatureBoard {
    @Todo(task = "Implement login via OTP", assignedTo = "Neha", priority = "HIGH")
    public void loginFeature() { System.out.println("loginFeature..."); }

    @Todo(task = "Add dark mode", assignedTo = "Arjun")
    public void themeFeature() { System.out.println("themeFeature..."); }

    public void finishedFeature() { System.out.println("finishedFeature..."); }
}

public class Practice2_TodoAnnotation {
    public static void main(String[] args) throws Exception {
        FeatureBoard fb = new FeatureBoard();
        fb.loginFeature();
        fb.themeFeature();
        fb.finishedFeature();

        System.out.println("Pending tasks:");
        for (Method m : FeatureBoard.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Todo.class)) {
                Todo t = m.getAnnotation(Todo.class);
                System.out.println("- " + t.task() + " | assignedTo=" + t.assignedTo() + " | priority=" + t.priority());
            }
        }
    }
}