import models.Card;
import models.EmployeeCard;
import models.VisitorCard;
import services.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Main extends JFrame {
    private JTextField cardIdField, ownerNameField, ownerAgeField, passwordField;
    private JComboBox<String> cardTypeComboBox;
    private JTextArea resultTextArea;
    private JButton submitButton, registerButton, deactivateButton, modifyButton;
    private Admin admin;

    public Main() {
        setTitle("Access Control System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        admin = new Admin(1); // กำหนด Admin ให้กับระบบ

        // Panel สำหรับ Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2));

        inputPanel.add(new JLabel("Do you have a card ?"));
        JCheckBox hasCardCheckBox = new JCheckBox("Yes, I have.");
        inputPanel.add(hasCardCheckBox);

        inputPanel.add(new JLabel("Card ID:"));
        cardIdField = new JTextField();
        inputPanel.add(cardIdField);

        inputPanel.add(new JLabel("Owner Name:"));
        ownerNameField = new JTextField();
        inputPanel.add(ownerNameField);

        inputPanel.add(new JLabel("Owner Age:"));
        ownerAgeField = new JTextField();
        inputPanel.add(ownerAgeField);

        inputPanel.add(new JLabel("Password:"));
        passwordField = new JTextField();
        inputPanel.add(passwordField);

        inputPanel.add(new JLabel("Card Type:"));
        cardTypeComboBox = new JComboBox<>(new String[]{"Employee", "Visitor"});
        inputPanel.add(cardTypeComboBox);

        // ปุ่ม Submit
        submitButton = new JButton("Check a card");
        inputPanel.add(submitButton);

        // ปุ่ม Register
        registerButton = new JButton("Register a new card");
        inputPanel.add(registerButton);

        // ปุ่ม Deactivate Card
        deactivateButton = new JButton("Cancel a card");
        inputPanel.add(deactivateButton);

        // ปุ่ม Modify Card
        modifyButton = new JButton("Fix a card");
        inputPanel.add(modifyButton);

        // TextArea สำหรับแสดงผล
        resultTextArea = new JTextArea(5, 40);
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // ActionListener สำหรับการตรวจสอบบัตร
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hasCardCheckBox.isSelected()) {
                    // ตรวจสอบบัตร
                    int cardId = Integer.parseInt(cardIdField.getText());
                    Card card = admin.findCard(cardId);
                    if (card != null) {
                        String cardType = card.getCardType();
                        String ownerName = card.getOwnerName();
                        List<String> accessLevels = card.getAccessLevels();

                        String result = "บัตรของ " + ownerName + "\n" +
                                "ประเภท: " + cardType + "\n" +
                                "สามารถเข้าถึง: " + accessLevels + "\n";

                        resultTextArea.setText(result);
                    } else {
                        resultTextArea.setText("Don't have your card\n Please to register.");
                    }
                } else {
                    resultTextArea.setText("Please fill in your information to log in again.");
                }
            }
        });

        // ActionListener สำหรับการลงทะเบียนบัตรใหม่
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // สร้างข้อมูลบัตรใหม่
                String ownerName = ownerNameField.getText();
                int ownerAge = Integer.parseInt(ownerAgeField.getText());
                String password = passwordField.getText();
                List<String> accessLevels = new ArrayList<>();
                accessLevels.add("LowFloor"); // ใส่ระดับการเข้าถึงเบื้องต้น
                accessLevels.add("MediumFloor");
                accessLevels.add("HighFloor");

                String cardType = (String) cardTypeComboBox.getSelectedItem();
                List<String> cardIdFacades = new ArrayList<>();
                cardIdFacades.add(String.valueOf(new Random().nextInt(10000))); // สุ่ม ID

                Card card;
                if (cardType.equals("Employee")) {
                    card = new EmployeeCard(ownerName, ownerAge, cardIdFacades, password);
                } else {
                    card = new VisitorCard(ownerName, ownerAge, cardIdFacades, password);
                }

                admin.addCard(card, accessLevels);

                resultTextArea.setText("Successfully: " + card.getCardIdFacades().get(0));
            }
        });

        // ActionListener สำหรับการยกเลิกบัตร
        deactivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ยกเลิกบัตร
                int cardId = Integer.parseInt(cardIdField.getText());
                Card card = admin.findCard(cardId);
                if (card != null) {
                    admin.revokeCard(card);
                    resultTextArea.setText("card at ID: " + cardId + " It has been canceled already.");
                } else {
                    resultTextArea.setText("Can't find card at ID: " + cardId);
                }
            }
        });

        // ActionListener สำหรับการแก้ไขข้อมูลบัตร
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // แก้ไขข้อมูลบัตร
                int cardId = Integer.parseInt(cardIdField.getText());
                Card card = admin.findCard(cardId);
                if (card != null) {
                    String newOwnerName = ownerNameField.getText();
                    List<String> newAccessLevels = new ArrayList<>();
                    newAccessLevels.add("LowFloor"); // ใส่ระดับการเข้าถึงใหม่
                    newAccessLevels.add("MediumFloor");
                    newAccessLevels.add("HighFloor");

                    admin.modifyCard(card, newOwnerName, newAccessLevels);
                    resultTextArea.setText("Card information ID: " + cardId + " Successfully");
                } else {
                    resultTextArea.setText("Can't find card at ID: " + cardId);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}



