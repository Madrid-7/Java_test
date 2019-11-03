class Student{
    String name="student1";
    int age=15;
    public void stuMethod() {
        System.out.println("听课");
    }
    public void showStu() {
        System.out.println("姓名:"+name+",年龄:"+age+",学习方法：");
        stuMethod();
    }
}
class Undergraduate extends Student{
    String name="student2";
    int age=20;
    String major="经典力学";
    String degree="本科";
    public void stuMethod() {
        System.out.println("看书查资料");
    }
    public void showUnd() {
        System.out.println("姓名:"+name+",年龄:"+age);
        System.out.println("专业:"+major+",学位:"+degree+",学习方法：");
        stuMethod();
    }
} class GraduateStu extends Undergraduate{
    String name="student3";
    int age=25;
    String major="量子力学";
    String degree="研究生";
    String researchDir="量子力学应用";
    public void stuMethod() {
        System.out.println("深入研究思考");
    }
    public void showGra() {
        System.out.println("姓名:"+name+",年龄:"+age);
        System.out.println("专业:"+major+",学位:"+degree+",学习方法：");
        stuMethod();
    }
}
