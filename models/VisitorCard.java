package models;

import java.util.List;

public class VisitorCard extends Card {

    public VisitorCard(String ownerName, int ownerAge, List<String> cardId, String password) {
        super(ownerName, ownerAge, cardId, password);
    }

    @Override
    public String getCardType() {
        return "Visitor";
    }

    @Override
    public void analyzeUsage() {

        System.out.println("Analyzing usage for Visitor card...");
    }
}
