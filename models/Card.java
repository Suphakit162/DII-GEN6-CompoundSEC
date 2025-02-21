package models; // เพิ่ม package models

import services.Logger; // เพิ่ม import services.Logger

import java.util.List;
import java.util.ArrayList;

public abstract class Card {
    private List<String> cardIdFacades;
    private String ownerName;
    private int ownerAge;
    private final String cardType;
    private boolean isActive;
    private List<String> accessLevels;
    private String encryptedData;
    private String password;

    public Card(String ownerName, int ownerAge, List<String> cardIdFacades, String cardType, String password) {
        this.ownerName = ownerName;
        this.ownerAge = ownerAge;
        this.cardIdFacades = cardIdFacades;
        this.cardType = cardType;
        this.isActive = true;
        this.accessLevels = new ArrayList<>();
        this.password = password;
        this.encrypt();
    }

    public List<String> getCardIdFacades() { return cardIdFacades; }
    public String getOwnerName() { return ownerName; }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getOwnerAge() { return ownerAge; }
    public String getCardType() { return cardType; }
    public boolean isActive() { return isActive; }
    public List<String> getAccessLevels() { return accessLevels; }
    public void setAccessLevels(List<String> accessLevels) { this.accessLevels = accessLevels; }
    public String getPassword() { return password; } // เพิ่ม getter สำหรับ password

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    private void encrypt() {
        if (password != null && !password.isEmpty()) {
            String encryptedPassword = "";
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (Character.isLetter(c)) {
                    if (Character.isUpperCase(c)) {
                        encryptedPassword += (char) ('A' + ('Z' - c));
                    } else {
                        encryptedPassword += (char) ('a' + ('z' - c));
                    }
                } else {
                    encryptedPassword += c;
                }
            }
            this.encryptedData = encryptedPassword;
        } else {
            this.encryptedData = "";
        }
    }

    public String decrypt(String key) {
        if (encryptedData != null && !encryptedData.isEmpty()) {
            String decryptedPassword = "";
            for (int i = 0; i < encryptedData.length(); i++) {
                char c = encryptedData.charAt(i);
                if (Character.isLetter(c)) {
                    if (Character.isUpperCase(c)) {
                        decryptedPassword += (char) ('Z' - (c - 'A'));
                    } else {
                        decryptedPassword += (char) ('z' - (c - 'a'));
                    }
                } else {
                    decryptedPassword += c;
                }
            }
            return decryptedPassword;
        } else {
            return "";
        }
    }

    public void deactivateCard() {
        this.isActive = false;
        Logger.log("Card ID: " + cardIdFacades.get(0) + " has been deactivated.");
    }

    public abstract void analyzeUsage();

    public int getCardId() { // เพิ่ม method getCardId() สำหรับใช้งานกับ Logger
        if (!cardIdFacades.isEmpty()) {
            return Integer.parseInt(cardIdFacades.get(0)); // หรือตาม logic ที่เหมาะสม
        }
        return -1; // หรือค่า default ที่เหมาะสม
    }
}




