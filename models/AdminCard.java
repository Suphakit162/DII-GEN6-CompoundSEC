package models;

import java.util.List;

public class AdminCard extends Card {

    public AdminCard(String ownerName, int ownerAge, List<String> cardId, String password) {
        super(ownerName, ownerAge, cardId, password);
    }

    @Override
    public String getCardType() {
        return "Admin";
    }

    @Override
    public void analyzeUsage() {

        System.out.println("Analyzing usage for Admin card...");
    }
}
