import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/space")
public class SpaceServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        try
        {
            String url = "jdbc:mysql://localhost/db_space?allowPublicKeyRetrieval=true&serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "1107";

            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password))
            {
                Statement statement = conn.createStatement();
                ResultSet res = statement.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'table_space';");
                writer.println("<table border='1'>");
                writer.println("<tr>");
                while(res.next())
                {
                    writer.println("<th>" + res.getObject(1) + "</th>" );
                }
                writer.println("</tr>");
                res.close();
                res = statement.executeQuery("SELECT * FROM table_space;");
                while(res.next())
                {
                    writer.println("<tr>");
                    for(int i = 1 ;  i <= res.getMetaData().getColumnCount(); i++)
                    {
                        writer.println("<td>" + res.getObject(i) + "</td>");
                    }
                    writer.println("</tr>");
                }
                writer.println("</table>");
                res.close();
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}