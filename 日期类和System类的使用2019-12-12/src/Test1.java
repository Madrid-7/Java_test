import java.util.Calendar;

class Time {
    public int getDays(Calendar c1, Calendar c2) {

        int days= 0 ;

        long millis=c2.getTimeInMillis() - c1.getTimeInMillis();

        days=(int) (millis / 1000 / 24 / 60 / 60);

        return Math.abs(days);
    }
}

public class Test1{

    public static void main(String[] args) {
        Time ex = new Time();

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.set(1949, 10, 1);
        c2.set(2010, 8, 15);

        System.out.println("相隔天数为：" + ex.getDays(c1, c2));
    }
}
