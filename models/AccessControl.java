package models;

public class AccessControl {
    private final AccessStrategy accessStrategy;
    private final Card card;

    public AccessControl(AccessStrategy accessStrategy, Card card) {
        this.accessStrategy = accessStrategy;
        this.card = card;
    }

    public boolean requestAccess(String floorLevel, String roomNumber) {
        return accessStrategy.checkAccess(card, floorLevel, roomNumber);
    }
}






