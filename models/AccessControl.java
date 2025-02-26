package models;

import services.AuditService;
import services.Logger;

public class AccessControl {
    private final AccessStrategy accessStrategy;
    private final Card card;

    public AccessControl(AccessStrategy accessStrategy, Card card) {
        this.accessStrategy = accessStrategy;
        this.card = card;
    }

    public boolean requestAccess(String floorLevel, String roomNumber) {
        boolean granted = accessStrategy.checkAccess(card, floorLevel, roomNumber);
        if (!granted) {
            Logger.getInstance().log("ACCESS DENIED: Card " + card.getCardIdFacades().get(0) + " tried to access Floor " + floorLevel + ", Room " + roomNumber);
            AuditService.log("ACCESS DENIED: Card " + card.getCardIdFacades().get(0) + " tried to access Floor " + floorLevel + ", Room " + roomNumber);
        } else {
            Logger.getInstance().log("ACCESS GRANTED: Card " + card.getCardIdFacades().get(0) + " accessed Floor " + floorLevel + ", Room " + roomNumber);
        }
        return granted;
    }
}







