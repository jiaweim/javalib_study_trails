package tutorial.sqlite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.sql.*;

import static org.testng.Assert.assertEquals;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 08 Apr 2019, 7:09 PM
 */
public class SQLiteStatements
{

    private static Connection conn;

    @BeforeClass
    public static void setUp() throws SQLException
    {
        URL resource = SQLiteStatements.class.getClassLoader().getResource("chinook.db");
        String url = "jdbc:sqlite:" + resource.getFile();
        conn = DriverManager.getConnection(url);

        DatabaseMetaData metaData = conn.getMetaData();
        assertEquals(metaData.getDriverName(), "SQLite JDBC");
        assertEquals(metaData.getDriverVersion(), "3.27.2.1");
    }

    @Test
    public void testSELECT() throws SQLException
    {
        String sql = "SELECT  1+1";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getObject(0));
        }
        stmt.close();
    }

    @Test
    public void testINSERT() throws SQLException
    {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:");

        String tableSql = "CREATE TABLE IF NOT EXISTS warehouses (id integer PRIMARY KEY, name text NOT NULL, capacity real)";
        Statement stmt = conn.createStatement();
        stmt.execute(tableSql);

        insert(conn, "Raw Materials", 3000);
        insert(conn, "Semifinished Goods", 4000);
        insert(conn, "Finished Goods", 5000);

        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, name, capacity FROM warehouses");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getDouble("capacity"));
        }


        conn.close();
    }

    public void insert(Connection conn, String name, double capacity) throws SQLException
    {
        String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setDouble(2, capacity);
        pstmt.executeUpdate();
    }
}
