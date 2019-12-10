import java.util.ArrayList;
import java.util.List;

class Student {
    public String name;
    public String classroom;
    public double score;

    public Student(String name, String classroom, double score) {
        this.name = name;
        this.classroom = classroom;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", classroom='" + classroom + '\'' +
                ", score=" + score +
                '}'+"\n";
    }
}



public class Test1 {

    public static List<Character> choose(String str1, String str2) {
        List<Character> tmp = new ArrayList<>();
        for (int i = 0; i < str1.length(); i++) {
            int flag = 0;
            for (int j = 0; j < str2.length(); j++) {
                if(str1.charAt(i) == str2.charAt(j))
                    flag = 1;
            }
            if(flag == 0)
                tmp.add(str1.charAt(i));
        }
        return tmp;
    }

    public static void main(String[] args) {
        String str1 = "welcome to bit";
        String str2 = "come";
        List<Character> list = choose(str1,str2);
        for (char ch : list) {
            System.out.print(ch);
        }
    }


    public static void main1(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("qwe", "80", 100));
        list.add(new Student("asd", "81", 90));
        list.add(new Student("zxc", "82", 80));
        list.add(new Student("qwer", "80", 50));
        System.out.println(list);
    }
}
