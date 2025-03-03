package models;

public interface AccessStrategy {
    boolean checkAccess(Card card, String floorLevel, String roomNumber);
}
