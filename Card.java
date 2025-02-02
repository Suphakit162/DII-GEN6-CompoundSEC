import java.util.*;
public class Card {
    private String name;
    private int age;
    private int id;
    private String positive;
    private boolean check;

    public Card (String name,int age,int id,String positive) {
        this.name = name ;
        this.age = age ;
        this.id = id ;
        this.positive = positive ;
    }

    public boolean isCheck() {
        return check;
    }

    @Override
    public String toString() {
        return "Card{name='" + name + "', age=" + age + ", id=" + id + ", positive='" + positive + "'}";
    }

}
