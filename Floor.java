interface Floor {

    String floor = "";
    String level = "";

    public void useLift();
    public void levelFloor();
}
// Floor Access Control System (สามารถควบคุมการเข้าถึงแต่ละชั้น)
class FloorAccessControl implements Floor {
    private String level;
    private Card card;

    public FloorAccessControl(String level, Card card) {
        this.level = level;
        this.card = card;
    }

    @Override
    public void useLift() {
        if (card instanceof EmployeeCard && card.check) {
            System.out.println("Lift access granted to employee for floor " + level);
        } else if (card instanceof VisitorCard && card.check) {
            System.out.println("Lift access granted to visitor for floor " + level);
        } else {
            System.out.println("Access denied for card on floor " + level);
        }
    }

    @Override
    public void levelFloor() {
        System.out.println("Accessing " + level + " floor...");
    }
}





