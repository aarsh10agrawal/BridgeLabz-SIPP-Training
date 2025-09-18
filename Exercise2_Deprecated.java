// Exercise 2: Use @Deprecated to Mark an Old Method
class LegacyAPI {
    @Deprecated
    public void oldFeature() {
        // imagine this does something old-school
        System.out.println("oldFeature() is deprecated. Please use newFeature().");
    }

    public void newFeature() {
        System.out.println("newFeature() is the recommended method now.");
    }
}

public class Exercise2_Deprecated {
    public static void main(String[] args) {
        LegacyAPI api = new LegacyAPI();
        api.oldFeature(); // your IDE/javac should show a warning
        api.newFeature();
    }
}