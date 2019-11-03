import java.text.DecimalFormat;
public class Main {
    public static void main(String[] args) {
        DecimalFormat d = new DecimalFormat("#.####");
        Sphere a = new Sphere(1);
        System.out.println("半径为:"+a.r);
        System.out.println("截面圆的面积为： "+d.format(a.getArea()));
        System.out.println("截面圆的周长 为:"+d.format(a.getPerimeter()));
        System.out.println("表面积为:"+d.format(a.getSArea()));
        System.out.println("体积为:"+d.format(a.getVolume()));
    }
}
