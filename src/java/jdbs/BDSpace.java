package java.jdbs;
import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDSpace
{
    private Connection connection;
    public BDSpace() throws SQLException
    {
        DriverManager.registerDriver(new FabricMySQLDriver());
    }

    public Connection getConnection(String url, String username, String password) throws SQLException{
        if(connection != null)
        {return connection;}
        connection = DriverManager.getConnection(url,username,password);
        {return connection;}
    }
}