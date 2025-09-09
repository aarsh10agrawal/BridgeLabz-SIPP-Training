import java.io.*;

public class StudentDataStreamDemo {
    private static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {
        // ---------- Step 1: Write student details ----------
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_NAME))) {
            // Example student records
            dos.writeInt(101);               // Roll number
            dos.writeUTF("Alice");           // Name
            dos.writeDouble(3.8);            // GPA

            dos.writeInt(102);
            dos.writeUTF("Bob");
            dos.writeDouble(3.5);

            dos.writeInt(103);
            dos.writeUTF("Charlie");
            dos.writeDouble(3.9);

            System.out.println("✅ Student data written to " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("Error writing student data: " + e.getMessage());
        }

        // ---------- Step 2: Read student details ----------
        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("\nRetrieved Student Records:");
            
            while (dis.available() > 0) {  // read until EOF
                int roll = dis.readInt();
                String name = dis.readUTF();
                double gpa = dis.readDouble();

                System.out.println("Roll: " + roll + ", Name: " + name + ", GPA: " + gpa);
            }

        } catch (IOException e) {
            System.out.println("Error reading student data: " + e.getMessage());
        }
    }
}
