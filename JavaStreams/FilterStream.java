import java.io.*;

public class UpperToLowerFileConverter {
    public static void main(String[] args) {
        String sourceFile = "input.txt";        // Source text file
        String destFile = "output.txt";         // Destination text file

        // Using try-with-resources for automatic closing
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), "UTF-8"));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFile), "UTF-8"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line.toLowerCase());  // convert to lowercase
                writer.newLine(); // preserve line breaks
            }

            System.out.println("File converted successfully. Output written to " + destFile);

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}
