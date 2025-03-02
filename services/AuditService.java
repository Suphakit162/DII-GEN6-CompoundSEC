package services;

import java.util.ArrayList;
import java.util.List;

public class AuditService {
    private static List<String> auditLogs = new ArrayList<>();

    public static void log(String message) {
        auditLogs.add("[AUDIT] " + message);
        System.out.println("[AUDIT] " + message);
    }

    public static List<String> getLogs() {
        return auditLogs;
    }
}
