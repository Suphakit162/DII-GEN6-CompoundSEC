import models.Card;
import models.EmployeeCard;
import models.VisitorCard;
import services.Admin;
import services.TimeLogDecorator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.time.LocalTime;
import java.util.List;

public class Main extends JFrame {
    private JTextField cardIdField, ownerNameField, ownerAgeField, passwordField;
    private JComboBox<String> cardTypeComboBox;
    private JTextArea resultTextArea;
    private JButton submitButton, registerButton, deactivateButton, modifyButton;
    private JCheckBox hasCardCheckBox;
    private Admin admin;

    public Main() {
        setTitle("🔐 Access Control System");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        admin = new Admin(1);

        // เพิ่มข้อมูลเริ่มต้น
        admin.addCard(new EmployeeCard("Suphakit", 19, Arrays.asList("0001"), "1111"), Arrays.asList("LowFloor", "MediumFloor", "HighFloor"));

        JLabel headerLabel = new JLabel("Access Control System", JLabel.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        headerLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        inputPanel.add(new JLabel("🔑 Do you have a card?"));
        hasCardCheckBox = new JCheckBox("Yes, I have.");
        inputPanel.add(hasCardCheckBox);

        inputPanel.add(new JLabel("📌 Card ID:"));
        cardIdField = new JTextField();
        inputPanel.add(cardIdField);

        inputPanel.add(new JLabel("👤 Owner Name:"));
        ownerNameField = new JTextField();
        inputPanel.add(ownerNameField);

        inputPanel.add(new JLabel("🎂 Owner Age:"));
        ownerAgeField = new JTextField();
        inputPanel.add(ownerAgeField);

        inputPanel.add(new JLabel("🔒 Password:"));
        passwordField = new JTextField();
        inputPanel.add(passwordField);

        inputPanel.add(new JLabel("📇 Card Type:"));
        cardTypeComboBox = new JComboBox<>(new String[]{"Employee", "Visitor"});
        inputPanel.add(cardTypeComboBox);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        submitButton = new JButton("🔍 Check Card");
        registerButton = new JButton("➕ Register Card");
        deactivateButton = new JButton("❌ Cancel Card");
        modifyButton = new JButton("✏️ Modify Card");

        buttonPanel.add(submitButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(deactivateButton);
        buttonPanel.add(modifyButton);

        resultTextArea = new JTextArea(6, 40);
        resultTextArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        resultTextArea.setEditable(false);
        resultTextArea.setBorder(BorderFactory.createTitledBorder("📢 Result"));
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        add(headerLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);

        submitButton.addActionListener(e -> checkCard());
        registerButton.addActionListener(e -> registerCard());
        deactivateButton.addActionListener(e -> deactivateCard());
        modifyButton.addActionListener(e -> modifyCard());
    }

    private void modifyCard() {
        String cardIdText = cardIdField.getText().trim();
        String newOwner = ownerNameField.getText().trim();
        String cardType = (String) cardTypeComboBox.getSelectedItem();

        if (cardIdText.isEmpty() || newOwner.isEmpty()) {
            resultTextArea.setText("⚠️ Please enter Card ID and New Owner Name.");
            return;
        }

        if (!cardIdText.matches("\\d+")) {
            resultTextArea.setText("⚠️ Card ID must be a number.");
            return;
        }

        int cardId = Integer.parseInt(cardIdText);
        Card card = admin.findCard(cardId);

        if (card != null) {
            List<String> newAccessLevels;

            if (cardType.equals("Employee")) {
                newAccessLevels = Arrays.asList("LowFloor", "MediumFloor", "HighFloor");
            } else {
                newAccessLevels = Arrays.asList("LowFloor", "MediumFloor"); // Visitor จำกัดแค่ 2 ชั้น
            }

            admin.modifyCard(card, newOwner, newAccessLevels);
            resultTextArea.setText("✅ Card modified!\nNew Owner: " + newOwner + "\nNew Access: " + newAccessLevels);
        } else {
            resultTextArea.setText("❌ Card not found.");
        }
    }

    private void checkCard() {
        String cardIdText = cardIdField.getText().trim();
        if (cardIdText.isEmpty()) {
            resultTextArea.setText("⚠️ Please enter a Card ID.");
            return;
        }

        int cardId = Integer.parseInt(cardIdText);
        Card card = admin.findCard(cardId);
        if (card != null) {
            if (!card.isActive()) {  // ตรวจสอบสถานะของบัตร
                resultTextArea.setText("❌ Card is inactive. Please register again.");
            } else {
                LocalTime now = LocalTime.now();
                LocalTime accessStart = LocalTime.of(8, 0);
                LocalTime accessEnd = LocalTime.of(18, 0);
                boolean canAccess = now.isAfter(accessStart) && now.isBefore(accessEnd);
                resultTextArea.setText(
                        "✔️ Owner: " + card.getOwnerName() + "\n" +
                                "📇 Card Type: " + card.getCardType() + "\n" +
                                "🚪 Access Levels: " + card.getAccessLevels() + "\n" +
                                "⏰ Access Time: " + (canAccess ? "Allowed" : "Denied (Out of hours)")
                );
            }
        } else {
            resultTextArea.setText("❌ Card not found. Please register.");
        }
    }

    private void registerCard() {
        String ownerName = ownerNameField.getText().trim();
        String ownerAgeText = ownerAgeField.getText().trim();
        String password = passwordField.getText().trim();
        String cardType = (String) cardTypeComboBox.getSelectedItem();

        if (ownerName.isEmpty() || ownerAgeText.isEmpty() || password.isEmpty()) {
            resultTextArea.setText("⚠️ Please fill in all fields.");
            return;
        }

        int ownerAge = Integer.parseInt(ownerAgeText);
        List<String> accessLevels = cardType.equals("Employee")
                ? Arrays.asList("LowFloor", "MediumFloor", "HighFloor")
                : Arrays.asList("LowFloor", "MediumFloor");

        String cardId = String.valueOf(new Random().nextInt(9000) + 1000);
        Card card = cardType.equals("Employee")
                ? new EmployeeCard(ownerName, ownerAge, Collections.singletonList(cardId), password)
                : new VisitorCard(ownerName, ownerAge, Collections.singletonList(cardId), password);

        admin.addCard(card, accessLevels);
        resultTextArea.setText("✅ Successfully registered.\n📌 Card ID: " + cardId);
    }

    private void deactivateCard() {
        String cardIdText = cardIdField.getText().trim();
        if (cardIdText.isEmpty()) {
            resultTextArea.setText("⚠️ Please enter a valid Card ID.");
            return;
        }

        int cardId = Integer.parseInt(cardIdText);
        Card card = admin.findCard(cardId);
        if (card != null) {
            admin.revokeCard(card);  // ลบบัตรออกจากระบบ
            resultTextArea.setText("✅ Card ID " + cardId + " has been canceled.");

            // รีเฟรชผลลัพธ์
            checkCard();  // ตรวจสอบสถานะบัตรใหม่หลังจากยกเลิก
        } else {
            resultTextArea.setText("❌ Card not found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}



