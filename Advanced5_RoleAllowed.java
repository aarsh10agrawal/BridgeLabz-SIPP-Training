// Advanced 5: Role-Based Access Control with @RoleAllowed
import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface RoleAllowed {
    String value();
}

class SecurityContext {
    // super simple context
    private static String currentRole = "USER";

    public static void setRole(String role) { currentRole = role; }
    public static String getRole() { return currentRole; }
}

// We'll annotate methods; class-level also supported
class AdminPanel {
    @RoleAllowed("ADMIN")
    public void deleteAllUsers() {
        System.out.println("All users deleted (pretend).");
    }

    @RoleAllowed("USER")
    public void viewDashboard() {
        System.out.println("Showing dashboard.");
    }
}

public class Advanced5_RoleAllowed {
    // utility that checks @RoleAllowed before invoking
    static void invokeWithRoleCheck(Object obj, String methodName) throws Exception {
        Method m = obj.getClass().getDeclaredMethod(methodName);
        if (m.isAnnotationPresent(RoleAllowed.class)) {
            RoleAllowed ra = m.getAnnotation(RoleAllowed.class);
            if (!SecurityContext.getRole().equals(ra.value())) {
                System.out.println("Access Denied!");
                return;
            }
        }
        m.invoke(obj);
    }

    public static void main(String[] args) throws Exception {
        AdminPanel p = new AdminPanel();

        // simulate a normal USER
        SecurityContext.setRole("USER");
        invokeWithRoleCheck(p, "viewDashboard");   // should work
        invokeWithRoleCheck(p, "deleteAllUsers");  // should deny

        // now simulate ADMIN
        SecurityContext.setRole("ADMIN");
        invokeWithRoleCheck(p, "deleteAllUsers");  // should work
    }
}