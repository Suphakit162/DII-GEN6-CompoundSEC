import models.Card;
import models.EmployeeCard;
import models.VisitorCard;
import services.Admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.util.*;
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

        // ** Header Title **
        JLabel headerLabel = new JLabel("Access Control System", JLabel.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        headerLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // ** Panel สำหรับ Input **
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2, 10, 10));
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

        // ** Panel สำหรับปุ่ม **
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        submitButton = new JButton("🔍 Check Card");
        registerButton = new JButton("➕ Register Card");
        deactivateButton = new JButton("❌ Cancel Card");
        modifyButton = new JButton("✏️ Modify Card");

        buttonPanel.add(submitButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(deactivateButton);
        buttonPanel.add(modifyButton);

        // ** ปรับแต่งปุ่ม **
        JButton[] buttons = {submitButton, registerButton, deactivateButton, modifyButton};
        for (JButton button : buttons) {
            button.setFont(new Font("SansSerif", Font.BOLD, 14));
            button.setBackground(new Color(60, 120, 180));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEtchedBorder());
        }

        // ** TextArea สำหรับแสดงผล **
        resultTextArea = new JTextArea(6, 40);
        resultTextArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        resultTextArea.setEditable(false);
        resultTextArea.setBorder(BorderFactory.createTitledBorder("📢 Result"));
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        // ** เพิ่มทุกอย่างเข้า Frame **
        add(headerLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);

        // ** ActionListener สำหรับปุ่ม **
        submitButton.addActionListener(e -> checkCard());
        registerButton.addActionListener(e -> registerCard());
        deactivateButton.addActionListener(e -> deactivateCard());
        modifyButton.addActionListener(e -> modifyCard());
    }

    private void checkCard() {
        if (hasCardCheckBox.isSelected()) {
            String cardIdText = cardIdField.getText().trim();
            if (cardIdText.isEmpty() || !cardIdText.matches("\\d+")) {
                resultTextArea.setText("⚠️ Please enter a valid Card ID.");
                return;
            }

            int cardId = Integer.parseInt(cardIdText);
            Card card = admin.findCard(cardId);
            if (card != null) {
                resultTextArea.setText(
                        "✔️ Owner: " + card.getOwnerName() + "\n" +
                                "📇 Card Type: " + card.getCardType() + "\n" +
                                "🚪 Access Levels: " + card.getAccessLevels()
                );
            } else {
                resultTextArea.setText("❌ Card not found. Please register.");
            }
        } else {
            resultTextArea.setText("⚠️ Please fill in your information to log in again.");
        }
    }

    private void registerCard() {
        String ownerName = ownerNameField.getText().trim();
        String ownerAgeText = ownerAgeField.getText().trim();
        String password = passwordField.getText().trim();

        if (ownerName.isEmpty() || ownerAgeText.isEmpty() || password.isEmpty()) {
            resultTextArea.setText("⚠️ Please fill in all fields.");
            return;
        }

        if (!ownerAgeText.matches("\\d+")) {
            resultTextArea.setText("⚠️ Owner Age must be a number.");
            return;
        }

        int ownerAge = Integer.parseInt(ownerAgeText);
        List<String> accessLevels = Arrays.asList("LowFloor", "MediumFloor", "HighFloor");
        String cardType = (String) cardTypeComboBox.getSelectedItem();
        String cardId = String.valueOf(new Random().nextInt(9000) + 1000);

        List<String> cardIdFacades = Collections.singletonList(cardId);
        Card card = cardType.equals("Employee")
                ? new EmployeeCard(ownerName, ownerAge, cardIdFacades, password)
                : new VisitorCard(ownerName, ownerAge, cardIdFacades, password);

        admin.addCard(card, accessLevels);
        resultTextArea.setText("✅ Successfully registered.\n📌 Card ID: " + cardId);
    }

    private void deactivateCard() {
        String cardIdText = cardIdField.getText().trim();
        if (cardIdText.isEmpty() || !cardIdText.matches("\\d+")) {
            resultTextArea.setText("⚠️ Please enter a valid Card ID.");
            return;
        }

        int cardId = Integer.parseInt(cardIdText);
        Card card = admin.findCard(cardId);
        if (card != null) {
            admin.revokeCard(card);
            resultTextArea.setText("✅ Card ID " + cardId + " has been canceled.");
        } else {
            resultTextArea.setText("❌ Card not found.");
        }
    }

    private void modifyCard() {
        // โค้ดการแก้ไขเหมือนเดิม
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}



