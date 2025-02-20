// models/FloorAccessStrategy.java
package models;

import services.Logger;

public class FloorAccessStrategy implements AccessStrategy {
    private final String floorLevel;

    public FloorAccessStrategy(String floorLevel) {
        this.floorLevel = floorLevel;
    }

    @Override
    public void grantAccess(Card card) {
        if (card.isActive()) {
            Logger.log("Lift Access Granted - Floor: " + floorLevel + " | Card ID: " + card.getCardId());
        } else {
            Logger.log("Lift Access Denied - Floor: " + floorLevel + " | Card ID: " + card.getCardId());
        }
    }
}
