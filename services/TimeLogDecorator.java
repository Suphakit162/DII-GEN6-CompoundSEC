package services;

import models.Card;

import java.time.LocalTime;

public class TimeLogDecorator {

    public static boolean checkAccessTime() {
        LocalTime now = LocalTime.now();
        LocalTime accessStart = LocalTime.of(8, 0);
        LocalTime accessEnd = LocalTime.of(18, 0);
        return now.isAfter(accessStart) && now.isBefore(accessEnd);
    }

    public static String getAccessTimeStatus(boolean canAccess) {
        return canAccess ? "Allowed" : "Denied (Out of hours)";
    }
}
