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
        // Employee-specific logic for analyzing usage
        System.out.println("Analyzing usage for Admin card...");
    }
}
