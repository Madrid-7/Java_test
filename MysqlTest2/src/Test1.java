import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Test1 {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/data_2?useSSL=false",
                    "root",
                    "root"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("=============");
            System.out.println(" 1.注册用户");
            System.out.println(" 2.登录用户");
            System.out.println(" 其他.退出 ");
            System.out.println("=============");
            System.out.print("博客系统>");
            int select = sc.nextInt();
            switch (select) {
                case 1: registerUser(); break;
                case 2: break;
                default: running = false;
            }
        }
    }

    //注册用户
    private static void registerUser() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要注册的用户名");
        String username = sc.nextLine();
        Statement statement = connection.createStatement();
        String sql = String.format("insert into users(username) values ('%s')", username);
        statement.executeUpdate(sql);
        statement.close();
        System.out.println("注册成功");
    }

    private static void loginUser() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入用户名：");
        String username = sc.nextLine();
        Statement statement = connection.createStatement();
    }

}
