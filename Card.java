
public abstract class Card {
    protected String name;
    protected int age;
    protected int id;
    protected String positive;
    protected boolean check;


    public Card(String name, int age, int id, String positive) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.positive = positive;
        this.check = false;
    }


    public abstract void validateCard();


    public String getDetails() {
        return "Name: " + name + ", Age: " + age + ", ID: " + id + ", Positive: " + positive;
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

