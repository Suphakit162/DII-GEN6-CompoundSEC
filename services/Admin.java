package services;

import models.Card;
import models.EmployeeCard; // Import ที่ถูกต้อง
import models.VisitorCard; // Import ที่ถูกต้อง
import java.util.ArrayList;
import java.util.List;

public class Admin {
    private final int accessLevel;
    private final List<Card> cards;

    public Admin(int accessLevel) {
        this.accessLevel = accessLevel;
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
        Logger.log("Card Added - ID: " + card.getCardId() + " | Owner: " + card.getOwnerName());
    }

    public void revokeCard(Card card) {
        if (card.isActive()) { // ตรวจสอบสถานะบัตรก่อน revoke
            card.deactivateCard();
            Logger.log("Card Revoked - ID: " + card.getCardId());
        } else {
            Logger.log("Card is already inactive - ID: " + card.getCardId()); // Log กรณีบัตร inactive อยู่แล้ว
        }
    }

    public void modifyCard(Card card, String newOwner) {
        if (card instanceof EmployeeCard) {
            EmployeeCard employeeCard = (EmployeeCard) card;
            // แก้ไขข้อมูลบัตรพนักงาน (ตัวอย่าง)
            // employeeCard.setOwnerName(newOwner); // สมมติว่ามี setter
            Logger.log("Employee Card Modified - ID: " + card.getCardId() + " | New Owner: " + newOwner);

        } else if (card instanceof VisitorCard) {
            VisitorCard visitorCard = (VisitorCard) card;
            // แก้ไขข้อมูลบัตรผู้เยี่ยมชม (ตัวอย่าง)
            // visitorCard.setOwnerName(newOwner); // สมมติว่ามี setter
            Logger.log("Visitor Card Modified - ID: " + card.getCardId() + " | New Owner: " + newOwner);
        } else {
            Logger.log("Unknown Card Type Modified - ID: " + card.getCardId() + " | New Owner: " + newOwner);
        }
    }

    public Card findCard(int cardId) {
        for (Card card : cards) {
            if (card.getCardId() == cardId) {
                return card;
            }
        }
        return null; // Return null if card not found
    }
}

