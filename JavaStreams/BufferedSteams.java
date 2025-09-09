import java.io.*;

public class BufferedFileCopyComparison {
    public static void main(String[] args) {
        // Change these paths as per your system
        String sourceFile = "largefile.txt";      // ~100MB file
        String destFileUnbuffered = "copy_unbuffered.txt";
        String destFileBuffered = "copy_buffered.txt";

        long startTime, endTime;

        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destFileUnbuffered)) {

            startTime = System.nanoTime();

            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }

            endTime = System.nanoTime();
            System.out.println("Unbuffered copy time: " + (endTime - startTime) / 1_000_000 + " ms");

        } catch (IOException e) {
            System.out.println("Error in unbuffered copy: " + e.getMessage());
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFileBuffered))) {

            startTime = System.nanoTime();

            byte[] buffer = new byte[4096]; // 4 KB buffer
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            bos.flush(); // ensure all data is written
            endTime = System.nanoTime();
            System.out.println("Buffered copy time: " + (endTime - startTime) / 1_000_000 + " ms");

        } catch (IOException e) {
            System.out.println("Error in buffered copy: " + e.getMessage());
        }
    }
}
