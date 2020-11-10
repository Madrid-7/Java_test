public class 试试volatile重排序引起的问题 {
    static class Person {
        String name1;
        String name2;
        String name3;
        String name4;
        String name5;
        String name6;
        String name7;

        public Person(String name1, String name2, String name3, String name4, String name5, String name6, String name7) {
            this.name1 = name1;
            this.name2 = name2;
            this.name3 = name3;
            this.name4 = name4;
            this.name5 = name5;
            this.name6 = name6;
            this.name7 = name7;
        }

        void print() {
            System.out.println(name1);
            System.out.println(name2);
            System.out.println(name3);
            System.out.println(name4);
            System.out.println(name5);
            System.out.println(name6);
            System.out.println(name7);
        }
    }

    static Person p = null;

    static class Init extends Thread {
        @Override
        public void run() {
            p = new Person("1", "2", "3", "4", "5", "6", "7");
        }
    }

    static class Print extends Thread {
        @Override
        public void run() {
            if (p != null) {
                p.print();
            }
        }
    }

    public static void main(String[] args) {
        Init init = new Init();
        Print print = new Print();
        init.start();
        print.start();
    }
}
