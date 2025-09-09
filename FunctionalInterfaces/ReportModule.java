// Exportable interface
interface Exportable {
    void export(String data);

    // New default JSON export method
    default void exportToJSON(String data) {
        System.out.println(" JSON export not supported by this exporter.");
    }
}

// Existing CSV Exporter
class CSVExporter implements Exportable {
    @Override
    public void export(String data) {
        System.out.println(" Exporting data to CSV: " + data);
    }
    // No exportToJSON() → uses default
}

// Existing PDF Exporter
class PDFExporter implements Exportable {
    @Override
    public void export(String data) {
        System.out.println(" Exporting data to PDF: " + data);
    }
    // No exportToJSON() → uses default
}

// New JSON Exporter
class JSONExporter implements Exportable {
    @Override
    public void export(String data) {
        System.out.println(" Exporting data (default method not used).");
    }

    @Override
    public void exportToJSON(String data) {
        System.out.println(" Exporting data to JSON: { \"report\": \"" + data + "\" }");
    }
}

public class ReportModule {
    public static void main(String[] args) {
        Exportable csv = new CSVExporter();
        Exportable pdf = new PDFExporter();
        Exportable json = new JSONExporter();

        String reportData = "Quarterly Revenue Report";

        System.out.println(" Export Reports:");
        csv.export(reportData);
        pdf.export(reportData);
        json.export(reportData);

        System.out.println("\n JSON Export:");
        csv.exportToJSON(reportData); // uses default
        pdf.exportToJSON(reportData); // uses default
        json.exportToJSON(reportData); // overridden
    }
}
