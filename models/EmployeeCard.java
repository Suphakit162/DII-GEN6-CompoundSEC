package models;

import java.util.List;

public class EmployeeCard extends Card {

    public EmployeeCard(String ownerName, int ownerAge, List<String> cardId, String password) {
        super(ownerName, ownerAge, cardId, password);
    }

    @Override
    public String getCardType() {
        return "Employee";
    }

    @Override
    public void analyzeUsage() {

        System.out.println("Analyzing usage for Employee card...");
    }
}
