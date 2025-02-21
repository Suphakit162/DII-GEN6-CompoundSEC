package services;

public class UserLogDecorator {
    private Logger logger;
    private String userName;

    public UserLogDecorator(Logger logger, String userName) {
        this.logger = logger;
        this.userName = userName;
    }

    public void log(String message) {
        logger.log("[User: " + userName + "] " + message);
    }
}
