public class BackgroundJobExecution {
    public static void main(String[] args) {
        // Define background jobs using Runnable (Lambda)
        Runnable dataBackupJob = () -> {
            System.out.println(" Starting Data Backup...");
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(" Data Backup Completed!");
        };

        Runnable reportGenerationJob = () -> {
            System.out.println(" Generating Report...");
            try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(" Report Generated!");
        };

        Runnable notificationJob = () -> {
            System.out.println(" Sending Notifications...");
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(" Notifications Sent!");
        };

        // Execute tasks asynchronously in separate threads
        new Thread(dataBackupJob).start();
        new Thread(reportGenerationJob).start();
        new Thread(notificationJob).start();

        System.out.println(" Main thread continues working while jobs run in background...");
    }
}
