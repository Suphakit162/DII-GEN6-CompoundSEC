package services;

import models.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Admin {
    private int adminId;
    private Map<Integer, Card> cardDatabase;

    public Admin(int adminId) {
        this.adminId = adminId;
        cardDatabase = new HashMap<>();
    }

    // เพิ่มบัตรลงในฐานข้อมูล
    public void addCard(Card card, List<String> accessLevels) {
        card.setAccessLevels(accessLevels);
        cardDatabase.put(Integer.parseInt(card.getCardId().get(0)), card);  // ใช้ Card ID เป็นคีย์
    }

    // ค้นหาบัตรจาก Card ID
    public Card findCard(int cardId) {
        return cardDatabase.get(cardId);
    }

    // ยกเลิกการใช้งานบัตร (Deactivate)
    public void revokeCard(Card card) {
        card.setActive(false);
    }

    // แก้ไขบัตร
    public void modifyCard(Card card, String newOwnerName, int newOwnerAge, List<String> newAccessLevels) {
        card.setOwnerName(newOwnerName);
        card.setAccessLevels(newAccessLevels);
    }
}
