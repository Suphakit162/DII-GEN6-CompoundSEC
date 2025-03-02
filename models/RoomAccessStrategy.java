package models;

public class RoomAccessStrategy implements AccessStrategy {
    @Override
    public boolean checkAccess(Card card, String floorLevel, String roomNumber) {
        if (!card.isActive()) {
            return false;
        }
        return card.getAccessLevels().contains(roomNumber);
    }
}
