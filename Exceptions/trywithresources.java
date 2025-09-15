import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFirstLine {
    public static void main(String[] args) {
        // Ensure you have a file named "info.txt" in the same directory
        // or provide a full path.
        // Example content for info.txt:
        // This is the first line.
        // This is the second line.
        try (BufferedReader reader = new BufferedReader(new FileReader("info.txt"))) {
            String firstLine = reader.readLine();
            if (firstLine != null) {
                System.out.println("First line of the file: " + firstLine);
            } else {
                System.out.println("File is empty.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            // e.printStackTrace(); // Uncomment for more detailed error
        }
    }
}