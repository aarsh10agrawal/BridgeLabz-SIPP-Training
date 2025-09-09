import java.io.*;
import java.util.Arrays;

public class ImageByteArrayDemo {
    public static void main(String[] args) {
        String sourceImage = "source.jpg";       // Original image
        String destinationImage = "copy.jpg";    // New image

        try {
            // -------- Step 1: Read image into byte array --------
            FileInputStream fis = new FileInputStream(sourceImage);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            fis.close();

            byte[] imageBytes = baos.toByteArray();
            System.out.println("Image converted to byte array. Size: " + imageBytes.length + " bytes");

            // -------- Step 2: Write byte array back to new image --------
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
            FileOutputStream fos = new FileOutputStream(destinationImage);

            while ((bytesRead = bais.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            fos.close();
            bais.close();
            baos.close();

            System.out.println("Image successfully written to " + destinationImage);

            // -------- Step 3: Verify both files are identical --------
            if (filesAreEqual(new File(sourceImage), new File(destinationImage))) {
                System.out.println("✅ Verification successful: Files are identical!");
            } else {
                System.out.println("❌ Verification failed: Files differ.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Utility method to compare two files
    private static boolean filesAreEqual(File file1, File file2) throws IOException {
        if (file1.length() != file2.length()) return false;

        try (FileInputStream fis1 = new FileInputStream(file1);
             FileInputStream fis2 = new FileInputStream(file2)) {

            byte[] buf1 = new byte[4096];
            byte[] buf2 = new byte[4096];

            int bytesRead1, bytesRead2;
            while ((bytesRead1 = fis1.read(buf1)) != -1 &&
                   (bytesRead2 = fis2.read(buf2)) != -1) {
                if (bytesRead1 != bytesRead2 ||
                    !Arrays.equals(Arrays.copyOf(buf1, bytesRead1),
                                   Arrays.copyOf(buf2, bytesRead2))) {
                    return false;
                }
            }
        }
        return true;
    }
}
