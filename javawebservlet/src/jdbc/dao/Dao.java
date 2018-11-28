package jdbc.dao;
import java.sql.*;
/**
 * @author ดิหฌ
 * @create 2018-10-23 21:08
 **/
public class Dao {
    public static Connection getConnection() throws SQLException
    {
        String url = "jdbc:mysql://localhost:3306/dingcan";
        String username = "root";
        String password = "root";
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(ResultSet rs,PreparedStatement ps,Connection conn) throws SQLException
    {
        try
        {
            rs.close();
            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void updateclose(PreparedStatement ps,Connection conn) throws SQLException
    {
        try
        {
            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}