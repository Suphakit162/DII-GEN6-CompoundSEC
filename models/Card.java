package models;

import services.Logger;

public abstract class Card {
    private final int cardId;
    private String ownerName;
    private int ownerAge;
    private final String cardType;
    private boolean isActive;

    public Card(String ownerName, int ownerAge, int cardId, String cardType) {
        this.ownerName = ownerName;
        this.ownerAge = ownerAge;
        this.cardId = cardId;
        this.cardType = cardType;
        this.isActive = true;
    }

    // Getters & Setters
    public int getCardId() { return cardId; }
    public String getOwnerName() { return ownerName; }
    public int getOwnerAge() { return ownerAge; }
    public String getCardType() { return cardType; }
    public boolean isActive() { return isActive; }

    public void deactivateCard() {
        this.isActive = false;
        Logger.log("Card ID: " + cardId + " has been deactivated.");
    }

    public abstract void analyzeUsage();
}

//Id มีหลายชั้นเเละต้องเข้าถึงได้ตอนใช้ที่ไหนๆ ถ้าคนที่เก็บบัตรนี้ได้สามารถใช้การ์ดนี้ได้ทุกๆที่ สื่อคือ การ์ดใบนี้ควรมีพาสเวิร์ด ตามแบบ multi-facades
