import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddDataServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try
        {
            String url = "jdbc:mysql://localhost/db_space?allowPublicKeyRetrieval=true&serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "1107";

            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password))
            {
                PreparedStatement preparedStatement = conn.prepareStatement(("INSERT INTO [dbo].[table_space](name_, diameter_km_) VALUES(?,?);"));
                preparedStatement.setString(1,request.getParameter("name_"));
                preparedStatement.setString(2,request.getParameter("diameter_km_"));
                preparedStatement.execute();
                writer.println("Data in DB!");
            }
        }
        catch (Exception ex)
        {
            writer.println(ex.getMessage());
        }
        finally
        {
            writer.close();
        }
    }
}