// Beginner 1: @ImportantMethod with optional level
import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
    String level() default "HIGH";
}

class DemoImportant {
    @ImportantMethod
    public void init() { System.out.println("init called"); }

    @ImportantMethod(level = "MEDIUM")
    public void refresh() { System.out.println("refresh called"); }

    public void helper() { System.out.println("helper called"); }
}

public class Practice1_ImportantMethod {
    public static void main(String[] args) throws Exception {
        DemoImportant di = new DemoImportant();
        di.init();
        di.refresh();
        di.helper();

        // Reflection: find annotated methods
        System.out.println("Important methods:");
        for (Method m : DemoImportant.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod im = m.getAnnotation(ImportantMethod.class);
                System.out.println(m.getName() + " (level=" + im.level() + ")");
            }
        }
    }
}