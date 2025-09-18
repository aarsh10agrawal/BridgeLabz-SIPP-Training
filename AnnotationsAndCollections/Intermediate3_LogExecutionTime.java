// Intermediate 3: @LogExecutionTime to measure method time
import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {}

class Work {
    @LogExecutionTime
    public void slowTask() {
        // fake slow work
        long sum = 0;
        for (int i = 0; i < 5_000_00; i++) sum += i;
        System.out.println("slowTask sum=" + sum);
    }

    @LogExecutionTime
    public void fastTask() {
        int x = 0;
        for (int i = 0; i < 10_000; i++) x += i;
        System.out.println("fastTask x=" + x);
    }
}

public class Intermediate3_LogExecutionTime {
    public static void main(String[] args) throws Exception {
        Work w = new Work();
        // Simple "manual AOP" using reflection
        for (Method m : Work.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(LogExecutionTime.class)) {
                long start = System.nanoTime();
                m.invoke(w);
                long end = System.nanoTime();
                long ms = (end - start) / 1_000_000;
                System.out.println("Method " + m.getName() + " took ~" + ms + " ms");
            }
        }
    }
}