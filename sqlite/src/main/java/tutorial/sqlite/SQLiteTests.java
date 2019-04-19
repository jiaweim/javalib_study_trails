package tutorial.sqlite;

import org.testng.annotations.Test;

import java.sql.*;

import static org.testng.Assert.assertEquals;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 08 Apr 2019, 6:06 PM
 */
public class SQLiteTests
{
    @Test
    public void testCreate()
    {
        String url = "jdbc:sqlite:D:\\code\\test\\chinook.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData metaData = conn.getMetaData();
                assertEquals(metaData.getDriverName(), "SQLite JDBC");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCreateTable()
    {
        String url = "jdbc:sqlite:D:\\code\\test\\chinook.db";
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (" +
                "id integer PRIMARY KEY, " +
                "name text NOT NULL, " +
                "capacity real);";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
