package services;

import java.time.LocalDateTime;

public class LoggingService {
    // ตัวแปร static สำหรับเก็บ instance เดียวของ LoggingService
    private static LoggingService instance;

    // Constructor ที่ป้องกันการสร้าง instance จากภายนอก
    private LoggingService() {}

    // เมธอด static สำหรับดึง instance เดียว
    public static LoggingService getInstance() {
        if (instance == null) {
            instance = new LoggingService();
        }
        return instance;
    }

    // ฟังก์ชัน log สำหรับบันทึกข้อมูล
    public void log(String message) {
        System.out.println("[LOG] " + LocalDateTime.now() + " - " + message);
    }
}



