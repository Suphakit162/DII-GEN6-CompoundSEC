package models;

import java.util.List;

public abstract class Card {
    private String ownerName;
    private int ownerAge;
    private List<String> cardId;
    private String password;
    private boolean active;
    private List<String> accessLevels;

    public Card(String ownerName, int ownerAge, List<String> cardId, String password) {
        setOwnerName(ownerName);
        setOwnerAge(ownerAge);
        setCardId(cardId);
        setPassword(password);
        this.active = true;
    }

    // Getter and Setter methods
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getOwnerAge() {
        return ownerAge;
    }

    public void setOwnerAge(int ownerAge) {
        this.ownerAge = ownerAge;
    }

    public List<String> getCardId() {
        return cardId;
    }

    public void setCardId(List<String> cardId) {
        this.cardId = cardId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // ตรวจสอบรหัสผ่านที่ต้องประกอบด้วยตัวอักษร (เล็กและใหญ่) และตัวเลข
        if (password.matches("^[a-zA-Z0-9]+$")) {
            this.password = password;
        } else {
            System.out.println("Warning: Password must contain only letters (both lowercase and uppercase) and numbers.");
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getAccessLevels() {
        return accessLevels;
    }

    public void setAccessLevels(List<String> accessLevels) {
        this.accessLevels = accessLevels;
    }


    public List<String> getCardIdList() {
        return this.cardId;  // คืนค่า cardId list
    }

    // Abstract method that subclasses must implement
    public abstract String getCardType();
    public abstract void analyzeUsage();  // Add this abstract method
}
