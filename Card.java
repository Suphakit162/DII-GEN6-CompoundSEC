
public abstract class Card {
    protected String name;
    protected int age;
    protected int id;
    protected String position;
    protected boolean check;


    public Card(String name, int age, int id, String positive) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.position = positive;
        this.check = false;
    }


    public abstract void validateCard();



    public String getDetails() {
        return "Name: " + name + ", Age: " + age + ", ID: " + id + ", Positive: " + position;
    }
}

// บัตรพนักงาน
class EmployeeCard extends Card {
    public EmployeeCard(String name, int age, int id, String positive) {
        super(name, age, id, positive);
    }

    @Override
    public void validateCard() {
        if (age >= 18) {
            check = true;
            System.out.println("Employee Card " + name + " is valid.");
        } else {
            System.out.println("Employee Card " + name + " is invalid.");
        }
    }


}

// บัตรผู้เยี่ยมชม
class VisitorCard extends Card {
    public VisitorCard(String name, int age, int id, String positive) {
        super(name, age, id, positive);
    }

    @Override
    public void validateCard() {
        if (age >= 12) {
            check = true;
            System.out.println("Visitor Card " + name + " is valid.");
        } else {
            System.out.println("Visitor Card " + name + " is invalid.");
        }
    }
}


//Id มีหลายชั้นเเละต้องเข้าถึงได้ตอนใช้ที่ไหนๆ ถ้าคนที่เก็บบัตรนี้ได้สามารถใช้การ์ดนี้ได้ทุกๆที่ สื่อคือ การ์ดใบนี้ควรมีพาสเวิร์ด ตามแบบ multi-facades
