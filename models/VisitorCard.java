package models;

import java.util.List;

public class VisitorCard extends Card {
    public VisitorCard(String ownerName, int ownerAge, List<String> cardIdFacades, String password) {
        super(ownerName, ownerAge, cardIdFacades, "Visitor", password);
    }

    @Override
    public void analyzeUsage() {
        System.out.println("Visitor Card Usage Analysis: " + getCardIdFacades().get(0));
    }
}
