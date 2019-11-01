public class Calculator {
    private double num1;
    private double num2;

    public double getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public double Add() {
        return this.num1 + this.num2;
    }

    public double Sub() {
        return this.num1 - this.num2;
    }

    public double Mul() {
        return this.num1 * this.num2;
    }

    public double Div() {
        return this.num1 / this.num2;
    }
}
