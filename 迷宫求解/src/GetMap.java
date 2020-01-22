import java.sql.*;

public class GetMap {
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
    public int[][] getMap(String mapName) throws SQLException {

        Statement statement = connection.createStatement();

        //Scanner sc = new Scanner(System.in);
        //String mapName = sc.next();
        String sql = String.format("select mapString from maps where name = '%s'", mapName);
        //String sql = String.format("insert into users(username) values ('%s')", username);

        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next();
        String str = null;
        try {
            str = resultSet.getString(1);
        } catch (SQLException e) {
            System.out.print("无此名称对应地图，重新输入：>");
            return null;
        }

        resultSet.close();
        statement.close();
        connection.close();

        String[] strings = str.split(";\n");

        int[][] arr = new int[strings.length][];

        for (int i = 0; i < strings.length; i++) {
            String[] strs = strings[i].split(",");
            int[] arr2 = new int[strs.length];

            for (int j = 0; j < strs.length; j++) {
                arr2[j] = Integer.parseInt(strs[j]);
            }
            arr[i] = arr2;
        }
        return arr;
    }
}