import java.util.ArrayList;
import java.util.List;

public class Test2 {
    //numRows：你的行数
    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> triangle = new ArrayList<>();

        if(numRows == 0) {
            return triangle;
        }
        triangle.add(new ArrayList<>());//0
        triangle.get(0).add(1);
        //行数
        for (int i = 1; i < numRows; i++) {
            List<Integer> curRow = new ArrayList<>();
            curRow.add(1);
            //上一行
            List<Integer> prevRow = triangle.get(i-1);
            //第i行的第j列
            for (int j = 1; j < i; j++) {
                int tmp = prevRow.get(j-1)+prevRow.get(j);
                curRow.add(tmp);
            }
            curRow.add(1);
            triangle.add(curRow);
        }
        return triangle;
    }

    public static void main(String[] args) {
        List<List<Integer>> ret = generate(5);

        for (List<Integer> list  : ret) {
            System.out.println(list);
        }
    }

}
