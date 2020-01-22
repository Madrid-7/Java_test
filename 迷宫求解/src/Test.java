import java.sql.SQLException;
import java.util.Scanner;

public class Test {

    public static void printfArr(int[][] arr) {
        System.out.print("  ");
        for (int i = 0; i < arr[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        int count = 0;
        for (int[] arr1:arr) {
            System.out.printf("%-2d", count);
            for (int x:arr1) {

                if(x == 1)
                    System.out.print("■");
                else if(x == 0)
                    System.out.print(" ");
                else
                    System.out.print("*");
                System.out.print(" ");
            }
            count++;
            System.out.println();
        }
    }

    public static String getString(int[][] objarr){

        int typeNO = objarr.length;
        String tree = "";
        for (int i = 0 ;i < typeNO ; i++){
            //tree += "[";
            for (int j = 0; j < objarr[0].length; j++) {
                tree += objarr[i][j];
                if(j != objarr[0].length - 1) {
                    tree += ",";
                }
            }
            if(i != objarr.length - 1)
                tree += ";\n";
        }
        return tree;
    }

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.print("输入迷宫地图名：>");
        GetMap m = new GetMap();
        String mapName = null;

        int[][] arr = null;
        while (arr == null) {
            mapName = sc.next();
            arr = m.getMap(mapName);
        }
        System.out.println("迷宫地图如下：>");
        printfArr(arr);

        FindWays findWays = new FindWays(arr);

        System.out.print("输入入口：>");

        while (true) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (x > 0 && x < arr.length - 1 && y > 0 && y < arr[0].length - 1) {
                findWays.setStartX(x);
                findWays.setStartY(y);
                break;
            } else {
                System.out.print("坐标有误重新输入：>");
            }
        }

        System.out.print("输入出口：>");
        while (true) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (x > 0 && x < arr.length - 1 && y > 0 && y < arr[0].length - 1) {
                findWays.setEndX(x);
                findWays.setEndY(y);
                break;
            } else {
                System.out.print("坐标有误重新输入：>");
            }
        }

        if(findWays.findPath()) {
            int[][] mapResult = findWays.mapResult();
            System.out.println("迷宫的解如下：>");
            printfArr(mapResult);
            System.out.print("是否储存结果？Y/N :>");
            String s = sc.next();
            if(s.equalsIgnoreCase("Y")) {
                UpdateResult u = new UpdateResult();
                u.updateResult(mapName, getString(mapResult));
            }
        } else {
            System.out.println("该迷宫无解");
        }
    }
}