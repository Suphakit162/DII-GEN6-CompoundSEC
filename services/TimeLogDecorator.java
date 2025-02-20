// services/TimeLogDecorator.java
package services;

import java.time.LocalDateTime;

public class TimeLogDecorator extends Logger {

    @Override
    public void log(String message) {
        super.log("[Time: " + LocalDateTime.now() + "] " + message);  // เพิ่มเวลาใน Log
    }
}