
public class PrintSushu {

    public static void main(String[] args) {
        for (int i = 2; i < 100; i++) {
            int k = 0;
            for(int j = 2; j < i; j++) {
                if(i%j == 0)
                {
                    k = 1;
                    break;
                }
            }
            if(k == 0) {
                System.out.print(i + " ");
            }
        }
    }
}

