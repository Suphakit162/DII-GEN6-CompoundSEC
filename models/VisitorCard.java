package models;

public class VisitorCard extends Card {
    public VisitorCard(String ownerName, int ownerAge, int cardId) {
        super(ownerName, ownerAge, cardId, "Visitor"); // เพิ่ม "Visitor" เข้าไป
    }


    @Override
    public void analyzeUsage() {
        System.out.println(" Visitor Card Usage Analysis: " + getCardId());
    }
}
