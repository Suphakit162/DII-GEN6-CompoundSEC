// models/FloorAccessControl.java
package models;

import services.Logger;

public class FloorAccessControl {
    private final AccessStrategy accessStrategy;  // ใช้ AccessStrategy แทนที่การควบคุมการเข้าถึงแบบเดิม
    private final Card card;

    // Constructor เพิ่มการกำหนดกลยุทธ์
    public FloorAccessControl(AccessStrategy accessStrategy, Card card) {
        this.accessStrategy = accessStrategy;
        this.card = card;
    }

    public void useLift() {
        accessStrategy.grantAccess(card);  // ใช้กลยุทธ์ที่กำหนดไว้
    }
}







