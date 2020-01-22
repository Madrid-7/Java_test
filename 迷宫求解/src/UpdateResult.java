import java.sql.*;

public class UpdateResult {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/data_3?useSSL=false",
                    "root",
                    "root"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateResult(String mapName, String result) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("insert into map_result(maps_id) select id from maps where name = '%s'", mapName);
        statement.executeUpdate(sql);

        sql = String.format("select id from maps where name = '%s'", mapName);
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next();
        int id = resultSet.getInt(1);
        sql = String.format("update map_result set result = '%s' where maps_id = '%d'", result, id);
        statement.executeUpdate(sql);
        statement.close();
        System.out.println("上传成功");
    }
}


