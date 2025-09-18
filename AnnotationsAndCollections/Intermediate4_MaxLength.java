// Intermediate 4: @MaxLength for field validation
import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class User {
    @MaxLength(12)
    private String username;

    public User(String username) {
        // validate with reflection using the annotation value
        try {
            Field f = User.class.getDeclaredField("username");
            MaxLength ml = f.getAnnotation(MaxLength.class);
            int max = (ml != null) ? ml.value() : Integer.MAX_VALUE;
            if (username != null && username.length() > max) {
                throw new IllegalArgumentException("username length > " + max);
            }
            this.username = username;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "User{username='" + username + "'}";
    }
}

public class Intermediate4_MaxLength {
    public static void main(String[] args) {
        User ok = new User("short_name");
        System.out.println(ok);
        try {
            // this should fail since length > 12
            User bad = new User("this_is_a_very_long_username");
            System.out.println(bad);
        } catch (IllegalArgumentException ex) {
            System.out.println("Caught: " + ex.getMessage());
        }
    }
}