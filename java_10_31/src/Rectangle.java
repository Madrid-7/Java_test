public class Rectangle {
    private int w, h;
    public Rectangle() {
        this.w = 1;
        this.h = 1;
    }
    public Rectangle(int w, int h) {
        this.w = w;
        this.h = h;
    }
    public Rectangle(Rectangle r) {
        this.w = r.w;
        this.h = r.h;
    }
    public double getArea() {
        return w*h;
    }
    public double getPerimeter() {
        return 2*(w+h);
    }
    public int getWidth() {
        return w;
    }
    public int getHeight() {
        return h;
    }
    public String toString() {
        return "矩阵("+w+","+h+")";
    }
    public boolean equals(Rectangle  r) {
        if(this.w == r.w && this.h == r.h) {
            return true;
        } else {
            return false;
        }
    }
}
