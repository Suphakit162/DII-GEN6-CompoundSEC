
public abstract class Card {
    protected String name;
    protected int age;
    protected int id;
    protected String position;
    protected boolean check;

    public Card(String name, int age, int id, String position) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.position = position;
        this.check = false;
    }


    public abstract void validateCard();
    public abstract void analyzeUsage();//วิเคราะห์พฤติกรรมการใช้บัตร



    public String getDetails() {
        return "Name: " + name + ", Age: " + age + ", ID: " + id + ", Positive: " + position;
    }
}

// บัตรพนักงาน
class EmployeeCard extends Card {
    public EmployeeCard(String name, int age, int id, String position) {
        super(name, age, id, position);
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

    // การวิเคราะห์การใช้บัตรพนักงาน (ตัวอย่าง เช่น ตรวจสอบการใช้ในเวลา 08:00-17:00)
    @Override
    public void analyzeUsage() {
        // สมมุติว่าใช้เวลาในการทำงานในช่วง 08:00-17:00
        String currentTime = "14:00";  // เวลา ณ ปัจจุบัน (ตัวอย่างนี้ใช้ค่าเวลาคงที่)

        if (currentTime.compareTo("08:00") >= 0 && currentTime.compareTo("17:00") <= 0) {
            System.out.println("Employee Card " + name + " is used within the working hours.");
        } else {
            System.out.println("Employee Card " + name + " is used outside the working hours.");
        }
    }
}

// บัตรผู้เยี่ยมชม
class VisitorCard extends Card {
    public VisitorCard(String name, int age, int id, String position) {
        super(name, age, id, position);
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

    // การวิเคราะห์การใช้บัตรผู้เยี่ยมชม (ตัวอย่าง เช่น ตรวจสอบการใช้ในช่วงเวลาปกติหรือกลางคืน)
    @Override
    public void analyzeUsage() {
        // สมมุติว่าเวลาปัจจุบันคือ 23:00 (กลางคืน)
        String currentTime = "23:00";

        if (currentTime.compareTo("22:00") >= 0 && currentTime.compareTo("06:00") <= 0) {
            System.out.println("Visitor Card " + name + " is used during night time.");
        } else {
            System.out.println("Visitor Card " + name + " is used during normal hours.");
        }
    }
}



class CardManagement {
    public void addCard(Card card) {
        // Logic to add card
        System.out.println("Card " + card.getDetails() + " has been added.");
    }

    public void modifyCard(Card card) {
        // Logic to modify card details
        System.out.println("Card " + card.getDetails() + " has been modified.");
    }

    public void revokeCard(Card card) {
        // Logic to revoke card access
        System.out.println("Card " + card.getDetails() + " has been revoked.");
    }
}


//Id มีหลายชั้นเเละต้องเข้าถึงได้ตอนใช้ที่ไหนๆ ถ้าคนที่เก็บบัตรนี้ได้สามารถใช้การ์ดนี้ได้ทุกๆที่ สื่อคือ การ์ดใบนี้ควรมีพาสเวิร์ด ตามแบบ multi-facades
