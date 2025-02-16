package models;

import services.Logger;

public class FloorAccessControl {
    private final String floorLevel;
    private final Card card;

    public FloorAccessControl(String floorLevel, Card card) {
        this.floorLevel = floorLevel;
        this.card = card;
    }

    public void useLift() {
        if (card.isActive()) {
            Logger.log("Lift Access Granted - Floor: " + floorLevel + " | Card ID: " + card.getCardId());
        } else {
            Logger.log("Lift Access Denied - Floor: " + floorLevel + " | Card ID: " + card.getCardId());
        }
    }
}






