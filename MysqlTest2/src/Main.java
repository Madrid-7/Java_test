import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/data_2?useSSL=false",
                "root",
                "root"
        );

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select username from users");

        while (resultSet.next()) {
            String name = resultSet.getString(1);
            System.out.println(name);
        }
    }
}
