package tutorial.sqlite;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 08 Apr 2019, 5:56 PM
 */
public class SQLiteConnect
{
    @Test
    public void test()
    {
        Connection conn = null;
        String url = "jdbc:sqlite:D:\\code\\test\\chinook.db";
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
