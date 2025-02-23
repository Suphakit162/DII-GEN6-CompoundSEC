package services;

import models.Card;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    private final int accessLevel;
    private final List<Card> cards;

    public Admin(int accessLevel) {
        this.accessLevel = accessLevel;
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card, List<String> accessLevels) {
        card.setAccessLevels(accessLevels);
        cards.add(card);
        Logger.log("Card Added - ID: " + card.getCardIdFacades().get(0) + " | Owner: " + card.getOwnerName());
    }

    public void revokeCard(Card card) {
        if (card.isActive()) {
            card.deactivateCard(); // หรือปิดการใช้งานบัตร
            cards.remove(card);     // ลบบัตรออกจากรายการ cards
            Logger.log("Card Revoked - ID: " + card.getCardIdFacades().get(0));
        } else {
            Logger.log("Card is already inactive - ID: " + card.getCardIdFacades().get(0));
        }
    }

    public void modifyCard(Card card, String newOwner, List<String> newAccessLevels) {
        card.setOwnerName(newOwner);
        card.setAccessLevels(newAccessLevels);
        Logger.log("Card Modified - ID: " + card.getCardIdFacades().get(0) + " | New Owner: " + newOwner);
    }

    public Card findCard(int cardId) {
        for (Card card : cards) {
            if (card.getCardId() == cardId) {
                return card;
            }
        }
        return null;
    }
}


