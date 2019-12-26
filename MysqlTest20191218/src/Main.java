// import com.mysql.jdbc.Driver;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.注册Driver
        Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接
        // cmd mysql -uroot -p<数据库的密码>
        String url = "jdbc:mysql://127.0.0.1:3306/data_2?useSSL=false";
        String username = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, username, password);

        //3.获取语句Statement
        Statement statement = connection.createStatement();

        //4.执行一条SELECT DATABASE()
        String sql = "SELECT DATABASE()";
        ResultSet resultSet = statement.executeQuery(sql);

        //5.事先知道结果只有一行 + 一列
        resultSet.next();
        String name = resultSet.getString(1);

        System.out.println(name);

        //System.out.println(connection);

        sql = "SELECT id, username FROM users ORDER BY id";
        resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            int id = resultSet.getInt(1);
            String user = resultSet.getString(2);
            System.out.println(id + ", " + user);
        }

        //关闭结果集(resultSet)
        resultSet.close();
        //关闭语句(statement)
        statement.close();
        //关闭连接(connection)
        connection.close();
    }
}
