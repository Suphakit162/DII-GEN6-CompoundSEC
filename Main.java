import models.*;
import services.Admin;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin(3);

        // สร้างบัตรพนักงานและผู้เยี่ยมชม
        Card employeeCard = new EmployeeCard("John Doe", 30, 1001);
        Card visitorCard = new VisitorCard("Jane Smith", 25, 2001);

        // เพิ่มบัตร
        admin.addCard(employeeCard);
        admin.addCard(visitorCard);

        // ทดสอบการใช้บัตร
        FloorAccessControl floor1 = new FloorAccessControl("1st", employeeCard);
        floor1.useLift();

        FloorAccessControl floor2 = new FloorAccessControl("2nd", visitorCard);
        floor2.useLift();

        // ยกเลิกบัตร
        admin.revokeCard(visitorCard);
    }
}


