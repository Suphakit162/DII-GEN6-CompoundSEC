package models;

import java.util.List;

public abstract class Card {
    private String ownerName;
    private int ownerAge;
    private List<String> accessLevels;
    private boolean isActive;
    private List<String> cardIds;
    private String password;

    public Card(String ownerName, int ownerAge, List<String> cardIds, String password) {
        this.ownerName = ownerName;
        this.ownerAge = ownerAge;
        this.cardIds = cardIds;
        this.password = password;
        this.isActive = true;  // default to active
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getOwnerAge() {
        return ownerAge;
    }

    public List<String> getAccessLevels() {
        return accessLevels;
    }

    public void setAccessLevels(List<String> accessLevels) {
        this.accessLevels = accessLevels;
    }

    public List<String> getCardIdList() {
        return cardIds;
    }

    public boolean isActive() {
        return isActive;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void modifyCard(String newOwner, List<String> newAccessLevels) {
        this.ownerName = newOwner;
        this.accessLevels = newAccessLevels;
    }

    // เพิ่ม method analyzeUsage()
    public void analyzeUsage() {
        // ตัวอย่างการใช้งาน: พิมพ์การใช้งานบัตรออกมา
        System.out.println("Analyzing usage of card " + cardIds);
        // หรือจะทำการบันทึกข้อมูลการใช้งานหรืออะไรบางอย่างที่เกี่ยวข้อง
    }

    // เพิ่ม getter และ setter สำหรับ password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract String getCardType(); // ให้ subclass เช่น EmployeeCard หรือ VisitorCard มา implement
}
