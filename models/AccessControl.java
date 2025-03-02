package models;

import services.AuditService;
import services.Logger;
import services.TimeLogService;

public class AccessControl {
    private final AccessStrategy accessStrategy;
    private final Card card;
    private final TimeLogService timeLogService;

    public AccessControl(AccessStrategy accessStrategy, Card card) {
        this.accessStrategy = accessStrategy;
        this.card = card;
        this.timeLogService = new TimeLogService();
    }

    public boolean requestAccess(String floorLevel, String roomNumber) {
        boolean canAccess = timeLogService.canAccessNow(card);
        String accessStatus = timeLogService.getAccessStatus(canAccess);

        if (!canAccess) {
            Logger.getInstance().log("ACCESS DENIED: Card " + card.getCardIdList() + " tried to access Floor " + floorLevel + ", Room " + roomNumber + " - " + accessStatus);
            AuditService.log("ACCESS DENIED: Card " + card.getCardIdList() + " tried to access Floor " + floorLevel + ", Room " + roomNumber + " - " + accessStatus);
            return false;
        }

        boolean granted = accessStrategy.checkAccess(card, floorLevel, roomNumber);
        if (!granted) {
            Logger.getInstance().log("ACCESS DENIED: Card " + card.getCardIdList() + " tried to access Floor " + floorLevel + ", Room " + roomNumber);
            AuditService.log("ACCESS DENIED: Card " + card.getCardIdList() + " tried to access Floor " + floorLevel + ", Room " + roomNumber);
        } else {
            Logger.getInstance().log("ACCESS GRANTED: Card " + card.getCardIdList() + " accessed Floor " + floorLevel + ", Room " + roomNumber);
        }

        card.analyzeUsage();
        return granted;
    }
}
