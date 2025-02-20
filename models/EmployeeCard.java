package models;

public class EmployeeCard extends Card {
    public EmployeeCard(String ownerName, int ownerAge, int cardId) {
        super(ownerName, ownerAge, cardId, "Employee"); // เพิ่ม "Employee" เข้าไป
    }


    @Override
    public void analyzeUsage() {
        System.out.println(" Employee Card Usage Analysis: " + getCardId());
    }
}

