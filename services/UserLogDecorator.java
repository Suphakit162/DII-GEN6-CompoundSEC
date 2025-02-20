// services/UserLogDecorator.java
package services;

public class UserLogDecorator extends Logger {
    private final String userName;

    public UserLogDecorator(String userName) {
        this.userName = userName;
    }

    @Override
    public void log(String message) {
        super.log("[User: " + userName + "] " + message);  // เพิ่มชื่อผู้ใช้ใน Log
    }
}

