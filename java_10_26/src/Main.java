class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{"+"name='" + name + '\'' + ", age=" + age + '}';
    }

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public void show() {
        System.out.println("name:"+name+" " + "age:"+age);
    }
}


public class Main {
    public static void main(String[] args) {
    Person person = new Person("win",18);
    person.show();
    System.out.println(person);
    }
}
