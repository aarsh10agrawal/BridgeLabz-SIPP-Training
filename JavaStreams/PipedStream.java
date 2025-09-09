import java.io.*;

class WriterThread extends Thread {
    private PipedOutputStream pos;

    public WriterThread(PipedOutputStream pos) {
        this.pos = pos;
    }

    @Override
    public void run() {
        try {
            String[] messages = {
                "Hello from Writer!",
                "Piped Streams are cool.",
                "Inter-thread communication in Java.",
                "End"
            };

            for (String msg : messages) {
                pos.write(msg.getBytes());
                pos.write('\n');  // delimiter
                pos.flush();
                System.out.println("[Writer] Sent: " + msg);
                Thread.sleep(500); // simulate delay
            }
            pos.close(); // signal end of stream
        } catch (IOException | InterruptedException e) {
            System.out.println("Writer Error: " + e.getMessage());
        }
    }
}

class ReaderThread extends Thread {
    private PipedInputStream pis;

    public ReaderThread(PipedInputStream pis) {
        this.pis = pis;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(pis))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[Reader] Received: " + line);
            }
        } catch (IOException e) {
            System.out.println("Reader Error: " + e.getMessage());
        }
    }
}

public class PipedStreamDemo {
    public static void main(String[] args) {
        try {
            // Connect piped streams
            PipedOutputStream pos = new PipedOutputStream();
            PipedInputStream pis = new PipedInputStream(pos);

            // Create writer and reader threads
            Thread writer = new WriterThread(pos);
            Thread reader = new ReaderThread(pis);

            // Start threads
            writer.start();
            reader.start();

            // Wait for completion
            writer.join();
            reader.join();

            System.out.println("✅ Communication finished.");

        } catch (IOException | InterruptedException e) {
            System.out.println("Main Error: " + e.getMessage());
        }
    }
}
