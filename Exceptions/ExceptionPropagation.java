public class ExceptionPropagation {

    public static void method1() {
        System.out.println("Inside method1");
        int result = 10 / 0; // This will throw an ArithmeticException
        System.out.println("Result in method1: " + result); // This line will not be reached
    }

    public static void method2() {
        System.out.println("Inside method2");
        method1(); // Calls method1, which throws an exception
        System.out.println("Exiting method2"); // This line will not be reached if method1 throws
    }

    public static void main(String[] args) {
        System.out.println("Inside main");
        try {
            method2(); // Calls method2, which in turn calls method1
        } catch (ArithmeticException e) {
            System.out.println("Handled exception in main: " + e.getMessage());
        }
        System.out.println("Program finished.");
    }
}