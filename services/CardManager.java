package services;

import models.Card;
import models.EmployeeCard;
import models.VisitorCard;

import java.util.List;
import java.util.Random;

public class CardManager {

    public static Card createCard(String ownerName, int ownerAge, String password, String cardType) {
        String cardId = String.valueOf(new Random().nextInt(9000) + 1000);

        if (cardType.equals("Employee")) {
            return new EmployeeCard(ownerName, ownerAge, List.of(cardId), password);
        } else {
            return new VisitorCard(ownerName, ownerAge, List.of(cardId), password);
        }
    }
}
