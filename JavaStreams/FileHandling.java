import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {
        // You can modify these paths or take them as input
        String sourceFilePath = "source.txt";  
        String destFilePath = "destination.txt"; 

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            // Check if source file exists
            File sourceFile = new File(sourceFilePath);
            if (!sourceFile.exists()) {
                System.out.println("Source file does not exist: " + sourceFilePath);
                return;
            }

            // Open input and output streams
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(destFilePath); // Creates if not exists, overwrites if exists

            int byteData;
            while ((byteData = fis.read()) != -1) {
                fos.write(byteData);
            }

            System.out.println("File copied successfully to: " + destFilePath);

        } catch (IOException e) {
            System.out.println("An error occurred while processing files: " + e.getMessage());
        } finally {
            try {
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                System.out.println("Error closing file streams: " + e.getMessage());
            }
        }
    }
}
