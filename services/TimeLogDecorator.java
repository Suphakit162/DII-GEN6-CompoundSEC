package services;

import java.time.LocalDateTime;

public class TimeLogDecorator {
    private Logger logger;

    public TimeLogDecorator(Logger logger) {
        this.logger = logger;
    }

    public void log(String message) {
        logger.log("[Time: " + LocalDateTime.now() + "] " + message);
    }
}
