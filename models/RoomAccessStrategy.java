// models/RoomAccessStrategy.java
package models;

import services.Logger;

public class RoomAccessStrategy implements AccessStrategy {
    private final String roomNumber;

    public RoomAccessStrategy(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public void grantAccess(Card card) {
        if (card.isActive()) {
            Logger.log("Room Access Granted - Room: " + roomNumber + " | Card ID: " + card.getCardId());
        } else {
            Logger.log("Room Access Denied - Room: " + roomNumber + " | Card ID: " + card.getCardId());
        }
    }
}