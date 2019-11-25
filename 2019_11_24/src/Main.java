import java.util.Arrays;

class Money implements Cloneable {
    double money = 21.2;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Person implements Cloneable {
    public String name;
    public Money m;
    public Person() {
        this.m = new Money();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //return super.clone();
        Person p = (Person)super.clone();
        p.m = (Money)this.m.clone();
        return p;
    }
}

class Student implements Comparable{
    private String name;
    private int age;
    private double score;
    Student (String name, int age, double score) {
        this.age = age;
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        return (int)(this.score - ((Student) o).score);
    }
}

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person();
        Person person2 = (Person)person.clone();

        System.out.println(person.m.money);
        System.out.println(person2.m.money);

        System.out.println("===================");
        person2.m.money = 77.7;
        System.out.println(person.m.money);
        System.out.println(person2.m.money);
    }

    public static void main1(String[] args) {
        Student s1 = new Student("zh",18,86.2);
        Student s2 = new Student("liu",28,76.2);
        Student s3 = new Student("yang",15,96.2);
        Student[] arr = {s1,s2,s3};
        Arrays.sort(arr);
        
    }
}
