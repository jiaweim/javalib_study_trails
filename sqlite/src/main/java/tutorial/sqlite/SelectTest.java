package tutorial.sqlite;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 20 Jun 2020, 4:30 PM
 */
public class SelectTest
{
    Connection connectMemory() throws SQLException
    {
        return DriverManager.getConnection("jdbc:sqlite::memory:");
    }

    @Test
    void testSimple()
    {
        String sql = "SELECT 1 + 1;";
        try (Connection conn = connectMemory();
             Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                int anInt = resultSet.getInt(1);
                assertEquals(anInt, 2);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
