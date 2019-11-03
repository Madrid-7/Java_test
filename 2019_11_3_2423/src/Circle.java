public class Circle {
    int r;
    public Circle(int r) {
        this.r=r;
    }
    public double getArea() {
        return Math.PI*r*r;
    } public double getPerimeter() {
        return Math.PI*2*r;
    }
}
class Sphere extends Circle{
    public Sphere(int r) {
        super(r);
    }
    public double getSArea() {
        return Math.PI*4*r*r;
    }
    public double getVolume() {
        return (Math.PI*4*r*r*r)/3;
    }
}
