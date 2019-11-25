class Employee {
    private String name;
    private int age;
    private String sex;

    public Employee() {}
    public Employee(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInfo() {
        return "name = "+this.name+
                "; age = "+this.age+
                "; sex = "+this.sex;
    }

    public void print(){
        System.out.println(this.getInfo());
    }
}

class Administrator extends Employee {
    private String position;
    private int annualPay;

    public Administrator() {}
    public Administrator(String name, int age, String sex) {
        super(name,age,sex);
    }
    public Administrator(String name, int age, String sex, String position, int annualPay) {
        super(name,age,sex);
        this.setPosition(position);
        this.setAnnualPay(annualPay);
    }

    public void setPosition(String position){
        this.position = position;
    }
    public void setAnnualPay(int annualPay){
        this.annualPay = annualPay;
    }

    public String getInfo() {
        return super.getInfo()+
                "; position = "+this.position+
                "; annualPay = "+this.annualPay;
    }
    public void print(){
        System.out.println(this.getInfo());
    }
}
