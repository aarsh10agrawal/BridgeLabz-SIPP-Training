// Marker Interface
interface BackupSerializable {
    // no methods, just a marker
}

// Employee class (eligible for backup)
class Employee implements BackupSerializable {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + "}";
    }
}

// TemporarySession class (NOT for backup)
class TemporarySession {
    private String sessionId;

    public TemporarySession(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "TemporarySession{sessionId='" + sessionId + "'}";
    }
}

// Backup processor utility
class BackupProcessor {
    public static void backup(Object obj) {
        if (obj instanceof BackupSerializable) {
            System.out.println(" Backing up: " + obj);
        } else {
            System.out.println(" Skipping (not marked for backup): " + obj);
        }
    }
}

public class BackupSystem {
    public static void main(String[] args) {
        Employee emp = new Employee("Monika", 101);
        TemporarySession session = new TemporarySession("XYZ123");

        System.out.println(" Backup Process Started:");
        BackupProcessor.backup(emp);      // Allowed
        BackupProcessor.backup(session);  // Skipped
    }
}
