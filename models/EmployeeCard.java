package models;

import models.Card;

import java.util.List;

public class EmployeeCard extends Card {
    public EmployeeCard(String ownerName, int ownerAge, List<String> cardIdFacades, String password) {
        super(ownerName, ownerAge, cardIdFacades, "Employee", password);
    }

    @Override
    public void analyzeUsage() {
        System.out.println("Employee Card Usage Analysis: " + getCardIdFacades().get(0));
    }
}