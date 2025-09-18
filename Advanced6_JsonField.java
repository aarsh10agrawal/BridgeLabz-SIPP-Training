// Advanced 6: Custom Serialization Annotation @JsonField
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name();
}

class Person {
    @JsonField(name = "user_name")
    private String username;

    @JsonField(name = "years")
    private int age;

    private String ignored; // not annotated -> not serialized

    public Person(String username, int age, String ignored) {
        this.username = username;
        this.age = age;
        this.ignored = ignored;
    }
}

class JsonUtil {
    public static String toJson(Object obj) {
        try {
            Map<String, String> map = new LinkedHashMap<>();
            for (Field f : obj.getClass().getDeclaredFields()) {
                if (f.isAnnotationPresent(JsonField.class)) {
                    f.setAccessible(true);
                    JsonField jf = f.getAnnotation(JsonField.class);
                    Object val = f.get(obj);
                    // simple string escape
                    String strVal = (val == null) ? "null" : val.toString().replace("\"", "\\\"");
                    // numbers vs strings
                    if (val instanceof Number || val instanceof Boolean) {
                        map.put(jf.name(), String.valueOf(val));
                    } else if (val == null) {
                        map.put(jf.name(), "null");
                    } else {
                        map.put(jf.name(), "\"" + strVal + "\"");
                    }
                }
            }
            // build json
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            boolean first = true;
            for (Map.Entry<String, String> e : map.entrySet()) {
                if (!first) sb.append(", ");
                sb.append("\"").append(e.getKey()).append("\": ").append(e.getValue());
                first = false;
            }
            sb.append("}");
            return sb.toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Advanced6_JsonField {
    public static void main(String[] args) {
        Person p = new Person("ananya", 21, "skip-me");
        String json = JsonUtil.toJson(p);
        System.out.println(json);
    }
}