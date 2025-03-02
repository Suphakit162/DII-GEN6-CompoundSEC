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
        this.cardDatabase = new HashMap<>();
    }

    public void addCard(Card card, List<String> accessLevels) {
        card.setAccessLevels(accessLevels);
        // ใช้ cardId ที่แรกใน cardIds
        int cardId = Integer.parseInt(card.getCardIdList().get(0));
        cardDatabase.put(cardId, card);
    }

    public Card findCard(int cardId) {
        return cardDatabase.get(cardId);
    }

    public void modifyCard(Card card, String newOwner, List<String> newAccessLevels) {
        card.modifyCard(newOwner, newAccessLevels);
    }

    public void revokeCard(Card card) {
        card.deactivate();  // เรียกเมธอด deactivate()
    }

    public int getAdminId() {
        return adminId;
    }
}
