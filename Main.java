public class Main {
    public static void main(String[] args) {
        // สร้าง Admin โดยกำหนดระดับสิทธิ์ (1 - พนักงาน, 2 - ผู้เยี่ยมชม, 3 - ผู้ดูแลระบบ)
        Admin admin = new Admin(3); // ผู้ดูแลระบบ (ระดับ 3)

        // สร้างบัตรพนักงาน และบัตรผู้เยี่ยมชม
        Card employeeCard = new EmployeeCard("John Doe", 30, 1001, "Employee");
        Card visitorCard = new VisitorCard("Jane Smith", 25, 2001, "Visitor");

        // เพิ่มบัตร
        System.out.println("\n-- Adding Cards --");
        admin.addCard(employeeCard);
        admin.addCard(visitorCard);

        // ตรวจสอบสิทธิ์การเข้าถึงระดับ 2 (สามารถเข้าถึงผู้เยี่ยมชมและพนักงาน)
        System.out.println("\n-- Check Access Level --");
        if (admin.checkAccessLevel(2)) {
            System.out.println("Admin has sufficient access level to add cards.");
        } else {
            System.out.println("Access denied. Insufficient privileges.");
        }

        // แก้ไขบัตรพนักงาน
        System.out.println("\n-- Modifying Employee Card --");
        admin.modifyCard(employeeCard);

        // ยกเลิกบัตรผู้เยี่ยมชม
        System.out.println("\n-- Revoking Visitor Card --");
        admin.revokeCard(visitorCard);

        // บันทึกการเข้าถึงบัตร
        System.out.println("\n-- Auditing Access --");
        admin.auditAccess(employeeCard, true); // การเข้าถึงได้รับอนุญาต
        admin.auditAccess(visitorCard, false); // การเข้าถึงถูกปฏิเสธ

        // ทดสอบการใช้ระบบ Floor Access Control (การควบคุมการเข้าถึงชั้น)
        System.out.println("\n-- Testing Floor Access Control --");
        FloorAccessControl floor1 = new FloorAccessControl("1st", employeeCard);
        floor1.useLift();
        floor1.levelFloor();

        FloorAccessControl floor2 = new FloorAccessControl("2nd", visitorCard);
        floor2.useLift();
        floor2.levelFloor();

        // การวิเคราะห์พฤติกรรมการใช้บัตร
        System.out.println("\n-- Analyzing Card Usage Behavior --");
        employeeCard.analyzeUsage();
        visitorCard.analyzeUsage();
    }
}

