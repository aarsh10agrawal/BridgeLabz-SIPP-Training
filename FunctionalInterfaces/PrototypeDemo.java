// Prototype Model
class PatientRecord implements Cloneable {
    private String name;
    private int age;
    private String disease;

    public PatientRecord(String name, int age, String disease) {
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    // Override clone method
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // shallow copy
    }

    @Override
    public String toString() {
        return "PatientRecord{name='" + name + "', age=" + age + ", disease='" + disease + "'}";
    }
}

// Testing Prototype Cloning
public class PrototypeDemo {
    public static void main(String[] args) {
        try {
            // Predefined object (prototype)
            PatientRecord original = new PatientRecord("John Doe", 45, "Diabetes");

            // Cloning prototype
            PatientRecord copy1 = (PatientRecord) original.clone();
            PatientRecord copy2 = (PatientRecord) original.clone();

            System.out.println(" Original Record: " + original);
            System.out.println(" Cloned Record 1: " + copy1);
            System.out.println(" Cloned Record 2: " + copy2);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
