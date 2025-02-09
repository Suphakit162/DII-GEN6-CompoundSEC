public class Admin {
    int accessLevel; // เลเวลของสิทธิ์การเข้าถึง (เช่น 1 - พนักงาน, 2 - ผู้เยี่ยมชม, 3 - ผู้ดูแลระบบ)

    // คอนสตรัคเตอร์สำหรับ Admin
    public Admin(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    // เพิ่มบัตรใหม่
    public void addCard(Card card) {
        System.out.println("Admin adding card: " + card.getDetails());
        // สามารถใช้ CardManagement เพื่อเพิ่มบัตรในระบบได้
        CardManagement cardManagement = new CardManagement();
        cardManagement.addCard(card);
    }

    // แก้ไขบัตร
    public void modifyCard(Card card) {
        System.out.println("Admin modifying card: " + card.getDetails());
        // ใช้ CardManagement เพื่อแก้ไขบัตร
        CardManagement cardManagement = new CardManagement();
        cardManagement.modifyCard(card);
    }

    // ยกเลิกบัตร
    public void revokeCard(Card card) {
        System.out.println("Admin revoking card: " + card.getDetails());
        // ใช้ CardManagement เพื่อยกเลิกบัตร
        CardManagement cardManagement = new CardManagement();
        cardManagement.revokeCard(card);
    }

    // ตรวจสอบการเข้าออกของบัตร
    public void auditAccess(Card card, boolean accessGranted) {
        AuditTrail.logAccess(card, accessGranted); // บันทึกข้อมูลการเข้าถึง
    }

    // ตรวจสอบระดับสิทธิ์การเข้าถึง
    public boolean checkAccessLevel(int requiredLevel) {
        if (accessLevel >= requiredLevel) {
            return true; // อนุญาตให้เข้าถึง
        }
        return false; // ปฏิเสธการเข้าถึง
    }
}

class AuditTrail {
    public static void logAccess(Card card, boolean accessGranted) {
        String status = accessGranted ? "Granted" : "Denied";
        System.out.println("Card Access Log: " + card.getDetails() + " | Access " + status + " | Time: " + java.time.LocalDateTime.now());
    }
}

