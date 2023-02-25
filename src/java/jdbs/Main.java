package java.jdbs;

import java.sql.*;

public class Main
{
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1107";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static void main(String[] args) throws SQLException {
        BDSpace db = new BDSpace();
        Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
        String query = "SELECT * FROM table_space;";
        Statement statement = conn.createStatement();
        ResultSet resSet = statement.executeQuery(query);
        while(resSet.next()){
            int id;
            String name;
            float diameter_km;
            id=resSet.getInt("ID");
            name=resSet.getString("Name");
            diameter_km=resSet.getFloat("Diameter_km");
            Space space = new Space(id,name,diameter_km);
            System.out.println(space);
        }
        statement.close();
        conn.close();
    }
}